/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor: Yamel Senih www.erpcya.com                                    *
 * Contributor: Mario Calderon www.westfalia-it.com                           *
 *****************************************************************************/
package org.adempiere.pos;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Properties;

import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.adempiere.pos.service.CollectDetail;
import org.adempiere.pos.service.POSPanelInterface;
import org.compiere.apps.ADialog;
import org.compiere.apps.AppsAction;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.X_C_Payment;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Victor Perez <victor.perez@e-evolution.com>,  eEvolution http://www.e-evolution.com
 *
 */
public class VCollectDetail extends CollectDetail
	implements VetoableChangeListener, ActionListener, KeyListener, POSPanelInterface {
	
	/**
	 * Standard Constructor
	 * @param collect
	 * @param tenderType
	 * @param m_PayAmt
	 */
	public VCollectDetail(VCollect parentCollect, String tenderType, BigDecimal payAmt) {
		super(tenderType, payAmt);
		ctx = Env.getCtx();
		this.parentCollect = parentCollect;
		keyboard = parentCollect.getKeyboard();
		init();
	}
	
	/**	Panels				*/
	private CPanel 			mainPanel;
	private CPanel 			standardPanel;
	private TitledBorder 	titleBorder;
	private GridBagLayout 	layout;
	private CButton 		buttonMinus;
	
	/**	Check				*/
	private CPanel 			checkPanel;
	private POSTextField 	fieldCheckNo;
	private POSTextField 	fieldCheckRoutingNo;
	private VDate 			fieldCheckDate;
	
	/**	Debit Card			*/
	private CPanel 			debitPanel;
	private POSTextField 	fieldDebitRoutingNo;
	private POSTextField 	fieldDebitCVC;
	private POSTextField 	fieldDebitCountry;
	
	/**	Credit Card			*/
	private CPanel 			creditPanel;
	private VLookup 		fieldCreditCardType;
	private POSTextField 	fieldCreditCardNumber;
	private POSTextField 	fieldName;
	private VComboBox 		fieldCreditCardExpMM;
	private VComboBox 		fieldCreditCardExpYY;
	private POSTextField 	fieldCreditCardVV;
	
	/**	Credit Note			*/
	private CPanel 			creditMemoPanel;
	private VLookup 		fieldCreditMemo;
	private CLabel			labelCreditMemo;
	
	/**	Generic Values		*/
	private Properties 		ctx;
	private VLookup 		fieldTenderType;
	private VNumber 		fieldPayAmt;
	private VCollect 		parentCollect;
	
	/**	Keyboard to use		*/
	private POSKeyboard 	keyboard;
	/**	Default Font		*/
	private Font 			font; //= AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 12);
	/**	Log					*/
	private CLogger 		log = CLogger.getCLogger(VCollect.class);
	/**	Default Width		*/
	private final int		FIELD_WIDTH 	= 200;
	/**	Default Height		*/
	private final int		FIELD_HEIGHT 	= 25;
	
	/**
	 * Get Main Panel
	 * @return CPanel
	 */
	public CPanel getPanel() {
		return mainPanel;
	}
	
	/**
	 * Load Standard Fields for Collect
	 * @return void
	 */
	private void loadStandardPanel() {
		standardPanel = new CPanel(layout);
		//	For Tender Type
		int columnId = 8416;        //  C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, columnId, DisplayType.List);
		fieldTenderType = new VLookup("TenderType", true, false, true, lookup);
		((VComboBox) fieldTenderType.getCombo()).setRenderer(new POSLookupListCellRenderer(font));
		fieldTenderType.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		((VComboBox) fieldTenderType.getCombo()).setFont(font);
		fieldTenderType.addVetoableChangeListener(this);
		//	For Amount
		fieldPayAmt = new VNumber("PayAmt", true, false, true, DisplayType.Amount, "");
		fieldPayAmt.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldPayAmt.setFont(font);
		fieldPayAmt.setValue(Env.ZERO);
		fieldPayAmt.addVetoableChangeListener(this);
		fieldPayAmt.addKeyListener(this);
		//	Button
		AppsAction action = new AppsAction("Minus", KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2), false);
		action.setDelegate(this);
		buttonMinus = (CButton)action.getButton();
		buttonMinus.setPreferredSize(new Dimension(FIELD_HEIGHT, FIELD_HEIGHT));
		buttonMinus.setFocusable(false);
		//	Add Tender Type
		standardPanel.add(fieldTenderType,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		standardPanel.add(fieldPayAmt,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
	}
	
	/**
	 * Load Check Panel
	 * @return void
	 */
	private void loadCheckPanel() {
		//	Instance Panel
		checkPanel = new CPanel(layout);
		//	For Check No
		String m_CheckNo = Msg.translate(ctx, "CheckNo");
		fieldCheckNo = new POSTextField(m_CheckNo, keyboard);
		fieldCheckNo.setPlaceholder(m_CheckNo);
		fieldCheckNo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldCheckNo.setFont(font);
		fieldCheckNo.addKeyListener(this);
		fieldCheckNo.addActionListener(this);
		// For Check Route No
		String m_RoutingNo = Msg.translate(ctx, "RoutingNo");
		fieldCheckRoutingNo = new POSTextField(m_RoutingNo, keyboard);
		fieldCheckRoutingNo.setPlaceholder(m_RoutingNo);
		fieldCheckRoutingNo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldCheckRoutingNo.setFont(font);
		fieldCheckRoutingNo.addKeyListener(this);
		fieldCheckRoutingNo.addActionListener(this);
		//	For Check Date
		String languageName = Env.getAD_Language(ctx);
		Language language = Language.getLanguage(languageName);
		Language.setLoginLanguage(language);
		//	Locale
		Locale locale = language.getLocale();
		Locale.setDefault(locale);
		fieldCheckDate = new VDate(DisplayType.Date);
		fieldCheckDate.setFormat();
		fieldCheckDate.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldCheckDate.setFont(font);
		fieldCheckDate.addActionListener(this);
		//	Add To Panel
		checkPanel.add(fieldCheckRoutingNo,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		checkPanel.add(fieldCheckNo,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		checkPanel.add(fieldCheckDate,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		//	Default visible false
		checkPanel.setVisible(false);
	}
	
	/**
	 * Load for Debit Panel
	 * @return void
	 */
	private void loadDebitPanel() {
		debitPanel = new CPanel(layout);
		//	For Route
		String routingNo = Msg.translate(ctx, "RoutingNo");
		fieldDebitRoutingNo = new POSTextField(routingNo, keyboard);
		fieldDebitRoutingNo.setPlaceholder(routingNo);
		fieldDebitRoutingNo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldDebitRoutingNo.setFont(font);
		fieldDebitRoutingNo.addKeyListener(this);
		fieldDebitRoutingNo.addActionListener(this);
		//	For CVC
		String cvv2Match = Msg.translate(ctx, "R_CVV2Match");
		fieldDebitCVC = new POSTextField(cvv2Match, keyboard);
		fieldDebitCVC.setPlaceholder(cvv2Match);
		fieldDebitCVC.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldDebitCVC.setFont(font);
		fieldDebitCVC.addKeyListener(this);
		fieldDebitCVC.addActionListener(this);
		//	For Country
		String country = Msg.translate(ctx, "A_Country");
		fieldDebitCountry = new POSTextField(country, keyboard);
		fieldDebitCountry.setPlaceholder(country);
		fieldDebitCountry.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldDebitCountry.setFont(font);
		fieldDebitCountry.addKeyListener(this);
		fieldDebitCountry.addActionListener(this);
		//	Add to Panel
		debitPanel.add(fieldDebitRoutingNo,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		debitPanel.add(fieldDebitCountry,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		debitPanel.add(fieldDebitCVC,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		//	Default visible false
		debitPanel.setVisible(false);
	}
	
	/**
	 * Load for Credit Card
	 * @return void
	 */
	private void loadCreditPanel() {
		creditPanel = new CPanel(layout);
		//	For Credit Card
		int columnId = 8374; 			//C_Payment_v.CreditCardType
		MLookup cardlookup = MLookupFactory.get(Env.getCtx(), 0, 0, columnId, DisplayType.List);
		fieldCreditCardType = new VLookup("CreditCardType", true, false, true, cardlookup);
		//	For Credit Card Type
		((VComboBox) fieldCreditCardType.getCombo()).setRenderer(new POSLookupListCellRenderer(font));
		fieldCreditCardType.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		((VComboBox) fieldCreditCardType.getCombo()).setFont(font);
		fieldCreditCardType.addVetoableChangeListener(this);
		//	For Months
		//	For Card No
		String creditCardNumber = Msg.translate(ctx, "CreditCardNumber");
		fieldCreditCardNumber = new POSTextField(creditCardNumber, parentCollect.getKeyboard());
		fieldCreditCardNumber.setPlaceholder(creditCardNumber);
		fieldCreditCardNumber.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldCreditCardNumber.setFont(font);
		fieldCreditCardNumber.addKeyListener(this);
		fieldCreditCardNumber.addActionListener(this);
		//	For Card Name
		String name = Msg.translate(ctx, "A_Name");
		fieldName = new POSTextField(name, parentCollect.getKeyboard());
		fieldName.setPlaceholder(name);
		fieldName.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldName.setFont(font);
		fieldName.addKeyListener(this);
		fieldName.addActionListener(this);
		//	For Card Month
		fieldCreditCardExpMM = new VComboBox(getCCMonths());
		fieldCreditCardExpMM.setName("CreditCardExpMM");
		fieldCreditCardExpMM.setValue(-1);
		fieldCreditCardExpMM.setMandatory(true);
		fieldCreditCardExpMM.setPreferredSize(new Dimension(FIELD_WIDTH / 2, FIELD_HEIGHT));
		fieldCreditCardExpMM.setRenderer(new POSLookupListCellRenderer(font));
		fieldCreditCardExpMM.setFont(font);
		fieldCreditCardExpMM.addActionListener(this);
		//	For Card Year
		fieldCreditCardExpYY = new VComboBox(getCCYears());
		fieldCreditCardExpYY.setName("CreditCardExpYY");
		fieldCreditCardExpYY.setValue(-1);
		fieldCreditCardExpYY.setMandatory(true);
		fieldCreditCardExpYY.setPreferredSize(new Dimension(FIELD_WIDTH / 2, FIELD_HEIGHT));
		fieldCreditCardExpYY.setRenderer(new POSLookupListCellRenderer(font));
		fieldCreditCardExpYY.setFont(font);
		fieldCreditCardExpYY.addActionListener(this);
		//	For Card VV
		String creditCardVV = Msg.translate(ctx, "CreditCardVV");
		
		
		fieldCreditCardVV = new POSTextField(creditCardVV, parentCollect.getKeyboard());
		fieldCreditCardVV.setPlaceholder(creditCardVV);
		fieldCreditCardVV.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fieldCreditCardVV.setFont(font);
		fieldCreditCardVV.addKeyListener(this);
		fieldCreditCardVV.addActionListener(this);
		//	Add to Panel
		creditPanel.add(fieldCreditCardType,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		creditPanel.add(fieldName,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		creditPanel.add(fieldCreditCardNumber,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		creditPanel.add(fieldCreditCardVV,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		creditPanel.add(fieldCreditCardExpMM,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		creditPanel.add(fieldCreditCardExpYY,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		//	Default visible false
		creditPanel.setVisible(false);
	}
	
	/**
	 * Load for Credit Memo
	 * @return void
	 */
	private void loadCreditMemo() {
		creditMemoPanel = new CPanel(layout);
		//	Add label credit note
		labelCreditMemo = new CLabel(Msg.translate(Env.getCtx(), "CreditMemo") + ":");
		labelCreditMemo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));

		//	For Credit Memo
		MLookup cardNotelookup = getCreditMemoLockup(parentCollect.getC_BPartner_ID());
		fieldCreditMemo = new VLookup("CreditMemo", false, false, true, cardNotelookup);
		//	For Credit Memo Type
//		((VComboBox)fieldCreditMemo.getCombo()).setRenderer(new POSLookupListCellRenderer(font));
		fieldCreditMemo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
//		((VComboBox)fieldCreditMemo.getCombo()).setFont(font);
		fieldCreditMemo.addVetoableChangeListener(this);
		
		//	Add to Panel
		creditMemoPanel.add(labelCreditMemo,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		creditMemoPanel.add(fieldCreditMemo,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		//	Default visible false
		creditMemoPanel.setVisible(false);
	}
	
	/**
	 * Init Main Panel
	 * @return void
	 */
	private void init() {
		font = parentCollect.getPOS().getFont();
		layout = new GridBagLayout();
		mainPanel = new CPanel(layout);
		//	Set Border
		//titleBorder = BorderFactory.createTitledBorder("Credit Card");
		//titleBorder.setTitleColor(AdempierePLAF.getTextColor_Label());
		//mainPanel.setBorder(titleBorder);

		//	Load Standard Panel
		loadStandardPanel();
		//	Load Check Panel
		loadCheckPanel();
		//	Load Debit Panel
		loadDebitPanel();
		//	Load Credit Panel
		loadCreditPanel();
		//	Load Credit Note Panel
		loadCreditMemo();
		
		//	Add to Main Panel
		mainPanel.add(standardPanel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		mainPanel.add(buttonMinus,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		mainPanel.add(checkPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		mainPanel.add(debitPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		mainPanel.add(creditPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));

		mainPanel.add(creditMemoPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(2, 0, 2, 2), 0, 0));
		
		//	Change View
		fieldTenderType.setValue(getTenderType());
		fieldCreditCardType.setValue(getCreditCardType());
		fieldPayAmt.setValue(getPayAmt());
		changeViewPanel();
	}
			
	@Override
	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		String tenderType = getTenderType();
		log.config(name + " = " + value);
		//	Verify Event
		if(e.getSource().equals(fieldPayAmt)){
			BigDecimal payAmt = (BigDecimal) fieldPayAmt.getValue();
			if(tenderType.equals(X_C_Payment.TENDERTYPE_CreditMemo)
					&& payAmt.compareTo(getOpenAmtCreditMemo()) > 0
					&& fieldCreditMemo.getValue() != null) {
				ADialog.warn(1, null,  Msg.parseTranslation(ctx, "POS.MaxAmountAllowed")+":"+getOpenAmtCreditMemo());
				fieldPayAmt.setValue(getOpenAmtCreditMemo());
			}
			setPayAmt((BigDecimal) fieldPayAmt.getValue());
			parentCollect.refreshPanel();
		} else if(name.equals("TenderType")) {
			String m_TenderType = ((String)(value != null? value: 0));
			setTenderType(m_TenderType);
			changeViewPanel();
			fieldPayAmt.setValue(getInitPayAmt());
			setPayAmt((BigDecimal) fieldPayAmt.getValue());
			parentCollect.refreshPanel();
		} else if(name.equals("CreditCardType")) {
			setCreditCardType((String) fieldCreditCardType.getValue());
		} else if(name.equals("CreditMemo")) {
			int invoiceId = ((Integer)(value != null? value: 0)).intValue();
			setC_Invoice_ID(invoiceId);
			setPayAmt(getInitPayAmt());
			parentCollect.refreshPanel();
			fieldPayAmt.setValue(getOpenAmtCreditMemo());
			
			setPayAmt((BigDecimal) fieldPayAmt.getValue());
			parentCollect.refreshPanel();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonMinus)) {
			parentCollect.removeCollectDetail(this);
		} else if(e.getSource().equals(fieldCreditCardExpMM)) {
			setCreditCardExpMM((String) fieldCreditCardExpMM.getValue());
		} else if(e.getSource().equals(fieldCreditCardExpYY)) {
			setCreditCardExpYY((String) fieldCreditCardExpYY.getValue());
		} else if(e.getSource().equals(fieldCheckNo)) {	//	For Check
			setReferenceNo(fieldCheckNo.getText());
		} else if(e.getSource().equals(fieldCheckRoutingNo)) {
			setRoutingNo(fieldCheckRoutingNo.getText());
		} else if(e.getSource().equals(fieldCheckDate)) {
			setDateTrx(fieldCheckDate.getTimestamp());
		} else if(e.getSource().equals(fieldDebitRoutingNo)) {	//	For Debit Card
			setRoutingNo(fieldDebitRoutingNo.getText());
		} else if(e.getSource().equals(fieldDebitCVC)) {
			//	TODO add support to controller to be define
		} else if(e.getSource().equals(fieldDebitCountry)) {
			setA_Country(fieldDebitCountry.getText());
		} else if(e.getSource().equals(fieldCreditCardNumber)) {	//	For Credit Card
			setCreditCardNumber(fieldCreditCardNumber.getText());
		} else if(e.getSource().equals(fieldName)) {
			setA_Name(fieldName.getText());
		} else if(e.getSource().equals(fieldCreditCardVV)) {
			setCreditCardVV(fieldCreditCardVV.getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent keyEvent) {
		//	Validate for just L=letter digit
		if(!Character.isLetterOrDigit(keyEvent.getKeyChar())) {
			keyEvent.consume();
		}
		if(keyEvent.getSource().equals(fieldCheckNo)) {	//	For Check
			setReferenceNo(fieldCheckNo.getText());
		} else if(keyEvent.getSource().equals(fieldCheckRoutingNo)) {
			setRoutingNo(fieldCheckRoutingNo.getText());
		} else if(keyEvent.getSource().equals(fieldCheckDate)) {
			setDateTrx(fieldCheckDate.getTimestamp());
		} else if(keyEvent.getSource().equals(fieldDebitRoutingNo)) {	//	For Debit Card
			setRoutingNo(fieldDebitRoutingNo.getText());
		} else if(keyEvent.getSource().equals(fieldDebitCVC)) {
			setCreditCardVV(fieldDebitCVC.getText());
		} else if(keyEvent.getSource().equals(fieldDebitCountry)) {
			setA_Country(fieldDebitCountry.getText());
		} else if(keyEvent.getSource().equals(fieldCreditCardNumber)) {	//	For Credit Card
			setCreditCardNumber(fieldCreditCardNumber.getText());
		} else if(keyEvent.getSource().equals(fieldName)) {
			setA_Name(fieldName.getText());
		} else if(keyEvent.getSource().equals(fieldCreditCardVV)) {
			setCreditCardVV(fieldCreditCardVV.getText());
		} else {	//	TODO Add validation with name, it is resolved when implement KeyListener in VNumber
			setPayAmt((BigDecimal) fieldPayAmt.getValue());
		}
		//	Refresh
		parentCollect.refreshPanel();
	}

	@Override
	public void refreshPanel() {
		
	}

	@Override
	public String validatePayment() {
		
		return null;
	}

	/**
	 * Request Focus in Payment Amount Field
	 * @return void
	 */
	public void requestFocusInPayAmt() {
		fieldPayAmt.requestFocus();
	}
	
	@Override
	public void changeViewPanel() {
		String tenderType = getTenderType();
		//	Valid Null
		if(tenderType == null)
			return;
		//	Change Title
		String m_DisplayTenderType = fieldTenderType.getDisplay();
		//titleBorder.setTitle(m_DisplayTenderType);
		//	
		if(tenderType.equals(X_C_Payment.TENDERTYPE_Check)){
			checkPanel.setVisible(true);
			debitPanel.setVisible(false);
			creditPanel.setVisible(false);
			creditMemoPanel.setVisible(false);
		} else if(tenderType.equals(X_C_Payment.TENDERTYPE_DirectDebit)){
			checkPanel.setVisible(false);
			debitPanel.setVisible(true);
			creditPanel.setVisible(false);
			creditMemoPanel.setVisible(false);
		} else if(tenderType.equals(X_C_Payment.TENDERTYPE_CreditCard)){
			checkPanel.setVisible(false);
			debitPanel.setVisible(false);
			creditPanel.setVisible(true);
			creditMemoPanel.setVisible(false);
		} else if(tenderType.equals(X_C_Payment.TENDERTYPE_CreditMemo)){
			checkPanel.setVisible(false);
			debitPanel.setVisible(false);
			creditPanel.setVisible(false);
			creditMemoPanel.setVisible(true);
		} else {
			checkPanel.setVisible(false);
			debitPanel.setVisible(false);
			creditPanel.setVisible(false);
			creditMemoPanel.setVisible(false);
		}
	}
	
	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}
}