/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
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
 * @param <abtract>
 * 
 */
public abstract class AbstractCostingMethod implements ICostingMethod {
	protected final CLogger log = CLogger.getCLogger(getClass());

	MAcctSchema m_as;
	IDocumentLine m_model;
	MTransaction m_trx;
	MCost m_dimension;
	Boolean m_isSOTrx;
	BigDecimal m_costThisLevel;
	BigDecimal m_costLowLevel;
	BigDecimal m_cost;
	MCostDetail m_costdetail = null;
	BigDecimal m_movementQty = Env.ZERO;
	BigDecimal m_CumulatedAmt = Env.ZERO;
	BigDecimal m_CumulatedAmtLL = Env.ZERO;
	BigDecimal m_CumulatedQty = Env.ZERO;
	BigDecimal m_CurrentCostPrice = Env.ZERO;
	BigDecimal m_CurrentCostPriceLL = Env.ZERO;
	BigDecimal m_Amount = Env.ZERO;
	BigDecimal m_AmountLL = Env.ZERO;
	BigDecimal m_AdjustCost = Env.ZERO;
	BigDecimal m_AdjustCostLL = Env.ZERO;
	MCostDetail m_last_costdetail = null;
	String costingLevel;

	protected List<MCostDetail> createCostDetails(MCost cost, MTransaction mtrx) {
		IDocumentLine model = mtrx.getDocumentLine();

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
				MCostDetail cd = new MCostDetail(m_trx,
						m_as.getC_AcctSchema_ID(),
						m_dimension.getM_CostType_ID(),
						m_dimension.getM_CostElement_ID(), cc.getAmount(),
						Env.ZERO, cc.getQty(), m_model.get_TrxName());
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
				cd.setM_Transaction_ID(mtrx.get_ID());
				cd.setDescription(description.toString());
				cd.saveEx();
				list.add(cd);
			}
		} else // qty and amt is take from documentline
		{
			MCostDetail cd = new MCostDetail(m_trx, m_as.getC_AcctSchema_ID(),
					m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(),
					m_costThisLevel.multiply(model.getMovementQty()), Env.ZERO,
					model.getMovementQty(), m_model.get_TrxName());
			int id;
			if (model instanceof MMatchPO) {

				I_M_InOutLine iline = mtrx.getM_InOutLine();
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
			cd.setM_Transaction_ID(mtrx.get_ID());
			cd.saveEx();
			list.add(cd);
		}
		return list;
	}

	protected abstract List<CostComponent> getCalculatedCosts();

	/**
	 * Update the Inventory Value based in last transaction
	 */
	public void updateInventoryValue() {
		if (m_CumulatedQty.signum() != 0)
		{	
			m_dimension.setCurrentCostPrice(m_CumulatedAmt.divide(m_CumulatedQty, m_as.getCostingPrecision(),
					BigDecimal.ROUND_HALF_UP));
			m_dimension.setCurrentCostPriceLL(m_CumulatedAmtLL.divide(m_CumulatedQty, m_as.getCostingPrecision(),
					BigDecimal.ROUND_HALF_UP));
		}
		m_dimension.setCumulatedAmt(m_CumulatedAmt);
		m_dimension.setCumulatedAmtLL(m_CumulatedAmtLL);
		m_dimension.setCumulatedQty(m_CumulatedQty);
		m_dimension.setCurrentQty(m_CumulatedQty);
		m_dimension.saveEx();
	}

	/**
	 * Create Reversal Transaction
	 */
	public void createReversalCostDetail() {
		
		MTransaction original_trx = MTransaction.getByDocumentLine(m_trx);
		if (original_trx == null)
			 throw new AdempiereException("Can not found the original transaction");
		
		m_costdetail = new MCostDetail(m_model.getCtx(), 0, m_trx.get_TrxName());

		IDocumentLine model = original_trx.getDocumentLine();
		String idColumnName = model.get_TableName() + "_ID";

		// Qty Transaction
		m_last_costdetail = MCostDetail.getByTransaction(model,
				original_trx, m_as.getC_AcctSchema_ID(),
				m_dimension.getM_CostType_ID(),
				m_dimension.getM_CostElement_ID());
		if (m_last_costdetail == null)
		{	
			m_last_costdetail = MCostDetail.getByTransaction(model,
					original_trx, m_as.getC_AcctSchema_ID(),
					m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID());
			
			 throw new
					 AdempiereException("Can not found the original cost detail");
		}	 

			m_costdetail.setAD_Org_ID(m_last_costdetail.getAD_Org_ID());
			m_costdetail.setM_Warehouse_ID(m_last_costdetail.getM_Warehouse_ID());
			m_costdetail.copyValues(m_last_costdetail, m_costdetail);
			
			setReversalCostDetail();
			
			m_costdetail.setM_AttributeSetInstance_ID(m_trx
					.getM_AttributeSetInstance_ID());

			m_costdetail.setDateAcct(m_model.getDateAcct());
			//m_costdetail.setProcessing(false); not should change so that be costing re processing by early transaction
			m_costdetail.setM_Transaction_ID(m_trx.getM_Transaction_ID());
			m_costdetail.setDescription("Reversal "
					+ original_trx.getM_Transaction_ID());
			m_costdetail.setIsReversal(true);
			m_costdetail.saveEx();

			// Update the original cost detail
			m_last_costdetail
					.setDescription(m_last_costdetail.getDescription() != null ? m_last_costdetail
							.getDescription() : "" + "|Reversal "
							+ m_costdetail.getM_Transaction_ID());
			m_last_costdetail.setIsReversal(true);
			m_last_costdetail.saveEx(m_trx.get_TrxName());
			
		// Only uncomment to debug Trx.get(m_costdetail.get_TrxName(),
		// false).commit();
	}
	
	protected void setReversalCostDetail()
	{
		m_costdetail.setCurrentCostPrice(getNewCurrentCostPrice(
				m_last_costdetail, m_as.getCostingPrecision(),
				BigDecimal.ROUND_HALF_UP));
		
		m_costdetail.setCurrentCostPriceLL(getNewCurrentCostPriceLL(
				m_last_costdetail, m_as.getCostingPrecision(),
				BigDecimal.ROUND_HALF_UP));
		m_costdetail.setCurrentQty(Env.ZERO);
		m_costdetail.setQty(Env.ZERO);
		m_costdetail.setAmt(Env.ZERO);
		m_costdetail.setCostAmt(Env.ZERO);
		m_costdetail.setCostAdjustment(Env.ZERO);
		m_costdetail.setAmtLL(Env.ZERO);
		m_costdetail.setCostAmtLL(Env.ZERO);
		m_costdetail.setCostAdjustmentLL(Env.ZERO);
		m_costdetail.setCumulatedAmt(Env.ZERO);
		m_costdetail.setCumulatedAmtLL(Env.ZERO);
		m_costdetail.setCumulatedQty(Env.ZERO);
		
		m_costdetail.setSeqNo(m_last_costdetail.getSeqNo() + 10);
		m_costdetail.setQty(m_last_costdetail.getQty().negate());
		m_costdetail.setAmt(m_last_costdetail.getAmt());
		m_costdetail.setCostAmt(m_last_costdetail.getCostAmt());
	
		m_costdetail.setCostAdjustment(m_last_costdetail.getCostAdjustment());
		m_costdetail.setCostAdjustmentDate(m_last_costdetail
				.getCostAdjustmentDate());
		
		m_CurrentCostPrice = m_last_costdetail.getCurrentCostPrice();
		m_CurrentCostPriceLL = m_last_costdetail.getCurrentCostPriceLL();
		
		updateAmtCost();
		
		// Update the new cost detail
		m_CumulatedQty = getNewCumulatedQty(m_costdetail);
		m_CumulatedAmt = getNewCumulatedAmt(m_costdetail);
		m_CumulatedAmtLL = getNewCumulatedAmtLL(m_costdetail);
		m_CurrentCostPrice = getNewCurrentCostPrice(m_costdetail,
				m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
	}
	
	public abstract void updateAmtCost();

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
	abstract public BigDecimal getNewCumulatedAmt(MCostDetail cd);

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
	abstract public BigDecimal getNewCurrentCostPriceLL(MCostDetail cd,
			int scale, int roundingMode);

	/**
	 * Method to implement the costing method Get the new Cumulated Amt Low
	 * Level
	 * 
	 * @param cd
	 *            MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	abstract public BigDecimal getNewCumulatedAmtLL(MCostDetail cd);

	/**
	 * Method to implement the costing method Get the new Cumulated Qty
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Qty
	 */
	abstract public BigDecimal getNewCumulatedQty(MCostDetail cd);

	/**
	 * Recalculate Cost Detail
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param ct
	 *            Cost Type
	 * @param ce
	 *            Cost Element
	 */
	public void adjustCostDetail(MCostDetail cd) {
		MCostType ct = (MCostType) cd.getM_CostType();
		MCostElement ce = (MCostElement) cd.getM_CostElement();
		MTransaction trx = new MTransaction(cd.getCtx(),
				cd.getM_Transaction_ID(), cd.get_TrxName());
		IDocumentLine docLine = trx.getDocumentLine();
		CostEngineFactory.getCostEngine(cd.getAD_Client_ID()).createCostDetail(
				m_as, trx, docLine, ct, ce);
	}

	public void clearAccounting(MCostDetail cd) {
		// Only can delete if period is open
		MTransaction trx = new MTransaction(cd.getCtx(),
				cd.getM_Transaction_ID(), cd.get_TrxName());
		IDocumentLine docLine = trx.getDocumentLine();
		MPeriod.testPeriodOpen(cd.getCtx(), cd.getDateAcct(),
				docLine.getC_DocType_ID(), cd.getAD_Org_ID());

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
