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
/** Generated Model for C_Charge_Acct
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:56.937 */
public class X_C_Charge_Acct extends PO
{
/** Standard Constructor
@param ctx context
@param C_Charge_Acct_ID id
@param trxName transaction
*/
public X_C_Charge_Acct (Properties ctx, int C_Charge_Acct_ID, String trxName)
{
super (ctx, C_Charge_Acct_ID, trxName);
/** if (C_Charge_Acct_ID == 0)
{
setC_AcctSchema_ID (0);
setC_Charge_ID (0);
setCh_Expense_Acct (0);
setCh_Revenue_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_Charge_Acct (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=396 */
public static final int Table_ID=396;

/** TableName=C_Charge_Acct */
public static final String Table_Name="C_Charge_Acct";

protected static KeyNamePair Model = new KeyNamePair(396,"C_Charge_Acct");

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
StringBuffer sb = new StringBuffer ("X_C_Charge_Acct[").append(get_ID()).append("]");
return sb.toString();
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
/** Set Charge.
@param C_Charge_ID Additional document charges */
public void setC_Charge_ID (int C_Charge_ID)
{
if (C_Charge_ID < 1) throw new IllegalArgumentException ("C_Charge_ID is mandatory.");
set_ValueNoCheck ("C_Charge_ID", new Integer(C_Charge_ID));
}
/** Get Charge.
@return Additional document charges */
public int getC_Charge_ID() 
{
Integer ii = (Integer)get_Value("C_Charge_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charge Expense.
@param Ch_Expense_Acct Charge Expense Account */
public void setCh_Expense_Acct (int Ch_Expense_Acct)
{
set_Value ("Ch_Expense_Acct", new Integer(Ch_Expense_Acct));
}
/** Get Charge Expense.
@return Charge Expense Account */
public int getCh_Expense_Acct() 
{
Integer ii = (Integer)get_Value("Ch_Expense_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Charge Revenue.
@param Ch_Revenue_Acct Charge Revenue Account */
public void setCh_Revenue_Acct (int Ch_Revenue_Acct)
{
set_Value ("Ch_Revenue_Acct", new Integer(Ch_Revenue_Acct));
}
/** Get Charge Revenue.
@return Charge Revenue Account */
public int getCh_Revenue_Acct() 
{
Integer ii = (Integer)get_Value("Ch_Revenue_Acct");
if (ii == null) return 0;
return ii.intValue();
}
}
