/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software, you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation, either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY, without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program, if not, write to the Free Software        *
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

/** Generated Interface for PA_ReportSource
 *  @author Adempiere (generated) 
 *  @version Release 3.5.2a
 */
public interface I_PA_ReportSource 
{

    /** TableName=PA_ReportSource */
    public static final String Table_Name = "PA_ReportSource";

    /** AD_Table_ID=450 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public I_C_Activity getC_Activity() throws Exception;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public I_C_BPartner getC_BPartner() throws Exception;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public I_C_Campaign getC_Campaign() throws Exception;

    /** Column name C_ElementValue_ID */
    public static final String COLUMNNAME_C_ElementValue_ID = "C_ElementValue_ID";

	/** Set Account Element.
	  * Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID);

	/** Get Account Element.
	  * Account Element
	  */
	public int getC_ElementValue_ID();

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public I_C_Project getC_Project() throws Exception;

    /** Column name C_SalesRegion_ID */
    public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

	/** Set Sales Region.
	  * Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID);

	/** Get Sales Region.
	  * Sales coverage region
	  */
	public int getC_SalesRegion_ID();

	public I_C_SalesRegion getC_SalesRegion() throws Exception;

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

    /** Column name ElementType */
    public static final String COLUMNNAME_ElementType = "ElementType";

	/** Set Type.
	  * Element Type (account or user defined)
	  */
	public void setElementType (String ElementType);

	/** Get Type.
	  * Element Type (account or user defined)
	  */
	public String getElementType();

    /** Column name IsIncludeNullsActivity */
    public static final String COLUMNNAME_IsIncludeNullsActivity = "IsIncludeNullsActivity";

	/** Set Include Nulls in Activity.
	  * Include nulls in the selection of the activity
	  */
	public void setIsIncludeNullsActivity (boolean IsIncludeNullsActivity);

	/** Get Include Nulls in Activity.
	  * Include nulls in the selection of the activity
	  */
	public boolean isIncludeNullsActivity();

    /** Column name IsIncludeNullsBPartner */
    public static final String COLUMNNAME_IsIncludeNullsBPartner = "IsIncludeNullsBPartner";

	/** Set Include Nulls in BPartner.
	  * Include nulls in the selection of the business partner
	  */
	public void setIsIncludeNullsBPartner (boolean IsIncludeNullsBPartner);

	/** Get Include Nulls in BPartner.
	  * Include nulls in the selection of the business partner
	  */
	public boolean isIncludeNullsBPartner();

    /** Column name IsIncludeNullsCampaign */
    public static final String COLUMNNAME_IsIncludeNullsCampaign = "IsIncludeNullsCampaign";

	/** Set Include Nulls in Campaign.
	  * Include nulls in the selection of the campaign
	  */
	public void setIsIncludeNullsCampaign (boolean IsIncludeNullsCampaign);

	/** Get Include Nulls in Campaign.
	  * Include nulls in the selection of the campaign
	  */
	public boolean isIncludeNullsCampaign();

    /** Column name IsIncludeNullsElementValue */
    public static final String COLUMNNAME_IsIncludeNullsElementValue = "IsIncludeNullsElementValue";

	/** Set Include Nulls in Account.
	  * Include nulls in the selection of the account
	  */
	public void setIsIncludeNullsElementValue (boolean IsIncludeNullsElementValue);

	/** Get Include Nulls in Account.
	  * Include nulls in the selection of the account
	  */
	public boolean isIncludeNullsElementValue();

    /** Column name IsIncludeNullsLocation */
    public static final String COLUMNNAME_IsIncludeNullsLocation = "IsIncludeNullsLocation";

	/** Set Include Nulls in Location.
	  * Include nulls in the selection of the location
	  */
	public void setIsIncludeNullsLocation (boolean IsIncludeNullsLocation);

	/** Get Include Nulls in Location.
	  * Include nulls in the selection of the location
	  */
	public boolean isIncludeNullsLocation();

    /** Column name IsIncludeNullsOrg */
    public static final String COLUMNNAME_IsIncludeNullsOrg = "IsIncludeNullsOrg";

	/** Set Include Nulls in Org.
	  * Include nulls in the selection of the organization
	  */
	public void setIsIncludeNullsOrg (boolean IsIncludeNullsOrg);

	/** Get Include Nulls in Org.
	  * Include nulls in the selection of the organization
	  */
	public boolean isIncludeNullsOrg();

    /** Column name IsIncludeNullsProduct */
    public static final String COLUMNNAME_IsIncludeNullsProduct = "IsIncludeNullsProduct";

	/** Set Include Nulls in Product.
	  * Include nulls in the selection of the product
	  */
	public void setIsIncludeNullsProduct (boolean IsIncludeNullsProduct);

	/** Get Include Nulls in Product.
	  * Include nulls in the selection of the product
	  */
	public boolean isIncludeNullsProduct();

    /** Column name IsIncludeNullsProject */
    public static final String COLUMNNAME_IsIncludeNullsProject = "IsIncludeNullsProject";

	/** Set Include Nulls in Project.
	  * Include nulls in the selection of the project
	  */
	public void setIsIncludeNullsProject (boolean IsIncludeNullsProject);

	/** Get Include Nulls in Project.
	  * Include nulls in the selection of the project
	  */
	public boolean isIncludeNullsProject();

    /** Column name IsIncludeNullsSalesRegion */
    public static final String COLUMNNAME_IsIncludeNullsSalesRegion = "IsIncludeNullsSalesRegion";

	/** Set Include Nulls in Sales Region.
	  * Include nulls in the selection of the sales region
	  */
	public void setIsIncludeNullsSalesRegion (boolean IsIncludeNullsSalesRegion);

	/** Get Include Nulls in Sales Region.
	  * Include nulls in the selection of the sales region
	  */
	public boolean isIncludeNullsSalesRegion();

    /** Column name IsIncludeNullsUserElement1 */
    public static final String COLUMNNAME_IsIncludeNullsUserElement1 = "IsIncludeNullsUserElement1";

	/** Set Include Nulls in User Element 1.
	  * Include nulls in the selection of the user element 1
	  */
	public void setIsIncludeNullsUserElement1 (boolean IsIncludeNullsUserElement1);

	/** Get Include Nulls in User Element 1.
	  * Include nulls in the selection of the user element 1
	  */
	public boolean isIncludeNullsUserElement1();

    /** Column name IsIncludeNullsUserElement2 */
    public static final String COLUMNNAME_IsIncludeNullsUserElement2 = "IsIncludeNullsUserElement2";

	/** Set Include Nulls in User Element 2.
	  * Include nulls in the selection of the user element 2
	  */
	public void setIsIncludeNullsUserElement2 (boolean IsIncludeNullsUserElement2);

	/** Get Include Nulls in User Element 2.
	  * Include nulls in the selection of the user element 2
	  */
	public boolean isIncludeNullsUserElement2();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws Exception;

    /** Column name Org_ID */
    public static final String COLUMNNAME_Org_ID = "Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getOrg_ID();

    /** Column name PA_ReportLine_ID */
    public static final String COLUMNNAME_PA_ReportLine_ID = "PA_ReportLine_ID";

	/** Set Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID);

	/** Get Report Line	  */
	public int getPA_ReportLine_ID();

	public I_PA_ReportLine getPA_ReportLine() throws Exception;

    /** Column name PA_ReportSource_ID */
    public static final String COLUMNNAME_PA_ReportSource_ID = "PA_ReportSource_ID";

	/** Set Report Source.
	  * Restriction of what will be shown in Report Line
	  */
	public void setPA_ReportSource_ID (int PA_ReportSource_ID);

	/** Get Report Source.
	  * Restriction of what will be shown in Report Line
	  */
	public int getPA_ReportSource_ID();

    /** Column name UserElement1_ID */
    public static final String COLUMNNAME_UserElement1_ID = "UserElement1_ID";

	/** Set User Element 1.
	  * User defined accounting Element
	  */
	public void setUserElement1_ID (int UserElement1_ID);

	/** Get User Element 1.
	  * User defined accounting Element
	  */
	public int getUserElement1_ID();

    /** Column name UserElement2_ID */
    public static final String COLUMNNAME_UserElement2_ID = "UserElement2_ID";

	/** Set User Element 2.
	  * User defined accounting Element
	  */
	public void setUserElement2_ID (int UserElement2_ID);

	/** Get User Element 2.
	  * User defined accounting Element
	  */
	public int getUserElement2_ID();
}
