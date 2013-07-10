package org.adempiere.ad.migration.executor;

import org.adempiere.ad.migration.executor.impl.AbstractMigrationStepExecutor.ExecutionResult;

public interface IMigrationStepExecutor
{
	/**
	 * Applies the migration step for the specified transaction and returns {@link ExecutionResult}.
	 * 
	 * @param trxName
	 * @return {@link ExecutionResult}
	 */
	ExecutionResult apply(String trxName);

	/**
	 * Rolls the migration step back for the specified transaction and returns {@link ExecutionResult}.
	 * 
	 * @param trxName
	 * @return {@link ExecutionResult}
	 */
	ExecutionResult rollback(String trxName);
}
