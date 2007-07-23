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
/** Generated Model for AD_EntityType
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_AD_EntityType extends PO
{
/** Standard Constructor
@param ctx context
@param AD_EntityType_ID id
@param trxName transaction
*/
public X_AD_EntityType (Properties ctx, int AD_EntityType_ID, String trxName)
{
super (ctx, AD_EntityType_ID, trxName);
/** if (AD_EntityType_ID == 0)
{
setAD_EntityType_ID (0);
setEntityType (null);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_EntityType (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** TableName=AD_EntityType */
public static final String Table_Name="AD_EntityType";

/** AD_Table_ID=882 */
public static final int Table_ID=MTable.getTable_ID(Table_Name);

protected static KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

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
StringBuffer sb = new StringBuffer ("X_AD_EntityType[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Entity Type.
@param AD_EntityType_ID System Entity Type */
public void setAD_EntityType_ID (int AD_EntityType_ID)
{
if (AD_EntityType_ID < 1) throw new IllegalArgumentException ("AD_EntityType_ID is mandatory.");
set_ValueNoCheck ("AD_EntityType_ID", Integer.valueOf(AD_EntityType_ID));
}
/** Get Entity Type.
@return System Entity Type */
public int getAD_EntityType_ID() 
{
Integer ii = (Integer)get_Value("AD_EntityType_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_EntityType_ID */
public static final String COLUMNNAME_AD_EntityType_ID = "AD_EntityType_ID";
/** Set Classpath.
@param Classpath Extension Classpath */
public void setClasspath (String Classpath)
{
if (Classpath != null && Classpath.length() > 255)
{
log.warning("Length > 255 - truncated");
Classpath = Classpath.substring(0,254);
}
set_Value ("Classpath", Classpath);
}
/** Get Classpath.
@return Extension Classpath */
public String getClasspath() 
{
return (String)get_Value("Classpath");
}
/** Column name Classpath */
public static final String COLUMNNAME_Classpath = "Classpath";
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
/** Set Entity Type.
@param EntityType Dictionary Entity Type;
 Determines ownership and synchronization */
public void setEntityType (String EntityType)
{
if (EntityType == null) throw new IllegalArgumentException ("EntityType is mandatory.");
if (EntityType.length() > 4)
{
log.warning("Length > 4 - truncated");
EntityType = EntityType.substring(0,3);
}
set_ValueNoCheck ("EntityType", EntityType);
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
/** Set ModelPackage.
@param ModelPackage Java Package of the model classes */
public void setModelPackage (String ModelPackage)
{
if (ModelPackage != null && ModelPackage.length() > 255)
{
log.warning("Length > 255 - truncated");
ModelPackage = ModelPackage.substring(0,254);
}
set_Value ("ModelPackage", ModelPackage);
}
/** Get ModelPackage.
@return Java Package of the model classes */
public String getModelPackage() 
{
return (String)get_Value("ModelPackage");
}
/** Column name ModelPackage */
public static final String COLUMNNAME_ModelPackage = "ModelPackage";
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
/** Set Process Now.
@param Processing Process Now */
public void setProcessing (boolean Processing)
{
set_Value ("Processing", Boolean.valueOf(Processing));
}
/** Get Process Now.
@return Process Now */
public boolean isProcessing() 
{
Object oo = get_Value("Processing");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name Processing */
public static final String COLUMNNAME_Processing = "Processing";
/** Set Version.
@param Version Version of the table definition */
public void setVersion (String Version)
{
if (Version != null && Version.length() > 20)
{
log.warning("Length > 20 - truncated");
Version = Version.substring(0,19);
}
set_Value ("Version", Version);
}
/** Get Version.
@return Version of the table definition */
public String getVersion() 
{
return (String)get_Value("Version");
}
/** Column name Version */
public static final String COLUMNNAME_Version = "Version";
}
