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
package org.adempiere.core.domains.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for FM_Account
 *  @author Adempiere (generated) 
 *  @version Release 3.9.3 - $Id$ */
public class X_FM_Account extends PO implements I_FM_Account, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20220507L;

    /** Standard Constructor */
    public X_FM_Account (Properties ctx, int FM_Account_ID, String trxName)
    {
      super (ctx, FM_Account_ID, trxName);
      /** if (FM_Account_ID == 0)
        {
			setAccountNo (null);
			setC_Currency_ID (0);
			setFM_Account_ID (0);
			setFM_Agreement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_FM_Account (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_FM_Account[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getAccountNo());
    }

	/** Set Capital Amount.
		@param CapitalAmt Capital Amount	  */
	public void setCapitalAmt (BigDecimal CapitalAmt)
	{
		set_Value (COLUMNNAME_CapitalAmt, CapitalAmt);
	}

	/** Get Capital Amount.
		@return Capital Amount	  */
	public BigDecimal getCapitalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CapitalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.adempiere.core.domains.models.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_C_Currency)MTable.get(getCtx(), org.adempiere.core.domains.models.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Currency_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current balance.
		@param CurrentBalance 
		Current Balance
	  */
	public void setCurrentBalance (BigDecimal CurrentBalance)
	{
		set_Value (COLUMNNAME_CurrentBalance, CurrentBalance);
	}

	/** Get Current balance.
		@return Current Balance
	  */
	public BigDecimal getCurrentBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Quantity of Fees.
		@param FeesQty 
		Quantity of Fees for Loan
	  */
	public void setFeesQty (BigDecimal FeesQty)
	{
		set_Value (COLUMNNAME_FeesQty, FeesQty);
	}

	/** Get Quantity of Fees.
		@return Quantity of Fees for Loan
	  */
	public BigDecimal getFeesQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_FeesQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Financial Account.
		@param FM_Account_ID Financial Account	  */
	public void setFM_Account_ID (int FM_Account_ID)
	{
		if (FM_Account_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_FM_Account_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_FM_Account_ID, Integer.valueOf(FM_Account_ID));
	}

	/** Get Financial Account.
		@return Financial Account	  */
	public int getFM_Account_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Account_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.adempiere.core.domains.models.I_FM_Agreement getFM_Agreement() throws RuntimeException
    {
		return (org.adempiere.core.domains.models.I_FM_Agreement)MTable.get(getCtx(), org.adempiere.core.domains.models.I_FM_Agreement.Table_Name)
			.getPO(getFM_Agreement_ID(), get_TrxName());	}

	/** Set Agreement.
		@param FM_Agreement_ID Agreement	  */
	public void setFM_Agreement_ID (int FM_Agreement_ID)
	{
		if (FM_Agreement_ID < 1) 
			set_Value (COLUMNNAME_FM_Agreement_ID, null);
		else 
			set_Value (COLUMNNAME_FM_Agreement_ID, Integer.valueOf(FM_Agreement_ID));
	}

	/** Get Agreement.
		@return Agreement	  */
	public int getFM_Agreement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_FM_Agreement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Interest Amount.
		@param InterestAmt 
		Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt)
	{
		set_Value (COLUMNNAME_InterestAmt, InterestAmt);
	}

	/** Get Interest Amount.
		@return Interest Amount
	  */
	public BigDecimal getInterestAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_Value (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	public boolean isPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Payment date.
		@param PayDate 
		Date Payment made
	  */
	public void setPayDate (Timestamp PayDate)
	{
		set_Value (COLUMNNAME_PayDate, PayDate);
	}

	/** Get Payment date.
		@return Date Payment made
	  */
	public Timestamp getPayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayDate);
	}

	/** PaymentFrequency AD_Reference_ID=54019 */
	public static final int PAYMENTFREQUENCY_AD_Reference_ID=54019;
	/** Daily = D */
	public static final String PAYMENTFREQUENCY_Daily = "D";
	/** Monthly = M */
	public static final String PAYMENTFREQUENCY_Monthly = "M";
	/** Quarterly = Q */
	public static final String PAYMENTFREQUENCY_Quarterly = "Q";
	/** Semi-yearly = S */
	public static final String PAYMENTFREQUENCY_Semi_Yearly = "S";
	/** Yearly = Y */
	public static final String PAYMENTFREQUENCY_Yearly = "Y";
	/** Twice Monthly = T */
	public static final String PAYMENTFREQUENCY_TwiceMonthly = "T";
	/** Single Fee = F */
	public static final String PAYMENTFREQUENCY_SingleFee = "F";
	/** Weekly = W */
	public static final String PAYMENTFREQUENCY_Weekly = "W";
	/** Set Payment Frequency.
		@param PaymentFrequency 
		Payment Frequency
	  */
	public void setPaymentFrequency (String PaymentFrequency)
	{

		set_Value (COLUMNNAME_PaymentFrequency, PaymentFrequency);
	}

	/** Get Payment Frequency.
		@return Payment Frequency
	  */
	public String getPaymentFrequency () 
	{
		return (String)get_Value(COLUMNNAME_PaymentFrequency);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Status AD_Reference_ID=54012 */
	public static final int STATUS_AD_Reference_ID=54012;
	/** Agreement = A */
	public static final String STATUS_Agreement = "A";
	/** Transaction = T */
	public static final String STATUS_Transaction = "T";
	/** Account = C */
	public static final String STATUS_Account = "C";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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