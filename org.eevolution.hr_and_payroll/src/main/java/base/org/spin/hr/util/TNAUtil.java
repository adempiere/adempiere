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
package org.spin.hr.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.core.api.I_HR_Leave;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHRConcept;
import org.eevolution.hr.model.MHRLeave;
import org.eevolution.hr.model.MHRLeaveType;
import org.eevolution.hr.model.MHRWorkShift;

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
		whereClause.append("HR_Concept_ID=?");
		params.add(conceptId);
		//check partner
		whereClause.append(" AND C_BPartner_ID =?");
		params.add(partnerId);
		//Adding dates 
		whereClause.append(" AND ").append("DateDoc BETWEEN ? AND ?");
		params.add(from);
		params.add(to);
		//	Document Status
		whereClause.append(" AND ").append("DocStatus IN('CO', 'CL')");
		//check process and payroll
		if(workShiftId > 0) {
			whereClause.append(" AND EXISTS (SELECT 1 FROM HR_AttendanceBatch b"
					+" WHERE HR_Incidence.HR_AttendanceBatch_ID = b.HR_AttendanceBatch_ID"
					+" AND b.HR_WorkShift_ID=?)");

			params.add(workShiftId);
		}
		//
		StringBuffer sql = new StringBuffer("SELECT COALESCE(SUM(Qty), SUM(Amt), 0) FROM HR_Incidence")
					.append(" WHERE ").append(whereClause);
		BigDecimal value = DB.getSQLValueBDEx(trxName, sql.toString(), params);
		return value.doubleValue();
	}
	
	/**
	 * Get leave time between two dates
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static long getLeaveTimeBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime, String trxName) {
		List<MHRLeave> leaveList = getLeaveListBetween(ctx, businessPartnerId, leaveTypeValue, from, to, trxName);
		AtomicLong timeBetween = new AtomicLong();
		AtomicLong overlapedTime = new AtomicLong();
		AtomicReference<Timestamp> startDate = new AtomicReference<>();
		AtomicReference<Timestamp> endDate = new AtomicReference<>();
		leaveList.stream()
		.sorted(Comparator.comparing(MHRLeave::getStartDate))
		.forEach(leave -> {
			timeBetween.addAndGet(TimeUtil.getMillisecondsBetween(leave.getStartDate(), leave.getEndDate()));
			Timestamp lastStartDate = startDate.getAndSet(leave.getStartDate());
			Timestamp lastEndDate = endDate.getAndSet(leave.getEndDate());
			//	Validate
			if(lastStartDate != null
					&& lastEndDate != null) {
				if(TimeUtil.isValid(lastStartDate, lastEndDate, leave.getStartDate())) {
					Timestamp endDateForOverlap = lastEndDate;
					if(lastEndDate.getTime() > leave.getEndDate().getTime()) {
						endDateForOverlap = leave.getEndDate();
					}
					overlapedTime.addAndGet(TimeUtil.getMillisecondsBetween(leave.getStartDate(), endDateForOverlap));
				}
			}
		});
		//	Return time
		if(excludeOverlapedTime) {
			return timeBetween.get() - overlapedTime.get();
		}
		return timeBetween.get();
	}
	
	/**
	 * Get first leave between two dates
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static MHRLeave getFirstLeaveBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, String trxName) {
		Optional<MHRLeave> leave = getLeaveListBetween(ctx, businessPartnerId, leaveTypeValue, from, to, trxName)
				.stream()
				.sorted(Comparator.comparing(MHRLeave::getStartDate))
				.findFirst();
		//	Get if exist
		if(leave.isPresent()) {
			return leave.get();
		}
		return null;
	}
	
	/**
	 * Get last leave between two dates
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static MHRLeave getLastLeaveBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, String trxName) {
		Optional<MHRLeave> leave = getLeaveListBetween(ctx, businessPartnerId, leaveTypeValue, from, to, trxName)
				.stream()
				.sorted(Comparator.comparing(MHRLeave::getEndDate).reversed())
				.findFirst();
		//	Get if exist
		if(leave.isPresent()) {
			return leave.get();
		}
		return null;
	}
	
	/**
	 * Get Start Date from first leave between
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static Timestamp getLeaveStartDateBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, String trxName) {
		MHRLeave leave = getFirstLeaveBetween(ctx, businessPartnerId, leaveTypeValue, from, to, trxName);
		if(leave != null) {
			return leave.getStartDate();
		}
		//	
		return null;
	}
	
	/**
	 * Get End Date from last leave between
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static Timestamp getLeaveEndDateBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, String trxName) {
		MHRLeave leave = getLastLeaveBetween(ctx, businessPartnerId, leaveTypeValue, from, to, trxName);
		if(leave != null) {
			return leave.getEndDate();
		}
		//	
		return null;
	}
	
	/**
	 * Get Hours of leave it can returned a double
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param excludeOverlapedTime
	 * @param trxName
	 * @return
	 */
	public static double getLeaveHoursBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime, String trxName) {
		return TimeUtil.getHoursFromDuration(getLeaveTimeBetween(ctx, businessPartnerId, leaveTypeValue, from, to, excludeOverlapedTime, trxName));
	}
	
	/**
	 * Get Leave minutes
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param excludeOverlapedTime
	 * @param trxName
	 * @return
	 */
	public static int getLeaveMinutesBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime, String trxName) {
		return TimeUtil.getMinutesFromDuration(getLeaveTimeBetween(ctx, businessPartnerId, leaveTypeValue, from, to, excludeOverlapedTime, trxName));
	}
	
	/**
	 * Get days from leave
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param excludeOverlapedTime
	 * @param trxName
	 * @return
	 */
	public static int getLeaveDaysBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime, String trxName) {
		return TimeUtil.getDaysFromDuration(getLeaveTimeBetween(ctx, businessPartnerId, leaveTypeValue, from, to, excludeOverlapedTime, trxName));
	}
	
	/**
	 * Verify is a leave is encashment
	 * @param ctx
	 * @param leaveTypeValue
	 * @param trxName
	 * @return
	 */
	public static boolean isLeaveAllowedEncashment(Properties ctx, String leaveTypeValue, String trxName) {
		MHRLeaveType leaveType = MHRLeaveType.getByValue(ctx, leaveTypeValue, trxName);
		return leaveType != null && leaveType.isAllowedEncashment();
	}
	
	/**
	 * Verify is a leave is encashment
	 * @param leaveTypeValue
	 * @return
	 */
	public static boolean isLeaveAllowedEncashment(String leaveTypeValue) {
		return isLeaveAllowedEncashment(Env.getCtx(), leaveTypeValue, null);
	}
	
	/**
	 * Get Leave list between two dates
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param trxName
	 * @return
	 */
	public static List<MHRLeave> getLeaveListBetween(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp from, Timestamp to, String trxName) {
		List<Object> parameters = new ArrayList<>();
		parameters.add(businessPartnerId);
		StringBuffer whereClause = new StringBuffer("DocStatus IN('CO') AND C_BPartner_ID = ?");
		if(from != null
				&& to != null) {
			whereClause.append(" AND StartDate >= ? AND EndDate <= ?");
			parameters.add(from);
			parameters.add(to);
		} else if(from != null) {
			whereClause.append(" AND EndDate >= ?");
			parameters.add(from);
		} else {
			whereClause.append(" AND EndDate >= ?");
			parameters.add(to);
		}
		if(!Util.isEmpty(leaveTypeValue)) {
			MHRLeaveType leaveType = MHRLeaveType.getByValue(ctx, leaveTypeValue, trxName);
			if(leaveType != null) {
				whereClause.append(" AND " + I_HR_Leave.COLUMNNAME_HR_LeaveType_ID + " = ?");
				parameters.add(leaveType.getHR_LeaveType_ID());
			}
		}
		return new Query(ctx, I_HR_Leave.Table_Name, whereClause.toString(), trxName)
				.setParameters(parameters)
				.setOnlyActiveRecords(true)
				.setOrderBy(I_HR_Leave.COLUMNNAME_StartDate)
				.<MHRLeave>list();
	}
	
	/**
	 * Get Leave list for a attendance time
	 * A example for leave from 9:30 AM to 12:00 M
	 * <li>[(8:00)-----------------------------------------------------------------------------(12:00)]
	 * <li>[(Leave)-------(9:30)---------------------------------------------------------------(12:00)]
	 * <li>[(Attendance)--(9:30)-----------------(10:00)----------------------------------------------]
	 * <li>Range for evaluate: From 8:00 ~ 12:00
	 * <li>Range for create incidence: 8:30 ~ 12:00
	 * <li>Attendance Time is: (9:30 - 8:30) = 1 Hour
	 * @param ctx
	 * @param businessPartnerId
	 * @param leaveTypeValue
	 * @param attendanceTime
	 * @param trxName
	 * @return
	 */
	public static List<MHRLeave> getLeaveListAttendanceTime(Properties ctx, int businessPartnerId, String leaveTypeValue, Timestamp attendanceTime, String trxName) {
		String optionalWhereClause = "";
		List<Object> parameters = new ArrayList<>();
		parameters.add(businessPartnerId);
		parameters.add(attendanceTime);
		if(!Util.isEmpty(leaveTypeValue)) {
			MHRLeaveType leaveType = MHRLeaveType.getByValue(ctx, leaveTypeValue, trxName);
			if(leaveType != null) {
				optionalWhereClause = " AND " + I_HR_Leave.COLUMNNAME_HR_LeaveType_ID + " = ?";
				parameters.add(leaveType.getHR_LeaveType_ID());
			}
		}
		return new Query(ctx, I_HR_Leave.Table_Name, "DocStatus IN('CO')"
				+ " AND C_BPartner_ID = ?"
				+ " AND ? BETWEEN StartDate AND EndDate"
				+ optionalWhereClause, trxName)
				.setParameters(parameters)
				.setOnlyActiveRecords(true)
				.setOrderBy(I_HR_Leave.COLUMNNAME_StartDate)
				.<MHRLeave>list();
	}
	
	/**
	 * Get Duration for TimeUtil from TimeUnit
	 * @param timeUnit
	 * @return
	 */
	public static String getDurationUnitFromTimeUnit(String timeUnit) {
		if(Util.isEmpty(timeUnit)) {
			return null;
		}
		//	
		if(timeUnit.equals(MHRLeaveType.TIMEUNIT_Minute)) {
			return TimeUtil.DURATIONUNIT_Minute;
		} else if(timeUnit.equals(MHRLeaveType.TIMEUNIT_Hour)) {
			return TimeUtil.DURATIONUNIT_Hour;
		} else if(timeUnit.equals(MHRLeaveType.TIMEUNIT_Day)) {
			return TimeUtil.DURATIONUNIT_Day;
		} else if(timeUnit.equals(MHRLeaveType.TIMEUNIT_Week)) {
			return TimeUtil.DURATIONUNIT_Week;
		} else if(timeUnit.equals(MHRLeaveType.TIMEUNIT_Month)) {
			return TimeUtil.DURATIONUNIT_Month;
		} else if(timeUnit.equals(MHRLeaveType.TIMEUNIT_Year)) {
			return TimeUtil.DURATIONUNIT_Year;
		} else {
			return null;
		}
	}
	
	/**
	 * Get Duration for TimeUtil from Frequency Type
	 * @param frequencyType
	 * @return
	 */
	public static String getDurationUnitFromFrequencyType(String frequencyType) {
		if(Util.isEmpty(frequencyType)) {
			return null;
		}
		//	
		if(frequencyType.equals(MHRLeaveType.FREQUENCYTYPE_Minute)) {
			return TimeUtil.DURATIONUNIT_Minute;
		} else if(frequencyType.equals(MHRLeaveType.FREQUENCYTYPE_Hour)) {
			return TimeUtil.DURATIONUNIT_Hour;
		} else if(frequencyType.equals(MHRLeaveType.FREQUENCYTYPE_Day)) {
			return TimeUtil.DURATIONUNIT_Day;
		} else if(frequencyType.equals(MHRLeaveType.FREQUENCYTYPE_Monthly)) {
			return TimeUtil.DURATIONUNIT_Month;
		} else if(frequencyType.equals(MHRLeaveType.FREQUENCYTYPE_Yearly)) {
			return TimeUtil.DURATIONUNIT_Year;
		} else {
			return null;
		}
	}
}
