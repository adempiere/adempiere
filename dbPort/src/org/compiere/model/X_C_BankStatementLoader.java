/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software;
 you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY;
 without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program;
 if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.model;

/** Generated Model - DO NOT CHANGE */
import java.util.*;
import java.sql.*;
import java.math.*;
import org.compiere.util.*;
/** Generated Model for C_BankStatementLoader
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_BankStatementLoader extends PO
{
/** Standard Constructor
@param ctx context
@param C_BankStatementLoader_ID id
@param trxName transaction
*/
public X_C_BankStatementLoader (Properties ctx, int C_BankStatementLoader_ID, String trxName)
{
super (ctx, C_BankStatementLoader_ID, trxName);
/** if (C_BankStatementLoader_ID == 0)
{
setC_BankAccount_ID (0);
setC_BankStatementLoader_ID (0);
setName (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BankStatementLoader (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=640 */
public static final int Table_ID=MTable.getTable_ID("C_BankStatementLoader");

/** TableName=C_BankStatementLoader */
public static final String Table_Name="C_BankStatementLoader";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BankStatementLoader");

protected BigDecimal accessLevel = BigDecimal.valueOf(3);
/** AccessLevel
@return 3 - Client - Org 
*/
protected int get_AccessLevel()
{
return accessLevel.intValue();
}
/** Load Meta Data
@param ctx context
@return PO Info
*/
protected POInfo initPO (Properties ctx)
{
POInfo poi = POInfo.getPOInfo (ctx, Table_ID);
return poi;
}
/** Info
@return info
*/
public String toString()
{
StringBuffer sb = new StringBuffer ("X_C_BankStatementLoader[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Account No.
@param AccountNo Account Number */
public void setAccountNo (String AccountNo)
{
if (AccountNo != null && AccountNo.length() > 20)
{
log.warning("Length > 20 - truncated");
AccountNo = AccountNo.substring(0,19);
}
set_Value ("AccountNo", AccountNo);
}
/** Get Account No.
@return Account Number */
public String getAccountNo() 
{
return (String)get_Value("AccountNo");
}
/** Column name AccountNo */
public static final String COLUMNNAME_AccountNo = "AccountNo";
/** Set Branch ID.
@param BranchID Bank Branch ID */
public void setBranchID (String BranchID)
{
if (BranchID != null && BranchID.length() > 20)
{
log.warning("Length > 20 - truncated");
BranchID = BranchID.substring(0,19);
}
set_Value ("BranchID", BranchID);
}
/** Get Branch ID.
@return Bank Branch ID */
public String getBranchID() 
{
return (String)get_Value("BranchID");
}
/** Column name BranchID */
public static final String COLUMNNAME_BranchID = "BranchID";
/** Set Bank Account.
@param C_BankAccount_ID Account at the Bank */
public void setC_BankAccount_ID (int C_BankAccount_ID)
{
if (C_BankAccount_ID < 1) throw new IllegalArgumentException ("C_BankAccount_ID is mandatory.");
set_ValueNoCheck ("C_BankAccount_ID", Integer.valueOf(C_BankAccount_ID));
}
/** Get Bank Account.
@return Account at the Bank */
public int getC_BankAccount_ID() 
{
Integer ii = (Integer)get_Value("C_BankAccount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankAccount_ID */
public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";
/** Set Bank Statement Loader.
@param C_BankStatementLoader_ID Definition of Bank Statement Loader (SWIFT, OFX) */
public void setC_BankStatementLoader_ID (int C_BankStatementLoader_ID)
{
if (C_BankStatementLoader_ID < 1) throw new IllegalArgumentException ("C_BankStatementLoader_ID is mandatory.");
set_ValueNoCheck ("C_BankStatementLoader_ID", Integer.valueOf(C_BankStatementLoader_ID));
}
/** Get Bank Statement Loader.
@return Definition of Bank Statement Loader (SWIFT, OFX) */
public int getC_BankStatementLoader_ID() 
{
Integer ii = (Integer)get_Value("C_BankStatementLoader_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BankStatementLoader_ID */
public static final String COLUMNNAME_C_BankStatementLoader_ID = "C_BankStatementLoader_ID";
/** Set Date Format.
@param DateFormat Date format used in the imput format */
public void setDateFormat (String DateFormat)
{
if (DateFormat != null && DateFormat.length() > 20)
{
log.warning("Length > 20 - truncated");
DateFormat = DateFormat.substring(0,19);
}
set_Value ("DateFormat", DateFormat);
}
/** Get Date Format.
@return Date format used in the imput format */
public String getDateFormat() 
{
return (String)get_Value("DateFormat");
}
/** Column name DateFormat */
public static final String COLUMNNAME_DateFormat = "DateFormat";
/** Set Date last run.
@param DateLastRun Date the process was last run. */
public void setDateLastRun (Timestamp DateLastRun)
{
set_Value ("DateLastRun", DateLastRun);
}
/** Get Date last run.
@return Date the process was last run. */
public Timestamp getDateLastRun() 
{
return (Timestamp)get_Value("DateLastRun");
}
/** Column name DateLastRun */
public static final String COLUMNNAME_DateLastRun = "DateLastRun";
/** Set Description.
@param Description Optional short description of the record */
public void setDescription (String Description)
{
if (Description != null && Description.length() > 255)
{
log.warning("Length > 255 - truncated");
Description = Description.substring(0,254);
}
set_Value ("Description", Description);
}
/** Get Description.
@return Optional short description of the record */
public String getDescription() 
{
return (String)get_Value("Description");
}
/** Column name Description */
public static final String COLUMNNAME_Description = "Description";
/** Set File Name.
@param FileName Name of the local file or URL */
public void setFileName (String FileName)
{
if (FileName != null && FileName.length() > 120)
{
log.warning("Length > 120 - truncated");
FileName = FileName.substring(0,119);
}
set_Value ("FileName", FileName);
}
/** Get File Name.
@return Name of the local file or URL */
public String getFileName() 
{
return (String)get_Value("FileName");
}
/** Column name FileName */
public static final String COLUMNNAME_FileName = "FileName";
/** Set Financial Institution ID.
@param FinancialInstitutionID The ID of the Financial Institution / Bank */
public void setFinancialInstitutionID (String FinancialInstitutionID)
{
if (FinancialInstitutionID != null && FinancialInstitutionID.length() > 20)
{
log.warning("Length > 20 - truncated");
FinancialInstitutionID = FinancialInstitutionID.substring(0,19);
}
set_Value ("FinancialInstitutionID", FinancialInstitutionID);
}
/** Get Financial Institution ID.
@return The ID of the Financial Institution / Bank */
public String getFinancialInstitutionID() 
{
return (String)get_Value("FinancialInstitutionID");
}
/** Column name FinancialInstitutionID */
public static final String COLUMNNAME_FinancialInstitutionID = "FinancialInstitutionID";
/** Set Host Address.
@param HostAddress Host Address URL or DNS */
public void setHostAddress (String HostAddress)
{
if (HostAddress != null && HostAddress.length() > 60)
{
log.warning("Length > 60 - truncated");
HostAddress = HostAddress.substring(0,59);
}
set_Value ("HostAddress", HostAddress);
}
/** Get Host Address.
@return Host Address URL or DNS */
public String getHostAddress() 
{
return (String)get_Value("HostAddress");
}
/** Column name HostAddress */
public static final String COLUMNNAME_HostAddress = "HostAddress";
/** Set Host port.
@param HostPort Host Communication Port */
public void setHostPort (int HostPort)
{
set_Value ("HostPort", Integer.valueOf(HostPort));
}
/** Get Host port.
@return Host Communication Port */
public int getHostPort() 
{
Integer ii = (Integer)get_Value("HostPort");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name HostPort */
public static final String COLUMNNAME_HostPort = "HostPort";
/** Set Name.
@param Name Alphanumeric identifier of the entity */
public void setName (String Name)
{
if (Name == null) throw new IllegalArgumentException ("Name is mandatory.");
if (Name.length() > 60)
{
log.warning("Length > 60 - truncated");
Name = Name.substring(0,59);
}
set_Value ("Name", Name);
}
/** Get Name.
@return Alphanumeric identifier of the entity */
public String getName() 
{
return (String)get_Value("Name");
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), getName());
}
/** Column name Name */
public static final String COLUMNNAME_Name = "Name";
/** Set PIN.
@param PIN Personal Identification Number */
public void setPIN (String PIN)
{
if (PIN != null && PIN.length() > 20)
{
log.warning("Length > 20 - truncated");
PIN = PIN.substring(0,19);
}
set_Value ("PIN", PIN);
}
/** Get PIN.
@return Personal Identification Number */
public String getPIN() 
{
return (String)get_Value("PIN");
}
/** Column name PIN */
public static final String COLUMNNAME_PIN = "PIN";
/** Set Password.
@param Password Password of any length (case sensitive) */
public void setPassword (String Password)
{
if (Password != null && Password.length() > 60)
{
log.warning("Length > 60 - truncated");
Password = Password.substring(0,59);
}
set_Value ("Password", Password);
}
/** Get Password.
@return Password of any length (case sensitive) */
public String getPassword() 
{
return (String)get_Value("Password");
}
/** Column name Password */
public static final String COLUMNNAME_Password = "Password";
/** Set Proxy address.
@param ProxyAddress  Address of your proxy server */
public void setProxyAddress (String ProxyAddress)
{
if (ProxyAddress != null && ProxyAddress.length() > 60)
{
log.warning("Length > 60 - truncated");
ProxyAddress = ProxyAddress.substring(0,59);
}
set_Value ("ProxyAddress", ProxyAddress);
}
/** Get Proxy address.
@return  Address of your proxy server */
public String getProxyAddress() 
{
return (String)get_Value("ProxyAddress");
}
/** Column name ProxyAddress */
public static final String COLUMNNAME_ProxyAddress = "ProxyAddress";
/** Set Proxy logon.
@param ProxyLogon Logon of your proxy server */
public void setProxyLogon (String ProxyLogon)
{
if (ProxyLogon != null && ProxyLogon.length() > 60)
{
log.warning("Length > 60 - truncated");
ProxyLogon = ProxyLogon.substring(0,59);
}
set_Value ("ProxyLogon", ProxyLogon);
}
/** Get Proxy logon.
@return Logon of your proxy server */
public String getProxyLogon() 
{
return (String)get_Value("ProxyLogon");
}
/** Column name ProxyLogon */
public static final String COLUMNNAME_ProxyLogon = "ProxyLogon";
/** Set Proxy password.
@param ProxyPassword Password of your proxy server */
public void setProxyPassword (String ProxyPassword)
{
if (ProxyPassword != null && ProxyPassword.length() > 60)
{
log.warning("Length > 60 - truncated");
ProxyPassword = ProxyPassword.substring(0,59);
}
set_Value ("ProxyPassword", ProxyPassword);
}
/** Get Proxy password.
@return Password of your proxy server */
public String getProxyPassword() 
{
return (String)get_Value("ProxyPassword");
}
/** Column name ProxyPassword */
public static final String COLUMNNAME_ProxyPassword = "ProxyPassword";
/** Set Proxy port.
@param ProxyPort Port of your proxy server */
public void setProxyPort (int ProxyPort)
{
set_Value ("ProxyPort", Integer.valueOf(ProxyPort));
}
/** Get Proxy port.
@return Port of your proxy server */
public int getProxyPort() 
{
Integer ii = (Integer)get_Value("ProxyPort");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name ProxyPort */
public static final String COLUMNNAME_ProxyPort = "ProxyPort";
/** Set Statement Loader Class.
@param StmtLoaderClass Class name of the bank statement loader */
public void setStmtLoaderClass (String StmtLoaderClass)
{
if (StmtLoaderClass != null && StmtLoaderClass.length() > 60)
{
log.warning("Length > 60 - truncated");
StmtLoaderClass = StmtLoaderClass.substring(0,59);
}
set_Value ("StmtLoaderClass", StmtLoaderClass);
}
/** Get Statement Loader Class.
@return Class name of the bank statement loader */
public String getStmtLoaderClass() 
{
return (String)get_Value("StmtLoaderClass");
}
/** Column name StmtLoaderClass */
public static final String COLUMNNAME_StmtLoaderClass = "StmtLoaderClass";
/** Set User ID.
@param UserID User ID or account number */
public void setUserID (String UserID)
{
if (UserID != null && UserID.length() > 60)
{
log.warning("Length > 60 - truncated");
UserID = UserID.substring(0,59);
}
set_Value ("UserID", UserID);
}
/** Get User ID.
@return User ID or account number */
public String getUserID() 
{
return (String)get_Value("UserID");
}
/** Column name UserID */
public static final String COLUMNNAME_UserID = "UserID";
}
