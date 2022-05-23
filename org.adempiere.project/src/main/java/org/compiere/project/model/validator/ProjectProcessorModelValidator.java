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

package org.compiere.project.model.validator;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.MProject;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectTask;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.project.service.ProjectProcessorService;
import org.compiere.util.Util;
import org.eevolution.model.MProjectProcessorLog;

/**
 * Project Processor Model Validator
 * @author Carlos Parada, cparada@erpya.com, ERPCyA http://www.erpya.com
 *  	<a href="https://github.com/adempiere/adempiere/issues/2202">
 *		@see FR [ 2202 ] Add Support to Project Processor</a>
 */
public class ProjectProcessorModelValidator implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		engine.addModelChange(MProject.Table_Name, this);
		engine.addModelChange(MProjectPhase.Table_Name, this);
		engine.addModelChange(MProjectTask.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() {
		return 0;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		return null;
	}

	@Override
	public String modelChange(PO entity, int type) throws Exception {
		
		if (entity.get_TableName().equals(MProject.Table_Name)
				|| entity.get_TableName().equals(MProjectPhase.Table_Name)
					|| entity.get_TableName().equals(MProjectTask.Table_Name)) {

			if (type == TYPE_AFTER_NEW
					|| (type == TYPE_AFTER_CHANGE
							&& columnsValids(entity))) {

				String eventChangeLog = (type == TYPE_AFTER_NEW ? MProjectProcessorLog.EVENTCHANGELOG_Insert : MProjectProcessorLog.EVENTCHANGELOG_Update);
				if (entity.get_Table_ID()==MProjectPhase.Table_ID
						|| entity.get_Table_ID()==MProjectTask.Table_ID) {
					
					MProject project = null;
					
					if (entity.get_Table_ID()==MProjectPhase.Table_ID) { 
						MProjectPhase projectPhase = (MProjectPhase) entity;
						project = (MProject) projectPhase.getC_Project();
						if (project.getProjectLineLevel().equals(MProject.PROJECTLINELEVEL_Project))
							return null;
					}else{ 
						MProjectTask projectTask = (MProjectTask) entity;
						MProjectPhase projectPhase = (MProjectPhase) projectTask.getC_ProjectPhase();
						project = (MProject) projectPhase.getC_Project();
						if (project.getProjectLineLevel().equals(MProject.PROJECTLINELEVEL_Project)
								|| project.getProjectLineLevel().equals(MProject.PROJECTLINELEVEL_Phase))
							return null;
					}
				}
				
				if (!entity.is_ValueChanged(MProject.COLUMNNAME_DueType)) {
					if (entity.is_ValueChanged(MProject.COLUMNNAME_DateStartSchedule)
							&& !Util.isEmpty(entity.get_ValueAsString(MProject.COLUMNNAME_DueType))
								&& entity.get_ValueAsString(MProject.COLUMNNAME_DueType).equals(MProject.DUETYPE_Scheduled)) {
						entity.set_ValueOfColumn(MProject.COLUMNNAME_DueType, null);
						entity.set_ValueOfColumn(MProject.COLUMNNAME_DateLastAlert, null);
						entity.save();
					}else if ((entity.is_ValueChanged(MProject.COLUMNNAME_DateFinishSchedule)
								|| entity.is_ValueChanged(MProject.COLUMNNAME_DateDeadline))
										&& (entity.get_ValueAsString(MProject.COLUMNNAME_DueType).equals(MProject.DUETYPE_Due)
											|| entity.get_ValueAsString(MProject.COLUMNNAME_DueType).equals(MProject.DUETYPE_Overdue))) {
						entity.set_ValueOfColumn(MProject.COLUMNNAME_DueType, MProject.DUETYPE_Scheduled);
						entity.set_ValueOfColumn(MProject.COLUMNNAME_DateLastAlert, null);
						entity.save();
					}
				}
				ProjectProcessorService.runProjectProcessor(entity, null, "", eventChangeLog);
				
			}
		}
		
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		return null;
	}
	
	/**
	 * Returns columns changed
	 * @param entity
	 * @return
	 */
	private List<String> columnsChanged(PO entity){
		ArrayList<String> retValue = new ArrayList<String>();
		for (int i=0; i< entity.get_ColumnCount(); i++) {
			if (entity.is_ValueChanged(i))
				retValue.add(entity.get_ColumnName(i));
		}
		
		return retValue;
	}
	
	/**
	 * Verified if column is valid
	 * @param entity
	 * @return
	 */
	private boolean columnsValids(PO entity) {
		List<String> columnsChanged = columnsChanged(entity);
		for (String columnName : columnsChanged) {
			if (ProjectProcessorService.isListenColumn(columnName))
				return true;
		}
		
		return false;
	}

}
