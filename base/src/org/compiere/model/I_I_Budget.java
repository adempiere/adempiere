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

/** Generated Interface for I_Budget
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1
 */
public interface I_I_Budget 
{

    /** TableName=I_Budget */
    public static final String Table_Name = "I_Budget";

    /** AD_Table_ID=53618 */
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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

	public org.compiere.model.I_AD_Org getAD_OrgTrx() throws RuntimeException;

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

    /** Column name AccountValue */
    public static final String COLUMNNAME_AccountValue = "AccountValue";

	/** Set Account Key.
	  * Key of Account Element
	  */
	public void setAccountValue (String AccountValue);

	/** Get Account Key.
	  * Key of Account Element
	  */
	public String getAccountValue();

    /** Column name Account_ID */
    public static final String COLUMNNAME_Account_ID = "Account_ID";

	/** Set Account.
	  * Account used
	  */
	public void setAccount_ID (int Account_ID);

	/** Get Account.
	  * Account used
	  */
	public int getAccount_ID();

	public org.compiere.model.I_C_ElementValue getAccount() throws RuntimeException;

    /** Column name AcctSchemaName */
    public static final String COLUMNNAME_AcctSchemaName = "AcctSchemaName";

	/** Set Account Schema Name.
	  * Name of the Accounting Schema
	  */
	public void setAcctSchemaName (String AcctSchemaName);

	/** Get Account Schema Name.
	  * Name of the Accounting Schema
	  */
	public String getAcctSchemaName();

    /** Column name ActivityValue */
    public static final String COLUMNNAME_ActivityValue = "ActivityValue";

	/** Set Activity Value	  */
	public void setActivityValue (String ActivityValue);

	/** Get Activity Value	  */
	public String getActivityValue();

    /** Column name AssetValue */
    public static final String COLUMNNAME_AssetValue = "AssetValue";

	/** Set Asset Value	  */
	public void setAssetValue (String AssetValue);

	/** Get Asset Value	  */
	public String getAssetValue();

    /** Column name BPartnerValue */
    public static final String COLUMNNAME_BPartnerValue = "BPartnerValue";

	/** Set Business Partner Key.
	  * Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue);

	/** Get Business Partner Key.
	  * Key of the Business Partner
	  */
	public String getBPartnerValue();

    /** Column name BatchDocumentNo */
    public static final String COLUMNNAME_BatchDocumentNo = "BatchDocumentNo";

	/** Set Batch Document No.
	  * Document Number of the Batch
	  */
	public void setBatchDocumentNo (String BatchDocumentNo);

	/** Get Batch Document No.
	  * Document Number of the Batch
	  */
	public String getBatchDocumentNo();

    /** Column name BudgetCode */
    public static final String COLUMNNAME_BudgetCode = "BudgetCode";

	/** Set Budget Code 	  */
	public void setBudgetCode (String BudgetCode);

	/** Get Budget Code 	  */
	public String getBudgetCode();

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

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_LocFrom_ID */
    public static final String COLUMNNAME_C_LocFrom_ID = "C_LocFrom_ID";

	/** Set Location From.
	  * Location that inventory was moved from
	  */
	public void setC_LocFrom_ID (int C_LocFrom_ID);

	/** Get Location From.
	  * Location that inventory was moved from
	  */
	public int getC_LocFrom_ID();

	public org.compiere.model.I_C_Location getC_LocFrom() throws RuntimeException;

    /** Column name C_LocTo_ID */
    public static final String COLUMNNAME_C_LocTo_ID = "C_LocTo_ID";

	/** Set Location To.
	  * Location that inventory was moved to
	  */
	public void setC_LocTo_ID (int C_LocTo_ID);

	/** Get Location To.
	  * Location that inventory was moved to
	  */
	public int getC_LocTo_ID();

	public org.compiere.model.I_C_Location getC_LocTo() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_SalesRegion_ID */
    public static final String COLUMNNAME_C_SalesRegion_ID = "C_SalesRegion_ID";

	/** Set Sales Region.
	  * Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID);

	/** Get Sales Region.
	  * Sales coverage region
	  */
	public int getC_SalesRegion_ID();

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException;

    /** Column name C_SubAcct_ID */
    public static final String COLUMNNAME_C_SubAcct_ID = "C_SubAcct_ID";

	/** Set Sub Account.
	  * Sub account for Element Value
	  */
	public void setC_SubAcct_ID (int C_SubAcct_ID);

	/** Get Sub Account.
	  * Sub account for Element Value
	  */
	public int getC_SubAcct_ID();

	public org.compiere.model.I_C_SubAcct getC_SubAcct() throws RuntimeException;

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

	public org.compiere.model.I_C_ValidCombination getC_ValidCombination() throws RuntimeException;

    /** Column name CampaignValue */
    public static final String COLUMNNAME_CampaignValue = "CampaignValue";

	/** Set Campaign Value	  */
	public void setCampaignValue (String CampaignValue);

	/** Get Campaign Value	  */
	public String getCampaignValue();

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

    /** Column name GL_Budget_ID */
    public static final String COLUMNNAME_GL_Budget_ID = "GL_Budget_ID";

	/** Set Budget.
	  * General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID);

	/** Get Budget.
	  * General Ledger Budget
	  */
	public int getGL_Budget_ID();

	public org.compiere.model.I_GL_Budget getGL_Budget() throws RuntimeException;

    /** Column name GL_JournalBatch_ID */
    public static final String COLUMNNAME_GL_JournalBatch_ID = "GL_JournalBatch_ID";

	/** Set Journal Batch.
	  * General Ledger Journal Batch
	  */
	public void setGL_JournalBatch_ID (int GL_JournalBatch_ID);

	/** Get Journal Batch.
	  * General Ledger Journal Batch
	  */
	public int getGL_JournalBatch_ID();

	public org.compiere.model.I_GL_JournalBatch getGL_JournalBatch() throws RuntimeException;

    /** Column name GL_JournalLine_ID */
    public static final String COLUMNNAME_GL_JournalLine_ID = "GL_JournalLine_ID";

	/** Set Journal Line.
	  * General Ledger Journal Line
	  */
	public void setGL_JournalLine_ID (int GL_JournalLine_ID);

	/** Get Journal Line.
	  * General Ledger Journal Line
	  */
	public int getGL_JournalLine_ID();

	public org.compiere.model.I_GL_JournalLine getGL_JournalLine() throws RuntimeException;

    /** Column name GL_Journal_ID */
    public static final String COLUMNNAME_GL_Journal_ID = "GL_Journal_ID";

	/** Set Journal.
	  * General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID);

	/** Get Journal.
	  * General Ledger Journal
	  */
	public int getGL_Journal_ID();

	public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException;

    /** Column name I_Budget_ID */
    public static final String COLUMNNAME_I_Budget_ID = "I_Budget_ID";

	/** Set I_Budget_ID	  */
	public void setI_Budget_ID (int I_Budget_ID);

	/** Get I_Budget_ID	  */
	public int getI_Budget_ID();

    /** Column name I_ErrorMsg */
    public static final String COLUMNNAME_I_ErrorMsg = "I_ErrorMsg";

	/** Set Import Error Message.
	  * Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg);

	/** Get Import Error Message.
	  * Messages generated from import process
	  */
	public String getI_ErrorMsg();

    /** Column name I_IsImported */
    public static final String COLUMNNAME_I_IsImported = "I_IsImported";

	/** Set Imported.
	  * Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported);

	/** Get Imported.
	  * Has this import been processed
	  */
	public boolean isI_IsImported();

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

    /** Column name Jnl_Line_Description */
    public static final String COLUMNNAME_Jnl_Line_Description = "Jnl_Line_Description";

	/** Set Journal Line Description	  */
	public void setJnl_Line_Description (String Jnl_Line_Description);

	/** Get Journal Line Description	  */
	public String getJnl_Line_Description();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name Month_0_Amt */
    public static final String COLUMNNAME_Month_0_Amt = "Month_0_Amt";

	/** Set Month_0_Amt	  */
	public void setMonth_0_Amt (BigDecimal Month_0_Amt);

	/** Get Month_0_Amt	  */
	public BigDecimal getMonth_0_Amt();

    /** Column name Month_0_Qty */
    public static final String COLUMNNAME_Month_0_Qty = "Month_0_Qty";

	/** Set Month_0_Qty	  */
	public void setMonth_0_Qty (BigDecimal Month_0_Qty);

	/** Get Month_0_Qty	  */
	public BigDecimal getMonth_0_Qty();

    /** Column name Month_10_Amt */
    public static final String COLUMNNAME_Month_10_Amt = "Month_10_Amt";

	/** Set Month_10_Amt	  */
	public void setMonth_10_Amt (BigDecimal Month_10_Amt);

	/** Get Month_10_Amt	  */
	public BigDecimal getMonth_10_Amt();

    /** Column name Month_10_Qty */
    public static final String COLUMNNAME_Month_10_Qty = "Month_10_Qty";

	/** Set Month_10_Qty	  */
	public void setMonth_10_Qty (BigDecimal Month_10_Qty);

	/** Get Month_10_Qty	  */
	public BigDecimal getMonth_10_Qty();

    /** Column name Month_11_Amt */
    public static final String COLUMNNAME_Month_11_Amt = "Month_11_Amt";

	/** Set Month_11_Amt	  */
	public void setMonth_11_Amt (BigDecimal Month_11_Amt);

	/** Get Month_11_Amt	  */
	public BigDecimal getMonth_11_Amt();

    /** Column name Month_11_Qty */
    public static final String COLUMNNAME_Month_11_Qty = "Month_11_Qty";

	/** Set Month_11_Qty	  */
	public void setMonth_11_Qty (BigDecimal Month_11_Qty);

	/** Get Month_11_Qty	  */
	public BigDecimal getMonth_11_Qty();

    /** Column name Month_1_Amt */
    public static final String COLUMNNAME_Month_1_Amt = "Month_1_Amt";

	/** Set Month_1_Amt	  */
	public void setMonth_1_Amt (BigDecimal Month_1_Amt);

	/** Get Month_1_Amt	  */
	public BigDecimal getMonth_1_Amt();

    /** Column name Month_1_Qty */
    public static final String COLUMNNAME_Month_1_Qty = "Month_1_Qty";

	/** Set Month_1_Qty	  */
	public void setMonth_1_Qty (BigDecimal Month_1_Qty);

	/** Get Month_1_Qty	  */
	public BigDecimal getMonth_1_Qty();

    /** Column name Month_2_Amt */
    public static final String COLUMNNAME_Month_2_Amt = "Month_2_Amt";

	/** Set Month_2_Amt	  */
	public void setMonth_2_Amt (BigDecimal Month_2_Amt);

	/** Get Month_2_Amt	  */
	public BigDecimal getMonth_2_Amt();

    /** Column name Month_2_Qty */
    public static final String COLUMNNAME_Month_2_Qty = "Month_2_Qty";

	/** Set Month_2_Qty	  */
	public void setMonth_2_Qty (BigDecimal Month_2_Qty);

	/** Get Month_2_Qty	  */
	public BigDecimal getMonth_2_Qty();

    /** Column name Month_3_Amt */
    public static final String COLUMNNAME_Month_3_Amt = "Month_3_Amt";

	/** Set Month_3_Amt	  */
	public void setMonth_3_Amt (BigDecimal Month_3_Amt);

	/** Get Month_3_Amt	  */
	public BigDecimal getMonth_3_Amt();

    /** Column name Month_3_Qty */
    public static final String COLUMNNAME_Month_3_Qty = "Month_3_Qty";

	/** Set Month_3_Qty	  */
	public void setMonth_3_Qty (BigDecimal Month_3_Qty);

	/** Get Month_3_Qty	  */
	public BigDecimal getMonth_3_Qty();

    /** Column name Month_4_Amt */
    public static final String COLUMNNAME_Month_4_Amt = "Month_4_Amt";

	/** Set Month_4_Amt	  */
	public void setMonth_4_Amt (BigDecimal Month_4_Amt);

	/** Get Month_4_Amt	  */
	public BigDecimal getMonth_4_Amt();

    /** Column name Month_4_Qty */
    public static final String COLUMNNAME_Month_4_Qty = "Month_4_Qty";

	/** Set Month_4_Qty	  */
	public void setMonth_4_Qty (BigDecimal Month_4_Qty);

	/** Get Month_4_Qty	  */
	public BigDecimal getMonth_4_Qty();

    /** Column name Month_5_Amt */
    public static final String COLUMNNAME_Month_5_Amt = "Month_5_Amt";

	/** Set Month_5_Amt	  */
	public void setMonth_5_Amt (BigDecimal Month_5_Amt);

	/** Get Month_5_Amt	  */
	public BigDecimal getMonth_5_Amt();

    /** Column name Month_5_Qty */
    public static final String COLUMNNAME_Month_5_Qty = "Month_5_Qty";

	/** Set Month_5_Qty	  */
	public void setMonth_5_Qty (BigDecimal Month_5_Qty);

	/** Get Month_5_Qty	  */
	public BigDecimal getMonth_5_Qty();

    /** Column name Month_6_Amt */
    public static final String COLUMNNAME_Month_6_Amt = "Month_6_Amt";

	/** Set Month_6_Amt	  */
	public void setMonth_6_Amt (BigDecimal Month_6_Amt);

	/** Get Month_6_Amt	  */
	public BigDecimal getMonth_6_Amt();

    /** Column name Month_6_Qty */
    public static final String COLUMNNAME_Month_6_Qty = "Month_6_Qty";

	/** Set Month_6_Qty	  */
	public void setMonth_6_Qty (BigDecimal Month_6_Qty);

	/** Get Month_6_Qty	  */
	public BigDecimal getMonth_6_Qty();

    /** Column name Month_7_Amt */
    public static final String COLUMNNAME_Month_7_Amt = "Month_7_Amt";

	/** Set Month_7_Amt	  */
	public void setMonth_7_Amt (BigDecimal Month_7_Amt);

	/** Get Month_7_Amt	  */
	public BigDecimal getMonth_7_Amt();

    /** Column name Month_7_Qty */
    public static final String COLUMNNAME_Month_7_Qty = "Month_7_Qty";

	/** Set Month_7_Qty	  */
	public void setMonth_7_Qty (BigDecimal Month_7_Qty);

	/** Get Month_7_Qty	  */
	public BigDecimal getMonth_7_Qty();

    /** Column name Month_8_Amt */
    public static final String COLUMNNAME_Month_8_Amt = "Month_8_Amt";

	/** Set Month_8_Amt	  */
	public void setMonth_8_Amt (BigDecimal Month_8_Amt);

	/** Get Month_8_Amt	  */
	public BigDecimal getMonth_8_Amt();

    /** Column name Month_8_Qty */
    public static final String COLUMNNAME_Month_8_Qty = "Month_8_Qty";

	/** Set Month_8_Qty	  */
	public void setMonth_8_Qty (BigDecimal Month_8_Qty);

	/** Get Month_8_Qty	  */
	public BigDecimal getMonth_8_Qty();

    /** Column name Month_9_Amt */
    public static final String COLUMNNAME_Month_9_Amt = "Month_9_Amt";

	/** Set Month_9_Amt	  */
	public void setMonth_9_Amt (BigDecimal Month_9_Amt);

	/** Get Month_9_Amt	  */
	public BigDecimal getMonth_9_Amt();

    /** Column name Month_9_Qty */
    public static final String COLUMNNAME_Month_9_Qty = "Month_9_Qty";

	/** Set Month_9_Qty	  */
	public void setMonth_9_Qty (BigDecimal Month_9_Qty);

	/** Get Month_9_Qty	  */
	public BigDecimal getMonth_9_Qty();

    /** Column name OrgTrxValue */
    public static final String COLUMNNAME_OrgTrxValue = "OrgTrxValue";

	/** Set Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public void setOrgTrxValue (String OrgTrxValue);

	/** Get Trx Org Key.
	  * Key of the Transaction Organization
	  */
	public String getOrgTrxValue();

    /** Column name OrgValue */
    public static final String COLUMNNAME_OrgValue = "OrgValue";

	/** Set Org Key.
	  * Key of the Organization
	  */
	public void setOrgValue (String OrgValue);

	/** Get Org Key.
	  * Key of the Organization
	  */
	public String getOrgValue();

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

    /** Column name ProductValue */
    public static final String COLUMNNAME_ProductValue = "ProductValue";

	/** Set Product Key.
	  * Key of the Product
	  */
	public void setProductValue (String ProductValue);

	/** Get Product Key.
	  * Key of the Product
	  */
	public String getProductValue();

    /** Column name ProjectValue */
    public static final String COLUMNNAME_ProjectValue = "ProjectValue";

	/** Set Project Key.
	  * Key of the Project
	  */
	public void setProjectValue (String ProjectValue);

	/** Get Project Key.
	  * Key of the Project
	  */
	public String getProjectValue();

    /** Column name SalesRegionValue */
    public static final String COLUMNNAME_SalesRegionValue = "SalesRegionValue";

	/** Set Sales Region Value	  */
	public void setSalesRegionValue (String SalesRegionValue);

	/** Get Sales Region Value	  */
	public String getSalesRegionValue();

    /** Column name SubAcctValue */
    public static final String COLUMNNAME_SubAcctValue = "SubAcctValue";

	/** Set Sub Account Value.
	  * Sub account Value
	  */
	public void setSubAcctValue (String SubAcctValue);

	/** Get Sub Account Value.
	  * Sub account Value
	  */
	public String getSubAcctValue();

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

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException;

    /** Column name User3_ID */
    public static final String COLUMNNAME_User3_ID = "User3_ID";

	/** Set User List 3.
	  * User defined list element #3
	  */
	public void setUser3_ID (int User3_ID);

	/** Get User List 3.
	  * User defined list element #3
	  */
	public int getUser3_ID();

	public org.compiere.model.I_C_ElementValue getUser3() throws RuntimeException;

    /** Column name User4_ID */
    public static final String COLUMNNAME_User4_ID = "User4_ID";

	/** Set User List 4.
	  * User defined list element #4
	  */
	public void setUser4_ID (int User4_ID);

	/** Get User List 4.
	  * User defined list element #4
	  */
	public int getUser4_ID();

	public org.compiere.model.I_C_ElementValue getUser4() throws RuntimeException;

    /** Column name UserElement1_ID */
    public static final String COLUMNNAME_UserElement1_ID = "UserElement1_ID";

	/** Set User Element 1.
	  * User defined accounting Element
	  */
	public void setUserElement1_ID (int UserElement1_ID);

	/** Get User Element 1.
	  * User defined accounting Element
	  */
	public int getUserElement1_ID();

    /** Column name UserElement2_ID */
    public static final String COLUMNNAME_UserElement2_ID = "UserElement2_ID";

	/** Set User Element 2.
	  * User defined accounting Element
	  */
	public void setUserElement2_ID (int UserElement2_ID);

	/** Get User Element 2.
	  * User defined accounting Element
	  */
	public int getUserElement2_ID();

    /** Column name UserElementValue1 */
    public static final String COLUMNNAME_UserElementValue1 = "UserElementValue1";

	/** Set User Element Value 1.
	  * User Element Value 1 defined accounting Element
	  */
	public void setUserElementValue1 (String UserElementValue1);

	/** Get User Element Value 1.
	  * User Element Value 1 defined accounting Element
	  */
	public String getUserElementValue1();

    /** Column name UserElementValue2 */
    public static final String COLUMNNAME_UserElementValue2 = "UserElementValue2";

	/** Set User Element Value 2.
	  * User Element Value 2 defined accounting Element
	  */
	public void setUserElementValue2 (String UserElementValue2);

	/** Get User Element Value 2.
	  * User Element Value 2 defined accounting Element
	  */
	public String getUserElementValue2();

    /** Column name UserValue1 */
    public static final String COLUMNNAME_UserValue1 = "UserValue1";

	/** Set User List Value 1.
	  * User value defined list element #1
	  */
	public void setUserValue1 (String UserValue1);

	/** Get User List Value 1.
	  * User value defined list element #1
	  */
	public String getUserValue1();

    /** Column name UserValue2 */
    public static final String COLUMNNAME_UserValue2 = "UserValue2";

	/** Set User List Value 2.
	  * User value defined list element #2
	  */
	public void setUserValue2 (String UserValue2);

	/** Get User List Value 2.
	  * User value defined list element #2
	  */
	public String getUserValue2();

    /** Column name UserValue3 */
    public static final String COLUMNNAME_UserValue3 = "UserValue3";

	/** Set User List Value 3.
	  * User value defined list element #3
	  */
	public void setUserValue3 (String UserValue3);

	/** Get User List Value 3.
	  * User value defined list element #3
	  */
	public String getUserValue3();

    /** Column name UserValue4 */
    public static final String COLUMNNAME_UserValue4 = "UserValue4";

	/** Set User List Value 4.
	  * User value defined list element #3
	  */
	public void setUserValue4 (String UserValue4);

	/** Get User List Value 4.
	  * User value defined list element #3
	  */
	public String getUserValue4();
}
