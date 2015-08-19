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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;

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
//			
//			String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
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
			setTotals();
		}
		catch (Exception e )
		{
			FDialog.warn(0, this, "Payment processing failed: " + e.getMessage(),"");
		}
	}

	private WPosBasePanel p_posPanel;
	private MPOS p_pos;
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
	private int cont;
	private int keyLayoutId;
	private ArrayList<Object> types;
	private final String FONT_SIZE = "Font-size:medium;";
	private final String FONT_BOLD = "font-weight:700";
	
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
		
		Panel mainPanel = new Panel();
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
		Rows rows = null;
		Row row = null;
		rows = layout.newRows();
		row = rows.newRow();
		// Payment type selection
		int AD_Column_ID = 8416; //C_Payment_v.TenderType
		MLookup lookup = MLookupFactory.get(Env.getCtx(), 0, 0, AD_Column_ID, DisplayType.List);
		types = lookup.getData(true, false, true, true);
		filterTypes();
		row.appendChild(new Space());
		row = rows.newRow();
		
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
					tenderType = new Button();
					tenderType.setWidth("100px");
					tenderType.setHeight("50px");
					tenderType.setLabel(key.getName());
					tenderType.setId(key.getID());
					tenderType.setStyle("float:left; text-align:center; margin:1% 1%;");
					tenderType.addActionListener(this);
					row.appendChild(tenderType);
					row.setHeight("55px");
					row.setStyle("overflow:visible; border:0px");
					row = rows.newRow();
			}
		}
		//
		East east = new East();
		east.setStyle("border: none");
		mainLayout.appendChild(east);
		east.appendChild(eastPanel);
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
		
		row = rows.newRow();
		Label taxLabel = new Label(Msg.translate(p_ctx, "TaxAmt")+":");
		taxLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fTaxAmt = new Label();
		row.appendChild(taxLabel);
		row.appendChild(fTaxAmt);
		fTaxAmt.setStyle(FONT_SIZE);

		row = rows.newRow();
		fPayAmt = new Label();
		Label payAmtLabel = new Label(Msg.translate(p_ctx, "PayAmt")+":");
		row.appendChild(payAmtLabel.rightAlign());
		row.appendChild(fPayAmt);
		payAmtLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fPayAmt.setStyle(FONT_SIZE);
		fPayAmt.setText("0");
		keyLayoutId=p_pos.getOSNP_KeyLayout_ID();
		row = rows.newRow();
		fTenderAmt = new Label();
		lTenderAmt = new Label(Msg.translate(p_ctx, "AmountTendered")+":");
		fTenderAmt.addEventListener("onFocus", this);
		row.appendChild(lTenderAmt.rightAlign());
		lTenderAmt.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(fTenderAmt);
		fTenderAmt.setText("0");
		fTenderAmt.setStyle(FONT_SIZE);

		row = rows.newRow();		
		int pos=0;
		lTenderAmount = new Label[types.size()];
		// default to cash payment
		for (Object obj : types) {
			if ( obj instanceof ValueNamePair )	{
				ValueNamePair key = (ValueNamePair) obj;
					lTenderType = new Label(key.getName());
					lTenderType.setStyle(FONT_SIZE+FONT_BOLD);
					lTenderAmount[pos] = new Label("0");
					lTenderAmount[pos].setId(key.getID()+"_1");
					lTenderAmount[pos].setStyle(FONT_SIZE);
					row.appendChild(lTenderType.rightAlign());
					row.appendChild(lTenderAmount[pos]);
					row = rows.newRow();
					pos++;
			}
		}

		Label balanceLabel = new Label(Msg.translate(p_ctx, "Balance")+":");
		row.appendChild(balanceLabel.rightAlign());
		balanceLabel.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(fBalance);
		fBalance.setStyle(FONT_SIZE);
		row = rows.newRow();
		
		fReturnAmt = new Label();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned")+":");
		lReturnAmt.setStyle(FONT_SIZE+FONT_BOLD);
		fReturnAmt.setStyle(FONT_SIZE);
		row = rows.newRow();
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt);
		fReturnAmt.addEventListener("onFocus", this);
		setTotals();


		//SHW End
		South south = new South();
		ConfirmPanel confirm = new ConfirmPanel(true);
		confirm.addActionListener(this);

		mainLayout.appendChild(south);
		south.appendChild(confirm);
		
		
		keyboard = new WPOSKeyboard(keyLayoutId, this.fTenderAmt, WPOSKeyboard.KEYBOARD_NUMERIC_CASHOUT, fBalance);
		keyboard.setVisible(true);
		//
		West west = new West();
		west.setStyle("overflow:visible");
		mainLayout.appendChild(west);
		west.appendChild(keyboard);
		
	}
	
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
		pay.setWidth("630px");;
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
	public void cashout(){
		BigDecimal tender = new BigDecimal( fTenderAmt.getValue() );
		BigDecimal payAmt = new BigDecimal( fPayAmt.getValue() );
		if(payAmt.compareTo(tender) > 0)
			keyboard.setCashOut(payAmt.subtract(tender));
		else
			keyboard.setCashOut(Env.ZERO);
	}
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		String action = event.getTarget().getId();
		if(action.equals(MPayment.TENDERTYPE_Cash)){
			cashpay();
			calculateAmt();
		}
		else if(action.equals(MPayment.TENDERTYPE_Check)){
//			WPosCheckPayment chkpayment = new WPosCheckPayment(this);
//			chkpayment.setWidth("680px");
//			chkpayment.setHeight("420px");
//			AEnv.showWindow(chkpayment);
		}
		else if (event.getTarget().equals(fTenderAmt) ){
			cont++;
			if(cont<2){
				WPOSKeyboard keyboard = p_posPanel.getKeyboard(keyLayoutId);
				keyboard.setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
				keyboard.setPosTextField(this.fTenderAmt);	
				if(event.getName().equals("onFocus")) {
					keyboard.setWidth("280px");
					keyboard.setHeight("320px");
					AEnv.showWindow(keyboard);
					calculateAmt();
				}
			}
			else {
				
				cont=0;
				fReturnAmt.setFocus(true);
			}
			return;
		}
		else if ( event.getTarget().equals(fCreditNotes) ) {
			BigDecimal openamt = new BigDecimal( fTotal.getValue() );
			String ID = ((ValueNamePair) fCreditNotes.getSelectedItem().toValueNamePair()).getValue();
			MInvoice cn = new MInvoice(p_ctx, Integer.parseInt(ID), null);
			BigDecimal payamtmax = cn.getOpenAmt().negate();
			BigDecimal payamt = openamt.compareTo(payamtmax) >= 0 ? payamtmax:openamt;
			fPayAmt.setText(payamt.toString());
			fTenderAmt.setText(payamt.toString());
			BigDecimal tender = new BigDecimal( fTenderAmt.getValue() );
			if ( tender.compareTo(Env.ZERO) != 0 )
			{
				fReturnAmt.setValue(tender.subtract(payamt).toString());
			}
			return;
		}
		else if ( action.equals(ConfirmPanel.A_OK)) {
			processPayment();
			onClose();
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))	{
			onClose();
			return;
		}
		
			setTotals();
		
	}

}
