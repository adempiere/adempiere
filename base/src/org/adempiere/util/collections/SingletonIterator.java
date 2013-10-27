package org.adempiere.util.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Immutable iterator of a single element
 */
public class SingletonIterator<E> implements Iterator<E>
{
	private final E element;
	private boolean consumed = false;

	public SingletonIterator(E element)
	{
		this.element = element;
	}

	@Override
	public boolean hasNext()
	{
		return !consumed;
	}

	@Override
	public E next()
	{
		if (consumed)
		{
			throw new NoSuchElementException();
		}

		consumed = true;
		return element;
	}

	@Override
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

}
