package org.posterita.exceptions;

public class NoProductImageException extends OperationException 
{
	private static final long serialVersionUID = 1L;

	public NoProductImageException(String msg)
	{
		super(msg);
	}
}
