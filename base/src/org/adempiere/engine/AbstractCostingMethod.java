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
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMatchPO;
import org.compiere.model.MMovementLine;
import org.compiere.model.MTransaction;
import org.compiere.model.Query;
import org.compiere.model.X_M_Transaction;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;


/**
 * @author anca_bradau
 * @param <abtract>
 *
 */
public abstract class AbstractCostingMethod implements ICostingMethod
{
	protected final CLogger log = CLogger.getCLogger (getClass());

	MAcctSchema m_as;
	IDocumentLine m_model;
	MTransaction m_trx; 
	MCost m_dimension;
	Boolean m_isSOTrx;
	BigDecimal m_costThisLevel;
	BigDecimal m_costLowLevel;
	BigDecimal m_cost;
	MCostDetail m_costdetail = null;
	BigDecimal m_CumulatedAmt = Env.ZERO;
	BigDecimal m_CumulatedAmtLL = Env.ZERO;
	BigDecimal m_CumulatedQty = Env.ZERO;
	BigDecimal m_CurrentCostPrice = Env.ZERO;
	BigDecimal m_CurrentCostPriceLL = Env.ZERO;
	BigDecimal m_Amount= Env.ZERO;
	BigDecimal m_AmountLL= Env.ZERO;
	BigDecimal m_AdjustCost = Env.ZERO;
	BigDecimal m_AdjustCostLL = Env.ZERO;
	MCostDetail m_last_costdetail = null;
	String costingLevel;

	protected List<MCostDetail> createCostDetails(MCost cost,
			MTransaction mtrx)
	{ 
		IDocumentLine model = mtrx.getDocumentLine();
		
		final String idColumnName;
		if (model instanceof MMatchPO)
		{
			idColumnName = I_C_OrderLine.COLUMNNAME_C_OrderLine_ID;
		}
		else if (model instanceof MMatchInv)
		{
			idColumnName = I_C_InvoiceLine.COLUMNNAME_C_InvoiceLine_ID;
		}
		else
		{
			idColumnName = model.get_TableName()+"_ID";
		}


		List<MCostDetail> list = new ArrayList<MCostDetail>();
		if (model.isSOTrx()== true || model instanceof MInventoryLine || model instanceof MMovementLine)
		{
			List<CostComponent> ccs = getCalculatedCosts();
			for (CostComponent cc : ccs)
			{
				MCostDetail cd = new MCostDetail(m_trx, m_as.getC_AcctSchema_ID() ,m_dimension.getM_CostType_ID(), m_dimension.getM_CostElement_ID(), cc.getAmount(), Env.ZERO, cc.getQty(), m_model.get_TrxName());
				if (!cd.set_ValueOfColumnReturningBoolean(idColumnName, model.get_ID()))
					throw new AdempiereException("Cannot set "+idColumnName);

				StringBuilder description = new StringBuilder();
				if (!Util.isEmpty(model.getDescription(), true))
					description.append(model.getDescription());
				if (model.isSOTrx() != false)
				{
					description.append(model.isSOTrx() ? "(|->)" : "(|<-)");
				}
				if (model.isSOTrx()!= false) //TODO: need evaluate anca
					cd.setIsSOTrx(model.isSOTrx());
				else
					cd.setIsSOTrx(model.isSOTrx());	
				cd.setM_Transaction_ID(mtrx.get_ID());
				cd.setDescription(description.toString());
				cd.saveEx();
				list.add(cd);
			}
		}
		else //qty and amt is take from documentline
		{
			MCostDetail cd = new MCostDetail(m_trx,  m_as.getC_AcctSchema_ID() ,m_dimension.getM_CostType_ID(), m_dimension.getM_CostElement_ID() , m_costThisLevel.multiply(model.getMovementQty()),  Env.ZERO, model.getMovementQty(), m_model.get_TrxName());
			int id;
			if (model instanceof MMatchPO)
			{
				
			    I_M_InOutLine iline = mtrx.getM_InOutLine();
			    I_C_OrderLine oline = iline.getC_OrderLine();
			    id = oline.getC_OrderLine_ID();
			    
			}
			else 
			{
				id = model.get_ID(); 
			}
			if (!cd.set_ValueOfColumnReturningBoolean(idColumnName, id))
				throw new AdempiereException("Cannot set "+idColumnName);
			if (model.isSOTrx()!= false)
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
	public void updateInventoryValue()
	{
		m_dimension.setCurrentCostPrice(m_CurrentCostPrice);
		m_dimension.setCurrentCostPriceLL(m_CurrentCostPriceLL);
		m_dimension.setCumulatedAmt(m_CumulatedAmt);
		m_dimension.setCumulatedAmtLL(m_CumulatedAmtLL);
		m_dimension.setCumulatedQty(m_CumulatedQty);
		m_dimension.setCurrentQty(m_CumulatedQty);
		m_dimension.saveEx();
	}
	
	/**
	 * Create Reversal Transaction
	 */
	public MCostDetail createReversalCostDetail()
	{			
			List<MTransaction> trxs = MTransaction.getByDocumentLine(m_trx);
			if(trxs == null)
				throw new AdempiereException("Can not found the original transaction");
			
			m_costdetail = new MCostDetail(m_model.getCtx(), 0 , m_trx.get_TrxName());
			
			for(MTransaction original_trx: trxs)
			{
				IDocumentLine model = m_trx.getDocumentLine();
				String idColumnName = model.get_TableName()+"_ID";											
				
				//Qty Transaction								
				MCostDetail original_cd = MCostDetail.getByTransaction(original_trx,m_as.getC_AcctSchema_ID(), m_dimension.getM_CostType_ID(), m_dimension.getM_CostElement_ID());
				if(original_cd == null)
					throw new AdempiereException("Can not found the original cost detail");
				
				if(trxs.get(0).equals(original_trx))
				{					
					m_costdetail.setAD_Org_ID(original_cd.getAD_Org_ID());
					m_costdetail.copyValues(original_cd , m_costdetail);
					m_costdetail.setCumulatedAmt(getNewCumulatedAmt(m_last_costdetail));	
					m_costdetail.setCumulatedQty(getNewCumulatedQty(m_last_costdetail));
					m_costdetail.setCurrentCostPrice(getNewCurrentCostPrice(m_last_costdetail,m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP));				
					m_costdetail.setAmt(m_CurrentCostPrice.multiply(m_trx.getMovementQty()).abs());
					m_costdetail.setCumulatedAmtLL(getNewCumulatedAmtLL(m_last_costdetail));	
					m_costdetail.setCumulatedQty(getNewCumulatedQty(m_last_costdetail));
					m_costdetail.setCurrentCostPriceLL(getNewCurrentCostPriceLL(m_last_costdetail,m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP));				
					m_costdetail.setAmtLL(m_CurrentCostPriceLL.multiply(m_trx.getMovementQty()));
					
					m_costdetail.setQty(Env.ZERO);
					m_costdetail.setAmt(Env.ZERO);
					m_costdetail.setCostAmt(Env.ZERO);
					m_costdetail.setCostAdjustment(Env.ZERO);
					m_costdetail.setAmtLL(Env.ZERO);
					m_costdetail.setCostAmtLL(Env.ZERO);
					m_costdetail.setCostAdjustmentLL(Env.ZERO);
				}
				
				BigDecimal qty = m_costdetail.getQty();
				BigDecimal amt = m_costdetail.getAmt();
				BigDecimal costAmt = m_costdetail.getCostAmt();
				BigDecimal costAmtLL = m_costdetail.getCostAmtLL();
				BigDecimal costAdjustment = m_costdetail.getCostAdjustment();
				BigDecimal costAdjustmentLL = m_costdetail.getCostAdjustmentLL();
				
				
				m_costdetail.setQty(original_cd.getQty().negate().add(qty));
				m_costdetail.setAmt(original_cd.getAmt().add(amt));
				m_costdetail.setCostAmt(original_cd.getCostAmt().add(costAmt));
				m_costdetail.setCostAdjustment(original_cd.getCostAdjustment().add(costAdjustment));
				m_costdetail.setCostAdjustmentDate(original_cd.getCostAdjustmentDate());
				//Cost Lower Level
				m_costdetail.setAmtLL(original_cd.getAmtLL().add(costAmtLL));
				m_costdetail.setCostAmtLL(original_cd.getCostAmtLL().add(costAmtLL));
				m_costdetail.setCostAdjustmentLL(original_cd.getCostAdjustmentLL().add(costAdjustmentLL));
				m_costdetail.setCostAdjustmentDateLL(original_cd.getCostAdjustmentDateLL());
				m_costdetail.setM_AttributeSetInstance_ID(m_trx.getM_AttributeSetInstance_ID());

				m_costdetail.setDateAcct(original_cd.getDateAcct());
				m_costdetail.setProcessing(false);
				if (!m_costdetail.set_ValueOfColumnReturningBoolean(idColumnName, model.get_ID()))
					throw new AdempiereException("Cannot set "+idColumnName);
				m_costdetail.setM_Transaction_ID(m_trx.getM_Transaction_ID());
				m_costdetail.setDescription("Reversal " + original_cd.getM_Transaction_ID());
				m_costdetail.setIsReversal(true);
				m_costdetail.saveEx();
				
				//Update the original cost detail
				original_cd.setDescription(original_cd.getDescription() != null ? original_cd.getDescription(): "" + "|Reversal " + m_costdetail.getM_Transaction_ID());
				original_cd.setIsReversal(true);
				original_cd.saveEx(m_trx.get_TrxName());
				
				//Update the new cost detail
				m_CumulatedQty = getNewCumulatedQty(m_costdetail);
				m_CumulatedAmt = getNewCumulatedAmt(m_costdetail);
				m_CumulatedAmtLL = getNewCumulatedAmtLL(m_costdetail);
				m_CurrentCostPrice = getNewCurrentCostPrice(m_costdetail,m_as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
			}
			
			return m_costdetail;
	}
	
	/**
	 * Method to implement the costing method 
	 * Get the New Current Cost Price This Level
	 * @param cd Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price This Level
	 */
	abstract public BigDecimal getNewCurrentCostPrice(MCostDetail cd,int scale, int roundingMode);
	
	/**
	 * Method to implement the costing method 
	 * Get the New Cumulated Amt This Level
	 * @param cd Cost Detail
	 * @return  New Cumulated Amt This Level
	 */
	abstract public BigDecimal getNewCumulatedAmt(MCostDetail cd);
	
	/**
	 * Method to implement the costing method 
	 * Get the New Current Cost Price low level
	 * @param cd Cost Detail
	 * @param scale Scale
	 * @param roundingMode Rounding Mode
	 * @return New Current Cost Price low level
	 */
	abstract public BigDecimal getNewCurrentCostPriceLL(MCostDetail cd,int scale, int roundingMode);
	
	/**
	 * Method to implement the costing method 
	 * Get the new Cumulated Amt Low Level
	 * @param cd MCostDetail
	 * @return New Cumulated Am Low Level
	 */
	abstract public BigDecimal getNewCumulatedAmtLL(MCostDetail cd);
	
	/**
	 * Method to implement the costing method 
	 * Get the new Cumulated Qty
	 * @param cd Cost Detail
	 * @return New Cumulated Qty
	 */
	abstract public BigDecimal getNewCumulatedQty(MCostDetail cd);
	
	/**
	 * Recalculate Cost Detail
	 * @param cd Cost Detail
	 * @param ct Cost Type
	 * @param ce Cost Element
	 */
	public void adjustCostDetail(MCostDetail cd , MCostType ct, MCostElement ce)
	{
		MTransaction trx = new MTransaction(m_model.getCtx(), cd.getM_Transaction_ID(), m_model.get_TrxName());
		IDocumentLine docLine = trx.getDocumentLine();
		CostEngineFactory.getCostEngine(m_model.getAD_Client_ID()).createCostDetail(m_as,trx,docLine,ct,ce);
	}
}
