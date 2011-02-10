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
 *                 Teo Sarca, www.arhipac.ro                                  *
 *****************************************************************************/

package org.eevolution.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MCost;
import org.compiere.model.Query;

/**
 * PP Order Cost Model.
 *
 * @author Victor Perez www.e-evolution.com     
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderCost extends X_PP_Order_Cost
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5350327491217294969L;

	/**
	 * Create Order Cost Dimension based on Cost Dimension
	 * @param PP_Order_ID Manufacturing Order
	 * @param cost Cost Dimension
	 * @return Order Cost Dimension
	 */
	public static MPPOrderCost createOrderCostDimensionint (int PP_Order_ID ,MCost cost)
	{
		// Check if we already added this cost dimension
		MPPOrderCost orderCostDimension = MPPOrderCost.getByCostDimension(PP_Order_ID,cost);
		if(orderCostDimension == null)
		{	
		   orderCostDimension = new MPPOrderCost(cost, PP_Order_ID, cost.get_TrxName());
		}
		else 
		{
			orderCostDimension.setCostDimension(cost);
		}
		orderCostDimension.saveEx();
		return orderCostDimension;
	}
	
	/**
	 * get Order Cost Dimension 
	 * @param PP_Order_ID Manufacturing Order ID
	 * @param cost Cost Dimension
	 * @return MPPOrderCost Order Cost Dimension
	 */
	public static MPPOrderCost getByCostDimension(int PP_Order_ID , MCost cost)
	{
		final StringBuffer whereClause = new StringBuffer();
		whereClause.append(MPPOrderCost.COLUMNNAME_PP_Order_ID + "=? AND ");
		whereClause.append(MPPOrderCost.COLUMNNAME_AD_Org_ID+ "=? AND "); 
		whereClause.append(MPPOrderCost.COLUMNNAME_C_AcctSchema_ID + "=? AND ");
		whereClause.append(MPPOrderCost.COLUMNNAME_M_CostType_ID+ "=? AND "); 
		whereClause.append(MPPOrderCost.COLUMNNAME_M_CostElement_ID+ "=? AND "); 
		whereClause.append(MPPOrderCost.COLUMNNAME_M_Product_ID+ "=? AND "); 
		whereClause.append(MPPOrderCost.COLUMNNAME_M_AttributeSetInstance_ID+ "=? ");
		
		return new Query(cost.getCtx(), I_PP_Order_Cost.Table_Name, whereClause.toString(), cost.get_TrxName())
		.setClient_ID()
		.setParameters(
				PP_Order_ID,
				cost.getAD_Org_ID(), 
				cost.getC_AcctSchema_ID(), 
				cost.getM_CostType_ID(), 
				cost.getM_CostElement_ID(), 
				cost.getM_Product_ID(), 
				cost.getM_AttributeSetInstance_ID())
		.firstOnly();
	}

	public MPPOrderCost(Properties ctx, int PP_Order_Cost_ID,String trxName)
	{
		super (ctx, PP_Order_Cost_ID, trxName);
	}	//	MOrder

	public MPPOrderCost(Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MOrder

	/**
	 * Peer constructor
	 * @param cost
	 * @param PP_Order_ID
	 */
	public MPPOrderCost(MCost cost, int PP_Order_ID, String trxName)
	{
		this(cost.getCtx(), 0, trxName);
		setPP_Order_ID(PP_Order_ID);
		setCostDimension(cost);
	}
	
	/**
	 * Set Values from Cost Dimension  
	 * @param cost
	 */
	public void setCostDimension(MCost cost)
	{
		setClientOrg(cost);
		setC_AcctSchema_ID(cost.getC_AcctSchema_ID());
		setM_CostType_ID(cost.getM_CostType_ID());
		setCumulatedAmt(cost.getCumulatedAmt());
		setCumulatedQty(cost.getCumulatedQty());
		setCurrentCostPrice(cost.getCurrentCostPrice());
		setCurrentCostPriceLL(cost.getCurrentCostPriceLL());
		setM_Product_ID(cost.getM_Product_ID());
		setM_AttributeSetInstance_ID(cost.getM_AttributeSetInstance_ID());
		setM_CostElement_ID(cost.getM_CostElement_ID());
	}
}
