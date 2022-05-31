/**
 * Copyright (C) 2003-2018, e-Evolution Consultants S.A. , http://www.e-evolution.com
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

package org.eevolution.project.model.validator;

import org.compiere.model.MClient;
import org.compiere.model.MConversionRate;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MProject;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;

import java.math.BigDecimal;

/**
 * Project Model Validator
 */
public class ProjectModelValidator implements ModelValidator {

    public void initialize(ModelValidationEngine engine, MClient client) {
        //Check when a payment is completed
        engine.addDocValidate(MPayment.Table_Name, this);
        //Set Account dimension based on project if this not exist
        engine.addModelChange(MOrder.Table_Name, this);
        engine.addModelChange(MOrderLine.Table_Name, this);
        engine.addModelChange(MInvoice.Table_Name, this);
        engine.addModelChange(MInvoiceLine.Table_Name, this);
        engine.addModelChange(MPayment.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() {
        return Env.getAD_Client_ID(Env.getCtx());
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return "";
    }

    /**
     * @param entity
     * @param type   TYPE_
     * @return
     * @throws Exception
     */
    public String modelChange(PO entity, int type) {

        if (ModelValidator.TYPE_BEFORE_CHANGE == type) {
            Integer projectId = entity.get_ValueAsInt(MProject.COLUMNNAME_C_Project_ID);
            if (projectId > 0) {
                MProject project = new MProject(entity.getCtx(), projectId, entity.get_TrxName());
                if (entity.get_ColumnIndex(MProject.COLUMNNAME_C_Campaign_ID) > 0
                        && entity.get_ValueAsInt(MProject.COLUMNNAME_C_Campaign_ID) <= 0
                        && project.getC_Campaign_ID() > 0)
                    entity.set_ValueOfColumn(MProject.COLUMNNAME_C_Campaign_ID, project.getC_Campaign_ID());

                if (entity.get_ColumnIndex(MProject.COLUMNNAME_C_Activity_ID) > 0
                        && entity.get_ValueAsInt(MProject.COLUMNNAME_C_Activity_ID) <= 0
                        && project.getC_Activity_ID() > 0)
                    entity.set_ValueOfColumn(MProject.COLUMNNAME_C_Activity_ID, project.getC_Activity_ID());

                if (entity.get_ColumnIndex(MProject.COLUMNNAME_User1_ID) > 0
                        && entity.get_ValueAsInt(MProject.COLUMNNAME_User1_ID) <= 0
                        && project.getUser1_ID() > 0)
                    entity.set_ValueOfColumn(MProject.COLUMNNAME_User1_ID, project.getUser1_ID());

                if (entity.get_ColumnIndex(MProject.COLUMNNAME_User2_ID) > 0
                        && entity.get_ValueAsInt(MProject.COLUMNNAME_User2_ID) <= 0
                        && project.getUser2_ID() > 0)
                    entity.set_ValueOfColumn(MProject.COLUMNNAME_User2_ID, project.getUser2_ID());

                if (entity.get_ColumnIndex(MProject.COLUMNNAME_User3_ID) > 0
                        && entity.get_ValueAsInt(MProject.COLUMNNAME_User3_ID) <= 0
                        && project.getUser3_ID() > 0)
                    entity.set_ValueOfColumn(MProject.COLUMNNAME_User3_ID, project.getUser3_ID());

                if (entity.get_ColumnIndex(MProject.COLUMNNAME_User4_ID) > 0
                        && entity.get_ValueAsInt(MProject.COLUMNNAME_User4_ID) <= 0
                        && project.getUser4_ID() > 0)
                    entity.set_ValueOfColumn(MProject.COLUMNNAME_User4_ID, project.getUser4_ID());
            }
        }

        return null;
    }

    /**
     * @param entity
     * @param timing see TIMING_ constants
     * @return
     */
    public String docValidate(PO entity, int timing) {
        //Update Project Balance When Payment is receipt increase project balance when payment is not receipt decrease project balance
        if (MPayment.Table_ID == entity.get_Table_ID() && entity.get_ValueAsInt(MProject.COLUMNNAME_C_Project_ID) > 0) {
            MPayment payment = (MPayment) entity;
            MProject project = (MProject) payment.getC_Project();
            BigDecimal paymentAmount = payment.getPayAmt();
            if (payment.getC_Currency_ID() != project.getC_Currency_ID()) {
                paymentAmount = MConversionRate.convert(
                        entity.getCtx(),
                        paymentAmount,
                        project.getC_Currency_ID(),
                        payment.getC_Currency_ID(),
                        payment.getDateAcct(),
                        payment.getC_ConversionType_ID(),
                        getAD_Client_ID(),
                        payment.getAD_Org_ID());
            }
            // get payment with project link
            if (ModelValidator.TIMING_AFTER_COMPLETE == timing && payment.getReversal_ID() <= 0) {
                if (payment.isReceipt())
                    project.setProjectBalanceAmt(project.getProjectBalanceAmt().add(paymentAmount));
                else
                    project.setProjectBalanceAmt(project.getProjectBalanceAmt().subtract(paymentAmount));

                project.saveEx();
            }
            if (payment.getReversal_ID() > 0 && (ModelValidator.TIMING_AFTER_REVERSECORRECT == timing || ModelValidator.TIMING_AFTER_REVERSEACCRUAL == timing)) {
                if (payment.isReceipt())
                    project.setProjectBalanceAmt(project.getProjectBalanceAmt().subtract(paymentAmount));
                else
                    project.setProjectBalanceAmt(project.getProjectBalanceAmt().add(paymentAmount));

                project.saveEx();
            }
        }
        return null;
    }
}