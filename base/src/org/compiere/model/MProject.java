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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * 	Project Model
 *
 *	@author Jorg Janke
 *	@version $Id: MProject.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 */
public class MProject extends X_C_Project
{
	private static CCache<Integer, MProject> projectCacheIds = new CCache<Integer, MProject>(Table_Name, 100, 0);
	private static CCache<String, MProject> projectCacheValues = new CCache<String, MProject>(Table_Name, 100, 0);

	/**
	 * Ge project by Id
	 * @param ctx
	 * @param projectId
	 * @return
	 */
	public static MProject getById(Properties ctx, Integer projectId) {
		if (projectId <= 0)
			return null;
		if (projectCacheIds.size() == 0)
			getAll(ctx, true);

		MProject project = projectCacheIds.get(projectId);
		if (project != null)
			return project;

		project =  new Query(ctx , Table_Name , COLUMNNAME_C_Project_ID + "=?" , null)
				.setClient_ID()
				.setParameters(projectId)
				.first();

		if (project != null && project.get_ID() > 0) {
			int clientId = Env.getAD_Client_ID(ctx);
			String key = clientId + "#" + project.getValue();
			projectCacheIds.put(project.get_ID(), project);
			projectCacheValues.put(key, project);
		}
		return project;
	}

	/**
	 * Get project by Search Key
	 * @param ctx
	 * @param value
	 * @return
	 */
	public static MProject getByValue(Properties ctx, String value) {
		if (value == null)
			return null;
		if (projectCacheValues.size() == 0)
			getAll(ctx, true);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + value;
		MProject project = projectCacheValues.get(key);
		if (project != null && project.get_ID() > 0)
			return project;

		project = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", null)
				.setClient_ID()
				.setParameters(value)
				.first();
		if (project != null && project.get_ID() > 0) {
			projectCacheValues.put(key, project);
			projectCacheIds.put(project.get_ID(), project);
		}
		return project;
	}

	/**
	 * Get all project and create cache
	 * @param ctx
	 * @param resetCache
	 * @return
	 */
	public static List<MProject> getAll(Properties ctx, boolean resetCache) {
		List<MProject> projectList;
		if (resetCache || projectCacheIds.size() > 0) {
			projectList = new Query(Env.getCtx(), Table_Name, null, null)
					.setClient_ID()
					.setOrderBy(COLUMNNAME_Name)
					.list();
			projectList.stream().forEach(project -> {
				int clientId = Env.getAD_Client_ID(ctx);
				String key = clientId + "#" + project.getValue();
				projectCacheIds.put(project.getC_Project_ID(), project);
				projectCacheValues.put(key, project);
			});
			return projectList;
		}
		projectList = projectCacheIds.entrySet().stream()
				.map(project -> project.getValue())
				.collect(Collectors.toList());
		return projectList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151648902207548617L;

	/**
	 * 	Create new Project by copying
	 * 	@param ctx context
	 *	@param C_Project_ID project
	 * 	@param dateDoc date of the document date
	 *	@param trxName transaction
	 *	@return Project
	 */
	public static MProject copyFrom (Properties ctx, int C_Project_ID, Timestamp dateDoc, String trxName)
	{
		MProject from = new MProject (ctx, C_Project_ID, trxName);
		if (from.getC_Project_ID() == 0)
			throw new IllegalArgumentException ("From Project not found C_Project_ID=" + C_Project_ID);
		//
		MProject to = new MProject (ctx, 0, trxName);
		PO.copyValues(from, to, from.getAD_Client_ID(), from.getAD_Org_ID());
		to.set_ValueNoCheck ("C_Project_ID", I_ZERO);
		//	Set Value with Time
		String Value = to.getValue() + " ";
		String Time = dateDoc.toString();
		int length = Value.length() + Time.length();
		if (length <= 40)
			Value += Time;
		else
			Value += Time.substring (length-40);
		to.setValue(Value);
		to.setInvoicedAmt(Env.ZERO);
		to.setProjectBalanceAmt(Env.ZERO);
		to.setProcessed(false);
		//
		if (!to.save())
			throw new IllegalStateException("Could not create Project");

		if (to.copyDetailsFrom(from) == 0)
			throw new IllegalStateException("Could not create Project Details");

		return to;
	}	//	copyFrom

	
	/**************************************************************************
	 * 	Standard Constructor
	 *	@param ctx context
	 *	@param C_Project_ID id
	 *	@param trxName transaction
	 */
	public MProject (Properties ctx, int C_Project_ID, String trxName)
	{
		super (ctx, C_Project_ID, trxName);
		if (C_Project_ID == 0)
		{
		//	setC_Project_ID(0);
		//	setValue (null);
		//	setC_Currency_ID (0);
			setCommittedAmt (Env.ZERO);
			setCommittedQty (Env.ZERO);
			setInvoicedAmt (Env.ZERO);
			setInvoicedQty (Env.ZERO);
			setPlannedAmt (Env.ZERO);
			setPlannedMarginAmt (Env.ZERO);
			setPlannedQty (Env.ZERO);
			setProjectBalanceAmt (Env.ZERO);
		//	setProjectCategory(PROJECTCATEGORY_General);
			setProjInvoiceRule(PROJINVOICERULE_None);
			setProjectLineLevel(PROJECTLINELEVEL_Project);
			setIsCommitCeiling (false);
			setIsCommitment (false);
			setIsSummary (false);
			setProcessed (false);
		}
	}	//	MProject

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MProject (Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}	//	MProject

	/**	Cached PL			*/
	private int		m_M_PriceList_ID = 0;

	/**
	 * 	Get Project Type as Int (is Button).
	 *	@return C_ProjectType_ID id
	 */
	public int getC_ProjectType_ID_Int()
	{
		String pj = super.getC_ProjectType_ID();
		if (pj == null)
			return 0;
		int C_ProjectType_ID = 0;
		try
		{
			C_ProjectType_ID = Integer.parseInt (pj);
		}
		catch (Exception ex)
		{
			log.log(Level.SEVERE, pj, ex);
		}
		return C_ProjectType_ID;
	}	//	getC_ProjectType_ID_Int

	/**
	 * 	Set Project Type (overwrite r/o)
	 *	@param C_ProjectType_ID id
	 */
	public void setC_ProjectType_ID (int C_ProjectType_ID)
	{
		if (C_ProjectType_ID == 0)
			super.setC_ProjectType_ID (null);
		else
			super.set_Value("C_ProjectType_ID", C_ProjectType_ID);
	}	//	setC_ProjectType_ID

	/**
	 *	String Representation
	 * 	@return info
	 */
	public String toString()
	{
		StringBuffer sb = new StringBuffer ("MProject[").append(get_ID())
			.append("-").append(getValue()).append(",ProjectCategory=").append(getProjectCategory())
			.append("]");
		return sb.toString();
	}	//	toString

	/**
	 * 	Get Price List from Price List Version
	 *	@return price list or 0
	 */
	public int getM_PriceList_ID()
	{
		if (getM_PriceList_Version_ID() == 0)
			return 0;
		if (m_M_PriceList_ID > 0)
			return m_M_PriceList_ID;
		//
		String sql = "SELECT M_PriceList_ID FROM M_PriceList_Version WHERE M_PriceList_Version_ID=?";
		m_M_PriceList_ID = DB.getSQLValue(null, sql, getM_PriceList_Version_ID());
		return m_M_PriceList_ID;
	}	//	getM_PriceList_ID

	/**
	 * 	Set PL Version
	 *	@param M_PriceList_Version_ID id
	 */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		super.setM_PriceList_Version_ID(M_PriceList_Version_ID);
		m_M_PriceList_ID = 0;	//	reset
	}	//	setM_PriceList_Version_ID


	/**************************************************************************
	 * 	Get Project Lines
	 *	@return Array of lines
	 */
	public MProjectLine[] getLines()
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "C_Project_ID=?";
		List <MProjectLine> list = new Query(getCtx(), I_C_ProjectLine.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_Project_ID())
			.setOrderBy("Line")
			.list();
		//
		MProjectLine[] retValue = new MProjectLine[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getLines

	/**
	 * 	Get Project Issues
	 *	@return Array of issues
	 */
	public MProjectIssue[] getIssues()
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = "C_Project_ID=?";
		List <MProjectIssue> list = new Query(getCtx(), I_C_ProjectIssue.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_Project_ID())
			.setOrderBy("Line")
			.list();
		//
		MProjectIssue[] retValue = new MProjectIssue[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getIssues

	/**
	 * 	Get Project Phases
	 *	@return Array of phases
	 */
	public MProjectPhase[] getPhases()
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = "C_Project_ID=?";
		List <MProjectPhase> list = new Query(getCtx(), I_C_ProjectPhase.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_Project_ID())
			.setOrderBy("SeqNo")
			.list();
		//
		MProjectPhase[] retValue = new MProjectPhase[list.size()];
		list.toArray(retValue);
		return retValue;
	}	//	getPhases

	
	/**************************************************************************
	 * 	Copy Lines/Phase/Task from other Project
	 *	@param project project
	 *	@return number of total lines copied
	 */
	public int copyDetailsFrom (MProject project)
	{
		if (isProcessed() || project == null)
			return 0;
		int count = copyLinesFrom(project)
			+ copyPhasesFrom(project);
		return count;
	}	//	copyDetailsFrom

	/**
	 * 	Copy Lines From other Project
	 *	@param project project
	 *	@return number of lines copied
	 */
	public int copyLinesFrom (MProject project)
	{
		if (isProcessed() || project == null)
			return 0;
		int count = 0;
		MProjectLine[] fromLines = project.getLines();
		for (int i = 0; i < fromLines.length; i++)
		{
			//BF 3067850 - monhate
			if((fromLines[i].getC_ProjectPhase_ID() != 0)||
			   (fromLines[i].getC_ProjectTask_ID() != 0)) continue;
			
			MProjectLine line = new MProjectLine (getCtx(), 0, project.get_TrxName());
			PO.copyValues(fromLines[i], line, getAD_Client_ID(), getAD_Org_ID());
			line.setC_Project_ID(getC_Project_ID());
			line.setInvoicedAmt(Env.ZERO);
			line.setInvoicedQty(Env.ZERO);
			line.setC_OrderPO_ID(0);
			line.setC_Order_ID(0);
			line.setProcessed(false);
			if (line.save())
				count++;
		}
		if (fromLines.length != count)
			log.log(Level.SEVERE, "Lines difference - Project=" + fromLines.length + " <> Saved=" + count);

		return count;
	}	//	copyLinesFrom

	/**
	 * 	Copy Phases/Tasks from other Project
	 *	@param fromProject project
	 *	@return number of items copied
	 */
	public int copyPhasesFrom (MProject fromProject)
	{
		if (isProcessed() || fromProject == null)
			return 0;
		int count = 0;
		int taskCount = 0, lineCount = 0;
		//	Get Phases
		MProjectPhase[] myPhases = getPhases();
		MProjectPhase[] fromPhases = fromProject.getPhases();
		//	Copy Phases
		for (int i = 0; i < fromPhases.length; i++)
		{
			//	Check if Phase already exists
			int C_Phase_ID = fromPhases[i].getC_Phase_ID();
			boolean exists = false;
			if (C_Phase_ID == 0)
				exists = false;
			else
			{
				for (int ii = 0; ii < myPhases.length; ii++)
				{
					if (myPhases[ii].getC_Phase_ID() == C_Phase_ID)
					{
						exists = true;
						break;
					}
				}
			}
			//	Phase exist
			if (exists)
				log.info("Phase already exists here, ignored - " + fromPhases[i]);
			else
			{
				MProjectPhase toPhase = new MProjectPhase (getCtx (), 0, get_TrxName());
				PO.copyValues (fromPhases[i], toPhase, getAD_Client_ID (), getAD_Org_ID ());
				toPhase.setC_Project_ID (getC_Project_ID ());
				toPhase.setC_Order_ID (0);
				toPhase.setIsComplete (false);
				if (toPhase.save ())
				{
					count++;
					taskCount += toPhase.copyTasksFrom (fromPhases[i]);
					//BF 3067850 - monhate
					lineCount += toPhase.copyLinesFrom(fromPhases[i]);
				}
			}
		}
		if (fromPhases.length != count)
			log.warning("Count difference - Project=" + fromPhases.length + " <> Saved=" + count);

		return count + taskCount + lineCount;
	}	//	copyPhasesFrom


	/**
	 *	Set Project Type and Category.
	 * 	If Service Project copy Projet Type Phase/Tasks
	 *	@param type project type
	 */
	public void setProjectType (MProjectType type)
	{
		if (type == null)
			return;
		setC_ProjectType_ID(type.getC_ProjectType_ID());
		setProjectCategory(type.getProjectCategory());
		if (PROJECTCATEGORY_ServiceChargeProject.equals(getProjectCategory()))
			copyPhasesFrom(type);
	}	//	setProjectType

	/**
	 *	Copy Phases from Type
	 *	@param type Project Type
	 *	@return count
	 */
	public int copyPhasesFrom (MProjectType type)
	{
		//	create phases
		int count = 0;
		int taskCount = 0;
		MProjectTypePhase[] typePhases = type.getPhases();
		for (int i = 0; i < typePhases.length; i++)
		{
			MProjectPhase toPhase = new MProjectPhase (this, typePhases[i]);
			if (toPhase.save())
			{
				count++;
				taskCount += toPhase.copyTasksFrom(typePhases[i]);
			}
		}
		log.fine("#" + count + "/" + taskCount 
			+ " - " + type);
		if (typePhases.length != count)
			log.log(Level.SEVERE, "Count difference - Type=" + typePhases.length + " <> Saved=" + count);
		return count;
	}	//	copyPhasesFrom

	/**
	 * 	Before Save
	 *	@param newRecord new
	 *	@return true
	 */
	protected boolean beforeSave (boolean newRecord)
	{
		if (getAD_User_ID() == -1)	//	Summary Project in Dimensions
			setAD_User_ID(0);
		
		//	Set Currency
		if (is_ValueChanged("M_PriceList_Version_ID") && getM_PriceList_Version_ID() != 0)
		{
			MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID(), null);
			if (pl != null && pl.get_ID() != 0)
				setC_Currency_ID(pl.getC_Currency_ID());
		}
		
		return true;
	}	//	beforeSave
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
		{
			insert_Accounting("C_Project_Acct", "C_AcctSchema_Default", null);
			insert_Tree(MTree_Base.TREETYPE_Project);
		}

		//	Value/Name change
		if (success && !newRecord 
			&& (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "C_Project_ID=" + getC_Project_ID(), get_TrxName());

		return success;
	}	//	afterSave

	/**
	 * 	Before Delete
	 *	@return true
	 */
	protected boolean beforeDelete ()
	{
		return delete_Accounting("C_Project_Acct"); 
	}	//	beforeDelete

	/**
	 * 	After Delete
	 *	@param success
	 *	@return deleted
	 */
	protected boolean afterDelete (boolean success)
	{
		if (success)
			delete_Tree(MTree_Base.TREETYPE_Project);
		return success;
	}	//	afterDelete
	
	/**
	 * 	Return the Invoices Generated for this Project
	 *	@return invoices
	 *	@author monhate
	 */	
	public MInvoice[] getMInvoices(){
		StringBuilder sb = new StringBuilder();
		sb.append(MInvoice.COLUMNNAME_C_Project_ID).append("=?");
		Query qry = new Query(getCtx(), MInvoice.Table_Name, sb.toString(), get_TrxName());
		qry.setParameters(getC_Project_ID());		
		return (MInvoice[]) qry.list().toArray();
	}

}	//	MProject
