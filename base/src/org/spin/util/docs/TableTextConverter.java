/*************************************************************************************
 * Product: Spin-Suite (Mobile Suite)                       		                 *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util.docs;

import java.util.ArrayList;
import java.util.List;

import org.compiere.util.Util;

/**
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * Table object for text converter
 * @see: https://github.com/adempiere/adempiere/issues/1934
 * For formst reference use: http://www.sphinx-doc.org/en/master/usage/restructuredtext/basics.html
 */
public class TableTextConverter {

	/**
	 * Header Column Names
	 */
	public TableTextConverter() {
		rows = new ArrayList<>();
	}
	
	/**
	 * Constructor with column names
	 * @param columnNames
	 */
	public TableTextConverter(List<String> columnNames) {
		this();
		if(columnNames != null
				&& columnNames.size() > 0) {
			addRow(columnNames);
		}
	}
	
	/**	Rows	*/
	private List<List<String>> rows;
	
	/**
	 * Add Row
	 * @param row
	 */
	public void addRow(List<String> row) {
		rows.add(row);
	}
	
	/**
	 * Get Row
	 * @param index
	 * @return
	 */
	public List<String> getRow(int index) {
		if(index >= rows.size()) {
			return null;
		}
		//	Default
		return rows.get(index);
	}
	
	/**
	 * Get all rows
	 * @return
	 */
	public List<List<String>> getRows() {
		return rows;
	}
	
	/**
	 * Get Max Column Size
	 * @param columnIndex
	 * @return
	 */
	public int getMaxColumnSize(int columnIndex) {
		if(rows.size() == 0) {
			return -1;
		}
		int maxSize = 0;
		//	
		for(List<String> row : rows) {
			if(columnIndex >= row.size()) {
				continue;
			}
			String columnText = row.get(columnIndex);
			if(!Util.isEmpty(columnText)) {
				if(columnText.length() > maxSize) {
					maxSize = columnText.length();
				}
			}
		}
		//	
		return maxSize;
	}
	
}
