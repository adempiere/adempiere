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

import java.awt.Cursor;
import java.awt.event.WindowFocusListener;
import java.math.BigDecimal;

import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import org.adempiere.pos.POSTextField;
import org.adempiere.pos.VPOS;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MWarehousePrice;
import org.compiere.model.PO;
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
 *  @author victor.perez@e-evolution.com , http://www.e-evolution.com
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
	private POSTextField fieldValue;
	private POSTextField fieldProductName;
	private POSTextField fieldUPC;
	private POSTextField fieldSKU;
	/**	Internal Variables	*/
	private int 			productId;
	private String 			productName;
	private BigDecimal 		price;
	private int 			priceListVersionId;
	private int 			warehouseId;
	/**	Logger				*/
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
	
	@Override
	public synchronized void addWindowFocusListener(WindowFocusListener l) {
		super.addWindowFocusListener(l);
	}
	
	/**	From Clause							*/
	private static String sqlFrom = "RV_WarehousePrice";
	/** Where Clause						*/
	private static String sqlWhere = "IsActive='Y'";

	/**
	 * 	Set up Panel
	 */
	protected void init() {
		setTitle(Msg.parseTranslation(posPanel.getCtx() , "@M_Product_ID@"));
		parameterPanel.setLayout(new MigLayout("fill", "", "[50][50][]"));
		parameterPanel.setBorder(new TitledBorder(Msg.getMsg(ctx, "Query")));
		//
		CLabel labelValue = new CLabel(Msg.translate(ctx, "Value"));
		parameterPanel.add (labelValue, "growy");
		fieldValue = new POSTextField("", posPanel.getKeyboard());
		labelValue.setLabelFor(fieldValue);
		parameterPanel.add(fieldValue,  "h 30, w 200");
		fieldValue.addActionListener(this);
		//
		CLabel lableUPC = new CLabel(Msg.translate(ctx, "UPC"));
		parameterPanel.add (lableUPC, "growy");
		fieldUPC = new POSTextField("", posPanel.getKeyboard());
		lableUPC.setLabelFor(fieldUPC);
		parameterPanel.add(fieldUPC,  "h 30, w 200, wrap");
		fieldUPC.addActionListener(this);
		//
		CLabel labelName = new CLabel(Msg.translate(ctx, "Name"));
		parameterPanel.add (labelName, "growy");
		fieldProductName = new POSTextField("", posPanel.getKeyboard());
		labelName.setLabelFor(fieldProductName);
		parameterPanel.add(fieldProductName,  "h 30, w 200");
		fieldProductName.addActionListener(this);
		//
		CLabel labelSKU = new CLabel(Msg.translate(ctx, "SKU"));
		parameterPanel.add (labelSKU, "growy");
		fieldSKU = new POSTextField("", posPanel.getKeyboard());
		labelSKU.setLabelFor(fieldSKU);
		parameterPanel.add(fieldSKU,  "h 30, w 200");
		fieldSKU.addActionListener(this);
		//	Prepare Table
		posTable.prepareTable (columnInfos, sqlFrom,
				sqlWhere, false, "RV_WarehousePrice");
		//	
		posTable.setColumnVisibility(posTable.getColumn(0), false);
		posTable.getColumn(1).setPreferredWidth(175);
		posTable.getColumn(2).setPreferredWidth(175);
		posTable.getColumn(3).setPreferredWidth(100);
		posTable.getColumn(4).setPreferredWidth(75);
		posTable.getColumn(5).setPreferredWidth(75);
		posTable.getColumn(6).setPreferredWidth(75);
		posTable.getColumn(7).setPreferredWidth(75);
		posTable.setFillsViewportHeight(true); //@Trifon
		posTable.growScrollbars();
	}	//	init
	
	/**
	 * 	Set Query Data
	 *	@param M_PriceList_Version_ID plv
	 *	@param M_Warehouse_ID wh
	 */
	public void setQueryData (int M_PriceList_Version_ID, int M_Warehouse_ID) {
		priceListVersionId = M_PriceList_Version_ID;
		warehouseId = M_Warehouse_ID;
	}	//	setQueryData
	
	/**
	 * 	Set/display Results
	 *	@param results results
	 */
	private void setResultsFromArray(MWarehousePrice[] results) {
		posTable.loadTable(results);
		int rowCount = posTable.getRowCount();
		if (rowCount > 0) {
			posTable.setRowSelectionInterval(0, 0);
			if(rowCount == 1) {
				select();
			}
		}
	}	//	setResults

	/**
	 * 	Enable/Set Buttons and set ID
	 */
	protected void select() {
		cleanValues();
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
	}	//	enableButtons

	/**
	 * Clean Values
	 * @return void
	 */
	private void cleanValues() {
		productId = -1;
		productName = null;
		price = Env.ZERO;
	}

	/**
	 * 	Close.
	 * 	Set Values on other panels and close
	 */
	protected void close() {
		dispose();
	}	//	close

	@Override
	public void reset() {
		fieldValue.setText(null);
		fieldProductName.setText(null);
		fieldSKU.setText(null);
		fieldUPC.setText(null);
		setResults(new MWarehousePrice[0]);
	}

	@Override
	public void refresh() {
		cleanValues();
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setResults(MWarehousePrice.find (ctx,
				priceListVersionId, warehouseId,
				fieldValue.getText(), fieldProductName.getText(), fieldUPC.getText(), fieldSKU.getText(), null));
		this.setCursor(Cursor.getDefaultCursor());
	}
	
	@Override
	public void setResults(PO[] results) {
		//	Valid null result
		if(results == null
				|| !(results instanceof MWarehousePrice[]))
			return;
		//	
		setResultsFromArray((MWarehousePrice[]) results);
	}

	@Override
	protected void cancel() {
		cleanValues();
		dispose();
	}

	@Override
	public int getRecord_ID() {
		return productId;
	}

	@Override
	public String getValue() {
		return productName;
	}
	
}	//	PosQueryProduct