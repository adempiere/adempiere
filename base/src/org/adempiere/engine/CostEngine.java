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

package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_C_ProjectIssue;
import org.compiere.model.I_M_CostDetail;
import org.compiere.model.I_M_CostElement;
import org.compiere.model.I_M_CostType;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_Inventory;
import org.compiere.model.I_M_MatchInv;
import org.compiere.model.I_M_MatchPO;
import org.compiere.model.I_M_Movement;
import org.compiere.model.I_M_Product;
import org.compiere.model.I_M_Production;
import org.compiere.model.I_M_Transaction;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovementLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MTransaction;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_M_ProductionLine;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.model.I_PP_Cost_Collector;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrder;

/**
 * Cost Engine
 * 
 * @author victor.perez@e-evolution.com http://www.e-evolution.com
 * 
 */
public class CostEngine {
	/** Logger */
	protected transient CLogger log = CLogger.getCLogger(getClass());

    /** AD_Table_ID's of documents          */
    public final static int[]  documentsTableID = {
            I_M_InOut.Table_ID,
            I_M_Inventory.Table_ID,
            I_M_Movement.Table_ID,
            I_M_Product.Table_ID,
            I_C_ProjectIssue.Table_ID,
            I_PP_Cost_Collector.Table_ID,
            I_M_MatchPO.Table_ID,
            I_M_MatchInv.Table_ID};

    /** Table Names of documents          */
    public final static String[]  documentsTableName = {
            I_M_InOut.Table_Name,
            I_M_Inventory.Table_Name,
            I_M_Movement.Table_Name,
            I_M_Production.Table_Name,
            I_C_ProjectIssue.Table_Name,
            I_PP_Cost_Collector.Table_Name,
            I_M_MatchPO.Table_Name,
            I_M_MatchInv.Table_Name};

    /**
     * get seed cost
     * @param context
     * @param productId
     * @param trxName
     * @return
     */
	public static BigDecimal getSeedCost(Properties context , int  productId ,  String trxName)
	{
		BigDecimal costThisLevel = Env.ZERO;
		for (MProductPO productPO : MProductPO.getOfProduct(context,productId, trxName))
		 {
			 if (productPO.isCurrentVendor())
			 { 
				 if (productPO.getPriceLastInv().signum() != 0)
					 costThisLevel = productPO.getPriceLastInv();
				 else if (productPO.getPriceLastPO().signum() != 0) 
					 costThisLevel = productPO.getPriceLastPO();
				 else if  (productPO.getPricePO().signum() != 0) 
					 costThisLevel = productPO.getPricePO();
				 else 
					 costThisLevel = productPO.getPriceList();
				 return costThisLevel;
			 } 
		 }
		return costThisLevel;
	}

    /**
     *  Get Actual Cost of Parent Product Based on Cost Type
     * @param accountSchema
     * @param costTypeId
     * @param costElementId
     * @param order
     * @return
     */
	public static BigDecimal getParentActualCostByCostType(MAcctSchema accountSchema, int costTypeId, int costElementId, MPPOrder order) {
		StringBuffer whereClause = new StringBuffer();

		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID + "=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID + "=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_PP_Cost_Collector_ID);
		whereClause.append(" IN (SELECT PP_Cost_Collector_ID FROM PP_Cost_Collector cc WHERE cc.PP_Order_ID=? AND ");
		whereClause.append(" cc.CostCollectorType <> '").append(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt).append("')");

		BigDecimal actualCost = new Query(order.getCtx(),
				I_M_CostDetail.Table_Name, whereClause.toString(),
				order.get_TrxName())
				.setClient_ID()
				.setParameters(costTypeId, costElementId,
						order.getPP_Order_ID())
				.sum("(" + MCostDetail.COLUMNNAME_Amt + "+"
						+ MCostDetail.COLUMNNAME_CostAmtLL + ")");

		whereClause = new StringBuffer();
		whereClause
				.append(" EXISTS (SELECT 1 FROM PP_Cost_Collector cc WHERE PP_Cost_Collector_ID=M_Transaction.PP_Cost_Collector_ID AND cc.PP_Order_ID=? AND cc.M_Product_ID=? )");
		BigDecimal qtyDelivered = new Query(order.getCtx(),
				I_M_Transaction.Table_Name, whereClause.toString(),
				order.get_TrxName()).setClient_ID()
				.setParameters(order.getPP_Order_ID(), order.getM_Product_ID())
				.sum(MTransaction.COLUMNNAME_MovementQty);

		if (actualCost == null)
			actualCost = Env.ZERO;

		if (qtyDelivered.signum() != 0)
			actualCost = actualCost.divide(qtyDelivered,
					accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_DOWN);

		// return actualCost.negate();
		return actualCost;
	}

    /**
     * Get Actual Cost of Parent Product Based on Cost Type
     * @param accountSchema
     * @param costTypeId
     * @param costElementId
     * @param model
     * @return
     */
	public static BigDecimal getParentActualCostByCostType(MAcctSchema accountSchema, int costTypeId, int costElementId, X_M_ProductionLine model) {
		StringBuffer whereClause = new StringBuffer();

		whereClause.append(MCostDetail.COLUMNNAME_M_CostType_ID + "=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_CostElement_ID + "=? AND ");
		whereClause.append(MCostDetail.COLUMNNAME_M_ProductionLine_ID);
		whereClause.append(" IN (SELECT M_ProductionLine_ID FROM M_ProductionLine pl WHERE pl.M_ProductionPlan_ID=? AND ");
		whereClause.append(" pl.M_Product_ID <> ? )");

		BigDecimal actualCostTotal = new Query(model.getCtx(),
				I_M_CostDetail.Table_Name, whereClause.toString(),
				model.get_TrxName())
				.setClient_ID()
				.setParameters(costTypeId, costElementId,
						model.getM_ProductionPlan_ID(), model.getM_Product_ID())
				.sum("(" + MCostDetail.COLUMNNAME_Amt + "+"
						+ MCostDetail.COLUMNNAME_AmtLL + ")");

		if (actualCostTotal == null)
			actualCostTotal = Env.ZERO;

		BigDecimal actualCost = Env.ZERO;
		if (model.getMovementQty().signum() != 0 && actualCostTotal.signum() != 0)
			actualCost = actualCostTotal.divide(model.getMovementQty().abs(),
					accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);

		return actualCost;
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

	/*public List<MCost> getByElement(MProduct product, MAcctSchema as,
			int M_CostType_ID, int AD_Org_ID,int M_Warehouse_ID ,int M_AttributeSetInstance_ID,
			int M_CostElement_ID) {
		CostDimension cd = new CostDimension(product, as, M_CostType_ID,
				AD_Org_ID,  M_Warehouse_ID, M_AttributeSetInstance_ID, M_CostElement_ID);
		return cd.toQuery(MCost.class, product.get_TrxName())
				.setOnlyActiveRecords(true).list();
	}*/

    /**
     * create cost detail based transaction
     * @param transaction
     */
	public void createCostDetail(MTransaction transaction) {
		createCostDetail(transaction, transaction.getDocumentLine());
	}

    /**
     * create cost detail
     * @param transaction
     * @param model
     */
	public void createCostDetail(MTransaction transaction, IDocumentLine model) {
		// happen when you create invoice from Purchase Order
		if (transaction == null)
			return;

		for (MAcctSchema accountSchema : MAcctSchema.getClientAcctSchema(transaction.getCtx(),
                transaction.getAD_Client_ID())) {
			createCostDetail(accountSchema, transaction, model, model.isSOTrx());
		}
	}

    /**
     * Create Cost Detail
     * @param accountSchema Account Schema
     * @param transaction Transaction
     * @param model  Model
     * @param isSOTrx  Is Sales Transaction
     */
	public void createCostDetail(MAcctSchema accountSchema , MTransaction transaction, IDocumentLine model, Boolean isSOTrx) {
		
		MClient client = new MClient (transaction.getCtx() , transaction.getAD_Client_ID(), transaction.get_TrxName());
		StringBuilder description = new StringBuilder();
		if (!Util.isEmpty(model.getDescription(), true))
			description.append(model.getDescription());
		if (isSOTrx != null) {
			description.append(isSOTrx ? "(|->)" : "(|<-)");
		}

		List<MCostElement> costElements = MCostElement.getCostElement(transaction.getCtx(),
				transaction.get_TrxName());
		List<MCostType> costTypes = MCostType.get(transaction.getCtx(), transaction.get_TrxName());

		for (MCostType costType : costTypes) {
			if (!costType.isActive())
				continue;
			for (MCostElement costElement : costElements) {
				createCostDetail(accountSchema, costType, costElement , transaction, model , client.isCostImmediate());
			}
		}
	}

	/**
	 * Create Cost Detail
	 * @param accountSchema Account Schema
	 * @param transaction Material Transaction
	 * @param model IDocumentLine
	 * @param costType Cost Type
	 * @param costElement Cost Element
	 */
	public void createCostDetail(MAcctSchema accountSchema,MCostType costType, MCostElement costElement, MTransaction transaction , IDocumentLine model, boolean force) {

		if (!force)
			return;
		
		BigDecimal costThisLevel = Env.ZERO;
		BigDecimal costLowLevel = Env.ZERO;
		String costingLevel = MProduct.get(transaction.getCtx(),
				transaction.getM_Product_ID()).getCostingLevel(accountSchema,
				transaction.getAD_Org_ID());
		
		// The Change of price in the Invoice Line is not generated cost
		// adjustment for Average PO Costing method
		if (model instanceof MMatchInv
				&& MCostType.COSTINGMETHOD_AveragePO.equals(costType
                .getCostingMethod()))
			return;

		// The Change of price in the Invoice Line is not generated cost
		// adjustment for Average PO Costing method
		if (model instanceof MMatchPO
				&& MCostType.COSTINGMETHOD_AverageInvoice.equals(costType
						.getCostingMethod()))
			return;

		if (model instanceof MLandedCostAllocation) {
			MLandedCostAllocation allocation = (MLandedCostAllocation) model;
			costThisLevel = allocation.getPriceActual();
		}



		MCost cost = MCost.validateCostForCostType(accountSchema, costType, costElement,
				transaction.getM_Product_ID(), transaction.getAD_Org_ID(), transaction.getM_Warehouse_ID(),
				transaction.getM_AttributeSetInstance_ID(), transaction.get_TrxName());

		// get the cost for positive transaction
		if ((MCostElement.COSTELEMENTTYPE_Material.equals(costElement
				.getCostElementType()) || MCostElement.COSTELEMENTTYPE_LandedCost
				.equals(costElement.getCostElementType()))
				&& transaction.getMovementType().contains("+")
				&& !MCostType.COSTINGMETHOD_StandardCosting.equals(costType
						.getCostingMethod())) {
			if 
			(	model instanceof MMovementLine
			||  model instanceof MInventoryLine
			|| (model instanceof MInOutLine && MTransaction.MOVEMENTTYPE_CustomerReturns.equals(transaction.getMovementType()))
			) 
			{				
				costThisLevel = getCostThisLevel(accountSchema, costType, costElement, transaction, model, costingLevel);				
				
					
				// If cost this level is zero and is a physical inventory then
				// try get cost from physical inventory
				if (model instanceof MInventoryLine
						&& costThisLevel.signum() == 0
						&& MCostElement.COSTELEMENTTYPE_Material.equals(costElement
								.getCostElementType())) {
					MInventoryLine inventoryLine = (MInventoryLine) model;
					// Use the cost only for Physical Inventory
					if (inventoryLine.getQtyInternalUse().signum() == 0 )
						costThisLevel = inventoryLine.getCurrentCostPrice();
					
					if(costThisLevel.signum() == 0)
						costThisLevel = getCostThisLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
				}
				//Get cost from movement from if it > that zero replace cost This Level
				if (model instanceof MMovementLine) {
					MTransaction transactionFrom = MTransaction.getByDocumentLine(model,
							MTransaction.MOVEMENTTYPE_MovementFrom);					
					BigDecimal costMovementFrom = getCostThisLevel(accountSchema, costType, costElement, transactionFrom == null ? transaction : transactionFrom, model,costingLevel);
					if (costMovementFrom.signum() > 0 )
						costThisLevel = costMovementFrom;					
				}
			} else if (MCostElement.COSTELEMENTTYPE_Material.equals(costElement
					.getCostElementType())) {
				costThisLevel = model.getPriceActual();
			}
		}

		if (!MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod())) {
			if (model instanceof MPPCostCollector) {
				MPPCostCollector costCollector = (MPPCostCollector) model;
				if (MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt
						.equals(costCollector.getCostCollectorType())) {
					// get Actual Cost for Cost Type and Cost Element
					costLowLevel = CostEngine.getParentActualCostByCostType(
                            accountSchema, costType.getM_CostType_ID(), costElement.getM_CostElement_ID(), costCollector.getPP_Order()
                    );
				}
			}
			if (model instanceof MProductionLine) {
				MProductionLine productionLine = (MProductionLine) model;
				costThisLevel = CostEngine.getParentActualCostByCostType(
                        accountSchema, costType.getM_CostType_ID(), costElement.getM_CostElement_ID(), productionLine
                );
				
				if(costThisLevel.signum() == 0)
					costThisLevel = cost.getCurrentCostPrice();
					if(costThisLevel.signum() == 0 
					&& MCostElement.COSTELEMENTTYPE_Material.equals(costElement
							.getCostElementType()))
						costThisLevel = getSeedCost(
                                transaction.getCtx(),
                                transaction.getM_Product_ID(),
                                transaction.get_TableName());
						
				// Material Receipt for Production light
				if (productionLine.isParent()) {
					// get Actual Cost for Cost Type and Cost Element
					// if the product is purchase then no use low level 
					if (!productionLine.getM_Product().isPurchased())
					{	
						costLowLevel  = costThisLevel;
						costThisLevel = Env.ZERO;
					} 
				} else if ( productionLine.getMovementQty().signum() < 0)
					costThisLevel= Env.ZERO;
			}
		}

		final ICostingMethod method = CostingMethodFactory.get()
				.getCostingMethod(costType.getCostingMethod());
		method.setCostingMethod(accountSchema, transaction, model, cost, costThisLevel,
				costLowLevel, model.isSOTrx());
        method.process();
	}



	public void createCostDetail(MAcctSchema accountSchema, CostComponent costCollector, IDocumentLine model,
                                 Boolean isSOTrx, boolean setProcessed) {
		final String idColumnName = model.get_TableName() + "_ID";
		final String trxName = model.get_TrxName();

		// Delete Unprocessed zero Differences
		String sql = "DELETE M_CostDetail "
				+ "WHERE Processed='N' AND COALESCE(DeltaAmt,0)=0 AND COALESCE(DeltaQty,0)=0"
				+ " AND " + idColumnName + "=?" + " AND C_AcctSchema_ID=?"
				+ " AND M_AttributeSetInstance_ID=?";
		if (isSOTrx != null) {
			sql += " AND " + I_M_CostDetail.COLUMNNAME_IsSOTrx + "="
					+ (isSOTrx ? "'Y'" : "'N'");
		}
		int no = DB.executeUpdateEx(
				sql,
				new Object[] { model.get_ID(), accountSchema.getC_AcctSchema_ID(),
						model.getM_AttributeSetInstance_ID() }, trxName);
		if (no != 0)
			log.config("Deleted #" + no);

		// Build Description string
		StringBuilder description = new StringBuilder();
		if (!Util.isEmpty(model.getDescription(), true))
			description.append(model.getDescription());
		if (isSOTrx != null) {
			description.append(isSOTrx ? "(|->)" : "(|<-)");
		}

		List<MCost> costs = MCost.getForProduct(accountSchema, model);
		for (MCost cost : costs) {
			final MCostElement ce = MCostElement.get(cost.getCtx(),
					cost.getM_CostElement_ID());
			if (MCostElement.COSTELEMENTTYPE_LandedCost.equals(ce
					.getCostElementType())) {
				// skip landed costs
				continue;
			}

			final ICostingMethod method = CostingMethodFactory.get()
					.getCostingMethod(cost.getCostingMethod());
			method.setCostingMethod(accountSchema, null, model, cost,
					model.getPriceActual(), Env.ZERO, isSOTrx);

            method.process();
		}
	}

	//Create cost detail for by document
	public void createCostDetailForLandedCostAllocation(
			MLandedCostAllocation allocation) {
		MInOutLine ioLine = (MInOutLine) allocation.getM_InOutLine();
		
		for (MTransaction transaction : MTransaction.getByInOutLine(ioLine)) {
			for (MAcctSchema accountSchema : MAcctSchema.getClientAcctSchema(
					allocation.getCtx(), allocation.getAD_Client_ID())) {
				List<MCostType> costTypes = MCostType.get(allocation.getCtx(),
						allocation.get_TrxName());

				for (MCostType costType : costTypes) {
					MCostElement costElement = (MCostElement) allocation
							.getM_CostElement();
					CostEngineFactory.getCostEngine(
							allocation.getAD_Client_ID()).createCostDetail(accountSchema, costType, costElement, transaction, allocation, true);
				}
			}
		}
	}

	/*public static int deleteCostDetail(MAcctSchema accountSchema, int costElementId, int attributeSetInstanceId, IDocumentLine model) {
		// Delete Unprocessed zero Differences
		String sql = "DELETE "
				+ MCostDetail.Table_Name
				+ " WHERE Processed='N' AND COALESCE(DeltaAmt,0)=0 AND COALESCE(DeltaQty,0)=0"
				+ " AND " + model.get_TableName() + "_ID=?" + " AND "
				+ MCostDetail.COLUMNNAME_C_AcctSchema_ID + "=?" + " AND "
				+ MCostDetail.COLUMNNAME_M_AttributeSetInstance_ID + "=?"
				// + " AND "+MCostDetail.COLUMNNAME_M_CostType_ID+"=?"
				+ " AND " + MCostDetail.COLUMNNAME_M_CostElement_ID + "=?";
		Object[] parameters = new Object[] { model.get_ID(),
				accountSchema.getC_AcctSchema_ID(), attributeSetInstanceId,
				// as.getM_CostType_ID(),
                costElementId};

		int no = DB.executeUpdateEx(sql, parameters, model.get_TrxName());
		//if (no != 0)
		//	log.config("Deleted #" + no);
		return no;
	}*/



	public static boolean isActivityControlElement(I_M_CostElement element) {
		String costElementType = element.getCostElementType();
		return MCostElement.COSTELEMENTTYPE_Resource.equals(costElementType)
				|| MCostElement.COSTELEMENTTYPE_Overhead
						.equals(costElementType)
				|| MCostElement.COSTELEMENTTYPE_BurdenMOverhead
						.equals(costElementType);
	}

	public static List<MAcctSchema> getAcctSchema(PO po) {
		int AD_Org_ID = po.getAD_Org_ID();
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(po.getCtx(),
				po.getAD_Client_ID());
		ArrayList<MAcctSchema> list = new ArrayList<MAcctSchema>(ass.length);
		for (MAcctSchema as : ass) {
			if (!as.isSkipOrg(AD_Org_ID))
				list.add(as);
		}
		return list;
	}

	static public String getIDColumnName(IDocumentLine model) {
		String idColumnName = model.get_TableName() + "_ID";
		if (model instanceof MMatchPO) {
			idColumnName = I_C_OrderLine.COLUMNNAME_C_OrderLine_ID;
		}
		if (model instanceof MMatchInv) {
			idColumnName = I_C_InvoiceLine.COLUMNNAME_C_InvoiceLine_ID;
		}
		return idColumnName;
	}

	static public int getIDColumn(IDocumentLine model) {
		int id = model.get_ID();

		if (model instanceof MMatchPO) {
			id = ((MMatchPO) model).getC_OrderLine_ID();
		}
		if (model instanceof MMatchInv) {
			id = ((MMatchInv) model).getC_InvoiceLine_ID();
		}
		return id;
	}


    /**
     * get cost this level
     * @param accountSchema
     * @param costType
     * @param costElement
     * @param transaction
     * @param model
     * @param costingLevel
     * @return
     */
	public static BigDecimal getCostThisLevel(MAcctSchema accountSchema, I_M_CostType costType, I_M_CostElement costElement, MTransaction transaction,
                                              IDocumentLine model,
                                              String costingLevel) {
		BigDecimal costThisLevel = Env.ZERO;
		MCostDetail lastCostDetail = MCostDetail.getLastTransaction(model,
                transaction, accountSchema.getC_AcctSchema_ID(), costType.getM_CostType_ID(),
                costElement.getM_CostElement_ID(), model.getDateAcct(), costingLevel);
		if (lastCostDetail != null) {
			
				// Return of unit cost from last transaction 
				// transaction quantity is different of zero
				// then cost this level is equal that:
				// (Total Cost transaction + cost adjustments) divide by transaction quantity
				if (lastCostDetail.getQty().signum() != 0)
				{
					costThisLevel =  lastCostDetail.getCostAmt().add(
							lastCostDetail.getCostAdjustment())
							.divide(lastCostDetail.getQty(), accountSchema.getCostingPrecision(),
									BigDecimal.ROUND_HALF_UP).abs();
				}
				// return unit cost from last transaction
				// transaction quantity is zero
				// the cost this level is equal that:
				// (Total Cost Transaction + cost adjustments + accumulate cost) divide between on hand quantity 
				else if (lastCostDetail.getCumulatedQty().add( lastCostDetail.getQty()).signum() != 0)
				{
					costThisLevel =  lastCostDetail.getCostAmt().add(
									 lastCostDetail.getCostAdjustment()).add(
									 lastCostDetail.getCumulatedAmt())
							.divide(lastCostDetail.getCumulatedQty().add( lastCostDetail.getQty()),
									accountSchema.getCostingPrecision(),
							BigDecimal.ROUND_HALF_UP).abs();
					
					return costThisLevel;
				}	
				// Return of unit cost from inventory value
				// Cumulated quantity is different of zero 
				// then cost this level is equal that:
				// (Total Cost transaction + cost adjustments + Cumulated amount) divide by On hand Quantity
				else if (lastCostDetail.getCumulatedQty().signum() != 0)
				{
					costThisLevel = lastCostDetail.getCumulatedAmt()
							.divide(lastCostDetail.getCumulatedQty(),
									accountSchema.getCostingPrecision(),
							BigDecimal.ROUND_HALF_UP).abs();
					
					return costThisLevel;
				}	
				
			}

		return costThisLevel;
	}

    /**
     * clear Accounting
     * @param accountSchema
     * @param transaction
     */
	public void clearAccounting(MAcctSchema accountSchema , MTransaction transaction) {

			if (transaction.getM_InOutLine_ID() > 0) {
				MInOutLine line = (MInOutLine) transaction.getM_InOutLine();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),
                        line.getDateAcct()))
					return;

				// get Purchase matches
				List<MMatchPO> orderMatches = MMatchPO.getInOutLine(line);
				for (MMatchPO match : orderMatches) {
					if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , match, line.getDateAcct()))
							return;
				}

				// get invoice matches
				List<MMatchInv> invoiceMatches = MMatchInv.getInOutLine(line);
				for (MMatchInv match : invoiceMatches) {
					if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , match, line.getDateAcct()))
						return;
				}

			}
			else if (transaction.getC_ProjectIssue_ID() > 0) {
				MProjectIssue line = (MProjectIssue) transaction
						.getC_ProjectIssue();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),
                        line.getMovementDate()))
					return;
			}

			else if (transaction.getM_InventoryLine_ID() > 0) {
				MInventoryLine line = (MInventoryLine) transaction
						.getM_InventoryLine();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),
                        line.getDateAcct()))
					return;
			}
			else if (transaction.getM_MovementLine_ID() > 0) {
				MMovementLine line = (MMovementLine) transaction
						.getM_MovementLine();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),
                        line.getDateAcct()))
					return;
			}
			
			else if (transaction.getM_ProductionLine_ID() > 0) {
				MProductionLine line = (MProductionLine) transaction
						.getM_ProductionLine();
				MProduction production = (MProduction) line
						.getM_ProductionPlan().getM_Production();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , production,
                        production.getMovementDate()))
					return;

			}
			else
			{
				System.out.println ("Document no implementado :" + transaction);
			}	

	}

    /**
     * Clear Accounting
     * @param accountSchema
     * @param costType
     * @param model
     * @param dateAcct
     * @return true clean
     */
	public boolean clearAccounting(MAcctSchema accountSchema, I_M_CostType costType, PO model, Timestamp dateAcct) {
		
		// check if costing type need reset accounting 
		if (!accountSchema.getCostingMethod().equals(costType.getCostingMethod()))
				return false;
		final String  docBaseType;
		// check if account period is open
		if(model instanceof MMatchInv)
			docBaseType = MPeriodControl.DOCBASETYPE_MatchInvoice;
		else if (model instanceof MMatchPO)
			docBaseType = MPeriodControl.DOCBASETYPE_MatchPO;
		else
		{		
			MDocType dt = MDocType.get(model.getCtx(), model.get_ValueAsInt(MDocType.COLUMNNAME_C_DocType_ID));
			docBaseType = dt.getDocBaseType();
		}
			
		Boolean openPeriod = MPeriod.isOpen(model.getCtx(), dateAcct , docBaseType ,  model.getAD_Org_ID());
		if (!openPeriod)
		{	
			System.out.println("Periodo Cerrado no se puede restablecer al contabilidad para este documento.");
			return false;
		}	

		final String sqlUpdate = "UPDATE " + model.get_TableName() + " SET Posted = 'N' WHERE "+ model.get_TableName() + "_ID=?";
		DB.executeUpdate(sqlUpdate, new Object[] {model.get_ID()}, false , model.get_TrxName());
		//Delete account
		final String sqldelete = "DELETE FROM Fact_Acct WHERE Record_ID =? AND AD_Table_ID=?";		
		DB.executeUpdate (sqldelete ,new Object[] { model.get_ID(),
				model.get_Table_ID() }, false , model.get_TrxName());
		return true;
	}
}
