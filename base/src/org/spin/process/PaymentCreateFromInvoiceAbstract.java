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

package org.spin.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;

/** Generated Process for (Payment Generate (From Invoice Customer))
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class PaymentCreateFromInvoiceAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "SBP_PaymentGenerateFromInvoice";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Payment Generate (From Invoice Customer)";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54062;
	/**	Parameter Name for Target Document Type	*/
	public static final String C_DOCTYPETARGET_ID = "C_DocTypeTarget_ID";
	/**	Parameter Name for Document Date	*/
	public static final String DATEDOC = "DateDoc";
	/**	Parameter Name for Bank Account	*/
	public static final String C_BANKACCOUNT_ID = "C_BankAccount_ID";
	/**	Parameter Name for Payment date	*/
	public static final String PAYDATE = "PayDate";
	/**	Parameter Name for Tender type	*/
	public static final String TENDERTYPE = "TenderType";
	/**	Parameter Name for Payment amount	*/
	public static final String PAYAMT = "PayAmt";
	/**	Parameter Name for Over/Under Payment	*/
	public static final String ISOVERUNDERPAYMENT = "IsOverUnderPayment";
	/**	Parameter Name for Routing No	*/
	public static final String ROUTINGNO = "RoutingNo";
	/**	Parameter Name for Account No	*/
	public static final String ACCOUNTNO = "AccountNo";
	/**	Parameter Name for Check No	*/
	public static final String CHECKNO = "CheckNo";
	/**	Parameter Name for Micr	*/
	public static final String MICR = "Micr";
	/**	Parameter Name for Credit Card	*/
	public static final String CREDITCARDTYPE = "CreditCardType";
	/**	Parameter Name for Transaction Type	*/
	public static final String TRXTYPE = "TrxType";
	/**	Parameter Name for Number	*/
	public static final String CREDITCARDNUMBER = "CreditCardNumber";
	/**	Parameter Name for Verification Code	*/
	public static final String CREDITCARDVV = "CreditCardVV";
	/**	Parameter Name for Exp. Month	*/
	public static final String CREDITCARDEXPMM = "CreditCardExpMM";
	/**	Parameter Name for Exp. Year	*/
	public static final String CREDITCARDEXPYY = "CreditCardExpYY";
	/**	Parameter Name for Account Name	*/
	public static final String A_NAME = "A_Name";
	/**	Parameter Name for Account Street	*/
	public static final String A_STREET = "A_Street";
	/**	Parameter Name for Account City	*/
	public static final String A_CITY = "A_City";
	/**	Parameter Name for Account Zip/Postal	*/
	public static final String A_ZIP = "A_Zip";
	/**	Parameter Name for Account State	*/
	public static final String A_STATE = "A_State";
	/**	Parameter Name for Account Country	*/
	public static final String A_COUNTRY = "A_Country";
	/**	Parameter Name for Driver License	*/
	public static final String A_IDENT_DL = "A_Ident_DL";
	/**	Parameter Name for Social Security No	*/
	public static final String A_IDENT_SSN = "A_Ident_SSN";
	/**	Parameter Name for Account EMail	*/
	public static final String A_EMAIL = "A_EMail";
	/**	Parameter Name for Tax Amount	*/
	public static final String TAXAMT = "TaxAmt";
	/**	Parameter Name for PO Number	*/
	public static final String PONUM = "PONum";
	/**	Parameter Name for Voice authorization code	*/
	public static final String VOICEAUTHCODE = "VoiceAuthCode";
	/**	Parameter Name for Original Transaction ID	*/
	public static final String ORIG_TRXID = "Orig_TrxID";
	/**	Parameter Value for Target Document Type	*/
	private int docTypeTargetId;
	/**	Parameter Value for Document Date	*/
	private Timestamp dateDoc;
	/**	Parameter Value for Bank Account	*/
	private int bankAccountId;
	/**	Parameter Value for Payment date	*/
	private Timestamp payDate;
	/**	Parameter Value for Tender type	*/
	private String tenderType;
	/**	Parameter Value for Payment amount	*/
	private BigDecimal payAmt;
	/**	Parameter Value for Over/Under Payment	*/
	private boolean isOverUnderPayment;
	/**	Parameter Value for Routing No	*/
	private String routingNo;
	/**	Parameter Value for Account No	*/
	private String accountNo;
	/**	Parameter Value for Check No	*/
	private String checkNo;
	/**	Parameter Value for Micr	*/
	private String micr;
	/**	Parameter Value for Credit Card	*/
	private String creditCardType;
	/**	Parameter Value for Transaction Type	*/
	private String trxType;
	/**	Parameter Value for Number	*/
	private String creditCardNumber;
	/**	Parameter Value for Verification Code	*/
	private String creditCardVV;
	/**	Parameter Value for Exp. Month	*/
	private int creditCardExpMM;
	/**	Parameter Value for Exp. Year	*/
	private int creditCardExpYY;
	/**	Parameter Value for Account Name	*/
	private String name;
	/**	Parameter Value for Account Street	*/
	private String street;
	/**	Parameter Value for Account City	*/
	private String city;
	/**	Parameter Value for Account Zip/Postal	*/
	private String zip;
	/**	Parameter Value for Account State	*/
	private String state;
	/**	Parameter Value for Account Country	*/
	private String country;
	/**	Parameter Value for Driver License	*/
	private String identDL;
	/**	Parameter Value for Social Security No	*/
	private String identSSN;
	/**	Parameter Value for Account EMail	*/
	private String eMail;
	/**	Parameter Value for Tax Amount	*/
	private BigDecimal taxAmt;
	/**	Parameter Value for PO Number	*/
	private String pONum;
	/**	Parameter Value for Voice authorization code	*/
	private String voiceAuthCode;
	/**	Parameter Value for Original Transaction ID	*/
	private String trxID;

	@Override
	protected void prepare() {
		docTypeTargetId = getParameterAsInt(C_DOCTYPETARGET_ID);
		dateDoc = getParameterAsTimestamp(DATEDOC);
		bankAccountId = getParameterAsInt(C_BANKACCOUNT_ID);
		payDate = getParameterAsTimestamp(PAYDATE);
		tenderType = getParameterAsString(TENDERTYPE);
		payAmt = getParameterAsBigDecimal(PAYAMT);
		isOverUnderPayment = getParameterAsBoolean(ISOVERUNDERPAYMENT);
		routingNo = getParameterAsString(ROUTINGNO);
		accountNo = getParameterAsString(ACCOUNTNO);
		checkNo = getParameterAsString(CHECKNO);
		micr = getParameterAsString(MICR);
		creditCardType = getParameterAsString(CREDITCARDTYPE);
		trxType = getParameterAsString(TRXTYPE);
		creditCardNumber = getParameterAsString(CREDITCARDNUMBER);
		creditCardVV = getParameterAsString(CREDITCARDVV);
		creditCardExpMM = getParameterAsInt(CREDITCARDEXPMM);
		creditCardExpYY = getParameterAsInt(CREDITCARDEXPYY);
		name = getParameterAsString(A_NAME);
		street = getParameterAsString(A_STREET);
		city = getParameterAsString(A_CITY);
		zip = getParameterAsString(A_ZIP);
		state = getParameterAsString(A_STATE);
		country = getParameterAsString(A_COUNTRY);
		identDL = getParameterAsString(A_IDENT_DL);
		identSSN = getParameterAsString(A_IDENT_SSN);
		eMail = getParameterAsString(A_EMAIL);
		taxAmt = getParameterAsBigDecimal(TAXAMT);
		pONum = getParameterAsString(PONUM);
		voiceAuthCode = getParameterAsString(VOICEAUTHCODE);
		trxID = getParameterAsString(ORIG_TRXID);
	}

	/**	 Getter Parameter Value for Target Document Type	*/
	protected int getDocTypeTargetId() {
		return docTypeTargetId;
	}

	/**	 Setter Parameter Value for Target Document Type	*/
	protected void setDocTypeTargetId(int docTypeTargetId) {
		this.docTypeTargetId = docTypeTargetId;
	}

	/**	 Getter Parameter Value for Document Date	*/
	protected Timestamp getDateDoc() {
		return dateDoc;
	}

	/**	 Setter Parameter Value for Document Date	*/
	protected void setDateDoc(Timestamp dateDoc) {
		this.dateDoc = dateDoc;
	}

	/**	 Getter Parameter Value for Bank Account	*/
	protected int getBankAccountId() {
		return bankAccountId;
	}

	/**	 Setter Parameter Value for Bank Account	*/
	protected void setBankAccountId(int bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	/**	 Getter Parameter Value for Payment date	*/
	protected Timestamp getPayDate() {
		return payDate;
	}

	/**	 Setter Parameter Value for Payment date	*/
	protected void setPayDate(Timestamp payDate) {
		this.payDate = payDate;
	}

	/**	 Getter Parameter Value for Tender type	*/
	protected String getTenderType() {
		return tenderType;
	}

	/**	 Setter Parameter Value for Tender type	*/
	protected void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	/**	 Getter Parameter Value for Payment amount	*/
	protected BigDecimal getPayAmt() {
		return payAmt;
	}

	/**	 Setter Parameter Value for Payment amount	*/
	protected void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	/**	 Getter Parameter Value for Over/Under Payment	*/
	protected boolean isOverUnderPayment() {
		return isOverUnderPayment;
	}

	/**	 Setter Parameter Value for Over/Under Payment	*/
	protected void setIsOverUnderPayment(boolean isOverUnderPayment) {
		this.isOverUnderPayment = isOverUnderPayment;
	}

	/**	 Getter Parameter Value for Routing No	*/
	protected String getRoutingNo() {
		return routingNo;
	}

	/**	 Setter Parameter Value for Routing No	*/
	protected void setRoutingNo(String routingNo) {
		this.routingNo = routingNo;
	}

	/**	 Getter Parameter Value for Account No	*/
	protected String getAccountNo() {
		return accountNo;
	}

	/**	 Setter Parameter Value for Account No	*/
	protected void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**	 Getter Parameter Value for Check No	*/
	protected String getCheckNo() {
		return checkNo;
	}

	/**	 Setter Parameter Value for Check No	*/
	protected void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	/**	 Getter Parameter Value for Micr	*/
	protected String getMicr() {
		return micr;
	}

	/**	 Setter Parameter Value for Micr	*/
	protected void setMicr(String micr) {
		this.micr = micr;
	}

	/**	 Getter Parameter Value for Credit Card	*/
	protected String getCreditCardType() {
		return creditCardType;
	}

	/**	 Setter Parameter Value for Credit Card	*/
	protected void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**	 Getter Parameter Value for Transaction Type	*/
	protected String getTrxType() {
		return trxType;
	}

	/**	 Setter Parameter Value for Transaction Type	*/
	protected void setTrxType(String trxType) {
		this.trxType = trxType;
	}

	/**	 Getter Parameter Value for Number	*/
	protected String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**	 Setter Parameter Value for Number	*/
	protected void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**	 Getter Parameter Value for Verification Code	*/
	protected String getCreditCardVV() {
		return creditCardVV;
	}

	/**	 Setter Parameter Value for Verification Code	*/
	protected void setCreditCardVV(String creditCardVV) {
		this.creditCardVV = creditCardVV;
	}

	/**	 Getter Parameter Value for Exp. Month	*/
	protected int getCreditCardExpMM() {
		return creditCardExpMM;
	}

	/**	 Setter Parameter Value for Exp. Month	*/
	protected void setCreditCardExpMM(int creditCardExpMM) {
		this.creditCardExpMM = creditCardExpMM;
	}

	/**	 Getter Parameter Value for Exp. Year	*/
	protected int getCreditCardExpYY() {
		return creditCardExpYY;
	}

	/**	 Setter Parameter Value for Exp. Year	*/
	protected void setCreditCardExpYY(int creditCardExpYY) {
		this.creditCardExpYY = creditCardExpYY;
	}

	/**	 Getter Parameter Value for Account Name	*/
	protected String getName() {
		return name;
	}

	/**	 Setter Parameter Value for Account Name	*/
	protected void setName(String name) {
		this.name = name;
	}

	/**	 Getter Parameter Value for Account Street	*/
	protected String getStreet() {
		return street;
	}

	/**	 Setter Parameter Value for Account Street	*/
	protected void setStreet(String street) {
		this.street = street;
	}

	/**	 Getter Parameter Value for Account City	*/
	protected String getCity() {
		return city;
	}

	/**	 Setter Parameter Value for Account City	*/
	protected void setCity(String city) {
		this.city = city;
	}

	/**	 Getter Parameter Value for Account Zip/Postal	*/
	protected String getZip() {
		return zip;
	}

	/**	 Setter Parameter Value for Account Zip/Postal	*/
	protected void setZip(String zip) {
		this.zip = zip;
	}

	/**	 Getter Parameter Value for Account State	*/
	protected String getState() {
		return state;
	}

	/**	 Setter Parameter Value for Account State	*/
	protected void setState(String state) {
		this.state = state;
	}

	/**	 Getter Parameter Value for Account Country	*/
	protected String getCountry() {
		return country;
	}

	/**	 Setter Parameter Value for Account Country	*/
	protected void setCountry(String country) {
		this.country = country;
	}

	/**	 Getter Parameter Value for Driver License	*/
	protected String getIdentDL() {
		return identDL;
	}

	/**	 Setter Parameter Value for Driver License	*/
	protected void setIdentDL(String identDL) {
		this.identDL = identDL;
	}

	/**	 Getter Parameter Value for Social Security No	*/
	protected String getIdentSSN() {
		return identSSN;
	}

	/**	 Setter Parameter Value for Social Security No	*/
	protected void setIdentSSN(String identSSN) {
		this.identSSN = identSSN;
	}

	/**	 Getter Parameter Value for Account EMail	*/
	protected String getEMail() {
		return eMail;
	}

	/**	 Setter Parameter Value for Account EMail	*/
	protected void setEMail(String eMail) {
		this.eMail = eMail;
	}

	/**	 Getter Parameter Value for Tax Amount	*/
	protected BigDecimal getTaxAmt() {
		return taxAmt;
	}

	/**	 Setter Parameter Value for Tax Amount	*/
	protected void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	/**	 Getter Parameter Value for PO Number	*/
	protected String getPONum() {
		return pONum;
	}

	/**	 Setter Parameter Value for PO Number	*/
	protected void setPONum(String pONum) {
		this.pONum = pONum;
	}

	/**	 Getter Parameter Value for Voice authorization code	*/
	protected String getVoiceAuthCode() {
		return voiceAuthCode;
	}

	/**	 Setter Parameter Value for Voice authorization code	*/
	protected void setVoiceAuthCode(String voiceAuthCode) {
		this.voiceAuthCode = voiceAuthCode;
	}

	/**	 Getter Parameter Value for Original Transaction ID	*/
	protected String getTrxID() {
		return trxID;
	}

	/**	 Setter Parameter Value for Original Transaction ID	*/
	protected void setTrxID(String trxID) {
		this.trxID = trxID;
	}

	/**	 Getter Parameter Value for Process ID	*/
	public static final int getProcessId() {
		return ID_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Value	*/
	public static final String getProcessValue() {
		return VALUE_FOR_PROCESS;
	}

	/**	 Getter Parameter Value for Process Name	*/
	public static final String getProcessName() {
		return NAME_FOR_PROCESS;
	}
}