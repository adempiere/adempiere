/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2003-2012 e-Evolution Consultants. All Rights Reserved.      *
 * Copyright (C) 2003-2012 Victor Pérez Juárez 								  * 
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
 * Contributor(s): Victor Pérez Juárez  (victor.perez@e-evolution.com)		  *
 * Sponsors: e-Evolution Consultants (http://www.e-evolution.com/)            *
 *****************************************************************************/

package org.eevolution.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Box;

import org.adempiere.controller.SmallViewEditable;
import org.compiere.apps.ALayout;
import org.compiere.apps.ALayoutConstraint;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.swing.CEditor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;
import org.eevolution.grid.BrowserSearch;

/**
 * @author victor.perez@e-evolution.com , eEvolution Consultants
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 340 ] Smart Browse context is changed from table
 * 		@see https://github.com/adempiere/adempiere/issues/340
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/556">
 * 		FR [ 590 ] NPE on Smart Browser when a field have display logic</a>
 * 		<a href="https://github.com/adempiere/adempiere/issues/590">
 * 		@see FR [ 590 ] The ZK search window don't have standard position buttons</a>
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li> BR [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] 
 *			Parameter Panel & SmartBrowser criteria do not set gridField value
 * 
 */
public class VBrowserSearch extends BrowserSearch implements SmallViewEditable {

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param p_Browser Browser controller
	 */
	public VBrowserSearch(int WindowNo, int p_AD_Browse_ID) {
		super(WindowNo, p_AD_Browse_ID);
	}	//	VBrowserSearch

	/**
	 * With Columns
	 * @param WindowNo
	 * @param p_Browser
	 * @param column
	 */
	public VBrowserSearch(int WindowNo, int p_AD_Browse_ID, int column) {
		super(WindowNo, p_AD_Browse_ID, column);
	}	//	VBrowserSearch
	
	//Layout Mode
	private int cols = 0;
	private int row = 0;
	//
	// Presentation for ranges
	private ArrayList<CLabel> 	m_separators;
	//
	// Layout
	private BorderLayout 	mainLayout;
	private ALayout			centerLayout;
	private CPanel 			centerPanel;
	private CPanel			mainPanel;
	
	private static CLogger log = CLogger.getCLogger(VBrowserSearch.class);

	/**
	 * Initialize components
	 */
	@Override
	public void initComponents() {
		if(mainPanel != null)
			return;
		//	
		mainLayout 		= new BorderLayout();
		centerPanel 	= new CPanel();
		mainPanel 		= new CPanel();
		m_separators 	= new ArrayList<CLabel>();
		//	
		mainPanel.setLayout(mainLayout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//	
		centerLayout 	= new ALayout();
		centerPanel.setLayout(centerLayout);
	}

	/**
	 * Get Main Panel
	 * @return
	 */
	public CPanel getPanel() {
		return mainPanel;
	}
	
	/**
	 *  Dispose
	 */
	public void dispose() {
		super.dispose();
		mainPanel.removeAll();
	}   //  dispose

	public CEditor createEditor(GridField field) {
		return(VEditorFactory.getEditor(field, false));
	}

	@Override
	public void formatEditor(CEditor editor1, CEditor editor2) {
		log.fine("");
		
		VEditor editor = (VEditor) editor1;
		VEditor editorTo = (VEditor) editor2;

		if(editor == null) {
			return;
		}

		configColumns(editor, editorTo);
		//  Create label
		CLabel label = VEditorFactory.getLabel(editor.getField());
		if (label == null) {
			centerPanel.add(Box.createHorizontalStrut(12), new ALayoutConstraint(row, cols++));   	//	left gap
		} else {
			centerPanel.add(label, new ALayoutConstraint(row, cols++));
		}
		//
		centerPanel.add ((Component)editor, new ALayoutConstraint(row, cols++));
		//	To
		if(editorTo == null) {
			m_separators.add(null);
			return;
		}
		//	Add
		CLabel dash = new CLabel(" - ");
		centerPanel.add (dash, new ALayoutConstraint(row, cols++));
		m_separators.add(dash);
		//
		centerPanel.add ((Component)editorTo, new ALayoutConstraint(row, cols++));
	}
	
	/**
	 * Congure columns
	 * @param editor
	 * @param editor_To
	 */
	private void configColumns(VEditor editor, VEditor editorTo) {
		int maxToAdd = getColumns() * 2;
		int columnsToAdd = getColumns();
		//	for To field
		if(editorTo != null) {
			columnsToAdd += 2;
		}
		//	Verify if is new row or not
		if((cols + columnsToAdd) > maxToAdd ) {
			cols = 0;
			row ++;
		}
	}
	
	@Override
	public void setComponentVisibility(int index, Boolean visible, Boolean isRange) {
		VEditor editor = (VEditor) getEditor(index);
		VEditor editorTo = (VEditor) getEditorTo(index);
		//	Validate null
		if (editor == null)
			return;
		
		Component[] components = centerPanel.getComponents();
		for (Component comp : components) {
			if (editor.getField().getColumnName().equals(comp.getName())) {	
				if (visible) {
					if (!comp.isVisible()) {
						comp.setVisible(true); // visibility
						//	FR [ 349 ]
						if (isRange && editorTo != null) {
							editorTo.setVisible(true);
							m_separators.get(index).setText(" - ");
						}
					}
				}
				else if (comp.isVisible()) {
					comp.setVisible(false);
					//	BR [ 590 ]
					if (isRange && editorTo != null) {
						editorTo.setVisible(false);
						m_separators.get(index).setText("");
					}
				}
			}
		}
	}	
}
