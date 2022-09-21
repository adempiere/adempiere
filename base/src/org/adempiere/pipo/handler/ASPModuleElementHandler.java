/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.adempiere.pipo.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.sax.TransformerHandler;

import org.adempiere.core.domains.models.I_AD_BrowseCustom;
import org.adempiere.core.domains.models.I_AD_Field;
import org.adempiere.core.domains.models.I_AD_ProcessCustom;
import org.adempiere.core.domains.models.I_AD_Role;
import org.adempiere.core.domains.models.I_AD_Tab;
import org.adempiere.core.domains.models.I_AD_User;
import org.adempiere.core.domains.models.I_AD_Window;
import org.adempiere.core.domains.models.I_AD_WindowCustom;
import org.adempiere.core.domains.models.I_ASP_Browse;
import org.adempiere.core.domains.models.I_ASP_Field;
import org.adempiere.core.domains.models.I_ASP_Form;
import org.adempiere.core.domains.models.I_ASP_Level;
import org.adempiere.core.domains.models.I_ASP_Module;
import org.adempiere.core.domains.models.I_ASP_Process;
import org.adempiere.core.domains.models.I_ASP_Process_Para;
import org.adempiere.core.domains.models.I_ASP_Tab;
import org.adempiere.core.domains.models.I_ASP_Task;
import org.adempiere.core.domains.models.I_ASP_Window;
import org.adempiere.core.domains.models.I_ASP_Workflow;
import org.adempiere.core.domains.models.X_ASP_Browse;
import org.adempiere.core.domains.models.X_ASP_Field;
import org.adempiere.core.domains.models.X_ASP_Form;
import org.adempiere.core.domains.models.X_ASP_Level;
import org.adempiere.core.domains.models.X_ASP_Process;
import org.adempiere.core.domains.models.X_ASP_Process_Para;
import org.adempiere.core.domains.models.X_ASP_Tab;
import org.adempiere.core.domains.models.X_ASP_Task;
import org.adempiere.core.domains.models.X_ASP_Window;
import org.adempiere.core.domains.models.X_ASP_Workflow;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.pipo.PackOut;
import org.compiere.model.MBrowseCustom;
import org.compiere.model.MProcessCustom;
import org.compiere.model.MWindowCustom;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.xml.sax.SAXException;

/**
 * ASP Module support
 * @author Yamel Senih www.erpya.com
 * 
 */
public class ASPModuleElementHandler extends GenericPOHandler {
	public void create(Properties ctx, TransformerHandler document) throws SAXException {
		int aspModuleId = Env.getContextAsInt(ctx, I_ASP_Module.COLUMNNAME_ASP_Module_ID);
		PackOut packOut = (PackOut) ctx.get("PackOutProcess");
		if(packOut == null ) {
			packOut = new PackOut();
			packOut.setLocalContext(ctx);
		}
		//	ASP
		try {
			packOut.createGenericPO(document, I_ASP_Module.Table_ID, aspModuleId, true, null);
			List<String> excludedTables = new ArrayList<>();
			excludedTables.add(I_AD_User.Table_Name);
			excludedTables.add(I_AD_Role.Table_Name);
			excludedTables.add(I_AD_Window.Table_Name);
			excludedTables.add(I_AD_Tab.Table_Name);
			excludedTables.add(I_AD_Field.Table_Name);
			//	Level
			List<X_ASP_Level> levelList = new Query(ctx, I_ASP_Level.Table_Name, I_ASP_Level.COLUMNNAME_ASP_Module_ID + " = ?", null)
					.setParameters(aspModuleId)
					.list();
			//	For tabs
			for(X_ASP_Level level : levelList) {
				packOut.createGenericPO(document, level, true, excludedTables);
				//	For window
				List<X_ASP_Window> windowList = new Query(ctx, I_ASP_Window.Table_Name, I_ASP_Window.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				//	For tabs
				for(X_ASP_Window window : windowList) {
					packOut.createGenericPO(document, window, true, excludedTables);
					//	For Tabs
					List<X_ASP_Tab> tabList = new Query(ctx, I_ASP_Tab.Table_Name, I_ASP_Tab.COLUMNNAME_ASP_Window_ID + " = ?", null)
							.setParameters(window.getASP_Window_ID())
							.list();
					//	
					for(X_ASP_Tab tab : tabList) {
						packOut.createGenericPO(document, tab, true, excludedTables);
						//	For Fields
						List<X_ASP_Field> fieldList = new Query(ctx, I_ASP_Field.Table_Name, I_ASP_Field.COLUMNNAME_ASP_Tab_ID + " = ?", null)
								.setParameters(tab.getASP_Tab_ID())
								.list();
						//	
						for(X_ASP_Field field : fieldList) {
							packOut.createGenericPO(document, field, true, excludedTables);
						}
					}
				}
				//	for Process
				List<X_ASP_Process> processList = new Query(ctx, I_ASP_Process.Table_Name, I_ASP_Process.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(X_ASP_Process process : processList) {
					packOut.createGenericPO(document, process, true, excludedTables);
					//	For process parameters
					List<X_ASP_Process_Para> processParamerersList = new Query(ctx, I_ASP_Process_Para.Table_Name, I_ASP_Process_Para.COLUMNNAME_ASP_Process_ID + " = ?", null)
							.setParameters(process.getASP_Process_ID())
							.list();
					for(X_ASP_Process_Para processParameter : processParamerersList) {
						packOut.createGenericPO(document, processParameter, true, excludedTables);
					}
				}
				//	For Task
				List<X_ASP_Task> taskList = new Query(ctx, I_ASP_Task.Table_Name, I_ASP_Task.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(X_ASP_Task task : taskList) {
					packOut.createGenericPO(document, task, true, excludedTables);
				}
				//	For Browse
				List<X_ASP_Browse> browseList = new Query(ctx, I_ASP_Browse.Table_Name, I_ASP_Browse.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.setOnlyActiveRecords(true)
						.list();
				for(X_ASP_Browse browse : browseList) {
					packOut.createGenericPO(document, browse, true, excludedTables);
				}
				//	For Form
				List<X_ASP_Form> formList = new Query(ctx, I_ASP_Form.Table_Name, I_ASP_Form.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(X_ASP_Form form : formList) {
					packOut.createGenericPO(document, form, true, excludedTables);
				}
				//	For Workflow
				List<X_ASP_Workflow> workflowList = new Query(ctx, I_ASP_Workflow.Table_Name, I_ASP_Workflow.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(X_ASP_Workflow workflow : workflowList) {
					packOut.createGenericPO(document, workflow, true, excludedTables);
				}
				//	For window Customization
				List<MWindowCustom> windowCustomizationList = new Query(ctx, I_AD_WindowCustom.Table_Name, I_AD_WindowCustom.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(MWindowCustom window : windowCustomizationList) {
					packOut.createWindowCustomization(window.getAD_WindowCustom_ID(), document);
				}
				//	For process Customization
				List<MProcessCustom> processCustomizationList = new Query(ctx, I_AD_ProcessCustom.Table_Name, I_AD_ProcessCustom.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(MProcessCustom process : processCustomizationList) {
					packOut.createProcessCustomization(process.getAD_ProcessCustom_ID(), document);
				}
				//	For process Customization
				List<MBrowseCustom> browseCustomizationList = new Query(ctx, I_AD_BrowseCustom.Table_Name, I_AD_BrowseCustom.COLUMNNAME_ASP_Level_ID + " = ?", null)
						.setParameters(level.getASP_Level_ID())
						.list();
				for(MBrowseCustom browse : browseCustomizationList) {
					packOut.createBrowseCustomization(browse.getAD_BrowseCustom_ID(), document);
				}
			}
		} catch (Exception e) {
			throw new AdempiereException(e);
		}
	}
}
