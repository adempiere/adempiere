package org.adempiere.util.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@link Iterator} which uses a {@link Predicate} to filter the items from underlying {@link Iterator}.
 * 
 * @author tsa
 * 
 * @param <E>
 */
public class FilterIterator<E> implements Iterator<E>, IteratorWrapper<E>
{
	private final Iterator<E> iterator;
	private final Predicate<E> predicate;

	private E next;
	private boolean nextSet = false;

	public FilterIterator(final Iterator<E> iterator, final Predicate<E> predicate)
	{
		super();
		if (iterator == null)
		{
			throw new IllegalArgumentException("iterator is null");
		}
		this.iterator = iterator;
		this.predicate = predicate;
	}

	public FilterIterator(final BlindIterator<E> iterator, final Predicate<E> predicate)
	{
		this(new BlindIteratorWrapper<E>(iterator), predicate);
	}

	@Override
	public boolean hasNext()
	{
		if (nextSet)
		{
			return true;
		}
		else
		{
			return setNextValid();
		}
	}

	@Override
	public E next()
	{
		if (!nextSet)
		{
			if (!setNextValid())
			{
				throw new NoSuchElementException();
			}
		}
		nextSet = false;
		return next;
	}

	/**
	 * Set next valid element
	 * 
	 * @return true if next valid element was found and set
	 */
	public boolean setNextValid()
	{
		while (iterator.hasNext())
		{
			final E element = iterator.next();
			if (predicate.evaluate(element))
			{
				next = element;
				nextSet = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * @throws UnsupportedOperationException always
	 */
	@Override
	public void remove()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> getParentIterator()
	{
		return iterator;
	}

}
