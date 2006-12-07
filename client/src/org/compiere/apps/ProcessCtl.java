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
package org.compiere.apps;

import java.awt.*;
import java.io.*;
import java.lang.reflect.*;
import java.rmi.*;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import org.compiere.db.*;
import org.compiere.interfaces.*;
import org.compiere.model.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.util.*;
import org.compiere.wf.*;

/**
 *	Process Interface Controller.
 *
 *  @author 	Jorg Janke
 *  @version 	$Id: ProcessCtl.java,v 1.2 2006/07/30 00:51:27 jjanke Exp $
 */
public class ProcessCtl extends Thread
{
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
	 *	Called from ProcessCtl.startProcess, ProcessDialog.actionPerformed,
	 *  APanel.cmd_print, APanel.actionButton, VPaySelect.cmd_generate
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

		MPInstance instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID());
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
			return null;
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters (Dialog)
		ProcessParameter para = new ProcessParameter (Env.getFrame((Container)parent), WindowNo, pi);
		if (para.initDialog())
		{
			para.setVisible(true);
			if (!para.isOK())
			{
				pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessCancelled"));
				pi.setError (true);
				return null;
			}
		}

		//	execute
		ProcessCtl worker = new ProcessCtl(parent, pi, trx);
		worker.start();		//	MUST be start!
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
	public static ProcessCtl process(ASyncProcess parent, int WindowNo, ProcessParameterPanel paraPanel, ProcessInfo pi, Trx trx)
	{
		log.fine("WindowNo=" + WindowNo + " - " + pi);

		MPInstance instance = new MPInstance(Env.getCtx(), pi.getAD_Process_ID(), pi.getRecord_ID());
		if (!instance.save())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoInstance"));
			pi.setError (true);
			return null;
		}
		pi.setAD_PInstance_ID (instance.getAD_PInstance_ID());

		//	Get Parameters
		if (!paraPanel.saveParameters())
		{
			pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessCancelled"));
			pi.setError (true);
			return null;
		}

		//	execute
		ProcessCtl worker = new ProcessCtl(parent, pi, trx);
		worker.start();		//	MUST be start!
		return worker;
	}	//	execute


	
	/**************************************************************************
	 *  Constructor
	 *  @param parent Container & ASyncProcess
	 *  @param pi Process info
	 *  @param trx Transaction
	 *  Created in process(), VInvoiceGen.generateInvoices
	 */
	public ProcessCtl (ASyncProcess parent, ProcessInfo pi, Trx trx)
	{
		m_parent = parent;
		m_pi = pi;
		m_trx = trx;	//	handeled correctly
	}   //  ProcessCtl

	private ASyncProcess    m_parent;
	private ProcessInfo     m_pi;
	private Trx				m_trx;
	private Waiting         m_waiting;
	private boolean 		m_IsServerProcess = false;
	
	/**	Static Logger	*/
	private static CLogger	log	= CLogger.getCLogger (ProcessCtl.class);
	
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
	public void run ()
	{
		log.fine("AD_PInstance_ID=" + m_pi.getAD_PInstance_ID()
			+ ", Record_ID=" + m_pi.getRecord_ID());

		//  Lock
		lock();
	//	try {System.out.println(">> sleeping ..");sleep(20000);System.out.println(".. sleeping <<");} catch (Exception e) {}

		//	Get Process Information: Name, Procedure Name, ClassName, IsReport, IsDirectPrint
		String 	ProcedureName = "";
		int     AD_ReportView_ID = 0;
		int		AD_Workflow_ID = 0;
		boolean IsReport = false;
		boolean	IsDirectPrint = false;
		//
		String sql = "SELECT p.Name, p.ProcedureName,p.ClassName, p.AD_Process_ID,"		//	1..4
			+ " p.isReport,p.IsDirectPrint,p.AD_ReportView_ID,p.AD_Workflow_ID,"		//	5..8
			+ " CASE WHEN COALESCE(p.Statistic_Count,0)=0 THEN 0 ELSE p.Statistic_Seconds/p.Statistic_Count END CASE,"
			+ " p.IsServerProcess " 
			+ "FROM AD_Process p"
			+ " INNER JOIN AD_PInstance i ON (p.AD_Process_ID=i.AD_Process_ID) "
			+ "WHERE p.IsActive='Y'"
			+ " AND i.AD_PInstance_ID=?";
		if (!Env.isBaseLanguage(Env.getCtx(), "AD_Process"))
			sql = "SELECT t.Name, p.ProcedureName,p.ClassName, p.AD_Process_ID,"		//	1..4
				+ " p.isReport, p.IsDirectPrint,p.AD_ReportView_ID,p.AD_Workflow_ID,"	//	5..8
				+ " CASE WHEN COALESCE(p.Statistic_Count,0)=0 THEN 0 ELSE p.Statistic_Seconds/p.Statistic_Count END CASE,"
				+ " p.IsServerProcess "
				+ "FROM AD_Process p"
				+ " INNER JOIN AD_PInstance i ON (p.AD_Process_ID=i.AD_Process_ID) "
				+ " INNER JOIN AD_Process_Trl t ON (p.AD_Process_ID=t.AD_Process_ID"
					+ " AND t.AD_Language='" + Env.getAD_Language(Env.getCtx()) + "') "
				+ "WHERE p.IsActive='Y'"
				+ " AND i.AD_PInstance_ID=?";
		//
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, 
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, null);
			pstmt.setInt(1, m_pi.getAD_PInstance_ID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_pi.setTitle (rs.getString(1));
				if (m_waiting != null)
					m_waiting.setTitle(m_pi.getTitle());
				ProcedureName = rs.getString(2);
				m_pi.setClassName (rs.getString(3));
				m_pi.setAD_Process_ID (rs.getInt(4));
				//	Report
				if ("Y".equals(rs.getString(5)))
				{
					IsReport = true;
					if ("Y".equals(rs.getString(6)) && !Ini.isPropertyBool(Ini.P_PRINTPREVIEW))
						IsDirectPrint = true;
				}
				AD_ReportView_ID = rs.getInt(7);
				AD_Workflow_ID = rs.getInt(8);
				//
				int estimate = rs.getInt(9);
				if (estimate != 0)
				{
					m_pi.setEstSeconds (estimate + 1);     //  admin overhead
					if (m_waiting != null)
						m_waiting.setTimerEstimate(m_pi.getEstSeconds());
				}
				m_IsServerProcess = "Y".equals(rs.getString(10));
			}
			else
				log.log(Level.SEVERE, "No AD_PInstance_ID=" + m_pi.getAD_PInstance_ID());
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			m_pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoProcedure") + " " + e.getLocalizedMessage(), true);
			unlock();
			log.log(Level.SEVERE, "run", e);
			return;
		}

		//  No PL/SQL Procedure
		if (ProcedureName == null)
			ProcedureName = "";

		
		/**********************************************************************
		 *	Workflow
		 */
		if (AD_Workflow_ID > 0)	
		{
			startWorkflow (AD_Workflow_ID);
			unlock();
			return;
		}

		/**********************************************************************
		 *	Start Optional Class
		 */
		if (m_pi.getClassName() != null)
		{
			//	Run Class
			if (!startProcess())
			{
				unlock();
				return;
			}

			//  No Optional SQL procedure ... done
			if (!IsReport  && ProcedureName.length() == 0)
			{
				unlock ();
				return;
			}
			//  No Optional Report ... done
			if (IsReport && AD_ReportView_ID == 0)
			{
				unlock ();
				return;
			}
		}

		//  If not a report, we need a prodedure name
		if (!IsReport && ProcedureName.length() == 0)
		{
			m_pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessNoProcedure"), true);
			unlock();
			return;
		}

		/**********************************************************************
		 *	Report submission
		 */
		if (IsReport)
		{
			//	Optional Pre-Report Process
			if (ProcedureName.length() > 0)
			{
				if (!startDBProcess(ProcedureName))
				{
					unlock();
					return;
				}
			}	//	Pre-Report

			//	Start Report	-----------------------------------------------
			boolean ok = ReportCtl.start(m_pi, IsDirectPrint);
			m_pi.setSummary("Report", !ok);
			unlock ();
		}
		/**********************************************************************
		 * 	Process submission
		 */
		else
		{
			if (!startDBProcess (ProcedureName))
			{
				unlock();
				return;
			}
			//	Success - getResult
			ProcessInfoUtil.setSummaryFromDB(m_pi);
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
		JFrame frame = Env.getFrame((Container)m_parent);
		if (frame instanceof AWindow)
			((AWindow)frame).setBusyTimer(m_pi.getEstSeconds());
		else
			m_waiting = new Waiting (frame, Msg.getMsg(Env.getCtx(), "Processing"), false, m_pi.getEstSeconds());
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				log.finer("lock");
				m_parent.lockUI(m_pi);
			}
		});
		if (m_waiting != null)
		{
			m_waiting.toFront();
			m_waiting.setVisible(true);
		}
	}   //  lock

	/**
	 *  Unlock UI & dispose Waiting.
	 * 	Called from run()
	 */
	private void unlock ()
	{
	//	log.info("...");
		if (m_pi.isBatch())
			m_pi.setIsTimeout(true);
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				String summary = m_pi.getSummary();
				log.finer("unlock - " + summary);
				if (summary != null && summary.indexOf("@") != -1)
					m_pi.setSummary(Msg.parseTranslation(Env.getCtx(), summary));
				m_parent.unlockUI(m_pi);
			}
		});
		//	Remove Waiting/Processing Indicator
		if (m_waiting != null)
			m_waiting.dispose();
		m_waiting = null;
	}   //  unlock

	
	/**************************************************************************
	 *  Start Workflow.
	 *
	 *  @param AD_Workflow_ID workflow
	 *  @return     true if started
	 */
	private boolean startWorkflow (int AD_Workflow_ID)
	{
		log.fine(AD_Workflow_ID + " - " + m_pi);
		boolean started = false;
		if (DB.isRemoteProcess())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					m_pi = server.workflow (Env.getCtx(), m_pi, AD_Workflow_ID);
					log.finest("server => " + m_pi);
					started = true;
				}
			}
			catch (RemoteException ex)
			{
				log.log(Level.SEVERE, "AppsServer error", ex);
				started = false;
			}
		}
		//	Run locally
		if (!started && !m_IsServerProcess)
		{
			MWorkflow wf = MWorkflow.get (Env.getCtx(), AD_Workflow_ID);
			MWFProcess wfProcess = null;
			if (m_pi.isBatch())
				wfProcess = wf.start(m_pi);				//	may return null
			else
				wfProcess = wf.startWait(m_pi);	//	may return null
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
		log.fine(m_pi.toString());
		boolean started = false;
		if (DB.isRemoteProcess())
		{
			Server server = CConnection.get().getServer();
			try
			{
				if (server != null)
				{	//	See ServerBean
					m_pi = server.process (Env.getCtx(), m_pi);
					log.finest("server => " + m_pi);
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
							+  cause.toString() + " - " + m_pi, ex);
					else
						log.log(Level.SEVERE, "AppsServer error(1b): " 
							+ cause.toString() + " - " + m_pi, ex);
				}
				else
					log.log(Level.SEVERE, " AppsServer error(1) - " 
						+ m_pi, ex);
				started = false;
			}
			catch (RemoteException ex)
			{
				Throwable cause = ex.getCause();
				if (cause == null)
					cause = ex;
				log.log(Level.SEVERE, "AppsServer error - " + m_pi, cause);
				started = false;
			}
		}
		//	Run locally
		if (!started && !m_IsServerProcess)
		{
			ProcessCall myObject = null;
			try
			{
				Class myClass = Class.forName(m_pi.getClassName());
				myObject = (ProcessCall)myClass.newInstance();
				if (myObject == null)
					m_pi.setSummary("No Instance for " + m_pi.getClassName(), true);
				else
					myObject.startProcess(Env.getCtx(), m_pi, m_trx);
				if (m_trx != null)
				{
					m_trx.commit();
					m_trx.close();
				}
			}
			catch (Exception e)
			{
				if (m_trx != null)
				{
					m_trx.rollback();
					m_trx.close();
				}
				m_pi.setSummary("Error starting Class " + m_pi.getClassName(), true);
				log.log(Level.SEVERE, m_pi.getClassName(), e);
			}
		}
		return !m_pi.isError();
	}   //  startProcess


	/**************************************************************************
	 *  Start Database Process
	 *  @param ProcedureName PL/SQL procedure name
	 *  @return true if success
	 */
	private boolean startDBProcess (String ProcedureName)
	{
		//  execute on this thread/connection
		log.fine(ProcedureName + "(" + m_pi.getAD_PInstance_ID() + ")");
		String sql = "{call " + ProcedureName + "(?)}";
		try
		{
			CallableStatement cstmt = DB.prepareCall(sql);	//	ro??
			cstmt.setInt(1, m_pi.getAD_PInstance_ID());
			cstmt.executeUpdate();
			cstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
			m_pi.setSummary (Msg.getMsg(Env.getCtx(), "ProcessRunError") + " " + e.getLocalizedMessage());
			m_pi.setError (true);
			return false;
		}
	//	log.fine(Log.l4_Data, "ProcessCtl.startProcess - done");
		return true;
	}   //  startDBProcess

	
}	//	ProcessCtl
