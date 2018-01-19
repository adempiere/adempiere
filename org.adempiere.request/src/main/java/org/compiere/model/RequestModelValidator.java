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


package org.compiere.model;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Evaluator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
 */
public class RequestModelValidator implements ModelValidator {

    private Timestamp currentDate;
    private static CLogger log = CLogger.getCLogger(RequestModelValidator.class);

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {

        List<MStandardRequestType> standardRequestTypes = new ArrayList<>();

        standardRequestTypes = new Query(Env.getCtx(), MStandardRequestType.Table_Name, null, null)
                .setOnlyActiveRecords(true)
                .setClient_ID()
                .setOrderBy(MStandardRequestType.COLUMNNAME_AD_Table_ID + "," + MStandardRequestType.COLUMNNAME_C_DocType_ID)
                .list();

        standardRequestTypes.stream()
                .filter(standardRequestType ->
                        isValidFromTo(standardRequestType.getValidFrom(), standardRequestType.getValidTo())
                     && standardRequestType.getEventModelValidator().startsWith("T"))
                .forEach(standardRequestType -> {
                        engine.addModelChange(standardRequestType.getAD_Table().getTableName(), this);
                });

        standardRequestTypes.stream()
                .filter(standardRequestType ->
                        isValidFromTo( standardRequestType.getValidFrom(), standardRequestType.getValidTo())
                     && standardRequestType.getEventModelValidator().startsWith("D")
                     && standardRequestType.getC_DocType_ID() > 0 && standardRequestType.getDocStatus() != null)
                .forEach(standardRequestType -> {
                    engine.addDocValidate(standardRequestType.getAD_Table().getTableName(), this);
                });
    }

    public Timestamp getCurrentDate()
    {
        if (currentDate != null)
            return currentDate;

        currentDate = new Timestamp(System.currentTimeMillis());
        return currentDate;
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
        Boolean isSOTrx = "Y".equals(Env.getContext(entity.getCtx(),"IsSOTrx"));
        // Create Request for Project Phase
        if (MProjectPhase.Table_ID == entity.get_Table_ID()) {
            MProjectPhase projectPhase = (MProjectPhase) entity;
            if (projectPhase != null && projectPhase.getC_Phase_ID() > 0) {
                MProjectTypePhase projectTypePhase = new MProjectTypePhase(projectPhase.getCtx(), projectPhase.getC_Phase_ID(), projectPhase.get_TrxName());
                MStandardRequestType.getByTable(entity).stream()
                        .filter(standardRequestType -> standardRequestType.get_ID() == projectTypePhase.getR_StandardRequestType_ID()
                             && isValidFromTo( standardRequestType.getValidFrom(), standardRequestType.getValidTo())
                             && standardRequestType.getEventModelValidator().equals(tableEventValidators[type])
                             && isValidWhereCondition(entity, standardRequestType.getWhereClause()))
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
                             && isValidFromTo(standardRequestType.getValidFrom(), standardRequestType.getValidTo())
                             && standardRequestType.getEventModelValidator().equals(tableEventValidators[type])
                             && isValidWhereCondition(entity, standardRequestType.getWhereClause()))
                        .forEach(standardRequestType -> {
                            standardRequestType.createStandardRequest(entity);
                        });
                return "";

            }
        }
        // Create Request for an Entity
        MStandardRequestType.getByTable(entity).stream()
                .filter(standardRequestType ->
                        isValidFromTo(standardRequestType.getValidFrom(), standardRequestType.getValidTo())
                     && isValidSOTrx(entity , standardRequestType.getIsSOTrx())
                     && standardRequestType.getEventModelValidator().equals(tableEventValidators[type])
                     && isValidWhereCondition(entity , standardRequestType.getWhereClause()))
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
        String documentStatus = entity.get_ColumnIndex(MStandardRequestType.COLUMNNAME_DocStatus) > 0 ?
                entity.get_ValueAsString(MStandardRequestType.COLUMNNAME_DocStatus) : null;
        Integer documentTypeId = entity.get_ColumnIndex(MStandardRequestType.COLUMNNAME_C_DocType_ID) > 0 ?
                entity.get_ValueAsInt(MStandardRequestType.COLUMNNAME_C_DocType_ID) : null;
        if (documentTypeId > 0 && documentStatus != null) {
            MStandardRequestType.getByTable(entity).stream()
                    .filter(standardRequestType ->
                            isValidFromTo(standardRequestType.getValidFrom(), standardRequestType.getValidTo())
                         && isValidSOTrx(entity , standardRequestType.getIsSOTrx())
                         && isValidDocument(standardRequestType, timing , entity.get_Table_ID() , documentTypeId , documentStatus)
                         && isValidWhereCondition(entity , standardRequestType.getWhereClause()))
                    .forEach(standardRequestType -> {
                            standardRequestType.createStandardRequest(entity);
                    });
        }
        return null;
    }

    /**
     * Validate if Create Request for Document
     * @param standardRequestType
     * @param timing
     * @param tableId
     * @param documentTypeId
     * @param documentStatus
     * @return
     */
    private Boolean isValidDocument(MStandardRequestType standardRequestType ,int timing, int tableId , int documentTypeId , String documentStatus )
    {
            if (standardRequestType != null
             && standardRequestType.getEventModelValidator().equals(documentEventValidators[timing])
             && standardRequestType.getAD_Table_ID() == tableId
             && standardRequestType.getC_DocType_ID() == documentTypeId
             && (standardRequestType.getDocStatus() == null || standardRequestType.getDocStatus().equals(documentStatus)))
                return true;
            else
                return false;
    }


    /**
     * Valid IsSOTrx with context
     * @param entity
     * @param standardRequestIsSOTrx
     * @return
     */
    private Boolean isValidSOTrx(PO entity , String standardRequestIsSOTrx)
    {
        if (standardRequestIsSOTrx == null)
            return true;

        Boolean isSoTrx;
        if (entity.get_ColumnIndex("IsSOTrx") > 0)
            isSoTrx = entity.get_ValueAsBoolean("IsSOTrx");
        else
            isSoTrx = Env.isSOTrx(Env.getCtx());

        if (isSoTrx == "Y".equals(standardRequestIsSOTrx))
            return true;
        else if (isSoTrx == "N".equals(standardRequestIsSOTrx))
            return false;
        else
            return false;

    }

    /**
     * Get if range date is valid vs current date
     * @param validFrom
     * @param validTo
     * @return
     */
    private Boolean isValidFromTo(Timestamp validFrom, Timestamp validTo)
    {
        if (validFrom != null && getCurrentDate().before(validFrom))
            return false;
        if (validTo != null && getCurrentDate().after(validTo))
            return false;
        return true;
    }

    /**
     * Validate additional condition if it's stablish in the standard request type
     * @param entity PO object
     * @param whereClause condition
     * @return boolean
     */
    private boolean isValidWhereCondition(PO entity, String whereClause) {

        if (whereClause == null || whereClause.isEmpty()) return true;

        if (!validateQueryObject(entity, whereClause)) {
            log.severe("SQL logic evaluated to false ("+whereClause+")");
            return false;
        }
        return true;
    }

    /**
     * Test condition
     * @param entity PO object
     * @param  whereClause  condition
     * @return boolean
     */
    private boolean validateQueryObject(PO entity, String whereClause) {

        String tableName = entity.get_TableName();
        String[] keyColumns = entity.get_KeyColumns();
        String whereConditions =  "";
        if (keyColumns.length != 1) {
            log.severe("Tables with more then one key column not supported - "
                    + tableName + " = " + keyColumns.length);
            return false;
        }
        if ((whereClause.indexOf('@') > -1)){
            whereConditions = Env.parseVariable(whereClause, entity, entity.get_TrxName(), false);
        }

        PO instance = new Query(entity.getCtx(), tableName,
                (whereConditions.isEmpty())? whereClause:whereConditions +" AND "+keyColumns[0] + "=" + entity.get_ID(),
                entity.get_TrxName())
                .first();

        return instance != null && instance.get_ID() > 0;

    }


}
