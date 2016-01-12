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

import javax.swing.KeyStroke;

import org.adempiere.pos.search.WQueryBPartner;
import org.adempiere.pos.search.WQueryDocType;
import org.adempiere.pos.search.WQueryProduct;
import org.adempiere.pos.search.WQueryTicket;
import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.pos.service.I_POSQuery;
import org.adempiere.pos.service.POSQueryListener;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MPOSKey;
import org.compiere.model.MWarehousePrice;
import org.compiere.pos.PosKeyListener;
import org.compiere.print.ReportCtl;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Space;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSActionPanel extends WPOSSubPanel implements PosKeyListener, I_POSPanel, POSQueryListener{

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
	/**	Is Keyboard			*/
	private boolean			isKeyboard;
	/**	For Show BPartner	*/
	private	WPOSTextField 	fieldProductName;
	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(WPOSActionPanel.class);

	private final String ACTION_NEW         = "New";
	private final String ACTION_DOCTYPE     = "DocType";
	private final String ACTION_BPARTNER    = "BPartner";
	private final String ACTION_HISTORY     = "History";
	private final String ACTION_BACK       	= "Parent";
	private final String ACTION_NEXT  		= "Detail";
	private final String ACTION_PAYMENT     = "Payment";
	private final String ACTION_CANCEL      = "Cancel";
	private final String ACTION_LOGOUT      = "End";
	/**	Info Product Panel	*/
	private WPOSInfoProduct infoProductPanel;
	/**	Paramenter Panel	*/
	private Panel 			parameterPanel;
	
	@Override
	public void init() {

		parameterPanel = new Panel();
		Grid parameterLayout = GridFactory.newGridLayout();
		Grid LayoutButton = GridFactory.newGridLayout();
		Rows rows = null;
		Row row = null;	
		isKeyboard = false;

		appendChild(parameterLayout);
		parameterLayout.setWidth("60%");
		rows = parameterLayout.newRows();
		row = rows.newRow();
		parameterLayout.setStyle("border: none; width:400px; height:100%;");
		LayoutButton.setStyle("border: none; width:400px; height:100%;");
		
		appendChild(LayoutButton);
		rows = LayoutButton.newRows();
		LayoutButton.setStyle("border:none");
		row = rows.newRow();
		row.setHeight("55px");

		row.appendChild(new Space());
		// NEW
		buttonNew = createButtonAction(ACTION_NEW, KeyStroke.getKeyStroke(KeyEvent.VK_F2, Event.F2));
		buttonNew.addActionListener(this);
		row.appendChild(buttonNew);

		// DocType 
		buttonDocType = createButtonAction(ACTION_DOCTYPE, KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.F4));
		buttonDocType.addActionListener(this);
		buttonDocType.setTooltiptext(Msg.translate(ctx, "C_DocType_ID"));
		
		row.appendChild(buttonDocType);
		// BPartner Search
		buttonBPartner = createButtonAction(ACTION_BPARTNER, KeyStroke.getKeyStroke(KeyEvent.VK_F3, Event.F3));
		buttonBPartner.addActionListener(this);
		buttonBPartner.setTooltiptext(Msg.translate(ctx, "IsCustomer"));
		row.appendChild(buttonBPartner);
				
		// HISTORY
		buttonHistory = createButtonAction(ACTION_HISTORY, null);
		buttonHistory.addActionListener(this);
		row.appendChild(buttonHistory);

		buttonBack = createButtonAction(ACTION_BACK, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
		buttonBack.setTooltiptext(Msg.translate(ctx, "Previous"));
		row.appendChild (buttonBack);
		buttonNext = createButtonAction(ACTION_NEXT, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));
		buttonNext.setTooltiptext(Msg.translate(ctx, "Next"));
		row.appendChild (buttonNext);
		
		// PAYMENT
		buttonCollect = createButtonAction(ACTION_PAYMENT, null);
		buttonCollect.addActionListener(this);
		row.appendChild(buttonCollect);
		buttonCollect.setEnabled(false);

		// Cancel
		buttonCancel = createButtonAction (ACTION_CANCEL, null);
		buttonCancel.addActionListener(this);
		buttonCancel.setTooltiptext(Msg.translate(ctx, "POS.IsCancel"));
		row.appendChild (buttonCancel);
		buttonCancel.setEnabled(false);
		
		// LOGOUT
		buttonLogout = createButtonAction (ACTION_LOGOUT, null);
		buttonLogout.addActionListener(this);
		buttonLogout.setTooltiptext(Msg.translate(ctx, "End"));
		row.appendChild (buttonLogout);
		row.appendChild(new Space());
		
		row = rows.newRow();
		row.setSpans("1, 9");
		row.setHeight("55px");

		fieldProductName = new WPOSTextField(Msg.translate(Env.getCtx(), "M_Product_ID"), posPanel.getKeyboard());
		fieldProductName.setWidth("98%");
		fieldProductName.setHeight("35px");
		fieldProductName.setStyle("Font-size:medium; font-weight:bold");
		fieldProductName.addEventListener(this);
		fieldProductName.setValue(Msg.translate(Env.getCtx(), "M_Product_ID"));

		row.appendChild(new Space());
		row.appendChild(fieldProductName);
		enableButton();
		
		infoProductPanel = new WPOSInfoProduct(posPanel);
		row = rows.newRow();
		row.setSpans("1,9");
		row.appendChild(new Space());
		row.appendChild(infoProductPanel.getPanel());
		//	List Orders
		posPanel.listOrder();
	}
	/**
	 * 	Print Ticket
	 *  @return void
	 */
	public void printTicket() {
		if (!posPanel.hasOrder())
			return;
		
		try {
			//print standard document
				Trx.run(new TrxRunnable() {
					public void run(String trxName) {
						if (posPanel.getAD_Sequence_ID()!= 0) {
						
							String docno = posPanel.getSequenceDoc(trxName);
							String q = "Confirmar el número consecutivo "  + docno;
							if (FDialog.ask(0, null, "", q)) {
								posPanel.setPOReference(docno);
								posPanel.saveNextSeq(trxName);
							}
						}
					}
				});
			
				ReportCtl.startDocumentPrint(0, posPanel.getC_Order_ID(), false);
			}
			catch (Exception e) 
			{
				logger.severe("PrintTicket - Error Printing Ticket");
			}
			  
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
	
	/**
	 * 
	 */
	private void payOrder() {

		//Check if order is completed, if so, print and open drawer, create an empty order and set cashGiven to zero
		if( posPanel.getM_Order() == null ) {
				FDialog.warn(0, Msg.getMsg(ctx, "POS.MustCreateOrder"));
				return;
		}
		WCollect collect = new WCollect(posPanel);
		if (collect.showCollect()) {
			if(posPanel.isToPrint()) {
				printTicket();
			}
			//	
			posPanel.setOrder(0);
			posPanel.refreshPanel();
			refreshProductInfo(null);
		}
	}
	
	/**
	 * 	Find/Set Product & Price
	 */
	private void findProduct()
	{
		String query = fieldProductName.getText();
		if (query == null || query.length() == 0)
			return;
		query = query.toUpperCase();
		//	Test Number
		boolean allNumber = true;
		try
		{
			Integer.getInteger(query);
		}
		catch (Exception e)
		{
			allNumber = false;
		}
		String Value = query;
		String Name = query;
		String UPC = (allNumber ? query : null);
		String SKU = (allNumber ? query : null);
		
		MWarehousePrice[] results = null;

		//
		results = MWarehousePrice.find  (ctx,
				posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID(),
				Value, Name, UPC, SKU, null);
		
		//	Set Result
//		if (results.length == 0)
//		{
//			String message = Msg.getMsg(p_ctx,  "POS.SearchProductNF");
//			FDialog.warn(0, null, message +" "+ query,"");
//		}
		if (results.length == 1)
		{
			posPanel.addLine(results[0].getM_Product_ID(), Env.ONE);
			fieldProductName.setValue(results[0].getName());
			
		}
		else	//	more than one
		{
			WQueryProduct qt = new WQueryProduct(posPanel);
			qt.setResults(results);
			qt.setQueryData(posPanel.getM_PriceList_Version_ID(), posPanel.getM_Warehouse_ID());
			AEnv.showWindow(qt);
			Object[] result = qt.getSelectedKeys();
			if(result == null) 
				return;
			
			for(Object item : result) {
				fieldProductName.setText(fieldProductName.getTitle());
				posPanel.addLine((Integer)item, Env.ONE);
			}
		}
	}	//	findProduct

	
	
	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event e) throws Exception {
		
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
			refreshProductInfo(null);
		} 
		else if (e.getTarget().equals(buttonDocType)){
			WQueryDocType qt = new WQueryDocType(posPanel);
			qt.setVisible(true);
			AEnv.showWindow(qt);
		}
		else if(e.getTarget().equals(buttonCollect)){
			payOrder();
			return;
		}
		else if (e.getTarget().equals(buttonBack)){
			previousRecord();
			refreshProductInfo(null);
			posPanel.changeViewPanel();
		}
		else if (e.getTarget().equals(buttonNext)){
			nextRecord();
			refreshProductInfo(null);
			posPanel.changeViewPanel();
		}
		else if(e.getTarget().equals(buttonLogout)){
			dispose();
			return;
		}
		else if (e.getTarget().equals(buttonBPartner)) {
			WQueryBPartner qt = new WQueryBPartner(posPanel);
			if(!posPanel.isBPartnerStandard())
				qt.loadData();
			AEnv.showWindow(qt);
			if (qt.getRecord_ID() > 0) {
				if(!posPanel.hasOrder()) {
					posPanel.newOrder(qt.getRecord_ID());
					posPanel.refreshPanel();
				} else {
					posPanel.setC_BPartner_ID(qt.getRecord_ID());
				}
				logger.fine("C_BPartner_ID=" + qt.getRecord_ID());
			}
		}
		// Cancel
		else if (e.getTarget().equals(buttonCancel)){
			deleteOrder();
			refreshProductInfo(null);
		}
		//	History
		if (e.getTarget().equals(buttonHistory)) {
			
			WQueryTicket qt = new WQueryTicket(posPanel);
			qt.setVisible(true);
			AEnv.showWindow(qt);
			posPanel.reloadIndex(qt.getRecord_ID());
		}
		posPanel.refreshPanel();

	}

	@Override
	public void refreshPanel() {
		
	}

	@Override
	public String validatePanel() {
		return null;
	}

	/**
	 * Previous Record Order
	 */
	public void previousRecord() {
		posPanel.previousRecord();
		//	Refresh
		posPanel.refreshPanel();
	}

	/**
	 * Next Record Order
	 */
	public void nextRecord() {
		posPanel.nextRecord();
		//	Refresh
		posPanel.refreshPanel();
	}
	
	@Override
	public void changeViewPanel() {
		if(posPanel.hasOrder()) {
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
	
	/**
	 * Refresh Product Info from Key
	 * @param key
	 * @return void
	 */
	public void refreshProductInfo(MPOSKey key) {
		infoProductPanel.refreshProduct(key);
		parameterPanel.invalidate();
	}
	
	/**
	 * Refresh Product Info from Product
	 * @param p_M_Product_ID
	 * @return void
	 */
	public void refreshProductInfo(int p_M_Product_ID) {
		infoProductPanel.refreshProduct(p_M_Product_ID);
		parameterPanel.invalidate();
	}
	
	@Override
	public void okAction(I_POSQuery query) {
		if (query.getRecord_ID() <= 0)
			return;
		//	For Ticket
		if(query instanceof WQueryTicket) {
			posPanel.setOrder(query.getRecord_ID());
			posPanel.reloadIndex(query.getRecord_ID());
		} else if(query instanceof WQueryBPartner) {
			if(!posPanel.hasOrder()) {
				posPanel.newOrder(query.getRecord_ID());
			} else {
				posPanel.setC_BPartner_ID(query.getRecord_ID());
			}
			//	
			logger.fine("C_BPartner_ID=" + query.getRecord_ID());
		} else if(query instanceof WQueryProduct) {
			if (query.getRecord_ID() > 0) {
				posPanel.addLine(query.getRecord_ID(), Env.ONE);
			}
		}
		//	Refresh
		posPanel.refreshPanel();
	}
	
	@Override
	public void cancelAction(I_POSQuery query) {
		
	}
	@Override
	public void moveUp() {
	}
	@Override
	public void moveDown() {
	}	
	
	/**
	 * Reset Product Info 
	 * @return void
	 */
	public void resetProductInfo() {
		infoProductPanel.resetValues();
	}
}