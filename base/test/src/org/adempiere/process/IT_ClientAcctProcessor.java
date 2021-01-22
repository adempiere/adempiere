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
package org.adempiere.process;

import static org.compiere.Adempiere.startup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.stream.Stream;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.IntegrationTestTag;
import org.compiere.acct.Doc;
import org.compiere.model.I_AD_SysConfig;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.FactAcctReset;
import org.compiere.process.ProcessInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;
import org.eevolution.service.dsl.ProcessBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Process")
@Tag("Accounting")
@Tag("ClientAcctProcessor")
@DisplayName("Given the GardenWorld client and the ClientAcctProcessor")
class IT_ClientAcctProcessor implements IntegrationTestTag {

    // Can't use the CommonGWSetup as the TrxName can't be maintained
    // throughout all tests
    private static int AD_CLIENT_ID = CommonGWData.AD_CLIENT_ID;
    private static final String CLIENT_ACCOUNTING_IMMEDIATE = "I";
    private static final String CLIENT_ACCOUNTING_DISABLED = "D";
    private static final boolean IS_CLIENT = true;
    private static int initialClientAcctConfigId;
    private static boolean createdNew = false;
    private static String initialClientAcctValue = "";
    private ProcessBuilder process;

    ClientAcctProcessor processor;
    private static Timestamp today;
    private static Properties ctx;
    private static String trxName = null;

    private void assertNoUnpostedDocuments() {

        assertNoUnpostedDocuments(0);

    }

    private void assertNoUnpostedDocuments(int tableId) {

        int[] documentsTableID = Doc.getDocumentsTableID();
        String[] documentsTableName = Doc.getDocumentsTableName();
        for (int i = 0; i < documentsTableID.length; i++) {

            if (tableId > 0 && tableId != documentsTableID[i])
                continue;

            String tableName = documentsTableName[i];
            String where = "Processed='Y' AND Posted='N'";
            BigDecimal count = new Query(ctx, tableName, where, trxName)
                    .setClient_ID()
                    .setOnlyActiveRecords(true)
                    .aggregate("Processed", Query.AGGREGATE_COUNT);

            assertEquals(Env.ZERO, count, "Table " + tableName + " has "
                    + count + " unposted records");

        }

    }

    private void assertProcessWasSuccessful(ProcessInfo info) {
    
        assertFalse(info.isError());
        assertEquals("OK", info.getSummary());
    
    }

    private Stream<MSysConfig> clientAccountingConfigs(int clientId) {

        String where = "Name=? AND AD_Client_ID IN (0, ?)";
        return new Query(ctx, I_AD_SysConfig.Table_Name, where, null)
                .setOnlyActiveRecords(true)
                .setParameters("CLIENT_ACCOUNTING", clientId)
                .setOrderBy("AD_Client_ID DESC, AD_Org_ID DESC")
                .list(MSysConfig.class)
                .stream();

    }

    private void disableClientAccounting() {

        MSysConfig gwAccounting = getOrCreateClientAcctConfig();
        gwAccounting.setValue(CLIENT_ACCOUNTING_DISABLED);
        gwAccounting.saveEx();

    }

    private MSysConfig getOrCreateClientAcctConfig() {

        createdNew = false;
        MSysConfig gwAccounting = clientAccountingConfigs(AD_CLIENT_ID)
                .filter(config -> config.getAD_Client_ID() == AD_CLIENT_ID)
                .findFirst()
                .orElseGet(() -> {
                    MSysConfig config = new MSysConfig(ctx, 0, null);
                    config.setName("CLIENT_ACCOUNTING");
                    createdNew = true;
                    return config;
                });
        gwAccounting.saveEx();
        setInitialConfigId(gwAccounting.get_ID());
        setInitialConfigValue(gwAccounting.getValue());
        return gwAccounting;

    }

    private void enableClientAccounting() {

        MSysConfig gwAccounting = getOrCreateClientAcctConfig();
        gwAccounting.setValue(CLIENT_ACCOUNTING_IMMEDIATE);
        gwAccounting.saveEx();

    }

    private void resetClientAccounting() {

        resetClientAccounting(0);

    }

    private void resetClientAccounting(int tableId) {

        ProcessBuilder.create(ctx)
                .process(org.compiere.process.FactAcctReset.class)
                .withTitle("FactAcctReset")
                .withParameter(FactAcctReset.AD_CLIENT_ID, AD_CLIENT_ID)
                .withParameter(FactAcctReset.DELETEPOSTING, true)
                .withParameter(FactAcctReset.AD_TABLE_ID, tableId)
                .withParameter(FactAcctReset.DATEACCT,
                        TimeUtil.getDay(1999, 01, 01), today)
                .execute();

    }

    private void resetInitialConfig() {

        if (createdNew) {
            MSysConfig config =
                    new MSysConfig(ctx, initialClientAcctConfigId, null);
            config.deleteEx(true);
        } else {
            resetInitialConfigValue();
        }

    }

    private void resetInitialConfigValue() {

        MSysConfig config =
                new MSysConfig(ctx, initialClientAcctConfigId, null);
        config.setValue(initialClientAcctValue);
        config.saveEx();

    }

    private void setInitialConfigId(int id) {

        initialClientAcctConfigId = id;

    }

    private void setInitialConfigValue(String value) {

        initialClientAcctValue = value;

    }

    @BeforeAll
    static void givenTheGardenWorldContext() {

        today = TimeUtil.getDay(System.currentTimeMillis());
        ctx = Env.getCtx();
        ctx.setProperty("#AD_Org_ID", Integer.toString(CommonGWData.AD_ORG_ID));
        ctx.setProperty("#AD_User_ID",
                Integer.toString(CommonGWData.AD_USER_ID));
        ctx.setProperty("#AD_Client_ID", Integer.toString(AD_CLIENT_ID));
        ctx.setProperty("#Date",
                TimeUtil.getDay(System.currentTimeMillis()).toString());
        ctx.setProperty("#AD_Language", "en");

        Ini.setClient(IS_CLIENT);
        Ini.loadProperties(false);
        startup(IS_CLIENT);

    }

    @BeforeEach
    void givenClientAcctProcessor() {

        process = ProcessBuilder.create(ctx)
                .process(org.adempiere.process.ClientAcctProcessor.class)
                .withTitle("ClientAcctProcessorTest");

    }

    @AfterEach
    void resetAccountingAndRemoveInstanceInfo() {

        int instanceId = process.getProcessInfo().getAD_PInstance_ID();
        String sql = "DELETE AD_PInstance_Log "
                + "WHERE AD_PInstance_ID=" + instanceId;
        DB.executeUpdateEx(sql, null);
        resetClientAccounting();

    }

    @Nested
    @DisplayName("When ClientAccounting is disabled")
    class WhenClientAccountingDisabled {

        @BeforeEach
        void disableAccounting() {

            disableClientAccounting();

        }

        @Test
        @DisplayName("Then the ClientAcctProcessor doIt method should "
                + "throw an exception")
        final void whenClientAcctDisabled_throwsException() {

            assertThrows(AdempiereException.class, () -> {
                process.execute(trxName);
            });

        }

    }

    @Nested
    @DisplayName("When ClientAccounting is enabled")
    class WhenClientAccountingEnabled {

        @BeforeEach
        void enableAccounting() {

            enableClientAccounting();

        }

        @AfterEach
        void resetClientAccountingConfig() {

            resetInitialConfig();

        }

        @Test
        @DisplayName("When passed no parameters, then the process should "
                + "succeed")
        final void whenNoParameters_doItReturnsOk() throws Exception {

            ProcessInfo info = process.execute(trxName);
            assertProcessWasSuccessful(info);                

        }

        @Test
        @DisplayName("When passed null parameters, then the process should "
                + "succeed")
        final void whenNullParameters_doItReturnsOk() throws Exception {

            ProcessInfo info = process
                    .withParameter("ANullParameter", null)
                    .withParameter("AnotherNullParameter", null)
                    .execute(trxName);
            assertProcessWasSuccessful(info);                

        }

        @Test
        @DisplayName("When passed unknown parameters, then the process should "
                + "succeed")
        final void whenUnknownParameters_doItReturnsOk() throws Exception {

            ProcessInfo info = process
                    .withParameter("AnUnknownParameter", "someValue")
                    .execute(trxName);
            assertProcessWasSuccessful(info);                

        }

        @Nested
        @DisplayName("When the acct schema is provided as a parameter")
        class WhenAcctSchemaProvidedAsAParameter {

            @BeforeEach
            void provideAcctSchemaAsParameter() {

                MAcctSchema[] as =
                        MAcctSchema.getClientAcctSchema(ctx, AD_CLIENT_ID);
                process = process.withParameter("C_AcctSchema_ID",
                        as[0].getC_AcctSchema_ID());

            }

            @Test
            @DisplayName("Then the process should succeed")
            final void whenPassedSchema_processSucceeds() {

                ProcessInfo info = process.execute(trxName);
                assertProcessWasSuccessful(info);                

            }

        }

        @Nested
        @DisplayName("When passed a non-document table")
        class WhenPassedANonDocumentTable {

            int tableId = 0;

            @BeforeEach
            void provideANonDocumentTableAsParameter() {

                MTable table = MTable.get(ctx, "C_BPartner");
                tableId = table.getAD_Table_ID();
                process = process
                        .withParameter("AD_Table_ID", tableId);

            }

            @Test
            @DisplayName("Then the process should succeed")
            final void whenPassedNonDocumentTable_processSucceeds()
                    throws Exception {

                ProcessInfo info = process.execute(trxName);
                assertProcessWasSuccessful(info);                

            }

        }

        @Nested
        @DisplayName("When passed a document table")
        class WhenPassedATable {

            int tableId = 0;

            @BeforeEach
            void provideATableAsParameter() {

                MTable table = MTable.get(ctx, "C_Invoice");
                tableId = table.getAD_Table_ID();
                resetClientAccounting(tableId);
                process = process
                        .withParameter("AD_Table_ID", tableId);

            }

            @Test
            @DisplayName("Then the process should succeed "
                    + "and all documents in the table should be posted")
            final void whenPassedATable_processSucceeds() throws Exception {

                ProcessInfo info = process.execute(trxName);
                assertProcessWasSuccessful(info);                
                assertNoUnpostedDocuments(tableId);

            }

        }

        @Nested
        @DisplayName("When accounting is reset")
        class WhenAccountingIsReset {

            @BeforeEach
            void resetAccounting() {

                resetClientAccounting();

            }

            @Test
            @DisplayName("When the process is run, then all documents "
                    + "should be posted")
            final void WhenProcessIsRunThenAllDocumentShouldBePosted()
                    throws Exception {

                ProcessInfo info = process.execute(trxName);
                assertProcessWasSuccessful(info);                
                assertNoUnpostedDocuments();

            }

        }

    }

}
