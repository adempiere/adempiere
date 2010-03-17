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
 *****************************************************************************/
package org.compiere.www;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ecs.AlignType;
import org.apache.ecs.Element;
import org.apache.ecs.xhtml.form;
import org.apache.ecs.xhtml.i;
import org.apache.ecs.xhtml.input;
import org.apache.ecs.xhtml.option;
import org.apache.ecs.xhtml.p;
import org.apache.ecs.xhtml.select;
import org.apache.ecs.xhtml.table;
import org.apache.ecs.xhtml.td;
import org.apache.ecs.xhtml.tr;
import org.compiere.model.GridTab;
import org.compiere.model.MAllocationHdr;
import org.compiere.model.MBankStatement;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalBatch;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MPayment;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessPara;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.WebDoc;
import org.compiere.util.WebEnv;
import org.compiere.util.WebSessionCtx;
import org.compiere.util.WebUtil;
import org.compiere.wf.MWFActivity;


/**
 *	HTML Process and Report UI
 *
 *  @author Jorg Janke
 *  @version  $Id: WProcess.java,v 1.1 2009/04/15 11:27:15 vinhpt Exp $
 */
public class WProcess extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3937980012432969521L;
	
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	//Modified by Rob Klein 4/29/07
	private String errorMessage = null;
	private static String[]		s_value = null;
	private static String[]		s_name;
	private static String[]		s_description;

	/**
	 *	Initialize global variables
	 *	@param config 
	 *	@throws ServletException 
	 */
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		if (!WebEnv.initWeb(config))
			throw new ServletException("WProcess.init");
	}   //  init

	/**
	 *	Process the HTTP Get request.
	 *	Initial Call
	 *	@param request 
	 *	@param response 
	 *	@throws ServletException 
	 *	@throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//  Get Session attributes
		
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		//Modified by Rob Klein 4/29/07
	  	WWindowStatus ws = WWindowStatus.get(request);
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
				
		WebDoc doc = null;
		//  Get Parameter: Menu_ID
		//Modified by Rob Klein 4/29/07
		int AD_Menu_ID = WebUtil.getParameterAsInt(request, "AD_Menu_ID");		
		String fileName = WebUtil.getParameter(request, "File");
		if (AD_Menu_ID > 0)
		{
			
			doc = createParameterPage(wsc, AD_Menu_ID,0,0,0,0,null,null);
		}
		//else if (fileName!=null)
		//{			
		//	int AD_PInstance_ID = WebUtil.getParameterAsInt(request, "AD_PInstance_ID");
		//	
		//	String error = streamResult (request, response, AD_PInstance_ID, fileName);
		//	if (error == null)
		//		return;
		//	doc = WebDoc.createWindow(error);
		//}
		else
		{
			int AD_Process_ID = WebUtil.getParameterAsInt(request, "AD_Process_ID");			
			int AD_Window_ID = WebUtil.getParameterAsInt(request, "AD_Window_ID");
			int AD_Table_ID = WebUtil.getParameterAsInt(request, "AD_Table_ID");
			int AD_Record_ID = WebUtil.getParameterAsInt(request, "AD_Record_ID");
			String columnName = WebUtil.getParameter(request, "columnName");
			int AD_Tab_ID = WebUtil.getParameterAsInt(request, "AD_Tab_ID");
			if (AD_Process_ID == 0)
			{
				WebUtil.createErrorPage(request, response, this, "No Process");
				return;
			}
			
			doc = createParameterPage(wsc, AD_Process_ID,AD_Window_ID,AD_Table_ID,AD_Record_ID,1,
					columnName,ws.curTab);
			
		}
		if (doc == null)
			doc = WebDoc.createWindow("Process Not Found");

		//
		WebUtil.createResponse(request, response, this, null, doc, true);
	}   //  doGet


	/**
	 *  Process the HTTP Post request.
	 *  Get Parameters and Process
	 *	@param request 
	 *	@param response 
	 *	@throws ServletException 
	 *	@throws IOException 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//  Get Session attributes
	  	WebSessionCtx wsc = WebSessionCtx.get(request);
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		int AD_Process_ID = WebUtil.getParameterAsInt(request, "AD_Process_ID");
		//Modified by Rob Klein 4/29/07
		int AD_Window_ID = WebUtil.getParameterAsInt(request, "AD_Window_ID");
		int AD_Table_ID = WebUtil.getParameterAsInt(request, "AD_Table_ID");
		int AD_Record_ID = WebUtil.getParameterAsInt(request, "AD_Record_ID");		
		
		
		if (AD_Process_ID == 0)
		{
			WebUtil.createErrorPage(request, response, this, "No Process");
			return;
		}
		//Modified by Rob Klein 6/01/07
		//WebDoc doc = createProcessPage(request, AD_Process_ID, AD_Window_ID);
		createProcessPage(request, response, AD_Process_ID, AD_Window_ID);
		//
		
	}   //  doPost

	//Modified by Rob Klein 4/29/07
	/**************************************************************************
	 * 	Create Parameter Page
	 * 	@param wsc web session context
	 *	@param AD_Menu_ID Menu
	 *	@return Page
	 */
	private WebDoc createParameterPage (WebSessionCtx wsc, int processId, int windowID,int tableID,int recordID,int Type,
			String columnName, GridTab mTab)
	{
		MProcess process = null;
		if (Type == 0)
			 process = MProcess.getFromMenu (wsc.ctx, processId);
		else
			 process = MProcess.get(wsc.ctx, processId);
		
		//	need to check if Role can access
		if (process == null)
		{
			WebDoc doc = WebDoc.createWindow("Process Not Found");
			return doc;
		}
		//Modified by Rob Klein 4/29/07
		WebDoc doc = WebDoc.createWindow(process.getName());
		if (process.isWorkflow())
		{
			//Modified by Rob Klein 7/01/07
			if (mTab == null)
			{
				doc = WebDoc.createWindow("No Tab found");
				return doc;
			}	
			
			//	Pop up Document Action (Workflow)
			if (columnName.toString().equals("DocAction"))			{
				
				readReference();			
								
				option[] Options = dynInit( windowID, tableID, recordID,
						 columnName,  mTab);				
				
				td center = doc.addWindowCenter(false);
				
				WebField wField = new WebField (wsc,
						columnName, columnName, columnName,
						//	no display length
						17, 22, 22, false,
						// 	not r/o, ., not error, not dependent
						false, false, false, false, false, processId,
						0, 0, 0, 0, null, null, null, null, null);
				
				if (process.getDescription() != null)
					center.addElement(new p(new i(process.getDescription())));
				if (process.getHelp() != null)
					center.addElement(new p(process.getHelp(), AlignType.LEFT));
				form myForm = new form ("WProcess")
					.setName("process" + process.getAD_Process_ID());

				myForm.setTarget("WPopup");
				//myForm.setOnSubmit("this.Submit.disabled=true;return true;");
				myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Process_ID", process.getAD_Process_ID()));
				myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Window_ID", windowID));
				myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Table_ID", tableID));
				myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Record_ID", recordID));
				table myTable = new table("0", "0", "5", "100%", null);
				myTable.setID("WProcessParameter");
				
				myTable.addElement(new tr()
				.addElement(wField.getLabel())
				.addElement(createSelectField(columnName,Options)));
				

				// Reset
				String text = "Reset";
				if (wsc.ctx != null)
					text = Msg.getMsg (wsc.ctx, "Reset");		
				input restbtn = new input(input.TYPE_RESET, text, "  "+text);		
				restbtn.setID(text);
				restbtn.setClass("resetbtn");	
				
				//	Submit
				 text = "Submit";
				if (wsc.ctx != null)
					text = Msg.getMsg (wsc.ctx, "Submit");		
				input submitbtn = new input(input.TYPE_SUBMIT, text, "  "+text);		
				submitbtn.setID(text);
				submitbtn.setClass("submitbtn");				
				
				myTable.addElement(new tr()
					.addElement(new td(null, AlignType.RIGHT, AlignType.MIDDLE, false, 
							restbtn))
					.addElement(new td(null, AlignType.LEFT, AlignType.MIDDLE, false, 
							submitbtn ))
					.addElement(new td(null, AlignType.RIGHT, AlignType.MIDDLE, false, 
							null)));
				myForm.addElement(myTable);
				center.addElement(myForm);	
				
			}	//	DocAction
			
		}
		else{		

			td center = doc.addWindowCenter(false);
			if (process.getDescription() != null)
				center.addElement(new p(new i(process.getDescription())));
			if (process.getHelp() != null)
				center.addElement(new p(process.getHelp(), AlignType.LEFT));
			//
			form myForm = new form ("WProcess")
				.setName("process" + process.getAD_Process_ID());
			myForm.setTarget("WPopup");
			//myForm.setOnSubmit("this.Submit.disabled=true;return true;");
			myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Process_ID", process.getAD_Process_ID()));
			myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Window_ID", windowID));
			myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Table_ID", tableID));
			myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Record_ID", recordID));
			table myTable = new table("0", "0", "5", "100%", null);
			myTable.setID("WProcessParameter");
			MProcessPara[] parameter = process.getParameters();
			
			for (int i = 0; i < parameter.length; i++)
			{			
				MProcessPara para = parameter[i];			
				WebField wField = new WebField (wsc,
					para.getColumnName(), para.getName(), para.getDescription(),
					//	no display length
					para.getAD_Reference_ID(), para.getFieldLength(), para.getFieldLength(), false,
					// 	not r/o, ., not error, not dependent
					false, para.isMandatory(), false, false, false, para.getAD_Process_ID(),
					0,0,0,i, null,null, null,null, null);
				
				WebField wFieldforRange = null;
				
				if(para.isRange())				
					wFieldforRange = new WebField (wsc,
						para.getColumnName(), para.getName(), para.getDescription(),
						//	no display length
						para.getAD_Reference_ID(), para.getFieldLength(), para.getFieldLength(), false,
						// 	not r/o, ., not error, not dependent
						false, para.isMandatory(), false, false, false, para.getAD_Process_ID(),0,0,0,i+1,
						null,null, null,null, null);			
				
				td toField = para.isRange() 
					? wFieldforRange.getField(para.getLookup(), para.getDefaultValue2())
					: new td(WebEnv.NBSP);
				
				//	Add to Table
				myTable.addElement(new tr()
					.addElement(wField.getLabel())
					.addElement(wField.getField(para.getLookup(), para.getDefaultValue()))
					.addElement(toField));		
			}
			
			// Reset
			String text = "Reset";
			if (wsc.ctx != null)
				text = Msg.getMsg (wsc.ctx, "Reset");		
			input restbtn = new input(input.TYPE_RESET, text, "  "+text);		
			restbtn.setID(text);
			restbtn.setClass("resetbtn");	
			
			//	Submit
			 text = "Submit";
			if (wsc.ctx != null)
				text = Msg.getMsg (wsc.ctx, "Submit");		
			input submitbtn = new input(input.TYPE_SUBMIT, text, "  "+text);		
			submitbtn.setID(text);
			submitbtn.setClass("submitbtn");
			submitbtn.setOnClick("popUp('WProcess','WPopup')");
			
			myTable.addElement(new tr()
				.addElement(new td(null, AlignType.RIGHT, AlignType.MIDDLE, false, 
						restbtn))
				.addElement(new td(null, AlignType.LEFT, AlignType.MIDDLE, false, 
						submitbtn ))
				.addElement(new td(null, AlignType.RIGHT, AlignType.MIDDLE, false, 
						null)));
			myForm.addElement(myTable);
			center.addElement(myForm);
		}
		return doc;
	}	//	createParameterPage

//Modified by Rob klein 4/29/07	
	/**************************************************************************
	 * 	Create Parocess Page
	 * 	@param request request
	 *	@param AD_Process_ID Process
	 *	@return Page
	 */
	public void createProcessPage (HttpServletRequest request, HttpServletResponse response, int AD_Process_ID, int AD_Window_ID)
	{
	  	WebSessionCtx wsc = WebSessionCtx.get (request);
		MProcess process = MProcess.get (wsc.ctx, AD_Process_ID);
		log.info("PI table id "+process.get_Table_ID());
		log.info("PI table name id "+process.get_TableName());
		log.info("PI table client id "+process.getAD_Client_ID());
		log.info("PI table process id "+process.getAD_Process_ID());
		log.info("PI  process class name "+process.getClassname());
		
		//	need to check if Role can access
		WebDoc doc = null;
		if (process == null)
		{
			 doc = WebDoc.createWindow("Process Not Found");

		}
		else{
			doc = WebDoc.createWindow(process.getName());
		td center = doc.addWindowCenter(false);
		if (process.getDescription() != null)
			center.addElement(new p(new i(process.getDescription())));
		if (process.getHelp() != null)
			center.addElement(new p(process.getHelp(), AlignType.LEFT));
		
		//	Create Process Instance
		MPInstance pInstance = fillParameter (request, process);
		//		
		
		int AD_Table_ID = WebUtil.getParameterAsInt(request, "AD_Table_ID");
		int AD_Record_ID = WebUtil.getParameterAsInt(request, "AD_Record_ID");		
		
		
		ProcessInfo pi = new ProcessInfo (process.getName(), process.getAD_Process_ID(), AD_Table_ID, AD_Record_ID);		
		pi.setAD_User_ID(Env.getAD_User_ID(wsc.ctx));
		pi.setAD_Client_ID(Env.getAD_Client_ID(wsc.ctx));
		pi.setClassName(process.getClassname());
		log.info("PI client id "+pi.getAD_Client_ID());
		pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());			
		
		//	Info
		p p = new p();
		p.addElement(Msg.translate(wsc.ctx, "AD_PInstance_ID") + ": " + pInstance.getAD_PInstance_ID());
		center.addElement(p);
		
		//	Start
		boolean processOK = false;
		if (process.isWorkflow())
		{
			Trx trx = Trx.get(Trx.createTrxName("WebPrc"), true);
			try
			{	
				WProcessCtl.process(this, AD_Window_ID, pi, trx, request); 
				//processOK = process.processIt(pi, trx);			
				trx.commit();
				trx.close();
			}
			catch (Throwable t)
			{
				trx.rollback();
				trx.close();
			}
			if ( pi.isError())
			{
				center.addElement(new p("Error:" + pi.getSummary(), 
					AlignType.LEFT).setClass("Cerror"));
				processOK = false;
			}
			else
			{
				center.addElement(new p("OK: Workflow Started", 
						AlignType.LEFT));
					processOK = true;
			}
			center.addElement(new p().addElement(pi.getSummary()));
			center.addElement(pi.getLogInfo(true));
		}
		
		String jasper=process.getJasperReport();
		if (process.isJavaProcess())
		{
			if (jasper!=null) {
				pi.setPrintPreview (false);
				pi.setIsBatch(true);
			}
			Trx trx = Trx.get(Trx.createTrxName("WebPrc"), true);
			try
			{				
				processOK = process.processIt(pi, trx);			
				trx.commit();
				trx.close();
			}
			catch (Throwable t)
			{
				trx.rollback();
				trx.close();
			}
			if (!processOK || pi.isError())
			{
				center.addElement(new p("Error:" + pi.getSummary(), 
					AlignType.LEFT).setClass("Cerror"));
				processOK = false;
			} else {
				if(jasper!=null) {
					String error = WebUtil.streamFile(response, pi.getPDFReport());
					//String error = streamResult (request, response, pInstance.getAD_PInstance_ID(), file);
					if (error == null)
						return;
					doc = WebDoc.create(error);
					wsc.ctx.put("AD_PInstance_ID=" + pInstance.getAD_PInstance_ID(), "ok");
				} else {
					center.addElement(new p().addElement(pi.getSummary()));
					center.addElement(pi.getLogInfo(true));
				}
			}
		}
		
		//	Report
		if (process.isReport())
		//if (processOK && process.isReport())
		{
			//doc = null;
			
			
			
			if(jasper==null) {
				log.info(response.toString());
				ReportEngine re = ReportEngine.get(wsc.ctx, pi);
				if (re == null)
				{
					center.addElement(new p("Could not start ReportEngine", 
						AlignType.LEFT).setClass("Cerror"));
				}
				else
				{
					try
					{
						File file = File.createTempFile("WProcess", ".pdf");
						boolean ok = re.createPDF(file);
						if (ok)
						{	
							String error = WebUtil.streamFile(response, file);
							//String error = streamResult (request, response, pInstance.getAD_PInstance_ID(), file);
							if (error == null)
								return;
							doc = WebDoc.create(error);
							
							//Modified by Rob Klein 6/1/07
							/**
							String url = "WProcess?AD_PInstance_ID=" 
								+ pInstance.getAD_PInstance_ID()
								+ "&File=" 
								+ URLEncoder.encode(file.getAbsolutePath(), WebEnv.ENCODING);
							a link = new a (url, null, a.TARGET_BLANK, process.getName());
							center
								.addElement(new p()
									.addElement("Report created: ")
									.addElement(link));
							//	Marker that Process is OK
							 * */
							wsc.ctx.put("AD_PInstance_ID=" + pInstance.getAD_PInstance_ID(), "ok");
							
						}
						else
							center.addElement(new p("Could not create Report", 
								AlignType.LEFT).setClass("Cerror"));
					}
					catch (Exception e)
					{
						center.addElement(new p("Could not create Report:", 
							AlignType.LEFT).setClass("Cerror"));
						center.addElement(e.toString());
					}
				}
			}
		}
		}
		doc.addPopupClose(wsc.ctx);
		
		try {
			WebUtil.createResponse(request, response, this, null, doc, false);
		} catch (IOException e) {
			log.info(e.toString());
		}		
	}	//	createProcessPage
	
	private ASyncProcess ASyncProcess(WProcess process) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 	Fill Parameter
	 *	@param request request
	 *	@param process process
	 *	@return process instance
	 */
	private MPInstance fillParameter(HttpServletRequest request, MProcess process)
	{
		MPInstance pInstance = new MPInstance (process, 0);
		//
		MPInstancePara[] iParams = pInstance.getParameters();
		for (int pi = 0; pi < iParams.length; pi++)
		{
			MPInstancePara iPara = iParams[pi];
			String key = iPara.getParameterName();
			MProcessPara pPara = process.getParameter(key);
			if (pPara == null)
			{
				log.log(Level.SEVERE, "Parameter not found: " + key);
				continue;
			}
			
			String valueString = WebUtil.getParameter (request, key);
			log.fine("fillParameter - " + key + " = " + valueString);
			Object value = valueString;
			if (valueString != null && valueString.length() == 0)
				value = null;
			//	No Value
			if (value == null)
			{
			//	if (pPara.isMandatory())
			//		log.log(Level.WARNING,"fillParameter - " + key 
			//			+ " - empty - mandatory!");
			}
			else
			{
				//	Convert to Type
				try
				{
					if (DisplayType.isNumeric(pPara.getAD_Reference_ID()) 
						|| DisplayType.isID(pPara.getAD_Reference_ID()))
					{
						BigDecimal bd = null;
						if (value instanceof BigDecimal)
							bd = (BigDecimal)value;
						else if (value instanceof Integer)
							bd = new BigDecimal (((Integer)value).intValue());
						else
							bd = new BigDecimal (value.toString());
						iPara.setP_Number(bd);
						log.fine("fillParameter - " + key
								+ " = " + valueString + " (=" + bd + "=)");
					}
					else if (DisplayType.isDate(pPara.getAD_Reference_ID()))
					{
						Timestamp ts = null;
						if (value instanceof Timestamp)
							ts = (Timestamp)value;
						else
							ts = Timestamp.valueOf(value.toString());
						iPara.setP_Date(ts);
						log.fine("fillParameter - " + key
							+ " = " + valueString + " (=" + ts + "=)");
					}
					else
					{
						iPara.setP_String(value.toString());
					}
					//
					iPara.save();
				}
				catch (Exception e)
				{
					log.warning("fillParameter - " + key
						+ " = " + valueString + " (" + value
						+ ") " + value.getClass().getName()
						+ " - " + e.getLocalizedMessage());
				}
			}	//	not null
		}	//	instance parameter loop
		
		return pInstance;
	}	//	fillParameter

	/**
	 * 	Stream Result
	 *	@param request request
	 *	@param response response
	 *	@param AD_PInstance_ID instance id
	 *	@param fileName file to stream
	 *	@return message
	 */
	private String streamResult (HttpServletRequest request, HttpServletResponse response,
		int AD_PInstance_ID, File file)
	{
		if (AD_PInstance_ID == 0)
			return "Your process not found";
	  	WebSessionCtx wsc = WebSessionCtx.get (request);
		Object value = wsc.ctx.get("AD_PInstance_ID=" + AD_PInstance_ID);
		if (value == null || !value.equals("ok"))
			return "Process Instance not found";
		//
		if (file == null || file.length() == 0)
			return "No Process Result";
		//File file = new File (fileName);
		if (!file.exists())
			return "Process Result not found: " + file;
		//	OK
		return WebUtil.streamFile(response, file);
	}	//	streamResult

//Modidifed by Rob Klein 4/29/07
	
	/**
	 *	Dynamic Init - determine valid DocActions based on DocStatus for the different documents.
	 *  <pre>
	 *  DocStatus (131)
			??                         Unknown
			AP *                       Approved
			CH                         Changed
			CL *                       Closed
			CO *                       Completed
			DR                         Drafted
			IN                         Inactive
			NA                         Not Approved
			PE                         Posting Error
			PO *                       Posted
			PR *                       Printed
			RE                         Reversed
			TE                         Transfer Error
			TR *                       Transferred
			VO *                       Voided
			XX                         Being Processed
	 *
	 *   DocAction (135)
			--                         <None>
			AP *                       Approve
			CL *                       Close
			CO *                       Complete
			PO *                       Post
			PR *                       Print
			RA                         Reverse - Accrual
			RC                         Reverse - Correction
			RE                         RE-activate
			RJ                         Reject
			TR *                       Transfer
			VO *                       Void
			XL                         Unlock
	 *  </pre>
	 * 	@param Record_ID id
	 */
	private option[] dynInit(int m_WindowNo,int m_AD_Table_ID,int Record_ID,
			String columnName, GridTab m_mTab)
	{
		String DocStatus = (String)m_mTab.getValue("DocStatus");
		String DocAction = (String)m_mTab.getValue("DocAction");
		//
		Object Processing = m_mTab.getValue("Processing");
		String OrderType = Env.getContext(Env.getCtx(), m_WindowNo, "OrderType");
		String IsSOTrx = Env.getContext(Env.getCtx(), m_WindowNo, "IsSOTrx");

		option[] optionsret = new option[0];
		String[] list = new String [s_value.length];
		
		 
		if (DocStatus == null)
		{
			errorMessage = "Document Status is Null";
			return optionsret;
		}

		log.fine("DocStatus=" + DocStatus 
			+ ", DocAction=" + DocAction + ", OrderType=" + OrderType 
			+ ", IsSOTrx=" + IsSOTrx + ", Processing=" + Processing 
			+ ", AD_Table_ID=" + m_AD_Table_ID + ", Record_ID=" + Record_ID);
		//

		int index = 0;

		/**
		 * 	Check Existence of Workflow Acrivities
		 */
		String wfStatus = MWFActivity.getActiveInfo(Env.getCtx(), m_AD_Table_ID, Record_ID); 
		if (wfStatus != null)
		{
			errorMessage =  wfStatus;
			return optionsret;
		}
		
		//	Status Change
		if (!checkStatus(m_mTab.getTableName(), Record_ID, DocStatus))
		{
			errorMessage =  "DocumentStatusChanged";			
			return optionsret;
		}
		
		/*******************
		 *  General Actions
		 */

		//	Locked
		if (Processing != null)
		{
			boolean locked = "Y".equals(Processing);
			if (!locked && Processing instanceof Boolean)
				locked = ((Boolean)Processing).booleanValue();
			if (locked)
				list[index++]= DocumentEngine.ACTION_Unlock;
		}

		//	Approval required           ..  NA
		if (DocStatus.equals(DocumentEngine.STATUS_NotApproved))
		{
			list[index++]= (DocumentEngine.ACTION_Prepare);
			list[index++]= (DocumentEngine.ACTION_Void);
		}
		//	Draft/Invalid				..  DR/IN
		else if (DocStatus.equals(DocumentEngine.STATUS_Drafted)
			|| DocStatus.equals(DocumentEngine.STATUS_Invalid))
		{
			list[index++]= (DocumentEngine.ACTION_Complete);
		//	options[index++] = (DocumentEngine.ACTION_Prepare);
			list[index++]= (DocumentEngine.ACTION_Void);
		}
		//	In Process                  ..  IP
		else if (DocStatus.equals(DocumentEngine.STATUS_InProgress)
			|| DocStatus.equals(DocumentEngine.STATUS_Approved))
		{
			list[index++]= (DocumentEngine.ACTION_Complete);
			list[index++]= (DocumentEngine.ACTION_Void);
		}
		//	Complete                    ..  CO
		else if (DocStatus.equals(DocumentEngine.STATUS_Completed))
		{
			list[index++]= (DocumentEngine.ACTION_Close);
		}
		//	Waiting Payment
		else if (DocStatus.equals(DocumentEngine.STATUS_WaitingPayment)
			|| DocStatus.equals(DocumentEngine.STATUS_WaitingConfirmation))
		{
			list[index++]= (DocumentEngine.ACTION_Void);
			list[index++]= (DocumentEngine.ACTION_Prepare);
		}
		//	Closed, Voided, REversed    ..  CL/VO/RE
		else if (DocStatus.equals(DocumentEngine.STATUS_Closed) 
			|| DocStatus.equals(DocumentEngine.STATUS_Voided) 
			|| DocStatus.equals(DocumentEngine.STATUS_Reversed))
			return optionsret;

		/********************
		 *  Order
		 */
		if (m_AD_Table_ID == MOrder.Table_ID)
		{
			//	Draft                       ..  DR/IP/IN
			if (DocStatus.equals(DocumentEngine.STATUS_Drafted)
				|| DocStatus.equals(DocumentEngine.STATUS_InProgress)
				|| DocStatus.equals(DocumentEngine.STATUS_Invalid))
			{
				list[index++]= (DocumentEngine.ACTION_Prepare);
				list[index++]= (DocumentEngine.ACTION_Close);
				//	Draft Sales Order Quote/Proposal - Process
				if ("Y".equals(IsSOTrx)
					&& ("OB".equals(OrderType) || "ON".equals(OrderType)))
					DocAction = DocumentEngine.ACTION_Prepare;
			}
			//	Complete                    ..  CO
			else if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Void);
				list[index++]= (DocumentEngine.ACTION_ReActivate);
			}
			else if (DocStatus.equals(DocumentEngine.STATUS_WaitingPayment))
			{
				list[index++]= (DocumentEngine.ACTION_ReActivate);
				list[index++]= (DocumentEngine.ACTION_Close);
			}
		}
		/********************
		 *  Shipment
		 */
		else if (m_AD_Table_ID == MInOut.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Void);
				list[index++]= (DocumentEngine.ACTION_Reverse_Correct);
			}
		}
		/********************
		 *  Invoice
		 */
		else if (m_AD_Table_ID == MInvoice.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Void);
				list[index++]= (DocumentEngine.ACTION_Reverse_Correct);
			}
		}
		/********************
		 *  Payment
		 */
		else if (m_AD_Table_ID == MPayment.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Void);
				list[index++]= (DocumentEngine.ACTION_Reverse_Correct);
			}
		}
		/********************
		 *  GL Journal
		 */
		else if (m_AD_Table_ID == MJournal.Table_ID || m_AD_Table_ID == MJournalBatch.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Reverse_Correct);
				list[index++]= (DocumentEngine.ACTION_Reverse_Accrual);
			}
		}
		/********************
		 *  Allocation
		 */
		else if (m_AD_Table_ID == MAllocationHdr.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Void);
				list[index++]= (DocumentEngine.ACTION_Reverse_Correct);
			}
		}
		/********************
		 *  Bank Statement
		 */
		else if (m_AD_Table_ID == MBankStatement.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++]= (DocumentEngine.ACTION_Void);
			}
		}
		/********************
		 *  Inventory Movement, Physical Inventory
		 */
		else if (m_AD_Table_ID == MMovement.Table_ID
			|| m_AD_Table_ID == MInventory.Table_ID)
		{
			//	Complete                    ..  CO
			if (DocStatus.equals(DocumentEngine.STATUS_Completed))
			{
				list[index++] = (DocumentEngine.ACTION_Void);
				list[index++] = (DocumentEngine.ACTION_Reverse_Correct);
			}			
			
		}
		
		
		option[] options = new option[index];
		/**
		 *	Add Name to options
		 */
		for (int i = 0; i < index; i++)
		{
			//	Serach for option and add it			
			boolean added = false;
			for (int j = 0; j < s_value.length && !added; j++)
			{				
				if (list[i].equals(s_value[j]))
				{					
					options[i] = new option(list[i]).addElement(s_name[j]);
					added = true;
					if (DocAction.equals(s_value[i]))
						options[i].setSelected(true);
				}
			}
		}	

		return options;
	}	//	dynInit
	
	/**
	 *	Fill Vector with DocAction Ref_List(135) values
	 */
	private void readReference()
	{
		String sql;
		if (Env.isBaseLanguage(Env.getCtx(), "AD_Ref_List"))
			sql = "SELECT Value, Name, Description FROM AD_Ref_List "
				+ "WHERE AD_Reference_ID=135 ORDER BY Name";
		else
			sql = "SELECT l.Value, t.Name, t.Description "
				+ "FROM AD_Ref_List l, AD_Ref_List_Trl t "
				+ "WHERE l.AD_Ref_List_ID=t.AD_Ref_List_ID"
				+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "'"
				+ " AND l.AD_Reference_ID=135 ORDER BY t.Name";

		ArrayList<String> v_value = new ArrayList<String>();
		ArrayList<String> v_name = new ArrayList<String>();
		ArrayList<String> v_description = new ArrayList<String>();
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, null);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next())
			{
				String value = rs.getString(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				if (description == null)
					description = "";
				//
				v_value.add(value);
				v_name.add(name);
				v_description.add(description);
			}
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
		}

		//	convert to arrays
		int size = v_value.size();
		s_value = new String[size];
		s_name = new String[size];
		s_description = new String[size];
		for (int i = 0; i < size; i++)
		{
			s_value[i] = (String)v_value.get(i);
			s_name[i] = (String)v_name.get(i);
			s_description[i] = (String)v_description.get(i);
		}
	}	//	readReference
	
	/**
	 * 	Check Status Change
	 *	@param TableName table name
	 *	@param Record_ID record
	 *	@param DocStatus current doc status
	 *	@return true if status not changed
	 */
	private boolean checkStatus (String TableName, int Record_ID, String DocStatus)
	{
		String sql = "SELECT 2 FROM " + TableName 
			+ " WHERE " + TableName + "_ID=" + Record_ID
			+ " AND DocStatus='" + DocStatus + "'";
		int result = DB.getSQLValue(null, sql);
		return result == 2;
	}	//	checkStatusChange	
		
	/**
	 * 	Get Select Field
	 *	@param lookup lookup
	 *	@param dataValue default value
	 *	@return selction td
	 */
	private td createSelectField (String m_columnName, option[] options)
	{		
		select sel = new select(m_columnName, options);
		sel.setID(m_columnName);
		sel.setDisabled(false);
		sel.setClass("Cmandatory");
		
		//
		return createTD(sel);

	}	//	getSelectField
	/**
	 * 	Create Left Top aligned TD
	 *	@param element element
	 *	@return td table data
	 */
	private td createTD (Element element)
	{
		td td = new td()
			.addElement(element)
			.setAlign(AlignType.LEFT)
			.setVAlign(AlignType.TOP);	
		return td;
	}	//	createTD

}   //  WProcess
