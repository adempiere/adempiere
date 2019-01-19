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
package org.spin.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for C_BankMatcher
 *  @author Adempiere (generated) 
 *  @version Release 3.9.1 - $Id$ */
public class X_C_BankMatcher extends PO implements I_C_BankMatcher, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181220L;

    /** Standard Constructor */
    public X_C_BankMatcher (Properties ctx, int C_BankMatcher_ID, String trxName)
    {
      super (ctx, C_BankMatcher_ID, trxName);
      /** if (C_BankMatcher_ID == 0)
        {
			setC_BankMatcher_ID (0);
			setC_BankStatementMatcher_ID (0);
			setC_Bank_ID (0);
        } */
    }

    /** Load Constructor */
    public X_C_BankMatcher (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 2 - Client 
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
      StringBuffer sb = new StringBuffer ("X_C_BankMatcher[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Bank Matcher.
		@param C_BankMatcher_ID Bank Matcher	  */
	public void setC_BankMatcher_ID (int C_BankMatcher_ID)
	{
		if (C_BankMatcher_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BankMatcher_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BankMatcher_ID, Integer.valueOf(C_BankMatcher_ID));
	}

	/** Get Bank Matcher.
		@return Bank Matcher	  */
	public int getC_BankMatcher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankMatcher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BankStatementMatcher getC_BankStatementMatcher() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankStatementMatcher)MTable.get(getCtx(), org.compiere.model.I_C_BankStatementMatcher.Table_Name)
			.getPO(getC_BankStatementMatcher_ID(), get_TrxName());	}

	/** Set Bank Statement Matcher.
		@param C_BankStatementMatcher_ID 
		Algorithm to match Bank Statement Info to Business Partners, Invoices and Payments
	  */
	public void setC_BankStatementMatcher_ID (int C_BankStatementMatcher_ID)
	{
		if (C_BankStatementMatcher_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BankStatementMatcher_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BankStatementMatcher_ID, Integer.valueOf(C_BankStatementMatcher_ID));
	}

	/** Get Bank Statement Matcher.
		@return Algorithm to match Bank Statement Info to Business Partners, Invoices and Payments
	  */
	public int getC_BankStatementMatcher_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankStatementMatcher_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException
    {
		return (org.compiere.model.I_C_Bank)MTable.get(getCtx(), org.compiere.model.I_C_Bank.Table_Name)
			.getPO(getC_Bank_ID(), get_TrxName());	}

	/** Set Bank.
		@param C_Bank_ID 
		Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID)
	{
		if (C_Bank_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Bank_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Bank_ID, Integer.valueOf(C_Bank_ID));
	}

	/** Get Bank.
		@return Bank
	  */
	public int getC_Bank_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Bank_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
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