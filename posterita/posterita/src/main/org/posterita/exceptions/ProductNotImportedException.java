package org.posterita.exceptions;

public class ProductNotImportedException extends OperationException 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4111958312107421807L;

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
