package org.adempiere.process;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.process.ProcessInfo;
import org.compiere.process.SynchronizeTerminology;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.eevolution.service.dsl.ProcessBuilder;

public class MigrationLoader {
	
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (MigrationLoader.class);
	

	public static void main(String[] args) {
		
		boolean clean_arg = false;
		// If called with the argument "clean", the loader will apply any
		// outstanding migrations, mark ALL applied dictionary migrations as processed
		// and delete all the steps and data to save space.
		if (args.length > 0)
		 clean_arg = args[0].equals("clean");
		
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.CONFIG);
		
		if (! DB.isConnected()) {
			log.info("No DB Connection");
			System.exit(1);
		}
		
		// Turn off the Log Migration Script preference.
		Ini.setProperty(Ini.P_LOGMIGRATIONSCRIPT, false);

		
		// Parameter values - these need to be final or effectively final
		// Load migrations from the default location
		String fileName = Adempiere.getAdempiereHome() + File.separator + "migration";
		boolean apply = true;
		boolean failOnError = true;
		boolean clean = clean_arg;

		Properties context = Env.getCtx();

		try {
			//Import Migration from XML
			ProcessInfo processInfo = ProcessBuilder.create(context)
			.process(org.compiere.process.MigrationFromXML.class)
			.withTitle("Import Migration from XML")
			.withParameter("FailOnError",failOnError)
			.withParameter("FileName", fileName)
			.withParameter("Apply", apply)
			.withParameter("Clean", clean)
			.execute();

			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());
			if (failOnError && processInfo.isError())
				throw new AdempiereException(processInfo.getSummary());

			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.SequenceCheck.class)
					.withTitle("Sequence Check")
					.execute();
			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.SynchronizeTerminology.class)
					.withTitle("Synchronize Terminology")
					.withParameter(SynchronizeTerminology.IsCreateElement,false)
					.withParameter(SynchronizeTerminology.IsDeletingUnusedElement, false)
					.execute();
			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.RoleAccessUpdate.class)
					.withTitle("Role Access Update")
					.withParameter("AD_Client_ID", 0)
					.executeUsingSystemRole();
			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());

			processInfo = ProcessBuilder.create(context)
					.process(org.compiere.process.CleanUpGW.class)
					.withTitle("Updating Garden World")
					.executeUsingSystemRole();
			log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());
		} catch (AdempiereException e) {
			e.printStackTrace();
		}
	}
}
