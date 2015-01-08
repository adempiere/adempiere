/**
 * 
 */
package org.compiere.FA.exceptions;


/**
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class AssetCheckDocumentException extends AssetException
{
	private static final long serialVersionUID = 1L;

	public AssetCheckDocumentException(String additionalMessage)
	{
		super ("@CheckDocument@ - "+additionalMessage); // TODO: AD_Message
	}
}
