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
import java.util.Locale;

import org.compiere.util.Util;

/**
 * Parse String line from file to values of transaction
 * See: https://en.wikipedia.org/wiki/Quicken_Interchange_Format
 * Only supported basic label information by default, :
 * <li>D=Date. Leading zeroes on month and day can be skipped. Year can be either 4 digits or 2 digits or '6 (=2006).
 * <li>T=Amount of the item. For payments, a leading minus sign is required. For deposits, either no sign or a leading plus sign is accepted. Do not include currency symbols ($, £, ¥, etc.). Comma separators between thousands are allowed.
 * <li>U=Seems identical to T field (amount of item.) Both T and U are present in QIF files exported from Quicken 2015.
 * <li>M=Memo—any text you want to record about the item.
 * <li>C=Cleared status. Values are blank (not cleared), "*" or "c" (cleared) and "X" or "R" (reconciled).
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1700 ] Add Quicken Interchange Format support
 * @see https://github.com/adempiere/adempiere/issues/1700
 */
public abstract class QIFBankTransaction extends BankTransactionAbstract {
	/**	Start file text	*/
	public static final String FILE_START = "!Type:";
	/**	End Line	*/
	public static final String END_LINE = "^";
	
	/**	Cash Flow: Cash Account */
	public static final String HEAD_TYPE_Cash = "Cash";
	/**	Cash Flow: Checking & Savings Account */
	public static final String HEAD_TYPE_Bank = "Bank";
	/**	Cash Flow: Credit Card Account */
	public static final String HEAD_TYPE_CCard = "CCard";
	/**	Investing: Investment Account */
	public static final String HEAD_TYPE_Invst = "Invst";
	/**	Property & Debt: Asset */
	public static final String HEAD_TYPE_Oth_A = "Oth A";
	/**	Property & Debt: Liability */
	public static final String HEAD_TYPE_Oth_L = "Oth L";
	/**	Invoice (Quicken for Business only) */
	public static final String HEAD_TYPE_Invoice = "Invoice";
	
	/**	D	Date. Leading zeroes on month and day can be skipped. Year can be either 4 digits or 2 digits or '6 (=2006).	*/	
	public static final String LINE_TYPE_All_Date = "D";
	/**	Amount of the item. For payments, a leading minus sign is required. For deposits, either no sign or a leading plus sign is accepted. Do not include currency symbols ($, £, ¥, etc.). Comma separators between thousands are allowed.	*/	
	public static final String LINE_TYPE_All_Amount = "T";
	/**	Seems identical to T field (amount of item.) Both T and U are present in QIF files exported from Quicken 2015.	*/	
	public static final String LINE_TYPE_All_Amount_U = "U";
	/**	Memo—any text you want to record about the item.	*/	
	public static final String LINE_TYPE_All_Memo = "M";
	/**	Cleared status. Values are blank (not cleared), "*" or "c" (cleared) and "X" or "R" (reconciled).	*/	
	public static final String LINE_TYPE_All_Cleared_Status = "C";
	/**	Number of the check. Can also be "Deposit", "Transfer", "Print", "ATM", "EFT".	*/	
	public static final String LINE_TYPE_Banking_Check_Number = "N";
	/**	Payee. Or a description for deposits, transfers, etc.	*/	
	public static final String LINE_TYPE_Banking_Payee = "P";
	/**	Address of Payee. Up to 5 address lines are allowed. A 6th address line is a message that prints on the check. 1st line is normally the same as the Payee line—the name of the Payee.	*/	
	public static final String LINE_TYPE_Banking_Payee_Address = "A";
	/**	Category or Transfer and (optionally) Class. The literal values are those defined in the Quicken Category list. SubCategories can be indicated by a colon (":") followed by the subcategory literal. If the Quicken file uses Classes, this can be indicated by a slash ("/") followed by the class literal. For Investments, MiscIncX or MiscExpX actions, Category/class or transfer/class. (40 characters maximum)	*/	
	public static final String LINE_TYPE_Banking_Category = "L";
	/**	Flag this transaction as a reimbursable business expense.	*/	
	public static final String LINE_TYPE_Banking_Flag = "F";
	/**	Split category. Same format as L (Categorization) field. (40 characters maximum)	*/	
	public static final String LINE_TYPE_Splits_Category = "S";
	/**	Split memo—any text to go with this split item.	*/	
	public static final String LINE_TYPE_Splits_Memo = "E";
	/** Amount for this split of the item. Same format as T field.	*/	
	public static final String LINE_TYPE_Splits_Amount = "$";
	/** Percent. Optional—used if splits are done by percentage.	*/	
	public static final String LINE_TYPE_Splits_Percentage = "%";
	/** Investment Action (Buy, Sell, etc.).	*/	
	public static final String LINE_TYPE_Investment_Action = "N";
	/** Security name.	*/	
	public static final String LINE_TYPE_Investment_Name = "Y";
	/** Price.	*/	
	public static final String LINE_TYPE_Investment_Price = "I";
	/** Quantity of shares (or split ratio, if Action is StkSplit).	*/	
	public static final String LINE_TYPE_Investment_Quantity = "Q";
	/** Commission cost (generally found in stock trades)	*/	
	public static final String LINE_TYPE_Investment_Commission = "O";
	/** Amount transferred, if cash is moved between accounts	*/	
	public static final String LINE_TYPE_Investment_Amount = "$";
	/** Budgeted amount - may be repeated many times for monthly budgets.	*/	
	public static final String LINE_TYPE_Categories_Budgeted = "B";
	/** Extended data for Quicken Business. Followed by a second character subcode (see below) followed by content data.	*/	
	public static final String LINE_TYPE_Invoices_Extended_Data = "X";
	/** Ship-to address	*/	
	public static final String LINE_TYPE_Invoices_Ship_To = "XA";
	/** Invoice transaction type: 1 for invoice, 3 for payment	*/	
	public static final String LINE_TYPE_Invoices_Trx_Type = "XI";
	/** Invoice due date	*/	
	public static final String LINE_TYPE_Invoices_Due_Date = "XE";
	/** Tax account	*/	
	public static final String LINE_TYPE_Invoices_Tax_Account = "XC";
	/** Tax rate	*/	
	public static final String LINE_TYPE_Invoices_Tax_Rate = "XR";
	/** Tax amount	*/	
	public static final String LINE_TYPE_Invoices_Tax_Amount = "XT";
	/** Line item description	*/	
	public static final String LINE_TYPE_Invoices_Line_Item_Description = "XS";
	/** Line item category name	*/	
	public static final String LINE_TYPE_Invoices_Line_Item_Category = "XN";
	/** Line item quantity	*/	
	public static final String LINE_TYPE_Invoices_Line_Item_Quantity = "X#";
	/** Line item price per unit (multiply by X# for line item amount)	*/	
	public static final String LINE_TYPE_Invoices_Line_Item_Price = "X$";
	/** Line item taxable flag	*/	
	public static final String LINE_TYPE_Invoices_Line_Item_Taxable = "XF";
	
	/**	Amount Type */
	public static String[] TYPE_AMOUNT = {
			LINE_TYPE_All_Amount,
			LINE_TYPE_All_Amount_U,
			LINE_TYPE_Splits_Amount,
			LINE_TYPE_Splits_Percentage,
			LINE_TYPE_Investment_Price,
			LINE_TYPE_Investment_Quantity,
			LINE_TYPE_Investment_Commission,
			LINE_TYPE_Investment_Amount,
			LINE_TYPE_Categories_Budgeted,
			LINE_TYPE_Invoices_Tax_Rate,
			LINE_TYPE_Invoices_Tax_Amount,
			LINE_TYPE_Invoices_Line_Item_Quantity,
			LINE_TYPE_Invoices_Line_Item_Price,
			LINE_TYPE_Invoices_Line_Item_Taxable
	};
	
	/**	Date Type */
	public static String[] TYPE_DATE = {
			LINE_TYPE_All_Date,
			LINE_TYPE_Invoices_Due_Date
	};
	
	/**	String Type */
	public static String[] TYPE_STRING = {
			LINE_TYPE_All_Memo,
			LINE_TYPE_All_Cleared_Status,
			LINE_TYPE_Banking_Check_Number,
			LINE_TYPE_Banking_Payee,
			LINE_TYPE_Banking_Payee_Address,
			LINE_TYPE_Banking_Category,
			LINE_TYPE_Banking_Flag,
			LINE_TYPE_Splits_Category,
			LINE_TYPE_Splits_Memo,
			LINE_TYPE_Investment_Action,
			LINE_TYPE_Investment_Name,
			LINE_TYPE_Invoices_Extended_Data,
			LINE_TYPE_Invoices_Ship_To,
			LINE_TYPE_Invoices_Trx_Type,
			LINE_TYPE_Invoices_Tax_Account,
			LINE_TYPE_Invoices_Line_Item_Description,
			LINE_TYPE_Invoices_Line_Item_Category
	};
	
	/**	File Type	*/
	private String type;
	
	/**	
	 * Get Locale, it handle the parse of date and date format
	 * @return Locale
	 */
	public abstract Locale getLocale();
	
	/**
	 * Get Date Pattern
	 * @return Date Format
	 */
	public abstract String getDatePattern();
	
	/**
	 * Get Decimal Pattern
	 * @return Decimal Pattern
	 */
	public abstract String getDecimalPattern();
	
	/**
	 * Get Decimal Separator for parse String
	 * @return
	 */
	public abstract String getDecimalSeparator();
	
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
		if(line.startsWith(FILE_START)
				&& type != null) {
			index = line.indexOf(FILE_START) + FILE_START.length();
			setFileType(line.substring(index));
			return;
		}
		//	For Amount
		for(String prefix : TYPE_AMOUNT) {
			if(!line.startsWith(prefix)) {
				continue;
			}
			index = line.indexOf(prefix) + prefix.length();
			addValue(prefix, getNumber(getDecimalSeparator().charAt(0), getDecimalPattern(), line.substring(index)));
			return;
		}
		//	For Date
		for(String prefix : TYPE_DATE) {
			if(!line.startsWith(prefix)) {
				continue;
			}
			index = line.indexOf(prefix) + prefix.length();
			addValue(prefix, getDate(getDatePattern(), line.substring(index)));
			return;
		}
		//	For All
		for(String prefix : TYPE_STRING) {
			if(!line.startsWith(prefix)) {
				continue;
			}
			index = line.indexOf(prefix) + prefix.length();
			addValue(prefix, line.substring(index));
			return;
		}
	}
	
	/**
	 * Verify if is end of line
	 * @param line
	 * @return
	 */
	public boolean isEndTransactionLine(String line) {
		if(Util.isEmpty(line)) {
			return false;
		}
		//	For all
		return line.trim().startsWith(END_LINE);
	}
	
	/**
	 * Set File Type
	 * @param type
	 */
	protected void setFileType(String type) {
		this.type = type;
	}
	
	/**
	 * Get File Type
	 * @return
	 */
	protected String getFileType() {
		return type;
	}
	
	/**
	 * Get Bank Transaction Date
	 * @return
	 */
	public Timestamp getTrxDate() {
		return getDate(LINE_TYPE_All_Date);
	}
	
	/**
	 * Get Amount of transaction
	 * @return
	 */
	public BigDecimal getAmount() {
		return getNumber(LINE_TYPE_All_Amount);
	}

	/**
	 * Get Payee Account
	 * @return
	 */
	public String getPayeeAccountNo() {
		return getString(LINE_TYPE_Banking_Payee);
	}
	
	/**
	 * Get Memo of Transaction
	 * @return
	 */
	public String getMemo() {
		return getString(LINE_TYPE_All_Memo);
	}
	
	/**
	 * Get Category
	 * @return
	 */
	public String getTrxType() {
		return getString(LINE_TYPE_Banking_Category);
	}
	
	/**
	 * Get Check Numbers
	 * @return
	 */
	public String getCheckNo() {
		return getReferenceNo();
	}
	
	@Override
	public String getReferenceNo() {
		return getString(LINE_TYPE_Banking_Check_Number);
	}
	
	/**
	 * Process or change value for import
	 * you can implement it method for replace special characters
	 * @param value
	 * @return
	 */
	protected abstract String processValue(String value);

	@Override
	public Timestamp getValueDate() {
		return getDate(LINE_TYPE_All_Date);
	}

	@Override
	public Timestamp getStatementDate() {
		return getDate(LINE_TYPE_All_Date);
	}

	@Override
	public String getCurrency() {
		return null;
	}
	
	@Override
	public String getTrxCode() {
		return getString(LINE_TYPE_Banking_Category);
	}
}
