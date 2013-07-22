package org.adempiere.ad.dao.impl;

import java.util.Comparator;

import org.adempiere.ad.dao.IQueryOrderBy;

public class SqlQueryOrderBy implements IQueryOrderBy
{
	private final String orderBy;

	public SqlQueryOrderBy(final String orderBy)
	{
		this.orderBy = normalize(orderBy);
	}

	private static final String normalize(final String orderBy)
	{
		String orderByFinal = orderBy != null ? orderBy.trim() : null;
		if (orderByFinal != null && orderByFinal.toUpperCase().startsWith("ORDER BY"))
		{
			orderByFinal = orderBy.substring(8);
		}
		return orderByFinal;
	}

	@Override
	public String getSql()
	{
		return orderBy;
	}

	@Override
	public Comparator<Object> getComparator()
	{
		throw new UnsupportedOperationException("SqlQueryOrderBy does not support Comparator");
	}

	@Override
	public String toString()
	{
		return "SqlQueryOrderBy[" + orderBy + "]";
	}
}
