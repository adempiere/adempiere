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

import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.KeyStroke;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.event.ActionEvent;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;

/**
 *	POS Query Product
 *	
 *  @author Based on Modified Original Code, Revised and Optimized
 *         *Copyright (c) Jorg Janke
 */
public class WQueryProduct extends WPosQuery
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9172276999827406833L;

	/**
	 * 	Constructor
	 */
	public WQueryProduct (WPosBasePanel posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct
	
	private WPosTextField		f_value;
	private WPosTextField		f_name;
	private WPosTextField		f_upc;
	private WPosTextField		f_sku;
	private int 			cont;
	private int				m_M_Product_ID;
	private String			m_ProductName;
	private BigDecimal		m_Price;
	//
	private int 			m_M_PriceList_Version_ID;
	private int 			m_M_Warehouse_ID;
	private Button f_refresh;
	private Button f_ok;
	private Button f_cancel;
	/**	Logger			*/
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
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Borderlayout mainLayout = new Borderlayout();
		Grid productLayout = GridFactory.newGridLayout();
		//	Set title window
		this.setTitle("Query Title");
		
		cont=2;
		appendChild(panel);
		//	North
		northPanel = new Panel();
		mainPanel.appendChild(mainLayout);
		mainPanel.setStyle("width: 100%; height: 100%; padding: 0; margin: 0");
		mainLayout.setHeight("100%");
		mainLayout.setWidth("100%");
		Center center = new Center();
		//
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(northPanel);
		northPanel.appendChild(productLayout);
		appendChild(mainPanel);
		productLayout.setWidth("100%");
		Rows rows = null;
		Row row = null;
		rows = productLayout.newRows();
		row = rows.newRow();
		//
		Label lvalue = new Label(Msg.translate(p_ctx, "Value"));
		row.appendChild(lvalue.rightAlign());
		f_value = new WPosTextField(p_posPanel, p_pos.getOSK_KeyLayout_ID());
		row.appendChild(f_value);
		//
		f_value.addEventListener("onFocus",this);
		Label lupc = new Label(Msg.translate(p_ctx, "UPC"));
		row.appendChild(lupc.rightAlign());
		f_upc = new WPosTextField(p_posPanel, p_pos.getOSK_KeyLayout_ID());
		row.appendChild(f_upc);
		f_upc.addEventListener("onFocus",this);
		//  New Line
		row = rows.newRow();
		//
		Label lname = new Label(Msg.translate(p_ctx, "Name"));
		row.appendChild (lname.rightAlign());
		f_name = new WPosTextField(p_posPanel, p_pos.getOSK_KeyLayout_ID());
		row.appendChild(f_name);
		f_name.addEventListener("onFocus",this);
		//
		Label lsku = new Label(Msg.translate(p_ctx, "SKU"));
		row.appendChild(lsku.rightAlign());
		f_sku = new WPosTextField(p_posPanel, p_pos.getOSK_KeyLayout_ID());
		row.appendChild(f_sku);
		f_sku.addEventListener("onFocus",this);
		//
		row.setHeight("65px");
		f_refresh = createButtonAction("Refresh", KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		row.appendChild(f_refresh);
		//  New Line
		row = rows.newRow();
		row.setSpans("5");
		row.setHeight("65px");
		Panel panelbutton = new Panel();
		f_up = createButtonAction("Previous", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
		panelbutton.appendChild(f_up);
		f_down = createButtonAction("Next", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
		panelbutton.appendChild(f_down);
		
		f_ok = createButtonAction("Ok", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0));
		panelbutton.appendChild(f_ok);
		f_ok.setFocus(true);
		f_cancel = createButtonAction("Cancel", KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		panelbutton.appendChild(f_cancel);
		row.appendChild(panelbutton);

		m_table = ListboxFactory.newDataTable();
		String sql = m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "RV_WarehousePrice")
			+ " ORDER BY Margin, QtyAvailable";
		
		f_refresh.setFocus(true);
		center = new Center();
		center.setStyle("border: none");
		m_table.setWidth("100%");
		m_table.setHeight("99%");
		m_table.addActionListener(this);
		center.appendChild(m_table);
		mainLayout.appendChild(center);
		m_table.loadTable(new PO[0]);
		m_table.autoSize();
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
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (MWarehousePrice[] results)
	{
		m_table.loadTable(results);
		if (m_table.getRowCount() >0 )
			enableButtons();
		m_table.autoSize();
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
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
		f_ok.setEnabled(enabled);
		log.fine("M_Product_ID=" + m_M_Product_ID + " - " + m_ProductName + " - " + m_Price); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close()
	{
		log.fine("M_Product_ID=" + m_M_Product_ID); 
		if (m_M_Product_ID > 0)
		{
			p_posPanel.f_order.setM_Product_ID(m_M_Product_ID);
			p_posPanel.f_order.setPrice(m_Price);
		}
		else
		{
			p_posPanel.f_order.setM_Product_ID(0);
			p_posPanel.f_order.setPrice(Env.ZERO);
		}
		this.detach();
	}	//	close


	@Override
	public void reset() {
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if (event.getTarget().equals(f_refresh)) {
				setResults(MWarehousePrice.find (p_ctx,
					m_M_PriceList_Version_ID, m_M_Warehouse_ID,
					f_value.getText(), f_name.getText(), f_upc.getText(), f_sku.getText(), null));
				return;
			}
		else if(event.getTarget().equals(f_value) || event.getTarget().equals(f_upc)
				|| event.getTarget().equals(f_name) || event.getTarget().equals(f_sku)){
			cont++;
			if(cont < 2) {
				WPOSKeyboard keyboard;
				//	Get Keyboard Panel
				if(event.getTarget().equals(f_value))
					keyboard = p_posPanel.getKeyboard(f_value.getKeyLayoutId(), this, f_value);
				else if (event.getTarget().equals(f_upc))
					keyboard = p_posPanel.getKeyboard(f_value.getKeyLayoutId(), this, f_upc);
				else if (event.getTarget().equals(f_name))
					keyboard = p_posPanel.getKeyboard(f_value.getKeyLayoutId(), this, f_name);
				else 
					keyboard = p_posPanel.getKeyboard(f_value.getKeyLayoutId(), this, f_sku);
				
				//	Set Title
				keyboard.setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
				keyboard.setVisible(true);
				keyboard = null;
				//	Refresh Table
				if(event.getName().equals("onFocus")) {
					setResults(MWarehousePrice.find (p_ctx,
						m_M_PriceList_Version_ID, m_M_Warehouse_ID,
						f_value.getText(), f_name.getText(), f_upc.getText(), f_sku.getText(), null));
						f_refresh.setFocus(true);
				}
			}
			else {
				cont=0;
				f_refresh.setFocus(true);
			}
			return;
		}
		else if (event.getTarget().equals(f_down)){
			if((m_table.getRowCount()-1)>m_table.getSelectedRow() && m_table.getRowCount() != 0) 
				m_table.setSelectedIndex(m_table.getSelectedRow()+1);
			else
				m_table.setSelectedIndex(0);
			return;
		}
		else if (event.getTarget().equals(f_up)){
			if((m_table.getRowCount()-1)<=m_table.getSelectedRow() && m_table.getRowCount() != 0) 
				m_table.setSelectedIndex(m_table.getRowCount()-1);
			else
				m_table.setSelectedIndex(m_table.getSelectedRow()-1);
			return;
		}
		enableButtons();
		if(event.getTarget().equals(f_ok)){
			close();
		}
		if(event.getTarget().equals(f_cancel)){
			close();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
}	//	PosQueryProduct
