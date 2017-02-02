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
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Oscar Gómez  www.e-evolution.com                           *
 * Contributor(s): Victor Pérez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.eevolution.model.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Import Employee Attribute, this process allows import employee attribute for an employee
 * @author oscar.gomez@www.e-evolution.com, e-Evolution
 * @author victor.perez@www.e-evolution.com, e-Evolution
 */
public class ImportEmployeeAttributes extends ImportEmployeeAttributesAbstract {
    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare


    /**
     * Process
     *
     * @return message
     * @throws Exception
     */
    protected String doIt() throws Exception {
        if (isDeleteoldimportedrecords())
            getAttributes(true, true).stream().forEach(importAttribute -> importAttribute.deleteEx(true));

        int count = 0;

        for (X_I_HR_Attribute importAttribute : getAttributes(false, false)) {

            final String partnerQuery = "SELECT C_BPartner_ID FROM C_BPartner WHERE TRIM(Value) = ?";
            int partnerId = DB.getSQLValue(null, partnerQuery, importAttribute.getValue().trim());
            if (partnerId < 0) {
                importAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@HR_Employee_ID@ @NotFound@"));
                importAttribute.saveEx();
                continue;
            }
            importAttribute.setC_BPartner_ID(partnerId);

            final String conceptQuery = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(Value) = ?";
            int conceptId = DB.getSQLValue(null, conceptQuery, importAttribute.getConceptValue().trim());
            if (conceptId < 0) {
                importAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@HR_Concept_ID@ @NotFound@"));
                importAttribute.saveEx();
                continue;
            }
            MHRConcept concept = new MHRConcept(getCtx(), conceptId, get_TrxName());
            importAttribute.setHR_Concept_ID(concept.getHR_Concept_ID());

            if (importAttribute.getValidFrom() == null) {
                importAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@Invalid@ @ValidFrom@"));
                importAttribute.saveEx();
                continue;
            }

            Optional<BigDecimal> optionalAmount = Optional.ofNullable(importAttribute.getAmount());
            Optional<BigDecimal> optionalQuantity = Optional.ofNullable(importAttribute.getQty());
            Optional<Timestamp> optionalServiceDate = Optional.ofNullable(importAttribute.getServiceDate());
            Optional<String> optionalTextMsg = Optional.ofNullable(importAttribute.getTextMsg());
            Optional<Integer> optionalMinValue = Optional.ofNullable(importAttribute.getMinValue());
            Optional<Integer> optionalMaxValue = Optional.ofNullable(importAttribute.getMaxValue());
            Optional<Integer> optionalDepartamentId = Optional.ofNullable(importAttribute.getHR_Department_ID());
            Optional<Integer> optionalJobId = Optional.ofNullable(importAttribute.getHR_Job_ID());
            Optional<Timestamp> optionalValidFrom = Optional.ofNullable(importAttribute.getValidFrom());
            Optional<Timestamp> optionalValidTo = Optional.ofNullable(importAttribute.getValidTo());
            Optional<Integer> optionalRuleId = Optional.ofNullable(importAttribute.getAD_Rule_ID());
            Optional<MHRPayroll> optionalPayroll = Optional.ofNullable(MHRPayroll.forValue(getCtx(), importAttribute.getPayrollValue()));
            Optional<String> optinalReferenceNo = Optional.ofNullable(importAttribute.getReferenceNo());
            int payrollId = optionalPayroll.isPresent() ? optionalPayroll.get().getHR_Payroll_ID() : 0;
            MHREmployee employee = MHREmployee.getActiveEmployee(getCtx() , partnerId , get_TrxName());
            Optional<MHRAttribute> optionalAttribute = Optional.ofNullable(
                    MHRAttribute.getByConceptAndPartnerId(
                            concept,
                            partnerId,
                            payrollId,
                            importAttribute.getReferenceNo(),
                            importAttribute.getDescription(),
                            importAttribute.getValidFrom())
            );
            MHRAttribute attribute = optionalAttribute.orElse(new MHRAttribute(importAttribute));
            if (attribute.getHR_Attribute_ID() <= 0) {
                attribute.setColumnType(concept.getColumnType());
                attribute.setHR_Employee_ID(employee.getHR_Employee_ID());
                optionalValidFrom.ifPresent(validFrom -> attribute.setValidFrom(validFrom));
                optionalPayroll.ifPresent(payroll -> attribute.setHR_Payroll_ID(payroll.getHR_Payroll_ID()));
            }

            optinalReferenceNo.ifPresent(referenceNo -> attribute.setReferenceNo(referenceNo));
            optionalAmount.filter(amount -> amount != null && amount.signum() > 0).ifPresent(amount -> attribute.setAmount(amount));
            optionalQuantity.filter(quantity -> quantity != null && quantity.signum() > 0).ifPresent(quantity -> attribute.setQty(quantity));
            optionalServiceDate.ifPresent(serviceDate -> attribute.setServiceDate(serviceDate));
            optionalTextMsg.ifPresent(msgText -> attribute.setTextMsg(msgText));
            optionalMinValue.filter(minValue -> minValue != null && minValue > 0).ifPresent(minValue -> attribute.setMinValue(minValue));
            optionalMaxValue.filter(maxValue -> maxValue != null && maxValue > 0).ifPresent(maxValue -> attribute.setMaxValue(maxValue));
            optionalDepartamentId.ifPresent(departamentId -> attribute.setHR_Department_ID(departamentId));
            optionalJobId.ifPresent(jobId -> attribute.setHR_Job_ID(jobId));
            optionalValidTo.ifPresent(validTo -> attribute.setValidTo(validTo));
            optionalRuleId.filter(ruleId -> ruleId != null && ruleId > 0).ifPresent(ruleId -> attribute.setAD_Rule_ID(ruleId));
            attribute.setIsActive(true);
            if (attribute.save()) {
                importAttribute.setI_IsImported(true);
                importAttribute.setI_ErrorMsg("");
                importAttribute.setProcessed(true);
                importAttribute.setHR_Attribute_ID(attribute.get_ID());
                importAttribute.saveEx();
            }
        }
        return count + " @Records@ @ProcessOK@";
    }    //	doIt

    /**
     * Get Import Attribute List
     * @param isImported
     * @param isProcessed
     * @return
     */
    private List<X_I_HR_Attribute> getAttributes(boolean isImported, boolean isProcessed) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_HR_Attribute.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_HR_Attribute.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), X_I_HR_Attribute.Table_Name, whereClause.toString(), get_TrxName())
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .list();

    }
}    //	HRCreateAPInvoice