package org.compiere.asset.exceptions;


/**
 * Throwed when a specific functionality is not supported.
 * Please don't confunde with {@link AssetNotImplementedException}. 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class AssetNotSupportedException extends AssetException
{
	private static final long serialVersionUID = 1L;

	public AssetNotSupportedException (String funcName, String actualValue)
	{
		super("@NotSupported@ @"+funcName+"@ "+actualValue);
	}
}
