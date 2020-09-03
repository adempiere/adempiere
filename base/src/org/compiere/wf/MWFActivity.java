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
 *                                                                            *
 * @author: Jorg Janke                                                        *
 * @author: Kitti U. Fix[3409739]DocValueWorkflow_cannot_set_var_ID_Column    *
 *                                                                            *               
 *****************************************************************************/
package org.compiere.wf;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_AD_Process;
import org.compiere.model.MAttachment;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MColumn;
import org.compiere.model.MConversionRate;
import org.compiere.model.MMailText;
import org.compiere.model.MNote;
import org.compiere.model.MOrg;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MRefList;
import org.compiere.model.MRole;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_WF_Activity;
import org.compiere.print.ReportEngine;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.StateEngine;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trace;
import org.compiere.util.Trx;
import org.compiere.util.Util;

/**
 *	Workflow Activity Model.
 *	Controlled by WF Process: 
 *		set Node - startWork 
 * 
 * @author Jorg Janke
 * @author Kitti U. Fix[3409739]DocValueWorkflow_cannot_set_var_ID_Column
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1875>
 * 		@see FR [ 1875 ] Add Workflow activity translation</a>
 * @author Victor Pérez, E Evolution Consulting,  wwww.e-evolution.com
 * 				<li>[Bug Report] The workflow engine is not correctly handling transactions when processing documents #3170
 * 				<a href="https://github.com/adempiere/adempiere/issues/3170">
 */
public class MWFActivity extends X_AD_WF_Activity implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3282235931100223816L;

	/**
	 * 	Get Activities for table/record 
	 *	@param ctx context
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@param activeOnly if true only not processed records are returned
	 *	@return activity
	 */
	public static MWFActivity[] get (Properties ctx, int AD_Table_ID, int Record_ID, boolean activeOnly)
	{
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer("AD_Table_ID=? AND Record_ID=?");
		params.add(AD_Table_ID);
		params.add(Record_ID);
		if (activeOnly)
		{
			whereClause.append(" AND Processed<>?");
			params.add(true);
		}
		List<MWFActivity> list = new Query(ctx, Table_Name, whereClause.toString(), null)
					.setParameters(params)
					.setOrderBy(COLUMNNAME_AD_WF_Activity_ID)
					.list();
		
		MWFActivity[] retValue = new MWFActivity[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	get

	/**
	 * 	Get Active Info
	 * 	@param ctx context
	 *	@param AD_Table_ID table
	 *	@param Record_ID record
	 *	@return activity summary
	 */
	public static String getActiveInfo (Properties ctx, int AD_Table_ID, int Record_ID)
	{
		MWFActivity[] acts = get (ctx, AD_Table_ID, Record_ID, true);
		if (acts == null || acts.length == 0)
			return null;
		//
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < acts.length; i++)
		{
			if (i > 0)
				sb.append(Env.NL);
			MWFActivity activity = acts[i];
			sb.append(activity.toStringX());
		}
		return sb.toString();
	}	//	getActivityInfo

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param AD_WF_Activity_ID id
	 *	@param trxName transaction
	 */
	public MWFActivity (Properties ctx, int AD_WF_Activity_ID, String trxName)
	{
		super (ctx, AD_WF_Activity_ID, trxName);
		if (AD_WF_Activity_ID == 0)
			throw new IllegalArgumentException ("@WFA.CannotCreate@");
		m_state = new StateEngine (getWFState());
	}	//	MWFActivity

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MWFActivity (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		m_state = new StateEngine (getWFState());
	}	//	MWFActivity

	/**
	 * 	Parent Contructor
	 *	@param process process
	 *	@param AD_WF_Node_ID start node
	 */
	public MWFActivity (MWFProcess process, int AD_WF_Node_ID)
	{
		super (process.getCtx(), 0, process.get_TrxName());
		setAD_WF_Process_ID (process.getAD_WF_Process_ID());
		setPriority(process.getPriority());
		//	Document Link
		setAD_Table_ID(process.getAD_Table_ID());
		setRecord_ID(process.getRecord_ID());
		//modified by Rob Klein
		setAD_Client_ID(process.getAD_Client_ID());
		setAD_Org_ID(process.getAD_Org_ID());
		//	Status
		super.setWFState(WFSTATE_NotStarted);
		m_state = new StateEngine (getWFState());
		setProcessed (false);
		//	Set Workflow Node
		setAD_Workflow_ID (process.getAD_Workflow_ID());
		setAD_WF_Node_ID (AD_WF_Node_ID);
		//	Node Priority & End Duration
		MWFNode node = MWFNode.get(getCtx(), AD_WF_Node_ID);
		int priority = node.getPriority();
		if (priority != 0 && priority != getPriority())
			setPriority (priority);
		long limitMS = node.getLimitMS();
		if (limitMS != 0)
			setEndWaitTime(new Timestamp(limitMS + System.currentTimeMillis()));
		//	Responsible
		setResponsible(process);
		save();
		//
		m_audit = new MWFEventAudit(this);
		m_audit.saveEx();
		//
		m_process = process;
	}	//	MWFActivity

	/**
	 * 	Parent Contructor
	 *	@param process process
	 *	@param AD_WF_Node_ID start node
	 *	@param lastPO PO from the previously executed node
	 */
	public MWFActivity(MWFProcess process, int next_ID, PO lastPO) {
		this(process, next_ID);
		if (lastPO != null) {
			// Compare if the last PO is the same type and record needed here, if yes, use it
			if (lastPO.get_Table_ID() == getAD_Table_ID() && lastPO.get_ID() == getRecord_ID()) {
				m_po = lastPO;
			}
		}
	}

	/**	State Machine				*/
	private StateEngine			m_state = null;
	/**	Workflow Node				*/
	private MWFNode				m_node = null;
	/**	Audit						*/
	private MWFEventAudit		m_audit = null;
	/** Persistent Object			*/
	private PO					m_po = null;
	/** Document Status				*/
	private String				m_docStatus = null;
	/**	New Value to save in audit	*/
	private String				m_newValue = null;
	/** Process						*/
	private MWFProcess 			m_process = null;
	/** List of email recipients	*/
	private ArrayList<String> 	m_emails = new ArrayList<String>();

	/**************************************************************************
	 * 	Get State
	 *	@return state
	 */
	public StateEngine getState()
	{
		return m_state;
	}	//	getState

	/**
	 * Set Activity State.
	 * It also validates the new state and if is valid,
	 * then create event audit and call {@link MWFProcess#checkActivities(String, PO)} 
	 * @param wFState
	 */
	@Override
	public void setWFState (String wFState)
	{
		if (m_state == null)
			m_state = new StateEngine (getWFState());
		if (m_state.isClosed())
			return;
		if (getWFState().equals(wFState))
			return;
		//
		if (m_state.isValidNewState(wFState))
		{
			String oldState = getWFState();
			log.fine(oldState + "->"+ wFState + ", Msg=" + getTextMsg()); 
			super.setWFState (wFState);
			m_state = new StateEngine (getWFState());
			save();			//	closed in MWFProcess.checkActivities()
			updateEventAudit();			
			
			//	Inform Process
			if (m_process == null)
				m_process = new MWFProcess (getCtx(), getAD_WF_Process_ID(), get_TrxName());
			m_process.checkActivities(m_po);
		}
		else
		{
			String msg = "@WFA.StateIgnored@ - @New@ = " 
				+ MRefList.getListName(getCtx(), WFSTATE_AD_Reference_ID, wFState) + ", @Current@ = " + MRefList.getListName(getCtx(), WFSTATE_AD_Reference_ID, getWFState()); 
			log.log(Level.SEVERE, msg);
			Trace.printStack();
			setTextMsg(msg);
			save();
			// TODO: teo_sarca: throw exception ? please analyze the call hierarchy first 
		}
	}	//	setWFState
	
	/**
	 * 	Is Activity closed
	 * 	@return true if closed
	 */
	public boolean isClosed()
	{
		return m_state.isClosed();
	}	//	isClosed
	
	
	/**************************************************************************
	 * 	Update Event Audit
	 */
	private void updateEventAudit()
	{
	//	log.fine("");
		getEventAudit();
		m_audit.setTextMsg(getTextMsg());
		m_audit.setWFState(getWFState());
		if (m_newValue != null)
			m_audit.setNewValue(m_newValue);
		if (m_state.isClosed())
		{
			m_audit.setEventType(MWFEventAudit.EVENTTYPE_ProcessCompleted);
			long ms = System.currentTimeMillis() - m_audit.getCreated().getTime();
			m_audit.setElapsedTimeMS(new BigDecimal(ms));
		}
		else
			m_audit.setEventType(MWFEventAudit.EVENTTYPE_StateChanged);
		m_audit.saveEx();
	}	//	updateEventAudit

	/**
	 * 	Get/Create Event Audit
	 * 	@return event
	 */
	public MWFEventAudit getEventAudit()
	{
		if (m_audit != null)
			return m_audit;
		MWFEventAudit[] events = MWFEventAudit.get(getCtx(), getAD_WF_Process_ID(), getAD_WF_Node_ID(), get_TrxName());
		if (events == null || events.length == 0)
			m_audit = new MWFEventAudit(this);
		else
			m_audit = events[events.length-1];		//	last event
		return m_audit;
	}	//	getEventAudit
	
	
	/**************************************************************************
	 * 	Get Persistent Object in Transaction
	 * 	@param trx transaction
	 *	@return po
	 */
	public PO getPO (Trx trx)
	{
		if (m_po != null) {
			if (trx != null)
				m_po.set_TrxName(trx.getTrxName());
			return m_po;
		}
		
		MTable table = MTable.get (getCtx(), getAD_Table_ID());
		if (trx != null)
			m_po = table.getPO(getRecord_ID(), trx.getTrxName());
		else
			m_po = table.getPO(getRecord_ID(), null);
		return m_po;
	}	//	getPO
	
	/**
	 * 	Get Persistent Object
	 *	@return po
	 */
	public PO getPO()
	{
		return getPO(get_TrxName() != null ? Trx.get(get_TrxName(), false) : null);
	}	//	getPO
	
	/**
	 * 	Get PO AD_Client_ID
	 *	@return client of PO
	 */
	public int getPO_AD_Client_ID()
	{
		if (m_po == null)
			getPO(get_TrxName() != null ? Trx.get(get_TrxName(), false) : null);
		if (m_po != null)
			return m_po.getAD_Client_ID();
		return 0;
	}	//	getPO_AD_Client_ID
	
	/**
	 * 	Get Attribute Value (based on Node) of PO
	 *	@return Attribute Value or null
	 */
	public Object getAttributeValue()
	{
		MWFNode node = getNode();
		if (node == null)
			return null;
		int AD_Column_ID = node.getAD_Column_ID();
		if (AD_Column_ID == 0)
			return null;
		PO po = getPO();
		if (po.get_ID() == 0)
			return null;
		return po.get_ValueOfColumn(AD_Column_ID);
	}	//	getAttributeValue
	
	/**
	 * 	Is SO Trx
	 *	@return SO Trx or of not found true
	 */
	public boolean isSOTrx()
	{
		PO po = getPO();
		if (po.get_ID() == 0)
			return true;
		//	Is there a Column?
		int index = po.get_ColumnIndex("IsSOTrx");
		if (index < 0)
		{
			if (po.get_TableName().startsWith("M_"))
				return false;
			return true;
		}
		//	we have a column
		try
		{
			Boolean IsSOTrx = (Boolean)po.get_Value(index);
			return IsSOTrx.booleanValue();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "", e);
		}
		return true;
	}	//	isSOTrx
	
	
	/**************************************************************************
	 * 	Set AD_WF_Node_ID.
	 * 	(Re)Set to Not Started
	 *	@param AD_WF_Node_ID now node
	 */
	@Override
	public void setAD_WF_Node_ID (int AD_WF_Node_ID)
	{
		if (AD_WF_Node_ID == 0)
			throw new IllegalArgumentException("@WFA.NodeNoDefined@");
		super.setAD_WF_Node_ID (AD_WF_Node_ID);
		//
		if (!WFSTATE_NotStarted.equals(getWFState()))
		{
			super.setWFState(WFSTATE_NotStarted);
			m_state = new StateEngine (getWFState());
		}
		if (isProcessed())
			setProcessed (false);
	}	//	setAD_WF_Node_ID
	
	/**
	 * 	Get WF Node
	 *	@return node
	 */
	public MWFNode getNode()
	{
		if (m_node == null)
			m_node = MWFNode.get (getCtx(), getAD_WF_Node_ID());
		return m_node;
	}	//	getNode
	
	/**
	 * 	Get WF Node Name
	 *	@return translated node name
	 */
	public String getNodeName()
	{
		return getNode().getName(true);
	}	//	getNodeName

	/**
	 * 	Get Node Description
	 *	@return translated node description
	 */
	public String getNodeDescription()
	{
		return getNode().getDescription(true);
	}	//	getNodeDescription
	
	/**
	 * 	Get Node Help
	 *	@return translated node help
	 */
	public String getNodeHelp()
	{
		return getNode().getHelp(true);
	}	//	getNodeHelp
	
	
	/**
	 * 	Is this an user Approval step?
	 *	@return true if User Approval
	 */
	public boolean isUserApproval()
	{
		return getNode().isUserApproval();
	}	//	isNodeApproval

	/**
	 * 	Is this a Manual user step?
	 *	@return true if Window/Form/..
	 */
	public boolean isUserManual()
	{
		return getNode().isUserManual();
	}	//	isUserManual

	/**
	 * 	Is this a user choice step?
	 *	@return true if User Choice
	 */
	public boolean isUserChoice()
	{
		return getNode().isUserChoice();
	}	//	isUserChoice

	
	/**
	 * 	Set Text Msg (add to existing)
	 *	@param textMsg
	 */
	public void setTextMsg (String textMsg) {
		if (Util.isEmpty(textMsg)) {
			return;
		}
		String oldText = getTextMsg();
		textMsg = Msg.parseTranslation(getCtx(), textMsg);
		if (Util.isEmpty(oldText)) {
			super.setTextMsg (textMsg);
		} else if(textMsg != null 
				&& textMsg.length() > 0) {
			super.setTextMsg(oldText + Env.NL + " - " + textMsg);
		}
	}	//	setTextMsg	
	
	/**
	 * 	Add to Text Msg
	 *	@param obj some object
	 */
	public void addTextMsg (Object obj)
	{
		if (obj == null)
			return;
		//
		StringBuffer TextMsg = new StringBuffer ();
		if (obj instanceof Exception)
		{
			Exception ex = (Exception)obj;
			if (ex.getMessage() != null && ex.getMessage().trim().length() > 0)
			{
				TextMsg.append(ex.toString());
			}
			else if (ex instanceof NullPointerException)
			{
				TextMsg.append(ex.getClass().getName());
			}
			while (ex != null)
			{
				StackTraceElement[] st = ex.getStackTrace();
				for (int i = 0; i < st.length; i++)
				{
					StackTraceElement ste = st[i];
					if (i == 0 || ste.getClassName().startsWith("org.compiere") || ste.getClassName().startsWith("org.adempiere"))
						TextMsg.append(" (").append(i).append("): ")
							.append(ste.toString())
							.append(Env.NL);
				}
				if (ex.getCause() instanceof Exception)
					ex = (Exception)ex.getCause();
				else
					ex = null;
			}
		}
		else
		{
			TextMsg.append(obj.toString());
		}
		//
		String oldText = getTextMsg();
		if (oldText == null || oldText.length() == 0)
			super.setTextMsg(Util.trimSize(TextMsg.toString(),1000));
		else if (TextMsg != null && TextMsg.length() > 0)
			super.setTextMsg(oldText + Env.NL + " - " + TextMsg.toString());
	}	//	setTextMsg	
	
	/**
	 * 	Get WF State text
	 *	@return state text
	 */
	public String getWFStateText ()
	{
		return MRefList.getListName(getCtx(), WFSTATE_AD_Reference_ID, getWFState());
	}	//	getWFStateText
	
	/**
	 * 	Set Responsible and User from Process / Node
	 *	@param process process
	 */
	private void setResponsible (MWFProcess process)
	{
		//	Responsible
		int AD_WF_Responsible_ID = getNode().getAD_WF_Responsible_ID();
		if (AD_WF_Responsible_ID == 0)	//	not defined on Node Level
			AD_WF_Responsible_ID = process.getAD_WF_Responsible_ID();
		setAD_WF_Responsible_ID (AD_WF_Responsible_ID);
		MWFResponsible resp = getResponsible();
		
		//	User - Directly responsible
		int AD_User_ID = resp.getAD_User_ID();
		//	Invoker - get Sales Rep or last updater of document
		if (AD_User_ID == 0 && resp.isInvoker())
			AD_User_ID = process.getAD_User_ID();
		//
		setAD_User_ID(AD_User_ID);
	}	//	setResponsible
	
	/**
	 * 	Get Responsible
	 *	@return responsible
	 */
	public MWFResponsible getResponsible()
	{
		MWFResponsible resp = MWFResponsible.get(getCtx(), getAD_WF_Responsible_ID());
		return resp;
	}	//	isInvoker

	/**
	 * 	Is Invoker (no user & no role)
	 *	@return true if invoker
	 */
	public boolean isInvoker()
	{
		return getResponsible().isInvoker();
	}	//	isInvoker

	/**
	 * 	Get Approval User.
	 * 	If the returned user is the same, the document is approved.
	 *	@param AD_User_ID starting User
	 *	@param C_Currency_ID currency
	 *	@param amount amount
	 *	@param AD_Org_ID document organization
	 *	@param ownDocument the document is owned by AD_User_ID
	 *	@return AD_User_ID - if -1 no Approver
	 */
	public int getApprovalUser (int AD_User_ID, 
			int C_Currency_ID, BigDecimal amount, 
			int AD_Org_ID, boolean ownDocument)
	{
		//	Nothing to approve
		if (amount == null 
			|| amount.signum() == 0)
			return AD_User_ID;
		
		//	Starting user
		MUser user = MUser.get(getCtx(), AD_User_ID);
		log.info("For User=" + user 
			+ ", Amt=" + amount 
			+ ", Own=" + ownDocument);

		MUser oldUser = null;
		while (user != null)
		{
			if (user.equals(oldUser))
			{
				log.info("Loop - " + user.getName());
				return -1;
			}
			oldUser = user;
			log.fine("User=" + user.getName());
			//	Get Roles of User
			MRole[] roles = user.getRoles(AD_Org_ID);
			for (int i = 0; i < roles.length; i++)
			{
				MRole role = roles[i];
				if (ownDocument && !role.isCanApproveOwnDoc())
					continue;	//	find a role with allows them to approve own
				BigDecimal roleAmt = role.getAmtApproval();
				if (roleAmt == null || roleAmt.signum() == 0)
					continue;
				if (C_Currency_ID != role.getC_Currency_ID()	
					&& role.getC_Currency_ID() != 0)			//	No currency = amt only
				{
					roleAmt =  MConversionRate.convert(getCtx(),//	today & default rate 
						roleAmt, role.getC_Currency_ID(), 
						C_Currency_ID, getAD_Client_ID(), AD_Org_ID);
					if (roleAmt == null || roleAmt.signum() == 0)
						continue;
				}
				boolean approved = amount.compareTo(roleAmt) <= 0;
				log.fine("Approved=" + approved 
					+ " - User=" + user.getName() + ", Role=" + role.getName()
					+ ", ApprovalAmt=" + roleAmt);
				if (approved)
					return user.getAD_User_ID();
			}
			
			//	**** Find next User 
			//	Get Supervisor
			if (user.getSupervisor_ID() != 0)
			{
				user = MUser.get(getCtx(), user.getSupervisor_ID());
				log.fine("Supervisor: " + user.getName()); 
			}
			else
			{
				log.fine("No Supervisor"); 
				MOrg org = MOrg.get (getCtx(), AD_Org_ID);
				MOrgInfo orgInfo = org.getInfo();
				//	Get Org Supervisor
				if (orgInfo.getSupervisor_ID() != 0)
				{
					user = MUser.get(getCtx(), orgInfo.getSupervisor_ID());
					log.fine("Org=" + org.getName() + ",Supervisor: " + user.getName()); 
				}
				else
				{
					log.fine("No Org Supervisor"); 
					//	Get Parent Org Supervisor
					if (orgInfo.getParent_Org_ID() != 0)
					{
						org = MOrg.get (getCtx(), orgInfo.getParent_Org_ID());
						orgInfo = org.getInfo();
						if (orgInfo.getSupervisor_ID() != 0)
						{
							user = MUser.get(getCtx(), orgInfo.getSupervisor_ID());
							log.fine("Parent Org Supervisor: " + user.getName()); 
						}
					}
				}
			}	//	No Supervisor
			//ownDocument should always be false for the next user
			ownDocument = false;
		}	//	while there is a user to approve
		
		log.fine("No user found"); 
		return -1;
	}	//	getApproval

	/**************************************************************************
	 * 	Execute Work.
	 * 	Called from MWFProcess.startNext
	 * 	Feedback to Process via setWFState -> checkActivities
	 */
	public void run()
	{
		log.info ("Node=" + getNode());
		m_newValue = null;
		Trx transaction = m_process.getWorkflowProcessTransaction();
		// Declare save point
		Savepoint savepoint = null;
		try
		{
			savepoint = transaction.setSavepoint(null);
			if (!m_state.isValidAction(StateEngine.ACTION_Start))
			{
				setTextMsg("@WFA.State@ = " + MRefList.getListName(getCtx(), WFSTATE_AD_Reference_ID, getWFState()) + " - @WFCannotStart@");
				addTextMsg(new Exception(""));
				setWFState(StateEngine.STATE_Terminated);
				return;
			}
			//
			setWFState(StateEngine.STATE_Running);
			if (getNode().get_ID() == 0)
			{
				setTextMsg("@AD_WF_Node_ID@ @NotFound@ - @AD_WF_Node_ID@: " + getAD_WF_Node_ID());
				setWFState(StateEngine.STATE_Aborted);
				return;
			}
			//	Do Work
			/****	Trx Start	****/
			boolean done = performWork(transaction);
			transaction.releaseSavepoint(savepoint);
			/****	Trx End		****/
			setWFState (done ? StateEngine.STATE_Completed : StateEngine.STATE_Suspended);

		}
		catch (Exception e)
		{
			log.log(Level.WARNING, "" + getNode(), e);
			/****	Trx Rollback	****/
			try {
				transaction.rollback(savepoint);
			} catch (SQLException sqlException) {
				throw new AdempiereException(sqlException.getMessage());
			}
			if (e.getCause() != null)
				log.log(Level.WARNING, "Cause", e.getCause());

			String processMsg = e.getLocalizedMessage();
			if (processMsg == null || processMsg.length() == 0)
				processMsg = e.getMessage();
			setTextMsg(processMsg);
			addTextMsg(e);
			setWFState (StateEngine.STATE_Terminated);	//	unlocks
			//	Set Document Status
			if (m_po != null && m_po instanceof DocAction && m_docStatus != null)
			{
				m_po.load(transaction.getTrxName());
				DocAction doc = (DocAction)m_po;
				doc.setDocStatus(m_docStatus);
				m_po.saveEx();
			}
		}
	}	//	run

	/**
	 * 	Perform Work.
	 * 	Set Text Msg.
	 * 	@param trx transaction
	 *	@return true if completed, false otherwise
	 *	@throws Exception if error
	 */
	private boolean performWork (Trx trx) throws Exception
	{
		log.info (m_node + " [" + trx.getTrxName() + "]");
		m_docStatus = null;
		if (m_node.getPriority() != 0)		//	overwrite priority if defined
			setPriority(m_node.getPriority());
		String action = m_node.getAction();
		
		/******	Sleep (Start/End)			******/
		if (MWFNode.ACTION_WaitSleep.equals(action))
		{
			log.fine("Sleep:WaitTime=" + m_node.getWaitTime());
			if (m_node.getWaitingTime() == 0)
				return true;	//	done
			Calendar cal = Calendar.getInstance();
			cal.add(m_node.getDurationCalendarField(), m_node.getWaitTime());
			setEndWaitTime(new Timestamp(cal.getTimeInMillis()));
			return false;		//	not done
		}
		
		/******	Document Action				******/
		else if (MWFNode.ACTION_DocumentAction.equals(action))
		{
			log.fine("DocumentAction=" + m_node.getDocAction());
			getPO(trx);
			MTable table = MTable.get(getCtx(), getAD_Table_ID());
			if (m_po == null) {
				throw new Exception("@WFA.PO@ @NotFound@ - @AD_Table_ID@ = " 
						+ (table != null? table.getTableName(): getAD_Table_ID()) + ", @Record_ID@ = " + getRecord_ID());
			}
			boolean success = false;
			String processMsg = null;
			DocAction doc = null;
			if (m_po instanceof DocAction)
			{
				doc = (DocAction)m_po;
				//
				try {
					success = doc.processIt (m_node.getDocAction());	//	** Do the work
					setTextMsg(doc.getSummary());
					processMsg = doc.getProcessMsg();
					// Bug 1904717 - Invoice reversing has incorrect doc status
					// Just prepare and complete return a doc status to take into account
					// the rest of methods return boolean, so doc status must not be taken into account when not successful
					if (   DocAction.ACTION_Prepare.equals(m_node.getDocAction())
						|| DocAction.ACTION_Complete.equals(m_node.getDocAction()) 
						|| success)
						m_docStatus = doc.getDocStatus();
				}
				catch (Exception e) {
					if (m_process != null)
						m_process.setProcessMsg(e.getLocalizedMessage());
					throw e;
				}
				if (m_process != null)
					m_process.setProcessMsg(processMsg);
			} else {
				throw new IllegalStateException("@WFA.PO@ @WFA.NotHaveDocAction@ - "
						+ m_po.getClass().getName()
						+ " - @AD_Table_ID@ = " + (table != null? table.getTableName(): getAD_Table_ID()) + ", @Record_ID@ = " + getRecord_ID());
			}
			//
			m_po.saveEx();
			if (!success) {
				if (processMsg == null || processMsg.length() == 0) {
					processMsg = "@WFA.PerformWork@ @Error@ - " + m_node.toStringX();
					if (doc != null)	//	problem: status will be rolled back
						processMsg += " - @DocStatus@ " + MRefList.getListName(getCtx(), DocAction.DOCSTATUS_AD_REFERENCE_ID, doc.getDocStatus());
				}
				throw new Exception(processMsg);
			}
			return success;
		}	//	DocumentAction
		
		/******	Report						******/
		else if (MWFNode.ACTION_AppsReport.equals(action))
		{
			log.fine("Report:AD_Process_ID=" + m_node.getAD_Process_ID());
			//	Process
			MProcess process = MProcess.get(getCtx(), m_node.getAD_Process_ID());
			process.set_TrxName(trx != null ? trx.getTrxName() : null);
			if (!process.isReport() || process.getAD_ReportView_ID() == 0) {
				log.warning("Not a Report AD_Process_ID=" + m_node.getAD_Process_ID());
				throw new IllegalStateException("@WFA.NotReport@ @AD_Process_ID@: " + process.get_Translation(I_AD_Process.COLUMNNAME_Name));
			}
			//
			ProcessInfo pi = new ProcessInfo (m_node.getName(true), m_node.getAD_Process_ID(),
				getAD_Table_ID(), getRecord_ID());
			pi.setAD_User_ID(getAD_User_ID());
			pi.setAD_Client_ID(getAD_Client_ID());
			MPInstance pInstance = new MPInstance(process, getRecord_ID());
			pInstance.set_TrxName(trx != null ? trx.getTrxName() : null);
			fillParameter(pInstance, trx);
			pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
			//	Report
			ReportEngine re = ReportEngine.get(getCtx(), pi);
			if (re == null) {
				log.warning("Cannot create Report AD_Process_ID=" + m_node.getAD_Process_ID());
				throw new IllegalStateException("@WFA.CannotCreateReport@ @AD_Process_ID@: " + process.get_Translation(I_AD_Process.COLUMNNAME_Name));
			}
			File report = re.getPDF();
			//	Notice
			MNote note = new MNote(getCtx(), "WorkflowResult", getAD_User_ID(), trx.getTrxName());
			note.setTextMsg(m_node.getName(true));
			note.setDescription(m_node.getDescription(true));
			note.setRecord(getAD_Table_ID(), getRecord_ID());
			note.saveEx();
			//	Attachment
			MAttachment attachment = new MAttachment (getCtx(), MNote.Table_ID, note.getAD_Note_ID(), trx.getTrxName());
			attachment.addEntry(report);
			attachment.setTextMsg(m_node.getName(true));
			attachment.saveEx();
			return true;
		}
		
		/******	Process						******/
		else if (MWFNode.ACTION_AppsProcess.equals(action))
		{
			log.fine("Process:AD_Process_ID=" + m_node.getAD_Process_ID());
			//	Process
			MProcess process = MProcess.get(getCtx(), m_node.getAD_Process_ID());
			MPInstance pInstance = new MPInstance(process, getRecord_ID());
			fillParameter(pInstance, trx);
			//
			ProcessInfo pi = new ProcessInfo (m_node.getName(true), m_node.getAD_Process_ID(),
				getAD_Table_ID(), getRecord_ID());
			pi.setAD_User_ID(getAD_User_ID());
			pi.setAD_Client_ID(getAD_Client_ID());
			pi.setAD_PInstance_ID(pInstance.getAD_PInstance_ID());
			return process.processItWithoutTrxClose(pi, trx);
		}
		/******	EMail						******/
		else if (MWFNode.ACTION_EMail.equals(action))
		{
			log.fine ("EMail:EMailRecipient=" + m_node.getEMailRecipient());
			getPO(trx);
			MTable table = MTable.get(getCtx(), getAD_Table_ID());
			if (m_po == null) {
				throw new Exception("@WFA.PO@ @NotFound@ - @AD_Table_ID@ = " 
						+ (table != null? table.getTableName(): getAD_Table_ID()) + ", @Record_ID@ = " + getRecord_ID());
			}
			m_emails = new ArrayList<String>();
			//	Send EMail
			sendEMail();
			return true;	//	done
		}	//	EMail
		
		/******	Set Variable				******/
		else if (MWFNode.ACTION_SetVariable.equals(action))
		{
			String value = m_node.getAttributeValue();
			log.fine("SetVariable:AD_Column_ID=" + m_node.getAD_Column_ID()
				+ " to " +  value);
			MColumn column = m_node.getColumn();
			int dt = column.getAD_Reference_ID();
			return setVariable (value, dt, null, trx);
		}	//	SetVariable
		
		/******	TODO Start WF Instance		******/
		else if (MWFNode.ACTION_SubWorkflow.equals(action))
		{
			log.warning ("Workflow:AD_Workflow_ID=" + m_node.getAD_Workflow_ID());
			log.warning("Start WF Instance is not implemented yet");
		}
		
		/******	User Choice					******/
		else if (MWFNode.ACTION_UserChoice.equals(action))
		{
			log.fine("UserChoice:AD_Column_ID=" + m_node.getAD_Column_ID());
			//	Approval
			if (m_node.isUserApproval() 
				&& getPO(trx) instanceof DocAction)
			{
				DocAction doc = (DocAction)m_po;
				boolean autoApproval = false;
				//	Approval Hierarchy
				if (isInvoker())
				{
					//	Set Approver
					int startAD_User_ID = Env.getAD_User_ID(getCtx());
					if (startAD_User_ID == 0)
						startAD_User_ID = doc.getDoc_User_ID();
					int nextAD_User_ID = getApprovalUser(startAD_User_ID, 
						doc.getC_Currency_ID(), doc.getApprovalAmt(),
						doc.getAD_Org_ID(), 
						startAD_User_ID == doc.getDoc_User_ID());	//	own doc
					//	same user = approved
					autoApproval = startAD_User_ID == nextAD_User_ID;
					if (!autoApproval)
						setAD_User_ID(nextAD_User_ID);
				}
				else	//	fixed Approver
				{
					MWFResponsible resp = getResponsible();
					// MZ Goodwill
					// [ 1742751 ] Workflow: User Choice is not working
					if (resp.isHuman())
					{
						autoApproval = resp.getAD_User_ID() == Env.getAD_User_ID(getCtx());
						if (!autoApproval && resp.getAD_User_ID() != 0)
							setAD_User_ID(resp.getAD_User_ID());
					}
					else if(resp.isRole())
					{
						MUserRoles[] urs = MUserRoles.getOfRole(getCtx(), resp.getAD_Role_ID());
						for (int i = 0; i < urs.length; i++)
						{
							if(urs[i].getAD_User_ID() == Env.getAD_User_ID(getCtx()))
							{
								autoApproval = true;
								break;
							}
						}
					}
					else if(resp.isOrganization())
					{
						throw new AdempiereException("@WPA.OrganizationResponsible@ " + resp);
					}
					else
					{
						throw new AdempiereException("@NotSupported@ "+resp);
					}
					// end MZ
				}
				if (autoApproval
					&& doc.processIt(DocAction.ACTION_Approve)
					&& doc.save())
					return true;	//	done
			}	//	approval
			return false;	//	wait for user
		}
		/******	User Form					******/
		else if (MWFNode.ACTION_UserForm.equals(action))
		{
			log.fine("Form:AD_Form_ID=" + m_node.getAD_Form_ID());
			return false;
		}
		else if (MWFNode.ACTION_SmartBrowse.equals(action))
		{
			log.fine("Form:AD_Browse_ID=" + m_node.getAD_Browse_ID());
			return false;
		}
		/******	User Window					******/
		else if (MWFNode.ACTION_UserWindow.equals(action))
		{
			log.fine("Window:AD_Window_ID=" + m_node.getAD_Window_ID());
			return false;
		}
		//
		throw new IllegalArgumentException("@WFA.InvalidAction@" + action);
	}	//	performWork
	
	/**
	 * 	Set Variable
	 *	@param value new Value
	 *	@param displayType display type
	 *	@param textMsg optional Message
	 *	@return true if set
	 *	@throws Exception if error
	 */
	private boolean setVariable(String value, int displayType, String textMsg, Trx trx) throws Exception
	{
		m_newValue = null;
		getPO(trx);
		MTable table = MTable.get(getCtx(), getAD_Table_ID());
		if (m_po == null) {
			throw new Exception("@WFA.PO@ @NotFound@ - @AD_Table_ID@ = " 
					+ (table != null? table.getTableName(): getAD_Table_ID()) + ", @Record_ID@ = " + getRecord_ID());
		}
		//	Set Value
		Object dbValue = null;
		if (value == null)
			;
		else if (displayType == DisplayType.YesNo)
			dbValue = new Boolean("Y".equals(value));
		else if (DisplayType.isNumeric(displayType))
			dbValue = new BigDecimal (value);
		else if (DisplayType.isID(displayType)) // Fix[3409739]DocValueWorkflow_cannot_set_var_ID_Column
			dbValue = new Integer (value);
		else
			dbValue = value;
		m_po.set_ValueOfColumn(getNode().getAD_Column_ID(), dbValue);
		m_po.saveEx();
		if (dbValue != null && !dbValue.equals(m_po.get_ValueOfColumn(getNode().getAD_Column_ID()))) {
			throw new Exception("@WFA.PO@ @WFA.NotUpdated@ - @AD_Table_ID@: " 
					+ (table != null? table.getTableName(): getAD_Table_ID()) + ", @Record_ID@ = " + getRecord_ID() 
					+ " - @WFA.Should@ = " + value + ", @Is@ = " + m_po.get_ValueOfColumn(m_node.getAD_Column_ID()));
		}
		//	Info
		String msg = "@" + getNode().getAttributeName() + "@" + "=" + value;
		if (textMsg != null && textMsg.length() > 0)
			msg += " - " + textMsg;
		setTextMsg (msg);
		m_newValue = value;
		return true;
	}	//	setVariable
	
	/**
	 * 	Set User Choice
	 * 	@param AD_User_ID user
	 *	@param value new Value
	 *	@param displayType display type
	 *	@param textMsg optional Message
	 *	@return true if set
	 *	@throws Exception if error
	 */
	public boolean setUserChoice (int AD_User_ID, String value, int displayType, 
		String textMsg) throws Exception
	{
		//	Check if user approves own document when a role is reponsible
		/*
		 * 2007-06-08, matthiasO.
		 * The following sequence makes sure that only users in roles which
		 * have the 'Approve own document flag' set can set the user choice
		 * of 'Y' (approve) or 'N' (reject).
		 * IMHO this is against the meaning of 'Approve own document': Why
		 * should a user who is faced with the task of approving documents
		 * generally be required to have the ability to approve his OWN
		 * documents? If the document to approve really IS his own document
		 * this will be respected when trying to find an approval user in
		 * the call to getApprovalUser(...) below.
		*/
		setWFState (StateEngine.STATE_Running);
		setAD_User_ID(AD_User_ID);
		Trx trx = ( get_TrxName() != null ) ? Trx.get(get_TrxName(), false) : null;
		boolean ok = setVariable (value, displayType, textMsg, trx);
		if (!ok)
			return false;

		String newState = StateEngine.STATE_Completed;
		//	Approval
		if (getNode().isUserApproval() && getPO(trx) instanceof DocAction)
		{
			DocAction doc = (DocAction) m_po;
			try
			{
				//	Not approved
				if (!"Y".equals(value))
				{
					newState = StateEngine.STATE_Aborted;
					if (!(doc.processIt (DocAction.ACTION_Reject)))
						setTextMsg ("@WFA.CannotReject@ @DocStatus@: " + doc.getDocStatus());
				}
				else
				{
					if (isInvoker())
					{
						int startAD_User_ID = Env.getAD_User_ID(getCtx());
						if (startAD_User_ID == 0)
							startAD_User_ID = doc.getDoc_User_ID();
						int nextAD_User_ID = getApprovalUser(startAD_User_ID, 
							doc.getC_Currency_ID(), doc.getApprovalAmt(),
							doc.getAD_Org_ID(), 
							startAD_User_ID == doc.getDoc_User_ID());	//	own doc
						//	No Approver
						if (nextAD_User_ID <= 0)
						{
							newState = StateEngine.STATE_Aborted;
							setTextMsg ("@WFA.CannotApprove@ - @WFA.NoApprover@");
							doc.processIt (DocAction.ACTION_Reject);
						}
						else if (startAD_User_ID != nextAD_User_ID)
						{
							forwardTo(nextAD_User_ID, "@WFA.NextApprover@");
							newState = StateEngine.STATE_Suspended;
						}
						else	//	Approve
						{
							if (!(doc.processIt (DocAction.ACTION_Approve)))
							{
								newState = StateEngine.STATE_Aborted;
								setTextMsg ("@WFA.CannotApprove@ - @DocStatus@: " + MRefList.getListName(getCtx(), DocAction.DOCSTATUS_AD_REFERENCE_ID, doc.getDocStatus()));
							}		
						}
					}
					//	No Invoker - Approve
					else if (!(doc.processIt (DocAction.ACTION_Approve)))
					{
						newState = StateEngine.STATE_Aborted;
						setTextMsg ("@WFA.CannotApprove@ - @DocStatus@: " + MRefList.getListName(getCtx(), DocAction.DOCSTATUS_AD_REFERENCE_ID, doc.getDocStatus()));
					}
				}
				doc.saveEx();
			}
			catch (Exception e)
			{
				newState = StateEngine.STATE_Terminated;
				setTextMsg ("@WFA.UserChoice@: " + e.toString());
				addTextMsg(e);
				log.log(Level.WARNING, "", e);
			}
			// Send Approval Notification
			if (newState.equals(StateEngine.STATE_Aborted)) {
				MUser to = new MUser(getCtx(), doc.getDoc_User_ID(), null);
				
				// send email
				if (to.isNotificationEMail()) {
					MClient client = MClient.get(getCtx(), doc.getAD_Client_ID());
					client.sendEMail(doc.getDoc_User_ID(), Msg.getMsg(getCtx(), "NotApproved")
							+ ": " + doc.getDocumentNo(), 
							(doc.getSummary() != null ? doc.getSummary() + Env.NL : "" )
							+ (doc.getProcessMsg() != null ? doc.getProcessMsg() + Env.NL : "") 
							+ (getTextMsg() != null ? getTextMsg() : ""), null);
				}

				// Send Note
				if (to.isNotificationNote()) {
					MNote note = new MNote(getCtx(), "NotApproved", doc.getDoc_User_ID(), null);
					note.setTextMsg((doc.getSummary() != null ? doc.getSummary() + Env.NL : "" )
							+ (doc.getProcessMsg() != null ? doc.getProcessMsg() + Env.NL : "") 
							+ (getTextMsg() != null ? getTextMsg() : ""));
					// 2007-06-08, matthiasO.
					// Add record information to the note, so that the user receiving the
					// note can jump to the doc easily
					note.setRecord(m_po.get_Table_ID(), m_po.get_ID());
					note.saveEx();
				}
			}
		}
		setWFState (newState);
		return ok;
	}	//	setUserChoice
	
	/**
	 * 	Forward To
	 *	@param AD_User_ID user
	 *	@param textMsg text message
	 *	@return true if forwarded
	 */
	public boolean forwardTo (int AD_User_ID, String textMsg)
	{
		if (AD_User_ID == getAD_User_ID())
		{
			log.log(Level.WARNING, "Same User - AD_User_ID=" + AD_User_ID);
			return false;
		}
		//
		MUser oldUser = MUser.get(getCtx(), getAD_User_ID());
		MUser user = MUser.get(getCtx(), AD_User_ID);
		if (user == null || user.get_ID() == 0)
		{
			log.log(Level.WARNING, "Does not exist - AD_User_ID=" + AD_User_ID);
			return false;
		}
		//	Update 
		setAD_User_ID (user.getAD_User_ID());
		setTextMsg(textMsg);
		save();
		//	Close up Old Event
		getEventAudit();
		m_audit.setAD_User_ID(oldUser.getAD_User_ID());
		m_audit.setTextMsg(getTextMsg());
		m_audit.setAttributeName("AD_User_ID");
		m_audit.setOldValue(oldUser.getName()+ "("+oldUser.getAD_User_ID()+")");
		m_audit.setNewValue(user.getName()+ "("+user.getAD_User_ID()+")");
		//
		m_audit.setWFState(getWFState());
		m_audit.setEventType(MWFEventAudit.EVENTTYPE_StateChanged);
		long ms = System.currentTimeMillis() - m_audit.getCreated().getTime();
		m_audit.setElapsedTimeMS(new BigDecimal(ms));
		m_audit.saveEx();
		//	Create new one
		m_audit = new MWFEventAudit(this);
		m_audit.saveEx();
		return true;
	}	//	forwardTo

	/**
	 * 	Set User Confirmation
	 * 	@param AD_User_ID user
	 *	@param textMsg optional message
	 */
	public void setUserConfirmation (int AD_User_ID, String textMsg)
	{
		log.fine(textMsg);
		setWFState (StateEngine.STATE_Running);
		setAD_User_ID(AD_User_ID);
		if (textMsg != null)
			setTextMsg (textMsg);
		setWFState (StateEngine.STATE_Completed);
	}	//	setUserConfirmation
	

	/**
	 * 	Fill Parameter
	 *	@param pInstance process instance
	 * 	@param trx transaction
	 */
	private void fillParameter(MPInstance pInstance, Trx trx)
	{
		getPO(trx);
		//
		MWFNodePara[] nParams = m_node.getParameters();
		MPInstancePara[] iParams = pInstance.getParameters();
		for (int pi = 0; pi < iParams.length; pi++)
		{
			MPInstancePara iPara = iParams[pi];
			for (int np = 0; np < nParams.length; np++)
			{
				MWFNodePara nPara = nParams[np];
				if (iPara.getParameterName().equals(nPara.getAttributeName()))
				{
					String variableName = nPara.getAttributeValue();
					log.fine(nPara.getAttributeName()
						+ " = " + variableName);
					//	Value - Constant/Variable
					Object value = variableName;
					if (variableName == null
						|| (variableName != null && variableName.length() == 0))
						value = null;
					else if (variableName.indexOf('@') != -1 && m_po != null)	//	we have a variable
					{
						//	Strip
						int index = variableName.indexOf('@');
						String columnName = variableName.substring(index+1);
						index = columnName.indexOf('@');
						if (index == -1)
						{
							log.warning(nPara.getAttributeName()
								+ " - cannot evaluate=" + variableName);
							break;
						}
						columnName = columnName.substring(0, index);
						index = m_po.get_ColumnIndex(columnName);
						if (index != -1)
						{
							value = m_po.get_Value(index);
						}
						else	//	not a column
						{
							//	try Env
							String env = Env.getContext(getCtx(), columnName);
							if (env.length() == 0)
							{
								log.warning(nPara.getAttributeName()
									+ " - not column nor environment =" + columnName 
									+ "(" + variableName + ")");
								break;
							}
							else
								value = env;
						}
					}	//	@variable@
					
					//	No Value
					if (value == null)
					{
						if (nPara.isMandatory())
							log.warning(nPara.getAttributeName() 
								+ " - empty - mandatory!");
						else
							log.fine(nPara.getAttributeName() 
								+ " - empty");
						break;
					}
					
					//	Convert to Type
					try
					{
						if (DisplayType.isNumeric(nPara.getDisplayType()) 
							|| DisplayType.isID(nPara.getDisplayType()))
						{
							BigDecimal bd = null;
							if (value instanceof BigDecimal)
								bd = (BigDecimal)value;
							else if (value instanceof Integer)
								bd = new BigDecimal (((Integer)value).intValue());
							else
								bd = new BigDecimal (value.toString());
							iPara.setP_Number(bd);
							log.fine(nPara.getAttributeName()
								+ " = " + variableName + " (=" + bd + "=)");
						}
						else if (DisplayType.isDate(nPara.getDisplayType()))
						{
							Timestamp ts = null;
							if (value instanceof Timestamp)
								ts = (Timestamp)value;
							else
								ts = Timestamp.valueOf(value.toString());
							iPara.setP_Date(ts);
							log.fine(nPara.getAttributeName()
								+ " = " + variableName + " (=" + ts + "=)");
						}
						else
						{
							iPara.setP_String(value.toString());
							log.fine(nPara.getAttributeName()
								+ " = " + variableName
								+ " (=" + value + "=) " + value.getClass().getName());
						}
						if (!iPara.save())
							log.warning("Not Saved - " + nPara.getAttributeName());
					}
					catch (Exception e)
					{
						log.warning(nPara.getAttributeName()
							+ " = " + variableName + " (" + value
							+ ") " + value.getClass().getName()
							+ " - " + e.getLocalizedMessage());
					}
					break;
				}
			}	//	node parameter loop
		}	//	instance parameter loop
	}	//	fillParameter

	/*********************************
	 * 	Send EMail
	 */
	private void sendEMail() {
		int userId;
		String documentInfo;
		String documentSummary;
		File pdf = null;
		//	Validate
		if (m_po instanceof DocAction) {
			DocAction doc = (DocAction)m_po;
			userId = doc.getDoc_User_ID();
			documentInfo = doc.getDocumentInfo();
			documentSummary = doc.getSummary();
			pdf = doc.createPDF();
		} else {
			userId = m_po.getCreatedBy();
			documentInfo = getNode().getDescription();
			documentSummary = getNodeHelp();
		}
		//	Change values
		if(Util.isEmpty(documentInfo)) {
			documentInfo = "";
		}
		if(Util.isEmpty(documentSummary)) {
			documentSummary = "";
		}
		MMailText text = new MMailText (getCtx(), m_node.getR_MailText_ID(), null);
		text.setPO(m_po, true);
		//
		String subject = documentInfo  
			+ ": " + text.getMailHeader();
		String message = text.getMailText(true)
			+ Env.NL + "-----" + Env.NL + documentInfo
			+ Env.NL + documentSummary;
		//
		MClient client = MClient.get(m_po.getCtx(), m_po.getAD_Client_ID());
		
		//	Explicit EMail
		sendEMail(client, 0, m_node.getEMail(), subject, message, pdf, text.isHtml());
		//	Recipient Type
		String recipient = m_node.getEMailRecipient();
		//	email to document user
		if (recipient == null || recipient.length() == 0)
			sendEMail(client, userId, null, subject, message, pdf, text.isHtml()); 
		else if (recipient.equals(MWFNode.EMAILRECIPIENT_DocumentBusinessPartner))
		{
			int index = m_po.get_ColumnIndex("AD_User_ID");
			if (index > 0)
			{
				Object oo = m_po.get_Value(index);
				if (oo instanceof Integer)
				{
					int AD_User_ID = ((Integer)oo).intValue();
					if (AD_User_ID != 0)
						sendEMail(client, AD_User_ID, null, subject, message, pdf, text.isHtml());
					else
						log.fine("No User in Document");
				}
				else
					log.fine("Empty User in Document");
			}
			else
				log.fine("No User Field in Document");
		}
		else if (recipient.equals(MWFNode.EMAILRECIPIENT_DocumentOwner))
			sendEMail(client, userId, null, subject, message, pdf, text.isHtml());
		else if (recipient.equals(MWFNode.EMAILRECIPIENT_SupervisorOfDocumentOwner) && userId > 0) {
			MUser documentUser = new MUser(getCtx() , userId, get_TrxName());
			if (documentUser.getSupervisor_ID() > 0 )
				sendEMail(client, documentUser.getSupervisor_ID(), null, subject, message, pdf, text.isHtml());
		}
		else if (recipient.equals(MWFNode.EMAILRECIPIENT_WFResponsible))
		{
			MWFResponsible resp = getResponsible();
			if (resp.isInvoker())
				sendEMail(client, userId, null, subject, message, pdf, text.isHtml()); 
			else if (resp.isHuman())
				sendEMail(client, resp.getAD_User_ID(), null, subject, message, pdf, text.isHtml()); 
			else if (resp.isRole())
			{
				MRole role = resp.getRole();
				if (role != null)
				{
					MUser[] users = MUser.getWithRole(role);
					for (int i = 0; i < users.length; i++)
						sendEMail(client, users[i].getAD_User_ID(), null, subject, message, pdf, text.isHtml()); 
				}
			}
			else if (resp.isOrganization())
			{
				MOrgInfo org = MOrgInfo.get(getCtx(), m_po.getAD_Org_ID(), get_TrxName());
				if (org.getSupervisor_ID() == 0)
					log.fine("No Supervisor for AD_Org_ID=" + m_po.getAD_Org_ID());
				else
					sendEMail(client, org.getSupervisor_ID(), null, subject, message, pdf, text.isHtml());
			}
		}
		else if (recipient.equals(MWFNode.EMAILRECIPIENT_SupervisorOfWFResponsible))
		{
			MWFResponsible resp = getResponsible();
			if (resp.isInvoker()) {
				MUser documentUser = new MUser(getCtx() , userId, get_TrxName());
				if (documentUser.getSupervisor_ID() > 0)
					sendEMail(client, documentUser.getSupervisor_ID(), null, subject, message, pdf, text.isHtml());

			}
			else if (resp.isHuman()) {
				MUser documentUser = new MUser(getCtx() , resp.getAD_User_ID() , get_TrxName());
				if (documentUser.getSupervisor_ID() > 0)
					sendEMail(client, documentUser.getSupervisor_ID(), null, subject, message, pdf, text.isHtml());
			}
			else if (resp.isRole())
			{
				MRole role = resp.getRole();
				if (role != null)
				{
					MUser[] users = MUser.getWithRole(role);
					for (int i = 0; i < users.length; i++) {
						if (users[i].getSupervisor_ID() > 0) {
							sendEMail(client, users[i].getSupervisor_ID(), null, subject, message, pdf, text.isHtml());
						}
					}
				}
			}
			else if (resp.isOrganization())
			{
				MOrgInfo org = MOrgInfo.get(getCtx(), m_po.getAD_Org_ID(), get_TrxName());
				if (org.getSupervisor_ID() == 0)
					log.fine("No Supervisor for AD_Org_ID=" + m_po.getAD_Org_ID());
				else {
					MUser user = new MUser(getCtx() , org.getSupervisor_ID() , get_TrxName());
					if (user.getSupervisor_ID() > 0)
						sendEMail(client, user.getSupervisor_ID() , null, subject, message, pdf, text.isHtml());
				}
			}
		}
	}	//	sendEMail
	
	/**
	 * 	Send actual EMail
	 *	@param client client
	 *	@param AD_User_ID user
	 *	@param email email string
	 *	@param subject subject
	 *	@param message message
	 *	@param pdf attachment
	 *  @param  isHtml isHtml
	 */
	private void sendEMail (MClient client, int AD_User_ID, String email,
		String subject, String message, File pdf, boolean isHtml)
	{
		if (AD_User_ID != 0)
		{
			MUser user = MUser.get(getCtx(), AD_User_ID);
			email = user.getEMail();
			if (email != null && email.length() > 0)
			{
				email = email.trim();
				if (!m_emails.contains(email))
				{
					client.sendEMail(null, user, subject, message, pdf,isHtml);
					m_emails.add(email);
				}
			}
			else
				log.info("No EMail for User " + user.getName());
		}
		else if (email != null && email.length() > 0)
		{
			//	Just one
			if (email.indexOf(';') == -1)
			{
				email = email.trim();
				if (!m_emails.contains(email))
				{
					client.sendEMail(email, subject, message, pdf, isHtml);
					m_emails.add(email);
				}
				return;
			}
			//	Multiple EMail
			StringTokenizer st = new StringTokenizer(email, ";");
			while (st.hasMoreTokens())
			{
				String email1 = st.nextToken().trim();
				if (email1.length() == 0)
					continue;
				if (!m_emails.contains(email1))
				{
					client.sendEMail(email1, subject, message, pdf, isHtml);
					m_emails.add(email1);
				}
			}
		}
	}	//	sendEMail
	
	/**************************************************************************
	 * 	Get Process Activity (Event) History
	 *	@return history
	 */
	public String getHistoryHTML()
	{
		SimpleDateFormat format = DisplayType.getDateFormat(DisplayType.DateTime);
		StringBuffer sb = new StringBuffer();
		MWFEventAudit[] events = MWFEventAudit.get(getCtx(), getAD_WF_Process_ID(), get_TrxName());
		for (int i = 0; i < events.length; i++)
		{
			MWFEventAudit audit = events[i];
		//	sb.append("<p style=\"width:400\">");
			sb.append("<p>");
			sb.append(format.format(audit.getCreated()))
				.append(" ")
				.append(getHTMLpart("b", audit.getNodeName()))
				.append(": ")
				.append(getHTMLpart(null, audit.getDescription()))
				.append(getHTMLpart("i", audit.getTextMsg()));
			sb.append("</p>");
		}
		return sb.toString();
	}	//	getHistory
	
	/**
	 * 	Get HTML part
	 *	@param tag HTML tag
	 *	@param content content
	 *	@return <tag>content</tag>
	 */
	private StringBuffer getHTMLpart (String tag, String content)
	{
		StringBuffer sb = new StringBuffer();
		if (content == null || content.length() == 0)
			return sb;
		if (tag != null && tag.length() > 0)
			sb.append("<").append(tag).append(">");
		sb.append(content);
		if (tag != null && tag.length() > 0)
			sb.append("</").append(tag).append(">");
		return sb;
	}	//	getHTMLpart
	
	
	/**************************************************************************
	 * 	Does the underlying PO (!) object have a PDF Attachment
	 * 	@return true if there is a pdf attachment
	 */
	public boolean isPdfAttachment()
	{
		if (getPO() == null)
			return false;
		return m_po.isPdfAttachment();
	}	//	isPDFAttachment

	/**
	 * 	Get PDF Attachment of underlying PO (!) object
	 *	@return pdf data or null
	 */
	public byte[] getPdfAttachment()
	{
		if (getPO() == null)
			return null;
		return m_po.getPdfAttachment();
	}	//	getPdfAttachment
	
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MWFActivity[");
		sb.append(get_ID()).append(",Node=");
		if (m_node == null)
			sb.append(getAD_WF_Node_ID());
		else
			sb.append(m_node.getName());
		sb.append(",State=").append(getWFState())
			.append(",AD_User_ID=").append(getAD_User_ID())
			.append(",").append(getCreated())
			.append ("]");
		return sb.toString ();
	} 	//	toString
	
	/**
	 * 	User String Representation.
	 * 	Suspended: Approve it (Joe)
	 *	@return info
	 */
	public String toStringX ()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getWFStateText())
			.append(": ").append(getNode().getName());
		if (getAD_User_ID() > 0)
		{
			MUser user = MUser.get(getCtx(), getAD_User_ID());
			sb.append(" (").append(user.getName()).append(")");
		}
		return sb.toString();
	}	//	toStringX

	/**
	 * 	Get Document Summary
	 *	@return PO Summary
	 */
	public String getSummary()
	{
		PO po = getPO();
		if (po == null)
			return null;
		StringBuffer sb = new StringBuffer();
		String[] keyColumns = po.get_KeyColumns();
		if ((keyColumns != null) && (keyColumns.length > 0))
			sb.append(Msg.getElement(getCtx(), keyColumns[0])).append(" ");
		int index = po.get_ColumnIndex("DocumentNo");
		if (index != -1)
			sb.append(po.get_Value(index)).append(": ");
		index = po.get_ColumnIndex("SalesRep_ID");
		Integer sr = null;
		if (index != -1)
			sr = (Integer)po.get_Value(index);
		else
		{
			index = po.get_ColumnIndex("AD_User_ID");
			if (index != -1)
				sr = (Integer)po.get_Value(index);
		}
		if (sr != null)
		{
			MUser user = MUser.get(getCtx(), sr.intValue());
			if (user != null)
				sb.append(user.getName()).append(" ");
		}
		//
		index = po.get_ColumnIndex("C_BPartner_ID");
		if (index != -1)
		{
			Integer bp = (Integer)po.get_Value(index);
			if (bp != null)
			{
				MBPartner partner = MBPartner.get(getCtx(), bp.intValue());
				if (partner != null)
					sb.append(partner.getName()).append(" ");
			}
		}
		return sb.toString();
	}	//	getSummary
}	//	MWFActivity
