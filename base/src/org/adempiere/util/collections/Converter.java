package org.adempiere.util.collections;

public interface Converter<OT, IT>
{
	OT convert(IT value);
}
