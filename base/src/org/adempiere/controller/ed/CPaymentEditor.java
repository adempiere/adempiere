/**
 * 
 */
package org.adempiere.controller.ed;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author Home
 *
 */
public interface CPaymentEditor {
	

	public final static String FIELD_payment = "paymentCombo";
	public final static String FIELD_kType = "kTypeCombo";
	public final static String FIELD_kNumber = "kNumberField";
	public final static String FIELD_kName = "kNameField";
	public final static String FIELD_kExp = "kExpField";
	public final static String FIELD_kApproval = "kApprovalField";
	public final static String FIELD_kAmount = "kAmountField";
	public final static String FIELD_tAccount = "tAccountCombo";
	public final static String FIELD_sCheckNumber = "sCheckNumberField";
	public final static String FIELD_sAccountNumber = "sAccountNumberField";
	public final static String FIELD_sRouting = "sRoutingField";
	public final static String FIELD_sCurrency = "sCurrencyCombo";
	public final static String FIELD_bCurrency = "bCurrencyCombo";
	public final static String FIELD_pTerm = "pTermCombo";
	public final static String FIELD_bAmount = "bAmountField";
	public final static String FIELD_sAmount = "sAmountField";
	public final static String FIELD_bDate = "bDateField";
	public final static String FIELD_sCheck = "sCheckField";
	public final static String FIELD_sBankAccount = "sBankAccountCombo";
	public final static String FIELD_bCashBook = "bCashBookCombo";
	public final static String FIELD_tAmount = "tAmountField";

	
	public String getPaymentRule();
	
	public int getBankAccount(String paymentRule);
	
	public int getCashBook(String paymentRule);
	
	public Timestamp getDateAcct(String paymentRule);
	
	public BigDecimal getPaymentAmount(String paymentRule);

	public String getCreditCardType(String paymentRule);
	
	public int getBPBankAccount(String paymentRule);
	
	public int getPaymentTerm(String paymentRule);

	public String getCreditCardNumber(String paymentRule);

	public String getCreditCardExpiry(String paymentRule);

	public String getCreditCardName(String paymentRule);

	public String getCheckAccountNumber(String paymentRule);

	public String getCheckRoutingNumber(String paymentRule);

	public String getCheckNumber(String paymentRule);
	
	public void setMandatory(String field, boolean mandatory);
	
	public void setError(String field, boolean error);

}
