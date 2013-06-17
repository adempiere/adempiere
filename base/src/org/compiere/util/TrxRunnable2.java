/**
 * 
 */
package org.compiere.util;

/**
 * @author tsa
 *
 */
public interface TrxRunnable2 extends TrxRunnable
{
	@Override
	public void run(String localTrxName) throws Exception;
	
	/**
	 * Method called when {@link #run(String)} throws an exception.
	 * In this method you can handle this exception or throw another exception.
	 * If an exception is thrown or method returns true, the transaction will be rollback.
	 * 
	 * Please note, this method is called before transaction is rolled-back or savepoint is released
	 * @param e exception
	 * @return true if transaction should be rollback
	 */
	public boolean doCatch(Throwable e) throws Throwable;
	
	/**
	 * Method called after {@link #run(String)} runs.
	 * 
	 * Please note, this method is called AFTER transaction is rolled-back or savepoint is released
	 */
	public void doFinally();
	
}
