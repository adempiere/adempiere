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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for AD_ClientInfo
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_AD_ClientInfo extends PO implements I_AD_ClientInfo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100622L;

    /** Standard Constructor */
    public X_AD_ClientInfo (Properties ctx, int AD_ClientInfo_ID, String trxName)
    {
      super (ctx, AD_ClientInfo_ID, trxName);
      /** if (AD_ClientInfo_ID == 0)
        {
			setIsDiscountLineAmt (false);
        } */
    }

    /** Load Constructor */
    public X_AD_ClientInfo (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
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
      StringBuffer sb = new StringBuffer ("X_AD_ClientInfo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_AD_Tree getAD_Tree_Activity() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Activity_ID(), get_TrxName());	}

	/** Set Activity Tree.
		@param AD_Tree_Activity_ID 
		Trees are used for (financial) reporting
	  */
	public void setAD_Tree_Activity_ID (int AD_Tree_Activity_ID)
	{
		if (AD_Tree_Activity_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Activity_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Activity_ID, Integer.valueOf(AD_Tree_Activity_ID));
	}

	/** Get Activity Tree.
		@return Trees are used for (financial) reporting
	  */
	public int getAD_Tree_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_BPartner() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_BPartner_ID(), get_TrxName());	}

	/** Set BPartner Tree.
		@param AD_Tree_BPartner_ID 
		Trees are used for (financial) reporting
	  */
	public void setAD_Tree_BPartner_ID (int AD_Tree_BPartner_ID)
	{
		if (AD_Tree_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_BPartner_ID, Integer.valueOf(AD_Tree_BPartner_ID));
	}

	/** Get BPartner Tree.
		@return Trees are used for (financial) reporting
	  */
	public int getAD_Tree_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Campaign() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Campaign_ID(), get_TrxName());	}

	/** Set Campaign Tree.
		@param AD_Tree_Campaign_ID 
		Trees are used for (financial) reporting
	  */
	public void setAD_Tree_Campaign_ID (int AD_Tree_Campaign_ID)
	{
		if (AD_Tree_Campaign_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Campaign_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Campaign_ID, Integer.valueOf(AD_Tree_Campaign_ID));
	}

	/** Get Campaign Tree.
		@return Trees are used for (financial) reporting
	  */
	public int getAD_Tree_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Menu() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Menu_ID(), get_TrxName());	}

	/** Set Menu Tree.
		@param AD_Tree_Menu_ID 
		Tree of the menu
	  */
	public void setAD_Tree_Menu_ID (int AD_Tree_Menu_ID)
	{
		if (AD_Tree_Menu_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Menu_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Menu_ID, Integer.valueOf(AD_Tree_Menu_ID));
	}

	/** Get Menu Tree.
		@return Tree of the menu
	  */
	public int getAD_Tree_Menu_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Menu_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Org() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Org_ID(), get_TrxName());	}

	/** Set Organization Tree.
		@param AD_Tree_Org_ID 
		Trees are used for (financial) reporting and security access (via role)
	  */
	public void setAD_Tree_Org_ID (int AD_Tree_Org_ID)
	{
		if (AD_Tree_Org_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Org_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Org_ID, Integer.valueOf(AD_Tree_Org_ID));
	}

	/** Get Organization Tree.
		@return Trees are used for (financial) reporting and security access (via role)
	  */
	public int getAD_Tree_Org_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Org_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Product() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Product_ID(), get_TrxName());	}

	/** Set Product Tree.
		@param AD_Tree_Product_ID 
		Trees are used for (financial) reporting
	  */
	public void setAD_Tree_Product_ID (int AD_Tree_Product_ID)
	{
		if (AD_Tree_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Product_ID, Integer.valueOf(AD_Tree_Product_ID));
	}

	/** Get Product Tree.
		@return Trees are used for (financial) reporting
	  */
	public int getAD_Tree_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_Project() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_Project_ID(), get_TrxName());	}

	/** Set Project Tree.
		@param AD_Tree_Project_ID 
		Trees are used for (financial) reporting
	  */
	public void setAD_Tree_Project_ID (int AD_Tree_Project_ID)
	{
		if (AD_Tree_Project_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Project_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_Project_ID, Integer.valueOf(AD_Tree_Project_ID));
	}

	/** Get Project Tree.
		@return Trees are used for (financial) reporting
	  */
	public int getAD_Tree_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_AD_Tree getAD_Tree_SalesRegion() throws RuntimeException
    {
		return (I_AD_Tree)MTable.get(getCtx(), I_AD_Tree.Table_Name)
			.getPO(getAD_Tree_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region Tree.
		@param AD_Tree_SalesRegion_ID 
		Trees are used for (financial) reporting
	  */
	public void setAD_Tree_SalesRegion_ID (int AD_Tree_SalesRegion_ID)
	{
		if (AD_Tree_SalesRegion_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_SalesRegion_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_Tree_SalesRegion_ID, Integer.valueOf(AD_Tree_SalesRegion_ID));
	}

	/** Get Sales Region Tree.
		@return Trees are used for (financial) reporting
	  */
	public int getAD_Tree_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Tree_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_AcctSchema getC_AcctSchema1() throws RuntimeException
    {
		return (I_C_AcctSchema)MTable.get(getCtx(), I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema1_ID(), get_TrxName());	}

	/** Set Primary Accounting Schema.
		@param C_AcctSchema1_ID 
		Primary rules for accounting
	  */
	public void setC_AcctSchema1_ID (int C_AcctSchema1_ID)
	{
		if (C_AcctSchema1_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema1_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema1_ID, Integer.valueOf(C_AcctSchema1_ID));
	}

	/** Get Primary Accounting Schema.
		@return Primary rules for accounting
	  */
	public int getC_AcctSchema1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_BPartner getC_BPartnerCashTrx() throws RuntimeException
    {
		return (I_C_BPartner)MTable.get(getCtx(), I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerCashTrx_ID(), get_TrxName());	}

	/** Set Template B.Partner.
		@param C_BPartnerCashTrx_ID 
		Business Partner used for creating new Business Partners on the fly
	  */
	public void setC_BPartnerCashTrx_ID (int C_BPartnerCashTrx_ID)
	{
		if (C_BPartnerCashTrx_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerCashTrx_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerCashTrx_ID, Integer.valueOf(C_BPartnerCashTrx_ID));
	}

	/** Get Template B.Partner.
		@return Business Partner used for creating new Business Partners on the fly
	  */
	public int getC_BPartnerCashTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerCashTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Calendar getC_Calendar() throws RuntimeException
    {
		return (I_C_Calendar)MTable.get(getCtx(), I_C_Calendar.Table_Name)
			.getPO(getC_Calendar_ID(), get_TrxName());	}

	/** Set Calendar.
		@param C_Calendar_ID 
		Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID)
	{
		if (C_Calendar_ID < 1) 
			set_Value (COLUMNNAME_C_Calendar_ID, null);
		else 
			set_Value (COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
	}

	/** Get Calendar.
		@return Accounting Calendar Name
	  */
	public int getC_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_UOM getC_UOM_Length() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_Length_ID(), get_TrxName());	}

	/** Set UOM for Length.
		@param C_UOM_Length_ID 
		Standard Unit of Measure for Length
	  */
	public void setC_UOM_Length_ID (int C_UOM_Length_ID)
	{
		if (C_UOM_Length_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Length_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Length_ID, Integer.valueOf(C_UOM_Length_ID));
	}

	/** Get UOM for Length.
		@return Standard Unit of Measure for Length
	  */
	public int getC_UOM_Length_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Length_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_UOM getC_UOM_Time() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_Time_ID(), get_TrxName());	}

	/** Set UOM for Time.
		@param C_UOM_Time_ID 
		Standard Unit of Measure for Time
	  */
	public void setC_UOM_Time_ID (int C_UOM_Time_ID)
	{
		if (C_UOM_Time_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Time_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Time_ID, Integer.valueOf(C_UOM_Time_ID));
	}

	/** Get UOM for Time.
		@return Standard Unit of Measure for Time
	  */
	public int getC_UOM_Time_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Time_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_UOM getC_UOM_Weight() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_Weight_ID(), get_TrxName());	}

	/** Set UOM for Weight.
		@param C_UOM_Weight_ID 
		Standard Unit of Measure for Weight
	  */
	public void setC_UOM_Weight_ID (int C_UOM_Weight_ID)
	{
		if (C_UOM_Weight_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Weight_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Weight_ID, Integer.valueOf(C_UOM_Weight_ID));
	}

	/** Get UOM for Weight.
		@return Standard Unit of Measure for Weight
	  */
	public int getC_UOM_Weight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Weight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_UOM getC_UOM_Volume() throws RuntimeException
    {
		return (I_C_UOM)MTable.get(getCtx(), I_C_UOM.Table_Name)
			.getPO(getC_UOM_Volume_ID(), get_TrxName());	}

	/** Set UOM for Volume.
		@param C_UOM_Volume_ID 
		Standard Unit of Measure for Volume
	  */
	public void setC_UOM_Volume_ID (int C_UOM_Volume_ID)
	{
		if (C_UOM_Volume_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_Volume_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_Volume_ID, Integer.valueOf(C_UOM_Volume_ID));
	}

	/** Get UOM for Volume.
		@return Standard Unit of Measure for Volume
	  */
	public int getC_UOM_Volume_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_Volume_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** DeliveryPolicy AD_Reference_ID=53355 */
	public static final int DELIVERYPOLICY_AD_Reference_ID=53355;
	/** No Hold = N */
	public static final String DELIVERYPOLICY_NoHold = "N";
	/** Strict order = O */
	public static final String DELIVERYPOLICY_StrictOrder = "O";
	/** Set Delivery Policy.
		@param DeliveryPolicy 
		Delivery Policy
	  */
	public void setDeliveryPolicy (String DeliveryPolicy)
	{

		set_Value (COLUMNNAME_DeliveryPolicy, DeliveryPolicy);
	}

	/** Get Delivery Policy.
		@return Delivery Policy
	  */
	public String getDeliveryPolicy () 
	{
		return (String)get_Value(COLUMNNAME_DeliveryPolicy);
	}

	/** Set Discount calculated from Line Amounts.
		@param IsDiscountLineAmt 
		Payment Discount calculation does not include Taxes and Charges
	  */
	public void setIsDiscountLineAmt (boolean IsDiscountLineAmt)
	{
		set_Value (COLUMNNAME_IsDiscountLineAmt, Boolean.valueOf(IsDiscountLineAmt));
	}

	/** Get Discount calculated from Line Amounts.
		@return Payment Discount calculation does not include Taxes and Charges
	  */
	public boolean isDiscountLineAmt () 
	{
		Object oo = get_Value(COLUMNNAME_IsDiscountLineAmt);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Days to keep Log.
		@param KeepLogDays 
		Number of days to keep the log entries
	  */
	public void setKeepLogDays (int KeepLogDays)
	{
		set_Value (COLUMNNAME_KeepLogDays, Integer.valueOf(KeepLogDays));
	}

	/** Get Days to keep Log.
		@return Number of days to keep the log entries
	  */
	public int getKeepLogDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_KeepLogDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Logo.
		@param Logo_ID Logo	  */
	public void setLogo_ID (int Logo_ID)
	{
		if (Logo_ID < 1) 
			set_Value (COLUMNNAME_Logo_ID, null);
		else 
			set_Value (COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
	}

	/** Get Logo.
		@return Logo	  */
	public int getLogo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Logo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Logo Report.
		@param LogoReport_ID Logo Report	  */
	public void setLogoReport_ID (int LogoReport_ID)
	{
		if (LogoReport_ID < 1) 
			set_Value (COLUMNNAME_LogoReport_ID, null);
		else 
			set_Value (COLUMNNAME_LogoReport_ID, Integer.valueOf(LogoReport_ID));
	}

	/** Get Logo Report.
		@return Logo Report	  */
	public int getLogoReport_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LogoReport_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Logo Web.
		@param LogoWeb_ID Logo Web	  */
	public void setLogoWeb_ID (int LogoWeb_ID)
	{
		if (LogoWeb_ID < 1) 
			set_Value (COLUMNNAME_LogoWeb_ID, null);
		else 
			set_Value (COLUMNNAME_LogoWeb_ID, Integer.valueOf(LogoWeb_ID));
	}

	/** Get Logo Web.
		@return Logo Web	  */
	public int getLogoWeb_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LogoWeb_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Product getM_ProductFreight() throws RuntimeException
    {
		return (I_M_Product)MTable.get(getCtx(), I_M_Product.Table_Name)
			.getPO(getM_ProductFreight_ID(), get_TrxName());	}

	/** Set Product for Freight.
		@param M_ProductFreight_ID Product for Freight	  */
	public void setM_ProductFreight_ID (int M_ProductFreight_ID)
	{
		if (M_ProductFreight_ID < 1) 
			set_Value (COLUMNNAME_M_ProductFreight_ID, null);
		else 
			set_Value (COLUMNNAME_M_ProductFreight_ID, Integer.valueOf(M_ProductFreight_ID));
	}

	/** Get Product for Freight.
		@return Product for Freight	  */
	public int getM_ProductFreight_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductFreight_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}