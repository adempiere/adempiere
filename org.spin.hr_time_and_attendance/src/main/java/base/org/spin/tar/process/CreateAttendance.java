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
package org.spin.tar.process;

import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHREmployee;
import org.eevolution.hr.model.MHRWorkShift;
import org.spin.tar.model.MHRAttendanceBatch;
import org.spin.tar.model.MHRAttendanceRecord;

/** Generated Process for (Create Attendance Record)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class CreateAttendance extends CreateAttendanceAbstract {
	
	@Override
	protected String doIt() throws Exception {
		int created = 0;
		MHRAttendanceBatch attendanceBatch = null;
		//	Loop for keys
		for(Integer key : getSelectionKeys()) {
			int employeeId = getSelectionAsInt(key, "EE_HR_Employee_ID");
			Timestamp shiftFromTime = getSelectionAsTimestamp(key, "WS_ShiftFromTime");
			Timestamp breakStartTime = getSelectionAsTimestamp(key, "WS_BreakStartTime");
			Timestamp breakEndTime = getSelectionAsTimestamp(key, "WS_BreakEndTime");
			Timestamp shiftToTime = getSelectionAsTimestamp(key, "WS_ShiftToTime");
			int workShiftId = getSelectionAsInt(key, "WS_HR_WorkShift_ID");
			boolean isCreateDefault = getSelectionAsBoolean(key, "WS_IsCreateDefault");
			//	Validate
			if(workShiftId == 0) {
				log.warning("HR_Employee_ID=" + employeeId + " [HR_WorkShift_ID Not Found]");
				continue;
			}
			//	Add default
			MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), workShiftId);
			attendanceBatch = new MHRAttendanceBatch(getCtx(), 0, get_TrxName());
			MHREmployee employee = MHREmployee.getById(getCtx(), employeeId);
			//	Set Employee
			attendanceBatch.setC_BPartner_ID(employee.getC_BPartner_ID());
			attendanceBatch.setHR_Employee_ID(employeeId);
			//	Set Document Date
			attendanceBatch.setDateDoc(getDateDoc());
			//	Set Work Shift
			attendanceBatch.setHR_WorkShift_ID(workShiftId);
			//	Set Values
			if(getDocTypeId() > 0) {
				attendanceBatch.setC_DocType_ID(getDocTypeId());
			}
			//	Add support to contract
			int contractId = getParameterAsInt("S_Contract_ID");
			int contractLineId = getParameterAsInt("S_ContractLine_ID");
			//	Set
			if(contractId > 0) {
				attendanceBatch.set_ValueOfColumn("S_Contract_ID", contractId);
			}
			if(contractLineId > 0) {
				attendanceBatch.set_ValueOfColumn("S_ContractLine_ID", contractLineId);
			}
			//	Save
			attendanceBatch.saveEx();
			//	Set Defaults
			if(isCreateDefault
					|| !Util.isEmpty(getIsCreateDefault()) && getIsCreateDefault().equals("Y")) {
				shiftFromTime = workShift.getShiftFromTime();
				breakStartTime = workShift.getBreakStartTime();
				breakEndTime = workShift.getBreakEndTime();
				shiftToTime = workShift.getShiftToTime();
			}
			//	Validate
			//	From Time
			if(shiftFromTime == null) {
				log.warning("HR_Employee_ID=" + employeeId + " [ShiftFromTime Not Found]");
				continue;
			}
			//	Validate
			if(shiftToTime == null) {
				log.warning("HR_Employee_ID=" + employeeId + " [ShiftToTime Not Found]");
				continue;
			}
			//	Sequence No
			int seqNo = 10;
			//	For Shift From Time
			MHRAttendanceRecord attendance = new MHRAttendanceRecord(attendanceBatch);
			attendance.setAttendanceTime(TimeUtil.getDayTime(getDateDoc(), shiftFromTime));
			attendance.setSeqNo(seqNo);
			attendance.saveEx();
			seqNo += 10;
			//	For Break Start Time
			if(breakStartTime != null) {
				attendance = new MHRAttendanceRecord(attendanceBatch);
				attendance.setAttendanceTime(TimeUtil.getDayTime(getDateDoc(), breakStartTime));
				attendance.setSeqNo(seqNo);
				attendance.saveEx();
				seqNo += 10;
			}
			//	For Break End Time
			if(breakStartTime != null) {
				attendance = new MHRAttendanceRecord(attendanceBatch);
				attendance.setAttendanceTime(TimeUtil.getDayTime(getDateDoc(), breakEndTime));
				attendance.setSeqNo(seqNo);
				attendance.saveEx();
				seqNo += 10;
			}
			//	For Shift To Time
			attendance = new MHRAttendanceRecord(attendanceBatch);
			attendance.setAttendanceTime(TimeUtil.getDayTime(getDateDoc(), shiftToTime));
			attendance.setSeqNo(seqNo);
			attendance.saveEx();
			//	Process It
			processDocument(attendanceBatch);
			created++;
		}
		return "@Created@ " + created + (created == 1? " [@HR_AttendanceBatch_ID@ " + attendanceBatch.getDocumentNo() + "]": "");
	}
	
	/**
	 * Process Attendance Record
	 * @param attendanceBatch
	 */
	private void processDocument(MHRAttendanceBatch attendanceBatch) {
		//	Process Selection
		if(!attendanceBatch.processIt(getDocAction())) {
			throw new AdempiereException("@Error@ " + attendanceBatch.getProcessMsg());
		}
		//	
		attendanceBatch.saveEx();
		addLog(attendanceBatch.getHR_AttendanceBatch_ID(), null, null, attendanceBatch.toString());
	}
}