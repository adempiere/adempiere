/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.pos;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.adempiere.pos.service.I_POSPanel;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.PO;
import org.compiere.pos.PosTable;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * Current Line Sub Panel
 * 
 * @author OpenXpertya
 * Based on Modified Original Code, Revised and Optimized
 *         *Copyright Jorg Janke
 * red1 - [2093355 ] Small bugs in OpenXpertya POS
 *  @author Susanne Calderón Schöningh, Systemhaus Westfalia
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  
 *  @version $Id: QueryProduct.java,v 1.1 jjanke Exp $
 *  @version $Id: QueryProduct.java,v 2.0 2015/09/01 00:00:00 scalderon
 *  
 */
public class POSOrderLinePanel extends POSSubPanel 
	implements ActionListener, FocusListener, 
		ListSelectionListener,  TableModelListener, I_POSPanel, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4023538043556457231L;

	/**
	 * Constructor
	 * 
	 * @param posPanel POS Panel
	 */
	public POSOrderLinePanel(VPOS posPanel) {
		super(posPanel);
	}

	/**	Current Order Line	*/
	private int 			m_C_OrderLine_ID = 0;
	
	/**	Logger				*/
	private static CLogger 	log = CLogger.getCLogger(POSOrderLinePanel.class);
	

	/** The Table			*/
	private PosTable 		m_table;
	/**	Table Handle		*/
	private POSOrderLineTableHandle m_TableHandle;
	
	/**
	 * Initialize
	 */ 
	public void init() {
		//	Content
		setLayout(new BorderLayout());
		m_table = new PosTable();
		m_TableHandle = new POSOrderLineTableHandle(m_table);
		CScrollPane scroll = new CScrollPane(m_table);
		scroll.addKeyListener(this);
		m_TableHandle.prepareTable(v_POSPanel.isModifyPrice());
		m_table.getModel().addTableModelListener(this);
		m_table.addKeyListener(this);
		
		m_table.setFillsViewportHeight(true); //@Trifon
		m_table.growScrollbars();
		add(scroll, BorderLayout.CENTER);
		addKeyListener(this);
	} //init


	/**
	 * Dispose - Free Resources
	 */
	public void dispose() {
		super.dispose();
	} //	dispose

	/**
	 * Action Listener
	 * 
	 * @param e event
	 */
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action == null || action.length() == 0)
			return;
		log.info( "POSOrderLinePanel - actionPerformed: " + action);
		//	Product
		//	Refresh All
		v_POSPanel.refreshPanel();
	} //	actionPerformed
	
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


	public void valueChanged(ListSelectionEvent e) {
		if ( e.getValueIsAdjusting() )
			return;

		int row = m_table.getSelectedRow();
		if (row != -1 ) {
			Object data = m_table.getModel().getValueAt(row, 0);
			if ( data != null )	{
				Integer id = (Integer) ((IDColumn)data).getRecord_ID();
				m_C_OrderLine_ID = id;
			}
		}
		//	Refresh
		v_POSPanel.refreshPanel();
		
	}  // valueChanged	
	
	@Override
    public void tableChanged(TableModelEvent e) {
        boolean isUpdate = (e.getType() == TableModelEvent.UPDATE);
        int row = e.getFirstRow();
		int col = e.getColumn();
		//  Not a table update
		if (!isUpdate
				|| (col != POSOrderLineTableHandle.POSITION_QTYORDERED
						&& col != POSOrderLineTableHandle.POSITION_PRICE)) {
			return;
		}
		//	Get ID
		IDColumn key = (IDColumn) m_table.getValueAt(row, POSOrderLineTableHandle.POSITION_C_ORDER_ID);
		//	Validate Key
		if (key != null) {
			//	Set Current Order Line
			m_C_OrderLine_ID = key.getRecord_ID();
    		//	Get Values
    		BigDecimal m_QtyOrdered = (BigDecimal) m_table.getValueAt(row, POSOrderLineTableHandle.POSITION_QTYORDERED);
    		BigDecimal m_Price = (BigDecimal) m_table.getValueAt(row, POSOrderLineTableHandle.POSITION_PRICE);
			//	Remove Listener
    		m_table.getModel().removeTableModelListener(this);
			//	Remove line
			if(m_QtyOrdered.compareTo(Env.ZERO) <= 0) {
				v_POSPanel.deleteLine(m_C_OrderLine_ID);
				v_POSPanel.refreshPanel();
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
			//	Request Focus
			m_table.requestFocusInWindow();
    	}
    }

	@Override
	public void refreshPanel() {
		//	Remove Listener
		m_table.getModel().removeTableModelListener(this);
		//	
		if (!v_POSPanel.hasOrder()) {
			m_table.loadTable(new PO[0]);
		}
		//	Load Data
		m_TableHandle.loadTable(v_POSPanel.getC_Order_ID());
		//	
		for ( int i = 0; i < m_table.getRowCount(); i ++ ) {
			IDColumn key = (IDColumn) m_table.getModel().getValueAt(i, 0);
			if ( key != null && m_C_OrderLine_ID > 0 && key.getRecord_ID() == m_C_OrderLine_ID ) {
				m_table.getSelectionModel().setSelectionInterval(i, i);
				break;
			}
		}
		//	Add Listener
		m_table.getModel().addTableModelListener(this);
	}


	@Override
	public String validatePanel() {
		return null;
	}


	@Override
	public void changeViewPanel() {
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		int row = m_table.getSelectedRow();
		if(row < 0) {
			e.consume();
			return;
		}
		//	
		m_table.setRowChecked(row, true);
		//	
		switch (e.getKeyCode()) {
			case KeyEvent.VK_ALT:
				break;
			case KeyEvent.VK_O:
				m_table.editCellAt(row, POSOrderLineTableHandle.POSITION_QTYORDERED, e);
				break;
			case KeyEvent.VK_P:
				m_table.editCellAt(row, POSOrderLineTableHandle.POSITION_PRICE, e);
				break;
			default:
				break;
		}		
	}
} //	POSOrderLinePanel