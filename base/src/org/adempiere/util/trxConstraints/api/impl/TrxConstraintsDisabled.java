 /******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) <Company or Author Name> All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or              *
 * modify it under the terms of the GNU General Public License                *
 * as published by the Free Software Foundation; either version 2             *
 * of the License, or (at your option) any later version.                     *
 * This program is distributed in the hope that it will be useful,            *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *																		      *
 * @author Tobias Schöneberg, t.schoeneberg@metas.de, METAS GROUP			  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.util.trxConstraints.api.impl;

import java.util.Collections;
import java.util.Set;

import org.adempiere.util.trxConstraints.api.ITrxConstraints;

/**
 * This implementation is returned by {@link TrxConstraintsBL#getConstraints()} to indicate that the Trx constraints
 * have been globally disabled.
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
