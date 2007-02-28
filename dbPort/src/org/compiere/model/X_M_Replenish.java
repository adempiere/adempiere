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
/** Generated Model for M_Replenish
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_M_Replenish extends PO
{
/** Standard Constructor
@param ctx context
@param M_Replenish_ID id
@param trxName transaction
*/
public X_M_Replenish (Properties ctx, int M_Replenish_ID, String trxName)
{
super (ctx, M_Replenish_ID, trxName);
/** if (M_Replenish_ID == 0)
{
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
public X_M_Replenish (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=249 */
public static final int Table_ID=MTable.getTable_ID("M_Replenish");

/** TableName=M_Replenish */
public static final String Table_Name="M_Replenish";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Replenish");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
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
StringBuffer sb = new StringBuffer ("X_M_Replenish[").append(get_ID()).append("]");
return sb.toString();
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
/** Column name Level_Max */
public static final String COLUMNNAME_Level_Max = "Level_Max";
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
/** Column name Level_Min */
public static final String COLUMNNAME_Level_Min = "Level_Min";
/** Set Product.
@param M_Product_ID Product, Service, Item */
public void setM_Product_ID (int M_Product_ID)
{
if (M_Product_ID < 1) throw new IllegalArgumentException ("M_Product_ID is mandatory.");
set_ValueNoCheck ("M_Product_ID", Integer.valueOf(M_Product_ID));
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

/** M_WarehouseSource_ID AD_Reference_ID=197 */
public static final int M_WAREHOUSESOURCE_ID_AD_Reference_ID=197;
/** Set Source Warehouse.
@param M_WarehouseSource_ID Optional Warehouse to replenish from */
public void setM_WarehouseSource_ID (int M_WarehouseSource_ID)
{
if (M_WarehouseSource_ID <= 0) set_Value ("M_WarehouseSource_ID", null);
 else 
set_Value ("M_WarehouseSource_ID", Integer.valueOf(M_WarehouseSource_ID));
}
/** Get Source Warehouse.
@return Optional Warehouse to replenish from */
public int getM_WarehouseSource_ID() 
{
Integer ii = (Integer)get_Value("M_WarehouseSource_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_WarehouseSource_ID */
public static final String COLUMNNAME_M_WarehouseSource_ID = "M_WarehouseSource_ID";
/** Set Warehouse.
@param M_Warehouse_ID Storage Warehouse and Service Point */
public void setM_Warehouse_ID (int M_Warehouse_ID)
{
if (M_Warehouse_ID < 1) throw new IllegalArgumentException ("M_Warehouse_ID is mandatory.");
set_ValueNoCheck ("M_Warehouse_ID", Integer.valueOf(M_Warehouse_ID));
}
/** Get Warehouse.
@return Storage Warehouse and Service Point */
public int getM_Warehouse_ID() 
{
Integer ii = (Integer)get_Value("M_Warehouse_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name M_Warehouse_ID */
public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

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
/** Column name ReplenishType */
public static final String COLUMNNAME_ReplenishType = "ReplenishType";
}
