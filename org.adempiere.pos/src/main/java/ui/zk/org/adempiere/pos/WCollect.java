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
import java.util.Properties;
import java.util.logging.Level;

import javax.swing.KeyStroke;

import org.adempiere.pipo.exception.POSaveFailedException;
import org.adempiere.pos.service.Collect;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MPOSKey;
import org.compiere.model.X_C_Payment;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Space;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raúl Muñoz, rmunoz@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li>Change Name
 */
public class WCollect extends Collect implements WPOSKeyListener, EventListener,POSPanelInterface {
	
	/**
	 * 
	 * *** Constructor ***
	 * @param posPanel
	 */
	public WCollect(WPOS posPanel) {
		super(posPanel.getCtx(), posPanel.getOrder(), posPanel.getM_POS());
		this.posPanel = posPanel;
		p_ctx = posPanel.getCtx();
		m_Format = DisplayType.getNumberFormat(DisplayType.Amount);
		init();
			
	}
	/** 
	 * Load Collect
	 * @return WCollect 
	 */
	public WCollect load (WPOS posPanel)
	{
		//	Instance Collects
		load(posPanel.getCtx() , posPanel.getOrder() , posPanel.getM_POS());
		removeAllCollectDetail();
		collectRowNo = 0;
		calculatePanelData();
		refreshPanel();
		addCollectType();
		this.posPanel.disablePOSButtons();
		return this;
	}

	/**	Panels					*/
	private WPOS 				posPanel;
	private Panel 				mainPanel; 
	private Grid 				eastlayout;
	private Rows 				rows;
	private Row 				row;
	private North 				north;
	private Grid 				layout;
	private Panel 				centerPanel;
	
	/** Window					 */
	private Properties 			p_ctx;
	
	/**	Fields Summary			*/
	private Label 				fPaidAmt;
	private Label 				fPayAmt;
	private BigDecimal balance = Env.ZERO;
	private Label 				fReturnAmt;
	private Label 				lReturnAmt;
	private Label 				fOpenAmt;
	private DecimalFormat 		m_Format;
	private Label				fsPaidAmtLabel;
	
	/**	Action					*/
	private Button 				bPlus;
	private ConfirmPanel 		confirm;

	/**	Generic Values			*/
	private boolean 			isProcessed;
	private int 				collectRowNo = 0;
	
	/**	Log						*/
	private CLogger 			log = CLogger.getCLogger(WCollect.class);
	/**	Default	Font Size		*/
	private final String 		FONT_SIZE = "Font-size:medium;";
	/**	Default	Font Weight		*/
	private final String 		FONT_BOLD = "font-weight:700";
	/**	Default	Screen Small	*/
	private final int 			SCREEN_SMALL = 609;
	/**	Default	Screen Small	*/
	private final int 			SCREEN_MEDIUM = 756;
	/**	Default	Screen Medium	*/
	private final int 			SCREEN_NORMAL = 907;
	/**	Default	Screen Large	*/
	private final int 			SCREEN_LARGE = 1022;
	

	/**
	 * Init Dialog
	 * @return void
	 */
	private void init() {
		log.info("");
		try {
			zkInit();
			refreshPanel();
		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
		}
	}
	/**
	 * Instance Window and fill fields
	 * @return void
	 */
	private void zkInit(){
//		
		mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		layout = GridFactory.newGridLayout();
		eastlayout = GridFactory.newGridLayout();
		
		//	Panels
		centerPanel = new Panel();
		Panel eastPanel = new Panel();
		mainPanel.appendChild(mainLayout);
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
		Label fsLabel = new Label(Msg.translate(p_ctx, "PayAmt")+":");
		fsLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fPayAmt = new Label();
//		fPayAmt.setText(getPrePayAmt().toString());
		row.appendChild(fsLabel.rightAlign());
		row.appendChild(fPayAmt.rightAlign());
		fPayAmt.setStyle(FONT_SIZE);
		
		row = rows.newRow();
		row.appendChild(new Space());
		//	Add Payment Amount
		Label lOpenAmt = new Label(Msg.translate(p_ctx, "OpenAmt") + ":");
		lOpenAmt.setStyle(FONT_SIZE+FONT_BOLD);
		row.appendChild(lOpenAmt.rightAlign());
		
		fOpenAmt = new Label();
		fOpenAmt.setStyle(FONT_SIZE);
			
		row.appendChild(fOpenAmt.rightAlign());
			
		fReturnAmt = new Label();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned")+":");
		lReturnAmt.setStyle(FONT_SIZE+FONT_BOLD);
		fReturnAmt.setStyle(FONT_SIZE);

		row = rows.newRow();
		row.appendChild(new Space());
		fReturnAmt = new Label();
		lReturnAmt = new Label(Msg.translate(p_ctx, "AmountReturned")+":");
		lReturnAmt.setStyle(FONT_SIZE+FONT_BOLD);
		fReturnAmt.setStyle(FONT_SIZE);
		row.appendChild(lReturnAmt.rightAlign());
		row.appendChild(fReturnAmt.rightAlign());
		fReturnAmt.addEventListener("onFocus", this);
		
		row = rows.newRow();
		row.appendChild(new Space());
		fsPaidAmtLabel = new Label(Msg.translate(p_ctx, "PaidAmt")+":");
		fsPaidAmtLabel.setStyle(FONT_SIZE+FONT_BOLD);
		fPaidAmt = new Label();
		row.appendChild(fsPaidAmtLabel.rightAlign());
		row.appendChild(fPaidAmt.rightAlign());
		fPaidAmt.setStyle(FONT_SIZE);

		// Button Plus
		bPlus = createButtonAction("Plus", KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		row = rows.newRow();

		row.appendChild(new Space());
		row.appendChild(new Space());

		row.appendChild(bPlus);
		confirm = new ConfirmPanel(true);
		confirm.addActionListener(this);
		confirm.getOKButton().setWidth("55px");
		confirm.getOKButton().setHeight("55px");
		confirm.getButton(ConfirmPanel.A_CANCEL).setWidth("55px");
		confirm.getButton(ConfirmPanel.A_CANCEL).setHeight("55px");
		
		row.appendChild(confirm);

		row.setHeight("60px");
		Center center = new Center();
		center.setStyle("border: none; overflow-y:auto;overflow-x:hidden;");
		mainLayout.appendChild(center);
		center.appendChild(centerPanel);
		centerPanel.appendChild(layout);
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setStyle("overflow:auto;");
		
		rows = layout.newRows();
		row = rows.newRow();
		row.setWidth("100%");


		// Completed Standard Order: only prepayment possible 
		if(posPanel.getTotalLines().compareTo(Env.ZERO)==1 &&
		   posPanel.isCompleted() &&
		   posPanel.isStandardOrder()) {
			//fIsPrePayOrder.setEnabled(false);
//			fIsCreditOrder.setEnabled(false);
			//fIsPrePayOrder.setSelected(true);
		}
		// Not completed Order 
		else if(posPanel.getTotalLines().compareTo(Env.ZERO)==1 &&
				!posPanel.isCompleted()) {
			if(posPanel.isStandardOrder() /*|| pos.isWarehouseOrder()*/) {
				 // Standard Order or Warehouse Order: no Credit Order, no prepayment
				//fIsPrePayOrder.setEnabled(false);
				//fIsPrePayOrder.setSelected(false);
//				fIsCreditOrder.setEnabled(false);
//				fIsCreditOrder.setSelected(false);
			}
			else {		
				//fIsPrePayOrder.setEnabled(true);
//				fIsCreditOrder.setEnabled(true);
			}
		}
		else {
			//fIsPrePayOrder.setEnabled(false);
//			fIsCreditOrder.setEnabled(false);
//			if(posPanel.isCompleted() &&
//				posPanel.getM_Order().isInvoiced()  &&
//				posPanel.getOpenAmt().compareTo(Env.ZERO)==1) {
//				fIsCreditOrder.setSelected(true);
//			}
		}
	}
	
	/**
	 * Add new Collect
	 * @return void
	 */
	private void addCollectType(){
		row = rows.newRow();
		
		String tenderType = X_C_Payment.TENDERTYPE_Cash;
		if(collectRowNo > 0) {
			tenderType = X_C_Payment.TENDERTYPE_Check;
		}
		BigDecimal balance = getBalance(posPanel.getOpenAmt());
		if(balance.doubleValue() < 0)
			balance = Env.ZERO;
		
		WCollectDetail collectDetail = new WCollectDetail(this, tenderType, balance);

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
	
	/**
	 * Create Button Action
	 * @return void
	 */
	protected Button createButtonAction (String action, KeyStroke accelerator)	{
		Button button = new Button();
		button.setImage("images/"+action+"24.png");
		button.setTooltiptext(Msg.translate(p_ctx, action));
		button.setWidth("55px");
		button.setHeight("55px");
		button.addActionListener(this);
		return button;
	}	//	getButtonAction
	

	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		String action = event.getTarget().getId();
		if (event.getName().equals("onBlur") || event.getName().equals("onFocus")) {
		}
		else if(event.getTarget().equals(bPlus)){
			addCollectType();
			return;
		}
		
		else if ( action.equals(ConfirmPanel.A_OK)) {
			//	Validate before process
			String validResult = validatePayment();
			if(validResult == null) {
				validResult = executePayment();
			}
			//	Show error dialog
			if(validResult != null) {
				FDialog.warn(0, Msg.parseTranslation(p_ctx, validResult));
				return;
			}
			
			// Process printing
			isProcessed = true;
			if(!posPanel.isStandardOrder() && !posPanel.isWarehouseOrder() && posPanel.isToPrint())
				printTicketWeb();
			hidePanel();
			posPanel.showKeyboard();
			posPanel.setOrder(0);
			posPanel.refreshPanel();
			posPanel.refreshProductInfo(null);
            posPanel.restoreTable();
			return;
		}

		else if ( action.equals(ConfirmPanel.A_CANCEL)) {
			hidePanel();
			posPanel.showKeyboard();
			posPanel.refreshPanel();
            posPanel.restoreTable();
            return;
		}
	}
	
	/**
	 * Remove All Collect Detail
	 * @return void
	 */
	public void removeAllCollectDetail() {
		layout.getChildren().clear();
		super.removeAllCollectDetail();
		//	Refresh View
		rows = layout.newRows();
		row = rows.newRow();
		collectRowNo = 0;
		layout.invalidate();
	}
	/**
	 * Process Window
	 * @return
	 * @return String
	 */
	public String executePayment() {
		String errorMsg = null;
		try {
			Trx.run(new TrxRunnable() {
				public void run(String trxName) {
					if(posPanel.processOrder(trxName, isAllowsPartialPayment(), getBalance(posPanel.getOpenAmt()).doubleValue() <= 0)) {
						processTenderTypes(trxName, posPanel.getOpenAmt());
						String error = getErrorMsg();
						if(error != null && error.length() > 0)
							throw new POSaveFailedException(Msg.parseTranslation(p_ctx, "@order.no@ " + posPanel.getDocumentNo() + ": "  + getErrorMsg()));
					} else {
						throw new POSaveFailedException(Msg.parseTranslation(p_ctx, "@order.no@ " + posPanel.getDocumentNo() + ": "  +
				                 "@ProcessRunError@" + " (" +  posPanel.getProcessMsg() + ")"));
					}
				}
			});
		} catch (Exception e) {
			errorMsg = e.getLocalizedMessage();
		} finally {
		}
		//	Default
		return errorMsg;
	}
	
	/**
	 * Show Collect
	 * @return boolean
	 */
	public boolean showCollect() {
		showPanel();
		mainPanel.setWidth("99%");
		int p_height = SessionManager.getAppDesktop().getClientInfo().desktopHeight;
		if(p_height < SCREEN_SMALL) {
			mainPanel.setHeight(p_height/2.5+"px");
		}
		if(p_height < SCREEN_MEDIUM) {
			mainPanel.setHeight(p_height/2+"px");
		}
		else if(p_height < SCREEN_NORMAL) {
			mainPanel.setHeight(p_height/1.7+"px");
		}
		else if(p_height < SCREEN_LARGE) {
			mainPanel.setHeight(p_height/1.6+"px");
		}
		else {
			mainPanel.setHeight(p_height/2 + "px");	
		}
			
		return isProcessed();
	}

	/**
	 * Calculate and change data in panel
	 * @return void
	 */
	public void calculatePanelData(){
		//	Get from controller
		BigDecimal collectDetail = getCollectDetailAmt();
		//
		//m_PayAmt= m_PayAmt.add(getPrePayAmt());
		balance = getBalance(posPanel.getOpenAmt());
		balance = balance.setScale(2, BigDecimal.ROUND_HALF_UP);
		String currencyISO_Code = posPanel.getCurSymbol();
		//	Change View
		//fGrandTotal.setText(currencyISO_Code +" "+ m_Format.format(posPanel.getGrandTotal()));
		fPayAmt.setText(currencyISO_Code +" "+ posPanel.getNumberFormat().format(collectDetail));
		if(isAllowsPartialPayment()) {
			fsPaidAmtLabel.setVisible(true);
			fPaidAmt.setVisible(true);
			fPaidAmt.setText(currencyISO_Code + " "
					+ posPanel.getNumberFormat().format(posPanel.getPaidAmt()));
		}
		else
		{
			fsPaidAmtLabel.setVisible(false);
			fPaidAmt.setVisible(false);
		}
		BigDecimal m_ReturnAmt = Env.ZERO;
		BigDecimal m_OpenAmt = Env.ZERO;
		if(balance.doubleValue() < 0) {
			m_ReturnAmt = balance.abs();
		} else if(balance.doubleValue() > 0){
			m_OpenAmt = balance;
		}
		//	Set Return Amount
		fReturnAmt.setText(currencyISO_Code +" "+ m_Format.format(m_ReturnAmt));
		//	Set Open Amount
		fOpenAmt.setText(currencyISO_Code + " " 
				+ posPanel.getNumberFormat().format(m_OpenAmt));
	}
	@Override
	public void refreshPanel() {
		calculatePanelData();
		//	
		changeViewPanel();
	}

	@Override
	public String validatePayment() {
		String errorMsg = null;
		if(!posPanel.hasOrder()) {	//	When is not created order
			errorMsg = "@POS.MustCreateOrder@";
		} else {
			if(!(posPanel.isStandardOrder()))
				// No Check if Order is not Standard Order
				// TODO: Review why nor Warehouse Order
				errorMsg = validateTenderTypes(posPanel.getOpenAmt());
		}
		//	
		return errorMsg;
	}

	private boolean isProcessed() {
		return isProcessed ;
	}

//	public String getGranTotal(){
//		return fGrandTotal.getValue();
//	}

	/**
	 * Get Keyboard
	 * @return POSKeyboard
	 */
	public WPOSKeyboard getKeyboard() {
		return posPanel.getKeyboard();
	}

	@Override
	public void changeViewPanel() {
//		Set Credit for Complete Documents
		boolean isCreditOpen = (posPanel.isCompleted()
				&& posPanel.getOpenAmt().doubleValue() > 0);
		//	Is Standard Order
		boolean isStandardOrder = posPanel.isStandardOrder();
		//	Set Credit Order
		setIsCreditOrder(isCreditOrder() 
				|| (isCreditOpen && !isStandardOrder));
		//	
		//setIsPrePayOrder(isAllowsPartialPayment()
		//		|| (isCreditOpen && isStandardOrder));
		//	Set Credit and Pre-Pay Order
//		fIsCreditOrder.setSelected(isCreditOrder());
		//fIsPrePayOrder.setSelected(isAllowsPartialPayment());
//			fPaymentTerm.setVisible(isCreditOrder());
		//	Verify complete order
		if(posPanel.isCompleted()) {
//			fIsCreditOrder.setEnabled(false);
			//fIsPrePayOrder.setEnabled(false);
//				fPaymentTerm.setEnabled(false);
			bPlus.setEnabled(isCreditOpen);
			confirm.getOKButton().setEnabled(true);
		} else if(posPanel.isVoided()){
//			fIsCreditOrder.setEnabled(false);
			//fIsPrePayOrder.setEnabled(false);
//				fPaymentTerm.setEnabled(false);
			bPlus.setEnabled(false);
			confirm.getOKButton().setEnabled(false);
		} else if(posPanel.isStandardOrder() /*|| pos.isWarehouseOrder()*/) {
			// Standard Order or Warehouse Order: no Credit Order, no prepayment
			//fIsPrePayOrder.setEnabled(false);
//			fIsCreditOrder.setEnabled(false);
			bPlus.setEnabled(false);
		}
		else {
//			fIsCreditOrder.setEnabled(true);
			//fIsPrePayOrder.setEnabled(true);
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
		org.zkoss.zul.Groupbox comp = child.getPanel();
		removeCollect(child);
		comp.detach();
		collectRowNo--;
	}

	

	@Override
	public void moveUp() {
		
	}

	@Override
	public void moveDown() {
		
	}

	@Override
	public void keyReturned(MPOSKey key) {
		
	}
	
	/**
	 * 	Print Ticket for Web
	 *  @return void
	 */
	public void printTicketWeb() {	
		
		try {
			//print standard document
			if (posPanel.isToPrint() && posPanel.hasOrder()) {
				posPanel.printTicket();
			}
		}
			catch (Exception e) 
			{
				log.severe("PrintTicket - Error Printing Ticket");
			}			  
	}

	/**
	 * Close Collect Payment Panel
	 * @return void
	 */
	/*public void closeCollectPayment()
	{
		row.removeChild(collectPayment.getPanel());
		row.setHeight("50%");
		row.setSpans("4");
		row.appendChild(keyboardPanel);
	}*/

	public void hidePanel()
	{
		mainPanel.setVisible(false);
		posPanel.closeCollectPayment();
	}
	public void showPanel()
	{
		mainPanel.setVisible(true);
	}

	public Panel getPanel() {
		return mainPanel;
	}

}
