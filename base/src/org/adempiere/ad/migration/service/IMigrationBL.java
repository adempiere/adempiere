package org.adempiere.ad.migration.service;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_Migration;
import org.compiere.model.I_AD_MigrationStep;

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

	void sortStepsByCreated(I_AD_Migration migration);

	void mergeMigration(I_AD_Migration to, I_AD_Migration from);

	String getSummary(I_AD_Migration migration);

	String getSummary(I_AD_MigrationStep step);

	void setSeqNo(I_AD_Migration migration);

	void setSeqNo(I_AD_MigrationStep step);
}
