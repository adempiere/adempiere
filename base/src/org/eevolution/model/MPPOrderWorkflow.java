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

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.wf.MWorkflow;

/**
 *	PP Order WorkFlow Model
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MPPOrderWorkflow.java,v 1.4 2006/07/30 00:51:05 jjanke Exp $
 * 
 *  @author Teo Sarca, http://www.arhipac.ro
 */
public class MPPOrderWorkflow extends X_PP_Order_Workflow
{
	private static final long serialVersionUID = 1L;

	/**
	 * 	Get Workflow from Cache
	 *	@param ctx context
	 *	@param AD_Workflow_ID id
	 *	@return workflow
	 */
	public static MPPOrderWorkflow get (Properties ctx, int PP_Order_Workflow_ID)
	{
		Integer key = new Integer (PP_Order_Workflow_ID);
		MPPOrderWorkflow retValue = (MPPOrderWorkflow)s_cache.get(key);
		if (retValue != null)
			return retValue;
		retValue = new MPPOrderWorkflow (ctx, PP_Order_Workflow_ID, null);
		if (retValue.get_ID() != 0)
			s_cache.put(key, retValue);
		return retValue;
	}	//	get
	
	public static MPPOrderWorkflow forPP_Order_ID(Properties ctx, int PP_Order_ID, String trxName)
	{
		final String whereClause = MPPOrderWorkflow.COLUMNNAME_PP_Order_ID+"=?";
		return new Query(ctx, MPPOrderWorkflow.Table_Name, whereClause, trxName)
				.setParameters(new Object[]{PP_Order_ID})
				.first();
	}

	/**	Single Cache					*/
	private static CCache<Integer,MPPOrderWorkflow>	s_cache = new CCache<Integer,MPPOrderWorkflow>("PP_Order_Workflow", 20);

	/**************************************************************************
	 * 	Create/Load Workflow
	 * 	@param ctx Context
	 * 	@param PP_Order_Workflow_ID ID
	 * 	@param trxName transaction
	 */
	public MPPOrderWorkflow (Properties ctx, int PP_Order_Workflow_ID, String trxName)
	{
		super (ctx, PP_Order_Workflow_ID, trxName);
		if (PP_Order_Workflow_ID == 0)
		{
			//	setPP_Order_Workflow_ID (0);
			//	setValue (null);
			//	setName (null);
			setAccessLevel (ACCESSLEVEL_Organization);
			setAuthor (MClient.get(ctx).getName());
			setDurationUnit(DURATIONUNIT_Day);
			setDuration (1);
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsDefault (false);
			setPublishStatus (PUBLISHSTATUS_UnderRevision);	// U
			setVersion (0);
			setCost (0);
			setWaitingTime (0);
			setWorkingTime (0);
		}
		loadNodes();
	}	//	MPPOrderWorkflow

	/**
	 * 	Load Constructor
	 * 	@param ctx context
	 * 	@param rs result set
	 * 	@param trxName transaction
	 */
	public MPPOrderWorkflow (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		loadNodes();
	}	//	Workflow
	
	/**
	 * Peer constructor
	 * @param workflow
	 * @param PP_Order_ID
	 * @param trxName
	 */
	public MPPOrderWorkflow (MWorkflow workflow, int PP_Order_ID, String trxName)
	{
		this(workflow.getCtx(), 0, trxName);
		setPP_Order_ID(PP_Order_ID);
		//
		setValue(workflow.getValue());
		setWorkflowType(workflow.getWorkflowType());
		setQtyBatchSize(workflow.getQtyBatchSize());
		setName(workflow.getName());
		setAccessLevel(workflow.getAccessLevel());
		setAuthor(workflow.getAuthor());
		setDurationUnit(workflow.getDurationUnit());
		setDuration(workflow.getDuration());
		setEntityType(workflow.getEntityType());
		setIsDefault(workflow.isDefault());
		setPublishStatus(workflow.getPublishStatus());
		setVersion(workflow.getVersion());
		setCost(workflow.getCost());
		setWaitingTime(workflow.getWaitingTime());
		setWorkingTime(workflow.getWorkingTime());
		setAD_WF_Responsible_ID(workflow.getAD_WF_Responsible_ID());
		setAD_Workflow_ID(workflow.getAD_Workflow_ID());
		setLimit(workflow.getLimit());
		setPriority(workflow.getPriority());
		setValidateWorkflow(workflow.getValidateWorkflow());
		setS_Resource_ID(workflow.getS_Resource_ID());
		setQueuingTime(workflow.getQueuingTime());
		setSetupTime(workflow.getSetupTime());
		setMovingTime(workflow.getMovingTime());
		setProcessType(workflow.getProcessType());
		setAD_Table_ID(workflow.getAD_Table_ID());
		setAD_WF_Node_ID(workflow.getAD_WF_Node_ID());
		setAD_WorkflowProcessor_ID(workflow.getAD_WorkflowProcessor_ID());
		setDescription(workflow.getDescription());
		setValidFrom(workflow.getValidFrom());
		setValidTo(workflow.getValidTo());
	}

	/**	WF Nodes				*/
	private List<MPPOrderNode> m_nodes = null;

	/**
	 * 	Load All Nodes
	 */
	protected void loadNodes()
	{
		final String whereClause = MPPOrderNode.COLUMNNAME_PP_Order_Workflow_ID+"=?";
		m_nodes = new Query(getCtx(), MPPOrderNode.Table_Name, whereClause, get_TrxName())
						.setParameters(new Object[]{get_ID()})
						.setOnlyActiveRecords(true)
						.list();
		log.fine("#" + m_nodes.size());
	}	//	loadNodes

	/**
	 * 	Get Number of Nodes
	 * 	@return number of nodes
	 */
	public int getNodeCount()
	{
		return m_nodes.size();
	}	//	getNextNodeCount

	/**
	 * 	Get the nodes
	 *  @param ordered ordered array
	 * 	@param AD_Client_ID for client
	 * 	@return array of nodes
	 */
	public MPPOrderNode[] getNodes(boolean ordered, int AD_Client_ID)
	{
		if (ordered)
			return getNodesInOrder(AD_Client_ID);
		//
		ArrayList<MPPOrderNode> list = new ArrayList<MPPOrderNode>();
		for (int i = 0; i < m_nodes.size(); i++)
		{
			MPPOrderNode node = m_nodes.get(i);
			if (node.getAD_Client_ID() == 0 || node.getAD_Client_ID() == AD_Client_ID)
				list.add(node);
		}
		MPPOrderNode[] retValue = new MPPOrderNode [list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getNodes

	/**
	 * 	Get the first node
	 * 	@return array of next nodes
	 */
	public MPPOrderNode getFirstNode()
	{
		return getNode (getPP_Order_Node_ID());
	}	//	getFirstNode

	/**
	 * 	Get Node with ID in Workflow
	 * 	@param PP_Order_Node_ID ID
	 * 	@return node or null
	 */
	public MPPOrderNode getNode (int PP_Order_Node_ID)
	{
		for (int i = 0; i < m_nodes.size(); i++)
		{
			MPPOrderNode node = (MPPOrderNode)m_nodes.get(i);
			if (node.getPP_Order_Node_ID() == PP_Order_Node_ID)
				return node;
		}
		return null;
	}	//	getNode

	/**
	 * 	Get the next nodes
	 * 	@param PP_Order_Node_ID ID
	 * 	@param AD_Client_ID for client
	 * 	@return array of next nodes or null
	 */
	public MPPOrderNode[] getNextNodes (int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode node = getNode(PP_Order_Node_ID);
		if (node == null || node.getNextNodeCount() == 0)
			return null;
		//
		MPPOrderNodeNext[] nexts = node.getTransitions(AD_Client_ID);
		ArrayList<MPPOrderNode> list = new ArrayList<MPPOrderNode>();
		for (int i = 0; i < nexts.length; i++)
		{
			MPPOrderNode next = getNode (nexts[i].getPP_Order_Next_ID());
			if (next != null)
				list.add(next);
		}

		//	Return Nodes
		MPPOrderNode[] retValue = new MPPOrderNode [list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getNextNodes

	/**
	 * 	Get The Nodes in Sequence Order
	 * 	@param AD_Client_ID client
	 * 	@return Nodes in sequence
	 */
	private MPPOrderNode[] getNodesInOrder(int AD_Client_ID)
	{
		ArrayList<MPPOrderNode> list = new ArrayList<MPPOrderNode>();
		addNodesSF (list, getPP_Order_Node_ID(), AD_Client_ID);	//	start with first
		//	Remaining Nodes
		if (m_nodes.size() != list.size())
		{
			//	Add Stand alone
			for (int n = 0; n < m_nodes.size(); n++)
			{
				MPPOrderNode node = (MPPOrderNode)m_nodes.get(n);
				if (node.getAD_Client_ID() == 0 || node.getAD_Client_ID() == AD_Client_ID)
				{
					boolean found = false;
					for (int i = 0; i < list.size(); i++)
					{
						MPPOrderNode existing = (MPPOrderNode)list.get(i);
						if (existing.getPP_Order_Node_ID() == node.getPP_Order_Node_ID())
						{
							found = true;
							break;
						}
					}
					if (!found)
					{
						log.log(Level.WARNING, "Added Node w/o transition: " + node);
						list.add(node);
					}
				}
			}
		}
		//
		MPPOrderNode[] nodeArray = new MPPOrderNode [list.size()];
		list.toArray(nodeArray);
		return nodeArray;
	}	//	getNodesInOrder

	/**
	 * 	Add Nodes recursively (depth first) to Ordered List
	 *  @param list list to add to
	 * 	@param PP_Order_Node_ID start node id
	 * 	@param AD_Client_ID for client
	 */
	private void addNodesDF (ArrayList<MPPOrderNode> list, int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode node = getNode (PP_Order_Node_ID);
		if (node != null && !list.contains(node))
		{
			list.add(node);
			//	Get Dependent
			MPPOrderNodeNext[] nexts = node.getTransitions(AD_Client_ID);
			for (int i = 0; i < nexts.length; i++)
				addNodesDF (list, nexts[i].getPP_Order_Next_ID(), AD_Client_ID);
		}
	}	//	addNodesDF

	/**
	 * 	Add Nodes recursively (sibling first) to Ordered List
	 *  @param list list to add to
	 * 	@param PP_Order_Node_ID start node id
	 * 	@param AD_Client_ID for client
	 */
	private void addNodesSF (ArrayList<MPPOrderNode> list, int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode node = getNode (PP_Order_Node_ID);
		if (node != null 
				&& (node.getAD_Client_ID() == 0 || node.getAD_Client_ID() == AD_Client_ID))
		{
			if (!list.contains(node))
				list.add(node);
			MPPOrderNodeNext[] nexts = node.getTransitions(AD_Client_ID);
			for (int i = 0; i < nexts.length; i++)
			{
				MPPOrderNode child = getNode (nexts[i].getPP_Order_Next_ID());
				if (child.getAD_Client_ID() == 0
						|| child.getAD_Client_ID() == AD_Client_ID)
				{
					if (!list.contains(child))
						list.add(child);
				}
			}
			//	Remainder Nodes not conncetd
			for (int i = 0; i < nexts.length; i++)
				addNodesSF (list, nexts[i].getPP_Order_Next_ID(), AD_Client_ID);
		}
	}	//	addNodesSF

	/**************************************************************************
	 * 	Get first transition (Next Node) of ID
	 * 	@param PP_Order_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return next PP_Order_Node_ID or 0
	 */
	public int getNext (int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode[] nodes = getNodesInOrder(AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			if (nodes[i].getPP_Order_Node_ID() == PP_Order_Node_ID)
			{
				MPPOrderNodeNext[] nexts = nodes[i].getTransitions(AD_Client_ID);
				if (nexts.length > 0)
				{
					return nexts[0].getPP_Order_Next_ID();
				}
				return 0;
			}
		}
		return 0;
	}	//	getNext

	/**
	 * 	Get Transitions (NodeNext) of ID
	 * 	@param PP_Order_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return array of next nodes
	 */
	public MPPOrderNodeNext[] getNodeNexts (int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode[] nodes = getNodesInOrder(AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			if (nodes[i].getPP_Order_Node_ID() == PP_Order_Node_ID)
			{
				return nodes[i].getTransitions(AD_Client_ID);
			}
		}
		return null;
	}	//	getNext

	/**
	 * 	Get (first) Previous Node of ID
	 * 	@param PP_Order_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return next PP_Order_Node_ID or 0
	 */
	public int getPrevious (int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode[] nodes = getNodesInOrder(AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			if (nodes[i].getPP_Order_Node_ID() == PP_Order_Node_ID)
			{
				if (i > 0)
					return nodes[i-1].getPP_Order_Node_ID();
				return 0;
			}
		}
		return 0;
	}	//	getPrevious

	/**
	 * 	Get very Last Node
	 * 	@param AD_Client_ID for client
	 * 	@return next PP_Order_Node_ID or 0
	 */
	public int getLast (int AD_Client_ID)
	{
		MPPOrderNode[] nodes = getNodesInOrder(AD_Client_ID);
		if (nodes.length > 0)
		{
			return nodes[nodes.length-1].getPP_Order_Node_ID();
		}
		return 0;
	}	//	getLast

	/**
	 * 	Is this the first Node
	 * 	@param PP_Order_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return true if first node
	 */
	public boolean isFirst (int PP_Order_Node_ID, int AD_Client_ID)
	{
		return PP_Order_Node_ID == getPP_Order_Node_ID();
	}	//	isFirst

	/**
	 * 	Is this the last Node
	 * 	@param PP_Order_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return true if last node
	 */
	public boolean isLast (int PP_Order_Node_ID, int AD_Client_ID)
	{
		MPPOrderNode[] nodes = getNodesInOrder(AD_Client_ID);
		return PP_Order_Node_ID == nodes[nodes.length-1].getPP_Order_Node_ID();
	}	//	isLast

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MPPOrderWorkflow[");
		sb.append(get_ID()).append("-").append(getName())
		.append ("]");
		return sb.toString ();
	} //	toString

	@Override
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		log.fine("Success=" + success);
		if (success && newRecord)
		{
			//	save all nodes -- Creating new Workflow
			MPPOrderNode[] nodes = getNodesInOrder(0);
			for (int i = 0; i < nodes.length; i++)
				nodes[i].saveEx(get_TrxName());
		}

		return success;
	}   //  afterSave

	/**
	 * 	Get Duration Base in Seconds
	 *	@return duration unit in seconds
	 */
	public long getDurationBaseSec ()
	{
		if (getDurationUnit() == null)
			return 0;
		else if (DURATIONUNIT_Second.equals(getDurationUnit()))
			return 1;
		else if (DURATIONUNIT_Minute.equals(getDurationUnit()))
			return 60;
		else if (DURATIONUNIT_Hour.equals(getDurationUnit()))
			return 3600;
		else if (DURATIONUNIT_Day.equals(getDurationUnit()))
			return 86400;
		else if (DURATIONUNIT_Month.equals(getDurationUnit()))
			return 2592000;
		else if (DURATIONUNIT_Year.equals(getDurationUnit()))
			return 31536000;
		return 0;
	}	//	getDurationBaseSec

	/**
	 * 	Get Duration CalendarField
	 *	@return Calendar.MINUTE, etc.
	 */
	public int getDurationCalendarField()
	{
		if (getDurationUnit() == null)
			return Calendar.MINUTE;
		else if (DURATIONUNIT_Second.equals(getDurationUnit()))
			return Calendar.SECOND;
		else if (DURATIONUNIT_Minute.equals(getDurationUnit()))
			return Calendar.MINUTE;
		else if (DURATIONUNIT_Hour.equals(getDurationUnit()))
			return Calendar.HOUR;
		else if (DURATIONUNIT_Day.equals(getDurationUnit()))
			return Calendar.DAY_OF_YEAR;
		else if (DURATIONUNIT_Month.equals(getDurationUnit()))
			return Calendar.MONTH;
		else if (DURATIONUNIT_Year.equals(getDurationUnit()))
			return Calendar.YEAR;
		return Calendar.MINUTE;
	}	//	getDurationCalendarField
}	//	MPPOrderWorkflow_ID
