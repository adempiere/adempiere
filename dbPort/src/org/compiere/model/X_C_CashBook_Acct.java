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
/** Generated Model for C_CashBook_Acct
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:56.875 */
public class X_C_CashBook_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_CashBook_Acct_ID id
@param trxName transaction
*/
public X_C_CashBook_Acct (Properties ctx, int C_CashBook_Acct_ID, String trxName)
{
super (ctx, C_CashBook_Acct_ID, trxName);
/** if (C_CashBook_Acct_ID == 0)
{
setCB_Asset_Acct (0);
setCB_CashTransfer_Acct (0);
setCB_Differences_Acct (0);
setCB_Expense_Acct (0);
setCB_Receipt_Acct (0);
setC_AcctSchema_ID (0);
setC_CashBook_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_CashBook_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=409 */
public static final int Table_ID=409;

/** TableName=C_CashBook_Acct */
public static final String Table_Name="C_CashBook_Acct";

protected static KeyNamePair Model = new KeyNamePair(409,"C_CashBook_Acct");

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
StringBuffer sb = new StringBuffer ("X_C_CashBook_Acct[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Cash Book Asset.
@param CB_Asset_Acct Cash Book Asset Account */
public void setCB_Asset_Acct (int CB_Asset_Acct)
{
set_Value ("CB_Asset_Acct", new Integer(CB_Asset_Acct));
}
/** Get Cash Book Asset.
@return Cash Book Asset Account */
public int getCB_Asset_Acct() 
{
Integer ii = (Integer)get_Value("CB_Asset_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cash Transfer.
@param CB_CashTransfer_Acct Cash Transfer Clearing Account */
public void setCB_CashTransfer_Acct (int CB_CashTransfer_Acct)
{
set_Value ("CB_CashTransfer_Acct", new Integer(CB_CashTransfer_Acct));
}
/** Get Cash Transfer.
@return Cash Transfer Clearing Account */
public int getCB_CashTransfer_Acct() 
{
Integer ii = (Integer)get_Value("CB_CashTransfer_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cash Book Differences.
@param CB_Differences_Acct Cash Book Differences Account */
public void setCB_Differences_Acct (int CB_Differences_Acct)
{
set_Value ("CB_Differences_Acct", new Integer(CB_Differences_Acct));
}
/** Get Cash Book Differences.
@return Cash Book Differences Account */
public int getCB_Differences_Acct() 
{
Integer ii = (Integer)get_Value("CB_Differences_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cash Book Expense.
@param CB_Expense_Acct Cash Book Expense Account */
public void setCB_Expense_Acct (int CB_Expense_Acct)
{
set_Value ("CB_Expense_Acct", new Integer(CB_Expense_Acct));
}
/** Get Cash Book Expense.
@return Cash Book Expense Account */
public int getCB_Expense_Acct() 
{
Integer ii = (Integer)get_Value("CB_Expense_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cash Book Receipt.
@param CB_Receipt_Acct Cash Book Receipts Account */
public void setCB_Receipt_Acct (int CB_Receipt_Acct)
{
set_Value ("CB_Receipt_Acct", new Integer(CB_Receipt_Acct));
}
/** Get Cash Book Receipt.
@return Cash Book Receipts Account */
public int getCB_Receipt_Acct() 
{
Integer ii = (Integer)get_Value("CB_Receipt_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Accounting Schema.
@param C_AcctSchema_ID Rules for accounting */
public void setC_AcctSchema_ID (int C_AcctSchema_ID)
{
if (C_AcctSchema_ID < 1) throw new IllegalArgumentException ("C_AcctSchema_ID is mandatory.");
set_ValueNoCheck ("C_AcctSchema_ID", new Integer(C_AcctSchema_ID));
}
/** Get Accounting Schema.
@return Rules for accounting */
public int getC_AcctSchema_ID() 
{
Integer ii = (Integer)get_Value("C_AcctSchema_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Cash Book.
@param C_CashBook_ID Cash Book for recording petty cash transactions */
public void setC_CashBook_ID (int C_CashBook_ID)
{
if (C_CashBook_ID < 1) throw new IllegalArgumentException ("C_CashBook_ID is mandatory.");
set_ValueNoCheck ("C_CashBook_ID", new Integer(C_CashBook_ID));
}
/** Get Cash Book.
@return Cash Book for recording petty cash transactions */
public int getC_CashBook_ID() 
{
Integer ii = (Integer)get_Value("C_CashBook_ID");
if (ii == null) return 0;
return ii.intValue();
}
}
