package org.adempiere.ad.migration.executor;

import java.util.List;

import org.adempiere.ad.migration.model.I_AD_Migration;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;

public interface IMigrationExecutor
{
	enum Action
	{
		Apply,
		Rollback,
	}

	enum CommitLevel
	{
		Batch,
		Step,
	}

	/**
	 * Get the current migration.
	 * 
	 * @return {@link I_AD_Migration} migration
	 */
	I_AD_Migration getAD_Migration();

	/**
	 * Get the current commit level.
	 * 
	 * @return {@link CommitLevel} commitLevel
	 */
	CommitLevel getCommitLevel();

	/**
	 * Sets the Migration Executor commit level.
	 * 
	 * @param commitLevel
	 */
	void setCommitLevel(CommitLevel commitLevel);

	/**
	 * Get the migration steps assigned to this migration.
	 * 
	 * @return {@link List}&lt;{@link I_AD_MigrationStep}&gt; migrationSteps
	 */
	List<I_AD_MigrationStep> getMigrationSteps();

	/**
	 * Sets the migration steps for the current migration.
	 * 
	 * @param steps
	 */
	void setMigrationSteps(List<I_AD_MigrationStep> steps);

	/**
	 * Execute migration depending on the migration {@link Action}. Action is detected.
	 */
	void execute();

	/**
	 * Parameterized {@link #execute()}
	 * 
	 * @param action
	 */
	void execute(final Action action);

	/**
	 * Get the status message (description) depending on the {@link X_AD_Migration} status code.
	 * 
	 * @return {@link String} status description
	 */
	String getStatusDescription();

	/**
	 * Get the exceptions that occured during the execution of the current migration.
	 * 
	 * @return {@link List}&lt;{@link Exception}&gt; exceptions
	 */
	List<Exception> getExecutionErrors();
}