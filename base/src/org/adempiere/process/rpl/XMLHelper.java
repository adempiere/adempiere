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
**********************************************************************/
package org.adempiere.process.rpl;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *	Utility class which helps with XML processing.
 *	
 *	@author Trifon Trifonov
 *	@version $Id$
 */
public class XMLHelper {

	private static XPath xPath = XPathFactory.newInstance().newXPath();

	public static Element getElement(String xPathExpression, Node node)
			throws XPathExpressionException {
		return (Element) xPath.evaluate(xPathExpression, node,
				XPathConstants.NODE);
	}

	public static Node getNode(String xPathExpression, Node node)
			throws XPathExpressionException {
		return (Node) xPath
				.evaluate(xPathExpression, node, XPathConstants.NODE);
	}

	public static NodeList getNodeList(String xPathExpression, Node node)
			throws XPathExpressionException {
		return (NodeList) xPath.evaluate(xPathExpression, node,
				XPathConstants.NODESET);
	}

	public static Double getNumber(String xPathExpression, Node node)
			throws XPathExpressionException {
		return (Double) xPath.evaluate(xPathExpression, node,
				XPathConstants.NUMBER);
	}

	public static String getString(String xPathExpression, Node node)
			throws XPathExpressionException {
		return (String) xPath.evaluate(xPathExpression, node,
				XPathConstants.STRING);
	}

	public static Boolean getBoolean(String xPathExpression, Node node)
			throws XPathExpressionException {
		return (Boolean) xPath.evaluate(xPathExpression, node,
				XPathConstants.BOOLEAN);
	}
	


	public static Document createDocumentFromFile(String pathToXmlFile)
			throws ParserConfigurationException, SAXException, IOException {
		// path to file is global
		String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
		String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
		// String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		// validate against XML Schema in dbsql2xml.xsd
		// documentBuilderFactory.setNamespaceAware(true);

		//INFO change validation to true. Someday when xsd file is complete...
		documentBuilderFactory.setValidating(false);
		documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
		// documentBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE, new File(pathToXsdFile));
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new File(pathToXmlFile));

		return document;
	}
	
	public static Document createDocumentFromString(String str)
	throws ParserConfigurationException, SAXException, IOException {
		// path to file is global
//		String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
//		String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
		// String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		// validate against XML Schema in dbsql2xml.xsd
		// documentBuilderFactory.setNamespaceAware(true);
		
		//INFO change validation to true. Someday when xsd file is complete...
		documentBuilderFactory.setValidating(false);
//		documentBuilderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
		// documentBuilderFactory.setAttribute(JAXP_SCHEMA_SOURCE, new File(pathToXsdFile));
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse( new InputSource(new StringReader( str ) ) );
		
		return document;
	}
	
}
