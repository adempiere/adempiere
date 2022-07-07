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
package org.compiere.model;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.spin.model.MRNoticeTemplate;
import org.spin.model.MRNoticeTemplateEvent;
import org.spin.queue.notification.DefaultNotifier;
import org.spin.queue.util.QueueLoader;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * 	Request Model
 *
 *  @author Jorg Janke
 *  @version $Id: MRequest.java,v 1.2 2006/07/30 00:51:03 jjanke Exp $
 */
public class MRequest extends X_R_Request
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6049674214655497548L;

	/**
	 * 	Get Request ID from mail text
	 *	@param mailText mail text
	 *	@return ID if it contains request tag otherwise 0
	 */
	public static int getR_Request_ID (String mailText)
	{
		if (mailText == null)
			return 0;
		int indexStart = mailText.indexOf(TAG_START);
		if (indexStart == -1)
			return 0;
		int indexEnd = mailText.indexOf(TAG_END, indexStart);
		if (indexEnd == -1)
			return 0;
		//
		indexStart += 5; 
		String idString = mailText.substring(indexStart, indexEnd);
		int R_Request_ID = 0;
		try
		{
			R_Request_ID = Integer.parseInt(idString);
		}
		catch (Exception e)
		{
			s_log.severe ("Cannot parse " + idString);
		}
		return R_Request_ID;
	}	//	getR_Request_ID

	/**	Static Logger					*/
	private static CLogger s_log	= CLogger.getCLogger (MRequest.class);
	/** Request Tag Start				*/
	private static final String		TAG_START = "[Req#";
	/** Request Tag End					*/
	private static final String		TAG_END = "#ID]";
	
	
	/**************************************************************************
	 * 	Constructor
	 * 	@param ctx context
	 * 	@param R_Request_ID request or 0 for new
	 *	@param trxName transaction
	 */
	public MRequest(Properties ctx, int R_Request_ID, String trxName)
	{
		super (ctx, R_Request_ID, trxName);
		if (R_Request_ID == 0)
		{
			setDueType (DUETYPE_Due);
		//  setSalesRep_ID (0);
		//	setDocumentNo (null);
			setConfidentialType (CONFIDENTIALTYPE_PublicInformation);	// A
			setConfidentialTypeEntry (CONFIDENTIALTYPEENTRY_PublicInformation);	// A
			setProcessed (false);
			setRequestAmt (Env.ZERO);
			setPriorityUser (PRIORITY_Low);
		//  setR_RequestType_ID (0);
		//  setSummary (null);
			setIsEscalated (false);
			setIsSelfService (false);
			setIsInvoiced (false);
		}
	}	//	MRequest

	/**
	 * 	SelfService Constructor
	 * 	@param ctx context
	 * 	@param SalesRep_ID SalesRep
	 * 	@param R_RequestType_ID request type
	 * 	@param Summary summary
	 * 	@param isSelfService self service
	 *	@param trxName transaction
	 */
	public MRequest (Properties ctx, int SalesRep_ID,
		int R_RequestType_ID, String Summary, boolean isSelfService, String trxName)
	{
		this(ctx, 0, trxName);
		set_Value("SalesRep_ID", Integer.valueOf(SalesRep_ID));	//	could be 0
		set_Value("R_RequestType_ID", Integer.valueOf(R_RequestType_ID));
		setSummary (Summary);
		setIsSelfService(isSelfService);
		if (getRequestType() != null)
		{
			String ct = getRequestType().getConfidentialType();
			if (ct != null)
			{
				setConfidentialType (ct);
				setConfidentialTypeEntry (ct);
			}
		}
	}	//	MRequest

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MRequest (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MRequest

	/** Request Type				*/
	private MRequestType requestType = null;
	/**	Changed						*/
	private boolean			m_changed = false;
	/**	BPartner					*/
	private MBPartner m_partner = null;
	/** User/Contact				*/
	private MUser m_user = null;
	/** List of EMail Notices		*/
	private StringBuffer	m_emailTo = new StringBuffer();

	/** Separator line				*/
	public static final String	SEPARATOR = 
		"\n---------.----------.----------.----------.----------.----------\n";
	
	/**************************************************************************
	 * 	Set Default Request Type.
	 */
	public void setR_RequestType_ID ()
	{
		requestType = MRequestType.getDefault(getCtx());
		if (requestType == null)
			log.warning("No default found");
		else
			super.setR_RequestType_ID(requestType.getR_RequestType_ID());
	}	//	setR_RequestType_ID

	/**
	 * 	Set Default Request Status.
	 */
	public void setR_Status_ID ()
	{
		MStatus status = MStatus.getDefault(getCtx(), getR_RequestType_ID());
		if (status == null)
		{
			log.warning("No default found");
			if (getR_Status_ID() != 0)
				setR_Status_ID(0);
		}
		else
			setR_Status_ID(status.getR_Status_ID());
	}	//	setR_Status_ID
	
	/**
	 * 	Add To Result
	 * 	@param Result
	 */
	public void addToResult (String Result)
	{
		String oldResult = getResult();
		if (Result == null || Result.length() == 0)
			;
		else if (oldResult == null || oldResult.length() == 0)
			setResult (Result);
		else
			setResult (oldResult + "\n-\n" + Result);
	}	//	addToResult

	/**
	 * 	Set DueType based on Date Next Action
	 */
	public void setDueType()
	{
		Timestamp due = getDateNextAction();
		if (due == null)
			return;
		//
		Timestamp overdue = TimeUtil.addDays(due, getRequestType().getDueDateTolerance());
		Timestamp now = new Timestamp (System.currentTimeMillis());
		//
		String DueType = DUETYPE_Due;
		if (now.before(due))
			DueType = DUETYPE_Scheduled;
		else if (now.after(overdue))
			DueType = DUETYPE_Overdue;
		super.setDueType(DueType);
	}	//	setDueType

	
	/**************************************************************************
	 * 	Get Action History
	 *	@return array of actions
	 */
	public MRequestAction[] getActions()
	{
		final String whereClause = MRequestAction.COLUMNNAME_R_Request_ID+"=?";
		List<MRequestAction> list = new Query(getCtx(), I_R_RequestAction.Table_Name, whereClause, get_TrxName())
										.setParameters(get_ID())
										.setOrderBy("Created DESC")
										.list();
		return list.toArray(new MRequestAction[list.size()]);
	}	//	getActions

	/**
	 * 	Get Updates
	 * 	@param confidentialType maximum confidential type - null = all
	 *	@return updates
	 */
	public MRequestUpdate[] getUpdates(String confidentialType)
	{
		final String whereClause = MRequestUpdate.COLUMNNAME_R_Request_ID+"=?";
		List<MRequestUpdate> listUpdates = new Query(getCtx(), I_R_RequestUpdate.Table_Name, whereClause, get_TrxName())
										.setParameters(get_ID())
										.setOrderBy("Created DESC")
										.list();
		ArrayList<MRequestUpdate> list = new ArrayList<MRequestUpdate>();
		for (MRequestUpdate ru : listUpdates)
		{
			if (confidentialType != null)
			{
				//	Private only if private
				if (ru.getConfidentialTypeEntry().equals(CONFIDENTIALTYPEENTRY_PrivateInformation)
						&& !confidentialType.equals(CONFIDENTIALTYPEENTRY_PrivateInformation))
					continue;
				//	Internal not if Customer/Public
				if (ru.getConfidentialTypeEntry().equals(CONFIDENTIALTYPEENTRY_Internal)
						&& (confidentialType.equals(CONFIDENTIALTYPEENTRY_PartnerConfidential)
								|| confidentialType.equals(CONFIDENTIALTYPEENTRY_PublicInformation)))
					continue;
				//	No Customer if public
				if (ru.getConfidentialTypeEntry().equals(CONFIDENTIALTYPEENTRY_PartnerConfidential)
						&& confidentialType.equals(CONFIDENTIALTYPEENTRY_PublicInformation))
					continue;
			}
			list.add(ru);
		}
		//
		MRequestUpdate[] retValue = new MRequestUpdate[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getUpdates
	
	/**
	 * 	Get Public Updates
	 *	@return public updates
	 */
	public MRequestUpdate[] getUpdatesPublic()
	{
		return getUpdates(CONFIDENTIALTYPE_PublicInformation);
	}	//	getUpdatesPublic

	/**
	 * 	Get Customer Updates
	 *	@return customer updates
	 */
	public MRequestUpdate[] getUpdatesCustomer()
	{
		return getUpdates(CONFIDENTIALTYPE_PartnerConfidential);
	}	//	getUpdatesCustomer

	/**
	 * 	Get Internal Updates
	 *	@return internal updates
	 */
	public MRequestUpdate[] getUpdatesInternal()
	{
		return getUpdates(CONFIDENTIALTYPE_Internal);
	}	//	getUpdatesInternal

	/**
	 *	Get Request Type
	 *	@return Request Type 	
	 */
	public MRequestType getRequestType()
	{
		if (requestType == null)
		{
			int R_RequestType_ID = getR_RequestType_ID();
			if (R_RequestType_ID == 0)
			{
				setR_RequestType_ID();
				R_RequestType_ID = getR_RequestType_ID();
			}
			requestType = MRequestType.get (getCtx(), R_RequestType_ID);
		}
		return requestType;
	}	//	getRequestType

	
	/**
	 *	Get Request Type Text (for jsp)
	 *	@return Request Type Text	
	 */
	public String getRequestTypeName()
	{
		if (requestType == null)
			getRequestType();
		if (requestType == null)
			return "??";
		return requestType.getName();
	}	//	getRequestTypeText

	/**
	 * 	Get Request Category
	 *	@return category
	 */
	public MRequestCategory getCategory()
	{
		if (getR_Category_ID() == 0)
			return null;
		return MRequestCategory.get(getCtx(), getR_Category_ID());
	}	//	getCategory

	/**
	 * 	Get Request Category Name
	 *	@return name
	 */
	public String getCategoryName()
	{
		MRequestCategory cat = getCategory();
		if (cat == null)
			return "";
		return cat.getName();
	}	//	getCategoryName

	/**
	 * 	Get Request Group
	 *	@return group
	 */
	public MGroup getGroup()
	{
		if (getR_Group_ID() == 0)
			return null;
		return MGroup.get(getCtx(), getR_Group_ID());
	}	//	getGroup

	/**
	 * 	Get Request Group Name
	 *	@return name
	 */
	public String getGroupName()
	{
		MGroup grp = getGroup();
		if (grp == null)
			return "";
		return grp.getName();
	}	//	getGroupName
	
	/**
	 * 	Get Status
	 *	@return status
	 */
	public MStatus getStatus()
	{
		if (getR_Status_ID() == 0)
			return null;
		return MStatus.get(getCtx(), getR_Status_ID());
	}	//	getStatus
	
	/**
	 * 	Get Request Status Name
	 *	@return name
	 */
	public String getStatusName()
	{
		MStatus sta = getStatus();
		if (sta == null)
			return "?";
		return sta.getName();
	}	//	getStatusName
	
	/**
	 * 	Get Request Resolution
	 *	@return resolution
	 */
	public MResolution getResolution()
	{
		if (getR_Resolution_ID() == 0)
			return null;
		return MResolution.get(getCtx(), getR_Resolution_ID());
	}	//	getResolution
	
	/**
	 * 	Get Request Resolution Name
	 *	@return name
	 */
	public String getResolutionName()
	{
		MResolution res = getResolution();
		if (res == null)
			return "";
		return res.getName();
	}	//	getResolutionName

	/**
	 * 	Is Overdue
	 *	@return true if overdue
	 */
	public boolean isOverdue()
	{
		return DUETYPE_Overdue.equals(getDueType());
	}	//	isOverdue

	/**
	 * 	Is due
	 *	@return true if due
	 */
	public boolean isDue()
	{
		return DUETYPE_Due.equals(getDueType());
	}	//	isDue

	/**
	 * 	Get DueType Text (for jsp)
	 *	@return text
	 */
	public String getDueTypeText()
	{
		return MRefList.getListName(getCtx(), DUETYPE_AD_Reference_ID, getDueType());
	}	//	getDueTypeText
	
	/**
	 * 	Get Priority Text (for jsp)
	 *	@return text
	 */
	public String getPriorityText()
	{
		return MRefList.getListName(getCtx(), PRIORITY_AD_Reference_ID, getPriority());
	}	//	getPriorityText

	/**
	 * 	Get Importance Text (for jsp)
	 *	@return text
	 */
	public String getPriorityUserText()
	{
		return MRefList.getListName(getCtx(), PRIORITYUSER_AD_Reference_ID, getPriorityUser());
	}	//	getPriorityUserText

	/**
	 * 	Get Confidential Text (for jsp)
	 *	@return text
	 */
	public String getConfidentialText()
	{
		return MRefList.getListName(getCtx(), CONFIDENTIALTYPE_AD_Reference_ID, getConfidentialType());
	}	//	getConfidentialText

	/**
	 * 	Get Confidential Entry Text (for jsp)
	 *	@return text
	 */
	public String getConfidentialEntryText()
	{
		return MRefList.getListName(getCtx(), CONFIDENTIALTYPEENTRY_AD_Reference_ID, getConfidentialTypeEntry());
	}	//	getConfidentialTextEntry
	
	/**
	 * 	Set Date Last Alert to today
	 */
	public void setDateLastAlert ()
	{
		super.setDateLastAlert (new Timestamp(System.currentTimeMillis()));
	}	//	setDateLastAlert

	/**
	 * 	Get Sales Rep
	 *	@return Sales Rep User
	 */
	public MUser getSalesRep()
	{
		if (getSalesRep_ID() == 0)
			return null;
		return MUser.get(getCtx(), getSalesRep_ID());
	}	//	getSalesRep
	
	/**
	 * 	Get Sales Rep Name
	 *	@return Sales Rep User
	 */
	public String getSalesRepName()
	{
		MUser sr = getSalesRep();
		if (sr == null)
			return "n/a";
		return sr.getName();
	}	//	getSalesRepName

	/**
	 * 	Get Name of creator
	 *	@return name
	 */
	public String getCreatedByName()
	{
		MUser user = MUser.get(getCtx(), getCreatedBy());
		return user.getName();
	}	//	getCreatedByName

	/**
	 * 	Get Contact (may be not defined)
	 *	@return Sales Rep User
	 */
	public MUser getUser()
	{
		if (getAD_User_ID() == 0)
			return null;
		if (m_user != null && m_user.getAD_User_ID() != getAD_User_ID())
			m_user = null;
		if (m_user == null)
			m_user = new MUser(getCtx(), getAD_User_ID(), get_TrxName());
		return m_user;
	}	//	getUser
	
	/**
	 * 	Get BPartner (may be not defined)
	 *	@return Sales Rep User
	 */
	public MBPartner getBPartner()
	{
		if (getC_BPartner_ID() == 0)
			return null;
		if (m_partner != null && m_partner.getC_BPartner_ID() != getC_BPartner_ID())
			m_partner = null;
		if (m_partner == null)
			m_partner = new MBPartner(getCtx(), getC_BPartner_ID(), get_TrxName());
		return m_partner;
	}	//	getBPartner

	/**
	 * 	Web Can Update Request
	 *	@return true if Web can update
	 */
	public boolean isWebCanUpdate()
	{
		if (isProcessed())
			return false;
		if (getR_Status_ID() == 0)
			setR_Status_ID();
		if (getR_Status_ID() == 0)
			return false;
		MStatus status = MStatus.get(getCtx(), getR_Status_ID());
		if (status == null)
			return false;
		return status.isWebCanUpdate();
	}	//	isWebCanUpdate
	

	/**
	 * 	Set Priority
	 */
	private void setPriority()
	{
		if (getPriorityUser() == null)
			setPriorityUser(PRIORITYUSER_Low);
		//
		if (getBPartner() != null)
		{
			MBPGroup bpg = MBPGroup.get(getCtx(), getBPartner().getC_BP_Group_ID());
			String prioBase = bpg.getPriorityBase();
			if (prioBase != null && !prioBase.equals(X_C_BP_Group.PRIORITYBASE_Same))
			{
				char targetPrio = getPriorityUser().charAt(0);
				if (prioBase.equals(X_C_BP_Group.PRIORITYBASE_Lower))
					targetPrio += 2;
				else
					targetPrio -= 2;
				if (targetPrio < PRIORITY_High.charAt(0))	//	1
					targetPrio = PRIORITY_High.charAt(0);
				if (targetPrio > PRIORITY_Low.charAt(0))	//	9
					targetPrio = PRIORITY_Low.charAt(0);
				if (getPriority() == null)
					setPriority(String.valueOf(targetPrio));
				else	//	previous priority
				{
					if (targetPrio < getPriority().charAt(0))
						setPriority(String.valueOf(targetPrio));
				}
			}
		}
		//	Same if nothing else
		if (getPriority() == null)
			setPriority(getPriorityUser());
	}	//	setPriority
	
	/**
	 * 	Set Confidential Type Entry
	 *	@param ConfidentialTypeEntry confidentiality
	 */
	public void setConfidentialTypeEntry (String ConfidentialTypeEntry)
	{
		if (ConfidentialTypeEntry == null)
			ConfidentialTypeEntry = getConfidentialType();
		//
		if (CONFIDENTIALTYPE_Internal.equals(getConfidentialType()))
			super.setConfidentialTypeEntry (CONFIDENTIALTYPE_Internal);
		else if (CONFIDENTIALTYPE_PrivateInformation.equals(getConfidentialType()))
		{
			if (CONFIDENTIALTYPE_Internal.equals(ConfidentialTypeEntry)
				|| CONFIDENTIALTYPE_PrivateInformation.equals(ConfidentialTypeEntry))
				super.setConfidentialTypeEntry (ConfidentialTypeEntry);
			else
				super.setConfidentialTypeEntry (CONFIDENTIALTYPE_PrivateInformation);
		}
		else if (CONFIDENTIALTYPE_PartnerConfidential.equals(getConfidentialType()))
		{
			if (CONFIDENTIALTYPE_Internal.equals(ConfidentialTypeEntry)
				|| CONFIDENTIALTYPE_PrivateInformation.equals(ConfidentialTypeEntry)
				|| CONFIDENTIALTYPE_PartnerConfidential.equals(ConfidentialTypeEntry))
				super.setConfidentialTypeEntry (ConfidentialTypeEntry);
			else
				super.setConfidentialTypeEntry (CONFIDENTIALTYPE_PartnerConfidential);
		}
		else if (CONFIDENTIALTYPE_PublicInformation.equals(getConfidentialType()))
			super.setConfidentialTypeEntry (ConfidentialTypeEntry);
	}	//	setConfidentialTypeEntry
	
	/**
	 * 	Web Update
	 *	@param result result
	 *	@return true if updated
	 */
	public boolean webUpdate (String result)
	{
		MStatus status = MStatus.get(getCtx(), getR_Status_ID());
		if (!status.isWebCanUpdate())
			return false;
		if (status.getUpdate_Status_ID() > 0)
			setR_Status_ID(status.getUpdate_Status_ID());
		setResult(result);
		return true;
	}	//	webUpdate
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MRequest[");
		sb.append (get_ID()).append ("-").append(getDocumentNo()).append("-").append(getSummary()).append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	Create PDF
	 *	@return pdf or null
	 */
	public File createPDF ()
	{
		// globalqss - comment to solve bug [ 1688794 ] System is generating lots of temp files
//		try
//		{
//			File temp = File.createTempFile(get_TableName()+get_ID()+"_", ".pdf");
//			return createPDF (temp);
//		}
//		catch (Exception e)
//		{
//			log.severe("Could not create PDF - " + e.getMessage());
//		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (newRecord || is_ValueChanged("R_RequestType_ID"))
		{
			if (getRequestType() != null)
			{
				if (isInvoiced() != getRequestType().isInvoiced()) {
					setIsInvoiced(getRequestType().isInvoiced());
				}
				if (getDateNextAction() == null && getRequestType().getAutoDueDateDays() > 0)
					setDateNextAction(TimeUtil.addDays(new Timestamp(System.currentTimeMillis()),
							getRequestType().getAutoDueDateDays()));
			}
			//	Is Status Valid
			if (getR_Status_ID() != 0)
			{
				MStatus sta = MStatus.get(getCtx(), getR_Status_ID());
				MRequestType rt = MRequestType.get(getCtx(), getR_RequestType_ID());
				if (sta.getR_StatusCategory_ID() != rt.getR_StatusCategory_ID())
					setR_Status_ID();	//	set to default
			}
		}
		if (isInvoiced()) {
			Optional.ofNullable(getResult()).ifPresent(result -> {
				Optional<BigDecimal> maybeQuantityToInvoice = Optional.ofNullable(getQtyInvoiced());
				if (maybeQuantityToInvoice.isPresent() && maybeQuantityToInvoice.get().signum() == 0)
					throw new AdempiereException("@R_Request_ID@ @IsInvoiced@ @And@ @QtyInvoiced@ @NotFound@");
			});
		}

		//	Request Status
		if (getR_Status_ID() == 0)
			setR_Status_ID();
		//	Validate/Update Due Type
		setDueType();
		MStatus status = MStatus.get(getCtx(), getR_Status_ID());
		//	Close/Open
		if (status != null)
		{
			if (status.isOpen())
			{
				if (getStartDate() == null)
					setStartDate (new Timestamp(System.currentTimeMillis()));
				if (getCloseDate() != null)
					setCloseDate(null);
			}
			if (status.isClosed() 
				&& getCloseDate() == null)
				setCloseDate(new Timestamp(System.currentTimeMillis()));
			if (status.isFinalClose())
				setProcessed(true);
		}
		
		//	Confidential Info
		if (getConfidentialType() == null)
		{
			if (getRequestType() != null)
			{
				String ct = getRequestType().getConfidentialType();
				if (ct != null)
					setConfidentialType (ct);
			}
			if (getConfidentialType() == null)
				setConfidentialType(CONFIDENTIALTYPEENTRY_PublicInformation);
		}
		if (getConfidentialTypeEntry() == null)
			setConfidentialTypeEntry(getConfidentialType());
		else
			setConfidentialTypeEntry(getConfidentialTypeEntry());

		//	Importance / Priority
		setPriority();

		if (getC_ProjectPhase_ID() > 0 )
		{
			I_C_ProjectPhase projectPhase = getC_ProjectPhase();
			if (projectPhase != null && getC_Project_ID() <= 0)
				setC_Project_ID(projectPhase.getC_Project_ID());

		}

		if (getC_ProjectTask_ID() > 0)
		{
			I_C_ProjectTask projectTask =  getC_ProjectTask();
			if (projectTask != null && getC_ProjectPhase_ID() <= 0)
				setC_ProjectPhase_ID(projectTask.getC_ProjectPhase_ID());
		}
				
		//	New
		if (newRecord)
			return true;
		
		//	Change Log
		m_changed = false;
		ArrayList<String> sendInfo = new ArrayList<String>();
		MRequestAction requestAction = new MRequestAction(this, false);
		//
		if (checkChange(requestAction, "R_RequestType_ID"))
			sendInfo.add("R_RequestType_ID");
		if (checkChange(requestAction, "R_Group_ID"))
			sendInfo.add("R_Group_ID");
		if (checkChange(requestAction, "R_Category_ID"))
			sendInfo.add("R_Category_ID");
		if (checkChange(requestAction, "R_Status_ID"))
			sendInfo.add("R_Status_ID");
		if (checkChange(requestAction, "R_Resolution_ID"))
			sendInfo.add("R_Resolution_ID");
		//
		if (checkChange(requestAction, "SalesRep_ID"))
		{
			//	Sender
			int AD_User_ID = Env.getContextAsInt(p_ctx, "#AD_User_ID");
			if (AD_User_ID == 0)
				AD_User_ID = getUpdatedBy();
			//	Old
			Object oo = get_ValueOld("SalesRep_ID");
			int oldSalesRep_ID = 0;
			if (oo instanceof Integer)
				oldSalesRep_ID = ((Integer)oo).intValue();
			if (oldSalesRep_ID != 0)
			{
				//  RequestActionTransfer - Request {0} was transfered by {1} from {2} to {3}
				Object[] args = new Object[] {getDocumentNo(), 
					MUser.getNameOfUser(AD_User_ID),
					MUser.getNameOfUser(oldSalesRep_ID),
					MUser.getNameOfUser(getSalesRep_ID())
					};
				String msg = Msg.getMsg(getCtx(), "RequestActionTransfer", args);
				addToResult(msg);
				sendInfo.add("SalesRep_ID");
			}
		}
		checkChange(requestAction, "AD_Role_ID");
		//
		checkChange(requestAction, "Priority");
		if (checkChange(requestAction, "PriorityUser"))
			sendInfo.add("PriorityUser");
		if (checkChange(requestAction, "IsEscalated"))
			sendInfo.add("IsEscalated");
		//
		checkChange(requestAction, "ConfidentialType");
		checkChange(requestAction, "Summary");
		checkChange(requestAction, "IsSelfService");
		checkChange(requestAction, "C_BPartner_ID");
		checkChange(requestAction, "AD_User_ID");
		checkChange(requestAction, "C_Project_ID");
		checkChange(requestAction, "A_Asset_ID");
		checkChange(requestAction, "C_Order_ID");
		checkChange(requestAction, "C_Invoice_ID");
		checkChange(requestAction, "M_Product_ID");
		checkChange(requestAction, "C_Payment_ID");
		checkChange(requestAction, "M_InOut_ID");
		checkChange(requestAction, "M_RMA_ID");
		checkChange(requestAction, "C_Campaign_ID");
		checkChange(requestAction, "RequestAmt");
		checkChange(requestAction, "IsInvoiced");
		checkChange(requestAction, "C_Activity_ID");
		checkChange(requestAction, "DateNextAction");
		checkChange(requestAction, "M_ProductSpent_ID");
		checkChange(requestAction, "QtySpent");
		checkChange(requestAction, "QtyInvoiced");
		checkChange(requestAction, "StartDate");
		checkChange(requestAction, "CloseDate");
		checkChange(requestAction, "TaskStatus");
		checkChange(requestAction, "DateStartPlan");
		checkChange(requestAction, "DateCompletePlan");
		//
		if (m_changed)
			requestAction.saveEx();
		
		//	Current Info
		MRequestUpdate requestUpdate = new MRequestUpdate(this);
		// Link Request Action  with Request Update
		if (requestAction.getR_RequestAction_ID() > 0)
			requestUpdate.setR_RequestAction_ID(requestAction.getR_RequestAction_ID());

		if (requestUpdate.isNewInfo())
			requestUpdate.saveEx();
		else
			requestUpdate = null;
		//
		m_emailTo = new StringBuffer();
		if (requestUpdate != null || sendInfo.size() > 0)
		{
			// Note that calling the notifications from beforeSave is causing the
			// new interested are not notified if the RV_RequestUpdates view changes
			// this is, when changed the sales rep (solved in sendNotices)
			// or when changed the request category or group or contact (unsolved - the old ones are notified)
			if(checkChange(requestAction, "SalesRep_ID")) {
				sendNotices(sendInfo, MRNoticeTemplateEvent.EVENTTYPE_SalesRepAlertWhenTransferringARequest);
			} else if(checkChange(requestAction, "Summary")) {
				sendNotices(sendInfo, MRNoticeTemplateEvent.EVENTTYPE_EndUserLimitOverrideNotice);
			}
			sendNotices(sendInfo, MRNoticeTemplateEvent.EVENTTYPE_AutomaticTaskNewActivityNotice);
			//	Update
			setDateLastAction(getUpdated());
			setLastResult(getResult());
			setDueType();
			//	Reset
			setConfidentialTypeEntry (getConfidentialType());
			// setStartDate(null);  //red1 - bug [ 1743159 ] Requests - Start Date is not retained.
			setEndTime(null);
			setR_StandardResponse_ID(0);
			setR_MailText_ID(0);
			setResult(null);
			// globalqss - these fields must be cleared (waiting to open bug in sf)
		//	setM_ProductSpent_ID(0);
		//	setQtySpent(null);
		//	setQtyInvoiced(null);
		}
		return true;
	}	//	beforeSave

	/**
	 * 	Check for changes
	 *	@param requestAction request action
	 *	@param columnName column
	 *	@return true if changes
	 */
	private boolean checkChange (MRequestAction requestAction, String columnName)
	{
		if (is_ValueChanged(columnName))
		{
			Object value = get_ValueOld(columnName);
			if (value == null)
				requestAction.addNullColumn(columnName);
			else
				requestAction.set_ValueNoCheck(columnName, value);
			m_changed = true;
			return true;
		}
		return false;
	}	//	checkChange
	
	/**
	 * 	Set SalesRep_ID
	 *	@param SalesRep_ID id
	 */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID != 0)
			super.setSalesRep_ID (SalesRep_ID);
		else if (getSalesRep_ID() != 0)
			log.warning("Ignored - Tried to set SalesRep_ID to 0 from " + getSalesRep_ID());
	}	//	setSalesRep_ID
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		
		//	Create Update
		if (newRecord && getResult() != null)
		{
			MRequestUpdate update = new MRequestUpdate(this);
			update.saveEx();
		}
		//	Initial Mail
		if (newRecord) {
			sendNotices(new ArrayList<String>(), MRNoticeTemplateEvent.EVENTTYPE_EndUserNewRequestNotice);
		}
		//	ChangeRequest - created in Request Processor
		if (getM_ChangeRequest_ID() != 0
			&& is_ValueChanged(COLUMNNAME_R_Group_ID))	//	different ECN assignment?
		{
			int oldID = get_ValueOldAsInt(COLUMNNAME_R_Group_ID);
			if (getR_Group_ID() == 0)
			{
				setM_ChangeRequest_ID(0);	//	not effective as in afterSave
			}
			else
			{
				MGroup oldG = MGroup.get(getCtx(), oldID);
				MGroup newG = MGroup.get(getCtx(), getR_Group_ID());
				if (oldG.getPP_Product_BOM_ID() != newG.getPP_Product_BOM_ID()
					|| oldG.getM_ChangeNotice_ID() != newG.getM_ChangeNotice_ID())
				{
					MChangeRequest ecr = new MChangeRequest(getCtx(), getM_ChangeRequest_ID(), get_TrxName());
					if (!ecr.isProcessed()
						|| ecr.getM_FixChangeNotice_ID() == 0)
					{
						ecr.setPP_Product_BOM_ID(newG.getPP_Product_BOM_ID());
						ecr.setM_ChangeNotice_ID(newG.getM_ChangeNotice_ID());
						ecr.saveEx();
					}
				}
			}
		}
		
		if (m_emailTo.length() > 0)
			log.saveInfo ("RequestActionEMailOK", m_emailTo.toString());
		
		return success;
	}	//	afterSave
	
	/**
	 * 	Send Update EMail/Notices
	 * 	@param list list of changes
	 *  @param eventType Event Type
	 */
	public void sendNotices(ArrayList<String> list, String eventType) {
		//	Subject
		String subject = "";
		//	Message
		String message = new String();
		//	
		int updatedBy = Env.getAD_User_ID(getCtx());
		//		UpdatedBy: Joe
		MUser from = MUser.get(getCtx(), updatedBy);
		//	Event Type
		if(!Util.isEmpty(eventType)) {
			MMailText mailText = MRNoticeTemplate.getMailTemplate(getCtx(), MRNoticeTemplate.TEMPLATETYPE_Request, eventType);
			if(mailText != null) {
				mailText.setUser(from);
				if(getC_BPartner_ID() != 0) {
					mailText.setBPartner(getC_BPartner_ID());
				}
				//	Add Request
				mailText.setPO(this);
				subject = mailText.getMailHeader();
				//	Message
				message = mailText.getMailText(true);
				StringBuffer localMessage = new StringBuffer();
				for (int i = 0; i < list.size(); i++) {
					String columnName = (String)list.get(i);
					localMessage.append(Env.NL).append(Msg.getElement(getCtx(), columnName))
						.append(": ").append(get_DisplayValue(columnName, false))
						.append(" -> ").append(get_DisplayValue(columnName, true));
				}
				//	
				message += localMessage.toString();
			}
		}
		if(Util.isEmpty(subject)
				&& Util.isEmpty(message)) {
			StringBuffer localMessage = new StringBuffer();
			subject = Msg.translate(getCtx(), "R_Request_ID")
			+ " " + Msg.getMsg(getCtx(), "Updated") + ": " + getDocumentNo();
			if (from != null)
				localMessage.append(Msg.translate(getCtx(), "UpdatedBy")).append(": ")
					.append(from.getName());
			//		LastAction/Created: ...	
			if (getDateLastAction() != null)
				localMessage.append("\n").append(Msg.translate(getCtx(), "DateLastAction"))
					.append(": ").append(getDateLastAction());
			else
				localMessage.append("\n").append(Msg.translate(getCtx(), "Created"))
					.append(": ").append(getCreated());
			//	Changes
			for (int i = 0; i < list.size(); i++)
			{
				String columnName = (String)list.get(i);
				localMessage.append("\n").append(Msg.getElement(getCtx(), columnName))
					.append(": ").append(get_DisplayValue(columnName, false))
					.append(" -> ").append(get_DisplayValue(columnName, true));
			}
			//	NextAction
			if (getDateNextAction() != null)
				localMessage.append("\n").append(Msg.translate(getCtx(), "DateNextAction"))
					.append(": ").append(getDateNextAction());
			localMessage.append(SEPARATOR)
				.append(getSummary());
			if (getResult() != null)
				localMessage.append("\n----------\n").append(getResult());
			localMessage.append(getMailTrailer(null));
			message = localMessage.toString();
		}
		File pdf = createPDF();
		log.finer(message);
		
		//	Prepare sending Notice/Mail
		//	Reset from if external
		if (from.getEMailUser() == null || from.getEMailUserPW() == null)
			from = null;
		int success = 0;
		int failure = 0;
		int notices = 0;
		//
		ArrayList<Integer> recipients = new ArrayList<Integer>();
		final String sql = "SELECT u.AD_User_ID, u.NotificationType, u.EMail, u.Name, MAX(r.AD_Role_ID) "
			+ "FROM RV_RequestUpdates_Only ru"
			+ " INNER JOIN AD_User u ON (ru.AD_User_ID=u.AD_User_ID OR u.AD_User_ID=?)"
			+ " LEFT OUTER JOIN AD_User_Roles r ON (u.AD_User_ID=r.AD_User_ID) "
			+ "WHERE ru.R_Request_ID=? "
			+ "GROUP BY u.AD_User_ID, u.NotificationType, u.EMail, u.Name";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement (sql, get_TrxName());
			pstmt.setInt (1, getSalesRep_ID());
			pstmt.setInt (2, getR_Request_ID());
			rs = pstmt.executeQuery ();
			while (rs.next()) {
				int userId = rs.getInt(1);
				//	Role
				int roleId = rs.getInt(5);
				if (rs.wasNull())
					roleId = -1;
				
				//	Don't send mail to oneself
				boolean isIncludeOwnChanges = false;
				if (from !=null)
					isIncludeOwnChanges = from.isIncludeOwnChanges();
				if (userId == updatedBy
						&& !isIncludeOwnChanges)
					continue;
				//	No confidential to externals
				if (roleId == -1 
					&& (getConfidentialTypeEntry().equals(CONFIDENTIALTYPE_Internal)
						|| getConfidentialTypeEntry().equals(CONFIDENTIALTYPE_PrivateInformation)))
					continue;
				//	Check duplicate receivers
				Integer ii = Integer.valueOf(userId);
				if (recipients.contains(ii))
					continue;
				recipients.add(ii);
			}
			//	Send notifications
			//	Get instance for notifier
			DefaultNotifier notifier = (DefaultNotifier) QueueLoader.getInstance().getQueueManager(DefaultNotifier.QUEUETYPE_DefaultNotifier)
					.withContext(getCtx())
					.withTransactionName(get_TrxName());
			//	Send notification to queue
			notifier
				.clearMessage()
				.withApplicationType(DefaultNotifier.DefaultNotificationType_UserDefined)
				.withUserId(updatedBy)
				.withText(message)
				.addAttachment(pdf)
				.withDescription(subject)
				.withEntity(this)
			;
			//	Add recipients
			recipients.forEach(recipientId -> notifier.addRecipient(recipientId));
			//	Add to queue
			notifier.addToQueue();
			success = recipients.size();
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.info("EMail Success=" + success + ", Failure=" + failure
			+ " - Notices=" + notices);
	}	//	sendNotice
	
	/**************************************************************************
	 * 	Get MailID
	 * 	@param serverAddress server address
	 *	@return Mail Trailer
	 */
	public String getMailTrailer(String serverAddress)
	{
		StringBuffer sb = new StringBuffer("\n").append(SEPARATOR)
			.append(Msg.translate(getCtx(), "R_Request_ID"))
			.append(": ").append(getDocumentNo())	
			
			.append("  ").append(getMailTag())
			.append("\nSent by AdempiereMail");
		if (serverAddress != null)
			sb.append(" from ").append(serverAddress);
		return sb.toString();
	}	//	getMailTrailer

	/**
	 * 	Get Mail Tag
	 *	@return [Req@{id}@]
	 */
	public String getMailTag()
	{
		return TAG_START + get_ID() + TAG_END;
	}	//	getMailTag
	
	/**
	 * 	(Soft) Close request.
	 * 	Must be called after webUpdate
	 */
	public void doClose()
	{
		MStatus status = MStatus.get(getCtx(), getR_Status_ID());
		if (!status.isClosed())
		{
			MStatus[] closed = MStatus.getClosed(getCtx());
			MStatus newStatus = null;
			for (int i = 0; i < closed.length; i++)
			{
				if (!closed[i].isFinalClose())
				{
					newStatus = closed[i];
					break;
				}
			}
			if (newStatus == null && closed.length > 0)
				newStatus = closed[0];
			if (newStatus != null)
				setR_Status_ID(newStatus.getR_Status_ID());
		}
	}	//	doClose
	
	/**
	 * 	Escalate request
	 * 	@param user true if user escalated - otherwise system
	 */
	public void doEscalate(boolean user)
	{
		if (user)
		{
			String Importance = getPriorityUser();
			if (PRIORITYUSER_Urgent.equals(Importance))
				;	//	high as it goes
			else if (PRIORITYUSER_High.equals(Importance))
				setPriorityUser(PRIORITYUSER_Urgent);
			else if (PRIORITYUSER_Medium.equals(Importance))
				setPriorityUser(PRIORITYUSER_High);
			else if (PRIORITYUSER_Low.equals(Importance))
				setPriorityUser(PRIORITYUSER_Medium);
			else if (PRIORITYUSER_Minor.equals(Importance))
				setPriorityUser(PRIORITYUSER_Low);
		}
		else
		{
			String Importance = getPriority();
			if (PRIORITY_Urgent.equals(Importance))
				;	//	high as it goes
			else if (PRIORITY_High.equals(Importance))
				setPriority(PRIORITY_Urgent);
			else if (PRIORITY_Medium.equals(Importance))
				setPriority(PRIORITY_High);
			else if (PRIORITY_Low.equals(Importance))
				setPriority(PRIORITY_Medium);
			else if (PRIORITY_Minor.equals(Importance))
				setPriority(PRIORITY_Low);
		}
	}	//	doEscalate
	
}	//	MRequest
