/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
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
package org.adempiere.impexp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DisplayType;
import org.compiere.util.Msg;
import org.compiere.util.Util;

/**
 * Export excel from ArrayList of data
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 *
 */
public class ArrayExcelExporter extends AbstractExcelExporter {
	private Properties context = null;
	private ArrayList<ArrayList<Object>> data = null;

	/**
	 * Export Array Exporter
	 * @param context
	 * @param data
	 * @param dataIncludeHeader
	 */
	public ArrayExcelExporter(Properties context, ArrayList<ArrayList<Object>> data, Boolean dataIncludeHeader) {
		super();
		this.context = context;
		this.data = data;
		super.dataIncludeHeader = dataIncludeHeader;
	}
	
	@Override
	public Properties getCtx() {
		return context;
	}
	
	@Override
	public int getColumnCount() {
		return data.get(0).size();
	}

	@Override
	public int getDisplayType(int row, int col) {
		ArrayList<Object> dataRow = data.get(row);
		Object value = dataRow.get(col);
		if (value == null)
			;
		else if (value instanceof Timestamp) {
			return DisplayType.Date;
			// TODO: handle DateTime
		}
		else if (value instanceof Number) {
			if (value instanceof Integer) {
				return DisplayType.Integer;
			}
			else {
				return DisplayType.Number;
			}
		}
		else if (value instanceof Boolean) {
			return DisplayType.YesNo;
		}
		else {
			return DisplayType.String;
		}
		return -1;
	}

	@Override
	public String getHeaderName(int col) {
		Object o = data.get(0).get(col);
		String name = o != null ? o.toString() : null;
		String nameTrl = Msg.translate(getLanguage(), name);
		if (Util.isEmpty(nameTrl))
			nameTrl = name;
		return nameTrl;
	}
	
	@Override
	public String getFormatPattern(int col) {
		return null;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		ArrayList<Object> dataRow = data.get(row);
		Object value = dataRow.get(col);
		return value;
	}

	@Override
	public boolean isColumnPrinted(int col) {
		return true;
	}

	@Override
	public boolean isFunctionRow() {
		return false;
	}

	@Override
	public boolean isPageBreak(int row, int col) {
		return false;
	}

	@Override
	protected void setCurrentRow(int row) {
	}
}
