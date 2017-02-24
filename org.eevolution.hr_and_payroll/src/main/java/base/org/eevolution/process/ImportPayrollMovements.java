/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2016 ADempiere Foundation, All Rights Reserved.         *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * or via info@adempiere.net or http://www.adempiere.net/license.html         *
 * Copyright (C) 2003-2014 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): Victor Pérez www.e-evolution.com                           *
 *****************************************************************************/
package org.eevolution.process;

import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Import Payroll Movements
 * author victor.perez@e-evolution.com, www-e-evolution.com
 */
public class ImportPayrollMovements extends ImportPayrollMovementsAbstract {

    /**
     * Prepare - e.g., get Parameters.
     */
    protected void prepare() {
        super.prepare();
    }    //	prepare

    /**
     * Perform process.
     *
     * @return Message
     * @throws Exception
     */
    protected String doIt() throws Exception {

        if (isDeleteoldimportedrecords())
            Arrays.stream(getPayrollImportMovementIds(true, true, null)).forEach(recordId -> {
                X_I_HR_Movement importPayrollMovement = new X_I_HR_Movement(getCtx(), recordId, null);
                importPayrollMovement.deleteEx(true);
            });

        AtomicInteger importedRecord = new AtomicInteger(0);
        AtomicInteger withErrors = new AtomicInteger(0);
        Arrays.stream(getPayrollImportMovementIds(false, false, null)).forEach(recordId -> {
            Trx.run(trxName -> {
                X_I_HR_Movement importPayrollMovement = new X_I_HR_Movement(getCtx(), recordId, trxName);
                fillIdValues(importPayrollMovement);
                if (importRecord(importPayrollMovement))
                    importedRecord.updateAndGet(record -> record + 1);
                else
                    withErrors.updateAndGet(error -> error + 1);
            });
        });
        return "@HR_Movement_ID@ @Import@ @Records@ " + importedRecord.get() + " @Errors@ " + withErrors.get();
    }    //	doIt

    /**
     * Fill mandatory information
     *
     * @param importPayrollMovement
     */
    private void fillIdValues(X_I_HR_Movement importPayrollMovement) {

        StringBuilder messageError = new StringBuilder();
        importPayrollMovement.setI_ErrorMsg("");
        Integer processId = getId(MHRProcess.Table_Name, MHRProcess.COLUMNNAME_Name + "=?", importPayrollMovement.getProcessName());
        if (processId < 0)
            messageError.append("@HR_Process_ID@  @NotFound@ ");
        else {
            MHRProcess process = new MHRProcess(getCtx(), processId, importPayrollMovement.get_TrxName());
            if (MHRProcess.DOCSTATUS_Drafted.equals(process.getDocStatus()) && MHRProcess.DOCSTATUS_InProgress.equals(process.getDocStatus()))
                messageError.append("@DocStatus@ @NotValid@");
        }

        Integer partnerId = getId(MBPartner.Table_Name, MBPartner.COLUMNNAME_Value + "=?", importPayrollMovement.getBPartner_Value());
        if (partnerId < 0)
            messageError.append(", ").append("@C_BPartner_ID@ @NotFound@");
        else {
            MBPartner partner = new MBPartner(getCtx(), partnerId, importPayrollMovement.get_TrxName());
            if (!partner.isEmployee())
                messageError.append(", ").append("@IsEmployee@ @NotValid@ @C_BPartner_ID@ " + partner.getName());
        }

        StringBuilder whereClause = new StringBuilder("");
        whereClause.append(MHRConcept.COLUMNNAME_Value).append("=? AND ")
                .append(MHRConcept.COLUMNNAME_IsManual).append("=? AND ")
                .append(MHRConcept.COLUMNNAME_IsActive).append("=? ");

        Integer conceptId = getId(MHRConcept.Table_Name, whereClause.toString(), importPayrollMovement.getConceptValue().trim(), true, true);
        if (conceptId < 0)
            setImportError(importPayrollMovement, "@HR_Concept_ID@ " + importPayrollMovement.getConceptValue() + " @NotFound@").saveEx(importPayrollMovement.get_TrxName());
        else {
            MHRConcept concept = new MHRConcept(getCtx(), conceptId, importPayrollMovement.get_TrxName());
            if (MHRConcept.TYPE_RuleEngine.equals(concept.getType()))
                messageError.append(", ").append("@HR_Concept_ID@ " + concept.getName() + " @NotValid@ ");
        }

        if (importPayrollMovement.getValidFrom() == null)
            messageError.append(", ").append("@ValidFrom@ @FillMandatory@ ");


        if (importPayrollMovement.getValidTo() == null)
            importPayrollMovement.setValidTo(importPayrollMovement.getValidFrom());

        if (messageError != null && messageError.length() > 0)
            setImportError(importPayrollMovement, messageError.toString()).saveEx(importPayrollMovement.get_TrxName());

        importPayrollMovement.setHR_Process_ID(processId);
        importPayrollMovement.setHR_Concept_ID(conceptId);
        importPayrollMovement.setC_BPartner_ID(partnerId);
        importPayrollMovement.saveEx(importPayrollMovement.get_TrxName());
    }

    private boolean importRecord(X_I_HR_Movement importPayrollMovement) {
        if (importPayrollMovement.getI_ErrorMsg() != null && importPayrollMovement.getI_ErrorMsg().length() > 0) {
            importPayrollMovement.setProcessed(false);
            importPayrollMovement.setI_IsImported(false);
            importPayrollMovement.saveEx();
            return false;
        }

        MHRMovement payrollMovement = new MHRMovement(importPayrollMovement);
        payrollMovement.saveEx(importPayrollMovement.get_TrxName());
        importPayrollMovement.setHR_Movement_ID(payrollMovement.getHR_Movement_ID());
        importPayrollMovement.setI_IsImported(true);
        importPayrollMovement.setProcessed(true);
        importPayrollMovement.setI_ErrorMsg("");
        importPayrollMovement.saveEx(importPayrollMovement.get_TrxName());
        return true;
    }

    /**
     * get Id based table name and where clause
     *
     * @param tableName
     * @param whereClause
     * @param parameters
     * @return
     */
    private int getId(String tableName, String whereClause, Object... parameters) {
        return new Query(getCtx(), tableName, whereClause, get_TrxName())
                .setClient_ID()
                .setParameters(parameters)
                .firstId();
    }

    /**
     * set Import Error
     *
     * @param importPayrollMovement
     * @param error
     * @return
     */
    private X_I_HR_Movement setImportError(X_I_HR_Movement importPayrollMovement, String error) {
        importPayrollMovement.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
        addLog(importPayrollMovement.getI_HR_Movement_ID(), importPayrollMovement.getValidFrom(), importPayrollMovement.getAmount(), importPayrollMovement.getI_ErrorMsg());
        return importPayrollMovement;
    }

    /**
     * Get payroll Import Movement
     *
     * @param isImported
     * @param isProcessed
     * @return
     */
    private int[] getPayrollImportMovementIds(boolean isImported, boolean isProcessed, String trxName) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_HR_Movement.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_HR_Movement.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), X_I_HR_Movement.Table_Name, whereClause.toString(), trxName)
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .getIDs();
    }
}    //	ImportPayrollMovements
