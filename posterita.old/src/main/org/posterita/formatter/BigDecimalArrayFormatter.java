package org.posterita.formatter;

import java.math.BigDecimal;

public class BigDecimalArrayFormatter extends Formatter 
{
	// return String[]
	public Object format(Object value)
	{
		if (value == null)
			return new String[]{};
		
		BigDecimal values[] = (BigDecimal[])value;
		
		String retVal[] = new String[values.length];
		
		for (int i = 0; i < values.length; i++)
		{
			retVal[i] = values[i].toPlainString();
		}
		
		return retVal;
	}
	
	// return BigDecimal[]
	public Object unformat(Object object)
	{
		if (object == null)
		{
			return null;
		}
		
		String values[] = (String[])object;
		
		if (values.length == 0)
		{
			return null;
		}
		
		BigDecimal retVal[] = new BigDecimal[values.length];
		
		for (int i = 0; i < values.length; i++)
		{
			retVal[i] = new BigDecimal(values[i]);
		}
		
		return retVal;
	}
}
