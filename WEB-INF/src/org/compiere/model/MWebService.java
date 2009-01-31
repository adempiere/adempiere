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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.CCache;
import org.compiere.util.CLogger;

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
