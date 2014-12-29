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
	public static void main(String[] args)
	{
		MigrationLoader.main(args);
	}
}
