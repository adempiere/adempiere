package org.adempiere.ad.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.dao.IQueryOrderByBuilder;
import org.compiere.util.Util;

class QueryOrderByBuilder implements IQueryOrderByBuilder
{
	private List<QueryOrderByItem> orderBys = new ArrayList<QueryOrderByItem>();

	public QueryOrderByBuilder()
	{
	}

	/* (non-Javadoc)
	 * @see org.adempiere.ad.dao.impl.IQueryOrderByBuilder#addColumn(java.lang.String)
	 */
	@Override
	public IQueryOrderByBuilder addColumn(String columnName)
	{
		return addColumn(columnName, true);
	}

	/* (non-Javadoc)
	 * @see org.adempiere.ad.dao.impl.IQueryOrderByBuilder#addColumn(java.lang.String, boolean)
	 */
	@Override
	public IQueryOrderByBuilder addColumn(String columnName, boolean asc)
	{
		Util.assumeNotNull(columnName, "columnName not null");

		final QueryOrderByItem orderByItem = new QueryOrderByItem(columnName, asc);
		orderBys.add(orderByItem);

		return this;
	}

	/* (non-Javadoc)
	 * @see org.adempiere.ad.dao.impl.IQueryOrderByBuilder#create()
	 */
	@Override
	public IQueryOrderBy create()
	{
		final QueryOrderBy orderBy = new QueryOrderBy(orderBys);
		return orderBy;
	}
}
