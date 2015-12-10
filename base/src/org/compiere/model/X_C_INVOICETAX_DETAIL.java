/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

/** Generated Model for C_INVOICETAX_DETAIL
 *  @author Adempiere (generated) 
 *  @version R/3 - $Id$ */
public class X_C_INVOICETAX_DETAIL extends PO implements I_C_INVOICETAX_DETAIL, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150702L;

    /** Standard Constructor */
    public X_C_INVOICETAX_DETAIL (Properties ctx, int C_INVOICETAX_DETAIL_ID, String trxName)
    {
      super (ctx, C_INVOICETAX_DETAIL_ID, trxName);
      /** if (C_INVOICETAX_DETAIL_ID == 0)
        {
			setC_INVOICETAX_DETAIL_ID (0);
			setC_InvoiceLine_ID (0);
			setC_Tax_ID (0);
			setIsTaxIncluded (false);
			setProcessed (false);
			setTaxAmt (Env.ZERO);
			setTaxBaseAmt (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_C_INVOICETAX_DETAIL (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_INVOICETAX_DETAIL[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (I_C_BPartner)MTable.get(getCtx(), I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Charge getC_Charge() throws RuntimeException
    {
		return (I_C_Charge)MTable.get(getCtx(), I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set C_INVOICETAX_DETAIL ID.
		@param C_INVOICETAX_DETAIL_ID C_INVOICETAX_DETAIL ID	  */
	public void setC_INVOICETAX_DETAIL_ID (int C_INVOICETAX_DETAIL_ID)
	{
		if (C_INVOICETAX_DETAIL_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_INVOICETAX_DETAIL_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_INVOICETAX_DETAIL_ID, Integer.valueOf(C_INVOICETAX_DETAIL_ID));
	}

	/** Get C_INVOICETAX_DETAIL ID.
		@return C_INVOICETAX_DETAIL ID	  */
	public int getC_INVOICETAX_DETAIL_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_INVOICETAX_DETAIL_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException
    {
		return (I_C_InvoiceLine)MTable.get(getCtx(), I_C_InvoiceLine.Table_Name)
			.getPO(getC_InvoiceLine_ID(), get_TrxName());	}

	/** Set Invoice Line.
		@param C_InvoiceLine_ID 
		Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		if (C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
	}

	/** Get Invoice Line.
		@return Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (I_C_Invoice)MTable.get(getCtx(), I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Tax getC_Tax() throws RuntimeException
    {
		return (I_C_Tax)MTable.get(getCtx(), I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Price includes Tax.
		@param IsTaxIncluded 
		Tax is included in the price 
	  */
	public void setIsTaxIncluded (boolean IsTaxIncluded)
	{
		set_Value (COLUMNNAME_IsTaxIncluded, Boolean.valueOf(IsTaxIncluded));
	}

	/** Get Price includes Tax.
		@return Tax is included in the price 
	  */
	public boolean isTaxIncluded () 
	{
		Object oo = get_Value(COLUMNNAME_IsTaxIncluded);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_M_Product getM_Product() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set PARENT_TAXID.
		@param PARENT_TAXID PARENT_TAXID	  */
	public void setPARENT_TAXID (int PARENT_TAXID)
	{
		set_Value (COLUMNNAME_PARENT_TAXID, Integer.valueOf(PARENT_TAXID));
	}

	/** Get PARENT_TAXID.
		@return PARENT_TAXID	  */
	public int getPARENT_TAXID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PARENT_TAXID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set TAXRATE.
		@param TAXRATE TAXRATE	  */
	public void setTAXRATE (BigDecimal TAXRATE)
	{
		set_Value (COLUMNNAME_TAXRATE, TAXRATE);
	}

	/** Get TAXRATE.
		@return TAXRATE	  */
	public BigDecimal getTAXRATE () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TAXRATE);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax base Amount.
		@param TaxBaseAmt 
		Base for calculating the tax amount
	  */
	public void setTaxBaseAmt (BigDecimal TaxBaseAmt)
	{
		set_Value (COLUMNNAME_TaxBaseAmt, TaxBaseAmt);
	}

	/** Get Tax base Amount.
		@return Base for calculating the tax amount
	  */
	public BigDecimal getTaxBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxBaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
	
	protected boolean afterSave (boolean newRecord, boolean success) //AB 08-07-2015
	{
		if (!success || newRecord)
			return success;
		
		System.out.println("Modifying Rows of Tax Table, Manually");
		
		String sql="select nvl(sum(taxamt),0) from c_invoicetax_detail where parent_taxid="+getPARENT_TAXID()+" and  c_invoicetax_detail_id NOT IN ("+getC_INVOICETAX_DETAIL_ID()+") and c_invoice_id="+getC_Invoice_ID();
		BigDecimal TotaltaxAmt=DB.getSQLValueBD(null, sql);
		TotaltaxAmt=TotaltaxAmt.add(getTaxAmt());
		
		sql="update c_invoicetax set taxamt="+TotaltaxAmt+" where c_tax_id="+getPARENT_TAXID()+" and  c_invoice_id="+getC_Invoice_ID();
		DB.executeUpdate(sql, null);
		
		String Rtype;
		String sqlB="select rtype from c_invoice where c_invoice_id="+getC_Invoice_ID();
		Rtype=DB.getSQLValueString(null, sqlB);	
		
		if(Rtype.equals("L"))
		{
			sql = "UPDATE C_Invoice i "
					+ " SET ActualGT=TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID),"
						+ "GrandTotal=floor(TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID)),"
						+ "RAmount=((TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID))-(floor(TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID)))) "
						+ "WHERE C_Invoice_ID="+getC_Invoice_ID();
		}
		else if(Rtype.equals("U"))
		{
			sql = "UPDATE C_Invoice i "
					+ " SET ActualGT=TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID), "
						+ "GrandTotal=ceil(TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID)),"
						+ "RAmount=((ceil(TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID)))-(TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID))) "
						+ "WHERE C_Invoice_ID="+getC_Invoice_ID();
		}
		else if(Rtype.equals("N"))
		{
			sql = "UPDATE C_Invoice i "
					+ " SET ActualGT=TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID),"
						+ "GrandTotal=TotalLines+(SELECT COALESCE(SUM(TaxAmt),0) FROM C_InvoiceTax it WHERE i.C_Invoice_ID=it.C_Invoice_ID),"
						+ "RAmount= 0 "
						+ "WHERE C_Invoice_ID="+getC_Invoice_ID();
		}
		DB.executeUpdate(sql, null);

		return true;
	}
		
}