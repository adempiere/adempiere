/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Process_Para
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS
 */
public interface I_AD_Process_Para 
{

    /** TableName=AD_Process_Para */
    public static final String Table_Name = "AD_Process_Para";

    /** AD_Table_ID=285 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Element_ID */
    public static final String COLUMNNAME_AD_Element_ID = "AD_Element_ID";

	/** Set System Element.
	  * System Element enables the central maintenance of column description and help.
	  */
	public void setAD_Element_ID (int AD_Element_ID);

	/** Get System Element.
	  * System Element enables the central maintenance of column description and help.
	  */
	public int getAD_Element_ID();

	public I_AD_Element getAD_Element() throws RuntimeException;

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

    /** Column name AD_Process_ID */
    public static final String COLUMNNAME_AD_Process_ID = "AD_Process_ID";

	/** Set Process.
	  * Process or Report
	  */
	public void setAD_Process_ID (int AD_Process_ID);

	/** Get Process.
	  * Process or Report
	  */
	public int getAD_Process_ID();

	public I_AD_Process getAD_Process() throws RuntimeException;

    /** Column name AD_Process_Para_ID */
    public static final String COLUMNNAME_AD_Process_Para_ID = "AD_Process_Para_ID";

	/** Set Process Parameter	  */
	public void setAD_Process_Para_ID (int AD_Process_Para_ID);

	/** Get Process Parameter	  */
	public int getAD_Process_Para_ID();

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

	public I_AD_Reference getAD_Reference() throws RuntimeException;

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

	public I_AD_Reference getAD_Reference_Value() throws RuntimeException;

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

	public I_AD_Val_Rule getAD_Val_Rule() throws RuntimeException;

    /** Column name ColumnName */
    public static final String COLUMNNAME_ColumnName = "ColumnName";

	/** Set DB Column Name.
	  * Name of the column in the database
	  */
	public void setColumnName (String ColumnName);

	/** Get DB Column Name.
	  * Name of the column in the database
	  */
	public String getColumnName();

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

    /** Column name EntityType */
    public static final String COLUMNNAME_EntityType = "EntityType";

	/** Set Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public void setEntityType (String EntityType);

	/** Get Entity Type.
	  * Dictionary Entity Type;
 Determines ownership and synchronization
	  */
	public String getEntityType();

    /** Column name FieldLength */
    public static final String COLUMNNAME_FieldLength = "FieldLength";

	/** Set Length.
	  * Length of the column in the database
	  */
	public void setFieldLength (int FieldLength);

	/** Get Length.
	  * Length of the column in the database
	  */
	public int getFieldLength();

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

    /** Column name IsCentrallyMaintained */
    public static final String COLUMNNAME_IsCentrallyMaintained = "IsCentrallyMaintained";

	/** Set Centrally maintained.
	  * Information maintained in System Element table
	  */
	public void setIsCentrallyMaintained (boolean IsCentrallyMaintained);

	/** Get Centrally maintained.
	  * Information maintained in System Element table
	  */
	public boolean isCentrallyMaintained();

    /** Column name IsMandatory */
    public static final String COLUMNNAME_IsMandatory = "IsMandatory";

	/** Set Mandatory.
	  * Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory);

	/** Get Mandatory.
	  * Data entry is required in this column
	  */
	public boolean isMandatory();

    /** Column name IsRange */
    public static final String COLUMNNAME_IsRange = "IsRange";

	/** Set Range.
	  * The parameter is a range of values
	  */
	public void setIsRange (boolean IsRange);

	/** Get Range.
	  * The parameter is a range of values
	  */
	public boolean isRange();

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
