/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.VerticalBox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MConversionRate;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.model.MRole;
import org.compiere.model.X_C_Order;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Separator;

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
 * 	@author 	Niraj Sohun
 * 				Payment Rule : Based on VPayment
 */

public class WPayment extends Window implements EventListener
{
	private static final long serialVersionUID = 1L;

	/**	Window						*/
	private int m_WindowNo = 0;
	
	/**	Tab							*/
	private GridTab m_mTab;

	//	Data from Order/Invoice
	private String              m_DocStatus = null;
	
	/** Start Payment Rule          */
	private String m_PaymentRule = "";
	
	/** Start Payment Term          */
	private int	m_C_PaymentTerm_ID = 0;
	
	/** Start Acct Date             */
	private Timestamp m_DateAcct = null;
	
	/** Start Payment               */
	private int m_C_Payment_ID = 0;
	
	private MPayment m_mPayment = null;
	private MPayment m_mPaymentOriginal = null;
	
	/** Start CashBook Line         */
	private int m_C_CashLine_ID = 0;
	
	private MCashLine m_cashLine = null;
	
	/** Start CreditCard            */
	private String m_CCType = "";
	
	/** Start Bank Account			*/
	private int m_C_BankAccount_ID = 0;
	
	/** Start CashBook              */
	private int m_C_CashBook_ID = 0;

	/** Is SOTrx					*/
	private boolean m_isSOTrx = true;

	/** Invoice Currency              */
	private int	m_C_Currency_ID = 0;
	
	private int m_AD_Client_ID = 0;
	private int m_AD_Org_ID = 0;
	private int m_C_BPartner_ID = 0;
	
	//	Payment Amount
	private BigDecimal m_Amount = Env.ZERO;

	private boolean 			m_initOK = false;
	
	/** Only allow changing Rule        */
	private boolean m_onlyRule = false;
	
	private DecimalFormat m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
	
	//	EMU Currencies
	private static Hashtable<Integer,KeyNamePair> s_Currencies = null;	
	
	private boolean	m_needSave = false;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPayment.class);

	/**	Date Editor 	*/
	Datebox bDateField = new Datebox();

	private Textbox bAmountField = new Textbox();
	private Textbox sAmountField = new Textbox();
	private Textbox kAmountField = new Textbox();
	private Textbox kNumberField = new Textbox();
	private Textbox kExpField = new Textbox();
	private Textbox kApprovalField = new Textbox();
	private Textbox sRoutingField = new Textbox();
	private Textbox sNumberField = new Textbox();
	private Textbox sCheckField = new Textbox();
	private Textbox tRoutingField = new Textbox();
	private Textbox tNumberField = new Textbox();

	private Label kStatus = new Label();
	private Label sStatus = new Label();
	private Label tStatus = new Label();
	private Label bCurrencyLabel = new Label();
	private Label sCurrencyLabel = new Label();
	private Label paymentLabel = new Label();
	private Label kNumberLabel = new Label();
	private Label kExpLabel = new Label();
	private Label kApprovalLabel = new Label();
	private Label kAmountLabel = new Label();
	private Label kTypeLabel = new Label();
	private Label tRoutingText = new Label();
	private Label tNumberText = new Label();	
	private Label tAccountLabel = new Label();
	private Label sBankAccountLabel = new Label();
	private Label sAmountLabel = new Label();
	private Label sRoutingLabel = new Label();
	private Label sNumberLabel = new Label();
	private Label sCheckLabel = new Label();
	private Label pTermLabel = new Label();
	private Label bAmountLabel = new Label();
	private Label bDateLabel = new Label();
	private Label bCashBookLabel = new Label();

	private Listbox kTypeCombo = new Listbox();
	private Listbox bCurrencyCombo = new Listbox();
	private Listbox sCurrencyCombo = new Listbox();
	private Listbox paymentCombo = new Listbox();
	private Listbox pTermCombo = new Listbox();
	private Listbox tAccountCombo = new Listbox();
	private Listbox sBankAccountCombo = new Listbox();
	private Listbox bCashBookCombo = new Listbox();
	
	private Button kOnline = new Button();
	private Button sOnline = new Button();
	private Button btnOk = new Button();
	private Button btnCancel = new Button();
	private Button tOnline = new Button();
	
	private VerticalBox mainPanel = new VerticalBox();
	private Hbox northPanel = new Hbox();
	private VerticalBox centerPanel = new VerticalBox();
	private VerticalBox kPanel = new VerticalBox();
	private VerticalBox tPanel = new VerticalBox();
	private VerticalBox sPanel = new VerticalBox();
	private Hbox pPanel = new Hbox();
	private VerticalBox bPanel = new VerticalBox();
	
	/**
	 *	Constructor
	 *
	 *	@param WindowNo	owning window
	 *  @param mTab     owning tab
	 *	@param button	button with access information
	 */
	
	public WPayment (int WindowNo, GridTab mTab, WButtonEditor button)
	{
		super();
		
		m_WindowNo = WindowNo;
		m_isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), WindowNo, "IsSOTrx"));
		m_mTab = mTab;
		
		try
		{
			//bDateField = new WDateEditor("DateAcc", "", false, false, true);
			bDateField.setValue(new Date(System.currentTimeMillis()));
			
			jbInit();
			m_initOK = dynInit(button); // Null Pointer if order/invoice not saved yet
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "WPayment", ex);
			m_initOK = false;
		}

		this.setVisible(true);
		//show
	}	//	VPayment
	
	/**
	 *	Static Init
	 *  @throws Exception
	 */
	
	private void jbInit() throws Exception
	{
		this.setBorder("normal");
		this.setWidth("400px");
		this.setTitle("Payment");
		this.setClosable(true);
		this.appendChild(mainPanel);
		
		centerPanel.setWidth("100%");
		
		mainPanel.setWidth("100%");
		mainPanel.appendChild(northPanel);
		mainPanel.appendChild(new Separator());
		mainPanel.appendChild(centerPanel);
		
		paymentLabel.setValue(Msg.translate(Env.getCtx(), "PaymentRule"));
		
		paymentCombo.setRows(0);
		paymentCombo.setMold("select");
		paymentCombo.addEventListener(Events.ON_SELECT, this);
		
		northPanel.setWidth("100%");
		northPanel.setWidths("40%, 60%");
		northPanel.appendChild(paymentLabel);
		northPanel.appendChild(paymentCombo);
		
		// Credit Card
		
		kPanel.setWidth("100%");
		
		kTypeLabel.setValue(Msg.translate(Env.getCtx(), "CreditCardType"));
		kNumberLabel.setValue(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		kExpLabel.setValue(Msg.getMsg(Env.getCtx(), "Expires"));
		kApprovalLabel.setValue(Msg.translate(Env.getCtx(), "VoiceAuthCode"));
		kAmountLabel.setValue(Msg.getMsg(Env.getCtx(), "Amount"));
		kAmountField.setText("");
		kOnline.setLabel(Msg.getMsg(Env.getCtx(), "Online"));
		kOnline.addEventListener(Events.ON_CLICK, this);
		kStatus.setValue(" ");
		centerPanel.appendChild(kPanel);
		
		Hbox boxKType = new Hbox();
		
		kTypeCombo.setRows(0);
		kTypeCombo.setMold("select");
		kTypeCombo.addEventListener(Events.ON_SELECT, this);
		
		boxKType.setWidth("100%");
		boxKType.setWidths("50%, 50%");
		boxKType.appendChild(kTypeLabel);
		boxKType.appendChild(kTypeCombo);
		kPanel.appendChild(boxKType);
		
		Hbox boxKNumber = new Hbox();
		boxKNumber.setWidth("100%");
		boxKNumber.setWidths("50%, 50%");
		boxKNumber.appendChild(kNumberLabel);
		boxKNumber.appendChild(kNumberField);
		kPanel.appendChild(boxKNumber);
		
		Hbox boxKExp = new Hbox();
		boxKExp.setWidth("100%");
		boxKExp.setWidths("50%, 50%");
		boxKExp.appendChild(kExpLabel);
		boxKExp.appendChild(kExpField);
		kPanel.appendChild(boxKExp);
		
		Hbox boxKAmount = new Hbox();
		boxKAmount.setWidth("100%");
		boxKAmount.setWidths("50%, 50%");
		boxKAmount.appendChild(kAmountLabel);
		boxKAmount.appendChild(kAmountField);
		kPanel.appendChild(boxKAmount);
		
		Hbox boxKApproval = new Hbox();
		boxKApproval.setWidth("100%");
		boxKApproval.setWidths("50%, 50%");
		boxKApproval.appendChild(kApprovalLabel);
		boxKApproval.appendChild(kApprovalField);
		kPanel.appendChild(boxKApproval);
		
		kPanel.appendChild(kStatus);
		kPanel.appendChild(kOnline);
		
		// Direct Debit/Credit
		
		tPanel.setWidth("100%");
		
		tAccountLabel.setValue(Msg.translate(Env.getCtx(), "C_BP_BankAccount_ID"));
		//tRoutingField.setColumns(8);
		//tNumberField.setColumns(10);
		tRoutingText.setValue(Msg.translate(Env.getCtx(), "RoutingNo"));
		tNumberText.setValue(Msg.translate(Env.getCtx(), "AccountNo"));
		tOnline.setLabel(Msg.getMsg(Env.getCtx(), "Online"));
		tOnline.addEventListener(Events.ON_CLICK, this);
		tStatus.setValue(" ");
		centerPanel.appendChild(tPanel);
		
		Hbox boxTAccount = new Hbox();
		
		tAccountCombo.setRows(0);
		tAccountCombo.setMold("select");
		tAccountCombo.addEventListener(Events.ON_SELECT, this);
		
		boxTAccount.setWidth("100%");
		boxTAccount.setWidths("45%, 55%");
		boxTAccount.appendChild(tAccountLabel);
		boxTAccount.appendChild(tAccountCombo);
		tPanel.appendChild(boxTAccount);
		
		Hbox boxTRouting = new Hbox();
		boxTRouting.setWidth("100%");
		boxTRouting.setWidths("45%, 55%");
		boxTRouting.appendChild(tRoutingText);
		boxTRouting.appendChild(tRoutingField);
		tPanel.appendChild(boxTRouting);

		Hbox boxTNumber = new Hbox();
		boxTNumber.setWidth("100%");
		boxTNumber.setWidths("45%, 55%");
		boxTNumber.appendChild(tNumberText);
		boxTNumber.appendChild(tNumberField);
		tPanel.appendChild(boxTNumber);
		
		tPanel.appendChild(tStatus);
		tPanel.appendChild(tOnline);
		
		// Cheque
		
		sPanel.setWidth("100%");
		
		sBankAccountLabel.setValue(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		sAmountLabel.setValue(Msg.getMsg(Env.getCtx(), "Amount"));
		sAmountField.setText("");
		sRoutingLabel.setValue(Msg.translate(Env.getCtx(), "RoutingNo"));
		sNumberLabel.setValue(Msg.translate(Env.getCtx(), "AccountNo"));
		sCheckLabel.setValue(Msg.translate(Env.getCtx(), "CheckNo"));
		//sCheckField.setColumns(8);
		sCurrencyLabel.setValue(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		//sNumberField.setPreferredSize(new Dimension(100, 21));
		//sRoutingField.setPreferredSize(new Dimension(70, 21));
		sStatus.setValue(" ");
		sOnline.setLabel(Msg.getMsg(Env.getCtx(), "Online"));
		sOnline.addEventListener(Events.ON_CLICK, this);
		centerPanel.appendChild(sPanel);
		
		Hbox boxSBankAccount = new Hbox();
		
		sBankAccountCombo.setRows(0);
		sBankAccountCombo.setMold("select");
		sBankAccountCombo.addEventListener(Events.ON_SELECT, this);
				
		boxSBankAccount.setWidth("100%");
		boxSBankAccount.setWidths("40%, 60%");
		boxSBankAccount.appendChild(sBankAccountLabel);
		boxSBankAccount.appendChild(sBankAccountCombo);
		sPanel.appendChild(boxSBankAccount);
		
		Hbox boxSCurrency = new Hbox();
		
		sCurrencyCombo.setRows(0);
		sCurrencyCombo.setMold("select");
		sCurrencyCombo.addEventListener(Events.ON_SELECT, this);
		
		boxSCurrency.setWidth("100%");
		boxSCurrency.setWidths("40%, 60%");
		boxSCurrency.appendChild(sCurrencyLabel);
		boxSCurrency.appendChild(sCurrencyCombo);
		sPanel.appendChild(boxSCurrency);
		
		Hbox boxSAmount = new Hbox();
		boxSAmount.setWidth("100%");
		boxSAmount.setWidths("40%, 60%");
		boxSAmount.appendChild(sAmountLabel);
		boxSAmount.appendChild(sAmountField);
		sPanel.appendChild(boxSAmount);
		
		Hbox boxSRouting = new Hbox();
		boxSRouting.setWidth("100%");
		boxSRouting.setWidths("40%, 60%");
		boxSRouting.appendChild(sRoutingLabel);
		boxSRouting.appendChild(sRoutingField);
		sPanel.appendChild(boxSRouting);
		
		Hbox boxSNumber = new Hbox();
		boxSNumber.setWidth("100%");
		boxSNumber.setWidths("40%, 60%");
		boxSNumber.appendChild(sNumberLabel);
		boxSNumber.appendChild(sNumberField);
		sPanel.appendChild(boxSNumber);
		
		Hbox boxSCheck = new Hbox();
		boxSCheck.setWidth("100%");
		boxSCheck.setWidths("40%, 60%");
		boxSCheck.appendChild(sCheckLabel);
		boxSCheck.appendChild(sCheckField);
		sPanel.appendChild(boxSCheck);
		
		sPanel.appendChild(sOnline);
		sPanel.appendChild(sStatus);
		
		// Cash
		
		pPanel.setWidth("100%");
		pPanel.setWidths("40%, 60%");
		
		pTermLabel.setValue(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"));
		centerPanel.appendChild(pPanel);
		
		pTermCombo.setRows(0);
		pTermCombo.setMold("select");
		pTermCombo.addEventListener(Events.ON_SELECT, this);
		
		pPanel.appendChild(pTermLabel);
		pPanel.appendChild(pTermCombo);
		
		//
		
		bPanel.setWidth("100%");
		
		bCashBookLabel.setValue(Msg.translate(Env.getCtx(), "C_CashBook_ID"));
		bCurrencyLabel.setValue(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		bAmountLabel.setValue(Msg.getMsg(Env.getCtx(), "Amount"));
		bAmountField.setText("");
		bDateLabel.setValue(Msg.translate(Env.getCtx(), "DateAcct"));
		centerPanel.appendChild(bPanel);
		
		Hbox boxBCashBook = new Hbox();
		
		bCashBookCombo.setRows(0);
		bCashBookCombo.setMold("select");
		bCashBookCombo.addEventListener(Events.ON_SELECT, this);
		
		boxBCashBook.setWidth("100%");
		boxBCashBook.setWidths("40%, 60%");
		boxBCashBook.appendChild(bCashBookLabel);
		boxBCashBook.appendChild(bCashBookCombo);
		bPanel.appendChild(boxBCashBook);
		
		Hbox boxBCurrency = new Hbox();
		
		bCurrencyCombo.setRows(0);
		bCurrencyCombo.setMold("select");
		bCurrencyCombo.addEventListener(Events.ON_SELECT, this);
		
		boxBCurrency.setWidth("100%");
		boxBCurrency.setWidths("40%, 60%");
		boxBCurrency.appendChild(bCurrencyLabel);
		boxBCurrency.appendChild(bCurrencyCombo);
		bPanel.appendChild(boxBCurrency);
		
		Hbox boxBDate = new Hbox();
		boxBDate.setWidth("100%");
		boxBDate.setWidths("40%, 60%");
		boxBDate.appendChild(bDateLabel);
		boxBDate.appendChild(bDateField);
		bPanel.appendChild(boxBDate);
		
		Hbox boxBAmount = new Hbox();
		boxBAmount.setWidth("100%");
		boxBAmount.setWidths("40%, 60%");
		boxBAmount.appendChild(bAmountLabel);
		boxBAmount.appendChild(bAmountField);
		bPanel.appendChild(boxBAmount);
		
		Hbox boxButtons = new Hbox();
		
		btnCancel.setImage("/images/Cancel24.gif");
		btnCancel.addEventListener(Events.ON_CLICK, this);
		btnOk.setImage("/images/Ok24.gif");
		btnOk.addEventListener(Events.ON_CLICK, this);
		
		boxButtons.appendChild(btnCancel);
		boxButtons.appendChild(btnOk);
		mainPanel.appendChild(new Separator());
		mainPanel.appendChild(boxButtons);
	}

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
	
	private boolean dynInit (WButtonEditor button) throws Exception
	{
		m_DocStatus = (String)m_mTab.getValue("DocStatus");
		log.config(m_DocStatus);

		if (m_mTab.getValue("C_BPartner_ID") == null)
		{
			FDialog.error(0, this, "SaveErrorRowNotFound");
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
			FDialog.error(m_WindowNo, this, "PaymentZero");
			return false;
		}
		
		bAmountField.setText(m_Format.format(m_Amount));
		sAmountField.setText(m_Format.format(m_Amount));
		kAmountField.setText(m_Format.format(m_Amount));

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
				kStatus.setValue(m_mPayment.getR_PnRef());
				kAmountField.setText(m_Format.format(m_mPayment.getPayAmt()));

				//	if approved/paid, don't let it change
				
				kTypeCombo.setEnabled(!m_mPayment.isApproved());
				kNumberField.setEnabled(!m_mPayment.isApproved());
				kExpField.setEnabled(!m_mPayment.isApproved());
				kApprovalField.setEnabled(!m_mPayment.isApproved());
				kOnline.setEnabled(!m_mPayment.isApproved());
				kAmountField.setEnabled(!m_mPayment.isApproved());
				
				//  Check
				
				m_C_BankAccount_ID = m_mPayment.getC_BankAccount_ID();
				sRoutingField.setText(m_mPayment.getRoutingNo());
				sNumberField.setText(m_mPayment.getAccountNo());
				sCheckField.setText(m_mPayment.getCheckNo());
				sStatus.setValue(m_mPayment.getR_PnRef());
				sAmountField.setText(m_Format.format(m_mPayment.getPayAmt()));
				
				//  Transfer
				
				tRoutingField.setText(m_mPayment.getRoutingNo());
				tNumberField.setText(m_mPayment.getAccountNo());
				tStatus.setValue(m_mPayment.getR_PnRef());
			}
		}
		if (m_mPayment == null)
		{
			m_mPayment = new MPayment (Env.getCtx (), 0, null);
			m_mPayment.setAD_Org_ID(m_AD_Org_ID);
			m_mPayment.setAmount (m_C_Currency_ID, m_Amount);
		}

		//  Existing Cashbook entry
		
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
				bAmountField.setText(m_cashLine.getAmount().toString());
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
				bCurrencyCombo.appendItem(key.toString(), s_Currencies.get(key));
				sCurrencyCombo.appendItem(key.toString(), s_Currencies.get(key));
			}

			ListItem listitem = new ListItem();
			listitem.setValue(s_Currencies.get(C_Currency_ID));

			sCurrencyCombo.addEventListener(Events.ON_SELECT, this);
			sCurrencyCombo.setSelectedItem(listitem);
			
			bCurrencyCombo.addEventListener(Events.ON_SELECT, this);
			bCurrencyCombo.setSelectedItem(listitem);
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
			paymentCombo.appendItem(pp.getName(), pp);
			
			if (PaymentRule.toString().equals(m_PaymentRule))	//	to select
				vp = pp;
		}

		//	Set PaymentRule
		
		paymentCombo.addEventListener(Events.ON_SELECT, this);
		
		if (vp != null)
		{
			for (int i = 0; i < paymentCombo.getItemCount(); i++)
			{
				ListItem item = paymentCombo.getItemAtIndex(i);
				ValueNamePair v = (ValueNamePair)item.getValue();
				
				if (v == vp)
				{
					paymentCombo.setSelectedIndex(i);
					break;
				}
			}
		}
		
		Event evt = new Event(Events.ON_SELECT, paymentCombo);
		onEvent(evt);

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
				pTermCombo.appendItem(pp.getName(), pp);
				
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
		{
			for (int i = 0; i < pTermCombo.getItemCount(); i++)
			{
				ListItem item = pTermCombo.getItemAtIndex(i);
				KeyNamePair k = (KeyNamePair)item.getValue();
				
				if (k == kp)
				{
					pTermCombo.setSelectedIndex(i);
					break;
				}
			}
		}
		
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
				tAccountCombo.appendItem(pp.getName(), pp);
				//	kp = pp;
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
		{
			for (int i = 0; i < tAccountCombo.getItemCount(); i++)
			{
				ListItem item = tAccountCombo.getItemAtIndex(i);
				KeyNamePair k = (KeyNamePair)item.getValue();
				
				if (k == kp)
				{
					tAccountCombo.setSelectedIndex(i);
					break;
				}
			}
		}
		
		/**
		 *	Load Credit Cards
		 */
		
		ValueNamePair[] ccs = m_mPayment.getCreditCards();
		vp = null;
		
		for (int i = 0; i < ccs.length; i++)
		{
			kTypeCombo.appendItem(ccs[i].getName(), ccs[i]);
			
			if (ccs[i].getValue().equals(m_CCType))
				vp = ccs[i];
		}
		
		//	Set Selection
		
		if (vp != null)
		{
			for (int i = 0; i < kTypeCombo.getItemCount(); i++)
			{
				ListItem item = kTypeCombo.getItemAtIndex(i);
				ValueNamePair v = (ValueNamePair)item.getValue();
				
				if (v == vp)
				{
					kTypeCombo.setSelectedIndex(i);
					break;
				}
			}
		}
		
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
				sBankAccountCombo.appendItem(pp.getName(), pp);
				
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
		{
			for (int i = 0; i < sBankAccountCombo.getItemCount(); i++)
			{
				ListItem item = sBankAccountCombo.getItemAtIndex(i);
				KeyNamePair k = (KeyNamePair)item.getValue();
				
				if (k == kp)
				{
					sBankAccountCombo.setSelectedIndex(i);
					break;
				}
			}
		}

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
				bCashBookCombo.appendItem(pp.getName(), pp);
						
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
			for (int i = 0; i < bCashBookCombo.getItemCount(); i++)
			{
				ListItem item = bCashBookCombo.getItemAtIndex(i);
				KeyNamePair k = (KeyNamePair)item.getValue();
				
				if (k == kp)
				{
					bCashBookCombo.setSelectedIndex(i);
					break;
				}
			}
			
			if (m_C_CashBook_ID == 0)
				m_C_CashBook_ID = kp.getKey();  //  set to default to avoid 'cashbook changed' message
		}

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
	} // loadCurrencies
	
	public void onEvent(Event e) throws Exception 
	{
		log.fine( "VPayment.actionPerformed - " + e.getTarget());

		//	Finish
		
		if (e.getTarget() == btnOk)
		{
			if (checkMandatory())
			{
				saveChanges(); // cannot recover
				this.detach();
			}
		}
		else if (e.getTarget() == btnCancel)
			this.detach();

		//	Payment Method Change
		
		if (e.getTarget() == paymentCombo)
		{
			//	get selection
			
			ListItem listitem = paymentCombo.getSelectedItem();
			ValueNamePair pp = (ValueNamePair)listitem.getValue();
			
			if (pp != null)
			{
				String s = pp.getValue().toLowerCase();
		
				if (X_C_Order.PAYMENTRULE_DirectDebit.equalsIgnoreCase(s))
					s = X_C_Order.PAYMENTRULE_DirectDeposit.toLowerCase();
				
				s += "Panel";
				
				//centerLayout.show(centerPanel, s);	//	switch to panel
				
				kPanel.setVisible(false);
				tPanel.setVisible(false);
				sPanel.setVisible(false);
				pPanel.setVisible(false);
				bPanel.setVisible(false);
				
				if (s.equals("kPanel"))
					kPanel.setVisible(true);
				else if (s.equals("tPanel"))
					tPanel.setVisible(true);
				else if (s.equals("sPanel"))
					sPanel.setVisible(true);
				else if (s.equals("pPanel"))
					pPanel.setVisible(true);
				else if (s.equals("bPanel"))
					bPanel.setVisible(true);
			}
		}

		//	Check Currency change
		
		else if (e.getTarget() == sCurrencyCombo)
		{
			ListItem listitem = sCurrencyCombo.getSelectedItem();
			KeyNamePair pp = (KeyNamePair)listitem.getValue();
			BigDecimal amt = MConversionRate.convert(Env.getCtx(),
				m_Amount, m_C_Currency_ID, pp.getKey(), m_AD_Client_ID, m_AD_Org_ID);
			sAmountField.setText(m_Format.format(amt));
		}
		
		//	Cash Currency change
		
		else if (e.getTarget() == bCurrencyCombo)
		{
			ListItem listitem = bCurrencyCombo.getSelectedItem();
			KeyNamePair pp = (KeyNamePair)listitem.getValue();
			BigDecimal amt = MConversionRate.convert(Env.getCtx(),
				m_Amount, m_C_Currency_ID, pp.getKey(), m_AD_Client_ID, m_AD_Org_ID);
			bAmountField.setText(m_Format.format(amt));
		}

		//  Online
		
		else if (e.getTarget() == kOnline || e.getTarget() == sOnline )
			processOnline();
	}	//	actionPerformed

	/**************************************************************************
	 *	Save Changes
	 *	@return true, if eindow can exit
	 */
	
	private boolean saveChanges()
	{
		ListItem listitem = paymentCombo.getSelectedItem();
		ValueNamePair vp = (ValueNamePair)listitem.getValue();
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
			listitem = bCashBookCombo.getSelectedItem();
			
			KeyNamePair kp = null;
			
			if (listitem != null)
				kp = (KeyNamePair)listitem.getValue();
			
			if (kp != null)
				newC_CashBook_ID = kp.getKey();
			
			//bDateField.
			
			newDateAcct = (Timestamp)bDateField.getValue();
			
			// Get changes to cash amount
			
			m_mPayment.setAmount(m_C_Currency_ID, new BigDecimal(bAmountField.getText()));
			m_Amount = new BigDecimal(bAmountField.getText());
			
			//ADialog.info(m_WindowNo, this, "MAJJ Debug", bAmountField.getText());			
		}

		//	K (CreditCard)  Type, Number, Exp, Approval
		
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_CreditCard))
		{
			listitem = kTypeCombo.getSelectedItem();
			
			if (listitem != null)
				vp = (ValueNamePair)listitem.getValue();
			else 
				vp = null;
			
			if (vp != null)
				newCCType = vp.getValue();
		}

		//	T (Transfer)	BPartner_Bank
		
		else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit) 
			|| newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit) )
		{
			if (tAccountCombo.getSelectedItem() != null)
				tAccountCombo.getSelectedItem();
		}

		//	P (PaymentTerm)	PaymentTerm
		
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
		{
			listitem = pTermCombo.getSelectedItem();
			
			KeyNamePair kp = null;
			
			if (listitem != null)
				kp = (KeyNamePair)listitem.getValue();
			
			if (kp != null)
				newC_PaymentTerm_ID = kp.getKey();
		}

		//	S (Check)		(Currency) CheckNo, Routing
		
		else if (newPaymentRule.equals(X_C_Order.PAYMENTRULE_Check))
		{
			//	sCurrencyCombo.getSelectedItem();
			
			listitem = sBankAccountCombo.getSelectedItem();
			
			KeyNamePair kp = null;
			
			if (listitem != null)
				kp = (KeyNamePair)listitem.getValue();
			
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
						FDialog.error(m_WindowNo, this, "PaymentError", "CashNotCancelled");
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
					FDialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCancelled " + m_mPaymentOriginal.getDocumentNo());
				
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
						FDialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCancelled " + m_mPayment.getDocumentNo());
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
				FDialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
			}
			else
			{
				payAmount = new BigDecimal(bAmountField.getText());
			
				//  Changed Amount
				
				if (m_cashLine != null
					&& payAmount.compareTo(m_cashLine.getAmount()) != 0)
				{
					log.config("Changed CashBook Amount");
				
					//m_cashLine.setAmount(payAmount);
					
					m_cashLine.setAmount(new BigDecimal(bAmountField.getText()));
					
					// ADialog.info(m_WindowNo, this, "m_cashLine - Changed Amount", "Amount: "+m_cashLine.getAmount());
					
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
						FDialog.error(m_WindowNo, this, "PaymentError", "CashNotCancelled");
					
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
						FDialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
					else
					{
						MCashLine cl = new MCashLine (cash);
						
						// cl.setAmount(new BigDecimal(bAmountField.getText()));
						//ADialog.info(m_WindowNo, this, "m_cashLine - New Cashbook", "Amount: "+cl.getAmount());
						
						if (invoice != null)
							cl.setInvoice(invoice);	// overrides amount
						if (order != null)
						{
							cl.setOrder(order, null); // overrides amount
							m_needSave = true;
						}
						
						cl.setAmount(new BigDecimal(bAmountField.getText()));
						
						if (cl.save())
						{	
							log.config("CashCreated");
						
							if (invoice == null && C_Invoice_ID != 0)
							{
								invoice = new MInvoice (Env.getCtx(), C_Invoice_ID, null);	
							}
							
							if (invoice != null) 
							{
								invoice.setC_CashLine_ID(cl.getC_CashLine_ID());
								invoice.save();
							}	
							
							if (order == null && C_Order_ID != 0)
							{
								order = new MOrder (Env.getCtx(), C_Order_ID, null);
							}
							
							if (order != null) 
							{
								order.setC_CashLine_ID(cl.getC_CashLine_ID());
								order.save();
							}
							
							log.config("Update Order & Invoice with CashLine");
						}	
						else
							FDialog.error(m_WindowNo, this, "PaymentError", "CashNotCreated");
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
			
				// Get changes to credit card amount
				
				m_mPayment.setAmount(m_C_Currency_ID, new BigDecimal(kAmountField.getText()));
				m_mPayment.setPaymentProcessor();
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDeposit)
				|| newPaymentRule.equals(MOrder.PAYMENTRULE_DirectDebit))
			{
				m_mPayment.setBankACH(newC_BankAccount_ID, m_isSOTrx, newPaymentRule, 
					tRoutingField.getText(), tNumberField.getText());
				
				m_mPayment.setAmount(m_C_Currency_ID, payAmount);
			}
			else if (newPaymentRule.equals(MOrder.PAYMENTRULE_Check))
			{
				m_mPayment.setBankCheck(newC_BankAccount_ID, m_isSOTrx, sRoutingField.getText(),
					sNumberField.getText(), sCheckField.getText());
				
				// Get changes to check amount
				
				m_mPayment.setAmount(m_C_Currency_ID, new BigDecimal(sAmountField.getText()));
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
					FDialog.info(m_WindowNo, this, "PaymentCreated", m_mPayment.getDocumentNo());
				else
					FDialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
			}
			else
				log.fine("NotDraft " + m_mPayment);
		}


		/**********************
		 *	Save Values to mTab
		 */
		
		log.config("Saving changes");

		if (!newPaymentRule.equals(m_PaymentRule))
			m_mTab.setValue("PaymentRule", newPaymentRule);

		if (!newDateAcct.equals(m_DateAcct))
			m_mTab.setValue("DateAcct", newDateAcct);

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
		log.config( "WPayment.checkMandatory");

		ListItem listitem = new ListItem();
		listitem = paymentCombo.getSelectedItem();
		ValueNamePair vp = (ValueNamePair)listitem.getValue();
		String PaymentRule = vp.getValue();
		
		//  only Payment Rule
		
		if (m_onlyRule)
			return true;

		Timestamp DateAcct = m_DateAcct;
		int C_PaymentTerm_ID = m_C_PaymentTerm_ID;
		int C_CashBook_ID = m_C_CashBook_ID;
		String CCType = m_CCType;
		int C_BankAccount_ID = 0;

		/***********************
		 *	Mandatory Data Check
		 */
		
		boolean dataOK = true;
		
		//	B (Cash)		(Currency)
		
		if (PaymentRule.equals(MOrder.PAYMENTRULE_Cash))
		{
			listitem = new ListItem();
			listitem = bCashBookCombo.getSelectedItem(); 
			
			KeyNamePair kp = null;
			
			if (listitem != null)
				kp = (KeyNamePair)listitem.getValue();
			
			if (kp != null)
				C_CashBook_ID = kp.getKey();
			
			DateAcct = (Timestamp)bDateField.getValue();
		}

		//	K (CreditCard)  Type, Number, Exp, Approval

		else if (PaymentRule.equals(MOrder.PAYMENTRULE_CreditCard))
		{
			listitem = kTypeCombo.getSelectedItem();
			
			if (listitem != null)
				vp = (ValueNamePair)listitem.getValue();
			else
				vp = null;
			
			if (vp != null)
				CCType = vp.getValue();

			String error = MPaymentValidate.validateCreditCardNumber(kNumberField.getText(), CCType);
			
			if (error.length() != 0)
			{
				//kNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());

				if (error.indexOf('?') == -1)
				{
					FDialog.error(m_WindowNo, this, error);
					dataOK = false;
				}
				else    //  warning
				{
					if (!FDialog.ask(m_WindowNo, this, error))
						dataOK = false;
				}
			}
			
			error = MPaymentValidate.validateCreditCardExp(kExpField.getText());
			
			if (error.length() != 0)
			{
				//kExpField.setBackground(AdempierePLAF.getFieldBackground_Error());
				FDialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
		}

		//	T (Transfer)	BPartner_Bank
		
		else if (PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDeposit)
			|| PaymentRule.equals(X_C_Order.PAYMENTRULE_DirectDebit))
		{
			listitem = tAccountCombo.getSelectedItem();
			
			KeyNamePair bpba =  null;
			
			if (listitem != null)
				bpba = (KeyNamePair)listitem.getValue();
			
			if (bpba == null)
			{
				//tAccountCombo.setBackground(AdempierePLAF.getFieldBackground_Error());
				FDialog.error(m_WindowNo, this, "PaymentBPBankNotFound");
				dataOK = false;
			}
		}	//	Direct

		//	P (PaymentTerm)	PaymentTerm
		
		else if (PaymentRule.equals(X_C_Order.PAYMENTRULE_OnCredit))
		{
			listitem = pTermCombo.getSelectedItem();
			
			KeyNamePair kp = null;
			
			if (listitem != null)
				kp = (KeyNamePair)listitem.getValue();
			
			if (kp != null)
				C_PaymentTerm_ID = kp.getKey();
		}

		//	S (Check)		(Currency) CheckNo, Routing
		
		else if (PaymentRule.equals(MOrder.PAYMENTRULE_Check))
		{
			//	sCurrencyCombo.getSelectedItem();
			listitem = sBankAccountCombo.getSelectedItem();
			
			KeyNamePair kp = null;
			
			if (listitem != null)
				kp = (KeyNamePair)listitem.getValue();
			
			if (kp != null)
				C_BankAccount_ID = kp.getKey();
			
			String error = MPaymentValidate.validateRoutingNo(sRoutingField.getText());
			
			if (error.length() != 0)
			{
				//sRoutingField.setBackground(AdempierePLAF.getFieldBackground_Error());
				FDialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
			
			error = MPaymentValidate.validateAccountNo(sNumberField.getText());
			
			if (error.length() != 0)
			{
				//sNumberField.setBackground(AdempierePLAF.getFieldBackground_Error());
				FDialog.error(m_WindowNo, this, error);
				dataOK = false;
			}
			
			error = MPaymentValidate.validateCheckNo(sCheckField.getText());
			
			if (error.length() != 0)
			{
				//sCheckField.setBackground(AdempierePLAF.getFieldBackground_Error());
				FDialog.error(m_WindowNo, this, error);
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
				FDialog.error(m_WindowNo, this, "PaymentNoProcessor");
				dataOK = false;
			}
		}

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

		ListItem listitem = new ListItem();
		listitem = paymentCombo.getSelectedItem();
		ValueNamePair vp = (ValueNamePair)listitem.getValue();
		String PaymentRule = vp.getValue();

		//  --  CreditCard
		
		if (PaymentRule.equals(X_C_Order.PAYMENTRULE_CreditCard))
		{
			listitem = kTypeCombo.getSelectedItem();
			vp = (ValueNamePair)listitem.getValue();
			String CCType = vp.getValue();

			m_mPayment.setCreditCard(MPayment.TRXTYPE_Sales, CCType,
				kNumberField.getText(), "", kExpField.getText());
			m_mPayment.setAmount(m_C_Currency_ID, m_Amount);
			m_mPayment.setPaymentProcessor();
			m_mPayment.setC_BPartner_ID(m_C_BPartner_ID);

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
					FDialog.info(m_WindowNo, this, "PaymentProcessed", info + "\n" + m_mPayment.getDocumentNo());
				else
					FDialog.error(m_WindowNo, this, "PaymentError", "PaymentNotCreated");
				
				saveChanges();
				//dispose();
			}
			else
			{
				FDialog.error(m_WindowNo, this, "PaymentNotProcessed", info);
			}
		}
		else
			FDialog.error(m_WindowNo, this, "PaymentNoProcessor");
	}   //  online

	/**
	 * 	Need Save record (payment with waiting order)
	 *	@return true if payment with waiting order
	 */
	
	public boolean needSave()
	{
		return m_needSave;
	}	//	needSave
}
