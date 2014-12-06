/**
 * 
 */
package org.compiere.FA.exceptions;

import org.adempiere.exceptions.AdempiereException;

/**
 * Asset Related General Exception. This is the root of all Asset Related Exceptions.
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class AssetException extends AdempiereException
{
	private static final long serialVersionUID = 1L;

	public AssetException()
	{
		super();
	}

	public AssetException(String message)
	{
		super(message);
	}

	public AssetException(Throwable cause)
	{
		super(cause);
	}

	public AssetException(String message, Throwable cause)
	{
		super(message, cause);
	}
}
