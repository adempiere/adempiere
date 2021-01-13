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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.compiere.util.CCache;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MProjectMember;
import org.eevolution.model.X_I_Project;

/**
 * 	Project Model
 *
 *	@author Jorg Janke
 *  @author Víctor Pérez Juárez , victor.perez@e-evolution.com , http://www.e-evolution.com
 *  <a href="https://github.com/adempiere/adempiere/issues/1478">
 *  <li>Add support to create request based on Standard Request Type setting on Project Type #1478
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
	 * @param trxName
	 * @return
	 */
	public static MProject getById(Properties ctx, Integer projectId, String trxName) {
		if (projectId <= 0)
			return null;
		if (projectCacheIds.size() == 0)
			getAll(ctx, true, trxName);

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
	 * @param trxName
	 * @return
	 */
	public static MProject getByValue(Properties ctx, String value, String trxName) {
		if (value == null)
			return null;
		if (projectCacheValues.size() == 0)
			getAll(ctx, true, trxName);

		int clientId = Env.getAD_Client_ID(ctx);
		String key = clientId + "#" + value;
		MProject project = projectCacheValues.get(key);
		if (project != null && project.get_ID() > 0)
			return project;

		project = new Query(ctx, Table_Name, COLUMNNAME_Value + "=?", trxName)
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
	 * @param trxName
	 * @return
	 */
	public static List<MProject> getAll(Properties ctx, boolean resetCache, String trxName) {
		List<MProject> projectList;
		if (resetCache || projectCacheIds.size() > 0) {
			projectList = new Query(Env.getCtx(), Table_Name, null, trxName)
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

	public MProject (X_I_Project projectImport)
	{
		super(projectImport.getCtx() , 0 , projectImport.get_TrxName());
		setAD_Org_ID(projectImport.getAD_Org_ID());
		setM_PriceList_Version_ID(projectImport.getM_PriceList_Version_ID());
		setAD_Color_ID(projectImport.getAD_Color_ID());
		setAD_OrgTrx_ID(projectImport.getAD_OrgTrx_ID());
		setAD_User_ID(projectImport.getAD_User_ID());
		setC_Activity_ID(projectImport.getC_Activity_ID());
		setC_BPartner_ID(projectImport.getC_BPartner_ID());
		setC_BPartner_Location_ID(projectImport.getC_BPartner_Location_ID());
		setC_BPartnerSR_ID(projectImport.getC_BPartnerSR_ID());
		setC_Campaign_ID(projectImport.getC_Campaign_ID());
		setC_Currency_ID(projectImport.getC_Currency_ID());
		setC_PaymentTerm_ID(projectImport.getC_PaymentTerm_ID());
		setC_PaymentTerm_ID(projectImport.getC_PaymentTerm_ID());
		setC_ProjectCategory_ID(projectImport.getC_ProjectCategory_ID());
		setC_ProjectClass_ID(projectImport.getC_ProjectClass_ID());
		setC_ProjectGroup_ID(projectImport.getC_ProjectGroup_ID());
		setC_ProjectStatus_ID(projectImport.getC_ProjectStatus_ID());
		setC_SalesRegion_ID(projectImport.getC_SalesRegion_ID());
		setCommittedAmt(projectImport.getCommittedAmt());
		setCommittedQty(projectImport.getCommittedQty());
		setDateContract(projectImport.getDateContract());
		setDateDeadline(projectImport.getDateDeadline());
		setDateFinish(projectImport.getDateFinish());
		setDateStart(projectImport.getDateStart());
		setDateFinishSchedule(projectImport.getDateFinishSchedule());
		setDateStartSchedule(projectImport.getDateStartSchedule());
		setDescription(projectImport.getDescription());
		setDurationUnit(projectImport.getDurationUnit());
		setInvoicedAmt(projectImport.getInvoicedAmt());
		setInvoicedQty(projectImport.getInvoicedQty());
		setIsCommitCeiling(projectImport.isCommitCeiling());
		setIsCommitment(isCommitment());
		setIsIndefinite(projectImport.isIndefinite());
		setIsSummary(projectImport.isSummary());
		setM_Warehouse_ID(projectImport.getM_Warehouse_ID());
		setName(projectImport.getName());
		setNote(projectImport.getNote());
		setPlannedAmt(projectImport.getPlannedAmt());
		setPlannedMarginAmt(projectImport.getPlannedMarginAmt());
		setPlannedQty(projectImport.getPlannedQty());
		setPOReference(projectImport.getPOReference());
		setPriorityRule(projectImport.getPriorityRule());
		setProjectBalanceAmt(projectImport.getProjectBalanceAmt());
		setProjectLineLevel(projectImport.getProjectLineLevel());
		setProjectManager_ID(projectImport.getProjectManager_ID());
		setProjInvoiceRule(projectImport.getProjInvoiceRule());
		setSalesRep_ID(projectImport.getSalesRep_ID());
		setUser1_ID(projectImport.getUser1_ID());
		setUser2_ID(projectImport.getUser2_ID());
		setUser3_ID(projectImport.getUser3_ID());
		setUser4_ID(projectImport.getUser4_ID());
		setValue(projectImport.getValue());
	}


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
	public List<MProjectLine> getLines()
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		final String whereClause = "C_Project_ID=?";
		return new Query(getCtx(), I_C_ProjectLine.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_Project_ID())
			.setOrderBy("Line")
			.list();
	}	//	getLines

	/**
	 * 	Get Project Issues
	 *	@return Array of issues
	 */
	public List<MProjectIssue> getIssues()
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = "C_Project_ID=?";
		return new Query(getCtx(), I_C_ProjectIssue.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_Project_ID())
			.setOrderBy("Line")
			.list();
	}	//	getIssues

	/**
	 * 	Get Project Phases
	 *	@return Array of phases
	 */
	public List<MProjectPhase> getPhases()
	{
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		String whereClause = "C_Project_ID=?";
		return new Query(getCtx(), I_C_ProjectPhase.Table_Name, whereClause, get_TrxName())
			.setParameters(getC_Project_ID())
			.setOrderBy("SeqNo")
			.list();
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
		AtomicInteger count = new AtomicInteger(0);
		List<MProjectLine> fromProjectLines = project.getLines();
		fromProjectLines.stream()
				.filter(fromProjectLine ->
						fromProjectLine.getC_ProjectPhase_ID() <= 0
					 || fromProjectLine.getC_ProjectTask_ID() <= 0)
				.forEach(fromProjectLine -> {
					MProjectLine toProjectLine = new MProjectLine(getCtx(), 0, project.get_TrxName());
					PO.copyValues(fromProjectLine, toProjectLine, getAD_Client_ID(), getAD_Org_ID());
					toProjectLine.setC_Project_ID(getC_Project_ID());
					toProjectLine.setInvoicedAmt(Env.ZERO);
					toProjectLine.setInvoicedQty(Env.ZERO);
					toProjectLine.setC_OrderPO_ID(0);
					toProjectLine.setC_Order_ID(0);
					toProjectLine.setProcessed(false);
					toProjectLine.saveEx();
					count.getAndUpdate(no -> no + 1);
				});

		if (fromProjectLines.size() != count.get())
			log.log(Level.SEVERE, "Lines difference - Project=" + fromProjectLines.size() + " <> Saved=" + count);
		return count.get();
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
		AtomicInteger count = new AtomicInteger(0);
		AtomicInteger taskCount = new AtomicInteger(0);
		AtomicInteger lineCount = new AtomicInteger(0);
		//	Get Phases
		List<MProjectPhase> toPhases = getPhases();
		List<MProjectPhase> fromPhases = fromProject.getPhases();
		fromPhases.stream()
				.forEach(fromPhase -> {
					//	Check if Phase already exists
					Boolean exists = toPhases.stream().anyMatch(toPhase -> toPhase.getC_Phase_ID() == fromPhase.getC_Phase_ID());
					//	Phase exist
					if (exists)
						log.info("Phase already exists here, ignored - " + fromPhase);
					else {
						MProjectPhase toPhase = new MProjectPhase(getCtx(), 0, get_TrxName());
						PO.copyValues(fromPhase, toPhase, getAD_Client_ID(), getAD_Org_ID());
						toPhase.setC_Project_ID(getC_Project_ID());
						toPhase.setC_Order_ID(0);
						toPhase.setIsComplete(false);
						toPhase.saveEx();
						count.getAndUpdate(no -> no + 1);
						taskCount.getAndUpdate(taskNo -> taskNo + toPhase.copyTasksFrom(fromPhase));
						lineCount.getAndUpdate(lineNo -> lineNo + toPhase.copyLinesFrom(fromPhase));
					}
				});
		if (fromPhases.size() != count.get())
			log.warning("Count difference - Project=" + fromPhases.size() + " <> Saved=" + count.get());

		return count.get() + taskCount.get() + lineCount.get();
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
		setC_ProjectType_ID(Integer.toString(type.getC_ProjectType_ID()));
		setProjectCategory(type.getProjectCategory());
		createRequest(type);
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
		AtomicInteger count = new AtomicInteger(0);
		AtomicInteger taskCount = new AtomicInteger(0);
		List<MProjectTypePhase> typePhases = type.getPhases();
		typePhases.stream()
				.forEach(fromPhase -> {
					MProjectPhase toPhase = new MProjectPhase(this, fromPhase);
					toPhase.setC_Project_ID(getC_Project_ID());
					toPhase.setProjInvoiceRule(getProjInvoiceRule());
					toPhase.saveEx();
					count.getAndUpdate(no -> no + 1);
					taskCount.getAndUpdate(no -> no + toPhase.copyTasksFrom(fromPhase));
				});
		log.fine("#" + count.get() + "/" + taskCount.get()
			+ " - " + type);
		if (typePhases.size() != count.get())
			log.log(Level.SEVERE, "Count difference - Type=" + typePhases.size() + " <> Saved=" + count.get());
		return count.get();
	}	//	copyPhasesFrom


	/**
	 * create Request Project
	 */
	public void createRequest(MProjectType projectType)
	{
		if (projectType.getR_StandardRequestType_ID() > 0)
		{
			MStandardRequestType standardRequestType = (MStandardRequestType) projectType.getR_StandardRequestType();
			List<MRequest> requests =  standardRequestType.createStandardRequest(this);
			requests.stream().forEach(request -> {
				request.setC_Project_ID(getC_Project_ID());
				request.setDateStartPlan(getDateStartSchedule());
				request.setDateCompletePlan(getDateFinishSchedule());
				request.saveEx();
			});
		}
	}

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
		}

		//	Value/Name change
		if (success && !newRecord 
			&& (is_ValueChanged("Value") || is_ValueChanged("Name")))
			MAccount.updateValueDescription(getCtx(), "C_Project_ID=" + getC_Project_ID(), get_TrxName());

		if (getSalesRep_ID() > 0 && !MProjectMember.memberExists(this, getSalesRep_ID()))
			MProjectMember.addMember(this, getSalesRep_ID());
		if (getProjectManager_ID() > 0 && !MProjectMember.memberExists(this, getProjectManager_ID()))
			MProjectMember.addMember(this, getProjectManager_ID());
		if (getAD_User_ID() > 0 && !MProjectMember.memberExists(this, getAD_User_ID()))
			MProjectMember.addMember(this, getAD_User_ID());

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
