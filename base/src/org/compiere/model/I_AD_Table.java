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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for AD_Table
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3
 */
public interface I_AD_Table 
{

    /** TableName=AD_Table */
    public static final String Table_Name = "AD_Table";

    /** AD_Table_ID=100 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name AccessLevel */
    public static final String COLUMNNAME_AccessLevel = "AccessLevel";

	/** Set Data Access Level.
	  * Access Level required
	  */
	public void setAccessLevel (String AccessLevel);

	/** Get Data Access Level.
	  * Access Level required
	  */
	public String getAccessLevel();

    /** Column name ACTriggerLength */
    public static final String COLUMNNAME_ACTriggerLength = "ACTriggerLength";

	/** Set Auto Complete Min Length.
	  * Identifier autocomplete trigger length
	  */
	public void setACTriggerLength (int ACTriggerLength);

	/** Get Auto Complete Min Length.
	  * Identifier autocomplete trigger length
	  */
	public int getACTriggerLength();

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

	public org.spin.model.I_AD_ContextInfo getAD_ContextInfo() throws RuntimeException;

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

    /** Column name AD_Table_ID */
    public static final String COLUMNNAME_AD_Table_ID = "AD_Table_ID";

	/** Set Table.
	  * Database Table information
	  */
	public void setAD_Table_ID (int AD_Table_ID);

	/** Get Table.
	  * Database Table information
	  */
	public int getAD_Table_ID();

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

	public org.compiere.model.I_AD_Val_Rule getAD_Val_Rule() throws RuntimeException;

    /** Column name AD_Window_ID */
    public static final String COLUMNNAME_AD_Window_ID = "AD_Window_ID";

	/** Set Window.
	  * Data entry or display window
	  */
	public void setAD_Window_ID (int AD_Window_ID);

	/** Get Window.
	  * Data entry or display window
	  */
	public int getAD_Window_ID();

	public org.compiere.model.I_AD_Window getAD_Window() throws RuntimeException;

    /** Column name CopyColumnsFromTable */
    public static final String COLUMNNAME_CopyColumnsFromTable = "CopyColumnsFromTable";

	/** Set Copy Columns From Table	  */
	public void setCopyColumnsFromTable (String CopyColumnsFromTable);

	/** Get Copy Columns From Table	  */
	public String getCopyColumnsFromTable();

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

    /** Column name ImportTable */
    public static final String COLUMNNAME_ImportTable = "ImportTable";

	/** Set Import Table.
	  * Import Table Columns from Database
	  */
	public void setImportTable (String ImportTable);

	/** Get Import Table.
	  * Import Table Columns from Database
	  */
	public String getImportTable();

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

    /** Column name IsChangeLog */
    public static final String COLUMNNAME_IsChangeLog = "IsChangeLog";

	/** Set Maintain Change Log.
	  * Maintain a log of changes
	  */
	public void setIsChangeLog (boolean IsChangeLog);

	/** Get Maintain Change Log.
	  * Maintain a log of changes
	  */
	public boolean isChangeLog();

    /** Column name IsDeleteable */
    public static final String COLUMNNAME_IsDeleteable = "IsDeleteable";

	/** Set Records deletable.
	  * Indicates if records can be deleted from the database
	  */
	public void setIsDeleteable (boolean IsDeleteable);

	/** Get Records deletable.
	  * Indicates if records can be deleted from the database
	  */
	public boolean isDeleteable();

    /** Column name IsDocument */
    public static final String COLUMNNAME_IsDocument = "IsDocument";

	/** Set Is Document.
	  * This flag determinate if the record is a document
	  */
	public void setIsDocument (boolean IsDocument);

	/** Get Is Document.
	  * This flag determinate if the record is a document
	  */
	public boolean isDocument();

    /** Column name IsHighVolume */
    public static final String COLUMNNAME_IsHighVolume = "IsHighVolume";

	/** Set High Volume.
	  * Use Search instead of Pick list
	  */
	public void setIsHighVolume (boolean IsHighVolume);

	/** Get High Volume.
	  * Use Search instead of Pick list
	  */
	public boolean isHighVolume();

    /** Column name IsIgnoreMigration */
    public static final String COLUMNNAME_IsIgnoreMigration = "IsIgnoreMigration";

	/** Set Ignore Migration.
	  * Ignore this record in log migration
	  */
	public void setIsIgnoreMigration (boolean IsIgnoreMigration);

	/** Get Ignore Migration.
	  * Ignore this record in log migration
	  */
	public boolean isIgnoreMigration();

    /** Column name IsSecurityEnabled */
    public static final String COLUMNNAME_IsSecurityEnabled = "IsSecurityEnabled";

	/** Set Security enabled.
	  * If security is enabled, user access to data can be restricted via Roles
	  */
	public void setIsSecurityEnabled (boolean IsSecurityEnabled);

	/** Get Security enabled.
	  * If security is enabled, user access to data can be restricted via Roles
	  */
	public boolean isSecurityEnabled();

    /** Column name IsView */
    public static final String COLUMNNAME_IsView = "IsView";

	/** Set View.
	  * This is a view
	  */
	public void setIsView (boolean IsView);

	/** Get View.
	  * This is a view
	  */
	public boolean isView();

    /** Column name LoadSeq */
    public static final String COLUMNNAME_LoadSeq = "LoadSeq";

	/** Set Sequence	  */
	public void setLoadSeq (int LoadSeq);

	/** Get Sequence	  */
	public int getLoadSeq();

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

    /** Column name NameOldValue */
    public static final String COLUMNNAME_NameOldValue = "NameOldValue";

	/** Set Name Old Value.
	  * The old value of the column or table name.  Used to synchronize name changes with the database.
	  */
	public void setNameOldValue (String NameOldValue);

	/** Get Name Old Value.
	  * The old value of the column or table name.  Used to synchronize name changes with the database.
	  */
	public String getNameOldValue();

    /** Column name PO_Window_ID */
    public static final String COLUMNNAME_PO_Window_ID = "PO_Window_ID";

	/** Set PO Window.
	  * Purchase Order Window
	  */
	public void setPO_Window_ID (int PO_Window_ID);

	/** Get PO Window.
	  * Purchase Order Window
	  */
	public int getPO_Window_ID();

	public org.compiere.model.I_AD_Window getPO_Window() throws RuntimeException;

    /** Column name ReplicationType */
    public static final String COLUMNNAME_ReplicationType = "ReplicationType";

	/** Set Replication Type.
	  * Type of Data Replication
	  */
	public void setReplicationType (String ReplicationType);

	/** Get Replication Type.
	  * Type of Data Replication
	  */
	public String getReplicationType();

    /** Column name RequiresSync */
    public static final String COLUMNNAME_RequiresSync = "RequiresSync";

	/** Set Requires Sync.
	  * A flag indicating that the associated column or table definition is not synchronized with the database.
	  */
	public void setRequiresSync (boolean RequiresSync);

	/** Get Requires Sync.
	  * A flag indicating that the associated column or table definition is not synchronized with the database.
	  */
	public boolean isRequiresSync();

    /** Column name TableName */
    public static final String COLUMNNAME_TableName = "TableName";

	/** Set DB Table Name.
	  * Name of the table in the database
	  */
	public void setTableName (String TableName);

	/** Get DB Table Name.
	  * Name of the table in the database
	  */
	public String getTableName();

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
