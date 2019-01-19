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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for Fact_Reconciliation
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_Fact_Reconciliation extends PO implements I_Fact_Reconciliation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_Fact_Reconciliation (Properties ctx, int Fact_Reconciliation_ID, String trxName)
    {
      super (ctx, Fact_Reconciliation_ID, trxName);
      /** if (Fact_Reconciliation_ID == 0)
        {
			setFact_Acct_ID (0);
			setFact_Reconciliation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_Fact_Reconciliation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_Fact_Reconciliation[")
        .append(get_ID()).append("]");
      return sb.toString();
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
		throw new IllegalArgumentException ("Account_ID is virtual column");	}

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

	/** Set Accounted Amount.
		@param AmtAcct 
		Amount Balance in Currency of Accounting Schema
	  */
	public void setAmtAcct (BigDecimal AmtAcct)
	{
		throw new IllegalArgumentException ("AmtAcct is virtual column");	}

	/** Get Accounted Amount.
		@return Amount Balance in Currency of Accounting Schema
	  */
	public BigDecimal getAmtAcct () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_AmtAcct);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
		throw new IllegalArgumentException ("C_BPartner_ID is virtual column");	}

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

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		throw new IllegalArgumentException ("DateAcct is virtual column");	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	public org.compiere.model.I_Fact_Acct getFact_Acct() throws RuntimeException
    {
		return (org.compiere.model.I_Fact_Acct)MTable.get(getCtx(), org.compiere.model.I_Fact_Acct.Table_Name)
			.getPO(getFact_Acct_ID(), get_TrxName());	}

	/** Set Accounting Fact.
		@param Fact_Acct_ID Accounting Fact	  */
	public void setFact_Acct_ID (int Fact_Acct_ID)
	{
		if (Fact_Acct_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Fact_Acct_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Fact_Acct_ID, Integer.valueOf(Fact_Acct_ID));
	}

	/** Get Accounting Fact.
		@return Accounting Fact	  */
	public int getFact_Acct_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fact_Acct_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getFact_Acct_ID()));
    }

	/** Set Accounting Fact Reconciliation.
		@param Fact_Reconciliation_ID Accounting Fact Reconciliation	  */
	public void setFact_Reconciliation_ID (int Fact_Reconciliation_ID)
	{
		if (Fact_Reconciliation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_Fact_Reconciliation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_Fact_Reconciliation_ID, Integer.valueOf(Fact_Reconciliation_ID));
	}

	/** Get Accounting Fact Reconciliation.
		@return Accounting Fact Reconciliation	  */
	public int getFact_Reconciliation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Fact_Reconciliation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Match Code.
		@param MatchCode 
		String identifying related accounting facts
	  */
	public void setMatchCode (String MatchCode)
	{
		set_Value (COLUMNNAME_MatchCode, MatchCode);
	}

	/** Get Match Code.
		@return String identifying related accounting facts
	  */
	public String getMatchCode () 
	{
		return (String)get_Value(COLUMNNAME_MatchCode);
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