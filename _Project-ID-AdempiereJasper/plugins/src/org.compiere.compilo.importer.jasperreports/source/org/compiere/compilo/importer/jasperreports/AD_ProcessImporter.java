package org.compiere.compilo.importer.jasperreports;

import java.sql.SQLException;

import javax.xml.xpath.XPathExpressionException;

import org.compiere.compilo.helper.XMLHelper;
import org.compiere.compilo.importer.core.ImportException;
import org.compiere.compilo.importer.core.TableImporter;
import org.compiere.model.MProcess;
import org.compiere.util.Env;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class AD_ProcessImporter extends TableImporter {

	
	/*
	 * Aim of this plugin is to import record having this structure:
	 * 
	<AD_Process>
	  <AD_Client_Value>GardenWorld</AD_Client_Value>
	  <AD_Org_Value>0</AD_Org_Value>
	  <IsActive>Y</IsActive>
	  <CreatedBy_Name>SuperUser</CreatedBy_Name>
	  <UpdatedBy_Name>SuperUser</UpdatedBy>
	  
	  <Value>Standalone</Value>
	  <Name>Standalone</Name>
	  <Description>Stand alone JasperReport for sample only</Description>
      <Classname>org.compiere.report.ReportStarter</Classname>
      <EntityType>User maintained</EntityType>
      <AccessLevel>Client+Organization</AccessLevel>
      <JasperReport>http://localhost:8088/webApp/standalone.jrxml</JasperReport>
	</AD_Process>
	 * 
	 * AD_Process_Node represents AD_Process XML element.
	 * 
	 * Using XMLHelper.getString("Name", AD_Process_Node); 
	 *  developer can get value of XML element "Name".
	 *  
	 *  (non-Javadoc)
	 * @see org.compiere.compilo.importer.core.TableImporter#importTable(org.w3c.dom.Node, org.w3c.dom.Element)
	 */
	public void importTable(Node AD_Process_Node, Element outElement) 
		throws DOMException, SQLException, XPathExpressionException, ImportException {
		// TODO - now "outElement" is not used... 
		// This means that we do not return result in xml file...
		Document outDocument = outElement.getOwnerDocument();
		Element result = outDocument.createElement("AD_Process");
		
		String name = null;
		String value = null;
		int    AD_Process_ID = 0;
		
		String AD_Client_Value = null;
		int    AD_Client_ID = 0;
		
		String AD_Org_Value = null;
		int    AD_Org_ID = 0;
		
		String CreatedBy_Name = null;
		int    CreatedBy_ID = 0;
		
		String UpdatedBy_Name = null;
		int    UpdatedBy_ID = 0;
		
		name = XMLHelper.getString("Name", AD_Process_Node);
		log.info("Name = [" + name +"]");
		result.appendChild(createNewTextElement("Name", ""+name, outDocument));
		
		value = XMLHelper.getString("Value", AD_Process_Node);
		log.info("Value = [" + value +"]");
		result.appendChild(createNewTextElement("Value", ""+value, outDocument));
		
		AD_Client_Value = XMLHelper.getString("AD_Client_Value", AD_Process_Node);
		log.info("AD_Client_Value = [" + AD_Client_Value +"]");
		result.appendChild(createNewTextElement("AD_Client_Value", ""+AD_Client_Value, outDocument));
		
		CreatedBy_Name = XMLHelper.getString("CreatedBy_Name", AD_Process_Node);
		log.info("CreatedBy_Name = [" + CreatedBy_Name +"]");
		result.appendChild(createNewTextElement("CreatedBy_Name", ""+CreatedBy_Name, outDocument));
		
		UpdatedBy_Name = XMLHelper.getString("UpdatedBy_Name", AD_Process_Node);
		log.info("UpdatedBy_Name = [" + UpdatedBy_Name +"]");
		result.appendChild(createNewTextElement("UpdatedBy_Name", ""+UpdatedBy_Name, outDocument));
		
		log.info("_______________________________________________");
		
		if (value != null && !"".equals(value)) {
			// Search for AD_Process by Value...
			AD_Process_ID = XMLHelper.getIDbyValue("AD_Process", value, AD_Client_Value);
		} else {
			// Search for AD_Process by Name...
			AD_Process_ID = XMLHelper.getIDbyName("AD_Process", name, AD_Client_Value);
		}
		log.info("AD_Process_ID = " + AD_Process_ID);
		result.appendChild(createNewTextElement("AD_Process_ID", ""+AD_Process_ID, outDocument));
		
		// Search for AD_Client_ID by Value...
		AD_Client_ID = XMLHelper.getIDbyValue("AD_Client", AD_Client_Value, AD_Client_Value);
		log.info("AD_Client_ID = " + AD_Client_ID);
		result.appendChild(createNewTextElement("AD_Client_ID", ""+AD_Client_ID, outDocument));
		
		// Search for AD_Org_ID by Value...
		AD_Org_ID = XMLHelper.getIDbyValue("AD_Org", AD_Org_Value, AD_Client_Value);
		log.info("AD_Org_ID = " + AD_Org_ID);
		result.appendChild(createNewTextElement("AD_Org_ID", ""+AD_Org_ID, outDocument));
		
		
		if (value == null || "".equals(value) ||
			name == null || "".equals(name) ||
			AD_Client_Value == null || "".equals(AD_Client_Value)) 
		{
			log.error("ERROR: Name or Value or AD_Client_Value is null...");
			System.out.println("ERROR: Name or Value or AD_Client_Value is null...");
			throw new ImportException("ERROR: Name or Value or AD_Client_Value is null...");
		}
		
		// Search for AD_User by Name...
		CreatedBy_ID = XMLHelper.getIDbyName("AD_User", CreatedBy_Name, AD_Client_Value);
		log.info("CreatedBy_ID = " + CreatedBy_ID);
		result.appendChild(createNewTextElement("CreatedBy_ID", ""+CreatedBy_ID, outDocument));
		if (CreatedBy_Name != null && !"".equals(CreatedBy_Name)) {
			//adRole.set_ValueNoCheck("CreatedBy", CreatedBy_ID);
			Env.setContext(Env.getCtx(), "#AD_User_ID", CreatedBy_ID);
		}
		UpdatedBy_ID = XMLHelper.getIDbyName("AD_User", UpdatedBy_Name, AD_Client_Value);
		log.info("UpdatedBy_ID = " + UpdatedBy_ID);
		result.appendChild(createNewTextElement("UpdatedBy_ID", ""+UpdatedBy_ID, outDocument));
		if (UpdatedBy_Name != null && !"".equals(UpdatedBy_Name)) {
			//adRole.set_ValueNoCheck("CreatedBy", CreatedBy_ID);
			Env.setContext(Env.getCtx(), "#AD_User_ID", UpdatedBy_ID);
		}
		
		Env.setContext(Env.getCtx(), "#AD_Client_ID", AD_Client_ID);
	    Env.setContext(Env.getCtx(), "#AD_Org_ID", AD_Org_ID);
	    
		MProcess process = new MProcess(Env.getCtx(), AD_Process_ID, null);
		
		if (name != null && !"".equals(name)) {
			process.setName(name);
		}
		if (value != null && !"".equals(value)) {
			process.setValue(value);
		}
		
		String Description = XMLHelper.getString("Description", AD_Process_Node);
		log.info("Description = " + Description);
		result.appendChild(createNewTextElement("Description", ""+Description, outDocument));
		if (Description != null && !"".equals(Description)) {
			process.setDescription(Description);
		}

		String Classname = XMLHelper.getString("Classname", AD_Process_Node);
		log.info("Classname = " + Classname);
		result.appendChild(createNewTextElement("Classname", ""+Classname, outDocument));
		if (Classname != null && !"".equals(Classname)) {
			process.setClassname(Classname);
		}
		
		String EntityType = XMLHelper.getString("EntityType", AD_Process_Node);
		log.info("EntityType = " + EntityType);
		result.appendChild(createNewTextElement("EntityType", ""+EntityType, outDocument));
		if (EntityType != null && !"".equals(EntityType)) {
			String entityTypeValue = XMLHelper.reverseReference("_Entity Type", EntityType);
			result.appendChild(createNewTextElement("EntityTypeValue", ""+entityTypeValue, outDocument));
    		if (entityTypeValue != null) {
    			process.setEntityType(entityTypeValue);
    		} else {
    			// Set Default entity type
    			process.setEntityType("U");
    		}
			
		}
		
		String AccessLevel = XMLHelper.getString("AccessLevel", AD_Process_Node);
		log.info("AccessLevel = " + AccessLevel);
		result.appendChild(createNewTextElement("AccessLevel", ""+AccessLevel, outDocument));
		if (AccessLevel != null && !"".equals(AccessLevel)) {
			String accessLevelValue = XMLHelper.reverseReference("AD_Table Access Levels", "Client+Organization");
			result.appendChild(createNewTextElement("AccessLevelValue", ""+accessLevelValue, outDocument));
			if (accessLevelValue != null) {
				process.setAccessLevel(accessLevelValue);
			} else {
				// Set Default access level
				process.setAccessLevel(XMLHelper.reverseReference("AD_Table Access Levels", "Client+Organization"));
			}
		}
		
		String JasperReport = XMLHelper.getString("JasperReport", AD_Process_Node);
		log.info("JasperReport = " + JasperReport);
		result.appendChild(createNewTextElement("JasperReport", ""+JasperReport, outDocument));
		if (JasperReport != null && !"".equals(JasperReport)) {
			
			try {
				process.setJasperReport(JasperReport);
			} catch (Error er) {
				er.printStackTrace();
				
				//process.set_CustomColumn("JasperReport", JasperReport);
			}
		}
		
		boolean resultSave = true;
		resultSave = process.save();
		log.info("--- RESULT SAVE = " + resultSave);
		result.appendChild(createNewTextElement("result", ""+resultSave, outDocument));
		outElement.appendChild(result);
	}

	private Element createNewTextElement(String elementName, String textNodeValue, Document outDocument) {
		Element newElement = outDocument.createElement(elementName);
		
		Text newText = outDocument.createTextNode(textNodeValue);
		
		newElement.appendChild(newText);
		
		return newElement;
	}
}
