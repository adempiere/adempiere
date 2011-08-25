package org.adempiere.process;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.compiere.Adempiere;
import org.compiere.model.MMigration;
import org.compiere.util.CLogger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MigrationLoader {
	

	/**	Logger	*/
	private CLogger	log	= CLogger.getCLogger (MigrationLoader.class);
	
	public void load(Properties ctx)
	{
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
				Document doc = builder.parse(file);

				NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
				for ( int i = 0; i < migrations.getLength(); i++ ) {
					MMigration migration = MMigration.fromXmlNode(ctx, (Element) migrations.item(i), null);  //TODO trx
					if ( migration != null )
					{
						migration.setFailOnError(true);
						migration.apply();
					}

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
