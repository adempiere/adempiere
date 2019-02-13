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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.compiere.util.Env;
import org.compiere.util.Util;

/**
 * Abstract class for handle helper method
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 1700 ] Add Quicken Interchange Format support
 * @see https://github.com/adempiere/adempiere/issues/1700
 */
public abstract class BankTransactionAbstract implements TrxDataInterface {
	/**	Values from file	*/
	private HashMap<String, Object> values = new HashMap<>();
	
	/**
	 * Validate if transaction data is ending
	 * @param line
	 * @return
	 */
	public abstract boolean isEndTransactionLine(String line);
	
	/**
	 * Add value with a key
	 * @param key
	 * @param value
	 */
	protected void addValue(String key, Object value) {
		values.put(key, value);
	}
	
	/**
	 * Get value from key
	 * @param key
	 * @return
	 */
	protected Object getValue(String key) {
		return values.get(key);
	}
	
	/**
	 * Helper method for get Amount
	 * @param key
	 * @return
	 */
	protected BigDecimal getNumber(String key) {
		Object value = getValue(key);
		BigDecimal bigDecimalValue = Env.ZERO;
        if (value != null) {
            if (value instanceof BigDecimal) {
            	bigDecimalValue = (BigDecimal) value;
            } else if(value instanceof Double) {
            	bigDecimalValue = new BigDecimal((Double) value);
            } else if(value instanceof Float) {
            	bigDecimalValue = new BigDecimal((Float) value);
            } else if(value instanceof Integer) {
            	bigDecimalValue = new BigDecimal((Integer) value);
            }
        }
        //  Default
        return bigDecimalValue;
	}
	
	/**
	 * Like SubString but validate
	 * @param value
	 * @param cutPosition
	 * @return
	 */
	protected String subString(String value, int fromPosition, int toPosition) {
		if(Util.isEmpty(value)) {
			return null;
		}
		if(fromPosition < 0) {
			return null;
		}
		if(fromPosition >= toPosition) {
			return null;
		}
		if(value.length() >= toPosition) {
			return value.substring(fromPosition, toPosition);
		}
		//	Default
		return value;
	}
	
	/**
	 * Get Amount from String
	 * @param separator
	 * @param pattern
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	protected BigDecimal getNumber(char separator, String pattern, String value) throws ParseException {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(separator);
		if(separator == '.') {
			symbols.setDecimalSeparator(',');
		} else {
			symbols.setDecimalSeparator('.');
		}
		//	Instance it
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		//	Parse
		return (BigDecimal)decimalFormat.parse(value);
	}
	
	/**
	 * Get Date from format Pattern and value
	 * @param pattern
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	protected Timestamp getDate(String pattern, String value) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		//	Parse
		Date date = dateFormat.parse(value);
		return new Timestamp(date.getTime());
	}
	
	/**
	 * Helper method for get date from key
	 * @param key
	 * @return
	 */
	protected Timestamp getDate(String key) {
		Object value = getValue(key);
		if (value == null)
            return null;
        return (Timestamp) value;
	}
	
	/**
	 * Helper method for get string
	 * @param key
	 * @return
	 */
	protected String getString(String key) {
		Object value = getValue(key);
		if (value == null)
            return null;
        return value.toString();
	}
	
	/**
	 * Parse Line
	 * @param line
	 */
	public void parseLine(String line) throws Exception {
		line = processValue(line);
		if(Util.isEmpty(line)) {
			return;
		}
	}
	
	/**
	 * Process or change value for import
	 * you can implement it method for replace special characters
	 * @param value
	 * @return
	 */
	protected abstract String processValue(String value);
	
	/**
	 * Validate if data is complete for save
	 * @return
	 */
	public abstract boolean isCompleteData();
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for(Map.Entry<String, Object> entry : values.entrySet()) {
			buffer.append("Key=").append(entry.getKey()).append(", Value=").append(entry.getValue()).append(Env.NL);
		}
		//	Return 
		return buffer.toString();
	}
}
