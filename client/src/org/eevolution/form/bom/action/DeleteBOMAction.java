/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                        *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.form.bom.action;

import java.awt.event.ActionEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.eevolution.form.action.PopupAction;
import org.eevolution.model.wrapper.BOMLineWrapper;
import org.eevolution.model.wrapper.BOMWrapper;

/**
 *  @author Victor Perez, e-Evolution, S.C.
 * Delete Bill of Material / Line
 * 
 * Deletes a bill of material or a single line by tree selection.
 * 
 * @author Gunther Hoppe, tranSIT GmbH Ilmenau/Germany
 * @version 1.0, October 14th 2005
 */
public class DeleteBOMAction extends PopupAction {

	public static final String COMMAND = "deleteBOM";
	
	protected JTree tree;
	
	/**
	 * Constructs a new Instance.
	 */
	public DeleteBOMAction(JTree tree) {
		
		super(COMMAND);
		setActionCommand(COMMAND);
		
		this.tree = tree;
	}

	protected String getCommand() {

		return COMMAND;
	}

	protected String validateAction() {

		return null;
	}

	protected void doAction(ActionEvent e) {
	
		if(tree != null) {

			delete((DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent());
		}
	}

	private void delete(DefaultMutableTreeNode node) {
		
		if(node.getUserObject() instanceof BOMWrapper) {
			
			BOMWrapper bom = (BOMWrapper)node.getUserObject();
	    	for(int i = 0; i < node.getChildCount(); i++) {
	    		
	    		delete((DefaultMutableTreeNode)node.getChildAt(i));
	    		if(!successful()) {
	    			
	    			break;
	    		}
	    	}
 
	    	deletePO(bom.get());
		}
		else {
			
	    	BOMLineWrapper line = (BOMLineWrapper)node.getUserObject();
	    	deletePO(line.get());
		}
	}
}
