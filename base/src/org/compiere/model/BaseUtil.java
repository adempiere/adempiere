package org.compiere.model;

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;

public class BaseUtil {


	/**
	 * Rounds the given amount to given precision. A precision of 2 will round to two decimals.
	 * A precision of -2 will round to nearest 100.
	 * 
	 * @param amount
	 * @param precision
	 * @return
	 */
	public static BigDecimal roundToPrecision(BigDecimal amount, int precision) {
		double multiplier = Math.pow(10.0, precision);
		amount = new BigDecimal(new Double(Math.round(amount.doubleValue()*multiplier)/multiplier).toString());
		return(amount);
	}
	
	/**
	 * Rounds the given amount according to rounding rule. For currency precision rounding
	 * please use {@link roundToPrecision(BigDecimal, int)}
	 * 
	 * @param amount
	 * @param roundingRule
	 * @return
	 */
	public static BigDecimal round(BigDecimal amount, String roundingRule) throws AdempiereException {

		if (roundingRule==null) return(amount);
		
		 // No rounding
		if ("N".equals(roundingRule)) {
			return(amount);
		}
		// Whole number
		if ("0".equals(roundingRule)) {
			amount = new BigDecimal(new Long(Math.round(amount.doubleValue())).toString());
			return(amount);
		}
		// Nickel 0.05, 0.10, 0.15 etc
		if ("5".equals(roundingRule)) {
			amount = new BigDecimal(new Double(Math.round(amount.doubleValue()*20.0)/20.0).toString()); 
		}
		// Ending in 9/5
		if ("9".equals(roundingRule)) {
			if (amount.doubleValue()%10<=5) {
				amount = new BigDecimal(new Double(Math.round(amount.doubleValue()) + (5-Math.round(amount.doubleValue())%10)).toString());
			} else {
				amount = new BigDecimal(new Double(Math.round(amount.doubleValue()) + (9-Math.round(amount.doubleValue())%10)).toString());
			}
		}
		// Dime .10, .20, .30 etc 
		if ("D".equals(roundingRule)) {
			amount = new BigDecimal(new Double(Math.round(amount.doubleValue()*10.0)/10.0).toString());
		}
		// Quarter .25, .50, .75 etc
		if ("Q".equals(roundingRule)) {
			amount = new BigDecimal(new Double(Math.round(amount.doubleValue()*4.0)/4.0).toString());
		}
		// Ten 10.00, 20.00 etc
		if ("T".equals(roundingRule)) {
			amount = new BigDecimal(new Double(Math.round(amount.doubleValue()/10.0)*10.0).toString());
		}
		if ("C".equals(roundingRule)) {
			throw new AdempiereException("Rounding rule not supported. Please use roundToPrecision.");
		}
		
		return(amount);
	}
	
	
}
