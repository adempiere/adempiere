/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/

package org.adempiere.webui.panel;

import org.adempiere.apps.toolbar.AProcessActionModel;
import org.adempiere.core.domains.models.I_AD_Process;
import org.adempiere.webui.editor.WButtonEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.model.MProcess;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

/**
 * View for Action Process PopupMenu
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/999">
 * 		@see FR [ 999 ] Add ZK Support for Process Action</a>
 *
 */
public class WProcessAction {

	public WProcessAction(AbstractADWindowPanel parent) {
		this.parent= parent; 
		popupMenu =  new Menupopup();
        popupMenu.setStyle("background: #E8E3E3 repeat-y scroll 0 0 !important;");
        //	Add Options
        model = new AProcessActionModel();
        //	
        for(MProcess process : model.fetchProcesses(Env.getCtx(), parent.getToolbar().getCurrentPanel().getGridTab())) {
        	addMenuItem(process);
        }
	}
	
	private AbstractADWindowPanel parent;
	private Menupopup popupMenu;
	private AProcessActionModel model;
	
	/**
	 * Add menu Item
	 * @param optionName
	 */
	private void addMenuItem(MProcess process) {
        Menuitem menuItem =  new Menuitem(model.getDisplayName(process));
        menuItem.addEventListener(Events.ON_CLICK, new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				startProcess(process);
			}
		});
        //	Add
        popupMenu.appendChild(menuItem);
    }
	
	/**
	 * Start Process
	 * @param process
	 */
	private void startProcess(MProcess process) {
		GridFieldVO fieldVO = GridFieldVO.createStdField(Env.getCtx(), 
				parent.getWindowNo(), 0, 
				parent.getActiveGridTab().getAD_Window_ID(), 
				parent.getActiveGridTab().getAD_Tab_ID(), 
				false, 
				false, 
				false);
		//	Set
		fieldVO.ColumnName = "StartProcess";
		fieldVO.Description = process.get_Translation(I_AD_Process.COLUMNNAME_Description);
		fieldVO.AD_Process_ID = process.getAD_Process_ID();
		fieldVO.Help = process.get_Translation(I_AD_Process.COLUMNNAME_Description);
		fieldVO.isProcess = true;
		fieldVO.displayType = DisplayType.Button;
		//	
		WButtonEditor button = (WButtonEditor) WebEditorFactory.getEditor(new GridField(fieldVO), false);
		parent.actionButton(button);
	}
	
	/**
	 * Open Process Option
	 * @param invoker
	 */
	public void openOption(Component invoker) {
		popupMenu.setPage(invoker.getPage());
		popupMenu.open(invoker);
	}
	
}
