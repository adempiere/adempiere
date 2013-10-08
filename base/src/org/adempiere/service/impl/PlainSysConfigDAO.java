package org.adempiere.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.adempiere.ad.wrapper.IPOJOFilter;
import org.compiere.model.IQuery;
import org.compiere.model.I_AD_SysConfig;
import org.compiere.model.POJOQuery;

public class PlainSysConfigDAO extends AbstractSysConfigDAO
{
	public PlainSysConfigDAO()
	{
		super();
	}

	@Override
	public I_AD_SysConfig retrieveSysConfig(final Properties ctx, final String name, final int AD_Client_ID, final int AD_Org_ID, final String trxName)
	{
		return createSysConfigQuery(trxName, AD_Client_ID, AD_Org_ID, true).firstOnly(I_AD_SysConfig.class);
	}

	/**
	 * 
	 * @param name
	 * @param adClientId
	 * @param adOrgId
	 * @param strictClientOrg if false, also records were AD_Client_ID or AD_Org_ID is ZERO will be returned
	 * @return
	 */
	private final IQuery createSysConfigQuery(final String name, final int adClientId, final int adOrgId, final boolean strictClientOrg)
	{
		return new POJOQuery(I_AD_SysConfig.class)
				.addFilter(new IPOJOFilter<Object>()
				{

					@Override
					public boolean accept(final Object o)
					{
						final I_AD_SysConfig pojo = (I_AD_SysConfig)o;
						if (!pojo.getName().equals(name))
						{
							return false;
						}
						
						if (pojo.getAD_Client_ID() != adClientId)
						{
							if (!strictClientOrg && pojo.getAD_Client_ID() > 0)
							{
								return false;
							}
						}
						
						if (pojo.getAD_Org_ID() != adOrgId || pojo.getAD_Org_ID() > 0)
						{
							if (!strictClientOrg && pojo.getAD_Org_ID() > 0)
							{
								return false;
							}
						}

						if (strictClientOrg && !pojo.isActive())
						{
							return false;
						}

						return true;
					}
				})
				.setOrderBy(sysConfigFindOrderBy);
	}

	@Override
	public String retrieveSysConfigValue(String name, String defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		final I_AD_SysConfig sysConfig = createSysConfigQuery(name, AD_Client_ID, AD_Org_ID, false).first(I_AD_SysConfig.class);
		if (sysConfig == null)
		{
			return defaultValue;
		}
		return sysConfig.getValue();
	}

	@Override
	public List<String> retrieveNamesForPrefix(final String prefix, final int adClientId, final int adOrgId)
	{
		final List<I_AD_SysConfig> sysConfigs = new POJOQuery(I_AD_SysConfig.class)
				.addFilter(new IPOJOFilter<Object>()
				{

					@Override
					public boolean accept(final Object o)
					{
						final I_AD_SysConfig pojo = (I_AD_SysConfig)o;
						if (!pojo.getName().startsWith(prefix))
						{
							return false;
						}
						if (pojo.getAD_Client_ID() != adClientId || pojo.getAD_Client_ID() > 0)
						{
							return false;
						}
						if (pojo.getAD_Org_ID() != adOrgId || pojo.getAD_Org_ID() > 0)
						{
							return false;
						}
						return true;
					}
				})
				.list(I_AD_SysConfig.class);

		final List<String> result = new ArrayList<String>();
		for (final I_AD_SysConfig sysConfig : sysConfigs)
		{
			result.add(sysConfig.getName());
		}

		Collections.sort(result);

		return result;
	}

}
