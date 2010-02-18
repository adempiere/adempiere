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
import java.util.ArrayList;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAcctSchemaElement;
import org.compiere.model.MConversionRate;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMatchInv;
import org.compiere.model.ProductCost;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Post MatchInv Documents.
 *  <pre>
 *  Table:              M_MatchInv (472)
 *  Document Types:     MXI
 *  </pre>
 *  Update Costing Records
 *  @author Jorg Janke
 *  @version  $Id: Doc_MatchInv.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 *  
 *  FR [ 1840016 ] Avoid usage of clearing accounts - subject to C_AcctSchema.IsPostIfClearingEqual 
 *  Avoid posting if both accounts Not Invoiced Receipts and Inventory Clearing are equal
 *  BF [ 2789949 ] Multicurrency in matching posting
 */
public class Doc_MatchInv extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_MatchInv (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super(ass, MMatchInv.class, rs, DOCTYPE_MatMatchInv, trxName);
	}   //  Doc_MatchInv

	/** Invoice Line			*/
	private MInvoiceLine	m_invoiceLine = null;
	/** Material Receipt		*/
	private MInOutLine		m_receiptLine = null;
	
	private ProductCost		m_pc = null;

	/** Commitments			*/
//	private DocLine[]		m_commitments = null;
	
	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		setC_Currency_ID (Doc.NO_CURRENCY);
		MMatchInv matchInv = (MMatchInv)getPO();
		setDateDoc(matchInv.getDateTrx());
		setQty (matchInv.getQty());
		//	Invoice Info
		int C_InvoiceLine_ID = matchInv.getC_InvoiceLine_ID();
		m_invoiceLine = new MInvoiceLine (getCtx(), C_InvoiceLine_ID, null);
		//		BP for NotInvoicedReceipts
		int C_BPartner_ID = m_invoiceLine.getParent().getC_BPartner_ID(); 
		setC_BPartner_ID(C_BPartner_ID);
		//
		int M_InOutLine_ID = matchInv.getM_InOutLine_ID();
		m_receiptLine = new MInOutLine (getCtx(), M_InOutLine_ID, null);		
		//
		m_pc = new ProductCost (Env.getCtx(), 
			getM_Product_ID(), matchInv.getM_AttributeSetInstance_ID(), null);
		m_pc.setQty(getQty());
		
		return null;
	}   //  loadDocumentDetails


	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line and tax amounts from total - no rounding
	 *  @return Zero (always balanced)
	 */
	public BigDecimal getBalance()
	{
		return Env.ZERO;
	}   //  getBalance

	
	/**
	 *  Create Facts (the accounting logic) for
	 *  MXI.
	 * 	(single line)
	 *  <pre>
	 *      NotInvoicedReceipts     DR			(Receipt Org)
	 *      InventoryClearing               CR
	 *      InvoicePV               DR      CR  (difference)
	 *  Commitment
	 * 		Expense							CR
	 * 		Offset					DR
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();
		//  Nothing to do
		if (getM_Product_ID() == 0								//	no Product
			|| getQty().signum() == 0
			|| m_receiptLine.getMovementQty().signum() == 0)	//	Qty = 0
		{
			log.fine("No Product/Qty - M_Product_ID=" + getM_Product_ID()
				+ ",Qty=" + getQty() + ",InOutQty=" + m_receiptLine.getMovementQty());
			return facts;
		}
//		MMatchInv matchInv = (MMatchInv)getPO();
		
		//  create Fact Header
		Fact fact = new Fact(this, as, Fact.POST_Actual);
		setC_Currency_ID (as.getC_Currency_ID());
		boolean isInterOrg = isInterOrg(as);

		/**	Needs to be handeled in PO Matching as no Receipt info
		if (m_pc.isService())
		{
			log.fine("Service - skipped");
			return fact;
		}
		**/
		
		
		//  NotInvoicedReceipt      DR
		//  From Receipt
		BigDecimal multiplier = getQty()
			.divide(m_receiptLine.getMovementQty(), 12, BigDecimal.ROUND_HALF_UP)
			.abs();
		FactLine dr = fact.createLine (null,
			getAccount(Doc.ACCTTYPE_NotInvoicedReceipts, as),
			as.getC_Currency_ID(), Env.ONE, null);			// updated below
		if (dr == null)
		{
			p_Error = "No Product Costs";
			return null;
		}
		dr.setQty(getQty());
	//	dr.setM_Locator_ID(m_receiptLine.getM_Locator_ID());
	//	MInOut receipt = m_receiptLine.getParent();
	//	dr.setLocationFromBPartner(receipt.getC_BPartner_Location_ID(), true);	//  from Loc
	//	dr.setLocationFromLocator(m_receiptLine.getM_Locator_ID(), false);		//  to Loc
		BigDecimal temp = dr.getAcctBalance();
		//	Set AmtAcctCr/Dr from Receipt (sets also Project)
		if (!dr.updateReverseLine (MInOut.Table_ID, 		//	Amt updated
			m_receiptLine.getM_InOut_ID(), m_receiptLine.getM_InOutLine_ID(),
			multiplier))
		{
			p_Error = "Mat.Receipt not posted yet";
			return null;
		}
		log.fine("CR - Amt(" + temp + "->" + dr.getAcctBalance() 
			+ ") - " + dr.toString());

		//  InventoryClearing               CR
		//  From Invoice
		MAccount expense = m_pc.getAccount(ProductCost.ACCTTYPE_P_InventoryClearing, as);
		if (m_pc.isService())
			expense = m_pc.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
		BigDecimal LineNetAmt = m_invoiceLine.getLineNetAmt();
		multiplier = getQty()
			.divide(m_invoiceLine.getQtyInvoiced(), 12, BigDecimal.ROUND_HALF_UP)
			.abs();
		if (multiplier.compareTo(Env.ONE) != 0)
			LineNetAmt = LineNetAmt.multiply(multiplier);
		if (m_pc.isService())
			LineNetAmt = dr.getAcctBalance();	//	book out exact receipt amt
		FactLine cr = null;
		if (as.isAccrual())
		{
			cr = fact.createLine (null, expense,
				as.getC_Currency_ID(), null, LineNetAmt);		//	updated below
			if (cr == null)
			{
				log.fine("Line Net Amt=0 - M_Product_ID=" + getM_Product_ID()
					+ ",Qty=" + getQty() + ",InOutQty=" + m_receiptLine.getMovementQty());
				
				//  Invoice Price Variance
				BigDecimal ipv = dr.getSourceBalance().negate();
				if (ipv.signum() != 0)
				{
					MInvoice m_invoice = m_invoiceLine.getParent();
					int C_Currency_ID = m_invoice.getC_Currency_ID();
					FactLine pv = fact.createLine(null,
							m_pc.getAccount(ProductCost.ACCTTYPE_P_IPV, as),
							C_Currency_ID, ipv);
					pv.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
					pv.setC_Campaign_ID(m_invoiceLine.getC_Campaign_ID());
					pv.setC_Project_ID(m_invoiceLine.getC_Project_ID());
					pv.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
					pv.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
					pv.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
					pv.setUser1_ID(m_invoiceLine.getUser1_ID());
					pv.setUser2_ID(m_invoiceLine.getUser2_ID());
				}
				log.fine("IPV=" + ipv + "; Balance=" + fact.getSourceBalance());
				facts.add(fact);
				return facts;
			}
			cr.setQty(getQty().negate());
			temp = cr.getAcctBalance();
			//	Set AmtAcctCr/Dr from Invoice (sets also Project)
			if (as.isAccrual() && !cr.updateReverseLine (MInvoice.Table_ID, 		//	Amt updated
				m_invoiceLine.getC_Invoice_ID(), m_invoiceLine.getC_InvoiceLine_ID(), multiplier))
			{
				p_Error = "Invoice not posted yet";
				return null;
			}
			log.fine("DR - Amt(" + temp + "->" + cr.getAcctBalance() 
				+ ") - " + cr.toString());
		}
		else	//	Cash Acct
		{
			MInvoice invoice = m_invoiceLine.getParent();
			if (as.getC_Currency_ID() != invoice.getC_Currency_ID())
				LineNetAmt = MConversionRate.convert(getCtx(), LineNetAmt, 
					invoice.getC_Currency_ID(), as.getC_Currency_ID(),
					invoice.getDateAcct(), invoice.getC_ConversionType_ID(),
					invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
			cr = fact.createLine (null, expense,
				as.getC_Currency_ID(), null, LineNetAmt);
			cr.setQty(getQty().multiply(multiplier).negate());
		}
		cr.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
		cr.setC_Campaign_ID(m_invoiceLine.getC_Campaign_ID());
		cr.setC_Project_ID(m_invoiceLine.getC_Project_ID());
		cr.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
		cr.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
		cr.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
		cr.setUser1_ID(m_invoiceLine.getUser1_ID());
		cr.setUser2_ID(m_invoiceLine.getUser2_ID());

		//AZ Goodwill
		//Desc: Source Not Balanced problem because Currency is Difference - PO=CNY but AP=USD 
		//see also Fact.java: checking for isMultiCurrency()
		if (dr.getC_Currency_ID() != cr.getC_Currency_ID())
			setIsMultiCurrency(true);
		//end AZ
		
		// Avoid usage of clearing accounts
		// If both accounts Not Invoiced Receipts and Inventory Clearing are equal
		// then remove the posting
		
		MAccount acct_db =  dr.getAccount(); // not_invoiced_receipts
		MAccount acct_cr = cr.getAccount(); // inventory_clearing
		
		if ((!as.isPostIfClearingEqual()) && acct_db.equals(acct_cr) && (!isInterOrg)) {
			
			BigDecimal debit = dr.getAmtSourceDr();
			BigDecimal credit = cr.getAmtSourceCr();
			
			if (debit.compareTo(credit) == 0) {
				fact.remove(dr);
				fact.remove(cr);
			}
		
		}
		// End Avoid usage of clearing accounts
		

		//  Invoice Price Variance 	difference
		BigDecimal ipv = cr.getAcctBalance().add(dr.getAcctBalance()).negate();
		if (ipv.signum() != 0)
		{
			FactLine pv = fact.createLine(null,
				m_pc.getAccount(ProductCost.ACCTTYPE_P_IPV, as),
				as.getC_Currency_ID(), ipv);
			pv.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
			pv.setC_Campaign_ID(m_invoiceLine.getC_Campaign_ID());
			pv.setC_Project_ID(m_invoiceLine.getC_Project_ID());
			pv.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
			pv.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
			pv.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
			pv.setUser1_ID(m_invoiceLine.getUser1_ID());
			pv.setUser2_ID(m_invoiceLine.getUser2_ID());
		}
		log.fine("IPV=" + ipv + "; Balance=" + fact.getSourceBalance());
		
// Elaine 2008/6/20		
/* Source move to MInvoice.createMatchInvCostDetail()
		//	Cost Detail Record - data from Expense/IncClearing (CR) record
		// MZ Goodwill
		// Create Cost Detail Matched Invoice using Total Amount and Total Qty based on InvoiceLine
		MMatchInv[] mInv = MMatchInv.getInvoiceLine(getCtx(), m_invoiceLine.getC_InvoiceLine_ID(), getTrxName());
		BigDecimal tQty = Env.ZERO;
		BigDecimal tAmt = Env.ZERO;
		for (int i = 0 ; i < mInv.length ; i++)
		{
			if (mInv[i].isPosted() && mInv[i].getM_MatchInv_ID() != get_ID())
			{
				tQty = tQty.add(mInv[i].getQty());
				multiplier = mInv[i].getQty()
					.divide(m_invoiceLine.getQtyInvoiced(), 12, BigDecimal.ROUND_HALF_UP).abs();
				tAmt = tAmt.add(m_invoiceLine.getLineNetAmt().multiply(multiplier));
			}
		}
		tAmt = tAmt.add(cr.getAcctBalance().negate()); //Invoice Price
		
		// 	Different currency
		MInvoice invoice = m_invoiceLine.getParent();
		if (as.getC_Currency_ID() != invoice.getC_Currency_ID())
		{
			tAmt = MConversionRate.convert(getCtx(), tAmt, 
				invoice.getC_Currency_ID(), as.getC_Currency_ID(),
				invoice.getDateAcct(), invoice.getC_ConversionType_ID(),
				invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
			if (tAmt == null)
			{
				p_Error = "AP Invoice not convertible - " + as.getName();
				return null;
			}
		}
		
		// set Qty to negative value when MovementType is Vendor Returns
		MInOut receipt = m_receiptLine.getParent();
		if (receipt.getMovementType().equals(MInOut.MOVEMENTTYPE_VendorReturns))
			tQty = tQty.add(getQty().negate()); //	Qty is set to negative value
		else
			tQty = tQty.add(getQty());
	
		// Set Total Amount and Total Quantity from Matched Invoice 
		MCostDetail.createInvoice(as, getAD_Org_ID(), 
				getM_Product_ID(), matchInv.getM_AttributeSetInstance_ID(),
				m_invoiceLine.getC_InvoiceLine_ID(), 0,		//	No cost element
				tAmt, tQty,	getDescription(), getTrxName());
		// end MZ
*/
		//  Update Costing
		updateProductInfo(as.getC_AcctSchema_ID(), 
			MAcctSchema.COSTINGMETHOD_StandardCosting.equals(as.getCostingMethod()));
		//
		facts.add(fact);
		
		/** Commitment release										****/
		if (as.isAccrual() && as.isCreatePOCommitment())
		{
			fact = Doc_Order.getCommitmentRelease(as, this, 
				getQty(), m_invoiceLine.getC_InvoiceLine_ID(), Env.ONE);
			if (fact == null)
				return null;
			facts.add(fact);
		}	//	Commitment
		
		return facts;
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

		// verify if org of receipt line is different from org of invoice line
		if (m_receiptLine != null && m_invoiceLine != null && m_receiptLine.getAD_Org_ID() != m_invoiceLine.getAD_Org_ID())
			return true;
		
		return false;
	}

	/**
	 *  Update Product Info (old).
	 *  - Costing (CostStandardCumQty, CostStandardCumAmt, CostAverageCumQty, CostAverageCumAmt)
	 *  @param C_AcctSchema_ID accounting schema
	 *  @param standardCosting true if std costing
	 *  @return true if updated
	 *  @deprecated old costing
	 */
	private boolean updateProductInfo (int C_AcctSchema_ID, boolean standardCosting)
	{
		log.fine("M_MatchInv_ID=" + get_ID());

		//  update Product Costing Qty/Amt
		//  requires existence of currency conversion !!
		StringBuffer sql = new StringBuffer (
			"UPDATE M_Product_Costing pc "
			+ "SET (CostStandardCumQty,CostStandardCumAmt, CostAverageCumQty,CostAverageCumAmt) = "
			+ "(SELECT pc.CostStandardCumQty + m.Qty,"
			+ "pc.CostStandardCumAmt + currencyConvert(il.PriceActual,i.C_Currency_ID,a.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID)*m.Qty, "
			+ "pc.CostAverageCumQty + m.Qty,"
			+ "pc.CostAverageCumAmt + currencyConvert(il.PriceActual,i.C_Currency_ID,a.C_Currency_ID,i.DateInvoiced,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID)*m.Qty "
			+ "FROM M_MatchInv m"
			+ " INNER JOIN C_InvoiceLine il ON (m.C_InvoiceLine_ID=il.C_InvoiceLine_ID)"
			+ " INNER JOIN C_Invoice i ON (il.C_Invoice_ID=i.C_Invoice_ID),"
			+ " C_AcctSchema a "
			+ "WHERE pc.C_AcctSchema_ID=a.C_AcctSchema_ID"
			+ " AND pc.M_Product_ID=m.M_Product_ID"
			+ " AND m.M_MatchInv_ID=").append(get_ID()).append(")"
			//
			+ "WHERE pc.C_AcctSchema_ID=").append(C_AcctSchema_ID).append(
			  " AND EXISTS (SELECT * FROM M_MatchInv m "
				+ "WHERE pc.M_Product_ID=m.M_Product_ID"
				+ " AND m.M_MatchInv_ID=").append(get_ID()).append(")"); 
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_Costing - Qty/Amt Updated #=" + no);

		//  Update Average Cost
		sql = new StringBuffer (
			"UPDATE M_Product_Costing "
			+ "SET CostAverage = CostAverageCumAmt/DECODE(CostAverageCumQty, 0,1, CostAverageCumQty) "
			+ "WHERE C_AcctSchema_ID=").append(C_AcctSchema_ID)
			.append(" AND M_Product_ID=").append(getM_Product_ID());
		no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_Costing - AvgCost Updated #=" + no);
		

		//  Update Current Cost
		if (!standardCosting)
		{
			sql = new StringBuffer (
				"UPDATE M_Product_Costing "
				+ "SET CurrentCostPrice = CostAverage "
				+ "WHERE C_AcctSchema_ID=").append(C_AcctSchema_ID)
				.append(" AND M_Product_ID=").append(getM_Product_ID());
			no = DB.executeUpdate(sql.toString(), getTrxName());
			log.fine("M_Product_Costing - CurrentCost Updated=" + no);
		}
		return true;
	}   //  updateProductInfo

}   //  Doc_MatchInv
