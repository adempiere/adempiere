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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import org.adempiere.core.domains.models.I_HR_WorkShift;
import org.adempiere.core.domains.models.I_I_HR_AttendanceRecord;
import org.adempiere.core.domains.models.X_HR_WorkShift;
import org.adempiere.core.domains.models.X_I_HR_AttendanceRecord;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.hr.model.MHREmployee;
import org.eevolution.hr.model.MHRWorkGroup;
import org.eevolution.hr.model.MHRWorkShift;
import org.spin.tar.model.MHRAttendanceBatch;
import org.spin.tar.model.MHRAttendanceRecord;
import org.spin.tar.model.MHRShiftSchedule;

/** Generated Process for (Import Attendance Record)
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class ImportAttendance extends ImportAttendanceAbstract {
	
	@Override
	protected String doIt() throws Exception {
		//	Delete Old Imported
		 if (isDeleteOldImported()) {
			 getAttendanceIds(true,true, null)
			 .forEach(recordId -> {
				 	X_I_HR_AttendanceRecord importAttendance = new X_I_HR_AttendanceRecord(getCtx(), recordId , null);
				 	importAttendance.deleteEx(true);
	            });
		 }
		 AtomicInteger importedRecord = new AtomicInteger(0);
		 AtomicInteger withErrors = new AtomicInteger(0);
		 getGroupedAttendances()
		 .entrySet()
		 .parallelStream()
		 .forEach(entry -> {
			 Trx.run(transactionName -> {
				 AtomicInteger attendanceQuantity = new AtomicInteger(1);
				 AtomicReference<MHRAttendanceBatch> attendanceBatchReference = new AtomicReference<MHRAttendanceBatch>();
				 entry.getValue().forEach(attendanceId -> {
					 X_I_HR_AttendanceRecord importAttendance = new X_I_HR_AttendanceRecord(getCtx(), attendanceId, transactionName);
		                boolean isOk = fillIdValues(importAttendance);
		                if(isOk 
		                		&& !isValidateOnly()) {
		                	MHRAttendanceBatch attendanceBatch = attendanceBatchReference.get(); 
			                if(attendanceBatch == null) {
			                	attendanceBatch = createBatch(importAttendance.getHR_Employee_ID(), importAttendance.getAttendanceTime(), transactionName);
				               	attendanceBatchReference.set(attendanceBatch);
			                } else {
			                	if(attendanceBatch.getHR_Employee_ID() != importAttendance.getHR_Employee_ID()) {
				                	if(!attendanceBatch.processIt(MHRAttendanceBatch.DOCACTION_Complete)) {
				        				if(attendanceBatch != null) {
				        					setImportError(importAttendance, "@Error@ " + attendanceBatch.getProcessMsg());
				        				}
				        			}
				        			//	
				                	attendanceBatch.saveEx();
				        			addLog(attendanceBatch.getHR_AttendanceBatch_ID(), attendanceBatch.getDateDoc(), null, attendanceBatch.toString());
				        			attendanceBatchReference.set(attendanceBatch = createBatch(importAttendance.getHR_Employee_ID(), importAttendance.getAttendanceTime(), transactionName));
			                	}
			                }
			                importRecord(attendanceBatchReference.get(), importAttendance, attendanceQuantity.getAndIncrement() * 10);
		                	importedRecord.updateAndGet(record -> record + 1);
		                } else if(!isOk) {
		                	withErrors.updateAndGet(error -> error + 1);
		                }
				 });
				 MHRAttendanceBatch attendanceBatch = attendanceBatchReference.get();
				 if(attendanceBatch != null) {
					 if(!attendanceBatch.processIt(MHRAttendanceBatch.DOCACTION_Complete)) {
						 withErrors.updateAndGet(error -> error + 1);
					 }
				 }
			 });
	     });
	     return "@HR_AttendanceRecord_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
	}
	
	private MHRAttendanceBatch createBatch(int employeeId, Timestamp attendanceTime, String transactionName) {
		MHRAttendanceBatch attendanceBatch = new MHRAttendanceBatch(getCtx(), 0, transactionName);
    	attendanceBatch.setDateDoc(TimeUtil.getDay(attendanceTime));
       	MHREmployee employee = MHREmployee.getById(getCtx(), employeeId);
       	attendanceBatch.setC_BPartner_ID(employee.getC_BPartner_ID());
       	attendanceBatch.setHR_Employee_ID(employeeId);
       	setWorkShiftIdFromEmployeeAndTime(attendanceBatch, employee, attendanceTime);
       	attendanceBatch.saveEx();
       	return attendanceBatch;
	}
	
	private Map<String, List<Integer>> getGroupedAttendances() {
		Map<String, List<Integer>> groupedRequisitions = new HashMap<String, List<Integer>>();
		getAttendanceIds(false, false, null).forEach(importAttendanceId -> {
			X_I_HR_AttendanceRecord importAttendance = new X_I_HR_AttendanceRecord(getCtx(), importAttendanceId, null);
			String key = Optional.ofNullable(importAttendance.getCode()).orElse("") + "|" + TimeUtil.getDay(importAttendance.getAttendanceTime());
			List<Integer> importReferences = new ArrayList<Integer>();
			if(groupedRequisitions.containsKey(key)) {
				importReferences = groupedRequisitions.get(key);
			}
			importReferences.add(importAttendanceId);
			groupedRequisitions.put(key, importReferences);
		});
		return groupedRequisitions;
	}
	
	/**
	 * Import It
	 * @param importAttendance
	 * @return
	 */
	 private void importRecord(MHRAttendanceBatch attendanceBatch, X_I_HR_AttendanceRecord importAttendance, int sequence) {
		 MHRAttendanceRecord attendanceRecord = new MHRAttendanceRecord(attendanceBatch);
    	 attendanceRecord.setAttendanceTime(importAttendance.getAttendanceTime());
    	 attendanceRecord.setSeqNo(sequence);
    	 attendanceRecord.saveEx(importAttendance.get_TrxName());
    	 //	Save reference
    	 importAttendance.setHR_AttendanceBatch_ID(attendanceBatch.getHR_AttendanceBatch_ID());
    	 importAttendance.setHR_AttendanceRecord_ID(attendanceRecord.getHR_AttendanceRecord_ID());
    	 //	
    	 importAttendance.setI_IsImported(true);
         importAttendance.setI_ErrorMsg("");
         importAttendance.setProcessed(true);
         importAttendance.saveEx();
	 }
	 
	 /**
		 * Get Work Shift from Employee and attendance time
		 * @param context
		 * @param attendanceTime
		 * @param employeeId
		 * @return
		 */
		private int getWorkShiftIdFromEmployee(Properties context, Timestamp attendanceTime, int employeeId) {
			MHREmployee employee = MHREmployee.getById(context, employeeId);
			return getWorkShiftIdFromShiftGroupAndTime(context, employee.getHR_ShiftGroup_ID(), employee.getHR_WorkGroup_ID(), attendanceTime);
		}
		
		 /**
	     * Get Work shift from Shift Group
	     * @param ctx
	     * @param shiftGroupId
	     * @param transactionName
	     * @return
	     */
	    private int getWorkShiftIdFromShiftGroupAndTime(Properties ctx, int shiftGroupId, int workGroupId, Timestamp time) {
	        if(shiftGroupId <= 0 || time == null) {
	        	return -1;
	        }
	        Timestamp day = TimeUtil.getDay(System.currentTimeMillis());
	        Timestamp timeAsDay = TimeUtil.getDayTime(day, time);
	        if(workGroupId > 0) {
	        	MHRWorkGroup workGroup = MHRWorkGroup.getById(ctx, workGroupId, null);
	            int workShiftId = -1;
	            if(workGroup.isShiftAllocation()) {
	    			workShiftId = workGroup.getHR_WorkShift_ID();
	    			if(workShiftId <= 0
	    					&& workGroup.getHR_ShiftGroup_ID() > 0) {
	    				MHRWorkShift workShift = MHRWorkShift.getDefaultFromGroup(ctx, workGroup.getHR_ShiftGroup_ID(), null);
	    				if(workShift != null) {
	    					workShiftId = workShift.getHR_WorkShift_ID();
	    				}
	    			}
	    		} else {
	    			MHRShiftSchedule shiftSchedule = MHRShiftSchedule.getScheduleFromWorkGroup(ctx, workGroup.getHR_WorkGroup_ID(), time, null);
	    			if(shiftSchedule != null) {
	    				workShiftId = shiftSchedule.getHR_WorkShift_ID();
	    			}
	    		}
	            if(workShiftId > 0) {
	            	return workShiftId;
	            }
	        }
	        if(shiftGroupId > 0) {
	        	final String whereClause = I_HR_WorkShift.COLUMNNAME_HR_ShiftGroup_ID + " = ?";
	            List<Integer> workShiftIds = new Query(ctx, I_HR_WorkShift.Table_Name, whereClause, null)
	                    .setParameters(shiftGroupId)
	                    .setOnlyActiveRecords(true)
	                    .setOrderBy(I_HR_WorkShift.COLUMNNAME_SeqNo)
	                    .getIDsAsList();
	            if(workShiftIds != null && workShiftIds.size() > 0) {
	            	for(int workShiftId : workShiftIds) {
	            		X_HR_WorkShift workShift = new X_HR_WorkShift(ctx, workShiftId, null);
	            		if(TimeUtil.isValid(TimeUtil.getDayTime(day, workShift.getShiftFromTime()), TimeUtil.getDayTime(day, workShift.getShiftToTime()), timeAsDay)) {
	            			return workShiftId;
	            		}
	            	}
	            	//	Default
	            	return workShiftIds.get(0);
	            }
	        }
	        return -1;
	    }
	    
	    /**
	     * Set Work Shift and Schedule from Employee and time
	     * @param ctx
	     * @param shiftGroupId
	     * @param transactionName
	     * @return
	     */
	    private void setWorkShiftIdFromEmployeeAndTime(MHRAttendanceBatch batch, MHREmployee employee, Timestamp time) {
	        if(batch == null || employee == null || time == null || batch.getHR_WorkShift_ID() > 0) {
	        	return;
	        }
	        Timestamp day = TimeUtil.getDay(System.currentTimeMillis());
	        Timestamp timeAsDay = TimeUtil.getDayTime(day, time);
	        if(employee.getHR_WorkGroup_ID() > 0) {
	        	MHRWorkGroup workGroup = MHRWorkGroup.getById(batch.getCtx(), employee.getHR_WorkGroup_ID(), null);
	            int workShiftId = -1;
	            if(workGroup.isShiftAllocation()) {
	    			workShiftId = workGroup.getHR_WorkShift_ID();
	    			if(workShiftId <= 0
	    					&& workGroup.getHR_ShiftGroup_ID() > 0) {
	    				MHRWorkShift workShift = MHRWorkShift.getDefaultFromGroup(batch.getCtx(), workGroup.getHR_ShiftGroup_ID(), null);
	    				if(workShift != null) {
	    					batch.setHR_WorkShift_ID(workShift.getHR_WorkShift_ID());
	    				}
	    			}
	    		} else {
	    			MHRShiftSchedule shiftSchedule = MHRShiftSchedule.getScheduleFromWorkGroup(batch.getCtx(), workGroup.getHR_WorkGroup_ID(), time, null);
	    			if(shiftSchedule != null) {
	    				workShiftId = shiftSchedule.getHR_WorkShift_ID();
	    				batch.setHR_WorkShift_ID(shiftSchedule.getHR_WorkShift_ID());
	    				batch.setHR_ShiftSchedule_ID(shiftSchedule.getHR_ShiftSchedule_ID());
	    			}
	    		}
	        }
	        if(employee.getHR_ShiftGroup_ID() > 0) {
	        	final String whereClause = I_HR_WorkShift.COLUMNNAME_HR_ShiftGroup_ID + " = ?";
	            List<Integer> workShiftIds = new Query(batch.getCtx(), I_HR_WorkShift.Table_Name, whereClause, null)
	                    .setParameters(employee.getHR_ShiftGroup_ID())
	                    .setOnlyActiveRecords(true)
	                    .setOrderBy(I_HR_WorkShift.COLUMNNAME_SeqNo)
	                    .getIDsAsList();
	            if(workShiftIds != null && workShiftIds.size() > 0) {
	            	for(int workShiftId : workShiftIds) {
	            		X_HR_WorkShift workShift = new X_HR_WorkShift(batch.getCtx(), workShiftId, null);
	            		if(TimeUtil.isValid(TimeUtil.getDayTime(day, workShift.getShiftFromTime()), TimeUtil.getDayTime(day, workShift.getShiftToTime()), timeAsDay)) {
	            			batch.setHR_WorkShift_ID(workShift.getHR_WorkShift_ID());
	            		}
	            	}
	            	if(batch.getHR_WorkShift_ID() <= 0) {
	            		batch.setHR_WorkShift_ID(workShiftIds.get(0));
	            	}
	            }
	        }
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
	    	int workShiftId = getWorkShiftIdFromEmployee(getCtx(), importAttendance.getAttendanceTime(), importAttendance.getHR_Employee_ID());
           	if(workShiftId <= 0) {
           		messageError.append(" @HR_WorkShift_ID@ @NotFound@");
           	}
	    	if (messageError.length() > 0) {
	    		setImportError(importAttendance, messageError.toString()).saveEx();
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
	    private List<Integer> getAttendanceIds(boolean isImported, boolean isProcessed, String transactionName) {
	        StringBuilder whereClause = new StringBuilder();
	        whereClause.append(I_I_HR_AttendanceRecord.COLUMNNAME_I_IsImported).append("=? AND ")
	                .append(I_I_HR_AttendanceRecord.COLUMNNAME_Processed).append("=?");

	        return new Query(getCtx(), I_I_HR_AttendanceRecord.Table_Name, whereClause.toString(), transactionName)
	                .setOnlyActiveRecords(true)
	                .setParameters(isImported, isProcessed)
	                .setOrderBy(I_I_HR_AttendanceRecord.COLUMNNAME_Code + ", " + I_I_HR_AttendanceRecord.COLUMNNAME_AttendanceTime)
	                .getIDsAsList();

	    }
}