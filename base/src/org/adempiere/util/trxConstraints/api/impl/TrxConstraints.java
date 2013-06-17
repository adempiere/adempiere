package org.adempiere.util.trxConstraints.api.impl;

import java.util.HashSet;
import java.util.Set;

import org.adempiere.util.trxConstraints.api.ITrxConstraints;

/**
 * 
 * @see ITrxConstraints for java doc
 */
public class TrxConstraints implements ITrxConstraints
{
	private static final boolean DEFAULT_TRX_TIMEOUT_LOG_ONLY = false;

	private static final int DEFAULT_TRX_TIMEOUT_SECS = 600;

	private static final int DEFAULT_MAX_TRX = 3;

	private static final int DEFAULT_MAX_SAVEPOINTS = 3;

	private static final boolean DEFAULT_ACTIVE = true;

	private static final boolean DEFAULT_ONLY_ALLOWED_TRXNAME_PREFIXES = false;

	private static final boolean DEFAULT_ALLOW_TRX_AFTER_TREAD_END = false;

	private int trxTimeoutSecs = DEFAULT_TRX_TIMEOUT_SECS;

	private boolean trxTimeoutLogOnly = DEFAULT_TRX_TIMEOUT_LOG_ONLY;

	private int maxTrx = DEFAULT_MAX_TRX;

	private int maxSavepoints = DEFAULT_MAX_SAVEPOINTS;

	private boolean active = DEFAULT_ACTIVE;

	private boolean onlyAllowedTrxNamePrefixes = DEFAULT_ONLY_ALLOWED_TRXNAME_PREFIXES;

	private boolean allowTrxAfterThreadEnd = DEFAULT_ALLOW_TRX_AFTER_TREAD_END;

	private Set<String> allowedTrxNamePrefixes = new HashSet<String>();

	TrxConstraints()
	{
	}

	/**
	 * Copy constructor
	 * 
	 * @param origninal
	 */
	TrxConstraints(ITrxConstraints original)
	{
		this.active = original.isActive();
		this.allowedTrxNamePrefixes = new HashSet<String>(original.getAllowedTrxNamePrefixes());
		this.allowTrxAfterThreadEnd = original.isAllowTrxAfterThreadEnd();
		this.maxSavepoints = original.getMaxSavepoints();
		this.maxTrx = original.getMaxTrx();
		this.onlyAllowedTrxNamePrefixes = original.isOnlyAllowedTrxNamePrefixes();
		this.trxTimeoutLogOnly = original.isTrxTimeoutLogOnly();
		this.trxTimeoutSecs = original.getTrxTimeoutSecs();
	}

	@Override
	public ITrxConstraints setTrxTimeoutSecs(final int secs, final boolean logOnly)
	{
		this.trxTimeoutSecs = secs;
		this.trxTimeoutLogOnly = logOnly;
		return this;
	}

	@Override
	public int getTrxTimeoutSecs()
	{
		return trxTimeoutSecs;
	}

	@Override
	public boolean isTrxTimeoutLogOnly()
	{
		return trxTimeoutLogOnly;
	}

	@Override
	public ITrxConstraints setMaxTrx(final int max)
	{
		this.maxTrx = max;
		return this;
	}

	@Override
	public ITrxConstraints incMaxTrx(final int num)
	{
		this.maxTrx += num;
		return this;
	}

	@Override
	public ITrxConstraints setAllowTrxAfterThreadEnd(boolean allow)
	{
		this.allowTrxAfterThreadEnd = allow;
		return this;
	}

	@Override
	public int getMaxTrx()
	{
		return maxTrx;
	}

	@Override
	public ITrxConstraints setMaxSavepoints(int maxSavepoints)
	{
		this.maxSavepoints = maxSavepoints;
		return this;
	}

	@Override
	public int getMaxSavepoints()
	{
		return maxSavepoints;
	}

	@Override
	public ITrxConstraints setActive(boolean active)
	{
		this.active = active;
		return this;
	}

	@Override
	public boolean isActive()
	{
		return active;
	}

	@Override
	public boolean isOnlyAllowedTrxNamePrefixes()
	{
		return onlyAllowedTrxNamePrefixes;
	}

	@Override
	public ITrxConstraints setOnlyAllowedTrxNamePrefixes(boolean onlyAllowedTrxNamePrefixes)
	{
		this.onlyAllowedTrxNamePrefixes = onlyAllowedTrxNamePrefixes;
		return this;
	}

	@Override
	public ITrxConstraints addAllowedTrxNamePrefix(final String trxNamePrefix)
	{
		allowedTrxNamePrefixes.add(trxNamePrefix);
		return this;
	}

	@Override
	public ITrxConstraints removeAllowedTrxNamePrefix(final String trxNamePrefix)
	{
		allowedTrxNamePrefixes.remove(trxNamePrefix);
		return this;
	}
	
	@Override
	public Set<String> getAllowedTrxNamePrefixes()
	{
		return allowedTrxNamePrefixes;
	}

	@Override
	public boolean isAllowTrxAfterThreadEnd()
	{
		return allowTrxAfterThreadEnd;
	}

	@Override
	public void reset()
	{
		setActive(DEFAULT_ACTIVE);
		setAllowTrxAfterThreadEnd(DEFAULT_ALLOW_TRX_AFTER_TREAD_END);
		setMaxSavepoints(DEFAULT_MAX_SAVEPOINTS);
		setMaxTrx(DEFAULT_MAX_TRX);
		setTrxTimeoutSecs(DEFAULT_TRX_TIMEOUT_SECS, DEFAULT_TRX_TIMEOUT_LOG_ONLY);
		setOnlyAllowedTrxNamePrefixes(DEFAULT_ONLY_ALLOWED_TRXNAME_PREFIXES);
		allowedTrxNamePrefixes.clear();
	}

	@Override
	public String toString()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("TrxConstraints[");
		sb.append("active=" + this.active);
		sb.append(", allowedTrxNamePrefixes=" + getAllowedTrxNamePrefixes());
		sb.append(", allowTrxAfterThreadEnd=" + isAllowTrxAfterThreadEnd());
		sb.append(", maxSavepoints=" + getMaxSavepoints());
		sb.append(", maxTrx=" + getMaxTrx());
		sb.append(", onlyAllowedTrxNamePrefixes=" + isOnlyAllowedTrxNamePrefixes());
		sb.append(", trxTimeoutLogOnly=" + isTrxTimeoutLogOnly());
		sb.append(", trxTimeoutSecs=" + getTrxTimeoutSecs());
		sb.append("]");
		return sb.toString();
	}
}
