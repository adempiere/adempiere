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
 *********************************************************************/

package org.compiere.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;


/**
 * @author Trifon N. Trifonov
 * @author Antonio Cañaveral, e-Evolution
 * 				<li>[ 2195090 ] Implementing ExportFormat cache
 * 				<li>http://sourceforge.net/tracker/index.php?func=detail&aid=2195090&group_id=176962&atid=879335
 * @author victor.perez@e-evolution.com, e-Evolution
 * 				<li>[ 2195090 ] Stabilization of replication
 * 				<li>https://sourceforge.net/tracker/?func=detail&atid=879332&aid=2936561&group_id=176962
 *
 */
public class MEXPFormat extends X_EXP_Format {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5011042965945626099L;

	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MEXPFormat.class);
	
	private static CCache<String,MEXPFormat> s_cache = new CCache<String,MEXPFormat>(MEXPFormat.Table_Name, 50 );
	private static CCache<Integer,MEXPFormat> exp_format_by_id_cache 	= new CCache<Integer,MEXPFormat>(MEXPFormat.Table_Name, 50);

	private Collection<MEXPFormatLine> m_lines = null;
	private Collection<MEXPFormatLine> m_lines_unique = null;
	
	public MEXPFormat(Properties ctx, int EXP_Format_ID, String trxName) 
	{	
		super(ctx, EXP_Format_ID, trxName);
	}
	
	public MEXPFormat(Properties ctx, ResultSet rs, String trxName) {
		super (ctx, rs, trxName);
	}
	
	public Collection<MEXPFormatLine> getFormatLines() {
		return getFormatLinesOrderedBy(X_EXP_FormatLine.COLUMNNAME_Position);
	}
	
	public Collection<MEXPFormatLine> getFormatLinesOrderedBy(String orderBy) 
	{
		if(m_lines != null)
		{
			return m_lines;
		}
		
		final String clauseWhere = X_EXP_FormatLine.COLUMNNAME_EXP_Format_ID + "=?";	
		m_lines = new Query(getCtx() , I_EXP_FormatLine.Table_Name, clauseWhere , get_TrxName())
						.setOnlyActiveRecords(true)
						.setParameters(new Object[]{getEXP_Format_ID()})
						.setOrderBy(orderBy)
						.list();
		return m_lines;
	}

	public Collection<MEXPFormatLine> getUniqueColumns() throws SQLException {	
		
		if (m_lines_unique != null)
			return m_lines_unique;
		
		final String clauseWhere = X_EXP_FormatLine.COLUMNNAME_EXP_Format_ID+"= ?"
								 + " AND " + X_EXP_FormatLine.COLUMNNAME_IsPartUniqueIndex +"= ?";
		m_lines_unique = new Query(getCtx(), I_EXP_FormatLine.Table_Name, clauseWhere, get_TrxName())
													 .setOnlyActiveRecords(true)
													 .setParameters(new Object[]{getEXP_Format_ID(), "Y"})
													 .setOrderBy(X_EXP_FormatLine.COLUMNNAME_Position)
													 .list();
		return m_lines_unique;
	}
	
	public static MEXPFormat get(Properties ctx, int EXP_Format_ID, String trxName) 
	{
		MEXPFormat exp_format = exp_format_by_id_cache.get(EXP_Format_ID);
		if(exp_format != null)
			return exp_format;
		exp_format = new MEXPFormat(ctx, EXP_Format_ID , trxName);
		if(exp_format!=null)
		{	
		exp_format.getFormatLines();
		exp_format_by_id_cache.put(EXP_Format_ID, exp_format);
		}
		return exp_format;
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
		
		if(retValue != null)
		{	
		retValue.getFormatLines();
		s_cache.put (key, retValue);
		exp_format_by_id_cache.put(retValue.getEXP_Format_ID(), retValue);
		}
		
		return retValue;
	}

	public static MEXPFormat getFormatByAD_Client_IDAD_Table_IDAndVersion(Properties ctx, int AD_Client_ID, int AD_Table_ID, String version, String trxName) throws SQLException 
	{
		String key = new String(MTable.getTableName(ctx, AD_Table_ID)+version);
		MEXPFormat retValue=null;
		
		retValue = (MEXPFormat)s_cache.get(key);
		if(retValue!=null)
			return retValue;
		
		StringBuffer whereClause = new StringBuffer(" AD_Client_ID = ? ")
			.append("  AND ").append(X_EXP_Format.COLUMNNAME_AD_Table_ID).append(" = ? ")
			.append("  AND ").append(X_EXP_Format.COLUMNNAME_Version).append(" = ?");

		retValue = (MEXPFormat) new Query(ctx,X_EXP_Format.Table_Name,whereClause.toString(),trxName)
						.setParameters(new Object[] {AD_Client_ID,AD_Table_ID,version})
						.first();
		if(retValue!=null)
		{	
		retValue.getFormatLines();
		s_cache.put (key, retValue);
		exp_format_by_id_cache.put(retValue.getEXP_Format_ID(), retValue);
		}
		
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
