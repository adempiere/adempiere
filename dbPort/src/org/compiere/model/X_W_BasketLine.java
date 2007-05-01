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
/** Generated Model for W_BasketLine
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_W_BasketLine extends PO
{
/** Standard Constructor
@param ctx context
@param W_BasketLine_ID id
@param trxName transaction
*/
public X_W_BasketLine (Properties ctx, int W_BasketLine_ID, String trxName)
{
super (ctx, W_BasketLine_ID, trxName);
/** if (W_BasketLine_ID == 0)
{
setDescription (null);
setLine (0);
setPrice (Env.ZERO);
setProduct (null);
setQty (Env.ZERO);
setW_BasketLine_ID (0);
setW_Basket_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_W_BasketLine (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=549 */
public static final int Table_ID=MTable.getTable_ID("W_BasketLine");

/** TableName=W_BasketLine */
public static final String Table_Name="W_BasketLine";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"W_BasketLine");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
/** AccessLevel
@return 4 - System 
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
StringBuffer sb = new StringBuffer ("X_W_BasketLine[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description == null) throw new IllegalArgumentException ("Description is mandatory.");
if (Description.length() > 255)
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (int Line)
{
set_Value ("Line", Integer.valueOf(Line));
}
/** Get Line No.
@return Unique line for this document */
public int getLine() 
{
Integer ii = (Integer)get_Value("Line");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getLine()));
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID <= 0) set_Value ("M_Product_ID", null);
 else 
set_Value ("M_Product_ID", Integer.valueOf(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Product_ID */
public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";
/** Set Price.
@param Price Price */
public void setPrice (BigDecimal Price)
{
if (Price == null) throw new IllegalArgumentException ("Price is mandatory.");
set_Value ("Price", Price);
}
/** Get Price.
@return Price */
public BigDecimal getPrice() 
{
BigDecimal bd = (BigDecimal)get_Value("Price");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Price */
public static final String COLUMNNAME_Price = "Price";
/** Set Product.
@param Product Product */
public void setProduct (String Product)
{
if (Product == null) throw new IllegalArgumentException ("Product is mandatory.");
if (Product.length() > 40)
{
log.warning("Length > 40 - truncated");
Product = Product.substring(0,39);
}
set_Value ("Product", Product);
}
/** Get Product.
@return Product */
public String getProduct() 
{
return (String)get_Value("Product");
}
/** Column name Product */
public static final String COLUMNNAME_Product = "Product";
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
/** Set Basket Line.
@param W_BasketLine_ID Web Basket Line */
public void setW_BasketLine_ID (int W_BasketLine_ID)
{
if (W_BasketLine_ID < 1) throw new IllegalArgumentException ("W_BasketLine_ID is mandatory.");
set_ValueNoCheck ("W_BasketLine_ID", Integer.valueOf(W_BasketLine_ID));
}
/** Get Basket Line.
@return Web Basket Line */
public int getW_BasketLine_ID() 
{
Integer ii = (Integer)get_Value("W_BasketLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_BasketLine_ID */
public static final String COLUMNNAME_W_BasketLine_ID = "W_BasketLine_ID";
/** Set W_Basket_ID.
@param W_Basket_ID Web Basket */
public void setW_Basket_ID (int W_Basket_ID)
{
if (W_Basket_ID < 1) throw new IllegalArgumentException ("W_Basket_ID is mandatory.");
set_ValueNoCheck ("W_Basket_ID", Integer.valueOf(W_Basket_ID));
}
/** Get W_Basket_ID.
@return Web Basket */
public int getW_Basket_ID() 
{
Integer ii = (Integer)get_Value("W_Basket_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name W_Basket_ID */
public static final String COLUMNNAME_W_Basket_ID = "W_Basket_ID";
}
