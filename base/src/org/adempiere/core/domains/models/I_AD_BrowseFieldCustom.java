/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.                                     *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net                                                  *
 * or https://github.com/adempiere/adempiere/blob/develop/license.html        *
 *****************************************************************************/
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_BrowseFieldCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_AD_BrowseFieldCustom 
{

    /** TableName=AD_BrowseFieldCustom */
    public static final String Table_Name = "AD_BrowseFieldCustom";

    /** AD_Table_ID=54616 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Browse_Field_ID */
    public static final String COLUMNNAME_AD_Browse_Field_ID = "AD_Browse_Field_ID";

	/** Set Browse Field	  */
	public void setAD_Browse_Field_ID (int AD_Browse_Field_ID);

	/** Get Browse Field	  */
	public int getAD_Browse_Field_ID();

	public org.adempiere.core.domains.models.I_AD_Browse_Field getAD_Browse_Field() throws RuntimeException;

    /** Column name AD_BrowseCustom_ID */
    public static final String COLUMNNAME_AD_BrowseCustom_ID = "AD_BrowseCustom_ID";

	/** Set Browse Customization	  */
	public void setAD_BrowseCustom_ID (int AD_BrowseCustom_ID);

	/** Get Browse Customization	  */
	public int getAD_BrowseCustom_ID();

	public org.adempiere.core.domains.models.I_AD_BrowseCustom getAD_BrowseCustom() throws RuntimeException;

    /** Column name AD_BrowseFieldCustom_ID */
    public static final String COLUMNNAME_AD_BrowseFieldCustom_ID = "AD_BrowseFieldCustom_ID";

	/** Set Browse Field	  */
	public void setAD_BrowseFieldCustom_ID (int AD_BrowseFieldCustom_ID);

	/** Get Browse Field	  */
	public int getAD_BrowseFieldCustom_ID();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

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

    /** Column name Axis_Column_ID */
    public static final String COLUMNNAME_Axis_Column_ID = "Axis_Column_ID";

	/** Set Axis Column.
	  * Axis the link column.
	  */
	public void setAxis_Column_ID (int Axis_Column_ID);

	/** Get Axis Column.
	  * Axis the link column.
	  */
	public int getAxis_Column_ID();

	public org.adempiere.core.domains.models.I_AD_Column getAxis_Column() throws RuntimeException;

    /** Column name Axis_Parent_Column_ID */
    public static final String COLUMNNAME_Axis_Parent_Column_ID = "Axis_Parent_Column_ID";

	/** Set Axis Parent Column.
	  * The link Axis column view on the parent key
	  */
	public void setAxis_Parent_Column_ID (int Axis_Parent_Column_ID);

	/** Get Axis Parent Column.
	  * The link Axis column view on the parent key
	  */
	public int getAxis_Parent_Column_ID();

	public org.adempiere.core.domains.models.I_AD_View_Column getAxis_Parent_Column() throws RuntimeException;

    /** Column name Callout */
    public static final String COLUMNNAME_Callout = "Callout";

	/** Set Callout.
	  * Fully qualified class names and method - separated by semicolons
	  */
	public void setCallout (String Callout);

	/** Get Callout.
	  * Fully qualified class names and method - separated by semicolons
	  */
	public String getCallout();

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

    /** Column name DefaultValue2 */
    public static final String COLUMNNAME_DefaultValue2 = "DefaultValue2";

	/** Set Default Logic 2.
	  * Default value hierarchy, separated by ;

	  */
	public void setDefaultValue2 (String DefaultValue2);

	/** Get Default Logic 2.
	  * Default value hierarchy, separated by ;

	  */
	public String getDefaultValue2();

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

    /** Column name IsIdentifier */
    public static final String COLUMNNAME_IsIdentifier = "IsIdentifier";

	/** Set Identifier.
	  * This column is part of the record identifier
	  */
	public void setIsIdentifier (String IsIdentifier);

	/** Get Identifier.
	  * This column is part of the record identifier
	  */
	public String getIsIdentifier();

    /** Column name IsInfoOnly */
    public static final String COLUMNNAME_IsInfoOnly = "IsInfoOnly";

	/** Set Is Information Only.
	  * When a Parameter is Information Only
	  */
	public void setIsInfoOnly (String IsInfoOnly);

	/** Get Is Information Only.
	  * When a Parameter is Information Only
	  */
	public String getIsInfoOnly();

    /** Column name IsKey */
    public static final String COLUMNNAME_IsKey = "IsKey";

	/** Set Key column.
	  * This column is the key in this table
	  */
	public void setIsKey (String IsKey);

	/** Get Key column.
	  * This column is the key in this table
	  */
	public String getIsKey();

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

    /** Column name IsOrderBy */
    public static final String COLUMNNAME_IsOrderBy = "IsOrderBy";

	/** Set Order by.
	  * Include in sort order
	  */
	public void setIsOrderBy (boolean IsOrderBy);

	/** Get Order by.
	  * Include in sort order
	  */
	public boolean isOrderBy();

    /** Column name IsQueryCriteria */
    public static final String COLUMNNAME_IsQueryCriteria = "IsQueryCriteria";

	/** Set Query Criteria.
	  * The column is also used as a query criteria
	  */
	public void setIsQueryCriteria (boolean IsQueryCriteria);

	/** Get Query Criteria.
	  * The column is also used as a query criteria
	  */
	public boolean isQueryCriteria();

    /** Column name IsRange */
    public static final String COLUMNNAME_IsRange = "IsRange";

	/** Set Range.
	  * The parameter is a range of values
	  */
	public void setIsRange (String IsRange);

	/** Get Range.
	  * The parameter is a range of values
	  */
	public String getIsRange();

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

    /** Column name ReadOnlyLogic */
    public static final String COLUMNNAME_ReadOnlyLogic = "ReadOnlyLogic";

	/** Set Read Only Logic.
	  * Logic to determine if field is read only (applies only when field is read-write)
	  */
	public void setReadOnlyLogic (String ReadOnlyLogic);

	/** Get Read Only Logic.
	  * Logic to determine if field is read only (applies only when field is read-write)
	  */
	public String getReadOnlyLogic();

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

    /** Column name ValueMax */
    public static final String COLUMNNAME_ValueMax = "ValueMax";

	/** Set Max. Value.
	  * Maximum Value for a field
	  */
	public void setValueMax (String ValueMax);

	/** Get Max. Value.
	  * Maximum Value for a field
	  */
	public String getValueMax();

    /** Column name ValueMin */
    public static final String COLUMNNAME_ValueMin = "ValueMin";

	/** Set Min. Value.
	  * Minimum Value for a field
	  */
	public void setValueMin (String ValueMin);

	/** Get Min. Value.
	  * Minimum Value for a field
	  */
	public String getValueMin();

    /** Column name VFormat */
    public static final String COLUMNNAME_VFormat = "VFormat";

	/** Set Value Format.
	  * Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public void setVFormat (String VFormat);

	/** Get Value Format.
	  * Format of the value;
 Can contain fixed format elements, Variables: "_lLoOaAcCa09"
	  */
	public String getVFormat();
}
