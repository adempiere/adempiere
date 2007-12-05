/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Group
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_A_Asset_Group 
{

    /** TableName=A_Asset_Group */
    public static final String Table_Name = "A_Asset_Group";

    /** AD_Table_ID=542 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_Group_ID */
    public static final String COLUMNNAME_A_Asset_Group_ID = "A_Asset_Group_ID";

	/** Set Asset Group.
	  * Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID);

	/** Get Asset Group.
	  * Group of Assets
	  */
	public int getA_Asset_Group_ID();

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

    /** Column name IsCreateAsActive */
    public static final String COLUMNNAME_IsCreateAsActive = "IsCreateAsActive";

	/** Set Create As Active.
	  * Create Asset and activate it
	  */
	public void setIsCreateAsActive (boolean IsCreateAsActive);

	/** Get Create As Active.
	  * Create Asset and activate it
	  */
	public boolean isCreateAsActive();

    /** Column name IsDepreciated */
    public static final String COLUMNNAME_IsDepreciated = "IsDepreciated";

	/** Set Depreciate.
	  * The asset will be depreciated
	  */
	public void setIsDepreciated (boolean IsDepreciated);

	/** Get Depreciate.
	  * The asset will be depreciated
	  */
	public boolean isDepreciated();

    /** Column name IsOneAssetPerUOM */
    public static final String COLUMNNAME_IsOneAssetPerUOM = "IsOneAssetPerUOM";

	/** Set One Asset Per UOM.
	  * Create one asset per UOM
	  */
	public void setIsOneAssetPerUOM (boolean IsOneAssetPerUOM);

	/** Get One Asset Per UOM.
	  * Create one asset per UOM
	  */
	public boolean isOneAssetPerUOM();

    /** Column name IsOwned */
    public static final String COLUMNNAME_IsOwned = "IsOwned";

	/** Set Owned.
	  * The asset is owned by the organization
	  */
	public void setIsOwned (boolean IsOwned);

	/** Get Owned.
	  * The asset is owned by the organization
	  */
	public boolean isOwned();

    /** Column name IsTrackIssues */
    public static final String COLUMNNAME_IsTrackIssues = "IsTrackIssues";

	/** Set Track Issues.
	  * Enable tracking issues for this asset
	  */
	public void setIsTrackIssues (boolean IsTrackIssues);

	/** Get Track Issues.
	  * Enable tracking issues for this asset
	  */
	public boolean isTrackIssues();

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
}
