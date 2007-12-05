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
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_Withholding
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_Withholding 
{

    /** TableName=C_Withholding */
    public static final String Table_Name = "C_Withholding";

    /** AD_Table_ID=304 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name Beneficiary */
    public static final String COLUMNNAME_Beneficiary = "Beneficiary";

	/** Set Beneficiary.
	  * Business Partner to whom payment is made
	  */
	public void setBeneficiary (int Beneficiary);

	/** Get Beneficiary.
	  * Business Partner to whom payment is made
	  */
	public int getBeneficiary();

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

	public I_C_PaymentTerm getC_PaymentTerm() throws Exception;

    /** Column name C_Withholding_ID */
    public static final String COLUMNNAME_C_Withholding_ID = "C_Withholding_ID";

	/** Set Withholding.
	  * Withholding type defined
	  */
	public void setC_Withholding_ID (int C_Withholding_ID);

	/** Get Withholding.
	  * Withholding type defined
	  */
	public int getC_Withholding_ID();

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

    /** Column name FixAmt */
    public static final String COLUMNNAME_FixAmt = "FixAmt";

	/** Set Fix amount.
	  * Fix amounted amount to be levied or paid
	  */
	public void setFixAmt (BigDecimal FixAmt);

	/** Get Fix amount.
	  * Fix amounted amount to be levied or paid
	  */
	public BigDecimal getFixAmt();

    /** Column name IsPaidTo3Party */
    public static final String COLUMNNAME_IsPaidTo3Party = "IsPaidTo3Party";

	/** Set Paid to third party.
	  * Amount paid to someone other than the Business Partner
	  */
	public void setIsPaidTo3Party (boolean IsPaidTo3Party);

	/** Get Paid to third party.
	  * Amount paid to someone other than the Business Partner
	  */
	public boolean isPaidTo3Party();

    /** Column name IsPercentWithholding */
    public static final String COLUMNNAME_IsPercentWithholding = "IsPercentWithholding";

	/** Set Percent withholding.
	  * Withholding amount is a percentage of the invoice amount
	  */
	public void setIsPercentWithholding (boolean IsPercentWithholding);

	/** Get Percent withholding.
	  * Withholding amount is a percentage of the invoice amount
	  */
	public boolean isPercentWithholding();

    /** Column name IsTaxProrated */
    public static final String COLUMNNAME_IsTaxProrated = "IsTaxProrated";

	/** Set Prorate tax.
	  * Tax is Prorated
	  */
	public void setIsTaxProrated (boolean IsTaxProrated);

	/** Get Prorate tax.
	  * Tax is Prorated
	  */
	public boolean isTaxProrated();

    /** Column name IsTaxWithholding */
    public static final String COLUMNNAME_IsTaxWithholding = "IsTaxWithholding";

	/** Set Tax withholding.
	  * This is a tax related withholding
	  */
	public void setIsTaxWithholding (boolean IsTaxWithholding);

	/** Get Tax withholding.
	  * This is a tax related withholding
	  */
	public boolean isTaxWithholding();

    /** Column name MaxAmt */
    public static final String COLUMNNAME_MaxAmt = "MaxAmt";

	/** Set Max Amount.
	  * Maximum Amount in invoice currency
	  */
	public void setMaxAmt (BigDecimal MaxAmt);

	/** Get Max Amount.
	  * Maximum Amount in invoice currency
	  */
	public BigDecimal getMaxAmt();

    /** Column name MinAmt */
    public static final String COLUMNNAME_MinAmt = "MinAmt";

	/** Set Min Amount.
	  * Minimum Amount in invoice currency
	  */
	public void setMinAmt (BigDecimal MinAmt);

	/** Get Min Amount.
	  * Minimum Amount in invoice currency
	  */
	public BigDecimal getMinAmt();

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

    /** Column name Percent */
    public static final String COLUMNNAME_Percent = "Percent";

	/** Set Percent.
	  * Percentage
	  */
	public void setPercent (BigDecimal Percent);

	/** Get Percent.
	  * Percentage
	  */
	public BigDecimal getPercent();

    /** Column name ThresholdMax */
    public static final String COLUMNNAME_ThresholdMax = "ThresholdMax";

	/** Set Threshold max.
	  * Maximum gross amount for withholding calculation  (0=no limit)
	  */
	public void setThresholdMax (BigDecimal ThresholdMax);

	/** Get Threshold max.
	  * Maximum gross amount for withholding calculation  (0=no limit)
	  */
	public BigDecimal getThresholdMax();

    /** Column name Thresholdmin */
    public static final String COLUMNNAME_Thresholdmin = "Thresholdmin";

	/** Set Threshold min.
	  * Minimum gross amount for withholding calculation
	  */
	public void setThresholdmin (BigDecimal Thresholdmin);

	/** Get Threshold min.
	  * Minimum gross amount for withholding calculation
	  */
	public BigDecimal getThresholdmin();
}
