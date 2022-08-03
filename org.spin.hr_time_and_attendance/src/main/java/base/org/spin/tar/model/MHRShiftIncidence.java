/**************************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                               *
 * This program is free software; you can redistribute it and/or modify it    		  *
 * under the terms version 2 or later of the GNU General Public License as published  *
 * by the Free Software Foundation. This program is distributed in the hope           *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied         *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                   *
 * See the GNU General Public License for more details.                               *
 * You should have received a copy of the GNU General Public License along            *
 * with this program; if not, printLine to the Free Software Foundation, Inc.,        *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                             *
 * For the text or an alternative of this public license, you may reach us            *
 * Copyright (C) 2012-2018 E.R.P. Consultores y Asociados, S.A. All Rights Reserved.  *
 * Contributor: Yamel Senih ysenih@erpya.com                                          *
 * See: www.erpya.com                                                                 *
 *************************************************************************************/
package org.spin.tar.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.api.I_HR_ShiftIncidence;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

/**
 * 	Class added for handle shift incidence
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1870>
 * 		@see FR [ 1870 ] Add Calulation for Attendance Record</a>
 */
public class MHRShiftIncidence extends X_HR_ShiftIncidence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 625050579283149689L;

	/**
	 * @param ctx
	 * @param HR_ShiftIncidence_ID
	 * @param trxName
	 */
	public MHRShiftIncidence(Properties ctx, int HR_ShiftIncidence_ID, String trxName) {
		super(ctx, HR_ShiftIncidence_ID, trxName);
	}

	/**
	 * @param ctx
	 * @param rs
	 * @param trxName
	 */
	public MHRShiftIncidence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}
	
	/**	Hash for days	*/
	private static CCache<String, List<MHRShiftIncidence>> shiftIncidenceForDaysCache = new CCache<String, List<MHRShiftIncidence>>(Table_Name, 1000);
	/** Cache Incidence */
	private static CCache<String, List<MHRShiftIncidence>> shiftIncidenceListCache = new CCache<String, List<MHRShiftIncidence>>(Table_Name, 1000);
	
	
	/**
	 * Evaluate if it can generate a incidence
	 * @param attendanceTime
	 * @return
	 */
	public boolean evaluateTime(Timestamp attendanceTime) {
		Timestamp evaluateTime = TimeUtil.getDayTime(getTimeFrom(), attendanceTime);
		if(isMandatoryRange()) {
			return TimeUtil.isValid(getTimeFrom(), getTimeTo(), evaluateTime);
		} else {
			return TimeUtil.isValid(getTimeFrom(), evaluateTime, evaluateTime);
		}
	}
	
	/**
	 * Verify if it have fixed values
	 * @return
	 */
	public boolean isFixedValue() {
		return (getFixedQty() != null && !getFixedQty().equals(Env.ZERO)) 
				|| (getFixedAmt() != null && !getFixedAmt().equals(Env.ZERO)); 
	}
	
	/**
	 * Get Duration in Millisecond, it can be used after evaluate time
	 * A example for entrance
	 * <li>[(8:00)------------(8:30)----------------------------(9:30)-------------------------(12:00)]
	 * <li>[(From)--------(Beginning Time)-----------------(Attendance Time)----------------------(To)]
	 * <li>Range for evaluate: From 8:00 ~ 12:00
	 * <li>Range for create incidence: 8:30 ~ 12:00
	 * <li>Attendance Time is: (9:30 - 8:30) = 1 Hour
	 * @param attendanceTime
	 * @return
	 */
	public long getDurationInMillis(Timestamp attendanceTime) {
		if(!evaluateTime(attendanceTime)) {
			return -1;
		}
		long duration = 0;
		Timestamp evaluateTime = TimeUtil.getDayTime(getTimeFrom(), attendanceTime);
		Timestamp beginninTime = getBeginningTime();
		Timestamp timeFrom = getTimeFrom();
		Timestamp timeTo = getTimeTo();
		//	Change time to
		if(timeTo.before(timeFrom)) {
			timeTo = TimeUtil.getDayTime(TimeUtil.addDays(timeTo, 1), timeTo);
		}
		if(getEventType().equals(EVENTTYPE_Entrance)) {
			if(beginninTime == null) {
				beginninTime = timeFrom;
			}
			//	Add to duration
			if(!TimeUtil.isValid(beginninTime, getTimeTo(), evaluateTime)) {
				duration = -1;
			} else if(isAnticipatedRecord()) {
				duration = timeTo.getTime() - evaluateTime.getTime();
			} else if(evaluateTime.after(beginninTime)) {
				duration = evaluateTime.getTime() - beginninTime.getTime();
			}
		} else if(getEventType().equals(EVENTTYPE_Egress)) {
			if(beginninTime == null) {
				beginninTime = timeFrom;
			}
			//	Is from last to beginning
			if(isAnticipatedRecord()) {
				beginninTime = getTimeTo();
				if(evaluateTime.before(timeFrom)) {
					evaluateTime = timeFrom;
				}
				duration = beginninTime.getTime() - evaluateTime.getTime();
			} else if(evaluateTime.after(beginninTime)) {
				if(evaluateTime.after(timeTo)) {
					evaluateTime = timeTo;
				}
				//	Calculate
				duration = evaluateTime.getTime() - beginninTime.getTime();
			}
		}
		//	Return
		return duration;
	}

	/**
	 * Get Shift Incidence List
	 * @param requery
	 * @return
	 */
	public static List<MHRShiftIncidence> getShiftIncidenceList(Properties ctx, int workShiftId, boolean requery) {
		String key = "IncidenceList|" + workShiftId;
		List<MHRShiftIncidence> shiftIncidenceList = shiftIncidenceListCache.get(key);
		if(requery
				|| shiftIncidenceList == null) {
			shiftIncidenceList = new Query(ctx, I_HR_ShiftIncidence.Table_Name, "HR_WorkShift_ID = ?", null)
					.setParameters(workShiftId)
					.list();
			if(shiftIncidenceList != null) {
				shiftIncidenceListCache.put(key, shiftIncidenceList);
			}
		}
		//	Default return
		return shiftIncidenceList;
	}
	
	/**
	 * Get Shift Incidence List
	 * @param eventType
	 * @param attendanceTime
	 * @return
	 */
	public static List<MHRShiftIncidence> getShiftIncidenceList(Properties ctx, int workShiftId, String eventType, Timestamp attendanceTime) {
		String keyPrefix = workShiftId + "|";
		//	Validate
		if(attendanceTime == null
				|| Util.isEmpty(eventType)) {
			return new ArrayList<MHRShiftIncidence>();
		}
		//	Get
		List<MHRShiftIncidence> shiftIncidenceList = shiftIncidenceForDaysCache.get(keyPrefix + eventType + TimeUtil.getDayOfWeek(attendanceTime));
		if(shiftIncidenceList != null) {
			return shiftIncidenceList;
		}
		//	Get reload false
		shiftIncidenceList = getShiftIncidenceList(ctx, workShiftId, false);
		String entrance = X_HR_ShiftIncidence.EVENTTYPE_Entrance;
		String egress = X_HR_ShiftIncidence.EVENTTYPE_Egress;
		String attendance = X_HR_ShiftIncidence.EVENTTYPE_Attendance;
		String leave = X_HR_ShiftIncidence.EVENTTYPE_Leave;
		//	Load Hash
		if(shiftIncidenceList != null) {
			//	Sunday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.SUNDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.SUNDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.SUNDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.SUNDAY, new ArrayList<MHRShiftIncidence>());
			//	Monday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.MONDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.MONDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.MONDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.MONDAY, new ArrayList<MHRShiftIncidence>());
			//	Tuesday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.TUESDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.TUESDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.TUESDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.TUESDAY, new ArrayList<MHRShiftIncidence>());
			//	Wednesday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.WEDNESDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.WEDNESDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.WEDNESDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.WEDNESDAY, new ArrayList<MHRShiftIncidence>());
			//	Thursday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.THURSDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.THURSDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.THURSDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.THURSDAY, new ArrayList<MHRShiftIncidence>());
			//	Friday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.FRIDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.FRIDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.FRIDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.FRIDAY, new ArrayList<MHRShiftIncidence>());
			//	Saturday
			shiftIncidenceForDaysCache.put(keyPrefix + entrance + Calendar.SATURDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + egress + Calendar.SATURDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + attendance + Calendar.SATURDAY, new ArrayList<MHRShiftIncidence>());
			shiftIncidenceForDaysCache.put(keyPrefix + leave + Calendar.SATURDAY, new ArrayList<MHRShiftIncidence>());
			//	Add
			for(MHRShiftIncidence shiftIncidence : shiftIncidenceList) {
				if(!shiftIncidence.isOnSunday()
						&& !shiftIncidence.isOnMonday()
						&& !shiftIncidence.isOnTuesday()
						&& !shiftIncidence.isOnWednesday()
						&& !shiftIncidence.isOnThursday()
						&& !shiftIncidence.isOnFriday()
						&& !shiftIncidence.isOnSaturday()) {
					//	Sunday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.SUNDAY).add(shiftIncidence);
					//	Monday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.MONDAY).add(shiftIncidence);
					//	Tuesday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.TUESDAY).add(shiftIncidence);
					//	Wednesday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.WEDNESDAY).add(shiftIncidence);
					//	Thursday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.THURSDAY).add(shiftIncidence);
					//	Friday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.FRIDAY).add(shiftIncidence);
					//	Saturday
					shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.SATURDAY).add(shiftIncidence);
				} else {
					//	Sunday
					if(shiftIncidence.isOnSunday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.SUNDAY).add(shiftIncidence);
					}
					//	Monday
					if(shiftIncidence.isOnMonday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.MONDAY).add(shiftIncidence);
					}
					//	Tuesday
					if(shiftIncidence.isOnTuesday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.TUESDAY).add(shiftIncidence);
					}
					//	Wednesday
					if(shiftIncidence.isOnWednesday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.WEDNESDAY).add(shiftIncidence);
					}
					//	Thursday
					if(shiftIncidence.isOnThursday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.THURSDAY).add(shiftIncidence);
					}
					//	Friday
					if(shiftIncidence.isOnFriday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.FRIDAY).add(shiftIncidence);
					}
					//	Saturday
					if(shiftIncidence.isOnSaturday()) {
						shiftIncidenceForDaysCache.get(keyPrefix + shiftIncidence.getEventType() + Calendar.SATURDAY).add(shiftIncidence);
					}
				}
			}	
		}
		//	Get
		shiftIncidenceList = shiftIncidenceForDaysCache.get(keyPrefix + eventType + TimeUtil.getDayOfWeek(attendanceTime));
		if(shiftIncidenceList == null) {
			return new ArrayList<MHRShiftIncidence>();
		}
		//	Return
		return shiftIncidenceList;
	}
	
	@Override
	public String toString() {
		return "MHRShiftIncidence [getBeginningTime()=" + getBeginningTime() + ", getDescription()=" + getDescription()
				+ ", getEventType()=" + getEventType() + ", getHR_Concept_ID()=" + getHR_Concept_ID()
				+ ", isOnFriday()=" + isOnFriday() + ", isOnMonday()=" + isOnMonday() + ", isOnSaturday()="
				+ isOnSaturday() + ", isOnSunday()=" + isOnSunday() + ", isOnThursday()=" + isOnThursday()
				+ ", isOnTuesday()=" + isOnTuesday() + ", isOnWednesday()=" + isOnWednesday() + ", getTimeFrom()="
				+ getTimeFrom() + ", getTimeTo()=" + getTimeTo() + ", getTimeUnit()=" + getTimeUnit() + "]";
	}
}
