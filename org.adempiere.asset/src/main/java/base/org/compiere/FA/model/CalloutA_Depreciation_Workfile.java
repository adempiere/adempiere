package org.compiere.FA.model;

import java.math.BigDecimal;
import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

/**
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class CalloutA_Depreciation_Workfile extends CalloutEngine
{
	public String A_Valoare_Cofinantare (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
			return "";
		MDepreciationWorkfile.updateFinantare(SetGetUtil.wrap(mTab), mField.getColumnName());
		return "";
	}
	
	public String uselifeyear(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		BigDecimal uselife = null;
		if (MDepreciationWorkfile.COLUMNNAME_UseLifeYears.equals(mField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).multiply(BigDecimal.valueOf(12.0));
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, value);
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths, uselife);
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, uselife);	
			
		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeMonths.equals(mField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).divide(BigDecimal.valueOf(12.0));
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears, uselife);
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, uselife);
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, value);
			
		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F.equals(mField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).multiply(BigDecimal.valueOf(12.0));
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F, uselife);
			
		} else if (MDepreciationWorkfile.COLUMNNAME_UseLifeMonths_F.equals(mField.getColumnName()))
		{
			uselife =  new BigDecimal(value.toString()).divide(BigDecimal.valueOf(12.0));
			mTab.setValue(MDepreciationWorkfile.COLUMNNAME_UseLifeYears_F, uselife);
			
		}
		return "";
		
	}
	
}