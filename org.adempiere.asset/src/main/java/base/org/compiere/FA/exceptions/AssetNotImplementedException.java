/**
 * 
 */
package org.compiere.FA.exceptions;


/**
 * Throwed when an asset related functionality is not yet implemented
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class AssetNotImplementedException extends AssetException
{
	private static final long serialVersionUID = 1L;

	public AssetNotImplementedException(String additionalMessage)
	{
		super("@NotImplemented@ "+additionalMessage);
	}
}
