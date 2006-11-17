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
/** Generated Model for T_Replenish
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:56:03.75 */
public class X_T_Replenish extends PO
{
/** Standard Constructor
@param ctx context
@param T_Replenish_ID id
@param trxName transaction
*/
public X_T_Replenish (Properties ctx, int T_Replenish_ID, String trxName)
{
super (ctx, T_Replenish_ID, trxName);
/** if (T_Replenish_ID == 0)
{
setAD_PInstance_ID (0);
setC_BPartner_ID (0);
setLevel_Max (Env.ZERO);
setLevel_Min (Env.ZERO);
setM_Product_ID (0);
setM_Warehouse_ID (0);
setReplenishType (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_T_Replenish (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=364 */
public static final int Table_ID=364;

/** TableName=T_Replenish */
public static final String Table_Name="T_Replenish";

protected static KeyNamePair Model = new KeyNamePair(364,"T_Replenish");

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
StringBuffer sb = new StringBuffer ("X_T_Replenish[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Process Instance.
@param AD_PInstance_ID Instance of the process */
public void setAD_PInstance_ID (int AD_PInstance_ID)
{
if (AD_PInstance_ID < 1) throw new IllegalArgumentException ("AD_PInstance_ID is mandatory.");
set_ValueNoCheck ("AD_PInstance_ID", new Integer(AD_PInstance_ID));
}
/** Get Process Instance.
@return Instance of the process */
public int getAD_PInstance_ID() 
{
Integer ii = (Integer)get_Value("AD_PInstance_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_Value ("C_BPartner_ID", new Integer(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Document Type.
@param C_DocType_ID Document type or rules */
public void setC_DocType_ID (int C_DocType_ID)
{
if (C_DocType_ID <= 0) set_Value ("C_DocType_ID", null);
 else 
set_Value ("C_DocType_ID", new Integer(C_DocType_ID));
}
/** Get Document Type.
@return Document type or rules */
public int getC_DocType_ID() 
{
Integer ii = (Integer)get_Value("C_DocType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Maximum Level.
@param Level_Max Maximum Inventory level for this product */
public void setLevel_Max (BigDecimal Level_Max)
{
if (Level_Max == null) throw new IllegalArgumentException ("Level_Max is mandatory.");
set_Value ("Level_Max", Level_Max);
}
/** Get Maximum Level.
@return Maximum Inventory level for this product */
public BigDecimal getLevel_Max() 
{
BigDecimal bd = (BigDecimal)get_Value("Level_Max");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Minimum Level.
@param Level_Min Minimum Inventory level for this product */
public void setLevel_Min (BigDecimal Level_Min)
{
if (Level_Min == null) throw new IllegalArgumentException ("Level_Min is mandatory.");
set_Value ("Level_Min", Level_Min);
}
/** Get Minimum Level.
@return Minimum Inventory level for this product */
public BigDecimal getLevel_Min() 
{
BigDecimal bd = (BigDecimal)get_Value("Level_Min");
if (bd == null) return Env.ZERO;
return bd;
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

/** M_WarehouseSource_ID AD_Reference_ID=197 */
public static final int M_WAREHOUSESOURCE_ID_AD_Reference_ID=197;
/** Set Source Warehouse.
@param M_WarehouseSource_ID Optional Warehouse to replenish from */
public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
{
if (M_WarehouseSource_ID <= 0) set_Value ("M_WarehouseSource_ID", null);
 else 
set_Value ("M_WarehouseSource_ID", new Integer(M_WarehouseSource_ID));
}
/** Get Source Warehouse.
@return Optional Warehouse to replenish from */
public int getM_WarehouseSource_ID() 
{
Integer ii = (Integer)get_Value("M_WarehouseSource_ID");
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
/** Set Minimum Order Qty.
@param Order_Min Minimum order quantity in UOM */
public void setOrder_Min (BigDecimal Order_Min)
{
set_Value ("Order_Min", Order_Min);
}
/** Get Minimum Order Qty.
@return Minimum order quantity in UOM */
public BigDecimal getOrder_Min() 
{
BigDecimal bd = (BigDecimal)get_Value("Order_Min");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Order Pack Qty.
@param Order_Pack Package order size in UOM (e.g. order set of 5 units) */
public void setOrder_Pack (BigDecimal Order_Pack)
{
set_Value ("Order_Pack", Order_Pack);
}
/** Get Order Pack Qty.
@return Package order size in UOM (e.g. order set of 5 units) */
public BigDecimal getOrder_Pack() 
{
BigDecimal bd = (BigDecimal)get_Value("Order_Pack");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set On Hand Quantity.
@param QtyOnHand On Hand Quantity */
public void setQtyOnHand (BigDecimal QtyOnHand)
{
set_Value ("QtyOnHand", QtyOnHand);
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
set_Value ("QtyOrdered", QtyOrdered);
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
set_Value ("QtyReserved", QtyReserved);
}
/** Get Reserved Quantity.
@return Reserved Quantity */
public BigDecimal getQtyReserved() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyReserved");
if (bd == null) return Env.ZERO;
return bd;
}
/** Set Quantity to Order.
@param QtyToOrder Quantity to Order */
public void setQtyToOrder (BigDecimal QtyToOrder)
{
set_Value ("QtyToOrder", QtyToOrder);
}
/** Get Quantity to Order.
@return Quantity to Order */
public BigDecimal getQtyToOrder() 
{
BigDecimal bd = (BigDecimal)get_Value("QtyToOrder");
if (bd == null) return Env.ZERO;
return bd;
}

/** ReplenishType AD_Reference_ID=164 */
public static final int REPLENISHTYPE_AD_Reference_ID=164;
/** Manual = 0 */
public static final String REPLENISHTYPE_Manual = "0";
/** Reorder below Minimum Level = 1 */
public static final String REPLENISHTYPE_ReorderBelowMinimumLevel = "1";
/** Maintain Maximum Level = 2 */
public static final String REPLENISHTYPE_MaintainMaximumLevel = "2";
/** Custom = 9 */
public static final String REPLENISHTYPE_Custom = "9";
/** Set Replenish Type.
@param ReplenishType Method for re-ordering a product */
public void setReplenishType (String ReplenishType)
{
if (ReplenishType == null) throw new IllegalArgumentException ("ReplenishType is mandatory");
if (ReplenishType.equals("0") || ReplenishType.equals("1") || ReplenishType.equals("2") || ReplenishType.equals("9"));
 else throw new IllegalArgumentException ("ReplenishType Invalid value - " + ReplenishType + " - Reference_ID=164 - 0 - 1 - 2 - 9");
if (ReplenishType.length() > 1)
{
log.warning("Length > 1 - truncated");
ReplenishType = ReplenishType.substring(0,0);
}
set_Value ("ReplenishType", ReplenishType);
}
/** Get Replenish Type.
@return Method for re-ordering a product */
public String getReplenishType() 
{
return (String)get_Value("ReplenishType");
}

/** ReplenishmentCreate AD_Reference_ID=329 */
public static final int REPLENISHMENTCREATE_AD_Reference_ID=329;
/** Inventory Move = MMM */
public static final String REPLENISHMENTCREATE_InventoryMove = "MMM";
/** Purchase Order = POO */
public static final String REPLENISHMENTCREATE_PurchaseOrder = "POO";
/** Requisition = POR */
public static final String REPLENISHMENTCREATE_Requisition = "POR";
/** Set Create.
@param ReplenishmentCreate Create from Replenishment */
public void setReplenishmentCreate (String ReplenishmentCreate)
{
if (ReplenishmentCreate == null) throw new IllegalArgumentException ("ReplenishmentCreate is mandatory");
if (ReplenishmentCreate == null || ReplenishmentCreate.equals("MMM") || ReplenishmentCreate.equals("POO") || ReplenishmentCreate.equals("POR"));
 else throw new IllegalArgumentException ("ReplenishmentCreate Invalid value - " + ReplenishmentCreate + " - Reference_ID=329 - MMM - POO - POR");
if (ReplenishmentCreate != null && ReplenishmentCreate.length() > 1)
{
log.warning("Length > 1 - truncated");
ReplenishmentCreate = ReplenishmentCreate.substring(0,0);
}
set_Value ("ReplenishmentCreate", ReplenishmentCreate);
}
/** Get Create.
@return Create from Replenishment */
public String getReplenishmentCreate() 
{
return (String)get_Value("ReplenishmentCreate");
}
}
