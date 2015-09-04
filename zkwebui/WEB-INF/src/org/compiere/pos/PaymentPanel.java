package org.compiere.pos;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;

public class PaymentPanel extends Collect implements EventListener {
	
	private String p_TenderType;
	private Panel mainPanel; 
	private Grid mainGrid;
	private Properties p_ctx;

	private Listbox tenderTypePick = ListboxFactory.newDropdownListbox();
	private Listbox bankList = ListboxFactory.newDropdownListbox();
	public Textbox fPayAmt;
	private WPosTextField fCheckAccountNo;
	private Datebox fCheckdate;
	private WPosTextField fCheckRouteNo;
	private WPosTextField fCCardNo;
	private WPosTextField fCCardName;
	private Listbox fCCardType= ListboxFactory.newDropdownListbox();
	private WPosTextField fCCardMonth;
	private WPosTextField fCCardVC;

	private Label lCheckNo;
	private Label lCheckAccountNo;
	private Label lCheckRouteNo;
	private Label lCCardNo;
	private Label lCCardName;
	private Label lCCardType;
	private Label lCCardMonth;
	private Label lCCardVC;
	private int cont;
	private int keyLayoutId;
	private MPOS p_MPOS;
	private MOrder p_Order;
	private PosOrderModel p_order;
	private Borderlayout mainLayout;
	
	private final String COLOR_GRAY = "color:#666";
	private final String COLOR_BLACK = "color:#000";
	private DateFormat 				dateFormat 		 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private EventListener p_Event;
	private WPOS p_posBasePanel;
	
	public PaymentPanel(Properties ctx, MOrder m_Order, int m_M_POS_ID, String m_TendeType, EventListener m_event, WPOS m_posBasePanel ) {
		super(ctx, m_Order, m_M_POS_ID);
		p_TenderType = m_TendeType;
		p_ctx = ctx;
		p_Event = m_event;
		//	Instance POS
		p_MPOS = MPOS.get(ctx, m_M_POS_ID);
		p_Order = m_Order;
		p_posBasePanel = m_posBasePanel;
		keyLayoutId = p_MPOS.getOSNP_KeyLayout_ID();
	}
	
	public Panel cashPanel(){
		mainPanel = new Panel();
		mainGrid = GridFactory.newGridLayout();
		mainPanel.appendChild(mainGrid);
		mainGrid.setWidth("95%");
		mainGrid.setHeight("50px");
		Center center = new Center();
		mainLayout = new Borderlayout();
		mainLayout.appendChild(center);
		
		center.appendChild(mainPanel);
		mainLayout.setStyle("overflow:hidden");
		center.setStyle("border: none;overflow:hidden;");
		
		Rows rows = null;
		Row row = null;
		rows = mainGrid.newRows();
		row = rows.newRow();
		// Payment type selection
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> types = lookup.getData(true, false, true, true);
		
		AD_Column_ID = 8374; //C_Payment_v.TenderType
		MLookup cardlookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> cards = cardlookup.getData(true, false, true, true);
		
		// Add Bank List
		ValueNamePair[] banks = getBank();
		for(int i=0; i < banks.length; i++)
			bankList.appendItem(banks[i].getName(),banks[i].getValue());
				
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				if(p_TenderType.equals("X") && "X".contains(key.getID()))
					tenderTypePick.appendItem(key.getName(), key);
			}
		}
		tenderTypePick.setWidth("139px");
		tenderTypePick.addActionListener(this);
		row.appendChild(tenderTypePick);
		

		Label lPayAmt  = new Label(Msg.translate(p_ctx, "PayAmt"));
		lPayAmt.setWidth("225px");
		fPayAmt = new Textbox();
		row.appendChild(fPayAmt);
		fPayAmt.setText(lPayAmt.getValue());
		fPayAmt.setStyle("text-align:right");
		fPayAmt.addFocusListener(p_Event);

		return mainPanel;
	}
	public Panel paymentPanel(){
		mainPanel = new Panel();
		mainGrid = GridFactory.newGridLayout();
		mainPanel.appendChild(mainGrid);
		mainGrid.setWidth("95%");
		mainGrid.setHeight("70px");
		Center center = new Center();
		mainLayout = new Borderlayout();
		mainLayout.appendChild(center);
		
		center.appendChild(mainPanel);
		mainLayout.setStyle("overflow:hidden");
		center.setStyle("border: none;overflow:hidden;");
		
		Rows rows = null;
		Row row = null;
		rows = mainGrid.newRows();
		row = rows.newRow();
		// Payment type selection
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> types = lookup.getData(true, false, true, true);
		
		AD_Column_ID = 8374; //C_Payment_v.TenderType
		MLookup cardlookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> cards = cardlookup.getData(true, false, true, true);
		
		// Add Bank List
		ValueNamePair[] banks = getBank();
		for(int i=0; i < banks.length; i++)
			bankList.appendItem(banks[i].getName(),banks[i].getValue());
				
		int position = 0;
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				tenderTypePick.appendItem(key.getName(), key);
				if ( key.getID().equals(p_TenderType)){   // Cash
					tenderTypePick.setSelectedIndex(position);
				}
				if (!"CKFN".contains(key.getID() ) ) {
					tenderTypePick.removeItemAt(position);
					position--;
				}
				position++;
				
			}
		}
		
		tenderTypePick.addActionListener(this);
		row.appendChild(tenderTypePick);
		

		Label lPayAmt  = new Label(Msg.translate(p_ctx, "PayAmt"));
		lPayAmt.setWidth("225px");
		fPayAmt = new Textbox();
		row.appendChild(fPayAmt);
		fPayAmt.setText(lPayAmt.getValue());
		fPayAmt.setValue("0.00");
		fPayAmt.setStyle("text-align:right");
		fPayAmt.addFocusListener(p_Event);
		
		row = rows.newRow();
		fCheckRouteNo = new WPosTextField(p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		row.appendChild(fCheckRouteNo);
		fCheckRouteNo.setValue(lCheckRouteNo.getValue());
		fCheckRouteNo.addEventListener("onFocus", this);
		row.appendChild(bankList);
		
		fCheckdate = new Datebox();
		lCheckNo = new Label(Msg.translate(p_ctx, "CheckNo"));
		row = rows.newRow();
		row.appendChild(fCheckdate);

		lCCardType = new Label(Msg.translate(p_ctx, "CreditCardType"));
		row = rows.newRow();
		row.appendChild(fCCardType);
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
		fCCardNo = new WPosTextField(p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		
		row.appendChild(fCCardNo);
		fCCardNo.setText(lCCardNo.getValue());
		fCCardNo.addEventListener("onFocus", this);
		
		fCCardName = new WPosTextField(p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		row = rows.newRow();
		row.appendChild(fCCardName);
		
		fCCardName.setValue(lCCardName.getValue());
		fCCardName.addEventListener("onFocus", this);
		
		fCCardMonth = new WPosTextField(p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		row.appendChild(fCCardMonth);
		fCCardMonth.setValue(lCCardMonth.getValue());
		fCCardMonth.addEventListener("onFocus", this);
		
		fCCardVC = new WPosTextField(p_posBasePanel, p_MPOS.getOSK_KeyLayout_ID());
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		row = rows.newRow();
		row.appendChild(fCCardVC);
		fCCardVC.setValue(lCCardVC.getValue());
		fCCardVC.addEventListener("onFocus", this);
		setTotals();

		return mainPanel;
	}

	public ValueNamePair[] getBank(){
		return DB.getValueNamePairs("SELECT C_Bank_ID, Name FROM C_Bank", true, null);
	}
	public boolean savePay(){
		BigDecimal payAmt = new BigDecimal(fPayAmt.getValue());
		
		if(p_TenderType.equals(MPayment.TENDERTYPE_Cash))
			addCash(payAmt);
		else if(p_TenderType.equals(MPayment.TENDERTYPE_Check)) {
			String hourString = dateFormat.format(fCheckdate.getValue());
			String bank_ID = ((ValueNamePair) bankList.getSelectedItem().toValueNamePair()).getValue();
			
			String routeNo = fCheckRouteNo.getValue();
			
			Timestamp dateTrx = Timestamp.valueOf(hourString);
			addCheck(payAmt, routeNo, Integer.parseInt(bank_ID), dateTrx);
		}
		else if(p_TenderType.equals(MPayment.TENDERTYPE_CreditCard)){

			int month = MPaymentValidate.getCreditCardExpMM(fCCardMonth.getText());
			int year = MPaymentValidate.getCreditCardExpYY(fCCardMonth.getText());
			String cardNo = fCCardNo.getValue();
			String cardCVC = fCCardVC.getValue();
			ValueNamePair pp = fCCardType.getSelectedItem().toValueNamePair();
			String cardType = ((ValueNamePair) fCCardType.getSelectedItem().toValueNamePair()).getValue();
			
			payCreditCard(payAmt, fCCardName.getValue(), month, year, cardNo, cardCVC, cardType);
		}
			
		processPayment();
		return true;
	}
	public void setTotals() {

		String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
		boolean cash = MPayment.TENDERTYPE_Cash.equals(tenderType);
		boolean check = MPayment.TENDERTYPE_Check.equals(tenderType);
		boolean creditcard = MPayment.TENDERTYPE_CreditCard.equals(tenderType);
		boolean directDeposit = MPayment.TENDERTYPE_DirectDeposit.equals(tenderType);
		boolean directDebit = MPayment.TENDERTYPE_DirectDebit.equals(tenderType);
		boolean account = MPayment.TENDERTYPE_Account.equals(tenderType);
		p_TenderType = tenderType;

		if(check)
			mainGrid.setHeight("120px");
		else if(creditcard)
			mainGrid.setHeight("160px");
		else if(account)
			mainGrid.setHeight("170px");
		else if(directDebit)
			mainGrid.setHeight("90px");
		else if(directDeposit)
			mainGrid.setHeight("90px");
		else if(cash)
			mainGrid.setHeight("50px");
		else
			mainGrid.setHeight("50px");
			
		fCheckdate.setVisible(check);
		fCheckRouteNo.setVisible(check);
		lCheckNo.setVisible(check);
		lCheckRouteNo.setVisible(check);
		bankList.setVisible(check);

		
		fCCardMonth.setVisible(creditcard);
		fCCardName.setVisible(creditcard);
		fCCardNo.setVisible(creditcard);
		fCCardType.setVisible(creditcard);
		fCCardVC.setVisible(creditcard);
		lCCardMonth.setVisible(creditcard);
		lCCardName.setVisible(creditcard);
		lCCardNo.setVisible(creditcard);
		lCCardType.setVisible(creditcard);
		lCCardVC.setVisible(creditcard);

	}
	public void clear(){
		mainLayout = null;
		mainGrid = null;
		mainPanel = null;
	}
	protected Button createButtonAction (String action, KeyStroke accelerator)
	{
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setTooltiptext(Msg.translate(p_ctx, action));
		button.setWidth("55px");
		button.setHeight("55px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	public void setTenderType(String m_TenderType) {
		p_TenderType = m_TenderType;
	}
	
	public String getTenderType() {
		return p_TenderType;
	}
	public Panel getMainPanel(){
		return mainPanel;
	}
	public BigDecimal getPayAmt(){
		return new BigDecimal(fPayAmt.getValue());
	}
	public Textbox getlPayAmt(){
		return fPayAmt;
	}
	
	public void showKeyboard(WPosTextField field) {
		cont++;
		if(cont<2){
				WPOSKeyboard keyboard = new WPOSKeyboard (p_posBasePanel, keyLayoutId); 
				keyboard.setWidth("280px");
				keyboard.setHeight("320px");
				keyboard.setPosTextField(field);	
				AEnv.showWindow(keyboard);
			}
		else {
			cont=0;
			mainPanel.setFocus(true);
		}
	
	}
		
	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getName().equals("onFocus")){
		
		if(e.getTarget().equals(fCheckAccountNo)){
			fCheckAccountNo.setValue("");
			showKeyboard(fCheckAccountNo);
		}
		
		if(e.getTarget().equals(fCheckdate)){
//			fCheckdate.setValue("");
			
		}
		if(e.getTarget().equals(fCheckRouteNo)){
			fCheckRouteNo.setValue("");
			showKeyboard(fCheckRouteNo);
		}
		if(e.getTarget().equals(fCCardNo)){
			fCCardNo.setValue("");
			showKeyboard(fCCardNo);
		}
		if(e.getTarget().equals(fCCardName)){
			fCCardName.setValue("");
			showKeyboard(fCCardName);
		}
		if(e.getTarget().equals(fCCardMonth)){
			fCCardMonth.setValue("");
			showKeyboard(fCCardMonth);
		}
		if(e.getTarget().equals(fCCardVC)){
			fCCardVC.setValue("");
			showKeyboard(fCCardVC);
		}
		}
			setTotals();
	}
}
