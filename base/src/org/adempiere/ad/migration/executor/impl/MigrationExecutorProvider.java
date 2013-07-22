package org.adempiere.ad.migration.executor.impl;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.adempiere.ad.migration.executor.IMigrationExecutor;
import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationExecutorProvider;
import org.adempiere.ad.migration.executor.IMigrationStepExecutor;
import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.ad.migration.model.X_AD_MigrationStep;
import org.adempiere.exceptions.AdempiereException;

public class MigrationExecutorProvider implements IMigrationExecutorProvider
{
	private Map<String, Class<? extends IMigrationStepExecutor>> stepExecutorsByType = new ConcurrentHashMap<String, Class<? extends IMigrationStepExecutor>>();

	public MigrationExecutorProvider()
	{
		registerMigrationStepExecutor(X_AD_MigrationStep.STEPTYPE_SQLStatement, SQLStatementMigrationStepExecutor.class);
		registerMigrationStepExecutor(X_AD_MigrationStep.STEPTYPE_ApplicationDictionary, POMigrationStepExecutor.class);
	}

	@Override
	public IMigrationExecutorContext createInitialContext(final Properties ctx)
	{
		return new MigrationExecutorContext(ctx, this);
	}

	@Override
	public IMigrationExecutor newMigrationExecutor(final IMigrationExecutorContext migrationCtx, final int migrationId)
	{
		return new MigrationExecutor(migrationCtx, migrationId);
	}

	@Override
	public void registerMigrationStepExecutor(final String stepType, final Class<? extends IMigrationStepExecutor> executorClass)
	{
		stepExecutorsByType.put(stepType, executorClass);
	}

	@Override
	public IMigrationStepExecutor newMigrationStepExecutor(final IMigrationExecutorContext migrationCtx, final I_AD_MigrationStep step)
	{
		final String stepType = step.getStepType();
		final Class<? extends IMigrationStepExecutor> executorClass = stepExecutorsByType.get(stepType);

		if (executorClass == null)
		{
			throw new AdempiereException("Step type not supported: " + stepType);
		}

		try
		{
			return executorClass
					.getConstructor(IMigrationExecutorContext.class, I_AD_MigrationStep.class)
					.newInstance(migrationCtx, step);
		}
		catch (Exception e)
		{
			throw new AdempiereException("Cannot not load step executor for class " + executorClass, e);
		}
	}
}
