/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_M_InOutLine;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MDocType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPeriod;
import org.compiere.model.MTransaction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.eevolution.model.MPPCostCollector;

/**
 * @author anca_bradau
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */

/**
 * Abstract Costing Method
 */
public abstract class AbstractCostingMethod implements ICostingMethod {
	protected final CLogger log = CLogger.getCLogger(getClass());

	protected MAcctSchema accountSchema;
    protected Timestamp dateAccounting;
    protected Boolean isOpenPeriod = null;
    protected BigDecimal movementQuantity =  Env.ZERO;
    protected IDocumentLine model;
    protected MTransaction transaction;
    protected MCost dimension;
    protected Boolean isSalesTransaction;
    protected BigDecimal costThisLevel;
    protected BigDecimal costLowLevel;
    protected MCostDetail costDetail = null;
    protected BigDecimal accumulatedAmount = Env.ZERO;
    protected BigDecimal accumulatedAmountLowerLevel = Env.ZERO;
    protected BigDecimal accumulatedQuantity = Env.ZERO;
    protected BigDecimal currentCostPrice = Env.ZERO;
    protected BigDecimal currentCostPriceLowerLevel = Env.ZERO;
    protected BigDecimal amount = Env.ZERO;
    protected BigDecimal amountLowerLevel = Env.ZERO;
    protected BigDecimal adjustCost = Env.ZERO;
    protected BigDecimal adjustCostLowerLevel = Env.ZERO;
    protected MCostDetail lastCostDetail = null;
    protected String costingLevel;

	protected List<MCostDetail> createCostDetails(MCost cost, MTransaction transaction) {
		IDocumentLine model = transaction.getDocumentLine();

		final String idColumnName;
		if (model instanceof MMatchPO) {
			idColumnName = I_C_OrderLine.COLUMNNAME_C_OrderLine_ID;
		} else if (model instanceof MMatchInv) {
			idColumnName = I_C_InvoiceLine.COLUMNNAME_C_InvoiceLine_ID;
		} else {
			idColumnName = model.get_TableName() + "_ID";
		}

		List<MCostDetail> list = new ArrayList<MCostDetail>();
		if (model.isSOTrx() == true || model instanceof MInventoryLine
				|| model instanceof MMovementLine) {
			List<CostComponent> ccs = getCalculatedCosts();
			for (CostComponent cc : ccs) {
				MCostDetail cd = new MCostDetail(this.transaction,
						accountSchema.getC_AcctSchema_ID(),
						dimension.getM_CostType_ID(),
						dimension.getM_CostElement_ID(), cc.getAmount(),
						Env.ZERO, cc.getQty(), this.model.get_TrxName());
				if (!cd.set_ValueOfColumnReturningBoolean(idColumnName,
						model.get_ID()))
					throw new AdempiereException("Cannot set " + idColumnName);

				StringBuilder description = new StringBuilder();
				if (!Util.isEmpty(model.getDescription(), true))
					description.append(model.getDescription());
				if (model.isSOTrx() != false) {
					description.append(model.isSOTrx() ? "(|->)" : "(|<-)");
				}
				if (model.isSOTrx() != false) // TODO: need evaluate anca
					cd.setIsSOTrx(model.isSOTrx());
				else
					cd.setIsSOTrx(model.isSOTrx());
				cd.setM_Transaction_ID(transaction.get_ID());
				cd.setDescription(description.toString());
				cd.saveEx();
				list.add(cd);
			}
		} else // qty and amt is take from documentline
		{
			MCostDetail cd = new MCostDetail(this.transaction, accountSchema.getC_AcctSchema_ID(),
					dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(),
					costThisLevel.multiply(model.getMovementQty()), Env.ZERO,
					model.getMovementQty(), this.model.get_TrxName());
			int id;
			if (model instanceof MMatchPO) {

				I_M_InOutLine iline = transaction.getM_InOutLine();
				I_C_OrderLine oline = iline.getC_OrderLine();
				id = oline.getC_OrderLine_ID();

			} else {
				id = model.get_ID();
			}
			if (!cd.set_ValueOfColumnReturningBoolean(idColumnName, id))
				throw new AdempiereException("Cannot set " + idColumnName);
			if (model.isSOTrx() != false)
				cd.setIsSOTrx(model.isSOTrx());
			else
				cd.setIsSOTrx(model.isSOTrx());
			cd.setM_Transaction_ID(transaction.get_ID());
			cd.saveEx();
			list.add(cd);
		}
		return list;
	}

	protected abstract List<CostComponent> getCalculatedCosts();

	/**
	 * Update the Inventory Value based in last transaction
	 */
    protected  void updateInventoryValue()
    {

    }

	/**
	 * Create Reversal Transaction
	 */
	public void createReversalCostDetail() {
		
		MTransaction originalTransaction = MTransaction.getByDocumentLine(transaction);
		if (originalTransaction == null)
		{	
			 //throw new AdempiereException("Can not found the original transaction");
			System.out.println("Transaction not found :" + transaction);
			return;
		}	
		
		costDetail = new MCostDetail(model.getCtx(), 0, transaction.get_TrxName());
		// Qty Transaction
		lastCostDetail = MCostDetail.getByTransaction(originalTransaction.getDocumentLine(),
                originalTransaction, accountSchema.getC_AcctSchema_ID(),
                dimension.getM_CostType_ID(),
                dimension.getM_CostElement_ID());
		if (lastCostDetail == null)
		{	
			/*lastCostDetail = MCostDetail.getByTransaction(model,
					original_trx, accountSchema.getC_AcctSchema_ID(),
					dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID());*/
			
			 //throw new
			 //		 AdempiereException("Can not found the original cost detail");
			System.out.println("Detail Cost not found :" + originalTransaction);
			return;
		}

            costDetail.copyValues(lastCostDetail, costDetail);
			costDetail.setAD_Org_ID(lastCostDetail.getAD_Org_ID());
			costDetail.setM_Warehouse_ID(lastCostDetail.getM_Warehouse_ID());

			
			setReversalCostDetail();
			
			costDetail.setM_AttributeSetInstance_ID(transaction
                    .getM_AttributeSetInstance_ID());

			costDetail.setDateAcct(this.model.getDateAcct());
			//costDetail.setProcessing(false); not should change so that be costing re processing by early transaction
			//costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
			costDetail.setDescription("Reversal "
                    + originalTransaction.getM_Transaction_ID());
			costDetail.setIsReversal(true);
			costDetail.saveEx();

			// Update the original cost detail
			lastCostDetail
					.setDescription(lastCostDetail.getDescription() != null ? lastCostDetail
                            .getDescription() : "" + "|Reversal "
                            + costDetail.getM_Transaction_ID());
			lastCostDetail.setIsReversal(true);
			lastCostDetail.saveEx(transaction.get_TrxName());
			
		// Only uncomment to debug Trx.get(costDetail.get_TrxName(),
		// false).commit();
	}
	
	protected void setReversalCostDetail()
	{
		costDetail.setCurrentCostPrice(getNewCurrentCostPrice(
                lastCostDetail, accountSchema.getCostingPrecision(),
                BigDecimal.ROUND_HALF_UP));
		
		costDetail.setCurrentCostPriceLL(getNewCurrentCostPriceLowerLevel(
                lastCostDetail, accountSchema.getCostingPrecision(),
                BigDecimal.ROUND_HALF_UP));
		costDetail.setCurrentQty(Env.ZERO);
		costDetail.setQty(Env.ZERO);
		costDetail.setAmt(Env.ZERO);
		costDetail.setCostAmt(Env.ZERO);
		costDetail.setCostAdjustment(Env.ZERO);
		costDetail.setAmtLL(Env.ZERO);
		costDetail.setCostAmtLL(Env.ZERO);
		costDetail.setCostAdjustmentLL(Env.ZERO);
		costDetail.setCumulatedAmt(Env.ZERO);
		costDetail.setCumulatedAmtLL(Env.ZERO);
		costDetail.setCumulatedQty(Env.ZERO);
        costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
		
		costDetail.setSeqNo(lastCostDetail.getSeqNo() + 10);
		costDetail.setQty(lastCostDetail.getQty().negate());
		costDetail.setAmt(lastCostDetail.getAmt());
		costDetail.setCostAmt(lastCostDetail.getCostAmt());
	
		costDetail.setCostAdjustment(lastCostDetail.getCostAdjustment());
		costDetail.setCostAdjustmentDate(lastCostDetail
                .getCostAdjustmentDate());
		
		currentCostPrice = lastCostDetail.getCurrentCostPrice();
		currentCostPriceLowerLevel = lastCostDetail.getCurrentCostPriceLL();
		
		updateAmountCost();
		
		// Update the new cost detail
		accumulatedQuantity = getNewAccumulatedQuantity(costDetail);
		accumulatedAmount = getNewAccumulatedAmount(costDetail);
		accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(costDetail);
		currentCostPrice = getNewCurrentCostPrice(costDetail,
                accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
	}
	
	public abstract void updateAmountCost();

	/**
	 * Method to implement the costing method Get the New Current Cost Price
	 * This Level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param scale
	 *            Scale
	 * @param roundingMode
	 *            Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	abstract public BigDecimal getNewCurrentCostPrice(MCostDetail cd,
			int scale, int roundingMode);

	/**
	 * Method to implement the costing method Get the New Cumulated Amt This
	 * Level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Amt This Level
	 */
	abstract public BigDecimal getNewAccumulatedAmount(MCostDetail cd);

	/**
	 * Method to implement the costing method Get the New Current Cost Price low
	 * level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param scale
	 *            Scale
	 * @param roundingMode
	 *            Rounding Mode
	 * @return New Current Cost Price low level
	 */
	abstract public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cd,
                                                                int scale, int roundingMode);

	/**
	 * Method to implement the costing method Get the new Cumulated Amt Low
	 * Level
	 * 
	 * @param cd
	 *            MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	abstract public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail cd);

	/**
	 * Method to implement the costing method Get the new Cumulated Qty
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Qty
	 */
	abstract public BigDecimal getNewAccumulatedQuantity(MCostDetail cd);

	/**
	 * Recalculate Cost Detail
	 * @param costDetail
	 */
	public void adjustCostDetail(MCostDetail costDetail) {
		MCostType costType = (MCostType) costDetail.getM_CostType();
		MCostElement costElement = (MCostElement) costDetail.getM_CostElement();
		MTransaction transaction = new MTransaction(costDetail.getCtx(),
				costDetail.getM_Transaction_ID(), costDetail.get_TrxName());
		IDocumentLine docLine = transaction.getDocumentLine();
		CostEngineFactory.getCostEngine(costDetail.getAD_Client_ID()).createCostDetail(
                (MAcctSchema) costDetail.getC_AcctSchema(), costType, costElement, transaction, docLine, true);
	}

	public void clearAccounting(MCostDetail cd) {
		// Only can delete if period is open
		MTransaction trx = new MTransaction(cd.getCtx(),
				cd.getM_Transaction_ID(), cd.get_TrxName());
		IDocumentLine docLine = trx.getDocumentLine();
		MDocType dt = MDocType.get(cd.getCtx(), docLine.getC_DocType_ID());
		Boolean openPeriod = MPeriod.isOpen(cd.getCtx(),  cd.getDateAcct() ,  dt .getDocBaseType(),  cd.getAD_Org_ID());
		
		if(!openPeriod)
			return;

		String sqldelete = "DELETE FROM Fact_Acct WHERE Record_ID =? AND AD_Table_ID=?";
		int ad_table_id = 0;
		int record_id = 0;
		if (cd.getC_OrderLine_ID() != 0) {
			MOrderLine line = (MOrderLine) cd.getC_OrderLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			record_id = line.getParent().get_ID();
			ad_table_id = line.getParent().get_Table_ID();
		}
		if (cd.getM_InOutLine_ID() != 0) {
			MInOutLine line = (MInOutLine) cd.getM_InOutLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			record_id = line.getParent().get_ID();
			ad_table_id = line.getParent().get_Table_ID();
		}

		if (cd.getM_InventoryLine_ID() != 0) {
			MInventoryLine line = (MInventoryLine) cd.getM_InventoryLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			record_id = line.getParent().get_ID();
			ad_table_id = line.getParent().get_Table_ID();
		}

		if (cd.getM_MovementLine_ID() != 0) {
			MMovementLine line = (MMovementLine) cd.getM_MovementLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			record_id = line.getParent().get_ID();
			ad_table_id = line.getParent().get_Table_ID();
		}
		if (cd.getM_ProductionLine_ID() != 0) {
		}

		if (cd.getPP_Cost_Collector_ID() != 0) {
			MPPCostCollector cc = (MPPCostCollector) cd.getPP_Cost_Collector();
			cc.setPosted(false);
			cc.saveEx();
			record_id = cc.get_ID();
			ad_table_id = cc.get_Table_ID();
		}
		int no = DB.executeUpdateEx(sqldelete, new Object[] { record_id,
				ad_table_id }, cd.get_TrxName());
	}
}
