/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.util.Env;
import org.compiere.util.Util;

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
		if (model.getReversalLine_ID() > 0  && !model.isReversalParent())
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
			amountLowerLevel = movementQuantity.multiply(currentCostPriceLowerLevel);
			accumulatedQuantity = dimension.getCumulatedQty().add(movementQuantity);
			accumulatedAmount = dimension.getCumulatedAmt().add(amount);
			accumulatedAmountLowerLevel = dimension.getCumulatedAmtLL().add(amountLowerLevel);
			return;
		}

		if (costDetail != null) {
			amount = movementQuantity.multiply(costDetail.getCurrentCostPrice());
			amountLowerLevel = movementQuantity.multiply(costDetail.getCurrentCostPriceLL());
			accumulatedQuantity = costDetail.getCumulatedQty().add(movementQuantity);
			accumulatedAmount = costDetail.getCumulatedAmt().add(amount);
			accumulatedAmountLowerLevel = costDetail.getCumulatedAmtLL().add(amountLowerLevel);
			currentCostPrice = dimension.getCurrentCostPrice();
			currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
			adjustCost = currentCostPrice.multiply(dimension.getCumulatedQty()).subtract(dimension.getCumulatedAmt());
			adjustCost = currentCostPriceLowerLevel.multiply(dimension.getCumulatedQty()).subtract(dimension.getCumulatedAmtLL());
			return;
		}

		amount = movementQuantity.multiply(dimension.getCurrentCostPrice());
		amountLowerLevel = movementQuantity.multiply(dimension.getCurrentCostPriceLL());
		accumulatedAmount = dimension.getCumulatedAmt().add(amount).add(adjustCost);
		accumulatedAmountLowerLevel = dimension.getCumulatedAmtLL().add(amountLowerLevel).add(adjustCostLowerLevel);
		accumulatedQuantity = dimension.getCumulatedQty().add(movementQuantity);
		currentCostPrice = dimension.getCurrentCostPrice();
		currentCostPriceLowerLevel = dimension.getCurrentCostPriceLL();
	}


	private void createCostDetail() {
		final String idColumnName = CostEngine.getIDColumnName(model);
		if (model.getReversalLine_ID() > 0  && !model.isReversalParent()) {
			createReversalCostDetail();
			return;
		}

		int seqNo = lastCostDetail.getSeqNo() + 10;
		
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
                costDetail.setAmt(costDetail.getAmt().add(costDetail.getCostAdjustment()));
                costDetail.setDescription(description + " Adjust Cost:" + adjustCost);
            }
            // update adjustment cost lower level
            if (adjustCostLowerLevel.signum() != 0) {
                description = costDetail.getDescription() != null ? costDetail.getDescription() : "";
                costDetail.setCostAdjustmentDateLL(model.getDateAcct());
                costDetail.setCostAdjustmentLL(adjustCostLowerLevel);
                costDetail.setAmtLL(costDetail.getCostAmtLL().add(costDetail.getCostAdjustmentLL()));
                costDetail.setDescription(description + " Adjust Cost LL:" + adjustCost);
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
	 * @deprecated
	 */
	public BigDecimal getNewCurrentCostPrice(MCostDetail cost, int scale, int roundingMode) {
		return getNewCurrentCostPrice(cost, scale, RoundingMode.valueOf(roundingMode));
	}
	
	/**
	 * Average Invoice Get the New Current Cost Price This Level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	public BigDecimal getNewCurrentCostPrice(MCostDetail cost, int scale, RoundingMode roundingMode) {
		if (getNewAccumulatedQuantity(cost).signum() != 0 && getNewAccumulatedAmount(cost).signum() != 0) {
			return getNewAccumulatedAmount(cost).divide(getNewAccumulatedQuantity(cost), scale, roundingMode);
		}

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
	 * Average Invoice Get the new Cumulated Amt Low Level
	 * @param cost MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail cost) {
		BigDecimal accumulatedAmountLowerLevel = Env.ZERO;
		if (cost.getQty().signum() >= 0)
			accumulatedAmountLowerLevel = cost.getCumulatedAmtLL().add(cost.getCostAmtLL()).add(cost.getCostAdjustmentLL());
		else if (cost.getQty().signum() < 0)
			accumulatedAmountLowerLevel = cost.getCumulatedAmtLL().add(cost.getCostAmtLL().negate()).add(cost.getCostAdjustmentLL().negate());
		else if (cost.getQty().signum() == 0)
		{
			if(getNewAccumulatedQuantity(cost).signum() > 0)
				accumulatedAmountLowerLevel = cost.getCumulatedAmt().add(cost.getCostAmtLL()).add(cost.getCostAdjustmentLL());
			else if (getNewAccumulatedQuantity(cost).signum() < 0)
				accumulatedAmountLowerLevel = cost.getCumulatedAmt().add(cost.getCostAmtLL().negate()).add(cost.getCostAdjustmentLL().negate());
		}
		return accumulatedAmountLowerLevel;
	}

	/**
	 * Average Invoice Get the New Current Cost Price low level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 * @deprecated
	 */
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cost, int scale, int roundingMode) {
		return getNewCurrentCostPriceLowerLevel(cost, scale, RoundingMode.valueOf(roundingMode));
	}

	/**
	 * Average Invoice Get the New Current Cost Price low level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 */
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cost, int scale, RoundingMode roundingMode) {
		if (getNewAccumulatedQuantity(cost).signum() != 0 && getNewAccumulatedAmountLowerLevel(cost).signum() != 0) {
			return getNewAccumulatedAmountLowerLevel(cost).divide(getNewAccumulatedQuantity(cost), scale, roundingMode);
		}
		
		return BigDecimal.ZERO;
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
            costDetail.setCostAmt(costDetail.getAmt().subtract(costDetail.getCostAdjustment()));
            costDetail.setCostAmtLL(costDetail.getAmtLL().subtract(costDetail.getCostAdjustmentLL()));
        }
        else if (movementQuantity.signum() < 0 ) {
            costDetail.setCostAmt(costDetail.getAmt().add(adjustCost));
            costDetail.setCostAmtLL(costDetail.getAmtLL().add(adjustCostLowerLevel));
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
}
