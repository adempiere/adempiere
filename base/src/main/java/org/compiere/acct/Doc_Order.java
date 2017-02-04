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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClientInfo;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequisitionLine;
import org.compiere.model.MTax;
import org.compiere.model.ProductCost;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *  Post Order Documents.
 *  <pre>
 *  Table:              C_Order (259)
 *  Document Types:     SOO, POO
 *  </pre>
 *  @author Jorg Janke
 *  @version  $Id: Doc_Order.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class Doc_Order extends Doc
{
	/**
	 *  Constructor
	 * 	@param ass accounting schemata
	 * 	@param rs record
	 * 	@param trxName trx
	 */
	public Doc_Order (MAcctSchema[] ass, ResultSet rs, String trxName)
	{
		super (ass, MOrder.class, rs, null, trxName);
	}	//	Doc_Order

	/** Contained Optional Tax Lines    */
	private DocTax[]        m_taxes = null;
	/** Requisitions					*/
	private DocLine[]		m_requisitions = null;
	/** Order Currency Precision		*/
	private int				m_precision = -1;

	/**
	 *  Load Specific Document Details
	 *  @return error message or null
	 */
	protected String loadDocumentDetails ()
	{
		MOrder order = (MOrder)getPO();
		setDateDoc(order.getDateOrdered());
		setIsTaxIncluded(order.isTaxIncluded());
		//	Amounts
		setAmount(AMTTYPE_Gross, order.getGrandTotal());
		setAmount(AMTTYPE_Net, order.getTotalLines());
		setAmount(AMTTYPE_Charge, order.getChargeAmt());
				
		//	Contained Objects
		m_taxes = loadTaxes();
		p_lines = loadLines(order);
	//	log.fine( "Lines=" + p_lines.length + ", Taxes=" + m_taxes.length);
		return null;
	}   //  loadDocumentDetails


	/**
	 *	Load Invoice Line
	 *	@param order order
	 *  @return DocLine Array
	 */
	private DocLine[] loadLines(MOrder order)
	{
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		MOrderLine[] lines = order.getLines();
		for (int i = 0; i < lines.length; i++)
		{
			MOrderLine line = lines[i];
			DocLine docLine = new DocLine (line, this);
			BigDecimal Qty = line.getQtyOrdered();
			docLine.setQty(Qty, order.isSOTrx());
			//
			BigDecimal PriceActual = line.getPriceActual();
			BigDecimal PriceCost = null;
			if (getDocumentType().equals(DOCTYPE_POrder))	//	PO
				PriceCost = line.getPriceCost();
			BigDecimal LineNetAmt = null;
			if (PriceCost != null && PriceCost.signum() != 0)
				LineNetAmt = Qty.multiply(PriceCost);
			else
				LineNetAmt = line.getLineNetAmt();
			docLine.setAmount (LineNetAmt);	//	DR
			BigDecimal PriceList = line.getPriceList();
			int C_Tax_ID = docLine.getC_Tax_ID();
			//	Correct included Tax
			if (isTaxIncluded() && C_Tax_ID != 0)
			{
				MTax tax = MTax.get(getCtx(), C_Tax_ID);
				if (!tax.isZeroTax())
				{
					BigDecimal LineNetAmtTax = tax.calculateTax(LineNetAmt, true, getStdPrecision());
					log.fine("LineNetAmt=" + LineNetAmt + " - Tax=" + LineNetAmtTax);
					LineNetAmt = LineNetAmt.subtract(LineNetAmtTax);
					for (int t = 0; t < m_taxes.length; t++)
					{
						if (m_taxes[t].getC_Tax_ID() == C_Tax_ID)
						{
							m_taxes[t].addIncludedTax(LineNetAmtTax);
							break;
						}
					}
					BigDecimal PriceListTax = tax.calculateTax(PriceList, true, getStdPrecision());
					PriceList = PriceList.subtract(PriceListTax);
				}
			}	//	correct included Tax
			
			docLine.setAmount (LineNetAmt, PriceList, Qty);
			list.add(docLine);
		}

		//	Return Array
		DocLine[] dl = new DocLine[list.size()];
		list.toArray(dl);
		return dl;
	}	//	loadLines
	
	
	/**
	 * 	Load Requisitions
	 *	@return requisition lines of Order
	 */
	private DocLine[] loadRequisitions ()
	{
		MOrder order = (MOrder)getPO();
		MOrderLine[] oLines = order.getLines();
		HashMap<Integer,BigDecimal> qtys = new HashMap<Integer,BigDecimal>();
		for (int i = 0; i < oLines.length; i++)
		{
			MOrderLine line = oLines[i];
			qtys.put(new Integer(line.getC_OrderLine_ID()), line.getQtyOrdered());
		}
		//
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		String sql = "SELECT * FROM M_RequisitionLine rl "
			+ "WHERE EXISTS (SELECT * FROM C_Order o "
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID) "
				+ "WHERE ol.C_OrderLine_ID=rl.C_OrderLine_ID"
				+ " AND o.C_Order_ID=?) "
			+ "ORDER BY rl.C_OrderLine_ID";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, order.getC_Order_ID());
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MRequisitionLine line = new MRequisitionLine (getCtx(), rs, null);
				DocLine docLine = new DocLine (line, this);
				//	Quantity - not more then OrderLine
				//	Issue: Split of Requisition to multiple POs & different price
				Integer key = new Integer(line.getC_OrderLine_ID());
				BigDecimal maxQty = qtys.get(key);
				BigDecimal Qty = line.getQty().max(maxQty);
				if (Qty.signum() == 0)
					continue;
				docLine.setQty (Qty, false);
				qtys.put(key, maxQty.subtract(Qty));
				//
				BigDecimal PriceActual = line.getPriceActual();
				BigDecimal LineNetAmt = line.getLineNetAmt();
				if (line.getQty().compareTo(Qty) != 0)
					LineNetAmt = PriceActual.multiply(Qty);
				docLine.setAmount (LineNetAmt);	 // DR
				list.add (docLine);
			}
		}
		catch (Exception e)
		{
			log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		// Return Array
		DocLine[] dls = new DocLine[list.size ()];
		list.toArray (dls);
		return dls;
	}	// loadRequisitions

	
	/**
	 * 	Get Currency Precision
	 *	@return precision
	 */
	private int getStdPrecision()
	{
		if (m_precision == -1)
			m_precision = MCurrency.getStdPrecision(getCtx(), getC_Currency_ID());
		return m_precision;
	}	//	getPrecision

	/**
	 *	Load Invoice Taxes
	 *  @return DocTax Array
	 */
	private DocTax[] loadTaxes()
	{
		ArrayList<DocTax> list = new ArrayList<DocTax>();
		String sql = "SELECT it.C_Tax_ID, t.Name, t.Rate, it.TaxBaseAmt, it.TaxAmt, t.IsSalesTax "
			+ "FROM C_Tax t, C_OrderTax it "
			+ "WHERE t.C_Tax_ID=it.C_Tax_ID AND it.C_Order_ID=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, getTrxName());
			pstmt.setInt(1, get_ID());
			rs = pstmt.executeQuery();
			//
			while (rs.next())
			{
				int C_Tax_ID = rs.getInt(1);
				String name = rs.getString(2);
				BigDecimal rate = rs.getBigDecimal(3);
				BigDecimal taxBaseAmt = rs.getBigDecimal(4);
				BigDecimal amount = rs.getBigDecimal(5);
				boolean salesTax = "Y".equals(rs.getString(6));
				//
				DocTax taxLine = new DocTax(C_Tax_ID, name, rate, 
					taxBaseAmt, amount, salesTax);
				list.add(taxLine);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally {
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		//	Return Array
		DocTax[] tl = new DocTax[list.size()];
		list.toArray(tl);
		return tl;
	}	//	loadTaxes

	
	/**************************************************************************
	 *  Get Source Currency Balance - subtracts line and tax amounts from total - no rounding
	 *  @return positive amount, if total invoice is bigger than lines
	 */
	public BigDecimal getBalance()
	{
		BigDecimal retValue = Env.ZERO;
		StringBuffer sb = new StringBuffer (" [");
		//  Total
		retValue = retValue.add(getAmount(Doc.AMTTYPE_Gross));
		sb.append(getAmount(Doc.AMTTYPE_Gross));
		//  - Header Charge
		retValue = retValue.subtract(getAmount(Doc.AMTTYPE_Charge));
		sb.append("-").append(getAmount(Doc.AMTTYPE_Charge));
		//  - Tax
		if (m_taxes != null)
		{
			for (int i = 0; i < m_taxes.length; i++)
			{
				retValue = retValue.subtract(m_taxes[i].getAmount());
				sb.append("-").append(m_taxes[i].getAmount());
			}
		}
		//  - Lines
		if (p_lines != null)
		{
			for (int i = 0; i < p_lines.length; i++)
			{
				retValue = retValue.subtract(p_lines[i].getAmtSource());
				sb.append("-").append(p_lines[i].getAmtSource());
			}
			sb.append("]");
		}
		//
		if (retValue.signum() != 0		//	Sum of Cost(vs. Price) in lines may not add up 
			&& getDocumentType().equals(DOCTYPE_POrder))	//	PO
		{
			log.fine(toString() + " Balance=" + retValue + sb.toString() + " (ignored)");
			retValue = Env.ZERO;
		}
		else
			log.fine(toString() + " Balance=" + retValue + sb.toString());
		return retValue;
	}   //  getBalance

	
	/*************************************************************************
	 *  Create Facts (the accounting logic) for
	 *  SOO, POO.
	 *  <pre>
	 *  Reservation (release)
	 * 		Expense			DR
	 * 		Offset					CR
	 *  Commitment
	 *  (to be released by Invoice Matching)
	 * 		Expense					CR
	 * 		Offset			DR
	 *  </pre>
	 *  @param as accounting schema
	 *  @return Fact
	 */
	public ArrayList<Fact> createFacts (MAcctSchema as)
	{
		ArrayList<Fact> facts = new ArrayList<Fact>();
		//  Purchase Order
		if (getDocumentType().equals(DOCTYPE_POrder))
		{
			updateProductPO(as);
			updateProductInfo(as.getC_AcctSchema_ID());

			BigDecimal grossAmt = getAmount(Doc.AMTTYPE_Gross);

			//  Commitment
			FactLine fl = null;
			if (as.isCreatePOCommitment())
			{
				Fact fact = new Fact(this, as, Fact.POST_Commitment);
				BigDecimal total = Env.ZERO;
				for (int i = 0; i < p_lines.length; i++)
				{
					DocLine line = p_lines[i];
					BigDecimal cost = line.getAmtSource();
					total = total.add(cost);

					//	Account
					MAccount expense = line.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
					fl = fact.createLine (line, expense,
						getC_Currency_ID(), cost, null);
				}
				//	Offset
				MAccount offset = getAccount(ACCTTYPE_CommitmentOffset, as);
				if (offset == null)
				{
					p_Error = "@NotFound@ @CommitmentOffset_Acct@";
					log.log(Level.SEVERE, p_Error);
					return null;
				}
				fact.createLine (null, offset,
					getC_Currency_ID(), null, total);
				//
				facts.add(fact);
			}
			
			//  Reverse Reservation
			if (as.isCreateReservation())
			{
				Fact fact = new Fact(this, as, Fact.POST_Reservation);
				BigDecimal total = Env.ZERO;
				if (m_requisitions == null)
					m_requisitions = loadRequisitions();
				for (int i = 0; i < m_requisitions.length; i++)
				{
					DocLine line = m_requisitions[i];
					BigDecimal cost = line.getAmtSource();
					total = total.add(cost);

					//	Account
					MAccount expense = line.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
					fl = fact.createLine (line, expense,
						getC_Currency_ID(), null, cost);
				}
				//	Offset
				if (m_requisitions.length > 0)
				{
					MAccount offset = getAccount(ACCTTYPE_CommitmentOffset, as);
					if (offset == null)
					{
						p_Error = "@NotFound@ @CommitmentOffset_Acct@";
						log.log(Level.SEVERE, p_Error);
						return null;
					}
					fact.createLine (null, offset,
						getC_Currency_ID(), total, null);
				}
				//
				facts.add(fact);
			}	//	reservations
		}
		//	SO
		else if (getDocumentType().equals(DOCTYPE_SOrder))
		{
			//  Commitment
			FactLine fl = null;
			if (as.isCreateSOCommitment())
			{
				Fact fact = new Fact(this, as, Fact.POST_Commitment);
				BigDecimal total = Env.ZERO;
				for (int i = 0; i < p_lines.length; i++)
				{
					DocLine line = p_lines[i];
					BigDecimal cost = line.getAmtSource();
					total = total.add(cost);

					//	Account
					MAccount revenue = line.getAccount(ProductCost.ACCTTYPE_P_Revenue, as);
					fl = fact.createLine (line, revenue,
						getC_Currency_ID(), null, cost);
				}
				//	Offset
				MAccount offset = getAccount(ACCTTYPE_CommitmentOffsetSales, as);
				if (offset == null)
				{
					p_Error = "@NotFound@ @CommitmentOffsetSales_Acct@";
					log.log(Level.SEVERE, p_Error);
					return null;
				}
				fact.createLine (null, offset,
					getC_Currency_ID(), total, null);
				//
				facts.add(fact);
			}
			
		}
		return facts;
	}   //  createFact

	
	/**
	 * 	Update ProductPO PriceLastPO
	 *	@param as accounting schema
	 */
	private void updateProductPO(MAcctSchema as)
	{
		MClientInfo ci = MClientInfo.get(getCtx(), as.getAD_Client_ID());
		if (ci.getC_AcctSchema1_ID() != as.getC_AcctSchema_ID())
			return;

		StringBuffer sql = new StringBuffer (
			"UPDATE M_Product_PO po "
			+ "SET PriceLastPO = (SELECT currencyConvert(ol.PriceActual,ol.C_Currency_ID,po.C_Currency_ID,o.DateOrdered,o.C_ConversionType_ID,o.AD_Client_ID,o.AD_Org_ID) "
			+ "FROM C_Order o, C_OrderLine ol "
			+ "WHERE o.C_Order_ID=ol.C_Order_ID"
			+ " AND po.M_Product_ID=ol.M_Product_ID AND po.C_BPartner_ID=o.C_BPartner_ID ");
			//jz + " AND ROWNUM=1 AND o.C_Order_ID=").append(get_ID()).append(") ")
			if (DB.isOracle()) //jz
			{
				sql.append(" AND ROWNUM=1 ");
			}
			else 
				sql.append(" AND ol.C_OrderLine_ID = (SELECT MIN(ol1.C_OrderLine_ID) "
						+ "FROM C_Order o1, C_OrderLine ol1 "
						+ "WHERE o1.C_Order_ID=ol1.C_Order_ID"
						+ " AND po.M_Product_ID=ol1.M_Product_ID AND po.C_BPartner_ID=o1.C_BPartner_ID")
						.append("  AND o1.C_Order_ID=").append(get_ID()).append(") ");
			sql.append("  AND o.C_Order_ID=").append(get_ID()).append(") ")
			.append("WHERE EXISTS (SELECT * "
			+ "FROM C_Order o, C_OrderLine ol "
			+ "WHERE o.C_Order_ID=ol.C_Order_ID"
			+ " AND po.M_Product_ID=ol.M_Product_ID AND po.C_BPartner_ID=o.C_BPartner_ID"
			+ " AND o.C_Order_ID=").append(get_ID()).append(")");
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("Updated=" + no);
	}	//	updateProductPO
	
	
	/**
	 * 	Get Commitments
	 * 	@param doc document
	 * 	@param maxQty Qty invoiced/matched
	 * 	@param C_InvoiceLine_ID invoice line
	 *	@return commitments (order lines)
	 */
	protected static DocLine[] getCommitments(Doc doc, BigDecimal maxQty, int C_InvoiceLine_ID)
	{
		int precision = -1;
		//
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		String sql = "SELECT * FROM C_OrderLine ol "
			+ "WHERE EXISTS "
				+ "(SELECT * FROM C_InvoiceLine il "
				+ "WHERE il.C_OrderLine_ID=ol.C_OrderLine_ID"
				+ " AND il.C_InvoiceLine_ID=?)"
			+ " OR EXISTS "
				+ "(SELECT * FROM M_MatchPO po "
				+ "WHERE po.C_OrderLine_ID=ol.C_OrderLine_ID"
				+ " AND po.C_InvoiceLine_ID=?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, C_InvoiceLine_ID);
			pstmt.setInt (2, C_InvoiceLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				if (maxQty.signum() == 0)
					continue;
				MOrderLine line = new MOrderLine (doc.getCtx(), rs, null);
				DocLine docLine = new DocLine (line, doc);
				//	Currency
				if (precision == -1)
				{
					doc.setC_Currency_ID(docLine.getC_Currency_ID());
					precision = MCurrency.getStdPrecision(doc.getCtx(), docLine.getC_Currency_ID());
				}
				//	Qty
				BigDecimal Qty = line.getQtyOrdered().max(maxQty);
				docLine.setQty(Qty, false);
				//
				BigDecimal PriceActual = line.getPriceActual();
				BigDecimal PriceCost = line.getPriceCost();
				BigDecimal LineNetAmt = null;
				if (PriceCost != null && PriceCost.signum() != 0)
					LineNetAmt = Qty.multiply(PriceCost);
				else if (Qty.equals(maxQty))
					LineNetAmt = line.getLineNetAmt();
				else
					LineNetAmt = Qty.multiply(PriceActual);
				maxQty = maxQty.subtract(Qty);
				
				docLine.setAmount (LineNetAmt);	//	DR
				BigDecimal PriceList = line.getPriceList();
				int C_Tax_ID = docLine.getC_Tax_ID();
				//	Correct included Tax
				if (C_Tax_ID != 0 && line.getParent().isTaxIncluded())
				{
					MTax tax = MTax.get(doc.getCtx(), C_Tax_ID);
					if (!tax.isZeroTax())
					{
						BigDecimal LineNetAmtTax = tax.calculateTax(LineNetAmt, true, precision);
						s_log.fine("LineNetAmt=" + LineNetAmt + " - Tax=" + LineNetAmtTax);
						LineNetAmt = LineNetAmt.subtract(LineNetAmtTax);
						BigDecimal PriceListTax = tax.calculateTax(PriceList, true, precision);
						PriceList = PriceList.subtract(PriceListTax);
					}
				}	//	correct included Tax
				
				docLine.setAmount (LineNetAmt, PriceList, Qty);
				list.add(docLine);
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		//	Return Array
		DocLine[] dl = new DocLine[list.size()];
		list.toArray(dl);
		return dl;
	}	//	getCommitments

	/**
	 * 	Get Commitment Release.
	 * 	Called from MatchInv for accrual and Allocation for Cash Based
	 *	@param as accounting schema
	 *	@param doc doc
	 *	@param Qty qty invoiced/matched
	 *	@param C_InvoiceLine_ID line
	 *	@param multiplier 1 for accrual
	 *	@return Fact
	 */
	protected static Fact getCommitmentRelease(MAcctSchema as, Doc doc, 
		BigDecimal Qty, int C_InvoiceLine_ID, BigDecimal multiplier)
	{
		Fact fact = new Fact(doc, as, Fact.POST_Commitment);
		DocLine[] commitments = Doc_Order.getCommitments(doc, Qty, 
				C_InvoiceLine_ID);
		
		BigDecimal total = Env.ZERO;
		FactLine fl = null;
		int C_Currency_ID = -1;
		for (int i = 0; i < commitments.length; i++)
		{
			DocLine line = commitments[i];
			if (C_Currency_ID == -1)
				C_Currency_ID = line.getC_Currency_ID();
			else if (C_Currency_ID != line.getC_Currency_ID())
			{
				doc.p_Error = "Different Currencies of Order Lines";
				s_log.log(Level.SEVERE, doc.p_Error);
				return null;
			}
			BigDecimal cost = line.getAmtSource().multiply(multiplier);
			total = total.add(cost);

			//	Account
			MAccount expense = line.getAccount(ProductCost.ACCTTYPE_P_Expense, as);
			fl = fact.createLine (line, expense,
				C_Currency_ID, null, cost);
		}
		//	Offset
		MAccount offset = doc.getAccount(ACCTTYPE_CommitmentOffset, as);
		if (offset == null)
		{
			doc.p_Error = "@NotFound@ @CommitmentOffset_Acct@";
			s_log.log(Level.SEVERE, doc.p_Error);
			return null;
		}
		fact.createLine (null, offset,
			C_Currency_ID, total, null);
		return fact;
	}	//	getCommitmentRelease
	
	/**
	 * 	Get Commitments Sales
	 * 	@param doc document
	 * 	@param maxQty Qty invoiced/matched
	 * 	@param C_OrderLine_ID invoice line
	 *	@return commitments (order lines)
	 */
	protected static DocLine[] getCommitmentsSales(Doc doc, BigDecimal maxQty, int M_InOutLine_ID)
	{
		int precision = -1;
		//
		ArrayList<DocLine> list = new ArrayList<DocLine>();
		String sql = "SELECT * FROM C_OrderLine ol "
			+ "WHERE EXISTS "
				+ "(SELECT * FROM M_InOutLine il "
				+ "WHERE il.C_OrderLine_ID=ol.C_OrderLine_ID"
				+ " AND il.M_InOutLine_ID=?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);
			pstmt.setInt (1, M_InOutLine_ID);
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				if (maxQty.signum() == 0)
					continue;
				MOrderLine line = new MOrderLine (doc.getCtx(), rs, null);
				DocLine docLine = new DocLine (line, doc);
				//	Currency
				if (precision == -1)
				{
					doc.setC_Currency_ID(docLine.getC_Currency_ID());
					precision = MCurrency.getStdPrecision(doc.getCtx(), docLine.getC_Currency_ID());
				}
				//	Qty
				BigDecimal Qty = line.getQtyOrdered().max(maxQty);
				docLine.setQty(Qty, false);
				//
				BigDecimal PriceActual = line.getPriceActual();
				BigDecimal PriceCost = line.getPriceCost();
				BigDecimal LineNetAmt = null;
				if (PriceCost != null && PriceCost.signum() != 0)
					LineNetAmt = Qty.multiply(PriceCost);
				else if (Qty.equals(maxQty))
					LineNetAmt = line.getLineNetAmt();
				else
					LineNetAmt = Qty.multiply(PriceActual);
				maxQty = maxQty.subtract(Qty);
				
				docLine.setAmount (LineNetAmt);	//	DR
				BigDecimal PriceList = line.getPriceList();
				int C_Tax_ID = docLine.getC_Tax_ID();
				//	Correct included Tax
				if (C_Tax_ID != 0 && line.getParent().isTaxIncluded())
				{
					MTax tax = MTax.get(doc.getCtx(), C_Tax_ID);
					if (!tax.isZeroTax())
					{
						BigDecimal LineNetAmtTax = tax.calculateTax(LineNetAmt, true, precision);
						s_log.fine("LineNetAmt=" + LineNetAmt + " - Tax=" + LineNetAmtTax);
						LineNetAmt = LineNetAmt.subtract(LineNetAmtTax);
						BigDecimal PriceListTax = tax.calculateTax(PriceList, true, precision);
						PriceList = PriceList.subtract(PriceListTax);
					}
				}	//	correct included Tax
				
				docLine.setAmount (LineNetAmt, PriceList, Qty);
				list.add(docLine);
			}
		}
		catch (Exception e)
		{
			s_log.log (Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		//	Return Array
		DocLine[] dl = new DocLine[list.size()];
		list.toArray(dl);
		return dl;
	}	//	getCommitmentsSales

	/**
	 * 	Get Commitment Sales Release.
	 * 	Called from InOut
	 *	@param as accounting schema
	 *	@param doc doc
	 *	@param Qty qty invoiced/matched
	 *	@param C_OrderLine_ID line
	 *	@param multiplier 1 for accrual
	 *	@return Fact
	 */
	protected static Fact getCommitmentSalesRelease(MAcctSchema as, Doc doc, 
		BigDecimal Qty, int M_InOutLine_ID, BigDecimal multiplier)
	{
		Fact fact = new Fact(doc, as, Fact.POST_Commitment);
		DocLine[] commitments = Doc_Order.getCommitmentsSales(doc, Qty, 
				M_InOutLine_ID);
		
		BigDecimal total = Env.ZERO;
		FactLine fl = null;
		int C_Currency_ID = -1;
		for (int i = 0; i < commitments.length; i++)
		{
			DocLine line = commitments[i];
			if (C_Currency_ID == -1)
				C_Currency_ID = line.getC_Currency_ID();
			else if (C_Currency_ID != line.getC_Currency_ID())
			{
				doc.p_Error = "Different Currencies of Order Lines";
				s_log.log(Level.SEVERE, doc.p_Error);
				return null;
			}
			BigDecimal cost = line.getAmtSource().multiply(multiplier);
			total = total.add(cost);

			//	Account
			MAccount revenue = line.getAccount(ProductCost.ACCTTYPE_P_Revenue, as);
			fl = fact.createLine (line, revenue,
				C_Currency_ID, cost, null);
		}
		//	Offset
		MAccount offset = doc.getAccount(ACCTTYPE_CommitmentOffsetSales, as);
		if (offset == null)
		{
			doc.p_Error = "@NotFound@ @CommitmentOffsetSales_Acct@";
			s_log.log(Level.SEVERE, doc.p_Error);
			return null;
		}
		fact.createLine (null, offset,
			C_Currency_ID, null, total);
		return fact;
	}	//	getCommitmentSalesRelease
	
	/**************************************************************************
	 *  Update Product Info (old)
	 *  - Costing (PriceLastPO)
	 *  - PO (PriceLastPO)
	 *  @param C_AcctSchema_ID accounting schema
	 *  @deprecated old costing
	 */
	private void updateProductInfo (int C_AcctSchema_ID)
	{
		log.fine("C_Order_ID=" + get_ID());

		/** @todo Last.. would need to compare document/last updated date
		 *  would need to maintain LastPriceUpdateDate on _PO and _Costing */

		//  update Product Costing
		//  requires existence of currency conversion !!
		//  if there are multiple lines of the same product last price uses first
		StringBuffer sql = new StringBuffer (
			"UPDATE M_Product_Costing pc "
			+ "SET PriceLastPO = "
			+ "(SELECT currencyConvert(ol.PriceActual,ol.C_Currency_ID,a.C_Currency_ID,o.DateOrdered,o.C_ConversionType_ID,o.AD_Client_ID,o.AD_Org_ID) "
			+ "FROM C_Order o, C_OrderLine ol, C_AcctSchema a "
			+ "WHERE o.C_Order_ID=ol.C_Order_ID"
			+ " AND pc.M_Product_ID=ol.M_Product_ID AND pc.C_AcctSchema_ID=a.C_AcctSchema_ID ");
			if (DB.isOracle()) //jz
			{
				sql.append(" AND ROWNUM=1 ");
			}
			else 
				sql.append(" AND ol.C_OrderLine_ID = (SELECT MIN(ol1.C_OrderLine_ID) "
						+ "FROM C_Order o1, C_OrderLine ol1 "
						+ "WHERE o1.C_Order_ID=ol1.C_Order_ID"
						+ " AND pc.M_Product_ID=ol1.M_Product_ID ")
						.append("  AND o1.C_Order_ID=").append(get_ID()).append(") ");
			sql.append(" AND pc.C_AcctSchema_ID=").append(C_AcctSchema_ID).append(" AND o.C_Order_ID=")
			.append(get_ID()).append(") ")
			.append("WHERE EXISTS (SELECT * "
			+ "FROM C_Order o, C_OrderLine ol, C_AcctSchema a "
			+ "WHERE o.C_Order_ID=ol.C_Order_ID"
			+ " AND pc.M_Product_ID=ol.M_Product_ID AND pc.C_AcctSchema_ID=a.C_AcctSchema_ID"
			+ " AND pc.C_AcctSchema_ID=").append(C_AcctSchema_ID).append(" AND o.C_Order_ID=")
			.append(get_ID()).append(")");
		int no = DB.executeUpdate(sql.toString(), getTrxName());
		log.fine("M_Product_Costing - Updated=" + no);
	}   //  updateProductInfo

}   //  Doc_Order