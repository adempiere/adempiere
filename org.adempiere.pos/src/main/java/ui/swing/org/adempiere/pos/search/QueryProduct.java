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

package org.adempiere.pos.search;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MWarehousePrice;
import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.compiere.swing.CLabel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 *	POS Query Product
 *	
 *  @author Based on Modified Original Code, Revised and Optimized
 *         *Copyright (c) Jorg Janke
 *  @author Dixon Martinez, ERPCYA 
 *  @author Susanne Calderón Schöningh, Systemhaus Westfalia
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *  <li> Implement best practices
 *  
 *  @version $Id: QueryProduct.java,v 1.1 jjanke Exp $
 *  @version $Id: QueryProduct.java,v 2.0 2015/09/01 00:00:00 scalderon
 */
public class QueryProduct extends POSQuery {

	private static final long serialVersionUID = 9172276999827406833L;

	/**
	 * 	Constructor
	 */
	public QueryProduct (VPOS posPanel) {
		super(posPanel);
	}	//	PosQueryProduct
	
	/**	Search Fields		*/
	private POSTextField	f_Value;
	private POSTextField	f_ProductName;
	private POSTextField	f_UPC;
	private POSTextField	f_SKU;
	/**	Internal Variables	*/
	private int				m_M_Product_ID;
	private String			m_ProductName;
	private BigDecimal		m_Price;
	private int 			m_M_PriceList_Version_ID;
	private int 			m_M_Warehouse_ID;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(QueryProduct.class);
	
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] s_layout = new ColumnInfo[] 
	{
		new ColumnInfo(" ", "M_Product_ID", IDColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Value"), "Value", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Name"), "Name", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "UPC"), "UPC", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "SKU"), "SKU", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "QtyOnHand", Double.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "PriceStd"), "PriceStd", BigDecimal.class)
	};
	
	/**	From Clause							*/
	private static String s_sqlFrom = "RV_WarehousePrice";
	/** Where Clause						*/
	private static String s_sqlWhere = "IsActive='Y'"; 

	/**
	 * 	Set up Panel
	 */
	protected void init() {
		v_ParameterPanel.setLayout(new MigLayout("fill", "", "[50][50][]"));
		v_ParameterPanel.setBorder(new TitledBorder(Msg.getMsg(m_ctx, "Query")));
		//
		CLabel lvalue = new CLabel(Msg.translate(m_ctx, "Value"));
		v_ParameterPanel.add (lvalue, "growy");
		f_Value = new POSTextField("", v_POSPanel.getKeyboard());
		lvalue.setLabelFor(f_Value);
		v_ParameterPanel.add(f_Value,  "h 30, w 200");
		f_Value.addActionListener(this);
		//
		CLabel lupc = new CLabel(Msg.translate(m_ctx, "UPC"));
		v_ParameterPanel.add (lupc, "growy");
		f_UPC = new POSTextField("", v_POSPanel.getKeyboard());
		lupc.setLabelFor(f_UPC);
		v_ParameterPanel.add(f_UPC,  "h 30, w 200, wrap");
		f_UPC.addActionListener(this);
		//
		CLabel lname = new CLabel(Msg.translate(m_ctx, "Name"));
		v_ParameterPanel.add (lname, "growy");
		f_ProductName = new POSTextField("", v_POSPanel.getKeyboard());
		lname.setLabelFor(f_ProductName);
		v_ParameterPanel.add(f_ProductName,  "h 30, w 200");
		f_ProductName.addActionListener(this);
		//
		CLabel lsku = new CLabel(Msg.translate(m_ctx, "SKU"));
		v_ParameterPanel.add (lsku, "growy");
		f_SKU = new POSTextField("", v_POSPanel.getKeyboard());
		lsku.setLabelFor(f_SKU);
		v_ParameterPanel.add(f_SKU,  "h 30, w 200");
		f_SKU.addActionListener(this);
		//	Prepare Table
		m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "RV_WarehousePrice");
		//	
		m_table.addMouseListener(this);
		m_table.getSelectionModel().addListSelectionListener(this);
		m_table.setColumnVisibility(m_table.getColumn(0), false);
		m_table.getColumn(1).setPreferredWidth(175);
		m_table.getColumn(2).setPreferredWidth(175);
		m_table.getColumn(3).setPreferredWidth(100);
		m_table.getColumn(4).setPreferredWidth(75);
		m_table.getColumn(5).setPreferredWidth(75);
		m_table.getColumn(6).setPreferredWidth(75);
		m_table.getColumn(7).setPreferredWidth(75);
		select();
		m_table.setFillsViewportHeight(true); //@Trifon
		m_table.growScrollbars();
		f_Value.requestFocus();
	}	//	init
	
	/**
	 * 	Set Query Data
	 *	@param M_PriceList_Version_ID plv
	 *	@param M_Warehouse_ID wh
	 */
	public void setQueryData (int M_PriceList_Version_ID, int M_Warehouse_ID) {
		m_M_PriceList_Version_ID = M_PriceList_Version_ID;
		m_M_Warehouse_ID = M_Warehouse_ID;
	}	//	setQueryData
	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource() == f_Value || e.getSource() == f_UPC
			|| e.getSource() == f_ProductName || e.getSource() == f_SKU) {
			refresh();
			return;
		}
		//	Exit
	}	//	actionPerformed
	
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResults (MWarehousePrice[] results) {
		m_table.loadTable(results);
		if (m_table.getRowCount() >0 )
			m_table.setRowSelectionInterval(0, 0);
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void select() {
		m_M_Product_ID = -1;
		m_ProductName = null;
		m_Price = null;
		int row = m_table.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = m_table.getSelectedRowKey();
			if (ID != null)
			{
				m_M_Product_ID = ID.intValue();
				m_ProductName = (String)m_table.getValueAt(row, 2);
				m_Price = (BigDecimal)m_table.getValueAt(row, 7);
			}
		}
//		f_Ok.setEnabled(enabled);
		log.fine("M_Product_ID=" + m_M_Product_ID + " - " + m_ProductName + " - " + m_Price); 
	}	//	enableButtons



	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close() {
		log.fine("M_Product_ID=" + m_M_Product_ID);
		Integer ID = m_table.getSelectedRowKey();
		if (ID != null)
			m_M_Product_ID = ID.intValue();
	}	//	close

	/**
	 * Get Product Identifier
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return int
	 */
	public int getM_Product_ID() {
		return m_M_Product_ID;
	}
	
	/**
	 * Get Product Name
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return String
	 */
	public String getProductName() {
		return m_ProductName;
	}

	@Override
	public void reset() {
		f_Value.setText(null);
		f_ProductName.setText(null);
		f_SKU.setText(null);
		f_UPC.setText(null);
		setResults(new MWarehousePrice[0]);
	}

	@Override
	public void refresh() {
		setResults(MWarehousePrice.find (m_ctx,
				m_M_PriceList_Version_ID, m_M_Warehouse_ID,
				f_Value.getText(), f_ProductName.getText(), f_UPC.getText(), f_SKU.getText(), null));
	}

	@Override
	protected void cancel() {
		m_M_Product_ID = -1;
		m_ProductName = null;
		m_Price = Env.ZERO;
	}
}	//	PosQueryProduct