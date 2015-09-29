package org.adempiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.pos.service.CollectDetail;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.X_C_Payment;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Space;

public class WCollectDetail extends CollectDetail implements EventListener, I_POSPanel {
	
	private String m_TenderType;
	private Div v_Div; 
	private Grid v_StandarPanel;
	private Grid v_CheckPanel;
	private Grid v_CreditPanel;
	private Properties p_ctx;

	private Listbox fTenderType;
	private Listbox bankList;
	public POSNumberBox fPayAmt;
	private WPosTextField fCheckAccountNo;
	private Textbox fCheckdate;
	private WPosTextField fCheckRouteNo;
	private WPosTextField fCCardNo;
	private WPosTextField fCCardName;
	private Listbox fCCardType;
	private WPosTextField fCCardMonth;
	private WPosTextField fCCardVC;
	private WPosTextField fCheckNo;
	private Button bMinus;

	private Label lCheckNo;
	private Label lCheckAccountNo;
	private Label lCheckRouteNo;
	private Label lCCardNo;
	private Label lCCardName;
	private Label lCCardType;
	private Label lCCardMonth;
	private Label lCCardVC;
	private int cont;
	private Borderlayout mainLayout;
	private Panelchildren v_PanelChildren;
	
	private final String FONT_SIZE = "Font-size:medium;";
	private final String HEIGHT = "height:33px;";
	private final String WIDTH = "width:139px;";
	private WCollect v_Parent;
	private WPOSKeyboard keyboard;
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
		keyboard = v_Parent.getKeyboard();
		init();
	}
	
	public void loadStandardPanel(){
		v_StandarPanel = GridFactory.newGridLayout();
		v_StandarPanel.setWidth("100%");
		v_StandarPanel.setHeight("75px");
		groupPanel.appendChild(v_StandarPanel);

		mainLayout = new Borderlayout();

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
				
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
					fTenderType.appendItem(key.getName(), key);
			}
		}

		fTenderType.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fTenderType.addActionListener(this);
		row.appendChild(fTenderType);
		

		Label lPayAmt  = new Label(Msg.translate(p_ctx, "PayAmt"));
		lPayAmt.setWidth("225px");
		fPayAmt = new POSNumberBox(false);
		
		row.appendChild(fPayAmt);

		row.appendChild(bMinus);
		fPayAmt.setValue(new BigDecimal("0.0"));
		fPayAmt.setStyle("text-align:right;"+HEIGHT+WIDTH+FONT_SIZE);
		fPayAmt.addEventListener("onBlur",this);
		fPayAmt.addEventListener("onChange",this);
		fPayAmt.addEventListener("onFocus",this);
	}
	
	public void loadCheckPanel(){
		
		v_CheckPanel = GridFactory.newGridLayout();
		v_CheckPanel.setWidth("100%");
		v_CheckPanel.setHeight("95px");
		groupPanel.appendChild(v_CheckPanel);
		
		Rows rows = null;
		Row row = null;
		rows = v_CheckPanel.newRows();
		row = rows.newRow();
		
				
		row = rows.newRow();
		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		fCheckRouteNo = new WPosTextField(lCheckRouteNo.getValue(), keyboard);
		row.appendChild(fCheckRouteNo);
		fCheckRouteNo.setValue(lCheckRouteNo.getValue());
		fCheckRouteNo.addEventListener("onFocus", this);
		fCheckRouteNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		
		row.appendChild(fCheckRouteNo);

		lCheckNo = new Label(Msg.translate(p_ctx, "CheckNo"));
		fCheckNo = new WPosTextField(lCheckRouteNo.getValue(), keyboard);
		fCheckNo.setValue(lCheckRouteNo.getValue());
		fCheckNo.addEventListener("onFocus", this);
		fCheckNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fCheckNo);
		
		fCheckdate = new Textbox("MM/DD/YYYY");
		fCheckdate.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row = rows.newRow();
		fCheckdate.addEventListener("onFocus", this);
		row.appendChild(fCheckdate);

	}

	public void loadCreditPanel(){
		v_CreditPanel = GridFactory.newGridLayout();
		v_CreditPanel.setWidth("100%");
		v_CreditPanel.setHeight("175px");
		
		
		Rows rows = null;
		Row row = null;
		rows = v_CreditPanel.newRows();
		row = rows.newRow();

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
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		fCCardNo = new WPosTextField(lCCardNo.getValue(), keyboard);
		fCCardNo.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		row.appendChild(fCCardNo);
		fCCardNo.setText(lCCardNo.getValue());
		fCCardNo.addEventListener("onFocus", this);
		
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		fCCardName = new WPosTextField(lCCardName.getValue(), keyboard);
		row = rows.newRow();
		row.appendChild(fCCardName);
		fCCardName.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		fCCardName.setValue(lCCardName.getValue());
		fCCardName.addEventListener("onFocus", this);
		
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		fCCardMonth = new WPosTextField(lCCardMonth.getValue(), keyboard);
		row.appendChild(fCCardMonth);
		fCCardMonth.setValue(lCCardMonth.getValue());
		fCCardMonth.addEventListener("onFocus", this);
		fCCardMonth.setStyle(HEIGHT+WIDTH+FONT_SIZE);
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		fCCardVC = new WPosTextField(lCCardVC.getValue(), keyboard);
		row = rows.newRow();
		
		row.appendChild(fCCardVC);
		fCCardVC.setValue(lCCardVC.getValue());
		fCCardVC.addEventListener("onFocus", this);
		fCCardVC.setStyle(HEIGHT+WIDTH+FONT_SIZE);
//		setTotals();

	}

	public ValueNamePair[] getBank(){
		return DB.getValueNamePairs("SELECT C_Bank_ID, Name FROM C_Bank", true, null);
	}
	public boolean savePay(){
//		BigDecimal payAmt = fPayAmt.getValue();
//		
////		if(m_TenderType.equals(MPayment.TENDERTYPE_Cash))
////			addCash(payAmt);
////		else
//			if(m_TenderType.equals(MPayment.TENDERTYPE_Check)) {
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//			String strDate = fCheckdate.getValue();
//		    Timestamp dateTrx = null;
//			try{
//			    Date parsedDate = dateFormat.parse(strDate);
//			    dateTrx = new Timestamp(parsedDate.getTime());
//			}catch(Exception e){
//				e.printStackTrace(); 
//			}
//
//			String bank_ID = ((ValueNamePair) bankList.getSelectedItem().toValueNamePair()).getValue();
//			
//			String routeNo = fCheckRouteNo.getValue();
//			
////			addCheck(payAmt, routeNo, Integer.parseInt(bank_ID), dateTrx);
//		}
//		else if(m_TenderType.equals(MPayment.TENDERTYPE_CreditCard)){
//
//			int month = MPaymentValidate.getCreditCardExpMM(fCCardMonth.getText());
//			int year = MPaymentValidate.getCreditCardExpYY(fCCardMonth.getText());
//			String cardNo = fCCardNo.getValue();
//			String cardCVC = fCCardVC.getValue();
//			String cardType = ((ValueNamePair) fCCardType.getSelectedItem().toValueNamePair()).getValue();
			
//			payCreditCard(payAmt, fCCardName.getValue(), month, year, cardNo, cardCVC, cardType);
//		}
			
//		processPayment();
		return true;
	}
	/**
	 * Change view from tender type
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_TenderType
	 * @return void
	 */
	private void changeView() {
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
			v_CreditPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_DirectDebit)){
			v_CheckPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_CreditCard)){
			v_CheckPanel.setVisible(false);
			v_CreditPanel.setVisible(true);
		} else {
			v_CheckPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
		}
	}

	public void clear(){
		mainLayout = null;
		v_StandarPanel = null;
	}
	public void setTenderType(String p_TenderType) {
		m_TenderType = p_TenderType;
	}
	
	public String getTenderType() {
		return m_TenderType;
	}
	public BigDecimal getPayAmt(){
		return fPayAmt.getValue();
	}
	public POSNumberBox getlPayAmt(){
		return fPayAmt;
	}
	
	public void showKeyboard(WPosTextField field) {
		cont++;
		if(cont==1){
//				WPOSKeyboard keyboard =  p_posBasePanel.getKeyboard(field.getKeyLayoutId()); 
//				keyboard.setWidth("750px");
//				keyboard.setHeight("380px");
//				keyboard.setPosTextField(field);	
//				AEnv.showWindow(keyboard);
			}
		else {
			cont=0;
//			mainPanel.setFocus(true);
		}
	
	}
		
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event e) throws Exception {
		System.out.println(e.getTarget().getId()+" "+fPayAmt.getId());
		
		if(e.getTarget().equals(bMinus)){
			v_Parent.removeCollectDetail(this);
		}
		if(e.getTarget().equals(fTenderType)) {
			String m_TenderType =  ((ValueNamePair) fTenderType.getValue()).getID();
			setTenderType(m_TenderType);
			changeViewPanel();
		}
		else if(e.getName().equals("onFocus")){
		
		}else if(e.getTarget().equals(fCheckAccountNo)){
			fCheckAccountNo.setValue("");
			showKeyboard(fCheckAccountNo);
		}
		
		else if(e.getTarget().equals(fCheckdate)){
			fCheckdate.setValue("");
			
		}
		else if(e.getTarget().equals(fCheckRouteNo)){
			fCheckRouteNo.setValue("");
			showKeyboard(fCheckRouteNo);
		}
		else if(e.getTarget().equals(fCCardNo)){
			fCCardNo.setValue("");
			showKeyboard(fCCardNo);
		}
		else if(e.getTarget().equals(fCCardName)){
			fCCardName.setValue("");
			showKeyboard(fCCardName);
		}
		else if(e.getTarget().equals(fCCardMonth)){
			fCCardMonth.setValue("");
			showKeyboard(fCCardMonth);
		}
		else if(e.getTarget().equals(fCCardVC)){
			fCCardVC.setValue("");
			showKeyboard(fCCardVC);
		}
		else {
			setPayAmt((BigDecimal) fPayAmt.getValue());
			v_Parent.refreshPanel();
		}
//			setTotals();
	}
	

	/**
	 * Init Main Panel
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return void
	 */
	private void init() {
		v_MainPanel = new Panel();
		v_PanelChildren = new Panelchildren();
		v_Div = new Div();

		groupPanel = new Groupbox();
		v_PanelChildren.appendChild(v_Div);

		v_PanelChildren.appendChild(groupPanel);

		v_TitleBorder = new Caption("Credit Card");
		v_TitleBorder.setStyle("Font-size:13pt");
		groupPanel.appendChild(v_TitleBorder);
		v_MainPanel.appendChild(v_PanelChildren);
		
		//	Load Standard Panel
		loadStandardPanel();
		//	Load Check Panel
		loadCheckPanel();
		//	Load Credit Panel
		loadCreditPanel();
		//	Add to Main Panel
		groupPanel.appendChild(v_StandarPanel);
		groupPanel.appendChild(v_CheckPanel);
		groupPanel.appendChild(v_CreditPanel);

		//	Change View
		changeView();

	
	}
	
	/**
	 * Get Main Panel
	 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return Panel
	 */
	public Panel getPanel() {
		return v_MainPanel;
	}

	@Override
	public void refreshPanel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String validatePanel() {
		// TODO Auto-generated method stub
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
//			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_DirectDebit)){
			v_CheckPanel.setVisible(false);
//			v_DebitPanel.setVisible(true);
			v_CreditPanel.setVisible(false);
		} else if(p_TenderType.equals(X_C_Payment.TENDERTYPE_CreditCard)){
			v_CheckPanel.setVisible(false);
//			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(true);
		} else {
			v_CheckPanel.setVisible(false);
//			v_DebitPanel.setVisible(false);
			v_CreditPanel.setVisible(false);
		}
	}

}
