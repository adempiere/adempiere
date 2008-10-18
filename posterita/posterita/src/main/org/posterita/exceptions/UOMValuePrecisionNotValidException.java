package org.posterita.exceptions;

/**
 * This Exception corresponds to the situation where the entered 
 * value for some field has a precision which exceeds that field's UOM precision. 
 * 
 * @author sendy
 *
 */
public class UOMValuePrecisionNotValidException extends OperationException 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UOMValuePrecisionNotValidException(String exception)
    {
        super(exception);
    }
}
