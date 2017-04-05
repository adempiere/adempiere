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
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_C_ProjectIssue;
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
import org.compiere.model.MConversionRate;
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
import org.compiere.model.MProductCategoryAcct;
import org.compiere.model.MProductPO;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MTransaction;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_M_Product;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.model.I_PP_Cost_Collector;
import org.eevolution.model.MPPCostCollector;


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
     * @param costCollector
     * @return
     */
	public static BigDecimal getParentActualCostByCostType(MAcctSchema accountSchema, int costTypeId, int costElementId, MPPCostCollector costCollector) {
		StringBuffer whereClause = new StringBuffer()
		.append(MCostDetail.COLUMNNAME_C_AcctSchema_ID).append("=? AND ")
		.append(MCostDetail.COLUMNNAME_M_CostType_ID + "=? AND ")
		.append(MCostDetail.COLUMNNAME_M_CostElement_ID + "=? AND ")
		.append(MCostDetail.COLUMNNAME_PP_Cost_Collector_ID)
		.append(" IN (SELECT PP_Cost_Collector_ID FROM PP_Cost_Collector cc WHERE cc.PP_Order_ID=? AND ")
		.append(" cc.CostCollectorType <> '").append(MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt).append("')");

		BigDecimal actualCost = new Query(costCollector.getCtx(), MCostDetail.Table_Name, whereClause.toString(), costCollector.get_TrxName())
				.setClient_ID()
				.setParameters(accountSchema.getC_AcctSchema_ID() , costTypeId, costElementId, costCollector.getPP_Order_ID())
				.sum("(" + MCostDetail.COLUMNNAME_Amt + "+" + MCostDetail.COLUMNNAME_CostAmtLL + ")");

		whereClause = new StringBuffer();
		whereClause
				.append(" EXISTS (SELECT 1 FROM PP_Cost_Collector cc ")
				.append(" WHERE PP_Cost_Collector_ID=M_Transaction.PP_Cost_Collector_ID AND cc.PP_Order_ID=? AND cc.M_Product_ID=? )");
		BigDecimal qtyDelivered = new Query(costCollector.getCtx(), I_M_Transaction.Table_Name, whereClause.toString(), costCollector.get_TrxName())
				.setClient_ID()
				.setParameters(costCollector.getPP_Order_ID(), costCollector.getM_Product_ID())
				.sum(MTransaction.COLUMNNAME_MovementQty);

		if (actualCost == null)
			actualCost = Env.ZERO;

		if (qtyDelivered.signum() != 0)
			actualCost = actualCost.divide(qtyDelivered,
					accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_DOWN);

		BigDecimal rate = MConversionRate.getRate(
				costCollector.getC_Currency_ID(), costCollector.getC_Currency_ID(),
				costCollector.getDateAcct(), costCollector.getC_ConversionType_ID(),
				costCollector.getAD_Client_ID(), costCollector.getAD_Org_ID());
		if (rate != null) {
			actualCost = actualCost.multiply(rate);
			if (actualCost.scale() > accountSchema.getCostingPrecision())
				actualCost = actualCost.setScale(accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
		}

		return actualCost;
	}

	public static BigDecimal getParentActualCostByCostType(MAcctSchema accountSchema, MCostType costType,
			MCostElement costElement, I_M_Production production) {
		//	Get BOM Cost - Sum of individual lines
		BigDecimal totalCost = Env.ZERO;
		for (MProductionLine productionLine : ((MProduction)production).getLines())
		{
			if (productionLine.isParent())
				continue;

			String productType = productionLine.getM_Product().getProductType();

			BigDecimal cost = BigDecimal.ZERO;

			if (X_M_Product.PRODUCTTYPE_Item.equals(productType)) {
				cost = MCostDetail.getCostByModel(accountSchema.getC_AcctSchema_ID(), costType.getM_CostType_ID() , costElement.getM_CostElement_ID() , productionLine);
			}
			else if(X_M_Product.PRODUCTTYPE_Resource.equals(productType))
			{
				MCost costDimension = MCost.validateCostForCostType(accountSchema, costType, costElement,
						productionLine.getM_Product_ID(), productionLine.getAD_Org_ID(), productionLine.getM_Locator().getM_Warehouse_ID(),
						productionLine.getM_AttributeSetInstance_ID(), productionLine.get_TrxName());

				if (costDimension != null && costDimension.getCurrentCostPrice().signum() != 0 )
					cost = costDimension.getCurrentCostPrice().multiply(productionLine.getMovementQty().negate());
			}


			if (cost != null && cost.signum() != 0)
				totalCost = totalCost.add(cost);

		}

		BigDecimal unitCost = Env.ZERO;
		if (production.getProductionQty().signum() != 0 && totalCost.signum() != 0)
			unitCost = totalCost.divide(production.getProductionQty() , accountSchema.getCostingPrecision() , BigDecimal.ROUND_HALF_UP);

		return unitCost;
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

	/**
	 * Generate by transaction
	 * @param transaction
	 */
	public void createCostDetail(MTransaction transaction, IDocumentLine model) {

		MClient client = new MClient (transaction.getCtx() , transaction.getAD_Client_ID(), transaction.get_TrxName());
		StringBuilder description = new StringBuilder();
		if (model != null && model.getDescription() != null && !Util.isEmpty(model.getDescription(), true))
			description.append(model.getDescription());
		if (model != null) {
			description.append(model.isSOTrx() ? "(|->)" : "(|<-)");
		}

		List<MAcctSchema> acctSchemas = new ArrayList(Arrays.asList(MAcctSchema
				.getClientAcctSchema(transaction.getCtx(), transaction.getAD_Client_ID(),
						transaction.get_TrxName())));

		List<MCostElement> costElements = MCostElement.getCostElement(transaction.getCtx(),
				transaction.get_TrxName());
		List<MCostType> costTypes = MCostType.get(transaction.getCtx(), transaction.get_TrxName());
		for (MAcctSchema accountSchema : acctSchemas) {
			for (MCostType costType : costTypes) {
				if (!costType.isActive())
					continue;
				for (MCostElement costElement : costElements) {
					createCostDetail(accountSchema, costType, costElement, transaction, model, client.isCostImmediate());
				}
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
			costThisLevel = convertCostToSchemaCurrency(accountSchema, model , model.getPriceActualCurrency());
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
				if (model instanceof MInventoryLine) {
					MInventoryLine inventoryLine = (MInventoryLine) model;
					// If cost this level is zero and is a physical inventory then
					// try get cost from physical inventory
					if (costThisLevel.signum() == 0 && MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
						// Use the current cost only for Physical Inventory
						if (inventoryLine.getQtyInternalUse().signum() == 0 &&
								inventoryLine.getCurrentCostPrice() != null &&
								inventoryLine.getCurrentCostPrice().signum() > 0) {
							costThisLevel = convertCostToSchemaCurrency(accountSchema, model, model.getPriceActualCurrency());
						}
						if (costThisLevel.signum() == 0)
							costThisLevel = getCostThisLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
					}

					costLowLevel = getCostLowLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
					// If cost Low level is zero and is a physical inventory then
					// try get cost low level from physical inventory
					if (costLowLevel.signum() == 0 && MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
						// Use the current cost only for Physical Inventory
						if (inventoryLine.getQtyInternalUse().signum() == 0 &&
								inventoryLine.getCurrentCostPriceLL() != null &&
								inventoryLine.getCurrentCostPriceLL().signum() > 0) {
							costLowLevel = convertCostToSchemaCurrency(accountSchema, model, inventoryLine.getCurrentCostPriceLL());
						}
						if (costLowLevel.signum() == 0)
							costLowLevel = getCostLowLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
					}
				}


				//Get cost from movement from if it > that zero replace cost This Level
				if (model instanceof MMovementLine) {
					MTransaction transactionFrom = MTransaction.getByDocumentLine(model, MTransaction.MOVEMENTTYPE_MovementFrom);
					BigDecimal costMovementFrom = getCostThisLevel(accountSchema, costType, costElement, transactionFrom == null ? transaction : transactionFrom, model,costingLevel);
					if (costMovementFrom.signum() > 0 )
						costThisLevel = costMovementFrom;

					BigDecimal costMovementFromLL = getCostLowLevel(accountSchema, costType, costElement, transactionFrom == null ? transaction : transactionFrom, model,costingLevel);
					if (costMovementFromLL.signum() > 0 )
						costLowLevel = costMovementFromLL;
				}
			} else if (MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
					costThisLevel = convertCostToSchemaCurrency(accountSchema , model , model.getPriceActualCurrency());
			}
		}

		if (!MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod())) {
			if (model instanceof MPPCostCollector) {
				MPPCostCollector costCollector = (MPPCostCollector) model;
				if (MPPCostCollector.COSTCOLLECTORTYPE_MaterialReceipt.equals(costCollector.getCostCollectorType())) {
					// get Actual Cost for Cost Type and Cost Element
					costThisLevel = getCostThisLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
					costLowLevel = CostEngine.getParentActualCostByCostType(accountSchema, costType.getM_CostType_ID(), costElement.getM_CostElement_ID(), costCollector);
				} 
			}
			if (model instanceof MProductionLine) {
				
				MProductionLine productionLine = (MProductionLine) model;
				if (productionLine.isParent())
						costThisLevel = CostEngine.getParentActualCostByCostType(
								accountSchema, costType, costElement, productionLine.getM_Production());
				
				if(costThisLevel.signum() == 0)
					costThisLevel = cost.getCurrentCostPrice();
					if(costThisLevel.signum() == 0 
					&& MCostElement.COSTELEMENTTYPE_Material.equals(costElement
							.getCostElementType()))
						costThisLevel = getSeedCost(
                                transaction.getCtx(),
                                transaction.getM_Product_ID(),
                                transaction.get_TrxName());
						
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
					costLowLevel= Env.ZERO;
			}
		}
        else if (MCostType.COSTINGMETHOD_StandardCosting.equals(costType.getCostingMethod())){
			costThisLevel = cost.getCurrentCostPrice();
			costLowLevel = cost.getCurrentCostPriceLL();
			//Define Cost Inventory Line
			if (model instanceof MInventoryLine) {
				MInventoryLine inventoryLine = (MInventoryLine) model;
				//Define Current Cost Level
				if (costThisLevel.signum() == 0 && MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
					// Use the current cost only for Physical Inventory
					if (inventoryLine.getQtyInternalUse().signum() == 0 &&
							inventoryLine.getCurrentCostPrice() != null &&
							inventoryLine.getCurrentCostPrice().signum() > 0) {
						costThisLevel = convertCostToSchemaCurrency(accountSchema, model, model.getPriceActualCurrency());
						cost.setCurrentCostPrice(costThisLevel);
						cost.saveEx();
					}

					if (costThisLevel.signum() == 0)
						costThisLevel = getCostThisLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
				}
				//Define Current Cost Low Level
				if (costLowLevel.signum() == 0 && MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
					// Use the cost only for Physical Inventory
					if (inventoryLine.getQtyInternalUse().signum() == 0 &&
							inventoryLine.getCurrentCostPriceLL() != null &&
							inventoryLine.getCurrentCostPriceLL().signum() > 0) {
						costLowLevel = convertCostToSchemaCurrency(accountSchema, model, inventoryLine.getCurrentCostPriceLL());

						cost.setCurrentCostPriceLL(costLowLevel);
						cost.saveEx();
					}

					if (costLowLevel.signum() == 0)
						costLowLevel = getCostLowLevel(accountSchema, costType, costElement, transaction, model, costingLevel);
				}
			}

			if (model instanceof  MMovementLine) {
				MTransaction transactionFrom = MTransaction.getByDocumentLine(model, MTransaction.MOVEMENTTYPE_MovementFrom);
				BigDecimal costMovementFrom = getCostThisLevel(accountSchema, costType, costElement, transactionFrom == null ? transaction : transactionFrom, model, costingLevel);
				if (costThisLevel.signum() == 0 && MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
					if (costMovementFrom.signum() > 0)
						costThisLevel = costMovementFrom;
				}
				if (costLowLevel.signum() == 0 && MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
					BigDecimal costMovementFromLL = getCostLowLevel(accountSchema, costType, costElement, transactionFrom == null ? transaction : transactionFrom, model, costingLevel);
					if (costMovementFromLL.signum() > 0)
						costLowLevel = costMovementFromLL;
				}
			}

            if (costThisLevel.signum() == 0 &&  MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType())) {
                costThisLevel = getSeedCost(transaction.getCtx(), transaction.getM_Product_ID(), transaction.get_TrxName());
                if (costThisLevel.signum() == 0)
                    if (model instanceof  MInOutLine && !model.isSOTrx()) {
							costThisLevel = convertCostToSchemaCurrency(accountSchema , model , model.getPriceActualCurrency());
                    }
                if (costThisLevel.signum() != 0) {
                    cost.setCurrentCostPrice(costThisLevel);
                    cost.saveEx();
                }
            }

			if (costLowLevel.signum() != 0) {
				cost.setCurrentCostPriceLL(costLowLevel);
				cost.saveEx();
			}
        }

		final ICostingMethod method = CostingMethodFactory.get()
				.getCostingMethod(costType.getCostingMethod());
		method.setCostingMethod(accountSchema, transaction, model, cost, costThisLevel,
				costLowLevel, model.isSOTrx());
        method.process();
	}


	/**
	 * Convert Cost To Schema Currency
	 * @param acctSchema
	 * @param model
	 * @param cost
	 * @return
	 */
	private BigDecimal convertCostToSchemaCurrency(MAcctSchema acctSchema , IDocumentLine model , BigDecimal cost)
	{
		BigDecimal costThisLevel = BigDecimal.ZERO;
		BigDecimal rate = MConversionRate.getRate(
				model.getC_Currency_ID(), acctSchema.getC_Currency_ID() ,
				model.getDateAcct(), model.getC_ConversionType_ID() ,
				model.getAD_Client_ID(), model.getAD_Org_ID());
		if (rate != null) {
			costThisLevel = cost.multiply(rate);
			if (costThisLevel.scale() > acctSchema.getCostingPrecision())
				costThisLevel = costThisLevel.setScale(acctSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
		}
		return costThisLevel;
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

	public static boolean isActivityControlElement(I_M_CostElement element) {
		String costElementType = element.getCostElementType();
		return MCostElement.COSTELEMENTTYPE_Resource.equals(costElementType)
				|| MCostElement.COSTELEMENTTYPE_Overhead.equals(costElementType)
				|| MCostElement.COSTELEMENTTYPE_BurdenMOverhead.equals(costElementType)
				|| MCostElement.COSTELEMENTTYPE_OutsideProcessing.equals(costElementType);
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
	 * get cost this level
	 * @param accountSchema
	 * @param costType
	 * @param costElement
	 * @param transaction
	 * @param model
	 * @param costingLevel
	 * @return
	 */
	public static BigDecimal getCostLowLevel(MAcctSchema accountSchema, I_M_CostType costType, I_M_CostElement costElement, MTransaction transaction,
											  IDocumentLine model,
											  String costingLevel) {
		BigDecimal costLowLevel = Env.ZERO;
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
				costLowLevel =  lastCostDetail.getCostAmtLL().add(lastCostDetail.getCostAdjustmentLL())
						.divide(lastCostDetail.getQty(), accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP).abs();
			}
			// return unit cost from last transaction
			// transaction quantity is zero
			// the cost this level is equal that:
			// (Total Cost Transaction + cost adjustments + accumulate cost) divide between on hand quantity
			else if (lastCostDetail.getCumulatedQty().add( lastCostDetail.getQty()).signum() != 0)
			{
				costLowLevel =  lastCostDetail.getCostAmtLL().add(lastCostDetail.getCostAdjustmentLL()).add(lastCostDetail.getCumulatedAmtLL())
						.divide(lastCostDetail.getCumulatedQty().add( lastCostDetail.getQty()),
								accountSchema.getCostingPrecision(),
								BigDecimal.ROUND_HALF_UP).abs();

				return costLowLevel;
			}
			// Return of unit cost from inventory value
			// Cumulated quantity is different of zero
			// then cost this level is equal that:
			// (Total Cost transaction + cost adjustments + Cumulated amount) divide by On hand Quantity
			else if (lastCostDetail.getCumulatedQty().signum() != 0)
			{
				costLowLevel = lastCostDetail.getCumulatedAmtLL()
						.divide(lastCostDetail.getCumulatedQty(), accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP).abs();
				return costLowLevel;
			}

		}

		return costLowLevel;
	}

    /**
     * clear Accounting
     * @param accountSchema
     * @param transaction
     */
	public void clearAccounting(MAcctSchema accountSchema , MTransaction transaction) {

			if (transaction.getM_InOutLine_ID() > 0) {
				MInOutLine line = (MInOutLine) transaction.getM_InOutLine();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent() , transaction.getM_Product_ID() , line.getDateAcct()))
					return;

				// get Purchase matches
				List<MMatchPO> orderMatches = MMatchPO.getInOutLine(line);
				for (MMatchPO match : orderMatches) {
					if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , match,  transaction.getM_Product_ID() , line.getDateAcct()))
							return;
				}

				// get invoice matches
				List<MMatchInv> invoiceMatches = MMatchInv.getInOutLine(line);
				for (MMatchInv match : invoiceMatches) {
					if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , match,  transaction.getM_Product_ID() , line.getDateAcct()))
						return;
				}

			}
			else if (transaction.getC_ProjectIssue_ID() > 0) {
				MProjectIssue line = (MProjectIssue) transaction.getC_ProjectIssue();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),  transaction.getM_Product_ID() , line.getMovementDate()))
					return;
			}

			else if (transaction.getM_InventoryLine_ID() > 0) {
				MInventoryLine line = (MInventoryLine) transaction.getM_InventoryLine();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),  transaction.getM_Product_ID() , line.getDateAcct()))
					return;
			}
			else if (transaction.getM_MovementLine_ID() > 0) {
				MMovementLine line = (MMovementLine) transaction.getM_MovementLine();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , line.getParent(),  transaction.getM_Product_ID() , line.getDateAcct()))
					return;
			}
			
			else if (transaction.getM_ProductionLine_ID() > 0) {
				MProductionLine line = (MProductionLine) transaction.getM_ProductionLine();
				MProduction production = (MProduction) line.getM_ProductionPlan().getM_Production();
				if (!clearAccounting(accountSchema, accountSchema.getM_CostType() , production,  transaction.getM_Product_ID()  , production.getMovementDate()))
					return;

			}
			else if (transaction.getPP_Cost_Collector_ID() > 0)
			{
				MPPCostCollector costCollector = (MPPCostCollector) transaction.getPP_Cost_Collector();
				if(!clearAccounting(accountSchema, accountSchema.getM_CostType() , costCollector , costCollector.getM_Product_ID() , costCollector.getDateAcct()));
				return;
			}
			else
			{
				log.info("Document does not exist :" + transaction);
			}	

	}

    /**
     * Clear Accounting
     * @param accountSchema
     * @param costType
     * @param model
	 * @param productId
     * @param dateAcct
     * @return true clean
     */
	public boolean clearAccounting(MAcctSchema accountSchema, I_M_CostType costType, PO model, int productId , Timestamp dateAcct) {
		// check if costing type need reset accounting 
		if (!accountSchema.getCostingMethod().equals(costType.getCostingMethod())) {
			MProduct product = MProduct.get(accountSchema.getCtx() , productId);
			MProductCategoryAcct productCategoryAcct = MProductCategoryAcct.get(accountSchema.getCtx(), product.getM_Product_Category_ID(), accountSchema.get_ID(), model.get_TrxName());
			if (productCategoryAcct == null || !costType.getCostingMethod().equals(productCategoryAcct.getCostingMethod()))
				return false;
		}
		final String  docBaseType;
		// check if account period is open
		if(model instanceof MMatchInv)
			docBaseType = MPeriodControl.DOCBASETYPE_MatchInvoice;
		else if (model instanceof MMatchPO)
			docBaseType = MPeriodControl.DOCBASETYPE_MatchPO;
		else if (model instanceof MProduction)
			docBaseType = MPeriodControl.DOCBASETYPE_MaterialProduction;
		else
		{		
			MDocType docType = MDocType.get(model.getCtx(), model.get_ValueAsInt(MDocType.COLUMNNAME_C_DocType_ID));
			docBaseType = docType.getDocBaseType();
		}
			
		Boolean openPeriod = MPeriod.isOpen(model.getCtx(), dateAcct , docBaseType ,  model.getAD_Org_ID());
		if (!openPeriod)
		{	
			System.out.println("Period closed.");
			return false;
		}	

		final String sqlUpdate = "UPDATE " + model.get_TableName() + " SET Posted = 'N' WHERE "+ model.get_TableName() + "_ID=?";
		DB.executeUpdate(sqlUpdate, new Object[] {model.get_ID()}, false , model.get_TrxName());
		//Delete account
		final String sqldelete = "DELETE FROM Fact_Acct WHERE Record_ID =? AND AD_Table_ID=?";		
		DB.executeUpdate (sqldelete ,new Object[] { model.get_ID(), model.get_Table_ID() }, false , model.get_TrxName());
		return true;
	}
}
