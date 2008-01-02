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

/** Generated Interface for C_AcctSchema_GL
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1t
 */
public interface I_C_AcctSchema_GL 
{

    /** TableName=C_AcctSchema_GL */
    public static final String Table_Name = "C_AcctSchema_GL";

    /** AD_Table_ID=266 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 2 - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(2);

    /** Load Meta Data */

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

    /** Column name CommitmentOffsetSales_Acct */
    public static final String COLUMNNAME_CommitmentOffsetSales_Acct = "CommitmentOffsetSales_Acct";

	/** Set Commitment Offset Sales.
	  * Budgetary Commitment Offset Account for Sales
	  */
	public void setCommitmentOffsetSales_Acct (int CommitmentOffsetSales_Acct);

	/** Get Commitment Offset Sales.
	  * Budgetary Commitment Offset Account for Sales
	  */
	public int getCommitmentOffsetSales_Acct();

    /** Column name CommitmentOffset_Acct */
    public static final String COLUMNNAME_CommitmentOffset_Acct = "CommitmentOffset_Acct";

	/** Set Commitment Offset.
	  * Budgetary Commitment Offset Account
	  */
	public void setCommitmentOffset_Acct (int CommitmentOffset_Acct);

	/** Get Commitment Offset.
	  * Budgetary Commitment Offset Account
	  */
	public int getCommitmentOffset_Acct();

    /** Column name CurrencyBalancing_Acct */
    public static final String COLUMNNAME_CurrencyBalancing_Acct = "CurrencyBalancing_Acct";

	/** Set Currency Balancing Acct.
	  * Account used when a currency is out of balance
	  */
	public void setCurrencyBalancing_Acct (int CurrencyBalancing_Acct);

	/** Get Currency Balancing Acct.
	  * Account used when a currency is out of balance
	  */
	public int getCurrencyBalancing_Acct();

    /** Column name IncomeSummary_Acct */
    public static final String COLUMNNAME_IncomeSummary_Acct = "IncomeSummary_Acct";

	/** Set Income Summary Acct.
	  * Income Summary Account 
	  */
	public void setIncomeSummary_Acct (int IncomeSummary_Acct);

	/** Get Income Summary Acct.
	  * Income Summary Account 
	  */
	public int getIncomeSummary_Acct();

    /** Column name IntercompanyDueFrom_Acct */
    public static final String COLUMNNAME_IntercompanyDueFrom_Acct = "IntercompanyDueFrom_Acct";

	/** Set Intercompany Due From Acct.
	  * Intercompany Due From / Receivables Account
	  */
	public void setIntercompanyDueFrom_Acct (int IntercompanyDueFrom_Acct);

	/** Get Intercompany Due From Acct.
	  * Intercompany Due From / Receivables Account
	  */
	public int getIntercompanyDueFrom_Acct();

    /** Column name IntercompanyDueTo_Acct */
    public static final String COLUMNNAME_IntercompanyDueTo_Acct = "IntercompanyDueTo_Acct";

	/** Set Intercompany Due To Acct.
	  * Intercompany Due To / Payable Account
	  */
	public void setIntercompanyDueTo_Acct (int IntercompanyDueTo_Acct);

	/** Get Intercompany Due To Acct.
	  * Intercompany Due To / Payable Account
	  */
	public int getIntercompanyDueTo_Acct();

    /** Column name PPVOffset_Acct */
    public static final String COLUMNNAME_PPVOffset_Acct = "PPVOffset_Acct";

	/** Set PPV Offset.
	  * Purchase Price Variance Offset Account
	  */
	public void setPPVOffset_Acct (int PPVOffset_Acct);

	/** Get PPV Offset.
	  * Purchase Price Variance Offset Account
	  */
	public int getPPVOffset_Acct();

    /** Column name RetainedEarning_Acct */
    public static final String COLUMNNAME_RetainedEarning_Acct = "RetainedEarning_Acct";

	/** Set Retained Earning Acct	  */
	public void setRetainedEarning_Acct (int RetainedEarning_Acct);

	/** Get Retained Earning Acct	  */
	public int getRetainedEarning_Acct();

    /** Column name SuspenseBalancing_Acct */
    public static final String COLUMNNAME_SuspenseBalancing_Acct = "SuspenseBalancing_Acct";

	/** Set Suspense Balancing Acct	  */
	public void setSuspenseBalancing_Acct (int SuspenseBalancing_Acct);

	/** Get Suspense Balancing Acct	  */
	public int getSuspenseBalancing_Acct();

    /** Column name SuspenseError_Acct */
    public static final String COLUMNNAME_SuspenseError_Acct = "SuspenseError_Acct";

	/** Set Suspense Error Acct	  */
	public void setSuspenseError_Acct (int SuspenseError_Acct);

	/** Get Suspense Error Acct	  */
	public int getSuspenseError_Acct();

    /** Column name UseCurrencyBalancing */
    public static final String COLUMNNAME_UseCurrencyBalancing = "UseCurrencyBalancing";

	/** Set Use Currency Balancing	  */
	public void setUseCurrencyBalancing (boolean UseCurrencyBalancing);

	/** Get Use Currency Balancing	  */
	public boolean isUseCurrencyBalancing();

    /** Column name UseSuspenseBalancing */
    public static final String COLUMNNAME_UseSuspenseBalancing = "UseSuspenseBalancing";

	/** Set Use Suspense Balancing	  */
	public void setUseSuspenseBalancing (boolean UseSuspenseBalancing);

	/** Get Use Suspense Balancing	  */
	public boolean isUseSuspenseBalancing();

    /** Column name UseSuspenseError */
    public static final String COLUMNNAME_UseSuspenseError = "UseSuspenseError";

	/** Set Use Suspense Error	  */
	public void setUseSuspenseError (boolean UseSuspenseError);

	/** Get Use Suspense Error	  */
	public boolean isUseSuspenseError();
}
