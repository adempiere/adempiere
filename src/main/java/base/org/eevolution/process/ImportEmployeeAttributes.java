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
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.eevolution.model.MHRConcept;
import org.eevolution.model.X_HR_Attribute;
import org.eevolution.model.X_I_HR_Attribute;
import org.eevolution.model.I_I_HR_Attribute;

import java.util.List;
import java.util.logging.Level;

/**
 * Import Employee Attribute, this process allows import employee attribute for an employee
 *
 * @author oscar.gomez@www.e-evolution.com, e-Evolution
 * @author victor.perez@www.e-evolution.com, e-Evolution
 */
public class ImportEmployeeAttributes extends SvrProcess {

    protected boolean deleteOldImported = false;

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        for (ProcessInfoParameter para : getParameter()) {
            String name = para.getParameterName();
            if (para.getParameter() == null)
                ;
            if (para.getParameterName().equals("DeleteOldImported"))
                deleteOldImported = para.getParameter_ToAsBoolean();

            else
                log.log(Level.SEVERE, "Unknown Parameter: " + name);
        }
    }    //	prepare


    /**
     * Process
     *
     * @return message
     * @throws Exception
     */
    protected String doIt() throws Exception {

        String msg = "";

        int count = 0;
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_HR_Attribute.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_HR_Attribute.COLUMNNAME_Processed).append("=?");

        List<X_I_HR_Attribute> getAttribute = new Query(getCtx(), X_I_HR_Attribute.Table_Name, whereClause.toString(), get_TrxName())
                .setOnlyActiveRecords(true)
                .setParameters(false, false)
                .list();

        for (X_I_HR_Attribute importAttributes : getAttribute) {
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

            if (importAttributes.getAmount().signum() > 0 || importAttributes.getQty().signum() > 0) {
                X_HR_Attribute attribute = new X_HR_Attribute(getCtx(), 0, get_TrxName());
                attribute.setC_BPartner_ID(partnerId);
                attribute.setHR_Concept_ID(concept.get_ID());
                attribute.setColumnType(concept.getColumnType());
                attribute.setValidFrom(importAttributes.getValidFrom());
                if (importAttributes.getAmount().signum() > 0)
                    attribute.setAmount(importAttributes.getAmount());
                if (importAttributes.getQty().signum() > 0)
                    attribute.setQty(importAttributes.getQty());

                if (importAttributes.getValidTo() != null) {
                    attribute.setValidTo(importAttributes.getValidTo());
                }

                if (importAttributes.getPayrollValue() != null) {
                    final String payrollQuery = "SELECT HR_Payroll_ID FROM HR_Payroll WHERE TRIM(Value) = ?";
                    int payrollId = DB.getSQLValue(null, payrollQuery, importAttributes.getValue().trim());
                    if (payrollId < 0) {
                        attribute.setHR_Payroll_ID(payrollId);
                    }
                }

                attribute.setIsActive(true);
                if (attribute.save()) {
                    importAttributes.setI_IsImported(true);
                    importAttributes.setI_ErrorMsg("");
                    importAttributes.setProcessed(true);
                    importAttributes.setHR_Attribute_ID(attribute.get_ID());
                    importAttributes.saveEx();
                }
            }
        }
        return count + " @Records@ @ProcessOK@";
    }    //	doIt
}    //	HRCreateAPInvoice