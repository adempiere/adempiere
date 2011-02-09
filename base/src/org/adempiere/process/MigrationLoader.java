package org.adempiere.process;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.compiere.model.MMigration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class MigrationLoader {
	
	public void load(Properties ctx)
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder;
		try {
			builder = dbf.newDocumentBuilder();
		Class klass = getClass();
		InputSource is1 = new InputSource(klass.getResourceAsStream("/org/adempiere/migration/Migration.xml"));
		Document doc = builder.parse(is1);

		NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
		for ( int i = 0; i < migrations.getLength(); i++ ) {
			int migrationId = MMigration.fromXmlNode(ctx, (Element) migrations.item(i), null);  //TODO trx
			if ( migrationId > 0 )
			{
				MMigration migration = new MMigration(ctx, migrationId, null);
				migration.setFailOnError(true);
				migration.apply();
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
