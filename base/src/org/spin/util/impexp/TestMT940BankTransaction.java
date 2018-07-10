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
public class TestMT940BankTransaction extends MT940BankTransaction {
	
	/**
	 * Set value from prefix
	 * @param key
	 * @param value
	 * @throws ParseException 
	 */
	protected void setValue(String key, String value) throws ParseException {
		if(HEAD_STATEMENT_DATE.equals(key)) {
			//	Statement Date
			addValue(HEAD_STATEMENT_DATE, getDate("ddMMyy", subString(value, 1, 6)));
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
			String trxType = subString(value, index, index +=1);
			addValue(LINE_TRANSACTION_Type, trxType);
			//	Currency
			// addValue(LINE_TRANSACTION_Currency_Char, subString(value, index, index += 1));
			//	Amount
			BigDecimal amount = getNumber(',', "########.##", subString(value, index, value.indexOf(LINE_TRANSACTION_NMSCNONREF)));
			if(!Util.isEmpty(trxType)
					&& trxType.equals(DEBT)
					&& amount != null) {
				amount = amount.negate();
			}
			addValue(LINE_TRANSACTION_Amount, amount);
			//	Add Transaction number
			addValue(LINE_TRANSACTION_DETAIL_Reference_Number, subString(value, value.indexOf(LINE_TRANSACTION_NMSCNONREF) + LINE_TRANSACTION_NMSCNONREF.length(), value.length()));
		} else if(LINE_BOOKING_TIME.equals(key)) {
			//	Booking Time
			addValue(LINE_BOOKING_TIME, getDate("hhmm", subString(value, 0, 4)));
		} else if(LINE_TRANSACTION_DETAIL.equals(key)) {
			int index = 0;
			//	Transaction Code
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Code, subString(value, index, index += 3));
			//	Transaction Title
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title, subString(value, index += 3, index += 40));
			//	Reconciliation Code
			addValue(LINE_TRANSACTION_DETAIL_Reconciliation_Code, subString(value, index += 1, index += 6));
			//	Transaction Title 1
			addValue(LINE_TRANSACTION_DETAIL_Transaction_Title1, subString(value, index += 3, index + 13));
			//	Reconciliation Code 1
			addValue(LINE_TRANSACTION_DETAIL_Reconciliation_Code1, subString(value, index += 9, index += 4));
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
}
