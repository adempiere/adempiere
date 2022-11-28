/**
 * 
 */
package org.eevolution.hr.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.core.domains.models.X_HR_Period;
import org.compiere.util.CCache;

/**
 * HR Period
 * @author Teo Sarca, www.arhipac.ro
 */
public class MHRPeriod extends X_HR_Period
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7787966459848200539L;
	private static CCache<Integer, MHRPeriod> s_cache = new CCache<Integer, MHRPeriod>(Table_Name, 20);

	
	@Deprecated
	public static MHRPeriod get(Properties ctx, int periodId)
	{
		return getById(ctx, periodId , null);
	}

	public static MHRPeriod getById(Properties ctx, int periodId, String trxName)
	{
		if (periodId <= 0)
			return null;
		//
		MHRPeriod period = s_cache.get(periodId);
		if (period != null)
		{
			return period;
		}
		// Try Load
		period = new MHRPeriod(ctx, periodId, trxName);
		if (period.get_ID() == periodId)
		{
			s_cache.put(periodId, period);
		}
		else
		{
			period = null;
		}
		return period;
	}

	public MHRPeriod(Properties ctx, int HR_Period_ID, String trxName)
	{
		super(ctx, HR_Period_ID, trxName);
	}
	public MHRPeriod(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

}
