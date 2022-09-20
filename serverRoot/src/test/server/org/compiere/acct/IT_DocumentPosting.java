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
package org.compiere.acct;

import static org.adempiere.test.CommonGWData.JOE_BLOCK_ID;
import static org.adempiere.test.CommonGWData.STANDARD_PRICELIST_ID;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.core.domains.models.I_C_Invoice;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.adempiere.test.CommonIntegrationTestUtilities;
import org.compiere.db.CConnection;
import org.compiere.model.MAcctProcessor;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.server.AdempiereServer;
import org.compiere.session.ServerBean;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.services.dsl.ProcessBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

@Tag("DocProcess")
@Tag("Accounting")
@DisplayName("IT_DocumentPosting: Given Accounting in GardenWorld context")
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class IT_DocumentPosting extends CommonGWSetup {

    private static final String CLIENT_ACCOUNTING_IMMEDIATE = "I";
    private static final String CLIENT_ACCOUNTING_QUEUE = "Q";
    private static final String CLIENT_ACCOUNTING_DISABLED = "D";

    private DocPostingTestUtilities postTestUtils;
    private CommonIntegrationTestUtilities testUtils;

    private MInvoice invoice;
    private Doc_Invoice docSpy;
    private DocumentEngine docEngineSpy;

    private CConnection serverConnectionMock;
    private ServerBean serverBean;

    @Captor
    private ArgumentCaptor<Integer> clientIdCaptor;
    @Captor
    private ArgumentCaptor<Properties> ctxCaptor;
    @Captor
    private ArgumentCaptor<Boolean> forceCaptor;
    @Captor
    private ArgumentCaptor<Integer> recordIdCaptor;
    @Captor
    private ArgumentCaptor<Integer> tableIdCaptor;
    @Captor
    private ArgumentCaptor<String> trxNameCaptor;

    private String getPostedStatus() {

        String sql = "SELECT Posted FROM C_Invoice "
                + "WHERE C_Invoice_ID=? "
                + "AND AD_Client_ID=?";
        return DB.getSQLValueString(trxName, sql, invoice.get_ID(),
                invoice.getAD_Client_ID());

    }

    private void closePeriod(Timestamp date) {

        MDocType docType =
                (MDocType) invoice.getC_DocTypeTarget();
        closePeriod(ctx, AD_ORG_ID, docType, date, trxName);

    }

    private void completeInvoice() {

        invoice.processIt(DocAction.ACTION_Complete);
        invoice.saveEx();
        moveProcessedOnTime5SecEarlier(invoice);
        invoice.load(trxName);

    }

    private void completeInvoiceOnDate(final Timestamp date) {

        invoice.setDateAcct(date);
        completeInvoice();

    }

    private AdempiereServer createAcctAdempiereServer(final String trxName) {

        int gardenWorldAcctProcessorId = 100;
        MAcctProcessor model =
                new MAcctProcessor(ctx, gardenWorldAcctProcessorId, trxName);
        AdempiereServer server = AdempiereServer.create(model);
        return server;

    }

    private MInvoice createDraftInvoice() {

        MInvoice invoice = new MInvoice(ctx, 0, trxName);
        invoice.setC_BPartner_ID(JOE_BLOCK_ID);
        invoice.setM_PriceList_ID(STANDARD_PRICELIST_ID);
        invoice.setIsSOTrx(true);
        invoice.saveEx();

        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setM_Product_ID(CommonGWData.AZALEA_BUSH_PRODUCT_ID);
        invoiceLine.setQty(Env.ONE);
        invoiceLine.setPrice();
        invoiceLine.saveEx();

        invoice.load(trxName);

        return invoice;

    }

    private ServerBean createServerBean() {

        ServerBean server = spy(ServerBean.class);
        return server;

    }

    private void moveProcessedOnTime5SecEarlier(MInvoice invoice) {

        // Necessary to avoid a 2 second filter in the accounting processors
        invoice.setProcessedOn(
                invoice.getProcessedOn()
                        .subtract(BigDecimal.valueOf(5000)));
        invoice.saveEx();

    }

    private void openPeriod(Timestamp date) {

        MDocType docType =
                (MDocType) invoice.getC_DocTypeTarget();
        testUtils.openPeriod(ctx, AD_ORG_ID, docType, date, trxName);

    }

    private void runAcctProcessor() {

        trx.commit();
        trx.start();
        AdempiereServer server = createAcctAdempiereServer(trxName);
        server.runNow();

        int id = invoice.get_ID();
        invoice = new MInvoice(ctx, id, null);

    }

    private void runClientAcctProcessor() {

        trx.commit();
        trx.start();
        ProcessBuilder.create(ctx)
                .process(org.adempiere.process.ClientAcctProcessor.class)
                .withTitle("ClientAcctProcessorTest")
                .execute(trxName);

        int id = invoice.get_ID();
        invoice = new MInvoice(ctx, id, null);

    }

    private void assertServerNotUsed() {

        verify(docEngineSpy, never()).getServer();

    }

    private void assertAcctProcessorWasUsed() {

        verify(docEngineSpy).getServer();

    }

    private void assertClientAcctProcessorFailsToRun() {

        AdempiereException e = assertThrows(
                AdempiereException.class, () -> {
                    runClientAcctProcessor();
                });
        assertTrue(e.getMessage().startsWith(
                "@ProcessRunError@ @Error@ Client Accounting is not enabled"));

    }

    private void assertDocEnginePostItNotCalled() {

        verify(docEngineSpy, never()).postIt();

    }

    private void assertDocPostedStatusIsPeriodClosed() {

        assertEquals("p", getPostedStatus());

    }

    private void assertDocWasNotPosted() {

        invoice.load(trxName);
        assertFalse(invoice.isPosted());

    }

    private void assertDocWasPosted() {

        invoice.load(trxName);
        assertTrue(invoice.isPosted());

    }

    private void assertDocWasPostedImmediately() {

        verify(docEngineSpy).postIt();
        verify(docEngineSpy).postImmediate(true);

    }

    private void assertDocWasPostedWithinItsOwnTransaction() {

        verify(docEngineSpy).getDoc(any(), anyString(),
                anyInt(), trxNameCaptor.capture());
        verify(docSpy).post(true, true);
        assertEquals(trxName, trxNameCaptor.getValue(),
                "Posting was not performed within the document's own transaction");

    }

    private void
            assertServerPostedInvoiceUsingNullTransaction() {

        verify(serverBean).postImmediate(ctxCaptor.capture(),
                clientIdCaptor.capture(),
                tableIdCaptor.capture(), recordIdCaptor.capture(),
                forceCaptor.capture(), trxNameCaptor.capture());

        assertNotEquals(ctx, ctxCaptor.getValue());
        assertEquals(AD_CLIENT_ID, clientIdCaptor.getValue());
        assertEquals(I_C_Invoice.Table_ID, tableIdCaptor.getValue());
        assertEquals(invoice.get_ID(), recordIdCaptor.getValue());
        assertEquals(true, forceCaptor.getValue());
        assertNull(trxNameCaptor.getValue());

    }

    @BeforeAll
    static void givenTheGardenWorldContext() {

    }

    @BeforeEach
    void setupTests() {

        postTestUtils = new DocPostingTestUtilities();
        testUtils = new CommonIntegrationTestUtilities();

    }

    @Nested
    @DisplayName("Given a document that is not automatically posted "
            + "and has no errors")
    class GivenStandardDocWithNoErrors {

        @BeforeEach
        void createDraftInvoiceWithNoErrors() {

            invoice = createDraftInvoice();

            docSpy = createDocInvoiceSpy(invoice);
            docEngineSpy = createDocumentEngineSpy(invoice, docSpy);
            invoice.setDocumentEngine_forTestingOnly(docEngineSpy);

        }

        Doc_Invoice createDocInvoiceSpy(MInvoice invoice) {

            Doc_Invoice docSpy = null;
            MAcctSchema[] schemas = MAcctSchema
                    .getClientAcctSchema(ctx, AD_CLIENT_ID);
            String tableName = I_C_Invoice.Table_Name;
            int recordId = invoice.get_ID();
            String sql = "SELECT * FROM " + tableName
                    + " WHERE " + tableName + "_ID=?";
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = DB.prepareStatement(sql, trxName);
                pstmt.setInt(1, recordId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    docSpy = spy(
                            new Doc_Invoice(schemas, rs, trxName));
                } else
                    fail("Not Found: " + tableName + "_ID="
                            + recordId);
            } catch (Exception e) {
                fail("Unexpected exception " + e.getMessage());
            } finally {
                DB.close(rs, pstmt);
                rs = null;
                pstmt = null;
            }

            lenient().doReturn(DocAction.STATUS_Completed).when(docSpy)
                    .getDocStatus();

            return docSpy;

        }

        DocumentEngine createDocumentEngineSpy(MInvoice invoice,
                Doc_Invoice docSpy) {

            serverConnectionMock = mock(CConnection.class);
            lenient().doReturn(true).when(serverConnectionMock)
                    .isAppsServerOK(anyBoolean());
            serverBean = createServerBean();

            DocumentEngine docEngineSpy = spy(new DocumentEngine(invoice,
                    invoice.getDocStatus()));
            lenient().doReturn(docSpy).when(docEngineSpy).getDoc(any(),
                    anyString(), anyInt(), any());
            lenient().doReturn(serverConnectionMock).when(docEngineSpy)
                    .getServerConnection();
            lenient().doReturn(serverBean).when(docEngineSpy).getServer();
            return docEngineSpy;

        }

        @Nested
        @DisplayName("Given Immediate Client Accounting config")
        class GivenImmediateAcctConfig {

            @BeforeEach
            void setImmediateAcctConfig() {

                postTestUtils.setClientAccounting(ctx, AD_CLIENT_ID,
                        CLIENT_ACCOUNTING_IMMEDIATE, null);

            }

            @AfterEach
            void resetAcctConfig() {

                postTestUtils.resetClientAccounting(ctx, AD_CLIENT_ID, null);

            }

            @Nested
            @DisplayName("Given Automatic Period Control is enabled")
            class GivenAutomaticPeriodControlEnabled {

                @BeforeEach
                void setupAutomaticPeriodControl() {

                    testUtils.turnOnAutoPeriodControl(ctx, AD_CLIENT_ID);

                }

                @Nested
                @DisplayName("When document is completed within the automatic "
                        + "control dates")
                class WhenDocumentIsCompletedWithinAutomaticControlDates {

                    @BeforeEach
                    void completeDocumentWithDateToday() {

                        completeInvoiceOnDate(today);

                    }

                    @Test
                    @DisplayName("Then it is posted immediately within its "
                            + "own transaction and the AcctProcessor is not used")
                    void docIsPostedImmediately() {

                        assertDocWasPosted();
                        assertDocWasPostedImmediately();
                        assertDocWasPostedWithinItsOwnTransaction();
                        assertServerNotUsed();

                    }

                }

            }

            @Nested
            @DisplayName("Given Automatic Period Control is disabled")
            class GivenAutomaticPeriodControlDisabled {

                @BeforeEach
                void disableAutomaticPeriodControl() {

                    testUtils.turnOffAutoPeriodControl(ctx, AD_CLIENT_ID);

                }

                @Nested
                @DisplayName("When document is completed within an open period")
                class WhenDocIsCompletedInOpenPeriod {

                    @BeforeEach
                    void openPeriodAndCompleteDoc() {

                        openPeriod(today);
                        completeInvoiceOnDate(today);

                    }

                    @Test
                    @DisplayName("Then it is posted immediately within its own "
                            + "transaction and the AcctProcessor is not used")
                    void docIsPostedImmediately() {

                        assertDocWasPosted();
                        assertDocWasPostedImmediately();
                        assertDocWasPostedWithinItsOwnTransaction();
                        assertServerNotUsed();

                    }

                }

                @Nested
                @DisplayName("When document is completed within a closed period")
                class WhenDocIsCompletedInClosedPeriod {

                    @BeforeEach
                    void closeThePeriod() {

                        closePeriod(today);

                    }

                    @Test
                    @DisplayName("Then it throws a Period Closed exception"
                            + " and the AcctProcessor is not used")
                    void docThrowsPeriodClosedException() {

                        assertThrows(PeriodClosedException.class, () -> {
                            invoice.processIt(DocAction.ACTION_Complete);
                        });

                        assertDocWasNotPosted();
                        assertServerNotUsed();

                    }

                }

            }

        }

        @Nested
        @DisplayName("Given Queue Client Accounting config")
        class GivenQueueClientAccountingConfig {

            @BeforeEach
            void setQueueAcctConfig() {

                postTestUtils.setClientAccounting(ctx, AD_CLIENT_ID,
                        CLIENT_ACCOUNTING_QUEUE, null);

            }

            @AfterEach
            void resetAcctConfig() {

                postTestUtils.resetClientAccounting(ctx, AD_CLIENT_ID, null);

            }

            @Nested
            @DisplayName("Given Automatic Period Control is enabled")
            class GivenAutomaticPeriodControlEnabled {

                @BeforeEach
                void setupAutomaticPeriodControl() {

                    testUtils.turnOnAutoPeriodControl(ctx, AD_CLIENT_ID);

                }

                @Nested
                @DisplayName("When document is completed within the "
                        + "automatic control dates")
                class WhenDocIsCompletedWithinAutomaticControlDates {

                    @BeforeEach
                    void completeDocumentWithDateToday() {

                        completeInvoiceOnDate(today);

                    }

                    @Test
                    @DisplayName("Then it is not posted immediately")
                    void thenItIsNotPostedImmediately() {

                        assertDocWasNotPosted();
                        assertDocEnginePostItNotCalled();
                        assertServerNotUsed();

                    }

                    @Test
                    @DisplayName("Then it is posted by the "
                            + "ClientAcctProcessor")
                    void thenItIsPostedByClientAcctProcessor()
                            throws Exception {

                        runClientAcctProcessor();
                        assertDocWasPosted();

                    }

                }

            }

            @Nested
            @DisplayName("Given Automatic Period Control is disabled")
            class GivenAutomaticPeriodControlDisabled {

                @BeforeEach
                void disableAutomaticPeriodControl() {

                    testUtils.turnOffAutoPeriodControl(ctx, AD_CLIENT_ID);

                }

                @Nested
                @DisplayName("When document is completed within an open "
                        + "period")
                class WhenDocIsCompletedInOpenPeriod {

                    @BeforeEach
                    void openPeriodAndCompleteDoc() {

                        openPeriod(today);
                        completeInvoiceOnDate(today);

                    }

                    @Test
                    @DisplayName("Then it is posted by the AcctProcessor")
                    void thenItIsPostedByAcctProcessor() {

                        runAcctProcessor();
                        assertDocWasPosted();

                    }

                    @Test
                    @DisplayName("Then it is posted by the "
                            + "ClientAcctProcessor")
                    void thenItIsPostedByClientAcctProcessor() {

                        runClientAcctProcessor();
                        assertDocWasPosted();

                    }

                }

                @Nested
                @DisplayName("When document is completed within a closed "
                        + "period")
                class WhenDocIsCompletedInAClosedPeriod {

                    @BeforeEach
                    void completeDocThenCloseThePeriod() {

                        openPeriod(today);
                        completeInvoiceOnDate(today);
                        closePeriod(today);

                    }

                    @Test
                    @DisplayName("Then it is flagged with status Period "
                            + "Closed by the AcctProcessor")
                    void
                            thenItIsFlaggedPeriodClosedByAcctProcessor() {

                        runClientAcctProcessor();
                        assertDocPostedStatusIsPeriodClosed();

                    }

                    @Test
                    @DisplayName("Then it is flagged with status Period "
                            + "Closed by the ClientAcctProcessor")
                    void thenItIsFlaggedPeriodClosedByClientAcctProcessor() {

                        runClientAcctProcessor();
                        assertDocPostedStatusIsPeriodClosed();

                    }

                }

            }

        }

        @Nested
        @DisplayName("Given Disabled Client Accounting config")
        class GivenDisabledAcctConfig {

            @BeforeEach
            void disableAcctConfig() {

                postTestUtils.setClientAccounting(ctx, AD_CLIENT_ID,
                        CLIENT_ACCOUNTING_DISABLED, null);

            }

            @Nested
            @DisplayName("Given Automatic Period Control is enabled")
            class GivenAutomaticPeriodControlIsEnabled {

                @BeforeEach
                void setupAutomaticPeriodControl() {

                    testUtils.turnOnAutoPeriodControl(ctx, AD_CLIENT_ID);

                }

                @Nested
                @DisplayName("When document is completed within the "
                        + "automatic control dates")
                class WhenDocIsCompletedWithinAutomaticControlDates {

                    @BeforeEach
                    void completeDocumentWithDateToday() {

                        completeInvoiceOnDate(today);

                    }

                    @Test
                    @DisplayName("Then it is posted by the AcctProcessor")
                    void thenItIsPostedByAcctProcessor() {

                        runAcctProcessor();
                        assertDocWasPosted();

                    }

                    @Test
                    @DisplayName("Then the ClientAcctProcessor fails to run "
                            + "and the doc is not posted")
                    void thenItIsNotPostedByClientAcctProcessor() {

                        assertClientAcctProcessorFailsToRun();
                        assertDocWasNotPosted();

                    }

                }

            }

            @Nested
            @DisplayName("Given Automatic Period Control is disabled")
            class GivenAutomaticPeriodControlIsDisabled {

                @BeforeEach
                void disableAutomaticPeriodControl() {

                    testUtils.turnOffAutoPeriodControl(ctx, AD_CLIENT_ID);

                }

                @Nested
                @DisplayName("When document is completed within an open "
                        + "period")
                class WhenDocIsCompletedInOpenPeriod {

                    @BeforeEach
                    void openPeriodAndCompleteDoc() {

                        openPeriod(today);
                        completeInvoiceOnDate(today);

                    }

                    @Test
                    @DisplayName("Then it is posted by the AcctProcessor")
                    void thenItIsPostedByAcctProcessor() {

                        runAcctProcessor();
                        assertDocWasPosted();

                    }

                    @Test
                    @DisplayName("Then the ClientAcctProcessor fails to run "
                            + "and the doc is not posted")
                    void thenItIsNotPostedByClientAcctProcessor() {

                        assertClientAcctProcessorFailsToRun();
                        assertDocWasNotPosted();

                    }

                }

                @Nested
                @DisplayName("When document is completed within a closed "
                        + "period")
                class WhenDocIsCompletedInAClosedPeriod {

                    @BeforeEach
                    void completeDocThenCloseThePeriod() {

                        openPeriod(today);
                        completeInvoiceOnDate(today);
                        closePeriod(today);

                    }

                    @Test
                    @DisplayName("Then it is flagged with status Period "
                            + "Closed by the AcctProcessor")
                    void
                            thenItIsFlaggedPeriodClosedByAcctProcessor() {

                        runAcctProcessor();
                        assertDocPostedStatusIsPeriodClosed();

                    }

                    @Test
                    @DisplayName("Then the ClientAcctProcessor fails to run "
                            + "and the doc is not posted")
                    void thenItIsNotPostedByClientAcctProcessor() {

                        assertClientAcctProcessorFailsToRun();
                        assertDocWasNotPosted();

                    }

                }

            }

        }

        @Nested
        @DisplayName("Given the document is posted")
        class GivenDocumentIsPosted {

            @BeforeEach
            void completeAndPostDoc() {

                testUtils.turnOnAutoPeriodControl(ctx, AD_CLIENT_ID);
                postTestUtils.setClientAccounting(ctx, AD_CLIENT_ID,
                        CLIENT_ACCOUNTING_IMMEDIATE, null);
                completeInvoiceOnDate(today);
                assertDocWasPosted();

            }

            @Nested
            @DisplayName("Given client accounting is enabled")
            class GivenClientAcctIsEnabled {

                @Nested
                @DisplayName("When submitted for posting")
                class WhenSubmitedForPosting {

                    @Test
                    @DisplayName("Then the doc will be reposted and previous "
                            + "accounting will be deleted")
                    void thenIsRepostedAndPreviousAcctFactsDeleted() {

                        invoice.processIt(DocAction.ACTION_Post);
                        assertServerNotUsed();
                        // Count includes original posting
                        verify(docSpy, times(2)).deleteAcct();
                        verify(docSpy, times(2)).postImmediate(true);
                        verify(docSpy, times(2)).post(true, true);

                    }

                }

            }

            @Nested
            @DisplayName("Given client accounting is Disabled")
            class GivenClientAcctIsDisabled {

                @BeforeEach
                void disableClientAccounting() {

                    postTestUtils.disableClientAccounting(ctx, AD_CLIENT_ID,
                            null);

                }

                @Nested
                @DisplayName("When submitted for posting")
                class WhenSubmitedForPosting {

                    @Test
                    @DisplayName("Then the doc will be reposted and previous "
                            + "accounting will be deleted")
                    void
                            thenIsRepostedAndPreviousAcctFactsDeleted() {

                        invoice.processIt(DocAction.ACTION_Post);

                        verify(docSpy, times(1)).deleteAcct();
                        verify(docSpy, times(1)).postImmediate(true);
                        verify(docSpy, times(1)).post(true, true);

                        // Once for the first posting, twice for the repost
                        // Count includes original posting
                        verify(docEngineSpy, times(2)).postIt();
                        verify(docEngineSpy, times(2)).postImmediate(true);

                        assertAcctProcessorWasUsed();

                        assertServerPostedInvoiceUsingNullTransaction();

                    }

                }

            }

        }

    }

    @Nested
    @DisplayName("Given an existing document "
            + "and has no errors")
    class GivenAnExistingDocumentWithNoErrors {
        
        private int invoiceId;
        
        @BeforeEach
        void getExistingDocument() {
            
            int windowNo = 0;
            invoiceId = 103;
            MInvoice existingInvoice = new MInvoice(ctx, invoiceId, null);
            Env.setContext(ctx, windowNo, "IsSOTrx", existingInvoice.isSOTrx());
            Env.setContext(ctx, windowNo, "IsApproved", existingInvoice.isApproved());
            Env.setContext(ctx, windowNo,  "C_Currency_ID", existingInvoice.getC_Currency_ID());
            
        }
        
        @Test
        final void whenRepostedFromSwing_postingShouldSucceed() {
            
            String error = DocumentEngine.postImmediate(ctx, AD_CLIENT_ID, 
                    I_C_Invoice.Table_ID, invoiceId, false, null);
            assertNull(error);
            MInvoice existingInvoice = new MInvoice(ctx, invoiceId, null);
            assertTrue(existingInvoice.isPosted());
            
        }

        @Test
        final void whenRepostedFormZK_postingShouldSucceed() {
            
            MAcctSchema[] ass = MAcctSchema.getClientAcctSchema(ctx, AD_CLIENT_ID);
            String error = Doc.postImmediate(ass, I_C_Invoice.Table_ID, invoiceId, false, null);

            assertNull(error);
            MInvoice existingInvoice = new MInvoice(ctx, invoiceId, null);
            assertTrue(existingInvoice.isPosted());
            
        }

    }

}
