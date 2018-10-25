/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.util.impexp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;

import org.compiere.util.Util;

/**
 * Parse String line from file to values of transaction
 * <table>
 * 	<body>
 * 		<tr>
 * 			<th>Format</th>
 * 			<th>Description</th>
 * 		</tr>
 * 		<tr>
 * 			<th>n</th>
 * 			<th>Only Digits: 0 1 2 3 4 5 6 7 8 9 </th>
 * 		</tr>
 * 		<tr>
 * 			<th>a</th>
 * 			<th>Only letters: A B C D E F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z Ś Ź ś ź Ł Ą Ż ł ą ż Ć Ę Ń Ó ć ę ń ó</th>
 * 		</tr>
 *  	<tr>
 * 			<th>c</th>
 * 			<th>Alphanumeric = digits + letters</th>
 * 		</tr>
 *  	<tr>
 * 			<th>x</th>
 * 			<th>space ' ( ) + , - . / 0 1 2 3 4 5 6 7 8 9 : ? A B C D E F G H I J K L M N O P Q R S T U V W X Y Z a b c d e f g h i j k l m n o p q r s t u v w x y z Ś Ź ś ź Ł Ą Ż ł ą ż Ć Ę Ń Ó ć ę ń ó </th>
 * 		</tr>
 *   	<tr>
 * 			<th>d</th>
 * 			<th>Amount – digits with coma ( , ) as decimal symbol</th>
 * 		</tr>
 * 	</body>
 * </table>
 * Note:  
 * <li>35x: means that there may be up to 35 characters from x group, including empty field.
 * <li>3!a: exactly 3 letters
 * <li>2n: up to 2 digits
 * <li>4*35x: up to 4 subfields up to 35 characters each. 
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1701 ] Add support to MT940 format
 * @see https://github.com/adempiere/adempiere/issues/1701
 */
public class MT940BankTransaction extends BankTransactionAbstract {
	/**	Timestamp of report generation in format DDDHHMM, where DDD is number of day in year and HHMM time. */
	public static final String HEAD_STATEMENT_DATE = ":20:";
	/**	Account number in IBAN form */
	public static final String HEAD_IBAN = ":25:";
	/**	Statement number */
	public static final String HEAD_STATEMENT_NUMBER = ":25C:";
	/**	Account owner name */
	public static final String HEAD_ACCOUNT_OWNER_NAME = ":NS:22";
	/**	Account name */
	public static final String HEAD_ACCOUNT_NAME = ":NS:23";
	/**	Opening balance  */
	public static final String HEAD_OPENING_BALANCE = ":60F:";
	/**	Sub Fields	*/
	/**	Debit/Credit indicator [1!a] 
	 * C = credit (credit balance) D = debit (debit balance) 	*/
	public static final String HEAD_OPENING_BALANCE_Type = HEAD_OPENING_BALANCE + "T|ype";
	/**	Date [YYMMDD]	*/
	public static final String HEAD_OPENING_BALANCE_Date = HEAD_OPENING_BALANCE + "|Date";
	/**	Currency [3!a]	*/
	public static final String HEAD_OPENING_BALANCE_Currency = HEAD_OPENING_BALANCE + "|Currency";
	/**	Amount [15d]	*/
	public static final String HEAD_OPENING_BALANCE_Amount = HEAD_OPENING_BALANCE + "|Amount";
	/**	Transaction line	*/	
	public static final String LINE_TRANSACTION = ":61:";
	/**	Sub Fields	*/
	/**	Value Date [YYMMDD]	*/
	public static final String LINE_TRANSACTION_Value_Date = LINE_TRANSACTION + "|ValueDate";
	/**	Value Date [MMDD]	*/
	public static final String LINE_TRANSACTION_Booking_Date = LINE_TRANSACTION + "|BookingDate";
	/**	Debit/Credit indicator [1!a] C = credit D = debit 	*/
	public static final String LINE_TRANSACTION_Type = LINE_TRANSACTION + "|Type";
	/**	3rd character of the currency code [1!a] 3rd character of the currency code (N for PLN)	*/
	public static final String LINE_TRANSACTION_Currency_Char = LINE_TRANSACTION + "|CurrencyChar";
	/**	Transaction amount [15d]	*/
	public static final String LINE_TRANSACTION_Amount = LINE_TRANSACTION + "|Amount";
	/**	Fixed value field NTRFNONREF//  [Always value NTRFNONREF//]	*/
	public static final String LINE_TRANSACTION_Fixed_Value = LINE_TRANSACTION + "|FixedValue";
	/**	Fixed Value	*/	
	public static final String LINE_TRANSACTION_NMSCNONREF = "NMSCNONREF//";
	/**	Booking Time [HHMM]	*/
	public static final String LINE_BOOKING_TIME = ":NS:19";
	/**	Transaction Detail	*/ 
	public static final String LINE_TRANSACTION_DETAIL = ":86:";
	/**	Sub Fields	*/
	/**	Transaction code [3!n] Numerical transaction code. List of codes available in separate document. Transaction code enables automatical recognition of transaction type	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Code = LINE_TRANSACTION_DETAIL + "|TransactionCode";
	/**	Transaction type [35x] Transaction type (description)	*/ 
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Type = LINE_TRANSACTION_DETAIL + "|TransactionType";
	/**	Sequence number [35x] Sequential number of transaction on account	*/
	public static final String LINE_TRANSACTION_DETAIL_Sequence_Number = LINE_TRANSACTION_DETAIL + "|SequenceNumber";
	/**	Transaction title [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title = LINE_TRANSACTION_DETAIL + "|TransactionTitle";
	/**	Transaction title + Continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title1 = LINE_TRANSACTION_DETAIL + "|TransactionTitle1";
	/**	Transaction title + Continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title2 = LINE_TRANSACTION_DETAIL + "|TransactionTitle2";
	/**	Transaction title + Continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title3 = LINE_TRANSACTION_DETAIL + "|TransactionTitle3";
	/**	Transaction title + Continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title4 = LINE_TRANSACTION_DETAIL + "|TransactionTitle4";
	/**	Transaction title + Continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title5 = LINE_TRANSACTION_DETAIL + "|TransactionTitle5";
	/**	Transaction title + Continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Transaction_Title6 = LINE_TRANSACTION_DETAIL + "|TransactionTitle6";
	/**	Counterparty name and address [35x]	Counterparty name and address	*/
	public static final String LINE_TRANSACTION_DETAIL_Counterparty_Address = LINE_TRANSACTION_DETAIL + "|CounterpartyAddress";
	/**	Counterparty name and address [35x]	Counterparty name and address	*/
	public static final String LINE_TRANSACTION_DETAIL_Counterparty_Address1 = LINE_TRANSACTION_DETAIL + "|CounterpartyAddress1";
	/**	Counterparty name and address [35x]	Counterparty name and address	*/
	public static final String LINE_TRANSACTION_DETAIL_Counterparty_Address2 = LINE_TRANSACTION_DETAIL + "|CounterpartyAddress2";
	/**	Technical field [10x] Technical field (no usage)	*/
	public static final String LINE_TRANSACTION_DETAIL_Technical_Field = LINE_TRANSACTION_DETAIL + "|TechnicalField";
	/**	Technical field [10x] Technical field (no usage)	*/
	public static final String LINE_TRANSACTION_DETAIL_Technical_Field1 = LINE_TRANSACTION_DETAIL + "|TechnicalField1";
	/**	Technical field [24x] Technical field (no usage)	*/
	public static final String LINE_TRANSACTION_DETAIL_Technical_Field2 = LINE_TRANSACTION_DETAIL + "|TechnicalField2";
	/**	Technical field [27x] Technical field (no usage)	*/
	public static final String LINE_TRANSACTION_DETAIL_Technical_Field3 = LINE_TRANSACTION_DETAIL + "|TechnicalField3";
	/**	Counterparty account [34x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Counterparty_Account = LINE_TRANSACTION_DETAIL + "|CounterpartyAccount";
	/**	Counterparty name and address - continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Counterparty_Account1 = LINE_TRANSACTION_DETAIL + "|CounterpartyAccount1";
	/**	Reconciliation code (Annotations)  [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Reconciliation_Code = LINE_TRANSACTION_DETAIL + "|ReconciliationCode";
	/**	Reconciliation code (Annotations) - continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Reconciliation_Code1 = LINE_TRANSACTION_DETAIL + "|ReconciliationCode1";
	/**	Transaction reference number given by bank [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Reference_Number = LINE_TRANSACTION_DETAIL + "|ReferenceNumber";
	/**	Reconciliation code (Annotations) - continuation [35x]	*/
	public static final String LINE_TRANSACTION_DETAIL_Reconciliation_Code2 = LINE_TRANSACTION_DETAIL + "|ReferenceNumber1";
	/**	Closing Balance	*/	
	public static final String LINE_CLOSING_BALANCE = ":62F:";
	/**	Debit/Credit indicator [1!a] C = credit (credit balance) D = debit (debit balance) 	*/
	public static final String LINE_CLOSING_BALANCE_Type = LINE_CLOSING_BALANCE + "|Type";
	/**	Date [YYMMDD] 	*/
	public static final String LINE_CLOSING_BALANCE_Date = LINE_CLOSING_BALANCE + "|Date";
	/**	Currency [3!a]	*/
	public static final String LINE_CLOSING_BALANCE_Currency = LINE_CLOSING_BALANCE + "|Currency";
	/**	Amount [15d] 	*/
	public static final String LINE_CLOSING_BALANCE_Amount = LINE_CLOSING_BALANCE + "|Amount";
	/**	Available Balance	*/	
	public static final String LINE_AVAILABLE_BALANCE = ":64:";
	/**	Debit/Credit indicator [1!a] C = credit (credit balance) D = debit (debit balance) 	*/
	public static final String LINE_AVAILABLE_BALANCE_Type = LINE_AVAILABLE_BALANCE + "|Type";
	/**	Date [YYMMDD] 	*/
	public static final String LINE_AVAILABLE_BALANCE_Date = LINE_AVAILABLE_BALANCE + "|Date";
	/**	Currency [3!a]	*/
	public static final String LINE_AVAILABLE_BALANCE_Currency = LINE_AVAILABLE_BALANCE + "|Currency";
	/**	Amount [15d] 	*/
	public static final String LINE_AVAILABLE_BALANCE_Amount = LINE_AVAILABLE_BALANCE + "|Amount";
	/**	Additional information [6*65x]	*/	
	public static final String LINE_TYPE_ADDITIONAL_INFORMATION = ":86:";
	/**	Debt Constant	*/
	public static final String DEBT = "D";
	/**	Credit Constant	*/
	public static final String CREDIT = "C";
	/**	Amount Type */
	public static String[] TAGS = {
			HEAD_STATEMENT_DATE,
			HEAD_IBAN,
			HEAD_STATEMENT_NUMBER,
			HEAD_ACCOUNT_OWNER_NAME,
			HEAD_ACCOUNT_NAME,
			HEAD_OPENING_BALANCE,
			LINE_TRANSACTION,
			LINE_BOOKING_TIME,
			LINE_TRANSACTION_DETAIL, 
			LINE_CLOSING_BALANCE,
			LINE_AVAILABLE_BALANCE,
			LINE_TYPE_ADDITIONAL_INFORMATION
	};
	
	/**
	 * Parse Line
	 * @param line
	 */
	public void parseLine(String line) throws Exception {
		line = processValue(line);
		if(Util.isEmpty(line)) {
			return;
		}
		//	
		int index = 0;
		//	For Amount
		for(String prefix : TAGS) {
			if(!line.startsWith(prefix)) {
				continue;
			}
			index = line.indexOf(prefix) + prefix.length();
			setValue(prefix, line.substring(index));
			return;
		}
	}
	
	/**
	 * Set value from prefix
	 * @param key
	 * @param value
	 * @throws ParseException 
	 */
	protected void setValue(String key, String value) throws ParseException {
		if(HEAD_STATEMENT_DATE.equals(key)) {
			//	
		} else if(HEAD_IBAN.equals(key)) {
			addValue(key, value);
		} else if(HEAD_STATEMENT_NUMBER.equals(key)) {
			addValue(key, getNumber('.', "#####", value));
		} else if(HEAD_ACCOUNT_OWNER_NAME.equals(key)) {
			addValue(key, subString(value, 0, 35));
		} else if(HEAD_ACCOUNT_NAME.equals(key)) {
			addValue(key, subString(value, 0, 35));
		} else if(HEAD_OPENING_BALANCE.equals(key)) {
			int index = 0;
			//	Type
			addValue(HEAD_OPENING_BALANCE_Type, subString(value, index, index += 1));
			//	Date
			addValue(HEAD_OPENING_BALANCE_Date, getDate("yyMMdd", subString(value, index, index += 6)));
			//	Currency
			addValue(HEAD_OPENING_BALANCE_Currency, subString(value, index, index += 3));
			//	Amount
			addValue(HEAD_OPENING_BALANCE_Amount, getNumber(',', "########.##", subString(value, index, value.length())));
		} else if(LINE_TRANSACTION.equals(key)) {
			int index = 0;
			//	Date
			addValue(LINE_TRANSACTION_Value_Date, getDate("yyMMdd", subString(value, index, index += 6)));
			//	Booking Date
			addValue(LINE_TRANSACTION_Booking_Date, getDate("MMdd", subString(value, index, index += 4)));
			//	Type
			addValue(LINE_TRANSACTION_Type, subString(value, index, index +=1));
			//	Currency
			// addValue(LINE_TRANSACTION_Currency_Char, subString(value, index, index += 1));
			//	Amount
			addValue(LINE_TRANSACTION_Amount, getNumber(',', "########.##", subString(value, index, index += 10)));
			//	Add Transaction number
			addValue(LINE_TRANSACTION_DETAIL_Reference_Number, subString(value, index += 12, index += 11));
		} else if(LINE_BOOKING_TIME.equals(key)) {
			//	Booking Time
			addValue(LINE_BOOKING_TIME, getDate("hhmm", subString(value, 0, 4)));
		} else if(LINE_TRANSACTION_DETAIL.equals(key)) {
			int index = 0;
			//	Transaction Code
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Code, subString(value, index, index += 3));
			//	Transaction Type
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Type, subString(value, index, index += 1));
			//	Transaction Number
			addValue(LINE_TRANSACTION_DETAIL_Sequence_Number, subString(value, index, index += 16));
			//	Transaction Title
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title, subString(value, index += 3, index += 40));
			//	Transaction Title 1
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title1, subString(value, index, index += 35));
			//	Transaction Title 2
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title2, subString(value, index, index += 35));
			//	Transaction Title 3
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title3, subString(value, index, index += 35));
			//	Transaction Title 4
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title4, subString(value, index, index += 35));
			//	Transaction Title 5
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title5, subString(value, index, index += 35));
			//	Transaction Title 6
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title6, subString(value, index, index += 35));
			//	Counterparty Address
			addValue(LINE_TRANSACTION_DETAIL_Counterparty_Address, subString(value, index, index += 35));
			//	Counterparty Address 1
			addValue(LINE_TRANSACTION_DETAIL_Counterparty_Address1, subString(value, index, index += 35));
			//	Counterparty Address 2
			addValue(LINE_TRANSACTION_DETAIL_Counterparty_Address2, subString(value, index, index += 35));
			//	Technical Comment
			addValue(LINE_TRANSACTION_DETAIL_Technical_Field, subString(value, index, index += 10));
			//	Technical Comment 1
			addValue(LINE_TRANSACTION_DETAIL_Technical_Field1, subString(value, index, index += 24));
			//	Technical Comment 2
			addValue(LINE_TRANSACTION_DETAIL_Technical_Field2, subString(value, index, index += 27));
			//	Technical Comment 3
			addValue(LINE_TRANSACTION_DETAIL_Technical_Field3, subString(value, index, index += 27));
			//	Counterparty Account
			addValue(LINE_TRANSACTION_DETAIL_Counterparty_Account, subString(value, index, index += 34));
			//	Counterparty Address
			addValue(LINE_TRANSACTION_DETAIL_Counterparty_Address, subString(value, index, index += 35));
			//	Reconciliation Code
			addValue(LINE_TRANSACTION_DETAIL_Reconciliation_Code, subString(value, index, index += 35));
			//	Reconciliation Code 1
			addValue(LINE_TRANSACTION_DETAIL_Reconciliation_Code1, subString(value, index, index += 35));
			//	Reference number
			addValue(LINE_TRANSACTION_DETAIL_Reference_Number, subString(value, index, index += 35));
			//	Reconciliation Code 2
			addValue(LINE_TRANSACTION_DETAIL_Reconciliation_Code2, subString(value, index, index += 35));
		} else if(LINE_CLOSING_BALANCE.equals(key)) {
			int index = 0;
			//	Transaction Type
			addValue(LINE_CLOSING_BALANCE_Type, subString(value, index, index += 1));
			//	Date
			addValue(LINE_CLOSING_BALANCE_Date, getDate("yyMMdd", subString(value, index, index += 6)));
			//	Currency
			addValue(LINE_CLOSING_BALANCE_Currency, subString(value, index, index += 3));
			//	Amount
			addValue(LINE_CLOSING_BALANCE_Amount, getNumber(',', "########.##", subString(value, index, value.length())));
		} else if(LINE_AVAILABLE_BALANCE.equals(key)) {
			int index = 0;
			//	Transaction Type
			addValue(LINE_AVAILABLE_BALANCE_Type, subString(value, index, index += 1));
			//	Date
			addValue(LINE_AVAILABLE_BALANCE_Date, getDate("yyMMdd", subString(value, index, index += 6)));
			//	Currency
			addValue(LINE_AVAILABLE_BALANCE_Currency, subString(value, index, index += 3));
			//	Amount
			addValue(LINE_AVAILABLE_BALANCE_Amount, getNumber(',', "########.##", subString(value, index, value.length())));
		} else if(LINE_TYPE_ADDITIONAL_INFORMATION.equals(key)) {
			addValue(LINE_TYPE_ADDITIONAL_INFORMATION, subString(value, 0, 65));
		}
	}
	
	/**
	 * Get Bank Transaction Date
	 * @return
	 */
	public Timestamp getTrxDate() {
		return getDate(LINE_TRANSACTION_Value_Date);
	}
	
	/**
	 * Get Amount of transaction
	 * @return
	 */
	public BigDecimal getAmount() {
		return getNumber(LINE_TRANSACTION_Amount);
	}

	/**
	 * Get Payee Account
	 * @return
	 */
	public String getPayeeAccountNo() {
		return getString(LINE_TRANSACTION_DETAIL_Counterparty_Account);
	}
	
	/**
	 * Get Memo of Transaction
	 * @return
	 */
	public String getMemo() {
		return getString(LINE_TRANSACTION_DETAIL_Transaction_Title);
	}
	
	/**
	 * Get Category
	 * @return
	 */
	public String getTrxType() {
		return getString(LINE_TRANSACTION_Type);
	}
	
	/**
	 * Get Check Numbers
	 * @return
	 */
	public String getCheckNo() {
		return getString(LINE_TRANSACTION_DETAIL_Reference_Number);
	}
	
	/**
	 * Process or change value for import
	 * you can implement it method for replace special characters
	 * @param value
	 * @return
	 */
	protected String processValue(String value) {
		return value.replaceAll("[	+^&áàäéèëíìïóòöúùñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ$><]", "");
	}

	@Override
	public boolean isEndTransactionLine(String line) {
		return line.startsWith(LINE_TRANSACTION_DETAIL);
	}
	
	@Override
	public boolean isCompleteData() {
		return true;
	}

	@Override
	public String getCurrency() {
		return getString(HEAD_OPENING_BALANCE_Currency);
	}

	@Override
	public Timestamp getValueDate() {
		return getDate(LINE_TRANSACTION_Value_Date);
	}

	@Override
	public Timestamp getStatementDate() {
		return getDate(HEAD_STATEMENT_DATE);
	}

	@Override
	public String getReferenceNo() {
		return getString(LINE_TRANSACTION_DETAIL_Reference_Number);
	}

	@Override
	public String getTrxCode() {
		return getString(LINE_TRANSACTION_DETAIL_Transaction_Code);
	}
	
	@Override
	public String getPayeeName() {
		return getString(LINE_TRANSACTION_DETAIL_Counterparty_Address);
	}

	@Override
	public String getPayeeDescription() {
		return getString(LINE_TRANSACTION_DETAIL_Counterparty_Address1);
	}
}
