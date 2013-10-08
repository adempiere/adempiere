package org.adempiere.util.comparator;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class ComparableComparator<T extends Comparable> implements Comparator<T>
{
	private static final transient ComparableComparator<Comparable> instance = new ComparableComparator<Comparable>();

	public static <T extends Comparable> ComparableComparator<T> getInstance()
	{
		@SuppressWarnings("unchecked")
		final ComparableComparator<T> cmp = (ComparableComparator<T>)instance;
		return cmp;
	}

	public static <T extends Comparable> ComparableComparator<T> getInstance(Class<T> clazz)
	{
		return getInstance();
	}

	@Override
	public int compare(T o1, T o2)
	{
		@SuppressWarnings("unchecked")
		final int cmp = o1.compareTo(o2);
		return cmp;
	}

}
