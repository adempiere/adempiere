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
 * @author Systemhaus Westfalia SusanneCalderon <susanne.de.calderon@westfalia-it.com>
 *    <li> New method for searching original cost detail of cancelled match inv and match PO</>
 *    https://github.com/adempiere/adempiere/issues/1918
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

	protected List<MCostDetail> createCostDetails() {
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
			List<CostComponent> costComponents = getCalculatedCosts();
			for (CostComponent costComponent : costComponents) {
				MCostDetail cost = new MCostDetail(transaction,
						accountSchema.getC_AcctSchema_ID(),
						dimension.getM_CostType_ID(),
						dimension.getM_CostElement_ID(), costComponent.getAmount(),
						Env.ZERO, costComponent.getQty(), model.get_TrxName());
				if (!cost.set_ValueOfColumnReturningBoolean(idColumnName, model.get_ID()))
					throw new AdempiereException("Cannot set " + idColumnName);

				StringBuilder description = new StringBuilder();
				if (!Util.isEmpty(model.getDescription(), true))
					description.append(model.getDescription());
				if (model.isSOTrx() != false) {
					description.append(model.isSOTrx() ? "(|->)" : "(|<-)");
				}
				if (model.isSOTrx() != false) // TODO: need evaluate anca
					cost.setIsSOTrx(model.isSOTrx());
				else
					cost.setIsSOTrx(model.isSOTrx());
				cost.setM_Transaction_ID(transaction.get_ID());
				cost.setDescription(description.toString());
				cost.saveEx();
				list.add(cost);
			}
		} else // qty and amt is take from documentline
		{
			MCostDetail cost = new MCostDetail(transaction, accountSchema.getC_AcctSchema_ID(),
					dimension.getM_CostType_ID(),
					dimension.getM_CostElement_ID(),
					costThisLevel.multiply(model.getMovementQty()), Env.ZERO,
					model.getMovementQty(), model.get_TrxName());
			int id;
			if (model instanceof MMatchPO) {

				I_M_InOutLine inOutLine = transaction.getM_InOutLine();
				I_C_OrderLine orderLine = inOutLine.getC_OrderLine();
				id = orderLine.getC_OrderLine_ID();

			} else {
				id = model.get_ID();
			}
			if (!cost.set_ValueOfColumnReturningBoolean(idColumnName, id))
				throw new AdempiereException("Cannot set " + idColumnName);
			if (model.isSOTrx() != false)
				cost.setIsSOTrx(model.isSOTrx());
			else
				cost.setIsSOTrx(model.isSOTrx());
			cost.setM_Transaction_ID(transaction.get_ID());
			cost.saveEx();
			list.add(cost);
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
	    String description = "";
		if (model instanceof MMatchInv){
			MMatchInv original = new MMatchInv(model.getCtx(), ((MMatchInv) model).getReversal_ID(), model.get_TrxName());
			if (original == null)
			    return ;
			StringBuffer whereClause = new StringBuffer();
			whereClause.append(" M_CostType_ID=" + dimension.getM_CostType_ID());
			whereClause.append(" AND M_CostElement_ID=" + dimension.getM_CostElement_ID());
			whereClause.append(" AND M_MatchInv_ID=?");
			lastCostDetail = MCostDetail.get(original.getCtx(),whereClause.toString(),
					original.getM_MatchInv_ID(),
					original.getM_AttributeSetInstance_ID(), dimension.getC_AcctSchema_ID(), original.get_TrxName());
			if (lastCostDetail == null){
			    whereClause = new StringBuffer();
                whereClause.append(" M_CostType_ID=" + dimension.getM_CostType_ID());
                whereClause.append(" AND M_CostElement_ID=" + dimension.getM_CostElement_ID());
                whereClause.append(" AND C_Invoiceline_ID=" + original.getC_InvoiceLine_ID());
                whereClause.append(" AND M_InoutLine_ID=?");
                lastCostDetail = MCostDetail.get(original.getCtx(),whereClause.toString(),
                        original.getM_InOutLine_ID(),
                        original.getM_AttributeSetInstance_ID(), dimension.getC_AcctSchema_ID(), original.get_TrxName());
                if (lastCostDetail == null)
                    return;
            }

			description = "Reversal MatchInv " + original.getDocumentNo();
		}

        else if (model instanceof MMatchPO){
            MMatchPO original = new MMatchPO(model.getCtx(), ((MMatchInv) model).getReversal_ID(), model.get_TrxName());
            StringBuffer whereClause = new StringBuffer();
            whereClause.append(" M_CostType_ID=" + dimension.getM_CostType_ID());
            whereClause.append(" AND M_CostElement_ID=" + dimension.getM_CostElement_ID());
            whereClause.append(" AND M_MatchPO_ID=?");
            lastCostDetail = MCostDetail.get(original.getCtx(),whereClause.toString(),
                    original.getM_MatchPO_ID(),
                    original.getM_AttributeSetInstance_ID(), dimension.getC_AcctSchema_ID(), original.get_TrxName());
            if (lastCostDetail ==null)
                return;
            description = "Reversal MatchPO " + original.getDocumentNo();
        }
		else{
			MTransaction originalTransaction = MTransaction.getByDocumentLine(transaction);
			if (originalTransaction == null)
			{
				//throw new AdempiereException("Can not found the original transaction");
				//System.out.println("Transaction not found :" + transaction);
				log.info("Transaction not found :" + transaction);
				return;
			}lastCostDetail = MCostDetail.getByTransaction(
					originalTransaction.getDocumentLine(),
					originalTransaction,
					accountSchema.getC_AcctSchema_ID(),
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
				//System.out.println("Detail Cost not found :" + originalTransaction);
				log.info("Detail Cost not found :" + originalTransaction);
				return;
			}
			description = "Reversal " + originalTransaction.getM_Transaction_ID();
		}

		costDetail = new MCostDetail(model.getCtx(), 0, transaction.get_TrxName());
		// Qty Transaction

            costDetail.copyValues(lastCostDetail, costDetail);
			costDetail.setAD_Org_ID(lastCostDetail.getAD_Org_ID());
			costDetail.setM_Warehouse_ID(lastCostDetail.getM_Warehouse_ID());
			setReversalCostDetail();
			costDetail.setM_AttributeSetInstance_ID(transaction.getM_AttributeSetInstance_ID());
			costDetail.setDateAcct(model.getDateAcct());
			//costDetail.setProcessing(false); not should change so that be costing re processing by early transaction
			//costDetail.setM_Transaction_ID(transaction.getM_Transaction_ID());
			costDetail.setDescription(description);
			costDetail.setIsReversal(true);
			costDetail.saveEx();
			// Update the original cost detail
			lastCostDetail.setDescription(lastCostDetail.getDescription() != null ? lastCostDetail.getDescription() : "" + "|Reversal " + costDetail.getM_Transaction_ID());
			lastCostDetail.setIsReversal(true);
			lastCostDetail.saveEx(transaction.get_TrxName());
			if (model.getDateAcct().compareTo(lastCostDetail.getDateAcct()) != 0){
			    lastCostDetail = MCostDetail.getLastTransaction(model, transaction,
                        accountSchema.getC_AcctSchema_ID(), dimension.getM_CostType_ID(),
                        dimension.getM_CostElement_ID(),dateAccounting,
                        costingLevel);
			    costDetail.setSeqNo(lastCostDetail.getSeqNo() + 10);
			    costDetail.setCumulatedQty(getNewAccumulatedQuantity(lastCostDetail));
			    costDetail.setCumulatedAmt(getNewAccumulatedAmount(lastCostDetail));
			    costDetail.setCumulatedAmtLL(getNewAccumulatedAmountLowerLevel(lastCostDetail));

			    updateAmountCost();
			    updateInventoryValue();
            }

		// Only uncomment to debug Trx.get(costDetail.get_TrxName(),
		// false).commit();
	}
	
	protected void setReversalCostDetail()
	{
		costDetail.setCurrentCostPrice(getNewCurrentCostPrice(lastCostDetail, accountSchema.getCostingPrecision(),BigDecimal.ROUND_HALF_UP));
		costDetail.setCurrentCostPriceLL(getNewCurrentCostPriceLowerLevel(lastCostDetail, accountSchema.getCostingPrecision(),BigDecimal.ROUND_HALF_UP));
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
		costDetail.setAmtLL(lastCostDetail.getAmtLL());
		costDetail.setCostAmtLL(lastCostDetail.getCostAmtLL());
		costDetail.setCostAdjustmentLL(lastCostDetail.getCostAdjustmentLL());
		costDetail.setCostAdjustmentDate(lastCostDetail.getCostAdjustmentDate());
		currentCostPrice = lastCostDetail.getCurrentCostPrice();
		currentCostPriceLowerLevel = lastCostDetail.getCurrentCostPriceLL();
		if (model instanceof MMatchInv){

            costDetail.setAmt(lastCostDetail.getAmt().negate());
            costDetail.setCostAmt(lastCostDetail.getCostAmt().negate());
            costDetail.setCostAdjustment(lastCostDetail.getCostAdjustment().negate());
            costDetail.setAmtLL(lastCostDetail.getAmtLL().negate());
            costDetail.setCostAmtLL(lastCostDetail.getCostAmtLL().negate());
            costDetail.setCostAdjustmentLL(lastCostDetail.getCostAdjustmentLL().negate());
            costDetail.setCostAdjustmentDate(lastCostDetail.getCostAdjustmentDate());
            currentCostPrice = lastCostDetail.getCurrentCostPrice().negate();
            currentCostPriceLowerLevel = lastCostDetail.getCurrentCostPriceLL().negate();
        }

		updateAmountCost();
		
		// Update the new cost detail
		accumulatedQuantity = getNewAccumulatedQuantity(costDetail);
		accumulatedAmount = getNewAccumulatedAmount(costDetail);
		accumulatedAmountLowerLevel = getNewAccumulatedAmountLowerLevel(costDetail);
		currentCostPrice = getNewCurrentCostPrice(costDetail, accountSchema.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
	}
	
	public abstract void updateAmountCost();

	/**
	 * Method to implement the costing method Get the New Current Cost Price
	 * This Level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	abstract public BigDecimal getNewCurrentCostPrice(MCostDetail cost, int scale, int roundingMode);

	/**
	 * Method to implement the costing method Get the New Cumulated Amt This Level
	 * @param cost Cost Detail
	 * @return New Cumulated Amt This Level
	 */
	abstract public BigDecimal getNewAccumulatedAmount(MCostDetail cost);

	/**
	 * Method to implement the costing method Get the New Current Cost Price low level
	 * @param cost Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 */
	abstract public BigDecimal getNewCurrentCostPriceLowerLevel(MCostDetail cost, int scale, int roundingMode);

	/**
	 * Method to implement the costing method Get the new Cumulated Amt Low Level
	 * @param cost MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	abstract public BigDecimal getNewAccumulatedAmountLowerLevel(MCostDetail cost);

	/**
	 * Method to implement the costing method Get the new Cumulated Qty
	 * @param cost Cost Detail
	 * @return New Cumulated Qty
	 */
	abstract public BigDecimal getNewAccumulatedQuantity(MCostDetail cost);

	/**
	 * Recalculate Cost Detail
	 * @param cost
	 */
	public void adjustCostDetail(MCostDetail cost) {
		MCostType costType = (MCostType) cost.getM_CostType();
		MCostElement costElement = (MCostElement) cost.getM_CostElement();
		MTransaction transaction = new MTransaction(cost.getCtx(), cost.getM_Transaction_ID(), cost.get_TrxName());
		IDocumentLine docLine = transaction.getDocumentLine();
		CostEngineFactory.getCostEngine(cost.getAD_Client_ID()).createCostDetail(
                (MAcctSchema) cost.getC_AcctSchema(), costType, costElement, transaction, docLine, true);
	}

	public void clearAccounting(MCostDetail cost) {
		// Only can delete if period is open
		MTransaction trx = new MTransaction(cost.getCtx(), cost.getM_Transaction_ID(), cost.get_TrxName());
		IDocumentLine documentLine = trx.getDocumentLine();
		MDocType docType = MDocType.get(cost.getCtx(), documentLine.getC_DocType_ID());
		Boolean openPeriod = MPeriod.isOpen(cost.getCtx(),  cost.getDateAcct() ,  docType .getDocBaseType(),  cost.getAD_Org_ID());
		
		if(!openPeriod)
			return;

		String sqldelete = "DELETE FROM Fact_Acct WHERE Record_ID =? AND AD_Table_ID=?";
		int tableId = 0;
		int recordId = 0;
		if (cost.getC_OrderLine_ID() != 0) {
			MOrderLine line = (MOrderLine) cost.getC_OrderLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			recordId = line.getParent().get_ID();
			tableId = line.getParent().get_Table_ID();
		}
		if (cost.getM_InOutLine_ID() != 0) {
			MInOutLine line = (MInOutLine) cost.getM_InOutLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			recordId = line.getParent().get_ID();
			tableId = line.getParent().get_Table_ID();
		}

		if (cost.getM_InventoryLine_ID() != 0) {
			MInventoryLine line = (MInventoryLine) cost.getM_InventoryLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			recordId = line.getParent().get_ID();
			tableId = line.getParent().get_Table_ID();
		}

		if (cost.getM_MovementLine_ID() != 0) {
			MMovementLine line = (MMovementLine) cost.getM_MovementLine();
			line.getParent().setPosted(false);
			line.getParent().saveEx();
			recordId = line.getParent().get_ID();
			tableId = line.getParent().get_Table_ID();
		}
		if (cost.getM_ProductionLine_ID() != 0) {
		}

		if (cost.getPP_Cost_Collector_ID() != 0) {
			MPPCostCollector costCollector = (MPPCostCollector) cost.getPP_Cost_Collector();
			costCollector.setPosted(false);
			costCollector.saveEx();
			recordId = costCollector.get_ID();
			tableId = costCollector.get_Table_ID();
		}
		int no = DB.executeUpdateEx(sqldelete, new Object[] { recordId, tableId }, cost.get_TrxName());
	}
}
