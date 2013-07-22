package org.adempiere.util.collections;

import java.util.NoSuchElementException;

/**
 * A Blind Iterator it's an iterator which does not have the look-ahead method (i.e. hasNext()).
 * 
 * It only has the {@link #next()} which returns the next element or NULL if there are no more elements.
 * 
 * @author tsa
 * 
 * @param <E>
 */
public interface BlindIterator<E>
{
	/**
	 * Returns the next element in the iteration or <code>null</code> if there are no more elements.
	 * 
	 * @return the next element in the iteration or null
	 * @throws NoSuchElementException implementations could throw this exception if repeatedly called after end was reached
	 */
	public E next();
}
