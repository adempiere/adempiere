package org.adempiere.ad.migration.executor.impl;

import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutor.Action;
import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.impl.AbstractMigrationStepExecutor.ExecutionResult;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.util.CLogger;
import org.compiere.util.TrxRunnable2;

class MigrationStepExecutorRunnable implements TrxRunnable2
{
	private final transient CLogger logger = CLogger.getCLogger(getClass());

	private final IMigrationExecutorContext migrationCtx;
	private final I_AD_MigrationStep step;
	private final Action action;

	private Throwable exception;
	private ExecutionResult executionResult = null;

	public MigrationStepExecutorRunnable(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step, final Action action)
	{
		this.migrationCtx = migrationCtx;
		this.step = step;
		this.action = action;
	}

	public ExecutionResult getExecutionResult()
	{
		return executionResult;
	}

	@Override
	public void run(String trxName)
	{
		final CompositeMigrationStepExecutor executor = new CompositeMigrationStepExecutor(migrationCtx, step);

		if (action == Action.Apply)
		{
			executionResult = executor.apply(trxName);
		}
		else if (action == Action.Rollback)
		{
			executionResult = executor.rollback(trxName);
		}
		else
		{
			throw new IllegalStateException("Unknown action: " + action);
		}
	}

	@Override
	public boolean doCatch(Throwable e) throws Throwable
	{
		exception = e;
		throw e;
	}

	@Override
	public void doFinally()
	{
		if (executionResult != null)
		{
			setExecutionStatus();
			InterfaceWrapperHelper.save(step);
		}
	}

	/**
	 * After step execution, sets ErrorMsg, StatusCode, Apply
	 */
	private void setExecutionStatus()
	{
		// Success
		if (exception == null)
		{
			step.setErrorMsg(null);
			if (action == Action.Apply && executionResult == ExecutionResult.Executed)
			{
				step.setStatusCode(X_AD_MigrationStep.STATUSCODE_Applied);
			}
			else if (action == Action.Rollback && executionResult == ExecutionResult.Executed)
			{
				step.setStatusCode(X_AD_MigrationStep.STATUSCODE_Unapplied);
			}
			else
			{
				// leave the StatusCode as is
			}
		}
		// Error
		else
		{
			logger.log(Level.SEVERE, "Action " + action + " of " + step + " failed.", exception);

			step.setErrorMsg(exception.getLocalizedMessage());
			if (action == Action.Apply)
			{
				step.setStatusCode(X_AD_MigrationStep.STATUSCODE_Failed);
				step.setApply(X_AD_MigrationStep.APPLY_Apply);
			}
		}

		if (X_AD_MigrationStep.STATUSCODE_Applied.equals(step.getStatusCode()))
		{
			step.setApply(X_AD_MigrationStep.APPLY_Rollback);
		}
		else if (X_AD_MigrationStep.STATUSCODE_Unapplied.equals(step.getStatusCode()))
		{
			step.setApply(X_AD_MigrationStep.APPLY_Apply);
		}
	}
}
