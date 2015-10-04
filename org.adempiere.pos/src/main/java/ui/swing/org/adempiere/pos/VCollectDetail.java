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

import javax.swing.BorderFactory;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.adempiere.plaf.AdempierePLAF;
import org.adempiere.pos.service.CollectDetail;
import org.adempiere.pos.service.I_POSPanel;
import org.compiere.apps.AppsAction;
import org.compiere.grid.ed.VComboBox;
import org.compiere.grid.ed.VDate;
import org.compiere.grid.ed.VLookup;
import org.compiere.grid.ed.VNumber;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.X_C_Payment;
import org.compiere.swing.CButton;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *
 */
public class VCollectDetail extends CollectDetail
	implements VetoableChangeListener, ActionListener, KeyListener, I_POSPanel {
	
	/**	Panels				*/
	private CPanel 			v_MainPanel;
	private CPanel 			v_StandardPanel;
	private TitledBorder 	v_TitleBorder;
	private GridBagLayout 	layout;
	private CButton			bMinus;
	
	/**	Check				*/
	private CPanel 			v_CheckPanel;
	private POSTextField 	fCheckNo;
	private POSTextField 	fCheckRoutingNo;
	private VDate 			fCheckDate;
	
	/**	Debit Card			*/
	private CPanel 			v_DebitPanel;
	private POSTextField 	fDebitRoutingNo;
	private POSTextField 	fDebitCVC;
	private POSTextField 	fDebitCountry;
	
	/**	Credit Card			*/
	private CPanel 			v_CreditPanel;
	private VLookup 		fCreditCardType;
	private POSTextField 	fCreditCardNumber;
	private POSTextField 	fA_Name;
	private VComboBox 		fCreditCardExpMM;
	private VComboBox 		fCreditCardExpYY;
	private POSTextField 	fCreditCardVV;
	
	/**	Generic Values		*/
	private Properties 		p_ctx;
	private VLookup 		fTenderType;
	private VNumber 		fPayAmt;
	private VCollect 		v_Parent;
	
	/**	Keyboard to use		*/
	private POSKeyboard keyboard;
	/**	Default Font		*/
	private Font 			font = AdempierePLAF.getFont_Field().deriveFont(Font.BOLD, 18);
	/**	Log					*/
	private CLogger 		log = CLogger.getCLogger(VCollect.class);
	/**	Default Width		*/
	private final int		FIELD_WIDTH 	= 200;
	/**	Default Height		*/
	private final int		FIELD_HEIGHT 	= 50;
	
	/**
	 * Standard Constructor
	 * @param p_VCollect
	 * @param p_TenderType
	 * @param m_PayAmt
	 */
	public VCollectDetail(VCollect p_VCollect, String p_TenderType, BigDecimal m_PayAmt) {
		super(p_TenderType, m_PayAmt);
		p_ctx = Env.getCtx();
		v_Parent = p_VCollect;
		keyboard = v_Parent.getKeyboard();
		init();
	}
	
	/**
	 * Get Main Panel
	 * @return CPanel
	 */
	public CPanel getPanel() {
		return v_MainPanel;
	}
	
	/**
	 * Load Standard Fields for Collect
	 * @return void
	 */
	private void loadStandardPanel() {
		v_StandardPanel = new CPanel(layout);
		//	For Tender Type
		int AD_Column_ID = 8416;        //  C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		fTenderType = new VLookup("TenderType", true, false, true, lookup);
		((VComboBox)fTenderType.getCombo()).setRenderer(new POSLookupCellRenderer(font));
		fTenderType.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		((VComboBox)fTenderType.getCombo()).setFont(font);
		fTenderType.addVetoableChangeListener(this);
		//	For Amount
		fPayAmt = new VNumber("PayAmt", true, false, true, DisplayType.Amount, "");
		fPayAmt.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fPayAmt.setFont(font);
		fPayAmt.setValue(Env.ZERO);
		fPayAmt.addVetoableChangeListener(this);
		fPayAmt.addKeyListener(this);
		//	Button
		AppsAction act = new AppsAction("Minus", KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2), false);
		act.setDelegate(this);
		bMinus = (CButton)act.getButton();
		bMinus.setPreferredSize(new Dimension(FIELD_HEIGHT, FIELD_HEIGHT));
		bMinus.setFocusable(false);
		//	Add Tender Type
		v_StandardPanel.add(fTenderType,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		v_StandardPanel.add(fPayAmt,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
	}
	
	/**
	 * Load Check Panel
	 * @return void
	 */
	private void loadCheckPanel() {
		//	Instance Panel
		v_CheckPanel = new CPanel(layout);
		//	For Check No
		String m_CheckNo = Msg.translate(p_ctx, "CheckNo");
		fCheckNo = new POSTextField(m_CheckNo, keyboard);
		fCheckNo.setPlaceholder(m_CheckNo);
		fCheckNo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fCheckNo.setFont(font);
		fCheckNo.addKeyListener(this);
		fCheckNo.addActionListener(this);
		// For Check Route No
		String m_RoutingNo = Msg.translate(p_ctx, "RoutingNo");
		fCheckRoutingNo = new POSTextField(m_RoutingNo, keyboard);
		fCheckRoutingNo.setPlaceholder(m_RoutingNo);
		fCheckRoutingNo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fCheckRoutingNo.setFont(font);
		fCheckRoutingNo.addKeyListener(this);
		fCheckRoutingNo.addActionListener(this);
		//	For Check Date
		String langName = Env.getAD_Language(p_ctx);
		Language language = Language.getLanguage(langName);
		Language.setLoginLanguage(language);
		//	Locale
		Locale loc = language.getLocale();
		Locale.setDefault(loc);
		fCheckDate = new VDate(DisplayType.Date);
		fCheckDate.setFormat();	
		fCheckDate.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fCheckDate.setFont(font);
		fCheckDate.addActionListener(this);
		//	Add To Panel
		v_CheckPanel.add(fCheckRoutingNo,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		v_CheckPanel.add(fCheckNo,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		v_CheckPanel.add(fCheckDate,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	Default visible false
		v_CheckPanel.setVisible(false);
	}
	
	/**
	 * Load for Debit Panel
	 * @return void
	 */
	private void loadDebitPanel() {
		v_DebitPanel = new CPanel(layout);
		//	For Route
		String m_RoutingNo = Msg.translate(p_ctx, "RoutingNo");
		fDebitRoutingNo = new POSTextField(m_RoutingNo, keyboard);
		fDebitRoutingNo.setPlaceholder(m_RoutingNo);
		fDebitRoutingNo.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fDebitRoutingNo.setFont(font);
		fDebitRoutingNo.addKeyListener(this);
		fDebitRoutingNo.addActionListener(this);
		//	For CVC
		String m_R_CVV2Match = Msg.translate(p_ctx, "R_CVV2Match");
		fDebitCVC = new POSTextField(m_R_CVV2Match, keyboard);
		fDebitCVC.setPlaceholder(m_R_CVV2Match);
		fDebitCVC.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fDebitCVC.setFont(font);
		fDebitCVC.addKeyListener(this);
		fDebitCVC.addActionListener(this);
		//	For Country
		String m_A_Country = Msg.translate(p_ctx, "A_Country");
		fDebitCountry = new POSTextField(m_A_Country, keyboard);
		fDebitCountry.setPlaceholder(m_A_Country);
		fDebitCountry.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fDebitCountry.setFont(font);
		fDebitCountry.addKeyListener(this);
		fDebitCountry.addActionListener(this);
		//	Add to Panel
		v_DebitPanel.add(fDebitRoutingNo,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		v_DebitPanel.add(fDebitCountry,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));		
		v_DebitPanel.add(fDebitCVC,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	Default visible false
		v_DebitPanel.setVisible(false);
	}
	
	/**
	 * Load for Credit Card
	 * @return void
	 */
	private void loadCreditPanel() {
		v_CreditPanel = new CPanel(layout);
		//	For Credit Card
		int AD_Column_ID = 8374; 			//C_Payment_v.CreditCardType
		MLookup cardlookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		fCreditCardType = new VLookup("CreditCardType", true, false, true, cardlookup);
		//	For Credit Card Type
		((VComboBox)fCreditCardType.getCombo()).setRenderer(new POSLookupCellRenderer(font));
		fCreditCardType.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		((VComboBox)fCreditCardType.getCombo()).setFont(font);
		fCreditCardType.addVetoableChangeListener(this);
		//	For Months
		//	For Card No
		String m_CreditCardNumber = Msg.translate(p_ctx, "CreditCardNumber");
		fCreditCardNumber = new POSTextField(m_CreditCardNumber, v_Parent.getKeyboard());
		fCreditCardNumber.setPlaceholder(m_CreditCardNumber);
		fCreditCardNumber.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fCreditCardNumber.setFont(font);
		fCreditCardNumber.addKeyListener(this);
		fCreditCardNumber.addActionListener(this);
		//	For Card Name
		String m_A_Name = Msg.translate(p_ctx, "A_Name");
		fA_Name = new POSTextField(m_A_Name, v_Parent.getKeyboard());
		fA_Name.setPlaceholder(m_A_Name);
		fA_Name.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fA_Name.setFont(font);
		fA_Name.addKeyListener(this);
		fA_Name.addActionListener(this);
		//	For Card Month
		fCreditCardExpMM = new VComboBox(getCCMonths());
		fCreditCardExpMM.setName("CreditCardExpMM");
		fCreditCardExpMM.setValue(-1);
		fCreditCardExpMM.setMandatory(true);
		fCreditCardExpMM.setPreferredSize(new Dimension(FIELD_WIDTH / 2, FIELD_HEIGHT));
		fCreditCardExpMM.setRenderer(new POSLookupCellRenderer(font));
		fCreditCardExpMM.setFont(font);
		fCreditCardExpMM.addActionListener(this);
		//	For Card Year
		fCreditCardExpYY = new VComboBox(getCCYears());
		fCreditCardExpYY.setName("CreditCardExpYY");
		fCreditCardExpYY.setValue(-1);
		fCreditCardExpYY.setMandatory(true);
		fCreditCardExpYY.setPreferredSize(new Dimension(FIELD_WIDTH / 2, FIELD_HEIGHT));
		fCreditCardExpYY.setRenderer(new POSLookupCellRenderer(font));
		fCreditCardExpYY.setFont(font);
		fCreditCardExpYY.addActionListener(this);
		//	For Card VV
		String m_CreditCardVV = Msg.translate(p_ctx, "CreditCardVV");
		
		
		fCreditCardVV = new POSTextField(m_CreditCardVV, v_Parent.getKeyboard());
		fCreditCardVV.setPlaceholder(m_CreditCardVV);
		fCreditCardVV.setPreferredSize(new Dimension(FIELD_WIDTH, FIELD_HEIGHT));
		fCreditCardVV.setFont(font);
		fCreditCardVV.addKeyListener(this);
		fCreditCardVV.addActionListener(this);
		//	Add to Panel
		v_CreditPanel.add(fCreditCardType,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		v_CreditPanel.add(fA_Name,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		v_CreditPanel.add(fCreditCardNumber,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));	
		v_CreditPanel.add(fCreditCardVV,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));	
		v_CreditPanel.add(fCreditCardExpMM,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NORTH, new Insets(1, 0, 5, 5), 0, 0));
		v_CreditPanel.add(fCreditCardExpYY,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(1, 0, 5, 5), 0, 0));
		//	Default visible false
		v_CreditPanel.setVisible(false);
	}
	
	/**
	 * Init Main Panel
	 * @return void
	 */
	private void init() {
		layout = new GridBagLayout();
		v_MainPanel = new CPanel(layout);
		//	Set Border
		v_TitleBorder = BorderFactory.createTitledBorder("Credit Card");
		v_TitleBorder.setTitleColor(AdempierePLAF.getTextColor_Label());
		v_MainPanel.setBorder(v_TitleBorder);
		//	Load Standard Panel
		loadStandardPanel();
		//	Load Check Panel
		loadCheckPanel();
		//	Load Debit Panel
		loadDebitPanel();
		//	Load Credit Panel
		loadCreditPanel();
		//	Add to Main Panel
		v_MainPanel.add(v_StandardPanel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		
		v_MainPanel.add(bMinus,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		
		v_MainPanel.add(v_CheckPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		
		v_MainPanel.add(v_DebitPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		
		v_MainPanel.add(v_CreditPanel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.NORTH, new Insets(5, 0, 5, 5), 0, 0));
		//	Change View
		fTenderType.setValue(getTenderType());
		fCreditCardType.setValue(getCreditCardType());
		fPayAmt.setValue(getPayAmt());
		//	
		changeViewPanel();
	}
			
	@Override
	public void vetoableChange(PropertyChangeEvent e)
			throws PropertyVetoException {
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		log.config(name + " = " + value);
		//	Verify Event
		if(e.getSource().equals(fPayAmt)){
			setPayAmt((BigDecimal) fPayAmt.getValue());
			v_Parent.refreshPanel();
		} else if(name.equals("TenderType")) {
			String m_TenderType = ((String)(value != null? value: 0));
			setTenderType(m_TenderType);
			changeViewPanel();
		} else if(name.equals("CreditCardType")) {
			setCreditCardType((String) fCreditCardType.getValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(bMinus)) {
			v_Parent.removeCollectDetail(this);
		} else if(e.getSource().equals(fCreditCardExpMM)) {
			setCreditCardExpMM((String)fCreditCardExpMM.getValue());
		} else if(e.getSource().equals(fCreditCardExpYY)) {
			setCreditCardExpYY((String)fCreditCardExpYY.getValue());
		} else if(e.getSource().equals(fCheckNo)) {	//	For Check
			setReferenceNo(fCheckNo.getText());
		} else if(e.getSource().equals(fCheckRoutingNo)) {
			setRoutingNo(fCheckRoutingNo.getText());
		} else if(e.getSource().equals(fCheckDate)) {
			setDateTrx(fCheckDate.getTimestamp());
		} else if(e.getSource().equals(fDebitRoutingNo)) {	//	For Debit Card
			setRoutingNo(fDebitRoutingNo.getText());
		} else if(e.getSource().equals(fDebitCVC)) {
			//	TODO add support to controller to be define
		} else if(e.getSource().equals(fDebitCountry)) {
			setA_Country(fDebitCountry.getText());
		} else if(e.getSource().equals(fCreditCardNumber)) {	//	For Credit Card
			setCreditCardNumber(fCreditCardNumber.getText());
		} else if(e.getSource().equals(fA_Name)) {
			setA_Name(fA_Name.getText());
		} else if(e.getSource().equals(fCreditCardVV)) {
			setCreditCardVV(fCreditCardVV.getText());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//	Validate for just L=letter digit
		if(!Character.isLetterOrDigit(e.getKeyChar())) {
			e.consume();
		}
		if(e.getSource().equals(fCheckNo)) {	//	For Check
			setReferenceNo(fCheckNo.getText());
		} else if(e.getSource().equals(fCheckRoutingNo)) {
			setRoutingNo(fCheckRoutingNo.getText());
		} else if(e.getSource().equals(fCheckDate)) {
			setDateTrx(fCheckDate.getTimestamp());
		} else if(e.getSource().equals(fDebitRoutingNo)) {	//	For Debit Card
			setRoutingNo(fDebitRoutingNo.getText());
		} else if(e.getSource().equals(fDebitCVC)) {
			setCreditCardVV(fDebitCVC.getText());
		} else if(e.getSource().equals(fDebitCountry)) {
			setA_Country(fDebitCountry.getText());
		} else if(e.getSource().equals(fCreditCardNumber)) {	//	For Credit Card
			setCreditCardNumber(fCreditCardNumber.getText());
		} else if(e.getSource().equals(fA_Name)) {
			setA_Name(fA_Name.getText());
		} else if(e.getSource().equals(fCreditCardVV)) {
			setCreditCardVV(fCreditCardVV.getText());
		} else {	//	TODO Add validation with name, it is resolved when implement KeyListener in VNumber
			setPayAmt((BigDecimal) fPayAmt.getValue());
		}
		//	Refresh
		v_Parent.refreshPanel();
	}

	@Override
	public void refreshPanel() {
		
	}

	@Override
	public String validatePanel() {
		
		return null;
	}

	/**
	 * Request Focus in Payment Amount Field
	 * @return void
	 */
	public void requestFocusInPayAmt() {
		fPayAmt.requestFocus();
	}
	
	@Override
	public void changeViewPanel() {
		String p_TenderType = getTenderType();
		//	Valid Null
		if(p_TenderType == null)
			return;
		//	Change Title
		String m_DisplayTenderType = fTenderType.getDisplay();
		v_TitleBorder.setTitle(m_DisplayTenderType);
		//	
		if(p_TenderType.equals(X_C_Payment.TENDERTYPE_Check)){
			v_CheckPanel.setVisible(true);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_DirectDebit)){
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(true);
			v_CreditPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_CreditCard)){
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(true);
		} else {
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
		}
	}
}