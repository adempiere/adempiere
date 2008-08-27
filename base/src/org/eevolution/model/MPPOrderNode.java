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
package org.eevolution.model;

import java.awt.Point;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.model.MColumn;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.wf.MWFNode;

/**
 *	PP Order Workflow Node Model
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MWFNode.java,v 1.2 2006/07/30 00:51:05 jjanke Exp $
 * 
 * @author Teo Sarca, http://www.arhipac.ro
 */
public class MPPOrderNode extends X_PP_Order_Node
{
	private static final long serialVersionUID = 1L;


	/**
	 * 	Get WF Node from Cache
	 *	@param ctx context
	 *	@param PP_Order_Node_ID id
	 *	@return MPPOrderNode
	 */
	public static MPPOrderNode get (Properties ctx, int PP_Order_Node_ID)
	{
		MPPOrderNode retValue = s_cache.get (PP_Order_Node_ID);
		if (retValue != null)
			return retValue;
		retValue = new MPPOrderNode (ctx, PP_Order_Node_ID, null);
		if (retValue.get_ID () != 0)
			s_cache.put (PP_Order_Node_ID, retValue);
		return retValue;
	}	//	get

	/**	Cache						*/
	private static CCache<Integer,MPPOrderNode>	s_cache	= new CCache<Integer,MPPOrderNode> (Table_Name, 50);
	
	
	/**************************************************************************
	 * 	Standard Constructor - save to cache
	 *	@param ctx context
	 *	@param PP_Order_Node_ID id
	 *	@param trxName transaction
	 */
	public MPPOrderNode (Properties ctx, int PP_Order_Node_ID, String trxName)
	{
		super (ctx, PP_Order_Node_ID, trxName);
		if (PP_Order_Node_ID == 0)
		{
		//	setPP_Order_Node_ID (0);
		//	setPP_Order_Workflow_ID (0);
		//	setValue (null);
		//	setName (null);
			setAction (ACTION_WaitSleep);
			setCost (Env.ZERO);
			setDuration (0);
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsCentrallyMaintained (true);	// Y
			setJoinElement (JOINELEMENT_XOR);	// X
			setLimit (0);
			setSplitElement (SPLITELEMENT_XOR);	// X
			setWaitingTime (0);
			setXPosition (0);
			setYPosition (0);
		}
		//	Save to Cache
		if (get_ID() != 0)
			s_cache.put (getPP_Order_Node_ID(), this);
	}	//	MPPOrderNode

	/**
	 * 	Parent Constructor
	 *	@param wf workflow (parent)
	 *	@param Value value
	 *	@param Name name
	 */
	public MPPOrderNode (MPPOrderWorkflow wf, String Value, String Name)
	{
		this (wf.getCtx(), 0, wf.get_TrxName());
		setClientOrg(wf);
		setPP_Order_Workflow_ID (wf.getPP_Order_Workflow_ID());
		setValue (Value);
		setName (Name);
		m_durationBaseMS = wf.getDurationBaseSec() * 1000;
	}	//	MPPOrderNode
	
	/**
	 * Peer constructor
	 * @param wfNode
	 * @param PP_Order_Workflow
	 * @param qtyOrdered
	 * @param trxName
	 */
	public MPPOrderNode (MWFNode wfNode, MPPOrderWorkflow PP_Order_Workflow,
							BigDecimal qtyOrdered,
							String trxName)
	{
		this(wfNode.getCtx(), 0, trxName);
		setPP_Order_ID(PP_Order_Workflow.getPP_Order_ID());
		setPP_Order_Workflow_ID(PP_Order_Workflow.getPP_Order_Workflow_ID());
		//
		setAction(wfNode.getAction());
		setAD_WF_Node_ID(wfNode.getAD_WF_Node_ID());
		setAD_WF_Responsible_ID(wfNode.getAD_WF_Responsible_ID());
		setAD_Workflow_ID(wfNode.getAD_Workflow_ID());
		setCost(wfNode.getCost());
		setDuration(wfNode.getDuration());
		setEntityType(wfNode.getEntityType());
		setIsCentrallyMaintained(wfNode.isCentrallyMaintained());
		setJoinElement(wfNode.getJoinElement()); // X
		setLimit(wfNode.getLimit());
		setName(wfNode.getName());
		setPriority(wfNode.getPriority());
		setSplitElement(wfNode.getSplitElement()); // X
		setSubflowExecution(wfNode.getSubflowExecution());
		setValue(wfNode.getValue());
		setS_Resource_ID(wfNode.getS_Resource_ID());
		setSetupTime(wfNode.getSetupTime());
		setSetupTimeRequiered(wfNode.getSetupTime());
		BigDecimal time = new BigDecimal(wfNode.getDuration()).multiply(qtyOrdered);
		setDurationRequiered(time.intValue());
		setMovingTime(wfNode.getMovingTime());
		setWaitingTime(wfNode.getWaitingTime());
		setWorkingTime(wfNode.getWorkingTime());
		setQueuingTime(wfNode.getQueuingTime());
		setXPosition(wfNode.getXPosition());
		setYPosition(wfNode.getYPosition());
		setDocAction(wfNode.getDocAction());
		setAD_Column_ID(wfNode.getAD_Column_ID());
		setAD_Form_ID(wfNode.getAD_Form_ID());
		setAD_Image_ID(wfNode.getAD_Image_ID());
		setAD_Window_ID(wfNode.getAD_Window_ID());
		setAD_Process_ID(wfNode.getAD_Process_ID());
		setAttributeName(wfNode.getAttributeName());
		setAttributeValue(wfNode.getAttributeValue());
		setC_BPartner_ID(wfNode.getC_BPartner_ID());
		setStartMode(wfNode.getStartMode());
		setFinishMode(wfNode.getFinishMode());
		setValidFrom(wfNode.getValidFrom());
		setValidTo(wfNode.getValidTo());
	}
	
	/**
	 * 	Load Constructor - save to cache
	 * 	@param ctx context
	 * 	@param rs result set to load info from
	 *	@param trxName transaction
	 */
	public MPPOrderNode (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		loadNext();
		//	Save to Cache
		s_cache.put (getPP_Order_Node_ID(), this);
	}	//	MPPOrderNode
	
	
	/**	Next Modes				*/
	private List<MPPOrderNodeNext>	m_next = new ArrayList<MPPOrderNodeNext>();
	/**	Column					*/
	private MColumn		m_column = null;
	/** Duration Base MS		*/
	private long			m_durationBaseMS = -1;
	
	/**
	 * 	Set Client Org
	 *	@param AD_Client_ID client
	 *	@param AD_Org_ID org
	 */
	public void setClientOrg (int AD_Client_ID, int AD_Org_ID)
	{
		super.setClientOrg (AD_Client_ID, AD_Org_ID);
	}	//	setClientOrg

	/**
	 * 	Load Next
	 */
	private void loadNext()
	{
		boolean splitAnd = SPLITELEMENT_AND.equals(getSplitElement());
		String whereClause = "PP_Order_Node_ID=? AND IsActive='Y'";
		m_next = new Query(getCtx(), MPPOrderNodeNext.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{get_ID()})
											.setOrderBy(MPPOrderNodeNext.COLUMNNAME_SeqNo)
											.list();
		for (MPPOrderNodeNext next : m_next) {
			next.setFromSplitAnd(splitAnd);
		}
		log.fine("#" + m_next.size());
	}	//	loadNext

	/**
	 * 	Get Number of Next Nodes
	 * 	@return number of next nodes
	 */
	public int getNextNodeCount()
	{
		return m_next.size();
	}	//	getNextNodeCount

	/**
	 * 	Get the transitions
	 * 	@param AD_Client_ID for client
	 * 	@return array of next nodes
	 */
	public MPPOrderNodeNext[] getTransitions(int AD_Client_ID)
	{
		ArrayList<MPPOrderNodeNext> list = new ArrayList<MPPOrderNodeNext>();
		for (int i = 0; i < m_next.size(); i++)
		{
			MPPOrderNodeNext next = m_next.get(i);
			if (next.getAD_Client_ID() == 0 || next.getAD_Client_ID() == AD_Client_ID)
				list.add(next);
		}
		MPPOrderNodeNext[] retValue = new MPPOrderNodeNext [list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getNextNodes

	/**
	 * 	Set Position
	 * 	@param position point
	 */
	public void setPosition (Point position)
	{
		setPosition(position.x, position.y);
	}	//	setPosition

	/**
	 * 	Set Position
	 * 	@param x x
	 * 	@param y y
	 */
	public void setPosition (int x, int y)
	{
		setXPosition(x);
		setYPosition(y);
	}	//	setPosition

	/**
	 * 	Get Position
	 * 	@return position point
	 */
	public Point getPosition ()
	{
		return new Point (getXPosition(), getYPosition());
	}	//	getPosition

	/**
	 * 	Get Action Info
	 *	@return info
	 */
	public String getActionInfo()
	{
		String action = getAction();
		if (ACTION_AppsProcess.equals(action))
			return "Process:AD_Process_ID=" + getAD_Process_ID();
		else if (ACTION_DocumentAction.equals(action))
			return "DocumentAction=" + getDocAction();
		else if (ACTION_AppsReport.equals(action))
			return "Report:AD_Process_ID=" + getAD_Process_ID();
		else if (ACTION_AppsTask.equals(action))
			return "Task:AD_Task_ID=" + getAD_Task_ID();
		else if (ACTION_SetVariable.equals(action))
			return "SetVariable:AD_Column_ID=" + getAD_Column_ID();
		else if (ACTION_SubWorkflow.equals(action))
			return "Workflow:PP_Order_Workflow_ID=" + getPP_Order_Workflow_ID();
		else if (ACTION_UserChoice.equals(action))
			return "UserChoice:AD_Column_ID=" + getAD_Column_ID();
		else if (ACTION_UserWorkbench.equals(action))
			return "Workbench:?";
		else if (ACTION_UserForm.equals(action))
			return "Form:AD_Form_ID=" + getAD_Form_ID();
		else if (ACTION_UserWindow.equals(action))
			return "Window:AD_Window_ID=" + getAD_Window_ID();
		//else if (ACTION_WaitSleep.equals(action))
		//	return "Sleep:WaitTime=" + getWaitTime();
		return "??";
	}	//	getActionInfo
	
	
	/**
	 * 	Get Attribute Name
	 *	@see org.compiere.model.X_PP_Order_Node#getAttributeName()
	 *	@return Attribute Name
	 */
	@Override
	public String getAttributeName ()
	{
		if (getAD_Column_ID() == 0)
			return super.getAttributeName();
		//	We have a column
		String attribute = super.getAttributeName();
		if (attribute != null && attribute.length() > 0)
			return attribute;
		setAttributeName(getColumn().getColumnName());
		return super.getAttributeName ();
	}	//	getAttributeName
	
	
	/**
	 * 	Get Column
	 *	@return column if valid
	 */
	public MColumn getColumn()
	{
		if (getAD_Column_ID() == 0)
			return null;
		if (m_column == null)
			m_column = MColumn.get(getCtx(), getAD_Column_ID());
		return m_column;
	}	//	getColumn
	
	/**
	 * 	Is this an Approval setp?
	 *	@return true if User Approval
	 */
	public boolean isUserApproval()
	{
		if (!ACTION_UserChoice.equals(getAction()))
			return false;
		return getColumn() != null 
			&& "IsApproved".equals(getColumn().getColumnName());
	}	//	isApproval

	/**
	 * 	Is this a User Choice step?
	 *	@return true if User Choice
	 */
	public boolean isUserChoice()
	{
		return ACTION_UserChoice.equals(getAction());
	}	//	isUserChoice
	
	/**
	 * 	Is this a Manual user step?
	 *	@return true if Window/Form/Workbench
	 */
	public boolean isUserManual()
	{
		if (ACTION_UserForm.equals(getAction())
			|| ACTION_UserWindow.equals(getAction())
			|| ACTION_UserWorkbench.equals(getAction()))
			return true;
		return false;
	}	//	isUserManual
	
	
	/**
	 * 	Get Duration in ms
	 *	@return duration in ms
	 */
	public long getDurationMS ()
	{
		long duration = super.getDuration ();
		if (duration == 0)
			return 0;
		if (m_durationBaseMS == -1)
			m_durationBaseMS = getWorkflow().getDurationBaseSec() * 1000;
		return duration * m_durationBaseMS;
	}	//	getDurationMS
	
	/**
	 * 	Get Duration Limit in ms
	 *	@return duration limit in ms
	 */
	public long getLimitMS ()
	{
		long limit = super.getLimit ();
		if (limit == 0)
			return 0;
		if (m_durationBaseMS == -1)
			m_durationBaseMS = getWorkflow().getDurationBaseSec() * 1000;
		return limit * m_durationBaseMS;
	}	//	getLimitMS
	
	/**
	 * 	Get Duration CalendarField
	 *	@return Calendar.MINUTE, etc.
	 */
	public int getDurationCalendarField()
	{
		return getWorkflow().getDurationCalendarField();
	}	//	getDurationCalendarField

	/**
	 * 	Get Workflow
	 *	@return workflow
	 */
	public MPPOrderWorkflow getWorkflow()
	{
		return MPPOrderWorkflow.get(getCtx(), getPP_Order_Workflow_ID());
	}	//	getWorkflow
	
	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPPOrderNode[");
		sb.append(get_ID())
			.append("-").append(getName())
			.append(",Action=").append(getActionInfo())
			.append ("]");
		return sb.toString ();
	}	//	toString

	/**
	 * 	User String Representation
	 *	@return info
	 */
	public String toStringX ()
	{
		StringBuffer sb = new StringBuffer ("MPPOrderNode[");
		sb.append(getName())
			.append("-").append(getActionInfo())
			.append("]");
		return sb.toString ();
	}	//	toStringX
	
	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true if can be saved
	 */
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		String action = getAction();
		if (action.equals(ACTION_WaitSleep))
			;
		else if (action.equals(ACTION_AppsProcess) || action.equals(ACTION_AppsReport)) 
		{
			if (getAD_Process_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Process_ID"));
				return false;
			}
		}
		else if (action.equals(ACTION_AppsTask)) 
		{
			if (getAD_Task_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Task_ID"));
				return false;
			}
		}
		else if (action.equals(ACTION_DocumentAction)) 
		{
			if (getDocAction() == null || getDocAction().length() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "DocAction"));
				return false;
			}
		}
		/*else if (action.equals(ACTION_EMail))
		{
			if (getR_MailText_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "R_MailText_ID"));
				return false;
			}
		}*/
		else if (action.equals(ACTION_SetVariable)) 
		{
			if (getAttributeValue() == null)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "AttributeValue"));
				return false;
			}
		}
		else if (action.equals(ACTION_SubWorkflow))
		{
			if (getPP_Order_Workflow_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "PP_Order_Workflow_ID"));
				return false;
			}
		}
		else if (action.equals(ACTION_UserChoice)) 
		{
			if (getAD_Column_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Column_ID"));
				return false;
			}
		}
		else if (action.equals(ACTION_UserForm)) 
		{
			if (getAD_Form_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Form_ID"));
				return false;
			}
		}
		else if (action.equals(ACTION_UserWindow)) 
		{
			if (getAD_Window_ID() == 0)
			{
				log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Window_ID"));
				return false;
			}
		}
//		else if (action.equals(ACTION_UserWorkbench)) 
//		{
//		&& getAD_Workbench_ID() == 0)
//			log.saveError("FillMandatory", Msg.getElement(getCtx(), "AD_Workbench_ID"));
//			return false;
//		}
		
		return true;
	}	//	beforeSave
}	//	M_WFNext
