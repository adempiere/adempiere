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
package org.compiere.grid;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.swing.BorderFactory;

import org.adempiere.controller.PaymentFormController;
import org.adempiere.controller.ed.CPaymentEditor;
import org.compiere.apps.ADialog;
import org.compiere.apps.AEnv;
import org.compiere.apps.ConfirmPanel;
import org.compiere.grid.ed.VButton;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.GridTab;
import org.compiere.swing.CButton;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CDialog;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CTextField;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;

/**
 *	Display (and process) Payment Options.
 *  See the Payment Form Controller for details on the operation
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: VPayment.java,v 1.2 2006/07/30 00:51:28 jjanke Exp $
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1763488 ] Error on cash payment
 * 				<li>BF [ 1789949 ] VPayment: is displaying just "CashNotCreated"
 * @author Michael Judd, Akuna Ltd
 * 				<li>FR [ 2803341 ] Deprecate Cash Journal
 * @author Michael McKay, mckayERP@gmail.com
 * 		<li><a href=https://github.com/adempiere/adempiere/issues/2347">#2347</a>Reduce duplicated code
 */
public class VPayment extends CDialog
	implements ActionListener, CPaymentEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7931457502030396154L;

	private int windowNo;

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
		windowNo = WindowNo;
		controller = new PaymentFormController(this, WindowNo, mTab, button.getValues());

		try
		{
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

	PaymentFormController 		controller;

	//
	private boolean 			m_initOK = false;
	
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
	private GridBagLayout bPanelLayout = new GridBagLayout();
	private VDate bDateField = new VDate("DateAcct", false, false, true, DisplayType.Date, "DateAcct");
	private CLabel bCurrencyLabel = new CLabel();
	private CComboBox bCurrencyCombo = new CComboBox();
	private CLabel bAmountLabel = new CLabel();
	private VNumber bAmountField = new VNumber();
	private CPanel kPanel = new CPanel();
	private GridBagLayout kLayout = new GridBagLayout();
	private CLabel kTypeLabel = new CLabel();
	private CComboBox kTypeCombo = new CComboBox();
	private CLabel kNumberLabel = new CLabel();
	private CTextField kNumberField = new CTextField();
	private CLabel kNameLabel = new CLabel();
	private CTextField kNameField = new CTextField();
	private CLabel kExpLabel = new CLabel();
	private CTextField kExpField = new CTextField();
	private CLabel kApprovalLabel = new CLabel();
	private CTextField kApprovalField = new CTextField();
	private CLabel kAmountLabel = new CLabel();
	private VNumber kAmountField = new VNumber();
	private CPanel tPanel = new CPanel();
	private CLabel tAccountLabel = new CLabel();
	private CComboBox tAccountCombo = new CComboBox();
	private CLabel tAmountLabel = new CLabel();
	private VNumber tAmountField = new VNumber();
	private CPanel sPanel = new CPanel();
	private GridBagLayout sPanelLayout = new GridBagLayout();
	private CLabel sAccountNumberLabel = new CLabel();
	private CTextField sAccountNumberField = new CTextField();
	private CLabel sRoutingLabel = new CLabel();
	private CTextField sRoutingField = new CTextField();
	private CLabel sCurrencyLabel = new CLabel();
	private CComboBox sCurrencyCombo = new CComboBox();
	private CPanel pPanel = new CPanel();
	private CLabel pTermLabel = new CLabel();
	private CComboBox pTermCombo = new CComboBox();
	private CLabel sAmountLabel = new CLabel();
	private VNumber sAmountField = new VNumber();

	private CLabel bDateLabel = new CLabel();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private CTextField sCheckNumberField = new CTextField();
	private CLabel sCheckNumberLabel = new CLabel();
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
	private CLabel tStatus = new CLabel();
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
		kNumberField.setPreferredSize(new Dimension(160, 21));
		kNameField.setPreferredSize(new Dimension(160, 21));
		kExpField.setPreferredSize(new Dimension(40, 21));
		kApprovalField.setPreferredSize(new Dimension(120, 21));
		kTypeLabel.setText(Msg.translate(Env.getCtx(), "CreditCardType"));
		kNumberLabel.setText(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		kNameLabel.setText(Msg.translate(Env.getCtx(),"Name"));
		kExpLabel.setText(Msg.getMsg(Env.getCtx(), "Expires"));
		kApprovalLabel.setText(Msg.translate(Env.getCtx(), "VoiceAuthCode"));
		kAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		kAmountField.setDisplayType(DisplayType.Amount);
		kOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
		kOnline.addActionListener(this);
		kStatus.setText(" ");
		centerPanel.add(kPanel, "kPanel");
		centerLayout.addLayoutComponent(kPanel, "kPanel");
		kPanel.add(kTypeLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kTypeCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kNumberLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kNumberField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kNameLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
			kPanel.add(kNameField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kExpLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		kPanel.add(kExpField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		kPanel.add(kAmountLabel,   new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		kPanel.add(kAmountField,     new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		kPanel.add(kApprovalLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		kPanel.add(kApprovalField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		kPanel.add(kStatus, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		kPanel.add(kOnline, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//	DircetDebit/Credit
		tPanel.setLayout(tPanelLayout);
		tAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BP_BankAccount_ID"));
		tAmountLabel.setText(Msg.translate(Env.getCtx(), "Amount"));
		tAmountField.setDisplayType(DisplayType.Amount);
		tOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
		tOnline.addActionListener(this);
		tStatus.setText(" ");
		centerPanel.add(tPanel, "tPanel");
		centerLayout.addLayoutComponent(tPanel, "tPanel");
		tPanel.add(tAccountLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 0), 0, 0));
		tPanel.add(tAccountCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		tPanel.add(tAmountLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
		tPanel.add(tAmountField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
		tPanel.add(tStatus, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		tPanel.add(tOnline, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0
			,GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		// Cheque
		sPanel.setLayout(sPanelLayout);
		sBankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		sAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		sAmountField.setDisplayType(DisplayType.Amount);
		sRoutingLabel.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		sAccountNumberLabel.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		sCheckNumberLabel.setText(Msg.translate(Env.getCtx(), "CheckNo"));
		sCheckNumberField.setColumns(8);
		sCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		sAccountNumberField.setPreferredSize(new Dimension(100, 21));
		sRoutingField.setPreferredSize(new Dimension(70, 21));
		sStatus.setText(" ");
		sOnline.setText(Msg.getMsg(Env.getCtx(), "Online"));
		sOnline.addActionListener(this);
		centerPanel.add(sPanel, "sPanel");
		centerLayout.addLayoutComponent(sPanel, "sPanel");
		sPanel.add(sBankAccountLabel,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		sPanel.add(sBankAccountCombo,    new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
		sPanel.add(sCurrencyLabel,   new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sCurrencyCombo,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		sPanel.add(sAmountLabel,   new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 5, 0), 0, 0));
		sPanel.add(sAmountField,     new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 5, 5), 0, 0));
		sPanel.add(sRoutingLabel,   new GridBagConstraints(0, 3, 1, 2, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 2, 0), 0, 0));
		sPanel.add(sRoutingField,    new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		sPanel.add(sAccountNumberLabel,   new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sAccountNumberField,    new GridBagConstraints(1, 5, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 5, 2, 0), 0, 0));
		sPanel.add(sCheckNumberLabel,   new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		sPanel.add(sCheckNumberField,    new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		sPanel.add(sOnline,      new GridBagConstraints(3, 6, 1, 1, 0.0, 0.0
				,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		sPanel.add(sStatus,    new GridBagConstraints(0, 7, 3, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		
		// Payment Term
		pPanel.setLayout(pPanelLayout);
		pTermLabel.setText(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"));
		centerPanel.add(pPanel, "pPanel");
		centerLayout.addLayoutComponent(pPanel, "pPanel");
		pPanel.add(pTermLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 5, 2, 0), 0, 0));
		pPanel.add(pTermCombo, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		
		
		// Cash
		bCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		bPanel.setLayout(bPanelLayout);
		bAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		bAmountField.setDisplayType(DisplayType.Amount);
		bDateLabel.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		centerLayout.addLayoutComponent(bPanel, "bPanel");
		centerPanel.add(bPanel, "bPanel");
		
		bCashBookLabel.setText(Msg.translate(Env.getCtx(), PaymentFormController.MSG_CashJournal));
		bCashBookCombo.setToolTipText(Msg.translate(Env.getCtx(), PaymentFormController.MSG_CashJournalTip));
		bPanel.add(bCashBookLabel,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		bPanel.add(bCashBookCombo,    new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));

		
		bPanel.add(bCurrencyLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(2, 0, 2, 0), 0, 0));
		bPanel.add(bCurrencyCombo,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 5, 2, 5), 0, 0));
		bPanel.add(bDateLabel,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 2, 0), 0, 0));
		bPanel.add(bDateField,  new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0
			,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 2, 5), 0, 0));
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
	@SuppressWarnings("unchecked")
	private boolean dynInit (VButton button) throws Exception
	{
		
		if (!controller.init() && !controller.getErrorMsg().isEmpty())
		{
			ADialog.error(0, this, controller.getErrorMsg());
			return false;
		}

		centerPanel.setVisible(!controller.isOnlyChangePaymentRule());
		
		BigDecimal amount = controller.getPaymentAmount();
		bAmountField.setValue(amount);
		sAmountField.setValue(amount);
		kAmountField.setValue(amount);
		tAmountField.setValue(amount);
				
		//	For payments, use today's date
		bDateField.setValue(controller.getDate());

		if(controller.isEMUCurrency())
		{

			Enumeration<Integer> en = controller.getCurrencies().keys();
			while (en.hasMoreElements())
			{
				Object key = en.nextElement();
				bCurrencyCombo.addItem(controller.getCurrencies().get(key));
				sCurrencyCombo.addItem(controller.getCurrencies().get(key));
			}
			sCurrencyCombo.addActionListener(this);
			sCurrencyCombo.setSelectedItem(controller.getCurrentCurrency());
			bCurrencyCombo.addActionListener(this);
			bCurrencyCombo.setSelectedItem(controller.getCurrentCurrency());

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
		for (ValueNamePair paymentRule : controller.getPaymentRules())
		{			
			paymentCombo.addItem(paymentRule);
		}

		/**
		 * 	Load Payment Terms
		 */
		for (KeyNamePair paymentTerm : controller.getPaymentTerms())
		{			
				pTermCombo.addItem(paymentTerm);
		}
		//	Set Selection
		if (controller.getSelectedPaymentTerm() != null)
			pTermCombo.setSelectedItem(controller.getSelectedPaymentTerm());
		

		/**
		 * 	Load BP Accounts
		 */
		for (KeyNamePair account : controller.getBPAccounts())
		{		
			tAccountCombo.addItem(account);
		}

		/**
		 *	Load Credit Cards
		 */
		for (ValueNamePair card : controller.getCreditCards())
		{
			
			kTypeCombo.addItem(card);
		}
		//	Set Selection
		if (controller.getSelecteCreditCard() != null)
			kTypeCombo.setSelectedItem(controller.getSelecteCreditCard());

		/**
		 *  Load Bank Accounts
		 */
		for (KeyNamePair account : controller.getBankAccounts())
		{
			sBankAccountCombo.addItem(account);
		}
		//	Set Selection
		if (controller.getSelectedBankAccount() != null)
		{
			sBankAccountCombo.setSelectedItem(controller.getSelectedBankAccount());
		}


		/**
		 *  Load Cash Books
		 */
		for (KeyNamePair cashBook : controller.getCashBooks())
		{
			
			bCashBookCombo.addItem(cashBook);
			
		}
		
		if (controller.getSelectedCashBook() != null)
		{
			
			bCashBookCombo.setSelectedItem(controller.getSelectedCashBook());
			
		}

		//	Set PaymentRule do this last as the action will trigger other events
		paymentCombo.addActionListener(this);
		if (controller.getSelectedPaymentRule() != null) {
			paymentCombo.setSelectedItem(controller.getSelectedPaymentRule());
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




	/**************************************************************************
	 *	Action Listener
	 *  @param e event
	 */
	public void actionPerformed(ActionEvent e)
	{
		log.fine( "VPayment.actionPerformed - " + e.getActionCommand());

		//	Finish
		if (e.getActionCommand().equals(ConfirmPanel.A_OK))
		{
			if (controller.isOnlyChangePaymentRule() 
				|| ADialog.ask(windowNo, this, PaymentFormController.MSG_PaymentCreateConfirmation))
			{
				if (saveChanges())
				{
					dispose ();
				}
			}
		}
		else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL))
			dispose();

		//	Payment Method Change
		else if (e.getSource() == paymentCombo)
		{
			onPaymentComboSelection();
		}

		//	Check Currency change
		else if (e.getSource() == sCurrencyCombo)
		{
			KeyNamePair pp = (KeyNamePair) sCurrencyCombo.getSelectedItem();
			sAmountField.setValue(controller.getConvertedAmount(pp.getKey()));
		}
		//	Cash Currency change
		else if (e.getSource() == bCurrencyCombo)
		{
			KeyNamePair pp = (KeyNamePair)bCurrencyCombo.getSelectedItem();
			bAmountField.setValue(controller.getConvertedAmount(pp.getKey()));
		}

		//  Online
		else if (e.getSource() == kOnline || e.getSource() == sOnline || e.getSource() == tOnline)
		{
			processOnline();
			if (controller.getErrorMsg().isEmpty())
			{
				dispose();
			}
		}
	}	//	actionPerformed


	private void onPaymentComboSelection() {
		//	get selection
		ValueNamePair pp = (ValueNamePair)paymentCombo.getSelectedItem();
		if (pp != null)
		{
			
			bAmountField.setValue(controller.getPaymentAmount());
			sAmountField.setValue(controller.getPaymentAmount());
			kAmountField.setValue(controller.getPaymentAmount());
			centerLayout.show(centerPanel, controller.whichPanel(pp));	//	switch to panel
			controller.checkMandatory();
			
		}
	}


	/**************************************************************************
	 *	Save Changes
	 *	@return true, if Window can exit
	 */
	private boolean saveChanges() {
		
		if (!controller.saveChanges())
		{
			ADialog.error(windowNo, this, PaymentFormController.MSG_PaymentError, controller.getErrorMsg());			
			return false;
		}
		
		// Check the message in case
		if (controller.getErrorMsg() != null && !controller.getErrorMsg().isEmpty())
		{
			ADialog.error(windowNo, this, PaymentFormController.MSG_PaymentError, controller.getErrorMsg());
		}
		
		if (controller.getPaymentDocNumber() != null && !controller.getPaymentDocNumber().isEmpty())
		{
			ADialog.info(windowNo, this, PaymentFormController.MSG_PaymentCreated, controller.getPaymentDocNumber());
		}
		return true;

	} // saveChanges
	
	
	/**************************************************************************
	 *  Process Online (sales only) - if approved - exit
	 */
	private void processOnline()
	{
		log.config("");
		
		if (!controller.processOnline())
		{
			ADialog.error(windowNo, this, PaymentFormController.MSG_PaymentError, controller.getErrorMsg());
		}
		else
		{
			String info = controller.getOnlineInfo();
			ADialog.info(windowNo, this, PaymentFormController.MSG_PaymentProcessed, info + "\n" + controller.getPaymentDocNumber());
			dispose();
		}
	}   //  online

	/**
	 * 	Need Save record (payment with waiting order)
	 *	@return true if payment with waiting order
	 */
	public boolean needSave()
	{
		return m_needSave;
	}	//	needSave
	
	public String getPaymentRule() {
		return ((ValueNamePair) paymentCombo.getSelectedItem()).getValue();
	}
	
	public int getBankAccount(String paymentRule) {

		KeyNamePair kp = null;
		
		if (paymentRule.equalsIgnoreCase("S"))
		{
			
			kp = (KeyNamePair) sBankAccountCombo.getSelectedItem(); 
		}
		
		if (kp != null)
			return kp.getKey();
		else
			return 0;
			
	}
	
	public int getCashBook(String paymentRule) {
		
		KeyNamePair kp = null;
		
		if (paymentRule.equalsIgnoreCase("B")) 
		{
			
			kp = (KeyNamePair) bCashBookCombo.getSelectedItem();
			
		}
		
		if (kp != null)
			return kp.getKey();
		else
			return 0;
		
	}
	
	public Timestamp getDateAcct(String paymentRule) {
	
		// There is only one date
		if (paymentRule.equalsIgnoreCase("B")) 
		{
			
			return (Timestamp) bDateField.getValue();
			
		}
		
		return null;
	}
	
	public BigDecimal getPaymentAmount(String paymentRule) {

		BigDecimal amount = null;
		
		if (paymentRule.equalsIgnoreCase("B")) 
		{
			
			amount = (BigDecimal) bAmountField.getValue();
			
		}
		else if (paymentRule.equalsIgnoreCase("S"))
		{
			
			amount = (BigDecimal) sAmountField.getValue();
			
		}
		else if (paymentRule.equalsIgnoreCase("K"))
		{
			
			amount = (BigDecimal) kAmountField.getValue();
			
		}
		else if (paymentRule.equalsIgnoreCase("T") || paymentRule.equalsIgnoreCase("D"))
		{
			
			return (BigDecimal) tAmountField.getValue();
		}

		if (amount != null)
			return amount;
		else
			return Env.ZERO;

	}
	
	public String getCreditCardType(String paymentRule) {
		
		ValueNamePair vp = null;
		
		if (paymentRule.equalsIgnoreCase("K"))
		{
			vp = (ValueNamePair) kTypeCombo.getSelectedItem();
		}

		if (vp != null)
			return vp.getValue();
		else
			return "";

	}

	public int getBPBankAccount(String paymentRule) {
		
		KeyNamePair kp = null;
		
		if (paymentRule.equalsIgnoreCase("T") || paymentRule.equalsIgnoreCase("D")) 
		{
			
			kp = (KeyNamePair) tAccountCombo.getSelectedItem();
			
		}
		
		if (kp != null)
			return kp.getKey();
		else
			return 0;

	}
	
	public int getPaymentTerm(String paymentRule) {
		
		KeyNamePair kp = null;
		
		if (paymentRule.equalsIgnoreCase("P")) 
		{
			
			kp = (KeyNamePair) pTermCombo.getSelectedItem();
			
		}
		
		if (kp != null)
			return kp.getKey();
		else
			return 0;

	}


	@Override
	public String getCreditCardNumber(String paymentRule) {
				
		if (paymentRule.equalsIgnoreCase("K"))
		{
			return kNumberField.getText();
		}
		else
			return "";

	}


	@Override
	public String getCreditCardExpiry(String paymentRule) {
	
		if (paymentRule.equalsIgnoreCase("K"))
		{
			return kExpField.getText();
		}
		else
			return "";

	}

	@Override
	public String getCreditCardName(String paymentRule) {
		
		if (paymentRule.equalsIgnoreCase("K"))
		{
			return kNameField.getText();
		}
		else
			return "";
	}


	@Override
	public String getCheckAccountNumber(String paymentRule) {
		
		if (paymentRule.equalsIgnoreCase("S"))
		{
			return sAccountNumberField.getText();
		}
		else
			return "";
		
	}


	@Override
	public String getCheckRoutingNumber(String paymentRule) {
		
		if (paymentRule.equalsIgnoreCase("S"))
		{
			return sRoutingField.getText();
		}
		else
			return "";
		
	}


	@Override
	public String getCheckNumber(String paymentRule) {
		
		if (paymentRule.equalsIgnoreCase("S"))
		{
			return sCheckNumberField.getText();
		}
		else
			return "";
		
	}


	@Override
	public void setMandatory(String field, boolean mandatory) {
		
		if (field.equals(FIELD_payment))
			paymentCombo.setMandatory(mandatory);
		if (field.equals(FIELD_kType))
			kTypeCombo.setMandatory(mandatory);	
		if (field.equals(FIELD_kNumber))
			kNumberField.setMandatory(mandatory);
		if (field.equals(FIELD_kName))
			kNameField.setMandatory(mandatory);
		if (field.equals(FIELD_kExp))
			kExpField.setMandatory(mandatory);
		if (field.equals(FIELD_kApproval))
			kApprovalField.setMandatory(mandatory);
		if (field.equals(FIELD_kAmount))
			kAmountField.setMandatory(mandatory);
		if (field.equals(FIELD_tAccount))
			tAccountCombo.setMandatory(mandatory);
		if (field.equals(FIELD_sCheckNumber))
			sCheckNumberField.setMandatory(mandatory);
		if (field.equals(FIELD_sAccountNumber))
			sAccountNumberField.setMandatory(mandatory);
		if (field.equals(FIELD_sRouting))
			sRoutingField.setMandatory(mandatory);
		if (field.equals(FIELD_sCurrency))
			sCurrencyCombo.setMandatory(mandatory);
		if (field.equals(FIELD_bCurrency))
			bCurrencyCombo.setMandatory(mandatory);
		if (field.equals(FIELD_pTerm))
			pTermCombo.setMandatory(mandatory);
		if (field.equals(FIELD_bAmount))
			bAmountField.setMandatory(mandatory);
		if (field.equals(FIELD_sAmount))
			sAmountField.setMandatory(mandatory);
		if (field.equals(FIELD_bDate))
			bDateField.setMandatory(mandatory);
		if (field.equals(FIELD_sCheck))
			sCheckNumberField.setMandatory(mandatory);
		if (field.equals(FIELD_sBankAccount))
			sBankAccountCombo.setMandatory(mandatory);
		if (field.equals(FIELD_bCashBook))
			bCashBookCombo.setMandatory(mandatory);
		
	}


	@Override
	public void setError(String field, boolean error) {
		
		if (field.equals(FIELD_payment))
			paymentCombo.setBackground(error);
		if (field.equals(FIELD_kType))
			kTypeCombo.setBackground(error);	
		if (field.equals(FIELD_kNumber))
			kNumberField.setBackground(error);
		if (field.equals(FIELD_kName))
			kNameField.setBackground(error);
		if (field.equals(FIELD_kExp))
			kExpField.setBackground(error);
		if (field.equals(FIELD_kApproval))
			kApprovalField.setBackground(error);
		if (field.equals(FIELD_kAmount))
			kAmountField.setBackground(error);
		if (field.equals(FIELD_tAccount))
			tAccountCombo.setBackground(error);
		if (field.equals(FIELD_sCheckNumber))
			sCheckNumberField.setBackground(error);
		if (field.equals(FIELD_sAccountNumber))
			sAccountNumberField.setBackground(error);
		if (field.equals(FIELD_sRouting))
			sRoutingField.setBackground(error);
		if (field.equals(FIELD_sCurrency))
			sCurrencyCombo.setBackground(error);
		if (field.equals(FIELD_bCurrency))
			bCurrencyCombo.setBackground(error);
		if (field.equals(FIELD_pTerm))
			pTermCombo.setBackground(error);
		if (field.equals(FIELD_bAmount))
			bAmountField.setBackground(error);
		if (field.equals(FIELD_sAmount))
			sAmountField.setBackground(error);
		if (field.equals(FIELD_bDate))
			bDateField.setBackground(error);
		if (field.equals(FIELD_sCheck))
			sCheckNumberField.setBackground(error);
		if (field.equals(FIELD_sBankAccount))
			sBankAccountCombo.setBackground(error);
		if (field.equals(FIELD_bCashBook))
			bCashBookCombo.setBackground(error);
		
	}

}	//	VPayment