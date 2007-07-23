/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *                                                                            *
 * Copyright (C) 2004 Marco LOMBARDO. lombardo@mayking.com                    *
 * Contributor: Robert KLEIN. robeklein@hotmail.com                           *
 * Contributor: Tim Heath                                                     *
 * Contributor: Low Heng Sin  hengsin@avantz.com                              *
 *****************************************************************************/

package org.adempiere.pipo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Stack;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.adempiere.pipo.handler.CodeSnipitElementHandler;
import org.adempiere.pipo.handler.ColumnElementHandler;
import org.adempiere.pipo.handler.DataElementHandler;
import org.adempiere.pipo.handler.DistFileElementHandler;
import org.adempiere.pipo.handler.DynValRuleElementHandler;
import org.adempiere.pipo.handler.FieldElementHandler;
import org.adempiere.pipo.handler.FormAccessElementHandler;
import org.adempiere.pipo.handler.FormElementHandler;
import org.adempiere.pipo.handler.ImpFormatElementHandler;
import org.adempiere.pipo.handler.ImpFormatRowElementHandler;
import org.adempiere.pipo.handler.MenuElementHandler;
import org.adempiere.pipo.handler.MessageElementHandler;
import org.adempiere.pipo.handler.OrgRoleElementHandler;
import org.adempiere.pipo.handler.PreferenceElementHandler;
import org.adempiere.pipo.handler.PrintFormatElementHandler;
import org.adempiere.pipo.handler.PrintFormatItemElementHandler;
import org.adempiere.pipo.handler.ProcessAccessElementHandler;
import org.adempiere.pipo.handler.ProcessElementHandler;
import org.adempiere.pipo.handler.ProcessParaElementHandler;
import org.adempiere.pipo.handler.ReferenceElementHandler;
import org.adempiere.pipo.handler.ReferenceListElementHandler;
import org.adempiere.pipo.handler.ReferenceTableElementHandler;
import org.adempiere.pipo.handler.ReportViewColElementHandler;
import org.adempiere.pipo.handler.ReportViewElementHandler;
import org.adempiere.pipo.handler.RoleElementHandler;
import org.adempiere.pipo.handler.SQLStatementElementHandler;
import org.adempiere.pipo.handler.TabElementHandler;
import org.adempiere.pipo.handler.TableElementHandler;
import org.adempiere.pipo.handler.TaskAccessElementHandler;
import org.adempiere.pipo.handler.TaskElementHandler;
import org.adempiere.pipo.handler.UserRoleElementHandler;
import org.adempiere.pipo.handler.WindowAccessElementHandler;
import org.adempiere.pipo.handler.WindowElementHandler;
import org.adempiere.pipo.handler.WorkbenchElementHandler;
import org.adempiere.pipo.handler.WorkflowAccessElementHandler;
import org.adempiere.pipo.handler.WorkflowElementHandler;
import org.adempiere.pipo.handler.WorkflowNodeElementHandler;
import org.adempiere.pipo.handler.WorkflowNodeNextConditionElementHandler;
import org.adempiere.pipo.handler.WorkflowNodeNextElementHandler;
import org.compiere.model.*;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.CLogger;
import org.compiere.util.Trx;
import org.xml.sax.Attributes;

import org.xml.sax.helpers.DefaultHandler;



/**
 * SAX Handler for parsing XML description of the GUI.
 *
 * @author Marco LOMBARDO, lombardo@mayking.com
 * @author Robert KLEIN, robeklein@hotmail
 * 
 * Contributor: William G. Heath - Import of workflows and dynamic validations
 */
public class PackInHandler extends DefaultHandler {

    /**
     * 	PackInHandler Handler
     */
    public PackInHandler () {
    	setupHandlers();
    }   // PackInHandler   
    
    /** Set this if you want to update Dictionary  */
    private String m_UpdateMode = "true";
    private String packageDirectory = null;
    private String m_DatabaseType = "Oracle";
    private boolean adempiereAD = false;    
    private int m_AD_Client_ID = 0;
    private int AD_Package_Imp_ID=0;
	private int AD_Package_Imp_Inst_ID=0;
    private CLogger log = CLogger.getCLogger("PackIn");
    private OutputStream  fw_document = null;
    private TransformerHandler hd_document = null;
    private AttributesImpl attsOut = null;
    private StreamResult streamResult_document = null;		
	private SAXTransformerFactory tf_document = null;	
	private Transformer serializer_document = null;
	private int Start_Doc = 0;
	private String logDate = null;
	private String PK_Status = "Installing";
	// transaction name 
	private	String 		m_trxName = null;
	private Properties  m_ctx = null;

	private Map<String, ElementHandler>handlers = null;
	private List<Element> menus = new ArrayList<Element>();
	private List<DeferEntry> defer = new ArrayList<DeferEntry>();
	private Stack<Element> stack = new Stack<Element>();

	private void init() throws SAXException {
		PackIn pack = new PackIn();
		packageDirectory = pack.m_Package_Dir;
		m_UpdateMode = pack.m_UpdateMode;
		m_DatabaseType = pack.m_Database;    	
		File file = new File("");
		SimpleDateFormat formatter_file = new SimpleDateFormat("yyMMddHHmmssZ");
		SimpleDateFormat formatter_log = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date today = new Date();
		String fileDate = formatter_file.format(today);
		logDate = formatter_log.format(today);
		
		String file_document = packageDirectory+File.separator+"doc"+File.separator+"Importlog_"+fileDate+".xml";		
		log.info("file_document="+file_document);
		try {
			fw_document = new FileOutputStream (file_document, false);
		} catch (FileNotFoundException e1) {
			log.warning ("Failed to create log file:"+e1);
		}
		streamResult_document = new StreamResult(fw_document);		
		tf_document = (SAXTransformerFactory) SAXTransformerFactory.newInstance();	
		
		try {
			hd_document = tf_document.newTransformerHandler();
		} catch (TransformerConfigurationException e2) {
			log.info ("startElement:"+e2);
		}		
		serializer_document = hd_document.getTransformer();		
		serializer_document.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
		serializer_document.setOutputProperty(OutputKeys.INDENT,"yes");		
		hd_document.setResult(streamResult_document);				
		hd_document.startDocument();		
		attsOut = new AttributesImpl();		
		attsOut.clear();		
		hd_document.processingInstruction("xml-stylesheet","type=\"text/css\" href=\"adempiereDocument.css\"");
		Properties tmp = new Properties();
		if (m_ctx != null)
			tmp.putAll(m_ctx);
		else
			tmp.putAll(Env.getCtx());
		m_ctx = tmp;
		if (m_trxName == null)
			m_trxName = Trx.createTrxName("PackIn");
		
		m_AD_Client_ID = Env.getContextAsInt(m_ctx, "AD_Client_ID");
		
		Start_Doc=1;
	}
	
	private void setupHandlers() {
		DataElementHandler dataHandler = new DataElementHandler();
    	handlers = new HashMap<String, ElementHandler>();
    	handlers.put("menu", new MenuElementHandler());
    	handlers.put("adempieredata", dataHandler);
    	handlers.put("data", dataHandler);
    	handlers.put("dtable", dataHandler);
    	handlers.put("drow", dataHandler);
    	handlers.put("dcolumn", dataHandler);
    	handlers.put("window", new WindowElementHandler());
    	handlers.put("windowaccess", new WindowAccessElementHandler());
    	handlers.put("preference", new PreferenceElementHandler());
    	handlers.put("tab", new TabElementHandler());
    	handlers.put("field", new FieldElementHandler());
    	handlers.put("process", new ProcessElementHandler());
    	handlers.put("processpara", new ProcessParaElementHandler());
    	handlers.put("processaccess", new ProcessAccessElementHandler());
    	handlers.put("message", new MessageElementHandler());
    	handlers.put("dynvalrule", new DynValRuleElementHandler());
    	handlers.put("workflow", new WorkflowElementHandler());
    	handlers.put("workflowNode", new WorkflowNodeElementHandler());
    	handlers.put("workflowNodeNext", new WorkflowNodeNextElementHandler());
    	handlers.put("workflowNodeNextCondition", new WorkflowNodeNextConditionElementHandler());
    	handlers.put("workflowaccess", new WorkflowAccessElementHandler());
    	handlers.put("table", new TableElementHandler());
    	handlers.put("column", new ColumnElementHandler());
    	handlers.put("role", new RoleElementHandler());
    	handlers.put("userrole", new UserRoleElementHandler());
    	handlers.put("orgrole", new OrgRoleElementHandler());
    	handlers.put("form", new FormElementHandler());
    	handlers.put("formaccess", new FormAccessElementHandler());
    	handlers.put("task", new TaskElementHandler());
    	handlers.put("taskaccess", new TaskAccessElementHandler());
    	handlers.put("impformat", new ImpFormatElementHandler());
    	handlers.put("impformatrow", new ImpFormatRowElementHandler());
    	handlers.put("workbench", new WorkbenchElementHandler());
    	handlers.put("codesnipit", new CodeSnipitElementHandler());
    	handlers.put("distfile", new DistFileElementHandler());
    	handlers.put("reportview", new ReportViewElementHandler());
    	handlers.put("reportviewcol", new ReportViewColElementHandler());
    	handlers.put("printformat", new PrintFormatElementHandler());
    	handlers.put("printformatitem", new PrintFormatItemElementHandler());
    	handlers.put("SQLStatement", new SQLStatementElementHandler());
    	handlers.put("reference", new ReferenceElementHandler());
    	handlers.put("referencelist", new ReferenceListElementHandler());
    	handlers.put("referencetable", new ReferenceTableElementHandler());
	}
	
    /**
     * 	Receive notification of the start of an element.
     *
     * 	@param uri namespace
     * 	@param localName simple name
     * 	@param qName qualified name
     * 	@param atts attributes
     * 	@throws org.xml.sax.SAXException
     */
	public void startElement (String uri, String localName, String qName, Attributes atts)
	throws org.xml.sax.SAXException {
		
		// Create the package log    	
		if (Start_Doc==0){
			init();
		}
		// Check namespace.
		String elementValue = null;
		if ("".equals (uri))
			elementValue = qName;
		else
			elementValue = uri + localName;
		
		// adempiereAD.	
		if (elementValue.equals("adempiereAD")) {		
			log.info("adempiereAD updateMode="+m_UpdateMode);
			adempiereAD = true;	    
			//Start package log
			hd_document.startElement("","","adempiereDocument",attsOut);
			hd_document.startElement("","","header",attsOut);		
			hd_document.characters((atts.getValue("Name")+" Install Log").toCharArray(),0,(atts.getValue("Name")+" Install Log").length());
			hd_document.endElement("","","header");
			hd_document.startElement("","","H3",attsOut);		
			hd_document.characters(("Package Name:" ).toCharArray(),0,("Package Name:" ).length());
			hd_document.endElement("","","H3");
			hd_document.startElement("","","packagename4log",attsOut);
			hd_document.characters(atts.getValue("Name").toCharArray(),0,atts.getValue("Name").length());
			hd_document.endElement("","","packagename4log");
			hd_document.startElement("","","H3",attsOut);		
			hd_document.characters(("Version:" ).toCharArray(),0,("Version:" ).length());
			hd_document.endElement("","","H3");
			hd_document.startElement("","","Version",attsOut);
			hd_document.characters(atts.getValue("Version").toCharArray(),0,atts.getValue("Version").length());
			hd_document.endElement("","","Version");
			hd_document.startElement("","","H3",attsOut);		
			hd_document.characters(("Package Install Date:" ).toCharArray(),0,("Package Install Date:" ).length());
			hd_document.endElement("","","H3");
			hd_document.startElement("","","installDate",attsOut);
			hd_document.characters(logDate.toCharArray(),0,logDate.length());
			hd_document.endElement("","","installDate");
			hd_document.startElement("","","H3",attsOut);		
			hd_document.characters(("Min. Compiere Version:" ).toCharArray(),0,("Min. Compiere Version:" ).length());
			hd_document.endElement("","","H3");
			hd_document.startElement("","","CompVer",attsOut);
			hd_document.characters(atts.getValue("CompVer").toCharArray(),0,atts.getValue("CompVer").length());
			hd_document.endElement("","","CompVer");
			hd_document.startElement("","","H3",attsOut);		
			hd_document.characters(("Min. Database Date:" ).toCharArray(),0,("Min. Database Date:" ).length());
			hd_document.endElement("","","H3");
			hd_document.startElement("","","DataBase",attsOut);
			hd_document.characters(atts.getValue("DataBase").toCharArray(),0,atts.getValue("DataBase").length());
			hd_document.endElement("","","DataBase");
			
			createImp_Sum_table ("AD_Package_Imp_Backup");
			createImp_Sum_table ("AD_Package_Imp");
			createImp_Sum_table ("AD_Package_Imp_Inst");
			createImp_Sum_table ("AD_Package_Imp_Detail");
			
			// Update Summary Package History Table
			String sql2 = "SELECT AD_PACKAGE_IMP_INST_ID FROM AD_PACKAGE_IMP_INST WHERE NAME ="
				+	"'" +  atts.getValue("Name")
				+	"' AND PK_VERSION ='" +  atts.getValue("Version") + "'";		
			int PK_preInstalled = DB.getSQLValue(m_trxName,sql2); 
			
			AD_Package_Imp_ID = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Package_Imp", null);
			
			StringBuffer sqlB = new StringBuffer ("Insert INTO AD_Package_Imp") 
					.append( "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " ) 
					.append( "AD_PACKAGE_IMP_ID, RELEASENO, PK_VERSION, VERSION " ) 
					.append( ", DESCRIPTION, NAME, CREATOR" ) 
					.append( ", CREATORCONTACT, CREATEDDATE,UPDATEDDATE,PK_STATUS)" )
					.append( "VALUES(" )
					.append( " "+ Env.getAD_Client_ID(m_ctx) )
					.append( ", "+ Env.getAD_Org_ID(m_ctx) )
					.append( ", "+ Env.getAD_User_ID(m_ctx) )
					.append( ", "+ Env.getAD_User_ID(m_ctx) )
					.append( ", " + AD_Package_Imp_ID ) 
					.append( ", '" + atts.getValue("CompVer") )
					.append( "', '" + atts.getValue("Version") )
					.append( "', '" + atts.getValue("DataBase") )
					.append( "', '" +  atts.getValue("Description").replaceAll("'","''").replaceAll(",","") )
					.append( "', '" +  atts.getValue("Name") )
					.append( "', '" + atts.getValue("creator") )
					.append( "', '" + atts.getValue("creatorcontact") )
					.append( "', '" + atts.getValue("createddate") )
					.append( "', '" + atts.getValue("updateddate") )
					.append( "', '" + PK_Status )
					.append( "')" );
			Env.getAD_User_ID(m_ctx);
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);		
			if (no == -1)
				log.info("Insert to Package import failed");
			
			if ( PK_preInstalled == -1){		
				AD_Package_Imp_Inst_ID = MSequence.getNextID (Env.getAD_Client_ID(m_ctx), "AD_Package_Imp_Inst", null);
				
				//Insert Package into package install log
				sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Inst") 
						.append( "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " ) 
						.append( "AD_PACKAGE_IMP_INST_ID, RELEASENO, PK_VERSION, VERSION " ) 
						.append( ", DESCRIPTION, NAME, CREATOR" ) 
						.append( ", CREATORCONTACT, CREATEDDATE,UPDATEDDATE,PK_STATUS)" )
						.append( "VALUES(" )
						.append( " "+ Env.getAD_Client_ID(m_ctx) )
						.append( ", "+ Env.getAD_Org_ID(m_ctx) )
						.append( ", "+ Env.getAD_User_ID(m_ctx) )
						.append( ", "+ Env.getAD_User_ID(m_ctx) )
						.append( ", " + AD_Package_Imp_Inst_ID ) 
						.append( ", '" + atts.getValue("CompVer") )
						.append( "', '" + atts.getValue("Version") )
						.append( "', '" + atts.getValue("DataBase") )
						.append( "', '" +  atts.getValue("Description").replaceAll("'","''").replaceAll(",","") )
						.append( "', '" +  atts.getValue("Name") )
						.append( "', '" + atts.getValue("creator") )
						.append( "', '" + atts.getValue("creatorcontact") )
						.append( "', '" + atts.getValue("createddate") )
						.append( "', '" + atts.getValue("updateddate") )
						.append( "', '" + PK_Status )
						.append( "')" );
				
				Env.getAD_User_ID(m_ctx);
				no = DB.executeUpdate (sqlB.toString(), m_trxName);		
				if (no == -1)
					log.info("Insert to Package List import failed");
			}
			else{
				//Update package list with package status
				AD_Package_Imp_Inst_ID = PK_preInstalled;
				sqlB = new StringBuffer ("UPDATE AD_Package_Imp_Inst "
						+ "SET PK_Status = '" + PK_Status 
						+ "' WHERE AD_Package_Imp_Inst_ID = "+AD_Package_Imp_Inst_ID);		
				no = DB.executeUpdate (sqlB.toString(), m_trxName);
				if (no == -1)
					log.info("Update to package summary failed");
			}
			Env.setContext(m_ctx, "AD_Package_Imp_ID", AD_Package_Imp_ID);
			Env.setContext(m_ctx, "UpdateMode", m_UpdateMode);
			Env.setContext(m_ctx, "TrxName", m_trxName);
			Env.setContext(m_ctx, "PackageDirectory", packageDirectory);
			m_ctx.put("Document", hd_document);
			m_ctx.put("DocumentAttributes", attsOut);
		}
		else if (elementValue.equals("menu")) {
			//defer
			Element e = new Element(uri, localName, qName, atts);
			menus.add(e);
		}
		else {
			Element e = new Element(uri, localName, qName, atts);
			stack.push(e);
			ElementHandler handler = handlers.get(elementValue);
			if (handler != null)
				handler.startElement(m_ctx, e);
			if (e.defer)
				defer.add(new DeferEntry(e, true));
		}	
	}   // startElement
    
	/**
     *	Check if Package History Table exists in database.  If not create
     *
     *      @param tablename
     *       	
     */
    public void createImp_Sum_table (String tablename){
    	// Check if table exists.
    	
    	Connection conn = DB.getConnectionRW();
    	DatabaseMetaData dbm;
    	try {
    		dbm = conn.getMetaData();
    		//    	 check if table is there
    		ResultSet tables = null;
    		if (m_DatabaseType.equals("Oracle"))
    			tables = dbm.getTables(null, null, tablename.toUpperCase(), null );
    		else if (m_DatabaseType.equals("PostgreSQL"))
    			tables = dbm.getTables(null, null, tablename.toLowerCase(), null );
    		
    		if (tables.next()) {
    			log.info ("Table Found");
    		}
    		else {        		
    			if (tablename.equals("AD_Package_Imp")){
    				StringBuffer sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( ")
    						.append( tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, " )
    						.append( "AD_CLIENT_ID NUMBER(10) NOT NULL, " )
    						.append( "AD_ORG_ID  NUMBER(10) NOT NULL, " )
    						.append( "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, " )
    						.append( "CREATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "CREATEDBY NUMBER(10) NOT NULL, " )
    						.append( "UPDATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "UPDATEDBY NUMBER(10) NOT NULL, " )
    						.append( "NAME NVARCHAR2(60) NOT NULL, " )
    						.append( "PK_STATUS NVARCHAR2(22), " )
    						.append( "RELEASENO NVARCHAR2(20), " )
    						.append( "PK_VERSION NVARCHAR2(20), " ) 
    						.append( "VERSION NVARCHAR2(20), " )
    						.append( "DESCRIPTION NVARCHAR2(1000) NOT NULL, " ) 
    						.append( "EMAIL NVARCHAR2(60), " )
    						.append( "PROCESSED CHAR(1) DEFAULT 'N', " )
    						.append( "PROCESSING CHAR(1) DEFAULT 'N', " )
    						.append( "CREATOR VARCHAR2(60 ), " ) 
    						.append( "CREATORCONTACT VARCHAR2(255), " ) 
    						.append( " CREATEDDATE  VARCHAR2(25), " ) 
    						.append( "UPDATEDDATE VARCHAR2(25), " )					 
    						.append( "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        		
    				
    				try {
    					PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
    							ResultSet.CONCUR_UPDATABLE, null);
    					pstmt.executeUpdate();
    					MSequence.createTableSequence (m_ctx, "AD_Package_Imp", m_trxName);
    					pstmt.close();
    					pstmt = null;
    				}
    				catch (Exception e) {
    					log.info ("createImp_Sum_table:"+e);
    				}
    			}
    			if (tablename.equals("AD_Package_Imp_Inst")){
    				StringBuffer sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( ")
    						.append( tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, " )
    						.append( "AD_CLIENT_ID NUMBER(10) NOT NULL, " )
    						.append( "AD_ORG_ID  NUMBER(10) NOT NULL, " )
    						.append( "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, " )
    						.append( "CREATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "CREATEDBY NUMBER(10) NOT NULL, " )
    						.append( "UPDATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "UPDATEDBY NUMBER(10) NOT NULL, " )
    						.append( "NAME NVARCHAR2(60) NOT NULL, " )
    						.append( "PK_STATUS NVARCHAR2(22), " )
    						.append( "RELEASENO NVARCHAR2(20), " )
    						.append( "PK_VERSION NVARCHAR2(20), " ) 
    						.append( "VERSION NVARCHAR2(20), " )
    						.append( "DESCRIPTION NVARCHAR2(1000) NOT NULL, " ) 
    						.append( "EMAIL NVARCHAR2(60), " ) 
    						.append( "PROCESSED CHAR(1) DEFAULT 'N', " )
    						.append( "PROCESSING CHAR(1) DEFAULT 'N', " )
    						.append( "CREATOR VARCHAR2(60 ), " ) 
    						.append( "CREATORCONTACT VARCHAR2(255), " ) 
    						.append( " CREATEDDATE  VARCHAR2(25), " ) 
    						.append( "UPDATEDDATE VARCHAR2(25), " )					 
    						.append( "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        		
    				
    				try {
    					PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
    							ResultSet.CONCUR_UPDATABLE, null);
    					pstmt.executeUpdate();
    					MSequence.createTableSequence (m_ctx, "AD_Package_Imp_Inst", m_trxName);
    					pstmt.close();
    					pstmt = null;
    				}
    				catch (Exception e) {
    					log.info ("createImp_Sum_table:"+e);
    				}
    			}
    			if (tablename.equals("AD_Package_Imp_Detail")){
    				StringBuffer sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( ")
    						.append( tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, " )
    						.append( "AD_CLIENT_ID NUMBER(10) NOT NULL, " )
    						.append( "AD_ORG_ID  NUMBER(10) NOT NULL, " )
    						.append( "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, " )
    						.append( "CREATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "CREATEDBY NUMBER(10) NOT NULL, " )
    						.append( "UPDATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "UPDATEDBY NUMBER(10) NOT NULL, " )
    						.append( "NAME NVARCHAR2(60), " )
    						.append( "AD_PACKAGE_IMP_ID Number(10) NOT NULL, " )  
    						.append( "AD_ORIGINAL_ID Number(10) NOT NULL, " )
    						.append( "AD_BACKUP_ID Number(10), " )
    						.append( "ACTION NVARCHAR2(20), " ) 
    						.append( "SUCCESS NVARCHAR2(20), " )
    						.append( "TYPE NVARCHAR2(60), " ) 
    						.append( "TABLENAME NVARCHAR2(60), " )
    						.append( "AD_TABLE_ID NUMBER(10), " )
    						.append( "UNINSTALL CHAR(1), " )
    						.append( "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        		
    				
    				try {
    					PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
    							ResultSet.CONCUR_UPDATABLE, null);
    					pstmt.executeUpdate();
    					MSequence.createTableSequence (m_ctx, "AD_Package_Imp_Detail", m_trxName);
    					pstmt.close();
    					pstmt = null;
    				}
    				catch (Exception e) {
    					log.info ("createImp_Sum_table:"+e);
    				}
    			}
    			if (tablename.equals("AD_Package_Imp_Backup")){
    				StringBuffer sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( ")
    						.append( tablename.toUpperCase()+"_ID NUMBER(10) NOT NULL, " )
    						.append( "AD_CLIENT_ID NUMBER(10) NOT NULL, " )
    						.append( "AD_ORG_ID  NUMBER(10) NOT NULL, " )
    						.append( "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, " )
    						.append( "CREATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "CREATEDBY NUMBER(10) NOT NULL, " )
    						.append( "UPDATED DATE DEFAULT SYSDATE NOT NULL, " )
    						.append( "UPDATEDBY NUMBER(10) NOT NULL, " )        				 
    						.append( "AD_PACKAGE_IMP_ID Number(10) NOT NULL, " )
    						.append( "AD_PACKAGE_IMP_DETAIL_ID Number(10) NOT NULL, " )    					 
    						.append( "AD_TABLE_ID NUMBER(10), " )
    						.append( "AD_COLUMN_ID NUMBER(10), " )
    						.append( "AD_REFERENCE_ID NUMBER(10), " )
    						.append( "AD_PACKAGE_IMP_BCK_DIR NVARCHAR2(255), " )
    						.append( "AD_PACKAGE_IMP_ORG_DIR NVARCHAR2(255), " )
    						.append( "COLVALUE NVARCHAR2(2000), " )
    						.append( "UNINSTALL CHAR(1), " )
    						.append( "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        	
    				
    				try {
    					PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
    							ResultSet.CONCUR_UPDATABLE, null);
    					pstmt.executeUpdate();
    					MSequence.createTableSequence (m_ctx, "AD_Package_Imp_Backup", m_trxName);
    					pstmt.close();
    					pstmt = null;
    				}	
    				catch (Exception e) {
    					log.info ("createImp_Sum_table:"+e);
    				}
    			}	
    		}
    		
    		tables.close();
    	}
    	
    	catch (SQLException e) {
    		log.info ("createImp_Sum_table:"+e);
    	}
    	
    	finally
    	{
    		if( conn != null )
    		{
    			try
    			{
    				conn.close();
    			}
    			catch( Exception e ){}
    		}
    	}
    }

    /**
     *	Receive notification of the end of an element.
     * 	@param uri namespace
     * 	@param localName simple name
     * 	@param qName qualified name
     * 	@throws SAXException
     */
    public void endElement (String uri, String localName, String qName) throws SAXException {
    	// Check namespace.
    	String elementValue = null;
    	if ("".equals (uri))
    		elementValue = qName;
    	else
    		elementValue = uri + localName;
    	
    	if (elementValue.equals("adempiereAD")){
    		processDeferElements();
    		processMenuElements();
    		if (!PK_Status.equals("Completed with errors"))
    			PK_Status = "Completed successfully";
    		
    		//Update package history log with package status
    		StringBuffer sqlB = new StringBuffer ("UPDATE AD_Package_Imp "
    				+ "SET PK_Status = '" + PK_Status
    				+ "' WHERE AD_Package_Imp_ID = " + AD_Package_Imp_ID);		
    		int no = DB.executeUpdate (sqlB.toString(), m_trxName);
    		if (no == -1)
    			log.info("Update to package summary failed");
    		
    		//Update package list with package status		
    		sqlB = new StringBuffer ("UPDATE AD_Package_Imp_Inst "
    				+ "SET PK_Status = '" + PK_Status
    				+ "' WHERE AD_Package_Imp_Inst_ID = " + AD_Package_Imp_Inst_ID);		
    		no = DB.executeUpdate (sqlB.toString(), m_trxName);
    		if (no == -1)
    			log.info("Update to package list failed");
    		
    		hd_document.endElement("","","adempiereDocument");
    		hd_document.endDocument();	
    		try {
    			fw_document.close();
    		}
    		catch (Exception e)
    		{}
    	} else {
    		Element e = stack.pop();
    		ElementHandler handler = handlers.get(elementValue);
    		if (handler != null)
    			handler.endElement(m_ctx, e);
    		if (e.defer)
				defer.add(new DeferEntry(e, false));
    	}
    }   // endElement
    
    private void processMenuElements() throws SAXException {
    	ElementHandler handler = handlers.get("menu");
		if (menus.size() > 0 && handler != null) {
			for (Element e : menus) {
				handler.startElement(m_ctx, e);
				handler.endElement(m_ctx, e);
			}
		}
	}
    
    private void processDeferElements() throws SAXException {
    	if (defer.isEmpty()) return;
    	
    	do {
    		int startSize = defer.size();
    		List<DeferEntry> tmp = new ArrayList<DeferEntry>(defer);
    		defer.clear();
    		for (DeferEntry d : tmp) {
    			d.element.defer = false;
    			ElementHandler handler = handlers.get(d.element.getElementValue());
    			if (handler != null) {
    				if (d.startElement)
    					handler.startElement(m_ctx, d.element);
    				else
    					handler.endElement(m_ctx, d.element);
    			}
    			if (d.element.defer)
    				defer.add(d);
    		}
    		int endSize = defer.size();
    		if (startSize == endSize) break;
    	} while (defer.size() > 0);
    }

	// globalqss - add support for trx in 3.1.2
	public void set_TrxName(String trxName) {
		m_trxName = trxName;
	}
    
    // globalqss - add support for trx in 3.1.2
	public void setCtx(Properties ctx) {
		m_ctx = ctx;
	}

	class DeferEntry {
		Element element;
		boolean startElement = false;
		
		DeferEntry(Element e, boolean b) {
			element = e;
			startElement = b;
		}
	}
}   // PackInHandler
