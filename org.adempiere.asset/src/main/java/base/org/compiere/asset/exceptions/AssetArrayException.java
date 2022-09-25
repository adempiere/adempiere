/**
 * 
 */
package org.compiere.asset.exceptions;

import java.util.List;

/**
 * @author Teo Sarca, www.arhipac.ro
 *
 */
public class AssetArrayException extends AssetException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6145405299338077726L;

	public AssetArrayException(List<Exception> errors)
	{
		super(buildMessage(errors));
	}
	
	private static String buildMessage(List<Exception> errors)
	{
		StringBuffer sb = new StringBuffer("The following errors were encountered: "); // TODO: translate
		for (Exception e : errors)
		{
			sb.append("\n"+e.getLocalizedMessage());
		}
		return sb.toString();
	}
}
