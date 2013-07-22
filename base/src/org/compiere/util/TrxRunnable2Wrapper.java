/**
 * 
 */
package org.compiere.util;

/**
 * @author tsa
 *
 */
public class TrxRunnable2Wrapper implements TrxRunnable2
{
	private final TrxRunnable runnable;

	public TrxRunnable2Wrapper(TrxRunnable r)
	{
		this.runnable = r;
	}
	@Override
	public void run(String trxName) throws Exception
	{
		runnable.run(trxName);
	}

	@Override
	public boolean doCatch(Throwable e) throws Throwable
	{
		throw e;
	}

	@Override
	public void doFinally()
	{
	}

}
