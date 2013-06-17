package org.adempiere.ad.service;

/**
 * Transactions Listeners Mananger
 * 
 * @author tsa
 * 
 */
public interface ITrxListenerManager
{

	void registerListener(ITrxListener listener);

	void fireAfterCommit(String trxName);

}
