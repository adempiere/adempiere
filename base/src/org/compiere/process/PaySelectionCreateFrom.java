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
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionLine;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 *	Create Payment Selection Lines from AP Invoices
 *	
 *  @author Jorg Janke
 *  @version $Id: PaySelectionCreateFrom.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class PaySelectionCreateFrom extends SvrProcess
{
	/**	Only When Discount			*/
	private boolean 	p_OnlyDiscount = false;
	/** Only when Due				*/
	private boolean		p_OnlyDue = false;
	/** Include Disputed			*/
	private boolean		p_IncludeInDispute = false;
	/** Match Requirement			*/
	private String		p_MatchRequirement = "N";
	/** Payment Rule				*/
	private String		p_PaymentRule = null;
	/** BPartner					*/
	private int			p_C_BPartner_ID = 0;
	/** BPartner Group				*/
	private int			p_C_BP_Group_ID = 0;
	/**	Payment Selection			*/
	private int			p_C_PaySelection_ID = 0;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("OnlyDiscount"))
				p_OnlyDiscount = "Y".equals(para[i].getParameter());
			else if (name.equals("OnlyDue"))
				p_OnlyDue = "Y".equals(para[i].getParameter());
			else if (name.equals("IncludeInDispute"))
				p_IncludeInDispute = "Y".equals(para[i].getParameter());
			else if (name.equals("MatchRequirement"))
				p_MatchRequirement = (String)para[i].getParameter();
			else if (name.equals("PaymentRule"))
				p_PaymentRule = (String)para[i].getParameter();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_C_PaySelection_ID = getRecord_ID();
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message 
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info ("C_PaySelection_ID=" + p_C_PaySelection_ID
			+ ", OnlyDiscount=" + p_OnlyDiscount + ", OnlyDue=" + p_OnlyDue
			+ ", IncludeInDispute=" + p_IncludeInDispute
			+ ", MatchRequirement=" + p_MatchRequirement
			+ ", PaymentRule=" + p_PaymentRule
			+ ", C_BP_Group_ID=" + p_C_BP_Group_ID + ", C_BPartner_ID=" + p_C_BPartner_ID);
		
		MPaySelection psel = new MPaySelection (getCtx(), p_C_PaySelection_ID, get_TrxName());
		if (psel.get_ID() == 0)
			throw new IllegalArgumentException("Not found C_PaySelection_ID=" + p_C_PaySelection_ID);
		if (psel.isProcessed())
			throw new IllegalArgumentException("@Processed@");
	//	psel.getPayDate();

		String sql = "SELECT C_Invoice_ID,"
			//	Open
			+ " currencyConvert(invoiceOpen(i.C_Invoice_ID, 0)"
				+ ",i.C_Currency_ID, ?,?, i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID),"	//	##1/2 Currency_To,PayDate
			//	Discount
			+ " currencyConvert(paymentTermDiscount(i.GrandTotal,i.C_Currency_ID,i.C_PaymentTerm_ID,i.DateInvoiced, ?)"	//	##3 PayDate
				+ ",i.C_Currency_ID, ?,?,i.C_ConversionType_ID,i.AD_Client_ID,i.AD_Org_ID),"	//	##4/5 Currency_To,PayDate
			+ " PaymentRule, IsSOTrx "		//	4..6
			+ "FROM C_Invoice i "
			+ "WHERE IsSOTrx='N' AND IsPaid='N' AND DocStatus IN ('CO','CL')"
			+ " AND AD_Client_ID=?"				//	##6
			//	Existing Payments - Will reselect Invoice if prepared but not paid 
			+ " AND NOT EXISTS (SELECT * FROM C_PaySelectionLine psl"
			+                 " INNER JOIN C_PaySelectionCheck psc ON (psl.C_PaySelectionCheck_ID=psc.C_PaySelectionCheck_ID)"
			+                 " LEFT OUTER JOIN C_Payment pmt ON (pmt.C_Payment_ID=psc.C_Payment_ID)"
			+                 " WHERE i.C_Invoice_ID=psl.C_Invoice_ID AND psl.IsActive='Y'"
			+				  " AND (pmt.DocStatus IS NULL OR pmt.DocStatus NOT IN ('VO','RE')) )";
		//	Disputed
		if (!p_IncludeInDispute)
			sql += " AND i.IsInDispute='N'";
		//	PaymentRule (optional)
		if (p_PaymentRule != null)
			sql += " AND PaymentRule=?";		//	##
		//	OnlyDiscount
		if (p_OnlyDiscount)
		{
			if (p_OnlyDue)
				sql += " AND (";
			else
				sql += " AND ";
			sql += "paymentTermDiscount(invoiceOpen(C_Invoice_ID, 0), C_Currency_ID, C_PaymentTerm_ID, DateInvoiced, ?) > 0";	//	##
		}
		//	OnlyDue
		if (p_OnlyDue)
		{
			if (p_OnlyDiscount)
				sql += " OR ";
			else
				sql += " AND ";
			sql += "paymentTermDueDays(C_PaymentTerm_ID, DateInvoiced, ?) >= 0";	//	##
			if (p_OnlyDiscount)
				sql += ")";
		}
		//	Business Partner
		if (p_C_BPartner_ID != 0)
			sql += " AND C_BPartner_ID=?";	//	##
		//	Business Partner Group
		else if (p_C_BP_Group_ID != 0)
			sql += " AND EXISTS (SELECT * FROM C_BPartner bp "
				+ "WHERE bp.C_BPartner_ID=i.C_BPartner_ID AND bp.C_BP_Group_ID=?)";	//	##
		//	PO Matching Requiremnent
		if (p_MatchRequirement.equals("P") || p_MatchRequirement.equals("B"))
		{
			sql += " AND EXISTS (SELECT * FROM C_InvoiceLine il "
				+ "WHERE i.C_Invoice_ID=il.C_Invoice_ID"
				+ " AND QtyInvoiced=(SELECT SUM(Qty) FROM M_MatchPO m "
					+ "WHERE il.C_InvoiceLine_ID=m.C_InvoiceLine_ID))";
		}
		//	Receipt Matching Requiremnent
		if (p_MatchRequirement.equals("R") || p_MatchRequirement.equals("B"))
		{
			sql += " AND EXISTS (SELECT * FROM C_InvoiceLine il "
				+ "WHERE i.C_Invoice_ID=il.C_Invoice_ID"
				+ " AND QtyInvoiced=(SELECT SUM(Qty) FROM M_MatchInv m "
					+ "WHERE il.C_InvoiceLine_ID=m.C_InvoiceLine_ID))";
		}
	
		//
		int lines = 0;
		int C_CurrencyTo_ID = psel.getC_Currency_ID();
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (sql, get_TrxName());
			int index = 1;
			pstmt.setInt (index++, C_CurrencyTo_ID);
			pstmt.setTimestamp(index++, psel.getPayDate());
			//
			pstmt.setTimestamp(index++, psel.getPayDate());
			pstmt.setInt (index++, C_CurrencyTo_ID);
			pstmt.setTimestamp(index++, psel.getPayDate());
			//
			pstmt.setInt(index++, psel.getAD_Client_ID());
			if (p_PaymentRule != null)
				pstmt.setString(index++, p_PaymentRule);
			if (p_OnlyDiscount)
				pstmt.setTimestamp(index++, psel.getPayDate());
			if (p_OnlyDue)
				pstmt.setTimestamp(index++, psel.getPayDate());
			if (p_C_BPartner_ID != 0)
				pstmt.setInt (index++, p_C_BPartner_ID);
			else if (p_C_BP_Group_ID != 0)
				pstmt.setInt (index++, p_C_BP_Group_ID);
			//
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				int C_Invoice_ID = rs.getInt(1);
				BigDecimal PayAmt = rs.getBigDecimal(2);
				if (C_Invoice_ID == 0 || Env.ZERO.compareTo(PayAmt) == 0)
					continue;
				BigDecimal DiscountAmt = rs.getBigDecimal(3);
				String PaymentRule  = rs.getString(4);
				boolean isSOTrx = "Y".equals(rs.getString(5));
				//
				lines++;
				MPaySelectionLine pselLine = new MPaySelectionLine (psel, lines*10, PaymentRule);
				pselLine.setInvoice (C_Invoice_ID, isSOTrx,
					PayAmt, PayAmt.subtract(DiscountAmt), DiscountAmt);
				if (!pselLine.save())
				{
					pstmt.close();
					throw new IllegalStateException ("Cannot save MPaySelectionLine");
				}
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
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
		
		return "@C_PaySelectionLine_ID@  - #" + lines;
	}	//	doIt

}	//	PaySelectionCreateFrom
