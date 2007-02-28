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
/** Generated Model for C_BP_BankAccount
 *  @author Adempiere (generated) 
 *  @version Release 3.1.5 - $Id$ */
public class X_C_BP_BankAccount extends PO
{
/** Standard Constructor
@param ctx context
@param C_BP_BankAccount_ID id
@param trxName transaction
*/
public X_C_BP_BankAccount (Properties ctx, int C_BP_BankAccount_ID, String trxName)
{
super (ctx, C_BP_BankAccount_ID, trxName);
/** if (C_BP_BankAccount_ID == 0)
{
setA_Name (null);
setC_BP_BankAccount_ID (0);
setC_BPartner_ID (0);
setIsACH (false);
}
 */
}
/** Load Constructor 
@param ctx context
@param rs result set 
@param trxName transaction
*/
public X_C_BP_BankAccount (Properties ctx, ResultSet rs, String trxName)
{
super (ctx, rs, trxName);
}
/** AD_Table_ID=298 */
public static final int Table_ID=MTable.getTable_ID("C_BP_BankAccount");

/** TableName=C_BP_BankAccount */
public static final String Table_Name="C_BP_BankAccount";

protected static KeyNamePair Model = new KeyNamePair(Table_ID,"C_BP_BankAccount");

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
StringBuffer sb = new StringBuffer ("X_C_BP_BankAccount[").append(get_ID()).append("]");
return sb.toString();
}
/** Set User/Contact.
@param AD_User_ID User within the system - Internal or Business Partner Contact */
public void setAD_User_ID (int AD_User_ID)
{
if (AD_User_ID <= 0) set_Value ("AD_User_ID", null);
 else 
set_Value ("AD_User_ID", Integer.valueOf(AD_User_ID));
}
/** Get User/Contact.
@return User within the system - Internal or Business Partner Contact */
public int getAD_User_ID() 
{
Integer ii = (Integer)get_Value("AD_User_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name AD_User_ID */
public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";
/** Set Account City.
@param A_City City or the Credit Card or Account Holder */
public void setA_City (String A_City)
{
if (A_City != null && A_City.length() > 60)
{
log.warning("Length > 60 - truncated");
A_City = A_City.substring(0,59);
}
set_Value ("A_City", A_City);
}
/** Get Account City.
@return City or the Credit Card or Account Holder */
public String getA_City() 
{
return (String)get_Value("A_City");
}
/** Column name A_City */
public static final String COLUMNNAME_A_City = "A_City";
/** Set Account Country.
@param A_Country Country */
public void setA_Country (String A_Country)
{
if (A_Country != null && A_Country.length() > 40)
{
log.warning("Length > 40 - truncated");
A_Country = A_Country.substring(0,39);
}
set_Value ("A_Country", A_Country);
}
/** Get Account Country.
@return Country */
public String getA_Country() 
{
return (String)get_Value("A_Country");
}
/** Column name A_Country */
public static final String COLUMNNAME_A_Country = "A_Country";
/** Set Account EMail.
@param A_EMail Email Address */
public void setA_EMail (String A_EMail)
{
if (A_EMail != null && A_EMail.length() > 60)
{
log.warning("Length > 60 - truncated");
A_EMail = A_EMail.substring(0,59);
}
set_Value ("A_EMail", A_EMail);
}
/** Get Account EMail.
@return Email Address */
public String getA_EMail() 
{
return (String)get_Value("A_EMail");
}
/** Column name A_EMail */
public static final String COLUMNNAME_A_EMail = "A_EMail";
/** Set Driver License.
@param A_Ident_DL Payment Identification - Driver License */
public void setA_Ident_DL (String A_Ident_DL)
{
if (A_Ident_DL != null && A_Ident_DL.length() > 20)
{
log.warning("Length > 20 - truncated");
A_Ident_DL = A_Ident_DL.substring(0,19);
}
set_Value ("A_Ident_DL", A_Ident_DL);
}
/** Get Driver License.
@return Payment Identification - Driver License */
public String getA_Ident_DL() 
{
return (String)get_Value("A_Ident_DL");
}
/** Column name A_Ident_DL */
public static final String COLUMNNAME_A_Ident_DL = "A_Ident_DL";
/** Set Social Security No.
@param A_Ident_SSN Payment Identification - Social Security No */
public void setA_Ident_SSN (String A_Ident_SSN)
{
if (A_Ident_SSN != null && A_Ident_SSN.length() > 20)
{
log.warning("Length > 20 - truncated");
A_Ident_SSN = A_Ident_SSN.substring(0,19);
}
set_Value ("A_Ident_SSN", A_Ident_SSN);
}
/** Get Social Security No.
@return Payment Identification - Social Security No */
public String getA_Ident_SSN() 
{
return (String)get_Value("A_Ident_SSN");
}
/** Column name A_Ident_SSN */
public static final String COLUMNNAME_A_Ident_SSN = "A_Ident_SSN";
/** Set Account Name.
@param A_Name Name on Credit Card or Account holder */
public void setA_Name (String A_Name)
{
if (A_Name == null) throw new IllegalArgumentException ("A_Name is mandatory.");
if (A_Name.length() > 60)
{
log.warning("Length > 60 - truncated");
A_Name = A_Name.substring(0,59);
}
set_Value ("A_Name", A_Name);
}
/** Get Account Name.
@return Name on Credit Card or Account holder */
public String getA_Name() 
{
return (String)get_Value("A_Name");
}
/** Column name A_Name */
public static final String COLUMNNAME_A_Name = "A_Name";
/** Set Account State.
@param A_State State of the Credit Card or Account holder */
public void setA_State (String A_State)
{
if (A_State != null && A_State.length() > 40)
{
log.warning("Length > 40 - truncated");
A_State = A_State.substring(0,39);
}
set_Value ("A_State", A_State);
}
/** Get Account State.
@return State of the Credit Card or Account holder */
public String getA_State() 
{
return (String)get_Value("A_State");
}
/** Column name A_State */
public static final String COLUMNNAME_A_State = "A_State";
/** Set Account Street.
@param A_Street Street address of the Credit Card or Account holder */
public void setA_Street (String A_Street)
{
if (A_Street != null && A_Street.length() > 60)
{
log.warning("Length > 60 - truncated");
A_Street = A_Street.substring(0,59);
}
set_Value ("A_Street", A_Street);
}
/** Get Account Street.
@return Street address of the Credit Card or Account holder */
public String getA_Street() 
{
return (String)get_Value("A_Street");
}
/** Column name A_Street */
public static final String COLUMNNAME_A_Street = "A_Street";
/** Set Account Zip/Postal.
@param A_Zip Zip Code of the Credit Card or Account Holder */
public void setA_Zip (String A_Zip)
{
if (A_Zip != null && A_Zip.length() > 20)
{
log.warning("Length > 20 - truncated");
A_Zip = A_Zip.substring(0,19);
}
set_Value ("A_Zip", A_Zip);
}
/** Get Account Zip/Postal.
@return Zip Code of the Credit Card or Account Holder */
public String getA_Zip() 
{
return (String)get_Value("A_Zip");
}
/** Column name A_Zip */
public static final String COLUMNNAME_A_Zip = "A_Zip";
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

/** BPBankAcctUse AD_Reference_ID=393 */
public static final int BPBANKACCTUSE_AD_Reference_ID=393;
/** Both = B */
public static final String BPBANKACCTUSE_Both = "B";
/** Direct Debit = D */
public static final String BPBANKACCTUSE_DirectDebit = "D";
/** None = N */
public static final String BPBANKACCTUSE_None = "N";
/** Direct Deposit = T */
public static final String BPBANKACCTUSE_DirectDeposit = "T";
/** Set Account Usage.
@param BPBankAcctUse Business Partner Bank Account usage */
public void setBPBankAcctUse (String BPBankAcctUse)
{
if (BPBankAcctUse == null || BPBankAcctUse.equals("B") || BPBankAcctUse.equals("D") || BPBankAcctUse.equals("N") || BPBankAcctUse.equals("T"));
 else throw new IllegalArgumentException ("BPBankAcctUse Invalid value - " + BPBankAcctUse + " - Reference_ID=393 - B - D - N - T");
if (BPBankAcctUse != null && BPBankAcctUse.length() > 1)
{
log.warning("Length > 1 - truncated");
BPBankAcctUse = BPBankAcctUse.substring(0,0);
}
set_Value ("BPBankAcctUse", BPBankAcctUse);
}
/** Get Account Usage.
@return Business Partner Bank Account usage */
public String getBPBankAcctUse() 
{
return (String)get_Value("BPBankAcctUse");
}
/** Column name BPBankAcctUse */
public static final String COLUMNNAME_BPBankAcctUse = "BPBankAcctUse";

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
if (BankAccountType == null || BankAccountType.equals("C") || BankAccountType.equals("S"));
 else throw new IllegalArgumentException ("BankAccountType Invalid value - " + BankAccountType + " - Reference_ID=216 - C - S");
if (BankAccountType != null && BankAccountType.length() > 1)
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
/** Set Partner Bank Account.
@param C_BP_BankAccount_ID Bank Account of the Business Partner */
public void setC_BP_BankAccount_ID (int C_BP_BankAccount_ID)
{
if (C_BP_BankAccount_ID < 1) throw new IllegalArgumentException ("C_BP_BankAccount_ID is mandatory.");
set_ValueNoCheck ("C_BP_BankAccount_ID", Integer.valueOf(C_BP_BankAccount_ID));
}
/** Get Partner Bank Account.
@return Bank Account of the Business Partner */
public int getC_BP_BankAccount_ID() 
{
Integer ii = (Integer)get_Value("C_BP_BankAccount_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BP_BankAccount_ID */
public static final String COLUMNNAME_C_BP_BankAccount_ID = "C_BP_BankAccount_ID";
/** Set Business Partner .
@param C_BPartner_ID Identifies a Business Partner */
public void setC_BPartner_ID (int C_BPartner_ID)
{
if (C_BPartner_ID < 1) throw new IllegalArgumentException ("C_BPartner_ID is mandatory.");
set_ValueNoCheck ("C_BPartner_ID", Integer.valueOf(C_BPartner_ID));
}
/** Get Business Partner .
@return Identifies a Business Partner */
public int getC_BPartner_ID() 
{
Integer ii = (Integer)get_Value("C_BPartner_ID");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name C_BPartner_ID */
public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";
/** Set Bank.
@param C_Bank_ID Bank */
public void setC_Bank_ID (int C_Bank_ID)
{
if (C_Bank_ID <= 0) set_Value ("C_Bank_ID", null);
 else 
set_Value ("C_Bank_ID", Integer.valueOf(C_Bank_ID));
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
/** Set Exp. Month.
@param CreditCardExpMM Expiry Month */
public void setCreditCardExpMM (int CreditCardExpMM)
{
set_Value ("CreditCardExpMM", Integer.valueOf(CreditCardExpMM));
}
/** Get Exp. Month.
@return Expiry Month */
public int getCreditCardExpMM() 
{
Integer ii = (Integer)get_Value("CreditCardExpMM");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CreditCardExpMM */
public static final String COLUMNNAME_CreditCardExpMM = "CreditCardExpMM";
/** Set Exp. Year.
@param CreditCardExpYY Expiry Year */
public void setCreditCardExpYY (int CreditCardExpYY)
{
set_Value ("CreditCardExpYY", Integer.valueOf(CreditCardExpYY));
}
/** Get Exp. Year.
@return Expiry Year */
public int getCreditCardExpYY() 
{
Integer ii = (Integer)get_Value("CreditCardExpYY");
if (ii == null) return 0;
return ii.intValue();
}
/** Column name CreditCardExpYY */
public static final String COLUMNNAME_CreditCardExpYY = "CreditCardExpYY";
/** Set Number.
@param CreditCardNumber Credit Card Number  */
public void setCreditCardNumber (String CreditCardNumber)
{
if (CreditCardNumber != null && CreditCardNumber.length() > 20)
{
log.warning("Length > 20 - truncated");
CreditCardNumber = CreditCardNumber.substring(0,19);
}
set_Value ("CreditCardNumber", CreditCardNumber);
}
/** Get Number.
@return Credit Card Number  */
public String getCreditCardNumber() 
{
return (String)get_Value("CreditCardNumber");
}
/** Column name CreditCardNumber */
public static final String COLUMNNAME_CreditCardNumber = "CreditCardNumber";

/** CreditCardType AD_Reference_ID=149 */
public static final int CREDITCARDTYPE_AD_Reference_ID=149;
/** Amex = A */
public static final String CREDITCARDTYPE_Amex = "A";
/** ATM = C */
public static final String CREDITCARDTYPE_ATM = "C";
/** Diners = D */
public static final String CREDITCARDTYPE_Diners = "D";
/** MasterCard = M */
public static final String CREDITCARDTYPE_MasterCard = "M";
/** Discover = N */
public static final String CREDITCARDTYPE_Discover = "N";
/** Purchase Card = P */
public static final String CREDITCARDTYPE_PurchaseCard = "P";
/** Visa = V */
public static final String CREDITCARDTYPE_Visa = "V";
/** Set Credit Card.
@param CreditCardType Credit Card (Visa, MC, AmEx) */
public void setCreditCardType (String CreditCardType)
{
if (CreditCardType == null || CreditCardType.equals("A") || CreditCardType.equals("C") || CreditCardType.equals("D") || CreditCardType.equals("M") || CreditCardType.equals("N") || CreditCardType.equals("P") || CreditCardType.equals("V"));
 else throw new IllegalArgumentException ("CreditCardType Invalid value - " + CreditCardType + " - Reference_ID=149 - A - C - D - M - N - P - V");
if (CreditCardType != null && CreditCardType.length() > 1)
{
log.warning("Length > 1 - truncated");
CreditCardType = CreditCardType.substring(0,0);
}
set_Value ("CreditCardType", CreditCardType);
}
/** Get Credit Card.
@return Credit Card (Visa, MC, AmEx) */
public String getCreditCardType() 
{
return (String)get_Value("CreditCardType");
}
/** Column name CreditCardType */
public static final String COLUMNNAME_CreditCardType = "CreditCardType";
/** Set Verification Code.
@param CreditCardVV Credit Card Verification code on credit card */
public void setCreditCardVV (String CreditCardVV)
{
if (CreditCardVV != null && CreditCardVV.length() > 4)
{
log.warning("Length > 4 - truncated");
CreditCardVV = CreditCardVV.substring(0,3);
}
set_Value ("CreditCardVV", CreditCardVV);
}
/** Get Verification Code.
@return Credit Card Verification code on credit card */
public String getCreditCardVV() 
{
return (String)get_Value("CreditCardVV");
}
/** Column name CreditCardVV */
public static final String COLUMNNAME_CreditCardVV = "CreditCardVV";
/** Set ACH.
@param IsACH Automatic Clearing House */
public void setIsACH (boolean IsACH)
{
set_Value ("IsACH", Boolean.valueOf(IsACH));
}
/** Get ACH.
@return Automatic Clearing House */
public boolean isACH() 
{
Object oo = get_Value("IsACH");
if (oo != null) 
{
 if (oo instanceof Boolean) return ((Boolean)oo).booleanValue();
 return "Y".equals(oo);
}
return false;
}
/** Column name IsACH */
public static final String COLUMNNAME_IsACH = "IsACH";

/** R_AvsAddr AD_Reference_ID=213 */
public static final int R_AVSADDR_AD_Reference_ID=213;
/** No Match = N */
public static final String R_AVSADDR_NoMatch = "N";
/** Unavailable = X */
public static final String R_AVSADDR_Unavailable = "X";
/** Match = Y */
public static final String R_AVSADDR_Match = "Y";
/** Set Address verified.
@param R_AvsAddr This address has been verified */
public void setR_AvsAddr (String R_AvsAddr)
{
if (R_AvsAddr == null || R_AvsAddr.equals("N") || R_AvsAddr.equals("X") || R_AvsAddr.equals("Y"));
 else throw new IllegalArgumentException ("R_AvsAddr Invalid value - " + R_AvsAddr + " - Reference_ID=213 - N - X - Y");
if (R_AvsAddr != null && R_AvsAddr.length() > 1)
{
log.warning("Length > 1 - truncated");
R_AvsAddr = R_AvsAddr.substring(0,0);
}
set_ValueNoCheck ("R_AvsAddr", R_AvsAddr);
}
/** Get Address verified.
@return This address has been verified */
public String getR_AvsAddr() 
{
return (String)get_Value("R_AvsAddr");
}
/** Column name R_AvsAddr */
public static final String COLUMNNAME_R_AvsAddr = "R_AvsAddr";

/** R_AvsZip AD_Reference_ID=213 */
public static final int R_AVSZIP_AD_Reference_ID=213;
/** No Match = N */
public static final String R_AVSZIP_NoMatch = "N";
/** Unavailable = X */
public static final String R_AVSZIP_Unavailable = "X";
/** Match = Y */
public static final String R_AVSZIP_Match = "Y";
/** Set Zip verified.
@param R_AvsZip The Zip Code has been verified */
public void setR_AvsZip (String R_AvsZip)
{
if (R_AvsZip == null || R_AvsZip.equals("N") || R_AvsZip.equals("X") || R_AvsZip.equals("Y"));
 else throw new IllegalArgumentException ("R_AvsZip Invalid value - " + R_AvsZip + " - Reference_ID=213 - N - X - Y");
if (R_AvsZip != null && R_AvsZip.length() > 1)
{
log.warning("Length > 1 - truncated");
R_AvsZip = R_AvsZip.substring(0,0);
}
set_ValueNoCheck ("R_AvsZip", R_AvsZip);
}
/** Get Zip verified.
@return The Zip Code has been verified */
public String getR_AvsZip() 
{
return (String)get_Value("R_AvsZip");
}
/** Column name R_AvsZip */
public static final String COLUMNNAME_R_AvsZip = "R_AvsZip";
/** Set Routing No.
@param RoutingNo Bank Routing Number */
public void setRoutingNo (String RoutingNo)
{
if (RoutingNo != null && RoutingNo.length() > 20)
{
log.warning("Length > 20 - truncated");
RoutingNo = RoutingNo.substring(0,19);
}
set_Value ("RoutingNo", RoutingNo);
}
/** Get Routing No.
@return Bank Routing Number */
public String getRoutingNo() 
{
return (String)get_Value("RoutingNo");
}
/** Column name RoutingNo */
public static final String COLUMNNAME_RoutingNo = "RoutingNo";
}
