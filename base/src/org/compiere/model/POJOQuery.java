package org.compiere.model;

import java.util.Comparator;
import java.util.List;

import org.adempiere.ad.dao.IQueryOrderBy;
import org.adempiere.ad.wrapper.CompositePOJOFilter;
import org.adempiere.ad.wrapper.IPOJOFilter;
import org.adempiere.ad.wrapper.POJOLookupMap;
import org.adempiere.exceptions.DBException;
import org.adempiere.model.InterfaceWrapperHelper;

public class POJOQuery implements IQuery
{
	private final Class<?> modelClass;
	private final CompositePOJOFilter<Object> filters = new CompositePOJOFilter<Object>();
	private IQueryOrderBy orderBy;

	public POJOQuery(Class<?> modelClass)
	{
		this.modelClass = modelClass;
	}

	@Override
	public String toString()
	{
		return "POJOQuery ["
				+ "modelClass=" + modelClass
				+ ", filters=" + filters
				+ ", orderBy=" + orderBy
				+ "]";
	}

	public POJOQuery addFilter(IPOJOFilter<Object> filter)
	{
		filters.addFilter(filter);
		return this;
	}

	@Override
	public String getTableName()
	{
		return InterfaceWrapperHelper.getTableName(modelClass);
	}

	@Override
	public POJOQuery copy()
	{
		final POJOQuery queryNew = new POJOQuery(modelClass);
		queryNew.filters.addFilters(filters.getFilters());
		queryNew.orderBy = this.orderBy;
		return queryNew;
	}

	/**
	 * @return the orderByComparator
	 */
	private Comparator<Object> getOrderByComparator()
	{
		if (orderBy == null)
		{
			return null;
		}
		return orderBy.getComparator();
	}

	private <T> Comparator<T> getOrderByComparator(Class<T> clazz)
	{
		final Comparator<Object> orderByComparator = getOrderByComparator();
		if (orderByComparator == null)
		{
			return null;
		}

		return new Comparator<T>()
		{

			@Override
			public int compare(T o1, T o2)
			{
				return orderByComparator.compare(o1, o2);
			}
		};
	}

	@Override
	public POJOQuery setOrderBy(final IQueryOrderBy orderBy)
	{
		this.orderBy = orderBy;
		return this;
	}

	@Override
	public <T> List<T> list(Class<T> clazz) throws DBException
	{
		final POJOLookupMap db = POJOLookupMap.get();
		final List<T> result = db.getRecords(clazz, getFilters(clazz), getOrderByComparator(clazz));
		return result;
	}

	@Override
	public <T> T first(Class<T> clazz) throws DBException
	{
		final POJOLookupMap db = POJOLookupMap.get();
		return db.getFirst(clazz, getFilters(clazz), getOrderByComparator(clazz));
	}

	@Override
	public <T> T firstOnly(Class<T> clazz) throws DBException
	{
		final POJOLookupMap db = POJOLookupMap.get();
		return db.getFirstOnly(clazz, getFilters(clazz), getOrderByComparator(clazz));
	}

	@Override
	public int count() throws DBException
	{
		final POJOLookupMap db = POJOLookupMap.get();

		@SuppressWarnings("unchecked")
		Class<Object> clazz = (Class<Object>)modelClass;

		return db.getRecords(clazz, getFilters(clazz)).size();
	}

	private <T> IPOJOFilter<T> getFilters(final Class<T> modelClass)
	{
		return new IPOJOFilter<T>()
		{

			@Override
			public boolean accept(T pojo)
			{
				return filters.accept(pojo);
			}
		};
	}

}
