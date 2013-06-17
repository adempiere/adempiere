package org.adempiere.util.trxConstraints.api.impl;

import java.util.Collections;
import java.util.Set;

import org.adempiere.util.trxConstraints.api.ITrxConstraints;

/**
 * This implementation is returned by {@link TrxConstraintsBL#getConstraints()} to indicate that the Trx constraints
 * have been globally disabled.
 * 
 * @author ts
 * 
 */
public final class TrxConstraintsDisabled implements ITrxConstraints
{
	private static final TrxConstraintsDisabled instance = new TrxConstraintsDisabled();

	public static TrxConstraintsDisabled get()
	{
		return instance;
	}
	private TrxConstraintsDisabled()
	{
	}

	@Override
	public ITrxConstraints setActive(boolean active)
	{
		return this;
	}

	@Override
	public boolean isActive()
	{
		return false;
	}

	@Override
	public ITrxConstraints setOnlyAllowedTrxNamePrefixes(boolean onlyAllowedTrxNamePrefixes)
	{
		return this;
	}

	@Override
	public boolean isOnlyAllowedTrxNamePrefixes()
	{
		return false;
	}

	@Override
	public ITrxConstraints addAllowedTrxNamePrefix(String trxNamePrefix)
	{
		return this;
	}

	@Override
	public ITrxConstraints removeAllowedTrxNamePrefix(String trxNamePrefix)
	{
		return this;
	}
	
	@Override
	public Set<String> getAllowedTrxNamePrefixes()
	{
		return Collections.emptySet();
	}

	@Override
	public ITrxConstraints setTrxTimeoutSecs(int secs, boolean logOnly)
	{
		return this;
	}

	@Override
	public int getTrxTimeoutSecs()
	{
		return 0;
	}

	@Override
	public boolean isTrxTimeoutLogOnly()
	{
		return false;
	}

	@Override
	public ITrxConstraints setMaxTrx(int max)
	{
		return this;
	}

	@Override
	public ITrxConstraints incMaxTrx(int num)
	{
		return this;
	}

	@Override
	public int getMaxTrx()
	{
		return 0;
	}

	@Override
	public int getMaxSavepoints()
	{
		return 0;
	}

	@Override
	public ITrxConstraints setMaxSavepoints(int maxSavePoints)
	{
		return this;
	}

	@Override
	public boolean isAllowTrxAfterThreadEnd()
	{
		return false;
	}

	@Override
	public ITrxConstraints setAllowTrxAfterThreadEnd(boolean allow)
	{
		return this;
	}

	@Override
	public void reset()
	{
	}

	@Override
	public String toString()
	{
		return "TrxConstraints have been globally disabled. Add or change AD_SysConfig " + TrxConstraintsBL.CFG_TRX_CONSTRAINTS_DISABLED + " to enable them";
	}
}
