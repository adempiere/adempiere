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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MResource;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.wf.MWFNode;

/**
 * PP Order Workflow Node Model
 *
 * @author Victor Perez, e-Evolution, S.C.
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
	
	
	/**
	 * @return true if this is last node
	 */
	public static boolean isLastNode(Properties ctx, int PP_Order_Node_ID, String trxName)
	{
		String whereClause = MPPOrderNodeNext.COLUMNNAME_PP_Order_Node_ID+"=?";
		return false == new Query(ctx, MPPOrderNodeNext.Table_Name, whereClause, trxName)
									.setOnlyActiveRecords(true)
									.setParameters(new Object[]{PP_Order_Node_ID})
									.match();
	}

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
			setDocStatus(MPPOrderNode.DOCSTATUS_Drafted);
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
		setIsSubcontracting(wfNode.isSubcontracting());
		setC_BPartner_ID(wfNode.getC_BPartner_ID());
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
		//
		setQtyOrdered(qtyOrdered);
		setDocStatus(MPPOrderNode.DOCSTATUS_Drafted);
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
	/** Duration Base MS		*/
	private long			m_durationBaseMS = -1;

	/**
	 * 	Load Next
	 */
	private void loadNext()
	{
		boolean splitAnd = SPLITELEMENT_AND.equals(getSplitElement());
		String whereClause = MPPOrderNodeNext.COLUMNNAME_PP_Order_Node_ID+"=?";
		m_next = new Query(getCtx(), MPPOrderNodeNext.Table_Name, whereClause, get_TrxName())
											.setParameters(new Object[]{get_ID()})
											.setOnlyActiveRecords(true)
											.setOrderBy(MPPOrderNodeNext.COLUMNNAME_SeqNo)
											.list();
		for (MPPOrderNodeNext next : m_next)
		{
			next.setFromSplitAnd(splitAnd);
		}
		log.fine("#" + m_next.size());
	}	//	loadNext
	
	/**
	 * Set Qty Required and DurationRequired (Duration * qtyOrdered) 
	 * @param qtyOrdered
	 */
	public void setQtyOrdered(BigDecimal qtyOrdered)
	{
		setQtyRequiered(qtyOrdered);
		BigDecimal time = BigDecimal.valueOf(getDuration()).multiply(qtyOrdered);
		setDurationRequiered(time.intValue());
	}

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
		for (MPPOrderNodeNext next : m_next)
		{
			if (next.getAD_Client_ID() == 0 || next.getAD_Client_ID() == AD_Client_ID)
			{
				list.add(next);
			}
		}
		return list.toArray(new MPPOrderNodeNext [list.size()]);
	}	//	getNextNodes
	
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
			m_durationBaseMS = getPPOrderWorkflow().getDurationBaseSec() * 1000;
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
			m_durationBaseMS = getPPOrderWorkflow().getDurationBaseSec() * 1000;
		return limit * m_durationBaseMS;
	}	//	getLimitMS
	
	/**
	 * 	Get Duration CalendarField
	 *	@return Calendar.MINUTE, etc.
	 */
	public int getDurationCalendarField()
	{
		return getPPOrderWorkflow().getDurationCalendarField();
	}	//	getDurationCalendarField

	/**
	 * 	Get Workflow (NoTrx)
	 *	@return workflow
	 */
	public MPPOrderWorkflow getPPOrderWorkflow()
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
			.append ("]");
		return sb.toString ();
	}	//	toString
	
	/**
	 * Calculate the cost for this Activity using the Cost Element Type
	 * @param CostElementType Cost Element Type (Labor or Burden)
	 * @param C_AcctSchema_ID AcctSchema
	 * @param M_CostType_ID Cost Type
	 * @param AD_Org_ID Organization
	 * @param setuptime Setup Time
	 * @param duration Duration
	 * @return cost for this Cost Element Type (Labor or Burden)
	 * @throws Exception when the UOM do not is Hours
	 */
	public BigDecimal getCostForCostElementType(String CostElementType, int C_AcctSchema_ID,int M_CostType_ID,int AD_Org_ID,int setuptime, int duration)
	{
		MResource resource = (MResource) getS_Resource();
		//get the rate for this cost type element (Rsource, Burden)
		MPPOrderWorkflow workflow = getPPOrderWorkflow();
		double rate = resource.getResouceRate(C_AcctSchema_ID, M_CostType_ID,CostElementType, AD_Org_ID);
		BigDecimal cost =  Env.ZERO;
		if (rate == 0)
		{
			return Env.ZERO;
		}
		
		int C_UOM_ID = DB.getSQLValueEx(get_TrxName(),"SELECT C_UOM_ID FROM M_Product WHERE S_Resource_ID = ? " , getS_Resource_ID());
		MUOM uom = MUOM.get(getCtx(), C_UOM_ID);
		if (uom.isHour())
		{
			double hours = (setuptime / workflow.getQtyBatchSize().doubleValue() + duration)
			* workflow.getDurationBaseSec() / 3600; 
			double nodeCost = rate * hours;
			cost = cost.add(new BigDecimal(nodeCost));
			log.info("Activity: " + getName() + " Resouce: " + resource.getName() +" CostElementType: " + CostElementType +  " Time Base: "+workflow.getDurationUnit() +" Duration (H): " + hours +  " Rate: " + rate + " Activity Cost: " +  nodeCost +" =>Cost: "+cost);
		}
		else
		{
			throw new AdempiereException("@NotSupported@ @C_UOM_ID@="+uom.getName());
		}
		return cost;
	}
}
