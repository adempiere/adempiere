/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz  (globalqss@users.sourceforge.net)                    *
*                                                                     *
* Sponsors:                                                           *
* - GlobalQSS (http://www.globalqss.com)                              *
***********************************************************************/

package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Web Services Type Model
 *	
 *  @author Carlos Ruiz
 */
public class MWebServiceType extends X_WS_WebServiceType
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 134887822892217528L;

	/**	Parameters	*/
	private X_WS_WebService_Para[]	m_para = null;
	
	/**	Allowed input columns	*/
	private String[]	m_inputcolumnnames = null;
	/**	Allowed output columns	*/
	private String[]	m_outputcolumnnames = null;
	
	
	/**
	 * 	Get Parameters
	 *	@param requery requery
	 *	@return array of methods
	 */
	public X_WS_WebService_Para[] getParameters (boolean requery)
	{
		if (m_para != null && !requery)
			return m_para;
		String sql = "SELECT * FROM WS_WebService_Para WHERE WS_WebServiceType_ID=? AND IsActive='Y' ORDER BY ParameterName";
		ArrayList<X_WS_WebService_Para> list = new ArrayList<X_WS_WebService_Para>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getWS_WebServiceType_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new X_WS_WebService_Para (getCtx(), rs, get_TrxName()));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		m_para = new X_WS_WebService_Para[list.size ()];
		list.toArray (m_para);
		return m_para;
	}	//	getParameters

	/**
	 * 	Get Parameter
	 *	@param parameterName
	 *	@return parameter if found
	 */
	public X_WS_WebService_Para getParameter (String parameterName)
	{
		if (parameterName == null || parameterName.length() == 0)
			return null;
		getParameters(false);
		//
		for (int i = 0; i < m_para.length; i++)
		{
			if (parameterName.equals(m_para[i].getParameterName()))
				return m_para[i];
		}
		return null;
	}	//	getParameter
	
	/**
	 * 	Get Input Columnnames
	 *	@param requery requery
	 *	@return array of methods
	 */
	public String[] getInputColumnNames (boolean requery)
	{
		if (m_inputcolumnnames != null && !requery)
			return m_inputcolumnnames;
		String sql = "SELECT c.ColumnName FROM WS_WebServiceFieldInput f, AD_Column c " +
				"WHERE f.WS_WebServiceType_ID=? " +
				"AND c.AD_Column_ID=f.AD_Column_ID " +
				"AND c.IsActive='Y' " +
				"AND f.IsActive='Y' " +
				"ORDER BY c.ColumnName";
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getWS_WebServiceType_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (rs.getString(1));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		m_inputcolumnnames = new String[list.size ()];
		list.toArray (m_inputcolumnnames);
		return m_inputcolumnnames;
	}	//	getInputColumnNames

	/**
	 * 	Is Input Column Name Allowed
	 *	@param columnName
	 *	@return true if allowed
	 */
	public boolean isInputColumnNameAllowed (String columnName)
	{
		if (columnName == null || columnName.length() == 0)
			return false;
		getInputColumnNames(false);
		//
		for (int i = 0; i < m_inputcolumnnames.length; i++)
		{
			if (columnName.equals(m_inputcolumnnames[i]))
				return true;
		}
		return false;
	}	//	isInputColumnNameAllowed
	
	/**
	 * 	Get Output Columnnames
	 *	@param requery requery
	 *	@return array of methods
	 */
	public String[] getOutputColumnNames (boolean requery)
	{
		if (m_outputcolumnnames != null && !requery)
			return m_outputcolumnnames;
		String sql = "SELECT c.ColumnName FROM WS_WebServiceFieldOutput f, AD_Column c " +
				"WHERE f.WS_WebServiceType_ID=? " +
				"AND c.AD_Column_ID=f.AD_Column_ID " +
				"AND c.IsActive='Y' " +
				"AND f.IsActive='Y' " +
				"ORDER BY c.ColumnName";
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getWS_WebServiceType_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (rs.getString(1));
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		m_outputcolumnnames = new String[list.size ()];
		list.toArray (m_outputcolumnnames);
		return m_outputcolumnnames;
	}	//	getOutputColumnNames

	/**
	 * 	Is Output Column Name Allowed
	 *	@param columnName
	 *	@return true if allowed
	 */
	public boolean isOutputColumnNameAllowed (String columnName)
	{
		if (columnName == null || columnName.length() == 0)
			return false;
		getOutputColumnNames(false);
		//
		for (int i = 0; i < m_outputcolumnnames.length; i++)
		{
			if (columnName.equals(m_outputcolumnnames[i]))
				return true;
		}
		return false;
	}	//	isOutputColumnNameAllowed
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MWebServiceType.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param WS_WebServiceType_ID
	 *	@param trxName transaction
	 */
	public MWebServiceType (Properties ctx, int WS_WebServiceType_ID, String trxName)
	{
		super (ctx, WS_WebServiceType_ID, trxName);
        /** if (WS_WebServiceType_ID == 0)
        {
			setName (null);
			setValue (null);
			setWS_WebService_ID (0);
			setWS_WebServiceMethod_ID (0);
			setWS_WebServiceType_ID (0);
        } */
	}	//	MWebServiceType

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MWebServiceType (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MWebServiceType
	
}	//	MWebServiceType
