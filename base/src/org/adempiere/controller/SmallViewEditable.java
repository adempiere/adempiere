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

package org.adempiere.controller;

import java.sql.ResultSet;

import org.compiere.model.GridField;
import org.compiere.swing.CEditor;

/**
 * The SmallViewEditable Interface defines the required behaviour of 
 * the UI view classes that extend the SmallViewController.  The UI classes 
 * can be either Swing or ZK.  The SmallViewController provides the connection
 * between the database model for {@link org.compiere.model.GridField GridFields}
 * and the UI editors.
 * 
 * @author mckayERP michael.mckay@mckayerp.com
 *
 */
public interface SmallViewEditable {

	/**
	 * Initialize the view components.  initComponents will be called during the initialization process
	 * to allow the View to generate the display structure.
	 */
	public void initComponents();

	/**
	 * Load the fields required by the view.  The loadData function should generate ResultSets that match 
	 * the AD_Process_Para fields and should call {@link org.adempiere.controller.SmallViewController#createField(ResultSet, int) createField(ResultSet, int)}
	 * to create the necessary fields.
	 */
	public boolean loadData();
	
	/**
	 * A function that must be implemented by the View to create the appropriate editor
	 * for the given gridField.  The editor should implement the CEditor interface and 
	 * return the CEditor object. 
	 * @param the GridField that requires an editor.
	 * @return The CEditor object representing the editor.
	 */
	public CEditor createEditor(GridField field);

	/**
	 * Format the given editors. formatEditor will be called during the initialization 
	 * process to allow the View to format the editor, create labels, 
	 * separators and any other actions necessary to position and display the editor.<br>
	 * <br>
	 * The two parameters represent a possible range.    
	 *  
	 * @param editor - the main editor object or the "From" element of a range
	 * @param editorTo - the "To" element of a range or null if the pair are not a range.
	 */
	public void formatEditor(CEditor editor, CEditor editorTo);

	/**
	 * Set the visibility of UI components for the indexed editor.  The index matches the order in 
	 * which createEditor and formatEditor were called and can be used to identify other components
	 * in the UI that are affected by the visibility of the editor. 
	 * @param index - the index of the editor
	 * @param visible - true if visible
	 * @param isRange - true if the editor is part of a range
	 */
	public void setComponentVisibility(int index, Boolean visible, Boolean isRange);
	
}
