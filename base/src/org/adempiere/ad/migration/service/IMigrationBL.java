package org.adempiere.ad.migration.service;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.util.ISingletonService;

public interface IMigrationBL extends ISingletonService
{
	/**
	 * Updates {@link I_AD_Migration}'s status fields.
	 * 
	 * NOTE: this method is also saving the migration object.
	 * 
	 * @param migration
	 */
	void updateStatus(I_AD_Migration migration);

	/**
	 * Sort steps by creation TIMESTAMP.
	 * 
	 * @param migration
	 */
	void sortStepsByCreated(I_AD_Migration migration);

	/**
	 * Merge {@link I_AD_Migration} from -> to.
	 * 
	 * @param to
	 * @param from
	 */
	void mergeMigration(I_AD_Migration to, I_AD_Migration from);

	/**
	 * @param migration
	 * @return String migration summary
	 */
	String getSummary(I_AD_Migration migration);

	/**
	 * @param step
	 * @return String migration step summary
	 */
	String getSummary(I_AD_MigrationStep step);

	/**
	 * Increment migration {@link I_AD_Migration} sequence number.
	 * 
	 * @param migration
	 */
	void setSeqNo(I_AD_Migration migration);

	/**
	 * Increment migration step {@link I_AD_MigrationStep} sequence number.
	 * 
	 * @param step
	 */
	void setSeqNo(I_AD_MigrationStep step);
}
