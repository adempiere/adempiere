package org.compiere.model;

import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.exceptions.DBException;

public interface IQuery
{
	/**
	 * 
	 * @return a copy of this object
	 */
	IQuery copy();

	IQuery setOrderBy(IQueryOrderBy orderBy);

	String getTableName();

	<T> List<T> list(Class<T> clazz) throws DBException;

	<T> T first(Class<T> clazz) throws DBException;

	<T> T firstOnly(Class<T> clazz) throws DBException;

	int count() throws DBException;
}
