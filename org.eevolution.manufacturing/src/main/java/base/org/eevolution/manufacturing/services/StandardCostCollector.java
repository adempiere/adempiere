/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2015 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2015 Victor Pérez Juárez 								  *
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.manufacturing.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_WF_Node;
import org.adempiere.core.domains.models.I_PP_Cost_Collector;
import org.adempiere.core.domains.models.I_PP_Order_BOMLine;
import org.adempiere.engine.CostDimension;
import org.adempiere.engine.CostEngine;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.eevolution.manufacturing.model.MPPCostCollector;
import org.eevolution.manufacturing.model.MPPOrderCost;
import org.eevolution.model.RoutingService;
import org.eevolution.model.RoutingServiceFactory;

/**
 * A Cost collector cost implementation
 */
public class StandardCostCollector {
	public static BigDecimal getResourceStandardCostRate(MPPCostCollector cc,
			int S_Resource_ID, CostDimension d, String trxName) {
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), S_Resource_ID, null);
		return getProductStandardCostPrice(cc, resourceProduct,
				MAcctSchema.get(Env.getCtx(), d.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), d.getM_CostElement_ID()));
	}
	
	public static BigDecimal getProductStandardCostPrice(MPPCostCollector cc,
			MProduct product, MAcctSchema as, MCostElement element) {
		CostDimension d = new CostDimension(product, as, as.getM_CostType_ID(),
				0, // AD_Org_ID,
				0,
				0, // M_ASI_ID,
				element.getM_CostElement_ID());
		MPPOrderCost oc = d.toQuery(MPPOrderCost.class,
				MPPOrderCost.COLUMNNAME_PP_Order_ID + "=?",
				new Object[] { cc.getPP_Order_ID() }, cc.get_TrxName())
				.firstOnly();
		if (oc == null) {
			return Env.ZERO;
		}
		BigDecimal costs = oc.getCurrentCostPrice().add(
				oc.getCurrentCostPriceLL());
		return CostEngine.roundCost(costs, as.getC_AcctSchema_ID());
	}

	public static void createRateVariances(MPPCostCollector costCollector) {
		MProduct product = null;
		if (costCollector.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl)) {
			final I_AD_WF_Node node = costCollector.getPP_Order_Node().getAD_WF_Node();
			product = MProduct.forS_Resource_ID(costCollector.getCtx(),
					node.getS_Resource_ID(), null);
		} else if (costCollector.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ComponentIssue)) {
			final I_PP_Order_BOMLine bomLine = costCollector.getPP_Order_BOMLine();
			product = MProduct.get(costCollector.getCtx(), bomLine.getM_Product_ID());
		} else if (MPPCostCollector.COSTCOLLECTORTYPE_RateVariance.equals(costCollector.getCostCollectorType()))
			product =  MProduct.get(costCollector.getCtx(), costCollector.getM_Product_ID());

		MPPCostCollector costCollectorRateVariance = null; // Cost Collector - Rate Variance
		for (MAcctSchema accountSchema : CostEngine.getAcctSchema(costCollector)) {
			for (MCostElement costElement : MCostElement.getCostElement(costCollector.getCtx(), costCollector.get_TrxName())) {
				final MCostDetail cost = getCostDetail(costCollector, costElement.getM_CostElement_ID());
				if (cost == null)
					continue;
				//
				final BigDecimal quantity = cost.getQty();
				final BigDecimal priceStandard = getProductStandardCostPrice(costCollector, product, accountSchema, costElement);
				final BigDecimal priceActual = getProductActualCostPrice(costCollector, product, accountSchema, costElement, costCollector.get_TrxName());
				final BigDecimal amountStandard = CostEngine.roundCost(priceStandard.multiply(quantity), accountSchema.getC_AcctSchema_ID());
				final BigDecimal amtActual = CostEngine.roundCost(priceActual.multiply(quantity), accountSchema.getC_AcctSchema_ID());
				if (amountStandard.compareTo(amtActual) == 0)
					continue;
				//
				if (costCollectorRateVariance == null)
					costCollectorRateVariance = MPPCostCollector.createVarianceCostCollector(costCollector, MPPCostCollector.COSTCOLLECTORTYPE_RateVariance);

				List<MCostType> costTypes = MCostType.get(accountSchema.getCtx(), accountSchema.get_TrxName());
				for (MCostType costType : costTypes) {
					createVarianceCostDetail(costCollectorRateVariance, amtActual.abs(), quantity, cost, null, accountSchema, costType,  costElement);
					createVarianceCostDetail(costCollectorRateVariance, amountStandard.abs(), quantity, cost, null, accountSchema, costType, costElement);
				}
			}
		}
		//
		if (costCollectorRateVariance != null) {
			boolean ok = costCollectorRateVariance.processIt(MPPCostCollector.ACTION_Complete);
			costCollectorRateVariance.saveEx();
			if (!ok)
				throw new AdempiereException(costCollectorRateVariance.getProcessMsg());
		}
	}
	
	public static List<MCostDetail> getByCollectorCost(MPPCostCollector costCollector)
	{
		StringBuffer whereClause = new StringBuffer();
		whereClause.append(MCostDetail.COLUMNNAME_PP_Cost_Collector_ID).append("=? ");	
		return new Query(costCollector.getCtx(), MCostDetail.Table_Name , whereClause.toString(), costCollector.get_TrxName())
		.setClient_ID()
		.setParameters(costCollector.getPP_Cost_Collector_ID())
		.list();
	}
	
	public static MCostDetail getCostDetail(MPPCostCollector cc, int M_CostElement_ID) {
		final String whereClause = MCostDetail.COLUMNNAME_PP_Cost_Collector_ID
				+ "=?" + " AND " + MCostDetail.COLUMNNAME_M_CostElement_ID
				+ "=?";
		MCostDetail cd = new Query(cc.getCtx(), MCostDetail.Table_Name,
				whereClause, cc.get_TrxName())
				.setClient_ID()
				.setParameters(
						new Object[] { cc.getPP_Cost_Collector_ID(),
								M_CostElement_ID }).firstOnly();
		return cd;
	}

	public static void  createMethodVariances (MPPCostCollector costCollector) {
		if (!costCollector.getCostCollectorType().equals(MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance))
			return;

		for (MAcctSchema as : CostEngine.getAcctSchema(costCollector)) {
			for (MCostElement element : MCostElement.getCostElement(costCollector.getCtx(), costCollector.get_TrxName())) {
				List<MCostType> costtypes = MCostType.get(as.getCtx(), as.get_TrxName());
				for (MCostType costType : costtypes) {
					//implementation only for standard cost
					if (!MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod()))
						continue;
					MProduct product = MProduct.get(costCollector.getCtx(),costCollector.getM_Product_ID());
					final BigDecimal priceActual = getProductActualCostPrice(costCollector, product, as, element, costCollector.get_TrxName());
					createVarianceCostDetail(costCollector, priceActual, costCollector.getMovementQty(), null, product , as, costType, element);
				}
			}
		}
	}

	public static void createMethodVariancesFromActivityControl(MPPCostCollector costCollector) {
		if (!costCollector.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl))
			return;
		//
		final int std_resource_id = costCollector.getPP_Order_Node().getAD_WF_Node()
				.getS_Resource_ID();
		final int actual_resource_id = costCollector.getS_Resource_ID();
		if (std_resource_id == actual_resource_id) {
			return;
		}
		//
		MPPCostCollector methodChangeVariance = null; // Cost Collector - Method Change Variance
		final RoutingService routingService = RoutingServiceFactory.get()
				.getRoutingService(costCollector.getAD_Client_ID());
		for (MAcctSchema as : CostEngine.getAcctSchema(costCollector)) {
			for (MCostElement element : MCostElement.getCostElement(costCollector.getCtx(), costCollector.get_TrxName())) {
				final MProduct resourcePStd = MProduct.forS_Resource_ID(costCollector.getCtx(), std_resource_id, null);
				final MProduct resourcePActual = MProduct.forS_Resource_ID(costCollector.getCtx(), actual_resource_id, null);
				final BigDecimal priceStd = getProductActualCostPrice(costCollector, resourcePStd, as, element, costCollector.get_TrxName());
				final BigDecimal priceActual = getProductActualCostPrice(costCollector, resourcePActual, as, element, costCollector.get_TrxName());
				if (priceStd.compareTo(priceActual) == 0) {
					continue;
				}
				//
				if (methodChangeVariance == null) {
					methodChangeVariance = MPPCostCollector.createVarianceCostCollector(costCollector, MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance);
				}
				//
				final BigDecimal qty = routingService.getResourceBaseValue(
						costCollector.getS_Resource_ID(), costCollector);
				final BigDecimal amtStd = priceStd.multiply(qty);
				final BigDecimal amtActual = priceActual.multiply(qty);
				//
				List<MCostType> costtypes = MCostType.get(as.getCtx(), as.get_TrxName());
				for (MCostType costType : costtypes) {
					//implementation only for standard cost
					if (!MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod()))
						continue;
					createVarianceCostDetail(methodChangeVariance, amtActual.abs(), qty, null, resourcePActual, as, costType, element);
					createVarianceCostDetail(methodChangeVariance, amtStd.negate(), qty.negate(), null, resourcePStd, as, costType , element);
				}
			}
		}
		//
		if (methodChangeVariance != null) {
			boolean ok = methodChangeVariance.processIt(MPPCostCollector.ACTION_Complete);
			methodChangeVariance.saveEx();
			if (!ok)
				throw new AdempiereException(methodChangeVariance.getProcessMsg());
		}
	}

	/**
	 * Create Cost detail from cost collector
	 * @param costCollector
	 * @param amount
	 * @param quantity
	 * @param cost
	 * @param product
	 * @param accountSchema
	 * @param costType
	 * @param costElement
	 * @return
	 */
	public static  MCostDetail createVarianceCostDetail(MPPCostCollector costCollector,
			BigDecimal amount, BigDecimal quantity, MCostDetail cost, MProduct product,
			MAcctSchema accountSchema,MCostType costType ,  MCostElement costElement) {
		final MCostDetail costDetailVariance = new MCostDetail(costCollector.getCtx(), 0,
				costCollector.get_TrxName());
		if (cost != null) {
			MCostDetail.copyValues(cost, costDetailVariance);
			costDetailVariance.setProcessed(false);
		}
		if (product != null) {
			costDetailVariance.setM_Product_ID(product.getM_Product_ID());
			costDetailVariance.setM_AttributeSetInstance_ID(costCollector.getM_AttributeSetInstance_ID());
		}
		if (accountSchema != null) {
			costDetailVariance.setC_AcctSchema_ID(accountSchema.getC_AcctSchema_ID());
		}
		if (costElement != null) {
			costDetailVariance.setM_CostElement_ID(costElement.getM_CostElement_ID());
		}
		//
		costDetailVariance.setPP_Cost_Collector_ID(costCollector.getPP_Cost_Collector_ID());
		costDetailVariance.setM_CostType_ID(costType.getM_CostType_ID());
		costDetailVariance.setM_CostElement_ID(costElement.getM_CostElement_ID());
		costDetailVariance.setAmt(amount);
		costDetailVariance.setCostAmt(amount);
		costDetailVariance.setAmtLL(BigDecimal.ZERO);
		costDetailVariance.setQty(quantity);
		costDetailVariance.setDateAcct(costCollector.getDateAcct());
		costDetailVariance.saveEx();
		//processCostDetail(costDetailVariance);
		return costDetailVariance;
	}

	public static void createActivityControl(MPPCostCollector costCollector) {
		if (!costCollector.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl))
			return;
		//
		final MProduct product = MProduct.forS_Resource_ID(costCollector.getCtx(),
				costCollector.getS_Resource_ID(), null);
		final RoutingService routingService = RoutingServiceFactory.get()
				.getRoutingService(costCollector.getAD_Client_ID());
		final BigDecimal quantity = routingService.getResourceBaseValue(
				costCollector.getS_Resource_ID(), costCollector);

		for (MAcctSchema accountSchema : CostEngine.getAcctSchema(costCollector)) {
			for (MCostElement costElement : MCostElement.getCostElement(costCollector.getCtx(), costCollector.get_TrxName())) {
				if (!CostEngine.isActivityControlElement(costElement)) {
					continue;
				}
				final CostDimension dimension = new CostDimension(
						product, accountSchema,
						accountSchema.getM_CostType_ID(),
						costCollector.getAD_Org_ID(),
						costCollector.getM_Warehouse_ID(),
						costCollector.getM_AttributeSetInstanceTo_ID(),
						costElement.getM_CostElement_ID());
				final BigDecimal price = getResourceActualCostRate(costCollector.getS_Resource_ID(), dimension, costCollector.get_TrxName());
				BigDecimal costs = price.multiply(quantity);
				if (costs.scale() > accountSchema.getCostingPrecision())
					costs = costs.setScale(accountSchema.getCostingPrecision(),
							RoundingMode.HALF_UP);
				//
				List<MCostType> costTypes = MCostType.get(accountSchema.getCtx(), accountSchema.get_TrxName());
				for (MCostType costType : costTypes) {
					//implementation only for standard cost
					if (!MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod()))
						continue;
					
					MCostDetail cost = new MCostDetail(accountSchema, costCollector.getAD_Org_ID(), // AD_Org_ID,
							dimension.getM_Product_ID(), 0, // M_AttributeSetInstance_ID,
							costElement.getM_CostElement_ID(), costs.negate(),
							quantity.negate(), costElement.getName() , // Description,
							costCollector.get_TrxName(), costType.getM_CostType_ID());
					cost.setPP_Cost_Collector_ID(costCollector.getPP_Cost_Collector_ID());
					cost.setDateAcct(costCollector.getDateAcct());
					cost.setCostAmt(costs.negate());
					cost.saveEx();
				}
			}
		}
	}

	public static void createUsageVariances(MPPCostCollector usageVariance) {
		// Apply only for material Usage Variance
		if (!usageVariance.isCostCollectorType(MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance))
			throw new IllegalArgumentException("Cost Collector is not Material Usage Variance");
		//
		final MProduct product;
		final BigDecimal quantity;
		if (usageVariance.getPP_Order_BOMLine_ID() > 0) {
			product = MProduct.get(usageVariance.getCtx(), usageVariance.getM_Product_ID());
			quantity = usageVariance.getMovementQty();
		} else {
			product = MProduct.forS_Resource_ID(usageVariance.getCtx(), usageVariance.getS_Resource_ID(), null);
			final RoutingService routingService = RoutingServiceFactory.get().getRoutingService(usageVariance.getAD_Client_ID());
			quantity = routingService.getResourceBaseValue(usageVariance.getS_Resource_ID(), usageVariance);
		}
		//
		for (MAcctSchema accountSchema : CostEngine.getAcctSchema(usageVariance)) {
			for (MCostElement element : MCostElement.getCostElement(usageVariance.getCtx(), usageVariance.get_TrxName())) {
				final BigDecimal price = getProductActualCostPrice(usageVariance, product, accountSchema, element, usageVariance.get_TrxName());
				final BigDecimal amt = CostEngine.roundCost(price.multiply(quantity), accountSchema.getC_AcctSchema_ID());
				//
				// Create / Update Cost Detail
				if (amt.compareTo(Env.ZERO) != 0)
				{
					List<MCostType> costTypes = MCostType.get(accountSchema.getCtx(), accountSchema.get_TrxName());
					for (MCostType costType : costTypes) {
						createVarianceCostDetail(usageVariance, amt.abs(), quantity, null, product, accountSchema, costType , element);
					}
				}
			} // for Elments
		} // Account Schema
	}

	/**
	 * get Resource Actual Cost Rate
	 * @param resourceId
	 * @param costDimension
	 * @param trxName
	 * @return
	 */
	public static BigDecimal getResourceActualCostRate(int resourceId, CostDimension costDimension, String trxName) {
		if (resourceId <= 0)
			return Env.ZERO;
		final MProduct resourceProduct = MProduct.forS_Resource_ID(Env.getCtx(), resourceId, trxName);
		CostDimension resourcecCostDimension = new CostDimension(costDimension.setM_Product(resourceProduct));
		MCost cost = resourcecCostDimension.toQuery(MCost.class, trxName).firstOnly();
		if (cost == null)
			return Env.ZERO;
		BigDecimal price = cost.getCurrentCostPrice().add(cost.getCurrentCostPriceLL());
		return CostEngine.roundCost(price, resourcecCostDimension.getC_AcctSchema_ID());
	}



	public static BigDecimal getProductActualCostPrice(MPPCostCollector costCollector,
			MProduct product, MAcctSchema acctSchema, MCostElement element,
			String trxName) {
		String costingLevel = product.getCostingLevel(acctSchema);
		// Org Element
		int orgId = 0;
		int warehouseId = 0 ;
		int attributeSetInstanceId = 0;
		if (costCollector != null) {
			orgId = costCollector.getAD_Org_ID();
			warehouseId = costCollector.getM_Warehouse_ID();
			attributeSetInstanceId = costCollector.getM_AttributeSetInstance_ID();
		}

		if (MAcctSchema.COSTINGLEVEL_Client.equals(costingLevel)) {
			orgId = 0;
			attributeSetInstanceId = 0;
			warehouseId = 0;
		} 
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(costingLevel))
		{	
			attributeSetInstanceId = 0;
			warehouseId = 0;
		}	
		else if (MAcctSchema.COSTINGLEVEL_Warehouse.equals(costingLevel))
		{	
			attributeSetInstanceId = 0;
		}	
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(costingLevel))
		{
			orgId = 0;
			warehouseId = 0;
		}
			
		CostDimension costDimension = new CostDimension(product, acctSchema, acctSchema.getM_CostType_ID(),
				orgId, warehouseId ,attributeSetInstanceId, // M_ASI_ID,
				element.getM_CostElement_ID());
		MCost cost = costDimension.toQuery(MCost.class, trxName).firstOnly();
 		if (cost == null)
			return Env.ZERO;
		BigDecimal price = cost.getCurrentCostPrice().add(
				cost.getCurrentCostPriceLL());
		return CostEngine.roundCost(price, acctSchema.getC_AcctSchema_ID());
	}
	
	/**
	 * get Cost Collector That not was generate by inventory transaction
	 * @param productId
	 * @param dateAccount
	 * @param dateAccountTo
	 * @param trxName
	 * @return Collection the Cost Collector
	 */
	public static List<MPPCostCollector> getCostCollectorNotTransaction(
			Properties ctx,
			int productId,
			Timestamp dateAccount,
			Timestamp dateAccountTo,
			String trxName)
	{
		List<Object> params = new ArrayList<Object>();
		final StringBuffer whereClause = new StringBuffer();
		whereClause.append(MPPCostCollector.COLUMNNAME_CostCollectorType +" NOT IN ('100','110') AND ");
		if(productId > 0)
		{	
		  whereClause.append(MPPCostCollector.COLUMNNAME_M_Product_ID + "=? AND ");
		  params.add(productId);
		}	 
		if (dateAccount == null || dateAccountTo == null)
			throw new AdempiereException("@DateAcct@ @NotFound@");

		whereClause.append(MPPCostCollector.COLUMNNAME_DateAcct + ">=? AND ");
		params.add(dateAccount);

		whereClause.append(MPPCostCollector.COLUMNNAME_DateAcct + "<=?");
		params.add(dateAccountTo);
		 
		return new Query(ctx, I_PP_Cost_Collector.Table_Name, whereClause.toString() , trxName)
					.setClient_ID()
					.setParameters(params)
					.list();
								 
	}
}    //	AveragePOCostCollector
