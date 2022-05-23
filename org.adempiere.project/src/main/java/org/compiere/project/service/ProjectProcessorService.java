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
package org.compiere.project.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Lookup;
import org.compiere.model.MColumn;
import org.compiere.model.MProject;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectProcessorChange;
import org.compiere.model.MProjectProcessorQueued;
import org.compiere.model.MProjectTask;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.model.POInfo;
import org.compiere.model.Query;
import org.compiere.util.DisplayType;
import org.eevolution.model.MProjectMember;
import org.eevolution.model.MProjectProcessor;
import org.eevolution.model.MProjectProcessorLog;

/**
 * Project Processor Utils
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class ProjectProcessorService {

	/** PO Info*/
	private static POInfo p_info = null;
	
	public static String[] LISTEN_COLUMNS = new String [] {MProject.COLUMNNAME_ProjectManager_ID,
															MProject.COLUMNNAME_Name,
															MProject.COLUMNNAME_Description,
															MProject.COLUMNNAME_Note,
															MProject.COLUMNNAME_C_ProjectStatus_ID,
															MProject.COLUMNNAME_DateLastAction,
															MProject.COLUMNNAME_PriorityRule,
															MProject.COLUMNNAME_DateStartSchedule,
															MProject.COLUMNNAME_DateFinishSchedule,
															MProject.COLUMNNAME_DateStart,
															MProject.COLUMNNAME_DateFinish,
															MProject.COLUMNNAME_DateDeadline,
															MProject.COLUMNNAME_DateContract,
															MProject.COLUMNNAME_IsIndefinite,
															MProject.COLUMNNAME_IsActive,
															MProject.COLUMNNAME_DateLastAlert,
															MProject.COLUMNNAME_M_PriceList_Version_ID,
															MProject.COLUMNNAME_ProjInvoiceRule,
															MProject.COLUMNNAME_C_BPartner_ID,
															MProject.COLUMNNAME_PlannedAmt,
															MProject.COLUMNNAME_PlannedQty,
															MProjectPhase.COLUMNNAME_Help,
															MProjectPhase.COLUMNNAME_Responsible_ID,
															MProjectPhase.COLUMNNAME_PercentageCompleted,
															MProjectPhase.COLUMNNAME_DurationEstimated,
															MProjectPhase.COLUMNNAME_DurationReal,
															MProjectPhase.COLUMNNAME_IsRecurrent,
															MProjectPhase.COLUMNNAME_FrequencyType,
															MProjectPhase.COLUMNNAME_Frequency,
															MProjectPhase.COLUMNNAME_RunsMax,
															MProjectPhase.COLUMNNAME_RunsRemaining,
															MProjectPhase.COLUMNNAME_DateNextRun,
															MProjectPhase.COLUMNNAME_DateLastRun,
															MProjectPhase.COLUMNNAME_StartDate,
															MProjectPhase.COLUMNNAME_EndDate,
															MProjectPhase.COLUMNNAME_IsMilestone,
															MProjectTask.COLUMNNAME_DateFinish,
															MProjectTask.COLUMNNAME_C_ProjectTaskCategory_ID
															};
	
	public static String[] EXCLUDE_COLUMNS = new String [] {MProject.COLUMNNAME_DateLastAlert};


	public static String[] RESPONSIBLE_COLUMNS = new String [] {MProject.COLUMNNAME_ProjectManager_ID,
															MProjectPhase.COLUMNNAME_Responsible_ID
															};
	public static String[] INFO_COLUMNS = new String [] {MProject.COLUMNNAME_CreatedBy,
															MProject.COLUMNNAME_C_ProjectStatus_ID,
															MProject.COLUMNNAME_PriorityRule,
															MProject.COLUMNNAME_DueType,
															MProject.COLUMNNAME_C_ProjectCategory_ID,
															MProject.COLUMNNAME_C_ProjectClass_ID,
															MProject.COLUMNNAME_C_ProjectGroup_ID
														};
	public static String[] TIME_COLUMNS = new String [] {MProject.COLUMNNAME_DateStartSchedule,
															MProject.COLUMNNAME_DateFinishSchedule,
															MProject.COLUMNNAME_DateStart,
															MProject.COLUMNNAME_DateFinish,
															MProjectPhase.COLUMNNAME_StartDate,
															MProjectPhase.COLUMNNAME_EndDate,
															MProjectPhase.COLUMNNAME_DateDeadline,
															MProjectPhase.COLUMNNAME_PercentageCompleted
															};
	
	
	/**
	 * Get Display Value
	 * @param columnName
	 * @param entity
	 * @return
	 */
	public static String getDisplayValue(String columnName, PO entity) {
		
		if (entity==null) 
			return "--";
		
		Object value = entity.get_Value(columnName); 
		if (value == null)
			return "--";
		
		if (p_info == null 
				|| (p_info != null && p_info.getAD_Table_ID()!=entity.get_Table_ID())) 
			p_info = POInfo.getPOInfo (entity.getCtx(), entity.get_Table_ID(), entity.get_TrxName());
		
		if (p_info == null)
			return entity.get_ValueAsString(columnName);
		
		String retValue = value.toString();
		int index = entity.get_ColumnIndex(columnName);
		if (index < 0)
			return retValue;
		
		int displayType = p_info.getColumnDisplayType(index);
		if (DisplayType.isText(displayType) || DisplayType.YesNo == displayType) {
			return retValue;
		}
		//	For Date
		if(DisplayType.isDate(displayType)) {
			SimpleDateFormat format = DisplayType.getDateFormat(displayType);
			return format.format(value);
		}
		//	For Number
		if(DisplayType.isNumeric(displayType)) {
			DecimalFormat format = DisplayType.getNumberFormat(displayType);
			format.format(value);
		}
		//	Lookup
		Lookup lookup = p_info.getColumnLookup(index);
		if (lookup != null)
			return lookup.getDisplay(value);
		//	Other
		return retValue;
	}	//	get_DisplayValue
	
	/**
	 * Run Processor Log
	 * @param entity
	 * @param currentProcessor
	 * @param m_summary
	 * @param eventChangeLog
	 * @return
	 */
	public static MProjectProcessorLog runProjectProcessor(PO entity, MProjectProcessor currentProcessor, String m_summary, String eventChangeLog) {
    	MProject project = null;
		MProjectPhase projectPhase = null;
		MProjectTask projectTask = null;
		MProjectProcessorLog projectProcessorLog = null;
		//Set Project / Phase / Task
		if (entity instanceof MProject) 
			project = (MProject) entity;
		else if (entity instanceof MProjectPhase) {
			projectPhase = (MProjectPhase) entity;
			project = (MProject) projectPhase.getC_Project();
		}else if (entity instanceof MProjectTask) {
			projectTask = (MProjectTask) entity;
			projectPhase = (MProjectPhase) projectTask.getC_ProjectPhase();
			project = (MProject) projectPhase.getC_Project();
		}
    	if (currentProcessor == null) {
			List<MProjectProcessor> processor = new Query(entity.getCtx(), MProjectProcessor.Table_Name, "", entity.get_TrxName())
											.setOrderBy(MProjectProcessor.COLUMNNAME_C_ProjectType_ID + "," + MProjectProcessor.COLUMNNAME_C_ProjectTaskCategory_ID)
											.list();
			
			
			for (MProjectProcessor mProjectProcessor : processor) {
				
				if (project !=null 
						&& currentProcessor ==null
							&& project.getAD_Client_ID() == mProjectProcessor.getAD_Client_ID()
							)
					currentProcessor = mProjectProcessor;
				
				if (project !=null
						&& project.getAD_Client_ID() == mProjectProcessor.getAD_Client_ID()
							&& project.get_ValueAsInt("C_ProjectType_ID") == mProjectProcessor.getC_ProjectType_ID())
					currentProcessor = mProjectProcessor;
				
				if (project !=null
						&& projectTask!=null 
							&& project.getAD_Client_ID() == mProjectProcessor.getAD_Client_ID()
								&& projectTask.getC_ProjectTaskCategory_ID() == mProjectProcessor.getC_ProjectTaskCategory_ID()) {
					currentProcessor = mProjectProcessor;
					break;
				}
			}
    	}
    	
    	if (currentProcessor != null) {
			int no = currentProcessor.deleteLog();
			m_summary +="Logs deleted=" + no;
			
			projectProcessorLog = new MProjectProcessorLog(currentProcessor, m_summary, entity.get_TrxName());
			projectProcessorLog.setEventChangeLog(eventChangeLog);
			
			if (!projectProcessorLog.save())
				throw new AdempiereException ("@SaveError@ @C_ProjectProcessorLog_ID@");
			else {
					
					int  addQueued= 0;
					//Process Project
					if (entity.get_Table_ID() == MProject.Table_ID) 
						if (project!= null 
								&& project.getProjectManager_ID()!=0)
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), project.getProjectManager_ID()))
								addQueued ++; 
	
					//Process Project Phase
					if (entity.get_Table_ID() == MProjectPhase.Table_ID) { 
					
						if (projectPhase!= null 
								&& projectPhase.getResponsible_ID()!=0)
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), projectPhase.getResponsible_ID()))
								addQueued ++;
						
						if (project!= null 
								&& project.getProjectManager_ID()!=0)
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), project.getProjectManager_ID()))
								addQueued ++;
					}
					//Process Project Task
					if (entity.get_Table_ID() == MProjectTask.Table_ID) { 
						
						if (projectTask!= null 
								&& projectTask.getResponsible_ID()!=0)
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), projectTask.getResponsible_ID()))
								addQueued ++;
						
						if (projectPhase!= null 
								&& projectPhase.getResponsible_ID()!=0)
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), projectPhase.getResponsible_ID()))
								addQueued ++;
						
						if (project!= null 
								&& project.getProjectManager_ID()!=0)
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), project.getProjectManager_ID()))
								addQueued ++;
					}
					
					//Project Members
					if (project!=null) {
						List<MProjectMember> members = MProjectMember.getMembers(project);
						for (MProjectMember mProjectMember : members) 
							if (addQueued(projectProcessorLog, entity.getUpdatedBy(), mProjectMember.getAD_User_ID(),mProjectMember.getNotificationType()))
								addQueued ++;
						
					}
					//Add Changes
					if (addQueued > 0)
						addChanges(projectProcessorLog, entity);
				
			}
    	}
		
		return projectProcessorLog;
	}	//MProjectProcessorLog
	
	/**
	 * Add Queued
	 * @param pLog
	 * @param userId
	 * @return
	 */
    private static boolean addQueued(MProjectProcessorLog pLog, int updatedById, int userId, String NotificationType) {
    	MUser user = MUser.get(pLog.getCtx(), userId);
    	if(user.getAD_User_ID() == updatedById
    			&& !user.isIncludeOwnChanges()) {
    		return false;
    	}
    	MProjectProcessorQueued queued = new MProjectProcessorQueued(pLog, userId);
    	if (NotificationType!=null
    			&& !NotificationType.equals(queued.getNotificationType())) 
    		queued.setNotificationType(NotificationType);
    	
    	
    	if (NotificationType==null
    			&& queued.getNotificationType()==null)
			queued.setNotificationType(MProjectProcessorQueued.NOTIFICATIONTYPE_None);
	
		if (queued.is_new()
				|| queued.is_Changed())
			if(!queued.save()) 
				throw new AdempiereException("@SaveError@ @C_ProjectProcessorQueued_ID@");
		

		return true;
    }	//addQueued
    
    /**
     * Add Queued
     * @param pLog
     * @param userId
     * @return
     */
    private static boolean addQueued(MProjectProcessorLog pLog, int updatedById, int userId) {
    	return addQueued(pLog, updatedById, userId, null);
    }
    
    /**
     * Add Changes
     * @param log
     * @param entity
     * @return
     */
	private static int addChanges(MProjectProcessorLog log, PO entity) {
		
		int columnChanges = 0;
		
		for (int i=0; i < entity.get_ColumnCount(); i++) {
			String columnName = entity.get_ColumnName(i);
			
			if ((entity.is_ValueChanged(i)
					&& isListenColumn(columnName))
					|| (log.getEventChangeLog().equals(MProjectProcessorLog.EVENTCHANGELOG_Insert)
							&& (columnName.equals(MProjectProcessorLog.COLUMNNAME_Created)
								|| columnName.equals(MProjectProcessorLog.COLUMNNAME_CreatedBy)))
				){
				MProjectProcessorChange change = new MProjectProcessorChange(log);
				change.setAD_Table_ID(entity.get_Table_ID());
				change.setRecord_ID(entity.get_ID());
				change.setAD_Column_ID(MColumn.getColumn_ID(entity.get_TableName(), columnName));
				change.setNewValue((getDisplayValue(columnName,entity)));
				
				if (!change.save()) {
					throw new AdempiereException("@SaveError@ @C_ProjectProcessorQueued_ID@");
				}else
					columnChanges++;
			}
		}
		
		return columnChanges;
	}	//addChanges
	
	/**
	 * Check if column is listen
	 * @param column
	 * @return
	 */
	public static boolean isListenColumn(String column) {
		if (column != null) {
			for (String column_listen : LISTEN_COLUMNS) {
				if (column_listen.equals(column))
					return true;
			}
		}
		return false;
	}	//isListenColumn
	
	/**
	 * Check if column is excluded
	 * @param column
	 * @return
	 */
	public static boolean isExcludeColumn(String column) {
		if (column != null) {
			for (String column_exclude : EXCLUDE_COLUMNS) {
				if (column_exclude.equals(column))
					return true;
			}
		}
		return false;
	}	//isListenColumn

}
