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

/** Generated Interface for A_Depreciation_Exp
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_A_Depreciation_Exp 
{

    /** TableName=A_Depreciation_Exp */
    public static final String Table_Name = "A_Depreciation_Exp";

    /** AD_Table_ID=53115 */
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

    /** Column name A_Account_Number_Acct */
    public static final String COLUMNNAME_A_Account_Number_Acct = "A_Account_Number_Acct";

	/** Set A_Account_Number_Acct	  */
	public void setA_Account_Number_Acct (int A_Account_Number_Acct);

	/** Get A_Account_Number_Acct	  */
	public int getA_Account_Number_Acct();

	public I_C_ValidCombination getA_Account_Number_A() throws RuntimeException;

    /** Column name A_Accumulated_Depr */
    public static final String COLUMNNAME_A_Accumulated_Depr = "A_Accumulated_Depr";

	/** Set Accumulated Depreciation	  */
	public void setA_Accumulated_Depr (BigDecimal A_Accumulated_Depr);

	/** Get Accumulated Depreciation	  */
	public BigDecimal getA_Accumulated_Depr();

    /** Column name A_Accumulated_Depr_Delta */
    public static final String COLUMNNAME_A_Accumulated_Depr_Delta = "A_Accumulated_Depr_Delta";

	/** Set Accumulated Depreciation (delta)	  */
	public void setA_Accumulated_Depr_Delta (BigDecimal A_Accumulated_Depr_Delta);

	/** Get Accumulated Depreciation (delta)	  */
	public BigDecimal getA_Accumulated_Depr_Delta();

    /** Column name A_Accumulated_Depr_F */
    public static final String COLUMNNAME_A_Accumulated_Depr_F = "A_Accumulated_Depr_F";

	/** Set Accumulated Depreciation (fiscal)	  */
	public void setA_Accumulated_Depr_F (BigDecimal A_Accumulated_Depr_F);

	/** Get Accumulated Depreciation (fiscal)	  */
	public BigDecimal getA_Accumulated_Depr_F();

    /** Column name A_Accumulated_Depr_F_Delta */
    public static final String COLUMNNAME_A_Accumulated_Depr_F_Delta = "A_Accumulated_Depr_F_Delta";

	/** Set Accumulated Depreciation - fiscal (delta)	  */
	public void setA_Accumulated_Depr_F_Delta (BigDecimal A_Accumulated_Depr_F_Delta);

	/** Get Accumulated Depreciation - fiscal (delta)	  */
	public BigDecimal getA_Accumulated_Depr_F_Delta();

    /** Column name A_Asset_Addition_ID */
    public static final String COLUMNNAME_A_Asset_Addition_ID = "A_Asset_Addition_ID";

	/** Set Asset Addition	  */
	public void setA_Asset_Addition_ID (int A_Asset_Addition_ID);

	/** Get Asset Addition	  */
	public int getA_Asset_Addition_ID();

	public org.compiere.model.I_A_Asset_Addition getA_Asset_Addition() throws RuntimeException;

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

    /** Column name A_Asset_Cost_Delta */
    public static final String COLUMNNAME_A_Asset_Cost_Delta = "A_Asset_Cost_Delta";

	/** Set Delta Asset Cost	  */
	public void setA_Asset_Cost_Delta (BigDecimal A_Asset_Cost_Delta);

	/** Get Delta Asset Cost	  */
	public BigDecimal getA_Asset_Cost_Delta();

    /** Column name A_Asset_Disposed_ID */
    public static final String COLUMNNAME_A_Asset_Disposed_ID = "A_Asset_Disposed_ID";

	/** Set Asset Disposed	  */
	public void setA_Asset_Disposed_ID (int A_Asset_Disposed_ID);

	/** Get Asset Disposed	  */
	public int getA_Asset_Disposed_ID();

	public org.compiere.model.I_A_Asset_Disposed getA_Asset_Disposed() throws RuntimeException;

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

    /** Column name A_Depreciation_Entry_ID */
    public static final String COLUMNNAME_A_Depreciation_Entry_ID = "A_Depreciation_Entry_ID";

	/** Set Depreciation Entry	  */
	public void setA_Depreciation_Entry_ID (int A_Depreciation_Entry_ID);

	/** Get Depreciation Entry	  */
	public int getA_Depreciation_Entry_ID();

	public org.compiere.model.I_A_Depreciation_Entry getA_Depreciation_Entry() throws RuntimeException;

    /** Column name A_Depreciation_Exp_ID */
    public static final String COLUMNNAME_A_Depreciation_Exp_ID = "A_Depreciation_Exp_ID";

	/** Set A_Depreciation_Exp_ID	  */
	public void setA_Depreciation_Exp_ID (int A_Depreciation_Exp_ID);

	/** Get A_Depreciation_Exp_ID	  */
	public int getA_Depreciation_Exp_ID();

    /** Column name A_Entry_Type */
    public static final String COLUMNNAME_A_Entry_Type = "A_Entry_Type";

	/** Set Entry Type	  */
	public void setA_Entry_Type (String A_Entry_Type);

	/** Get Entry Type	  */
	public String getA_Entry_Type();

    /** Column name A_Period */
    public static final String COLUMNNAME_A_Period = "A_Period";

	/** Set Asset Period	  */
	public void setA_Period (int A_Period);

	/** Get Asset Period	  */
	public int getA_Period();

    /** Column name CR_Account_ID */
    public static final String COLUMNNAME_CR_Account_ID = "CR_Account_ID";

	/** Set Account (credit).
	  * Account used
	  */
	public void setCR_Account_ID (int CR_Account_ID);

	/** Get Account (credit).
	  * Account used
	  */
	public int getCR_Account_ID();

	public I_C_ValidCombination getCR_Account() throws RuntimeException;

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

    /** Column name DR_Account_ID */
    public static final String COLUMNNAME_DR_Account_ID = "DR_Account_ID";

	/** Set Account (debit).
	  * Account used
	  */
	public void setDR_Account_ID (int DR_Account_ID);

	/** Get Account (debit).
	  * Account used
	  */
	public int getDR_Account_ID();

	public I_C_ValidCombination getDR_Account() throws RuntimeException;

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

    /** Column name Expense */
    public static final String COLUMNNAME_Expense = "Expense";

	/** Set Expense	  */
	public void setExpense (BigDecimal Expense);

	/** Get Expense	  */
	public BigDecimal getExpense();

    /** Column name Expense_F */
    public static final String COLUMNNAME_Expense_F = "Expense_F";

	/** Set Expense (fiscal)	  */
	public void setExpense_F (BigDecimal Expense_F);

	/** Get Expense (fiscal)	  */
	public BigDecimal getExpense_F();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

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
}
