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
package org.compiere.web;

import java.io.*;
import java.lang.management.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.ecs.*;
import org.apache.ecs.xhtml.*;
import org.compiere.*;
import org.compiere.db.*;
import org.compiere.model.*;
import org.compiere.server.*;
import org.compiere.util.*;

/**
 *	Adempiere Server Monitor
 *	
 *  @author Jorg Janke
 *  @version $Id: AdempiereMonitor.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class AdempiereMonitor extends HttpServlet
{
	/**	Logger				*/
	private static CLogger	log = CLogger.getCLogger(AdempiereMonitor.class);
	/**	The Server			*/
	private AdempiereServerMgr	m_serverMgr = null;
	/** Message				*/
	private p				m_message = null;
	

	
	/**
	 * 	Get
	 *	@param request request
	 *	@param response response
	 *	@throws javax.servlet.ServletException
	 *	@throws java.io.IOException
	 */
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		m_message = null;
		if (processLogParameter (request, response))
			return;
		if (processTraceParameter (request, response))
			return;
		if (processEMailParameter (request, response))
			return;
		if (processCacheParameter (request, response))
			return;
		//
		if (processRunNowParameter (request))
			;
		else
			processActionParameter (request);
		createSummaryPage(request, response);
	}	//	doGet
	
	/**
	 * 	Post
	 *	@param request request
	 *	@param response response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		doGet(request, response);
	}	//	doPost

	/**
	 * 	Process Log Parameter and return log page
	 *	@param request request
	 *	@param response response
	 *	@return true if it was a log request
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private boolean processLogParameter (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String serverID = WebUtil.getParameter (request, "Log");
		if (serverID == null || serverID.length() == 0)
			return false;
		
		log.info ("ServerID=" + serverID);
		AdempiereServer server = m_serverMgr.getServer(serverID);
		if (server == null)
		{
			m_message = new p();
			m_message.addElement(new strong("Server not found: "));
			m_message.addElement(serverID);
			return false;
		}
		
		WebDoc doc = WebDoc.create ("Adempiere Server Monitor Log");
		//	Body
		body b = doc.getBody();
		//
		p para = new p();
		a link = new a ("adempiereMonitor#" + serverID, "Return");
		para.addElement(link);
		b.addElement(para);
		//
		b.addElement(new h2(server.getName()));
		//
		table table = new table();
		table.setBorder(1);
		table.setCellSpacing(2);
		table.setCellPadding(2);
		
		//	Header
		tr line = new tr();
		line.addElement(new th().addElement("Created"));
		line.addElement(new th().addElement("Summary"));
	//	line.addElement(new th().addElement("Error"));
		line.addElement(new th().addElement("Reference"));
		line.addElement(new th().addElement("TextMsg"));
	//	line.addElement(new th().addElement("Description"));
		table.addElement(line);
		
		AdempiereProcessorLog[] logs = server.getLogs();
		for (int i = 0; i < logs.length; i++)
		{
			AdempiereProcessorLog pLog = logs[i];
			line = new tr();
			line.addElement(new td().addElement(WebEnv.getCellContent(pLog.getCreated())));
			line.addElement(new td().addElement(WebEnv.getCellContent(pLog.getSummary())));
			line.addElement(new td().addElement(WebEnv.getCellContent(pLog.getReference())));
			line.addElement(new td().addElement(WebEnv.getCellContent(pLog.getTextMsg())));
			table.addElement(line);
		}
		//
		b.addElement(table);
		link = new a ("#top", "Top");
		b.addElement(link);
		
		//	fini
		WebUtil.createResponse (request, response, this, null, doc, false);
		return true;
	}	//	processLogParameter
	
	/**
	 * 	Process Run Parameter
	 *	@param request request
	 *	@return true if it was a Run request
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private boolean processRunNowParameter (HttpServletRequest request)
		throws ServletException, IOException
	{
		String serverID = WebUtil.getParameter (request, "RunNow");
		if (serverID == null || serverID.length() == 0)
			return false;
		
		log.info ("ServerID=" + serverID);
		AdempiereServer server = m_serverMgr.getServer(serverID);
		if (server == null)
		{
			m_message = new p();
			m_message.addElement(new strong("Server not found: "));
			m_message.addElement(serverID);
			return false;
		}
		//
		server.runNow();
		//
		return true;
	}	//	processRunParameter

	/**
	 * 	Process Action Parameter
	 *	@param request request
	 */
	private void processActionParameter (HttpServletRequest request)
	{
		String action = WebUtil.getParameter (request, "Action");
		if (action == null || action.length() == 0)
			return;
		log.info ("Action=" + action);
		try
		{
			boolean start = action.startsWith("Start");
			m_message = new p();
			String msg = (start ? "Started" : "Stopped") + ": ";
			m_message.addElement(new strong(msg));
			//
			String serverID = action.substring(action.indexOf('_')+1);
			boolean ok = false;
			if (serverID.equals("All"))
			{
				if (start)
					ok = m_serverMgr.startAll();
				else
					ok = m_serverMgr.stopAll();
				m_message.addElement("All");
			}
			else
			{
				AdempiereServer server = m_serverMgr.getServer(serverID);
				if (server == null)
				{
					m_message = new p();
					m_message.addElement(new strong("Server not found: "));
					m_message.addElement(serverID);
					return;
				}
				else
				{
					if (start)
						ok = m_serverMgr.start (serverID);
					else
						ok = m_serverMgr.stop (serverID);
					m_message.addElement(server.getName());
				}
			}
			m_message.addElement(ok ? " - OK" : " - Error!");
		}
		catch (Exception e)
		{
			m_message = new p();
			m_message.addElement(new strong("Error processing parameter: " + action));
			m_message.addElement(new br());
			m_message.addElement(e.toString());
		}
	}	//	processActionParameter

	/**
	 * 	Process Trace Parameter
	 *	@param request request
	 *	@param response response
	 *	@return true if it was a trace request with output
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private boolean processTraceParameter (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String traceCmd = WebUtil.getParameter (request, "Trace");
		String traceLevel = WebUtil.getParameter (request, "TraceLevel");
		if (traceLevel != null && traceLevel.length() > 0)
		{
			log.info ("New Level: " + traceLevel);
			CLogMgt.setLevel(traceLevel);
			Ini.setProperty(Ini.P_TRACELEVEL, traceLevel);
			Ini.saveProperties(false);
			return false;
		}
		
		if (traceCmd == null || traceCmd.length() == 0)
			return false;
		
		log.info ("Command: " + traceCmd);
		CLogFile fileHandler = CLogFile.get (false, null, false);
		//
		if (traceCmd.equals("ROTATE"))
		{
			if (fileHandler != null)
				fileHandler.rotateLog();
			return false;	//	re-display
		}
		else if (traceCmd.equals("DELETE"))
		{
			File logDir = fileHandler.getLogDirectory();
			if (logDir != null && logDir.isDirectory())
			{
				File[] logs = logDir.listFiles();
				for (int i = 0; i < logs.length; i++) 
				{
					String fileName = logs[i].getAbsolutePath();
					if (fileName.equals(fileHandler.getFileName()))
						continue;
					if (logs[i].delete())
						log.warning("Deleted: " + fileName);
					else
						log.warning("Not Deleted: " + fileName);
				}
			}
			return false;	//	re-display
		}
		
		//	Display current log File
		if (fileHandler != null && fileHandler.getFileName().equals(traceCmd))
			fileHandler.flush();
		
		//	Spool File
		File file = new File (traceCmd);
		if (!file.exists())
		{
			log.warning ("Did not find File: " + traceCmd);
			return false;
		}
		if (file.length() == 0)
		{
			log.warning ("File Length=0: " + traceCmd);
			return false;
		}
		
		//	Stream Log
		log.info ("Streaming: " + traceCmd);
		try
		{
			long time = System.currentTimeMillis();		//	timer start
			int fileLength = (int)file.length();
			int bufferSize = 2048; //	2k Buffer
			byte[] buffer = new byte[bufferSize];
			//
			response.setContentType("text/plain");
			response.setBufferSize(bufferSize);
			response.setContentLength(fileLength);
			//
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream out = response.getOutputStream ();
			int read = 0;
			while ((read = fis.read(buffer)) > 0)
				out.write (buffer, 0, read);
			out.flush();
			out.close();
			fis.close();
			//
			time = System.currentTimeMillis() - time;
			double speed = (fileLength/1024) / ((double)time/1000);
			log.info("length=" 
				+ fileLength + " - " 
				+ time + " ms - " 
				+ speed + " kB/sec");
		}
		catch (IOException ex)
		{
			log.log(Level.SEVERE, "stream" + ex);
		}
		return true;
	}	//	processTraceParameter
	
	/**
	 * 	Process EMail Parameter
	 *	@param request request
	 *	@param response response
	 *	@return true if it was a email request with output
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private boolean processEMailParameter (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String email = WebUtil.getParameter (request, "EMail");
		if (email == null || email.length() == 0)
			return false;
		
		int AD_Client_ID = -1;
		try
		{
			AD_Client_ID = Integer.parseInt(email);
		}
		catch (Exception e)
		{
			log.warning("Parsing: " + email + " - " + e.toString());
		}
		if (AD_Client_ID < 0)
		{
			m_message = new p();
			m_message.addElement("No EMail: " + email);
			return false;
		}
		
	//	log.info ("Test EMail: " + AD_Client_ID);
		MClient client = MClient.get(new Properties(), AD_Client_ID);
		log.info ("Test: " + client);
		
		m_message = new p();
		m_message.addElement(client.getName() + ": " + client.testEMail());
		return false;
	}	//	processEMailParameter
	

	/**
	 * 	Process Cache Parameter
	 *	@param request request
	 *	@param response response
	 *	@return true if it was a email request with output
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private boolean processCacheParameter (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		String cmd = WebUtil.getParameter (request, "CacheReset");
		if (cmd == null || cmd.length() == 0)
			return false;
		String tableName = WebUtil.getParameter (request, "CacheTableName");
		String record_ID = WebUtil.getParameter (request, "CacheRecord_ID");
		
		m_message = new p();
		try
		{
			if (tableName == null || tableName.length() == 0)
			{
				CacheMgt.get().reset();
				m_message.addElement("Cache Reset: All");
			}
			else if (record_ID == null || record_ID.length() == 0)
			{
				CacheMgt.get().reset(tableName);
				m_message.addElement("Cache Reset: " + tableName);
			}
			else
			{
				CacheMgt.get().reset(tableName, Integer.parseInt(record_ID));
				m_message.addElement("Cache Reset: " + tableName + ", Record_ID=" + record_ID);
			}
		}
		catch (Exception e)
		{
			log.severe(e.toString());
			m_message.addElement("Error: " + e.toString());
		}
		return false;	//	continue
	}	//	processEMailParameter

	/**************************************************************************
	 * 	Create & Return Summary Page
	 *	@param request request
	 *	@param response response
	 *	@throws ServletException
	 *	@throws IOException
	 */
	private void createSummaryPage (HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		WebDoc doc = WebDoc.create ("Adempiere Server Monitor");
	//	log.info("ServletConfig=" + getServletConfig());
	//	AdempiereServerGroup.get().dump();

		//	Body
		body bb = doc.getBody();
		//	Message
		if (m_message != null)
		{
			bb.addElement(new hr());
			bb.addElement(m_message);
			bb.addElement(new hr());
		}
		
		//	Summary
		table table = new table();
		table.setBorder(1);
		table.setCellSpacing(2);
		table.setCellPadding(2);
		//
		tr line = new tr();
		line.addElement(new th().addElement(Adempiere.getName()));
		line.addElement(new td().addElement(Adempiere.getVersion()));
		table.addElement(line);
		line = new tr();
		line.addElement(new th().addElement(Adempiere.getImplementationVendor()));
		line.addElement(new td().addElement(Adempiere.getImplementationVersion()));
		table.addElement(line);
		line = new tr();
		line.addElement(new th().addElement("Manager"));
		line.addElement(new td().addElement(WebEnv.getCellContent(m_serverMgr.getDescription())));
		table.addElement(line);
		line = new tr();
		line.addElement(new th().addElement("Start - Elapsed"));
		line.addElement(new td().addElement(WebEnv.getCellContent(m_serverMgr.getStartTime())
			+ " - " + TimeUtil.formatElapsed(m_serverMgr.getStartTime())));
		table.addElement(line);
		line = new tr();
		line.addElement(new th().addElement("Servers"));
		line.addElement(new td().addElement(WebEnv.getCellContent(m_serverMgr.getServerCount())));
		table.addElement(line);
		line = new tr();
		line.addElement(new th().addElement("Last Updated"));
		line.addElement(new td().addElement(new Timestamp(System.currentTimeMillis()).toString()));
		table.addElement(line);
		bb.addElement(table);
		//
		p para = new p();
		a link = new a ("adempiereMonitor?Action=Start_All", "Start All");
		para.addElement(link);
		para.addElement(" - ");
		link = new a ("adempiereMonitor?Action=Stop_All", "Stop All");
		para.addElement(link);
		para.addElement(" - ");
		link = new a ("adempiereMonitor", "Refresh");
		para.addElement(link);
		bb.addElement(para);
		
		//	***** Server Links *****
		bb.addElement(new hr());
		para = new p();
		AdempiereServer[] servers = m_serverMgr.getAll();		
		for (int i = 0; i < servers.length; i++)
		{
			if (i > 0)
				para.addElement(new br());
			AdempiereServer server = servers[i];
			link = new a ("#" + server.getServerID(), server.getName());
			para.addElement(link);
			font status = null;
			if (server.isAlive())
				status = new font().setColor(HtmlColor.GREEN).addElement(" (Running)");
			else
				status = new font().setColor(HtmlColor.RED).addElement(" (Stopped)");
			para.addElement(status);
		}
		bb.addElement(para);

		//	**** Log Management ****	
		createLogMgtPage(bb);	
		
		//	***** Server Details *****
		for (int i = 0; i < servers.length; i++)
		{
			AdempiereServer server = servers[i];
			bb.addElement(new hr());
			bb.addElement(new a().setName(server.getServerID()));
			bb.addElement(new h2(server.getName()));
			//
			table = new table();
			table.setBorder(1);
			table.setCellSpacing(2);
			table.setCellPadding(2);
			//	Status
			line = new tr();
			if (server.isAlive())
			{
				String msg = "Stop";
				if (server.isInterrupted())
					msg += " (Interrupted)";
				link = new a ("adempiereMonitor?Action=Stop_" + server.getServerID(), msg);
				if (server.isSleeping())
				{
					line.addElement(new th().addElement("Sleeping"));
					line.addElement(new td().addElement(link));
				}
				else
				{
					line.addElement(new th().addElement("Running"));
					line.addElement(new td().addElement(link));
				}
				table.addElement(line);
				line = new tr();
				line.addElement(new th().addElement("Start - Elapsed"));
				line.addElement(new td().addElement(WebEnv.getCellContent(server.getStartTime()) 
					+ " - " + TimeUtil.formatElapsed(server.getStartTime())));
			}
			else
			{
				String msg = "Start";
				if (server.isInterrupted())
					msg += " (Interrupted)";
				line.addElement(new th().addElement("Not Started"));
				link = new a ("adempiereMonitor?Action=Start_" + server.getServerID(), msg);
				line.addElement(new td().addElement(link));
			}
			table.addElement(line);
			//
			line = new tr();
			line.addElement(new th().addElement("Description"));
			line.addElement(new td().addElement(WebEnv.getCellContent(server.getDescription())));
			table.addElement(line);
			//
			line = new tr();
			line.addElement(new th().addElement("Last Run"));
			line.addElement(new td().addElement(WebEnv.getCellContent(server.getDateLastRun())));
			table.addElement(line);
			line = new tr();
			line.addElement(new th().addElement("Info"));
			line.addElement(new td().addElement(WebEnv.getCellContent(server.getServerInfo())));
			table.addElement(line);
			//
			line = new tr();
			line.addElement(new th().addElement("Next Run"));
			td td = new td();
			td.addElement(WebEnv.getCellContent(server.getDateNextRun(false)));
			td.addElement(" - ");
			link = new a ("adempiereMonitor?RunNow=" + server.getServerID(), "(Run Now)");
			td.addElement(link);
			line.addElement(td);
			table.addElement(line);
			//
			line = new tr();
			line.addElement(new th().addElement("Statistics"));
			line.addElement(new td().addElement(server.getStatistics()));
			table.addElement(line);
			//
			
			//	Add table to Body
			bb.addElement(table);
			link = new a ("#top", "Top");
			bb.addElement(link);
			bb.addElement(" - ");
			link = new a ("adempiereMonitor?Log=" + server.getServerID(), "Log");
			bb.addElement(link);
			bb.addElement(" - ");
			link = new a ("adempiereMonitor", "Refresh");
			bb.addElement(link);
		}

		//	fini
		WebUtil.createResponse (request, response, this, null, doc, false);
	}	//	createSummaryPage
	
	/**
	 * 	Add Log Management to page
	 *	@param bb body
	 */
	private void createLogMgtPage (body bb)
	{
		bb.addElement(new hr());
		
		//	Ini Parameters
		table table = new table();
		table.setBorder(1);
		table.setCellSpacing(2);
		table.setCellPadding(2);
		//
		Properties ctx = new Properties();
		MSystem system = MSystem.get(ctx);
		tr line = new tr();
		line.addElement(new th().addElement(system.getDBAddress()));
		line.addElement(new td().addElement(Ini.getAdempiereHome()));
		table.addElement(line);
		//	OS + Name
		line = new tr();
		String info = System.getProperty("os.name")
			+ " " + System.getProperty("os.version");
		String s = System.getProperty("sun.os.patch.level");
		if (s != null && s.length() > 0)
			info += " (" + s + ")";
		line.addElement(new th().addElement(info));
		info = system.getName();
		if (system.getCustomPrefix() != null)
			info += " (" + system.getCustomPrefix() + ")"; 
		line.addElement(new td().addElement(info));
		table.addElement(line);
		//	Java + email
		line = new tr();
		info = System.getProperty("java.vm.name")
			+ " " + System.getProperty("java.vm.version");
		line.addElement(new th().addElement(info));
		line.addElement(new td().addElement(system.getUserName()));
		table.addElement(line);
		//	DB + Instance
		line = new tr();
		CConnection cc = CConnection.get();
		AdempiereDatabase db = cc.getDatabase();
		info = db.getDescription();
		line.addElement(new th().addElement(info));
		line.addElement(new td().addElement(cc.getConnectionURL()));
//		line.addElement(new td().addElement(system.getDBInstance()));
		table.addElement(line);
		//	Processors/Support
		line = new tr();
		line.addElement(new th().addElement("Processor/Support"));
		line.addElement(new td().addElement(system.getNoProcessors() + "/" + system.getSupportUnits()));
		table.addElement(line);
		//	Memory
		line = new tr();
		MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
		line.addElement(new th().addElement("VM Memory"));
		line.addElement(new td().addElement(new CMemoryUsage(memory.getNonHeapMemoryUsage()).toString()));
		table.addElement(line);
		line = new tr();
		line.addElement(new th().addElement("Heap Memory"));
		line.addElement(new td().addElement(new CMemoryUsage(memory.getHeapMemoryUsage()).toString()));
		table.addElement(line);
		//	Runtime
		line = new tr();
		RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
		line.addElement(new th().addElement("Runtime " + rt.getName()));
		line.addElement(new td().addElement(TimeUtil.formatElapsed(rt.getUptime())));
		table.addElement(line);
		//	Threads
		line = new tr();
		ThreadMXBean th = ManagementFactory.getThreadMXBean();
		line.addElement(new th().addElement("Threads " + th.getThreadCount()));
		line.addElement(new td().addElement("Peak=" + th.getPeakThreadCount() 
			+ ", Demons=" + th.getDaemonThreadCount()
			+ ", Total=" + th.getTotalStartedThreadCount()));
		table.addElement(line);
		//	Cache Reset
		line = new tr();
		line.addElement(new th().addElement(CacheMgt.get().toStringX()));
		line.addElement(new td().addElement(new a ("adempiereMonitor?CacheReset=Yes", "Reset Cache")));
		table.addElement(line);
		
		//	Trace Level
		line = new tr();
		line.addElement(new th().addElement(new label("TraceLevel").addElement("Trace Log Level")));
		form myForm = new form("adempiereMonitor", form.METHOD_POST, form.ENC_DEFAULT);
		//	LogLevel Selection
		option[] options = new option[CLogMgt.LEVELS.length];
		for (int i = 0; i < options.length; i++) 
		{
			options[i] = new option(CLogMgt.LEVELS[i].getName());
			options[i].addElement(CLogMgt.LEVELS[i].getName());
			if (CLogMgt.LEVELS[i] == CLogMgt.getLevel())
				options[i].setSelected(true);
		}
		select sel = new select("TraceLevel", options);
		myForm.addElement(sel);
		myForm.addElement(new input(input.TYPE_SUBMIT, "Set", "Set"));
		line.addElement(new td().addElement(myForm));
		table.addElement(line);
		//
		line = new tr();
		CLogFile fileHandler = CLogFile.get (true, null, false);
		line.addElement(new th().addElement("Trace File"));
		line.addElement(new td().addElement(new a ("adempiereMonitor?Trace=" + fileHandler.getFileName(), "Current")));
		table.addElement(line);
		//
		line = new tr();
		line.addElement(new td().addElement(new a ("adempiereMonitor?Trace=ROTATE", "Rotate Trace Log")));
		line.addElement(new td().addElement(new a ("adempiereMonitor?Trace=DELETE", "Delete all Trace Logs")));
		table.addElement(line);
		//
		bb.addElement(table);
		
		//	List Log Files
		p p = new p();
		p.addElement(new b("All Log Files: "));
		//	All in dir
		File logDir = fileHandler.getLogDirectory();
		if (logDir != null && logDir.isDirectory())
		{
			File[] logs = logDir.listFiles();
			for (int i = 0; i < logs.length; i++) 
			{
				// Skip if is not a file - teo_sarca [ 1726066 ]
				if (!logs[i].isFile())
					continue;
				
				if (i != 0)
					p.addElement(" - ");
				String fileName = logs[i].getAbsolutePath();
				a link = new a ("adempiereMonitor?Trace=" + fileName, fileName);
				p.addElement(link);
				int size = (int)(logs[i].length()/1024);
				if (size < 1024)
					p.addElement(" (" + size + "k)");
				else
					p.addElement(" (" + size/1024 + "M)");
			}
		}
		bb.addElement(p);
		
		//	Clients and Web Stores
		table = new table();
		table.setBorder(1);
		table.setCellSpacing(2);
		table.setCellPadding(2);
		//	
		line = new tr();
		MClient[] clients = MClient.getAll(ctx);
		line.addElement(new th().addElement("Client #" + clients.length + " - EMail Test:"));
		p = new p();
		for (int i = 0; i < clients.length; i++)
		{
			MClient client = clients[i];
			if (i > 0)
				p.addElement(" - ");
			p.addElement(new a("adempiereMonitor?EMail=" + client.getAD_Client_ID(), client.getName()));
		}
		if (clients.length == 0)
			p.addElement("&nbsp;");
		line.addElement(new td().addElement(p));
		table.addElement(line);
		//	
		line = new tr();
		MStore[] wstores = MStore.getActive();
		line.addElement(new th().addElement("Active Web Stores #" + wstores.length));
		p = new p();
		for (int i = 0; i < wstores.length; i++)
		{
			MStore store = wstores[i];
			if (i > 0)
				p.addElement(" - ");
			a a = new a(store.getWebContext(), store.getName());
			a.setTarget("t" + i);
			p.addElement(a);
		}
		if (wstores.length == 0)
			p.addElement("&nbsp;");
		line.addElement(new td().addElement(p));
		table.addElement(line);
		//
		bb.addElement(table);
	}	//	createLogMgtPage
	
	/**************************************************************************
	 * 	Init
	 *	@param config config
	 *	@throws javax.servlet.ServletException
	 */
	public void init (ServletConfig config) throws ServletException
	{
		WebEnv.initWeb(config);
		log.info ("");
		m_serverMgr = AdempiereServerMgr.get();
	}	//	init
	
	/**
	 * 	Destroy
	 */
	public void destroy ()
	{
		log.info ("destroy");
		m_serverMgr = null;
	}	//	destroy
	
	/**
	 * 	Log error/warning
	 *	@param message message
	 *	@param e exception
	 */
	public void log (String message, Throwable e)
	{
		if (e == null)
			log.warning (message);
		log.log(Level.SEVERE, message, e);
	}	//	log
	
	/**
	 * 	Log debug
	 *	@param message message
	 */
	public void log (String message)
	{
		log.fine(message);
	}	//	log

	
	/**
	 * 	Get Servlet Name
	 *	@return servlet name
	 */
	public String getServletName ()
	{
		return "AdempiereMonitor";
	}	//	getServletName

	/**
	 * 	Get Servlet Info
	 *	@return servlet info
	 */
	public String getServletInfo ()
	{
		return "Adempiere Server Monitor";
	}	//	getServletName

}	//	AdempiereMonitor
