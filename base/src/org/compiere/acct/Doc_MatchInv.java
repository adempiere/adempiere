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
import org.compiere.model.MCostDetail;
import org.compiere.model.MCostType;
import org.compiere.model.MDocType;
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
		m_invoiceLine = new MInvoiceLine (getCtx(), C_InvoiceLine_ID, getTrxName());
		//		BP for NotInvoicedReceipts
		int C_BPartner_ID = m_invoiceLine.getParent().getC_BPartner_ID(); 
		setC_BPartner_ID(C_BPartner_ID);
		//
		int M_InOutLine_ID = matchInv.getM_InOutLine_ID();
		m_receiptLine = new MInOutLine (getCtx(), M_InOutLine_ID, getTrxName());		
		//
		m_pc = new ProductCost (Env.getCtx(), 
			getM_Product_ID(), matchInv.getM_AttributeSetInstance_ID(), getTrxName());
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
		
		MCostType ct = MCostType.get(as, getM_Product_ID(), getAD_Org_ID());
		
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
		if (!m_receiptLine.getM_Product().isStocked())
			return facts;
		
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
        dr.setM_Product_ID(m_receiptLine.getM_Product_ID());
		String documentBaseTypeReceipt = DB.getSQLValueString(m_receiptLine.get_TrxName() , "SELECT DocBaseType FROM C_DocType WHERE C_DocType_ID=?", m_receiptLine.getParent().getC_DocType_ID());
		BigDecimal quantityReceipt = MDocType.DOCBASETYPE_MaterialReceipt.equals(documentBaseTypeReceipt) ? getQty() : getQty().negate();
		dr.setQty(quantityReceipt);
		BigDecimal temp = dr.getAcctBalance();
		//	Set AmtAcctCr/Dr from Receipt (sets also Project)
		if (!dr.updateReverseLine (MInOut.Table_ID, 		//	Amt updated
			m_receiptLine.getM_InOut_ID(), m_receiptLine.getM_InOutLine_ID(), quantityReceipt , multiplier))
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
					BigDecimal costs = MCostDetail.getByDocLineMatchInv(m_invoiceLine,  
							m_receiptLine,as.getC_AcctSchema_ID(), as.getM_CostType_ID());

					int ACCTTYPE_P = 0;
					if(MCostType.COSTINGMETHOD_StandardCosting.equals(ct.getCostingMethod()) 
					|| MCostType.COSTINGMETHOD_AverageInvoice.equals(ct.getCostingMethod()))
						ACCTTYPE_P  = ProductCost.ACCTTYPE_P_IPV;
					else
						ACCTTYPE_P  = ProductCost.ACCTTYPE_P_Asset;
					BigDecimal diff = ipv.subtract(costs);
					MInvoice m_invoice = m_invoiceLine.getParent();
					int C_Currency_ID = m_invoice.getC_Currency_ID();
					FactLine pv = fact.createLine(null,
							m_pc.getAccount(ACCTTYPE_P, as),
							C_Currency_ID, ipv);
					pv.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
					pv.setC_Campaign_ID(m_invoiceLine.getC_Campaign_ID());
					pv.setC_Project_ID(m_invoiceLine.getC_Project_ID());
					pv.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
					pv.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
					pv.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
					pv.setUser1_ID(m_invoiceLine.getUser1_ID());
					pv.setUser2_ID(m_invoiceLine.getUser2_ID());
					pv.setUser3_ID(m_invoiceLine.getUser3_ID());
					pv.setUser4_ID(m_invoiceLine.getUser4_ID());
					if (diff.compareTo(Env.ZERO)!= 0 && MCostType.COSTINGMETHOD_AverageInvoice.equals(ct.getCostingMethod()))
					{
						FactLine diffline = fact.createLine(null,
								m_pc.getAccount(ProductCost.ACCTTYPE_P_CostAdjustment, as),
								C_Currency_ID, diff.negate());
						diffline.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
						diffline.setC_Project_ID(m_invoiceLine.getC_Project_ID());
						diffline.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
						diffline.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
						diffline.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
						diffline.setUser1_ID(m_invoiceLine.getUser1_ID());
						diffline.setUser2_ID(m_invoiceLine.getUser2_ID());
						diffline.setUser3_ID(m_invoiceLine.getUser3_ID());
						diffline.setUser4_ID(m_invoiceLine.getUser4_ID());
					}
				}
				log.fine("IPV=" + ipv + "; Balance=" + fact.getSourceBalance());
				facts.add(fact);
				return facts;
			}
            cr.setM_Product_ID(m_invoiceLine.getM_Product_ID());
			temp = cr.getAcctBalance();
			String documentBaseTypeInvoice = DB.getSQLValueString(m_invoiceLine.get_TrxName() , "SELECT DocBaseType FROM C_DocType WHERE C_DocType_ID=?", m_invoiceLine.getParent().getC_DocType_ID());
			BigDecimal quantityInvoice = MDocType.DOCBASETYPE_APInvoice.equals(documentBaseTypeInvoice) ?  getQty().negate() : getQty() ;
			cr.setQty(quantityInvoice);
			//	Set AmtAcctCr/Dr from Invoice (sets also Project)
			if (as.isAccrual() && !cr.updateReverseLine (MInvoice.Table_ID, 		//	Amt updated
				m_invoiceLine.getC_Invoice_ID(), m_invoiceLine.getC_InvoiceLine_ID(), quantityInvoice , multiplier))
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
		cr.setUser3_ID(m_invoiceLine.getUser3_ID());
		cr.setUser4_ID(m_invoiceLine.getUser4_ID());

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
		if (ipv.compareTo(Env.ZERO) == 0)
		{
			facts.add(fact);
			return facts;
		}
		if (!MCostType.COSTINGMETHOD_AverageInvoice.equals(ct.getCostingMethod()))
		{
			int ACCTTYPE_P = 0;
			if(MCostType.COSTINGMETHOD_StandardCosting.equals(ct.getCostingMethod())
			|| MCostType.COSTINGMETHOD_AverageInvoice.equals(ct.getCostingMethod()))
				ACCTTYPE_P  = ProductCost.ACCTTYPE_P_IPV;
			else
				ACCTTYPE_P  = ProductCost.ACCTTYPE_P_Asset;
			
			FactLine pv = fact.createLine(null,
				m_pc.getAccount(ACCTTYPE_P, as),
				as.getC_Currency_ID(), ipv);
			pv.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
			pv.setC_Campaign_ID(m_invoiceLine.getC_Campaign_ID());
			pv.setC_Project_ID(m_invoiceLine.getC_Project_ID());
			pv.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
			pv.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
			pv.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
			pv.setUser1_ID(m_invoiceLine.getUser1_ID());
			pv.setUser2_ID(m_invoiceLine.getUser2_ID());
			pv.setUser3_ID(m_invoiceLine.getUser3_ID());
			pv.setUser4_ID(m_invoiceLine.getUser4_ID());
			
			//
		}
		else
		{
			BigDecimal costs = MCostDetail.getByDocLineMatchInv(m_invoiceLine,  
					m_receiptLine,as.getC_AcctSchema_ID(), as.getM_CostType_ID());

			FactLine pv = fact.createLine(null,
				m_pc.getAccount(ProductCost.ACCTTYPE_P_Asset, as),
				as.getC_Currency_ID(), costs);
			if (pv != null) {
				pv.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
				pv.setC_Campaign_ID(m_invoiceLine.getC_Campaign_ID());
				pv.setC_Project_ID(m_invoiceLine.getC_Project_ID());
				pv.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
				pv.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
				pv.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
				pv.setUser1_ID(m_invoiceLine.getUser1_ID());
				pv.setUser2_ID(m_invoiceLine.getUser2_ID());
				pv.setUser3_ID(m_invoiceLine.getUser3_ID());
				pv.setUser4_ID(m_invoiceLine.getUser4_ID());
			}
			
			BigDecimal diff = ipv.subtract(costs);
			MInvoice m_invoice = m_invoiceLine.getParent();
			if (diff.compareTo(Env.ZERO)!= 0 )
			{
				FactLine diffline = fact.createLine(null,
						m_pc.getAccount(ProductCost.ACCTTYPE_P_CostAdjustment, as),
						as.getC_Currency_ID(), diff);
				if (diffline != null) {
					diffline.setC_Activity_ID(m_invoiceLine.getC_Activity_ID());
					diffline.setC_Project_ID(m_invoiceLine.getC_Project_ID());
					diffline.setC_ProjectPhase_ID(m_invoiceLine.getC_ProjectPhase_ID());
					diffline.setC_ProjectTask_ID(m_invoiceLine.getC_ProjectTask_ID());
					diffline.setC_UOM_ID(m_invoiceLine.getC_UOM_ID());
					diffline.setUser1_ID(m_invoiceLine.getUser1_ID());
					diffline.setUser2_ID(m_invoiceLine.getUser2_ID());
					diffline.setUser3_ID(m_invoiceLine.getUser3_ID());
					diffline.setUser4_ID(m_invoiceLine.getUser4_ID());
				}
			}
		}
		log.fine("IPV=" + ipv + "; Balance=" + fact.getSourceBalance());
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

}   //  Doc_MatchInv
