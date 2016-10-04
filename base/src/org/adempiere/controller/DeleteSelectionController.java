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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Lookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.CLogMgt;
import org.compiere.util.DB;
import org.compiere.util.Env;


/**
 * The DeleteSelectionController provides a MVC used for 
 * get data for delete records with selection
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/592">
 * 		@see FR [ 592 ] Delete Selection dialog is not MVC</a>
 */
public abstract class DeleteSelectionController {

	/**
	 * Standard constructor with selection
	 * @param defaultSelection
	 */
	public DeleteSelectionController(GridTab tab, int[] defaultSelection) {
		this.currentTab = tab;
		this.defaultSelection = defaultSelection;
	}

	/**
	 * Standard constructor without selection
	 * @param tab
	 */
	public DeleteSelectionController(GridTab tab) {
		this(tab, null);
	}
	
	/**	Default Selection to show	*/
	private int[] defaultSelection;
	/**	Current Tab					*/
	private GridTab currentTab;
	/**	Is OK pressed				*/
	private boolean isOkPressed = false;
	/**	Index			*/
	private int[] selection;
	
	/**
	 * Get Tab
	 * @return
	 */
	public GridTab getTab() {
		return currentTab;
	}
	
	/**
	 * Get Default Selection
	 * @return
	 */
	public int[] getDefaultSelection() {
		return defaultSelection;
	}
	
	/**
	 * Get Index selected
	 * @return
	 */
	public int[] getSelection() {
		return selection;
	}
	
	/**
	 * Set Selection
	 * @param selection
	 */
	public void setSelection(int[] selection) {
		this.selection = selection;
		Arrays.sort(selection);
	}
	
	/**
	 * Is Ok Pressed by user
	 * @return
	 */
	public boolean isOkPressed() {
		return isOkPressed;
	}
	
	/**
	 * Set if is ok button is pressed
	 * @param isOkPressed
	 */
	public void setIsOkPressed(boolean isOkPressed) {
		this.isOkPressed = isOkPressed;
	}
	
	/**
	 * Get SQL for show view
	 * @return
	 */
	public Vector<String> getData() {
		String keyColumnName = currentTab.getKeyColumnName();
		String sql = null;
		Vector<String> data = new Vector<String>();
		//	Reload
		if (keyColumnName.trim().length() > 0) {
			sql = MLookupFactory.getLookup_TableDirEmbed(Env.getLanguage(Env.getCtx()), keyColumnName, "[?","?]")
			   .replace("[?.?]", "?");
		}
		int noOfRows = currentTab.getRowCount();
		for(int i = 0; i < noOfRows; i++) {
			StringBuffer displayValue = new StringBuffer();
			if (keyColumnName.trim().length() == 0) {
				ArrayList<String> parentColumnNames = currentTab.getParentColumnNames();
				for(Iterator<String> iter = parentColumnNames.iterator(); iter.hasNext();) {
					String columnName = iter.next();
					GridField field = currentTab.getField(columnName);
					if(field.isLookup()) {
						Lookup lookup = field.getLookup();
						if (lookup != null) {
							displayValue = displayValue.append(lookup.getDisplay(currentTab.getValue(i,columnName))).append(" | ");
						} else {
							displayValue = displayValue.append(currentTab.getValue(i,columnName)).append(" | ");
						}
					} else {
						displayValue = displayValue.append(currentTab.getValue(i,columnName)).append(" | ");
					}
				}
			} else {
				final int id = currentTab.getKeyID(i);
				String value = DB.getSQLValueStringEx(null, sql, id);
				if (value != null)
					value = value.replace(" - ", " | ");
				displayValue.append(value);
				// Append ID
				if (displayValue.length() == 0 || CLogMgt.isLevelFine())
				{
					if (displayValue.length() > 0)
						displayValue.append(" | ");
					displayValue.append("<").append(id).append(">");
				}
			}
			//
			data.add(displayValue.toString());
		}
		//	Return
		return data;
	}
	
	/**
	 * Show dialog view
	 */
	public abstract void showDialog();
}
