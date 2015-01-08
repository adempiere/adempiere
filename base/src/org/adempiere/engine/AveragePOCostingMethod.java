/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/**
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class AveragePOCostingMethod extends AbstractCostingMethod
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
	}

	public void calculate() {

        if (model instanceof MMatchInv)
            return;

		// try find the last cost detail transaction
		lastCostDetail = MCostDetail.getLastTransaction(model, transaction,
				accountSchema.getC_AcctSchema_ID(), dimension.getM_CostType_ID(),
				dimension.getM_CostElement_ID(), model.getDateAcct(),
				costingLevel);

		// If model is reversal then no calculate cost
		//Validate if model have a reverses and processing of reverse
		if (model.getReversalLine_ID() > 0
			&& costDetail == null)
			return;
		else if( costDetail != null
			&& costDetail.isReversal()
			&& model.getReversalLine_ID() > 0)
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
			lastCostDetail.setDateAcct(model.getDateAcct());
		}
			
		BigDecimal quantityOnHand = getNewAccumulatedQuantity(lastCostDetail);
		
		// The cost detail was created before then is necessary to update cost by
		// generate adjustment	
		if (transaction.getM_Transaction_ID() == lastCostDetail.getM_Transaction_ID()) {
			
			//Processing provision of purchase cost  
			//Provision is calculated when the last cost detail  is a material receipt and not exist of invoice line
			//if an invoice line exist for this cost detail then an invoice line was processed for this material receipt 
			//and not exist different between purchase cost and invoice cost, this logic was implemented to prevent 
			//that a provision of purchase cost decreases more than one times in a cost adjustment
			BigDecimal provisionOfPurchaseCost = BigDecimal.ZERO;
			BigDecimal provisionOfPurchaseCostLL = BigDecimal.ZERO;
            // Quantity accumulated from last cost transaction
            accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail).add(
                    transaction.getMovementQty());

			if (model instanceof MMatchInv && lastCostDetail.getC_InvoiceLine_ID() == 0)
			{
				provisionOfPurchaseCost = lastCostDetail.getCostAmt();
				provisionOfPurchaseCostLL =  lastCostDetail.getCostAmtLL();
				MMatchInv iMatch =  (MMatchInv) model;
				lastCostDetail.setC_InvoiceLine_ID(iMatch.getC_InvoiceLine_ID());
				lastCostDetail.saveEx();
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
			costDetail.setAmt(costDetail.getCostAmt().add(
					costDetail.getCostAdjustment()));
			costDetail.setCostAdjustmentLL(adjustCostLowerLevel);
			costDetail.setAmtLL(costDetail.getCostAmtLL().add(
					costDetail.getCostAdjustmentLL()));

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
								add(transaction.getMovementQty())
								.multiply(costThisLevel)
								.subtract(costThisLevel
								.multiply(transaction.getMovementQty()));
			} // Logic to calculate adjustment when inventory is negative
			else if (quantityOnHand.add(transaction.getMovementQty()).signum() < 0
			&& getNewCurrentCostPrice(lastCostDetail, accountSchema
			  .getCostingPrecision(),  BigDecimal.ROUND_HALF_UP).signum() != 0
			&& costThisLevel.signum() == 0  )
			{
				currentCostPrice = getNewCurrentCostPrice(lastCostDetail, accountSchema
						.getCostingPrecision(),  BigDecimal.ROUND_HALF_UP);
				adjustCost = currentCostPrice.multiply(transaction.getMovementQty()).abs();
			}
				

			
			amount = transaction.getMovementQty().multiply(costThisLevel);
			amountLowerLevel = transaction.getMovementQty().multiply(costLowLevel);
			
			accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail).add(
					transaction.getMovementQty());
			
			accumulatedAmount = getNewAccumulatedAmount(lastCostDetail);
			accumulatedAmount = accumulatedQuantity.signum() > 0 ? accumulatedAmount.add(amount) : accumulatedAmount.add(amount.negate());
			
			accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(lastCostDetail);
			accumulatedAmountLowerLevel = accumulatedQuantity.signum() > 0 ? accumulatedAmountLowerLevel.add(amountLowerLevel) : accumulatedAmountLowerLevel.add(amountLowerLevel.negate());
	
			currentCostPrice = costThisLevel;
			currentCostPriceLowerLevel = costLowLevel;
	
		}
		else if (transaction.getMovementType().endsWith("-")) {
			// Use the last current cost price for out transaction			
			if (quantityOnHand.add(transaction.getMovementQty()).signum() >= 0)
			{
				currentCostPrice = getNewCurrentCostPrice(lastCostDetail, accountSchema
						.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
				currentCostPriceLowerLevel = getNewCurrentCostPriceLowerLevel(lastCostDetail, accountSchema
                        .getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			} 
			else
			{
				currentCostPrice = CostEngine.getCostThisLevel(accountSchema, dimension.getM_CostType(), dimension.getM_CostElement(), transaction, model, costingLevel);
			}
		
			amount = transaction.getMovementQty().multiply(currentCostPrice);
			amountLowerLevel = transaction.getMovementQty().multiply(currentCostPriceLowerLevel);

			accumulatedQuantity = getNewAccumulatedQuantity(lastCostDetail).add(
					transaction.getMovementQty());
			
			accumulatedAmount = getNewAccumulatedAmount(lastCostDetail);
			accumulatedAmount = accumulatedQuantity.signum() > 0 ? accumulatedAmount.add(amount) : accumulatedAmount.add(amount.negate());
			
			accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(lastCostDetail);
			accumulatedAmountLowerLevel = accumulatedQuantity.signum() > 0 ? accumulatedAmountLowerLevel.add(amountLowerLevel) : accumulatedAmountLowerLevel.add(amountLowerLevel.negate());
		
			if(costDetail != null)
			{	
				costDetail.setAmt(currentCostPrice.multiply(transaction.getMovementQty().abs()));
				costDetail.setAmtLL(currentCostPriceLowerLevel.multiply(transaction.getMovementQty()).abs());
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
			BigDecimal movementQuantity = transaction.getMovementQty();
			// if exist adjustment cost for Landed Cost Allocation or Match Inv then set the movement qty to zero
			if (adjustCost.add(adjustCostLowerLevel).signum() != 0
			&& (model instanceof MLandedCostAllocation || model instanceof MMatchInv))
				movementQuantity = Env.ZERO;

			// create new cost detail
			costDetail = new MCostDetail(transaction, accountSchema.getC_AcctSchema_ID(),
					dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(), currentCostPrice
							.multiply(movementQuantity).abs(),
					currentCostPriceLowerLevel.multiply(movementQuantity).abs(),
					movementQuantity, transaction.get_TrxName());
			// set account date for this cost detail
			costDetail.setDateAcct(model.getDateAcct());
			costDetail.setSeqNo(seqNo);

			// set transaction id
			if (transaction != null)
				costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
			// set if transaction is sales order type or not
			if (isSalesTransaction != null)
				costDetail.setIsSOTrx(isSalesTransaction);
			else
				costDetail.setIsSOTrx(model.isSOTrx());

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

			updateAmountCost();
			return;
		}
	}

	public MCostDetail process() {		
		calculate();
		createCostDetail();
		updateInventoryValue();
		createCostAdjustment();
       // if (costDetail != null && costDetail.getM_CostDetail_ID() > 0)
       //     DB.executeUpdate("UPDATE M_CostDetail SET Processing='N' WHERE M_CostDetail_ID=?", costDetail.getM_CostDetail_ID(), costDetail.get_TrxName());

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
		List<MCostDetail> cds = MCostDetail.getAfterDate(costDetail,
				costingLevel);
		if (cds == null || cds.size() == 0)
			return;
		
		MCostDetail last_cd = costDetail;
		costDetail = null;
		
		 /*System.out.println(
		 "-----------------------------------ADJUSTMENT COST -------------------------------------------------"
		 ); System.out.println(last_cd); System.out.println(
		 "----------------------------------------------------------------------------------------------------"
		 );*/
		 
		//Renumber sequence
		for (MCostDetail cd : cds) {
			cd.setSeqNo(last_cd.getSeqNo() + 10); // remunerate sequence
			cd.setProcessing(true);
			cd.saveEx();
			last_cd = cd;
			// Only uncomment to debug
			// Trx.get(cd.get_TrxName(), false).commit();
		}

		for (MCostDetail cd : cds) {
			adjustCostDetail(cd);
            cd.setProcessing(false);
            cd.saveEx();
 			//clearAccounting(cd);
			// Only uncomment to debug
			// Trx.get(cd.get_TrxName(), false).commit();
		}
	}

	@Override
	public void processCostDetail(MCostDetail costDetail) {
	}

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Average Invoice Get the New Current Cost Price This Level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param scale
	 *            Scale
	 * @param roundingMode
	 *            Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	public BigDecimal getNewCurrentCostPrice(MCostDetail cd, int scale,
			int roundingMode) {
		if (getNewAccumulatedQuantity(cd).signum() != 0
				&& getNewAccumulatedAmount(cd).signum() != 0)
			return getNewAccumulatedAmount(cd).divide(getNewAccumulatedQuantity(cd), scale,
					roundingMode);
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the New Cumulated Amt This Level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Amt This Level
	 */
	public BigDecimal getNewAccumulatedAmount(MCostDetail cd) {

		BigDecimal accumulatedAmount = Env.ZERO;
		if (cd.getQty().signum() > 0)
			accumulatedAmount = cd.getCumulatedAmt().add(cd.getCostAmt())
					.add(cd.getCostAdjustment());
		else if (cd.getQty().signum() < 0)
			accumulatedAmount = cd.getCumulatedAmt().add(cd.getCostAmt().negate())
					.add(cd.getCostAdjustment().negate());
		else if (cd.getQty().signum() == 0)
		{
			if(getNewAccumulatedQuantity(cd).signum() > 0)
				accumulatedAmount = cd.getCumulatedAmt().add(cd.getCostAmt())
				.add(cd.getCostAdjustment());
			else if (getNewAccumulatedQuantity(cd).signum() < 0)
				accumulatedAmount = cd.getCumulatedAmt().add(cd.getCostAmt().negate())
				.add(cd.getCostAdjustment().negate());
				
		}
		
		return accumulatedAmount;
	}

	/**
	 * Average Invoice Get the New Current Cost Price low level
	 * 
	 * @param costDetail Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 */
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail costDetail, int scale,
                                                       int roundingMode) {
		if (getNewAccumulatedQuantity(costDetail).signum() != 0
				&& getNewAccumulatedAmountLowerLevel(costDetail).signum() != 0)
			return getNewAccumulatedAmountLowerLevel(costDetail).divide(getNewAccumulatedQuantity(costDetail),
					scale, roundingMode);
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the new Cumulated Amt Low Level
	 * 
	 * @param costDetail MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail costDetail) {
		BigDecimal accumulatedAmountLowerLevel = Env.ZERO;
		if (costDetail.getQty().signum() >= 0)
			accumulatedAmountLowerLevel = costDetail.getCumulatedAmtLL().add(costDetail.getCostAmtLL())
					.add(costDetail.getCostAdjustmentLL());
		else
			accumulatedAmountLowerLevel = costDetail.getCumulatedAmtLL()
					.add(costDetail.getCostAmtLL().negate())
					.add(costDetail.getCostAdjustmentLL().negate());
		return accumulatedAmountLowerLevel;
	}

	/**
	 * Average Invoice Get the new Cumulated Qty
	 * @param costDetail Cost Detail
	 * @return New Accumulated Quantity
	 */
	public BigDecimal getNewAccumulatedQuantity(MCostDetail costDetail) {
		    return costDetail.getCumulatedQty().add(costDetail.getQty());
	}

	/**
	 * Update Cost Amt
	 */
	public void updateAmountCost() {
		
		if (transaction.getMovementQty().signum() > 0) {
			costDetail.setCostAmt(costDetail.getAmt().subtract(
					costDetail.getCostAdjustment()));
			costDetail.setCostAmtLL(costDetail.getAmtLL().subtract(
					costDetail.getCostAdjustmentLL()));
		}	
		else if (transaction.getMovementQty().signum() < 0 ) {
			costDetail.setCostAmt(costDetail.getAmt().add(adjustCost));
			costDetail.setCostAmtLL(costDetail.getAmtLL().add(
                    adjustCostLowerLevel));
		}

        costDetail.setCumulatedQty(getNewAccumulatedQuantity(lastCostDetail));
        costDetail.setCumulatedAmt(getNewAccumulatedAmount(lastCostDetail));
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
			costDetail.setProcessed(false);
		}
		costDetail.saveEx();
        //System.out.println("Catidad Inicial" + costDetail.getCumulatedQty() + " Saldo Inicial  " + costDetail.getCumulatedAmt());
		//System.out.println (costDetail.getM_Warehouse().getName() + " " + costDetail.getSeqNo() + " Cumulated Qty:" + costDetail.getCumulatedQty() + " Cumulated Amt:" + costDetail.getCumulatedAmt() + " Transaction ID: " +  costDetail.getM_Transaction_ID() +  " Model ID: " + model.get_ID() + " Date " + costDetail.getDateAcct());
		// Trx.get(costDetail.get_TrxName(), false).commit();
	}

    public void updateInventoryValue() {
        if (accumulatedQuantity.signum() != 0)
        {
            dimension.setCurrentCostPrice(accumulatedAmount.divide(accumulatedQuantity, accountSchema.getCostingPrecision(),
                    BigDecimal.ROUND_HALF_UP));
            dimension.setCurrentCostPriceLL(accumulatedAmountLowerLevel.divide(accumulatedQuantity, accountSchema.getCostingPrecision(),
                    BigDecimal.ROUND_HALF_UP));
        }
        dimension.setCumulatedAmt(accumulatedAmount);
        dimension.setCumulatedAmtLL(accumulatedAmountLowerLevel);
        dimension.setCumulatedQty(accumulatedQuantity);
        dimension.setCurrentQty(accumulatedQuantity);
        dimension.saveEx();
    }


    /**
	 * Recalculate Cost Detail
	 * @param costDetail
     */
	public void adjustCostDetail(MCostDetail costDetail) {

        Properties ctx =  costDetail.getCtx();
        String trxName = costDetail.get_TrxName();
        int transactionId = costDetail.getM_Transaction_ID();
        int clientId = costDetail.getAD_Client_ID();

		MTransaction transaction = new MTransaction(ctx, transactionId, trxName);

		MCostType costType = (MCostType) costDetail.getM_CostType();
		MCostElement costElement = (MCostElement) costDetail.getM_CostElement();
		MAcctSchema accountSchema = (MAcctSchema) costDetail.getC_AcctSchema();


		if (MTransaction.MOVEMENTTYPE_VendorReceipts.equals(transaction.getMovementType()))
		{
			MInOutLine line = (MInOutLine) transaction.getDocumentLine();
			if (MCostElement.COSTELEMENTTYPE_Material.equals(costElement.getCostElementType()))
			{
                if (costDetail.getM_InOutLine_ID() > 0 && costDetail.getQty().signum() !=  0 )
                {
                    CostEngineFactory.getCostEngine(clientId).createCostDetail(
                            accountSchema, costType, costElement, transaction, line, true);
                }
                else if (costDetail.getM_InOutLine_ID() > 0 && costDetail.getQty().signum() != 0 && costDetail.getC_OrderLine_ID() > 0) {
                    List<MMatchPO> orderMatches = MMatchPO.getInOutLine(line);
                    for (MMatchPO match : orderMatches) {
                        if (match.getM_InOutLine_ID() == line.getM_InOutLine_ID()
                                && match.getM_Product_ID() == transaction.getM_Product_ID()) {
                            CostEngineFactory.getCostEngine(clientId)
                                    .createCostDetail(accountSchema, costType, costElement, transaction, match, true);
                        }
                    }
                }
                else if (costDetail.getM_InOutLine_ID() > 0 && costDetail.getQty().signum() == 0 && costDetail.getC_InvoiceLine_ID() > 0 ) {
                    List<MMatchInv> invoiceMatches = MMatchInv
                            .getInOutLine(line);
                    for (MMatchInv match : invoiceMatches) {
                        if (match.getM_Product_ID() == transaction.getM_Product_ID()) {
                            CostEngineFactory.getCostEngine(clientId)
                                    .createCostDetail(accountSchema, costType, costElement, transaction, match, true);
                        }
                    }
                }
			}

			//get landed allocation cost
			for (MLandedCostAllocation allocation : 
				MLandedCostAllocation.getOfInOuline(line,
							costElement.getM_CostElement_ID()))
			{
				//System.out.println("Allocation : " + allocation.getC_LandedCostAllocation_ID() +  " Amount:" +  allocation.getAmt());
				CostEngineFactory
				.getCostEngine(clientId)
				.createCostDetail(accountSchema, costType, costElement, transaction, allocation, true);
			}
		}
        else
            CostEngineFactory.getCostEngine(clientId).createCostDetail(
                    accountSchema, costType, costElement, transaction, transaction.getDocumentLine(), true);
	}	
}
