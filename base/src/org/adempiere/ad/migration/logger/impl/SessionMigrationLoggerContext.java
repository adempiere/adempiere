package org.adempiere.ad.migration.logger.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.ad.migration.logger.IMigrationLoggerContext;
import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.service.IMigrationDAO;
import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.Services;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Ini;

/**
 * 
 * @author tsa
 * 
 */
public class SessionMigrationLoggerContext implements IMigrationLoggerContext
{
	public static final String SYSCONFIG_Enabled = "org.adempiere.ad.migration.logger.MigrationLogger.Enabled";
	public static final boolean SYSCONFIG_Enabled_Default = false;

	private final Map<String, Integer> migrationsMap = new HashMap<String, Integer>(3);
	private final CCache<Integer, I_AD_Migration> migrationsCache = new CCache<Integer, I_AD_Migration>(I_AD_Migration.Table_Name, 3, 2);

	public SessionMigrationLoggerContext()
	{
	}

	@Override
	public boolean isEnabled()
	{
		if (!Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT))
		{
			return false;
		}

		if (!Services.get(ISysConfigBL.class).getBooleanValue(SYSCONFIG_Enabled, SYSCONFIG_Enabled_Default))
		{
			return false;
		}

		return true;
	}

	@Override
	public boolean isGenerateComments()
	{
		return true;
	}

	@Override
	public I_AD_Migration getMigration(String key)
	{
		final Properties ctx = Env.getCtx();

		final Integer migrationId = migrationsMap.get(key);
		if (migrationId == null || migrationId <= 0)
		{
			return null;
		}

		I_AD_Migration migration = migrationsCache.get(migrationId);
		if (migration != null)
		{
			return migration;
		}

		// We have the ID in out map, but cache expired, which means that maybe somebody deleted this record
		// Trying to reload
		migration = Services.get(IMigrationDAO.class).retrieveMigrationOrNull(ctx, migrationId);
		if (migration != null)
		{
			putMigration(key, migration);
			return migration;
		}

		return migration;
	}

	@Override
	public void putMigration(String key, I_AD_Migration migration)
	{
		migrationsMap.put(key, migration.getAD_Migration_ID());
		migrationsCache.put(migration.getAD_Migration_ID(), migration);
	}
}
