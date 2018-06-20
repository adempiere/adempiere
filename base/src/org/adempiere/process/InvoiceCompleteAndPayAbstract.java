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

package org.adempiere.process;

import java.math.BigDecimal;
import org.compiere.process.SvrProcess;

/** Generated Process for (Invoice Complete And Pay)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public abstract class InvoiceCompleteAndPayAbstract extends SvrProcess {
	/** Process Value 	*/
	private static final String VALUE_FOR_PROCESS = "InvoiceCompleteAndPay";
	/** Process Name 	*/
	private static final String NAME_FOR_PROCESS = "Invoice Complete And Pay";
	/** Process Id 	*/
	private static final int ID_FOR_PROCESS = 54064;
	/**	Parameter Name for Bank Account	*/
	public static final String PARA_C_BANKACCOUNT_ID = "Para_C_BankAccount_ID";
	/**	Parameter Name for Payment Rule	*/
	public static final String PARA_PAYMENTRULE = "Para_PaymentRule";
	/**	Parameter Name for Bank Account Document	*/
	public static final String C_BANKACCOUNTDOC_ID = "C_BankAccountDoc_ID";
	/**	Parameter Name for Document Type	*/
	public static final String C_DOCTYPE_ID = "C_DocType_ID";
	/**	Parameter Name for Payment amount	*/
	public static final String PAYAMT = "PayAmt";
	/**	Parameter Name for Check No	*/
	public static final String CHECKNO = "CheckNo";
	/**	Parameter Name for Printed	*/
	public static final String ISPRINTED = "IsPrinted";
	/**	Parameter Name for Direct print	*/
	public static final String ISDIRECTPRINT = "IsDirectPrint";
	/**	Parameter Name for Credit Card	*/
	public static final String CREDITCARDTYPE = "CreditCardType";
	/**	Parameter Name for Number	*/
	public static final String CREDITCARDNUMBER = "CreditCardNumber";
	/**	Parameter Name for Description	*/
	public static final String DESCRIPTION = "Description";
	/**	Parameter Name for Currency Type	*/
	public static final String C_CONVERSIONTYPE_ID = "C_ConversionType_ID";
	/**	Parameter Value for Bank Account	*/
	private int cBankAccountId;
	/**	Parameter Value for Payment Rule	*/
	private String paymentRule;
	/**	Parameter Value for Bank Account Document	*/
	private int bankAccountDocId;
	/**	Parameter Value for Document Type	*/
	private int docTypeId;
	/**	Parameter Value for Payment amount	*/
	private BigDecimal payAmt;
	/**	Parameter Value for Check No	*/
	private int checkNo;
	/**	Parameter Value for Printed	*/
	private boolean isPrinted;
	/**	Parameter Value for Direct print	*/
	private boolean isDirectPrint;
	/**	Parameter Value for Credit Card	*/
	private String creditCardType;
	/**	Parameter Value for Number	*/
	private String creditCardNumber;
	/**	Parameter Value for Description	*/
	private String description;
	/**	Parameter Value for Currency Type	*/
	private int conversionTypeId;

	@Override
	protected void prepare() {
		cBankAccountId = getParameterAsInt(PARA_C_BANKACCOUNT_ID);
		paymentRule = getParameterAsString(PARA_PAYMENTRULE);
		bankAccountDocId = getParameterAsInt(C_BANKACCOUNTDOC_ID);
		docTypeId = getParameterAsInt(C_DOCTYPE_ID);
		payAmt = getParameterAsBigDecimal(PAYAMT);
		checkNo = getParameterAsInt(CHECKNO);
		isPrinted = getParameterAsBoolean(ISPRINTED);
		isDirectPrint = getParameterAsBoolean(ISDIRECTPRINT);
		creditCardType = getParameterAsString(CREDITCARDTYPE);
		creditCardNumber = getParameterAsString(CREDITCARDNUMBER);
		description = getParameterAsString(DESCRIPTION);
		conversionTypeId = getParameterAsInt(C_CONVERSIONTYPE_ID);
	}

	/**	 Getter Parameter Value for Bank Account	*/
	protected int getCBankAccountId() {
		return cBankAccountId;
	}

	/**	 Setter Parameter Value for Bank Account	*/
	protected void setCBankAccountId(int cBankAccountId) {
		this.cBankAccountId = cBankAccountId;
	}

	/**	 Getter Parameter Value for Payment Rule	*/
	protected String getPaymentRule() {
		return paymentRule;
	}

	/**	 Setter Parameter Value for Payment Rule	*/
	protected void setPaymentRule(String paymentRule) {
		this.paymentRule = paymentRule;
	}

	/**	 Getter Parameter Value for Bank Account Document	*/
	protected int getBankAccountDocId() {
		return bankAccountDocId;
	}

	/**	 Setter Parameter Value for Bank Account Document	*/
	protected void setBankAccountDocId(int bankAccountDocId) {
		this.bankAccountDocId = bankAccountDocId;
	}

	/**	 Getter Parameter Value for Document Type	*/
	protected int getDocTypeId() {
		return docTypeId;
	}

	/**	 Setter Parameter Value for Document Type	*/
	protected void setDocTypeId(int docTypeId) {
		this.docTypeId = docTypeId;
	}

	/**	 Getter Parameter Value for Payment amount	*/
	protected BigDecimal getPayAmt() {
		return payAmt;
	}

	/**	 Setter Parameter Value for Payment amount	*/
	protected void setPayAmt(BigDecimal payAmt) {
		this.payAmt = payAmt;
	}

	/**	 Getter Parameter Value for Check No	*/
	protected int getCheckNo() {
		return checkNo;
	}

	/**	 Setter Parameter Value for Check No	*/
	protected void setCheckNo(int checkNo) {
		this.checkNo = checkNo;
	}

	/**	 Getter Parameter Value for Printed	*/
	protected boolean isPrinted() {
		return isPrinted;
	}

	/**	 Setter Parameter Value for Printed	*/
	protected void setIsPrinted(boolean isPrinted) {
		this.isPrinted = isPrinted;
	}

	/**	 Getter Parameter Value for Direct print	*/
	protected boolean isDirectPrint() {
		return isDirectPrint;
	}

	/**	 Setter Parameter Value for Direct print	*/
	protected void setIsDirectPrint(boolean isDirectPrint) {
		this.isDirectPrint = isDirectPrint;
	}

	/**	 Getter Parameter Value for Credit Card	*/
	protected String getCreditCardType() {
		return creditCardType;
	}

	/**	 Setter Parameter Value for Credit Card	*/
	protected void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**	 Getter Parameter Value for Number	*/
	protected String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**	 Setter Parameter Value for Number	*/
	protected void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**	 Getter Parameter Value for Description	*/
	protected String getDescription() {
		return description;
	}

	/**	 Setter Parameter Value for Description	*/
	protected void setDescription(String description) {
		this.description = description;
	}

	/**	 Getter Parameter Value for Currency Type	*/
	protected int getConversionTypeId() {
		return conversionTypeId;
	}

	/**	 Setter Parameter Value for Currency Type	*/
	protected void setConversionTypeId(int conversionTypeId) {
		this.conversionTypeId = conversionTypeId;
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