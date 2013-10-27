package org.adempiere.util.collections;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class IteratorUtils
{
	public static <E> Iterable<E> asIterable(final Iterator<E> it)
	{
		return new Iterable<E>()
		{

			@Override
			public Iterator<E> iterator()
			{
				return it;
			}
		};
	}

	/**
	 * Converts an {@link Iterator} to a {@link List} by fetching all of its elements.
	 * 
	 * NOTE: if Iterator is closeable, it will be closed.
	 * 
	 * @param it
	 * @return list
	 */
	public static <E> List<E> asList(final Iterator<E> it)
	{
		try
		{
			if (!it.hasNext())
			{
				return Collections.emptyList();
			}

			final List<E> list = new ArrayList<E>();
			while (it.hasNext())
			{
				final E e = it.next();
				list.add(e);
			}

			return Collections.unmodifiableList(list);
		}
		finally
		{
			close(it);
		}
	}

	public static void close(final Iterator<?> iterator)
	{
		if (iterator == null)
		{
			return;
		}

		if (iterator instanceof Closeable)
		{
			final Closeable closeable = (Closeable)iterator;
			try
			{
				closeable.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		else if (iterator instanceof IteratorWrapper)
		{
			final IteratorWrapper<?> wrapper = (IteratorWrapper<?>)iterator;
			final Iterator<?> parentIterator = wrapper.getParentIterator();
			close(parentIterator);
		}

		// iterator is not closeable, nothing to do
	}

	public static void close(final BlindIterator<?> iterator)
	{
		if (iterator == null)
		{
			return;
		}

		if (iterator instanceof Closeable)
		{
			final Closeable closeable = (Closeable)iterator;
			try
			{
				closeable.close();
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		else if (iterator instanceof IteratorWrapper)
		{
			final IteratorWrapper<?> wrapper = (IteratorWrapper<?>)iterator;
			final Iterator<?> parentIterator = wrapper.getParentIterator();
			close(parentIterator);
		}

		// iterator is not closeable, nothing to do
	}
	
	public static <E> PeekIterator<E> asPeekIterator(final Iterator<E> iterator)
	{
		if (iterator instanceof PeekIterator)
		{
			final PeekIterator<E> peekIterator = (PeekIterator<E>)iterator;
			return peekIterator;
		}
		else
		{
			return new PeekIteratorWrapper<E>(iterator);
		}
	}
}
