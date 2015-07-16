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

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.compiere.apps.ADialog;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.PO;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Doublebox;

/**
 * Current Line Sub Panel
 * 
 * @author OpenXpertya
 * Based on Modified Original Code, Revised and Optimized
 *         *Copyright Jorg Janke
 * red1 - [2093355 ] Small bugs in OpenXpertya POS
 */
public class WSubCurrentLine extends WPosSubPanel implements ActionListener, FocusListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4023538043556457231L;

	/**
	 * Constructor
	 * 
	 * @param posPanel POS Panel
	 */
	public WSubCurrentLine(WPosBasePanel posPanel) {
		super(posPanel);
	}

	private Button f_up;
	private Button f_delete;
	private Button f_down;
	//
	private Button f_plus;
	private Button f_minus;
	private Doublebox f_price;
	private Doublebox f_quantity;
	protected Textbox	f_name1;
	private Button			f_bSearch;
	private int orderLineId = 0;
	

	/**	The Product					*/
	private MProduct		m_product = null; 

	/** Warehouse					*/
	private int 			m_M_Warehouse_ID;
	/** PLV							*/
	private int 			m_M_PriceList_Version_ID;
	
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(SubCurrentLine.class);
	

	/** The Table					*/
	PosTable		m_table;
	/** The Query SQL				*/
	private String			m_sql;
	/**	Logger			*/
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "C_OrderLine_ID", IDColumn.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"), "QtyOrdered", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_UOM_ID"), "UOMSymbol", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "PriceActual"), "PriceActual", BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "LineNetAmt"), "LineNetAmt", BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Tax_ID"), "TaxIndicator", String.class), 
	};
	/**	From Clause							*/
	private static String s_sqlFrom = "C_Order_LineTax_v";
	/** Where Clause						*/
	private static String s_sqlWhere = "C_Order_ID=? AND LineNetAmt <> 0"; 
	
	/**
	 * Initialize
	 */ 
	public void init() {
	
		//	Content
//		setLayout(new MigLayout("fill, ins 0 0"));
		
//		String buttonSize = "w 50!, h 50!,";
		this.setHeight("100%");
		this.setWidth("99%");

		Panel parameterPanel2 = new Panel();
		Grid parameterLayout2 = GridFactory.newGridLayout();
		Rows rows = null;
		Row row = null;
		Center center = new Center();
		center.setStyle("border: none");
		appendChild(center);
		
		parameterPanel2.appendChild(parameterLayout2);
		parameterLayout2.setWidth("40%");
		center.appendChild(parameterPanel2);

		rows = parameterLayout2.newRows();
		row = rows.newRow();
		
		
		//
		f_bSearch = createButtonAction ("Product", KeyStroke.getKeyStroke(KeyEvent.VK_I, Event.CTRL_MASK));
		row.appendChild(f_bSearch);
		
		Label productLabel = new Label(Msg.translate(Env.getCtx(), "M_Product_ID"));
		row.appendChild(productLabel);
		
//		f_name = new WPosTextField(Msg.translate(Env.getCtx(), "M_Product_ID"), p_posPanel, p_pos.getOSK_KeyLayout_ID());
		f_name1 = new Textbox(Msg.translate(Env.getCtx(), "M_Product_ID"));
		f_name1.setName("Name");
//		f_name.addActionListener(this);
//		f_name.addFocusListener(this);
//		f_name.requestFocusInWindow();
		
		row.appendChild(f_name1);

//		m_table = new PosTable();
////		CScrollPane scroll = new CScrollPane(m_table);
//		m_sql = m_table.prepareTable (s_layout, s_sqlFrom, 
//				s_sqlWhere, false, "C_Order_LineTax_v")
//				+ " ORDER BY Line";
//		//	m_table.addMouseListener(this);
//		m_table.getSelectionModel().addListSelectionListener(this);
//		m_table.setColumnVisibility(m_table.getColumn(0), false);
//		m_table.getColumn(1).setPreferredWidth(175);
//		m_table.getColumn(2).setPreferredWidth(75);
//		m_table.getColumn(3).setPreferredWidth(30);
//		m_table.getColumn(4).setPreferredWidth(75);
//		m_table.getColumn(5).setPreferredWidth(75);
//		m_table.getColumn(6).setPreferredWidth(30);
//		m_table.setFocusable(false);
//		m_table.setFillsViewportHeight( true ); //@Trifon
//		m_table.growScrollbars();

//		row.appendChild(scroll);
		
		f_up = createButtonAction("Previous", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		row.appendChild (f_up);
		f_down = createButtonAction("Next", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		row.appendChild (f_down);

		
		f_delete = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, Event.SHIFT_MASK));
		row.appendChild (f_delete);
		
		//
		f_minus = createButtonAction("Minus", null);
		row.appendChild(f_minus);


		
		Label qtyLabel = new Label(Msg.translate(Env.getCtx(), "QtyOrdered"));
		row.appendChild(qtyLabel);

		//f_quantity = new WPosTextField(Msg.translate(Env.getCtx(), "QtyOrdered"),
//		p_posPanel,p_pos.getOSNP_KeyLayout_ID(), DisplayType.getNumberFormat(DisplayType.Quantity));
		
		f_quantity = new Doublebox(1);
//		f_quantity.setHorizontalAlignment(JTextField.TRAILING);
//		f_quantity.addActionListener(this);
		row.appendChild(f_quantity);
//		setQty(Env.ONE);
		//
		f_plus = createButtonAction("Plus", null);
		row.appendChild(f_plus);
		
		
		Label priceLabel = new Label(Msg.translate(Env.getCtx(), "PriceActual"));
		row.appendChild(priceLabel);
		
		//new WPosTextField(Msg.translate(Env.getCtx(), "PriceActual"),
//		p_posPanel,p_pos.getOSNP_KeyLayout_ID(), DisplayType.getNumberFormat(DisplayType.Amount));
		f_price = new Doublebox(0.0);
//		f_price.addActionListener(this);
//		f_price.setHorizontalAlignment(JTextField.TRAILING);
		row.appendChild(f_price);
//		setPrice(Env.ZERO);
				enableButtons();
	} //init


	/**
	 * Dispose - Free Resources
	 */
//	public void dispose() {
//		super.dispose();
//	} //	dispose

	/**
	 * Action Listener
	 * 
	 * @param e event
	 */
	public void actionPerformed(ActionEvent e) {
//		String action = e.getActionCommand();
//		if (action == null || action.length() == 0)
//			return;
//		log.info( "SubCurrentLine - actionPerformed: " + action);
//		
//		//	Plus
//		if (action.equals("Plus"))
//		{
//			if ( orderLineId > 0 )
//			{
//				MOrderLine line = new MOrderLine(p_ctx, orderLineId, null);
//				if ( line != null )
//				{
//					line.setQty(line.getQtyOrdered().add(Env.ONE));
//					line.saveEx();
//					p_posPanel.updateInfo();
//				}
//			}
//
//		}
//		//	Minus
//		else if (action.equals("Minus"))
//		{
//			if ( orderLineId > 0 )
//			{
//				MOrderLine line = new MOrderLine(p_ctx, orderLineId, null);
//				if ( line != null )
//				{
//					line.setQty(line.getQtyOrdered().subtract(Env.ONE));
//					line.saveEx();
//					p_posPanel.updateInfo();
//				}
//			}
//
//		}
//		//	VNumber
//		else if (e.getSource() == f_price)		{
//			MOrderLine line = new MOrderLine(p_ctx, orderLineId, null);
//			if ( line != null )
//			{
//				line.setQty(new BigDecimal(f_price.getValue().toString()));
//				line.saveEx();
//				p_posPanel.updateInfo();
//			}
//		}
//		else if (e.getSource() == f_quantity && orderLineId > 0 )
//		{
//			MOrderLine line = new MOrderLine(p_ctx, orderLineId, null);
//			if ( line != null )
//			{
//				line.setQty(new BigDecimal(f_quantity.getValue().toString()));
//				line.saveEx();
//				p_posPanel.updateInfo();
//			}
//		}
//		//	Product
//		if (action.equals("Product"))
//		{
//			setParameter();
//			WQueryProduct qt = new WQueryProduct(p_posPanel);
//			qt.setQueryData(m_M_PriceList_Version_ID, m_M_Warehouse_ID);
//			qt.setVisible(true);
//			findProduct();
//			
//			int row = m_table.getSelectedRow();
//			if (row < 0) row = 0;
//			m_table.getSelectionModel().setSelectionInterval(row, row);
//			// https://sourceforge.net/tracker/?func=detail&atid=879332&aid=3121975&group_id=176962
//			m_table.scrollRectToVisible(m_table.getCellRect(row, 1, true)); //@Trifon - BF[3121975]
//		}
//		//	Name
//		else if (e.getSource() == f_name)
//			findProduct();
//		if ("Previous".equalsIgnoreCase(e.getActionCommand()))
//		{
//			int rows = m_table.getRowCount();
//			if (rows == 0) 
//				return;
//			int row = m_table.getSelectedRow();
//			row--;
//			if (row < 0)
//				row = 0;
//			m_table.getSelectionModel().setSelectionInterval(row, row);
//			// https://sourceforge.net/tracker/?func=detail&atid=879332&aid=3121975&group_id=176962
//			m_table.scrollRectToVisible(m_table.getCellRect(row, 1, true)); //@Trifon - BF[3121975]
//			return;
//		}
//		else if ("Next".equalsIgnoreCase(e.getActionCommand()))
//		{
//			int rows = m_table.getRowCount();
//			if (rows == 0)
//				return;
//			int row = m_table.getSelectedRow();
//			row++;
//			if (row >= rows)
//				row = rows - 1;
//			m_table.getSelectionModel().setSelectionInterval(row, row);
//			// https://sourceforge.net/tracker/?func=detail&atid=879332&aid=3121975&group_id=176962
//			m_table.scrollRectToVisible(m_table.getCellRect(row, 1, true)); //@Trifon - BF[3121975]
//			return;
//		}
//		//	Delete
//		else if (action.equals("Cancel"))
//		{
//			int rows = m_table.getRowCount();
//			if (rows != 0)
//			{
//				int row = m_table.getSelectedRow();
//				if (row != -1)
//				{
//					if ( p_posPanel.m_order != null )
//						p_posPanel.m_order.deleteLine(m_table.getSelectedRowKey());
//					setQty(null);
//					setPrice(null);
//					
//					orderLineId = 0;
//				}
//			}
//		}
//		p_posPanel.updateInfo();
	} //	actionPerformed
	
	
	private void enableButtons()
	{
		boolean enabled = true;
		if ( m_table == null || m_table.getRowCount() == 0 || m_table.getSelectedRowKey() == null )
		{
			enabled = false;
		}
		f_down.setEnabled(enabled);
		f_up.setEnabled(enabled);
		f_delete.setEnabled(enabled);
		f_minus.setEnabled(enabled);
		f_plus.setEnabled(enabled);
		f_quantity.setDisabled(!enabled);
		f_price.setDisabled(!enabled);
	}
	
	/**
	 * 	Set Query Parameter
	 */
	private void setParameter()
	{
		//	What PriceList ?
		m_M_Warehouse_ID = p_pos.getM_Warehouse_ID();
		m_M_PriceList_Version_ID = p_posPanel.f_order.getM_PriceList_Version_ID();
	}	//	setParameter

	/**
	 * Set Price
	 * 
	 * @param price
	 */
	public void setPrice(BigDecimal price) {
		if (price == null)
			price = Env.ZERO;
		f_price.setValue(price.doubleValue());
		boolean rw = Env.ZERO.compareTo(price) == 0 || p_pos.isModifyPrice();
		f_price.setDisabled(!rw);
	} //	setPrice

	/**
	 * Get Price
	 * 
	 * @return price
	 */
//	public BigDecimal getPrice() {
//		return (BigDecimal) f_price.getValue();
//	} //	getPrice

	/**
	 * Set Qty
	 * 
	 * @param qty
	 */
	public void setQty(BigDecimal qty) {
		f_quantity.setValue(qty.doubleValue());
	} //	setQty

	/**
	 * Get Qty
	 * 
	 * @return qty
	 */
//	public BigDecimal getQty() {
//		return (BigDecimal) f_quantity.getValue();
//	} //	getQty

	/***************************************************************************
	 * New Line
	 */
//	public void newLine() {
//		setM_Product_ID(0);
//		setQty(Env.ONE);
//		setPrice(Env.ZERO);
//		orderLineId = 0;
//		f_name.requestFocusInWindow();
//	} //	newLine

	

	
//	/**
//	 * 	Set Price for defined product 
//	 */
//	public void setPrice()
//	{
//		if (m_product == null)
//			return;
//		//
//		setParameter();
//		MWarehousePrice result = MWarehousePrice.get (m_product,
//			m_M_PriceList_Version_ID, m_M_Warehouse_ID, null);
//		if (result != null)
//			p_posPanel.f_curLine.setPrice(result.getPriceStd());
//		else
//			p_posPanel.f_curLine.setPrice(Env.ZERO);
//	}	//	setPrice
//	


	/**************************************************************************
	 * 	Find/Set Product & Price
	 */
//	private void findProduct()
//	{
//		String query = f_name.getText();
//		if (query == null || query.length() == 0)
//			return;
//		query = query.toUpperCase();
//		//	Test Number
//		boolean allNumber = true;
//		try
//		{
//			Integer.getInteger(query);
//		}
//		catch (Exception e)
//		{
//			allNumber = false;
//		}
//		String Value = query;
//		String Name = query;
//		String UPC = (allNumber ? query : null);
//		String SKU = (allNumber ? query : null);
//		
//		MWarehousePrice[] results = null;
//		setParameter();
//		//
//		results = MWarehousePrice.find (p_ctx,
//			m_M_PriceList_Version_ID, m_M_Warehouse_ID,
//			Value, Name, UPC, SKU, null);
//		
//		//	Set Result
//		if (results.length == 0)
//		{
//			String message = Msg.translate(p_ctx,  "search.product.notfound");
//			ADialog.warn(0, p_posPanel, message + query);
//			setM_Product_ID(0);
//			p_posPanel.f_curLine.setPrice(Env.ZERO);
//		}
//		else if (results.length == 1)
//		{
//			setM_Product_ID(results[0].getM_Product_ID());
//			setQty(Env.ONE);
//			f_name.setText(results[0].getName());
//			p_posPanel.f_curLine.setPrice(results[0].getPriceStd());
//			saveLine();
//		}
//		else	//	more than one
//		{
//			WQueryProduct qt = new WQueryProduct(p_posPanel);
//			qt.setResults(results);
//			qt.setQueryData(m_M_PriceList_Version_ID, m_M_Warehouse_ID);
//			qt.setVisible(true);
//		}
//	}	//	findProduct


	/**************************************************************************
	 * 	Set Product
	 *	@param M_Product_ID id
	 */
//	public void setM_Product_ID (int M_Product_ID)
//	{
//		log.fine( "PosSubProduct.setM_Product_ID=" + M_Product_ID);
//		if (M_Product_ID <= 0)
//			m_product = null;
//		else
//		{
//			m_product = MProduct.get(p_ctx, M_Product_ID);
//			if (m_product.get_ID() == 0)
//				m_product = null;
//		}
//		//	Set String Info
//		if (m_product != null)
//		{
//			f_name.setText(m_product.getName());
//			f_name.setToolTipText(m_product.getDescription());
//		}
//		else
//		{
//			f_name.setText(null);
//			f_name.setToolTipText(null);
//		}
//	}	//	setM_Product_ID
	
	/**
	 * 	Focus Gained
	 *	@param e
	 */
	public void focusGained (FocusEvent e)
	{

		
	}	//	focusGained
		

	/**
	 * 	Focus Lost
	 *	@param e
	 */
	public void focusLost (FocusEvent e)
	{
//		if (e.isTemporary())
//			return;
//		log.info( "PosSubProduct - focusLost");
//		findProduct();
//		
//		p_posPanel.updateInfo();
	}	//	focusLost


	public void valueChanged(ListSelectionEvent e) {
		if ( e.getValueIsAdjusting() )
			return;

		int row = m_table.getSelectedRow();
		if (row != -1 )
		{
			Object data = m_table.getModel().getValueAt(row, 0);
			if ( data != null )
			{
				Integer id = (Integer) ((IDColumn)data).getRecord_ID();
				orderLineId = id;
				loadLine(id);
			}
		}
		enableButtons();
		
	}


	private void loadLine(int lineId) {
		
		if ( lineId <= 0 )
			return;
	
		log.fine("SubCurrentLine - loading line " + lineId);
		MOrderLine ol = new MOrderLine(p_ctx, lineId, null);
		if ( ol != null )
		{
			setPrice(ol.getPriceActual());
			setQty(ol.getQtyOrdered());
		}
		
	}


	@Override
	public void onEvent(org.zkoss.zk.ui.event.Event event) throws Exception {
		// TODO Auto-generated method stub
		
	}

} //	PosSubCurrentLine
