/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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

import java.io.*;
import java.math.*;
import java.net.*;
import java.sql.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.model.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.util.*;

/**
 *	HTML Process and Report UI
 *
 *  @author Jorg Janke
 *  @version  $Id: WProcess.java,v 1.3 2006/07/30 00:53:21 jjanke Exp $
 */
public class WProcess extends HttpServlet
{
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());

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
		if (wsc == null)
		{
			WebUtil.createTimeoutPage(request, response, this, null);
			return;
		}
		
		WebDoc doc = null;
		//  Get Parameter: Menu_ID
		int AD_Menu_ID = WebUtil.getParameterAsInt(request, "AD_Menu_ID");
		if (AD_Menu_ID > 0)
		{
			log.info("doGet - AD_Menu_ID=" + AD_Menu_ID);
			doc = createParameterPage(wsc, AD_Menu_ID);
		}
		else
		{
			String fileName = WebUtil.getParameter(request, "File");
			int AD_PInstance_ID = WebUtil.getParameterAsInt(request, "AD_PInstance_ID");
			log.info("doGet - AD_PInstance_ID=" + AD_PInstance_ID 
				+ ", File=" + fileName);
			String error = streamResult (request, response, AD_PInstance_ID, fileName);
			if (error == null)
				return;
			doc = WebDoc.createWindow(error);
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
		log.info("doGet - AD_Process_ID=" + AD_Process_ID);
		if (AD_Process_ID == 0)
		{
			WebUtil.createErrorPage(request, response, this, "No Process");
			return;
		}
		
		WebDoc doc = createProcessPage(request, AD_Process_ID);
		//
		WebUtil.createResponse(request, response, this, null, doc, false);
	}   //  doPost

	
	/**************************************************************************
	 * 	Create Parameter Page
	 * 	@param wsc web session context
	 *	@param AD_Menu_ID Menu
	 *	@return Page
	 */
	private WebDoc createParameterPage (WebSessionCtx wsc, int AD_Menu_ID)
	{
		MProcess process = MProcess.getFromMenu (wsc.ctx, AD_Menu_ID);
		//	need to check if Role can access
		if (process == null)
		{
			WebDoc doc = WebDoc.createWindow("Process Not Found");
			return doc;
		}

		WebDoc doc = WebDoc.createWindow(process.getName());
		td center = doc.addWindowCenter(false);
		if (process.getDescription() != null)
			center.addElement(new p(new i(process.getDescription())));
		if (process.getHelp() != null)
			center.addElement(new p(process.getHelp(), AlignType.LEFT));
		//
		form myForm = new form ("WProcess")
			.setName("process" + process.getAD_Process_ID());
		myForm.setOnSubmit("this.Submit.disabled=true;return true;");
		myForm.addElement(new input(input.TYPE_HIDDEN, "AD_Process_ID", process.getAD_Process_ID()));
		table myTable = new table("0", "0", "5", "100%", null);
		myTable.setID("WProcessParameter");
		MProcessPara[] parameter = process.getParameters();
		for (int i = 0; i < parameter.length; i++)
		{
			MProcessPara para = parameter[i];
			//
			WebField wField = new WebField (wsc,
				para.getColumnName(), para.getName(), para.getDescription(),
				//	no display length
				para.getAD_Reference_ID(), para.getFieldLength(), para.getFieldLength(), false,
				// 	not r/o, ., not error, not dependent
				false, para.isMandatory(), false, false, false);	
			
			td toField = para.isRange() 
				? wField.getField(para.getLookup(), para.getDefaultValue2())
				: new td(WebEnv.NBSP);
			
			//	Add to Table
			myTable.addElement(new tr()
				.addElement(wField.getLabel())
				.addElement(wField.getField(para.getLookup(), para.getDefaultValue()))
				.addElement(toField));
		}
		//	Submit
		myTable.addElement(new tr()
			.addElement(new td(null, AlignType.LEFT, AlignType.MIDDLE, false, 
				new input(input.TYPE_RESET, "Reset", "Reset") ))
			.addElement(new td(null, AlignType.LEFT, AlignType.MIDDLE, false, 
				null ))
			.addElement(new td(null, AlignType.RIGHT, AlignType.MIDDLE, false, 
				new input(input.TYPE_SUBMIT, "Submit", "Submit") )));
		myForm.addElement(myTable);
		center.addElement(myForm);
		return doc;
	}	//	createParameterPage

	
	/**************************************************************************
	 * 	Create Parocess Page
	 * 	@param request request
	 *	@param AD_Process_ID Process
	 *	@return Page
	 */
	private WebDoc createProcessPage (HttpServletRequest request, int AD_Process_ID)
	{
	  	WebSessionCtx wsc = WebSessionCtx.get (request);
		MProcess process = MProcess.get (wsc.ctx, AD_Process_ID);
		//	need to check if Role can access
		if (process == null)
		{
			WebDoc doc = WebDoc.createWindow("Process Not Found");
			return doc;
		}

		WebDoc doc = WebDoc.createWindow(process.getName());
		td center = doc.addWindowCenter(false);
		if (process.getDescription() != null)
			center.addElement(new p(new i(process.getDescription())));
		if (process.getHelp() != null)
			center.addElement(new p(process.getHelp(), AlignType.LEFT));
		
		//	Create Process Instance
		MPInstance pInstance = fillParameter (request, process);
		//
		ProcessInfo pi = new ProcessInfo (process.getName(), process.getAD_Process_ID());
		pi.setAD_User_ID(Env.getAD_User_ID(wsc.ctx));
		pi.setAD_Client_ID(Env.getAD_Client_ID(wsc.ctx));
		pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
		
		//	Info
		p p = new p();
		p.addElement(Msg.translate(wsc.ctx, "AD_PInstance_ID") + ": " + pInstance.getAD_PInstance_ID());
		center.addElement(p);
		
		//	Start
		boolean processOK = false;
		if (process.isJavaProcess())
		{
			Trx trx = Trx.get(Trx.createTrxName("WebPrc"), true);
			try
			{
				processOK = process.processIt(pi, trx);
				trx.commit(true);
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
			}
			center.addElement(new p().addElement(pi.getSummary()));
			center.addElement(pi.getLogInfo(true));
		}
		
		//	Report
		if (processOK && process.isReport())
		{
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
		return doc;
	}	//	createProcessPage
	
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
		int AD_PInstance_ID, String fileName)
	{
		if (AD_PInstance_ID == 0)
			return "Your process not found";
	  	WebSessionCtx wsc = WebSessionCtx.get (request);
		Object value = wsc.ctx.get("AD_PInstance_ID=" + AD_PInstance_ID);
		if (value == null || !value.equals("ok"))
			return "Process Instance not found";
		//
		if (fileName == null || fileName.length() == 0)
			return "No Process Result";
		File file = new File (fileName);
		if (!file.exists())
			return "Process Result not found: " + file;
		//	OK
		return WebUtil.streamFile(response, file);
	}	//	streamResult
	
}   //  WProcess
