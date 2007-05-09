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
/** Generated Model for M_Warehouse
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_M_Warehouse extends PO
{
/** Standard Constructor
@param ctx context
@param M_Warehouse_ID id
@param trxName transaction
*/
public X_M_Warehouse (Properties ctx, int M_Warehouse_ID, String trxName)
{
super (ctx, M_Warehouse_ID, trxName);
/** if (M_Warehouse_ID == 0)
{
setC_Location_ID (0);
setM_Warehouse_ID (0);
setName (null);
setSeparator (null);	// *
setValue (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Warehouse (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=190 */
public static final int Table_ID=MTable.getTable_ID("M_Warehouse");

/** TableName=M_Warehouse */
public static final String Table_Name="M_Warehouse";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Warehouse");

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
StringBuffer sb = new StringBuffer ("X_M_Warehouse[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Address.
@param C_Location_ID Location or Address */
public void setC_Location_ID (int C_Location_ID)
{
if (C_Location_ID < 1) throw new IllegalArgumentException ("C_Location_ID is mandatory.");
set_Value ("C_Location_ID", Integer.valueOf(C_Location_ID));
}
/** Get Address.
@return Location or Address */
public int getC_Location_ID() 
{
Integer ii = (Integer)get_Value("C_Location_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Location_ID */
public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";
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
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";

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
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Replenishment Class.
@param ReplenishmentClass Custom class to calculate Quantity to Order */
public void setReplenishmentClass (String ReplenishmentClass)
{
if (ReplenishmentClass != null && ReplenishmentClass.length() > 60)
{
log.warning("Length > 60 - truncated");
ReplenishmentClass = ReplenishmentClass.substring(0,59);
}
set_Value ("ReplenishmentClass", ReplenishmentClass);
}
/** Get Replenishment Class.
@return Custom class to calculate Quantity to Order */
public String getReplenishmentClass() 
{
return (String)get_Value("ReplenishmentClass");
}
/** Column name ReplenishmentClass */
public static final String COLUMNNAME_ReplenishmentClass = "ReplenishmentClass";
/** Set Element Separator.
@param Separator Element Separator */
public void setSeparator (String Separator)
{
if (Separator == null) throw new IllegalArgumentException ("Separator is mandatory.");
if (Separator.length() > 1)
{
log.warning("Length > 1 - truncated");
Separator = Separator.substring(0,0);
}
set_Value ("Separator", Separator);
}
/** Get Element Separator.
@return Element Separator */
public String getSeparator() 
{
return (String)get_Value("Separator");
}
/** Column name Separator */
public static final String COLUMNNAME_Separator = "Separator";
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
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Column name Value */
public static final String COLUMNNAME_Value = "Value";
}
