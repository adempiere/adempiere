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
/** Generated Model for M_Locator
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_M_Locator extends PO
{
/** Standard Constructor
@param ctx context
@param M_Locator_ID id
@param trxName transaction
*/
public X_M_Locator (Properties ctx, int M_Locator_ID, String trxName)
{
super (ctx, M_Locator_ID, trxName);
/** if (M_Locator_ID == 0)
{
setIsDefault (false);
setM_Locator_ID (0);
setM_Warehouse_ID (0);
setPriorityNo (0);	// 50
setValue (null);
setX (null);
setY (null);
setZ (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_M_Locator (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=207 */
public static final int Table_ID=MTable.getTable_ID("M_Locator");

/** TableName=M_Locator */
public static final String Table_Name="M_Locator";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"M_Locator");

protected BigDecimal accessLevel = new BigDecimal(7);
/** AccessLevel
@return 7 - System - Client - Org 
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
StringBuffer sb = new StringBuffer ("X_M_Locator[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Locator.
@param M_Locator_ID Warehouse Locator */
public void setM_Locator_ID (int M_Locator_ID)
{
if (M_Locator_ID < 1) throw new IllegalArgumentException ("M_Locator_ID is mandatory.");
set_ValueNoCheck ("M_Locator_ID", Integer.valueOf(M_Locator_ID));
}
/** Get Locator.
@return Warehouse Locator */
public int getM_Locator_ID() 
{
Integer ii = (Integer)get_Value("M_Locator_ID");
if (ii == null) return 0;
return ii.intValue();
}
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
/** Set Relative Priority.
@param PriorityNo Where inventory should be picked from first */
public void setPriorityNo (int PriorityNo)
{
set_Value ("PriorityNo", Integer.valueOf(PriorityNo));
}
/** Get Relative Priority.
@return Where inventory should be picked from first */
public int getPriorityNo() 
{
Integer ii = (Integer)get_Value("PriorityNo");
if (ii == null) return 0;
return ii.intValue();
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
set_Value ("Value", Value);
}
/** Get Search Key.
@return Search key for the record in the format required - must be unique */
public String getValue() 
{
return (String)get_Value("Value");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getValue());
}
/** Set Aisle (X).
@param X X dimension, e.g., Aisle */
public void setX (String X)
{
if (X == null) throw new IllegalArgumentException ("X is mandatory.");
if (X.length() > 60)
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
if (Y == null) throw new IllegalArgumentException ("Y is mandatory.");
if (Y.length() > 60)
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
if (Z == null) throw new IllegalArgumentException ("Z is mandatory.");
if (Z.length() > 60)
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
