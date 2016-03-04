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

import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.search.WQueryDocType;
import org.adempiere.pos.search.WQueryProduct;
import org.adempiere.pos.search.WQueryOrderHistory;
import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.panel.InfoProductPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MPOSKey;
import org.compiere.pos.PosKeyListener;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Space;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSActionPanel extends WPOSSubPanel implements PosKeyListener, POSPanelInterface, POSQueryListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2131406504920855582L;
	
	/**
	 * 	Constructor
	 *	@param posPanel POS Panel
	 */
	public WPOSActionPanel (WPOS posPanel) {
		super (posPanel);
	}	//	WPOSActionPanel
		
	/**	Buttons Command		*/
	private Button 			buttonNew;
	private Button 			buttonDocType;
	private Button 			buttonBPartner;
	private Button 			buttonHistory;
	private Button 			buttonBack;
	private Button 			buttonNext;
	private Button 			buttonCollect;
	private Button 			buttonCancel;
	private Button 			buttonLogout;
	private Button 			buttonProcess;
	/**	Is Keyboard			*/
	private boolean			isKeyboard;
	/**	For Show BPartner	*/
	private	WPOSTextField 	fieldProductName;
	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(WPOSActionPanel.class);

	private final String ACTION_NEW         = "New";
	private final String ACTION_DOCTYPE     = "DocType";
	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_PROCESS     = "Process";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_BACK       	= "Parent";
	private final String ACTION_NEXT  		= "Detail";
	private final String ACTION_PAYMENT     = "Payment";
	private final String ACTION_CANCEL      = "Cancel";
	private final String ACTION_LOGOUT      = "End";

	/**	Paramenter Panel	*/
	private Panel 			parameterPanel;
	/**	Process Action 						*/
	private WPOSActionMenu actionProcessMenu;
	/** Product Lookup		*/
	private WPOSLookupProduct cmbSearch;
	
	@Override
	public void init() {

		parameterPanel = new Panel();
		Grid LayoutButton = GridFactory.newGridLayout();
		Rows rows = null;
		Row row = null;	
		isKeyboard = false;
		actionProcessMenu = new WPOSActionMenu(posPanel);
		LayoutButton.setStyle("border: none; width:400px; height:100%;");
		
		appendChild(LayoutButton);
		rows = LayoutButton.newRows();
		LayoutButton.setStyle("border:none");
		row = rows.newRow();
		row.setHeight("55px");

	
		// NEW
		buttonNew = createButtonAction(ACTION_NEW, "F2");
		buttonNew.addActionListener(this);
		row.appendChild(buttonNew);

		// DocType 
		buttonDocType = createButtonAction(ACTION_DOCTYPE, "F10");
		buttonDocType.addActionListener(this);
		buttonDocType.setTooltiptext("F10-"+Msg.translate(ctx, "C_DocType_ID"));
		
		row.appendChild(buttonDocType);
		// BPartner Search
		buttonBPartner = createButtonAction(ACTION_BPARTNER, "Alt+B");
		buttonBPartner.addActionListener(this);
		buttonBPartner.setTooltiptext("Alt+B-"+Msg.translate(ctx, "IsCustomer"));
		row.appendChild(buttonBPartner);
		
		buttonProcess = createButtonAction(ACTION_PROCESS, "Alt+P");
		buttonProcess.addActionListener(this);
		buttonProcess.setTooltiptext("ALT+P-"+Msg.translate(ctx, "Process"));
		
		row.appendChild(buttonProcess);
		
		// HISTORY
		buttonHistory = createButtonAction(ACTION_HISTORY, "F9");
		buttonHistory.addActionListener(this);
		row.appendChild(buttonHistory);
		
		buttonBack = createButtonAction(ACTION_BACK, "Alt+Left");
		buttonBack.setTooltiptext("Alt+Left-"+Msg.translate(ctx, "Previous"));
		row.appendChild (buttonBack);
		buttonNext = createButtonAction(ACTION_NEXT, "Alt+Right");
		buttonNext.setTooltiptext("Alt+Right"+Msg.translate(ctx, "Next"));
		row.appendChild (buttonNext);
		
		// PAYMENT
		buttonCollect = createButtonAction(ACTION_PAYMENT, "F4");
		buttonCollect.addActionListener(this);
		row.appendChild(buttonCollect);
		buttonCollect.setEnabled(false);

		// Cancel
		buttonCancel = createButtonAction (ACTION_CANCEL, "F3");
		buttonCancel.addActionListener(this);
		buttonCancel.setTooltiptext("F3-"+Msg.translate(ctx, "POS.IsCancel"));
		row.appendChild (buttonCancel);
		buttonCancel.setEnabled(false);
		
		// LOGOUT
		buttonLogout = createButtonAction (ACTION_LOGOUT, "Alt+L");
		buttonLogout.addActionListener(this);
		buttonLogout.setTooltiptext("Alt+L-"+Msg.translate(ctx, "End"));
		row.appendChild (buttonLogout);
		row.appendChild(new Space());
		
//		row = rows.newRow();
//		row.setSpans("1,9");
		row.setHeight("55px");

		fieldProductName = new WPOSTextField(Msg.translate(Env.getCtx(), "M_Product_ID"), posPanel.getKeyboard());
		fieldProductName.setWidth("98%");
		fieldProductName.setHeight("35px");
		
		Keylistener keyListener = new Keylistener();
		
    	keyListener.setCtrlKeys("#f2#f3#f4#f9#f10@b@#left@#right^l@i@p");
    	keyListener.addEventListener(Events.ON_CTRL_KEY, posPanel);
    	keyListener.addEventListener(Events.ON_CTRL_KEY, this);
    	keyListener.setAutoBlur(false);
    	
		fieldProductName.setStyle("Font-size:medium; font-weight:bold");
		fieldProductName.setValue(Msg.translate(Env.getCtx(), "M_Product_ID"));
		fieldProductName.addEventListener(this);

		row = rows.newRow();
		row.setSpans("10");
		if (posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard()) {
			cmbSearch = new WPOSLookupProduct(this, fieldProductName, new Long("1"));
			cmbSearch.setWidth("100%");
			cmbSearch.setStyle(WPOS.FONTSTYLE+WPOS.FONTSIZELARGE);
			fieldProductName.appendChild(keyListener);
			fieldProductName.setVisible(false);
			fieldProductName.setWidth("0%");
			cmbSearch.addEventListener(Events.ON_CHANGE, this);
	        row.appendChild(cmbSearch);
			row.appendChild(fieldProductName);
		} else {
			row.appendChild(fieldProductName);
			fieldProductName.appendChild(keyListener);
			fieldProductName.setWidth("98%");
		}
			
		
		enableButton();
		//	List Orders
		posPanel.listOrder();
	}

	/** 
	 * Open window Doctype 
	 */
	private void openDocType() { 
		WQueryDocType qt = new WQueryDocType(posPanel);
		qt.setVisible(true);
		AEnv.showWindow(qt);
	}
	
	private void openHistory() { 
		WQueryOrderHistory qt = new WQueryOrderHistory(posPanel);
		qt.setVisible(true);
		AEnv.showWindow(qt);
		posPanel.reloadIndex(qt.getRecord_ID());
	}
	
	private void openBPartner() {
		WQueryBPartner qt = new WQueryBPartner(posPanel);
		if(!posPanel.isBPartnerStandard())
			qt.loadData();
		AEnv.showWindow(qt);
		if (qt.getRecord_ID() > 0) {
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(qt.getRecord_ID());
				posPanel.refreshPanel();
			} else {
				posPanel.configureBPartner(qt.getRecord_ID());
			}
			logger.fine("C_BPartner_ID=" + qt.getRecord_ID());
		}
	}

	@Override
	public void onEvent(Event e) throws Exception {
		if(e.getName().equals(Events.ON_CHANGE)){
			if(cmbSearch.getSelectedRecord() >= 0) {
				posPanel.addOrUpdateLine(cmbSearch.getSelectedRecord(), Env.ONE);
				cmbSearch.setText("");
			}
        }

		if (Events.ON_CTRL_KEY.equals(e.getName())) {
    		KeyEvent keyEvent = (KeyEvent) e;
    		//F2 == 113
    		if (keyEvent.getKeyCode() == 113 ) {
    			posPanel.newOrder();
    		}
    		//F3 == 114
    		else if (keyEvent.getKeyCode() == 114 ) {
				posPanel.setUserPinListener(e);
				if (posPanel.isUserPinValid())
					deleteOrder();
    		}
    		//F4 == 115
    		else if (keyEvent.getKeyCode() == 115 ) {
    			payOrder();
    			return;
    		}
    		//F9 == 120
    		else if (keyEvent.getKeyCode() == 120 ) {
    			openHistory();
    		}
    		//F10 == 121
    		else if (keyEvent.getKeyCode() == 121 ) {
    			openDocType();
    		}
    		//Alt+b == 66
    		else if (keyEvent.getKeyCode() == 66 ) {
    			openBPartner();
    		}
    		//Alt+left == 37
    		else if (keyEvent.getKeyCode() == 37 ) {
    			previousRecord();
    		}
    		//Alt+right == 39
    		else if (keyEvent.getKeyCode() == 39 ) {
    			nextRecord();
    		}
    		//CTL+L == 76
    		else if (keyEvent.getKeyCode() == 76 ) {
    			dispose();
    			return;
    		}
    		//Alt+I == 73
    		else if (keyEvent.getKeyCode() == 73 ) {
    			showWindowProduct();
    			return;
    		}
    		//Alt+P == 80
    		else if (keyEvent.getKeyCode() == 80 ) {
    			actionProcessMenu.getPopUp().setPage(buttonProcess.getPage());
    			actionProcessMenu.getPopUp().open(150, 150);
    			return;
    		}
		}
		if(e.getTarget().equals(fieldProductName.getComponent(WPOSTextField.SECONDARY))
					&& e.getName().equals(Events.ON_FOCUS) && !isKeyboard){
				if(posPanel.isDrafted() || posPanel.isInProgress())  {
					isKeyboard = true;
					if(!fieldProductName.showKeyboard()){
						findProduct(); 
					}		
					fieldProductName.setFocus(true);	
				}
			}
			if(e.getTarget().equals(fieldProductName.getComponent(WPOSTextField.PRIMARY)) && e.getName().equals(Events.ON_FOCUS)){
				isKeyboard = false;
			}
		
		if (e.getTarget().equals(buttonNew)){
			posPanel.newOrder();
		} 
		else if (e.getTarget().equals(buttonDocType)){
			posPanel.setUserPinListener(e);
			if(posPanel.isUserPinValid()) {
				openDocType();
			}
		}
		else if(e.getTarget().equals(buttonCollect)){
			payOrder();
			return;
		}
		else if(e.getTarget().equals(buttonProcess)){
			posPanel.setUserPinListener(e);
			if(posPanel.isUserPinValid()) {
				actionProcessMenu.getPopUp().setPage(this.getPage());
				actionProcessMenu.getPopUp().open(buttonProcess);	
			}
			return;
		}
		else if (e.getTarget().equals(buttonBack)){
			previousRecord();
		}
		else if (e.getTarget().equals(buttonNext)){
			nextRecord();
		}
		else if(e.getTarget().equals(buttonLogout)){
			dispose();
			return;
		}
		else if (e.getTarget().equals(buttonBPartner)) {
			openBPartner();
		}
		// Cancel
		else if (e.getTarget().equals(buttonCancel)){
			posPanel.setUserPinListener(e);
			if(posPanel.isUserPinValid()) {
				deleteOrder();
			}
		}
		//	History
		if (e.getTarget().equals(buttonHistory)) {
			openHistory();
		}
		posPanel.refreshPanel();

	}
	
	/**
	 * Show Window Product
	 */
	private void showWindowProduct() {

		/*WQueryProduct qt = new WQueryProduct(posPanel);
		if(p_results != null)
			qt.setResults(p_results);
		qt.setQueryData(posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID());
		AEnv.showWindow(qt);
		Object[] result = qt.getSelectedKeys();
		if(result == null) 
			return;
		
		for(Object item : result) {
			fieldProductName.setText(fieldProductName.getTitle());
			posPanel.addOrUpdateLine((Integer)item, Env.ONE);
		}*/
		//	Show Info
		InfoProductPanel infoPanel = new InfoProductPanel(
				posPanel.getWindowNo(),
				true,
				posPanel.getM_Warehouse_ID(),
				posPanel.getM_PriceList_ID(),
				0 ,
				null ,
				true ,
				true ,
				null);

		Object[] result = infoPanel.getSelectedKeys();
		if(result == null)
			return;

		for (Object item : result)
		{
			int productId = (Integer)item;
			//String productValue = posPanel.getProductValue(productId);
			fieldProductName.setText(fieldProductName.getTitle());
			posPanel.addOrUpdateLine(productId, Env.ONE);
		}
	}

	public void getMainFocus() {
		/*SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (getProductTimer() != null)
					getProductTimer().restart();
				fieldProductName.requestFocusInWindow();
			}
		});*/
	}

	/**
	 * 	Find/Set Product & Price
	 */
	private void findProduct() throws Exception
	{
		String query = fieldProductName.getText();
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		try {
			Integer.getInteger(query);
		} catch (Exception e) {
			allNumber = false;
		}
		/*String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);*/
		List<Vector<Object>> results = CPOS.getQueryProduct(query, posPanel.getM_Warehouse_ID() , posPanel.getM_PriceList_Version_ID());
		//	Set Result
		if (results.size() == 1) {
			Optional<Vector<Object>> columns = results.stream().findFirst();
			if (columns.isPresent()) {
				Integer productId = (Integer) columns.get().elementAt(0);
				String productName = (String) columns.get().elementAt(2);
				posPanel.addOrUpdateLine(productId, Env.ONE);
				fieldProductName.setText(productName);
			}
		} else {	//	more than one

			InfoProductPanel infoPanel = new InfoProductPanel(
					posPanel.getWindowNo(),
					true,
					posPanel.getM_Warehouse_ID(),
					posPanel.getM_PriceList_ID(),
					0 ,
					query ,
					true ,
					true ,
					null);

			Object[] result = infoPanel.getSelectedKeys();
			if(result == null)
				return;

			for (Object item : result)
			{
				int productId = (Integer)item;
				//String productValue = posPanel.getProductValue(productId);
				fieldProductName.setText(fieldProductName.getTitle());
				posPanel.addOrUpdateLine(productId, Env.ONE);
			}
		}
	}	//	findProduct

	//@Override
	//public void quantityRequestFocus() {
	//	posPanel.quantityRequestFocus();
	//}
	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		posPanel.previousRecord();
		posPanel.refreshPanel();
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		posPanel.nextRecord();
		posPanel.refreshPanel();
	}

	/**
	 *
	 */
	private void payOrder() {

		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( posPanel.getM_Order() == null ) {
			FDialog.warn(0, Msg.getMsg(ctx, "POS.MustCreateOrder"));
			return;
		}
//		if (collect.show()) {
		posPanel.showCollectPayment();
//			if(posPanel.isToPrint()) {
//				printTicket();
//			}
		//
//			posPanel.setOrder(0);
//			posPanel.refreshPanel();
//			refreshProductInfo(null);
//		}
	}

	/**
	 * Execute deleting an order
	 * If the order is in drafted status -> ask to delete it
	 * If the order is in completed status -> ask to void it it
	 * Otherwise, it must be done outside this class.
	 */
	private void deleteOrder() {
		String errorMsg = null;
		String askMsg = "POS.DeleteOrder";
		if (posPanel.isCompleted()) {
			askMsg = "POS.OrderIsAlreadyCompleted";
		}
		//	Show Ask
		if (FDialog.ask(0, this, Msg.getMsg(ctx, askMsg))) {
			errorMsg = posPanel.cancelOrder();
		}
		if(errorMsg != null){
			FDialog.error(0,  Msg.parseTranslation(ctx, errorMsg));
			return;
		}
		//	Update
		posPanel.refreshPanel();
	} // deleteOrder

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {

	}
	@Override
	public void refreshPanel() {
		if(posPanel.hasOrder()) {
			if (cmbSearch != null && posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard()) {
				cmbSearch.setPriceListVersionId(posPanel.getM_PriceList_Version_ID());
				cmbSearch.setWarehouseId(posPanel.getM_Warehouse_ID());
			}
			//	For Next
			buttonNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			buttonBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			//	For Collect
			if(posPanel.hasLines()
					&& !posPanel.isPaid()
					&& !posPanel.isVoided()
					&& !posPanel.isReturnMaterial()) {
				//	For Credit Order
				buttonCollect.setEnabled(true);
			} else {
				buttonCollect.setEnabled(false);
			}
			// For BusinessPartner and Document Type
			if(posPanel.isDrafted() || posPanel.isInProgress()) {
				buttonDocType.setEnabled(true);
				buttonBPartner.setEnabled(true);
			} else {
				buttonDocType.setEnabled(false);
				buttonBPartner.setEnabled(false);
			} 
			//	For Cancel Action
			buttonCancel.setEnabled(!posPanel.isVoided());
			buttonNew.setEnabled(true);
			buttonHistory.setEnabled(true);
			buttonProcess.setEnabled(true);
		} else {
			buttonNew.setEnabled(true);
			buttonHistory.setEnabled(true);
			//	For Next
			buttonNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			buttonBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			buttonCollect.setEnabled(false);
			//	For Cancel Action
			buttonCancel.setEnabled(false);
			// For BusinessPartner and Document Type
			buttonDocType.setEnabled(false);
			buttonBPartner.setEnabled(false);
		}
		posPanel.changeViewQuantityPanel();
	}
	
	/**
	 * Enable Bttons
	 * @return void
	 */
	public void enableButton(){
		fieldProductName.setText(fieldProductName.getTitle());
		buttonNew.setEnabled(true);
		buttonCancel.setEnabled(false);
		buttonHistory.setEnabled(true);
		buttonCollect.setEnabled(false);
	}
	
	@Override
	public void keyReturned(MPOSKey key) {

	}
	
	@Override
	public void okAction(POSQueryInterface query) {
		if (query.getRecord_ID() <= 0)
			return;
		//	For Ticket
		if(query instanceof WQueryOrderHistory) {
			posPanel.setOrder(query.getRecord_ID());
			posPanel.reloadIndex(query.getRecord_ID());
		} else if(query instanceof WQueryBPartner) {
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(query.getRecord_ID());
			} else {
				posPanel.configureBPartner(query.getRecord_ID());
			}
			//	
			logger.fine("C_BPartner_ID=" + query.getRecord_ID());
		} else if(query instanceof WQueryProduct) {
			if (query.getRecord_ID() > 0) {
				posPanel.addOrUpdateLine(query.getRecord_ID(), Env.ONE);
			}
		}
		//	Refresh
		posPanel.refreshPanel();
	}
	
	@Override
	public void cancelAction(POSQueryInterface query) {
		
	}
	@Override
	public void moveUp() {
	}
	@Override
	public void moveDown() {
	}	

	public void resetPanel() {
		buttonNew.setEnabled(false);
		buttonHistory.setEnabled(false);
		buttonNext.setEnabled(false);
		buttonBack.setEnabled(false);
		buttonCollect.setEnabled(false);
		buttonCancel.setEnabled(false);
		buttonDocType.setEnabled(false);
		buttonBPartner.setEnabled(false);	
		buttonProcess.setEnabled(false);
	}
}