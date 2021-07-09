/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 SC ARHIPAC SERVICE SRL. All Rights Reserved.            *
 * Copyright (C) 2006-2021 ADempiere Foundation, All Rights Reserved.         *
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
 *****************************************************************************/
package org.compiere.wf;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MRequisition;
import org.compiere.model.Query;
import org.compiere.util.Trx;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test Workflow related classes
 * 
 * @author Teo Sarca, www.arhipac.ro
 * @author Victor Pérez, E Evolution Consulting, wwww.e-evolution.com
 *         <li>[Bug Report] The workflow engine is not correctly handling
 *         transactions when processing documents #3170
 *         <a href="https://github.com/adempiere/adempiere/issues/3170">
 */
class IT_Workflow extends CommonGWSetup {

    @ParameterizedTest
    @ValueSource(booleans = { true, false })
    void testMWFActivity_shouldNotReturnNull(boolean isActive) {

        int AD_Table_ID = MRequisition.Table_ID;
        int dummyRecord_ID = 1;
        MWFActivity[] list = MWFActivity.get(getCtx(), AD_Table_ID,
                dummyRecord_ID, isActive);
        assertNotNull(list, "MWFActivity returned null");

    }

    @Test
    void testMWFEventAudit() {

        int AD_WF_Process_ID = 1;
        int AD_WF_Node_ID = 1;
        MWFEventAudit.get(getCtx(), AD_WF_Process_ID, AD_WF_Node_ID,
                getTrxName());
        MWFEventAudit.get(getCtx(), AD_WF_Process_ID, getTrxName());

    }

    @Test
    void testMWFPRocess() {

        MWFProcess proc =
                new Query(getCtx(), MWFProcess.Table_Name, null, getTrxName())
                        .setClient_ID()
                        .setOrderBy(MWFProcess.COLUMNNAME_AD_WF_Process_ID)
                        .first();
        proc.setWorkflowProcessTransaction(Trx.get(getTrxName(), false));
        if (proc != null) {
            proc.getActivities(true, false);
            proc.getActivities(true, true);
        }
        // Check MWorkflow, MWFNode, MWFNodeNext etc
        int AD_Client_ID = AD_CLIENT_ID;
        int AD_Workflow_ID = 115; // Process_Requisition
        MWorkflow wf = MWorkflow.get(getCtx(), AD_Workflow_ID);
        for (MWFNode node : wf.getNodes(false, AD_Client_ID)) {
            MWFNodePara.getParameters(node.getCtx(), node.getAD_WF_Node_ID());

            for (MWFNodeNext next : node.getTransitions(AD_Client_ID)) {
                next.getConditions(true);
                next.getConditions(false);
            }
        }
        // Check MWorkflowProcessor
        for (MWorkflowProcessor processor : MWorkflowProcessor
                .getActive(getCtx())) {
            processor.getLogs();
        }
    }

}
