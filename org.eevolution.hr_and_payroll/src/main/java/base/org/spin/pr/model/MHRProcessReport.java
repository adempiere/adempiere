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
 * Contributor(s): Yamel Senih www.erpya.com                                  *
 *****************************************************************************/
package org.spin.pr.model;

import java.io.File;
import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Util;
import org.spin.hr.util.AbstractPayrollReportExport;
import org.spin.hr.util.GenericPayrollExport;

/**
 * Process Report for Payroll Movements: it allows create dynamic reports from templates of iReport and ADempiere
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1538">
 * 		@see FR [ 1538 ] Create Payroll Process Report Elements</a>
 *
 */
public class MHRProcessReport extends X_HR_ProcessReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7984464436153212633L;

	public MHRProcessReport(Properties ctx, int HR_ProcessReport_ID, String trxName) {
		super(ctx, HR_ProcessReport_ID, trxName);
	}
	
	public MHRProcessReport(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Cache */
	private static CCache<Integer, MHRProcessReport> s_cache = new CCache<Integer, MHRProcessReport>(Table_Name, 100);
	
	/**	Lines	*/
	private List<MHRProcessReportLine> lines = null;
	/**	Export class	*/
	private AbstractPayrollReportExport reportExport = null;
	
	/**
	 * Get Optional Cache
	 * @param ctx
	 * @param p_LVE_HR_ProcessReport_ID
	 * @return
	 * @return MLVEHRProcessReport
	 */
	public static MHRProcessReport get(Properties ctx, int p_LVE_HR_ProcessReport_ID) {
		//	Valid ID
		if(p_LVE_HR_ProcessReport_ID == 0)
			return null;
		//	Get from Cache
		MHRProcessReport report = s_cache.get(p_LVE_HR_ProcessReport_ID);
		if (report != null) {
			return report;
		}
		//	Get DB
		report = new MHRProcessReport(ctx, p_LVE_HR_ProcessReport_ID, null);
		// Put in Cache
		s_cache.put(report.get_ID(), report);
			//	Return
		return report;
	}
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public List<MHRProcessReportLine> getLines (boolean requery, String sqlWhere) {
		if (lines  != null && !requery) {
			return lines;
		}
		lines = new Query(getCtx(), 
				MHRProcessReportLine.Table_Name, "HR_ProcessReport_ID=?", get_TrxName())
		.setParameters(getHR_ProcessReport_ID())
		.list();

		return lines;
	}	//	getLines
	
	/**
	 * Copy Lines of Process Report
	 * @param from
	 * @param to
	 * @return
	 * @return int
	 */
	public int copyFrom (MHRProcessReport from, MHRProcessReport to){
		
		int count = 0;
		List<MHRProcessReportLine> concepts;
		
		//	Lines from payroll to copy
		concepts = from.getLines(false,null);
		
		//	Loop from process report to copy
		for (MHRProcessReportLine line : concepts) {
			MHRProcessReportLine processReportLine =
					new MHRProcessReportLine(getCtx(), 0, get_TrxName());
			//	Copy values
			PO.copyValues(line,processReportLine, line.getAD_Client_ID(), line.getAD_Org_ID());

			//	Set parent
			processReportLine.setHR_ProcessReport_ID(getHR_ProcessReport_ID());
			//	Save line
			processReportLine.saveEx();
			
			count++;			
		}
		return count;
	}
	
	/**
	 * Get Class from device type, used for handler
	 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
	 * @return
	 * @return Class<?>
	 */
	private Class<?> getHandlerClass() {
		String className = getFileExportClass();
		//	Validate null values
		if(Util.isEmpty(className)) {
			return null;
		}
		try {
			Class<?> clazz = Class.forName(className);
			//	Make sure that it is a PO class
			Class<?> superClazz = clazz.getSuperclass();
			//	Validate super class
			while (superClazz != null) {
				if (superClazz == AbstractPayrollReportExport.class) {
					log.fine("Use: " + className);
					return clazz;
				}
				//	Get Super Class
				superClazz = superClazz.getSuperclass();
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		//	
		log.finest("Not found: " + className);
		return null;
	}	//	getHandlerClass
	
	/**
	 * Load class for export
	 * @throws Exception
	 */
	private void loadExportClass() throws Exception {
		if(reportExport != null) {
			return;
		}
		//	Load it
		//	Get class from parent
		Class<?> clazz = getHandlerClass();
		//	Not yet implemented
		if (clazz == null) {
			log.log(Level.INFO, "Using Standard Report Export");
			reportExport = new GenericPayrollExport(getCtx());
			return;
		}
		//	
		Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{Properties.class});
		//	new instance
		reportExport = (AbstractPayrollReportExport) constructor.newInstance(new Object[] {getCtx()});
	}
	
	/**
	 * Get Report export instance
	 * @return
	 */
	public AbstractPayrollReportExport getPayrollReportExport() throws Exception {
		loadExportClass();
		return reportExport;
	}
	
	/**
	 * Load Default class
	 * @throws Exception 
	 */
	public boolean exportToFile(File file, List<X_RV_HR_ProcessDetail> detail) throws Exception {
		loadExportClass();
		//	
		if(reportExport != null) {
			reportExport.setDetail(detail);
			return reportExport.exportToFile(file);
		}
		//	default
		return false;
	}
	
	@Override
	public String toString() {
		return getHR_ProcessReport_ID() + " - " + getName();
	}
}
