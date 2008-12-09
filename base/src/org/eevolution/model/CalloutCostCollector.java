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

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

/**
 * Cost Collector Callout
 *
 * @author Victor Perez www.e-evolution.com     
 * @author Teo Sarca, www.arhipac.ro
 */
public class CalloutCostCollector extends CalloutEngine
{
	public String Manufacture (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		Integer PP_Order_ID = (Integer)value;
		if (PP_Order_ID == null || PP_Order_ID <= 0)
			return "";
		//
		MPPOrder order =  new MPPOrder(ctx, PP_Order_ID, null);
		mTab.setValue(MPPCostCollector.COLUMNNAME_S_Resource_ID, order.getS_Resource_ID());
		mTab.setValue(MPPCostCollector.COLUMNNAME_M_Product_ID, order.getM_Product_ID());
		mTab.setValue(MPPCostCollector.COLUMNNAME_M_AttributeSetInstance_ID, order.getM_AttributeSetInstance_ID());
		mTab.setValue(MPPCostCollector.COLUMNNAME_M_Warehouse_ID, order.getM_Warehouse_ID());
		mTab.setValue(MPPCostCollector.COLUMNNAME_MovementQty, order.getQtyEntered());
		//
		MPPOrderWorkflow owf = order.getMPPOrderWorkflow();
		mTab.setValue(MPPCostCollector.COLUMNNAME_PP_Order_Workflow_ID, owf.getPP_Order_Workflow_ID());
//		mTab.setValue(MPPCostCollector.COLUMNNAME_DurationUnit, owf.getDurationUnit());
		//
		return "";
	}

	public String node (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		/*
		Integer PP_Order_Node_ID = (Integer)value;
		// 
		if (PP_Order_Node_ID == null || PP_Order_Node_ID <= 0)
			return "";
		MPPOrderNode ordernode =  new MPPOrderNode(ctx, PP_Order_Node_ID, null);
		mTab.setValue(MPPCostCollector.COLUMNNAME_IsSubcontracting, ordernode.isSubcontracting() ? "Y" : "N");
		*/
		return "";
	}  

	public String MovementType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		return "";
	}  
}


