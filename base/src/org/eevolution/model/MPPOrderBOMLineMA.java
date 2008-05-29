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

import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.compiere.util.*;
import org.compiere.model.*;

/**
 *	Shipment Material Allocation
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPPOrderBOMLineMA.java,v 1.1 2005/04/01 05:59:48 vpj-cd Exp $
 */
public class MPPOrderBOMLineMA extends X_PP_Order_BOMLineMA
{
	/**
	 * 	Get Material Allocations for Line
	 *	@param ctx context
	 *	@param PP_Order_BOMLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MPPOrderBOMLineMA[] get (Properties ctx, int PP_Order_BOMLine_ID, String trxName)
	{
		ArrayList list = new ArrayList ();
		String sql = "SELECT * FROM PP_Order_BOMLineMA WHERE PP_Order_BOMLine_ID=?";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, PP_Order_BOMLine_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				list.add (new MPPOrderBOMLineMA (ctx, rs, trxName));
			}
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
		
		MPPOrderBOMLineMA[] retValue = new MPPOrderBOMLineMA[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get
	
	/**
	 * 	Delete all Material Allocation for InOut
	 *	@param M_InOut_ID shipment
	 *	@return number of rows deleted or -1 for error
	 */
	public static int deleteOrderBOMLineMA (int PP_Order_BOMLine_ID, String trxName)
	{
		String sql = "DELETE FROM PP_Order_BOMLineMA ma WHERE EXISTS "
			+ "(SELECT * FROM PP_Order_BOMLine l WHERE l.PP_Order_BOMLine_ID=ma.PP_Order_BOMLine_ID"
			+ " AND PP_Order_BOMLine_ID=" + PP_Order_BOMLine_ID + ")";
		return DB.executeUpdate(sql, trxName);
	}	//	deleteInOutMA
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MPPOrderBOMLineMA.class);
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param M_InOutLineMA_ID ignored
	 *	@param trxName trx
	 */
	public MPPOrderBOMLineMA (Properties ctx, int PP_Order_BOMLineMA_ID, String trxName)
	{
		super (ctx, PP_Order_BOMLineMA_ID, trxName);
		if (PP_Order_BOMLineMA_ID != 0)
			throw new IllegalArgumentException("Multi-Key");
	}	//	MInOutLineMA

	/**
	 * 	Load Cosntructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MPPOrderBOMLineMA (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MInOutLineMA
	
	/**
	 * 	Parent Constructor
	 *	@param parent parent
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param MovementQty qty
	 */
	public MPPOrderBOMLineMA (MPPOrderBOMLine parent, int M_AttributeSetInstance_ID, BigDecimal MovementQty)
	{
		this (parent.getCtx(), 0, parent.get_TrxName());
		setClientOrg(parent);
		setPP_Order_BOMLine_ID(parent.getPP_Order_BOMLine_ID());
		//
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setMovementQty(MovementQty);
	}	//	MInOutLineMA
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPPOrderBOMLineMA[");
		sb.append("PP_Order_BOMLine_ID=").append(getPP_Order_BOMLine_ID())
			.append(",M_AttributeSetInstance_ID=").append(getM_AttributeSetInstance_ID())
			.append(", Qty=").append(getMovementQty())
			.append ("]");
		return sb.toString ();
	}	//	toString
	
}	//	PP_Order_BOMLineMA
