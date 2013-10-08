package org.adempiere.util.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.adempiere.util.Check;

public class ComparatorChain<T> implements Comparator<T>
{
	private final List<Comparator<T>> comparators = new ArrayList<Comparator<T>>();

	public ComparatorChain()
	{
	}

	public ComparatorChain<T> addComparator(Comparator<T> cmp)
	{
		final boolean reverse = false;
		return addComparator(cmp, reverse);
	}

	public ComparatorChain<T> addComparator(Comparator<T> cmp, boolean reverse)
	{
		Check.assumeNotNull(cmp, "cmp not null");
		if (reverse)
		{
			comparators.add(new ReverseComparator<T>(cmp));
		}
		else
		{
			comparators.add(cmp);
		}

		return this;
	}

	@Override
	public int compare(T o1, T o2)
	{
		Check.assume(!comparators.isEmpty(), "ComparatorChain contains comparators");

		for (final Comparator<T> cmp : comparators)
		{
			final int result = cmp.compare(o1, o2);
			if (result != 0)
			{
				return result;
			}
		}

		// assuming that objects are equal if none of child comparators said otherwise
		return 0;
	}

	@Override
	public String toString()
	{
		return "ComparatorChain [comparators=" + comparators + "]";
	}
}
