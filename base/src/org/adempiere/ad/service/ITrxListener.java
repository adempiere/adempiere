package org.adempiere.ad.service;

/**
 * Transaction listener
 * 
 * @author tsa
 * 
 */
public interface ITrxListener
{
	/**
	 * Method called after a transaction was successfully committed
	 * 
	 * @param trxName
	 */
	void afterCommit(String trxName);
}
