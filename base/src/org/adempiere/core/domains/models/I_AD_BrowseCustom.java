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

/** Generated Interface for AD_BrowseCustom
 *  @author Adempiere (generated) 
 *  @version Release 3.9.4
 */
public interface I_AD_BrowseCustom 
{

    /** TableName=AD_BrowseCustom */
    public static final String Table_Name = "AD_BrowseCustom";

    /** AD_Table_ID=54614 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Browse_ID */
    public static final String COLUMNNAME_AD_Browse_ID = "AD_Browse_ID";

	/** Set Smart Browse	  */
	public void setAD_Browse_ID (int AD_Browse_ID);

	/** Get Smart Browse	  */
	public int getAD_Browse_ID();

	public org.adempiere.core.domains.models.I_AD_Browse getAD_Browse() throws RuntimeException;

    /** Column name AD_BrowseCustom_ID */
    public static final String COLUMNNAME_AD_BrowseCustom_ID = "AD_BrowseCustom_ID";

	/** Set Browse Customization	  */
	public void setAD_BrowseCustom_ID (int AD_BrowseCustom_ID);

	/** Get Browse Customization	  */
	public int getAD_BrowseCustom_ID();

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

	public org.adempiere.core.domains.models.I_AD_Process getAD_Process() throws RuntimeException;

    /** Column name AD_Role_ID */
    public static final String COLUMNNAME_AD_Role_ID = "AD_Role_ID";

	/** Set Role.
	  * Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID);

	/** Get Role.
	  * Responsibility Role
	  */
	public int getAD_Role_ID();

	public org.adempiere.core.domains.models.I_AD_Role getAD_Role() throws RuntimeException;

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

	public org.adempiere.core.domains.models.I_AD_Table getAD_Table() throws RuntimeException;

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.adempiere.core.domains.models.I_AD_User getAD_User() throws RuntimeException;

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

	public org.adempiere.core.domains.models.I_AD_Window getAD_Window() throws RuntimeException;

    /** Column name ASP_Level_ID */
    public static final String COLUMNNAME_ASP_Level_ID = "ASP_Level_ID";

	/** Set ASP Level	  */
	public void setASP_Level_ID (int ASP_Level_ID);

	/** Get ASP Level	  */
	public int getASP_Level_ID();

	public org.adempiere.core.domains.models.I_ASP_Level getASP_Level() throws RuntimeException;

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

    /** Column name HierarchyType */
    public static final String COLUMNNAME_HierarchyType = "HierarchyType";

	/** Set Hierarchy Type.
	  * Hierarchy Type (Hierarchy: Add, Merge or Overwrite)
	  */
	public void setHierarchyType (String HierarchyType);

	/** Get Hierarchy Type.
	  * Hierarchy Type (Hierarchy: Add, Merge or Overwrite)
	  */
	public String getHierarchyType();

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

    /** Column name IsCollapsibleByDefault */
    public static final String COLUMNNAME_IsCollapsibleByDefault = "IsCollapsibleByDefault";

	/** Set Is collapsible by default.
	  * Flag to indicate if is collapsible by default
	  */
	public void setIsCollapsibleByDefault (String IsCollapsibleByDefault);

	/** Get Is collapsible by default.
	  * Flag to indicate if is collapsible by default
	  */
	public String getIsCollapsibleByDefault();

    /** Column name IsDeleteable */
    public static final String COLUMNNAME_IsDeleteable = "IsDeleteable";

	/** Set Records deletable.
	  * Indicates if records can be deleted from the database
	  */
	public void setIsDeleteable (String IsDeleteable);

	/** Get Records deletable.
	  * Indicates if records can be deleted from the database
	  */
	public String getIsDeleteable();

    /** Column name IsExecutedQueryByDefault */
    public static final String COLUMNNAME_IsExecutedQueryByDefault = "IsExecutedQueryByDefault";

	/** Set Is executed query by default.
	  * Is executed query by default
	  */
	public void setIsExecutedQueryByDefault (String IsExecutedQueryByDefault);

	/** Get Is executed query by default.
	  * Is executed query by default
	  */
	public String getIsExecutedQueryByDefault();

    /** Column name IsSelectedByDefault */
    public static final String COLUMNNAME_IsSelectedByDefault = "IsSelectedByDefault";

	/** Set Is selected by default.
	  * Allows auto select rows of a browser
	  */
	public void setIsSelectedByDefault (String IsSelectedByDefault);

	/** Get Is selected by default.
	  * Allows auto select rows of a browser
	  */
	public String getIsSelectedByDefault();

    /** Column name IsShowTotal */
    public static final String COLUMNNAME_IsShowTotal = "IsShowTotal";

	/** Set Show Total.
	  * Show totals into Smart Browser
	  */
	public void setIsShowTotal (String IsShowTotal);

	/** Get Show Total.
	  * Show totals into Smart Browser
	  */
	public String getIsShowTotal();

    /** Column name IsUpdateable */
    public static final String COLUMNNAME_IsUpdateable = "IsUpdateable";

	/** Set Updatable.
	  * Determines, if the field can be updated
	  */
	public void setIsUpdateable (String IsUpdateable);

	/** Get Updatable.
	  * Determines, if the field can be updated
	  */
	public String getIsUpdateable();

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
