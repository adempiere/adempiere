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
package org.compiere.server;

import it.sauronsoftware.cron4j.Predictor;
import it.sauronsoftware.cron4j.SchedulingPattern;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.print.*;
import org.compiere.process.*;
import org.compiere.util.*;


/**
 *	Scheduler
 *
 *  @author Jorg Janke
 *  @version $Id: Scheduler.java,v 1.5 2006/07/30 00:53:33 jjanke Exp $
 */
public class Scheduler extends AdempiereServer
{
	/**
	 * 	Scheduler
	 *	@param model model
	 */
	public Scheduler (MScheduler model)
	{
		super (model, 240);		//	nap
		m_model = model;
	//	m_client = MClient.get(model.getCtx(), model.getAD_Client_ID());
	}	//	Scheduler

	/**	The Concrete Model			*/
	private MScheduler			m_model = null;
	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	/** Transaction					*/
	private Trx					m_trx = null;

	private it.sauronsoftware.cron4j.Scheduler cronScheduler;
	private Predictor predictor;

	/**
	 * 	Work
	 */
	protected void doWork ()
	{
		m_summary = new StringBuffer(m_model.toString())
			.append(" - ");
		//
		MProcess process = m_model.getProcess();
		try
		{
			m_trx = Trx.get(Trx.createTrxName("Scheduler"), true);
			if (process.isReport())
				m_summary.append(runReport(process));
			else
				m_summary.append(runProcess(process));
			m_trx.commit(true);
		}
		catch (Exception e)
		{
			if (m_trx != null)
				m_trx.rollback();
			log.log(Level.WARNING, process.toString(), e);
			m_summary.append(e.toString());
		}
		finally
		{
			if (m_trx != null)
				m_trx.close();
		}

		//
		int no = m_model.deleteLog();
		m_summary.append("Logs deleted=").append(no);
		//
		MSchedulerLog pLog = new MSchedulerLog(m_model, m_summary.toString());
		pLog.setReference("#" + String.valueOf(p_runCount)
			+ " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
		pLog.save();
	}	//	doWork

	/**
	 * 	Run Report
	 *	@param process
	 *	@return summary
	 *	@throws Exception
	 */
	private String runReport(MProcess process) throws Exception
	{		log.info(process.toString());
		if (!process.isReport() || process.getAD_ReportView_ID() == 0)
			return "Not a Report AD_Process_ID=" + process.getAD_Process_ID()
				+ " - " + process.getName();
		//	Process
		int AD_Table_ID = 0;
		int Record_ID = 0;
		//
		MPInstance pInstance = new MPInstance(process, Record_ID);
		fillParameter(pInstance);
		//
		ProcessInfo pi = new ProcessInfo (process.getName(), process.getAD_Process_ID(),
			AD_Table_ID, Record_ID);
		pi.setAD_User_ID(m_model.getUpdatedBy());
		pi.setAD_Client_ID(m_model.getAD_Client_ID());
		pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
		if (!process.processIt(pi, m_trx) && pi.getClassName() != null)
			return "Process failed: (" + pi.getClassName() + ") " + pi.getSummary();

		//	Report
		ReportEngine re = ReportEngine.get(getCtx(), pi);
		if (re == null)
			return "Cannot create Report AD_Process_ID=" + process.getAD_Process_ID()
				+ " - " + process.getName();
		File report = re.getPDF();
		//	Notice
		int AD_Message_ID = 884;		//	HARDCODED SchedulerResult
		Integer[] userIDs = m_model.getRecipientAD_User_IDs();
		for (int i = 0; i < userIDs.length; i++)
		{
			MNote note = new MNote(getCtx(),
					AD_Message_ID, userIDs[i].intValue(), m_trx.getTrxName());
			note.setClientOrg(m_model.getAD_Client_ID(), m_model.getAD_Org_ID());
			note.setTextMsg(m_model.getName());
			note.setDescription(m_model.getDescription());
			note.setRecord(AD_Table_ID, Record_ID);
			note.save();
			//	Attachment
			MAttachment attachment = new MAttachment (getCtx(),
					X_AD_Note.Table_ID, note.getAD_Note_ID(), m_trx.getTrxName());
			attachment.setClientOrg(m_model.getAD_Client_ID(), m_model.getAD_Org_ID());
			attachment.addEntry(report);
			attachment.setTextMsg(m_model.getName());
			attachment.save();
		}
		//
		return pi.getSummary();
	}	//	runReport

	/**
	 * 	Run Process
	 *	@param process process
	 *	@return summary
	 *	@throws Exception
	 */
	private String runProcess(MProcess process) throws Exception
	{
		log.info(process.toString());
		//	Process (see also MWFActivity.performWork
		int AD_Table_ID = 0;
		int Record_ID = 0;
		//
		MPInstance pInstance = new MPInstance(process, Record_ID);
		fillParameter(pInstance);
		//
		ProcessInfo pi = new ProcessInfo (process.getName(), process.getAD_Process_ID(),
			AD_Table_ID, Record_ID);
		pi.setAD_User_ID(m_model.getUpdatedBy());
		pi.setAD_Client_ID(m_model.getAD_Client_ID());
		pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
		//notify supervisor if error

		MUser from = new MUser(getCtx(), pi.getAD_User_ID(), null);
		if ( !process.processIt(pi, m_trx) )
		{
			int supervisor = m_model.getSupervisor_ID();
			if (supervisor > 0)
			{
				MUser user = new MUser(getCtx(), supervisor, null);
				String type = user.getNotificationType();
				boolean email = X_AD_User.NOTIFICATIONTYPE_EMail.equals(type) ||
					X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(type);
				boolean notice = X_AD_User.NOTIFICATIONTYPE_Notice.equals(type) ||
					X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(type);
				if (email)
				{
					MClient client = MClient.get(m_model.getCtx(), m_model.getAD_Client_ID());
//					client.sendEMail(from, user, process.getName(), pi.getSummary() + " " + pi.getLogInfo(), null);
					client.sendEMail(from, user, m_model.getName(), pi.getSummary() + " " + pi.getLogInfo(), null);
				}
				if (notice) {
					int AD_Message_ID = 442; //ProcessRunError
					MNote note = new MNote(getCtx(),
							AD_Message_ID, supervisor, m_trx.getTrxName());
					note.setClientOrg(m_model.getAD_Client_ID(), m_model.getAD_Org_ID());
					note.setTextMsg(pi.getSummary());
					//note.setDescription();
					note.setRecord(X_AD_PInstance.Table_ID, pi.getAD_PInstance_ID());
					note.save();
				}
			}
		}
		else
		{
			Integer[] userIDs = m_model.getRecipientAD_User_IDs();
			for (int i = 0; i < userIDs.length; i++)
			{
				MUser user = new MUser(getCtx(), userIDs[i].intValue(), null);
				String type = user.getNotificationType();
				boolean email = X_AD_User.NOTIFICATIONTYPE_EMail.equals(type) ||
					X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(type);
				boolean notice = X_AD_User.NOTIFICATIONTYPE_Notice.equals(type) ||
					X_AD_User.NOTIFICATIONTYPE_EMailPlusNotice.equals(type);
				if (email)
				{
					MClient client = MClient.get(m_model.getCtx(), m_model.getAD_Client_ID());
//					client.sendEMail(from, user, process.getName(), pi.getSummary() + " " + pi.getLogInfo(), null);
					client.sendEMail(from, user, m_model.getName(), pi.getSummary() + " " + pi.getLogInfo(), null);
				}
				if (notice) {
					int AD_Message_ID = 441; //ProcessOK
					MNote note = new MNote(getCtx(),
							AD_Message_ID, userIDs[i].intValue(), m_trx.getTrxName());
					note.setClientOrg(m_model.getAD_Client_ID(), m_model.getAD_Org_ID());
					note.setTextMsg(pi.getSummary());
					//note.setDescription();
					note.setRecord(X_AD_PInstance.Table_ID, pi.getAD_PInstance_ID());
					note.save();
				}
			}
		}
		return pi.getSummary();
	}	//	runProcess

	/**
	 * 	Fill Parameter
	 *	@param pInstance process instance
	 */
	private void fillParameter(MPInstance pInstance)
	{
		MSchedulerPara[] sParams = m_model.getParameters (false);
		MPInstancePara[] iParams = pInstance.getParameters();
		for (int pi = 0; pi < iParams.length; pi++)
		{
			MPInstancePara iPara = iParams[pi];
			for (int np = 0; np < sParams.length; np++)
			{
				MSchedulerPara sPara = sParams[np];
				if (iPara.getParameterName().equals(sPara.getColumnName()))
				{
					String variable = sPara.getParameterDefault();
					log.fine(sPara.getColumnName() + " = " + variable);
					//	Value - Constant/Variable
					Object value = variable;
					if (variable == null
						|| (variable != null && variable.length() == 0))
						value = null;
					else if (variable.indexOf('@') != -1)	//	we have a variable
					{
						//	Strip
						int index = variable.indexOf('@');
						String columnName = variable.substring(index+1);
						index = columnName.indexOf('@');
						if (index == -1)
						{
							log.warning(sPara.getColumnName()
								+ " - cannot evaluate=" + variable);
							break;
						}
						columnName = columnName.substring(0, index);
						//	try Env
						String env = Env.getContext(getCtx(), columnName);
						if (env.length() == 0)
						{
							log.warning(sPara.getColumnName()
								+ " - not in environment =" + columnName
								+ "(" + variable + ")");
							break;
						}
						else
							value = env;
					}	//	@variable@

					//	No Value
					if (value == null)
					{
						log.fine(sPara.getColumnName() + " - empty");
						break;
					}

					//	Convert to Type
					try
					{
						if (DisplayType.isNumeric(sPara.getDisplayType())
							|| DisplayType.isID(sPara.getDisplayType()))
						{
							BigDecimal bd = null;
							if (value instanceof BigDecimal)
								bd = (BigDecimal)value;
							else if (value instanceof Integer)
								bd = new BigDecimal (((Integer)value).intValue());
							else
								bd = new BigDecimal (value.toString());
							iPara.setP_Number(bd);
							log.fine(sPara.getColumnName()
								+ " = " + variable + " (=" + bd + "=)");
						}
						else if (DisplayType.isDate(sPara.getDisplayType()))
						{
							Timestamp ts = null;
							if (value instanceof Timestamp)
								ts = (Timestamp)value;
							else
								ts = Timestamp.valueOf(value.toString());
							iPara.setP_Date(ts);
							log.fine(sPara.getColumnName()
								+ " = " + variable + " (=" + ts + "=)");
						}
						else
						{
							iPara.setP_String(value.toString());
							log.fine(sPara.getColumnName()
								+ " = " + variable
								+ " (=" + value + "=) " + value.getClass().getName());
						}
						if (!iPara.save())
							log.warning("Not Saved - " + sPara.getColumnName());
					}
					catch (Exception e)
					{
						log.warning(sPara.getColumnName()
							+ " = " + variable + " (" + value
							+ ") " + value.getClass().getName()
							+ " - " + e.getLocalizedMessage());
					}
					break;
				}	//	parameter match
			}	//	scheduler parameter loop
		}	//	instance parameter loop
	}	//	fillParameter


	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public String getServerInfo()
	{
		return "#" + p_runCount + " - Last=" + m_summary.toString();
	}	//	getServerInfo

	@Override
	public void run() {
		String cronPattern = (String) m_model.getCronPattern();
		if (cronPattern != null && cronPattern.trim().length() > 0 && SchedulingPattern.validate(cronPattern)) {
			cronScheduler = new it.sauronsoftware.cron4j.Scheduler();
			cronScheduler.schedule(cronPattern, new Runnable() {
				public void run() {
					runNow();
					long next = predictor.nextMatchingTime();
					p_model.setDateNextRun(new Timestamp(next));
					p_model.save();
				}
			});
			predictor = new Predictor(cronPattern);
			long next = predictor.nextMatchingTime();
			p_model.setDateNextRun(new Timestamp(next));
			p_model.save();
			cronScheduler.start();
			while (true) {
				if (!sleep()) {
					cronScheduler.stop();
					break;
				} else if (!cronScheduler.isStarted()) {
					break;
				}
			}
		} else {
			super.run();
		}
	}
}	//	Scheduler
