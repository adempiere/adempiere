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
/** Generated Model for CM_AccessProfile
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_CM_AccessProfile extends PO
{
/** Standard Constructor
@param ctx context
@param CM_AccessProfile_ID id
@param trxName transaction
*/
public X_CM_AccessProfile (Properties ctx, int CM_AccessProfile_ID, String trxName)
{
super (ctx, CM_AccessProfile_ID, trxName);
/** if (CM_AccessProfile_ID == 0)
{
setCM_AccessProfile_ID (0);
setIsExclude (true);	// Y
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_CM_AccessProfile (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=885 */
public static final int Table_ID=MTable.getTable_ID("CM_AccessProfile");

/** TableName=CM_AccessProfile */
public static final String Table_Name="CM_AccessProfile";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"CM_AccessProfile");

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
StringBuffer sb = new StringBuffer ("X_CM_AccessProfile[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Web Access Profile.
@param CM_AccessProfile_ID Web Access Profile */
public void setCM_AccessProfile_ID (int CM_AccessProfile_ID)
{
if (CM_AccessProfile_ID < 1) throw new IllegalArgumentException ("CM_AccessProfile_ID is mandatory.");
set_ValueNoCheck ("CM_AccessProfile_ID", Integer.valueOf(CM_AccessProfile_ID));
}
/** Get Web Access Profile.
@return Web Access Profile */
public int getCM_AccessProfile_ID() 
{
Integer ii = (Integer)get_Value("CM_AccessProfile_ID");
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
/** Set Exclude.
@param IsExclude Exclude access to the data - if not selected Include access to the data */
public void setIsExclude (boolean IsExclude)
{
set_Value ("IsExclude", Boolean.valueOf(IsExclude));
}
/** Get Exclude.
@return Exclude access to the data - if not selected Include access to the data */
public boolean isExclude() 
{
Object oo = get_Value("IsExclude");
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
}
