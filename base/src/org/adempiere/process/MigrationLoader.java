package org.adempiere.process;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ServerProcessCtl;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Trx;

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

		Properties ctx = Env.getCtx();

		try {
			Trx.run(trxName -> {
				int processId = 53175; // Import Migration from XML
				MPInstance instance = new MPInstance(ctx, processId, 0);
				instance.saveEx();
				// FailOnError
				MPInstancePara parameter = new MPInstancePara(instance,10);
				parameter.setParameter("FailOnError",failOnError);
				parameter.saveEx();
				// FileName
				parameter = new MPInstancePara(instance,20);
				parameter.setParameter("FileName", fileName);
				parameter.saveEx();
				// Apply
				parameter = new MPInstancePara(instance,30);
				parameter.setParameter("Apply", apply);
				parameter.saveEx();
				// Clean
				parameter = new MPInstancePara(instance,40);
				parameter.setParameter("Clean", clean);
				parameter.saveEx();
	
				ProcessInfo pi = new ProcessInfo("Import Migration from XML", processId);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
				pi.setRecord_ID(0);
				ServerProcessCtl migrationProcess = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
				migrationProcess.run();
				log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
				//if (failOnError && pi.isError())
				//	throw new AdempiereException(pi.getSummary());
			});
			
			// Run the post processes.
			Trx.run(trxName -> {
				// Run the post processes
				int processId = 258; // Sequence Check"
				MPInstance instance = new MPInstance(Env.getCtx(), processId, 0);
				instance.saveEx();
				ProcessInfo pi = new ProcessInfo("Sequence Check", processId);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());

				ServerProcessCtl sequenceCheck = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
                sequenceCheck.run();
				log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
            });

			Trx.run(trxName -> {
				int processId = 172; // Synchronize Terminology
				MPInstance instance = new MPInstance(Env.getCtx(), processId, 0);
				instance.saveEx();
				ProcessInfo pi = new ProcessInfo("Synchronize Terminology", processId);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
				ServerProcessCtl synchronizeTerminology = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
				synchronizeTerminology.run();
				log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
			});

			Trx.run(trxName -> {
				int processId = 295; // Role Access Update
				MPInstance instance = new MPInstance(Env.getCtx(), processId, 0);
				ProcessInfo pi = new ProcessInfo("Role Access Update", processId);
				instance.saveEx();
				pi.setAD_Client_ID(0);
				pi.setAD_User_ID(100);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
				ServerProcessCtl roleAccessUpdate = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
				roleAccessUpdate.run();
				log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
			});

			Trx.run(trxName -> {
				int processId = 53733; // UpdateGW
				MPInstance instance = new MPInstance(Env.getCtx(), processId, 0);
				instance.saveEx();
				ProcessInfo pi = new ProcessInfo("Updating Garden World", processId);
				pi.setAD_Client_ID(0);
				pi.setAD_User_ID(100);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());

				ServerProcessCtl updateGW = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
				updateGW.start();
				log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
			});
		} catch (AdempiereException e) {
			e.printStackTrace();
		}
	}
}
