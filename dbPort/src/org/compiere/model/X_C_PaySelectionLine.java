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
/** Generated Model for C_PaySelectionLine
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:58.093 */
public class X_C_PaySelectionLine extends PO
{
/** Standard Constructor
@param ctx context
@param C_PaySelectionLine_ID id
@param trxName transaction
*/
public X_C_PaySelectionLine (Properties ctx, int C_PaySelectionLine_ID, String trxName)
{
super (ctx, C_PaySelectionLine_ID, trxName);
/** if (C_PaySelectionLine_ID == 0)
{
setC_Invoice_ID (0);
setC_PaySelectionLine_ID (0);
setC_PaySelection_ID (0);
setDifferenceAmt (Env.ZERO);
setDiscountAmt (Env.ZERO);
setIsManual (false);
setIsSOTrx (false);
setLine (0);	// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_PaySelectionLine WHERE C_PaySelection_ID=@C_PaySelection_ID@
setOpenAmt (Env.ZERO);
setPayAmt (Env.ZERO);
setPaymentRule (null);	// S
setProcessed (false);	// N
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_PaySelectionLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=427 */
public static final int Table_ID=427;

/** TableName=C_PaySelectionLine */
public static final String Table_Name="C_PaySelectionLine";

protected static KeyNamePair Model = new KeyNamePair(427,"C_PaySelectionLine");

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
StringBuffer sb = new StringBuffer ("X_C_PaySelectionLine[").append(get_ID()).append("]");
return sb.toString();
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
/** Set Pay Selection Check.
@param C_PaySelectionCheck_ID Payment Selection Check */
public void setC_PaySelectionCheck_ID (int C_PaySelectionCheck_ID)
{
if (C_PaySelectionCheck_ID <= 0) set_Value ("C_PaySelectionCheck_ID", null);
 else 
set_Value ("C_PaySelectionCheck_ID", new Integer(C_PaySelectionCheck_ID));
}
/** Get Pay Selection Check.
@return Payment Selection Check */
public int getC_PaySelectionCheck_ID() 
{
Integer ii = (Integer)get_Value("C_PaySelectionCheck_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Payment Selection Line.
@param C_PaySelectionLine_ID Payment Selection Line */
public void setC_PaySelectionLine_ID (int C_PaySelectionLine_ID)
{
if (C_PaySelectionLine_ID < 1) throw new IllegalArgumentException ("C_PaySelectionLine_ID is mandatory.");
set_ValueNoCheck ("C_PaySelectionLine_ID", new Integer(C_PaySelectionLine_ID));
}
/** Get Payment Selection Line.
@return Payment Selection Line */
public int getC_PaySelectionLine_ID() 
{
Integer ii = (Integer)get_Value("C_PaySelectionLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_PaySelectionLine_ID()));
}
/** Set Payment Selection.
@param C_PaySelection_ID Payment Selection */
public void setC_PaySelection_ID (int C_PaySelection_ID)
{
if (C_PaySelection_ID < 1) throw new IllegalArgumentException ("C_PaySelection_ID is mandatory.");
set_ValueNoCheck ("C_PaySelection_ID", new Integer(C_PaySelection_ID));
}
/** Get Payment Selection.
@return Payment Selection */
public int getC_PaySelection_ID() 
{
Integer ii = (Integer)get_Value("C_PaySelection_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Set Difference.
@param DifferenceAmt Difference Amount */
public void setDifferenceAmt (BigDecimal DifferenceAmt)
{
if (DifferenceAmt == null) throw new IllegalArgumentException ("DifferenceAmt is mandatory.");
set_ValueNoCheck ("DifferenceAmt", DifferenceAmt);
}
/** Get Difference.
@return Difference Amount */
public BigDecimal getDifferenceAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("DifferenceAmt");
if (bd == null) return Env.ZERO;
return bd;
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
set_Value ("IsManual", new Boolean(IsManual));
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
/** Set Sales Transaction.
@param IsSOTrx This is a Sales Transaction */
public void setIsSOTrx (boolean IsSOTrx)
{
set_Value ("IsSOTrx", new Boolean(IsSOTrx));
}
/** Get Sales Transaction.
@return This is a Sales Transaction */
public boolean isSOTrx() 
{
Object oo = get_Value("IsSOTrx");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", new Integer(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Open Amount.
@param OpenAmt Open item amount */
public void setOpenAmt (BigDecimal OpenAmt)
{
if (OpenAmt == null) throw new IllegalArgumentException ("OpenAmt is mandatory.");
set_ValueNoCheck ("OpenAmt", OpenAmt);
}
/** Get Open Amount.
@return Open item amount */
public BigDecimal getOpenAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OpenAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Payment amount.
@param PayAmt Amount being paid */
public void setPayAmt (BigDecimal PayAmt)
{
if (PayAmt == null) throw new IllegalArgumentException ("PayAmt is mandatory.");
set_Value ("PayAmt", PayAmt);
}
/** Get Payment amount.
@return Amount being paid */
public BigDecimal getPayAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("PayAmt");
if (bd == null) return Env.ZERO;
return bd;
}

/** PaymentRule AD_Reference_ID=195 */
public static final int PAYMENTRULE_AD_Reference_ID=195;
/** Cash = B */
public static final String PAYMENTRULE_Cash = "B";
/** Direct Debit = D */
public static final String PAYMENTRULE_DirectDebit = "D";
/** Credit Card = K */
public static final String PAYMENTRULE_CreditCard = "K";
/** On Credit = P */
public static final String PAYMENTRULE_OnCredit = "P";
/** Check = S */
public static final String PAYMENTRULE_Check = "S";
/** Direct Deposit = T */
public static final String PAYMENTRULE_DirectDeposit = "T";
/** Set Payment Rule.
@param PaymentRule How you pay the invoice */
public void setPaymentRule (String PaymentRule)
{
if (PaymentRule == null) throw new IllegalArgumentException ("PaymentRule is mandatory");
if (PaymentRule.equals("B") || PaymentRule.equals("D") || PaymentRule.equals("K") || PaymentRule.equals("P") || PaymentRule.equals("S") || PaymentRule.equals("T"));
 else throw new IllegalArgumentException ("PaymentRule Invalid value - " + PaymentRule + " - Reference_ID=195 - B - D - K - P - S - T");
if (PaymentRule.length() > 1)
{
log.warning("Length > 1 - truncated");
PaymentRule = PaymentRule.substring(0,0);
}
set_Value ("PaymentRule", PaymentRule);
}
/** Get Payment Rule.
@return How you pay the invoice */
public String getPaymentRule() 
{
return (String)get_Value("PaymentRule");
}
/** Set Processed.
@param Processed The document has been processed */
public void setProcessed (boolean Processed)
{
set_Value ("Processed", new Boolean(Processed));
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
}
