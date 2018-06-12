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
 * Copyright (C) 2003-2015 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.spin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.print.ReportEngine;

/**
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 * 		<a href="https://github.com/adempiere/adempiere/issues/1400">
 * 		@see FR [ 1400 ] Dynamic report export</a>
 */
public class ReportExport {
		
	public ReportExport(Properties ctx, ReportEngine reportEngine) {
		this.ctx = ctx;
		this.reportEngine = reportEngine;
		//	Load Default Export Format
		loadDefaultFormat();
	}
	
	/**	Report Engine	*/
	private ReportEngine reportEngine;
	/**	Context			*/
	private Properties ctx;
	/**	Export Format List	*/
	private List<AbstractExportFormat> exportFormatList = new ArrayList<AbstractExportFormat>();
	
	/**
	 * Get Report Engine
	 * @return
	 */
	public ReportEngine getReportEngine() {
		return reportEngine;
	}
	
	/**
	 * Get Context
	 * @return
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * Add Export format to list
	 * @param supportedFormat
	 */
	public void addExportFormat(AbstractExportFormat supportedFormat) {
		if(supportedFormat == null) {
			return;
		}
		//	Add
		exportFormatList.add(supportedFormat);
	}
	
	/**
	 * Get Export format list
	 * @return
	 */
	public List<AbstractExportFormat> getExportFormatList() {
		return exportFormatList;
	}
	
	/**
	 * Load default formats
	 */
	private void loadDefaultFormat() {
		//	PS
		addExportFormat(new ExportFormatPS(getCtx(), getReportEngine()));
		//	XML
		addExportFormat(new ExportFormatXML(getCtx(), getReportEngine()));
		//	PDF
		addExportFormat(new ExportFormatPDF(getCtx(), getReportEngine()));
		//	HTML
		addExportFormat(new ExportFormatHTML(getCtx(), getReportEngine()));
		//	TXT
		addExportFormat(new ExportFormatTXT(getCtx(), getReportEngine()));
		//	SSV
		addExportFormat(new ExportFormatSSV(getCtx(), getReportEngine()));
		//	CSV
		addExportFormat(new ExportFormatCSV(getCtx(), getReportEngine()));
		//	XLS
		addExportFormat(new ExportFormatXLS(getCtx(), getReportEngine()));
		//	XLSX
		addExportFormat(new ExportFormatXLSX(getCtx(), getReportEngine()));
		//	ARXML
		addExportFormat(new ExportFormatARXML(getCtx(), getReportEngine()));
	}
	
}	//	AbstractReportExport
