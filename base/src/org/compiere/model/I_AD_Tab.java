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

/** Generated Interface for AD_Tab
 *  @author Adempiere (generated) 
 *  @version Release 3.5.3a
 */
public interface I_AD_Tab 
{

    /** TableName=AD_Tab */
    public static final String Table_Name = "AD_Tab";

    /** AD_Table_ID=106 */
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

    /** Column name AD_Column_ID */
    public static final String COLUMNNAME_AD_Column_ID = "AD_Column_ID";

	/** Set Column.
	  * Column in the table
	  */
	public void setAD_Column_ID (int AD_Column_ID);

	/** Get Column.
	  * Column in the table
	  */
	public int getAD_Column_ID();

	public I_AD_Column getAD_Column() throws RuntimeException;

    /** Column name AD_ColumnSortOrder_ID */
    public static final String COLUMNNAME_AD_ColumnSortOrder_ID = "AD_ColumnSortOrder_ID";

	/** Set Order Column.
	  * Column determining the order
	  */
	public void setAD_ColumnSortOrder_ID (int AD_ColumnSortOrder_ID);

	/** Get Order Column.
	  * Column determining the order
	  */
	public int getAD_ColumnSortOrder_ID();

    /** Column name AD_ColumnSortYesNo_ID */
    public static final String COLUMNNAME_AD_ColumnSortYesNo_ID = "AD_ColumnSortYesNo_ID";

	/** Set Included Column.
	  * Column determining if a Table Column is included in Ordering
	  */
	public void setAD_ColumnSortYesNo_ID (int AD_ColumnSortYesNo_ID);

	/** Get Included Column.
	  * Column determining if a Table Column is included in Ordering
	  */
	public int getAD_ColumnSortYesNo_ID();

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

	public I_AD_Image getAD_Image() throws RuntimeException;

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

    /** Column name AD_Tab_ID */
    public static final String COLUMNNAME_AD_Tab_ID = "AD_Tab_ID";

	/** Set Tab.
	  * Tab within a Window
	  */
	public void setAD_Tab_ID (int AD_Tab_ID);

	/** Get Tab.
	  * Tab within a Window
	  */
	public int getAD_Tab_ID();

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

	public I_AD_Table getAD_Table() throws RuntimeException;

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

	public I_AD_Window getAD_Window() throws RuntimeException;

    /** Column name CommitWarning */
    public static final String COLUMNNAME_CommitWarning = "CommitWarning";

	/** Set Commit Warning.
	  * Warning displayed when saving
	  */
	public void setCommitWarning (String CommitWarning);

	/** Get Commit Warning.
	  * Warning displayed when saving
	  */
	public String getCommitWarning();

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

    /** Column name HasTree */
    public static final String COLUMNNAME_HasTree = "HasTree";

	/** Set Has Tree.
	  * Window has Tree Graph
	  */
	public void setHasTree (boolean HasTree);

	/** Get Has Tree.
	  * Window has Tree Graph
	  */
	public boolean isHasTree();

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

    /** Column name ImportFields */
    public static final String COLUMNNAME_ImportFields = "ImportFields";

	/** Set Import Fields.
	  * Create Fields from Table Columns
	  */
	public void setImportFields (String ImportFields);

	/** Get Import Fields.
	  * Create Fields from Table Columns
	  */
	public String getImportFields();

    /** Column name Included_Tab_ID */
    public static final String COLUMNNAME_Included_Tab_ID = "Included_Tab_ID";

	/** Set Included Tab.
	  * Included Tab in this Tab (Master Dateail)
	  */
	public void setIncluded_Tab_ID (int Included_Tab_ID);

	/** Get Included Tab.
	  * Included Tab in this Tab (Master Dateail)
	  */
	public int getIncluded_Tab_ID();

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

    /** Column name IsAdvancedTab */
    public static final String COLUMNNAME_IsAdvancedTab = "IsAdvancedTab";

	/** Set Advanced Tab.
	  * This Tab contains advanced Functionality
	  */
	public void setIsAdvancedTab (boolean IsAdvancedTab);

	/** Get Advanced Tab.
	  * This Tab contains advanced Functionality
	  */
	public boolean isAdvancedTab();

    /** Column name IsInfoTab */
    public static final String COLUMNNAME_IsInfoTab = "IsInfoTab";

	/** Set Accounting Tab.
	  * This Tab contains accounting information
	  */
	public void setIsInfoTab (boolean IsInfoTab);

	/** Get Accounting Tab.
	  * This Tab contains accounting information
	  */
	public boolean isInfoTab();

    /** Column name IsInsertRecord */
    public static final String COLUMNNAME_IsInsertRecord = "IsInsertRecord";

	/** Set Insert Record.
	  * The user can insert a new Record
	  */
	public void setIsInsertRecord (boolean IsInsertRecord);

	/** Get Insert Record.
	  * The user can insert a new Record
	  */
	public boolean isInsertRecord();

    /** Column name IsReadOnly */
    public static final String COLUMNNAME_IsReadOnly = "IsReadOnly";

	/** Set Read Only.
	  * Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly);

	/** Get Read Only.
	  * Field is read only
	  */
	public boolean isReadOnly();

    /** Column name IsSingleRow */
    public static final String COLUMNNAME_IsSingleRow = "IsSingleRow";

	/** Set Single Row Layout.
	  * Default for toggle between Single- and Multi-Row (Grid) Layout
	  */
	public void setIsSingleRow (boolean IsSingleRow);

	/** Get Single Row Layout.
	  * Default for toggle between Single- and Multi-Row (Grid) Layout
	  */
	public boolean isSingleRow();

    /** Column name IsSortTab */
    public static final String COLUMNNAME_IsSortTab = "IsSortTab";

	/** Set Order Tab.
	  * The Tab determines the Order
	  */
	public void setIsSortTab (boolean IsSortTab);

	/** Get Order Tab.
	  * The Tab determines the Order
	  */
	public boolean isSortTab();

    /** Column name IsTranslationTab */
    public static final String COLUMNNAME_IsTranslationTab = "IsTranslationTab";

	/** Set TranslationTab.
	  * This Tab contains translation information
	  */
	public void setIsTranslationTab (boolean IsTranslationTab);

	/** Get TranslationTab.
	  * This Tab contains translation information
	  */
	public boolean isTranslationTab();

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

    /** Column name OrderByClause */
    public static final String COLUMNNAME_OrderByClause = "OrderByClause";

	/** Set Sql ORDER BY.
	  * Fully qualified ORDER BY clause
	  */
	public void setOrderByClause (String OrderByClause);

	/** Get Sql ORDER BY.
	  * Fully qualified ORDER BY clause
	  */
	public String getOrderByClause();

    /** Column name Parent_Column_ID */
    public static final String COLUMNNAME_Parent_Column_ID = "Parent_Column_ID";

	/** Set Parent Column.
	  * The link column on the parent tab.
	  */
	public void setParent_Column_ID (int Parent_Column_ID);

	/** Get Parent Column.
	  * The link column on the parent tab.
	  */
	public int getParent_Column_ID();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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

    /** Column name TabLevel */
    public static final String COLUMNNAME_TabLevel = "TabLevel";

	/** Set Tab Level.
	  * Hierarchical Tab Level (0 = top)
	  */
	public void setTabLevel (int TabLevel);

	/** Get Tab Level.
	  * Hierarchical Tab Level (0 = top)
	  */
	public int getTabLevel();

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

    /** Column name WhereClause */
    public static final String COLUMNNAME_WhereClause = "WhereClause";

	/** Set Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public void setWhereClause (String WhereClause);

	/** Get Sql WHERE.
	  * Fully qualified SQL WHERE clause
	  */
	public String getWhereClause();
}
