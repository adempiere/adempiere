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
 * Contributor(s): victor.perez@e-evolution.com http://www.e-evolution.com    *
 *****************************************************************************/

package org.adempiere.model.engines;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MResource;
import org.compiere.model.MTransaction;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.report.MReportTree;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrder;
import org.eevolution.model.MPPOrderBOMLine;
import org.eevolution.model.MPPOrderCost;
import org.eevolution.model.MPPOrderNode;

/**
 * Cost Engine
 * @author victor.perez@e-evolution.com http://www.e-evolution.com
 *
 */
public class CostEngine 
{
	/**	Logger							*/
	protected static transient CLogger	log = CLogger.getCLogger (CostEngine.class);
	
    /**
     * Get the the Total Cost for Cost Type and Cost Element Type
     * @param product Product
     * @param as  Account Schema
     * @param AD_Org_ID Organization ID
     * @param M_AttributeSetInstance_ID Attribute Set Instance ID
     * @param M_CostType_ID cost type
     * @param CostElementType Cost Element Type
     * @param Qty Quantity
     * @return Total Costs for Cost Type and Cost Element Type
     */
	public static BigDecimal getOrderCostsByElement (int PP_Order_ID, MProduct product, MAcctSchema as,  
			int AD_Org_ID, int M_AttributeSetInstance_ID,
			int M_CostType_ID, int M_CostElement_ID,
			BigDecimal Qty)
	{
		CostDimension d = new CostDimension(product, as, M_CostType_ID, AD_Org_ID, M_AttributeSetInstance_ID,
												M_CostElement_ID);
		BigDecimal costPrice = d.toQuery(MPPOrderCost.class,
									MPPOrderCost.COLUMNNAME_PP_Order_ID+"=?", new Object[]{PP_Order_ID},
									product.get_TrxName())
								.sum(MPPOrderCost.COLUMNNAME_CurrentCostPrice+"+"+MPPOrderCost.COLUMNNAME_CurrentCostPriceLL);
		BigDecimal cost = costPrice.multiply(Qty);
		
		// Fix Cost Precision 
		int precision = as.getCostingPrecision();
		if (cost.scale() > precision)
		{
			cost = cost.setScale(precision, RoundingMode.HALF_UP);
		}
		
		return cost;
	}
	
	/**
     * Get the the Total Cost for Cost Type and Cost Element Type from MCost
     * @param product Product
     * @param as  Account Schema
     * @param AD_Org_ID Organization ID
     * @param M_AttributeSetInstance_ID Attribute Set Instance ID
     * @param M_CostType_ID cost type
     * @param CostElementType Cost Element Type
     * @param Qty Quantity
     * @return Total Costs for Cost Type and Cost Element Type
     */
	public static BigDecimal getCostsByElement (MProduct product, MAcctSchema as,  
			int AD_Org_ID, int M_AttributeSetInstance_ID,
			int M_CostType_ID, int M_CostElement_ID,
			BigDecimal Qty)
	{
		CostDimension d = new CostDimension(product, as, M_CostType_ID, AD_Org_ID, M_AttributeSetInstance_ID,
												M_CostElement_ID);
		BigDecimal costPrice = d.toQuery(MCost.class,null, null,
									product.get_TrxName())
								.sum(MCost.COLUMNNAME_CurrentCostPrice+"+"+MCost.COLUMNNAME_CurrentCostPriceLL);
		BigDecimal cost = costPrice.multiply(Qty);
		
		// Fix Cost Precision 
		int precision = as.getCostingPrecision();
		if (cost.scale() > precision)
		{
			cost = cost.setScale(precision, RoundingMode.HALF_UP);
		}
		
		return cost;
	}
	
	public static Collection<MCost> getByElement (MProduct product, MAcctSchema as, 
			int M_CostType_ID, int AD_Org_ID, int M_AttributeSetInstance_ID, int M_CostElement_ID)
	{
		//Set the Costing Level 
		String CostingLevel = product.getCostingLevel(as);
		if (MAcctSchema.COSTINGLEVEL_Client.equals(CostingLevel))
		{
			AD_Org_ID = 0;
			M_AttributeSetInstance_ID = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_Organization.equals(CostingLevel))
		{
			M_AttributeSetInstance_ID = 0;
		}
		else if (MAcctSchema.COSTINGLEVEL_BatchLot.equals(CostingLevel))
		{
			AD_Org_ID = 0;
		}

		final String whereClause = "AD_Client_ID=? AND AD_Org_ID=?"
			+ " AND "+MCost.COLUMNNAME_M_Product_ID+"=?"
			+ " AND "+MCost.COLUMNNAME_M_AttributeSetInstance_ID+"=?"
			+ " AND "+MCost.COLUMNNAME_C_AcctSchema_ID+"=?"
			+ " AND "+MCost.COLUMNNAME_M_CostType_ID+"=?"
			+ " AND "+MCost.COLUMNNAME_M_CostElement_ID+"=?";
		
		List<Object> params = new  ArrayList<Object>();
		params.add(product.getAD_Client_ID());
		params.add(AD_Org_ID);
		params.add(product.getM_Product_ID());
		params.add(M_AttributeSetInstance_ID);
		params.add(as.getC_AcctSchema_ID());
		params.add(M_CostType_ID);
		params.add(M_CostElement_ID);
		
		return new Query(product.getCtx(), MCost.Table_Name, whereClause, product.get_TrxName())
						.setParameters(params)
						.setOnlyActiveRecords(true)
						.list();
	}	//	get

	
	/**
	 * 
	 * @param MTransaction Material Transaction
	 * @param as Account Schema
	 * @param M_CostElement_ID Cost Element
	 * @return MCost
	 */
	public static MCost getCost(MTransaction mtrx, MAcctSchema as, MCostElement element)
	{
		MProduct product = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID());
		CostDimension d = new CostDimension(product, as, as.getM_CostType_ID(),
				mtrx.getAD_Org_ID(), mtrx.getM_AttributeSetInstance_ID(),
				element.get_ID());
		return d.toQuery(MCost.class, mtrx.get_TrxName()).firstOnly();
	}
	
	/**
	 * Get Cost Detail
	 * @param model Model Inventory Line
	 * @param as Account Schema
	 * @param M_CostElement_ID Cost Element
	 * @param M_AttributeSetInstance_ID
	 * @return MCostDetail 
	 */
	public static MCostDetail getCostDetail(IDocumentLine model, MTransaction mtrx ,MAcctSchema as, int M_CostElement_ID)
	{
		final String whereClause = "AD_Client_ID=? AND AD_Org_ID=?"
						+" AND "+model.get_TableName()+"_ID=?" 
						+" AND "+MCostDetail.COLUMNNAME_M_Product_ID+"=?"
						+" AND "+MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID+"=?"
						+" AND "+MCostDetail.COLUMNNAME_C_AcctSchema_ID+"=?"
//						+" AND "+MCostDetail.COLUMNNAME_M_CostType_ID+"=?"
						+" AND "+MCostDetail.COLUMNNAME_M_CostElement_ID+"=?";
		final Object[] params = new Object[]{
				mtrx.getAD_Client_ID(), 
				mtrx.getAD_Org_ID(), 
				model.get_ID(),
				mtrx.getM_Product_ID(),
				mtrx.getM_AttributeSetInstance_ID(),
				as.getC_AcctSchema_ID(),
				//as.getM_CostType_ID(), 
				M_CostElement_ID, 
		};
		return new Query(mtrx.getCtx(),MCostDetail.Table_Name, whereClause , mtrx.get_TrxName())
		.setParameters(params)
		.firstOnly();
	}	
	
	/**
	 * Create Cost Detail
	 * @param model Model Inventory Line
	 * @param MTransaction Material Transaction
	 */
	public static void createCostDetail (IDocumentLine model , MTransaction mtrx)
	{
		BigDecimal Amt = Env.ZERO;
		for(MAcctSchema as : MAcctSchema.getClientAcctSchema(mtrx.getCtx(), mtrx.getAD_Client_ID()))
		{
			boolean skip = false;
			if (as.getAD_OrgOnly_ID() != 0)
			{
				if (as.getOnlyOrgs() == null)
					as.setOnlyOrgs(MReportTree.getChildIDs(mtrx.getCtx(), 
						0, MAcctSchemaElement.ELEMENTTYPE_Organization, 
						as.getAD_OrgOnly_ID()));

				//	Header Level Org
				skip = as.isSkipOrg(mtrx.getAD_Org_ID());
				//	Line Level Org
				skip = as.isSkipOrg(mtrx.getAD_Org_ID());
			}
			if (skip)
			{
				continue;
			}

			// Cost Detail
			final MProduct product = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID());
			final String costingMethod = product.getCostingMethod(as);
			for (MCostElement element : MCostElement.getByCostingMethod(mtrx.getCtx(), costingMethod))
			{					
					//	Delete Unprocessed zero Differences
					deleteCostDetail(model, as, element.get_ID(), mtrx.getM_AttributeSetInstance_ID());
					MCostDetail cd = CostEngine.getCostDetail(model, mtrx ,as, element.get_ID());
					if (cd == null)		//	createNew
					{	
						MCost cost = getCost(mtrx, as, element);
						if(cost == null)
							throw new AdempiereException("@NotFound@ @Cost@"); 
						Amt = cost.getCurrentCostPrice().add(cost.getCurrentCostPriceLL()).multiply(mtrx.getMovementQty());
						cd = new MCostDetail (as, mtrx.getAD_Org_ID(), 
								mtrx.getM_Product_ID(), mtrx.getM_AttributeSetInstance_ID(), 
								element.get_ID(),
								Amt, mtrx.getMovementQty(),
								model.getDescription(),
								mtrx.get_TrxName());
//						cd.setMovementDate(mtrx.getMovementDate());
//						if (cost != null)
//						{	
//							cd.setCurrentCostPrice(cost.getCurrentCostPrice());
//							cd.setCurrentCostPriceLL(cost.getCurrentCostPriceLL());
//						}
//						else
//						{
//							cd.setCurrentCostPrice(Env.ZERO);
//							cd.setCurrentCostPriceLL(Env.ZERO);
//						}
//						cd.setM_CostType_ID(as.getM_CostType_ID());
//						//cd.setCostingMethod(element.getCostingMethod());
//						cd.setM_Transaction_ID(mtrx.get_ID());
						if(model instanceof MPPCostCollector)
							cd.setPP_Cost_Collector_ID(model.get_ID());
					}
					else
					{
						cd.setDeltaAmt(Amt.subtract(cd.getAmt()));
						cd.setDeltaQty(mtrx.getMovementQty().subtract(cd.getQty()));
						if (cd.isDelta())
						{
							cd.setProcessed(false);
							cd.setAmt(Amt);
							cd.setQty(mtrx.getMovementQty());
						}
					}
					boolean ok = cd.save();
					if (ok && !cd.isProcessed())
					{
						MClient client = MClient.get(as.getCtx(), as.getAD_Client_ID());
						if (client.isCostImmediate())
							cd.process();
					}
					log.config("(" + ok + ") " + cd);
			} // for ELements	
		} // Account Schema 			
	}	//	createProduction
	
	public static int deleteCostDetail(IDocumentLine model, MAcctSchema as ,int M_CostElement_ID,
			int M_AttributeSetInstance_ID)
	{
		//	Delete Unprocessed zero Differences
		String sql = "DELETE " + MCostDetail.Table_Name
			+ " WHERE Processed='N' AND COALESCE(DeltaAmt,0)=0 AND COALESCE(DeltaQty,0)=0"
			+ " AND "+model.get_TableName()+"_ID=?" 
			+ " AND "+MCostDetail.COLUMNNAME_C_AcctSchema_ID+"=?" 
			+ " AND "+MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID+"=?"
//			+ " AND "+MCostDetail.COLUMNNAME_M_CostType_ID+"=?"
			+ " AND "+MCostDetail.COLUMNNAME_M_CostElement_ID+"=?";
		Object[] parameters = new Object[]{ model.get_ID(), 
											as.getC_AcctSchema_ID(), 
											M_AttributeSetInstance_ID,
											//as.getM_CostType_ID(),
											M_CostElement_ID};

		int no =DB.executeUpdateEx(sql,parameters, model.get_TrxName());
		if (no != 0)
			log.config("Deleted #" + no);
		return no;

	}

	/**
	 * Get Rate for this Resource
	 * @param r
	 * @param d
	 * @param trxName
	 * @return
	 */
	public static double getResouceRate(MResource r, CostDimension d, String trxName)
	{
		return d.setM_Product_ID(r.getProduct().get_ID())
				.toQuery(MCost.class, trxName)
				.sum(MCost.COLUMNNAME_CurrentCostPrice)
				.doubleValue();
	}

	public static BigDecimal getNodeCost(MWFNode node,
											int setuptime, int duration,
											CostDimension d, String trxName)
	{
		Properties ctx = node.getCtx();
		MResource resource = MResource.get(ctx, node.getS_Resource_ID());
		if(resource == null)
		{
			return Env.ZERO;
		}

		//
		// Get Resource Rate
		double rate = getResouceRate(resource, d, trxName);
		if (rate == 0)
		{
			return Env.ZERO;
		}

		//
		// Calculate duration in hours
		MWorkflow workflow = node.getWorkflow();
		MUOM uom = MUOM.get(ctx, resource.getC_UOM_ID());
		double hoursMultiplier;
		if (uom.isHour())
		{
			hoursMultiplier = (double)workflow.getDurationBaseSec() / (double)3600;
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @C_UOM_ID@="+uom.getName());
		}
		double unitDuration = duration;
		double unitsPerCycle = node.getUnitsCycles().intValue(); 
		if (unitsPerCycle > 0)
		{
			unitDuration = (double)duration / (double)unitsPerCycle;
		}
		double totalDuration = (setuptime / workflow.getQtyBatchSize().doubleValue() + unitDuration);
		double totalDurationHours = totalDuration * hoursMultiplier;

		//
		// Calculate Activity Cost
		BigDecimal cost = BigDecimal.valueOf(rate * totalDurationHours);

		//
		// Round to Costing Precision
		final int precision = MAcctSchema.get(ctx, d.getC_AcctSchema_ID()).getCostingPrecision();
		if (cost.scale() > precision)
		{
			cost = cost.setScale(precision, RoundingMode.HALF_UP);
		}

		log.info("Node: " + node.getName() + ", Resouce: " + resource.getName());
		log.info("Dimension: "+d);
		log.info("Duration (H): " + totalDurationHours +  " Rate: " + rate + " => Cost: "+cost);
		return cost;
	}
	
	public static BigDecimal getNodeCost(MPPOrderNode activity,
											BigDecimal setuptime, BigDecimal duration,
											CostDimension d, String trxName)
	{
		// TODO: proper implementation because
		//	* we need to use PP_Order_Workflow.QtyBatchSize instead of AD_Workflow.QtyBatchSize 
		MWFNode node = (MWFNode)activity.getAD_WF_Node();
		BigDecimal cost = getNodeCost(node, setuptime.intValue(), duration.intValue(), d, trxName);

		return cost;
	}
	
	public static boolean isActivityControlElement(MCostElement element)
	{
		String costElementType = element.getCostElementType();
		return MCostElement.COSTELEMENTTYPE_Resource.equals(costElementType)
				|| MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(costElementType);
	}
	
	public static boolean createMethodChangeVariance(MPPOrder order)
	{
		MPPOrderBOMLine[] bomlines = order.getLines();
		for(MPPOrderBOMLine line : bomlines)
		{
			if(line.getQtyBOM().signum() == 0 && line.getQtyBatch().signum() == 0)
			{
				//MPPCostCollector cc = MPPCostCollector.createCollector(order, order.getM_Product_ID(), 0, 0, order.getS_Resource_ID(), line.getPP_Order_BOMLine_ID(), 0, , MPP, movementdate, qty, scrap, reject, durationSetup, duration)
			}
		}
		
		return true;
	}
	
	public static boolean createUsedVariance(MPPOrder order)
	{
		return true;
	}
	
	public static boolean createRateVariance(MPPOrder order)
	{
		return true;
	}
	
	public static boolean createMixVariance(MPPOrder order)
	{
		return true;
	}
}
