package org.adempiere.util.concurrent;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * It's a wrapper which converts a value into a future value which is available right away.
 * 
 * @author tsa
 * 
 * @param <T>
 */
public class InstantFuture<T> implements Future<T>
{
	private final T value;

	public InstantFuture(final T value)
	{
		this.value = value;
	}

	/**
	 * @return already completed, always return false
	 */
	@Override
	public boolean cancel(boolean mayInterruptIfRunning)
	{
		// already completed, always return false
		return false;
	}

	@Override
	public boolean isCancelled()
	{
		return false;
	}

	@Override
	public boolean isDone()
	{
		return true;
	}

	@Override
	public T get()
	{
		return value;
	}

	@Override
	public T get(long timeout, TimeUnit unit)
	{
		return value;
	}
}
