/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for T_InvoiceGL
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_T_InvoiceGL extends PO
{
/** Standard Constructor
@param ctx context
@param T_InvoiceGL_ID id
@param trxName transaction
*/
public X_T_InvoiceGL (Properties ctx, int T_InvoiceGL_ID, String trxName)
{
super (ctx, T_InvoiceGL_ID, trxName);
/** if (T_InvoiceGL_ID == 0)
{
setAD_PInstance_ID (0);
setAmtAcctBalance (Env.ZERO);
setAmtRevalCr (Env.ZERO);
setAmtRevalCrDiff (Env.ZERO);
setAmtRevalDr (Env.ZERO);
setAmtRevalDrDiff (Env.ZERO);
setAmtSourceBalance (Env.ZERO);
setC_ConversionTypeReval_ID (0);
setC_Invoice_ID (0);
setDateReval (new Timestamp(System.currentTimeMillis()));
setFact_Acct_ID (0);
setGrandTotal (Env.ZERO);
setIsAllCurrencies (false);
setOpenAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_T_InvoiceGL (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=803 */
public static final int Table_ID=MTable.getTable_ID("T_InvoiceGL");

/** TableName=T_InvoiceGL */
public static final String Table_Name="T_InvoiceGL";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"T_InvoiceGL");

protected BigDecimal accessLevel = new BigDecimal(3);
/** AccessLevel
@return 3 - Client - Org 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_T_InvoiceGL[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Instance.
@param AD_PInstance_ID Instance of the process */
public void setAD_PInstance_ID (int AD_PInstance_ID)
{
if (AD_PInstance_ID < 1) throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
set_Value ("AD_PInstance_ID", Integer.valueOf(AD_PInstance_ID));
}
/** Get Process Instance.
@return Instance of the process */
public int getAD_PInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_PInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** APAR AD_Reference_ID=332 */
public static final int APAR_AD_Reference_ID=332;
/** Receivables & Payables = A */
public static final String APAR_ReceivablesPayables = "A";
/** Payables only = P */
public static final String APAR_PayablesOnly = "P";
/** Receivables only = R */
public static final String APAR_ReceivablesOnly = "R";
/** Set AP - AR.
@param APAR Include Receivables and/or Payables transactions */
public void setAPAR (String APAR)
{
if (APAR == null || APAR.equals("A") || APAR.equals("P") || APAR.equals("R"));
 else throw new IllegalArgumentException ("APAR Invalid value - " + APAR + " - Reference_ID=332 - A - P - R");
if (APAR != null && APAR.length() > 1)
{
log.warning("Length > 1 - truncated");
APAR = APAR.substring(0,0);
}
set_Value ("APAR", APAR);
}
/** Get AP - AR.
@return Include Receivables and/or Payables transactions */
public String getAPAR() 
{
return (String)get_Value("APAR");
}
/** Set Accounted Balance.
@param AmtAcctBalance Accounted Balance Amount */
public void setAmtAcctBalance (BigDecimal AmtAcctBalance)
{
if (AmtAcctBalance == null) throw new IllegalArgumentException ("AmtAcctBalance is mandatory.");
set_Value ("AmtAcctBalance", AmtAcctBalance);
}
/** Get Accounted Balance.
@return Accounted Balance Amount */
public BigDecimal getAmtAcctBalance() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtAcctBalance");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Revaluated Amount Cr.
@param AmtRevalCr Revaluated Cr Amount */
public void setAmtRevalCr (BigDecimal AmtRevalCr)
{
if (AmtRevalCr == null) throw new IllegalArgumentException ("AmtRevalCr is mandatory.");
set_Value ("AmtRevalCr", AmtRevalCr);
}
/** Get Revaluated Amount Cr.
@return Revaluated Cr Amount */
public BigDecimal getAmtRevalCr() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtRevalCr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Revaluated Difference Cr.
@param AmtRevalCrDiff Revaluated Cr Amount Difference */
public void setAmtRevalCrDiff (BigDecimal AmtRevalCrDiff)
{
if (AmtRevalCrDiff == null) throw new IllegalArgumentException ("AmtRevalCrDiff is mandatory.");
set_Value ("AmtRevalCrDiff", AmtRevalCrDiff);
}
/** Get Revaluated Difference Cr.
@return Revaluated Cr Amount Difference */
public BigDecimal getAmtRevalCrDiff() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtRevalCrDiff");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Revaluated Amount Dr.
@param AmtRevalDr Revaluated Dr Amount */
public void setAmtRevalDr (BigDecimal AmtRevalDr)
{
if (AmtRevalDr == null) throw new IllegalArgumentException ("AmtRevalDr is mandatory.");
set_Value ("AmtRevalDr", AmtRevalDr);
}
/** Get Revaluated Amount Dr.
@return Revaluated Dr Amount */
public BigDecimal getAmtRevalDr() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtRevalDr");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Revaluated Difference Dr.
@param AmtRevalDrDiff Revaluated Dr Amount Difference */
public void setAmtRevalDrDiff (BigDecimal AmtRevalDrDiff)
{
if (AmtRevalDrDiff == null) throw new IllegalArgumentException ("AmtRevalDrDiff is mandatory.");
set_Value ("AmtRevalDrDiff", AmtRevalDrDiff);
}
/** Get Revaluated Difference Dr.
@return Revaluated Dr Amount Difference */
public BigDecimal getAmtRevalDrDiff() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtRevalDrDiff");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Source Balance.
@param AmtSourceBalance Source Balance Amount */
public void setAmtSourceBalance (BigDecimal AmtSourceBalance)
{
if (AmtSourceBalance == null) throw new IllegalArgumentException ("AmtSourceBalance is mandatory.");
set_Value ("AmtSourceBalance", AmtSourceBalance);
}
/** Get Source Balance.
@return Source Balance Amount */
public BigDecimal getAmtSourceBalance() 
{
BigDecimal bd = (BigDecimal)get_Value("AmtSourceBalance");
if (bd == null) return Env.ZERO;
return bd;
}

/** C_ConversionTypeReval_ID AD_Reference_ID=352 */
public static final int C_CONVERSIONTYPEREVAL_ID_AD_Reference_ID=352;
/** Set Revaluation Conversion Type.
@param C_ConversionTypeReval_ID Revaluation Currency Conversion Type */
public void setC_ConversionTypeReval_ID (int C_ConversionTypeReval_ID)
{
if (C_ConversionTypeReval_ID < 1) throw new IllegalArgumentException ("C_ConversionTypeReval_ID is mandatory.");
set_Value ("C_ConversionTypeReval_ID", Integer.valueOf(C_ConversionTypeReval_ID));
}
/** Get Revaluation Conversion Type.
@return Revaluation Currency Conversion Type */
public int getC_ConversionTypeReval_ID() 
{
Integer ii = (Integer)get_Value("C_ConversionTypeReval_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** C_DocTypeReval_ID AD_Reference_ID=170 */
public static final int C_DOCTYPEREVAL_ID_AD_Reference_ID=170;
/** Set Revaluation Document Type.
@param C_DocTypeReval_ID Document Type for Revaluation Journal */
public void setC_DocTypeReval_ID (int C_DocTypeReval_ID)
{
if (C_DocTypeReval_ID <= 0) set_Value ("C_DocTypeReval_ID", null);
 else 
set_Value ("C_DocTypeReval_ID", Integer.valueOf(C_DocTypeReval_ID));
}
/** Get Revaluation Document Type.
@return Document Type for Revaluation Journal */
public int getC_DocTypeReval_ID() 
{
Integer ii = (Integer)get_Value("C_DocTypeReval_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID < 1) throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
set_Value ("C_Invoice_ID", Integer.valueOf(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Revaluation Date.
@param DateReval Date of Revaluation */
public void setDateReval (Timestamp DateReval)
{
if (DateReval == null) throw new IllegalArgumentException ("DateReval is mandatory.");
set_Value ("DateReval", DateReval);
}
/** Get Revaluation Date.
@return Date of Revaluation */
public Timestamp getDateReval() 
{
return (Timestamp)get_Value("DateReval");
}
/** Set Accounting Fact.
@param Fact_Acct_ID Accounting Fact */
public void setFact_Acct_ID (int Fact_Acct_ID)
{
if (Fact_Acct_ID < 1) throw new IllegalArgumentException ("Fact_Acct_ID is mandatory.");
set_ValueNoCheck ("Fact_Acct_ID", Integer.valueOf(Fact_Acct_ID));
}
/** Get Accounting Fact.
@return Accounting Fact */
public int getFact_Acct_ID() 
{
Integer ii = (Integer)get_Value("Fact_Acct_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Grand Total.
@param GrandTotal Total amount of document */
public void setGrandTotal (BigDecimal GrandTotal)
{
if (GrandTotal == null) throw new IllegalArgumentException ("GrandTotal is mandatory.");
set_Value ("GrandTotal", GrandTotal);
}
/** Get Grand Total.
@return Total amount of document */
public BigDecimal getGrandTotal() 
{
BigDecimal bd = (BigDecimal)get_Value("GrandTotal");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Include All Currencies.
@param IsAllCurrencies Report not just foreign currency Invoices */
public void setIsAllCurrencies (boolean IsAllCurrencies)
{
set_Value ("IsAllCurrencies", Boolean.valueOf(IsAllCurrencies));
}
/** Get Include All Currencies.
@return Report not just foreign currency Invoices */
public boolean isAllCurrencies() 
{
Object oo = get_Value("IsAllCurrencies");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Open Amount.
@param OpenAmt Open item amount */
public void setOpenAmt (BigDecimal OpenAmt)
{
if (OpenAmt == null) throw new IllegalArgumentException ("OpenAmt is mandatory.");
set_Value ("OpenAmt", OpenAmt);
}
/** Get Open Amount.
@return Open item amount */
public BigDecimal getOpenAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OpenAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Percent.
@param Percent Percentage */
public void setPercent (BigDecimal Percent)
{
set_Value ("Percent", Percent);
}
/** Get Percent.
@return Percentage */
public BigDecimal getPercent() 
{
BigDecimal bd = (BigDecimal)get_Value("Percent");
if (bd == null) return Env.ZERO;
return bd;
}
}
