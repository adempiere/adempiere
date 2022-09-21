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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Perez www.e-evolution.com                           *
 *****************************************************************************/

package org.eevolution.hr.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.X_HR_WorkShift;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.CCache;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

/**
 * Created by victor.perez@e-evolution.com, e-Evolution on 03/12/13.
 * @author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1870>
 * 		@see FR [ 1870 ] Add Calulation for Attendance Record</a>
 */
public class MHRWorkShift extends X_HR_WorkShift {

	public MHRWorkShift(Properties ctx, int HR_WorkShift_ID, String trxName) {
        super(ctx, HR_WorkShift_ID, trxName);
    }

    public MHRWorkShift(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 955746318164528261L;
    
	/** Cache */
	private static CCache<Integer, MHRWorkShift> workShiftCache = new CCache<Integer, MHRWorkShift>(Table_Name, 1000);
	/** Cache */
	private static CCache<String, MHRWorkShift> workShiftValueCache = new CCache<String, MHRWorkShift>(Table_Name, 1000);
	
	/**
	 * Get Work Shift by Id
	 * @param ctx
	 * @param workShiftId
	 * @return
	 */
	public static MHRWorkShift getById(Properties ctx, int workShiftId, String trxName) {
		if (workShiftId <= 0)
			return null;

		MHRWorkShift workShift = workShiftCache.get(workShiftId);
		if (workShift != null)
			return workShift;

		workShift = new MHRWorkShift(ctx, workShiftId, trxName);
		if (workShift.get_ID() == workShiftId)
			workShiftCache.put(workShiftId, workShift);
		else
			workShift = null;
		return workShift;
	}
	
	/**
	 * Old Compatibility
	 * @param ctx
	 * @param workShiftId
	 * @return
	 */
	public static MHRWorkShift getById(Properties ctx, int workShiftId) {
		return getById(ctx, workShiftId, null);
	}
	
	 /**
     * Get Work shift by Value
     * @param ctx
     * @param workShiftValue
     * @param trxName
     * @return
     */
    public static MHRWorkShift getByValue(Properties ctx, String workShiftValue, String trxName) {
        if (Util.isEmpty(workShiftValue, true))
            return null;

        int clientId = Env.getAD_Client_ID(ctx);
        final String key = clientId + "#" + workShiftValue;
        MHRWorkShift workShift = workShiftValueCache.get(key);
        if (workShift != null)
            return workShift;

        final String whereClause = COLUMNNAME_Value + "=? AND AD_Client_ID IN (?,?)";
        workShift = new Query(ctx, Table_Name, whereClause, trxName)
                .setParameters(workShiftValue, 0, clientId)
                .setOnlyActiveRecords(true)
                .setOrderBy("AD_Client_ID DESC")
                .first();
        if (workShift != null) {
        	workShiftValueCache.put(key, workShift);
        	workShiftCache.put(workShift.get_ID(), workShift);
        }
        return workShift;
    }
    
	 /**
     * Get Work shift from Shift Group
     * @param ctx
     * @param shiftGroupId
     * @param trxName
     * @return
     */
    public static List<MHRWorkShift> getFromGroup(Properties ctx, int shiftGroupId, String trxName) {
        if(shiftGroupId <= 0) {
        	return null;
        }
        final String whereClause = COLUMNNAME_HR_ShiftGroup_ID + "=?";
        return new Query(ctx, Table_Name, whereClause, trxName)
                .setParameters(shiftGroupId)
                .setOnlyActiveRecords(true)
                .setOrderBy(COLUMNNAME_SeqNo)
                .list();
    }
    
    /**
     * Get default shift work from group
     * @param ctx
     * @param shiftGroupId
     * @param trxName
     * @return
     */
    public static MHRWorkShift getDefaultFromGroup(Properties ctx, int shiftGroupId, String trxName) {
        if(shiftGroupId <= 0) {
        	return null;
        }
        List<MHRWorkShift> listOfGroup = getFromGroup(ctx, shiftGroupId, trxName);
        if(listOfGroup != null
        		&& listOfGroup.size() > 0) {
        	return listOfGroup.stream().findFirst().get();
        }
        return null;
    }
    
    /**
     * Get By Value
     * @param ctx
     * @param workShiftValue
     * @return
     */
    public static MHRWorkShift getByValue(Properties ctx, String workShiftValue) {
    	return getByValue(ctx, workShiftValue, null);
    }
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		//	Validate for non business day
		if(isOnSunday()
				&& isOnMonday()
				&& isOnTuesday()
				&& isOnWednesday()
				&& isOnThursday()
				&& isOnFriday()
				&& isOnSaturday()) {
			throw new AdempiereException("@TNA.AtLeastBusinessDay@");
		}
		Timestamp fromTime = getShiftFromTime();
		Timestamp toTime = getShiftToTime();
        Timestamp breakStartTime = getBreakStartTime();
        Timestamp breakEndTime = getBreakEndTime();
        BigDecimal breakHourNo = getBreakHoursNo();
        if (fromTime != null 
        		&& toTime != null) {
        	if (fromTime.after(toTime)) {
            	toTime = TimeUtil.getDayTime(TimeUtil.addDays(toTime, 1), toTime);
            }
        }
        //	For break Time
        if (breakStartTime != null 
        		&& breakEndTime != null) {
        	//	
        	if (fromTime.after(breakStartTime)) {
        		breakStartTime = TimeUtil.getDayTime(TimeUtil.addDays(breakStartTime, 1), breakStartTime);
        	}
        	if (breakStartTime.after(breakEndTime)) {
        		breakEndTime = TimeUtil.getDayTime(TimeUtil.addDays(breakEndTime, 1), breakEndTime);
        	}
        	long breakDifference = breakEndTime.getTime() - breakStartTime.getTime();
        	breakHourNo = new BigDecimal(breakDifference / (double)(1000 * 60 * 60));
        }
        //	Set break hour no
        if(breakHourNo == null) {
        	breakHourNo = Env.ZERO;
        }
		//	Validate break
		if(!TimeUtil.isValid(fromTime, toTime, breakStartTime)) {
			throw new AdempiereException("@TNA.InvalidBreakStartTime@");
		}
		//	
		if(!TimeUtil.isValid(fromTime, toTime, breakEndTime)) {
			throw new AdempiereException("@TNA.InvalidBreakEndTime@");
		}
		//	
		if(breakStartTime != null
				&& breakEndTime != null
				&& breakEndTime.getTime() < breakStartTime.getTime()) {
			throw new AdempiereException("@TNA.InvalidBreakTime@");
		}
		//	Validate Pair
		if(getMinAttendanceRequire() > 0
				&& getMinAttendanceRequire() % 2 != 0) {
			throw new AdempiereException("@TNA.AttendanceNotPair@");
		}
        //[20111209:6:00]
        long workDifference = toTime.getTime() - fromTime.getTime();
        BigDecimal workHourNo = new BigDecimal(workDifference / (double)(1000 * 60 * 60));
        //	Set
        setBreakHoursNo(breakHourNo);
        setNoOfHours(workHourNo.subtract(breakHourNo));
        setShiftFromTime(fromTime);
        setShiftToTime(toTime);
        setBreakStartTime(breakStartTime);
        setBreakEndTime(breakEndTime);
		return true;
	}

	@Override
	public String toString() {
		return "MHRWorkShift [getBreakEndTime()=" + getBreakEndTime() + ", getBreakHoursNo()=" + getBreakHoursNo()
				+ ", getBreakStartTime()=" + getBreakStartTime() + ", isOverTimeApplicable()=" + isOverTimeApplicable()
				+ ", getName()=" + getName() + ", getNoOfHours()=" + getNoOfHours() + ", isOnFriday()=" + isOnFriday()
				+ ", isOnMonday()=" + isOnMonday() + ", isOnSaturday()=" + isOnSaturday() + ", isOnSunday()="
				+ isOnSunday() + ", isOnThursday()=" + isOnThursday() + ", isOnTuesday()=" + isOnTuesday()
				+ ", isOnWednesday()=" + isOnWednesday() + ", getShiftFromTime()=" + getShiftFromTime()
				+ ", getShiftToTime()=" + getShiftToTime() + "]";
	}
}
