package org.compiere.process;

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

import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.io.FileUtils;
import org.compiere.model.MMigration;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.service.dsl.ProcessBuilder;
import org.spin.util.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// This process is called from the Application Dictionary Menu to load Migrations from XML files.
// It is also called from the MigrationLoader which is called by RUN_MigrateXML.
public class MigrationFromXML extends MigrationFromXMLAbstract {

	private DocumentBuilder builder;
	private Boolean success = false;
	
	@Override
	protected String doIt() throws Exception {
		
		if ( Ini.isPropertyBool(Ini.P_LOGMIGRATIONSCRIPT) )
		{
			addLog( Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSetMessage"));
			return "@Error@" + Msg.getMsg(getCtx(), "LogMigrationScriptFlagIsSet");
		}
		
		loadXML();
//		clean();		

		return "Import complete";

	}

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
		
	/**
	 * Load the XML migration file or files.  
	 */
	@SuppressWarnings("unchecked")
	private void loadXML()
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);

		// file can be a file or directory
		File file = new File(getFilePathOrName());

		try {
			XMLUtils.setDefaultFeatures(dbf);
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
			
			success = true;			
			for (File migFile : migrationFiles )
			{
				loadFile(migFile);
				if (!success)
					break;
			}
			
		} catch ( ParserConfigurationException
				| SAXException 
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( AdempiereException e ) {
			if (!isForce())
				throw new AdempiereException("Loading Migration from XML failed.", e);
		}
		
	}
	private void loadFile(File file) throws SAXException, IOException {

		if (!file.exists()) return;
		
		if (!file.getName().endsWith(".xml")) return;
		
		if (file.getName().equals("build.xml")) return; 
		
		log.log(Level.CONFIG, "Loading file: " + file);
		
		Document doc = builder.parse(file);

		NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
		for ( int i = 0; i < migrations.getLength(); i++ ) {

			Element element = (Element) migrations.item(i);

			// Top level - create a new transaction for every migration and commit
			Trx.run(trxName -> {
				Properties ctx = Env.getCtx();
				MMigration migration;
				try {
						migration = MMigration.fromXmlNode(ctx, element, trxName);
						if (migration == null) {
							log.log(Level.CONFIG, "XML file not a Migration. Skipping.");
							return;
						}

						if (isApply()) {
                            if (MMigration.STATUSCODE_Applied.equals(migration.getStatusCode())) {
                                log.log(Level.CONFIG, migration.toString() + " ---> Migration already applied - skipping.");
                                return;
                            }
                            if (MMigration.STATUSCODE_Failed.equals(migration.getStatusCode())
                        		|| MMigration.STATUSCODE_PartiallyApplied.equals(migration.getStatusCode())) {
                                log.log(Level.CONFIG, migration.toString() + " ---> Migration exists but has to be rolled back.");
                                // Rollback the migration to try and correct the error.
    							applyMigration(migration.getCtx(), migration.getAD_Migration_ID(), trxName);                                
                            }
                            // Apply the migration
							applyMigration(migration.getCtx(), migration.getAD_Migration_ID(), trxName);
						}
				} catch (AdempiereException|SQLException e) {
					if (!isForce())
					{
						throw new AdempiereException("Loading migration from " + file.toString() + " failed.", e);
					}
				}
			});
		}
	}

	private void applyMigration(Properties ctx  , int migrationId, String trxName) throws AdempiereException {
		ProcessInfo processInfo = ProcessBuilder.create(ctx)
				.process(MigrationApply.getProcessId())
				.withTitle("Apply migration")
				.withRecordId(MMigration.Table_ID , migrationId)
				.withParameter("FailOnError",true)
				.withParameter(ISFORCE, isForce())
				.execute(trxName);

		log.log(Level.CONFIG, "Process=" + processInfo.getTitle() + " Error="+processInfo.isError() + " Summary=" + processInfo.getSummary());
		if (processInfo.isError())
			throw new AdempiereException(processInfo.getSummary());
	}

//	private void clean() {
//		
//		if (!is)
//			return;
//		
//		// For backward compatibility, check if the processed column has been added 
//		// to the AD_Migration table
//		// The processed column was added to AD_MigrationStep just prior to release 3.8.0.
//		MMigration migration = new MMigration(getCtx(),0,get_TrxName());
//		if (migration.get_ColumnIndex(I_AD_Migration.COLUMNNAME_Processed) < 0)
//			return;
//
//		migration = null;
//		Boolean notProcessed = false;
//		for (MMigration mig : MMigration.getMigrations(getCtx(), notProcessed, get_TrxName())) {
//			if (mig != null)
//				mig.clean();
//		}
//	}	
}