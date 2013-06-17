package org.adempiere.ad.migration.executor;

import org.adempiere.ad.migration.executor.impl.AbstractMigrationStepExecutor.ExecutionResult;

public interface IMigrationStepExecutor
{

	ExecutionResult apply(String trxName);

	ExecutionResult rollback(String trxName);

}
