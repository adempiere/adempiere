/**
 * 
 */
package org.compiere.FA.exceptions;

/**
 * @author Teo Sarca, www.arhipac.ro
 *
 */
public class AssetNotActiveException extends AssetException
{
	private static final long serialVersionUID = 1L;

	public AssetNotActiveException(int A_Asset_ID)
	{
		super("@AssetNotActive@ (ID="+A_Asset_ID+")");
	}
}
