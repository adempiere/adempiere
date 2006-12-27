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
/** Generated Model for C_AllocationLine
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_C_AllocationLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_AllocationLine_ID id
@param trxName transaction
*/
public X_C_AllocationLine (Properties ctx, int C_AllocationLine_ID, String trxName)
{
super (ctx, C_AllocationLine_ID, trxName);
/** if (C_AllocationLine_ID == 0)
{
setAmount (Env.ZERO);
setC_AllocationHdr_ID (0);
setC_AllocationLine_ID (0);
setDiscountAmt (Env.ZERO);
setWriteOffAmt (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_AllocationLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=390 */
public static final int Table_ID=MTable.getTable_ID("C_AllocationLine");

/** TableName=C_AllocationLine */
public static final String Table_Name="C_AllocationLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_AllocationLine");

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
StringBuffer sb = new StringBuffer ("X_C_AllocationLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Amount.
@param Amount Amount in a defined currency */
public void setAmount (BigDecimal Amount)
{
if (Amount == null) throw new IllegalArgumentException ("Amount is mandatory.");
set_ValueNoCheck ("Amount", Amount);
}
/** Get Amount.
@return Amount in a defined currency */
public BigDecimal getAmount() 
{
BigDecimal bd = (BigDecimal)get_Value("Amount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Allocation.
@param C_AllocationHdr_ID Payment allocation */
public void setC_AllocationHdr_ID (int C_AllocationHdr_ID)
{
if (C_AllocationHdr_ID < 1) throw new IllegalArgumentException ("C_AllocationHdr_ID is mandatory.");
set_ValueNoCheck ("C_AllocationHdr_ID", Integer.valueOf(C_AllocationHdr_ID));
}
/** Get Allocation.
@return Payment allocation */
public int getC_AllocationHdr_ID() 
{
Integer ii = (Integer)get_Value("C_AllocationHdr_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Allocation Line.
@param C_AllocationLine_ID Allocation Line */
public void setC_AllocationLine_ID (int C_AllocationLine_ID)
{
if (C_AllocationLine_ID < 1) throw new IllegalArgumentException ("C_AllocationLine_ID is mandatory.");
set_ValueNoCheck ("C_AllocationLine_ID", Integer.valueOf(C_AllocationLine_ID));
}
/** Get Allocation Line.
@return Allocation Line */
public int getC_AllocationLine_ID() 
{
Integer ii = (Integer)get_Value("C_AllocationLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID <= 0) set_ValueNoCheck ("C_BPartner_ID", null);
 else 
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
/** Set Cash Journal Line.
@param C_CashLine_ID Cash Journal Line */
public void setC_CashLine_ID (int C_CashLine_ID)
{
if (C_CashLine_ID <= 0) set_ValueNoCheck ("C_CashLine_ID", null);
 else 
set_ValueNoCheck ("C_CashLine_ID", Integer.valueOf(C_CashLine_ID));
}
/** Get Cash Journal Line.
@return Cash Journal Line */
public int getC_CashLine_ID() 
{
Integer ii = (Integer)get_Value("C_CashLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Invoice.
@param C_Invoice_ID Invoice Identifier */
public void setC_Invoice_ID (int C_Invoice_ID)
{
if (C_Invoice_ID <= 0) set_ValueNoCheck ("C_Invoice_ID", null);
 else 
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
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Invoice_ID()));
}
/** Set Order.
@param C_Order_ID Order */
public void setC_Order_ID (int C_Order_ID)
{
if (C_Order_ID <= 0) set_ValueNoCheck ("C_Order_ID", null);
 else 
set_ValueNoCheck ("C_Order_ID", Integer.valueOf(C_Order_ID));
}
/** Get Order.
@return Order */
public int getC_Order_ID() 
{
Integer ii = (Integer)get_Value("C_Order_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment.
@param C_Payment_ID Payment identifier */
public void setC_Payment_ID (int C_Payment_ID)
{
if (C_Payment_ID <= 0) set_ValueNoCheck ("C_Payment_ID", null);
 else 
set_ValueNoCheck ("C_Payment_ID", Integer.valueOf(C_Payment_ID));
}
/** Get Payment.
@return Payment identifier */
public int getC_Payment_ID() 
{
Integer ii = (Integer)get_Value("C_Payment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Transaction Date.
@param DateTrx Transaction Date */
public void setDateTrx (Timestamp DateTrx)
{
set_ValueNoCheck ("DateTrx", DateTrx);
}
/** Get Transaction Date.
@return Transaction Date */
public Timestamp getDateTrx() 
{
return (Timestamp)get_Value("DateTrx");
}
/** Set Discount Amount.
@param DiscountAmt Calculated amount of discount */
public void setDiscountAmt (BigDecimal DiscountAmt)
{
if (DiscountAmt == null) throw new IllegalArgumentException ("DiscountAmt is mandatory.");
set_ValueNoCheck ("DiscountAmt", DiscountAmt);
}
/** Get Discount Amount.
@return Calculated amount of discount */
public BigDecimal getDiscountAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DiscountAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Manual.
@param IsManual This is a manual process */
public void setIsManual (boolean IsManual)
{
set_ValueNoCheck ("IsManual", Boolean.valueOf(IsManual));
}
/** Get Manual.
@return This is a manual process */
public boolean isManual() 
{
Object oo = get_Value("IsManual");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Over/Under Payment.
@param OverUnderAmt Over-Payment (unallocated) or Under-Payment (partial payment) Amount */
public void setOverUnderAmt (BigDecimal OverUnderAmt)
{
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
/** Set Write-off Amount.
@param WriteOffAmt Amount to write-off */
public void setWriteOffAmt (BigDecimal WriteOffAmt)
{
if (WriteOffAmt == null) throw new IllegalArgumentException ("WriteOffAmt is mandatory.");
set_ValueNoCheck ("WriteOffAmt", WriteOffAmt);
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
