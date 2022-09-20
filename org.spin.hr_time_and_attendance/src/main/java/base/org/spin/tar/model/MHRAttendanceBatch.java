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

import java.io.File;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.core.domains.models.I_HR_AttendanceRecord;
import org.adempiere.core.domains.models.X_HR_AttendanceBatch;
import org.adempiere.core.domains.models.X_HR_ShiftIncidence;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MPeriod;
import org.compiere.model.MRule;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.Query;
import org.compiere.model.Scriptlet;
import org.compiere.process.DocAction;
import org.compiere.process.DocOptions;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHRConcept;
import org.eevolution.hr.model.MHREmployee;
import org.eevolution.hr.model.MHRPayroll;
import org.eevolution.hr.model.MHRWorkGroup;
import org.eevolution.hr.model.MHRWorkShift;
import org.spin.hr.util.TNAUtil;

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
	/** the context for rules */
	HashMap<String, Object> scriptCtx = new HashMap<String, Object>();
	private MHRShiftIncidence shiftIncidence;
	private MHRAttendanceRecord attendance;
	private MHRAttendanceRecord firstAttendance;
	private MHRAttendanceRecord lastAttendance;
	private MHRIncidence incidence;
	private MHREmployee employee;
	private MHRWorkShift workShift;
	/**	Script to import	*/
	private static StringBuffer scriptImport = new StringBuffer(
			" import org.eevolution.model.*;" 
			+ Env.NL + "import org.compiere.model.*;"
			+ Env.NL + "import org.adempiere.model.*;"
			+ Env.NL + "import org.spin.model.*;"
			+ Env.NL + "import org.compiere.util.*;"
			+ Env.NL + "import org.spin.util.*;"
			+ Env.NL + "import java.util.*;" 
			+ Env.NL + "import java.math.*;"
			+ Env.NL + "import java.sql.*;");

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
		//	Validate and prepare attendance
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
				this.shiftIncidence = shiftIncidence;
				this.incidence = incidence;
				//	Process rule if it applied
				if(shiftIncidence.getAD_Rule_ID() > 0) {
					processRule(incidence);
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
		scriptCtx.remove("_LeaveDurationInMillis");
		scriptCtx.remove("_LeaveHours");
		scriptCtx.remove("_LeaveMinutes");
		scriptCtx.remove("_AttendanceDurationInMillis");
		scriptCtx.remove("_AttendanceHours");
		scriptCtx.remove("_AttendanceMinutes");
		//	Get Worked time
		List<MHRShiftIncidence> shiftIncidenceList = MHRShiftIncidence.getShiftIncidenceList(getCtx(), workShift.getHR_WorkShift_ID(), X_HR_ShiftIncidence.EVENTTYPE_Leave, getDateDoc());
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
		//	Duration in milliseconds
		final long durationInMillis = leaveHours.longValue() * (1000 * 60 * 60);
		final long attendanceDrationInMillis = attendanceHours.longValue() * (1000 * 60 * 60);
		//	Leave
		scriptCtx.put("_LeaveDurationInMillis", durationInMillis);
		scriptCtx.put("_LeaveHours", leaveHours.doubleValue());
		scriptCtx.put("_LeaveMinutes", TimeUtil.getMinutesFromDuration(durationInMillis));
		//	Attendance
		scriptCtx.put("_AttendanceDurationInMillis", attendanceDrationInMillis);
		scriptCtx.put("_AttendanceHours", attendanceHours.doubleValue());
		scriptCtx.put("_AttendanceMinutes", TimeUtil.getMinutesFromDuration(attendanceDrationInMillis));
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
			this.shiftIncidence = shiftIncidence;
			this.incidence = incidence;
			//	Process rule if it applied
			if(shiftIncidence.getAD_Rule_ID() > 0) {
				processRule(incidence);
			}
			incidence.saveEx();
		});
	}
	
	/**
	 * Load variables
	 */
	private void loadBatchVariables() {
		MBPartner businessPartner = (MBPartner) getC_BPartner();
		List<MHRAttendanceRecord> attendanceList = getLines(false);
		if(!isLeave()) {
			this.firstAttendance = attendanceList.stream().findFirst().get();
			this.lastAttendance = attendanceList.get(attendanceList.size() - 1);
		}
		//	
		setHR_Employee_ID(employee.getHR_Employee_ID());
		String employeePayrollValue = null;
		if(employee.getHR_Payroll_ID() != 0) {
			MHRPayroll employeePayroll = MHRPayroll.getById(getCtx(), employee.getHR_Payroll_ID(), get_TrxName());
			employeePayrollValue = employeePayroll.getValue();
		}
		workShift = MHRWorkShift.getById(getCtx(), getHR_WorkShift_ID());
		//	
		scriptCtx.put("_HR_FirstAttendanceRecord", firstAttendance);
		scriptCtx.put("_HR_LastAttendanceRecord", lastAttendance);
		if(firstAttendance != null) {
			scriptCtx.put("_FirstAttendanceTime", firstAttendance.getAttendanceTime());
		}
		if(lastAttendance != null) {
			scriptCtx.put("_LastAttendanceTime", lastAttendance.getAttendanceTime());
		}
		scriptCtx.put("_DateStart", employee.getStartDate());
		scriptCtx.put("_DateEnd", employee.getEndDate());
		scriptCtx.put("_C_BPartner_ID", businessPartner.getC_BPartner_ID());
		scriptCtx.put("_HR_Employee_ID", employee.getHR_Employee_ID());
		scriptCtx.put("_C_BPartner", businessPartner);
		scriptCtx.put("_HR_Employee", employee);
		scriptCtx.put("_HR_Employee_Payroll_Value", employeePayrollValue);
		//	Document
		scriptCtx.put("_DateDoc", getDateDoc());
		scriptCtx.put("_HR_AttendanceBatch_ID", getHR_AttendanceBatch_ID());
		scriptCtx.put("_HR_WorkShift_ID", getHR_WorkShift_ID());
		scriptCtx.put("_HR_ShiftSchedule_ID", getHR_ShiftSchedule_ID());
		scriptCtx.put("process", this);
		scriptCtx.put("_HR_WorkShift", workShift);
		scriptCtx.put("_ShiftFromTime", workShift.getShiftFromTime());
		scriptCtx.put("_ShiftToTime", workShift.getShiftToTime());
		scriptCtx.put("_BreakStartTime", workShift.getBreakStartTime());
		scriptCtx.put("_BreakEndTime", workShift.getBreakEndTime());
		BigDecimal breakHoursNo = workShift.getBreakHoursNo();
		BigDecimal hoursNo = workShift.getNoOfHours();
		if(breakHoursNo == null) {
			breakHoursNo = Env.ZERO;
		}
		if(hoursNo == null) {
			hoursNo = Env.ZERO;
		}
		scriptCtx.put("_BreakHoursNo", breakHoursNo.doubleValue());
		scriptCtx.put("_NoOfHours", hoursNo.doubleValue());
		scriptCtx.put("_ExpectedShiftFromTime", TimeUtil.getDayTime(getDateDoc(), workShift.getShiftFromTime()));
		scriptCtx.put("_ExpectedShiftToTime", TimeUtil.getDayTime(getDateDoc(), workShift.getShiftToTime()));
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
				//	
				int firstHours = (int) TimeUtil.getHoursBetween(TimeUtil.getDay(firstAttendance.getAttendanceTime()), firstAttendance.getAttendanceTime());
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
								this.shiftIncidence = shiftIncidence;
								this.incidence = incidence;
								//	Process rule if it applied
								if(shiftIncidence.getAD_Rule_ID() > 0) {
									processRule(incidence);
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
									|| (durationInMillis == 0 && shiftIncidence.isFixedValue())
									|| shiftIncidence.isVariableCalculation()) {
								MHRIncidence incidence = new MHRIncidence(this, shiftIncidence, durationInMillis);
								if(get_ValueAsInt("S_Contract_ID") > 0) {
									incidence.set_ValueOfColumn("S_Contract_ID", get_ValueAsInt("S_Contract_ID"));
								}
								if(get_ValueAsInt("S_ContractLine_ID") > 0) {
									incidence.set_ValueOfColumn("S_ContractLine_ID", get_ValueAsInt("S_ContractLine_ID"));
								}
								this.shiftIncidence = shiftIncidence;
								this.attendance = attendance;
								this.incidence = incidence;
								//	Process rule if it applied
								if(shiftIncidence.getAD_Rule_ID() > 0) {
									processRule(incidence);
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
	 * Process rule
	 * @param incidence
	 */
	private void processRule(MHRIncidence incidence) {
		scriptCtx.remove("_AttendanceTime");
		scriptCtx.remove("_HR_AttendanceRecord_ID");
		scriptCtx.remove("_HR_AttendanceRecord");
		scriptCtx.put("_HR_Concept_ID", shiftIncidence.getHR_Concept_ID());
		scriptCtx.put("_HR_ShiftIncidence_ID", shiftIncidence.getHR_ShiftIncidence_ID());
		//	Objects
		scriptCtx.put("_HR_ShiftIncidence", shiftIncidence);
		scriptCtx.put("_HR_Concept", MHRConcept.getById(getCtx(), shiftIncidence.getHR_Concept_ID(), get_TrxName()));
		scriptCtx.put("_EventType", shiftIncidence.getEventType());
		scriptCtx.put("_TimeFrom", shiftIncidence.getTimeFrom());
		scriptCtx.put("_TimeTo", shiftIncidence.getTimeTo());
		scriptCtx.put("_BeginningTime", shiftIncidence.getBeginningTime());
		scriptCtx.put("_TimeUnit", shiftIncidence.getTimeUnit());
		scriptCtx.put("_DefaultAmt", shiftIncidence.getDefaultAmt());
		scriptCtx.put("_DefaultQty", shiftIncidence.getDefaultQty());
		scriptCtx.put("_FixedAmt", shiftIncidence.getFixedAmt());
		scriptCtx.put("_FixedQty", shiftIncidence.getFixedQty());
		if(attendance != null) {
			scriptCtx.put("_AttendanceTime", attendance.getAttendanceTime());
			scriptCtx.put("_HR_AttendanceRecord_ID", attendance.getHR_AttendanceRecord_ID());
			scriptCtx.put("_HR_AttendanceRecord", attendance);
		}
		//	Run
		MRule rule = MRule.get(getCtx(), shiftIncidence.getAD_Rule_ID());
		try {
			if (rule == null) {
				log.log(Level.WARNING, " @AD_Rule_ID@ @NotFound@");
			}
			if (!(rule.getEventType().equals(MRule.EVENTTYPE_HumanResourcePayroll)
					&& rule.getRuleType().equals(MRule.RULETYPE_JSR223ScriptingAPIs))) {
				log.log(Level.WARNING, " must be of type JSR 223 and event human resource");
			}

			String text = "";
			String description = null;
			double result = 0.0;
			if (rule.getScript() != null) {
				text = rule.getScript().trim()
						.replaceAll("\\bget", "process.get")
						.replaceAll("\\bset", "process.set")
						.replace(".process.get", ".get")
						.replace(".process.set", ".set");
			}
			final String script =
					scriptImport.toString()
							+ Env.NL + "double result = 0;"
							+ Env.NL + "String description = null;"
							+ Env.NL + text;
			Scriptlet engine = new Scriptlet (Scriptlet.VARIABLE, script, scriptCtx);
			Exception ex = engine.execute();
			if (ex != null) {
				throw ex;
			}
			result = (double) engine.getResult(false);
			description = (String) engine.getDescription();
			//	Set result and description
			if(!Util.isEmpty(description)) {
				incidence.setDescription(description);
			}
			//	
			if(result != 0) {
				incidence.setQty(new BigDecimal(result));
			}
		} catch (Exception e) {
			throw new AdempiereException("@HR_Employee_ID@ : " + employee.getC_BPartner().getValue() + " " + employee.getC_BPartner().getName() 
			+ " \n @HR_WorkShift_ID@ " + workShift.getValue() + " -> " + workShift.getName()
			+ " \n @AD_Rule_ID@=" + rule.getValue() + "\n  @Script@: " + rule.getScript() + " \n @Error@" + Env.NL + e.getLocalizedMessage());
		}
	}
	
	/**
	 * Get Attendance Time
	 * @return
	 */
	public Timestamp getAttendanceTime() {
		return attendance.getAttendanceTime();
	}
	
	/**
	 * Get Incidence Amount
	 * @return
	 */
	public double getIncidenceAmt() {
		if(incidence.getAmt() == null) {
			return 0.0;
		}
		return incidence.getAmt().doubleValue();
	}
	
	/**
	 * Get Incidence Quantity
	 * @return
	 */
	public double getIncidenceQty() {
		if(incidence.getQty() == null) {
			return 0.0;
		}
		return incidence.getQty().doubleValue();
	}
	
	/**
	 * Getg Shift Incidence Fixed Quantity
	 * @return
	 */
	public double getShiftIncidenceFixedQty() {
		if(shiftIncidence.getFixedQty() == null) {
			return 0.0;
		}
		return shiftIncidence.getFixedQty().doubleValue();
	}
	
	/**
	 * Get fixed quantity from shift incidence
	 * @return
	 */
	public double getShiftIncidenceFixedAmt() {
		if(shiftIncidence.getFixedAmt() == null) {
			return 0.0;
		}
		return shiftIncidence.getFixedAmt().doubleValue();
	}
	
	/**
	 * Get Duration in milliseconds for attendance
	 * @return
	 */
	public long getAttendanceDurationInMillis() {
		return shiftIncidence.getDurationInMillis(attendance.getAttendanceTime());
	}
	
	/**
	 * Get Attendance Time Quantity
	 * @return
	 */
	public double getAttendanceTimeQty() {
		return MHRIncidence.getTime(shiftIncidence.getTimeUnit(), getAttendanceDurationInMillis());
	}
	
	/**
	 * Set Incidence Quantity
	 * @param quantity
	 */
	public void setIncidenceQty(double quantity) {
		incidence.setQty(new BigDecimal(quantity));
	}
	
	/**
	 * Set Amount for incidence
	 * @param amount
	 */
	public void setIncidenceAmt(double amount) {
		incidence.setAmt(new BigDecimal(amount));
	}
	
	/**********************************************************************************
	 * Helper Method for Get Amount from time and attendance record                   *
	 **********************************************************************************/
	
	/**
	 * Helper Method : Concept by range from-to a sum of incidence
	 * @param conceptValue
	 * @param workShiftValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getIncidenceSum(String conceptValue, String workShiftValue, Timestamp from, Timestamp to) {
		return TNAUtil.getIncidenceSum(getCtx(), conceptValue, workShiftValue, getC_BPartner_ID(), from, to, get_TrxName());
	} // getIncidence
	
	/**
	 * Helper Method : Concept by range from-to a sum of incidence
	 * @param conceptValue
	 * @param from
	 * @param to
	 * @return
	 */
	public double getIncidenceSum(String conceptValue, Timestamp from, Timestamp to) {
		return TNAUtil.getIncidenceSum(getCtx(), conceptValue, null, getC_BPartner_ID(), from, to, get_TrxName());
	} // getIncidence
	
	/***********************************************************************************
	 * Helper methods for get time from employee leave                                 *
	 **********************************************************************************/
	
	/**
	 * Helper method: get hours of leave with from and to date
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param excludeOverlapedTime
	 * @return
	 */
	public double getLeaveHoursBetween(String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime) {
		return TNAUtil.getLeaveHoursBetween(getCtx(), getC_BPartner_ID(), leaveTypeValue, from, to, excludeOverlapedTime, get_TrxName());
	}
	
	/**
	 * Helper method: get minutes of leave with from and to date
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param excludeOverlapedTime
	 * @return
	 */
	public int getLeaveMinutesBetween(String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime) {
		return TNAUtil.getLeaveMinutesBetween(getCtx(), getC_BPartner_ID(), leaveTypeValue, from, to, excludeOverlapedTime, get_TrxName());
	}
	
	/**
	 * Helper method: get days of leave with from and to date
	 * @param leaveTypeValue
	 * @param from
	 * @param to
	 * @param excludeOverlapedTime
	 * @return
	 */
	public int getLeaveDaysBetween(String leaveTypeValue, Timestamp from, Timestamp to, boolean excludeOverlapedTime) {
		return TNAUtil.getLeaveDaysBetween(getCtx(), getC_BPartner_ID(), leaveTypeValue, from, to, excludeOverlapedTime, get_TrxName());
	}
	
	/**
	 * Process Shift Incidence
	 * @return
	 */
	private String processShiftIncidence() {
		validateEmployee();
		validateWorkShift();
		StringBuffer errorMessage = new StringBuffer();
		//	 Validate pair
		int attendanceQuantity = getLines(false).size();
		if(attendanceQuantity % 2 != 0) {
			errorMessage.append("@TNA.AttendanceNotPair@");
		}
		setIsLeave(attendanceQuantity == 0);
		saveEx();
		//	For Quantity
		if(!isLeave()) {
			MHRWorkShift workShift = MHRWorkShift.getById(getCtx(), getHR_WorkShift_ID());
			if(workShift.getMinAttendanceRequire() > 0) {
				if(attendanceQuantity < workShift.getMinAttendanceRequire()) {
					errorMessage.append("@MinAttendanceRequire@");
				}
			}
		}
		deleteMovements();
		//	
		loadBatchVariables();
		//	validate leave
		if(isLeave()) {
			processLeave(Env.ZERO);
		} else {
			BigDecimal attendanceHours = processAttendance();
			processLeave(attendanceHours);
			processIncidence(attendanceHours);
		}
		//	Return Message
		if(errorMessage.length() > 0) {
			return errorMessage.toString();
		}
		//	default
		return null;
	}
	
	/**
	 * Get work shift for employee from:
	 * - Shift Schedule for a Work Group
	 * - Work Group add to employee configuration
	 * - Shift Work add to employee configuration
	 */
	private void validateWorkShift() {
		if(getHR_WorkShift_ID() > 0) {
			return;
		}
		MHREmployee employee = MHREmployee.getById(getCtx(), getHR_Employee_ID());
		int workShiftId = 0;
		int shiftScheduleId = 0;
		if(employee.getHR_WorkGroup_ID() > 0) {
			MHRWorkGroup workGroup = MHRWorkGroup.getById(getCtx(), employee.getHR_WorkGroup_ID(), get_TrxName());
			if(workGroup.isShiftAllocation()) {
				workShiftId = workGroup.getHR_WorkShift_ID();
				if(workShiftId == 0
						&& workGroup.getHR_ShiftGroup_ID() > 0) {
					MHRWorkShift workShift = MHRWorkShift.getDefaultFromGroup(getCtx(), workGroup.getHR_ShiftGroup_ID(), get_TrxName());
					if(workShift != null) {
						workShiftId = workShift.getHR_WorkShift_ID();
					}
				}
			} else {
				MHRShiftSchedule shiftSchedule = MHRShiftSchedule.getScheduleFromWorkGroup(getCtx(), workGroup.getHR_WorkGroup_ID(), getDateDoc(), get_TableName());
				if(shiftSchedule != null) {
					workShiftId = shiftSchedule.getHR_WorkShift_ID();
					shiftScheduleId = shiftSchedule.getHR_ShiftSchedule_ID();
				}
			}
		} else if(employee.getHR_ShiftGroup_ID() > 0) {
			MHRWorkShift workShift = MHRWorkShift.getDefaultFromGroup(getCtx(), employee.getHR_ShiftGroup_ID(), get_TrxName());
			if(workShift != null) {
				workShiftId = workShift.getHR_WorkShift_ID();
			}
		}
		//	Validate work shift
		if(workShiftId == 0) {
			throw new AdempiereException("@HR_WorkShift_ID@ @NotFound@");
		}
		//	Set
		setHR_WorkShift_ID(workShiftId);
		if(shiftScheduleId > 0) {
			setHR_ShiftSchedule_ID(shiftScheduleId);
		}
	}
	
	/**
	 * Validate Employee
	 */
	private void validateEmployee() {
		MBPartner businessPartner = (MBPartner) getC_BPartner();
		if(getHR_Employee_ID() > 0) {
			employee = MHREmployee.getById(getCtx(), getHR_Employee_ID());
		} else {
			employee = MHREmployee.getActiveEmployee(getCtx(), businessPartner.getC_BPartner_ID(), get_TrxName());
		}
		//	Validate null
		if(employee == null) {
			throw new AdempiereException("@HR_Employee_ID@ @NotFound@: " + businessPartner.getValue() + " - " + businessPartner.getName());
		}
		//	
		setHR_Employee_ID(employee.getHR_Employee_ID());
		saveEx();
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
		// Before Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_VOID);
		if (m_processMsg != null)
			return false;
		addDescription(Msg.getMsg(getCtx(), "Voided"));
		// After Void
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_VOID);
		if (m_processMsg != null)
			return false;

		setProcessed(true);
        setDocAction(DOCACTION_None);
		return true;
	}	//	voidIt
	
	/**
     *  Add to Description
     *  @param description text
     */
    public void addDescription (String description) {
        String desc = getDescription();
        if (desc == null)
            setDescription(description);
        else
            setDescription(desc + " | " + description);
    }   //  addDescription
	
	/**
	 * 	Close Document.
	 * 	Cancel not delivered Qunatities
	 * 	@return true if success 
	 */
	public boolean closeIt() {
		log.info("closeIt - " + toString());
		// Before Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_CLOSE);
		if (m_processMsg != null)
			return false;
		
		setProcessed(true);
		setDocAction(DOCACTION_None);
		
		// After Close
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_CLOSE);
		if (m_processMsg != null)
			return false;

		return true;
	}	//	closeIt
	
	/**
	 * 	Reverse Correction
	 * 	@return true if success 
	 */
	public boolean reverseCorrectIt()
	{
		log.info("reverseCorrectIt - " + toString());
		// Before reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSECORRECT);
		if (m_processMsg != null)
			return false;
		//	Void It
		voidIt();
		// After reverseCorrect
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSECORRECT);
		if (m_processMsg != null)
			return false;

		return false;
	}	//	reverseCorrectionIt
	
	/**
	 * 	Reverse Accrual - none
	 * 	@return true if success 
	 */
	public boolean reverseAccrualIt()
	{
		log.info("reverseAccrualIt - " + toString());
		// Before reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;
		//	Void It
		voidIt();
		// After reverseAccrual
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REVERSEACCRUAL);
		if (m_processMsg != null)
			return false;

		return false;
	}	//	reverseAccrualIt
	
	/** 
	 * 	Re-activate
	 * 	@return true if success 
	 */
	public boolean reActivateIt()
	{
		log.info("reActivateIt - " + toString());
		// Before reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_BEFORE_REACTIVATE);
		if (m_processMsg != null)
			return false;
		// After reActivate
		m_processMsg = ModelValidationEngine.get().fireDocValidate(this,ModelValidator.TIMING_AFTER_REACTIVATE);
		if (m_processMsg != null)
			return false;
		
		setDocAction(DOCACTION_Complete);
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