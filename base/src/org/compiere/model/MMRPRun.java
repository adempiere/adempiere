package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MMRPRun extends X_MRP_Run
{

	public MMRPRun(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public MMRPRun(Properties ctx, int MRP_Run_ID, String trxName)
	{
		super(ctx, MRP_Run_ID, trxName);
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

}
