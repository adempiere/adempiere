package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3InvoiceFunctionsTest extends CommonGWSetup {

    private MInvoice testInvoice;

    @BeforeAll
    void loadTestData() {
        testInvoice = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='Y'", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(testInvoice != null, "Need paid invoice for test");
    }

    @Test
    void invoicePaid_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        int currencyId = testInvoice.getC_Currency_ID();
        BigDecimal multiplierAP = testInvoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();

        BigDecimal javaResult = InvoiceFunctions.invoicePaid(invoiceId, currencyId, multiplierAP, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaid(invoiceId, currencyId, multiplierAP);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoicePaid(%d, %d, %s): java=%s, sql=%s",
                invoiceId, currencyId, multiplierAP, javaResult, sqlResult));
    }

    @Test
    void invoicePaidToDate_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        int currencyId = testInvoice.getC_Currency_ID();
        BigDecimal multiplierAP = testInvoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

        BigDecimal javaResult = InvoiceFunctions.invoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoicePaidToDate: java=%s, sql=%s", javaResult, sqlResult));
    }

    @Test
    void invoicePaidToDate_historicalDate_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        int currencyId = testInvoice.getC_Currency_ID();
        BigDecimal multiplierAP = testInvoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();
        // Use date before invoice - should return 0
        Timestamp dateAcct = Timestamp.valueOf("2020-01-01 00:00:00");

        BigDecimal javaResult = InvoiceFunctions.invoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaidToDate(invoiceId, currencyId, multiplierAP, dateAcct);

        assertEquals(0, BigDecimal.ZERO.compareTo(javaResult),
            "Historical date before payments should return zero");
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoicePaidToDate historical: java=%s, sql=%s", javaResult, sqlResult));
    }

    @Test
    void invoiceOpen_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();

        BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoiceId, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoiceId, null);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoiceOpen(%d, null): java=%s, sql=%s",
                invoiceId, javaResult, sqlResult));
    }

    @Test
    void invoiceOpen_withSchedule_matchesSql() {
        // Find invoice with payment schedules
        MInvoice invWithSchedule = new Query(Env.getCtx(), MInvoice.Table_Name,
            "IsPayScheduleValid='Y' AND DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(invWithSchedule != null, "Need invoice with payment schedule");

        // Get first schedule
        MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
            Env.getCtx(), invWithSchedule.getC_Invoice_ID(), 0, null);
        assumeTrue(schedules.length > 0, "Need payment schedule records");

        int scheduleId = schedules[0].getC_InvoicePaySchedule_ID();
        int invoiceId = invWithSchedule.getC_Invoice_ID();

        BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoiceId, scheduleId);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoiceId, scheduleId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoiceOpen(%d, %d): java=%s, sql=%s",
                invoiceId, scheduleId, javaResult, sqlResult));
    }

    @Test
    void invoiceOpenToDate_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

        BigDecimal javaResult = InvoiceFunctions.invoiceOpenToDate(invoiceId, null, dateAcct);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpenToDate(invoiceId, null, dateAcct);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoiceOpenToDate: java=%s, sql=%s", javaResult, sqlResult));
    }

    @Test
    void invoiceOpenToDate_historicalDate_matchesSql() {
        int invoiceId = testInvoice.getC_Invoice_ID();
        // Use date before invoice - should return full open amount (no allocations counted)
        Timestamp dateAcct = Timestamp.valueOf("2020-01-01 00:00:00");

        BigDecimal javaResult = InvoiceFunctions.invoiceOpenToDate(invoiceId, null, dateAcct);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpenToDate(invoiceId, null, dateAcct);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoiceOpenToDate historical: java=%s, sql=%s", javaResult, sqlResult));
    }

    @Test
    void invoiceOpenToDate_withSchedule_matchesSql() {
        // Find invoice with payment schedules
        MInvoice invWithSchedule = new Query(Env.getCtx(), MInvoice.Table_Name,
            "IsPayScheduleValid='Y' AND DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true)
            .first();
        assumeTrue(invWithSchedule != null, "Need invoice with payment schedule");

        MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
            Env.getCtx(), invWithSchedule.getC_Invoice_ID(), 0, null);
        assumeTrue(schedules.length > 0, "Need payment schedule records");

        int scheduleId = schedules[0].getC_InvoicePaySchedule_ID();
        int invoiceId = invWithSchedule.getC_Invoice_ID();
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());

        BigDecimal javaResult = InvoiceFunctions.invoiceOpenToDate(invoiceId, scheduleId, dateAcct);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpenToDate(invoiceId, scheduleId, dateAcct);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("invoiceOpenToDate(%d, %d, date): java=%s, sql=%s",
                invoiceId, scheduleId, javaResult, sqlResult));
    }
}
