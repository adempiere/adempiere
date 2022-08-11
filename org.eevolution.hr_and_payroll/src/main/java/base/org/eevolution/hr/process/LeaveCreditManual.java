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

package org.eevolution.hr.process;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;
import org.eevolution.hr.model.MHRLeave;
import org.eevolution.hr.model.MHRLeaveAssign;
import org.eevolution.hr.model.MHRLeaveType;
import org.spin.hr.util.TNAUtil;


/** 
 * 	Create Leave from Employee allocation for leave Type
 * 	@author Yamel Senih, ysenih@erpya.com, ERPCyA http://www.erpya.com
 */
public class LeaveCreditManual extends LeaveCreditManualAbstract {

	@Override
	protected String doIt() throws Exception {
		if(getNoOfLeavesAllocated() <= 0) {
			return "";
		}
		MHRLeaveAssign assignedLeave = new MHRLeaveAssign(getCtx(), getRecord_ID(), get_TrxName());
		MHRLeaveType leaveType = MHRLeaveType.getById(getCtx(), assignedLeave.getHR_LeaveType_ID(), get_TrxName());
		Timestamp validFrom = assignedLeave.getValidFrom();
		boolean isFromPreviousLeave = false;
		//	
		if(getValidFrom() != null) {
			if(!TimeUtil.isValid(validFrom, assignedLeave.getValidTo(), getValidFrom())) {
				throw new AdempiereException("@Invalid@ @ValidFrom@");
			}
			validFrom = getValidFrom();
		} else {	//	Get from last leave
			validFrom = assignedLeave.getDateLastRun();
			isFromPreviousLeave = true;
		}
		//
		if(validFrom == null) {
			validFrom = assignedLeave.getValidFrom();
		}
		//	
		int leaveToUse = assignedLeave.getBalance();
		if(getNoOfLeavesAllocated() < leaveToUse) {
			leaveToUse = getNoOfLeavesAllocated();
		}
		if(leaveToUse == 0) {
			throw new AdempiereException("@HR_LeaveAssign_ID@ @Used@");
		}
		String durationType = TNAUtil.getDurationUnitFromTimeUnit(leaveType.getTimeUnit());
		//	Create
		if(Util.isEmpty(durationType)) {
			throw new AdempiereException("@Invalid@ @TimeUnit@");
		}
		if(durationType.equals(TimeUtil.DURATIONUNIT_Hour)) {
			durationType = TimeUtil.DURATIONUNIT_Day;
		}
		BigDecimal leaveDuration = leaveType.getLeaveDurationTime();
		if(leaveDuration == null
				|| leaveDuration.compareTo(Env.ZERO) <= 0) {
			leaveDuration = Env.ONE;
		}
		if(isFromPreviousLeave) {
			validFrom = TimeUtil.addDuration(validFrom, durationType, leaveDuration);
		}
		//	
		for(int i = 0; i < leaveToUse; i++) {
			MHRLeave leave = new MHRLeave(getCtx(), 0, get_TrxName());
			leave.setHR_LeaveType_ID(leaveType.getHR_LeaveType_ID());
			leave.setHR_LeaveAssign_ID(assignedLeave.getHR_LeaveAssign_ID());
			leave.setHR_LeaveReason_ID(getLeaveReasonId());
			leave.setDateDoc(validFrom);
			leave.setC_BPartner_ID(assignedLeave.getC_BPartner_ID());
			//	Save
			leave.saveEx();
			//	Process
			if(!leave.processIt(getDocAction())) {
				throw new AdempiereException(leave.getProcessMsg());
			}
			leave.saveEx();
			addLog("@HR_Leave_ID@ " + leave.getDocumentNo() + " @DateDoc@: " + DisplayType.getDateFormat(DisplayType.Date).format(leave.getDateDoc()));
			//	Increase valid from
			validFrom = TimeUtil.addDuration(validFrom, durationType, leaveDuration);
		}
		assignedLeave.setDateLastRun(validFrom);
		assignedLeave.saveEx();
		return "@Created@: " + leaveToUse;
	}
}