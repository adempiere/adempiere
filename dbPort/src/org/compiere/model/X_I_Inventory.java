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
/** Generated Model for I_Inventory
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_I_Inventory extends PO
{
/** Standard Constructor
@param ctx context
@param I_Inventory_ID id
@param trxName transaction
*/
public X_I_Inventory (Properties ctx, int I_Inventory_ID, String trxName)
{
super (ctx, I_Inventory_ID, trxName);
/** if (I_Inventory_ID == 0)
{
setI_Inventory_ID (0);
setI_IsImported (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_I_Inventory (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=572 */
public static final int Table_ID=MTable.getTable_ID("I_Inventory");

/** TableName=I_Inventory */
public static final String Table_Name="I_Inventory";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"I_Inventory");

protected BigDecimal accessLevel = BigDecimal.valueOf(2);
/** AccessLevel
@return 2 - Client 
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
StringBuffer sb = new StringBuffer ("X_I_Inventory[").append(get_ID()).append("]");
return sb.toString();
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
/** Set Import Error Message.
@param I_ErrorMsg Messages generated from import process */
public void setI_ErrorMsg (String I_ErrorMsg)
{
if (I_ErrorMsg != null && I_ErrorMsg.length() > 2000)
{
log.warning("Length > 2000 - truncated");
I_ErrorMsg = I_ErrorMsg.substring(0,1999);
}
set_Value ("I_ErrorMsg", I_ErrorMsg);
}
/** Get Import Error Message.
@return Messages generated from import process */
public String getI_ErrorMsg() 
{
return (String)get_Value("I_ErrorMsg");
}
/** Set Import Inventory.
@param I_Inventory_ID Import Inventory Transactions */
public void setI_Inventory_ID (int I_Inventory_ID)
{
if (I_Inventory_ID < 1) throw new IllegalArgumentException ("I_Inventory_ID is mandatory.");
set_ValueNoCheck ("I_Inventory_ID", Integer.valueOf(I_Inventory_ID));
}
/** Get Import Inventory.
@return Import Inventory Transactions */
public int getI_Inventory_ID() 
{
Integer ii = (Integer)get_Value("I_Inventory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getI_Inventory_ID()));
}
/** Set Imported.
@param I_IsImported Has this import been processed */
public void setI_IsImported (boolean I_IsImported)
{
set_Value ("I_IsImported", Boolean.valueOf(I_IsImported));
}
/** Get Imported.
@return Has this import been processed */
public boolean isI_IsImported() 
{
Object oo = get_Value("I_IsImported");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Locator Key.
@param LocatorValue Key of the Warehouse Locator */
public void setLocatorValue (String LocatorValue)
{
if (LocatorValue != null && LocatorValue.length() > 40)
{
log.warning("Length > 40 - truncated");
LocatorValue = LocatorValue.substring(0,39);
}
set_Value ("LocatorValue", LocatorValue);
}
/** Get Locator Key.
@return Key of the Warehouse Locator */
public String getLocatorValue() 
{
return (String)get_Value("LocatorValue");
}
/** Set Lot No.
@param Lot Lot number (alphanumeric) */
public void setLot (String Lot)
{
if (Lot != null && Lot.length() > 20)
{
log.warning("Length > 20 - truncated");
Lot = Lot.substring(0,19);
}
set_Value ("Lot", Lot);
}
/** Get Lot No.
@return Lot number (alphanumeric) */
public String getLot() 
{
return (String)get_Value("Lot");
}
/** Set Phys.Inventory Line.
@param M_InventoryLine_ID Unique line in an Inventory document */
public void setM_InventoryLine_ID (int M_InventoryLine_ID)
{
if (M_InventoryLine_ID <= 0) set_Value ("M_InventoryLine_ID", null);
 else 
set_Value ("M_InventoryLine_ID", Integer.valueOf(M_InventoryLine_ID));
}
/** Get Phys.Inventory Line.
@return Unique line in an Inventory document */
public int getM_InventoryLine_ID() 
{
Integer ii = (Integer)get_Value("M_InventoryLine_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Phys.Inventory.
@param M_Inventory_ID Parameters for a Physical Inventory */
public void setM_Inventory_ID (int M_Inventory_ID)
{
if (M_Inventory_ID <= 0) set_Value ("M_Inventory_ID", null);
 else 
set_Value ("M_Inventory_ID", Integer.valueOf(M_Inventory_ID));
}
/** Get Phys.Inventory.
@return Parameters for a Physical Inventory */
public int getM_Inventory_ID() 
{
Integer ii = (Integer)get_Value("M_Inventory_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID <= 0) set_Value ("M_Locator_ID", null);
 else 
set_Value ("M_Locator_ID", Integer.valueOf(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID <= 0) set_Value ("M_Warehouse_ID", null);
 else 
set_Value ("M_Warehouse_ID", Integer.valueOf(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Movement Date.
@param MovementDate Date a product was moved in or out of inventory */
public void setMovementDate (Timestamp MovementDate)
{
set_Value ("MovementDate", MovementDate);
}
/** Get Movement Date.
@return Date a product was moved in or out of inventory */
public Timestamp getMovementDate() 
{
return (Timestamp)get_Value("MovementDate");
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
/** Set Quantity book.
@param QtyBook Book Quantity */
public void setQtyBook (BigDecimal QtyBook)
{
set_Value ("QtyBook", QtyBook);
}
/** Get Quantity book.
@return Book Quantity */
public BigDecimal getQtyBook() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyBook");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Quantity count.
@param QtyCount Counted Quantity */
public void setQtyCount (BigDecimal QtyCount)
{
set_Value ("QtyCount", QtyCount);
}
/** Get Quantity count.
@return Counted Quantity */
public BigDecimal getQtyCount() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyCount");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Serial No.
@param SerNo Product Serial Number  */
public void setSerNo (String SerNo)
{
if (SerNo != null && SerNo.length() > 20)
{
log.warning("Length > 20 - truncated");
SerNo = SerNo.substring(0,19);
}
set_Value ("SerNo", SerNo);
}
/** Get Serial No.
@return Product Serial Number  */
public String getSerNo() 
{
return (String)get_Value("SerNo");
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
set_Value ("UPC", UPC);
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
if (Value != null && Value.length() > 40)
{
log.warning("Length > 40 - truncated");
Value = Value.substring(0,39);
}
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Set Warehouse Key.
@param WarehouseValue Key of the Warehouse */
public void setWarehouseValue (String WarehouseValue)
{
if (WarehouseValue != null && WarehouseValue.length() > 40)
{
log.warning("Length > 40 - truncated");
WarehouseValue = WarehouseValue.substring(0,39);
}
set_Value ("WarehouseValue", WarehouseValue);
}
/** Get Warehouse Key.
@return Key of the Warehouse */
public String getWarehouseValue() 
{
return (String)get_Value("WarehouseValue");
}
/** Set Aisle (X).
@param X X dimension, e.g., Aisle */
public void setX (String X)
{
if (X != null && X.length() > 60)
{
log.warning("Length > 60 - truncated");
X = X.substring(0,59);
}
set_Value ("X", X);
}
/** Get Aisle (X).
@return X dimension, e.g., Aisle */
public String getX() 
{
return (String)get_Value("X");
}
/** Set Bin (Y).
@param Y Y dimension, e.g., Bin */
public void setY (String Y)
{
if (Y != null && Y.length() > 60)
{
log.warning("Length > 60 - truncated");
Y = Y.substring(0,59);
}
set_Value ("Y", Y);
}
/** Get Bin (Y).
@return Y dimension, e.g., Bin */
public String getY() 
{
return (String)get_Value("Y");
}
/** Set Level (Z).
@param Z Z dimension, e.g., Level */
public void setZ (String Z)
{
if (Z != null && Z.length() > 60)
{
log.warning("Length > 60 - truncated");
Z = Z.substring(0,59);
}
set_Value ("Z", Z);
}
/** Get Level (Z).
@return Z dimension, e.g., Level */
public String getZ() 
{
return (String)get_Value("Z");
}
}
