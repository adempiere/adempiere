package org.adempiere.ad.migration.logger.impl;

import org.adempiere.ad.migration.logger.IMigrationLoggerContext;
import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.util.Check;

public class SingletonMigrationLoggerContext implements IMigrationLoggerContext
{
	private final I_AD_Migration migration;
	private boolean generateComments = false;

	public SingletonMigrationLoggerContext(final I_AD_Migration migration)
	{
		Check.assumeNotNull(migration, "migration not null");
		this.migration = migration;
	}

	/**
	 * @return true always
	 */
	@Override
	public boolean isEnabled()
	{
		return true;
	}

	@Override
	public I_AD_Migration getMigration(String key)
	{
		return migration;
	}

	@Override
	public void putMigration(String key, I_AD_Migration migration)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isGenerateComments()
	{
		return generateComments;
	}

	public void setGenerateComments(final boolean generateComments)
	{
		this.generateComments = generateComments;
	}
}
