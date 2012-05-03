package org.compiere.process;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.compiere.model.MMigration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class MigrationFromXML extends SvrProcess {

	private String fileName;

	@Override
	protected String doIt() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = dbf.newDocumentBuilder();
		InputSource is1 = new InputSource(fileName);
		Document doc = builder.parse(is1);

		NodeList migrations = doc.getDocumentElement().getElementsByTagName("Migration");
		for ( int i = 0; i < migrations.getLength(); i++ )
			MMigration.fromXmlNode(getCtx(), (Element) migrations.item(i), get_TrxName());
		
		return "Import complete";

	}

	@Override
	protected void prepare() {
		ProcessInfoParameter[] paras = getParameter();
		for ( ProcessInfoParameter para : paras )
		{
			if ( para.getParameterName().equals("FileName"))
				fileName = (String) para.getParameter();
		}
	}

}
