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
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.ldap.LdapProcessor;
import org.compiere.model.AdempiereProcessor;
import org.compiere.model.AdempiereProcessor2;
import org.compiere.model.AdempiereProcessorLog;
import org.compiere.model.MAcctProcessor;
import org.compiere.model.MAlertProcessor;
import org.compiere.model.MClient;
import org.compiere.model.MIMPProcessor;
import org.compiere.model.MLdapProcessor;
import org.compiere.model.MRequestProcessor;
import org.compiere.model.MScheduler;
import org.compiere.model.MSystem;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.wf.MWorkflowProcessor;
import org.eevolution.model.MProjectProcessor;

/**
 *	ADempiere Server Base
 *
 *  @author Jorg Janke
 *  @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  		<a href="https://github.com/adempiere/adempiere/issues/2202">
 *			@see FR [ 2202 ] Add Support to Project Processor</a>
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *	<li>Add support to only one run for a scheduler
 */
public abstract class AdempiereServer extends Thread
{
	/**
	 * 	Create New Server Thread
	 *	@param model model
	 *	@return server tread or null
	 */
	public static AdempiereServer create (AdempiereProcessor model)
	{
		if (model instanceof MRequestProcessor)
			return new RequestProcessor ((MRequestProcessor)model);
		if (model instanceof MWorkflowProcessor)
			return new WorkflowProcessor ((MWorkflowProcessor)model);
		if (model instanceof MAcctProcessor)
			return new AcctProcessor ((MAcctProcessor)model);
		if (model instanceof MAlertProcessor)
			return new AlertProcessor ((MAlertProcessor)model);
		if (model instanceof MScheduler)
			return new Scheduler ((MScheduler)model);
		if (model instanceof MLdapProcessor)
			return new LdapProcessor((MLdapProcessor)model);
		if (model instanceof MIMPProcessor) // @Trifon
			return new ReplicationProcessor((MIMPProcessor)model);
		//FR [ 2202 ]
		if (model instanceof MProjectProcessor)
			return new ProjectProcessor((MProjectProcessor) model);
		//
		throw new IllegalArgumentException("Unknown Processor");
	}	//	 create


	/**************************************************************************
	 * 	Server Base Class
	 * 	@param model model
	 *	@param initialNap delay time running in sec
	 */
	protected AdempiereServer (AdempiereProcessor model, int initialNap)
	{
		super (AdempiereServerGroup.get(), null, model.getName(), 0);
		p_model = model;
		m_ctx = new Properties(model.getCtx());
		if (p_system == null)
			p_system = MSystem.get(m_ctx);
		
		//p_client = MClient.get(m_ctx);
		//FR [ 2202 ]
		p_client = MClient.get(m_ctx, model.getAD_Client_ID());
		Env.setContext(m_ctx, "#AD_Client_ID", p_client.getAD_Client_ID());
		Env.setContext(m_ctx, "#AD_Language", p_client.getAD_Language());
		m_initialNap = initialNap;
		Timestamp dateNextRun = getDateNextRun(true);
		if (dateNextRun != null)
			nextWork = dateNextRun.getTime();

		long now = System.currentTimeMillis();
		if (nextWork > now)
		{
			sleepTime = nextWork - now;
		}
	//	log.info(model.getName() + " - " + getThreadGroup());
	}	//	ServerBase

	/**	The Processor Model						*/
	protected					AdempiereProcessor 	p_model;
	/** Initial nap is seconds		*/
	private int					m_initialNap = 0;

	/**	Miliseconds to sleep - 10 Min default	*/
	private long				sleepTime = 600000;
	/** Sleeping					*/
	private volatile boolean	sleeping = false;
	/** Server start time					*/
	private long				m_start = 0;
	/** Number of Work executions	*/
	protected int 				p_runCount = 0;
	/** Tine start of work				*/
	protected long				p_startWork = 0;
	/** Number MS of last Run		*/
	private long 				m_runLastMS = 0;
	/** Number of MS total			*/
	private long 				m_runTotalMS = 0;
	/** When to run next			*/
	private long 				nextWork = 0;

	/**	Logger						*/
	protected CLogger	log = CLogger.getCLogger(getClass());
	/**	Context						*/
	private Properties	m_ctx = null;
	/** System						*/
	protected static MSystem p_system = null;
	/** Client						*/
	protected MClient	p_client = null;

	/**
	 * 	Get Server Context
	 *	@return context
	 */
	public Properties getCtx()
	{
		return m_ctx;
	}	//	getCtx

	/**
	 * @return Returns the sleepMS.
	 */
	public long getSleepMS ()
	{
		return sleepTime;
	}	//	getSleepMS


	/**
	 * 	Sleep for set time
	 *	@return true if not interrupted
	 */
	public boolean sleep()
	{
		if (isInterrupted())
		{
			log.info (getName() + ": interrupted");
			return false;
		}
		log.fine(getName() + ": sleeping " + TimeUtil.formatElapsed(sleepTime));
		sleeping = true;
		try
		{
			sleep (sleepTime);
		}
		catch (InterruptedException e)
		{
			log.info (getName() + ": interrupted");
			sleeping = false;
			return false;
		}
		sleeping = false;
		return true;
	}	//	sleep

	/**
	 * 	Run Now
	 */
	public void runNow()
	{
		log.info(getName());
		p_startWork = System.currentTimeMillis();
		doWork();
		long now = System.currentTimeMillis();
		//	---------------

		p_runCount++;
		m_runLastMS = now - p_startWork;
		m_runTotalMS += m_runLastMS;
		p_model.setDateLastRun(new Timestamp(now));
		p_model.saveEx();
		//
		log.fine(getName() + ": " + getStatistics());
	}	//	runNow

	/**
	 * Validate if is fixed time
	 * @return
	 */
	protected boolean isValidForRun() {
		if(Optional.ofNullable(p_model.getFrequencyType()).orElse(MScheduler.FREQUENCYTYPE_Day).equals(MScheduler.FREQUENCYTYPE_DoesNotRepeat)
				&& Optional.ofNullable(p_model.getDateLastRun()).isPresent()) {
			return false;
		}
		return true;
	}
	
	/**************************************************************************
	 * 	Run async
	 */
	public void run ()
	{
		try
		{
			log.fine(getName() + ": pre-nap - " + m_initialNap);
			sleep (m_initialNap * 1000);
		}
		catch (InterruptedException e)
		{
			log.log(Level.INFO, getName() + ": pre-nap interrupted", e);
		}

		m_start = System.currentTimeMillis();
		while (true)
		{
			if (nextWork == 0)
			{
				Timestamp dateNextRun = getDateNextRun(true);
				if (dateNextRun != null)
					nextWork = dateNextRun.getTime();
			}
			long now = System.currentTimeMillis();
			if (nextWork > now)
			{
				sleepTime = nextWork - now;
				if (!sleep ())
					break;
			}
			if (isInterrupted())
			{
				log.info (getName() + ": interrupted");
				break;
			}
			//	Validate if is for not repeat
			if(!isValidForRun()) {
				log.info (getName() + ": Run finished");
				break;
			}
			//	---------------
			p_startWork = System.currentTimeMillis();
			doWork();
			now = System.currentTimeMillis();
			//	---------------

			p_runCount++;
			m_runLastMS = now - p_startWork;
			m_runTotalMS += m_runLastMS;
			//
			sleepTime = calculateSleep();
			Timestamp dateLastRun = new Timestamp(now);
			if (p_model instanceof AdempiereProcessor2)
			{
				AdempiereProcessor2 ap = (AdempiereProcessor2) p_model;
				if (ap.isIgnoreProcessingTime())
				{
					dateLastRun = new Timestamp(p_startWork);
					if (nextWork <= 0)
						nextWork = p_startWork;
					nextWork = nextWork + sleepTime;
					while (nextWork < now)
					{
						nextWork = nextWork + sleepTime;
					}
				}
				else
				{
					nextWork = now + sleepTime;
				}
			}
			else
			{
				nextWork = now + sleepTime;
			}
			//
			p_model.setDateLastRun(dateLastRun);
			if(Optional.ofNullable(p_model.getFrequencyType()).orElse(MScheduler.FREQUENCYTYPE_Day).equals(MScheduler.FREQUENCYTYPE_DoesNotRepeat)) {
				p_model.setDateNextRun(new Timestamp(nextWork));
			}

			p_model.saveEx();
			//
			log.fine(getName() + ": " + getStatistics());
			if (!sleep())
				break;
		}
		m_start = 0;
	}	//	run

	/**
	 * 	Get Run Statistics
	 *	@return Statistic info
	 */
	public String getStatistics()
	{
		return "Run #" + p_runCount
			+ " - Last=" + TimeUtil.formatElapsed(m_runLastMS)
			+ " - Total=" + TimeUtil.formatElapsed(m_runTotalMS)
			+ " - Next " + TimeUtil.formatElapsed(nextWork - System.currentTimeMillis());
	}	//	getStatistics

	/**
	 * 	Do the actual Work
	 */
	protected abstract void doWork();

	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public abstract String getServerInfo();

	/**
	 * 	Get Unique ID
	 *	@return Unique ID
	 */
	public String getServerID()
	{
		return p_model.getServerID();
	}	//	getServerID

	/**
	 * 	Get the date Next run
	 * 	@param requery requery database
	 * 	@return date next run
	 */
	public Timestamp getDateNextRun (boolean requery)
	{
		return p_model.getDateNextRun(requery);
	}	//	getDateNextRun

	/**
	 * 	Get the date Last run
	 * 	@return date lext run
	 */
	public Timestamp getDateLastRun ()
	{
		return p_model.getDateLastRun();
	}	//	getDateLastRun

	/**
	 * 	Get Description
	 *	@return Description
	 */
	public String getDescription()
	{
		return p_model.getDescription();
	}	//	getDescription

	/**
	 * 	Get Model
	 *	@return Model
	 */
	public AdempiereProcessor getModel()
	{
		return p_model;
	}	//	getModel

	/**
	 * 	Calculate Sleep ms
	 *	@return miliseconds
	 */
	private long calculateSleep ()
	{
		String frequencyType = p_model.getFrequencyType();
		int frequency = p_model.getFrequency();
		if (frequency < 1)
			frequency = 1;
		//
		long typeSec = 600;			//	10 minutes
		if (frequencyType == null) {
			typeSec = 300;			//	5 minutes
		} else if (MScheduler.FREQUENCYTYPE_Secound.equals(frequencyType)) {
			typeSec = 1;
		} else if (MScheduler.FREQUENCYTYPE_Minute.equals(frequencyType)) {
			typeSec = 60;
		} else if (MScheduler.FREQUENCYTYPE_Hour.equals(frequencyType)) {
			typeSec = 3600;
		} else if (MScheduler.FREQUENCYTYPE_Day.equals(frequencyType)) {
			typeSec = 86400;
		} else if(MScheduler.FREQUENCYTYPE_Weekly.equals(frequencyType)) {
			typeSec = 604800;
		} else if(MScheduler.FREQUENCYTYPE_Quarterly.equals(frequencyType)) {
			typeSec = 1296000;
		} else if(MScheduler.FREQUENCYTYPE_Monthly.equals(frequencyType)) {
			typeSec = 2592000;
		} else if(MScheduler.FREQUENCYTYPE_Yearly.equals(frequencyType)) {
			typeSec = 31536000;
		} else if(MScheduler.FREQUENCYTYPE_DoesNotRepeat.equals(frequencyType)) {
			typeSec = 30;
		}
		//
		return typeSec * 1000 * frequency;		//	ms
	}	//	calculateSleep

	/**
	 * 	Is Sleeping
	 *	@return sleeping
	 */
	public boolean isSleeping()
	{
		return sleeping;
	}	//	isSleeping

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer (getName())
			.append (",Prio=").append(getPriority())
			.append (",").append (getThreadGroup())
			.append (",Alive=").append(isAlive())
			.append (",Sleeping=").append(sleeping)
			.append (",Last=").append(getDateLastRun());
		if (sleeping)
			sb.append (",Next=").append(getDateNextRun(false));
		return sb.toString ();
	}	//	toString

	/**
	 * 	Get Seconds Alive
	 *	@return seconds alive
	 */
	public int getSecondsAlive()
	{
		if (m_start == 0)
			return 0;
		long now = System.currentTimeMillis();
		long ms = (now-m_start) / 1000;
		return (int)ms;
	}	//	getSecondsAlive

	/**
	 * 	Get Start Time
	 *	@return start time
	 */
	public Timestamp getStartTime()
	{
		if (m_start == 0)
			return null;
		return new Timestamp (m_start);
	}	//	getStartTime

	/**
	 * 	Get Processor Logs
	 *	@return logs
	 */
	public AdempiereProcessorLog[] getLogs()
	{
		return p_model.getLogs();
	}	//	getLogs

}	//	AdempiereServer
