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
/** Generated Model for C_RfQLineQty
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_RfQLineQty extends PO
{
/** Standard Constructor
@param ctx context
@param C_RfQLineQty_ID id
@param trxName transaction
*/
public X_C_RfQLineQty (Properties ctx, int C_RfQLineQty_ID, String trxName)
{
super (ctx, C_RfQLineQty_ID, trxName);
/** if (C_RfQLineQty_ID == 0)
{
setBenchmarkPrice (Env.ZERO);
setC_RfQLineQty_ID (0);
setC_RfQLine_ID (0);
setC_UOM_ID (0);
setIsOfferQty (false);
setIsPurchaseQty (false);
setIsRfQQty (true);	// Y
setQty (Env.ZERO);	// 1
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_RfQLineQty (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=675 */
public static final int Table_ID=MTable.getTable_ID("C_RfQLineQty");

/** TableName=C_RfQLineQty */
public static final String Table_Name="C_RfQLineQty";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_RfQLineQty");

protected BigDecimal accessLevel = BigDecimal.valueOf(1);
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
StringBuffer sb = new StringBuffer ("X_C_RfQLineQty[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Benchmark Price.
@param BenchmarkPrice Price to compare responses to */
public void setBenchmarkPrice (BigDecimal BenchmarkPrice)
{
if (BenchmarkPrice == null) throw new IllegalArgumentException ("BenchmarkPrice is mandatory.");
set_Value ("BenchmarkPrice", BenchmarkPrice);
}
/** Get Benchmark Price.
@return Price to compare responses to */
public BigDecimal getBenchmarkPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("BenchmarkPrice");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name BenchmarkPrice */
public static final String COLUMNNAME_BenchmarkPrice = "BenchmarkPrice";
/** Set Best Response Amount.
@param BestResponseAmt Best Response Amount */
public void setBestResponseAmt (BigDecimal BestResponseAmt)
{
set_Value ("BestResponseAmt", BestResponseAmt);
}
/** Get Best Response Amount.
@return Best Response Amount */
public BigDecimal getBestResponseAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("BestResponseAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name BestResponseAmt */
public static final String COLUMNNAME_BestResponseAmt = "BestResponseAmt";
/** Set RfQ Line Quantity.
@param C_RfQLineQty_ID Request for Quotation Line Quantity */
public void setC_RfQLineQty_ID (int C_RfQLineQty_ID)
{
if (C_RfQLineQty_ID < 1) throw new IllegalArgumentException ("C_RfQLineQty_ID is mandatory.");
set_ValueNoCheck ("C_RfQLineQty_ID", Integer.valueOf(C_RfQLineQty_ID));
}
/** Get RfQ Line Quantity.
@return Request for Quotation Line Quantity */
public int getC_RfQLineQty_ID() 
{
Integer ii = (Integer)get_Value("C_RfQLineQty_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RfQLineQty_ID */
public static final String COLUMNNAME_C_RfQLineQty_ID = "C_RfQLineQty_ID";
/** Set RfQ Line.
@param C_RfQLine_ID Request for Quotation Line */
public void setC_RfQLine_ID (int C_RfQLine_ID)
{
if (C_RfQLine_ID < 1) throw new IllegalArgumentException ("C_RfQLine_ID is mandatory.");
set_ValueNoCheck ("C_RfQLine_ID", Integer.valueOf(C_RfQLine_ID));
}
/** Get RfQ Line.
@return Request for Quotation Line */
public int getC_RfQLine_ID() 
{
Integer ii = (Integer)get_Value("C_RfQLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_RfQLine_ID */
public static final String COLUMNNAME_C_RfQLine_ID = "C_RfQLine_ID";
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
set_Value ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_UOM_ID()));
}
/** Column name C_UOM_ID */
public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
/** Set Offer Quantity.
@param IsOfferQty This quantity is used in the Offer to the Customer */
public void setIsOfferQty (boolean IsOfferQty)
{
set_Value ("IsOfferQty", Boolean.valueOf(IsOfferQty));
}
/** Get Offer Quantity.
@return This quantity is used in the Offer to the Customer */
public boolean isOfferQty() 
{
Object oo = get_Value("IsOfferQty");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsOfferQty */
public static final String COLUMNNAME_IsOfferQty = "IsOfferQty";
/** Set Purchase Quantity.
@param IsPurchaseQty This quantity is used in the Purchase Order to the Supplier */
public void setIsPurchaseQty (boolean IsPurchaseQty)
{
set_Value ("IsPurchaseQty", Boolean.valueOf(IsPurchaseQty));
}
/** Get Purchase Quantity.
@return This quantity is used in the Purchase Order to the Supplier */
public boolean isPurchaseQty() 
{
Object oo = get_Value("IsPurchaseQty");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsPurchaseQty */
public static final String COLUMNNAME_IsPurchaseQty = "IsPurchaseQty";
/** Set RfQ Quantity.
@param IsRfQQty The quantity is used when generating RfQ Responses */
public void setIsRfQQty (boolean IsRfQQty)
{
set_Value ("IsRfQQty", Boolean.valueOf(IsRfQQty));
}
/** Get RfQ Quantity.
@return The quantity is used when generating RfQ Responses */
public boolean isRfQQty() 
{
Object oo = get_Value("IsRfQQty");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsRfQQty */
public static final String COLUMNNAME_IsRfQQty = "IsRfQQty";
/** Set Margin %.
@param Margin Margin for a product as a percentage */
public void setMargin (BigDecimal Margin)
{
set_Value ("Margin", Margin);
}
/** Get Margin %.
@return Margin for a product as a percentage */
public BigDecimal getMargin() 
{
BigDecimal bd = (BigDecimal)get_Value("Margin");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Margin */
public static final String COLUMNNAME_Margin = "Margin";
/** Set Offer Amount.
@param OfferAmt Amount of the Offer */
public void setOfferAmt (BigDecimal OfferAmt)
{
set_Value ("OfferAmt", OfferAmt);
}
/** Get Offer Amount.
@return Amount of the Offer */
public BigDecimal getOfferAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("OfferAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name OfferAmt */
public static final String COLUMNNAME_OfferAmt = "OfferAmt";
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
if (Qty == null) throw new IllegalArgumentException ("Qty is mandatory.");
set_Value ("Qty", Qty);
}
/** Get Quantity.
@return Quantity */
public BigDecimal getQty() 
{
BigDecimal bd = (BigDecimal)get_Value("Qty");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Qty */
public static final String COLUMNNAME_Qty = "Qty";
}
