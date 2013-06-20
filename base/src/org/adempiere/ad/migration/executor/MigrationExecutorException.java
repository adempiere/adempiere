package org.adempiere.ad.migration.executor;

import org.adempiere.exceptions.AdempiereException;

public class MigrationExecutorException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5403917143953357678L;
	
	public static boolean isFatal(Throwable e)
	{
		if (e instanceof MigrationExecutorException)
		{
			final MigrationExecutorException mee = (MigrationExecutorException)e;
			return mee.isFatal();
		}
		
		return true;
	}

	private final boolean fatal;

	public MigrationExecutorException(Throwable e)
	{
		super(e);
		fatal = true;
	}

	public MigrationExecutorException(String message, boolean fatal)
	{
		super(message);
		this.fatal = fatal;
	}

	public boolean isFatal()
	{
		return fatal;
	}
}
