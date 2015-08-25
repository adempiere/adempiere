package org.compiere.pos;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MCurrency;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentProcessor;
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
	private Textbox fTenderAmt;
	private Label lTenderAmt;
	private Textbox fReturnAmt;
	private Label lReturnAmt;
	private int cont;
	private int keyLayoutId;
	private MPOS p_MPOS;
	private MOrder p_Order;
	private PosOrderModel p_order;
	
	private Borderlayout mainLayout;
	private final String FONT_SIZE = "Font-size:10pt;";
	private final String FONT_BOLD = "font-weight:700";
	
	public PaymentPanel(Properties ctx, MOrder m_Order, int m_M_POS_ID, String m_TendeType ) {
		super(ctx, m_Order, m_M_POS_ID);
		p_TenderType = "C";
		p_ctx = ctx;
		//	Instance POS
		p_MPOS = MPOS.get(ctx, m_M_POS_ID);
		p_Order = m_Order;
	}
	
	public Panel paymentPanel(){
		mainPanel = new Panel();
		mainGrid = GridFactory.newGridLayout();
		mainPanel.appendChild(mainGrid);
		mainGrid.setWidth("100%");
		mainGrid.setHeight("190px");
		Center center = new Center();
		center.setStyle("border: none");
		mainLayout = new Borderlayout();
		mainLayout.appendChild(center);
		center.appendChild(mainPanel);
		Rows rows = null;
		Row row = null;
		rows = mainGrid.newRows();

		row = rows.newRow();
		row.appendChild(new Label(Msg.translate(p_ctx, "TenderType")).rightAlign());
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
				if ( key.getID().equals("X")){   // Cash
					tenderTypePick.setSelectedValueNamePair(key);
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
		row = rows.newRow();
		fPayAmt = new Textbox();
		row.appendChild(new Label(Msg.translate(p_ctx, "PayAmt")).rightAlign());
		row.appendChild(fPayAmt);
		fPayAmt.setText("0");
		row = rows.newRow();
		fTenderAmt = new Textbox();
		lTenderAmt = new Label(Msg.translate(p_ctx, "AmountTendered"));
		fTenderAmt.addEventListener("onFocus", this);
		row.appendChild(lTenderAmt.rightAlign());
		row.appendChild(fTenderAmt);
		fTenderAmt.setText("0");

		row = rows.newRow();		
		fReturnAmt = new Textbox();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned"));
		
		row = rows.newRow();
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt);
		fReturnAmt.setReadonly(true);
		fReturnAmt.addEventListener("onFocus", this);
		
		row = rows.newRow();
		fCheckRouteNo = new Textbox();
		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		row.appendChild(lCheckRouteNo.rightAlign());
		row.appendChild(fCheckRouteNo);

		row = rows.newRow();
		fCheckAccountNo = new Textbox();
		lCheckAccountNo = new Label(Msg.translate(p_ctx, "AccountNo"));

		row = rows.newRow();
		row.appendChild(lCheckAccountNo.rightAlign());
		row.appendChild(fCheckAccountNo);
		
		fCheckNo = new Textbox();
		lCheckNo = new Label(Msg.translate(p_ctx, "CheckNo"));
		row = rows.newRow();
		row.appendChild(lCheckNo.rightAlign());
		row.appendChild(fCheckNo);

		lCCardType = new Label(Msg.translate(p_ctx, "CreditCardType"));
		row = rows.newRow();
		row.appendChild(lCCardType.rightAlign());
		row.appendChild(fCCardType);
			
		/**
		 *	Load Credit Cards
		 */
		ValueNamePair[] ccs = getCreditCards(new BigDecimal (fPayAmt.getText()), p_Order.getAD_Client_ID(),p_Order.getAD_Org_ID(), p_Order.getC_Campaign_ID(), null);
		for(int x = 0; x < ccs.length; x++){
			fCCardType.appendItem(ccs[x].getName(),String.valueOf(ccs[x].getValue()));
		}
		
		fCCardNo = new Textbox();
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		
		row = rows.newRow();
		row.appendChild(lCCardNo.rightAlign());
		row.appendChild(fCCardNo);
		
		fCCardName = new Textbox();
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		row = rows.newRow();
		row.appendChild(lCCardName.rightAlign());
		row.appendChild(fCCardName);
		
		fCCardMonth = new Textbox();
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		row = rows.newRow();
		row.appendChild(lCCardMonth.rightAlign());
		row.appendChild(fCCardMonth);
		
		fCCardVC = new Textbox();
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		row = rows.newRow();
		row.appendChild(lCCardVC.rightAlign());
		row.appendChild(fCCardVC);
		setTotals();
		
		return mainPanel;
	}
	
	private void setTotals() {

		String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
		boolean cash = MPayment.TENDERTYPE_Cash.equals(tenderType);
		boolean check = MPayment.TENDERTYPE_Check.equals(tenderType);
		boolean creditcard = MPayment.TENDERTYPE_CreditCard.equals(tenderType);
		boolean account = MPayment.TENDERTYPE_Account.equals(tenderType);

		fTenderAmt.setVisible(cash);
		fReturnAmt.setVisible(cash);
		lTenderAmt.setVisible(cash);
		lReturnAmt.setVisible(cash);
		
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
	
	public void setTenderType(String m_TenderType) {
		p_TenderType = m_TenderType;
	}
	
	public String getTenderType() {
		return p_TenderType;
	}

	@Override
	public void onEvent(Event arg0) throws Exception {

		setTotals();
	}
}
