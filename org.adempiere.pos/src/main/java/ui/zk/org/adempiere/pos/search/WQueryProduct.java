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
import org.adempiere.pos.WPOSTextField;
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
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Raul Muñoz, rmunoz@erpcya.com, ERPCYA http://www.erpcya.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * @author victor.perez@e-evolution.com , http://www.e-evolution.com
 */
public class WQueryProduct extends WPOSQuery
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
	
	/** Fields 					*/
	private WPOSTextField 	fieldValue;
	private WPOSTextField 	fieldProductName;
	private WPOSTextField 	fieldUPC;
	private WPOSTextField 	fieldSKU;
	private int 			productId;
	private String 			productName;
	private BigDecimal 		price;
	//
	private int 			priceListVersionId;
	private int 			warehouseId;
	private boolean			isKeyboard;
	/**	Logger			*/
	private static CLogger logger = CLogger.getCLogger(QueryProduct.class);
	
	/**	Table Column Layout Info			*/
	private static ColumnInfo[] columnInfos = new ColumnInfo[]
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
	private static String sqlFrom = "RV_WarehousePrice";
	/** Where Clause						*/
	private static String sqlWhere = "IsActive='Y'";
	/** Result IDs              */
	private ArrayList<Integer> results = new ArrayList<Integer>(3);
	
	/**
	 * 	Set up Panel
	 */
	protected void init()
	{
		setTitle(Msg.translate(Env.getCtx(), "M_Product_ID"));
		Panel panel = new Panel();
		setVisible(true);
		Panel mainPanel = new Panel();
		Grid productLayout = GridFactory.newGridLayout();
		
		Groupbox groupPanel = new Groupbox();
		Caption v_TitleBorder = new Caption(Msg.getMsg(ctx, "Query"));
		
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
		Label labelValue = new Label(Msg.translate(ctx, "Value"));
		row.appendChild(labelValue.rightAlign());
		labelValue.setStyle(WPOS.FONTSIZESMALL);
		fieldValue = new WPOSTextField(null, posPanel.getKeyboard());
		fieldValue.setStyle(WPOS.FONTSIZESMALL);
		fieldValue.setWidth("120px");
		row.appendChild(fieldValue);
		//
		fieldValue.addEventListener("onFocus",this);
		Label labelUPC = new Label(Msg.translate(ctx, "UPC"));
		labelUPC.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(labelUPC.rightAlign());
		fieldUPC = new WPOSTextField(null, posPanel.getKeyboard());
		fieldUPC.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(fieldUPC);
		fieldUPC.addEventListener("onFocus",this);
		fieldUPC.setWidth("120px");
		//  New Line
		row = rows.newRow();
		//
		Label labelName = new Label(Msg.translate(ctx, "Name"));
		labelName.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild (labelName.rightAlign());
		fieldProductName = new WPOSTextField(null, posPanel.getKeyboard());
		fieldProductName.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(fieldProductName);
		fieldProductName.addEventListener("onFocus",this);
		fieldProductName.setWidth("120px");
		
		//
		Label labelSKU = new Label(Msg.translate(ctx, "SKU"));
		labelSKU.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(labelSKU.rightAlign());
		fieldSKU = new WPOSTextField(null, posPanel.getKeyboard());
		fieldSKU.setStyle(WPOS.FONTSIZESMALL);
		row.appendChild(fieldSKU);
		fieldSKU.addEventListener("onFocus",this);
		fieldSKU.setWidth("120px");
		//
		row.setHeight("65px");
		

		posTable = ListboxFactory.newDataTable();
		posTable.prepareTable (columnInfos, sqlFrom,
				sqlWhere, false, "RV_WarehousePrice");
		
		center = new Center();
		center.setStyle("border: none");
		posTable.setWidth("100%");
		posTable.setHeight("99%");
		posTable.addActionListener(this);
		center.appendChild(posTable);
		mainLayout.appendChild(center);
		posTable.loadTable(new PO[0]);
		posTable.setClass("Table-OrderLine");
		posTable.setMultiSelection(true);
	}	//	init

	
	/**
	 * 	Set Query Data
	 *	@param M_PriceList_Version_ID plv
	 *	@param M_Warehouse_ID wh
	 */
	public void setQueryData (int M_PriceList_Version_ID, int M_Warehouse_ID)
	{
		priceListVersionId = M_PriceList_Version_ID;
		warehouseId = M_Warehouse_ID;
	}	//	setQueryData
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	public void setResults (MWarehousePrice[] results)
	{
		posTable.loadTable(results);
		if (posTable.getRowCount() >0 )
			enableButtons();
		posTable.autoSize();
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void enableButtons()
	{
		productId = -1;
		productName = null;
		price = null;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer id = posTable.getSelectedRowKey();
			if (id != null)
			{
				productId = id.intValue();
				productName = (String) posTable.getValueAt(row, 2);
				price = (BigDecimal) posTable.getValueAt(row, 7);
			}
		}
		logger.fine("M_Product_ID=" + productId + " - " + productName + " - " + price);
	}	//	enableButtons

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close()
	{
		logger.fine("M_Product_ID=" + productId);
		this.detach();
	}	//	close


	@Override
	public void reset() {
		fieldValue.setText(null);
		fieldProductName.setText(null);
		fieldSKU.setText(null);
		fieldUPC.setText(null);
		setResults(new MWarehousePrice[0]);
	}
	public String showKeyboard(Event e){
		isKeyboard = true;
		Textbox field = (Textbox) e.getTarget();

		WPOSKeyboard keyboard = posPanel.getKeyboard();
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
		else if(event.getTarget().equals(fieldValue.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			fieldValue.setValue(showKeyboard(event));
			fieldValue.setFocus(true);
		}
		else if(event.getTarget().equals(fieldUPC.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			fieldUPC.setValue(showKeyboard(event));
			fieldUPC.setFocus(true);
		}
		else if(event.getTarget().equals(fieldProductName.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			fieldProductName.setValue(showKeyboard(event));
			fieldProductName.setFocus(true);
		}
		else if(event.getTarget().equals(fieldSKU.getComponent(WPOSTextField.SECONDARY)) && !isKeyboard){
			fieldSKU.setValue(showKeyboard(event));
			fieldSKU.setFocus(true);
		}
		 else if(event.getTarget().equals(fieldValue.getComponent(WPOSTextField.PRIMARY))
					|| event.getTarget().equals(fieldUPC.getComponent(WPOSTextField.PRIMARY))
					|| event.getTarget().equals(fieldProductName.getComponent(WPOSTextField.PRIMARY))
					|| event.getTarget().equals(fieldSKU.getComponent(WPOSTextField.PRIMARY))) {
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
		setResults(MWarehousePrice.find (ctx,
				priceListVersionId, warehouseId,
				fieldValue.getText(), fieldProductName.getText(),
				fieldUPC.getText(), fieldSKU.getText(), null));
			return;
	}


	@Override
	protected void select() {
		productId = -1;
		productName = null;
		price = null;
		int row = posTable.getSelectedRow();
		boolean enabled = row != -1;
		if (enabled)
		{
			Integer ID = posTable.getSelectedRowKey();
			if (ID != null)
			{
				productId = ID.intValue();
				productName = (String) posTable.getValueAt(row, 2);
				price = (BigDecimal) posTable.getValueAt(row, 7);
			}
		}
		logger.fine("M_Product_ID=" + productId + " - " + productName + " - " + price);
	}


	@Override
	protected void cancel() {
		productId = -1;
		productName = null;
		price = Env.ZERO;
	}	
	public int getRecord_ID() {
		return productId;
	}

	public String getValue() {
		return productName;
	}
	
	/**
	 *	Get selected Keys
	 *  @return selected keys (Integers)
	 */
	public Object[] getSelectedKeys()
	{
		if (results.size() == 0)
			return null;
		return results.toArray(new Integer[0]);
	}	//	getSelectedKeys;

	/**
	 *	Get (first) selected Key
	 *  @return selected key
	 */
	public Object getSelectedKey()
	{
		if ( results.size() == 0)
			return null;
		return results.get(0);
	}	//	getSelectedKey
	
	/**
     *  Get the keys of selected row/s based on layout defined in prepareTable
     *  @return IDs if selection present
     *  @author ashley
     */
    protected ArrayList<Integer> getSelectedRowKeys()
    {
        ArrayList<Integer> selectedDataList = new ArrayList<Integer>();
        
        if (posTable.getKeyColumnIndex() == -1) {
            return selectedDataList;
        }
        
       int[] rows = posTable.getSelectedIndices();
        for (int row = 0; row < rows.length; row++) {
            Object data = posTable.getModel().getValueAt(rows[row], posTable.getKeyColumnIndex());
            if (data instanceof IDColumn) {
                IDColumn dataColumn = (IDColumn)data;
                selectedDataList.add(dataColumn.getRecord_ID());
            }
            else {
                logger.severe("For multiple selection, IDColumn should be key column for selection");
            }
        }
        
        if (selectedDataList.size() == 0) {
        	int row = posTable.getSelectedRow();
    		if (row != -1 && posTable.getKeyColumnIndex() != -1) {
    			Object data = posTable.getModel().getValueAt(row, posTable.getKeyColumnIndex());
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
		if (posTable == null)
			return;

		results.addAll(getSelectedRowKeys());

		//	Save Settings of detail info screens
//		saveSelectionDetail();
		
	}	//	saveSelection

}	//	PosQueryProduct
