package org.adempiere.ad.migration.service;

import java.util.List;
import java.util.Properties;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationData;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.util.ISingletonService;

public interface IMigrationDAO extends ISingletonService
{
	I_AD_Migration retrieveMigrationOrNull(Properties ctx, int migrationId);

	boolean existsMigration(Properties ctx, String name, int seqNo, String entityType, String trxName);

	int countMigrationSteps(I_AD_Migration migration, String statusCode);

	List<I_AD_MigrationStep> retrieveSteps(I_AD_Migration migration, boolean sortAsc);

	void mergeMigration(I_AD_Migration to, I_AD_Migration from);

	int getMigrationLastSeqNo(I_AD_Migration migration);

	int getMigrationStepLastSeqNo(I_AD_MigrationStep step);

	List<I_AD_MigrationData> retrieveMigrationData(I_AD_MigrationStep step);
}
