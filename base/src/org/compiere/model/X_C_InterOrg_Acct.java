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

import java.sql.ResultSet;
import java.util.Properties;

/** Generated Model for C_InterOrg_Acct
 *  @author Adempiere (generated) 
 *  @version Release 3.9.2 - $Id$ */
public class X_C_InterOrg_Acct extends PO implements I_C_InterOrg_Acct, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191120L;

    /** Standard Constructor */
    public X_C_InterOrg_Acct (Properties ctx, int C_InterOrg_Acct_ID, String trxName)
    {
      super (ctx, C_InterOrg_Acct_ID, trxName);
      /** if (C_InterOrg_Acct_ID == 0)
        {
			setAD_OrgTo_ID (0);
			setC_AcctSchema_ID (0);
			setIntercompanyDueFrom_Acct (0);
			setIntercompanyDueTo_Acct (0);
        } */
    }

    /** Load Constructor */
    public X_C_InterOrg_Acct (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_InterOrg_Acct[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Org getAD_OrgTo() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Org)MTable.get(getCtx(), org.compiere.model.I_AD_Org.Table_Name)
			.getPO(getAD_OrgTo_ID(), get_TrxName());	}

	/** Set Inter-Organization.
		@param AD_OrgTo_ID 
		Organization valid for intercompany documents
	  */
	public void setAD_OrgTo_ID (int AD_OrgTo_ID)
	{
		if (AD_OrgTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_OrgTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_OrgTo_ID, Integer.valueOf(AD_OrgTo_ID));
	}

	/** Get Inter-Organization.
		@return Organization valid for intercompany documents
	  */
	public int getAD_OrgTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_AcctSchema_ID, Integer.valueOf(C_AcctSchema_ID));
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

	public I_C_ValidCombination getIntercompanyDueFrom_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getIntercompanyDueFrom_Acct(), get_TrxName());	}

	/** Set Intercompany Due From Acct.
		@param IntercompanyDueFrom_Acct 
		Intercompany Due From / Receivables Account
	  */
	public void setIntercompanyDueFrom_Acct (int IntercompanyDueFrom_Acct)
	{
		set_Value (COLUMNNAME_IntercompanyDueFrom_Acct, Integer.valueOf(IntercompanyDueFrom_Acct));
	}

	/** Get Intercompany Due From Acct.
		@return Intercompany Due From / Receivables Account
	  */
	public int getIntercompanyDueFrom_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntercompanyDueFrom_Acct);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_ValidCombination getIntercompanyDueTo_A() throws RuntimeException
    {
		return (I_C_ValidCombination)MTable.get(getCtx(), I_C_ValidCombination.Table_Name)
			.getPO(getIntercompanyDueTo_Acct(), get_TrxName());	}

	/** Set Intercompany Due To Acct.
		@param IntercompanyDueTo_Acct 
		Intercompany Due To / Payable Account
	  */
	public void setIntercompanyDueTo_Acct (int IntercompanyDueTo_Acct)
	{
		set_Value (COLUMNNAME_IntercompanyDueTo_Acct, Integer.valueOf(IntercompanyDueTo_Acct));
	}

	/** Get Intercompany Due To Acct.
		@return Intercompany Due To / Payable Account
	  */
	public int getIntercompanyDueTo_Acct () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IntercompanyDueTo_Acct);
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