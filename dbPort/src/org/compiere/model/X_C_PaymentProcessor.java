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
/** Generated Model for C_PaymentProcessor
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_PaymentProcessor extends PO
{
/** Standard Constructor
@param ctx context
@param C_PaymentProcessor_ID id
@param trxName transaction
*/
public X_C_PaymentProcessor (Properties ctx, int C_PaymentProcessor_ID, String trxName)
{
super (ctx, C_PaymentProcessor_ID, trxName);
/** if (C_PaymentProcessor_ID == 0)
{
setAcceptAMEX (false);
setAcceptATM (false);
setAcceptCheck (false);
setAcceptCorporate (false);
setAcceptDiners (false);
setAcceptDirectDebit (false);
setAcceptDirectDeposit (false);
setAcceptDiscover (false);
setAcceptMC (false);
setAcceptVisa (false);
setC_BankAccount_ID (0);
setC_PaymentProcessor_ID (0);
setCommission (Env.ZERO);
setCostPerTrx (Env.ZERO);
setHostAddress (null);
setHostPort (0);
setName (null);
setPassword (null);
setRequireVV (false);
setUserID (null);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_PaymentProcessor (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=398 */
public static final int Table_ID=MTable.getTable_ID("C_PaymentProcessor");

/** TableName=C_PaymentProcessor */
public static final String Table_Name="C_PaymentProcessor";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_PaymentProcessor");

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
StringBuffer sb = new StringBuffer ("X_C_PaymentProcessor[").append(get_ID()).append("]");
return sb.toString();
}

/** AD_Sequence_ID AD_Reference_ID=128 */
public static final int AD_SEQUENCE_ID_AD_Reference_ID=128;
/** Set Sequence.
@param AD_Sequence_ID Document Sequence */
public void setAD_Sequence_ID (int AD_Sequence_ID)
{
if (AD_Sequence_ID <= 0) set_Value ("AD_Sequence_ID", null);
 else 
set_Value ("AD_Sequence_ID", Integer.valueOf(AD_Sequence_ID));
}
/** Get Sequence.
@return Document Sequence */
public int getAD_Sequence_ID() 
{
Integer ii = (Integer)get_Value("AD_Sequence_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_Sequence_ID */
public static final String COLUMNNAME_AD_Sequence_ID = "AD_Sequence_ID";
/** Set Accept AMEX.
@param AcceptAMEX Accept American Express Card */
public void setAcceptAMEX (boolean AcceptAMEX)
{
set_Value ("AcceptAMEX", Boolean.valueOf(AcceptAMEX));
}
/** Get Accept AMEX.
@return Accept American Express Card */
public boolean isAcceptAMEX() 
{
Object oo = get_Value("AcceptAMEX");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptAMEX */
public static final String COLUMNNAME_AcceptAMEX = "AcceptAMEX";
/** Set Accept ATM.
@param AcceptATM Accept Bank ATM Card */
public void setAcceptATM (boolean AcceptATM)
{
set_Value ("AcceptATM", Boolean.valueOf(AcceptATM));
}
/** Get Accept ATM.
@return Accept Bank ATM Card */
public boolean isAcceptATM() 
{
Object oo = get_Value("AcceptATM");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptATM */
public static final String COLUMNNAME_AcceptATM = "AcceptATM";
/** Set Accept Electronic Check.
@param AcceptCheck Accept ECheck (Electronic Checks) */
public void setAcceptCheck (boolean AcceptCheck)
{
set_Value ("AcceptCheck", Boolean.valueOf(AcceptCheck));
}
/** Get Accept Electronic Check.
@return Accept ECheck (Electronic Checks) */
public boolean isAcceptCheck() 
{
Object oo = get_Value("AcceptCheck");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptCheck */
public static final String COLUMNNAME_AcceptCheck = "AcceptCheck";
/** Set Accept Corporate.
@param AcceptCorporate Accept Corporate Purchase Cards */
public void setAcceptCorporate (boolean AcceptCorporate)
{
set_Value ("AcceptCorporate", Boolean.valueOf(AcceptCorporate));
}
/** Get Accept Corporate.
@return Accept Corporate Purchase Cards */
public boolean isAcceptCorporate() 
{
Object oo = get_Value("AcceptCorporate");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptCorporate */
public static final String COLUMNNAME_AcceptCorporate = "AcceptCorporate";
/** Set Accept Diners.
@param AcceptDiners Accept Diner's Club */
public void setAcceptDiners (boolean AcceptDiners)
{
set_Value ("AcceptDiners", Boolean.valueOf(AcceptDiners));
}
/** Get Accept Diners.
@return Accept Diner's Club */
public boolean isAcceptDiners() 
{
Object oo = get_Value("AcceptDiners");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptDiners */
public static final String COLUMNNAME_AcceptDiners = "AcceptDiners";
/** Set Accept Direct Debit.
@param AcceptDirectDebit Accept Direct Debits (vendor initiated) */
public void setAcceptDirectDebit (boolean AcceptDirectDebit)
{
set_Value ("AcceptDirectDebit", Boolean.valueOf(AcceptDirectDebit));
}
/** Get Accept Direct Debit.
@return Accept Direct Debits (vendor initiated) */
public boolean isAcceptDirectDebit() 
{
Object oo = get_Value("AcceptDirectDebit");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptDirectDebit */
public static final String COLUMNNAME_AcceptDirectDebit = "AcceptDirectDebit";
/** Set Accept Direct Deposit.
@param AcceptDirectDeposit Accept Direct Deposit (payee initiated) */
public void setAcceptDirectDeposit (boolean AcceptDirectDeposit)
{
set_Value ("AcceptDirectDeposit", Boolean.valueOf(AcceptDirectDeposit));
}
/** Get Accept Direct Deposit.
@return Accept Direct Deposit (payee initiated) */
public boolean isAcceptDirectDeposit() 
{
Object oo = get_Value("AcceptDirectDeposit");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptDirectDeposit */
public static final String COLUMNNAME_AcceptDirectDeposit = "AcceptDirectDeposit";
/** Set Accept Discover.
@param AcceptDiscover Accept Discover Card */
public void setAcceptDiscover (boolean AcceptDiscover)
{
set_Value ("AcceptDiscover", Boolean.valueOf(AcceptDiscover));
}
/** Get Accept Discover.
@return Accept Discover Card */
public boolean isAcceptDiscover() 
{
Object oo = get_Value("AcceptDiscover");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptDiscover */
public static final String COLUMNNAME_AcceptDiscover = "AcceptDiscover";
/** Set Accept MasterCard.
@param AcceptMC Accept Master Card */
public void setAcceptMC (boolean AcceptMC)
{
set_Value ("AcceptMC", Boolean.valueOf(AcceptMC));
}
/** Get Accept MasterCard.
@return Accept Master Card */
public boolean isAcceptMC() 
{
Object oo = get_Value("AcceptMC");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptMC */
public static final String COLUMNNAME_AcceptMC = "AcceptMC";
/** Set Accept Visa.
@param AcceptVisa Accept Visa Cards */
public void setAcceptVisa (boolean AcceptVisa)
{
set_Value ("AcceptVisa", Boolean.valueOf(AcceptVisa));
}
/** Get Accept Visa.
@return Accept Visa Cards */
public boolean isAcceptVisa() 
{
Object oo = get_Value("AcceptVisa");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name AcceptVisa */
public static final String COLUMNNAME_AcceptVisa = "AcceptVisa";
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
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID <= 0) set_Value ("C_Currency_ID", null);
 else 
set_Value ("C_Currency_ID", Integer.valueOf(C_Currency_ID));
}
/** Get Currency.
@return The Currency for this record */
public int getC_Currency_ID() 
{
Integer ii = (Integer)get_Value("C_Currency_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_Currency_ID */
public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";
/** Set Payment Processor.
@param C_PaymentProcessor_ID Payment processor for electronic payments */
public void setC_PaymentProcessor_ID (int C_PaymentProcessor_ID)
{
if (C_PaymentProcessor_ID < 1) throw new IllegalArgumentException ("C_PaymentProcessor_ID is mandatory.");
set_ValueNoCheck ("C_PaymentProcessor_ID", Integer.valueOf(C_PaymentProcessor_ID));
}
/** Get Payment Processor.
@return Payment processor for electronic payments */
public int getC_PaymentProcessor_ID() 
{
Integer ii = (Integer)get_Value("C_PaymentProcessor_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_PaymentProcessor_ID */
public static final String COLUMNNAME_C_PaymentProcessor_ID = "C_PaymentProcessor_ID";
/** Set Commission %.
@param Commission Commission stated as a percentage */
public void setCommission (BigDecimal Commission)
{
if (Commission == null) throw new IllegalArgumentException ("Commission is mandatory.");
set_Value ("Commission", Commission);
}
/** Get Commission %.
@return Commission stated as a percentage */
public BigDecimal getCommission() 
{
BigDecimal bd = (BigDecimal)get_Value("Commission");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name Commission */
public static final String COLUMNNAME_Commission = "Commission";
/** Set Cost per transaction.
@param CostPerTrx Fixed cost per transaction */
public void setCostPerTrx (BigDecimal CostPerTrx)
{
if (CostPerTrx == null) throw new IllegalArgumentException ("CostPerTrx is mandatory.");
set_Value ("CostPerTrx", CostPerTrx);
}
/** Get Cost per transaction.
@return Fixed cost per transaction */
public BigDecimal getCostPerTrx() 
{
BigDecimal bd = (BigDecimal)get_Value("CostPerTrx");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CostPerTrx */
public static final String COLUMNNAME_CostPerTrx = "CostPerTrx";
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
/** Set Host Address.
@param HostAddress Host Address URL or DNS */
public void setHostAddress (String HostAddress)
{
if (HostAddress == null) throw new IllegalArgumentException ("HostAddress is mandatory.");
if (HostAddress.length() > 60)
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
/** Set Minimum Amt.
@param MinimumAmt Minumum Amout in Document Currency */
public void setMinimumAmt (BigDecimal MinimumAmt)
{
set_Value ("MinimumAmt", MinimumAmt);
}
/** Get Minimum Amt.
@return Minumum Amout in Document Currency */
public BigDecimal getMinimumAmt() 
{
BigDecimal bd = (BigDecimal)get_Value("MinimumAmt");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name MinimumAmt */
public static final String COLUMNNAME_MinimumAmt = "MinimumAmt";
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
/** Set Partner ID.
@param PartnerID Partner ID or Account for the Payment Processor */
public void setPartnerID (String PartnerID)
{
if (PartnerID != null && PartnerID.length() > 60)
{
log.warning("Length > 60 - truncated");
PartnerID = PartnerID.substring(0,59);
}
set_Value ("PartnerID", PartnerID);
}
/** Get Partner ID.
@return Partner ID or Account for the Payment Processor */
public String getPartnerID() 
{
return (String)get_Value("PartnerID");
}
/** Column name PartnerID */
public static final String COLUMNNAME_PartnerID = "PartnerID";
/** Set Password.
@param Password Password of any length (case sensitive) */
public void setPassword (String Password)
{
if (Password == null) throw new IllegalArgumentException ("Password is mandatory.");
if (Password.length() > 60)
{
log.warning("Length > 60 - truncated");
Password = Password.substring(0,59);
}
set_ValueE ("Password", Password);
}
/** Get Password.
@return Password of any length (case sensitive) */
public String getPassword() 
{
return (String)get_ValueE("Password");
}
/** Column name Password */
public static final String COLUMNNAME_Password = "Password";
/** Set Payment Processor Class.
@param PayProcessorClass Payment Processor Java Class */
public void setPayProcessorClass (String PayProcessorClass)
{
if (PayProcessorClass != null && PayProcessorClass.length() > 60)
{
log.warning("Length > 60 - truncated");
PayProcessorClass = PayProcessorClass.substring(0,59);
}
set_Value ("PayProcessorClass", PayProcessorClass);
}
/** Get Payment Processor Class.
@return Payment Processor Java Class */
public String getPayProcessorClass() 
{
return (String)get_Value("PayProcessorClass");
}
/** Column name PayProcessorClass */
public static final String COLUMNNAME_PayProcessorClass = "PayProcessorClass";
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
set_ValueE ("ProxyPassword", ProxyPassword);
}
/** Get Proxy password.
@return Password of your proxy server */
public String getProxyPassword() 
{
return (String)get_ValueE("ProxyPassword");
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
/** Set Require CreditCard Verification Code.
@param RequireVV Require 3/4 digit Credit Verification Code */
public void setRequireVV (boolean RequireVV)
{
set_Value ("RequireVV", Boolean.valueOf(RequireVV));
}
/** Get Require CreditCard Verification Code.
@return Require 3/4 digit Credit Verification Code */
public boolean isRequireVV() 
{
Object oo = get_Value("RequireVV");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name RequireVV */
public static final String COLUMNNAME_RequireVV = "RequireVV";
/** Set User ID.
@param UserID User ID or account number */
public void setUserID (String UserID)
{
if (UserID == null) throw new IllegalArgumentException ("UserID is mandatory.");
if (UserID.length() > 60)
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
/** Set Vendor ID.
@param VendorID Vendor ID for the Payment Processor */
public void setVendorID (String VendorID)
{
if (VendorID != null && VendorID.length() > 60)
{
log.warning("Length > 60 - truncated");
VendorID = VendorID.substring(0,59);
}
set_Value ("VendorID", VendorID);
}
/** Get Vendor ID.
@return Vendor ID for the Payment Processor */
public String getVendorID() 
{
return (String)get_Value("VendorID");
}
/** Column name VendorID */
public static final String COLUMNNAME_VendorID = "VendorID";
}
