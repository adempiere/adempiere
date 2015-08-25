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

package org.compiere.pos;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.w3c.dom.events.EventException;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.East;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zkex.zul.West;
import org.zkoss.zul.Space;


/**
 * 
 * @author Raul Muñoz 20/03/2015, 12:50 
 */
public class WPosPayment extends Window implements WPosKeyListener, EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961106531807910948L;

	private void processPayment() {

		try {
			BigDecimal amt = new BigDecimal(fPayAmt.getValue());
			if(fTenderAmt.getValue().equals(0)){
				FDialog.warn(0, p_posPanel, "Mount", "");
				return;
			}

			for(int i = 0; i < types.size(); i++){
				if(lTenderAmount[i].getId().equals(MPayment.TENDERTYPE_Cash+"_1")){
					amt = new BigDecimal(lTenderAmount[i].getValue());
						p_order.payCash(amt);
				}
			}
			

			p_posPanel.f_order.openCashDrawer();
//			setTotals();
		}
		catch (Exception e )
		{
			FDialog.warn(0, this, "Payment processing failed: " + e.getMessage(),"");
		}
	}

	public WPosBasePanel p_posPanel;
	public MPOS p_pos;
	private Properties p_ctx;
	private PosOrderModel p_order;
	private Label fTotal = new Label();
	private Label fBalance = new Label();
	private Button tenderType;
	private Label fPayAmt;
	private boolean paid = false;
	private BigDecimal balance = Env.ZERO;
	private Listbox fCreditNotes= ListboxFactory.newDropdownListbox();
	private WPOSKeyboard keyboard; 
	private Label fTenderAmt;
	private Label lTenderAmt;
	private Label fReturnAmt;
	private Label lReturnAmt;
	private Label fSubTotal;
	private Label fTaxAmt;
	private Label lTenderType;
	private Label lTenderAmount[];
	private Button fPlus;
	private int cont;
	private int keyLayoutId;
	private ArrayList<Object> types;
	private Listbox tenderTypePick = ListboxFactory.newDropdownListbox();
	private final String FONT_SIZE = "Font-size:10pt;";
	private final String FONT_BOLD = "font-weight:700";
	private Textbox fAmount;
	private PaymentPanel pp;
	private Rows rows = null;
	private Row row = null;
	private Panel mainPanel; 
	public WPosPayment(WPosBasePanel posPanel, WSubOrder subOrder) {
		super();
		p_posPanel = posPanel;
		p_pos = subOrder.p_pos;
		p_ctx = p_pos.getCtx();
		p_order = subOrder.m_order;
		setTitle(Msg.translate(p_ctx, "Payment"));
		setClosable(true);
		
		if ( p_order == null )
			dispose();
		
		init();
	}

	private void init() {
		cont = 0;
		Panel panel = new Panel();
		//	Content
		
		mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		Grid layout = GridFactory.newGridLayout();
		Grid eastlayout = GridFactory.newGridLayout();
		appendChild(panel);
		
		//	Panels
		Panel centerPanel = new Panel();
		Panel eastPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		//
		
		Center center = new Center();
		center.setStyle("border: none");
		mainLayout.appendChild(center);
		center.appendChild(centerPanel);
		centerPanel.appendChild(layout);
		layout.setWidth("100%");
		layout.setHeight("370px");
		appendChild(mainPanel);
		
		rows = layout.newRows();
		row = rows.newRow();

		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(eastPanel);
		eastPanel.appendChild(eastlayout);
		eastlayout.setWidth("100%");
		eastlayout.setHeight("100%");
		
		rows = eastlayout.newRows();
		row = rows.newRow();

		row = rows.newRow();
		Label gtLabel = new Label(Msg.translate(p_ctx, "GrandTotal")+":");
		gtLabel.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(gtLabel.rightAlign());
		row.appendChild(fTotal);
		fTotal.setStyle(FONT_SIZE);
		
		row = rows.newRow();
		Label fsLabel = new Label(Msg.translate(p_ctx, "SubTotal")+":");
		fsLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fSubTotal = new Label();
		row.appendChild(fsLabel.rightAlign());
		row.appendChild(fSubTotal);
		fSubTotal.setStyle(FONT_SIZE);
				
		fReturnAmt = new Label();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned")+":");
		lReturnAmt.setStyle(FONT_SIZE+FONT_BOLD);
		fReturnAmt.setStyle(FONT_SIZE);
		row = rows.newRow();
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt);
		fReturnAmt.addEventListener("onFocus", this);
//		setTotals();
		
		row = rows.newRow();
		lTenderType = new Label(Msg.translate(p_ctx, "TenderType"));
		lTenderType.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(lTenderType.rightAlign());
		
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
		tenderTypePick.setStyle(FONT_SIZE);
		tenderTypePick.addActionListener(this);
		row.appendChild(tenderTypePick);
		
		fAmount = new Textbox();
		row.appendChild(fAmount);
		fAmount.setStyle(FONT_SIZE);
		fAmount.setWidth("90px");
		fAmount.setAttribute("placeholder", Msg.translate(p_ctx, "Amount"));

		// Button Plus
		fPlus = createButtonAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		row.appendChild(fPlus);

		
		
		//SHW End
		South south = new South();
		ConfirmPanel confirm = new ConfirmPanel(true);
		confirm.addActionListener(this);

		mainLayout.appendChild(south);
		south.appendChild(confirm);
		
	}
	
	private void addTypePay(){
		row = rows.newRow();
		row.setSpans("4");
		pp = new PaymentPanel(p_ctx, p_order, p_order.getC_POS_ID(), "C");
		row.appendChild(pp.paymentPanel());
		mainPanel.invalidate();
		
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
	
	public void filterTypes(){
		for (int x=0; x < types.size(); x++) {
			Object obj = types.get(x); 
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
				if (!"CKXFN".contains(key.getID() ) ){ 
					types.remove(x);
					x--;
				}
				
			}
		}
	}

	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		String action = event.getTarget().getId();
		if(action.equals(MPayment.TENDERTYPE_Cash)){
			cashpay();
			calculateAmt();
		}
		else if(event.getTarget().equals(fPlus)){
			addTypePay();
		}
		
		else if ( action.equals(ConfirmPanel.A_OK)) {
			processPayment();
			onClose();
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))	{
			onClose();
			return;
		}

//		setTotals();
		
	}
	private void setTotals() {

		fTotal.setValue(p_order.getGrandTotal().toString());
		
		BigDecimal received = p_order.getPaidAmt();		
		balance  = p_order.getGrandTotal().subtract(received);
		balance = balance.setScale(MCurrency.getStdPrecision(p_ctx, p_order.getC_Currency_ID()));
		if ( balance.compareTo(Env.ZERO) <= 0 )
		{
			paid = true;
			
			if ( balance.compareTo(Env.ZERO) < 0 )
					FDialog.warn(0, this, Msg.getMsg(p_ctx, "Change") + ": " + balance,"");
			dispose();
		}
		fSubTotal.setValue(p_order.getSubtotal().toString());
		fBalance.setValue(balance.toString());
		fPayAmt.setValue(balance.toString());
		fTaxAmt.setValue(p_order.getTaxAmt().toString());

	}

	public void keyReturned(MPOSKey key) {
		
		String text = key.getText();
		String payAmt = fPayAmt.getValue();
		
		if ( text != null && !text.isEmpty() )
		{
			if ( text.equals(".") && payAmt.indexOf(".") == -1 )
			{
				fPayAmt.setText(payAmt + text);
				return;
			}
			try
			{
				Integer.parseInt(text);		// test if number
				fPayAmt.setText(payAmt + text);
			}
			catch (NumberFormatException e)
			{
				// ignore non-numbers
			}
		}
	}
	
	public static boolean pay(WPosBasePanel posPanel, WSubOrder subOrder) {
		
		WPosPayment pay = new WPosPayment(posPanel, subOrder);
		pay.setVisible(true);
		pay.setWidth("430px");;
		pay.setHeight("380px"); ;
		pay.setClosable(true);
		AEnv.showWindow(pay);
		return pay.isPaid();
	}

	private boolean isPaid() {
		return paid ;
	}

	public void calculateAmt() {

		BigDecimal tender;
		BigDecimal tenderPay = new BigDecimal(Env.ZERO.toString());
		BigDecimal pay = new BigDecimal( fPayAmt.getValue() );
		for(int i = 0; i < types.size(); i++){
			tender = new BigDecimal(lTenderAmount[i].getValue());
			tenderPay = tenderPay.add(tender);
		}
		
		if ( !pay.equals(0) ) {
			fReturnAmt.setValue(tenderPay.subtract(pay).toString());
		}
		fTenderAmt.setValue(tenderPay.toString());
		cashout();
	}
	public void cashpay(){
		keyboard.getMount();
		for(int i = 0; i < types.size(); i++){
			if(lTenderAmount[i].getId().equals(MPayment.TENDERTYPE_Cash+"_1")){
				BigDecimal tender = new BigDecimal( fTenderAmt.getValue() );
				BigDecimal cashpay = new BigDecimal( lTenderAmount[i].getValue() );
				lTenderAmount[i].setValue(tender.add(cashpay).toString());
				break;
			}
		}
	}
	
	public void checkpay(String checkMountchk){
		for(int i = 0; i < types.size(); i++){
			if(lTenderAmount[i].getId().equals(MPayment.TENDERTYPE_Check+"_1")){
				BigDecimal tender = new BigDecimal( checkMountchk );
				BigDecimal checkpay = new BigDecimal( lTenderAmount[i].getValue() );
				lTenderAmount[i].setValue(tender.add(checkpay).toString());
				break;
			}
		}
	}

	public void cCardPay(String cCardMount){
		for(int i = 0; i < types.size(); i++){
			if(lTenderAmount[i].getId().equals(MPayment.TENDERTYPE_CreditCard+"_1")){
				BigDecimal tender = new BigDecimal( cCardMount );
				BigDecimal cCardPay = new BigDecimal( lTenderAmount[i].getValue() );
				lTenderAmount[i].setValue(tender.add(cCardPay).toString());
				break;
			}
		}
	}
	
	public void cashout(){
		BigDecimal tender = new BigDecimal( fTenderAmt.getValue() );
		BigDecimal payAmt = new BigDecimal( fPayAmt.getValue() );
		if(payAmt.compareTo(tender) > 0)
			keyboard.setCashOut(payAmt.subtract(tender));
		else
			keyboard.setCashOut(Env.ZERO);
	}
	public String getGranTotal(){
		return fTotal.getValue();
	}
	public String getBalance(){
		return fBalance.getValue();
	}
	public PosOrderModel getPosOrderModel(){
		return p_order;
	}
}
