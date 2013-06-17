package org.adempiere.service.impl;

import java.util.List;
import java.util.Properties;

import org.compiere.model.I_AD_SysConfig;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;

public class SysConfigDAO extends AbstractSysConfigDAO
{
	@Override
	public I_AD_SysConfig retrieveSysConfig(final Properties ctx, final String name, final int AD_Client_ID, final int AD_Org_ID, final String trxName)
	{
		final String whereClause = I_AD_SysConfig.COLUMNNAME_Name + "=?"
				+ " AND " + I_AD_SysConfig.COLUMNNAME_AD_Client_ID + "=?"
				+ " AND " + I_AD_SysConfig.COLUMNNAME_AD_Org_ID + "=?";

		final I_AD_SysConfig sysConfig = new Query(ctx, I_AD_SysConfig.Table_Name, whereClause, trxName)
				.setParameters(name, AD_Client_ID, AD_Org_ID)
				.firstOnly(I_AD_SysConfig.class);
		
		return sysConfig;
	}
	
	@Override
	public String retrieveSysConfigValue(String Name, String defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		return MSysConfig.getValue(Name, defaultValue, AD_Client_ID, AD_Org_ID);
	}
	
	@Override
	public List<String> retrieveNamesForPrefix(String prefix, int adClientId, int adOrgId)
	{
		Util.assume(!Util.isEmpty(prefix, true), "prefix is empty");

		final String whereClause = I_AD_SysConfig.COLUMNNAME_Name + " LIKE ?"
				+ " AND " + I_AD_SysConfig.COLUMNNAME_AD_Client_ID + " IN (0,?)"
				+ " AND " + I_AD_SysConfig.COLUMNNAME_AD_Org_ID + " IN (0,?)"
				+ " AND " + I_AD_SysConfig.COLUMNNAME_IsActive + "=?";

		final String sqlPrefix = prefix + "%";

		return new Query(Env.getCtx(), I_AD_SysConfig.Table_Name, whereClause, Trx.TRXNAME_None)
				.setParameters(sqlPrefix, adClientId, adOrgId, true)
				.setOrderBy(I_AD_SysConfig.COLUMNNAME_Name)
				.aggregateList(I_AD_SysConfig.COLUMNNAME_Name, Query.AGGREGATE_DISTINCT, String.class);
	}

}
