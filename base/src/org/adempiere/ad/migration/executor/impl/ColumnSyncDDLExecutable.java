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
 *                                                                            *
 * @author Teo Sarca , t.sarca@metas.ro , METAS GROUP						  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.migration.executor.impl;

import java.util.logging.Level;

import org.adempiere.ad.migration.executor.IMigrationExecutorContext;
import org.adempiere.ad.migration.executor.IPostponedExecutable;
import org.adempiere.util.Check;
import org.compiere.model.I_AD_Column;
import org.compiere.model.MColumn;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;

public class ColumnSyncDDLExecutable implements IPostponedExecutable
{
	protected final transient CLogger logger = CLogger.getCLogger(getClass());

	private final IMigrationExecutorContext migrationCtx;
	private final int adColumnId;
	private final boolean drop;

	public ColumnSyncDDLExecutable(IMigrationExecutorContext migrationCtx, final int adColumnId, final boolean drop)
	{
		Check.assume(adColumnId > 0, "adColumnId > 0");

		this.migrationCtx = migrationCtx;
		this.adColumnId = adColumnId;
		this.drop = drop;
	}

	@Override
	public void execute()
	{
		final MColumn column = retrieveColumn();
		if (column == null)
		{
			logger.log(Level.INFO, "No AD_Column found for " + adColumnId + ". Skip");
			return;
		}

		//
		if (drop)
		{
			// TODO unsync column?
			logger.warning("Please manualy drop column " + column.getColumnName() + "(" + column.getAD_Table() + ")");
		}
		else
		{
			column.syncDatabase();
		}
	}

	private MColumn retrieveColumn()
	{
		final MColumn column = new Query(migrationCtx.getCtx(), I_AD_Column.Table_Name, I_AD_Column.COLUMNNAME_AD_Column_ID + "=?", Trx.TRXNAME_None)
				.setParameters(adColumnId)
				.firstOnly();
		return column;
	}
}
