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

import java.util.ArrayList;

import org.adempiere.controller.SmallViewEditable;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.compiere.model.GridField;
import org.compiere.swing.CEditor;
import org.eevolution.grid.BrowserSearch;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Row;

/**
 * @author victor.perez@e-evolution.com , eEvolution Consultants
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<li>BR [ 340 ] Smart Browse context is changed from table
 * 		@see https://github.com/adempiere/adempiere/issues/340
 * 		<li><a href="https://github.com/adempiere/adempiere/issues/556">
 * 		FR [ 556 ] Criteria Search on SB don't have a parameter like only information</a>
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li> BR [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] 
 *			Parameter Panel & SmartBrowser criteria do not set gridField value
 */
public class WBrowserSearch extends BrowserSearch implements SmallViewEditable {

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param p_AD_Browse_ID Smart Browse ID
	 */
	public WBrowserSearch(int WindowNo, int p_AD_Browse_ID) {
		this(WindowNo, p_AD_Browse_ID, COLUMNS_1);
	}	//	WBrowserSearch
	
	/**
	 * With Columns
	 * @param WindowNo
	 * @param p_AD_Browse_ID
	 * @param columns
	 */
	public WBrowserSearch(int WindowNo, int p_AD_Browse_ID, int columns) {
		super(WindowNo, p_AD_Browse_ID, columns);
	}

	//Layout Mode
	private int cols = 0;
	private ArrayList<Label> 	m_separators;
	/**	Rows for Parameters	*/
	private Rows 	rows;
	private Row		currentRow;
	//
	private Grid 	centerPanel;
	private Panel	mainPanel;

	/**
	 * Initialize components
	 */
	@Override
	public void initComponents() {
		if(mainPanel != null)
			return;
		//	
		m_separators = new ArrayList<Label>();
		rows = new Rows();
		//
		mainPanel = new Panel();
		centerPanel = GridFactory.newGridLayout();
		
		//setup columns
    	Columns columns = new Columns();
    	centerPanel.appendChild(columns);
    	int colN = getColumns() * 2;
    	if(colN != 0) {
    		int percent = 100 / colN;
    		for(int i = 0; i < colN; i++) {
    			Column col = new Column();
	        	col.setWidth((i == 0
	        			? ((int) percent / 2)
	        			: percent) + "%");
	        	columns.appendChild(col);
	    	}
    	}
    	//	Add Rows
    	centerPanel.appendChild(rows);
    	mainPanel.appendChild(centerPanel);
		mainPanel.setStyle("overflow-y:auto");
	}
	
	/**
	 * Get Main Panel
	 * @return
	 */
	public Panel getPanel() {
		return mainPanel;
	}
	
	public CEditor createEditor(GridField field) {
		return(WebEditorFactory.getEditor(field, false));
	}
	
	public void formatEditor(CEditor editor1, CEditor editor2) {
		
		WEditor editor = (WEditor) editor1;
		WEditor editorTo = (WEditor) editor2;
		//	
		configColumns(editor, editorTo);
		WEditorPopupMenu popupMenu;

		// Editor
        //setup editor context menu
        popupMenu = editor.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editor);
            mainPanel.appendChild(popupMenu);
        }

		//streach component to fill grid cell
		editor.fillHorizontal();
		
    	Div div = new Div();
        div.setAlign("right");
        
        Label label = editor.getLabel();
        div.appendChild(label);
        if (label.getDecorator() != null)
        	div.appendChild(label.getDecorator());
        //	
        currentRow.appendChild(div);
        //	Add Child
        cols += 2;
		//	
        Hbox box;
        if(editorTo != null) {
        	box = new Hbox();
        	box.appendChild(editor.getComponent());
        } else {
            currentRow.appendChild(editor.getComponent());
        	m_separators.add(null);
        	return;
        }
        
        // EditorTo
		//setup editor context menu
        popupMenu = editorTo.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editor2);
            mainPanel.appendChild(popupMenu);
        }
        //
		editorTo.fillHorizontal();

		Label separator = new Label(" - ");
		m_separators.add(separator);
		box.appendChild(separator);
		box.appendChild(editorTo.getComponent());
        //	Add
        currentRow.appendChild(box);
	}
	
	/**
	 * Configure columns
	 * @param field
	 * @param field_To
	 */
	private void configColumns(WEditor editor, WEditor editorTo) {
		int maxToAdd = getColumns() * 2;
		int columnsToAdd = getColumns();
		//	for To field
		if(editorTo != null) {
			columnsToAdd += 2;
		}
		//	Verify if is new row or not
		if((cols + columnsToAdd) > maxToAdd
				|| currentRow == null) {
			cols = 0;
			currentRow = new Row();
			rows.appendChild(currentRow);
		}
	}
	
	@Override
	public void setComponentVisibility(int index, Boolean visible, Boolean isRange) {
		WEditor editor = (WEditor) getEditor(index);
		WEditor editorTo = (WEditor) getEditorTo(index);
		if (visible) {
			if (!editor.isVisible()) {
				editor.setVisible(true);  // Also makes the label visible
				if (isRange && editorTo != null) {
					m_separators.get(index).setVisible(true);
					editorTo.setVisible(true);
				}
			}
		}
		else if (editor.isVisible()) {
			editor.setVisible(false);  // Also hides the label
			if (isRange && editorTo != null) {
				m_separators.get(index).setText("");
				editorTo.setVisible(false);
			}
		}
	}

}
