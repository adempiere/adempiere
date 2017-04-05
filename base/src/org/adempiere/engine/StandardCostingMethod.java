/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.model.I_PP_Order_BOMLine;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.MPPOrderCost;
import org.eevolution.model.RoutingService;
import org.eevolution.model.RoutingServiceFactory;

/**
 * @author anca_bradau
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class StandardCostingMethod extends AbstractCostingMethod implements
		ICostingMethod {

	public void setCostingMethod(MAcctSchema accountSchema, MTransaction transaction, IDocumentLine model,
                                 MCost dimension, BigDecimal costThisLevel,
                                 BigDecimal costLowLevel, Boolean isSalesTransaction) {
		this.accountSchema = accountSchema;
		this.transaction = transaction;
		this.dimension = dimension;
		this.costThisLevel = (costThisLevel == null ? Env.ZERO : costThisLevel);
		this.costLowLevel = (costLowLevel == null ? Env.ZERO : costLowLevel);
		this.isSalesTransaction = isSalesTransaction;
		this.model = transaction.getDocumentLine();
		this.costingLevel = MProduct.get(this.transaction.getCtx(), this.transaction.getM_Product_ID())
				.getCostingLevel(this.accountSchema, this.transaction.getAD_Org_ID());
		this.costDetail = MCostDetail.getByTransaction(this.model, this.transaction,
				this.accountSchema.getC_AcctSchema_ID(), this.dimension.getM_CostType_ID(),
				this.dimension.getM_CostElement_ID());
		this.movementQuantity = transaction.getMovementQty();
		//Setting Accounting period status
		MDocType documentType = new MDocType(transaction.getCtx(), transaction.getDocumentLine().getC_DocType_ID(), transaction.get_TrxName());
		this.isOpenPeriod = MPeriod.isOpen(transaction.getCtx(), model.getDateAcct() , documentType.getDocBaseType(), transaction.getAD_Org_ID());

		//Setting Date Accounting based on Open Period
		if (this.isOpenPeriod)
			this.dateAccounting = model.getDateAcct();
		else if (model instanceof MLandedCostAllocation )
			this.dateAccounting = ((MLandedCostAllocation) model).getC_InvoiceLine().getC_Invoice().getDateAcct();
		else if (model instanceof MMatchInv)
			this.dateAccounting = ((MMatchInv) model).getC_InvoiceLine().getC_Invoice().getDateAcct();
		else
			this.dateAccounting = null; // Is Necessary define that happen in this case when period is close
	}

	private void calculate() {
		if (model.getReversalLine_ID() > 0)
			return;

		// try find the last cost detail transaction
		lastCostDetail = MCostDetail.getLastTransaction(model, transaction,
				accountSchema.getC_AcctSchema_ID(), dimension.getM_CostType_ID(),
				dimension.getM_CostElement_ID(),dateAccounting,
				costingLevel);

		// created a new instance cost detail to process calculated cost
		if (lastCostDetail == null) {
			lastCostDetail = new MCostDetail(transaction,
					accountSchema.getC_AcctSchema_ID(), dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(), Env.ZERO, Env.ZERO,
					Env.ZERO, transaction.get_TrxName());
			lastCostDetail.setDateAcct(dateAccounting);
		}


		if (movementQuantity.signum() < 0) {
			currentCostPrice = dimension.getCurrentCostPrice();
			currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
			amount = movementQuantity.multiply(currentCostPrice);
			amountLowerLevel = movementQuantity
					.multiply(currentCostPriceLowerLevel);
			accumulatedQuantity = dimension.getCumulatedQty().add(
					movementQuantity);
			accumulatedAmount = dimension.getCumulatedAmt().add(amount);
			accumulatedAmountLowerLevel = dimension.getCumulatedAmtLL().add(amountLowerLevel);
			return;
		}

		if (costDetail != null) {
			amount = movementQuantity.multiply(
					costDetail.getCurrentCostPrice());
			amountLowerLevel = movementQuantity.multiply(
					costDetail.getCurrentCostPriceLL());
			accumulatedQuantity = costDetail.getCumulatedQty().add(
					movementQuantity);
			accumulatedAmount = costDetail.getCumulatedAmt().add(amount);
			accumulatedAmountLowerLevel = costDetail.getCumulatedAmtLL().add(amountLowerLevel);
			currentCostPrice = dimension.getCurrentCostPrice();
			currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
			adjustCost = currentCostPrice.multiply(
					dimension.getCumulatedQty()).subtract(
					dimension.getCumulatedAmt());
			adjustCost = currentCostPriceLowerLevel.multiply(
					dimension.getCumulatedQty()).subtract(
					dimension.getCumulatedAmtLL());
			return;
		}

		amount = movementQuantity.multiply(
				dimension.getCurrentCostPrice());
		amountLowerLevel = movementQuantity.multiply(
				dimension.getCurrentCostPriceLL());
		accumulatedAmount = dimension.getCumulatedAmt().add(amount)
				.add(adjustCost);
		accumulatedAmountLowerLevel = dimension.getCumulatedAmtLL().add(amountLowerLevel)
				.add(adjustCostLowerLevel);
		accumulatedQuantity = dimension.getCumulatedQty().add(
				movementQuantity);
		currentCostPrice = dimension.getCurrentCostPrice();
		currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
	}


	private void createCostDetail() {
		final String idColumnName = CostEngine.getIDColumnName(model);
		if (model.getReversalLine_ID() > 0) {
			createReversalCostDetail();
			return;
		}

		int seqNo = lastCostDetail.getSeqNo() + 10;

		if (model instanceof MPPCostCollector)
		{
			MPPCostCollector cc = (MPPCostCollector) model;
			if (MPPCostCollector.COSTCOLLECTORTYPE_MethodChangeVariance.equals(cc.getCostCollectorType())) {
				createMethodVariances(cc);
			} else if (MPPCostCollector.COSTCOLLECTORTYPE_UsegeVariance.equals(cc.getCostCollectorType())) {
				createUsageVariances(cc);
			} else if (MPPCostCollector.COSTCOLLECTORTYPE_RateVariance.equals(cc.getCostCollectorType())) {
				createRateVariances(cc);
			} else if (MPPCostCollector.COSTCOLLECTORTYPE_MixVariance.equals(cc.getCostCollectorType())) {
				; // no implement
			}
		}
		
		if (costDetail == null) {
            costDetail = new MCostDetail(transaction, accountSchema.getC_AcctSchema_ID(),
                    dimension.getM_CostType_ID(),
                    dimension.getM_CostElement_ID(), currentCostPrice
                    .multiply(movementQuantity).abs(),
                    currentCostPriceLowerLevel.multiply(movementQuantity).abs(),
                    movementQuantity, transaction.get_TrxName());
            costDetail.setDateAcct(dateAccounting);
            costDetail.setSeqNo(seqNo);

		}


        if (adjustCost.signum() != 0 || adjustCostLowerLevel.signum() != 0) {
            String description = costDetail.getDescription() != null ? costDetail
                    .getDescription() : "";
            // update adjustment cost this level
            if (adjustCost.signum() != 0) {
                costDetail.setCostAdjustmentDate(model.getDateAcct());
                costDetail.setCostAdjustment(adjustCost);
                //costDetail.setCostAmt(BigDecimal.ZERO);
                costDetail.setAmt(costDetail.getAmt().add(
                        costDetail.getCostAdjustment()));
                costDetail.setDescription(description + " Adjust Cost:"
                        + adjustCost);
            }
            // update adjustment cost lower level
            if (adjustCostLowerLevel.signum() != 0) {
                description = costDetail.getDescription() != null ? costDetail
                        .getDescription() : "";
                costDetail.setCostAdjustmentDateLL(model.getDateAcct());
                costDetail.setCostAdjustmentLL(adjustCostLowerLevel);
                //costDetail.setCostAmtLL(BigDecimal.ZERO);
                costDetail.setAmt(costDetail.getCostAmtLL().add(
                        costDetail.getCostAdjustmentLL()));
                costDetail.setDescription(description
                        + " Adjust Cost LL:" + adjustCost);
            }
        }

        if (!costDetail.set_ValueOfColumnReturningBoolean(idColumnName,
				model.get_ID()))
			throw new AdempiereException("Cannot set " + idColumnName);

        // set if transaction is sales order type or not
        if (isSalesTransaction != null)
            costDetail.setIsSOTrx(isSalesTransaction);
        else
            costDetail.setIsSOTrx(model.isSOTrx());

		// set transaction id
		if (transaction != null)
			costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());

		costDetail.setCumulatedQty(dimension.getCumulatedQty());
		costDetail.setCumulatedAmt(dimension.getCumulatedAmt());
		costDetail.setCumulatedAmtLL(dimension.getCumulatedAmtLL());
		costDetail.setCurrentCostPrice(dimension.getCurrentCostPrice());
		costDetail.setCurrentCostPriceLL(dimension.getCurrentCostPriceLL());
		StringBuilder description = new StringBuilder();
		if (!Util.isEmpty(model.getDescription(), true))
			description.append(model.getDescription());
		if (isSalesTransaction != null) {
			description.append(isSalesTransaction ? "(|->)" : "(|<-)");
		}
		if (transaction != null)
			costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
		costDetail.setDescription(description.toString());
		updateAmountCost();
		costDetail.saveEx();
		// CostAms

		return;
	}

	public void createCostAdjustment() {
	}

	public MCostDetail process() {
		calculate();
		createCostDetail();
		updateInventoryValue();
		createCostAdjustment();
		return costDetail;
	}

	/**
	 * Average Invoice Get the New Current Cost Price This Level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	public BigDecimal getNewCurrentCostPrice(MCostDetail cost, int scale, int roundingMode) {
		if (getNewAccumulatedQuantity(cost).signum() != 0 && getNewAccumulatedAmount(cost).signum() != 0)
			return getNewAccumulatedAmount(cost).divide(getNewAccumulatedQuantity(cost), scale, roundingMode);
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the New Cumulated Amt This Level
	 * @param cost Cost Detail
	 * @return New Cumulated Amt This Level
	 */
	public BigDecimal getNewAccumulatedAmount(MCostDetail cost) {
		BigDecimal accumulatedAmount = Env.ZERO;
		if (cost.getQty().signum() > 0)
			accumulatedAmount = cost.getCumulatedAmt().add(cost.getCostAmt()).add(cost.getCostAdjustment());
		else if (cost.getQty().signum() < 0)
			accumulatedAmount = cost.getCumulatedAmt().add(cost.getCostAmt().negate()).add(cost.getCostAdjustment().negate());
		else if (cost.getQty().signum() == 0)
		{
			if(getNewAccumulatedQuantity(cost).signum() > 0)
				accumulatedAmount = cost.getCumulatedAmt().add(cost.getCostAmt()).add(cost.getCostAdjustment());
			else if (getNewAccumulatedQuantity(cost).signum() < 0)
				accumulatedAmount = cost.getCumulatedAmt().add(cost.getCostAmt().negate()).add(cost.getCostAdjustment().negate());
		}
		return accumulatedAmount;
	}

	/**
	 * Average Invoice Get the New Current Cost Price low level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 */
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cost, int scale, int roundingMode) {
		if (getNewAccumulatedQuantity(cost).signum() != 0 && getNewAccumulatedAmountLowerLevel(cost).signum() != 0)
			return getNewAccumulatedAmountLowerLevel(cost).divide(getNewAccumulatedQuantity(cost), scale, roundingMode);
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the new Cumulated Amt Low Level
	 * @param cost MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail cost) {
		BigDecimal accumulatedAmountLowerLevel = Env.ZERO;
		if (cost.getQty().signum() >= 0)
			accumulatedAmountLowerLevel = cost.getCumulatedAmtLL().add(cost.getCostAmtLL()).add(cost.getCostAdjustmentLL());
		else
			accumulatedAmountLowerLevel = cost.getCumulatedAmtLL().add(cost.getCostAmtLL().negate()).add(cost.getCostAdjustmentLL().negate());
		return accumulatedAmountLowerLevel;
	}

	/**
	 * Average Invoice Get the new Cumulated Qty
	 * @param cost Cost Detail
	 * @return New Accumulated Quantity
	 */
	public BigDecimal getNewAccumulatedQuantity(MCostDetail cost) {
		    return cost.getCumulatedQty().add(cost.getQty());
	}

	/*public void processCostDetail(MCostDetail costDetail) {
		if (!costDetail.isProcessed()) {
			MAcctSchema as = MAcctSchema.get(costDetail.getCtx(),
					costDetail.getC_AcctSchema_ID());
			MClient client = MClient.get(as.getCtx(), as.getAD_Client_ID());
			if (client.isCostImmediate())
				costDetail.process();
		}
	}*/

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Update Cost Amt
	 */
	public void updateAmountCost() {

        if (movementQuantity.signum() > 0) {
            costDetail.setCostAmt(costDetail.getAmt().subtract(
                    costDetail.getCostAdjustment()));
            costDetail.setCostAmtLL(costDetail.getAmtLL().subtract(
                    costDetail.getCostAdjustmentLL()));
        }
        else if (movementQuantity.signum() < 0 ) {
            costDetail.setCostAmt(costDetail.getAmt().add(adjustCost));
            costDetail.setCostAmtLL(costDetail.getAmtLL().add(
                    adjustCostLowerLevel));
        }

        costDetail.setCumulatedQty(getNewAccumulatedQuantity(lastCostDetail));
        costDetail.setCumulatedAmt(getNewAccumulatedAmount(lastCostDetail));
        costDetail.setCumulatedAmtLL(getNewAccumulatedAmountLowerLevel(lastCostDetail));
        costDetail.setCurrentCostPrice(currentCostPrice);
        costDetail.setCurrentCostPriceLL(currentCostPriceLowerLevel);

        // set the id for model
        final String idColumnName = CostEngine.getIDColumnName(model);
        costDetail.set_ValueOfColumn(idColumnName,
                CostEngine.getIDColumn(model));

        if (model instanceof MInOutLine)
        {
            MInOutLine ioLine =  (MInOutLine) model;
            costDetail.setC_OrderLine_ID(ioLine.getC_OrderLine_ID());
            // IMPORTANT : reset possible provision purchase cost processed
            costDetail.setC_InvoiceLine_ID(0);
        }

        if (model instanceof MMatchInv && costDetail.getM_InOutLine_ID() == 0)
        {
            MMatchInv iMatch =  (MMatchInv) model;
            costDetail.setM_InOutLine_ID(iMatch.getM_InOutLine_ID());
        }
        if(model instanceof MMatchPO && costDetail.getM_InOutLine_ID() == 0)
        {
            MMatchPO poMatch =  (MMatchPO) model;
            costDetail.setM_InOutLine_ID(poMatch.getM_InOutLine_ID());
        }
        if (model instanceof MLandedCostAllocation) {
            MLandedCostAllocation allocation = (MLandedCostAllocation) model;
            costDetail.setM_InOutLine_ID(allocation.getM_InOutLine_ID());
            costDetail.setC_InvoiceLine_ID(allocation.getC_InvoiceLine_ID());
            costDetail.setC_LandedCostAllocation_ID(allocation.getC_LandedCostAllocation_ID());
            costDetail.setProcessed(false);
        }
        costDetail.saveEx();
	}

    /**
     * Update the Inventory Value based in last transaction
     */
    public void updateInventoryValue() {
        dimension.setCumulatedAmt(accumulatedAmount);
        dimension.setCumulatedAmtLL(accumulatedAmountLowerLevel);
        dimension.setCumulatedQty(accumulatedQuantity);
        dimension.setCurrentQty(accumulatedQuantity);
        dimension.saveEx();
    }
	
	public BigDecimal getResourceStandardCostRate(MPPCostCollector cc,
			int S_Resource_ID, CostDimension d, String trxName) {
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), S_Resource_ID, null);
		return getProductStandardCostPrice(cc, resourceProduct,
				MAcctSchema.get(Env.getCtx(), d.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), d.getM_CostElement_ID()));
	}
	
	public BigDecimal getProductStandardCostPrice(MPPCostCollector cc,
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

	public void createRateVariances(MPPCostCollector costCollector) {
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
				final MCostDetail cost = MCostDetail.getCostDetail(costCollector, costElement.getM_CostElement_ID());
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

	public void createMethodVariances(MPPCostCollector costCollector) {
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
	public MCostDetail createVarianceCostDetail(MPPCostCollector costCollector,
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

	public void createActivityControl(MPPCostCollector costCollector) {
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

	public void createUsageVariances(MPPCostCollector usageVariance) {
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

}
