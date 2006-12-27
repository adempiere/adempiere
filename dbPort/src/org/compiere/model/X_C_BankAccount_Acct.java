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
/** Generated Model for C_BankAccount_Acct
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_BankAccount_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_BankAccount_Acct_ID id
@param trxName transaction
*/
public X_C_BankAccount_Acct (Properties ctx, int C_BankAccount_Acct_ID, String trxName)
{
super (ctx, C_BankAccount_Acct_ID, trxName);
/** if (C_BankAccount_Acct_ID == 0)
{
setB_Asset_Acct (0);
setB_Expense_Acct (0);
setB_InTransit_Acct (0);
setB_InterestExp_Acct (0);
setB_InterestRev_Acct (0);
setB_PaymentSelect_Acct (0);
setB_RevaluationGain_Acct (0);
setB_RevaluationLoss_Acct (0);
setB_SettlementGain_Acct (0);
setB_SettlementLoss_Acct (0);
setB_UnallocatedCash_Acct (0);
setB_Unidentified_Acct (0);
setC_AcctSchema_ID (0);
setC_BankAccount_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BankAccount_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=391 */
public static final int Table_ID=MTable.getTable_ID("C_BankAccount_Acct");

/** TableName=C_BankAccount_Acct */
public static final String Table_Name="C_BankAccount_Acct";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BankAccount_Acct");

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
StringBuffer sb = new StringBuffer ("X_C_BankAccount_Acct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Bank Asset.
@param B_Asset_Acct Bank Asset Account */
public void setB_Asset_Acct (int B_Asset_Acct)
{
set_Value ("B_Asset_Acct", Integer.valueOf(B_Asset_Acct));
}
/** Get Bank Asset.
@return Bank Asset Account */
public int getB_Asset_Acct() 
{
Integer ii = (Integer)get_Value("B_Asset_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Expense.
@param B_Expense_Acct Bank Expense Account */
public void setB_Expense_Acct (int B_Expense_Acct)
{
set_Value ("B_Expense_Acct", Integer.valueOf(B_Expense_Acct));
}
/** Get Bank Expense.
@return Bank Expense Account */
public int getB_Expense_Acct() 
{
Integer ii = (Integer)get_Value("B_Expense_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank In Transit.
@param B_InTransit_Acct Bank In Transit Account */
public void setB_InTransit_Acct (int B_InTransit_Acct)
{
set_Value ("B_InTransit_Acct", Integer.valueOf(B_InTransit_Acct));
}
/** Get Bank In Transit.
@return Bank In Transit Account */
public int getB_InTransit_Acct() 
{
Integer ii = (Integer)get_Value("B_InTransit_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Interest Expense.
@param B_InterestExp_Acct Bank Interest Expense Account */
public void setB_InterestExp_Acct (int B_InterestExp_Acct)
{
set_Value ("B_InterestExp_Acct", Integer.valueOf(B_InterestExp_Acct));
}
/** Get Bank Interest Expense.
@return Bank Interest Expense Account */
public int getB_InterestExp_Acct() 
{
Integer ii = (Integer)get_Value("B_InterestExp_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Interest Revenue.
@param B_InterestRev_Acct Bank Interest Revenue Account */
public void setB_InterestRev_Acct (int B_InterestRev_Acct)
{
set_Value ("B_InterestRev_Acct", Integer.valueOf(B_InterestRev_Acct));
}
/** Get Bank Interest Revenue.
@return Bank Interest Revenue Account */
public int getB_InterestRev_Acct() 
{
Integer ii = (Integer)get_Value("B_InterestRev_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment Selection.
@param B_PaymentSelect_Acct AP Payment Selection Clearing Account */
public void setB_PaymentSelect_Acct (int B_PaymentSelect_Acct)
{
set_Value ("B_PaymentSelect_Acct", Integer.valueOf(B_PaymentSelect_Acct));
}
/** Get Payment Selection.
@return AP Payment Selection Clearing Account */
public int getB_PaymentSelect_Acct() 
{
Integer ii = (Integer)get_Value("B_PaymentSelect_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Revaluation Gain.
@param B_RevaluationGain_Acct Bank Revaluation Gain Account */
public void setB_RevaluationGain_Acct (int B_RevaluationGain_Acct)
{
set_Value ("B_RevaluationGain_Acct", Integer.valueOf(B_RevaluationGain_Acct));
}
/** Get Bank Revaluation Gain.
@return Bank Revaluation Gain Account */
public int getB_RevaluationGain_Acct() 
{
Integer ii = (Integer)get_Value("B_RevaluationGain_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Revaluation Loss.
@param B_RevaluationLoss_Acct Bank Revaluation Loss Account */
public void setB_RevaluationLoss_Acct (int B_RevaluationLoss_Acct)
{
set_Value ("B_RevaluationLoss_Acct", Integer.valueOf(B_RevaluationLoss_Acct));
}
/** Get Bank Revaluation Loss.
@return Bank Revaluation Loss Account */
public int getB_RevaluationLoss_Acct() 
{
Integer ii = (Integer)get_Value("B_RevaluationLoss_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Settlement Gain.
@param B_SettlementGain_Acct Bank Settlement Gain Account */
public void setB_SettlementGain_Acct (int B_SettlementGain_Acct)
{
set_Value ("B_SettlementGain_Acct", Integer.valueOf(B_SettlementGain_Acct));
}
/** Get Bank Settlement Gain.
@return Bank Settlement Gain Account */
public int getB_SettlementGain_Acct() 
{
Integer ii = (Integer)get_Value("B_SettlementGain_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Settlement Loss.
@param B_SettlementLoss_Acct Bank Settlement Loss Account */
public void setB_SettlementLoss_Acct (int B_SettlementLoss_Acct)
{
set_Value ("B_SettlementLoss_Acct", Integer.valueOf(B_SettlementLoss_Acct));
}
/** Get Bank Settlement Loss.
@return Bank Settlement Loss Account */
public int getB_SettlementLoss_Acct() 
{
Integer ii = (Integer)get_Value("B_SettlementLoss_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Unallocated Cash.
@param B_UnallocatedCash_Acct Unallocated Cash Clearing Account */
public void setB_UnallocatedCash_Acct (int B_UnallocatedCash_Acct)
{
set_Value ("B_UnallocatedCash_Acct", Integer.valueOf(B_UnallocatedCash_Acct));
}
/** Get Unallocated Cash.
@return Unallocated Cash Clearing Account */
public int getB_UnallocatedCash_Acct() 
{
Integer ii = (Integer)get_Value("B_UnallocatedCash_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Bank Unidentified Receipts.
@param B_Unidentified_Acct Bank Unidentified Receipts Account */
public void setB_Unidentified_Acct (int B_Unidentified_Acct)
{
set_Value ("B_Unidentified_Acct", Integer.valueOf(B_Unidentified_Acct));
}
/** Get Bank Unidentified Receipts.
@return Bank Unidentified Receipts Account */
public int getB_Unidentified_Acct() 
{
Integer ii = (Integer)get_Value("B_Unidentified_Acct");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Bank Account.
@param C_BankAccount_ID Account at the Bank */
public void setC_BankAccount_ID (int C_BankAccount_ID)
{
if (C_BankAccount_ID < 1) throw new IllegalArgumentException ("C_BankAccount_ID is mandatory.");
set_ValueNoCheck ("C_BankAccount_ID", Integer.valueOf(C_BankAccount_ID));
}
/** Get Bank Account.
@return Account at the Bank */
public int getC_BankAccount_ID() 
{
Integer ii = (Integer)get_Value("C_BankAccount_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
