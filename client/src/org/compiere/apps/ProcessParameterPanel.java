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
package org.compiere.apps;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;

import org.adempiere.controller.SmallViewEditable;
import org.compiere.grid.ed.VEditor;
import org.compiere.grid.ed.VEditorFactory;
import org.compiere.model.GridField;
import org.compiere.process.ProcessInfo;
import org.compiere.swing.CEditor;
import org.compiere.swing.CLabel;
import org.compiere.swing.CPanel;
import org.compiere.util.CLogger;

/**
 *	Process Parameter Panel, based on existing ProcessParameter dialog.
 *	- Embedded in ProcessDialog
 *	- checks, if parameters exist and inquires and saves them
 *
 * @author Low Heng Sin
 * @author Juan David Arboleda (arboleda), GlobalQSS, [ 1795398 ] Process
 *         Parameter: add display and readonly logic
 * @author Teo Sarca, www.arhipac.ro
 * 			<li>BF [ 2548216 ] Process Param Panel is not showing any parameter if error 
 * @author victor.perez@e-evoluton.com, www.e-evolution.com 
 * 			<li>FR [ 3426137 ] Smart Browser
 * 			 https://sourceforge.net/tracker/?func=detail&aid=3426137&group_id=176962&atid=879335
 * 			<li> https://adempiere.atlassian.net/browse/ADEMPIERE-97
 * 			<li> The parameters is not show when use display logic, the parameters dialog window is not automatically resize.
 * @version 	2006-12-01
 * @author Michael Mckay michael.mckay@mckayerp.com
 * 			<li>BF3423098 - Labels for process parameters with display logic false are still displayed
 *			<li>BF [ <a href="https://github.com/adempiere/adempiere/issues/495">495</a> ] Parameter Panel & SmartBrowser criteria do not set gridField value
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *			<li>FR [ 265 ] ProcessParameterPanel is not MVC
 *			@see https://github.com/adempiere/adempiere/issues/265
 *			<li>FR [ 349 ] GridFieldVO attribute is ambiguous
 * 			@see https://github.com/adempiere/adempiere/issues/349
 * 			<li>FR [ 298 ] Process Parameter Panel not set default value correctly into parameters
 *			@see https://github.com/adempiere/adempiere/issues/298
 */
public class ProcessParameterPanel extends ProcessParameter implements SmallViewEditable {

	/**
	 *	Dynamic generated Parameter panel.
	 *  @param WindowNo window
	 *  @param pi process info
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi) {
		super(WindowNo, pi);
	}	//	ProcessParameterPanel

	/**
	 * With Columns
	 * @param WindowNo
	 * @param pi
	 * @param column
	 */
	public ProcessParameterPanel(int WindowNo, ProcessInfo pi, int column) {
		super(WindowNo, pi, column);
	}	//	ProcessParameterPanel
	
	//Layout Mode
	private int cols = 0;
	private int row = 0;
	//
	// Presentation for ranges
	private ArrayList<CLabel> 	m_separators;
	//
	// Layout
	private BorderLayout 	mainLayout;
	private CPanel 			centerPanel;
	private CPanel			mainPanel;
	//
	private int 			labelMinWidth = 0;
	private int 			fieldMinWidth = 0;
	private final int		SEPARATOR = 25;
	
	private static CLogger log	= CLogger.getCLogger(ProcessParameterPanel.class);

	public void initComponents() {
		mainLayout 		= new BorderLayout();
		centerPanel 	= new CPanel();
		mainPanel 		= new CPanel();
		m_separators 	= new ArrayList<CLabel>();
		//	
		mainPanel.setLayout(mainLayout);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		//	
		centerPanel.setLayout(new ALayout());
	}

	/**
	 * Get Main Panel
	 * @return
	 */
	public CPanel getPanel() {
		return mainPanel;
	}
	
	public CEditor createEditor(GridField field) {
		return(VEditorFactory.getEditor(field, false));
	}

	
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
			int currentWidth = label.getPreferredSize().width;
			labelMinWidth = currentWidth > labelMinWidth ? currentWidth : labelMinWidth;
//			label.setSize(label.getPreferredSize());
//			label.setMinimumSize(label.getPreferredSize());
//			label.putClientProperty("LabelUI.truncationString", ">");
			centerPanel.add(label, new ALayoutConstraint(row, cols++));
		}
		//	
		Component component = (Component) editor;
		fieldMinWidth = component.getPreferredSize().width > fieldMinWidth 
				? component.getPreferredSize().width : fieldMinWidth;
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
		if((cols + columnsToAdd) > maxToAdd) {
			cols = 0;
			row ++;
		}
	}


	@Override
	public void dynamicDisplay() {
		//	
		centerPanel.setPreferredSize(new Dimension(labelMinWidth + fieldMinWidth + SEPARATOR, (row + 1) * SEPARATOR)); // Row height
		
		super.dynamicDisplay();

	} // Dynamic Display.
	
	@Override
	public void setComponentVisibility(int index, Boolean visible, Boolean isRange) {
		
		VEditor editor = (VEditor) getEditor(index);
		if (editor == null)
			return;
		
		Component[] components = centerPanel.getComponents();
		for (Component comp : components) {
			if (editor.getField().getColumnName().equals(comp.getName())) {	
				if (visible) {
					if (!comp.isVisible()) {
						comp.setVisible(true); // visibility
						//	FR [ 349 ]
						if (isRange)
							m_separators.get(index).setText(" - ");
						else
							m_separators.get(index).setText("");
					}
				}
				else if (comp.isVisible()) {
					comp.setVisible(false);
					if (isRange) {
						m_separators.get(index).setText("");
					}
				}
			}
		}
	}	
		
	@Override
	public String saveParameters() {
		String validError = super.saveParameters();
		if(validError != null) {
			ADialog.error(getWindowNo(), getPanel(), "FillMandatory", validError);
		}
		//	
		return validError;
	}

}	//	ProcessParameterPanel
