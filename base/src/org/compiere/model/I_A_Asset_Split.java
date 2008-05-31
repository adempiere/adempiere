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

/** Generated Interface for A_Asset_Split
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.5.1a
 */
public interface I_A_Asset_Split 
{

    /** TableName=A_Asset_Split */
    public static final String Table_Name = "A_Asset_Split";

    /** AD_Table_ID=53122 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 7 - System - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(7);

    /** Load Meta Data */

    /** Column name A_Amount_Split */
    public static final String COLUMNNAME_A_Amount_Split = "A_Amount_Split";

	/** Set A_Amount_Split	  */
	public void setA_Amount_Split (BigDecimal A_Amount_Split);

	/** Get A_Amount_Split	  */
	public BigDecimal getA_Amount_Split();

    /** Column name A_Asset_Acct_ID */
    public static final String COLUMNNAME_A_Asset_Acct_ID = "A_Asset_Acct_ID";

	/** Set A_Asset_Acct_ID	  */
	public void setA_Asset_Acct_ID (int A_Asset_Acct_ID);

	/** Get A_Asset_Acct_ID	  */
	public int getA_Asset_Acct_ID();

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

    /** Column name A_Asset_ID_To */
    public static final String COLUMNNAME_A_Asset_ID_To = "A_Asset_ID_To";

	/** Set A_Asset_ID_To	  */
	public void setA_Asset_ID_To (int A_Asset_ID_To);

	/** Get A_Asset_ID_To	  */
	public int getA_Asset_ID_To();

    /** Column name A_Asset_Split_ID */
    public static final String COLUMNNAME_A_Asset_Split_ID = "A_Asset_Split_ID";

	/** Set A_Asset_Split_ID	  */
	public void setA_Asset_Split_ID (int A_Asset_Split_ID);

	/** Get A_Asset_Split_ID	  */
	public int getA_Asset_Split_ID();

    /** Column name A_Depreciation_Workfile_ID */
    public static final String COLUMNNAME_A_Depreciation_Workfile_ID = "A_Depreciation_Workfile_ID";

	/** Set A_Depreciation_Workfile_ID	  */
	public void setA_Depreciation_Workfile_ID (int A_Depreciation_Workfile_ID);

	/** Get A_Depreciation_Workfile_ID	  */
	public int getA_Depreciation_Workfile_ID();

    /** Column name A_Percent_Original */
    public static final String COLUMNNAME_A_Percent_Original = "A_Percent_Original";

	/** Set A_Percent_Original	  */
	public void setA_Percent_Original (BigDecimal A_Percent_Original);

	/** Get A_Percent_Original	  */
	public BigDecimal getA_Percent_Original();

    /** Column name A_Percent_Split */
    public static final String COLUMNNAME_A_Percent_Split = "A_Percent_Split";

	/** Set A_Percent_Split	  */
	public void setA_Percent_Split (BigDecimal A_Percent_Split);

	/** Get A_Percent_Split	  */
	public BigDecimal getA_Percent_Split();

    /** Column name A_QTY_Current */
    public static final String COLUMNNAME_A_QTY_Current = "A_QTY_Current";

	/** Set A_QTY_Current	  */
	public void setA_QTY_Current (BigDecimal A_QTY_Current);

	/** Get A_QTY_Current	  */
	public BigDecimal getA_QTY_Current();

    /** Column name A_QTY_Split */
    public static final String COLUMNNAME_A_QTY_Split = "A_QTY_Split";

	/** Set A_QTY_Split	  */
	public void setA_QTY_Split (BigDecimal A_QTY_Split);

	/** Get A_QTY_Split	  */
	public BigDecimal getA_QTY_Split();

    /** Column name A_Split_Type */
    public static final String COLUMNNAME_A_Split_Type = "A_Split_Type";

	/** Set A_Split_Type	  */
	public void setA_Split_Type (String A_Split_Type);

	/** Get A_Split_Type	  */
	public String getA_Split_Type();

    /** Column name A_Transfer_Balance_IS */
    public static final String COLUMNNAME_A_Transfer_Balance_IS = "A_Transfer_Balance_IS";

	/** Set A_Transfer_Balance_IS	  */
	public void setA_Transfer_Balance_IS (boolean A_Transfer_Balance_IS);

	/** Get A_Transfer_Balance_IS	  */
	public boolean isA_Transfer_Balance_IS();

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
