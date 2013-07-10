package org.adempiere.ad.migration.executor;

import java.util.Properties;

import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.util.ISingletonService;

public interface IMigrationExecutorProvider extends ISingletonService
{
	/**
	 * Creates a new instance of a {@link IMigrationExecutorContext} implementation and returns it.
	 * 
	 * @param ctx
	 * @return {@link IMigrationExecutorContext} migrationExecutorContext
	 */
	IMigrationExecutorContext createInitialContext(Properties ctx);

	/**
	 * Registers a new {@link MigrationStepExecutor} for the current {@link IMigrationExecutorProvider} instance.
	 * 
	 * @param stepType
	 * @param executorClass
	 */
	void registerMigrationStepExecutor(String stepType, Class<? extends IMigrationStepExecutor> executorClass);

	/**
	 * Creates a new instance of a {@link IMigrationExecutor} implementation for the current context and returns it.
	 * 
	 * @param ctx
	 * @param migrationId
	 * @return {@link IMigrationExecutor} migrationExecutor
	 */
	IMigrationExecutor newMigrationExecutor(IMigrationExecutorContext ctx, int migrationId);

	/**
	 * Creates a new instance of a {@link IMigrationStepExecutor} implementation for the current migration context and returns it.
	 * 
	 * @param migrationCtx
	 * @param step
	 * @return {@link IMigrationStepExecutor} migrationStepExecutor
	 */
	IMigrationStepExecutor newMigrationStepExecutor(IMigrationExecutorContext migrationCtx, I_AD_MigrationStep step);
}
