package org.adempiere.ad.migration.service;

import java.util.List;
import java.util.Properties;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationData;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.util.ISingletonService;

public interface IMigrationDAO extends ISingletonService
{
	/**
	 * @param ctx
	 * @param migrationId
	 * @return {@link I_AD_Migration} by migration id.
	 */
	I_AD_Migration retrieveMigrationOrNull(Properties ctx, int migrationId);

	/**
	 * @param ctx
	 * @param name
	 * @param seqNo
	 * @param entityType
	 * @param trxName
	 * @return true if migration was found using the specified properties, false otherwise.
	 */
	boolean existsMigration(Properties ctx, String name, int seqNo, String entityType, String trxName);

	/**
	 * @param migration
	 * @param statusCode
	 * @return integer the count of the migration steps for the specified migration.
	 */
	int countMigrationSteps(I_AD_Migration migration, String statusCode);

	/**
	 * Retrieve the migration steps for the specified migration.
	 * 
	 * @param migration
	 * @param sortAsc
	 * @return {@link List}&lt;{@link I_AD_MigrationStep}&gt;
	 */
	List<I_AD_MigrationStep> retrieveSteps(I_AD_Migration migration, boolean sortAsc);

	/**
	 * Merge {@link I_AD_Migration} from -> to.
	 * 
	 * @param to
	 * @param from
	 */
	void mergeMigration(I_AD_Migration to, I_AD_Migration from);

	/**
	 * @param migration
	 * @return integer the last {@link I_AD_Migration} sequence number.
	 */
	int getMigrationLastSeqNo(I_AD_Migration migration);

	/**
	 * @param step
	 * @return integer the last {@link I_AD_MigrationStep} sequence number.
	 */
	int getMigrationStepLastSeqNo(I_AD_MigrationStep step);

	/**
	 * @param step
	 * @return {@link List}&lt;{@link I_AD_MigrationData}&gt; of the specified migration step.
	 */
	List<I_AD_MigrationData> retrieveMigrationData(I_AD_MigrationStep step);
}
