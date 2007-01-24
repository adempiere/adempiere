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
/** Generated Model for AD_Column
 *  @author Adempiere (generated) 
 *  @version Release 3.1.4 - $Id$ */
public class X_AD_Column extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Column_ID id
@param trxName transaction
*/
public X_AD_Column (Properties ctx, int AD_Column_ID, String trxName)
{
super (ctx, AD_Column_ID, trxName);
/** if (AD_Column_ID == 0)
{
setAD_Column_ID (0);
setAD_Element_ID (0);
setAD_Reference_ID (0);
setAD_Table_ID (0);
setColumnName (null);
setEntityType (null);	// U
setIsAlwaysUpdateable (false);	// N
setIsEncrypted (null);	// N
setIsIdentifier (false);
setIsKey (false);
setIsMandatory (false);
setIsParent (false);
setIsSelectionColumn (false);
setIsTranslated (false);
setIsUpdateable (true);	// Y
setName (null);
setVersion (Env.ZERO);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Column (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=101 */
public static final int Table_ID=MTable.getTable_ID("AD_Column");

/** TableName=AD_Column */
public static final String Table_Name="AD_Column";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Column");

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
StringBuffer sb = new StringBuffer ("X_AD_Column[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Column.
@param AD_Column_ID Column in the table */
public void setAD_Column_ID (int AD_Column_ID)
{
if (AD_Column_ID < 1) throw new IllegalArgumentException ("AD_Column_ID is mandatory.");
set_ValueNoCheck ("AD_Column_ID", Integer.valueOf(AD_Column_ID));
}
/** Get Column.
@return Column in the table */
public int getAD_Column_ID() 
{
Integer ii = (Integer)get_Value("AD_Column_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set System Element.
@param AD_Element_ID System Element enables the central maintenance of column description and help. */
public void setAD_Element_ID (int AD_Element_ID)
{
if (AD_Element_ID < 1) throw new IllegalArgumentException ("AD_Element_ID is mandatory.");
set_Value ("AD_Element_ID", Integer.valueOf(AD_Element_ID));
}
/** Get System Element.
@return System Element enables the central maintenance of column description and help. */
public int getAD_Element_ID() 
{
Integer ii = (Integer)get_Value("AD_Element_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Process.
@param AD_Process_ID Process or Report */
public void setAD_Process_ID (int AD_Process_ID)
{
if (AD_Process_ID <= 0) set_Value ("AD_Process_ID", null);
 else 
set_Value ("AD_Process_ID", Integer.valueOf(AD_Process_ID));
}
/** Get Process.
@return Process or Report */
public int getAD_Process_ID() 
{
Integer ii = (Integer)get_Value("AD_Process_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_Reference_ID AD_Reference_ID=1 */
public static final int AD_REFERENCE_ID_AD_Reference_ID=1;
/** Set Reference.
@param AD_Reference_ID System Reference and Validation */
public void setAD_Reference_ID (int AD_Reference_ID)
{
if (AD_Reference_ID < 1) throw new IllegalArgumentException ("AD_Reference_ID is mandatory.");
set_Value ("AD_Reference_ID", Integer.valueOf(AD_Reference_ID));
}
/** Get Reference.
@return System Reference and Validation */
public int getAD_Reference_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_ID");
if (ii == null) return 0;
return ii.intValue();
}

/** AD_Reference_Value_ID AD_Reference_ID=4 */
public static final int AD_REFERENCE_VALUE_ID_AD_Reference_ID=4;
/** Set Reference Key.
@param AD_Reference_Value_ID Required to specify, if data type is Table or List */
public void setAD_Reference_Value_ID (int AD_Reference_Value_ID)
{
if (AD_Reference_Value_ID <= 0) set_Value ("AD_Reference_Value_ID", null);
 else 
set_Value ("AD_Reference_Value_ID", Integer.valueOf(AD_Reference_Value_ID));
}
/** Get Reference Key.
@return Required to specify, if data type is Table or List */
public int getAD_Reference_Value_ID() 
{
Integer ii = (Integer)get_Value("AD_Reference_Value_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Table.
@param AD_Table_ID Database Table information */
public void setAD_Table_ID (int AD_Table_ID)
{
if (AD_Table_ID < 1) throw new IllegalArgumentException ("AD_Table_ID is mandatory.");
set_ValueNoCheck ("AD_Table_ID", Integer.valueOf(AD_Table_ID));
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
set_Value ("AD_Val_Rule_ID", Integer.valueOf(AD_Val_Rule_ID));
}
/** Get Dynamic Validation.
@return Dynamic Validation Rule */
public int getAD_Val_Rule_ID() 
{
Integer ii = (Integer)get_Value("AD_Val_Rule_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Callout.
@param Callout Fully qualified class names and method - separated by semicolons */
public void setCallout (String Callout)
{
if (Callout != null && Callout.length() > 255)
{
log.warning("Length > 255 - truncated");
Callout = Callout.substring(0,254);
}
set_Value ("Callout", Callout);
}
/** Get Callout.
@return Fully qualified class names and method - separated by semicolons */
public String getCallout() 
{
return (String)get_Value("Callout");
}
/** Set DB Column Name.
@param ColumnName Name of the column in the database */
public void setColumnName (String ColumnName)
{
if (ColumnName == null) throw new IllegalArgumentException ("ColumnName is mandatory.");
if (ColumnName.length() > 40)
{
log.warning("Length > 40 - truncated");
ColumnName = ColumnName.substring(0,39);
}
set_Value ("ColumnName", ColumnName);
}
/** Get DB Column Name.
@return Name of the column in the database */
public String getColumnName() 
{
return (String)get_Value("ColumnName");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getColumnName());
}
/** Set Column SQL.
@param ColumnSQL Virtual Column (r/o) */
public void setColumnSQL (String ColumnSQL)
{
if (ColumnSQL != null && ColumnSQL.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ColumnSQL = ColumnSQL.substring(0,1999);
}
set_Value ("ColumnSQL", ColumnSQL);
}
/** Get Column SQL.
@return Virtual Column (r/o) */
public String getColumnSQL() 
{
return (String)get_Value("ColumnSQL");
}
/** Set Default Logic.
@param DefaultValue Default value hierarchy, separated by ;
 */
public void setDefaultValue (String DefaultValue)
{
if (DefaultValue != null && DefaultValue.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DefaultValue = DefaultValue.substring(0,1999);
}
set_Value ("DefaultValue", DefaultValue);
}
/** Get Default Logic.
@return Default value hierarchy, separated by ;
 */
public String getDefaultValue() 
{
return (String)get_Value("DefaultValue");
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
/** Set Length.
@param FieldLength Length of the column in the database */
public void setFieldLength (int FieldLength)
{
set_Value ("FieldLength", Integer.valueOf(FieldLength));
}
/** Get Length.
@return Length of the column in the database */
public int getFieldLength() 
{
Integer ii = (Integer)get_Value("FieldLength");
if (ii == null) return 0;
return ii.intValue();
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
/** Set Always Updateable.
@param IsAlwaysUpdateable The column is always updateable, even if the record is not active or processed */
public void setIsAlwaysUpdateable (boolean IsAlwaysUpdateable)
{
set_Value ("IsAlwaysUpdateable", Boolean.valueOf(IsAlwaysUpdateable));
}
/** Get Always Updateable.
@return The column is always updateable, even if the record is not active or processed */
public boolean isAlwaysUpdateable() 
{
Object oo = get_Value("IsAlwaysUpdateable");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}

/** IsEncrypted AD_Reference_ID=354 */
public static final int ISENCRYPTED_AD_Reference_ID=354;
/** Not Encrypted = N */
public static final String ISENCRYPTED_NotEncrypted = "N";
/** Encrypted = Y */
public static final String ISENCRYPTED_Encrypted = "Y";
/** Set Encrypted.
@param IsEncrypted Display or Storage is encrypted */
public void setIsEncrypted (String IsEncrypted)
{
if (IsEncrypted == null) throw new IllegalArgumentException ("IsEncrypted is mandatory");
if (IsEncrypted.equals("N") || IsEncrypted.equals("Y"));
 else throw new IllegalArgumentException ("IsEncrypted Invalid value - " + IsEncrypted + " - Reference_ID=354 - N - Y");
if (IsEncrypted.length() > 1)
{
log.warning("Length > 1 - truncated");
IsEncrypted = IsEncrypted.substring(0,0);
}
set_Value ("IsEncrypted", IsEncrypted);
}
/** Get Encrypted.
@return Display or Storage is encrypted */
public String getIsEncrypted() 
{
return (String)get_Value("IsEncrypted");
}
/** Set Identifier.
@param IsIdentifier This column is part of the record identifier */
public void setIsIdentifier (boolean IsIdentifier)
{
set_Value ("IsIdentifier", Boolean.valueOf(IsIdentifier));
}
/** Get Identifier.
@return This column is part of the record identifier */
public boolean isIdentifier() 
{
Object oo = get_Value("IsIdentifier");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Key column.
@param IsKey This column is the key in this table */
public void setIsKey (boolean IsKey)
{
set_Value ("IsKey", Boolean.valueOf(IsKey));
}
/** Get Key column.
@return This column is the key in this table */
public boolean isKey() 
{
Object oo = get_Value("IsKey");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Mandatory.
@param IsMandatory Data entry is required in this column */
public void setIsMandatory (boolean IsMandatory)
{
set_Value ("IsMandatory", Boolean.valueOf(IsMandatory));
}
/** Get Mandatory.
@return Data entry is required in this column */
public boolean isMandatory() 
{
Object oo = get_Value("IsMandatory");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Parent link column.
@param IsParent This column is a link to the parent table (e.g. header from lines) - incl. Association key columns */
public void setIsParent (boolean IsParent)
{
set_Value ("IsParent", Boolean.valueOf(IsParent));
}
/** Get Parent link column.
@return This column is a link to the parent table (e.g. header from lines) - incl. Association key columns */
public boolean isParent() 
{
Object oo = get_Value("IsParent");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Selection Column.
@param IsSelectionColumn Is this column used for finding rows in windows */
public void setIsSelectionColumn (boolean IsSelectionColumn)
{
set_Value ("IsSelectionColumn", Boolean.valueOf(IsSelectionColumn));
}
/** Get Selection Column.
@return Is this column used for finding rows in windows */
public boolean isSelectionColumn() 
{
Object oo = get_Value("IsSelectionColumn");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Synchronize Database.
@param IsSyncDatabase Change database table definition when changing dictionary definition */
public void setIsSyncDatabase (String IsSyncDatabase)
{
if (IsSyncDatabase != null && IsSyncDatabase.length() > 1)
{
log.warning("Length > 1 - truncated");
IsSyncDatabase = IsSyncDatabase.substring(0,0);
}
set_Value ("IsSyncDatabase", IsSyncDatabase);
}
/** Get Synchronize Database.
@return Change database table definition when changing dictionary definition */
public String getIsSyncDatabase() 
{
return (String)get_Value("IsSyncDatabase");
}
/** Set Translated.
@param IsTranslated This column is translated */
public void setIsTranslated (boolean IsTranslated)
{
set_Value ("IsTranslated", Boolean.valueOf(IsTranslated));
}
/** Get Translated.
@return This column is translated */
public boolean isTranslated() 
{
Object oo = get_Value("IsTranslated");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Set Updateable.
@param IsUpdateable Determines, if the field can be updated */
public void setIsUpdateable (boolean IsUpdateable)
{
set_Value ("IsUpdateable", Boolean.valueOf(IsUpdateable));
}
/** Get Updateable.
@return Determines, if the field can be updated */
public boolean isUpdateable() 
{
Object oo = get_Value("IsUpdateable");
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
/** Set Read Only Logic.
@param ReadOnlyLogic Logic to determine if field is read only (applies only when field is read-write) */
public void setReadOnlyLogic (String ReadOnlyLogic)
{
if (ReadOnlyLogic != null && ReadOnlyLogic.length() > 2000)
{
log.warning("Length > 2000 - truncated");
ReadOnlyLogic = ReadOnlyLogic.substring(0,1999);
}
set_Value ("ReadOnlyLogic", ReadOnlyLogic);
}
/** Get Read Only Logic.
@return Logic to determine if field is read only (applies only when field is read-write) */
public String getReadOnlyLogic() 
{
return (String)get_Value("ReadOnlyLogic");
}
/** Set Sequence.
@param SeqNo Method of ordering records;
 lowest number comes first */
public void setSeqNo (int SeqNo)
{
set_Value ("SeqNo", Integer.valueOf(SeqNo));
}
/** Get Sequence.
@return Method of ordering records;
 lowest number comes first */
public int getSeqNo() 
{
Integer ii = (Integer)get_Value("SeqNo");
if (ii == null) return 0;
return ii.intValue();
}
/** Set Value Format.
@param VFormat Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public void setVFormat (String VFormat)
{
if (VFormat != null && VFormat.length() > 60)
{
log.warning("Length > 60 - truncated");
VFormat = VFormat.substring(0,59);
}
set_Value ("VFormat", VFormat);
}
/** Get Value Format.
@return Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09" */
public String getVFormat() 
{
return (String)get_Value("VFormat");
}
/** Set Max. Value.
@param ValueMax Maximum Value for a field */
public void setValueMax (String ValueMax)
{
if (ValueMax != null && ValueMax.length() > 20)
{
log.warning("Length > 20 - truncated");
ValueMax = ValueMax.substring(0,19);
}
set_Value ("ValueMax", ValueMax);
}
/** Get Max. Value.
@return Maximum Value for a field */
public String getValueMax() 
{
return (String)get_Value("ValueMax");
}
/** Set Min. Value.
@param ValueMin Minimum Value for a field */
public void setValueMin (String ValueMin)
{
if (ValueMin != null && ValueMin.length() > 20)
{
log.warning("Length > 20 - truncated");
ValueMin = ValueMin.substring(0,19);
}
set_Value ("ValueMin", ValueMin);
}
/** Get Min. Value.
@return Minimum Value for a field */
public String getValueMin() 
{
return (String)get_Value("ValueMin");
}
/** Set Version.
@param Version Version of the table definition */
public void setVersion (BigDecimal Version)
{
if (Version == null) throw new IllegalArgumentException ("Version is mandatory.");
set_Value ("Version", Version);
}
/** Get Version.
@return Version of the table definition */
public BigDecimal getVersion() 
{
BigDecimal bd = (BigDecimal)get_Value("Version");
if (bd == null) return Env.ZERO;
return bd;
}
}
