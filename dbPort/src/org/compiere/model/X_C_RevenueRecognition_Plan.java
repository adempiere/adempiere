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
/** Generated Model for C_RevenueRecognition_Plan
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.531 */
public class X_C_RevenueRecognition_Plan extends PO
{
/** Standard Constructor
@param ctx context
@param C_RevenueRecognition_Plan_ID id
@param trxName transaction
*/
public X_C_RevenueRecognition_Plan (Properties ctx, int C_RevenueRecognition_Plan_ID, String trxName)
{
super (ctx, C_RevenueRecognition_Plan_ID, trxName);
/** if (C_RevenueRecognition_Plan_ID == 0)
{
setC_AcctSchema_ID (0);
setC_Currency_ID (0);
setC_InvoiceLine_ID (0);
setC_RevenueRecognition_ID (0);
setC_RevenueRecognition_Plan_ID (0);
setP_Revenue_Acct (0);
setRecognizedAmt (Env.ZERO);
setTotalAmt (Env.ZERO);
setUnEarnedRevenue_Acct (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RevenueRecognition_Plan (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=443 */
public static final int Table_ID=443;

/** TableName=C_RevenueRecognition_Plan */
public static final String Table_Name="C_RevenueRecognition_Plan";

protected static KeyNamePair Model = new KeyNamePair(443,"C_RevenueRecognition_Plan");

protected BigDecimal accessLevel = new BigDecimal(1);
/** AccessLevel
@return 1 - Org 
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
StringBuffer sb = new StringBuffer ("X_C_RevenueRecognition_Plan[").append(get_ID()).append("]");
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
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
set_ValueNoCheck ("C_Currency_ID", new Integer(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice Line.
@param C_InvoiceLine_ID Invoice Detail Line */
public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
{
if (C_InvoiceLine_ID < 1) throw new IllegalArgumentException ("C_InvoiceLine_ID is mandatory.");
set_ValueNoCheck ("C_InvoiceLine_ID", new Integer(C_InvoiceLine_ID));
}
/** Get Invoice Line.
@return Invoice Detail Line */
public int getC_InvoiceLine_ID() 
{
Integer ii = (Integer)get_Value("C_InvoiceLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Revenue Recognition.
@param C_RevenueRecognition_ID Method for recording revenue */
public void setC_RevenueRecognition_ID (int C_RevenueRecognition_ID)
{
if (C_RevenueRecognition_ID < 1) throw new IllegalArgumentException ("C_RevenueRecognition_ID is mandatory.");
set_ValueNoCheck ("C_RevenueRecognition_ID", new Integer(C_RevenueRecognition_ID));
}
/** Get Revenue Recognition.
@return Method for recording revenue */
public int getC_RevenueRecognition_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_RevenueRecognition_ID()));
}
/** Set Revenue Recognition Plan.
@param C_RevenueRecognition_Plan_ID Plan for recognizing or recording revenue */
public void setC_RevenueRecognition_Plan_ID (int C_RevenueRecognition_Plan_ID)
{
if (C_RevenueRecognition_Plan_ID < 1) throw new IllegalArgumentException ("C_RevenueRecognition_Plan_ID is mandatory.");
set_ValueNoCheck ("C_RevenueRecognition_Plan_ID", new Integer(C_RevenueRecognition_Plan_ID));
}
/** Get Revenue Recognition Plan.
@return Plan for recognizing or recording revenue */
public int getC_RevenueRecognition_Plan_ID() 
{
Integer ii = (Integer)get_Value("C_RevenueRecognition_Plan_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product Revenue.
@param P_Revenue_Acct Account for Product Revenue (Sales Account) */
public void setP_Revenue_Acct (int P_Revenue_Acct)
{
set_ValueNoCheck ("P_Revenue_Acct", new Integer(P_Revenue_Acct));
}
/** Get Product Revenue.
@return Account for Product Revenue (Sales Account) */
public int getP_Revenue_Acct() 
{
Integer ii = (Integer)get_Value("P_Revenue_Acct");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Recognized Amount.
@param RecognizedAmt Recognized Amount */
public void setRecognizedAmt (BigDecimal RecognizedAmt)
{
if (RecognizedAmt == null) throw new IllegalArgumentException ("RecognizedAmt is mandatory.");
set_ValueNoCheck ("RecognizedAmt", RecognizedAmt);
}
/** Get Recognized Amount.
@return Recognized Amount */
public BigDecimal getRecognizedAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("RecognizedAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Total Amount.
@param TotalAmt Total Amount */
public void setTotalAmt (BigDecimal TotalAmt)
{
if (TotalAmt == null) throw new IllegalArgumentException ("TotalAmt is mandatory.");
set_ValueNoCheck ("TotalAmt", TotalAmt);
}
/** Get Total Amount.
@return Total Amount */
public BigDecimal getTotalAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("TotalAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Unearned Revenue.
@param UnEarnedRevenue_Acct Account for unearned revenue */
public void setUnEarnedRevenue_Acct (int UnEarnedRevenue_Acct)
{
set_ValueNoCheck ("UnEarnedRevenue_Acct", new Integer(UnEarnedRevenue_Acct));
}
/** Get Unearned Revenue.
@return Account for unearned revenue */
public int getUnEarnedRevenue_Acct() 
{
Integer ii = (Integer)get_Value("UnEarnedRevenue_Acct");
if (ii == null) return 0;
return ii.intValue();
}
}
