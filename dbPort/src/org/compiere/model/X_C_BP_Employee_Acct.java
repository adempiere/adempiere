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
/** Generated Model for C_BP_Employee_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_BP_Employee_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_BP_Employee_Acct_ID id
@param trxName transaction
*/
public X_C_BP_Employee_Acct (Properties ctx, int C_BP_Employee_Acct_ID, String trxName)
{
super (ctx, C_BP_Employee_Acct_ID, trxName);
/** if (C_BP_Employee_Acct_ID == 0)
{
setC_AcctSchema_ID (0);
setC_BPartner_ID (0);
setE_Expense_Acct (0);
setE_Prepayment_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BP_Employee_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=184 */
public static final int Table_ID=MTable.getTable_ID("C_BP_Employee_Acct");

/** TableName=C_BP_Employee_Acct */
public static final String Table_Name="C_BP_Employee_Acct";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BP_Employee_Acct");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_C_BP_Employee_Acct[").append(get_ID()).append("]");
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
/** Column name C_AcctSchema_ID */
public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Employee Expense.
@param E_Expense_Acct Account for Employee Expenses */
public void setE_Expense_Acct (int E_Expense_Acct)
{
set_Value ("E_Expense_Acct", Integer.valueOf(E_Expense_Acct));
}
/** Get Employee Expense.
@return Account for Employee Expenses */
public int getE_Expense_Acct() 
{
Integer ii = (Integer)get_Value("E_Expense_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name E_Expense_Acct */
public static final String COLUMNNAME_E_Expense_Acct = "E_Expense_Acct";
/** Set Employee Prepayment.
@param E_Prepayment_Acct Account for Employee Expense Prepayments */
public void setE_Prepayment_Acct (int E_Prepayment_Acct)
{
set_Value ("E_Prepayment_Acct", Integer.valueOf(E_Prepayment_Acct));
}
/** Get Employee Prepayment.
@return Account for Employee Expense Prepayments */
public int getE_Prepayment_Acct() 
{
Integer ii = (Integer)get_Value("E_Prepayment_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name E_Prepayment_Acct */
public static final String COLUMNNAME_E_Prepayment_Acct = "E_Prepayment_Acct";
}
