package org.adempiere.util.exceptions;

public class ServicesException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6076066735401985642L;

	
	public ServicesException(String message, Throwable cause)
	{
		super(message, cause);

	}

	public ServicesException(String message)
	{
		super(message);

	}

	public ServicesException(Throwable cause)
	{
		super(cause);
	}

}
