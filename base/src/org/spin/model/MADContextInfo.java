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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_AD_Field;
import org.compiere.model.MField;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Class for handle status bar
 * @author yamel, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1784 ]
 * @see https://github.com/adempiere/adempiere/issues/1784
 */
public class MADContextInfo extends X_AD_ContextInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9157152272245789095L;
	/**	Logger			*/
	private static CLogger	log = CLogger.getCLogger(MADContextInfo.class);
	
	public MADContextInfo(Properties ctx, int contextInfoId, String trxName) {
		super(ctx, contextInfoId, trxName);
	}
	
	public MADContextInfo(Properties ctx, ResultSet rs, String trxName) {
      super (ctx, rs, trxName);
    }
	
	/** Static Cache */
	private static CCache<Integer, MADContextInfo> contextInfoCacheIds = new CCache<Integer, MADContextInfo>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, MADContextInfo> contextInfoCacheFromIds = new CCache<String, MADContextInfo>(Table_Name, 30);
	/** Static Cache */
	private static CCache<String, Map<String, MADContextInfo>> contextInfoCacheFielsIds = new CCache<String, Map<String, MADContextInfo>>(Table_Name, 30);
	
	/**
	 * Get/Load Status Bar [CACHED]
	 * @param ctx context
	 * @param statusBarId
	 * @return status bar or null
	 */
	public static MADContextInfo getById(Properties ctx, int statusBarId) {
		if (statusBarId <= 0)
			return null;

		MADContextInfo contextInfo = contextInfoCacheIds.get(statusBarId);
		if (contextInfo != null && contextInfo.get_ID() > 0)
			return contextInfo;

		contextInfo = new Query(ctx , Table_Name , COLUMNNAME_AD_ContextInfo_ID + "=?" , null)
				.setParameters(statusBarId)
				.first();
		if (contextInfo != null && contextInfo.get_ID() > 0) {
			contextInfoCacheIds.put(contextInfo.get_ID(), contextInfo);
		}
		return contextInfo;
	}
	
	/**
	 * Get From Tab ID
	 * @param ctx
	 * @param tabId
	 * @return
	 */
	public static MADContextInfo getFromTabId(Properties ctx, int tabId) {
		if (tabId <= 0)
			return null;
		String key = "Tab|" + tabId;
		MADContextInfo statusBar = contextInfoCacheFromIds.get(key);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;
		try {
			statusBar = new Query(ctx , Table_Name , "EXISTS(SELECT 1 FROM AD_Tab t "
					+ "WHERE t.AD_Tab_ID = ? "
					+ "AND t.AD_ContextInfo_ID = AD_ContextInfo.AD_ContextInfo_ID)" , null)
					.setParameters(tabId)
					.first();
		} catch(Exception e) {
			log.severe("getFromTabId: " + e.getLocalizedMessage());
		}
		
		if (statusBar != null && statusBar.get_ID() > 0) {
			contextInfoCacheFromIds.put(key, statusBar);
		}
		return statusBar;
	}
	
	/**
	 * Get From Table ID
	 * @param ctx
	 * @param tableId
	 * @return
	 */
	public static MADContextInfo getFromTableId(Properties ctx, int tableId) {
		if (tableId <= 0)
			return null;
		String key = "Table|" + tableId;
		MADContextInfo statusBar = contextInfoCacheFromIds.get(key);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;
		try {
			statusBar = new Query(ctx , Table_Name , "EXISTS(SELECT 1 FROM AD_Table t "
					+ "WHERE t.AD_Table_ID = ? "
					+ "AND t.AD_ContextInfo_ID = AD_ContextInfo.AD_ContextInfo_ID)" , null)
					.setParameters(tableId)
					.first();
		} catch(Exception e) {
			log.severe("getFromTableId: " + e.getLocalizedMessage());
		}
		
		if (statusBar != null && statusBar.get_ID() > 0) {
			contextInfoCacheFromIds.put(key, statusBar);
		}
		return statusBar;
	}

	/**
	 * Get From Window ID
	 * @param ctx
	 * @param windowId
	 * @return
	 */
	public static MADContextInfo getFromWindowId(Properties ctx, int windowId) {
		if (windowId <= 0)
			return null;
		String key = "Window|" + windowId;
		MADContextInfo statusBar = contextInfoCacheFromIds.get(key);
		if (statusBar != null && statusBar.get_ID() > 0)
			return statusBar;
		try {
			statusBar = new Query(ctx , Table_Name , "EXISTS(SELECT 1 FROM AD_Window w "
					+ "WHERE w.AD_Window_ID = ? "
					+ "AND w.AD_ContextInfo_ID = AD_ContextInfo.AD_ContextInfo_ID)" , null)
					.setParameters(windowId)
					.first();	
		} catch(Exception e) {
			log.severe("getFromWindowId: " + e.getLocalizedMessage());
		}
		
		if (statusBar != null && statusBar.get_ID() > 0) {
			contextInfoCacheFromIds.put(key, statusBar);
		}
		return statusBar;
	}
	
	/**
	 * Get List of context info for tab
	 * @param ctx
	 * @param tabId
	 * @return
	 */
	public static Map<String, MADContextInfo> getFromTabIdForField(Properties ctx, int tabId) {
		if (tabId <= 0)
			return null;
		String key = "TabField|" + tabId;
		Map<String, MADContextInfo> contextInfoHash = contextInfoCacheFielsIds.get(key);
		if (contextInfoHash != null && contextInfoHash.size() > 0)
			return contextInfoHash;
		//	
		List<MField> fieldList = null;
		//	
		try {
			fieldList = new Query(ctx , I_AD_Field.Table_Name , "AD_Tab_ID = ? "
					+ "AND AD_ContextInfo_ID IS NOT NULL" , null)
					.setParameters(tabId)
					.<MField>list();
		} catch(Exception e) {
			log.severe("getFromTabIdForField: " + e.getLocalizedMessage());
		}
		//	Clear
		if(fieldList != null
				&& fieldList.size() > 0) {
			contextInfoHash = new HashMap<String, MADContextInfo>();
			//	Get
			for(MField field : fieldList) {
				contextInfoHash.put(field.getAD_Column().getColumnName(), MADContextInfo.getById(field.getCtx(), field.get_ValueAsInt("AD_ContextInfo_ID")));
			}
			if (contextInfoHash != null && contextInfoHash.size() > 0) {
				contextInfoCacheFielsIds.put(key, contextInfoHash);
			}
		}
		//	
		return contextInfoHash;
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
					if(!(value instanceof BigDecimal)
							&& !(value instanceof Double)
							&& !(value instanceof Float)
							&& !(value instanceof Integer)
							&& !(value instanceof String)
							&& !(value instanceof Timestamp)) {
						continue;
					}
					//	
					if(value instanceof BigDecimal) {
						value = ((BigDecimal) value).doubleValue();
					} else if(value instanceof Timestamp) {
						value = new Long(((Timestamp) value).getTime());
					} else if(value instanceof Number) {
						value = Integer.valueOf(((Number) value).intValue());
					} else {
						value = String.valueOf(value);
					}
					//	Set value
					arguments[column] = value;
				}
				//	Return
				return arguments;
			}
		} catch (SQLException e) {
			log.log(Level.WARNING, "\nSQL=" + sql, e);
		} finally {
			DB.close(rs, pstmt);
		}
		return null;
	}

	@Override
	public String toString() {
		return "MADContextInfo [getAD_ContextInfo_ID()=" + getAD_ContextInfo_ID() + ", getName()=" + getName()
				+ ", getValue()=" + getValue() + "]";
	}
	
}
