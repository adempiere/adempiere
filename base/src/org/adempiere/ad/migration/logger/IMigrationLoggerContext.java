package org.adempiere.ad.migration.logger;

import org.adempiere.ad.migration.model.I_AD_Migration;

public interface IMigrationLoggerContext
{
	/**
	 * @return true if migration scripts logging is enabled
	 */
	boolean isEnabled();

	/**
	 * @param key
	 * @return {@link I_AD_Migration} the current migration
	 */
	I_AD_Migration getMigration(String key);

	/**
	 * Add specified migration to the context.
	 * 
	 * @param key
	 * @param migration
	 */
	void putMigration(String key, I_AD_Migration migration);

	/**
	 * @return true if comments are to be generated
	 */
	boolean isGenerateComments();
}
