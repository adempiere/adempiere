/**
 * 
 */
package org.compiere.FA.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.model.GridTabWrapper;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_A_Asset_Disposed;
import org.compiere.model.MAssetDisposed;
import org.compiere.util.Env;



/**
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class CalloutA_Asset_Disposed extends CalloutEngine
{
	public String asset(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		I_A_Asset_Disposed bean = GridTabWrapper.create(mTab, I_A_Asset_Disposed.class);
		MAssetDisposed.updateFromAsset(bean);
		bean.setA_Disposal_Amt(bean.getA_Asset_Cost().subtract(bean.getA_Accumulated_Depr()));
		//
		return "";
	}

	public String date(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if (isCalloutActive())
		{
			return "";
		}
		String columnName = mField.getColumnName();
		//
		if (MAssetDisposed.COLUMNNAME_DateDoc.equals(columnName))
		{
			I_A_Asset_Disposed bean = GridTabWrapper.create(mTab, I_A_Asset_Disposed.class);
			Timestamp dateDoc = (Timestamp)value;
			bean.setDateAcct(dateDoc);
			bean.setA_Disposed_Date(dateDoc);
		}
		//
		return "";
	}

	public String amt(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		String columnName = mField.getColumnName();
		
		I_A_Asset_Disposed bean = GridTabWrapper.create(mTab, I_A_Asset_Disposed.class);
		//
		int asset_id = bean.getA_Asset_ID();
		if (asset_id <= 0)
		{
			bean.setA_Disposal_Amt(Env.ZERO);
			bean.setA_Accumulated_Depr_Delta(Env.ZERO);
			bean.setExpense(Env.ZERO);
		}
		else if (MAssetDisposed.COLUMNNAME_A_Disposal_Amt.equals(columnName))
		{
			MAssetDisposed.setA_Disposal_Amt(bean);
		}
		else if (MAssetDisposed.COLUMNNAME_Expense.equals(columnName))
		{
			BigDecimal disposalAmt = bean.getA_Disposal_Amt();
			BigDecimal expenseAmt = bean.getExpense();
			bean.setA_Accumulated_Depr_Delta(disposalAmt.subtract(expenseAmt));
		}
		else if (MAssetDisposed.COLUMNNAME_A_Accumulated_Depr.equals(columnName))
		{
			BigDecimal disposalAmt = bean.getA_Disposal_Amt();
			BigDecimal accumDepr = bean.getA_Accumulated_Depr_Delta();
			bean.setExpense(disposalAmt.subtract(accumDepr));
		}
		return "";
	}

}
