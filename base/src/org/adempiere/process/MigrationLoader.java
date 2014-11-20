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

import org.adempiere.exceptions.AdempiereException;
import org.apache.commons.io.FileUtils;
import org.compiere.Adempiere;
import org.compiere.model.MColumn;
import org.compiere.model.MMigration;
import org.compiere.model.MTable;
import org.compiere.process.ProcessInfo;
import org.compiere.process.RoleAccessUpdate;
import org.compiere.process.SequenceCheck;
import org.compiere.process.SynchronizeTerminology;
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
	

	/**	Logger	*/
	private CLogger	log	= CLogger.getCLogger (MigrationLoader.class);
	private MMigration m_Migration;
	
	private Comparator<File> fileComparator = new Comparator<File>() {
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
		
	public void loadXML(Properties ctx)
	{
		
		if (! DB.isConnected()) {
			log.info("No DB Connection");
			System.exit(1);
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();
			
			
			File home = new File (Adempiere.getAdempiereHome() + File.separator + "migration");
			if ( !home.exists() && !home.isDirectory() )
			{
				log.log(Level.WARNING, "No migration directory");
				return;
			}
			else
			{
				log.log(Level.CONFIG, "Processing migration files in directory: " + home.getAbsolutePath() );
			}
			
			// Recursively find files
			@SuppressWarnings("unchecked")
			List<File> migrationFiles = (List<File>) FileUtils.listFiles(home, new String[]{"xml"}, true);
			Collections.sort(migrationFiles, fileComparator);

			
			for (File file : migrationFiles )
			{
				if (file.getName().equals("build.xml")) continue; 
				log.log(Level.CONFIG, "Loading file: " + file);
				
				Document doc = builder.parse(file);

				NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
				for ( int i = 0; i < migrations.getLength(); i++ ) {
					
				   Trx.run(new TrxRunnable() 
				   {
					   private Properties ctx;
					   private Element element;
					   private MigrationLoader loader;
					   
					   TrxRunnable setParamenters(Properties ctx , Element element, MigrationLoader loader)
					   {
						   this.ctx =  ctx;
						   this.element = element;
						   this.loader = loader;
						   return this;
					   }
			            public void run(String trxName) {
			            	try {
			            		MMigration mig = MMigration.fromXmlNode(ctx, element , trxName);
			            		if (loader != null) {
			            			loader.setMigration(mig);
			            		}
			            		if (mig == null) {
			            			log.log(Level.CONFIG, "XML file not a Migration. Skipping.");
			            		}
							} catch (SQLException e) {
								e.printStackTrace();
							}
			            }
			       }.setParamenters(ctx, (Element) migrations.item(i), this));
				   
				   // Apply the migration just loaded.
				   applyMigration(m_Migration);
				}
			}

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
	}
	
	private void applyMigration(MMigration migration) {
		
		if (migration == null)
			return;

		if (MMigration.STATUSCODE_Applied.equals(migration.getStatusCode())) {
			log.log(Level.CONFIG, "Migration already applied - skipping: " + migration);
			return;
		}
			
		migration.set_ColSyncCallback(this);
		log.log(Level.CONFIG, "Applying migration: " + migration);
		migration.setFailOnError(true);
		try {
			migration.apply();
		} catch (AdempiereException e) {
			throw new AdempiereException(e);
		}
		finally {
			migration.updateStatus(null);
		}
	}
	
	protected void setMigration(MMigration mig) {
		m_Migration = mig;
	}

	public void applyMigrations() {
		String where = ("IsActive='Y'");
		
		List<MMigration> migrations = MTable.get(Env.getCtx(), MMigration.Table_Name)
		.createQuery(where, null).setOrderBy("SeqNo").list();
		
		for (MMigration migration : migrations )
		{
			applyMigration(migration);
		}
	}
	
	public static void main(String[] args) {
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.CONFIG);
		
		MigrationLoader loader = new MigrationLoader();
		loader.loadXML(Env.getCtx());  // and apply - each migration has to be applied before the next is loaded.
		//loader.applyMigrations();	
		
		ProcessInfo pi = new ProcessInfo("Sequence Check", 258);
		pi.setAD_Client_ID(0);
		pi.setAD_User_ID(100);
		
		SequenceCheck scheck = new SequenceCheck();
		scheck.startProcess(Env.getCtx(), pi, null);

		System.out.println("Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());		
		
		
		pi = new ProcessInfo("Synchronize Terminology", 172);
		pi.setAD_Client_ID(0);
		pi.setAD_User_ID(100);
		
		SynchronizeTerminology sc = new SynchronizeTerminology();
		sc.startProcess(Env.getCtx(), pi, null);
		
		System.out.println("Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
		
		pi = new ProcessInfo("Role Access Update", 295);
		pi.setAD_Client_ID(0);
		pi.setAD_User_ID(100);
		
		RoleAccessUpdate rau = new RoleAccessUpdate();
		rau.startProcess(Env.getCtx(), pi, null);
		
		System.out.println("Process=" + pi.getTitle() + " Error="+pi.isError() + " Summary=" + pi.getSummary());
	}
	
	// Synchronizing the column in the database with the changes in the AD_Column table
	// has to be performed in its own transaction to avoid a database lock.
	// The following classes and interface provide a method of doing so.  
	
	/**
	 * Interface to identify the column to sync.  Called from the step.apply().
	 *
	 */
	public interface SyncCol {
		  public void addSyncColumn(int column_id);
	}
	
	/**
	 * Array list containing the column IDs that need to be synced with the database
	 */
	private List<Integer> syncColumns = new ArrayList<Integer>();
	
	/**
	 * Add a column ID to the list of columns that need to be synced with the database
	 * @param ad_column_id
	 */
	public void addSyncColumn (int ad_column_id) {
		syncColumns.add(ad_column_id);
	}
	
	/**
	 * Synchronize all columns in the list with the database.  This operation is performed in
	 * its own transaction.
	 */
	public void syncColumns() {
		if (syncColumns == null || syncColumns.size() == 0)
			return;

		for (int ad_column_id : syncColumns) {
			try {
				Trx.run(new SyncRunner(ad_column_id));
			}
			catch (org.adempiere.exceptions.DBException e) {
				log.log(Level.CONFIG, "Error synchronizing column " + ad_column_id + ". " + e.toString());
			}
		}
		syncColumns.clear();
	}

	class SyncRunner implements TrxRunnable {
			int ad_column_id;
				
			public SyncRunner(int column_id) {
				this.ad_column_id = column_id;
			}
			
			public void run(String trxName) {
	
			MColumn col = new MColumn(Env.getCtx(), ad_column_id, trxName);
			if (ad_column_id > 0 && !col.isVirtualColumn())	{
				log.log(Level.CONFIG, "Synchronizing column " + ad_column_id + ": " + col.toString());
				col.syncDatabase();
			}
		}
	}
}
