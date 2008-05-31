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

/** Generated Interface for A_Asset_Transfer
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Transfer 
{

    /** TableName=A_Asset_Transfer */
    public static final String Table_Name = "A_Asset_Transfer";

    /** AD_Table_ID=53128 */
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

    /** Column name A_Accumdepreciation_Acct_New */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct_New = "A_Accumdepreciation_Acct_New";

	/** Set A_Accumdepreciation_Acct_New	  */
	public void setA_Accumdepreciation_Acct_New (int A_Accumdepreciation_Acct_New);

	/** Get A_Accumdepreciation_Acct_New	  */
	public int getA_Accumdepreciation_Acct_New();

    /** Column name A_Accumdepreciation_Acct_Str */
    public static final String COLUMNNAME_A_Accumdepreciation_Acct_Str = "A_Accumdepreciation_Acct_Str";

	/** Set A_Accumdepreciation_Acct_Str	  */
	public void setA_Accumdepreciation_Acct_Str (String A_Accumdepreciation_Acct_Str);

	/** Get A_Accumdepreciation_Acct_Str	  */
	public String getA_Accumdepreciation_Acct_Str();

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

    /** Column name A_Asset_Acct_New */
    public static final String COLUMNNAME_A_Asset_Acct_New = "A_Asset_Acct_New";

	/** Set A_Asset_Acct_New	  */
	public void setA_Asset_Acct_New (int A_Asset_Acct_New);

	/** Get A_Asset_Acct_New	  */
	public int getA_Asset_Acct_New();

    /** Column name A_Asset_Acct_Str */
    public static final String COLUMNNAME_A_Asset_Acct_Str = "A_Asset_Acct_Str";

	/** Set A_Asset_Acct_Str	  */
	public void setA_Asset_Acct_Str (String A_Asset_Acct_Str);

	/** Get A_Asset_Acct_Str	  */
	public String getA_Asset_Acct_Str();

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

    /** Column name A_Asset_Transfer_ID */
    public static final String COLUMNNAME_A_Asset_Transfer_ID = "A_Asset_Transfer_ID";

	/** Set A_Asset_Transfer_ID	  */
	public void setA_Asset_Transfer_ID (int A_Asset_Transfer_ID);

	/** Get A_Asset_Transfer_ID	  */
	public int getA_Asset_Transfer_ID();

    /** Column name A_Depreciation_Acct */
    public static final String COLUMNNAME_A_Depreciation_Acct = "A_Depreciation_Acct";

	/** Set A_Depreciation_Acct	  */
	public void setA_Depreciation_Acct (int A_Depreciation_Acct);

	/** Get A_Depreciation_Acct	  */
	public int getA_Depreciation_Acct();

    /** Column name A_Depreciation_Acct_New */
    public static final String COLUMNNAME_A_Depreciation_Acct_New = "A_Depreciation_Acct_New";

	/** Set A_Depreciation_Acct_New	  */
	public void setA_Depreciation_Acct_New (int A_Depreciation_Acct_New);

	/** Get A_Depreciation_Acct_New	  */
	public int getA_Depreciation_Acct_New();

    /** Column name A_Depreciation_Acct_Str */
    public static final String COLUMNNAME_A_Depreciation_Acct_Str = "A_Depreciation_Acct_Str";

	/** Set A_Depreciation_Acct_Str	  */
	public void setA_Depreciation_Acct_Str (String A_Depreciation_Acct_Str);

	/** Get A_Depreciation_Acct_Str	  */
	public String getA_Depreciation_Acct_Str();

    /** Column name A_Disposal_Loss */
    public static final String COLUMNNAME_A_Disposal_Loss = "A_Disposal_Loss";

	/** Set A_Disposal_Loss	  */
	public void setA_Disposal_Loss (int A_Disposal_Loss);

	/** Get A_Disposal_Loss	  */
	public int getA_Disposal_Loss();

    /** Column name A_Disposal_Loss_New */
    public static final String COLUMNNAME_A_Disposal_Loss_New = "A_Disposal_Loss_New";

	/** Set A_Disposal_Loss_New	  */
	public void setA_Disposal_Loss_New (int A_Disposal_Loss_New);

	/** Get A_Disposal_Loss_New	  */
	public int getA_Disposal_Loss_New();

    /** Column name A_Disposal_Loss_Str */
    public static final String COLUMNNAME_A_Disposal_Loss_Str = "A_Disposal_Loss_Str";

	/** Set A_Disposal_Loss_Str	  */
	public void setA_Disposal_Loss_Str (String A_Disposal_Loss_Str);

	/** Get A_Disposal_Loss_Str	  */
	public String getA_Disposal_Loss_Str();

    /** Column name A_Disposal_Revenue */
    public static final String COLUMNNAME_A_Disposal_Revenue = "A_Disposal_Revenue";

	/** Set A_Disposal_Revenue	  */
	public void setA_Disposal_Revenue (int A_Disposal_Revenue);

	/** Get A_Disposal_Revenue	  */
	public int getA_Disposal_Revenue();

    /** Column name A_Disposal_Revenue_New */
    public static final String COLUMNNAME_A_Disposal_Revenue_New = "A_Disposal_Revenue_New";

	/** Set A_Disposal_Revenue_New	  */
	public void setA_Disposal_Revenue_New (int A_Disposal_Revenue_New);

	/** Get A_Disposal_Revenue_New	  */
	public int getA_Disposal_Revenue_New();

    /** Column name A_Disposal_Revenue_Str */
    public static final String COLUMNNAME_A_Disposal_Revenue_Str = "A_Disposal_Revenue_Str";

	/** Set A_Disposal_Revenue_Str	  */
	public void setA_Disposal_Revenue_Str (String A_Disposal_Revenue_Str);

	/** Get A_Disposal_Revenue_Str	  */
	public String getA_Disposal_Revenue_Str();

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

    /** Column name A_Split_Percent */
    public static final String COLUMNNAME_A_Split_Percent = "A_Split_Percent";

	/** Set A_Split_Percent	  */
	public void setA_Split_Percent (BigDecimal A_Split_Percent);

	/** Get A_Split_Percent	  */
	public BigDecimal getA_Split_Percent();

    /** Column name A_Transfer_Balance */
    public static final String COLUMNNAME_A_Transfer_Balance = "A_Transfer_Balance";

	/** Set A_Transfer_Balance	  */
	public void setA_Transfer_Balance (boolean A_Transfer_Balance);

	/** Get A_Transfer_Balance	  */
	public boolean isA_Transfer_Balance();

    /** Column name A_Transfer_Balance_IS */
    public static final String COLUMNNAME_A_Transfer_Balance_IS = "A_Transfer_Balance_IS";

	/** Set A_Transfer_Balance_IS	  */
	public void setA_Transfer_Balance_IS (boolean A_Transfer_Balance_IS);

	/** Get A_Transfer_Balance_IS	  */
	public boolean isA_Transfer_Balance_IS();

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
}
