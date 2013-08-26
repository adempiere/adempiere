package org.compiere.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MMigration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MigrationFromXML extends SvrProcess {

	private String fileName = null;
	private boolean apply = false;

	@Override
	protected String doIt() throws Exception {
		
		File file = new File(fileName);
		if ( !file.exists() )
			throw new AdempiereException("@FileNotFound@");
		
		File[] migrationFiles = null;
		if ( file.isDirectory() )
		{
			migrationFiles = file.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					
					return name.endsWith(".xml");
				}
			});	
		}
		else 
		{
			migrationFiles = new File[] {file};
		}
		
		for (File xmlfile : migrationFiles )
		{
			log.log(Level.FINE, "Loading migration from file: " + file.getAbsolutePath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			dbf.setIgnoringElementContentWhitespace(true);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			InputSource is1 = new InputSource(new FileInputStream(xmlfile));
			Document doc = builder.parse(is1);

			NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
			for ( int i = 0; i < migrations.getLength(); i++ )
			{
				MMigration migration = MMigration.fromXmlNode(getCtx(), (Element) migrations.item(i), get_TrxName());
				if ( apply )
					migration.apply();
			}
		}
		
		return "Import complete";

	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] paras = getParameter();
		for ( ProcessInfoParameter para : paras )
		{
			if ( para.getParameterName().equals("FileName"))
				fileName = (String) para.getParameter();
			else if ( para.getParameterName().equals("Apply"))
				apply  = para.getParameterAsBoolean();
		}
	}

}
