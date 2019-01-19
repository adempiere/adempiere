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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_CommissionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_CommissionLine extends PO implements I_C_CommissionLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_CommissionLine (Properties ctx, int C_CommissionLine_ID, String trxName)
    {
      super (ctx, C_CommissionLine_ID, trxName);
      /** if (C_CommissionLine_ID == 0)
        {
			setAmtMultiplier (Env.ZERO);
			setAmtSubtract (Env.ZERO);
			setC_CommissionLine_ID (0);
			setC_Commission_ID (0);
			setCommissionOrders (false);
			setIsPositiveOnly (false);
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM C_CommissionLine WHERE C_Commission_ID=@C_Commission_ID@
			setQtyMultiplier (Env.ZERO);
			setQtySubtract (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_C_CommissionLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_CommissionLine[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Multiplier Amount.
		@param AmtMultiplier 
		Multiplier Amount for generating commissions
	  */
	public void setAmtMultiplier (BigDecimal AmtMultiplier)
	{
		set_Value (COLUMNNAME_AmtMultiplier, AmtMultiplier);
	}

	/** Get Multiplier Amount.
		@return Multiplier Amount for generating commissions
	  */
	public BigDecimal getAmtMultiplier () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtMultiplier);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Subtract Amount.
		@param AmtSubtract 
		Subtract Amount for generating commissions
	  */
	public void setAmtSubtract (BigDecimal AmtSubtract)
	{
		set_Value (COLUMNNAME_AmtSubtract, AmtSubtract);
	}

	/** Get Subtract Amount.
		@return Subtract Amount for generating commissions
	  */
	public BigDecimal getAmtSubtract () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtSubtract);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
			.getPO(getC_BP_Group_ID(), get_TrxName());	}

	/** Set Business Partner Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
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

	public org.compiere.model.I_C_Channel getC_Channel() throws RuntimeException
    {
		return (org.compiere.model.I_C_Channel)MTable.get(getCtx(), org.compiere.model.I_C_Channel.Table_Name)
			.getPO(getC_Channel_ID(), get_TrxName());	}

	/** Set Channel.
		@param C_Channel_ID 
		Sales Channel
	  */
	public void setC_Channel_ID (int C_Channel_ID)
	{
		if (C_Channel_ID < 1) 
			set_Value (COLUMNNAME_C_Channel_ID, null);
		else 
			set_Value (COLUMNNAME_C_Channel_ID, Integer.valueOf(C_Channel_ID));
	}

	/** Get Channel.
		@return Sales Channel
	  */
	public int getC_Channel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Channel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Commission Line.
		@param C_CommissionLine_ID 
		Commission Line
	  */
	public void setC_CommissionLine_ID (int C_CommissionLine_ID)
	{
		if (C_CommissionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_CommissionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_CommissionLine_ID, Integer.valueOf(C_CommissionLine_ID));
	}

	/** Get Commission Line.
		@return Commission Line
	  */
	public int getC_CommissionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_CommissionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Commission getC_Commission() throws RuntimeException
    {
		return (org.compiere.model.I_C_Commission)MTable.get(getCtx(), org.compiere.model.I_C_Commission.Table_Name)
			.getPO(getC_Commission_ID(), get_TrxName());	}

	/** Set Commission.
		@param C_Commission_ID 
		Commission
	  */
	public void setC_Commission_ID (int C_Commission_ID)
	{
		if (C_Commission_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Commission_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Commission_ID, Integer.valueOf(C_Commission_ID));
	}

	/** Get Commission.
		@return Commission
	  */
	public int getC_Commission_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Commission_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_Commission_ID()));
    }

	public org.compiere.model.I_C_DunningLevel getC_DunningLevel() throws RuntimeException
    {
		return (org.compiere.model.I_C_DunningLevel)MTable.get(getCtx(), org.compiere.model.I_C_DunningLevel.Table_Name)
			.getPO(getC_DunningLevel_ID(), get_TrxName());	}

	/** Set Dunning Level.
		@param C_DunningLevel_ID Dunning Level	  */
	public void setC_DunningLevel_ID (int C_DunningLevel_ID)
	{
		if (C_DunningLevel_ID < 1) 
			set_Value (COLUMNNAME_C_DunningLevel_ID, null);
		else 
			set_Value (COLUMNNAME_C_DunningLevel_ID, Integer.valueOf(C_DunningLevel_ID));
	}

	/** Get Dunning Level.
		@return Dunning Level	  */
	public int getC_DunningLevel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DunningLevel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException
    {
		return (org.compiere.model.I_C_PaymentTerm)MTable.get(getCtx(), org.compiere.model.I_C_PaymentTerm.Table_Name)
			.getPO(getC_PaymentTerm_ID(), get_TrxName());	}

	/** Set Payment Term.
		@param C_PaymentTerm_ID 
		The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID)
	{
		if (C_PaymentTerm_ID < 1) 
			set_Value (COLUMNNAME_C_PaymentTerm_ID, null);
		else 
			set_Value (COLUMNNAME_C_PaymentTerm_ID, Integer.valueOf(C_PaymentTerm_ID));
	}

	/** Get Payment Term.
		@return The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_PaymentTerm_ID);
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

	/** Set Commission only specified Orders.
		@param CommissionOrders 
		Commission only Orders or Invoices, where this Sales Rep is entered
	  */
	public void setCommissionOrders (boolean CommissionOrders)
	{
		set_Value (COLUMNNAME_CommissionOrders, Boolean.valueOf(CommissionOrders));
	}

	/** Get Commission only specified Orders.
		@return Commission only Orders or Invoices, where this Sales Rep is entered
	  */
	public boolean isCommissionOrders () 
	{
		Object oo = get_Value(COLUMNNAME_CommissionOrders);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Days From.
		@param DaysFrom Days From	  */
	public void setDaysFrom (int DaysFrom)
	{
		set_Value (COLUMNNAME_DaysFrom, Integer.valueOf(DaysFrom));
	}

	/** Get Days From.
		@return Days From	  */
	public int getDaysFrom () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DaysFrom);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Days To.
		@param DaysTo Days To	  */
	public void setDaysTo (int DaysTo)
	{
		set_Value (COLUMNNAME_DaysTo, Integer.valueOf(DaysTo));
	}

	/** Get Days To.
		@return Days To	  */
	public int getDaysTo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DaysTo);
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

	/** InvoiceCollectionType AD_Reference_ID=394 */
	public static final int INVOICECOLLECTIONTYPE_AD_Reference_ID=394;
	/** Dunning = D */
	public static final String INVOICECOLLECTIONTYPE_Dunning = "D";
	/** Collection Agency = C */
	public static final String INVOICECOLLECTIONTYPE_CollectionAgency = "C";
	/** Legal Procedure = L */
	public static final String INVOICECOLLECTIONTYPE_LegalProcedure = "L";
	/** Uncollectable = U */
	public static final String INVOICECOLLECTIONTYPE_Uncollectable = "U";
	/** Set Collection Status.
		@param InvoiceCollectionType 
		Invoice Collection Status
	  */
	public void setInvoiceCollectionType (String InvoiceCollectionType)
	{

		set_Value (COLUMNNAME_InvoiceCollectionType, InvoiceCollectionType);
	}

	/** Get Collection Status.
		@return Invoice Collection Status
	  */
	public String getInvoiceCollectionType () 
	{
		return (String)get_Value(COLUMNNAME_InvoiceCollectionType);
	}

	/** Set Is Percentage.
		@param IsPercentage 
		Indicates that Quantity is expressed as Percentage (%)
	  */
	public void setIsPercentage (boolean IsPercentage)
	{
		set_Value (COLUMNNAME_IsPercentage, Boolean.valueOf(IsPercentage));
	}

	/** Get Is Percentage.
		@return Indicates that Quantity is expressed as Percentage (%)
	  */
	public boolean isPercentage () 
	{
		Object oo = get_Value(COLUMNNAME_IsPercentage);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percentage From Price.
		@param IsPercentageFromPrice 
		Percentage From Price is for calculate % of compliance from price instead quantity
	  */
	public void setIsPercentageFromPrice (boolean IsPercentageFromPrice)
	{
		set_Value (COLUMNNAME_IsPercentageFromPrice, Boolean.valueOf(IsPercentageFromPrice));
	}

	/** Get Percentage From Price.
		@return Percentage From Price is for calculate % of compliance from price instead quantity
	  */
	public boolean isPercentageFromPrice () 
	{
		Object oo = get_Value(COLUMNNAME_IsPercentageFromPrice);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Positive only.
		@param IsPositiveOnly 
		Do not generate negative commissions
	  */
	public void setIsPositiveOnly (boolean IsPositiveOnly)
	{
		set_Value (COLUMNNAME_IsPositiveOnly, Boolean.valueOf(IsPositiveOnly));
	}

	/** Get Positive only.
		@return Do not generate negative commissions
	  */
	public boolean isPositiveOnly () 
	{
		Object oo = get_Value(COLUMNNAME_IsPositiveOnly);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Class getM_Product_Class() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Class)MTable.get(getCtx(), org.compiere.model.I_M_Product_Class.Table_Name)
			.getPO(getM_Product_Class_ID(), get_TrxName());	}

	/** Set Product Class.
		@param M_Product_Class_ID 
		Class of a Product
	  */
	public void setM_Product_Class_ID (int M_Product_Class_ID)
	{
		if (M_Product_Class_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Class_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Class_ID, Integer.valueOf(M_Product_Class_ID));
	}

	/** Get Product Class.
		@return Class of a Product
	  */
	public int getM_Product_Class_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Class_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Classification getM_Product_Classification() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Classification)MTable.get(getCtx(), org.compiere.model.I_M_Product_Classification.Table_Name)
			.getPO(getM_Product_Classification_ID(), get_TrxName());	}

	/** Set Product Classification.
		@param M_Product_Classification_ID 
		Classification of a Product
	  */
	public void setM_Product_Classification_ID (int M_Product_Classification_ID)
	{
		if (M_Product_Classification_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Classification_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Classification_ID, Integer.valueOf(M_Product_Classification_ID));
	}

	/** Get Product Classification.
		@return Classification of a Product
	  */
	public int getM_Product_Classification_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Classification_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Group getM_Product_Group() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Group)MTable.get(getCtx(), org.compiere.model.I_M_Product_Group.Table_Name)
			.getPO(getM_Product_Group_ID(), get_TrxName());	}

	/** Set Product Group.
		@param M_Product_Group_ID 
		Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID)
	{
		if (M_Product_Group_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Group_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Group_ID, Integer.valueOf(M_Product_Group_ID));
	}

	/** Get Product Group.
		@return Group of a Product
	  */
	public int getM_Product_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Maximum Compliance (%).
		@param MaxCompliance 
		Maximum Compliance of Forecast
	  */
	public void setMaxCompliance (BigDecimal MaxCompliance)
	{
		set_Value (COLUMNNAME_MaxCompliance, MaxCompliance);
	}

	/** Get Maximum Compliance (%).
		@return Maximum Compliance of Forecast
	  */
	public BigDecimal getMaxCompliance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxCompliance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Maximum Percentage.
		@param MaxPercentage 
		Maximum Percentage of the entire amount
	  */
	public void setMaxPercentage (BigDecimal MaxPercentage)
	{
		set_Value (COLUMNNAME_MaxPercentage, MaxPercentage);
	}

	/** Get Maximum Percentage.
		@return Maximum Percentage of the entire amount
	  */
	public BigDecimal getMaxPercentage () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MaxPercentage);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Minimum Compliance (%).
		@param MinCompliance 
		Minimum Compliance of Forecast
	  */
	public void setMinCompliance (BigDecimal MinCompliance)
	{
		set_Value (COLUMNNAME_MinCompliance, MinCompliance);
	}

	/** Get Minimum Compliance (%).
		@return Minimum Compliance of Forecast
	  */
	public BigDecimal getMinCompliance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MinCompliance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed = M */
	public static final String PAYMENTRULE_Mixed = "M";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}

	/** Set Multiplier Quantity.
		@param QtyMultiplier 
		Value to multiply quantities by for generating commissions.
	  */
	public void setQtyMultiplier (BigDecimal QtyMultiplier)
	{
		set_Value (COLUMNNAME_QtyMultiplier, QtyMultiplier);
	}

	/** Get Multiplier Quantity.
		@return Value to multiply quantities by for generating commissions.
	  */
	public BigDecimal getQtyMultiplier () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyMultiplier);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Subtract Quantity.
		@param QtySubtract 
		Quantity to subtract when generating commissions
	  */
	public void setQtySubtract (BigDecimal QtySubtract)
	{
		set_Value (COLUMNNAME_QtySubtract, QtySubtract);
	}

	/** Get Subtract Quantity.
		@return Quantity to subtract when generating commissions
	  */
	public BigDecimal getQtySubtract () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtySubtract);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
}