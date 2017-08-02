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
 * Copyright (C) 2016 ADempiere Foundation All Rights Reserved.               *
 *****************************************************************************/
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import org.adempiere.controller.DeleteSelectionController;
import org.compiere.model.GridTab;
import org.compiere.swing.CDialog;
import org.compiere.swing.CPanel;
import org.compiere.swing.CScrollPane;
import org.compiere.util.Env;
import org.compiere.util.Msg;

/**
 * The DeleteSelectionController provides a MVC used for 
 * get data for delete records with selection
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/592">
 * 		@see FR [ 592 ] Delete Selection dialog is not MVC</a>
 */
public class DeleteSelection extends DeleteSelectionController implements ActionListener {

	/**
	 * Default Constructor with selection
	 * @param owner
	 * @param tab
	 * @param defaultSelection
	 */
	public DeleteSelection(Frame owner, GridTab tab, List<Integer> defaultSelection) {
		super(tab, defaultSelection);
		mainDialog = new CDialog(owner, Msg.getMsg(Env.getCtx(), "DeleteSelection"), true);
	}
	
	/**
	 * Default Constructor without selection
	 * @param owner
	 * @param tab
	 */
	public DeleteSelection(Frame owner, GridTab tab) {
		this(owner, tab, null);
	}
	
	/**	Main Dialog		*/
	private CDialog mainDialog;
	/**	Main Panel		*/
	private CPanel	mainPanel;
	/**	Cancel Ok Panel	*/
	private ConfirmPanel confirmPanel;
	/**	List for Select	*/
	private JList<String> list;
	

	/**
	 * Init Components
	 */
	private void initComponents() {
		mainPanel = new CPanel(new BorderLayout(0,0));
		confirmPanel = new ConfirmPanel(true);
		mainDialog.getContentPane().add(mainPanel);
		//	Add Components
		list = new JList<String>();
		CScrollPane scrollPane = new CScrollPane(list);
		// FR [ 2877111 ]
		list.setListData(getData());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JEditorPane message = new JEditorPane();
		//	Description
		message.setContentType("text/html");
		message.setEditable(false);
		message.setBackground(Color.white);
		message.setFocusable(true);
		message.setBackground(mainPanel.getBackground());
		message.setText(Msg.getMsg(Env.getCtx(), "DeleteSelectionDescription"));
		//	Add to main panel
		mainPanel.add(message, BorderLayout.NORTH);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(confirmPanel, BorderLayout.SOUTH);
		confirmPanel.addActionListener(this);
		//	Default Selected
		if(isDefaultSelected()
				&& getSelection() != null) {
			list.setSelectedIndices(getSelection());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(confirmPanel.getCancelButton())) {
			mainDialog.dispose();
		} else if(e.getSource().equals(confirmPanel.getOKButton())) {
			setIsOkPressed(true);
			setSelection(list.getSelectedIndices());
			mainDialog.dispose();
		}
	}
	
	@Override
	public void showDialog() {
		initComponents();
		AEnv.showCenterScreen(mainDialog);
	}
}
