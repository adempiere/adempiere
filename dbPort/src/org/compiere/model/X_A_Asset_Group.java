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
/** Generated Model for A_Asset_Group
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_A_Asset_Group extends PO
{
/** Standard Constructor
@param ctx context
@param A_Asset_Group_ID id
@param trxName transaction
*/
public X_A_Asset_Group (Properties ctx, int A_Asset_Group_ID, String trxName)
{
super (ctx, A_Asset_Group_ID, trxName);
/** if (A_Asset_Group_ID == 0)
{
setA_Asset_Group_ID (0);
setIsCreateAsActive (true);	// Y
setIsDepreciated (false);
setIsOneAssetPerUOM (false);
setIsOwned (false);
setIsTrackIssues (false);	// N
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_A_Asset_Group (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=542 */
public static final int Table_ID=MTable.getTable_ID("A_Asset_Group");

/** TableName=A_Asset_Group */
public static final String Table_Name="A_Asset_Group";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"A_Asset_Group");

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
StringBuffer sb = new StringBuffer ("X_A_Asset_Group[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Asset Group.
@param A_Asset_Group_ID Group of Assets */
public void setA_Asset_Group_ID (int A_Asset_Group_ID)
{
if (A_Asset_Group_ID < 1) throw new IllegalArgumentException ("A_Asset_Group_ID is mandatory.");
set_ValueNoCheck ("A_Asset_Group_ID", Integer.valueOf(A_Asset_Group_ID));
}
/** Get Asset Group.
@return Group of Assets */
public int getA_Asset_Group_ID() 
{
Integer ii = (Integer)get_Value("A_Asset_Group_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name A_Asset_Group_ID */
public static final String COLUMNNAME_A_Asset_Group_ID = "A_Asset_Group_ID";
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
/** Column name Help */
public static final String COLUMNNAME_Help = "Help";
/** Set Create As Active.
@param IsCreateAsActive Create Asset and activate it */
public void setIsCreateAsActive (boolean IsCreateAsActive)
{
set_Value ("IsCreateAsActive", Boolean.valueOf(IsCreateAsActive));
}
/** Get Create As Active.
@return Create Asset and activate it */
public boolean isCreateAsActive() 
{
Object oo = get_Value("IsCreateAsActive");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCreateAsActive */
public static final String COLUMNNAME_IsCreateAsActive = "IsCreateAsActive";
/** Set Depreciate.
@param IsDepreciated The asset will be depreciated */
public void setIsDepreciated (boolean IsDepreciated)
{
set_Value ("IsDepreciated", Boolean.valueOf(IsDepreciated));
}
/** Get Depreciate.
@return The asset will be depreciated */
public boolean isDepreciated() 
{
Object oo = get_Value("IsDepreciated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDepreciated */
public static final String COLUMNNAME_IsDepreciated = "IsDepreciated";
/** Set One Asset Per UOM.
@param IsOneAssetPerUOM Create one asset per UOM */
public void setIsOneAssetPerUOM (boolean IsOneAssetPerUOM)
{
set_Value ("IsOneAssetPerUOM", Boolean.valueOf(IsOneAssetPerUOM));
}
/** Get One Asset Per UOM.
@return Create one asset per UOM */
public boolean isOneAssetPerUOM() 
{
Object oo = get_Value("IsOneAssetPerUOM");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsOneAssetPerUOM */
public static final String COLUMNNAME_IsOneAssetPerUOM = "IsOneAssetPerUOM";
/** Set Owned.
@param IsOwned The asset is owned by the organization */
public void setIsOwned (boolean IsOwned)
{
set_Value ("IsOwned", Boolean.valueOf(IsOwned));
}
/** Get Owned.
@return The asset is owned by the organization */
public boolean isOwned() 
{
Object oo = get_Value("IsOwned");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsOwned */
public static final String COLUMNNAME_IsOwned = "IsOwned";
/** Set Track Issues.
@param IsTrackIssues Enable tracking issues for this asset */
public void setIsTrackIssues (boolean IsTrackIssues)
{
set_Value ("IsTrackIssues", Boolean.valueOf(IsTrackIssues));
}
/** Get Track Issues.
@return Enable tracking issues for this asset */
public boolean isTrackIssues() 
{
Object oo = get_Value("IsTrackIssues");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsTrackIssues */
public static final String COLUMNNAME_IsTrackIssues = "IsTrackIssues";
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
}
