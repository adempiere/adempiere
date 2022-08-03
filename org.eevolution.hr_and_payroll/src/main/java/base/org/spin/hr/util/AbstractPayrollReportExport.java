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
package org.spin.hr.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.compiere.util.Util;
import org.spin.pr.model.X_RV_HR_ProcessDetail;

/**
 * 	Abstract class implementation for export files from Payroll Process Report
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1539">
 * 		@see FR [ 1539 ] Add Process for Reporting</a>
 */
public abstract class AbstractPayrollReportExport {
	
	public AbstractPayrollReportExport(Properties ctx) {
		this.ctx = ctx;
	}
	
	/**	Context	*/
	private Properties ctx;
	/**	Detail of concepts	*/
	private List<X_RV_HR_ProcessDetail> detail;
	
	/**
	 * Set Detail fro export
	 * @param detail
	 */
	public void setDetail(List<X_RV_HR_ProcessDetail> detail) {
		this.detail = detail;
	}
	
	/**
	 * Get Detail of Movements
	 * @return
	 */
	public List<X_RV_HR_ProcessDetail> getDetail() {
		return detail;
	}
	
	/**
	 * Get Context
	 * @return
	 */
	public Properties getCtx() {
		return ctx;
	}
	
	/**
	 * Get a list of extension supported
	 * @return
	 */
	public String getExtension() {
		return "txt";
	}
	
	/**
	 * Get a File Name
	 * @return
	 */
	public String getFileName() {
		return "PayrollReportExport";
	}
	
	/**
	 * Get Complete file name (File Name + extension)
	 * @return
	 */
	public String getCompleteFileName() {
		if(Util.isEmpty(getFileName())
				|| Util.isEmpty(getExtension())) {
			return null;
		}
		//	
		return getFileName() + "." + getExtension();
	}
	
	
	/**
	 * Create File
	 * @return
	 * @throws IOException
	 */
	public File createFile() throws IOException {
		return File.createTempFile(getFileName(), "." + getExtension());
	}
	
	/**
	 * Export to file
	 * @param file
	 * @return
	 */
	public boolean exportToFile(File file) throws Exception {
		//	
		return true;
	}
}	//	PaymentExport
