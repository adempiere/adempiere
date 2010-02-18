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
package org.compiere.acct;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MConversionRate;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMatchPO;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.model.ProductCost;
import org.compiere.model.X_M_InOut;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Post MatchPO Documents.
 *  <pre>
 *  Table:              C_MatchPO (473)
 *  Document Types:     MXP
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_MatchPO.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_MatchPO extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_MatchPO (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MMatchPO.class, rs, DOCTYPE_MatMatchPO, trxName);
	}   //  Doc_MatchPO

	private int         m_C_OrderLine_ID = 0;
	private MOrderLine	m_oLine = null;
	//
	private int         m_M_InOutLine_ID = 0;
	private MInOutLine		m_ioLine = null;
	private int         m_C_InvoiceLine_ID = 0;
	
	private ProductCost m_pc;
	private int			m_M_AttributeSetInstance_ID = 0;

	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		setC_Currency_ID (Doc.NO_CURRENCY);
		MMatchPO matchPO = (MMatchPO)getPO();
		setDateDoc(matchPO.getDateTrx());
		//
		m_M_AttributeSetInstance_ID = matchPO.getM_AttributeSetInstance_ID();
		setQty (matchPO.getQty());
		//
		m_C_OrderLine_ID = matchPO.getC_OrderLine_ID();
		m_oLine = new MOrderLine (getCtx(), m_C_OrderLine_ID, getTrxName());
		//
		m_M_InOutLine_ID = matchPO.getM_InOutLine_ID();
		m_ioLine = new MInOutLine (getCtx(), m_M_InOutLine_ID, null);	
		
		m_C_InvoiceLine_ID = matchPO.getC_InvoiceLine_ID();
		
		//
		m_pc = new ProductCost (Env.getCtx(), 
			getM_Product_ID(), m_M_AttributeSetInstance_ID, getTrxName());
		m_pc.setQty(getQty());
		return null;
	}   //  loadDocumentDetails

	
	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line and tax amounts from total - no rounding
	 *  @return Zero - always balanced
	 */
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}   //  getBalance

	
	/**
	 *  Create Facts (the accounting logic) for
	 *  MXP.
	 *  <pre>
	 *      Product PPV     <difference>
	 *      PPV_Offset                  <difference>
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();
		//
		if (getM_Product_ID() == 0		//  Nothing to do if no Product
			|| getQty().signum() == 0
			|| m_M_InOutLine_ID == 0)	//  No posting if not matched to Shipment
		{
			log.fine("No Product/Qty - M_Product_ID=" + getM_Product_ID()
				+ ",Qty=" + getQty());
			return facts;
		}

		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID(as.getC_Currency_ID());
		boolean isInterOrg = isInterOrg(as);
		
		//	Purchase Order Line
		BigDecimal poCost = m_oLine.getPriceCost();
		if (poCost == null || poCost.signum() == 0)
			poCost = m_oLine.getPriceActual();
		
		MInOutLine receiptLine = new MInOutLine (getCtx(), m_M_InOutLine_ID, getTrxName());	
		MInOut inOut = receiptLine.getParent(); 
		boolean isReturnTrx = inOut.getMovementType().equals(X_M_InOut.MOVEMENTTYPE_VendorReturns);
		
		// calculate po cost
		poCost = poCost.multiply(getQty());			//	Delivered so far
		
		//	Different currency
		if (m_oLine.getC_Currency_ID() != as.getC_Currency_ID())
		{
			MOrder order = m_oLine.getParent();
			Timestamp dateAcct = order.getDateAcct();
			BigDecimal rate = MConversionRate.getRate(
				order.getC_Currency_ID(), as.getC_Currency_ID(),
				dateAcct, order.getC_ConversionType_ID(),
				m_oLine.getAD_Client_ID(), m_oLine.getAD_Org_ID());
			if (rate == null)
			{
				p_Error = "Purchase Order not convertible - " + as.getName();
				return null;
			}
			poCost = poCost.multiply(rate);
			if (poCost.scale() > as.getCostingPrecision())
				poCost = poCost.setScale(as.getCostingPrecision(), BigDecimal.ROUND_HALF_UP);
		}

		//	Calculate PPV for standard costing
		MProduct product = MProduct.get(getCtx(), getM_Product_ID());
		String costingMethod = product.getCostingMethod(as);
		//get standard cost and also make sure cost for other costing method is updated
		BigDecimal costs = m_pc.getProductCosts(as, getAD_Org_ID(), 
			MAcctSchema.COSTINGMETHOD_StandardCosting, m_C_OrderLine_ID, false);	//	non-zero costs

		if (MAcctSchema.COSTINGMETHOD_StandardCosting.equals(costingMethod))
		{
			//	No Costs yet - no PPV
			if (costs == null || costs.signum() == 0)
			{
				p_Error = "Resubmit - No Costs for " + product.getName();
				log.log(Level.SEVERE, p_Error);
				return null;
			}
	
			//	Difference
			BigDecimal difference = poCost.subtract(costs);
			//	Nothing to post
			if (difference.signum() == 0)
			{
				log.log(Level.FINE, "No Cost Difference for M_Product_ID=" + getM_Product_ID());
				return facts;
			}
	
			//  Product PPV
			FactLine cr = fact.createLine(null,
				m_pc.getAccount(ProductCost.ACCTTYPE_P_PPV, as),
				as.getC_Currency_ID(), isReturnTrx ? difference.negate() : difference);
			if (cr != null)
			{
				cr.setQty(isReturnTrx ? getQty().negate() : getQty());
				cr.setC_BPartner_ID(m_oLine.getC_BPartner_ID());
				cr.setC_Activity_ID(m_oLine.getC_Activity_ID());
				cr.setC_Campaign_ID(m_oLine.getC_Campaign_ID());
				cr.setC_Project_ID(m_oLine.getC_Project_ID());
				cr.setC_ProjectPhase_ID(m_oLine.getC_ProjectPhase_ID());
				cr.setC_ProjectTask_ID(m_oLine.getC_ProjectTask_ID());
				cr.setC_UOM_ID(m_oLine.getC_UOM_ID());
				cr.setUser1_ID(m_oLine.getUser1_ID());
				cr.setUser2_ID(m_oLine.getUser2_ID());
			}
	
			//  PPV Offset
			FactLine dr = fact.createLine(null,
				getAccount(Doc.ACCTTYPE_PPVOffset, as),
				as.getC_Currency_ID(), isReturnTrx ? difference : difference.negate());
			if (dr != null)
			{
				dr.setQty(isReturnTrx ? getQty() : getQty().negate());
				dr.setC_BPartner_ID(m_oLine.getC_BPartner_ID());
				dr.setC_Activity_ID(m_oLine.getC_Activity_ID());
				dr.setC_Campaign_ID(m_oLine.getC_Campaign_ID());
				dr.setC_Project_ID(m_oLine.getC_Project_ID());
				dr.setC_ProjectPhase_ID(m_oLine.getC_ProjectPhase_ID());
				dr.setC_ProjectTask_ID(m_oLine.getC_ProjectTask_ID());
				dr.setC_UOM_ID(m_oLine.getC_UOM_ID());
				dr.setUser1_ID(m_oLine.getUser1_ID());
				dr.setUser2_ID(m_oLine.getUser2_ID());
			}
			
			// Avoid usage of clearing accounts
			// If both accounts Purchase Price Variance and Purchase Price Variance Offset are equal
			// then remove the posting
			
			MAccount acct_db =  dr.getAccount(); // PPV
			MAccount acct_cr = cr.getAccount(); // PPV Offset
			
			if ((!as.isPostIfClearingEqual()) && acct_db.equals(acct_cr) && (!isInterOrg)) {
				
				BigDecimal debit = dr.getAmtSourceDr();
				BigDecimal credit = cr.getAmtSourceCr();
				
				if (debit.compareTo(credit) == 0) {
					fact.remove(dr);
					fact.remove(cr);
				}
			
			}
			// End Avoid usage of clearing accounts
			
			//
			facts.add(fact);
			return facts;
		}
		else
		{
			return facts;
		}
	}   //  createFact
	
	/** Verify if the posting involves two or more organizations
	@return true if there are more than one org involved on the posting
	 */
	private boolean isInterOrg(MAcctSchema as) {
		MAcctSchemaElement elementorg = as.getAcctSchemaElement(MAcctSchemaElement.ELEMENTTYPE_Organization);
		if (elementorg == null || !elementorg.isBalanced()) {
			// no org element or not need to be balanced
			return false;
		}

		// verify if org of receipt line is different from org of order line
		// ignoring invoice line org as not used in posting
		if (m_ioLine != null && m_oLine != null
				&& m_ioLine.getAD_Org_ID() != m_oLine.getAD_Org_ID())
			return true;
		
		return false;
	}

	/**
	 *  Update Product Info (old).
	 *  - Costing (CostStandardPOQty, CostStandardPOAmt)
	 *  @param C_AcctSchema_ID accounting schema
	 *  @deprecated old costing
	 */
	private void updateProductInfo (int C_AcctSchema_ID)
	{
		log.fine("M_MatchPO_ID=" + get_ID());

		//  update Product Costing
		//  requires existence of currency conversion !!
		StringBuffer sql = new StringBuffer (
			"UPDATE M_Product_Costing pc "
			+ "SET (CostStandardPOQty,CostStandardPOAmt) = "
			+ "(SELECT CostStandardPOQty + m.Qty,"
			+ " CostStandardPOAmt + currencyConvert(ol.PriceActual,ol.C_Currency_ID,a.C_Currency_ID,ol.DateOrdered,null,ol.AD_Client_ID,ol.AD_Org_ID)*m.Qty "
			+ "FROM M_MatchPO m, C_OrderLine ol, C_AcctSchema a "
			+ "WHERE m.C_OrderLine_ID=ol.C_OrderLine_ID"
			+ " AND pc.M_Product_ID=ol.M_Product_ID"
			+ " AND pc.C_AcctSchema_ID=a.C_AcctSchema_ID"
			+ " AND m.M_MatchPO_ID=").append(get_ID()).append(") ")
			.append("WHERE pc.C_AcctSchema_ID=").append(C_AcctSchema_ID)
			.append(" AND pc.M_Product_ID=").append(getM_Product_ID());
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_Costing - Updated=" + no);
	}   //  updateProductInfo

}   //  Doc_MatchPO
