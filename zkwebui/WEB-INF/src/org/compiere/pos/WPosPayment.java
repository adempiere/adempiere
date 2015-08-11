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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;


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

			String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
			BigDecimal amt = new BigDecimal(fPayAmt.getText());
			if(fTenderAmt.getText().equals(0)){
				FDialog.warn(0, p_posPanel, "Mount", "");
				return;
			}
			if ( tenderType.equals(MPayment.TENDERTYPE_Cash) )
			{
				p_order.payCash(amt);
			}
			else if ( tenderType.equals("F") )
			{
//				String ID = ((ValueNamePair) fCreditNotes.getSelectedItem()).getValue();
//				MInvoice cn = new MInvoice(p_ctx, Integer.parseInt(ID), null);
//				p_posPanel.m_order.payCreditNote(cn, amt);
			}

			else if ( tenderType.equals("N") )
			{
			}
			else if ( tenderType.equals(MPayment.TENDERTYPE_Check) )
			{
				p_order.payCheck(amt,fCheckAccountNo.getText(), fCheckRouteNo.getText(), fCheckNo.getText());
				p_posPanel.f_order.openCashDrawer();
			}
			else if ( tenderType.equals(MPayment.TENDERTYPE_CreditCard) )
			{
				String error = null;
				error = MPaymentValidate.validateCreditCardExp(fCCardMonth.getText());
				if ( error != null && !error.isEmpty() )
				{
					FDialog.warn(0, p_posPanel, error, "");
					return;
				}
				int month = MPaymentValidate.getCreditCardExpMM(fCCardMonth.getText());
				int year = MPaymentValidate.getCreditCardExpYY(fCCardMonth.getText());

				ValueNamePair pp = fCCardType.getSelectedItem().toValueNamePair();
				if (pp == null)
					return;
				String type = pp.getID();
				error = MPaymentValidate.validateCreditCardNumber(fCCardNo.getText(), type);
				if ( error != null && !error.isEmpty() )
				{
					FDialog.warn(0, p_posPanel, error, "");
					return;
				}
				p_posPanel.m_order.payCreditCard(amt, fCCardName.getText(),
						month, year, fCCardNo.getText(), fCCardVC.getText(), type);
				p_posPanel.f_order.openCashDrawer();
			}
			else if ( tenderType.equals(MPayment.TENDERTYPE_Account) )
			{
				p_order.payCash(amt);
				p_posPanel.f_order.openCashDrawer();
			}
			else
			{
				FDialog.warn(0, this, "Unsupported payment type", "");
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
	private Textbox fTotal = new Textbox();
	private Textbox fBalance = new Textbox();
	private Listbox tenderTypePick = ListboxFactory.newDropdownListbox();
	private WPosTextField fPayAmt;
	private boolean paid = false;
	private BigDecimal balance = Env.ZERO;
	private WPosTextField fCheckAccountNo;
	private WPosTextField fCheckNo;
	private WPosTextField fCheckRouteNo;
	private WPosTextField fCCardNo;
	private WPosTextField fCCardName;
	private Listbox fCCardType= ListboxFactory.newDropdownListbox();
	private Listbox fCreditNotes= ListboxFactory.newDropdownListbox();

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
	private Label lCreditNotes;
	private WPosTextField fTenderAmt;
	private Label lTenderAmt;
	private WPosTextField fReturnAmt;
	private Label lReturnAmt;
	private int cont;
	private int keyLayoutId;
	public WPosPayment(WPosBasePanel posPanel, WSubOrder subOrder) {
		super();
		p_posPanel = posPanel;
		p_pos = subOrder.p_pos;
		p_ctx = p_pos.getCtx();
		p_order = subOrder.m_order;
		setTitle(Msg.translate(p_ctx, "Payment"));
		if ( p_order == null )
			dispose();
		
		init();
	}

	private void init() {
		cont = 0;
		Panel panel = new Panel();
		//	Content
		if(getWidth()==null){
			setWidth("750px");
			setHeight("380px");
		}
		Panel mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		Grid layout = GridFactory.newGridLayout();
		appendChild(panel);
		
		//	North
		Panel centerPanel = new Panel();
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
		layout.setHeight("100%");
		appendChild(mainPanel);
		Rows rows = null;
		Row row = null;
		rows = layout.newRows();
		row = rows.newRow();


		
		Label gtLabel = new Label(Msg.translate(p_ctx, "GrandTotal"));
		row.appendChild(gtLabel.rightAlign());
		row.appendChild(fTotal);
		fTotal.setEnabled(false);
		row = rows.newRow();
		row.appendChild(new Label(Msg.translate(p_ctx, "Balance")).rightAlign());
		row.appendChild(fBalance);
		fBalance.setEnabled(false);

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
		fPayAmt = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		row.appendChild(new Label(Msg.translate(p_ctx, "PayAmt")).rightAlign());
		row.appendChild(fPayAmt);
		fPayAmt.setText("0");
		keyLayoutId=p_pos.getOSNP_KeyLayout_ID();
		row = rows.newRow();
		fTenderAmt = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lTenderAmt = new Label(Msg.translate(p_ctx, "AmountTendered"));
		fTenderAmt.addEventListener("onFocus", this);
		row.appendChild(lTenderAmt.rightAlign());
		row.appendChild(fTenderAmt);
		fTenderAmt.setText("0");

		row = rows.newRow();		
		fReturnAmt = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned"));
		
		row = rows.newRow();
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt);
		fReturnAmt.setReadonly(true);
		fReturnAmt.addEventListener("onFocus", this);
		
		row = rows.newRow();
		fCheckRouteNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lCheckRouteNo = new Label(Msg.translate(p_ctx, "RoutingNo"));
		row.appendChild(lCheckRouteNo.rightAlign());
		row.appendChild(fCheckRouteNo);

		row = rows.newRow();
		fCheckAccountNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
		lCheckAccountNo = new Label(Msg.translate(p_ctx, "AccountNo"));

		row = rows.newRow();
		row.appendChild(lCheckAccountNo.rightAlign());
		row.appendChild(fCheckAccountNo);
		
		fCheckNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID());
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
		ValueNamePair[] ccs = p_order.getCreditCards(new BigDecimal (fPayAmt.getText()));
		for(int x = 0; x < ccs.length; x++){
			fCCardType.appendItem(ccs[x].getName(),String.valueOf(ccs[x].getValue()));
		}
		
		fCCardNo = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID(),  new DecimalFormat("#"));
		lCCardNo = new Label(Msg.translate(p_ctx, "CreditCardNumber"));
		
		row = rows.newRow();
		row.appendChild(lCCardNo.rightAlign());
		row.appendChild(fCCardNo);
		
		fCCardName = new WPosTextField(p_posPanel, p_pos.getOSK_KeyLayout_ID());
		lCCardName = new Label(Msg.translate(p_ctx, "Name"));
		row = rows.newRow();
		row.appendChild(lCCardName.rightAlign());
		row.appendChild(fCCardName);
		
		fCCardMonth = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID(), new DecimalFormat("#"));
		lCCardMonth = new Label(Msg.translate(p_ctx, "Expires"));
		row = rows.newRow();
		row.appendChild(lCCardMonth.rightAlign());
		row.appendChild(fCCardMonth);
		
		fCCardVC = new WPosTextField(p_posPanel, p_pos.getOSNP_KeyLayout_ID(),  new DecimalFormat("#"));
		lCCardVC = new Label(Msg.translate(p_ctx, "CVC"));
		row = rows.newRow();
		row.appendChild(lCCardVC.rightAlign());
		row.appendChild(fCCardVC);

		//SHW Credit Notes

		/**
		 *	Load Credit Notes
		 */
		ValueNamePair[] cnp = p_order.getCreditNotes();
		//	Set Selection
		fCreditNotes = new Listbox();
		
		lCreditNotes = new Label(Msg.translate(p_ctx, "CreditNote"));
		fCreditNotes.addActionListener(this);
		row.appendChild(lCreditNotes.rightAlign());
		row.appendChild(fCreditNotes);
		//SHW Ende
		
		South south = new South();
		ConfirmPanel confirm = new ConfirmPanel(true, false, true, false, false, false, false);
		confirm.addActionListener(this);

		mainLayout.appendChild(south);
		south.appendChild(confirm);
		
		
				
		setTotals();
	}

	private void setTotals() {

		String tenderType = ((ValueNamePair) tenderTypePick.getValue()).getID();
		boolean cash = MPayment.TENDERTYPE_Cash.equals(tenderType);
		boolean check = MPayment.TENDERTYPE_Check.equals(tenderType);
		boolean creditcard = MPayment.TENDERTYPE_CreditCard.equals(tenderType);
		boolean account = MPayment.TENDERTYPE_Account.equals(tenderType);
		boolean creditNote = tenderType.equals("F");
		boolean returnVisible = creditNote || cash;
		fTenderAmt.setValue("0");
		
		fTenderAmt.setVisible(cash);
		fReturnAmt.setVisible(returnVisible);
		lTenderAmt.setVisible(cash);
		lReturnAmt.setVisible(returnVisible);
		
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

		lCreditNotes.setVisible(creditNote);
		fCreditNotes.setVisible(creditNote);
		
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
		
		fBalance.setValue(balance.toString());
		fPayAmt.setValue(balance.toString());

	}

	public void keyReturned(MPOSKey key) {
		
		String text = key.getText();
		String payAmt = fPayAmt.getText();
		
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
		pay.setWidth("350px");;
		pay.setHeight("370px"); ;
		pay.setClosable(true);
		AEnv.showWindow(pay);
		return pay.isPaid();
	}

	private boolean isPaid() {
		return paid ;
	}

	public void calculateAmt() {
		BigDecimal tender = new BigDecimal( fTenderAmt.getText() );
		BigDecimal pay = new BigDecimal( fPayAmt.getText() );	

		if ( tender.compareTo(Env.ZERO) != 0 ) {
			fReturnAmt.setValue(tender.subtract(pay).toString());
		}

	}
	
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		String action = event.getTarget().getId();
		
		if (event.getTarget().equals(fTenderAmt) ){
			cont++;
			if(cont<2){
				WPOSKeyboard keyboard = p_posPanel.getKeyboard(keyLayoutId); 
				keyboard.setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
				keyboard.setPosTextField(this.fTenderAmt);	
				if(event.getName().equals("onFocus")) {
					keyboard.setVisible(true);
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
		else if ( event.getTarget().equals(fCreditNotes) )
		{
			BigDecimal openamt = new BigDecimal( fTotal.getText() );
			String ID = ((ValueNamePair) fCreditNotes.getSelectedItem().toValueNamePair()).getValue();
			MInvoice cn = new MInvoice(p_ctx, Integer.parseInt(ID), null);
			BigDecimal payamtmax = cn.getOpenAmt().negate();
			BigDecimal payamt = openamt.compareTo(payamtmax) >=0? payamtmax:openamt;
			fPayAmt.setValue(payamt.toString());
			fTenderAmt.setValue(payamt.toString());
			BigDecimal tender = new BigDecimal( fTenderAmt.getText() );
			if ( tender.compareTo(Env.ZERO) != 0 )
			{
				fReturnAmt.setValue(tender.subtract(payamt).toString());
			}
			return;
		}
		else if (event.getTarget().equals(fReturnAmt) ){

			BigDecimal tender = new BigDecimal( fTenderAmt.getText() );
			BigDecimal pay = new BigDecimal( fPayAmt.getText() );
			fReturnAmt.setValue(tender.subtract(pay).toString());
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
