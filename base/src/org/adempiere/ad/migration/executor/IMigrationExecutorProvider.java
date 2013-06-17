package org.adempiere.ad.migration.executor;

import java.util.Properties;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_MigrationStep;

public interface IMigrationExecutorProvider extends ISingletonService
{
	IMigrationExecutorContext createContext(Properties ctx);

	void registerMigrationStepExecutor(String stepType, Class<? extends IMigrationStepExecutor> executorClass);

	IMigrationExecutor newMigrationExecutor(IMigrationExecutorContext ctx, int migrationId);

	IMigrationStepExecutor newMigrationStepExecutor(IMigrationExecutorContext migrationCtx, I_AD_MigrationStep step);
}
