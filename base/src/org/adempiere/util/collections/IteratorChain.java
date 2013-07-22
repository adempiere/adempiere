package org.adempiere.util.collections;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * An {@link Iterator} implementation which chains a colection of iterators.
 * 
 * @author tsa
 * 
 * @param <E>
 */
public class IteratorChain<E> implements Iterator<E>, Closeable
{
	private final List<Iterator<E>> iterators = new ArrayList<Iterator<E>>();
	private boolean addingLocked = false;

	/**
	 * Add another iterator at the end of the chain.
	 * 
	 * @param it
	 * @return this
	 * @throws IllegalArgumentException if <code>it</code> is null
	 * @throws IllegalStateException if the chain is locked due to some read/write operations
	 */
	public IteratorChain<E> addIterator(Iterator<E> it)
	{
		if (it == null)
		{
			throw new IllegalArgumentException("it is null");
		}
		if (addingLocked)
		{
			throw new IllegalStateException("Cannot add more iterators after chain was locked due to a read operation");
		}

		iterators.add(it);

		return this;
	}

	@Override
	public boolean hasNext()
	{
		addingLocked = true;

		while (!iterators.isEmpty())
		{
			final Iterator<E> it = iterators.get(0);
			final boolean hasNext = it.hasNext();
			if (hasNext)
			{
				return true;
			}

			final Iterator<E> removedIterator = iterators.remove(0);
			IteratorUtils.close(removedIterator);
		}

		return false;
	}

	@Override
	public E next()
	{
		addingLocked = true;
		return iterators.get(0).next();
	}

	@Override
	public void remove()
	{
		iterators.get(0).remove();
		addingLocked = true;
	}

	@Override
	public void close()
	{
		for (final Iterator<?> iterator : iterators)
		{
			IteratorUtils.close(iterator);
		}
	}
}
