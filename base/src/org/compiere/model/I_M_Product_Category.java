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

import java.util.*;
import java.sql.Timestamp;
import java.math.*;
import org.compiere.util.*;

    /** Generated Interface for M_Product_Category
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:51.468
     */
    public interface I_M_Product_Category 
{

    /** TableName=M_Product_Category */
    public static final String Table_Name = "M_Product_Category";

    /** AD_Table_ID=209 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = new BigDecimal(3);

    /** Load Meta Data */

    /** Column name AD_PrintColor_ID */
    public static final String COLUMNNAME_AD_PrintColor_ID = "AD_PrintColor_ID";

	/** Set Print Color.
	  * Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID);

	/** Get Print Color.
	  * Color used for printing and display
	  */
	public int getAD_PrintColor_ID();

	public I_AD_PrintColor getI_AD_PrintColor() throws Exception;

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

	public I_A_Asset_Group getI_A_Asset_Group() throws Exception;

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

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name IsSelfService */
    public static final String COLUMNNAME_IsSelfService = "IsSelfService";

	/** Set Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService);

	/** Get Self-Service.
	  * This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService();

    /** Column name MMPolicy */
    public static final String COLUMNNAME_MMPolicy = "MMPolicy";

	/** Set Material Policy.
	  * Material Movement Policy
	  */
	public void setMMPolicy (String MMPolicy);

	/** Get Material Policy.
	  * Material Movement Policy
	  */
	public String getMMPolicy();

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

    /** Column name M_Product_Category_Parent_ID */
    public static final String COLUMNNAME_M_Product_Category_Parent_ID = "M_Product_Category_Parent_ID";

	/** Set Parent Product Category	  */
	public void setM_Product_Category_Parent_ID (int M_Product_Category_Parent_ID);

	/** Get Parent Product Category	  */
	public int getM_Product_Category_Parent_ID();

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

    /** Column name PlannedMargin */
    public static final String COLUMNNAME_PlannedMargin = "PlannedMargin";

	/** Set Planned Margin %.
	  * Project's planned margin as a percentage
	  */
	public void setPlannedMargin (BigDecimal PlannedMargin);

	/** Get Planned Margin %.
	  * Project's planned margin as a percentage
	  */
	public BigDecimal getPlannedMargin();

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
