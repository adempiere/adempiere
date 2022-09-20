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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.adempiere.ad.process.ISvrProcessPrecondition;
import org.adempiere.ad.services.IDeveloperModeBL;
import org.adempiere.core.domains.models.I_AD_Process;
import org.adempiere.core.domains.models.I_AD_Table_Process;
import org.adempiere.util.Check;
import org.adempiere.util.Services;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Trx;

/**
 * 
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/999">
 * 		@see FR [ 999 ] Add ZK Support for Process Action</a>
 *
 */
public class AProcessActionModel
{
	private static final String ACTION_Name = "Process";

	private final CLogger logger = CLogger.getCLogger(getClass());

	public String getActionName()
	{
		return ACTION_Name;
	}

	public List<MProcess> fetchProcesses(Properties ctx, GridTab gridTab)
	{
		final List<MProcess> emptyList = Collections.unmodifiableList(new ArrayList<MProcess>());
		if (gridTab == null)
		{
			return emptyList;
		}
		// get fields the button type that are displayed
		final List<GridField> gridFieldProcess = Arrays.stream(gridTab.getFields())
				.filter(field -> field.getAD_Process_ID() > 0 && field.isDisplayed())
				.collect(Collectors.toList());
		final MRole role = MRole.getDefault(ctx, false);
		Check.assumeNotNull(role, "No role found for {0}", ctx);
		final List<MProcess> list = fetchProcessesForTab(ctx, gridTab);

		for (Iterator<MProcess> it = list.iterator(); it.hasNext();)
		{
			final MProcess process = it.next();
			//Validate if process is displayed based on display rule
			Optional<GridField> maybeGridFieldProcess = gridFieldProcess.stream()
					.filter(field -> field.getAD_Process_ID() == process.getAD_Process_ID())
					.findFirst();
			boolean isDisplay = maybeGridFieldProcess.map(gridField -> gridField.isDisplayed(true)).orElse(true);
			if (!isDisplay) {
				it.remove();
				continue;
			}

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
	public String getDisplayName(final MProcess process) {
		String name = process.get_Translation(I_AD_Process.COLUMNNAME_Name);

		final IDeveloperModeBL developerModeBL = Services.get(IDeveloperModeBL.class);
		if (developerModeBL != null && developerModeBL.isEnabled()) {
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
	public String getDescription(final MProcess process) {
		String description = process.get_Translation(I_AD_Process.COLUMNNAME_Description);

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

	private boolean isPreconditionApplicable(MProcess process, GridTab gridTab)
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

	private List<MProcess> fetchProcessesForTab(final Properties ctx, GridTab gridTab)
	{
		final List<Integer> processIds = staticRegisteredProcesses.get(gridTab.getAD_Table_ID());

		final List<Object> params = new ArrayList<Object>();
		params.add(gridTab.getAD_Table_ID());
		final String whereClause =
				// AD_Process_ID present in AD_Table_Process table
				"EXISTS("
						+ " SELECT 1 "
						+ " FROM " + I_AD_Table_Process.Table_Name + " tp"
						+ " WHERE tp." + I_AD_Table_Process.COLUMNNAME_AD_Table_ID + "=?"
						+ "	AND tp." + I_AD_Table_Process.COLUMNNAME_IsActive + "='Y'"
						+ " AND tp." + I_AD_Table_Process.COLUMNNAME_AD_Process_ID + "=" 
						+ MProcess.Table_Name + "." + MProcess.COLUMNNAME_AD_Process_ID
						+ ")"
						+ " OR EXISTS("
						+ "			SELECT 1 "
						+ "			FROM AD_Field f "
						+ "			INNER JOIN AD_Column c ON(c.AD_Column_ID = f.AD_Column_ID) "
						+ "			WHERE f.AD_Tab_ID = " + gridTab.getAD_Tab_ID() + " "
						+ "			AND c.AD_Process_ID = AD_Process.AD_Process_ID "
						+ "			AND c.IsActive = 'Y' AND f.IsActive = 'Y' AND f.IsDisplayed = 'Y'"
						+ ")"
						// ... or AD_Process_ID was statically registered
						+ " OR " + MProcess.COLUMNNAME_AD_Process_ID + " IN " + DB.buildSqlList(processIds, params);

		final List<MProcess> list = new Query(ctx, MProcess.Table_Name, whereClause, Trx.TRXNAME_None)
				.setParameters(params)
				.setOnlyActiveRecords(true)
				.setOrderBy(MProcess.COLUMNNAME_Name)
				.list(MProcess.class);

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
