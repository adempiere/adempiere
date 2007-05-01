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
/** Generated Model for C_BankAccount
 *  @author Adempiere (generated) 
 *  @version Release 3.2.0 - $Id$ */
public class X_C_BankAccount extends PO
{
/** Standard Constructor
@param ctx context
@param C_BankAccount_ID id
@param trxName transaction
*/
public X_C_BankAccount (Properties ctx, int C_BankAccount_ID, String trxName)
{
super (ctx, C_BankAccount_ID, trxName);
/** if (C_BankAccount_ID == 0)
{
setAccountNo (null);
setBankAccountType (null);
setC_BankAccount_ID (0);
setC_Bank_ID (0);
setC_Currency_ID (0);
setCreditLimit (Env.ZERO);
setCurrentBalance (Env.ZERO);
setIsDefault (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BankAccount (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=297 */
public static final int Table_ID=MTable.getTable_ID("C_BankAccount");

/** TableName=C_BankAccount */
public static final String Table_Name="C_BankAccount";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BankAccount");

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
StringBuffer sb = new StringBuffer ("X_C_BankAccount[").append(get_ID()).append("]");
return sb.toString();
}
/** Set Account No.
@param AccountNo Account Number */
public void setAccountNo (String AccountNo)
{
if (AccountNo == null) throw new IllegalArgumentException ("AccountNo is mandatory.");
if (AccountNo.length() > 20)
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
/** Set BBAN.
@param BBAN Basic Bank Account Number */
public void setBBAN (String BBAN)
{
if (BBAN != null && BBAN.length() > 40)
{
log.warning("Length > 40 - truncated");
BBAN = BBAN.substring(0,39);
}
set_Value ("BBAN", BBAN);
}
/** Get BBAN.
@return Basic Bank Account Number */
public String getBBAN() 
{
return (String)get_Value("BBAN");
}
/** Column name BBAN */
public static final String COLUMNNAME_BBAN = "BBAN";

/** BankAccountType AD_Reference_ID=216 */
public static final int BANKACCOUNTTYPE_AD_Reference_ID=216;
/** Checking = C */
public static final String BANKACCOUNTTYPE_Checking = "C";
/** Savings = S */
public static final String BANKACCOUNTTYPE_Savings = "S";
/** Set Bank Account Type.
@param BankAccountType Bank Account Type */
public void setBankAccountType (String BankAccountType)
{
if (BankAccountType == null) throw new IllegalArgumentException ("BankAccountType is mandatory");
if (BankAccountType.equals("C") || BankAccountType.equals("S"));
 else throw new IllegalArgumentException ("BankAccountType Invalid value - " + BankAccountType + " - Reference_ID=216 - C - S");
if (BankAccountType.length() > 1)
{
log.warning("Length > 1 - truncated");
BankAccountType = BankAccountType.substring(0,0);
}
set_Value ("BankAccountType", BankAccountType);
}
/** Get Bank Account Type.
@return Bank Account Type */
public String getBankAccountType() 
{
return (String)get_Value("BankAccountType");
}
/** Column name BankAccountType */
public static final String COLUMNNAME_BankAccountType = "BankAccountType";
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
/** Set Bank.
@param C_Bank_ID Bank */
public void setC_Bank_ID (int C_Bank_ID)
{
if (C_Bank_ID < 1) throw new IllegalArgumentException ("C_Bank_ID is mandatory.");
set_ValueNoCheck ("C_Bank_ID", Integer.valueOf(C_Bank_ID));
}
/** Get Bank.
@return Bank */
public int getC_Bank_ID() 
{
Integer ii = (Integer)get_Value("C_Bank_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Get Record ID/ColumnName
@return ID/ColumnName pair
*/public KeyNamePair getKeyNamePair() 
{
return new KeyNamePair(get_ID(), String.valueOf(getC_Bank_ID()));
}
/** Column name C_Bank_ID */
public static final String COLUMNNAME_C_Bank_ID = "C_Bank_ID";
/** Set Currency.
@param C_Currency_ID The Currency for this record */
public void setC_Currency_ID (int C_Currency_ID)
{
if (C_Currency_ID < 1) throw new IllegalArgumentException ("C_Currency_ID is mandatory.");
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
/** Set Credit limit.
@param CreditLimit Amount of Credit allowed */
public void setCreditLimit (BigDecimal CreditLimit)
{
if (CreditLimit == null) throw new IllegalArgumentException ("CreditLimit is mandatory.");
set_Value ("CreditLimit", CreditLimit);
}
/** Get Credit limit.
@return Amount of Credit allowed */
public BigDecimal getCreditLimit() 
{
BigDecimal bd = (BigDecimal)get_Value("CreditLimit");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CreditLimit */
public static final String COLUMNNAME_CreditLimit = "CreditLimit";
/** Set Current balance.
@param CurrentBalance Current Balance */
public void setCurrentBalance (BigDecimal CurrentBalance)
{
if (CurrentBalance == null) throw new IllegalArgumentException ("CurrentBalance is mandatory.");
set_Value ("CurrentBalance", CurrentBalance);
}
/** Get Current balance.
@return Current Balance */
public BigDecimal getCurrentBalance() 
{
BigDecimal bd = (BigDecimal)get_Value("CurrentBalance");
if (bd == null) return Env.ZERO;
return bd;
}
/** Column name CurrentBalance */
public static final String COLUMNNAME_CurrentBalance = "CurrentBalance";
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
/** Set IBAN.
@param IBAN International Bank Account Number */
public void setIBAN (String IBAN)
{
if (IBAN != null && IBAN.length() > 40)
{
log.warning("Length > 40 - truncated");
IBAN = IBAN.substring(0,39);
}
set_Value ("IBAN", IBAN);
}
/** Get IBAN.
@return International Bank Account Number */
public String getIBAN() 
{
return (String)get_Value("IBAN");
}
/** Column name IBAN */
public static final String COLUMNNAME_IBAN = "IBAN";
/** Set Default.
@param IsDefault Default value */
public void setIsDefault (boolean IsDefault)
{
set_Value ("IsDefault", Boolean.valueOf(IsDefault));
}
/** Get Default.
@return Default value */
public boolean isDefault() 
{
Object oo = get_Value("IsDefault");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsDefault */
public static final String COLUMNNAME_IsDefault = "IsDefault";
}
