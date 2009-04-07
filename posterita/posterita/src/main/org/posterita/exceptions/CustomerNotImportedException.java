package org.posterita.exceptions;

public class CustomerNotImportedException extends OperationException
{
	private static final long serialVersionUID = 1L;
	
    public CustomerNotImportedException() 
    {
        super();
    }

    public CustomerNotImportedException(Exception exception) 
    {
        super(exception);
    }

    public CustomerNotImportedException(String msg, Exception exception) 
    {
        super(msg, exception);
    }

    public CustomerNotImportedException(String exception)
    {
        super(exception);
    }
}
