/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovementLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.eevolution.model.MPPCostCollector;

/**
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * @author Systemhaus Westfalia SusanneCalderon <susanne.de.calderon@westfalia-it.com>
 *    <li> Set M_MatchInv_ID and M_MatchPO_ID in Costdetail</>
 *    https://github.com/adempiere/adempiere/issues/1918
 * 
 */
public class AverageInvoiceCostingMethod extends AbstractCostingMethod
		implements ICostingMethod {

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
		this.costingLevel = MProduct.get(this.transaction.getCtx(), this.transaction.getM_Product_ID())
				.getCostingLevel(accountSchema, transaction.getAD_Org_ID());
		// find if this transaction exist into cost detail
		this.costDetail = MCostDetail.getByTransaction(this.model, this.transaction,
				this.accountSchema.getC_AcctSchema_ID(), this.dimension.getM_CostType_ID(),
				this.dimension.getM_CostElement_ID());

        //Setting Accounting period status
        MDocType documentType = new MDocType(transaction.getCtx(), transaction.getDocumentLine().getC_DocType_ID(), transaction.get_TrxName());
        this.isOpenPeriod = MPeriod.isOpen(transaction.getCtx(), model.getDateAcct() , documentType.getDocBaseType(), transaction.getAD_Org_ID());

        //Setting Date Accounting based on Open Period
        if (this.isOpenPeriod)
            this.dateAccounting = model.getDateAcct();
        /*else if (model instanceof MLandedCostAllocation )
                this.dateAccounting = ((MLandedCostAllocation) model).getC_InvoiceLine().getC_Invoice().getDateAcct();
        //else if (model instanceof MMatchInv)
		//	this.dateAccounting = ((MMatchInv) model).getC_InvoiceLine().getC_Invoice().getDateAcct();*/
        else
            this.dateAccounting = null; // Is Necessary define that happen in this case when period is close

        this.movementQuantity = transaction.getMovementQty();
	}

	public void calculate() {
		// try find the last cost detail transaction
		lastCostDetail = MCostDetail.getLastTransaction(model, transaction,
				accountSchema.getC_AcctSchema_ID(), dimension.getM_CostType_ID(),
				dimension.getM_CostElement_ID(),dateAccounting,
				costingLevel);

		// If model is reversal then no calculate cost
		//Validate if model have a reverses and processing of reverse
		if (model.getReversalLine_ID() > 0 && costDetail == null)
			return;
		else if( costDetail != null && costDetail.isReversal() && model.getReversalLine_ID() > 0)
	    {
            setReversalCostDetail();
            return;
	    }

		// created a new instance cost detail to process calculated cost
		if (lastCostDetail == null) {
			lastCostDetail = new MCostDetail(transaction,
					accountSchema.getC_AcctSchema_ID(), dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(), Env.ZERO, Env.ZERO,
					Env.ZERO, transaction.get_TrxName());
			lastCostDetail.setDateAcct(dateAccounting);
		}
			
		BigDecimal quantityOnHand = getNewAccumulatedQuantity(lastCostDetail);
		
		// The cost detail was created before then is necessary to update cost by
		// generate adjustment
		if (transaction.getM_Transaction_ID() == lastCostDetail.getM_Transaction_ID()) {
			movementQuantity = Env.ZERO;
			
			//Processing provision of purchase cost  
			//Provision is calculated when the last cost detail  is a material receipt and not exist of invoice line
			//if an invoice line exist for this cost detail then an invoice line was processed for this material receipt 
			//and not exist different between purchase cost and invoice cost, this logic was implemented to prevent 
			//that a provision of purchase cost decreases more than one times in a cost adjustment
			BigDecimal provisionOfPurchaseCost = BigDecimal.ZERO;
			BigDecimal provisionOfPurchaseCostLL = BigDecimal.ZERO;
            // Quantity accumulated from last cost transaction
            accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail).add(
                    movementQuantity);

			if (model instanceof MMatchInv)
			{
				if (lastCostDetail.getM_MatchInv_ID() != 0 || lastCostDetail.getC_LandedCostAllocation_ID() !=0)
				{
					StringBuffer whereClause = new StringBuffer();
					whereClause.append(" M_CostType_ID=" + dimension.getM_CostType_ID());
					whereClause.append(" AND M_CostElement_ID=" + dimension.getM_CostElement_ID());
					whereClause.append(" AND M_MatchInv_ID is null and C_LandedCostAllocation_ID is null");
					whereClause.append(" AND M_InOutline_ID=?");
					MCostDetail receiptCostDetail = MCostDetail.get(model.getCtx(),whereClause.toString(),
							((MMatchInv) model).getM_InOutLine_ID(),
							model.getM_AttributeSetInstance_ID(), dimension.getC_AcctSchema_ID(), model.get_TrxName());
					provisionOfPurchaseCost = receiptCostDetail.getCostAmt();
					provisionOfPurchaseCostLL =  receiptCostDetail.getCostAmtLL();
				}
				else
				{
					provisionOfPurchaseCost = lastCostDetail.getCostAmt();
					provisionOfPurchaseCostLL =  lastCostDetail.getCostAmtLL();
				}
				MMatchInv iMatch =  (MMatchInv) model;
				//lastCostDetail.setC_InvoiceLine_ID(iMatch.getC_InvoiceLine_ID());
				//lastCostDetail.saveEx();
                // reset the accumulated quantity with last cost detail
                if (lastCostDetail != null && lastCostDetail.getM_CostDetail_ID() > 0)
                    accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail);
			}
			adjustCost = model.getMovementQty().multiply(costThisLevel).subtract(provisionOfPurchaseCost);
			adjustCostLowerLevel =  model.getMovementQty().multiply(costLowLevel).subtract(provisionOfPurchaseCostLL);

			
			accumulatedAmount = getNewAccumulatedAmount(lastCostDetail);
			accumulatedAmount = accumulatedQuantity.signum() > 0 ? accumulatedAmount.add(adjustCost) : accumulatedAmount.add(adjustCost.negate());
			
			accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(lastCostDetail);
			accumulatedAmountLowerLevel =  accumulatedQuantity.signum() > 0 ? accumulatedAmountLowerLevel.add(adjustCostLowerLevel) : 
				accumulatedAmountLowerLevel.add(adjustCostLowerLevel.negate());
					
			
			currentCostPrice = accumulatedAmount.divide(
					accumulatedQuantity.signum() != 0 ? accumulatedQuantity
							: BigDecimal.ONE, accountSchema.getCostingPrecision(),
					BigDecimal.ROUND_HALF_UP);
			currentCostPriceLowerLevel = accumulatedAmountLowerLevel.divide(accumulatedQuantity
					.signum() != 0 ? accumulatedQuantity : BigDecimal.ONE, accountSchema
					.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);

			if(adjustCost.add(adjustCostLowerLevel).signum() == 0)
				return;
			// validation when the cost detail is reprocess
			if (costDetail == null)
				return;
			
			// reset with the current values
			costDetail.setCostAdjustment(adjustCost);
			costDetail.setAmt(costDetail.getCostAmt().add(costDetail.getCostAdjustment()));
			costDetail.setCostAdjustmentLL(adjustCostLowerLevel);
			costDetail.setAmtLL(costDetail.getCostAmtLL().add(costDetail.getCostAdjustmentLL()));

			updateAmountCost();

			return;
		}
		
		// calculated costing
		if (transaction.getMovementType().endsWith("+"))
		{
			//Project of cost if a cost was entry in zero, the inventory is revalued using the fist cost
			//Example ; Quantity On hand 2.00 , Total Cost : 0.00 , Transaction Quantity 4.00 , Cost Total Transaction 17.8196
			//cost This Level = ( (17.8196 / 4)  * 6 ) / 4 | (costThisLevel * costThisLevel) / lastCostDetail.getQty()
			
			//Detect Inventory with zero value
			//the On hand is different zero and inventory values is zero then
			if (quantityOnHand.signum() != 0
			&& getNewAccumulatedAmount(lastCostDetail).signum() == 0
			&& costThisLevel.signum() != 0
			)
			{				
				adjustCost = quantityOnHand.
								add(movementQuantity)
								.multiply(costThisLevel)
								.subtract(costThisLevel
								.multiply(movementQuantity));
			} // Logic to calculate adjustment when inventory is negative
			else if (quantityOnHand.add(movementQuantity).signum() < 0
			&& getNewCurrentCostPrice(lastCostDetail, accountSchema.getCostingPrecision(),  BigDecimal.ROUND_HALF_UP).signum() != 0
			&& costThisLevel.signum() == 0  )
			{
				currentCostPrice = getNewCurrentCostPrice(lastCostDetail, accountSchema.getCostingPrecision(),  BigDecimal.ROUND_HALF_UP);
				adjustCost = currentCostPrice.multiply(movementQuantity).abs();
			}
            // If period is not open then an adjustment cost is create based on quantity on hand of attribute instance
            // the amount difference is apply to adjustment cost account, the reason is because is import distribute
            // proportionally
			if (model instanceof MLandedCostAllocation || model instanceof MMatchInv)
			{
                //if (!isOpenPeriod)
                {

					int attributeSetInstanceId = 0;
					if (model instanceof  MLandedCostAllocation) {
						MLandedCostAllocation costAllocation = (MLandedCostAllocation) this.model;
						attributeSetInstanceId = costAllocation.getM_AttributeSetInstance_ID();
					}
					if (model instanceof MMatchInv) {
						MMatchInv matchInv = (MMatchInv) this.model;
						attributeSetInstanceId = matchInv.getM_AttributeSetInstance_ID();
					}

                    this.movementQuantity = MCostDetail.getQtyOnHandByASIAndSeqNo(
                            transaction.getCtx(),
                            transaction.getM_Product_ID(),
                            dimension.getM_CostType_ID(),
                            dimension.getM_CostElement_ID(),
							attributeSetInstanceId,
                            lastCostDetail.getSeqNo(),
                            transaction.get_TrxName());

                    accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail);
                    currentCostPrice = movementQuantity.multiply(costThisLevel);
                    currentCostPriceLowerLevel = movementQuantity.multiply(costLowLevel);
                    adjustCost = currentCostPrice;
                    adjustCostLowerLevel = currentCostPriceLowerLevel;
                }
			}
			else
			{
                    accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail).add(movementQuantity);
                    currentCostPrice = costThisLevel;
                    currentCostPriceLowerLevel = costLowLevel;
			}

            amount = movementQuantity.multiply(costThisLevel);
            amountLowerLevel = movementQuantity.multiply(costLowLevel);

            accumulatedAmount = getNewAccumulatedAmount(lastCostDetail);
			accumulatedAmount = accumulatedQuantity.signum() > 0 ? accumulatedAmount.add(amount) : accumulatedAmount.add(amount.negate());
			
			accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(lastCostDetail);
			accumulatedAmountLowerLevel = accumulatedQuantity.signum() > 0 ? accumulatedAmountLowerLevel.add(amountLowerLevel) : accumulatedAmountLowerLevel.add(amountLowerLevel.negate());
			if (model instanceof MMovementLine && costDetail!=null){
				costDetail.setAmt(amount);
			}
		}
		else if (transaction.getMovementType().endsWith("-")) {
			// Use the last current cost price for out transaction			
			if (quantityOnHand.add(movementQuantity).signum() >= 0)
			{
				currentCostPrice = getNewCurrentCostPrice(lastCostDetail, accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				currentCostPriceLowerLevel = getNewCurrentCostPriceLowerLevel(lastCostDetail, accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			} 
			else
			{
				currentCostPrice = CostEngine.getCostThisLevel(accountSchema, dimension.getM_CostType(), dimension.getM_CostElement(), transaction, model, costingLevel);
			}
		
			amount = transaction.getMovementQty().multiply(currentCostPrice);
			amountLowerLevel = movementQuantity.multiply(currentCostPriceLowerLevel);

			accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail).add(
                    movementQuantity);
			
			accumulatedAmount = getNewAccumulatedAmount(lastCostDetail);
			accumulatedAmount = accumulatedQuantity.signum() > 0 ? accumulatedAmount.add(amount) : accumulatedAmount.add(amount.negate());
			
			accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(lastCostDetail);
			accumulatedAmountLowerLevel = accumulatedQuantity.signum() > 0 ? accumulatedAmountLowerLevel.add(amountLowerLevel) : accumulatedAmountLowerLevel.add(amountLowerLevel.negate());
		
			if(costDetail != null)
			{	
				costDetail.setAmt(currentCostPrice.multiply(movementQuantity.abs()));
				costDetail.setAmtLL(currentCostPriceLowerLevel.multiply(movementQuantity).abs());
			}
		}
		
		//create new cost
		if (costDetail == null)
			return;
		
		updateAmountCost();
	}

	private void createCostDetail() {

		// Ignore reversal transaction because is created based on the original
		// transaction
		//Validate if model have a reverses and processing of reverse
		if (model.getReversalLine_ID() > 0 && costDetail == null ) {
			createReversalCostDetail();
			return;
		} 
		else if (model.getReversalLine_ID() > 0)
			return;
		

		int seqNo = lastCostDetail.getSeqNo() + 10;
		// create a new cost detail or is necessary create a new cost detail for
		// adjustment
		if (transaction.getM_Transaction_ID() != lastCostDetail
				.getM_Transaction_ID()
				&& costDetail == null
				|| adjustCost.add(adjustCostLowerLevel).signum() != 0
				&& costDetail == null) {
			// set Movement Qty in Zero because is a adjustment
			// if exist adjustment cost for Landed Cost Allocation or Match Inv then set the movement qty to zero
			if ((adjustCost.add(adjustCostLowerLevel).signum() != 0 && costDetail != null)
			|| (model instanceof MLandedCostAllocation || model instanceof MMatchInv))
				movementQuantity = Env.ZERO;

			// create new cost detail
			costDetail = new MCostDetail(transaction, accountSchema.getC_AcctSchema_ID(),
					dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(), currentCostPrice
							.multiply(movementQuantity).abs(),
					currentCostPriceLowerLevel.multiply(movementQuantity).abs(),
					movementQuantity, transaction.get_TrxName());
			// set account date for this cost detail
			costDetail.setDateAcct(dateAccounting);
			costDetail.setSeqNo(seqNo);

			// set transaction id
			if (transaction != null)
				costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
			// set if transaction is sales order type or not
			if (isSalesTransaction != null && isSalesTransaction)
				costDetail.setIsSOTrx(isSalesTransaction);
			else if (isSalesTransaction != null && !isSalesTransaction)
				costDetail.setIsSOTrx(model.isSOTrx());

			if (adjustCost.signum() != 0 || adjustCostLowerLevel.signum() != 0) {
				String description = costDetail.getDescription() != null ? costDetail
						.getDescription() : "";
				// update adjustment cost this level
				if (adjustCost.signum() != 0) {
					costDetail.setCostAdjustmentDate(model.getDateAcct());
					costDetail.setCostAdjustment(adjustCost);
					costDetail.setCostAmt(BigDecimal.ZERO);
					costDetail.setAmt(costDetail.getAmt().add(costDetail.getCostAdjustment()));
					costDetail.setDescription(description + Msg.parseTranslation(Env.getCtx() , "@CostAdjustment@ ") + adjustCost);
				}
				// update adjustment cost lower level
				if (adjustCostLowerLevel.signum() != 0) {
					description = costDetail.getDescription() != null ? costDetail.getDescription() : "";
					costDetail.setCostAdjustmentDateLL(model.getDateAcct());
					costDetail.setCostAdjustmentLL(adjustCostLowerLevel);
					costDetail.setCostAmtLL(BigDecimal.ZERO);
					costDetail.setAmtLL(costDetail.getCostAmtLL().add(costDetail.getCostAdjustmentLL()));
					costDetail.setDescription(description + Msg.parseTranslation(Env.getCtx() , "@CostAdjustmentLL@ ")+ adjustCost);
				}
			}

			updateAmountCost();
			return;
		}
	}

	public MCostDetail process() {		
		calculate();
		createCostDetail();
		updateInventoryValue();
		createCostAdjustment();
		return costDetail;
	}

	public void createCostAdjustment() {
		// only re process cost detail if account schema need adjust cost
		if (!accountSchema.isAdjustCOGS())
			return;
		// void the cycle process, only process the adjustment
		if (costDetail == null || costDetail.isProcessing())
			return;

		// Check if cost detail is an earlier transaction
		// get the cost details that need be re process before this cost
		// transaction
		List<MCostDetail> costDetails = MCostDetail.getAfterDate(costDetail, costingLevel);
		if (costDetails == null || costDetails.size() == 0)
			return;
		
		MCostDetail lastCostDetail = costDetail;
		costDetail = null;
		//Renumber sequence
		for (MCostDetail cost : costDetails) {
			cost.setSeqNo(lastCostDetail.getSeqNo() + 10); // remunerate sequence
			cost.setProcessing(true);
			cost.saveEx();
			lastCostDetail = cost;
			// Only uncomment to debug
			// Trx.get(cd.get_TrxName(), false).commit();
		}
		int costDetailAdjustmentNo = 0;
		for (MCostDetail cost : costDetails) {
			costDetailAdjustmentNo ++;
			adjustCostDetail(cost);
            cost.setProcessing(false);
            cost.saveEx();
 			//clearAccounting(cd);
			// Only uncomment to debug
			// Trx.get(cd.get_TrxName(), false).commit();
		}
	}

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
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
				accumulatedAmountLowerLevel = cost.getCumulatedAmtLL().add(cost.getCostAmtLL()).add(cost.getCostAdjustmentLL());
			else if (getNewAccumulatedQuantity(cost).signum() < 0)
				accumulatedAmountLowerLevel = cost.getCumulatedAmtLL().add(cost.getCostAmtLL().negate()).add(cost.getCostAdjustmentLL().negate());
		}
		return accumulatedAmountLowerLevel;
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
	 * Average Invoice Get the new Cumulated Qty
	 * @param cost Cost Detail
	 * @return New Accumulated Quantity
	 */
	public BigDecimal getNewAccumulatedQuantity(MCostDetail cost) {
		    return cost.getCumulatedQty().add(cost.getQty());
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
		costDetail.set_ValueOfColumn(idColumnName, CostEngine.getIDColumn(model));
		if (model instanceof MInOutLine)
		{	
			MInOutLine ioLine =  (MInOutLine) model;
			costDetail.setC_OrderLine_ID(ioLine.getC_OrderLine_ID());
			// IMPORTANT : reset possible provision purchase cost processed
			costDetail.setC_InvoiceLine_ID(0);
		}

		if (model instanceof MMatchInv )
		{	
			MMatchInv iMatch =  (MMatchInv) model;
			costDetail.setM_MatchInv_ID(model.get_ID());
			if(costDetail.getM_InOutLine_ID() == 0)
				costDetail.setM_InOutLine_ID(iMatch.getM_InOutLine_ID());

			if(costDetail.getC_InvoiceLine_ID() == 0)
				costDetail.setC_InvoiceLine_ID(iMatch.getM_InOutLine_ID());
		}
		if(model instanceof MMatchPO )
		{
			MMatchPO poMatch =  (MMatchPO) model;
			costDetail.setM_MatchPO_ID(poMatch.getM_MatchPO_ID());
			if (costDetail.getM_InOutLine_ID() == 0)
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

    public void updateInventoryValue() {
        if (accumulatedQuantity.signum() != 0)
        {
            dimension.setCurrentCostPrice(accumulatedAmount.divide(accumulatedQuantity, accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP));
            dimension.setCurrentCostPriceLL(accumulatedAmountLowerLevel.divide(accumulatedQuantity, accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP));
        }
        if (model.getReversalLine_ID() != 0)
		{
			if (lastCostDetail == null)
				return;
		}
        dimension.setCumulatedAmt(accumulatedAmount);
        dimension.setCumulatedAmtLL(accumulatedAmountLowerLevel);
        dimension.setCumulatedQty(accumulatedQuantity);
        dimension.setCurrentQty(accumulatedQuantity);
        dimension.saveEx();
    }


    /**
	 * Recalculate Cost Detail
	 * @param cost
     */
	public void adjustCostDetail(MCostDetail cost) {

        Properties ctx =  cost.getCtx();
        String trxName = cost.get_TrxName();
        int transactionId = cost.getM_Transaction_ID();
        int clientId = cost.getAD_Client_ID();

		MTransaction transaction = new MTransaction(ctx, transactionId, trxName);

		MCostType costType = (MCostType) cost.getM_CostType();
		MCostElement costElement = (MCostElement) cost.getM_CostElement();
		MAcctSchema accountSchema = (MAcctSchema) cost.getC_AcctSchema();

        CostEngineFactory.getCostEngine(accountSchema.getAD_Client_ID())
                .clearAccounting(accountSchema, transaction);

		if (MTransaction.MOVEMENTTYPE_VendorReceipts.equals(transaction.getMovementType()))
		{
			MInOutLine line = (MInOutLine) transaction.getDocumentLine();
			if (MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType()))
			{
                if (cost.getM_InOutLine_ID() > 0 && cost.getQty().signum() !=  0 )
                {
                    CostEngineFactory.getCostEngine(clientId).createCostDetail(accountSchema, costType, costElement, transaction, line, true);
                }
                else if (cost.getM_InOutLine_ID() > 0 && cost.getQty().signum() != 0 && cost.getC_OrderLine_ID() > 0) {
                    List<MMatchPO> orderMatches = MMatchPO.getInOutLine(line);
                    for (MMatchPO match : orderMatches) {
                        if (match.getM_InOutLine_ID() == line.getM_InOutLine_ID()
                                && match.getM_Product_ID() == transaction.getM_Product_ID()) {
                            CostEngineFactory.getCostEngine(clientId)
                                    .createCostDetail(accountSchema, costType, costElement, transaction, match, true);
                        }
                    }
                }
                else if (cost.getM_InOutLine_ID() > 0 && cost.getQty().signum() == 0 && cost.getC_InvoiceLine_ID() > 0 && cost.getC_LandedCostAllocation_ID() ==0) {
                    List<MMatchInv> invoiceMatches = MMatchInv.getInOutLine(line);
                    for (MMatchInv match : invoiceMatches) {
                        if (match.getM_Product_ID() == transaction.getM_Product_ID()) {
                            CostEngineFactory.getCostEngine(clientId).createCostDetail(accountSchema, costType, costElement, transaction, match, true);
                        }
                    }
                }
			}// only own allocation
			if (cost.getC_LandedCostAllocation_ID()!=0)
			{
				MLandedCostAllocation allocation = (MLandedCostAllocation)cost.getC_LandedCostAllocation();
				{
					CostEngineFactory
					.getCostEngine(clientId)
					.createCostDetail(accountSchema, costType, costElement, transaction, allocation, true);
				}
			}
		}
        else
            CostEngineFactory.getCostEngine(clientId).createCostDetail(
                    accountSchema, costType, costElement, transaction, transaction.getDocumentLine(), true);
	}	
	

	public void createUpdateAverageCostDetail(MPPCostCollector costCollectorVariance,
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

	public BigDecimal getResourceActualCostRate(MPPCostCollector costCollector,
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
	

	public BigDecimal getProductActualCostPrice(MPPCostCollector costCollector, MProduct product, MAcctSchema acctSchema, MCostElement costElement, String trxName)
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

	protected BigDecimal roundCost(BigDecimal price, int accountSchemaId) {
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
	

	public BigDecimal getResourceFutureCostRate(MPPCostCollector costCollector,
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
	

	public BigDecimal getProductFutureCostPrice(MPPCostCollector costCollector, MProduct product, MAcctSchema acctSchema, MCostElement costElement, String trxName)
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
}
