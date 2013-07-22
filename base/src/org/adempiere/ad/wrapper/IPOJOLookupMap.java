package org.adempiere.ad.wrapper;

/**
 * Interface used by {@link POJOWrapper} when it needs to lookup for records
 * 
 * @author tsa
 * 
 */
public interface IPOJOLookupMap
{
	int nextId(String tableName);

	<T> T lookup(Class<T> clazz, int id);

	void save(Object model);

	boolean delete(Object model);
}
