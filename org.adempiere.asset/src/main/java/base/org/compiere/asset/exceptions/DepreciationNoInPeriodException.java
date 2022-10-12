/**
 * 
 */
package org.compiere.asset.exceptions;

/**
 * @author teo_sarca
 *
 */
public class DepreciationNoInPeriodException extends AssetException
{
	private static final long serialVersionUID = 1L;
	
	public DepreciationNoInPeriodException(int A_Asset_ID, int Workfile_Period_ID, int DepExp_Period_ID)
	{
		super("Registration is not in balance (ID Asset="+A_Asset_ID
				+", WK Period="+Workfile_Period_ID+", DepExp Period="+DepExp_Period_ID
				+")");
	}

}
