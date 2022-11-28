/**
 * 
 */
package org.compiere.asset.exceptions;


/**
 * Asset is already depreciated and this is an issue for the action that invoked this exception
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class AssetAlreadyDepreciatedException extends AssetException
{
	private static final long serialVersionUID = 1L;

	public AssetAlreadyDepreciatedException()
	{
		super("@AssetAlreadyDepreciated@");
	}
}
