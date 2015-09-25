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
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.pos;

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
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com Sep 24, 2015, 2:37:58 AM
 * <li> Define columns of order line table
 * <li> Handle the Load from DB
 */
public class POSOrderLineTableHandle {
	
	/**
	 * 
	 * *** Constructor ***
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
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
	public static final String 	C_UOM_ID    = "C_UOM_ID";
	public static final String 	PRICEACTUAL = "PriceActual";
	public static final String 	LINENETAMT  = "LineNetAmt";
	public static final String 	GRANDTOTAL  = "GrandTotal";
	/**	Column Position		*/
	public static final int	POSITION_C_ORDER_ID 	= 0;
	public static final int	POSITION_QTYORDERED 	= 2;
	public static final int	POSITION_PRICE 			= 4;
	public static final int	POSITION_LINENETAMT 	= 5;
	public static final int	POSITION_GRANDTOTAL 	= 7;
	
	/**	Table Column Layout Info	*/
	private ColumnInfo[] s_layout = new ColumnInfo[] {
		new ColumnInfo(" ", "C_OrderLine_ID", IDColumn.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), PRODUCTNAME), PRODUCTNAME, String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), QTYORDERED), QTYORDERED, BigDecimal.class, false, true, null),
		new ColumnInfo(Msg.translate(Env.getCtx(), C_UOM_ID), "UOMSymbol", String.class),
		new ColumnInfo(Msg.translate(Env.getCtx(), PRICEACTUAL), PRICEACTUAL, BigDecimal.class, false, true, null), 
		new ColumnInfo(Msg.translate(Env.getCtx(), LINENETAMT), LINENETAMT, BigDecimal.class), 
		new ColumnInfo(Msg.translate(Env.getCtx(), "C_Tax_ID"), "TaxIndicator", String.class, true, true, null), 
		new ColumnInfo(Msg.translate(Env.getCtx(), GRANDTOTAL), GRANDTOTAL, BigDecimal.class,  true, true, null), 
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
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return boolean
	 */
	public boolean prepareTable() {
		if(m_Table == null)
			return false;
		//	Default Prepare
		m_sql = m_Table.prepareTable (s_layout, s_sqlFrom, 
				s_sqlWhere, false, TABLE_NAME);
		m_Table.setColumnClass(POSITION_QTYORDERED, BigDecimal.class, false);
		m_Table.setColumnClass(POSITION_PRICE, BigDecimal.class, false);
		//	Default Return
		return true;
	}
	
	/**
	 * Load Table from SQL
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @param p_C_Order_ID
	 * @return
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