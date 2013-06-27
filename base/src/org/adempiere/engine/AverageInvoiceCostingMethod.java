/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostType;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.util.Env;

/**
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 * 
 */
public class AverageInvoiceCostingMethod extends AbstractCostingMethod
		implements ICostingMethod {

	/*
	 * Constructor for Cost Engine
	 * 
	 * @see
	 * org.adempiere.engine.ICostingMethod#setCostingMethod(org.compiere.model
	 * .MAcctSchema, org.compiere.model.MTransaction,
	 * org.adempiere.engine.IDocumentLine, org.compiere.model.MCost,
	 * java.math.BigDecimal, java.math.BigDecimal, java.lang.Boolean)
	 */
	public void setCostingMethod(MAcctSchema as, IDocumentLine model,
			MTransaction mtrx, MCost dimension, BigDecimal costThisLevel,
			BigDecimal costLowLevel, Boolean isSOTrx) {
		m_as = as;
		m_trx = mtrx;
		m_dimension = dimension;
		m_costThisLevel = (costThisLevel == null ? Env.ZERO : costThisLevel);
		m_costLowLevel = (costLowLevel == null ? Env.ZERO : costLowLevel);
		m_isSOTrx = isSOTrx;
		m_model = model;
		costingLevel = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID())
				.getCostingLevel(as, mtrx.getAD_Org_ID());
		// find if this transaction exist into cost detail
		m_costdetail = MCostDetail.getByTransaction(model, m_trx,
				m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
				m_dimension.getM_CostElement_ID());
	}

	public void calculate() {
		// try find the last cost detail transaction
		m_last_costdetail = MCostDetail.getLastTransaction(m_model, m_trx,
				m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
				m_dimension.getM_CostElement_ID(), m_model.getDateAcct(),
				costingLevel);

		// If model is reversal then no calculate cost
		if (m_model.getReversalLine_ID() > 0 && m_costdetail == null)
			return;
		
		// created a new instance cost detail to process calculated cost
		if (m_last_costdetail == null) { 
			m_last_costdetail = new MCostDetail(m_trx,
					m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(), Env.ZERO, Env.ZERO,
					Env.ZERO, m_trx.get_TrxName());
			m_last_costdetail.setDateAcct(m_model.getDateAcct());
		}
			
				
		// The cost detail was created before then is necessary to update cost by
		// generate adjustment	
		if (m_trx.getM_Transaction_ID() == m_last_costdetail.getM_Transaction_ID()) {
			m_Amount = m_model.getMovementQty().multiply(m_costThisLevel); // total
																			// adjustment
																			// this
																			// level
			m_AmountLL = m_model.getMovementQty().multiply(m_costLowLevel); // total
																			// adjustment
																			// low
																			// level
			m_CumulatedQty = getNewCumulatedQty(m_last_costdetail); // Quantity
																	// Cumulated
																	// from last
																	// transaction
			// Cost Adjustment
			m_AdjustCost = m_Amount.subtract(m_last_costdetail.getCostAmt());
			m_AdjustCostLL = m_AmountLL.subtract(m_last_costdetail
					.getCostAmtLL());
				
			m_CumulatedAmt = getNewCumulatedAmt(m_last_costdetail).add(
					m_AdjustCost);
			m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail)
					.subtract(m_AdjustCostLL);

			m_CurrentCostPrice = m_CumulatedAmt.divide(
					m_CumulatedQty.signum() != 0 ? m_CumulatedQty
							: BigDecimal.ONE, m_as.getCostingPrecision(),
					BigDecimal.ROUND_HALF_UP);
			m_CurrentCostPriceLL = m_CumulatedAmtLL.divide(m_CumulatedQty
					.signum() != 0 ? m_CumulatedQty : BigDecimal.ONE, m_as
					.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);

			if(m_AdjustCost.add(m_AdjustCostLL).signum() == 0)
				return;
			// validation when the cost detail is reprocess
			if (m_costdetail == null)
				return;
			
			// reset with the current values
			m_costdetail.setCostAdjustment(m_AdjustCost);
			m_costdetail.setAmt(m_costdetail.getCostAmt().add(
					m_costdetail.getCostAdjustment()));
			m_costdetail.setCostAdjustmentLL(m_AdjustCostLL);
			m_costdetail.setAmtLL(m_costdetail.getCostAmtLL().add(
					m_costdetail.getCostAdjustmentLL()));

			updateAmtCost();

			return;
		}
		
		
		// calculated costing
		if (m_trx.getMovementType().endsWith("+"))
		{
			m_Amount = m_trx.getMovementQty().multiply(m_costThisLevel);
			m_AmountLL = m_trx.getMovementQty().multiply(m_costLowLevel);
			m_CumulatedAmt = getNewCumulatedAmt(m_last_costdetail).add(m_Amount);
			m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail).add(
					m_AmountLL);

			m_CumulatedQty = getNewCumulatedQty(m_last_costdetail).add(
					m_trx.getMovementQty());


			m_CurrentCostPrice = m_costThisLevel;
			m_CurrentCostPriceLL = m_costLowLevel;
			
		}
		else if (m_trx.getMovementType().endsWith("-")) {

			// Use the last current cost price for out transaction
			//Calculate based on last average cost.
			m_CurrentCostPrice = getNewCurrentCostPrice(m_last_costdetail,
					m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			m_CurrentCostPriceLL = getNewCurrentCostPriceLL(m_last_costdetail,
					m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);

			m_Amount = m_trx.getMovementQty().multiply(m_CurrentCostPrice);
			m_AmountLL = m_trx.getMovementQty().multiply(m_CurrentCostPriceLL);

			m_CumulatedAmt = getNewCumulatedAmt(m_last_costdetail)
					.add(m_Amount);
			m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail).add(
					m_AmountLL);
			
			m_CumulatedQty = getNewCumulatedQty(m_last_costdetail).add(
					m_trx.getMovementQty());
		
			if(m_costdetail != null)
			{	
					m_costdetail.setAmt(m_CurrentCostPrice.multiply(m_model
							.getMovementQty()));
					m_costdetail.setAmtLL(m_CurrentCostPriceLL.multiply(m_model
							.getMovementQty()));
			}
		}
		
		//create new cost
		if (m_costdetail == null)
			return;
		
		updateAmtCost();
	}

	private void createCostDetail() {

		// Ignore reversal transaction because is created based on the original
		// transaction
		if (m_model.getReversalLine_ID() > 0 && m_costdetail == null) {
			createReversalCostDetail();
			return;
		}

		int seqNo = m_last_costdetail.getSeqNo() + 10;
		// create a new cost detail or is necessary create a new cost detail for
		// adjustment
		if (m_trx.getM_Transaction_ID() != m_last_costdetail
				.getM_Transaction_ID()
				&& m_costdetail == null
				|| m_AdjustCost.add(m_AdjustCostLL).signum() != 0
				&& m_costdetail == null) {
			// set Movement Qty in Zero because is a adjustment
			BigDecimal m_movementQty = m_trx.getMovementQty();
			// if exist adjustment cost then set the movement qty to zero
			if (m_AdjustCost.add(m_AdjustCostLL).signum() != 0)
				m_movementQty = Env.ZERO;

			// create new cost detail
			m_costdetail = new MCostDetail(m_trx, m_as.getC_AcctSchema_ID(),
					m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(), m_CurrentCostPrice
							.multiply(m_movementQty).abs(),
					m_CurrentCostPriceLL.multiply(m_movementQty).abs(),
					m_movementQty, m_trx.get_TrxName());
			// set account date for this cost detail
			m_costdetail.setDateAcct(m_model.getDateAcct());
			m_costdetail.setSeqNo(seqNo);

			// set transaction id
			if (m_trx != null)
				m_costdetail.setM_Transaction_ID(m_trx.getM_Transaction_ID());
			// set if transaction is sales order type or not
			if (m_isSOTrx != null)
				m_costdetail.setIsSOTrx(m_isSOTrx);
			else
				m_costdetail.setIsSOTrx(m_model.isSOTrx());

			if (m_AdjustCost.signum() != 0 || m_AdjustCostLL.signum() != 0) {
				String description = m_costdetail.getDescription() != null ? m_costdetail
						.getDescription() : "";
				// update adjustment cost this level
				if (m_AdjustCost.signum() != 0) {
					m_costdetail.setCostAdjustmentDate(m_model.getDateAcct());
					m_costdetail.setCostAdjustment(m_AdjustCost);
					m_costdetail.setCostAmt(BigDecimal.ZERO);
					m_costdetail.setAmt(m_costdetail.getCostAmt().add(
							m_costdetail.getCostAdjustment()));
					m_costdetail.setDescription(description + " Adjust Cost:"
							+ m_AdjustCost);
				}
				// update adjustment cost lower level
				if (m_AdjustCostLL.signum() != 0) {
					description = m_costdetail.getDescription() != null ? m_costdetail
							.getDescription() : "";
					m_costdetail.setCostAdjustmentDateLL(m_model.getDateAcct());
					m_costdetail.setCostAdjustmentLL(m_AdjustCostLL);
					m_costdetail.setCostAmtLL(BigDecimal.ZERO);
					m_costdetail.setAmt(m_costdetail.getCostAmtLL().add(
							m_costdetail.getCostAdjustmentLL()));
					m_costdetail.setDescription(description
							+ " Adjust Cost LL:" + m_AdjustCost);
				}
			}

			updateAmtCost();
			return;
		}
	}

	public MCostDetail process() {

		calculate();
		createCostDetail();
		updateInventoryValue();
		createCostAdjustment();
		return m_costdetail;
	}

	public void createCostAdjustment() {
		// only re process cost detail if account schema need adjust cost
		if (!m_as.isAdjustCOGS())
			return;
		// void the cycle process, only process the adjustment
		if (m_costdetail == null || m_costdetail.isProcessing())
			return;

		// Check if cost detail is an earlier transaction
		// get the cost details that need be re process before this cost
		// transaction
		List<MCostDetail> cds = MCostDetail.getAfterDate(m_costdetail,
				costingLevel);
		if (cds == null || cds.size() == 0)
			return;
		
		MCostDetail last_cd = m_costdetail;
		m_costdetail = null;
		
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
 			clearAccounting(cd);
			// Only uncomment to debug
			// Trx.get(cd.get_TrxName(), false).commit();
		}
	}

	@Override
	public void processCostDetail(MCostDetail mCostdetail) {
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
		if (getNewCumulatedQty(cd).signum() != 0
				&& getNewCumulatedAmt(cd).signum() != 0)
			return getNewCumulatedAmt(cd).divide(getNewCumulatedQty(cd), scale,
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
	public BigDecimal getNewCumulatedAmt(MCostDetail cd) {

		BigDecimal cumulatedAmt = Env.ZERO;
		if (cd.getQty().signum() >= 0)
			cumulatedAmt = cd.getCumulatedAmt().add(cd.getCostAmt())
					.add(cd.getCostAdjustment());
		else
			cumulatedAmt = cd.getCumulatedAmt().add(cd.getCostAmt().negate())
					.add(cd.getCostAdjustment().negate());

		return cumulatedAmt;
	}

	/**
	 * Average Invoice Get the New Current Cost Price low level
	 * 
	 * @param cd
	 *            Cost Detail
	 * @param scale
	 *            Scale
	 * @param roundingMode
	 *            Rounding Mode
	 * @return New Current Cost Price low level
	 */
	public BigDecimal getNewCurrentCostPriceLL(MCostDetail cd, int scale,
			int roundingMode) {
		if (getNewCumulatedQty(cd).signum() != 0
				&& getNewCumulatedAmtLL(cd).signum() != 0)
			return getNewCumulatedAmtLL(cd).divide(getNewCumulatedQty(cd),
					scale, roundingMode);
		else
			return BigDecimal.ZERO;
	}

	/**
	 * Average Invoice Get the new Cumulated Amt Low Level
	 * 
	 * @param cd
	 *            MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	public BigDecimal getNewCumulatedAmtLL(MCostDetail cd) {
		BigDecimal cumulatedAmtLL = Env.ZERO;
		if (cd.getQty().signum() > 0)
			cumulatedAmtLL = cd.getCumulatedAmtLL().add(cd.getCostAmtLL())
					.add(cd.getCostAdjustmentLL());
		else
			cumulatedAmtLL = cd.getCumulatedAmtLL()
					.add(cd.getCostAmtLL().negate())
					.add(cd.getCostAdjustmentLL().negate());
		return cumulatedAmtLL;
	}

	/**
	 * Average Invoice Get the new Cumulated Qty
	 * 
	 * @param cd
	 *            Cost Detail
	 * @return New Cumulated Qty
	 */
	public BigDecimal getNewCumulatedQty(MCostDetail cd) {
		return cd.getCumulatedQty().add(cd.getQty());
	}

	/**
	 * Update Cost Amt
	 */
	private void updateAmtCost() {
		if (m_trx.getMovementType().contains("+")) {
			m_costdetail.setCostAmt(m_costdetail.getAmt().subtract(
					m_costdetail.getCostAdjustment()));
			m_costdetail.setCostAmtLL(m_costdetail.getAmtLL().subtract(
					m_costdetail.getCostAdjustmentLL()));
		}
		if (m_trx.getMovementType().contains("-")) {
			m_costdetail.setCostAmt(m_costdetail.getAmt().add(m_AdjustCost));
			m_costdetail.setCostAmtLL(m_costdetail.getAmtLL().add(
					m_AdjustCostLL));
		}

		// set the id for model
		final String idColumnName = CostEngine.getIDColumnName(m_model);
		m_costdetail.set_ValueOfColumn(idColumnName,
				CostEngine.getIDColumn(m_model));
		
		if (m_model instanceof MInOutLine)
		{	
			MInOutLine ioLine =  (MInOutLine) m_model;
			m_costdetail.setC_OrderLine_ID(ioLine.getC_OrderLine_ID());
		}
		if (m_model instanceof MMatchInv && m_costdetail.getM_InOutLine_ID() == 0)
		{	
			MMatchInv iMatch =  (MMatchInv) m_model;
			m_costdetail.setM_InOutLine_ID(iMatch.getM_InOutLine_ID());
		}
		if(m_model instanceof MMatchPO && m_costdetail.getM_InOutLine_ID() == 0)
		{
			MMatchPO poMatch =  (MMatchPO) m_model;
			m_costdetail.setM_InOutLine_ID(poMatch.getM_InOutLine_ID());
		}
		m_costdetail.setCumulatedAmt(getNewCumulatedAmt(m_last_costdetail));
		m_costdetail.setCumulatedQty(getNewCumulatedQty(m_last_costdetail));
		m_costdetail.setCurrentCostPrice(m_CurrentCostPrice);
		m_costdetail.setCurrentCostPriceLL(m_CurrentCostPriceLL);
		m_costdetail.saveEx();
		// Trx.get(m_costdetail.get_TrxName(), false).commit();
	}

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
		MTransaction trx = new MTransaction(m_model.getCtx(),
				cd.getM_Transaction_ID(), m_model.get_TrxName());
		IDocumentLine docLine = null;
		MCostType ct = (MCostType) cd.getM_CostType();
		MCostElement ce = (MCostElement) cd.getM_CostElement();

		CostEngineFactory.getCostEngine(cd.getAD_Client_ID()).createCostDetail(
				m_as, trx, trx.getDocumentLine(), ct, ce);

		if (MCostElement.COSTELEMENTTYPE_Material.equals(ce
				.getCostElementType())) {
			// Calculate adjustment cost by variances in invoices
			if (MTransaction.MOVEMENTTYPE_VendorReceipts.equals(trx
					.getMovementType())
					&& MCostElement.COSTELEMENTTYPE_Material.equals(ce
							.getCostElementType())) {
				MInOutLine line = (MInOutLine) trx.getDocumentLine();
				MMatchPO[] orderMatches = MMatchPO.getOrderLine(cd.getCtx(),
						line.getC_OrderLine_ID(), cd.get_TrxName());
				for (MMatchPO match : orderMatches) {
					if (match.getM_InOutLine_ID() == line.getM_InOutLine_ID()
							&& match.getM_Product_ID() == trx.getM_Product_ID()) {
						CostEngineFactory.getCostEngine(cd.getAD_Client_ID())
								.createCostDetail(m_as, trx, match, ct, ce);
					}
				}

				MMatchInv[] invoiceMatches = MMatchInv
						.getInOutLine(cd.getCtx(), line.getM_InOutLine_ID(),
								cd.get_TrxName());
				for (MMatchInv match : invoiceMatches) {
					if (match.getM_Product_ID() == trx.getM_Product_ID()) {
						CostEngineFactory.getCostEngine(cd.getAD_Client_ID())
								.createCostDetail(m_as, trx, match, ct, ce);
					}
				}
			}
			return;
		}
		// Process Landed Cost Element
		if (MCostElement.COSTELEMENTTYPE_LandedCost.equals(ce
				.getCostElementType())) {
			if (MTransaction.MOVEMENTTYPE_VendorReceipts.equals(trx
					.getMovementType())
					&& cd.getC_LandedCostAllocation_ID() > 0) {
					CostEngineFactory
					.getCostEngine(cd.getAD_Client_ID())
					.createCostDetailForLandedCostAllocation((MLandedCostAllocation) cd.getC_LandedCostAllocation());
			}
			return;
		}
	}
}
