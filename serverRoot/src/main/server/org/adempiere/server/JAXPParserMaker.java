/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Trifon Trifonov.                                      *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Trifon Trifonov (trifonnt@users.sourceforge.net)                  *
*                                                                     *
* Sponsors:                                                           *
* - E-evolution (http://www.e-evolution.com)                          *
***********************************************************************/
package org.adempiere.server;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;

/**
 * 
 * @author Trifon N. Trifonov
 *
 */
public class JAXPParserMaker {
	
	public static StringBuffer text = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
	.append("<C_BPartner AD_Client_Value=\"GardenWorld\" Version=\"3.2.0\">")
	.append(" <AD_Client_ID>")
	.append("  <AD_Client_Value>GardenWorld</AD_Client_Value>")
	.append(" </AD_Client_ID>")
	.append(" <AD_Org_ID>")
	.append("  <Value>0</Value>")
	.append("  <AD_Client_ID>")
	.append("   <AD_Client_Value>SYSTEM</AD_Client_Value>")
	.append("  </AD_Client_ID>")
	.append(" </AD_Org_ID>")
	.append(" <Value>C&amp;W-test05</Value>")
	.append(" <Name>C&amp;W Construction-05</Name>")
	.append(" <C_BP_Group_ID>")
	.append("  <Value>Standard</Value>")
	.append("  <AD_Client_ID>")
	.append("   <AD_Client_Value>GardenWorld</AD_Client_Value>")
	.append("  </AD_Client_ID>")
	.append(" </C_BP_Group_ID>")
	.append(" <Created DateFormat=\"MM/dd/yyyy hh:mm:ss\">04/11/2001 09:11:36</Created>")
	.append(" <CreatedBy>")
	.append("  <Name>SuperUser</Name>")
	.append("  <AD_Client_ID>")
	.append("   <AD_Client_Value>SYSTEM</AD_Client_Value>")
	.append("  </AD_Client_ID>")
	.append(" </CreatedBy>")
	.append(" <Updated DateFormat=\"MM/dd/yyyy hh:mm:ss\">11/17/2007 01:49:50</Updated>")
	.append(" <UpdatedBy>")
	.append("  <Name>System</Name>")
	.append("  <AD_Client_ID>")
	.append("   <AD_Client_Value>SYSTEM</AD_Client_Value>")
	.append("  </AD_Client_ID>")
	.append(" </UpdatedBy>")
	.append("</C_BPartner>")
	;
	
	public static void main(String[] args) {

		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder parser = builderFactory.newDocumentBuilder();

			try {
				// Read the entire document into memory
				Document document = parser.parse( new InputSource(new StringReader( text.toString() ) ) );
				
				System.out.println( "Document = " + document);
				// work with the document...
			} catch (SAXException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}


		} catch (ParserConfigurationException e) {
			System.err.println("You need to install a JAXP aware parser.");
		}

	}

}

