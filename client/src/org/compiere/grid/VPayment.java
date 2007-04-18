/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.grid;

import java.awt.*;
import java.awt.event.*;
import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

import org.adempiere.plaf.AdempierePLAF;
import org.compiere.apps.*;
import org.compiere.grid.ed.*;
import org.compiere.model.*;
import org.compiere.process.*;
import org.compiere.swing.*;
import org.compiere.util.*;

/**
 *	Display (and process) Payment Options.
 *  <pre>
 *  Payment Rule
 *  -B- Cash          (Date)          -> Cash Entry
 *  -P- Payment Term  (Term)
 *  -S- Check         (Routing, ..)   -> Payment Entry
 *  -K- CreditCard    (No)            -> Payment Entry
 *  -U- ACH Transfer  (Routing)       -> Payment Entry
 *
 *  When processing:
 *  - If an invoice is a S/K/U, but has no Payment Entry, it is changed to P
 *  - If an invoive is B and has no Cash Entry, it is created
 *  - An invoice is "Open" if it is "P" and no Payment
 *
 *  Entry:
 *  - If not processed, an invoice has no Cash or Payment entry
 *  - The entry is created, during "Online" and when Saving
 *
 *  Changes/Reversals:
 *  - existing Cash Entries are reversed and newly created
 *  - existing Payment Entries are not changed and then "hang there" and need to be allocated
 *  </pre>
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VPayment.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 */
public class VPayment extends CDialog
	implements ActionListener
{
	/**
	 *	Constructor
	 *
	 *	@param WindowNo	owning window
	 *  @param mTab     owning tab
	 *	@param button	button with access information
	 */
	public VPayment (int WindowNo, GridTab mTab, VButton button)
	{
		super(Env.getWindow(WindowNo), Msg.getMsg(Env.getCtx(), "Payment"), true);
		m_WindowNo = WindowNo;
		m_isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), WindowNo, "IsSOTrx"));
		m_mTab = mTab;
		try
		{
			bDateField = new VDate("DateAcct", false, false, true, DisplayType.Date, "DateAcct");
			jbInit();
			m_initOK = dynInit(button);     //  Null Pointer if order/invoice not saved yet
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VPayment", ex);
			m_initOK = false;
		}
		//
		AEnv.positionCenterWindow(Env.getWindow(WindowNo), this);
	}	//	VPayment

	/**	Window						*/
	private int                 m_WindowNo = 0;
	/**	Tab							*/
	private GridTab         		m_mTab;

	//	Data from Order/Invoice
	private String              m_DocStatus = null;
	/** Start Payment Rule          */
	private String				m_PaymentRule = "";
	/** Start Payment Term          */
	private int					m_C_PaymentTerm_ID = 0;
	/** Start Acct Date             */
	private Timestamp			m_DateAcct = null;
	/** Start Payment               */
	private int					m_C_Payment_ID = 0;
	private MPayment            m_mPayment = null;
	private MPayment            m_mPaymentOriginal = null;
	/** Start CashBook Line         */
	private int                 m_C_CashLine_ID = 0;
	private MCashLine			m_cashLine = null;
	/** Start CreditCard            */
	private String              m_CCType = "";
	/** Start Bank Account			*/
	private int					m_C_BankAccount_ID = 0;
	/** Start CashBook              */
	private int                 m_C_CashBook_ID = 0;

	/** Is SOTrx					*/
	private boolean				m_isSOTrx = true;

	/** Invoice Currency              */
	private int	 				m_C_Currency_ID = 0;
	private int                 m_AD_Client_ID = 0;
	private int                 m_AD_Org_ID = 0;
	private int                 m_C_BPartner_ID = 0;
	private BigDecimal			m_Amount = Env.ZERO;	//	Payment Amount
	//
	private boolean 			m_initOK = false;
	/** Only allow changing Rule        */
	private boolean             m_onlyRule = false;
	private DecimalFormat 		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
	private static Hashtable<Integer,KeyNamePair> s_Currencies = null;	//	EMU Currencies
	
	private boolean				m_needSave = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VPayment.class);

	//
	private CPanel mainPanel = new CPanel();
	private BorderLayout mainLayout = new BorderLayout();
	private CPanel northPanel = new CPanel();
	private CPanel centerPanel = new CPanel();
	private FlowLayout northLayout = new FlowLayout();
	private CComboBox paymentCombo = new CComboBox();
	private CLabel paymentLabel = new CLabel();
	private CardLayout centerLayout = new CardLayout();
	private CPanel bPanel = new CPanel();
	private CPanel kPanel = new CPanel();
	private GridBagLayout kLayout = new GridBagLayout();
	private CLabel kTypeLabel = new CLabel();
	private CComboBox kTypeCombo = new CComboBox();
	private CLabel kNumnerLabel = new CLabel();
	private CTextField kNumberField = new CTextField();
	private CLabel kExpLabel = new CLabel();
	private CTextField kExpField = new CTextField();
	private CLabel kApprovalLabel = new CLabel();
	private CTextField kApprovalField = new CTextField();
	private CPanel tPanel = new CPanel();
	private CLabel tAccountLabel = new CLabel();
	private CComboBox tAccountCombo = new CComboBox();
	private CPanel sPanel = new CPanel();
	private GridBagLayout sPanelLayout = new GridBagLayout();
	private CLabel sNumberLabel = new CLabel();
	private CTextField sNumberField = new CTextField();
	private CLabel sRoutingLabel = new CLabel();
	private CTextField sRoutingField = new CTextField();
	private CLabel sCurrencyLabel = new CLabel();
	private CComboBox sCurrencyCombo = new CComboBox();
	private CLabel bCurrencyLabel = new CLabel();
	private CComboBox bCurrencyCombo = new CComboBox();
	private CPanel pPanel = new CPanel();
	private CLabel pTermLabel = new CLabel();
	private CComboBox pTermCombo = new CComboBox();
	private GridBagLayout bPanelLayout = new GridBagLayout();
	private CLabel bAmountLabel = new CLabel();
	private CLabel bAmountField = new CLabel();
	private CLabel sAmountLabel = new CLabel();
	private CLabel sAmountField = new CLabel();
	private VDate bDateField;
	private CLabel bDateLabel = new CLabel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private CTextField sCheckField = new CTextField();
	private CLabel sCheckLabel = new CLabel();
	private CButton kOnline = new CButton();
	private CButton sOnline = new CButton();
	private CComboBox sBankAccountCombo = new CComboBox();
	private CLabel sBankAccountLabel = new CLabel();
	private GridBagLayout pPanelLayout = new GridBagLayout();
	private CLabel bCashBookLabel = new CLabel();
	private CComboBox bCashBookCombo = new CComboBox();
	private GridBagLayout tPanelLayout = new GridBagLayout();
	private CButton tOnline = new CButton();
	private CLabel kStatus = new CLabel();
	private CTextField tRoutingField = new CTextField();
	private CTextField tNumberField = new CTextField();
	private CLabel tStatus = new CLabel();
	private CLabel tRoutingText = new CLabel();
	private CLabel tNumberText = new CLabel();
	private CLabel sStatus = new CLabel();

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	private void jbInit() throws Exception
	{
		centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		getContentPane().add(mainPanel);
		mainPanel.setLayout(mainLayout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//
		northPanel.setLayout(northLayout);
		paymentLabel.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.add(paymentLabel, null);
		northPanel.add(paymentCombo, null);
		//
		centerPanel.setLayout(centerLayout);
		//      CreditCard
		kPanel.setLayout(kLayout);
		kNumberField.setPreferredSize(new Dimension(120, 21));
		kExpField.setPreferredSize(new Dimension(40, 21));
		kApprovalField.setPreferredSize(new Dimension(120, 21));
		kTypeLabel.setText(Msg.translate(Env.getCtx(), "CreditCardType"));
		kNumnerLabel.setText(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		kExpLabel.setText(Msg.getMsg(Env.getCtx(), "Expires"));
		kApprovalLabel.setText(Msg.translate(Env.getCtx(), "VoiceAuthCode"));
		kOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
		kOnline.addActionListener(this);
		kStatus.setText(" ");
		centerPanel.add(kPanel, "kPanel");
		centerLayout.addLayoutComponent(kPanel, "kPanel");
		kPanel.add(kTypeLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kTypeCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kNumnerLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kNumberField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kExpLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kExpField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kApprovalLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		kPanel.add(kApprovalField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		kPanel.add(kStatus, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		kPanel.add(kOnline, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//	DircetDebit/Credit
		tPanel.setLayout(tPanelLayout);
		tAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BP_BankAccount_ID"));
		tRoutingField.setColumns(8);
		tNumberField.setColumns(10);
		tRoutingText.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		tNumberText.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		tOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
		tStatus.setText(" ");
		centerPanel.add(tPanel, "tPanel");
		centerLayout.addLayoutComponent(tPanel, "tPanel");
		tPanel.add(tAccountLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		tPanel.add(tAccountCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		tPanel.add(tRoutingField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		tPanel.add(tNumberField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		tPanel.add(tStatus, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		tPanel.add(tRoutingText, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		tPanel.add(tNumberText, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		tPanel.add(tOnline, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//
		sPanel.setLayout(sPanelLayout);
		sBankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		sAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		sAmountField.setText("");
		sRoutingLabel.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		sNumberLabel.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		sCheckLabel.setText(Msg.translate(Env.getCtx(), "CheckNo"));
		sCheckField.setColumns(8);
		sCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		sNumberField.setPreferredSize(new Dimension(100, 21));
		sRoutingField.setPreferredSize(new Dimension(70, 21));
		sStatus.setText(" ");
		sOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
		centerPanel.add(sPanel, "sPanel");
		centerLayout.addLayoutComponent(sPanel, "sPanel");
		sPanel.add(sCurrencyLabel,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sCurrencyCombo,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		sPanel.add(sRoutingField,    new GridBagConstraints(1, 4, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		sPanel.add(sNumberField,    new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		sPanel.add(sCheckField,    new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		sPanel.add(sRoutingLabel,   new GridBagConstraints(0, 3, 1, 2, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0));
		sPanel.add(sNumberLabel,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sCheckLabel,   new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sBankAccountCombo,    new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		sPanel.add(sBankAccountLabel,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		sPanel.add(sStatus,    new GridBagConstraints(0, 7, 3, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		sPanel.add(sOnline,      new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		sPanel.add(sAmountField,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		sPanel.add(sAmountLabel,   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		//
		pPanel.setLayout(pPanelLayout);
		pTermLabel.setText(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"));
		centerPanel.add(pPanel, "pPanel");
		centerLayout.addLayoutComponent(pPanel, "pPanel");
		pPanel.add(pTermLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		pPanel.add(pTermCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		//
		bCashBookLabel.setText(Msg.translate(Env.getCtx(), "C_CashBook_ID"));
		bCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		bPanel.setLayout(bPanelLayout);
		bAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		bAmountField.setText("");
		bDateLabel.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		centerLayout.addLayoutComponent(bPanel, "bPanel");
		centerPanel.add(bPanel, "bPanel");
		bPanel.add(bCurrencyLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		bPanel.add(bCurrencyCombo,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		bPanel.add(bDateField,  new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		bPanel.add(bDateLabel,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		bPanel.add(bCashBookLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		bPanel.add(bCashBookCombo,  new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		bPanel.add(bAmountLabel,   new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		bPanel.add(bAmountField,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		//
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
	}	//	jbInit

	
	/**************************************************************************
	 *	Dynamic Init.
	 *		B (Cash)		(Currency)
	 *		K (CreditCard)  Type, Number, Exp, Approval
	 *		L (DirectDebit)	BPartner_Bank
	 *		P (PaymentTerm)	PaymentTerm
	 *		S (Check)		(Currency) CheckNo, Routing
	 *
	 *	Currencies are shown, if member of EMU
	 *  @param button payment type button
	 *  @return true if init OK
	 *  @throws Exception
	 */
	private boolean dynInit (VButton button) throws Exception
	{
		m_DocStatus = (String)m_mTab.getValue("DocStatus");
		log.config(m_DocStatus);

		if (m_mTab.getValue("C_BPartner_ID") == null)
		{
			ADialog.error(0, this, "SaveErrorRowNotFound");
			return false;
		}

		//	Is the Trx posted?
	//	String Posted = (String)m_mTab.getValue("Posted");
	//	if (Posted != null && Posted.equals("Y"))
	//		return false;

		//  DocStatus
		m_DocStatus = (String)m_mTab.getValue("DocStatus");
		if (m_DocStatus == null)
			m_DocStatus = "";
		//	Is the Trx closed?		Reversed / Voided / Cloased
		if (m_DocStatus.equals("RE") || m_DocStatus.equals("VO") || m_DocStatus.equals("CL"))
			return false;
		//  Document is not complete - allow to change the Payment Rule only
		if (m_DocStatus.equals("CO") || m_DocStatus.equals("WP") )
			m_onlyRule = false;
		else
			m_onlyRule = true;
		//	PO only  Rule
		if (!m_onlyRule		//	Only order has Warehouse
			&& !m_isSOTrx && m_mTab.getValue("M_Warehouse_ID") != null)
			m_onlyRule = true;
		
		centerPanel.setVisible(!m_onlyRule);
		
		
		//  Amount
		m_Amount = (BigDecimal)m_mTab.getValue("GrandTotal");
		if (!m_onlyRule && m_Amount.compareTo(Env.ZERO) == 0)
		{
			ADialog.error(m_WindowNo, this, "PaymentZero");
			return false;
		}
		bAmountField.setText(m_Format.format(m_Amount));
		sAmountField.setText(m_Format.format(m_Amount));

		/**
		 *	Get Data from Grid
		 */
		m_AD_Client_ID = ((Integer)m_mTab.getValue("AD_Client_ID")).intValue();
		m_AD_Org_ID = ((Integer)m_mTab.getValue("AD_Org_ID")).intValue();
		m_C_BPartner_ID = ((Integer)m_mTab.getValue("C_BPartner_ID")).intValue();
		m_PaymentRule = (String)m_mTab.getValue("PaymentRule");
		m_C_Currency_ID = ((Integer)m_mTab.getValue("C_Currency_ID")).intValue();
		m_DateAcct = (Timestamp)m_mTab.getValue("DateAcct");
		if (m_mTab.getValue("C_PaymentTerm_ID") != null)
			m_C_PaymentTerm_ID = ((Integer)m_mTab.getValue("C_PaymentTerm_ID")).intValue();
		//  Existing Payment
		if (m_mTab.getValue("C_Payment_ID") != null)
		{
			m_C_Payment_ID = ((Integer)m_mTab.getValue("C_Payment_ID")).intValue();
			if (m_C_Payment_ID != 0)
			{
				m_mPayment = new MPayment(Env.getCtx(), m_C_Payment_ID, null);
				m_mPaymentOriginal = new MPayment(Env.getCtx(), m_C_Payment_ID, null);	//	full copy
				//  CreditCard
				m_CCType = m_mPayment.getCreditCardType();
				kNumberField.setText(m_mPayment.getCreditCardNumber());
				kExpField.setText(m_mPayment.getCreditCardExp(null));
				kApprovalField.setText(m_mPayment.getVoiceAuthCode());
				kStatus.setText(m_mPayment.getR_PnRef());
				//	if approved/paid, don't let it change
				kTypeCombo.setReadWrite(!m_mPayment.isApproved());
				kNumberField.setReadWrite(!m_mPayment.isApproved());
				kExpField.setReadWrite(!m_mPayment.isApproved());
				kApprovalField.setReadWrite(!m_mPayment.isApproved());
				kOnline.setReadWrite(!m_mPayment.isApproved());
				//  Check
				m_C_BankAccount_ID = m_mPayment.getC_BankAccount_ID();
				sRoutingField.setText(m_mPayment.getRoutingNo());
				sNumberField.setText(m_mPayment.getAccountNo());
				sCheckField.setText(m_mPayment.getCheckNo());
				sStatus.setText(m_mPayment.getR_PnRef());
				//  Transfer
				tRoutingField.setText(m_mPayment.getRoutingNo());
				tNumberField.setText(m_mPayment.getAccountNo());
				tStatus.setText(m_mPayment.getR_PnRef());
			}
		}
		if (m_mPayment == null)
		{
			m_mPayment = new MPayment (Env.getCtx (), 0, null);
			m_mPayment.setAD_Org_ID(m_AD_Org_ID);
			m_mPayment.setAmount (m_C_Currency_ID, m_Amount);
		}

		//  Existing Cahbook entry
		m_cashLine = null;
		m_C_CashLine_ID = 0;
		if (m_mTab.getValue("C_CashLine_ID") != null)
		{
			m_C_CashLine_ID = ((Integer)m_mTab.getValue("C_CashLine_ID")).intValue();
			if (m_C_CashLine_ID == 0)
				m_cashLine = null;
			else
			{
				m_cashLine = new MCashLine (Env.getCtx(), m_C_CashLine_ID, null);
				m_DateAcct = m_cashLine.getStatementDate();
			}
		}

		//	Accounting Date
		bDateField.setValue(m_DateAcct);

		if (s_Currencies == null)
			loadCurrencies();

		//	Is the currency an EMU currency?
		Integer C_Currency_ID = new Integer(m_C_Currency_ID);
		if (s_Currencies.containsKey(C_Currency_ID))
		{
			Enumeration en = s_Currencies.keys();
			while (en.hasMoreElements())
			{
				Object key = en.nextElement();
				bCurrencyCombo.addItem(s_Currencies.get(key));
				sCurrencyCombo.addItem(s_Currencies.get(key));
			}
			sCurrencyCombo.addActionListener(this);
			sCurrencyCombo.setSelectedItem(s_Currencies.get(C_Currency_ID));
			bCurrencyCombo.addActionListener(this);
			bCurrencyCombo.setSelectedItem(s_Currencies.get(C_Currency_ID));
		}
		else	//	No EMU Currency
		{
			bCurrencyLabel.setVisible(false);	//	Cash
			bCurrencyCombo.setVisible(false);
			sCurrencyLabel.setVisible(false);	//	Check
			sCurrencyCombo.setVisible(false);
		}

		/**
		 *	Payment Combo
		 */
		if (m_PaymentRule == null)
			m_PaymentRule = "";
		ValueNamePair vp = null;
		HashMap values = button.getValues();
		Object[] a = values.keySet().toArray();
		for (int i = 0; i < a.length; i++)
		{			
                        String PaymentRule = (String)a[i];		//	used for Panel selection
			if (X_C_Order.PAYMENTRULE_DirectDebit.equals(PaymentRule)			//	SO
				&& !m_isSOTrx)
				continue;
			else if (X_C_Order.PAYMENTRULE_DirectDeposit.equals(PaymentRule)	//	PO 
				&& m_isSOTrx)
				continue;
                                                
			ValueNamePair pp = new ValueNamePair(PaymentRule, (String)values.get(a[i]));
			paymentCombo.addItem(pp);
			if (PaymentRule.toString().equals(m_PaymentRule))	//	to select
				vp = pp;
		}

		//	Set PaymentRule
		paymentCombo.addActionListener(this);
		if (vp != null)
			paymentCombo.setSelectedItem(vp);

		/**
		 * 	Load Payment Terms
		 */
		String SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_PaymentTerm_ID, Name FROM C_PaymentTerm WHERE IsActive='Y' ORDER BY Name",
			"C_PaymentTerm", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		KeyNamePair kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				pTermCombo.addItem(pp);
				if (key == m_C_PaymentTerm_ID)
					kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException ept)
		{
			log.log(Level.SEVERE, SQL, ept);
		}
		//	Set Selection
		if (kp != null)
			pTermCombo.setSelectedItem(kp);

		/**
		 * 	Load Accounts
		 */
		SQL = "SELECT a.C_BP_BankAccount_ID, NVL(b.Name, ' ')||a.AccountNo AS Acct "
			+ "FROM C_BP_BankAccount a,C_Bank b "
			+ "WHERE C_BPartner_ID=? AND a.IsActive='Y'";
		kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			pstmt.setInt(1, m_C_BPartner_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				tAccountCombo.addItem(pp);
		//			kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException eac)
		{
			log.log(Level.SEVERE, SQL, eac);
		}
		//	Set Selection
		if (kp != null)
			tAccountCombo.setSelectedItem(kp);

		/**
		 *	Load Credit Cards
		 */
		ValueNamePair[] ccs = m_mPayment.getCreditCards();
		vp = null;
		for (int i = 0; i < ccs.length; i++)
		{
			kTypeCombo.addItem(ccs[i]);
			if (ccs[i].getValue().equals(m_CCType))
				vp = ccs[i];
		}
		//	Set Selection
		if (vp != null)
			kTypeCombo.setSelectedItem(vp);

		/**
		 *  Load Bank Accounts
		 */
		SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_BankAccount_ID, Name || ' ' || AccountNo, IsDefault "
			+ "FROM C_BankAccount ba"
			+ " INNER JOIN C_Bank b ON (ba.C_Bank_ID=b.C_Bank_ID) "
			+ "WHERE b.IsActive='Y'",
			"ba", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				sBankAccountCombo.addItem(pp);
				if (key == m_C_BankAccount_ID)
					kp = pp;
				if (kp == null && rs.getString(3).equals("Y"))    //  Default
					kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException ept)
		{
			log.log(Level.SEVERE, SQL, ept);
		}
		//	Set Selection
		if (kp != null)
			sBankAccountCombo.setSelectedItem(kp);


		/**
		 *  Load Cash Books
		 */
		SQL = MRole.getDefault().addAccessSQL(
			"SELECT C_CashBook_ID, Name, AD_Org_ID FROM C_CashBook WHERE IsActive='Y'",
			"C_CashBook", MRole.SQL_NOTQUALIFIED, MRole.SQL_RO);
		kp = null;
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int key = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair pp = new KeyNamePair(key, name);
				bCashBookCombo.addItem(pp);
				if (key == m_C_CashBook_ID)
					kp = pp;
				if (kp == null && key == m_AD_Org_ID)       //  Default Org
					kp = pp;
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException epc)
		{
			log.log(Level.SEVERE, SQL, epc);
		}
		//	Set Selection
		if (kp != null)
		{
			bCashBookCombo.setSelectedItem(kp);
			if (m_C_CashBook_ID == 0)
				m_C_CashBook_ID = kp.getKey();  //  set to default to avoid 'cashbook changed' message
		}

		//
		return true;
	}	//	dynInit

	/**
	 *	Init OK to be able to make changes?
	 *  @return true if init OK
	 */
	public boolean isInitOK()
	{
		return m_initOK;
	}	//	isInitOK


	/**
	 *	Fill s_Currencies with EMU currencies
	 */
	private void loadCurrencies()
	{
		s_Currencies = new Hashtable<Integer,KeyNamePair>(12);	//	Currenly only 10+1
		String SQL = "SELECT C_Currency_ID, ISO_Code FROM C_Currency "
			+ "WHERE (IsEMUMember='Y' AND EMUEntryDate<SysDate) OR IsEuro='Y' "
			+ "ORDER BY 2";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(SQL, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt(1);
				String name = rs.getString(2);
				s_Currencies.put(new Integer(id), new KeyNamePair(id, name));
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, SQL, e);
		}
	}	//	loadCurrencies


	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
	//	log.fine( "VPayment.actionPerformed - " + e.getActionCommand());

		//	Finish
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (checkMandatory())
			{
				saveChanges (); // cannot recover
				dispose ();
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();

		//	Payment Method Change
		else if (e.getSource() == paymentCombo)
		{
			//	get selection
			ValueNamePair pp = (ValueNamePair)paymentCombo.getSelectedItem();
			if (pp != null)
			{
				String s = pp.getValue().toLowerCase();
				if (X_C_Order.PAYMENTRULE_DirectDebit.equalsIgnoreCase(s))
					s = X_C_Order.PAYMENTRULE_DirectDeposit.toLowerCase();
				s += "Panel";	
				centerLayout.show(centerPanel, s);	//	switch to panel
			}
		}

		//	Check Currency change
		else if (e.getSource() == sCurrencyCombo)
		{
			KeyNamePair pp = (KeyNamePair)sCurrencyCombo.getSelectedItem();
			BigDecimal amt = MConversionRate.convert(Env.getCtx(),
				m_Amount, m_C_Currency_ID, pp.getKey(), m_AD_Client_ID, m_AD_Org_ID);
			sAmountField.setText(m_Format.format(amt));
		}
		//	Cash Currency change
		else if (e.getSource() == bCurrencyCombo)
		{
			KeyNamePair pp = (KeyNamePair)bCurrencyCombo.getSelectedItem();
			BigDecimal amt = MConversionRate.convert(Env.getCtx(),
				m_Amount, m_C_Currency_ID, pp.getKey(), m_AD_Client_ID, m_AD_Org_ID);
			bAmountField.setText(m_Format.format(amt));
		}

		//  Online
		else if (e.getSource() == kOnline || e.getSource() == sOnline)
			processOnline();
	}	//	actionPerformed


	/**************************************************************************
	 *	Save Changes
	 *	@return true, if eindow can exit
	 */
	private boolean saveChanges()
	{
		ValueNamePair vp = (ValueNamePair)paymentCombo.getSelectedItem();
		String newPaymentRule = vp.getValue();
		log.info("New Rule: " + newPaymentRule);

		//  only Payment Rule
		if (m_onlyRule)
		{
			if (!newPaymentRule.equals(m_PaymentRule))
				m_mTab.setValue("PaymentRule", newPaymentRule);
			return true;
		}

		//	New Values
		Timestamp newDateAcct = m_DateAcct;
		int newC_PaymentTerm_ID = m_C_PaymentTerm_ID;
		int newC_CashLine_ID = m_C_CashLine_ID;
		int newC_CashBook_ID = m_C_CashBook_ID;
		String newCCType = m_CCType;
		int newC_BankAccount_ID = 0;
		
		//	B (Cash)		(Currency)
		if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Cash))
		{
			KeyNamePair kp = (KeyNamePair)bCashBookCombo.getSelectedItem();
			if (kp != null)
				newC_CashBook_ID = kp.getKey();
			newDateAcct = (Timestamp)bDateField.getValue();
		}

		//	K (CreditCard)  Type, Number, Exp, Approval
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_CreditCard))
		{
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			if (vp != null)
				newCCType = vp.getValue();
		}

		//	T (Transfer)	BPartner_Bank
		else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit) 
			|| newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit) )
		{
			tAccountCombo.getSelectedItem();
		}

		//	P (PaymentTerm)	PaymentTerm
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
		{
			KeyNamePair kp = (KeyNamePair)pTermCombo.getSelectedItem();
			if (kp != null)
				newC_PaymentTerm_ID = kp.getKey();
		}

		//	S (Check)		(Currency) CheckNo, Routing
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Check))
		{
		//	sCurrencyCombo.getSelectedItem();
			KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
			if (kp != null)
				newC_BankAccount_ID = kp.getKey();
		}
		else
			return false;

		//  find Bank Account if not qualified yet
		if ("KTSD".indexOf(newPaymentRule) != -1 && newC_BankAccount_ID == 0)
		{
			String tender = MPayment.TENDERTYPE_CreditCard;
			if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit))
				tender = MPayment.TENDERTYPE_DirectDeposit;
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit))
				tender = MPayment.TENDERTYPE_DirectDebit;
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_Check))
				tender = MPayment.TENDERTYPE_Check;
		}

		/***********************
		 *  Changed PaymentRule
		 */
		if (!newPaymentRule.equals(m_PaymentRule))
		{
			log.fine("Changed PaymentRule: " + m_PaymentRule + " -> " + newPaymentRule);
			//  We had a CashBook Entry
			if (m_PaymentRule.equals(X_C_Order.PAYMENTRULE_Cash))
			{
				log.fine("Old Cash - " + m_cashLine);
				if (m_cashLine != null)
				{
					MCashLine cl = m_cashLine.createReversal();
					if (cl.save())
						log.config( "CashCancelled");
					else
						ADialog.error(m_WindowNo, this, "PaymentError", "CashNotCancelled");
				}
				newC_CashLine_ID = 0;      //  reset
			}
			//  We had a change in Payment type (e.g. Check to CC)
			else if ("KTSD".indexOf(m_PaymentRule) != -1 && "KTSD".indexOf(newPaymentRule) != -1 && m_mPaymentOriginal != null)
			{
				log.fine("Old Payment(1) - " + m_mPaymentOriginal);
				m_mPaymentOriginal.setDocAction(DocAction.ACTION_Reverse_Correct);
				boolean ok = m_mPaymentOriginal.processIt(DocAction.ACTION_Reverse_Correct);
				m_mPaymentOriginal.save();
				if (ok)
					log.info( "Payment Canecelled - " + m_mPaymentOriginal);
				else
					ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCancelled " + m_mPaymentOriginal.getDocumentNo());
				m_mPayment.resetNew();
			}
			//	We had a Payment and something else (e.g. Check to Cash)
			else if ("KTSD".indexOf(m_PaymentRule) != -1 && "KTSD".indexOf(newPaymentRule) == -1)
			{
				log.fine("Old Payment(2) - " + m_mPaymentOriginal);
				if (m_mPaymentOriginal != null)
				{
					m_mPaymentOriginal.setDocAction(DocAction.ACTION_Reverse_Correct);
					boolean ok = m_mPaymentOriginal.processIt(DocAction.ACTION_Reverse_Correct);
					m_mPaymentOriginal.save();
					if (ok)        //  Cancel Payment
					{
						log.fine("PaymentCancelled " + m_mPayment.getDocumentNo ());
						m_mTab.getTableModel().dataSave(true);
						m_mPayment.resetNew();
						m_mPayment.setAmount(m_C_Currency_ID, m_Amount);
					}
					else
						ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCancelled " + m_mPayment.getDocumentNo());
				}
			}
		}

		//  Get Order and optionally Invoice
		int C_Order_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Order_ID");
		int C_Invoice_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Invoice_ID");
		if (C_Invoice_ID == 0 && m_DocStatus.equals("CO"))
			C_Invoice_ID = getInvoiceID (C_Order_ID);

		//  Amount sign negative, if ARC (Credit Memo) or API (AP Invoice)
		boolean negateAmt = false;
		MInvoice invoice = null;
		if (C_Invoice_ID != 0)
		{
			invoice = new MInvoice (Env.getCtx(), C_Invoice_ID, null);
			negateAmt = invoice.isCreditMemo();
		}
		MOrder order = null;
		if (invoice == null && C_Order_ID != 0)
			order = new MOrder (Env.getCtx(), C_Order_ID, null);
		BigDecimal payAmount = m_Amount;
		if (negateAmt)
			payAmount = m_Amount.negate();
		// Info
		log.config("C_Order_ID=" + C_Order_ID + ", C_Invoice_ID=" + C_Invoice_ID + ", NegateAmt=" + negateAmt);

		/***********************
		 *  CashBook
		 */
		if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Cash))
		{
			log.fine("Cash");
			String description = (String)m_mTab.getValue("DocumentNo");

			if (C_Invoice_ID == 0 && order == null)
			{
				log.config("No Invoice!");
				ADialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
			}
			else
			{
				//  Changed Amount
				if (m_cashLine != null
					&& payAmount.compareTo(m_cashLine.getAmount()) != 0)
				{
					log.config("Changed CashBook Amount");
					m_cashLine.setAmount(payAmount);
					if (m_cashLine.save())
						log.config("CashAmt Changed");
				}
				//	Different Date/CashBook
				if (m_cashLine != null
					&& (newC_CashBook_ID != m_C_CashBook_ID 
						|| !TimeUtil.isSameDay(m_cashLine.getStatementDate(), newDateAcct)))
				{
					log.config("Changed CashBook/Date: " + m_C_CashBook_ID + "->" + newC_CashBook_ID);
					MCashLine reverse = m_cashLine.createReversal();
					if (!reverse.save())
						ADialog.error(m_WindowNo, this, "PaymentError", "CashNotCancelled");
					m_cashLine = null;
				}
				
				//	Create new
				if (m_cashLine == null)
				{
					log.config("New CashBook");
					int C_Currency_ID = 0;
					if (invoice != null)
						C_Currency_ID = invoice.getC_Currency_ID();
					if (C_Currency_ID == 0 && order != null)
						C_Currency_ID = order.getC_Currency_ID();
					MCash cash = null;
					if (newC_CashBook_ID != 0)
						cash = MCash.get (Env.getCtx(), newC_CashBook_ID, newDateAcct, null);
					else	//	Default
						cash = MCash.get (Env.getCtx(), m_AD_Org_ID, newDateAcct, C_Currency_ID, null);
					if (cash == null || cash.get_ID() == 0)
						ADialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
					else
					{
						MCashLine cl = new MCashLine (cash);
						if (invoice != null)
							cl.setInvoice(invoice);
						if (order != null)
						{
							cl.setOrder(order, null);
							m_needSave = true;
						}
						if (cl.save())
						{	
							log.config("CashCreated");
							if(invoice == null && C_Invoice_ID != 0)
							{
								invoice = new MInvoice (Env.getCtx(), C_Invoice_ID, null);								
								invoice.setC_CashLine_ID(cl.getC_CashLine_ID());
								invoice.save();
							}
							if(order == null && C_Order_ID != 0)
							{
								order = new MOrder (Env.getCtx(), C_Order_ID, null);
								order.setC_CashLine_ID(cl.getC_CashLine_ID());
								order.save();
							}
							log.config("Update Order & Invoice with CashLine");
						}	
						else
							ADialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
					}
				}
			}	//	have invoice
		}
		/***********************
		 *  Payments
		 */
		if ("KS".indexOf(newPaymentRule) != -1)
		{
			log.fine("Payment - " + newPaymentRule);
			//  Set Amount
			m_mPayment.setAmount(m_C_Currency_ID, payAmount);
			if (newPaymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
			{
				m_mPayment.setCreditCard(MPayment.TRXTYPE_Sales, newCCType,
					kNumberField.getText(), "", kExpField.getText());
				m_mPayment.setPaymentProcessor();
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit)
				|| newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit))
			{
				m_mPayment.setBankACH(newC_BankAccount_ID, m_isSOTrx, newPaymentRule, 
					tRoutingField.getText(), tNumberField.getText());
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_Check))
			{
				m_mPayment.setBankCheck(newC_BankAccount_ID, m_isSOTrx, sRoutingField.getText(),
					sNumberField.getText(), sCheckField.getText());
			}
			m_mPayment.setC_BPartner_ID(m_C_BPartner_ID);
			m_mPayment.setC_Invoice_ID(C_Invoice_ID);
			if (order != null)
			{
				m_mPayment.setC_Order_ID(C_Order_ID);
				m_needSave = true;
			}
			m_mPayment.setDateTrx(m_DateAcct);
			m_mPayment.setDateAcct(m_DateAcct);
			m_mPayment.save();
			
			//  Save/Post
			if (MPayment.DOCSTATUS_Drafted.equals(m_mPayment.getDocStatus()))
			{
				boolean ok = m_mPayment.processIt(DocAction.ACTION_Complete);
				m_mPayment.save();
				if (ok)
					ADialog.info(m_WindowNo, this, "PaymentCreated", m_mPayment.getDocumentNo());
				else
					ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
			}
			else
				log.fine("NotDraft " + m_mPayment);
		}


		/**********************
		 *	Save Values to mTab
		 */
		log.config("Saving changes");
		//
		if (!newPaymentRule.equals(m_PaymentRule))
			m_mTab.setValue("PaymentRule", newPaymentRule);
		//
		if (!newDateAcct.equals(m_DateAcct))
			m_mTab.setValue("DateAcct", newDateAcct);
		//
		if (newC_PaymentTerm_ID != m_C_PaymentTerm_ID)
			m_mTab.setValue("C_PaymentTerm_ID", new Integer(newC_PaymentTerm_ID));
		//	Set Payment
		if (m_mPayment.getC_Payment_ID() != m_C_Payment_ID)
		{
			if (m_mPayment.getC_Payment_ID() == 0)
				m_mTab.setValue("C_Payment_ID", null);
			else
				m_mTab.setValue("C_Payment_ID", new Integer(m_mPayment.getC_Payment_ID()));
		}
		//	Set Cash
		if (newC_CashLine_ID != m_C_CashLine_ID)
		{
			if (newC_CashLine_ID == 0)
				m_mTab.setValue("C_CashLine_ID", null);
			else
				m_mTab.setValue("C_CashLine_ID", new Integer(newC_CashLine_ID));
		}
		return true;
	}	//	saveChanges

	/**
	 *  Check Mandatory
	 *  @return true if all mandatory items are OK
	 */
	private boolean checkMandatory()
	{
	//	log.config( "VPayment.checkMandatory");

		ValueNamePair vp = (ValueNamePair)paymentCombo.getSelectedItem();
		String PaymentRule = vp.getValue();
		//  only Payment Rule
		if (m_onlyRule)
			return true;

		Timestamp DateAcct = m_DateAcct;
		int C_PaymentTerm_ID = m_C_PaymentTerm_ID;
		int C_CashBook_ID = m_C_CashBook_ID;
		String CCType = m_CCType;
		//
		int C_BankAccount_ID = 0;

		/***********************
		 *	Mandatory Data Check
		 */
		boolean dataOK = true;
		//	B (Cash)		(Currency)
		if (PaymentRule.equals(MOrder.PAYMENTRULE_Cash))
		{
			KeyNamePair kp = (KeyNamePair)bCashBookCombo.getSelectedItem();
			if (kp != null)
				C_CashBook_ID = kp.getKey();
			DateAcct = (Timestamp)bDateField.getValue();
		}

		//	K (CreditCard)  Type, Number, Exp, Approval
		else if (PaymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
		{
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			if (vp != null)
				CCType = vp.getValue();
			//
			String error = MPaymentValidate.validateCreditCardNumber(kNumberField.getText(), CCType);
			if (error.length() != 0)
			{
				kNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());
				if (error.indexOf('?') == -1)
				{
					ADialog.error(m_WindowNo, this, error);
					dataOK = false;
				}
				else    //  warning
				{
					if (!ADialog.ask(m_WindowNo, this, error))
						dataOK = false;
				}
			}
			error = MPaymentValidate.validateCreditCardExp(kExpField.getText());
			if(error.length() != 0)
			{
				kExpField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
		}

		//	T (Transfer)	BPartner_Bank
		else if (PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDeposit)
			|| PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDebit))
		{
			KeyNamePair bpba = (KeyNamePair)tAccountCombo.getSelectedItem();
			if (bpba == null)

			{
				tAccountCombo.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, "PaymentBPBankNotFound");
				dataOK = false;
			}
		}	//	Direct

		//	P (PaymentTerm)	PaymentTerm
		else if (PaymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
		{
			KeyNamePair kp = (KeyNamePair)pTermCombo.getSelectedItem();
			if (kp != null)
				C_PaymentTerm_ID = kp.getKey();
		}

		//	S (Check)		(Currency) CheckNo, Routing
		else if (PaymentRule.equals(MOrder.PAYMENTRULE_Check))
		{
		//	sCurrencyCombo.getSelectedItem();
			KeyNamePair kp = (KeyNamePair)sBankAccountCombo.getSelectedItem();
			if (kp != null)
				C_BankAccount_ID = kp.getKey();
			String error = MPaymentValidate.validateRoutingNo(sRoutingField.getText());
			if (error.length() != 0)
			{
				sRoutingField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
			error = MPaymentValidate.validateAccountNo(sNumberField.getText());
			if (error.length() != 0)
			{
				sNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
			error = MPaymentValidate.validateCheckNo(sCheckField.getText());
			if (error.length() != 0)
			{
				sCheckField.setBackground(AdempierePLAF.getFieldBackground_Error());
				ADialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
		}
		else
		{
			log.log(Level.SEVERE, "Unknown PaymentRule " + PaymentRule);
			return false;
		}

		//  find Bank Account if not qualified yet
		if ("KTSD".indexOf(PaymentRule) != -1 && C_BankAccount_ID == 0)
		{
			String tender = MPayment.TENDERTYPE_CreditCard;
			if (PaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit))
				tender = MPayment.TENDERTYPE_DirectDeposit;
			else if (PaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit))
				tender = MPayment.TENDERTYPE_DirectDebit;
			else if (PaymentRule.equals(MOrder.PAYMENTRULE_Check))
				tender = MPayment.TENDERTYPE_Check;
			//	Check must have a bank account
			if (C_BankAccount_ID == 0 && "S".equals(PaymentRule))
                        {
				ADialog.error(m_WindowNo, this, "PaymentNoProcessor");
				dataOK = false;
			}
		}
		//
		log.config("OK=" + dataOK);
		return dataOK;
	}   //  checkMandatory

	/**
	 *  Get Invoice ID for Order
	 *  @param C_Order_ID order
	 *  @return C_Invoice_ID or 0 if not found
	 */
	private static int getInvoiceID (int C_Order_ID)
	{
		int retValue = 0;
		String sql = "SELECT C_Invoice_ID FROM C_Invoice WHERE C_Order_ID=? "
			+ "ORDER BY C_Invoice_ID DESC";     //  last invoice
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, C_Order_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = rs.getInt(1);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		return retValue;
	}   //  getInvoiceID

	
	/**************************************************************************
	 *  Process Online (sales only) - if approved - exit
	 */
	private void processOnline()
	{
		log.config("");
		if (!checkMandatory())
			return;

		boolean approved = false;
		String info = "";
		//
		ValueNamePair vp = (ValueNamePair)paymentCombo.getSelectedItem();
		String PaymentRule = vp.getValue();

		//  --  CreditCard
		if (PaymentRule.equals(X_C_Order.PAYMENTRULE_CreditCard))
		{
			vp = (ValueNamePair)kTypeCombo.getSelectedItem();
			String CCType = vp.getValue();

			m_mPayment.setCreditCard(MPayment.TRXTYPE_Sales, CCType,
				kNumberField.getText(), "", kExpField.getText());
			m_mPayment.setAmount(m_C_Currency_ID, m_Amount);
			m_mPayment.setPaymentProcessor();
			m_mPayment.setC_BPartner_ID(m_C_BPartner_ID);
			//
			int C_Invoice_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Invoice_ID");
			if (C_Invoice_ID == 0 && m_DocStatus.equals("CO"))
			{
				int C_Order_ID = Env.getContextAsInt(Env.getCtx(), m_WindowNo, "C_Order_ID");
				C_Invoice_ID = getInvoiceID (C_Order_ID);
			}
			m_mPayment.setC_Invoice_ID(C_Invoice_ID);
			m_mPayment.setDateTrx(m_DateAcct);
			//  Set Amount
			m_mPayment.setAmount(m_C_Currency_ID, m_Amount);

			approved = m_mPayment.processOnline();
			info = m_mPayment.getR_RespMsg() + " (" + m_mPayment.getR_AuthCode()
				+ ") ID=" + m_mPayment.getR_PnRef();
			boolean saved = m_mPayment.save();

			if (approved)
			{
				boolean ok = m_mPayment.processIt(DocAction.ACTION_Complete);
				m_mPayment.save();
				if (ok)
					ADialog.info(m_WindowNo, this, "PaymentProcessed", info + "\n" + m_mPayment.getDocumentNo());
				else
					ADialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
				saveChanges();
				dispose();
			}
			else
			{
				ADialog.error(m_WindowNo, this, "PaymentNotProcessed", info);
			}
		}
		else
			ADialog.error(m_WindowNo, this, "PaymentNoProcessor");
	}   //  online

	/**
	 * 	Need Save record (payment with waiting order)
	 *	@return true if payment with waiting order
	 */
	public boolean needSave()
	{
		return m_needSave;
	}	//	needSave
	
}	//	VPayment
