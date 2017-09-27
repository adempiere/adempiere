package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;


/** Depreciation Build
 *  @author Teo Sarca, SC ARHIPAC SRL
 *  @version $Id$ -- Release 2.5.3a - 2006-06-22 18:03:22.896
 */
public class MDepreciationBuild extends X_A_Depreciation_Build
{
	/** Standard Constructor */
	public MDepreciationBuild (Properties ctx, int A_Depreciation_Build_ID, String trxName)
	{
		super (ctx, A_Depreciation_Build_ID, trxName);
		/** if (A_Depreciation_Build_ID == 0)
		{
			setA_Depreciation_Build_ID (0);
		}
		 */
	}
	/** Load Constructor */
	public MDepreciationBuild (Properties ctx, ResultSet rs, String trxName)
	{
		super (ctx, rs, trxName);
	}
}
