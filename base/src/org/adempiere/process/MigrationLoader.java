package org.adempiere.process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.Adempiere;
import org.compiere.model.MMigration;
import org.compiere.model.MTable;
import org.compiere.util.CLogMgt;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MigrationLoader {
	

	/**	Logger	*/
	private CLogger	log	= CLogger.getCLogger (MigrationLoader.class);
	
	public void load(Properties ctx)
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
				log.log(Level.FINE, "Processing migration files in directory: " + home.getAbsolutePath() );
			}
			
			File[] migrationFiles = home.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					
					return name.endsWith(".xml");
				}
			});
			
			for (File file : migrationFiles )
			{

				log.log(Level.FINE, "Loading file: " + file);
				
				Document doc = builder.parse(file);

				NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
				for ( int i = 0; i < migrations.getLength(); i++ ) {
					MMigration.fromXmlNode(ctx, (Element) migrations.item(i), null);  //TODO trx
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
	
	public void applyMigrations() {
		String where = ("IsActive='Y'");
		
		List<MMigration> migrations = MTable.get(Env.getCtx(), MMigration.Table_Name)
		.createQuery(where, null).setOrderBy("SeqNo").list();
		
		for (MMigration migration : migrations )
		{
			log.log(Level.FINE, "Applying migration: " + migration);
			migration.setFailOnError(true);
			try {
				Trx trx = Trx.get("Migration", true);
				migration.set_TrxName(trx.getTrxName());
				migration.apply();
				trx.commit();
				trx.close();
			} catch (SQLException e) {
				throw new AdempiereException(e);
			}
		}
	}
	
	public static void main(String[] args) {
		Adempiere.startupEnvironment(false);
		CLogMgt.setLevel(Level.FINE);
		
		MigrationLoader loader = new MigrationLoader();
		loader.load(Env.getCtx());
		loader.applyMigrations();
		
	}
}
