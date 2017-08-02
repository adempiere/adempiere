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

import java.util.List;
import java.util.Optional;
import java.util.Vector;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.search.WQueryDocType;
import org.adempiere.pos.search.WQueryOrderHistory;
import org.adempiere.pos.service.CPOS;
import org.adempiere.pos.service.POSLookupProductInterface;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.pos.service.POSQueryInterface;
import org.adempiere.pos.service.POSQueryListener;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.panel.InfoProductPanel;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MPOSKey;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkforge.keylistener.Keylistener;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.KeyEvent;
import org.zkoss.zul.Timer;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSActionPanel extends WPOSSubPanel
		implements POSKeyListener, POSPanelInterface, POSQueryListener ,  POSLookupProductInterface{

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
	}	//	WPosSubCustomer


	/**	Buttons Command		*/
	private Button 			buttonNew;
	private Button 			buttonPrint;
	private Button 			buttonDocType;
	private Button 			buttonProduct;
	private Button 			buttonBPartner;
	private Button 			buttonProcess;
	private Button 			buttonHistory;
	private Button 			buttonBack;
	private Button 			buttonNext;
	private Button 			buttonCollect;
	private Button 			buttonCancel;
	private Button 			buttonLogout;

	/**	Is Keyboard			*/
	private boolean			isKeyboard;
	/**	For Show Product	*/
	private	WPOSTextField 	fieldProductName;
	/** Find Product Timer **/
	private Timer findProductTimer;
	private WPOSLookupProduct lookupProduct;
	/**	Process Action 						*/
	private WPOSActionMenu actionProcessMenu;

	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(WPOSActionPanel.class);

	private final String ACTION_NEW         = "New";
	private final String ACTION_PRINT       = "Print";
	private final String ACTION_DOCTYPE     = "Assignment";
	private final String ACTION_PRODUCT     = "InfoProduct";
	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_PROCESS     = "Process";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_BACK       	= "Parent";
	private final String ACTION_NEXT  		= "Detail";
	private final String ACTION_PAYMENT     = "Payment";
	private final String ACTION_CANCEL      = "Cancel";
	private final String ACTION_LOGOUT      = "End";
	
	@Override
	public void init() {

		Grid LayoutButton = GridFactory.newGridLayout();
		Row row = null;	
		Rows rows = null;
			
		isKeyboard = false;
		LayoutButton.setStyle("border: none; width:400px; height:95%;");
		
		appendChild(LayoutButton);
		rows = LayoutButton.newRows();
		LayoutButton.setStyle("border:none");
		row = rows.newRow();
		row.setHeight("55px");
	
		// NEW
		buttonNew = createButtonAction(ACTION_NEW, "F2");
		buttonNew.addActionListener(this);
		row.appendChild(buttonNew);

		// PRINT
		buttonPrint = createButtonAction(ACTION_PRINT, "F12");
		buttonPrint.addActionListener(this);
		row.appendChild(buttonPrint);

		// DocType 
		buttonDocType = createButtonAction(ACTION_DOCTYPE, "F10");
		buttonDocType.addActionListener(this);
		buttonDocType.setTooltiptext("F10-"+Msg.translate(ctx, "C_DocType_ID"));
		
		row.appendChild(buttonDocType);
		// PRODUCT
		buttonProduct = createButtonAction(ACTION_PRODUCT, "Alt+I");
		buttonProduct.addActionListener(this);
		buttonProduct.setTooltiptext("Alt+I-"+Msg.translate(ctx, "InfoProduct"));
		row.appendChild(buttonProduct);
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
		row.setSpans("12");
		if (posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard()) {
			lookupProduct = new WPOSLookupProduct(this, fieldProductName, new Long("1"));
			lookupProduct.setPriceListId(posPanel.getM_PriceList_ID());
			lookupProduct.setPartnerId(posPanel.getC_BPartner_ID());
			lookupProduct.setWarehouseId(posPanel.getM_Warehouse_ID());
			findProductTimer = new Timer(500); // , lookupProduct);
			lookupProduct.setWidth("100%");
			lookupProduct.setStyle(WPOS.FONTSTYLE+WPOS.FONTSIZELARGE);
			fieldProductName.appendChild(keyListener);
			fieldProductName.setVisible(false);
			fieldProductName.setWidth("0%");
			findProductTimer.start();
			lookupProduct.addEventListener(Events.ON_CHANGE, this);
	        row.appendChild(lookupProduct);
			row.appendChild(fieldProductName);
		} else {
			row.appendChild(fieldProductName);
			fieldProductName.appendChild(keyListener);
			fieldProductName.setWidth("100%");
		}
		enableButton();
		actionProcessMenu = new WPOSActionMenu(posPanel);
		
		//	List Orders
		posPanel.listOrder();
		getMainFocus();
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
		try {
            if(e.getName().equals(Events.ON_CHANGE)){
                if(lookupProduct.getSelectedRecord() >= 0) {
                  lookupProduct.setText(String.valueOf(lookupProduct.getSelectedRecord()));
                    lookupProduct.captureProduct();
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
                    showWindowProduct(null);
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
                            findProduct(true);
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
			if (e.getTarget().equals(buttonPrint)){
				posPanel.printTicket();
			}
            else if (e.getTarget().equals(buttonDocType)){
                if(posPanel.isUserPinValid()) {
                    openDocType();
                }
            }
			else if (e.getTarget().equals(buttonProduct)) {
				showWindowProduct("");
			}
			else if (e.getTarget().equals(buttonBPartner)) {
				openBPartner();
			}
            else if(e.getTarget().equals(buttonCollect)){
            	if(posPanel.isReturnMaterial()) {
					completeReturn();
				} else {
					payOrder();
				}
                return;
            }
            else if(e.getTarget().equals(buttonProcess)){
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
            // Cancel
            else if (e.getTarget().equals(buttonCancel)){
                if(posPanel.isUserPinValid()) {
                    deleteOrder();
                }
            }
            //	History
            if (e.getTarget().equals(buttonHistory)) {
                openHistory();
            }
            posPanel.refreshPanel();
        } catch (Exception exception) {
            FDialog.error(posPanel.getWindowNo(), exception.getLocalizedMessage());
        }
	}
	
	/**
	 * Show Window Product
	 */
	private void showWindowProduct(String query) {
		//	Show Info
		//posPanel.getFrame().getContentPane().invalidate();
		InfoProductPanel infoProduct = new InfoProductPanel(
				posPanel.getWindowNo(),
				true,
				posPanel.getM_Warehouse_ID(),
				posPanel.getM_PriceList_ID() ,
				0,
				query,
				true,
				true,
				null);
		AEnv.showWindow(infoProduct);
		Object[] result = infoProduct.getSelectedKeys();
		if(result == null)
			return;
		if (infoProduct.isCancelled())
			return;
		for (Object item : result)
		{
			int productId = (Integer) item;
			if (productId > 0) {
				String value = posPanel.getProductValue(productId);
				fieldProductName.setText(value);
				try {
					posPanel.setAddQty(true);
					findProduct(true);
				} catch (Exception exception) {
					FDialog.error(0, this, exception.getLocalizedMessage());
				}
				fieldProductName.setText("");
				//fieldProductName.repaint();
			}
		}
	}

	/**
	 * Get the Main Focus
	 * 
	 * @return void
	 */
	public void getMainFocus() {

		if (posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard()) 
			lookupProduct.focus();
			else
			fieldProductName.focus();
	}

	/**************************************************************************
	 * 	Find/Set Product & Price
	 */
	public void findProduct(boolean editQty) throws Exception {
		if (getProductTimer() != null)
			getProductTimer().stop();
		String query;
		if(posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard())
		  query = String.valueOf(lookupProduct.getText());
		else
		  query = fieldProductName.getText();
		  fieldProductName.setText("");
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		try {
			Integer.getInteger(query);
		} catch (Exception e) {}
		//	
		List<Vector<Object>> results = CPOS.getQueryProduct(query, posPanel.getM_Warehouse_ID(), 
				posPanel.getM_PriceList_ID() , posPanel.getC_BPartner_ID());
		//	Set Result
		if (results.size() == 1) {
			Optional<Vector<Object>> columns = results.stream().findFirst();
			if (columns.isPresent()) {
				Integer productId = (Integer) columns.get().elementAt(0);
				String productName = (String) columns.get().elementAt(2);
				posPanel.setAddQty(true);
				posPanel.addOrUpdateLine(productId, editQty? Env.ZERO: Env.ONE);
				fieldProductName.setText(productName);
			}
		} else {	//	more than one
            showWindowProduct(query);
		}
		//	Change focus
		posPanel.refreshPanel();
		posPanel.changeViewPanel();
		//	
		if(editQty)
			quantityRequestFocus();
	}	//	findProduct


	public void quantityRequestFocus() {
		posPanel.quantityRequestFocus();
	}

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
	 * Complete Return Material
	 */
	private void completeReturn() {
		String errorMsg = null;
		String askMsg = "@new.customer.return.order@ @DisplayDocumentInfo@ : " + posPanel.getDocumentNo()
                + " @To@ @C_BPartner_ID@ : " + posPanel.getBPName();
		//	
		if (posPanel.isCompleted()) {
			return;
		}
		//	Show Ask
		if (FDialog.ask(posPanel.getWindowNo(), this, "StartProcess?", Msg.parseTranslation(posPanel.getCtx(), askMsg))) {
			try {
				posPanel.completeReturn();
			} catch(Exception e) {
				errorMsg = e.getLocalizedMessage();
			}
		}
		//	show if exists error
		if(errorMsg != null)
			FDialog.error(posPanel.getWindowNo(), Msg.parseTranslation(ctx, errorMsg));
		//	Update
		posPanel.refreshPanel();
	}

	/**
	 * Execute order payment
	 * If order is not processed, process it first.
	 * If it is successful, proceed to pay and print ticket
	 */
	private void payOrder() {
		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if(!posPanel.hasOrder()) {
			FDialog.warn(posPanel.getWindowNo(), Msg.getMsg(ctx, "POS.MustCreateOrder"));
		} else {
			posPanel.hideKeyboard();
			posPanel.showCollectPayment();
		}
	}  // payOrder

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
		if (FDialog.ask(0, this , Msg.getMsg(ctx, Msg.getMsg(ctx, askMsg)))) {
			//posPanel.getFrame().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//	Cancel Order
			errorMsg = posPanel.cancelOrder();
			//	Set Cursor to default
			//posPanel.getFrame().setCursor(Cursor.getDefaultCursor());
		}
		//	show if exists error
		if(errorMsg != null)
			FDialog.error(posPanel.getWindowNo(), Msg.parseTranslation(ctx, errorMsg));
		//	Update
		posPanel.refreshPanel();
	} // deleteOrder

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		refreshPanel();
	}

	@Override
	public void refreshPanel() {
		if(posPanel.hasOrder()) {
			if (lookupProduct != null && posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard()) {
				lookupProduct.setPriceListId(posPanel.getM_PriceList_ID());
				lookupProduct.setPartnerId(posPanel.getC_BPartner_ID());
				lookupProduct.setWarehouseId(posPanel.getM_Warehouse_ID());
			}

			//	For Next
			buttonNext.setEnabled(!posPanel.isLastRecord() && posPanel.hasRecord());
			//	For Back
			buttonBack.setEnabled(!posPanel.isFirstRecord() && posPanel.hasRecord());
			//	For Collect
			if(posPanel.hasLines()
					&& !posPanel.isPaid()
					&& !posPanel.isVoided()) {
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
		buttonNew.setEnabled(true);
		buttonHistory.setEnabled(true);
		buttonProcess.setEnabled(true);
	}

	/**
	 * Enable Bttons
	 * @return void
	 */
	public void enableButton(){
		buttonNew.setEnabled(true);
		buttonCancel.setEnabled(false);
		buttonHistory.setEnabled(true);
		buttonCollect.setEnabled(false);
	}


	@Override
	public void okAction(POSQueryInterface query) {
		try
		{
			if (query.getRecord_ID() <= 0)
				return;
			//	For Ticket
			if(query instanceof WQueryOrderHistory) {
				posPanel.setOrder(query.getRecord_ID());
				posPanel.reloadIndex(query.getRecord_ID());
			} else if(query instanceof WQueryBPartner) {
				if(!posPanel.hasOrder()) {
					posPanel.newOrder(query.getRecord_ID());
					posPanel.getMainFocus();
				} else {
					posPanel.configureBPartner(query.getRecord_ID());
				}
				//
				logger.fine("C_BPartner_ID=" + query.getRecord_ID());
			} else if(query instanceof WQueryDocType) {
				if (query.getRecord_ID() > 0) {
					posPanel.setC_DocType_ID(query.getRecord_ID());
				}
			}
			//	Refresh
			posPanel.refreshPanel();
		}
		catch (AdempiereException exception) {
			FDialog.error(posPanel.getWindowNo(), this, exception.getLocalizedMessage());
		} catch (Exception exception) {
			FDialog.error(posPanel.getWindowNo(), this, exception.getLocalizedMessage());
		}

	}

	@Override
	public void cancelAction(POSQueryInterface query) {
		//	Nothing
	}

	@Override
	public void moveUp() {
	}

	@Override
	public void moveDown() {
	}	

	public void disableButtons() {
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
	
	/**
	 * Reset Panel
	 */
	public void resetPanel() {
		fieldProductName.setValue(Msg.translate(Env.getCtx(), "M_Product_ID"));
	}

	public Timer getProductTimer()
	{
		return  findProductTimer;
	}

	@Override
	public void keyReturned(MPOSKey key) {

	}
	
	public void updateProductPlaceholder(String name)
	{
		if (posPanel.isEnableProductLookup() && !posPanel.isVirtualKeyboard()) 
			lookupProduct.setText(name);
		else
			fieldProductName.setText(name);
	}
}//	WPOSActionPanel