/**
 * 
 */
package org.compiere.FA.exceptions;

import org.compiere.util.Util;

/**
 * Threw when asset status has changed
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class AssetStatusChangedException extends AssetException
{
	private static final long serialVersionUID = 1L;

	public AssetStatusChangedException()
	{
		this(null);
	}
	
	public AssetStatusChangedException(String msg)
	{
		super(buildMsg(msg));
	}

	private static String buildMsg(String msg)
	{
		StringBuffer sb = new StringBuffer("@AssetStatusChanged@");
		if (!Util.isEmpty(msg))
			sb.append(" ").append(msg);
		return sb.toString();
	}
}
