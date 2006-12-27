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
/** Generated Model for C_InvoicePaySchedule
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_InvoicePaySchedule extends PO
{
/** Standard Constructor
@param ctx context
@param C_InvoicePaySchedule_ID id
@param trxName transaction
*/
public X_C_InvoicePaySchedule (Properties ctx, int C_InvoicePaySchedule_ID, String trxName)
{
super (ctx, C_InvoicePaySchedule_ID, trxName);
/** if (C_InvoicePaySchedule_ID == 0)
{
setC_InvoicePaySchedule_ID (0);
setC_Invoice_ID (0);
setDiscountAmt (Env.ZERO);
setDiscountDate (new Timestamp(System.currentTimeMillis()));
setDueAmt (Env.ZERO);
setDueDate (new Timestamp(System.currentTimeMillis()));
setIsValid (false);
setProcessed (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_InvoicePaySchedule (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=551 */
public static final int Table_ID=MTable.getTable_ID("C_InvoicePaySchedule");

/** TableName=C_InvoicePaySchedule */
public static final String Table_Name="C_InvoicePaySchedule";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_InvoicePaySchedule");

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
StringBuffer sb = new StringBuffer ("X_C_InvoicePaySchedule[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Invoice Payment Schedule.
@param C_InvoicePaySchedule_ID Invoice Payment Schedule */
public void setC_InvoicePaySchedule_ID (int C_InvoicePaySchedule_ID)
{
if (C_InvoicePaySchedule_ID < 1) throw new IllegalArgumentException ("C_InvoicePaySchedule_ID is mandatory.");
set_ValueNoCheck ("C_InvoicePaySchedule_ID", Integer.valueOf(C_InvoicePaySchedule_ID));
}
/** Get Invoice Payment Schedule.
@return Invoice Payment Schedule */
public int getC_InvoicePaySchedule_ID() 
{
Integer ii = (Integer)get_Value("C_InvoicePaySchedule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID < 1) throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
set_ValueNoCheck ("C_Invoice_ID", Integer.valueOf(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment Schedule.
@param C_PaySchedule_ID Payment Schedule Template */
public void setC_PaySchedule_ID (int C_PaySchedule_ID)
{
if (C_PaySchedule_ID <= 0) set_ValueNoCheck ("C_PaySchedule_ID", null);
 else 
set_ValueNoCheck ("C_PaySchedule_ID", Integer.valueOf(C_PaySchedule_ID));
}
/** Get Payment Schedule.
@return Payment Schedule Template */
public int getC_PaySchedule_ID() 
{
Integer ii = (Integer)get_Value("C_PaySchedule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Discount Amount.
@param DiscountAmt Calculated amount of discount */
public void setDiscountAmt (BigDecimal DiscountAmt)
{
if (DiscountAmt == null) throw new IllegalArgumentException ("DiscountAmt is mandatory.");
set_Value ("DiscountAmt", DiscountAmt);
}
/** Get Discount Amount.
@return Calculated amount of discount */
public BigDecimal getDiscountAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DiscountAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Discount Date.
@param DiscountDate Last Date for payments with discount */
public void setDiscountDate (Timestamp DiscountDate)
{
if (DiscountDate == null) throw new IllegalArgumentException ("DiscountDate is mandatory.");
set_Value ("DiscountDate", DiscountDate);
}
/** Get Discount Date.
@return Last Date for payments with discount */
public Timestamp getDiscountDate() 
{
return (Timestamp)get_Value("DiscountDate");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getDiscountDate()));
}
/** Set Amount due.
@param DueAmt Amount of the payment due */
public void setDueAmt (BigDecimal DueAmt)
{
if (DueAmt == null) throw new IllegalArgumentException ("DueAmt is mandatory.");
set_Value ("DueAmt", DueAmt);
}
/** Get Amount due.
@return Amount of the payment due */
public BigDecimal getDueAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DueAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Due Date.
@param DueDate Date when the payment is due */
public void setDueDate (Timestamp DueDate)
{
if (DueDate == null) throw new IllegalArgumentException ("DueDate is mandatory.");
set_Value ("DueDate", DueDate);
}
/** Get Due Date.
@return Date when the payment is due */
public Timestamp getDueDate() 
{
return (Timestamp)get_Value("DueDate");
}
/** Set Valid.
@param IsValid Element is valid */
public void setIsValid (boolean IsValid)
{
set_Value ("IsValid", Boolean.valueOf(IsValid));
}
/** Get Valid.
@return Element is valid */
public boolean isValid() 
{
Object oo = get_Value("IsValid");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", Boolean.valueOf(Processed));
}
/** Get Processed.
@return The document has been processed */
public boolean isProcessed() 
{
Object oo = get_Value("Processed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
}
