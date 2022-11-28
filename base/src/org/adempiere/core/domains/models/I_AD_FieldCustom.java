/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_FieldCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_AD_FieldCustom 
{

    /** TableName=AD_FieldCustom */
    public static final String Table_Name = "AD_FieldCustom";

    /** AD_Table_ID=464 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_ContextInfo_ID */
    public static final String COLUMNNAME_AD_ContextInfo_ID = "AD_ContextInfo_ID";

	/** Set Context Info.
	  * Context Info Maintaining
	  */
	public void setAD_ContextInfo_ID (int AD_ContextInfo_ID);

	/** Get Context Info.
	  * Context Info Maintaining
	  */
	public int getAD_ContextInfo_ID();

	public org.adempiere.core.domains.models.I_AD_ContextInfo getAD_ContextInfo() throws RuntimeException;

    /** Column name AD_FieldCustom_ID */
    public static final String COLUMNNAME_AD_FieldCustom_ID = "AD_FieldCustom_ID";

	/** Set Custom Field	  */
	public void setAD_FieldCustom_ID (int AD_FieldCustom_ID);

	/** Get Custom Field	  */
	public int getAD_FieldCustom_ID();

    /** Column name AD_FieldDefinition_ID */
    public static final String COLUMNNAME_AD_FieldDefinition_ID = "AD_FieldDefinition_ID";

	/** Set Field Definition	  */
	public void setAD_FieldDefinition_ID (int AD_FieldDefinition_ID);

	/** Get Field Definition	  */
	public int getAD_FieldDefinition_ID();

	public org.adempiere.core.domains.models.I_AD_FieldDefinition getAD_FieldDefinition() throws RuntimeException;

    /** Column name AD_FieldGroup_ID */
    public static final String COLUMNNAME_AD_FieldGroup_ID = "AD_FieldGroup_ID";

	/** Set Field Group.
	  * Logical grouping of fields
	  */
	public void setAD_FieldGroup_ID (int AD_FieldGroup_ID);

	/** Get Field Group.
	  * Logical grouping of fields
	  */
	public int getAD_FieldGroup_ID();

	public org.adempiere.core.domains.models.I_AD_FieldGroup getAD_FieldGroup() throws RuntimeException;

    /** Column name AD_Field_ID */
    public static final String COLUMNNAME_AD_Field_ID = "AD_Field_ID";

	/** Set Field.
	  * Field on a database table
	  */
	public void setAD_Field_ID (int AD_Field_ID);

	/** Get Field.
	  * Field on a database table
	  */
	public int getAD_Field_ID();

	public org.adempiere.core.domains.models.I_AD_Field getAD_Field() throws RuntimeException;

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

	public org.adempiere.core.domains.models.I_AD_Image getAD_Image() throws RuntimeException;

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AD_Reference_ID */
    public static final String COLUMNNAME_AD_Reference_ID = "AD_Reference_ID";

	/** Set Reference.
	  * System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID);

	/** Get Reference.
	  * System Reference and Validation
	  */
	public int getAD_Reference_ID();

	public org.adempiere.core.domains.models.I_AD_Reference getAD_Reference() throws RuntimeException;

    /** Column name AD_Reference_Value_ID */
    public static final String COLUMNNAME_AD_Reference_Value_ID = "AD_Reference_Value_ID";

	/** Set Reference Key.
	  * Required to specify, if data type is Table or List
	  */
	public void setAD_Reference_Value_ID (int AD_Reference_Value_ID);

	/** Get Reference Key.
	  * Required to specify, if data type is Table or List
	  */
	public int getAD_Reference_Value_ID();

	public org.adempiere.core.domains.models.I_AD_Reference getAD_Reference_Value() throws RuntimeException;

    /** Column name AD_TabCustom_ID */
    public static final String COLUMNNAME_AD_TabCustom_ID = "AD_TabCustom_ID";

	/** Set Custom Tab	  */
	public void setAD_TabCustom_ID (int AD_TabCustom_ID);

	/** Get Custom Tab	  */
	public int getAD_TabCustom_ID();

	public org.adempiere.core.domains.models.I_AD_TabCustom getAD_TabCustom() throws RuntimeException;

    /** Column name AD_Val_Rule_ID */
    public static final String COLUMNNAME_AD_Val_Rule_ID = "AD_Val_Rule_ID";

	/** Set Dynamic Validation.
	  * Dynamic Validation Rule
	  */
	public void setAD_Val_Rule_ID (int AD_Val_Rule_ID);

	/** Get Dynamic Validation.
	  * Dynamic Validation Rule
	  */
	public int getAD_Val_Rule_ID();

	public org.adempiere.core.domains.models.I_AD_Val_Rule getAD_Val_Rule() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DefaultValue */
    public static final String COLUMNNAME_DefaultValue = "DefaultValue";

	/** Set Default Logic.
	  * Default value hierarchy, separated by ;

	  */
	public void setDefaultValue (String DefaultValue);

	/** Get Default Logic.
	  * Default value hierarchy, separated by ;

	  */
	public String getDefaultValue();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DisplayLength */
    public static final String COLUMNNAME_DisplayLength = "DisplayLength";

	/** Set Display Length.
	  * Length of the display in characters
	  */
	public void setDisplayLength (int DisplayLength);

	/** Get Display Length.
	  * Length of the display in characters
	  */
	public int getDisplayLength();

    /** Column name DisplayLogic */
    public static final String COLUMNNAME_DisplayLogic = "DisplayLogic";

	/** Set Display Logic.
	  * If the Field is displayed, the result determines if the field is actually displayed
	  */
	public void setDisplayLogic (String DisplayLogic);

	/** Get Display Logic.
	  * If the Field is displayed, the result determines if the field is actually displayed
	  */
	public String getDisplayLogic();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name InfoFactoryClass */
    public static final String COLUMNNAME_InfoFactoryClass = "InfoFactoryClass";

	/** Set Info Factory Class.
	  * Fully qualified class name that implements the InfoFactory interface
	  */
	public void setInfoFactoryClass (String InfoFactoryClass);

	/** Get Info Factory Class.
	  * Fully qualified class name that implements the InfoFactory interface
	  */
	public String getInfoFactoryClass();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsAllowCopy */
    public static final String COLUMNNAME_IsAllowCopy = "IsAllowCopy";

	/** Set Allow Copy.
	  * Defines whether the value of this field is considered in the copy of record
	  */
	public void setIsAllowCopy (String IsAllowCopy);

	/** Get Allow Copy.
	  * Defines whether the value of this field is considered in the copy of record
	  */
	public String getIsAllowCopy();

    /** Column name IsDisplayed */
    public static final String COLUMNNAME_IsDisplayed = "IsDisplayed";

	/** Set Displayed.
	  * Determines, if this field is displayed
	  */
	public void setIsDisplayed (boolean IsDisplayed);

	/** Get Displayed.
	  * Determines, if this field is displayed
	  */
	public boolean isDisplayed();

    /** Column name IsDisplayedGrid */
    public static final String COLUMNNAME_IsDisplayedGrid = "IsDisplayedGrid";

	/** Set Displayed in Grid.
	  * Determines, if this field is displayed in grid view
	  */
	public void setIsDisplayedGrid (boolean IsDisplayedGrid);

	/** Get Displayed in Grid.
	  * Determines, if this field is displayed in grid view
	  */
	public boolean isDisplayedGrid();

    /** Column name IsEmbedded */
    public static final String COLUMNNAME_IsEmbedded = "IsEmbedded";

	/** Set Is Embedded.
	  * When checked of include tab is embedded
	  */
	public void setIsEmbedded (String IsEmbedded);

	/** Get Is Embedded.
	  * When checked of include tab is embedded
	  */
	public String getIsEmbedded();

    /** Column name IsEncrypted */
    public static final String COLUMNNAME_IsEncrypted = "IsEncrypted";

	/** Set Encrypted.
	  * Display or Storage is encrypted
	  */
	public void setIsEncrypted (String IsEncrypted);

	/** Get Encrypted.
	  * Display or Storage is encrypted
	  */
	public String getIsEncrypted();

    /** Column name IsFieldOnly */
    public static final String COLUMNNAME_IsFieldOnly = "IsFieldOnly";

	/** Set Field Only.
	  * Label is not displayed
	  */
	public void setIsFieldOnly (String IsFieldOnly);

	/** Get Field Only.
	  * Label is not displayed
	  */
	public String getIsFieldOnly();

    /** Column name IsHeading */
    public static final String COLUMNNAME_IsHeading = "IsHeading";

	/** Set Heading only.
	  * Field without Column - Only label is displayed
	  */
	public void setIsHeading (String IsHeading);

	/** Get Heading only.
	  * Field without Column - Only label is displayed
	  */
	public String getIsHeading();

    /** Column name IsMandatory */
    public static final String COLUMNNAME_IsMandatory = "IsMandatory";

	/** Set Mandatory.
	  * Data entry is required in this column
	  */
	public void setIsMandatory (String IsMandatory);

	/** Get Mandatory.
	  * Data entry is required in this column
	  */
	public String getIsMandatory();

    /** Column name IsQuickEntry */
    public static final String COLUMNNAME_IsQuickEntry = "IsQuickEntry";

	/** Set Quick Entry.
	  * Display in Quick Entry Form
	  */
	public void setIsQuickEntry (String IsQuickEntry);

	/** Get Quick Entry.
	  * Display in Quick Entry Form
	  */
	public String getIsQuickEntry();

    /** Column name IsReadOnly */
    public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

	/** Set Read Only.
	  * Field is read only
	  */
	public void setIsReadOnly (String IsReadOnly);

	/** Get Read Only.
	  * Field is read only
	  */
	public String getIsReadOnly();

    /** Column name IsSameLine */
    public static final String COLUMNNAME_IsSameLine = "IsSameLine";

	/** Set Same Line.
	  * Displayed on same line as previous field
	  */
	public void setIsSameLine (String IsSameLine);

	/** Get Same Line.
	  * Displayed on same line as previous field
	  */
	public String getIsSameLine();

    /** Column name IsUpdateable */
    public static final String COLUMNNAME_IsUpdateable = "IsUpdateable";

	/** Set Updatable.
	  * Determines, if the field can be updated
	  */
	public void setIsUpdateable (boolean IsUpdateable);

	/** Get Updatable.
	  * Determines, if the field can be updated
	  */
	public boolean isUpdateable();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name ObscureType */
    public static final String COLUMNNAME_ObscureType = "ObscureType";

	/** Set Obscure.
	  * Type of obscuring the data (limiting the display)
	  */
	public void setObscureType (String ObscureType);

	/** Get Obscure.
	  * Type of obscuring the data (limiting the display)
	  */
	public String getObscureType();

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

    /** Column name SeqNoGrid */
    public static final String COLUMNNAME_SeqNoGrid = "SeqNoGrid";

	/** Set Grid Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNoGrid (int SeqNoGrid);

	/** Get Grid Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNoGrid();

    /** Column name SortNo */
    public static final String COLUMNNAME_SortNo = "SortNo";

	/** Set Record Sort No.
	  * Determines in what order the records are displayed
	  */
	public void setSortNo (int SortNo);

	/** Get Record Sort No.
	  * Determines in what order the records are displayed
	  */
	public int getSortNo();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UUID */
    public static final String COLUMNNAME_UUID = "UUID";

	/** Set Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID);

	/** Get Immutable Universally Unique Identifier.
	  * Immutable Universally Unique Identifier
	  */
	public String getUUID();
}
