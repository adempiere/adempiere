package org.adempiere.util.comparator;

import java.io.Serializable;
import java.util.Comparator;

/**
 * A {@link Comparator} which does nothing.
 * 
 * @author tsa
 * 
 */
public final class NullComparator implements Comparator<Object>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8769676939269341332L;

	@SuppressWarnings("rawtypes")
	private static final Comparator instance = new NullComparator();

	public static <T> Comparator<T> getInstance()
	{
		@SuppressWarnings("unchecked")
		final Comparator<T> cmp = (Comparator<T>)instance;
		return cmp;
	}

	private NullComparator()
	{
		super();
	}

	@Override
	public int compare(Object o1, Object o2)
	{
		return 0;
	}

}
