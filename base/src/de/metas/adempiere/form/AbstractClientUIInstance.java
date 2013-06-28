package de.metas.adempiere.form;

import java.util.logging.Level;

import org.compiere.util.CLogger;

/**
 * Implements some common methods which are not directly related to a particular user interface
 * 
 * @author tsa
 * 
 */
public abstract class AbstractClientUIInstance implements IClientUIInstance
{
	protected final CLogger logger = CLogger.getCLogger(getClass());

	@Override
	public void warn(int WindowNo, Throwable e)
	{
		final String AD_Message = "Error";
		final String message = e == null ? "@UnknownError@" : e.getLocalizedMessage();
		warn(WindowNo, AD_Message, message);

		logger.log(Level.FINE, message, e);
	}

	@Override
	public Thread createUserThread(Runnable runnable, String threadName)
	{
		final Thread thread;
		if (threadName == null)
		{
			thread = new Thread(runnable);
		}
		else
		{
			thread = new Thread(runnable, threadName);
		}

		thread.setDaemon(true);

		return thread;
	}
}
