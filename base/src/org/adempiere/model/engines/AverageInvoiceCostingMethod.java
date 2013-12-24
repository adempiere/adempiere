/**
 * 
 */
package org.adempiere.model.engines;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MLandedCostAllocation;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovement;
import org.compiere.model.MPeriod;
import org.compiere.model.MPeriodControl;
import org.compiere.model.MProduct;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProjectIssue;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.model.X_M_Production;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.model.MPPCostCollector;
import org.eevolution.model.RoutingService;
import org.eevolution.model.RoutingServiceFactory;

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
	 * org.adempiere.model.engines.ICostingMethod#setCostingMethod(org.compiere.model
	 * .MAcctSchema, org.compiere.model.MTransaction,
	 * org.adempiere.model.engines.IDocumentLine, org.compiere.model.MCost,
	 * java.math.BigDecimal, java.math.BigDecimal, java.lang.Boolean)
	 */
	private Boolean newCostdetail = true;
	private BigDecimal movementqty = Env.ONE;
	private Boolean isReversal = false;

		
	public void setCostingMethod(MAcctSchema as, IDocumentLine model,
			MTransaction mtrx, MCost dimension, BigDecimal costThisLevel,
			BigDecimal costLowLevel, Boolean isSOTrx) {
		if (model instanceof MPPCostCollector)
		{
			MPPCostCollector cc = (MPPCostCollector)model;
			if (cc.isActivityControl())
			{	
				
				MProduct prod_product = cc.getPP_Order().getM_Product();
				MProduct product = MProduct.forS_Resource_ID(cc.getCtx(),
						cc.getS_Resource_ID(), null);
				final RoutingService routingService = RoutingServiceFactory.get()
						.getRoutingService(cc.getAD_Client_ID());
				final BigDecimal qty = routingService.getResourceBaseValue(
						cc.getS_Resource_ID(), cc);
				final CostDimension d = new CostDimension(product, as,
						as.getM_CostType_ID(), 0, // AD_Org_ID,
						0, // Warehouse ID
						0, // M_ASI_ID
						dimension.getM_CostElement_ID());		
				final BigDecimal price = getResourceActualCostRate(cc,
						cc.getS_Resource_ID(), d, cc.get_TrxName());
				BigDecimal costs = price.multiply(qty);
				//if (costLowLevel.compareTo(Env.ZERO) != 0)
				//	costs = costs.divide(costLowLevel, 5, BigDecimal.ROUND_HALF_UP);
				if (costs.scale() > as.getCostingPrecision()) 
					costs = costs.setScale(as.getCostingPrecision(),
							RoundingMode.HALF_UP);
					

				
				
				
				m_as = as;
				m_trx = mtrx;
				m_dimension = dimension;
				m_costThisLevel = (costs == null ? Env.ZERO : costs.divide(m_trx.getMovementQty(), 5, BigDecimal.ROUND_HALF_UP));
				m_costLowLevel = Env.ZERO;
				m_isSOTrx = isSOTrx;
				m_model = model;
				costingLevel = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID())
						.getCostingLevel(as, mtrx.getAD_Org_ID());
				// find if this transaction exist into cost detail
				m_costdetail = MCostDetail.getByTransaction(model, m_trx,
						m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
						m_dimension.getM_CostElement_ID());
				isReversal(m_model);
				return;
			}
		}
		resetFactAcct(model);
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
		isReversal(m_model);
	}

	public void calculate() {
		// try find the last cost detail transaction
				m_last_costdetail = MCostDetail.getLastTransaction(m_model, m_trx,
					m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(), m_model.getDateAcct(),
					costingLevel);
		// If model is reversal then no calculate cost
		if (m_model.getReversalLine_ID() > 0 && isReversal)
			return;
		
		// created a new instance cost detail to process calculated cost
		if (m_last_costdetail == null) { 
			m_last_costdetail = new MCostDetail(m_trx,
					m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(), Env.ZERO, Env.ZERO,
					Env.ZERO, m_trx.get_TrxName());
			m_last_costdetail.setDateAcct(m_model.getDateAcct());
		}
			
		
		
		// calculated costing
		if (m_trx.getMovementType().endsWith("+"))
		{
			if (m_model instanceof MLandedCostAllocation && m_dimension.getM_CostElement().getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Material))
			{
				MLandedCostAllocation alo = (MLandedCostAllocation)m_model;
				int asi = alo.getM_InOutLine().getM_AttributeSetInstance_ID();
				String whereClause = "M_Attributesetinstance_ID=? and m_Product_ID=? and seqno <=? and m_costtype_ID=? and m_costelement_ID=?";
				BigDecimal qty = new Query(alo.getCtx(), MCostDetail.Table_Name, whereClause, alo.get_TrxName())
				.setParameters(asi, alo.getM_Product_ID(), m_last_costdetail.getSeqNo(), m_last_costdetail.getM_CostType_ID(), m_last_costdetail.getM_CostElement_ID())
				.sum(MCostDetail.COLUMNNAME_Qty);

				m_AdjustCost = alo.getAmt().divide(alo.getQty(),m_as.getCostingPrecision(),BigDecimal.ROUND_HALF_UP);
				m_AdjustCost = m_AdjustCost.multiply(qty);
				//m_AdjustCost = m_Amount;

				m_AdjustCostLL = m_AmountLL;
				if (m_AdjustCostLL == null) m_AdjustCostLL = Env.ZERO;
				
				m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail).add(
						m_AmountLL);

				m_CumulatedQty = m_last_costdetail.getCumulatedQty();


				m_CurrentCostPrice = m_costThisLevel;
				m_CurrentCostPriceLL = m_costLowLevel;
				
			}
			/*if (m_model instanceof MPPCostCollector 
					&& !m_dimension.getM_CostElement().getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Material))
			{
				MPPCostCollector alo = (MPPCostCollector)m_model;
				BigDecimal movementqty = m_trx.getMovementQty();
				int asi = m_trx.getM_AttributeSetInstance_ID();
				m_CumulatedQty = m_last_costdetail.getCumulatedQty();
				m_CurrentCostPrice = m_costThisLevel;
				m_CurrentCostPriceLL = m_costLowLevel;
				m_AdjustCost = m_CurrentCostPrice.multiply(m_trx.getMovementQty());
				//m_Amount = m_CurrentCostPrice.multiply(m_trx.getMovementQty());
				//m_AdjustCost = m_Amount;

				m_AdjustCostLL = m_CurrentCostPriceLL;

				m_CumulatedAmt = getNewCumulatedAmt(m_last_costdetail).add(m_Amount).add(m_AdjustCost);
				m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail).add(
						m_AmountLL);

				m_CumulatedQty = getNewCumulatedQty(m_last_costdetail).add(
						m_trx.getMovementQty());
				
			}*/


			else if (m_model instanceof MMatchInv && m_dimension.getM_CostElement().getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Material))
			{
				MMatchInv minv = (MMatchInv)m_model;
				if (m_costdetail == null)
					return;
				int asi = minv.getM_InOutLine().getM_AttributeSetInstance_ID();
				BigDecimal diff = minv.getC_InvoiceLine().getLineNetAmt();
				if (m_costdetail != null)
					diff = minv.getC_InvoiceLine().getLineNetAmt().subtract(m_costdetail.getCostAmt());
				String whereClause = "M_Attributesetinstance_ID=? and m_Product_ID=? and seqno <=? and m_costtype_ID=? and m_costelement_ID=?";
				BigDecimal qty = new Query(minv.getCtx(), MCostDetail.Table_Name, whereClause, minv.get_TrxName())
				.setParameters(asi, minv.getM_Product_ID(), m_last_costdetail.getSeqNo(), m_last_costdetail.getM_CostType_ID(), m_last_costdetail.getM_CostElement_ID())
				.sum(MCostDetail.COLUMNNAME_Qty);
				m_AdjustCost = diff.divide(minv.getQty(),m_as.getCostingPrecision(),BigDecimal.ROUND_HALF_UP);
				if (m_AdjustCost.compareTo(Env.ZERO)==0)
					newCostdetail = false;
				m_CurrentCostPrice = m_AdjustCost;

				m_AdjustCost = m_AdjustCost.multiply(qty);
				//m_AdjustCost = m_Amount;

				m_AdjustCostLL = m_AmountLL;
				}
			else
			{				
				m_Amount = m_trx.getMovementQty().multiply(m_costThisLevel);
				m_AmountLL = m_trx.getMovementQty().multiply(m_costLowLevel);
				m_CurrentCostPrice =m_costThisLevel;
				m_CurrentCostPriceLL = m_costLowLevel;
			}
			m_CumulatedAmt = getNewCumulatedAmt(m_last_costdetail).add(m_Amount);
			m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail).add(
					m_AmountLL);

			m_CumulatedQty = getNewCumulatedQty(m_last_costdetail).add(
					m_trx.getMovementQty());


		}
		else if (m_trx.getMovementType().endsWith("-")) {

			// Use the last current cost price for out transaction
			//Calculate based on last average cost.
			m_CurrentCostPrice = getNewCurrentCostPrice(m_last_costdetail,
					m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			m_CurrentCostPriceLL = getNewCurrentCostPriceLL(m_last_costdetail,
					m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			if (m_CurrentCostPrice.compareTo(Env.ZERO) == 0)
				m_CurrentCostPrice = m_costThisLevel;
			if (m_CurrentCostPriceLL.compareTo(Env.ZERO)==0)
				m_CurrentCostPriceLL = m_costLowLevel;

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
					m_costdetail.setAmt(m_CurrentCostPrice.multiply(m_trx.getMovementQty().negate()));
					m_costdetail.setAmtLL(m_CurrentCostPriceLL.multiply(m_trx.getMovementQty().negate()));
			}
		}
		
		//create new cost
		if (m_costdetail == null)
			return;
		
		//updateAmtCost();
	}

	private void createCostDetail() {

		// Ignore reversal transaction because is created based on the original
		// transaction
		if (m_model.getReversalLine_ID() > 0 ) {
			if (isReversal) 
			createReversalCostDetail();			
			return;
		}

		BigDecimal m_movementQty = m_trx.getMovementQty();	
		if (m_model instanceof MMatchInv)
		{
			m_movementQty = Env.ZERO;
		}		

		if (m_model instanceof MLandedCostAllocation)
		{
			m_movementQty = Env.ZERO;
		}
		if (m_model instanceof MPPCostCollector)
		{
			MPPCostCollector cc = (MPPCostCollector)m_model;
			if (cc.isActivityControl())
				m_movementQty = m_trx.getMovementQty();
		}
		
			

		int seqNo = m_last_costdetail.getSeqNo() + 10; 
		if (newCostdetail)
		{
			// if exist adjustment cost then set the movement qty to zero
			if (m_AdjustCost.add(m_AdjustCostLL).signum() != 0)
				m_movementQty = Env.ZERO;
			// create new cost detail
			m_costdetail = new MCostDetail(m_trx, m_as.getC_AcctSchema_ID(),
					m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(), 
					m_CurrentCostPrice.multiply(m_movementQty).abs(),
					m_CurrentCostPriceLL.multiply(m_movementQty).abs(),
					m_movementQty, m_trx.get_TrxName());
			// set account date for this cost detail
			if  (m_model instanceof MLandedCostAllocation)
				m_costdetail.setDateAcct(((MLandedCostAllocation) m_model).getC_InvoiceLine().getC_Invoice().getDateAcct());
			
			else
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
					m_costdetail.setCostAdjustmentLL(m_AdjustCostLL);
					m_costdetail.setCostAmt(BigDecimal.ZERO);
					m_costdetail.setCostAmtLL(BigDecimal.ZERO);
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
					m_costdetail.setCostAdjustment(m_AdjustCost);
					m_costdetail.setCostAmtLL(BigDecimal.ZERO);
					m_costdetail.setCostAmt(BigDecimal.ZERO);
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
		if (m_model instanceof MProductionLine)
		{
			MProductionLine productionLine = (MProductionLine) m_model;
			if(!productionLine.getM_Product().getProductType().equals(MProduct.PRODUCTTYPE_Item))
			{
				createCostDetail_RessourceProduct();
				return m_costdetail;				
			}
		}
		calculate();
		createCostDetail();
		updateInventoryValue();
		//createCostAdjustment();
		return m_costdetail;
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

		if (m_model instanceof MLandedCostAllocation) {

			m_costdetail.setC_InvoiceLine_ID(((MLandedCostAllocation) m_model).getC_InvoiceLine_ID());
			m_costdetail.setM_InOutLine_ID(((MLandedCostAllocation) m_model).getM_InOutLine_ID());
		}
		else
		{

			if (m_trx.getMovementType().contains("+")) {
				if (m_AdjustCost.compareTo(Env.ZERO)!=0 || m_AdjustCostLL.compareTo(Env.ZERO)!=0)
				{
					m_costdetail.setCostAmt(Env.ZERO);
					m_costdetail.setCostAmtLL(Env.ZERO);
					m_costdetail.setCostAdjustment(m_AdjustCost);
					m_costdetail.setCostAdjustmentLL(m_AdjustCostLL);
				}else
				{
					m_costdetail.setCostAmt(m_costdetail.getAmt());
					m_costdetail.setCostAmtLL(m_costdetail.getAmtLL());					
				}
					
			}
			if (m_trx.getMovementType().contains("-")) {
				m_costdetail.setCostAmt(m_costdetail.getAmt().add(m_AdjustCost));
				m_costdetail.setCostAmtLL(m_costdetail.getAmtLL().add(m_AdjustCostLL));
			}

		}
		if (m_trx.getDocumentLine().getReversalLine_ID() != 0 && m_costdetail.getCostAmt().compareTo(Env.ZERO) == -1 
				&& m_trx.getMovementType().contains("-") && m_trx.getMovementQty().compareTo(Env.ZERO) == 1)
				{
					m_costdetail.setCostAmt(m_costdetail.getCostAmt().abs());
					m_costdetail.setCostAmtLL(m_costdetail.getCostAmtLL().abs());
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
		if (m_model instanceof MMatchInv)
		{	
			MMatchInv iMatch =  (MMatchInv) m_model;
			if (m_costdetail.getM_InOutLine_ID()==0)
				m_costdetail.setM_InOutLine_ID(iMatch.getM_InOutLine_ID());
			if (m_costdetail.getC_InvoiceLine_ID() == 0)
			m_costdetail.setC_InvoiceLine_ID(iMatch.getC_InvoiceLine_ID());
			//m_costdetail.saveEx();
			//return;
		}
		if(m_model instanceof MMatchPO && m_costdetail.getM_InOutLine_ID() == 0)
		{
			MMatchPO poMatch =  (MMatchPO) m_model;
			m_costdetail.setM_InOutLine_ID(poMatch.getM_InOutLine_ID());
		}

		
		m_costdetail.setCumulatedAmt(getNewCumulatedAmt(m_last_costdetail));
		m_costdetail.setCumulatedAmtLL(getNewCumulatedAmtLL(m_last_costdetail));
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
	

	public void updateInventoryValue() {
		
		
		if(!newCostdetail)
			return;
		m_dimension.setCumulatedAmt(getNewCumulatedAmt(m_costdetail));
		m_dimension.setCumulatedAmtLL(getNewCumulatedAmtLL(m_costdetail));
		m_dimension.setCumulatedQty(getNewCumulatedQty(m_costdetail));
		m_dimension.setCurrentQty(getNewCumulatedQty(m_costdetail));
		BigDecimal costprice = getNewCurrentCostPrice(m_costdetail, 5, BigDecimal.ROUND_HALF_UP);
		m_dimension.setCurrentCostPrice(getNewCurrentCostPrice(m_costdetail, 5, BigDecimal.ROUND_HALF_UP));
		m_dimension.setCurrentCostPriceLL(getNewCurrentCostPriceLL(m_costdetail, 5, BigDecimal.ROUND_HALF_UP));
		m_dimension.saveEx();
		/*if (!m_dimension.getM_CostElement().getCostElementType().equals(MCostElement.COSTELEMENTTYPE_Material))
			return;
		if (m_CumulatedQty.signum() != 0 )
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
		if (costingLevel.equals("O"))
		{
			if (!m_trx.getMovementType().equals(MTransaction.MOVEMENTTYPE_VendorReceipts))
				return;
			final String whereClause = "AD_Client_ID=? AND AD_Org_ID<>?"
					+" AND "+MCost.COLUMNNAME_M_CostType_ID+"=?"
					+" AND "+MCost.COLUMNNAME_C_AcctSchema_ID+"=?"
					+" AND "+MCost.COLUMNNAME_M_CostElement_ID+"=?"
					+" AND "+MCost.COLUMNNAME_M_Product_ID+ "=?"
					+" AND currentcostprice = 0";
			final Object[] params = new Object[]{m_dimension.getAD_Client_ID(), m_dimension.getAD_Org_ID(),
					m_dimension.getM_CostType_ID(), m_dimension.getC_AcctSchema_ID(),
					m_dimension.getM_CostElement_ID(),
					m_dimension.getM_Product_ID()};
			List<MCost> costs =  new Query(m_dimension.getCtx(), MCost.Table_Name, whereClause, m_dimension.get_TrxName())
			.setOnlyActiveRecords(true)
			.setParameters(params)
			.list();
			for (MCost cost:costs)
			{
				cost.setCurrentCostPrice(m_dimension.getCurrentCostPrice());
				cost.setCurrentCostPriceLL(m_dimension.getCurrentCostPriceLL());
				cost.saveEx();
			}				
		}
	*/}
	
	private void createCostDetail_RessourceProduct() {

		// Ignore reversal transaction because is created based on the original
		// transaction
		if (m_model.getReversalLine_ID() > 0 && m_costdetail == null) {
			createReversalCostDetail();

			return;
		}

		Timestamp dateacct = null;
		dateacct = m_model.getDateAcct();
		MDocType dt = new MDocType(m_trx.getCtx(), m_model.getC_DocType_ID(), m_trx.get_TrxName());
		Boolean periodopen = MPeriod.isOpen(m_trx.getCtx(), dateacct, dt.getDocBaseType(), m_trx.getAD_Org_ID());
		m_last_costdetail = MCostDetail.getLastTransaction(m_model, m_trx,
				m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
				m_dimension.getM_CostElement_ID(), m_model.getDateAcct(),
				costingLevel, periodopen);

		if (m_last_costdetail == null) { 
			m_last_costdetail = new MCostDetail(m_trx,
					m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(),
					m_dimension.getM_CostElement_ID(), Env.ZERO, Env.ZERO,
					Env.ZERO, m_trx.get_TrxName());
			m_last_costdetail.setDateAcct(m_model.getDateAcct());
		}
		int seqNo = m_last_costdetail.getSeqNo() + 10;
		// create a new cost detail or is necessary create a new cost detail for
		// adjustment {
		// set Movement Qty in Zero because is a adjustment
		BigDecimal m_movementQty = m_trx.getMovementQty();
		if (m_AdjustCost.add(m_AdjustCostLL).signum() != 0)
			m_movementQty = Env.ZERO;

		// create new cost detail
		m_costdetail = new MCostDetail(m_trx, m_as.getC_AcctSchema_ID(),
				m_dimension.getM_CostType_ID(),
				m_dimension.getM_CostElement_ID(), m_costThisLevel
				.multiply(m_movementQty).abs(),
				m_costLowLevel.multiply(m_movementQty).abs(),
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
		m_costdetail.setM_ProductionLine_ID(m_trx.getM_ProductionLine_ID());
		m_costdetail.setCumulatedAmt(getNewCumulatedAmt(m_last_costdetail));
		m_costdetail.setCumulatedQty(getNewCumulatedQty(m_last_costdetail));
		m_costdetail.saveEx();
		return;
	}
	
	private void isReversal(IDocumentLine model)
	{
		String whereClause = model.get_TableName() + "_ID =?";
		int id = new Query(model.getCtx(), model.get_TableName(), whereClause, model.get_TrxName())
			.setParameters(model.getReversalLine_ID())
			.firstId();
		isReversal =  id > 0? true:false;
		if (model instanceof MLandedCostAllocation)
		{
			MLandedCostAllocation lca = (MLandedCostAllocation)model;
			if (lca.getC_InvoiceLine().getC_Invoice().getReversal_ID()!= 0)
				isReversal = true;
		}
	}
	
	
	private Boolean resetFactAcct(IDocumentLine model)
	{
		String sqldelete = "delete m_costdetail where m_product_id=? and dateacct>?";
		int delete = DB.executeUpdateEx(sqldelete, new Object[]{model.getM_Product_ID(),model.getDateAcct()}, model.get_TrxName());
		String sqlfactacct = 
				"select distinct tablename, f.ad_table_id, record_id " + 
				"from fact_acct f " +
				"inner join ad_table t on f.ad_table_id=t.ad_table_id " +
				"where f.m_product_id =? " +
				" and f.ad_table_id in (319,321,323,325,472,473,53035, 623) and trunc(dateacct)>" + DB.TO_DATE(model.getDateAcct());
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sqlfactacct, null);
			pstmt.setInt (1, model.getM_Product_ID());
			//pstmt.setTimestamp(2,model.getDateAcct());
			rs = pstmt.executeQuery ();
			while (rs.next ())
				delete(rs.getString(1), rs.getInt(2), model, rs.getInt(3));				
		}
		catch (Exception e)
		{
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		sqlfactacct = 
				"select distinct tablename, f.ad_table_id, record_id " + 
				"from fact_acct f " +
				"inner join ad_table t on f.ad_table_id=t.ad_table_id " +
				" inner join c_landedcostallocation lca on f.line_ID = lca.c_invoiceline_id and f.ad_table_id=318 " +
				" where f.m_product_id =? and dateacct > ?";
		pstmt = null;
		rs = null;
		try
		{
			pstmt = DB.prepareStatement (sqlfactacct, null);
			pstmt.setInt (1, model.getM_Product_ID());
			pstmt.setTimestamp(2,model.getDateAcct());
			rs = pstmt.executeQuery ();
			while (rs.next ())
				delete(rs.getString(1), rs.getInt(2), model, rs.getInt(3));				
		}
		catch (Exception e)
		{
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return true;
	}
	
	private void delete (String TableName, int AD_Table_ID, IDocumentLine model, int record_ID)
	{
		Timestamp today = TimeUtil.trunc(new Timestamp (System.currentTimeMillis()), TimeUtil.TRUNC_DAY);


		String docBaseType = null;
		if (AD_Table_ID == MInOut.Table_ID)
			docBaseType = "IN ('" + MPeriodControl.DOCBASETYPE_MaterialDelivery
				+ "','" + MPeriodControl.DOCBASETYPE_MaterialReceipt + "')";
		else if (AD_Table_ID == MInvoice.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_APInvoice + "'";
		else if (AD_Table_ID == MProjectIssue.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ProjectIssue + "'";
		else if (AD_Table_ID == MMovement.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialMovement + "'";
		else if (AD_Table_ID == MInventory.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialPhysicalInventory + "'";
		else if (AD_Table_ID == X_M_Production.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MaterialProduction + "'";
		else if (AD_Table_ID == MMatchInv.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchInvoice + "'";
		else if (AD_Table_ID == MMatchPO.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_MatchPO + "'";
		else if (AD_Table_ID == MPPCostCollector.Table_ID)
			docBaseType = "= '" + MPeriodControl.DOCBASETYPE_ManufacturingCostCollector+ "'";
		//
		else
			docBaseType = " AND pc.DocBaseType " + docBaseType;

		if (!MPeriod.isOpen (model.getCtx(), model.getDateAcct(), docBaseType, model.getAD_Org_ID()))
			return;
		
		//	Doc
		String sql1 = "UPDATE " + TableName	+ " SET Posted='N', Processing='N' where " + TableName + "_ID =?";
		String sql2 = "delete from fact_acct where ad_table_id=? and record_id=?";
		//	Fact
		//
		
		int updated = DB.executeUpdateEx(sql1, new Object[] {record_ID},model.get_TrxName());
		int deleted = DB.executeUpdateEx(sql2, new Object[] {AD_Table_ID, record_ID},model.get_TrxName());
	}	//	delete
	public BigDecimal getResourceActualCostRate(MPPCostCollector cc,
			int S_Resource_ID, CostDimension d, String trxName) {
		if (S_Resource_ID <= 0)
			return Env.ZERO;
		final MProduct resourceProduct = MProduct.forS_Resource_ID(
				Env.getCtx(), S_Resource_ID, null);
		return getProductActualCostPrice(cc, resourceProduct,
				MAcctSchema.get(Env.getCtx(), d.getC_AcctSchema_ID()),
				MCostElement.get(Env.getCtx(), d.getM_CostElement_ID()),
				trxName);
	}public BigDecimal getProductActualCostPrice(MPPCostCollector cc, MProduct product, MAcctSchema as, MCostElement element, String trxName) 
	{
		CostDimension d = new CostDimension(product,
					as, as.getM_CostType_ID(),
					0, //AD_Org_ID,
					0, //Warehouse ID
					0, //M_ASI_ID,
					element.getM_CostElement_ID());
			MCost cost = d.toQuery(MCost.class, trxName).firstOnly();
			
		if (cost == null)
			return Env.ZERO;
		BigDecimal price = cost.getCurrentCostPrice().add(
				cost.getCurrentCostPriceLL());
		return roundCost(price, as.getC_AcctSchema_ID());
	}

	protected BigDecimal roundCost(BigDecimal price, int C_AcctSchema_ID) {
		// Fix Cost Precision
		int precision = MAcctSchema.get(Env.getCtx(), C_AcctSchema_ID)
				.getCostingPrecision();
		BigDecimal priceRounded = price;
		if (priceRounded.scale() > precision) {
			priceRounded = priceRounded.setScale(precision,
					RoundingMode.HALF_UP);
		}
		return priceRounded;
	}
	

}
