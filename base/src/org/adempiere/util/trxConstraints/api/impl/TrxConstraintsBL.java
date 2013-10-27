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
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP		   			          *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.util.trxConstraints.api.impl;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;
import java.util.WeakHashMap;

import org.adempiere.service.ISysConfigBL;
import org.adempiere.util.Services;
import org.adempiere.util.trxConstraints.api.ITrxConstraints;
import org.adempiere.util.trxConstraints.api.ITrxConstraintsBL;
import org.compiere.util.Util;

public class TrxConstraintsBL implements ITrxConstraintsBL
{
	public static final String CFG_TRX_CONSTRAINTS_DISABLED = "org.compiere.util.trxConstraints.disabled";

	/**
	 * Instance to return in case that trx constraints have been globally disabled
	 */
	public static final TrxConstraintsDisabled disabled = TrxConstraintsDisabled.get();

	private final Map<Thread, Deque<ITrxConstraints>> thread2TrxConstraint = Collections.synchronizedMap(new WeakHashMap<Thread, Deque<ITrxConstraints>>());

	@Override
	public ITrxConstraints getConstraints()
	{
		final Thread callingThread = Thread.currentThread();
		return getConstraints(callingThread);
	}

	@Override
	public ITrxConstraints getConstraints(final Thread thread)
	{
		final ISysConfigBL sysconfigBL = Services.get(ISysConfigBL.class);
		if (sysconfigBL.getBooleanValue(CFG_TRX_CONSTRAINTS_DISABLED, true))
		{
			return disabled;
		}
		
		synchronized (thread2TrxConstraint)
		{
			Deque<ITrxConstraints> stack = thread2TrxConstraint.get(thread);
			if (stack == null)
			{
				stack = new ArrayDeque<ITrxConstraints>();
				thread2TrxConstraint.put(thread, stack);
			}

			if (!stack.isEmpty())
			{
				return stack.getFirst();
			}

			final ITrxConstraints newInstance = new TrxConstraints();
			stack.push(newInstance);

			return newInstance;
		}
	}

	@Override
	public void saveConstraints()
	{
		final Thread callingThread = Thread.currentThread();

		synchronized (thread2TrxConstraint)
		{
			ITrxConstraints constraints = getConstraints(callingThread); // make sure that there is at least one instance
			if (isDisabled(constraints))
				return;

			final Deque<ITrxConstraints> stack = thread2TrxConstraint.get(callingThread);
			Util.assume(stack != null, "Stack for thread " + callingThread + " is not null");
			Util.assume(!stack.isEmpty(), "Stack for thread " + callingThread + " is not empty");

			stack.push(new TrxConstraints(stack.getLast()));
		}
	}

	@Override
	public void restoreConstraints()
	{
		final Thread callingThread = Thread.currentThread();

		synchronized (thread2TrxConstraint)
		{

			final Deque<ITrxConstraints> stack = thread2TrxConstraint.get(callingThread);
			if (stack == null)
			{
				// There are no constraints for the calling thread.
				// In other words, getConstraints() hasn't been called yet.
				// Consequently there is nothing to restore.
				return;
			}

			Util.assume(!stack.isEmpty(), "Stack for thread " + callingThread + " is not empty");
			if (stack.size() <= 1)
			{
				// there is only the current constraint instance, but no saved instance.
				// Consequently there is nothing to restore.
				return;
			}

			stack.pop();
		}
	}

	@Override
	public boolean isDisabled(ITrxConstraints constraints)
	{
		return constraints instanceof TrxConstraintsDisabled;
	}
}
