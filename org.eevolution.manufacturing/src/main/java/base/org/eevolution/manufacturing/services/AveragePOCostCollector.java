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

import org.adempiere.engine.CostDimension;
import org.adempiere.engine.CostingMethodFactory;
import org.adempiere.engine.ICostingMethod;
import org.adempiere.engine.IDocumentLine;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.eevolution.manufacturing.model.MPPCostCollector;

/**
 * A Cost collector cost implementation
 */
public class AveragePOCostCollector {
	public static void createUpdateAverageCostDetail(MPPCostCollector costCollectorVariance,
			BigDecimal costVarianceThisLevel, BigDecimal costVarianceLowLevel,
			MProduct product,
			MAcctSchema acctSchema, MCostType costType, MCostElement costElement) {

		String whereClause = " exists (select 1 from pp_cost_collector pc" +
				" where pc.pp_cost_collector_ID=m_transaction.pp_Cost_collector_ID and costcollectortype =? " +
				" and pc.pp_order_ID=?)";
		MTransaction mtrx =  new Query(costCollectorVariance.getCtx(), MTransaction.Table_Name, whereClause, costCollectorVariance.get_TrxName())
		.setParameters(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt,costCollectorVariance.getPP_Order_ID())
		.setOrderBy("M_Transaction_ID desc")
		.first();

		BigDecimal costThisLevel = Env.ZERO;
		BigDecimal costLowLevel = Env.ZERO;
		String costingLevel = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID()).getCostingLevel(acctSchema, mtrx.getAD_Org_ID());
		costCollectorVariance.set_ValueOfColumn("Cost", costVarianceThisLevel.compareTo(Env.ZERO) != 0 ? costVarianceThisLevel : costVarianceLowLevel);
		costCollectorVariance.saveEx();
		IDocumentLine model = costCollectorVariance;

		MCost cost = MCost.validateCostForCostType(acctSchema, costType, costElement,product.getM_Product_ID(), 0, 0, 0, mtrx.get_TrxName());
		final ICostingMethod method = CostingMethodFactory.get().getCostingMethod(costType.getCostingMethod());
		method.setCostingMethod(acctSchema, mtrx, model, cost, costThisLevel, costLowLevel, model.isSOTrx());
		method.process();
	}

	public static BigDecimal getResourceActualCostRate(MPPCostCollector costCollector,
			int resourceId, CostDimension costDimension, String trxName) {
		if (resourceId <= 0)
			return Env.ZERO;
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), resourceId, null);
		return getProductActualCostPrice(costCollector, resourceProduct,
				MAcctSchema.get(Env.getCtx(), costDimension.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), costDimension.getM_CostElement_ID()),
				trxName);
	}
	

	public static BigDecimal getProductActualCostPrice(MPPCostCollector costCollector, MProduct product, MAcctSchema acctSchema, MCostElement costElement, String trxName)
	{
		String CostingLevel = product.getCostingLevel(acctSchema);
		// Org Element
		int orgId = 0;
		int warehouseId = 0;
		if (product.getS_Resource_ID() != 0){
			orgId = product.getS_Resource().getAD_Org_ID();
			warehouseId = product.getS_Resource().getM_Warehouse_ID();
		}
			
		else 
		{
			orgId = (costCollector == null)? costElement.getAD_Org_ID():costCollector.getAD_Org_ID();
			warehouseId = (costCollector == null)? 0:costCollector.getM_Warehouse_ID();
		}
		int attributeSetInstanceId = (costCollector == null)? 0:costCollector.getM_AttributeSetInstance_ID();
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
			orgId = 0;
			attributeSetInstanceId = 0;
			warehouseId = 0;
		} 
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
			attributeSetInstanceId = 0;
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
			orgId = 0;
		CostDimension costDimension = new CostDimension(product,
				acctSchema, acctSchema.getM_CostType_ID(),
				orgId,
				attributeSetInstanceId,
				warehouseId, //warehouse
				costElement.getM_CostElement_ID());
		MCost cost = costDimension.toQuery(MCost.class, trxName).firstOnly();

		if (cost == null)
			return Env.ZERO;
		BigDecimal price = cost.getCurrentCostPrice().add(
                cost.getCurrentCostPriceLL());
		return roundCost(price, acctSchema.getC_AcctSchema_ID());
	}

	protected static BigDecimal roundCost(BigDecimal price, int accountSchemaId) {
		// Fix Cost Precision
		int precision = MAcctSchema.get(Env.getCtx(), accountSchemaId)
				.getCostingPrecision();
		BigDecimal priceRounded = price;
		if (priceRounded.scale() > precision) {
			priceRounded = priceRounded.setScale(precision,
					RoundingMode.HALF_UP);
		}
		return priceRounded;
	}
	

	public static BigDecimal getResourceFutureCostRate(MPPCostCollector costCollector,
			int resourceId, CostDimension costDimension, String trxName) {
		if (resourceId <= 0)
			return Env.ZERO;
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), resourceId, null);
		return getProductFutureCostPrice(costCollector, resourceProduct,
				MAcctSchema.get(Env.getCtx(), costDimension.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), costDimension.getM_CostElement_ID()),
				trxName);
	}
	

	public static BigDecimal getProductFutureCostPrice(MPPCostCollector costCollector, MProduct product, MAcctSchema acctSchema, MCostElement costElement, String trxName)
	{
		String CostingLevel = product.getCostingLevel(acctSchema);
		// Org Element
		int orgId = 0;
		int warehouseId = 0;
		if (product.getS_Resource_ID() != 0){
			orgId = product.getS_Resource().getAD_Org_ID();
			warehouseId = product.getS_Resource().getM_Warehouse_ID();
		}
			
		else 
		{
			orgId = (costCollector == null)? costElement.getAD_Org_ID():costCollector.getAD_Org_ID();
			warehouseId = (costCollector == null)? 0:costCollector.getM_Warehouse_ID();
		}
		int attributeSetInstanceId = (costCollector == null)? 0:costCollector.getM_AttributeSetInstance_ID();
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel)) {
			orgId = 0;
			attributeSetInstanceId = 0;
			warehouseId = 0;
		} 
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
			attributeSetInstanceId = 0;
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
			orgId = 0;
		CostDimension d = new CostDimension(product,
				acctSchema, acctSchema.getM_CostType_ID(),
				orgId,
				attributeSetInstanceId,
				warehouseId, //warehouse
				costElement.getM_CostElement_ID());
		MCost cost = d.toQuery(MCost.class, trxName).firstOnly();

		if (cost == null)
			return Env.ZERO;
		BigDecimal price = cost.getFutureCostPrice().add(
                cost.getFutureCostPriceLL());
		return roundCost(price, acctSchema.getC_AcctSchema_ID());
	}
}    //	AveragePOCostCollector
