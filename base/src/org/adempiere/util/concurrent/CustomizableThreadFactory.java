package org.adempiere.util.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread factory that allows to decide if a thread is a daemon or user thread.
 * 
 * The code is based on <code>java.util.concurrent.Executors.DefaultThreadFactory</code> and it brings following functionalities (more):
 * <ul>
 * <li>ability to specify which will be the name prefix for newly created threads
 * <li>ability to specify if we want to create Daemon threads or user threads
 * </ul>
 * 
 * @author tsa
 * 
 */
public class CustomizableThreadFactory implements ThreadFactory
{
	/**
	 * Counts the instances of CustomizableThreadFactory and includes the number in each instance's thread name prefix.
	 */
	private static final AtomicInteger poolNumber = new AtomicInteger(1);

	/**
	 * Thread group used to create to new threads
	 */
	private final ThreadGroup group;

	/**
	 * Current thread number in this factory.
	 */
	private final AtomicInteger threadNumber = new AtomicInteger(1);

	/**
	 * Name prefix to be used when creating new threads
	 */
	private final String namePrefix;

	/**
	 * Shall we create Daemon threads?
	 */
	private boolean daemon = false;

	/**
	 * Creates a new factory with the given <code>namePrefix</code>. New threads will be created within the thread-group of the {@link SecurityManager} returned by
	 * <code>System.getSecurityManager()</code> or (if that security manager is null) within the current thread's thread group.
	 * 
	 * @param namePrefix prefix to be used for thread names
	 */
	public CustomizableThreadFactory(final String namePrefix)
	{
		final SecurityManager s = System.getSecurityManager();
		group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
		this.namePrefix = namePrefix + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * This implementation creates a new thread with priority {@link Thread#NORM_PRIORITY}, using {@link #isDaemon()} to decide if the thread shall be a daemon thread or not.
	 */
	@Override
	public Thread newThread(Runnable r)
	{
		final Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0); // stackSize=0

		if (t.isDaemon() != daemon)
		{
			t.setDaemon(daemon);
		}
		if (t.getPriority() != Thread.NORM_PRIORITY)
		{
			t.setPriority(Thread.NORM_PRIORITY);
		}
		return t;
	}

	/**
	 * @return the daemon
	 */
	public boolean isDaemon()
	{
		return daemon;
	}

	/**
	 * Decides if the next threads created by {@link #newThread(Runnable)} shall be daemon threads or user threads.
	 * 
	 * @param daemon the daemon to set
	 */
	public void setDaemon(boolean daemon)
	{
		this.daemon = daemon;
	}

}
