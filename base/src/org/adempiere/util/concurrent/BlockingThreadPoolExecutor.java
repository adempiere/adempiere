package org.adempiere.util.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.adempiere.util.Check;

/**
 * An {@link ThreadPoolExecutor} which blocks on submitting new tasks, if the maximum pool size was reached.
 * 
 * @author tsa
 * 
 */
public class BlockingThreadPoolExecutor extends ThreadPoolExecutor
{
	private final Semaphore semaphore;

	/**
	 * 
	 * @param corePoolSize
	 * @param maximumPoolSize maximum allowed threads. This value shall be greater than zero.
	 * @param keepAliveTime
	 * @param unit
	 * @param threadFactory
	 * @see ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, TimeUnit, java.util.concurrent.BlockingQueue, ThreadFactory, RejectedExecutionHandler)
	 */
	public BlockingThreadPoolExecutor(int corePoolSize,
			int maximumPoolSize,
			long keepAliveTime,
			TimeUnit unit,
			ThreadFactory threadFactory)
	{
		// NOTE: A short example about how the ThreadPoolExecutor works together with this blocking (by using semaphores) works
		// Consider:
		// corePoolSize=2
		// => workQueue size = 2
		// maximumPoolSize=10
		// => semaphore size = 10
		//
		// In this case, when you start the processor which will submit tasks one by one (infinitely) you will see only 8 working threads.
		// This is because:
		// * 8 tasks will go to processing tasks
		// * 2 tasks will be in internal workQueue
		// * after 10 submited tasks the semaphore's counter will be ZERO, so it will block
		super(corePoolSize,
				maximumPoolSize,
				keepAliveTime,
				unit,
				// workQueue - the working queue needs to be bounded (have a size) else you will encount following issue: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6756747
				new LinkedBlockingQueue<Runnable>(corePoolSize),
				threadFactory,
				// RejectedExecutionHandler: AbortPolicy. Shall never happen because we use a semaphore which we use to check if there are available slots
				new ThreadPoolExecutor.AbortPolicy());

		Check.assume(maximumPoolSize > 0, "maximumPoolSize={0} > 0", maximumPoolSize);

		// NOTE: for future development: maybe we shall initialize the semaphore with maximumPoolSize + workQueue.capacity in order to have number of running tasks equal with maxPoolSize.
		// Before please investigate first.

		semaphore = new Semaphore(maximumPoolSize);
	}

	/**
	 * @throws UnsupportedOperationException always, because changing corePoolSize in BlockingThreadPoolExecutor is not allowed because there are semaphores which depend on this value
	 */
	@Override
	public void setCorePoolSize(int corePoolSize)
	{
		throw new UnsupportedOperationException("Changing corePoolSize in BlockingThreadPoolExecutor is not allowed because there are semaphores which depend on this value");
	}

	/**
	 * @throws UnsupportedOperationException always, because changing maximumPoolSize in BlockingThreadPoolExecutor is not allowed because there are semaphores which depend on this value
	 */
	@Override
	public void setMaximumPoolSize(int maximumPoolSize)
	{
		throw new UnsupportedOperationException("Changing maximumPoolSize in BlockingThreadPoolExecutor is not allowed because there are semaphores which depend on this value");
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * NOTE: if the maximum pool size was reached, this method blocks until we have free slots to execute this task.
	 * 
	 * @param task
	 */
	@Override
	public void execute(final Runnable task)
	{
		boolean acquired = false;
		do
		{
			try
			{
				semaphore.acquire();
				acquired = true;
			}
			catch (InterruptedException e)
			{
				// wait forever!
			}
		}
		while (!acquired);

		boolean executed = false;
		try
		{
			super.execute(task);
			executed = true;
		}
		finally
		{
			if (!executed)
			{
				// an exception was thrown, releasing the semaphore here because in this case, afterExecute won't be called
				semaphore.release();
			}
		}
	}

	@Override
	protected final void afterExecute(final Runnable r, final Throwable t)
	{
		// NOTE: if we reach this point, our task was successfully submitted and semaphore acquired, so it's safe to release the semaphore here.
		semaphore.release();
	}
}
