package org.posterita.exceptions;

public class CustomerNotImportedException extends OperationException
{
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
