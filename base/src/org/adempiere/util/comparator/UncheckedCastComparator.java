package org.adempiere.util.comparator;

import java.util.Comparator;

import org.adempiere.util.Check;

/**
 * An comparator wrapper which is converting an comparator of type <code>IT</code> to a comparator of type <code>OT</code> by performing an unchecked cast. <br/>
 * <br/>
 * Example:
 * 
 * <pre>
 * Comparator&lt;String&gt; myStringComparator = ....;
 * Comparator&lt;Object&gt; myObjectComparator = new UncheckedCastComparator&lt;Object, String&gt;(myStringComparator);
 * 
 * List&lt;Object&gt; list = ...; // a list of Object on which we know that we have String objects
 * Collections.sort(list, myObjectComparator);
 * </pre>
 * 
 * @author tsa
 * 
 * @param <OT> output type
 * @param <IT> input type
 */
public class UncheckedCastComparator<OT, IT> implements Comparator<OT>
{
	private final Comparator<IT> comparator;

	public UncheckedCastComparator(Comparator<IT> comparator)
	{
		Check.assumeNotNull(comparator, "comparator not null");
		this.comparator = comparator;
	}

	@Override
	public int compare(OT o1, OT o2)
	{
		@SuppressWarnings("unchecked")
		final IT casted_o1 = (IT)o1;

		@SuppressWarnings("unchecked")
		final IT casted_o2 = (IT)o2;

		return comparator.compare(casted_o1, casted_o2);
	}

}
