package org.adempiere.ad.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.Util;

public class CompositePOJOFilter<T> implements IPOJOFilter<T>
{
	private final List<IPOJOFilter<T>> filters = new ArrayList<IPOJOFilter<T>>();

	public CompositePOJOFilter<T> addFilter(IPOJOFilter<T> filter)
	{
		Util.assumeNotNull(filter, "filter not null");
		if (!filters.contains(filter))
		{
			filters.add(filter);
		}

		return this;
	}

	public CompositePOJOFilter<T> addFilters(final List<IPOJOFilter<T>> filters)
	{
		if (filters == null || filters.isEmpty())
		{
			return this;
		}

		for (final IPOJOFilter<T> filter : filters)
		{
			addFilter(filter);
		}

		return this;
	}

	public List<IPOJOFilter<T>> getFilters()
	{
		return new ArrayList<IPOJOFilter<T>>(filters);
	}

	@Override
	public boolean accept(T pojo)
	{
		for (final IPOJOFilter<T> filter : filters)
		{
			if (!filter.accept(pojo))
			{
				return false;
			}
		}

		return true;
	}

}
