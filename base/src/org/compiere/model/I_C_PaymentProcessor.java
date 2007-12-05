/**********************************************************************
 * This file is part of Adempiere ERP Bazaar                          *
 * http://www.adempiere.org                                           *
 *                                                                    *
 * Copyright (C) Trifon Trifonov.                                     *
 * Copyright (C) Contributors                                         *
 *                                                                    *
 * This program is free software;
 you can redistribute it and/or      *
 * modify it under the terms of the GNU General Public License        *
 * as published by the Free Software Foundation;
 either version 2     *
 * of the License, or (at your option) any later version.             *
 *                                                                    *
 * This program is distributed in the hope that it will be useful,    *
 * but WITHOUT ANY WARRANTY;
 without even the implied warranty of     *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the       *
 * GNU General Public License for more details.                       *
 *                                                                    *
 * You should have received a copy of the GNU General Public License  *
 * along with this program;
 if not, write to the Free Software        *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,         *
 * MA 02110-1301, USA.                                                *
 *                                                                    *
 * Contributors:                                                      *
 * - Trifon Trifonov (trifonnt@users.sourceforge.net)                 *
 *                                                                    *
 * Sponsors:                                                          *
 * - Company (http://www.site.com)                                    *
 **********************************************************************/
package org.compiere.model;

import java.math.BigDecimal;
import org.compiere.util.KeyNamePair;

/** Generated Interface for C_PaymentProcessor
 *  @author Trifon Trifonov (generated) 
 *  @version Release 3.3.1b
 */
public interface I_C_PaymentProcessor 
{

    /** TableName=C_PaymentProcessor */
    public static final String Table_Name = "C_PaymentProcessor";

    /** AD_Table_ID=398 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Sequence_ID */
    public static final String COLUMNNAME_AD_Sequence_ID = "AD_Sequence_ID";

	/** Set Sequence.
	  * Document Sequence
	  */
	public void setAD_Sequence_ID (int AD_Sequence_ID);

	/** Get Sequence.
	  * Document Sequence
	  */
	public int getAD_Sequence_ID();

    /** Column name AcceptAMEX */
    public static final String COLUMNNAME_AcceptAMEX = "AcceptAMEX";

	/** Set Accept AMEX.
	  * Accept American Express Card
	  */
	public void setAcceptAMEX (boolean AcceptAMEX);

	/** Get Accept AMEX.
	  * Accept American Express Card
	  */
	public boolean isAcceptAMEX();

    /** Column name AcceptATM */
    public static final String COLUMNNAME_AcceptATM = "AcceptATM";

	/** Set Accept ATM.
	  * Accept Bank ATM Card
	  */
	public void setAcceptATM (boolean AcceptATM);

	/** Get Accept ATM.
	  * Accept Bank ATM Card
	  */
	public boolean isAcceptATM();

    /** Column name AcceptCheck */
    public static final String COLUMNNAME_AcceptCheck = "AcceptCheck";

	/** Set Accept Electronic Check.
	  * Accept ECheck (Electronic Checks)
	  */
	public void setAcceptCheck (boolean AcceptCheck);

	/** Get Accept Electronic Check.
	  * Accept ECheck (Electronic Checks)
	  */
	public boolean isAcceptCheck();

    /** Column name AcceptCorporate */
    public static final String COLUMNNAME_AcceptCorporate = "AcceptCorporate";

	/** Set Accept Corporate.
	  * Accept Corporate Purchase Cards
	  */
	public void setAcceptCorporate (boolean AcceptCorporate);

	/** Get Accept Corporate.
	  * Accept Corporate Purchase Cards
	  */
	public boolean isAcceptCorporate();

    /** Column name AcceptDiners */
    public static final String COLUMNNAME_AcceptDiners = "AcceptDiners";

	/** Set Accept Diners.
	  * Accept Diner's Club
	  */
	public void setAcceptDiners (boolean AcceptDiners);

	/** Get Accept Diners.
	  * Accept Diner's Club
	  */
	public boolean isAcceptDiners();

    /** Column name AcceptDirectDebit */
    public static final String COLUMNNAME_AcceptDirectDebit = "AcceptDirectDebit";

	/** Set Accept Direct Debit.
	  * Accept Direct Debits (vendor initiated)
	  */
	public void setAcceptDirectDebit (boolean AcceptDirectDebit);

	/** Get Accept Direct Debit.
	  * Accept Direct Debits (vendor initiated)
	  */
	public boolean isAcceptDirectDebit();

    /** Column name AcceptDirectDeposit */
    public static final String COLUMNNAME_AcceptDirectDeposit = "AcceptDirectDeposit";

	/** Set Accept Direct Deposit.
	  * Accept Direct Deposit (payee initiated)
	  */
	public void setAcceptDirectDeposit (boolean AcceptDirectDeposit);

	/** Get Accept Direct Deposit.
	  * Accept Direct Deposit (payee initiated)
	  */
	public boolean isAcceptDirectDeposit();

    /** Column name AcceptDiscover */
    public static final String COLUMNNAME_AcceptDiscover = "AcceptDiscover";

	/** Set Accept Discover.
	  * Accept Discover Card
	  */
	public void setAcceptDiscover (boolean AcceptDiscover);

	/** Get Accept Discover.
	  * Accept Discover Card
	  */
	public boolean isAcceptDiscover();

    /** Column name AcceptMC */
    public static final String COLUMNNAME_AcceptMC = "AcceptMC";

	/** Set Accept MasterCard.
	  * Accept Master Card
	  */
	public void setAcceptMC (boolean AcceptMC);

	/** Get Accept MasterCard.
	  * Accept Master Card
	  */
	public boolean isAcceptMC();

    /** Column name AcceptVisa */
    public static final String COLUMNNAME_AcceptVisa = "AcceptVisa";

	/** Set Accept Visa.
	  * Accept Visa Cards
	  */
	public void setAcceptVisa (boolean AcceptVisa);

	/** Get Accept Visa.
	  * Accept Visa Cards
	  */
	public boolean isAcceptVisa();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public I_C_BankAccount getC_BankAccount() throws Exception;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public I_C_Currency getC_Currency() throws Exception;

    /** Column name C_PaymentProcessor_ID */
    public static final String COLUMNNAME_C_PaymentProcessor_ID = "C_PaymentProcessor_ID";

	/** Set Payment Processor.
	  * Payment processor for electronic payments
	  */
	public void setC_PaymentProcessor_ID (int C_PaymentProcessor_ID);

	/** Get Payment Processor.
	  * Payment processor for electronic payments
	  */
	public int getC_PaymentProcessor_ID();

    /** Column name Commission */
    public static final String COLUMNNAME_Commission = "Commission";

	/** Set Commission %.
	  * Commission stated as a percentage
	  */
	public void setCommission (BigDecimal Commission);

	/** Get Commission %.
	  * Commission stated as a percentage
	  */
	public BigDecimal getCommission();

    /** Column name CostPerTrx */
    public static final String COLUMNNAME_CostPerTrx = "CostPerTrx";

	/** Set Cost per transaction.
	  * Fixed cost per transaction
	  */
	public void setCostPerTrx (BigDecimal CostPerTrx);

	/** Get Cost per transaction.
	  * Fixed cost per transaction
	  */
	public BigDecimal getCostPerTrx();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name HostAddress */
    public static final String COLUMNNAME_HostAddress = "HostAddress";

	/** Set Host Address.
	  * Host Address URL or DNS
	  */
	public void setHostAddress (String HostAddress);

	/** Get Host Address.
	  * Host Address URL or DNS
	  */
	public String getHostAddress();

    /** Column name HostPort */
    public static final String COLUMNNAME_HostPort = "HostPort";

	/** Set Host port.
	  * Host Communication Port
	  */
	public void setHostPort (int HostPort);

	/** Get Host port.
	  * Host Communication Port
	  */
	public int getHostPort();

    /** Column name MinimumAmt */
    public static final String COLUMNNAME_MinimumAmt = "MinimumAmt";

	/** Set Minimum Amt.
	  * Minumum Amout in Document Currency
	  */
	public void setMinimumAmt (BigDecimal MinimumAmt);

	/** Get Minimum Amt.
	  * Minumum Amout in Document Currency
	  */
	public BigDecimal getMinimumAmt();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name PartnerID */
    public static final String COLUMNNAME_PartnerID = "PartnerID";

	/** Set Partner ID.
	  * Partner ID or Account for the Payment Processor
	  */
	public void setPartnerID (String PartnerID);

	/** Get Partner ID.
	  * Partner ID or Account for the Payment Processor
	  */
	public String getPartnerID();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name PayProcessorClass */
    public static final String COLUMNNAME_PayProcessorClass = "PayProcessorClass";

	/** Set Payment Processor Class.
	  * Payment Processor Java Class
	  */
	public void setPayProcessorClass (String PayProcessorClass);

	/** Get Payment Processor Class.
	  * Payment Processor Java Class
	  */
	public String getPayProcessorClass();

    /** Column name ProxyAddress */
    public static final String COLUMNNAME_ProxyAddress = "ProxyAddress";

	/** Set Proxy address.
	  *  Address of your proxy server
	  */
	public void setProxyAddress (String ProxyAddress);

	/** Get Proxy address.
	  *  Address of your proxy server
	  */
	public String getProxyAddress();

    /** Column name ProxyLogon */
    public static final String COLUMNNAME_ProxyLogon = "ProxyLogon";

	/** Set Proxy logon.
	  * Logon of your proxy server
	  */
	public void setProxyLogon (String ProxyLogon);

	/** Get Proxy logon.
	  * Logon of your proxy server
	  */
	public String getProxyLogon();

    /** Column name ProxyPassword */
    public static final String COLUMNNAME_ProxyPassword = "ProxyPassword";

	/** Set Proxy password.
	  * Password of your proxy server
	  */
	public void setProxyPassword (String ProxyPassword);

	/** Get Proxy password.
	  * Password of your proxy server
	  */
	public String getProxyPassword();

    /** Column name ProxyPort */
    public static final String COLUMNNAME_ProxyPort = "ProxyPort";

	/** Set Proxy port.
	  * Port of your proxy server
	  */
	public void setProxyPort (int ProxyPort);

	/** Get Proxy port.
	  * Port of your proxy server
	  */
	public int getProxyPort();

    /** Column name RequireVV */
    public static final String COLUMNNAME_RequireVV = "RequireVV";

	/** Set Require CreditCard Verification Code.
	  * Require 3/4 digit Credit Verification Code
	  */
	public void setRequireVV (boolean RequireVV);

	/** Get Require CreditCard Verification Code.
	  * Require 3/4 digit Credit Verification Code
	  */
	public boolean isRequireVV();

    /** Column name UserID */
    public static final String COLUMNNAME_UserID = "UserID";

	/** Set User ID.
	  * User ID or account number
	  */
	public void setUserID (String UserID);

	/** Get User ID.
	  * User ID or account number
	  */
	public String getUserID();

    /** Column name VendorID */
    public static final String COLUMNNAME_VendorID = "VendorID";

	/** Set Vendor ID.
	  * Vendor ID for the Payment Processor
	  */
	public void setVendorID (String VendorID);

	/** Get Vendor ID.
	  * Vendor ID for the Payment Processor
	  */
	public String getVendorID();
}
