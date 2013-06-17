package org.adempiere.util.collections;

import java.util.Iterator;

/**
 * An iterator that supports a one-element lookahead while iterating.
 * 
 * @author tsa
 * 
 * @param <E>
 */
public interface PeekIterator<E> extends Iterator<E>
{
	/**
	 * Returns the next element in the iteration, without advancing the iteration.
	 * 
	 * @return
	 */
	public E peek();
}
