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
package org.adempiere.pos;

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
		m_Table = p_OrderLineTable;
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
	/**	Column Position		*/
	public static final int	POSITION_C_ORDER_ID 	= 0;
	public static final int	POSITION_QTYORDERED 	= 2;
	public static final int	POSITION_PRICE 			= 4;
	public static final int	POSITION_LINENETAMT 	= 6;
	public static final int	POSITION_GRANDTOTAL 	= 8;
	
	/**	Table Column Layout Info	*/
	private ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_OrderLine_ID", IDColumn.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), PRODUCTNAME), PRODUCTNAME, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), QTYORDERED), QTYORDERED, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), UOM), "UOMSymbol", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PRICEACTUAL), PRICEACTUAL, BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), DISCOUNT), DISCOUNT, BigDecimal.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), LINENETAMT), LINENETAMT, BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), TAX), "TaxIndicator", String.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), GRANDTOTAL), GRANDTOTAL, BigDecimal.class), 
	};
	
	/**	From Clause					*/
	private String 			s_sqlFrom = "POS_OrderLine_v";
	/** Where Clause				*/
	private String 			s_sqlWhere = "C_Order_ID=?";
	/** The Query SQL				*/
	private String			m_sql;
	/**	Table						*/
	private IMiniTable		m_Table;
	/**	Logger						*/
	private static CLogger 	log = CLogger.getCLogger(POSOrderLineTableHandle.class);
	
	/**
	 * Prepare Table
	 * @return boolean
	 */
	public boolean prepareTable() {
		if(m_Table == null)
			return false;
		//	Default Prepare
		m_sql = m_Table.prepareTable (s_layout, s_sqlFrom, 
				s_sqlWhere, false, TABLE_NAME);
		//	Default Return
		return true;
	}
	
	/**
	 * Set Editable Quantity and Price
	 * @param p_IsModifyPrice
	 * @param p_IsDrafted
	 * @return void
	 */
	public void setEditable(boolean p_IsModifyPrice, boolean p_IsDrafted) {
		m_Table.setColumnClass(POSITION_QTYORDERED, BigDecimal.class, !p_IsDrafted);
		m_Table.setColumnClass(POSITION_PRICE, BigDecimal.class, !(p_IsModifyPrice && p_IsDrafted));
	}
	
	/**
	 * Load Table from SQL
	 * @param p_C_Order_ID
	 * @return boolean
	 */
	public boolean loadTable(int p_C_Order_ID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (m_sql, null);
			pstmt.setInt (1, p_C_Order_ID);
			rs = pstmt.executeQuery ();
			m_Table.loadTable(rs);
			//	is Ok
			return true;
		} catch (Exception e) {
			log.log(Level.SEVERE, m_sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		//	Return
		return false;
	}
}