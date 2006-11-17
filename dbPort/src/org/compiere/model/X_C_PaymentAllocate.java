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
/** Generated Model for C_PaymentAllocate
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.218 */
public class X_C_PaymentAllocate extends PO
{
/** Standard Constructor
@param ctx context
@param C_PaymentAllocate_ID id
@param trxName transaction
*/
public X_C_PaymentAllocate (Properties ctx, int C_PaymentAllocate_ID, String trxName)
{
super (ctx, C_PaymentAllocate_ID, trxName);
/** if (C_PaymentAllocate_ID == 0)
{
setAmount (Env.ZERO);
setC_Invoice_ID (0);
setC_PaymentAllocate_ID (0);
setC_Payment_ID (0);
setDiscountAmt (Env.ZERO);
setOverUnderAmt (Env.ZERO);
setWriteOffAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_PaymentAllocate (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=812 */
public static final int Table_ID=812;

/** TableName=C_PaymentAllocate */
public static final String Table_Name="C_PaymentAllocate";

protected static KeyNamePair Model = new KeyNamePair(812,"C_PaymentAllocate");

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
StringBuffer sb = new StringBuffer ("X_C_PaymentAllocate[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amount Amount in a defined currency */
public void setAmount (BigDecimal Amount)
{
if (Amount == null) throw new IllegalArgumentException ("Amount is mandatory.");
set_Value ("Amount", Amount);
}
/** Get Amount.
@return Amount in a defined currency */
public BigDecimal getAmount() 
{
BigDecimal bd = (BigDecimal)get_Value("Amount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Allocation Line.
@param C_AllocationLine_ID Allocation Line */
public void setC_AllocationLine_ID (int C_AllocationLine_ID)
{
if (C_AllocationLine_ID <= 0) set_Value ("C_AllocationLine_ID", null);
 else 
set_Value ("C_AllocationLine_ID", new Integer(C_AllocationLine_ID));
}
/** Get Allocation Line.
@return Allocation Line */
public int getC_AllocationLine_ID() 
{
Integer ii = (Integer)get_Value("C_AllocationLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID < 1) throw new IllegalArgumentException ("C_Invoice_ID is mandatory.");
set_Value ("C_Invoice_ID", new Integer(C_Invoice_ID));
}
/** Get Invoice.
@return Invoice Identifier */
public int getC_Invoice_ID() 
{
Integer ii = (Integer)get_Value("C_Invoice_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Invoice_ID()));
}
/** Set Allocate Payment.
@param C_PaymentAllocate_ID Allocate Payment to Invoices */
public void setC_PaymentAllocate_ID (int C_PaymentAllocate_ID)
{
if (C_PaymentAllocate_ID < 1) throw new IllegalArgumentException ("C_PaymentAllocate_ID is mandatory.");
set_ValueNoCheck ("C_PaymentAllocate_ID", new Integer(C_PaymentAllocate_ID));
}
/** Get Allocate Payment.
@return Allocate Payment to Invoices */
public int getC_PaymentAllocate_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentAllocate_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID < 1) throw new IllegalArgumentException ("C_Payment_ID is mandatory.");
set_Value ("C_Payment_ID", new Integer(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
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
/** Set Invoice Amt.
@param InvoiceAmt Invoice Amt */
public void setInvoiceAmt (BigDecimal InvoiceAmt)
{
set_Value ("InvoiceAmt", InvoiceAmt);
}
/** Get Invoice Amt.
@return Invoice Amt */
public BigDecimal getInvoiceAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("InvoiceAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Over/Under Payment.
@param OverUnderAmt Over-Payment (unallocated) or Under-Payment (partial payment) Amount */
public void setOverUnderAmt (BigDecimal OverUnderAmt)
{
if (OverUnderAmt == null) throw new IllegalArgumentException ("OverUnderAmt is mandatory.");
set_Value ("OverUnderAmt", OverUnderAmt);
}
/** Get Over/Under Payment.
@return Over-Payment (unallocated) or Under-Payment (partial payment) Amount */
public BigDecimal getOverUnderAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OverUnderAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Remaining Amt.
@param RemainingAmt Remaining Amount */
public void setRemainingAmt (BigDecimal RemainingAmt)
{
throw new IllegalArgumentException ("RemainingAmt is virtual column");
}
/** Get Remaining Amt.
@return Remaining Amount */
public BigDecimal getRemainingAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("RemainingAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Write-off Amount.
@param WriteOffAmt Amount to write-off */
public void setWriteOffAmt (BigDecimal WriteOffAmt)
{
if (WriteOffAmt == null) throw new IllegalArgumentException ("WriteOffAmt is mandatory.");
set_Value ("WriteOffAmt", WriteOffAmt);
}
/** Get Write-off Amount.
@return Amount to write-off */
public BigDecimal getWriteOffAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("WriteOffAmt");
if (bd == null) return Env.ZERO;
return bd;
}
}
