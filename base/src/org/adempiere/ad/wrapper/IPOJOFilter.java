package org.adempiere.ad.wrapper;

public interface IPOJOFilter<T>
{
	boolean accept(T pojo);
}
