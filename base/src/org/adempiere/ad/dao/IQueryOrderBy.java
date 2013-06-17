package org.adempiere.ad.dao;

import java.util.Comparator;

public interface IQueryOrderBy
{

	String getSql();

	Comparator<Object> getComparator();

}
