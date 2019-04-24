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

/** Generated Interface for A_Depreciation_Workfile
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_A_Depreciation_Workfile 
{

    /** TableName=A_Depreciation_Workfile */
    public static final String Table_Name = "A_Depreciation_Workfile";

    /** AD_Table_ID=53116 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

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

    /** Column name A_Accumulated_Depr */
    public static final String COLUMNNAME_A_Accumulated_Depr = "A_Accumulated_Depr";

	/** Set Accumulated Depreciation	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr);

	/** Get Accumulated Depreciation	  */
	public BigDecimal getA_Accumulated_Depr();

    /** Column name A_Accumulated_Depr_F */
    public static final String COLUMNNAME_A_Accumulated_Depr_F = "A_Accumulated_Depr_F";

	/** Set Accumulated Depreciation (fiscal)	  */
	public void setA_Accumulated_Depr_F (BigDecimal A_Accumulated_Depr_F);

	/** Get Accumulated Depreciation (fiscal)	  */
	public BigDecimal getA_Accumulated_Depr_F();

    /** Column name A_Asset_Cost */
    public static final String COLUMNNAME_A_Asset_Cost = "A_Asset_Cost";

	/** Set Fixed Asset Cost.
	  * Cost of acquisition of the Fixed Asset
	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost);

	/** Get Fixed Asset Cost.
	  * Cost of acquisition of the Fixed Asset
	  */
	public BigDecimal getA_Asset_Cost();

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Fixed Asset.
	  * Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID();

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException;

    /** Column name A_Asset_Life_Current_Year */
    public static final String COLUMNNAME_A_Asset_Life_Current_Year = "A_Asset_Life_Current_Year";

	/** Set A_Asset_Life_Current_Year	  */
	public void setA_Asset_Life_Current_Year (BigDecimal A_Asset_Life_Current_Year);

	/** Get A_Asset_Life_Current_Year	  */
	public BigDecimal getA_Asset_Life_Current_Year();

    /** Column name A_Asset_Life_Years */
    public static final String COLUMNNAME_A_Asset_Life_Years = "A_Asset_Life_Years";

	/** Set Life Years	  */
	public void setA_Asset_Life_Years (int A_Asset_Life_Years);

	/** Get Life Years	  */
	public int getA_Asset_Life_Years();

    /** Column name A_Asset_Life_Years_F */
    public static final String COLUMNNAME_A_Asset_Life_Years_F = "A_Asset_Life_Years_F";

	/** Set Life Years (fiscal)	  */
	public void setA_Asset_Life_Years_F (int A_Asset_Life_Years_F);

	/** Get Life Years (fiscal)	  */
	public int getA_Asset_Life_Years_F();

    /** Column name A_Asset_Remaining */
    public static final String COLUMNNAME_A_Asset_Remaining = "A_Asset_Remaining";

	/** Set Remaining Amt	  */
	public void setA_Asset_Remaining (BigDecimal A_Asset_Remaining);

	/** Get Remaining Amt	  */
	public BigDecimal getA_Asset_Remaining();

    /** Column name A_Asset_Remaining_F */
    public static final String COLUMNNAME_A_Asset_Remaining_F = "A_Asset_Remaining_F";

	/** Set Remaining Amt (fiscal)	  */
	public void setA_Asset_Remaining_F (BigDecimal A_Asset_Remaining_F);

	/** Get Remaining Amt (fiscal)	  */
	public BigDecimal getA_Asset_Remaining_F();

    /** Column name A_Base_Amount */
    public static final String COLUMNNAME_A_Base_Amount = "A_Base_Amount";

	/** Set A_Base_Amount	  */
	public void setA_Base_Amount (BigDecimal A_Base_Amount);

	/** Get A_Base_Amount	  */
	public BigDecimal getA_Base_Amount();

    /** Column name A_Calc_Accumulated_Depr */
    public static final String COLUMNNAME_A_Calc_Accumulated_Depr = "A_Calc_Accumulated_Depr";

	/** Set A_Calc_Accumulated_Depr	  */
	public void setA_Calc_Accumulated_Depr (BigDecimal A_Calc_Accumulated_Depr);

	/** Get A_Calc_Accumulated_Depr	  */
	public BigDecimal getA_Calc_Accumulated_Depr();

    /** Column name A_Curr_Dep_Exp */
    public static final String COLUMNNAME_A_Curr_Dep_Exp = "A_Curr_Dep_Exp";

	/** Set A_Curr_Dep_Exp	  */
	public void setA_Curr_Dep_Exp (BigDecimal A_Curr_Dep_Exp);

	/** Get A_Curr_Dep_Exp	  */
	public BigDecimal getA_Curr_Dep_Exp();

    /** Column name A_Current_Period */
    public static final String COLUMNNAME_A_Current_Period = "A_Current_Period";

	/** Set Current Period	  */
	public void setA_Current_Period (int A_Current_Period);

	/** Get Current Period	  */
	public int getA_Current_Period();

    /** Column name A_Depreciation_Workfile_ID */
    public static final String COLUMNNAME_A_Depreciation_Workfile_ID = "A_Depreciation_Workfile_ID";

	/** Set Fixed Asset Balances.
	  * Fixed Asset Balances
	  */
	public void setA_Depreciation_Workfile_ID (int A_Depreciation_Workfile_ID);

	/** Get Fixed Asset Balances.
	  * Fixed Asset Balances
	  */
	public int getA_Depreciation_Workfile_ID();

    /** Column name A_Expense_SL */
    public static final String COLUMNNAME_A_Expense_SL = "A_Expense_SL";

	/** Set SL Expense/Period	  */
	public void setA_Expense_SL (BigDecimal A_Expense_SL);

	/** Get SL Expense/Period	  */
	public BigDecimal getA_Expense_SL();

    /** Column name A_Expense_SL_F */
    public static final String COLUMNNAME_A_Expense_SL_F = "A_Expense_SL_F";

	/** Set SL Expense/Period (fiscal)	  */
	public void setA_Expense_SL_F (BigDecimal A_Expense_SL_F);

	/** Get SL Expense/Period (fiscal)	  */
	public BigDecimal getA_Expense_SL_F();

    /** Column name A_FundingMode_ID */
    public static final String COLUMNNAME_A_FundingMode_ID = "A_FundingMode_ID";

	/** Set Asset Funding Mode	  */
	public void setA_FundingMode_ID (int A_FundingMode_ID);

	/** Get Asset Funding Mode	  */
	public int getA_FundingMode_ID();

	public org.compiere.model.I_A_FundingMode getA_FundingMode() throws RuntimeException;

    /** Column name A_Life_Period */
    public static final String COLUMNNAME_A_Life_Period = "A_Life_Period";

	/** Set Life Periods	  */
	public void setA_Life_Period (int A_Life_Period);

	/** Get Life Periods	  */
	public int getA_Life_Period();

    /** Column name A_Life_Period_F */
    public static final String COLUMNNAME_A_Life_Period_F = "A_Life_Period_F";

	/** Set Life Period (fiscal)	  */
	public void setA_Life_Period_F (int A_Life_Period_F);

	/** Get Life Period (fiscal)	  */
	public int getA_Life_Period_F();

    /** Column name A_Life_Period_Max */
    public static final String COLUMNNAME_A_Life_Period_Max = "A_Life_Period_Max";

	/** Set Life periods (max)	  */
	public void setA_Life_Period_Max (int A_Life_Period_Max);

	/** Get Life periods (max)	  */
	public int getA_Life_Period_Max();

    /** Column name A_Life_Period_Min */
    public static final String COLUMNNAME_A_Life_Period_Min = "A_Life_Period_Min";

	/** Set Life periods (min)	  */
	public void setA_Life_Period_Min (int A_Life_Period_Min);

	/** Get Life periods (min)	  */
	public int getA_Life_Period_Min();

    /** Column name A_Period_Forecast */
    public static final String COLUMNNAME_A_Period_Forecast = "A_Period_Forecast";

	/** Set A_Period_Forecast	  */
	public void setA_Period_Forecast (BigDecimal A_Period_Forecast);

	/** Get A_Period_Forecast	  */
	public BigDecimal getA_Period_Forecast();

    /** Column name A_Period_Posted */
    public static final String COLUMNNAME_A_Period_Posted = "A_Period_Posted";

	/** Set A_Period_Posted	  */
	public void setA_Period_Posted (int A_Period_Posted);

	/** Get A_Period_Posted	  */
	public int getA_Period_Posted();

    /** Column name A_Prior_Year_Accumulated_Depr */
    public static final String COLUMNNAME_A_Prior_Year_Accumulated_Depr = "A_Prior_Year_Accumulated_Depr";

	/** Set A_Prior_Year_Accumulated_Depr	  */
	public void setA_Prior_Year_Accumulated_Depr (BigDecimal A_Prior_Year_Accumulated_Depr);

	/** Get A_Prior_Year_Accumulated_Depr	  */
	public BigDecimal getA_Prior_Year_Accumulated_Depr();

    /** Column name A_QTY_Current */
    public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

	/** Set Fixed Asset Current Qty.
	  * Fixed Asset Current Quantity
	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get Fixed Asset Current Qty.
	  * Fixed Asset Current Quantity
	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_Salvage_Value */
    public static final String COLUMNNAME_A_Salvage_Value = "A_Salvage_Value";

	/** Set Asset Salvage Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value);

	/** Get Asset Salvage Value	  */
	public BigDecimal getA_Salvage_Value();

    /** Column name A_Tip_Finantare */
    public static final String COLUMNNAME_A_Tip_Finantare = "A_Tip_Finantare";

	/** Set Financing Type.
	  * Financing Type
	  */
	public void setA_Tip_Finantare (String A_Tip_Finantare);

	/** Get Financing Type.
	  * Financing Type
	  */
	public String getA_Tip_Finantare();

    /** Column name A_Valoare_Cofinantare */
    public static final String COLUMNNAME_A_Valoare_Cofinantare = "A_Valoare_Cofinantare";

	/** Set Own contribution	  */
	public void setA_Valoare_Cofinantare (BigDecimal A_Valoare_Cofinantare);

	/** Get Own contribution	  */
	public BigDecimal getA_Valoare_Cofinantare();

    /** Column name A_Valoare_Tert */
    public static final String COLUMNNAME_A_Valoare_Tert = "A_Valoare_Tert";

	/** Set Third contribution	  */
	public void setA_Valoare_Tert (BigDecimal A_Valoare_Tert);

	/** Get Third contribution	  */
	public BigDecimal getA_Valoare_Tert();

    /** Column name AssetDepreciationDate */
    public static final String COLUMNNAME_AssetDepreciationDate = "AssetDepreciationDate";

	/** Set Asset Depreciation Date.
	  * Date of last depreciation
	  */
	public void setAssetDepreciationDate (Timestamp AssetDepreciationDate);

	/** Get Asset Depreciation Date.
	  * Date of last depreciation
	  */
	public Timestamp getAssetDepreciationDate();

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

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

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set Posting Type.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get Posting Type.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

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

    /** Column name UseLifeMonths */
    public static final String COLUMNNAME_UseLifeMonths = "UseLifeMonths";

	/** Set Usable Life - Months.
	  * Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths);

	/** Get Usable Life - Months.
	  * Months of the usable life of the asset
	  */
	public int getUseLifeMonths();

    /** Column name UseLifeMonths_F */
    public static final String COLUMNNAME_UseLifeMonths_F = "UseLifeMonths_F";

	/** Set Use Life - Months (fiscal)	  */
	public void setUseLifeMonths_F (int UseLifeMonths_F);

	/** Get Use Life - Months (fiscal)	  */
	public int getUseLifeMonths_F();

    /** Column name UseLifeYears */
    public static final String COLUMNNAME_UseLifeYears = "UseLifeYears";

	/** Set Usable Life - Years.
	  * Years of the usable life of the asset
	  */
	public void setUseLifeYears (int UseLifeYears);

	/** Get Usable Life - Years.
	  * Years of the usable life of the asset
	  */
	public int getUseLifeYears();

    /** Column name UseLifeYears_F */
    public static final String COLUMNNAME_UseLifeYears_F = "UseLifeYears_F";

	/** Set Use Life - Years (fiscal)	  */
	public void setUseLifeYears_F (int UseLifeYears_F);

	/** Get Use Life - Years (fiscal)	  */
	public int getUseLifeYears_F();
}
