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
package org.compiere.session;

import java.util.*;
import java.util.logging.*;

import javax.ejb.*;

import org.adempiere.util.ProcessUtil;
import org.compiere.*;
import org.compiere.acct.*;
import org.compiere.model.*;
import org.compiere.process.*;
import org.compiere.util.*;

/**
 * 	Adempiere Server Bean.
 *
 *  @ejb.bean name="adempiere/Server"
 *		display-name="Adempiere Server Session Bean"
 *		type="Stateless"
 *		view-type="both"
 *      transaction-type="Bean"
 *      jndi-name="adempiere/Server"
 *      local-jndi-name="adempiere/ServerLocal"
 *
 *  @ejb.ejb-ref ejb-name="adempiere/Server"
 *  	view-type="both"
 *		ref-name="adempiere/Server"
 *  @ejb.ejb-ref ejb-name="adempiere/Server"
 *  	view-type="local"
 *		ref-name="adempiere/ServerLocal"
 *  
 *  @ejb.permission role-name="adempiereUsers"
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: ServerBean.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 *  @author Low Heng Sin
 *  - Added remote transaction management
 *  - Added support to run db process remotely on server
 *  
 *  @author Teo Sarca, SC ARHIPAC SERVICE SRL - BF [ 1757523 ]
 */
public class ServerBean implements SessionBean
{
	/**	Context				*/
	private SessionContext 	m_Context;
	/**	Logger				*/
	private static CLogger log = CLogger.getCLogger(ServerBean.class);
	//
	private static int		s_no = 0;
	private int				m_no = 0;
	//
	private int				m_windowCount = 0;
	private int				m_postCount = 0;
	private int				m_processCount = 0;
	private int				m_workflowCount = 0;
	private int				m_paymentCount = 0;
	private int				m_nextSeqCount = 0;
	private int				m_stmt_rowSetCount = 0;
	private int				m_stmt_updateCount = 0;
	private int				m_cacheResetCount = 0;
	private int				m_updateLOBCount = 0;

	/**
	 *  Post Immediate
	 *  @ejb.interface-method view-type="both"
	 *
	 *	@param	ctx Client Context
	 *  @param  AD_Client_ID    Client ID of Document
	 *  @param  AD_Table_ID     Table ID of Document
	 *  @param  Record_ID       Record ID of this document
	 *  @param  force           force posting
	 *  @param trxName transaction
	 *  @return null, if success or error message
	 */
	public String postImmediate (Properties ctx, 
		int AD_Client_ID, int AD_Table_ID, int Record_ID, boolean force, String trxName)
	{
		log.info ("[" + m_no + "] Table=" + AD_Table_ID + ", Record=" + Record_ID);
		if (trxName != null) {
			if (Trx.get(trxName, false) == null) {
				throw new RuntimeException("Transaction lost - " + trxName);
			}
		}
		m_postCount++;
		MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(ctx, AD_Client_ID);
		return Doc.postImmediate(ass, AD_Table_ID, Record_ID, force, trxName);
	}	//	postImmediate

	/*************************************************************************
	 *  Process Remote
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param ctx Context
	 *  @param pi Process Info
	 *  @return resulting Process Info
	 */
	public ProcessInfo process (Properties ctx, ProcessInfo pi)
	{
		
		m_processCount++;
		
		//	Start Process
		String trxName = Trx.createTrxName("ServerPrc"); 
		Trx trx = Trx.get(trxName, true);
		ProcessUtil.startJavaProcess(ctx, pi, trx);
		return pi;
	}	//	process


	/*************************************************************************
	 *  Run Workflow (and wait) on Server
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param ctx Context
	 *  @param pi Process Info
	 *  @param AD_Workflow_ID id
	 *  @return process info
	 */
	public ProcessInfo workflow (Properties ctx, ProcessInfo pi, int AD_Workflow_ID)
	{
		log.info ("[" + m_no + "] " + AD_Workflow_ID);
		m_workflowCount++;
		ProcessUtil.startWorkFlow(ctx, pi, AD_Workflow_ID);
		return pi;
	}	//	workflow

	/**
	 *  Create EMail from Server (Request User)
	 *  @ejb.interface-method view-type="both"
	 *  @param ctx Context
	 *  @param AD_Client_ID client 
	 *	@param to recipient email address
	 *	@param subject subject
	 *	@param message message
	 *  @return EMail
	 */
	public EMail createEMail (Properties ctx, int AD_Client_ID, 
		String to, String subject, String message)
	{
		MClient client = MClient.get(ctx, AD_Client_ID);
		boolean html = false;
		if (message != null && message.startsWith(EMail.HTML_MAIL_MARKER)) 
		{
			html = true;
			message = message.substring(EMail.HTML_MAIL_MARKER.length());
		}
		return client.createEMail(to, subject, message, html);
	}	//	createEMail

	/**
	 *  Create EMail from Server (Request User)
	 *  @ejb.interface-method view-type="both"
	 *  @param ctx Context
	 *  @param AD_Client_ID client 
	 *  @param AD_User_ID user to send email from
	 *	@param to recipient email address
	 *	@param subject subject
	 *	@param message message
	 *  @return EMail
	 */
	public EMail createEMail (Properties ctx, int AD_Client_ID,
		int AD_User_ID,
		String to, String subject, String message)
	{
		MClient client = MClient.get(ctx, AD_Client_ID);
		MUser from = new MUser (ctx, AD_User_ID, null);
		boolean html = false;
		if (message != null && message.startsWith(EMail.HTML_MAIL_MARKER)) 
		{
			html = true;
			message = message.substring(EMail.HTML_MAIL_MARKER.length());
		}
		return client.createEMail(from, to, subject, message, html);
	}	//	createEMail

	
	/**
	 *  Execute task on server
	 *  @ejb.interface-method view-type="both"
	 *  @param AD_Task_ID task 
	 *  @return execution trace
	 */
	public String executeTask (int AD_Task_ID)
	{
		MTask task = new MTask (Env.getCtx(), AD_Task_ID, null);	//	Server Context
		return task.execute();
	}	//	executeTask
	
	
	/**
	 *  Cash Reset
	 *  @ejb.interface-method view-type="both"
	 *
	 *  @param tableName table name
	 *  @param Record_ID record or 0 for all
	 * 	@return number of records reset
	 */
	public int cacheReset (String tableName, int Record_ID)
	{
		log.config(tableName + " - " + Record_ID);
		m_cacheResetCount++;
		return CacheMgt.get().reset(tableName, Record_ID);
	}	//	cacheReset
	
	/**************************************************************************
	 * 	Describes the instance and its content for debugging purpose
	 *  @ejb.interface-method view-type="both"
	 *  @ejb.permission unchecked="true"
	 * 	@return Debugging information about the instance and its content
	 */
	public String getStatus()
	{
		StringBuffer sb = new StringBuffer("ServerBean[");
		sb.append(m_no)
			.append("-Window=").append(m_windowCount)
			.append(",Post=").append(m_postCount)
			.append(",Process=").append(m_processCount)
			.append(",NextSeq=").append(m_nextSeqCount)
			.append(",Workflow=").append(m_workflowCount)
			.append(",Payment=").append(m_paymentCount)
			.append(",RowSet=").append(m_stmt_rowSetCount)
			.append(",Update=").append(m_stmt_updateCount)
			.append(",CacheReset=").append(m_cacheResetCount)
			.append(",UpdateLob=").append(m_updateLOBCount)
			.append("]");
		return sb.toString();
	}	//	getStatus

	/**
	 * Execute db proces on server
	 * @ejb.interface-method view-type="both"
	 * @param processInfo
	 * @param procedureName
	 * @return ProcessInfo
	 */
	public ProcessInfo dbProcess(ProcessInfo processInfo, String procedureName)
	{
		String trxName = Trx.createTrxName("ServerDBPrc"); 
		Trx trx = Trx.get(trxName, true);
		ProcessUtil.startDatabaseProcedure(processInfo, procedureName, trx);
		return processInfo;
	}
	
	/**
	 * 	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		return getStatus();
	}	//	toString

	
	/**************************************************************************
	 * 	Create the Session Bean
	 * 	@throws EJBException
	 * 	@throws CreateException
	 *  @ejb.create-method view-type="both"
	 *  @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws EJBException, CreateException
	{
		m_no = ++s_no;
		try
		{
			if (!Adempiere.startup(false))
				throw new CreateException("Adempiere could not start");
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, "ejbCreate", ex);
		//	throw new CreateException ();
		}
		log.info ("#" + getStatus());
	}	//	ejbCreate


	// -------------------------------------------------------------------------
	// Framework Callbacks
	// -------------------------------------------------------------------------

	/**
	 * Method setSessionContext
	 * @param aContext SessionContext
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#setSessionContext(SessionContext)
	 */
	public void setSessionContext (SessionContext aContext) throws EJBException
	{
		m_Context = aContext;
	}	//	setSessionContext

	/**
	 * Method ejbActivate
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate() throws EJBException
	{
		if (log == null)
			log = CLogger.getCLogger(getClass());
		log.info ("ejbActivate " + getStatus());
	}	//	ejbActivate

	/**
	 * Method ejbPassivate
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate() throws EJBException
	{
		log.info ("ejbPassivate " + getStatus());
	}	//	ejbPassivate

	/**
	 * Method ejbRemove
	 * @throws EJBException
	 * @see javax.ejb.SessionBean#ejbRemove()
	 */
	public void ejbRemove() throws EJBException
	{
		log.info ("ejbRemove " + getStatus());
	}	//	ejbRemove


	/**************************************************************************
	 * 	Dump SerialVersionUID of class 
	 *	@param clazz class
	 */
	protected static void dumpSVUID (Class clazz)
	{
		String s = clazz.getName() 
			+ " ==\nstatic final long serialVersionUID = "
			+ java.io.ObjectStreamClass.lookup(clazz).getSerialVersionUID()
			+ "L;\n";
		System.out.println (s);
	}	//	dumpSVUID
	
	/**
	 * 	Print UID of used classes.
	 *	@param args ignored
	 */
	public static void main (String[] args)
	{
		dumpSVUID(ProcessInfo.class);
	}	//	main

}	//	ServerBean
