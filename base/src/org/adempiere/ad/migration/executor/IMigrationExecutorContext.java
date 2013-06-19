package org.adempiere.ad.migration.executor;

import java.util.Properties;

public interface IMigrationExecutorContext
{
	public static enum MigrationOperation
	{
		BOTH,
		DDL,
		DML,
	};

	Properties getCtx();

	IMigrationExecutorProvider getMigrationExecutorProvider();

	boolean isFailOnFirstError();

	void setFailOnFirstError(boolean failOnFirstError);

	void setMigrationOperation(MigrationOperation operation);

	boolean isApplyDML();

	boolean isApplyDDL();

	boolean isSkipMissingColumns();

	boolean isSkipMissingTables();
}
