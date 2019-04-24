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

/** Generated Interface for PA_DashboardContent
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_PA_DashboardContent 
{

    /** TableName=PA_DashboardContent */
    public static final String Table_Name = "PA_DashboardContent";

    /** AD_Table_ID=50010 */
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

	public org.adempiere.model.I_AD_Browse getAD_Browse() throws RuntimeException;

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

    /** Column name ColumnNo */
    public static final String COLUMNNAME_ColumnNo = "ColumnNo";

	/** Set Column No.
	  * Dashboard content column number
	  */
	public void setColumnNo (int ColumnNo);

	/** Get Column No.
	  * Dashboard content column number
	  */
	public int getColumnNo();

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

    /** Column name GoalDisplay */
    public static final String COLUMNNAME_GoalDisplay = "GoalDisplay";

	/** Set Goal Display.
	  * Type of goal display on dashboard
	  */
	public void setGoalDisplay (String GoalDisplay);

	/** Get Goal Display.
	  * Type of goal display on dashboard
	  */
	public String getGoalDisplay();

    /** Column name HTML */
    public static final String COLUMNNAME_HTML = "HTML";

	/** Set HTML	  */
	public void setHTML (String HTML);

	/** Get HTML	  */
	public String getHTML();

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

    /** Column name IsCollapsible */
    public static final String COLUMNNAME_IsCollapsible = "IsCollapsible";

	/** Set Collapsible.
	  * Flag to indicate the state of the dashboard panel
	  */
	public void setIsCollapsible (boolean IsCollapsible);

	/** Get Collapsible.
	  * Flag to indicate the state of the dashboard panel
	  */
	public boolean isCollapsible();

    /** Column name IsEventRequired */
    public static final String COLUMNNAME_IsEventRequired = "IsEventRequired";

	/** Set IsEventRequired	  */
	public void setIsEventRequired (boolean IsEventRequired);

	/** Get IsEventRequired	  */
	public boolean isEventRequired();

    /** Column name IsOpenByDefault */
    public static final String COLUMNNAME_IsOpenByDefault = "IsOpenByDefault";

	/** Set Open By Default	  */
	public void setIsOpenByDefault (boolean IsOpenByDefault);

	/** Get Open By Default	  */
	public boolean isOpenByDefault();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

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

    /** Column name PA_DashboardContent_ID */
    public static final String COLUMNNAME_PA_DashboardContent_ID = "PA_DashboardContent_ID";

	/** Set Dashboard Content	  */
	public void setPA_DashboardContent_ID (int PA_DashboardContent_ID);

	/** Get Dashboard Content	  */
	public int getPA_DashboardContent_ID();

    /** Column name PA_Goal_ID */
    public static final String COLUMNNAME_PA_Goal_ID = "PA_Goal_ID";

	/** Set Goal.
	  * Performance Goal
	  */
	public void setPA_Goal_ID (int PA_Goal_ID);

	/** Get Goal.
	  * Performance Goal
	  */
	public int getPA_Goal_ID();

	public org.compiere.model.I_PA_Goal getPA_Goal() throws RuntimeException;

    /** Column name PageSize */
    public static final String COLUMNNAME_PageSize = "PageSize";

	/** Set PageSize	  */
	public void setPageSize (BigDecimal PageSize);

	/** Get PageSize	  */
	public BigDecimal getPageSize();

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

    /** Column name Zoom_Field_ID */
    public static final String COLUMNNAME_Zoom_Field_ID = "Zoom_Field_ID";

	/** Set Zoom_Field_ID	  */
	public void setZoom_Field_ID (int Zoom_Field_ID);

	/** Get Zoom_Field_ID	  */
	public int getZoom_Field_ID();

	public org.adempiere.model.I_AD_Browse_Field getZoom_Field() throws RuntimeException;

    /** Column name Zoom_Tab_ID */
    public static final String COLUMNNAME_Zoom_Tab_ID = "Zoom_Tab_ID";

	/** Set Zoom_Tab_ID	  */
	public void setZoom_Tab_ID (int Zoom_Tab_ID);

	/** Get Zoom_Tab_ID	  */
	public int getZoom_Tab_ID();

	public org.compiere.model.I_AD_Tab getZoom_Tab() throws RuntimeException;

    /** Column name Zoom_Window_ID */
    public static final String COLUMNNAME_Zoom_Window_ID = "Zoom_Window_ID";

	/** Set Zoom_Window_ID	  */
	public void setZoom_Window_ID (int Zoom_Window_ID);

	/** Get Zoom_Window_ID	  */
	public int getZoom_Window_ID();

	public org.compiere.model.I_AD_Window getZoom_Window() throws RuntimeException;

    /** Column name ZulFilePath */
    public static final String COLUMNNAME_ZulFilePath = "ZulFilePath";

	/** Set ZUL File Path.
	  * Absolute path to zul file
	  */
	public void setZulFilePath (String ZulFilePath);

	/** Get ZUL File Path.
	  * Absolute path to zul file
	  */
	public String getZulFilePath();

    /** Column name onevent */
    public static final String COLUMNNAME_onevent = "onevent";

	/** Set onevent	  */
	public void setonevent (String onevent);

	/** Get onevent	  */
	public String getonevent();
}
