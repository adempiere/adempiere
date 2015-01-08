/*******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution * Copyright (C)
 * 1999-2009 Adempiere, Inc. All Rights Reserved. * This program is free
 * software; you can redistribute it and/or modify it * under the terms version
 * 2 of the GNU General Public License as published * by the Free Software
 * Foundation. This program is distributed in the hope * that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied * warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. * See the GNU General
 * Public License for more details. * You should have received a copy of the GNU
 * General Public License along * with this program; if not, write to the Free
 * Software Foundation, Inc., * 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307 USA. *
 * 
 ******************************************************************************/

package org.compiere.process;

import java.io.FileWriter;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.compiere.model.MMigration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * Process to export an AD migration script as xml
 * 
 * @author Paul Bowden, Adaxa Pty Ltd
 *
 */
public class MigrationToXML extends SvrProcess {

	private int migrationId = 0;
	private String fileName;

	@Override
	protected String doIt() throws Exception {
		MMigration migration = new MMigration(getCtx(), migrationId, get_TrxName());
		if ( migration == null || migration.is_new() )
			return "No migration to export";
		
		log.log(Level.FINE, "Creating xml document for migration: " + migration);
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.newDocument();
		Element root = document.createElement("Migrations");
		document.appendChild(root);
		root.appendChild(migration.toXmlNode(document));
		
		  //set up a transformer
        TransformerFactory transfac = TransformerFactory.newInstance();
        transfac.setAttribute("indent-number", 2);
        Transformer trans;
        
		trans = transfac.newTransformer();
		
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.STANDALONE, "yes");

        log.log(Level.FINE, "Writing xml to file.");
        //create string from xml tree
        FileWriter fw = new FileWriter(fileName);
        StreamResult result = new StreamResult(fw);
        DOMSource source = new DOMSource(document);
        trans.transform(source, result);
        fw.close();
        
		return "Exported migration to: " + fileName;
	}

	@Override
	protected void prepare() {

		ProcessInfoParameter[] paras = getParameter();
		for ( ProcessInfoParameter para : paras )
		{
			if ( para.getParameterName().equals("AD_Migration_ID"))
				migrationId =  para.getParameterAsInt();
			else if ( para.getParameterName().equals("FileName"))
				fileName = (String) para.getParameter();
		}
		
		// if run from Migration window
		if ( migrationId == 0 )
			migrationId = getRecord_ID();
		
		log.log(Level.FINE, "AD_Migration_ID = " + migrationId + ", filename = " + fileName);

	}

}
