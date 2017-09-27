/**
 * 
 */
package org.compiere.acct;

import java.math.BigDecimal;

import org.compiere.model.MAccount;

/**
 * @author ancu
 *
 */
public final class FactUtil
{
	
	/**
	 * Create a simple acct transaction, as fellows:
	 * <pre>
	 * if signSensitive == true then
	 * 	if amt >= 0
	 * 		account_DR	DR			 amt
	 * 		account_CR		CR		 amt
	 *	if amt < 0
	 * 		account_CR	DR			-amt
	 * 		account_DR		CR		-amt
	 * if signSensitive == false then:
	 *		account_DR	DR			 amt
	 *		account_CR		CR		-amt
	 *		(same as when signSensitive==true and amt>=0)
	 * </pre>
	 * Note:
	 * <ul>
	 * <li>Operation index is automatically incremented 
	 * </ul>
	 * @param  docLine Document line or null
	 * @param  account_DR   DR account
	 * @param  account_CR   CR account
	 * @param  C_Currency_ID Currency
	 * @param  Amt amount
	 * @param  signSensitive if true, the DR and CR account will switch when amount is negative
	 * @return resulting two fact lines
	 * @category arhipac
	 */
	public static FactLine[] createSimpleOperation (
		Fact fact,
		DocLine docLine, 
		MAccount account_DR, MAccount account_CR,
		int C_Currency_ID, BigDecimal amt, boolean signSensitive)
	{
		FactLine[] lines = new FactLine[2];
		//newTrxIndex();
		if (signSensitive) {
			lines[0] = fact.createLine(docLine, account_DR, C_Currency_ID, amt);
			lines[1] = fact.createLine(docLine, account_CR, C_Currency_ID, amt.negate());
		}
		else {
			lines[0] = fact.createLine(docLine, account_DR, C_Currency_ID, amt, null);
			lines[1] = fact.createLine(docLine, account_CR, C_Currency_ID, null, amt);
		}
		//newTrxIndex();
		//
		return lines;
	}   //  createLine
}
