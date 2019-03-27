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
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for I_Budget
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_I_Budget extends PO implements I_I_Budget, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_I_Budget (Properties ctx, int I_Budget_ID, String trxName)
    {
      super (ctx, I_Budget_ID, trxName);
      /** if (I_Budget_ID == 0)
        {
			setI_Budget_ID (0);
        } */
    }

    /** Load Constructor */
    public X_I_Budget (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_I_Budget[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Org getAD_OrgTrx() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Org)MTable.get(getCtx(), org.compiere.model.I_AD_Org.Table_Name)
			.getPO(getAD_OrgTrx_ID(), get_TrxName());	}

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_A_Asset getA_Asset() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset)MTable.get(getCtx(), org.compiere.model.I_A_Asset.Table_Name)
			.getPO(getA_Asset_ID(), get_TrxName());	}

	/** Set Fixed Asset.
		@param A_Asset_ID 
		Fixed Asset used internally or by customers
	  */
	public void setA_Asset_ID (int A_Asset_ID)
	{
		if (A_Asset_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_ID, Integer.valueOf(A_Asset_ID));
	}

	/** Get Fixed Asset.
		@return Fixed Asset used internally or by customers
	  */
	public int getA_Asset_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Key.
		@param AccountValue 
		Key of Account Element
	  */
	public void setAccountValue (String AccountValue)
	{
		set_Value (COLUMNNAME_AccountValue, AccountValue);
	}

	/** Get Account Key.
		@return Key of Account Element
	  */
	public String getAccountValue () 
	{
		return (String)get_Value(COLUMNNAME_AccountValue);
	}

	public org.compiere.model.I_C_ElementValue getAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getAccount_ID(), get_TrxName());	}

	/** Set Account.
		@param Account_ID 
		Account used
	  */
	public void setAccount_ID (int Account_ID)
	{
		if (Account_ID < 1) 
			set_Value (COLUMNNAME_Account_ID, null);
		else 
			set_Value (COLUMNNAME_Account_ID, Integer.valueOf(Account_ID));
	}

	/** Get Account.
		@return Account used
	  */
	public int getAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Schema Name.
		@param AcctSchemaName 
		Name of the Accounting Schema
	  */
	public void setAcctSchemaName (String AcctSchemaName)
	{
		set_Value (COLUMNNAME_AcctSchemaName, AcctSchemaName);
	}

	/** Get Account Schema Name.
		@return Name of the Accounting Schema
	  */
	public String getAcctSchemaName () 
	{
		return (String)get_Value(COLUMNNAME_AcctSchemaName);
	}

	/** Set Activity Value.
		@param ActivityValue Activity Value	  */
	public void setActivityValue (String ActivityValue)
	{
		set_Value (COLUMNNAME_ActivityValue, ActivityValue);
	}

	/** Get Activity Value.
		@return Activity Value	  */
	public String getActivityValue () 
	{
		return (String)get_Value(COLUMNNAME_ActivityValue);
	}

	/** Set Asset Value.
		@param AssetValue Asset Value	  */
	public void setAssetValue (String AssetValue)
	{
		set_Value (COLUMNNAME_AssetValue, AssetValue);
	}

	/** Get Asset Value.
		@return Asset Value	  */
	public String getAssetValue () 
	{
		return (String)get_Value(COLUMNNAME_AssetValue);
	}

	/** Set Business Partner Key.
		@param BPartnerValue 
		Key of the Business Partner
	  */
	public void setBPartnerValue (String BPartnerValue)
	{
		set_Value (COLUMNNAME_BPartnerValue, BPartnerValue);
	}

	/** Get Business Partner Key.
		@return Key of the Business Partner
	  */
	public String getBPartnerValue () 
	{
		return (String)get_Value(COLUMNNAME_BPartnerValue);
	}

	/** Set Batch Document No.
		@param BatchDocumentNo 
		Document Number of the Batch
	  */
	public void setBatchDocumentNo (String BatchDocumentNo)
	{
		set_Value (COLUMNNAME_BatchDocumentNo, BatchDocumentNo);
	}

	/** Get Batch Document No.
		@return Document Number of the Batch
	  */
	public String getBatchDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_BatchDocumentNo);
	}

	/** Set Budget Code .
		@param BudgetCode Budget Code 	  */
	public void setBudgetCode (String BudgetCode)
	{
		set_Value (COLUMNNAME_BudgetCode, BudgetCode);
	}

	/** Get Budget Code .
		@return Budget Code 	  */
	public String getBudgetCode () 
	{
		return (String)get_Value(COLUMNNAME_BudgetCode);
	}

	public org.compiere.model.I_C_AcctSchema getC_AcctSchema() throws RuntimeException
    {
		return (org.compiere.model.I_C_AcctSchema)MTable.get(getCtx(), org.compiere.model.I_C_AcctSchema.Table_Name)
			.getPO(getC_AcctSchema_ID(), get_TrxName());	}

	/** Set Accounting Schema.
		@param C_AcctSchema_ID 
		Rules for accounting
	  */
	public void setC_AcctSchema_ID (int C_AcctSchema_ID)
	{
		if (C_AcctSchema_ID < 1) 
			set_Value (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_Value (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
	}

	/** Get Accounting Schema.
		@return Rules for accounting
	  */
	public int getC_AcctSchema_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_AcctSchema_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Location getC_LocFrom() throws RuntimeException
    {
		return (org.compiere.model.I_C_Location)MTable.get(getCtx(), org.compiere.model.I_C_Location.Table_Name)
			.getPO(getC_LocFrom_ID(), get_TrxName());	}

	/** Set Location From.
		@param C_LocFrom_ID 
		Location that inventory was moved from
	  */
	public void setC_LocFrom_ID (int C_LocFrom_ID)
	{
		if (C_LocFrom_ID < 1) 
			set_Value (COLUMNNAME_C_LocFrom_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocFrom_ID, Integer.valueOf(C_LocFrom_ID));
	}

	/** Get Location From.
		@return Location that inventory was moved from
	  */
	public int getC_LocFrom_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocFrom_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Location getC_LocTo() throws RuntimeException
    {
		return (org.compiere.model.I_C_Location)MTable.get(getCtx(), org.compiere.model.I_C_Location.Table_Name)
			.getPO(getC_LocTo_ID(), get_TrxName());	}

	/** Set Location To.
		@param C_LocTo_ID 
		Location that inventory was moved to
	  */
	public void setC_LocTo_ID (int C_LocTo_ID)
	{
		if (C_LocTo_ID < 1) 
			set_Value (COLUMNNAME_C_LocTo_ID, null);
		else 
			set_Value (COLUMNNAME_C_LocTo_ID, Integer.valueOf(C_LocTo_ID));
	}

	/** Get Location To.
		@return Location that inventory was moved to
	  */
	public int getC_LocTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_LocTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException
    {
		return (org.compiere.model.I_C_SalesRegion)MTable.get(getCtx(), org.compiere.model.I_C_SalesRegion.Table_Name)
			.getPO(getC_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_SubAcct getC_SubAcct() throws RuntimeException
    {
		return (org.compiere.model.I_C_SubAcct)MTable.get(getCtx(), org.compiere.model.I_C_SubAcct.Table_Name)
			.getPO(getC_SubAcct_ID(), get_TrxName());	}

	/** Set Sub Account.
		@param C_SubAcct_ID 
		Sub account for Element Value
	  */
	public void setC_SubAcct_ID (int C_SubAcct_ID)
	{
		if (C_SubAcct_ID < 1) 
			set_Value (COLUMNNAME_C_SubAcct_ID, null);
		else 
			set_Value (COLUMNNAME_C_SubAcct_ID, Integer.valueOf(C_SubAcct_ID));
	}

	/** Get Sub Account.
		@return Sub account for Element Value
	  */
	public int getC_SubAcct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SubAcct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ValidCombination getC_ValidCombination() throws RuntimeException
    {
		return (org.compiere.model.I_C_ValidCombination)MTable.get(getCtx(), org.compiere.model.I_C_ValidCombination.Table_Name)
			.getPO(getC_ValidCombination_ID(), get_TrxName());	}

	/** Set Combination.
		@param C_ValidCombination_ID 
		Valid Account Combination
	  */
	public void setC_ValidCombination_ID (int C_ValidCombination_ID)
	{
		if (C_ValidCombination_ID < 1) 
			set_Value (COLUMNNAME_C_ValidCombination_ID, null);
		else 
			set_Value (COLUMNNAME_C_ValidCombination_ID, Integer.valueOf(C_ValidCombination_ID));
	}

	/** Get Combination.
		@return Valid Account Combination
	  */
	public int getC_ValidCombination_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ValidCombination_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Campaign Value.
		@param CampaignValue Campaign Value	  */
	public void setCampaignValue (String CampaignValue)
	{
		set_Value (COLUMNNAME_CampaignValue, CampaignValue);
	}

	/** Get Campaign Value.
		@return Campaign Value	  */
	public String getCampaignValue () 
	{
		return (String)get_Value(COLUMNNAME_CampaignValue);
	}

	public org.compiere.model.I_GL_Budget getGL_Budget() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Budget)MTable.get(getCtx(), org.compiere.model.I_GL_Budget.Table_Name)
			.getPO(getGL_Budget_ID(), get_TrxName());	}

	/** Set Budget.
		@param GL_Budget_ID 
		General Ledger Budget
	  */
	public void setGL_Budget_ID (int GL_Budget_ID)
	{
		if (GL_Budget_ID < 1) 
			set_Value (COLUMNNAME_GL_Budget_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Budget_ID, Integer.valueOf(GL_Budget_ID));
	}

	/** Get Budget.
		@return General Ledger Budget
	  */
	public int getGL_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_GL_JournalBatch getGL_JournalBatch() throws RuntimeException
    {
		return (org.compiere.model.I_GL_JournalBatch)MTable.get(getCtx(), org.compiere.model.I_GL_JournalBatch.Table_Name)
			.getPO(getGL_JournalBatch_ID(), get_TrxName());	}

	/** Set Journal Batch.
		@param GL_JournalBatch_ID 
		General Ledger Journal Batch
	  */
	public void setGL_JournalBatch_ID (int GL_JournalBatch_ID)
	{
		if (GL_JournalBatch_ID < 1) 
			set_Value (COLUMNNAME_GL_JournalBatch_ID, null);
		else 
			set_Value (COLUMNNAME_GL_JournalBatch_ID, Integer.valueOf(GL_JournalBatch_ID));
	}

	/** Get Journal Batch.
		@return General Ledger Journal Batch
	  */
	public int getGL_JournalBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_JournalBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_GL_JournalLine getGL_JournalLine() throws RuntimeException
    {
		return (org.compiere.model.I_GL_JournalLine)MTable.get(getCtx(), org.compiere.model.I_GL_JournalLine.Table_Name)
			.getPO(getGL_JournalLine_ID(), get_TrxName());	}

	/** Set Journal Line.
		@param GL_JournalLine_ID 
		General Ledger Journal Line
	  */
	public void setGL_JournalLine_ID (int GL_JournalLine_ID)
	{
		if (GL_JournalLine_ID < 1) 
			set_Value (COLUMNNAME_GL_JournalLine_ID, null);
		else 
			set_Value (COLUMNNAME_GL_JournalLine_ID, Integer.valueOf(GL_JournalLine_ID));
	}

	/** Get Journal Line.
		@return General Ledger Journal Line
	  */
	public int getGL_JournalLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_JournalLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Journal)MTable.get(getCtx(), org.compiere.model.I_GL_Journal.Table_Name)
			.getPO(getGL_Journal_ID(), get_TrxName());	}

	/** Set Journal.
		@param GL_Journal_ID 
		General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID)
	{
		if (GL_Journal_ID < 1) 
			set_Value (COLUMNNAME_GL_Journal_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Journal_ID, Integer.valueOf(GL_Journal_ID));
	}

	/** Get Journal.
		@return General Ledger Journal
	  */
	public int getGL_Journal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Journal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set I_Budget_ID.
		@param I_Budget_ID I_Budget_ID	  */
	public void setI_Budget_ID (int I_Budget_ID)
	{
		if (I_Budget_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_I_Budget_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_I_Budget_ID, Integer.valueOf(I_Budget_ID));
	}

	/** Get I_Budget_ID.
		@return I_Budget_ID	  */
	public int getI_Budget_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_I_Budget_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getI_Budget_ID()));
    }

	/** Set Import Error Message.
		@param I_ErrorMsg 
		Messages generated from import process
	  */
	public void setI_ErrorMsg (String I_ErrorMsg)
	{
		set_Value (COLUMNNAME_I_ErrorMsg, I_ErrorMsg);
	}

	/** Get Import Error Message.
		@return Messages generated from import process
	  */
	public String getI_ErrorMsg () 
	{
		return (String)get_Value(COLUMNNAME_I_ErrorMsg);
	}

	/** Set Imported.
		@param I_IsImported 
		Has this import been processed
	  */
	public void setI_IsImported (boolean I_IsImported)
	{
		set_Value (COLUMNNAME_I_IsImported, Boolean.valueOf(I_IsImported));
	}

	/** Get Imported.
		@return Has this import been processed
	  */
	public boolean isI_IsImported () 
	{
		Object oo = get_Value(COLUMNNAME_I_IsImported);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Journal Line Description.
		@param Jnl_Line_Description Journal Line Description	  */
	public void setJnl_Line_Description (String Jnl_Line_Description)
	{
		set_Value (COLUMNNAME_Jnl_Line_Description, Jnl_Line_Description);
	}

	/** Get Journal Line Description.
		@return Journal Line Description	  */
	public String getJnl_Line_Description () 
	{
		return (String)get_Value(COLUMNNAME_Jnl_Line_Description);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Month_0_Amt.
		@param Month_0_Amt Month_0_Amt	  */
	public void setMonth_0_Amt (BigDecimal Month_0_Amt)
	{
		set_Value (COLUMNNAME_Month_0_Amt, Month_0_Amt);
	}

	/** Get Month_0_Amt.
		@return Month_0_Amt	  */
	public BigDecimal getMonth_0_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_0_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_0_Qty.
		@param Month_0_Qty Month_0_Qty	  */
	public void setMonth_0_Qty (BigDecimal Month_0_Qty)
	{
		set_Value (COLUMNNAME_Month_0_Qty, Month_0_Qty);
	}

	/** Get Month_0_Qty.
		@return Month_0_Qty	  */
	public BigDecimal getMonth_0_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_0_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_10_Amt.
		@param Month_10_Amt Month_10_Amt	  */
	public void setMonth_10_Amt (BigDecimal Month_10_Amt)
	{
		set_Value (COLUMNNAME_Month_10_Amt, Month_10_Amt);
	}

	/** Get Month_10_Amt.
		@return Month_10_Amt	  */
	public BigDecimal getMonth_10_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_10_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_10_Qty.
		@param Month_10_Qty Month_10_Qty	  */
	public void setMonth_10_Qty (BigDecimal Month_10_Qty)
	{
		set_Value (COLUMNNAME_Month_10_Qty, Month_10_Qty);
	}

	/** Get Month_10_Qty.
		@return Month_10_Qty	  */
	public BigDecimal getMonth_10_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_10_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_11_Amt.
		@param Month_11_Amt Month_11_Amt	  */
	public void setMonth_11_Amt (BigDecimal Month_11_Amt)
	{
		set_Value (COLUMNNAME_Month_11_Amt, Month_11_Amt);
	}

	/** Get Month_11_Amt.
		@return Month_11_Amt	  */
	public BigDecimal getMonth_11_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_11_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_11_Qty.
		@param Month_11_Qty Month_11_Qty	  */
	public void setMonth_11_Qty (BigDecimal Month_11_Qty)
	{
		set_Value (COLUMNNAME_Month_11_Qty, Month_11_Qty);
	}

	/** Get Month_11_Qty.
		@return Month_11_Qty	  */
	public BigDecimal getMonth_11_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_11_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_1_Amt.
		@param Month_1_Amt Month_1_Amt	  */
	public void setMonth_1_Amt (BigDecimal Month_1_Amt)
	{
		set_Value (COLUMNNAME_Month_1_Amt, Month_1_Amt);
	}

	/** Get Month_1_Amt.
		@return Month_1_Amt	  */
	public BigDecimal getMonth_1_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_1_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_1_Qty.
		@param Month_1_Qty Month_1_Qty	  */
	public void setMonth_1_Qty (BigDecimal Month_1_Qty)
	{
		set_Value (COLUMNNAME_Month_1_Qty, Month_1_Qty);
	}

	/** Get Month_1_Qty.
		@return Month_1_Qty	  */
	public BigDecimal getMonth_1_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_1_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_2_Amt.
		@param Month_2_Amt Month_2_Amt	  */
	public void setMonth_2_Amt (BigDecimal Month_2_Amt)
	{
		set_Value (COLUMNNAME_Month_2_Amt, Month_2_Amt);
	}

	/** Get Month_2_Amt.
		@return Month_2_Amt	  */
	public BigDecimal getMonth_2_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_2_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_2_Qty.
		@param Month_2_Qty Month_2_Qty	  */
	public void setMonth_2_Qty (BigDecimal Month_2_Qty)
	{
		set_Value (COLUMNNAME_Month_2_Qty, Month_2_Qty);
	}

	/** Get Month_2_Qty.
		@return Month_2_Qty	  */
	public BigDecimal getMonth_2_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_2_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_3_Amt.
		@param Month_3_Amt Month_3_Amt	  */
	public void setMonth_3_Amt (BigDecimal Month_3_Amt)
	{
		set_Value (COLUMNNAME_Month_3_Amt, Month_3_Amt);
	}

	/** Get Month_3_Amt.
		@return Month_3_Amt	  */
	public BigDecimal getMonth_3_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_3_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_3_Qty.
		@param Month_3_Qty Month_3_Qty	  */
	public void setMonth_3_Qty (BigDecimal Month_3_Qty)
	{
		set_Value (COLUMNNAME_Month_3_Qty, Month_3_Qty);
	}

	/** Get Month_3_Qty.
		@return Month_3_Qty	  */
	public BigDecimal getMonth_3_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_3_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_4_Amt.
		@param Month_4_Amt Month_4_Amt	  */
	public void setMonth_4_Amt (BigDecimal Month_4_Amt)
	{
		set_Value (COLUMNNAME_Month_4_Amt, Month_4_Amt);
	}

	/** Get Month_4_Amt.
		@return Month_4_Amt	  */
	public BigDecimal getMonth_4_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_4_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_4_Qty.
		@param Month_4_Qty Month_4_Qty	  */
	public void setMonth_4_Qty (BigDecimal Month_4_Qty)
	{
		set_Value (COLUMNNAME_Month_4_Qty, Month_4_Qty);
	}

	/** Get Month_4_Qty.
		@return Month_4_Qty	  */
	public BigDecimal getMonth_4_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_4_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_5_Amt.
		@param Month_5_Amt Month_5_Amt	  */
	public void setMonth_5_Amt (BigDecimal Month_5_Amt)
	{
		set_Value (COLUMNNAME_Month_5_Amt, Month_5_Amt);
	}

	/** Get Month_5_Amt.
		@return Month_5_Amt	  */
	public BigDecimal getMonth_5_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_5_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_5_Qty.
		@param Month_5_Qty Month_5_Qty	  */
	public void setMonth_5_Qty (BigDecimal Month_5_Qty)
	{
		set_Value (COLUMNNAME_Month_5_Qty, Month_5_Qty);
	}

	/** Get Month_5_Qty.
		@return Month_5_Qty	  */
	public BigDecimal getMonth_5_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_5_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_6_Amt.
		@param Month_6_Amt Month_6_Amt	  */
	public void setMonth_6_Amt (BigDecimal Month_6_Amt)
	{
		set_Value (COLUMNNAME_Month_6_Amt, Month_6_Amt);
	}

	/** Get Month_6_Amt.
		@return Month_6_Amt	  */
	public BigDecimal getMonth_6_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_6_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_6_Qty.
		@param Month_6_Qty Month_6_Qty	  */
	public void setMonth_6_Qty (BigDecimal Month_6_Qty)
	{
		set_Value (COLUMNNAME_Month_6_Qty, Month_6_Qty);
	}

	/** Get Month_6_Qty.
		@return Month_6_Qty	  */
	public BigDecimal getMonth_6_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_6_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_7_Amt.
		@param Month_7_Amt Month_7_Amt	  */
	public void setMonth_7_Amt (BigDecimal Month_7_Amt)
	{
		set_Value (COLUMNNAME_Month_7_Amt, Month_7_Amt);
	}

	/** Get Month_7_Amt.
		@return Month_7_Amt	  */
	public BigDecimal getMonth_7_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_7_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_7_Qty.
		@param Month_7_Qty Month_7_Qty	  */
	public void setMonth_7_Qty (BigDecimal Month_7_Qty)
	{
		set_Value (COLUMNNAME_Month_7_Qty, Month_7_Qty);
	}

	/** Get Month_7_Qty.
		@return Month_7_Qty	  */
	public BigDecimal getMonth_7_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_7_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_8_Amt.
		@param Month_8_Amt Month_8_Amt	  */
	public void setMonth_8_Amt (BigDecimal Month_8_Amt)
	{
		set_Value (COLUMNNAME_Month_8_Amt, Month_8_Amt);
	}

	/** Get Month_8_Amt.
		@return Month_8_Amt	  */
	public BigDecimal getMonth_8_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_8_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_8_Qty.
		@param Month_8_Qty Month_8_Qty	  */
	public void setMonth_8_Qty (BigDecimal Month_8_Qty)
	{
		set_Value (COLUMNNAME_Month_8_Qty, Month_8_Qty);
	}

	/** Get Month_8_Qty.
		@return Month_8_Qty	  */
	public BigDecimal getMonth_8_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_8_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_9_Amt.
		@param Month_9_Amt Month_9_Amt	  */
	public void setMonth_9_Amt (BigDecimal Month_9_Amt)
	{
		set_Value (COLUMNNAME_Month_9_Amt, Month_9_Amt);
	}

	/** Get Month_9_Amt.
		@return Month_9_Amt	  */
	public BigDecimal getMonth_9_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_9_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Month_9_Qty.
		@param Month_9_Qty Month_9_Qty	  */
	public void setMonth_9_Qty (BigDecimal Month_9_Qty)
	{
		set_Value (COLUMNNAME_Month_9_Qty, Month_9_Qty);
	}

	/** Get Month_9_Qty.
		@return Month_9_Qty	  */
	public BigDecimal getMonth_9_Qty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Month_9_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Trx Org Key.
		@param OrgTrxValue 
		Key of the Transaction Organization
	  */
	public void setOrgTrxValue (String OrgTrxValue)
	{
		set_Value (COLUMNNAME_OrgTrxValue, OrgTrxValue);
	}

	/** Get Trx Org Key.
		@return Key of the Transaction Organization
	  */
	public String getOrgTrxValue () 
	{
		return (String)get_Value(COLUMNNAME_OrgTrxValue);
	}

	/** Set Org Key.
		@param OrgValue 
		Key of the Organization
	  */
	public void setOrgValue (String OrgValue)
	{
		set_Value (COLUMNNAME_OrgValue, OrgValue);
	}

	/** Get Org Key.
		@return Key of the Organization
	  */
	public String getOrgValue () 
	{
		return (String)get_Value(COLUMNNAME_OrgValue);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Product Key.
		@param ProductValue 
		Key of the Product
	  */
	public void setProductValue (String ProductValue)
	{
		set_Value (COLUMNNAME_ProductValue, ProductValue);
	}

	/** Get Product Key.
		@return Key of the Product
	  */
	public String getProductValue () 
	{
		return (String)get_Value(COLUMNNAME_ProductValue);
	}

	/** Set Project Key.
		@param ProjectValue 
		Key of the Project
	  */
	public void setProjectValue (String ProjectValue)
	{
		set_Value (COLUMNNAME_ProjectValue, ProjectValue);
	}

	/** Get Project Key.
		@return Key of the Project
	  */
	public String getProjectValue () 
	{
		return (String)get_Value(COLUMNNAME_ProjectValue);
	}

	/** Set Sales Region Value.
		@param SalesRegionValue Sales Region Value	  */
	public void setSalesRegionValue (String SalesRegionValue)
	{
		set_Value (COLUMNNAME_SalesRegionValue, SalesRegionValue);
	}

	/** Get Sales Region Value.
		@return Sales Region Value	  */
	public String getSalesRegionValue () 
	{
		return (String)get_Value(COLUMNNAME_SalesRegionValue);
	}

	/** Set Sub Account Value.
		@param SubAcctValue 
		Sub account Value
	  */
	public void setSubAcctValue (String SubAcctValue)
	{
		set_Value (COLUMNNAME_SubAcctValue, SubAcctValue);
	}

	/** Get Sub Account Value.
		@return Sub account Value
	  */
	public String getSubAcctValue () 
	{
		return (String)get_Value(COLUMNNAME_SubAcctValue);
	}

	/** Set Immutable Universally Unique Identifier.
		@param UUID 
		Immutable Universally Unique Identifier
	  */
	public void setUUID (String UUID)
	{
		set_Value (COLUMNNAME_UUID, UUID);
	}

	/** Get Immutable Universally Unique Identifier.
		@return Immutable Universally Unique Identifier
	  */
	public String getUUID () 
	{
		return (String)get_Value(COLUMNNAME_UUID);
	}

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser1_ID(), get_TrxName());	}

	/** Set User List 1.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get User List 1.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser2_ID(), get_TrxName());	}

	/** Set User List 2.
		@param User2_ID 
		User defined list element #2
	  */
	public void setUser2_ID (int User2_ID)
	{
		if (User2_ID < 1) 
			set_Value (COLUMNNAME_User2_ID, null);
		else 
			set_Value (COLUMNNAME_User2_ID, Integer.valueOf(User2_ID));
	}

	/** Get User List 2.
		@return User defined list element #2
	  */
	public int getUser2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser3() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser3_ID(), get_TrxName());	}

	/** Set User List 3.
		@param User3_ID 
		User defined list element #3
	  */
	public void setUser3_ID (int User3_ID)
	{
		if (User3_ID < 1) 
			set_Value (COLUMNNAME_User3_ID, null);
		else 
			set_Value (COLUMNNAME_User3_ID, Integer.valueOf(User3_ID));
	}

	/** Get User List 3.
		@return User defined list element #3
	  */
	public int getUser3_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User3_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser4() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser4_ID(), get_TrxName());	}

	/** Set User List 4.
		@param User4_ID 
		User defined list element #4
	  */
	public void setUser4_ID (int User4_ID)
	{
		if (User4_ID < 1) 
			set_Value (COLUMNNAME_User4_ID, null);
		else 
			set_Value (COLUMNNAME_User4_ID, Integer.valueOf(User4_ID));
	}

	/** Get User List 4.
		@return User defined list element #4
	  */
	public int getUser4_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User4_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Element 1.
		@param UserElement1_ID 
		User defined accounting Element
	  */
	public void setUserElement1_ID (int UserElement1_ID)
	{
		if (UserElement1_ID < 1) 
			set_Value (COLUMNNAME_UserElement1_ID, null);
		else 
			set_Value (COLUMNNAME_UserElement1_ID, Integer.valueOf(UserElement1_ID));
	}

	/** Get User Element 1.
		@return User defined accounting Element
	  */
	public int getUserElement1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UserElement1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Element 2.
		@param UserElement2_ID 
		User defined accounting Element
	  */
	public void setUserElement2_ID (int UserElement2_ID)
	{
		if (UserElement2_ID < 1) 
			set_Value (COLUMNNAME_UserElement2_ID, null);
		else 
			set_Value (COLUMNNAME_UserElement2_ID, Integer.valueOf(UserElement2_ID));
	}

	/** Get User Element 2.
		@return User defined accounting Element
	  */
	public int getUserElement2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UserElement2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User Element Value 1.
		@param UserElementValue1 
		User Element Value 1 defined accounting Element
	  */
	public void setUserElementValue1 (String UserElementValue1)
	{
		set_Value (COLUMNNAME_UserElementValue1, UserElementValue1);
	}

	/** Get User Element Value 1.
		@return User Element Value 1 defined accounting Element
	  */
	public String getUserElementValue1 () 
	{
		return (String)get_Value(COLUMNNAME_UserElementValue1);
	}

	/** Set User Element Value 2.
		@param UserElementValue2 
		User Element Value 2 defined accounting Element
	  */
	public void setUserElementValue2 (String UserElementValue2)
	{
		set_Value (COLUMNNAME_UserElementValue2, UserElementValue2);
	}

	/** Get User Element Value 2.
		@return User Element Value 2 defined accounting Element
	  */
	public String getUserElementValue2 () 
	{
		return (String)get_Value(COLUMNNAME_UserElementValue2);
	}

	/** UserValue1 AD_Reference_ID=134 */
	public static final int USERVALUE1_AD_Reference_ID=134;
	/** Set User List Value 1.
		@param UserValue1 
		User value defined list element #1
	  */
	public void setUserValue1 (String UserValue1)
	{

		set_Value (COLUMNNAME_UserValue1, UserValue1);
	}

	/** Get User List Value 1.
		@return User value defined list element #1
	  */
	public String getUserValue1 () 
	{
		return (String)get_Value(COLUMNNAME_UserValue1);
	}

	/** UserValue2 AD_Reference_ID=134 */
	public static final int USERVALUE2_AD_Reference_ID=134;
	/** Set User List Value 2.
		@param UserValue2 
		User value defined list element #2
	  */
	public void setUserValue2 (String UserValue2)
	{

		set_Value (COLUMNNAME_UserValue2, UserValue2);
	}

	/** Get User List Value 2.
		@return User value defined list element #2
	  */
	public String getUserValue2 () 
	{
		return (String)get_Value(COLUMNNAME_UserValue2);
	}

	/** UserValue3 AD_Reference_ID=134 */
	public static final int USERVALUE3_AD_Reference_ID=134;
	/** Set User List Value 3.
		@param UserValue3 
		User value defined list element #3
	  */
	public void setUserValue3 (String UserValue3)
	{

		set_Value (COLUMNNAME_UserValue3, UserValue3);
	}

	/** Get User List Value 3.
		@return User value defined list element #3
	  */
	public String getUserValue3 () 
	{
		return (String)get_Value(COLUMNNAME_UserValue3);
	}

	/** UserValue4 AD_Reference_ID=134 */
	public static final int USERVALUE4_AD_Reference_ID=134;
	/** Set User List Value 4.
		@param UserValue4 
		User value defined list element #3
	  */
	public void setUserValue4 (String UserValue4)
	{

		set_Value (COLUMNNAME_UserValue4, UserValue4);
	}

	/** Get User List Value 4.
		@return User value defined list element #3
	  */
	public String getUserValue4 () 
	{
		return (String)get_Value(COLUMNNAME_UserValue4);
	}
}