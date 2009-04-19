/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          * 
 * http://www.adempiere.org                                           * 
 *                                                                    * 
 * Copyright (C) Trifon Trifonov.                                     * 
 * Copyright (C) Contributors                                         * 
 *                                                                    * 
 * This program is free software; you can redistribute it and/or      * 
 * modify it under the terms of the GNU General Public License        * 
 * as published by the Free Software Foundation; either version 2     * 
 * of the License, or (at your option) any later version.             * 
 *                                                                    * 
 * This program is distributed in the hope that it will be useful,    * 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of     * 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       * 
 * GNU General Public License for more details.                       * 
 *                                                                    * 
 * You should have received a copy of the GNU General Public License  * 
 * along with this program; if not, write to the Free Software        * 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         * 
 * MA 02110-1301, USA.                                                * 
 *                                                                    * 
 * Contributors:                                                      * 
 *  - Trifon Trifonov (trifonnt@users.sourceforge.net)                *
 *  - Antonio Cañaveral, e-Evolution
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author Trifon N. Trifonov
 * @author Antonio Cañaveral, e-Evolution
 * 				<li>[ 2195090 ] Implementing ExportFormat cache
 * 				<li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195090&group_id=176962&atid=879335
 */
public class MEXPFormat extends X_EXP_Format {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MEXPFormat.class);
	
	private static CCache<String,MEXPFormat> s_cache = new CCache<String,MEXPFormat>("MEXPFormat", 50 );	
	
	public MEXPFormat(Properties ctx, int EXP_Format_ID, String trxName) {
		super(ctx, EXP_Format_ID, trxName);
	}
	
	public MEXPFormat(Properties ctx, ResultSet rs, String trxName) {
		super (ctx, rs, trxName);
	}
	
	public MEXPFormatLine[] getFormatLines() {
		return getFormatLinesOrderedBy(X_EXP_FormatLine.COLUMNNAME_Position);
	}
	
	public MEXPFormatLine[] getFormatLinesOrderedBy(String orderBy) {
		List<MEXPFormatLine> resultList = new ArrayList<MEXPFormatLine>();
		                   
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(X_EXP_FormatLine.Table_Name)
			.append(" WHERE ").append(X_EXP_FormatLine.COLUMNNAME_EXP_Format_ID).append("=?")
			.append(" AND IsActive = ?")
			.append(" ORDER BY ").append(orderBy);
		PreparedStatement pstmt = null;
		MEXPFormatLine exportLine = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXP_Format_ID());
			pstmt.setString(2, "Y");
			ResultSet rs = pstmt.executeQuery ();
			while ( rs.next() ) {
				exportLine = new MEXPFormatLine (getCtx(), rs, get_TrxName());
				resultList.add(exportLine);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			try	{
				if (pstmt != null) pstmt.close ();
				pstmt = null;
			} catch (Exception e) {	pstmt = null; }
		}

		MEXPFormatLine[] result = (MEXPFormatLine[])resultList.toArray( new MEXPFormatLine[0]);
		return result;
	}

	public MEXPFormatLine[] getUniqueColumns() throws SQLException {
		List<MEXPFormatLine> resultList = new ArrayList<MEXPFormatLine>();
		                   
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(X_EXP_FormatLine.Table_Name)
			.append(" WHERE ").append(X_EXP_FormatLine.COLUMNNAME_EXP_Format_ID).append("= ?")
			.append(" AND IsActive = ?")
			.append(" AND ").append(X_EXP_FormatLine.COLUMNNAME_IsPartUniqueIndex).append("= ?")
			.append(" ORDER BY ").append(X_EXP_FormatLine.COLUMNNAME_Position);
		PreparedStatement pstmt = null;
		MEXPFormatLine exportLine = null;
		log.info(sql.toString());
		
		log.info("pstmt.setInt(1, getEXP_Format_ID() = " + getEXP_Format_ID());
		try {
			pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			pstmt.setInt(1, getEXP_Format_ID());
			pstmt.setString(2, "Y");
			pstmt.setString(3, "Y");
			ResultSet rs = pstmt.executeQuery ();
			while ( rs.next() ) {
				exportLine = new MEXPFormatLine (getCtx(), rs, get_TrxName());
				resultList.add(exportLine);
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} catch (SQLException e) {
			s_log.log(Level.SEVERE, sql.toString(), e);
			throw e;
		} finally {
			try	{
				if (pstmt != null) pstmt.close ();
				pstmt = null;
			} catch (Exception e) {	pstmt = null; }
		}

		MEXPFormatLine[] result = (MEXPFormatLine[])resultList.toArray( new MEXPFormatLine[0]);
		return result;
	}

	
	public static MEXPFormat getFormatByValueAD_Client_IDAndVersion(Properties ctx, String value, int AD_Client_ID, String version, String trxName) 
			throws SQLException 
	{
		String key = new String(value+version);
		MEXPFormat retValue=null;
		if(retValue!=null)
			return retValue;
		
		StringBuffer whereCluse = new StringBuffer(X_EXP_Format.COLUMNNAME_Value).append("=?")
		.append(" AND AD_Client_ID = ?")
		.append(" AND ").append(X_EXP_Format.COLUMNNAME_Version).append(" = ?");

		retValue = (MEXPFormat) new Query(ctx,X_EXP_Format.Table_Name,whereCluse.toString(),trxName)
					.setParameters(new Object[] {value,AD_Client_ID,version}).first();
		s_cache.put (key, retValue);
		
		return retValue;
	}

	public static MEXPFormat getFormatByAD_Client_IDAD_Table_IDAndVersion(Properties ctx, int AD_Client_ID, int AD_Table_ID, String version, String trxName) throws SQLException 
	{
		String key = new String(MTable.getTableName(ctx, AD_Table_ID)+version);
		MEXPFormat retValue=null;
		
		retValue = (MEXPFormat)s_cache.get(key);
		if(retValue!=null)
			return retValue;
		
		StringBuffer whereCluse = new StringBuffer(" AD_Client_ID = ? ")
			.append("  AND ").append(X_EXP_Format.COLUMNNAME_AD_Table_ID).append(" = ? ")
			.append("  AND ").append(X_EXP_Format.COLUMNNAME_Version).append(" = ?");

		retValue = (MEXPFormat) new Query(ctx,X_EXP_Format.Table_Name,whereCluse.toString(),trxName)
						.setParameters(new Object[] {AD_Client_ID,AD_Table_ID,version}).first();
		
		s_cache.put (key, retValue);
		
		return retValue;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer ("X_EXP_Format[ID=").append(get_ID()).append("; Value = "+getValue()+"]");
		return sb.toString();

	}
	
	/**
	 * 	Before Delete
	 *	@return true of it can be deleted
	 */
	protected boolean beforeDelete ()
	{
		int[] ids =MEXPFormatLine.getAllIDs(MEXPFormatLine.Table_Name, "EXP_Format_ID="+getEXP_Format_ID(), get_TrxName());
		for (int id : ids)
		{
			MEXPFormatLine line = new MEXPFormatLine(getCtx(), id, get_TrxName());
			line.delete(true);
		}
		return true;
	}	//	beforeDelete
}
