package org.compiere.asset.controller;

import java.math.BigDecimal;
import java.util.Properties;

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
		BigDecimal uselife = null;
		if (MDepreciationWorkfile.COLUMNNAME_UseLifeYears.equals(gridField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).multiply(BigDecimal.valueOf(12.0));
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, value);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths, uselife);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, uselife);
			
		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeMonths.equals(gridField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).divide(BigDecimal.valueOf(12.0));
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears, uselife);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, uselife);
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, value);
			
		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F.equals(gridField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).multiply(BigDecimal.valueOf(12.0));
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, uselife);
			
		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F.equals(gridField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).divide(BigDecimal.valueOf(12.0));
			gridTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, uselife);
			
		}
		return "";
		
	}
	
}