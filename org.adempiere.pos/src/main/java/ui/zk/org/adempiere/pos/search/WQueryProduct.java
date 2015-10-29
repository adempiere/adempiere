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

package org.adempiere.pos.search;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.adempiere.pos.WPOS;
import org.adempiere.pos.WPOSKeyboard;
import org.adempiere.pos.WPosTextField;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.PO;
import org.compiere.pos.QueryProduct;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

/**
 * 
 * @author Raul Muñoz 20/03/2015 
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
	public WQueryProduct (WPOS posPanel)
	{
		super(posPanel);
	}	//	PosQueryProduct
	
	private WPosTextField		f_Value;
	private WPosTextField		f_ProductName;
	private WPosTextField		f_UPC;
	private WPosTextField		f_SKU;
	private int					m_M_Product_ID;
	private String				m_ProductName;
	private BigDecimal			m_Price;
	//
	private int 				m_M_PriceList_Version_ID;
	private int 				m_M_Warehouse_ID;
	private boolean				isKeyboard;
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
	/** Result IDs              */
	private ArrayList<Integer>	m_results = new ArrayList<Integer>(3);
	
	/**
	 * 	Set up Panel
	 */
	protected void init()
	{
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid productLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(p_ctx, "Query"));
		
		//	Set title window
		this.setClosable(true);
		
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
		north.appendChild(groupPanel);
		groupPanel.appendChild(v_TitleBorder);
		groupPanel.appendChild(productLayout);
		appendChild(mainPanel);
		productLayout.setWidth("100%");
		Rows rows = null;
		Row row = null;
		rows = productLayout.newRows();
		row = rows.newRow();
		//
		Label lValue = new Label(Msg.translate(p_ctx, "Value"));
		row.appendChild(lValue.rightAlign());
		lValue.setStyle(WPOS.FONTSIZESMALL);
		f_Value = new WPosTextField(p_pos.getOSK_KeyLayout_ID());
		f_Value.setStyle(WPOS.FONTSIZESMALL);
		f_Value.setWidth("120px");
		row.appendChild(f_Value);
		//
		f_Value.addEventListener("onFocus",this);
		Label lUpc = new Label(Msg.translate(p_ctx, "UPC"));
		lUpc.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(lUpc.rightAlign());
		f_UPC = new WPosTextField(p_pos.getOSK_KeyLayout_ID());
		f_UPC.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(f_UPC);
		f_UPC.addEventListener("onFocus",this);
		f_UPC.setWidth("120px");
		//  New Line
		row = rows.newRow();
		//
		Label lName = new Label(Msg.translate(p_ctx, "Name"));
		lName.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild (lName.rightAlign());
		f_ProductName = new WPosTextField(p_pos.getOSK_KeyLayout_ID());
		f_ProductName.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(f_ProductName);
		f_ProductName.addEventListener("onFocus",this);
		f_ProductName.setWidth("120px");
		
		//
		Label lSku = new Label(Msg.translate(p_ctx, "SKU"));
		lSku.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(lSku.rightAlign());
		f_SKU = new WPosTextField(p_pos.getOSK_KeyLayout_ID());
		f_SKU.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(f_SKU);
		f_SKU.addEventListener("onFocus",this);
		f_SKU.setWidth("120px");
		//
		row.setHeight("65px");
		

		m_table = ListboxFactory.newDataTable();
		m_table.prepareTable (s_layout, s_sqlFrom, 
			s_sqlWhere, false, "RV_WarehousePrice");
		
		center = new Center();
		center.setStyle("border: none");
		m_table.setWidth("100%");
		m_table.setHeight("99%");
		m_table.addActionListener(this);
		center.appendChild(m_table);
		mainLayout.appendChild(center);
		m_table.loadTable(new PO[0]);
		m_table.setClass("Table-OrderLine");
		m_table.setMultiSelection(true);
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
		log.fine("M_Product_ID=" + m_M_Product_ID + " - " + m_ProductName + " - " + m_Price); 
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close()
	{
		log.fine("M_Product_ID=" + m_M_Product_ID); 
		this.detach();
	}	//	close


	@Override
	public void reset() {
		f_Value.setText(null);
		f_ProductName.setText(null);
		f_SKU.setText(null);
		f_UPC.setText(null);
		setResults(new MWarehousePrice[0]);
	}
	public String showKeyboard(Event e){
		isKeyboard = true;
		Textbox field = (Textbox) e.getTarget();

		WPOSKeyboard keyboard = v_POSPanel.getKeyboard();
		if(e.getName().equals(Events.ON_FOCUS)){
			keyboard.setPosTextField(field);	
			AEnv.showWindow(keyboard);
		}
		return field.getText();
	}

	@Override
	public void onEvent(Event event) throws Exception {
		if (event.getTarget().getId().equals("Refresh")) {
			refresh();
			return;
		}
		else if(event.getTarget().equals(f_Value.getComponent(WPosTextField.SECONDARY)) && !isKeyboard){
			f_Value.setValue(showKeyboard(event));
			f_Value.setFocus(true);
		}
		else if(event.getTarget().equals(f_UPC.getComponent(WPosTextField.SECONDARY)) && !isKeyboard){
			f_UPC.setValue(showKeyboard(event));
			f_UPC.setFocus(true);
		}
		else if(event.getTarget().equals(f_ProductName.getComponent(WPosTextField.SECONDARY)) && !isKeyboard){
			f_ProductName.setValue(showKeyboard(event));
			f_ProductName.setFocus(true);
		}
		else if(event.getTarget().equals(f_SKU.getComponent(WPosTextField.SECONDARY)) && !isKeyboard){
			f_SKU.setValue(showKeyboard(event));
			f_SKU.setFocus(true);
		}
		 else if(event.getTarget().equals(f_Value.getComponent(WPosTextField.PRIMARY))  
					|| event.getTarget().equals(f_UPC.getComponent(WPosTextField.PRIMARY))
					|| event.getTarget().equals(f_ProductName.getComponent(WPosTextField.PRIMARY))
					|| event.getTarget().equals(f_SKU.getComponent(WPosTextField.PRIMARY))) {
			 	 refresh();
				 isKeyboard = false;
			}
		enableButtons();
		if(event.getTarget().getId().equals("Ok")){
			saveSelection();
			close();
		}
		if(event.getTarget().getId().equals("Cancel")){
			close();
		}
		else if(event.getTarget().getId().equals("Reset")){
			reset();
		}

	}

	@Override
	public void refresh() {
		setResults(MWarehousePrice.find (p_ctx,
				m_M_PriceList_Version_ID, m_M_Warehouse_ID,
				f_Value.getText(), f_ProductName.getText(), 
				f_UPC.getText(), f_SKU.getText(), null));
			return;
	}


	@Override
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
		log.fine("M_Product_ID=" + m_M_Product_ID + " - " + m_ProductName + " - " + m_Price);
	}


	@Override
	protected void cancel() {
		m_M_Product_ID = -1;
		m_ProductName = null;
		m_Price = Env.ZERO;
	}	
	public int getRecord_ID() {
		return m_M_Product_ID;
	}

	public String getValue() {
		return m_ProductName;
	}
	
	/**
	 *	Get selected Keys
	 *  @return selected keys (Integers)
	 */
	public Object[] getSelectedKeys()
	{
		if (m_results.size() == 0)
			return null;
		return m_results.toArray(new Integer[0]);
	}	//	getSelectedKeys;

	/**
	 *	Get (first) selected Key
	 *  @return selected key
	 */
	public Object getSelectedKey()
	{
		if ( m_results.size() == 0)
			return null;
		return m_results.get(0);
	}	//	getSelectedKey
	
	/**
     *  Get the keys of selected row/s based on layout defined in prepareTable
     *  @return IDs if selection present
     *  @author ashley
     */
    protected ArrayList<Integer> getSelectedRowKeys()
    {
        ArrayList<Integer> selectedDataList = new ArrayList<Integer>();
        
        if (m_table.getKeyColumnIndex() == -1) {
            return selectedDataList;
        }
        
       int[] rows = m_table.getSelectedIndices();
        for (int row = 0; row < rows.length; row++) {
            Object data = m_table.getModel().getValueAt(rows[row], m_table.getKeyColumnIndex());
            if (data instanceof IDColumn) {
                IDColumn dataColumn = (IDColumn)data;
                selectedDataList.add(dataColumn.getRecord_ID());
            }
            else {
                log.severe("For multiple selection, IDColumn should be key column for selection");
            }
        }
        
        if (selectedDataList.size() == 0) {
        	int row = m_table.getSelectedRow();
    		if (row != -1 && m_table.getKeyColumnIndex() != -1) {
    			Object data = m_table.getModel().getValueAt(row, m_table.getKeyColumnIndex());
    			if (data instanceof IDColumn)
    				selectedDataList.add(((IDColumn)data).getRecord_ID());
    			if (data instanceof Integer)
    				selectedDataList.add((Integer)data);
    		}
        }
      
        return selectedDataList;
    }   //  getSelectedRowKeys

    /**
	 *	Save Selection	- Called by dispose
	 */
	protected void saveSelection ()	{
		//	Already disposed
		if (m_table == null)
			return;

		m_results.addAll(getSelectedRowKeys());

		//	Save Settings of detail info screens
//		saveSelectionDetail();
		
	}	//	saveSelection

}	//	PosQueryProduct
