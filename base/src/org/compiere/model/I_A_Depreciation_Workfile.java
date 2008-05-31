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

/** Generated Interface for A_Depreciation_Workfile
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
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

    /** Column name A_Accumulated_Depr */
    public static final String COLUMNNAME_A_Accumulated_Depr = "A_Accumulated_Depr";

	/** Set A_Accumulated_Depr	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr);

	/** Get A_Accumulated_Depr	  */
	public BigDecimal getA_Accumulated_Depr();

    /** Column name A_Asset_Cost */
    public static final String COLUMNNAME_A_Asset_Cost = "A_Asset_Cost";

	/** Set A_Asset_Cost	  */
	public void setA_Asset_Cost (BigDecimal A_Asset_Cost);

	/** Get A_Asset_Cost	  */
	public BigDecimal getA_Asset_Cost();

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

    /** Column name A_Asset_Life_Current_Year */
    public static final String COLUMNNAME_A_Asset_Life_Current_Year = "A_Asset_Life_Current_Year";

	/** Set A_Asset_Life_Current_Year	  */
	public void setA_Asset_Life_Current_Year (BigDecimal A_Asset_Life_Current_Year);

	/** Get A_Asset_Life_Current_Year	  */
	public BigDecimal getA_Asset_Life_Current_Year();

    /** Column name A_Asset_Life_Years */
    public static final String COLUMNNAME_A_Asset_Life_Years = "A_Asset_Life_Years";

	/** Set A_Asset_Life_Years	  */
	public void setA_Asset_Life_Years (int A_Asset_Life_Years);

	/** Get A_Asset_Life_Years	  */
	public int getA_Asset_Life_Years();

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

	/** Set A_Current_Period	  */
	public void setA_Current_Period (int A_Current_Period);

	/** Get A_Current_Period	  */
	public int getA_Current_Period();

    /** Column name A_Depreciation_Workfile_ID */
    public static final String COLUMNNAME_A_Depreciation_Workfile_ID = "A_Depreciation_Workfile_ID";

	/** Set A_Depreciation_Workfile_ID	  */
	public void setA_Depreciation_Workfile_ID (int A_Depreciation_Workfile_ID);

	/** Get A_Depreciation_Workfile_ID	  */
	public int getA_Depreciation_Workfile_ID();

    /** Column name A_Life_Period */
    public static final String COLUMNNAME_A_Life_Period = "A_Life_Period";

	/** Set A_Life_Period	  */
	public void setA_Life_Period (int A_Life_Period);

	/** Get A_Life_Period	  */
	public int getA_Life_Period();

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

	/** Set A_QTY_Current	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get A_QTY_Current	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_Salvage_Value */
    public static final String COLUMNNAME_A_Salvage_Value = "A_Salvage_Value";

	/** Set A_Salvage_Value	  */
	public void setA_Salvage_Value (BigDecimal A_Salvage_Value);

	/** Get A_Salvage_Value	  */
	public BigDecimal getA_Salvage_Value();

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

	/** Set PostingType.
	  * The type of posted amount for the transaction
	  */
	public void setPostingType (String PostingType);

	/** Get PostingType.
	  * The type of posted amount for the transaction
	  */
	public String getPostingType();

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();
}
