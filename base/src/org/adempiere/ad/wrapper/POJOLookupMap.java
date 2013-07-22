package org.adempiere.ad.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.compiere.model.MTable;
import org.compiere.util.Env;
import org.compiere.util.Util;

public class POJOLookupMap implements IPOJOLookupMap
{
	private static final POJOLookupMap instance = new POJOLookupMap();

	public static POJOLookupMap get()
	{
		return instance;
	}

	private static final int DEFAULT_FirstId = 10000;
	private static int nextId = DEFAULT_FirstId;

	/**
	 * Map of cached objects (TableName -> Record_ID -> Object)
	 */
	private Map<String, Map<Integer, Object>> cachedObjects = new HashMap<String, Map<Integer, Object>>();

	protected POJOLookupMap()
	{
		super();
	}

	@Override
	public int nextId(String tableName)
	{
		nextId++;
		return nextId;
	}

	@Override
	public <T> T lookup(Class<T> clazz, int id)
	{
		final String tableName = POJOWrapper.getTableNameOrNull(clazz);
		if (tableName == null)
		{
			return null;
		}

		Map<Integer, Object> values = cachedObjects.get(tableName);
		if (values == null || values.isEmpty())
		{
			throw new RuntimeException("No cached object found for clazz=" + clazz + ", id=" + id);
		}

		final Object result = values.get(id);
		if (result == null)
		{
			throw new RuntimeException("No cached object found for clazz=" + clazz + ", id=" + id);
		}
		
		if (clazz.isAssignableFrom(result.getClass()))
		{
			@SuppressWarnings("unchecked")
			final T resultConv = (T)result;
			return resultConv;
		}

		final T resultConv = POJOWrapper.create(result, clazz);
		return resultConv;
	}

	public <T> T lookup(int tableId, int recordId)
	{
		for (String tableName : cachedObjects.keySet())
		{
			if (tableId == MTable.getTable_ID(tableName))
			{
				final Map<Integer, Object> recordsMap = cachedObjects.get(tableName);
				if (recordsMap == null)
				{
					return null;
				}

				@SuppressWarnings("unchecked")
				final T value = (T)recordsMap.get(recordId);
				return value;
			}
		}

		return null;
	}

	public <T> T lookup(final int tableId, final int recordId, final Class<T> modelClass)
	{
		final Object o = lookup(tableId, recordId);
		if (o == null)
		{
			return null;
		}
		return POJOWrapper.create(o, modelClass);
	}

	public <T> T newInstance(Properties ctx, Class<T> interfaceClass)
	{
		final T model = POJOWrapper.create(ctx, interfaceClass, this);
		return model;
	}

	/**
	 * Create a new object using global ctx (Env.getCtx()) and Trx.TRX_None as transaction.
	 * 
	 * @param interfaceClass
	 * @return
	 */
	public <T> T newInstance(Class<T> interfaceClass)
	{
		return newInstance(Env.getCtx(), interfaceClass);
	}

	@Override
	public void save(Object model)
	{
		if (model == null)
		{
			throw new IllegalArgumentException("model is null");
		}
		final POJOWrapper wrapper = POJOWrapper.getWrapper(model);
		if (wrapper == null)
		{
			throw new IllegalArgumentException("model '" + model + "' is not wrapped from " + POJOWrapper.class);
		}

		final String tableName = wrapper.getTableName();

		int id = wrapper.getId();
		if (id < 0)
		{
			id = nextId(tableName);
			wrapper.setId(id);
		}

		Map<Integer, Object> tableCachedObjects = cachedObjects.get(tableName);
		if (tableCachedObjects == null)
		{
			// we use LinkedHashMap to preserve the order in which the objects are saved
			tableCachedObjects = new LinkedHashMap<Integer, Object>();
			cachedObjects.put(tableName, tableCachedObjects);
		}
		tableCachedObjects.put(id, model);
	}

	public <T> List<T> getRecords(final Class<T> clazz)
	{
		final IPOJOFilter<T> filter = null;
		final Comparator<T> orderByComparator = null;
		return getRecords(clazz, filter, orderByComparator);
	}

	public <T> List<T> getRecords(final Class<T> clazz, final IPOJOFilter<T> filter)
	{
		final Comparator<T> orderByComparator = null;
		return getRecords(clazz, filter, orderByComparator);
	}

	public <T> List<T> getRecords(final Class<T> clazz, final IPOJOFilter<T> filter, final Comparator<T> orderByComparator)
	{
		final String tableName = POJOWrapper.getTableName(clazz);

		final Map<Integer, Object> recordsMap = cachedObjects.get(tableName);
		if (recordsMap == null || recordsMap.isEmpty())
		{
			return Collections.emptyList();
		}

		final List<T> result = filter == null ? new ArrayList<T>() : new ArrayList<T>(recordsMap.size());
		for (Object o : recordsMap.values())
		{
			final T record = POJOWrapper.create(o, clazz);
			if (filter == null || filter.accept(record))
			{
				result.add(record);
			}
		}

		if (orderByComparator != null)
		{
			Collections.sort(result, orderByComparator);
		}

		return result;
	}

	public <T> T getFirstOnly(Class<T> clazz, IPOJOFilter<T> filter)
	{
		final Comparator<T> orderByComparator = null;
		return getFirstOnly(clazz, filter, orderByComparator);
	}

	public <T> T getFirstOnly(Class<T> clazz, IPOJOFilter<T> filter, final Comparator<T> orderByComparator)
	{
		final List<T> result = getRecords(clazz, filter, orderByComparator);
		if (result == null || result.isEmpty())
		{
			return null;
		}
		if (result.size() == 1)
		{
			return result.get(0);
		}

		throw new RuntimeException("More then one record were found for " + clazz + ", filtered by " + filter + ": " + result);
	}

	public <T> T getFirst(Class<T> clazz, IPOJOFilter<T> filter, final Comparator<T> orderByComparator)
	{
		final List<T> result = getRecords(clazz, filter, orderByComparator);
		if (result == null || result.isEmpty())
		{
			return null;
		}
		return result.get(0);
	}
	
	public <T> boolean match(Class<T> clazz, IPOJOFilter<T> filter)
	{
		Util.assumeNotNull(filter, "filter not null");
		
		final String tableName = POJOWrapper.getTableName(clazz);

		final Map<Integer, Object> recordsMap = cachedObjects.get(tableName);
		if (recordsMap == null || recordsMap.isEmpty())
		{
			return false;
		}

		for (final Object o : recordsMap.values())
		{
			final T record = POJOWrapper.create(o, clazz);
			if (filter.accept(record))
			{
				return true;
			}
		}

		return false;
	}


	public void dumpStatus()
	{
		System.out.println(dumpStatusToString());
	}

	public String dumpStatusToString()
	{
		final StringBuilder sb = new StringBuilder();

		final List<String> tableNames = new ArrayList<String>(cachedObjects.keySet());
		Collections.sort(tableNames);

		for (String tableName : tableNames)
		{
			final Map<Integer, Object> map = cachedObjects.get(tableName);
			if (map == null || map.isEmpty())
			{
				continue;
			}

			sb.append("\nTable " + tableName + ": " + map.size() + " records");
			for (final Object o : map.values())
			{
				sb.append("\n\t" + o);
			}
		}

		return sb.toString();
	}

	public void clear()
	{
		nextId = DEFAULT_FirstId;
		cachedObjects.clear();
	}

	@Override
	public boolean delete(Object model)
	{
		if (model == null)
		{
			throw new IllegalArgumentException("model is null");
		}
		final POJOWrapper wrapper = POJOWrapper.getWrapper(model);
		if (wrapper == null)
		{
			throw new IllegalArgumentException("model '" + model + "' is not wrapped from " + POJOWrapper.class);
		}

		int id = wrapper.getId();
		if (id < 0)
		{
			// not saved, nothing to delete
			return false;
		}

		final String tableName = wrapper.getTableName();

		Map<Integer, Object> tableCachedObjects = cachedObjects.get(tableName);
		if (tableCachedObjects == null)
		{
			// not exists
			return false;
		}

		final Object removedObject = tableCachedObjects.remove(id);
		final boolean deleted = removedObject != null;

		return deleted;
	}
}
