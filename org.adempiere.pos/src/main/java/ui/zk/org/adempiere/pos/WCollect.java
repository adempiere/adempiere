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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.KeyStroke;

import org.adempiere.pipo.exception.POSaveFailedException;
import org.adempiere.pos.service.Collect;
import org.adempiere.pos.service.I_POSPanel;
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
import org.compiere.model.MOrder;
import org.compiere.model.MPOS;
import org.compiere.model.MPOSKey;
import org.compiere.model.X_C_Payment;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
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
public class WCollect extends Collect implements WPosKeyListener, EventListener,I_POSPanel {

	public WPOS v_POSPanel;
	public MPOS p_pos;
	private Properties p_ctx;
	private MOrder p_order;
	private Label fGrandTotal = new Label();
	private Label fPayAmt;
	private boolean isPaid = false;
	private BigDecimal m_Balance = Env.ZERO;
	private Checkbox fIsPrePayOrder;
	private Checkbox fIsCreditOrder;
	private Label fReturnAmt;
	private Label lReturnAmt;
	private Button bPlus;
	private ArrayList<Object> types;
	private final String FONT_SIZE = "Font-size:medium;";
	private final String FONT_BOLD = "font-weight:700";
	private Rows rows = null;
	private Row row = null;
	private Panel mainPanel; 
	private Grid eastlayout;
	private North north;
	private Grid layout;
	private DecimalFormat 	m_Format;
	private int 	collectRowNo = 0;
	private Window v_Window;
	/**	Log					*/
	private CLogger 		log = CLogger.getCLogger(WCollect.class);
	private ConfirmPanel confirm;
	private Panel centerPanel;
	public WCollect(WPOS posPanel) {
		super(posPanel.getCtx(), posPanel.getM_Order(), posPanel.getM_POS());
		
		v_POSPanel = posPanel;
		p_ctx = posPanel.getCtx();
		p_order = posPanel.getM_Order();
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);

		init();
	}

	private void init() {
		log.info("");
		try {
			zkInit();
			refreshPanel();
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
				
	}
	private void zkInit(){
		Panel panel = new Panel();
		//	Content
		v_Window = new Window();
		v_Window.setTitle(Msg.translate(p_ctx, "Payment"));
		v_Window.setClosable(true);
		
		mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		layout = GridFactory.newGridLayout();
		v_Window.appendChild(panel);
		eastlayout = GridFactory.newGridLayout();
		
		//	Panels
		centerPanel = new Panel();
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
		eastlayout.setWidth("100%");
		eastlayout.setHeight("100%");
		
		rows = eastlayout.newRows();
		
		row = rows.newRow();

		row.appendChild(new Space());
		
		Label gtLabel = new Label(Msg.translate(p_ctx, "GrandTotal")+":");
		gtLabel.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(gtLabel.rightAlign());
		row.appendChild(fGrandTotal.rightAlign());
		fGrandTotal.setStyle(FONT_SIZE);
		fGrandTotal.setValue(p_order.getGrandTotal().toString());
		
		row = rows.newRow();

		row.appendChild(new Space());
		Label fsLabel = new Label(Msg.translate(p_ctx, "PayAmt")+":");
		fsLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fPayAmt = new Label();
		fPayAmt.setText(getPrePayAmt().toString());
		row.appendChild(fsLabel.rightAlign());
		row.appendChild(fPayAmt.rightAlign());
		fPayAmt.setStyle(FONT_SIZE);
		
		row = rows.newRow();
		row.appendChild(new Space());
		row.appendChild(new Space());
		row.setHeight("5px");
		Label line = new Label ("____________________");
		row.appendChild(line.rightAlign());
		
		fReturnAmt = new Label();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned")+":");
		lReturnAmt.setStyle(FONT_SIZE+FONT_BOLD);
		fReturnAmt.setStyle(FONT_SIZE);
		row = rows.newRow();

		row.appendChild(new Space());
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt.rightAlign());
		fReturnAmt.addEventListener("onFocus", this);
		
		// Button Plus
		bPlus = createButtonAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		row = rows.newRow();

		row.appendChild(new Space());
		fIsPrePayOrder = new Checkbox();
		fIsPrePayOrder.addActionListener(this);
		fIsPrePayOrder.setText(Msg.translate(p_ctx, "isPrePayment"));
		fIsPrePayOrder.setStyle(FONT_SIZE+ "; Text-Align:right");
		
		row.appendChild(fIsPrePayOrder);
		fIsCreditOrder = new Checkbox();
		fIsCreditOrder.setText(Msg.translate(p_ctx, "CreditSale"));
		
		fIsCreditOrder.setStyle(FONT_SIZE);
		fIsCreditOrder.setHeight("30px");
		fIsCreditOrder.addActionListener(this);
		row.appendChild(fIsCreditOrder);
		row.appendChild(bPlus);
		row.setHeight("60px");
		Center center = new Center();
		center.setStyle("border: none; overflow-y:auto;overflow-x:hidden;");
		mainLayout.appendChild(center);
		center.appendChild(centerPanel);
		centerPanel.appendChild(layout);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setStyle("overflow:auto;");
		v_Window.appendChild(mainPanel);
		
		rows = layout.newRows();
		row = rows.newRow();
		row.setWidth("100%");

		//SHW End
		South south = new South();
		confirm = new ConfirmPanel(true);
		confirm.addActionListener(this);

		mainLayout.appendChild(south);
		south.appendChild(confirm);

		// Pre-Payment, Standard Order: enable only if the order is completed and there are lines 
		if(v_POSPanel.getTotalLines().compareTo(Env.ZERO)==1 && 
		   v_POSPanel.isCompleted() &&
		   v_POSPanel.isStandardOrder()) {	
			fIsPrePayOrder.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			fIsPrePayOrder.setSelected(true);
		}
		// Pre-Payment, Credit Order: enable only if the order is drafted and there are lines 
		else if(v_POSPanel.getTotalLines().compareTo(Env.ZERO)==1 && 
				!v_POSPanel.isCompleted()) {		
			fIsPrePayOrder.setEnabled(true);	
			fIsCreditOrder.setEnabled(true);
		}
		else {
			fIsPrePayOrder.setEnabled(false);	
			fIsCreditOrder.setEnabled(false);
			if(v_POSPanel.isCompleted() && 
				v_POSPanel.getM_Order().isInvoiced()  && 
				v_POSPanel.getOpenAmt().compareTo(Env.ZERO)==1) {
				fIsCreditOrder.setSelected(true);
			}
		}
	}
	/**
	 * Get Balance
	 * @return
	 * @return BigDecimal
	 */
	private BigDecimal getBalance() {
		BigDecimal m_PayAmt = getPayAmt();
		return v_POSPanel.getOpenAmt().subtract(m_PayAmt);
	}
	
	private void addCollectType(){
		row = rows.newRow();
		
		String tenderType = X_C_Payment.TENDERTYPE_Cash;
		if(collectRowNo > 0) {
			tenderType = X_C_Payment.TENDERTYPE_Check;
		}
		BigDecimal m_Balance = getBalance();
		if(m_Balance.doubleValue() < 0)
			m_Balance = Env.ZERO;
		
		WCollectDetail collectDetail = new WCollectDetail(this, tenderType, getBalance());

		//	Add Collect controller
		addCollect(collectDetail);
		// add parameter panel
		row.appendChild(collectDetail.getPanel());
		
		//	Repaint
		layout.invalidate();
		//	Add Count
		collectRowNo++;
		//	Calculate Data
		calculatePanelData();
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
		if(event.getTarget().equals(fIsCreditOrder)){
			fIsPrePayOrder.setSelected(false);
			if(fIsCreditOrder.isSelected()) {				
				bPlus.setVisible(false);  // TODO setEnable(false) doesn't work!!
				confirm.getOKButton().setEnabled(true);
			}
		else 
			bPlus.setVisible(true);
		}
		else if(event.getTarget().equals(fIsPrePayOrder)){
			fIsCreditOrder.setSelected(false);
			bPlus.setVisible(true);   // TODO setEnable(true) doesn't work!!
		}
		else if (event.getName().equals("onBlur") || event.getName().equals("onFocus")) {
		}
		else if(event.getTarget().equals(bPlus)){
			addCollectType();
			return;
		}
		
		else if ( action.equals(ConfirmPanel.A_OK)) {
			//	Validate before process
			String validResult = validatePanel();
			if(validResult == null) {
				validResult = saveData();
			}
			//	Show Dialog
			if(validResult != null) {
				FDialog.warn(0, Msg.parseTranslation(p_ctx, validResult));
				return;
			}
			
			isPaid = true;
			v_Window.dispose();
			return;
		}

		else if ( action.equals(ConfirmPanel.A_CANCEL)) {
			v_Window.dispose();
			return;
		}
		 else if(event.getTarget().equals(fIsPrePayOrder)) {	//	For Pre-Payment Order Checked
			fIsCreditOrder.setSelected(false);
			bPlus.setVisible(true); 
		}
		 else if(event.getTarget().equals(fIsCreditOrder)) {	//	For Credit Order Checked
				fIsPrePayOrder.setSelected(false);
				if(fIsCreditOrder.isSelected()) {				
					bPlus.setVisible(false);  
					confirm.getOKButton().setEnabled(true);
					removeAllCollectDetail();
				}
				else 
					bPlus.setVisible(true);
			} else if(event.getTarget().equals(fIsPrePayOrder)) {	//	For Pre-Payment Order Checked
				fIsCreditOrder.setSelected(false);
				bPlus.setVisible(true);   
			}
		else {
					layout.invalidate();
			return;
		}
	}
	
	/**
	 * Remove All Collect Detail
	 * @return void
	 */
	public void removeAllCollectDetail() {
		layout.getChildren().clear();
		centerPanel.getChildren().clear();
		super.removeAllCollectDetail();
		//	Refresh View
//		v_ScrollPanel.validate();
//		v_ScrollPanel.repaint();
	}
	/**
	 * Process Window
	 * @return
	 * @return String
	 */
	public String saveData() {
		try {
//			v_POSPanel.setPrepayment(fIsPrePayment.isSelected());
			setIsCreditOrder(fIsCreditOrder.isSelected());
//			setReturnAmt(new BigDecimal(fReturnAmt.getValue()));
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					if(v_POSPanel.processOrder(trxName, isPrePayOrder())) {
						processPayment(trxName, v_POSPanel.getOpenAmt());
					} else {
						throw new POSaveFailedException(v_POSPanel.getProcessMsg());
					}
				}
			});
		} catch (Exception e) {
			return e.getMessage();
		} finally {
		}
		//	Default
		return null;
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
			try	{
				Integer.parseInt(text);		// test if number
				fPayAmt.setText(payAmt + text);
			}
			catch (NumberFormatException e)	{
				// ignore non-numbers
			}
		}
	}
	
	public boolean showCollect() {
		
		v_Window.setWidth("445px");;
		v_Window.setHeight("580px"); ;
		v_Window.setClosable(true);
		AEnv.showWindow(v_Window);
		return isPaid();
	}

	public void calculatePanelData(){
		//	Get from controller
		BigDecimal m_PayAmt = getPayAmt();
		//
		m_PayAmt= m_PayAmt.add(getPrePayAmt());
		m_Balance = v_POSPanel.getGrandTotal().subtract(m_PayAmt);
		m_Balance = m_Balance.setScale(2, BigDecimal.ROUND_HALF_UP);
		String currencyISO_Code = v_POSPanel.getCurSymbol();
		//	Change View
		fGrandTotal.setText(currencyISO_Code +" "+ m_Format.format(v_POSPanel.getGrandTotal()));
		fPayAmt.setText(currencyISO_Code +" "+ m_Format.format(m_PayAmt));
		
		BigDecimal m_ReturnAmt = Env.ZERO;
		if(m_Balance.doubleValue() < 0) {
			m_ReturnAmt = m_Balance.abs();
		}
		fReturnAmt.setText(currencyISO_Code +" "+ m_Format.format(m_ReturnAmt));
	}
	@Override
	public void refreshPanel() {
		calculatePanelData();
		//	
		changeViewPanel();
	}

	@Override
	public String validatePanel() {
		if(!v_POSPanel.hasOrder()) {	//	When is not created order
			return "POS.MustCreateOrder";
		} else if(fIsCreditOrder.isSelected()) {	//	For Credit Order
			return null;
		} else if(!fIsPrePayOrder.isSelected() 
				&& m_Balance.doubleValue() > 0) {	//	For Pre-Payment Order
			return "POS.OrderPayNotCompleted";
		} else if(m_Balance.doubleValue() < 0) {
			return "POS.OrderPayNotCompletedAmtExceeded";
		}
		//	
		return null;
	}

	private boolean isPaid() {
		return isPaid ;
	}

	public String getGranTotal(){
		return fGrandTotal.getValue();
	}

	/**
	 * Get Keyboard
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return POSKeyboard
	 */
	public WPOSKeyboard getKeyboard() {
		return v_POSPanel.getKeyboard();
	}

	@Override
	public void changeViewPanel() {
//		Set Credit for Complete Documents
			boolean isCreditOpen = (v_POSPanel.isCompleted() 
					&& v_POSPanel.getOpenAmt().doubleValue() > 0);
			//	Is Standard Order
			boolean isStandardOrder = v_POSPanel.isStandardOrder();
			//	Set Credit Order
			setIsCreditOrder(isCreditOrder() 
					|| (isCreditOpen && !isStandardOrder));
			//	
			setIsPrePayOrder(isPrePayOrder()
					|| (isCreditOpen && isStandardOrder));
			//	Set Credit and Pre-Pay Order
			fIsCreditOrder.setSelected(isCreditOrder());
			fIsPrePayOrder.setSelected(isPrePayOrder());
//			fPaymentTerm.setVisible(isCreditOrder());
			//	Verify complete order
			if(v_POSPanel.isCompleted()) {
				fIsCreditOrder.setEnabled(false);
				fIsPrePayOrder.setEnabled(false);
//				fPaymentTerm.setEnabled(false);
				bPlus.setEnabled(isCreditOpen);
				confirm.getOKButton().setEnabled(true);
			} else if(v_POSPanel.isVoided()){
				fIsCreditOrder.setEnabled(false);
				fIsPrePayOrder.setEnabled(false);
//				fPaymentTerm.setEnabled(false);
				bPlus.setEnabled(false);
				confirm.getOKButton().setEnabled(false);
			} else {
				fIsCreditOrder.setEnabled(true);
				fIsPrePayOrder.setEnabled(true);
//				fPaymentTerm.setEnabled(true);
				bPlus.setEnabled(!isCreditOrder()
						|| isCreditOpen);
				confirm.getOKButton().setEnabled(true);
			}
	}
	
	/**
	 * Remove Collect Detal From Child
	 * @param child
	 * @return void
	 */
	public void removeCollectDetail(WCollectDetail child) {
		org.zkoss.zul.Panel comp = child.getPanel();
		removeCollect(child);
		comp.detach();
	
		
	}
	
}
