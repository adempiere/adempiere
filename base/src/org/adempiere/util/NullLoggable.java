package org.adempiere.util;

/**
 * An {@link ILoggable} which does nothing
 * 
 * @author tsa
 * 
 */
public final class NullLoggable implements ILoggable
{
	public static final NullLoggable instance = new NullLoggable();

	private NullLoggable()
	{
		super();
	}

	@Override
	public void addLog(String msg)
	{
		// nothing
	}

}
