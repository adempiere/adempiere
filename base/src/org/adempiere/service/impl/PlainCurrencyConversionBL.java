package org.adempiere.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.ad.wrapper.IPOJOFilter;
import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ICurrencyConversionBL;
import org.adempiere.util.time.SystemTime;
import org.compiere.model.I_C_Currency;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.compiere.util.Util.ArrayKey;

public class PlainCurrencyConversionBL implements ICurrencyConversionBL
{
	private String defaultCurrencyIsoCode = "CHF";

	@Override
	public I_C_Currency getBaseCurrency(Properties ctx)
	{
		final int adClientId = Env.getAD_Client_ID(ctx);
		return getBaseCurrency(ctx, adClientId);
	}

	@Override
	public I_C_Currency getBaseCurrency(Properties ctx, int adClientId)
	{
		return getCreateCurrency(ctx, defaultCurrencyIsoCode);
	}

	@Override
	public BigDecimal convertBase(Properties ctx, BigDecimal Amt, int CurFrom_ID, Timestamp ConvDate, int C_ConversionType_ID, int AD_Client_ID, int AD_Org_ID)
	{
		final int CurTo_ID = getBaseCurrency(ctx, AD_Client_ID).getC_Currency_ID();
		return convert(ctx, Amt, CurFrom_ID, CurTo_ID, ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}

	@Override
	public BigDecimal convert(Properties ctx, BigDecimal Amt, int CurFrom_ID, int CurTo_ID, Timestamp ConvDate, int C_ConversionType_ID, int AD_Client_ID, int AD_Org_ID)
	{
		final BigDecimal rate = getRate(CurFrom_ID, CurTo_ID, ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);

		final I_C_Currency currencyTo = getCurrencyById(CurTo_ID);
		final BigDecimal amtConv = Amt.multiply(rate).setScale(currencyTo.getStdPrecision(), RoundingMode.HALF_UP);

		return amtConv;
	}

	@Override
	public BigDecimal convert(Properties ctx, BigDecimal Amt, int CurFrom_ID, int CurTo_ID, int AD_Client_ID, int AD_Org_ID)
	{
		final Timestamp ConvDate = SystemTime.asTimestamp();
		final int C_ConversionType_ID = 0;
		return convert(ctx, Amt, CurFrom_ID, CurTo_ID, ConvDate, C_ConversionType_ID, AD_Client_ID, AD_Org_ID);
	}

	private final Map<ArrayKey, BigDecimal> rates = new HashMap<ArrayKey, BigDecimal>();

	@Override
	public BigDecimal getRate(int CurFrom_ID, int CurTo_ID, Timestamp ConvDate, int ConversionType_ID, int AD_Client_ID, int AD_Org_ID)
	{
		if (CurFrom_ID == CurTo_ID)
		{
			return BigDecimal.ONE;
		}

		final ArrayKey key = new ArrayKey(CurFrom_ID, CurTo_ID);
		final BigDecimal rate = rates.get(key);
		if (rate == null)
		{
			throw new RuntimeException("Rate not found for currency=" + CurFrom_ID + "->" + CurTo_ID);
		}

		return rate;
	}

	public void setRate(final I_C_Currency currencyFrom, final I_C_Currency currencyTo, final BigDecimal rate)
	{
		final ArrayKey key = new ArrayKey(currencyFrom.getC_Currency_ID(), currencyTo.getC_Currency_ID());
		rates.put(key, rate);
	}

	public I_C_Currency getCreateCurrency(final Properties ctx, final String isoCode)
	{
		final POJOLookupMap db = POJOLookupMap.get();
		I_C_Currency currency = db.getFirstOnly(I_C_Currency.class, new IPOJOFilter<I_C_Currency>()
		{

			@Override
			public boolean accept(I_C_Currency pojo)
			{
				return Util.equals(pojo.getISO_Code(), isoCode);
			}
		});
		if (currency == null)
		{
			currency = db.newInstance(ctx, I_C_Currency.class);
			currency.setISO_Code(isoCode);
			currency.setCurSymbol(isoCode);
			currency.setStdPrecision(2);
			currency.setCostingPrecision(4);
			InterfaceWrapperHelper.save(currency);
		}
		return currency;
	}

	public I_C_Currency getCurrencyById(final int currencyId)
	{
		final POJOLookupMap db = POJOLookupMap.get();
		return db.lookup(I_C_Currency.class, currencyId);
	}
}
