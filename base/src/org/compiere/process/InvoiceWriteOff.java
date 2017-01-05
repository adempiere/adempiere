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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MAllocationHdr;
import org.compiere.model.MAllocationLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MPayment;
import org.compiere.model.MRole;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Write-off Open Invoices
 *	
 *  @author Jorg Janke
 *  @author victor.perez@e-evolution.com , www.e-evolution.com
 */
public class InvoiceWriteOff extends InvoiceWriteOffAbstract
{

	private static String	ONLY_AP = "P";
	private static String	ONLY_AR = "R";
	/**	Allocation Hdr			*/
	private MAllocationHdr allocation = null;
	/**	Payment					*/
	private MPayment payment = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		super.prepare();
	}	//	prepare

	/**
	 * 	Execute
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		List<Object> parameters = new ArrayList<Object>();
		log.info("C_BPartner_ID=" + getBusinessPartnerId()
			+ ", C_BP_Group_ID=" + getBusinessPartnerGroupId()
			+ ", C_Invoice_ID=" + getInvoiceId()
			+ ", InvoiceCollectionType=" + getCollectionStatus()
			+ ", C_DunningLevel_ID=" + getDunningLevelId()
			+ "; APAR=" + getAccountPayableAndReceivable()
			+ ", " + getDateInvoiced() + " - " + getDateInvoicedTo()
			+ "; CreatePayment=" + isCreatePayment()
			+ ", C_BankAccount_ID=" + getBankAccountId());
		
		StringBuffer sql = new StringBuffer(
			"SELECT C_Invoice_ID,DocumentNo,DateInvoiced,"
			+ " C_Currency_ID,GrandTotal, invoiceOpen(C_Invoice_ID, 0) AS OpenAmt "
			+ "FROM C_Invoice WHERE ");
		
		if (getOrganizationId() > 0)
		{	
			sql.append("AD_Org_ID=? AND ");
			parameters.add(getOrganizationId());
		}
		
		if (getInvoiceId() > 0)
		{	
			sql.append("C_Invoice_ID=? AND ");
			parameters.add(getInvoiceId());
		}	
		else
		{
			if (getBusinessPartnerId() > 0)
			{	
				sql.append("C_BPartner_ID=? AND ");
				parameters.add(getBusinessPartnerId());
			}
			else if (getBusinessPartnerGroupId() > 0)
			{
				sql.append("EXISTS (SELECT * FROM C_BPartner bp WHERE C_Invoice.C_BPartner_ID=bp.C_BPartner_ID AND bp.C_BP_Group_ID=?) AND ");
				parameters.add(getBusinessPartnerGroupId());
			}
			//
			if (ONLY_AR.equals(getAccountPayableAndReceivable()))
			{	
				sql.append("IsSOTrx=? AND ");
				parameters.add("Y");
			}
			else if (ONLY_AP.equals(getAccountPayableAndReceivable()))
			{	
				sql.append("IsSOTrx=? AND ");
				parameters.add("N");
			}	
			
			if(getCollectionStatus() != null)
			{	
				sql.append(" InvoiceCollectionType=? AND ");
				parameters.add(getCollectionStatus());
			}	
			
			if(getDunningLevelId() > 0)
			{	
				sql.append(" C_DunningLevel_ID=? AND ");
				parameters.add(getDunningLevelId());
			}	
			//
			if (getDateInvoiced() != null && getDateInvoicedTo() != null)
				sql.append(" TRUNC(DateInvoiced, 'DD') BETWEEN ")
					.append(DB.TO_DATE(getDateInvoiced(), true))
					.append(" AND ")
					.append(DB.TO_DATE(getDateInvoicedTo(), true))
					.append(" AND ");
			else if (getDateInvoiced() != null)
				sql.append(" TRUNC(DateInvoiced, 'DD') >= ")
					.append(DB.TO_DATE(getDateInvoiced(), true))
					.append(" AND ");
			else if (getDateInvoicedTo() != null)
				sql.append("  TRUNC(DateInvoiced, 'DD') <= ")
					.append(DB.TO_DATE(getDateInvoicedTo(), true))
					.append(" AND ");
		}
		sql.append(" IsPaid='N' ORDER BY C_Currency_ID, C_BPartner_ID, DateInvoiced");
		
		final String finalSql = MRole.getDefault(getCtx(), false).addAccessSQL( sql.toString(), "C_Invoice", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO );
		log.finer(finalSql);
		//
		int counter = 0;
		PreparedStatement pstmt = null;
		try
		{
			pstmt = DB.prepareStatement (finalSql, get_TrxName());
			DB.setParameters(pstmt, parameters);
			
			ResultSet rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				if (writeOff(rs.getInt(1), rs.getString(2), rs.getTimestamp(3),
					rs.getInt(4), rs.getBigDecimal(6)))
					counter++;
			}
			rs.close ();
			pstmt.close ();
			pstmt = null;
		} 
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
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
		//	final
		processPayment();
		processAllocation();
		return "#" + counter;
	}	//	doIt

	/**
	 * 	Write Off
	 *	@param invoiceId invoice
	 *	@param documentNo doc no
	 *	@param dateInvoiced date
	 *	@param currencyId currency
	 *	@param openAmt open amt
	 *	@return true if written off
	 */
	private boolean writeOff (int invoiceId, String documentNo, Timestamp dateInvoiced,
		int currencyId, BigDecimal openAmt)
	{
		//	Nothing to do
		if (openAmt == null || openAmt.signum() == 0)
			return false;
		if (openAmt.abs().compareTo(getMaximumWriteOffperInvoice()) >= 0)
			return false;
		//
		if (isSimulation())
		{
			addLog("@IsSimulation@");
			addLog(invoiceId, dateInvoiced, openAmt, documentNo);
			return true;
		}
		
		//	Invoice
		MInvoice invoice = new MInvoice(getCtx(), invoiceId, get_TrxName());
		if (!invoice.isSOTrx())
			openAmt = openAmt.negate();
		
		//	Allocation
		if (allocation == null || currencyId != allocation.getC_Currency_ID())
		{
			processAllocation();
			allocation = new MAllocationHdr (getCtx(), true, 
				getAccountDate(), currencyId,
				getProcessInfo().getTitle() + " #" + getAD_PInstance_ID(), get_TrxName());
			allocation.setAD_Org_ID(invoice.getAD_Org_ID());
			if (!allocation.save())
			{
				log.log(Level.SEVERE, "Cannot create allocation header");
				return false;
			}
		}
		//	Payment
		if (isCreatePayment()
			&& (payment == null 
				|| invoice.getC_BPartner_ID() != payment.getC_BPartner_ID()
				|| currencyId != payment.getC_Currency_ID()))
		{
			processPayment();
			payment = new MPayment(getCtx(), 0, get_TrxName());
			payment.setAD_Org_ID(invoice.getAD_Org_ID());
			payment.setC_BankAccount_ID(getBankAccountId());
			payment.setTenderType(MPayment.TENDERTYPE_Check);
			payment.setDateTrx(getAccountDate());
			payment.setDateAcct(getAccountDate());
			payment.setDescription(getProcessInfo().getTitle() + " #" + getAD_PInstance_ID());
			payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
			payment.setIsReceipt(true);	//	payments are negative
			payment.setC_Currency_ID(currencyId);
			if (!payment.save())
			{
				log.log(Level.SEVERE, "Cannot create payment");
				return false;
			}
		}

		//	Line
		MAllocationLine allocationLine = null;
		if (isCreatePayment())
		{
			allocationLine = new MAllocationLine (allocation, openAmt,
				Env.ZERO, Env.ZERO, Env.ZERO);
			payment.setPayAmt(payment.getPayAmt().add(openAmt));
			allocationLine.setC_Payment_ID(payment.getC_Payment_ID());
		}
		else
			allocationLine = new MAllocationLine (allocation, Env.ZERO,
				Env.ZERO, openAmt, Env.ZERO);
		allocationLine.setC_Invoice_ID(invoiceId);
		if (allocationLine.save())
		{
			addLog(invoiceId, dateInvoiced, openAmt, documentNo);
			return true;
		}
		//	Error
		log.log(Level.SEVERE, "Cannot create allocation line for C_Invoice_ID=" + invoiceId);
		return false;
	}	//	writeOff
	
	/**
	 * 	Process Allocation
	 *	@return true if processed
	 */
	private boolean processAllocation()
	{
		if (allocation == null)
			return true;
		processPayment();
		//	Process It
		if (allocation.processIt(DocAction.ACTION_Complete) &&  allocation.save())
		{
			allocation = null;
			return true;
		}
		//
		allocation = null;
		return false;
	}	//	processAllocation

	/**
	 * 	Process Payment
	 *	@return true if processed
	 */
	private boolean processPayment()
	{
		if (payment == null)
			return true;
		//	Process It
		if (payment.processIt(DocAction.ACTION_Complete) &&  payment.save())
		{
			payment = null;
			return true;
		}
		//
		payment = null;
		return false;
	}	//	processPayment
	
}	//	InvoiceWriteOff
