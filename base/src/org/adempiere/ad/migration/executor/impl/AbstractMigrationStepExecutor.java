package org.adempiere.ad.migration.executor.impl;

import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationStepExecutor;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.compiere.util.CLogger;
import org.compiere.util.Util;

public abstract class AbstractMigrationStepExecutor implements IMigrationStepExecutor
{
	public static enum ExecutionResult
	{
		Executed,
		Skipped,
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

	protected I_AD_MigrationStep getAD_MigrationStep()
	{
		return step;
	}

	protected IMigrationExecutorContext getMigrationExecutorContext()
	{
		return migrationExecutorContext;
	}

	protected Properties getCtx()
	{
		return migrationExecutorContext.getCtx();
	}

	protected final void log(final String msg, final String resolution, final boolean isError)
	{
		final Level level = isError ? Level.WARNING : Level.INFO;

		if (!logger.isLoggable(level))
		{
			return;
		}

		final StringBuffer sb = new StringBuffer();
		sb.append("Step ").append(step.getSeqNo());

		if (!Util.isEmpty(msg, true))
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
