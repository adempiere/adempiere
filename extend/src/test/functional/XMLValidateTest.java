//XMLValidateTest.java
package test.functional;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.MLocation;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.adempiere.pipo.*;
import org.compiere.util.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import org.xml.sax.SAXException;
import java.io.IOException;


public class XMLValidateTest extends TestCase {
	
	public void testValidateXML() {
		try {
			DocumentBuilder parser =
            			DocumentBuilderFactory.newInstance().newDocumentBuilder();
        		Document document = parser.parse(new File("PackOut.xml"));
		} catch (ParserConfigurationException e) {
            		// exception handling
			System.out.println("ParserConfigurationException: " + e.getMessage());
			assertTrue("testValidateXML", false);
		} catch (SAXException e) {
            		// exception handling - document not valid!
			System.out.println("SAXException: " + e.getMessage());
			assertTrue("testValidateXML", false);
        	} catch (IOException e) {
            		// exception handling
			System.out.println("IOException: " + e.getMessage());
			assertTrue("testValidateXML", false);
        	}

		assertTrue("testValidateXML", true);
	}
}
