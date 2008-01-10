/**********************************************************************
* This file is part of Adempiere ERP Bazaar                           *
* http://www.adempiere.org                                            *
*                                                                     *
* Copyright (C) Carlos Ruiz - globalqss                               *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Carlos Ruiz - globalqss                                           *
*                                                                     *
* Sponsors:                                                           *
* - Company (http://www.globalqss.com)                                *
***********************************************************************/

package org.adempiere.process;

import java.util.*;
import java.util.logging.*;
import org.compiere.model.*;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.wf.MWFNode;
import org.compiere.wf.MWorkflow;

/**
 * 	Generate ASP entries for a level
 *	
 *  @author Carlos Ruiz
 */
public class ASPGenerateLevel extends SvrProcess
{
	private String  p_ASP_Status;
	private int p_AD_Menu_ID;
	private boolean p_IsGenerateFields;
	private int p_ASP_Level_ID;
	
	private int noWindows = 0;
	private int noTabs = 0;
	private int noFields = 0;
	private int noProcesses = 0;
	private int noParameters = 0;
	private int noForms = 0;
	private int noTasks = 0;
	private int noWorkflows = 0;
	
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("ASP_Status"))
				p_ASP_Status = (String) para[i].getParameter();
			else if (name.equals("AD_Menu_ID"))
				p_AD_Menu_ID = para[i].getParameterAsInt();
			else if (name.equals("IsGenerateFields"))
				p_IsGenerateFields = para[i].getParameter().equals("Y");
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_ASP_Level_ID = getRecord_ID();
	}	//	prepare

	/**
	 * 	Process
	 *	@return info
	 *	@throws Exception
	 */
	protected String doIt () throws Exception
	{
		log.info("ASP_Status=" + p_ASP_Status 
			+ ", AD_Menu_ID=" + p_AD_Menu_ID
			+ ", IsGenerateFields=" + p_IsGenerateFields
			);
		
		MClientInfo clientInfo = MClientInfo.get(getCtx(), getAD_Client_ID(), get_TrxName());
		int AD_Tree_ID = clientInfo.getAD_Tree_Menu_ID();
		MTree thisTree = new MTree (getCtx(), AD_Tree_ID, true, true, true, get_TrxName());
		MTreeNode node;
		if (p_AD_Menu_ID > 0)
			node = thisTree.getRoot().findNode(p_AD_Menu_ID);
		else
			node = thisTree.getRoot();
			
		// Navigate the menu and add every non-summary node
		if (node != null && node.isSummary())
		{
			Enumeration en = node.preorderEnumeration();
			while (en.hasMoreElements())
			{
				MTreeNode nn = (MTreeNode)en.nextElement();
				if (!nn.isSummary())
					addNodeToLevel(nn);
			}
		}
		
		if (noWindows > 0)
			addLog("Window " + noWindows);
		if (noTabs > 0)
			addLog("Tab " + noTabs);
		if (noFields > 0)
			addLog("Field " + noFields);
		if (noProcesses > 0)
			addLog("Process " + noProcesses);
		if (noParameters > 0)
			addLog("Process Parameter " + noParameters);
		if (noForms > 0)
			addLog("Form " + noForms);
		if (noTasks > 0)
			addLog("Task " + noTasks);
		if (noWorkflows > 0)
			addLog("Workflow " + noWorkflows);

		return "@OK@";
	}	//	doIt

	private void addNodeToLevel(MTreeNode nn) {
		// Add Menu
		MMenu menu = new MMenu(getCtx(), nn.getNode_ID(), get_TrxName());

		if (menu.getAction().equals(MMenu.ACTION_Window)) {
			MWindow window = new MWindow(getCtx(), menu.getAD_Window_ID(), get_TrxName());
			if (DB.getSQLValue(
					get_TrxName(),
					"SELECT COUNT(*) FROM ASP_Window WHERE ASP_Level_ID = ? AND AD_Window_ID = ?",
					p_ASP_Level_ID, window.getAD_Window_ID()) < 1) {
				// Add Window, Tabs and Fields (if IsGenerateFields)
				X_ASP_Window aspWindow = new X_ASP_Window(getCtx(), 0, get_TrxName());
				aspWindow.setASP_Level_ID(p_ASP_Level_ID);
				aspWindow.setAD_Window_ID(window.getAD_Window_ID());
				aspWindow.setASP_Status(p_ASP_Status);
				if (aspWindow.save())
					noWindows++;
			}
			// tabs
			MTab[] tabs = window.getTabs(true, get_TrxName());
			for (int  it = 0; it < tabs.length; it++) {
				if (DB.getSQLValue(
						get_TrxName(),
						"SELECT COUNT(*) FROM ASP_Tab WHERE ASP_Level_ID = ? AND AD_Tab_ID = ?",
						p_ASP_Level_ID, tabs[it].getAD_Tab_ID()) < 1) {
					X_ASP_Tab aspTab = new X_ASP_Tab(getCtx(), 0, get_TrxName());
					aspTab.setASP_Level_ID(p_ASP_Level_ID);
					aspTab.setAD_Window_ID(tabs[it].getAD_Window_ID());
					aspTab.setAD_Tab_ID(tabs[it].getAD_Tab_ID());
					aspTab.setASP_Status(p_ASP_Status);
					aspTab.setAllFields(! p_IsGenerateFields);
					if (aspTab.save())
						noTabs++;
					if (p_IsGenerateFields) {
						// fields
						MField[] fields = tabs[it].getFields(true, get_TrxName());
						for (int  ifi = 0; ifi < fields.length; ifi++) {
							if (DB.getSQLValue(
									get_TrxName(),
									"SELECT COUNT(*) FROM ASP_Field WHERE ASP_Level_ID = ? AND AD_Field_ID = ?",
									p_ASP_Level_ID, fields[ifi].getAD_Field_ID()) < 1) {
								X_ASP_Field aspField = new X_ASP_Field(getCtx(), 0, get_TrxName());
								aspField.setASP_Level_ID(p_ASP_Level_ID);
								aspField.setAD_Tab_ID(fields[ifi].getAD_Tab_ID());
								aspField.setAD_Field_ID(fields[ifi].getAD_Field_ID());
								aspField.setASP_Status(p_ASP_Status);
								if (aspField.save())
									noFields++;
							}
						}
					}
				}
			}
		} else if (menu.getAction().equals(MMenu.ACTION_Process) 
				|| menu.getAction().equals(MMenu.ACTION_Report)) {
			// Add Process and Parameters
			MProcess process = new MProcess(getCtx(), menu.getAD_Process_ID(), get_TrxName());
			if (DB.getSQLValue(
					get_TrxName(),
					"SELECT COUNT(*) FROM ASP_Process WHERE ASP_Level_ID = ? AND AD_Process_ID = ?",
					p_ASP_Level_ID, process.getAD_Process_ID()) < 1) {
				X_ASP_Process aspProcess = new X_ASP_Process(getCtx(), 0, get_TrxName());
				aspProcess.setASP_Level_ID(p_ASP_Level_ID);
				aspProcess.setAD_Process_ID(process.getAD_Process_ID());
				aspProcess.setASP_Status(p_ASP_Status);
				if (aspProcess.save())
					noProcesses++;
			}
			// parameters
			MProcessPara[] processparas = process.getParameters();
			for (int  ipp = 0; ipp < processparas.length; ipp++) {
				if (DB.getSQLValue(
						get_TrxName(),
						"SELECT COUNT(*) FROM ASP_Process_Para WHERE ASP_Level_ID = ? AND AD_Process_Para_ID = ?",
						p_ASP_Level_ID, processparas[ipp].getAD_Process_Para_ID()) < 1) {
					X_ASP_Process_Para aspProcess_Para = new X_ASP_Process_Para(getCtx(), 0, get_TrxName());
					aspProcess_Para.setASP_Level_ID(p_ASP_Level_ID);
					aspProcess_Para.setAD_Process_ID(processparas[ipp].getAD_Process_ID());
					aspProcess_Para.setAD_Process_Para_ID(processparas[ipp].getAD_Process_Para_ID());
					aspProcess_Para.setASP_Status(p_ASP_Status);
					if (aspProcess_Para.save())
						noParameters++;
				}
			}
		} else if (menu.getAction().equals(MMenu.ACTION_Form)) {
			// Add Form
			MForm form = new MForm(getCtx(), menu.getAD_Form_ID(), get_TrxName());
			if (DB.getSQLValue(
					get_TrxName(),
					"SELECT COUNT(*) FROM ASP_Form WHERE ASP_Level_ID = ? AND AD_Form_ID = ?",
					p_ASP_Level_ID, form.getAD_Form_ID()) < 1) {
				X_ASP_Form aspForm = new X_ASP_Form(getCtx(), 0, get_TrxName());
				aspForm.setASP_Level_ID(p_ASP_Level_ID);
				aspForm.setAD_Form_ID(form.getAD_Form_ID());
				aspForm.setASP_Status(p_ASP_Status);
				if (aspForm.save())
					noForms++;
			}
		} else if (menu.getAction().equals(MMenu.ACTION_Task)) {
			// Add Task
			MTask task = new MTask(getCtx(), menu.getAD_Task_ID(), get_TrxName());
			if (DB.getSQLValue(
					get_TrxName(),
					"SELECT COUNT(*) FROM ASP_Task WHERE ASP_Level_ID = ? AND AD_Task_ID = ?",
					p_ASP_Level_ID, task.getAD_Task_ID()) < 1) {
				X_ASP_Task aspTask = new X_ASP_Task(getCtx(), 0, get_TrxName());
				aspTask.setASP_Level_ID(p_ASP_Level_ID);
				aspTask.setAD_Task_ID(task.getAD_Task_ID());
				aspTask.setASP_Status(p_ASP_Status);
				if (aspTask.save())
					noTasks++;
			}
		} else if (menu.getAction().equals(MMenu.ACTION_WorkFlow)) {
			// Add Workflow and Nodes
			MWorkflow workflow = new MWorkflow(getCtx(), menu.getAD_Workflow_ID(), get_TrxName());
			if (DB.getSQLValue(
					get_TrxName(),
					"SELECT COUNT(*) FROM ASP_Workflow WHERE ASP_Level_ID = ? AND AD_Workflow_ID = ?",
					p_ASP_Level_ID, workflow.getAD_Workflow_ID()) < 1) {
				X_ASP_Workflow aspWorkflow = new X_ASP_Workflow(getCtx(), 0, get_TrxName());
				aspWorkflow.setASP_Level_ID(p_ASP_Level_ID);
				aspWorkflow.setAD_Workflow_ID(workflow.getAD_Workflow_ID());
				aspWorkflow.setASP_Status(p_ASP_Status);
				if (aspWorkflow.save())
					noWorkflows++;
			}
		}		
	}

}	//	ASPGenerateLevel