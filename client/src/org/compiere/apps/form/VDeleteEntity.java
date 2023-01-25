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
package org.compiere.apps.form;

import it.cnr.imaa.essi.lablib.gui.checkboxtree.CheckboxTree;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.logging.Level;

import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.adempiere.util.DeleteEntitiesModel;
import org.compiere.apps.ADialog;
import org.compiere.apps.ConfirmPanel;
import org.compiere.swing.CButton;
import org.compiere.swing.CCheckBox;
import org.compiere.swing.CComboBox;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

/**
 *	Delete Dialog.
 *
 *	@author Paul Bowden
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/880">
 * 		@see FR [ 880 ] Allow "Delete Entities" form to be accessible only for role "System Administrator"</a>
 */
public class VDeleteEntity extends DeleteEntityControler
	implements FormPanel, ActionListener {
	
	/**	Window No			*/
	private int         	m_WindowNo = 0;
	/**	FormFrame			*/
	private FormFrame 		m_frame;   
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(VDeleteEntity.class);

	private BorderLayout mainLayout = new BorderLayout();
	private CPanel mainPanel = new CPanel();
	private CPanel centerPanel = new CPanel();
	private GridBagLayout centerLayout = new GridBagLayout();
	private ConfirmPanel confirmPanel = new ConfirmPanel(true);
	private JScrollPane treePane;
	private CheckboxTree tree;
	private CLabel clientLabel;
	private CComboBox clientPick;
	private CComboBox tablePick;
	private CLabel tableLabel;
	private CComboBox definitionPick;
	private CLabel definitionLabel;
	private CCheckBox isTableBased;
	private CCheckBox dryRun;
	private CButton refreshButton;
	private DefaultMutableTreeNode rootNode;

	/**
	 *	Initialize Panel
	 *  @param WindowNo window
	 *  @param frame frame
	 */
	public void init (int WindowNo, FormFrame frame) {
		m_WindowNo = WindowNo;
		m_frame = frame;
		log.info( "VMerge.init - WinNo=" + m_WindowNo);
		try {
			preInit();
			jbInit ();
			frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		} catch (Exception ex) {
			log.log(Level.SEVERE, "", ex);
		}
	}	//	init
	
	/**
	 * Create Nodes for parent
	 * @param root
	 */
	@SuppressWarnings("unchecked")
	private void createNodes(DefaultMutableTreeNode root, boolean isParent) {
		DeleteEntitiesModel currentNode = (DeleteEntitiesModel) root.getUserObject();		
		//	Load from parent
		loadChilds(currentNode, root, isParent);
		//	
		Enumeration<TreeNode> kids = root.children();
		while (kids.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)kids.nextElement();
			if (root.isNodeAncestor(node)) {
				log.log(Level.WARNING, "Loop detected, escaping.");
				break;
			} else if (((DeleteEntitiesModel) node.getUserObject()).isMandatoryLink()) {
				createNodes(node, false);
			}
		}
	}

	/**
	 * Clear View
	 */
	private void loadValues() {
		//	Client
		Object o = clientPick.getValue();
		if (o != null) {
			setClientId(((KeyNamePair) o).getKey());
		}
		setCleanDefinition(!isTableBased.isSelected());
		if(isCleanDefinition()) {
			//	Definition
			o = definitionPick.getValue();
			if (o != null) {
				setCleanDefinitionId(((KeyNamePair) o).getKey());
			}
			setTableId(0);			
		} else {
			//	Table
			o = tablePick.getValue();
			if (o != null) {
				setTableId(((KeyNamePair) o).getKey());
			}
			setCleanDefinitionId(0);
		}
		//	Dry Run
		setDryRun(dryRun.isSelected());
	}
	
	/**
	 * Load View
	 * @param clientId
	 * @param tableId
	 */
	private void loadView() {
		//	
		mainPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		confirmPanel.getOKButton().setEnabled(false);
		//
		DeleteEntitiesModel data = new DeleteEntitiesModel(getClientId(), getTableId());
		//	
		rootNode = new DefaultMutableTreeNode(data);

		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		model.setRoot(rootNode);
		createNodes(rootNode, true);

		tree.expandRow(0);

		//
		confirmPanel.getOKButton().setEnabled(true);
		mainPanel.setCursor(Cursor.getDefaultCursor());
	}

	/**
	 * 	Pre Init
	 */
	private void preInit() {
		refreshButton = ConfirmPanel.createRefreshButton(false);
		refreshButton.addActionListener(this);
		//	
		dryRun = new CCheckBox(Msg.getMsg(Env.getCtx(), "DryRun"), true);
		isTableBased = new CCheckBox(Msg.getElement(Env.getCtx(), "IsTableBased"), true);
		isTableBased.addActionListener(this);
		clientLabel = new CLabel(Msg.getElement(Env.getCtx(), "AD_Client_ID"));
		clientPick = new CComboBox(getClients());
		clientPick.setSelectedItem(null);
		clientPick.setMandatory(true);
		clientPick.setBackground(false);
		
		tableLabel = new CLabel(Msg.getElement(Env.getCtx(), "AD_Table_ID"));
		tablePick = new CComboBox(getTables());
		definitionLabel = new CLabel(Msg.getElement(Env.getCtx(), "AD_CleanDefinition_ID"));
		definitionPick = new CComboBox(getCleanDefinition());
		tablePick.setSelectedItem(null);
		tablePick.setMandatory(true);
		tablePick.setBackground(false);
		definitionPick.setSelectedItem(null);
		definitionPick.setMandatory(true);
		definitionPick.setBackground(false);
		definitionPick.setVisible(false);
		definitionLabel.setVisible(false);
		rootNode = new DefaultMutableTreeNode(null);
		tree = new CheckboxTree(rootNode);
		treePane = new JScrollPane(tree);
		
		JViewport viewPort = treePane.getViewport();
		viewPort.add(tree);
	}	//	preInit

	/**
	 * 	Static init
	 * 	@throws java.lang.Exception
	 */
	void jbInit () throws Exception {
		mainPanel.setLayout (mainLayout);
		mainLayout.setHgap (5);
		mainLayout.setVgap (5);
		//
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
		//
		centerPanel.setLayout (centerLayout);
		mainPanel.add(centerPanel, BorderLayout.NORTH);
		//	Cliet Label
		centerPanel.add(clientLabel,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//	Client Pick
		centerPanel.add(clientPick,   new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//	Is Table Based
		centerPanel.add(isTableBased,   new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Dry Run
		centerPanel.add(dryRun,   new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Table Label
		centerPanel.add(tableLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//	Table Pick
		centerPanel.add(tablePick,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		// Definition Label
		centerPanel.add(definitionLabel,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//	Definition Pick
		centerPanel.add(definitionPick,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//	Refresh Button
		centerPanel.add(refreshButton,    new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
				,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
		//	
		mainPanel.add(treePane, BorderLayout.CENTER);
	}	//	jbInit

	/**
	 * 	Dispose
	 */
	public void dispose() {
		if (m_frame != null)
			m_frame.dispose();
		m_frame = null;
	}	//	dispose

	/**
	 *  Action ListeneranObject
	 *  @param e event
	 */
	public void actionPerformed (ActionEvent e) {
		log.log(Level.FINE, "from: " + e.getSource() + " action: " + e.getActionCommand());
		//	
		if(e.getSource().equals(isTableBased)) {
			definitionPick.setVisible(!isTableBased.isSelected());
			definitionLabel.setVisible(!isTableBased.isSelected());
			tablePick.setVisible(isTableBased.isSelected());
			tableLabel.setVisible(isTableBased.isSelected());
		} else if (e.getActionCommand().equals(ConfirmPanel.A_CANCEL)) {
			dispose();
			return;
		} else if (e.getActionCommand().equals(ConfirmPanel.A_OK)) {
			//	Load current values
			loadValues();
			//	
			if(getClientId() <= 0 || getTableId() <= 0) {
				ADialog.error(m_WindowNo, mainPanel, "Error", 
						Msg.getMsg(Env.getCtx(), "SelectClient"));
			} else {
				//	
				Enumeration<?> nodes = rootNode.breadthFirstEnumeration();
				//	Prepare to delete
				while (nodes.hasMoreElements()) {
					addToStackForDelete((DeleteEntitiesModel) (((DefaultMutableTreeNode) nodes.nextElement()).getUserObject()));
				}
				int deleted = 0;
				try {
					//	Delete
					deleted = deleteSelectedNodes();
				} catch (Exception ex) {
					ADialog.error(m_WindowNo, mainPanel, "DeleteError", ex.getLocalizedMessage());
					return;
				}
				//	
				ADialog.info (m_WindowNo, mainPanel, "DeleteSuccess", 
						Msg.getMsg(Env.getCtx(), "RecordsDeleted") + " #" + deleted);
				//	
				loadView();
			}
		} else if(e.getActionCommand().equals(ConfirmPanel.A_REFRESH)) {
			//	Load current values
			loadValues();
			//	
			if (getClientId() <= 0) {
				ADialog.error(m_WindowNo, mainPanel, "Error", 
						Msg.getMsg(Env.getCtx(), "SelectClient"));
				return;
			}
			//	Load
			loadView();
			//
		}
	}   //  actionPerformed

	@Override
	public void addToNode(DeleteEntitiesModel data, Object rootNode) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(data.getTableName());
		node.setUserObject(data);
		//	Add to parent
		((DefaultMutableTreeNode)rootNode).add(node);
	}
}	//	VDelete