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

package org.eevolution.manufacturing.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.eevolution.manufacturing.model.MPPProductPlanning;

/**
 * CreateProductPlanning
 * 
 * @author Victor Perez, e-Evolution, S.C.
 * @version $Id: CreateProductPlanning.java,v 1.1 2004/06/22 05:24:03 vpj-cd Exp $
 * 
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class CreateProductPlanning extends SvrProcess
{
	/** Process Parameters */;
	private int 			productCategoryId = 0;
	private int 				  warehouseId = 0;
	private int 				   resourceId = 0 ;
	private int 					plannerId = 0 ;
	private BigDecimal   deliveryTimePromised = Env.ZERO;
	private int			networkDistributionId = 0;
	private int 			 	   workflowId = 0;
	private BigDecimal 				timeFence = Env.ZERO;
	private boolean 			   createPlan = false;
	private boolean 					isMPS = false;
	private String 				  orderPolicy = "";
	private BigDecimal 			  orderPeriod = Env.ZERO;
	private BigDecimal 			 transferTime = Env.ZERO;
	private BigDecimal 			  safetyStock = Env.ZERO;
	private BigDecimal 				 orderMin = Env.ZERO;
	private BigDecimal 				 orderMax = Env.ZERO;
	private BigDecimal 				orderPack = Env.ZERO;
	private BigDecimal 				 orderQty = Env.ZERO;
	private BigDecimal 			  workingTime = Env.ZERO;
	private int 						yield = 0;
	private int 						orgId = 0;
	private int 					 clientId = 0;

	// Statistics 
	private int countCreated = 0;
	private int countUpdated = 0;

	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (MProduct.COLUMNNAME_M_Product_Category_ID.equals(name))
				productCategoryId = para.getParameterAsInt();
			else if (MPPProductPlanning.COLUMNNAME_M_Warehouse_ID.equals(name))
				warehouseId = para.getParameterAsInt();
			else if (MPPProductPlanning.COLUMNNAME_S_Resource_ID.equals(name))
				resourceId = para.getParameterAsInt();
			else if (MPPProductPlanning.COLUMNNAME_IsCreatePlan.equals(name))
				createPlan = para.getParameterAsBoolean();
			else if (MPPProductPlanning.COLUMNNAME_IsMPS.equals(name))
				isMPS = para.getParameterAsBoolean();
			else if (MPPProductPlanning.COLUMNNAME_DD_NetworkDistribution_ID.equals(name))
				networkDistributionId =  para.getParameterAsInt();
			else if (MPPProductPlanning.COLUMNNAME_AD_Workflow_ID.equals(name))
				workflowId =  para.getParameterAsInt();
			else if (MPPProductPlanning.COLUMNNAME_TimeFence.equals(name))
				timeFence =  para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_TransferTime.equals(name))
				transferTime =  para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_SafetyStock.equals(name))
				safetyStock = para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Order_Min.equals(name))
				orderMin = para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Order_Max.equals(name))
				orderMax =  para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Order_Pack.equals(name))
				orderPack = para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Order_Qty.equals(name))
				orderQty =  para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_WorkingTime.equals(name))
				workingTime = para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Yield.equals(name))
				yield =  para.getParameterAsInt();
			else if (MPPProductPlanning.COLUMNNAME_DeliveryTime_Promised.equals(name))
				deliveryTimePromised =  para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Order_Period.equals(name))
				orderPeriod = para.getParameterAsBigDecimal();
			else if (MPPProductPlanning.COLUMNNAME_Order_Policy.equals(name))
				orderPolicy =  para.getParameterAsString();
			else if (MPPProductPlanning.COLUMNNAME_Planner_ID.equals(name))
				plannerId =   para.getParameterAsInt();
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);
		}
		
		clientId = Env.getAD_Client_ID(getCtx());
		if(warehouseId > 0)
		{
			MWarehouse warehouse = MWarehouse.get(getCtx(), warehouseId);
			orgId = warehouse.getAD_Org_ID();
		}
	}	// prepare


	/***************************************************************************
	 * Create Data Planning record
	 */
	protected String doIt() throws Exception                
	{
		for (int productId : getProductIds(clientId))
			createPlanning(productId);


		return " @Created@ # " + countCreated +
			   " @Updated@ # " + countUpdated;
	}

	private int[] getProductIds(int clientId)
	{
		return new Query(getCtx() , MProduct.Table_Name , "AD_Client_ID=?" , get_TrxName())
				.setOnlyActiveRecords(true)
				.setParameters(clientId)
				.getIDs();
	}
	
	private void createPlanning(int productId)
	{
		MPPProductPlanning productPlanning = MPPProductPlanning.get(getCtx(), clientId, orgId, warehouseId, resourceId , productId , get_TrxName());
		boolean isNew = productPlanning == null;
		// Create Product Data Planning
		if (productPlanning == null)
		{
			productPlanning = new MPPProductPlanning(getCtx(), 0, get_TrxName());
			productPlanning.setAD_Org_ID(orgId);
			productPlanning.setM_Warehouse_ID(warehouseId);
			productPlanning.setS_Resource_ID(resourceId);
			productPlanning.setM_Product_ID(productId);
		}
		productPlanning.setDD_NetworkDistribution_ID (networkDistributionId);
		productPlanning.setAD_Workflow_ID(workflowId);
		productPlanning.setIsCreatePlan(createPlan);
		productPlanning.setIsMPS(isMPS);
		productPlanning.setIsRequiredMRP(true);
		productPlanning.setIsRequiredDRP(true);
		productPlanning.setDeliveryTime_Promised(deliveryTimePromised);
		productPlanning.setOrder_Period(orderPeriod);
		productPlanning.setPlanner_ID(plannerId);
		productPlanning.setOrder_Policy(orderPolicy);
		productPlanning.setSafetyStock(safetyStock);
		productPlanning.setOrder_Qty(orderQty);
		productPlanning.setOrder_Min(orderMin);
		productPlanning.setOrder_Max(orderMax);
		productPlanning.setOrder_Pack(orderPack);
		productPlanning.setTimeFence(timeFence);
		productPlanning.setTransferTime(transferTime);
		productPlanning.setIsPhantom(false);
		productPlanning.setWorkingTime(workingTime);
		productPlanning.setYield(yield);
		productPlanning.saveEx();

		if (isNew)
			countCreated++;
		else
			countUpdated++;
	}
}