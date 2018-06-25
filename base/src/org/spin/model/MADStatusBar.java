/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Class for handle status bar
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1784 ]
 * @see https://github.com/adempiere/adempiere/issues/1784
 */
public class MADStatusBar extends X_AD_StatusBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9157152272245789095L;
	
	public MADStatusBar(Properties ctx, int AD_StatusBar_ID, String trxName) {
		super(ctx, AD_StatusBar_ID, trxName);
	}
	
	public MADStatusBar(Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }
	
	/** Static Cache */
	private static CCache<Integer, MADStatusBar> statusBarCacheIds = new CCache<Integer, MADStatusBar>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MADStatusBar> statusBarCacheFromIds = new CCache<String, MADStatusBar>(Table_Name, 30);
	
	/**
	 * Get/Load Status Bar [CACHED]
	 * @param ctx context
	 * @param statusBarId
	 * @return status bar or null
	 */
	public static MADStatusBar getById(Properties ctx, int statusBarId) {
		if (statusBarId <= 0)
			return null;

		MADStatusBar statusBar = statusBarCacheIds.get(statusBarId);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;

		statusBar = new Query(ctx , Table_Name , COLUMNNAME_AD_StatusBar_ID + "=?" , null)
				.setParameters(statusBarId)
				.first();
		if (statusBar != null && statusBar.get_ID() > 0) {
			statusBarCacheIds.put(statusBar.get_ID(), statusBar);
		}
		return statusBar;
	}
	
	/**
	 * Get From Tab ID
	 * @param ctx
	 * @param tabId
	 * @return
	 */
	public static MADStatusBar getFromTabId(Properties ctx, int tabId) {
		if (tabId <= 0)
			return null;
		String key = "Tab|" + tabId;
		MADStatusBar statusBar = statusBarCacheFromIds.get(key);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;

		statusBar = new Query(ctx , Table_Name , "EXISTS(SELECT 1 FROM AD_Tab t "
				+ "WHERE t.AD_Tab_ID = ? "
				+ "AND t.AD_StatusBar_ID = AD_StatusBar.AD_StatusBar_ID)" , null)
				.setParameters(tabId)
				.first();
		if (statusBar != null && statusBar.get_ID() > 0) {
			statusBarCacheFromIds.put(key, statusBar);
		}
		return statusBar;
	}
	
	/**
	 * Get From Table ID
	 * @param ctx
	 * @param tableId
	 * @return
	 */
	public static MADStatusBar getFromTableId(Properties ctx, int tableId) {
		if (tableId <= 0)
			return null;
		String key = "Table|" + tableId;
		MADStatusBar statusBar = statusBarCacheFromIds.get(key);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;

		statusBar = new Query(ctx , Table_Name , "EXISTS(SELECT 1 FROM AD_Table t "
				+ "WHERE t.AD_Table_ID = ? "
				+ "AND t.AD_StatusBar_ID = AD_StatusBar.AD_StatusBar_ID)" , null)
				.setParameters(tableId)
				.first();
		if (statusBar != null && statusBar.get_ID() > 0) {
			statusBarCacheFromIds.put(key, statusBar);
		}
		return statusBar;
	}

	/**
	 * Get From Window ID
	 * @param ctx
	 * @param windowId
	 * @return
	 */
	public static MADStatusBar getFromWindowId(Properties ctx, int windowId) {
		if (windowId <= 0)
			return null;
		String key = "Window|" + windowId;
		MADStatusBar statusBar = statusBarCacheFromIds.get(key);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;

		statusBar = new Query(ctx , Table_Name , "EXISTS(SELECT 1 FROM AD_Window w "
				+ "WHERE w.AD_Window_ID = ? "
				+ "AND w.AD_StatusBar_ID = AD_StatusBar.AD_StatusBar_ID)" , null)
				.setParameters(windowId)
				.first();
		if (statusBar != null && statusBar.get_ID() > 0) {
			statusBarCacheFromIds.put(key, statusBar);
		}
		return statusBar;
	}
	
	
	/**
	 * Get Arguments from Custom SQL with window no
	 * @param windowNo User for parse context
	 * @return
	 */
	public Object[] getArguments(int windowNo) {
		String sql = getSQLStatement();
		if(Util.isEmpty(sql)) {
			return null;
		}
		//	Parse It
		sql = Env.parseContext(getCtx(), windowNo, sql, false, false);
		if(Util.isEmpty(sql)) {
			return null;
		}
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnQty = metaData.getColumnCount();
			Object[] arguments = new Object[columnQty];
			if (rs.next()) {
				for(int column = 0; column < columnQty; column++)  {
					Object value = rs.getObject(column + 1);
					if(value instanceof BigDecimal
							&& value instanceof Double
							&& value instanceof Float
							&& value instanceof Integer
							&& value instanceof String
							&& value instanceof Timestamp) {
						continue;
					}
					if(value == null) {
						value = new String();
					}
					//	
					if(value instanceof BigDecimal) {
						value = ((BigDecimal) value).doubleValue();
					} else if(value instanceof Timestamp) {
						value = new Long(((Timestamp) value).getTime());
					} else if(value instanceof Number) {
						value = new Integer(((Number) value).intValue());
					} else {
						value = String.valueOf(value);
					}
					//	Set value
					arguments[column] = value;
				}
				//	Return
				return arguments;
			}
			DB.close(rs, pstmt);
		} catch (SQLException e) {
			DB.close(rs, pstmt);
			log.log(Level.WARNING, "\nSQL=" + sql, e);
		}
		return null;
	}

	@Override
	public String toString() {
		return "MADStatusBar [getAD_StatusBar_ID()=" + getAD_StatusBar_ID() + ", getName()=" + getName()
				+ ", getValue()=" + getValue() + "]";
	}
	
}
