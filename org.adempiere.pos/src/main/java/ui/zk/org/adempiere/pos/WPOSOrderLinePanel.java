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

import org.adempiere.pos.service.I_POSPanel;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.event.WTableModelEvent;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MOrderLine;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;

/**
 * Button panel supporting multiple linked layouts
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 */
public class WPOSOrderLinePanel extends WPOSSubPanel implements WTableModelListener, I_POSPanel,FocusListener {
	
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
	private int 					m_C_OrderLine_ID = 0;
	/** The Table					*/
	private WPOSTable				 m_table;
	/** The Table					*/
	public POSOrderLineTableHandle	 m_TableHandle;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(WPOSOrderLinePanel.class);

	@Override
	protected void init() {
		m_table = new WPOSTable();
		//	
		m_TableHandle = new POSOrderLineTableHandle(m_table);
		m_TableHandle.prepareTable();

		m_table.setColumnClass(4, BigDecimal.class, true);
		Center center = new Center();
		center.appendChild(m_table);
		m_table.setWidth("100%");
		m_table.setHeight("100%");
		m_table.addActionListener(this);
		m_table.addEventListener(Events.ON_CLICK, this);
		m_table.getModel().addTableModelListener(this);
		center.setStyle("border: none; height:100%;");
		m_table.setClass("Table-OrderLine");
		m_table.setColumnReadOnly(POSOrderLineTableHandle.POSITION_QTYORDERED, true);
		appendChild(center);
	}

	@Override
	public void refreshPanel() {
	
		if (!v_POSPanel.hasOrder()) {
			m_table.loadTable(new PO[0]);
		}
		
		//	
		//	Load Data
		m_TableHandle.loadTable(v_POSPanel.getC_Order_ID());
		//	
		for ( int i = 0; i < m_table.getRowCount(); i ++ ) {
			IDColumn key = (IDColumn) m_table.getModel().getValueAt(i, 0);
			if ( key != null && m_C_OrderLine_ID > 0 && key.getRecord_ID() == m_C_OrderLine_ID )
			{
				m_table.setSelectedIndex(i);
				break;
			}
		}
		return;
	}
	
	@Override
	public void onEvent(Event arg0) throws Exception {
		String action = arg0.getTarget().getId();
		if (action == null || action.length() == 0)
			return;
		m_table.getModel().removeTableModelListener(this);
		log.info( "POSOrderLinePanel - actionPerformed: " + action);
		
		if(arg0.getName().equals(Events.ON_SELECT)) {
			m_TableHandle.setEditable(v_POSPanel.isModifyPrice(), v_POSPanel.isDrafted());
			WListbox c_table = (WListbox)arg0.getTarget();
			IDColumn key = (IDColumn) m_table.getModel().getValueAt(c_table.getSelectedRow(), 0);
			m_C_OrderLine_ID = key.getRecord_ID();
			showProductInfo(c_table.getSelectedRow());
			
		}
		
		//	Refresh All
		v_POSPanel.refreshPanel();
		m_table.getModel().addTableModelListener(this);
	}

	@Override
	public void tableChanged(WTableModelEvent event) {
		int row = m_table.getSelectedRow();
		if(event.getColumn() == POSOrderLineTableHandle.POSITION_DELETE){
			m_table.getModel().removeTableModelListener(this);
			ListModelTable m_model = (ListModelTable)event.getModel();
			for(int x = 0; x < m_table.getRowCount(); x++){
				String value = m_model.getValueAt(x, 1).toString();
				if(value.length() == 0){
					IDColumn key = (IDColumn) m_model.getValueAt(x, 0);
					m_C_OrderLine_ID = key.getRecord_ID();
					v_POSPanel.deleteLine(m_C_OrderLine_ID);
					m_table.getModel().remove(x);
				}
			}

			m_table.getModel().addTableModelListener(this);
			v_POSPanel.refreshHeader();
			return;
		}
		if (event.getModel().equals(m_table.getModel())){ //Add Minitable Source Condition
			if (row != -1 )	{

				Object data = m_table.getModel().getValueAt(row, 0);
				if ( data != null )	{
					Integer id = (Integer) ((IDColumn)data).getRecord_ID();
					m_C_OrderLine_ID = id;
					showProductInfo(row);
				}
			}else {
				return;
			}
			int id = m_table.getSelectedRow();
				if (id != -1) {	
					ListModelTable model = m_table.getModel();
					IDColumn key = (IDColumn) model.getValueAt(id, 0);
					m_table.getModel().removeTableModelListener(this);			
				
				//	Validate Key
				if (key != null) {
					//	Set Current Order Line
					m_C_OrderLine_ID = key.getRecord_ID();
					BigDecimal m_QtyOrdered = (BigDecimal) m_table.getValueAt(row, POSOrderLineTableHandle.POSITION_QTYORDERED);
		    		BigDecimal m_Price = (BigDecimal) m_table.getValueAt(row, POSOrderLineTableHandle.POSITION_PRICE);
					//	Remove Listener
		    		m_table.getModel().removeTableModelListener(this);
					//	Remove line
					if(m_QtyOrdered.compareTo(Env.ZERO) <= 0) {
						v_POSPanel.deleteLine(m_C_OrderLine_ID);
						((ListModelTable)m_table.getModel()).remove(row);
						m_table.getModel().addTableModelListener(this);
						v_POSPanel.refreshHeader();
						//	Exit
						return;
					}
					//	Get Order Line
					BigDecimal[] m_Summary = v_POSPanel.updateLine(m_C_OrderLine_ID, m_QtyOrdered, m_Price);
					//	Set Totals
					if(m_Summary != null) {
						m_table.setValueAt(m_Summary[0], row, POSOrderLineTableHandle.POSITION_LINENETAMT);
						m_table.setValueAt(m_Summary[2], row, POSOrderLineTableHandle.POSITION_GRANDTOTAL);
					}
					m_table.getModel().addTableModelListener(this);
					//	Only Refresh Header
					v_POSPanel.refreshHeader();
					
					return;
				}
			}
			
		}
	}
	public void valueChange() {
		int id = m_table.getSelectedRow();
		ListModelTable model = m_table.getModel();
		if (id != -1) {	
			IDColumn key = (IDColumn) model.getValueAt(id, 0);
			
			if ( key != null &&  key.getRecord_ID() != m_C_OrderLine_ID ){
				m_C_OrderLine_ID = key.getRecord_ID();
				MOrderLine line = new MOrderLine(m_ctx, m_C_OrderLine_ID, null);
				if ( line != null )
				{
					
						line.setPrice(new BigDecimal(m_table.getModel().getValueAt(id, 4).toString()));
						line.setQty(new BigDecimal(m_table.getModel().getValueAt(id, 2).toString()));
						line.saveEx();
						v_POSPanel.reloadOrder();
						v_POSPanel.refreshPanel();
					}
				
			}
		}

	}
	/**
	 * 	Focus Gained
	 *	@param e
	 */
	public void focusGained (FocusEvent e) {
		log.info("POSOrderLinePanel - focusGained: " + e);		
	}	//	focusGained
		

	/**
	 * 	Focus Lost
	 *	@param e
	 */
	public void focusLost (FocusEvent e) {
		if (e.isTemporary())
			return;
		log.info( "POSProductPanel - focusLost");
		v_POSPanel.refreshPanel();
	}	//	focusLost

	@Override
	public String validatePanel() {
		return null;
	}

	@Override
	public void changeViewPanel() {
		
	}
	/**
	 * Show Product Info
	 * @param row
	 * @return void
	 */
	private void showProductInfo(int row) {
		Object data = m_table.getModel().getValueAt(row, 0);
		if ( data != null )	{
			Integer id = (Integer) ((IDColumn)data).getRecord_ID();
			m_C_OrderLine_ID = id;
			int m_M_Product_ID = DB.getSQLValue(null, "SELECT ol.M_Product_ID "
					+ "FROM C_OrderLine ol "
					+ "WHERE ol.C_OrderLine_ID = ?", m_C_OrderLine_ID);
			//	Refresh
			v_POSPanel.refreshProductInfo(m_M_Product_ID);
		}
	}
}
