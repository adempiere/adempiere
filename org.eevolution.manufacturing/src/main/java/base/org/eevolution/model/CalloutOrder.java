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

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;
import org.compiere.model.MUOMConversion;
import org.compiere.util.Env;
import org.compiere.wf.MWorkflow;

/**
 *	Callout (Manufacturing) Order
 *	
 *  @author Victor Perez
 *  @author Teo Sarca, www.arhipac.ro
 */
public class CalloutOrder extends CalloutEngine
{
	/**	Debug Steps			*/
	private boolean steps = false;

	/**
	 *	Order Line - Quantity.
	 *		- called from C_UOM_ID, QtyEntered, QtyOrdered
	 *		- enforces qty UOM relationship
	 *  @param ctx      Context
	 *  @param WindowNo current Window No
	 *  @param mTab     Model Tab
	 *  @param mField   Model Field
	 *  @param value    The new value
	 */
	public String qty (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (value == null)
			return "";

		int M_Product_ID = Env.getContextAsInt(ctx, WindowNo, "M_Product_ID");
		if (steps) log.warning("qty - init - M_Product_ID=" + M_Product_ID + " - " );
		BigDecimal QtyOrdered = Env.ZERO ; 
		BigDecimal QtyEntered = Env.ZERO ;

		//	No Product
		if (M_Product_ID == 0)
		{
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			mTab.setValue("QtyOrdered", QtyEntered);
		}
		//	UOM Changed - convert from Entered -> Product
		else if (mField.getColumnName().equals("C_UOM_ID"))
		{

			int C_UOM_To_ID = ((Integer)value).intValue();
			QtyEntered = (BigDecimal)mTab.getValue("QtyEntered");
			QtyOrdered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
					C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		//	QtyEntered changed - calculate QtyOrdered
		else if (mField.getColumnName().equals("QtyEntered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyEntered = (BigDecimal)value;
			QtyOrdered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, 
					C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			log.fine("qty - UOM=" + C_UOM_To_ID 
					+ ", QtyEntered=" + QtyEntered
					+ " -> " + conversion 
					+ " QtyOrdered=" + QtyOrdered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
		}
		//	QtyOrdered changed - calculate QtyEntered
		else if (mField.getColumnName().equals("QtyOrdered"))
		{
			int C_UOM_To_ID = Env.getContextAsInt(ctx, WindowNo, "C_UOM_ID");
			QtyOrdered = (BigDecimal)value;
			QtyEntered = MUOMConversion.convertProductTo (ctx, M_Product_ID, 
					C_UOM_To_ID, QtyOrdered);
			if (QtyEntered == null)
				QtyEntered = QtyOrdered;
			boolean conversion = QtyOrdered.compareTo(QtyEntered) != 0;
			log.fine("qty - UOM=" + C_UOM_To_ID 
					+ ", QtyOrdered=" + QtyOrdered
					+ " -> " + conversion 
					+ " QtyEntered=" + QtyEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyEntered", QtyEntered);
		}
		return qtyBatch(ctx, WindowNo, mTab, mField, value);
		//return "";
	}	//	qty

	public String qtyBatch (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		I_PP_Order order = GridTabWrapper.create(mTab, I_PP_Order.class);
		MPPOrder.updateQtyBatchs(ctx, order, true);
		return "";
	}
	
	public String product (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		
		I_PP_Order order = GridTabWrapper.create(mTab, I_PP_Order.class);
		MProduct product = MProduct.get(ctx, order.getM_Product_ID());
		if (product == null)
		{
			return "";
		}
		order.setC_UOM_ID(product.getC_UOM_ID());
		
		I_PP_Product_Planning pp = getPP_Product_Planning(ctx, order);
		order.setAD_Workflow_ID(pp.getAD_Workflow_ID());
		order.setPP_Product_BOM_ID(pp.getPP_Product_BOM_ID());
		
		if (pp.getPP_Product_BOM_ID() > 0)
		{
			I_PP_Product_BOM bom = pp.getPP_Product_BOM();
			order.setC_UOM_ID(bom.getC_UOM_ID());
		}
		
		MPPOrder.updateQtyBatchs(ctx, order, true);
		
		return "";
	}

	/**
	 * Find Product Planning Data for given manufacturing order.
	 * If not planning found, a new one is created and filled with default values.
	 * <p>TODO: refactor with org.eevolution.process.MRP.getProductPlanning method 
	 * @param ctx context
	 * @param order manufacturing order
	 * @return product planning data (never return null) 
	 */
	protected static I_PP_Product_Planning getPP_Product_Planning(Properties ctx, I_PP_Order order)
	{
		I_PP_Product_Planning pp = MPPProductPlanning.find(ctx,
				order.getAD_Org_ID(), order.getM_Warehouse_ID(),
				order.getS_Resource_ID(), order.getM_Product_ID(),
				null);
		if (pp == null)
		{
			pp = new MPPProductPlanning(ctx, 0, null);
			pp.setAD_Org_ID(order.getAD_Org_ID());
			pp.setM_Warehouse_ID(order.getM_Warehouse_ID());
			pp.setS_Resource_ID(order.getS_Resource_ID());
			pp.setM_Product_ID(order.getM_Product_ID());
		}
		MProduct product = MProduct.get(ctx, pp.getM_Product_ID());
		//
		if (pp.getAD_Workflow_ID() <= 0)
		{
			pp.setAD_Workflow_ID(MWorkflow.getWorkflowSearchKey(product));
		}
		if (pp.getPP_Product_BOM_ID() <= 0)
		{
			I_PP_Product_BOM bom = MPPProductBOM.getDefault(product, null);
			if (bom != null)
			{
				pp.setPP_Product_BOM_ID(bom.getPP_Product_BOM_ID());
			}
		}
		//
		return pp;
	}
}	//	CalloutOrder

