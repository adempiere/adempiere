package org.compiere.util;

import java.util.Map;

/**
 * Helper class for building {@link Object#equals(Object)} method.
 * 
 * This class is similar with apache commons EqualsBuilder, but much more simple.
 * 
 * @author tsa
 * 
 */
public class EqualsBuilder
{
	private boolean isEqual = true;

	public EqualsBuilder()
	{
		super();
	}

	public EqualsBuilder append(Object value1, Object value2)
	{
		if (!isEqual)
		{
			return this;
		}

		if (!Util.equals(value1, value2))
		{
			isEqual = false;
		}

		return this;
	}

	public EqualsBuilder append(Map<String, Object> map1, Map<String, Object> map2, boolean handleEmptyAsNull)
	{
		if (!isEqual)
		{
			return this;
		}

		if (handleEmptyAsNull)
		{
			final Object value1 = map1 == null || map1.isEmpty() ? null : map1;
			final Object value2 = map2 == null || map2.isEmpty() ? null : map2;
			return append(value1, value2);
		}

		return append(map1, map2);
	}

	public boolean isEqual()
	{
		return isEqual;
	}

	/**
	 * Checks and casts <code>other</code> to same class as <code>obj</code>.
	 * 
	 * This method shall be used as fist statement in an {@link Object#equals(Object)} implementation. <br/>
	 * <br/>
	 * Example:
	 * 
	 * <pre>
	 * public boolean equals(Object obj)
	 * {
	 * 	if (this == obj)
	 * 	{
	 * 		return true;
	 * 	}
	 * 	final MyClass other = EqualsBuilder.getOther(this, obj);
	 * 	if (other == null)
	 * 	{
	 * 		return false;
	 * 	}
	 * 
	 * 	return new EqualsBuilder()
	 * 			.append(prop1, other.prop1)
	 * 			.append(prop2, other.prop2)
	 * 			.append(prop3, other.prop3)
	 * 			// ....
	 * 			.isEqual();
	 * }
	 * </pre>
	 * 
	 * @param obj
	 * @param other
	 * @return <code>other</code> casted to same class as <code>obj</code> or null if <code>other</code> is null or does not have the same class
	 */
	public static <T> T getOther(T obj, Object other)
	{
		if (obj == null)
		{
			throw new IllegalArgumentException("obj is null");
		}

		if (obj == other)
		{
			@SuppressWarnings("unchecked")
			final T otherCasted = (T)other;
			return otherCasted;
		}

		if (other == null)
		{
			return null;
		}

		if (obj.getClass() != other.getClass())
		{
			return null;
		}

		@SuppressWarnings("unchecked")
		final T otherCasted = (T)other;
		return otherCasted;
	}
}
