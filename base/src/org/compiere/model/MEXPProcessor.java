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
 *                                                                    *
 * Sponsors:                                                          *
 *  - e-Evolution (http://www.e-evolution.com/)                       *
 *********************************************************************/

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author Trifon N. Trifonov
 */
public class MEXPProcessor extends X_EXP_Processor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2925330684523283775L;
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MEXPProcessor.class);
	
	private static MEXPProcessor processor= null;
	private X_EXP_ProcessorParameter[] parameters = null;
	
	public static MEXPProcessor get(Properties ctx, int EXP_Processor_ID, String trxName)
	{
	    if(processor == null)
	    {
		processor = new MEXPProcessor(ctx, EXP_Processor_ID, trxName);
	    }
	    return processor;
	}
	
	public MEXPProcessor(Properties ctx, int EXP_Processor_ID, String trxName) {
		super(ctx, EXP_Processor_ID, trxName);
	}
	
	public MEXPProcessor(Properties ctx, ResultSet rs, String trxName) {
		super (ctx, rs, trxName);
	}
	
	public X_EXP_ProcessorParameter[] getEXP_ProcessorParameters(String trxName) {
	    
	    if(parameters != null)
		return parameters;
	    
		List<X_EXP_ProcessorParameter> resultList = new ArrayList<X_EXP_ProcessorParameter>();
		                   
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(X_EXP_ProcessorParameter.Table_Name)
			.append(" WHERE ").append(X_EXP_ProcessorParameter.COLUMNNAME_EXP_Processor_ID).append("=?") // # 1
			.append(" AND IsActive = ?")  // # 2
			//.append(" ORDER BY ").append(X_EXP_ProcessorParameter.COLUMNNAME_)
		;
		PreparedStatement pstmt = null;
		X_EXP_ProcessorParameter processorParameter = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt(1, getEXP_Processor_ID());
			pstmt.setString(2, "Y");
			ResultSet rs = pstmt.executeQuery ();
			while ( rs.next() ) {
				processorParameter = new X_EXP_ProcessorParameter (getCtx(), rs, trxName);
				resultList.add(processorParameter);
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
		X_EXP_ProcessorParameter[] result = (X_EXP_ProcessorParameter[])resultList.toArray( new X_EXP_ProcessorParameter[0]);
		parameters = result;
		return result;
	}

	
}
