/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.									  *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 *****************************************************************************/

package org.spin.tar.process;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.api.I_I_HR_AttendanceRecord;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.hr.model.MHREmployee;
import org.eevolution.hr.model.MHRShiftGroup;
import org.eevolution.hr.model.MHRWorkGroup;
import org.eevolution.hr.model.MHRWorkShift;
import org.spin.tar.model.MHRAttendanceBatch;
import org.spin.tar.model.MHRAttendanceRecord;
import org.spin.tar.model.X_I_HR_AttendanceRecord;

/** Generated Process for (Import Attendance Record)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class ImportAttendance extends ImportAttendanceAbstract {
	
	/**	Attendance Batch	*/
	private MHRAttendanceBatch attendanceBatch = null;
	/**	Sequence	*/
	private int attendanceQuantity = 0;
	
	@Override
	protected String doIt() throws Exception {
		//	Delete Old Imported
		 if (isDeleteOldImported()) {
			 Arrays.stream(getAttendanceIds(true,true, null))
			 .forEach(recordId -> {
				 	X_I_HR_AttendanceRecord importAttendance = new X_I_HR_AttendanceRecord(getCtx(), recordId , null);
				 	importAttendance.deleteEx(true);
	            });
		 }
		 AtomicInteger importedRecord = new AtomicInteger(0);
		 AtomicInteger withErrors = new AtomicInteger(0);
		 Arrays.stream(getAttendanceIds(false, false, null))
		 .forEach(recordId -> {
	            Trx.run( trxName -> {
	            	X_I_HR_AttendanceRecord importAttendance = new X_I_HR_AttendanceRecord(getCtx(), recordId, trxName);
	                boolean isOk = fillIdValues(importAttendance);
	                if(isOk 
	                		&& !isValidateOnly()) {
	                	if (importRecord(importAttendance)) {
		                	importedRecord.updateAndGet(record -> record + 1);
		                } else {
		                	withErrors.updateAndGet(error -> error + 1);
		                }
	                }
	            });
	        });
		 //	Process Last
		 processDocument(null);
	     return "@HR_AttendanceRecord_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
	}
	
	/**
	 * Get Work Shift
	 * @param employeeId
	 * @return
	 */
	private int getWorkShiftId(int employeeId) {
		MHREmployee employee = MHREmployee.getById(getCtx(), employeeId);
   	 	int workShiftId = 0;
	   	if(employee.getHR_ShiftGroup_ID() != 0) {
	   		MHRShiftGroup shiftGroup = MHRShiftGroup.getById(getCtx(), employee.getHR_ShiftGroup_ID(), get_TrxName());
	   		List<MHRWorkShift> workShiftList = shiftGroup.getWorkShiftList(false);
	   		if(workShiftList != null) {
	   			workShiftId = workShiftList.get(0).getHR_WorkShift_ID();
	   		}
	   	}
	   	//	
	   	if(workShiftId <= 0
	   			&& employee.getHR_WorkGroup_ID() > 0) {
	   		MHRWorkGroup workGroup = MHRWorkGroup.getById(getCtx(), employee.getHR_WorkGroup_ID(), get_TrxName());
	   		if(workGroup.getHR_WorkShift_ID() > 0) {
	   			workShiftId = workGroup.getHR_WorkShift_ID();
	   		}
	   		//	Get from Shift Group
	   		if(workShiftId <= 0
	   				&& workGroup.getHR_ShiftGroup_ID() > 0) {
	   			MHRShiftGroup shiftGroup = MHRShiftGroup.getById(getCtx(), workGroup.getHR_ShiftGroup_ID(), get_TrxName());
		   		List<MHRWorkShift> workShiftList = shiftGroup.getWorkShiftList(false);
		   		if(workShiftList != null) {
		   			workShiftId = workShiftList.get(0).getHR_WorkShift_ID();
		   		}
	   		}
	   	}
	   	//	
	   	return workShiftId;
	}
	
	/**
	 * Import It
	 * @param importAttendance
	 * @return
	 */
	 private boolean importRecord(X_I_HR_AttendanceRecord importAttendance) {
		 int workShiftId = getWorkShiftId(importAttendance.getHR_Employee_ID());
		 MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), workShiftId);
         if(attendanceBatch != null
        		 && (attendanceBatch.getHR_Employee_ID() != importAttendance.getHR_Employee_ID()
        				 || workShift.getMinAttendanceRequire() <= attendanceQuantity)) {
        	 processDocument(importAttendance);
        	 attendanceBatch = null;
         }
         //	Create new
         if(attendanceBatch == null) {
        	 attendanceQuantity = 0;
        	 attendanceBatch = new MHRAttendanceBatch(getCtx(), 0, get_TrxName());
        	 attendanceBatch.setDateDoc(TimeUtil.getDay(importAttendance.getAttendanceTime()));
        	 MHREmployee employee = MHREmployee.getById(getCtx(), importAttendance.getHR_Employee_ID());
        	 attendanceBatch.setC_BPartner_ID(employee.getC_BPartner_ID());
        	 attendanceBatch.setHR_Employee_ID(importAttendance.getHR_Employee_ID());
        	 workShiftId = getWorkShiftId(employee.getHR_Employee_ID());
        	 //	
        	 if(workShiftId <= 0) {
        		 setImportError(importAttendance, "@HR_WorkShift_ID@ @NotFound@").saveEx(importAttendance.get_TrxName());
        		 attendanceBatch = null;
        	 } else {
        		 attendanceBatch.setHR_WorkShift_ID(workShiftId);
        		 attendanceBatch.saveEx();
        	 }
         }
         if(attendanceBatch != null) {
        	 MHRAttendanceRecord attendanceRecord = new MHRAttendanceRecord(attendanceBatch);
        	 attendanceRecord.setAttendanceTime(importAttendance.getAttendanceTime());
        	 attendanceRecord.setSeqNo(attendanceQuantity * 10);
        	 attendanceRecord.saveEx();
        	 //	Save reference
        	 importAttendance.setHR_AttendanceBatch_ID(attendanceBatch.getHR_AttendanceBatch_ID());
        	 importAttendance.setHR_AttendanceRecord_ID(attendanceRecord.getHR_AttendanceRecord_ID());
        	 //	
        	 importAttendance.setI_IsImported(true);
             importAttendance.setI_ErrorMsg("");
             importAttendance.setProcessed(true);
             importAttendance.saveEx();
             //	
             attendanceQuantity++;
             return true;
         }
         //	Return 
         return false;
	 }
	 
		/**
		 * Process Attendance
		 * @param importAttendance
		 */
		private void processDocument(X_I_HR_AttendanceRecord importAttendance) {
			if(attendanceBatch == null) {
				return;
			}
			//	Process Selection
			if(!attendanceBatch.processIt(MHRAttendanceBatch.DOCACTION_Complete)) {
				if(importAttendance != null) {
					setImportError(importAttendance, "@Error@ " + attendanceBatch.getProcessMsg());
				}
			}
			//	
			attendanceBatch.saveEx();
			addLog(attendanceBatch.getHR_AttendanceBatch_ID(), attendanceBatch.getDateDoc(), null, attendanceBatch.toString());
		}
		
		/**
	     * set Import Error
	     *
	     * @param importAttendance
	     * @param error
	     * @return
	     */
	    private X_I_HR_AttendanceRecord setImportError(X_I_HR_AttendanceRecord importAttendance, String error) {
	    	importAttendance.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
	    	importAttendance.setI_IsImported(false);
	    	importAttendance.setProcessed(false);
	        addLog(importAttendance.getI_HR_AttendanceRecord_ID(), importAttendance.getAttendanceTime(), null, importAttendance.getI_ErrorMsg());
	        return importAttendance;
	    }
	    
	    /**
	     * Fill mandatory information
	     *
	     * @param importAttendance
	     */
	    private boolean fillIdValues(X_I_HR_AttendanceRecord importAttendance) {
	    	StringBuilder messageError = new StringBuilder();
	    	importAttendance.setI_ErrorMsg("");
	    	final String employeeQuery = "SELECT HR_Employee_ID FROM HR_Employee WHERE TRIM(Code) = TRIM(?) "
	    			+ "AND (EmployeeStatus = '13' OR EmployeeStatus IS NULL) AND AD_Client_ID = " + getClientId();
	    	int employeeId = DB.getSQLValue(null, employeeQuery, importAttendance.getCode());
	    	if(employeeId > 0) {
	    		importAttendance.setHR_Employee_ID(employeeId);
	    	} else {
	    		messageError.append("@HR_Employee_ID@ @NotFound@");
	    	}
	    	//	Validate Work shift
	    	if(employeeId > 0) {
	    		if(getWorkShiftId(employeeId) <= 0) {
	    			messageError.append(" @HR_WorkShift_ID@ @NotFound@");
	    		}
	    	} else {
	    		messageError.append(" @HR_WorkShift_ID@ @NotFound@");
	    	}
	    	if (messageError.length() > 0) {
	    		setImportError(importAttendance, messageError.toString()).saveEx(importAttendance.get_TrxName());
	    	}
			//	
	        if (importAttendance.getI_ErrorMsg() != null && importAttendance.getI_ErrorMsg().length() > 0) {
	           importAttendance.setProcessed(false);
	           importAttendance.setI_IsImported(false);
	           importAttendance.saveEx();
	           return false;
	        }
	        importAttendance.saveEx();
	        //	Default 
	        return true;
	    }
	    
	    /**
	     * Get Import Attendance List
	     * @param isImported
	     * @param isProcessed
	     * @return
	     */
	    private int[] getAttendanceIds(boolean isImported, boolean isProcessed, String trxName) {
	        StringBuilder whereClause = new StringBuilder();
	        whereClause.append(I_I_HR_AttendanceRecord.COLUMNNAME_I_IsImported).append("=? AND ")
	                .append(I_I_HR_AttendanceRecord.COLUMNNAME_Processed).append("=?");

	        return new Query(getCtx(), I_I_HR_AttendanceRecord.Table_Name, whereClause.toString(), trxName)
	                .setOnlyActiveRecords(true)
	                .setParameters(isImported, isProcessed)
	                .setOrderBy(I_I_HR_AttendanceRecord.COLUMNNAME_Code + ", " + I_I_HR_AttendanceRecord.COLUMNNAME_AttendanceTime)
	                .getIDs();

	    }
}