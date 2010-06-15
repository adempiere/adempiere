/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_AcctSchema_GL
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS
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

	public I_C_AcctSchema getC_AcctSchema() throws RuntimeException;

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

	public I_C_ValidCombination getCommitmentOffset_A() throws RuntimeException;

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

	public I_C_ValidCombination getCommitmentOffsetSales_A() throws RuntimeException;

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

	public I_C_ValidCombination getCurrencyBalancing_A() throws RuntimeException;

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

	public I_C_ValidCombination getIncomeSummary_A() throws RuntimeException;

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

	public I_C_ValidCombination getIntercompanyDueFrom_A() throws RuntimeException;

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

	public I_C_ValidCombination getIntercompanyDueTo_A() throws RuntimeException;

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

	public I_C_ValidCombination getPPVOffset_A() throws RuntimeException;

    /** Column name RetainedEarning_Acct */
    public static final String COLUMNNAME_RetainedEarning_Acct = "RetainedEarning_Acct";

	/** Set Retained Earning Acct	  */
	public void setRetainedEarning_Acct (int RetainedEarning_Acct);

	/** Get Retained Earning Acct	  */
	public int getRetainedEarning_Acct();

	public I_C_ValidCombination getRetainedEarning_A() throws RuntimeException;

    /** Column name SuspenseBalancing_Acct */
    public static final String COLUMNNAME_SuspenseBalancing_Acct = "SuspenseBalancing_Acct";

	/** Set Suspense Balancing Acct	  */
	public void setSuspenseBalancing_Acct (int SuspenseBalancing_Acct);

	/** Get Suspense Balancing Acct	  */
	public int getSuspenseBalancing_Acct();

	public I_C_ValidCombination getSuspenseBalancing_A() throws RuntimeException;

    /** Column name SuspenseError_Acct */
    public static final String COLUMNNAME_SuspenseError_Acct = "SuspenseError_Acct";

	/** Set Suspense Error Acct	  */
	public void setSuspenseError_Acct (int SuspenseError_Acct);

	/** Get Suspense Error Acct	  */
	public int getSuspenseError_Acct();

	public I_C_ValidCombination getSuspenseError_A() throws RuntimeException;

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
