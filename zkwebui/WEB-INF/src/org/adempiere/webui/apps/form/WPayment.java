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
package org.adempiere.webui.apps.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.controller.PaymentFormController;
import org.adempiere.controller.ed.CPaymentEditor;
import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.BusyDialog;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.compiere.util.ValueNamePair;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.au.out.AuEcho;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Space;

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
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1763488 ] Error on cash payment
 * 				<li>BF [ 1789949 ] VPayment: is displaying just "CashNotCreated"
 */
public class WPayment extends Window
	implements EventListener, CPaymentEditor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3550713503274155601L;

	private int windowNo;


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
		windowNo = WindowNo;
		controller = new PaymentFormController(this, WindowNo, mTab, button.getValues());
		this.setTitle(Msg.getMsg(Env.getCtx(), "Payment"));
		this.setAttribute("mode", "modal");
		try
		{
			zkInit();
			m_initOK = dynInit(button);     //  Null Pointer if order/invoice not saved yet
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "VPayment", ex);
			m_initOK = false;
		}
		//
//		this.setHeight("400px");
//		this.setWidth("500px");
		this.setBorder("normal");
	}	//	VPayment

	PaymentFormController 		controller;

	//
	private boolean 			m_initOK = false;
	
	private boolean				m_needSave = false;
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WPayment.class);

	//
	private Panel mainPanel = new Panel();
	private Borderlayout mainLayout = new Borderlayout();
	private Panel northPanel = new Panel();
	private Panel centerPanel = new Panel();
	private Listbox paymentCombo = ListboxFactory.newDropdownListbox();
	private Label paymentLabel = new Label();
	private List<Panel> centerLayout = new ArrayList<Panel>();
	private Panel bPanel = new Panel();
	private Panel kPanel = new Panel();
	private Grid northLayout = GridFactory.newGridLayout();
	private Grid kLayout = GridFactory.newGridLayout();
	private Label kTypeLabel = new Label();
	private Listbox kTypeCombo = ListboxFactory.newDropdownListbox();
	private Label kNameLabel = new Label();
	private Textbox kNameField = new Textbox();
	private Label kNumberLabel = new Label();
	private Textbox kNumberField = new Textbox();
	private Label kExpLabel = new Label();
	private Textbox kExpField = new Textbox();
	private Label kApprovalLabel = new Label();
	private Textbox kApprovalField = new Textbox();
	private Label kAmountLabel = new Label();
	private WNumberEditor kAmountField = new WNumberEditor();
	private Panel tPanel = new Panel();
	private Label tAccountLabel = new Label();
	private Listbox tAccountCombo = ListboxFactory.newDropdownListbox();
	private Panel sPanel = new Panel();
	private Grid sPanelLayout = GridFactory.newGridLayout();
	private Label sAccountNumberLabel = new Label();
	private Textbox sAccountNumberField = new Textbox();
	private Label sRoutingLabel = new Label();
	private Textbox sRoutingField = new Textbox();
	private Label sCurrencyLabel = new Label();
	private Listbox sCurrencyCombo = ListboxFactory.newDropdownListbox();
	private Label bCurrencyLabel = new Label();
	private Listbox bCurrencyCombo = ListboxFactory.newDropdownListbox();
	private Panel pPanel = new Panel();
	private Label pTermLabel = new Label();
	private Listbox pTermCombo = ListboxFactory.newDropdownListbox();
	private Grid bPanelLayout = GridFactory.newGridLayout();
	private Label bAmountLabel = new Label();
	private WNumberEditor bAmountField = new WNumberEditor();
	private Label sAmountLabel = new Label();
	private WNumberEditor sAmountField = new WNumberEditor();
	private WDateEditor bDateField;
	private Label bDateLabel = new Label();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Textbox sCheckNumberField = new Textbox();
	private Label sCheckNumberLabel = new Label();
	private Button kOnline = new Button();
	private Button sOnline = new Button();
	private Listbox sBankAccountCombo = ListboxFactory.newDropdownListbox();
	private Label sBankAccountLabel = new Label();
	private Grid pPanelLayout = GridFactory.newGridLayout();
	private Label bCashBookLabel = new Label();
	private Listbox bCashBookCombo = ListboxFactory.newDropdownListbox();
	private Grid tPanelLayout = GridFactory.newGridLayout();
	private Button tOnline = new Button();
	private Label kStatus = new Label();
	private Label tStatus = new Label();
	private Label tAmountLabel = new Label();
	private WNumberEditor tAmountField = new WNumberEditor();
	private Label sStatus = new Label();

	private boolean m_isLocked = false;
	private BusyDialog progressWindow;

	private Keylistener keyListener;
	
	private static final int KEYBOARD_KEY_RETURN = 13;
	

	/**
	 *	Static Init
	 *  @throws Exception
	 */
	private void zkInit() throws Exception
	{
		
		this.appendChild(mainPanel);
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		Center center = new Center();
		mainLayout.appendChild(center);
		center.appendChild(centerPanel);
		//
		paymentLabel.setText(Msg.translate(Env.getCtx(), "PaymentRule"));
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		northPanel.appendChild(northLayout);
		Rows rows = northLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(paymentLabel.rightAlign());
		row.appendChild(paymentCombo);
		row.appendChild(new Space());
		row.appendChild(new Space());
		//
		// Get the display format for amounts
		bAmountField = new WNumberEditor("Amount", true, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "Amount"));
		kAmountField = new WNumberEditor("Amount", true, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "Amount"));
		sAmountField = new WNumberEditor("Amount", true, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "Amount"));
		tAmountField = new WNumberEditor("Amount", true, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "Amount"));
		
		//      CreditCard
		kPanel.appendChild(kLayout);
		kNumberField.setWidth("160pt");
		kExpField.setWidth("40pt");
		kApprovalField.setWidth("120pt");
		kTypeLabel.setText(Msg.translate(Env.getCtx(), "CreditCardType"));
		kNameLabel.setText(Util.cleanAmp(Msg.translate(Env.getCtx(), "Name")));
		kNumberLabel.setText(Msg.translate(Env.getCtx(), "CreditCardNumber"));
		kExpLabel.setText(Msg.getMsg(Env.getCtx(), "Expires"));
		kApprovalLabel.setText(Msg.translate(Env.getCtx(), "VoiceAuthCode"));
		kAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		kOnline.setLabel(Msg.getMsg(Env.getCtx(), "Online"));  // Check online processing not enabled
		LayoutUtils.addSclass("action-text-button", kOnline);
		kOnline.addActionListener(this);
		kStatus.setText(" ");
		kPanel.setId("kPanel");
		centerPanel.appendChild(kPanel);
		centerLayout.add(kPanel);
		
		rows = kLayout.newRows();
		row = rows.newRow();
		row.appendChild(kTypeLabel.rightAlign());
		row.appendChild(kTypeCombo);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(kNameLabel.rightAlign());
		row.appendChild(kNameField);
		row.appendChild(new Space());
		row.appendChild(new Space());

		row = rows.newRow();
		row.appendChild(kNumberLabel.rightAlign());
		row.appendChild(kNumberField);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(kExpLabel.rightAlign());
		row.appendChild(kExpField);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(kAmountLabel.rightAlign());
		row.appendChild(kAmountField.getComponent());
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(kApprovalLabel.rightAlign());
		row.appendChild(kApprovalField);
		row.appendChild(new Space());
		row.appendChild(kOnline);   // check online processing not enabled
		
		row = rows.newRow();
		row.setSpans("3,1");
		row.appendChild(kStatus);
		row.appendChild(new Space());
		
		//	DircetDebit/Credit
		tPanel.appendChild(tPanelLayout);
		tAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BP_BankAccount_ID"));
		tAmountLabel.setText(Msg.translate(Env.getCtx(), "Amount"));
		tOnline.setLabel(Msg.getMsg(Env.getCtx(), "Online"));
		LayoutUtils.addSclass("action-text-button", tOnline);
		tStatus.setText(" ");
		tPanel.setId("tPanel");
		centerPanel.appendChild(tPanel);
		centerLayout.add(tPanel);
		
		rows = tPanelLayout.newRows();
		row = rows.newRow();
		row.appendChild(tAccountLabel.rightAlign());
		row.appendChild(tAccountCombo);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(tAmountLabel.rightAlign());
		row.appendChild(tAmountField.getComponent());
		row.appendChild(new Space());
		row.appendChild(tOnline);
				
		row = rows.newRow();
		row.setSpans("3,1");
		row.appendChild(tStatus);
		row.appendChild(new Space());
						
		// Cheque
		sPanel.appendChild(sPanelLayout);
		sBankAccountLabel.setText(Msg.translate(Env.getCtx(), "C_BankAccount_ID"));
		sAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
		sRoutingLabel.setText(Msg.translate(Env.getCtx(), "RoutingNo"));
		sAccountNumberLabel.setText(Msg.translate(Env.getCtx(), "AccountNo"));
		sCheckNumberLabel.setText(Msg.translate(Env.getCtx(), "CheckNo"));
		sCheckNumberField.setCols(8);
		sCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		sAccountNumberField.setWidth("100pt");
		sRoutingField.setWidth("70pt");
		sStatus.setText(" ");
		sOnline.setLabel(Msg.getMsg(Env.getCtx(), "Online"));
		sOnline.addActionListener(this);
		LayoutUtils.addSclass("action-text-button", sOnline);
		sPanel.setId("sPanel");
		centerPanel.appendChild(sPanel);
		centerLayout.add(sPanel);
		
		rows = sPanelLayout.newRows();
		row = rows.newRow();
		row.appendChild(sBankAccountLabel.rightAlign());
		row.appendChild(sBankAccountCombo);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		if (controller.isEMUCurrency())
		{
			row = rows.newRow();
			row.appendChild(sCurrencyLabel.rightAlign());
			row.appendChild(sCurrencyCombo);
			row.appendChild(new Space());
			row.appendChild(new Space());
		}
		
		row = rows.newRow();
		row.appendChild(sAmountLabel.rightAlign());
		row.appendChild(sAmountField.getComponent());
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(sRoutingLabel.rightAlign());
		row.appendChild(sRoutingField);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(sAccountNumberLabel.rightAlign());
		row.appendChild(sAccountNumberField);
		row.appendChild(new Space());
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.appendChild(sCheckNumberLabel.rightAlign());
		row.appendChild(sCheckNumberField);
		row.appendChild(new Space());
		row.appendChild(sOnline);
		
		row = rows.newRow();
		row.setSpans("3,1");
		row.appendChild(sStatus);
		row.appendChild(new Space());
		
		// Payment Term
		pPanel.appendChild(pPanelLayout);
		pTermLabel.setText(Msg.translate(Env.getCtx(), "C_PaymentTerm_ID"));
		pPanel.setId("pPanel");
		centerPanel.appendChild(pPanel);
		centerLayout.add(pPanel);
		
		rows = pPanelLayout.newRows();
		row = rows.newRow();
		row.appendChild(pTermLabel.rightAlign());
		row.appendChild(pTermCombo);
		
		// Cash
		bCashBookLabel.setText(Msg.translate(Env.getCtx(), PaymentFormController.MSG_CashJournal));
		bCashBookLabel.setTooltip(Msg.translate(Env.getCtx(), PaymentFormController.MSG_CashJournalTip));
		bCurrencyLabel.setText(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		bPanel.appendChild(bPanelLayout);
		bAmountLabel.setText(Msg.getMsg(Env.getCtx(), "Amount"));
//		bAmountField.getComponent().setFormat(format);
		bDateLabel.setText(Msg.translate(Env.getCtx(), "DateAcct"));
		bDateField = new WDateEditor("DateAcct", false, false, true, "DateAcct");
		bPanel.setId("bPanel");
		centerPanel.appendChild(bPanel);
		centerLayout.add(bPanel);
		
		rows = bPanelLayout.newRows();
		row = rows.newRow();
		row.appendChild(bCashBookLabel.rightAlign());
		row.appendChild(bCashBookCombo);
		
		row = rows.newRow();
		row.appendChild(bCurrencyLabel.rightAlign());
		row.appendChild(bCurrencyCombo);
		
		row = rows.newRow();
		row.appendChild(bDateLabel.rightAlign());
		row.appendChild(bDateField.getComponent());
		
		row = rows.newRow();
		row.appendChild(bAmountLabel.rightAlign());
		row.appendChild(bAmountField.getComponent());
		//
		South south = new South();
		south.setStyle("border: none");
		mainLayout.appendChild(south);
		south.appendChild(confirmPanel);
		confirmPanel.addActionListener(this);

		keyListener = new Keylistener();
		keyListener.setCtrlKeys("#enter");
		keyListener.addEventListener(Events.ON_CTRL_KEY, this);
		addEventListener(Events.ON_CANCEL, this);
		appendChild(keyListener);
		
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
	private boolean dynInit (WButtonEditor button) throws Exception
	{
		if (!controller.init() && !controller.getErrorMsg().isEmpty())
		{
			FDialog.error(0, this, controller.getErrorMsg());
			return false;
		}
		
		centerPanel.setVisible(!controller.isOnlyChangePaymentRule());

		BigDecimal amount = controller.getPaymentAmount();
		bAmountField.setValue(amount);
		sAmountField.setValue(amount);
		kAmountField.setValue(amount);
		tAmountField.setValue(amount);

		//	Accounting Date
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
			sCurrencyCombo.setSelectedKeyNamePair(controller.getCurrentCurrency());
			bCurrencyCombo.addActionListener(this);
			bCurrencyCombo.setSelectedKeyNamePair(controller.getCurrentCurrency());

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

		//	Set PaymentRule
		paymentCombo.addActionListener(this);
		if (controller.getSelectedPaymentRule() != null) {
			paymentCombo.setSelectedValueNamePair(controller.getSelectedPaymentRule());
		}
		else
		{
			paymentCombo.setSelectedIndex(0);  // pick the first by default
		}
		onPaymentComboSelection();  // Sets the window size based on the selection

		/**
		 * 	Load Payment Terms
		 */
		for (KeyNamePair paymentTerm : controller.getPaymentTerms())
		{			
				pTermCombo.addItem(paymentTerm);
		}
		//	Set Selection
		if (controller.getSelectedPaymentTerm() != null)
			pTermCombo.setSelectedKeyNamePair(controller.getSelectedPaymentTerm());

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
			kTypeCombo.setSelectedValueNamePair(controller.getSelecteCreditCard());

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
			sBankAccountCombo.setSelectedKeyNamePair(controller.getSelectedBankAccount());
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
			
			bCashBookCombo.setSelectedKeyNamePair(controller.getSelectedCashBook());
			
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
	public void onEvent(Event e)
	{
		log.fine( "WPayment.actionPerformed - " + e.getTarget().getId());
		int code = 0;
		if (e.getName().equals(Events.ON_CTRL_KEY) && e.getTarget() == keyListener) {
			KeyEvent keyEvent = (KeyEvent) e;
			code = keyEvent.getKeyCode();
		}
		//	Finish
		if (e.getTarget().getId().equals(ConfirmPanel.A_OK) || code == KEYBOARD_KEY_RETURN)
		{
			if (controller.isOnlyChangePaymentRule() 
				|| FDialog.ask(windowNo, this, PaymentFormController.MSG_PaymentCreateConfirmation))
			{
				if (saveChanges ())
				{
					dispose ();
				}
			}
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL) || e.getName().equals(Events.ON_CANCEL))
			dispose();

		//	Payment Method Change
		else if (e.getTarget() == paymentCombo)
		{
			onPaymentComboSelection();
		}

		//	Check Currency change
		else if (e.getTarget() == sCurrencyCombo)
		{
			KeyNamePair pp = sCurrencyCombo.getSelectedItem().toKeyNamePair();
			sAmountField.setValue(controller.getConvertedAmount(pp.getKey()));
		}
		//	Cash Currency change
		else if (e.getTarget() == bCurrencyCombo)
		{
			KeyNamePair pp = bCurrencyCombo.getSelectedItem().toKeyNamePair();
			bAmountField.setValue(controller.getConvertedAmount(pp.getKey()));
		}

		//  Online
		else if (e.getTarget() == kOnline || e.getTarget() == sOnline) {
			this.lockUI();
			Clients.response(new AuEcho(this, "runProcessOnline", null));
		}
	}	//	actionPerformed

	public void lockUI() {
		if (m_isLocked) return;
		
		m_isLocked = true;
		
		showBusyDialog();
	}

	private void showBusyDialog() {
		progressWindow = new BusyDialog();
		progressWindow.setPage(this.getPage());
		progressWindow.doHighlighted();
	}
	
	public void runProcessOnline() {
		try {
			processOnline();
		} finally {
			unlockUI();
		}
	}
	
	public void unlockUI() {
		if (!m_isLocked) return;
		
		m_isLocked = false;
		hideBusyDialog();
		updateUI();
	}

	private void hideBusyDialog() {
		if (progressWindow != null) {
			progressWindow.dispose();
			progressWindow = null;
		}
	}

	private void updateUI() {
		if (controller.getErrorMsg().isEmpty())
			dispose();
	}

	private void onPaymentComboSelection() {
		//	get selection
		ListItem selectedItem = paymentCombo.getSelectedItem(); 
		ValueNamePair pp = selectedItem != null ? selectedItem.toValueNamePair() : null;
		if (pp != null)
		{
			bAmountField.setValue(controller.getPaymentAmount());
			sAmountField.setValue(controller.getPaymentAmount());
			kAmountField.setValue(controller.getPaymentAmount());
			show(controller.whichPanel(pp));	//	switch to panel
			controller.checkMandatory();
			
		}
	}


	private void show(String s) {
		for (Panel p : centerLayout) {
			if (p.getId().equals(s))
			{
				if (!p.isVisible())
					p.setVisible(true);
			}
			else if (p.isVisible())
			{
				p.setVisible(false);
			}
		}
		if (controller.isOnlyChangePaymentRule() || s.equals("pPanel"))
		{
			this.setWidth("420px");
			this.setHeight("150px");
		}
		else if (s.equals("bPanel"))
		{
			this.setWidth("420px");
			this.setHeight("200px");
		}
		else if (s.equals("sPanel"))
		{
			this.setWidth("470px");
			this.setHeight("300px");
		}
		else if (s.equals("tPanel"))
		{
			this.setWidth("470px");
			this.setHeight("215px");
		}
		else if (s.equals("kPanel"))
		{
			this.setWidth("500px");
			this.setHeight("300px");
		}
	}

	/**************************************************************************
	 *	Save Changes
	 *	@return true, if Window can exit
	 */
	private boolean saveChanges() {
		
		if (!controller.saveChanges())
		{
			FDialog.error(windowNo, this, "PaymentError", controller.getErrorMsg());			
			return false;
		}
		
		// Check the message in case
		if (controller.getErrorMsg() != null && !controller.getErrorMsg().isEmpty())
		{
			FDialog.error(windowNo, this, PaymentFormController.MSG_PaymentError, controller.getErrorMsg());
		}
		
		if (controller.getPaymentDocNumber() != null && !controller.getPaymentDocNumber().isEmpty())
		{
			FDialog.info(windowNo, this, PaymentFormController.MSG_PaymentCreated, controller.getPaymentDocNumber());
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
			FDialog.error(windowNo, this, "PaymentError", controller.getErrorMsg());
		}
		else
		{
			String info = controller.getOnlineInfo();
			FDialog.info(windowNo, this, "PaymentProcessed", info + "\n" + controller.getPaymentDocNumber());
			dispose();
		}
	}

	/**
	 * 	Need Save record (payment with waiting order)
	 *	@return true if payment with waiting order
	 */
	public boolean needSave()
	{
		return m_needSave;
	}	//	needSave
	
	public String getPaymentRule() {
		return ((ValueNamePair) paymentCombo.getSelectedItem().toValueNamePair()).getValue();
	}

	public int getBankAccount(String paymentRule) {

		ListItem li = null;
		
//		if (paymentRule.equalsIgnoreCase("B")) 
//		{
//
//			li = bBankAccountCombo.getSelectedItem();
//			
//		}
//		else 
		if (paymentRule.equalsIgnoreCase("S"))
		{
			
			li = sBankAccountCombo.getSelectedItem();
			
		}
		
		if (li != null)
			return li.toKeyNamePair().getKey();
		else
			return 0;
			
	}
	
	public int getCashBook(String paymentRule) {
		
		ListItem li = null;
		
		if (paymentRule.equalsIgnoreCase("B")) 
		{
			
			li = bCashBookCombo.getSelectedItem();
			
		}
		
		if (li != null)
			return li.toKeyNamePair().getKey();
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
			
			amount = (BigDecimal) tAmountField.getValue();

		}
		
		if (amount != null)
			return amount;
		else
			return Env.ZERO;

	}
	
	public String getCreditCardType(String paymentRule) {
		
		ListItem li = null;
		
		if (paymentRule.equalsIgnoreCase("K"))
		{
			li = kTypeCombo.getSelectedItem();
		}

		if (li != null)
			return li.toValueNamePair().getValue();
		else
			return "";
		
	}
	
	public int getBPBankAccount(String paymentRule) {
		
		ListItem li = null;
		
		if (paymentRule.equalsIgnoreCase("T") || paymentRule.equalsIgnoreCase("D")) 
		{
			
			li = tAccountCombo.getSelectedItem();
			
		}
		
		if (li != null)
			return li.toKeyNamePair().getKey();
		else
			return 0;

	}

	public int getPaymentTerm(String paymentRule) {
		
		ListItem li = null;
		
		if (paymentRule.equalsIgnoreCase("P")) 
		{
			
			li = pTermCombo.getSelectedItem();
			
		}
		
		if (li != null)
			return li.toKeyNamePair().getKey();
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
			return kNumberField.getText();
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
			paymentLabel.setMandatory(mandatory);
		if (field.equals(FIELD_kType))
			kTypeLabel.setMandatory(mandatory);	
		if (field.equals(FIELD_kNumber))
			kNumberLabel.setMandatory(mandatory);
		if (field.equals(FIELD_kName))
			kNameLabel.setMandatory(mandatory);
		if (field.equals(FIELD_kExp))
			kExpLabel.setMandatory(mandatory);
		if (field.equals(FIELD_kApproval))
			kApprovalLabel.setMandatory(mandatory);
		if (field.equals(FIELD_kAmount))
			kAmountLabel.setMandatory(mandatory);
		if (field.equals(FIELD_tAccount))
			tAccountLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sAccountNumber))
			sAccountNumberLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sCheckNumber))
			sCheckNumberLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sRouting))
			sRoutingLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sCurrency))
			sCurrencyLabel.setMandatory(mandatory);
		if (field.equals(FIELD_bCurrency))
			bCurrencyLabel.setMandatory(mandatory);
		if (field.equals(FIELD_pTerm))
			pTermLabel.setMandatory(mandatory);
		if (field.equals(FIELD_bAmount))
			bAmountLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sAmount))
			sAmountLabel.setMandatory(mandatory);
		if (field.equals(FIELD_bDate))
			bDateLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sCheck))
			sCheckNumberLabel.setMandatory(mandatory);
		if (field.equals(FIELD_sBankAccount))
			sBankAccountLabel.setMandatory(mandatory);
		if (field.equals(FIELD_bCashBook))
			bCashBookLabel.setMandatory(mandatory);
		if (field.equals(FIELD_tAmount))
			tAmountLabel.setMandatory(mandatory);
		
	}


	@Override
	public void setError(String field, boolean error) {
		
		setMandatory(field, error);
		
	}


}	//	WPayment
