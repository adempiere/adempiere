/******************************************************************************
 * Product: ADempiere ERP & CRM Smart Business Solution                       *
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
package org.compiere.process;

import static org.adempiere.test.TestUtilities.randomString;
import static org.compiere.Adempiere.startup;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Savepoint;
import java.util.List;
import java.util.Properties;

import org.adempiere.core.domains.models.I_AD_Window_Access;
import org.adempiere.core.domains.models.X_AD_Role;
import org.adempiere.core.domains.models.X_AD_Window_Access;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.IntegrationTestTag;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.eevolution.services.dsl.ProcessBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("RoleAccessUpdate")
@DisplayName("Given the GardenWorld Client ")
class RoleAccessUpdate_IT implements IntegrationTestTag{

    // Can't extend the CommonGWSetup as the transaction
    // has to be closed after each process run.
    
    private static final int AD_USER_ID = 100; // SuperUser
    private static final int GARDEN_USER_ROLE_ID = 103;
    private static final int SYSTEM_ADMIN_ROLE_ID = 0;
    private static final int SYSTEM_CLIENT_ID = 0;
    private static final int GARDEN_WORLD_CLIENT_ID = CommonGWData.AD_CLIENT_ID;
    private static final int AD_ORG_ID = CommonGWData.AD_ORG_ID;
    protected static final boolean IS_CLIENT = CommonGWData.IS_CLIENT;
    private static Properties ctx;
    private String trxName = null;
    private Trx trx = null;
    private Savepoint testSavepoint = null;

    private int windowId = 193; // Note - access level ALL

    @BeforeAll
    public static void createGWContextAndConnect() {

        ctx = Env.getCtx();
        ctx.setProperty("#AD_Org_ID", Integer.toString(AD_ORG_ID));
        ctx.setProperty("#AD_User_ID", Integer.toString(AD_USER_ID));
        ctx.setProperty("#AD_Client_ID",
                Integer.toString(GARDEN_WORLD_CLIENT_ID));
        ctx.setProperty("#Date",
                TimeUtil.getDay(System.currentTimeMillis()).toString());
        ctx.setProperty("#AD_Language", "en");

        Ini.setClient(IS_CLIENT);
        Ini.loadProperties(false);
        startup(IS_CLIENT);

    }

    @AfterAll
    public static void tearDownAfterClass() {

        ctx = null;

    }

    @BeforeEach
    void createTransactionAndSavePoint() throws Exception {

        trxName = Trx.createTrxName("TestRun_" + randomString(4));
        trx = Trx.get(trxName, false);
        testSavepoint = trx.setSavepoint("SingleTest_" + randomString(4));

    }

    @AfterEach
    void rollbackSavePointAndCloseTransaction() throws Exception {

        if (trx != null && trx.isActive() && testSavepoint != null) {
            trx.rollback(testSavepoint);
        }
        trx.close();
        trx = null;

    }

    private List<MRole> getAllAutomaticRoles() {

        String where = X_AD_Role.COLUMNNAME_IsManual + "='N'";
        return new Query(ctx, MRole.Table_Name, where, trxName)
                .setOnlyActiveRecords(true)
                .list(MRole.class);

    }

    private void assertAllRolesUpdated(int windowId) {

        for (MRole role : getAllAutomaticRoles()) {
            assertNotNull(role.getWindowAccess(windowId),
                    "Role " + role.getName()
                            + " does not have access to window "
                            + windowId);
        }

    }

    private void assertGWRolesUpdated(int windowId) {

        for (MRole role : getAllAutomaticRoles()) {
            if (role.getAD_Client_ID() == GARDEN_WORLD_CLIENT_ID) {
                assertNotNull(role.getWindowAccess(windowId),
                        "Role " + role.getName()
                                + " of clientId " + role.getAD_Client_ID()
                                + " does not have access to window "
                                + windowId);
            } else {
                assertNull(role.getWindowAccess(windowId),
                        "Role " + role.getName()
                                + " of clientId " + role.getAD_Client_ID()
                                + " does have access to window "
                                + windowId);
            }
        }

    }

    private void assertNoRoleHasAccess(int windowId) {

        for (MRole role : getAllAutomaticRoles()) {
            assertNull(role.getWindowAccess(windowId),
                    "Role " + role.getName()
                            + " does have access to window "
                            + windowId);
        }

    }

    private void assertOnlyGWUserRoleUpdated(int windowId) {

        for (MRole role : getAllAutomaticRoles()) {
            if (role.getAD_Role_ID() == GARDEN_USER_ROLE_ID) {
                assertNotNull(role.getWindowAccess(windowId),
                        "Role " + role.getName()
                                + " of clientId " + role.getAD_Client_ID()
                                + " does not have access to window "
                                + windowId);
            } else {
                assertNull(role.getWindowAccess(windowId),
                        "Role " + role.getName()
                                + " of clientId " + role.getAD_Client_ID()
                                + " does have access to window "
                                + windowId);
            }
        }

    }

    private void assertOnlySysAdmRoleUpdated(int windowId) {

        for (MRole role : getAllAutomaticRoles()) {
            if (role.getAD_Role_ID() == SYSTEM_ADMIN_ROLE_ID) {
                assertNotNull(role.getWindowAccess(windowId),
                        "Role " + role.getName()
                                + " of clientId " + role.getAD_Client_ID()
                                + " does not have access to window "
                                + windowId);
            } else {
                assertNull(role.getWindowAccess(windowId),
                        "Role " + role.getName()
                                + " of clientId " + role.getAD_Client_ID()
                                + " does have access to window "
                                + windowId);
            }
        }

    }

    @Nested
    @DisplayName("And given no window access")
    class GivenNoWindowAccess {

        @BeforeEach
        void deleteWindowAccess() {

            getAllAutomaticRoles().stream()
                    .forEach(role -> {
                        String where =
                                X_AD_Window_Access.COLUMNNAME_AD_Window_ID + "="
                                        + windowId
                                        + " AND "
                                        + X_AD_Window_Access.COLUMNNAME_AD_Role_ID
                                        + "=" + role.getAD_Role_ID();
                        new Query(ctx, I_AD_Window_Access.Table_Name, where,
                                trxName)
                                        .list().stream()
                                        .forEach(wa -> wa.deleteEx(false));

                    });

        }

        @Test
        @DisplayName("When window access is checked, then no role has access")
        final void whenAccessIsChecked_thenNoAccess() {

            assertNoRoleHasAccess(windowId);

        }

        @Test
        @DisplayName("When the Role Access Update is run for the System Client "
                + "with no role selected, then all roles in the client are updated")
        final void whenRunForSystemAndNoRole_thenAllRolesUpdated() {

            ProcessBuilder
                    .create(ctx)
                    .process(RoleAccessUpdate.class)
                    .withParameter(RoleAccessUpdate.AD_CLIENT_ID,
                            SYSTEM_CLIENT_ID)
                    .withParameter(RoleAccessUpdate.AD_ROLE_ID, null)
                    .execute(trxName);

            assertAllRolesUpdated(windowId);

        }

        @Test
        @DisplayName("When the Role Access Update is run for the System Client "
                + "with the System Admin role selected, then only the System "
                + "Admin role is updated")
        final void whenRunForSystemAndSysAdmRole_thenOnlySysAdmUpdated() {

            ProcessBuilder
                    .create(ctx)
                    .process(RoleAccessUpdate.class)
                    .withParameter(RoleAccessUpdate.AD_CLIENT_ID,
                            SYSTEM_CLIENT_ID)
                    .withParameter(RoleAccessUpdate.AD_ROLE_ID,
                            SYSTEM_ADMIN_ROLE_ID)
                    .execute(trxName);

            assertOnlySysAdmRoleUpdated(windowId);

        }

        @Test
        @DisplayName("When the Role Access Update is run for the GardenWorld Client "
                + "with no role selected, then all roles in the client are updated")
        final void whenRunForGWAndNoRole_thenAllGWRolesUpdated() {

            ProcessBuilder
                    .create(ctx)
                    .process(RoleAccessUpdate.class)
                    .withParameter(RoleAccessUpdate.AD_CLIENT_ID,
                            GARDEN_WORLD_CLIENT_ID)
                    .withParameter(RoleAccessUpdate.AD_ROLE_ID, null)
                    .execute(trxName);

            assertGWRolesUpdated(windowId);

        }

        @Test
        @DisplayName("When the Role Access Update is run for the GardenWorld Client "
                + "with the GardenUser role selected, then only that roles is updated")
        final void whenRunForGWAndGardenUserRole_thenOnlyGardenUserUpdated() {

            ProcessBuilder
                    .create(ctx)
                    .process(RoleAccessUpdate.class)
                    .withParameter(RoleAccessUpdate.AD_CLIENT_ID,
                            GARDEN_WORLD_CLIENT_ID)
                    .withParameter(RoleAccessUpdate.AD_ROLE_ID,
                            GARDEN_USER_ROLE_ID)
                    .execute(trxName);

            assertOnlyGWUserRoleUpdated(windowId);

        }

    }

}
