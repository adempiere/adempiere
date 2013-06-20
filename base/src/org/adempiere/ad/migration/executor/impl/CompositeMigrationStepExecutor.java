package org.adempiere.ad.migration.executor.impl;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationExecutorProvider;
import org.adempiere.ad.migration.executor.IMigrationStepExecutor;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;

public class CompositeMigrationStepExecutor extends AbstractMigrationStepExecutor
{
	public CompositeMigrationStepExecutor(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step)
	{
		super(migrationCtx, step);
	}

	@Override
	public ExecutionResult apply(final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		// Already applied
		if (X_AD_MigrationStep.STATUSCODE_Applied.equals(step.getStatusCode()))
		{
			log("Already applied", "SKIP", false);
			return ExecutionResult.Ignored;
		}

		final IMigrationStepExecutor executor = createDelegatedMigrationStepExecutor(step);
		return executor.apply(trxName);
	}

	@Override
	public ExecutionResult rollback(final String trxName)
	{
		final I_AD_MigrationStep step = getAD_MigrationStep();

		// Not Applied, no rollback is needed
		if (!X_AD_MigrationStep.STATUSCODE_Applied.equals(step.getStatusCode()))
		{
			log("Not applied. Nothing to rollback", "SKIP", false);
			return ExecutionResult.Ignored;
		}

		final IMigrationStepExecutor executor = createDelegatedMigrationStepExecutor(step);
		return executor.rollback(trxName);
	}

	private IMigrationStepExecutor createDelegatedMigrationStepExecutor(final I_AD_MigrationStep step)
	{
		final IMigrationExecutorContext migrationCtx = getMigrationExecutorContext();
		final IMigrationExecutorProvider factory = migrationCtx.getMigrationExecutorProvider();
		final IMigrationStepExecutor executor = factory.newMigrationStepExecutor(migrationCtx, step);
		return executor;
	}

}
