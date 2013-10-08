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
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.ad.service.ITrxListener;
import org.adempiere.ad.service.ITrxListenerManager;
import org.compiere.util.CLogger;

/**
 * Default {@link ITrxListenerManager} implementation
 *
 */
public class TrxListenerManager implements ITrxListenerManager
{
	private static final CLogger logger = CLogger.getCLogger(TrxListenerManager.class);

	private volatile List<ITrxListener> listeners = null;

	public TrxListenerManager()
	{
	}

	@Override
	public void registerListener(final ITrxListener listener)
	{
		if (listener == null)
		{
			return;
		}

		if (listeners == null)
		{
			listeners = new ArrayList<ITrxListener>();
		}
		if (listeners.contains(listener))
		{
			return;
		}
		listeners.add(listener);
	}

	@Override
	public void fireAfterCommit(final String trxName)
	{
		if (listeners == null)
		{
			return;
		}

		for (final ITrxListener listener : listeners)
		{
			try
			{
				listener.afterCommit(trxName);
			}
			catch (Exception e)
			{
				logger.log(Level.SEVERE, "Error while invoking afterCommit on transaction " + trxName + " using " + listener + ". Error was discarded.", e);
			}
		}
	}
}
