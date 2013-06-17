package org.adempiere.util.collections;

import java.util.Iterator;

// based on com.google.common.collect.Iterators.PeekingImpl
public class PeekIteratorWrapper<E> implements PeekIterator<E>, IteratorWrapper<E>
{
	private final Iterator<E> iterator;
	private boolean hasPeeked;
	private E peekedElement;

	public PeekIteratorWrapper(final Iterator<E> iterator)
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
		return hasPeeked || iterator.hasNext();
	}

	@Override
	public E next()
	{
		if (!hasPeeked)
		{
			final E result = iterator.next();
			return result;
		}

		final E result = peekedElement;
		hasPeeked = false;
		peekedElement = null;
		return result;
	}

	@Override
	public void remove()
	{
		if (!hasPeeked)
		{
			throw new IllegalStateException("Can't remove after you've peeked at next");
		}
		iterator.remove();
	}

	@Override
	public E peek()
	{
		if (!hasPeeked)
		{
			peekedElement = iterator.next();
			hasPeeked = true;
		}
		return peekedElement;
	}

	@Override
	public Iterator<E> getParentIterator()
	{
		return iterator;
	}

}
