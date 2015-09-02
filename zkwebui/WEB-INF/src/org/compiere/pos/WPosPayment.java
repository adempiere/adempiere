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
import java.util.List;
import java.util.Properties;

import javax.swing.KeyStroke;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.ADialog;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.model.MPayment;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.ValueNamePair;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
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
	private Label fPayAmt;
	private boolean paid = false;
	private BigDecimal balance = Env.ZERO;
	private WPOSKeyboard keyboard;
	private Checkbox isPrePaiment;
	private Label fTenderAmt;
	private Label fReturnAmt;
	private Label lReturnAmt;
	private Label lTenderAmount[];
	private Button fPlus;
	private int cont;
	private ArrayList<Object> types;
	private final String FONT_SIZE = "Font-size:medium;";
	private final String FONT_BOLD = "font-weight:700";
	private List<PaymentPanel> pp;
	private List<Button> fMinus;
	private Rows rows = null;
	private Row row = null;
	private Panel mainPanel; 
	private Grid eastlayout;
	private North north;
	private Grid layout;
	private int 	position=1;
	private Button 	bMinus;
	private int 	keyLayoutId;

	public WPosPayment(WPosBasePanel posPanel, WSubOrder subOrder) {
		super();
		p_posPanel = posPanel;
		p_pos = subOrder.p_pos;
		p_ctx = p_pos.getCtx();
		p_order = subOrder.m_order;
		keyLayoutId=p_pos.getOSNP_KeyLayout_ID();
		
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
		layout = GridFactory.newGridLayout();
		appendChild(panel);
		eastlayout = GridFactory.newGridLayout();
		
		//	Panels
		Panel centerPanel = new Panel();
		Panel eastPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0;");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");

		//
		north = new North();
		north.setStyle("border: none; ");
		mainLayout.appendChild(north);
		north.appendChild(eastPanel);
		eastPanel.appendChild(eastlayout);
		eastlayout.setWidth("400px");
		eastlayout.setHeight("100%");
		
		rows = eastlayout.newRows();
		
		row = rows.newRow();
		isPrePaiment = new Checkbox();
		isPrePaiment.setText(Msg.translate(p_ctx, "isPrePayment"));
		isPrePaiment.setStyle(FONT_SIZE);
		row.appendChild(isPrePaiment);
		
		Label gtLabel = new Label(Msg.translate(p_ctx, "GrandTotal")+":");
		gtLabel.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(gtLabel.rightAlign());
		row.appendChild(fTotal);
		fTotal.setStyle(FONT_SIZE);
		fTotal.setValue(p_order.getGrandTotal().toString());
		
		
		row = rows.newRow();
		row.appendChild(new Space());
		

		Label fsLabel = new Label(Msg.translate(p_ctx, "PayAmt")+":");
		fsLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fPayAmt = new Label();
		row.appendChild(fsLabel.rightAlign());
		row.appendChild(fPayAmt);
		fPayAmt.setStyle(FONT_SIZE);
		fPayAmt.setValue(Env.ZERO.toString());
		
		fReturnAmt = new Label();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned")+":");
		lReturnAmt.setStyle(FONT_SIZE+FONT_BOLD);
		fReturnAmt.setStyle(FONT_SIZE);
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt);
		fReturnAmt.addEventListener("onFocus", this);
		
		// Button Plus
		fPlus = createButtonAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));

				
		row = rows.newRow();

		
		Center center = new Center();
		center.setStyle("border: none; overflow-y:auto;overflow-x:hidden;");
		mainLayout.appendChild(center);
		center.appendChild(centerPanel);
		centerPanel.appendChild(layout);
		layout.setWidth("400px");
		layout.setHeight("100%");
		layout.setStyle("overflow:auto;");
		appendChild(mainPanel);
		
		rows = layout.newRows();
		row = rows.newRow();
		row.setWidth("100%");
		
		pp = new ArrayList<PaymentPanel> ();
		fMinus = new ArrayList<Button> ();
		PaymentPanel pPayment= new PaymentPanel(p_ctx, p_order, p_order.getC_POS_ID(), "X", this, p_posPanel);
		pp.add(pPayment);
		bMinus = createButtonAction("Minus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		fMinus.add(bMinus);
		row.setHeight("55px");
		bMinus.setId("0");
		row.appendChild(pp.get(0).cashPanel());
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
		// Button Minus
		bMinus = createButtonAction("Minus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		fMinus.add(bMinus);
		bMinus.setId(String.valueOf(position));
		PaymentPanel pPayment= new PaymentPanel(p_ctx, p_order, p_order.getC_POS_ID(), "X", this, p_posPanel);
		pp.add(pPayment);
		row.appendChild(pp.get(pp.size()-1).paymentPanel());
		layout.invalidate();
		row.appendChild(fMinus.get(pp.size()-1));
		position++;

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
		if(event.getName().equals("onFocus")){
			for(int x = 0; x < pp.size(); x++)
				if(event.getTarget().equals(pp.get(x).fPayAmt)){
					pp.get(x).fPayAmt.setValue("");
					cont++;
					if(cont<2){
						if(event.getName().equals("onFocus")) {
							WPOSKeyboard keyboard = new WPOSKeyboard (p_posPanel, keyLayoutId); 
							keyboard.setWidth("280px");
							keyboard.setHeight("320px");
							keyboard.setPosTextField(pp.get(x).fPayAmt);	
							AEnv.showWindow(keyboard);
							if(pp.get(x).fPayAmt.getValue().equals(""))
								pp.get(x).fPayAmt.setValue("0");
							calculate();
						}
						
					}
						else {
							cont=0;
							mainPanel.setFocus(true);
						}
			}
		} else if (event.getName().equals("onBlur")) {
			for(int x = 0; x < pp.size(); x++)
				if(event.getTarget().equals(pp.get(x).fPayAmt)){
					if(pp.get(x).fPayAmt.getValue().equals("")){
						pp.get(x).fPayAmt.setValue("0");
					}
					else {
						calculate();
					}
			}
			
			}
		else if(event.getTarget().equals(fPlus)){
			addTypePay();
		}
		
		else if ( action.equals(ConfirmPanel.A_OK)) {
			// Process Payment: first Process Order (if needed)
			if ( !p_order.isProcessed() && !p_order.processOrder() ) {
					FDialog.warn(0, Msg.getMsg(p_ctx, "PosOrderProcessFailed"));
					return;
			}
			for(int x = 0; x < pp.size(); x++)
				pp.get(x).savePay();
			onClose();
		}
		else if ( action.equals(ConfirmPanel.A_CANCEL))	{
			onClose();
			return;
		}
		else {
			for(int x = 0; x < fMinus.size(); x++){
				if(event.getTarget().getId().equals(fMinus.get(x).getId())){
					fMinus.get(x).detach();
					pp.get(x).getMainPanel().detach();
					fMinus.remove(x);
					pp.remove(x);
					layout.invalidate();
				}
			}
		}
	}
	private void calculate() {
		BigDecimal mount = new BigDecimal(Env.ZERO.toString());
		for(int x = 0; x < pp.size(); x++){
				BigDecimal pay = new BigDecimal(pp.get(x).fPayAmt.getValue());
				mount = mount.add(pay);
		}
		
		balance  = p_order.getGrandTotal().subtract(mount);
		if ( balance.compareTo(Env.ZERO) <= 0 )
		{
			paid = true;
			
		}
		fBalance.setValue(balance.toString());
		fReturnAmt.setValue(balance.toString());
		fPayAmt.setValue(mount.toString());
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
		pay.setHeight("580px"); ;
		pay.setClosable(true);
		AEnv.showWindow(pay);
		return pay.isPaid();
	}

	private boolean isPaid() {
		return paid ;
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
