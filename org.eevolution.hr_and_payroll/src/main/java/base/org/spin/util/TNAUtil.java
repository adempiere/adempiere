/*************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                              *
 * This program is free software; you can redistribute it and/or modify it    		 *
 * under the terms version 2 or later of the GNU General Public License as published *
 * by the Free Software Foundation. This program is distributed in the hope   		 *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 		 *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           		 *
 * See the GNU General Public License for more details.                       		 *
 * You should have received a copy of the GNU General Public License along    		 *
 * with this program; if not, write to the Free Software Foundation, Inc.,    		 *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     		 *
 * For the text or an alternative of this public license, you may reach us    		 *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved. *
 * Contributor(s): Yamel Senih www.erpya.com				  		                 *
 *************************************************************************************/
package org.spin.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Util;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.MHRWorkShift;
import org.spin.model.I_HR_Incidence;

/**
 * 	Class added for helper method of incidence
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1870>
 * 		@see FR [ 1870 ] Add Calulation for Attendance Record</a>
 */
public class TNAUtil {
	
	/**
	 * Get Incidence Sum: get sum of all incidences completed on period from-to
	 * @param ctx
	 * @param conceptValue
	 * @param workShiftValue
	 * @param partnerId
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static double getIncidenceSum(
			Properties ctx,
			String conceptValue,
			String workShiftValue,
			int partnerId,
			Timestamp from,
			Timestamp to, 
			String trxName) {
		//	Validate concept
		MHRConcept concept = MHRConcept.getByValue(ctx, conceptValue, trxName);
		if (concept == null)
			return 0.0;
		int workShiftId = 0;
		if(!Util.isEmpty(workShiftValue)) {
			MHRWorkShift workShift = MHRWorkShift.getByValue(ctx, workShiftValue, trxName);
			workShiftId = workShift.getHR_WorkShift_ID();
		}
		//	
		return getIncidenceSum(ctx, concept.getHR_Concept_ID(), workShiftId, partnerId, from, to, trxName);
	}
	
	/**
	 * Get Incidence Sum: get sum of all incidences completed on period from-to
	 * @param ctx
	 * @param conceptId
	 * @param workShiftId
	 * @param partnerId
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static double getIncidenceSum(
			Properties ctx,
			int conceptId,
			int workShiftId,
			int partnerId,
			Timestamp from,
			Timestamp to, 
			String trxName) {
		//
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer whereClause = new StringBuffer();
		//check concept
		whereClause.append(I_HR_Incidence.COLUMNNAME_HR_Concept_ID + "=?");
		params.add(conceptId);
		//check partner
		whereClause.append(" AND " + I_HR_Incidence.COLUMNNAME_C_BPartner_ID  + "=?");
		params.add(partnerId);
		//Adding dates 
		whereClause.append(" AND ").append(I_HR_Incidence.COLUMNNAME_DateDoc).append(" BETWEEN ? AND ?");
		params.add(from);
		params.add(to);
		//	Document Status
		whereClause.append(" AND ").append(I_HR_Incidence.COLUMNNAME_DocStatus).append(" IN('CO', 'CL')");
		//check process and payroll
		if(workShiftId > 0) {
			whereClause.append(" AND EXISTS (SELECT 1 FROM HR_AttendanceBatch b"
					+" WHERE HR_Incidence.HR_AttendanceBatch_ID = b.HR_AttendanceBatch_ID"
					+" AND b.HR_WorkShift_ID=?)");

			params.add(workShiftId);
		}
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(Qty), SUM(Amt), 0) FROM ").append(I_HR_Incidence.Table_Name)
					.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(trxName, sql.toString(), params);
		return value.doubleValue();
	}
}
