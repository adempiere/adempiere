/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MTransaction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

/**
 * @author anca_bradau
 * 
 */
public class LastPOPriceCostingMethod extends AbstractCostingMethod implements ICostingMethod
{

    /**
     * Constructor for Cost Engine
     * @param accountSchema
     * @param transaction
     * @param model
     * @param dimension
     * @param costThisLevel
     * @param costLowLevel
     * @param isSalesTransaction
     */
    public void setCostingMethod(MAcctSchema accountSchema, MTransaction transaction, IDocumentLine model,
                                 MCost dimension, BigDecimal costThisLevel,
                                 BigDecimal costLowLevel, Boolean isSalesTransaction) {
        this.accountSchema = accountSchema;
        this.transaction = transaction;
        this.dimension = dimension;
        this.costThisLevel = (costThisLevel == null ? Env.ZERO : costThisLevel);
        this.costLowLevel = (costLowLevel == null ? Env.ZERO : costLowLevel);
        this.isSalesTransaction = isSalesTransaction;
        this.model = model;
        this.costingLevel = org.compiere.model.MProduct.get(this.transaction.getCtx(), this.transaction.getM_Product_ID())
                .getCostingLevel(accountSchema, transaction.getAD_Org_ID());
        // find if this transaction exist into cost detail
        this.costDetail = MCostDetail.getByTransaction(this.model, this.transaction,
                this.accountSchema.getC_AcctSchema_ID(), this.dimension.getM_CostType_ID(),
                this.dimension.getM_CostElement_ID());
    }
	
	public MCostDetail process() {
		CLogger logger = CLogger.getCLogger(LastPOPriceCostingMethod.class);
		boolean isReturnTrx =  costDetail.getQty().signum() < 0;
		int precision = accountSchema.getCostingPrecision();
		BigDecimal price = costDetail.getAmt();

		if ( costDetail.getQty().signum() != 0)
			price =  costDetail.getAmt().divide(costDetail.getQty(), precision, BigDecimal.ROUND_HALF_UP);

		if ( costDetail.getC_OrderLine_ID() != 0) {
			if (!isReturnTrx) {
				if ( costDetail.getQty().signum() != 0)
					dimension.setCurrentCostPrice(price);
				else {
					BigDecimal currentCost = dimension.getCurrentCostPrice().add(costDetail.getAmt());
					dimension.setCurrentCostPrice(currentCost);
				}
			}
			dimension.add( costDetail.getAmt(),  costDetail.getQty());
			logger.finer("PO - LastPO - " + dimension);
		} 
		else if ( costDetail.getM_InOutLine_ID() != 0 // AR Shipment Detail Record
				||  costDetail.getM_MovementLine_ID() != 0
				||  costDetail.getM_InventoryLine_ID() != 0
				||  costDetail.getM_ProductionLine_ID() != 0
				||  costDetail.getC_ProjectIssue_ID() != 0
				||  costDetail.getPP_Cost_Collector_ID() != 0)
		{
			dimension.setCurrentQty(dimension.getCurrentQty().add(costDetail.getQty()));
			logger.finer("QtyAdjust - LastPO - " + dimension);
			dimension.saveEx();
		}
		return costDetail;
	}

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void updateAmountCost() {

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
}
