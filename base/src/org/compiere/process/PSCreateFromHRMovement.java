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
 * Copyright (C) 2003-2016 E.R.P. Consultores y Asociados, C.A.               *
 * All Rights Reserved.                                                       *
 * Contributor(s): Yamel Senih www.erpcya.com                                 *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.X_HR_Movement;
import org.adempiere.core.domains.models.X_HR_Process;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionLine;

/**
 * 	Payment Selection Create From Invoice, used for Smart Browse (Create From Payroll Movement)
 *	@author Yamel Senih, ysenih@erpcya.com, ERPCyA http://www.erpcya.com
 *		<li> FR [ 297 ] Payment Selection must be like ADempiere Document
 *		@see https://github.com/adempiere/adempiere/issues/297
 */
public class PSCreateFromHRMovement extends PSCreateFromHRMovementAbstract {

	/**	Sequence			*/
	private AtomicInteger sequence = new AtomicInteger(10);
	/**	Process Cache	*/
	private Map<Integer, X_HR_Process> payrollProcessMap = new HashMap<>();
	@Override
	protected void prepare() {
		super.prepare();
		//	Valid Record Identifier
		if(getRecord_ID() <= 0)
			throw new AdempiereException("@C_PaySelection_ID@ @NotFound@");
	}

	@Override
	protected String doIt() throws Exception {
		//	Instance current Payment Selection
		MPaySelection paySelection = new MPaySelection(getCtx(), getRecord_ID(), get_TrxName());
		sequence.set(paySelection.getLastLineNo());
		//	Loop for keys
		getSelectionKeys().forEach(key -> {
			//	get values from result set
			int movementId = key;
			String paymentRule = getSelectionAsString(key, "HRM_PaymentRule");
			BigDecimal sourceAmount = getSelectionAsBigDecimal(key, "HRM_Amount");
			BigDecimal convertedAmount = getSelectionAsBigDecimal(key, "HRM_ConvertedAmount");
			MPaySelectionLine line = new MPaySelectionLine(paySelection, sequence.getAndAdd(10), paymentRule);
			//	Add Order
			X_HR_Movement payrollMovement = new X_HR_Movement(getCtx(), movementId, get_TrxName());
			Optional<X_HR_Process> mybePayrollProcess = Optional.ofNullable(payrollProcessMap.get(payrollMovement.getHR_Process_ID()));
			X_HR_Process payrollProcess = mybePayrollProcess.orElseGet(() -> {
				X_HR_Process processFromMovement = new X_HR_Process(getCtx(), payrollMovement.getHR_Process_ID(), get_TrxName());
				payrollProcessMap.put(payrollMovement.getHR_Process_ID(), processFromMovement);
				return processFromMovement;
			});
			//	Set from Payroll Movement and conversion type
			line.setHRMovement(payrollMovement, payrollProcess.getC_ConversionType_ID(), sourceAmount, convertedAmount);
			//	Save
			line.saveEx();
		});
		//	Default Ok
		return "@OK@";
	}
}
