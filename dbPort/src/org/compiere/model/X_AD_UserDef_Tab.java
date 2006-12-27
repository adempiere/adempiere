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
/** Generated Model for AD_UserDef_Tab
 *  @author Jorg Janke (generated) 
 *  @version Release 3.1.3 - $Id$ */
public class X_AD_UserDef_Tab extends PO
{
/** Standard Constructor
@param ctx context
@param AD_UserDef_Tab_ID id
@param trxName transaction
*/
public X_AD_UserDef_Tab (Properties ctx, int AD_UserDef_Tab_ID, String trxName)
{
super (ctx, AD_UserDef_Tab_ID, trxName);
/** if (AD_UserDef_Tab_ID == 0)
{
setAD_Tab_ID (0);
setAD_UserDef_Tab_ID (0);
setAD_UserDef_Win_ID (0);
setIsMultiRowOnly (false);
setIsReadOnly (false);
setIsSingleRow (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_UserDef_Tab (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=466 */
public static final int Table_ID=MTable.getTable_ID("AD_UserDef_Tab");

/** TableName=AD_UserDef_Tab */
public static final String Table_Name="AD_UserDef_Tab";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_UserDef_Tab");

protected BigDecimal accessLevel = new BigDecimal(4);
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
StringBuffer sb = new StringBuffer ("X_AD_UserDef_Tab[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Tab.
@param AD_Tab_ID Tab within a Window */
public void setAD_Tab_ID (int AD_Tab_ID)
{
if (AD_Tab_ID < 1) throw new IllegalArgumentException ("AD_Tab_ID is mandatory.");
set_Value ("AD_Tab_ID", Integer.valueOf(AD_Tab_ID));
}
/** Get Tab.
@return Tab within a Window */
public int getAD_Tab_ID() 
{
Integer ii = (Integer)get_Value("AD_Tab_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User defined Tab.
@param AD_UserDef_Tab_ID User defined Tab */
public void setAD_UserDef_Tab_ID (int AD_UserDef_Tab_ID)
{
if (AD_UserDef_Tab_ID < 1) throw new IllegalArgumentException ("AD_UserDef_Tab_ID is mandatory.");
set_ValueNoCheck ("AD_UserDef_Tab_ID", Integer.valueOf(AD_UserDef_Tab_ID));
}
/** Get User defined Tab.
@return User defined Tab */
public int getAD_UserDef_Tab_ID() 
{
Integer ii = (Integer)get_Value("AD_UserDef_Tab_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set User defined Window.
@param AD_UserDef_Win_ID User defined Window */
public void setAD_UserDef_Win_ID (int AD_UserDef_Win_ID)
{
if (AD_UserDef_Win_ID < 1) throw new IllegalArgumentException ("AD_UserDef_Win_ID is mandatory.");
set_ValueNoCheck ("AD_UserDef_Win_ID", Integer.valueOf(AD_UserDef_Win_ID));
}
/** Get User defined Window.
@return User defined Window */
public int getAD_UserDef_Win_ID() 
{
Integer ii = (Integer)get_Value("AD_UserDef_Win_ID");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Comment/Help.
@param Help Comment or Hint */
public void setHelp (String Help)
{
if (Help != null && Help.length() > 2000)
{
log.warning("Length > 2000 - truncated");
Help = Help.substring(0,1999);
}
set_Value ("Help", Help);
}
/** Get Comment/Help.
@return Comment or Hint */
public String getHelp() 
{
return (String)get_Value("Help");
}
/** Set Multi Row Only.
@param IsMultiRowOnly This applies to Multi-Row view only */
public void setIsMultiRowOnly (boolean IsMultiRowOnly)
{
set_Value ("IsMultiRowOnly", Boolean.valueOf(IsMultiRowOnly));
}
/** Get Multi Row Only.
@return This applies to Multi-Row view only */
public boolean isMultiRowOnly() 
{
Object oo = get_Value("IsMultiRowOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Read Only.
@param IsReadOnly Field is read only */
public void setIsReadOnly (boolean IsReadOnly)
{
set_Value ("IsReadOnly", Boolean.valueOf(IsReadOnly));
}
/** Get Read Only.
@return Field is read only */
public boolean isReadOnly() 
{
Object oo = get_Value("IsReadOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Single Row Layout.
@param IsSingleRow Default for toggle between Single- and Multi-Row (Grid) Layout */
public void setIsSingleRow (boolean IsSingleRow)
{
set_Value ("IsSingleRow", Boolean.valueOf(IsSingleRow));
}
/** Get Single Row Layout.
@return Default for toggle between Single- and Multi-Row (Grid) Layout */
public boolean isSingleRow() 
{
Object oo = get_Value("IsSingleRow");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
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
}
