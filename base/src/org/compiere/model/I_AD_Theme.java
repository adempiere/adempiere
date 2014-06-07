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

/** Generated Interface for AD_Theme
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0RC
 */
public interface I_AD_Theme 
{

    /** TableName=AD_Theme */
    public static final String Table_Name = "AD_Theme";

    /** AD_Table_ID=53809 */
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

    /** Column name AD_Theme_ID */
    public static final String COLUMNNAME_AD_Theme_ID = "AD_Theme_ID";

	/** Set AD_Theme ID	  */
	public void setAD_Theme_ID (int AD_Theme_ID);

	/** Get AD_Theme ID	  */
	public int getAD_Theme_ID();

    /** Column name CacheHours */
    public static final String COLUMNNAME_CacheHours = "CacheHours";

	/** Set Cache Hours.
	  * The number of hours that the theme won't be changed and client can safely cache the file.
	  */
	public void setCacheHours (int CacheHours);

	/** Get Cache Hours.
	  * The number of hours that the theme won't be changed and client can safely cache the file.
	  */
	public int getCacheHours();

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

    /** Column name IsCached */
    public static final String COLUMNNAME_IsCached = "IsCached";

	/** Set Cache.
	  * Is the theme to be cached?
	  */
	public void setIsCached (boolean IsCached);

	/** Get Cache.
	  * Is the theme to be cached?
	  */
	public boolean isCached();

    /** Column name IsDefaultTheme */
    public static final String COLUMNNAME_IsDefaultTheme = "IsDefaultTheme";

	/** Set Is Default Theme.
	  * Is this theme to be the default theme used on startup?
	  */
	public void setIsDefaultTheme (boolean IsDefaultTheme);

	/** Get Is Default Theme.
	  * Is this theme to be the default theme used on startup?
	  */
	public boolean isDefaultTheme();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * The name of the theme
	  */
	public void setName (String Name);

	/** Get Name.
	  * The name of the theme
	  */
	public String getName();

    /** Column name ThemeDisplay */
    public static final String COLUMNNAME_ThemeDisplay = "ThemeDisplay";

	/** Set Display Name.
	  * The theme name to display.
	  */
	public void setThemeDisplay (String ThemeDisplay);

	/** Get Display Name.
	  * The theme name to display.
	  */
	public String getThemeDisplay();

    /** Column name ThemeOrigin */
    public static final String COLUMNNAME_ThemeOrigin = "ThemeOrigin";

	/** Set Origin.
	  * The type of theme structure.  Can be a FOLDER or JAR based theme.
	  */
	public void setThemeOrigin (String ThemeOrigin);

	/** Get Origin.
	  * The type of theme structure.  Can be a FOLDER or JAR based theme.
	  */
	public String getThemeOrigin();

    /** Column name ThemePriority */
    public static final String COLUMNNAME_ThemePriority = "ThemePriority";

	/** Set Priority.
	  * An interger indicating the relative priority of this theme.
	  */
	public void setThemePriority (int ThemePriority);

	/** Get Priority.
	  * An interger indicating the relative priority of this theme.
	  */
	public int getThemePriority();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
