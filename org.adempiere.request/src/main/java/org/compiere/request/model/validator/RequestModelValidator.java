/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */


package org.compiere.request.model.validator;

import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MProjectPhase;
import org.compiere.model.MProjectTask;
import org.compiere.model.MProjectTypePhase;
import org.compiere.model.MProjectTypeTask;
import org.compiere.model.MStandardRequestType;
import org.compiere.model.MTable;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Request Model Validator
 *
 * @author Victor Perez , victor.perez@e-evolution.com , http://www.e-evolution.com
 *      <li>#1330Add support for Request Standard Type
 *      <li>https://github.com/adempiere/adempiere/issues/1330</>
 *      <li>#1478 Add support to create Request based on Standard Request Type setting on Project Type
 *      <li>https://github.com/adempiere/adempiere/issues/1478
 * @author OpenUp Solutions Sylvie Bouissa, sylvie.bouissa@openupsolutions.com, http://www.openupsolutions.com
 *      <li>#1451 Add additional condition to an Standard Request
 *      <li>Reference to issue https://github.com/adempiere/adempiere/issues/1451
 * @author Yamel Senih, ysenih@erpya.com , http://www.erpya.com
 * 		<li>Add support to request type without document type and document status
 */
public class RequestModelValidator implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
    	
        List<MStandardRequestType> standardRequestTypes = new ArrayList<>();

        standardRequestTypes = new Query(Env.getCtx(), MStandardRequestType.Table_Name, null, null)
                .setOnlyActiveRecords(true)
                .setOrderBy(MStandardRequestType.COLUMNNAME_AD_Table_ID + "," + MStandardRequestType.COLUMNNAME_C_DocType_ID)
                .list();

        standardRequestTypes.stream()
                .filter(standardRequestType ->
                        standardRequestType.isValidFromTo() &&
                        standardRequestType.getEventModelValidator().startsWith("T"))
                .collect(Collectors.groupingBy(MStandardRequestType::getAD_Table_ID))
                .entrySet()
                .stream()
                .forEach(tableSet -> {
                    engine.addModelChange(MTable.getTableName(Env.getCtx(), tableSet.getKey()), this);
                });

        standardRequestTypes.stream()
                .filter(standardRequestType ->
                        standardRequestType.isValidFromTo() &&
                        standardRequestType.getEventModelValidator().startsWith("D"))
                .collect(Collectors.groupingBy(MStandardRequestType::getAD_Table_ID))
                .entrySet()
                .stream()
                .forEach(tableSet -> {
                    engine.addDocValidate(MTable.getTableName(Env.getCtx(), tableSet.getKey()), this);
                });
    }

    @Override
    public int getAD_Client_ID() {
        return Env.getAD_Client_ID(Env.getCtx());
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO entity, int type) throws Exception {
    	//#2541 Allow to delete draft or in process invoices generated from a request update.
        if (MInvoice.Table_ID == entity.get_Table_ID() && TYPE_BEFORE_DELETE == type) {
            StringBuilder statement = new StringBuilder();
            statement.append("UPDATE R_RequestUpdate r SET C_InvoiceLine_ID = NULL WHERE ");
            statement.append("EXISTS (SELECT 1 FROM C_Invoice i INNER JOIN C_InvoiceLine il ON (i.C_Invoice_ID=il.C_Invoice_ID) ");
            statement.append("WHERE i.DocStatus IN('DR','IP') AND i.C_Invoice_ID=? AND il.C_InvoiceLine_ID = r.C_InvoiceLine_ID)");
            DB.executeUpdate(statement.toString(), entity.get_ID() ,entity.get_TrxName());
        }
        // Create Request for Project Phase
        if (MProjectPhase.Table_ID == entity.get_Table_ID()) {
            MProjectPhase projectPhase = (MProjectPhase) entity;
            if (projectPhase != null && projectPhase.getC_Phase_ID() > 0) {
                MProjectTypePhase projectTypePhase = new MProjectTypePhase(projectPhase.getCtx(), projectPhase.getC_Phase_ID(), projectPhase.get_TrxName());
                MStandardRequestType.getByTable(entity).stream()
                        .filter(standardRequestType -> standardRequestType.get_ID() == projectTypePhase.getR_StandardRequestType_ID()
                             && standardRequestType.getEventModelValidator().equals(tableEventValidators[type])
                             && standardRequestType.isValid(entity))
                        .forEach(standardRequestType -> {
                            standardRequestType.createStandardRequest(entity);
                        });
                return "";
            }
        }
        // Create Request for Project Task
        if (MProjectTask.Table_ID == entity.get_Table_ID()) {
            MProjectTask projectTask = (MProjectTask) entity;
            if (projectTask != null && projectTask.getC_Task_ID() > 0) {
                MProjectTypeTask projectTypeTask = new MProjectTypeTask(projectTask.getCtx(), projectTask.getC_Task_ID(), projectTask.get_TrxName());
                MStandardRequestType.getByTable(entity).stream()
                        .filter(standardRequestType -> standardRequestType.get_ID() == projectTypeTask.getR_StandardRequestType_ID()
                             && standardRequestType.getEventModelValidator().equals(tableEventValidators[type])
                             && standardRequestType.isValid(entity))
                        .forEach(standardRequestType -> {
                            standardRequestType.createStandardRequest(entity);
                        });
                return "";
            }
        }
        // Create Request for an Entity
        MStandardRequestType.getByTable(entity).stream()
                .filter(standardRequestType ->
                        standardRequestType.getEventModelValidator().equals(tableEventValidators[type])
                     && standardRequestType.isValid(entity))
                .forEach(standardRequestType -> {
                    standardRequestType.createStandardRequest(entity);
                });
        return null;
    }

    /**
     * Document Validate for Standard Request Type
     * @param entity
     * @param timing see TIMING_ constants
     * @return
     */
    public String docValidate(PO entity, int timing) {
        String documentStatus = entity.get_ValueAsString(MStandardRequestType.COLUMNNAME_DocStatus);
        int documentTypeId = entity.get_ValueAsInt(MStandardRequestType.COLUMNNAME_C_DocType_ID);
                MStandardRequestType.getByTable(entity).stream()
                .filter(standardRequestType ->
                   standardRequestType.getEventModelValidator().equals(documentEventValidators[timing])
                && standardRequestType.isValidDocument(entity , documentTypeId , documentStatus))
                .forEach(standardRequestType -> {
                        standardRequestType.createStandardRequest(entity);
                });
        return null;
    }
}
