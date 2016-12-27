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
            getAttribute(true, true).stream().forEach(importAttribute -> importAttribute.deleteEx(true));

        int count = 0;

        for (X_I_HR_Attribute importAttributes : getAttribute(false, false)) {

            final String partnerQuery = "SELECT C_BPartner_ID FROM C_BPartner WHERE TRIM(Value) = ?";
            int partnerId = DB.getSQLValue(null, partnerQuery, importAttributes.getValue().trim());
            if (partnerId < 0) {
                importAttributes.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@HR_Employee_ID@ @NotFound@"));
                importAttributes.saveEx();
                continue;
            }

            final String conceptQuery = "SELECT HR_Concept_ID FROM HR_Concept WHERE TRIM(Value) = ?";
            int conceptId = DB.getSQLValue(null, conceptQuery, importAttributes.getConceptValue().trim());
            if (conceptId < 0) {
                importAttributes.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@HR_Concept_ID@ @NotFound@"));
                importAttributes.saveEx();
                continue;
            }

            MHRConcept concept = new MHRConcept(getCtx(), conceptId, get_TrxName());

            if (importAttributes.getValidFrom() == null) {
                importAttributes.setI_ErrorMsg(Msg.parseTranslation(getCtx(), "@Invalid@ @ValidFrom@"));
                importAttributes.saveEx();
                continue;
            }

            X_HR_Attribute attribute = new X_HR_Attribute(getCtx(), 0, get_TrxName());
            attribute.setC_BPartner_ID(partnerId);
            attribute.setHR_Concept_ID(concept.get_ID());
            attribute.setColumnType(concept.getColumnType());
            //Set Amount
            Optional.ofNullable(importAttributes.getAmount()).filter(amount -> amount != null && amount.signum() > 0 ).ifPresent(amount -> attribute.setAmount(importAttributes.getAmount()));
            //Set Quantity
            Optional.ofNullable(importAttributes.getQty()).filter(quantity -> quantity != null && quantity.signum() > 0).ifPresent(quantity -> attribute.setQty(quantity));
            // Set Service Date
            Optional.ofNullable(importAttributes.getServiceDate()).ifPresent(serviceDate -> attribute.setServiceDate(serviceDate));
            //Set service data
            Optional.ofNullable(importAttributes.getTextMsg()).ifPresent(msgText -> attribute.setTextMsg(msgText));
            //Set msg text
            Optional.ofNullable(importAttributes.getMinValue()).filter(minValue -> minValue != null && minValue > 0).ifPresent(minValue -> attribute.setMinValue(minValue));
            //Set Max Value
            Optional.ofNullable(importAttributes.getMaxValue()).filter(maxValue -> maxValue != null && maxValue > 0).ifPresent(maxValue -> attribute.setMaxValue(maxValue));
            // Set Max Value
            Optional.ofNullable(importAttributes.getHR_Department_ID()).ifPresent(departamentId -> attribute.setHR_Department_ID(departamentId));
            // Set Min Value
            Optional.ofNullable(importAttributes.getHR_Job_ID()).ifPresent(jobId -> attribute.setHR_Job_ID(jobId));
            // Set valid dates
            Optional.ofNullable(importAttributes.getValidFrom()).ifPresent(validFrom -> attribute.setValidFrom(validFrom));
            Optional.ofNullable(importAttributes.getValidTo()).ifPresent(validTo -> attribute.setValidTo(validTo));
            // Set Rule Engine
            Optional.ofNullable(importAttributes.getAD_Rule_ID()).filter(ruleId -> ruleId != null && ruleId > 0).ifPresent(ruleId -> attribute.setAD_Rule_ID(ruleId));
            // Set Payroll
            Optional.ofNullable(importAttributes.getPayrollValue()).ifPresent(payrollValue -> {
                final String payrollQuery = "SELECT HR_Payroll_ID FROM HR_Payroll WHERE TRIM(Value) = ?";
                int payrollId = DB.getSQLValue(null, payrollQuery, payrollValue.trim());
                if (payrollId < 0) {
                    attribute.setHR_Payroll_ID(payrollId);
                }
            });
            attribute.setIsActive(true);
            if (attribute.save()) {
                importAttributes.setI_IsImported(true);
                importAttributes.setI_ErrorMsg("");
                importAttributes.setProcessed(true);
                importAttributes.setHR_Attribute_ID(attribute.get_ID());
                importAttributes.saveEx();
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
    private List<X_I_HR_Attribute> getAttribute(boolean isImported, boolean isProcessed) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_HR_Attribute.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_HR_Attribute.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), X_I_HR_Attribute.Table_Name, whereClause.toString(), get_TrxName())
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .list();

    }
}    //	HRCreateAPInvoice