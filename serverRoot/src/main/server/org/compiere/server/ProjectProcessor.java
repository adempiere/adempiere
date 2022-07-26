/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Carlos Parada cparada@erpya.com                                       *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.compiere.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.model.MMailText;
import org.compiere.model.MNote;
import org.compiere.model.MProject;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectProcessorChange;
import org.compiere.model.MProjectProcessorQueued;
import org.compiere.model.MProjectTask;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.project.service.ProjectProcessorService;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.eevolution.model.MProjectProcessor;
import org.eevolution.model.MProjectProcessorLog;
import org.eevolution.model.MProjectStatus;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

/**
 * Project Processor
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class ProjectProcessor extends AdempiereServer
{
	/**
	 * 	RequestProcessor
	 *	@param model model
	 */
	public ProjectProcessor (MProjectProcessor model)
	{
		super (model, 60);	//	1 minute delay
		m_model = model;
	}	//	RequestProcessor
	
	/**	The Concrete Model			*/
	private MProjectProcessor	m_model = null;
	
	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	
	/**Current PO Changes*/
	private PO					m_PO = null;
	
	/**Columns Changeg*/
	private ArrayList<String> 	m_columns = new ArrayList<String>();
	
	/**Values Changed*/
	private ArrayList<String> 	m_Values = new ArrayList<String>();
	
	/**Current Poject*/
	private MProject 			m_Project = null;
	
	/**Current Project Phase*/
	private MProjectPhase		m_ProjectPhase = null;
	
	/**Current Project Task*/
	private MProjectTask		m_ProjectTask = null;
	
	/**Prefix Subject for Mail to Send*/
	private String				m_PrefixSubject = "";
	
	/**Prefix Text for Mail to Send*/
	private String				m_PrefixTextMail = "";
	
	/**No HTML Text for Notice */
	private String 				m_TextNotice	= "";
	
	/**Mail Template for Project Processor */
	private MMailText			m_MailText = null;
	
	/**Extra Message for Mail*/
	private String m_ExtraMsg	= "";

	/**Changes is Scheduled*/
	private boolean isScheduled = false;
	
	/**Log */
	private static CLogger logger = CLogger.getCLogger (ProjectProcessor.class);
	
	/**************************************************************************
	 * 	Do the actual Work
	 */
	protected void doWork()
	{
		m_summary = new StringBuffer();
		m_MailText = new MMailText (Env.getCtx(), m_model.getR_MailText_ID(), null);
		//
		clearGlobals();
		//Process Queued Manual Changes
		processQueued();
		
		//Process Scheduled Changes
		processProjects ();
		processQueued();
		
	}	//	doWork

	
	/**************************************************************************
	 *  Process Projects.
	 *  Scheduled - are they due?
	 */
	private void processProjects ()
	{
		//Start Date Alert
		processStartDateAlert();
		
		//Process Status 
		processStatus();
				
		//Due Alerts
		processDueAlert();
		
		//Overdue Alerts
		processOverDueAlert();
	}	//  processProject

	/**
	 * Process Queued for e-mail messages
	 */
	private void processQueued() {
		
		String whereClause = " EXISTS (SELECT 1 "
								+ "FROM C_ProjectProcessorQueued q "
								+ "WHERE q.SendEmail='N' AND q.C_ProjectProcessorLog_ID = C_ProjectProcessorLog.C_ProjectProcessorLog_ID )";
		MProjectProcessorLog[] pLogs = m_model.getProcessorLogs(whereClause);
		boolean loadMsj = false;
		for (MProjectProcessorLog mProjectProcessorLog : pLogs) {
			loadMsj = loadMsgToSend(mProjectProcessorLog);
			MProjectProcessorQueued[] queued = mProjectProcessorLog.getQueued("SendEmail='N'");
			for (MProjectProcessorQueued mProjectProcessorQueued : queued) {
				if (loadMsj) {
					String NotificationType = (Util.isEmpty(mProjectProcessorQueued.getNotificationType()) ? MProjectProcessorQueued.NOTIFICATIONTYPE_None : mProjectProcessorQueued.getNotificationType()) ; 
					if (NotificationType.equals(MProjectProcessorQueued.NOTIFICATIONTYPE_EMail))
						sendEmail(mProjectProcessorQueued);
					else if (NotificationType.equals(MProjectProcessorQueued.NOTIFICATIONTYPE_EMailPlusNotice)) {
						sendEmail(mProjectProcessorQueued);
						createNotice(mProjectProcessorQueued);
					}
					else if (NotificationType.equals(MProjectProcessorQueued.NOTIFICATIONTYPE_Notice)) 
						createNotice(mProjectProcessorQueued);
				}
				mProjectProcessorQueued.setSendEMail(true);
				mProjectProcessorQueued.save();
			}
			//Clear Globals
			clearGlobals();
		}			
	}	//processQueued

	/**
	 * Send Email
	 * @param log
	 * @param queued
	 * @return
	 */
	private boolean sendEmail (MProjectProcessorQueued queued) {
		MUser recipient = (MUser) queued.getAD_User();
		m_MailText.setUser(recipient);
		if (m_PO != null) {
			m_MailText.setPO(m_PO);
		}
		//	
		Trx.run(transactionName -> {
			String subject = (!Util.isEmpty(m_MailText.getMailHeader())  ? m_MailText.getMailHeader() + " " :m_MailText.getMailHeader()) + m_PrefixSubject;
			String message = "";
			if (m_MailText.getR_MailText_ID() != 0) {
				message = m_MailText.getMailText(true);
			}
			//	Get instance for notifier
			DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
					.withContext(getCtx())
					.withTransactionName(transactionName);
			//	Send notification to queue
			notifier
				.clearMessage()
				.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
				.withText(m_PrefixTextMail + (message == null ? "" : message)  + "\n")
				.withDescription(subject);
			//	Add user
			if (m_model.getSupervisor_ID() != 0) {
				notifier.withUserId(m_model.getSupervisor_ID());
			}
			//	Add recipients
			notifier.addRecipient(queued.getAD_User_ID());
			if(m_PO != null) {
				notifier
					.withEntity(m_PO);
			}
			//	Add to queue
			notifier.addToQueue();
		});
		return true;
	}   //  sendEmail
	
	/**
	 * Send Email
	 * @param log
	 * @param queued
	 * @return
	 */
	private boolean createNotice (MProjectProcessorQueued queued){
		MUser to = (MUser) queued.getAD_User();
		if (to.getAD_User_ID()!=0) {
			MNote note = new MNote(getCtx(), "Error", to.getAD_User_ID(), m_PO.get_TrxName());
			note.setRecord(m_PO.get_Table_ID(), m_PO.get_ID());
			note.setReference(m_PrefixSubject);
			note.setTextMsg(m_TextNotice);
			if (!note.save())
				return false;
		}
		
		return true;
	}   //  createNotice


	/**
	 * Process Project Status
	 */
	private void processStatus()
	{
		//Process Project Status
		String whereClause = "";
		Timestamp currentDate = new Timestamp(TimeUtil.getToday().getTimeInMillis());
		
		whereClause = "EXISTS (SELECT 1 FROM "
				+ "C_ProjectStatus ps "
				+ "WHERE ps.C_ProjectStatus_ID = C_Project.C_ProjectStatus_ID "
				+ "AND ps.TimeoutDays > 0 AND ps.Next_Status_ID > 0 "
				+ "AND C_Project.DateLastAction + ps.TimeoutDays < ?) ";
		
		new ScheduleChange(getCtx(), MProject.Table_Name, whereClause, m_model.get_TrxName())
				.setAlertMessageColumn("@C_ProjectStatus_ID@ @HasBeenChanged@")
				.IsNextProjectStatus()
				.setParameters(currentDate)
				.processScheduleChanges();
		
		
		//Process Project Phase Status
		whereClause = "EXISTS (SELECT 1 FROM "
				+ "C_ProjectStatus ps "
				+ "WHERE ps.C_ProjectStatus_ID = C_ProjectPhase.C_ProjectStatus_ID "
				+ "AND ps.TimeoutDays > 0 AND ps.Next_Status_ID > 0 "
				+ "AND C_ProjectPhase.DateLastAction + ps.TimeoutDays < ?) ";
		
		new ScheduleChange(getCtx(), MProjectPhase.Table_Name, whereClause, m_model.get_TrxName())
				.setAlertMessageColumn("@C_ProjectStatus_ID@ @HasBeenChanged@")
				.IsNextProjectStatus()
				.setParameters(currentDate)
				.processScheduleChanges();

		//Process Project Task Status
		whereClause = "EXISTS (SELECT 1 FROM "
				+ "C_ProjectStatus ps "
				+ "WHERE ps.C_ProjectStatus_ID = C_ProjectTask.C_ProjectStatus_ID "
				+ "AND ps.TimeoutDays > 0 AND ps.Next_Status_ID > 0 "
				+ "AND C_ProjectTask.DateLastAction + ps.TimeoutDays < ?) ";

		new ScheduleChange(getCtx(), MProjectTask.Table_Name, whereClause, m_model.get_TrxName())
				.setAlertMessageColumn("@C_ProjectStatus_ID@ @HasBeenChanged@")
				.IsNextProjectStatus()
				.setParameters(currentDate)
				.processScheduleChanges();
	}	//	processStatus
	
	/**
	 * Process Project Start Date
	 */
	private void processStartDateAlert() {
		
		String whereClauseGeneral = "";
		String whereClause = "";
		isScheduled = true;
		ArrayList<Object> params = new ArrayList<Object>();
		Timestamp currentDate = new Timestamp(TimeUtil.getToday().getTimeInMillis());
		int daysBeforeStart = m_model.get_ValueAsInt("DaysBeforeStart");
		//Project
		whereClauseGeneral =  "(DateStartSchedule IS NOT NULL AND DateStartSchedule - ? <= ? AND DateStartSchedule >= ?) AND (DueType IS NULL OR DueType = ?)";
		params.add(daysBeforeStart);
		params.add(currentDate);
		params.add(currentDate);
		params.add(MProject.DUETYPE_Scheduled);
		
		
		//Remind Days
		whereClauseGeneral+= " AND (DateLastAlert IS NULL OR DateLastAlert!=?)";
		params.add(currentDate);
		
		if (m_model.getC_ProjectType_ID()!=0) {
			whereClause+= " AND C_ProjectType_ID = ?";
			params.add(m_model.getC_ProjectType_ID());
		}
		
		new ScheduleChange(getCtx(), MProject.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
				.setColumns(MProject.COLUMNNAME_DueType,MProject.COLUMNNAME_DateLastAlert)
				.setValues(MProject.DUETYPE_Scheduled,currentDate)
				.setAlertMessageColumn("@C_Project_ID@ @IsFor@ @Start@")
				.setParameters(params)
				.processScheduleChanges();
		
		//Project Phase
		whereClause = "";
		if (m_model.getC_ProjectType_ID()!=0) 
			whereClause+= " AND EXISTS (SELECT 1 FROM C_Project p WHERE p.C_Project_ID = C_ProjectPhase.C_Project_ID AND p.C_ProjectType_ID = ?)";
		
		new ScheduleChange(getCtx(), MProjectPhase.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
				.setColumns(MProjectPhase.COLUMNNAME_DueType,MProjectPhase.COLUMNNAME_DateLastAlert)
				.setValues(MProject.DUETYPE_Scheduled,currentDate)
				.setAlertMessageColumn("@C_ProjectPhase_ID@ @IsFor@ @Start@")
				.setParameters(params)
				.processScheduleChanges();
		
		//Project Task
		whereClause = "";
		if (m_model.getC_ProjectType_ID()!=0) 
			whereClause+= " AND EXISTS (SELECT 1 "
										+ "FROM C_Project p "
										+ "INNER JOIN C_ProjectPhase ph ON (p.C_Project_ID = ph.C_Project_ID) "
										+ "WHERE ph.C_ProjectPhase_ID = C_ProjectTask.C_ProjectPhase_ID AND p.C_ProjectType_ID = ?)";
		
		if (m_model.getC_ProjectTaskCategory_ID()!=0) {
			whereClause+= " AND C_ProjectTaskCategory_ID=?";
			params.add(m_model.getC_ProjectTaskCategory_ID());
		}
			
		new ScheduleChange(getCtx(), MProjectTask.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
				.setColumns(MProjectTask.COLUMNNAME_DueType,MProjectTask.COLUMNNAME_DateLastAlert)
				.setValues(MProject.DUETYPE_Scheduled,currentDate)
				.setAlertMessageColumn("@C_ProjectTask_ID@ @IsFor@ @Start@")
				.setParameters(params)
				.processScheduleChanges();
	}	//processStartDateAlert
	
	/**
	 * Process Project Due
	 */
	private void processDueAlert() {
		if (m_model.getOverdueAlertDays()!=0) {
			String whereClauseGeneral = "";
			String whereClause = ""; 
			isScheduled =true;
			ArrayList<Object> params = new ArrayList<Object>();
			Timestamp currentDate = new Timestamp(TimeUtil.getToday().getTimeInMillis());
			//General Filters
			whereClauseGeneral =  "(COALESCE(DateDeadLine,DateFinishSchedule) IS NOT NULL) AND (DueType IS NULL OR DueType = ?) ";
			
			params.add(MProject.DUETYPE_Scheduled);
			
			//Due Days Alert
			whereClauseGeneral += " AND (COALESCE(DateDeadLine,DateFinishSchedule) + ? <= ? AND COALESCE(DateDeadLine,DateFinishSchedule) >= ?) ";
			params.add(m_model.getOverdueAlertDays());
			params.add(currentDate);
			params.add(currentDate);
			
			//Project
			whereClause = "AND  (DateFinish IS NULL) ";
			if (m_model.getC_ProjectType_ID()!=0) {
				whereClause+= " AND (C_ProjectType_ID = ?)";
				params.add(m_model.getC_ProjectType_ID());
			}
			
			
			
			new ScheduleChange(getCtx(), MProject.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
					.setColumns(MProject.COLUMNNAME_DueType,MProject.COLUMNNAME_DateLastAlert)
					.setValues(MProject.DUETYPE_Due,currentDate)
					.setAlertMessageColumn("@C_Project_ID@ @IsDue@")
					.setParameters(params)
					.processScheduleChanges();
			
			//Project Phase
			whereClause = "AND  (EndDate IS NULL) ";
			if (m_model.getC_ProjectType_ID()!=0) 
				whereClause+= " AND EXISTS (SELECT 1 FROM C_Project p WHERE p.C_Project_ID = C_ProjectPhase.C_Project_ID AND p.C_ProjectType_ID = ?) ";
			
			new ScheduleChange(getCtx(), MProjectPhase.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
					.setColumns(MProjectPhase.COLUMNNAME_DueType,MProjectPhase.COLUMNNAME_DateLastAlert)
					.setValues(MProjectPhase.DUETYPE_Due,currentDate)
					.setAlertMessageColumn("@C_ProjectPhase_ID@ @IsDue@")
					.setParameters(params)
					.processScheduleChanges();
			
			//Project Task
			whereClause = "AND  (DateFinish IS NULL) ";
			if (m_model.getC_ProjectType_ID()!=0) 
				whereClause+= " AND (EXISTS (SELECT 1 "
											+ "FROM C_Project p "
											+ "INNER JOIN C_ProjectPhase ph ON (p.C_Project_ID = ph.C_Project_ID) "
											+ "WHERE ph.C_ProjectPhase_ID = C_ProjectTask.C_ProjectPhase_ID AND p.C_ProjectType_ID = ?))";
			
			if (m_model.getC_ProjectTaskCategory_ID()!=0) {
				whereClause+= " AND (C_ProjectTaskCategory_ID=?)";
				params.add(m_model.getC_ProjectTaskCategory_ID());
			}
				
			new ScheduleChange(getCtx(), MProjectTask.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
					.setColumns(MProjectTask.COLUMNNAME_DueType,MProjectTask.COLUMNNAME_DateLastAlert)
					.setValues(MProjectTask.DUETYPE_Due,currentDate)
					.setAlertMessageColumn("@C_ProjectTask_ID@ @IsDue@")
					.setParameters(params)
					.processScheduleChanges();
		}
	}	//processDueAlert
	
	/**
	 * Process Project Due
	 */
	private void processOverDueAlert() {
		if (m_model.getOverdueAssignDays()!=0) {
			String whereClauseGeneral = "";
			String whereClause = ""; 
			isScheduled = true;
			ArrayList<Object> params = new ArrayList<Object>();
			Timestamp currentDate = new Timestamp(TimeUtil.getToday().getTimeInMillis());
			//General Filters
			whereClauseGeneral =  "(COALESCE(DateDeadLine,DateFinishSchedule) IS NOT NULL) AND (DueType IS NULL OR DueType IN (?,?,?)) ";
			
			params.add(MProject.DUETYPE_Scheduled);
			params.add(MProject.DUETYPE_Due);
			params.add(MProject.DUETYPE_Overdue);
			
			//Due Days Alert
			whereClauseGeneral += " AND (COALESCE(DateDeadLine,DateFinishSchedule) + ? >= ? AND COALESCE(DateDeadLine,DateFinishSchedule) < ?) ";
			params.add(m_model.getOverdueAssignDays());
			params.add(currentDate);
			params.add(currentDate);
			
			//Project
			whereClause = "AND  (DateFinish IS NULL) ";
			if (m_model.getC_ProjectType_ID()!=0) {
				whereClause+= " AND (C_ProjectType_ID = ?)";
				params.add(m_model.getC_ProjectType_ID());
			}
			
			
			
			new ScheduleChange(getCtx(), MProject.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
					.setColumns(MProject.COLUMNNAME_DueType,MProject.COLUMNNAME_DateLastAlert)
					.setValues(MProject.DUETYPE_Overdue,currentDate)
					.setAlertMessageColumn("@C_Project_ID@ @OverDue@")
					.setParameters(params)
					.processScheduleChanges();
			
			//Project Phase
			whereClause = "AND  (EndDate IS NULL) ";
			if (m_model.getC_ProjectType_ID()!=0) 
				whereClause+= " AND EXISTS (SELECT 1 FROM C_Project p WHERE p.C_Project_ID = C_ProjectPhase.C_Project_ID AND p.C_ProjectType_ID = ?) ";
			
			new ScheduleChange(getCtx(), MProjectPhase.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
					.setColumns(MProjectPhase.COLUMNNAME_DueType,MProjectPhase.COLUMNNAME_DateLastAlert)
					.setValues(MProjectPhase.DUETYPE_Overdue,currentDate)
					.setAlertMessageColumn("@C_ProjectPhase_ID@ @OverDue@")
					.setParameters(params)
					.processScheduleChanges();
			
			//Project Task
			whereClause = "AND  (DateFinish IS NULL) ";
			if (m_model.getC_ProjectType_ID()!=0) 
				whereClause+= " AND (EXISTS (SELECT 1 "
											+ "FROM C_Project p "
											+ "INNER JOIN C_ProjectPhase ph ON (p.C_Project_ID = ph.C_Project_ID) "
											+ "WHERE ph.C_ProjectPhase_ID = C_ProjectTask.C_ProjectPhase_ID AND p.C_ProjectType_ID = ?))";
			
			if (m_model.getC_ProjectTaskCategory_ID()!=0) {
				whereClause+= " AND (C_ProjectTaskCategory_ID=?)";
				params.add(m_model.getC_ProjectTaskCategory_ID());
			}
				
			new ScheduleChange(getCtx(), MProjectTask.Table_Name, whereClauseGeneral + whereClause, m_model.get_TrxName())
					.setColumns(MProjectTask.COLUMNNAME_DueType,MProjectTask.COLUMNNAME_DateLastAlert)
					.setValues(MProjectTask.DUETYPE_Overdue,currentDate)
					.setAlertMessageColumn("@C_ProjectTask_ID@ @OverDue@")
					.setParameters(params)
					.processScheduleChanges();
		}
	}	//processOverDueAlert
	
	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public String getServerInfo()
	{
		return "#" + p_runCount + " - Last=" + m_summary.toString();
	}	//	getServerInfo
	
	/**
	 * Load Message to Send
	 * @param log
	 */
	private boolean loadMsgToSend(MProjectProcessorLog log) {
		
		MProjectProcessorChange[] changes = log.getChange(null);
		MProjectStatus status = null;
		String nameHeader = "";
		String itemStatus = "";
		MUser createdBy = null;
		MUser updatedBy = null;
		
		if (changes.length==0)
			return false;
		
		for (MProjectProcessorChange change : changes) {
			if (m_PO == null) {
				if (change.getAD_Table_ID()==MProject.Table_ID) {
					m_Project = new MProject (getCtx(), change.getRecord_ID(), change.get_TrxName());
					m_PO = m_Project;
				}else if (change.getAD_Table_ID()==MProjectPhase.Table_ID) {
					m_ProjectPhase = new MProjectPhase (getCtx(), change.getRecord_ID(), change.get_TrxName());
					m_Project = (MProject) m_ProjectPhase.getC_Project();
					m_PO = m_ProjectPhase;
				}else if (change.getAD_Table_ID()==MProjectTask.Table_ID) {
					m_ProjectTask = new MProjectTask (getCtx(), change.getRecord_ID(), change.get_TrxName());
					m_ProjectPhase = (MProjectPhase) m_ProjectTask.getC_ProjectPhase();
					m_Project = (MProject) m_ProjectPhase.getC_Project();
					m_PO = m_ProjectPhase;
				}
			}

			if (m_PO!=null 
					&& isScheduled) 
				m_ExtraMsg = m_PO.get_ValueAsString(MProject.COLUMNNAME_AlertMessage);
			
			if (change.getAD_Column_ID()!=0) {
				MColumn col = MColumn.get (change.getCtx(), change.getAD_Column_ID());
				m_columns.add(col.getColumnName());
				m_Values.add(change.getNewValue());
			}
		}
		
		if (m_Project != null) 
			m_PrefixSubject = "[" + m_Project.getName();
		
		if (m_ProjectTask != null) {
			m_PrefixTextMail = Msg.parseTranslation(log.getCtx(), "@C_ProjectTask_ID@") + " #" + m_ProjectTask.getSeqNo();
			m_PrefixSubject += (Util.isEmpty(m_PrefixSubject) ? "": " - ") + m_PrefixTextMail; 
			nameHeader = m_ProjectTask.getName();
			createdBy = MUser.get(log.getCtx(), m_ProjectTask.getCreatedBy());
			updatedBy = MUser.get(log.getCtx(), m_ProjectTask.getUpdatedBy());
			if (m_ProjectTask.getC_ProjectStatus_ID()!=0) {
				status = (MProjectStatus) m_ProjectTask.getC_ProjectStatus(); 
				itemStatus = "(" + status.getName() + ")";
			}
		}else if (m_ProjectPhase != null) {
			m_PrefixTextMail =Msg.parseTranslation(log.getCtx(), "@C_ProjectPhase_ID@") + " #" + m_ProjectPhase.getSeqNo();
			m_PrefixSubject += (Util.isEmpty(m_PrefixSubject) ? "": " - ") + m_PrefixTextMail;
			nameHeader = m_ProjectPhase.getName();
			createdBy = MUser.get(log.getCtx(), m_ProjectPhase.getCreatedBy());
			updatedBy = MUser.get(log.getCtx(), m_ProjectPhase.getUpdatedBy());
			if (m_ProjectPhase.getC_ProjectStatus_ID()!=0) {
				status = (MProjectStatus) m_ProjectPhase.getC_ProjectStatus(); 
				itemStatus = "(" + status.getName() + ")";
			}
		}else if (m_Project != null) {
			m_PrefixTextMail =Msg.parseTranslation(log.getCtx(), "@C_Project_ID@") + " " + m_Project.getName();
			createdBy = MUser.get(log.getCtx(), m_Project.getCreatedBy());
			updatedBy = MUser.get(log.getCtx(), m_Project.getUpdatedBy());
			if (m_Project.getC_ProjectStatus_ID()!=0) {
				status = (MProjectStatus) m_Project.getC_ProjectStatus(); 
				itemStatus = "(" + status.getName() + ")";
			}
		}
		
		if (!Util.isEmpty(m_PrefixSubject))
			m_PrefixSubject += "] "; 
		
		m_PrefixSubject += itemStatus + " " + nameHeader;
		if (log.getEventChangeLog()!=null) {
			
			if (log.getEventChangeLog().equals(MProjectProcessorLog.EVENTCHANGELOG_Insert)
					&& createdBy!=null)
				m_PrefixTextMail+= " " +Msg.parseTranslation(log.getCtx(), "@CreatedBy@") + " " + createdBy.getName();
			else if (log.getEventChangeLog().equals(MProjectProcessorLog.EVENTCHANGELOG_Update)
					&& updatedBy!=null) 
				m_PrefixTextMail+= " " +Msg.parseTranslation(log.getCtx(), "@UpdatedBy@") + " " + updatedBy.getName();
			
			m_TextNotice = m_PrefixTextMail; 
			StringBuffer sb = new StringBuffer("<HR>\n")
				.append(m_PrefixTextMail + "\n")
				.append(getMessageColumnsChanged())
				.append(getMessageColumnsStatic());
			
			m_PrefixTextMail = sb.toString();
		}
		
		return true;
	}	//loadMsgToSend
	
	/**
	 * Get columns changed on a message
	 * @return
	 */
	private String getMessageColumnsChanged() {
		StringBuffer sb = new StringBuffer();
		
		if (m_columns.size()>0
				|| !Util.isEmpty(m_ExtraMsg))
			sb.append("<ul>\n");
		
		for (int i=0; i < m_columns.size(); i++) {
			String column = m_columns.get(i);
			
			if (ProjectProcessorService.isExcludeColumn(column))
				continue;
			
			String value = m_Values.get(i);
			String item = getItemPO(column,value);
			m_TextNotice += item + "\n";  	
			sb.append(item);
		}
		
		if (!Util.isEmpty(m_ExtraMsg)) {
			sb.append("<li><strong>" + m_ExtraMsg + "</li></strong>");
			m_TextNotice += m_ExtraMsg + "\n";  
		}
		if (m_columns.size()>0
				|| !Util.isEmpty(m_ExtraMsg)) 
			sb.append("</ul>\n")
			.append("<HR>\n");
		
		return sb.toString();
	}	//getMessageColumnsChanged
	
	/**
	 * Get columns static on a message
	 * @return
	 */
	private String getMessageColumnsStatic() {
		
		StringBuffer sb = new StringBuffer();
		String result = "";
		if (m_PO!=null) {
			for (String column : ProjectProcessorService.RESPONSIBLE_COLUMNS) {
				if (m_PO.get_ColumnIndex(column) >= 0)
					sb.append(getItemPO(column,null));
			}
			
			for (String column : ProjectProcessorService.INFO_COLUMNS) {
				if (m_PO.get_ColumnIndex(column) >= 0)
					sb.append(getItemPO(column,null));
			}
			
			for (String column : ProjectProcessorService.TIME_COLUMNS) {
				if (m_PO.get_ColumnIndex(column) >= 0)
					sb.append(getItemPO(column,null));
			}
			if (sb.length()>0) {
				result = "<ul>\n " + sb.toString() + "</ul>\n <HR>\n ";
			}
		}
		return result;
	}	//getMessageColumnsStatic
	
	/**
	 * Get item from PO
	 * @param column
	 * @param value
	 * @return
	 */
	private String getItemPO(String column,String value) {
		
		StringBuffer result = new StringBuffer();
		
		result.append("<li><strong>" + Msg.parseTranslation(m_PO.getCtx(), "@" + column + "@") + ":</strong> ");
		if (value !=null)
			result.append(value);
		else 
			result.append(ProjectProcessorService.getDisplayValue(column,m_PO));
		
		result.append("</li>\n");

		return result.toString();
	}	//getItemPO
	
	/**
	 * Clear globals variables
	 */
	private void clearGlobals() {
		m_PrefixSubject = "";
		m_PrefixTextMail = "";
		m_columns.clear();
		m_Values.clear();
		m_PO = null;
		m_Project = null;
		m_ProjectPhase = null;
		m_ProjectTask = null;
		m_ExtraMsg = "";
		m_TextNotice = "";
		isScheduled = false;
	}	//clearGlobals
	
}	

class ScheduleChange{
	
	private Properties ctx = null;
	private String trxName = null;
	private String tableName = null;
	private String whereClause = null;
	private String [] columnsToSet = new String [] {};
	private Object [] valuesToSet = new Object [] {};
	private Object [] parameters  = new Object [] {};
	private StringBuffer message = new StringBuffer();
	private boolean nextProjectStatus = false;
	
	/**
	 * Constructor
	 * @param ctx
	 * @param tableName
	 * @param whereClause
	 * @param trxName
	 */
	public ScheduleChange(Properties ctx, String tableName,String whereClause,String trxName) {
		this.ctx = ctx;
		this.tableName = tableName;
		this.whereClause = whereClause;
		this.trxName = trxName;
	}
	
	/**
	 * Set Table Name
	 * @param tableName
	 * @return
	 */
	public ScheduleChange setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}
	
	/**
	 * Set Where Clause
	 * @param whereClause
	 * @return
	 */
	public ScheduleChange setWhereClause(String whereClause) {
		this.whereClause = whereClause;
		return this;
	}
	
	/**
	 * Set Columns
	 * @param columns
	 * @return
	 */
	public ScheduleChange setColumns(String... columns) {
		this.columnsToSet = columns;
		return this;
	}
	
	/**
	 * Set Values
	 * @param values
	 * @return
	 */
	public ScheduleChange setValues(Object... values) {
		this.valuesToSet = values;
		return this;
	}
	
	/**
	 * Set Parameters from array
	 * @param parameters
	 * @return
	 */
	public ScheduleChange setParameters(Object... parameters) {
		this.parameters = parameters;
		return this;
	}
	
	/**
	 * Set Parameters from List Object
	 * @param parameters
	 * @return
	 */
	public ScheduleChange setParameters(List<Object> parameters) {
		this.parameters = parameters.toArray();
		return this;
	}
	
	/**
	 * Set Alert Message
	 * @param message
	 * @return
	 */
	public ScheduleChange setAlertMessageColumn(String message) {
		this.message.append(Msg.parseTranslation(ctx,message));
		return this;
	}
	
	/**
	 * if Changed Status
	 * @return
	 */
	public ScheduleChange IsNextProjectStatus() {
		this.nextProjectStatus = true;
		return this;
	}
	
	/**
	 * Process Schedule Changes
	 */
	public void processScheduleChanges() {
		List<PO> entitys = new Query(ctx, tableName, whereClause, trxName)
				.setParameters(parameters)
				.list();
		for (PO entity : entitys) {
			for (int i=0;i<columnsToSet.length;i++) 
				entity.set_ValueOfColumn(columnsToSet[i], valuesToSet[i]);
			
			if (nextProjectStatus) {
				if (entity.get_ColumnIndex(MProjectStatus.COLUMNNAME_C_ProjectStatus_ID)>=0) {
					int projecStatusID = entity.get_ValueAsInt(MProjectStatus.COLUMNNAME_C_ProjectStatus_ID);
					if (projecStatusID > 0) {
						MProjectStatus pStatus = MProjectStatus.get(ctx, entity.get_ValueAsInt(MProjectStatus.COLUMNNAME_C_ProjectStatus_ID));
						entity.set_ValueOfColumn(MProjectStatus.COLUMNNAME_C_ProjectStatus_ID, getNextStatus(pStatus));
					}
					
				}
			}
			
			if (this.message.length()>0) 
				entity.set_CustomColumn(MProject.COLUMNNAME_AlertMessage, this.message.toString());
			
			entity.save();
		}	
	}
	
	/**
	 * get Next Project Status 
	 * @param projectStatus
	 * @return
	 */
	private int getNextStatus(MProjectStatus projectStatus) {
		if (projectStatus.getTimeoutDays() <= 0
				|| projectStatus.getNext_Status_ID() == 0)
			return projectStatus.getC_ProjectStatus_ID();
		
		return projectStatus.getNext_Status_ID();
	}
	
	
}
//	ProjectProcessor
