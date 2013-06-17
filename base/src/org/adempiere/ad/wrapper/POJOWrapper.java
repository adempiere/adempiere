package org.adempiere.ad.wrapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.model.InterfaceWrapperHelper;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 * Simple implementation which binds an given interface to a internal Map.
 * 
 * @author tsa
 * 
 */
public class POJOWrapper implements InvocationHandler
{
	public static <T> T create(Properties ctx, Class<T> cl)
	{
		return create(ctx, cl, POJOLookupMap.get());
	}

	/**
	 * 
	 * @param ctx
	 * @param cl
	 * @param trxName ignored
	 * @return
	 */
	public static <T> T create(Properties ctx, Class<T> cl, String trxName)
	{
		return create(ctx, cl);
	}

	public static <T> T create(Properties ctx, int id, Class<T> cl, String trxName)
	{
		return POJOLookupMap.get().lookup(cl, id);
	}
	
	public static <T> T create(Properties ctx, String tableName, int id, Class<T> cl, String trxName)
	{
		final int tableId = MTable.getTable_ID(tableName);
		return POJOLookupMap.get().lookup(tableId, id);
	}

	public static <T> T create(Properties ctx, Class<T> cl, IPOJOLookupMap lookup)
	{
		@SuppressWarnings("unchecked")
		final T object = (T)Proxy.newProxyInstance(cl.getClassLoader(),
				new Class<?>[] { cl },
				new POJOWrapper(ctx, cl, lookup));
		return object;
	}

	public static <T> T create(Object model, Class<T> cl)
	{
		if (model == null)
		{
			return null;
		}

		if (cl.isInstance(model))
		{
			@SuppressWarnings("unchecked")
			final T result = (T)model;
			return result;
		}

		final POJOWrapper wrapper = getWrapper(model);
		if (wrapper == null)
		{
			throw new RuntimeException("Model not supported: " + model);
		}

		@SuppressWarnings("unchecked")
		final T result = (T)Proxy.newProxyInstance(cl.getClassLoader(),
				new Class<?>[] { cl },
				new POJOWrapper(cl, wrapper));
		return result;
	}

	public static Properties getCtx(Object model)
	{
		return Env.getCtx();
	}

	public static String getTrxName(Object model)
	{
		return Trx.TRXNAME_None;
	}

	public static POJOWrapper getWrapper(Object model)
	{
		if (model == null)
		{
			return null;
		}

		if (Proxy.isProxyClass(model.getClass()))
		{
			InvocationHandler ih = Proxy.getInvocationHandler(model);
			if (ih instanceof POJOWrapper)
			{
				POJOWrapper wrapper = (POJOWrapper)ih;
				return wrapper;
			}
			return null;
		}

		return null;

	}

	public static boolean isHandled(Object model)
	{
		return getWrapper(model) != null;
	}

	public static void save(Object model)
	{
		final POJOWrapper wrapper = getWrapper(model);
		wrapper.getLookupMap().save(model);
	}

	// private final transient CLogger log = CLogger.getCLogger(getClass());
	private final Properties ctx;
	private final IPOJOLookupMap lookup;
	private final String tableName;
	private final Class<?> interfaceClass;
	private final Map<String, Object> values;
	private final String idColumnName;

	private boolean strictValues = false;
	private static boolean strictValuesDefault = false;

	/**
	 * Optional instance's name to be used in debugging. If set, it will be used when converting this object to string
	 */
	private String instanceName;

	private POJOWrapper(Properties ctx, Class<?> interfaceClass, Map<String, Object> values, IPOJOLookupMap lookup)
	{
		super();
		this.ctx = ctx;
		this.interfaceClass = interfaceClass;
		this.tableName = InterfaceWrapperHelper.getTableName(interfaceClass);
		this.idColumnName = tableName + "_ID";
		this.lookup = lookup;

		this.values = new HashMap<String, Object>();

		// Standard values:
		this.values.put("IsActive", true);
		this.values.put("AD_Client_ID", Env.getAD_Client_ID(ctx));

		if (values != null)
		{
			this.values.putAll(values);
		}

		this.strictValues = strictValuesDefault;
	}

	private POJOWrapper(Properties ctx, Class<?> interfaceClass, IPOJOLookupMap lookup)
	{
		this(ctx, interfaceClass, null, lookup); // values = null
	}

	private POJOWrapper(Class<?> interfaceClass, POJOWrapper parentWrapper)
	{
		super();
		this.ctx = parentWrapper.getCtx();
		this.interfaceClass = interfaceClass;

		final String interfaceTableName = InterfaceWrapperHelper.getTableNameOrNull(interfaceClass);
		if (interfaceTableName == null)
		{
			this.tableName = parentWrapper.tableName;
		}
		else
		{
			Util.assume(interfaceTableName.equals(parentWrapper.tableName), "Parent wrapper must use tablename '{0}' instead of '{1}'", interfaceTableName, parentWrapper.tableName);
			this.tableName = interfaceTableName;
		}
		Util.assumeNotNull(this.tableName, "No TableName found for {0} using {1}", interfaceClass, parentWrapper);

		this.idColumnName = parentWrapper.idColumnName;

		this.lookup = parentWrapper.lookup;

		// instead of copying the Map, we are directly linking to it, to avoid duplicating the data
		this.values = parentWrapper.values;
		this.strictValues = strictValuesDefault;
	}

	public Properties getCtx()
	{
		return ctx;
	}

	public Class<?> getInterfaceClass()
	{
		return interfaceClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		final String methodName = method.getName();
		if (methodName.startsWith("set") && args.length == 1)
		{
			final String propertyName = methodName.substring(3);
			final Class<?> paramType = method.getParameterTypes()[0];
			final Object value = args[0];
			if (isModelInterface(paramType))
			{
				setReferencedObject(propertyName, value);
				// throw new AdempiereException("Object setter not supported: " + method);
				// setValueFromPO(propertyName + "_ID", paramType, value);
			}
			else
			{
				setValue(propertyName, value);
			}
			return null;
		}
		else if (methodName.startsWith("get") && (args == null || args.length == 0))
		{
			final String propertyName = methodName.substring(3);

			if (propertyName.equals(idColumnName))
			{
				return getId();
			}
			if (isModelInterface(method.getReturnType()))
			{
				return getReferencedObject(propertyName, method);
			}

			Object value = getValue(propertyName, method.getReturnType());
			if (value != null)
			{
				return value;
			}
			//
			if (method.getReturnType() == int.class)
			{
				value = Integer.valueOf(0);
			}
			else if (method.getReturnType() == BigDecimal.class)
			{
				value = BigDecimal.ZERO;
			}
			else if (PO.class.isAssignableFrom(method.getReturnType()))
			{
				throw new IllegalArgumentException("Method not supported - " + methodName);
			}
			return value;
		}
		else if (methodName.startsWith("is") && (args == null || args.length == 0))
		{
			String propertyName = methodName.substring(2);
			final Object value;
			if (values.containsKey(propertyName))
			{
				value = getValue(propertyName, method.getReturnType());
			}
			else if (values.containsKey("Is" + propertyName))
			{
				value = getValue("Is" + propertyName, method.getReturnType());
			}
			else
			{
				if (strictValues)
				{
					throw new IllegalStateException("No property " + propertyName + " was defined for bean " + this);
				}

				value = Boolean.FALSE;
			}

			// System.out.println("values="+values+", propertyName="+propertyName+" => value="+value);
			return value == null ? false : (Boolean)value;
		}
		else if (methodName.equals("equals") && args.length == 1)
		{
			return invokeEquals(args);
		}
		else if (methodName.equals("hashCode") && (args == null || args.length == 0))
		{
			return invokeHashCode();
		}
		else if (methodName.equals("toString") && (args == null || args.length == 0))
		{
			return toString();
		}
		else
		{
			throw new IllegalStateException("Method not supported: " + method);
		}
	}

	private void setReferencedObject(String propertyName, Object value)
	{
		final String idPropertyName = propertyName + "_ID";
		if (value == null)
		{
			values.remove(propertyName);
			values.remove(idPropertyName);
			return;
		}

		final POJOWrapper wrapper = getWrapper(value);
		if (wrapper == null)
		{
			throw new RuntimeException("No wrapper found for " + propertyName + " (value=" + value + ") in " + this);
		}
		final int valueId = wrapper.getId();
		setValue(propertyName, value);
		setValue(idPropertyName, valueId);
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder("POJOWrapper[");
		sb.append(interfaceClass);

		if (!Util.isEmpty(instanceName, true))
		{
			sb.append(", instanceName=").append(instanceName);
		}

		sb.append(", values=").append(values);
		sb.append("]");

		return sb.toString();
	}

	private Object getReferencedObject(String propertyName, Method method)
	{
		Object value = values.get(propertyName);
		if (value != null)
		{
			return value;
		}

		Integer id = (Integer)getValue(propertyName + "_ID", Integer.class);
		if (id == null || id < 0)
		{
			return null;
		}

		value = lookup.lookup(method.getReturnType(), id);

		return value;
	}

	public Object getValue(String propertyName, Class<?> returnType)
	{
		return getValue(propertyName, returnType, strictValues);
	}

	private Object getValue(String propertyName, Class<?> returnType, boolean enforceStrictValues)
	{
		if (enforceStrictValues && !values.containsKey(propertyName))
		{
			throw new IllegalStateException("No property " + propertyName + " was defined for bean " + this);
		}
		return values.get(propertyName);
	}

	public void setValue(String propertyName, Object value)
	{
		values.put(propertyName, value);
	}

	private boolean isModelInterface(Class<?> cl)
	{
		String tableName = InterfaceWrapperHelper.getTableNameOrNull(cl);
		return tableName != null;
	}

	private boolean invokeEquals(Object[] args)
	{
		final POJOWrapper wrapper2 = getWrapper(args[0]);
		final boolean result = this.equals(wrapper2);

		return result;
	}

	private Object invokeHashCode()
	{
		int result = hashCode();
		return result;
	}

	public int getId()
	{
		final Integer id = (Integer)getValue(idColumnName, Integer.class, false); // enforceStrictValues=false
		return id == null ? -1 : id.intValue();
	}

	public void setId(int id)
	{
		setValue(idColumnName, id);
	}

	public String getTableName()
	{
		return tableName;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idColumnName == null) ? 0 : idColumnName.hashCode());
		result = prime * result + ((tableName == null) ? 0 : tableName.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		POJOWrapper other = (POJOWrapper)obj;
		if (idColumnName == null)
		{
			if (other.idColumnName != null)
			{
				return false;
			}
		}
		else if (!idColumnName.equals(other.idColumnName))
		{
			return false;
		}
		if (tableName == null)
		{
			if (other.tableName != null)
			{
				return false;
			}
		}
		else if (!tableName.equals(other.tableName))
		{
			return false;
		}
		if (values == null)
		{
			if (other.values != null)
			{
				return false;
			}
		}
		else if (!values.equals(other.values))
		{
			return false;
		}
		return true;
	}

	public static String getTableName(Class<?> clazz)
	{
		try
		{
			final String tableName = (String)clazz.getField("Table_Name").get(null);
			return tableName;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public static String getTableNameOrNull(Class<?> clazz)
	{
		try
		{
			final String tableName = (String)clazz.getField("Table_Name").get(null);
			return tableName;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public IPOJOLookupMap getLookupMap()
	{
		return lookup;
	}

	public Map<String, Object> getValuesMap()
	{
		return values;
	}

	public static void delete(Object model)
	{
		final POJOWrapper wrapper = getWrapper(model);
		wrapper.getLookupMap().delete(model);
	}

	/**
	 * Check if given columnName's value is null
	 * 
	 * @param model
	 * @param columnName
	 * @return true if columnName's value is null
	 */
	public static boolean isNull(Object model, String columnName)
	{
		final POJOWrapper wrapper = getWrapper(model);
		if (wrapper == null)
		{
			return true;
		}

		final Object value = wrapper.getValue(columnName, Object.class);
		return value == null;
	}

	public static void refresh(Object model)
	{
		final POJOWrapper wrapper = getWrapper(model);
		if (wrapper == null)
		{
			throw new AdempiereException("Not supported model: " + model);
		}

		// nothing to do, the model shall be already up2date
	}

	public void copyFrom(POJOWrapper from)
	{
		if (from.values == null)
		{
			return;
		}

		values.putAll(from.values);
	}

	public static void setInstanceName(final Object model, final String instanceName)
	{
		final POJOWrapper wrapper = getWrapper(model);
		if (wrapper == null)
		{
			return;
		}

		wrapper.instanceName = instanceName;
	}

	public static String getInstanceName(final Object model)
	{
		final POJOWrapper wrapper = getWrapper(model);
		if (wrapper == null)
		{
			return null;
		}

		return wrapper.instanceName;
	}

	/**
	 * Sets if the values shall be strict. By strict values we mean that if a value was not found in the map when the getter is called we will throw an exception.
	 * 
	 * @param strictValues
	 */
	public void setStrictValues(boolean strictValues)
	{
		this.strictValues = strictValues;
	}

	public boolean isStrictValues()
	{
		return this.strictValues;
	}

	public static void enableStrictValues(Object model)
	{
		POJOWrapper.getWrapper(model).setStrictValues(true);
	}

	public static void setDefaultStrictValues(final boolean enabled)
	{
		strictValuesDefault = enabled;
	}

	private static final String DYNATTR_PREFIX = "##DynAttr";

	public static void setDynAttribute(final Object model, final String attributeName, final Object value)
	{
		final POJOWrapper wrapper = getWrapper(model);
		wrapper.getValuesMap().put(DYNATTR_PREFIX + "#" + attributeName, value);

	}

	public static <T> T getDynAttribute(final Object model, final String attributeName)
	{
		final POJOWrapper wrapper = getWrapper(model);
		@SuppressWarnings("unchecked")
		T value = (T)wrapper.getValuesMap().get(DYNATTR_PREFIX + "#" + attributeName);
		return value;
	}

}