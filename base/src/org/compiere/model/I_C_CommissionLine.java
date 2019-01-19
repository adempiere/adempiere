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

/** Generated Interface for C_CommissionLine
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_C_CommissionLine 
{

    /** TableName=C_CommissionLine */
    public static final String Table_Name = "C_CommissionLine";

    /** AD_Table_ID=431 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

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

    /** Column name AmtMultiplier */
    public static final String COLUMNNAME_AmtMultiplier = "AmtMultiplier";

	/** Set Multiplier Amount.
	  * Multiplier Amount for generating commissions
	  */
	public void setAmtMultiplier (BigDecimal AmtMultiplier);

	/** Get Multiplier Amount.
	  * Multiplier Amount for generating commissions
	  */
	public BigDecimal getAmtMultiplier();

    /** Column name AmtSubtract */
    public static final String COLUMNNAME_AmtSubtract = "AmtSubtract";

	/** Set Subtract Amount.
	  * Subtract Amount for generating commissions
	  */
	public void setAmtSubtract (BigDecimal AmtSubtract);

	/** Get Subtract Amount.
	  * Subtract Amount for generating commissions
	  */
	public BigDecimal getAmtSubtract();

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException;

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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

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

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Channel_ID */
    public static final String COLUMNNAME_C_Channel_ID = "C_Channel_ID";

	/** Set Channel.
	  * Sales Channel
	  */
	public void setC_Channel_ID (int C_Channel_ID);

	/** Get Channel.
	  * Sales Channel
	  */
	public int getC_Channel_ID();

	public org.compiere.model.I_C_Channel getC_Channel() throws RuntimeException;

    /** Column name C_CommissionLine_ID */
    public static final String COLUMNNAME_C_CommissionLine_ID = "C_CommissionLine_ID";

	/** Set Commission Line.
	  * Commission Line
	  */
	public void setC_CommissionLine_ID (int C_CommissionLine_ID);

	/** Get Commission Line.
	  * Commission Line
	  */
	public int getC_CommissionLine_ID();

    /** Column name C_Commission_ID */
    public static final String COLUMNNAME_C_Commission_ID = "C_Commission_ID";

	/** Set Commission.
	  * Commission
	  */
	public void setC_Commission_ID (int C_Commission_ID);

	/** Get Commission.
	  * Commission
	  */
	public int getC_Commission_ID();

	public org.compiere.model.I_C_Commission getC_Commission() throws RuntimeException;

    /** Column name C_DunningLevel_ID */
    public static final String COLUMNNAME_C_DunningLevel_ID = "C_DunningLevel_ID";

	/** Set Dunning Level	  */
	public void setC_DunningLevel_ID (int C_DunningLevel_ID);

	/** Get Dunning Level	  */
	public int getC_DunningLevel_ID();

	public org.compiere.model.I_C_DunningLevel getC_DunningLevel() throws RuntimeException;

    /** Column name C_PaymentTerm_ID */
    public static final String COLUMNNAME_C_PaymentTerm_ID = "C_PaymentTerm_ID";

	/** Set Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public void setC_PaymentTerm_ID (int C_PaymentTerm_ID);

	/** Get Payment Term.
	  * The terms of Payment (timing, discount)
	  */
	public int getC_PaymentTerm_ID();

	public org.compiere.model.I_C_PaymentTerm getC_PaymentTerm() throws RuntimeException;

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

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

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

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException;

    /** Column name CommissionOrders */
    public static final String COLUMNNAME_CommissionOrders = "CommissionOrders";

	/** Set Commission only specified Orders.
	  * Commission only Orders or Invoices, where this Sales Rep is entered
	  */
	public void setCommissionOrders (boolean CommissionOrders);

	/** Get Commission only specified Orders.
	  * Commission only Orders or Invoices, where this Sales Rep is entered
	  */
	public boolean isCommissionOrders();

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

    /** Column name DaysFrom */
    public static final String COLUMNNAME_DaysFrom = "DaysFrom";

	/** Set Days From	  */
	public void setDaysFrom (int DaysFrom);

	/** Get Days From	  */
	public int getDaysFrom();

    /** Column name DaysTo */
    public static final String COLUMNNAME_DaysTo = "DaysTo";

	/** Set Days To	  */
	public void setDaysTo (int DaysTo);

	/** Get Days To	  */
	public int getDaysTo();

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

    /** Column name InvoiceCollectionType */
    public static final String COLUMNNAME_InvoiceCollectionType = "InvoiceCollectionType";

	/** Set Collection Status.
	  * Invoice Collection Status
	  */
	public void setInvoiceCollectionType (String InvoiceCollectionType);

	/** Get Collection Status.
	  * Invoice Collection Status
	  */
	public String getInvoiceCollectionType();

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

    /** Column name IsPercentage */
    public static final String COLUMNNAME_IsPercentage = "IsPercentage";

	/** Set Is Percentage.
	  * Indicates that Quantity is expressed as Percentage (%)
	  */
	public void setIsPercentage (boolean IsPercentage);

	/** Get Is Percentage.
	  * Indicates that Quantity is expressed as Percentage (%)
	  */
	public boolean isPercentage();

    /** Column name IsPercentageFromPrice */
    public static final String COLUMNNAME_IsPercentageFromPrice = "IsPercentageFromPrice";

	/** Set Percentage From Price.
	  * Percentage From Price is for calculate % of compliance from price instead quantity
	  */
	public void setIsPercentageFromPrice (boolean IsPercentageFromPrice);

	/** Get Percentage From Price.
	  * Percentage From Price is for calculate % of compliance from price instead quantity
	  */
	public boolean isPercentageFromPrice();

    /** Column name IsPositiveOnly */
    public static final String COLUMNNAME_IsPositiveOnly = "IsPositiveOnly";

	/** Set Positive only.
	  * Do not generate negative commissions
	  */
	public void setIsPositiveOnly (boolean IsPositiveOnly);

	/** Get Positive only.
	  * Do not generate negative commissions
	  */
	public boolean isPositiveOnly();

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

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_Class_ID */
    public static final String COLUMNNAME_M_Product_Class_ID = "M_Product_Class_ID";

	/** Set Product Class.
	  * Class of a Product
	  */
	public void setM_Product_Class_ID (int M_Product_Class_ID);

	/** Get Product Class.
	  * Class of a Product
	  */
	public int getM_Product_Class_ID();

	public org.compiere.model.I_M_Product_Class getM_Product_Class() throws RuntimeException;

    /** Column name M_Product_Classification_ID */
    public static final String COLUMNNAME_M_Product_Classification_ID = "M_Product_Classification_ID";

	/** Set Product Classification.
	  * Classification of a Product
	  */
	public void setM_Product_Classification_ID (int M_Product_Classification_ID);

	/** Get Product Classification.
	  * Classification of a Product
	  */
	public int getM_Product_Classification_ID();

	public org.compiere.model.I_M_Product_Classification getM_Product_Classification() throws RuntimeException;

    /** Column name M_Product_Group_ID */
    public static final String COLUMNNAME_M_Product_Group_ID = "M_Product_Group_ID";

	/** Set Product Group.
	  * Group of a Product
	  */
	public void setM_Product_Group_ID (int M_Product_Group_ID);

	/** Get Product Group.
	  * Group of a Product
	  */
	public int getM_Product_Group_ID();

	public org.compiere.model.I_M_Product_Group getM_Product_Group() throws RuntimeException;

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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name MaxCompliance */
    public static final String COLUMNNAME_MaxCompliance = "MaxCompliance";

	/** Set Maximum Compliance (%).
	  * Maximum Compliance of Forecast
	  */
	public void setMaxCompliance (BigDecimal MaxCompliance);

	/** Get Maximum Compliance (%).
	  * Maximum Compliance of Forecast
	  */
	public BigDecimal getMaxCompliance();

    /** Column name MaxPercentage */
    public static final String COLUMNNAME_MaxPercentage = "MaxPercentage";

	/** Set Maximum Percentage.
	  * Maximum Percentage of the entire amount
	  */
	public void setMaxPercentage (BigDecimal MaxPercentage);

	/** Get Maximum Percentage.
	  * Maximum Percentage of the entire amount
	  */
	public BigDecimal getMaxPercentage();

    /** Column name MinCompliance */
    public static final String COLUMNNAME_MinCompliance = "MinCompliance";

	/** Set Minimum Compliance (%).
	  * Minimum Compliance of Forecast
	  */
	public void setMinCompliance (BigDecimal MinCompliance);

	/** Get Minimum Compliance (%).
	  * Minimum Compliance of Forecast
	  */
	public BigDecimal getMinCompliance();

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

	public org.compiere.model.I_AD_Org getOrg() throws RuntimeException;

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

    /** Column name QtyMultiplier */
    public static final String COLUMNNAME_QtyMultiplier = "QtyMultiplier";

	/** Set Multiplier Quantity.
	  * Value to multiply quantities by for generating commissions.
	  */
	public void setQtyMultiplier (BigDecimal QtyMultiplier);

	/** Get Multiplier Quantity.
	  * Value to multiply quantities by for generating commissions.
	  */
	public BigDecimal getQtyMultiplier();

    /** Column name QtySubtract */
    public static final String COLUMNNAME_QtySubtract = "QtySubtract";

	/** Set Subtract Quantity.
	  * Quantity to subtract when generating commissions
	  */
	public void setQtySubtract (BigDecimal QtySubtract);

	/** Get Subtract Quantity.
	  * Quantity to subtract when generating commissions
	  */
	public BigDecimal getQtySubtract();

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
}
