package org.adempiere.ad.migration.executor;

import java.util.Properties;

import org.adempiere.ad.migration.model.I_AD_MigrationStep;
import org.adempiere.util.ISingletonService;

public interface IMigrationExecutorProvider extends ISingletonService
{
	IMigrationExecutorContext createInitialContext(Properties ctx);

	void registerMigrationStepExecutor(String stepType, Class<? extends IMigrationStepExecutor> executorClass);

	IMigrationExecutor newMigrationExecutor(IMigrationExecutorContext ctx, int migrationId);

	IMigrationStepExecutor newMigrationStepExecutor(IMigrationExecutorContext migrationCtx, I_AD_MigrationStep step);
}
