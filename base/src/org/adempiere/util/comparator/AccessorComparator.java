package org.adempiere.util.comparator;

import java.util.Comparator;

import org.adempiere.util.Check;
import org.adempiere.util.TypedAccessor;

/**
 * Wraps a given comparator by transforming the values from outer type to inner type
 * 
 * @author tsa
 * 
 * @param <OT> outer type
 * @param <IT> inner type
 */
public class AccessorComparator<OT, IT> implements Comparator<OT>
{
	private final Comparator<IT> cmp;
	private final TypedAccessor<IT> accessor;

	/**
	 * 
	 * @param cmp comparator to be used for comparing inner type values
	 * @param accessor accessor which will get the inner type from a given outer type object
	 */
	public AccessorComparator(final Comparator<IT> cmp, final TypedAccessor<IT> accessor)
	{
		Check.assumeNotNull(cmp, "cmp not null");
		Check.assumeNotNull(accessor, "accessor not null");

		this.cmp = cmp;
		this.accessor = accessor;
	}

	@Override
	public int compare(OT o1, OT o2)
	{
		if (o1 == o2)
		{
			return 0;
		}
		if (o1 == null)
		{
			return +1;
		}
		if (o2 == null)
		{
			return -1;
		}

		final IT value1 = accessor.getValue(o1);
		final IT value2 = accessor.getValue(o2);

		return cmp.compare(value1, value2);
	}

	@Override
	public String toString()
	{
		return "AccessorComparator [cmp=" + cmp + ", accessor=" + accessor + "]";
	}
}
