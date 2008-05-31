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
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for A_Asset_Change
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Change 
{

    /** TableName=A_Asset_Change */
    public static final String Table_Name = "A_Asset_Change";

    /** AD_Table_ID=53133 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Accumdepreciation_Acct */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct = "A_Accumdepreciation_Acct";

	/** Set A_Accumdepreciation_Acct	  */
	public void setA_Accumdepreciation_Acct (int A_Accumdepreciation_Acct);

	/** Get A_Accumdepreciation_Acct	  */
	public int getA_Accumdepreciation_Acct();

    /** Column name A_Asset_Acct */
    public static final String COLUMNNAME_A_Asset_Acct = "A_Asset_Acct";

	/** Set A_Asset_Acct	  */
	public void setA_Asset_Acct (int A_Asset_Acct);

	/** Get A_Asset_Acct	  */
	public int getA_Asset_Acct();

    /** Column name A_Asset_Acct_ID */
    public static final String COLUMNNAME_A_Asset_Acct_ID = "A_Asset_Acct_ID";

	/** Set A_Asset_Acct_ID	  */
	public void setA_Asset_Acct_ID (int A_Asset_Acct_ID);

	/** Get A_Asset_Acct_ID	  */
	public int getA_Asset_Acct_ID();

    /** Column name A_Asset_Addition_ID */
    public static final String COLUMNNAME_A_Asset_Addition_ID = "A_Asset_Addition_ID";

	/** Set A_Asset_Addition_ID	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID);

	/** Get A_Asset_Addition_ID	  */
	public int getA_Asset_Addition_ID();

    /** Column name A_Asset_Change_ID */
    public static final String COLUMNNAME_A_Asset_Change_ID = "A_Asset_Change_ID";

	/** Set A_Asset_Change_ID	  */
	public void setA_Asset_Change_ID (int A_Asset_Change_ID);

	/** Get A_Asset_Change_ID	  */
	public int getA_Asset_Change_ID();

    /** Column name A_Asset_CreateDate */
    public static final String COLUMNNAME_A_Asset_CreateDate = "A_Asset_CreateDate";

	/** Set A_Asset_CreateDate	  */
	public void setA_Asset_CreateDate (Timestamp A_Asset_CreateDate);

	/** Get A_Asset_CreateDate	  */
	public Timestamp getA_Asset_CreateDate();

    /** Column name A_Asset_ID */
    public static final String COLUMNNAME_A_Asset_ID = "A_Asset_ID";

	/** Set Asset.
	  * Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID);

	/** Get Asset.
	  * Asset used internally or by customers
	  */
	public int getA_Asset_ID();

    /** Column name A_Asset_Retirement_ID */
    public static final String COLUMNNAME_A_Asset_Retirement_ID = "A_Asset_Retirement_ID";

	/** Set Asset Retirement.
	  * Internally used asset is not longer used.
	  */
	public void setA_Asset_Retirement_ID (int A_Asset_Retirement_ID);

	/** Get Asset Retirement.
	  * Internally used asset is not longer used.
	  */
	public int getA_Asset_Retirement_ID();

    /** Column name A_Asset_RevalDate */
    public static final String COLUMNNAME_A_Asset_RevalDate = "A_Asset_RevalDate";

	/** Set A_Asset_RevalDate	  */
	public void setA_Asset_RevalDate (Timestamp A_Asset_RevalDate);

	/** Get A_Asset_RevalDate	  */
	public Timestamp getA_Asset_RevalDate();

    /** Column name A_Asset_Spread_Type */
    public static final String COLUMNNAME_A_Asset_Spread_Type = "A_Asset_Spread_Type";

	/** Set A_Asset_Spread_Type	  */
	public void setA_Asset_Spread_Type (int A_Asset_Spread_Type);

	/** Get A_Asset_Spread_Type	  */
	public int getA_Asset_Spread_Type();

    /** Column name A_Depreciation_Acct */
    public static final String COLUMNNAME_A_Depreciation_Acct = "A_Depreciation_Acct";

	/** Set A_Depreciation_Acct	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct);

	/** Get A_Depreciation_Acct	  */
	public int getA_Depreciation_Acct();

    /** Column name A_Depreciation_Calc_Type */
    public static final String COLUMNNAME_A_Depreciation_Calc_Type = "A_Depreciation_Calc_Type";

	/** Set A_Depreciation_Calc_Type	  */
	public void setA_Depreciation_Calc_Type (int A_Depreciation_Calc_Type);

	/** Get A_Depreciation_Calc_Type	  */
	public int getA_Depreciation_Calc_Type();

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

    /** Column name A_Depreciation_Table_Header_ID */
    public static final String COLUMNNAME_A_Depreciation_Table_Header_ID = "A_Depreciation_Table_Header_ID";

	/** Set A_Depreciation_Table_Header_ID	  */
	public void setA_Depreciation_Table_Header_ID (int A_Depreciation_Table_Header_ID);

	/** Get A_Depreciation_Table_Header_ID	  */
	public int getA_Depreciation_Table_Header_ID();

    /** Column name A_Depreciation_Variable_Perc */
    public static final String COLUMNNAME_A_Depreciation_Variable_Perc = "A_Depreciation_Variable_Perc";

	/** Set A_Depreciation_Variable_Perc	  */
	public void setA_Depreciation_Variable_Perc (BigDecimal A_Depreciation_Variable_Perc);

	/** Get A_Depreciation_Variable_Perc	  */
	public BigDecimal getA_Depreciation_Variable_Perc();

    /** Column name A_Disposal_Loss */
    public static final String COLUMNNAME_A_Disposal_Loss = "A_Disposal_Loss";

	/** Set A_Disposal_Loss	  */
	public void setA_Disposal_Loss (int A_Disposal_Loss);

	/** Get A_Disposal_Loss	  */
	public int getA_Disposal_Loss();

    /** Column name A_Disposal_Revenue */
    public static final String COLUMNNAME_A_Disposal_Revenue = "A_Disposal_Revenue";

	/** Set A_Disposal_Revenue	  */
	public void setA_Disposal_Revenue (int A_Disposal_Revenue);

	/** Get A_Disposal_Revenue	  */
	public int getA_Disposal_Revenue();

    /** Column name A_Parent_Asset_ID */
    public static final String COLUMNNAME_A_Parent_Asset_ID = "A_Parent_Asset_ID";

	/** Set A_Parent_Asset_ID	  */
	public void setA_Parent_Asset_ID (int A_Parent_Asset_ID);

	/** Get A_Parent_Asset_ID	  */
	public int getA_Parent_Asset_ID();

    /** Column name A_Period_End */
    public static final String COLUMNNAME_A_Period_End = "A_Period_End";

	/** Set A_Period_End	  */
	public void setA_Period_End (int A_Period_End);

	/** Get A_Period_End	  */
	public int getA_Period_End();

    /** Column name A_Period_Start */
    public static final String COLUMNNAME_A_Period_Start = "A_Period_Start";

	/** Set A_Period_Start	  */
	public void setA_Period_Start (int A_Period_Start);

	/** Get A_Period_Start	  */
	public int getA_Period_Start();

    /** Column name A_QTY_Current */
    public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

	/** Set A_QTY_Current	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get A_QTY_Current	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_QTY_Original */
    public static final String COLUMNNAME_A_QTY_Original = "A_QTY_Original";

	/** Set A_QTY_Original	  */
	public void setA_QTY_Original (BigDecimal A_QTY_Original);

	/** Get A_QTY_Original	  */
	public BigDecimal getA_QTY_Original();

    /** Column name A_Reval_Accumdep_Offset_Cur */
    public static final String COLUMNNAME_A_Reval_Accumdep_Offset_Cur = "A_Reval_Accumdep_Offset_Cur";

	/** Set A_Reval_Accumdep_Offset_Cur	  */
	public void setA_Reval_Accumdep_Offset_Cur (int A_Reval_Accumdep_Offset_Cur);

	/** Get A_Reval_Accumdep_Offset_Cur	  */
	public int getA_Reval_Accumdep_Offset_Cur();

    /** Column name A_Reval_Accumdep_Offset_Prior */
    public static final String COLUMNNAME_A_Reval_Accumdep_Offset_Prior = "A_Reval_Accumdep_Offset_Prior";

	/** Set A_Reval_Accumdep_Offset_Prior	  */
	public void setA_Reval_Accumdep_Offset_Prior (int A_Reval_Accumdep_Offset_Prior);

	/** Get A_Reval_Accumdep_Offset_Prior	  */
	public int getA_Reval_Accumdep_Offset_Prior();

    /** Column name A_Reval_Cal_Method */
    public static final String COLUMNNAME_A_Reval_Cal_Method = "A_Reval_Cal_Method";

	/** Set A_Reval_Cal_Method	  */
	public void setA_Reval_Cal_Method (String A_Reval_Cal_Method);

	/** Get A_Reval_Cal_Method	  */
	public String getA_Reval_Cal_Method();

    /** Column name A_Reval_Cost_Offset */
    public static final String COLUMNNAME_A_Reval_Cost_Offset = "A_Reval_Cost_Offset";

	/** Set A_Reval_Cost_Offset	  */
	public void setA_Reval_Cost_Offset (int A_Reval_Cost_Offset);

	/** Get A_Reval_Cost_Offset	  */
	public int getA_Reval_Cost_Offset();

    /** Column name A_Reval_Cost_Offset_Prior */
    public static final String COLUMNNAME_A_Reval_Cost_Offset_Prior = "A_Reval_Cost_Offset_Prior";

	/** Set A_Reval_Cost_Offset_Prior	  */
	public void setA_Reval_Cost_Offset_Prior (int A_Reval_Cost_Offset_Prior);

	/** Get A_Reval_Cost_Offset_Prior	  */
	public int getA_Reval_Cost_Offset_Prior();

    /** Column name A_Reval_Depexp_Offset */
    public static final String COLUMNNAME_A_Reval_Depexp_Offset = "A_Reval_Depexp_Offset";

	/** Set A_Reval_Depexp_Offset	  */
	public void setA_Reval_Depexp_Offset (int A_Reval_Depexp_Offset);

	/** Get A_Reval_Depexp_Offset	  */
	public int getA_Reval_Depexp_Offset();

    /** Column name A_Salvage_Value */
    public static final String COLUMNNAME_A_Salvage_Value = "A_Salvage_Value";

	/** Set A_Salvage_Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value);

	/** Get A_Salvage_Value	  */
	public BigDecimal getA_Salvage_Value();

    /** Column name A_Split_Percent */
    public static final String COLUMNNAME_A_Split_Percent = "A_Split_Percent";

	/** Set A_Split_Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent);

	/** Get A_Split_Percent	  */
	public BigDecimal getA_Split_Percent();

    /** Column name Ad_User_ID */
    public static final String COLUMNNAME_Ad_User_ID = "Ad_User_ID";

	/** Set Ad_User_ID	  */
	public void setAd_User_ID (int Ad_User_ID);

	/** Get Ad_User_ID	  */
	public int getAd_User_ID();

    /** Column name AssetAccumDepreciationAmt */
    public static final String COLUMNNAME_AssetAccumDepreciationAmt = "AssetAccumDepreciationAmt";

	/** Set AssetAccumDepreciationAmt	  */
	public void setAssetAccumDepreciationAmt (BigDecimal AssetAccumDepreciationAmt);

	/** Get AssetAccumDepreciationAmt	  */
	public BigDecimal getAssetAccumDepreciationAmt();

    /** Column name AssetBookValueAmt */
    public static final String COLUMNNAME_AssetBookValueAmt = "AssetBookValueAmt";

	/** Set AssetBookValueAmt	  */
	public void setAssetBookValueAmt (BigDecimal AssetBookValueAmt);

	/** Get AssetBookValueAmt	  */
	public BigDecimal getAssetBookValueAmt();

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

    /** Column name AssetDisposalDate */
    public static final String COLUMNNAME_AssetDisposalDate = "AssetDisposalDate";

	/** Set Asset Disposal Date.
	  * Date when the asset is/was disposed
	  */
	public void setAssetDisposalDate (Timestamp AssetDisposalDate);

	/** Get Asset Disposal Date.
	  * Date when the asset is/was disposed
	  */
	public Timestamp getAssetDisposalDate();

    /** Column name AssetMarketValueAmt */
    public static final String COLUMNNAME_AssetMarketValueAmt = "AssetMarketValueAmt";

	/** Set Market value Amount.
	  * Market value of the asset
	  */
	public void setAssetMarketValueAmt (BigDecimal AssetMarketValueAmt);

	/** Get Market value Amount.
	  * Market value of the asset
	  */
	public BigDecimal getAssetMarketValueAmt();

    /** Column name AssetServiceDate */
    public static final String COLUMNNAME_AssetServiceDate = "AssetServiceDate";

	/** Set In Service Date.
	  * Date when Asset was put into service
	  */
	public void setAssetServiceDate (Timestamp AssetServiceDate);

	/** Get In Service Date.
	  * Date when Asset was put into service
	  */
	public Timestamp getAssetServiceDate();

    /** Column name AssetValueAmt */
    public static final String COLUMNNAME_AssetValueAmt = "AssetValueAmt";

	/** Set Asset value.
	  * Book Value of the asset
	  */
	public void setAssetValueAmt (BigDecimal AssetValueAmt);

	/** Get Asset value.
	  * Book Value of the asset
	  */
	public BigDecimal getAssetValueAmt();

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

	public I_C_AcctSchema getC_AcctSchema() throws Exception;

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

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

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

    /** Column name C_ValidCombination_ID */
    public static final String COLUMNNAME_C_ValidCombination_ID = "C_ValidCombination_ID";

	/** Set Combination.
	  * Valid Account Combination
	  */
	public void setC_ValidCombination_ID (int C_ValidCombination_ID);

	/** Get Combination.
	  * Valid Account Combination
	  */
	public int getC_ValidCombination_ID();

    /** Column name ChangeAmt */
    public static final String COLUMNNAME_ChangeAmt = "ChangeAmt";

	/** Set ChangeAmt	  */
	public void setChangeAmt (BigDecimal ChangeAmt);

	/** Get ChangeAmt	  */
	public BigDecimal getChangeAmt();

    /** Column name ChangeDate */
    public static final String COLUMNNAME_ChangeDate = "ChangeDate";

	/** Set ChangeDate	  */
	public void setChangeDate (Timestamp ChangeDate);

	/** Get ChangeDate	  */
	public Timestamp getChangeDate();

    /** Column name ChangeType */
    public static final String COLUMNNAME_ChangeType = "ChangeType";

	/** Set ChangeType	  */
	public void setChangeType (String ChangeType);

	/** Get ChangeType	  */
	public String getChangeType();

    /** Column name ConventionType */
    public static final String COLUMNNAME_ConventionType = "ConventionType";

	/** Set ConventionType	  */
	public void setConventionType (int ConventionType);

	/** Get ConventionType	  */
	public int getConventionType();

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

    /** Column name DepreciationType */
    public static final String COLUMNNAME_DepreciationType = "DepreciationType";

	/** Set DepreciationType	  */
	public void setDepreciationType (int DepreciationType);

	/** Get DepreciationType	  */
	public int getDepreciationType();

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

    /** Column name IsDisposed */
    public static final String COLUMNNAME_IsDisposed = "IsDisposed";

	/** Set Disposed.
	  * The asset is disposed
	  */
	public void setIsDisposed (boolean IsDisposed);

	/** Get Disposed.
	  * The asset is disposed
	  */
	public boolean isDisposed();

    /** Column name IsFullyDepreciated */
    public static final String COLUMNNAME_IsFullyDepreciated = "IsFullyDepreciated";

	/** Set Fully depreciated.
	  * The asset is fully depreciated
	  */
	public void setIsFullyDepreciated (boolean IsFullyDepreciated);

	/** Get Fully depreciated.
	  * The asset is fully depreciated
	  */
	public boolean isFullyDepreciated();

    /** Column name IsInPosession */
    public static final String COLUMNNAME_IsInPosession = "IsInPosession";

	/** Set In Possession.
	  * The asset is in the possession of the organization
	  */
	public void setIsInPosession (boolean IsInPosession);

	/** Get In Possession.
	  * The asset is in the possession of the organization
	  */
	public boolean isInPosession();

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

    /** Column name LifeUseUnits */
    public static final String COLUMNNAME_LifeUseUnits = "LifeUseUnits";

	/** Set Life use.
	  * Units of use until the asset is not usable anymore
	  */
	public void setLifeUseUnits (int LifeUseUnits);

	/** Get Life use.
	  * Units of use until the asset is not usable anymore
	  */
	public int getLifeUseUnits();

    /** Column name Lot */
    public static final String COLUMNNAME_Lot = "Lot";

	/** Set Lot No.
	  * Lot number (alphanumeric)
	  */
	public void setLot (String Lot);

	/** Get Lot No.
	  * Lot number (alphanumeric)
	  */
	public String getLot();

    /** Column name PostingType */
    public static final String COLUMNNAME_PostingType = "PostingType";

	/** Set PostingType.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get PostingType.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

    /** Column name Serno */
    public static final String COLUMNNAME_Serno = "Serno";

	/** Set Serno	  */
	public void setSerno (String Serno);

	/** Get Serno	  */
	public String getSerno();

    /** Column name TextDetails */
    public static final String COLUMNNAME_TextDetails = "TextDetails";

	/** Set Details	  */
	public void setTextDetails (String TextDetails);

	/** Get Details	  */
	public String getTextDetails();

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

    /** Column name UseUnits */
    public static final String COLUMNNAME_UseUnits = "UseUnits";

	/** Set Use units.
	  * Currently used units of the assets
	  */
	public void setUseUnits (int UseUnits);

	/** Get Use units.
	  * Currently used units of the assets
	  */
	public int getUseUnits();

    /** Column name Versionno */
    public static final String COLUMNNAME_Versionno = "Versionno";

	/** Set Versionno	  */
	public void setVersionno (String Versionno);

	/** Get Versionno	  */
	public String getVersionno();
}
