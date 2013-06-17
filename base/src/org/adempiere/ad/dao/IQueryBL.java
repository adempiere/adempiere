package org.adempiere.ad.dao;

import org.adempiere.util.ISingletonService;

public interface IQueryBL extends ISingletonService
{
	IQueryOrderByBuilder createQueryOrderByBuilder();

	IQueryOrderBy createSqlQueryOrderBy(String orderBy);
}
