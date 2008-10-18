/*
 * Created on 2005/03/11
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.posterita.exceptions;

/**
 * @author jane
 *
 *To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class InvalidEmailException extends OperationException 
{
	private static final long serialVersionUID = 1L;
	
	public InvalidEmailException(String exception)
	{
		super(exception);
	}
	
	public InvalidEmailException(String msg, Exception ex)
	{
		super(msg, ex);
	}

    
}
