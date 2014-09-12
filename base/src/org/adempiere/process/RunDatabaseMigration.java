package org.adempiere.process;

import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * @author al
 */
public class RunDatabaseMigration
{
	private static CLogger logger = CLogger.getCLogger(SignDatabaseBuild.class);

	public static void main(String[] args)
	{
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.FINE);

		logger.info("Running Database Migration...");

		if (!DB.isConnected())
		{
			logger.info("No DB Connection");
			System.exit(1);
		}

		// Migration Loader will be called via shell script.
		MigrationLoader loader = new MigrationLoader();
		loader.loadXML(Env.getCtx());
	}
}
