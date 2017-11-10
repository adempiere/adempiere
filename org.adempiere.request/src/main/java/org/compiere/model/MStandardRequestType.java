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

import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Standard Request Type
 */
public class MStandardRequestType extends X_R_StandardRequestType {
    private List<MStandardRequest> standardRequests = new ArrayList<>();
    private List<String> columnName = new ArrayList<>();
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
        standardRequests.stream()
                .forEach(standardRequest -> {
                    Timestamp dateNextAction = TimeUtil.addDuration(today,standardRequest.getDurationUnit(), standardRequest.getDuration());
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
                    request.setPriority(standardRequest.getPriority());
                    // Set Entity Link Reference
                    if (request.get_ColumnIndex(entity.get_TableName() + "_ID") > 0 && entity.get_ID() > 0)
                        request.set_Value(entity.get_TableName() + "_ID", entity.get_ID());

                    //Set Tanant Agent
                    if (standardRequest.getSalesRep_ID() > 0) {
                        request.setSalesRep_ID(standardRequest.getSalesRep_ID());
                    }
                    else {
                        int salesRepId = Env.getAD_User_ID(Env.getCtx());
                        if (salesRepId > 0)
                            request.setSalesRep_ID(salesRepId);
                    }

                    if (dateNextAction != null)
                        request.setDateNextAction(dateNextAction);
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
}
