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
import java.util.Date;

import javax.print.attribute.standard.MediaSizeName;

import org.adempiere.impexp.AbstractExcelExporter;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.compiere.print.MPrintFormat;
import org.compiere.print.MPrintFormatItem;
import org.compiere.print.MPrintPaper;
import org.compiere.print.PrintData;
import org.compiere.print.PrintDataElement;

/**
 * Export PrintData to Excel (XLS) file
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 * 			<li>BF [ 1939010 ] Excel Export ERROR - java.sql.Date - integrated Mario Grigioni's fix
 * 			<li>BF [ 1974309 ] Exporting a report to XLS is not setting page format
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
	public PrintDataExcelExporter(PrintData printData, MPrintFormat printFormat, boolean isXLSX) {
		this(printData, printFormat);
		this.isXLSX = isXLSX;
	}

	
	@Override
	public int getColumnCount() {
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
	public int getDisplayType(int row, int col) {
		PrintDataElement pde = getPDE(row, col);
		if (pde != null) {
			return pde.getDisplayType();
		}
		return -1;
		//
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		PrintDataElement pde = getPDE(row, col);
		Object value = null;
		if (pde == null)
			;
		else if (pde.isDate()) {
			Object o = pde.getValue();
			if (o instanceof Date)
				value = new Timestamp(((Date)o).getTime());
			else
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
	public String getHeaderName(int col) {
		return m_printFormat.getItem(col).getPrintName(getLanguage());
	}

	@Override
	public String getFormatPattern(int col) {
		return m_printFormat.getItem(col).getFormatPattern();
	}

	@Override
	public int getRowCount() {
		return m_printData.getRowCount();
	}

	@Override
	public boolean isColumnPrinted(int col) {
		MPrintFormatItem item = m_printFormat.getItem(col);
		return item.isPrinted();
	}

	@Override
	public boolean isPageBreak(int row, int col) {
		PrintDataElement pde = getPDE(row, col);
		return pde != null ? pde.isPageBreak() : false;
	}

	@Override
	protected void setCurrentRow(int row) {
		m_printData.setRowIndex(row);
	}

	@Override
	public boolean isFunctionRow() {
		return m_printData.isFunctionRow();
	}

	@Override
	protected void formatPage(Sheet sheet) {
		super.formatPage(sheet);
		MPrintPaper paper = MPrintPaper.get(this.m_printFormat.getAD_PrintPaper_ID());
		//
		// Set paper size:
		short paperSize = -1;
		MediaSizeName mediaSizeName = paper.getMediaSize().getMediaSizeName();
		if (MediaSizeName.NA_LETTER.equals(mediaSizeName)) {
			paperSize = PrintSetup.LETTER_PAPERSIZE;
		}
		else if (MediaSizeName.NA_LEGAL.equals(mediaSizeName)) {
		  	paperSize = PrintSetup.LEGAL_PAPERSIZE;
		}
		else if (MediaSizeName.EXECUTIVE.equals(mediaSizeName)) {
			paperSize = PrintSetup.EXECUTIVE_PAPERSIZE;
		}
		else if (MediaSizeName.ISO_A4.equals(mediaSizeName)) {
			paperSize = PrintSetup.A4_PAPERSIZE;
		}
		else if (MediaSizeName.ISO_A5.equals(mediaSizeName)) {
			paperSize = PrintSetup.A5_PAPERSIZE;
		}
		else if (MediaSizeName.NA_NUMBER_10_ENVELOPE.equals(mediaSizeName)) {
			paperSize = PrintSetup.ENVELOPE_10_PAPERSIZE;
		}
//		else if (MediaSizeName..equals(mediaSizeName)) {
//			paperSize = HSSFPrintSetup.ENVELOPE_DL_PAPERSIZE;
//		}
//		else if (MediaSizeName..equals(mediaSizeName)) {
//			paperSize = HSSFPrintSetup.ENVELOPE_CS_PAPERSIZE;
//		}
		else if (MediaSizeName.MONARCH_ENVELOPE.equals(mediaSizeName)) {
			paperSize = HSSFPrintSetup.ENVELOPE_MONARCH_PAPERSIZE;
		}
		if (paperSize != -1) {
			sheet.getPrintSetup().setPaperSize(paperSize);
		}
		//
		// Set Landscape/Portrait:
		sheet.getPrintSetup().setLandscape(paper.isLandscape());
		//
		// Set Paper Margin:
		sheet.setMargin(Sheet.TopMargin, ((double)paper.getMarginTop()) /72);
		sheet.setMargin(Sheet.RightMargin, ((double)paper.getMarginRight()) / 72);
		sheet.setMargin(Sheet.LeftMargin, ((double)paper.getMarginLeft()) / 72);
		sheet.setMargin(Sheet.BottomMargin, ((double)paper.getMarginBottom()) / 72);
		//
	}
	
	
}
