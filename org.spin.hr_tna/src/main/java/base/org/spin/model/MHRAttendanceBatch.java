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
package org.spin.model;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.model.MHRWorkShift;

/**
 * 	Class added for handle Attendance batch
 * 	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<a href="https://github.com/adempiere/adempiere/issues/1870>
 * 		@see FR [ 1870 ] Add Calulation for Attendance Record</a>
 */
public class MHRAttendanceBatch extends X_HR_AttendanceBatch implements DocAction, DocOptions {

	/**
	 *
	 */
	private static final long serialVersionUID = 20180728L;
	/**	Lines						*/
	private List<MHRAttendanceRecord> attendanceRecordList = null;
	/** Standard documents	*/
	public static final String		DocBaseType_Standard = "TNA";

    /** Standard Constructor */
    public MHRAttendanceBatch (Properties ctx, int HR_AttendanceBatch_ID, String trxName)
    {
      super (ctx, HR_AttendanceBatch_ID, trxName);
    }

    /** Load Constructor */
    public MHRAttendanceBatch (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

	/**
	 * 	Get Document Info
	 *	@return document info (untranslated)
	 */
	public String getDocumentInfo()
	{
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		return dt.getName() + " " + getDocumentNo();
	}	//	getDocumentInfo

	/**
	 * 	Create PDF
	 *	@return File or null
	 */
	public File createPDF ()
	{
		try
		{
			File temp = File.createTempFile(get_TableName() + get_ID() +"_", ".pdf");
			return createPDF (temp);
		}
		catch (Exception e)
		{
			log.severe("Could not create PDF - " + e.getMessage());
		}
		return null;
	}	//	getPDF

	/**
	 * 	Create PDF file
	 *	@param file output file
	 *	@return file if success
	 */
	public File createPDF (File file)
	{
	//	ReportEngine re = ReportEngine.get (getCtx(), ReportEngine.INVOICE, getC_Invoice_ID());
	//	if (re == null)
			return null;
	//	return re.getPDF(file);
	}	//	createPDF

	
	/**************************************************************************
	 * 	Process document
	 *	@param processAction document action
	 *	@return true if performed
	 */
	public boolean processIt (String processAction)
	{
		m_processMsg = null;
		DocumentEngine engine = new DocumentEngine (this, getDocStatus());
		return engine.processIt (processAction, getDocAction());
	}	//	processIt
	
	/**	Process Message 			*/
	private String		m_processMsg = null;
	/**	Just Prepared Flag			*/
	private boolean		m_justPrepared = false;

	/**
	 * 	Unlock Document.
	 * 	@return true if success 
	 */
	public boolean unlockIt()
	{
		log.info("unlockIt - " + toString());
	//	setProcessing(false);
		return true;
	}	//	unlockIt
	
	/**
	 * 	Invalidate Document
	 * 	@return true if success 
	 */
	public boolean invalidateIt()
	{
		log.info("invalidateIt - " + toString());
		setDocAction(DOCACTION_Prepare);
		return true;
	}	//	invalidateIt
	
	/**
	 *	Prepare Document
	 * 	@return new status (In Progress or Invalid) 
	 */
	public String prepareIt()
	{
		log.info(toString());
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());

		//	Std Period open?
		if (!MPeriod.isOpen(getCtx(), getDateDoc(), dt.getDocBaseType(), getAD_Org_ID())) {
			m_processMsg = "@PeriodClosed@";
			return DocAction.STATUS_Invalid;
		}
		//	Validate and prepare atendance
		m_processMsg = processShiftIncidence();
		if (m_processMsg != null) {
			return DocAction.STATUS_Invalid;
		}
		//	Add up Amounts
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_PREPARE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		m_justPrepared = true;
		if (!DOCACTION_Complete.equals(getDocAction()))
			setDocAction(DOCACTION_Complete);
		return DocAction.STATUS_InProgress;
	}	//	prepareIt
	
	/**
	 * Set Document Type
	 */
	public void setC_DocType_ID() {
		String sql = "SELECT C_DocType_ID FROM C_DocType "
			+ "WHERE AD_Client_ID = ? AND AD_Org_ID IN (0," + getAD_Org_ID()
			+ ") AND DocBaseType = ? "
			+ " AND IsActive = 'Y' "
			+ "ORDER BY AD_Org_ID, IsDefault DESC";
		int C_DocType_ID = DB.getSQLValue(null, sql, getAD_Client_ID(), DocBaseType_Standard);
		if (C_DocType_ID <= 0) {
			log.severe ("Not found for AD_Client_ID=" + getAD_Client_ID () + ", DocBaseType=" + DocBaseType_Standard);
		} else {
			log.fine("(APS) - " + DocBaseType_Standard);
			setC_DocType_ID(C_DocType_ID);
		}
	}	//	setC_DocTypeTarget_ID
	
	@Override
	protected boolean beforeSave(boolean newRecord) {
		if(newRecord) {
			//	Set Document Type
			if(getC_DocType_ID() == 0) {
				setC_DocType_ID();
			}
		}
		return super.beforeSave(newRecord);
	}
	
	/**
	 * Process Attendance
	 * @return
	 */
	private BigDecimal processAttendance() {
		//	Get Worked time
		MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), getHR_WorkShift_ID());
		List<MHRShiftIncidence> shiftIncidenceList = MHRShiftIncidence.getShiftIncidenceList(getCtx(), workShift.getHR_WorkShift_ID(), X_HR_ShiftIncidence.EVENTTYPE_Attendance, getDateDoc());
		BigDecimal attendanceHours = Env.ZERO;
		for(MHRShiftIncidence shiftIncidence : shiftIncidenceList) {
			long durationInMillis = 0;
			long startTime = 0;
			long endTime = 0;
			for(MHRAttendanceRecord attendance : getLines(false)) {
				if(startTime == 0) {
					startTime = attendance.getAttendanceTime().getTime();
				} else {
					endTime = attendance.getAttendanceTime().getTime();
				}
				//	sum it
				if(startTime != 0
						&& endTime != 0) {
					durationInMillis += (endTime - startTime);
					endTime = 0;
					startTime = 0;
				}
			}
			//	Create Incidence
			if(durationInMillis != 0) {
				MHRIncidence incidence = new MHRIncidence(this, shiftIncidence, durationInMillis);
				if(get_ValueAsInt("S_Contract_ID") > 0) {
					incidence.set_ValueOfColumn("S_Contract_ID", get_ValueAsInt("S_Contract_ID"));
				}
				if(get_ValueAsInt("S_ContractLine_ID") > 0) {
					incidence.set_ValueOfColumn("S_ContractLine_ID", get_ValueAsInt("S_ContractLine_ID"));
				}
				incidence.saveEx();
				//	Set value for worked hours
				attendanceHours = attendanceHours.add(new BigDecimal(MHRIncidence.getTime(MHRShiftIncidence.TIMEUNIT_Hour, durationInMillis)));
			}
		}
		//	Return 
		return attendanceHours;
	}
	
	
	/**
	 * Process Leave
	 * @param attendanceHours
	 */
	private void processLeave(BigDecimal attendanceHours) {
		//	Get Worked time
		MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), getHR_WorkShift_ID());
		List<MHRShiftIncidence> shiftIncidenceList = MHRShiftIncidence.getShiftIncidenceList(getCtx(), workShift.getHR_WorkShift_ID(), X_HR_ShiftIncidence.EVENTTYPE_Attendance, getDateDoc());
		//	Get from work shift
		BigDecimal noOfHours = workShift.getNoOfHours();
		if(noOfHours == null) {
			return;
		}
		//	Leave value
		BigDecimal leaveHours = noOfHours.subtract(attendanceHours);
		if(leaveHours.signum() <= 0) {
			return;
		}
		//	Duration in millis
		final long durationInMillis = leaveHours.longValue() * (1000 * 60 * 60);
		//	Create record
		shiftIncidenceList.stream().forEach(shiftIncidence -> {
			//	Create Incidence
			MHRIncidence incidence = new MHRIncidence(this, shiftIncidence, durationInMillis);
			if(get_ValueAsInt("S_Contract_ID") > 0) {
				incidence.set_ValueOfColumn("S_Contract_ID", get_ValueAsInt("S_Contract_ID"));
			}
			if(get_ValueAsInt("S_ContractLine_ID") > 0) {
				incidence.set_ValueOfColumn("S_ContractLine_ID", get_ValueAsInt("S_ContractLine_ID"));
			}
			incidence.saveEx();
		});
	}
	
	/**
	 * Process Incidence
	 * @param attendanceHours
	 */
	private void processIncidence(BigDecimal attendanceHours) {
		//	Get Worked time
		MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), getHR_WorkShift_ID());
		//	For Variable entrance
		if(workShift.isVariableEntrance()) {
			if(attendanceHours != null
					&& attendanceHours.doubleValue() >= workShift.getNoOfHours().doubleValue()) {
				List<MHRAttendanceRecord> attendanceList = getLines(false);
				MHRAttendanceRecord firstAttendance = attendanceList.get(0);
				MHRAttendanceRecord lastAttendance = attendanceList.get(attendanceList.size() - 1);
				//	
				int firstHours = TimeUtil.getHoursBetween(TimeUtil.getDay(firstAttendance.getAttendanceTime()), firstAttendance.getAttendanceTime());
				//	
				Timestamp beginningTime = TimeUtil.addDuration(firstAttendance.getAttendanceTime(), TimeUtil.DURATIONUNIT_Hour, 
						workShift.getNoOfHours()
						.add(workShift.getBreakHoursNo())
						.add(new BigDecimal(firstHours)));
				//	Get incidence from attendance
				MHRShiftIncidence.getShiftIncidenceList(getCtx(), workShift.getHR_WorkShift_ID(), 
						X_HR_ShiftIncidence.EVENTTYPE_Egress, getDateDoc()).stream()
					.filter(shiftIncidence -> shiftIncidence.evaluateTime(lastAttendance.getAttendanceTime()))
						.forEach(shiftIncidence -> {
							shiftIncidence.setBeginningTime(beginningTime);
							long durationInMillis = shiftIncidence.getDurationInMillis(lastAttendance.getAttendanceTime());
							if(durationInMillis > 0
									|| (durationInMillis == 0 && shiftIncidence.isFixedValue())) {
								MHRIncidence incidence = new MHRIncidence(this, shiftIncidence, durationInMillis);
								if(get_ValueAsInt("S_Contract_ID") > 0) {
									incidence.set_ValueOfColumn("S_Contract_ID", get_ValueAsInt("S_Contract_ID"));
								}
								if(get_ValueAsInt("S_ContractLine_ID") > 0) {
									incidence.set_ValueOfColumn("S_ContractLine_ID", get_ValueAsInt("S_ContractLine_ID"));
								}
								incidence.saveEx();
							}
				});
			}
		} else {
			//	Create Incidence for extra
			boolean isEntrance = true;
			for(MHRAttendanceRecord attendance : getLines(false)) {
				List<MHRShiftIncidence> shiftIncidenceList = MHRShiftIncidence.getShiftIncidenceList(getCtx(), workShift.getHR_WorkShift_ID(), 
						isEntrance? 
						X_HR_ShiftIncidence.EVENTTYPE_Entrance: 
							X_HR_ShiftIncidence.EVENTTYPE_Egress, getDateDoc());
				//	Get incidence from attendance
				shiftIncidenceList.stream()
					.filter(shiftIncidence -> shiftIncidence.evaluateTime(attendance.getAttendanceTime()))
						.forEach(shiftIncidence -> {
							long durationInMillis = shiftIncidence.getDurationInMillis(attendance.getAttendanceTime());
							if(durationInMillis > 0
									|| (durationInMillis == 0 && shiftIncidence.isFixedValue())) {
								MHRIncidence incidence = new MHRIncidence(this, shiftIncidence, durationInMillis);
								if(get_ValueAsInt("S_Contract_ID") > 0) {
									incidence.set_ValueOfColumn("S_Contract_ID", get_ValueAsInt("S_Contract_ID"));
								}
								if(get_ValueAsInt("S_ContractLine_ID") > 0) {
									incidence.set_ValueOfColumn("S_ContractLine_ID", get_ValueAsInt("S_ContractLine_ID"));
								}
								incidence.saveEx();
							}
				});
				//	Change event type
				isEntrance = !isEntrance;
			}
		}
	}
	
	/**
	 * Process Shift Incidence
	 * @return
	 */
	private String processShiftIncidence() {
		StringBuffer errorMessage = new StringBuffer();
		//	 Validate pair
		int attendanceQuantity = getLines(false).size();
		if(attendanceQuantity == 0) {
			return "@NoLines@";
		}
		if(attendanceQuantity % 2 != 0) {
			errorMessage.append("@TNA.AttendanceNotPair@");
		}
		//	For Quantity
		MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), getHR_WorkShift_ID());
		if(workShift.getMinAttendanceRequire() > 0) {
			if(attendanceQuantity < workShift.getMinAttendanceRequire()) {
				errorMessage.append("@MinAttendanceRequire@");
			}
		}
		deleteMovements();
		//	
		BigDecimal attendanceHours = processAttendance();
		processLeave(attendanceHours);
		processIncidence(attendanceHours);
		//	Return Message
		if(errorMessage.length() > 0) {
			return errorMessage.toString();
		}
		//	default
		return null;
	}
	
	/**
	 * Delete generated movements
	 */
	private void deleteMovements() {
		//	Delete Old
		int deleted = DB.executeUpdateEx("DELETE FROM HR_Incidence WHERE IsManual = 'N' AND HR_AttendanceBatch_ID = " + getHR_AttendanceBatch_ID(), get_TrxName());
		log.info("Incidences Deleted = " + deleted);	
	}
	
	/**
	 * 	Approve Document
	 * 	@return true if success 
	 */
	public boolean  approveIt()
	{
		log.info("approveIt - " + toString());
		setIsApproved(true);
		return true;
	}	//	approveIt
	
	/**
	 * 	Reject Approval
	 * 	@return true if success 
	 */
	public boolean rejectIt()
	{
		log.info("rejectIt - " + toString());
		setIsApproved(false);
		return true;
	}	//	rejectIt
	
	/**
	 * 	Complete Document
	 * 	@return new status (Complete, In Progress, Invalid, Waiting ..)
	 */
	public String completeIt()
	{
		//	Re-Check
		if (!m_justPrepared)
		{
			String status = prepareIt();
			if (!DocAction.STATUS_InProgress.equals(status))
				return status;
		}

		m_processMsg = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_BEFORE_COMPLETE);
		if (m_processMsg != null)
			return DocAction.STATUS_Invalid;
		
		//	Implicit Approval
		if (!isApproved())
			approveIt();
		log.info(toString());
		//
		
		//	User Validation
		String valid = ModelValidationEngine.get().fireDocValidate(this, ModelValidator.TIMING_AFTER_COMPLETE);
		if (valid != null)
		{
			m_processMsg = valid;
			return DocAction.STATUS_Invalid;
		}
		//	Set Definitive Document No
		setDefiniteDocumentNo();

		setProcessed(true);
		setDocAction(DOCACTION_Close);
		return DocAction.STATUS_Completed;
	}	//	completeIt
	
	/**
	 * 	Set the definite document number after completed
	 */
	private void setDefiniteDocumentNo() {
		MDocType dt = MDocType.get(getCtx(), getC_DocType_ID());
		if (dt.isOverwriteDateOnComplete()) {
			setDateDoc(new Timestamp(System.currentTimeMillis()));
		}
		if (dt.isOverwriteSeqOnComplete()) {
			String value = null;
			int index = p_info.getColumnIndex("C_DocType_ID");
			if (index == -1)
				index = p_info.getColumnIndex("C_DocTypeTarget_ID");
			if (index != -1)		//	get based on Doc Type (might return null)
				value = DB.getDocumentNo(get_ValueAsInt(index), get_TrxName(), true);
			if (value != null) {
				setDocumentNo(value);
			}
		}
	}

	/**
	 * 	Void Document.
	 * 	Same as Close.
	 * 	@return true if success 
	 */
	public boolean voidIt()
	{
		log.info("voidIt - " + toString());
		return closeIt();
	}	//	voidIt
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt()
	{
		log.info("closeIt - " + toString());

		//	Close Not delivered Qty
		setDocAction(DOCACTION_None);
		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		deleteMovements();
		setProcessed(false);
		return true;
	}	//	reActivateIt
	
	
	/*************************************************************************
	 * 	Get Summary
	 *	@return Summary of Document
	 */
	public String getSummary()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(getDocumentNo());
	//	sb.append(": ")
	//		.append(Msg.translate(getCtx(),"TotalLines")).append("=").append(getTotalLines())
	//		.append(" (#").append(getLines(false).length).append(")");
		//	 - Description
		if (getDescription() != null && getDescription().length() > 0)
			sb.append(" - ").append(getDescription());
		return sb.toString();
	}	//	getSummary
	
	/**
	 * Set Processed to Line and header
	 */
	public void setProcessed(boolean processed) {
		super.setProcessed(processed);
		//	Change Processed in line
		for(MHRAttendanceRecord attendanceLine : getLines(true)) {
			attendanceLine.setProcessed(processed);
			attendanceLine.saveEx();
		}
	}
	
	/**
	 * 	Get Lines
	 *	@param requery requery
	 *	@return lines
	 */
	public List<MHRAttendanceRecord> getLines(boolean requery) {
		if (attendanceRecordList != null 
				&& !requery) {
			attendanceRecordList.stream()
					.filter(attendanceLine -> attendanceLine != null )
					.forEach(attendanceLine -> attendanceLine.set_TrxName(get_TrxName()));
			return attendanceRecordList;
		}
		//	
		return getLines(null, null);
	}	//	getLines
	
	/**
	 * Get Lines with where clause and order by clause, re-query
	 * @param whereClause
	 * @param orderClause
	 * @return
	 */
	public List<MHRAttendanceRecord> getLines(String whereClause, String orderClause) {
		//FR: [ 2214883 ] Remove SQL code and Replace for Query - red1
		StringBuffer whereClauseFinal = new StringBuffer(COLUMNNAME_HR_AttendanceBatch_ID+" = ? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (Util.isEmpty(orderClause, true))
			orderClause = I_HR_AttendanceRecord.COLUMNNAME_SeqNo;
		attendanceRecordList = new Query(getCtx(),
				I_HR_AttendanceRecord.Table_Name, whereClauseFinal.toString(), get_TrxName())
			.setParameters(getHR_AttendanceBatch_ID())
			.setOrderBy(orderClause)
			.list();
		return attendanceRecordList;
	}

	/**
	 * 	Get Process Message
	 *	@return clear text error message
	 */
	public String getProcessMsg()
	{
		return m_processMsg;
	}	//	getProcessMsg
	
	/**
	 * 	Get Document Owner (Responsible)
	 *	@return AD_User_ID
	 */
	public int getDoc_User_ID()
	{
	//	return getSalesRep_ID();
		return 0;
	}	//	getDoc_User_ID

	/**
	 * 	Get Document Approval Amount
	 *	@return amount
	 */
	public BigDecimal getApprovalAmt()
	{
		return null;	//getTotalLines();
	}	//	getApprovalAmt
	
	/**
	 * 	Get Document Currency
	 *	@return C_Currency_ID
	 */
	public int getC_Currency_ID()
	{
	//	MPriceList pl = MPriceList.get(getCtx(), getM_PriceList_ID());
	//	return pl.getC_Currency_ID();
		return 0;
	}	//	getC_Currency_ID
	
	@Override
	public int customizeValidActions(String docStatus, Object processing,
			String orderType, String isSOTrx, int AD_Table_ID,
			String[] docAction, String[] options, int index) {
		//	Valid Document Action
		if (AD_Table_ID == Table_ID){
			if (docStatus.equals(DocumentEngine.STATUS_Drafted)
					|| docStatus.equals(DocumentEngine.STATUS_InProgress)
					|| docStatus.equals(DocumentEngine.STATUS_Invalid)) {
					options[index++] = DocumentEngine.ACTION_Prepare;
				}
				//	Complete                    ..  CO
				else if (docStatus.equals(DocumentEngine.STATUS_Completed)) {
					options[index++] = DocumentEngine.ACTION_ReActivate;
					options[index++] = DocumentEngine.ACTION_Void;
				}
		}
		//	Default
		return index;
	}

    @Override
    public String toString()
    {
      StringBuffer sb = new StringBuffer ("MHRAttendanceBatch[")
        .append(getSummary()).append("]");
      return sb.toString();
    }
}