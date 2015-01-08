/**
 * 
 */
package org.adempiere.exceptions;

import org.compiere.model.Query;

/**
 * Exception thrown by {@link Query} class when there were more records and only one was expected
 * 
 * @author Teo Sarca
 * 
 */
public class DBMoreThenOneRecordsFoundException extends DBException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3925120991619286612L;

	private static final String AD_Message = "QueryMoreThanOneRecordsFound";

	/**
	 * @param msg
	 */
	public DBMoreThenOneRecordsFoundException(String detailMessage)
	{
		super("@" + AD_Message + "@ (" + detailMessage + ")");
	}

}
