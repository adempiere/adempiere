package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MReplenishPlan extends X_M_ReplenishPlan
{

	public MReplenishPlan(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public MReplenishPlan(Properties ctx, int M_ReplenishPlan_ID, String trxName)
	{
		super(ctx, M_ReplenishPlan_ID, trxName);
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

}
