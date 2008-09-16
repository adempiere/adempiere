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
 *                 Teo Sarca, http://www.arhipac.ro                           *
 *****************************************************************************/
package org.eevolution.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;

/**
 *	Shipment Material Allocation
 *	
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MPPOrderBOMLineMA.java,v 1.1 2005/04/01 05:59:48 vpj-cd Exp $
 *  
 *  @author Teo Sarca, http://www.arhipac.ro
 */
public class MPPOrderBOMLineMA extends X_PP_Order_BOMLineMA
{
	private static final long serialVersionUID = 1L;

	/**
	 * 	Get Material Allocations for Line
	 *	@param ctx context
	 *	@param PP_Order_BOMLine_ID line
	 *	@param trxName trx
	 *	@return allocations
	 */
	public static MPPOrderBOMLineMA[] get (Properties ctx, int PP_Order_BOMLine_ID, String trxName)
	{
		final String whereClause = COLUMNNAME_PP_Order_BOMLine_ID+"=?";
		List<MPPOrderBOMLineMA> list = new Query(ctx, Table_Name, whereClause, trxName)
											.setParameters(new Object[]{PP_Order_BOMLine_ID})
											.setOrderBy(COLUMNNAME_PP_Order_BOMLineMA_ID)
											.list();
		return list.toArray(new MPPOrderBOMLineMA[list.size()]);
	}
	
	/**
	 * Delete all Material Allocation for PP order component
	 * @param PP_Order_BOMLine_ID PP order component line
	 * @return number of rows deleted
	 */
	public static int deleteOrderBOMLineMA (int PP_Order_BOMLine_ID, String trxName)
	{
		final String sql = "DELETE FROM "+Table_Name+" WHERE "+COLUMNNAME_PP_Order_BOMLine_ID+"=?";
		int no = DB.executeUpdateEx(sql, new Object[]{PP_Order_BOMLine_ID}, trxName);
		
		s_log.config("Delete old #" + no);
		return no;
	}
	
	/**	Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MPPOrderBOMLineMA.class);
	
	/**
	 * Standard Constructor
	 * @param ctx context
	 * @param M_InOutLineMA_ID ignored
	 * @param trxName trx
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
	 * Parent Constructor
	 * @param parent parent
	 * @param M_AttributeSetInstance_ID asi
	 * @param MovementQty qty
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
