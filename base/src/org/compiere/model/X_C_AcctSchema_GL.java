/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
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
/** Generated Model for C_AcctSchema_GL
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_AcctSchema_GL extends PO
{
/** Standard Constructor
@param ctx context
@param C_AcctSchema_GL_ID id
@param trxName transaction
*/
public X_C_AcctSchema_GL (Properties ctx, int C_AcctSchema_GL_ID, String trxName)
{
super (ctx, C_AcctSchema_GL_ID, trxName);
/** if (C_AcctSchema_GL_ID == 0)
{
setC_AcctSchema_ID (0);
setCommitmentOffset_Acct (0);
setIncomeSummary_Acct (0);
setIntercompanyDueFrom_Acct (0);
setIntercompanyDueTo_Acct (0);
setPPVOffset_Acct (0);
setRetainedEarning_Acct (0);
setUseCurrencyBalancing (false);
setUseSuspenseBalancing (false);
setUseSuspenseError (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_AcctSchema_GL (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=266 */
public static final int Table_ID=MTable.getTable_ID("C_AcctSchema_GL");

/** TableName=C_AcctSchema_GL */
public static final String Table_Name="C_AcctSchema_GL";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_AcctSchema_GL");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_C_AcctSchema_GL[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", Integer.valueOf(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_AcctSchema_ID()));
}
/** Column name C_AcctSchema_ID */
public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";
/** Set Commitment Offset.
@param CommitmentOffset_Acct Budgetary Commitment Offset Account */
public void setCommitmentOffset_Acct (int CommitmentOffset_Acct)
{
set_Value ("CommitmentOffset_Acct", Integer.valueOf(CommitmentOffset_Acct));
}
/** Get Commitment Offset.
@return Budgetary Commitment Offset Account */
public int getCommitmentOffset_Acct() 
{
Integer ii = (Integer)get_Value("CommitmentOffset_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CommitmentOffset_Acct */
public static final String COLUMNNAME_CommitmentOffset_Acct = "CommitmentOffset_Acct";
/** Set Currency Balancing Acct.
@param CurrencyBalancing_Acct Account used when a currency is out of balance */
public void setCurrencyBalancing_Acct (int CurrencyBalancing_Acct)
{
set_Value ("CurrencyBalancing_Acct", Integer.valueOf(CurrencyBalancing_Acct));
}
/** Get Currency Balancing Acct.
@return Account used when a currency is out of balance */
public int getCurrencyBalancing_Acct() 
{
Integer ii = (Integer)get_Value("CurrencyBalancing_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CurrencyBalancing_Acct */
public static final String COLUMNNAME_CurrencyBalancing_Acct = "CurrencyBalancing_Acct";
/** Set Income Summary Acct.
@param IncomeSummary_Acct Income Summary Account  */
public void setIncomeSummary_Acct (int IncomeSummary_Acct)
{
set_Value ("IncomeSummary_Acct", Integer.valueOf(IncomeSummary_Acct));
}
/** Get Income Summary Acct.
@return Income Summary Account  */
public int getIncomeSummary_Acct() 
{
Integer ii = (Integer)get_Value("IncomeSummary_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name IncomeSummary_Acct */
public static final String COLUMNNAME_IncomeSummary_Acct = "IncomeSummary_Acct";
/** Set Intercompany Due From Acct.
@param IntercompanyDueFrom_Acct Intercompany Due From / Receivables Account */
public void setIntercompanyDueFrom_Acct (int IntercompanyDueFrom_Acct)
{
set_Value ("IntercompanyDueFrom_Acct", Integer.valueOf(IntercompanyDueFrom_Acct));
}
/** Get Intercompany Due From Acct.
@return Intercompany Due From / Receivables Account */
public int getIntercompanyDueFrom_Acct() 
{
Integer ii = (Integer)get_Value("IntercompanyDueFrom_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name IntercompanyDueFrom_Acct */
public static final String COLUMNNAME_IntercompanyDueFrom_Acct = "IntercompanyDueFrom_Acct";
/** Set Intercompany Due To Acct.
@param IntercompanyDueTo_Acct Intercompany Due To / Payable Account */
public void setIntercompanyDueTo_Acct (int IntercompanyDueTo_Acct)
{
set_Value ("IntercompanyDueTo_Acct", Integer.valueOf(IntercompanyDueTo_Acct));
}
/** Get Intercompany Due To Acct.
@return Intercompany Due To / Payable Account */
public int getIntercompanyDueTo_Acct() 
{
Integer ii = (Integer)get_Value("IntercompanyDueTo_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name IntercompanyDueTo_Acct */
public static final String COLUMNNAME_IntercompanyDueTo_Acct = "IntercompanyDueTo_Acct";
/** Set PPV Offset.
@param PPVOffset_Acct Purchase Price Variance Offset Account */
public void setPPVOffset_Acct (int PPVOffset_Acct)
{
set_Value ("PPVOffset_Acct", Integer.valueOf(PPVOffset_Acct));
}
/** Get PPV Offset.
@return Purchase Price Variance Offset Account */
public int getPPVOffset_Acct() 
{
Integer ii = (Integer)get_Value("PPVOffset_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PPVOffset_Acct */
public static final String COLUMNNAME_PPVOffset_Acct = "PPVOffset_Acct";
/** Set Retained Earning Acct.
@param RetainedEarning_Acct Retained Earning Acct */
public void setRetainedEarning_Acct (int RetainedEarning_Acct)
{
set_Value ("RetainedEarning_Acct", Integer.valueOf(RetainedEarning_Acct));
}
/** Get Retained Earning Acct.
@return Retained Earning Acct */
public int getRetainedEarning_Acct() 
{
Integer ii = (Integer)get_Value("RetainedEarning_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name RetainedEarning_Acct */
public static final String COLUMNNAME_RetainedEarning_Acct = "RetainedEarning_Acct";
/** Set Suspense Balancing Acct.
@param SuspenseBalancing_Acct Suspense Balancing Acct */
public void setSuspenseBalancing_Acct (int SuspenseBalancing_Acct)
{
set_Value ("SuspenseBalancing_Acct", Integer.valueOf(SuspenseBalancing_Acct));
}
/** Get Suspense Balancing Acct.
@return Suspense Balancing Acct */
public int getSuspenseBalancing_Acct() 
{
Integer ii = (Integer)get_Value("SuspenseBalancing_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SuspenseBalancing_Acct */
public static final String COLUMNNAME_SuspenseBalancing_Acct = "SuspenseBalancing_Acct";
/** Set Suspense Error Acct.
@param SuspenseError_Acct Suspense Error Acct */
public void setSuspenseError_Acct (int SuspenseError_Acct)
{
set_Value ("SuspenseError_Acct", Integer.valueOf(SuspenseError_Acct));
}
/** Get Suspense Error Acct.
@return Suspense Error Acct */
public int getSuspenseError_Acct() 
{
Integer ii = (Integer)get_Value("SuspenseError_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name SuspenseError_Acct */
public static final String COLUMNNAME_SuspenseError_Acct = "SuspenseError_Acct";
/** Set Use Currency Balancing.
@param UseCurrencyBalancing Use Currency Balancing */
public void setUseCurrencyBalancing (boolean UseCurrencyBalancing)
{
set_Value ("UseCurrencyBalancing", Boolean.valueOf(UseCurrencyBalancing));
}
/** Get Use Currency Balancing.
@return Use Currency Balancing */
public boolean isUseCurrencyBalancing() 
{
Object oo = get_Value("UseCurrencyBalancing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name UseCurrencyBalancing */
public static final String COLUMNNAME_UseCurrencyBalancing = "UseCurrencyBalancing";
/** Set Use Suspense Balancing.
@param UseSuspenseBalancing Use Suspense Balancing */
public void setUseSuspenseBalancing (boolean UseSuspenseBalancing)
{
set_Value ("UseSuspenseBalancing", Boolean.valueOf(UseSuspenseBalancing));
}
/** Get Use Suspense Balancing.
@return Use Suspense Balancing */
public boolean isUseSuspenseBalancing() 
{
Object oo = get_Value("UseSuspenseBalancing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name UseSuspenseBalancing */
public static final String COLUMNNAME_UseSuspenseBalancing = "UseSuspenseBalancing";
/** Set Use Suspense Error.
@param UseSuspenseError Use Suspense Error */
public void setUseSuspenseError (boolean UseSuspenseError)
{
set_Value ("UseSuspenseError", Boolean.valueOf(UseSuspenseError));
}
/** Get Use Suspense Error.
@return Use Suspense Error */
public boolean isUseSuspenseError() 
{
Object oo = get_Value("UseSuspenseError");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name UseSuspenseError */
public static final String COLUMNNAME_UseSuspenseError = "UseSuspenseError";
}
