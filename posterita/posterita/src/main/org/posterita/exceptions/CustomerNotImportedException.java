package org.posterita.exceptions;

public class CustomerNotImportedException extends OperationException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4763042516279622902L;

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
