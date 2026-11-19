package org.compiere.asset.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.compiere.asset.feature.UseLifeImpl;
import org.compiere.asset.model.MDepreciationWorkfile;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

/**
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class CalloutDepreciationWorkfile extends CalloutEngine
{
	public String valoareCofinantare(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive())
			return "";
		MDepreciationWorkfile.updateFinantare(org.compiere.asset.model.SetGetUtil.wrap(gridTab), gridField.getColumnName());
		return "";
	}
	
	public String uselifeyear(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (value == null)
			return "";

		String column = gridField.getColumnName();
		//	Years are a Quantity (BigDecimal) since adempiere#4412; months stay a whole number.
		BigDecimal years = new BigDecimal(value.toString());

		if (MDepreciationWorkfile.COLUMNNAME_UseLifeYears.equals(column))
		{
			Integer months = Integer.valueOf(monthsOf(years));
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, years);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths, months);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, months);

		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeMonths.equals(column))
		{
			Integer months = Integer.valueOf(years.setScale(0, RoundingMode.HALF_UP).intValue());
			years = yearsOf(months);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears, years);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, years);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, months);

		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F.equals(column))
		{
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, Integer.valueOf(monthsOf(years)));

		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F.equals(column))
		{
			Integer months = Integer.valueOf(years.setScale(0, RoundingMode.HALF_UP).intValue());
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, yearsOf(months));

		}
		return "";
		
	}

	/**
	 *	@param years fractional useful life
	 *	@return the equivalent whole number of months
	 */
	private static int monthsOf(BigDecimal years)
	{
		return years.multiply(UseLifeImpl.MONTHS_PER_YEAR).setScale(0, RoundingMode.HALF_UP).intValue();
	}

	/**
	 *	@param months whole number of months
	 *	@return the equivalent fractional useful life in years
	 */
	private static BigDecimal yearsOf(Integer months)
	{
		return new BigDecimal(months.intValue()).divide(UseLifeImpl.MONTHS_PER_YEAR, UseLifeImpl.SCALE_USE_LIFE_YEARS, RoundingMode.HALF_UP);
	}
	
}