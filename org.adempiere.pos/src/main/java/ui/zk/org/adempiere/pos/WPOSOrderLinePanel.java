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

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;

import org.adempiere.pos.service.POSOrderLineTableHandle;
import org.adempiere.pos.service.POSPanelInterface;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.event.ListDataEvent;

/**
 * Button panel supporting multiple linked layouts
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 *  <li><a href="https://github.com/adempiere/adempiere/issues/533">
 *           BF/FR [ 533 ] Update Fields when selected line</a>
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WPOSOrderLinePanel extends WPOSSubPanel implements WTableModelListener, POSPanelInterface,FocusListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4023538043556457231L;

	/**
	 * Constructor
	 * 
	 * @param posPanel POS Panel
	 */
	public WPOSOrderLinePanel(WPOS posPanel) {
		super(posPanel);
	}
	/**	Current Order Line			*/
	private int 					orderLineId = 0;
	/** The Table					*/
	private WPOSTable 				posTable;
	/** The Table					*/
	public 	POSOrderLineTableHandle lineTableHandle;
	/**	Logger				*/
	private static CLogger logger = CLogger.getCLogger(WPOSOrderLinePanel.class);

	@Override
	protected void init() {
		posTable = new WPOSTable();
		//	
		lineTableHandle = new POSOrderLineTableHandle(posTable);
		lineTableHandle.prepareTable();

		posTable.setColumnClass(4, BigDecimal.class, true);
		appendChild(posTable);
		posTable.repaint();
		posTable.setWidth("99%");
		posTable.setHeight("47%");
		posTable.addActionListener(this);
		posTable.addEventListener(Events.ON_CLICK, this);
		posTable.getModel().addTableModelListener(this);
		posTable.setClass("Table-OrderLine");
		posTable.setColumnReadOnly(POSOrderLineTableHandle.POSITION_QTYORDERED, true);
	}

	@Override
	public void refreshPanel() {
	
		if (!posPanel.hasOrder()) {
			posTable.loadTable(new PO[0]);
		}
		//	Load Data
		lineTableHandle.loadTable(posPanel.getC_Order_ID());
		//	
		for (int i = 0; i < posTable.getRowCount(); i ++ ) {
			IDColumn key = (IDColumn) posTable.getModel().getValueAt(i, 0);
			if ( key != null && orderLineId > 0 && key.getRecord_ID() == orderLineId)
			{
				posTable.setSelectedIndex(i);
				posPanel.changeViewPanel();
				showProductInfo(i);
				break;
			}
			// Select first row, if end of table and no row has been selected
			if(i==posTable.getRowCount()-1)	 {
        //  Set Current Order Line
        orderLineId = key.getRecord_ID();
				posTable.setSelectedIndex(0);
				posPanel.changeViewPanel();
				showProductInfo(0);
			}
		}
		return;
	}
	
	/**
	 * Disable Table 
	 */
	public void disableTable() {
		posTable.setEnabled(false);
		lineTableHandle.setEditable(false, false);
		posTable.removeActionListener(this);
		posTable.removeEventListener(Events.ON_CLICK, this);
	}
	
	/**
	 * Enable Table
	 */
	public void enableTable() {
		posTable.setEnabled(true);
		lineTableHandle.setEditable(posPanel.isModifyPrice(), posPanel.isDrafted());
		posTable.addActionListener(this);
		posTable.addEventListener(Events.ON_CLICK, this);
	}
	
	@Override
	public void onEvent(Event arg0) throws Exception {
		String action = arg0.getTarget().getId();
		if (action == null || action.length() == 0)
			return;
		posTable.getModel().removeTableModelListener(this);
		logger.info( "POSOrderLinePanel - actionPerformed: " + action);
		
		if(arg0.getName().equals(Events.ON_SELECT)) {
			selectLine();
		}
		
		//	Refresh All
		posPanel.refreshPanel();
		posTable.getModel().addTableModelListener(this);
	}
	
	public void selectLine(){
		lineTableHandle.setEditable(posPanel.isModifyPrice(), posPanel.isDrafted());
		IDColumn key = (IDColumn) posTable.getModel().getValueAt(posTable.getSelectedRow(), 0);
		orderLineId = key.getRecord_ID();
		showProductInfo(posTable.getSelectedRow());
		posPanel.changeViewPanel();
	}
	@Override
	public void tableChanged(WTableModelEvent event) {
		boolean isUpdate = (event.getType() == ListDataEvent.CONTENTS_CHANGED);
		int col = event.getColumn();
		int row = posTable.getSelectedRow();
		/*if(event.getColumn() == POSOrderLineTableHandle.POSITION_DELETE){
			posTable.getModel().removeTableModelListener(this);
			ListModelTable m_model = (ListModelTable)event.getModel();
			for(int x = 0; x < posTable.getRowCount(); x++){
				String value = m_model.getValueAt(x, 1).toString();
				if(value.length() == 0){
					IDColumn key = (IDColumn) m_model.getValueAt(x, 0);
					orderLineId = key.getRecord_ID();
					posPanel.deleteLine(orderLineId);
					posTable.getModel().remove(x);
				}
			}

			posTable.getModel().addTableModelListener(this);
			posPanel.refreshHeader();
			return;
		}*/
		if (!isUpdate
				|| (col != POSOrderLineTableHandle.POSITION_QTYORDERED
						&& col != POSOrderLineTableHandle.POSITION_PRICE)) {
			return;
		}
		if (event.getModel().equals(posTable.getModel())){ //Add Minitable Source Condition
				if (row != -1) {	
					ListModelTable model = posTable.getModel();
					IDColumn key = (IDColumn) model.getValueAt(row, 0);
					posTable.getModel().removeTableModelListener(this);
				
				//	Validate Key
				if (key != null) {
					//	Set Current Order Line
					orderLineId = key.getRecord_ID();
					BigDecimal m_QtyOrdered       = (BigDecimal) posTable.getValueAt(row, POSOrderLineTableHandle.POSITION_QTYORDERED);
					BigDecimal m_Price            = (BigDecimal) posTable.getValueAt(row, POSOrderLineTableHandle.POSITION_PRICE);
					BigDecimal discountPercentage = (BigDecimal) posTable.getValueAt(row, POSOrderLineTableHandle.POSITION_DISCOUNT);
					posPanel.setQty(m_QtyOrdered);
					posPanel.setPrice(m_Price);
					posPanel.setDiscountPercentage(discountPercentage);
					updateLine();
				}
			}
			
		}
	}
	
	public void updateLine() {
		int row = posTable.getSelectedRow();
			//	Remove Listener
			posTable.getModel().removeTableModelListener(this);
			//	Remove line
			if(posPanel.getQty() != null && posPanel.getQty().signum() < 0) {
				if (orderLineId > 0 && !posPanel.isAddQty())
				if(posPanel.isRequiredPIN() && posPanel.isUserPinValid()) {
					posPanel.deleteLine(orderLineId);
				}
				if (row >= 0) {
					((ListModelTable) posTable.getModel()).remove(row);
					posTable.getModel().addTableModelListener(this);
					posPanel.refreshPanel();
				}
				//	Exit
				return;
			}
			
			//	Get Order Line
			BigDecimal[] m_Summary = posPanel.updateLine(
					orderLineId,
					posPanel.getQty().add(posPanel.getQtyAdded()),
					posPanel.getPriceLimit(),
					posPanel.getPrice() ,
					posPanel.getPriceList(),
					posPanel.getDiscountPercentage());
			//	Set Totals
			if(m_Summary != null && row >= 0) {
				posTable.setValueAt(m_Summary[0], row, POSOrderLineTableHandle.POSITION_LINENETAMT);
				posTable.setValueAt(m_Summary[2], row, POSOrderLineTableHandle.POSITION_GRANDTOTAL);
			}
			posTable.getModel().addTableModelListener(this);
			//	Only Refresh Header
			posPanel.refreshHeader();
			posPanel.refreshPanel();
			
			return;
	}
	
	public void moveDown() {
		if((posTable.getRowCount()-1) > posTable.getSelectedRow() && posTable.getRowCount() != 0)
			posTable.setSelectedIndex(posTable.getSelectedRow()+1);
		else
			posTable.setSelectedIndex(0);
		selectLine();
		posPanel.changeViewPanel();
		showProductInfo(posTable.getSelectedIndex());
		return;
	}
	
	public void moveUp() {
		if((posTable.getRowCount()-1) >= posTable.getSelectedRow() && posTable.getSelectedRow() != 0)
			posTable.setSelectedIndex(posTable.getSelectedRow()-1);
		else
			posTable.setSelectedIndex(posTable.getRowCount()-1);
		selectLine();		
		posPanel.changeViewPanel();
		showProductInfo(posTable.getSelectedIndex());
		return;
	}
	
	/**
	 * Seek in record from Product
	 * @param p_M_Product_ID
	 */
	public void seekFromProduct(int p_M_Product_ID) {
		int m_C_OrderLine_ID = getC_OrderLine_ID(p_M_Product_ID);
		if(m_C_OrderLine_ID <= 0)
			return;
		//	
		orderLineId = m_C_OrderLine_ID;
		//	Iterate
		for (int i = 0; i < posTable.getRowCount(); i ++ ) {
			IDColumn key = (IDColumn) posTable.getModel().getValueAt(i, 0);
			if ( key != null && orderLineId > 0 && key.getRecord_ID() == orderLineId) {
				posTable.setSelectedIndex(i);
				selectLine();
				break;
			}
		}
	}
	/**
	 * 	Focus Gained
	 *	@param e
	 */
	public void focusGained (FocusEvent e) {
		logger.info("POSOrderLinePanel - focusGained: " + e);
	}	//	focusGained
		

	/**
	 * 	Focus Lost
	 *	@param e
	 */
	public void focusLost (FocusEvent e) {
		if (e.isTemporary())
			return;
		logger.info( "POSDocumentPanel - focusLost");
		posPanel.refreshPanel();
	}	//	focusLost

	@Override
	public String validatePayment() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		int row = posTable.getSelectedRow();
		if (row != -1 &&  row < posTable.getRowCount()) {
			//	Set Current Order Line
			BigDecimal qtyOrdered = (BigDecimal) posTable.getValueAt(row, POSOrderLineTableHandle.POSITION_QTYORDERED);
			BigDecimal price = (BigDecimal) posTable.getValueAt(row, POSOrderLineTableHandle.POSITION_PRICE);
			BigDecimal discountPercentage = (BigDecimal) posTable.getValueAt(row, POSOrderLineTableHandle.POSITION_DISCOUNT);
			
			posPanel.setQty(qtyOrdered);
			posPanel.setPrice(price);
			posPanel.setDiscountPercentage(discountPercentage);
		}
		else {
			posPanel.setQty(Env.ZERO);
			posPanel.setPrice(Env.ZERO);
			posPanel.setPriceLimit(Env.ZERO);
			posPanel.setPriceList(Env.ZERO);
			posPanel.setDiscountPercentage(Env.ZERO);
		}
	}
	
	/**
	 * Show Product Info
	 * @param row
	 * @return void
	 */
	private void showProductInfo(int row) {
		Object data = posTable.getModel().getValueAt(row, 0);
		if ( data != null )	{
			Integer id = (Integer) ((IDColumn)data).getRecord_ID();
			posPanel.setOrderLineId(id);
      BigDecimal quantity = (BigDecimal) posTable.getModel().getValueAt(row, POSOrderLineTableHandle.POSITION_QTYORDERED);
      BigDecimal price = (BigDecimal) posTable.getModel().getValueAt(row, POSOrderLineTableHandle.POSITION_PRICE);
      BigDecimal discount = (BigDecimal) posTable.getModel().getValueAt(row, POSOrderLineTableHandle.POSITION_DISCOUNT);

      //	Refresh
      posPanel.setQty(quantity);
      posPanel.setPrice(price);
      posPanel.setDiscountPercentage(discount);
      posPanel.changeViewPanel();
      posPanel.refreshProductInfo(posPanel.getM_Product_ID(posPanel.getOrderLineId()));

//			posPanel.refreshProductInfo(m_M_Product_ID);
		
		}
	}

	/**
	 * Get Order Line from Product
	 * @param p_M_Product_ID
	 * @return
	 */
	private int getC_OrderLine_ID(int p_M_Product_ID) {
		return DB.getSQLValue(null, "SELECT ol.C_OrderLine_ID "
				+ "FROM C_OrderLine ol "
				+ "WHERE ol.M_Product_ID = ? AND ol.C_Order_ID = ?", 
				p_M_Product_ID, posPanel.getC_Order_ID());
	}
	
	public int getC_OrderLine_ID()
	{
		return orderLineId;
	}
}