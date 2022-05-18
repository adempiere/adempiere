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
package org.spin.investment.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_TransactionType_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_TransactionType_Acct extends PO implements I_FM_TransactionType_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_TransactionType_Acct (Properties ctx, int FM_TransactionType_Acct_ID, String trxName)
    {
      super (ctx, FM_TransactionType_Acct_ID, trxName);
      /** if (FM_TransactionType_Acct_ID == 0)
        {
			setC_AcctSchema_ID (0);
			setFM_Expense_Acct (0);
			setFM_Revenue_Acct (0);
			setFM_TransactionType_Acct_ID (0);
			setFM_TransactionType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FM_TransactionType_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_TransactionType_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public I_C_ValidCombination getFM_Expense_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getFM_Expense_Acct(), get_TrxName());	}

	/** Set Financial Expense Account.
		@param FM_Expense_Acct Financial Expense Account	  */
	public void setFM_Expense_Acct (int FM_Expense_Acct)
	{
		set_Value (COLUMNNAME_FM_Expense_Acct, Integer.valueOf(FM_Expense_Acct));
	}

	/** Get Financial Expense Account.
		@return Financial Expense Account	  */
	public int getFM_Expense_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Expense_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getFM_Revenue_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getFM_Revenue_Acct(), get_TrxName());	}

	/** Set Financial Revenue Account.
		@param FM_Revenue_Acct Financial Revenue Account	  */
	public void setFM_Revenue_Acct (int FM_Revenue_Acct)
	{
		set_Value (COLUMNNAME_FM_Revenue_Acct, Integer.valueOf(FM_Revenue_Acct));
	}

	/** Get Financial Revenue Account.
		@return Financial Revenue Account	  */
	public int getFM_Revenue_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Revenue_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Financial Transaction Type Account ID.
		@param FM_TransactionType_Acct_ID Financial Transaction Type Account ID	  */
	public void setFM_TransactionType_Acct_ID (int FM_TransactionType_Acct_ID)
	{
		if (FM_TransactionType_Acct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_TransactionType_Acct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_TransactionType_Acct_ID, Integer.valueOf(FM_TransactionType_Acct_ID));
	}

	/** Get Financial Transaction Type Account ID.
		@return Financial Transaction Type Account ID	  */
	public int getFM_TransactionType_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_TransactionType_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.spin.investment.model.I_FM_TransactionType getFM_TransactionType() throws RuntimeException
    {
		return (org.spin.investment.model.I_FM_TransactionType)MTable.get(getCtx(), org.spin.investment.model.I_FM_TransactionType.Table_Name)
			.getPO(getFM_TransactionType_ID(), get_TrxName());	}

	/** Set Financial Transaction Type.
		@param FM_TransactionType_ID Financial Transaction Type	  */
	public void setFM_TransactionType_ID (int FM_TransactionType_ID)
	{
		if (FM_TransactionType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_TransactionType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_TransactionType_ID, Integer.valueOf(FM_TransactionType_ID));
	}

	/** Get Financial Transaction Type.
		@return Financial Transaction Type	  */
	public int getFM_TransactionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_TransactionType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getFM_TransactionType_ID()));
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
}