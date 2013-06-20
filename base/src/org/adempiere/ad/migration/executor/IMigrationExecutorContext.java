package org.adempiere.ad.migration.executor;

import java.util.List;
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

	void addPostponedExecutable(IPostponedExecutable ddlExecutable);

	List<IPostponedExecutable> popPostponedExecutables();
}
