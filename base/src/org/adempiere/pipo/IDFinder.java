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
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * Utility class for the looking up of record id.
 * @author Low Heng Sin
 *
 */
public class IDFinder {

	private static CLogger log = CLogger.getCLogger(IDFinder.class);
	
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
		StringBuffer sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where name=?");
		
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
		}
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
		StringBuffer sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where UPPER("+columnName+")=?");
		//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+value.toString());
		
		if (!tableName.startsWith("AD_"))
			sqlB = sqlB.append(" and AD_Client_ID=?");
		//here!
		sqlB = sqlB.append(" Order By "+tableName+"_ID");
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			if (value instanceof String)
				pstmt.setString(1, ((String)value).toUpperCase());
			else if (value instanceof Integer)
				pstmt.setInt(1, ((Integer)value).intValue());
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
		}
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
		StringBuffer sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where UPPER(name)=? and "
				+ tableNameMaster+"_ID = (select "+tableNameMaster+"_ID from "+tableNameMaster+" where UPPER(name)=?)");
		
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			pstmt.setString(1, name.toUpperCase());
			pstmt.setString(2, nameMaster.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.info ("get_IDWithMaster:"+e);
		}
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
		StringBuffer sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where UPPER("+columnName+")=? and "
				+ tableNameMaster+"_ID =?");
		//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+name+" and "
		//	    + tableNameMaster+"_ID ="+masterID);
		log.info(sqlB.toString());
		
		try {
			
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), trxName);
			pstmt.setString(1, name.toUpperCase());
			pstmt.setInt(2, masterID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			log.info ("get_IDWithMasterAndColumn:"+e);
		}
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
		StringBuffer sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where name=? and "
				+ tableNameMaster+"_ID=?");
		
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
		}
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
		String sql = "SELECT "+tableName+"_ID "
		+ "FROM "+tableName+" "
		+ "WHERE name=?";
		if (!tableName.startsWith("AD_"))
			sql = sql + " AND AD_Client_ID=?";
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
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
			log.log(Level.SEVERE, "getID:"+e);
		}
		return id;
	}
}
