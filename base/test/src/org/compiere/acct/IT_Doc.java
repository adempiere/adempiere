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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.adempiere.exceptions.DBException;
import org.adempiere.test.CommonGWData;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.process.DocAction;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Doc")
@Tag("DocProcess")
@Tag("Accounting")
@DisplayName("IT_Doc: Given the Doc class and the current database")
class IT_Doc extends CommonGWSetup {

    private boolean FORCE = true;
    private boolean REPOST = true;
    private boolean NOT_FORCED = false;
    private boolean NO_REPOST = false;

    private MInvoice createAndCompleteInvoice(String trxName) {

        MInvoice invoice = new MInvoice(ctx, 0, trxName);
        invoice.setC_BPartner_ID(CommonGWData.SEEDFARM_ID);
        invoice.setIsSOTrx(false);
        invoice.saveEx();

        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setM_Product_ID(CommonGWData.AZALEA_BUSH_PRODUCT_ID);
        invoiceLine.setQtyEntered(Env.ONE);
        invoiceLine.saveEx();

        invoice.processIt(DocAction.ACTION_Complete);

        invoice.saveEx();
        invoice.load(trxName);

        return invoice;

    }

    private String getPostedStatus(Doc doc, String trxName) {

        String postedStatus = null;
        String tableName = doc.get_TableName();
        String sql = "SELECT Posted FROM "
                + tableName + " WHERE " + tableName + "_ID=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, doc.get_ID());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                postedStatus = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new DBException(e, sql);
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

        return postedStatus;

    }

    void assertInvoiceIsUnlocked(MInvoice invoice) {

        invoice.load(trxName);
        assertFalse(invoice.isProcessing());

    }

    void assertInvoiceIsLocked(MInvoice invoice) {

        invoice.load(trxName);
        assertTrue(invoice.isProcessing());

    }

    @Nested
    @DisplayName("And Given an invoice document and a transaction")
    class andGivenAndInvoiceDocumentWithTransaction {

        MInvoice invoice;
        Doc invoiceDoc;

        @BeforeEach
        void giveAnInvoiceAndCompleteIt() {

            invoice = createAndCompleteInvoice(trxName);
            MAcctSchema[] acctSchema = MAcctSchema
                    .getClientAcctSchema(ctx, AD_CLIENT_ID);
            invoiceDoc = Doc.get(acctSchema, MInvoice.Table_ID,
                    invoice.getC_Invoice_ID(), trxName);
            assertInvoiceIsUnlocked(invoice);

        }

        @Test
        @DisplayName("When locked, then the document should be locked(flagged Processing)")
        void whenLocked_isProcessingIsTrue() {

            boolean locked = invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            assertTrue(locked);
            assertInvoiceIsLocked(invoice);

        }

        @Test
        @DisplayName("When locked, a second call to lock not forced should fail")
        void whenLocked_secondCallToLockNotForcedShouldFail() {

            invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            assertFalse(invoiceDoc.lock(NOT_FORCED, NO_REPOST));

        }

        @Test
        @DisplayName("When locked, a second call to lock forced should succeed")
        void whenLocked_secondCallToLockForcedShouldSucceed() {

            invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            assertTrue(invoiceDoc.lock(FORCE, NO_REPOST));
            assertInvoiceIsLocked(invoice);

        }

        @Test
        @DisplayName("When posted, calling lock with no repost should fail")
        void whenPosted_lockWithNoRepostFails() {

            invoiceDoc.post(NOT_FORCED, NO_REPOST);
            boolean locked = invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            assertFalse(locked);
            assertInvoiceIsUnlocked(invoice);

        }

        @Test
        @DisplayName("When posted, calling lock with repost should succeed")
        void whenPosted_lockWithRepostFails() {

            invoiceDoc.post(NOT_FORCED, NO_REPOST);
            boolean locked = invoiceDoc.lock(NOT_FORCED, REPOST);
            assertTrue(locked);
            assertInvoiceIsLocked(invoice);

        }

        @Test
        @DisplayName("When locked, a call to unlock should unlock the doc")
        void whenLocked_unlockShouldUnlockIt() {

            invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            invoiceDoc.unlock();
            assertInvoiceIsUnlocked(invoice);

        }

        @Test
        @DisplayName("When locked and savePostedStatus is called, "
                + "then doc should be unlocked and the status should be set")
        void whenLockedAndSavePostedStatus_thenDocIsUnlockedAndStatusSet() {

            invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            Doc.savePostedStatus(invoiceDoc, Doc.STATUS_NotBalanced, trxName);
            assertInvoiceIsUnlocked(invoice);
            assertEquals(Doc.STATUS_NotBalanced,
                    getPostedStatus(invoiceDoc, trxName));

        }

        @Test
        @DisplayName("When streamUnpostedRecordsIdsForTableOnDate is called, "
                + "then it should return the invoice Id")
        void whenStreamUnpostedRecordIdsForTableOnDate_thenIdIsReturned() {

            boolean found = Doc.streamUnpostedRecordIdsForTableOnDate(ctx,
                    invoiceDoc.get_TableName(), invoice.getProcessedOn(),
                    trxName)
                    .anyMatch(id -> id == invoice.get_ID());

            assertTrue(found, "Invoice ID was not returned as expected");

        }

        @Test
        @DisplayName("When getListOfUnpostedProcessedOnDates is called "
                + "with null maxTime, then it should not return the invoice "
                + "ProcessedOn value")
        void whenGetListOfUnpostedProcessedOnDatesWithNullMax_thenProcessedOnNotFound() {

            String[] tables = new String[] { invoiceDoc.get_TableName() };
            List<BigDecimal> list = Doc.getListOfUnpostedProcessedOnDates(ctx,
                    tables, null, trxName);

            // Warning - brittle on a slow machine
            assertFalse(list.contains(invoice.getProcessedOn()), "This tests "
                    + "checks for a default delay of 2 seconds.  If the test "
                    + "processing from invoice complete to test is slow, "
                    + "this test may fail.");

        }

        @Test
        @DisplayName("When getListOfUnpostedProcessedOnDates is called "
                + "with maxTime > ProcessedOn, then it should return the "
                + "invoice ProcessedOn value")
        void whenGetListOfUnpostedProcessedOnDatesWithMaxGT_thenProcessedOnFound()
                throws Exception {

            BigDecimal max = invoice.getProcessedOn().add(Env.ONE);
            String[] tables = new String[] { invoiceDoc.get_TableName() };
            List<BigDecimal> list = Doc.getListOfUnpostedProcessedOnDates(ctx,
                    tables, max, trxName);

            assertTrue(list.contains(invoice.getProcessedOn()));

        }

        @Test
        @DisplayName("When getListOfUnpostedProcessedOnDates is called "
                + "with maxTime < ProcessedOn, then it should not return the "
                + "invoice ProcessedOn value")
        void whenGetListOfUnpostedProcessedOnDatesWithMaxLT_thenProcessedOnNotFound()
                throws Exception {

            BigDecimal max = invoice.getProcessedOn().subtract(Env.ONE);
            String[] tables = new String[] { invoiceDoc.get_TableName() };
            List<BigDecimal> list = Doc.getListOfUnpostedProcessedOnDates(ctx,
                    tables, max, trxName);

            assertFalse(list.contains(invoice.getProcessedOn()));

        }

    }

    @Nested
    @DisplayName("And given an invoice document and no transaction")
    class andGivenAnInvoiceDocumentWithNoTransaction {

        String initialTrxName;
        MInvoice invoice;
        Doc invoiceDoc;

        @BeforeEach
        void giveAnInvoiceAndCompleteItWithNoTransaction() {

            initialTrxName = trxName;
            trxName = null;
            invoice = createAndCompleteInvoice(null);
            MAcctSchema[] acctSchema = MAcctSchema
                    .getClientAcctSchema(ctx, AD_CLIENT_ID);
            invoiceDoc = Doc.get(acctSchema, MInvoice.Table_ID,
                    invoice.getC_Invoice_ID(), null);
            assertFalse(invoice.isProcessing());

        }

        @AfterEach
        void deleteInvoice() {

            invoice.deleteEx(FORCE);
            trxName = initialTrxName;

        }

        @Test
        @DisplayName("When locked, then the document should be flagged Processing")
        void whenLocked_isProcessingIsTrue() {

            boolean locked = invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            assertTrue(locked);
            assertInvoiceIsLocked(invoice);

        }

        @Test
        @DisplayName("When locked, a call to unlock should unlock the doc")
        void whenLocked_aCallUnlockShouldUnlockTheDoc() {

            invoiceDoc.lock(NOT_FORCED, NO_REPOST);
            invoiceDoc.unlock();
            assertInvoiceIsUnlocked(invoice);

        }

    }

}
