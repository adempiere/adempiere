package org.adempiere.ad.migration.executor;

import java.util.Properties;

public interface IMigrationExecutorContext
{

	Properties getCtx();

	boolean isFailOnFirstError();

	void setFailOnFirstError(boolean failOnFirstError);

	IMigrationExecutorProvider getMigrationExecutorProvider();

}
