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
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.IntegrationTestTag;
import org.compiere.acct.Doc;
import org.compiere.acct.DocPostingTestUtilities;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MTable;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Ini;
import org.compiere.util.TimeUtil;
import org.eevolution.services.dsl.ProcessBuilder;
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
@DisplayName("IT_ClientAcctProcessor: Given the GardenWorld client and the ClientAcctProcessor")
class IT_ClientAcctProcessor implements IntegrationTestTag {

    // Can't use the CommonGWSetup as the TrxName can't be maintained
    // throughout all tests
    private static int AD_CLIENT_ID = CommonGWData.AD_CLIENT_ID;
    private static final String CLIENT_ACCOUNTING_IMMEDIATE = "I";
    private static final boolean IS_CLIENT = true;
    private ProcessBuilder process;

    ClientAcctProcessor processor;
    private static Properties ctx;
    private static String trxName = null;
    private DocPostingTestUtilities docUtils;

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

    private MInvoice createAnInvoiceThatWillFailPosting() {
    
        MInvoice invoice = new MInvoice(ctx, 0, trxName);
        invoice.setC_BPartner_ID(CommonGWData.SEEDFARM_ID);
        invoice.setIsSOTrx(false);
        invoice.saveEx();
    
        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setM_Product_ID(CommonGWData.AZALEA_BUSH_PRODUCT_ID);
        invoiceLine.setQtyEntered(Env.ONE);
        invoiceLine.saveEx();
    
        invoice.prepareIt();
        invoice.completeIt();
        
        invoice.setDocStatus(DocAction.STATUS_Invalid);
        invoice.saveEx();
        
        return invoice;
    
    }


    @BeforeAll
    static void givenTheGardenWorldContext() {

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

        docUtils = new DocPostingTestUtilities();
        
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
        docUtils.resetClientAccounting(ctx, AD_CLIENT_ID, trxName);

    }

    @Nested
    @DisplayName("When ClientAccounting is disabled")
    class WhenClientAccountingDisabled {

        @BeforeEach
        void disableAccounting() {

            docUtils.disableClientAccounting(ctx, AD_CLIENT_ID, trxName);

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

            docUtils.setClientAccounting(ctx, AD_CLIENT_ID, CLIENT_ACCOUNTING_IMMEDIATE, trxName);

        }

        @AfterEach
        void resetClientAccountingConfig() {

            docUtils.resetInitialConfig(ctx, trxName);

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
                docUtils.resetClientAccounting(ctx, AD_CLIENT_ID, tableId, trxName);
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
            
            @Nested
            @DisplayName("When a document fails posting")
            class WhenADocumentFailsPosting {
                
                MInvoice invoice;
                
                @BeforeEach
                void setupACompletedInvoiceThatFailsPosting() {

                    //  Ensure all current invoices are posted
                    process.execute(trxName);
                    invoice = createAnInvoiceThatWillFailPosting();

                }
                
                @AfterEach
                void deleteInvoice() {
                    invoice.deleteEx(true);
                }
                
                @Test
                @DisplayName("Then the document will not be posted")
                void ThenTheDocumentWillNotBePosted() {
                    
                    process.execute();
                    invoice.load(trxName);
                    assertEquals(DocAction.STATUS_Invalid, invoice.getDocStatus());
                    assertFalse(invoice.isProcessing());
                    
                }
                
            }


        }

        @Nested
        @DisplayName("When accounting is reset")
        class WhenAccountingIsReset {

            @BeforeEach
            void resetAccounting() {

                docUtils.resetClientAccounting(ctx, AD_CLIENT_ID, trxName);

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
