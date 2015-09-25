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

package org.compiere.pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import net.miginfocom.swing.MigLayout;

import org.compiere.minigrid.IDColumn;
import org.compiere.model.MOrderLine;
import org.compiere.model.PO;
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
		ListSelectionListener,  TableModelListener, I_POSPanel {
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
	private static CLogger log = CLogger.getCLogger(POSOrderLinePanel.class);
	

	/** The Table			*/
	private PosTable		m_table;
	/**	Table Handle		*/
	private POSOrderLineTableHandle m_TableHandle;
	
	/**
	 * Initialize
	 */ 
	public void init() {
	
		//	Content
		setLayout(new MigLayout("fill, ins 10 10"));
	
		m_table = new PosTable();
		m_TableHandle = new POSOrderLineTableHandle(m_table);
		CScrollPane scroll = new CScrollPane(m_table);
		m_TableHandle.prepareTable();
//		m_table.getSelectionModel().addListSelectionListener(this);
//		m_table.setColumnVisibility(m_table.getColumn(0), true);
//		m_table.getColumn(1).setPreferredWidth(320);
//		m_table.getColumn(2).setPreferredWidth(65);
//		m_table.getColumn(3).setPreferredWidth(30);
//		m_table.getColumn(4).setPreferredWidth(75);
//		m_table.getColumn(5).setPreferredWidth(75);
//		m_table.getColumn(6).setPreferredWidth(70);
//		m_table.getColumn(7).setPreferredWidth(75);
//		m_table.setColumnClass(7, BigDecimal.class, false);
//		m_table.setFocusable(false);
//		m_table.getColumnModel().getColumn(7).setCellEditor(
//				new BigDecimalEditor());
		m_table.getModel().addTableModelListener(this);
		m_table.addKeyListener(new java.awt.event.KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {// GEN-FIRST:
				// event_DetailKeyPressed
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ALT:
					break;
				case KeyEvent.VK_A:
					break;
				default:
					break;
				}
				v_POSPanel.refreshPanel();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {// GEN-FIRST:
				// event_DetailKeyPressed
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ALT:
					break;
				case KeyEvent.VK_A:
					break;

				default:
					break;
				}

				v_POSPanel.refreshPanel();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {// GEN-FIRST:
				// event_DetailKeyPressed
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ALT:
					break;
				case KeyEvent.VK_A:
					break;

				default:
					break;
				}
				v_POSPanel.refreshPanel();

			}			
		});

		m_table.setFillsViewportHeight( true ); //@Trifon
		m_table.growScrollbars();
		add (scroll, "growx, spanx, growy, pushy, h 100:30:");		
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
	
    public void tableChanged(TableModelEvent e) {
        int id = m_table.getSelectedRow();
		boolean isUpdate = (e.getType() == TableModelEvent.UPDATE);
		if (!isUpdate) {
			return;
		}
        if (id != -1) {	
    		IDColumn key = (IDColumn) m_table.getModel().getValueAt(id, POSOrderLineTableHandle.POSITION_C_ORDER_ID);
    		
    		if ( key != null &&  key.getRecord_ID() != m_C_OrderLine_ID )
    			m_C_OrderLine_ID = key.getRecord_ID();
			
    		BigDecimal price = new BigDecimal(m_table.getModel().getValueAt(id, POSOrderLineTableHandle.POSITION_PRICE).toString());
			BigDecimal qty = new BigDecimal(m_table.getModel().getValueAt(id, POSOrderLineTableHandle.POSITION_QTYORDERED).toString());
			m_table.getModel().removeTableModelListener(this);
			//	
			MOrderLine line = new MOrderLine(m_ctx, m_C_OrderLine_ID, null);
			line.setPrice(price);
			line.setQty(qty);
			line.saveEx();
			BigDecimal grandTotal = line.getLineNetAmt();
			m_table.getModel().setValueAt(line.getLineNetAmt(), id, POSOrderLineTableHandle.POSITION_LINENETAMT);
			if(!line.getC_Tax().getRate().equals(null)){
				grandTotal.multiply(line.getC_Tax().getRate());
			} 
			m_table.getModel().setValueAt(grandTotal, id, POSOrderLineTableHandle.POSITION_GRANDTOTAL);
			if(qty.compareTo(Env.ZERO) <= 0){
//						p_posPanel.updateInfo();
			}
			v_POSPanel.reloadOrder();
			v_POSPanel.refreshPanel();
			m_table.getModel().addTableModelListener(this);
    	}
       // ...// Do something with the data...
    }

	@Override
	public void refreshPanel() {
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
	}


	@Override
	public String validatePanel() {
		return null;
	}


	@Override
	public void changeViewPanel() {
		
	}
} //	POSOrderLinePanel