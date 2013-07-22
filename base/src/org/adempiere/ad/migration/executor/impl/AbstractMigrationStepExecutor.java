package org.adempiere.ad.migration.executor.impl;

import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationStepExecutor;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.util.Check;
import org.compiere.util.CLogger;

public abstract class AbstractMigrationStepExecutor implements IMigrationStepExecutor
{
	/**
	 * Migration Step Execution Result
	 */
	public static enum ExecutionResult
	{
		/**
		 * Migration step was executed successfully.
		 */
		Executed,
		/**
		 * Migration step could not be executed and was skipped <i>(e.g missing column)</i>.
		 */
		Skipped,
		/**
		 * Migration step was ignored <i>(usually because it was already applied or could not be rolled back)</i>.
		 */
		Ignored,
	};

	protected final transient CLogger logger = CLogger.getCLogger(getClass());

	private final IMigrationExecutorContext migrationExecutorContext;
	private final I_AD_MigrationStep step;

	public AbstractMigrationStepExecutor(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step)
	{
		super();
		this.migrationExecutorContext = migrationCtx;
		this.step = step;
	}

	/**
	 * Get current migration step.
	 * 
	 * @return {@link I_AD_MigrationStep} step
	 */
	protected I_AD_MigrationStep getAD_MigrationStep()
	{
		return step;
	}

	/**
	 * Get current migration executor context.
	 * 
	 * @return {@link IMigrationExecutorContext} migrationExecutorContext
	 */
	protected IMigrationExecutorContext getMigrationExecutorContext()
	{
		return migrationExecutorContext;
	}

	/**
	 * Get current properties context.
	 * 
	 * @return {@link Properties} ctx
	 */
	protected Properties getCtx()
	{
		return migrationExecutorContext.getCtx();
	}

	/**
	 * Log error messages as WARNING and normal ones as INFO.
	 * 
	 * @param msg
	 * @param resolution
	 * @param isError
	 */
	protected final void log(final String msg, final String resolution, final boolean isError)
	{
		final Level level = isError ? Level.WARNING : Level.INFO;

		if (!logger.isLoggable(level))
		{
			return;
		}

		final StringBuffer sb = new StringBuffer();
		sb.append("Step ").append(step.getSeqNo());

		if (!Check.isEmpty(msg, true))
		{
			sb.append(": ").append(msg.trim());
		}

		if (resolution != null)
		{
			sb.append(" [").append(resolution).append("]");
		}

		logger.log(level, sb.toString());
	}
}
