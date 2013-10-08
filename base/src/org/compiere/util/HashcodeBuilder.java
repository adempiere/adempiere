package org.compiere.util;

import java.util.Map;

/**
 * Helper class for building {@link Object#hashCode()} method.
 * 
 * This class is similar with apache commons HashCodeBuilder, but much more simple.
 * 
 * @author tsa
 * 
 */
public class HashcodeBuilder
{
	private final static int prime = 31;

	private int hashcode = 0;

	public HashcodeBuilder()
	{
		super();
	}

	public HashcodeBuilder append(Object value)
	{
		hashcode = prime * hashcode + (value == null ? 0 : value.hashCode());
		return this;
	}

	public HashcodeBuilder append(Map<?, ?> map, boolean handleEmptyAsNull)
	{
		if (handleEmptyAsNull && (map == null || map.isEmpty()))
		{
			return append((Object)null);
		}

		return append((Object)map);
	}

	public int toHashcode()
	{
		return hashcode;
	}
}
