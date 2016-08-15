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
 * Copyright (C) 2003-2013 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor: Yamel Senih www.erpcya.com                                    *
 * Contributor: Mario Calderon www.westfalia-it.com                           *
 *****************************************************************************/
package org.adempiere.pos.service;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.minigrid.IMiniTable;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * @author Mario Calderon, mario.calderon@westfalia-it.com, Systemhaus Westfalia, http://www.westfalia-it.com
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/01/16.
 * <li> Define columns of order line table
 * <li> Handle the Load from DB
 */
public class POSOrderLineTableHandle {
	
	/**
	 * 
	 * *** Constructor ***
	 * @param p_OrderLineTable
	 */
	public POSOrderLineTableHandle (IMiniTable p_OrderLineTable) {
		table = p_OrderLineTable;
	}
	
	/**	Table Name			*/
	private static final String	TABLE_NAME	= "POS_OrderLine_v";
	
	/**	Column Names		*/
	public static final String 	PRODUCTNAME	= "ProductName";
	public static final String 	QTYORDERED  = "QtyOrdered";
	public static final String 	UOM    		= "C_UOM_ID";
	public static final String 	PRICEACTUAL = "PriceActual";
	public static final String 	TAX 		= "C_Tax_ID";
	public static final String 	DISCOUNT 	= "Discount";
	public static final String 	LINENETAMT  = "LineNetAmt";
	public static final String 	GRANDTOTAL  = "GrandTotal";
	
	/**	Columns Quantity	*/
	public static final int	COLUMN_QTY = 10;
	
	/**	Column Position		*/
	public static final int POSITION_C_ORDERLINE_ID = 0;
	//public static final int	POSITION_DELETE	 		= 1;
	public static final int	POSITION_QTYORDERED 	= 2;
	public static final int	POSITION_PRICE 			= 4;
	public static final int	POSITION_DISCOUNT 		= 5;
	public static final int	POSITION_LINENETAMT 	= 6;
	public static final int	POSITION_GRANDTOTAL 	= 8;
	
	/**	Table Column Layout Info	*/
	private ColumnInfo[] columns = new ColumnInfo[] {
		new ColumnInfo(" ", "C_OrderLine_ID", IDColumn.class,0,false,true,null,false), 
		//new ColumnInfo("", "C_OrderLine_ID", DeleteColumn.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PRODUCTNAME), PRODUCTNAME, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Qty"), QTYORDERED , BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), UOM), "UOMSymbol", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Price"), PRICEACTUAL, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), DISCOUNT), DISCOUNT, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "SubTotal"), LINENETAMT, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), TAX), "TaxIndicator", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), "Total"), GRANDTOTAL, BigDecimal.class),
	};
	
	/**	From Clause					*/
	private final String sqlFrom = "POS_OrderLine_v";
	/** Where Clause				*/
	private final String sqlWhere = "C_Order_ID=?";
	/** The Query SQL				*/
	private String sqlStatement;
	/**	Table						*/
	private IMiniTable table;
	/**	Logger						*/
	private static CLogger 	log = CLogger.getCLogger(POSOrderLineTableHandle.class);
	
	/**
	 * Prepare Table
	 * @return boolean
	 */
	public boolean prepareTable() {
		if(table == null)
			return false;
		//	Default Prepare
		sqlStatement = table.prepareTable (columns, sqlFrom,
				sqlWhere, false, TABLE_NAME);
		//	Default Return
		return true;
	}
	
	/**
	 * Set Editable Quantity and Price
	 * @param isModifyPrice
	 * @param isDrafted
	 * @return void
	 */
	public void setEditable(boolean isModifyPrice, boolean isDrafted) {
		table.setColumnClass(POSITION_QTYORDERED, BigDecimal.class, !isDrafted);
		table.setColumnClass(POSITION_PRICE, BigDecimal.class, !(isModifyPrice && isDrafted));
	}
	
	/**
	 * Load Table from SQL
	 * @param orderId
	 * @return boolean
	 */
	public boolean loadTable(int orderId) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = DB.prepareStatement (sqlStatement, null);
			statement.setInt (1, orderId);
			resultSet = statement.executeQuery ();
			if (resultSet != null)
				table.loadTable(resultSet);
			//	is Ok
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, sqlStatement, e);
		} finally {
			DB.close(resultSet, statement);
			resultSet = null; statement = null;
		}
		//	Return
		return false;
	}
}