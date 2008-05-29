package org.eevolution.process;

/******************************************************************************
 * The contents of this file are subject to the   Compiere License  Version 1.1
 * ("License"); You may not use this file except in compliance with the License
 * You may obtain a copy of the License at http://www.compiere.org/license.html
 * Software distributed under the License is distributed on an  "AS IS"  basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * The Original Code is             Compiere  ERP & CRM Smart Business Solution
 * The Initial Developer of the Original Code is Jorg Janke  and ComPiere, Inc.
 * Portions created by Jorg Janke are Copyright (C) 1999-2003 Jorg Janke, parts
 * created by ComPiere are Copyright (C) ComPiere, Inc.;   All Rights Reserved.
* created by Victor Perez are Copyright (C) e-Evolution,SC. All Rights Reserved.
 * Contributor(s): Victor Perez
 *****************************************************************************/
//package org.eevolution.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.*;
import java.math.BigDecimal;


import org.compiere.model.*;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.report.FinBalance;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.model.*;
import org.compiere.util.*;


/**
 *  BOM lines explosion for print
 *
 *        @author Sergio Ramazzina,Victor Perez
 *        @version $Id: PrintBOM.java,v 1.2 2005/04/19 12:54:30 srama Exp $
 */
public class InvoiceCalculateTax extends SvrProcess {
	
	
    private static final Properties ctx = Env.getCtx();
    
    //private static final String AD_Client_ID = ctx.getProperty("#AD_Client_ID");
    //private static final String AD_Org_ID = ctx.getProperty("#AD_Org_ID");
    int p_C_Invoice_ID =  0;
    private MInvoiceTax[]	m_taxes;
	/**
	 * 	Financial Report Constructor
	 */
	public InvoiceCalculateTax()
	{
		super();
		log.info(" ");
	}	//	FinBalance


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
		else if (name.equals("C_Invoice_ID"))
			p_C_Invoice_ID = para[i].getParameterAsInt();
		}
    	
    } //        prepare

    /**
     *  Perform process.
     *  @return Message (clear text)
     *  @throws Exception if not successful
     */
    protected String doIt() throws Exception {
    	
    	calculateTaxTotal(p_C_Invoice_ID);       
        return "@ProcessOK@";
    } //        doIt
    
    /**
	 * 	Calculate Tax and Total
	 * 	@return true if calculated
	 */
	private boolean calculateTaxTotal(int C_Invoice_ID)	
	{
		Trx trx = Trx.get("retax", true);
		
		MInvoice inv = new MInvoice(Env.getCtx(),C_Invoice_ID,trx.getTrxName());
		updateBalance(inv, -1 , trx.getTrxName());
		log.fine("");
		//	Delete Taxes
		DB.executeUpdate("DELETE C_InvoiceTax WHERE C_Invoice_ID=" + inv.getC_Invoice_ID(), trx.getTrxName());
		m_taxes = null;
		
		//	Lines
		BigDecimal totalLines = Env.ZERO;
		ArrayList<Integer> taxList = new ArrayList<Integer>();
		MInvoiceLine[] lines = inv.getLines(false);
		for (int i = 0; i < lines.length; i++)
		{
			MInvoiceLine line = lines[i];
			/**	Sync ownership for SO
			if (isSOTrx() && line.getAD_Org_ID() != getAD_Org_ID())
			{
				line.setAD_Org_ID(getAD_Org_ID());
				line.save();
			}	**/
			Integer taxID = new Integer(line.getC_Tax_ID());
			if (!taxList.contains(taxID))
			{
				MInvoiceTax iTax = MInvoiceTax.get (line, inv.getPrecision(), 
					false, trx.getTrxName());	//	current Tax
				if (iTax != null)
				{
					iTax.setIsTaxIncluded(inv.isTaxIncluded());
					iTax.setAD_Org_ID(inv.getAD_Org_ID());
					if (!iTax.calculateTaxFromLines())
						return false;
					if (!iTax.save())
						return false;
					taxList.add(taxID);
				}
			}
			totalLines = totalLines.add(line.getLineNetAmt());
		}
		
		//	Taxes
		BigDecimal grandTotal = totalLines;
		MInvoiceTax[] taxes = inv.getTaxes(true);
		for (int i = 0; i < taxes.length; i++)
		{
			MInvoiceTax iTax = taxes[i];
			//MTax tax = iTax.getTax();
			MTax tax = MTax.get(Env.getCtx(), iTax.getC_Tax_ID());
			
			if (tax.isSummary())
			{
				MTax[] cTaxes = tax.getChildTaxes(false);	//	Multiple taxes
				for (int j = 0; j < cTaxes.length; j++)
				{
					MTax cTax = cTaxes[j];
					BigDecimal taxAmt = cTax.calculateTax(iTax.getTaxBaseAmt(), inv.isTaxIncluded(), inv.getPrecision());
					//
					MInvoiceTax newITax = new MInvoiceTax(Env.getCtx(), 0, trx.getTrxName());
					//newITax.setClientOrg(this);
					newITax.setC_Invoice_ID(inv.getC_Invoice_ID());
					newITax.setC_Tax_ID(cTax.getC_Tax_ID());
					//newITax.setPrecision(inv.getPrecision());
					newITax.setIsTaxIncluded(inv.isTaxIncluded());
					newITax.setTaxBaseAmt(iTax.getTaxBaseAmt());
					newITax.setTaxAmt(taxAmt);
					newITax.setAD_Org_ID(inv.getAD_Org_ID());
					if (!newITax.save())
						return false;
					//
					if (!inv.isTaxIncluded())
						grandTotal = grandTotal.add(taxAmt);
				}
				if (!iTax.delete(true, trx.getTrxName()))
					return false;
			}
			else
			{
				if (!inv.isTaxIncluded())
					grandTotal = grandTotal.add(iTax.getTaxAmt());
			}
		}		
		//
		inv.setTotalLines(totalLines);
		inv.setGrandTotal(grandTotal);
		if(!inv.save())
		{
		return false;
		}
		updateBalance(inv, 1 , trx.getTrxName());
		trx.commit();
		
		return true;
		
	}	//	calculateTaxTotal
	
	private boolean updateBalance(MInvoice i , int factor , String trx_name)
	{
        
		//	Update BP Statistics
		MBPartner bp = new MBPartner (Env.getCtx(), i.getC_BPartner_ID(), trx_name);
		//	Update total revenue and balance / credit limit (reversed on AllocationLine.processIt)
		BigDecimal invAmt = MConversionRate.convertBase(Env.getCtx(), i.getGrandTotal(true).multiply(new BigDecimal(factor)),	//	CM adjusted 
			i.getC_Currency_ID(), i.getDateAcct(), 0, getAD_Client_ID(), i.getAD_Org_ID());
		if (invAmt == null)
		{
			return false;
		}
		//	Total Balance
		BigDecimal newBalance = bp.getTotalOpenBalance(false);
		if (newBalance == null)
			newBalance = Env.ZERO;
		if (i.isSOTrx())
		{
			newBalance = newBalance.add(invAmt);
			//
			if (bp.getFirstSale() == null)
				bp.setFirstSale(i.getDateInvoiced());
			BigDecimal newLifeAmt = bp.getActualLifeTimeValue();
			if (newLifeAmt == null)
				newLifeAmt = invAmt;
			else
				newLifeAmt = newLifeAmt.add(invAmt);
			BigDecimal newCreditAmt = bp.getSO_CreditUsed();
			if (newCreditAmt == null)
				newCreditAmt = invAmt;
			else
				newCreditAmt = newCreditAmt.add(invAmt);
			//
			log.fine("GrandTotal=" + i.getGrandTotal(true) + "(" + invAmt 
				+ ") BP Life=" + bp.getActualLifeTimeValue() + "->" + newLifeAmt
				+ ", Credit=" + bp.getSO_CreditUsed() + "->" + newCreditAmt
				+ ", Balance=" + bp.getTotalOpenBalance(false) + " -> " + newBalance);
			bp.setActualLifeTimeValue(newLifeAmt);
			bp.setSO_CreditUsed(newCreditAmt);
		}	//	SO
		else
		{
			newBalance = newBalance.subtract(invAmt);
			log.fine("GrandTotal=" + i.getGrandTotal(true) + "(" + invAmt 
				+ ") Balance=" + bp.getTotalOpenBalance(false) + " -> " + newBalance);
		}
		bp.setTotalOpenBalance(newBalance);
		bp.setSOCreditStatus();
		if (!bp.save())
		{
			return false;
		}
		return true;
	}
	

	/**************************************************************************
	 * 	Test
	 * 	@param args ignored
	 */
	public static void main(String[] args)
	{
		org.compiere.Adempiere.startup(true);
		InvoiceCalculateTax cs = new InvoiceCalculateTax();
		try
		{
			cs.doIt();
		}
		catch (Exception e)
		{
			System.out.println("Error" + e.getMessage());
		}
		
	}	//	main

        
}
