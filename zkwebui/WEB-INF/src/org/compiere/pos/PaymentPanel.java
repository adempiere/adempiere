package org.compiere.pos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
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
	private Textbox fPayAmt;
	private Textbox fCheckAccountNo;
	private Textbox fCheckNo;
	private Textbox fCheckRouteNo;
	private Textbox fCCardNo;
	private Textbox fCCardName;
	private Listbox fCCardType= ListboxFactory.newDropdownListbox();
	private Textbox fCCardMonth;
	private Textbox fCCardVC;

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
	
	public PaymentPanel(Properties ctx, MOrder m_Order, int m_M_POS_ID, String m_TendeType ) {
		super(ctx, m_Order, m_M_POS_ID);
		p_TenderType = m_TendeType;
		p_ctx = ctx;
		//	Instance POS
		p_MPOS = MPOS.get(ctx, m_M_POS_ID);
		p_Order = m_Order;
	}
	
	public Panel paymentPanel(){
		mainPanel = new Panel();
		mainGrid = GridFactory.newGridLayout();
		mainGrid.setStyle("overflow-y:hidden;overflow-x:hidden");
		mainPanel.setStyle("overflow-y:hidden;overflow-x:hidden");
		mainPanel.appendChild(mainGrid);
		mainGrid.setWidth("99%");
		mainGrid.setHeight("auto");
		Center center = new Center();
		mainLayout = new Borderlayout();
		mainLayout.appendChild(center);
		mainLayout.setStyle("overflow-y:hidden;overflow-x:hidden");
		center.setStyle("border: none;overflow-y:hidden;overflow-x:hidden");
		center.appendChild(mainPanel);
		Rows rows = null;
		Row row = null;
		rows = mainGrid.newRows();
		row = rows.newRow();
		// Payment type selection
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		ArrayList<Object> types = lookup.getData(true, false, true, true);
		int position = 0;
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				tenderTypePick.appendItem(key.getName(), key);
				if ( key.getID().equals(p_TenderType)){   // Cash
					tenderTypePick.setSelectedIndex(position);
				}
				if (!"CKXFN".contains(key.getID() ) ) {
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
		fPayAmt.setStyle("text-align:right");
		fPayAmt.addFocusListener(this);
		
		row = rows.newRow();
		fCheckRouteNo = new Textbox();
		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		row.appendChild(fCheckRouteNo);
		fCheckRouteNo.setValue(lCheckRouteNo.getValue());
		fCheckRouteNo.addFocusListener(this);
		
		fCheckAccountNo = new Textbox();
		lCheckAccountNo = new Label(Msg.translate(p_ctx, "AccountNo"));
		row.appendChild(fCheckAccountNo);
		fCheckAccountNo.setValue(lCheckAccountNo.getValue());
		fCheckAccountNo.addFocusListener(this);
		
		fCheckNo = new Textbox();
		lCheckNo = new Label(Msg.translate(p_ctx, "CheckNo"));
		row = rows.newRow();
		row.appendChild(fCheckNo);
		fCheckNo.setValue(lCheckNo.getValue());
		fCheckNo.addFocusListener(this);

		lCCardType = new Label(Msg.translate(p_ctx, "CreditCardType"));
		row = rows.newRow();
		row.appendChild(fCCardType);
		fCCardType.setValue(lCCardType.getValue());
		fCCardType.addActionListener(this);
		
		/**
		 *	Load Credit Cards
		 */
		ValueNamePair[] ccs = getCreditCards(new BigDecimal ("0"), p_Order.getAD_Client_ID(),p_Order.getAD_Org_ID(), p_Order.getC_Campaign_ID(), null);
		for(int x = 0; x < ccs.length; x++){
			fCCardType.appendItem(ccs[x].getName(),String.valueOf(ccs[x].getValue()));
		}
		
		fCCardNo = new Textbox();
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		
		row.appendChild(fCCardNo);
		fCCardNo.setText(lCCardNo.getValue());
		fCCardNo.addFocusListener(this);
		
		fCCardName = new Textbox();
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		row = rows.newRow();
		row.appendChild(fCCardName);
		fCCardName.setValue(lCCardName.getValue());
		fCCardName.addFocusListener(this);
		
		fCCardMonth = new Textbox();
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		row.appendChild(fCCardMonth);
		fCCardMonth.setValue(lCCardMonth.getValue());
		fCCardMonth.addFocusListener(this);
		
		fCCardVC = new Textbox();
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		row = rows.newRow();
		row.appendChild(fCCardVC);
		fCCardVC.setValue(lCCardVC.getValue());
		fCCardVC.addFocusListener(this);
		setTotals();

		return mainPanel;
	}
	
	public boolean savePay(){
		BigDecimal payAmt = new BigDecimal(fPayAmt.getValue());
		if(p_TenderType.equals(MPayment.TENDERTYPE_Cash))
			addCash(payAmt);
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
			
		fCheckAccountNo.setVisible(check);
		fCheckNo.setVisible(check);
		fCheckRouteNo.setVisible(check);
		lCheckAccountNo.setVisible(check);
		lCheckNo.setVisible(check);
		lCheckRouteNo.setVisible(check);

		
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

	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getName().equals("onFocus")){
		if(e.getTarget().equals(fPayAmt)){
			fPayAmt.setValue("");
		}
		if(e.getTarget().equals(fCheckAccountNo)){
			fCheckAccountNo.setValue("");
		}
		if(e.getTarget().equals(fCheckNo)){
			fCheckNo.setValue("");
		}
		if(e.getTarget().equals(fCheckRouteNo)){
			fCheckRouteNo.setValue("");
		}
		if(e.getTarget().equals(fCCardNo)){
			fCCardNo.setValue("");
		}
		if(e.getTarget().equals(fCCardName)){
			fCCardName.setValue("");
		}
		if(e.getTarget().equals(fCCardMonth)){
			fCCardMonth.setValue("");
		}
		if(e.getTarget().equals(fCCardVC)){
			fCCardVC.setValue("");
		}
		}
			setTotals();
	}
}
