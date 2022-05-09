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

/** Generated Model for FM_TransactionType
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_TransactionType extends PO implements I_FM_TransactionType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_TransactionType (Properties ctx, int FM_TransactionType_ID, String trxName)
    {
      super (ctx, FM_TransactionType_ID, trxName);
      /** if (FM_TransactionType_ID == 0)
        {
			setFM_TransactionType_ID (0);
			setName (null);
			setType (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_FM_TransactionType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_TransactionType[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Type AD_Reference_ID=54020 */
	public static final int TYPE_AD_Reference_ID=54020;
	/** Transaction Reverse = TRE */
	public static final String TYPE_TransactionReverse = "TRE";
	/** Loan Interest (Calculated) = LIN */
	public static final String TYPE_LoanInterestCalculated = "LIN";
	/** Loan Dunning Interest (Calculated) = LDD */
	public static final String TYPE_LoanDunningInterestCalculated = "LDD";
	/** Loan Initial Payment = LIP */
	public static final String TYPE_LoanInitialPayment = "LIP";
	/** Loan Interest Tax Calculated = LIT */
	public static final String TYPE_LoanInterestTaxCalculated = "LIT";
	/** Loan Capital (Calculated) = LCC */
	public static final String TYPE_LoanCapitalCalculated = "LCC";
	/** Loan Dunning Tax Amount (Calculated) = LDT */
	public static final String TYPE_LoanDunningTaxAmountCalculated = "LDT";
	/** Loan Capital (Invoiced) = LCI */
	public static final String TYPE_LoanCapitalInvoiced = "LCI";
	/** Loan Interest (Invoiced) = LII */
	public static final String TYPE_LoanInterestInvoiced = "LII";
	/** Loan Dunning Interest (Invoiced) = LDI */
	public static final String TYPE_LoanDunningInterestInvoiced = "LDI";
	/** Loan Tax Amount (Invoiced) = LTI */
	public static final String TYPE_LoanTaxAmountInvoiced = "LTI";
	/** Loan Dunning Tax Amount (Invoiced) = LTT */
	public static final String TYPE_LoanDunningTaxAmountInvoiced = "LTT";
	/** Loan Provision (Calculated) = LPC */
	public static final String TYPE_LoanProvisionCalculated = "LPC";
	/** Loan Suspend Interest (Calculated) = LSI */
	public static final String TYPE_LoanSuspendInterestCalculated = "LSI";
	/** Loan Suspend Dunning (Calculated) = LSD */
	public static final String TYPE_LoanSuspendDunningCalculated = "LSD";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		set_ValueNoCheck (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getValue());
    }
}