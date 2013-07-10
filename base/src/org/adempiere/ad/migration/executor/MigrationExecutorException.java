package org.adempiere.ad.migration.executor;

import org.adempiere.exceptions.AdempiereException;

public class MigrationExecutorException extends AdempiereException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5403917143953357678L;

	private final boolean fatal;

	/**
	 * Wrap an {@link Throwable} exception with a {@link MigrationExecutorException}
	 * 
	 * @param e
	 */
	public MigrationExecutorException(Throwable e)
	{
		super(e);
		fatal = true;
	}

	/**
	 * Create a new {@link MigrationExecutorException}. The fatal flag should be set to true when the execution must be ended on error.
	 * 
	 * @param message
	 * @param fatal
	 */
	public MigrationExecutorException(String message, boolean fatal)
	{
		super(message);
		this.fatal = fatal;
	}

	/**
	 * Get the exception's fatal flag.
	 * 
	 * @return boolean
	 */
	public boolean isFatal()
	{
		return fatal;
	}

	/**
	 * Checks if a {@link MigrationExecutorException} is fatal for an instance of {@link Throwable}.
	 * 
	 * @param e
	 * @return boolean
	 */
	public static boolean isFatal(Throwable e)
	{
		if (e instanceof MigrationExecutorException)
		{
			final MigrationExecutorException mee = (MigrationExecutorException)e;
			return mee.isFatal();
		}

		return true;
	}
}
