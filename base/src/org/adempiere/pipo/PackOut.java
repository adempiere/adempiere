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
 * Copyright (C)                                                              *
 * 2004 Robert KLEIN. robeklein@hotmail.com                                   * 
 * Contributor(s): Low Heng Sin hengsin@avantz.com                            *
 *                 Teo Sarca teo.sarca@arhipac.ro, SC ARHIPAC SERVICE SRL     *
 *****************************************************************************/
package org.adempiere.pipo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pipo.handler.ASPModuleElementHandler;
import org.adempiere.pipo.handler.AdElementHandler;
import org.adempiere.pipo.handler.BrowseCustomElementHandler;
import org.adempiere.pipo.handler.BrowseElementHandler;
import org.adempiere.pipo.handler.CodeSnipitElementHandler;
import org.adempiere.pipo.handler.DataElementHandler;
import org.adempiere.pipo.handler.DistFileElementHandler;
import org.adempiere.pipo.handler.DynValRuleElementHandler;
import org.adempiere.pipo.handler.EntityTypeElementHandler;
import org.adempiere.pipo.handler.FormElementHandler;
import org.adempiere.pipo.handler.GenericPOHandler;
import org.adempiere.pipo.handler.ImpFormatElementHandler;
import org.adempiere.pipo.handler.MenuElementHandler;
import org.adempiere.pipo.handler.MessageElementHandler;
import org.adempiere.pipo.handler.PrintFormatElementHandler;
import org.adempiere.pipo.handler.ProcessCustomElementHandler;
import org.adempiere.pipo.handler.ProcessElementHandler;
import org.adempiere.pipo.handler.ReferenceElementHandler;
import org.adempiere.pipo.handler.ReportViewElementHandler;
import org.adempiere.pipo.handler.RoleElementHandler;
import org.adempiere.pipo.handler.SQLStatementElementHandler;
import org.adempiere.pipo.handler.TableElementHandler;
import org.adempiere.pipo.handler.TaskElementHandler;
import org.adempiere.pipo.handler.ViewElementHandler;
import org.adempiere.pipo.handler.WindowCustomElementHandler;
import org.adempiere.pipo.handler.WindowElementHandler;
import org.adempiere.pipo.handler.WorkflowElementHandler;
import org.adempiere.util.ProcessUtil;
import org.compiere.model.I_AD_BrowseCustom;
import org.compiere.model.I_AD_ModelValidator;
import org.compiere.model.I_AD_Package_Exp;
import org.compiere.model.I_AD_Package_Exp_Detail;
import org.compiere.model.I_AD_ProcessCustom;
import org.compiere.model.I_AD_WindowCustom;
import org.compiere.model.I_ASP_Module;
import org.compiere.model.MPackageExp;
import org.compiere.model.MPackageExpDetail;
import org.compiere.model.MTable;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Element;
import org.compiere.model.X_AD_Package_Exp_Detail;
import org.compiere.model.X_AD_Reference;
import org.compiere.process.ProcessInfo;
import org.compiere.util.Env;
import org.spin.model.MADPackageExpCustom;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 *	Convert AD to XML
 *	
 *  @author Robert Klein
 *  @version $Id: PackOut.java,v 1.0
 *  
 * Contributor: William G. Heath - Export of workflows and dynamic validations
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1819315 ] PackOut: fix xml indentation not working
 * 			<li>BF [ 1819319 ] PackOut: use just active AD_Package_Exp_Detail lines
 * 			<li>--
 * 			<li>FR [ 2847727 ] 2pack export all messages for a entity type functionality
 * 				https://sourceforge.net/tracker/?func=detail&atid=879335&aid=2847727&group_id=176962
 *	@author Dixon Martinez, dmartinez@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>BR [1019] New Icon to export report definition is show only swing but not ZK https://github.com/adempiere/adempiere/issues/1019
 */

public class PackOut extends PackOutAbstract {
	private String PackOutVer = "005";
    private String packagedir = null;
    private String packagename = null;
    private String includesdir = null;

    public final static int MAX_OFFICIAL_ID = 999999;

    private Properties localContext = null;
    
    ProcessElementHandler processHandler = new ProcessElementHandler();
    TaskElementHandler taskHandler = new TaskElementHandler();
    FormElementHandler formHandler = new FormElementHandler();
    WindowElementHandler windowHandler = new WindowElementHandler();
    ViewElementHandler viewHandler = new ViewElementHandler();
    BrowseElementHandler browseHandler = new BrowseElementHandler();
    MenuElementHandler menuHandler = new MenuElementHandler();
    ReportViewElementHandler reportViewHandler = new ReportViewElementHandler();
    DataElementHandler dataHandler = new DataElementHandler();
    TableElementHandler tableHandler = new TableElementHandler();
    RoleElementHandler roleHandler = new RoleElementHandler();
    SQLStatementElementHandler sqlHandler = new SQLStatementElementHandler();
    ImpFormatElementHandler impFormtHandler = new ImpFormatElementHandler();
    CodeSnipitElementHandler codeHandler = new CodeSnipitElementHandler();
    WorkflowElementHandler workflowHandler = new WorkflowElementHandler();
    DynValRuleElementHandler dynValRuleHandler = new DynValRuleElementHandler();
    MessageElementHandler messageHandler = new MessageElementHandler();
    PrintFormatElementHandler printFormatHandler = new PrintFormatElementHandler();
    DistFileElementHandler distFileHandler = new DistFileElementHandler();
    ReferenceElementHandler referenceHandler = new ReferenceElementHandler();
    AdElementHandler adElementHandler = new AdElementHandler();
    EntityTypeElementHandler entitytypeHandler = new EntityTypeElementHandler();
    ASPModuleElementHandler aspModuleHandler = new ASPModuleElementHandler();
    WindowCustomElementHandler windowCustomizationHandler = new WindowCustomElementHandler();
    ProcessCustomElementHandler processCustomizationHandler = new ProcessCustomElementHandler();
    BrowseCustomElementHandler browseCustomizationHandler = new BrowseCustomElementHandler();
    GenericPOHandler genericPOHandler = new GenericPOHandler();
    
	
	/**
	 * 	Start the transformation to XML
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		initContext();
		
		OutputStream  packageDocStream = null;
		OutputStream  packOutDocStream = null;
		log.info("doIt - AD_PACKAGE_EXP_ID=" + getRecord_ID());
		if (getRecord_ID() == 0)
			throw new IllegalArgumentException("No Record");	//	
		try {
			MPackageExp exportPackage = new MPackageExp(getCtx(), getRecord_ID(), get_TrxName());
			//Create the package documentation
			packagedir = exportPackage.getFile_Directory().trim();			
			if (!packagedir.endsWith("/") && !packagedir.endsWith("\\"))
				packagedir += File.separator;
			packagename = packagedir + exportPackage.getName();
			includesdir = exportPackage.getName() + File.separator +"**";
			(new File(packagename+File.separator+"doc"+File.separator)).mkdirs();
			String file_document = packagename+File.separator+"doc"+File.separator+exportPackage.getName() + "Doc.xml";		
			packageDocStream = new FileOutputStream (file_document, false);		
			StreamResult streamResult_document = new StreamResult(new OutputStreamWriter(packageDocStream,"ISO-8859-1"));	
			SAXTransformerFactory tf_document = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			tf_document.setAttribute("indent-number", Integer.valueOf(4));
			TransformerHandler packageDocument = tf_document.newTransformerHandler();		
			Transformer serializer_document = packageDocument.getTransformer();		
			serializer_document.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
			serializer_document.setOutputProperty(OutputKeys.INDENT,"yes");		
			packageDocument.setResult(streamResult_document);
			packageDocument.startDocument();
			AttributesImpl atts = new AttributesImpl();
			atts.clear();			
			packageDocument.processingInstruction("xml-stylesheet","type=\"text/css\" href=\"adempiereDocument.css\"");
			packageDocument.startElement("","","adempiereDocument",atts);
			packageDocument.startElement("","","header",atts);		
			packageDocument.characters((exportPackage.getName()+" Package Description").toCharArray(),0,(exportPackage.getName()+" Package Description").length());
			packageDocument.endElement("","","header");
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Package Name:" ).toCharArray(),0,("Package Name:" ).length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","packagename",atts);		
			packageDocument.characters(exportPackage.getName().toCharArray(),0,exportPackage.getName().length());
			packageDocument.endElement("","","packagename");					
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Creator:" ).toCharArray(),0,("Creator:").length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","creator",atts);
			packageDocument.characters(exportPackage.getUserName().toCharArray(),0,exportPackage.getUserName().length());
			packageDocument.endElement("","","creator");					
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Email Address:" ).toCharArray(),0,("Email Address:" ).length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","creatorcontact",atts);
			packageDocument.characters(exportPackage.getEMail().toCharArray(),0,exportPackage.getEMail().length());
			packageDocument.endElement("","","creatorcontact");					
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Created:" ).toCharArray(),0,("Created:" ).length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","createddate",atts);
			packageDocument.characters(exportPackage.getCreated().toString().toCharArray(),0,exportPackage.getCreated().toString().length());
			packageDocument.endElement("","","createddate");					
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Updated:" ).toCharArray(),0,("Updated:" ).length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","updateddate",atts);
			packageDocument.characters(exportPackage.getUpdated().toString().toCharArray(),0,exportPackage.getUpdated().toString().length());
			packageDocument.endElement("","","updateddate");				
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Description:" ).toCharArray(),0,("Description:" ).length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","description",atts);
			packageDocument.characters(exportPackage.getDescription().toCharArray(),0,exportPackage.getDescription().length());
			packageDocument.endElement("","","description");					
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Instructions:" ).toCharArray(),0,("Instructions:" ).length());
			packageDocument.endElement("","","H1");
			packageDocument.startElement("","","instructions",atts);
			packageDocument.characters(exportPackage.getInstructions().toCharArray(),0,exportPackage.getInstructions().length());
			packageDocument.endElement("","","instructions");
			packageDocument.startElement("","","H1",atts);		
			packageDocument.characters(("Files in Package:" ).toCharArray(),0,("Files in Package:" ).length());
			packageDocument.endElement("","","H1");		
			packageDocument.startElement("","","file",atts);
			packageDocument.characters(("File: PackOut.xml").toCharArray(),0,("File: PackOut.xml").length());
			packageDocument.endElement("","","file");		
			packageDocument.startElement("","","filedirectory",atts);
			packageDocument.characters("Directory: \\dict\\".toCharArray(),0,("Directory: \\dict\\").length());
			packageDocument.endElement("","","filedirectory");		
			packageDocument.startElement("","","filenotes",atts);
			packageDocument.characters("Notes: Contains all application/object settings for package".toCharArray(),0,"Notes: Contains all application/object settings for package".length());
			packageDocument.endElement("","","filenotes");			
			(new File(packagename+File.separator+ "dict"+File.separator)).mkdirs();		
			String file_menu = packagename+File.separator+ "dict"+File.separator+"PackOut.xml";		
			packOutDocStream = new FileOutputStream (file_menu, false);
			StreamResult streamResult_menu = new StreamResult(new OutputStreamWriter(packOutDocStream,"ISO-8859-1"));	
			SAXTransformerFactory tf_menu = (SAXTransformerFactory) SAXTransformerFactory.newInstance();					 
			tf_menu.setAttribute("indent-number", Integer.valueOf(4));
			TransformerHandler packOutDocument = tf_menu.newTransformerHandler();		
			Transformer serializer_menu = packOutDocument.getTransformer();		
			serializer_menu.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1");		
			serializer_menu.setOutputProperty(OutputKeys.INDENT,"yes");		
			packOutDocument.setResult(streamResult_menu);
			packOutDocument.startDocument();
			atts.clear();
			atts.addAttribute("","","Name","CDATA",exportPackage.getName());
			atts.addAttribute("","","Version","CDATA",exportPackage.getPK_Version());
			atts.addAttribute("","","CompVer","CDATA",exportPackage.getReleaseNo());
			atts.addAttribute("","","DataBase","CDATA",exportPackage.getVersion());
			atts.addAttribute("","","Description","CDATA",exportPackage.getDescription());
			atts.addAttribute("","","creator","CDATA",exportPackage.getUserName());
			atts.addAttribute("","","creatorcontact","CDATA",exportPackage.getEMail());
			atts.addAttribute("","","createddate","CDATA",exportPackage.getCreated().toString());
			atts.addAttribute("","","updateddate","CDATA",exportPackage.getUpdated().toString());
			atts.addAttribute("","","PackOutVer","CDATA",PackOutVer);		
			
			packOutDocument.startElement("","","adempiereAD",atts);		
			atts.clear();
			
			List<MPackageExpDetail> packageDetailList = new Query(getCtx(), I_AD_Package_Exp_Detail.Table_Name, 
					I_AD_Package_Exp.COLUMNNAME_AD_Package_Exp_ID + " = ?", get_TrxName())
					.setParameters(getRecord_ID())
					.setOnlyActiveRecords(true)
					.setOrderBy(I_AD_Package_Exp_Detail.COLUMNNAME_Line)
					.<MPackageExpDetail>list();
			//	Iterate
			for(MPackageExpDetail detail : packageDetailList) {
				String type = detail.getType();
				Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_EntityType_ID, detail.getAD_EntityType_ID());
				//
				log.info("Line = " + detail.getLine());
				if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_ApplicationOrModule) == 0){
					createMenu(detail.getAD_Menu_ID(), packOutDocument );
				}
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_ProcessReport) == 0)
					createProcess (detail.getAD_Process_ID(), packOutDocument );
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_ReportView) == 0)
					createReportview (detail.getAD_ReportView_ID(), packOutDocument );
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Data) == 0)
					createData (detail.getAD_Table_ID(), detail.getSQLStatement(), packOutDocument );
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Table) == 0)
					createTable (detail.getAD_Table_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Form) == 0)
					createForm (detail.getAD_Form_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Window) == 0)
					createWindow (detail.getAD_Window_ID(), packOutDocument);	
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_SmartBrowse) == 0)
					createBrowse (detail.getAD_Browse_ID(), packOutDocument);
				else if(type.equals(X_AD_Package_Exp_Detail.TYPE_SmartView))
					createView(detail.getAD_ReportView_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Role) == 0)
					createRoles (detail.getAD_Role_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_SQLStatement) == 0)
					createSQL (detail.getSQLStatement(), detail.getDBType(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_ImportFormat) == 0)
					createImpFormat (detail.getAD_ImpFormat_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Reference) == 0)
					createReference (detail.getAD_Reference_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_CodeSnipit) == 0)						
					createSnipit(
							detail.getDestination_Directory(),
							detail.getDestination_FileName(),
							detail.getAD_Package_Code_Old(),
							detail.getAD_Package_Code_New(),
							detail.getReleaseNo(),
							packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Workflow) == 0)
					createWorkflow (detail.getAD_Workflow_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_DynamicValidationRule) == 0)
					createDynamicRuleValidation(detail.getAD_Val_Rule_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_Message) == 0)
					createMessage(detail.getAD_Message_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_PrintFormat) == 0)
					createPrintFormat(detail.getAD_PrintFormat_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_ModelValidator) == 0)
					createModelValidator(detail.getAD_ModelValidator_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_EntityType) == 0)
					createEntityType(detail.getAD_EntityType_ID(), packOutDocument);
				else if(type.compareTo(X_AD_Package_Exp_Detail.TYPE_ASPModule) == 0) 
					createASPModule(detail.getASP_Module_ID(), packOutDocument);
				else if(type.equals(X_AD_Package_Exp_Detail.TYPE_CustomExport))
					createCustomExport(detail.getAD_Package_Exp_Custom_ID(), packOutDocument);
				else if (type.compareTo(X_AD_Package_Exp_Detail.TYPE_File_CodeOrOther) == 0) {
					log.log(Level.INFO,"In PackOut.java handling Code or Other 2pack module creation");
					
					String fullDirectory = exportPackage.getFile_Directory() + exportPackage.getName() + detail.getTarget_Directory();
					log.log(Level.INFO,"fullDirectory" + fullDirectory);
					String targetDirectoryModified=null;
					char fileseperator1 = '/';
					char fileseperator2 = '\\';
					//Correct package for proper file separator
					if (File.separator.equals("/")){			
						targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
					}
					else
						targetDirectoryModified = fullDirectory.replace(fileseperator1,fileseperator2);
					
					String target_File = (targetDirectoryModified);						
					new File(target_File).mkdirs();						
					fullDirectory = detail.getFile_Directory();
					targetDirectoryModified=null;						
					//Correct package for proper file separator
					if (File.separator.equals("/")){			
						targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
					}
					else
						targetDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
					
					copyCode(
							targetDirectoryModified + detail.getFileName(),
							target_File + detail.getFileName());
					
					atts.clear();
					
					if(detail.getDestination_Directory() != null){
						
						fullDirectory = detail.getDestination_Directory();
						String destinationDirectoryModified=null;						
						
						//Correct package for proper file separator
						if (File.separator.equals("/")){			
							destinationDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);
						}
						else
							destinationDirectoryModified = fullDirectory.replace(fileseperator2,fileseperator1);							
						
						createDistributeFile(
								detail.getFileName(),
								detail.getTarget_Directory(),
								detail.getReleaseNo(),
								destinationDirectoryModified, 
								packOutDocument);
						
					}
					
					if(detail.getFileName() != null){
						packageDocument.startElement("","","file",atts);
						packageDocument.characters(("File: " + detail.getFileName()).toCharArray(),0,("File: " + detail.getFileName()).length());
						packageDocument.endElement("","","file");
					}
					packageDocument.startElement("","","filedirectory",atts);
					packageDocument.characters(
									("Directory: " + detail.getTarget_Directory()).toCharArray(),
									0,
									("Directory: " + detail.getTarget_Directory()).length());
					packageDocument.endElement("","","filedirectory");
					
					packageDocument.startElement("","","filenotes",atts);
					packageDocument.characters(
									("Notes: " + detail.getDescription()).toCharArray(),
									0,
									(("Notes: " + detail.getDescription()).length()));
					packageDocument.endElement("","","filenotes");
				}
			}
			//	Remove Entity Type from context
			getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_EntityType_ID);
			atts.clear();
			packOutDocument.endElement("","","adempiereAD");
			packOutDocument.endDocument();packageDocument.endElement("","","adempiereDocument");
			packageDocument.endDocument();
			//m_Exp.setProcessed(true);
			//m_Exp.saveEx();
		
		} catch (Exception e) {
			log.log(Level.SEVERE,e.getLocalizedMessage(), e);
			throw e;
		} finally {
			// Close streams - teo_sarca [ 1704762 ]
			if (packageDocStream != null)
				try {
					packageDocStream.close();
				} catch (Exception e) {}
			if (packOutDocStream != null)
				try {
					packOutDocStream.close();
				} catch (Exception e) {}
		}
		
		//create compressed packages
		//set the files	
		File srcFolder = new File(packagedir);
		File destZipFile = new File(packagename+".zip");
		File destTarFile = new File(packagename+".tar");
		File destGZipFile = new File(packagename+".tar.gz");
		
		//delete the old packages if necessary
		destZipFile.delete();
		destTarFile.delete();
		destGZipFile.delete();
		
		//create the compressed packages
		CreateZipFile.zipFolder(srcFolder, destZipFile, includesdir);		
		CreateZipFile.tarFolder(srcFolder, destTarFile, includesdir);
		CreateZipFile.gzipFile(destTarFile, destGZipFile);
		
		if (ProcessInfo.INTERFACE_TYPE_ZK.equals(this.getProcessInfo().getInterfaceType()))
		{
			// Force download of the file
			String className = "org.zkoss.zul.Filedownload";
			//Get Class
			Class<?> Filedownload = null;
			//use context classloader if available
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			if (classLoader == null)
				classLoader = ProcessUtil.class.getClassLoader();
			try
			{
				Filedownload = classLoader.loadClass(className);
			}
			catch (ClassNotFoundException ex)
			{
				log.log(Level.WARNING, className, ex);
				throw new AdempiereException("Can't load the necessary class '" + className + "' to download the file. " + ex.getMessage());
			}

			Method save = null;
			Object FileDownloader = Filedownload.newInstance();
			File file = new File(packagename+".zip");			
			try 
			{
				// Invoke the ZK Filedownload.save(file, contentType)
				save = Filedownload.getMethod("save", java.io.File.class, java.lang.String.class);
				save.invoke(FileDownloader, file, (String) null);
			} 
			catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
			{
				
				log.log(Level.WARNING, "Can't download the file.", e);
				throw new AdempiereException("Can't download the file. " + e.getMessage());
				
			}
			
		}
		
		//Clean .tar file up
		destTarFile.delete();
		
		return "Finish Process";
	}	//	doIt
	
	private void initContext() {
		Properties tmp = new Properties();
		if (getCtx() != null)
			tmp.putAll(getCtx());
		tmp.put("TrxName", get_TrxName());
		tmp.put("PackOutProcess", this);
		localContext = tmp;
	}

	private void copyCode (String sourceName, String copyName)
	{
		copyFile (sourceName, copyName );
	}
	
	/**
	 * 
	 * @param AD_Menu_ID
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createMenu(int AD_Menu_ID, TransformerHandler packOutDocument) throws SAXException {
		Env.setContext(getCtx(), "AD_Menu_ID", AD_Menu_ID);
		menuHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_Menu_ID");
	}

	/**
	 * 
	 * @param printFormatId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createPrintFormat (int printFormatId, TransformerHandler packOutDocument) throws SAXException {
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_PrintFormat_ID, printFormatId);
		printFormatHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_PrintFormat_ID);
	}

	/**
	 * 
	 * @param AD_Message_ID
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createMessage (int AD_Message_ID, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Message_ID, AD_Message_ID);
		messageHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Message_ID);
	}
	
	/**
	 * 
	 * @param valRuleId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createDynamicRuleValidation (int valRuleId,  
			TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Val_Rule_ID, valRuleId);
		dynValRuleHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Val_Rule_ID);
	}

	/**
	 * 
	 * @param AD_Workflow_ID
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createWorkflow (int AD_Workflow_ID, TransformerHandler packOutDocument)  throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Workflow_ID, AD_Workflow_ID);
		workflowHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Workflow_ID);
	}

	/**
	 * 
	 * @param FileName
	 * @param Source_Directory
	 * @param ReleaseNo
	 * @param Target_Directory
	 * @param atts
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createDistributeFile (String FileName, String Source_Directory, String ReleaseNo,String Target_Directory, TransformerHandler packOutDocument) throws SAXException
	{	
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_FileName, FileName);
		Env.setContext(getCtx(), "Source_Directory", Source_Directory);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_ReleaseNo, ReleaseNo);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_Target_Directory, Target_Directory);
		distFileHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_FileName);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_ReleaseNo);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_Target_Directory);
		getCtx().remove("Source_Directory");
	}
	
	/**
	 * 
	 * @param AD_Form_ID
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createForm (int AD_Form_ID, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), "AD_Form_ID", AD_Form_ID);
		formHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_Form_ID");
	}

	/**
	 * 
	 * @param AD_Task_ID
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createTask (int AD_Task_ID, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), "AD_Task_ID", AD_Task_ID);
		taskHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_Task_ID");
	}

	/**
	 * 
	 * @param processId
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createProcess (int processId, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), "AD_Process_ID", processId);
		processHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_Process_ID");
	}
	
	/**
	 * 
	 * @param AD_Window_ID
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createWindow (int AD_Window_ID, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), "AD_Window_ID", AD_Window_ID);
		windowHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_Window_ID");
	}
	
	/**
	 * 
	 * @param AD_View_ID
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createView (int AD_View_ID, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), "AD_View_ID", AD_View_ID);
		viewHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_View_ID");
	}
	
	/**
	 * 
	 * @param AD_Browse_ID
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createBrowse (int AD_Browse_ID, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), "AD_Browse_ID", AD_Browse_ID);
		browseHandler.create(getCtx(), packOutDocument);
		getCtx().remove("AD_Browse_ID");
	}
	/**
	 * 
	 * @param table_id
	 * @param sql
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createData (int table_id, String sql, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Table_ID, table_id);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_SQLStatement, sql);
		dataHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Table_ID);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_SQLStatement);
	}
	
	/**
	 * 
	 * @param Reportview_id
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createReportview (int Reportview_id, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_ReportView_ID, Reportview_id);
		reportViewHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_ReportView_ID);
	}
	
	/**
	 * 
	 * @param SQLStatement
	 * @param DBType
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createSQL (String SQLStatement, String DBType, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_SQLStatement, SQLStatement);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_DBType, DBType);
		sqlHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_SQLStatement);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_DBType);
	}

	/**
	 * 
	 * @param FileDir
	 * @param FileName
	 * @param OldCode
	 * @param NewCode
	 * @param ReleaseNo
	 * @param atts
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createSnipit (String FileDir, String FileName, String OldCode, String NewCode, String ReleaseNo, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_File_Directory, FileDir);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_FileName, FileName);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Package_Code_Old, OldCode);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Package_Code_New, NewCode);
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_ReleaseNo, ReleaseNo);
		codeHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_File_Directory);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_FileName);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Package_Code_Old);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Package_Code_New);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_ReleaseNo);
	}
	
	/**
	 * 
	 * @param Role_id
	 * @param packOutDocument
	 * @throws Exception 
	 */
	public void createRoles (int Role_id, TransformerHandler packOutDocument) throws Exception
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Role_ID, Role_id);
		roleHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Role_ID);
	}
	
	/**
	 * 
	 * @param Reference_id
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createReference (int Reference_id, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Reference.COLUMNNAME_AD_Reference_ID, Reference_id);
		referenceHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Reference.COLUMNNAME_AD_Reference_ID);
	}

	/**
	 * 
	 * @param import_id
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createImpFormat (int import_id, TransformerHandler packOutDocument) throws SAXException
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_ImpFormat_ID, import_id);
		impFormtHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_ImpFormat_ID);
		
	}
	
	/**
	 * 
	 * @param table_id
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createTable (int table_id, TransformerHandler packOutDocument) throws SAXException
	{	
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_Table_ID, table_id);
		tableHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_Table_ID);
	}	
	
	/**
	 * 
	 * @param Reference_id
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createAdElement (int elementId, TransformerHandler packOutDocument) throws SAXException {
		Env.setContext(getCtx(), X_AD_Element.COLUMNNAME_AD_Element_ID, elementId);
		adElementHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Element.COLUMNNAME_AD_Element_ID);
	}
	
	/**
	 * 
	 * @param parentTableName
	 * @param parentId
	 * @param packOutDocument
	 * @throws SAXException
	 */
	public void createTranslations (String parentTableName, int parentId, TransformerHandler packOutDocument) throws SAXException {
		genericPOHandler.createTranslation(getCtx(), packOutDocument, MTable.get(getCtx(), parentTableName).getPO(parentId, get_TrxName()));
	}
	
	/**
	 * Get generic PO handler
	 * @return
	 */
	public GenericPOHandler getGenericPOHandler() {
		return genericPOHandler;
	}
	
	/**
	 * 
	 * @param modelValidatorId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createModelValidator (int modelValidatorId, TransformerHandler packOutDocument) throws SAXException {
		createGenericPO(packOutDocument, I_AD_ModelValidator.Table_ID, modelValidatorId);
	}
	
	/**
	 * 
	 * @param AD_EntityType_ID
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createEntityType (int AD_EntityType_ID, TransformerHandler packOutDocument) throws Exception
	{
		Env.setContext(getCtx(), X_AD_Package_Exp_Detail.COLUMNNAME_AD_EntityType_ID, AD_EntityType_ID);
		entitytypeHandler.create(getCtx(), packOutDocument);
		getCtx().remove(X_AD_Package_Exp_Detail.COLUMNNAME_AD_EntityType_ID);
	}
	
	/**
	 * For Window Customization
	 * @param windowCustomizationId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createWindowCustomization(int windowCustomizationId, TransformerHandler packOutDocument) throws Exception {
		Env.setContext(getCtx(), I_AD_WindowCustom.COLUMNNAME_AD_WindowCustom_ID, windowCustomizationId);
		windowCustomizationHandler.create(getCtx(), packOutDocument);
		getCtx().remove(I_AD_WindowCustom.COLUMNNAME_AD_WindowCustom_ID);
	}
	
	/**
	 * For Process Customization
	 * @param processCustomizationId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createProcessCustomization(int processCustomizationId, TransformerHandler packOutDocument) throws Exception {
		Env.setContext(getCtx(), I_AD_ProcessCustom.COLUMNNAME_AD_ProcessCustom_ID, processCustomizationId);
		processCustomizationHandler.create(getCtx(), packOutDocument);
		getCtx().remove(I_AD_ProcessCustom.COLUMNNAME_AD_ProcessCustom_ID);
	}
	
	/**
	 * For Browse Customization
	 * @param processCustomizationId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createBrowseCustomization(int processCustomizationId, TransformerHandler packOutDocument) throws Exception {
		Env.setContext(getCtx(), I_AD_BrowseCustom.COLUMNNAME_AD_BrowseCustom_ID, processCustomizationId);
		browseCustomizationHandler.create(getCtx(), packOutDocument);
		getCtx().remove(I_AD_BrowseCustom.COLUMNNAME_AD_BrowseCustom_ID);
	}
	
	/**
	 * @param aspModuleId
	 * @param packOutDocument
	 * @throws Exception
	 */
	public void createASPModule(int aspModuleId, TransformerHandler packOutDocument) throws Exception {
		Env.setContext(getCtx(), I_ASP_Module.COLUMNNAME_ASP_Module_ID, aspModuleId);
		aspModuleHandler.create(getCtx(), packOutDocument);
		getCtx().remove(I_ASP_Module.COLUMNNAME_ASP_Module_ID);
	}
	
	/**
	 * Create custom export
	 * @throws Exception 
	 */
	private void createCustomExport(int exporterId, TransformerHandler packOutDocument) throws Exception {
		MADPackageExpCustom exporter = MADPackageExpCustom.getById(getCtx(), exporterId, null);
		if(exporter == null) {
			return;
		}
		//	Export it
		GenericPOHandler handler = exporter.getExporterInstance();
		if(handler != null) {
			handler.create(getCtx(), packOutDocument);
		} else {
			throw new AdempiereException("@HandlerClass@ @NotFound@");
		}
	}
	
	/**
	 * Create for generic PO
	 * @param packOutDocument
	 * @param entity
	 * @throws SAXException
	 */
	public void createGenericPO(TransformerHandler packOutDocument, PO entity, boolean includeParents, List<String> excludedParentList) throws SAXException {
		genericPOHandler.create(getCtx(), packOutDocument, entity, includeParents, excludedParentList);
	}
	
	/**
	 * Create Generic PO without parents
	 * @param packOutDocument
	 * @param entity
	 * @throws SAXException
	 */
	public void createGenericPO(TransformerHandler packOutDocument, PO entity) throws SAXException {
		createGenericPO(packOutDocument, entity, false, null);
	}
	
	/**
	 * Create generic PO
	 * @param packOutDocument
	 * @param tableId
	 * @param recordId
	 * @throws SAXException
	 */
	public void createGenericPO(TransformerHandler packOutDocument, int tableId, int recordId, boolean includeParents, List<String> excludedParentList) throws SAXException {
		Env.setContext(getCtx(), GenericPOHandler.TABLE_ID_TAG, tableId);
		Env.setContext(getCtx(), GenericPOHandler.RECORD_ID_TAG, recordId);
		genericPOHandler.create(getCtx(), packOutDocument, null, includeParents, excludedParentList);
		getCtx().remove(GenericPOHandler.TABLE_ID_TAG);
		getCtx().remove(GenericPOHandler.RECORD_ID_TAG);
	}
	
	/**
	 * Create Generic PO without parents
	 * @param packOutDocument
	 * @param tableId
	 * @param recordId
	 * @throws SAXException
	 */
	public void createGenericPO(TransformerHandler packOutDocument, int tableId, int recordId) throws SAXException {
		createGenericPO(packOutDocument, tableId, recordId, false, null);
	}

	
	public void copyFile (String sourceName, String copyName ) {
		InputStream source = null;  // Stream for reading from the source file.
		OutputStream copy = null;   // Stream for writing the copy.
		boolean force;  // This is set to true if the "-f" option
		//    is specified on the command line.
		int byteCount;  // Number of bytes copied from the source file.
		force = true;
		byteCount = 0;
		try {
			source = new FileInputStream(sourceName);
			File file = new File(copyName);
			if (file.exists() && force == false) {
				System.out.println(
				"Output file exists.  Use the -f option to replace it.");
				return;  
			}
			copy = new FileOutputStream(copyName, false);
			while (true) {
				int data = source.read();
				if (data < 0)
					break;
				copy.write(data);
				byteCount++;
			}
			System.out.println("Successfully copied " + byteCount + " bytes.");
		}
		catch (Exception e) {
			System.out.println("Error occurred while copying.  "+ byteCount + " bytes copied.");
			System.out.println(e.toString());
		} finally {
			try {
				if(source != null) {
					source.close();
				}
				if(copy != null) {
					copy.close();
				}
			} catch (IOException e) {
				
			}
		}
	}


	@Override
	public Properties getCtx() {
		return localContext != null ? localContext : super.getCtx();
	}
	//	BR [1019]
	/**
	 * Set Value by Local Context
	 * @param localContext to set
	 */
	public void setLocalContext(Properties localContext) {
		this.localContext = localContext;
	}
}	//	PackOut
