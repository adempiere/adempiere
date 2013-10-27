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
 * @author Teo Sarca, t.sarca@metas.ro, METAS GROUP							  *
 *  			                                                       		  *
 *****************************************************************************/
package org.adempiere.ad.migration.logger;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_Session;
import org.compiere.model.PO;
import org.compiere.model.POInfo;


public interface IMigrationLogger extends ISingletonService
{
	/**
	 * Create migration step using the current {@link IMigrationLoggerContext} for the specified {@link PO}
	 * 
	 * @param migrationCtx
	 * @param po
	 * @param info
	 * @param event
	 */
	void logMigration(IMigrationLoggerContext migrationCtx, PO po, POInfo info, String event);

	/**
	 * Create migration step using the current {@link I_AD_Session} for the specified {@link PO}
	 * 
	 * @param session
	 * @param po
	 * @param info
	 * @param event
	 */
	void logMigration(I_AD_Session session, PO po, POInfo info, String event);

	/**
	 * Create a raw SQL migration step for the specified {@link PO}
	 * 
	 * @param session
	 * @param po
	 * @param info
	 * @param event
	 */
	void logMigrationSQL(PO contextPO, String sql);

	/**
	 * Add table to ignore list (ignore specified table when logging migration steps).
	 * 
	 * @param tableName
	 */
	void addTableToIgnoreList(String tableName);

	/**
	 * Remove table from ignore list (do not ignore specified table when logging migration steps).
	 * 
	 * @param tableName
	 */
	void removeTableFromIgnoreList(String tableName);
}
