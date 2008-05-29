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

import java.util.*;
import java.sql.*;
import java.math.*;

import org.compiere.util.*;
import org.compiere.model.*;

/**
 *  Order Model.
 * 	Please do not set DocStatus and C_DocType_ID directly. 
 * 	They are set in the process() method. 
 * 	Use DocAction and C_DocTypeTarget_ID instead.
 *
 *  @author Victor Perez www.e-evolution.com     
 *  @version $Id: MOrder.java,v 1.40 2004/04/13 04:19:30 vpj-cd Exp $
 */
public class MPPOrderCost extends X_PP_Order_Cost
{
	/**
	 *  Default Constructor
	 *  @param ctx context
	 *  @param  C_Order_ID    order to load, (0 create new order)
	 */
	public MPPOrderCost(Properties ctx, int PP_Order_Cost_ID,String trxName)
	{
		super (ctx,  PP_Order_Cost_ID,trxName);
		//  New
		if ( PP_Order_Cost_ID == 0)
		{

		}
	}	//	MOrder
        
        	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrderCost(Properties ctx, MCost m_cost, int PP_Order_ID, String trxName)
	{
		super (ctx, 0,trxName);
                
                setC_AcctSchema_ID(m_cost.getC_AcctSchema_ID());
                
                setCumulatedAmt(m_cost.getCumulatedAmt());
                setCumulatedQty(m_cost.getCumulatedQty()); 
                setCurrentCostPrice(m_cost.getCurrentCostPrice());
                //setCurrentCostPriceLL(m_cost.getCurrentCostPriceLL());
                setCurrentCostPriceLL((BigDecimal)m_cost.get_Value("CurrentCostPriceLL"));
                setM_Product_ID(m_cost.getM_Product_ID());               
                setM_CostElement_ID(m_cost.getM_CostElement_ID());
                setM_AttributeSetInstance_ID(m_cost.getM_AttributeSetInstance_ID());
                save(get_TrxName());
                
	}	//	MOrder

        
        
	
	/**
	 *  Load Constructor
	 *  @param ctx context
	 *  @param rs result set record
	 */
	public MPPOrderCost(Properties ctx, ResultSet rs,String trxName)
	{
		super (ctx, rs,"PP_Order_Cost");
	}	//	MOrder

	/**
	 * 	Overwrite Client/Org if required
	 * 	@param AD_Client_ID client
	 * 	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg(AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg


	//	save

	
	

}	//	MOrder
