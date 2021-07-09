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
package org.compiere.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.Adempiere;
import org.compiere.model.MAcctProcessor;
import org.compiere.model.MAlertProcessor;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.MLdapProcessor;
import org.compiere.model.MRequestProcessor;
import org.compiere.model.MScheduler;
import org.compiere.model.MSession;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.wf.MWorkflowProcessor;
import org.eevolution.model.MProjectProcessor;

/**
 *	ADempiere Server Manager
 *	
 *  @author Jorg Janke
 *  @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  		<a href="https://github.com/adempiere/adempiere/issues/2202">
 *			@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class AdempiereServerMgr
{
	/**
	 * 	Get Adempiere Server Manager
	 *	@return mgr
	 */
	public static AdempiereServerMgr get()
	{
		if (serverManager == null)
		{
			//	for faster subsequent calls
			serverManager = new AdempiereServerMgr();
			serverManager.startServers();
			serverManager.log.info(serverManager.toString());
		}
		return serverManager;
	}	//	get
	
	/**	Singleton					*/
	private static	AdempiereServerMgr	serverManager = null;
	/**	Logger			*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	
	/**************************************************************************
	 * 	Adempiere Server Manager
	 */
	private AdempiereServerMgr ()
	{
		super();
		startEnvironment();
	//	m_serverMgr.startServers();
	}	//	AdempiereServerMgr

	/**	The Servers				*/
	private ArrayList<AdempiereServer>	serversList = new ArrayList<AdempiereServer>();
	/** Context					*/
	private Properties		context = Env.getCtx();
	/** Start					*/
	private Timestamp		startTime = new Timestamp(System.currentTimeMillis());

	/**
	 * 	Start Environment
	 *	@return true if started
	 */
	private boolean startEnvironment()
	{
		Adempiere.startup(false);
		log.info("");
		
		//	Set Session
		MSession session = MSession.get(getCtx(), true);
		session.setWebStoreSession(false);
		session.setWebSession("Server");
		session.saveEx();
		//
		return true;
	}	//	startEnvironment
	
	/**
	 * 	Start Environment
	 *	@return true if started
	 */
	private boolean startServers()
	{
		log.info("");
		int noServers = 0;
		//	Accounting
		MAcctProcessor[] acctModels = MAcctProcessor.getActive(context);
		for (int i = 0; i < acctModels.length; i++)
		{
			MAcctProcessor pModel = acctModels[i];
			AdempiereServer server = AdempiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			serversList.add(server);
		}		
		//	Request
		MRequestProcessor[] requestModels = MRequestProcessor.getActive(context);
		for (int i = 0; i < requestModels.length; i++)
		{
			MRequestProcessor pModel = requestModels[i];
			AdempiereServer server = AdempiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			serversList.add(server);
		}
		//	Workflow
		MWorkflowProcessor[] workflowModels = MWorkflowProcessor.getActive(context);
		for (int i = 0; i < workflowModels.length; i++)
		{
			MWorkflowProcessor pModel = workflowModels[i];
			AdempiereServer server = AdempiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			serversList.add(server);
		}		
		//	Alert
		MAlertProcessor[] alertModels = MAlertProcessor.getActive(context);
		for (int i = 0; i < alertModels.length; i++)
		{
			MAlertProcessor pModel = alertModels[i];
			AdempiereServer server = AdempiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			serversList.add(server);
		}		
		//	Scheduler
		MScheduler[] schedulerModels = MScheduler.getActive(context);
		for (int i = 0; i < schedulerModels.length; i++)
		{
			MScheduler pModel = schedulerModels[i];
			AdempiereServer server = AdempiereServer.create(pModel);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			serversList.add(server);
		}		
		//	LDAP
		MLdapProcessor[] ldapModels = MLdapProcessor.getActive(context);
		for (int i = 0; i < ldapModels.length; i++)
		{
			MLdapProcessor lp = ldapModels[i];
			AdempiereServer server = AdempiereServer.create(lp);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-1);
			serversList.add(server);
		}
		//	ImportProcessor - @Trifon
		MIMPProcessor[] importModels = MIMPProcessor.getActive(context);
		for (int i = 0; i < importModels.length; i++)
		{
			MIMPProcessor lp = importModels[i];
			AdempiereServer server = AdempiereServer.create(lp);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-1);
			serversList.add(server);
		}
		//	FR [ 2202 ] Project Processor 
		MProjectProcessor[] projectModels = MProjectProcessor.getActive(context);
		for (MProjectProcessor mProjectProcessor : projectModels) {
			AdempiereServer server = AdempiereServer.create(mProjectProcessor);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-1);
			serversList.add(server);
		}
		log.fine("#" + noServers);
		return startAll();
	}	//	startEnvironment
	
	/**
	 * Restart services, if exists a new service is reloaded
	 */
	public void restartServices() {
		log.fine("#" + serversList.size());
		destroy();
		startServers();
	}

	/**
	 * 	Get Server Context
	 *	@return ctx
	 */
	public Properties getCtx()
	{
		return context;
	}	//	getCtx
	
	/**
	 * 	Start all servers
	 *	@return true if started
	 */
	public boolean startAll()
	{
		log.info ("");
		AdempiereServer[] servers = getInActive();
		for (int i = 0; i < servers.length; i++)
		{
			AdempiereServer server = servers[i];
			try
			{
				if (server.isAlive())
					continue;
				//	Wait until dead
				if (server.isInterrupted())
				{
					int maxWait = 10;	//	10 iterations = 1 sec
					while (server.isAlive())
					{
						if (maxWait-- == 0)
						{
							log.severe ("Wait timeout for interruped " + server);
							break;
						}
						try
						{
							Thread.sleep(100);		//	1/10 sec
						}
						catch (InterruptedException e)
						{
							log.log(Level.SEVERE, "While sleeping", e);
						}
					}
				}
				//	Do start
				if (!server.isAlive())
				{
					//	replace
					server = AdempiereServer.create (server.getModel());
					if (server == null)
						serversList.remove(i);
					else
						serversList.set(i, server);
					server.start();
					server.setPriority(Thread.NORM_PRIORITY-2);
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "Server: " + server, e);
			}
		}	//	for all servers
		
		//	Final Check
		int noRunning = 0;
		int noStopped = 0;
		for (int i = 0; i < servers.length; i++)
		{
			AdempiereServer server = servers[i];
			try
			{
				if (server.isAlive())
				{
					log.info("Alive: " + server);
					noRunning++;
				}
				else
				{
					log.warning("Dead: " + server);
					noStopped++;
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(checking) - " + server, e);
				noStopped++;
			}
		}
		log.fine("Running=" + noRunning + ", Stopped=" + noStopped);
		AdempiereServerGroup.get().dump();
		return noStopped == 0;
	}	//	startAll

	/**
	 * 	Start Server if not started yet
	 * 	@param serverID server ID
	 *	@return true if started
	 */
	public boolean start (String serverID)
	{
		AdempiereServer server = getServer(serverID);
		if (server == null)
			return false;
		if (server.isAlive())
			return true;
		
		try
		{
			//	replace
			int index = serversList.indexOf(server);
			server = AdempiereServer.create (server.getModel());
			if (server == null)
				serversList.remove(index);
			else
				serversList.set(index, server);
			server.start();
			server.setPriority(Thread.NORM_PRIORITY-2);
			Thread.yield();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "Server=" + serverID, e);
			return false;
		}
		log.info(server.toString());
		AdempiereServerGroup.get().dump();
		return server.isAlive();
	}	//	startIt
	
	/**
	 * 	Stop all Servers
	 *	@return true if stopped
	 */
	public boolean stopAll()
	{
		log.info ("");
		AdempiereServer[] servers = getActive();
		//	Interrupt
		for (int i = 0; i < servers.length; i++)
		{
			AdempiereServer server = servers[i];
			try
			{
				if (server.isAlive() && !server.isInterrupted())
				{
					server.setPriority(Thread.MAX_PRIORITY-1);
					server.interrupt();
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(interrupting) - " + server, e);
			}
		}	//	for all servers
		Thread.yield();
		
		//	Wait for death
		for (int i = 0; i < servers.length; i++)
		{
			AdempiereServer server = servers[i];
			try
			{
				int maxWait = 10;	//	10 iterations = 1 sec
				while (server.isAlive())
				{
					if (maxWait-- == 0)
					{
						log.severe ("Wait timeout for interruped " + server);
						break;
					}
					Thread.sleep(100);		//	1/10
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(waiting) - " + server, e);
			}
		}	//	for all servers
		
		//	Final Check
		int noRunning = 0;
		int noStopped = 0;
		for (int i = 0; i < servers.length; i++)
		{
			AdempiereServer server = servers[i];
			try
			{
				if (server.isAlive())
				{
					log.warning ("Alive: " + server);
					noRunning++;
				}
				else
				{
					log.info ("Stopped: " + server);
					noStopped++;
				}
			}
			catch (Exception e)
			{
				log.log(Level.SEVERE, "(checking) - " + server, e);
				noRunning++;
			}
		}
		log.fine("Running=" + noRunning + ", Stopped=" + noStopped);
		AdempiereServerGroup.get().dump();
		return noRunning == 0;
	}	//	stopAll

	/**
	 * 	Stop Server if not stopped
	 * 	@param serverID server ID
	 *	@return true if interrupted
	 */
	public boolean stop (String serverID)
	{
		AdempiereServer server = getServer(serverID);
		if (server == null)
			return false;
		if (!server.isAlive())
			return true;

		try
		{
			server.interrupt();
			Thread.sleep(10);	//	1/100 sec
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "stop", e);
			return false;
		}
		log.info(server.toString());
		AdempiereServerGroup.get().dump();
		return !server.isAlive();
	}	//	stop

	
	/**
	 * 	Destroy
	 */
	public void destroy ()
	{
		log.info ("");
		stopAll();
		serversList.clear();
	}	//	destroy

	/**
	 * 	Get Active Servers
	 *	@return array of active servers
	 */
	protected AdempiereServer[] getActive()
	{
		ArrayList<AdempiereServer> list = new ArrayList<AdempiereServer>();
		for (int i = 0; i < serversList.size(); i++)
		{
			AdempiereServer server = (AdempiereServer)serversList.get(i);
			if (server != null && server.isAlive() && !server.isInterrupted())
				list.add (server);
		}
		AdempiereServer[] retValue = new AdempiereServer[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getActive
	
	/**
	 * 	Get InActive Servers
	 *	@return array of inactive servers
	 */
	protected AdempiereServer[] getInActive()
	{
		ArrayList<AdempiereServer> list = new ArrayList<AdempiereServer>();
		for (int i = 0; i < serversList.size(); i++)
		{
			AdempiereServer server = (AdempiereServer)serversList.get(i);
			if (server != null && (!server.isAlive() || !server.isInterrupted()))
				list.add (server);
		}
		AdempiereServer[] retValue = new AdempiereServer[list.size()];
		list.toArray (retValue);
		return retValue;
	}	//	getInActive

	/**
	 * 	Get all Servers
	 *	@return array of servers
	 */
	public AdempiereServer[] getAll()
	{
		AdempiereServer[] retValue = new AdempiereServer[serversList.size()];
		serversList.toArray (retValue);
		return retValue;
	}	//	getAll
	
	/**
	 * 	Get Server with ID
	 *	@param serverID server id
	 *	@return server or null
	 */
	public AdempiereServer getServer (String serverID)
	{
		if (serverID == null)
			return null;
		for (int i = 0; i < serversList.size(); i++)
		{
			AdempiereServer server = (AdempiereServer)serversList.get(i);
			if (serverID.equals(server.getServerID()))
				return server;
		}
		return null;
	}	//	getServer
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("AdempiereServerMgr[");
		sb.append("Servers=").append(serversList.size())
			.append(",ContextSize=").append(context.size())
			.append(",Started=").append(startTime)
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * 	Get Description
	 *	@return description
	 */
	public String getDescription()
	{
		return "$Revision: 1.4 $";
	}	//	getDescription
	
	/**
	 * 	Get Number Servers
	 *	@return no of servers
	 */
	public String getServerCount()
	{
		int noRunning = 0;
		int noStopped = 0;
		for (int i = 0; i < serversList.size(); i++)
		{
			AdempiereServer server = (AdempiereServer)serversList.get(i);
			if (server.isAlive())
				noRunning++;
			else
				noStopped++;
		}
		String info = String.valueOf(serversList.size())
			+ " - Running=" + noRunning
			+ " - Stopped=" + noStopped;
		return info;
	}	//	getServerCount
	
	/**
	 * 	Get start date
	 *	@return start date
	 */
	public Timestamp getStartTime()
	{
		return startTime;
	}	//	getStartTime

	public static void main(String[] args) {
		AdempiereServerMgr.get();
	}
	
}	//	AdempiereServerMgr
