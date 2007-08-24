/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.lang.reflect.Constructor;
import java.util.logging.Level;
import org.compiere.util.*;

/** Generated Model for C_BankAccount
 *  @author Adempiere (generated) 
 *  @version Release 3.3.0 - $Id$ */
public class X_C_BankAccount extends PO implements I_C_BankAccount, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    /** Standard Constructor */
    public X_C_BankAccount (Properties ctx, int C_BankAccount_ID, String trxName)
    {
      super (ctx, C_BankAccount_ID, trxName);
      /** if (C_BankAccount_ID == 0)        {			setAccountNo (null);
			setBankAccountType (null);
			setC_BankAccount_ID (0);
			setC_Bank_ID (0);
			setC_Currency_ID (0);
			setCreditLimit (Env.ZERO);
			setCurrentBalance (Env.ZERO);
			setIsDefault (false);
} */
    }

    /** Load Constructor */
    public X_C_BankAccount (Properties ctx, ResultSet rs, String trxName)
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
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_C_BankAccount[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		if (AccountNo == null)
			throw new IllegalArgumentException ("AccountNo is mandatory.");
		if (AccountNo.length() > 20)
		{
			log.warning("Length > 20 - truncated");
			AccountNo = AccountNo.substring(0, 19);
		}
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

	/** Set BBAN.
		@param BBAN 
		Basic Bank Account Number
	  */
	public void setBBAN (String BBAN)
	{
		if (BBAN != null && BBAN.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			BBAN = BBAN.substring(0, 39);
		}
		set_Value (COLUMNNAME_BBAN, BBAN);
	}

	/** Get BBAN.
		@return Basic Bank Account Number
	  */
	public String getBBAN () 
	{
		return (String)get_Value(COLUMNNAME_BBAN);
	}

/** BankAccountType AD_Reference_ID=216 */
public static final int BANKACCOUNTTYPE_AD_Reference_ID=216;/** Checking = C */
public static final String BANKACCOUNTTYPE_Checking = "C";/** Savings = S */
public static final String BANKACCOUNTTYPE_Savings = "S";
	/** Set Bank Account Type.
		@param BankAccountType 
		Bank Account Type
	  */
	public void setBankAccountType (String BankAccountType)
	{
if (BankAccountType == null) throw new IllegalArgumentException ("BankAccountType is mandatory");if (BankAccountType.equals("C") || BankAccountType.equals("S")); else throw new IllegalArgumentException ("BankAccountType Invalid value - " + BankAccountType + " - Reference_ID=216 - C - S");		if (BankAccountType.length() > 1)
		{
			log.warning("Length > 1 - truncated");
			BankAccountType = BankAccountType.substring(0, 0);
		}
		set_Value (COLUMNNAME_BankAccountType, BankAccountType);
	}

	/** Get Bank Account Type.
		@return Bank Account Type
	  */
	public String getBankAccountType () 
	{
		return (String)get_Value(COLUMNNAME_BankAccountType);
	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1)
			 throw new IllegalArgumentException ("C_BankAccount_ID is mandatory.");
		set_ValueNoCheck (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_C_Bank getI_C_Bank() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Bank.Table_Name);
        I_C_Bank result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Bank)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Bank_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Bank.
		@param C_Bank_ID 
		Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID)
	{
		if (C_Bank_ID < 1)
			 throw new IllegalArgumentException ("C_Bank_ID is mandatory.");
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_Bank_ID()));
    }

	public I_C_Currency getI_C_Currency() throws Exception 
    {
        Class<?> clazz = MTable.getClass(I_C_Currency.Table_Name);
        I_C_Currency result = null;
        try	{
	        Constructor<?> constructor = null;
	    	constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class, int.class, String.class});
    	    result = (I_C_Currency)constructor.newInstance(new Object[] {getCtx(), new Integer(getC_Currency_ID()), get_TrxName()});
        } catch (Exception e) {
	        log.log(Level.SEVERE, "(id) - Table=" + Table_Name + ",Class=" + clazz, e);
	        log.saveError("Error", "Table=" + Table_Name + ",Class=" + clazz);
           throw e;
        }
        return result;
    }

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1)
			 throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
		set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
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

	/** Set Credit limit.
		@param CreditLimit 
		Amount of Credit allowed
	  */
	public void setCreditLimit (BigDecimal CreditLimit)
	{
		if (CreditLimit == null)
			throw new IllegalArgumentException ("CreditLimit is mandatory.");
		set_Value (COLUMNNAME_CreditLimit, CreditLimit);
	}

	/** Get Credit limit.
		@return Amount of Credit allowed
	  */
	public BigDecimal getCreditLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CreditLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Current balance.
		@param CurrentBalance 
		Current Balance
	  */
	public void setCurrentBalance (BigDecimal CurrentBalance)
	{
		if (CurrentBalance == null)
			throw new IllegalArgumentException ("CurrentBalance is mandatory.");
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
		if (Description != null && Description.length() > 255)
		{
			log.warning("Length > 255 - truncated");
			Description = Description.substring(0, 254);
		}
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set IBAN.
		@param IBAN 
		International Bank Account Number
	  */
	public void setIBAN (String IBAN)
	{
		if (IBAN != null && IBAN.length() > 40)
		{
			log.warning("Length > 40 - truncated");
			IBAN = IBAN.substring(0, 39);
		}
		set_Value (COLUMNNAME_IBAN, IBAN);
	}

	/** Get IBAN.
		@return International Bank Account Number
	  */
	public String getIBAN () 
	{
		return (String)get_Value(COLUMNNAME_IBAN);
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}