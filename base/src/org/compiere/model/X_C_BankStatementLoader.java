/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for C_BankStatementLoader
 *  @author Adempiere (generated) 
 *  @version Release 3.6.0LTS - $Id$ */
public class X_C_BankStatementLoader extends PO implements I_C_BankStatementLoader, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20100614L;

    /** Standard Constructor */
    public X_C_BankStatementLoader (Properties ctx, int C_BankStatementLoader_ID, String trxName)
    {
      super (ctx, C_BankStatementLoader_ID, trxName);
      /** if (C_BankStatementLoader_ID == 0)
        {
			setC_BankAccount_ID (0);
			setC_BankStatementLoader_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_C_BankStatementLoader (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_C_BankStatementLoader[")
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

	/** Set Branch ID.
		@param BranchID 
		Bank Branch ID
	  */
	public void setBranchID (String BranchID)
	{
		set_Value (COLUMNNAME_BranchID, BranchID);
	}

	/** Get Branch ID.
		@return Bank Branch ID
	  */
	public String getBranchID () 
	{
		return (String)get_Value(COLUMNNAME_BranchID);
	}

	public I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (I_C_BankAccount)MTable.get(getCtx(), I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BankAccount_ID, null);
		else 
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

	/** Set Bank Statement Loader.
		@param C_BankStatementLoader_ID 
		Definition of Bank Statement Loader (SWIFT, OFX)
	  */
	public void setC_BankStatementLoader_ID (int C_BankStatementLoader_ID)
	{
		if (C_BankStatementLoader_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BankStatementLoader_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BankStatementLoader_ID, Integer.valueOf(C_BankStatementLoader_ID));
	}

	/** Get Bank Statement Loader.
		@return Definition of Bank Statement Loader (SWIFT, OFX)
	  */
	public int getC_BankStatementLoader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankStatementLoader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Format.
		@param DateFormat 
		Date format used in the input format
	  */
	public void setDateFormat (String DateFormat)
	{
		set_Value (COLUMNNAME_DateFormat, DateFormat);
	}

	/** Get Date Format.
		@return Date format used in the input format
	  */
	public String getDateFormat () 
	{
		return (String)get_Value(COLUMNNAME_DateFormat);
	}

	/** Set Date last run.
		@param DateLastRun 
		Date the process was last run.
	  */
	public void setDateLastRun (Timestamp DateLastRun)
	{
		set_Value (COLUMNNAME_DateLastRun, DateLastRun);
	}

	/** Get Date last run.
		@return Date the process was last run.
	  */
	public Timestamp getDateLastRun () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateLastRun);
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

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** Set Financial Institution ID.
		@param FinancialInstitutionID 
		The ID of the Financial Institution / Bank
	  */
	public void setFinancialInstitutionID (String FinancialInstitutionID)
	{
		set_Value (COLUMNNAME_FinancialInstitutionID, FinancialInstitutionID);
	}

	/** Get Financial Institution ID.
		@return The ID of the Financial Institution / Bank
	  */
	public String getFinancialInstitutionID () 
	{
		return (String)get_Value(COLUMNNAME_FinancialInstitutionID);
	}

	/** Set Host Address.
		@param HostAddress 
		Host Address URL or DNS
	  */
	public void setHostAddress (String HostAddress)
	{
		set_Value (COLUMNNAME_HostAddress, HostAddress);
	}

	/** Get Host Address.
		@return Host Address URL or DNS
	  */
	public String getHostAddress () 
	{
		return (String)get_Value(COLUMNNAME_HostAddress);
	}

	/** Set Host port.
		@param HostPort 
		Host Communication Port
	  */
	public void setHostPort (int HostPort)
	{
		set_Value (COLUMNNAME_HostPort, Integer.valueOf(HostPort));
	}

	/** Get Host port.
		@return Host Communication Port
	  */
	public int getHostPort () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HostPort);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set PIN.
		@param PIN 
		Personal Identification Number
	  */
	public void setPIN (String PIN)
	{
		set_Value (COLUMNNAME_PIN, PIN);
	}

	/** Get PIN.
		@return Personal Identification Number
	  */
	public String getPIN () 
	{
		return (String)get_Value(COLUMNNAME_PIN);
	}

	/** Set Proxy address.
		@param ProxyAddress 
		 Address of your proxy server
	  */
	public void setProxyAddress (String ProxyAddress)
	{
		set_Value (COLUMNNAME_ProxyAddress, ProxyAddress);
	}

	/** Get Proxy address.
		@return  Address of your proxy server
	  */
	public String getProxyAddress () 
	{
		return (String)get_Value(COLUMNNAME_ProxyAddress);
	}

	/** Set Proxy logon.
		@param ProxyLogon 
		Logon of your proxy server
	  */
	public void setProxyLogon (String ProxyLogon)
	{
		set_Value (COLUMNNAME_ProxyLogon, ProxyLogon);
	}

	/** Get Proxy logon.
		@return Logon of your proxy server
	  */
	public String getProxyLogon () 
	{
		return (String)get_Value(COLUMNNAME_ProxyLogon);
	}

	/** Set Proxy password.
		@param ProxyPassword 
		Password of your proxy server
	  */
	public void setProxyPassword (String ProxyPassword)
	{
		set_Value (COLUMNNAME_ProxyPassword, ProxyPassword);
	}

	/** Get Proxy password.
		@return Password of your proxy server
	  */
	public String getProxyPassword () 
	{
		return (String)get_Value(COLUMNNAME_ProxyPassword);
	}

	/** Set Proxy port.
		@param ProxyPort 
		Port of your proxy server
	  */
	public void setProxyPort (int ProxyPort)
	{
		set_Value (COLUMNNAME_ProxyPort, Integer.valueOf(ProxyPort));
	}

	/** Get Proxy port.
		@return Port of your proxy server
	  */
	public int getProxyPort () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ProxyPort);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Statement Loader Class.
		@param StmtLoaderClass 
		Class name of the bank statement loader
	  */
	public void setStmtLoaderClass (String StmtLoaderClass)
	{
		set_Value (COLUMNNAME_StmtLoaderClass, StmtLoaderClass);
	}

	/** Get Statement Loader Class.
		@return Class name of the bank statement loader
	  */
	public String getStmtLoaderClass () 
	{
		return (String)get_Value(COLUMNNAME_StmtLoaderClass);
	}

	/** Set User ID.
		@param UserID 
		User ID or account number
	  */
	public void setUserID (String UserID)
	{
		set_Value (COLUMNNAME_UserID, UserID);
	}

	/** Get User ID.
		@return User ID or account number
	  */
	public String getUserID () 
	{
		return (String)get_Value(COLUMNNAME_UserID);
	}
}