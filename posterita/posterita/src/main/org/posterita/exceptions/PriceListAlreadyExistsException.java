package org.posterita.exceptions;

public class PriceListAlreadyExistsException extends OperationException 
{
	private static final long serialVersionUID = 1L;
	
	public PriceListAlreadyExistsException(String exception)
	{
		 super(exception);
	}
	
}
