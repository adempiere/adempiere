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
/** Generated Model for AD_Workbench
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Workbench extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Workbench_ID id
@param trxName transaction
*/
public X_AD_Workbench (Properties ctx, int AD_Workbench_ID, String trxName)
{
super (ctx, AD_Workbench_ID, trxName);
/** if (AD_Workbench_ID == 0)
{
setAD_Column_ID (0);
setAD_Workbench_ID (0);
setEntityType (null);	// U
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Workbench (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=468 */
public static final int Table_ID=MTable.getTable_ID("AD_Workbench");

/** TableName=AD_Workbench */
public static final String Table_Name="AD_Workbench";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Workbench");

protected BigDecimal accessLevel = BigDecimal.valueOf(4);
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
StringBuffer sb = new StringBuffer ("X_AD_Workbench[").append(get_ID()).append("]");
return sb.toString();
}
/** Set System Color.
@param AD_Color_ID Color for backgrounds or indicators */
public void setAD_Color_ID (Object AD_Color_ID)
{
set_Value ("AD_Color_ID", AD_Color_ID);
}
/** Get System Color.
@return Color for backgrounds or indicators */
public Object getAD_Color_ID() 
{
return get_Value("AD_Color_ID");
}
/** Column name AD_Color_ID */
public static final String COLUMNNAME_AD_Color_ID = "AD_Color_ID";

/** AD_Column_ID AD_Reference_ID=244 */
public static final int AD_COLUMN_ID_AD_Reference_ID=244;
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID < 1) throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
set_Value ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Column_ID */
public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";
/** Set Image.
@param AD_Image_ID Image or Icon */
public void setAD_Image_ID (int AD_Image_ID)
{
if (AD_Image_ID <= 0) set_Value ("AD_Image_ID", null);
 else 
set_Value ("AD_Image_ID", Integer.valueOf(AD_Image_ID));
}
/** Get Image.
@return Image or Icon */
public int getAD_Image_ID() 
{
Integer ii = (Integer)get_Value("AD_Image_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Image_ID */
public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";
/** Set Workbench.
@param AD_Workbench_ID Collection of windows, reports */
public void setAD_Workbench_ID (int AD_Workbench_ID)
{
if (AD_Workbench_ID < 1) throw new IllegalArgumentException ("AD_Workbench_ID is mandatory.");
set_ValueNoCheck ("AD_Workbench_ID", Integer.valueOf(AD_Workbench_ID));
}
/** Get Workbench.
@return Collection of windows, reports */
public int getAD_Workbench_ID() 
{
Integer ii = (Integer)get_Value("AD_Workbench_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Workbench_ID */
public static final String COLUMNNAME_AD_Workbench_ID = "AD_Workbench_ID";
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

/** EntityType AD_Reference_ID=389 */
public static final int ENTITYTYPE_AD_Reference_ID=389;
/** Set Entity Type.
@param EntityType Dictionary Entity Type;
 Determines ownership and synchronization */
public void setEntityType (String EntityType)
{
if (EntityType.length() > 4)
{
log.warning("Length > 4 - truncated");
EntityType = EntityType.substring(0,3);
}
set_Value ("EntityType", EntityType);
}
/** Get Entity Type.
@return Dictionary Entity Type;
 Determines ownership and synchronization */
public String getEntityType() 
{
return (String)get_Value("EntityType");
}
/** Column name EntityType */
public static final String COLUMNNAME_EntityType = "EntityType";
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
