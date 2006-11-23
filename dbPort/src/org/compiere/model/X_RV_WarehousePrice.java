/******************************************************************************
 * Product: Compiere ERP & CRM Smart Business Solution                        *
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
/** Generated Model for RV_WarehousePrice
 *  @author Jorg Janke (generated) 
 *  @version Release 2.6.0a - $Id$ */
public class X_RV_WarehousePrice extends PO
{
/** Standard Constructor
@param ctx context
@param RV_WarehousePrice_ID id
@param trxName transaction
*/
public X_RV_WarehousePrice (Properties ctx, int RV_WarehousePrice_ID, String trxName)
{
super (ctx, RV_WarehousePrice_ID, trxName);
/** if (RV_WarehousePrice_ID == 0)
{
setC_UOM_ID (0);
setM_PriceList_Version_ID (0);
setM_Product_ID (0);
setM_Warehouse_ID (0);
setName (null);
setValue (null);
setWarehouseName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_RV_WarehousePrice (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=639 */
public static final int Table_ID=639;

/** TableName=RV_WarehousePrice */
public static final String Table_Name="RV_WarehousePrice";

protected static KeyNamePair Model = new KeyNamePair(639,"RV_WarehousePrice");

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
StringBuffer sb = new StringBuffer ("X_RV_WarehousePrice[").append(get_ID()).append("]");
return sb.toString();
}
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
set_ValueNoCheck ("C_UOM_ID", new Integer(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Instance Attribute.
@param IsInstanceAttribute The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date) */
public void setIsInstanceAttribute (boolean IsInstanceAttribute)
{
set_ValueNoCheck ("IsInstanceAttribute", new Boolean(IsInstanceAttribute));
}
/** Get Instance Attribute.
@return The product attribute is specific to the instance (like Serial No, Lot or Guarantee Date) */
public boolean isInstanceAttribute() 
{
Object oo = get_Value("IsInstanceAttribute");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Price List Version.
@param M_PriceList_Version_ID Identifies a unique instance of a Price List */
public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
{
if (M_PriceList_Version_ID < 1) throw new IllegalArgumentException ("M_PriceList_Version_ID is mandatory.");
set_ValueNoCheck ("M_PriceList_Version_ID", new Integer(M_PriceList_Version_ID));
}
/** Get Price List Version.
@return Identifies a unique instance of a Price List */
public int getM_PriceList_Version_ID() 
{
Integer ii = (Integer)get_Value("M_PriceList_Version_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", new Integer(M_Product_ID));
}
/** Get Product.
@return Product, Service, Item */
public int getM_Product_ID() 
{
Integer ii = (Integer)get_Value("M_Product_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_ValueNoCheck ("M_Warehouse_ID", new Integer(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Margin %.
@param Margin Margin for a product as a percentage */
public void setMargin (BigDecimal Margin)
{
set_ValueNoCheck ("Margin", Margin);
}
/** Get Margin %.
@return Margin for a product as a percentage */
public BigDecimal getMargin() 
{
BigDecimal bd = (BigDecimal)get_Value("Margin");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_ValueNoCheck ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Set Limit Price.
@param PriceLimit Lowest price for a product */
public void setPriceLimit (BigDecimal PriceLimit)
{
set_ValueNoCheck ("PriceLimit", PriceLimit);
}
/** Get Limit Price.
@return Lowest price for a product */
public BigDecimal getPriceLimit() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceLimit");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set List Price.
@param PriceList List Price */
public void setPriceList (BigDecimal PriceList)
{
set_ValueNoCheck ("PriceList", PriceList);
}
/** Get List Price.
@return List Price */
public BigDecimal getPriceList() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceList");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Standard Price.
@param PriceStd Standard Price */
public void setPriceStd (BigDecimal PriceStd)
{
set_ValueNoCheck ("PriceStd", PriceStd);
}
/** Get Standard Price.
@return Standard Price */
public BigDecimal getPriceStd() 
{
BigDecimal bd = (BigDecimal)get_Value("PriceStd");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Available Quantity.
@param QtyAvailable Available Quantity (On Hand - Reserved) */
public void setQtyAvailable (BigDecimal QtyAvailable)
{
set_ValueNoCheck ("QtyAvailable", QtyAvailable);
}
/** Get Available Quantity.
@return Available Quantity (On Hand - Reserved) */
public BigDecimal getQtyAvailable() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyAvailable");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set On Hand Quantity.
@param QtyOnHand On Hand Quantity */
public void setQtyOnHand (BigDecimal QtyOnHand)
{
set_ValueNoCheck ("QtyOnHand", QtyOnHand);
}
/** Get On Hand Quantity.
@return On Hand Quantity */
public BigDecimal getQtyOnHand() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyOnHand");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Ordered Quantity.
@param QtyOrdered Ordered Quantity */
public void setQtyOrdered (BigDecimal QtyOrdered)
{
set_ValueNoCheck ("QtyOrdered", QtyOrdered);
}
/** Get Ordered Quantity.
@return Ordered Quantity */
public BigDecimal getQtyOrdered() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyOrdered");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Reserved Quantity.
@param QtyReserved Reserved Quantity */
public void setQtyReserved (BigDecimal QtyReserved)
{
set_ValueNoCheck ("QtyReserved", QtyReserved);
}
/** Get Reserved Quantity.
@return Reserved Quantity */
public BigDecimal getQtyReserved() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyReserved");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set SKU.
@param SKU Stock Keeping Unit */
public void setSKU (String SKU)
{
if (SKU != null && SKU.length() > 30)
{
log.warning("Length > 30 - truncated");
SKU = SKU.substring(0,29);
}
set_ValueNoCheck ("SKU", SKU);
}
/** Get SKU.
@return Stock Keeping Unit */
public String getSKU() 
{
return (String)get_Value("SKU");
}
/** Set Symbol.
@param UOMSymbol Symbol for a Unit of Measure */
public void setUOMSymbol (String UOMSymbol)
{
if (UOMSymbol != null && UOMSymbol.length() > 10)
{
log.warning("Length > 10 - truncated");
UOMSymbol = UOMSymbol.substring(0,9);
}
set_ValueNoCheck ("UOMSymbol", UOMSymbol);
}
/** Get Symbol.
@return Symbol for a Unit of Measure */
public String getUOMSymbol() 
{
return (String)get_Value("UOMSymbol");
}
/** Set UPC/EAN.
@param UPC Bar Code (Universal Product Code or its superset European Article Number) */
public void setUPC (String UPC)
{
if (UPC != null && UPC.length() > 30)
{
log.warning("Length > 30 - truncated");
UPC = UPC.substring(0,29);
}
set_ValueNoCheck ("UPC", UPC);
}
/** Get UPC/EAN.
@return Bar Code (Universal Product Code or its superset European Article Number) */
public String getUPC() 
{
return (String)get_Value("UPC");
}
/** Set Search Key.
@param Value Search key for the record in the format required - must be unique */
public void setValue (String Value)
{
if (Value == null) throw new IllegalArgumentException ("Value is mandatory.");
if (Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_ValueNoCheck ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Set Warehouse.
@param WarehouseName Warehouse Name */
public void setWarehouseName (String WarehouseName)
{
if (WarehouseName == null) throw new IllegalArgumentException ("WarehouseName is mandatory.");
if (WarehouseName.length() > 60)
{
log.warning("Length > 60 - truncated");
WarehouseName = WarehouseName.substring(0,59);
}
set_ValueNoCheck ("WarehouseName", WarehouseName);
}
/** Get Warehouse.
@return Warehouse Name */
public String getWarehouseName() 
{
return (String)get_Value("WarehouseName");
}
}
