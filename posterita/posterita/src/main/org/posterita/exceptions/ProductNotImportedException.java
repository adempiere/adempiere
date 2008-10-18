package org.posterita.exceptions;

public class ProductNotImportedException extends OperationException 
{

	public ProductNotImportedException() 
	{
		super();
	}

	public ProductNotImportedException(Exception exception) 
	{
		super(exception);
	}

	public ProductNotImportedException(String msg, Exception exception) 
	{
		super(msg, exception);
	}

	public ProductNotImportedException(String exception)
	{
		super(exception);
	}

}
