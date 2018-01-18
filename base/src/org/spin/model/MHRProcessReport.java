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
package org.spin.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CCache;

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
	
	@Override
	public String toString() {
		return getHR_ProcessReport_ID() + " - " + getName();
	}
}
