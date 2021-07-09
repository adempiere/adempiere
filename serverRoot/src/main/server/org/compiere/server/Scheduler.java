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

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MNote;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRole;
import org.compiere.model.MScheduler;
import org.compiere.model.MSchedulerLog;
import org.compiere.model.MUser;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoUtil;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.service.dsl.ProcessBuilder;

import it.sauronsoftware.cron4j.Predictor;
import it.sauronsoftware.cron4j.SchedulingPattern;


/**
 *	Scheduler
 *
 *  @author Jorg Janke
 *  @version $Id: Scheduler.java,v 1.5 2006/07/30 00:53:33 jjanke Exp $
 *  
 *  Contributors:
 *    Carlos Ruiz - globalqss - FR [3135351] - Enable Scheduler for buttons
 *    Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com - Fixes default parameters
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
		schedulerConfiguration = model;
	//	m_client = MClient.get(model.getCtx(), model.getAD_Client_ID());
	}	//	Scheduler

	/**	The Concrete Model			*/
	private MScheduler			schedulerConfiguration = null;
	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	/** Transaction					*/
	private Trx					m_trx = null;

	private it.sauronsoftware.cron4j.Scheduler cronScheduler;
	private Predictor predictor;
	
	// ctx for the report/process
	Properties m_schedulerctx = new Properties();

	/**
	 * 	Work
	 */
	protected void doWork ()
	{
		m_summary = new StringBuffer(schedulerConfiguration.toString())
			.append(" - ");

		// Prepare a ctx for the report/process - BF [1966880]
		m_schedulerctx.clear();
		MClient schedclient = MClient.get(getCtx(), schedulerConfiguration.getAD_Client_ID());
		Env.setContext(m_schedulerctx, "#AD_Client_ID", schedclient.getAD_Client_ID());
		Env.setContext(m_schedulerctx, "#AD_Language", schedclient.getAD_Language());
		Env.setContext(m_schedulerctx, "#AD_Org_ID", schedulerConfiguration.getAD_Org_ID());
		if (schedulerConfiguration.getAD_Org_ID() != 0) {
			MOrgInfo schedorg = MOrgInfo.get(getCtx(), schedulerConfiguration.getAD_Org_ID(), null);
			if (schedorg.getM_Warehouse_ID() > 0)
				Env.setContext(m_schedulerctx, "#M_Warehouse_ID", schedorg.getM_Warehouse_ID());
		}
		Env.setContext(m_schedulerctx, "#AD_User_ID", getAD_User_ID());
		Env.setContext(m_schedulerctx, "#SalesRep_ID", getAD_User_ID());
		// TODO: It can be convenient to add  AD_Scheduler.AD_Role_ID
		MUser scheduser = MUser.get(getCtx(), getAD_User_ID());
		MRole[] schedroles = scheduser.getRoles(schedulerConfiguration.getAD_Org_ID());
		if (schedroles != null && schedroles.length > 0)
			Env.setContext(m_schedulerctx, "#AD_Role_ID", schedroles[0].getAD_Role_ID()); // first role, ordered by AD_Role_ID
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat dateFormat4Timestamp = new SimpleDateFormat("yyyy-MM-dd"); 
		Env.setContext(m_schedulerctx, "#Date", dateFormat4Timestamp.format(ts)+" 00:00:00" );    //  JDBC format
		Properties currentctx = Env.getCtx();
		Env.setCtx(m_schedulerctx);

		MProcess process = new MProcess(m_schedulerctx, schedulerConfiguration.getAD_Process_ID(), null);
		try
		{
			m_trx = Trx.get(Trx.createTrxName("Scheduler"), true);
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
		
		// Restore system context
		Env.setCtx(currentctx);

		//
		int no = schedulerConfiguration.deleteLog();
		m_summary.append(" Logs deleted=").append(no);
		//
		MSchedulerLog pLog = new MSchedulerLog(schedulerConfiguration, m_summary.toString());
		pLog.setReference("#" + String.valueOf(p_runCount)
			+ " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
		pLog.saveEx();
	}	//	doWork

	/**
	 * 	Run Process or Report
	 *	@param process process
	 *	@return summary
	 *	@throws Exception
	 */
	private String runProcess(MProcess process) throws Exception {
		log.info(process.toString());
		
		boolean isReport = (process.isReport() || process.getAD_ReportView_ID() > 0);
		
		//	Process (see also MWFActivity.performWork
		int AD_Table_ID = schedulerConfiguration.get_Table_ID();
		int Record_ID = schedulerConfiguration.getRecord_ID();
		//
		ProcessBuilder builder = ProcessBuilder.create(getCtx())
			.process(process.getAD_Process_ID())
			.withClientId(schedulerConfiguration.getAD_Client_ID())
			.withUserId(getAD_User_ID())
			.withTitle(process.getName())
			.withRecordId(schedulerConfiguration.getAD_Table_ID(), schedulerConfiguration.getRecord_ID());
		//	Fill It
		fillParameter(builder);
		try {
			builder.execute();
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
		}
		//	Get Process Info
		ProcessInfo info = builder.getProcessInfo();
		MUser from = new MUser(getCtx(), getAD_User_ID(), null);
		if(info.isError()) {
			// notify supervisor if error
			int supervisor = schedulerConfiguration.getSupervisor_ID();
			if (supervisor > 0) {
				MUser user = new MUser(getCtx(), supervisor, null);
				boolean email = user.isNotificationEMail();
				boolean notice = user.isNotificationNote();
				
				if (email || notice) {
					ProcessInfoUtil.setLogFromDB(info);
				}
				//	
				if (email) {
					MClient client = MClient.get(schedulerConfiguration.getCtx(), schedulerConfiguration.getAD_Client_ID());
					client.sendEMail(from, user, process.getName(), info.getSummary() + " " + info.getLogInfo(), null);
				}
				if (notice) {
					int AD_Message_ID = 442; // HARDCODED ProcessRunError
					MNote note = new MNote(getCtx(), AD_Message_ID, supervisor, null);
					note.setClientOrg(schedulerConfiguration.getAD_Client_ID(), schedulerConfiguration.getAD_Org_ID());
					note.setTextMsg(info.getSummary());
					note.setRecord(MPInstance.Table_ID, info.getAD_PInstance_ID());
					note.saveEx();
				}
			}
		} else {
			// notify recipients on success
			Integer[] userIDs = schedulerConfiguration.getRecipientAD_User_IDs();
			if (userIDs.length > 0) 
			{
				ProcessInfoUtil.setLogFromDB(info);
				for (int i = 0; i < userIDs.length; i++)
				{
					MUser user = new MUser(getCtx(), userIDs[i].intValue(), null);
					boolean email = user.isNotificationEMail();
					boolean notice = user.isNotificationNote();
					
					File report = null;
					if (isReport) {
						//	Report
						ReportEngine re = ReportEngine.get(m_schedulerctx, info);
						if (re == null)
							return "Cannot create Report AD_Process_ID=" + process.getAD_Process_ID()
							+ " - " + process.getName();
						report = re.getPDF();

					}
					
					if (notice) {
						int AD_Message_ID = 441; // ProcessOK
						if (isReport)
							AD_Message_ID = 884; //	HARDCODED SchedulerResult
						MNote note = new MNote(getCtx(), AD_Message_ID, userIDs[i].intValue(), null);
						note.setClientOrg(schedulerConfiguration.getAD_Client_ID(), schedulerConfiguration.getAD_Org_ID());
						if (isReport) {
							note.setTextMsg(schedulerConfiguration.getName());
							note.setDescription(schedulerConfiguration.getDescription());
							note.setRecord(AD_Table_ID, Record_ID);
						} else {
							note.setTextMsg(info.getSummary());
							note.setRecord(MPInstance.Table_ID, info.getAD_PInstance_ID());
						}
						if (note.save()) {
							if (isReport) {
								//	Attachment
								MAttachment attachment = new MAttachment (getCtx(), MNote.Table_ID, note.getAD_Note_ID(), null);
								attachment.setClientOrg(schedulerConfiguration.getAD_Client_ID(), schedulerConfiguration.getAD_Org_ID());
								attachment.addEntry(report);
								attachment.setTextMsg(schedulerConfiguration.getName());
								attachment.saveEx();
							}
						}
					}
					//	
					if (email) {
						MClient client = MClient.get(schedulerConfiguration.getCtx(), schedulerConfiguration.getAD_Client_ID());
						if (isReport) {
							client.sendEMail(from, user, schedulerConfiguration.getName(), schedulerConfiguration.getDescription(), report);
						} else {
							client.sendEMail(from, user, process.getName(), info.getSummary() + " " + info.getLogInfo(), null);
						}
					}
					
				}
			}
		}
		return info.getSummary();
	}	//	runProcess

	private int getAD_User_ID() {
		int AD_User_ID;
		if (schedulerConfiguration.getSupervisor_ID() > 0)
			AD_User_ID = schedulerConfiguration.getSupervisor_ID();
		else if (schedulerConfiguration.getCreatedBy() > 0)
			AD_User_ID = schedulerConfiguration.getCreatedBy();
		else if (schedulerConfiguration.getUpdatedBy() > 0)
			AD_User_ID = schedulerConfiguration.getUpdatedBy();
		else
			AD_User_ID = 100; //fall back to SuperUser
		return AD_User_ID;
	}
	
	/**
	 * 	Fill Parameter
	 *	@param pInstance process instance
	 */
	private void fillParameter(ProcessBuilder builder) {
		Arrays.asList(schedulerConfiguration.getParameters(false))
			.forEach(schedulerParameter -> {
				String variable = schedulerParameter.getParameterDefault();
				log.fine(schedulerParameter.getColumnName() + " = " + variable);
				//	Value - Constant/Variable
				Object value = variable;
				if (variable == null
					|| (variable != null && variable.length() == 0)) {
					value = null;
				} else if (  variable.indexOf('@') != -1
						&& variable.indexOf('@') != variable.lastIndexOf('@')) {
					//	Strip
					int index = variable.indexOf('@');
					String columnName = variable.substring(index+1);
					index = columnName.indexOf('@');
					if (index != -1) {
						columnName = columnName.substring(0, index);
						//	try Env
						String env = Env.getContext(m_schedulerctx, columnName);
						if (env == null || env.length() == 0)
							env = Env.getContext(getCtx(), columnName);
						if (env.length() == 0) {
							log.warning(schedulerParameter.getColumnName()
								+ " - not in environment =" + columnName
								+ "(" + variable + ")");
						} else {
							value = env;
						}
					}
				}	//	@variable@
				//	Exists a Value
				if (value != null) {
					//	Object Parameter
					Object parameterOfProcess = null;
					//	Convert to Type
					try {
						if (DisplayType.isNumeric(schedulerParameter.getDisplayType())
							|| DisplayType.isID(schedulerParameter.getDisplayType())) {
							BigDecimal decimalValue = null;
							if (value instanceof BigDecimal)
								decimalValue = (BigDecimal)value;
							else if (value instanceof Integer)
								decimalValue = new BigDecimal (((Integer)value).intValue());
							else
								decimalValue = new BigDecimal (value.toString());
							parameterOfProcess = decimalValue;
							log.fine(schedulerParameter.getColumnName()
								+ " = " + variable + " (=" + decimalValue + "=)");
						} else if (DisplayType.YesNo == schedulerParameter.getDisplayType()) {
							boolean booleanValue = false;
							if(value instanceof Boolean) {
								booleanValue = ((Boolean) value);
							} else {
								booleanValue = value.toString().replaceAll("'", "").equals("Y");
							}
							parameterOfProcess = booleanValue;
							log.fine(schedulerParameter.getColumnName()
									+ " = " + variable + " (=" + booleanValue + "=)");
						} else if (DisplayType.isDate(schedulerParameter.getDisplayType())) {
							Timestamp dateValue = null;
							if (value instanceof Timestamp)
								dateValue = (Timestamp)value;
							else
								dateValue = Timestamp.valueOf(value.toString());
							parameterOfProcess = dateValue;
							log.fine(schedulerParameter.getColumnName()
								+ " = " + variable + " (=" + dateValue + "=)");
						} else {
							parameterOfProcess = value.toString().replaceAll("'", "");
							log.fine(schedulerParameter.getColumnName()
								+ " = " + variable
								+ " (=" + value + "=) " + value.getClass().getName());
						}
						//	add to builder
						if (parameterOfProcess != null) {
							log.fine("ColumnName=" + schedulerParameter.getColumnName() + ", Value=" + parameterOfProcess);
							builder.withParameter(schedulerParameter.getColumnName(), parameterOfProcess);
						}
					}
					catch (Exception e) {
						log.warning(schedulerParameter.getColumnName()
							+ " = " + variable + " (" + value
							+ ") " + value.getClass().getName()
							+ " - " + e.getLocalizedMessage());
					}
				}
			});
	}
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
		String cronPattern = (String) schedulerConfiguration.getCronPattern();
		if (cronPattern != null && cronPattern.trim().length() > 0 && SchedulingPattern.validate(cronPattern)) {
			cronScheduler = new it.sauronsoftware.cron4j.Scheduler();
			cronScheduler.schedule(cronPattern, new Runnable() {
				public void run() {
					runNow();
					long next = predictor.nextMatchingTime();
					p_model.setDateNextRun(new Timestamp(next));
					p_model.saveEx();
				}
			});
			predictor = new Predictor(cronPattern);
			long next = predictor.nextMatchingTime();
			p_model.setDateNextRun(new Timestamp(next));
			p_model.saveEx();
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
