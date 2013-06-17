package org.adempiere.ad.dao.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.util.comparator.AccessorComparator;
import org.adempiere.util.comparator.ComparableComparator;
import org.adempiere.util.comparator.ComparatorChain;
import org.adempiere.util.comparator.NullComparator;

class QueryOrderBy implements IQueryOrderBy
{
	private List<QueryOrderByItem> items;

	private boolean sqlOrderByCompiled = false;
	private String sqlOrderBy = null;

	private Comparator<Object> comparator = null;

	public QueryOrderBy(final List<QueryOrderByItem> items)
	{
		this.items = items == null ? null : new ArrayList<QueryOrderByItem>(items);
	}

	@Override
	public String toString()
	{
		return "QueryOrderBy[" + items + "]";
	}

	@Override
	public String getSql()
	{
		if (sqlOrderByCompiled)
		{
			return sqlOrderBy;
		}

		if (items != null && !items.isEmpty())
		{
			final StringBuilder sb = new StringBuilder();
			for (QueryOrderByItem item : items)
			{
				if (sb.length() > 0)
				{
					sb.append(", ");
				}
				sb.append(item.getColumnName()).append(item.isAsc() ? " ASC" : " DESC");
			}
			sqlOrderBy = sb.toString();
		}
		else
		{
			sqlOrderBy = null;
		}
		sqlOrderByCompiled = true;

		return sqlOrderBy;
	}

	@Override
	public Comparator<Object> getComparator()
	{
		if (comparator != null)
		{
			return comparator;
		}

		if (items != null && !items.isEmpty())
		{
			final ComparatorChain<Object> cmpChain = new ComparatorChain<Object>();
			for (final QueryOrderByItem item : items)
			{
				@SuppressWarnings("rawtypes")
				final ModelAccessor<Comparable> accessor = new ModelAccessor<Comparable>(item.getColumnName());

				@SuppressWarnings("rawtypes")
				final Comparator<Object> cmp = new AccessorComparator<Object, Comparable>(
						ComparableComparator.getInstance(),
						accessor);

				cmpChain.addComparator(cmp, !item.isAsc());
			}
			comparator = cmpChain;
		}
		else
		{
			comparator = NullComparator.getInstance();
		}

		return comparator;
	}
}
