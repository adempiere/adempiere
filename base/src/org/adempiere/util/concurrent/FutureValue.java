package org.adempiere.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * A value which will be set in future.
 * 
 * Invocations to {@link #get()} and {@link #get(long, TimeUnit)} will block until some thread will set a value by using {@link #set(Object)} or it will set an exception by using
 * {@link #setError(Exception)}.
 * 
 * @author tsa
 * 
 * @param <T>
 */
public class FutureValue<T> implements Future<T>
{
	private T value = null;
	private Exception exception = null;

	private boolean done = false;
	private final CountDownLatch latch = new CountDownLatch(1);

	public void set(final T value)
	{
		if (done)
		{
			throw new IllegalStateException("Value was already set");
		}
		this.value = value;
		this.exception = null;
		this.done = true;
		latch.countDown();
	}

	public void setError(final Exception e)
	{
		if (done)
		{
			throw new IllegalStateException("Value was already set");
		}
		this.value = null;
		this.exception = e;
		this.done = true;
		latch.countDown();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCancelled()
	{
		return false;
	}

	@Override
	public boolean isDone()
	{
		return done;
	}

	@Override
	public T get() throws InterruptedException, ExecutionException
	{
		latch.await();
		return get0();
	}

	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException
	{
		latch.await(timeout, unit);
		return get0();
	}

	private T get0() throws ExecutionException
	{
		if (!done)
		{
			throw new IllegalStateException("Value not available");
		}
		if (exception != null)
		{
			throw new ExecutionException(exception);
		}
		return value;
	}
}
