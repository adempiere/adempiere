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
 * Copyright (C) 2003-2014 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.pr.process;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.compiere.util.Ini;
import org.spin.hr.util.AbstractPayrollReportExport;
import org.spin.pr.model.MHRProcessReport;
import org.adempiere.core.domains.models.X_RV_HR_ProcessDetail;
import org.zkoss.zul.Filedownload;


/**
 * Process Report for Payroll Movements: show reports from movements and template
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1539">
 * 		@see FR [ 1539 ] Add Process for Reporting</a>
 *
 */
public class WPayrollProcessReport extends PayrollProcessReport {	
	/**
	 * Export to file
	 * @return String
	 */
	public String exportToFile(MHRProcessReport payrollReport) throws Exception {
		List<X_RV_HR_ProcessDetail> detail = loadDetail(payrollReport);
		if (Ini.isClient()) {
			return "Ok";
		}
		//	Valid Array
		if(detail == null
				|| detail.size() == 0) {
			return "Ok";
		}
		//	
		AbstractPayrollReportExport reportExport = payrollReport.getPayrollReportExport();
		//	if null
		if(reportExport == null) {
			return "Ok";
		}
		File exportFile = reportExport.createFile();
		//	Export
		boolean isExported = payrollReport.exportToFile(exportFile, detail);
		//	
		String result = "Ok";
		if (isExported && exportFile != null) {
			
			Filedownload.save(new FileInputStream(exportFile), "plain/text", reportExport.getCompleteFileName());
			//	Show Path
			result = "@Exported@ : " + exportFile.getPath();
		}
		//	Default
		return result;
	}

}
