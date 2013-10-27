package org.adempiere.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of an empty iterator.
 * 
 * @author tsa
 * 
 */
public final class EmptyIterator implements Iterator<Object>
{
	private static final EmptyIterator instance = new EmptyIterator();

	@SuppressWarnings("unchecked")
	public static <E> Iterator<E> getInstance()
	{
		return (Iterator<E>)instance;
	}

	private EmptyIterator()
	{
		super();
	}

	@Override
	public boolean hasNext()
	{
		return false;
	}

	@Override
	public Object next()
	{
		throw new NoSuchElementException();
	}

	@Override
	public void remove()
	{
		throw new IllegalStateException();
	}
}
