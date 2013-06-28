package org.adempiere.ad.modelvalidator;

import java.lang.reflect.Method;
import java.util.Arrays;

class Pointcut implements IPointcut
{
	private final PointcutType type;
	private final Method method;
	private final int[] timings;
	private String tableName;
	private Class<?> modelClass;
	private boolean methodRequiresTiming;
	private String[] changedColumns;

	@SuppressWarnings("PMD.ArrayIsStoredDirectly")
	public Pointcut(PointcutType type, Method method, int[] timings)
	{
		this.type = type;
		this.method = method;
		this.timings = timings;
	}

	@Override
	public Method getMethod()
	{
		return method;
	}

	@Override
	public String getTableName()
	{
		return tableName;
	}

	protected void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	@Override
	public Class<?> getModelClass()
	{
		return modelClass;
	}

	protected void setModelClass(Class<?> modelClass)
	{
		this.modelClass = modelClass;
	}

	@Override
	public boolean isMethodRequiresTiming()
	{
		return methodRequiresTiming;
	}

	protected void setMethodRequiresTiming(boolean methodRequiresTiming)
	{
		this.methodRequiresTiming = methodRequiresTiming;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.metas.adempiere.modelvalidator.IPointcut#getType()
	 */
	@Override
	public PointcutType getType()
	{
		return type;
	}

	@SuppressWarnings("PMD.MethodReturnsInternalArray")
	@Override
	public int[] getTimings()
	{
		return timings;
	}

	@SuppressWarnings("PMD.MethodReturnsInternalArray")
	@Override
	public String[] getChangedColumns()
	{
		return changedColumns;
	}

	@SuppressWarnings("PMD.ArrayIsStoredDirectly")
	public void setChangedColumns(String[] changedColumns)
	{
		this.changedColumns = changedColumns;
	}

	@Override
	public String toString()
	{
		return "Pointcut [type=" + type
				+ ", tableName=" + tableName
				+ ", modelClass=" + modelClass
				+ ", method=" + method
				+ ", timings=" + Arrays.toString(timings)
				+ ", onColumnChanged=" + Arrays.toString(changedColumns)
				+ ", methodRequiresTiming=" + methodRequiresTiming
				+ "]";
	}
}
