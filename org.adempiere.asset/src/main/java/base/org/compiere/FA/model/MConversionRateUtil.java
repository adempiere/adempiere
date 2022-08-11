/**
 * 
 */
package org.compiere.FA.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import org.compiere.model.MClient;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.adempiere.exceptions.NoCurrencyConversionException;

/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class MConversionRateUtil
{
	/**	Logger						*/
	private static final CLogger s_log = CLogger.getCLogger (MConversionRateUtil.class);
	
	public MConversionRateUtil()
	{
		// nothing
	}
	
	/**
	 * Convert an amount to base currency and update model fields (CurrencyRate, "AmtName").
	 * @param	model
	 * @param	DateName	conversion date field name
	 * @param	SourceAmtName	source amount field name
	 * @param	AmtName	converted amount field name (optional); if null then the converted amount field will not be updated
	 * @param	changedColumnName	the column that has changed (the controller); optional
	 * @return	converted amount or null if error
	 */
	public static BigDecimal convertBase(SetGetModel model, String DateName,
			String SourceAmtName, String AmtName, String changedColumnName)
	{
		// If Currency changed, reset rate
		if (changedColumnName != null && "C_Currency_ID".equalsIgnoreCase(changedColumnName))
		{
			model.set_AttrValue("CurrencyRate", Env.ZERO);
		}
		
		// Source Amount
		BigDecimal sourceAmt = SetGetUtil.get_AttrValueAsBigDecimal(model, SourceAmtName);
		if (sourceAmt == null || sourceAmt.signum() == 0)
		{
			if (AmtName != null)
			{
				model.set_AttrValue(AmtName, Env.ZERO);
			}
			return Env.ZERO;
		}

		// AD_Client_ID
		int AD_Client_ID = SetGetUtil.get_AttrValueAsInt(model, "AD_Client_ID");

		// Currency To
		int C_Currency_ID_To = MClient.get(model.getCtx(), AD_Client_ID).getAcctSchema().getC_Currency_ID();
		//~ model.set_AttrValue("C_Currency_ID_To", Integer.valueOf(C_Currency_ID_To));
		
		// Get Rate
		BigDecimal rate = SetGetUtil.get_AttrValueAsBigDecimal(model, "CurrencyRate");
		if (rate == null || rate.signum() == 0)
		{
			int AD_Org_ID = SetGetUtil.get_AttrValueAsInt(model, "AD_Client_ID");
			Timestamp ConvDate = SetGetUtil.get_AttrValueAsDate(model, DateName);
			int C_Currency_ID = SetGetUtil.get_AttrValueAsInt(model, "C_Currency_ID");
			if (C_Currency_ID == C_Currency_ID_To)
			{
				rate = Env.ONE;
			}
			else
			{
				int C_ConversionType_ID = SetGetUtil.get_AttrValueAsInt(model, "C_ConversionType_ID");
				rate = MConversionRate.getRate (C_Currency_ID, C_Currency_ID_To,
													ConvDate, C_ConversionType_ID,
													AD_Client_ID, AD_Org_ID);
				if (rate == null)
				{ // NoCurrencyConversion
					throw new NoCurrencyConversionException(C_Currency_ID, C_Currency_ID_To,
							ConvDate, C_ConversionType_ID,
							AD_Client_ID, AD_Org_ID);
				}
			}
		}
		model.set_AttrValue("CurrencyRate", rate);
		
		// Calculate converted amount
		BigDecimal amt = sourceAmt.multiply(rate);
		int stdPrecision = MCurrency.getStdPrecision(model.getCtx(), C_Currency_ID_To);
		amt = amt.setScale(stdPrecision, RoundingMode.HALF_UP);
		
		// Update model
		if (AmtName != null)
			model.set_AttrValue(AmtName, amt);
		// Return amt
		if (CLogMgt.isLevelFine())	s_log.fine("amt=" + sourceAmt + " * " + rate + "=" + amt + ", scale=" + stdPrecision);
		return amt;
	}	//	convert
}
