package org.adempiere.ad.migration.executor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationExecutorProvider;
import org.adempiere.ad.migration.executor.IPostponedExecutable;
import org.adempiere.util.Check;

public class MigrationExecutorContext implements IMigrationExecutorContext
{

	private final Properties ctx;
	private final IMigrationExecutorProvider factory;

	private boolean failOnFirstError = true;
	private MigrationOperation migrationOperation = MigrationOperation.BOTH;
	private final List<IPostponedExecutable> postponedExecutables = new ArrayList<IPostponedExecutable>();

	public MigrationExecutorContext(final Properties ctx, final IMigrationExecutorProvider factory)
	{
		this.ctx = ctx;
		this.factory = factory;
	}

	@Override
	public Properties getCtx()
	{
		return ctx;
	}

	@Override
	public boolean isFailOnFirstError()
	{
		return failOnFirstError;
	}

	@Override
	public void setFailOnFirstError(final boolean failOnFirstError)
	{
		this.failOnFirstError = failOnFirstError;
	}

	@Override
	public IMigrationExecutorProvider getMigrationExecutorProvider()
	{
		return factory;
	}

	@Override
	public void setMigrationOperation(MigrationOperation operation)
	{
		Check.assumeNotNull(operation, "operation not null");
		this.migrationOperation = operation;
	}

	@Override
	public boolean isApplyDML()
	{
		return migrationOperation == MigrationOperation.BOTH || migrationOperation == MigrationOperation.DML;
	}

	@Override
	public boolean isApplyDDL()
	{
		return migrationOperation == MigrationOperation.BOTH || migrationOperation == MigrationOperation.DDL;
	}

	@Override
	public boolean isSkipMissingColumns()
	{
		// TODO configuration to be implemented
		return true;
	}

	@Override
	public boolean isSkipMissingTables()
	{
		// TODO configuration to be implemented
		return true;
	}

	@Override
	public void addPostponedExecutable(IPostponedExecutable executable)
	{
		Check.assumeNotNull(executable, "executable not null");
		postponedExecutables.add(executable);
	}

	@Override
	public List<IPostponedExecutable> popPostponedExecutables()
	{
		final List<IPostponedExecutable> result = new ArrayList<IPostponedExecutable>(postponedExecutables);
		postponedExecutables.clear();

		return result;
	}
}
