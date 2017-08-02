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
package org.compiere.apps;

import java.awt.Container;
import java.io.InvalidClassException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.adempiere.util.ProcessUtil;
import org.compiere.db.CConnection;
import org.compiere.interfaces.Server;
import org.compiere.model.I_AD_Process;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRule;
import org.compiere.print.ReportCtl;
import org.compiere.process.ClientProcess;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.ASyncProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.wf.MWFProcess;

/**
 *	Process Interface Controller.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: ProcessCtl.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 *  @author Low Heng Sin
 *  - Added support for having description and parameter in one dialog
 *  - Added support to run db process remotely on server
 * 
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 				<li>BF [ 1757523 ] Server Processes are using Server's context
 * 				<li>FR [ 1807922 ] Pocess threads should have a better name
 * 				<li>BF [ 1960523 ] Server Process functionality not working
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *				<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *				@see https://github.com/adempiere/adempiere/issues/265
 *				<li>FR [ 295 ] Report viewer re-query
 *				@see https://github.com/adempiere/adempiere/issues/295
 *				<li>FR [ 352 ] T_Selection is better send to process like a HashMap instead read from disk
 *				@see https://github.com/adempiere/adempiere/issues/352
 *				<a href="https://github.com/adempiere/adempiere/issues/682">
 * 				@see FR [ 682 ] Use Model Class for ProcessCtl</a>
 */
public class ProcessCtl implements Runnable
{


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
	 *  @param WindowNo window no
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 *  @return worker started ProcessCtl instance or null for workflow
	 */
	public static ProcessCtl process (ASyncProcess parent, int WindowNo, ProcessInfo pi, Trx trx)
	{
		log.fine("WindowNo=" + WindowNo + " - " + pi);

		MPInstance instance = null; 
		try 
		{ 
			instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID()); 
		} 
		catch (Exception e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		} 
		catch (Error e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		}
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
			return null;
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters (Dialog)
		//	FR [ 265 ]
		//	Change to Standard Process Modal Dialog
		ProcessModalDialog para = new ProcessModalDialog(Env.getFrame((Container)parent), WindowNo, pi);
		if (para.isValidDialog()) {
			para.validate();
			para.pack();
			AEnv.showCenterWindow(Env.getWindow(WindowNo), para);
			if (!para.isOK()) {
				return null;
			}
		}

		//	execute
		ProcessCtl worker = new ProcessCtl(parent, WindowNo, pi, trx);
		if (parent != null)
		{
			//asynchrous
			worker.start();
		}
		else
		{
			//synchrous
			worker.run();
		}
		return worker;
	}	//	execute

	/**
	 *	Async Process - Do it all.
	 *  <code>
	 *	- Get Instance ID
	 *	- Get Parameters
	 *	- execute (lock - start process - unlock)
	 *  </code>
	 *  Creates a ProcessCtl instance, which calls
	 *  lockUI and unlockUI if parent is a ASyncProcess
	 *  <br>
	 *	Called from ProcessDialog.actionPerformed
	 *
	 *  @param parent ASyncProcess & Container
	 *  @param WindowNo window no
	 *  @param paraPanel Process Parameter Panel
	 *  @param pi ProcessInfo process info
	 *  @param trx Transaction
	 *  @return worker started ProcessCtl instance or null for workflow
	 */
	public static ProcessCtl process(ASyncProcess parent, int WindowNo, ProcessController parameter, ProcessInfo pi, Trx trx)
	{
		log.fine("WindowNo=" + WindowNo + " - " + pi);

		MPInstance instance = null; 
		try 
		{ 
			instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID()); 
		} 
		catch (Exception e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		} 
		catch (Error e) 
		{ 
			pi.setSummary (e.getLocalizedMessage()); 
			pi.setError (true); 
			log.warning(pi.toString()); 
			return null; 
		}
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
			return null;
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters
		//	BR [ 265 ]
		if (parameter != null) {
			if (parameter.saveParameters() != null) {
				return null;
			}
		}

		//	execute
		ProcessCtl worker = new ProcessCtl(parent, WindowNo, pi, trx);
		if (parent != null)
		{
			worker.start();
		}
		else
		{
			//synchrous
			worker.run();
		}
		return worker;
	}	//	execute
	
	/**
	 * Used for re-query report
	 * @param parent
	 * @param WindowNo
	 * @param pi
	 * @param isOnlyProcess
	 * @param trx
	 */
	public ProcessCtl (ASyncProcess parent, int WindowNo, ProcessInfo pi, boolean isOnlyProcess, Trx trx)
	{
		windowno = WindowNo;
		m_parent = parent;
		processInstance = pi;
		m_trx = trx;	//	handeled correctly
		m_IsOnlyProcess = isOnlyProcess;
	}   //  ProcessCtl
	
	/**************************************************************************
	 *  Constructor
	 *  @param parent Container & ASyncProcess
	 *  @param pi Process info
	 *  @param trx Transaction
	 *  Created in process(), VInvoiceGen.generateInvoices
	 *  FR [ 295 ]
	 *  Add parameter for run only process, not run report
	 */
	public ProcessCtl (ASyncProcess parent, int WindowNo, ProcessInfo pi, Trx trx) {
		this(parent, WindowNo, pi, false, trx);
	}

	/** Windowno */
	int windowno;
	/** Parenr */
	ASyncProcess m_parent;
	/** Process Info */
	ProcessInfo processInstance;
	private Trx				m_trx;
	private Waiting         waiting;
	private boolean 		isServerProcess = false;
	//	FR [ 295 ]
	private boolean			m_IsOnlyProcess = false;
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ProcessCtl.class);
	
	/**
	 * Run this process in a new thread
	 */
	public void start()
	{
		Thread thread = new Thread(this);
		// Set thread name - teo_sarca FR [ 1807922 ]
		if (processInstance != null)
			thread.setName(processInstance.getTitle()+"-"+processInstance.getAD_PInstance_ID());
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

		//  Lock
		lock();
		//	Get Process Information: Name, Procedure Name, ClassName, IsReport, IsDirectPrint
		String procedureName = "";
		boolean	isDirectPrint = false;
		boolean isPrintPreview = processInstance.isPrintPreview();
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
			unlock();
			log.log(Level.SEVERE, "run", "AD_Process_ID=" + processInstance.getAD_Process_ID() + " Not Found");
			return;
		}
		//	Set values from process
		processInstance.setTitle (process.get_Translation(I_AD_Process.COLUMNNAME_Name));
		if (waiting != null)
			waiting.setTitle(processInstance.getTitle());
		procedureName = process.getProcedureName();
		processInstance.setClassName(process.getClassname());
		//	Report
		if (process.isReport()) {
			if (process.isDirectPrint() && !Ini.isPropertyBool(Ini.P_PRINTPREVIEW)
					&& !isPrintPreview )
				isDirectPrint = true;
		}
		//
		int estimate = process.getEstimatedSeconds();
		if (estimate != 0) {
			processInstance.setEstSeconds (estimate + 1);     //  admin overhead
			if (waiting != null)
				waiting.setTimerEstimate(processInstance.getEstSeconds());
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
			unlock();
			return;
		}

		// Clear Jasper Report class if default - to be executed later
		if (process.isJasper()) {
			if (ProcessUtil.JASPER_STARTER_CLASS.equals(processInstance.getClassName())) {
				processInstance.setClassName(null);
			}
		}
		//	Save selection
		//	FR [ 352 ]
		//	if is from a selection then save all record in DB
		saveSelection();
		
		/**********************************************************************
		 *	Start Optional Class
		 */
		if (processInstance.getClassName() != null) {
			if (process.isJasper()) {
				processInstance.setReportingProcess(true);
			}

			//	Run Class
			if (!startProcess()) {
				unlock();
				return;
			}

			//  No Optional SQL procedure ... done
			if (!process.isReport() && procedureName.length() == 0) {
				unlock ();
				return;
			}
			//  No Optional Report ... done
			if (process.isReport() && process.getAD_ReportView_ID() == 0 && ! process.isJasper()) {
				unlock ();
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
				unlock();
				return;
			}
		}	//	Pre-Report

		if (process.isJasper()) {
			processInstance.setReportingProcess(true);
			processInstance.setClassName(ProcessUtil.JASPER_STARTER_CLASS);
			startProcess();
			unlock();
			return;
		}
		
		if (process.isReport()) {
			processInstance.setReportingProcess(true);
			if(!m_IsOnlyProcess) {
				//	Start Report	-----------------------------------------------
				boolean ok = ReportCtl.start(m_parent, windowno, processInstance, isDirectPrint);
				processInstance.setSummary("Report", !ok);
			}
			//	
			unlock ();
		}
		/**********************************************************************
		 * 	Process submission
		 */
		else {
			if (procedureName.length() > 0 
					&& !startDBProcess (procedureName)) {
				unlock();
				return;
			}
			//	Success - getResult
			ProcessInfoUtil.setSummaryFromDB(processInstance);
			unlock();
		}			//	*** Process submission ***
	//	log.fine(Log.l3_Util, "ProcessCtl.run - done");
	}   //  run

	/**
	 *  Lock UI & show Waiting
	 */
	private void lock ()
	{
	//	log.info("...");
		//m_parent is null for synchrous execution
		if (m_parent != null)
		{
			if (m_parent instanceof Container)
			{
				//swing client
				JFrame frame = Env.getFrame((Container)m_parent);
				if (frame instanceof AWindow)
					((AWindow)frame).setBusyTimer(processInstance.getEstSeconds());
				else
					waiting = new Waiting (frame, Msg.getMsg(Env.getCtx(), "Processing"), false, processInstance.getEstSeconds());
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						log.finer("lock");
						m_parent.lockUI(processInstance);
					}
				});
				if (waiting != null)
				{
					waiting.toFront();
					waiting.setVisible(true);
				}
			}
			else
			{
				//other client
				log.finer("lock");
				m_parent.lockUI(processInstance);
			}
		}
	}   //  lock

	/**
	 *  Unlock UI & dispose Waiting.
	 * 	Called from run()
	 */
	private void unlock ()
	{
	//	log.info("...");
		if (processInstance.isBatch())
			processInstance.setIsTimeout(true);
		if (m_parent != null)
		{
			if (m_parent instanceof Container)
			{
				//	Remove Waiting/Processing Indicator
				if (waiting != null)
					waiting.dispose();
				waiting = null;
				//swing client
				SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						String summary = processInstance.getSummary();
						log.finer("unlock - " + summary);
						if (summary != null && summary.indexOf('@') != -1)
							processInstance.setSummary(Msg.parseTranslation(Env.getCtx(), summary));
						m_parent.unlockUI(processInstance);
					}
				});
			}
			else
			{
				//other client
				m_parent.unlockUI(processInstance);
			}
		}
	}   //  unlock

	
	/**************************************************************************
	 *  Start Workflow.
	 *
	 *  @param AD_Workflow_ID workflow
	 *  @return     true if started
	 */
	private boolean startWorkflow (int AD_Workflow_ID)
	{
		log.fine(AD_Workflow_ID + " - " + processInstance);
		boolean started = false;
		if (isServerProcess)
		{
			Server server = CConnection.get().getServer();
			try
			{
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
			if (m_trx != null)
				processInstance.setTransactionName(m_trx.getTrxName());
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
	private boolean startProcess ()
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
			Server server = CConnection.get().getServer();
			try
			{
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
				return ProcessUtil.startScriptProcess(Env.getCtx(), processInstance, m_trx);
			} else {
				if (processInstance.isManagedTransaction())
					return ProcessUtil.startJavaProcess(Env.getCtx(), processInstance, m_trx);
				else
					return ProcessUtil.startJavaProcess(Env.getCtx(), processInstance, m_trx, processInstance.isManagedTransaction());
			}
		}
		return !processInstance.isError();
	}   //  startProcess


	/**************************************************************************
	 *  Start Database Process
	 *  @param ProcedureName PL/SQL procedure name
	 *  @return true if success
	 */
	private boolean startDBProcess (String ProcedureName)
	{
		//  execute on this thread/connection
		log.fine(ProcedureName + "(" + processInstance.getAD_PInstance_ID() + ")");
		boolean started = false;
		if (isServerProcess)
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					processInstance = server.dbProcess(processInstance, ProcedureName);
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
				{
					log.log(Level.SEVERE, " AppsServer error(1) - " 
						+ processInstance, ex);
					cause = ex;
				}
				processInstance.setSummary (Msg.getMsg(Env.getCtx(), "ProcessRunError") + " " + cause.getLocalizedMessage());
				processInstance.setError (true);
				return false;
			}
			catch (Exception ex)
			{
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
		if (!started)
		{
			if (processInstance.isManagedTransaction())
				return ProcessUtil.startDatabaseProcedure(processInstance, ProcedureName, m_trx);
			else
				return  ProcessUtil.startDatabaseProcedure(processInstance , ProcedureName , m_trx , processInstance.isManagedTransaction());
		}
	//	log.fine(Log.l4_Data, "ProcessCtl.startProcess - done");
		return true;
	}   //  startDBProcess

	/**
	 * Save selection when process is called with selection
	 */
	private void saveSelection() {
		if(processInstance.isSelection()) {
			if(processInstance.getSelectionKeys() != null) {
				//	Create Selection
				DB.createT_Selection(processInstance.getAD_PInstance_ID(), processInstance.getSelectionKeys(), processInstance.getTransactionName());
				if(processInstance.getSelectionValues() != null) {
					//	Create Selection for SB
					DB.createT_Selection_Browse(processInstance.getAD_PInstance_ID(), processInstance.getSelectionValues(), processInstance.getTransactionName());
				}
			} 
		}
	}
	
}	//	ProcessCtl
