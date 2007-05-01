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
/** Generated Model for C_UOM
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_UOM extends PO
{
/** Standard Constructor
@param ctx context
@param C_UOM_ID id
@param trxName transaction
*/
public X_C_UOM (Properties ctx, int C_UOM_ID, String trxName)
{
super (ctx, C_UOM_ID, trxName);
/** if (C_UOM_ID == 0)
{
setC_UOM_ID (0);
setCostingPrecision (0);
setIsDefault (false);
setName (null);
setStdPrecision (0);
setX12DE355 (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_UOM (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=146 */
public static final int Table_ID=MTable.getTable_ID("C_UOM");

/** TableName=C_UOM */
public static final String Table_Name="C_UOM";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_UOM");

protected BigDecimal accessLevel = BigDecimal.valueOf(6);
/** AccessLevel
@return 6 - System - Client 
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
StringBuffer sb = new StringBuffer ("X_C_UOM[").append(get_ID()).append("]");
return sb.toString();
}
/** Set UOM.
@param C_UOM_ID Unit of Measure */
public void setC_UOM_ID (int C_UOM_ID)
{
if (C_UOM_ID < 1) throw new IllegalArgumentException ("C_UOM_ID is mandatory.");
set_ValueNoCheck ("C_UOM_ID", Integer.valueOf(C_UOM_ID));
}
/** Get UOM.
@return Unit of Measure */
public int getC_UOM_ID() 
{
Integer ii = (Integer)get_Value("C_UOM_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_UOM_ID */
public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";
/** Set Costing Precision.
@param CostingPrecision Rounding used costing calculations */
public void setCostingPrecision (int CostingPrecision)
{
set_Value ("CostingPrecision", Integer.valueOf(CostingPrecision));
}
/** Get Costing Precision.
@return Rounding used costing calculations */
public int getCostingPrecision() 
{
Integer ii = (Integer)get_Value("CostingPrecision");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CostingPrecision */
public static final String COLUMNNAME_CostingPrecision = "CostingPrecision";
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
/** Column name IsDefault */
public static final String COLUMNNAME_IsDefault = "IsDefault";
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
/** Set Standard Precision.
@param StdPrecision Rule for rounding  calculated amounts */
public void setStdPrecision (int StdPrecision)
{
set_Value ("StdPrecision", Integer.valueOf(StdPrecision));
}
/** Get Standard Precision.
@return Rule for rounding  calculated amounts */
public int getStdPrecision() 
{
Integer ii = (Integer)get_Value("StdPrecision");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name StdPrecision */
public static final String COLUMNNAME_StdPrecision = "StdPrecision";
/** Set Symbol.
@param UOMSymbol Symbol for a Unit of Measure */
public void setUOMSymbol (String UOMSymbol)
{
if (UOMSymbol != null && UOMSymbol.length() > 10)
{
log.warning("Length > 10 - truncated");
UOMSymbol = UOMSymbol.substring(0,9);
}
set_Value ("UOMSymbol", UOMSymbol);
}
/** Get Symbol.
@return Symbol for a Unit of Measure */
public String getUOMSymbol() 
{
return (String)get_Value("UOMSymbol");
}
/** Column name UOMSymbol */
public static final String COLUMNNAME_UOMSymbol = "UOMSymbol";
/** Set UOM Code.
@param X12DE355 UOM EDI X12 Code */
public void setX12DE355 (String X12DE355)
{
if (X12DE355 == null) throw new IllegalArgumentException ("X12DE355 is mandatory.");
if (X12DE355.length() > 4)
{
log.warning("Length > 4 - truncated");
X12DE355 = X12DE355.substring(0,3);
}
set_Value ("X12DE355", X12DE355);
}
/** Get UOM Code.
@return UOM EDI X12 Code */
public String getX12DE355() 
{
return (String)get_Value("X12DE355");
}
/** Column name X12DE355 */
public static final String COLUMNNAME_X12DE355 = "X12DE355";
}
