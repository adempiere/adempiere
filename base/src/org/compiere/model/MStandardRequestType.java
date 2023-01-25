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

import org.adempiere.core.domains.models.X_R_StandardRequestType;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

/**
 * Standard Request Type
 */
public class MStandardRequestType extends X_R_StandardRequestType {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1032692474434985197L;
	
	private List<MStandardRequest> standardRequests = new ArrayList<>();
	
    private HashMap<String, Boolean> columns = new HashMap<>();


    public MStandardRequestType(Properties ctx, int R_StandardRequestType_ID, String trxName) {
        super(ctx, R_StandardRequestType_ID, trxName);
    }

    public MStandardRequestType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public static List<MStandardRequestType> getByTable(PO model) {
        StringBuilder whereClause = new StringBuilder();
        whereClause.append(MStandardRequestType.COLUMNNAME_AD_Table_ID).append("=? ");
        return new Query(model.getCtx(), Table_Name, whereClause.toString(), model.get_TrxName())
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .setParameters(model.get_Table_ID())
                .setOrderBy(MStandardRequestType.COLUMNNAME_AD_Table_ID + "," + MStandardRequestType.COLUMNNAME_C_DocType_ID)
                .list();
    }

    public List<MStandardRequest> getStandardRequest(boolean reset) {
        if (standardRequests.size() > 0 && !reset)
            return standardRequests;

        StringBuilder whereClause = new StringBuilder();
        whereClause.append(MStandardRequest.COLUMNNAME_R_StandardRequestType_ID).append("=?");

        standardRequests = new Query(getCtx(), MStandardRequest.Table_Name, whereClause.toString(), get_TrxName())
                .setClient_ID()
                .setOnlyActiveRecords(true)
                .setParameters(getR_StandardRequestType_ID())
                .list();
        return standardRequests;
    }

    public List<MRequest> createStandardRequest(PO entity) {
        // Related requests
        HashMap<Integer, MRequest> relatedRequests = new HashMap<>();
        // Load Request Columms
        if (columns.size() <= 0) {
            MTable requestTableInfo = MTable.get(entity.getCtx(), MRequest.Table_ID);
            Arrays.stream(requestTableInfo.getColumns(true))
                    .forEach(column -> columns.put(column.getColumnName(), column.isUpdateable()));
        }

        Timestamp today = new Timestamp(System.currentTimeMillis());
        List<MRequest> requests = new ArrayList<>();
        List<MStandardRequest> standardRequests = getStandardRequest(true);
        standardRequests.forEach(standardRequest -> {
                    MRequest request = new MRequest(entity.getCtx(), 0, entity.get_TrxName());
                    // Set column based current context
                    columns.keySet().stream()
                            .filter(columnName -> columns.get(columnName) && !columnName.equals(MRequest.COLUMNNAME_DocumentNo))
                            .forEach(columnName -> {
                            if (!DisplayType.isID(entity.get_ColumnDisplayType(entity.get_ColumnIndex(columnName)))
                                        && entity.get_ColumnIndex(columnName) > 0
                                        && entity.get_Value(columnName) != null)
                                    request.set_Value(columnName, entity.get_Value(columnName));
                             else if (DisplayType.isID(entity.get_ColumnDisplayType(entity.get_ColumnIndex(columnName)))
                                 && entity.get_ValueAsInt(columnName) > 0)
                                    request.set_Value(columnName, entity.get_ValueAsInt(columnName));

                            });
                    // Define Standad setting for Request
                    request.setR_RequestType_ID(standardRequest.getR_RequestType_ID());
                    request.setAD_Table_ID(entity.get_Table_ID());
                    request.setRecord_ID(entity.get_ID());
                    request.setResult(Msg.parseTranslation(entity.getCtx(),"@Generate@ @From@ " + getName()));
                    request.setR_Group_ID(standardRequest.getR_Group_ID());
                    request.setR_Category_ID(standardRequest.getR_Category_ID());
                    request.setDueType(standardRequest.getDueType());
                    request.setR_Status_ID(standardRequest.getR_Status_ID());
                    request.setTaskStatus(standardRequest.getTaskStatus());
                    request.setAD_Role_ID(standardRequest.getAD_Role_ID());
                    request.setSummary(standardRequest.getSummary());
                    Optional.ofNullable(standardRequest.getSubject()).ifPresent(subject -> request.setSubject(standardRequest.getSubject()));
                    request.setPriority(standardRequest.getPriority());
                    request.setDateStartPlan(today);

                    if (entity.get_ColumnIndex(MOrder.COLUMNNAME_DateOrdered) >  0) {
                        Optional<Timestamp> startPlanOptinal = Optional.ofNullable((Timestamp) entity.get_Value(MOrder.COLUMNNAME_DateOrdered));
                        startPlanOptinal.ifPresent(startPlan -> request.setDateStartPlan(startPlan));
                    }
                    else if (entity.get_ColumnIndex(MInventory.COLUMNNAME_MovementDate) >  0) {
                        Optional<Timestamp> startPlanOptinal = Optional.ofNullable((Timestamp) entity.get_Value(MInventory.COLUMNNAME_MovementDate));
                        startPlanOptinal.ifPresent(startPlan -> request.setDateStartPlan(startPlan));
                    }
                    else if (entity.get_ColumnIndex(MPayment.COLUMNNAME_DateTrx) >  0) {
                        Optional<Timestamp> startPlanOptinal = Optional.ofNullable((Timestamp) entity.get_Value(MPayment.COLUMNNAME_DateTrx));
                        startPlanOptinal.ifPresent(startPlan -> request.setDateStartPlan(startPlan));
                    }
                    else if (entity.get_ColumnIndex(MBankStatement.COLUMNNAME_StatementDate) >  0) {
                        Optional<Timestamp> startPlanOptinal = Optional.ofNullable((Timestamp) entity.get_Value(MBankStatement.COLUMNNAME_StatementDate));
                        startPlanOptinal.ifPresent(startPlan -> request.setDateStartPlan(startPlan));
                    }
                    else if (entity.get_ColumnIndex(MProject.COLUMNNAME_DateStart) >  0)
                    {
                        Optional<Timestamp> startPlanOptinal = Optional.ofNullable((Timestamp) entity.get_Value(MProject.COLUMNNAME_DateStart));
                        startPlanOptinal.ifPresent(startPlan -> request.setDateStartPlan(startPlan));
                    }
                    else if (entity.get_ColumnIndex(MPaySelection.COLUMNNAME_DateDoc) >  0)
                    {
                        Optional<Timestamp> startPlanOptinal = Optional.ofNullable((Timestamp) entity.get_Value(MPaySelection.COLUMNNAME_DateDoc));
                        startPlanOptinal.ifPresent(startPlan -> request.setDateStartPlan(startPlan));
                    }

                    // Set Entity Link Reference
                    if (request.get_ColumnIndex(entity.get_TableName() + "_ID") > 0 && entity.get_ID() > 0)
                        request.set_Value(entity.get_TableName() + "_ID", entity.get_ID());

                    //Set Tanant Agent
                    int salesRepId = standardRequest.getSalesRep_ID();
                    if(salesRepId == 0
                    		&& entity.get_ColumnIndex(MOrder.COLUMNNAME_SalesRep_ID) > -1) {
                    	salesRepId = entity.get_ValueAsInt(MOrder.COLUMNNAME_SalesRep_ID);
                    }
                    if(salesRepId == 0) {
                    	salesRepId = Env.getAD_User_ID(Env.getCtx());
                    }
                    //	Set
                    if (salesRepId > 0) {
                    	request.setSalesRep_ID(salesRepId);
                    }
                    Timestamp dateNextAction = TimeUtil.addDuration(request.getDateStartPlan() != null ? request.getDateStartPlan() : today ,standardRequest.getDurationUnit(), standardRequest.getDuration());
                    if (dateNextAction != null) {
                        request.setDateNextAction(dateNextAction);
                        request.setDateCompletePlan(dateNextAction);
                    }
                    if (standardRequest.getConfidentialTypeEntry() != null)
                        request.setConfidentialTypeEntry(standardRequest.getConfidentialTypeEntry());
                    request.saveEx();
                    relatedRequests.put(standardRequest.getR_StandardRequest_ID(), request);
                    requests.add(request);
                });
        // setting related request
        standardRequests.stream()
                .filter(standardRequest -> standardRequest.getR_RequestRelated_ID() > 0)
                .forEach(standardRequest -> {
                    MRequest relatedRequest = relatedRequests.get(standardRequest.getR_RequestRelated_ID());
                    MRequest request = relatedRequests.get(standardRequest.getR_StandardRequest_ID());
                    request.setR_RequestRelated_ID(relatedRequest.getR_Request_ID());
                    request.saveEx();
                });

        return requests;
    }

    public boolean isValid(PO entity)
    {
    	//	Only for client
    	if(entity.getAD_Client_ID() != getAD_Client_ID()) {
    		return false;
    	}
        if (isValidFromTo()         // Valid the Effective Date
        &&  isValidSOTrx(entity , getIsSOTrx())                 // Valid Sales Transaction context
        &&  isValidWhereCondition(entity, getWhereClause()))    // Valid Where Condition
            return true;
        else
            return false;
    }

    /**
     * Validate if Create Request for Document
     * @param entity
     * @param documentTypeId
     * @param documentStatus
     * @return
     */
    public boolean isValidDocument(PO entity , int documentTypeId , String documentStatus)
    {
        if (isValid(entity)
        && getAD_Table_ID() == entity.get_Table_ID()
        && (getC_DocType_ID() <= 0 || getC_DocType_ID() == documentTypeId)
        && (getDocStatus() == null || Util.isEmpty(documentStatus) || getDocStatus().equals(documentStatus)))
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
     * @return
     */
    public Boolean isValidFromTo()
    {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());

        if (getValidFrom() != null && currentDate.before(getValidFrom()))
            return false;
        if (getValidTo() != null && currentDate.after(getValidTo()))
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
