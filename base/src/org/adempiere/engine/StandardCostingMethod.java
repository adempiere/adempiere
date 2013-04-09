/**
 * 
 */
package org.adempiere.engine;

import java.math.BigDecimal;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
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

	public void setCostingMethod(MAcctSchema as, IDocumentLine model,
			MTransaction mtrx, MCost dimension, BigDecimal costThisLevel,
			BigDecimal costLowLevel, Boolean isSOTrx) {
		m_as = as;
		m_trx = mtrx;
		m_dimension = dimension;
		m_costThisLevel = (costThisLevel == null ? Env.ZERO : costThisLevel);
		m_costLowLevel = (costLowLevel == null ? Env.ZERO : costLowLevel);
		m_isSOTrx = isSOTrx;
		m_model = mtrx.getDocumentLine();
		costingLevel = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID())
				.getCostingLevel(as, mtrx.getAD_Org_ID());
		m_costdetail = MCostDetail.getByTransaction(model, mtrx,
				m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
				m_dimension.getM_CostElement_ID());
	}

	private void calculate() {
		if (m_model.getReversalLine_ID() > 0)
			return;

		if (m_trx.getMovementType().endsWith("-")) {
			m_CurrentCostPrice = m_dimension.getCurrentCostPrice();
			m_CurrentCostPriceLL = m_dimension.getCurrentCostPriceLL();
			m_Amount = m_model.getMovementQty().multiply(m_CurrentCostPrice);
			m_AmountLL = m_model.getMovementQty()
					.multiply(m_CurrentCostPriceLL);
			m_CumulatedQty = m_dimension.getCumulatedQty().add(
					m_trx.getMovementQty());
			m_CumulatedAmt = m_dimension.getCumulatedAmt().add(m_Amount);
			m_CumulatedAmtLL = m_dimension.getCumulatedAmtLL().add(m_AmountLL);
			return;
		}

		if (m_costdetail != null) {
			m_Amount = m_trx.getMovementQty().multiply(
					m_costdetail.getCurrentCostPrice());
			m_AmountLL = m_trx.getMovementQty().multiply(
					m_costdetail.getCurrentCostPriceLL());
			m_CumulatedQty = m_costdetail.getCumulatedQty().add(
					m_trx.getMovementQty());
			m_CumulatedAmt = m_costdetail.getCumulatedAmt().add(m_Amount);
			m_CumulatedAmtLL = m_costdetail.getCumulatedAmtLL().add(m_AmountLL);
			m_CurrentCostPrice = m_dimension.getCurrentCostPrice();
			m_CurrentCostPriceLL = m_dimension.getCurrentCostPriceLL();
			m_AdjustCost = m_CurrentCostPrice.multiply(
					m_dimension.getCumulatedQty()).subtract(
					m_dimension.getCumulatedAmt());
			m_AdjustCost = m_CurrentCostPriceLL.multiply(
					m_dimension.getCumulatedQty()).subtract(
					m_dimension.getCumulatedAmtLL());
			return;
		}

		m_Amount = m_trx.getMovementQty().multiply(
				m_dimension.getCurrentCostPrice());
		m_AmountLL = m_trx.getMovementQty().multiply(
				m_dimension.getCurrentCostPriceLL());
		m_CumulatedAmt = m_dimension.getCumulatedAmt().add(m_Amount)
				.add(m_AdjustCost);
		m_CumulatedAmtLL = m_dimension.getCumulatedAmtLL().add(m_AmountLL)
				.add(m_AdjustCostLL);
		m_CumulatedQty = m_dimension.getCumulatedQty().add(
				m_trx.getMovementQty());
		m_CurrentCostPrice = m_dimension.getCurrentCostPrice();
		m_CurrentCostPriceLL = m_dimension.getCurrentCostPriceLL();
	}

	private void createCostDetail() {
		final String idColumnName = CostEngine.getIDColumnName(m_model);
		if (m_model.getReversalLine_ID() > 0) {
			createReversalCostDetail();
			return;
		}

		if (m_costdetail == null) {
			m_costdetail = new MCostDetail(m_trx, m_as.getC_AcctSchema_ID(),
					m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(),
					m_CurrentCostPrice.multiply(m_trx.getMovementQty()),
					m_CurrentCostPriceLL.multiply(m_trx.getMovementQty()),
					m_trx.getMovementQty(), m_trx.get_TrxName());
			m_costdetail.setDateAcct(m_model.getDateAcct());
			m_costdetail.set_ValueOfColumn(idColumnName,
					CostEngine.getIDColumn(m_model));
		} else {
			if (!m_AdjustCost.equals(Env.ZERO)) {
				m_costdetail.setCostAdjustment(m_AdjustCost);
				m_costdetail.setProcessed(false);
				m_costdetail.setDescription("Adjust Cost");

			}
			if (!m_AdjustCostLL.equals(Env.ZERO)) {
				m_costdetail.setCostAdjustmentLL(m_AdjustCostLL);
				m_costdetail.setProcessed(false);
				m_costdetail.setDescription("Adjust Cost LL");

			}
			m_costdetail.set_ValueOfColumn(idColumnName,
					CostEngine.getIDColumn(m_model));
			m_costdetail.saveEx();
			return;
		}

		if (!m_costdetail.set_ValueOfColumnReturningBoolean(idColumnName,
				m_model.get_ID()))
			throw new AdempiereException("Cannot set " + idColumnName);

		if (m_isSOTrx != null)
			m_costdetail.setIsSOTrx(m_isSOTrx);
		else
			m_costdetail.setIsSOTrx(m_model.isSOTrx());

		m_costdetail.setCumulatedQty(m_dimension.getCumulatedQty());
		m_costdetail.setCumulatedAmt(m_dimension.getCumulatedAmt());
		m_costdetail.setCurrentCostPrice(m_dimension.getCurrentCostPrice());
		m_costdetail.setCumulatedAmtLL(m_dimension.getCumulatedAmtLL());
		m_costdetail.setCurrentCostPriceLL(m_dimension.getCurrentCostPriceLL());
		StringBuilder description = new StringBuilder();
		if (!Util.isEmpty(m_model.getDescription(), true))
			description.append(m_model.getDescription());
		if (m_isSOTrx != null) {
			description.append(m_isSOTrx ? "(|->)" : "(|<-)");
		}
		if (m_trx != null)
			m_costdetail.setM_Transaction_ID(m_trx.getM_Transaction_ID());
		m_costdetail.setDescription(description.toString());
		updateAmtCost();
		m_costdetail.saveEx();
		// CostAmt

		return;
	}

	public void createCostAdjutment() {
	}

	public MCostDetail process() {
		calculate();
		createCostDetail();
		updateInventoryValue();
		createCostAdjutment();
		return m_costdetail;
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
		if (getNewCumulatedQty(cd).signum() != 0)
			return cd.getCurrentCostPrice();
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
		return cd.getCumulatedAmt().add(cd.getCostAmt())
				.add(cd.getCostAdjustment());
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
		if (getNewCumulatedQty(cd).signum() != 0)
			return cd.getCurrentCostPriceLL();
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
		return cd.getCumulatedAmtLL().add(cd.getCostAmtLL())
				.add(cd.getCostAdjustmentLL());
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

	@Override
	public void processCostDetail(MCostDetail m_costdetail) {
		// TODO Auto-generated method stub

	}

	@Override
	protected List<CostComponent> getCalculatedCosts() {
		// TODO Auto-generated method stub
		return null;
	}

	private void updateAmtCost() {
		if (m_trx.getMovementType().contains("+")) {
			m_costdetail.setCostAmt(m_costdetail.getAmt().subtract(
					m_costdetail.getCostAdjustment()));
			m_costdetail.setCostAmtLL(m_costdetail.getAmtLL().subtract(
					m_AdjustCostLL));
		}
		if (m_trx.getMovementType().contains("-")) {
			m_costdetail.setCostAmt(m_costdetail.getAmt().add(m_AdjustCost));
			m_costdetail.setCostAmtLL(m_costdetail.getAmtLL().add(
					m_AdjustCostLL));
		}
	}

}
