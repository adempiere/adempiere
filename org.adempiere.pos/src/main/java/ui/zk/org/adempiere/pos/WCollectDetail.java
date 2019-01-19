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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Raul Muñoz www.erpcya.com					              *
 *****************************************************************************/

package org.adempiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.pos.service.CollectDetail;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.X_C_Payment;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Style;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raúl Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li>Change Name, Best practices
 */
public class WCollectDetail extends CollectDetail implements EventListener, POSPanelInterface {
	
	/**
	 * Standard Constructor
	 * @param p_WCollect
	 * @param p_TenderType
	 * @param m_PayAmt
	 */
	public WCollectDetail(WCollect p_WCollect, String p_TenderType, BigDecimal m_PayAmt) {
		super(p_TenderType, m_PayAmt);
		m_TenderType = p_TenderType;
		p_ctx = Env.getCtx();
		//	Instance POS
		v_Parent = p_WCollect;
		keyboard = v_Parent.getKeyboard();
		init();
	}
	
	/**	Panels				*/
	private String 			m_TenderType;
	private Grid 			v_StandarPanel;
	private Grid 			v_CheckPanel;
	private Grid 			v_CreditPanel;
	private Grid 			v_DebitPanel;
	private Properties 		p_ctx;
	private Listbox 		fTenderType;
	public POSNumberBox 	fPayAmt;
	
	/**	Check				*/
	private Datebox 		fCheckdate;
	private WPOSTextField 	fCheckRouteNo;
	private WPOSTextField 	fCheckNo;
	
	/**	Credit Card			*/
	private WPOSTextField 	fCCardNo;
	private WPOSTextField 	fCCardName;
	private Listbox 		fCCardType;
	private Listbox 		fCreditCardExpMM;
	private Listbox 		fCreditCardExpYY;
	private WPOSTextField 	fCCardVC;
	
	/**	Credit Note			*/
	private Grid			v_CreditMemoPanel;
	private Listbox 		fCreditMemo;
	private Label			lCreditMemo;
	
	/**	Debit Card			*/
	private WPOSTextField 	fDebitRoutingNo;
	private WPOSTextField 	fDebitCVC;
	private WPOSTextField 	fDebitCountry;
	private boolean			isKeyboard;
	private Button 			bMinus;
	private Panelchildren 	v_PanelChildren;
	
	/**	Keyboard to use		*/
	private WPOSKeyboard 	keyboard;
	/**	Default Font		*/
	private final String 	FONT_SIZE = "Font-size:medium;";
	/**	Default Width		*/
	private final String 	HEIGHT = "height:33px;";
	/**	Default Height		*/
	private final String 	WIDTH = "width:149px;";
	
	private WCollect 		v_Parent;
	/**	Panels				*/
	private Panel 			v_MainPanel;
	private Caption 		v_TitleBorder;
	private Groupbox 		groupPanel;
	
	/**
	 * Load standard Panel
	 * @return void
	 */
	public void loadStandardPanel(){
		v_StandarPanel = GridFactory.newGridLayout();
		v_StandarPanel.setWidth("100%");
		v_StandarPanel.setHeight("75px");
		groupPanel.appendChild(v_StandarPanel);

		Rows rows = null;
		Row row = null;
		rows = v_StandarPanel.newRows();
		row = rows.newRow();
		
		// Payment type selection
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> types = lookup.getData(true, false, true, true);
		
		bMinus = v_Parent.createButtonAction("Minus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		bMinus.addActionListener(this);
		row.setHeight("55px");
		
		fTenderType = ListboxFactory.newDropdownListbox();
		fTenderType.addActionListener(this);
		int pos = 0;
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				fTenderType.appendItem(key.getName(), key);
					
				if ( key.getID().equals(getTenderType())){
					fTenderType.setSelectedIndex(pos); 
				}
				pos++;
			}
		}

		fTenderType.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		
		row.appendChild(fTenderType);
		
		Label lPayAmt  = new Label(Msg.translate(p_ctx, "PayAmt"));
		lPayAmt.setWidth("225px");
		fPayAmt = new POSNumberBox(false);
		
		row.appendChild(fPayAmt);
		row.appendChild(bMinus);
		fPayAmt.setValue(new BigDecimal("0.0"));
		fPayAmt.setStyle("text-align:right;"+HEIGHT+WIDTH+FONT_SIZE);
		fPayAmt.addEventListener("onBlur",this);
		fPayAmt.addEventListener(Events.ON_CHANGING,this);
		fPayAmt.addEventListener(Events.ON_CHANGE,this);
	}
	
	/**
	 * Load Check Panel
	 * @return void
	 */
	public void loadCheckPanel(){
		
		v_CheckPanel = GridFactory.newGridLayout();
		v_CheckPanel.setWidth("100%");
		v_CheckPanel.setHeight("95px");
		groupPanel.appendChild(v_CheckPanel);
		
		Rows rows =v_CheckPanel.newRows();
		Row row = rows.newRow();

		fCheckRouteNo = new WPOSTextField(Msg.translate(p_ctx, "RoutingNo"), keyboard);
		row.appendChild(fCheckRouteNo);
		fCheckRouteNo.addEventListener("onFocus", this);
		fCheckRouteNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		
		row.appendChild(fCheckRouteNo);

		fCheckNo = new WPOSTextField(Msg.translate(p_ctx, "CheckNo"), keyboard);
		fCheckNo.addEventListener("onFocus", this);
		fCheckNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fCheckNo);
		
		fCheckdate = new Datebox();
		fCheckdate.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row = rows.newRow();
		fCheckdate.addEventListener("onFocus", this);
		row.appendChild(fCheckdate);

	}

	/**
	 * Load Credit Panel
	 * @return void
	 */
	public void loadCreditPanel(){
		v_CreditPanel = GridFactory.newGridLayout();
		v_CreditPanel.setWidth("100%");
		v_CreditPanel.setHeight("175px");
		
		Rows rows = v_CreditPanel.newRows();
		Row row = rows.newRow();

		row.setStyle("303px");
		
		int AD_Column_ID = 8374; //C_Payment_v.TenderType
		MLookup cardlookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> cards = cardlookup.getData(true, false, true, true);
		
		row = rows.newRow();
		fCCardType = ListboxFactory.newDropdownListbox();
		row.appendChild(fCCardType);
		fCCardType.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fCCardType.setValue(Msg.translate(p_ctx, "CreditCardType"));
		fCCardType.addActionListener(this);
		
		/**
		 *	Load Credit Cards
		 */
		for (Object obj : cards) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				fCCardType.appendItem(key.getName(), key.getID());
			}
		}
		
		row.setSpans("1,2");
		fCCardNo = new WPOSTextField(Msg.translate(p_ctx, "CreditCardNumber"), keyboard);
		fCCardNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fCCardNo);
		fCCardNo.addEventListener("onFocus", this);
		
		fCCardName = new WPOSTextField(Msg.translate(p_ctx, "Name"), keyboard);
		row = rows.newRow();
		row.appendChild(fCCardName);
		fCCardName.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fCCardName.addEventListener("onFocus", this);
		
		//	For Card Month
		fCreditCardExpMM = ListboxFactory.newDropdownListbox();
		ValueNamePair[] data = getCCMonths();
		for(ValueNamePair pp : data) {
			fCreditCardExpMM.appendItem(String.valueOf(pp.getName()),pp.getID());
			fCreditCardExpMM.setName(String.valueOf(pp.getID()));
		}
		fCreditCardExpMM.setName("CreditCardExpMM");
		fCreditCardExpMM.addActionListener(this);
		fCreditCardExpMM.setStyle(HEIGHT+"width:"+75+"px;"+FONT_SIZE);
		
		//	For Card Year
		fCreditCardExpYY = ListboxFactory.newDropdownListbox();
		data = getCCYears();
		for(ValueNamePair pp : data) {
			fCreditCardExpYY.appendItem(String.valueOf(pp.getName()),pp.getID());
			fCreditCardExpYY.setName(String.valueOf(pp.getID()));
		}
		fCreditCardExpYY.setName("CreditCardExpYY");
		fCreditCardExpYY.addActionListener(this);
		fCreditCardExpYY.setStyle("margin: 0px 50px 0px 0px;"+HEIGHT+"width:"+60+"px;"+FONT_SIZE);
			
		//	For Card VV
		row.appendChild(fCreditCardExpMM);
		row.appendChild(fCreditCardExpYY);
		fCCardVC = new WPOSTextField(Msg.translate(p_ctx, "CVC"), keyboard);
		row = rows.newRow();
		
		row.appendChild(fCCardVC);
		fCCardVC.addEventListener("onFocus", this);
		fCCardVC.setStyle(HEIGHT+WIDTH+FONT_SIZE);

	}
	
	/**
	 * Load Debit Panel
	 * @return void
	 */
	public void loadDebitPanel(){
		v_DebitPanel = GridFactory.newGridLayout();
		v_DebitPanel.setWidth("100%");
		v_DebitPanel.setHeight("95px");
		
		Rows rows = v_DebitPanel.newRows();
		Row row = rows.newRow();

		row.setSpans("1,2");
		fDebitRoutingNo = new WPOSTextField(Msg.translate(p_ctx, "RoutingNo"), keyboard);
		fDebitRoutingNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fDebitRoutingNo);
		fDebitRoutingNo.addEventListener("onFocus", this);
		
		fDebitCVC = new WPOSTextField(Msg.translate(p_ctx, "A_Country"), keyboard);
		row.appendChild(fDebitCVC);
		fDebitCVC.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fDebitCVC.addEventListener("onFocus", this);

		fDebitCountry = new WPOSTextField(Msg.translate(p_ctx, "R_CVV2Match"), keyboard);
		row = rows.newRow();
		
		row.appendChild(fDebitCountry);
		fDebitCountry.addEventListener("onFocus", this);
		fDebitCountry.setStyle(HEIGHT+WIDTH+FONT_SIZE);

	}

	/**
	 * Load for Credit Memo
	 * @return void
	 */
	private void loadCreditMemoPanel() {
		v_CreditMemoPanel = GridFactory.newGridLayout();
		v_CreditMemoPanel.setWidth("100%");
		v_CreditMemoPanel.setHeight("95px");
		
		Rows rows = v_CreditMemoPanel.newRows();
		Row row = rows.newRow();

		row.setSpans("1,2");
		//	Add label credit note
		lCreditMemo = new Label(Msg.translate(Env.getCtx(), "CreditMemo") + ":");
		lCreditMemo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(lCreditMemo);
		
		MLookup lookup = getCreditMemoLockup(v_Parent.getC_BPartner_ID());
		ArrayList<Object> types = lookup.getData(false, false, true, true);
		
		row = rows.newRow();
		fCreditMemo = ListboxFactory.newDropdownListbox();
		row.appendChild(fCreditMemo);
		fCreditMemo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fCreditMemo.setValue(Msg.translate(p_ctx, "CreditMemoType"));
		fCreditMemo.addActionListener(this);
		
		/**
		 *	Load Credit Notes
		 */
		for (Object obj : types) {
			if ( obj instanceof KeyNamePair )	{
				KeyNamePair key = (KeyNamePair) obj;
				fCreditMemo.appendItem(key.getName(), key.getID());
			}
		}
		
	}
	
	
	/**
	 * Clear Panel
	 * @return void
	 */
	public void clear(){
		v_StandarPanel = null;
	}

	/**
	 * Set Tender Type
	 */
	public void setTenderType(String p_TenderType) {
		m_TenderType = p_TenderType;
	}
	
	/**
	 * Get Tender Type
	 */
	public String getTenderType() {
		return m_TenderType;
	}
	
	/**
	 * Get Payment Amount
	 * @return
	 * @return POSNumberBox
	 */
	public POSNumberBox getlPayAmt(){
		return fPayAmt;
	}
	
	/**
	 * Show Keyboard
	 * @param field
	 * @param label
	 * @return void
	 */
	public void showKeyboard(WPOSTextField field, Label label) {
		isKeyboard = true;
		if(field.getText().equals(label.getValue()))
			field.setValue("");
		WPOSKeyboard keyboard = field.getKeyboard();
		keyboard.setWidth("750px");
		keyboard.setHeight("380px");
		keyboard.setPosTextField(field);	
		AEnv.showWindow(keyboard);
		if(field.getText().equals("")) 
			field.setValue(label.getValue());
		
	}
		
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event e) throws Exception {
		if(e.getTarget().equals(bMinus)){
			v_Parent.removeCollectDetail(this);
		} else if(e.getTarget().equals(fTenderType)) {
			String m_TenderType =  ((ValueNamePair) fTenderType.getValue()).getID();
			setTenderType(m_TenderType);
			changeViewPanel();
			fPayAmt.setValue(getInitPayAmt());
			setPayAmt((BigDecimal) fPayAmt.getValue());
			v_Parent.refreshPanel();
		} else if(e.getTarget().equals(fCheckdate)){
			//	TODO add support to controller to be define
//			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String hourString = dateFormat.format(fCheckdate.getValue());
//			Timestamp dateTrx = Timestamp.valueOf(hourString);
//			setDateTrx(dateTrx);
		} else if(e.getTarget().equals(fCreditMemo)) {
			int m_C_Invoice_ID = Integer.valueOf(fCreditMemo.getValue().toString());
			setC_Invoice_ID(m_C_Invoice_ID);
			setPayAmt(getInitPayAmt());
			v_Parent.refreshPanel();
			fPayAmt.setValue(getOpenAmtCreditMemo());
			
			setPayAmt((BigDecimal) fPayAmt.getValue());
			v_Parent.refreshPanel();
		} else if(e.getName().equals(Events.ON_FOCUS)){
			if(e.getTarget().equals(fCheckNo.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fCheckNo.showKeyboard();
				setReferenceNo(fCheckNo.getText());
				fCheckNo.setFocus(true);
			} else if(e.getTarget().equals(fCheckNo.getComponent(WPOSTextField.PRIMARY))) {
				isKeyboard = false;
			} else if(e.getTarget().equals(fCheckRouteNo.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fCheckRouteNo.showKeyboard();
				setRoutingNo(fCheckRouteNo.getText());
				fCheckRouteNo.setFocus(true);
			} else if(e.getTarget().equals(fCheckRouteNo.getComponent(WPOSTextField.PRIMARY))){
				isKeyboard = false;
			} else if(e.getTarget().equals(fDebitRoutingNo.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fDebitRoutingNo.showKeyboard();
				setRoutingNo(fDebitRoutingNo.getText());
				fDebitRoutingNo.setFocus(true);
			} else if(e.getTarget().equals(fDebitRoutingNo.getComponent(WPOSTextField.PRIMARY))) {
				isKeyboard = false;
			} else if(e.getTarget().equals(fDebitCVC.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fDebitCVC.showKeyboard();
				fDebitCVC.setFocus(true);
			} else if(e.getTarget().equals(fDebitCVC.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)) {
				isKeyboard = false;
			} else if(e.getTarget().equals(fDebitCountry.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fDebitCountry.showKeyboard();
				setA_Country(fDebitCountry.getText());
				fDebitCountry.setFocus(true);
			} else if(e.getTarget().equals(fDebitCountry.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)) {
				isKeyboard = false;
			} else if(e.getTarget().equals(fCCardNo.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fCCardNo.showKeyboard();
				setCreditCardNumber(fCCardNo.getText());
				fCCardNo.setFocus(true);
			} else if(e.getTarget().equals(fCCardNo.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)) {
				isKeyboard = false;
			} else if(e.getTarget().equals(fCCardName.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fCCardName.showKeyboard();
				setA_Name(fCCardName.getText());
				fCCardName.setFocus(true);
			} else if(e.getTarget().equals(fCCardName.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)) {
				isKeyboard = false;
			} else if(e.getTarget().equals(fCCardVC.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard) {
				isKeyboard = true;
				fCCardVC.showKeyboard();
				setCreditCardVV(fCCardVC.getText());
				fCCardVC.setFocus(true);
			} else if(e.getTarget().equals(fCCardVC.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)) {
				isKeyboard = false;
			}
		} else if(e.getTarget().equals(fCCardType)) {
			setCreditCardType((String) fCCardType.getValue());
		}
		else {
			String p_TenderType = getTenderType();
			BigDecimal payAmt = (BigDecimal)fPayAmt.getValue();
			
			if(Events.ON_CHANGE.equals(e.getName()) || e.getName().equals(Events.ON_CHANGING)) {
				if(((InputEvent)e).getValue().length() > 0)
					payAmt = new BigDecimal(((InputEvent)e).getValue());
				else 
					payAmt = Env.ZERO;

				if(p_TenderType.equals(X_C_Payment.TENDERTYPE_CreditMemo)
						&& payAmt.compareTo(getOpenAmtCreditMemo()) > 0 
						&& fCreditMemo.getSelectedIndex() > 0) {
					FDialog.warn(0, Msg.parseTranslation(p_ctx, "POS.MaxAmountAllowed")+":"+getOpenAmtCreditMemo());
					fPayAmt.setValue(getOpenAmtCreditMemo());
				}
				setPayAmt(payAmt);
			}
		}
		v_Parent.refreshPanel();
		setCreditCardExpMM((String)fCreditCardExpMM.getValue());
		setCreditCardExpYY((String)fCreditCardExpYY.getValue());
		
	}
	
	/**
	 * Init Main Panel
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	private void init() {
		v_MainPanel = new Panel();
		v_PanelChildren = new Panelchildren();

		groupPanel = new Groupbox();

		v_PanelChildren.appendChild(groupPanel);

		v_TitleBorder = new Caption("Credit Card");
		Style style = new Style();
		style.setContent(".z-fieldset legend {font-size: medium; font-weight:bold;}");
		style.setParent(v_TitleBorder);
		groupPanel.appendChild(v_TitleBorder);
		v_MainPanel.appendChild(v_PanelChildren);
		
		//	Load Standard Panel
		loadStandardPanel();
		//	Load Check Panel
		loadCheckPanel();
		//	Load Credit Panel
		loadCreditPanel();
		//	Load Debit Panel
		loadDebitPanel();
		//	Load Credit Note Panel
		loadCreditMemoPanel();
			
		//	Add to Main Panel
		groupPanel.appendChild(v_StandarPanel);
		groupPanel.appendChild(v_CheckPanel);
		groupPanel.appendChild(v_CreditPanel);
		groupPanel.appendChild(v_DebitPanel);
		groupPanel.appendChild(v_CreditMemoPanel);

	//  Change View
		changeViewPanel();
		fPayAmt.setValue(getPayAmt());
		
	}
	
	/**
	 * Get Main Panel
	 * @return Panel
	 */
	public Groupbox getPanel() {
		return groupPanel;
	}

	@Override
	public void refreshPanel() {
		
	}

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		String p_TenderType = getTenderType();
		//	Valid Null
		if(p_TenderType == null)
			return;
		//	Change Title
		String m_DisplayTenderType = ((ValueNamePair)fTenderType.getValue()).getName();
		v_TitleBorder.setLabel(m_DisplayTenderType);
		//	
		if(p_TenderType.equals(X_C_Payment.TENDERTYPE_Check)){
			v_CheckPanel.setVisible(true);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
			v_CreditMemoPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_DirectDebit)){
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(true);
			v_CreditPanel.setVisible(false);
			v_CreditMemoPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_CreditCard)){
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(true);
			v_CreditMemoPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_CreditMemo)){
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
			v_CreditMemoPanel.setVisible(true);
		} else {
			v_CheckPanel.setVisible(false);
			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
			v_CreditMemoPanel.setVisible(false);
		}
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}
}