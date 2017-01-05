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
import org.eevolution.model.I_I_HR_Attribute;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.X_HR_Attribute;
import org.eevolution.model.X_I_HR_Attribute;

import java.util.List;
import java.util.Optional;

/**
 * Import Employee Attribute, this process allows import employee attribute for an employee
 *
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

            final String conceptQuery = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(Value) = ?";
            int conceptId = DB.getSQLValue(null, conceptQuery, importAttribute.getConceptValue().trim());
            if (conceptId < 0) {
                importAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@HR_Concept_ID@ @NotFound@"));
                importAttribute.saveEx();
                continue;
            }

            MHRConcept concept = new MHRConcept(getCtx(), conceptId, get_TrxName());

            if (importAttribute.getValidFrom() == null) {
                importAttribute.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@Invalid@ @ValidFrom@"));
                importAttribute.saveEx();
                continue;
            }

            X_HR_Attribute attribute = new X_HR_Attribute(getCtx(), 0, get_TrxName());
            attribute.setC_BPartner_ID(partnerId);
            attribute.setHR_Concept_ID(concept.get_ID());
            attribute.setColumnType(concept.getColumnType());
            //Set Amount
            Optional.ofNullable(importAttribute.getAmount()).filter(amount -> amount != null && amount.signum() > 0 ).ifPresent(amount -> attribute.setAmount(importAttribute.getAmount()));
            //Set Quantity
            Optional.ofNullable(importAttribute.getQty()).filter(quantity -> quantity != null && quantity.signum() > 0).ifPresent(quantity -> attribute.setQty(quantity));
            // Set Service Date
            Optional.ofNullable(importAttribute.getServiceDate()).ifPresent(serviceDate -> attribute.setServiceDate(serviceDate));
            //Set service data
            Optional.ofNullable(importAttribute.getTextMsg()).ifPresent(msgText -> attribute.setTextMsg(msgText));
            //Set msg text
            Optional.ofNullable(importAttribute.getMinValue()).filter(minValue -> minValue != null && minValue > 0).ifPresent(minValue -> attribute.setMinValue(minValue));
            //Set Max Value
            Optional.ofNullable(importAttribute.getMaxValue()).filter(maxValue -> maxValue != null && maxValue > 0).ifPresent(maxValue -> attribute.setMaxValue(maxValue));
            // Set Max Value
            Optional.ofNullable(importAttribute.getHR_Department_ID()).ifPresent(departamentId -> attribute.setHR_Department_ID(departamentId));
            // Set Min Value
            Optional.ofNullable(importAttribute.getHR_Job_ID()).ifPresent(jobId -> attribute.setHR_Job_ID(jobId));
            // Set valid dates
            Optional.ofNullable(importAttribute.getValidFrom()).ifPresent(validFrom -> attribute.setValidFrom(validFrom));
            Optional.ofNullable(importAttribute.getValidTo()).ifPresent(validTo -> attribute.setValidTo(validTo));
            // Set Rule Engine
            Optional.ofNullable(importAttribute.getAD_Rule_ID()).filter(ruleId -> ruleId != null && ruleId > 0).ifPresent(ruleId -> attribute.setAD_Rule_ID(ruleId));
            // Set Payroll
            Optional.ofNullable(importAttribute.getPayrollValue()).ifPresent(payrollValue -> {
                final String payrollQuery = "SELECT HR_Payroll_ID FROM HR_Payroll WHERE TRIM(Value) = ?";
                int payrollId = DB.getSQLValue(null, payrollQuery, payrollValue.trim());
                if (payrollId < 0) {
                    attribute.setHR_Payroll_ID(payrollId);
                }
            });
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