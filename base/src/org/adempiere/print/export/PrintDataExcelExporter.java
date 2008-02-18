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
package org.adempiere.print.export;

import java.sql.Timestamp;

import org.adempiere.impexp.AbstractExcelExporter;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;

/**
 * Export PrintData to Excel (XLS) file
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class PrintDataExcelExporter
extends AbstractExcelExporter
{
	private PrintData m_printData;
	private MPrintFormat m_printFormat;

	public PrintDataExcelExporter(PrintData printData, MPrintFormat printFormat) {
		super();
		this.m_printData = printData;
		this.m_printFormat = printFormat;
	}
	
	@Override
	protected int getColumnCount() {
		return m_printFormat.getItemCount();
	}

	private PrintDataElement getPDE(int row, int col) {
		if (m_printData.getRowIndex() != row)
			m_printData.setRowIndex(row);
		//
		MPrintFormatItem item = m_printFormat.getItem(col);
		int AD_Column_ID = item.getAD_Column_ID();
		Object obj = null;
		if (AD_Column_ID > 0)
			obj = m_printData.getNode(Integer.valueOf(AD_Column_ID));
		if (obj != null && obj instanceof PrintDataElement) {
			return (PrintDataElement)obj;
		}
		return null;
	}
	@Override
	protected int getDisplayType(int row, int col) {
		PrintDataElement pde = getPDE(row, col);
		if (pde != null) {
			return pde.getDisplayType();
		}
		return -1;
		//
	}
	
	@Override
	protected Object getValueAt(int row, int col) {
		PrintDataElement pde = getPDE(row, col);
		Object value = null;
		if (pde == null)
			;
		else if (pde.isDate()) {
			value = (Timestamp)pde.getValue();
		}
		else if (pde.isNumeric()) {
			Object o = pde.getValue();
			if (o instanceof Number) {
				value = ((Number)o).doubleValue();
			}
		}
		else if (pde.isYesNo()) {
			value = pde.getValue();
		}
		else if (pde.isPKey()) {
			value = pde.getValueAsString();
		}
		else {
			value = pde.getValueDisplay(getLanguage());
		}
		//
		return value;
	}

	@Override
	protected String getHeaderName(int col) {
		return m_printFormat.getItem(col).getPrintName(getLanguage());
	}

	@Override
	protected int getRowCount() {
		return m_printData.getRowCount();
	}

	@Override
	protected boolean isColumnPrinted(int col) {
		MPrintFormatItem item = m_printFormat.getItem(col);
		return item.isPrinted();
	}

	@Override
	protected boolean isPageBreak(int row, int col) {
		PrintDataElement pde = getPDE(row, col);
		return pde != null ? pde.isPageBreak() : false;
	}

	@Override
	protected void setCurrentRow(int row) {
		m_printData.setRowIndex(row);
	}

	@Override
	protected boolean isFunctionRow() {
		return m_printData.isFunctionRow();
	}
}
