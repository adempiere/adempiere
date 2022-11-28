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

import org.adempiere.core.domains.models.X_HR_ProcessReportLine;
import org.eevolution.hr.model.MHRConcept;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Process Report for Payroll Movements: it allows create dynamic reports from templates of iReport and ADempiere
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1538">
 * 		@see FR [ 1538 ] Create Payroll Process Report Elements</a>
 *
 */
public class MHRProcessReportLine extends X_HR_ProcessReportLine {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3201369770249345047L;
	
	public MHRProcessReportLine(Properties ctx, int HR_ProcessReportLine_ID, String trxName) {
		super(ctx, HR_ProcessReportLine_ID, trxName);
	}

	public MHRProcessReportLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	@Override
	protected boolean beforeSave(boolean newRecord) {
		super.beforeSave(newRecord);
		if(getPrintName() == null){
			MHRConcept concept = MHRConcept.getById(getCtx(), getHR_Concept_ID() , get_TrxName());
			if(concept != null) {
				setPrintName(concept.getName());
			}
		}
		return true;
	}//	End beforeSave
	
}
