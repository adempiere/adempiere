package org.adempiere.util.collections;

import java.io.Closeable;
import java.util.Iterator;

/**
 * Converts a {@link BlindIterator} to a true {@link Iterator} (which has {@link #hasNext()}).
 * 
 * @author tsa
 * 
 * @param <E>
 */
public class BlindIteratorWrapper<E> implements Iterator<E>, Closeable
{
	private final BlindIterator<E> iterator;

	private E next;

	public BlindIteratorWrapper(final BlindIterator<E> iterator)
	{
		super();
		if (iterator == null)
		{
			throw new IllegalArgumentException("iterator is null");
		}
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext()
	{
		if (next == null)
		{
			next = iterator.next();
		}

		return next != null;
	}

	@Override
	public E next()
	{
		if (next != null)
		{
			final E retValue = next;
			next = null;
			return retValue;
		}

		return iterator.next();
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException("remove operation not supported");
	}

	@Override
	public void close()
	{
		IteratorUtils.close(iterator);
	}

}
