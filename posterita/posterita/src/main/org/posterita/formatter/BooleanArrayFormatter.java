package org.posterita.formatter;


public class BooleanArrayFormatter extends Formatter 
{

	public Object format(Object value)
	{
		if (value == null)
			return new String[]{};
		
		Boolean values[] = (Boolean[])value;
		
		String retVal[] = new String[values.length];
		
		for (int i = 0; i < values.length; i++)
		{
			retVal[i] = values[i].toString();
		}
		
		return retVal;
	}
	
	// return Boolean[]
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
		
		Boolean retVal[] = new Boolean[values.length];
		
		for (int i = 0; i < values.length; i++)
		{
			retVal[i] = new Boolean(values[i]);
		}
		
		return retVal;
	}

}
