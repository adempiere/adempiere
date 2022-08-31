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

import java.math.BigDecimal;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MTimeExpense;
import org.compiere.model.MTimeExpenseLine;
import org.compiere.model.X_S_TimeExpense;
import org.compiere.util.Util;
import org.spin.tar.model.MHRIncidence;
import org.spin.tar.model.MHRShiftIncidence;
import org.spin.tar.model.X_HR_Incidence;

/** Generated Process for (Create Expense Report (From Incidence))
 *  @author ADempiere (generated) 
 *  @version Release 3.9.0
 */
public class CreateExpenseReportFromIncidence extends CreateExpenseReportFromIncidenceAbstract {
	
	/**	Expense Report	*/
	private MTimeExpense expenseReport = null;
	/**	Current BP	*/
	private int currentBPartnerId = 0;
	/**	Contract Id	*/
	private int currentContractId = 0;
	
	@Override
	protected String doIt() throws Exception {
		int created = 0;
		//	Loop for keys
		for(Integer key : getSelectionKeys()) {
			MHRIncidence incidence = new MHRIncidence(getCtx(), key, get_TrxName());
			MHRShiftIncidence shiftIncidence = new MHRShiftIncidence(getCtx(), incidence.getHR_ShiftIncidence_ID(), get_TrxName());
			if(!incidence.getDocStatus().equals(X_HR_Incidence.DOCSTATUS_Completed)) {
				continue;
			}
			int bPartnerId = incidence.getC_BPartner_ID();
			int contractId = incidence.get_ValueAsInt("S_Contract_ID");
			int contractLineId = incidence.get_ValueAsInt("S_ContractLine_ID");
			//	Create new
			if(expenseReport == null
					|| currentBPartnerId != bPartnerId
					|| currentContractId != contractId) {
				currentContractId = contractId;
				currentBPartnerId = bPartnerId;
				processDocument();
				createExpenseReport();
			}
			//	Set Quantity
			BigDecimal qty = getSelectionAsBigDecimal(key, "HI_Qty");
			MTimeExpenseLine expenseLine = new MTimeExpenseLine(getCtx(), 0, get_TrxName());
			expenseLine.setS_TimeExpense_ID(expenseReport.getS_TimeExpense_ID());
			expenseLine.setDateExpense(getDateReport());
			expenseLine.setIsTimeReport(true);
			boolean isInvoiced = (Util.isEmpty(getIsInvoiced())
					? shiftIncidence.isInvoiced()
							: getIsInvoiced().equals("Y"));
			expenseLine.setIsInvoiced(isInvoiced);
			//	Validate product
			if(shiftIncidence.getM_Product_ID() != 0) {
				expenseLine.setM_Product_ID(shiftIncidence.getM_Product_ID());
			}
			expenseLine.setQty(qty);
			expenseLine.setC_BPartner_ID(currentBPartnerId);
			if(contractLineId > 0) {
				expenseLine.set_ValueOfColumn("S_ContractLine_ID", contractLineId);
			}
			expenseLine.saveEx();
			//	
			incidence.setS_TimeExpense_ID(expenseReport.getS_TimeExpense_ID());
			incidence.setS_TimeExpenseLine_ID(expenseLine.getS_TimeExpenseLine_ID());
			incidence.saveEx();
			//	Process it
			created++;
		}
		//	Last
		processDocument();
		return "@Created@ " + created;
	}
	
	/**
	 * Process Expense
	 */
	private void processDocument() {
		if(expenseReport == null) {
			return;
		}
		//	Process Selection
		if(!expenseReport.processIt(getDocAction())) {
			throw new AdempiereException("@Error@ " + expenseReport.getProcessMsg());
		}
		//	
		expenseReport.saveEx();
		addLog(expenseReport.getS_TimeExpense_ID(), null, null, expenseReport.toString());
	}
	
	/**
	 * Create Expense Report
	 */
	private void createExpenseReport() {
		expenseReport = new MTimeExpense(getCtx(), 0, get_TrxName());
		expenseReport.setDocStatus(X_S_TimeExpense.DOCSTATUS_Drafted);
		expenseReport.setDocAction(X_S_TimeExpense.DOCACTION_Complete);
		expenseReport.setC_BPartner_ID(currentBPartnerId);
		expenseReport.setDateReport(getDateReport());
		expenseReport.setM_Warehouse_ID(getWarehouseId());
		expenseReport.setM_PriceList_ID(getPriceListId());
		if(currentContractId > 0) {
			expenseReport.set_ValueOfColumn("S_Contract_ID", currentContractId);
		}
		expenseReport.saveEx();
	}
	
}