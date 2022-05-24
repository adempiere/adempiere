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
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

import org.compiere.model.I_R_Request;
import org.compiere.model.MChangeRequest;
import org.compiere.model.MClient;
import org.compiere.model.MGroup;
import org.compiere.model.MMailText;
import org.compiere.model.MRequest;
import org.compiere.model.MRequestProcessor;
import org.compiere.model.MRequestProcessorLog;
import org.compiere.model.MRequestProcessorRoute;
import org.compiere.model.MStatus;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.spin.model.MRNoticeTemplate;
import org.spin.model.MRNoticeTemplateEvent;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

/**
 *	Request Processor
 *	
 *  @author Jorg Janke
 *  @version $Id: RequestProcessor.java,v 1.3 2006/07/30 00:53:33 jjanke Exp $
 */
public class RequestProcessor extends AdempiereServer
{
	/**
	 * 	RequestProcessor
	 *	@param model model
	 */
	public RequestProcessor (MRequestProcessor model)
	{
		super (model, 60);	//	1 minute delay
		m_model = model;
		m_client = MClient.get(model.getCtx(), model.getAD_Client_ID());
	}	//	RequestProcessor
	
	/**	The Concrete Model			*/
	private MRequestProcessor	m_model = null;
	/**	Last Summary				*/
	private StringBuffer 		m_summary = new StringBuffer();
	/** Client onfo					*/
	private MClient 			m_client = null;
	/**	Count						*/
	private int					count = 0;
	/**	Mail Count					*/
	private int 				mailCount = 0;

	/**************************************************************************
	 * 	Do the actual Work
	 */
	protected void doWork()
	{
		m_summary = new StringBuffer();
		//
		processEMail();
		findSalesRep ();
		processStatus();
		processRequests ();
		processECR();
		//
		int no = m_model.deleteLog();
		m_summary.append("Logs deleted=").append(no);
		if (m_model.get_TrxName() == null) {
			addRequestProcessorLog(m_model.get_TrxName());
		} else {
			Trx.run(this::addRequestProcessorLog);
		}
	}	//	doWork

	/**
	 * Add Request Processor Log
	 * @param trxName
	 */
	private void addRequestProcessorLog(String trxName) {
		MRequestProcessorLog requestProcessorLog = new MRequestProcessorLog(m_model, m_summary.toString(), trxName);
		requestProcessorLog.setReference("#" + p_runCount + " - " + TimeUtil.formatElapsed(new Timestamp(p_startWork)));
		requestProcessorLog.saveEx();
	}

	/**************************************************************************
	 *  Process requests.
	 *  Scheduled - are they due?
	 */
	private void processRequests ()
	{
		resetCounter();
		StringBuffer whereClause = new StringBuffer();
		List<Object> parameters = new ArrayList<>();
		/**
		 *  Due Requests
		 */
		resetCounter();
		whereClause = new StringBuffer("DueType = ? AND Processed = 'N' AND AD_Client_ID = ?");
		parameters = new ArrayList<>();
		parameters.add(MRequest.DUETYPE_Scheduled);
		parameters.add(m_model.getAD_Client_ID());
		//	
		if (m_model.getR_RequestType_ID() != 0) {
			whereClause.append(" AND R_RequestType_ID = ?");
			parameters.add(m_model.getR_RequestType_ID());
		}
		whereClause.append(" AND DateNextAction < SysDate");
		//	Query for request
		new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
			.setParameters(parameters)
			.<MRequest>list()
			.forEach(request -> {
				request.setDueType();
				if (request.isDue()) {
					if (request.getRequestType().isEMailWhenDue()) {
						if (sendEmail (request, MRNoticeTemplateEvent.EVENTTYPE_SalesRepDueRequestAlert)) {
							request.setDateLastAlert();
							addMailCount();
						}
					}
					request.saveEx();
					addRecordCount();
				}
			});
		m_summary.append("New Due #").append(count);
		if (mailCount > 0)
			m_summary.append(" (").append(mailCount).append(" EMail)");
		m_summary.append (" - ");
		
		/**
		 *  Overdue Requests.
		 *  Due Requests - are they overdue?
		 */
		resetCounter();
		whereClause = new StringBuffer("DueType = ? AND Processed = 'N' AND AD_Client_ID = ?");
		parameters = new ArrayList<>();
		parameters.add(MRequest.DUETYPE_Due);
		parameters.add(m_model.getAD_Client_ID());
		//	
		if (m_model.getR_RequestType_ID() != 0) {
			whereClause.append(" AND R_RequestType_ID = ?");
			parameters.add(m_model.getR_RequestType_ID());
		}
		whereClause.append(" AND EXISTS(SELECT 1 FROM R_RequestType rt "
				+ "WHERE R_Request.R_RequestType_ID = rt.R_RequestType_ID"
				+ " AND (R_Request.DateNextAction+rt.DueDateTolerance) < SysDate)");
		//	Query for request
		new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
			.setParameters(parameters)
			.<MRequest>list()
			.forEach(request -> {
				request.setDueType();
				if (request.isOverdue()) {
					if (request.getRequestType().isEMailWhenOverdue()
						&& !TimeUtil.isSameDay(request.getDateLastAlert(), null)) {
						if (sendEmail (request, MRNoticeTemplateEvent.EVENTTYPE_AutomaticTaskExpiredTaskAlert)) {
							request.setDateLastAlert();
							addMailCount();
						}
					}
					request.saveEx();
					addRecordCount();
				}
			});
		m_summary.append("New Overdue #").append(count);
		if (mailCount > 0)
			m_summary.append(" (").append(mailCount).append(" EMail)");
		m_summary.append (" - ");
		
		/**
		 *  Send (over)due alerts
		 */
		if (m_model.getOverdueAlertDays() > 0) {
			resetCounter();
			whereClause = new StringBuffer("Processed = 'N' AND AD_Client_ID = ?");
			parameters = new ArrayList<>();
			parameters.add(m_model.getAD_Client_ID());
			whereClause.append(" AND (DateNextAction+" + m_model.getOverdueAlertDays() + ") < SysDate"
					+ " AND (DateLastAlert IS NULL");
			if (m_model.getRemindDays() > 0) {
				whereClause.append(" OR (DateLastAlert+" + m_model.getRemindDays() + ") < SysDate");
			}
			whereClause.append(")");
			//	
			if (m_model.getR_RequestType_ID() != 0) {
				whereClause.append(" AND R_RequestType_ID = ?");
				parameters.add(m_model.getR_RequestType_ID());
			}
			//	Query for request
			new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
				.setParameters(parameters)
				.<MRequest>list()
				.forEach(request -> {
					request.setDueType();
					if (request.getRequestType().isEMailWhenOverdue()
							&& (request.getDateLastAlert() == null
								|| !TimeUtil.isSameDay(request.getDateLastAlert(), null))){
						if (sendEmail (request, MRNoticeTemplateEvent.EVENTTYPE_AutomaticTaskExpiredTaskAlert)) {
							request.setDateLastAlert();
							addMailCount();
						}
						request.saveEx();
						addRecordCount();
					}
				});
			m_summary.append("Alerts #").append(count);
			if (mailCount > 0)
				m_summary.append(" (").append(mailCount).append(" EMail)");
			m_summary.append (" - ");
		}	//	Overdue
		
		/**
		 *  Escalate
		 */
		if (m_model.getOverdueAssignDays() > 0) {
			resetCounter();
			whereClause = new StringBuffer("Processed = 'N' AND AD_Client_ID = ? AND IsEscalated='N'");
			parameters = new ArrayList<>();
			parameters.add(m_model.getAD_Client_ID());
			whereClause.append(" AND (DateNextAction+" + m_model.getOverdueAssignDays() + ") < SysDate");
			//	
			if (m_model.getR_RequestType_ID() != 0) {
				whereClause.append(" AND R_RequestType_ID = ?");
				parameters.add(m_model.getR_RequestType_ID());
			}
			//	Query for request
			new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
				.setParameters(parameters)
				.<MRequest>list()
				.forEach(request -> {
					if(escalate(request)) {
						addRecordCount();
					}
				});
			m_summary.append("Escalated #").append(count).append(" - ");
		}	//	Esacalate
		
		/**
		 *  Send inactivity alerts
		 */
		if (m_model.getInactivityAlertDays() > 0)
		{
			resetCounter();
			whereClause = new StringBuffer("Processed = 'N' AND AD_Client_ID = ?");
			parameters = new ArrayList<>();
			parameters.add(m_model.getAD_Client_ID());
			whereClause.append(" AND (Updated+" + m_model.getInactivityAlertDays() + ") < SysDate AND (DateLastAlert IS NULL");
			if (m_model.getRemindDays() > 0) {
				whereClause.append(" OR (DateLastAlert+" + m_model.getRemindDays() + ") < SysDate");
			}
			whereClause.append(")");
			//	
			if (m_model.getR_RequestType_ID() != 0) {
				whereClause.append(" AND R_RequestType_ID = ?");
				parameters.add(m_model.getR_RequestType_ID());
			}
			//	Query for request
			new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
				.setParameters(parameters)
				.<MRequest>list()
				.forEach(request -> {
					if (request.getDateLastAlert() == null
							|| !TimeUtil.isSameDay(request.getDateLastAlert(), null)) {
						if (sendEmail (request, MRNoticeTemplateEvent.EVENTTYPE_AutomaticTaskExpiredTaskAlert)) {
							request.setDateLastAlert();
							addMailCount();
						}
						request.saveEx();
						addRecordCount();
					}
				});
			m_summary.append("Inactivity #").append(count).append(" - ");
			if (mailCount > 0)
				m_summary.append(" (").append(mailCount).append(" EMail)");
			m_summary.append (" - ");
		}	//	Inactivity		
	}	//  processRequests

	/**
	 *  Send Alert EMail
	 *  @param request request
	 *  @param eventType Event Type
	 *  @return true if sent
	 */
	private boolean sendEmail (MRequest request, String eventType) {
		AtomicReference<String> subject = new AtomicReference<String>();
		AtomicReference<String> message = new AtomicReference<String>();
		//	Event Type
		if(!Util.isEmpty(eventType)) {
			MMailText mailText = MRNoticeTemplate.getMailTemplate(getCtx(), MRNoticeTemplate.TEMPLATETYPE_Request, eventType);
			if(mailText != null) {
				//	Add Request
				mailText.setPO(request, true);
				subject.set(mailText.getMailHeader());
				//	Message
				message.set(mailText.getMailText(true));
			}
		}
		if(Util.isEmpty(subject.get())
				&& Util.isEmpty(message.get())) {
			//  Alert: Request {0} overdue
			subject.set(Msg.getMsg(m_client.getAD_Language(), "RequestDue", 
				new String[] {request.getDocumentNo()}));
			message.set(request.getSummary());
		}
		//	
		Trx.run(transactionName -> {
			//	Get instance for notifier
			try {
				DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
						.withContext(getCtx())
						.withTransactionName(transactionName);
				//	Send notification to queue
				notifier
					.clearMessage()
					.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
					.withUserId(request.getUpdatedBy())
					.addRecipient(request.getSalesRep_ID())
					.withText(message.get())
					.addAttachment(request.createPDF())
					.withDescription(subject.get());
				//	Add to queue
				notifier.addToQueue();
			} catch (Exception e) {
				log.severe(e.getLocalizedMessage());
			}
		});
		return true;
	}   //  sendAlert

	/**
	 *  Escalate
	 *  @param request request
	 * 	@return true if saved
	 */
	private boolean escalate (MRequest request)
	{
		//  Get Supervisor
		MUser supervisor = request.getSalesRep();	//	self
		int supervisor_ID = request.getSalesRep().getSupervisor_ID();
		if (supervisor_ID == 0 && m_model.getSupervisor_ID() != 0)
			supervisor_ID = m_model.getSupervisor_ID();
		if (supervisor_ID != 0 && supervisor_ID != request.getAD_User_ID())
			supervisor = MUser.get(getCtx(), supervisor_ID);
		
		//  Escalated: Request {0} to {1}
		AtomicReference<String> subject = new AtomicReference<String>();
		AtomicReference<String> message = new AtomicReference<String>();
		//	Event Type
		MMailText mailText = MRNoticeTemplate.getMailTemplate(getCtx(), MRNoticeTemplate.TEMPLATETYPE_Request, MRNoticeTemplateEvent.EVENTTYPE_AutomaticTaskTaskTransferNotice);
		if(mailText != null) {
			//	Add Request
			mailText.setPO(request, true);
			subject.set(mailText.getMailHeader());
			//	Message
			message.set(mailText.getMailText(true));
		}
		if(Util.isEmpty(subject.get())
				&& Util.isEmpty(message.get())) {
			//  Alert: Request {0} overdue
			subject.set(Msg.getMsg(m_client.getAD_Language(), "RequestEscalate", new String[] {request.getDocumentNo(), supervisor.getName()}));
			message.set(request.getSummary());
		}
		List<Integer> recipients = new ArrayList<>();
		recipients.add(request.getSalesRep_ID());
		//	Not the same - send mail to supervisor
		if (request.getSalesRep_ID() != supervisor.getAD_User_ID()) {
			recipients.add(supervisor.getAD_User_ID());
		}
		//	
		Trx.run(transactionName -> {
			//	Get instance for notifier
			DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
					.withContext(getCtx())
					.withTransactionName(transactionName);
			//	Send notification to queue
			notifier
				.clearMessage()
				.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
				.withText(message.get())
				.addAttachment(request.createPDF())
				.withDescription(subject.get());
			//	Add recipients
			recipients.forEach(recipientId -> notifier.addRecipient(recipientId));
			//	Add to queue
			notifier.addToQueue();
		});
		//  ----------------
		request.setDueType();
		request.setIsEscalated(true);
		request.setResult(subject.get());
		return request.save();
	}   //  escalate

	/**
	 * Add Record count
	 */
	private void addRecordCount() {
		count++;
	}
	
	/**
	 * Add mail count
	 */
	private void addMailCount() {
		mailCount++;
	}
	
	/**
	 * Reset Counter
	 */
	private void resetCounter() {
		count = 0;
		mailCount = 0;
	}

	/**************************************************************************
	 * 	Process Request Status
	 */
	private void processStatus()
	{
		resetCounter();
		StringBuffer whereClause = new StringBuffer("EXISTS("
				+ "SELECT 1 FROM R_Status s "
				+ "WHERE R_Request.R_Status_ID=s.R_Status_ID"
				+ " AND s.TimeoutDays > 0 AND s.Next_Status_ID > 0"
				+ " AND R_Request.DateLastAction+s.TimeoutDays < SysDate"
				+ ")");
		//	Query for request
		new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
			.setOrderBy(I_R_Request.COLUMNNAME_R_Status_ID)
			.<MRequest>list()
			.stream()
			.filter(request -> MStatus.get(getCtx(), request.getR_Status_ID()).getTimeoutDays() > 0 
					&& MStatus.get(getCtx(), request.getR_Status_ID()).getNext_Status_ID() > 0)
			.forEach(request -> {
				//	Get/Check Status
				MStatus status = MStatus.get(getCtx(), request.getR_Status_ID());
				//	Next Status
				MStatus next = MStatus.get(getCtx(), status.getNext_Status_ID());
				//
				String result = Msg.getMsg(getCtx(), "RequestStatusTimeout")
					+ ": " + status.getName() + " -> " + next.getName();
				request.setResult(result);
				request.setR_Status_ID(status.getNext_Status_ID());
				if(request.save()) {
					addRecordCount();
				}
			});	
		m_summary.append("Status Timeout #").append(count)
			.append(" - ");
	}	//	processStatus
	
	/**
	 * 	Create ECR
	 */
	private void processECR()
	{
		//	Get Requests with Request Type-AutoChangeRequest and Group with info
		resetCounter();
		StringBuffer whereClause = new StringBuffer("M_ChangeRequest_ID IS NULL"
			+ " AND EXISTS("
				+ "SELECT 1 FROM R_RequestType rt "
				+ "WHERE rt.R_RequestType_ID = R_Request.R_RequestType_ID"
				+ " AND rt.IsAutoChangeRequest='Y')"
			+ "AND EXISTS ("
				+ "SELECT 1 FROM R_Group g "
				+ "WHERE g.R_Group_ID = R_Request.R_Group_ID"
				+ " AND (g.M_BOM_ID IS NOT NULL OR g.M_ChangeNotice_ID IS NOT NULL)	)");
		//	Query for request
		new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
			.setOrderBy(I_R_Request.COLUMNNAME_R_Status_ID)
			.<MRequest>list()
			.forEach(request -> {
				MGroup requestGroup = MGroup.get(getCtx(), request.getR_Group_ID());
				MChangeRequest changeRequest = new MChangeRequest(request, requestGroup);
				if (request.save()) {
					request.setM_ChangeRequest_ID(changeRequest.getM_ChangeRequest_ID());
					if (request.save()) {
						addMailCount();
					}
				}
				addRecordCount();
			});
		m_summary.append("Auto Change Request #").append(count);
		if ((count - mailCount) > 0)
			m_summary.append("(fail=").append((count - mailCount)).append(")");
		m_summary.append(" - ");
	}	//	processECR
	
	
	/**************************************************************************
	 *	Create Reauest / Updates from EMail
	 */
	private void processEMail ()
	{
	//	m_summary.append("Mail #").append(count)
	//		.append(" - ");
	}   //  processEMail

	
	/**************************************************************************
	 * 	Allocate Sales Rep
	 */
	private void findSalesRep () {
		StringBuffer whereClause = new StringBuffer("Processed = 'N' AND AD_Client_ID = ? AND (SalesRep_ID = 0 OR SalesRep_ID IS NULL)");
		List<Object> parameters = new ArrayList<>();
		parameters.add(m_model.getAD_Client_ID());
		resetCounter();
		//	Query for request
		new Query(getCtx(), I_R_Request.Table_Name, whereClause.toString(), null)
			.setParameters(parameters)
			.<MRequest>list()
			.forEach(request -> {
				int salesRepId = findSalesRep(request);
				if (salesRepId != 0) {
					request.setSalesRep_ID(salesRepId);
					if(request.save()) {
						addMailCount();
					}
				}
				addRecordCount();
			});
		//
		if (mailCount == 0 && (count - mailCount) == 0)
			m_summary.append("No unallocated Requests");
		else
			m_summary.append("Allocated SalesRep=").append(mailCount);
		if ((count - mailCount) > 0)
			m_summary.append(",Not=").append((count - mailCount));
		m_summary.append(" - ");
	}	//	findSalesRep

	/**
	 *  Find SalesRep/User based on Request Type and Question.
	 *  @param request request
	 *  @return SalesRep_ID user
	 */
	private int findSalesRep (MRequest request)
	{
		String QText = request.getSummary();
		if (QText == null)
			QText = "";
		else
			QText = QText.toUpperCase();
		//
		MRequestProcessorRoute[] routes = m_model.getRoutes(false);
		for (int i = 0; i < routes.length; i++) {
			MRequestProcessorRoute route = routes[i];
			
			//	Match first on Request Type
			if (request.getR_RequestType_ID() == route.getR_RequestType_ID()
				&& route.getR_RequestType_ID() != 0)
				return route.getAD_User_ID();
			
			//	Match on element of keyword
			String keyword = route.getKeyword();
			if (keyword != null)
			{
				StringTokenizer st = new StringTokenizer(keyword.toUpperCase(), " ,;\t\n\r\f");
				while (st.hasMoreElements()) {
					if (QText.indexOf(st.nextToken()) != -1)
						return route.getAD_User_ID();
				}
			}
		}	//	for all routes

		return m_model.getSupervisor_ID();
	}   //  findSalesRep
	
	/**
	 * 	Get Server Info
	 *	@return info
	 */
	public String getServerInfo()
	{
		return "#" + p_runCount + " - Last=" + m_summary.toString();
	}	//	getServerInfo
	
}	//	RequestProcessor
