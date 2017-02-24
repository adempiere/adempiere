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
import org.compiere.util.Trx;
import org.eevolution.model.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;;

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
            Arrays.stream(getAttributeIds(true,true, null)).forEach(recordId -> {
                X_I_HR_Attribute importAttribute = new X_I_HR_Attribute(getCtx(), recordId , null);
                importAttribute.deleteEx(true);
            });

        AtomicInteger importedRecord = new AtomicInteger(0);
        AtomicInteger withErrors = new AtomicInteger(0);
        Arrays.stream(getAttributeIds(false, false, null )).forEach(recordId -> {
            Trx.run( trxName -> {
                X_I_HR_Attribute importEmployeeAttribute = new X_I_HR_Attribute(getCtx(), recordId, trxName);
                fillIdValues(importEmployeeAttribute);
                if (importRecord(importEmployeeAttribute))
                    importedRecord.updateAndGet(record -> record + 1);
                else
                    withErrors.updateAndGet(error -> error + 1);
            });
        });
        return "@HR_Attribute_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
    }    //	doIt

    /**
     * Fill mandatory information
     *
     * @param importEmployeeAttribute
     */
    private void fillIdValues(X_I_HR_Attribute importEmployeeAttribute) {

            StringBuilder messageError = new StringBuilder();
            importEmployeeAttribute.setI_ErrorMsg("");
            final String partnerQuery = "SELECT C_BPartner_ID FROM C_BPartner WHERE TRIM(Value) = ?";
            int partnerId = DB.getSQLValue(null, partnerQuery, importEmployeeAttribute.getValue().trim());
            if (partnerId < 0)
                messageError.append("@HR_Employee_ID@ @NotFound@");

            final String conceptQuery = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(Value) = ?";
            int conceptId = DB.getSQLValue(importEmployeeAttribute.get_TrxName(), conceptQuery, importEmployeeAttribute.getConceptValue().trim());
            if (conceptId < 0)
                messageError.append(", ").append("@HR_Concept_ID@ @NotFound@");

            if (importEmployeeAttribute.getValidFrom() == null)
                messageError.append(", ").append("@Invalid@ @ValidFrom@");


        if (messageError != null && messageError.length() > 0)
            setImportError(importEmployeeAttribute, messageError.toString()).saveEx(importEmployeeAttribute.get_TrxName());

        importEmployeeAttribute.setC_BPartner_ID(partnerId);
        importEmployeeAttribute.setHR_Concept_ID(conceptId);
        importEmployeeAttribute.saveEx();
    }

    /**
     * set Import Error
     *
     * @param importEmployeeAttribute
     * @param error
     * @return
     */
    private X_I_HR_Attribute setImportError(X_I_HR_Attribute importEmployeeAttribute, String error) {
        importEmployeeAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
        addLog(importEmployeeAttribute.getHR_Attribute_ID(), importEmployeeAttribute.getValidFrom(), importEmployeeAttribute.getAmount(), importEmployeeAttribute.getI_ErrorMsg());
        return importEmployeeAttribute;
    }

    private boolean importRecord(X_I_HR_Attribute importEmployeeAttribute) {

         if (importEmployeeAttribute.getI_ErrorMsg() != null && importEmployeeAttribute.getI_ErrorMsg().length() > 0) {
            importEmployeeAttribute.setProcessed(false);
            importEmployeeAttribute.setI_IsImported(false);
            importEmployeeAttribute.saveEx();
            return false;
         }

        Optional<BigDecimal> optionalAmount = Optional.ofNullable(importEmployeeAttribute.getAmount());
        Optional<BigDecimal> optionalQuantity = Optional.ofNullable(importEmployeeAttribute.getQty());
        Optional<Timestamp> optionalServiceDate = Optional.ofNullable(importEmployeeAttribute.getServiceDate());
        Optional<String> optionalTextMsg = Optional.ofNullable(importEmployeeAttribute.getTextMsg());
        Optional<Integer> optionalMinValue = Optional.ofNullable(importEmployeeAttribute.getMinValue());
        Optional<Integer> optionalMaxValue = Optional.ofNullable(importEmployeeAttribute.getMaxValue());
        Optional<Integer> optionalDepartmentId = Optional.ofNullable(importEmployeeAttribute.getHR_Department_ID());
        Optional<Integer> optionalJobId = Optional.ofNullable(importEmployeeAttribute.getHR_Job_ID());
        Optional<Timestamp> optionalValidFrom = Optional.ofNullable(importEmployeeAttribute.getValidFrom());
        Optional<Timestamp> optionalValidTo = Optional.ofNullable(importEmployeeAttribute.getValidTo());
        Optional<Integer> optionalRuleId = Optional.ofNullable(importEmployeeAttribute.getAD_Rule_ID());
        Optional<MHRPayroll> optionalPayroll = Optional.ofNullable(MHRPayroll.getByValue(getCtx(), importEmployeeAttribute.getPayrollValue()));
        Optional<String> optinalReferenceNo = Optional.ofNullable(importEmployeeAttribute.getReferenceNo());
        int payrollId = optionalPayroll.isPresent() ? optionalPayroll.get().getHR_Payroll_ID() : 0;
        MHREmployee employee = MHREmployee.getActiveEmployee(getCtx(), importEmployeeAttribute.getC_BPartner_ID(), get_TrxName());
        MHRConcept concept = MHRConcept.get(getCtx(), importEmployeeAttribute.getHR_Concept_ID());
        Optional<MHRAttribute> optionalAttribute = Optional.ofNullable(
                MHRAttribute.getByConceptAndPartnerId(
                        concept,
                        importEmployeeAttribute.getC_BPartner_ID(),
                        payrollId,
                        importEmployeeAttribute.getReferenceNo(),
                        importEmployeeAttribute.getDescription(),
                        importEmployeeAttribute.getValidFrom())
        );

        MHRAttribute employeeAttribute = optionalAttribute.orElse(new MHRAttribute(importEmployeeAttribute));
        if (employeeAttribute.getHR_Attribute_ID() <= 0) {
            employeeAttribute.setColumnType(concept.getColumnType());
            employeeAttribute.setHR_Employee_ID(employee.getHR_Employee_ID());
            optionalValidFrom.ifPresent(validFrom -> employeeAttribute.setValidFrom(validFrom));
            optionalPayroll.ifPresent(payroll -> employeeAttribute.setHR_Payroll_ID(payroll.getHR_Payroll_ID()));
        }

        optinalReferenceNo.ifPresent(referenceNo -> employeeAttribute.setReferenceNo(referenceNo));
        optionalAmount.filter(amount -> amount != null && amount.signum() > 0).ifPresent(amount -> employeeAttribute.setAmount(amount));
        optionalQuantity.filter(quantity -> quantity != null && quantity.signum() > 0).ifPresent(quantity -> employeeAttribute.setQty(quantity));
        optionalServiceDate.ifPresent(serviceDate -> employeeAttribute.setServiceDate(serviceDate));
        optionalTextMsg.ifPresent(msgText -> employeeAttribute.setTextMsg(msgText));
        optionalMinValue.filter(minValue -> minValue != null && minValue > 0).ifPresent(minValue -> employeeAttribute.setMinValue(minValue));
        optionalMaxValue.filter(maxValue -> maxValue != null && maxValue > 0).ifPresent(maxValue -> employeeAttribute.setMaxValue(maxValue));
        optionalDepartmentId.ifPresent(departamentId -> employeeAttribute.setHR_Department_ID(departamentId));
        optionalJobId.ifPresent(jobId -> employeeAttribute.setHR_Job_ID(jobId));
        optionalValidTo.ifPresent(validTo -> employeeAttribute.setValidTo(validTo));
        optionalRuleId.filter(ruleId -> ruleId != null && ruleId > 0).ifPresent(ruleId -> employeeAttribute.setAD_Rule_ID(ruleId));
        employeeAttribute.setIsActive(true);
        employeeAttribute.saveEx();

        importEmployeeAttribute.setHR_Attribute_ID(employeeAttribute.get_ID());
        importEmployeeAttribute.setI_IsImported(true);
        importEmployeeAttribute.setI_ErrorMsg("");
        importEmployeeAttribute.setProcessed(true);
        importEmployeeAttribute.saveEx();
        return true;
    }

        /**
         * Get Import Attribute List
         * @param isImported
         * @param isProcessed
         * @return
         */
        private int[] getAttributeIds(boolean isImported, boolean isProcessed, String trxName) {
            StringBuilder whereClause = new StringBuilder();
            whereClause.append(I_I_HR_Attribute.COLUMNNAME_I_IsImported).append("=? AND ")
                    .append(I_I_HR_Attribute.COLUMNNAME_Processed).append("=?");

            return new Query(getCtx(), X_I_HR_Attribute.Table_Name, whereClause.toString(), trxName)
                    .setOnlyActiveRecords(true)
                    .setParameters(isImported, isProcessed)
                    .getIDs();

        }
}