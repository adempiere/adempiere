package org.adempiere.ad.migration.executor.impl;

import java.util.Properties;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IMigrationExecutorProvider;

public class MigrationExecutorContext implements IMigrationExecutorContext
{

	private final Properties ctx;
	private final IMigrationExecutorProvider factory;

	private boolean failOnFirstError = true;

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
}
