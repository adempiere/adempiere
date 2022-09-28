/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2006-2020 ADempiere Foundation, All Rights Reserved.         *
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
package org.compiere.model;

import static org.adempiere.core.domains.models.X_C_Project.PROJECTLINELEVEL_Project;
import static org.adempiere.core.domains.models.X_C_Project.PROJINVOICERULE_None;
import static org.compiere.model.ModelTestUtilities.verifyExceptionForMissingMandatoryField;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWSetup;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("Model")
@Tag("MProject")
class IT_MProject extends CommonGWSetup {

    int currencyId;

    @BeforeEach
    void localSetup() {

        MClient client = MClient.get(ctx);
        currencyId = client.getC_Currency_ID();

    }

    private Exception createProductWithoutField(String fieldName) {

        MProject project = new MProject(ctx, 0, trxName);
        if (!fieldName.equals("name"))
            project.setName("testProject");
        if (!fieldName.equals("c_currency_id"))
            project.setC_Currency_ID(currencyId);
        Exception e = assertThrows(AdempiereException.class, () -> {
            project.saveEx();
        });
        return e;

    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void getByID_ifPassedlessThanOrEqualToZero_returnsNull(int id) {

        assertNull(MProject.getById(ctx, id, trxName), "When passed value " + id
                + " MProject.get() should return null");

    }

    @Test
    void getByID_shouldFindExisting() {

        MProject project1 = createMinimalProject();
        MProject project2 =
                MProject.getById(ctx, project1.getC_Project_ID(), trxName);

        assertEquals(project1.getC_Project_ID(), project2.getC_Project_ID(),
                "GetbyID did not return a project with the correct ID");

    }

    void getByValue_ifPassedNullShouldReturnNull() {

        assertNull(MProject.getByValue(ctx, null, trxName),
                "getByValue, if passed null should return null");

    }

    @Test
    void getByValue_shouldFindExisting() {

        final String testValue = "TestProjectValue";

        MProject project1 = createMinimalProject();
        project1.setValue(testValue);
        project1.saveEx();
        MProject project2 =
                MProject.getByValue(ctx, testValue, trxName);

        assertEquals(project1.getC_Project_ID(), project2.getC_Project_ID(),
                "GetbyValue did not return a project with the correct ID");

    }

    @ParameterizedTest
    @ValueSource(strings = { "name", "c_currency_id" })
    void constructor_withoutFieldThrowsException(String fieldName) {

        Exception e = createProductWithoutField(fieldName);
        verifyExceptionForMissingMandatoryField(fieldName, e);

    }

    @Test
    void constructor_success() {

        MProject project = createMinimalProject();

        assertTrue(project.getC_Project_ID() > 0,
                "Project ID is not assertEquals(Env.ZERO,  as expected");

    }

    private MProject createMinimalProject() {

        MProject project = new MProject(ctx, 0, trxName);
        project.setName("testProject");
        project.setC_Currency_ID(currencyId);
        project.saveEx();
        return project;

    }

    @Test
    void checkDefaultValues() {

        String errMsg = "Default value not set as expected";
        MProject project = new MProject(ctx, 0, trxName);
        assertEquals(Env.ZERO, project.getCommittedAmt(), errMsg);
        assertEquals(Env.ZERO, project.getCommittedQty(), errMsg);
        assertEquals(Env.ZERO, project.getInvoicedAmt(), errMsg);
        assertEquals(Env.ZERO, project.getInvoicedQty(), errMsg);
        assertEquals(Env.ZERO, project.getPlannedAmt(), errMsg);
        assertEquals(Env.ZERO, project.getPlannedMarginAmt(), errMsg);
        assertEquals(Env.ZERO, project.getPlannedQty(), errMsg);
        assertEquals(Env.ZERO, project.getProjectBalanceAmt(), errMsg);
        assertEquals(PROJINVOICERULE_None, project.getProjInvoiceRule(),
                errMsg);
        assertEquals(PROJECTLINELEVEL_Project, project.getProjectLineLevel(),
                errMsg);
        assertEquals(false, project.isCommitCeiling(), errMsg);
        assertEquals(false, project.isCommitment(), errMsg);
        assertEquals(false, project.isSummary(), errMsg);
        assertEquals(false, project.isProcessed(), errMsg);

    }

}
