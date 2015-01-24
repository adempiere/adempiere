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
package org.spinsuite.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for SPS_Menu
 *  @author Adempiere (generated) 
 *  @version Release 3.7.0LTS
 */
public interface I_SPS_Menu 
{

    /** TableName=SPS_Menu */
    public static final String Table_Name = "SPS_Menu";

    /** AD_Table_ID=53518 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 4 - System 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(4);

    /** Load Meta Data */

    /** Column name Action */
    public static final String COLUMNNAME_Action = "Action";

	/** Set Action.
	  * Indicates the Action to be performed
	  */
	public void setAction (String Action);

	/** Get Action.
	  * Indicates the Action to be performed
	  */
	public String getAction();

    /** Column name ActivityMenu_ID */
    public static final String COLUMNNAME_ActivityMenu_ID = "ActivityMenu_ID";

	/** Set Activity Menu	  */
	public void setActivityMenu_ID (int ActivityMenu_ID);

	/** Get Activity Menu	  */
	public int getActivityMenu_ID();

	public org.spinsuite.model.I_SPS_Menu getActivityMenu() throws RuntimeException;

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Form_ID */
    public static final String COLUMNNAME_AD_Form_ID = "AD_Form_ID";

	/** Set Special Form.
	  * Special Form
	  */
	public void setAD_Form_ID (int AD_Form_ID);

	/** Get Special Form.
	  * Special Form
	  */
	public int getAD_Form_ID();

	public org.compiere.model.I_AD_Form getAD_Form() throws RuntimeException;

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

	public org.compiere.model.I_AD_Process getAD_Process() throws RuntimeException;

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

    /** Column name DeploymentType */
    public static final String COLUMNNAME_DeploymentType = "DeploymentType";

	/** Set Deployment Type	  */
	public void setDeploymentType (String DeploymentType);

	/** Get Deployment Type	  */
	public String getDeploymentType();

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

    /** Column name ErrImgUrl */
    public static final String COLUMNNAME_ErrImgUrl = "ErrImgUrl";

	/** Set Error Image URL	  */
	public void setErrImgUrl (String ErrImgUrl);

	/** Get Error Image URL	  */
	public String getErrImgUrl();

    /** Column name GroupByClause */
    public static final String COLUMNNAME_GroupByClause = "GroupByClause";

	/** Set GROUP BY Clause	  */
	public void setGroupByClause (String GroupByClause);

	/** Get GROUP BY Clause	  */
	public String getGroupByClause();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

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

    /** Column name IsInsertRecord */
    public static final String COLUMNNAME_IsInsertRecord = "IsInsertRecord";

	/** Set Insert Record.
	  * The user can insert a new Record
	  */
	public void setIsInsertRecord (String IsInsertRecord);

	/** Get Insert Record.
	  * The user can insert a new Record
	  */
	public String getIsInsertRecord();

    /** Column name IsReadWrite */
    public static final String COLUMNNAME_IsReadWrite = "IsReadWrite";

	/** Set Read Write.
	  * Field is read / write
	  */
	public void setIsReadWrite (String IsReadWrite);

	/** Get Read Write.
	  * Field is read / write
	  */
	public String getIsReadWrite();

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name MenuType */
    public static final String COLUMNNAME_MenuType = "MenuType";

	/** Set Menu Type	  */
	public void setMenuType (String MenuType);

	/** Get Menu Type	  */
	public String getMenuType();

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

    /** Column name QuickActionMenu_ID */
    public static final String COLUMNNAME_QuickActionMenu_ID = "QuickActionMenu_ID";

	/** Set Quick Action Menu	  */
	public void setQuickActionMenu_ID (int QuickActionMenu_ID);

	/** Get Quick Action Menu	  */
	public int getQuickActionMenu_ID();

	public org.spinsuite.model.I_SPS_Menu getQuickActionMenu() throws RuntimeException;

    /** Column name SPS_Menu_ID */
    public static final String COLUMNNAME_SPS_Menu_ID = "SPS_Menu_ID";

	/** Set Menu Option	  */
	public void setSPS_Menu_ID (int SPS_Menu_ID);

	/** Get Menu Option	  */
	public int getSPS_Menu_ID();

    /** Column name SPS_SyncMenu_ID */
    public static final String COLUMNNAME_SPS_SyncMenu_ID = "SPS_SyncMenu_ID";

	/** Set Sync/Option Menu	  */
	public void setSPS_SyncMenu_ID (int SPS_SyncMenu_ID);

	/** Get Sync/Option Menu	  */
	public int getSPS_SyncMenu_ID();

	public org.spinsuite.model.I_SPS_SyncMenu getSPS_SyncMenu() throws RuntimeException;

    /** Column name SPS_Table_ID */
    public static final String COLUMNNAME_SPS_Table_ID = "SPS_Table_ID";

	/** Set Mobile Table	  */
	public void setSPS_Table_ID (int SPS_Table_ID);

	/** Get Mobile Table	  */
	public int getSPS_Table_ID();

	public org.spinsuite.model.I_SPS_Table getSPS_Table() throws RuntimeException;

    /** Column name SPS_Window_ID */
    public static final String COLUMNNAME_SPS_Window_ID = "SPS_Window_ID";

	/** Set Window Mobile	  */
	public void setSPS_Window_ID (int SPS_Window_ID);

	/** Get Window Mobile	  */
	public int getSPS_Window_ID();

	public org.spinsuite.model.I_SPS_Window getSPS_Window() throws RuntimeException;

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
