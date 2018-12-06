/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import javax.xml.validation.SchemaFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import org.xml.sax.XMLReader;


/**
 * Class for handle XML securitu and other utilities
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * <li> FR [ 2169 ] Improve default features for XML importer
 * @see https://github.com/adempiere/adempiere/issues/2169
 */
public class XMLUtils {
	
	/**
	 * Default features
	 * @param builder
	 * @throws ParserConfigurationException
	 */
	public static void setDefaultFeatures(DocumentBuilderFactory builder) throws ParserConfigurationException {
	    // Disable external DTDs as well
		String FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
		builder.setFeature(FEATURE, true);
	    FEATURE = "http://xml.org/sax/features/external-general-entities";
	    builder.setFeature(FEATURE, false);
	    FEATURE = "http://xml.org/sax/features/external-parameter-entities";
	    builder.setFeature(FEATURE, false);
	    FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
	    builder.setFeature(FEATURE, false);
	    builder.setXIncludeAware(false);
	    builder.setExpandEntityReferences(false);
	}
	
	/**
	 * Set default features
	 * @param transformerFactory
	 */
	public static void setDefaultFeatures(TransformerFactory transformerFactory) {
		transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
	}
	
	/**
	 * Default features for input factory
	 * @param xmlInputFactory
	 */
	public static void setDefaultFeatures(XMLInputFactory xmlInputFactory) {
		xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
		xmlInputFactory.setProperty("javax.xml.stream.isSupportingExternalEntities", false);
	}
	
	/**
	 * Default features for validator
	 * @param validator
	 * @throws SAXNotRecognizedException
	 * @throws SAXNotSupportedException
	 */
	public static void setDefaultFeatures(Validator validator) throws SAXNotRecognizedException, SAXNotSupportedException {
		validator.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		validator.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
	}

	/**
	 * Set default features for Schema factory
	 * @param factory
	 * @throws SAXNotSupportedException 
	 * @throws SAXNotRecognizedException 
	 */
	public static void setDefaultFeatures(SchemaFactory factory) throws SAXNotRecognizedException, SAXNotSupportedException {
		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		factory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
	}
	
	/**
	 * Set default features for SAX Transformer Factory
	 * @param saxTransformerFactory
	 */
	public static void setDefaultFeatures(SAXTransformerFactory saxTransformerFactory) {
		saxTransformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		saxTransformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
	}
	
	/**
	 * Default Features
	 * @param reader
	 * @throws SAXNotRecognizedException
	 * @throws SAXNotSupportedException
	 */
	public static void setDefaultFeatures(XMLReader reader) throws SAXNotRecognizedException, SAXNotSupportedException {
		reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
		reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
	}
	
	/**
	 * Set Default features
	 * @param saxParserFactory
	 * @throws SAXNotRecognizedException
	 * @throws SAXNotSupportedException
	 * @throws ParserConfigurationException
	 */
	public static void setDefaultFeatures(SAXParserFactory saxParserFactory) throws SAXNotRecognizedException, SAXNotSupportedException, ParserConfigurationException {
		saxParserFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
		saxParserFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
		saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	}
}
