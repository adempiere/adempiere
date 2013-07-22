/**
 * 
 */
package org.adempiere.ad.migration.logger;

import org.adempiere.util.ISingletonService;
import org.compiere.model.I_AD_Session;
import org.compiere.model.PO;
import org.compiere.model.POInfo;

/**
 * @author tsa
 * 
 */
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
