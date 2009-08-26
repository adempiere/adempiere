/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *
 * Copyright (C) 2005 Robert Klein. robeklein@hotmail.com
 * Contributor(s): Low Heng Sin hengsin@avantz.com
 *****************************************************************************/
package org.adempiere.pipo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.pipo.exception.DatabaseAccessException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Utility class for the looking up of record id.
 * @author Low Heng Sin
 *
 */
public class IDFinder {

	private static CLogger log = CLogger.getCLogger(IDFinder.class);
	
	private static Map<String, Integer>idCache = new HashMap<String, Integer>(); 
	
	/**
	 * Get ID from Name for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 * @param AD_Client_ID
	 * @param trxName
	 * 
	 */
	public static int get_ID (String tableName, String name, int AD_Client_ID, String trxName) {
		int id = 0;
		
		//construct cache key
		StringBuffer key = new StringBuffer();
		key.append(tableName)
			.append(".Name=")
			.append(name);
		if (!tableName.startsWith("AD_"))
			key.append(" and AD_Client_ID=").append(AD_Client_ID);
		
		//check cache
		if (idCache.containsKey(key.toString()))
			return idCache.get(key.toString());
		
		StringBuffer sqlB = new StringBuffer ("select ")
			.append(tableName)
			.append("_ID from ")
			.append(tableName)
			.append(" where name=?");
		
		if (!tableName.startsWith("AD_"))
			sqlB = sqlB.append(" and AD_Client_ID=?");
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			pstmt.setString(1, name);
			if (!tableName.startsWith("AD_"))
				pstmt.setInt(2, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.info ("get_ID:"+e);
			throw new DatabaseAccessException(e);
		}
		
		//keep in cache
		if (id > 0)
			idCache.put(key.toString(), id);
		
		return id;
	}
	
	/**
	 * Get ID from column value for a table.
	 *
	 * @param tableName
	 * @param columName
	 * @param value
	 * @param AD_Client_ID
	 * @param trxName
	 */
	public static int get_IDWithColumn (String tableName, String columnName, Object value, int AD_Client_ID, String trxName) {
		int id = 0;
		
		if (value == null)
			return id;
		
		//construct cache key
		StringBuffer key = new StringBuffer();
		key.append(tableName)
			.append(".")
			.append(columnName)
			.append("=")
			.append(value.toString());
		if (!tableName.startsWith("AD_"))
			key.append(" and AD_Client_ID=").append(AD_Client_ID);
		
		//check cache
		if (idCache.containsKey(key.toString()))
			return idCache.get(key.toString());
		
		StringBuffer sqlB = new StringBuffer ("select ")
		 	.append(tableName)
		 	.append("_ID from ")
		 	.append(tableName)
		 	.append(" where ")
		 	.append(columnName)
		 	.append(" = ?");
		
		if (!tableName.startsWith("AD_"))
			sqlB = sqlB.append(" and AD_Client_ID=?");
		//here!
		sqlB = sqlB.append(" Order By ")
				.append(tableName)
				.append("_ID");
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			if (value instanceof String)
				pstmt.setString(1, (String)value);
			else if (value instanceof Integer)
				pstmt.setInt(1, ((Integer)value).intValue());
			else
				pstmt.setObject(1, value);
			if (!tableName.startsWith("AD_"))
				pstmt.setInt(2, AD_Client_ID);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.info ("get_IDWithColumn:"+e);
			throw new DatabaseAccessException(e);
		}
		
		//update cache
		if (id > 0)
			idCache.put(key.toString(), id);
		
		return id;
	}
	
	/**
	 * Get ID from Name for a table with a Master reference.
	 *
	 * @param tableName
	 * @param name
	 * @param tableNameMaster
	 * @param nameMaster
	 * @param trxName
	 */
	public static int get_IDWithMaster (String tableName, String name, String tableNameMaster, String nameMaster, String trxName) {
		int id = 0;
		//construct cache key
		StringBuffer key = new StringBuffer();
		key.append(tableName)
			.append(".Name=")
			.append(name)
			.append(" and ")
			.append(tableNameMaster)
			.append(".Name=")
			.append(nameMaster);
		
		//check cache
		if (idCache.containsKey(key.toString()))
			return idCache.get(key.toString());
		
		StringBuffer sqlB = new StringBuffer ("select ")
			.append(tableName)
			.append("_ID from ")
			.append(tableName)
			.append(" where name = ? and ")
			.append(tableNameMaster)
			.append("_ID = (select ")
			.append(tableNameMaster)
			.append("_ID from ")
			.append(tableNameMaster)
			.append(" where name = ?)");
		
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			pstmt.setString(1, name);
			pstmt.setString(2, nameMaster);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		} catch (Exception e) {
			log.info ("get_IDWithMaster:"+e);
			throw new DatabaseAccessException(e);
		}
		
		//update cache
		if (id > 0)
			idCache.put(key.toString(), id);
		
		return id;
	}
	
	/**
     * Get ID from Name for a table with a Master reference.
     *
     * @param tableName
     * @param name
     * @param tableNameMaster
     * @param masterID
     * @param trxName
     */    
    
	public static int get_IDWithMasterAndColumn (String tableName, String columnName, String name, String tableNameMaster, int masterID, String trxName) {
		int id = 0;
		
		//check cache
		String key = tableName + "." + columnName + "=" + name + tableNameMaster + "=" + masterID;
		
		if (idCache.containsKey(key))
			return idCache.get(key);
		
		StringBuffer sqlB = new StringBuffer ("select ")
			.append(tableName)
			.append("_ID from ")
			.append(tableName)
			.append(" where ")
			.append(columnName)
			.append(" = ? and ")
			.append(tableNameMaster+"_ID =?");
		//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+name+" and "
		//	    + tableNameMaster+"_ID ="+masterID);
		log.info(sqlB.toString());
		
		try {
			
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			pstmt.setString(1, name);
			pstmt.setInt(2, masterID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				id = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.info ("get_IDWithMasterAndColumn:"+e);
			throw new DatabaseAccessException(e);
		}
		
		//update cache
		if (id > 0)
			idCache.put(key, id);
		
		return id;
	}

	/**
	 * Get ID from Name for a table with a Master reference ID.
	 *
	 * @param tableName
	 * @param name
	 * @param tableNameMaster
	 * @param masterID
	 * @param trxName
	 */    
	public static int get_IDWithMaster (String tableName, String name, String tableNameMaster, int masterID, String trxName) {
		int id = 0;
		
		//construct cache key
		StringBuffer key = new StringBuffer();
		key.append(tableName)
			.append(".Name=")
			.append(name)
			.append(" and ")
			.append(tableNameMaster)
			.append(".")
			.append(tableNameMaster)
			.append("_ID=")
			.append(masterID);
		
		//check cache
		if (idCache.containsKey(key.toString()))
			return idCache.get(key.toString());
		
		StringBuffer sqlB = new StringBuffer ("select ")
			.append(tableName)
			.append("_ID from ")
			.append(tableName)
			.append(" where name=? and ")
			.append(tableNameMaster)
			.append("_ID=?");
		
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			pstmt.setString(1, name);
			pstmt.setInt(2, masterID);	
			ResultSet rs = pstmt.executeQuery();	    
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.info ("get_IDWithMasterID:"+e);
			throw new DatabaseAccessException(e);
		}
		
		//update cache
		if (id > 0)
			idCache.put(key.toString(), id);
		
		return id;
	}

	/**
	 * Get ID from Name for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 * @param AD_Client_ID
	 * @param trxName
	 */
	public static int getIDbyName (String tableName, String name, int AD_Client_ID, String trxName) {
		int id = 0;
		
		//construct cache key
		StringBuffer key = new StringBuffer();
		key.append(tableName)
			.append(".Name=")
			.append(name);
		if (!tableName.startsWith("AD_"))
			key.append(" AND AD_Client_ID=").append(AD_Client_ID);
		
		//check cache
		if (idCache.containsKey(key.toString()))
			return idCache.get(key.toString());
		
		StringBuffer sql = new StringBuffer("SELECT ")
			.append(tableName)
			.append("_ID ")
			.append("FROM ")
			.append(tableName)
			.append(" ")
			.append("WHERE name=?");
		if (!tableName.startsWith("AD_"))
			sql.append(" AND AD_Client_ID=?");
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setString(1, name);
			if (!tableName.startsWith("AD_"))
				pstmt.setInt(2, AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "getIDbyName:"+e);
			throw new DatabaseAccessException(e);
		}
		
		//update cache
		if (id > 0)
			idCache.put(key.toString(), id);
		
		return id;
	}
	
	public static void clearIDCache() {
		idCache.clear();
	}
	
}
