package org.adempiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.pos.service.CollectDetail;
import org.adempiere.pos.service.I_POSPanel;
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
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.X_C_Payment;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Style;

public class WCollectDetail extends CollectDetail implements EventListener, I_POSPanel {
	
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
	private WPosTextField 	fCheckRouteNo;
	private WPosTextField 	fCheckNo;
	private Label 			lCheckNo;
	private Label 			lCheckRouteNo;
	
	/**	Credit Card			*/
	private WPosTextField 	fCCardNo;
	private WPosTextField 	fCCardName;
	private Listbox 		fCCardType;
	private Listbox 		fCreditCardExpMM;
	private Listbox 		fCreditCardExpYY;
	private WPosTextField 	fCCardVC;
	private Label 			lCCardNo;
	private Label 			lCCardName;
	private Label 			lCCardType;
	private Label 			lCCardVC;
	
	/**	Debit Card			*/
	private WPosTextField 	fDebitRoutingNo;
	private WPosTextField 	fDebitCVC;
	private WPosTextField 	fDebitCountry;
	private Label 			lDebitRoutingNo;
	private Label 			lDebitCVC;
	private Label 			lDebitCountry;
	private boolean			isKeyboard;
	private Button 			bMinus;
	private Panelchildren 	v_PanelChildren;
	
	private final String FONT_SIZE = "Font-size:medium;";
	private final String HEIGHT = "height:33px;";
	private final String WIDTH = "width:149px;";
	private WCollect v_Parent;
	/**	Panels				*/
	private Panel 			v_MainPanel;
	private Caption v_TitleBorder;
	private Groupbox groupPanel;
	
	public WCollectDetail(WCollect p_WCollect, String p_TenderType, BigDecimal m_PayAmt) {
		super(p_TenderType, m_PayAmt);
		m_TenderType = p_TenderType;
		p_ctx = Env.getCtx();
		//	Instance POS
		v_Parent = p_WCollect;
		init();
	}
	
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
	}
	
	public void loadCheckPanel(){
		
		v_CheckPanel = GridFactory.newGridLayout();
		v_CheckPanel.setWidth("100%");
		v_CheckPanel.setHeight("95px");
		groupPanel.appendChild(v_CheckPanel);
		
		Rows rows =v_CheckPanel.newRows();
		Row row = rows.newRow();

		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		fCheckRouteNo = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(fCheckRouteNo);
		fCheckRouteNo.setValue(lCheckRouteNo.getValue());
		fCheckRouteNo.addEventListener("onFocus", this);
		fCheckRouteNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		
		row.appendChild(fCheckRouteNo);

		lCheckNo = new Label(Msg.translate(p_ctx, "CheckNo"));
		fCheckNo = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		fCheckNo.setValue(lCheckNo.getValue());
		fCheckNo.addEventListener("onFocus", this);
		fCheckNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fCheckNo);
		
		fCheckdate = new Datebox();
		fCheckdate.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row = rows.newRow();
		fCheckdate.addEventListener("onFocus", this);
		row.appendChild(fCheckdate);

	}

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
		
		lCCardType = new Label(Msg.translate(p_ctx, "CreditCardType"));
		row = rows.newRow();
		fCCardType = ListboxFactory.newDropdownListbox();
		row.appendChild(fCCardType);
		fCCardType.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fCCardType.setValue(lCCardType.getValue());
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
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		fCCardNo = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		fCCardNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fCCardNo);
		fCCardNo.setText(lCCardNo.getValue());
		fCCardNo.addEventListener("onFocus", this);
		
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		fCCardName = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		row = rows.newRow();
		row.appendChild(fCCardName);
		fCCardName.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fCCardName.setValue(lCCardName.getValue());
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
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		fCCardVC = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		row = rows.newRow();
		
		row.appendChild(fCCardVC);
		fCCardVC.setValue(lCCardVC.getValue());
		fCCardVC.addEventListener("onFocus", this);
		fCCardVC.setStyle(HEIGHT+WIDTH+FONT_SIZE);

	}
	public void loadDebitPanel(){
		v_DebitPanel = GridFactory.newGridLayout();
		v_DebitPanel.setWidth("100%");
		v_DebitPanel.setHeight("95px");
		
		Rows rows = v_DebitPanel.newRows();
		Row row = rows.newRow();

		row.setSpans("1,2");
		lDebitRoutingNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		fDebitRoutingNo = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		fDebitRoutingNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fDebitRoutingNo);
		fDebitRoutingNo.setText(lDebitRoutingNo.getValue());
		fDebitRoutingNo.addEventListener("onFocus", this);
		
		lDebitCVC = new Label(Msg.translate(p_ctx, "A_Country"));
		fDebitCVC = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		row.appendChild(fDebitCVC);
		fDebitCVC.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fDebitCVC.setValue(lDebitCVC.getValue());
		fDebitCVC.addEventListener("onFocus", this);

		lDebitCountry = new Label(Msg.translate(p_ctx, "R_CVV2Match"));
		fDebitCountry = new WPosTextField(v_Parent.v_POSPanel.getOSKeyLayout_ID());
		row = rows.newRow();
		
		row.appendChild(fDebitCountry);
		fDebitCountry.setValue(lDebitCountry.getValue());
		fDebitCountry.addEventListener("onFocus", this);
		fDebitCountry.setStyle(HEIGHT+WIDTH+FONT_SIZE);

	}

	public void clear(){
		v_StandarPanel = null;
	}

	public void setTenderType(String p_TenderType) {
		m_TenderType = p_TenderType;
	}
	
	public String getTenderType() {
		return m_TenderType;
	}
	
	
	public POSNumberBox getlPayAmt(){
		return fPayAmt;
	}
	
	public void showKeyboard(WPosTextField field, Label label) {
		isKeyboard = true;
		if(field.getText().equals(label.getValue()))
			field.setValue("");
		WPOSKeyboard keyboard =  v_Parent.v_POSPanel.getKeyboard(field.getKeyLayoutId()); 
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
		}
		if(e.getTarget().equals(fTenderType)) {
			String m_TenderType =  ((ValueNamePair) fTenderType.getValue()).getID();
			setTenderType(m_TenderType);
			changeViewPanel();
		}else if(e.getTarget().equals(fCheckdate)){
			//	TODO add support to controller to be define
//			dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String hourString = dateFormat.format(fCheckdate.getValue());
//			Timestamp dateTrx = Timestamp.valueOf(hourString);
//			setDateTrx(dateTrx);
		}
		else if(e.getName().equals(Events.ON_FOCUS)){
			if(e.getTarget().equals(fCheckNo.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				 showKeyboard(fCheckNo,lCheckNo);
				 setReferenceNo(fCheckNo.getText());
				 fCheckNo.setFocus(true);
			}
			else if(e.getTarget().equals(fCheckNo.getComponent(WPosTextField.PRIMARY))){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fCheckRouteNo.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fCheckRouteNo,lCheckRouteNo);
				setRoutingNo(fCheckRouteNo.getText());
				fCheckRouteNo.setFocus(true);
			}
			else if(e.getTarget().equals(fCheckRouteNo.getComponent(WPosTextField.PRIMARY))){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fDebitRoutingNo.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fDebitRoutingNo,lDebitRoutingNo);
				setRoutingNo(fDebitRoutingNo.getText());
				fDebitRoutingNo.setFocus(true);
			}
			else if(e.getTarget().equals(fDebitRoutingNo.getComponent(WPosTextField.PRIMARY))){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fDebitCVC.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fDebitCVC,lDebitCVC);
				fDebitCVC.setFocus(true);
			}
			else if(e.getTarget().equals(fDebitCVC.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fDebitCountry.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fDebitCountry,lDebitCountry);
				setA_Country(fDebitCountry.getText());
				fDebitCountry.setFocus(true);
			}
			else if(e.getTarget().equals(fDebitCountry.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fCCardNo.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fCCardNo,lCCardNo);
				setCreditCardNumber(fCCardNo.getText());
				fCCardNo.setFocus(true);
			}
			else if(e.getTarget().equals(fCCardNo.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fCCardName.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fCCardName,lCCardName);
				setA_Name(fCCardName.getText());
				fCCardName.setFocus(true);
			}
			else if(e.getTarget().equals(fCCardName.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
			else if(e.getTarget().equals(fCCardVC.getComponent(WPosTextField.SECONDARY)) && !isKeyboard) {
				showKeyboard(fCCardVC,lCCardVC);
				setCreditCardVV(fCCardVC.getText());
				fCCardVC.setFocus(true);
			}
			else if(e.getTarget().equals(fCCardVC.getComponent(WPosTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
		}else if(e.getTarget().equals(fCCardType)) {
			setCreditCardType((String) fCCardType.getValue());
		}
		else {
			setPayAmt((BigDecimal) fPayAmt.getValue());
			v_Parent.refreshPanel();
		}

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
		//	Add to Main Panel
		groupPanel.appendChild(v_StandarPanel);
		groupPanel.appendChild(v_CheckPanel);
		groupPanel.appendChild(v_CreditPanel);
		groupPanel.appendChild(v_DebitPanel);

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
	public String validatePanel() {
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