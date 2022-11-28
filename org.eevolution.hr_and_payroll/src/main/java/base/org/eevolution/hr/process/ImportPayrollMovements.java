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
package org.eevolution.hr.process;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.adempiere.core.domains.models.I_I_HR_Movement;
import org.adempiere.core.domains.models.X_I_HR_Movement;
import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.eevolution.hr.model.MHRConcept;
import org.eevolution.hr.model.MHRMovement;
import org.eevolution.hr.model.MHRProcess;
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
     * @throws Exception Exception Error
     */
    protected String doIt() throws Exception {

        if (isDeleteOldImported())
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
     * @param importPayrollMovement Import Payroll Movement
     */
    private void fillIdValues(X_I_HR_Movement importPayrollMovement) {

        StringBuilder messageError = new StringBuilder();
        importPayrollMovement.setI_ErrorMsg("");
        if (importPayrollMovement.getHR_Process_ID() <= 0) {
            Optional<Integer> maybeProcessId = Optional.ofNullable(importPayrollMovement.getProcessName()).flatMap(processName -> {
                int processId = getId(MHRProcess.Table_Name, MHRProcess.COLUMNNAME_Name + "=?", processName);
                if (processId > 0)
                    return Optional.of(processId);
                else return Optional.empty();
            });
            if (maybeProcessId.isPresent())
                maybeProcessId.ifPresent(importPayrollMovement::setHR_Process_ID);
            else
                messageError.append("@HR_Process_ID@  @NotFound@ ");
        }

        if (importPayrollMovement.getC_BPartner_ID() <= 0) {
            Optional<Integer> maybePartnerId = Optional.ofNullable(importPayrollMovement.getBPartner_Value()).flatMap(partnerValue -> {
                int partnerId = getId(MBPartner.Table_Name, MBPartner.COLUMNNAME_Value + "=?", partnerValue);
                if (partnerId > 0) {
                    MBPartner partner = new MBPartner(getCtx(), partnerId, importPayrollMovement.get_TrxName());
                    if (!partner.isEmployee()) {
                        messageError.append(", ").append("@IsEmployee@ @NotValid@ @C_BPartner_ID@ ").append(partner.getName());
                        return Optional.empty();
                    }
                    return Optional.of(partnerId);
                } else return Optional.empty();

            });
            if (maybePartnerId.isPresent())
                maybePartnerId.ifPresent(importPayrollMovement::setC_BPartner_ID);
            else
                messageError.append(", ").append("@C_BPartner_ID@ @NotFound@ ");
        }

        if (importPayrollMovement.getHR_Concept_ID() <= 0) {
            Optional<Integer> maybeConceptId = Optional.ofNullable(importPayrollMovement.getConceptValue()).flatMap(conceptValue -> {
                StringBuilder whereClause = new StringBuilder();
                whereClause.append(MHRConcept.COLUMNNAME_Value).append("=? AND ")
                        .append(MHRConcept.COLUMNNAME_IsManual).append("=? AND ")
                        .append(MHRConcept.COLUMNNAME_IsActive).append("=? ");
                int conceptId = getId(MHRConcept.Table_Name, whereClause.toString(), conceptValue.trim(), true, true);
                if (conceptId > 0) {
                    MHRConcept concept = new MHRConcept(getCtx(), conceptId, importPayrollMovement.get_TrxName());
                    if (MHRConcept.TYPE_RuleEngine.equals(concept.getType())) {
                        messageError.append(", ").append("@HR_Concept_ID@ ").append(concept.getName()).append(" @NotValid@ ");
                        return Optional.empty();
                    }
                    return Optional.of(conceptId);

                } else return Optional.empty();
            });
            if (maybeConceptId.isPresent())
                maybeConceptId.ifPresent(importPayrollMovement::setHR_Concept_ID);
            else
                messageError.append("@HR_Concept_ID@ ").append(" @NotFound@ ");
        }

        if (importPayrollMovement.getValidFrom() == null)
            messageError.append(", @ValidFrom@ @FillMandatory@ ");

        if (importPayrollMovement.getValidTo() == null)
            importPayrollMovement.setValidTo(importPayrollMovement.getValidFrom());

        if (messageError.length() > 0)
            setImportError(importPayrollMovement, messageError.toString()).saveEx(importPayrollMovement.get_TrxName());

        importPayrollMovement.saveEx();
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
     * @param tableName   Table Name
     * @param whereClause Where Clause
     * @param parameters  Parameters
     * @return Id
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
     * @param importPayrollMovement Import Payroll Movement
     * @param error                 Error
     * @return Import Movement
     */
    private X_I_HR_Movement setImportError(X_I_HR_Movement importPayrollMovement, String error) {
        importPayrollMovement.setI_ErrorMsg(Msg.parseTranslation(getCtx(), error));
        addLog(importPayrollMovement.getI_HR_Movement_ID(), importPayrollMovement.getValidFrom(), importPayrollMovement.getAmount(), importPayrollMovement.getI_ErrorMsg());
        return importPayrollMovement;
    }

    /**
     * Get payroll Import Movement
     *
     * @param isImported  Is Imported
     * @param isProcessed Is Processed
     * @return Arrays ids
     */
    private int[] getPayrollImportMovementIds(boolean isImported, boolean isProcessed, String trxName) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(I_I_HR_Movement.COLUMNNAME_I_IsImported).append("=? AND ")
                .append(I_I_HR_Movement.COLUMNNAME_Processed).append("=?");

        return new Query(getCtx(), X_I_HR_Movement.Table_Name, whereClause.toString(), trxName)
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .setParameters(isImported, isProcessed)
                .getIDs();
    }
}    //	ImportPayrollMovements
