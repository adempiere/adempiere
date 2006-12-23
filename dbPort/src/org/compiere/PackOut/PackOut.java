/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.                *
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
 * Copyright (C)
 * 2004 Robert KLEIN. robeklein@hotmail.com * 
 * Contributor(s): ______________________________________.
 *****************************************************************************/
package org.compiere.PackOut;

import java.io.*;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.PackOut.CreateZipFile;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import org.compiere.model.X_AD_Column;
import org.compiere.model.X_AD_Field;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_AD_Process_Para;
import org.compiere.model.X_AD_Tab;
import org.compiere.model.X_AD_Table;
import org.compiere.model.X_AD_Window;
import org.compiere.model.X_AD_Preference;
import org.compiere.model.X_AD_Menu;
import org.compiere.model.X_AD_PrintFormatItem;
import org.compiere.model.X_AD_Task;
import org.compiere.model.X_AD_Form;
import org.compiere.model.X_AD_Workbench;
import org.compiere.model.X_AD_WorkbenchWindow;
import org.compiere.model.X_AD_Reference;
import org.compiere.model.X_AD_PrintFormat;
import org.compiere.model.X_AD_ImpFormat;
import org.compiere.model.X_AD_ImpFormat_Row;
import org.compiere.model.X_AD_Ref_List;
import org.compiere.model.X_AD_ReportView;
import org.compiere.model.X_AD_ReportView_Col;
import org.compiere.model.X_AD_Role;
import org.compiere.model.X_AD_Window_Access;
import org.compiere.model.X_AD_Process_Access;
import org.compiere.model.X_AD_Form_Access;
import org.compiere.model.X_AD_Workflow_Access;
import org.compiere.model.X_AD_Task_Access;
import org.compiere.model.X_AD_Role_OrgAccess;
import org.compiere.model.X_AD_User_Roles;
import org.compiere.util.DB;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.compiere.process.*;


/**
 *	Convert AD to XML
 *	
 *  @author Robert Klein
 *  @version $Id: PackOut.java,v 1.0
 *  
 */

public class PackOut extends SvrProcess
{
	/** Record ID				*/
	private int p_PackOut_ID = 0;
	private X_AD_Window m_Window = null;    
    private X_AD_Process_Para m_Processpara = null;
    private X_AD_Table m_Table = null;
    private X_AD_Column m_Column = null;
    private X_AD_Tab m_Tab = null;
    private X_AD_PrintFormat m_Printformat = null;
    private X_AD_Field m_Field = null; 
    private X_AD_Menu m_Menu = null;
    private X_AD_Task m_Task = null;
    private X_AD_PrintFormatItem m_PrintFormatItem = null;
    private X_AD_Form m_Form = null;
    private X_AD_Workbench m_Workbench = null;
    private X_AD_WorkbenchWindow m_Workbenchwindow = null;
    private X_AD_Reference m_Reference = null;
    private X_AD_Ref_List m_Ref_List = null; 
    private X_AD_ReportView m_Reportview = null;
    private X_AD_ReportView_Col m_Reportview_Col = null;
    private X_AD_Role m_Role = null;
    private X_AD_Preference m_Preference= null;
    private X_AD_ImpFormat m_ImpFormat= null;
    private X_AD_ImpFormat_Row m_ImpFormat_Row= null;
    private X_AD_Window_Access m_Window_Access = null;
    private X_AD_Process_Access m_Process_Access = null;
    private X_AD_Form_Access m_Form_Access = null;
    private X_AD_Workflow_Access m_Workflow_Access = null;
    private X_AD_Task_Access m_Task_Access = null;
    private X_AD_Role_OrgAccess m_Role_OrgAccess = null;
    private X_AD_User_Roles m_User_Role = null;
    private String PackOutVer = "005";
    private String packagedir = null;
    private String packagename = null;
    private String includesdir = null;
    private int Table_ID[] = new int [1000];
    private int Table_Count = 0;
    private int PK_AD_Client_ID = 0;
    private int PK_AD_Org_ID = 0;
    String fileSeperator=null;
    /**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		p_PackOut_ID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
		}		
	}	//	prepare

	
	/**
	 * 	Start the transformation to XML
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		log.info("doIt - AD_PACKAGE_EXP_ID=" + p_PackOut_ID);
		if (p_PackOut_ID == 0)
			throw new IllegalArgumentException("No Record");
		String sql1 = "SELECT * FROM AD_Package_Exp WHERE AD_Package_Exp_ID = "+p_PackOut_ID;		
		PreparedStatement pstmt1 = null;		
		pstmt1 = DB.prepareStatement (sql1, get_TrxName());		
		
		try {			
		ResultSet rs1 = pstmt1.executeQuery();
		while (rs1.next()){		
			//Create the package documentation
			File file = new File("");
			fileSeperator = file.separator;
			packagedir = rs1.getString("File_Directory");			
			packagename = packagedir + rs1.getString("PK_Name");
			includesdir = rs1.getString("PK_Name") + fileSeperator+"**";
			boolean success = (new File(rs1.getString("File_Directory") + rs1.getString("PK_Name")+fileSeperator+"doc"+fileSeperator )).mkdirs();
			String file_document = rs1.getString("File_Directory") + rs1.getString("PK_Name") +fileSeperator+ "doc"+fileSeperator+rs1.getString("PK_Name")+"Doc.xml";		
			OutputStream  fw_document = new FileOutputStream (file_document, false);		
			StreamResult streamResult_document = new StreamResult(fw_document);		
			SAXTransformerFactory tf_document = (SAXTransformerFactory) SAXTransformerFactory.newInstance();	
			TransformerHandler hd_documemt = tf_document.newTransformerHandler();		
			Transformer serializer_document = hd_documemt.getTransformer();		
			serializer_document.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
			serializer_document.setOutputProperty(OutputKeys.INDENT,"yes");		
			hd_documemt.setResult(streamResult_document);
			hd_documemt.startDocument();
			AttributesImpl atts = new AttributesImpl();
			atts.clear();			
			hd_documemt.processingInstruction("xml-stylesheet","type=\"text/css\" href=\"compiereDocument.css\"");
			hd_documemt.startElement("","","compiereDocument",atts);
			hd_documemt.startElement("","","header",atts);		
			hd_documemt.characters((rs1.getString("PK_Name")+" Package Description").toCharArray(),0,(rs1.getString("PK_Name")+" Package Description").length());
			hd_documemt.endElement("","","header");
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Package Name:" ).toCharArray(),0,("Package Name:" ).length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","packagename",atts);		
			hd_documemt.characters(rs1.getString("PK_Name").toCharArray(),0,rs1.getString("PK_Name").length());
			hd_documemt.endElement("","","packagename");					
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Creator:" ).toCharArray(),0,("Creator:").length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","creator",atts);
			hd_documemt.characters(rs1.getString("UserName").toCharArray(),0,rs1.getString("UserName").length());
			hd_documemt.endElement("","","creator");					
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Email Address:" ).toCharArray(),0,("Email Address:" ).length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","creatorcontact",atts);
			hd_documemt.characters(rs1.getString("Email").toCharArray(),0,rs1.getString("Email").length());
			hd_documemt.endElement("","","creatorcontact");					
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Created:" ).toCharArray(),0,("Created:" ).length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","createddate",atts);
			hd_documemt.characters(rs1.getString("Created").toString().toCharArray(),0,rs1.getString("Created").toString().length());
			hd_documemt.endElement("","","createddate");					
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Updated:" ).toCharArray(),0,("Updated:" ).length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","updateddate",atts);
			hd_documemt.characters(rs1.getString("Updated").toString().toCharArray(),0,rs1.getString("Updated".toString()).length());
			hd_documemt.endElement("","","updateddate");				
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Description:" ).toCharArray(),0,("Description:" ).length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","description",atts);
			hd_documemt.characters(rs1.getString("Description").toCharArray(),0,rs1.getString("Description").length());
			hd_documemt.endElement("","","description");					
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Instructions:" ).toCharArray(),0,("Instructions:" ).length());
			hd_documemt.endElement("","","H1");
			hd_documemt.startElement("","","instructions",atts);
			hd_documemt.characters(rs1.getString("Instructions").toCharArray(),0,rs1.getString("Instructions").length());
			hd_documemt.endElement("","","instructions");
			hd_documemt.startElement("","","H1",atts);		
			hd_documemt.characters(("Files in Package:" ).toCharArray(),0,("Files in Package:" ).length());
			hd_documemt.endElement("","","H1");		
			hd_documemt.startElement("","","file",atts);
			hd_documemt.characters(("File: PackOut.xml").toCharArray(),0,("File: PackOut.xml").length());
			hd_documemt.endElement("","","file");		
			hd_documemt.startElement("","","filedirectory",atts);
			hd_documemt.characters("Directory: \\dict\\".toCharArray(),0,("Directory: \\dict\\").length());
			hd_documemt.endElement("","","filedirectory");		
			hd_documemt.startElement("","","filenotes",atts);
			hd_documemt.characters("Notes: Contains all application/object settings for package".toCharArray(),0,"Notes: Contains all application/object settings for package".length());
			hd_documemt.endElement("","","filenotes");			
			success = (new File(rs1.getString("File_Directory") + rs1.getString("PK_Name")+fileSeperator+ "dict"+fileSeperator)).mkdirs();		
			String file_menu = rs1.getString("File_Directory") + rs1.getString("PK_Name") +fileSeperator+ "dict"+fileSeperator+"PackOut.xml";		
			OutputStream  fw_menu = new FileOutputStream (file_menu, false);
			StreamResult streamResult_menu = new StreamResult(fw_menu);		
			SAXTransformerFactory tf_menu = (SAXTransformerFactory) SAXTransformerFactory.newInstance();					 
			TransformerHandler hd_menu = tf_menu.newTransformerHandler();		
			Transformer serializer_menu = hd_menu.getTransformer();		
			serializer_menu.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
			serializer_menu.setOutputProperty(OutputKeys.INDENT,"yes");		
			hd_menu.setResult(streamResult_menu);
			hd_menu.startDocument();
			atts.clear();
			atts.addAttribute("","","PK_Name","CDATA",rs1.getString("PK_Name"));
			atts.addAttribute("","","Version","CDATA",rs1.getString("PK_Version"));
			atts.addAttribute("","","CompVer","CDATA",rs1.getString("ReleaseNo"));
			atts.addAttribute("","","DataBase","CDATA",rs1.getString("Version"));
			atts.addAttribute("","","Description","CDATA",rs1.getString("Description"));
			atts.addAttribute("","","creator","CDATA",rs1.getString("UserName"));
			atts.addAttribute("","","creatorcontact","CDATA",rs1.getString("Email"));
			atts.addAttribute("","","createddate","CDATA",rs1.getString("Created"));
			atts.addAttribute("","","updateddate","CDATA",rs1.getString("Updated"));
			atts.addAttribute("","","PackOutVer","CDATA",PackOutVer);		
			
			hd_menu.startElement("","","adempiereAD",atts);		
			atts.clear();			
			String sql = "SELECT * FROM AD_Package_Exp_Detail WHERE AD_Package_Exp_ID = "+p_PackOut_ID+" ORDER BY Line ASC";
			
			PreparedStatement pstmt = null;		
			pstmt = DB.prepareStatement (sql, get_TrxName());
			
			try {			
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()){
					String Type = rs.getString("Type");
					log.info(rs.getString("Line"));
					if (Type.compareTo("M") == 0){
						m_Menu = new X_AD_Menu (getCtx(), rs.getInt("AD_Menu_ID"), null);
						if (m_Menu.isSummary() == false) {
							CreateApplication (atts, hd_menu, rs.getInt("AD_Menu_ID"));							
						}
						else {
							atts = createmenuBinding(atts,m_Menu);
							hd_menu.startElement("","","menu",atts);
							CreateModule (atts, hd_menu, rs.getInt("AD_Menu_ID"));
							hd_menu.endElement("","","menu");
						}
					}
					else if (Type.compareTo("P") == 0)
						CreateProcess ( rs.getInt("AD_Process_ID"), atts, hd_menu );
					else if (Type.compareTo("R") == 0)
						CreateReportview ( rs.getInt("AD_ReportView_ID"), atts, hd_menu );
					else if (Type.compareTo("D") == 0)
						CreateData ( rs.getInt("AD_Table_ID"), rs.getString("SQLStatement"), atts, hd_menu );
					else if (Type.compareTo("T") == 0)
						CreateTable (rs.getInt("AD_Table_ID"), atts, hd_menu);
					else if (Type.compareTo("X") == 0)
						CreateForm (rs.getInt("AD_Form_ID"), atts, hd_menu);
					else if (Type.compareTo("W") == 0)
						CreateWindow (rs.getInt("AD_Window_ID"), atts, hd_menu);				
					else if (Type.compareTo("B") == 0)
						CreateWorkbench (rs.getInt("AD_Workbench_ID"), atts, hd_menu);
					else if (Type.compareTo("S") == 0)
						CreateRoles (rs.getInt("AD_Role_ID"), atts, hd_menu);
					else if (Type.compareTo("SQL") == 0)
						CreateSQL (rs.getString("SQLStatement"), rs.getString("DBType"), atts, hd_menu);
					else if (Type.compareTo("IMP") == 0)
						CreateImp (rs.getInt("AD_ImpFormat_ID"), atts, hd_menu);
					else if (Type.compareTo("SNI") == 0)						
						CreateSnipit (rs.getString("Destination_Directory"),rs.getString("Destination_FileName"),rs.getString("AD_Package_Code_Old"),
								rs.getString("AD_Package_Code_New"),  rs.getString("ReleaseNo"), atts, hd_menu);
					else if (Type.compareTo("F") == 0)
						//TODO Create Workflow
						CreateWorkflow (rs.getInt("AD_Workflow_ID"), atts, hd_menu);
					else if (Type.compareTo("C") == 0){
						
						String fullDirectory = rs1.getString("File_Directory") + rs1.getString("PK_Name")+rs.getString("Target_Directory");
						String targetDirectoryModified=null;
						char fileseperator1 = '/';
						char fileseperator2 = '\\';
						//Correct package for proper file seperator
						if (fileSeperator.equals("/")){			
							targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
						}
						else
							targetDirectoryModified = fullDirectory.replace(fileseperator1,fileseperator2);
						
						String target_File = (targetDirectoryModified);						
						success = (new File(target_File).mkdirs());						
						fullDirectory = rs.getString("File_Directory");
						targetDirectoryModified=null;						
						//Correct package for proper file seperator
						if (fileSeperator.equals("/")){			
							targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
						}
						else
							targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
						
						CopyCode (targetDirectoryModified+rs.getString("FileName"),target_File+rs.getString("FileName"));
						
						atts.clear();
						
						if(rs.getString("Destination_Directory") != null){
							
							fullDirectory = rs.getString("Destination_Directory");
							String destinationDirectoryModified=null;						
							
							//Correct package for proper file seperator
							if (fileSeperator.equals("/")){			
								destinationDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
							}
							else
								destinationDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);							
							
							DistributeFile( rs.getString("FileName"), rs.getString("Target_Directory"), rs.getString("ReleaseNo"),destinationDirectoryModified, atts, hd_menu);
							
						}
						
						if(rs.getString("FileName") != null){
							hd_documemt.startElement("","","file",atts);
							hd_documemt.characters(("File: "+rs.getString("FileName")).toCharArray(),0,("File: "+rs.getString("FileName")).length());
							hd_documemt.endElement("","","file");
						}					
						hd_documemt.startElement("","","filedirectory",atts);
						hd_documemt.characters(("Directory: "+rs.getString("TARGET_DIRECTORY")).toCharArray(),0,("Directory: "+rs.getString("TARGET_DIRECTORY")).length());
						hd_documemt.endElement("","","filedirectory");
						
						hd_documemt.startElement("","","filenotes",atts);
						hd_documemt.characters(("Notes: "+rs.getString("Description")).toCharArray(),0,(("Notes: " + rs.getString("Description")).length()));
						hd_documemt.endElement("","","filenotes");
					}
				}
				rs.close();
				pstmt.close();
				pstmt = null;
				}
				catch (Exception e)
					{
					log.log(Level.SEVERE,"getWindows"+ e);
					}
				finally
				{
					try
					{
						if (pstmt != null)
							pstmt.close ();
					}
					catch (Exception e)
					{}
					pstmt = null;
				}
			atts.clear();	
			hd_menu.startElement("","","menuset",atts);
			hd_menu.endElement("","","menuset");
			hd_menu.endElement("","","adempiereAD");
			hd_menu.endDocument();hd_documemt.endElement("","","compiereDocument");
			hd_documemt.endDocument();
			//m_Exp.setProcessed(true);
			//m_Exp.save();
			}
			rs1.close();
			pstmt1.close();
			pstmt1 = null;
			}
			catch (Exception e)
				{
				log.log(Level.SEVERE,"getWindows", e);
				}
			finally
			{
				try
				{
					if (pstmt1 != null)
						pstmt1.close ();
				}
				catch (Exception e)
				{}
				pstmt1 = null;
		}
			
		//create compressed packages
		//set the files
		File srcFolder = new File("");
		File destZipFile = new File("");
		File destTarFile = new File("");
		File destGZipFile = new File("");		
		srcFolder = (new File(packagedir));
		destZipFile = (new File(packagename+".zip"));
		destTarFile = (new File(packagename+".tar"));
		destGZipFile = (new File(packagename+".tar.gz"));
		
		//delete the old packages if necessary
		boolean success = destZipFile.delete();
		success = destTarFile.delete();
		success = destGZipFile.delete();
	    
		//create the compressed packages
		CreateZipFile.zipFolder(srcFolder, destZipFile, includesdir);		
		CreateZipFile.tarFolder(srcFolder, destTarFile, includesdir);
		CreateZipFile.gzipFile(destTarFile, destGZipFile);
		
		//Clean .tar file up
		success = destTarFile.delete();
		
		return "";
	}	//	doIt
	
	public void CreateApplication (AttributesImpl atts, TransformerHandler hd_menu, int menu_id) throws SAXException
	{
		String sql = null;
		int x = 0;
		sql = "SELECT A.Node_ID, B.AD_Menu_ID, B.Name, B.AD_WINDOW_ID, B.AD_WORKFLOW_ID, B.AD_TASK_ID, "
			+ "B.AD_PROCESS_ID, B.AD_FORM_ID, B.AD_WORKBENCH_ID "
			+ "FROM AD_TreeNoDemm A, AD_Menu B "
			+ "WHERE A.Node_ID = " + menu_id
			+ " AND A.Node_ID = B.AD_Menu_ID";			
		
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		
		try {
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{
				
				m_Menu = new X_AD_Menu (getCtx(), rs.getInt("AD_Menu_ID"), null);										
				atts = createmenuBinding(atts,m_Menu);
				hd_menu.startElement("","","menu",atts);
				if ( rs.getInt("AD_WINDOW_ID") > 0 | rs.getInt("AD_WORKFLOW_ID") > 0 | rs.getInt("AD_TASK_ID") > 0 | rs.getInt("AD_PROCESS_ID") > 0 | rs.getInt("AD_FORM_ID") > 0 | rs.getInt("AD_WORKBENCH_ID") > 0)				
				{	
			    //Call CreateWindow.		
					if (rs.getInt("AD_WINDOW_ID") > 0)
					{
						CreateWindow (rs.getInt("AD_WINDOW_ID"), atts, hd_menu );
					}					
				//Call CreateProcess.
					else if (rs.getInt("AD_PROCESS_ID") > 0)
					{					
						CreateProcess (rs.getInt("AD_PROCESS_ID"),  atts,  hd_menu);											
					}
				//Call CreateTask.
				else if (rs.getInt("AD_TASK_ID") > 0)
					{
						CreateTask (rs.getInt("AD_TASK_ID"),  atts,  hd_menu);					
					}
				//Call CreateForm.
				else if (rs.getInt("AD_FORM_ID") > 0)
					{
						CreateForm (rs.getInt("AD_FORM_ID"),  atts,  hd_menu);		
					}
				//Call CreateWorkbench
				else if (rs.getInt("AD_WORKBENCH_ID") > 0)
					{					
						CreateWorkbench (rs.getInt("AD_WORKBENCH_ID"), atts, hd_menu);				
					}
				//Call CreateModule because entry is a summary menu
				}
				else
					{	
						CreateModule (atts, hd_menu, rs.getInt("Node_ID"));					
					}
				hd_menu.endElement("","","menu");
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getWindows", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}	
	}
	
	public void CreateModule (AttributesImpl atts, TransformerHandler hd_menu, int menu_id) throws SAXException
	{
		String sql = null;
		sql = "SELECT A.Node_ID, B.AD_Menu_ID, B.Name, B.AD_WINDOW_ID, B.AD_WORKFLOW_ID, B.AD_TASK_ID, "
			+ "B.AD_PROCESS_ID, B.AD_FORM_ID, B.AD_WORKBENCH_ID "
			+ "FROM AD_TreeNoDemm A, AD_Menu B "
			+ "WHERE A.Parent_ID = " + menu_id
			+ " AND A.Node_ID = B.AD_Menu_ID";			
		
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		try {
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{
				//Menu tag Start.
				m_Menu = new X_AD_Menu (getCtx(), rs.getInt("AD_Menu_ID"), null);										
				atts = createmenuBinding(atts,m_Menu);
				hd_menu.startElement("","","menu",atts);
				if ( rs.getInt("AD_WINDOW_ID") > 0 | rs.getInt("AD_WORKFLOW_ID") > 0 | rs.getInt("AD_TASK_ID") > 0 | rs.getInt("AD_PROCESS_ID") > 0 | rs.getInt("AD_FORM_ID") > 0 | rs.getInt("AD_WORKBENCH_ID") > 0)				
				{	
			    //Call CreateWindow.		
					if (rs.getInt("AD_WINDOW_ID") > 0)
					{
						CreateWindow (rs.getInt("AD_WINDOW_ID"), atts, hd_menu );
					}					
				//Call CreateProcess.
					else if (rs.getInt("AD_PROCESS_ID") > 0)
					{					
						CreateProcess (rs.getInt("AD_PROCESS_ID"),  atts,  hd_menu);											
					}
				//Call CreateTask.
				else if (rs.getInt("AD_TASK_ID") > 0)
					{
						CreateTask (rs.getInt("AD_TASK_ID"),  atts,  hd_menu);					
					}
				//Call CreateForm.
				else if (rs.getInt("AD_FORM_ID") > 0)
					{
						CreateForm (rs.getInt("AD_FORM_ID"),  atts,  hd_menu);		
					}
				//Call CreateWorkbench
				else if (rs.getInt("AD_WORKBENCH_ID") > 0)
					{					
						CreateWorkbench (rs.getInt("AD_WORKBENCH_ID"), atts, hd_menu);				
					}
				//Call CreateModule because entry is a summary menu
				}
				else
					{	
						CreateModule (atts, hd_menu, rs.getInt("Node_ID"));					
					}					
				hd_menu.endElement("","","menu");				
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getWindows", e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}	
	}
	public void CopyCode (String sourceName, String copyName)
	{
		CopyFile (sourceName, copyName );
	}
	
	public void CreateWorkflow (int AD_Workflow_ID, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		//TODO Create workflow
	}
	
	public void CreateWorkbench (int AD_Workbench_ID, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		m_Workbench = new X_AD_Workbench (getCtx(), AD_Workbench_ID, null);					
		atts = createworkbenchBinding(atts,m_Workbench);
		hd_menu.startElement("","","workbench",atts);
		//Workbenchwindow tags
		String sqlP = "SELECT * FROM AD_WorkbenchWindow WHERE AD_WORKBENCH_ID = " + AD_Workbench_ID;							
		PreparedStatement pstmtP = null;
		pstmtP = DB.prepareStatement (sqlP, get_TrxName());		
		try {
			ResultSet rsP = pstmtP.executeQuery();		
			while (rsP.next())
			{
				m_Workbenchwindow = new X_AD_WorkbenchWindow (getCtx(), rsP.getInt("AD_Workbench_Window_ID"), null);
				atts = createworkbenchwindowBinding(atts,m_Workbenchwindow);
				hd_menu.startElement("","","workbenchwindow",atts);
				hd_menu.endElement("","","workbenchwindow");
			}								
			rsP.close();
			pstmtP.close();
			pstmtP = null;
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE,"getWorkbench_Window", e);
			}
			finally
			{
				try {
					if (pstmtP != null)
						pstmtP.close ();
				}
				catch (Exception e)
				{}
				pstmtP = null;
			}
			hd_menu.endElement("","","workbench");
		}
	public void DistributeFile (String FileName, String Source_Directory, String ReleaseNo,String Target_Directory, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{	
		atts.addAttribute("","","name","CDATA",FileName);
		atts.addAttribute("","","sourceDirectory","CDATA",Source_Directory);
		atts.addAttribute("","","targetDirectory","CDATA",Target_Directory);		
		atts.addAttribute("","","ReleaseNo","CDATA",ReleaseNo);		
		hd_menu.startElement("","","distfile",atts);
		hd_menu.endElement("","","distfile");
	}
	public void CreateForm (int AD_Form_ID, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		m_Form = new X_AD_Form (getCtx(), AD_Form_ID, null);					
		atts = createformBinding(atts,m_Form);
		hd_menu.startElement("","","form",atts);
		hd_menu.endElement("","","form");
	}
	public void CreateTask (int AD_Task_ID, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		m_Task = new X_AD_Task (getCtx(), AD_Task_ID, null);
		atts = createtaskBinding(atts,m_Task);
		hd_menu.startElement("","","task",atts);
		hd_menu.endElement("","","task");
	}
	public void CreateProcess (int AD_Process_ID, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		String sqlW = "SELECT * FROM AD_PROCESS WHERE AD_PROCESS_ID = " + AD_Process_ID;		
		
		PreparedStatement pstmt1 = null;
		pstmt1 = DB.prepareStatement (sqlW, get_TrxName());		
		try {
			ResultSet rs1 = pstmt1.executeQuery();		
			while (rs1.next())
			{					
				X_AD_Process m_Process = new X_AD_Process (getCtx(), rs1.getInt("AD_Process_ID"), null);
				
				if (rs1.getInt("AD_ReportView_ID")>0){
					
					CreateReportview(rs1.getInt("AD_ReportView_ID"),atts, hd_menu);
				}
				atts = createprocessBinding(atts,m_Process);
				hd_menu.startElement("","","process",atts);
		//				processpara tags	
				String sqlP = "SELECT * FROM AD_PROCESS_PARA WHERE AD_PROCESS_ID = " + AD_Process_ID;							
				PreparedStatement pstmtP = null;
				pstmtP = DB.prepareStatement (sqlP, get_TrxName());		
				try {
					ResultSet rsP = pstmtP.executeQuery();		
					while (rsP.next())
					{
						if (rsP.getInt("AD_Reference_ID")>0)
							CreateReference (rsP.getInt("AD_Reference_ID"), atts, hd_menu);
						if (rsP.getInt("AD_Reference_Value_ID")>0)
							CreateReference (rsP.getInt("AD_Reference_Value_ID"),atts, hd_menu);
						m_Processpara = new X_AD_Process_Para (getCtx(), rsP.getInt("AD_Process_Para_ID"), null);										
						atts = createprocessparaBinding(atts,m_Processpara);
						hd_menu.startElement("","","processpara",atts);
						hd_menu.endElement("","","processpara");
					}								
					rsP.close();
					pstmtP.close();
					pstmtP = null;
					}
					catch (Exception e)
					{
						log.log(Level.SEVERE,"getProcess_Para", e);
					}
					finally
					{
						try {
							if (pstmtP != null)
								pstmtP.close ();
						}
						catch (Exception e)
						{}
						pstmtP = null;
					}
					hd_menu.endElement("","","process");
			}
			rs1.close();
			pstmt1.close();
			pstmt1 = null;
			}
			catch (Exception e)	{
				log.log(Level.SEVERE,"getProcess", e);
			}
			finally{
				try	{
					if (pstmt1 != null)
						pstmt1.close ();
				}
				catch (Exception e)
				{}
				pstmt1 = null;
			}
	}
	public void CreateWindow (int AD_Window_ID, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
//Window Tag
		m_Window = new X_AD_Window (getCtx(), AD_Window_ID, null);
		atts = createwindowBinding(atts,m_Window);
		hd_menu.startElement("","","window",atts);
	//Tab Tag
		String sql2 = "SELECT * FROM AD_TAB WHERE AD_WINDOW_ID = " + AD_Window_ID;
		PreparedStatement pstmt1 = null;
		pstmt1 = DB.prepareStatement (sql2, get_TrxName());		
		try {
			ResultSet rs1 = pstmt1.executeQuery();		
			while (rs1.next())
			{
				String sql3 = "SELECT Name FROM AD_Table WHERE AD_Table_ID=?";							
				int table_id = rs1.getInt("AD_TABLE_ID");
				String name = rs1.getString("NAME");
				String tablename = DB.getSQLValueString(null, sql3, table_id);
				CreateTable (rs1.getInt("AD_Table_ID"), atts, hd_menu);
				m_Tab = new X_AD_Tab (getCtx(), rs1.getInt("AD_Tab_ID"), null);										
				atts = createtabBinding(atts,m_Tab);
				hd_menu.startElement("","","tab",atts);
	//Fields tags.
				String sql4 = "SELECT * FROM AD_FIELD WHERE AD_TAB_ID = " + rs1.getInt("AD_TAB_ID")
							+ "ORDER BY SEQNO asc";
				PreparedStatement pstmt2 = null;
				pstmt2 = DB.prepareStatement (sql4, get_TrxName());		
				try {									
					ResultSet rs2 = pstmt2.executeQuery();								
					while (rs2.next())
					{
						m_Field = new X_AD_Field (getCtx(), rs2.getInt("AD_Field_ID"), null);										
						atts = createfieldBinding(atts,m_Field);
						hd_menu.startElement("","","field",atts);
						hd_menu.endElement("","","field");
					}
					rs2.close();
					pstmt2.close();
					pstmt2 = null;
					}
					catch (Exception e)
					{
						log.log(Level.SEVERE,"getFields", e);
					}
					finally
					{
						try
						{
							if (pstmt2 != null)
								pstmt2.close ();
						}
						catch (Exception e)
						{}
						pstmt2 = null;
					}					
					hd_menu.endElement("","","tab");
	}
		rs1.close();
		pstmt1.close();
		pstmt1 = null;
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getTabs", e);
		}
		finally
		{
			try
			{
				if (pstmt1 != null)
					pstmt1.close ();
			}
			catch (Exception e)
			{}
			pstmt1 = null;
		}			
			
// 		Loop tags.						
		hd_menu.endElement("","","window");

		//Preference Tag
		sql2 = "SELECT * FROM AD_PREFERENCE WHERE AD_WINDOW_ID = " + AD_Window_ID;
		pstmt1 = null;
		pstmt1 = DB.prepareStatement (sql2, get_TrxName());		
		try {
			ResultSet rs1 = pstmt1.executeQuery();		
			while (rs1.next())
			{				
				m_Preference = new X_AD_Preference (getCtx(), rs1.getInt("AD_Preference_ID"), null);										
				atts = createpreferenceBinding(atts,m_Preference);
				hd_menu.startElement("","","preference",atts);
				hd_menu.endElement("","","preference");
			}
		rs1.close();
		pstmt1.close();		
		pstmt1 = null;
		}		
		catch (Exception e)
		{
			log.log(Level.SEVERE,"getTabs", e);
		}
		finally
		{
			try
			{
				if (pstmt1 != null)
					pstmt1.close ();
			}
			catch (Exception e)
			{}
			pstmt1 = null;
		}
		
		}
	public void CreateData (int table_id, String sql, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		Statement stmt = DB.createStatement();
		atts.clear();
		hd_menu.startElement("","","data",atts);		
		try {
			ResultSet rs = stmt.executeQuery(sql);		
			ResultSetMetaData meta = rs.getMetaData(); 
			int columns = meta.getColumnCount(); 
			int i = 1;
			String col_Name = null;
			String sql1 = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
	        String table_Name = DB.getSQLValueString(null,sql1,table_id);
	        atts.clear();
	        atts.addAttribute("","","name","CDATA",table_Name);
	        hd_menu.startElement("","","dtable",atts);
	    	while (rs.next()){
				atts.clear();				
				int key1 = 0;
				String nameatts = ""; 
				for (i=1 ;i <= columns;i++){
					col_Name = meta.getColumnName(i);					
					if (col_Name.equals("NAME") && rs.getObject("name") != null)
						nameatts = ""+rs.getObject("name");					
					String sql2 = "SELECT ColumnName FROM AD_Column "
								+ "WHERE isKey = 'Y' AND "
								+ "AD_Table_ID = ? AND "
								+ "Upper(ColumnName)= '"+col_Name+"'";
					String cName = DB.getSQLValueString(null,sql2,table_id);
					if (cName != null){
				    	if (cName.toUpperCase().equals(col_Name) & key1 == 0  ){
							atts.addAttribute("","","key1name","CDATA",cName);
							atts.addAttribute("","","lookupkey1name","CDATA",""+rs.getObject(col_Name));
							key1 = 1;							
						}
				    	else if (cName.toUpperCase().equals(col_Name) & key1 == 1 ){
							atts.addAttribute("","","key2name","CDATA",cName);
							atts.addAttribute("","","lookupkey2name","CDATA",""+rs.getObject(col_Name));
							key1 = 2;
						}
					}
				}
				atts.addAttribute("","","name","CDATA",nameatts);
				if ( key1 == 0 ){
					atts.addAttribute("","","key1name","CDATA","");
					atts.addAttribute("","","lookupkey1name","CDATA","");
					key1 = 1;
				}	
				if ( key1 == 1 ){
					atts.addAttribute("","","key2name","CDATA","");
					atts.addAttribute("","","lookupkey2name","CDATA","");
				}	
				hd_menu.startElement("","","drow",atts);				
				for (i=1 ;i <= columns;i++){
					atts.clear();
					col_Name = meta.getColumnName(i);
					String sql2 = "Select A.ColumnName, B.Name "
								+ "From AD_Column A, AD_Reference B " 
								+ "Where Upper(A.columnname) = ? and " 
								+ "A.AD_TABLE_ID = ? and " 
								+ "A.AD_Reference_ID = B.AD_Reference_ID";
					PreparedStatement pstmt = null;
					try
					{
						pstmt = DB.prepareStatement(sql2, get_TrxName());
						pstmt.setString(1, col_Name);
						pstmt.setInt(2, table_id);
						ResultSet rs1 = pstmt.executeQuery();						
						while (rs1.next()){
							//added 9/3/05
							atts.clear();
							atts.addAttribute("","","name","CDATA", rs1.getString("ColumnName"));							
							atts.addAttribute("","","class","CDATA", rs1.getString("Name"));
							if (rs1.getString("Name").equals("Date")||rs1.getString("Name").equals("Date+Time")||rs1.getString("Name").equals("Time"))
								atts.addAttribute("","","value","CDATA", "" + rs.getTimestamp(i));
							else
								atts.addAttribute("","","value","CDATA", "" + rs.getObject(i));
							
							if (!rs1.getString("ColumnName").equals("Created")&&!rs1.getString("ColumnName").equals("CreatedBy")&&
									!rs1.getString("ColumnName").equals("Updated")&&!rs1.getString("ColumnName").equals("UpdatedBy")){
							hd_menu.startElement("","","dcolumn",atts);
							hd_menu.endElement("","","dcolumn");
							}
						}					
					rs1.close();
					pstmt.close();
					pstmt = null;
					}	
					catch (Exception e)	{
						log.log(Level.SEVERE,"getData", e);
					}
				}
				hd_menu.endElement("","","drow");	
			}
			rs.close();
			stmt.close();
			stmt = null;
			hd_menu.endElement("","","dtable");
		}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"getData", e);
			}
			
			hd_menu.endElement("","","data");
}
	public void CreateReportview (int Reportview_id, AttributesImpl atts, TransformerHandler hd_menu)
	{
		String sql = "SELECT * FROM AD_ReportView WHERE AD_ReportView_ID= " + Reportview_id;
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		atts.clear();		
		try {
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{
				m_Reportview = new X_AD_ReportView (getCtx(), rs.getInt("AD_Reportview_ID"), null);								
				atts = createreportviewBinding(atts,m_Reportview);
				hd_menu.startElement("","","reportview",atts);
				hd_menu.endElement("","","reportview");
				
				String sql1 = "SELECT * FROM AD_Printformat WHERE AD_Reportview_ID= " + Reportview_id;
				PreparedStatement pstmt1 = null;
				pstmt1 = DB.prepareStatement (sql1, get_TrxName());		
				try {
					ResultSet rs1 = pstmt1.executeQuery();		
					while (rs1.next())
					{					
						//Export Table if neccessary
						CreateTable (rs1.getInt("AD_Table_ID"), atts, hd_menu);
						
						m_Printformat = new X_AD_PrintFormat (getCtx(), rs1.getInt("AD_Printformat_ID"), null);
						atts = createPrintformatBinding(atts,m_Printformat);
						hd_menu.startElement("","","printformat",atts);
						
							String sql2 = "SELECT * FROM AD_PrintFormatItem WHERE AD_PrintFormat_ID= " + rs1.getInt("AD_Printformat_ID");
							PreparedStatement pstmt2 = null;
							pstmt2 = DB.prepareStatement (sql2, get_TrxName());		
							try {
								ResultSet rs2 = pstmt2.executeQuery();		
								while (rs2.next())
								{
									m_PrintFormatItem = new X_AD_PrintFormatItem (getCtx(), rs2.getInt("AD_PrintFormatItem_ID"), null);									
									atts = createPrintformatItemBinding(atts,m_PrintFormatItem);
									hd_menu.startElement("","","printformatitem",atts);
									hd_menu.endElement("","","printformatitem");
								}
								rs2.close();
								pstmt2.close();
								pstmt2 = null;			
							}	
							
								catch (Exception e)
								{
									log.log(Level.SEVERE,"printformatitem", e);
								}
							finally
							{
								try
								{
									if (pstmt2 != null)
										pstmt2.close ();
								}
								catch (Exception e)
								{}
								pstmt2 = null;
							}
						
												
					hd_menu.endElement("","","printformat");
					}
					rs1.close();
					pstmt1.close();
					pstmt1 = null;			
				}	
				
					catch (Exception e)
					{
						log.log(Level.SEVERE,"printformat", e);
					}
				finally
				{
					try
					{
						if (pstmt1 != null)
							pstmt1.close ();
					}
					catch (Exception e)
					{}
					pstmt1 = null;
				}
				atts.clear();		
					sql1 = "SELECT * FROM AD_ReportView_Col WHERE AD_Reportview_ID= " + Reportview_id;
					pstmt1 = null;
					pstmt1 = DB.prepareStatement (sql1, get_TrxName());		
					try {
						ResultSet rs1 = pstmt1.executeQuery();		
						while (rs1.next())
						{
							m_Reportview_Col = new X_AD_ReportView_Col (getCtx(), rs1.getInt("AD_ReportView_Col_ID"), null);
							atts = createreportviewcolBinding(atts,m_Reportview_Col);
							hd_menu.startElement("","","reportviewcol",atts);
							hd_menu.endElement("","","reportviewcol");
						}
						rs1.close();
						pstmt1.close();
						pstmt1 = null;			
					}	
					
						catch (Exception e)
						{
							log.log(Level.SEVERE,"reportviewcol", e);
						}
					finally
					{
						try
						{
							if (pstmt1 != null)
								pstmt1.close ();
						}
						catch (Exception e)
						{}
						pstmt1 = null;
					}
					//atts = createreportviewBinding(atts,m_Reportview);
					//hd_menu.startElement("","","reportview",atts);
					//hd_menu.endElement("","","reportview");
				}
				
			rs.close();
			pstmt.close();
			pstmt = null;			
		}	
		
			catch (Exception e)
			{
				log.log(Level.SEVERE,"reportview", e);
			}
		
		
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
}
	public void CreateSQL (String SQLStatement, String DBType,AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		atts = createSQLStatmentBinding(atts, SQLStatement, DBType);
		hd_menu.startElement("","","SQLStatement",atts);
		hd_menu.endElement("","","SQLStatement");
	}
	public void CreateSnipit (String FileDir, String FileName, String OldCode, String NewCode, String ReleaseNo, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		atts = createSnipitBinding(atts, FileDir, FileName, OldCode, NewCode, ReleaseNo);
		hd_menu.startElement("","","codesnipit",atts);
		hd_menu.endElement("","","codesnipit");
	}
	public void CreateRoles (int Role_id, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		
		
		//Process role
		m_Role = new X_AD_Role (getCtx(), Role_id, null);
		atts = createroleBinding(atts,m_Role);
		hd_menu.startElement("","","role",atts);
		
		//Process org access
		String sql = "SELECT * FROM AD_Role_OrgAccess WHERE AD_Role_ID= " + Role_id;
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		try {			
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{
				atts = createorgaccessBinding(atts,rs.getInt("AD_Org_ID"),rs.getInt("AD_Role_ID"));
				hd_menu.startElement("","","OrgAccess",atts);
				hd_menu.endElement("","","OrgAccess");
			}
			rs.close();
			pstmt.close();
			pstmt = null;			
			}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"CreateRoles-Window Access", e);
			}
		//Process user assignment access
			sql = "SELECT * FROM AD_User_Roles WHERE AD_Role_ID= " + Role_id;
			pstmt = null;
			pstmt = DB.prepareStatement (sql, get_TrxName());
			try {			
				ResultSet rs = pstmt.executeQuery();		
				while (rs.next())
				{	
					atts = createusrassignBinding(atts,rs.getInt("AD_User_ID"),rs.getInt("AD_Role_ID"),rs.getInt("AD_Org_ID"));
					hd_menu.startElement("","","userrole",atts);
					hd_menu.endElement("","","userrole");
				}
			rs.close();
			pstmt.close();
			pstmt = null;			
			}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"CreateRoles-Window Access", e);
			}
		//Process AD_Window_Access Values
		sql = "SELECT * FROM AD_Window_Access WHERE AD_Role_ID= " + Role_id;
		pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		try {			
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{
				atts = createwindowaccessBinding(atts,rs.getInt("AD_Window_ID"),rs.getInt("AD_Role_ID"));
				hd_menu.startElement("","","windowaccess",atts);
				hd_menu.endElement("","","windowaccess");
			}
			rs.close();
			pstmt.close();
			pstmt = null;			
		}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"CreateRoles-Window Access", e);
			}
		
//			Process AD_Process_Access Values
		sql = "SELECT * FROM AD_Process_Access WHERE AD_Role_ID= " + Role_id;
		pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());
		try {			
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{
				//m_Process_Access = new X_AD_Process_Access (getCtx(), rs.getInt("AD_Process_ID"), null);
				atts = createprocessaccessBinding(atts,rs.getInt("AD_Process_ID"),rs.getInt("AD_Role_ID"));
				hd_menu.startElement("","","processaccess",atts);
				hd_menu.endElement("","","processaccess");
				
			}
			rs.close();
			pstmt.close();
			pstmt = null;			
		}	
		
			catch (Exception e)	{
				log.log(Level.SEVERE,"CreateRoles-AD_Process", e);
			}
			
//				Process AD_Form_Access Values
			sql = "SELECT * FROM AD_Form_Access WHERE AD_Role_ID= " + Role_id;
			pstmt = null;
			pstmt = DB.prepareStatement (sql, get_TrxName());
			try {			
				ResultSet rs = pstmt.executeQuery();		
				while (rs.next())
				{
					//m_Form_Access = new X_AD_Form_Access (getCtx(), rs.getInt("AD_Form_ID"), null);
					atts = createformaccessBinding(atts,rs.getInt("AD_Form_ID"),rs.getInt("AD_Role_ID"));
					hd_menu.startElement("","","formaccess",atts);
					hd_menu.endElement("","","formaccess");
					
				}
				rs.close();
				pstmt.close();
				pstmt = null;			
			}	
			
				catch (Exception e)	{
					log.log(Level.SEVERE,"CreateRoles-Form Access", e);
				}
			
//					Process AD_Workflow_Access Values
				sql = "SELECT * FROM AD_Workflow_Access WHERE AD_Role_ID= " + Role_id;
				pstmt = null;
				pstmt = DB.prepareStatement (sql, get_TrxName());
				try {			
					ResultSet rs = pstmt.executeQuery();		
					while (rs.next())
					{
						//m_Workflow_Access = new X_AD_Workflow_Access (getCtx(), rs.getInt("AD_Workflow_ID"), null);
						atts = createworkflowaccessBinding(atts,rs.getInt("AD_Workflow_ID"),rs.getInt("AD_Role_ID"));
						hd_menu.startElement("","","workflowaccess",atts);
						hd_menu.endElement("","","workflowaccess");
						
					}
					rs.close();
					pstmt.close();
					pstmt = null;			
				}	
				
					catch (Exception e)	{
						log.log(Level.SEVERE,"CreateRoles-Workflow", e);
					}
			
//						Process AD_Task_Access Values
					sql = "SELECT * FROM AD_Task_Access WHERE AD_Role_ID= " + Role_id;
					pstmt = null;
					pstmt = DB.prepareStatement (sql, get_TrxName());
					try {			
						ResultSet rs = pstmt.executeQuery();		
						while (rs.next())
						{
							//m_Task_Access = new X_AD_Task_Access (getCtx(), rs.getInt("AD_Task_ID"), null);
							atts = createtaskaccessBinding(atts,rs.getInt("AD_Task_ID"),rs.getInt("AD_Role_ID"));
							hd_menu.startElement("","","taskaccess",atts);
							hd_menu.endElement("","","taskaccess");
							
						}
						rs.close();
						pstmt.close();
						pstmt = null;			
					}	
					
						catch (Exception e)	{
							log.log(Level.SEVERE,"CreateRoles-Task", e);
						}
				finally
				{
					try
					{
						if (pstmt != null)
							pstmt.close ();
					}
					catch (Exception e)
					{}
					pstmt = null;
				}
				hd_menu.endElement("","","role");
	}
	public void CreateReference (int Reference_id, AttributesImpl atts, TransformerHandler hd_menu)
	{
	
		
		String sql = "SELECT * FROM AD_Reference WHERE AD_Reference_ID= " + Reference_id;

		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());		
		
		try {
			
			ResultSet rs = pstmt.executeQuery();		
		
			while (rs.next())
			{
				
				m_Reference = new X_AD_Reference (getCtx(), rs.getInt("AD_Reference_ID"), null);				
				atts = createreferenceBinding(atts,m_Reference);
				hd_menu.startElement("","","reference",atts);
				
				
				if (m_Reference.getValidationType().compareTo("L")== 0)
				{
					String sql1 = "SELECT * FROM AD_Ref_List WHERE AD_Reference_ID= " + Reference_id;
					
					
					PreparedStatement pstmt1 = null;
					pstmt1 = DB.prepareStatement (sql1, get_TrxName());		
					
					try {
						
						ResultSet rs1 = pstmt1.executeQuery();		
						
						while (rs1.next())
						{
							
							m_Ref_List = new X_AD_Ref_List (getCtx(), rs1.getInt("AD_REF_LIST_ID"), null);
							atts = createreflistBinding(atts,m_Ref_List);							
							hd_menu.startElement("","","referencelist",atts);
							hd_menu.endElement("","","referencelist");
						}
						rs1.close();
						pstmt1.close();
						pstmt1 = null;			
					}	
					
						catch (Exception e)
						{
							log.log(Level.SEVERE,"getRef_List", e);
						}
					
					
					finally
					{
						try
						{
							if (pstmt1 != null)
								pstmt1.close ();
						}
						catch (Exception e)
						{}
						pstmt1 = null;
					}
					
				}
				else if (m_Reference.getValidationType().compareTo("T")== 0)
				{
					String sql1 = "SELECT * FROM AD_Reference WHERE AD_Reference_ID= " + Reference_id;					
					PreparedStatement pstmt1 = null;
					pstmt1 = DB.prepareStatement (sql1, get_TrxName());		
					
					try {
						
						ResultSet rs1 = pstmt1.executeQuery();		
					
						while (rs1.next())
						{
							atts = createreferencetableBinding(atts,Reference_id);
							hd_menu.startElement("","","referencetable",atts);
							hd_menu.endElement("","","referencetable");
						}
						rs1.close();
						pstmt1.close();
						pstmt1 = null;			
					}	
					
						catch (Exception e)
						{
							log.log(Level.SEVERE,"getRef_Table", e);
						}
					
					
					finally
					{
						try
						{
							if (pstmt1 != null)
								pstmt1.close ();
						}
						catch (Exception e)
						{}
						pstmt1 = null;
					}
				}
				hd_menu.endElement("","","reference");
			}
			rs.close();
			pstmt.close();
			pstmt = null;			
		}	
		
			catch (Exception e)
			{
				log.log(Level.SEVERE,"getRefence", e);
			}
		
		
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
	}

	public void CreateImp (int import_id, AttributesImpl atts, TransformerHandler hd_menu) throws SAXException
	{
		atts.clear();
		m_ImpFormat = new X_AD_ImpFormat (getCtx(), import_id, null);
		atts = createimpBinding(atts,m_ImpFormat);	
		
		hd_menu.startElement("","","impformat",atts);		
		String sql = "SELECT * FROM AD_ImpFormat_Row WHERE AD_ImpFormat_ID= " + import_id;
		
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());	
		
		try {			
			ResultSet rs = pstmt.executeQuery();		
			while (rs.next())
			{				
				m_ImpFormat_Row = new X_AD_ImpFormat_Row (getCtx(), rs.getInt("AD_ImpFormat_Row_ID"), null);
				atts = createimprowBinding(atts,m_ImpFormat_Row);
				hd_menu.startElement("","","impformatrow",atts);
				hd_menu.endElement("","","impformatrow");			
				
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			}
			catch (Exception e)	{
				log.log(Level.SEVERE,"getProcess", e);
			}
			finally	{
				try	{
					if (pstmt != null)
						pstmt.close ();
				}
				catch (Exception e){}
				pstmt = null;
			}
		hd_menu.endElement("","","impformat");	
		
	}
	public void CreateTable (int table_id, AttributesImpl atts, TransformerHandler hd_menu)
	{	
		int New_Table = 2;
		
		// check to see if table has been exported already
		if (Table_Count == 0){
			Table_ID[Table_Count] = table_id;
			Table_Count = Table_Count+1;
			New_Table = 1; 
		}
		else{
			int i;
			for(i=0;i<Table_Count;i++){
				if (Table_ID[i] == table_id){
					New_Table = 0;
					i=Table_Count;
				}
			}
			if (New_Table == 2){
				New_Table = 1;
				Table_ID[Table_Count] = table_id;
				Table_Count = Table_Count+1;
			}
		}
		
		//Export table if not already done so
		if (New_Table == 1){
			
		String sql = "SELECT Name FROM AD_Table WHERE AD_Table_ID= " + table_id;

		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql, get_TrxName());		
		
		try {
			
			ResultSet rs = pstmt.executeQuery();		
		
			while (rs.next())
			{
				m_Table = new X_AD_Table (getCtx(), table_id, null);										
				atts = createtableBinding(atts,m_Table);	
				hd_menu.startElement("","","table",atts);
				
				String sql1 = "SELECT * FROM AD_Column WHERE AD_Table_ID = " + table_id
				+ " ORDER BY SEQNO asc";				
				
				PreparedStatement pstmt1 = null;
				pstmt1 = DB.prepareStatement (sql1, get_TrxName());		
				
				try {
					
					ResultSet rs1 = pstmt1.executeQuery();		
				
					while (rs1.next()){
						
						if (rs1.getInt("AD_Reference_ID")>0)
							CreateReference (rs1.getInt("AD_Reference_ID"), atts, hd_menu);
						
						if (rs1.getInt("AD_Reference_Value_ID")>0)
							CreateReference (rs1.getInt("AD_Reference_Value_ID"), atts, hd_menu);						

						if (rs1.getInt("AD_Process_ID")>0)
							CreateProcess (rs1.getInt("AD_Process_ID"), atts, hd_menu);							

						m_Column = new X_AD_Column (getCtx(), rs1.getInt("AD_Column_ID"), null);
						atts = createcolumnBinding(atts,m_Column);
						hd_menu.startElement("","","column",atts);
						hd_menu.endElement("","","column");
					}
		
					rs1.close();
					pstmt1.close();
					pstmt1 = null;
					}
					catch (Exception e)	{
						log.log(Level.SEVERE,"getProcess", e);
					}
					finally	{
						try	{
							if (pstmt1 != null)
								pstmt1.close ();
						}
						catch (Exception e){}
						pstmt1 = null;
					}	
					hd_menu.endElement("","","table");
			}
			rs.close();
			pstmt.close();
			pstmt = null;
			}
			
			catch (Exception e){
				log.log(Level.SEVERE,"getProcess", e);
			}
			finally{
				try	{
					if (pstmt != null)
						pstmt.close ();
				}
				catch (Exception e){}
				pstmt = null;
			}
		}
	}	
	public static AttributesImpl createfieldBinding( AttributesImpl atts, X_AD_Field m_Field) 
	{        
        String sql = null;
        String name = null;        
        atts.clear();
        if (m_Field.getAD_Column_ID()> 0 ){
        	sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Field.getAD_Column_ID());
        	atts.addAttribute("","","ADColumnNameID","CDATA",name);
        	}
        else
        	atts.addAttribute("","","ADColumnNameID","CDATA","");	
        
        if (m_Field.getAD_Column_ID()> 0 ){
            sql = "SELECT AD_Table_ID FROM AD_Column WHERE AD_Column_ID=?";
            int idTable = DB.getSQLValue(null, sql,m_Field.getAD_Column_ID());
            sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
            name = DB.getSQLValueString(null,sql,idTable);
            atts.addAttribute("","","ADTableNameID","CDATA",name);
        	}
        else
            atts.addAttribute("","","ADTableNameID","CDATA","");        
        if (m_Field.getAD_FieldGroup_ID()> 0 ){
        	sql = "SELECT Name FROM AD_FieldGroup WHERE AD_FieldGroup_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Field.getAD_FieldGroup_ID());        
        	atts.addAttribute("","","ADFieldGroupNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADFieldGroupNameID","CDATA","");        
        
        if (m_Field.getAD_Field_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Field WHERE AD_Field_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Field.getAD_Field_ID());        
        	atts.addAttribute("","","ADFieldNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADFieldNameID","CDATA","");
        
        if (m_Field.getAD_Tab_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Tab WHERE AD_Tab_ID=?";        	
        	name = DB.getSQLValueString(null,sql,m_Field.getAD_Tab_ID());        
        	atts.addAttribute("","","ADTabNameID","CDATA",name);        	
        	sql = "SELECT AD_Window_ID FROM AD_Tab WHERE AD_Tab_ID=?";        	
        	int windowid = DB.getSQLValue(null,sql,m_Field.getAD_Tab_ID());
        	sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
            name = DB.getSQLValueString(null,sql,windowid);
            atts.addAttribute("","","ADWindowNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADTabNameID","CDATA","");
        
        atts.addAttribute("","","EntityType","CDATA",(m_Field.getEntityType () != null ? m_Field.getEntityType ():""));
        atts.addAttribute("","","Name","CDATA",(m_Field.getName () != null ? m_Field.getName ():""));
        atts.addAttribute("","","SameLine","CDATA", (m_Field.isSameLine() == true ? "true":"false"));
        atts.addAttribute("","","isCentrallyMaintained","CDATA",(m_Field.isCentrallyMaintained()== true ? "true":"false"));
        atts.addAttribute("","","Displayed","CDATA",(m_Field.isDisplayed()== true ? "true":"false"));
        atts.addAttribute("","","isActive","CDATA",(m_Field.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isEncrypted","CDATA",(m_Field.isEncrypted()== true ? "true":"false"));
        atts.addAttribute("","","isFieldOnly","CDATA",(m_Field.isFieldOnly()== true ? "true":"false"));
        atts.addAttribute("","","isHeading","CDATA",(m_Field.isHeading()== true ? "true":"false"));
        atts.addAttribute("","","isReadOnly","CDATA",(m_Field.isReadOnly()== true ? "true":"false"));
        atts.addAttribute("","","SeqNo","CDATA", "" + (m_Field.getSeqNo()));        
        atts.addAttribute("","","DisplayLength","CDATA",(m_Field.getDisplayLength () > 0 ? "" + m_Field.getDisplayLength():"0"));
        atts.addAttribute("","","Description","CDATA",(m_Field.getDescription () != null ? m_Field.getDescription():""));
        atts.addAttribute("","","Help","CDATA",(m_Field.getHelp () != null ? m_Field.getHelp():""));
        atts.addAttribute("","","SortNo","CDATA",(m_Field.getSortNo () != null ? m_Field.getSortNo().toString():""));
        atts.addAttribute("","","DisplayLogic","CDATA",(m_Field.getDisplayLogic () != null ? m_Field.getDisplayLogic():""));
        atts.addAttribute("","","ObscureType","CDATA",(m_Field.getObscureType () != null ? m_Field.getObscureType():""));
        return atts;
	}
	public  static AttributesImpl createpreferenceBinding( AttributesImpl atts, X_AD_Preference m_Preference) 
	{        
        String sql = null;
        String name = null;
        atts.clear();
        sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
        name = DB.getSQLValueString(null,sql,m_Preference.getAD_Window_ID());
        atts.addAttribute("","","ADWindowNameID","CDATA",name);
        sql = "SELECT Name FROM AD_User WHERE AD_User_ID=?";
        name = DB.getSQLValueString(null,sql,m_Preference.getAD_User_ID());
        atts.addAttribute("","","ADUserNameID","CDATA",name);
        atts.addAttribute("","","Attribute","CDATA",m_Preference.getAttribute());
        atts.addAttribute("","","Value","CDATA",m_Preference.getValue());
        return atts;
	}
	
	public  static AttributesImpl createtabBinding( AttributesImpl atts, X_AD_Tab m_Tab) 
	{        
        String sql = null;
        String name = null;
        atts.clear();
        atts.addAttribute("","","Name","CDATA",(m_Tab.getName () != null ? m_Tab.getName ():"")); 
        if (m_Tab.getAD_ColumnSortOrder_ID()>0){
	        sql = "SELECT Name FROM AD_Column WHERE AD_Column_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Tab.getAD_ColumnSortOrder_ID());
            atts.addAttribute("","","ADColumnSortOrderNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADColumnSortOrderNameID","CDATA","");        
        if (m_Tab.getAD_ColumnSortYesNo_ID()>0   ){
	        sql = "SELECT Name FROM AD_Column WHERE AD_Column_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Tab.getAD_ColumnSortYesNo_ID());        
	        atts.addAttribute("","","ADColumnSortYesNoNameID","CDATA",name);        
        }
        else
        	atts.addAttribute("","","ADColumnSortYesNoNameID","CDATA",""); 
        if (m_Tab.getAD_Column_ID()>0  ){        
        	sql = "SELECT Name FROM AD_Column WHERE AD_Column_ID=?";
            name = DB.getSQLValueString(null,sql,m_Tab.getAD_Column_ID());
            atts.addAttribute("","","ADColumnNameID","CDATA",name);
        }
        else        	
        	atts.addAttribute("","","ADColumnNameID","CDATA","");       
        if (m_Tab.getAD_Image_ID() >0 ){        
        	sql = "SELECT Name FROM AD_Image WHERE AD_Image_ID=?";
            name = DB.getSQLValueString(null,sql,m_Tab.getAD_Image_ID());        
            atts.addAttribute("","","ADImageNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADImageNameID","CDATA","");	
        if (m_Tab.getAD_Process_ID() >0 ){        
	        sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Tab.getAD_Process_ID());
	        atts.addAttribute("","","ADProcessNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADProcessNameID","CDATA","");        
        if (m_Tab.getAD_Tab_ID() >0  ){
       		sql = "SELECT Name FROM AD_Tab WHERE AD_Tab_ID=?";
       		name = DB.getSQLValueString(null,sql,m_Tab.getAD_Tab_ID());
       		atts.addAttribute("","","ADTabNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADTabNameID","CDATA","");
        
        sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
        name = DB.getSQLValueString(null,sql,m_Tab.getAD_Table_ID());
        atts.addAttribute("","","ADTableNameID","CDATA",name);        
        sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
        name = DB.getSQLValueString(null,sql,m_Tab.getAD_Window_ID());
        atts.addAttribute("","","ADWindowNameID","CDATA",name);
        if (m_Tab.getIncluded_Tab_ID() > 0  ){       
        	sql = "SELECT Name FROM AD_Tab WHERE AD_Tab_ID=?";
            name = DB.getSQLValueString(null,sql,m_Tab.getIncluded_Tab_ID());
            atts.addAttribute("","","IncludedTabNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","IncludedTabNameID","CDATA","");
        atts.addAttribute("","","CommitWarning","CDATA",(m_Tab.getCommitWarning () != null ? m_Tab.getCommitWarning ():""));        
        atts.addAttribute("","","Description","CDATA",(m_Tab.getDescription () != null ? m_Tab.getDescription ():""));        
        atts.addAttribute("","","EntityType","CDATA",(m_Tab.getEntityType () != null ? m_Tab.getEntityType ():""));        
        atts.addAttribute("","","isHasTree","CDATA",(m_Tab.isHasTree()== true ? "true":"false"));        
        atts.addAttribute("","","Help","CDATA",(m_Tab.getHelp () != null ? m_Tab.getHelp ():""));        
        atts.addAttribute("","","isInfoTab","CDATA",(m_Tab.isInfoTab()== true ? "true":"false"));
        atts.addAttribute("","","isReadOnly","CDATA",(m_Tab.isReadOnly()== true ? "true":"false"));
        atts.addAttribute("","","isSingleRow","CDATA",(m_Tab.isSingleRow()== true ? "true":"false"));
        atts.addAttribute("","","isSortTab","CDATA",(m_Tab.isSortTab()== true ? "true":"false"));
        atts.addAttribute("","","isActive","CDATA",(m_Tab.isActive()== true ? "true":"false"));
        atts.addAttribute("","","IsTranslationTab","CDATA",(m_Tab.isTranslationTab()== true ? "true":"false"));        
        atts.addAttribute("","","OrderByClause","CDATA",(m_Tab.getOrderByClause () != null ? m_Tab.getOrderByClause ():""));
        atts.addAttribute("","","isProcessing","CDATA",(m_Tab.isProcessing()== true ? "true":"false"));
        atts.addAttribute("","","SeqNo","CDATA",(m_Tab.getSeqNo () >= 0 ? "" + m_Tab.getSeqNo ():"0"));
        atts.addAttribute("","","TabLevel","CDATA",(m_Tab.getTabLevel () >= 0 ? "" + m_Tab.getTabLevel ():""));
        atts.addAttribute("","","WhereClause","CDATA",(m_Tab.getWhereClause () != null ? m_Tab.getWhereClause ():""));
        atts.addAttribute("","","Syncfields","CDATA","false");                
        return atts;		
	}
	
	public static AttributesImpl createwindowBinding( AttributesImpl atts, X_AD_Window m_Window) 
	{
		atts.clear();
        String sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
        String name = DB.getSQLValueString(null,sql,m_Window.getAD_Window_ID());
        atts.addAttribute("","","ADWindowNameID","CDATA",name);
        if (m_Window.getAD_Image_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Image WHERE AD_Image_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Window.getAD_Image_ID());
        }        
        if (name != null )
        	atts.addAttribute("","","ADImageNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADImageNameID","CDATA","");	
        if (m_Window.getAD_Color_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Color WHERE AD_Color_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Window.getAD_Color_ID());
        }        
        if (name != null )
        	atts.addAttribute("","","ADColorNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADColorNameID","CDATA","");	
        atts.addAttribute("","","Description","CDATA",(m_Window.getDescription () != null ? m_Window.getDescription ():""));        
        atts.addAttribute("","","EntityType","CDATA",(m_Window.getEntityType () != null ? m_Window.getEntityType ():""));        
        atts.addAttribute("","","Help","CDATA",(m_Window.getHelp () != null ? m_Window.getHelp ():""));        
        atts.addAttribute("","","isBetaFunctionality","CDATA",(m_Window.isBetaFunctionality()== true ? "true":"false"));        
        atts.addAttribute("","","isDefault","CDATA",(m_Window.isDefault()== true ? "true":"false"));        
        atts.addAttribute("","","isSOTrx","CDATA",(m_Window.isSOTrx()== true ? "true":"false"));
        atts.addAttribute("","","isActive","CDATA",(m_Window.isActive()== true ? "true":"false"));
        atts.addAttribute("","","Name","CDATA",(m_Window.getName () != null ? m_Window.getName ():""));        
        atts.addAttribute("","","isProcessing","CDATA",(m_Window.isProcessing()== true ? "true":"false"));        
        atts.addAttribute("","","WinHeight","CDATA",(m_Window.getWinHeight () > 0 ? "" + m_Window.getWinHeight ():""));        
        atts.addAttribute("","","WinWidth","CDATA",(m_Window.getWinWidth ()  > 0 ? "" + m_Window.getWinWidth ():""));        
        atts.addAttribute("","","WindowType","CDATA",(m_Window.getWindowType () != null ? m_Window.getWindowType ():""));
        return atts;		
	}

	public static AttributesImpl createmenuBinding( AttributesImpl atts, X_AD_Menu m_Menu) 
	{        
        String sql = null;
        String name = null;
        atts.clear();
        if (m_Menu.getAD_Menu_ID()> 0 ){
        	
            sql = "SELECT Name FROM AD_Menu WHERE AD_Menu_ID=?";
            name = DB.getSQLValueString(null,sql,m_Menu.getAD_Menu_ID());
            atts.addAttribute("","","ADMenuNameID","CDATA",name);
            }
        else
            atts.addAttribute("","","ADMenuNameID","CDATA","");
        if (m_Menu.getAD_Window_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Menu.getAD_Window_ID());
	        atts.addAttribute("","","ADWindowNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADWindowNameID","CDATA","");
        if (m_Menu.getAD_Process_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Menu.getAD_Process_ID());        
        	atts.addAttribute("","","ADProcessNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADProcessNameID","CDATA","");
        if (m_Menu.getAD_Form_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Form WHERE AD_Form_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Menu.getAD_Form_ID());                
	        atts.addAttribute("","","ADFormNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADFormNameID","CDATA","");        
        if (m_Menu.getAD_Task_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Task WHERE AD_Task_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Menu.getAD_Task_ID());
	        atts.addAttribute("","","ADTaskNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADTaskNameID","CDATA","");	
        if (m_Menu.getAD_Workbench_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Workbench WHERE AD_Workbench_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Menu.getAD_Workbench_ID());
	        atts.addAttribute("","","ADWorkbenchNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADWorkbenchNameID","CDATA","");	
        if (m_Menu.getAD_Workflow_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Workflow WHERE AD_Workflow_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Menu.getAD_Workflow_ID());
        	atts.addAttribute("","","ADWorkflowNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADWorkflowNameID","CDATA","");
        sql = "SELECT Parent_ID FROM AD_TreeNoDemm WHERE AD_Tree_ID = 10 and Node_ID=?";        
        int id = DB.getSQLValue(null, sql,m_Menu.getAD_Menu_ID());
        if (id > 0){
        	sql = "SELECT Name FROM AD_Menu WHERE AD_Menu_ID=?";
        	name = DB.getSQLValueString(null,sql,id);
        	atts.addAttribute("","","ADParentMenuNameID","CDATA",name);
        }
        sql = "SELECT SeqNo FROM AD_TreeNoDemm WHERE AD_Tree_ID = 10 and Node_ID=?";        
        id = DB.getSQLValue(null, sql,m_Menu.getAD_Menu_ID());        
        atts.addAttribute("","","ADParentSeqno","CDATA",""+id);
        atts.addAttribute("","","Action","CDATA",(m_Menu.getAction () != null ? m_Menu.getAction ():""));
        atts.addAttribute("","","Description","CDATA",(m_Menu.getDescription () != null ? m_Menu.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Menu.getEntityType () != null ? m_Menu.getEntityType ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Menu.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isReadOnly","CDATA",(m_Menu.isReadOnly()== true ? "true":"false"));
        atts.addAttribute("","","isSOTrx","CDATA",(m_Menu.isSOTrx()== true ? "true":"false"));
        atts.addAttribute("","","isSummary","CDATA",(m_Menu.isSummary()== true ? "true":"false"));
        return atts;
	}

	public static AttributesImpl createprocessBinding( AttributesImpl atts, X_AD_Process m_Process) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
        atts.addAttribute("","","Name","CDATA",(m_Process.getName () != null ? m_Process.getName ():""));
        
		if (m_Process.getAD_Workflow_ID()> 0 ){        
	        sql = "SELECT Name FROM AD_Workflow WHERE AD_Workflow_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Process.getAD_Workflow_ID());		
	        atts.addAttribute("","","ADWorkflowNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADWorkflowNameID","CDATA","");
        if (m_Process.getAD_Process_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Process.getAD_Process_ID());      
	        atts.addAttribute("","","ADProcessNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADProcessNameID","CDATA","");
        if (m_Process.getAD_PrintFormat_ID()> 0 ){
	        sql = "SELECT Name FROM AD_PrintFormat WHERE AD_PrintFormat_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Process.getAD_PrintFormat_ID());        
	        atts.addAttribute("","","ADPrintFormatNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADPrintFormatNameID","CDATA","");
        if (m_Process.getAD_ReportView_ID()> 0 ){
	        sql = "SELECT Name FROM AD_ReportView WHERE AD_ReportView_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Process.getAD_ReportView_ID());
	        atts.addAttribute("","","ADReportViewNameID","CDATA",name);
	     	}
        else
        	atts.addAttribute("","","ADReportViewNameID","CDATA","");        
        atts.addAttribute("","","AccessLevel","CDATA",(m_Process.getAccessLevel () != null ? m_Process.getAccessLevel ():""));
        atts.addAttribute("","","Classname","CDATA",(m_Process.getClassname () != null ? m_Process.getClassname ():""));
		atts.addAttribute("","","Description","CDATA",(m_Process.getDescription () != null ? m_Process.getDescription ():""));
		atts.addAttribute("","","EntityType","CDATA",(m_Process.getEntityType () != null ? m_Process.getEntityType ():""));
		atts.addAttribute("","","Help","CDATA",(m_Process.getHelp () != null ? m_Process.getHelp ():""));
		atts.addAttribute("","","isBetaFunctionality","CDATA",(m_Process.isBetaFunctionality()== true ? "true":"false"));
		atts.addAttribute("","","isDirectPrint","CDATA",(m_Process.isDirectPrint()== true ? "true":"false"));
		atts.addAttribute("","","isReport","CDATA",(m_Process.isReport()== true ? "true":"false"));
		atts.addAttribute("","","isActive","CDATA",(m_Process.isActive()== true ? "true":"false"));
		atts.addAttribute("","","ProcedureName","CDATA",(m_Process.getProcedureName () != null ? m_Process.getProcedureName ():""));
		atts.addAttribute("","","StatisticCount","CDATA","0");
		atts.addAttribute("","","StatisticSeconds","CDATA","0");
		atts.addAttribute("","","Value","CDATA",(m_Process.getValue () != null ? m_Process.getValue ():""));
		atts.addAttribute("","","WorkflowValue","CDATA",(m_Process.getWorkflowValue () != null ? m_Process.getWorkflowValue ():""));
		return atts;
	}

	public static AttributesImpl createprocessparaBinding( AttributesImpl atts, X_AD_Process_Para m_Processpara) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        atts.addAttribute("","","Name","CDATA",(m_Processpara.getName () != null ? m_Processpara.getName ():""));
        if (m_Processpara.getAD_Process_ID()> 0 ){        
	        sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Processpara.getAD_Process_ID());
	        atts.addAttribute("","","ADProcessNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADProcessNameID","CDATA","");
        if (m_Processpara.getAD_Process_Para_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Process_Para WHERE AD_Process_Para_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Processpara.getAD_Process_Para_ID());
	        atts.addAttribute("","","ADProcessParaNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADProcessParaNameID","CDATA","");
        if (m_Processpara.getAD_Element_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Element WHERE AD_Element_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Processpara.getAD_Element_ID());
	        atts.addAttribute("","","ADElementNameID","CDATA",name);
	        }
        else
        	atts.addAttribute("","","ADElementNameID","CDATA","");
        if (m_Processpara.getAD_Reference_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Processpara.getAD_Reference_ID());
	        atts.addAttribute("","","ADReferenceNameID","CDATA",name);
        	}
        else
        	atts.addAttribute("","","ADReferenceNameID","CDATA","");
        if (m_Processpara.getAD_Reference_Value_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Processpara.getAD_Reference_Value_ID());
	        atts.addAttribute("","","ADReferenceValueNameID","CDATA",name);
        	}
        else
        	 atts.addAttribute("","","ADReferenceValueNameID","CDATA","");
        if (m_Processpara.getAD_Val_Rule_ID()> 0 ){
	        sql = "SELECT Name FROM AD_Val_Rule WHERE AD_Val_Rule_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Processpara.getAD_Val_Rule_ID());
	        atts.addAttribute("","","ADValRuleNameID","CDATA",name);
        	}
        else
        	 atts.addAttribute("","","ADValRuleNameID","CDATA","");
        atts.addAttribute("","","ColumnName","CDATA",(m_Processpara.getColumnName () != null ? m_Processpara.getColumnName ():""));
        atts.addAttribute("","","DefaultValue","CDATA",(m_Processpara.getDefaultValue () != null ? m_Processpara.getDefaultValue ():""));
        atts.addAttribute("","","DefaultValue2","CDATA",(m_Processpara.getDefaultValue2 () != null ? m_Processpara.getDefaultValue2 ():""));
        atts.addAttribute("","","Description","CDATA",(m_Processpara.getDescription () != null ? m_Processpara.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Processpara.getEntityType () != null ? m_Processpara.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Processpara.getHelp () != null ? m_Processpara.getHelp ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Processpara.isActive()== true ? "true":"false"));
        atts.addAttribute("","","VFormat","CDATA",(m_Processpara.getVFormat () != null ? m_Processpara.getVFormat ():""));
        atts.addAttribute("","","ValueMax","CDATA",(m_Processpara.getValueMax () != null ? m_Processpara.getValueMax ():""));
        atts.addAttribute("","","ValueMin","CDATA",(m_Processpara.getValueMin () != null ? m_Processpara.getValueMin ():""));
        atts.addAttribute("","","SeqNo","CDATA",(m_Processpara.getSeqNo() > 0 ? "" + m_Processpara.getSeqNo ():"0"));
        atts.addAttribute("","","FieldLength","CDATA",(m_Processpara.getFieldLength () > 0 ?  "" + m_Processpara.getFieldLength ():"0"));
        atts.addAttribute("","","isCentrallyMaintained","CDATA",(m_Processpara.isCentrallyMaintained()== true ? "true":"false"));
        atts.addAttribute("","","isMandatory","CDATA",(m_Processpara.isMandatory()== true ? "true":"false"));
        atts.addAttribute("","","isRange","CDATA",(m_Processpara.isRange()== true ? "true":"false"));
        return atts;
	}

	public static AttributesImpl createtableBinding( AttributesImpl atts, X_AD_Table m_Table) 
	{
		String sql = null;
        String name = null;        
        atts.clear();
        atts.addAttribute("","","Name","CDATA",(m_Table.getName () != null ? m_Table.getName ():""));        
        if (m_Table.getAD_Table_ID()> 0 ){
	        sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
	        name = DB.getSQLValueString(null,sql,m_Table.getAD_Table_ID());        
	        atts.addAttribute("","","ADTableNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADTableNameID","CDATA","");
        if (m_Table.getAD_Window_ID()> 0 ){
        	sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Table.getAD_Window_ID());        
        	atts.addAttribute("","","ADWindowNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADWindowNameID","CDATA","");
        if (m_Table.getPO_Window_ID()> 0 ){
            sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
            name = DB.getSQLValueString(null,sql,m_Table.getPO_Window_ID());       
            atts.addAttribute("","","POWindowNameID","CDATA",name);
            }
        else
            atts.addAttribute("","","POWindowNameID","CDATA","");
        if (m_Table.getAD_Val_Rule_ID()> 0 ){
            sql = "SELECT Name FROM AD_Val_Rule WHERE AD_Val_Rule_ID=?";
            name = DB.getSQLValueString(null,sql,m_Table.getAD_Val_Rule_ID());        
            atts.addAttribute("","","ADValRuleNameID","CDATA",name);
            }
        else
        	atts.addAttribute("","","ADValRuleNameID","CDATA","");	
        atts.addAttribute("","","AccessLevel","CDATA",(m_Table.getAccessLevel () != null ? m_Table.getAccessLevel ():""));
        atts.addAttribute("","","Description","CDATA",(m_Table.getDescription () != null ? m_Table.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Table.getEntityType () != null ? m_Table.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Table.getHelp() != null ? m_Table.getHelp ():""));
        atts.addAttribute("","","ImportTable","CDATA",(m_Table.getImportTable () != null ? m_Table.getImportTable ():""));
        atts.addAttribute("","","isChangeLog","CDATA",(m_Table.isChangeLog()== true ? "true":"false"));
        atts.addAttribute("","","isActive","CDATA",(m_Table.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isDeleteable","CDATA",(m_Table.isDeleteable()== true ? "true":"false"));
        atts.addAttribute("","","isHighVolume","CDATA",(m_Table.isHighVolume()== true ? "true":"false"));
        atts.addAttribute("","","isSecurityEnabled","CDATA",(m_Table.isSecurityEnabled()== true ? "true":"false"));
        atts.addAttribute("","","isView","CDATA",(m_Table.isView()== true ? "true":"false"));
        atts.addAttribute("","","LoadSeq","CDATA",(m_Table.getLoadSeq ()> 0 ? "" + m_Table.getLoadSeq ():""));        
        atts.addAttribute("","","ReplicationType","CDATA",(m_Table.getReplicationType () != null ? m_Table.getReplicationType ():""));
        atts.addAttribute("","","TableName","CDATA",(m_Table.getTableName () != null ? m_Table.getTableName ():""));
        return atts;
	}
	public static AttributesImpl createcolumnBinding( AttributesImpl atts, X_AD_Column m_Column) 
	{    
        String sql = null;
        String name = null;
        atts.clear();
        if (m_Column.getAD_Column_ID()> 0 ){
        	sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Column.getAD_Column_ID());
        	atts.addAttribute("","","ADColumnNameID","CDATA",name);
        }
        else
        	atts.addAttribute("","","ADColumnNameID","CDATA","");
        if (m_Column.getAD_Process_ID()> 0 ){        
            sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
            name = DB.getSQLValueString(null,sql,m_Column.getAD_Process_ID());        
            atts.addAttribute("","","ADProcessNameID","CDATA",name);
            }
        else
        	atts.addAttribute("","","ADProcessNameID","CDATA","");            
        if (m_Column.getAD_Element_ID()> 0 ){
            sql = "SELECT Name FROM AD_Element WHERE AD_Element_ID=?";
            name = DB.getSQLValueString(null,sql,m_Column.getAD_Element_ID());        
            atts.addAttribute("","","ADElementNameID","CDATA",name);
            } 
        else
         	atts.addAttribute("","","ADElementNameID","CDATA","");
        if (m_Column.getAD_Reference_ID()> 0 ){
            sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
            name = DB.getSQLValueString(null,sql,m_Column.getAD_Reference_ID());            
            atts.addAttribute("","","ADReferenceNameID","CDATA",name);
            }
        else
         	atts.addAttribute("","","ADReferenceNameID","CDATA","");
        if (m_Column.getAD_Reference_Value_ID()> 0 ){
            sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
            name = DB.getSQLValueString(null,sql,m_Column.getAD_Reference_Value_ID());            
            atts.addAttribute("","","ADReferenceNameValueID","CDATA",name);
            }
        else
         	atts.addAttribute("","","ADReferenceNameValueID","CDATA","");
        if (m_Column.getAD_Table_ID()> 0 ){
            sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
            name = DB.getSQLValueString(null,sql,m_Column.getAD_Table_ID());       
            atts.addAttribute("","","ADTableNameID","CDATA",name);
        	}
        else
         	atts.addAttribute("","","ADTableNameID","CDATA","");
        if (m_Column.getAD_Val_Rule_ID()> 0 ){
            sql = "SELECT Name FROM AD_Val_Rule WHERE AD_Val_Rule_ID=?";
            name = DB.getSQLValueString(null,sql,m_Column.getAD_Val_Rule_ID());        
            atts.addAttribute("","","ADValRuleNameID","CDATA",name);
            }
        else
        	atts.addAttribute("","","ADValRuleNameID","CDATA","");
        atts.addAttribute("","","Callout","CDATA",(m_Column.getCallout () != null ? m_Column.getCallout ():""));
        atts.addAttribute("","","ColumnName","CDATA",(m_Column.getColumnName () != null ? m_Column.getColumnName ():""));
        atts.addAttribute("","","DefaultValue","CDATA",(m_Column.getDefaultValue () != null ? m_Column.getDefaultValue ():""));
        atts.addAttribute("","","Description","CDATA",(m_Column.getDescription () != null ? m_Column.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Column.getEntityType () != null ? m_Column.getEntityType ():""));
        atts.addAttribute("","","FieldLength","CDATA",(m_Column.getFieldLength () > 0 ? "" + m_Column.getFieldLength ():"0"));
        atts.addAttribute("","","Help","CDATA",(m_Column.getHelp () != null ? m_Column.getHelp ():""));
        atts.addAttribute("","","isAlwaysUpdateable","CDATA",(m_Column.isAlwaysUpdateable()== true ? "true":"false"));
        //atts.addAttribute("","","isEncrypted","CDATA",(m_Column.getIsEncrypted()== true ? "true":"false"));
        atts.addAttribute("","","isIdentifier","CDATA",(m_Column.isIdentifier()== true ? "true":"false"));
        atts.addAttribute("","","isKey","CDATA",(m_Column.isKey()== true ? "true":"false"));
        atts.addAttribute("","","isMandatory","CDATA",(m_Column.isMandatory()== true ? "true":"false"));
        atts.addAttribute("","","isParent","CDATA",(m_Column.isParent()== true ? "true":"false"));
        atts.addAttribute("","","isSelectionColumn","CDATA",(m_Column.isSelectionColumn()== true ? "true":"false"));
        atts.addAttribute("","","isActive","CDATA",(m_Column.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isTranslated","CDATA",(m_Column.isTranslated()== true ? "true":"false"));
        atts.addAttribute("","","isUpdateable","CDATA",(m_Column.isUpdateable()== true ? "true":"false"));
        atts.addAttribute("","","Name","CDATA",(m_Column.getName () != null ? m_Column.getName ():""));
        atts.addAttribute("","","getIsSyncDatabase","CDATA",(m_Column.getIsSyncDatabase () != null ? m_Column.getIsSyncDatabase ():""));
        atts.addAttribute("","","ReadOnlyLogic","CDATA",(m_Column.getReadOnlyLogic () != null ? m_Column.getReadOnlyLogic ():""));
        atts.addAttribute("","","SeqNo","CDATA",(m_Column.getSeqNo ()> 0 ? "" + m_Column.getSeqNo ():"0"));
        atts.addAttribute("","","VFormat","CDATA",(m_Column.getVFormat () != null ? m_Column.getVFormat ():""));
        atts.addAttribute("","","ValueMax","CDATA",(m_Column.getValueMax () != null ? m_Column.getValueMax ():""));
        atts.addAttribute("","","ValueMin","CDATA",(m_Column.getValueMin () != null ? m_Column.getValueMin ():""));
        atts.addAttribute("","","Value","CDATA",(m_Column.getVersion()!= null ? "" + m_Column.getVersion ():"0.0"));
        return atts;
	}
	public static AttributesImpl  createtaskBinding( AttributesImpl atts, X_AD_Task m_Task) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_Task.getAD_Task_ID()> 0 ){
            sql = "SELECT Name FROM AD_Task WHERE AD_Task_ID=?";
            name = DB.getSQLValueString(null,sql,m_Task.getAD_Task_ID());
        }
        if (name != null )
            atts.addAttribute("","","ADTaskNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADTaskNameID","CDATA","");	
        atts.addAttribute("","","AccessLevel","CDATA",(m_Task.getAccessLevel () != null ? m_Task.getAccessLevel ():""));
        atts.addAttribute("","","Description","CDATA",(m_Task.getDescription () != null ? m_Task.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Task.getEntityType () != null ? m_Task.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Task.getHelp () != null ? m_Task.getHelp ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Task.isActive()== true ? "true":"false"));
        atts.addAttribute("","","Name","CDATA",(m_Task.getName () != null ? m_Task.getName ():""));
        atts.addAttribute("","","OS_Command","CDATA",(m_Task.getOS_Command () != null ? m_Task.getOS_Command ():""));
        return atts;                
	}
	
	public  static AttributesImpl  createformBinding( AttributesImpl atts, X_AD_Form m_Form) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_Form.getAD_Form_ID()> 0 ){
            sql = "SELECT Name FROM AD_Form WHERE AD_Form_ID=?";
            name = DB.getSQLValueString(null,sql,m_Form.getAD_Form_ID());
        }
        if (name != null )
            atts.addAttribute("","","ADFormNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADFormNameID","CDATA","");	
        atts.addAttribute("","","Classname","CDATA",(m_Form.getClassname () != null ? m_Form.getClassname ():""));
        atts.addAttribute("","","isBetaFunctionality","CDATA",(m_Form.isBetaFunctionality()== true ? "true":"false"));
        atts.addAttribute("","","AccessLevel","CDATA",(m_Form.getAccessLevel () != null ? m_Form.getAccessLevel ():""));
        atts.addAttribute("","","Description","CDATA",(m_Form.getDescription () != null ? m_Form.getDescription ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Form.isActive()== true ? "true":"false"));
        atts.addAttribute("","","EntityType","CDATA",(m_Form.getEntityType () != null ? m_Form.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Form.getHelp() != null ? m_Form.getHelp():""));
        atts.addAttribute("","","Name","CDATA",(m_Form.getName() != null ? m_Form.getName():""));
        return atts;
	}
	public static AttributesImpl  createworkbenchBinding( AttributesImpl atts, X_AD_Workbench m_Workbench) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_Workbench.getAD_Workbench_ID()> 0 ){
                sql = "SELECT Name FROM AD_Workbench WHERE AD_Workbench_ID=?";
                name = DB.getSQLValueString(null,sql,m_Workbench.getAD_Workbench_ID());
        }        
        if (name != null )
                atts.addAttribute("","","ADWorkbenchNameID","CDATA",name);
        else
        		atts.addAttribute("","","ADWorkbenchNameID","CDATA","");
        if (m_Workbench.getAD_Column_ID()> 0 ){
                sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
                name = DB.getSQLValueString(null,sql,m_Workbench.getAD_Column_ID());
        }        
        if (name != null )
                atts.addAttribute("","","ADColumnNameID","CDATA",name);
        else
        		atts.addAttribute("","","ADColumnNameID","CDATA","");
        atts.addAttribute("","","Description","CDATA",(m_Workbench.getDescription () != null ? m_Workbench.getDescription ():""));    
        atts.addAttribute("","","EntityType","CDATA",(m_Workbench.getEntityType () != null ? m_Workbench.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Workbench.getHelp () != null ? m_Workbench.getHelp ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Workbench.isActive()== true ? "true":"false"));
        atts.addAttribute("","","Name","CDATA",(m_Workbench.getName () != null ? m_Workbench.getName ():""));
        //atts.addAttribute("","","PA_Goal_ID","CDATA",(m_Workbench.getPA_Goal_ID() > 0 ? "" + m_Workbench.getPA_Goal_ID ():""));
        return atts;
	}


	public   static AttributesImpl  createworkbenchwindowBinding( AttributesImpl atts, X_AD_WorkbenchWindow m_Workbenchwindow) 
	{
		String sql = null;
        String name = null;
        atts.clear();
       if (m_Workbenchwindow.getAD_Process_ID()> 0 ){
            sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
            name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Process_ID());       
            atts.addAttribute("","","name","CDATA",name);
            }
       else
       		atts.addAttribute("","","name","CDATA","");
        if (m_Workbenchwindow.getAD_Workbench_ID()> 0 ){
            sql = "SELECT Name FROM AD_Workbench WHERE AD_Workbench_ID=?";
            name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Workbench_ID());
        }
        if (name != null )
            atts.addAttribute("","","ADWorkbenchNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADWorkbenchNameID","CDATA","");   
        if (m_Workbenchwindow.getAD_Form_ID()> 0 ){
            sql = "SELECT Name FROM AD_Form WHERE AD_Form_ID=?";
            name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Form_ID());
        }
        if (name != null )
            atts.addAttribute("","","ADFormNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADFormNameID","CDATA","");   
        if (m_Workbenchwindow.getAD_Window_ID()> 0 ){
            sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
            name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Window_ID());
        }
        if (name != null )
        	atts.addAttribute("","","ADWindowNameID","CDATA",name);            
        else
        	atts.addAttribute("","","ADWindowNameID","CDATA","");     
        if (m_Workbenchwindow.getAD_Task_ID()> 0 ){
       		sql = "SELECT Name FROM AD_Task WHERE AD_Task_ID=?";
        	name = DB.getSQLValueString(null,sql,m_Workbenchwindow.getAD_Task_ID());
       }
       if (name != null )
            atts.addAttribute("","","ADTaskNameID","CDATA",name);
       else
       		atts.addAttribute("","","ADTaskNameID","CDATA","");       
       atts.addAttribute("","","EntityType","CDATA",(m_Workbenchwindow.getEntityType () != null ? m_Workbenchwindow.getEntityType ():""));
       atts.addAttribute("","","SeqNo","CDATA",(m_Workbenchwindow.getSeqNo () > 0 ? "" + m_Workbenchwindow.getSeqNo ():""));
       atts.addAttribute("","","isActive","CDATA",(m_Workbenchwindow.isActive()== true ? "true":"false"));
       atts.addAttribute("","","isPrimary","CDATA",(m_Workbenchwindow.isPrimary()== true ? "true":"false"));
       return atts;
	}

	public  static AttributesImpl createreferenceBinding( AttributesImpl atts, X_AD_Reference m_Reference) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_Reference.getAD_Reference_ID()> 0 ){
                sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
                name = DB.getSQLValueString(null,sql,m_Reference.getAD_Reference_ID());
                atts.addAttribute("","","name","CDATA",name);
            }
        else
        		atts.addAttribute("","","name","CDATA","");        
        atts.addAttribute("","","Description","CDATA",(m_Reference.getDescription () != null ? m_Reference.getDescription ():"")); 
        atts.addAttribute("","","EntityType","CDATA",(m_Reference.getEntityType () != null ? m_Reference.getEntityType ():""));
        atts.addAttribute("","","Help","CDATA",(m_Reference.getHelp () != null ? m_Reference.getHelp ():""));
        atts.addAttribute("","","Name","CDATA",(m_Reference.getName () != null ? m_Reference.getName ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Reference.isActive()== true ? "true":"false"));
        atts.addAttribute("","","VFormat","CDATA",(m_Reference.getVFormat () != null ? m_Reference.getVFormat ():""));
        atts.addAttribute("","","ValidationType","CDATA",(m_Reference.getValidationType () != null ? m_Reference.getValidationType ():""));
        return atts;
	}
	public  static AttributesImpl createimpBinding( AttributesImpl atts, X_AD_ImpFormat m_ImpFormat) 
	{
	atts.clear();		
	if (m_ImpFormat.getAD_Table_ID()> 0 ){
        String sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";        
        String name = DB.getSQLValueString(null,sql,m_ImpFormat.getAD_Table_ID());
        atts.addAttribute("","","ADTableNameID","CDATA",name);
    	}
    else
        atts.addAttribute("","","ADTableNameID","CDATA","");

	atts.addAttribute("","","Name","CDATA",(m_ImpFormat.getName () != null ? m_ImpFormat.getName ():""));
	atts.addAttribute("","","isActive","CDATA",(m_ImpFormat.isActive()== true ? "true":"false"));
	atts.addAttribute("","","isProcessing","CDATA",(m_ImpFormat.isProcessing()== true ? "true":"false"));
	atts.addAttribute("","","Description","CDATA",(m_ImpFormat.getDescription () != null ? m_ImpFormat.getDescription():""));
	atts.addAttribute("","","FormatType","CDATA",(m_ImpFormat.getFormatType () != null ? m_ImpFormat.getFormatType():""));
	return atts;
	}	
	
	public  static AttributesImpl createimprowBinding( AttributesImpl atts, X_AD_ImpFormat_Row m_ImpFormat_Row) 
	{	
	String sql = null;
	String name = null;
	atts.clear();
	if (m_ImpFormat_Row.getAD_ImpFormat_ID()> 0 ){
        sql = "SELECT Name FROM AD_ImpFormat WHERE AD_ImpFormat_ID=?";
        name = DB.getSQLValueString(null,sql,m_ImpFormat_Row.getAD_ImpFormat_ID());
        atts.addAttribute("","","ADImpFormatNameID","CDATA",name);
    	}
    else
        atts.addAttribute("","","ADImpFormatNameID","CDATA","");
	
	if (m_ImpFormat_Row.getAD_Column_ID()> 0 ){
    	sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
    	name = DB.getSQLValueString(null,sql,m_ImpFormat_Row.getAD_Column_ID());
    	atts.addAttribute("","","ADColumnNameID","CDATA",name);
    }
    else
    	atts.addAttribute("","","ADColumnNameID","CDATA","");
	
	if (m_ImpFormat_Row.getAD_Column_ID()> 0 ){
        sql = "SELECT AD_Table_ID FROM AD_Column WHERE AD_Column_ID=?";
        int idTable = DB.getSQLValue(null, sql,m_ImpFormat_Row.getAD_Column_ID());
        sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
        name = DB.getSQLValueString(null,sql,idTable);
        atts.addAttribute("","","ADTableNameID","CDATA",name);
    	}
    else
        atts.addAttribute("","","ADTableNameID","CDATA","");
	
	atts.addAttribute("","","Name","CDATA",(m_ImpFormat_Row.getName () != null ? m_ImpFormat_Row.getName ():""));
	atts.addAttribute("","","SeqNo","CDATA",""+m_ImpFormat_Row.getSeqNo());
	atts.addAttribute("","","StartNo","CDATA",""+m_ImpFormat_Row.getStartNo());
	atts.addAttribute("","","EndNo","CDATA",""+m_ImpFormat_Row.getEndNo());
	atts.addAttribute("","","DataType","CDATA",(m_ImpFormat_Row.getDataType () != null ? m_ImpFormat_Row.getDataType():""));
	atts.addAttribute("","","DataFormat","CDATA",(m_ImpFormat_Row.getDataFormat () != null ? m_ImpFormat_Row.getDataFormat():""));
	atts.addAttribute("","","DecimalPoint","CDATA",(m_ImpFormat_Row.getDecimalPoint () != null ? m_ImpFormat_Row.getDecimalPoint():""));
	atts.addAttribute("","","isDivideBy100","CDATA",(m_ImpFormat_Row.isDivideBy100 ()== true ? "true":"false"));
	atts.addAttribute("","","ConstantValue","CDATA",(m_ImpFormat_Row.getConstantValue() != null ? m_ImpFormat_Row.getConstantValue():""));
	atts.addAttribute("","","Callout","CDATA",(m_ImpFormat_Row.getCallout() != null ? m_ImpFormat_Row.getCallout():""));
	atts.addAttribute("","","Script","CDATA",(m_ImpFormat_Row.getScript() != null ? m_ImpFormat_Row.getScript():""));
	atts.addAttribute("","","isActive","CDATA",(m_ImpFormat_Row.isActive()== true ? "true":"false"));
	
	return atts;
	}	
	
	public  static AttributesImpl createroleBinding( AttributesImpl atts, X_AD_Role m_Role) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
        if (m_Role.getAD_Tree_Menu_ID()> 0 ){
                sql = "SELECT Name FROM AD_Tree WHERE AD_Tree_ID=? AND AD_Tree.TreeType='MM'";
                name = DB.getSQLValueString(null,sql,m_Role.getAD_Tree_Menu_ID());
                atts.addAttribute("","","treemenuname","CDATA",name);
            }
        else
        		atts.addAttribute("","","treemenuname","CDATA","");
        
        if (m_Role.getAD_Tree_Org_ID()> 0 ){
            sql = "SELECT Name FROM AD_Tree WHERE AD_Tree_ID=? AND AD_Tree.TreeType='OO'";
            name = DB.getSQLValueString(null,sql,m_Role.getAD_Tree_Org_ID());
            atts.addAttribute("","","treeorgname","CDATA",name);
        }
        else
    		atts.addAttribute("","","treeorgname","CDATA","");
        
        if (m_Role.getC_Currency_ID()> 0 ){
            sql = "SELECT ISO_Code FROM C_Currency WHERE C_Currency_ID=?";
            name = DB.getSQLValueString(null,sql,m_Role.getC_Currency_ID());
            atts.addAttribute("","","currencycode","CDATA",name);
        }
        else
    		atts.addAttribute("","","currencycode","CDATA","");
        
        if (m_Role.getSupervisor_ID()> 0 ){
            sql = "SELECT Name FROM AD_User WHERE AD_User_ID=?";
            name = DB.getSQLValueString(null,sql,m_Role.getC_Currency_ID());
            atts.addAttribute("","","supervisorid","CDATA",name);
        }
        else
    		atts.addAttribute("","","supervisorid","CDATA","");
        
        atts.addAttribute("","","Description","CDATA",(m_Role.getDescription () != null ? m_Role.getDescription ():""));        
        atts.addAttribute("","","Name","CDATA",(m_Role.getName () != null ? m_Role.getName ():""));
        atts.addAttribute("","","AmtApproval","CDATA",(""+m_Role.getAmtApproval()));
        atts.addAttribute("","","isAccessAllOrgs","CDATA",(m_Role.isAccessAllOrgs()== true ? "true":"false"));
        atts.addAttribute("","","isActive","CDATA",(m_Role.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isCanApproveOwnDoc","CDATA",(m_Role.isCanApproveOwnDoc()== true ? "true":"false"));
        atts.addAttribute("","","isCanExport","CDATA",(m_Role.isCanExport()== true ? "true":"false"));
        atts.addAttribute("","","isCanReport","CDATA",(m_Role.isCanReport()== true ? "true":"false"));
        atts.addAttribute("","","isChangeLog","CDATA",(m_Role.isChangeLog()== true ? "true":"false"));
        atts.addAttribute("","","isManual","CDATA",(m_Role.isManual()== true ? "true":"false"));
        atts.addAttribute("","","isPersonalAccess","CDATA",(m_Role.isPersonalAccess()== true ? "true":"false"));
        atts.addAttribute("","","isPersonalLock","CDATA",(m_Role.isPersonalLock()== true ? "true":"false"));
        atts.addAttribute("","","isShowAcct","CDATA",(m_Role.isShowAcct()== true ? "true":"false"));
        atts.addAttribute("","","isUseUserOrgAccess","CDATA",(m_Role.isUseUserOrgAccess()== true ? "true":"false"));
        atts.addAttribute("","","isOverwritePriceLimit","CDATA",(m_Role.isOverwritePriceLimit()== true ? "true":"false"));
        atts.addAttribute("","","PreferenceType","CDATA",(m_Role.getPreferenceType() != null ? m_Role.getPreferenceType ():""));
        atts.addAttribute("","","UserLevel","CDATA",(m_Role.getUserLevel() != null ? m_Role.getUserLevel ():""));
        
        return atts;
	}	
	public  static AttributesImpl createSQLStatmentBinding( AttributesImpl atts, String SqlStatement, String DBType) 
	{
		   atts.clear();
			atts.addAttribute("","","DBType","CDATA",DBType);
			atts.addAttribute("","","statement","CDATA",SqlStatement);
		   return atts;
	
	}
	
	public  static AttributesImpl createSnipitBinding( AttributesImpl atts, String FileDir, String FileName, String OldCode, String NewCode, String ReleaseNo) 
	{
		atts.clear();
		atts.addAttribute("","","filedir","CDATA",FileDir);
		atts.addAttribute("","","filename","CDATA",FileName);			
		String preOldCode = OldCode.toString();
		String preNewCode = NewCode.toString();
		String modOldCode = preOldCode.replaceAll("\\$","\\\\\\$").replaceAll("\\.","\\\\.")				
		.replaceAll("\\^","\\\\^").replaceAll("\\(","\\\\(").replaceAll("\\)","\\\\)")
		.replaceAll("\\[","\\\\[").replaceAll("\\/","\\\\/").replaceAll("\\+","\\\\+")
		.replaceAll("\\*","\\\\*").replaceAll("\\|","\\\\|");				
		String modNewCode = preNewCode.replaceAll("\\$","\\\\\\$").replaceAll("\\.","\\\\.")
		.replaceAll("\\^","\\\\^").replaceAll("\\(","\\\\(").replaceAll("\\)","\\\\)")
		.replaceAll("\\[","\\\\[").replaceAll("\\/","\\\\/").replaceAll("\\+","\\\\+")
		.replaceAll("\\*","\\\\*").replaceAll("\\|","\\\\|");							
		atts.addAttribute("","","oldcode","CDATA",modOldCode);
		atts.addAttribute("","","newcode","CDATA",modNewCode);
		atts.addAttribute("","","ReleaseNo","CDATA",ReleaseNo);
	   return atts;
	
	}
	public  static AttributesImpl createorgaccessBinding( AttributesImpl atts, int org_id, int role_id) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
         sql = "SELECT Name FROM AD_Org WHERE AD_Org_ID=?";
         name = DB.getSQLValueString(null,sql,org_id);
         atts.addAttribute("","","orgname","CDATA",name);
         
         sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
         name = DB.getSQLValueString(null,sql,role_id);
         atts.addAttribute("","","rolename","CDATA",name);
         
         sql = "SELECT isActive FROM AD_Role_OrgAccess WHERE AD_Org_ID="+ org_id +" and AD_Role_ID=?";
         String TrueFalse = DB.getSQLValueString(null,sql,role_id);
         atts.addAttribute("","","isActive","CDATA",TrueFalse);
     
         sql = "SELECT isReadOnly FROM AD_Role_OrgAccess WHERE AD_Org_ID="+ org_id +" and AD_Role_ID=?";
         String isReadWrite = DB.getSQLValueString(null,sql,role_id);
         atts.addAttribute("","","isReadOnly","CDATA",TrueFalse);
         
        return atts;
	}
	//TODO
	public  static AttributesImpl createusrassignBinding( AttributesImpl atts, int user_id, int role_id, int org_id) 
	{		
		String sql = null;
        String name = null;
        atts.clear();
        
         sql = "SELECT Name FROM AD_User WHERE AD_User_ID=?";
         name = DB.getSQLValueString(null,sql,user_id);
         atts.addAttribute("","","username","CDATA",name);
         
         sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
         name = DB.getSQLValueString(null,sql,role_id);
         atts.addAttribute("","","rolename","CDATA",name);
         
         sql = "SELECT isActive FROM AD_User_Roles WHERE AD_User_ID="+ user_id +" and AD_Role_ID=?";
         String TrueFalse = DB.getSQLValueString(null,sql,role_id);
         atts.addAttribute("","","isActive","CDATA",TrueFalse);
     
         sql = "SELECT Name FROM AD_Org WHERE AD_Org_ID=?";
         name = DB.getSQLValueString(null,sql,org_id);
         atts.addAttribute("","","orgname","CDATA",name);
        
        return atts;
	}
	
	public  static AttributesImpl createwindowaccessBinding( AttributesImpl atts, int window_id, int role_id) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
       
        sql = "SELECT Name FROM AD_Window WHERE AD_Window_ID=?";
        name = DB.getSQLValueString(null,sql,window_id);
        atts.addAttribute("","","windowname","CDATA",name);
        
        sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
        name = DB.getSQLValueString(null,sql,role_id);
        atts.addAttribute("","","rolename","CDATA",name);
        
        sql = "SELECT isActive FROM AD_Window_Access WHERE AD_Window_ID="+ window_id +" and AD_Role_ID=?";
        String TrueFalse = DB.getSQLValueString(null,sql,role_id);
        atts.addAttribute("","","isActive","CDATA",TrueFalse);
    
        sql = "SELECT isReadWrite FROM AD_Window_Access WHERE AD_Window_ID="+ window_id +" and AD_Role_ID=?";
        String isReadWrite = DB.getSQLValueString(null,sql,role_id);
        atts.addAttribute("","","isReadWrite","CDATA",TrueFalse);        
        
        return atts;
	}
	
	public  static AttributesImpl createprocessaccessBinding( AttributesImpl atts, int process_id, int role_id) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
        sql = "SELECT Name FROM AD_Process WHERE AD_Process_ID=?";
        name = DB.getSQLValueString(null,sql,process_id);
        atts.addAttribute("","","processname","CDATA",name);

        sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
        name = DB.getSQLValueString(null,sql,role_id);
        atts.addAttribute("","","rolename","CDATA",name);
        
        sql = "SELECT isActive FROM AD_Process_Access WHERE AD_Process_ID="+ process_id +" and AD_Role_ID=?";
        String TrueFalse = DB.getSQLValueString(null,sql,role_id);
        atts.addAttribute("","","isActive","CDATA",TrueFalse);
    
        sql = "SELECT isReadWrite FROM AD_Process_Access WHERE AD_Process_ID="+ process_id +" and AD_Role_ID=?";
        String isReadWrite = DB.getSQLValueString(null,sql,role_id);
        atts.addAttribute("","","isReadWrite","CDATA",TrueFalse);        
    
        return atts;
	}
	
	public  static AttributesImpl createformaccessBinding( AttributesImpl atts, int form_id, int role_id) 
	{
		String sql = null;
        String name = null;
        atts.clear();        
        
        sql = "SELECT Name FROM AD_Form WHERE AD_Form_ID=?";
        name = DB.getSQLValueString(null,sql,form_id);
        atts.addAttribute("","","formname","CDATA",name);
    
	    sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
	    name = DB.getSQLValueString(null,sql,role_id);
	    atts.addAttribute("","","rolename","CDATA",name);
		
		sql = "SELECT isActive FROM AD_Form_Access WHERE AD_Form_ID="+ form_id +" and AD_Role_ID=?";
		String TrueFalse = DB.getSQLValueString(null,sql,role_id);
		atts.addAttribute("","","isActive","CDATA",TrueFalse);
		
		sql = "SELECT isReadWrite FROM AD_Form_Access WHERE AD_Form_ID="+ form_id +" and AD_Role_ID=?";
		String isReadWrite = DB.getSQLValueString(null,sql,role_id);
		atts.addAttribute("","","isReadWrite","CDATA",TrueFalse);        
		
		return atts;
        
	}
	
	public  static AttributesImpl createworkflowaccessBinding( AttributesImpl atts, int workflow_id, int role_id) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
        sql = "SELECT Name FROM AD_Workflow WHERE AD_Workflow_ID=?";
        name = DB.getSQLValueString(null,sql,workflow_id);
        atts.addAttribute("","","workflowname","CDATA",name);
    
	    sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
	    name = DB.getSQLValueString(null,sql,role_id);
	    atts.addAttribute("","","rolename","CDATA",name);
		
		sql = "SELECT isActive FROM AD_Workflow_Access WHERE AD_Workflow_ID="+ workflow_id +" and AD_Role_ID=?";
		String TrueFalse = DB.getSQLValueString(null,sql,role_id);
		atts.addAttribute("","","isActive","CDATA",TrueFalse);
		
		sql = "SELECT isReadWrite FROM AD_Workflow_Access WHERE AD_Workflow_ID="+ workflow_id +" and AD_Role_ID=?";
		String isReadWrite = DB.getSQLValueString(null,sql,role_id);
		atts.addAttribute("","","isReadWrite","CDATA",TrueFalse);        
	        
        return atts;
	}

	public  static AttributesImpl createtaskaccessBinding( AttributesImpl atts, int task_id, int role_id) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
        sql = "SELECT Name FROM AD_Task WHERE AD_Task_ID=?";
        name = DB.getSQLValueString(null,sql,task_id);
        atts.addAttribute("","","taskname","CDATA",name);
    
	    sql = "SELECT Name FROM AD_Role WHERE AD_Role_ID=?";
	    name = DB.getSQLValueString(null,sql,role_id);
	    atts.addAttribute("","","rolename","CDATA",name);
		
		sql = "SELECT isActive FROM AD_Task_Access WHERE AD_Task_ID="+ task_id +" and AD_Role_ID=?";
		String TrueFalse = DB.getSQLValueString(null,sql,role_id);
		atts.addAttribute("","","isActive","CDATA",TrueFalse);
		
		sql = "SELECT isReadWrite FROM AD_Task_Access WHERE AD_Task_ID="+ task_id +" and AD_Role_ID=?";
		String isReadWrite = DB.getSQLValueString(null,sql,role_id);
		atts.addAttribute("","","isReadWrite","CDATA",TrueFalse);    
        return atts;
	}

	public  static AttributesImpl createreflistBinding( AttributesImpl atts, X_AD_Ref_List m_Ref_List) 
	{
		String sql = null;
        String name = null;
        atts.clear();            
        if (m_Ref_List.getAD_Ref_List_ID ()> 0 ){
                sql = "SELECT Name FROM AD_Ref_List WHERE AD_Ref_List_ID=?";
                name = DB.getSQLValueString(null,sql,m_Ref_List.getAD_Ref_List_ID ());
        }        
        if (name != null )
                atts.addAttribute("","","ADReflistNameID","CDATA",name);
        else
        	atts.addAttribute("","","ADReflistNameID","CDATA","");         
        if (m_Ref_List.getAD_Reference_ID()> 0 ){
            sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
            name = DB.getSQLValueString(null,sql,m_Ref_List.getAD_Reference_ID());
        }        
        if (name != null )
            atts.addAttribute("","","ADRefenceNameID","CDATA",name);
        else
    	atts.addAttribute("","","ADRefenceNameID","CDATA","");	
        atts.addAttribute("","","Description","CDATA",(m_Ref_List.getDescription () != null ? m_Ref_List.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Ref_List.getEntityType () != null ? m_Ref_List.getEntityType ():""));
        atts.addAttribute("","","Name","CDATA",(m_Ref_List.getName () != null ? m_Ref_List.getName ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Ref_List.isActive()== true ? "true":"false"));
        //atts.addAttribute("","","ValidFrom","CDATA",(m_Ref_List.getValidFrom ().toGMTString() != null ? m_Ref_List.getValidFrom().toGMTString():""));
        //atts.addAttribute("","","ValidTo","CDATA",(m_Ref_List.getValidTo ().toGMTString() != null ? m_Ref_List.getValidTo().toGMTString():""));
        atts.addAttribute("","","Value","CDATA",(m_Ref_List.getValue () != null ? m_Ref_List.getValue ():""));
        return atts;
	}
	
	public AttributesImpl createreferencetableBinding( AttributesImpl atts, int reference_ID) 
	{
		atts.clear();		
		String name = null;
		String sql = null;
		String sql1 = "SELECT * FROM AD_Ref_Table WHERE AD_Reference_ID= " + reference_ID;
		
		PreparedStatement pstmt = null;
		pstmt = DB.prepareStatement (sql1, get_TrxName());
		try {
			ResultSet rs = pstmt.executeQuery();			
			while (rs.next()){								
				
				if (reference_ID > 0 ){
		            sql = "SELECT Name FROM AD_Reference WHERE AD_Reference_ID=?";
		            name = DB.getSQLValueString(null,sql,reference_ID);		        
		            atts.addAttribute("","","ADRefenceNameID","CDATA",name);		            
		            }
		        else
		         	atts.addAttribute("","","ADRefenceNameID","CDATA","");
				
				if (rs.getInt("AD_Table_ID")> 0 ){
		            sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
		            name = DB.getSQLValueString(null,sql,rs.getInt("AD_Table_ID"));
		            atts.addAttribute("","","ADTableNameID","CDATA",name);
		            }		            
		        else
		         	atts.addAttribute("","","ADTableNameID","CDATA","");
			
		        if (rs.getInt("AD_Display") > 0 ){
		            sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
		            name = DB.getSQLValueString(null,sql,rs.getInt("AD_Display"));		        
		            atts.addAttribute("","","ADDisplay","CDATA",name);}
		        else
		        	atts.addAttribute("","","ADDisplay","CDATA","");
		        
		        if (rs.getInt("AD_Key")> 0 ){
		            sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
		            name = DB.getSQLValueString(null,sql,rs.getInt("AD_Key"));		        
		            atts.addAttribute("","","Key","CDATA",name);
		            }
		        else
		        	atts.addAttribute("","","Key","CDATA","");
		        atts.addAttribute("","","EntityType","CDATA",(rs.getString("EntityType") != null ? rs.getString("EntityType"):""));		        
		        atts.addAttribute("","","IsValueDisplayed","CDATA",(rs.getString("IsValueDisplayed").compareTo("Y")==0 ? "Y":"N"));
		        atts.addAttribute("","","OrderByClause","CDATA",(rs.getString("OrderByClause") != null ? rs.getString("OrderByClause"):""));
		        atts.addAttribute("","","isActive","CDATA",(rs.getString("isActive").compareTo("Y")==0 ? "Y":"N"));
		        atts.addAttribute("","","WhereClause","CDATA",(rs.getString("WhereClause") != null ? rs.getString("WhereClause"):""));
		        
			}
			rs.close();
			pstmt.close();
			pstmt = null;
		}
		catch (Exception e)
		{
			
		}
		finally
		{
			try
			{
				if (pstmt != null)
					pstmt.close ();
			}
			catch (Exception e)
			{}
			pstmt = null;
		}
		return atts;		
	}
	

	public  static AttributesImpl createPrintformatBinding( AttributesImpl atts, X_AD_PrintFormat m_Printformat) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_Printformat.getAD_ReportView_ID()> 0 ){
            sql = "SELECT Name FROM AD_ReportView WHERE AD_ReportView_ID=?";
            name = DB.getSQLValueString(null,sql,m_Printformat.getAD_ReportView_ID());       
            atts.addAttribute("","","ADReportviewnameID","CDATA",name);}            
        else
        	atts.addAttribute("","","ADReportviewnameID","CDATA","");
        
        if (m_Printformat.getAD_Table_ID()> 0 ){
            sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
            name = DB.getSQLValueString(null,sql,m_Printformat.getAD_Table_ID());
            atts.addAttribute("","","ADTableNameID","CDATA",name);}
         else
         	atts.addAttribute("","","ADTableNameID","CDATA","");
         	
         if (m_Printformat.getAD_PrintTableFormat_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintTableFormat WHERE AD_PrintTableFormat_ID=?";
            name = DB.getSQLValueString(null,sql,m_Printformat.getAD_PrintTableFormat_ID());
            atts.addAttribute("","","ADPrintTableFormatID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintTableFormatID","CDATA","");
         	
        if (m_Printformat.getAD_PrintColor_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintColor WHERE AD_PrintColor_ID=?";
            name = DB.getSQLValueString(null,sql,m_Printformat.getAD_PrintColor_ID());
            atts.addAttribute("","","ADPrintColorID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintColorID","CDATA","");
         	
        if (m_Printformat.getAD_PrintFont_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintFont WHERE AD_PrintFont_ID=?";
            name = DB.getSQLValueString(null,sql,m_Printformat.getAD_PrintFont_ID());
            atts.addAttribute("","","ADPrintFontID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintFontID","CDATA","");

		if (m_Printformat.getAD_PrintPaper_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintPaper WHERE AD_PrintPaper_ID=?";
            name = DB.getSQLValueString(null,sql,m_Printformat.getAD_PrintPaper_ID());
            atts.addAttribute("","","ADPrintPaperID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintPaperID","CDATA","");
        
        atts.addAttribute("","","Description","CDATA",(m_Printformat.getDescription () != null ? m_Printformat.getDescription ():""));
        atts.addAttribute("","","Name","CDATA",(m_Printformat.getName () != null ? m_Printformat.getName ():""));
        atts.addAttribute("","","PrinterName","CDATA",(m_Printformat.getPrinterName() != null ? m_Printformat.getPrinterName ():""));
        atts.addAttribute("","","FooterMargin","CDATA",""+m_Printformat.getFooterMargin());
        atts.addAttribute("","","HeaderMargin","CDATA",""+m_Printformat.getHeaderMargin());
        atts.addAttribute("","","CreateCopy","CDATA",(m_Printformat.getCreateCopy() != null ? m_Printformat.getCreateCopy ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Printformat.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isTableBased","CDATA",(m_Printformat.isTableBased()== true ? "true":"false"));
        atts.addAttribute("","","isForm","CDATA",(m_Printformat.isForm()== true ? "true":"false"));
        atts.addAttribute("","","isStandardHeader","CDATA",(m_Printformat.isStandardHeaderFooter()== true ? "true":"false"));       
        atts.addAttribute("","","isDefault","CDATA",(m_Printformat.isDefault()== true ? "true":"false"));
        return atts;
        }

public  static AttributesImpl createPrintformatItemBinding( AttributesImpl atts, X_AD_PrintFormatItem m_PrintformatItem) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        if (m_PrintformatItem.getAD_PrintFormat_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintFormat WHERE AD_PrintFormat_ID=?";
            name = DB.getSQLValueString(null,sql,m_PrintformatItem.getAD_PrintFormat_ID());
            atts.addAttribute("","","ADPrintFormatNameID","CDATA",name);}
        else
        	atts.addAttribute("","","ADPrintFormatNameID","CDATA","");
        	
        if (m_PrintformatItem.getAD_PrintFormatChild_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintFormat WHERE AD_PrintFormat_ID=?";
            name = DB.getSQLValueString(null,sql,m_PrintformatItem.getAD_PrintFormatChild_ID());
            atts.addAttribute("","","ADPrintFormatChildNameID","CDATA",name);}
        else
        	atts.addAttribute("","","ADPrintFormatChildNameID","CDATA","");
        
        if (m_PrintformatItem.getAD_Column_ID()> 0 ){
            sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
            name = DB.getSQLValueString(null,sql,m_PrintformatItem.getAD_Column_ID());
            atts.addAttribute("","","ADColumnNameID","CDATA",name);}
         else
         	atts.addAttribute("","","ADColumnNameID","CDATA","");
        
        if (m_PrintformatItem.getAD_Column_ID()> 0 ){
            sql = "SELECT AD_Table_ID FROM AD_Column WHERE AD_Column_ID=?";
            int tableID = DB.getSQLValue(null, sql,m_PrintformatItem.getAD_Column_ID());
            sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
            name = DB.getSQLValueString(null, sql,tableID);
            atts.addAttribute("","","ADTableNameID","CDATA",name);}
         else
         	atts.addAttribute("","","ADTableNameID","CDATA","");


        
         if (m_PrintformatItem.getAD_PrintGraph_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintGraph WHERE AD_PrintGraph_ID=?";
            name = DB.getSQLValueString(null,sql,m_PrintformatItem.getAD_PrintGraph_ID());
            atts.addAttribute("","","ADPrintGraphID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintGraphID","CDATA","");
         	
        if (m_PrintformatItem.getAD_PrintColor_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintColor WHERE AD_PrintColor_ID=?";
            name = DB.getSQLValueString(null,sql,m_PrintformatItem.getAD_PrintColor_ID());
            atts.addAttribute("","","ADPrintColorID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintColorID","CDATA","");
         	
        if (m_PrintformatItem.getAD_PrintFont_ID()> 0 ){
            sql = "SELECT Name FROM AD_PrintFont WHERE AD_PrintFont_ID=?";
            name = DB.getSQLValueString(null,sql,m_PrintformatItem.getAD_PrintFont_ID());
            atts.addAttribute("","","ADPrintFontID","CDATA",name);}
         else
         	atts.addAttribute("","","ADPrintFontID","CDATA","");		
        
        atts.addAttribute("","","PrintName","CDATA",(m_PrintformatItem.getPrintName () != null ? m_PrintformatItem.getPrintName ():""));
        atts.addAttribute("","","Name","CDATA",(m_PrintformatItem.getName () != null ? m_PrintformatItem.getName ():""));        
        atts.addAttribute("","","PrintAreaType","CDATA",(m_PrintformatItem.getPrintAreaType() != null ? m_PrintformatItem.getPrintAreaType ():""));        
        atts.addAttribute("","","SeqNo","CDATA",""+m_PrintformatItem.getSeqNo());
        atts.addAttribute("","","PrintFormatType","CDATA",m_PrintformatItem.getPrintFormatType());        
        atts.addAttribute("","","XSpace","CDATA",(""+m_PrintformatItem.getXSpace()));
        atts.addAttribute("","","YSpace","CDATA",(""+m_PrintformatItem.getYSpace()));
        atts.addAttribute("","","Xposition","CDATA",(""+m_PrintformatItem.getXPosition()));
        atts.addAttribute("","","Yposition","CDATA",(""+m_PrintformatItem.getYPosition()));
        atts.addAttribute("","","MaxWidth","CDATA",(""+m_PrintformatItem.getMaxWidth()));
        atts.addAttribute("","","MaxHieght","CDATA",(""+m_PrintformatItem.getMaxHeight()));        
        atts.addAttribute("","","SortNo","CDATA",(""+m_PrintformatItem.getSortNo()));
        atts.addAttribute("","","FieldAlignmentType","CDATA",(m_PrintformatItem.getFieldAlignmentType() != null ? m_PrintformatItem.getFieldAlignmentType ():""));
        atts.addAttribute("","","LineAlignmentType","CDATA",(m_PrintformatItem.getLineAlignmentType() != null ? m_PrintformatItem.getLineAlignmentType ():""));
        atts.addAttribute("","","ImageURL","CDATA",(m_PrintformatItem.getImageURL() != null ? m_PrintformatItem.getImageURL ():""));
        atts.addAttribute("","","BelowColumn","CDATA",(""+m_PrintformatItem.getBelowColumn()));
        atts.addAttribute("","","RunningTotalLines","CDATA",(""+m_PrintformatItem.getRunningTotalLines()));
        atts.addAttribute("","","PrintNameSuffix","CDATA",(m_PrintformatItem.getPrintNameSuffix() != null ? m_PrintformatItem.getPrintNameSuffix ():""));                
        atts.addAttribute("","","ArcDiameter","CDATA",""+m_PrintformatItem.getArcDiameter());
        atts.addAttribute("","","LineWidth","CDATA",""+m_PrintformatItem.getLineWidth());
        atts.addAttribute("","","ShapeType","CDATA",m_PrintformatItem.getShapeType() != null ? m_PrintformatItem.getShapeType():"");
        atts.addAttribute("","","isActive","CDATA",(m_PrintformatItem.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isPrinted","CDATA",(m_PrintformatItem.isPrinted()== true ? "true":"false"));
        atts.addAttribute("","","isRelativePosition","CDATA",(m_PrintformatItem.isRelativePosition()== true ? "true":"false"));        
        atts.addAttribute("","","isNextLine","CDATA",(m_PrintformatItem.isNextLine()== true ? "true":"false"));       
        atts.addAttribute("","","isHeightOneLine","CDATA",(m_PrintformatItem.isHeightOneLine()== true ? "true":"false"));        
        atts.addAttribute("","","isOrderBy","CDATA",(m_PrintformatItem.isOrderBy()== true ? "true":"false"));
        atts.addAttribute("","","isGroupBy","CDATA",(m_PrintformatItem.isGroupBy()== true ? "true":"false"));
        atts.addAttribute("","","isPageBreak","CDATA",(m_PrintformatItem.isPageBreak()== true ? "true":"false"));
        atts.addAttribute("","","isSummarized","CDATA",(m_PrintformatItem.isSummarized()== true ? "true":"false"));
        atts.addAttribute("","","isImageIsAttached","CDATA",(m_PrintformatItem.isImageIsAttached()== true ? "true":"false"));
        atts.addAttribute("","","isAveraged","CDATA",(m_PrintformatItem.isAveraged()== true ? "true":"false"));
        atts.addAttribute("","","isCounted","CDATA",(m_PrintformatItem.isCounted()== true ? "true":"false"));
        atts.addAttribute("","","isSetNLPosition","CDATA",(m_PrintformatItem.isSetNLPosition()== true ? "true":"false"));
        atts.addAttribute("","","isSuppressNull","CDATA",(m_PrintformatItem.isSuppressNull()== true ? "true":"false"));
        atts.addAttribute("","","isFixedWidth","CDATA",(m_PrintformatItem.isFixedWidth()== true ? "true":"false"));
        atts.addAttribute("","","isNextPage","CDATA",(m_PrintformatItem.isNextPage()== true ? "true":"false"));
        atts.addAttribute("","","isMaxCalc","CDATA",(m_PrintformatItem.isMaxCalc()== true ? "true":"false"));
        atts.addAttribute("","","isMinCalc","CDATA",(m_PrintformatItem.isMinCalc()== true ? "true":"false"));
        atts.addAttribute("","","isRunningTotal","CDATA",(m_PrintformatItem.isRunningTotal()== true ? "true":"false"));
        atts.addAttribute("","","isVarianceCalc","CDATA",(m_PrintformatItem.isVarianceCalc()== true ? "true":"false"));
        atts.addAttribute("","","isDeviationCalc","CDATA",(m_PrintformatItem.isDeviationCalc()== true ? "true":"false"));
        
        return atts;
	}

	
	public  static AttributesImpl createreportviewBinding( AttributesImpl atts, X_AD_ReportView m_Reportview) 
	{
		String sql = null;
        String name = null;
        atts.clear();
        
        if (m_Reportview.getAD_ReportView_ID()> 0 ){
            sql = "SELECT Name FROM AD_ReportView WHERE AD_ReportView_ID=?";
            name = DB.getSQLValueString(null,sql,m_Reportview.getAD_ReportView_ID());        
            atts.addAttribute("","","ADReportviewnameID","CDATA",name);}
        else
        	atts.addAttribute("","","ADReportviewnameID","CDATA","");
        
        if (m_Reportview.getAD_Table_ID()> 0 ){
            sql = "SELECT TableName FROM AD_Table WHERE AD_Table_ID=?";
            name = DB.getSQLValueString(null,sql,m_Reportview.getAD_Table_ID());
            atts.addAttribute("","","ADTableNameID","CDATA",name);}
         else
         	atts.addAttribute("","","ADTableNameID","CDATA","");
        
        atts.addAttribute("","","Description","CDATA",(m_Reportview.getDescription () != null ? m_Reportview.getDescription ():""));
        atts.addAttribute("","","EntityType","CDATA",(m_Reportview.getEntityType () != null ? m_Reportview.getEntityType ():""));
        atts.addAttribute("","","Name","CDATA",(m_Reportview.getName () != null ? m_Reportview.getName ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Reportview.isActive()== true ? "true":"false"));
        atts.addAttribute("","","OrderByClause","CDATA",(m_Reportview.getOrderByClause () != null ? m_Reportview.getOrderByClause ():""));               
        atts.addAttribute("","","WhereClause","CDATA",(m_Reportview.getWhereClause () != null ? m_Reportview.getWhereClause ():""));
        return atts;
	}

	public  static AttributesImpl createreportviewcolBinding( AttributesImpl atts, X_AD_ReportView_Col m_Reportview_Col) 
	{
		String sql = null;
        String name = null;
        atts.clear();            
        if (m_Reportview_Col.getAD_Column_ID()> 0 ){
            sql = "SELECT ColumnName FROM AD_Column WHERE AD_Column_ID=?";
            name = DB.getSQLValueString(null,sql,m_Reportview_Col.getAD_Column_ID());        
            atts.addAttribute("","","ADColumnNameID","CDATA",name);}
        else
        	atts.addAttribute("","","ADColumnNameID","CDATA","");
        
        if (m_Reportview_Col.getAD_ReportView_ID()> 0 ){
            sql = "SELECT Name FROM AD_Reference WHERE AD_Reportview_ID=?";
            name = DB.getSQLValueString(null,sql,m_Reportview_Col.getAD_ReportView_ID());
            atts.addAttribute("","","ADReportviewnameID","CDATA",name);}
        else
        	atts.addAttribute("","","ADColumnNameID","CDATA","");
        
        if (m_Reportview_Col.getAD_ReportView_Col_ID()> 0 ){
            sql = "SELECT Name FROM AD_Reference WHERE AD_ReportView_Col_ID=?";
            name = DB.getSQLValueString(null,sql,m_Reportview_Col.getAD_ReportView_Col_ID());
            atts.addAttribute("","","ADReportViewColID","CDATA",name);}
        else
        	atts.addAttribute("","","ADColumnNameID","CDATA","");
        
        atts.addAttribute("","","FunctionColumn","CDATA",(m_Reportview_Col.getFunctionColumn () != null ? m_Reportview_Col.getFunctionColumn ():""));
        atts.addAttribute("","","isActive","CDATA",(m_Reportview_Col.isActive()== true ? "true":"false"));
        atts.addAttribute("","","isGroupFunction","CDATA",(m_Reportview_Col.isGroupFunction()== true ? "true":"false"));
        return atts;
	}
	
	public void CopyFile (String sourceName, String copyName ) {
        InputStream source;  // Stream for reading from the source file.
        OutputStream copy;   // Stream for writing the copy.
        boolean force;  // This is set to true if the "-f" option
                        //    is specified on the command line.
        int byteCount;  // Number of bytes copied from the source file.
        
        force = true;           
        try {
           source = new FileInputStream(sourceName);
        }
        catch (FileNotFoundException e) {
           System.out.println("Can't find file \"" + sourceName + "\".");
           return;
        }
        File file = new File(copyName);
        if (file.exists() && force == false) {
            System.out.println(
                 "Output file exists.  Use the -f option to replace it.");
            return;  
        }	
        try {           
           copy = new FileOutputStream(copyName, false);
        }
        catch (IOException e) {
           System.out.println("Can't open output file \"" 
                                                   + copyName + "\".");
           return;
        }
        byteCount = 0;
        try {
           while (true) {
              int data = source.read();
              if (data < 0)
                 break;
              copy.write(data);
              byteCount++;
           }
           source.close();
           copy.close();
           System.out.println("Successfully copied " + byteCount + " bytes.");
        }
        catch (Exception e) {
           System.out.println("Error occurred while copying.  "+ byteCount + " bytes copied.");
           System.out.println(e.toString());
        }
}
}	//	PackOut
