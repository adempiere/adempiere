/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.engine.CostComponent;
import org.adempiere.exceptions.CostInsufficientQtyException;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Cost Queue Model
 *	
 *  @author Jorg Janke
 *  @version $Id: MCostQueue.java,v 1.3 2006/07/30 00:51:05 jjanke Exp $
 */
public class MCostQueue extends X_M_CostQueue
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1782836708418500130L;


	/**
	 * 	Get/Create Cost Queue Record.
	 * 	CostingLevel is not validated
	 *	@param product product
	 *	@param M_AttributeSetInstance_ID real asi
	 *	@param as accounting schema
	 *	@param AD_Org_ID real org
	 *	@param M_CostElement_ID element
	 *	@param trxName transaction
	 *	@return cost queue or null
	 */
	public static MCostQueue get (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID, int M_CostElement_ID, String trxName)
	{
		final String whereClause = "AD_Client_ID=? AND AD_Org_ID=?"
									+ " AND "+COLUMNNAME_M_Product_ID+"=?"
									+ " AND "+COLUMNNAME_M_AttributeSetInstance_ID+"=?"
									+ " AND "+COLUMNNAME_M_CostType_ID+"=?"
									+ " AND "+COLUMNNAME_C_AcctSchema_ID+"=?"
									+ " AND "+COLUMNNAME_M_CostElement_ID+"=?";
		final Object[] params = new Object[]{product.getAD_Client_ID(), AD_Org_ID,
											product.get_ID(), M_AttributeSetInstance_ID,
											as.getM_CostType_ID(), as.get_ID(),
											M_CostElement_ID};
		MCostQueue costQ = new Query(product.getCtx(), Table_Name, whereClause, trxName)
								.setParameters(params)
								.first();
		//	New
		if (costQ == null)
		{
			costQ = new MCostQueue (product, M_AttributeSetInstance_ID, as, AD_Org_ID, M_CostElement_ID, trxName);
		}
		return costQ;
	}	//	get
	
    public static MCostQueue getQueueForAdjustment (MCostDetail cd, MCost cost, String trxName)
    {
		final MCostElement ce = MCostElement.get(cost.getCtx(), cost.getM_CostElement_ID());
		
    	List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		
		whereClause.append("AD_Client_ID=? AND AD_Org_ID=?");
		params.add(cost.getAD_Client_ID());
		params.add(cost.getAD_Org_ID());
		
		whereClause.append(" AND "+COLUMNNAME_M_Product_ID+"=?");
		params.add(cost.getM_Product_ID());
		
		final MAcctSchema as = MAcctSchema.get(cost.getCtx(), cost.getC_AcctSchema_ID());
		whereClause.append(" AND "+COLUMNNAME_M_CostType_ID+"=? AND "+COLUMNNAME_C_AcctSchema_ID+"=?");
		params.add(as.getM_CostType_ID());
		params.add(as.get_ID());
		
		whereClause.append(" AND "+COLUMNNAME_M_CostElement_ID+"=?");
		params.add(ce.get_ID());
		
		whereClause.append("AND " +COLUMNNAME_CurrentCostPrice+"=?");
		params.add(cd.getPrice());
		
		whereClause.append("AND " +COLUMNNAME_DateAcct+"=?");
		params.add(cd.getDateAcct());
		
		if (cost.getM_AttributeSetInstance_ID() > 0)
		{
			whereClause.append(" AND "+COLUMNNAME_M_AttributeSetInstance_ID+"=?");
			params.add(cost.getM_AttributeSetInstance_ID());
		}
		
		MCostQueue cq = new Query(cost.getCtx(), Table_Name, whereClause.toString(), trxName)
				.setParameters(params)
				.setOrderBy(COLUMNNAME_M_CostQueue_ID+ " DESC")
				.first();
		if (cq == null)
		{
			s_log.warning("Not found cost queue for "+cost);
			MProduct product = MProduct.get(cost.getCtx(), cost.getM_Product_ID());
			cq = new MCostQueue (product, cost.getM_AttributeSetInstance_ID(), as,
					cost.getAD_Org_ID(), cost.getM_CostElement_ID(), trxName);
			cq.setDateAcct(cd.getDateAcct());
		}
		return cq;
    }
	public static MCostQueue[] getQueue (MCost cost, Timestamp dateAcct, String trxName)
	{
		final MCostType ct = (MCostType) cost.getM_CostType();
		final MCostElement ce = MCostElement.get(cost.getCtx(), cost.getM_CostElement_ID());
		
		String orderByFlag = null;
		if (ct.getCostingMethod() != null)
		{
			if (MCostElement.COSTINGMETHOD_Fifo.equals(ct.getCostingMethod()))
				orderByFlag = " ASC";
			else if (MCostElement.COSTINGMETHOD_Lifo.equals(ct.getCostingMethod()))
				orderByFlag = " DESC";
		}
		else if (ce != null && ct.isFifo())
		{
			orderByFlag = " ASC";
		}
		else if (ce != null && ct.isLifo())
		{
			orderByFlag = " DESC";
		}
		/*else if (ce.isLandedCost())
		{
			orderByFlag = " ASC";
		}*/
		if (orderByFlag == null)
		{
			throw new IllegalArgumentException("Cost element should be FIFO or LIFO - "+ce+", CostingMethod="+ct.getCostingMethod());
		}
		String orderBy = COLUMNNAME_DateAcct+orderByFlag
					+","+COLUMNNAME_M_CostQueue_ID+orderByFlag;
		//
		List<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		
		whereClause.append("AD_Client_ID=? AND AD_Org_ID=?");
		params.add(cost.getAD_Client_ID());
		params.add(cost.getAD_Org_ID());
		
		whereClause.append(" AND "+COLUMNNAME_M_Product_ID+"=?");
		params.add(cost.getM_Product_ID());
		
		final MAcctSchema as = MAcctSchema.get(cost.getCtx(), cost.getC_AcctSchema_ID());
		whereClause.append(" AND "+COLUMNNAME_M_CostType_ID+"=? AND "+COLUMNNAME_C_AcctSchema_ID+"=?");
		params.add(as.getM_CostType_ID());
		params.add(as.get_ID());
		
		whereClause.append(" AND "+COLUMNNAME_M_CostElement_ID+"=?");
		params.add(ce.get_ID());
		
		if (cost.getM_AttributeSetInstance_ID() > 0)
		{
			whereClause.append(" AND "+COLUMNNAME_M_AttributeSetInstance_ID+"=?");
			params.add(cost.getM_AttributeSetInstance_ID());
		}
		
		List<MCostQueue> list = new Query(cost.getCtx(), Table_Name, whereClause.toString(), trxName)
				.setParameters(params)
				.setOrderBy(orderBy)
				.list();
		//
		return list.toArray(new MCostQueue[list.size()]);
	}	//	getQueue

	/**
	 * 	Get Cost Queue Records in Lifo/Fifo order
	 *	@param product product
	 *	@param M_ASI_ID costing level ASI
	 *	@param as accounting schema
	 *	@param Org_ID costing level org
	 *	@param ce Cost Element
	 *	@param trxName transaction
	 *	@return cost queue or null
	 *	@deprecated
	 */
	public static MCostQueue[] getQueue (MProduct product, int M_ASI_ID,
		MAcctSchema as, int Org_ID, MCostElement ce, String trxName)
	{
		ArrayList<MCostQueue> list = new ArrayList<MCostQueue>();
		String sql = "SELECT * FROM M_CostQueue "
			+ "WHERE AD_Client_ID=? AND AD_Org_ID=?"
			+ " AND M_Product_ID=?"
			+ " AND M_CostType_ID=? AND C_AcctSchema_ID=?"
			+ " AND M_CostElement_ID=?";
		if (M_ASI_ID != 0)
			sql += " AND M_AttributeSetInstance_ID=?";
		sql += " AND CurrentQty<>0 "
			+ "ORDER BY M_AttributeSetInstance_ID ";
		if (!ce.isFifo())
			sql += "DESC";
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, trxName);
			pstmt.setInt (1, product.getAD_Client_ID());
			pstmt.setInt (2, Org_ID);
			pstmt.setInt (3, product.getM_Product_ID());
			pstmt.setInt (4, as.getM_CostType_ID());
			pstmt.setInt (5, as.getC_AcctSchema_ID());
			pstmt.setInt (6, ce.getM_CostElement_ID());
			if (M_ASI_ID != 0)
				pstmt.setInt (7, M_ASI_ID);
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
				list.add(new MCostQueue (product.getCtx(), rs, trxName)); 
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		try
		{
			if (pstmt != null)
				pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			pstmt = null;
		}
		MCostQueue[] costQ = new MCostQueue[list.size()];
		list.toArray(costQ);
		return costQ;
	}	//	getQueue
	/**
	 * 	Adjust Qty based on in Lifo/Fifo order
	 *	@param product product
	 *	@param M_ASI_ID costing level ASI
	 *	@param as accounting schema
	 *	@param Org_ID costing level org
	 *	@param ce Cost Element
	 *	@param Qty quantity to be reduced
	 *	@param trxName transaction
	 *	@return cost for qty reduced or null of error
	 */
	public static BigDecimal adjustQty (MCost cost, BigDecimal Qty, Timestamp dateAcct, String trxName)
	{
		if (Qty.signum() == 0)
			return Env.ZERO;
		//
		MCostQueue[] costQ = getQueue(cost, dateAcct, trxName);
		BigDecimal costAmt = Env.ZERO;
		BigDecimal remainingQty = Qty;
		for (int i = 0; i < costQ.length; i++)
		{
			MCostQueue queue = costQ[i];
			//	Negative Qty i.e. add
			if (remainingQty.signum() < 0)
			{
				BigDecimal oldQty = queue.getCurrentQty();
				BigDecimal newQty = oldQty.subtract(remainingQty);
				queue.setCurrentQty(newQty);
				queue.saveEx();
				s_log.fine("Qty=" + remainingQty 
						+ "(!), ASI=" + queue.getM_AttributeSetInstance_ID()
						+ " - " + oldQty + " -> " + newQty);
				BigDecimal lastPrice = queue.getCurrentCostPrice();
				costAmt = costAmt.add(lastPrice.multiply(remainingQty));
				return costAmt;
			}

			//	Positive queue
			if (queue.getCurrentQty().signum() > 0)
			{
				BigDecimal reduction = remainingQty;
				if (reduction.compareTo(queue.getCurrentQty()) > 0)
					reduction = queue.getCurrentQty();
				BigDecimal oldQty = queue.getCurrentQty();
				BigDecimal newQty = oldQty.subtract(reduction);
				queue.setCurrentQty(newQty);
				queue.saveEx();
				s_log.fine("Qty=" + reduction 
						+ ", ASI=" + queue.getM_AttributeSetInstance_ID()
						+ " - " + oldQty + " -> " + newQty);
				remainingQty = remainingQty.subtract(reduction);
				//
				// arhipac: teo_sarca: begin --------------------------------------------------------------------------------------
				//MCostDetailAlloc.create(cd, queue, reduction.negate());
				BigDecimal lastPrice = queue.getCurrentCostPrice();
				costAmt = costAmt.add(reduction.multiply(lastPrice));
				// arhipac: teo_sarca: end ----------------------------------------------------------------------------------------
				//
				if (remainingQty.signum() == 0)
				{
					return costAmt;
				}
			}
		}	//	for queue	

		s_log.fine("RemainingQty=" + remainingQty);
		// arhipac: teo_sarca: begin
		if (remainingQty.signum() != 0)
		{
			throw new CostInsufficientQtyException(cost.getM_Product_ID(), cost.getM_AttributeSetInstance_ID(), Qty, remainingQty); 
		}
		// arhipac: teo_sarca: end
		return null;
	}	//	adjustQty

	/**
	 * 	Calculate Cost based on Qty based on in Lifo/Fifo order
	 *	@param product product
	 *	@param M_ASI_ID costing level ASI
	 *	@param as accounting schema
	 *	@param Org_ID costing level org
	 *	@param ce Cost Element
	 *	@param Qty quantity to be reduced
	 *	@param trxName transaction
	 *	@return cost for qty or null of error
	 */
	public static BigDecimal getCosts (MCost cost, BigDecimal Qty, Timestamp dateAcct, String trxName)
	{
		List<CostComponent> list = getCostLayers(cost, Qty, dateAcct, trxName);
		if (list == null)
			return null;
		//
		BigDecimal costs = Env.ZERO;
		BigDecimal qtyCalc = Env.ZERO;
		for (CostComponent cc : list)
		{
			costs = costs.add(cc.getAmount());
			qtyCalc = qtyCalc.add(cc.qty);
		}
		if (qtyCalc.compareTo(Qty) != 0)
		{
			throw new IllegalStateException("Invalid Qty - Required="+Qty+", Calculated="+qtyCalc);
		}
		return costs;
	}
	
	/**
	 * 	Calculate Cost based on Qty based on in Lifo/Fifo order
	 *	@param product product
	 *	@param M_ASI_ID costing level ASI
	 *	@param as accounting schema
	 *	@param Org_ID costing level org
	 *	@param ce Cost Element
	 *	@param Qty quantity to be reduced
	 *	@param trxName transaction
	 *	@return cost for qty or null of error
	 *	@deprecated
	 */
	public static BigDecimal getCosts (MProduct product, int M_ASI_ID,
		MAcctSchema as, int Org_ID, int M_Warehouse_ID, MCostElement ce, BigDecimal Qty, 
		String trxName)
	{
		if (Qty.signum() == 0)
			return Env.ZERO;
		MCostQueue[] costQ = getQueue(product, M_ASI_ID, 
			as, Org_ID, ce, trxName);
		//
		BigDecimal cost = Env.ZERO;
		BigDecimal remainingQty = Qty;
		BigDecimal firstPrice = null;
		BigDecimal lastPrice = null;
		//
		for (int i = 0; i < costQ.length; i++)
		{
			MCostQueue queue = costQ[i];
			//	Negative Qty i.e. add
			if (remainingQty.signum() <= 0)
			{
				BigDecimal oldQty = queue.getCurrentQty();
				lastPrice = queue.getCurrentCostPrice();
				BigDecimal costBatch = lastPrice.multiply(remainingQty);
				cost = cost.add(costBatch);
				s_log.config("ASI=" + queue.getM_AttributeSetInstance_ID()
					+ " - Cost=" + lastPrice + " * Qty=" + remainingQty + "(!) = " + costBatch);
				return cost;
			}
			
			//	Positive queue
			if (queue.getCurrentQty().signum() > 0)
			{
				BigDecimal reduction = remainingQty;
				if (reduction.compareTo(queue.getCurrentQty()) > 0)
					reduction = queue.getCurrentQty();
				BigDecimal oldQty = queue.getCurrentQty();
				lastPrice = queue.getCurrentCostPrice();
				BigDecimal costBatch = lastPrice.multiply(reduction);
				cost = cost.add(costBatch);
				s_log.fine("ASI=" + queue.getM_AttributeSetInstance_ID()
					+ " - Cost=" + lastPrice + " * Qty=" + reduction + " = " + costBatch);
				remainingQty = remainingQty.subtract(reduction);
				//	Done
				if (remainingQty.signum() == 0)
				{
					s_log.config("Cost=" + cost);
					return cost;
				}
				if (firstPrice == null)
					firstPrice = lastPrice;
			}
		}	//	for queue

		if (lastPrice == null)
		{
			lastPrice = MCost.getSeedCosts(product, M_ASI_ID, as, Org_ID, M_Warehouse_ID,
				ce.getCostingMethod(), 0);
			if (lastPrice == null)
			{
				s_log.info("No Price found");
				return null;
			}
			s_log.info("No Cost Queue");
		}
		BigDecimal costBatch = lastPrice.multiply(remainingQty);
		s_log.fine("RemainingQty=" + remainingQty + " * LastPrice=" + lastPrice + " = " + costBatch);
		cost = cost.add(costBatch);
		s_log.config("Cost=" + cost);
		return cost;
	}	//	getCosts
	/**
	 * get Cost Componebt
	 * @param cost
	 * @param Qty
	 * @param dateAcct
	 * @param trxName
	 * @return
	 */
	public static List<CostComponent> getCostLayers (MCost cost, BigDecimal Qty,
			Timestamp dateAcct, String trxName)
			{
		ArrayList<CostComponent> list = new ArrayList<CostComponent>();
		if (Qty.signum() == 0)
			return list;
		MCostQueue[] costQ = getQueue(cost, dateAcct, trxName);
		//
		BigDecimal costAmt = Env.ZERO;
		BigDecimal remainingQty = Qty;
		//		BigDecimal firstPrice = null;
		BigDecimal lastPrice = null;
		//

		for (int i = 0; i < costQ.length; i++)
		{
			MCostQueue queue = costQ[i];
				//	Negative Qty i.e. add
				/*if (remainingQty.signum() <= 0 && queue.getCurrentQty().signum() != 0)
			{
				BigDecimal reduction = remainingQty;//anca
				if (reduction.negate().compareTo(queue.getCurrentQty()) > 0)//anca
					reduction = queue.getCurrentQty().negate();//anca
				lastPrice = queue.getCurrentCostPrice();
				BigDecimal costBatch = lastPrice.multiply(reduction);
				list.add(new CostComponent(reduction, lastPrice));
				cost = cost.add(costBatch);
				s_log.config("ASI=" + queue.getM_AttributeSetInstance_ID()
					+ " - Cost=" + lastPrice + " * Qty=" + reduction+ "(!) = " + costBatch);
				//return cost;
				remainingQty = remainingQty.subtract(reduction);//anca
				if (queue.getCurrentQty().equals(Env.ZERO))	
		    	{
					return list;
		    	}

			}*/	
				//	Positive queue
				if (queue.getCurrentQty().signum() > 0)
				{
					BigDecimal reduction = remainingQty;
					if (reduction.negate().compareTo(queue.getCurrentQty()) > 0)
						reduction = queue.getCurrentQty().negate();
					// BigDecimal oldQty = queue.getCurrentQty();
					lastPrice = queue.getCurrentCostPrice();
					BigDecimal costBatch = lastPrice.multiply(reduction);
					list.add(new CostComponent(reduction, lastPrice));
					costAmt = costAmt.add(costBatch);
					s_log.fine("ASI=" + queue.getM_AttributeSetInstance_ID()
							+ " - Cost=" + lastPrice + " * Qty=" + reduction + " = " + costBatch);
					remainingQty = remainingQty.subtract(reduction);
					//	Done
					if (remainingQty.signum() == 0)
					{
						s_log.config("Cost=" + cost);
						return list; 
					}
					//				if (firstPrice == null)
					//					firstPrice = lastPrice;
				}
		}
		//	for queue

		// TODO: arhipac: teo_sarca: implement "Insufficient Qty Cost Option"
		if (lastPrice == null)
		{
			lastPrice = MCost.getSeedCosts(
					MProduct.get(cost.getCtx(), cost.getM_Product_ID()),
					cost.getM_AttributeSetInstance_ID(),
					MAcctSchema.get(cost.getCtx(), cost.getC_AcctSchema_ID()),
					cost.getAD_Org_ID(),
					cost.getM_Warehouse_ID(),
					cost.getCostingMethod(),
					0 // C_OrderLine_ID
			);
			if (lastPrice == null)
			{
				s_log.info("No Price found");
				return null;
			}
			s_log.info("No Cost Queue");
		}
		BigDecimal costBatch = lastPrice.multiply(remainingQty);
		s_log.fine("RemainingQty=" + remainingQty + " * LastPrice=" + lastPrice + " = " + costBatch);
		costAmt = costAmt.add(costBatch);
		list.add(new CostComponent(remainingQty, lastPrice));
		s_log.config("Cost=" + cost);
		return list;
	}	//	getCosts
	
	
	/**	Logger	*/
	private static CLogger 	s_log = CLogger.getCLogger (MCostQueue.class);
	
	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param ignored multi-key
	 *	@param trxName trx
	 */
	public MCostQueue (Properties ctx, int ignored, String trxName)
	{
		super (ctx, ignored, trxName);
		if (ignored == 0)
		{
		//	setC_AcctSchema_ID (0);
		//	setM_AttributeSetInstance_ID (0);
		//	setM_CostElement_ID (0);
		//	setM_CostType_ID (0);
		//	setM_Product_ID (0);
			setCurrentCostPrice (Env.ZERO);
			setCurrentQty (Env.ZERO);
		}
		else
			throw new IllegalArgumentException("Multi-Key");
	}	//	MCostQueue

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName trx
	 */
	public MCostQueue (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}	//	MCostQueue

	/**
	 * 	Parent Constructor
	 *	@param product Product
	 *	@param M_AttributeSetInstance_ID asi
	 *	@param as Acct Schema
	 *	@param AD_Org_ID org
	 *	@param M_CostElement_ID cost element
	 *	@param trxName transaction
	 */
	public MCostQueue (MProduct product, int M_AttributeSetInstance_ID, 
		MAcctSchema as, int AD_Org_ID, int M_CostElement_ID, String trxName)
	{
		this (product.getCtx(), 0, trxName);
		setClientOrg(product.getAD_Client_ID(), AD_Org_ID);
		setC_AcctSchema_ID(as.getC_AcctSchema_ID());
		setM_CostType_ID(as.getM_CostType_ID());
		setM_Product_ID(product.getM_Product_ID());
		setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
		setM_CostElement_ID(M_CostElement_ID);
	}	//	MCostQueue

	/**
	 * 	Update Record.
	 * 	((OldAvg*OldQty)+(Price*Qty)) / (OldQty+Qty)
	 *	@param amt total Amount
	 *	@param qty quantity
	 *	@param precision costing precision
	 */
	public void setCosts (BigDecimal amt, BigDecimal qty, int precision)
	{
		BigDecimal oldSum = getCurrentCostPrice().multiply(getCurrentQty());
		BigDecimal newSum = amt;	//	is total already
		BigDecimal sumAmt = oldSum.add(newSum);
		BigDecimal sumQty = getCurrentQty().add(qty);
		if (sumQty.signum() != 0)
		{
			BigDecimal cost = sumAmt.divide(sumQty, precision, BigDecimal.ROUND_HALF_UP);
			setCurrentCostPrice(cost);
		}
		//
		setCurrentQty(getCurrentQty().add(qty));
	}	//	update

	/**
	 * @category arhipac
	 */
	@Override
	public String toString()
	{
		return getClass().getSimpleName() + "[" + get_ID()
			+ ", AD_Org_ID=" + getAD_Org_ID()
			+ ", M_Product_ID=" + getM_Product_ID()
			+ ", M_ASI_ID=" + getM_AttributeSetInstance_ID()
			+ ", Price=" + getCurrentCostPrice()
			+ ", Qty=" + getCurrentQty()
			+ ", DateAcct="+getDateAcct()
			+ "]"
		;
	}
	
	/**
	 * Push to Queue Record.
	 * CostingLevel is not validated
	 * @category arhipac
	 */
	public static MCostQueue add (MProduct product, int M_AttributeSetInstance_ID,
		MAcctSchema as, int AD_Org_ID, int M_CostElement_ID,
		BigDecimal amt, BigDecimal qty, int precision,
		MCostDetail cd,
		String trxName)
	{
		if(CLogMgt.isLevelFine()) s_log.fine("Entering: org=" + AD_Org_ID + ", product=" + product.get_ID() + ", asi=" + M_AttributeSetInstance_ID + ", amt=" + amt + ", qty=" + qty + ", precision=" + precision);
		//
		MCostQueue cq = new MCostQueue (product, M_AttributeSetInstance_ID, as, AD_Org_ID, M_CostElement_ID, trxName);
		cq.setCosts(amt, qty, precision);
		cq.setDateAcct(cd.getDateAcct());
		cq.saveEx();
		//MCostDetailAlloc.create(cd, cq, qty);
		//
		if(CLogMgt.isLevelFine()) s_log.fine("Leaving: " + cq);
		return cq;
	}
	
	public void addCurrentQty(BigDecimal delta)
	{
		setCurrentQty(getCurrentQty().add(delta));
	}
}	//	MCostQueue
