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
package org.spin.tar.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.compiere.model.Query;
import org.compiere.util.CCache;

/**
 * Model class for shift schedule
 *	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com 
 */
public class MHRShiftSchedule extends X_HR_ShiftSchedule {
	
	public MHRShiftSchedule(Properties ctx, int HR_ShiftSchedule_ID, String trxName) {
		super(ctx, HR_ShiftSchedule_ID, trxName);
	}
	
	public MHRShiftSchedule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1396860053067383823L;
	
	/**	Hash for schedule	*/
	private static CCache<String, MHRShiftSchedule> shiftScheduleCache = new CCache<String, MHRShiftSchedule>(Table_Name, 1000);
	
	/**
	 * Get Schedule for work group from date
	 * @param context
	 * @param workGroupId
	 * @param date
	 * @param transactionName
	 * @return
	 */
	public static MHRShiftSchedule getScheduleFromWorkGroup(Properties context, int workGroupId, Timestamp date, String transactionName) {
		String key = workGroupId + "|" + new SimpleDateFormat("yyyyMMdd").format(date);
		MHRShiftSchedule shiftSchedule = shiftScheduleCache.get(key);
		if(shiftSchedule == null) {
			shiftSchedule = new Query(context, Table_Name, COLUMNNAME_HR_WorkGroup_ID + " = ? "
					+ "AND EXISTS(SELECT 1 "
					+ "					FROM HR_Period p "
					+ "					WHERE p.HR_Period_ID = HR_ShiftSchedule.HR_Period_ID "
					+ "					AND ? BETWEEN p.StartDate AND p.EndDate)", transactionName)
				.setParameters(workGroupId, date)
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.first();
		}
		if(shiftSchedule != null) {
			shiftScheduleCache.put(key, shiftSchedule);
		}
		//	Default
		return shiftSchedule;
	}

	@Override
	public String toString() {
		return "MHRShiftSchedule [getDescription()=" + getDescription() + ", getHR_Period_ID()=" + getHR_Period_ID()
				+ ", getHR_ShiftSchedule_ID()=" + getHR_ShiftSchedule_ID() + ", getHR_WorkGroup_ID()="
				+ getHR_WorkGroup_ID() + ", getHR_WorkShift_ID()=" + getHR_WorkShift_ID() + "]";
	}
}
