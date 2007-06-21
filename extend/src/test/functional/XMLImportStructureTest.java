//XMLImportStructureTest.java
package test.functional;

import java.io.*;
import java.util.*;
import java.util.Properties;
import java.util.logging.Level;

import junit.framework.TestCase;

import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Ini;
import org.adempiere.pipo.*;
import org.compiere.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.*;
import org.compiere.Adempiere;
import org.compiere.wf.*;

import java.math.BigDecimal;









public class XMLImportStructureTest extends TestCase {
	// Test: General
	private Properties testProperties = null;

	private Properties m_Ctx = null;
	
	private String fileName_DefaultValue = "J:/Trifon-CD-0.3/workspace/adempiere-trunk/adempiere/Adempiere/Adempiere.properties";
	private String fileName_Key = "AdempiereProperties";
	private String fileName_Value = "";
	
	private String isClient_DefaultValue = "Y";
	private String isClient_Key = "isClient";
	private boolean isClient_Value = true;

	private String AD_User_ID_DefaultValue = "0";
	private String AD_User_ID_Key = "AD_User_ID";
	private int AD_User_ID_Value = 0;

	private String AD_Client_ID_DefaultValue = "11";
	private String AD_Client_ID_Key = "AD_Client_ID";
	private int AD_Client_ID_Value = 11;

	// Test: Specific variables
	private MLocation location = null;
	

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		testProperties = new Properties();
		testProperties.load(new FileInputStream("test.properties"));
		fileName_Value = testProperties.getProperty(fileName_Key, fileName_DefaultValue);
		isClient_Value = "Y".equals( testProperties.getProperty(isClient_Key, isClient_DefaultValue) );
		AD_User_ID_Value = Integer.parseInt(testProperties.getProperty(AD_User_ID_Key, AD_User_ID_DefaultValue) );
		AD_Client_ID_Value = Integer.parseInt(testProperties.getProperty(AD_Client_ID_Key, AD_Client_ID_DefaultValue) );
		
		
		m_Ctx = new Properties();
		m_Ctx.setProperty("#AD_User_ID", new Integer(AD_User_ID_Value).toString());
		m_Ctx.setProperty("#AD_Client_ID", new Integer(AD_Client_ID_Value).toString());
		System.out.println("m_Ctx: " + m_Ctx);
		
		if (fileName_Value.length() < 1) {
		    assertEquals("Please specify path to Adempiere.properties file!", true, false);
		}
		
		System.setProperty("PropertyFile", fileName_Value);
		Ini.setClient (isClient_Value);
		org.compiere.Adempiere.startup(isClient_Value);

		// Force connection if there are enough parameters. Else we work with Adempiere.properties
//		if (args.length >= 6) {
//		    CConnection cc = CConnection.get(Database.DB_ORACLE, args[1], Integer.valueOf(args[2]).intValue(), args[3], args[4], args[5]);
//		    System.out.println("DB UserID:"+cc.getDbUid());
//		    DB.setDBTarget(cc);
//		}
	
		CLogMgt.setLevel(Level.FINEST);
/*		Available levels: 
		Level.OFF, Level.SEVERE, Level.WARNING, Level.INFO,
		Level.CONFIG, Level.FINE, Level.FINER, Level.FINEST, Level.ALL
*/
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		testProperties = null;
		m_Ctx = null;
	}

	public class TestPackInHandler extends DefaultHandler {
		OutputStream  fw_document = null;
    		private TransformerHandler hd_documemt = null;
    		private AttributesImpl attsOut = null;
    		private StreamResult streamResult_document = null;          
        	private SAXTransformerFactory tf_document = null;       
        	private Transformer serializer_document = null;
        	private int Start_Doc = 0;
        	private String Object_Status = null;
        	private String logDate = null;
        	private String PK_Status = "Installing";
        	String fileDate = null;
		int indentLevel = 0;

		private String          m_trxName = null;
		private Properties  m_ctx = null;
		StringBuffer sqlB = null;
		int AD_Package_Imp_ID=0;
		int AD_Package_Imp_Inst_ID=0;
		int AD_Backup_ID =0;
		private int m_AD_Client_ID = 0;


		Map<String, List<Map<String, String>>> elementAttsMap = new HashMap<String, List<Map<String,String>>>();

		public Map<String, List<Map<String, String>>> getElementAttsMap() {
			return elementAttsMap;
		}

		public void startElement (String uri, String localName, String qName, Attributes atts)
        throws org.xml.sax.SAXException {
			/*System.out.println("In startElement");
			System.out.println("uri: " + uri);
			System.out.println("localName: " + localName);
			System.out.println("qName: " + qName); */
			indentLevel++;
			//System.out.println("indentLevel: " + indentLevel);

			if (atts != null) {
				Map<String, String>  attsMap = new HashMap<String, String>();
				

            			for (int i = 0; i < atts.getLength(); i++) {
					attsMap.put(atts.getQName(i), atts.getValue(i));
                			/*System.out.println( "atts begin ----------");
                			System.out.println( atts.getLocalName(i));
                			System.out.println( atts.getQName(i));
                			System.out.println( atts.getValue(i));
                			System.out.println( "atts end   ----------"); */
            			} 

				attsMap.put("indentLevel", "" + indentLevel);

				List<Map<String, String>> m_attsMapList = elementAttsMap.get(qName);
                		if (m_attsMapList == null)
                    			elementAttsMap.put(qName, m_attsMapList=new ArrayList<Map<String, String>>());
				//System.out.println("adding atts.getLength: " + atts.getLength());
				//System.out.println("atts.getClass().getName(): " + atts.getClass().getName());
                		m_attsMapList.add(attsMap);
        		}

		}

		public void endElement (String uri, String localName, String qName) throws SAXException {
			//System.out.println("endElement qName: " + qName);
			indentLevel--;
			//System.out.println("indentLevel: " + indentLevel);
		        if(qName.equals("adempiereAD")) {
				processImportStructure();

			}
		}

		/*  The XML elements that need to be processed are as follows:
 			table
     			referencelist
         		printformatitem
	     		form
	         	message
		     	workflowNode
		        column
			printformat
			menuset
			workflowNodeNext
			referencetable
			processpara
			dynvalrule
			process
			preference
			tab
			field
			workflow
			menu
			window
			reference

			Dependency Analysis:

			windows don't depend on anything
			dynamic validation rules don't depend on anything
     			referencelist don't depend on anything
			forms don't depend on anything
			messages don't depend on anything
			references don't depend on anything

			tables depend on validation rules, windows
			columns depend on processes, references, tables, dynamic validation rules
			Fields depend on tables, windows
			printformats depend on tables, other printformats
         		printformatitems depend on printformats, tables
			workflows depend on tables 
			processes depend on workflows, printformats, reportviews 
		        workflowNodes depend on workflows, windows, processes, forms 		
			menus depend on windows, processes, forms, workflows, other menus
			workflowNodeNexts depend on workflows, workflowNodes
			referencetables depend on tables, columns
			processparas depend on processes, references, dynamic validation rules 
			preferences depend on windows
			tabs depend on columns, processes, tables, windows

		*/

		public void handleWindowsImport() {
			System.out.println("In handleWindowsImport");
			if(elementAttsMap.containsKey("window")) {

				List<Map<String, String>> m_windowsAttsMapList = elementAttsMap.get("window");
				System.out.println("processing " + m_windowsAttsMapList.size() + " windows");
					while(m_windowsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_windowsAttsMapList.get(0);
						System.out.println("window name: " +  attsMap.get("ADWindowNameID"));
						importWindow(attsMap);

						attsMap.clear();
						m_windowsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("window");
			}

		}

		public void handleDynValRulesImport() {
			System.out.println("In handleDynValRulesImport");

			if(elementAttsMap.containsKey("dynvalrule")) {

				List<Map<String, String>> m_dynvalrulesAttsMapList = elementAttsMap.get("dynvalrule");
				System.out.println("processing " + m_dynvalrulesAttsMapList.size() + " dynamic validation rules");
					while(m_dynvalrulesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_dynvalrulesAttsMapList.get(0);
						System.out.println("dynamic validation rule name: " +  attsMap.get("Name"));
						importDynValRule(attsMap);
						attsMap.clear();
						m_dynvalrulesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("dynvalrule");
			}

		}


		public void handleReferenceListsImport() {
			System.out.println("In handleReferenceListsImport");
			if(elementAttsMap.containsKey("referencelist")) {

				List<Map<String, String>> m_referenceListsAttsMapList = elementAttsMap.get("referencelist");
				System.out.println("processing " + m_referenceListsAttsMapList.size() + " referencelists");
					int i = 0;
					while(m_referenceListsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_referenceListsAttsMapList.get(0);
						System.out.println("referenceList num: "+ i++ + " name: " +  attsMap.get("Name"));
						importReferenceList(attsMap);
						attsMap.clear();
						m_referenceListsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("referencelist");
			}

		}

		public void handleFormsImport() {
			System.out.println("In handleFormsImport");
			if(elementAttsMap.containsKey("form")) {

				List<Map<String, String>> m_formsAttsMapList = elementAttsMap.get("form");
				System.out.println("processing " + m_formsAttsMapList.size() + " forms");
					while(m_formsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_formsAttsMapList.get(0);
						System.out.println("form name: " +  attsMap.get("ADFormNameID"));
						importForm(attsMap);
						attsMap.clear();
						m_formsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("form");
			}

		}


		public void handleMessagesImport() {
			System.out.println("In handleMessagesImport");
			if(elementAttsMap.containsKey("message")) {

				List<Map<String, String>> m_messagesAttsMapList = elementAttsMap.get("message");
				System.out.println("processing " + m_messagesAttsMapList.size() + " messages");
					while(m_messagesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_messagesAttsMapList.get(0);
						System.out.println("message value: " +  attsMap.get("Value"));
						importMessage(attsMap);		
						attsMap.clear();
						m_messagesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("message");
			}

		}

		public void handleReferencesImport() {
			System.out.println("In handleReferencesImport");
			if(elementAttsMap.containsKey("reference")) {

				List<Map<String, String>> m_referencesAttsMapList = elementAttsMap.get("reference");
				System.out.println("processing " + m_referencesAttsMapList.size() + " references");
					int i = 0;
					while(m_referencesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_referencesAttsMapList.get(0);
						System.out.println("reference num: " + i++ +" name: " +  attsMap.get("Name"));
						importReference(attsMap);		
						attsMap.clear();
						m_referencesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("reference");
			}

		}


		public void handleTablesImport() {
			System.out.println("In handleTablesImport");
			if(elementAttsMap.containsKey("table")) {

				List<Map<String, String>> m_tablesAttsMapList = elementAttsMap.get("table");
				System.out.println("processing " + m_tablesAttsMapList.size() + " tables");
					while(m_tablesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_tablesAttsMapList.get(0);
						System.out.println("table name: " +  attsMap.get("Name"));
						importTable(attsMap);
						attsMap.clear();
						m_tablesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("table");
			}

		}

		public void handleColumnsImport() {
			System.out.println("In handleColumnsImport");
			if(elementAttsMap.containsKey("column")) {

				List<Map<String, String>> m_columnsAttsMapList = elementAttsMap.get("column");
				System.out.println("processing " + m_columnsAttsMapList.size() + " columns");
					int i = 0;
					while(m_columnsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_columnsAttsMapList.get(0);
						//if(attsMap.get("ADColumnNameID").equals("isAvailable") && attsMap.get("ADTableNameID").equals("S_Resource")) {
							System.out.println("column num: "+ i++ +" name: " +  attsMap.get("ADColumnNameID"));
							System.out.println("table name: " +  attsMap.get("ADTableNameID"));
						importColumn(attsMap);
						//}

						attsMap.clear();
						m_columnsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("column");
			}

		}


		public void handleFieldsImport() {
			System.out.println("In handleFieldsImport");
			if(elementAttsMap.containsKey("field")) {

				List<Map<String, String>> m_fieldsAttsMapList = elementAttsMap.get("field");
				System.out.println("processing " + m_fieldsAttsMapList.size() + " fields");
					int i = 0;
					while(m_fieldsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_fieldsAttsMapList.get(0);
						System.out.println("field num: "+ i++ +" name: " +  attsMap.get("ADFieldNameID"));
						importField(attsMap);		
						attsMap.clear();
						m_fieldsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("field");
			}

		}

		public void handlePrintFormatsImport() {
			System.out.println("In handlePrintFormatsImport");
			if(elementAttsMap.containsKey("printformat")) {

				List<Map<String, String>> m_printformatsAttsMapList = elementAttsMap.get("printformat");
				System.out.println("processing " + m_printformatsAttsMapList.size() + " printformats");
					while(m_printformatsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_printformatsAttsMapList.get(0);
						System.out.println("printformat name: " +  attsMap.get("Name"));
						importPrintFormat(attsMap);		
						attsMap.clear();
						m_printformatsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("printformat");
			}

		}


		public void handlePrintFormatItemsImport() {
			System.out.println("In handlePrintFormatItemsImport");
			if(elementAttsMap.containsKey("printformatitem")) {

				List<Map<String, String>> m_printformatitemsAttsMapList = elementAttsMap.get("printformatitem");
				System.out.println("processing " + m_printformatitemsAttsMapList.size() + " printformats");
					while(m_printformatitemsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_printformatitemsAttsMapList.get(0);
						System.out.println("printformatitem name: " +  attsMap.get("Name"));
						importPrintFormatItem(attsMap);
						attsMap.clear();
						m_printformatitemsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("printformatitem");
			}

		}

		public void handleWorkflowsImport() {
			System.out.println("In handleWorkflowsImport");
			if(elementAttsMap.containsKey("workflow")) {

				List<Map<String, String>> m_workflowsAttsMapList = elementAttsMap.get("workflow");
				System.out.println("processing " + m_workflowsAttsMapList.size() + " workflows");
					while(m_workflowsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_workflowsAttsMapList.get(0);
						System.out.println("worklfow name: " +  attsMap.get("Name"));
						importWorkflow(attsMap);
						attsMap.clear();
						m_workflowsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("workflow");
			}

		}


		public void handleProcessesImport() {
			System.out.println("In handleProcessesImport");
			if(elementAttsMap.containsKey("process")) {

				List<Map<String, String>> m_processesAttsMapList = elementAttsMap.get("process");
				System.out.println("processing " + m_processesAttsMapList.size() + " processes");
					while(m_processesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_processesAttsMapList.get(0);
						System.out.println("process name: " +  attsMap.get("Name"));
						importProcess(attsMap);
						attsMap.clear();
						m_processesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("process");
			}

		}

		public void handleWorkflowNodesImport() {
			System.out.println("In handleWorkflowNodesImport");
			if(elementAttsMap.containsKey("workflowNode")) {

				List<Map<String, String>> m_workflowNodesAttsMapList = elementAttsMap.get("workflowNode");
				System.out.println("processing " + m_workflowNodesAttsMapList.size() + " workflowNodes");
					int i = 0;
					while(m_workflowNodesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_workflowNodesAttsMapList.get(0);
						System.out.println("worklfowNode  num: "+ i++ +" name: " +  attsMap.get("Name"));
						importWorkflowNode(attsMap);
						attsMap.clear();
						m_workflowNodesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("workflowNode");
			}

		}

		public void importForm(Map<String, String> attsMap) {
			MForm m_Form = null;
			
			String entitytype = attsMap.get("EntityType");		
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String name = attsMap.get("ADFormNameID");
				int id = get_ID("AD_Form", name);
				m_Form = new MForm(m_ctx, id, m_trxName);
				if (id > 0){
					AD_Backup_ID = copyRecord("AD_Form",m_Form);
					Object_Status = "Update";
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}	    
				m_Form.setClassname (attsMap.get("Classname"));
				m_Form.setIsBetaFunctionality (Boolean.valueOf(attsMap.get("isBetaFunctionality")).booleanValue());
				m_Form.setAccessLevel(attsMap.get("AccessLevel"));
				m_Form.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Form.setEntityType(attsMap.get("EntityType"));
				m_Form.setHelp(attsMap.get("Help").replaceAll(",",""));
				m_Form.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Form.setName(attsMap.get("Name")); 
				
				try {
				if (m_Form.save(m_trxName) == true) {		    	
					System.out.println("m_Form.save succeeded");
					record_log (1, m_Form.getName(),"Form", m_Form.get_ID(),AD_Backup_ID, Object_Status,"AD_Form",get_IDWithColumn("AD_Table", "TableName", "AD_Form"));           		        		
				}
				else{
					System.out.println("m_Form.save failed");
					record_log (0, m_Form.getName(),"Form", m_Form.get_ID(),AD_Backup_ID, Object_Status,"AD_Form",get_IDWithColumn("AD_Table", "TableName", "AD_Form"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importForm: " + e.getMessage());

				}
			}
		}

		public void importColumn(Map<String, String> attsMap) {
			MColumn m_Column = null;
			int success=0;
			String entitytype = attsMap.get("EntityType");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String columnName = attsMap.get("ColumnName");
				
				int tableid = get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID"));		    
				int id =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", columnName, "AD_Table", tableid);	    
				m_Column = new MColumn(m_ctx, id, m_trxName);
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_Column",m_Column);
					Object_Status = "Update";
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}			
				m_Column.setColumnName(columnName);
				
				String Name = attsMap.get("ADProcessNameID");	    
				id = get_IDWithColumn("AD_Process", "Name", Name);
				m_Column.setAD_Process_ID(id);		    
				Name = attsMap.get("ADReferenceNameID");
//				log.info("Column Name1 ->"+Name);
//				log.info("Database Name ->"+m_DatabaseType);		
				/**
				 //Adjust for difference between Oracle and PostgreSql DataTypes
				  if (m_DatabaseType.equals("Oracle")){
				  if (Name.equals("TIMESTAMP"))
				  Name = "Date";
				  else if (Name.equals("TIMESTAMP+Time"))
				  Name = "Date+Time";
				  else if (Name.equals("NUMERIC"))
				  Name = "Number";}
				  else if (m_DatabaseType.equals("Sybase")){
				  if (Name.equals("TIMESTAMP"))
				  Name = "Date";
				  else if (Name.equals("TIMESTAMP+Time"))
				  Name = "Date+Time";
				  else if (Name.equals("NUMERIC"))
				  Name = "Number";}
				  else if (m_DatabaseType.equals("PostgreSQL")){
				  if (Name.equals("Date"))					
				  Name = "TIMESTAMP";					
				  else if (Name.equals("Date+Time"))
				  Name = "TIMESTAMP+Time";					
				  else if (Name.equals("Number"))
				  Name = "NUMERIC";}
				  **/
//				log.info("Column Name2 ->"+Name);
				id = get_IDWithColumn("AD_Reference", "Name", Name);
				m_Column.setAD_Reference_ID(id);		    
//				log.info("Column ID ->"+id);
				Name = attsMap.get("ADTableNameID");	    
				id = get_IDWithColumn("AD_Table", "TableName", Name);
				m_Column.setAD_Table_ID(id);
				
				Name = attsMap.get("ADValRuleNameID");	    
				id = get_IDWithColumn("AD_Val_Rule", "Name", Name);			    
				m_Column.setAD_Val_Rule_ID(id);
				Name = attsMap.get("ADReferenceNameValueID");
				id = get_IDWithColumn("AD_Reference", "Name", Name);			    
				m_Column.setAD_Reference_Value_ID(id);
				m_Column.setCallout(attsMap.get("Callout"));
				m_Column.setColumnSQL(attsMap.get("ColumnSQL"));
				
				m_Column.setColumnName(attsMap.get("ColumnName"));
				m_Column.setDefaultValue(attsMap.get("DefaultValue"));
				m_Column.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Column.setEntityType(attsMap.get("EntityType"));
				
				if (Integer.parseInt(attsMap.get("FieldLength")) >0)
					m_Column.setFieldLength (Integer.parseInt(attsMap.get("FieldLength")));
				m_Column.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Column.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Column.setIsAlwaysUpdateable((Boolean.valueOf(attsMap.get("isAlwaysUpdateable")).booleanValue()));
				//m_Column.setIsEncrypted(atts.getValue("isEncrypted"));	            
				m_Column.setIsIdentifier((Boolean.valueOf(attsMap.get("isIdentifier")).booleanValue()));
				m_Column.setIsKey((Boolean.valueOf(attsMap.get("isKey")).booleanValue()));
				m_Column.setIsMandatory((Boolean.valueOf(attsMap.get("isMandatory")).booleanValue()));
				
				m_Column.setIsParent((Boolean.valueOf(attsMap.get("isParent")).booleanValue()));	            
				m_Column.setIsSelectionColumn((Boolean.valueOf(attsMap.get("isSelectionColumn")).booleanValue()));
				m_Column.setIsSyncDatabase (attsMap.get("getIsSyncDatabase"));
				
				m_Column.setIsTranslated((Boolean.valueOf(attsMap.get("isTranslated")).booleanValue()));
				m_Column.setIsUpdateable((Boolean.valueOf(attsMap.get("isUpdateable")).booleanValue()));
				m_Column.setName(attsMap.get("Name"));
				m_Column.setReadOnlyLogic(attsMap.get("ReadOnlyLogic"));
				
				if (Integer.parseInt(attsMap.get("SeqNo")) >0) 
					m_Column.setSeqNo(Integer.parseInt(attsMap.get("SeqNo")));
				m_Column.setVFormat(attsMap.get("VFormat"));
				if (attsMap.get("ValueMax") != null) 
					m_Column.setValueMax(attsMap.get("ValueMax"));
				if (attsMap.get("ValueMin") != null)
					m_Column.setValueMin(attsMap.get("ValueMin"));
				if (attsMap.get("Version") != null)
					m_Column.setVersion(new BigDecimal(attsMap.get("Version")));
				
				// Setup Element.
				id = get_IDWithColumn("AD_Element", "ColumnName", m_Column.getColumnName());
				X_AD_Element element =	new X_AD_Element(m_ctx, id, m_trxName);
				
				String Object_Status_col = Object_Status;
				if (element.getAD_Element_ID() == 0) {
					//Object_Status = "New";
					element.setColumnName(m_Column.getColumnName());
					element.setEntityType(m_Column.getEntityType());
					element.setPrintName(m_Column.getColumnName());
					
					element.setName(m_Column.getColumnName());			
					try {
					if (element.save(m_trxName) == true){            	
						System.out.println("element.savesucceded");
						record_log (1, m_Column.getName(),"Element", element.getAD_Element_ID(),AD_Backup_ID, "New","AD_Element",get_IDWithColumn("AD_Table", "TableName", "AD_Element"));
					} else {
						System.out.println("element.savefailed");
						record_log (0, m_Column.getName(),"Element", element.getAD_Element_ID(),AD_Backup_ID, "New","AD_Element",get_IDWithColumn("AD_Table", "TableName", "AD_Element"));
					}
					} catch (Exception e) {
						System.out.println("import Column element.save exception: " + e.getMessage());

					}
				}
				
				Object_Status = Object_Status_col;
				m_Column.setAD_Element_ID(element.getAD_Element_ID());
				
				boolean recreateColumn = 
					(
					    m_Column.is_new()
					 || m_Column.is_ValueChanged("AD_Reference_ID")
					 || m_Column.is_ValueChanged("FieldLength")
					 || m_Column.is_ValueChanged("ColumnName")
					 || m_Column.is_ValueChanged("IsMandatory")
					);

				// changed default ??
				// m_Column.is_ValueChanged("DefaultValue") doesn't work well with nulls
				System.out.println("recreateColumn: " + recreateColumn);
				if (! recreateColumn) {
					String oldDefault = (String) m_Column.get_ValueOld("DefaultValue");
					String newDefault = (String) m_Column.get_Value("DefaultValue");
					if (oldDefault != null && oldDefault.length() == 0)
						oldDefault = null;
					if (newDefault != null && newDefault.length() == 0)
						newDefault = null;
					if ((oldDefault == null && newDefault != null) ||
						(oldDefault != null && newDefault == null)) {
						recreateColumn = true;
					} else if (oldDefault != null && newDefault != null) {
						if (! oldDefault.equals(newDefault))
							recreateColumn = true;
					}
				}
				
				// Don't create database column for virtual columns
				// Don't create columns by default, just if getIsSyncDatabase='Y' 
				if (recreateColumn) {
					String sync = attsMap.get("getIsSyncDatabase");
					if (m_Column.isVirtualColumn() || sync == null || (!sync.equals("Y")))
						recreateColumn = false;
				}
				try {
				
				if (m_Column.save(m_trxName) == true){		    	
					System.out.println("m_Column.save succeeded");
					record_log (1, m_Column.getName(),"Column", m_Column.get_ID(),AD_Backup_ID, Object_Status,"AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));           		        		
				} else {
					System.out.println("m_Column.save failed");
					record_log (0, m_Column.getName(),"Column", m_Column.get_ID(),AD_Backup_ID, Object_Status,"AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
				}
				} catch(Exception e) {
					System.out.println("Exception in importColumn with m_Column.save: " + e.getMessage());

				}

				System.out.println("recreateColumn: " + recreateColumn);
				//FIXME:  wght total hack
				if(attsMap.get("ADTableNameID").equals("PP_WF_Node_Asset") || attsMap.get("ADTableNameID").equals("AD_User")) 
					recreateColumn = false;
				else
					recreateColumn = true;

				if (recreateColumn) {
					System.out.println("About to call createColumn");
					success = createcolumn (m_Column);
					System.out.println("After call to createColumn");
					try {
					
					if (success == 1){	    		           		        		
						record_log (1, m_Column.getColumnName(),"dbColumn", m_Column.get_ID(),0, Object_Status,attsMap.get("ADTableNameID").toUpperCase(),get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID").toUpperCase()));
					} else {
						record_log (0, m_Column.getColumnName(),"dbColumn", m_Column.get_ID(),0, Object_Status,attsMap.get("ADTableNameID").toUpperCase(),get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID").toUpperCase()));
					}
					} catch(Exception e) {
						System.out.println("Exception in importColumn: " + e.getMessage());
					}
			}
		}
		}

		public void importTable(Map<String, String> attsMap) {
			MTable m_Table = null;
			String entitytype = attsMap.get("EntityType");
			
			if (entitytype.equals("U") || entitytype.equals("D")) {
				
				String tableName = attsMap.get("ADTableNameID");
				
				int id = get_IDWithColumn("AD_Table", "TableName", tableName);
				
				m_Table = new MTable(m_ctx, id, m_trxName);
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_Table",m_Table);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				m_Table.setTableName(tableName);
				String Name = attsMap.get("ADWindowNameID");	    
				id = get_IDWithColumn("AD_Window", "Name", Name);
				m_Table.setAD_Window_ID(id);
				Name = attsMap.get("POWindowNameID");
				if (Name != null){
					id = get_IDWithColumn("AD_Window", "Name", Name);
					m_Table.setPO_Window_ID(id);
				}
				else
					Name = attsMap.get("ADValRuleNameID");
				id = get_IDWithColumn("AD_Val_Rule", "Name", Name);
				
				m_Table.setAD_Val_Rule_ID(id);
				m_Table.setAccessLevel (attsMap.get("AccessLevel"));		    
				m_Table.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Table.setEntityType(attsMap.get("EntityType"));
				m_Table.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));            
				m_Table.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Table.setImportTable(attsMap.get("ImportTable"));
				m_Table.setIsChangeLog(Boolean.valueOf(attsMap.get("isChangeLog")).booleanValue());
				m_Table.setIsDeleteable(Boolean.valueOf(attsMap.get("isDeleteable")).booleanValue());
				m_Table.setIsHighVolume(Boolean.valueOf(attsMap.get("isHighVolume")).booleanValue());
				m_Table.setIsSecurityEnabled(Boolean.valueOf(attsMap.get("isSecurityEnabled")).booleanValue());
				m_Table.setIsView(Boolean.valueOf(attsMap.get("isView")).booleanValue());
				//m_Table.setLoadSeq(Integer.parseInt(atts.getValue("LoadSeq")));
				m_Table.setName(attsMap.get("Name"));
				m_Table.setReplicationType(attsMap.get("ReplicationType"));
				m_Table.setTableName(attsMap.get("TableName"));
//				log.info("in3");
				//attsOut.clear();          
				try {
				if (m_Table.save(m_trxName) == true){		    	
					System.out.println("m_Table.save succeeded");
					record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),AD_Backup_ID, Object_Status,"AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));           		        		
				}
				else{
					System.out.println("m_Table.save failed");
					record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),AD_Backup_ID, Object_Status,"AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
				}            
				} catch(SAXException e) {
					System.out.println("Exception in importTable: " + e.getMessage());

				}
			}		
		}

		public void importField(Map<String, String> attsMap) {
			MField m_Field = null;

			String entitytype = attsMap.get("EntityType");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String name = attsMap.get("Name");
				String tabname = attsMap.get("ADTabNameID");
				String colname = attsMap.get("ADColumnNameID");
//				log.info("Column Name ->"+colname);
//				log.info("Database Name ->"+m_DatabaseType);
				/**		
				 //Adjust for difference between Oracle and PostgreSql DataTypes
				  if (m_DatabaseType.equals("Oracle")){
				  if (colname.equals("TIMESTAMP"))
				  colname = "Date";
				  else if (colname.equals("TIMESTAMP+Time"))
				  colname = "Date+Time";
				  else if (colname.equals("NUMERIC"))
				  colname = "Number";}
				  else if (m_DatabaseType.equals("Sybase")){
				  if (colname.equals("TIMESTAMP"))
				  colname = "Date";
				  else if (colname.equals("TIMESTAMP+Time"))
				  colname = "Date+Time";
				  else if (colname.equals("NUMERIC"))
				  colname = "Number";}
				  else if (m_DatabaseType.equals("PostgreSQL")){
				  log.info("Before Column Name ->"+colname);		
				  if (colname.equals("Date"))					
				  colname = "TIMESTAMP";					
				  else if (colname.equals("Date+Time"))
				  colname = "TIMESTAMP+Time";					
				  else if (colname.equals("Number"))
				  colname = "NUMERIC";
				  System.out.println("After Column Name ->"+colname);		}
				  **/
				int tableid = get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID"));			
				int windowid = get_ID("AD_Window", attsMap.get("ADWindowNameID"));
				//log.info("Column Name ->"+colname);		
				int columnid  = get_IDWithMasterAndColumn ("AD_Column","ColumnName", colname, "AD_Table", tableid);
				//log.info("ColumnID->"+columnid);
				sqlB = new StringBuffer ("select AD_Tab_ID from AD_Tab where AD_Window_ID = " + windowid
						+ " and Name = '"+tabname +"'"
						+ " and AD_Table_ID = ?");			
				int tabid = DB.getSQLValue(m_trxName, sqlB.toString (), tableid);		
				
				if (tabid > 0 )
				{   
					
					sqlB = new StringBuffer ("select AD_Field_ID from AD_Field where AD_Column_ID = "+columnid
							+ " and AD_Tab_ID = ?");
					int id = DB.getSQLValue(m_trxName, sqlB.toString (), tabid);
					m_Field = new MField(m_ctx, id, m_trxName);
					if (id > 0){			
						AD_Backup_ID = copyRecord("AD_Field",m_Field);
						Object_Status = "Update";			
					}
					else{
						Object_Status = "New";
						AD_Backup_ID =0;
					}
					m_Field.setName(attsMap.get("Name"));
					m_Field.setAD_Column_ID(columnid);
					name = attsMap.get("ADFieldGroupNameID");	    
					id = get_IDWithColumn("AD_FieldGroup", "Name", name);
					m_Field.setAD_FieldGroup_ID(id);		    
					m_Field.setAD_Tab_ID(tabid);	        
					m_Field.setEntityType (attsMap.get("EntityType"));
					m_Field.setIsSameLine(Boolean.valueOf(attsMap.get("SameLine")).booleanValue());
					m_Field.setIsCentrallyMaintained(Boolean.valueOf(attsMap.get("isCentrallyMaintained")).booleanValue());
					m_Field.setIsDisplayed(Boolean.valueOf(attsMap.get("Displayed")).booleanValue());	        
					//m_Field.setIsEncrypted(Boolean.valueOf(atts.getValue("isEncrypted")).booleanValue());
					m_Field.setIsFieldOnly(Boolean.valueOf(attsMap.get("isFieldOnly")).booleanValue());
					m_Field.setIsHeading(Boolean.valueOf(attsMap.get("isHeading")).booleanValue());
					m_Field.setIsReadOnly(Boolean.valueOf(attsMap.get("isReadOnly")).booleanValue());	        
					m_Field.setSeqNo(Integer.parseInt(attsMap.get("SeqNo")));
					m_Field.setDisplayLength(Integer.parseInt(attsMap.get("DisplayLength")));
					m_Field.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
					m_Field.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
					m_Field.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);	        
					m_Field.setSortNo(new BigDecimal(attsMap.get("SortNo")));
					m_Field.setDisplayLogic(attsMap.get("DisplayLogic"));
					try {
					if (m_Field.save(m_trxName) == true){		    	
						System.out.println("m_Field.save succeeded");
						record_log (1, m_Field.getName(),"Field", m_Field.get_ID(),AD_Backup_ID, Object_Status,"AD_Field",get_IDWithColumn("AD_Table", "TableName", "AD_Field"));           		        		
					}
					else{
						System.out.println("m_Field.save failed");
						record_log (0, m_Field.getName(),"Field", m_Field.get_ID(),AD_Backup_ID, Object_Status,"AD_Field",get_IDWithColumn("AD_Table", "TableName", "AD_Field"));
					}
					} catch(Exception e) {
						System.out.println("Exception in m_Field.save: " + e.getMessage());

					}
				}  
				else
					System.out.println("Tab do not exist to field Name: "+ name);		
				
			}	
		}

		public void importPrintFormatItem(Map<String, String> attsMap) {
			X_AD_PrintFormatItem m_PrintFormatItem = null;
			
			String name = attsMap.get("Name");
			int id = get_IDWithMaster("AD_PrintFormatItem", name,"AD_PrintFormat",attsMap.get("ADPrintFormatNameID"));		
			
			m_PrintFormatItem = new X_AD_PrintFormatItem(m_ctx, id, m_trxName);
			if (id > 0){
				AD_Backup_ID = copyRecord("AD_PrintFormatItem",m_PrintFormatItem);
				Object_Status = "Update";			
			}
			else{
				Object_Status = "New";
				AD_Backup_ID =0;
			}
			m_PrintFormatItem.setName(name);
			name = attsMap.get("ADPrintFormatNameID");
			id = get_IDWithColumn("AD_PrintFormat", "Name", name);	    
			m_PrintFormatItem.setAD_PrintFormat_ID(id);
			//name = atts.getValue("ADPrintFormatChildNameID");
			//id = get_IDWithColumn("AD_PrintFormat", "Name", name);
			//m_PrintFormatItem.setAD_PrintFormatChild_ID(id);
			name = attsMap.get("ADTableNameID");	    
			int tableid = get_IDWithColumn("AD_Table", "TableName", name);	    
			name = attsMap.get("ADColumnNameID");
			id =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableid);
			if(id > 0)
			m_PrintFormatItem.setAD_Column_ID(id);
			name = attsMap.get("ADPrintGraphID");
			id = get_IDWithColumn("AD_PrintGraph", "Name", name);
			m_PrintFormatItem.setAD_PrintGraph_ID(id);
			name = attsMap.get("ADPrintColorID");
			id = get_IDWithColumn("AD_PrintColor", "Name", name);
			m_PrintFormatItem.setAD_PrintColor_ID(id);
			name = attsMap.get("ADPrintFontID");
			id = get_IDWithColumn("AD_PrintFont", "Name", name);
			m_PrintFormatItem.setAD_PrintFont_ID(id);
			
			m_PrintFormatItem.setPrintName(attsMap.get("PrintName"));
			m_PrintFormatItem.setName(attsMap.get("Name"));        
			m_PrintFormatItem.setPrintAreaType(attsMap.get("PrintAreaType"));
			
			m_PrintFormatItem.setSeqNo(Integer.parseInt(attsMap.get("SeqNo")));
			m_PrintFormatItem.setPrintFormatType(attsMap.get("PrintFormatType"));        
			m_PrintFormatItem.setXSpace(Integer.parseInt(attsMap.get("XSpace")));
			
			m_PrintFormatItem.setYSpace(Integer.parseInt(attsMap.get("YSpace")));
			m_PrintFormatItem.setXPosition(Integer.parseInt(attsMap.get("Xposition")));
			m_PrintFormatItem.setYPosition(Integer.parseInt(attsMap.get("Yposition")));
			
			m_PrintFormatItem.setMaxWidth(Integer.parseInt(attsMap.get("MaxWidth")));
			m_PrintFormatItem.setMaxHeight(Integer.parseInt(attsMap.get("MaxHieght")));
			m_PrintFormatItem.setSortNo(Integer.parseInt(attsMap.get("SortNo")));
			
			m_PrintFormatItem.setFieldAlignmentType(attsMap.get("FieldAlignmentType"));
			m_PrintFormatItem.setLineAlignmentType(attsMap.get("LineAlignmentType"));
			m_PrintFormatItem.setImageURL(attsMap.get("ImageURL"));
			m_PrintFormatItem.setArcDiameter(Integer.parseInt(attsMap.get("ArcDiameter")));
			m_PrintFormatItem.setLineWidth(Integer.parseInt(attsMap.get("LineWidth")));
			m_PrintFormatItem.setShapeType(attsMap.get("ShapeType"));
			
			m_PrintFormatItem.setBelowColumn(Integer.parseInt(attsMap.get("BelowColumn")));
			m_PrintFormatItem.setPrintNameSuffix(attsMap.get("PrintNameSuffix"));
			m_PrintFormatItem.setRunningTotalLines(Integer.parseInt(attsMap.get("RunningTotalLines")));
			
			m_PrintFormatItem.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
			m_PrintFormatItem.setIsPrinted(Boolean.valueOf(attsMap.get("isPrinted")).booleanValue());
			m_PrintFormatItem.setIsRelativePosition(Boolean.valueOf(attsMap.get("isRelativePosition")).booleanValue());
			m_PrintFormatItem.setIsNextLine(Boolean.valueOf(attsMap.get("isNextLine")).booleanValue());
			
			m_PrintFormatItem.setIsHeightOneLine(Boolean.valueOf(attsMap.get("isHeightOneLine")).booleanValue());
			m_PrintFormatItem.setIsOrderBy(Boolean.valueOf(attsMap.get("isOrderBy")).booleanValue());
			m_PrintFormatItem.setIsGroupBy(Boolean.valueOf(attsMap.get("isGroupBy")).booleanValue());
			
			m_PrintFormatItem.setIsPageBreak(Boolean.valueOf(attsMap.get("isPageBreak")).booleanValue());
			m_PrintFormatItem.setIsSummarized(Boolean.valueOf(attsMap.get("isSummarized")).booleanValue());
			m_PrintFormatItem.setImageIsAttached(Boolean.valueOf(attsMap.get("isImageIsAttached")).booleanValue());
			
			m_PrintFormatItem.setIsAveraged(Boolean.valueOf(attsMap.get("isAveraged")).booleanValue());
			m_PrintFormatItem.setIsCounted(Boolean.valueOf(attsMap.get("isCounted")).booleanValue());
			m_PrintFormatItem.setIsSetNLPosition(Boolean.valueOf(attsMap.get("isSetNLPosition")).booleanValue());
			m_PrintFormatItem.setIsSuppressNull(Boolean.valueOf(attsMap.get("isSuppressNull")).booleanValue());
			
			m_PrintFormatItem.setIsFixedWidth(Boolean.valueOf(attsMap.get("isFixedWidth")).booleanValue());
			m_PrintFormatItem.setIsNextPage(Boolean.valueOf(attsMap.get("isNextPage")).booleanValue());
			m_PrintFormatItem.setIsMaxCalc(Boolean.valueOf(attsMap.get("isMaxCalc")).booleanValue());
			m_PrintFormatItem.setIsMinCalc(Boolean.valueOf(attsMap.get("isMinCalc")).booleanValue());
			
			m_PrintFormatItem.setIsRunningTotal(Boolean.valueOf(attsMap.get("isRunningTotal")).booleanValue());
			m_PrintFormatItem.setIsVarianceCalc(Boolean.valueOf(attsMap.get("isVarianceCalc")).booleanValue());
			m_PrintFormatItem.setIsDeviationCalc(Boolean.valueOf(attsMap.get("isDeviationCalc")).booleanValue());

			try {
			
			if (m_PrintFormatItem.save(m_trxName) == true){		    	
				System.out.println("m_PrintFormatItem.save succeeded");
				record_log (1, m_PrintFormatItem.getName(),"PrintFormatItem", m_PrintFormatItem.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormatItem",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormatItem"));           		        		
			}
			else{
				System.out.println("m_PrintFormatItem.save failed");
				record_log (0, m_PrintFormatItem.getName(),"PrintFormatItem", m_PrintFormatItem.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormatItem",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormatItem"));
			}   
					} catch(Exception e) {
						System.out.println("Exception in m_PrintFormatItem.save: " + e.getMessage());

					}
		}		

		public void importPrintFormat(Map<String, String> attsMap) {
			X_AD_PrintFormat m_PrintFormat = null;
			MTable m_Table = null;
			
			String name = attsMap.get("Name");		
			int id = get_IDWithColumn("AD_PrintFormat", "Name", name);		
			m_PrintFormat = new X_AD_PrintFormat(m_ctx, id, m_trxName);
			if (id > 0){
				AD_Backup_ID = copyRecord("AD_PrintFormat",m_PrintFormat);
				Object_Status = "Update";			
			}
			else{
				Object_Status = "New";
				AD_Backup_ID =0;
			}
			
			name = attsMap.get("ADReportviewnameID");
			id = get_IDWithColumn("AD_ReportView", "Name", name);
			m_PrintFormat.setAD_ReportView_ID(id);	    
			name = attsMap.get("ADTableNameID");
			id = get_IDWithColumn("AD_Table", "TableName", name);
			if (id == 0 ){
				m_Table = new MTable(m_ctx, 0, m_trxName);
				m_Table.setAccessLevel("3");
				m_Table.setName(name);			    
				m_Table.setTableName(name);		    
				try {
				if (m_Table.save(m_trxName) == true){		    	
					System.out.println("m_Table.save succeeded");
					record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
				}
				else{
					System.out.println("m_Table.save failed");
					record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
				}
				} catch(Exception e) {
					System.out.println("Exception in importPrintFormat: " + e.getMessage());

				}
				id = get_IDWithColumn("AD_Table", "TableName", name);
			}	    
			m_PrintFormat.setAD_Table_ID(id);
			
			name = attsMap.get("ADPrintTableFormatID");
			id = get_IDWithColumn("AD_PrintTableFormat", "Name", name);	
			m_PrintFormat.setAD_PrintTableFormat_ID(id);	    
			name = attsMap.get("ADPrintColorID");	    
			id = get_IDWithColumn("AD_PrintColor", "Name", name);
			
			m_PrintFormat.setAD_PrintColor_ID(id);	    
			name = attsMap.get("ADPrintFontID");
			id = get_IDWithColumn("AD_PrintFont", "Name", name);
			m_PrintFormat.setAD_PrintFont_ID(id);
			
			name = attsMap.get("ADPrintPaperID");
			id = get_IDWithColumn("AD_PrintPaper", "Name", name);
			m_PrintFormat.setAD_PrintPaper_ID(id);
			
			m_PrintFormat.setDescription (attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));	    
			m_PrintFormat.setName (attsMap.get("Name"));	    
			m_PrintFormat.setPrinterName(attsMap.get("PrinterName"));	    
			m_PrintFormat.setFooterMargin(Integer.parseInt(attsMap.get("FooterMargin")));
			
			m_PrintFormat.setHeaderMargin(Integer.parseInt(attsMap.get("HeaderMargin")));	    
			m_PrintFormat.setCreateCopy(attsMap.get("CreateCopy"));	    
			m_PrintFormat.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
			
			m_PrintFormat.setIsTableBased(Boolean.valueOf(attsMap.get("isTableBased")).booleanValue());	    
			m_PrintFormat.setIsForm(Boolean.valueOf(attsMap.get("isForm")).booleanValue());	    
			m_PrintFormat.setIsStandardHeaderFooter(Boolean.valueOf(attsMap.get("isStandardHeader")).booleanValue());
			
			m_PrintFormat.setIsDefault(Boolean.valueOf(attsMap.get("isDefault")).booleanValue());	    
			try {
			if (m_PrintFormat.save(m_trxName) == true){		    	
				System.out.println("m_PrintFormat.save succeeded");
				record_log (1, m_PrintFormat.getName(),"PrintFormat", m_PrintFormat.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormat",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormat"));           		        		
			}
			else{
				System.out.println("m_PrintFormat.save failed");
				record_log (0, m_PrintFormat.getName(),"PrintFormat", m_PrintFormat.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormat",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormat"));
			}	    
			} catch(Exception e) {
				System.out.println("Exception with importPrintFormat: " + e.getMessage());

			}
		}

		public void importWorkflowNodeNext(Map<String, String> attsMap) {
			MWFNodeNext m_WFNodeNext = null;
			String entitytype = attsMap.get("EntityType");

			if (entitytype.equals("U") || entitytype.equals("D")  || entitytype.equals("A")) {

				String workflowName = attsMap.get("ADWorkflowNameID");

				int workflowId = get_IDWithColumn("AD_Workflow", "name", workflowName);

				String workflowNodeName = attsMap.get("ADWorkflowNodeNameID");
				String workflowNodeNextName = attsMap.get("ADWorkflowNodeNextNameID");
				
				System.out.println("workflowNodeNextName: " + workflowNodeNextName);

				//analyzeWorkflowImport( workflowId, workflowNodeName, m_trxName);


				sqlB = new StringBuffer ("SELECT ad_wf_node_id FROM AD_WF_Node WHERE AD_Workflow_ID=? and Name =?");		

				int wfNodeId = DB.getSQLValue(m_trxName,sqlB.toString(),workflowId,workflowNodeName);
				System.out.println("wfNodeId found: " + wfNodeId);
				if( wfNodeId < 0) {
					//wfNodeId =  createPlaceHolderWorkflowNode( atts, m_trxName);
				}

				int wfNodeNextId = DB.getSQLValue(m_trxName,sqlB.toString(),workflowId,workflowNodeNextName);

				sqlB = new StringBuffer ("SELECT  ad_wf_nodenext_id FROM AD_WF_NodeNext  WHERE ad_wf_node_id =? and ad_wf_next_id =?");		

				//int id = get_IDWithColumn("AD_WF_Node", "name", workflowNodeName);
				int id = DB.getSQLValue(m_trxName,sqlB.toString(),wfNodeId,wfNodeNextId);
				System.out.println("id used for MWFNodeNext: " + id);

				System.out.println("About to execute new MWFNodeNext");

				m_WFNodeNext = new MWFNodeNext(m_ctx, id, m_trxName);
				System.out.println("After execute of new MWFNodeNext");
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_WF_NodeNext",m_WFNodeNext);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}

				System.out.println("about to execute m_WFNodeNext.setAD_WF_Node_ID with wfNodeId: " + wfNodeId);

				m_WFNodeNext.setAD_WF_Node_ID(wfNodeId);
				m_WFNodeNext.setAD_WF_Next_ID(wfNodeNextId);
				m_WFNodeNext.setEntityType(attsMap.get("EntityType"));
				m_WFNodeNext.setSeqNo(Integer.valueOf(attsMap.get("SeqNo")));
				m_WFNodeNext.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_WFNodeNext.setIsStdUserWorkflow(attsMap.get("IsStdUserWorkflow") != null ? Boolean.valueOf(attsMap.get("IsStdUserWorkflow")).booleanValue():true);
				//attsOut.clear();          
				System.out.println("about to execute m_WFNodeNext.save");
				try {
				if (m_WFNodeNext.save(m_trxName) == true){		    	
					System.out.println("m_WFNodeNext save success");
					record_log (1, String.valueOf(m_WFNodeNext.get_ID()),"WFNodeNext", m_WFNodeNext.get_ID(),AD_Backup_ID, Object_Status,"AD_WF_NodeNext",get_IDWithColumn("AD_WF_NodeNext", "ad_wf_nodenext_id", "AD_WF_NodeNext"));           		        		
				}
				else{
					System.out.println("m_WFNodeNext save failure");
					record_log (0, String.valueOf(m_WFNodeNext.get_ID()),"WFNode", m_WFNodeNext.get_ID(),AD_Backup_ID, Object_Status,"AD_WF_NodeNext",get_IDWithColumn("AD_WF_NodeNext", "ad_wf_nodenext_id", "AD_WF_NodeNext"));
				}            
				} catch(SAXException e) {
					System.out.println("Exception in importWorkflowNodeNext: " + e.getMessage());

				}
			} 
		}

		public void importWorkflowNode(Map<String, String> attsMap) {
			String entitytype = attsMap.get("EntityType");
			MWFNode m_WFNode = null;
			
			if (entitytype.equals("U") || entitytype.equals("D") || entitytype.equals("A")) {

				String workflowName = attsMap.get("ADWorkflowNameID");

				int workflowId = get_IDWithColumn("AD_Workflow", "name", workflowName);

				String workflowNodeName = attsMap.get("Name");
				System.out.println("workflowNodeName: " + workflowNodeName);

				sqlB = new StringBuffer ("SELECT ad_wf_node_id FROM AD_WF_Node WHERE AD_Workflow_ID=? and Name =?");		

				int id = DB.getSQLValue(m_trxName,sqlB.toString(),workflowId,workflowNodeName);

				m_WFNode = new MWFNode(m_ctx, id, m_trxName);
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_WF_Node",m_WFNode);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				m_WFNode.setName(workflowNodeName);

				String Name = attsMap.get("ADWorkflowNameID");	    
				id = get_IDWithColumn("AD_Workflow", "Name", Name);
				m_WFNode.setAD_Workflow_ID(id);
				m_WFNode.setAction(attsMap.get("Action"));
				if (attsMap.get("ADProcessNameID")!= null){
					Name = attsMap.get("ADProcessNameID");	    
					id = get_IDWithColumn("AD_Process", "Name", Name);
					if(id <= 0 && attsMap.get("Action").equals(X_AD_WF_Node.ACTION_AppsProcess) || attsMap.get("Action").equals(X_AD_WF_Node.ACTION_AppsReport)) {
						//id =  createPlaceHolderProcess(atts, m_trxName);
					}
					m_WFNode.setAD_Process_ID(id);		    
				}
				if (attsMap.get("ADFormNameID")!= null){
					String name = attsMap.get("ADFormNameID");	    
					System.out.println("ADFormNameID: " + name);
					id = get_IDWithColumn("AD_Form", "Name", name);
					System.out.println("AD_Form_id: " + id);
					if(id <= 0 && attsMap.get("Action").equals(X_AD_WF_Node.ACTION_UserForm)) {
						//id =  createPlaceHolderForm(atts, m_trxName);
					}
					System.out.println("ADFormNameID: " + id);
					m_WFNode.setAD_Form_ID(id);   
				}

				if (attsMap.get("ADWorkflowResponsibleNameID")!= null){
					String name = attsMap.get("ADWorkflowResponsibleNameID");	    
					id = get_IDWithColumn("AD_WF_Responsible", "Name", name);
					m_WFNode.setAD_WF_Responsible_ID(id);   
				}

				if (attsMap.get("ADWindowNameID")!= null){
					String name = attsMap.get("ADWindowNameID");	    
					id = get_IDWithColumn("AD_Window", "Name", name);
					if(id <= 0 && attsMap.get("Action").equals(X_AD_WF_Node.ACTION_UserWindow)) {
						//id =  createPlaceHolderWindow(atts, m_trxName);
					}
					System.out.println("ADWindowNameID: " + id);
					m_WFNode.setAD_Window_ID(id);   
				}
				if (attsMap.get("ADImageNameID")!= null){
					String name = attsMap.get("ADImageNameID");	    
					id = get_IDWithColumn("AD_Image", "Name", name);
					m_WFNode.setAD_Image_ID(id);   
				}
				if (attsMap.get("ADWorkflowBlockNameID")!= null){
					String name = attsMap.get("ADWorkflowBlockNameID");	    
					id = get_IDWithColumn("AD_WF_Block", "Name", name);
					m_WFNode.setAD_WF_Block_ID(id);   
				}
				/* FIXME:  Do we need TaskName ?
			if (attsMap.get("ADTaskNameID")!=null){
				String name = atts.getValue("ADTaskNameID");		
				sqlB = new StringBuffer ("SELECT AD_Task_ID FROM AD_Task WHERE Name= ?");
				taskid = DB.getSQLValue(m_trxName,sqlB.toString(),name);
			}
				 */
				//FIXME: manually set
				m_WFNode.setLimit(-1);

				m_WFNode.setEntityType(attsMap.get("EntityType"));
				m_WFNode.setDocAction(attsMap.get("DocAction"));
				m_WFNode.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_WFNode.setJoinElement(attsMap.get("JoinElement"));
				m_WFNode.setSplitElement(attsMap.get("SplitElement"));
				m_WFNode.setXPosition(Integer.valueOf(attsMap.get("XPosition")));
				m_WFNode.setYPosition(Integer.valueOf(attsMap.get("YPosition")));
				m_WFNode.setWaitingTime(Integer.valueOf(attsMap.get("WaitingTime")));
				m_WFNode.setWaitTime(Integer.valueOf(attsMap.get("WaitTime")));
				m_WFNode.setWorkingTime(Integer.valueOf(attsMap.get("WorkingTime")));
				m_WFNode.setCost(new BigDecimal(attsMap.get("Cost")));
				m_WFNode.setDuration(Integer.valueOf(attsMap.get("Duration")));
				m_WFNode.setPriority(Integer.valueOf(attsMap.get("Priority")));
				//FIXME:  Failing for some reason on a ""
				//m_WFNode.setStartMode(atts.getValue("StartMode"));
				//FIXME:  Failing for some reason on a ""
				//m_WFNode.setSubflowExecution(atts.getValue("SubflowExecution"));
				m_WFNode.setIsCentrallyMaintained(Boolean.valueOf(attsMap.get("IsCentrallyMaintained")).booleanValue());
				m_WFNode.setDynPriorityChange(new BigDecimal(attsMap.get("DynPriorityChange")));
				//m_WFNode.setAccessLevel (atts.getValue("AccessLevel"));		    
				//FIXME:  Failing for some reason on a ""
				//m_WFNode.setDynPriorityUnit (atts.getValue("DynPriorityUnit"));		    
				m_WFNode.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				//attsOut.clear();          
				System.out.println("about to execute m_WFNode.save");
				try {
				if (m_WFNode.save(m_trxName) == true){		    	
					System.out.println("m_WFNode save success");
					record_log (1, m_WFNode.getName(),"WFNode", m_WFNode.get_ID(),AD_Backup_ID, Object_Status,"AD_WF_Node",get_IDWithColumn("AD_WF_Node", "Name", "AD_WF_Node"));           		        		
				}
				else{
					System.out.println("m_WFNode save failure");
					record_log (0, m_WFNode.getName(),"WFNode", m_WFNode.get_ID(),AD_Backup_ID, Object_Status,"AD_WF_Node",get_IDWithColumn("AD_WF_Node", "Name", "AD_WF_Node"));
				}            
				} catch(SAXException e) {
					System.out.println("Exception in importWorkflowNode: " + e.getMessage());

				}

			}		
		}

		public void importWorkflow(Map<String, String> attsMap) {
			MWorkflow m_Workflow = null;
			String entitytype = attsMap.get("EntityType");
			System.out.println("entitytype "+attsMap.get("EntityType"));

			if (entitytype.equals("U") || entitytype.equals("D")) {
				System.out.println("entitytype is a U or D");

				String workflowName = attsMap.get("Name");

				int id = get_IDWithColumn("AD_Workflow", "name", workflowName);

				m_Workflow = new MWorkflow(m_ctx, id, m_trxName);
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_Workflow",m_Workflow);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}

				if (attsMap.get("ADWorkflowResponsibleNameID")!= null){
					String name = attsMap.get("ADWorkflowResponsibleNameID");	    
					id = get_IDWithColumn("AD_WF_Responsible", "Name", name);
					m_Workflow.setAD_WF_Responsible_ID(id);   
				}
				if (attsMap.get("ADTableNameID")!= null){
					String Name = attsMap.get("ADTableNameID");	    	
					id = get_IDWithColumn("AD_Table", "TableName", Name);
					m_Workflow.setAD_Table_ID(id);

				}
				if (attsMap.get("ADWorkflowProcessorNameID")!= null){
					String Name = attsMap.get("ADWorkflowProcessorNameID");	    	
					id = get_IDWithColumn("AD_WorkflowProcessor", "Name", Name);
					m_Workflow.setAD_WorkflowProcessor_ID(id);

				}
				m_Workflow.setName(workflowName);
				m_Workflow.setAccessLevel (attsMap.get("AccessLevel"));		    
				m_Workflow.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Workflow.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Workflow.setDurationUnit(attsMap.get("DurationUnit"));
				m_Workflow.setAuthor(attsMap.get("Author"));
				m_Workflow.setVersion(Integer.valueOf(attsMap.get("Version")));
				m_Workflow.setPriority(Integer.valueOf(attsMap.get("Priority")));
				m_Workflow.setLimit(Integer.valueOf(attsMap.get("Limit")));
				m_Workflow.setDuration(Integer.valueOf(attsMap.get("Duration")));
				m_Workflow.setCost(Integer.valueOf(attsMap.get("Cost")));
				m_Workflow.setWorkingTime(Integer.valueOf(attsMap.get("WorkingTime")));
				m_Workflow.setWaitingTime(Integer.valueOf(attsMap.get("WaitingTime")));
				m_Workflow.setPublishStatus(attsMap.get("PublishStatus"));
				m_Workflow.setWorkflowType(attsMap.get("WorkflowType"));
				m_Workflow.setDocValueLogic(attsMap.get("DocValueLogic"));
				//FIXME:  not in packout.xml
				//m_Workflow.setIsReadwrite(true);

				m_Workflow.setIsValid(attsMap.get("isValid") != null ? Boolean.valueOf(attsMap.get("isValid")).booleanValue():true);
				m_Workflow.setEntityType(attsMap.get("EntityType"));
				m_Workflow.setAD_WF_Node_ID(-1);
				//attsOut.clear();          
				System.out.println("about to execute m_Workflow.save");
				try {
				if (m_Workflow.save(m_trxName) == true){		    	
					System.out.println("m_Workflow.save succeeded");
					record_log (1, m_Workflow.getName(),"Workflow", m_Workflow.get_ID(),AD_Backup_ID, Object_Status,"AD_Workflow",get_IDWithColumn("AD_Workflow", "Name", "AD_Workflow"));           		        		
				}
				else{
					System.out.println("m_Workflow save failure");
					record_log (0, m_Workflow.getName(),"Workflow", m_Workflow.get_ID(),AD_Backup_ID, Object_Status,"AD_Workflow",get_IDWithColumn("AD_Workflow", "Name", "AD_Workflow"));
				}            
				} catch(SAXException e) {
					System.out.println("Exception in importWorkflow: " + e.getMessage());

				}
		}
		}

		public void importProcess(Map<String, String> attsMap) {
			MProcess m_Process = null;
			int id = 0;
			String entitytype = attsMap.get("EntityType");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String name = attsMap.get("Name");
				
				
				// Get New process.
				id=get_ID("AD_Process", name);				
				
				if (id > 0){
					m_Process = new MProcess(m_ctx, id, m_trxName);				
					AD_Backup_ID = copyRecord("AD_Process",m_Process);
					Object_Status = "Update";			
				}
				else{				
					m_Process = new MProcess(m_ctx, id, m_trxName);
					id = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Process", m_trxName);
					m_Process.setAD_Process_ID(id);				
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				m_Process.setName(name);
				name = attsMap.get("ADWorkflowNameID");	    
				id = get_IDWithColumn("AD_Workflow", "Name", name);
				
				m_Process.setAD_Workflow_ID(id);
				name = attsMap.get("ADProcessNameID");		    
				
				name = attsMap.get("ADPrintFormatNameID");	    
				id = get_IDWithColumn("AD_PrintFormat", "Name", name);
				m_Process.setAD_PrintFormat_ID(id);    
				name = attsMap.get("ADReportViewNameID");	    
				id = get_IDWithColumn("AD_ReportView", "Name", name);
				m_Process.setAD_ReportView_ID(id); 
				m_Process.setAccessLevel(attsMap.get("AccessLevel"));
				m_Process.setClassname(attsMap.get("Classname"));			
				m_Process.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Process.setEntityType(attsMap.get("EntityType"));
				m_Process.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Process.setIsBetaFunctionality(Boolean.valueOf(attsMap.get("isBetaFunctionality")).booleanValue());
				m_Process.setIsDirectPrint(Boolean.valueOf(attsMap.get("isDirectPrint")).booleanValue());
				m_Process.setIsReport(Boolean.valueOf(attsMap.get("isReport")).booleanValue());
				m_Process.setName(attsMap.get("Name"));
				
				m_Process.setProcedureName(attsMap.get("ProcedureName"));
				m_Process.setStatistic_Count(0);
				m_Process.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Process.setStatistic_Seconds(0);
				m_Process.setValue(attsMap.get("Value"));
				m_Process.setWorkflowValue(attsMap.get("WorkflowValue"));			
				try {
				if (m_Process.save(m_trxName) == true){		    	
					System.out.println("m_Process.save succeeded");
					record_log (1, m_Process.getName(),"Process", m_Process.get_ID(),AD_Backup_ID, Object_Status,"AD_Process",get_IDWithColumn("AD_Table", "TableName", "AD_Process"));           		        		
				}
				else{
					System.out.println("m_Process.save failed");
					record_log (0, m_Process.getName(),"Process", m_Process.get_ID(),AD_Backup_ID, Object_Status,"AD_Process",get_IDWithColumn("AD_Table", "TableName", "AD_Process"));
				}			
				} catch(SAXException e) {
					System.out.println("Exception in importProcess: " + e.getMessage());

				}
			}
		}

		public void importMenu(Map<String, String> attsMap) {
			X_AD_Menu m_Menu = null;
			int idDetail =0;

			String entitytype = attsMap.get("EntityType");
			//if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0) {
    			String name =  attsMap.get("ADMenuNameID");		    
    			int menuid = get_IDWithColumn("AD_Menu", "Name",name);
    			m_Menu = new X_AD_Menu(m_ctx, menuid, m_trxName);    	
    			if (menuid > 0){    		
    				AD_Backup_ID = copyRecord("AD_Menu",m_Menu);
				Object_Status = "Update";			
	    		}
	   		else{
				Object_Status = "New";
				AD_Backup_ID =0;
	    		}
    	
    			m_Menu.setName(name);
	    		int id = get_IDWithColumn("AD_Window", "Name", attsMap.get("ADWindowNameID"));
	    		m_Menu.setAD_Window_ID(id);
	    		id = get_IDWithColumn("AD_Process", "Name", attsMap.get("ADProcessNameID"));
	    		m_Menu.setAD_Process_ID(id);
	    		id = get_IDWithColumn("AD_Form", "Name", attsMap.get("ADFormNameID"));
	    		m_Menu.setAD_Form_ID(id);
	    		id = get_IDWithColumn("AD_Task", "Name", attsMap.get("ADTaskNameID"));
	    		m_Menu.setAD_Task_ID(id);

	    		id = get_IDWithColumn("AD_Workbench", "Name", attsMap.get("ADWorkbenchNameID"));
	    		m_Menu.setAD_Workbench_ID(id);  
	    		id = get_IDWithColumn("AD_Workflow", "Name", attsMap.get("ADWorkflowNameID"));
	    		m_Menu.setAD_Workflow_ID(id);    

			String m_Action = (attsMap.get("Action") != null ? attsMap.get("Action") : " ");
        		if (m_Action.compareTo(" ") > -1 )
        			m_Menu.setAction(m_Action);

        		m_Menu.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
        		m_Menu.setEntityType(attsMap.get("EntityType"));
        		m_Menu.setIsReadOnly(Boolean.valueOf(attsMap.get("isReadOnly")).booleanValue());
        		m_Menu.setIsSOTrx(Boolean.valueOf(attsMap.get("isSOTrx")).booleanValue());
        		m_Menu.setIsSummary(Boolean.valueOf(attsMap.get("isSummary")).booleanValue());
        		m_Menu.setIsActive(Boolean.valueOf(attsMap.get("isActive")).booleanValue());

        		if (m_Menu.save(m_trxName) == true){
	    			try {				
					System.out.println("m_Menu.save succeeded");
	    				idDetail = record_log (1, m_Menu.getName(),"Menu", m_Menu.get_ID(),AD_Backup_ID, Object_Status,"AD_Menu",get_IDWithColumn("AD_Table", "TableName", "AD_Menu"));
				} catch (SAXException e) {
					System.out.println ("setmenu:"+e);
				}           		        		
        		}	
        		else{
        			try {
					System.out.println("m_Menu.save failed");
        				idDetail = record_log (0, m_Menu.getName(),"Menu", m_Menu.get_ID(),AD_Backup_ID, Object_Status,"AD_Menu",get_IDWithColumn("AD_Table", "TableName", "AD_Menu"));
				} catch (SAXException e) {
					System.out.println("setmenu:"+e);
				}
        		}

        name = attsMap.get("ADParentMenuNameID");
        id = get_ID("AD_Menu", name);
        
        String sql2 = "SELECT count(Parent_ID) FROM AD_TREENODEMM WHERE AD_Tree_ID = 10"
		    	  + " AND Node_ID = " + menuid;
		int countRecords = DB.getSQLValue(m_trxName,sql2);		
		if (countRecords>0){			
			StringBuffer sqlC = new StringBuffer ("select * from AD_TREENODEMM where AD_Tree_ID = 10 and "
				   +" Node_ID =?");			
			try {
			    PreparedStatement pstmt1 = DB.prepareStatement(sqlC.toString(), m_trxName);
			    pstmt1.setInt(1,  menuid);			    
			    ResultSet rs1 = pstmt1.executeQuery();	    
			    if (rs1.next()){			    							
			    	
						String colValue=null;
							ResultSetMetaData meta = rs1.getMetaData(); 
							int columns = meta.getColumnCount();
							int tableID = get_IDWithColumn("AD_Table", "TableName", "AD_TreeNodeMM");
						    
						    for (int q = 1; q <= columns; q++){
						    	
						    	String col_Name = meta.getColumnName(q);
						    	StringBuffer sql = new StringBuffer ("SELECT AD_Column_ID FROM AD_column WHERE Upper(ColumnName) = '"+col_Name+"' AND AD_Table_ID = ?");
					    		int columnID = DB.getSQLValue(m_trxName, sql.toString(),tableID);
						    	sql = new StringBuffer ("SELECT AD_Reference_ID FROM AD_COLUMN WHERE AD_Column_ID = '"+columnID+"'");
					    		int referenceID = DB.getSQLValue(m_trxName,sql.toString());
					    		int idBackup = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Package_Imp_Backup", m_trxName);
					    		if (referenceID == 20|| referenceID == 28)
					    			if (rs1.getObject(q).equals("Y"))
					    				colValue = "true";
					    			else
					    				colValue = "false";
					    		else
					    			colValue = rs1.getObject(q).toString();
					    		
								    StringBuffer sqlD = new StringBuffer ("Insert INTO AD_Package_Imp_Backup" 
				    				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
				    				+   "AD_PACKAGE_IMP_BACKUP_ID, AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID," 
				    				+	" AD_TABLE_ID, AD_COLUMN_ID, AD_REFERENCE_ID, COLVALUE)"
				    				+	"VALUES("
				    				+	" "+ Env.getAD_Client_ID(m_ctx)
				    				+	", "+ Env.getAD_Org_ID(m_ctx)
				    				+	", "+ Env.getAD_User_ID(m_ctx)
				    				+	", "+ Env.getAD_User_ID(m_ctx)
									+	", " + idBackup
									+	", " + idDetail
				    				+	", " + AD_Package_Imp_ID
				    				+	", " + tableID
				    				+	", " + columnID
				    				+	", " + referenceID
				    				+	", '" +colValue									
				    				+"')");
								    int no = DB.executeUpdate (sqlD.toString(), m_trxName);
								    if (no == -1)
										System.out.println("Insert to import backup failed");
						    }
			
			    }
			    rs1.close();
			    pstmt1.close();
			    pstmt1 = null;
			
			}
			catch (Exception e) {
			    System.out.println ("get_IDWithMasterID:"+e);
			}
			
	    	
			sqlB = new StringBuffer ("UPDATE AD_TREENODEMM "
		  	      	  + "SET Parent_ID = " + id 
			      	  + " , SeqNo = " +  attsMap.get("ADParentSeqno")
			    	  + " WHERE AD_Tree_ID = 10"
			    	  + " AND Node_ID = " + m_Menu.getAD_Menu_ID());
		} else {
			sqlB = new StringBuffer ("Insert INTO AD_TREENODEMM"
	        		+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+	"Parent_ID, SeqNo, AD_Tree_ID, Node_ID)"
					+	"VALUES(0, 0, 0, 0, "
					+	id + ","+ attsMap.get("ADParentSeqno")+", 10, "+m_Menu.getAD_Menu_ID()+")");        
		}		
	     DB.executeUpdate(sqlB.toString(), m_trxName);	      
			
			//}  
		}

		public void importReferenceTable(Map<String, String> attsMap) {
			MTable m_Table = null;
			MColumn m_Column = null;
			
			String entitytype = attsMap.get("EntityType");		
			String name = attsMap.get("ADRefenceNameID");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				sqlB = new StringBuffer ("SELECT AD_Reference_ID FROM AD_Reference WHERE Name= ?");
				int id = DB.getSQLValue(m_trxName,sqlB.toString(),name);			
				sqlB = new StringBuffer ("SELECT Count(*) FROM AD_Ref_Table WHERE AD_Reference_ID= ?");
				int count = DB.getSQLValue(m_trxName, sqlB.toString(),id);	   	
				int tableId = get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID"));
				if (tableId ==0){
					m_Table = new MTable(m_ctx, 0, m_trxName);
					m_Table.setAccessLevel("3");
					m_Table.setName(attsMap.get("ADTableNameID"));			    
					m_Table.setTableName(attsMap.get("ADTableNameID"));		    
					try {
					if (m_Table.save(m_trxName) == true){		    	
						record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
					}
					else{
						record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
					}
					} catch(SAXException e) {
					System.out.println("Exception in importReferenceTable: " + e.getMessage());

				}
					tableId = get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID"));
				}
				name = attsMap.get("ADDisplay"); 
				int DisplayId =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);	    
				if (DisplayId ==0){
					m_Column = new MColumn(m_ctx,0,m_trxName);
					m_Column.setAD_Table_ID(tableId);
					// m_Column.setVersion(new BigDecimal("1")); // use constructor value
					m_Column.setColumnName(name);		    	
					m_Column.setName(name);
					m_Column.setAD_Reference_ID(30);
					try {
					if (m_Column.save(m_trxName) == true){		    	
						record_log (1, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
					}
					else{
						record_log (0, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
					}
					} catch(SAXException e) {
					System.out.println("Exception in importReferenceTable: " + e.getMessage());

				}
				}
				name = attsMap.get("Key");	    
				int keyId =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);
				if (keyId ==0){
					m_Column = new MColumn(m_ctx,0,m_trxName);
					m_Column.setAD_Table_ID(tableId);
					//m_Column.setVersion(new BigDecimal("1")); // use constructor value
					m_Column.setColumnName(name);
					m_Column.setName(name);
					m_Column.setAD_Reference_ID(30);
					try {
					if (m_Column.save(m_trxName) == true){
						record_log (1, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));           		        		
					}
					else{
						record_log (0, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
					}	    	
					} catch(SAXException e) {
					System.out.println("Exception in importReferenceTable: " + e.getMessage());

				}
				}
				
				name = attsMap.get("ADDisplay");	    
				DisplayId = get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);
				name = attsMap.get("Key");	    
				keyId = get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);
				String entityType = attsMap.get("EntityType");
				String isValueDisplayed = attsMap.get("IsValueDisplayed");		    
				String OrderByClause= attsMap.get("OrderByClause").replaceAll("'","''").replaceAll(",","");	
				String WhereClause= attsMap.get("WhereClause").replaceAll("'","''").replaceAll(",","");
				if (count >0 ){				
					sqlB = new StringBuffer ("UPDATE AD_Ref_Table "
							+ "SET AD_Table_ID = " + tableId
							+ ", AD_Display = " + DisplayId
							+ ", AD_Key = " + keyId
							+ ", isValueDisplayed = '" + isValueDisplayed
							+ "', OrderByClause = '" + OrderByClause
							+ "', EntityType ='" + entityType						
							+ "', WhereClause = '" + WhereClause
							+ "' WHERE AD_Reference_ID = " + id);					
					
					int no = DB.executeUpdate (sqlB.toString(), m_trxName);
					try {
					if (no > 0){			    	
						record_log (1, attsMap.get("ADRefenceNameID"),"Reference Table", id,0, "Update","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));
					}
					else{
						record_log (0, attsMap.get("ADRefenceNameID"),"Reference Table", id,0, "Update","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));
					}
					} catch(SAXException e) {
					System.out.println("Exception in importReferenceTable: " + e.getMessage());

				}
					
				}
				else{			
					sqlB = new StringBuffer ("Insert INTO AD_Ref_Table" 
							+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
							+   "AD_Reference_ID, AD_Table_ID, AD_Display, AD_Key " 
							+   ",entityType, isValueDisplayed, OrderByClause, "
							+   " WhereClause )"
							+	"VALUES(0, 0, 0, 0, "+id 
							+	", " + tableId
							+	", " + DisplayId
							+	", " + keyId
							+	", '" + entityType
							+	"', '" + isValueDisplayed
							+	"', '" +  OrderByClause  
							+	"', '" + WhereClause +"')");
					
					int no = DB.executeUpdate (sqlB.toString(), m_trxName);
					try {
					if (no > 0){
						record_log (1, attsMap.get("ADRefenceNameID"),"Reference Table", id,0, "New","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));		    	           		        		
					}
					else{
						record_log (0, attsMap.get("ADRefenceNameID"),"Reference Table", id,0, "New","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));
					}
					} catch(SAXException e) {
					System.out.println("Exception in importReferenceTable: " + e.getMessage());

				}
				}	 	
			}
		}

		public void importProcessPara(Map<String, String> attsMap) {
			
			String entitytype = attsMap.get("EntityType");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String name = attsMap.get("Name");
				
				int id = get_IDWithMaster("AD_Process_Para", name, "AD_Process", attsMap.get("ADProcessNameID"));
				X_AD_Process_Para m_Process_para = new X_AD_Process_Para(m_ctx, id, m_trxName);
				if (id>0){				
					AD_Backup_ID = copyRecord("AD_Process_Para",m_Process_para);
					Object_Status = "Update";				
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				m_Process_para.setName(attsMap.get("Name"));
				name = attsMap.get("ADProcessNameID");
				id = get_IDWithColumn("AD_Process", "Name", name);
				m_Process_para.setAD_Process_ID(id);
				name = attsMap.get("ADElementNameID");
				id = get_IDWithColumn("AD_Element", "Name", name);
				m_Process_para.setAD_Element_ID(id);
				name = attsMap.get("ADReferenceNameID");
				/**		    
				 //Adjust for difference between Oracle and PostgreSql DataTypes
				  if (m_DatabaseType.equals("Oracle")){
				  if (name.equals("TIMESTAMP"))
				  name = "Date";
				  else if (name.equals("TIMESTAMP+Time"))
				  name = "Date+Time";
				  else if (name.equals("NUMERIC"))
				  name = "Number";}
				  else if (m_DatabaseType.equals("Sybase")){
				  if (name.equals("TIMESTAMP"))
				  name = "Date";
				  else if (name.equals("TIMESTAMP+Time"))
				  name = "Date+Time";
				  else if (name.equals("NUMERIC"))
				  name = "Number";}
				  else if (m_DatabaseType.equals("PostgreSQL")){
				  if (name.equals("Date"))					
				  name = "TIMESTAMP";					
				  else if (name.equals("Date+Time"))
				  name = "TIMESTAMP+Time";					
				  else if (name.equals("Number"))
				  name = "NUMERIC";}
				  **/
				id = get_IDWithColumn("AD_Reference", "Name", name);
				m_Process_para.setAD_Reference_ID(id);
				name = attsMap.get("ADReferenceValueNameID");
				id = get_IDWithColumn("AD_Reference", "Name", name);
				m_Process_para.setAD_Reference_Value_ID(id);
				name = attsMap.get("ADValRuleNameID");
				id = get_IDWithColumn("AD_Val_Rule", "Name", name);
				m_Process_para.setAD_Val_Rule_ID(id);
				m_Process_para.setColumnName(attsMap.get("ColumnName"));
				m_Process_para.setDefaultValue(attsMap.get("DefaultValue"));
				m_Process_para.setDefaultValue2(attsMap.get("DefaultValue2"));
				m_Process_para.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Process_para.setEntityType(attsMap.get("EntityType"));
				m_Process_para.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Process_para.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Process_para.setName(attsMap.get("Name"));
				m_Process_para.setVFormat(attsMap.get("VFormat"));
				m_Process_para.setValueMax(attsMap.get("ValueMax"));
				m_Process_para.setValueMin(attsMap.get("ValueMin"));
				m_Process_para.setSeqNo(Integer.parseInt(attsMap.get("SeqNo")));
				m_Process_para.setFieldLength(Integer.parseInt(attsMap.get("FieldLength")));
				m_Process_para.setIsCentrallyMaintained(Boolean.valueOf(attsMap.get("isCentrallyMaintained")).booleanValue());
				m_Process_para.setIsMandatory(Boolean.valueOf(attsMap.get("isMandatory")).booleanValue());
				m_Process_para.setIsRange(Boolean.valueOf(attsMap.get("isRange")).booleanValue());
				try {
				if (m_Process_para.save(m_trxName) == true){		    	
					System.out.println("m_Process_para.save succeeded");
					record_log (1, m_Process_para.getName(),"Process_para", m_Process_para.get_ID(),AD_Backup_ID, Object_Status,"AD_Process_para",get_IDWithColumn("AD_Table", "TableName", "AD_Process_para"));           		        		
				}
				else{
					System.out.println("m_Process_para.save failed");
					record_log (0, m_Process_para.getName(),"Process_para", m_Process_para.get_ID(),AD_Backup_ID, Object_Status,"AD_Process_para",get_IDWithColumn("AD_Table", "TableName", "AD_Process_para"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importProcessPara: " + e.getMessage());

				}
			}
		}

		public void importPreference(Map<String, String> attsMap) {
			MPreference m_Preference = null;
			//TODO Add User_ID
			int windowid = get_ID("AD_Window", attsMap.get("ADWindowNameID"));		
			sqlB = new StringBuffer ("select AD_Preference_ID from AD_Preference where "
					+ " Attribute = '"+attsMap.get("Attribute") +"'"
					+ " and AD_Window_ID = ?");
			int id = DB.getSQLValue(m_trxName, sqlB.toString (), windowid);
			m_Preference = new MPreference(m_ctx, id, m_trxName);
			if (id > 0){			
				AD_Backup_ID = copyRecord("AD_Preference",m_Preference);
				Object_Status = "Update";
			}
			else{
				Object_Status = "New";
				AD_Backup_ID =0;
			}
			sqlB = null;
			m_Preference.setAD_Window_ID(windowid);
			m_Preference.setAttribute(attsMap.get("Attribute"));
			m_Preference.setValue(attsMap.get("Value"));
			try {
			if (m_Preference.save(m_trxName) == true){		    	
				System.out.println("m_Preference.save succeeded");
				record_log (1, m_Preference.getAttribute(),"Preference", m_Preference.get_ID(),AD_Backup_ID, Object_Status,"AD_Preference",get_IDWithColumn("AD_Table", "TableName", "AD_Preference"));           		        		
			}
			else{
				System.out.println("m_Preference.save failed");
				record_log (0, m_Preference.getAttribute(),"Preference", m_Preference.get_ID(),AD_Backup_ID, Object_Status,"AD_Preference",get_IDWithColumn("AD_Table", "TableName", "AD_Preference"));
			}
				} catch(SAXException e) {
					System.out.println("Exception in importPreference: " + e.getMessage());

				}
		}

		public void importTab(Map<String, String> attsMap) {
			MTab m_Tab = null;
			String entitytype = attsMap.get("EntityType");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				
				String name = attsMap.get("ADTabNameID");
				int tableid = get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID"));
				int windowid = get_ID("AD_Window", attsMap.get("ADWindowNameID"));
				sqlB = new StringBuffer ("select AD_Tab_ID from AD_Tab where AD_Window_ID = " + windowid
						+ " and Name = '"+name +"'"
						+ " and AD_Table_ID = ?");
				
				int id = DB.getSQLValue(m_trxName, sqlB.toString (), tableid);
				m_Tab = new MTab(m_ctx, id, m_trxName);
				if (id > 0){			
					AD_Backup_ID = copyRecord("AD_Tab",m_Tab);
					Object_Status = "Update";
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				sqlB = null;
				m_Tab.setName(name);	
				id = 0;
				if (attsMap.get("ADColumnSortYesNoNameID")!= null){
					name = attsMap.get("ADColumnSortYesNoNameID");	    
					id = get_IDWithColumn("AD_Column", "Name", name);
					m_Tab.setAD_ColumnSortYesNo_ID(id);
				}
				if (attsMap.get("ADColumnSortOrderNameID")!= null){
					name = attsMap.get("ADColumnSortOrderNameID");	    
					id = get_IDWithColumn("AD_Column", "Name", name);
					m_Tab.setAD_ColumnSortOrder_ID(id);
				}
				if (attsMap.get("ADImageNameID")!= null){
					name = attsMap.get("ADImageNameID");	    
					id = get_IDWithColumn("AD_Image", "Name", name);
					m_Tab.setAD_Image_ID(id);
				}
				if (attsMap.get("ADProcessNameID")!= null){
					name = attsMap.get("ADProcessNameID");	    
					id = get_IDWithColumn("AD_Process", "Name", name);
					m_Tab.setAD_Process_ID(id);
				}		    
				if (attsMap.get("ADTableNameID")!= null){
					name = attsMap.get("ADTableNameID");	    
					id = get_IDWithColumn("AD_Table", "TableName", name);
					m_Tab.setAD_Table_ID(id);   
				}
				if (attsMap.get("ADColumnNameID")!= null){
					name = attsMap.get("ADColumnNameID");
					id  = get_IDWithMasterAndColumn ("AD_Column","Name", attsMap.get("ADColumnNameID"), "AD_Table", get_IDWithColumn("AD_Table", "TableName", attsMap.get("ADTableNameID")));			    
					m_Tab.setAD_Column_ID(id);   
				}
				if (attsMap.get("ADWindowNameID")!= null){
					name = attsMap.get("ADWindowNameID");	    
					id = get_IDWithColumn("AD_Window", "Name", name);
					m_Tab.setAD_Window_ID(id);   
				}
				if (attsMap.get("IncludedTabNameID")!= null){
					name = attsMap.get("IncludedTabNameID");	    
					id = get_IDWithColumn("AD_Tab", "Name", name);
					m_Tab.setIncluded_Tab_ID(id);		        
				}
				m_Tab.setCommitWarning(attsMap.get("CommitWarning"));
				m_Tab.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Tab.setEntityType (attsMap.get("EntityType"));
				m_Tab.setHasTree(Boolean.valueOf(attsMap.get("isHasTree")).booleanValue());
				m_Tab.setHelp (attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Tab.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Tab.setImportFields (attsMap.get("ImportFields"));
				m_Tab.setIsInfoTab (Boolean.valueOf(attsMap.get("isInfoTab")).booleanValue());
				m_Tab.setIsReadOnly (Boolean.valueOf(attsMap.get("isReadOnly")).booleanValue());
				m_Tab.setIsSingleRow (Boolean.valueOf(attsMap.get("isSingleRow")).booleanValue());
				m_Tab.setIsSortTab (Boolean.valueOf(attsMap.get("isSortTab")).booleanValue());
				m_Tab.setIsTranslationTab (Boolean.valueOf(attsMap.get("IsTranslationTab")).booleanValue());
				m_Tab.setName (attsMap.get("Name"));
				m_Tab.setOrderByClause (attsMap.get("OrderByClause"));
				m_Tab.setProcessing(false);
				m_Tab.setSeqNo (Integer.parseInt(attsMap.get("SeqNo")));
				m_Tab.setTabLevel (Integer.parseInt(attsMap.get("TabLevel")));
				m_Tab.setWhereClause (attsMap.get("WhereClause"));
				if (attsMap.get("ReadOnlyLogic") != null) {
					m_Tab.setReadOnlyLogic(attsMap.get("ReadOnlyLogic"));
				}
				if (attsMap.get("DisplayLogic") != null) {
					m_Tab.setDisplayLogic(attsMap.get("DisplayLogic"));
				}
				if (attsMap.get("isInsertRecord") != null) {
					m_Tab.setIsInsertRecord(Boolean.valueOf(attsMap.get("isInsertRecord")).booleanValue());
				}
				if (attsMap.get("isAdvancedTab") != null) {
					m_Tab.setIsAdvancedTab(Boolean.valueOf(attsMap.get("isAdvancedTab")).booleanValue());
				}

				try {
				if (m_Tab.save(m_trxName) == true){		    	
					System.out.println("m_Tab.save succeeded");
					record_log (1, m_Tab.getName(),"Tab", m_Tab.get_ID(),AD_Backup_ID, Object_Status,"AD_Tab",get_IDWithColumn("AD_Table", "TableName", "AD_Tab"));           		        		
				} else {
					System.out.println("m_Tab.save failed");
					record_log (0, m_Tab.getName(),"Tab", m_Tab.get_ID(),AD_Backup_ID, Object_Status,"AD_Tab",get_IDWithColumn("AD_Table", "TableName", "AD_Tab"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importTab: " + e.getMessage());

				}
				
			}			
		} 		

		public void importWindow(Map<String, String> attsMap) {
			MWindow m_Window = null;
			String entitytype = attsMap.get("EntityType");
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String name = attsMap.get("Name");			
				int id= get_ID("AD_Window", name);
				m_Window = new MWindow(m_ctx, id, m_trxName);
				if (id > 0){			
					AD_Backup_ID = copyRecord("AD_Window",m_Window);
					Object_Status = "Update";	    	
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				m_Window.setName(name);	
				name = attsMap.get("ADImageNameID");	    
				id = get_IDWithColumn("AD_Image", "Name", name);
				m_Window.setAD_Image_ID(id);
				name = attsMap.get("ADColorNameID");	    
				id = get_IDWithColumn("AD_Color", "Name", name);
				m_Window.setAD_Color_ID(id); 
				m_Window.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Window.setEntityType(attsMap.get("EntityType"));
				m_Window.setHelp (attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Window.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Window.setIsBetaFunctionality(Boolean.valueOf(attsMap.get("isBetaFunctionality")).booleanValue());
				m_Window.setIsDefault(Boolean.valueOf(attsMap.get("isDefault")).booleanValue());
				m_Window.setIsSOTrx(Boolean.valueOf(attsMap.get("isSOTrx")).booleanValue());
				m_Window.setName (attsMap.get("Name"));
				m_Window.setProcessing (false);
				//m_Window.setWinHeight(Integer.parseInt(atts.getValue("WinHeight")));
				//m_Window.setWinWidth (Integer.parseInt(atts.getValue("WinWidth")));
				m_Window.setWindowType (attsMap.get("WindowType"));
				try {
				if (m_Window.save(m_trxName) == true){
					System.out.println("m_Window.save succeeded");
					record_log (1, m_Window.getName(),"Window", m_Window.get_ID(),AD_Backup_ID, Object_Status,"AD_Window",get_IDWithColumn("AD_Table", "TableName", "AD_Window"));           		        		
				}
				else{
					System.out.println("m_Window.save failed");
					record_log (0, m_Window.getName(),"Window", m_Window.get_ID(),AD_Backup_ID,  Object_Status,"AD_Window",get_IDWithColumn("AD_Table", "TableName", "AD_Window"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importWindow: " + e.getMessage());

				}
			}
		}

		public void importMessage(Map<String, String> attsMap) {
			MMessage m_Message = null;
			String entitytype = attsMap.get("EntityType");
			if (entitytype.equals("U") || entitytype.equals("D") ) {
				String value = attsMap.get("Value");
				int id = get_IDWithColumn("AD_Message", "value", value);

				m_Message = new MMessage(m_ctx, id, m_trxName);
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_Message",m_Message);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}    	    
				m_Message.setMsgText(attsMap.get("MsgText").replaceAll("'","''").replaceAll(",",""));
				m_Message.setMsgTip(attsMap.get("MsgTip").replaceAll("'","''").replaceAll(",",""));
				m_Message.setEntityType(attsMap.get("EntityType"));
				m_Message.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Message.setValue(value);
				m_Message.setMsgType(attsMap.get("MsgType"));		        
				try {
				if (m_Message.save(m_trxName) == true){		    	
					System.out.println("m_Message.save succeeded");
					record_log (1, m_Message.getValue(),"Message", m_Message.get_ID(),AD_Backup_ID, Object_Status,"AD_Message",get_IDWithColumn("AD_Message", "value", "AD_Message"));           		        		
				}
				else{
					System.out.println("m_Message.save failed");
					record_log (0, m_Message.getValue(),"Message", m_Message.get_ID(),AD_Backup_ID, Object_Status,"AD_Message",get_IDWithColumn("AD_Message", "value", "AD_Message"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importMessage: " + e.getMessage());

				}
			}
		}

		public void importDynValRule(Map<String, String> attsMap) {
			String entitytype = attsMap.get("EntityType");
			if (entitytype.equals("U") || entitytype.equals("D") ) {
				X_AD_Val_Rule m_ValRule = null;
				String name = attsMap.get("Name");
				int id = get_IDWithColumn("AD_Val_Rule", "name", name);
				
				m_ValRule = new X_AD_Val_Rule(m_ctx, id, m_trxName);
				if (id > 0){		
					AD_Backup_ID = copyRecord("AD_Val_Rule",m_ValRule);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}    	    
				m_ValRule.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_ValRule.setEntityType(attsMap.get("EntityType"));
				m_ValRule.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_ValRule.setName(name);
				m_ValRule.setType(attsMap.get("Type"));		        
				m_ValRule.setCode(attsMap.get("Code"));		        
				try {
				if (m_ValRule.save(m_trxName) == true){		    	
					System.out.println("m_ValRule.save succeeded");
					record_log (1, m_ValRule.getName(),"Task", m_ValRule.get_ID(),AD_Backup_ID, Object_Status,"AD_Val_Rule",get_IDWithColumn("AD_Val_Rule", "Name", "AD_Val_Rule"));           		        		
				}
				else{
					System.out.println("m_ValRule.save failed");
					record_log (0, m_ValRule.getName(),"Task", m_ValRule.get_ID(),AD_Backup_ID, Object_Status,"AD_Val_Rule",get_IDWithColumn("AD_Val_Rule", "Name", "AD_Val_Rule"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importDynValRule: " + e.getMessage());

				}
			}
		}

		public void importReferenceList(Map<String, String> attsMap) {
			//TODO: Solve for date issues with valuefrom valueto
			X_AD_Ref_List m_Ref_List = null;
			String entitytype = attsMap.get("EntityType");		
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0 ) {
				String name = attsMap.get("Name");
				int Referenceid = get_IDWithColumn("AD_Reference", "Name", attsMap.get("ADRefenceNameID"));
				int id = get_IDWithMaster("AD_Ref_List",  name, "AD_Reference",Referenceid);
				m_Ref_List = new X_AD_Ref_List(m_ctx, id, m_trxName);
				if (id > 0){
					AD_Backup_ID = copyRecord("AD_Ref_List",m_Ref_List);
					Object_Status = "Update";			
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				name = attsMap.get("ADRefenceNameID");	    
				id = get_IDWithColumn("AD_Reference", "Name", name);
				m_Ref_List.setAD_Reference_ID(id);
				m_Ref_List.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Ref_List.setEntityType (attsMap.get("EntityType"));
				m_Ref_List.setName(attsMap.get("Name"));
				m_Ref_List.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Ref_List.setValue(attsMap.get("Value"));

				try {
				if (m_Ref_List.save(m_trxName) == true){		    	
					System.out.println("m_Ref_List.save succeeded");
					record_log (1, m_Ref_List.getName(),"Reference List", m_Ref_List.get_ID(),AD_Backup_ID, Object_Status,"AD_Ref_List",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_List"));           		        		
				}
				else{
					System.out.println("m_Ref_List.save failed");
					record_log (0, m_Ref_List.getName(),"Reference List", m_Ref_List.get_ID(),AD_Backup_ID, Object_Status,"AD_Ref_List",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_List"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importReferenceList: " + e.getMessage());

				}
			}
		}

		public void importReference(Map<String, String> attsMap) {
			X_AD_Reference m_Reference = null;

			String entitytype = attsMap.get("EntityType");		
			String name = attsMap.get("name");
			
			if (entitytype.compareTo("U") == 0 || entitytype.compareTo("D") == 0) {
				int id = get_ID("AD_Reference", name);
				
				m_Reference = new X_AD_Reference(m_ctx, id, m_trxName);
				if (id > 0){
					AD_Backup_ID = copyRecord("AD_Reference",m_Reference);
					Object_Status = "Update";
				}
				else{
					Object_Status = "New";
					AD_Backup_ID =0;
				}
				
				m_Reference.setDescription(attsMap.get("Description").replaceAll("'","''").replaceAll(",",""));
				m_Reference.setEntityType(attsMap.get("EntityType"));
				m_Reference.setHelp(attsMap.get("Help").replaceAll("'","''").replaceAll(",",""));
				m_Reference.setIsActive(attsMap.get("isActive") != null ? Boolean.valueOf(attsMap.get("isActive")).booleanValue():true);
				m_Reference.setName(attsMap.get("name"));
				
				//m_Reference.setVFormat(atts.getValue("VFormat"));
				m_Reference.setValidationType(attsMap.get("ValidationType"));	        
				try {
				if (m_Reference.save(m_trxName) == true){
					System.out.println("m_Reference.save succeeded");
					record_log (1, m_Reference.getName(),"Reference", m_Reference.get_ID(),AD_Backup_ID, Object_Status,"AD_Reference",get_IDWithColumn("AD_Table", "TableName", "AD_Reference"));           		        		
				}
				else{
					System.out.println("m_Reference.save failed");
					record_log (0, m_Reference.getName(),"Reference", m_Reference.get_ID(),AD_Backup_ID, Object_Status,"AD_Reference",get_IDWithColumn("AD_Table", "TableName", "AD_Reference"));
				}
				} catch(SAXException e) {
					System.out.println("Exception in importReferenceList: " + e.getMessage());

				}
				
			}
		}

		public void handleMenusImport() {
			System.out.println("In handleMenusImport");
			if(elementAttsMap.containsKey("menu")) {

				List<Map<String, String>> m_menusAttsMapList = elementAttsMap.get("menu");
				System.out.println("processing " + m_menusAttsMapList.size() + " menus");
					while(m_menusAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_menusAttsMapList.get(0);
						System.out.println("menu name: " +  attsMap.get("ADMenuNameID"));
						importMenu(attsMap);		
						attsMap.clear();
						m_menusAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("menu");
			}

		}

		public void handleWorkflowNodeNextsImport() {
			System.out.println("In handleWorkflowNodeNextsImport");
			if(elementAttsMap.containsKey("workflowNodeNext")) {

				List<Map<String, String>> m_workflowNodeNextsAttsMapList = elementAttsMap.get("workflowNodeNext");
				System.out.println("processing " + m_workflowNodeNextsAttsMapList.size() + " workflowNodeNexts");
					int i = 0;
					while(m_workflowNodeNextsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_workflowNodeNextsAttsMapList.get(0);
						System.out.println("worklfowNodeNext num: "+ i++ +" name: " +  attsMap.get("ADWorkflowNodeNextNameID"));
						importWorkflowNodeNext(attsMap);
						attsMap.clear();
						m_workflowNodeNextsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("workflowNodeNext");
			}

		}


		public void handleReferenceTablesImport() {
			System.out.println("In handleReferenceTablesImport");
			if(elementAttsMap.containsKey("referencetable")) {

				List<Map<String, String>> m_referenceTablesAttsMapList = elementAttsMap.get("referencetable");
				System.out.println("processing " + m_referenceTablesAttsMapList.size() + " referenceTables");
					while(m_referenceTablesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_referenceTablesAttsMapList.get(0);
						System.out.println("reference table name: " +  attsMap.get("ADRefenceNameID"));
						importReferenceTable(attsMap);
						attsMap.clear();
						m_referenceTablesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("referencetable");
			}

		}

		public void handleProcessParasImport() {
			System.out.println("In handleProcessParasImport");
			if(elementAttsMap.containsKey("processpara")) {

				List<Map<String, String>> m_processParasAttsMapList = elementAttsMap.get("processpara");
				System.out.println("processing " + m_processParasAttsMapList.size() + " processparas");
					while(m_processParasAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_processParasAttsMapList.get(0);
						System.out.println("processPara name: " +  attsMap.get("Name"));
						importProcessPara(attsMap);
						attsMap.clear();
						m_processParasAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("processpara");
			}

		}


		public void handlePreferencesImport() {
			System.out.println("In handlePreferencesImport");
			if(elementAttsMap.containsKey("preference")) {

				List<Map<String, String>> m_preferencesAttsMapList = elementAttsMap.get("preference");
				System.out.println("processing " + m_preferencesAttsMapList.size() + " preferences");
					while(m_preferencesAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_preferencesAttsMapList.get(0);
						System.out.println("preference name: " +  attsMap.get("Name"));
						importPreference(attsMap);
						attsMap.clear();
						m_preferencesAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("preference");
			}

		}

		public void handleTabsImport() {
			System.out.println("In handleTabsImport");
			if(elementAttsMap.containsKey("tab")) {

				List<Map<String, String>> m_tabsAttsMapList = elementAttsMap.get("tab");
				System.out.println("processing " + m_tabsAttsMapList.size() + " tabs");
					while(m_tabsAttsMapList.size() > 0 ) {
						Map<String, String> attsMap = m_tabsAttsMapList.get(0);
						System.out.println("tab name: " +  attsMap.get("Name"));
						importTab(attsMap);
						attsMap.clear();
						m_tabsAttsMapList.remove(0);
						/*for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					} */
						
					}


				elementAttsMap.remove("tab");
			}

		}

		public void processImportStructure() {
			System.out.println("In processImportStructure");
			handleWindowsImport();
			handleDynValRulesImport(); 
			handleFormsImport();
			handleMessagesImport(); 
			handleReferencesImport();
			handleTablesImport(); 
			handleReferenceListsImport();   
			handleColumnsImport();   
			handleFieldsImport(); 
			handlePrintFormatsImport(); 
			handlePrintFormatItemsImport(); 
			handleWorkflowsImport(); 
			handleProcessesImport();  
			handleWorkflowNodesImport();   
			handleWorkflowNodeNextsImport(); 
			handleMenusImport(); 
			handleReferenceTablesImport(); 
			handleProcessParasImport(); 
			handlePreferencesImport(); 
			handleTabsImport(); 
			// FIXME:  don't know why it needs this again but it does
			handleFieldsImport(); 

			for (Map.Entry<String, List<Map<String, String>>> e : elementAttsMap.entrySet()) {
    				System.out.println(e.getKey());
			}

			if(true) {
    				System.out.println("Committing changes to database");

		         try {
				DB.commit(true, m_trxName);
			} catch (SQLException e) {
				e.printStackTrace(); 
			}  
			}

		}

	/**
	 * Get ID from Name for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 * 
	 */
	public int get_ID (String tableName, String name) {
		int id = 0;
		sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where name=?");
		
		if (!tableName.startsWith("AD_"))
			sqlB = sqlB.append(" and AD_Client_ID=?");
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
			pstmt.setString(1, name);
			if (!tableName.startsWith("AD_"))
				pstmt.setInt(2, m_AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			System.out.println ("get_ID:"+e);
		}
		return id;
	}

	/**
	 * Get ID from column value for a table.
	 *
	 * @param tableName
	 * @param columName
	 * @param name
	 */
	public int get_IDWithColumn (String tableName, String columnName, Object value) {
		int id = 0;
		sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where UPPER("+columnName+")=?");
		//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+value.toString());
		
		if (!tableName.startsWith("AD_"))
			sqlB = sqlB.append(" and AD_Client_ID=?");
		//here!
		sqlB = sqlB.append(" Order By "+tableName+"_ID");
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
			if (value instanceof String)
				pstmt.setString(1, ((String)value).toUpperCase());
			else if (value instanceof Integer)
				pstmt.setInt(1, ((Integer)value).intValue());
			if (!tableName.startsWith("AD_"))
				pstmt.setInt(2, m_AD_Client_ID);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			System.out.println ("get_ID:"+e);
		}
		return id;
	}

	/**
	 * Get ID from Name for a table with a Master reference.
	 *
	 * @param tableName
	 * @param name
	 * @param tableNameMaster
	 * @param nameMaster
	 */
	public int get_IDWithMaster (String tableName, String name, String tableNameMaster, String nameMaster) {
		int id = 0;
		sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where UPPER(name)=? and "
				+ tableNameMaster+"_ID = (select "+tableNameMaster+"_ID from "+tableNameMaster+" where UPPER(name)=?)");
		
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
			pstmt.setString(1, name.toUpperCase());
			pstmt.setString(2, nameMaster.toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			System.out.println ("get_IDWithMaster:"+e);
		}
		return id;
	}

    /**
     * Get ID from Name for a table with a Master reference.
     *
     * @param tableName
     * @param name
     * @param tableNameMaster
     * @param nameMaster
     */    
    
	public int get_IDWithMasterAndColumn (String tableName, String columnName, String name, String tableNameMaster, int masterID) {
		int id = 0;
		sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where UPPER("+columnName+")=? and "
				+ tableNameMaster+"_ID =?");
		//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+name+" and "
		//	    + tableNameMaster+"_ID ="+masterID);
		System.out.println(sqlB.toString());
		
		try {
			
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
			pstmt.setString(1, name.toUpperCase());
			pstmt.setInt(2, masterID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			System.out.println ("get_IDWithMasterAndColumn:"+e);
		}
		return id;
	}

	/**
	 * Get ID from Name for a table with a Master reference ID.
	 *
	 * @param tableName
	 * @param name
	 * @param tableNameMaster
	 * @param masterID
	 */    
	public int get_IDWithMaster (String tableName, String name, String tableNameMaster, int masterID) {
		int id = 0;
		sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where name=? and "
				+ tableNameMaster+"_ID=?");
		
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
			pstmt.setString(1, name);
			pstmt.setInt(2, masterID);	
			ResultSet rs = pstmt.executeQuery();	    
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			System.out.println ("get_IDWithMasterID:"+e);
		}
		return id;
	}

	/**
	 * Get ID from Name for a table.
	 * TODO: substitute with PO.getAllIDs
	 *
	 * @param tableName
	 * @param name
	 */
	public int getIDbyName (String tableName, String name) {
		int id = 0;
		String sql = "SELECT "+tableName+"_ID "
		+ "FROM "+tableName+" "
		+ "WHERE name=?";
		if (!tableName.startsWith("AD_"))
			sql = sql + " AND AD_Client_ID=?";
		try {
			PreparedStatement pstmt = DB.prepareStatement(sql, m_trxName);
			pstmt.setString(1, name);
			if (!tableName.startsWith("AD_"))
				pstmt.setInt(2, m_AD_Client_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				id = rs.getInt(1);
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e) {
			System.out.println("getID:"+e);
		}
		return id;
	}

    /**
     *	Write results to log and records in history table
     *
     *      @param success
     * 		@param tableName
     * 		@param objectType
     * 		@param objectID
     * 		@param objectStatus
     * 		@throws SAXException
     *       	
     */
    public int record_log (int success, String objectName,String objectType, int objectID,int objectIDBackup, String objectStatus, String tableName, int AD_Table_ID) throws SAXException{    	
	System.out.println("In record_log");
    	String recordLayout;
    	int id = 0;
    	if (success == 1){    		
		System.out.println("In record_log 1");
    		//hd_documemt.startElement("","","Successfull",attsOut);
    		recordLayout = "Type:"+objectType + "  -   Name:"+objectName + "  -  ID:"+objectID +"  -  Action:"+objectStatus+"  -  Success";
    		hd_documemt.startElement("","","Success",attsOut);
    		hd_documemt.characters(recordLayout.toCharArray(),0,recordLayout.length());
    		hd_documemt.endElement("","","Success");
    		//hd_documemt.endElement("","","Successfull");
    		
    		//String sql2 = "SELECT MAX(AD_PACKAGE_IMP_DETAIL_ID) FROM AD_PACKAGE_IMP_DETAIL";
    		//int id = DB.getSQLValue(m_trxName, sql2)+1;
		System.out.println("In record_log 2");
    		
    		id = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Package_Imp_Detail", m_trxName);
		System.out.println("In record_log 3");
    		
    		sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Detail" 
    				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
    				+   "AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID, TYPE, NAME," 
    				+   " ACTION, SUCCESS, AD_ORIGINAL_ID, AD_BACKUP_ID, TABLENAME, AD_TABLE_ID)"
    				+	" VALUES ("
    				+	" "+ Env.getAD_Client_ID(m_ctx)
    				+	", "+ Env.getAD_Org_ID(m_ctx)
    				+	", "+ Env.getAD_User_ID(m_ctx)
    				+	", "+ Env.getAD_User_ID(m_ctx)
    				+	", " + id 
    				+	", " + AD_Package_Imp_ID
    				+	", '" + objectType
    				+	"', '" + objectName
    				+	"', '" + objectStatus
    				+	"', 'Success'"
    				+	", "+objectID
    				+	", "+objectIDBackup
    				+	", '"+tableName
    				+	"', "+AD_Table_ID
    				+")");
    		int no = DB.executeUpdate (sqlB.toString(), m_trxName);
		System.out.println("In record_log 4");
    		if (no == -1)
    			System.out.println("Insert to import detail failed");
    		
    	}
    	else{
    		PK_Status = "Completed with errors";
    		hd_documemt.startElement("","","Failure",attsOut);    	
    		recordLayout = "Type:"+objectType + "  -   Name:"+tableName + "  -  ID:"+objectID +"  -  Action:"+objectStatus+"  -  Failure";
    		//hd_documemt.startElement("","","Success",attsOut);
    		hd_documemt.characters(recordLayout.toCharArray(),0,recordLayout.length());
    		//hd_documemt.endElement("","","Success");		
    		hd_documemt.endElement("","","Failure");
    		
    		//String sql2 = "SELECT MAX(AD_PACKAGE_IMP_DETAIL_ID) FROM AD_PACKAGE_IMP_DETAIL";
    		//int id = DB.getSQLValue(m_trxName,sql2)+1; 
    		
    		id = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Package_Imp_Detail", m_trxName);
    		
    		sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Detail" 
    				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
    				+   "AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID, TYPE, NAME," 
    				+   " ACTION, SUCCESS, AD_ORIGINAL_ID, AD_BACKUP_ID, TABLENAME, AD_TABLE_ID)"
    				+	" VALUES ("
    				+	" "+ Env.getAD_Client_ID(m_ctx)
    				+	", "+ Env.getAD_Org_ID(m_ctx)
    				+	", "+ Env.getAD_User_ID(m_ctx)
    				+	", "+ Env.getAD_User_ID(m_ctx)
    				+	", " + id 
    				+	", " + AD_Package_Imp_ID
    				+	", '" + objectType
    				+	"', '" + objectName
    				+	"', '" + objectStatus
    				+	"', 'Failure'" 
    				+	", "+objectID
    				+	", "+objectIDBackup
    				+	", '"+tableName
    				+	"', "+AD_Table_ID
    				+")");
    		int no = DB.executeUpdate (sqlB.toString(), m_trxName);
    		if (no == -1)
    			System.out.println("Insert to import detail failed");
    	}
    	
    	Object_Status = "Status not set";
    	return id;  
    }

	public void set_TrxName(String trxName) {
		m_trxName = trxName;
	}
    
    // globalqss - add support for trx in 3.1.2
	public void setCtx(Properties ctx) {
		m_ctx = ctx;
	}

    /**
     *	Make backup copy of record.
     *
     *      @param tablename
     *  	
     *  	
     *       	
     */
    public int copyRecord(String tableName,PO from){
	// Create new record
    	int idBackup = 0;
    	String colValue=null;
    	int tableID = get_IDWithColumn("AD_Table", "TableName", tableName);    	
		POInfo poInfo = POInfo.getPOInfo(m_ctx, tableID);
		for (int i = 0; i < poInfo.getColumnCount(); i++){
			String colName = poInfo.getColumnName(i);
			colValue=null;
			
			    int columnID =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", poInfo.getColumnName(i), "AD_Table", tableID);
			    StringBuffer sqlD = new StringBuffer("SELECT AD_Reference_ID FROM AD_COLUMN WHERE AD_Column_ID = '"+columnID+"'");
	    		int referenceID = DB.getSQLValue(m_trxName,sqlD.toString());
	    		
	    		idBackup = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Package_Imp_Backup", m_trxName);
	    		
	    		sqlD = new StringBuffer("SELECT MAX(AD_PACKAGE_IMP_DETAIL_ID) FROM AD_PACKAGE_IMP_DETAIL");
	    		int idDetail = DB.getSQLValue(m_trxName,sqlD.toString())+1;
	    		
	    		if (referenceID == 10 || referenceID == 14 || referenceID == 34 || referenceID == 17)
	    			if (from.get_Value(i)!= null)
	    				colValue = from.get_Value(i).toString().replaceAll("'","''");	    		
				else if (referenceID == 20|| referenceID == 28)
					if (from.get_Value(i)!= null)	    				    				
	    				colValue = from.get_Value(i).toString().replaceAll("'","''");
				else
					;//Ignore
	    			    		
	    		sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Backup" 
	    				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
	    				+   "AD_PACKAGE_IMP_BACKUP_ID, AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID," 
	    				+	" AD_TABLE_ID, AD_COLUMN_ID, AD_REFERENCE_ID, COLVALUE)"
	    				+	"VALUES("
	    				+	" "+ Env.getAD_Client_ID(m_ctx)
	    				+	", "+ Env.getAD_Org_ID(m_ctx)
	    				+	", "+ Env.getAD_User_ID(m_ctx)
	    				+	", "+ Env.getAD_User_ID(m_ctx)
						+	", " + idBackup
						+	", " + idDetail
	    				+	", " + AD_Package_Imp_ID
	    				+	", " + tableID
	    				+	", " + columnID
	    				+	", " + referenceID
	    				+	", '" + (colValue != null ? colValue : from.get_Value(i))
	    				+"')");
	    		
	    		int no = DB.executeUpdate (sqlB.toString(), m_trxName);
	    		if (no == -1)
					System.out.println("Insert to import backup failed");
	    		//}
		}		
		return idBackup;
    }

	// to aid with unit testing
	public void setHd_document(TransformerHandler hd_documemt) {
		this.hd_documemt = hd_documemt;
	}

    
    /**
     *	Check if column exists in database and modify.  If not create column.
     *
     *      @param tablename
     * 		@param columnname
     * 		@param v_AD_Reference_ID
     *  	@param v_FieldLength
     *  	@param v_DefaultValue
     *  	@param v_IsMandatory
     *       	
     */
    public int createcolumn (MColumn column){
    	MTable table = new MTable(m_ctx, column.getAD_Table_ID(), m_trxName);
    	if (table.isView())
    		return 0;
    	
    	int no = 0;
    	
    	String sql = null;
    	ResultSet rst = null;
    	ResultSet rsc = null; 
    	try {
    		//	Find Column in Database
    		DatabaseMetaData md = DB.getConnectionRO().getMetaData();
    		String catalog = DB.getDatabase().getCatalog();
    		String schema = DB.getDatabase().getSchema();
    		String tableName = table.getTableName();
    		String columnName = column.getColumnName();
    		if (DB.isOracle()) {
    			tableName = tableName.toUpperCase();
    			columnName = columnName.toUpperCase();
    		} else if (DB.isPostgreSQL()) {
    			tableName = tableName.toLowerCase();
    			columnName = columnName.toLowerCase();
    		}
    		
    		rst = md.getTables(catalog, schema, tableName, new String[] {"TABLE"});
    		if (! rst.next()) {
    			// table doesn't exist
    			sql = table.getSQLCreate ();
    		} else {
        		//
        		rsc = md.getColumns(catalog, schema, tableName, columnName);
        		if (rsc.next())
        		{
        			//	update existing column
        			boolean notNull = DatabaseMetaData.columnNoNulls == rsc.getInt("NULLABLE");
        			sql = column.getSQLModify(table, column.isMandatory() != notNull);
        		} else {
            		//	No existing column
        			sql = column.getSQLAdd(table);
        		}
        		rsc.close();
        		rsc = null;
    		}
    			
    		rst.close();
    		rst = null;
    		System.out.println("createcolumn sql: " + sql);
    		
        	if (sql.indexOf(DB.SQLSTATEMENT_SEPARATOR) == -1)
        	{
        		no = DB.executeUpdate(sql, false, m_trxName);
        	}
        	else
        	{
        		String statements[] = sql.split(DB.SQLSTATEMENT_SEPARATOR);
        		for (int i = 0; i < statements.length; i++)
        		{
        			int count = DB.executeUpdate(statements[i], false, m_trxName);
        			no += count;
        		}
        	}

    	} catch (SQLException e) {
    		e.printStackTrace();
    		if (rsc != null) {
    			try {
					rsc.close();
				} catch (SQLException e1) { }
    			rsc = null;
    		}
    		if (rst != null) {
    			try {
					rst.close();
				} catch (SQLException e1) { }
    			rst = null;
    		}
    		return 0;
    	}
    	
    	// postgres requires commit on DDL (ALTER,CREATE)
    	if (DB.isPostgreSQL()) {
    		try {
				DB.commit(true, m_trxName);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
    	}
    	
    	return 1;
    }

	}

	

	public void testXMLImportStructure() {
		String trxName = "test";
		Trx m_trx = Trx.get(Trx.createTrxName("SvrProcess"), true);


		File in = new File("PackOut.xml");
                if (!in.exists()) {
                        String msg = "File does not exist: PackOut.xml ";
                        System.out.println("importXML:" + msg);
			assertTrue("testXMLImportStructure", false);
                } else {
                	try {
                        	System.out.println("starting");

                        	TestPackInHandler handler = new TestPackInHandler();
		 		handler.setCtx(m_Ctx);
		 		handler.set_TrxName("test");

				SAXTransformerFactory tf_document = null;
				TransformerHandler hd_documemt = null;
				tf_document = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
								                  				try {
										                           		hd_documemt = tf_document.newTransformerHandler();
													        } catch (TransformerConfigurationException e2) {
															System.out.println("startElement:"+e2);
														} 
																					                   	handler.setHd_document(hd_documemt);


                        	SAXParserFactory factory = SAXParserFactory.newInstance();
                        	SAXParser parser = factory.newSAXParser();
                        	String msg = "Start Parser";
                        	System.out.println(msg);
                        	parser.parse(in, handler);
                        	msg = "End Parser";
                        	System.out.println(msg);
				Map<String, List<Map<String, String>>> elementAttsMap =  handler.getElementAttsMap();

				/* for (Map.Entry<String, List<Map<String, String>>> e : elementAttsMap.entrySet()) {
    					//System.out.println(e.getKey() + ": " + e.getValue());
					List<Map<String, String>> m_attsMapList = e.getValue();
    					System.out.println("element: " + e.getKey() );
					System.out.println("list size: " +  m_attsMapList.size());
					for (int j = 0; j < m_attsMapList.size(); j++ ) {
						Map<String, String> attsMap = m_attsMapList.get(j);
						System.out.println("attsMap length: " +  attsMap.size());
						for (Map.Entry<String, String> f : attsMap.entrySet()) {
                					System.out.println( "attsMap begin ----------");
    							System.out.println(f.getKey() + ": " + f.getValue());
                					System.out.println( "atts end   ----------");
            					}
						
					}

				} */

				
                	} catch (Exception e) {
                        	System.out.println("importXML exception: " +  e);
				e.printStackTrace();
                	}


		}

		assertTrue("testXMLImportStructure", true);
	}

	public static void main(String[] args) {
	      System.out.println("In main of XMLImportStructureTest");

	      XMLImportStructureTest m_XMLImportStructureTest = new XMLImportStructureTest();

              try {
	          m_XMLImportStructureTest.setUp();
	          m_XMLImportStructureTest.testXMLImportStructure();
              } catch(Exception e) {
	          System.out.println("Exception with m_XMLImportStructureTest.setUp: " + e.getMessage());
              }
       }


	
}
