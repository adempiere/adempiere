/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.adempiere.process;

import java.util.Enumeration;

import org.adempiere.core.domains.models.I_AD_Menu;
import org.adempiere.core.domains.models.I_ASP_Level;
import org.adempiere.model.MBrowse;
import org.adempiere.model.MBrowseField;
import org.compiere.model.MBrowseCustom;
import org.compiere.model.MBrowseFieldCustom;
import org.compiere.model.MClientInfo;
import org.compiere.model.MColumn;
import org.compiere.model.MField;
import org.compiere.model.MFieldCustom;
import org.compiere.model.MMenu;
import org.compiere.model.MProcess;
import org.compiere.model.MProcessCustom;
import org.compiere.model.MProcessPara;
import org.compiere.model.MProcessParaCustom;
import org.compiere.model.MTab;
import org.compiere.model.MTabCustom;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.MWindow;
import org.compiere.model.MWindowCustom;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;

/** 
 * 	Create Customization from ASP or Menu
 *  @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 *  @version Release 3.9.2
 */
public class CreateCustomizationFromASP extends CreateCustomizationFromASPAbstract {

	private int noWindows = 0;
	private int noTabs = 0;
	private int noFields = 0;
	private int noProcesses = 0;
	private int noParameters = 0;
	private int noBrowses = 0;
	private int noBrowseFields = 0;
	private int menuId = 0;
	
	@Override
	protected void prepare() {
		super.prepare();
		if(getTable_ID() == I_AD_Menu.Table_ID) {
			menuId = getRecord_ID();
		} else if(getTable_ID() == I_ASP_Level.Table_ID) {
			menuId = getParameterAsInt(I_AD_Menu.COLUMNNAME_AD_Menu_ID);
			setLevelId(getRecord_ID());
		}
	}
	
	@Override
	protected String doIt() throws Exception {
		MClientInfo clientInfo = MClientInfo.get(getCtx(), getAD_Client_ID(), get_TrxName());
		int AD_Tree_ID = clientInfo.getAD_Tree_Menu_ID();
		MTree thisTree = new MTree (getCtx(), AD_Tree_ID, true, true, null, get_TrxName());
		MTreeNode node;
		if (menuId > 0) {
			node = thisTree.getRoot().findNode(menuId);
		} else {
			node = thisTree.getRoot();
		}
			
		// Navigate the menu and add every non-summary node
		if (node != null) {
			if(node.isSummary()) {
				Enumeration<?> en = node.preorderEnumeration();
				while (en.hasMoreElements()) {
					MTreeNode childNode = (MTreeNode)en.nextElement();
					if (!childNode.isSummary()) {
						addNodeToLevel(childNode);
					}
				}
			} else {
				addNodeToLevel(node);
			}
		}
		if (noWindows > 0)
			addLog("@AD_Window_ID@ (" + noWindows + ")");
		if (noTabs > 0)
			addLog("@AD_Tab_ID@ (" + noTabs + ")");
		if (noFields > 0)
			addLog("@AD_Field_ID@ (" + noFields + ")");
		if (noProcesses > 0)
			addLog("@AD_Process_ID@ (" + noProcesses + ")");
		if (noParameters > 0)
			addLog("@AD_Process_Para_ID@ (" + noParameters + ")");
		if (noBrowses > 0)
			addLog("@AD_Browse_ID@ (" + noBrowses + ")");
		if (noBrowseFields > 0)
			addLog("@AD_Browse_Field_ID@ (" + noBrowseFields + ")");
		
		return "@OK@";
	}
	
	/**
	 * Add Node
	 * @param nn
	 */
	private void addNodeToLevel(MTreeNode nn) {
		// Add Menu
		MMenu menu = new MMenu(getCtx(), nn.getNode_ID(), get_TrxName());

		if (menu.getAction().equals(MMenu.ACTION_Window)) {
			MWindow window = new MWindow(getCtx(), menu.getAD_Window_ID(), get_TrxName());
			int customWindowId = DB.getSQLValueEx(get_TrxName(),
					"SELECT AD_WindowCustom_ID FROM AD_WindowCustom WHERE ASP_Level_ID = ? AND AD_Window_ID = ?",
					getRecord_ID(), window.getAD_Window_ID());
			MWindowCustom customWindow = null;
			if (customWindowId < 1) {
				// Add Window, Tabs and Fields (if IsGenerateFields)
				customWindow = new MWindowCustom(getCtx(), 0, get_TrxName());
				customWindow.setASP_Level_ID(getLevelId());
				customWindow.setAD_Window_ID(window.getAD_Window_ID());
				customWindow.setHierarchyType(getHierarchyType());
				customWindow.saveEx();
				noWindows++;
				customWindowId = customWindow.getAD_WindowCustom_ID();
			} else {
				customWindow = new MWindowCustom(getCtx(), customWindowId, get_TrxName());
			}
			// tabs
			for (MTab tab : window.getTabs(true, get_TrxName())) {
				int tabId = DB.getSQLValueEx(get_TrxName(),
						"SELECT AD_TabCustom_ID FROM AD_TabCustom WHERE AD_WindowCustom_ID = ? AND AD_Tab_ID = ?",
						customWindowId, tab.getAD_Tab_ID());
				MTabCustom customTab = null;
				if (tabId < 1) {
					customTab = new MTabCustom(customWindow);
					customTab.setAD_Tab_ID(tab.getAD_Tab_ID());
					customTab.saveEx();
					noTabs++;
					tabId = customTab.getAD_TabCustom_ID();
				} else {
					customTab = new MTabCustom(getCtx(), tabId, get_TrxName());
				}
				// fields
				if(isAllFields()) {
					for (MField field : tab.getFields(true, get_TrxName())) {
						if (field.isActive()) {
							if (DB.getSQLValueEx(
									get_TrxName(),
									"SELECT COUNT(*) FROM AD_FieldCustom WHERE AD_TabCustom_ID = ? AND AD_Field_ID = ?",
									customTab.getAD_TabCustom_ID(), field.getAD_Field_ID()) < 1) {
								MFieldCustom customField = new MFieldCustom(customTab);
								customField.setAD_Field_ID(field.getAD_Field_ID());
								customField.saveEx();
								noFields++;
							}
						}
						// verify if a field is a button and assign permission to the corresponding process
						MColumn column = MColumn.get(getCtx(), field.getAD_Column_ID());
						if (column.getAD_Reference_ID() == DisplayType.Button) {
							if (column.getAD_Process_ID() > 0) {
								generateProcess(column.getAD_Process_ID());
							}
						}
					}
				}
			}
		} else if (menu.getAction().equals(MMenu.ACTION_Process)
				|| menu.getAction().equals(MMenu.ACTION_Report)) {
			generateProcess(menu.getAD_Process_ID());
		} else if (menu.getAction().equals(MMenu.ACTION_SmartBrowse)) {
			// Add Browse
			MBrowse browse = new MBrowse(getCtx(), menu.getAD_Browse_ID(), get_TrxName());
			if (DB.getSQLValueEx(
					get_TrxName(),
					"SELECT COUNT(*) FROM AD_BrowseCustom WHERE ASP_Level_ID = ? AND AD_Browse_ID = ?",
					getRecord_ID(), browse.getAD_Browse_ID()) < 1) {
				MBrowseCustom customBrowse = new MBrowseCustom(getCtx(), 0, get_TrxName());
				customBrowse.setASP_Level_ID(getLevelId());
				customBrowse.setAD_Browse_ID(browse.getAD_Browse_ID());
				customBrowse.setHierarchyType(getHierarchyType());
				customBrowse.saveEx();
				//	For Browse Field
				if(isAllFields()) {
					for(MBrowseField browseField : browse.getFields()) {
						if (DB.getSQLValueEx(
								get_TrxName(),
								"SELECT COUNT(*) FROM AD_BrowseFieldCustom WHERE AD_BrowseCustom_ID = ? AND AD_Browse_Field_ID = ?",
								customBrowse.getAD_BrowseCustom_ID(), browseField.getAD_Browse_Field_ID()) < 1) {
							MBrowseFieldCustom customBrowseField = new MBrowseFieldCustom(customBrowse);
							customBrowseField.setAD_Browse_Field_ID(browseField.getAD_Browse_Field_ID());
							customBrowseField.saveEx();
							noBrowseFields++;
						}
					}
				}
				noBrowses++;
			}
		}		
	}
	
	/**
	 * For Process
	 * @param processId
	 */
	private void generateProcess(int processId) {
		// Add Process and Parameters
		MProcess process = new MProcess(getCtx(), processId, get_TrxName());
		int customprocessId = DB.getSQLValueEx(get_TrxName(),
				"SELECT AD_ProcessCustom_ID FROM AD_ProcessCustom WHERE ASP_Level_ID = ? AND AD_Process_ID = ?",
				getRecord_ID(), process.getAD_Process_ID());
		MProcessCustom customProcess = null;
		if (customprocessId < 1) {
			customProcess = new MProcessCustom(getCtx(), 0, get_TrxName());
			customProcess.setASP_Level_ID(getLevelId());
			customProcess.setAD_Process_ID(process.getAD_Process_ID());
			customProcess.setHierarchyType(getHierarchyType());
			customProcess.saveEx();
			noProcesses++;
			customprocessId = customProcess.getAD_ProcessCustom_ID();
		} else {
			customProcess = new MProcessCustom(getCtx(), customprocessId, get_TrxName());
		}
		//	
		if(isAllFields()) {
			// parameters
			for (MProcessPara processParameter : process.getParameters()) {
				if (DB.getSQLValueEx(
						get_TrxName(),
						"SELECT COUNT(*) FROM AD_ProcessParaCustom WHERE AD_ProcessCustom_ID = ? AND AD_Process_Para_ID = ?",
						customprocessId, processParameter.getAD_Process_Para_ID()) < 1) {
					MProcessParaCustom customProcessPara = new MProcessParaCustom(customProcess);
					customProcessPara.setAD_Process_Para_ID(processParameter.getAD_Process_Para_ID());
					customProcessPara.saveEx();
					noParameters++;
				}
			}
		}
	}
}