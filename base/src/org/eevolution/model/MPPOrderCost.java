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

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MCost;

/**
 *  Order Model.
 * 	Please do not set DocStatus and C_DocType_ID directly. 
 * 	They are set in the process() method. 
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.40 2004/04/13 04:19:30 vpj-cd Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 */
public class MPPOrderCost extends X_PP_Order_Cost
{
	private static final long serialVersionUID = 1L;

	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPOrderCost(Properties ctx, int PP_Order_Cost_ID,String trxName)
	{
		super (ctx, PP_Order_Cost_ID, trxName);
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
		setC_AcctSchema_ID(cost.getC_AcctSchema_ID());
		setCumulatedAmt(cost.getCumulatedAmt());
		setCumulatedQty(cost.getCumulatedQty());
		setCurrentCostPriceLL(cost.getCurrentCostPriceLL());
		setCurrentCostPrice(cost.getCurrentCostPrice());
		setM_Product_ID(cost.getM_Product_ID());
		setM_AttributeSetInstance_ID(cost.getM_AttributeSetInstance_ID());
		setM_CostElement_ID(cost.getM_CostElement_ID());
	}

	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	 public MPPOrderCost(Properties ctx, ResultSet rs, String trxName)
	{
		 super (ctx, rs, trxName);
	}	//	MOrder
}
