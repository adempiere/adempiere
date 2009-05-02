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
 *  - E-evolution (http://www.e-evolution.com/)                       *
 **********************************************************************/
package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 * @author Trifon Trifonov
 */
public class MIMPProcessor
	extends X_IMP_Processor
	implements AdempiereProcessor 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8634765494025824138L;
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MIMPProcessor.class);
	
	public MIMPProcessor(Properties ctx,
			int EXP_ReplicationProcessor_ID, String trxName) 
	{
		super(ctx, EXP_ReplicationProcessor_ID, trxName);
		if (EXP_ReplicationProcessor_ID == 0)
		{
			//setValue (/*client.getName() + " - " +*/ "Default Import Processor");
			setName (/*client.getName() + " - " +*/ "Default Import Processor");
			setFrequencyType (FREQUENCYTYPE_Hour);
			setFrequency (1);
			setKeepLogDays (7);
		}	
	}
	
	public MIMPProcessor(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	}
	
	/**
	 * 
	 */
	public Timestamp getDateNextRun (boolean requery)
	{
		if (requery)
			load(get_TrxName());
		return getDateNextRun();
	}


	/**
	 * 	Get Logs
	 *	@return logs
	 */
	public AdempiereProcessorLog[] getLogs ()
	{
		ArrayList<MIMPProcessorLog> list = new ArrayList<MIMPProcessorLog>();
		String sql = "SELECT * "
			+ "FROM " + X_IMP_ProcessorLog.Table_Name + " "
			+ "WHERE " + X_IMP_Processor.COLUMNNAME_IMP_Processor_ID + "=? " // # 1 
			+ "ORDER BY Created DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getIMP_Processor_ID());
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MIMPProcessorLog (getCtx(), rs, get_TrxName()));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MIMPProcessorLog[] retValue = new MIMPProcessorLog[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getLogs

	/**
	 * 	Delete old Request Log
	 *	@return number of records
	 */
	public int deleteLog()
	{
		if (getKeepLogDays() < 1)
			return 0;
		String sql = "DELETE " + X_IMP_ProcessorLog.Table_Name + " "
			+ "WHERE "+X_IMP_ProcessorLog.COLUMNNAME_IMP_Processor_ID+"=" + getIMP_Processor_ID() 
			+ " AND (Created+" + getKeepLogDays() + ") < SysDate";
		int no = DB.executeUpdate(sql, get_TrxName());
		return no;
	}

	public String getServerID() {
		return "ReplicationProcessor" + get_ID();
	}
	
	public X_IMP_ProcessorParameter[] getIMP_ProcessorParameters(String trxName) {
		List<X_IMP_ProcessorParameter> resultList = new ArrayList<X_IMP_ProcessorParameter>();
		                   
		StringBuffer sql = new StringBuffer("SELECT * ")
			.append(" FROM ").append(X_IMP_ProcessorParameter.Table_Name)
			.append(" WHERE ").append(X_IMP_ProcessorParameter.COLUMNNAME_IMP_Processor_ID).append("=?") // # 1
			.append(" AND IsActive = ?")  // # 2
			//.append(" ORDER BY ").append(X_EXP_ProcessorParameter.COLUMNNAME_)
		;
		PreparedStatement pstmt = null;
		X_IMP_ProcessorParameter processorParameter = null;
		try {
			pstmt = DB.prepareStatement (sql.toString(), trxName);
			pstmt.setInt(1, getIMP_Processor_ID());
			pstmt.setString(2, "Y");
			ResultSet rs = pstmt.executeQuery ();
			while ( rs.next() ) {
				processorParameter = new X_IMP_ProcessorParameter (getCtx(), rs, trxName);
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
		X_IMP_ProcessorParameter[] result = (X_IMP_ProcessorParameter[])resultList.toArray( new X_IMP_ProcessorParameter[0]);
		return result;
	}
	
	public static MIMPProcessor[] getActive(Properties ctx)
	{
		ArrayList<MIMPProcessor> list = new ArrayList<MIMPProcessor>();
		String sql = "SELECT * FROM "+X_IMP_Processor.Table_Name+" WHERE IsActive='Y'";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new MIMPProcessor (ctx, rs, null));
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		
		MIMPProcessor[] retValue = new MIMPProcessor[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getActive
	
}
