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
package org.compiere.wf;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MMenu;
import org.compiere.model.MProduct;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Workflow;
import org.compiere.process.ProcessInfo;
import org.compiere.process.StateEngine;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

/**
 *	WorkFlow Model
 *
 * 	@author 	Jorg Janke
 * 	@version 	$Id: MWorkflow.java,v 1.4 2006/07/30 00:51:05 jjanke Exp $
 * 
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>FR [ 2214883 ] Remove SQL code and Replace for Query
 * 			<li>BF [ 2665963 ] Copy Workflow name in Activity name
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li> FR [ 94 ] "IsDocument" flag in table for create default columns
 *			@see https://github.com/adempiere/adempiere/issues/94
 */
public class MWorkflow extends X_AD_Workflow
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4925514638954671534L;


	/**
	 * 	Get Workflow from Cache
	 *	@param ctx context
	 *	@param AD_Workflow_ID id
	 *	@return workflow
	 */
	public static MWorkflow get (Properties ctx, int AD_Workflow_ID)
	{
		MWorkflow retValue = (MWorkflow)s_cache.get(AD_Workflow_ID);
		if (retValue != null)
			return retValue;
		retValue = new MWorkflow (ctx, AD_Workflow_ID, null);
		if (retValue.get_ID() != 0)
			s_cache.put(AD_Workflow_ID, retValue);
		return retValue;
	}	//	get
	
	/**
	 * Get Workflow from Table if is Document
	 * @param ctx
	 * @param p_AD_Table_ID
	 * @param trxName
	 * @return
	 */
	public static MWorkflow getWorkFlowFromDocumentTable(Properties ctx, int p_AD_Table_ID, String trxName) {
		int m_AD_Workflow_ID = DB.getSQLValue(trxName, "SELECT AD_Workflow_ID "
				+ "FROM AD_Workflow "
				+ "WHERE AD_Client_ID = ? "
				+ "AND AD_Table_ID = ? "
				+ "AND WorkflowType = ?", 0, p_AD_Table_ID, WORKFLOWTYPE_DocumentProcess);
		//	Validate null
		if(m_AD_Workflow_ID <= 0) {
			return null;
		}
		//	Get from Cache or DB
		return get(ctx, m_AD_Workflow_ID);
	}
	
	
	
	/**
	 * 	Get Doc Value Workflow
	 *	@param ctx context
	 *	@param AD_Client_ID client
	 *	@param AD_Table_ID table
	 *	@return document value workflow array or null
	 */
	public static MWorkflow[] getDocValue (Properties ctx, int AD_Client_ID, int AD_Table_ID
			, String trxName //Bug 1568766 Trx should be kept all along the road		
	)
	{
		String key = "C" + AD_Client_ID + "T" + AD_Table_ID;
		//	Reload
		if (s_cacheDocValue.isReset())
		{
			final String whereClause = "WorkflowType=? AND IsValid=?";
			List<MWorkflow> workflows = new Query(ctx, Table_Name, whereClause, trxName)
				.setParameters(new Object[]{WORKFLOWTYPE_DocumentValue, true})
				.setOnlyActiveRecords(true)
				.setOrderBy("AD_Client_ID, AD_Table_ID")
				.list();
			ArrayList<MWorkflow> list = new ArrayList<MWorkflow>();
			String oldKey = "";
			String newKey = null;
			for (MWorkflow wf : workflows)
			{
				newKey = "C" + wf.getAD_Client_ID() + "T" + wf.getAD_Table_ID();
				if (!newKey.equals(oldKey) && list.size() > 0)
				{
					MWorkflow[] wfs = new MWorkflow[list.size()];
					list.toArray(wfs);
					s_cacheDocValue.put (oldKey, wfs);
					list = new ArrayList<MWorkflow>();
				}
				oldKey = newKey;
				list.add(wf);
			}
			
			//	Last one
			if (list.size() > 0)
			{
				MWorkflow[] wfs = new MWorkflow[list.size()];
				list.toArray(wfs);
				s_cacheDocValue.put (oldKey, wfs);
			}
			s_log.config("#" + s_cacheDocValue.size());
		}
		//	Look for Entry
		MWorkflow[] retValue = (MWorkflow[])s_cacheDocValue.get(key);
		//hengsin: this is not threadsafe
		/*
		//set trxName to all workflow instance
		if ( retValue != null && retValue.length > 0 )
		{
			for(int i = 0; i < retValue.length; i++)
			{
				retValue[i].set_TrxName(trxName);
			}
		}*/
		return retValue;
	}	//	getDocValue
	
	
	/**	Single Cache					*/
	private static CCache<Integer,MWorkflow>	s_cache = new CCache<Integer,MWorkflow>("AD_Workflow", 20);
	/**	Document Value Cache			*/
	private static CCache<String,MWorkflow[]>	s_cacheDocValue = new CCache<String,MWorkflow[]> ("AD_Workflow", 5);
	/**	Static Logger	*/
	private static CLogger	s_log	= CLogger.getCLogger (MWorkflow.class);
	
	
	/**************************************************************************
	 * 	Create/Load Workflow
	 * 	@param ctx Context
	 * 	@param AD_Workflow_ID ID
	 * 	@param trxName transaction
	 */
	public MWorkflow (Properties ctx, int AD_Workflow_ID, String trxName)
	{
		super (ctx, AD_Workflow_ID, trxName);
		if (AD_Workflow_ID == 0)
		{
		//	setAD_Workflow_ID (0);
		//	setValue (null);
		//	setName (null);
			setAccessLevel (ACCESSLEVEL_Organization);
			setAuthor ("ComPiere, Inc.");
			setDurationUnit(DURATIONUNIT_Day);
			setDuration (1);
			setEntityType (ENTITYTYPE_UserMaintained);	// U
			setIsDefault (false);
			setPublishStatus (PUBLISHSTATUS_UnderRevision);	// U
			setVersion (0);
			setCost (Env.ZERO);
			setWaitingTime (0);
			setWorkingTime (0);
			setIsBetaFunctionality(false);
		}
		loadTrl();
		loadNodes();
	}	//	MWorkflow
	
	/**
	 * 	Load Constructor
	 * 	@param ctx context
	 * 	@param rs result set
	 * 	@param trxName transaction
	 */
	public MWorkflow (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		loadTrl();
		loadNodes();
	}	//	Workflow

	/**	WF Nodes				*/
	private List<MWFNode>	m_nodes = new ArrayList<MWFNode>();

	/**	Translated Name			*/
	private String			m_name_trl = null;
	/**	Translated Description	*/
	private String			m_description_trl = null;
	/**	Translated Help			*/
	private String			m_help_trl = null;
	/**	Translation Flag		*/
	private boolean			m_translated = false;

	/**
	 * 	Load Translation
	 */
	private void loadTrl()
	{
		if (Env.isBaseLanguage(getCtx(), "AD_Workflow") || get_ID() == 0)
			return;
		String sql = "SELECT Name, Description, Help FROM AD_Workflow_Trl WHERE AD_Workflow_ID=? AND AD_Language=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, get_ID());
			pstmt.setString(2, Env.getAD_Language(getCtx()));
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				m_name_trl = rs.getString(1);
				m_description_trl = rs.getString(2);
				m_help_trl = rs.getString(3);
				m_translated = true;
			}
		}
		catch (SQLException e)
		{
			//log.log(Level.SEVERE, sql, e);
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		log.fine("Translated=" + m_translated);
	}	//	loadTrl

	/**
	 * 	Load All Nodes
	 */
	private void loadNodes()
	{
		m_nodes = new Query(getCtx(), MWFNode.Table_Name, "AD_WorkFlow_ID=?", get_TrxName())
			.setParameters(new Object[]{get_ID()})
			.setOnlyActiveRecords(true)
			.list();
		log.fine("#" + m_nodes.size());
	}	//	loadNodes

	
	/**************************************************************************
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
	public MWFNode[] getNodes(boolean ordered, int AD_Client_ID)
	{
		if (ordered)
			return getNodesInOrder(AD_Client_ID);
		//
		ArrayList<MWFNode> list = new ArrayList<MWFNode>();
		for (int i = 0; i < m_nodes.size(); i++)
		{
			MWFNode node = m_nodes.get(i);
			if (!node.isActive())
				continue;
			if (node.getAD_Client_ID() == 0 || node.getAD_Client_ID() == AD_Client_ID)
				list.add(node);
		}
		MWFNode[] retValue = new MWFNode [list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getNodes

	/**
	 * 	Get the first node
	 * 	@return array of next nodes
	 */
	public MWFNode getFirstNode()
	{
		return getNode (getAD_WF_Node_ID());
	}	//	getFirstNode

	/**
	 * 	Get Node with ID in Workflow
	 * 	@param AD_WF_Node_ID ID
	 * 	@return node or null
	 */
	protected MWFNode getNode (int AD_WF_Node_ID)
	{
		for (int i = 0; i < m_nodes.size(); i++)
		{
			MWFNode node = (MWFNode)m_nodes.get(i);
			if (node.getAD_WF_Node_ID() == AD_WF_Node_ID)
				return node;
		}
		return null;
	}	//	getNode

	/**
	 * 	Get the next nodes
	 * 	@param AD_WF_Node_ID ID
	 * 	@param AD_Client_ID for client
	 * 	@return array of next nodes or null
	 */
	public MWFNode[] getNextNodes (int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode node = getNode(AD_WF_Node_ID);
		if (node == null || node.getNextNodeCount() == 0)
			return null;
		//
		MWFNodeNext[] nexts = node.getTransitions(AD_Client_ID);
		ArrayList<MWFNode> list = new ArrayList<MWFNode>();
		for (int i = 0; i < nexts.length; i++)
		{
			MWFNode next = getNode (nexts[i].getAD_WF_Next_ID());
			if (next != null)
				list.add(next);
		}

		//	Return Nodes
		MWFNode[] retValue = new MWFNode [list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getNextNodes

	/**
	 * 	Get The Nodes in Sequence Order
	 * 	@param AD_Client_ID client
	 * 	@return Nodes in sequence
	 */
	private MWFNode[] getNodesInOrder(int AD_Client_ID)
	{
		ArrayList<MWFNode> list = new ArrayList<MWFNode>();
		addNodesSF (list, getAD_WF_Node_ID(), AD_Client_ID);	//	start with first
		//	Remaining Nodes
		if (m_nodes.size() != list.size())
		{
			//	Add Stand alone
			for (int n = 0; n < m_nodes.size(); n++)
			{
				MWFNode node = (MWFNode)m_nodes.get(n);
				if (!node.isActive())
					continue;
				if (node.getAD_Client_ID() == 0 || node.getAD_Client_ID() == AD_Client_ID)
				{
					boolean found = false;
					for (int i = 0; i < list.size(); i++)
					{
						MWFNode existing = (MWFNode)list.get(i);
						if (existing.getAD_WF_Node_ID() == node.getAD_WF_Node_ID())
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
		MWFNode[] nodeArray = new MWFNode [list.size()];
		list.toArray(nodeArray);
		return nodeArray;
	}	//	getNodesInOrder

	/**
	 * 	Add Nodes recursively (depth first) to Ordered List
	 *  @param list list to add to
	 * 	@param AD_WF_Node_ID start node id
	 * 	@param AD_Client_ID for client
	 */
	private void addNodesDF (ArrayList<MWFNode> list, int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode node = getNode (AD_WF_Node_ID);
		if (node != null && !list.contains(node))
		{
			list.add(node);
			//	Get Dependent
			MWFNodeNext[] nexts = node.getTransitions(AD_Client_ID);
			for (int i = 0; i < nexts.length; i++)
			{
				if (nexts[i].isActive())
					addNodesDF (list, nexts[i].getAD_WF_Next_ID(), AD_Client_ID);
			}
		}
	}	//	addNodesDF

	/**
	 * 	Add Nodes recursively (sibling first) to Ordered List
	 *  @param list list to add to
	 * 	@param AD_WF_Node_ID start node id
	 * 	@param AD_Client_ID for client
	 */
	private void addNodesSF (ArrayList<MWFNode> list, int AD_WF_Node_ID, int AD_Client_ID)
	{
		ArrayList<MWFNode> tmplist = new ArrayList<MWFNode> ();
		MWFNode node = getNode (AD_WF_Node_ID);
		if (node != null 
			&& (node.getAD_Client_ID() == 0 || node.getAD_Client_ID() == AD_Client_ID))
		{
			if (!list.contains(node))
				list.add(node);
			MWFNodeNext[] nexts = node.getTransitions(AD_Client_ID);
			for (int i = 0; i < nexts.length; i++)
			{
				MWFNode child = getNode (nexts[i].getAD_WF_Next_ID());
				if (!child.isActive())
					continue;
				if (child.getAD_Client_ID() == 0
					|| child.getAD_Client_ID() == AD_Client_ID)
				{
					if (!list.contains(child)){
						list.add(child);
						tmplist.add(child);
					}
				}
			}
			//	Remainder Nodes not connected
			for (int i = 0; i < tmplist.size(); i++)
				addNodesSF (list, tmplist.get(i).get_ID(), AD_Client_ID);
		}
	}	//	addNodesSF
	
	/**************************************************************************
	 * 	Get first transition (Next Node) of ID
	 * 	@param AD_WF_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return next AD_WF_Node_ID or 0
	 */
	public int getNext (int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode[] nodes = getNodesInOrder(AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			if (nodes[i].getAD_WF_Node_ID() == AD_WF_Node_ID)
			{
				MWFNodeNext[] nexts = nodes[i].getTransitions(AD_Client_ID);
				if (nexts.length > 0)
					return nexts[0].getAD_WF_Next_ID();
				return 0;
			}
		}
		return 0;
	}	//	getNext

	/**
	 * 	Get Transitions (NodeNext) of ID
	 * 	@param AD_WF_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return array of next nodes
	 */
	public MWFNodeNext[] getNodeNexts (int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode[] nodes = getNodesInOrder(AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			if (nodes[i].getAD_WF_Node_ID() == AD_WF_Node_ID)
			{
				return nodes[i].getTransitions(AD_Client_ID);
			}
		}
		return null;
	}	//	getNext
	
	/**
	 * 	Get (first) Previous Node of ID
	 * 	@param AD_WF_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return next AD_WF_Node_ID or 0
	 */
	public int getPrevious (int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode[] nodes = getNodesInOrder(AD_Client_ID);
		for (int i = 0; i < nodes.length; i++)
		{
			if (nodes[i].getAD_WF_Node_ID() == AD_WF_Node_ID)
			{
				if (i > 0)
					return nodes[i-1].getAD_WF_Node_ID();
				return 0;
			}
		}
		return 0;
	}	//	getPrevious

	/**
	 * 	Get very Last Node
	 * 	@param AD_WF_Node_ID ignored
	 * 	@param AD_Client_ID for client
	 * 	@return next AD_WF_Node_ID or 0
	 */
	public int getLast (int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode[] nodes = getNodesInOrder(AD_Client_ID);
		if (nodes.length > 0)
			return nodes[nodes.length-1].getAD_WF_Node_ID();
		return 0;
	}	//	getLast

	/**
	 * 	Is this the first Node
	 * 	@param AD_WF_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return true if first node
	 */
	public boolean isFirst (int AD_WF_Node_ID, int AD_Client_ID)
	{
		return AD_WF_Node_ID == getAD_WF_Node_ID();
	}	//	isFirst

	/**
	 * 	Is this the last Node
	 * 	@param AD_WF_Node_ID id
	 * 	@param AD_Client_ID for client
	 * 	@return true if last node
	 */
	public boolean isLast (int AD_WF_Node_ID, int AD_Client_ID)
	{
		MWFNode[] nodes = getNodesInOrder(AD_Client_ID);
		return AD_WF_Node_ID == nodes[nodes.length-1].getAD_WF_Node_ID();
	}	//	isLast

	
	/**************************************************************************
	 * 	Get Name
	 * 	@param translated translated
	 * 	@return Name
	 */
	public String getName(boolean translated)
	{
		if (translated && m_translated)
			return m_name_trl;
		return getName();
	}	//	getName

	/**
	 * 	Get Description
	 * 	@param translated translated
	 * 	@return Description
	 */
	public String getDescription (boolean translated)
	{
		if (translated && m_translated)
			return m_description_trl;
		return getDescription();
	}	//	getDescription

	/**
	 * 	Get Help
	 * 	@param translated translated
	 * 	@return Name
	 */
	public String getHelp (boolean translated)
	{
		if (translated && m_translated)
			return m_help_trl;
		return getHelp();
	}	//	getHelp

	/**
	 * 	String Representation
	 *	@return info
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer ("MWorkflow[");
		sb.append(get_ID()).append("-").append(getName())
			.append ("]");
		return sb.toString ();
	} //	toString
	
	/**************************************************************************
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		validate();
		return true;
	}	//	beforeSave
	
	/**
	 *  After Save.
	 *  @param newRecord new record
	 *  @param success success
	 *  @return true if save complete (if not overwritten true)
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		log.fine("Success=" + success);
		if (!success)
		{
			return false;
		}
		if (newRecord)
		{
			//	save all nodes -- Creating new Workflow
			MWFNode[] nodes = getNodesInOrder(0);
			for (int i = 0; i < nodes.length; i++)
			{
				nodes[i].saveEx(get_TrxName());
			}
		}
		
		if (newRecord)
		{
			int AD_Role_ID = Env.getAD_Role_ID(getCtx());
			MWorkflowAccess wa = new MWorkflowAccess(this, AD_Role_ID);
			wa.saveEx();
		}
		//	Menu/Workflow
		else if (is_ValueChanged("IsActive") || is_ValueChanged(COLUMNNAME_Name) 
			|| is_ValueChanged(COLUMNNAME_Description) || is_ValueChanged(COLUMNNAME_Help))
		{
			MMenu[] menues = MMenu.get(getCtx(), "AD_Workflow_ID=" + getAD_Workflow_ID(), get_TrxName());
			for (int i = 0; i < menues.length; i++)
			{
				menues[i].setIsActive(isActive());
				menues[i].setName(getName());
				menues[i].setDescription(getDescription());
				menues[i].saveEx();
			}
// TODO: teo_sarca: why do we need to sync node name with workflow name? - see BF 2665963
//			X_AD_WF_Node[] nodes = MWindow.getWFNodes(getCtx(), "AD_Workflow_ID=" + getAD_Workflow_ID(), get_TrxName());
//			for (int i = 0; i < nodes.length; i++)
//			{
//				boolean changed = false;
//				if (nodes[i].isActive() != isActive())
//				{
//					nodes[i].setIsActive(isActive());
//					changed = true;
//				}
//				if (nodes[i].isCentrallyMaintained())
//				{
//					nodes[i].setName(getName());
//					nodes[i].setDescription(getDescription());
//					nodes[i].setHelp(getHelp());
//					changed = true;
//				}
//				if (changed)
//					nodes[i].saveEx();
//			}
		}

		return success;
	}   //  afterSave

	/**************************************************************************
	 * 	Start Workflow.
	 * 	@param pi Process Info (Record_ID)
	 *  @deprecated
	 *	@return process
	 */
	public MWFProcess start (ProcessInfo pi)
	{
		return start(pi, null);
	}
	
	/**************************************************************************
	 * 	Start Workflow.
	 * 	@param pi Process Info (Record_ID)
	 *	@return process
	 */
	public MWFProcess start (ProcessInfo pi, String trxName)
	{
		MWFProcess workflowProcess = null;
		Trx localTrx = null;
		if (trxName == null)
			localTrx = Trx.get(Trx.createTrxName("WFP"), true);
		try
		{
			if (MWorkflow.WORKFLOWTYPE_DocumentProcess.equals(getWorkflowType()) && isLock(getAD_Table_ID(), pi.getRecord_ID()))
				throw new IllegalStateException(Msg.getMsg(getCtx() , "OtherProcessActive"));
			else if (MWorkflow.WORKFLOWTYPE_DocumentProcess.equals(getWorkflowType()))
				lock(getAD_Table_ID(),pi.getRecord_ID());

			workflowProcess = new MWFProcess (this, pi, trxName != null ? trxName : localTrx.getTrxName());
			workflowProcess.saveEx();
			pi.setSummary(Msg.getMsg(getCtx(), "Processing"));
			workflowProcess.startWork();
			if (localTrx != null)
				localTrx.commit(true);
			if (MWorkflow.WORKFLOWTYPE_DocumentProcess.equals(getWorkflowType()))
				unlock(getAD_Table_ID(),pi.getRecord_ID());
		}
		catch (Exception e)
		{
			if (localTrx != null)
				localTrx.rollback();
			log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			pi.setSummary(e.getMessage(), true);
			workflowProcess = null;
		}
		finally 
		{
			if (localTrx != null)
				localTrx.close();
		}
		return workflowProcess;
	}	//	MWFProcess

	/**
	 * Validate if a workflow is running for same Entity Data Id
	 * @param tableId
	 * @param recordId
	 * @return
	 */
	private boolean isLock(int tableId , int recordId)
	{
		MTable domain = MTable.get(getCtx() , tableId);
		String sqlProcessing = "SELECT Processing FROM " + domain.getTableName() +  " WHERE " +  domain.getKeyColumns()[0] + "=?";
		return "Y".equals(DB.getSQLValueStringEx(null , sqlProcessing, recordId));
	}

	/**
	 * Lock the entity data based on field processing set on for this document
	 * @param tableId
	 * @param recordId
	 */
	private void lock(int tableId , int recordId)
	{
		MTable domain = MTable.get(getCtx() , tableId);
		String update = "UPDATE "+ domain.getTableName() +" SET Processing='Y' WHERE (Processing='N' OR Processing IS NULL) AND " +  domain.getKeyColumns()[0]+ "=?";
		DB.executeUpdateEx(update, new Object[] {recordId}, null);
	}

	/**
	 * Unlock the entity data based on field processing set off for this document
	 * @param tableId
	 * @param recordId
	 */
	private void unlock(int tableId , int recordId)
	{
		MTable domain = MTable.get (getCtx(), tableId);
		String update = "UPDATE "+ domain.getTableName() +" SET Processing='N' WHERE " +  domain.getKeyColumns()[0]+ "=?";
		DB.executeUpdateEx(update, new Object[] {recordId}, null);
	}

	/**
	 * 	Start Workflow and Wait for completion.
	 * 	@param pi process info with Record_ID record for the workflow
	 *	@return process
	 */
	public MWFProcess startWait (ProcessInfo pi)
	{
		final int SLEEP = 500;		//	1/2 sec
		final int MAXLOOPS = 30;	//	15 sec	
		//
		MWFProcess process = start(pi, pi.getTransactionName());
		if (process == null)
			return null;
		Thread.yield();
		StateEngine state = process.getState();
		int loops = 0;
		while (!state.isClosed() && !state.isSuspended())
		{
			if (loops > MAXLOOPS)
			{
				log.warning("Timeout after sec " + ((SLEEP*MAXLOOPS)/1000));
				pi.setSummary(Msg.getMsg(getCtx(), "ProcessRunning"));
				pi.setIsTimeout(true);
				return process;
			}
		//	System.out.println("--------------- " + loops + ": " + state);
			try
			{
				Thread.sleep(SLEEP);
				loops++;
			}
			catch (InterruptedException e)
			{
				log.log(Level.SEVERE, "startWait interrupted", e);
				pi.setSummary("Interrupted");
				return process;
			}
			Thread.yield();
			state = process.getState();
		}
		String summary = process.getProcessMsg();
		if (summary == null || summary.trim().length() == 0)
			summary = state.toString();
		pi.setSummary(summary, state.isTerminated() || state.isAborted());
		log.fine(summary);
		return process;
	}	//	startWait
	
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

	
	/**************************************************************************
	 * 	Validate workflow.
	 * 	Sets Valid flag
	 *	@return errors or ""
	 */
	public String validate()
	{
		StringBuffer errors = new StringBuffer();
		//
		if (getAD_WF_Node_ID() == 0)
			errors.append(" - No Start Node");
		//
		if (WORKFLOWTYPE_DocumentValue.equals(getWorkflowType()) 
			&& (getDocValueLogic() == null || getDocValueLogic().length() == 0))
			errors.append(" - No Document Value Logic");
		//
		
		//
		if (getWorkflowType().equals(MWorkflow.WORKFLOWTYPE_Manufacturing))
		{
			this.setAD_Table_ID(0);
		}
			
		//	final
		boolean valid = errors.length() == 0;
		setIsValid(valid);
		if (!valid)
			log.info("validate: " + errors);
		return errors.toString();
	}	//	validate
	
	
	
	/**************************************************************************
	 * 	main
	 *	@param args
	 */
	public static void main (String[] args)
	{
		org.compiere.Adempiere.startup(true);

		//	Create Standard Document Process
		MWorkflow wf = new MWorkflow(Env.getCtx(), 0, null);
		wf.setValue ("Process_xx");
		wf.setName (wf.getValue());
		wf.setDescription("(Standard " + wf.getValue());
		wf.setEntityType (ENTITYTYPE_Dictionary);
		wf.saveEx();
		//
		MWFNode node10 = new MWFNode (wf, "10", "(Start)");
		node10.setDescription("(Standard Node)");
		node10.setEntityType (ENTITYTYPE_Dictionary);
		node10.setAction(MWFNode.ACTION_WaitSleep);
		node10.setWaitTime(0);
		node10.setPosition(5, 5);
		node10.saveEx();
		wf.setAD_WF_Node_ID(node10.getAD_WF_Node_ID());
		wf.saveEx();
		
		MWFNode node20 = new MWFNode (wf, "20", "(DocAuto)");
		node20.setDescription("(Standard Node)");
		node20.setEntityType (ENTITYTYPE_Dictionary);
		node20.setAction(MWFNode.ACTION_DocumentAction);
		node20.setDocAction(MWFNode.DOCACTION_None);
		node20.setPosition(5, 120);
		node20.saveEx();
		MWFNodeNext tr10_20 = new MWFNodeNext(node10, node20.getAD_WF_Node_ID());
		tr10_20.setEntityType (ENTITYTYPE_Dictionary);
		tr10_20.setDescription("(Standard Transition)");
		tr10_20.setSeqNo(100);
		tr10_20.saveEx();
		
		MWFNode node100 = new MWFNode (wf, "100", "(DocPrepare)");
		node100.setDescription("(Standard Node)");
		node100.setEntityType (ENTITYTYPE_Dictionary);
		node100.setAction(MWFNode.ACTION_DocumentAction);
		node100.setDocAction(MWFNode.DOCACTION_Prepare);
		node100.setPosition(170, 5);
		node100.saveEx();
		MWFNodeNext tr10_100 = new MWFNodeNext(node10, node100.getAD_WF_Node_ID());
		tr10_100.setEntityType (ENTITYTYPE_Dictionary);
		tr10_100.setDescription("(Standard Approval)");
		tr10_100.setIsStdUserWorkflow(true);
		tr10_100.setSeqNo(10);
		tr10_100.saveEx();
		
		MWFNode node200 = new MWFNode (wf, "200", "(DocComplete)");
		node200.setDescription("(Standard Node)");
		node200.setEntityType (ENTITYTYPE_Dictionary);
		node200.setAction(MWFNode.ACTION_DocumentAction);
		node200.setDocAction(MWFNode.DOCACTION_Complete);
		node200.setPosition(170, 120);
		node200.saveEx();
		MWFNodeNext tr100_200 = new MWFNodeNext(node100, node200.getAD_WF_Node_ID());
		tr100_200.setEntityType (ENTITYTYPE_Dictionary);
		tr100_200.setDescription("(Standard Transition)");
		tr100_200.setSeqNo(100);
		tr100_200.saveEx();
		
		
		/**
		Env.setContext(Env.getCtx(), "#AD_Client_ID ", "11");
		Env.setContext(Env.getCtx(), "#AD_Org_ID ", "11");
		Env.setContext(Env.getCtx(), "#AD_User_ID ", "100");
		//
		int AD_Workflow_ID = 115;			//	Requisition WF
		int M_Requsition_ID = 100;
		MRequisition req = new MRequisition (Env.getCtx(), M_Requsition_ID);
		req.setDocStatus(DocAction.DOCSTATUS_Drafted);
		req.saveEx();
		Log.setTraceLevel(8);
		System.out.println("---------------------------------------------------");
		MWorkflow wf = MWorkflow.get (Env.getCtx(), AD_Workflow_ID);
		**/
	//	wf.start(M_Requsition_ID);
		
	}	//	main
	
	/**
	 * Get AD_Workflow_ID for given M_Product_ID
	 * @param M_Product_ID
	 * @return AD_Workflow_ID
	 */
	public static int getWorkflowSearchKey(MProduct product)
	{
		int AD_Client_ID = Env.getAD_Client_ID(product.getCtx());
		String sql = "SELECT AD_Workflow_ID FROM AD_Workflow "
						+" WHERE Value = ? AND AD_Client_ID = ?";
		return DB.getSQLValueEx(null, sql, product.getValue(), AD_Client_ID);
	}

	/**
	 * Check if the workflow is valid for given date
	 * @param date
	 * @return true if valid
	 */
	public boolean isValidFromTo(Timestamp date)
	{
		Timestamp validFrom = getValidFrom();
		Timestamp validTo = getValidTo();
		
		if (validFrom != null && date.before(validFrom))
			return false;
		if (validTo != null && date.after(validTo))
			return false;
		return true;
	}
}	//	MWorkflow_ID
