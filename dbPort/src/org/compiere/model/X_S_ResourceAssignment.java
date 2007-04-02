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
/** Generated Model for S_ResourceAssignment
 *  @author Adempiere (generated) 
 *  @version Release 3.1.6 - $Id$ */
public class X_S_ResourceAssignment extends PO
{
/** Standard Constructor
@param ctx context
@param S_ResourceAssignment_ID id
@param trxName transaction
*/
public X_S_ResourceAssignment (Properties ctx, int S_ResourceAssignment_ID, String trxName)
{
super (ctx, S_ResourceAssignment_ID, trxName);
/** if (S_ResourceAssignment_ID == 0)
{
setAssignDateFrom (new Timestamp(System.currentTimeMillis()));
setIsConfirmed (false);
setName (null);
setS_ResourceAssignment_ID (0);
setS_Resource_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_S_ResourceAssignment (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=485 */
public static final int Table_ID=MTable.getTable_ID("S_ResourceAssignment");

/** TableName=S_ResourceAssignment */
public static final String Table_Name="S_ResourceAssignment";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"S_ResourceAssignment");

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
StringBuffer sb = new StringBuffer ("X_S_ResourceAssignment[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Assign From.
@param AssignDateFrom Assign resource from */
public void setAssignDateFrom (Timestamp AssignDateFrom)
{
if (AssignDateFrom == null) throw new IllegalArgumentException ("AssignDateFrom is mandatory.");
set_ValueNoCheck ("AssignDateFrom", AssignDateFrom);
}
/** Get Assign From.
@return Assign resource from */
public Timestamp getAssignDateFrom() 
{
return (Timestamp)get_Value("AssignDateFrom");
}
/** Column name AssignDateFrom */
public static final String COLUMNNAME_AssignDateFrom = "AssignDateFrom";
/** Set Assign To.
@param AssignDateTo Assign resource until */
public void setAssignDateTo (Timestamp AssignDateTo)
{
set_ValueNoCheck ("AssignDateTo", AssignDateTo);
}
/** Get Assign To.
@return Assign resource until */
public Timestamp getAssignDateTo() 
{
return (Timestamp)get_Value("AssignDateTo");
}
/** Column name AssignDateTo */
public static final String COLUMNNAME_AssignDateTo = "AssignDateTo";
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
/** Set Confirmed.
@param IsConfirmed Assignment is confirmed */
public void setIsConfirmed (boolean IsConfirmed)
{
set_ValueNoCheck ("IsConfirmed", Boolean.valueOf(IsConfirmed));
}
/** Get Confirmed.
@return Assignment is confirmed */
public boolean isConfirmed() 
{
Object oo = get_Value("IsConfirmed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsConfirmed */
public static final String COLUMNNAME_IsConfirmed = "IsConfirmed";
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
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set Quantity.
@param Qty Quantity */
public void setQty (BigDecimal Qty)
{
set_ValueNoCheck ("Qty", Qty);
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
/** Set Resource Assignment.
@param S_ResourceAssignment_ID Resource Assignment */
public void setS_ResourceAssignment_ID (int S_ResourceAssignment_ID)
{
if (S_ResourceAssignment_ID < 1) throw new IllegalArgumentException ("S_ResourceAssignment_ID is mandatory.");
set_ValueNoCheck ("S_ResourceAssignment_ID", Integer.valueOf(S_ResourceAssignment_ID));
}
/** Get Resource Assignment.
@return Resource Assignment */
public int getS_ResourceAssignment_ID() 
{
Integer ii = (Integer)get_Value("S_ResourceAssignment_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name S_ResourceAssignment_ID */
public static final String COLUMNNAME_S_ResourceAssignment_ID = "S_ResourceAssignment_ID";
/** Set Resource.
@param S_Resource_ID Resource */
public void setS_Resource_ID (int S_Resource_ID)
{
if (S_Resource_ID < 1) throw new IllegalArgumentException ("S_Resource_ID is mandatory.");
set_ValueNoCheck ("S_Resource_ID", Integer.valueOf(S_Resource_ID));
}
/** Get Resource.
@return Resource */
public int getS_Resource_ID() 
{
Integer ii = (Integer)get_Value("S_Resource_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getS_Resource_ID()));
}
/** Column name S_Resource_ID */
public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";
}
