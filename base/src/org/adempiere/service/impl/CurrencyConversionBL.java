package org.adempiere.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.service.ICurrencyConversionBL;
import org.compiere.model.I_C_Currency;
import org.compiere.model.MClient;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class CurrencyConversionBL implements ICurrencyConversionBL
{
	@Override
	public I_C_Currency getBaseCurrency(final Properties ctx)
	{
		final int adClientId = Env.getAD_Client_ID(ctx);
		return getBaseCurrency(ctx, adClientId);
	}

	@Override
	public I_C_Currency getBaseCurrency(final Properties ctx, final int adClientId)
	{
		Util.assume(adClientId >= 0, "adClientId >= 0");
		
		final MClient client = MClient.get(ctx, adClientId);
		Util.assumeNotNull(client, "AD_Client found for ID={0}", adClientId);
		
		final int currencyId = client.getC_Currency_ID();
		final I_C_Currency currency = MCurrency.get(ctx, currencyId);
		Util.assumeNotNull(currency, "C_Currency found for ID={0}", currencyId);
		
		return currency;
	}
	
	@Override
	public BigDecimal convertBase(Properties ctx,
			BigDecimal Amt, int CurFrom_ID,
			Timestamp ConvDate, int C_ConversionType_ID,
			int AD_Client_ID, int AD_Org_ID)
	{
		return MConversionRate.convertBase(ctx, Amt, CurFrom_ID, ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}

	@Override
	public BigDecimal convert(Properties ctx,
			BigDecimal Amt, int CurFrom_ID, int CurTo_ID,
			Timestamp ConvDate, int C_ConversionType_ID,
			int AD_Client_ID, int AD_Org_ID)
	{
		return MConversionRate.convert(ctx, Amt, CurFrom_ID, CurTo_ID, ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}

	@Override
	public BigDecimal convert(Properties ctx,
			BigDecimal Amt, int CurFrom_ID, int CurTo_ID,
			int AD_Client_ID, int AD_Org_ID)
	{
		return MConversionRate.convert(ctx, Amt, CurFrom_ID, CurTo_ID, AD_Client_ID, AD_Org_ID);
	}

	@Override
	public BigDecimal getRate(int CurFrom_ID, int CurTo_ID,
			Timestamp ConvDate, int ConversionType_ID, int AD_Client_ID, int AD_Org_ID)
	{
		return MConversionRate.getRate(CurFrom_ID, CurTo_ID, ConvDate, ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}

}
