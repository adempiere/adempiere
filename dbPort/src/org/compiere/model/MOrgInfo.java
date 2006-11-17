/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.sql.*;
import java.util.*;

import java.util.logging.*;
import org.compiere.util.*;

/**
 *	Organization Info Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MOrgInfo.java,v 1.3 2006/07/30 00:58:37 jjanke Exp $
 */
public class MOrgInfo extends X_AD_OrgInfo
{	
	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param AD_Org_ID id
	 *	@return Org Info
	 */
	public static MOrgInfo get (Properties ctx, int AD_Org_ID)
	{
		MOrgInfo retValue = null;
		String sql = "SELECT * FROM AD_OrgInfo WHERE AD_Org_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				retValue = new MOrgInfo (ctx, rs, null);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "get", e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		return retValue;
	}	//	get

	/** Static Logger					*/
	private static CLogger		s_log = CLogger.getCLogger (MOrgInfo.class);

	
	/**************************************************************************
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MOrgInfo (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MOrgInfo

	/**
	 * 	Organization constructor
	 *	@param org org
	 */
	public MOrgInfo (MOrg org)
	{
		super(org.getCtx(), 0, org.get_TrxName());
		setClientOrg(org);
		setDUNS ("?");
		setTaxID ("?");
	}	//	MOrgInfo
	
}	//	MOrgInfo
