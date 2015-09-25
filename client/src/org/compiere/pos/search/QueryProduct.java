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

package org.compiere.pos.search;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MWarehousePrice;
import org.compiere.pos.POSTextField;
import org.compiere.pos.PosTable;
import org.compiere.pos.VPOS;
import org.compiere.swing.CButton;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
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
public class QueryProduct extends POSQuery
{

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
	/**	Action Buttons		*/
	private CButton 		f_Refresh;
	private CButton 		f_Ok;
	private CButton 		f_Cancel;
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
	protected void init()
	{
		CPanel panel = new CPanel();
		
		panel.setLayout(new MigLayout("fill"));
		getContentPane().add(panel);
		//	North
		northPanel = new CPanel(new MigLayout("fill", "", "[50][50][]"));
		panel.add (northPanel, "north");
		northPanel.setBorder(new TitledBorder(Msg.getMsg(p_ctx, "Query")));
		
		//
		CLabel lvalue = new CLabel(Msg.translate(p_ctx, "Value"));
		northPanel.add (lvalue, "growy");
		f_Value = new POSTextField("", v_POSPanel.getKeyboard());
		lvalue.setLabelFor(f_Value);
		northPanel.add(f_Value,  "h 30, w 200");
		f_Value.addActionListener(this);
		//
		CLabel lupc = new CLabel(Msg.translate(p_ctx, "UPC"));
		northPanel.add (lupc, "growy");
		f_UPC = new POSTextField("", v_POSPanel.getKeyboard());
		lupc.setLabelFor(f_UPC);
		northPanel.add(f_UPC,  "h 30, w 200, wrap");
		f_UPC.addActionListener(this);
		//
		CLabel lname = new CLabel(Msg.translate(p_ctx, "Name"));
		northPanel.add (lname, "growy");
		f_ProductName = new POSTextField("", v_POSPanel.getKeyboard());
		lname.setLabelFor(f_ProductName);
		northPanel.add(f_ProductName,  "h 30, w 200");
		f_ProductName.addActionListener(this);
		//
		CLabel lsku = new CLabel(Msg.translate(p_ctx, "SKU"));
		northPanel.add (lsku, "growy");
		f_SKU = new POSTextField("", v_POSPanel.getKeyboard());
		lsku.setLabelFor(f_SKU);
		northPanel.add(f_SKU,  "h 30, w 200");
		f_SKU.addActionListener(this);
		//
		

		f_Refresh = createButtonAction("Refresh", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		northPanel.add(f_Refresh, "w 50!, h 50!, wrap, alignx trailing");
		
		f_up = createButtonAction("Previous", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		northPanel.add(f_up, "w 50!, h 50!, span, split 4");
		f_down = createButtonAction("Next", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		northPanel.add(f_down, "w 50!, h 50!");
		
		f_Ok = createButtonAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		northPanel.add(f_Ok, "w 50!, h 50!");
		
		f_Cancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		northPanel.add(f_Cancel, "w 50!, h 50!");

		//	Center
		m_table = new PosTable();
		String sql = m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "RV_WarehousePrice")
			+ " ORDER BY Margin, QtyAvailable";
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
		enableButtons();
		m_table.setFillsViewportHeight( true ); //@Trifon
		m_table.growScrollbars();
		centerScroll = new CScrollPane(m_table);
		panel.add (centerScroll, "growx, growy,south");
		panel.setPreferredSize(new Dimension(800,600));
		f_Value.requestFocus();
	}	//	init
	
	/**
	 * 	Set Query Data
	 *	@param M_PriceList_Version_ID plv
	 *	@param M_Warehouse_ID wh
	 */
	public void setQueryData (int M_PriceList_Version_ID, int M_Warehouse_ID)
	{
		m_M_PriceList_Version_ID = M_PriceList_Version_ID;
		m_M_Warehouse_ID = M_Warehouse_ID;
	}	//	setQueryData
	
	/**
	 * 	Action Listener
	 *	@param e event
	 */
	public void actionPerformed (ActionEvent e)
	{
		log.info(e.getActionCommand());
		if ("Refresh".equals(e.getActionCommand())
			|| e.getSource() == f_Value || e.getSource() == f_UPC
			|| e.getSource() == f_ProductName || e.getSource() == f_SKU)
		{
			setResults(MWarehousePrice.find (p_ctx,
				m_M_PriceList_Version_ID, m_M_Warehouse_ID,
				f_Value.getText(), f_ProductName.getText(), f_UPC.getText(), f_SKU.getText(), null));
			return;
		}
		else if ("Reset".equals(e.getActionCommand()))
		{
			reset();
			return;
		}
		else if ("Previous".equalsIgnoreCase(e.getActionCommand()))
		{
			int rows = m_table.getRowCount();
			if (rows == 0)
				return;
			int row = m_table.getSelectedRow();
			row--;
			if (row < 0)
				row = 0;
			m_table.getSelectionModel().setSelectionInterval(row, row);
			// https://sourceforge.net/tracker/?func=detail&atid=879332&aid=3121975&group_id=176962
			m_table.scrollRectToVisible(m_table.getCellRect(row, 1, true)); //@Trifon - BF[3121975]
			return;
		}
		else if ("Next".equalsIgnoreCase(e.getActionCommand()))
		{
			int rows = m_table.getRowCount();
			if (rows == 0)
				return;
			int row = m_table.getSelectedRow();
			row++;
			if (row >= rows)
				row = rows - 1;
			m_table.getSelectionModel().setSelectionInterval(row, row);
			// https://sourceforge.net/tracker/?func=detail&atid=879332&aid=3121975&group_id=176962
			m_table.scrollRectToVisible(m_table.getCellRect(row, 1, true)); //@Trifon - BF[3121975]
			return;
		}
		else if ("ok".equalsIgnoreCase(e.getActionCommand()))
			
		{
			int row = m_table.getSelectedRow();
		}
		//	Exit
		close();
	}	//	actionPerformed
	
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (MWarehousePrice[] results)
	{
		m_table.loadTable(results);
		if (m_table.getRowCount() >0 )
			m_table.setRowSelectionInterval(0, 0);
		enableButtons();
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons() {
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
		f_Ok.setEnabled(enabled);
		log.fine("M_Product_ID=" + m_M_Product_ID + " - " + m_ProductName + " - " + m_Price); 
	}	//	enableButtons



	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close()
	{
		log.fine("M_Product_ID=" + m_M_Product_ID);
		Integer ID = m_table.getSelectedRowKey();
		if (ID != null)
			m_M_Product_ID = ID.intValue(); 
		dispose();
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
}	//	PosQueryProduct