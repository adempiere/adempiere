package org.adempiere.ad.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.ad.service.ITrxListener;
import org.adempiere.ad.service.ITrxListenerManager;
import org.compiere.util.CLogger;

/**
 * Default {@link ITrxListenerManager} implementation
 * @author tsa
 *
 */
public class TrxListenerManager implements ITrxListenerManager
{
	private static final CLogger logger = CLogger.getCLogger(TrxListenerManager.class);

	private volatile List<ITrxListener> listeners = null;

	public TrxListenerManager()
	{
	}

	@Override
	public void registerListener(final ITrxListener listener)
	{
		if (listener == null)
		{
			return;
		}

		if (listeners == null)
		{
			listeners = new ArrayList<ITrxListener>();
		}
		if (listeners.contains(listener))
		{
			return;
		}
		listeners.add(listener);
	}

	@Override
	public void fireAfterCommit(final String trxName)
	{
		if (listeners == null)
		{
			return;
		}

		for (final ITrxListener listener : listeners)
		{
			try
			{
				listener.afterCommit(trxName);
			}
			catch (Exception e)
			{
				logger.log(Level.SEVERE, "Error while invoking afterCommit on transaction " + trxName + " using " + listener + ". Error was discarded.", e);
			}
		}
	}
}
