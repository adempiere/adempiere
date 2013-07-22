package org.adempiere.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.service.ITrxBL;
import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.service.ISysConfigDAO;
import org.adempiere.util.Services;
import org.compiere.model.I_AD_SysConfig;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.TrxRunnable;

/**
 * SysConfig Service. Most of the code is copy-paste from MSysConfig
 * 
 * @author tsa
 * 
 */
public class SysConfigBL implements ISysConfigBL
{
	private final CLogger s_log = CLogger.getCLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getValue(java.lang.String, java.lang.String)
	 */
	@Override
	public String getValue(String Name, String defaultValue)
	{
		return getValue(Name, defaultValue, 0, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String Name)
	{
		return getValue(Name, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getIntValue(java.lang.String, int)
	 */
	@Override
	public int getIntValue(String Name, int defaultValue)
	{
		String s = getValue(Name);
		if (s == null)
			return defaultValue;

		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getDoubleValue(java.lang.String, double)
	 */
	@Override
	public double getDoubleValue(String Name, double defaultValue)
	{
		String s = getValue(Name);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getBooleanValue(java.lang.String, boolean)
	 */
	@Override
	public boolean getBooleanValue(String Name, boolean defaultValue)
	{
		String s = getValue(Name);
		if (s == null || s.length() == 0)
			return defaultValue;

		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getValue(java.lang.String, java.lang.String, int)
	 */
	@Override
	public String getValue(String Name, String defaultValue, int AD_Client_ID)
	{
		return getValue(Name, defaultValue, AD_Client_ID, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getValue(java.lang.String, int)
	 */
	@Override
	public String getValue(String Name, int AD_Client_ID)
	{
		return (getValue(Name, null, AD_Client_ID));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getIntValue(java.lang.String, int, int)
	 */
	@Override
	public int getIntValue(String Name, int defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null)
			return defaultValue;

		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getDoubleValue(java.lang.String, double, int)
	 */
	@Override
	public double getDoubleValue(String Name, double defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getBooleanValue(java.lang.String, boolean, int)
	 */
	@Override
	public boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID)
	{
		String s = getValue(Name, AD_Client_ID);
		if (s == null || s.length() == 0)
			return defaultValue;

		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getValue(java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public String getValue(String Name, String defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		return Services.get(ISysConfigDAO.class).retrieveSysConfigValue(Name, defaultValue, AD_Client_ID, AD_Org_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getValue(java.lang.String, int, int)
	 */
	@Override
	public String getValue(String Name, int AD_Client_ID, int AD_Org_ID)
	{
		return getValue(Name, null, AD_Client_ID, AD_Org_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getIntValue(java.lang.String, int, int, int)
	 */
	@Override
	public int getIntValue(String Name, int defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null)
			return defaultValue;

		if (s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getIntValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getDoubleValue(java.lang.String, double, int, int)
	 */
	@Override
	public double getDoubleValue(String Name, double defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null || s.length() == 0)
			return defaultValue;
		//
		try
		{
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e)
		{
			s_log.log(Level.SEVERE, "getDoubleValue (" + Name + ") = " + s, e);
		}
		return defaultValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.service.impl.ISysConfigBL#getBooleanValue(java.lang.String, boolean, int, int)
	 */
	@Override
	public boolean getBooleanValue(String Name, boolean defaultValue, int AD_Client_ID, int AD_Org_ID)
	{
		String s = getValue(Name, AD_Client_ID, AD_Org_ID);
		if (s == null || s.length() == 0)
			return defaultValue;

		if ("Y".equalsIgnoreCase(s))
			return true;
		else if ("N".equalsIgnoreCase(s))
			return false;
		else
			return Boolean.valueOf(s).booleanValue();
	}

	@Override
	public void setValue(String name, int value, int AD_Org_ID)
	{
		setValue(name, String.valueOf(value), AD_Org_ID);
	}

	@Override
	public void setValue(String name, double value, int AD_Org_ID)
	{
		setValue(name, String.valueOf(value), AD_Org_ID);
	}

	@Override
	public void setValue(String name, boolean value, int AD_Org_ID)
	{
		setValue(name, value ? "Y" : "N", AD_Org_ID);
	}

	@Override
	public void setValue(final String name, final String value, final int AD_Org_ID)
	{
		final Properties ctx = Env.getCtx();
		final int AD_Client_ID = Env.getAD_Client_ID(ctx);
		
		Services.get(ITrxBL.class).run(new TrxRunnable()
		{
			
			@Override
			public void run(String localTrxName) throws Exception
			{
				I_AD_SysConfig sysConfig = Services.get(ISysConfigDAO.class).retrieveSysConfig(ctx, name, AD_Client_ID, AD_Org_ID, localTrxName);
				if (sysConfig == null)
				{
					sysConfig = InterfaceWrapperHelper.create(ctx, I_AD_SysConfig.class, localTrxName);
					sysConfig.setName(name);
					sysConfig.setAD_Org_ID(AD_Org_ID);
				}
				sysConfig.setValue(value);
				InterfaceWrapperHelper.save(sysConfig);
			}
		});
	}

	@Override
	public List<String> getNamesForPrefix(String prefix, int adClientId, int adOrgId)
	{
		return Services.get(ISysConfigDAO.class).retrieveNamesForPrefix(prefix, adClientId, adOrgId);
	}

	@Override
	public Map<String, String> getValuesForPrefix(String prefix, int adClientId, int adOrgId)
	{
		final boolean removePrefix = false;
		return getValuesForPrefix(prefix, removePrefix, adClientId, adOrgId);
	}

	@Override
	public Map<String, String> getValuesForPrefix(final String prefix, final boolean removePrefix, final int adClientId, final int adOrgId)
	{
		final List<String> paramNames = getNamesForPrefix(prefix, adClientId, adOrgId);
		final Map<String, String> result = new HashMap<String, String>(paramNames.size());
		for (final String paramName : paramNames)
		{
			final String defaultValue = null;
			final String value = getValue(paramName, defaultValue, adClientId, adOrgId);
			if (value == null)
			{
				continue;
			}

			final String name;
			if (removePrefix && paramName.startsWith(prefix) && !paramName.equals(prefix))
			{
				name = paramName.substring(prefix.length());
			}
			else
			{
				name = paramName;
			}

			result.put(name, value);
		}

		return result;
	}

}
