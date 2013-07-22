package org.adempiere.ad.dao;


public interface IQueryOrderByBuilder
{

	public abstract IQueryOrderByBuilder addColumn(String columnName);

	public abstract IQueryOrderByBuilder addColumn(String columnName, boolean asc);

	public abstract IQueryOrderBy create();

}