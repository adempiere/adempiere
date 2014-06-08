/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (coffee) <Company or Author Name> All Rights Reserved.           *
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
 *                                                                            *
 * @author Teo Sarca, t.sarca@metas.ro METAS GROUP 							  *
 *				                                                              *
 *****************************************************************************/

package org.adempiere.apps.toolbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.ad.process.ISvrProcessPrecondition;
import org.adempiere.ad.service.IDeveloperModeBL;
import org.adempiere.model.POWrapper;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.compiere.model.GridTab;
import org.compiere.model.I_AD_Process;
import org.compiere.model.I_AD_Table_Process;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;


public class AProcessModel
{
	private static final String ACTION_Name = "Process";

	private final CLogger logger = CLogger.getCLogger(getClass());

	public String getActionName()
	{
		return ACTION_Name;
	}

	public List<I_AD_Process> fetchProcesses(Properties ctx, GridTab gridTab)
	{
		final List<I_AD_Process> emptyList = Collections.unmodifiableList(new ArrayList<I_AD_Process>());
		if (gridTab == null)
		{
			return emptyList;
		}

		final MRole role = MRole.getDefault(ctx, false);
		Check.assumeNotNull(role, "No role found for {0}", ctx);

		final int AD_Table_ID = gridTab.getAD_Table_ID();
		final List<I_AD_Process> list = fetchProcessesForTable(ctx, AD_Table_ID);

		for (Iterator<I_AD_Process> it = list.iterator(); it.hasNext();)
		{
			final I_AD_Process process = it.next();

			// Filter out processes on which we don't have access
			final Boolean accessRW = role.checkProcessAccess(process.getAD_Process_ID());
			if (accessRW == null)
			{
				logger.log(Level.FINE, "Removing process {0} because user has no access at all to it", process);
				it.remove();
				continue;
			}
			else if (!accessRW)
			{
				logger.log(Level.FINE, "Removing process {0} because user has only readonly access to it", process);
				it.remove();
				continue;
			}

			// Filter out processes which have preconditions which don't apply
			if (!isPreconditionApplicable(process, gridTab))
			{
				logger.log(Level.FINE, "Removing process {0} because preconditions were not met", process);
				it.remove();
				continue;
			}
		}

		return list;
	}

	/**
	 * Gets display name to be used in Gear
	 * 
	 * @param process
	 * @return process display name
	 */
	public String getDisplayName(final I_AD_Process process)
	{
		final I_AD_Process processTrl = POWrapper.translate(process, I_AD_Process.class);
		String name = processTrl.getName();

		final IDeveloperModeBL developerModeBL = Services.get(IDeveloperModeBL.class);
		if (developerModeBL != null && developerModeBL.isEnabled())
		{
			name += "/" + process.getValue();
		}

		return name;
	}

	/**
	 * Gets description/tooltip to be used in Gear
	 * 
	 * @param process
	 * @return description/tooltip
	 */
	public String getDescription(final I_AD_Process process)
	{
		final I_AD_Process processTrl = POWrapper.translate(process, I_AD_Process.class);
		String description = processTrl.getDescription();

		final IDeveloperModeBL developerModeBL = Services.get(IDeveloperModeBL.class);
		if (developerModeBL != null && developerModeBL.isEnabled())
		{
			if (description == null)
			{
				description = "";
			}
			else
			{
				description += "\n - ";
			}
			description += "Classname:" + process.getClassname() + ", ID=" + process.getAD_Process_ID();
		}

		return description;
	}

	private boolean isPreconditionApplicable(I_AD_Process process, GridTab gridTab)
	{
		if (Check.isEmpty(process.getClassname(), true))
		{
			return true;
		}

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader == null)
		{
			classLoader = getClass().getClassLoader();
		}

		final Class<?> processClass;
		try
		{
			processClass = classLoader.loadClass(process.getClassname());
		}
		catch (ClassNotFoundException e)
		{
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return false;
		}

		if (!ISvrProcessPrecondition.class.isAssignableFrom(processClass))
		{
			return true;
		}

		final ISvrProcessPrecondition svrProcessPrecondit;
		try
		{
			svrProcessPrecondit = processClass.asSubclass(ISvrProcessPrecondition.class).newInstance();
			return svrProcessPrecondit.isPreconditionApplicable(gridTab);
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
			return false;
		}
	}

	private List<I_AD_Process> fetchProcessesForTable(final Properties ctx, final int adTableId)
	{
		final List<Integer> processIds = staticRegisteredProcesses.get(adTableId);

		final List<Object> params = new ArrayList<Object>();
		params.add(adTableId);
		final String whereClause =
				// AD_Process_ID present in AD_Table_Process table
				I_AD_Process.COLUMNNAME_AD_Process_ID + " IN ("
						+ " SELECT " + I_AD_Table_Process.COLUMNNAME_AD_Process_ID
						+ " FROM " + I_AD_Table_Process.Table_Name + " tp"
						+ " WHERE tp." + I_AD_Table_Process.COLUMNNAME_AD_Table_ID + "=?" +
						"	AND tp." + I_AD_Table_Process.COLUMNNAME_IsActive + "='Y'"
						+ ")"
						// ... or AD_Process_ID was statically registered
						+ " OR " + I_AD_Process.COLUMNNAME_AD_Process_ID + " IN " + DB.buildSqlList(processIds, params);

		final List<I_AD_Process> list = new Query(ctx, I_AD_Process.Table_Name, whereClause, Trx.TRXNAME_None)
				.setParameters(params)
				.setOnlyActiveRecords(true)
				.setOrderBy(I_AD_Process.COLUMNNAME_Name)
				.list(I_AD_Process.class);

		return list;
	}

	/**
	 * Map: AD_Table_ID to list of AD_Process_ID
	 */
	private static final Map<Integer, List<Integer>> staticRegisteredProcesses = new HashMap<Integer, List<Integer>>();

	/**
	 * Registers a process for a certain table without the need of an <code>AD_Table_Process</code> record in the database.
	 * 
	 * @param adTableId
	 * @param adProcessId
	 */
	public static void registerProcess(final int adTableId, final int adProcessId)
	{
		Check.assume(adTableId > 0, "adTableId > 0");
		Check.assume(adProcessId > 0, "adProcessId > 0");

		List<Integer> processIds = staticRegisteredProcesses.get(adTableId);
		if (processIds == null)
		{
			processIds = new ArrayList<Integer>();
			staticRegisteredProcesses.put(adTableId, processIds);
		}
		if (!processIds.contains(adProcessId))
		{
			processIds.add(adProcessId);
		}
	}
}
