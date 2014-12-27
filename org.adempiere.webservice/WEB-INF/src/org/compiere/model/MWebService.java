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
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Web Services Model
 *	
 *  @author Carlos Ruiz
 */
public class MWebService extends X_WS_WebService
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3561409141850981248L;

	/**
	 * 	Get MWebService from Cache
	 *	@param ctx context
	 * 	@param WS_WebService_ID id
	 *	@return MWebService
	 */
	public static MWebService get (Properties ctx, int WS_WebService_ID)
	{
		Integer key = new Integer (WS_WebService_ID);
		MWebService retValue = (MWebService) s_cache.get (key);
		if (retValue != null)
			return retValue;
		retValue = new MWebService (ctx, WS_WebService_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (key, retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get WebService from Cache
	 *	@param ctx context
	 *	@param webServiceValue
	 *	@return Table
	 */
	public static MWebService get (Properties ctx, String webServiceValue)
	{
		if (webServiceValue == null)
			return null;
		Iterator<MWebService> it = s_cache.values().iterator();
		while (it.hasNext())
		{
			MWebService retValue = it.next();
			if (webServiceValue.equals(retValue.getValue())) 
				return retValue;
		}
		//
		MWebService retValue = null;
		String sql = "SELECT * FROM WS_WebService WHERE Value=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setString(1, webServiceValue);
			rs = pstmt.executeQuery ();
			if (rs.next ())
				retValue = new MWebService (ctx, rs, null);
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		if (retValue != null)
		{
			Integer key = new Integer (retValue.getWS_WebService_ID());
			s_cache.put (key, retValue);
		}
		return retValue;
	}	//	get

	/**	Methods				*/
	private X_WS_WebServiceMethod[]	m_methods = null;
	
	/**
	 * 	Get Methods
	 *	@param requery requery
	 *	@return array of methods
	 */
	public X_WS_WebServiceMethod[] getMethods (boolean requery)
	{
		if (m_methods != null && !requery)
			return m_methods;
		String sql = "SELECT * FROM WS_WebServiceMethod WHERE WS_WebService_ID=? AND IsActive='Y' ORDER BY Value";
		ArrayList<X_WS_WebServiceMethod> list = new ArrayList<X_WS_WebServiceMethod>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getWS_WebService_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add (new X_WS_WebServiceMethod (getCtx(), rs, get_TrxName()));
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
		m_methods = new X_WS_WebServiceMethod[list.size ()];
		list.toArray (m_methods);
		return m_methods;
	}	//	getMethods

	/**
	 * 	Get Method
	 *	@param methodValue
	 *	@return method if found
	 */
	public X_WS_WebServiceMethod getMethod (String methodValue)
	{
		if (methodValue == null || methodValue.length() == 0)
			return null;
		getMethods(false);
		//
		for (int i = 0; i < m_methods.length; i++)
		{
			if (methodValue.equals(m_methods[i].getValue()))
				return m_methods[i];
		}
		return null;
	}	//	getMethod
	
	/**	Cache						*/
	private static CCache<Integer,MWebService>	s_cache	= new CCache<Integer,MWebService>("WS_WebService", 20);
	
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MWebService.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param WS_WebService_ID
	 *	@param trxName transaction
	 */
	public MWebService (Properties ctx, int WS_WebService_ID, String trxName)
	{
		super (ctx, WS_WebService_ID, trxName);
        /** if (WS_WebService_ID == 0)
        {
			setName (null);
			setValue (null);
			setWS_WebService_ID (0);
        } */
	}	//	MWebService

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MWebService (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MWebService
	
}	//	MWebService
