/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.model;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import org.compiere.util.*;

/**
 * 	Cost Element Model
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MCostElement.java,v 1.10 2005/11/28 03:35:24 vpj-cd Exp $
 */
public class MCostElement extends org.compiere.model.MCostElement
{
	
	
	/**
	 * 	Get active Material Cost Element for client 
	 *	@param po parent
	 *	@return cost element array
	 */
	public static MCostElement[] getCostElements (int AD_Org_ID)
	{
		ArrayList<MCostElement> list = new ArrayList<MCostElement>();
		String sql = "SELECT * FROM M_CostElement "
			+ "WHERE "
			+ " IsActive='Y' AND AD_Org_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
                        pstmt.setInt (1, AD_Org_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MCostElement (Env.getCtx(), rs, null));
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
		//
		MCostElement[] retValue = new MCostElement[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getMaterialCostElement
        
        
	
	
	/**	Cache						*/
	private static CCache<Integer,MCostElement>	s_cache	= new CCache<Integer,MCostElement>("M_CostElement", 20);
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MCostElement.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_CostElement_ID id
	 *	@param trxName trx
	 */
	public MCostElement (Properties ctx, int M_CostElement_ID, String trxName)
	{
		super (ctx, M_CostElement_ID, trxName);		
	}	//	MCostElement

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCostElement (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCostElement
		
}	//	MCostElement
