package org.compiere.asset.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.core.domains.models.I_A_Asset_Addition;
import org.adempiere.model.GridTabWrapper;
import org.compiere.asset.feature.UseLifeImpl;
import org.compiere.asset.model.MAssetAddition;
import org.compiere.asset.model.MConversionRateUtil;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProject;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;


/**
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class CalloutAssetAddition extends CalloutEngine
{
	public String matchInv(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";

		int M_MatchInv_ID = ((Number)value).intValue();
		if (M_MatchInv_ID > 0)
		{
			MAssetAddition.setM_MatchInv(org.compiere.asset.model.SetGetUtil.wrap(gridTab), M_MatchInv_ID);
		}
		//
		return amt(ctx, windowNo, gridTab, gridField, value);
	}

	public String project(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive())
			return "";
		//
		int projectId = 0;
		if (value != null && value instanceof Number)
			projectId = ((Number)value).intValue();
		else
			return "";
		//
		BigDecimal amt = Env.ZERO;
		if (projectId > 0) {
			MProject project = new MProject(ctx, projectId, null);
			amt = project.getProjectBalanceAmt();
			gridTab.setValue(MAssetAddition.COLUMNNAME_C_Currency_ID, project.getC_Currency_ID());
		}
		gridTab.setValue(MAssetAddition.COLUMNNAME_AssetSourceAmt, amt);
		return amt(ctx, windowNo, gridTab, gridField, value);
	}

	public String amt(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive())
			return "";
		//
		String columnName = gridField.getColumnName();
		if (MAssetAddition.COLUMNNAME_A_Accumulated_Depr.equals(columnName))
		{
			gridTab.setValue(MAssetAddition.COLUMNNAME_A_Accumulated_Depr_F, value);
		}
		else
		{
			BigDecimal amtEntered = (BigDecimal) gridTab.getValue(MAssetAddition.COLUMNNAME_AssetAmtEntered);
			gridTab.setValue(MAssetAddition.COLUMNNAME_AssetSourceAmt, amtEntered);
			MConversionRateUtil.convertBase(org.compiere.asset.model.SetGetUtil.wrap(gridTab),
					MAssetAddition.COLUMNNAME_DateAcct,
					MAssetAddition.COLUMNNAME_AssetSourceAmt,
					MAssetAddition.COLUMNNAME_AssetValueAmt,
					gridField.getColumnName());
		}
		//
		return "";
	}

	public String dateDoc(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive() || value == null)
			return "";
		
		gridTab.setValue(MAssetAddition.COLUMNNAME_DateAcct, value);
		return "";
	}
	
	public String uselife(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (MAssetAddition.COLUMNNAME_DeltaUseLifeYears.equals(gridField.getColumnName()))
		{
			gridTab.setValue(MAssetAddition.COLUMNNAME_DeltaUseLifeYears_F, value);
		}
		return "";
	}
	
	
	public String periodOffset(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		I_A_Asset_Addition assetAddition = GridTabWrapper.create(gridTab, I_A_Asset_Addition.class);
		if (!assetAddition.isA_Accumulated_Depr_Adjust())
		{
			return "";
		}
		
		int periods = TimeUtil.getMonthsBetween(assetAddition.getDateDoc(), assetAddition.getDateAcct());
		if (periods <= 0)
		{
			return "";
		}
		
		//	DeltaUseLifeYears is fractional since adempiere#4412: round to the nearest whole month
		//	instead of truncating (3.3333 years is 40 months, not 39).
		int uselifeMonths = assetAddition.getDeltaUseLifeYears().multiply(UseLifeImpl.MONTHS_PER_YEAR).setScale(0, RoundingMode.HALF_UP).intValue();
		if (uselifeMonths <= 0)
		{
			return "";
		}
		BigDecimal monthlyExpenseSL = assetAddition.getAssetValueAmt()
				.multiply(BigDecimal.valueOf(periods))
				.divide(BigDecimal.valueOf(uselifeMonths), 2, RoundingMode.HALF_UP);
		
		assetAddition.setA_Period_Start(periods + 1);
		assetAddition.setA_Accumulated_Depr(monthlyExpenseSL);
		assetAddition.setA_Accumulated_Depr_F(monthlyExpenseSL);
		
		return "";
	}
	
}
