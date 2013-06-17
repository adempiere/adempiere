package org.adempiere.ad.migration.logger;

import org.compiere.model.I_AD_Migration;

public interface IMigrationLoggerContext
{
	/**
	 * 
	 * @return true if migration scripts logging is enabled
	 */
	boolean isEnabled();

	I_AD_Migration getMigration(String key);

	void putMigration(String key, I_AD_Migration migration);

	boolean isGenerateComments();
}
