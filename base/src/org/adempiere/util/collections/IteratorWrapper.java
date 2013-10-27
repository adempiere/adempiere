package org.adempiere.util.collections;

import java.util.Iterator;

public interface IteratorWrapper<E>
{
	/**
	 * Returns the parent iterator, which is wrapped
	 * 
	 * @return parent iterator
	 */
	public Iterator<E> getParentIterator();
}
