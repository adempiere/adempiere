/**
 * 
 */
package de.metas.adempiere.form;

import org.compiere.util.CLogger;

/**
 * @author tsa
 * 
 */
public abstract class AbstractClientUI implements IClientUI
{
	protected final CLogger logger = CLogger.getCLogger(getClass());

	/**
	 * Gets the instance in current running desktop
	 * 
	 * @return
	 */
	protected abstract IClientUIInstance getCurrentInstance();

	@Override
	public void info(int WindowNo, String AD_Message)
	{
		getCurrentInstance().info(WindowNo, AD_Message);
	}

	@Override
	public void info(int WindowNo, String AD_Message, String message)
	{
		getCurrentInstance().info(WindowNo, AD_Message, message);
	}

	@Override
	public boolean ask(int WindowNo, String AD_Message)
	{
		return getCurrentInstance().ask(WindowNo, AD_Message);
	}

	@Override
	public boolean ask(int WindowNo, String AD_Message, String message)
	{
		return getCurrentInstance().ask(WindowNo, AD_Message, message);
	}

	@Override
	public void warn(int WindowNo, String AD_Message)
	{
		getCurrentInstance().warn(WindowNo, AD_Message);
	}

	@Override
	public void warn(int WindowNo, String AD_Message, String message)
	{
		getCurrentInstance().warn(WindowNo, AD_Message, message);
	}

	@Override
	public void warn(int WindowNo, Throwable e)
	{
		getCurrentInstance().warn(WindowNo, e);
	}

	@Override
	public void error(int WIndowNo, String AD_Message)
	{
		getCurrentInstance().error(WIndowNo, AD_Message);
	}

	@Override
	public void error(int WIndowNo, String AD_Message, String message)
	{
		getCurrentInstance().error(WIndowNo, AD_Message, message);
	}

	@Override
	public void download(byte[] data, String contentType, String filename)
	{
		getCurrentInstance().download(data, contentType, filename);
	}

	@Override
	public void invokeLater(int windowNo, Runnable runnable)
	{
		getCurrentInstance().invokeLater(windowNo, runnable);
	}

	@Override
	public Thread createUserThread(final Runnable runnable, final String threadName)
	{
		return getCurrentInstance().createUserThread(runnable, threadName);
	}

}
