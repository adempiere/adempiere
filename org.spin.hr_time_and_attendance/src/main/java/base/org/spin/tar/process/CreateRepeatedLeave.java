/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2017 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * or (at your option) any later version.										*
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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.adempiere.core.api.I_HR_LeaveAssign;
import org.adempiere.core.api.I_HR_LeaveReason;
import org.adempiere.core.api.I_HR_LeaveType;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHRLeave;
import org.eevolution.hr.model.MHRLeaveAssign;
import org.eevolution.hr.model.MHRLeaveReason;
import org.eevolution.hr.model.MHRLeaveType;
import org.spin.hr.util.TNAUtil;

/** 
 * Create Leave from Employee allocation for leave Type automatically
 * @author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class CreateRepeatedLeave extends CreateRepeatedLeaveAbstract {

	/**	Records created	*/
	private int created = 0;
	@Override
	protected String doIt() throws Exception {
		List<Object> parameters = new ArrayList<>();
		StringBuffer whereClause = new StringBuffer(I_HR_LeaveType.COLUMNNAME_IsLeaveRepeated).append(" = ").append(" ?");
		parameters.add(true);
		if(getLeaveTypeId() > 0) {
			parameters.add(getLeaveTypeId());
			whereClause.append(" AND ").append(I_HR_LeaveType.COLUMNNAME_HR_LeaveType_ID).append(" = ").append(" ?");
		}
		if(getLeaveReasonId() == 0) {
			MHRLeaveReason leaveReason = new Query(getCtx(), I_HR_LeaveReason.Table_Name, null, get_TrxName())
				.setClient_ID()
				.setOnlyActiveRecords(true)
				.first();
			if(leaveReason == null) {
				throw new AdempiereException("@HR_LeaveReason_ID@ @NotFound@");
			}
			setLeaveReasonId(leaveReason.getHR_LeaveReason_ID());
		}
		//	
		new Query(getCtx(), I_HR_LeaveType.Table_Name, whereClause.toString(), get_TrxName())
			.setParameters(parameters)
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.<MHRLeaveType>list().forEach(leaveType -> {
				processLeaveType(leaveType);
			});
		return "@Created@: " + created;
	}
	
	/**
	 * Process Leave Type
	 * @param leaveType
	 */
	private void processLeaveType(MHRLeaveType leaveType) {
		String durationType = TNAUtil.getDurationUnitFromFrequencyType(leaveType.getFrequencyType());
		//	Create
		if(Util.isEmpty(durationType)) {
			return;
		}
		if(durationType.equals(TimeUtil.DURATIONUNIT_Hour)) {
			durationType = TimeUtil.DURATIONUNIT_Day;
		}
		BigDecimal leaveDuration = leaveType.getLeaveDurationTime();
		if(leaveDuration == null
				|| leaveDuration.compareTo(Env.ZERO) <= 0) {
			leaveDuration = Env.ONE;
		}
		List<MHRLeaveAssign> leaveAssigList = new Query(getCtx(), I_HR_LeaveAssign.Table_Name, "HR_LeaveType_ID = ? "
				+ "AND ValidFrom <= ? "
				+ "AND (ValidTo >= ? OR ValidTo IS NULL) "
				+ "AND (DateLastRun < ? OR DateLastRun IS NULL)", get_TrxName())
			.setParameters(leaveType.getHR_LeaveType_ID(), getDateDoc(), getDateDoc(), getDateDoc())
			.setClient_ID()
			.setOnlyActiveRecords(true)
			.<MHRLeaveAssign>list();
		for(MHRLeaveAssign assignedLeave : leaveAssigList) {
			Timestamp dateLastRun = assignedLeave.getDateLastRun();
			if(dateLastRun == null) {
				dateLastRun = assignedLeave.getValidFrom();
			}
			dateLastRun = TimeUtil.getDay(dateLastRun);
			//	Validate
			Timestamp validationDate = TimeUtil.addDuration(dateLastRun, durationType, leaveDuration);
			if(!TimeUtil.isSameDay(validationDate, getDateDoc())) {
				continue;
			}
			//	Increase valid from
			MHRLeave leave = new MHRLeave(getCtx(), 0, get_TrxName());
			leave.setHR_LeaveType_ID(leaveType.getHR_LeaveType_ID());
			leave.setHR_LeaveAssign_ID(assignedLeave.getHR_LeaveAssign_ID());
			leave.setHR_LeaveReason_ID(getLeaveReasonId());
			leave.setDateDoc(getDateDoc());
			leave.setC_BPartner_ID(assignedLeave.getC_BPartner_ID());
			//	Save
			leave.saveEx();
			//	Process
			if(!leave.processIt(MHRLeave.DOCACTION_Prepare)) {
				throw new AdempiereException(leave.getProcessMsg());
			}
			leave.saveEx();
			addLog("@HR_Leave_ID@ " + leave.getDocumentNo() + " @DateDoc@: " + DisplayType.getDateFormat(DisplayType.Date).format(leave.getDateDoc()));
			assignedLeave.setDateLastRun(leave.getEndDate());
			assignedLeave.saveEx();
			created++;
		}
	}
}