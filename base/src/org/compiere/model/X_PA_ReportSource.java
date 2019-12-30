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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for PA_ReportSource
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_PA_ReportSource extends PO implements I_PA_ReportSource, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_PA_ReportSource (Properties ctx, int PA_ReportSource_ID, String trxName)
    {
      super (ctx, PA_ReportSource_ID, trxName);
      /** if (PA_ReportSource_ID == 0)
        {
			setElementType (null);
			setIsIncludeNullsActivity (false);
// N
			setIsIncludeNullsBPartner (false);
// N
			setIsIncludeNullsCampaign (false);
// N
			setIsIncludeNullsElementValue (false);
// N
			setIsIncludeNullsLocation (false);
// N
			setIsIncludeNullsOrg (false);
// N
			setIsIncludeNullsOrgTrx (false);
// N
			setIsIncludeNullsProduct (false);
// N
			setIsIncludeNullsProject (false);
// N
			setIsIncludeNullsSalesRegion (false);
// N
			setIsIncludeNullsUser1 (false);
// N
			setIsIncludeNullsUser2 (false);
// N
			setIsIncludeNullsUser3 (false);
// N
			setIsIncludeNullsUser4 (false);
// N
			setIsIncludeNullsUserElement1 (false);
// N
			setIsIncludeNullsUserElement2 (false);
// N
			setIsIncludeNullsUserList1 (false);
// N
			setIsIncludeNullsUserList2 (false);
// N
			setPA_ReportSource_ID (0);
        } */
    }

    /** Load Constructor */
    public X_PA_ReportSource (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_PA_ReportSource[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Org getAD_OrgTrx() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Org)MTable.get(getCtx(), org.compiere.model.I_AD_Org.Table_Name)
			.getPO(getAD_OrgTrx_ID(), get_TrxName());	}

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getC_ElementValue() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getC_ElementValue_ID(), get_TrxName());	}

	/** Set Account Element.
		@param C_ElementValue_ID 
		Account Element
	  */
	public void setC_ElementValue_ID (int C_ElementValue_ID)
	{
		if (C_ElementValue_ID < 1) 
			set_Value (COLUMNNAME_C_ElementValue_ID, null);
		else 
			set_Value (COLUMNNAME_C_ElementValue_ID, Integer.valueOf(C_ElementValue_ID));
	}

	/** Get Account Element.
		@return Account Element
	  */
	public int getC_ElementValue_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ElementValue_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Location getC_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException
    {
		return (org.compiere.model.I_C_SalesRegion)MTable.get(getCtx(), org.compiere.model.I_C_SalesRegion.Table_Name)
			.getPO(getC_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** ElementType AD_Reference_ID=53280 */
	public static final int ELEMENTTYPE_AD_Reference_ID=53280;
	/** Account = AC */
	public static final String ELEMENTTYPE_Account = "AC";
	/** Activity = AY */
	public static final String ELEMENTTYPE_Activity = "AY";
	/** BPartner = BP */
	public static final String ELEMENTTYPE_BPartner = "BP";
	/** Location From = LF */
	public static final String ELEMENTTYPE_LocationFrom = "LF";
	/** Location To = LT */
	public static final String ELEMENTTYPE_LocationTo = "LT";
	/** Campaign = MC */
	public static final String ELEMENTTYPE_Campaign = "MC";
	/** Organization = OO */
	public static final String ELEMENTTYPE_Organization = "OO";
	/** Org Trx = OT */
	public static final String ELEMENTTYPE_OrgTrx = "OT";
	/** Project = PJ */
	public static final String ELEMENTTYPE_Project = "PJ";
	/** Product = PR */
	public static final String ELEMENTTYPE_Product = "PR";
	/** Sub Account = SA */
	public static final String ELEMENTTYPE_SubAccount = "SA";
	/** Sales Region = SR */
	public static final String ELEMENTTYPE_SalesRegion = "SR";
	/** User List 1 = U1 */
	public static final String ELEMENTTYPE_UserList1 = "U1";
	/** User List 2 = U2 */
	public static final String ELEMENTTYPE_UserList2 = "U2";
	/** User Element 1 = X1 */
	public static final String ELEMENTTYPE_UserElement1 = "X1";
	/** User Element 2 = X2 */
	public static final String ELEMENTTYPE_UserElement2 = "X2";
	/** Combination = CO */
	public static final String ELEMENTTYPE_Combination = "CO";
	/** User List 3 = U3 */
	public static final String ELEMENTTYPE_UserList3 = "U3";
	/** User List 4 = U4 */
	public static final String ELEMENTTYPE_UserList4 = "U4";
	/** Set Type.
		@param ElementType 
		Element Type (account or user defined)
	  */
	public void setElementType (String ElementType)
	{

		set_Value (COLUMNNAME_ElementType, ElementType);
	}

	/** Get Type.
		@return Element Type (account or user defined)
	  */
	public String getElementType () 
	{
		return (String)get_Value(COLUMNNAME_ElementType);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getElementType()));
    }

	/** Set Include Nulls in Activity.
		@param IsIncludeNullsActivity 
		Include nulls in the selection of the activity
	  */
	public void setIsIncludeNullsActivity (boolean IsIncludeNullsActivity)
	{
		set_Value (COLUMNNAME_IsIncludeNullsActivity, Boolean.valueOf(IsIncludeNullsActivity));
	}

	/** Get Include Nulls in Activity.
		@return Include nulls in the selection of the activity
	  */
	public boolean isIncludeNullsActivity () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsActivity);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in BPartner.
		@param IsIncludeNullsBPartner 
		Include nulls in the selection of the business partner
	  */
	public void setIsIncludeNullsBPartner (boolean IsIncludeNullsBPartner)
	{
		set_Value (COLUMNNAME_IsIncludeNullsBPartner, Boolean.valueOf(IsIncludeNullsBPartner));
	}

	/** Get Include Nulls in BPartner.
		@return Include nulls in the selection of the business partner
	  */
	public boolean isIncludeNullsBPartner () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsBPartner);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Campaign.
		@param IsIncludeNullsCampaign 
		Include nulls in the selection of the campaign
	  */
	public void setIsIncludeNullsCampaign (boolean IsIncludeNullsCampaign)
	{
		set_Value (COLUMNNAME_IsIncludeNullsCampaign, Boolean.valueOf(IsIncludeNullsCampaign));
	}

	/** Get Include Nulls in Campaign.
		@return Include nulls in the selection of the campaign
	  */
	public boolean isIncludeNullsCampaign () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsCampaign);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Account.
		@param IsIncludeNullsElementValue 
		Include nulls in the selection of the account
	  */
	public void setIsIncludeNullsElementValue (boolean IsIncludeNullsElementValue)
	{
		set_Value (COLUMNNAME_IsIncludeNullsElementValue, Boolean.valueOf(IsIncludeNullsElementValue));
	}

	/** Get Include Nulls in Account.
		@return Include nulls in the selection of the account
	  */
	public boolean isIncludeNullsElementValue () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsElementValue);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Location.
		@param IsIncludeNullsLocation 
		Include nulls in the selection of the location
	  */
	public void setIsIncludeNullsLocation (boolean IsIncludeNullsLocation)
	{
		set_Value (COLUMNNAME_IsIncludeNullsLocation, Boolean.valueOf(IsIncludeNullsLocation));
	}

	/** Get Include Nulls in Location.
		@return Include nulls in the selection of the location
	  */
	public boolean isIncludeNullsLocation () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsLocation);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Org.
		@param IsIncludeNullsOrg 
		Include nulls in the selection of the organization
	  */
	public void setIsIncludeNullsOrg (boolean IsIncludeNullsOrg)
	{
		set_Value (COLUMNNAME_IsIncludeNullsOrg, Boolean.valueOf(IsIncludeNullsOrg));
	}

	/** Get Include Nulls in Org.
		@return Include nulls in the selection of the organization
	  */
	public boolean isIncludeNullsOrg () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsOrg);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Org Trx.
		@param IsIncludeNullsOrgTrx 
		Include nulls in the selection of the organization transaction
	  */
	public void setIsIncludeNullsOrgTrx (boolean IsIncludeNullsOrgTrx)
	{
		set_Value (COLUMNNAME_IsIncludeNullsOrgTrx, Boolean.valueOf(IsIncludeNullsOrgTrx));
	}

	/** Get Include Nulls in Org Trx.
		@return Include nulls in the selection of the organization transaction
	  */
	public boolean isIncludeNullsOrgTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsOrgTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Product.
		@param IsIncludeNullsProduct 
		Include nulls in the selection of the product
	  */
	public void setIsIncludeNullsProduct (boolean IsIncludeNullsProduct)
	{
		set_Value (COLUMNNAME_IsIncludeNullsProduct, Boolean.valueOf(IsIncludeNullsProduct));
	}

	/** Get Include Nulls in Product.
		@return Include nulls in the selection of the product
	  */
	public boolean isIncludeNullsProduct () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsProduct);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Project.
		@param IsIncludeNullsProject 
		Include nulls in the selection of the project
	  */
	public void setIsIncludeNullsProject (boolean IsIncludeNullsProject)
	{
		set_Value (COLUMNNAME_IsIncludeNullsProject, Boolean.valueOf(IsIncludeNullsProject));
	}

	/** Get Include Nulls in Project.
		@return Include nulls in the selection of the project
	  */
	public boolean isIncludeNullsProject () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsProject);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in Sales Region.
		@param IsIncludeNullsSalesRegion 
		Include nulls in the selection of the sales region
	  */
	public void setIsIncludeNullsSalesRegion (boolean IsIncludeNullsSalesRegion)
	{
		set_Value (COLUMNNAME_IsIncludeNullsSalesRegion, Boolean.valueOf(IsIncludeNullsSalesRegion));
	}

	/** Get Include Nulls in Sales Region.
		@return Include nulls in the selection of the sales region
	  */
	public boolean isIncludeNullsSalesRegion () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsSalesRegion);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User 1.
		@param IsIncludeNullsUser1 
		Include nulls in the selection of the user 1
	  */
	public void setIsIncludeNullsUser1 (boolean IsIncludeNullsUser1)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUser1, Boolean.valueOf(IsIncludeNullsUser1));
	}

	/** Get Include Nulls in User 1.
		@return Include nulls in the selection of the user 1
	  */
	public boolean isIncludeNullsUser1 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUser1);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User 2.
		@param IsIncludeNullsUser2 
		Include nulls in the selection of the user 2
	  */
	public void setIsIncludeNullsUser2 (boolean IsIncludeNullsUser2)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUser2, Boolean.valueOf(IsIncludeNullsUser2));
	}

	/** Get Include Nulls in User 2.
		@return Include nulls in the selection of the user 2
	  */
	public boolean isIncludeNullsUser2 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUser2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User 3.
		@param IsIncludeNullsUser3 
		Include nulls in the selection of the user 3
	  */
	public void setIsIncludeNullsUser3 (boolean IsIncludeNullsUser3)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUser3, Boolean.valueOf(IsIncludeNullsUser3));
	}

	/** Get Include Nulls in User 3.
		@return Include nulls in the selection of the user 3
	  */
	public boolean isIncludeNullsUser3 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUser3);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User 4.
		@param IsIncludeNullsUser4 
		Include nulls in the selection of the user 4
	  */
	public void setIsIncludeNullsUser4 (boolean IsIncludeNullsUser4)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUser4, Boolean.valueOf(IsIncludeNullsUser4));
	}

	/** Get Include Nulls in User 4.
		@return Include nulls in the selection of the user 4
	  */
	public boolean isIncludeNullsUser4 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUser4);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User Element 1.
		@param IsIncludeNullsUserElement1 
		Include nulls in the selection of the user element 1
	  */
	public void setIsIncludeNullsUserElement1 (boolean IsIncludeNullsUserElement1)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUserElement1, Boolean.valueOf(IsIncludeNullsUserElement1));
	}

	/** Get Include Nulls in User Element 1.
		@return Include nulls in the selection of the user element 1
	  */
	public boolean isIncludeNullsUserElement1 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUserElement1);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User Element 2.
		@param IsIncludeNullsUserElement2 
		Include nulls in the selection of the user element 2
	  */
	public void setIsIncludeNullsUserElement2 (boolean IsIncludeNullsUserElement2)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUserElement2, Boolean.valueOf(IsIncludeNullsUserElement2));
	}

	/** Get Include Nulls in User Element 2.
		@return Include nulls in the selection of the user element 2
	  */
	public boolean isIncludeNullsUserElement2 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUserElement2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User List 1.
		@param IsIncludeNullsUserList1 
		Include nulls in the selection of the User List 1
	  */
	public void setIsIncludeNullsUserList1 (boolean IsIncludeNullsUserList1)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUserList1, Boolean.valueOf(IsIncludeNullsUserList1));
	}

	/** Get Include Nulls in User List 1.
		@return Include nulls in the selection of the User List 1
	  */
	public boolean isIncludeNullsUserList1 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUserList1);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User List 2.
		@param IsIncludeNullsUserList2 
		Include nulls in the selection of the User List 2
	  */
	public void setIsIncludeNullsUserList2 (boolean IsIncludeNullsUserList2)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUserList2, Boolean.valueOf(IsIncludeNullsUserList2));
	}

	/** Get Include Nulls in User List 2.
		@return Include nulls in the selection of the User List 2
	  */
	public boolean isIncludeNullsUserList2 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUserList2);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User List 3.
		@param IsIncludeNullsUserList3 
		Include nulls in the selection of the User List 3
	  */
	public void setIsIncludeNullsUserList3 (boolean IsIncludeNullsUserList3)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUserList3, Boolean.valueOf(IsIncludeNullsUserList3));
	}

	/** Get Include Nulls in User List 3.
		@return Include nulls in the selection of the User List 3
	  */
	public boolean isIncludeNullsUserList3 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUserList3);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Include Nulls in User List 4.
		@param IsIncludeNullsUserList4 
		Include nulls in the selection of the User List 4
	  */
	public void setIsIncludeNullsUserList4 (boolean IsIncludeNullsUserList4)
	{
		set_Value (COLUMNNAME_IsIncludeNullsUserList4, Boolean.valueOf(IsIncludeNullsUserList4));
	}

	/** Get Include Nulls in User List 4.
		@return Include nulls in the selection of the User List 4
	  */
	public boolean isIncludeNullsUserList4 () 
	{
		Object oo = get_Value(COLUMNNAME_IsIncludeNullsUserList4);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set List Sources.
		@param ListSources 
		List Report Line Sources
	  */
	public void setListSources (boolean ListSources)
	{
		set_Value (COLUMNNAME_ListSources, Boolean.valueOf(ListSources));
	}

	/** Get List Sources.
		@return List Report Line Sources
	  */
	public boolean isListSources () 
	{
		Object oo = get_Value(COLUMNNAME_ListSources);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set List Transactions.
		@param ListTrx 
		List the report transactions
	  */
	public void setListTrx (boolean ListTrx)
	{
		set_Value (COLUMNNAME_ListTrx, Boolean.valueOf(ListTrx));
	}

	/** Get List Transactions.
		@return List the report transactions
	  */
	public boolean isListTrx () 
	{
		Object oo = get_Value(COLUMNNAME_ListTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Org getOrg() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Org)MTable.get(getCtx(), org.compiere.model.I_AD_Org.Table_Name)
			.getPO(getOrg_ID(), get_TrxName());	}

	/** Set Organization.
		@param Org_ID 
		Organizational entity within client
	  */
	public void setOrg_ID (int Org_ID)
	{
		if (Org_ID < 1) 
			set_Value (COLUMNNAME_Org_ID, null);
		else 
			set_Value (COLUMNNAME_Org_ID, Integer.valueOf(Org_ID));
	}

	/** Get Organization.
		@return Organizational entity within client
	  */
	public int getOrg_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_PA_ReportColumn getPA_ReportColumn() throws RuntimeException
    {
		return (org.compiere.model.I_PA_ReportColumn)MTable.get(getCtx(), org.compiere.model.I_PA_ReportColumn.Table_Name)
			.getPO(getPA_ReportColumn_ID(), get_TrxName());	}

	/** Set Report Column.
		@param PA_ReportColumn_ID 
		Column in Report
	  */
	public void setPA_ReportColumn_ID (int PA_ReportColumn_ID)
	{
		if (PA_ReportColumn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_ReportColumn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_ReportColumn_ID, Integer.valueOf(PA_ReportColumn_ID));
	}

	/** Get Report Column.
		@return Column in Report
	  */
	public int getPA_ReportColumn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportColumn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_PA_ReportLine getPA_ReportLine() throws RuntimeException
    {
		return (org.compiere.model.I_PA_ReportLine)MTable.get(getCtx(), org.compiere.model.I_PA_ReportLine.Table_Name)
			.getPO(getPA_ReportLine_ID(), get_TrxName());	}

	/** Set Report Line.
		@param PA_ReportLine_ID Report Line	  */
	public void setPA_ReportLine_ID (int PA_ReportLine_ID)
	{
		if (PA_ReportLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_ReportLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_ReportLine_ID, Integer.valueOf(PA_ReportLine_ID));
	}

	/** Get Report Line.
		@return Report Line	  */
	public int getPA_ReportLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Report Source.
		@param PA_ReportSource_ID 
		Restriction of what will be shown in Report Line
	  */
	public void setPA_ReportSource_ID (int PA_ReportSource_ID)
	{
		if (PA_ReportSource_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PA_ReportSource_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PA_ReportSource_ID, Integer.valueOf(PA_ReportSource_ID));
	}

	/** Get Report Source.
		@return Restriction of what will be shown in Report Line
	  */
	public int getPA_ReportSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PA_ReportSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser1_ID(), get_TrxName());	}

	/** Set User List 1.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get User List 1.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser2_ID(), get_TrxName());	}

	/** Set User List 2.
		@param User2_ID 
		User defined list element #2
	  */
	public void setUser2_ID (int User2_ID)
	{
		if (User2_ID < 1) 
			set_Value (COLUMNNAME_User2_ID, null);
		else 
			set_Value (COLUMNNAME_User2_ID, Integer.valueOf(User2_ID));
	}

	/** Get User List 2.
		@return User defined list element #2
	  */
	public int getUser2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser3() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser3_ID(), get_TrxName());	}

	/** Set User List 3.
		@param User3_ID 
		User defined list element #3
	  */
	public void setUser3_ID (int User3_ID)
	{
		if (User3_ID < 1) 
			set_Value (COLUMNNAME_User3_ID, null);
		else 
			set_Value (COLUMNNAME_User3_ID, Integer.valueOf(User3_ID));
	}

	/** Get User List 3.
		@return User defined list element #3
	  */
	public int getUser3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser4() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser4_ID(), get_TrxName());	}

	/** Set User List 4.
		@param User4_ID 
		User defined list element #4
	  */
	public void setUser4_ID (int User4_ID)
	{
		if (User4_ID < 1) 
			set_Value (COLUMNNAME_User4_ID, null);
		else 
			set_Value (COLUMNNAME_User4_ID, Integer.valueOf(User4_ID));
	}

	/** Get User List 4.
		@return User defined list element #4
	  */
	public int getUser4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Element 1.
		@param UserElement1_ID 
		User defined accounting Element
	  */
	public void setUserElement1_ID (int UserElement1_ID)
	{
		if (UserElement1_ID < 1) 
			set_Value (COLUMNNAME_UserElement1_ID, null);
		else 
			set_Value (COLUMNNAME_UserElement1_ID, Integer.valueOf(UserElement1_ID));
	}

	/** Get User Element 1.
		@return User defined accounting Element
	  */
	public int getUserElement1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UserElement1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Element 2.
		@param UserElement2_ID 
		User defined accounting Element
	  */
	public void setUserElement2_ID (int UserElement2_ID)
	{
		if (UserElement2_ID < 1) 
			set_Value (COLUMNNAME_UserElement2_ID, null);
		else 
			set_Value (COLUMNNAME_UserElement2_ID, Integer.valueOf(UserElement2_ID));
	}

	/** Get User Element 2.
		@return User defined accounting Element
	  */
	public int getUserElement2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UserElement2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}