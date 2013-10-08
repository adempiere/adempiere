package org.adempiere.util.comparator;

import java.util.Comparator;

import org.adempiere.util.Check;

public class ReverseComparator<T> implements Comparator<T>
{
	private final Comparator<T> cmp;

	public ReverseComparator(Comparator<T> cmp)
	{
		Check.assumeNotNull(cmp, "cmp not null");
		this.cmp = cmp;
	}

	@Override
	public int compare(T o1, T o2)
	{
		return -1 * cmp.compare(o1, o2);
	}
}
