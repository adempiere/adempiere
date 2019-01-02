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

/** Generated Interface for A_Asset_Group_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_A_Asset_Group_Acct 
{

    /** TableName=A_Asset_Group_Acct */
    public static final String Table_Name = "A_Asset_Group_Acct";

    /** AD_Table_ID=53130 */
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

    /** Column name A_Accumdepreciation_Acct */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct = "A_Accumdepreciation_Acct";

	/** Set Accumulated Depreciation Account	  */
	public void setA_Accumdepreciation_Acct (int A_Accumdepreciation_Acct);

	/** Get Accumulated Depreciation Account	  */
	public int getA_Accumdepreciation_Acct();

	public I_C_ValidCombination getA_Accumdepreciation_A() throws RuntimeException;

    /** Column name A_Asset_Acct */
    public static final String COLUMNNAME_A_Asset_Acct = "A_Asset_Acct";

	/** Set Asset Acct	  */
	public void setA_Asset_Acct (int A_Asset_Acct);

	/** Get Asset Acct	  */
	public int getA_Asset_Acct();

	public I_C_ValidCombination getA_Asset_A() throws RuntimeException;

    /** Column name A_Asset_Group_Acct_ID */
    public static final String COLUMNNAME_A_Asset_Group_Acct_ID = "A_Asset_Group_Acct_ID";

	/** Set Asset Group Accounting	  */
	public void setA_Asset_Group_Acct_ID (int A_Asset_Group_Acct_ID);

	/** Get Asset Group Accounting	  */
	public int getA_Asset_Group_Acct_ID();

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

	public org.compiere.model.I_A_Asset_Group getA_Asset_Group() throws RuntimeException;

    /** Column name A_Asset_Spread_Type */
    public static final String COLUMNNAME_A_Asset_Spread_Type = "A_Asset_Spread_Type";

	/** Set Spread Type	  */
	public void setA_Asset_Spread_Type (int A_Asset_Spread_Type);

	/** Get Spread Type	  */
	public int getA_Asset_Spread_Type();

	public org.compiere.model.I_A_Asset_Spread getA_Asset_Spread_T() throws RuntimeException;

    /** Column name A_Depreciation_Acct */
    public static final String COLUMNNAME_A_Depreciation_Acct = "A_Depreciation_Acct";

	/** Set Depreciation Account	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct);

	/** Get Depreciation Account	  */
	public int getA_Depreciation_Acct();

	public I_C_ValidCombination getA_Depreciation_A() throws RuntimeException;

    /** Column name A_Depreciation_Calc_Type */
    public static final String COLUMNNAME_A_Depreciation_Calc_Type = "A_Depreciation_Calc_Type";

	/** Set Calculation Type	  */
	public void setA_Depreciation_Calc_Type (int A_Depreciation_Calc_Type);

	/** Get Calculation Type	  */
	public int getA_Depreciation_Calc_Type();

	public org.compiere.model.I_A_Depreciation_Method getA_Depreciation_Calc_T() throws RuntimeException;

    /** Column name A_Depreciation_Conv_F_ID */
    public static final String COLUMNNAME_A_Depreciation_Conv_F_ID = "A_Depreciation_Conv_F_ID";

	/** Set Depreciation Convention (fiscal)	  */
	public void setA_Depreciation_Conv_F_ID (int A_Depreciation_Conv_F_ID);

	/** Get Depreciation Convention (fiscal)	  */
	public int getA_Depreciation_Conv_F_ID();

	public org.compiere.model.I_A_Depreciation_Convention getA_Depreciation_Conv_F() throws RuntimeException;

    /** Column name A_Depreciation_Conv_ID */
    public static final String COLUMNNAME_A_Depreciation_Conv_ID = "A_Depreciation_Conv_ID";

	/** Set Convention Type	  */
	public void setA_Depreciation_Conv_ID (int A_Depreciation_Conv_ID);

	/** Get Convention Type	  */
	public int getA_Depreciation_Conv_ID();

	public org.compiere.model.I_A_Depreciation_Convention getA_Depreciation_Conv() throws RuntimeException;

    /** Column name A_Depreciation_F_ID */
    public static final String COLUMNNAME_A_Depreciation_F_ID = "A_Depreciation_F_ID";

	/** Set Depreciation (fiscal)	  */
	public void setA_Depreciation_F_ID (int A_Depreciation_F_ID);

	/** Get Depreciation (fiscal)	  */
	public int getA_Depreciation_F_ID();

	public org.compiere.model.I_A_Depreciation getA_Depreciation_F() throws RuntimeException;

    /** Column name A_Depreciation_ID */
    public static final String COLUMNNAME_A_Depreciation_ID = "A_Depreciation_ID";

	/** Set Depreciation	  */
	public void setA_Depreciation_ID (int A_Depreciation_ID);

	/** Get Depreciation	  */
	public int getA_Depreciation_ID();

	public org.compiere.model.I_A_Depreciation getA_Depreciation() throws RuntimeException;

    /** Column name A_Depreciation_Manual_Amount */
    public static final String COLUMNNAME_A_Depreciation_Manual_Amount = "A_Depreciation_Manual_Amount";

	/** Set A_Depreciation_Manual_Amount	  */
	public void setA_Depreciation_Manual_Amount (BigDecimal A_Depreciation_Manual_Amount);

	/** Get A_Depreciation_Manual_Amount	  */
	public BigDecimal getA_Depreciation_Manual_Amount();

    /** Column name A_Depreciation_Manual_Period */
    public static final String COLUMNNAME_A_Depreciation_Manual_Period = "A_Depreciation_Manual_Period";

	/** Set A_Depreciation_Manual_Period	  */
	public void setA_Depreciation_Manual_Period (String A_Depreciation_Manual_Period);

	/** Get A_Depreciation_Manual_Period	  */
	public String getA_Depreciation_Manual_Period();

    /** Column name A_Depreciation_Method_F_ID */
    public static final String COLUMNNAME_A_Depreciation_Method_F_ID = "A_Depreciation_Method_F_ID";

	/** Set Depreciation Method (fiscal)	  */
	public void setA_Depreciation_Method_F_ID (int A_Depreciation_Method_F_ID);

	/** Get Depreciation Method (fiscal)	  */
	public int getA_Depreciation_Method_F_ID();

	public org.compiere.model.I_A_Depreciation_Method getA_Depreciation_Method_F() throws RuntimeException;

    /** Column name A_Depreciation_Method_ID */
    public static final String COLUMNNAME_A_Depreciation_Method_ID = "A_Depreciation_Method_ID";

	/** Set Depreciation Method	  */
	public void setA_Depreciation_Method_ID (int A_Depreciation_Method_ID);

	/** Get Depreciation Method	  */
	public int getA_Depreciation_Method_ID();

	public org.compiere.model.I_A_Depreciation_Method getA_Depreciation_Method() throws RuntimeException;

    /** Column name A_Depreciation_Table_Header_ID */
    public static final String COLUMNNAME_A_Depreciation_Table_Header_ID = "A_Depreciation_Table_Header_ID";

	/** Set A_Depreciation_Table_Header_ID	  */
	public void setA_Depreciation_Table_Header_ID (int A_Depreciation_Table_Header_ID);

	/** Get A_Depreciation_Table_Header_ID	  */
	public int getA_Depreciation_Table_Header_ID();

	public org.compiere.model.I_A_Depreciation_Table_Header getA_Depreciation_Table_Header() throws RuntimeException;

    /** Column name A_Depreciation_Variable_Perc */
    public static final String COLUMNNAME_A_Depreciation_Variable_Perc = "A_Depreciation_Variable_Perc";

	/** Set Variable Percent	  */
	public void setA_Depreciation_Variable_Perc (BigDecimal A_Depreciation_Variable_Perc);

	/** Get Variable Percent	  */
	public BigDecimal getA_Depreciation_Variable_Perc();

    /** Column name A_Depreciation_Variable_Perc_F */
    public static final String COLUMNNAME_A_Depreciation_Variable_Perc_F = "A_Depreciation_Variable_Perc_F";

	/** Set Variable Percent (fiscal)	  */
	public void setA_Depreciation_Variable_Perc_F (BigDecimal A_Depreciation_Variable_Perc_F);

	/** Get Variable Percent (fiscal)	  */
	public BigDecimal getA_Depreciation_Variable_Perc_F();

    /** Column name A_Disposal_Loss_Acct */
    public static final String COLUMNNAME_A_Disposal_Loss_Acct = "A_Disposal_Loss_Acct";

	/** Set Disposal Loss Acct	  */
	public void setA_Disposal_Loss_Acct (int A_Disposal_Loss_Acct);

	/** Get Disposal Loss Acct	  */
	public int getA_Disposal_Loss_Acct();

	public I_C_ValidCombination getA_Disposal_Loss_A() throws RuntimeException;

    /** Column name A_Disposal_Revenue_Acct */
    public static final String COLUMNNAME_A_Disposal_Revenue_Acct = "A_Disposal_Revenue_Acct";

	/** Set Disposal Revenue Acct	  */
	public void setA_Disposal_Revenue_Acct (int A_Disposal_Revenue_Acct);

	/** Get Disposal Revenue Acct	  */
	public int getA_Disposal_Revenue_Acct();

	public I_C_ValidCombination getA_Disposal_Revenue_A() throws RuntimeException;

    /** Column name A_Reval_Accumdep_Offset_Cur */
    public static final String COLUMNNAME_A_Reval_Accumdep_Offset_Cur = "A_Reval_Accumdep_Offset_Cur";

	/** Set Revaluation Accumulated Depreciation Offset for Current Year	  */
	public void setA_Reval_Accumdep_Offset_Cur (int A_Reval_Accumdep_Offset_Cur);

	/** Get Revaluation Accumulated Depreciation Offset for Current Year	  */
	public int getA_Reval_Accumdep_Offset_Cur();

	public I_C_ValidCombination getA_Reval_Accumdep_Offset_() throws RuntimeException;

    /** Column name A_Reval_Accumdep_Offset_Prior */
    public static final String COLUMNNAME_A_Reval_Accumdep_Offset_Prior = "A_Reval_Accumdep_Offset_Prior";

	/** Set Revaluation Accumulated Depreciation Offset for Prior Year	  */
	public void setA_Reval_Accumdep_Offset_Prior (int A_Reval_Accumdep_Offset_Prior);

	/** Get Revaluation Accumulated Depreciation Offset for Prior Year	  */
	public int getA_Reval_Accumdep_Offset_Prior();

	public I_C_ValidCombination getA_Reval_Accumdep_Offset_Pr() throws RuntimeException;

    /** Column name A_Reval_Adep_Offset_Cur_Acct */
    public static final String COLUMNNAME_A_Reval_Adep_Offset_Cur_Acct = "A_Reval_Adep_Offset_Cur_Acct";

	/** Set A_Reval_Accumdep_Offset_Cur	  */
	public void setA_Reval_Adep_Offset_Cur_Acct (int A_Reval_Adep_Offset_Cur_Acct);

	/** Get A_Reval_Accumdep_Offset_Cur	  */
	public int getA_Reval_Adep_Offset_Cur_Acct();

	public I_C_ValidCombination getA_Reval_Adep_Offset_Cur_A() throws RuntimeException;

    /** Column name A_Reval_Adep_Offset_Prior_Acct */
    public static final String COLUMNNAME_A_Reval_Adep_Offset_Prior_Acct = "A_Reval_Adep_Offset_Prior_Acct";

	/** Set A_Reval_Accumdep_Offset_Prior	  */
	public void setA_Reval_Adep_Offset_Prior_Acct (int A_Reval_Adep_Offset_Prior_Acct);

	/** Get A_Reval_Accumdep_Offset_Prior	  */
	public int getA_Reval_Adep_Offset_Prior_Acct();

	public I_C_ValidCombination getA_Reval_Adep_Offset_Prior_A() throws RuntimeException;

    /** Column name A_Reval_Cal_Method */
    public static final String COLUMNNAME_A_Reval_Cal_Method = "A_Reval_Cal_Method";

	/** Set A_Reval_Cal_Method	  */
	public void setA_Reval_Cal_Method (String A_Reval_Cal_Method);

	/** Get A_Reval_Cal_Method	  */
	public String getA_Reval_Cal_Method();

    /** Column name A_Reval_Cost_Offset */
    public static final String COLUMNNAME_A_Reval_Cost_Offset = "A_Reval_Cost_Offset";

	/** Set Revaluation Cost Offset for Current Year	  */
	public void setA_Reval_Cost_Offset (int A_Reval_Cost_Offset);

	/** Get Revaluation Cost Offset for Current Year	  */
	public int getA_Reval_Cost_Offset();

	public I_C_ValidCombination getA_Reval_Cost_Off() throws RuntimeException;

    /** Column name A_Reval_Cost_Offset_Acct */
    public static final String COLUMNNAME_A_Reval_Cost_Offset_Acct = "A_Reval_Cost_Offset_Acct";

	/** Set Reval Cost Offset Acct	  */
	public void setA_Reval_Cost_Offset_Acct (int A_Reval_Cost_Offset_Acct);

	/** Get Reval Cost Offset Acct	  */
	public int getA_Reval_Cost_Offset_Acct();

	public I_C_ValidCombination getA_Reval_Cost_Offset_A() throws RuntimeException;

    /** Column name A_Reval_Cost_Offset_Prior */
    public static final String COLUMNNAME_A_Reval_Cost_Offset_Prior = "A_Reval_Cost_Offset_Prior";

	/** Set Revaluation Cost Offset for Prior Year	  */
	public void setA_Reval_Cost_Offset_Prior (int A_Reval_Cost_Offset_Prior);

	/** Get Revaluation Cost Offset for Prior Year	  */
	public int getA_Reval_Cost_Offset_Prior();

	public I_C_ValidCombination getA_Reval_Cost_Offset_Pr() throws RuntimeException;

    /** Column name A_Reval_Cost_Offset_Prior_Acct */
    public static final String COLUMNNAME_A_Reval_Cost_Offset_Prior_Acct = "A_Reval_Cost_Offset_Prior_Acct";

	/** Set Reval Cost Offset Prior Acct	  */
	public void setA_Reval_Cost_Offset_Prior_Acct (int A_Reval_Cost_Offset_Prior_Acct);

	/** Get Reval Cost Offset Prior Acct	  */
	public int getA_Reval_Cost_Offset_Prior_Acct();

	public I_C_ValidCombination getA_Reval_Cost_Offset_Prior_A() throws RuntimeException;

    /** Column name A_Reval_Depexp_Offset */
    public static final String COLUMNNAME_A_Reval_Depexp_Offset = "A_Reval_Depexp_Offset";

	/** Set Revaluation Expense Offs	  */
	public void setA_Reval_Depexp_Offset (int A_Reval_Depexp_Offset);

	/** Get Revaluation Expense Offs	  */
	public int getA_Reval_Depexp_Offset();

	public I_C_ValidCombination getA_Reval_Depexp_Off() throws RuntimeException;

    /** Column name A_Reval_Depexp_Offset_Acct */
    public static final String COLUMNNAME_A_Reval_Depexp_Offset_Acct = "A_Reval_Depexp_Offset_Acct";

	/** Set Reval Depexp Offset Acct	  */
	public void setA_Reval_Depexp_Offset_Acct (int A_Reval_Depexp_Offset_Acct);

	/** Get Reval Depexp Offset Acct	  */
	public int getA_Reval_Depexp_Offset_Acct();

	public I_C_ValidCombination getA_Reval_Depexp_Offset_A() throws RuntimeException;

    /** Column name A_Split_Percent */
    public static final String COLUMNNAME_A_Split_Percent = "A_Split_Percent";

	/** Set Split Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent);

	/** Get Split Percent	  */
	public BigDecimal getA_Split_Percent();

    /** Column name C_AcctSchema_ID */
    public static final String COLUMNNAME_C_AcctSchema_ID = "C_AcctSchema_ID";

	/** Set Accounting Schema.
	  * Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID);

	/** Get Accounting Schema.
	  * Rules for accounting
	  */
	public int getC_AcctSchema_ID();

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

    /** Column name ConventionType */
    public static final String COLUMNNAME_ConventionType = "ConventionType";

	/** Set ConventionType	  */
	public void setConventionType (int ConventionType);

	/** Get ConventionType	  */
	public int getConventionType();

	public org.compiere.model.I_A_Depreciation_Convention getConventionT() throws RuntimeException;

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

    /** Column name DepreciationType */
    public static final String COLUMNNAME_DepreciationType = "DepreciationType";

	/** Set DepreciationType	  */
	public void setDepreciationType (int DepreciationType);

	/** Get DepreciationType	  */
	public int getDepreciationType();

	public org.compiere.model.I_A_Depreciation getDepreciationT() throws RuntimeException;

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
