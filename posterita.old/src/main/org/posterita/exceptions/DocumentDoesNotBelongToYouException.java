package org.posterita.exceptions;

public class DocumentDoesNotBelongToYouException extends OperationException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocumentDoesNotBelongToYouException(String message)
	{
		super(message);
	}
}
