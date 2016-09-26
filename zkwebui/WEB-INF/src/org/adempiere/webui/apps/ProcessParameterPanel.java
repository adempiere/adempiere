/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 Adempiere, Inc. All Rights Reserved.               *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/

package org.adempiere.webui.apps;



import java.util.ArrayList;

import org.adempiere.controller.SmallViewEditable;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WEditorPopupMenu;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ContextMenuListener;
import org.compiere.apps.ProcessParameter;
import org.compiere.model.GridField;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CEditor;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * 	@author 	Low Heng Sin
 * 	@author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 		<li>FR [ 3426137 ] Smart Browser
 *  	https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 *  @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *		@see https://github.com/adempiere/adempiere/issues/265
 *		<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *		@see https://github.com/adempiere/adempiere/issues/298
 *	@author Michael Mckay michael.mckay@mckayerp.com
 *		<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] Parameter Panel & SmartBrowser criteria do not set gridField value
 * 	@version 	2006-12-01
 */
public class ProcessParameterPanel extends ProcessParameter implements SmallViewEditable
{
	private String width;

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi)
	{
		this(WindowNo, pi, "100%");
	}	//	ProcessParameterPanel
	
	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi, String width) {
		this(WindowNo, pi, width, COLUMNS_1);
	}	//	ProcessParameterPanel
	
	/**
	 * With Columns
	 * @param WindowNo
	 * @param pi
	 * @param width
	 * @param columns
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi, String width, int columns) {
		super(WindowNo, pi, columns);
		this.width = width;
	}

	/**
	 * Initialize components
	 */
	@Override
	public void initComponents() {
		m_separators = new ArrayList<Label>();
		rows = new Rows();
		//
		mainPanel = new Panel();
		centerPanel = GridFactory.newGridLayout();
		centerPanel.setInnerWidth(width);
		mainPanel.appendChild(centerPanel);
		
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
	}
	
	/**
	 * Get Main Panel
	 * @return
	 */
	public Panel getPanel() {
		return mainPanel;
	}

	//Layout Mode
	private int cols = 0;
	//
	private ArrayList<Label> 	m_separators;
	/**	Rows for Parameters	*/
	private Rows 	rows;
	private Row		currentRow;
	//
	private Grid 	centerPanel;
	private Panel	mainPanel;

	/**
	 *  Dispose
	 */
	public void dispose() {
		super.dispose();
		m_separators.clear();
		mainPanel.detach();
	}   //  dispose
	
	public CEditor createEditor(GridField field) {
		return(WebEditorFactory.getEditor(field, false));
	}

	public void formatEditor(CEditor editor1, CEditor editor2) {
		//
		WEditor editor = (WEditor) editor1;
		WEditor editorTo = (WEditor) editor2;
		
		configColumns(editor, editorTo);
		//	
		if(editor == null) {
			return;
		}
		//streach component to fill grid cell
		editor.fillHorizontal();
        //setup editor context menu
		WEditorPopupMenu popupMenu;
        popupMenu = editor.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editor);
            mainPanel.appendChild(popupMenu);
        }
		//	
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
		//	The 2nd range editor
		editorTo.fillHorizontal();
		//setup editor context menu
        popupMenu = editorTo.getPopupMenu();                    
        if (popupMenu != null) {
        	popupMenu.addMenuListener((ContextMenuListener)editorTo);
            mainPanel.appendChild(popupMenu);
        }
        //
		Label separator = new Label(" - ");
		m_separators.add(separator);
		box.appendChild(separator);
		box.appendChild(editorTo.getComponent());
        //	Add
        currentRow.appendChild(box);
	}
	
	/**
	 * Congure columns
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
}	//	ProcessParameterPanel

