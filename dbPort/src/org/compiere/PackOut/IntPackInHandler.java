/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *
 * Copyright (C) 2004 Marco LOMBARDO. lombardo@mayking.com
 * Contributor(s): Robert KLEIN. robeklein@hotmail.com
 *_____________________________________________
 *****************************************************************************/

package org.compiere.PackOut;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.sql.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.*;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.compiere.Adempiere;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.X_AD_Column;
import org.compiere.model.X_AD_Element;
import org.compiere.model.X_AD_Field;
import org.compiere.model.X_AD_Menu;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_AD_Process_Para;
import org.compiere.model.X_AD_PrintFormatItem;
import org.compiere.model.X_AD_PrintFormat;
import org.compiere.model.X_AD_ImpFormat;
import org.compiere.model.X_AD_ImpFormat_Row;
import org.compiere.model.X_AD_ReportView;
import org.compiere.model.X_AD_ReportView_Col;
import org.compiere.model.X_AD_Tab;
import org.compiere.model.X_AD_Table;
import org.compiere.model.X_AD_Window;
import org.compiere.model.X_AD_Preference;
import org.compiere.model.X_AD_Task;
import org.compiere.model.X_AD_Form;
import org.compiere.model.X_AD_Workbench;
import org.compiere.model.X_AD_WorkbenchWindow;
import org.compiere.model.X_AD_Reference;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AD_Role;
import org.compiere.model.MSequence;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.CLogger;
import org.xml.sax.Attributes;

import org.xml.sax.helpers.DefaultHandler;



/**
 * SAX Handler for parsing XML description of the GUI.
 *
 * @author Marco LOMBARDO, lombardo@mayking.com
 * @author Robert KLEIN, robeklein@hotmail
 * 
 */
public class IntPackInHandler extends DefaultHandler {

    /**
     * 	PackInHandler Handler
     */
    public IntPackInHandler () {
    	
    }   // PackInHandler    
    private X_AD_Menu m_Menu;    
    private X_AD_Window m_Window = null;
    private X_AD_Preference m_Preference = null;
    private X_AD_Process m_Process = null;
    private X_AD_Process_Para m_Process_para = null;
    private X_AD_Task m_Task = null;    
    private X_AD_Form m_Form = null;
    private X_AD_Workbench m_Workbench = null;
    private X_AD_WorkbenchWindow m_Workbenchwindow = null;
    private X_AD_Reference m_Reference = null;
    private X_AD_Ref_List m_Ref_List = null;    
    private X_AD_PrintFormatItem m_PrintFormatItem = null;
    private X_AD_PrintFormat m_PrintFormat = null; 
    private X_AD_ImpFormat m_ImpFormat = null;
    private X_AD_ImpFormat_Row m_ImpFormat_row = null;
    private X_AD_ReportView m_Reportview = null;
    private X_AD_ReportView_Col m_Reportview_Col = null;
    private X_AD_Table m_Table = null;
    private X_AD_Role m_Role = null;
    private X_AD_Column m_Column = null;    
    private X_AD_Tab m_Tab;   
    private X_AD_Field m_Field = null;
    private String d_menu[][] = new String [100][16];
    private int menu_seq=0;    
    /** Original Values         */
	public Object[]    		m_oldValue = null;
	/** New Valies              */
	public Object[]    		m_newValue = null;
    /** Set this if you want to update Dictionary  */
    public String m_UpdateMode = "true";
    String packageDirectory = null;
    public String m_DatabaseType = "Oracle";
    private boolean adempiereAD = false;    
    private boolean adempieredata = false;    
    private String d_tablename = null;
    private String d_rowname = null;
    private IntGenericPO genericPO = null;
    private HashMap defaults = new HashMap();
    StringBuffer sqlB = null;        
    private int m_AD_Client_ID = 0;
    int AD_Package_Imp_ID=0;
	int AD_Package_Imp_Inst_ID=0;
    int AD_Backup_ID =0;
    private CLogger log = CLogger.getCLogger("PackIn");
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
	boolean force = true;  // This is set to true if the "-f" option
    //    is specified on the command line.
	String fileSeperator=null;
	// transaction name 
	private	String 		m_trxName = null;

	
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
    	
//    	Create the package log    	
    	if (Start_Doc==0){
    	IntPackIn pack = new IntPackIn();
    	packageDirectory = pack.m_Package_Dir;
    	m_UpdateMode = pack.m_UpdateMode;
    	m_DatabaseType = pack.m_Database;    	
		File file = new File("");
		fileSeperator = file.separator;	
    	SimpleDateFormat formatter_file = new SimpleDateFormat("yyMMddHHmmssZ");
    	SimpleDateFormat formatter_log = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
    	Date today = new Date();
    	fileDate = formatter_file.format(today);
    	logDate = formatter_log.format(today);
		
    	String file_document = packageDirectory+fileSeperator+"doc"+fileSeperator+"Importlog_"+fileDate+".xml";		
    	log.info(file_document);
    	try {
    		fw_document = new FileOutputStream (file_document, false);
    	} catch (FileNotFoundException e1) {
    		log.info ("startElement:"+e1);
    	}
		streamResult_document = new StreamResult(fw_document);		
		tf_document = (SAXTransformerFactory) SAXTransformerFactory.newInstance();	
		
		try {
			hd_documemt = tf_document.newTransformerHandler();
		} catch (TransformerConfigurationException e2) {
			log.info ("startElement:"+e2);
		}		
		serializer_document = hd_documemt.getTransformer();		
		serializer_document.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
		serializer_document.setOutputProperty(OutputKeys.INDENT,"yes");		
		hd_documemt.setResult(streamResult_document);				
		hd_documemt.startDocument();		
		attsOut = new AttributesImpl();		
		attsOut.clear();		
		hd_documemt.processingInstruction("xml-stylesheet","type=\"text/css\" href=\"compiereDocument.css\"");
		Start_Doc=1;
    }
	// Check namespace.
	String elementValue = null;
	if ("".equals (uri))
	    elementValue = qName;
	else
	    elementValue = uri + localName;
	if (false)
	    log.info("startElement: "+elementValue);
	
	// adempiereAD.	
	if (elementValue.equals("adempiereAD")) {		
		log.info(m_UpdateMode);
	    adempiereAD = true;	    
	    //Start package log
	    hd_documemt.startElement("","","compiereDocument",attsOut);
		hd_documemt.startElement("","","header",attsOut);		
		hd_documemt.characters((atts.getValue("PK_Name")+" Install Log").toCharArray(),0,(atts.getValue("PK_Name")+" Install Log").length());
		hd_documemt.endElement("","","header");
		hd_documemt.startElement("","","H3",attsOut);		
		hd_documemt.characters(("Package Name:" ).toCharArray(),0,("Package Name:" ).length());
		hd_documemt.endElement("","","H3");
		hd_documemt.startElement("","","packagename4log",attsOut);
		hd_documemt.characters(atts.getValue("PK_Name").toCharArray(),0,atts.getValue("PK_Name").length());
		hd_documemt.endElement("","","packagename4log");
		hd_documemt.startElement("","","H3",attsOut);		
		hd_documemt.characters(("Version:" ).toCharArray(),0,("Version:" ).length());
		hd_documemt.endElement("","","H3");
		hd_documemt.startElement("","","Version",attsOut);
		hd_documemt.characters(atts.getValue("Version").toCharArray(),0,atts.getValue("Version").length());
		hd_documemt.endElement("","","Version");
		hd_documemt.startElement("","","H3",attsOut);		
		hd_documemt.characters(("Package Install Date:" ).toCharArray(),0,("Package Install Date:" ).length());
		hd_documemt.endElement("","","H3");
		hd_documemt.startElement("","","installDate",attsOut);
		hd_documemt.characters(logDate.toCharArray(),0,logDate.length());
		hd_documemt.endElement("","","installDate");
		hd_documemt.startElement("","","H3",attsOut);		
		hd_documemt.characters(("Min. Compiere Version:" ).toCharArray(),0,("Min. Compiere Version:" ).length());
		hd_documemt.endElement("","","H3");
		hd_documemt.startElement("","","CompVer",attsOut);
		hd_documemt.characters(atts.getValue("CompVer").toCharArray(),0,atts.getValue("CompVer").length());
		hd_documemt.endElement("","","CompVer");
		hd_documemt.startElement("","","H3",attsOut);		
		hd_documemt.characters(("Min. Database Date:" ).toCharArray(),0,("Min. Database Date:" ).length());
		hd_documemt.endElement("","","H3");
		hd_documemt.startElement("","","DataBase",attsOut);
		hd_documemt.characters(atts.getValue("DataBase").toCharArray(),0,atts.getValue("DataBase").length());
		hd_documemt.endElement("","","DataBase");
	
		createImp_Sum_table ("AD_Package_Imp_Backup");
		createImp_Sum_table ("AD_Package_Imp");
		createImp_Sum_table ("AD_Package_Imp_Inst");
		createImp_Sum_table ("AD_Package_Imp_Detail");
		
		
		// Update Summary Package History Table
		String sql2 = "SELECT AD_PACKAGE_IMP_INST_ID FROM AD_PACKAGE_IMP_INST WHERE NAME ="
			+	"'" +  atts.getValue("PK_Name")
			+	"' AND PK_VERSION ='" +  atts.getValue("Version") + "'";		
		int PK_preInstalled = DB.getSQLValue(null,sql2); 
 
		
		AD_Package_Imp_ID = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp", null);
		
		sqlB = new StringBuffer ("Insert INTO AD_Package_Imp" 
				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
				+   "AD_PACKAGE_IMP_ID, RELEASENO, PK_VERSION, VERSION " 
				+   ", DESCRIPTION, NAME, CREATOR" 
				+	", CREATORCONTACT, CREATEDDATE,UPDATEDDATE,PK_STATUS)"
				+	"VALUES("
				+	" "+ Env.getAD_Client_ID(Env.getCtx())
				+	", "+ Env.getAD_Org_ID(Env.getCtx())
				+	", "+ Env.getAD_User_ID(Env.getCtx())
				+	", "+ Env.getAD_User_ID(Env.getCtx())
				+	", " + AD_Package_Imp_ID 
				+	", '" + atts.getValue("CompVer")
				+	"', '" + atts.getValue("Version")
				+	"', '" + atts.getValue("DataBase")
				+	"', '" +  atts.getValue("Description").replaceAll("'","''").replaceAll(",","")
				+	"', '" +  atts.getValue("PK_Name")
				+	"', '" + atts.getValue("creator")
				+	"', '" + atts.getValue("creatorcontact")
				+	"', '" + atts.getValue("createddate")
				+	"', '" + atts.getValue("updateddate")
				+	"', '" + PK_Status
				+"')");
		Env.getAD_User_ID(Env.getCtx());
		int no = DB.executeUpdate (sqlB.toString(), m_trxName);		
		if (no == -1)
			log.info("Insert to Package import failed");
		
		
		if ( PK_preInstalled == -1){		
			AD_Package_Imp_Inst_ID = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Inst", null);
			
			//Insert Package into package install log
			sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Inst" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_PACKAGE_IMP_INST_ID, RELEASENO, PK_VERSION, VERSION " 
					+   ", DESCRIPTION, NAME, CREATOR" 
					+	", CREATORCONTACT, CREATEDDATE,UPDATEDDATE,PK_STATUS)"
					+	"VALUES("
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
					+	", "+ Env.getAD_Org_ID(Env.getCtx())
					+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + AD_Package_Imp_Inst_ID 
					+	", '" + atts.getValue("CompVer")
					+	"', '" + atts.getValue("Version")
					+	"', '" + atts.getValue("DataBase")
					+	"', '" +  atts.getValue("Description").replaceAll("'","''").replaceAll(",","")
					+	"', '" +  atts.getValue("PK_Name")
					+	"', '" + atts.getValue("creator")
					+	"', '" + atts.getValue("creatorcontact")
					+	"', '" + atts.getValue("createddate")
					+	"', '" + atts.getValue("updateddate")
					+	"', '" + PK_Status
					+"')");
			
			Env.getAD_User_ID(Env.getCtx());
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

	}
	// adempieredata element.
	// some fields i.e. "table" are commons.
	else if (elementValue.equals("adempieredata") || elementValue.equals("data")) {
	    adempieredata = true;
	    if (atts.getValue("clientname") != null) {
	    	m_AD_Client_ID = get_ID("AD_Client", atts.getValue("clientname"));
	    	Env.setContext(Env.getCtx(), "AD_Client_ID", m_AD_Client_ID);
	    	log.info("adempieredata: client set to "+m_AD_Client_ID+" "+atts.getValue("clientname"));
	    }
	}
	else if (elementValue.equals("menu")) {	 
	    //String entitytype = atts.getValue("EntityType");
	    //if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode == true ) {
		
		    d_menu[menu_seq][0] = atts.getValue("ADMenuNameID");		    
		    d_menu[menu_seq][1] = atts.getValue("ADWindowNameID");
		    d_menu[menu_seq][2] = atts.getValue("ADProcessNameID");
		    d_menu[menu_seq][3] = atts.getValue("ADFormNameID");
		    d_menu[menu_seq][4] = atts.getValue("ADTaskNameID");
		    d_menu[menu_seq][5] = atts.getValue("ADWorkbenchNameID");
		    d_menu[menu_seq][6] = atts.getValue("ADWorkflowNameID");
		    d_menu[menu_seq][7] = (atts.getValue("Action") != null ? atts.getValue("Action") : " ");
		    d_menu[menu_seq][8] = atts.getValue("Description").replaceAll("'","''").replaceAll(",","");
		    d_menu[menu_seq][9] = atts.getValue("EntityType");
		    d_menu[menu_seq][10] = atts.getValue("isReadOnly");
		    d_menu[menu_seq][11] = atts.getValue("isSOTrx");
		    d_menu[menu_seq][12] = atts.getValue("isSummary");
		    d_menu[menu_seq][13] = atts.getValue("ADParentMenuNameID");
		    d_menu[menu_seq][14] = atts.getValue("ADParentSeqno");
		    d_menu[menu_seq][15] = atts.getValue("isActive");
		    menu_seq=menu_seq+1;
	    //}  
	}
	// window element.
	else if (elementValue.equals("window")) {		
		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			String name = atts.getValue("Name");			
			int id= get_ID("AD_Window", name);
			m_Window = new X_AD_Window(Env.getCtx(), id,null);
			if (id > 0){			
				AD_Backup_ID = copyRecord("AD_Window",m_Window);
		    	Object_Status = "Update";	    	
			}
		    else{
		    	 Object_Status = "New";
		    	 AD_Backup_ID =0;
		    }
			m_Window.setName(name);	
			name = atts.getValue("ADImageNameID");	    
		    id = get_IDWithColumn("AD_Image", "Name", name);
		    m_Window.setAD_Image_ID(id);
		    name = atts.getValue("ADColorNameID");	    
		    id = get_IDWithColumn("AD_Color", "Name", name);
		    m_Window.setAD_Color_ID(id); 
	        m_Window.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Window.setEntityType(atts.getValue("EntityType"));
	        m_Window.setHelp (atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
	        m_Window.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Window.setIsBetaFunctionality(Boolean.valueOf(atts.getValue("isBetaFunctionality")).booleanValue());
	        m_Window.setIsDefault(Boolean.valueOf(atts.getValue("isDefault")).booleanValue());
	        m_Window.setIsSOTrx(Boolean.valueOf(atts.getValue("isSOTrx")).booleanValue());
	        m_Window.setName (atts.getValue("Name"));
	        m_Window.setProcessing (false);
	        //m_Window.setWinHeight(Integer.parseInt(atts.getValue("WinHeight")));
	        //m_Window.setWinWidth (Integer.parseInt(atts.getValue("WinWidth")));
	        m_Window.setWindowType (atts.getValue("WindowType"));
	        m_Window.save(m_trxName);
	        if (m_Window.save(m_trxName) == true){
		    	record_log (1, m_Window.getName(),"Window", m_Window.get_ID(),AD_Backup_ID, Object_Status,"AD_Window",get_IDWithColumn("AD_Table", "TableName", "AD_Window"));           		        		
            }
            else{
            	record_log (0, m_Window.getName(),"Window", m_Window.get_ID(),AD_Backup_ID,  Object_Status,"AD_Window",get_IDWithColumn("AD_Table", "TableName", "AD_Window"));
            }
		}
	}
//	 preference element.
	else if (elementValue.equals("preference")) {
		//TODO Add User_ID
		int windowid = get_ID("AD_Window", atts.getValue("ADWindowNameID"));		
		sqlB = new StringBuffer ("select AD_Preference_ID from AD_Preference where "
				  + " Attribute = '"+atts.getValue("Attribute") +"'"
				  + " and AD_Window_ID = ?");
		int id = DB.getSQLValue(null, sqlB.toString (), windowid);
		m_Preference = new X_AD_Preference(Env.getCtx(), id,null);
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
		m_Preference.setAttribute(atts.getValue("Attribute"));
		m_Preference.setValue(atts.getValue("Value"));
		if (m_Preference.save(m_trxName) == true){		    	
	    	record_log (1, m_Preference.getAttribute(),"Preference", m_Preference.get_ID(),AD_Backup_ID, Object_Status,"AD_Preference",get_IDWithColumn("AD_Table", "TableName", "AD_Preference"));           		        		
        }
        else{
        	record_log (0, m_Preference.getAttribute(),"Preference", m_Preference.get_ID(),AD_Backup_ID, Object_Status,"AD_Preference",get_IDWithColumn("AD_Table", "TableName", "AD_Preference"));
        }
	}
	// tab element.
	else if (elementValue.equals("tab")) {
		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0) {
			
			String name = atts.getValue("ADTabNameID");
			int tableid = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));
			int windowid = get_ID("AD_Window", atts.getValue("ADWindowNameID"));
			sqlB = new StringBuffer ("select AD_Tab_ID from AD_Tab where AD_Window_ID = " + windowid
					  + " and Name = '"+name +"'"
					  + " and AD_Table_ID = ?");
			
			int id = DB.getSQLValue(null, sqlB.toString (), tableid);
			m_Tab = new X_AD_Tab(Env.getCtx(), id,null);
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
			if (atts.getValue("ADColumnSortYesNoNameID")!= null){
		    name = atts.getValue("ADColumnSortYesNoNameID");	    
		    id = get_IDWithColumn("AD_Column", "Name", name);
		    m_Tab.setAD_ColumnSortYesNo_ID(id);
			}
			if (atts.getValue("ADColumnSortOrderNameID")!= null){
		    name = atts.getValue("ADColumnSortOrderNameID");	    
		    id = get_IDWithColumn("AD_Column", "Name", name);
		    m_Tab.setAD_ColumnSortOrder_ID(id);
			}
		    if (atts.getValue("ADImageNameID")!= null){
		    name = atts.getValue("ADImageNameID");	    
		    id = get_IDWithColumn("AD_Image", "Name", name);
		    m_Tab.setAD_Image_ID(id);
		    }
		    if (atts.getValue("ADProcessNameID")!= null){
		    name = atts.getValue("ADProcessNameID");	    
		    id = get_IDWithColumn("AD_Process", "Name", name);
		    m_Tab.setAD_Process_ID(id);
		    }		    
		    if (atts.getValue("ADTableNameID")!= null){
		    name = atts.getValue("ADTableNameID");	    
		    id = get_IDWithColumn("AD_Table", "TableName", name);
		    m_Tab.setAD_Table_ID(id);   
		    }
		    if (atts.getValue("ADColumnNameID")!= null){
		    name = atts.getValue("ADColumnNameID");
		    id  = get_IDWithMasterAndColumn ("AD_Column","Name", atts.getValue("ADColumnNameID"), "AD_Table", get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID")));			    
		    m_Tab.setAD_Column_ID(id);   
			}
		    if (atts.getValue("ADWindowNameID")!= null){
		    name = atts.getValue("ADWindowNameID");	    
		    id = get_IDWithColumn("AD_Window", "Name", name);
		    m_Tab.setAD_Window_ID(id);   
		    }
		    if (atts.getValue("IncludedTabNameID")!= null){
		    name = atts.getValue("IncludedTabNameID");	    
		    id = get_IDWithColumn("AD_Tab", "Name", name);
		    m_Tab.setIncluded_Tab_ID(id);		        
		    }
	        m_Tab.setCommitWarning(atts.getValue("CommitWarning"));
	        m_Tab.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Tab.setEntityType (atts.getValue("EntityType"));
	        m_Tab.setHasTree(Boolean.valueOf(atts.getValue("isHasTree")).booleanValue());
	        m_Tab.setHelp (atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
	        m_Tab.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Tab.setImportFields (atts.getValue("ImportFields"));
	        m_Tab.setIsInfoTab (Boolean.valueOf(atts.getValue("isInfoTab")).booleanValue());
	        m_Tab.setIsReadOnly (Boolean.valueOf(atts.getValue("isReadOnly")).booleanValue());
	        m_Tab.setIsSingleRow (Boolean.valueOf(atts.getValue("isSingleRow")).booleanValue());
	        m_Tab.setIsSortTab (Boolean.valueOf(atts.getValue("isSortTab")).booleanValue());
	        m_Tab.setIsTranslationTab (Boolean.valueOf(atts.getValue("IsTranslationTab")).booleanValue());
	        m_Tab.setName (atts.getValue("Name"));
	        m_Tab.setOrderByClause (atts.getValue("OrderByClause"));
	        m_Tab.setProcessing(false);
	        m_Tab.setSeqNo (Integer.parseInt(atts.getValue("SeqNo")));
	        m_Tab.setTabLevel (Integer.parseInt(atts.getValue("TabLevel")));
	        m_Tab.setWhereClause (atts.getValue("WhereClause"));
	        if (m_Tab.save(m_trxName) == true){		    	
		    	record_log (1, m_Tab.getName(),"Tab", m_Tab.get_ID(),AD_Backup_ID, Object_Status,"AD_Tab",get_IDWithColumn("AD_Table", "TableName", "AD_Tab"));           		        		
            }
            else{
            	record_log (0, m_Tab.getName(),"Tab", m_Tab.get_ID(),AD_Backup_ID, Object_Status,"AD_Tab",get_IDWithColumn("AD_Table", "TableName", "AD_Tab"));
            }

		}			
	} 		
	// field element.
	else if (elementValue.equals("field")) {
		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0) {
			String name = atts.getValue("Name");
			String tabname = atts.getValue("ADTabNameID");
			String colname = atts.getValue("ADColumnNameID");
//log.info("Column Name ->"+colname);
//log.info("Database Name ->"+m_DatabaseType);
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
log.info("After Column Name ->"+colname);		}
**/
			int tableid = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));			
			int windowid = get_ID("AD_Window", atts.getValue("ADWindowNameID"));
//log.info("Column Name ->"+colname);		
			int columnid  = get_IDWithMasterAndColumn ("AD_Column","ColumnName", colname, "AD_Table", tableid);
//log.info("ColumnID->"+columnid);
			sqlB = new StringBuffer ("select AD_Tab_ID from AD_Tab where AD_Window_ID = " + windowid
					  + " and Name = '"+tabname +"'"
					  + " and AD_Table_ID = ?");			
			int tabid = DB.getSQLValue(null, sqlB.toString (), tableid);			
			sqlB = new StringBuffer ("select AD_Field_ID from AD_Field where AD_Column_ID = "+columnid
					  + " and AD_Tab_ID = ?");
			int id = DB.getSQLValue(null, sqlB.toString (), tabid);
			m_Field = new X_AD_Field(Env.getCtx(),id,null);
			if (id > 0){			
				AD_Backup_ID = copyRecord("AD_Field",m_Field);
				Object_Status = "Update";			
			}
		    else{
		    	 Object_Status = "New";
		    	 AD_Backup_ID =0;
		    }
			m_Field.setName(atts.getValue("Name"));
			m_Field.setAD_Column_ID(columnid);
		    name = atts.getValue("ADFieldGroupNameID");	    
		    id = get_IDWithColumn("AD_FieldGroup", "Name", name);
		    m_Field.setAD_FieldGroup_ID(id);		    
		    m_Field.setAD_Tab_ID(tabid);	        
		    m_Field.setEntityType (atts.getValue("EntityType"));
	        m_Field.setIsSameLine(Boolean.valueOf(atts.getValue("SameLine")).booleanValue());
	        m_Field.setIsCentrallyMaintained(Boolean.valueOf(atts.getValue("isCentrallyMaintained")).booleanValue());
	        m_Field.setIsDisplayed(Boolean.valueOf(atts.getValue("Displayed")).booleanValue());	        
	        //m_Field.setIsEncrypted(Boolean.valueOf(atts.getValue("isEncrypted")).booleanValue());
	        m_Field.setIsFieldOnly(Boolean.valueOf(atts.getValue("isFieldOnly")).booleanValue());
	        m_Field.setIsHeading(Boolean.valueOf(atts.getValue("isHeading")).booleanValue());
	        m_Field.setIsReadOnly(Boolean.valueOf(atts.getValue("isReadOnly")).booleanValue());	        
	        m_Field.setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
	        m_Field.setDisplayLength(Integer.parseInt(atts.getValue("DisplayLength")));
	        m_Field.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Field.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
	        m_Field.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);	        
	        m_Field.setSortNo(new BigDecimal(atts.getValue("SortNo")));
	        m_Field.setDisplayLogic(atts.getValue("DisplayLogic"));
	        if (m_Field.save(m_trxName) == true){		    	
		    	record_log (1, m_Field.getName(),"Field", m_Field.get_ID(),AD_Backup_ID, Object_Status,"AD_Field",get_IDWithColumn("AD_Table", "TableName", "AD_Field"));           		        		
            }
            else{
            	record_log (0, m_Field.getName(),"Field", m_Field.get_ID(),AD_Backup_ID, Object_Status,"AD_Field",get_IDWithColumn("AD_Table", "TableName", "AD_Field"));
            }

		}	
    }
	// process element.
	else if (elementValue.equals("process")) {
		int id = 0;
		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			String name = atts.getValue("Name");
			
			
		    // Get New process.
			id=get_ID("AD_Process", name);				
						
			if (id > 0){
				m_Process = new X_AD_Process(Env.getCtx(), id,null);				
				AD_Backup_ID = copyRecord("AD_Process",m_Process);
				Object_Status = "Update";			
			}
			else{				
				m_Process = new X_AD_Process(Env.getCtx(), id,null);
				id = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Process", null);
				m_Process.setAD_Process_ID(id);				
	    		Object_Status = "New";
	    		AD_Backup_ID =0;
			}
			m_Process.setName(name);
			name = atts.getValue("ADWorkflowNameID");	    
		    id = get_IDWithColumn("AD_Workflow", "Name", name);
		    
		    m_Process.setAD_Workflow_ID(id);
			name = atts.getValue("ADProcessNameID");		    
			
			name = atts.getValue("ADPrintFormatNameID");	    
		    id = get_IDWithColumn("AD_PrintFormat", "Name", name);
		    m_Process.setAD_PrintFormat_ID(id);    
		    name = atts.getValue("ADReportViewNameID");	    
		    id = get_IDWithColumn("AD_ReportView", "Name", name);
		    m_Process.setAD_ReportView_ID(id); 
		    m_Process.setAccessLevel(atts.getValue("AccessLevel"));
			m_Process.setClassname(atts.getValue("Classname"));			
			m_Process.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
			m_Process.setEntityType(atts.getValue("EntityType"));
			m_Process.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
			m_Process.setIsBetaFunctionality(Boolean.valueOf(atts.getValue("isBetaFunctionality")).booleanValue());
			m_Process.setIsDirectPrint(Boolean.valueOf(atts.getValue("isDirectPrint")).booleanValue());
			m_Process.setIsReport(Boolean.valueOf(atts.getValue("isReport")).booleanValue());
			m_Process.setName(atts.getValue("Name"));
			
			m_Process.setProcedureName(atts.getValue("ProcedureName"));
			m_Process.setStatistic_Count(0);
			m_Process.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
			m_Process.setStatistic_Seconds(0);
			m_Process.setValue(atts.getValue("Value"));
			m_Process.setWorkflowValue(atts.getValue("WorkflowValue"));			
	        if (m_Process.save(m_trxName) == true){		    	
		    	record_log (1, m_Process.getName(),"Process", m_Process.get_ID(),AD_Backup_ID, Object_Status,"AD_Process",get_IDWithColumn("AD_Table", "TableName", "AD_Process"));           		        		
            }
            else{
            	record_log (0, m_Process.getName(),"Process", m_Process.get_ID(),AD_Backup_ID, Object_Status,"AD_Process",get_IDWithColumn("AD_Table", "TableName", "AD_Process"));
            }			
		}
    }
	// processpara element.
	else if (elementValue.equals("processpara")) {

		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			String name = atts.getValue("Name");
			
			int id = get_IDWithMaster("AD_Process_Para", name, "AD_Process", atts.getValue("ADProcessNameID"));
			X_AD_Process_Para m_Process_para = new X_AD_Process_Para(Env.getCtx(),id ,null);
			if (id>0){				
					AD_Backup_ID = copyRecord("AD_Process_Para",m_Process_para);
					Object_Status = "Update";				
			}
			else{
		    		Object_Status = "New";
		    		AD_Backup_ID =0;
		    }
    		m_Process_para.setName(atts.getValue("Name"));
	    	name = atts.getValue("ADProcessNameID");
		    id = get_IDWithColumn("AD_Process", "Name", name);
		    m_Process_para.setAD_Process_ID(id);
		    name = atts.getValue("ADElementNameID");
		    id = get_IDWithColumn("AD_Element", "Name", name);
		    m_Process_para.setAD_Element_ID(id);
	 	    name = atts.getValue("ADReferenceNameID");
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
		    name = atts.getValue("ADReferenceValueNameID");
		    id = get_IDWithColumn("AD_Reference", "Name", name);
		    m_Process_para.setAD_Reference_Value_ID(id);
		    name = atts.getValue("ADValRuleNameID");
		    id = get_IDWithColumn("AD_Val_Rule", "Name", name);
		    m_Process_para.setAD_Val_Rule_ID(id);
		    m_Process_para.setColumnName(atts.getValue("ColumnName"));
		    m_Process_para.setDefaultValue(atts.getValue("DefaultValue"));
		    m_Process_para.setDefaultValue2(atts.getValue("DefaultValue2"));
		    m_Process_para.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
		    m_Process_para.setEntityType(atts.getValue("EntityType"));
		    m_Process_para.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
		    m_Process_para.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
		    m_Process_para.setName(atts.getValue("Name"));
		    m_Process_para.setVFormat(atts.getValue("VFormat"));
		    m_Process_para.setValueMax(atts.getValue("ValueMax"));
		    m_Process_para.setValueMin(atts.getValue("ValueMin"));
		    m_Process_para.setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
		    m_Process_para.setFieldLength(Integer.parseInt(atts.getValue("FieldLength")));
		    m_Process_para.setIsCentrallyMaintained(Boolean.valueOf(atts.getValue("isCentrallyMaintained")).booleanValue());
		    m_Process_para.setIsMandatory(Boolean.valueOf(atts.getValue("isMandatory")).booleanValue());
		    m_Process_para.setIsRange(Boolean.valueOf(atts.getValue("isRange")).booleanValue());
		    if (m_Process_para.save(m_trxName) == true){		    	
		    	record_log (1, m_Process_para.getName(),"Process_para", m_Process_para.get_ID(),AD_Backup_ID, Object_Status,"AD_Process_para",get_IDWithColumn("AD_Table", "TableName", "AD_Process_para"));           		        		
            }
            else{
            	record_log (0, m_Process_para.getName(),"Process_para", m_Process_para.get_ID(),AD_Backup_ID, Object_Status,"AD_Process_para",get_IDWithColumn("AD_Table", "TableName", "AD_Process_para"));
            }
	    }
	}
	// table element.
	else if (elementValue.equals("table")) {


		String entitytype = atts.getValue("EntityType");

		if (entitytype.equals("U") || entitytype.equals("D") && m_UpdateMode.equals("true")) {

		    String tableName = atts.getValue("ADTableNameID");

		    int id = get_IDWithColumn("AD_Table", "TableName", tableName);

		    m_Table = new X_AD_Table(Env.getCtx(),id,null);
		    if (id > 0){		
		    	AD_Backup_ID = copyRecord("AD_Table",m_Table);
				Object_Status = "Update";			
		    	}
		    else{
	    		Object_Status = "New";
	    		AD_Backup_ID =0;
		    	}
		    createtable (tableName);
	    	m_Table.setTableName(tableName);
	    	String Name = atts.getValue("ADWindowNameID");	    
		    id = get_IDWithColumn("AD_Window", "Name", Name);
		    m_Table.setAD_Window_ID(id);
		    Name = atts.getValue("POWindowNameID");
		    if (Name != null){
		    id = get_IDWithColumn("AD_Window", "Name", Name);
		    m_Table.setPO_Window_ID(id);
		    }
		    else
		    Name = atts.getValue("ADValRuleNameID");
		    id = get_IDWithColumn("AD_Val_Rule", "Name", Name);

		    m_Table.setAD_Val_Rule_ID(id);
		    m_Table.setAccessLevel (atts.getValue("AccessLevel"));		    
            m_Table.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
            m_Table.setEntityType(atts.getValue("EntityType"));
            m_Table.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));            
            m_Table.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
            m_Table.setImportTable(atts.getValue("ImportTable"));
            m_Table.setIsChangeLog(Boolean.valueOf(atts.getValue("isChangeLog")).booleanValue());
            m_Table.setIsDeleteable(Boolean.valueOf(atts.getValue("isDeleteable")).booleanValue());
            m_Table.setIsHighVolume(Boolean.valueOf(atts.getValue("isHighVolume")).booleanValue());
            m_Table.setIsSecurityEnabled(Boolean.valueOf(atts.getValue("isSecurityEnabled")).booleanValue());
            m_Table.setIsView(Boolean.valueOf(atts.getValue("isView")).booleanValue());
            //m_Table.setLoadSeq(Integer.parseInt(atts.getValue("LoadSeq")));
            m_Table.setName(atts.getValue("Name"));
            m_Table.setReplicationType(atts.getValue("ReplicationType"));
            m_Table.setTableName(atts.getValue("TableName"));
//log.info("in3");
            attsOut.clear();          
            if (m_Table.save(m_trxName) == true){		    	
		    	record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),AD_Backup_ID, Object_Status,"AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));           		        		
            }
            else{
            	record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),AD_Backup_ID, Object_Status,"AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
            }            
	    	}		
	    }
	// column element.
	else if (elementValue.equals("column")) {
		int success=0;
		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
		    String columnName = atts.getValue("ColumnName");
		    
		    int tableid = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));		    
		    int id =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", columnName, "AD_Table", tableid);	    
		    m_Column = new X_AD_Column(Env.getCtx(),id,null);
		    if (id > 0){		
		    	AD_Backup_ID = copyRecord("AD_Column",m_Column);
				Object_Status = "Update";
		    	}
		    else{
	    		Object_Status = "New";
	    		AD_Backup_ID =0;
		    	}			
	    	m_Column.setColumnName(columnName);
		    
	    	String Name = atts.getValue("ADProcessNameID");	    
		    id = get_IDWithColumn("AD_Process", "Name", Name);
		    m_Column.setAD_Process_ID(id);		    
		    Name = atts.getValue("ADReferenceNameID");
//log.info("Column Name1 ->"+Name);
//log.info("Database Name ->"+m_DatabaseType);		
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
//log.info("Column Name2 ->"+Name);
		    id = get_IDWithColumn("AD_Reference", "Name", Name);
		    m_Column.setAD_Reference_ID(id);		    
//log.info("Column ID ->"+id);
		    Name = atts.getValue("ADTableNameID");	    
		    id = get_IDWithColumn("AD_Table", "TableName", Name);
		    m_Column.setAD_Table_ID(id);
		    
		    Name = atts.getValue("ADValRuleNameID");	    
		    id = get_IDWithColumn("AD_Val_Rule", "Name", Name);			    
		    m_Column.setAD_Val_Rule_ID(id);
		    Name = atts.getValue("ADReferenceNameValueID");
			id = get_IDWithColumn("AD_Reference", "Name", Name);			    
		    m_Column.setAD_Reference_Value_ID(id);
		    m_Column.setCallout(atts.getValue("Callout"));
		    
	        m_Column.setColumnName(atts.getValue("ColumnName"));
	        m_Column.setDefaultValue(atts.getValue("DefaultValue"));
	        m_Column.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
            m_Column.setEntityType(atts.getValue("EntityType"));
            
            if (Integer.parseInt(atts.getValue("FieldLength")) >0)
            	m_Column.setFieldLength (Integer.parseInt(atts.getValue("FieldLength")));
            m_Column.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
            m_Column.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
            m_Column.setIsAlwaysUpdateable((Boolean.valueOf(atts.getValue("isAlwaysUpdateable")).booleanValue()));
            //m_Column.setIsEncrypted(atts.getValue("isEncrypted"));	            
            m_Column.setIsIdentifier((Boolean.valueOf(atts.getValue("isIdentifier")).booleanValue()));
            m_Column.setIsKey((Boolean.valueOf(atts.getValue("isKey")).booleanValue()));
            m_Column.setIsMandatory((Boolean.valueOf(atts.getValue("isMandatory")).booleanValue()));
            
            m_Column.setIsParent((Boolean.valueOf(atts.getValue("isParent")).booleanValue()));	            
            m_Column.setIsSelectionColumn((Boolean.valueOf(atts.getValue("isSelectionColumn")).booleanValue()));
            m_Column.setIsSyncDatabase (atts.getValue("getIsSyncDatabase"));
            
            m_Column.setIsTranslated((Boolean.valueOf(atts.getValue("isTranslated")).booleanValue()));
            m_Column.setIsUpdateable((Boolean.valueOf(atts.getValue("isUpdateable")).booleanValue()));
            m_Column.setName(atts.getValue("Name"));
            m_Column.setReadOnlyLogic(atts.getValue("ReadOnlyLogic"));
            
            if (Integer.parseInt(atts.getValue("SeqNo")) >0) 
            	m_Column.setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
            m_Column.setVFormat(atts.getValue("VFormat"));
            if (atts.getValue("ValueMax") != null) 
            	m_Column.setValueMax(atts.getValue("ValueMax"));
            if (atts.getValue("ValueMin") != null)
            	m_Column.setValueMin(atts.getValue("ValueMin"));
            m_Column.setVersion(new BigDecimal("0.0"));
            
            // Setup Element.
            id = get_IDWithColumn("AD_Element", "ColumnName", m_Column.getColumnName());
		    X_AD_Element element =	new X_AD_Element(Env.getCtx(), id,null);
		    
		    String Object_Status_col = Object_Status;
		    if (element.getAD_Element_ID() == 0) {
		    //Object_Status = "New";
			element.setColumnName(m_Column.getColumnName());
			element.setEntityType(m_Column.getEntityType());
			element.setPrintName(m_Column.getColumnName());
			
			element.setName(m_Column.getColumnName());			
			if (element.save(m_trxName) == true){            	
            	record_log (1, m_Column.getName(),"Element", element.getAD_Element_ID(),AD_Backup_ID, "New","AD_Element",get_IDWithColumn("AD_Table", "TableName", "AD_Element"));
            }
            else{
            	record_log (0, m_Column.getName(),"Element", element.getAD_Element_ID(),AD_Backup_ID, "New","AD_Element",get_IDWithColumn("AD_Table", "TableName", "AD_Element"));
            }
		    }
		    
		    Object_Status = Object_Status_col;
		    m_Column.setAD_Element_ID(element.getAD_Element_ID());
		    if (m_Column.save(m_trxName) == true){		    	
		    	record_log (1, m_Column.getName(),"Column", m_Column.get_ID(),AD_Backup_ID, Object_Status,"AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));           		        		
            }
            else{
            	record_log (0, m_Column.getName(),"Column", m_Column.get_ID(),AD_Backup_ID, Object_Status,"AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
            }
            
	        
	        success = createcolumn (atts.getValue("ADTableNameID").toUpperCase(), columnName.toUpperCase(),
	        		m_Column.getAD_Reference_ID(),m_Column.getFieldLength(), atts.getValue("DefaultValue"),
					m_Column.isMandatory());
	        
	        if (success == 1){	    		           		        		
	    		record_log (1, m_Column.getColumnName(),"dbColumn", m_Column.get_ID(),0, Object_Status,atts.getValue("ADTableNameID").toUpperCase(),get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID").toUpperCase()));
	    	}
	    	else{
	    		record_log (0, m_Column.getColumnName(),"dbColumn", m_Column.get_ID(),0, Object_Status,atts.getValue("ADTableNameID").toUpperCase(),get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID").toUpperCase()));
	    	}
	    	}
	    }

	//	 Role element.
	else if (elementValue.equals("role")) {
		
			
			String name = atts.getValue("Name");
			
			int id = get_ID("AD_Role", name);
		    m_Role = new X_AD_Role(Env.getCtx(), id,null);
		    
		    if (id > 0){		
		    	AD_Backup_ID = copyRecord("AD_Role",m_Role);
				Object_Status = "Update";			
		    	}
		   else{
	    		Object_Status = "New";
	    		AD_Backup_ID =0;
		    }
		    
		    m_Role.setName(name);
		    name = atts.getValue("treemenuname");
		    if (name!= null){
		    id = get_IDWithColumn("AD_Tree", "Name", name);
		    m_Role.setAD_Tree_Menu_ID(id);
		    }
		    
		    name = null;
		    name = atts.getValue("treeorgname");
		    if (name!= null){
		    id = get_IDWithColumn("AD_Tree", "Name", name);
		    m_Role.setAD_Tree_Org_ID(id);
		    }
		    
		    name = null;
		    name = atts.getValue("currencycode");
		    if (name!= null){
		    id = get_IDWithColumn("C_Currency", "ISO_Code", name);
		    m_Role.setC_Currency_ID(id);
		    }
		    
		    name = null;
		    name = atts.getValue("supervisorid");
		    if (name!= null){
		    id = get_IDWithColumn("AD_User", "Name", name);
		    m_Role.setC_Currency_ID(id);
		    }
		    
		    m_Role.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
		    m_Role.setAmtApproval(new BigDecimal(atts.getValue("AmtApproval")));
		    m_Role.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
		    m_Role.setIsAccessAllOrgs(atts.getValue("isAccessAllOrgs") != null ? Boolean.valueOf(atts.getValue("isAccessAllOrgs")).booleanValue():true);
		    m_Role.setIsCanApproveOwnDoc(atts.getValue("isCanApproveOwnDoc") != null ? Boolean.valueOf(atts.getValue("isCanApproveOwnDoc")).booleanValue():true);
		    m_Role.setIsCanExport(atts.getValue("isCanExport") != null ? Boolean.valueOf(atts.getValue("isCanExport")).booleanValue():true);
		    m_Role.setIsCanReport(atts.getValue("isCanReport") != null ? Boolean.valueOf(atts.getValue("isCanReport")).booleanValue():true);
		    m_Role.setIsChangeLog(atts.getValue("isChangeLog") != null ? Boolean.valueOf(atts.getValue("isChangeLog")).booleanValue():true);
		    m_Role.setIsManual(atts.getValue("isManual") != null ? Boolean.valueOf(atts.getValue("isManual")).booleanValue():true);
		    m_Role.setIsPersonalAccess(atts.getValue("isPersonalAccess") != null ? Boolean.valueOf(atts.getValue("isPersonalAccess")).booleanValue():true);
		    m_Role.setIsPersonalLock(atts.getValue("isPersonalLock") != null ? Boolean.valueOf(atts.getValue("isPersonalLock")).booleanValue():true);
		    m_Role.setIsShowAcct(atts.getValue("isShowAcct") != null ? Boolean.valueOf(atts.getValue("isShowAcct")).booleanValue():true);
		    m_Role.setIsUseUserOrgAccess(atts.getValue("isUseUserOrgAccess") != null ? Boolean.valueOf(atts.getValue("isUseUserOrgAccess")).booleanValue():true);
		    m_Role.setOverwritePriceLimit(atts.getValue("isOverwritePriceLimit") != null ? Boolean.valueOf(atts.getValue("isOverwritePriceLimit")).booleanValue():true);
		    m_Role.setPreferenceType(atts.getValue("PreferenceType"));
		    m_Role.setUserLevel(atts.getValue("UserLevel"));	    
		     
	        if (m_Role.save(m_trxName) == true){
	        	
		    	record_log (1, m_Role.getName(),"Role", m_Role.get_ID(),AD_Backup_ID, Object_Status,"AD_Role",get_IDWithColumn("AD_Table", "TableName", "AD_Role"));           		        		
            }
            else{
            	
            	record_log (0, m_Role.getName(),"Role", m_Role.get_ID(),AD_Backup_ID, Object_Status,"AD_Role",get_IDWithColumn("AD_Table", "TableName", "AD_Role"));
            }
	    }
		

	else if (elementValue.equals("userrole")) {
		int roleid =0;
		int userid =0;
		int orgid =0;
		
		if (atts.getValue("username")!=null){
		String name = atts.getValue("username");		
		sqlB = new StringBuffer ("SELECT AD_User_ID FROM AD_User WHERE Name= ?");
		userid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("orgname")!=null){
			String name = atts.getValue("orgname");		
			sqlB = new StringBuffer ("SELECT AD_Org_ID FROM AD_Org WHERE Name= ?");
			orgid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_User_Roles WHERE AD_User_ID = ? and AD_Role_ID = ? and AD_Org_ID = "+orgid);		
		int count = DB.getSQLValue(null,sqlB.toString(),userid,roleid);
		if (count>0){
		   	//AD_Backup_ID = copyRecord("AD_Role",m_Role);
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_User_Roles "
					+ "SET isActive = '" + atts.getValue("isActive")+"'"
					+ " WHERE AD_User_ID = " + userid
					+ " and AD_Role_ID = " + roleid
					+ " and AD_Org_ID = " + orgid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to user roles failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_User_Roles" 
					+   "(AD_Client_ID,  CreatedBy, UpdatedBy, " 
					+   "AD_User_ID, AD_Role_ID, AD_Org_ID, isActive) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())    				
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx()) 
					+	", " +userid 
					+	", " + roleid
					+	", " + orgid
					+	", '" + atts.getValue("isActive")+"')");
    		int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to user roles failed");
		}
	}    
	
	else if (elementValue.equals("orgrole")) {
		int roleid =0;
		int orgid =0;		
				
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("orgname")!=null){
			String name = atts.getValue("orgname");		
			sqlB = new StringBuffer ("SELECT AD_Org_ID FROM AD_Org WHERE Name= ?");
			orgid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_Role_OrgAccess WHERE AD_Role_ID=? and AD_Org_ID=?");		
		int count = DB.getSQLValue(null,sqlB.toString(),roleid,orgid);
		if (count>0){		   	
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_Role_OrgAccess "
					+ "SET isActive = '" + atts.getValue("isActive")
					+ "', isReadOnly = '" + atts.getValue("isReadOnly")
					+ "' WHERE AD_Role_ID = " + roleid
					+ " and AD_Org_ID = " + orgid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to org access failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_Role_OrgAccess" 
					+   "(AD_Client_ID, CreatedBy, UpdatedBy, " 
					+   "AD_Role_ID, AD_Org_ID, isActive, isReadOnly) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())    				
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + roleid
					+	", " + orgid
					+	", '" + atts.getValue("isActive")
					+	"', '" + atts.getValue("isReadOnly")+"')");

			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to org access failed");
		}
	}    
	
	else if (elementValue.equals("windowaccess")) {
		int roleid =0;
		int windowid =0;		
				
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("windowname")!=null){
			String name = atts.getValue("windowname");		
			sqlB = new StringBuffer ("SELECT AD_Window_ID FROM AD_Window WHERE Name= ?");
			windowid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_Window_Access WHERE AD_Role_ID=? and AD_Window_ID=?");		
		int count = DB.getSQLValue(null,sqlB.toString(),roleid,windowid);
		if (count>0){		   	
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_Window_Access "
					+ "SET isActive = '" + atts.getValue("isActive")
					+ "', isReadWrite = '" + atts.getValue("isReadWrite")
					+ "' WHERE AD_Role_ID = " + roleid
					+ " and AD_Window_ID = " + windowid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to window access failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_Window_Access" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_Role_ID, AD_Window_ID, isActive, isReadWrite) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
					+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + roleid
					+	", " + windowid
					+	", '" + atts.getValue("isActive")
					+	"', '" + atts.getValue("isReadWrite")+"')");

			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to window access failed");
		}
	}    
	
	else if (elementValue.equals("processaccess")) {
		int roleid =0;
		int processid =0;		
				
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("processname")!=null){
			String name = atts.getValue("processname");		
			sqlB = new StringBuffer ("SELECT AD_Process_ID FROM AD_Process WHERE Name= ?");
			processid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_Process_Access WHERE AD_Role_ID=? and AD_Process_ID=?");		
		int count = DB.getSQLValue(null,sqlB.toString(),roleid,processid);
		if (count>0){		   	
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_Process_Access "
					+ "SET isActive = '" + atts.getValue("isActive")
					+ "', isReadWrite = '" + atts.getValue("isReadWrite")
					+ "' WHERE AD_Role_ID = " + roleid
					+ " and AD_Process_ID = " + processid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to process access failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_Process_Access" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_Role_ID, AD_Process_ID, isActive, isReadWrite) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
					+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())					
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + roleid
					+	", " + processid
					+	", '" + atts.getValue("isActive")
					+	"', '" + atts.getValue("isReadWrite")+"')");

			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to process access failed");
		}
	}    
	
	else if (elementValue.equals("formaccess")) {
		int roleid =0;
		int formid =0;		
				
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("formname")!=null){
			String name = atts.getValue("formname");		
			sqlB = new StringBuffer ("SELECT AD_Form_ID FROM AD_Process WHERE Name= ?");
			formid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_Form_Access WHERE AD_Role_ID=? and AD_Form_ID=?");		
		int count = DB.getSQLValue(null,sqlB.toString(),roleid,formid);
		if (count>0){		   	
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_Form_Access "
					+ "SET isActive = '" + atts.getValue("isActive")
					+ "', isReadWrite = '" + atts.getValue("isReadWrite")
					+ "' WHERE AD_Role_ID = " + roleid
					+ " and AD_Form_ID = " + formid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to form access failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_Form_Access" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_Role_ID, AD_Form_ID, isActive, isReadWrite) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + roleid
					+	", " + formid
					+	", '" + atts.getValue("isActive")
					+	"', '" + atts.getValue("isReadWrite")+"')");

			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to form access failed");
		}
	}    
	

	else if (elementValue.equals("workflowaccess")) {
		int roleid =0;
		int workflowid =0;		
				
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("workflowname")!=null){
			String name = atts.getValue("workflowname");		
			sqlB = new StringBuffer ("SELECT AD_Workflow_ID FROM AD_Workflow WHERE Name= ?");
			workflowid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_Workflow_Access WHERE AD_Role_ID=? and AD_Workflow_ID=?");		
		int count = DB.getSQLValue(null,sqlB.toString(),roleid,workflowid);
		if (count>0){		   	
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_Workflow_Access "
					+ "SET isActive = '" + atts.getValue("isActive")
					+ "', isReadWrite = '" + atts.getValue("isReadWrite")
					+ "' WHERE AD_Role_ID = " + roleid
					+ " and AD_Workflow_ID = " + workflowid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to workflow access failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_Workflow_Access" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_Role_ID, AD_Workflow_ID, isActive, isReadWrite) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + roleid
					+	", " + workflowid
					+	", '" + atts.getValue("isActive")
					+	"', '" + atts.getValue("isReadWrite")+"')");

			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to workflow access failed");
		}
	}    
	
	else if (elementValue.equals("taskaccess")) {
		int roleid =0;
		int taskid =0;		
				
		if (atts.getValue("rolename")!=null){
		String name = atts.getValue("rolename");		
		sqlB = new StringBuffer ("SELECT AD_Role_ID FROM AD_Role WHERE Name= ?");
		roleid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		if (atts.getValue("taskname")!=null){
			String name = atts.getValue("taskname");		
			sqlB = new StringBuffer ("SELECT AD_Task_ID FROM AD_Task WHERE Name= ?");
			taskid = DB.getSQLValue(null,sqlB.toString(),name);
		}
		
		sqlB = new StringBuffer ("SELECT count(*) FROM AD_Task_Access WHERE AD_Role_ID=? and AD_Task_ID=?");		
		int count = DB.getSQLValue(null,sqlB.toString(),roleid,taskid);
		if (count>0){		   	
			Object_Status = "Update";			
			sqlB = new StringBuffer ("UPDATE AD_Task_Access "
					+ "SET isActive = '" + atts.getValue("isActive")
					+ "', isReadWrite = '" + atts.getValue("isReadWrite")
					+ "' WHERE AD_Role_ID = " + roleid
					+ " and AD_Task_ID = " + taskid );
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Update to task access failed");
		}
		else{
			Object_Status = "New";
    		AD_Backup_ID =0;
    		sqlB = new StringBuffer ("Insert INTO AD_Task_Access" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_Role_ID, AD_Task_ID, isActive, isReadWrite) "
					+	"VALUES(" 
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + roleid
					+	", " + taskid
					+	", '" + atts.getValue("isActive")
					+	"', '" + atts.getValue("isReadWrite")+"')");

			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to task access failed");
		}
	}
	
	
	//	 task element.
	else if (elementValue.equals("task")) {
		String entitytype = atts.getValue("EntityType");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			String name = atts.getValue("ADTaskNameID");
			int id = get_ID("AD_Task", name);
		    m_Task = new X_AD_Task(Env.getCtx(), id,null);
		    if (id > 0){		
		    	AD_Backup_ID = copyRecord("AD_Task",m_Task);
				Object_Status = "Update";			
		    	}
		   else{
	    		Object_Status = "New";
	    		AD_Backup_ID =0;
		    }    	    
	    	m_Task.setAccessLevel(atts.getValue("AccessLevel"));
	        m_Task.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Task.setEntityType(atts.getValue("EntityType"));
	        m_Task.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
	        m_Task.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Task.setName(name);
	        m_Task.setOS_Command(atts.getValue("OS_Command"));		        
	        if (m_Task.save(m_trxName) == true){		    	
		    	record_log (1, m_Task.getName(),"Task", m_Task.get_ID(),AD_Backup_ID, Object_Status,"AD_Task",get_IDWithColumn("AD_Table", "TableName", "AD_Task"));           		        		
            }
            else{
            	record_log (0, m_Task.getName(),"Task", m_Task.get_ID(),AD_Backup_ID, Object_Status,"AD_Task",get_IDWithColumn("AD_Table", "TableName", "AD_Task"));
            }
	    }
		}
	//	 form element.
	else if (elementValue.equals("form")) {

		String entitytype = atts.getValue("EntityType");		
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			String name = atts.getValue("ADFormNameID");
			int id = get_ID("AD_Form", name);
		    m_Form = new X_AD_Form(Env.getCtx(), id,null);
		    if (id > 0){
		    	AD_Backup_ID = copyRecord("AD_Form",m_Form);
				Object_Status = "Update";
				}
		   else{
				Object_Status = "New";
				AD_Backup_ID =0;
		    }	    
	    	m_Form.setClassname (atts.getValue("Classname"));
	        m_Form.setIsBetaFunctionality (Boolean.valueOf(atts.getValue("isBetaFunctionality")).booleanValue());
	        m_Form.setAccessLevel(atts.getValue("AccessLevel"));
	        m_Form.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Form.setEntityType(atts.getValue("EntityType"));
	        m_Form.setHelp(atts.getValue("Help").replaceAll(",",""));
	        m_Form.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Form.setName(atts.getValue("Name")); 

	        if (m_Form.save(m_trxName) == true){		    	
		    	record_log (1, m_Form.getName(),"Form", m_Form.get_ID(),AD_Backup_ID, Object_Status,"AD_Form",get_IDWithColumn("AD_Table", "TableName", "AD_Form"));           		        		
            }
            else{
            	record_log (0, m_Form.getName(),"Form", m_Form.get_ID(),AD_Backup_ID, Object_Status,"AD_Form",get_IDWithColumn("AD_Table", "TableName", "AD_Form"));
            }
	    }
		}
//	 impformat element.
	else if (elementValue.equals("impformat")) {		
		
		int id = get_ID("AD_ImpFormat", atts.getValue("Name"));
		m_ImpFormat = new X_AD_ImpFormat(Env.getCtx(), id,null);
	    if (id > 0){
	    	AD_Backup_ID = copyRecord("AD_ImpFormat",m_ImpFormat);
			Object_Status = "Update";			
	    }
	   else{
			Object_Status = "New";
			AD_Backup_ID =0;
	    }
	    m_ImpFormat.setName(atts.getValue("Name"));
	    String Name = atts.getValue("ADTableNameID");	    	
	    id = get_IDWithColumn("AD_Table", "TableName", Name);
	    m_ImpFormat.setAD_Table_ID(id);
	    m_ImpFormat.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	    m_ImpFormat.setProcessing(atts.getValue("isProcessing") != null ? Boolean.valueOf(atts.getValue("isProcessing")).booleanValue():true);
	    m_ImpFormat.setName(atts.getValue("Name"));
	    m_ImpFormat.setDescription(atts.getValue("Description"));
	    m_ImpFormat.setFormatType(atts.getValue("FormatType"));
	    m_ImpFormat.save(m_trxName);
	    if (m_ImpFormat.save(m_trxName) == true){		    	
	    	record_log (1, m_ImpFormat.getName(),"ImpFormat", m_ImpFormat.get_ID(),AD_Backup_ID, Object_Status,"AD_ImpFormat",get_IDWithColumn("AD_Table", "TableName", "AD_ImpFormat"));           		        		
        }
        else{
        	record_log (0, m_ImpFormat.getName(),"ImpFormat", m_ImpFormat.get_ID(),AD_Backup_ID, Object_Status,"AD_ImpFormat",get_IDWithColumn("AD_Table", "TableName", "AD_ImpFormat"));
        }		
	}
	//impformatrow element
	else if (elementValue.equals("impformatrow")) {		
		
		int impformid = get_ID("AD_ImpFormat", atts.getValue("ADImpFormatNameID"));		
	    int tableid = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));	
	    int columnid  = get_IDWithMasterAndColumn ("AD_Column","ColumnName", atts.getValue("ADColumnNameID"), "AD_Table", tableid);	    
		sqlB = new StringBuffer ("SELECT AD_ImpFormat_Row_ID FROM AD_ImpFormat_Row WHERE AD_Column_ID=? and AD_ImpFormat_ID=?");		
		int id = DB.getSQLValue(null,sqlB.toString(),columnid,impformid);
		m_ImpFormat_row = new X_AD_ImpFormat_Row(Env.getCtx(), id,null);
	    if (id > 0){
	    	AD_Backup_ID = copyRecord("AD_ImpFormat",m_ImpFormat_row);
			Object_Status = "Update";			
	    }
	   else{
			Object_Status = "New";
			AD_Backup_ID =0;
	    }
	    m_ImpFormat_row.setName(atts.getValue("Name"));	    
	    m_ImpFormat_row.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	    m_ImpFormat_row.setAD_Column_ID(columnid);
	    m_ImpFormat_row.setAD_ImpFormat_ID(impformid);
	    m_ImpFormat_row.setDataFormat(atts.getValue("DataFormat"));
	    m_ImpFormat_row.setDataType(atts.getValue("DataType"));
	    m_ImpFormat_row.setDecimalPoint(atts.getValue("DecimalPoint"));
	    m_ImpFormat_row.setDivideBy100(atts.getValue("isDivideBy100") != null ? Boolean.valueOf(atts.getValue("isDivideBy100")).booleanValue():true);
	    m_ImpFormat_row.setConstantValue(atts.getValue("ConstantValue"));
	    m_ImpFormat_row.setCallout(atts.getValue("Callout"));	    
	    m_ImpFormat_row.setEndNo(Integer.parseInt(atts.getValue("EndNo")));
	    m_ImpFormat_row.setScript(atts.getValue("Script"));
	    m_ImpFormat_row.setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
	    m_ImpFormat_row.setStartNo(Integer.parseInt(atts.getValue("StartNo")));	    
	    m_ImpFormat_row.save(m_trxName);
	    if (m_ImpFormat_row.save(m_trxName) == true){		    	
	    	record_log (1, m_ImpFormat_row.getName(),"ImpFormat", m_ImpFormat_row.get_ID(),AD_Backup_ID, Object_Status,"AD_ImpFormat",get_IDWithColumn("AD_Table", "TableName", "m_ImpFormat_row"));           		        		
        }
        else{
        	record_log (0, m_ImpFormat_row.getName(),"ImpFormat", m_ImpFormat_row.get_ID(),AD_Backup_ID, Object_Status,"AD_ImpFormat",get_IDWithColumn("AD_Table", "TableName", "m_ImpFormat_row"));
        }		
	}
	
	
	//	 workbench element.
	else if (elementValue.equals("workbench")) {
		String entitytype = atts.getValue("EntityType");		
		String name = atts.getValue("ADWorkbenchNameID");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			int id = get_ID("AD_Workbench", name);
		    m_Workbench = new X_AD_Workbench(Env.getCtx(), id,null);
		    if (id > 0){
		    	AD_Backup_ID = copyRecord("AD_Workbench",m_Workbench);
				Object_Status = "Update";			
		    }
		   else{
				Object_Status = "New";
				AD_Backup_ID =0;
		    }	    
	    	
		    int tableid = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));	
		    int columnid  = get_IDWithMasterAndColumn ("AD_Column","ColumnName", atts.getValue("ADColumnNameID"), "AD_Table", tableid);		    
		    m_Workbench.setAD_Column_ID(columnid);
	        m_Workbench.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Workbench.setEntityType(atts.getValue("EntityType"));
	        m_Workbench.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
	        m_Workbench.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Workbench.setName(atts.getValue("Name"));
	        //m_Workbench.setPA_Goal_ID(Integer.parseInt(atts.getValue("PAGoalID")));
	        if (m_Workbench.save(m_trxName) == true){		    	
		    	record_log (1, m_Workbench.getName(),"Workbench", m_Workbench.get_ID(),AD_Backup_ID, Object_Status,"AD_Workbench",get_IDWithColumn("AD_Table", "TableName", "AD_Workbench"));           		        		
            }
            else{
            	record_log (0, m_Workbench.getName(),"Workbench", m_Workbench.get_ID(),AD_Backup_ID, Object_Status,"AD_Workbench",get_IDWithColumn("AD_Table", "TableName", "AD_Workbench"));
            }
		    }
		}
	
//	 Snipit	element.
	else if (elementValue.equals("codesnipit")) {				    
		Object_Status = "Update";
		String releaseNumber = atts.getValue("ReleaseNo");			
		//Check Release Number
		if(Adempiere.MAIN_VERSION.equals(releaseNumber)||releaseNumber.equals("all")){
			String sourceName = atts.getValue("filename");
			String targetDirectory = atts.getValue("filedir");
			String oldCode = atts.getValue("oldcode");
			String newCode = atts.getValue("newcode");			
			
			int idDetail=0;
			InputStream source;  // Stream for reading from the source file.
	        OutputStream copy;   // Stream for writing the copy.
	        File currentDirectory = new File(".");

	        String packagePath=null;       
	        String sourcePath=null; 

	        //get compiere-all directory
	        try {
	        	packagePath = currentDirectory.getCanonicalPath();
	        	File parentDirectory = new File(packagePath);

	        	while (!parentDirectory.getName().equals("packages")){        		
	        		parentDirectory = parentDirectory.getParentFile();        				
	        	}
	       		parentDirectory = parentDirectory.getParentFile();        				
	        	sourcePath = parentDirectory.getCanonicalPath();
			} catch (IOException e1) {
				System.out.println("Can't find compiere-all directory.");			
			}        


			//	Create backup directory if required
	        File backupDir = new File(packagePath+fileSeperator+"backup"+fileSeperator);
	        if (!backupDir.exists()){
		        boolean success = (new File(packagePath+fileSeperator+"backup"+fileSeperator)).mkdirs();
		        if (!success) {
		            log.info("Backup directory creation failed");
		        }
	        }			

			//Correct target directory for proper file seperator
			String fullDirectory = sourcePath+targetDirectory;
			String targetDirectoryModified=null;
			char slash1 = '\\'; 
			char slash2 = '/';
			if (fileSeperator.equals("/"))			
				targetDirectoryModified = fullDirectory.replace(slash1,slash2);		
			else
				targetDirectoryModified = fullDirectory.replace(slash2,slash1);	

	        File file = new File(targetDirectoryModified+sourceName);		
	        log.info(targetDirectoryModified+sourceName);
			//	check to see if overwrites are allowed
	        if (file.exists() && force == false) {
	            System.out.println(
	                 "Output file exists.  Use the -f option to replace it.");
	            return;  
	        }		
	        //backup file to package directory
	        else if (file.exists() && force == true) {
	        	Object_Status = "Update";			
				log.info("Target Backup:"+targetDirectoryModified+sourceName);
				source = OpenInputfile(targetDirectoryModified+sourceName);
				SimpleDateFormat formatter_file = new SimpleDateFormat("yyMMddHHmmssSSSSZ");	    	
		    	Date today = new Date();
				fileDate = formatter_file.format(today);
		        copy = OpenOutputfile(packagePath+fileSeperator+"backup"+fileSeperator+fileDate+"_"+sourceName);
		        log.info("Source Backup:"+packagePath+fileSeperator+"backup"+fileSeperator+fileDate+"_"+sourceName);
		        copyFile (source,copy);
				log.info("Backup Complete");
	        }	        
	 
	        int success = readReplace(targetDirectoryModified+sourceName, oldCode, newCode);
	
//	      Record in log
	        int idBackup = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Backup", null);        
	        if (success != -1){
		    	try {				
		    		idDetail = record_log (1, sourceName,"codesnipit", 0,0, Object_Status,sourceName,0);
				} catch (SAXException e) {
					log.info ("setfile:"+e);
				}           		        		
	        }
	        else{
	        	try {
	        		idDetail = record_log (0, sourceName,"codesnipit", 0,0, Object_Status,sourceName,0);
				} catch (SAXException e) {
					log.info ("setfile:"+e);
				}
	        }
	        //Record in transaction file 
	        sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Backup" 
					+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+   "AD_PACKAGE_IMP_BACKUP_ID, AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID," 
					+	" AD_PACKAGE_IMP_ORG_DIR, AD_PACKAGE_IMP_BCK_DIR)"
					+	"VALUES("
					+	" "+ Env.getAD_Client_ID(Env.getCtx())
					+	", "+ Env.getAD_Org_ID(Env.getCtx())
					+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", "+ Env.getAD_User_ID(Env.getCtx())
					+	", " + idBackup
					+	", " + idDetail
					+	", " + AD_Package_Imp_ID
					+	", '" + targetDirectoryModified+sourceName
					+	"', '" + packagePath+fileSeperator+"backup"+fileSeperator+fileDate+"_"+sourceName
					+"')");
			
			int no = DB.executeUpdate (sqlB.toString(), m_trxName);
			if (no == -1)
				log.info("Insert to import backup failed");
			
		}
	  }		
	
	
//	 Copy Code to destination 
	else if (elementValue.equals("distfile")) {		

		String releaseNumber = atts.getValue("ReleaseNo");
		//log.info(atts.getValue("ReleaseNo"));
		//log.info(Compiere.MAIN_VERSION);
		//Check Release Number
		if(releaseNumber==null||Adempiere.MAIN_VERSION.equals(releaseNumber)||releaseNumber.equals("all")){			
		String sourceName = atts.getValue("name");
		String sourceDirectory = atts.getValue("sourceDirectory");
		String targetDirectory = atts.getValue("targetDirectory");
		
		Object_Status = "New";
		int idDetail=0;
		InputStream source;  // Stream for reading from the source file.
        OutputStream copy;   // Stream for writing the copy.       

        String packagePath=null;       
        String sourcePath=null; 

        //get compiere-all directory
        try {
        	packagePath = packageDirectory;
        	File parentDirectory = new File(packagePath);
        	while (!parentDirectory.getName().equals("packages")){        		
        		parentDirectory = parentDirectory.getParentFile();        				
        	}
       		parentDirectory = parentDirectory.getParentFile();        				
        	sourcePath = parentDirectory.getCanonicalPath();
		} catch (IOException e1) {
			System.out.println("Can't find compiere-all directory.");			
		}        


		//	Create backup directory if required
        File backupDir = new File(packagePath+fileSeperator+"backup"+fileSeperator);
        if (!backupDir.exists()){
	        boolean success = (new File(packagePath+fileSeperator+"backup"+fileSeperator)).mkdirs();
	        if (!success) {
	            log.info("Backup directory creation failed");
	        }
        }
		

		//Correct target directory for proper file seperator
		String fullDirectory = sourcePath+targetDirectory;
		String targetDirectoryModified=null;
		char slash1 = '\\'; 
		char slash2 = '/';
		if (fileSeperator.equals("/"))			
			targetDirectoryModified = fullDirectory.replace(slash1,slash2);		
		else
			targetDirectoryModified = fullDirectory.replace(slash2,slash1);	

        File file = new File(targetDirectoryModified+sourceName);		
        //check to see if overwrites are allowed
        if (file.exists() && force == false) {
            System.out.println(
                 "Output file exists.  Use the -f option to replace it.");
            return;  
        }		
        //backup file to package directory
        else if (file.exists() && force == true) {
        	Object_Status = "Update";			
			log.info("Target Backup:"+targetDirectoryModified+sourceName);
			source = OpenInputfile(targetDirectoryModified+sourceName);
			SimpleDateFormat formatter_file = new SimpleDateFormat("yyMMddHHmmssSSSSZ");	    	
	    	Date today = new Date();
			fileDate = formatter_file.format(today);
	        copy = OpenOutputfile(packagePath+fileSeperator+"backup"+fileSeperator+fileDate+"_"+sourceName);
	        log.info("Source Backup:"+packagePath+fileSeperator+"backup"+fileSeperator+fileDate+"_"+sourceName);
	        copyFile (source,copy);
			log.info("Backup Complete");
        }		
        
//		Correct dist directory for proper file seperator
		String distDirectoryModified=null;		
		if (fileSeperator.equals("/"))			
			distDirectoryModified = sourceDirectory.replace(slash1,slash2);
		else
			distDirectoryModified = sourceDirectory.replace(slash2,slash1);		
        source = OpenInputfile(packagePath+distDirectoryModified+sourceName);
		
//		 Create Target directory if required		
        File targetDir = new File(targetDirectoryModified);				
        if (!targetDir.exists()){
	        boolean success = (new File(targetDirectoryModified)).mkdirs();
	        if (!success) {
	            log.info("Target directory creation failed");
	        }
        }		
        copy = OpenOutputfile(targetDirectoryModified+sourceName);
		//Copy File
        int success = copyFile (source,copy);        
		//Record in log
        int idBackup = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Backup", null);        
        if (success != -1){
	    	try {				
	    		idDetail = record_log (1, sourceName,"file", 0,0, Object_Status,sourceName,0);
			} catch (SAXException e) {
				log.info ("setfile:"+e);
			}           		        		
        }
        else{
        	try {
        		idDetail = record_log (0, sourceName,"file", 0,0, Object_Status,sourceName,0);
			} catch (SAXException e) {
				log.info ("setfile:"+e);
			}
        }
        //Record in transaction file 
        sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Backup" 
				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
				+   "AD_PACKAGE_IMP_BACKUP_ID, AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID," 
				+	" AD_PACKAGE_IMP_ORG_DIR, AD_PACKAGE_IMP_BCK_DIR)"
				+	"VALUES("
				+	" "+ Env.getAD_Client_ID(Env.getCtx())
				+	", "+ Env.getAD_Org_ID(Env.getCtx())
				+	", "+ Env.getAD_User_ID(Env.getCtx())
				+	", "+ Env.getAD_User_ID(Env.getCtx())
				+	", " + idBackup
				+	", " + idDetail
				+	", " + AD_Package_Imp_ID
				+	", '" + targetDirectoryModified+sourceName
				+	"', '" + packagePath+fileSeperator+"backup"+fileSeperator+fileDate+"_"+sourceName
				+"')");
		
		int no = DB.executeUpdate (sqlB.toString(), m_trxName);
		if (no == -1)
			log.info("Insert to import backup failed");
		
		}
	}
	//	 workbenchwindow element
	else if (elementValue.equals("workbenchwindow")) {
//		 TODO: Solve for lack of name to identify window
		    }
	else if (elementValue.equals("reportview")) {		
		String entitytype = atts.getValue("EntityType");
		String name = atts.getValue("ADReportviewnameID");
		
			int id =  get_ID("AD_ReportView", name);
		    m_Reportview = new X_AD_ReportView(Env.getCtx(), id,null);
		    if (id > 0){
		    	AD_Backup_ID = copyRecord("AD_Reportview",m_Reportview);
				Object_Status = "Update";			
		    }
		   else{
				Object_Status = "New";
				AD_Backup_ID =0;
		    }		    
	    	String Name = atts.getValue("ADTableNameID");	    	
		    id = get_IDWithColumn("AD_Table", "TableName", Name);
		    if (id == 0 ){
		    	createtable (name);
		    	m_Table = new X_AD_Table(Env.getCtx(), 0,null);
		    	m_Table.setAccessLevel("3");
			    m_Table.setName(Name);			    
			    m_Table.setTableName(Name);		    
			    if (m_Table.save(m_trxName) == true){		    	
			    	record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
	            }
	            else{
	            	record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
	            }
		    	id = get_IDWithColumn("AD_Table", "TableName", Name);
		    }
		    
		    m_Reportview.setAD_Table_ID(id);
	        m_Reportview.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Reportview.setEntityType(atts.getValue("EntityType"));	        
	        m_Reportview.setName(atts.getValue("Name"));
	        m_Reportview.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Reportview.setOrderByClause(atts.getValue("OrderByClause"));
	        m_Reportview.setWhereClause(atts.getValue("WhereClause"));	        
	        if (m_Reportview.save(m_trxName) == true){		    	
		    	record_log (1, m_Reportview.getName(),"Reportview", m_Reportview.get_ID(),AD_Backup_ID, Object_Status,"AD_Reportview",get_IDWithColumn("AD_Table", "TableName", "AD_Reportview"));           		        		
            }
            else{
            	record_log (0, m_Reportview.getName(),"Reportview", m_Reportview.get_ID(),AD_Backup_ID, Object_Status,"AD_Reportview",get_IDWithColumn("AD_Table", "TableName", "AD_Reportview"));
            }
		    
	  } 
	
	else if (elementValue.equals("printformat")) {
		
		String name = atts.getValue("Name");		
		int id = get_IDWithColumn("AD_PrintFormat", "Name", name);		
		m_PrintFormat = new X_AD_PrintFormat(Env.getCtx(), id,null);
		if (id > 0){
			AD_Backup_ID = copyRecord("AD_PrintFormat",m_PrintFormat);
			Object_Status = "Update";			
		}
		else{
			Object_Status = "New";
			AD_Backup_ID =0;
		}
		
		name = atts.getValue("ADReportviewnameID");
	    id = get_IDWithColumn("AD_ReportView", "Name", name);
	    m_PrintFormat.setAD_ReportView_ID(id);	    
	    name = atts.getValue("ADTableNameID");
	    id = get_IDWithColumn("AD_Table", "TableName", name);
	    if (id == 0 ){
	    	createtable (name);
	    	m_Table = new X_AD_Table(Env.getCtx(), 0,null);
	    	m_Table.setAccessLevel("3");
		    m_Table.setName(name);			    
		    m_Table.setTableName(name);		    
		    if (m_Table.save(m_trxName) == true){		    	
		    	record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
            }
            else{
            	record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
            }
	    	id = get_IDWithColumn("AD_Table", "TableName", name);
	    }	    
	    m_PrintFormat.setAD_Table_ID(id);
		
	    name = atts.getValue("ADPrintTableFormatID");
	    id = get_IDWithColumn("AD_PrintTableFormat", "Name", name);	
	    m_PrintFormat.setAD_PrintTableFormat_ID(id);	    
	    name = atts.getValue("ADPrintColorID");	    
	    id = get_IDWithColumn("AD_PrintColor", "Name", name);
		
	    m_PrintFormat.setAD_PrintColor_ID(id);	    
	    name = atts.getValue("ADPrintFontID");
	    id = get_IDWithColumn("AD_PrintFont", "Name", name);
	    m_PrintFormat.setAD_PrintFont_ID(id);
		
	    name = atts.getValue("ADPrintPaperID");
	    id = get_IDWithColumn("AD_PrintPaper", "Name", name);
	    m_PrintFormat.setAD_PrintPaper_ID(id);
		
	    m_PrintFormat.setDescription (atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));	    
	    m_PrintFormat.setName (atts.getValue("Name"));	    
	    m_PrintFormat.setPrinterName(atts.getValue("PrinterName"));	    
	    m_PrintFormat.setFooterMargin(Integer.parseInt(atts.getValue("FooterMargin")));
		
	    m_PrintFormat.setHeaderMargin(Integer.parseInt(atts.getValue("HeaderMargin")));	    
	    m_PrintFormat.setCreateCopy(atts.getValue("CreateCopy"));	    
	    m_PrintFormat.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
		
	    m_PrintFormat.setIsTableBased(Boolean.valueOf(atts.getValue("isTableBased")).booleanValue());	    
	    m_PrintFormat.setIsForm(Boolean.valueOf(atts.getValue("isForm")).booleanValue());	    
	    m_PrintFormat.setIsStandardHeaderFooter(Boolean.valueOf(atts.getValue("isStandardHeader")).booleanValue());
		
	    m_PrintFormat.setIsDefault(Boolean.valueOf(atts.getValue("isDefault")).booleanValue());	    
	    if (m_PrintFormat.save(m_trxName) == true){		    	
	    	record_log (1, m_PrintFormat.getName(),"PrintFormat", m_PrintFormat.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormat",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormat"));           		        		
        }
        else{
        	record_log (0, m_PrintFormat.getName(),"PrintFormat", m_PrintFormat.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormat",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormat"));
        }	    
	}
	
	
	else if (elementValue.equals("printformatitem")) {		
		
		String name = atts.getValue("Name");
		int id = get_IDWithMaster("AD_PrintFormatItem", name,"AD_PrintFormat",atts.getValue("ADPrintFormatNameID"));		
		
	    m_PrintFormatItem = new X_AD_PrintFormatItem(Env.getCtx(), id,null);
	    if (id > 0){
	    	AD_Backup_ID = copyRecord("AD_PrintFormatItem",m_PrintFormatItem);
			Object_Status = "Update";			
	    }
	   else{
			Object_Status = "New";
			AD_Backup_ID =0;
	    }
	    m_PrintFormatItem.setName(name);
	    name = atts.getValue("ADPrintFormatNameID");
	    id = get_IDWithColumn("AD_PrintFormat", "Name", name);	    
	    m_PrintFormatItem.setAD_PrintFormat_ID(id);
	    //name = atts.getValue("ADPrintFormatChildNameID");
	    //id = get_IDWithColumn("AD_PrintFormat", "Name", name);
	    //m_PrintFormatItem.setAD_PrintFormatChild_ID(id);
	    name = atts.getValue("ADTableNameID");	    
	    int tableid = get_IDWithColumn("AD_Table", "TableName", name);	    
	    name = atts.getValue("ADColumnNameID");
	    id =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableid);
	    m_PrintFormatItem.setAD_Column_ID(id);
	    name = atts.getValue("ADPrintGraphID");
	    id = get_IDWithColumn("AD_PrintGraph", "Name", name);
	    m_PrintFormatItem.setAD_PrintGraph_ID(id);
	    name = atts.getValue("ADPrintColorID");
	    id = get_IDWithColumn("AD_PrintColor", "Name", name);
	    m_PrintFormatItem.setAD_PrintColor_ID(id);
	    name = atts.getValue("ADPrintFontID");
	    id = get_IDWithColumn("AD_PrintFont", "Name", name);
	    m_PrintFormatItem.setAD_PrintFont_ID(id);
	    
	    m_PrintFormatItem.setPrintName(atts.getValue("PrintName"));
	    m_PrintFormatItem.setName(atts.getValue("Name"));        
	    m_PrintFormatItem.setPrintAreaType(atts.getValue("PrintAreaType"));
		
	    m_PrintFormatItem.setSeqNo(Integer.parseInt(atts.getValue("SeqNo")));
	    m_PrintFormatItem.setPrintFormatType(atts.getValue("PrintFormatType"));        
	    m_PrintFormatItem.setXSpace(Integer.parseInt(atts.getValue("XSpace")));
		
	    m_PrintFormatItem.setYSpace(Integer.parseInt(atts.getValue("YSpace")));
	    m_PrintFormatItem.setXPosition(Integer.parseInt(atts.getValue("Xposition")));
	    m_PrintFormatItem.setYPosition(Integer.parseInt(atts.getValue("Yposition")));
		
	    m_PrintFormatItem.setMaxWidth(Integer.parseInt(atts.getValue("MaxWidth")));
	    m_PrintFormatItem.setMaxHeight(Integer.parseInt(atts.getValue("MaxHieght")));
	    m_PrintFormatItem.setSortNo(Integer.parseInt(atts.getValue("SortNo")));
		
	    m_PrintFormatItem.setFieldAlignmentType(atts.getValue("FieldAlignmentType"));
	    m_PrintFormatItem.setLineAlignmentType(atts.getValue("LineAlignmentType"));
	    m_PrintFormatItem.setImageURL(atts.getValue("ImageURL"));
	    m_PrintFormatItem.setArcDiameter(Integer.parseInt(atts.getValue("ArcDiameter")));
	    m_PrintFormatItem.setLineWidth(Integer.parseInt(atts.getValue("LineWidth")));
	    m_PrintFormatItem.setShapeType(atts.getValue("ShapeType"));
		
	    m_PrintFormatItem.setBelowColumn(Integer.parseInt(atts.getValue("BelowColumn")));
	    m_PrintFormatItem.setPrintNameSuffix(atts.getValue("PrintNameSuffix"));
	    m_PrintFormatItem.setRunningTotalLines(Integer.parseInt(atts.getValue("RunningTotalLines")));
		
	    m_PrintFormatItem.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	    m_PrintFormatItem.setIsPrinted(Boolean.valueOf(atts.getValue("isPrinted")).booleanValue());
	    m_PrintFormatItem.setIsRelativePosition(Boolean.valueOf(atts.getValue("isRelativePosition")).booleanValue());
	    m_PrintFormatItem.setIsNextLine(Boolean.valueOf(atts.getValue("isNextLine")).booleanValue());
		
	    m_PrintFormatItem.setIsHeightOneLine(Boolean.valueOf(atts.getValue("isHeightOneLine")).booleanValue());
	    m_PrintFormatItem.setIsOrderBy(Boolean.valueOf(atts.getValue("isOrderBy")).booleanValue());
	    m_PrintFormatItem.setIsGroupBy(Boolean.valueOf(atts.getValue("isGroupBy")).booleanValue());
		
	    m_PrintFormatItem.setIsPageBreak(Boolean.valueOf(atts.getValue("isPageBreak")).booleanValue());
	    m_PrintFormatItem.setIsSummarized(Boolean.valueOf(atts.getValue("isSummarized")).booleanValue());
	    m_PrintFormatItem.setImageIsAttached(Boolean.valueOf(atts.getValue("isImageIsAttached")).booleanValue());
		
	    m_PrintFormatItem.setIsAveraged(Boolean.valueOf(atts.getValue("isAveraged")).booleanValue());
	    m_PrintFormatItem.setIsCounted(Boolean.valueOf(atts.getValue("isCounted")).booleanValue());
	    m_PrintFormatItem.setIsSetNLPosition(Boolean.valueOf(atts.getValue("isSetNLPosition")).booleanValue());
	    m_PrintFormatItem.setIsSuppressNull(Boolean.valueOf(atts.getValue("isSuppressNull")).booleanValue());
		
	    m_PrintFormatItem.setIsFixedWidth(Boolean.valueOf(atts.getValue("isFixedWidth")).booleanValue());
	    m_PrintFormatItem.setIsNextPage(Boolean.valueOf(atts.getValue("isNextPage")).booleanValue());
	    m_PrintFormatItem.setIsMaxCalc(Boolean.valueOf(atts.getValue("isMaxCalc")).booleanValue());
	    m_PrintFormatItem.setIsMinCalc(Boolean.valueOf(atts.getValue("isMinCalc")).booleanValue());
		
	    m_PrintFormatItem.setIsRunningTotal(Boolean.valueOf(atts.getValue("isRunningTotal")).booleanValue());
	    m_PrintFormatItem.setIsVarianceCalc(Boolean.valueOf(atts.getValue("isVarianceCalc")).booleanValue());
	    m_PrintFormatItem.setIsDeviationCalc(Boolean.valueOf(atts.getValue("isDeviationCalc")).booleanValue());
		
	    if (m_PrintFormatItem.save(m_trxName) == true){		    	
	    	record_log (1, m_PrintFormatItem.getName(),"PrintFormatItem", m_PrintFormatItem.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormatItem",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormatItem"));           		        		
        }
        else{
        	record_log (0, m_PrintFormatItem.getName(),"PrintFormatItem", m_PrintFormatItem.get_ID(),AD_Backup_ID, Object_Status,"AD_PrintFormatItem",get_IDWithColumn("AD_Table", "TableName", "AD_PrintFormatItem"));
        }   
	}		
	else if (elementValue.equals("reportviewcol")) {
		
		String entitytype = atts.getValue("EntityType");		
		String name = atts.getValue("ADReportViewColID");
		
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			int id = get_ID("AD_Reportview_Col", name);
		    m_Reportview_Col = new X_AD_ReportView_Col(Env.getCtx(), id,null);
		    if (id > 0){
		    	AD_Backup_ID = copyRecord("AD_Reportview_Col",m_Reportview_Col);
				Object_Status = "Update";			
		    }
		   else{
				Object_Status = "New";
				AD_Backup_ID =0;
		    } 
	    	name = atts.getValue("ADReportviewnameID");	    
		    id = get_IDWithColumn("AD_Reference", "Name", name);			    
		    m_Reportview_Col.setAD_ReportView_ID(id);
		    name = atts.getValue("ADColumnNameID");	    
		    id = get_IDWithColumn("AD_Column", "Name", name);			    
		    m_Reportview_Col.setAD_Column_ID(id);  
	        m_Reportview_Col.setFunctionColumn(atts.getValue("ADColumnNameID"));
	        m_Reportview_Col.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Reportview_Col.setIsGroupFunction(Boolean.valueOf(atts.getValue("ADColumnNameID")).booleanValue());		        
	        if (m_Reportview_Col.save(m_trxName) == true){		    	
		    	record_log (1, ""+m_Reportview_Col.getAD_ReportView_ID(),"Reportview_Col", m_Reportview.get_ID(),AD_Backup_ID, Object_Status,"AD_Reportview_Col",get_IDWithColumn("AD_Table", "TableName", "AD_Reportview_Col"));           		        		
            }
            else{
            	record_log (0, ""+m_Reportview_Col.getAD_ReportView_ID(),"Reportview_Col", m_Reportview.get_ID(),AD_Backup_ID, Object_Status,"AD_Reportview_Col",get_IDWithColumn("AD_Table", "TableName", "AD_Reportview_Col"));
            }
	    }
	    }
	else if (elementValue.equals("SQLStatement")) {
		
		String DBType = atts.getValue("DBType");
		PreparedStatement pstmt = DB.prepareStatement(atts.getValue("statement"), m_trxName);	    
		try {
			if(DBType.equals("ALL")){				
				int n = pstmt.executeUpdate();				
				log.info("Executed SQL Statement: "+ atts.getValue("statement"));
			}
			else if(DB.isOracle() == true & DBType.equals("Oracle")){
				pstmt.executeUpdate();
				log.info("Executed SQL Statement for Oracle: "+ atts.getValue("statement"));
			}
/*			else if(DB.isSybase() == true & DBType.equals("Sybase")){
				pstmt.executeUpdate();
				log.info("Exceuted SQL Statement for Sybase");
				}
*/		}
		catch (Exception e)	{
			log.log(Level.SEVERE,"SQLSatement", e);
		}
	}
	else if (elementValue.equals("reference")) {

		String entitytype = atts.getValue("EntityType");		
		String name = atts.getValue("name");

		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			int id = get_ID("AD_Reference", name);

			m_Reference = new X_AD_Reference(Env.getCtx(), id,null);
			if (id > 0){
				AD_Backup_ID = copyRecord("AD_Reference",m_Reference);
				Object_Status = "Update";
				}
		   else{
				Object_Status = "New";
				AD_Backup_ID =0;
		    }

	    	m_Reference.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Reference.setEntityType(atts.getValue("EntityType"));
	        m_Reference.setHelp(atts.getValue("Help").replaceAll("'","''").replaceAll(",",""));
	        m_Reference.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Reference.setName(atts.getValue("name"));
	        
	        //m_Reference.setVFormat(atts.getValue("VFormat"));
	        m_Reference.setValidationType(atts.getValue("ValidationType"));	        
	        if (m_Reference.save(m_trxName) == true){
	        	record_log (1, m_Reference.getName(),"Reference", m_Reference.get_ID(),AD_Backup_ID, Object_Status,"AD_Reference",get_IDWithColumn("AD_Table", "TableName", "AD_Reference"));           		        		
            }
            else{
            	record_log (0, m_Reference.getName(),"Reference", m_Reference.get_ID(),AD_Backup_ID, Object_Status,"AD_Reference",get_IDWithColumn("AD_Table", "TableName", "AD_Reference"));
            }
	        
		    }
	    }
	else if (elementValue.equals("referencelist")) {
		//TODO: Solve for date issues with valuefrom valueto
		String entitytype = atts.getValue("EntityType");		
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {
			String name = atts.getValue("Name");
			int Referenceid = get_IDWithColumn("AD_Reference", "Name", atts.getValue("ADRefenceNameID"));
		    int id = get_IDWithMaster("AD_Ref_List",  name, "AD_Reference",Referenceid);
		    m_Ref_List = new X_AD_Ref_List(Env.getCtx(), id,null);
		    if (id > 0){
		    	AD_Backup_ID = copyRecord("AD_Ref_List",m_Ref_List);
				Object_Status = "Update";			
		    }
		   else{
				Object_Status = "New";
				AD_Backup_ID =0;
		    }
		    name = atts.getValue("ADRefenceNameID");	    
		    id = get_IDWithColumn("AD_Reference", "Name", name);
		    m_Ref_List.setAD_Reference_ID(id);
		    m_Ref_List.setDescription(atts.getValue("Description").replaceAll("'","''").replaceAll(",",""));
	        m_Ref_List.setEntityType (atts.getValue("EntityType"));
	        m_Ref_List.setName(atts.getValue("Name"));
	        m_Ref_List.setIsActive(atts.getValue("isActive") != null ? Boolean.valueOf(atts.getValue("isActive")).booleanValue():true);
	        m_Ref_List.setValue(atts.getValue("Value"));
	        m_Ref_List.save(m_trxName);	        
	        if (m_Ref_List.save(m_trxName) == true){		    	
		    	record_log (1, m_Ref_List.getName(),"Reference List", m_Ref_List.get_ID(),AD_Backup_ID, Object_Status,"AD_Ref_List",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_List"));           		        		
            }
            else{
            	record_log (0, m_Ref_List.getName(),"Reference List", m_Ref_List.get_ID(),AD_Backup_ID, Object_Status,"AD_Ref_List",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_List"));
            }
	    }
	}
	else if (elementValue.equals("referencetable")) {
		
		String entitytype = atts.getValue("EntityType");		
		String name = atts.getValue("ADRefenceNameID");
		if (entitytype.compareTo("U") == 0 | entitytype.compareTo("D") == 0 & m_UpdateMode.compareTo("true") == 0 ) {			
			sqlB = new StringBuffer ("SELECT AD_Reference_ID FROM AD_Reference WHERE Name= ?");
			int id = DB.getSQLValue(null,sqlB.toString(),name);			
			sqlB = new StringBuffer ("SELECT Count(*) FROM AD_Ref_Table WHERE AD_Reference_ID= ?");
			int count = DB.getSQLValue(null, sqlB.toString(),id);	   	
		    int tableId = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));
		    if (tableId ==0){
		    	createtable (atts.getValue("ADTableNameID"));
		    	m_Table = new X_AD_Table(Env.getCtx(), 0,null);
		    	m_Table.setAccessLevel("3");
			    m_Table.setName(atts.getValue("ADTableNameID"));			    
			    m_Table.setTableName(atts.getValue("ADTableNameID"));		    
			    if (m_Table.save(m_trxName) == true){		    	
			    	record_log (1, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
	            }
	            else{
	            	record_log (0, m_Table.getName(),"Table", m_Table.get_ID(),0, "New","AD_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Table"));
	            }
		    	tableId = get_IDWithColumn("AD_Table", "TableName", atts.getValue("ADTableNameID"));
		    }
	    	name = atts.getValue("ADDisplay"); 
	    	int DisplayId =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);	    
		    if (DisplayId ==0){
		    	m_Column = new X_AD_Column(Env.getCtx(),0,null);
		    	m_Column.setAD_Table_ID(tableId);
		    	m_Column.setVersion(new BigDecimal("1"));
		    	m_Column.setColumnName(name);		    	
		    	m_Column.setName(name);
		    	m_Column.setAD_Reference_ID(30);
		    	if (m_Column.save(m_trxName) == true){		    	
			    	record_log (1, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
	            }
	            else{
	            	record_log (0, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
	            }
		    }
		    name = atts.getValue("Key");	    
		    int keyId =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);
		    if (keyId ==0){
		    	m_Column = new X_AD_Column(Env.getCtx(),0,null);
		    	m_Column.setAD_Table_ID(tableId);
		    	m_Column.setVersion(new BigDecimal("1"));
		    	m_Column.setColumnName(name);
		    	m_Column.setName(name);
		    	m_Column.setAD_Reference_ID(30);
		    	if (m_Column.save(m_trxName) == true){
		    		record_log (1, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));           		        		
	            }
	            else{
	            	record_log (0, m_Column.getName(),"Column", m_Column.get_ID(),0, "New","AD_Column",get_IDWithColumn("AD_Table", "TableName", "AD_Column"));
	            }	    	
		    }
				    
		    name = atts.getValue("ADDisplay");	    
		    DisplayId = get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);
		    name = atts.getValue("Key");	    
		    keyId = get_IDWithMasterAndColumn ("AD_Column", "ColumnName", name, "AD_Table", tableId);
		    String entityType = atts.getValue("EntityType");
		    String isValueDisplayed = atts.getValue("IsValueDisplayed");		    
		    String OrderByClause= atts.getValue("OrderByClause").replaceAll("'","''").replaceAll(",","");	
		    String WhereClause= atts.getValue("WhereClause").replaceAll("'","''").replaceAll(",","");
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
					if (no > 0){			    	
				    	record_log (1, atts.getValue("ADRefenceNameID"),"Reference Table", id,0, "Update","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));
		            }
		            else{
		            	record_log (0, atts.getValue("ADRefenceNameID"),"Reference Table", id,0, "Update","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));
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
				if (no > 0){
					record_log (1, atts.getValue("ADRefenceNameID"),"Reference Table", id,0, "New","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));		    	           		        		
	            }
	            else{
	            	record_log (0, atts.getValue("ADRefenceNameID"),"Reference Table", id,0, "New","AD_Ref_Table",get_IDWithColumn("AD_Table", "TableName", "AD_Ref_Table"));
	            }
			}	 	
		}
	}
	else if (elementValue.equals("menuset")) {
		setmenu ();		
	}	
	
	/* ****************************************
	   adempieredata Handler.
	   **************************************** */
	// table element, adempieredata
	else if (elementValue.equals("dtable")) {
	    d_tablename = atts.getValue("name");
	}
	// row element, adempieredata
	else if (elementValue.equals("drow")) {
	    d_rowname = atts.getValue("name");
	    Properties ctx = Env.getCtx();	   
	    ctx.setProperty("adempieredataTable_ID", String.valueOf(get_IDWithColumn("AD_Table", "TableName", d_tablename)));	   	    
	    // name can be null if there are keyXname attributes.
	    if (!d_rowname.equals("")){
	    	int id = get_ID(d_tablename, d_rowname);
	    	genericPO = new IntGenericPO(Env.getCtx(), id);
	    	if (id > 0){
	    		AD_Backup_ID = copyRecord(d_tablename,genericPO);
	    		Object_Status = "Update";			
	    	}
	    	else{
	    		Object_Status = "New";
	    		AD_Backup_ID =0;
	    	}
	    }
	    // keyXname and lookupkeyXname.
	    else {
	    	String sql = "select * from "+d_tablename;
	    	String whereand = " where";
	    	String t_tablename = null;
	    	String CURRENT_KEY = "key1name";		
	    	if (!atts.getValue(CURRENT_KEY).equals("")) {		
	    		t_tablename = atts.getValue(CURRENT_KEY).substring(0, atts.getValue(CURRENT_KEY).length()-3);
	    		sql = sql+whereand+" "+atts.getValue(CURRENT_KEY)+"="+atts.getValue("lookup"+CURRENT_KEY);
	    		whereand = " and";
	    	}
	    	CURRENT_KEY = "key2name";		
	    	if (!atts.getValue(CURRENT_KEY).equals("")) {
	    		t_tablename = atts.getValue(CURRENT_KEY).substring(0, atts.getValue(CURRENT_KEY).length()-3);
	    		sql = sql+whereand+" "+atts.getValue(CURRENT_KEY)+"="+atts.getValue("lookup"+CURRENT_KEY);
	    		whereand = " and";
	    	}
	    	// Load GenericPO from rs, in fact ID could not exist e.g. Attribute Value
	    	try {
	    		PreparedStatement pstmt = DB.prepareStatement(sql, m_trxName);
	    		
	    		ResultSet rs = pstmt.executeQuery();
	    		if (rs.next()) {
	    			Object_Status = "Update";	
	    			genericPO = new IntGenericPO(Env.getCtx(), rs);
	    			rs.close();
	    			pstmt.close();
	    			pstmt = null;
	    		}
	    		else {
	    			genericPO = new IntGenericPO(Env.getCtx(), 0);
	    			rs.close();
	    			pstmt.close();
	    			pstmt = null;
	    			Object_Status = "New";
	    			// set keyXname.
	    			CURRENT_KEY = "key1name";
	    			if (!atts.getValue(CURRENT_KEY).equals("")) {
	    				t_tablename = atts.getValue(CURRENT_KEY).substring(0, atts.getValue(CURRENT_KEY).length()-3);
	    				genericPO.setValueNoCheck(atts.getValue(CURRENT_KEY), atts.getValue("lookup"+CURRENT_KEY));
	    			}
	    			CURRENT_KEY = "key2name";
	    			if (!atts.getValue(CURRENT_KEY).equals("")) {
	    				t_tablename = atts.getValue(CURRENT_KEY).substring(0, atts.getValue(CURRENT_KEY).length()-3);
	    				genericPO.setValueNoCheck(atts.getValue(CURRENT_KEY), atts.getValue("lookup"+CURRENT_KEY));
	    			}
	    		}
	    		
	    	}
	    	catch (Exception e) {
	    		log.info ("keyXname attribute. init from rs error."+e);
	    	}
	    }
	    // reset Table ID for GenericPO.
	    ctx.setProperty("adempieredataTable_ID", "0");
	    // for debug GenericPO.
	    if (false) {
	    	POInfo poInfo = POInfo.getPOInfo(Env.getCtx(), get_ID("AD_Table", d_tablename));
	    	if (poInfo == null)
	    		log.info("poInfo is null.");
	    	for (int i = 0; i < poInfo.getColumnCount(); i++) {
	    		log.info(d_tablename+" column: "+poInfo.getColumnName(i));
	    	}
	    }
	    // globalqss: set AD_Client_ID to the client setted in adempieredata
	    if (m_AD_Client_ID > 0)
	    	genericPO.setValue("AD_Client_ID", m_AD_Client_ID);
	    // if new. TODO: no defaults for keyXname.
	    if (!d_rowname.equals("") && ((Integer)(genericPO.get_Value(d_tablename+"_ID"))).intValue() == 0) {
			log.info("new genericPO, table: "+d_tablename+" name:"+d_rowname);
			genericPO.setValue("Name", d_rowname);
			// Set defaults.
			HashMap thisDefault = (HashMap)defaults.get(d_tablename);
			if (thisDefault != null) {
				Iterator iter = thisDefault.values().iterator();
				ArrayList thisValue = null;
				while (iter.hasNext()) {
					thisValue = (ArrayList)iter.next();
					if (((String)(thisValue.get(2))).equals("String"))
						genericPO.setValue((String)thisValue.get(0), (String)thisValue.get(1));
					else if (((String)(thisValue.get(2))).equals("Integer"))
						genericPO.setValue((String)thisValue.get(0), Integer.valueOf((String)thisValue.get(1)));
					else if (((String)(thisValue.get(2))).equals("Boolean"))
						genericPO.setValue((String)thisValue.get(0), new Boolean(((String)thisValue.get(1)).equals("true") ? true : false));
				}
			}
	    }
	}
	
	// column element, adempieredata
	else if (elementValue.equals("dcolumn")) {
		String columnName = atts.getValue("name");	    	
		int tableid = get_IDWithColumn("AD_Table", "TableName", d_tablename);
		int id =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", columnName, "AD_Table", tableid);
		StringBuffer sql = new StringBuffer ("SELECT IsUpdateable FROM AD_column WHERE AD_Column_ID = ?");
		String isUpdateable = DB.getSQLValueString(m_trxName, sql.toString(),id);
		sql = new StringBuffer ("SELECT IsKey FROM AD_column WHERE AD_Column_ID = ?");
		String isKey = DB.getSQLValueString(m_trxName, sql.toString(),id);
		if (isKey.equals("N") && 
				isUpdateable.equals("Y") &&
				(!atts.getValue("name").equals("CreatedBy")||!atts.getValue("name").equals("UpdatedBy"))) {
			if (atts.getValue("value") != null && !atts.getValue("value").equals("null")) {
				if (atts.getValue("class").equals("String") || atts.getValue("class").equals("Text")
						|| atts.getValue("class").equals("List")|| atts.getValue("class").equals("Yes-No")				
						|| atts.getValue("class").equals("Table")|| atts.getValue("class").equals("Button")
						|| atts.getValue("class").equals("Memo")|| atts.getValue("class").equals("Text Long")) {
					genericPO.setValue(atts.getValue("name").toString(), atts.getValue("value").toString());
				}
				else if (atts.getValue("class").equals("Number") || atts.getValue("class").equals("Amount")
						|| atts.getValue("class").equals("Quantity")|| atts.getValue("class").equals("Costs+Prices")){
					genericPO.setValue(atts.getValue("name").toString(), new BigDecimal(atts.getValue("value")));
				}
				else if (atts.getValue("class").equals("Integer") || atts.getValue("class").equals("ID")
						|| atts.getValue("class").equals("Table Direct")|| atts.getValue("class").equals("Table")
						|| atts.getValue("class").equals("Location (Address)")|| atts.getValue("class").equals("Account")
						|| atts.getValue("class").equals("Color)")|| atts.getValue("class").equals("Search")						
						|| atts.getValue("class").equals("Locator (WH)")|| atts.getValue("class").equals("Product Attribute")) {
					genericPO.setValue(atts.getValue("name").toString(), Integer.valueOf(atts.getValue("value")));
				}	
				else if (atts.getValue("class").equals("Boolean")) {
					genericPO.setValue(atts.getValue("name"), new Boolean(atts.getValue("value").equals("true") ? true : false));
				}
				else if (atts.getValue("class").equals("Date") || atts.getValue("class").equals("Date+Time")
						|| atts.getValue("class").equals("Time")) {
					genericPO.setValue(atts.getValue("name").toString(), Timestamp.valueOf(atts.getValue("value")));
				}//Binary,  Radio, RowID, Image not supported
			} else { // value is null 
				if (atts.getValue("lookupname") != null && !"".equals(atts.getValue("lookupname"))) {
					// globalqss - bring support from XML2AD to lookupname
					String m_tablename = atts.getValue("name").substring(0, atts.getValue("name").length()-3);
					genericPO.setValue(atts.getValue("name"), new Integer(getIDbyName(m_tablename, atts.getValue("lookupname"))));
				}
			}
		}			
	}	
    }   // startElement
    
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
	    log.info ("get_ID:"+e);
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
	sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"=?");
	//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+value.toString());

	if (!tableName.startsWith("AD_"))
	    sqlB = sqlB.append(" and AD_Client_ID=?");
    //here!
	sqlB = sqlB.append(" Order By "+tableName+"_ID");
	try {
	    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
	    if (value instanceof String)
		pstmt.setString(1, (String)value);
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
		log.info ("get_ID:"+e);
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
	sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where name=? and "
		    + tableNameMaster+"_ID = (select "+tableNameMaster+"_ID from "+tableNameMaster+" where name=?)");
	
	try {
	    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(), m_trxName);
	    pstmt.setString(1, name);
	    pstmt.setString(2, nameMaster);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next())
		id = rs.getInt(1);
	    rs.close();
	    pstmt.close();
	    pstmt = null;
	}
	catch (Exception e) {
	    log.info ("get_IDWithMaster:"+e);
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
	sqlB = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"=? and "
		    + tableNameMaster+"_ID =?");
	//StringBuffer sqlC = new StringBuffer ("select "+tableName+"_ID from "+tableName+" where "+columnName+"="+name+" and "
	//	    + tableNameMaster+"_ID ="+masterID);
	//log.info(sqlC.toString());
	
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
	    log.info ("get_IDWithMasterAndColumn:"+e);
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
	    log.info ("get_IDWithMasterID:"+e);
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
			log.log(Level.SEVERE, "getID:"+e);
		}
		return id;
	}
	
    /**
     * Set menu tree.
     *
     * 
     * 
     * 
     */    
    public void setmenu () {
	
    	String name = null;
    	int idDetail =0;
    	int i;
    	for(i=0;i<menu_seq;i++){
    	name = d_menu[i][0];
    	int menuid = get_IDWithColumn("AD_Menu", "Name",name);
    	m_Menu = new X_AD_Menu(Env.getCtx(), menuid,null);    	
    	if (menuid > 0){    		
    		AD_Backup_ID = copyRecord("AD_Menu",m_Menu);
			Object_Status = "Update";			
	    }
	   else{
			Object_Status = "New";
			AD_Backup_ID =0;
	    }
    	
    	m_Menu.setName(name);
    	name = d_menu[i][1];	    
	    int id = get_IDWithColumn("AD_Window", "Name", name);
	    m_Menu.setAD_Window_ID(id);
	    name = d_menu[i][2];	    
	    id = get_IDWithColumn("AD_Process", "Name", name);
	    m_Menu.setAD_Process_ID(id);
	    name = d_menu[i][3];	    
	    id = get_IDWithColumn("AD_Form", "Name", name);
	    m_Menu.setAD_Form_ID(id);
	    name = d_menu[i][4];	    
	    id = get_IDWithColumn("AD_Task", "Name", name);
	    m_Menu.setAD_Task_ID(id);
	    name = d_menu[i][5];	    
	    id = get_IDWithColumn("AD_Workbench", "Name", name);
	    m_Menu.setAD_Workbench_ID(id);  
	    name = d_menu[i][6];	    
	    id = get_IDWithColumn("AD_Workflow", "Name", name);
	    m_Menu.setAD_Workflow_ID(id);    
        if (d_menu[i][7].compareTo(" ") > -1 )
        	m_Menu.setAction(d_menu[i][7]);
        m_Menu.setDescription(d_menu[i][8]);
        m_Menu.setEntityType(d_menu[i][9]);
        m_Menu.setIsReadOnly(Boolean.valueOf(d_menu[i][10]).booleanValue());
        m_Menu.setIsSOTrx(Boolean.valueOf(d_menu[i][11]).booleanValue());
        m_Menu.setIsSummary(Boolean.valueOf(d_menu[i][12]).booleanValue());
        m_Menu.setIsActive(Boolean.valueOf(d_menu[i][15]).booleanValue());
        m_Menu.save(m_trxName);
        if (m_Menu.save(m_trxName) == true){
	    	try {				
	    		idDetail = record_log (1, m_Menu.getName(),"Menu", m_Menu.get_ID(),AD_Backup_ID, Object_Status,"AD_Menu",get_IDWithColumn("AD_Table", "TableName", "AD_Menu"));
			} catch (SAXException e) {
				log.info ("setmenu:"+e);
			}           		        		
        }
        else{
        	try {
        		idDetail = record_log (0, m_Menu.getName(),"Menu", m_Menu.get_ID(),AD_Backup_ID, Object_Status,"AD_Menu",get_IDWithColumn("AD_Table", "TableName", "AD_Menu"));
			} catch (SAXException e) {
				log.info ("setmenu:"+e);
			}
        }
        name = d_menu[i][13];
        id = get_ID("AD_Menu", name);
        
        String sql2 = "SELECT count(Parent_ID) FROM AD_TREENODEMM WHERE AD_Tree_ID = 10"
		    	  + " AND Node_ID = " + menuid;
		int countRecords = DB.getSQLValue(null,sql2);		
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
					    		int columnID = DB.getSQLValue(null, sql.toString(),tableID);
						    	sql = new StringBuffer ("SELECT AD_Reference_ID FROM AD_COLUMN WHERE AD_Column_ID = '"+columnID+"'");
					    		int referenceID = DB.getSQLValue(null,sql.toString());
					    		int idBackup = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Backup", null);
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
				    				+	" "+ Env.getAD_Client_ID(Env.getCtx())
				    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
				    				+	", "+ Env.getAD_User_ID(Env.getCtx())
				    				+	", "+ Env.getAD_User_ID(Env.getCtx())
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
										log.info("Insert to import backup failed");
						    }
			
			    }
			    rs1.close();
			    pstmt1.close();
			    pstmt1 = null;
			
			}
			catch (Exception e) {
			    log.info ("get_IDWithMasterID:"+e);
			}
			
	    	
			sqlB = new StringBuffer ("UPDATE AD_TREENODEMM "
		  	      	  + "SET Parent_ID = " + id 
			      	  + " , SeqNo = " + d_menu[i][14]
			    	  + " WHERE AD_Tree_ID = 10"
			    	  + " AND Node_ID = " + m_Menu.getAD_Menu_ID());
		}
		else{
			sqlB = new StringBuffer ("Insert INTO AD_TREENODEMM"
	        		+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
					+	"Parent_ID, SeqNo, AD_Tree_ID, Node_ID)"
					+	"VALUES(0, 0, 0, 0, "
					+	id + ","+ d_menu[i][14]+", 10, "+m_Menu.getAD_Menu_ID()+")");        
		}		
	     DB.executeUpdate(sqlB.toString(), m_trxName);	      
    	}
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
    	String recordLayout;
    	int id = 0;
    	if (success == 1){    		
    		//hd_documemt.startElement("","","Successfull",attsOut);
			recordLayout = "Type:"+objectType + "  -   Name:"+objectName + "  -  ID:"+objectID +"  -  Action:"+objectStatus+"  -  Success";
			hd_documemt.startElement("","","Success",attsOut);
    		hd_documemt.characters(recordLayout.toCharArray(),0,recordLayout.length());
    		hd_documemt.endElement("","","Success");
    		//hd_documemt.endElement("","","Successfull");
    		
    		//String sql2 = "SELECT MAX(AD_PACKAGE_IMP_DETAIL_ID) FROM AD_PACKAGE_IMP_DETAIL";
    		//int id = DB.getSQLValue(null, sql2)+1;
    		
    		id = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Detail", null);
    		
    		sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Detail" 
    				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
    				+   "AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID, TYPE, NAME," 
    				+   " ACTION, SUCCESS, AD_ORIGINAL_ID, AD_BACKUP_ID, TABLENAME, AD_TABLE_ID)"
    				+	"VALUES("
    				+	" "+ Env.getAD_Client_ID(Env.getCtx())
    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
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
    		if (no == -1)
				log.info("Insert to import detail failed");
    		
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
    		//int id = DB.getSQLValue(null,sql2)+1; 
    		
    		id = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Detail", null);
    		
    		sqlB = new StringBuffer ("Insert INTO AD_Package_Imp_Detail" 
    				+   "(AD_Client_ID, AD_Org_ID, CreatedBy, UpdatedBy, " 
    				+   "AD_PACKAGE_IMP_DETAIL_ID, AD_PACKAGE_IMP_ID, TYPE, NAME," 
    				+   " ACTION, SUCCESS, AD_ORIGINAL_ID, AD_BACKUP_ID, TABLENAME, AD_TABLE_ID)"
    				+	"VALUES("
    				+	" "+ Env.getAD_Client_ID(Env.getCtx())
    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
    				+	", "+ Env.getAD_User_ID(Env.getCtx())
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
				log.info("Insert to import detail failed");
    	}
    	
	 Object_Status = "Status not set";
	 return id;  
    }
    /**
     *	Check if table exists in database.  If not create
     *
     *      @param tablename
     *       	
     */
    public void createtable (String tablename){
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
    		sqlB = new StringBuffer ("SELECT Count(*) FROM AD_Sequence WHERE Name = ?");    		
			int count = DB.getSQLValue(null, sqlB.toString(),tablename);
			if (count == 0)
				MSequence.createTableSequence (Env.getCtx(), tablename, null);	
    	}
    	else {    		

    		sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( "
   				 + tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, "
				 + "AD_CLIENT_ID NUMBER(10) NOT NULL, "
				 + "AD_ORG_ID  NUMBER(10) NOT NULL, "
				 + "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, "
				 + "CREATED DATE DEFAULT SYSDATE NOT NULL, "
				 + "CREATEDBY NUMBER(10) NOT NULL, "
				 + "UPDATED DATE DEFAULT SYSDATE NOT NULL, "
				 + "UPDATEDBY NUMBER(10) NOT NULL, "
				 + "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );
    		
    		try {
    		    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
    		    pstmt.executeUpdate();
    		    MSequence.createTableSequence (Env.getCtx(), tablename, null);
    		    pstmt.close();
    		    pstmt = null;
    		}
    		catch (Exception e) {
    		    log.info ("createtable:"+e);
    		}
    	}
    	tables.close();
    	}
		
    	 catch (SQLException e) {
    	 	log.info ("createtable:"+e);
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
        		sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( "
       				 + tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, "
    				 + "AD_CLIENT_ID NUMBER(10) NOT NULL, "
    				 + "AD_ORG_ID  NUMBER(10) NOT NULL, "
    				 + "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, "
    				 + "CREATED DATE DEFAULT SYSDATE NOT NULL, "
    				 + "CREATEDBY NUMBER(10) NOT NULL, "
    				 + "UPDATED DATE DEFAULT SYSDATE NOT NULL, "
    				 + "UPDATEDBY NUMBER(10) NOT NULL, "
    				 + "NAME NVARCHAR2(60) NOT NULL, "
    				 + "PK_STATUS NVARCHAR2(22), "
					 + "RELEASENO NVARCHAR2(20), "
					 + "PK_VERSION NVARCHAR2(20), " 
					 + "VERSION NVARCHAR2(20), "
					 + "DESCRIPTION NVARCHAR2(1000) NOT NULL, " 
					 + "EMAIL NVARCHAR2(60), "
					 + "PROCESSED CHAR(1) DEFAULT 'N', "
					 + "PROCESSING CHAR(1) DEFAULT 'N', "
					 + "CREATOR VARCHAR2(60 ), " 
					 + "CREATORCONTACT VARCHAR2(255), " 
					 +" CREATEDDATE  VARCHAR2(25), " 
					 + "UPDATEDDATE VARCHAR2(25), "					 
    				 + "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        		
        		
        		try {
        		    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
        		    pstmt.executeUpdate();
        		    MSequence.createTableSequence (Env.getCtx(), "AD_Package_Imp", null);
        		    pstmt.close();
        		    pstmt = null;
        		}
        		catch (Exception e) {
        		    log.info ("createImp_Sum_table:"+e);
        			}
        		}
				if (tablename.equals("AD_Package_Imp_Inst")){
	        		sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( "
	       				 + tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, "
	    				 + "AD_CLIENT_ID NUMBER(10) NOT NULL, "
	    				 + "AD_ORG_ID  NUMBER(10) NOT NULL, "
	    				 + "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, "
	    				 + "CREATED DATE DEFAULT SYSDATE NOT NULL, "
	    				 + "CREATEDBY NUMBER(10) NOT NULL, "
	    				 + "UPDATED DATE DEFAULT SYSDATE NOT NULL, "
	    				 + "UPDATEDBY NUMBER(10) NOT NULL, "
	    				 + "NAME NVARCHAR2(60) NOT NULL, "
	    				 + "PK_STATUS NVARCHAR2(22), "
						 + "RELEASENO NVARCHAR2(20), "
						 + "PK_VERSION NVARCHAR2(20), " 
						 + "VERSION NVARCHAR2(20), "
						 + "DESCRIPTION NVARCHAR2(1000) NOT NULL, " 
						 + "EMAIL NVARCHAR2(60), "
						 + "PROCESSED CHAR(1) DEFAULT 'N', "
						 + "PROCESSING CHAR(1) DEFAULT 'N', "
						 + "CREATOR VARCHAR2(60 ), " 
						 + "CREATORCONTACT VARCHAR2(255), " 
						 +" CREATEDDATE  VARCHAR2(25), " 
						 + "UPDATEDDATE VARCHAR2(25), "					 
	    				 + "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        		
	        		
	        		try {
	        		    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE, null);
	        		    pstmt.executeUpdate();
	        		    MSequence.createTableSequence (Env.getCtx(), "AD_Package_Imp_Inst", null);
	        		    pstmt.close();
	        		    pstmt = null;
	        		}
	        		catch (Exception e) {
	        		    log.info ("createImp_Sum_table:"+e);
	        			}
	        		}
        		if (tablename.equals("AD_Package_Imp_Detail")){
            		sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( "
           				 + tablename.toUpperCase()+"_ID   NUMBER(10) NOT NULL, "
        				 + "AD_CLIENT_ID NUMBER(10) NOT NULL, "
        				 + "AD_ORG_ID  NUMBER(10) NOT NULL, "
        				 + "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, "
        				 + "CREATED DATE DEFAULT SYSDATE NOT NULL, "
        				 + "CREATEDBY NUMBER(10) NOT NULL, "
        				 + "UPDATED DATE DEFAULT SYSDATE NOT NULL, "
        				 + "UPDATEDBY NUMBER(10) NOT NULL, "
        				 + "NAME NVARCHAR2(60), "
        				 + "AD_PACKAGE_IMP_ID Number(10) NOT NULL, "  
    					 + "AD_ORIGINAL_ID Number(10) NOT NULL, "
    					 + "AD_BACKUP_ID Number(10), "
    					 + "ACTION NVARCHAR2(20), " 
    					 + "SUCCESS NVARCHAR2(20), "
    					 + "TYPE NVARCHAR2(60), " 
    					 + "TABLENAME NVARCHAR2(60), "
    					 + "AD_TABLE_ID NUMBER(10), "
						 + "UNINSTALL CHAR(1), "
        				 + "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        		
            	
            		try {
            		    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
            		    pstmt.executeUpdate();
            		    MSequence.createTableSequence (Env.getCtx(), "AD_Package_Imp_Detail", null);
            		    pstmt.close();
            		    pstmt = null;
            		}
            		catch (Exception e) {
            		    log.info ("createImp_Sum_table:"+e);
            			}
            		}
        		if (tablename.equals("AD_Package_Imp_Backup")){
            		sqlB = new StringBuffer ("CREATE TABLE "+ tablename.toUpperCase() + "( "
           				 + tablename.toUpperCase()+"_ID NUMBER(10) NOT NULL, "
        				 + "AD_CLIENT_ID NUMBER(10) NOT NULL, "
        				 + "AD_ORG_ID  NUMBER(10) NOT NULL, "
        				 + "ISACTIVE CHAR(1) DEFAULT 'Y' NOT NULL, "
        				 + "CREATED DATE DEFAULT SYSDATE NOT NULL, "
        				 + "CREATEDBY NUMBER(10) NOT NULL, "
        				 + "UPDATED DATE DEFAULT SYSDATE NOT NULL, "
        				 + "UPDATEDBY NUMBER(10) NOT NULL, "        				 
        				 + "AD_PACKAGE_IMP_ID Number(10) NOT NULL, "
        				 + "AD_PACKAGE_IMP_DETAIL_ID Number(10) NOT NULL, "    					 
    					 + "AD_TABLE_ID NUMBER(10), "
    					 + "AD_COLUMN_ID NUMBER(10), "
    					 + "AD_REFERENCE_ID NUMBER(10), "
						 + "AD_PACKAGE_IMP_BCK_DIR NVARCHAR2(255), "
						 + "AD_PACKAGE_IMP_ORG_DIR NVARCHAR2(255), "
    					 + "COLVALUE NVARCHAR2(2000), "
						 + "UNINSTALL CHAR(1), "
        				 + "PRIMARY KEY( "+tablename.toUpperCase() +"_ID)"+")" );        	
            		
            		try {
            		    PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
            		    pstmt.executeUpdate();
            		    MSequence.createTableSequence (Env.getCtx(), "AD_Package_Imp_Backup", null);
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
     *	Find and replace code
     *
     *      @param file name
     *  	@param old string
     *  	@param new string
     *       	
     */
    public static int readReplace(String fname, String oldPattern, String replPattern){
    	String line;
    	StringBuffer sb = new StringBuffer();
    	
    	try {
    		
    		FileInputStream fis = new FileInputStream(fname);
    		BufferedReader reader=new BufferedReader ( new InputStreamReader(fis));
    		while((line = reader.readLine()) != null) {
    			line = line.replaceAll(oldPattern, replPattern);
    			System.err.println(line);
    			sb.append(line+"\n");    			
    		}    		
    		reader.close();
    		BufferedWriter out=new BufferedWriter ( new FileWriter(fname));
    		out.write(sb.toString());
    		out.close();
    	}
    	catch (Throwable e) {    			
    	            System.err.println("error replacing codesnipit "+e);
    	            return -1;
    	}
    	return 0;
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
		POInfo poInfo = POInfo.getPOInfo(Env.getCtx(), tableID);
		for (int i = 0; i < poInfo.getColumnCount(); i++){
			String colName = poInfo.getColumnName(i);
			colValue=null;
			
			    int columnID =get_IDWithMasterAndColumn ("AD_Column", "ColumnName", poInfo.getColumnName(i), "AD_Table", tableID);
			    StringBuffer sqlD = new StringBuffer("SELECT AD_Reference_ID FROM AD_COLUMN WHERE AD_Column_ID = '"+columnID+"'");
	    		int referenceID = DB.getSQLValue(null,sqlD.toString());
	    		
	    		idBackup = MSequence.getNextID (Env.getAD_Client_ID(Env.getCtx()), "AD_Package_Imp_Backup", null);
	    		
	    		sqlD = new StringBuffer("SELECT MAX(AD_PACKAGE_IMP_DETAIL_ID) FROM AD_PACKAGE_IMP_DETAIL");
	    		int idDetail = DB.getSQLValue(null,sqlD.toString())+1;
	    		
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
	    				+	" "+ Env.getAD_Client_ID(Env.getCtx())
	    				+	", "+ Env.getAD_Org_ID(Env.getCtx())
	    				+	", "+ Env.getAD_User_ID(Env.getCtx())
	    				+	", "+ Env.getAD_User_ID(Env.getCtx())
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
					log.info("Insert to import backup failed");
	    		//}
		}		
		return idBackup;
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
    public int createcolumn (String tablename, String columnname, int v_AD_Reference_ID, int v_FieldLength, String v_DefaultValue, boolean v_IsMandatory ){
	// Check if column exists.    	

    	Connection conn = DB.getConnectionRW();
    	DatabaseMetaData dbm;

	StringBuffer sqlC = new StringBuffer ("no");
    	int column_Created=1;
		try {
		dbm = conn.getMetaData();		
		// check if column is exists

		ResultSet tables = null;
		if (m_DatabaseType.equals("Oracle"))
	    	tables = dbm.getColumns(null, null, tablename.toUpperCase(),columnname.toUpperCase());
		else if (m_DatabaseType.equals("PostgreSQL"))
	    	tables = dbm.getColumns(null, null, tablename.toLowerCase(),columnname.toLowerCase());

    	if (tables.next()) {
    		Object_Status = "Update";
		if (m_DatabaseType.equals("Oracle"))
	    		sqlB = new StringBuffer ("ALTER TABLE " + tablename + " MODIFY " + columnname + " ");
		else if (m_DatabaseType.equals("PostgreSQL"))
	    		sqlB = new StringBuffer ("ALTER TABLE " + tablename + " ALTER " + columnname + " TYPE ");

    	//Get Metadata for Column
    		Statement stmt = DB.createStatement();	
    		StringBuffer metasql = new StringBuffer("Select "+columnname+" From "+	tablename);
    		ResultSet rs = stmt.executeQuery(metasql.toString());		
    		ResultSetMetaData meta = rs.getMetaData(); 
						
			if (v_AD_Reference_ID == 10 || v_AD_Reference_ID == 14 || v_AD_Reference_ID == 34 || v_AD_Reference_ID == 17)
				sqlB = sqlB.append("NVARCHAR2(" + v_FieldLength + ")");
			else if (v_AD_Reference_ID == 20|| v_AD_Reference_ID == 28)
				sqlB = sqlB.append("CHAR(" + v_FieldLength + ")");
			else if ( v_AD_Reference_ID == 13|| v_AD_Reference_ID == 18|| v_AD_Reference_ID == 19|| v_AD_Reference_ID == 21|| v_AD_Reference_ID == 25|| v_AD_Reference_ID == 27|| v_AD_Reference_ID == 30|| v_AD_Reference_ID == 31|| v_AD_Reference_ID == 35) 
				sqlB = sqlB.append("NUMBER(10)");
			else if ( v_AD_Reference_ID == 11|| v_AD_Reference_ID == 12|| v_AD_Reference_ID == 22|| v_AD_Reference_ID == 29){
				//if (m_DatabaseType.equals("Oracle")){
					sqlB = sqlB.append("NUMBER");
				//	}
				//else if (m_DatabaseType.equals("PostgreSQL")){
				//	sqlB = sqlB.append("NUMERIC");
				//	}
    		}
    		else if (v_AD_Reference_ID == 15|| v_AD_Reference_ID == 16)
			{
				//if (m_DatabaseType.equals("Oracle")){
					sqlB = sqlB.append("Date");
//}
				//else if (m_DatabaseType.equals("PostgreSQL")){
				//	sqlB = sqlB.append("TIMESTAMP");}
			}		
    			
			else    //	23-Binary, 24-Radio, 26-RowID, 32-Image not supported
			//		Default (literal)
			if (!v_DefaultValue.equals(null) & v_DefaultValue.length() != 0) 
					if ( v_AD_Reference_ID == 10|| v_AD_Reference_ID == 14|| v_AD_Reference_ID == 17|| v_AD_Reference_ID == 20|| v_AD_Reference_ID == 28) 
					{
						if (m_DatabaseType.equals("Oracle"))
							sqlB = sqlB.append(" DEFAULT ('" + v_DefaultValue + "')");
						else if (m_DatabaseType.equals("PostgreSQL")){
							sqlB = sqlB.append(" SET DEFAULT ('" + v_DefaultValue + "')");}
					else{
						if (m_DatabaseType.equals("Oracle"))
							sqlB = sqlB.append(" DEFAULT " + v_DefaultValue);
						else if (m_DatabaseType.equals("PostgreSQL"))
							sqlB = sqlB.append(" SET DEFAULT " + v_DefaultValue);
					}
					}

			//		Mandatory
			if (v_IsMandatory == true && meta.isNullable(1)== 1){
					if (m_DatabaseType.equals("Oracle"))
						sqlB = sqlB.append(" NOT NULL");
					else if (m_DatabaseType.equals("PostgreSQL"))
						sqlC = new StringBuffer("ALTER TABLE " + tablename + " ALTER " + columnname +" SET NOT NULL");}
			else if (v_IsMandatory == false && meta.isNullable(1)!= 1){
					if (m_DatabaseType.equals("Oracle"))
						sqlB = sqlB.append(" NULL");
					else if (m_DatabaseType.equals("PostgreSQL"))
						sqlC = new StringBuffer("ALTER TABLE " + tablename + " ALTER " + columnname +" DROP NOT NULL"); }
			
			//log.info(sqlB.toString());
			try {
				PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
			}			
			catch (Exception e) {
				log.info ("createcolumn:"+e+"sql statement "+sqlB.toString());
				column_Created = 0;
			}

			if(!sqlC.toString().equals("no")){
			try {
				PreparedStatement pstmt = DB.prepareStatement(sqlC.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = null;
			}			
			catch (Exception e) {
				log.info ("createcolumn:"+e+"sql statement "+sqlC.toString());
				column_Created = 0;
			}
			}
			rs.close();	
			rs = null;			
			stmt.close();
			stmt=null;			
    	}
    	else {
    		Object_Status = "New";
    		sqlB = new StringBuffer ("ALTER TABLE " + tablename + " ADD " + columnname + " ");
//    			Map Data Type

    		 if (v_AD_Reference_ID == 10 || v_AD_Reference_ID == 14 || v_AD_Reference_ID == 34 || v_AD_Reference_ID == 17)
    			sqlB = sqlB.append("NVARCHAR2(" + v_FieldLength + ")");
    		else if (v_AD_Reference_ID == 20|| v_AD_Reference_ID == 28)
    			sqlB = sqlB.append("CHAR(" + v_FieldLength + ")");
    		else if ( v_AD_Reference_ID == 13|| v_AD_Reference_ID == 18|| v_AD_Reference_ID == 19|| v_AD_Reference_ID == 21|| v_AD_Reference_ID == 25|| v_AD_Reference_ID == 27|| v_AD_Reference_ID == 30|| v_AD_Reference_ID == 31|| v_AD_Reference_ID == 35) 
    			sqlB = sqlB.append("NUMBER(10)");
    		else if ( v_AD_Reference_ID == 11|| v_AD_Reference_ID == 12|| v_AD_Reference_ID == 22|| v_AD_Reference_ID == 29)
			{
				//if (m_DatabaseType.equals("Oracle")){
					sqlB = sqlB.append("NUMBER");
				//	}
				//else if (m_DatabaseType.equals("PostgreSQL")){
				//	sqlB = sqlB.append("NUMERIC");
				//	}
    		}
    		else if (v_AD_Reference_ID == 15|| v_AD_Reference_ID == 16)
			{
				//if (m_DatabaseType.equals("Oracle")){
					sqlB = sqlB.append("Date");
//}
				//else if (m_DatabaseType.equals("PostgreSQL")){
				//	sqlB = sqlB.append("TIMESTAMP");}
			}		
    			
    		else    //	23-Binary, 24-Radio, 26-RowID, 32-Image not supported    					
//    			Default (literal)
    		if (v_DefaultValue != null && v_DefaultValue.length() != 0) 
			if (!v_DefaultValue.equals(null) & v_DefaultValue.length() != 0) 
				if ( v_AD_Reference_ID == 10|| v_AD_Reference_ID == 14|| v_AD_Reference_ID == 17|| 					v_AD_Reference_ID == 20|| v_AD_Reference_ID == 28) 
					{
						if (m_DatabaseType.equals("Oracle"))
							sqlB = sqlB.append(" DEFAULT ('" + v_DefaultValue + "')");
						else if (m_DatabaseType.equals("PostgreSQL")){
							sqlB = sqlB.append(" SET DEFAULT ('" + v_DefaultValue + "')");}
					else{
						if (m_DatabaseType.equals("Oracle"))
							sqlB = sqlB.append(" DEFAULT " + v_DefaultValue);
						else if (m_DatabaseType.equals("PostgreSQL"))
							sqlB = sqlB.append(" SET DEFAULT " + v_DefaultValue);
					}
					}
//    			Mandatory
			if (v_IsMandatory == true ){
					if (m_DatabaseType.equals("Oracle"))
						sqlB = sqlB.append(" NOT NULL");
					else if (m_DatabaseType.equals("PostgreSQL"))
						sqlC = new StringBuffer("ALTER TABLE " + tablename + " ALTER " + columnname +" SET NOT NULL");}
			else if (v_IsMandatory == false ){
					if (m_DatabaseType.equals("Oracle"))
						sqlB = sqlB.append(" NULL");
					else if (m_DatabaseType.equals("PostgreSQL"))
						sqlC = new StringBuffer("ALTER TABLE " + tablename + " ALTER " + columnname +" DROP NOT NULL"); }

    		try {
    			PreparedStatement pstmt = DB.prepareStatement(sqlB.toString(),ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_UPDATABLE, null);
    			pstmt.executeUpdate();
    			pstmt.close();
    			pstmt = null;
    			conn.close();
    			conn = null;
    	
    		}
    		catch (Exception e) {
    			log.info ("createcolumn:"+e);
    			column_Created = 0;
    		}
		if(!sqlC.toString().equals("no")){
		try {
			PreparedStatement pstmt = DB.prepareStatement(sqlC.toString(),ResultSet.TYPE_FORWARD_ONLY,
			ResultSet.CONCUR_UPDATABLE, null);
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = null;
		}			
		catch (Exception e) {
			log.info ("createcolumn:"+e+"sql statement "+sqlC.toString());
			column_Created = 0;
		}
	}

    		
    	}
    	tables.close();
    	}
		catch (SQLException e) {
			
			e.printStackTrace();
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
		return column_Created;
    }

    /**
     *	Open input file for processing
     *
     * 	@param String file with path
     * 	
     */
    public FileInputStream OpenInputfile (String filePath) {
    
    FileInputStream fileTarget = null;
    
    try {    	
    	fileTarget = new FileInputStream(filePath);
     }
     catch (FileNotFoundException e ) {
        System.out.println("Can't find file ");
        
        return null;
     }
     return fileTarget;
    }
    
    /**
     *	Open output file for processing
     *
     * 	@param String file with path
     * 	
     */
    public OutputStream OpenOutputfile (String filePath) {
    
    	OutputStream fileTarget = null;
    
    try {    	
    	fileTarget = new FileOutputStream(filePath);
     }
     catch (FileNotFoundException e ) {
        System.out.println("Can't find file ");
        
        return null;
     }
     return fileTarget;
    }
    
    /**
     *	Copyfile
     *
     * 	@param String file with path
     * 	
     */
    public int copyFile (InputStream source,OutputStream target) {
    
    	 int byteCount = 0;
    	 int success = 0;
	        try {
	           while (true) {
	              int data = source.read();
	              if (data < 0)
	                 break;
	              target.write(data);
	              byteCount++;
	           }
	           source.close();
	           target.close();
	          
	           
	           System.out.println("Successfully copied " + byteCount + " bytes.");
	        }
	        catch (Exception e) {
	           System.out.println("Error occurred while copying.  "+ byteCount + " bytes copied.");
	           System.out.println(e.toString());
	           
	           success = -1;
	        }
	    return success;
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
	if (false)
	    log.info("endElement: "+elementValue);
	/* ****************************************
	   adempieredata Handler.
	   **************************************** */
	// row element, adempieredata
	else if (elementValue.equals("drow")){		
		genericPO.save(m_trxName);
	    if (genericPO.save(m_trxName)== true)
	    	record_log (1, genericPO.get_TableName(),"Data", genericPO.get_ID(),AD_Backup_ID, Object_Status,d_tablename,get_IDWithColumn("AD_Table", "TableName", d_tablename));
        else
        	record_log (0, genericPO.get_TableName(),"Data", genericPO.get_ID(),AD_Backup_ID, Object_Status,d_tablename,get_IDWithColumn("AD_Table", "TableName", d_tablename));
            
	    genericPO = null;
	}
	else if (elementValue.equals("adempiereAD")){
		if (!PK_Status.equals("Completed with errors"))
			PK_Status = "Completed successfully";
		
		//Update package history log with package status
		sqlB = new StringBuffer ("UPDATE AD_Package_Imp "
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

		hd_documemt.endElement("","","compiereDocument");
		hd_documemt.endDocument();		
	}
    }   // endElement
    
    // globalqss - add support for trx in 3.1.2
	public void set_TrxName(String trxName) {
		m_trxName = trxName;
	}
    
}   // PackInHandler

// Marco LOMBARDO, 2004-09-02, Italy.
// lombardo@mayking.com
// Robert KLEIN, 2005-03-22, USA
// RobEKlein@hotmail.com

