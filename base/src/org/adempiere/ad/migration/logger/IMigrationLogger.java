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

	void logMigration(IMigrationLoggerContext migrationCtx, PO po, POInfo info, String event);

	void logMigration(I_AD_Session session, PO po, POInfo info, String event);

	void logMigrationSQL(PO contextPO, String sql);

	void addTableToIgnoreList(String tableName);

	void removeTableFromIgnoreList(String tableName);
}
