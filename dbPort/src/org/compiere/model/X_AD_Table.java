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
/** Generated Model for AD_Table
 *  @author Jorg Janke (generated) 
 *  @version Release 2.5.3d - 2006-10-10 21:55:54.187 */
public class X_AD_Table extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Table_ID id
@param trxName transaction
*/
public X_AD_Table (Properties ctx, int AD_Table_ID, String trxName)
{
super (ctx, AD_Table_ID, trxName);
/** if (AD_Table_ID == 0)
{
setAD_Table_ID (0);
setAccessLevel (null);	// 4
setEntityType (null);	// U
setIsChangeLog (false);
setIsDeleteable (true);	// Y
setIsHighVolume (false);
setIsSecurityEnabled (false);
setIsView (false);	// N
setName (null);
setReplicationType (null);	// L
setTableName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Table (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=100 */
public static final int Table_ID=100;

/** TableName=AD_Table */
public static final String Table_Name="AD_Table";

protected static KeyNamePair Model = new KeyNamePair(100,"AD_Table");

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
StringBuffer sb = new StringBuffer ("X_AD_Table[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_ValueNoCheck ("AD_Table_ID", new Integer(AD_Table_ID));
}
/** Get Table.
@return Database Table information */
public int getAD_Table_ID() 
{
Integer ii = (Integer)get_Value("AD_Table_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Dynamic Validation.
@param AD_Val_Rule_ID Dynamic Validation Rule */
public void setAD_Val_Rule_ID (int AD_Val_Rule_ID)
{
if (AD_Val_Rule_ID <= 0) set_Value ("AD_Val_Rule_ID", null);
 else 
set_Value ("AD_Val_Rule_ID", new Integer(AD_Val_Rule_ID));
}
/** Get Dynamic Validation.
@return Dynamic Validation Rule */
public int getAD_Val_Rule_ID() 
{
Integer ii = (Integer)get_Value("AD_Val_Rule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Window.
@param AD_Window_ID Data entry or display window */
public void setAD_Window_ID (int AD_Window_ID)
{
if (AD_Window_ID <= 0) set_Value ("AD_Window_ID", null);
 else 
set_Value ("AD_Window_ID", new Integer(AD_Window_ID));
}
/** Get Window.
@return Data entry or display window */
public int getAD_Window_ID() 
{
Integer ii = (Integer)get_Value("AD_Window_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AccessLevel AD_Reference_ID=5 */
public static final int ACCESSLEVEL_AD_Reference_ID=5;
/** Organization = 1 */
public static final String ACCESSLEVEL_Organization = "1";
/** Client only = 2 */
public static final String ACCESSLEVEL_ClientOnly = "2";
/** Client+Organization = 3 */
public static final String ACCESSLEVEL_ClientPlusOrganization = "3";
/** System only = 4 */
public static final String ACCESSLEVEL_SystemOnly = "4";
/** System+Client = 6 */
public static final String ACCESSLEVEL_SystemPlusClient = "6";
/** All = 7 */
public static final String ACCESSLEVEL_All = "7";
/** Set Data Access Level.
@param AccessLevel Access Level required */
public void setAccessLevel (String AccessLevel)
{
if (AccessLevel == null) throw new IllegalArgumentException ("AccessLevel is mandatory");
if (AccessLevel.equals("1") || AccessLevel.equals("2") || AccessLevel.equals("3") || AccessLevel.equals("4") || AccessLevel.equals("6") || AccessLevel.equals("7"));
 else throw new IllegalArgumentException ("AccessLevel Invalid value - " + AccessLevel + " - Reference_ID=5 - 1 - 2 - 3 - 4 - 6 - 7");
if (AccessLevel.length() > 1)
{
log.warning("Length > 1 - truncated");
AccessLevel = AccessLevel.substring(0,0);
}
set_Value ("AccessLevel", AccessLevel);
}
/** Get Data Access Level.
@return Access Level required */
public String getAccessLevel() 
{
return (String)get_Value("AccessLevel");
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
/** Set Import Table.
@param ImportTable Import Table Columns from Database */
public void setImportTable (String ImportTable)
{
if (ImportTable != null && ImportTable.length() > 1)
{
log.warning("Length > 1 - truncated");
ImportTable = ImportTable.substring(0,0);
}
set_Value ("ImportTable", ImportTable);
}
/** Get Import Table.
@return Import Table Columns from Database */
public String getImportTable() 
{
return (String)get_Value("ImportTable");
}
/** Set Maintain Change Log.
@param IsChangeLog Maintain a log of changes */
public void setIsChangeLog (boolean IsChangeLog)
{
set_Value ("IsChangeLog", new Boolean(IsChangeLog));
}
/** Get Maintain Change Log.
@return Maintain a log of changes */
public boolean isChangeLog() 
{
Object oo = get_Value("IsChangeLog");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Records deleteable.
@param IsDeleteable Indicates if records can be deleted from the database */
public void setIsDeleteable (boolean IsDeleteable)
{
set_Value ("IsDeleteable", new Boolean(IsDeleteable));
}
/** Get Records deleteable.
@return Indicates if records can be deleted from the database */
public boolean isDeleteable() 
{
Object oo = get_Value("IsDeleteable");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set High Volume.
@param IsHighVolume Use Search instead of Pick list */
public void setIsHighVolume (boolean IsHighVolume)
{
set_Value ("IsHighVolume", new Boolean(IsHighVolume));
}
/** Get High Volume.
@return Use Search instead of Pick list */
public boolean isHighVolume() 
{
Object oo = get_Value("IsHighVolume");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Security enabled.
@param IsSecurityEnabled If security is enabled, user access to data can be restricted via Roles */
public void setIsSecurityEnabled (boolean IsSecurityEnabled)
{
set_Value ("IsSecurityEnabled", new Boolean(IsSecurityEnabled));
}
/** Get Security enabled.
@return If security is enabled, user access to data can be restricted via Roles */
public boolean isSecurityEnabled() 
{
Object oo = get_Value("IsSecurityEnabled");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set View.
@param IsView This is a view */
public void setIsView (boolean IsView)
{
set_Value ("IsView", new Boolean(IsView));
}
/** Get View.
@return This is a view */
public boolean isView() 
{
Object oo = get_Value("IsView");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Sequence.
@param LoadSeq Sequence */
public void setLoadSeq (int LoadSeq)
{
set_ValueNoCheck ("LoadSeq", new Integer(LoadSeq));
}
/** Get Sequence.
@return Sequence */
public int getLoadSeq() 
{
Integer ii = (Integer)get_Value("LoadSeq");
if (ii == null) return 0;
return ii.intValue();
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

/** PO_Window_ID AD_Reference_ID=284 */
public static final int PO_WINDOW_ID_AD_Reference_ID=284;
/** Set PO Window.
@param PO_Window_ID Purchase Order Window */
public void setPO_Window_ID (int PO_Window_ID)
{
if (PO_Window_ID <= 0) set_Value ("PO_Window_ID", null);
 else 
set_Value ("PO_Window_ID", new Integer(PO_Window_ID));
}
/** Get PO Window.
@return Purchase Order Window */
public int getPO_Window_ID() 
{
Integer ii = (Integer)get_Value("PO_Window_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** ReplicationType AD_Reference_ID=126 */
public static final int REPLICATIONTYPE_AD_Reference_ID=126;
/** Local = L */
public static final String REPLICATIONTYPE_Local = "L";
/** Merge = M */
public static final String REPLICATIONTYPE_Merge = "M";
/** Reference = R */
public static final String REPLICATIONTYPE_Reference = "R";
/** Set Replication Type.
@param ReplicationType Type of Data Replication */
public void setReplicationType (String ReplicationType)
{
if (ReplicationType == null) throw new IllegalArgumentException ("ReplicationType is mandatory");
if (ReplicationType.equals("L") || ReplicationType.equals("M") || ReplicationType.equals("R"));
 else throw new IllegalArgumentException ("ReplicationType Invalid value - " + ReplicationType + " - Reference_ID=126 - L - M - R");
if (ReplicationType.length() > 1)
{
log.warning("Length > 1 - truncated");
ReplicationType = ReplicationType.substring(0,0);
}
set_Value ("ReplicationType", ReplicationType);
}
/** Get Replication Type.
@return Type of Data Replication */
public String getReplicationType() 
{
return (String)get_Value("ReplicationType");
}
/** Set DB Table Name.
@param TableName Name of the table in the database */
public void setTableName (String TableName)
{
if (TableName == null) throw new IllegalArgumentException ("TableName is mandatory.");
if (TableName.length() > 40)
{
log.warning("Length > 40 - truncated");
TableName = TableName.substring(0,39);
}
set_Value ("TableName", TableName);
}
/** Get DB Table Name.
@return Name of the table in the database */
public String getTableName() 
{
return (String)get_Value("TableName");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getTableName());
}
}
