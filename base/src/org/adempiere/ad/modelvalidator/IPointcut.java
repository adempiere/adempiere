package org.adempiere.ad.modelvalidator;

import java.lang.reflect.Method;

public interface IPointcut
{

	Method getMethod();

	String getTableName();

	Class<?> getModelClass();

	boolean isMethodRequiresTiming();

	PointcutType getType();

	int[] getTimings();

	String[] getChangedColumns();

}