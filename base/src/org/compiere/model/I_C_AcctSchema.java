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

    /** Generated Interface for C_AcctSchema
     *  @author Trifon Trifonov (generated) 
     *  @version Release 3.3.0 - 2007-08-24 11:39:36.406
     */
    public interface I_C_AcctSchema 
{

    /** TableName=C_AcctSchema */
    public static final String Table_Name = "C_AcctSchema";

    /** AD_Table_ID=265 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = new BigDecimal(2);

    /** Load Meta Data */

    /** Column name AD_OrgOnly_ID */
    public static final String COLUMNNAME_AD_OrgOnly_ID = "AD_OrgOnly_ID";

	/** Set Only Organization.
	  * Create posting entries only for this organization
	  */
	public void setAD_OrgOnly_ID (int AD_OrgOnly_ID);

	/** Get Only Organization.
	  * Create posting entries only for this organization
	  */
	public int getAD_OrgOnly_ID();

    /** Column name AutoPeriodControl */
    public static final String COLUMNNAME_AutoPeriodControl = "AutoPeriodControl";

	/** Set Automatic Period Control.
	  * If selected, the periods are automatically opened and closed
	  */
	public void setAutoPeriodControl (boolean AutoPeriodControl);

	/** Get Automatic Period Control.
	  * If selected, the periods are automatically opened and closed
	  */
	public boolean isAutoPeriodControl();

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

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getI_C_Currency() throws Exception;

    /** Column name C_Period_ID */
    public static final String COLUMNNAME_C_Period_ID = "C_Period_ID";

	/** Set Period.
	  * Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID);

	/** Get Period.
	  * Period of the Calendar
	  */
	public int getC_Period_ID();

	public I_C_Period getI_C_Period() throws Exception;

    /** Column name CommitmentType */
    public static final String COLUMNNAME_CommitmentType = "CommitmentType";

	/** Set Commitment Type.
	  * Create Commitment and/or Reservations for Budget Control
	  */
	public void setCommitmentType (String CommitmentType);

	/** Get Commitment Type.
	  * Create Commitment and/or Reservations for Budget Control
	  */
	public String getCommitmentType();

    /** Column name CostingLevel */
    public static final String COLUMNNAME_CostingLevel = "CostingLevel";

	/** Set Costing Level.
	  * The lowest level to accumulate Costing Information
	  */
	public void setCostingLevel (String CostingLevel);

	/** Get Costing Level.
	  * The lowest level to accumulate Costing Information
	  */
	public String getCostingLevel();

    /** Column name CostingMethod */
    public static final String COLUMNNAME_CostingMethod = "CostingMethod";

	/** Set Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public void setCostingMethod (String CostingMethod);

	/** Get Costing Method.
	  * Indicates how Costs will be calculated
	  */
	public String getCostingMethod();

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

    /** Column name GAAP */
    public static final String COLUMNNAME_GAAP = "GAAP";

	/** Set GAAP.
	  * Generally Accepted Accounting Principles
	  */
	public void setGAAP (String GAAP);

	/** Get GAAP.
	  * Generally Accepted Accounting Principles
	  */
	public String getGAAP();

    /** Column name HasAlias */
    public static final String COLUMNNAME_HasAlias = "HasAlias";

	/** Set Use Account Alias.
	  * Ability to select (partial) account combinations by an Alias
	  */
	public void setHasAlias (boolean HasAlias);

	/** Get Use Account Alias.
	  * Ability to select (partial) account combinations by an Alias
	  */
	public boolean isHasAlias();

    /** Column name HasCombination */
    public static final String COLUMNNAME_HasCombination = "HasCombination";

	/** Set Use Account Combination Control.
	  * Combination of account elements are checked
	  */
	public void setHasCombination (boolean HasCombination);

	/** Get Use Account Combination Control.
	  * Combination of account elements are checked
	  */
	public boolean isHasCombination();

    /** Column name IsAccrual */
    public static final String COLUMNNAME_IsAccrual = "IsAccrual";

	/** Set Accrual.
	  * Indicates if Accrual or Cash Based accounting will be used
	  */
	public void setIsAccrual (boolean IsAccrual);

	/** Get Accrual.
	  * Indicates if Accrual or Cash Based accounting will be used
	  */
	public boolean isAccrual();

    /** Column name IsAdjustCOGS */
    public static final String COLUMNNAME_IsAdjustCOGS = "IsAdjustCOGS";

	/** Set Adjust COGS.
	  * Adjust Cost of Good Sold
	  */
	public void setIsAdjustCOGS (boolean IsAdjustCOGS);

	/** Get Adjust COGS.
	  * Adjust Cost of Good Sold
	  */
	public boolean isAdjustCOGS();

    /** Column name IsAllowNegativePosting */
    public static final String COLUMNNAME_IsAllowNegativePosting = "IsAllowNegativePosting";

	/** Set Allow Negative Posting.
	  * Allow to post negative accounting values
	  */
	public void setIsAllowNegativePosting (boolean IsAllowNegativePosting);

	/** Get Allow Negative Posting.
	  * Allow to post negative accounting values
	  */
	public boolean isAllowNegativePosting();

    /** Column name IsDiscountCorrectsTax */
    public static final String COLUMNNAME_IsDiscountCorrectsTax = "IsDiscountCorrectsTax";

	/** Set Correct tax for Discounts/Charges.
	  * Correct the tax for payment discount and charges
	  */
	public void setIsDiscountCorrectsTax (boolean IsDiscountCorrectsTax);

	/** Get Correct tax for Discounts/Charges.
	  * Correct the tax for payment discount and charges
	  */
	public boolean isDiscountCorrectsTax();

    /** Column name IsExplicitCostAdjustment */
    public static final String COLUMNNAME_IsExplicitCostAdjustment = "IsExplicitCostAdjustment";

	/** Set Explicit Cost Adjustment.
	  * Post the cost adjustment explicitly
	  */
	public void setIsExplicitCostAdjustment (boolean IsExplicitCostAdjustment);

	/** Get Explicit Cost Adjustment.
	  * Post the cost adjustment explicitly
	  */
	public boolean isExplicitCostAdjustment();

    /** Column name IsPostServices */
    public static final String COLUMNNAME_IsPostServices = "IsPostServices";

	/** Set Post Services Separately.
	  * Differentiate between Services and Product Receivable/Payables
	  */
	public void setIsPostServices (boolean IsPostServices);

	/** Get Post Services Separately.
	  * Differentiate between Services and Product Receivable/Payables
	  */
	public boolean isPostServices();

    /** Column name IsTradeDiscountPosted */
    public static final String COLUMNNAME_IsTradeDiscountPosted = "IsTradeDiscountPosted";

	/** Set Post Trade Discount.
	  * Generate postings for trade discounts
	  */
	public void setIsTradeDiscountPosted (boolean IsTradeDiscountPosted);

	/** Get Post Trade Discount.
	  * Generate postings for trade discounts
	  */
	public boolean isTradeDiscountPosted();

    /** Column name M_CostType_ID */
    public static final String COLUMNNAME_M_CostType_ID = "M_CostType_ID";

	/** Set Cost Type.
	  * Type of Cost (e.g. Current, Plan, Future)
	  */
	public void setM_CostType_ID (int M_CostType_ID);

	/** Get Cost Type.
	  * Type of Cost (e.g. Current, Plan, Future)
	  */
	public int getM_CostType_ID();

	public I_M_CostType getI_M_CostType() throws Exception;

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

    /** Column name Period_OpenFuture */
    public static final String COLUMNNAME_Period_OpenFuture = "Period_OpenFuture";

	/** Set Future Days.
	  * Number of days to be able to post to a future date (based on system date)
	  */
	public void setPeriod_OpenFuture (int Period_OpenFuture);

	/** Get Future Days.
	  * Number of days to be able to post to a future date (based on system date)
	  */
	public int getPeriod_OpenFuture();

    /** Column name Period_OpenHistory */
    public static final String COLUMNNAME_Period_OpenHistory = "Period_OpenHistory";

	/** Set History Days.
	  * Number of days to be able to post in the past (based on system date)
	  */
	public void setPeriod_OpenHistory (int Period_OpenHistory);

	/** Get History Days.
	  * Number of days to be able to post in the past (based on system date)
	  */
	public int getPeriod_OpenHistory();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name Separator */
    public static final String COLUMNNAME_Separator = "Separator";

	/** Set Element Separator.
	  * Element Separator
	  */
	public void setSeparator (String Separator);

	/** Get Element Separator.
	  * Element Separator
	  */
	public String getSeparator();

    /** Column name TaxCorrectionType */
    public static final String COLUMNNAME_TaxCorrectionType = "TaxCorrectionType";

	/** Set Tax Correction.
	  * Type of Tax Correction
	  */
	public void setTaxCorrectionType (String TaxCorrectionType);

	/** Get Tax Correction.
	  * Type of Tax Correction
	  */
	public String getTaxCorrectionType();
}
