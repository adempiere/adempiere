package org.compiere.util;


/**
 * Evaluator source
 * Extend Evaluatee interface with more methods
 * @author tsa
 *
 */
public interface Evaluatee2 extends Evaluatee
{
	/**
	 * Check if variableName exists.
	 * Note: that in case when is not sure if the variable exist, the implementation of this method should return true
	 * @param variableName
	 * @return true if the variable exists 
	 */
	public boolean has_Variable(String variableName);
	
	/**
	 * 	Get Old Variable Value
	 *	@param variableName name
	 *	@return value
	 */
	public String get_ValueOldAsString (String variableName);
}
