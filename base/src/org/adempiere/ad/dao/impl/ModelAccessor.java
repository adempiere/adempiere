package org.adempiere.ad.dao.impl;

import org.adempiere.model.InterfaceWrapperHelper;
import org.adempiere.util.TypedAccessor;

public class ModelAccessor<T> implements TypedAccessor<T>
{
	private final String columnName;

	public ModelAccessor(String columnName)
	{
		this.columnName = columnName;
	}

	@Override
	public T getValue(final Object model)
	{
		final T value = InterfaceWrapperHelper.getValue(model, columnName);
		return value;
	}

	@Override
	public String toString()
	{
		return "ModelAccessor [columnName=" + columnName + "]";
	}
}
