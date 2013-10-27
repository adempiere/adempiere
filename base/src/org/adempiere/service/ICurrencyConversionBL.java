package org.adempiere.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_C_Currency;

/**
 * Currency conversion services.
 * 
 * @author tsa
 * 
 */
public interface ICurrencyConversionBL extends ISingletonService
{
	int DEFAULT_ConversionType_ID = 0;
	
	/**
	 * Gets base currency of tenant which is set in context.
	 * 
	 * @param ctx
	 * @return currency
	 */
	I_C_Currency getBaseCurrency(Properties ctx);

	/**
	 * Gets base currency of given tenant
	 * 
	 * @param ctx
	 * @return currency
	 */
	I_C_Currency getBaseCurrency(Properties ctx, int adClientId);

	/**
	 * Convert an amount to base Currency
	 * 
	 * @param ctx context
	 * @param CurFrom_ID The C_Currency_ID FROM
	 * @param ConvDate conversion date - if null - use current date
	 * @param C_ConversionType_ID conversion rate type - if 0 - use Default
	 * @param Amt amount to be converted
	 * @param AD_Client_ID client
	 * @param AD_Org_ID organization
	 * @return converted amount
	 */
	BigDecimal convertBase(Properties ctx, BigDecimal Amt, int CurFrom_ID, Timestamp ConvDate, int C_ConversionType_ID, int AD_Client_ID, int AD_Org_ID);

	/**
	 * Convert an amount
	 * 
	 * @param ctx context
	 * @param CurFrom_ID The C_Currency_ID FROM
	 * @param CurTo_ID The C_Currency_ID TO
	 * @param ConvDate conversion date - if null - use current date
	 * @param C_ConversionType_ID conversion rate type - if 0 - use Default
	 * @param Amt amount to be converted
	 * @param AD_Client_ID client
	 * @param AD_Org_ID organization
	 * @return converted amount or null if no rate
	 */
	BigDecimal convert(Properties ctx, BigDecimal Amt, int CurFrom_ID, int CurTo_ID, Timestamp ConvDate, int C_ConversionType_ID, int AD_Client_ID, int AD_Org_ID);

	/**
	 * Convert an amount with today's default rate
	 * 
	 * @param ctx context
	 * @param CurFrom_ID The C_Currency_ID FROM
	 * @param CurTo_ID The C_Currency_ID TO
	 * @param Amt amount to be converted
	 * @param AD_Client_ID client
	 * @param AD_Org_ID organization
	 * @return converted amount
	 */
	BigDecimal convert(Properties ctx, BigDecimal Amt, int CurFrom_ID, int CurTo_ID, int AD_Client_ID, int AD_Org_ID);

	/**
	 * Get Currency Conversion Rate
	 * 
	 * @param CurFrom_ID The C_Currency_ID FROM
	 * @param CurTo_ID The C_Currency_ID TO
	 * @param ConvDate The Conversion date - if null - use current date
	 * @param ConversionType_ID Conversion rate type - if 0 - use Default
	 * @param AD_Client_ID client
	 * @param AD_Org_ID organization
	 * @return currency Rate or null
	 */
	BigDecimal getRate(int CurFrom_ID, int CurTo_ID, Timestamp ConvDate, int ConversionType_ID, int AD_Client_ID, int AD_Org_ID);
}
