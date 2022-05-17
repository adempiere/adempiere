/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.util.Env;

/**
* @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 * 
 */
public class UserDefinedCostingMethodExample extends AbstractCostingMethod
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
		this.model = model;
		this.costingLevel = MProduct.get(this.transaction.getCtx(), this.transaction.getM_Product_ID())
				.getCostingLevel(accountSchema, transaction.getAD_Org_ID());
		// find if this transaction exist into cost detail
		this.costDetail = MCostDetail.getByTransaction(this.model, this.transaction,
				this.accountSchema.getC_AcctSchema_ID(), this.dimension.getM_CostType_ID(),
				this.dimension.getM_CostElement_ID());


        this.movementQuantity = transaction.getMovementQty();
	}


	public MCostDetail process() {	
		costDetail = new MCostDetail(transaction, accountSchema.getC_AcctSchema_ID(),
				dimension.getM_CostType_ID(),
				dimension.getM_CostElement_ID(), Env.ONEHUNDRED.multiply(costThisLevel),
				Env.ONEHUNDRED.multiply(costThisLevel),
				movementQuantity, transaction.get_TrxName());
		costDetail.setDescription("Example User Defined Costing Method");
		costDetail.setDateAcct(model.getDateAcct());
		costDetail.saveEx();
		return costDetail;
	}

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAmountCost() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal getNewCurrentCostPrice(MCostDetail cost, int scale, int roundingMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewCurrentCostPrice(MCostDetail cost, int scale, RoundingMode roundingMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewAccumulatedAmount(MCostDetail cost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cost, int scale, int roundingMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cost, int scale, RoundingMode roundingMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail cost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getNewAccumulatedQuantity(MCostDetail cost) {
		// TODO Auto-generated method stub
		return null;
	}

}
