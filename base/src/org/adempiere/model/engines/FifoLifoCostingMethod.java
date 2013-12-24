/**
 * 
 */
package org.adempiere.model.engines;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_Transaction;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostElement;
import org.compiere.model.MCostQueue;
import org.compiere.model.MCostType;
import org.compiere.model.MProduct;
import org.compiere.model.MTransaction;
import org.compiere.model.ProductCost;
import org.compiere.model.Query;
import org.compiere.util.Env;

/**
 * @author teo_sarca
 * @author anca_bradau
 */
public class FifoLifoCostingMethod extends AbstractCostingMethod
{

	public void setCostingMethod (MAcctSchema as,IDocumentLine model, MTransaction mtrx, MCost dimension,
			BigDecimal costThisLevel , BigDecimal costLowLevel, Boolean isSOTrx)
	{ 
		m_as = as;
		m_trx  = mtrx;
		m_dimension = dimension;
		m_costThisLevel = (costThisLevel == null ? Env.ZERO : costThisLevel);
		m_costLowLevel = (costLowLevel == null ? Env.ZERO : costLowLevel);
		m_cost = m_costThisLevel.add(m_costLowLevel);
		m_isSOTrx = isSOTrx;
		m_model = mtrx.getDocumentLine();
		costingLevel = MProduct.get(mtrx.getCtx(), mtrx.getM_Product_ID()).getCostingLevel(as, mtrx.getAD_Org_ID());
		m_costdetail = MCostDetail.getByTransaction(model, mtrx,m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID() , m_dimension.getM_CostElement_ID());
	}
	
	public void calculate()
	{

		ProductCost pc = new ProductCost (m_model.getCtx(), 
				m_model.getM_Product_ID(), m_model.getM_AttributeSetInstance_ID(),
				m_model.get_TrxName());
		pc.setQty(m_trx.getMovementQty());
		//

		List<CostComponent> ccs = pc.getProductCostsLayers(m_dimension, 0, false);
		if (ccs == null || ccs.size() == 0)
		{
			MProduct product = MProduct.get(Env.getCtx(), m_model.getM_Product_ID());
			throw new AdempiereException("No Costs for " + product.getName());
		}
		m_calculatedCosts = ccs;
	}

	protected List<CostComponent> getCalculatedCosts()
	{
		if (m_calculatedCosts == null)
			calculate();
		return m_calculatedCosts;
	}
	private List<CostComponent> m_calculatedCosts = null;


	public void updateCurrentCost(MCostDetail m_costdetail)
	{
		MCostQueue[] cQueue = MCostQueue.getQueue(m_dimension, m_costdetail.getDateAcct(), m_costdetail.get_TrxName());
		//TODO: need evaluate this!
		if (cQueue != null)
		{
			MCostType ct = (MCostType) m_dimension.getM_CostType();
			if (cQueue.length >0 && ct.isFifo())
				m_dimension.setCurrentCostPrice(cQueue[0].getCurrentCostPrice());
			else if (cQueue.length > 0 && MCostElement.COSTELEMENTTYPE_LandedCost.equals(m_dimension.getM_CostElement().getCostElementType()))
				m_dimension.setCurrentCostPrice(cQueue[0].getCurrentCostPrice());
		}	
		m_dimension.setCurrentQty(m_dimension.getCurrentQty().add(m_costdetail.getQty()));

		if (cQueue != null && cQueue.length > 0)
		{
			BigDecimal cAmt = cQueue[0].getCurrentCostPrice().multiply(m_costdetail.getQty());
			m_dimension.setCumulatedAmt(m_dimension.getCumulatedAmt().add(cAmt));
			m_dimension.setCumulatedQty(m_dimension.getCumulatedQty().add(m_costdetail.getQty()));
		}
		log.finer("QtyAdjust - FiFo/Lifo - " + m_dimension);		
		m_dimension.saveEx();
	}

	public MCostDetail process()
	{
		processCostDetail();
		return m_costdetail;
	}

	public void processCostDetail()
	{		
		//check if document is entered with delay
		m_last_costdetail =  MCostDetail.getLastTransaction(m_trx,  m_as.getC_AcctSchema_ID() ,m_dimension.getM_CostType_ID(), m_dimension.getM_CostElement_ID(), costingLevel);
		if(m_last_costdetail == null)
		{
			m_last_costdetail = new MCostDetail(m_trx,  m_as.getC_AcctSchema_ID() ,m_dimension.getM_CostType_ID(), m_dimension.getM_CostElement_ID(), Env.ZERO , Env.ZERO, Env.ZERO, m_trx.get_TrxName());
			m_last_costdetail.setDateAcct(new Timestamp(System.currentTimeMillis()));
		}
		
		if(m_model.getReversalLine_ID() > 0)
		{	
			createReversalCostDetail();
			MCostQueue cq = MCostQueue.getQueueForAdjustment(m_costdetail, m_dimension, m_model.get_TrxName());
			if (cq.getCurrentQty().compareTo(m_costdetail.getQty()) == 1 
					||cq.getCurrentQty().compareTo(m_costdetail.getCurrentQty()) == 0)
			{
			cq.setCurrentQty(cq.getCurrentQty().add(m_costdetail.getQty()));
			m_dimension.setCurrentQty(m_dimension.getCurrentQty().add(m_costdetail.getQty()));
			cq.saveEx();
			m_dimension.saveEx();
			}
			else processCostDetail(m_costdetail);
			return;
		}
		if(m_costdetail == null)
		{
			for (MCostDetail cd : createCostDetails(m_dimension, m_trx))
			{
				if (CostDimension.isSameCostDimension(m_as, m_model) && 
					(m_trx.getMovementType().equals("M+") || m_trx.getMovementType().equals("M-")))
				{
					m_costdetail = cd;
					continue;
				}
				processCostDetail(cd);	
				
				if (m_costdetail.getDateAcct().compareTo(m_last_costdetail.getDateAcct()) < 0)
				{
					adjustementQueue(cd);
				}
			}
		}
		else 
		{
			m_Amount = m_trx.getMovementQty().multiply(m_cost);	
			m_AmountLL = m_trx.getMovementQty().multiply(m_costLowLevel);	
			m_CumulatedQty = m_dimension.getCumulatedQty();
			m_AdjustCost = m_Amount.subtract(m_costdetail.getAmt());
			m_CumulatedAmt = m_costdetail.getCumulatedAmt().add(m_Amount).add(m_AdjustCost);			
			m_CumulatedAmtLL = getNewCumulatedAmtLL(m_last_costdetail).add(m_AmountLL);		
			if(m_CumulatedAmt.signum() != 0)
				m_CurrentCostPrice = m_CumulatedAmt.divide(m_CumulatedQty.signum() != 0 ?  m_CumulatedQty : BigDecimal.ONE, m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			else
				m_CurrentCostPrice = Env.ZERO;
			if(m_CumulatedAmtLL.signum() != 0)
				m_CurrentCostPriceLL = m_CumulatedAmtLL.divide(m_CumulatedQty.signum() != 0 ?  m_CumulatedQty : BigDecimal.ONE, m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			else
				m_CurrentCostPriceLL = Env.ZERO;			

			if (m_AdjustCost.compareTo(Env.ZERO) != 0 )
			{
				m_costdetail.setCostAdjustment(m_AdjustCost);
				m_costdetail.setProcessed(false);
				m_costdetail.setDescription("Adjust Cost:"+ m_AdjustCost);
				m_costdetail.setCostAdjustmentDate(m_model.getDateAcct());
				m_costdetail.saveEx();
			}
			m_costdetail.saveEx();
			if (m_costdetail.getCostAdjustmentDate()!= null)
			{
				if(m_as.isAdjustCOGS())	
				{
					adjustementQueue(m_costdetail);
				}		
			}
		}
	}

	//	need this for reversal documents
	public void processCostDetail(MCostDetail cd)
	{
		boolean addition = cd.getQty().signum() > 0;
		MAcctSchema as =  MAcctSchema.get(cd.getCtx(), cd.getC_AcctSchema_ID());
		int precision = as.getCostingPrecision();
		MProduct product = MProduct.get(cd.getCtx(), cd.getM_Product_ID());
		BigDecimal price = cd.getAmt();

		if (cd.getQty().signum() != 0)
			price = cd.getAmt().divide(cd.getQty(), precision, BigDecimal.ROUND_HALF_UP);

		int AD_Org_ID = cd.getAD_Org_ID();
		int M_ASI_ID = cd.getM_AttributeSetInstance_ID();
		
		if (cd.getC_OrderLine_ID() != 0 && !(m_model.getReversalLine_ID() > 0))
		{
			log.finer("Inv - FiFo/LiFo - amt=" + cd.getAmt() + ", qty=" + cd.getQty() + " [NOTHING TO DO]");	
		}

		else if (cd.getM_InOutLine_ID() != 0 		//	AR Shipment Detail Record  
				|| cd.getM_MovementLine_ID() != 0 
				|| cd.getM_InventoryLine_ID() != 0
				|| cd.getM_ProductionLine_ID() != 0
				|| cd.getC_ProjectIssue_ID() != 0
				|| cd.getPP_Cost_Collector_ID() != 0
				|| cd.getC_LandedCostAllocation_ID()!=0)
		{
			if (addition)
			{
				MCostQueue.add(product, M_ASI_ID,
						as, AD_Org_ID, cd.getM_CostElement_ID(),
						cd.getAmt(), cd.getQty(), precision,
						(MCostDetail) cd, cd.get_TrxName());
			}
			else
			{
				BigDecimal amtQueue = MCostQueue.adjustQty(m_dimension,
						cd.getQty().negate(), cd.getDateAcct(), cd.get_TrxName());
				amtQueue = amtQueue.negate(); // outgoing amt should be negative
				if (cd.getAmt().compareTo(amtQueue) != 0)
				{
					BigDecimal priceQueue = Env.ZERO;
					if (cd.getQty().signum() != 0)
						priceQueue = amtQueue.divide(cd.getQty(), precision, BigDecimal.ROUND_HALF_UP);
					log.warning("Amt not match "+this+": price="+price+", priceQueue="+priceQueue+" [ADJUSTED]");
					// FIXME: teo_sarca: should not happen
					if ("Y".equals(Env.getContext(cd.getCtx(), "#M_CostDetail_CorrectAmt")))
					{
						cd.setAmt(amtQueue);
						cd.setAmt(amtQueue);
						cd.setPrice(priceQueue);
					}
					else
					{
						throw new AdempiereException("Amt not match "+this+": price="+price+", priceQueue="+priceQueue); 
					}
				}
			}
			cd.setCumulatedQty(m_dimension.getCumulatedQty());
			cd.setCumulatedAmt(m_dimension.getCumulatedQty());	
			cd.setCurrentCostPrice(m_dimension.getCurrentCostPrice());
			updateCurrentCost(cd);
			cd.saveEx();
			m_costdetail = cd; 
		}
	}
	
	public void adjustementQueue (MCostDetail costDetail)
	{
		final List<MCostDetail> cds;
		if (costDetail.getCostAdjustmentDate()!= null)
		{
			cds = MCostDetail.getAfterAndIncludeCostAdjustmentDate(costDetail);
		}
		else 
			cds = MCostDetail.getAfterDateAcct(costDetail);
		List<Object> list = new ArrayList<Object>();

		for (MCostDetail cd : cds)
		{
			if (cd == null)
				throw new AdempiereException("Error do not exist adjustment");
			MCostQueue cq = MCostQueue.getQueueForAdjustment(cd, m_dimension, m_model.get_TrxName());
			MTransaction trx = get(cd);
			//first condition - cost adjustement
			//second condition - delayed transaction
			if ((!(cq.getCurrentQty().compareTo(Env.ZERO) == 0) && 
				 (trx.getMovementType().equals("C-") || trx.getMovementType().equals("I+") ||
			      trx.getMovementType().equals("I-")) && cd.getCostAdjustmentDate()!= null )
				|| ((trx.getMovementType().equals("C-") || trx.getMovementType().equals("I+") ||
				    trx.getMovementType().equals("I-")) && m_trx.getMovementType().endsWith("+")))
			{ 
				cq.addCurrentQty(cd.getQty().negate());
				cq.saveEx();
				cd.setProcessed(false);
				cd.setAmt(cd.getQty().multiply(m_cost));
				cd.saveEx();
				list.add(cd);
			}
			else if (trx.getMovementType().equals("V+") && costDetail.getCostAdjustmentDate()!= null)
			{
				cd.setProcessed(false);
				cd.setAmt(m_Amount);
				cd.saveEx();
				cq.setCurrentCostPrice(cd.getAmt().divide(cd.getQty()));
				cq.saveEx();
				break;
			}
			else if (trx.getMovementType().equals("M+") || trx.getMovementType().equals("M-"))
			{
				MTransaction trxTo;
				if (trx.getMovementType().equals("M+"))	
				    trxTo = getPrevious(trx);
				else 
				    trxTo = getNext(trx);
				cd.setProcessed(false);
				if (CostDimension.isSameCostDimension(m_as, trx, trxTo))
				{
					cd.setAmt(cd.getQty().multiply(m_cost));
					cd.saveEx();
				}
				else 
				{	
					cq.addCurrentQty(cd.getQty().negate());
					cd.setAmt(cd.getQty().multiply(m_cost));
					cd.saveEx();
					if (trx.getMovementType().equals("M+"))
					    cq.setCurrentCostPrice(cd.getAmt().divide(cd.getQty()));	
					if (cq.getCurrentCostPrice().compareTo(Env.ZERO)== 0)
						cq.setCurrentCostPrice(cd.getCurrentCostPrice());
					cq.saveEx();
					list.add(cd);
				}
			}
			else 
		        continue;

		}
		for (MCostDetail cd : list.toArray(new MCostDetail[list.size()]) )
		{
			processCostDetail(cd);
		}
	} 	
	static public MTransaction get(MCostDetail cd)
	{
		final String whereClause = I_M_Transaction.COLUMNNAME_M_Product_ID + "=? AND "
	                               + I_M_Transaction.COLUMNNAME_M_Transaction_ID+ "=? AND "
	                               + I_M_Transaction.COLUMNNAME_MovementQty + "=?";
		MTransaction trx = new Query(cd.getCtx(), MTransaction.Table_Name, whereClause, cd.get_TrxName())
		.setClient_ID()
		.setParameters(cd.getM_Product_ID(),cd.getM_Transaction_ID(), cd.getQty())
		.firstOnly();
		return trx;
		
	}
	static public MTransaction getPrevious(MTransaction trx)
	{
		final String whereClause = I_M_Transaction.COLUMNNAME_M_Transaction_ID+ "<? AND "
	                               + I_M_Transaction.COLUMNNAME_M_Product_ID + "=? AND "
	                               + I_M_Transaction.COLUMNNAME_MovementQty + "=?";
		return new Query(trx.getCtx(), MTransaction.Table_Name, whereClause, trx.get_TrxName())
		.setClient_ID()
		.setParameters(trx.getM_Transaction_ID(), trx.getM_Product_ID(), trx.getMovementQty().negate())
		.first();
	}
	static public MTransaction getNext(MTransaction trx)
	{
		final String whereClause = I_M_Transaction.COLUMNNAME_M_Transaction_ID+ ">? AND "
		                         + I_M_Transaction.COLUMNNAME_M_Product_ID + "=? AND "
	                             + I_M_Transaction.COLUMNNAME_MovementQty + "=?";
		return new Query(trx.getCtx(), MTransaction.Table_Name, whereClause, trx.get_TrxName())
		.setClient_ID()
		.setParameters(trx.getM_Transaction_ID(), trx.getM_Product_ID(), trx.getMovementQty().negate())
		.first();
	}

	/**
	 * Average Invoice 
	 * Get the New Current Cost Price This Level
	 * @param cd Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	public BigDecimal getNewCurrentCostPrice(MCostDetail cd, int scale,
			int roundingMode) 
	{		
		if(getNewCumulatedQty(cd).signum() != 0 && getNewCumulatedAmt(cd).signum() != 0)
			return getNewCumulatedAmt(cd).divide(getNewCumulatedQty(cd), scale , roundingMode);
		else return BigDecimal.ZERO;
	}


	/**
	 * Get the New Cumulated Amt This Level
	 * @param cd Cost Detail
	 * @return  New Cumulated Amt This Level
	 */
	public BigDecimal getNewCumulatedAmt(MCostDetail cd) {
		
		BigDecimal cumulatedAmt = Env.ZERO;
		if(cd.getQty().signum() > 0)
			cumulatedAmt = cd.getCumulatedAmt().add(cd.getCostAmt()).add(cd.getCostAdjustment());
		else
			cumulatedAmt = cd.getCumulatedAmt().add(cd.getCostAmt().negate()).add(cd.getCostAdjustment().negate());
		
		return  cumulatedAmt;
	}


	/**
	 * Get the New Current Cost Price low level
	 * @param cd Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 */
	public BigDecimal getNewCurrentCostPriceLL(MCostDetail cd, int scale,
			int roundingMode) {
		if(getNewCumulatedQty(cd).signum() != 0 && getNewCumulatedAmtLL(cd).signum() != 0)
			return getNewCumulatedAmtLL(cd).divide(getNewCumulatedQty(cd), scale , roundingMode);
		else return BigDecimal.ZERO;
	}


	/**
	 * Get the new Cumulated Amt Low Level
	 * @param cd MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	public BigDecimal getNewCumulatedAmtLL(MCostDetail cd) {
		BigDecimal cumulatedAmtLL = Env.ZERO;
		if(cd.getQty().signum() > 0)
			 cumulatedAmtLL = cd.getCumulatedAmtLL().add(cd.getCostAmtLL()).add(cd.getCostAdjustmentLL());
		else
			 cumulatedAmtLL = cd.getCumulatedAmtLL().add(cd.getCostAmtLL().negate()).add(cd.getCostAdjustmentLL().negate());
		return cumulatedAmtLL;
	}

	/**
	 * Get the new Cumulated Qty
	 * @param cd Cost Detail
	 * @return New Cumulated Qty
	 */
	public BigDecimal getNewCumulatedQty(MCostDetail cd) {
		return cd.getCumulatedQty().add(cd.getQty());
	}
	
	/**
	 * Update Cost Amt
	 */
	public void updateAmtCost()
	{
		if(m_trx.getMovementType().contains("+"))
		{	
			m_costdetail.setCostAmt(m_costThisLevel.multiply(m_trx.getMovementQty()).abs());
			m_costdetail.setCostAmtLL(m_costLowLevel.multiply(m_trx.getMovementQty()).abs());
		}	
		if(m_trx.getMovementType().contains("-"))
		{	
			m_costdetail.setCostAmt(m_costdetail.getAmt());
			m_costdetail.setCostAmtLL(m_costdetail.getAmtLL());
		}	
	}	
}