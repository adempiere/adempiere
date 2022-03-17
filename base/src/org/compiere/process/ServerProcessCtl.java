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
package org.compiere.process;

import java.io.InvalidClassException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.logging.Level;

import org.adempiere.util.ProcessUtil;
import org.compiere.db.CConnection;
import org.compiere.interfaces.Server;
import org.compiere.model.I_AD_Process;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRule;
import org.compiere.print.ServerReportCtl;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.wf.MWFProcess;

public class ServerProcessCtl implements Runnable {
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ServerProcessCtl.class);
	
	/** Parent */
	private ASyncProcess parentProcess;
	/** Process Info */
	private ProcessInfo processInstance;
	private Trx transaction;
	private boolean isServerProcess = false;
	
	/**************************************************************************
	 *  Constructor
	 *  @param parent Container & ASyncProcess
	 *  @param pi Process info
	 *  @param trx Transaction
	 */
	public ServerProcessCtl (ASyncProcess parent, ProcessInfo pi, Trx trx) {
		parentProcess = parent;
		processInstance = pi;
		this.transaction = trx;	//	handled correctly
	}   //  ProcessCtl

	/**
	 *	Process Control
	 *  <code>
	 *	- Get Instance ID
	 *	- Get Parameters
	 *	- execute (lock - start process - unlock)
	 *  </code>
	 *  Creates a ProcessCtl instance, which calls
	 *  lockUI and unlockUI if parent is a ASyncProcess
	 *  <br>
	 *	Called from APanel.cmd_print, APanel.actionButton and
	 *  VPaySelect.cmd_generate
	 *
	 *  @param parent ASyncProcess & Container
	 *  @param processInfo ProcessInfo process info
	 *  @param trx Transaction
	 *  @return worker started ProcessCtl instance or null for workflow
	 */
	public static ServerProcessCtl process (ASyncProcess parent, ProcessInfo processInfo, Trx trx) {
		log.fine("ServerProcess - " + processInfo);

		MPInstance instance = null; 
		try  { 
			instance = new MPInstance(Env.getCtx(), processInfo.getAD_Process_ID(), processInfo.getRecord_ID()); 
		}  catch (Exception e) { 
			processInfo.setSummary (e.getLocalizedMessage()); 
			processInfo.setError (true); 
			log.warning(processInfo.toString()); 
			return null; 
		} 
		catch (Error e) { 
			processInfo.setSummary (e.getLocalizedMessage()); 
			processInfo.setError (true); 
			log.warning(processInfo.toString()); 
			return null; 
		}
		if (instance != null 
				&& !instance.save()) {
			processInfo.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			processInfo.setError (true);
			return null;
		}
		processInfo.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	execute
		ServerProcessCtl worker = new ServerProcessCtl(parent, processInfo, trx);
		if (parent != null) {
			//asynchrous
			worker.start();
		} else {
			//synchrous
			worker.run();
		}
		return worker;
	}	//	execute

	/**
	 * Run this process in a new thread
	 */
	public void start()
	{
		Thread thread = new Thread(this);
		// Set thread name
		if (processInstance != null)
			thread.setName(processInstance.getTitle()+"-"+ processInstance.getAD_PInstance_ID());
		thread.start();
	}

	/**
	 *	Execute Process Instance and Lock UI.
	 *  Calls lockUI and unlockUI if parent is a ASyncProcess
	 *  <pre>
	 *		- Get Process Information
	 *      - Call Class
	 *		- Submit SQL Procedure
	 *		- Run SQL Procedure
	 *	</pre>
	 */
	public void run () {
		log.fine("AD_PInstance_ID=" + processInstance.getAD_PInstance_ID()
			+ ", Record_ID=" + processInstance.getRecord_ID());

		//	Get Process Information: Name, Procedure Name, ClassName, IsReport, IsDirectPrint
		String procedureName = "";
		//	Get Process data
		MProcess process = null;
		//	
		if(processInstance.getAD_Process_ID() != 0) {
			process = MProcess.get(Env.getCtx(), processInstance.getAD_Process_ID());
		} else {
			process = MProcess.getFromInstance(Env.getCtx(), processInstance.getAD_PInstance_ID());
			processInstance.setAD_Process_ID(process.getAD_Process_ID());
		}
		//	
		if(process.getAD_Process_ID() <= 0) {
			processInstance.setSummary (Msg.parseTranslation(Env.getCtx(), "@AD_Process_ID@ @NotFound@"), true);
			log.log(Level.SEVERE, "run", "AD_Process_ID=" + processInstance.getAD_Process_ID() + " Not Found");
			return;
		}
		//	Set values from process
		processInstance.setTitle (process.get_Translation(I_AD_Process.COLUMNNAME_Name));
		procedureName = process.getProcedureName();
		processInstance.setClassName(process.getClassname());
		//
		int estimate = process.getEstimatedSeconds();
		if (estimate != 0) {
			processInstance.setEstSeconds (estimate + 1);     //  admin overhead
		}
		isServerProcess = process.isServerProcess();
		//  No PL/SQL Procedure
		if (procedureName == null)
			procedureName = "";
		
		/**********************************************************************
		 *	Workflow
		 */
		if (process.isWorkflow())	
		{
			startWorkflow (process.getAD_Workflow_ID());
			return;
		}

		// Clear Jasper Report class if default - to be executed later
		if (process.isJasper()) {
			if (ProcessUtil.JASPER_STARTER_CLASS.equals(processInstance.getClassName())) {
				processInstance.setClassName(null);
			}
		}
		/**********************************************************************
		 *	Start Optional Class
		 */
		if (processInstance.getClassName() != null) {
			if (process.isJasper()) {
				processInstance.setReportingProcess(true);
			}
			//	Run Class
			if (!startProcess()) {
				return;
			}

			//  No Optional SQL procedure ... done
			if (!process.isReport() && procedureName.length() == 0) {
				return;
			}
			//  No Optional Report ... done
			if (process.isReport() && process.getAD_ReportView_ID() == 0 && ! process.isJasper()) {
				return;
			}
		}

		/**********************************************************************
		 *	Report submission
		 */
		//	Optional Pre-Report Process
		if (process.isReport() && procedureName.length() > 0) {	
			processInstance.setReportingProcess(true);
			if (!startDBProcess(procedureName)) {
				return;
			}
		}	//	Pre-Report

		if (process.isJasper()) {
			processInstance.setReportingProcess(true);
			processInstance.setClassName(ProcessUtil.JASPER_STARTER_CLASS);
			startProcess();
			return;
		}
		
		if (process.isReport()) {
			processInstance.setReportingProcess(true);
			boolean ok = ServerReportCtl.start(parentProcess, processInstance);
			processInstance.setSummary("Report", !ok);
		}
		/**********************************************************************
		 * 	Process submission
		 */
		else {
			if (procedureName.length() > 0 
					&& !startDBProcess (procedureName)) {
				return;
			}
			//	Success - getResult
			ProcessInfoUtil.setSummaryFromDB(processInstance);
		}
	}   //  run
	
	/**************************************************************************
	 *  Start Workflow.
	 *
	 *  @param AD_Workflow_ID workflow
	 *  @return     true if started
	 */
	protected boolean startWorkflow (int AD_Workflow_ID)
	{
		log.fine(AD_Workflow_ID + " - " + processInstance);
		boolean started = false;
		if (isServerProcess)
		{
			try
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{	//	See ServerBean
					processInstance = server.workflow (Env.getRemoteCallCtx(Env.getCtx()), processInstance, AD_Workflow_ID);
					log.finest("server => " + processInstance);
					started = true;
				}
			}
			catch (Exception ex)
			{
				log.log(Level.SEVERE, "AppsServer error", ex);
				started = false;
			}
		}
		//	Run locally
		if (!started && !isServerProcess)
		{
			if (transaction != null)
				processInstance.setTransactionName(transaction.getTrxName());
			MWFProcess wfProcess = ProcessUtil.startWorkFlow(Env.getCtx(), processInstance, AD_Workflow_ID);
			started = wfProcess != null;
		}
		return started;
	}   //  startWorkflow

	/**************************************************************************
	 *  Start Java Process Class.
	 *      instanciate the class implementing the interface ProcessCall.
	 *  The class can be a Server/Client class (when in Package
	 *  org adempiere.process or org.compiere.model) or a client only class
	 *  (e.g. in org.compiere.report)
	 *
	 *  @return     true if success
	 */
	protected boolean startProcess ()
	{
		log.fine(processInstance.toString());
		boolean started = false;
		
		//hengsin, bug [ 1633995 ]
		boolean clientOnly = false;
		if (! processInstance.getClassName().toLowerCase().startsWith(MRule.SCRIPT_PREFIX)) {
			try {
				Class<?> processClass = Class.forName(processInstance.getClassName());
				if (ClientProcess.class.isAssignableFrom(processClass))
					clientOnly = true;
			} catch (Exception e) {}
		}
		
		if (isServerProcess && !clientOnly)
		{
			try
			{
				Server server = CConnection.get().getServer();
				if (server != null)
				{	
					//	See ServerBean
					processInstance = server.process (Env.getRemoteCallCtx(Env.getCtx()), processInstance);
					log.finest("server => " + processInstance);
					started = true;		
				}
			}
			catch (UndeclaredThrowableException ex)
			{
				Throwable cause = ex.getCause();
				if (cause != null)
				{
					if (cause instanceof InvalidClassException)
						log.log(Level.SEVERE, "Version Server <> Client: " 
							+  cause.toString() + " - " + processInstance, ex);
					else
						log.log(Level.SEVERE, "AppsServer error(1b): " 
							+ cause.toString() + " - " + processInstance, ex);
				}
				else
					log.log(Level.SEVERE, " AppsServer error(1) - " 
						+ processInstance, ex);
				started = false;
			}
			catch (Exception ex)
			{
				Throwable cause = ex.getCause();
				if (cause == null)
					cause = ex;
				log.log(Level.SEVERE, "AppsServer error - " + processInstance, cause);
				started = false;
			}
		}
		//	Run locally
		if (!started && (!isServerProcess || clientOnly ))
		{
			if (processInstance.getClassName().toLowerCase().startsWith(MRule.SCRIPT_PREFIX)) {
				return ProcessUtil.startScriptProcess(Env.getCtx(), processInstance, transaction);
			} else {
				if (processInstance.isManagedTransaction())
					return ProcessUtil.startJavaProcess(Env.getCtx(), processInstance, transaction);
				else
					return ProcessUtil.startJavaProcess(Env.getCtx(), processInstance, transaction, processInstance.isManagedTransaction());
			}
		}
		return !processInstance.isError();
	}   //  startProcess


	/**************************************************************************
	 *  Start Database Process
	 *  @param ProcedureName PL/SQL procedure name
	 *  @return true if success
	 */
	protected boolean startDBProcess (String ProcedureName)
	{
		//  execute on this thread/connection
		log.fine(ProcedureName + "(" + processInstance.getAD_PInstance_ID() + ")");
		boolean started = false;
		if (isServerProcess) {
			try {
				Server server = CConnection.get().getServer();
				if (server != null) {	//	See ServerBean
					processInstance = server.dbProcess(processInstance, ProcedureName);
					log.finest("server => " + processInstance);
					started = true;		
				}
			} catch (UndeclaredThrowableException ex) {
				Throwable cause = ex.getCause();
				if (cause != null) {
					if (cause instanceof InvalidClassException)
						log.log(Level.SEVERE, "Version Server <> Client: " 
							+  cause.toString() + " - " + processInstance, ex);
					else
						log.log(Level.SEVERE, "AppsServer error(1b): " 
							+ cause.toString() + " - " + processInstance, ex);
				} else {
					log.log(Level.SEVERE, " AppsServer error(1) - " 
						+ processInstance, ex);
					cause = ex;
				}
				processInstance.setSummary (Msg.getMsg(Env.getCtx(), "ProcessRunError") + " " + cause.getLocalizedMessage());
				processInstance.setError (true);
				return false;
			} catch (Exception ex) {
				Throwable cause = ex.getCause();
				if (cause == null)
					cause = ex;
				log.log(Level.SEVERE, "AppsServer error - " + processInstance, cause);
				processInstance.setSummary (Msg.getMsg(Env.getCtx(), "ProcessRunError") + " " + cause.getLocalizedMessage());
				processInstance.setError (true);
				return false;
			}
		}
		
		//try locally
		if (!started) {
			if (processInstance.isManagedTransaction())
				return ProcessUtil.startDatabaseProcedure(processInstance, ProcedureName, transaction);
			else
				return ProcessUtil.startDatabaseProcedure(processInstance, ProcedureName, transaction, processInstance.isManagedTransaction());

		}
		return true;
	}   //  startDBProcess
	

}
