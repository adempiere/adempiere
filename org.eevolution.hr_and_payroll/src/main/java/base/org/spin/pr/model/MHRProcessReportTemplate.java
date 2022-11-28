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

import org.adempiere.core.domains.models.X_HR_ProcessReportTemplate;
import org.compiere.util.CCache;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Process Report Template for Payroll Movements
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1558">
 * 		@see FR [ 1558 ] Add Payroll Process Report Template</a>
 *
 */
public class MHRProcessReportTemplate extends X_HR_ProcessReportTemplate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7984464436153212633L;

	public MHRProcessReportTemplate(Properties ctx, int HR_ProcessReportTemplate_ID, String trxName) {
		super(ctx, HR_ProcessReportTemplate_ID, trxName);
	}
	
	public MHRProcessReportTemplate(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/** Cache */
	private static CCache<Integer, MHRProcessReportTemplate> s_cache = new CCache<Integer, MHRProcessReportTemplate>(Table_Name, 100);
	
	/**
	 * Get Optional Cache
	 * @param ctx
	 * @param p_LVE_HR_ProcessReportTemplate_ID
	 * @return
	 * @return MLVEHRProcessReportTemplate
	 */
	public static MHRProcessReportTemplate get(Properties ctx, int p_LVE_HR_ProcessReportTemplate_ID) {
		//	Valid ID
		if(p_LVE_HR_ProcessReportTemplate_ID == 0)
			return null;
		//	Get from Cache
		MHRProcessReportTemplate report = s_cache.get(p_LVE_HR_ProcessReportTemplate_ID);
		if (report != null) {
			return report;
		}
		//	Get DB
		report = new MHRProcessReportTemplate(ctx, p_LVE_HR_ProcessReportTemplate_ID, null);
		// Put in Cache
		s_cache.put(report.get_ID(), report);
			//	Return
		return report;
	}
	
	@Override
	public String toString() {
		return getHR_ProcessReportTemplate_ID() + " - " + getName();
	}
}
