/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
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
package org.adempiere.webui.apps.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.adempiere.exceptions.ValueChangeEvent;
import org.adempiere.exceptions.ValueChangeListener;
import org.adempiere.util.DeleteEntitiesModel;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.apps.form.DeleteEntityControler;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treecol;
import org.zkoss.zul.Treecols;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

/**
 *	Delete Dialog.
 *
 *	@author Paul Bowden
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/880">
 * 		@see FR [ 880 ] Allow "Delete Entities" form to be accessible only for role "System Administrator"</a>
 */
public class WDeleteEntity extends DeleteEntityControler 
			implements IFormController,EventListener, ValueChangeListener{
	
	/**
	 * 
	 */
	private static CLogger log = CLogger.getCLogger(WDeleteEntity.class);
	private CustomForm form = new CustomForm();
	
	public WDeleteEntity()
	{
		Env.setContext(Env.getCtx(), form.getWindowNo(), "IsSOTrx", "Y");   //  defaults to no
		try
		{
			dynInit();
			zkInit();
		}
		catch(Exception e)
		{
			System.out.println(e);
			log.log(Level.SEVERE, "", e);
		}
	}
	
	private Borderlayout mainLayout = new Borderlayout();
	private Panel parameterPanel = new Panel();
	private Panel centerPanel = new Panel();
	private Panel southPanel = new Panel();
	private Grid centerLayout = GridFactory.newGridLayout();
	private Grid parameterLayout = GridFactory.newGridLayout();
	private Grid southLayout = GridFactory.newGridLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private Label clientLabel = new Label();
	private Label tableLabel = new Label();
	private Label definitionLabel = new Label();
	private Combobox tablePick = null;
	private Combobox definitionPick = null;
	private Combobox clientPick = null;
	private Button bRefresh = new Button();
	public Tree tree ;
	public Treecols treeCols;
	public Treecol treeCol;
	public Treecol treeCol2;
	public Checkbox dryRun;
	public Checkbox isTableBased;
	public Treerow treeRow;
	public Treecell treeCell;
	Set<Treeitem> setOfItemSelected = null;
	List<Treeitem> prevSelectedCol = new ArrayList<Treeitem>();
	public HashMap<String, Integer> clientMap = new HashMap<String, Integer>();
	
	private void zkInit() throws Exception {
		//Form Init()
		form.appendChild(mainLayout);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		clientLabel.setText(Msg.getElement(Env.getCtx(), "AD_Client_ID"));
		tableLabel.setText(Msg.getElement(Env.getCtx(), "AD_Table_ID"));
		definitionLabel.setText(Msg.getElement(Env.getCtx(), "AD_CleanDefinition_ID"));
		dryRun = new Checkbox(Msg.getMsg(Env.getCtx(), "DryRun"));
		dryRun.setChecked(true);
		isTableBased = new Checkbox(Msg.getElement(Env.getCtx(), "IsTableBased"));
		isTableBased.setChecked(true);
		//
		ConfirmPanel panel = new ConfirmPanel(false, false, false, false, false, false, false);
		bRefresh = panel.createButton(ConfirmPanel.A_REFRESH);
		bRefresh.addActionListener(this);
		isTableBased.addEventListener(Events.ON_CHECK, this);
		//	
		parameterPanel.appendChild(parameterLayout);
		North north = new North();
		north.setStyle("border: none");
		mainLayout.appendChild(north);
		north.appendChild(parameterPanel);
		Rows rows = null;
		Row row = null;
		parameterLayout.setWidth("100%");		
		rows = parameterLayout.newRows();
		row = rows.newRow();
		row.appendChild(clientLabel.rightAlign());
		row.appendChild(clientPick);
		row.appendChild(new Hbox());
		row.appendChild(isTableBased);
		row.appendChild(new Hbox());
		row.appendChild(dryRun);
		
		//	For Button
		row = rows.newRow();
		row.appendChild(tableLabel.rightAlign());
		row.appendChild(tablePick);
		row.appendChild(definitionLabel.rightAlign());
		row.appendChild(definitionPick);
		row.appendChild(new Hbox());
		row.appendChild(bRefresh);
		//	
		centerPanel.appendChild(centerLayout);
		centerLayout.setWidth("100%");			
		Center center = new Center();
		mainLayout.appendChild(center);
		center.setStyle("border: none");
		center.appendChild(centerPanel);
		tree = new Tree();
		treeCols = new Treecols();
		treeCol = new Treecol("");
		treeCol2 = new Treecol();
		centerPanel.appendChild(tree);
		treeCols.appendChild(treeCol);
		treeCols.appendChild(treeCol2);
        tree.appendChild(treeCols); 		
		center.setFlex(true);
		center.setAutoscroll(true);
		
		South south = new South();
		south.appendChild(southPanel);
		southPanel.appendChild(southLayout);		
		southPanel.setWidth("100%");
		mainLayout.appendChild(south);
		Rows rows2 = southLayout.newRows();		
		Row south_row = rows2.newRow();		
		south_row.appendChild(confirmPanel);
		confirmPanel.addActionListener(this);
		
	} 
	
	/**
	 * Dyn Init
	 */
	public void dynInit() {
		
		// Client Pick
		clientPick = new Combobox();
		tablePick = new Combobox();
		definitionPick = new Combobox();
		//	Load Clients
		for(KeyNamePair client : getClients()) {
			clientPick.appendItem(client.getName(), client);
		}
		//	
		clientPick.setSelectedIndex(0);
		// Load Tables
		for(KeyNamePair tables : getTables()) {
			tablePick.appendItem(tables.getName(), tables);
		}
		//	
		tablePick.setSelectedIndex(0);
		// Load Definitions
		for(KeyNamePair definition : getCleanDefinition()) {
			definitionPick.appendItem(definition.getName(), definition);
		}
		//	
		definitionPick.setSelectedIndex(0);
		//	
		definitionLabel.setVisible(false);
		definitionPick.setVisible(false);
	}   //  dynInit
	
	/**
	 * Create Nodes
	 * @param root
	 */
	private void createNodes(DeleteEntitiesModel currentNode, Treechildren root, boolean isParent)  {		
		//	Load from parent
		loadChilds(currentNode, root, isParent);
		@SuppressWarnings("unchecked")
		
		Collection<Treeitem> collItemChild = (Collection<Treeitem>) root.getItems();
		Iterator<Treeitem> it = collItemChild.iterator();
		//	
		while (it.hasNext()) {
			Treeitem node = (Treeitem) it.next();
			Treeitem rootOfNode = node.getParentItem();
			if ( rootOfNode != null && rootOfNode.getParentItem() != null &&  rootOfNode.getParentItem().equals(node)) {
				log.log(Level.WARNING, "Loop detected, escaping.");
				break;
			} else if (((DeleteEntitiesModel) node.getValue()).isMandatoryLink()){	
				DeleteEntitiesModel itemTableData = (DeleteEntitiesModel) node.getValue();
				Treechildren nodeChild = new Treechildren();
				createNodes(itemTableData, nodeChild, false);
				//	
				if(nodeChild.getItemCount() != 0) {
					node.appendChild(nodeChild);
				}
			}	
		}
	}

	@Override
	public void onEvent(Event e) throws Exception  {
		if(e.getTarget().equals(isTableBased)) {
			definitionPick.setVisible(!isTableBased.isChecked());
			definitionLabel.setVisible(!isTableBased.isChecked());
			tablePick.setVisible(isTableBased.isChecked());
			tableLabel.setVisible(isTableBased.isChecked());
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
		} else if(e.getTarget().getId().equals(ConfirmPanel.A_REFRESH)) {
			//	Load current values
			loadValues();
			//	
			if (getClientId() <= 0) {
				FDialog.error(form.getWindowNo(), form, "Error", Msg.getMsg(Env.getCtx(), "SelectClient"));
				return;
			}
			loadView();
		} else if (e.getTarget().getId().equals(ConfirmPanel.A_OK)) {
			//	Load current values
			loadValues();
			//	
			if (getClientId() <= 0 || getTableId() <= 0) {
				FDialog.error(form.getWindowNo(), "Error", 
						Msg.getMsg(Env.getCtx(), "SelectClient"));
			} else {
				@SuppressWarnings("unchecked")
				Collection<Treeitem> items = tree.getItems();	
				Iterator<Treeitem> nodes = items.iterator();
				//	Add to Delete Stack
				while (nodes.hasNext()) {
					addToStackForDelete((DeleteEntitiesModel) (((Treeitem) nodes.next()).getValue()));
				}
				int deleted = 0;
				try {
					//	Delete
					deleted = deleteSelectedNodes();
				} catch (Exception ex) {
					FDialog.error(form.getWindowNo(), "DeleteError", 
							ex.getLocalizedMessage());
					return;
				}
				//	
				FDialog.info(form.getWindowNo(), form, "DeleteSuccess", 
						Msg.getMsg(Env.getCtx(), "RecordsDeleted") + " #" + deleted);
				//	
				loadView();
			}
		}		
	}	// onEvent
	
	/**
	 * Dispose
	 */
	public void dispose() {
		SessionManager.getAppDesktop().closeActiveWindow();
	} // dispose

	/**
	 * Clear View
	 */
	private void loadValues() {
		//	Client
		Comboitem o = clientPick.getSelectedItem();
		if (o != null) {
			setClientId(((KeyNamePair) o.getValue()).getKey());
		}
		setCleanDefinition(!isTableBased.isChecked());
		if(isCleanDefinition()) {
			//	Table
			o = definitionPick.getSelectedItem();
			if (o != null) {
				setCleanDefinitionId(((KeyNamePair) o.getValue()).getKey());
			}
			setTableId(0);
		} else {
			//	Table
			o = tablePick.getSelectedItem();
			if (o != null) {
				setTableId(((KeyNamePair) o.getValue()).getKey());
			}
			setCleanDefinitionId(0);
		}
		//	Dry Run
		setDryRun(dryRun.isChecked());
	}
	
	/**
	 * Load View
	 * @param clientId
	 * @param tableId
	 */
	private void loadView() {
		//	
		DeleteEntitiesModel data = new DeleteEntitiesModel(getClientId(), getTableId());
		//	
		tree.clear();
		if((tree.getChildren()).size() > 1) {
			
			@SuppressWarnings("rawtypes")
			List treePreviousChild = tree.getChildren();
			tree.removeChild((Treechildren) treePreviousChild.get(1));
		}
		//	
		Treechildren rootTreeChild = new Treechildren();				
		Treeitem rootTreeItem = new Treeitem();
		rootTreeItem.setValue(data);
		rootTreeItem.setLabel(data.toString());
	
		Treechildren rootTreeItemChild = new Treechildren();
		createNodes(data, rootTreeItemChild, true);
				
		rootTreeItem.appendChild(rootTreeItemChild);		
		rootTreeChild.appendChild(rootTreeItem);
		tree.appendChild(rootTreeChild);
	}
	
	@Override
	public ADForm getForm() {
		return form;
	}

	@Override
	public void addToNode(DeleteEntitiesModel data, Object rootNode) {
		Treeitem treeitem = new Treeitem();
		((Treechildren) rootNode).appendChild(treeitem);
        treeitem.setLabel(data.toString());	
        treeitem.setValue(data);
	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		//	
	}
} 	// WDelete