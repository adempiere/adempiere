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
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Write-off Open Invoices
 *	
 *  @author Jorg Janke
 *  @version $Id: InvoiceWriteOff.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class InvoiceWriteOff extends SvrProcess
{
	/**	Organization				*/
	private int			p_AD_Org_ID = 0;
	/**	BPartner				*/
	private int			p_C_BPartner_ID = 0;
	/** BPartner Group			*/
	private int			p_C_BP_Group_ID = 0;
	/**	Invoice					*/
	private int			p_C_Invoice_ID = 0;
	
	/** Max Amt					*/
	private BigDecimal	p_MaxInvWriteOffAmt = Env.ZERO;
	/** AP or AR				*/
	private String		p_APAR = "R";
	private static String	ONLY_AP = "P";
	private static String	ONLY_AR = "R";
	
	/** Invoice Date From		*/
	private Timestamp	p_DateInvoiced_From = null;
	/** Invoice Date To			*/
	private Timestamp	p_DateInvoiced_To = null;
	/** Accounting Date			*/
	private Timestamp	p_DateAcct = null;
	/** Create Payment			*/
	private boolean		p_CreatePayment = false;
	/** Bank Account			*/
	private int			p_C_BankAccount_ID = 0;
	/** Simulation				*/
	private boolean		p_IsSimulation = true;
	/** InvoiceCollectionType 	*/
	private String p_InvoiceCollectionType = null;
	/** Dunning Level 			*/
	private int p_C_DunningLevel_ID = 0 ;

	/**	Allocation Hdr			*/
	private MAllocationHdr	m_alloc = null;
	/**	Payment					*/
	private MPayment		m_payment = null;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		for (ProcessInfoParameter para : getParameter())
		{
			String name = para.getParameterName();
			if (para.getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = para.getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para.getParameterAsInt();
			else if (name.equals("C_BP_Group_ID"))
				p_C_BP_Group_ID = para.getParameterAsInt();
			else if (name.equals("C_Invoice_ID"))
				p_C_Invoice_ID = para.getParameterAsInt();
			else if (name.equals("InvoiceCollectionType"))
				p_InvoiceCollectionType = (String) para.getParameter();
			else if (name.equals("C_DunningLevel_ID"))
				p_C_DunningLevel_ID = para.getParameterAsInt();
			//
			else if (name.equals("MaxInvWriteOffAmt"))
				p_MaxInvWriteOffAmt = (BigDecimal)para.getParameter();
			else if (name.equals("APAR"))
				p_APAR = (String)para.getParameter();
			//
			else if (name.equals("DateInvoiced"))
			{
				p_DateInvoiced_From = (Timestamp)para.getParameter();
				p_DateInvoiced_To = (Timestamp)para.getParameter_To();
			}
			else if (name.equals("DateAcct"))
				p_DateAcct = (Timestamp)para.getParameter();
			//
			else if (name.equals("CreatePayment"))
				p_CreatePayment = "Y".equals(para.getParameter());
			else if (name.equals("C_BankAccount_ID"))
				p_C_BankAccount_ID = para.getParameterAsInt();
			//
			else if (name.equals("IsSimulation"))
				p_IsSimulation = "Y".equals(para.getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}	//	prepare

	/**
	 * 	Execute
	 *	@return message
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		List<Object> parameters = new ArrayList<Object>();
		log.info("C_BPartner_ID=" + p_C_BPartner_ID 
			+ ", C_BP_Group_ID=" + p_C_BP_Group_ID
			+ ", C_Invoice_ID=" + p_C_Invoice_ID
			+ ", InvoiceCollectionType=" + p_InvoiceCollectionType
			+ ", C_DunningLevel_ID=" + p_C_DunningLevel_ID
			+ "; APAR=" + p_APAR
			+ ", " + p_DateInvoiced_From + " - " + p_DateInvoiced_To
			+ "; CreatePayment=" + p_CreatePayment
			+ ", C_BankAccount_ID=" + p_C_BankAccount_ID);
		//
		
		if (p_C_BPartner_ID == 0 && p_C_Invoice_ID == 0 && p_C_BP_Group_ID == 0 && p_InvoiceCollectionType == null && p_C_DunningLevel_ID == 0)
			throw new AdempiereUserError ("@FillMandatory@ @C_Invoice_ID@ / @C_BPartner_ID@ / @C_BP_Group_ID@ / @InvoiceCollectionType@ / @C_DunningLevel_ID@");
		//
		if (p_CreatePayment && p_C_BankAccount_ID == 0)
			throw new AdempiereUserError ("@FillMandatory@  @C_BankAccount_ID@");
		//
		StringBuffer sql = new StringBuffer(
			"SELECT C_Invoice_ID,DocumentNo,DateInvoiced,"
			+ " C_Currency_ID,GrandTotal, invoiceOpen(C_Invoice_ID, 0) AS OpenAmt "
			+ "FROM C_Invoice WHERE ");
		
		if (p_AD_Org_ID != 0)
		{	
			sql.append("AD_Org_ID=? AND ");
			parameters.add(p_AD_Org_ID);
		}
		
		if (p_C_Invoice_ID != 0)
		{	
			sql.append("C_Invoice_ID=? AND ");
			parameters.add(p_C_Invoice_ID);
		}	
		else
		{
			if (p_C_BPartner_ID != 0)
			{	
				sql.append("C_BPartner_ID=? AND ");
				parameters.add(p_C_BPartner_ID);
			}
			else if (p_C_BP_Group_ID != 0)
			{
				sql.append("EXISTS (SELECT * FROM C_BPartner bp WHERE C_Invoice.C_BPartner_ID=bp.C_BPartner_ID AND bp.C_BP_Group_ID=?) AND ");
				parameters.add(p_C_BP_Group_ID);
			}
			//
			if (ONLY_AR.equals(p_APAR))
			{	
				sql.append("IsSOTrx=? AND ");
				parameters.add("Y");
			}
			else if (ONLY_AP.equals(p_APAR))
			{	
				sql.append("IsSOTrx=? AND ");
				parameters.add("N");
			}	
			
			if(p_InvoiceCollectionType != null)
			{	
				sql.append(" InvoiceCollectionType=? AND ");
				parameters.add(p_InvoiceCollectionType);
			}	
			
			if(p_C_DunningLevel_ID != 0)
			{	
				sql.append(" C_DunningLevel_ID=? AND ");
				parameters.add(p_C_DunningLevel_ID);
			}	
			//
			if (p_DateInvoiced_From != null && p_DateInvoiced_To != null)
				sql.append(" TRUNC(DateInvoiced, 'DD') BETWEEN ")
					.append(DB.TO_DATE(p_DateInvoiced_From, true))
					.append(" AND ")
					.append(DB.TO_DATE(p_DateInvoiced_To, true))
					.append(" AND ");
			else if (p_DateInvoiced_From != null)
				sql.append(" TRUNC(DateInvoiced, 'DD') >= ")
					.append(DB.TO_DATE(p_DateInvoiced_From, true))
					.append(" AND ");
			else if (p_DateInvoiced_To != null)
				sql.append("  TRUNC(DateInvoiced, 'DD') <= ")
					.append(DB.TO_DATE(p_DateInvoiced_To, true))
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
	 *	@param C_Invoice_ID invoice
	 *	@param DocumentNo doc no
	 *	@param DateInvoiced date
	 *	@param C_Currency_ID currency
	 *	@param OpenAmt open amt
	 *	@return true if written off
	 */
	private boolean writeOff (int C_Invoice_ID, String DocumentNo, Timestamp DateInvoiced, 
		int C_Currency_ID, BigDecimal OpenAmt)
	{
		//	Nothing to do
		if (OpenAmt == null || OpenAmt.signum() == 0)
			return false;
		if (OpenAmt.abs().compareTo(p_MaxInvWriteOffAmt) >= 0)
			return false;
		//
		if (p_IsSimulation)
		{
			addLog("@IsSimulation@");
			addLog(C_Invoice_ID, DateInvoiced, OpenAmt, DocumentNo);
			return true;
		}
		
		//	Invoice
		MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
		if (!invoice.isSOTrx())
			OpenAmt = OpenAmt.negate();
		
		//	Allocation
		if (m_alloc == null || C_Currency_ID != m_alloc.getC_Currency_ID())
		{
			processAllocation();
			m_alloc = new MAllocationHdr (getCtx(), true, 
				p_DateAcct, C_Currency_ID,
				getProcessInfo().getTitle() + " #" + getAD_PInstance_ID(), get_TrxName());
			m_alloc.setAD_Org_ID(invoice.getAD_Org_ID());
			if (!m_alloc.save())
			{
				log.log(Level.SEVERE, "Cannot create allocation header");
				return false;
			}
		}
		//	Payment
		if (p_CreatePayment 
			&& (m_payment == null 
				|| invoice.getC_BPartner_ID() != m_payment.getC_BPartner_ID()
				|| C_Currency_ID != m_payment.getC_Currency_ID()))
		{
			processPayment();
			m_payment = new MPayment(getCtx(), 0, get_TrxName());
			m_payment.setAD_Org_ID(invoice.getAD_Org_ID());
			m_payment.setC_BankAccount_ID(p_C_BankAccount_ID);
			m_payment.setTenderType(MPayment.TENDERTYPE_Check);
			m_payment.setDateTrx(p_DateAcct);
			m_payment.setDateAcct(p_DateAcct);
			m_payment.setDescription(getProcessInfo().getTitle() + " #" + getAD_PInstance_ID());
			m_payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
			m_payment.setIsReceipt(true);	//	payments are negative
			m_payment.setC_Currency_ID(C_Currency_ID);
			if (!m_payment.save())
			{
				log.log(Level.SEVERE, "Cannot create payment");
				return false;
			}
		}

		//	Line
		MAllocationLine aLine = null;
		if (p_CreatePayment)
		{
			aLine = new MAllocationLine (m_alloc, OpenAmt,
				Env.ZERO, Env.ZERO, Env.ZERO);
			m_payment.setPayAmt(m_payment.getPayAmt().add(OpenAmt));
			aLine.setC_Payment_ID(m_payment.getC_Payment_ID());
		}
		else
			aLine = new MAllocationLine (m_alloc, Env.ZERO, 
				Env.ZERO, OpenAmt, Env.ZERO);
		aLine.setC_Invoice_ID(C_Invoice_ID);
		if (aLine.save())
		{
			addLog(C_Invoice_ID, DateInvoiced, OpenAmt, DocumentNo);
			return true;
		}
		//	Error
		log.log(Level.SEVERE, "Cannot create allocation line for C_Invoice_ID=" + C_Invoice_ID);
		return false;
	}	//	writeOff
	
	/**
	 * 	Process Allocation
	 *	@return true if processed
	 */
	private boolean processAllocation()
	{
		if (m_alloc == null)
			return true;
		processPayment();
		//	Process It
		if (m_alloc.processIt(DocAction.ACTION_Complete) &&  m_alloc.save())
		{
			m_alloc = null;
			return true;
		}
		//
		m_alloc = null;
		return false;
	}	//	processAllocation

	/**
	 * 	Process Payment
	 *	@return true if processed
	 */
	private boolean processPayment()
	{
		if (m_payment == null)
			return true;
		//	Process It
		if (m_payment.processIt(DocAction.ACTION_Complete) &&  m_payment.save())
		{
			m_payment = null;
			return true;
		}
		//
		m_payment = null;
		return false;
	}	//	processPayment
	
}	//	InvoiceWriteOff
