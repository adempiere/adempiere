package org.adempiere.util.collections;

import java.util.Iterator;

import org.adempiere.util.Check;

public class ConvertIteratorWrapper<OT, IT> implements Iterator<OT>, IteratorWrapper<IT>
{
	private final Iterator<IT> iterator;
	private final Converter<OT, IT> converter;

	public ConvertIteratorWrapper(final Iterator<IT> iterator, final Converter<OT, IT> converter)
	{
		super();

		Check.assumeNotNull(iterator, "iterator not null");
		Check.assumeNotNull(converter, "converter not null");

		this.iterator = iterator;
		this.converter = converter;
	}

	@Override
	public Iterator<IT> getParentIterator()
	{
		return iterator;
	}

	@Override
	public boolean hasNext()
	{
		return iterator.hasNext();
	}

	@Override
	public OT next()
	{
		final IT valueIn = iterator.next();
		final OT valueOut = converter.convert(valueIn);
		return valueOut;
	}

	@Override
	public void remove()
	{
		iterator.remove();
	}

}
