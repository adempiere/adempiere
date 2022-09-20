/**
 * 
 */
package org.compiere.asset.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.core.domains.models.I_A_Asset_Disposed;
import org.adempiere.model.GridTabWrapper;
import org.compiere.asset.model.MAssetDisposed;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;



/**
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class CalloutAssetDisposed extends CalloutEngine
{
	public String asset(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		I_A_Asset_Disposed assetDisposed = GridTabWrapper.create(gridTab, I_A_Asset_Disposed.class);
		MAssetDisposed.updateFromAsset(assetDisposed);
		assetDisposed.setA_Disposal_Amt(assetDisposed.getA_Asset_Cost().subtract(assetDisposed.getA_Accumulated_Depr()));
		//
		return "";
	}

	public String date(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		if (isCalloutActive())
		{
			return "";
		}
		String columnName = gridField.getColumnName();
		//
		if (MAssetDisposed.COLUMNNAME_DateDoc.equals(columnName))
		{
			I_A_Asset_Disposed assetDisposed = GridTabWrapper.create(gridTab, I_A_Asset_Disposed.class);
			Timestamp dateDoc = (Timestamp)value;
			assetDisposed.setDateAcct(dateDoc);
			assetDisposed.setA_Disposed_Date(dateDoc);
		}
		//
		return "";
	}

	public String amt(Properties ctx, int windowNo, GridTab gridTab, GridField gridField, Object value)
	{
		String columnName = gridField.getColumnName();
		
		I_A_Asset_Disposed assetDisposed = GridTabWrapper.create(gridTab, I_A_Asset_Disposed.class);
		//
		int assetId = assetDisposed.getA_Asset_ID();
		if (assetId <= 0)
		{
			assetDisposed.setA_Disposal_Amt(Env.ZERO);
			assetDisposed.setA_Accumulated_Depr_Delta(Env.ZERO);
			assetDisposed.setExpense(Env.ZERO);
		}
		else if (MAssetDisposed.COLUMNNAME_A_Disposal_Amt.equals(columnName))
		{
			MAssetDisposed.setA_Disposal_Amt(assetDisposed);
		}
		else if (MAssetDisposed.COLUMNNAME_Expense.equals(columnName))
		{
			BigDecimal disposalAmt = assetDisposed.getA_Disposal_Amt();
			BigDecimal expenseAmt = assetDisposed.getExpense();
			assetDisposed.setA_Accumulated_Depr_Delta(disposalAmt.subtract(expenseAmt));
		}
		else if (MAssetDisposed.COLUMNNAME_A_Accumulated_Depr.equals(columnName))
		{
			BigDecimal disposalAmt = assetDisposed.getA_Disposal_Amt();
			BigDecimal accumDepr = assetDisposed.getA_Accumulated_Depr_Delta();
			assetDisposed.setExpense(disposalAmt.subtract(accumDepr));
		}
		return "";
	}

}
