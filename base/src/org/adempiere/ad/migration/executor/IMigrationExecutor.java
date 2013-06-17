package org.adempiere.ad.migration.executor;

import java.util.List;

import org.compiere.model.I_AD_Migration;
import org.compiere.model.I_AD_MigrationStep;

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

	I_AD_Migration getAD_Migration();

	CommitLevel getCommitLevel();

	void setCommitLevel(CommitLevel commitLevel);

	List<I_AD_MigrationStep> getMigrationSteps();

	void setMigrationSteps(List<I_AD_MigrationStep> steps);

	void execute();

	void execute(final Action action);

	String getStatusDescription();

	List<Exception> getExecutionErrors();
}