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

import org.compiere.util.Env;

import java.util.ArrayList;
import java.util.List;

/**
 * Request Model Validator
 */
public class RequetModelValidator implements ModelValidator {
    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        List<MStandardRequestType> standardRequestTypes = new ArrayList<>();
        StringBuilder whereClause = new StringBuilder();
        standardRequestTypes = new Query(Env.getCtx(), MStandardRequestType.Table_Name, null, null)
                .setClient_ID()
                .setOrderBy(MStandardRequestType.COLUMNNAME_AD_Table_ID + "," + MStandardRequestType.COLUMNNAME_C_DocType_ID)
                .list();

        standardRequestTypes.stream()
                .filter(standardRequestType -> standardRequestType.getEventModelValidator().startsWith("T"))
                .forEach(standardRequestType -> {
                    engine.addModelChange(standardRequestType.getAD_Table().getTableName(), this);
                });

        standardRequestTypes.stream()
                .filter(standardRequestType ->
                        standardRequestType.getEventModelValidator().startsWith("D")
                                && standardRequestType.getC_DocType_ID() > 0 && standardRequestType.getDocStatus() != null)
                .forEach(standardRequestType -> {
                    engine.addDocValidate(standardRequestType.getAD_Table().getTableName(), this);
                });

        engine.addModelChange(MProject.Table_Name, this);
        engine.addModelChange(MProjectPhase.Table_Name, this);
        engine.addModelChange(MProjectTask.Table_Name, this);

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
        // Create Request for Project Phase
        if (MProjectPhase.Table_ID == entity.get_Table_ID()) {
            MProjectPhase projectPhase = (MProjectPhase) entity;
            if (projectPhase != null && projectPhase.getC_Phase_ID() > 0) {
                MProjectTypePhase projectTypePhase = new MProjectTypePhase(projectPhase.getCtx(), projectPhase.getC_Phase_ID(), projectPhase.get_TrxName());
                MStandardRequestType.getByTable(entity).stream()
                        .filter(standardRequestType -> standardRequestType.get_ID() == projectTypePhase.getR_StandardRequestType_ID()
                             && standardRequestType.getEventModelValidator().equals(tableEventValidators[type]))
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
                             && standardRequestType.getEventModelValidator().equals(tableEventValidators[type]))
                        .forEach(standardRequestType -> {
                            standardRequestType.createStandardRequest(entity);

                        });
                return "";

            }
        }
        // Create Request for an Entity
        MStandardRequestType.getByTable(entity).stream()
                .filter(standardRequestType -> standardRequestType.getEventModelValidator().equals(tableEventValidators[type]))
                .forEach(standardRequestType -> {
                    standardRequestType.createStandardRequest(entity);
                });

        return null;
    }

    @Override
    public String docValidate(PO entity, int timing) {
        String documentStatus = entity.get_ColumnIndex(MStandardRequestType.COLUMNNAME_DocStatus) > 0 ?
                entity.get_ValueAsString(MStandardRequestType.COLUMNNAME_DocStatus) : null;
        Integer documentTypeId = entity.get_ColumnIndex(MStandardRequestType.COLUMNNAME_C_DocType_ID) > 0 ?
                entity.get_ValueAsInt(MStandardRequestType.COLUMNNAME_C_DocType_ID) : null;
        if (documentTypeId > 0 && documentStatus != null) {
            MStandardRequestType.getByTable(entity).stream()
                    .filter(standardRequestType -> standardRequestType.getEventModelValidator().equals(documentEventValidators[timing])
                         && standardRequestType.getAD_Table_ID() == entity.get_Table_ID()
                         && standardRequestType.getC_DocType_ID() == documentTypeId)
                    .forEach(standardRequestType -> {
                            standardRequestType.createStandardRequest(entity);
                    });
        }
        return null;
    }
}
