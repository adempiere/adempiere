package org.adempiere.ad.dao.impl;

import org.adempiere.ad.dao.IQueryBL;
import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.dao.IQueryOrderByBuilder;

public class QueryBL implements IQueryBL
{
	@Override
	public IQueryOrderByBuilder createQueryOrderByBuilder()
	{
		return new QueryOrderByBuilder();
	}

	@Override
	public IQueryOrderBy createSqlQueryOrderBy(final String orderBy)
	{
		return new SqlQueryOrderBy(orderBy);
	}
}
