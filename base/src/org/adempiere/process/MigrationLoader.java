package org.adempiere.process;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.compiere.Adempiere;
import org.compiere.model.I_AD_Migration;
import org.compiere.model.MMigration;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ServerProcessCtl;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.compiere.util.TrxRunnable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MigrationLoader {
	
	DocumentBuilder builder;
	/**	Logger	*/
	private static CLogger	log	= CLogger.getCLogger (MigrationLoader.class);
	
	public Comparator<File> fileComparator = new Comparator<File>() {
		// Note - Not locale sensitive.
	    public int compare(File f1, File f2) {
	        return f1.getName().compareToIgnoreCase(f2.getName());
	    }
	};
		
	static String readFile(File file, Charset encoding) throws IOException 
	{
	  byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
	  return new String(encoded, encoding);
	}
		
	@SuppressWarnings("unchecked")
	/**
	 * Load XML migration file or files.  
	 * @param file A file or directory containing migration files.  If file 
	 * points to a directory, a recursive search of all sub-directories will
	 * be performed and all XML files found will be loaded. 
	 * @param apply True if the loaded migrations are to be applied.
	 */
	public void loadXML(File file, Boolean apply)
	{
		Boolean success = false;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);

		try {
			builder = dbf.newDocumentBuilder();
			
			List<File> migrationFiles = new ArrayList<File>();
			
			if ( !file.exists() )
			{
				log.log(Level.WARNING, "No file or directory found");
				return;
			}
			else if ( file.isDirectory() )  // file exists
			{
				log.log(Level.CONFIG, "Processing migration files in directory: " + file.getAbsolutePath() );
				// Recursively find files
				migrationFiles = (List<File>) FileUtils.listFiles(file, new String[]{"xml"}, true);
				Collections.sort(migrationFiles, fileComparator);
			}
			else {
				log.log(Level.CONFIG, "Processing migration file: " + file.getAbsolutePath() );				
				migrationFiles.add(file);
			}
			
			
			for (File migFile : migrationFiles )
			{
				loadFile(migFile, apply);
			}
			
			success = true;

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// If the loading and application of the migrations was successful,
		// run the post processes.  Don't run these if the migrations are 
		// just loaded but not applied.
		if (apply && success) {
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
				int processId = 172; // Synchronize Terminolog
				MPInstance instance = new MPInstance(Env.getCtx(), processId, 0);
				instance.saveEx();
				ProcessInfo pi = new ProcessInfo("Synchronize Terminology", processId);
				pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
				ServerProcessCtl synchronizeTerminology = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
				synchronizeTerminology.run();
				log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
			});

			Trx.run(trxName -> {
				int processId = 172; // Role Access Update
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
				int processId = 53733; // Role Access Update
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
		}
	}
	
	public void loadFile(File file, Boolean apply) throws SAXException, IOException {

		if (!file.exists()) return;
		
		if (!file.getName().endsWith(".xml")) return;
		
		if (file.getName().equals("build.xml")) return; 
		
		log.log(Level.CONFIG, "Loading file: " + file);
		
		Document doc = builder.parse(file);

		NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
		Env.setContext(Env.getCtx() , "LogMigrationScriptBatch", "Y");
		for ( int i = 0; i < migrations.getLength(); i++ ) {

			Trx.run(new TrxRunnable() {
				private Properties ctx;
				private Element element;
				MMigration migration;

				TrxRunnable setParameters(Properties ctx, Element element, MigrationLoader loader) {
					this.ctx = ctx;
					this.element = element;
					return this;
				}

				public void run(String trxName) {
					try {
							migration = MMigration.fromXmlNode(ctx, element, trxName);
                            if (MMigration.STATUSCODE_Applied.equals(migration.getStatusCode())) {
                                log.log(Level.CONFIG, migration.toString() + " ---> Migration already applied - skipping.");
                                return;
                            }
							if (migration == null) {
								log.log(Level.CONFIG, "XML file not a Migration. Skipping.");
								return;
							}

							if (apply) {
								applyMigration(migration.getCtx(), migration.getAD_Migration_ID());
								migration.updateStatus();
							}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}.setParameters(Env.getCtx(), (Element) migrations.item(i), this));
		}
		Env.setContext(Env.getCtx() , "LogMigrationScriptBatch", "N");
	}

	private void applyMigration(Properties ctx  , int migrationId) {
		Trx.run(trxName -> {
			int processId = 53173; // Role Access Update
			MPInstance instance = new MPInstance(ctx, processId, migrationId);
			instance.saveEx();
			MPInstancePara parameter = new MPInstancePara(instance,10);
			parameter.setParameter("FailOnError",true);
			parameter.saveEx();

			ProcessInfo pi = new ProcessInfo("Apply migration", processId);
			pi.setAD_PInstance_ID(instance.getAD_PInstance_ID());
			pi.setRecord_ID(migrationId);
			ServerProcessCtl migrationProcess = new ServerProcessCtl(null, pi, Trx.get(trxName, false));
			migrationProcess.run();
			log.log(Level.CONFIG, "Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
		});
	}

	public static void main(String[] args) {
		
		
		Boolean clean_migrations = false;
		// If called with the argument "clean", the loader will apply any
		// outstanding migrations, mark ALL applied dictionary migrations as processed
		// and delete all the steps and data to save space.
		if (args.length > 0)
		 clean_migrations = args[0].equals("clean");
		
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.CONFIG);
		
		if (! DB.isConnected()) {
			log.info("No DB Connection");
			System.exit(1);
		}
		
		// Load migrations from the default location
		File home = new File (Adempiere.getAdempiereHome() + File.separator + "migration");
		Boolean apply = true;

		MigrationLoader loader = new MigrationLoader();		
		loader.loadXML(home, apply);  // and apply - each migration has to be applied before the next is loaded.
		loader.clean(Env.getCtx(), clean_migrations, null);		
	}

	private void clean(Properties ctx, Boolean clean_migrations, String trxName) {
		
		if (!clean_migrations)
			return;
		// For backward compatibility, check if the processed column has been added 
		// to the AD_Migration table
		// The processed column was added to AD_MigrationStep just prior to release 3.8.0.
		MMigration migration = new MMigration(ctx,0,trxName);
		if (migration.get_ColumnIndex(I_AD_Migration.COLUMNNAME_Processed) < 0)
			return;

		migration = null;
		Boolean notProcessed = false;
		for (MMigration mig : MMigration.getMigrations(ctx, notProcessed, trxName)) {
			if (mig != null)
				mig.clean();
		}
	}	
}
