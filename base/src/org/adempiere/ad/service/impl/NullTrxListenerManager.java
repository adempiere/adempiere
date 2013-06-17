package org.adempiere.ad.service.impl;

import org.adempiere.ad.service.ITrxListener;
import org.adempiere.ad.service.ITrxListenerManager;

/**
 * Null {@link ITrxListenerManager} implementation
 * 
 * @author tsa
 * 
 */
public final class NullTrxListenerManager implements ITrxListenerManager
{
	public static final NullTrxListenerManager instance = new NullTrxListenerManager();

	private NullTrxListenerManager()
	{
		super();
	}

	/**
	 * @throws UnsupportedOperationException always
	 */
	@Override
	public void registerListener(ITrxListener listener)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Does nothing
	 */
	@Override
	public void fireAfterCommit(String trxName)
	{
		// nothing
	}

}
