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
/** Generated Model for PA_DashboardContent
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_PA_DashboardContent extends PO
{
/** Standard Constructor
@param ctx context
@param PA_DashboardContent_ID id
@param trxName transaction
*/
public X_PA_DashboardContent (Properties ctx, int PA_DashboardContent_ID, String trxName)
{
super (ctx, PA_DashboardContent_ID, trxName);
/** if (PA_DashboardContent_ID == 0)
{
setName (null);
setPA_DashboardContent_ID (0);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_PA_DashboardContent (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=PA_DashboardContent */
public static final String Table_Name="PA_DashboardContent";

/** AD_Table_ID=50010 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_PA_DashboardContent[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Window.
@param AD_Window_ID Data entry or display window */
public void setAD_Window_ID (int AD_Window_ID)
{
if (AD_Window_ID <= 0) set_Value ("AD_Window_ID", null);
 else 
set_Value ("AD_Window_ID", Integer.valueOf(AD_Window_ID));
}
/** Get Window.
@return Data entry or display window */
public int getAD_Window_ID() 
{
Integer ii = (Integer)get_Value("AD_Window_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Window_ID */
public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";
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
/** Set HTML.
@param HTML HTML */
public void setHTML (String HTML)
{
set_Value ("HTML", HTML);
}
/** Get HTML.
@return HTML */
public String getHTML() 
{
return (String)get_Value("HTML");
}
/** Column name HTML */
public static final String COLUMNNAME_HTML = "HTML";
/** Set Line No.
@param Line Unique line for this document */
public void setLine (BigDecimal Line)
{
set_Value ("Line", Line);
}
/** Get Line No.
@return Unique line for this document */
public BigDecimal getLine() 
{
BigDecimal bd = (BigDecimal)get_Value("Line");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Line */
public static final String COLUMNNAME_Line = "Line";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 120)
{
log.warning("Length > 120 - truncated");
Name = Name.substring(0,119);
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
/** Set PA_DashboardContent_ID.
@param PA_DashboardContent_ID PA_DashboardContent_ID */
public void setPA_DashboardContent_ID (int PA_DashboardContent_ID)
{
if (PA_DashboardContent_ID < 1) throw new IllegalArgumentException ("PA_DashboardContent_ID is mandatory.");
set_ValueNoCheck ("PA_DashboardContent_ID", Integer.valueOf(PA_DashboardContent_ID));
}
/** Get PA_DashboardContent_ID.
@return PA_DashboardContent_ID */
public int getPA_DashboardContent_ID() 
{
Integer ii = (Integer)get_Value("PA_DashboardContent_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_DashboardContent_ID */
public static final String COLUMNNAME_PA_DashboardContent_ID = "PA_DashboardContent_ID";
/** Set Goal.
@param PA_Goal_ID Performance Goal */
public void setPA_Goal_ID (int PA_Goal_ID)
{
if (PA_Goal_ID <= 0) set_Value ("PA_Goal_ID", null);
 else 
set_Value ("PA_Goal_ID", Integer.valueOf(PA_Goal_ID));
}
/** Get Goal.
@return Performance Goal */
public int getPA_Goal_ID() 
{
Integer ii = (Integer)get_Value("PA_Goal_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name PA_Goal_ID */
public static final String COLUMNNAME_PA_Goal_ID = "PA_Goal_ID";
}
