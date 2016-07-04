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
 * Contributor(s):Victor Perez www.e-evolution.com 				              *
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 *****************************************************************************/
package org.eevolution.process;

import org.compiere.model.I_C_BPartner;
import org.compiere.model.Query;
import org.eevolution.model.MHREmployee;
import org.eevolution.model.MHRMovement;
import org.eevolution.model.MHRPaySelection;
import org.eevolution.model.MHRPaySelectionLine;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author victor.perez@e-evolution.com, www.e-evolution.com
 */
public class HRPaySelectionCreateFrom extends HRPaySelectionCreateFromAbstract {
    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
        getProcessInfo().setTable_ID(MHRPaySelection.Table_ID);
    }    //	prepare

    /**
     * Perform process.
     * @return Message
     * @throws Exception if not successful
     */
    protected String doIt() throws Exception {
        log.info("Pay Selection Id=" + getRecord_ID()
                + ", Process=" + getPayrollProcessId()
                + ", Payroll=" + getPayrollId()
                + ", BP Group=" + getBusinessPartnerGroupId()
                + ", PaymentRule=" + getPaymentRule()
                + ", Concept=" + getGlobalPayrollConceptId()
                + ", Depatment=" + getDepartmentId()
                + ", Job=" + getPayrollJobId());

        MHRPaySelection paySelection = (MHRPaySelection) getInstance(get_TrxName());
        paySelection.setHR_Process_ID(getPayrollProcessId());
        paySelection.saveEx();

        List<Object> parameters = new ArrayList<Object>();
        if (getRecord_ID() == 0)
            throw new IllegalArgumentException("@HR_PaySelection_ID@ @Notfound@");
        if (paySelection.isProcessed())
            throw new IllegalArgumentException("@HR_PaySelection_ID@ @Processed@");

        parameters.add(getPayrollProcessId());
        parameters.add(true);
        parameters.add(getRecord_ID());

        StringBuilder where = new StringBuilder();
        where.append(MHRMovement.COLUMNNAME_HR_Process_ID).append("=?");
        where.append(" AND HR_Concept_ID IN(SELECT HR_Concept_ID FROM HR_Concept WHERE IsPaid=?)");    // Only Concept isPaid
        where.append(" AND HR_Movement_ID NOT IN(SELECT HR_Movement_ID " + // Not Exist in PaySelection Process or PaySelection Actual
                " FROM HR_PaySelectionLine " +
                " WHERE HR_PaySelectionCheck_ID > 0 OR HR_PaySelection_ID=?)");
        if (getBusinessPartnerGroupId() > 0) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_C_BP_Group_ID).append("=?");
            parameters.add(getBusinessPartnerGroupId());
        }
        if (getEmployeeTypeId() > 0) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_HR_EmployeeType_ID).append("=?");
            parameters.add(getEmployeeTypeId());
        }
        if (getBusinessPartnerId() > 0) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_C_BPartner_ID).append("=?");
            parameters.add(getBusinessPartnerId());
        }
        if (getPaymentRule() != null) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_PaymentRule).append("=?");
            parameters.add(getPaymentRule());
        }
        if (getGlobalPayrollConceptId() > 0) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_HR_Concept_ID).append("=?");
            parameters.add(getGlobalPayrollConceptId());
        }
        if (getDepartmentId() > 0) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_HR_Department_ID).append("=?");
            parameters.add(getDepartmentId());
        }
        if (getPayrollJobId() > 0) {
            where.append(" AND ").append(MHRMovement.COLUMNNAME_HR_Job_ID).append("=?");
            parameters.add(getPayrollJobId());
        }

        List<MHRMovement> movements = new Query(getCtx(), MHRMovement.Table_Name, where.toString(), get_TrxName())
                .setClient_ID()
                .setParameters(parameters)
                .list();
        AtomicInteger lineNo = new AtomicInteger();
        movements.stream()
                .filter(movement -> movement != null).forEach(movement -> {
            I_C_BPartner partner = movement.getC_BPartner();
            Optional<MHREmployee> employeeOption = Optional.ofNullable( MHREmployee.getActiveEmployee(getCtx(), partner.getC_BPartner_ID(), movement.get_TrxName()));
            String paymentRule = employeeOption
                    .flatMap(employee -> Optional.ofNullable(employee.getPaymentRule())) // if the employee exists and has a payment rule then is used
                    .orElse(Optional.ofNullable(partner.getPaymentRule()) // if has not then get partner payment rule
                    .orElse(MHREmployee.PAYMENTRULE_DirectDeposit)); // if has not then use default

            lineNo.updateAndGet(count -> count + 10);
            MHRPaySelectionLine paySelectionLine = new MHRPaySelectionLine(getCtx(), 0, get_TrxName());
            paySelectionLine.setHR_PaySelection_ID(getRecord_ID());
            paySelectionLine.setHR_Movement_ID(movement.getHR_Movement_ID());
            paySelectionLine.setPaymentRule(paymentRule);
            paySelectionLine.setAD_Org_ID(paySelection.getAD_Org_ID());
            paySelectionLine.setLine(lineNo.get());
            paySelectionLine.setOpenAmt(movement.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            paySelectionLine.setPayAmt(movement.getAmount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
            paySelectionLine.setDescription(partner.getName() + " " + partner.getName2());
            paySelectionLine.setDifferenceAmt(BigDecimal.ZERO.ZERO);
            paySelectionLine.setDiscountAmt(BigDecimal.ZERO);
            paySelectionLine.setIsManual(false);
            paySelectionLine.setIsSOTrx(false);
            paySelectionLine.setIsActive(true);
            paySelectionLine.saveEx();

        });
        return "@HR_PaySelection_ID@  - #" + lineNo.get();
    }    //	doIt

}