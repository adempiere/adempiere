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
/** Generated Model for AD_Field
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_AD_Field extends PO
{
/** Standard Constructor
@param ctx context
@param AD_Field_ID id
@param trxName transaction
*/
public X_AD_Field (Properties ctx, int AD_Field_ID, String trxName)
{
super (ctx, AD_Field_ID, trxName);
/** if (AD_Field_ID == 0)
{
setAD_Column_ID (0);
setAD_Field_ID (0);
setAD_Tab_ID (0);
setEntityType (null);	// U
setIsCentrallyMaintained (true);	// Y
setIsDisplayed (true);	// Y
setIsEncrypted (false);
setIsFieldOnly (false);
setIsHeading (false);
setIsReadOnly (false);
setIsSameLine (false);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_AD_Field (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=107 */
public static final int Table_ID=MTable.getTable_ID("AD_Field");

/** TableName=AD_Field */
public static final String Table_Name="AD_Field";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"AD_Field");

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
StringBuffer sb = new StringBuffer ("X_AD_Field[").append(get_ID()).append("]");
return sb.toString();
}
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
/** Set Field Group.
@param AD_FieldGroup_ID Logical grouping of fields */
public void setAD_FieldGroup_ID (int AD_FieldGroup_ID)
{
if (AD_FieldGroup_ID <= 0) set_Value ("AD_FieldGroup_ID", null);
 else 
set_Value ("AD_FieldGroup_ID", Integer.valueOf(AD_FieldGroup_ID));
}
/** Get Field Group.
@return Logical grouping of fields */
public int getAD_FieldGroup_ID() 
{
Integer ii = (Integer)get_Value("AD_FieldGroup_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_FieldGroup_ID */
public static final String COLUMNNAME_AD_FieldGroup_ID = "AD_FieldGroup_ID";
/** Set Field.
@param AD_Field_ID Field on a database table */
public void setAD_Field_ID (int AD_Field_ID)
{
if (AD_Field_ID < 1) throw new IllegalArgumentException ("AD_Field_ID is mandatory.");
set_ValueNoCheck ("AD_Field_ID", Integer.valueOf(AD_Field_ID));
}
/** Get Field.
@return Field on a database table */
public int getAD_Field_ID() 
{
Integer ii = (Integer)get_Value("AD_Field_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Field_ID */
public static final String COLUMNNAME_AD_Field_ID = "AD_Field_ID";

/** AD_Reference_ID AD_Reference_ID=1 */
public static final int AD_REFERENCE_ID_AD_Reference_ID=1;
/** Set Reference.
@param AD_Reference_ID System Reference and Validation */
public void setAD_Reference_ID (int AD_Reference_ID)
{
if (AD_Reference_ID <= 0) set_Value ("AD_Reference_ID", null);
 else 
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
/** Column name AD_Reference_ID */
public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";
/** Set Tab.
@param AD_Tab_ID Tab within a Window */
public void setAD_Tab_ID (int AD_Tab_ID)
{
if (AD_Tab_ID < 1) throw new IllegalArgumentException ("AD_Tab_ID is mandatory.");
set_ValueNoCheck ("AD_Tab_ID", Integer.valueOf(AD_Tab_ID));
}
/** Get Tab.
@return Tab within a Window */
public int getAD_Tab_ID() 
{
Integer ii = (Integer)get_Value("AD_Tab_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Tab_ID */
public static final String COLUMNNAME_AD_Tab_ID = "AD_Tab_ID";
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
/** Set Display Length.
@param DisplayLength Length of the display in characters */
public void setDisplayLength (int DisplayLength)
{
set_Value ("DisplayLength", Integer.valueOf(DisplayLength));
}
/** Get Display Length.
@return Length of the display in characters */
public int getDisplayLength() 
{
Integer ii = (Integer)get_Value("DisplayLength");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name DisplayLength */
public static final String COLUMNNAME_DisplayLength = "DisplayLength";
/** Set Display Logic.
@param DisplayLogic If the Field is displayed, the result determines if the field is actually displayed */
public void setDisplayLogic (String DisplayLogic)
{
if (DisplayLogic != null && DisplayLogic.length() > 2000)
{
log.warning("Length > 2000 - truncated");
DisplayLogic = DisplayLogic.substring(0,1999);
}
set_Value ("DisplayLogic", DisplayLogic);
}
/** Get Display Logic.
@return If the Field is displayed, the result determines if the field is actually displayed */
public String getDisplayLogic() 
{
return (String)get_Value("DisplayLogic");
}
/** Column name DisplayLogic */
public static final String COLUMNNAME_DisplayLogic = "DisplayLogic";

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
/** Set Centrally maintained.
@param IsCentrallyMaintained Information maintained in System Element table */
public void setIsCentrallyMaintained (boolean IsCentrallyMaintained)
{
set_Value ("IsCentrallyMaintained", Boolean.valueOf(IsCentrallyMaintained));
}
/** Get Centrally maintained.
@return Information maintained in System Element table */
public boolean isCentrallyMaintained() 
{
Object oo = get_Value("IsCentrallyMaintained");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsCentrallyMaintained */
public static final String COLUMNNAME_IsCentrallyMaintained = "IsCentrallyMaintained";
/** Set Displayed.
@param IsDisplayed Determines, if this field is displayed */
public void setIsDisplayed (boolean IsDisplayed)
{
set_Value ("IsDisplayed", Boolean.valueOf(IsDisplayed));
}
/** Get Displayed.
@return Determines, if this field is displayed */
public boolean isDisplayed() 
{
Object oo = get_Value("IsDisplayed");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDisplayed */
public static final String COLUMNNAME_IsDisplayed = "IsDisplayed";
/** Set Encrypted.
@param IsEncrypted Display or Storage is encrypted */
public void setIsEncrypted (boolean IsEncrypted)
{
set_Value ("IsEncrypted", Boolean.valueOf(IsEncrypted));
}
/** Get Encrypted.
@return Display or Storage is encrypted */
public boolean isEncrypted() 
{
Object oo = get_Value("IsEncrypted");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsEncrypted */
public static final String COLUMNNAME_IsEncrypted = "IsEncrypted";
/** Set Field Only.
@param IsFieldOnly Label is not displayed */
public void setIsFieldOnly (boolean IsFieldOnly)
{
set_Value ("IsFieldOnly", Boolean.valueOf(IsFieldOnly));
}
/** Get Field Only.
@return Label is not displayed */
public boolean isFieldOnly() 
{
Object oo = get_Value("IsFieldOnly");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsFieldOnly */
public static final String COLUMNNAME_IsFieldOnly = "IsFieldOnly";
/** Set Heading only.
@param IsHeading Field without Column - Only label is displayed */
public void setIsHeading (boolean IsHeading)
{
set_Value ("IsHeading", Boolean.valueOf(IsHeading));
}
/** Get Heading only.
@return Field without Column - Only label is displayed */
public boolean isHeading() 
{
Object oo = get_Value("IsHeading");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsHeading */
public static final String COLUMNNAME_IsHeading = "IsHeading";

/** IsMandatory AD_Reference_ID=319 */
public static final int ISMANDATORY_AD_Reference_ID=319;
/** No = N */
public static final String ISMANDATORY_No = "N";
/** Yes = Y */
public static final String ISMANDATORY_Yes = "Y";
/** Set Mandatory.
@param IsMandatory Data entry is required in this column */
public void setIsMandatory (String IsMandatory)
{
if (IsMandatory == null || IsMandatory.equals("N") || IsMandatory.equals("Y"));
 else throw new IllegalArgumentException ("IsMandatory Invalid value - " + IsMandatory + " - Reference_ID=319 - N - Y");
if (IsMandatory != null && IsMandatory.length() > 1)
{
log.warning("Length > 1 - truncated");
IsMandatory = IsMandatory.substring(0,0);
}
set_Value ("IsMandatory", IsMandatory);
}
/** Get Mandatory.
@return Data entry is required in this column */
public String getIsMandatory() 
{
return (String)get_Value("IsMandatory");
}
/** Column name IsMandatory */
public static final String COLUMNNAME_IsMandatory = "IsMandatory";
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
/** Column name IsReadOnly */
public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";
/** Set Same Line.
@param IsSameLine Displayed on same line as previous field */
public void setIsSameLine (boolean IsSameLine)
{
set_Value ("IsSameLine", Boolean.valueOf(IsSameLine));
}
/** Get Same Line.
@return Displayed on same line as previous field */
public boolean isSameLine() 
{
Object oo = get_Value("IsSameLine");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsSameLine */
public static final String COLUMNNAME_IsSameLine = "IsSameLine";
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

/** ObscureType AD_Reference_ID=291 */
public static final int OBSCURETYPE_AD_Reference_ID=291;
/** Obscure Digits but last 4 = 904 */
public static final String OBSCURETYPE_ObscureDigitsButLast4 = "904";
/** Obscure Digits but first/last 4 = 944 */
public static final String OBSCURETYPE_ObscureDigitsButFirstLast4 = "944";
/** Obscure AlphaNumeric but last 4 = A04 */
public static final String OBSCURETYPE_ObscureAlphaNumericButLast4 = "A04";
/** Obscure AlphaNumeric but first/last 4 = A44 */
public static final String OBSCURETYPE_ObscureAlphaNumericButFirstLast4 = "A44";
/** Set Obscure.
@param ObscureType Type of obscuring the data (limiting the display) */
public void setObscureType (String ObscureType)
{
if (ObscureType == null || ObscureType.equals("904") || ObscureType.equals("944") || ObscureType.equals("A04") || ObscureType.equals("A44"));
 else throw new IllegalArgumentException ("ObscureType Invalid value - " + ObscureType + " - Reference_ID=291 - 904 - 944 - A04 - A44");
if (ObscureType != null && ObscureType.length() > 3)
{
log.warning("Length > 3 - truncated");
ObscureType = ObscureType.substring(0,2);
}
set_Value ("ObscureType", ObscureType);
}
/** Get Obscure.
@return Type of obscuring the data (limiting the display) */
public String getObscureType() 
{
return (String)get_Value("ObscureType");
}
/** Column name ObscureType */
public static final String COLUMNNAME_ObscureType = "ObscureType";
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
/** Column name SeqNo */
public static final String COLUMNNAME_SeqNo = "SeqNo";
/** Set Record Sort No.
@param SortNo Determines in what order the records are displayed */
public void setSortNo (BigDecimal SortNo)
{
set_Value ("SortNo", SortNo);
}
/** Get Record Sort No.
@return Determines in what order the records are displayed */
public BigDecimal getSortNo() 
{
BigDecimal bd = (BigDecimal)get_Value("SortNo");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name SortNo */
public static final String COLUMNNAME_SortNo = "SortNo";
}
