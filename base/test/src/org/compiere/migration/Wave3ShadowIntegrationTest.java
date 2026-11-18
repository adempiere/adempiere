package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Stream;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.compiere.util.InvoiceFunctions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Comprehensive shadow integration tests for Wave 3.
 * Tests all 7 functions across multiple data scenarios.
 *
 * <p>Functions tested:
 * <ul>
 *   <li>paymentAllocated - allocated amount for a payment
 *   <li>paymentAvailable - available amount for a payment
 *   <li>invoicePaid - total paid amount for an invoice
 *   <li>invoicePaidToDate - paid amount up to a specific date
 *   <li>invoiceOpen - open (unpaid) amount for an invoice
 *   <li>invoiceOpenToDate - open amount up to a specific date
 *   <li>invoiceDiscount - discount available for early payment
 * </ul>
 *
 * <p>Test scenarios cover:
 * <ul>
 *   <li>Paid invoices (IsPaid='Y')
 *   <li>Open invoices (IsPaid='N')
 *   <li>Invoices with payment schedules (IsPayScheduleValid='Y')
 *   <li>Allocated payments (IsAllocated='Y')
 *   <li>Unallocated payments (IsAllocated='N')
 *   <li>Charge payments (C_Charge_ID > 0)
 * </ul>
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave3ShadowIntegrationTest extends CommonGWSetup {

    private List<MInvoice> paidInvoices;
    private List<MInvoice> openInvoices;
    private List<MInvoice> scheduledInvoices;
    private List<MPayment> allocatedPayments;
    private List<MPayment> unallocatedPayments;
    private List<MPayment> chargePayments;

    @BeforeAll
    void loadTestData() {
        paidInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='Y'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        openInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "DocStatus IN ('CO','CL') AND IsPaid='N'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        scheduledInvoices = new Query(Env.getCtx(), MInvoice.Table_Name,
            "IsPayScheduleValid='Y' AND DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true).setLimit(10).list();

        allocatedPayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL') AND IsAllocated='Y'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        unallocatedPayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL') AND IsAllocated='N'", null)
            .setOnlyActiveRecords(true).setLimit(20).list();

        chargePayments = new Query(Env.getCtx(), MPayment.Table_Name,
            "DocStatus IN ('CO','CL') AND C_Charge_ID > 0", null)
            .setOnlyActiveRecords(true).setLimit(10).list();

        // Require at least ONE invoice to run invoice tests
        // Both paid and open are acceptable - we test with whatever data exists
        assumeTrue(!paidInvoices.isEmpty() || !openInvoices.isEmpty(),
            "Need at least one completed invoice (paid or open) - found 0. " +
            "GardenWorld should have invoices with DocStatus IN ('CO','CL')");

        // Require at least ONE payment to run payment tests
        assumeTrue(!allocatedPayments.isEmpty() || !unallocatedPayments.isEmpty(),
            "Need at least one completed payment (allocated or unallocated) - found 0. " +
            "GardenWorld should have payments with DocStatus IN ('CO','CL')");
    }

    // ========== invoiceOpen Tests ==========

    Stream<Arguments> invoiceOpenTestCases() {
        return Stream.concat(
            paidInvoices.stream().map(i -> Arguments.of("paid_" + i.getC_Invoice_ID(), i)),
            openInvoices.stream().map(i -> Arguments.of("open_" + i.getC_Invoice_ID(), i))
        );
    }

    @ParameterizedTest(name = "invoiceOpen({0})")
    @MethodSource("invoiceOpenTestCases")
    void invoiceOpen_allInvoices_matchSql(String caseName, MInvoice invoice) {
        BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoice.getC_Invoice_ID(), null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoice.getC_Invoice_ID(), null);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    Stream<Arguments> scheduledInvoiceTestCases() {
        return scheduledInvoices.stream().flatMap(inv -> {
            MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
                Env.getCtx(), inv.getC_Invoice_ID(), 0, null);
            return java.util.Arrays.stream(schedules)
                .map(s -> Arguments.of("sched_" + inv.getC_Invoice_ID(),
                    inv, s.getC_InvoicePaySchedule_ID()));
        });
    }

    @ParameterizedTest(name = "invoiceOpen with schedule({0})")
    @MethodSource("scheduledInvoiceTestCases")
    void invoiceOpen_withSchedule_matchSql(String caseName, MInvoice invoice, int scheduleId) {
        BigDecimal javaResult = InvoiceFunctions.invoiceOpen(invoice.getC_Invoice_ID(), scheduleId);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpen(invoice.getC_Invoice_ID(), scheduleId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s schedule %d: java=%s, sql=%s",
                caseName, scheduleId, javaResult, sqlResult));
    }

    // ========== invoiceOpenToDate Tests ==========

    Stream<Arguments> invoiceOpenToDateTestCases() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Timestamp historicalDate = Timestamp.valueOf("2020-01-01 00:00:00");

        return Stream.concat(
            paidInvoices.stream().flatMap(i -> Stream.of(
                Arguments.of("paid_current_" + i.getC_Invoice_ID(), i, null, currentDate),
                Arguments.of("paid_historical_" + i.getC_Invoice_ID(), i, null, historicalDate)
            )),
            openInvoices.stream().flatMap(i -> Stream.of(
                Arguments.of("open_current_" + i.getC_Invoice_ID(), i, null, currentDate),
                Arguments.of("open_historical_" + i.getC_Invoice_ID(), i, null, historicalDate)
            ))
        );
    }

    @ParameterizedTest(name = "invoiceOpenToDate({0})")
    @MethodSource("invoiceOpenToDateTestCases")
    void invoiceOpenToDate_allInvoices_matchSql(String caseName, MInvoice invoice,
                                                 Integer scheduleId, Timestamp dateAcct) {
        BigDecimal javaResult = InvoiceFunctions.invoiceOpenToDate(
            invoice.getC_Invoice_ID(), scheduleId, dateAcct);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpenToDate(
            invoice.getC_Invoice_ID(), scheduleId, dateAcct);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    Stream<Arguments> scheduledInvoiceOpenToDateTestCases() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        return scheduledInvoices.stream().flatMap(inv -> {
            MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
                Env.getCtx(), inv.getC_Invoice_ID(), 0, null);
            return java.util.Arrays.stream(schedules)
                .map(s -> Arguments.of("sched_" + inv.getC_Invoice_ID(),
                    inv, s.getC_InvoicePaySchedule_ID(), currentDate));
        });
    }

    @ParameterizedTest(name = "invoiceOpenToDate with schedule({0})")
    @MethodSource("scheduledInvoiceOpenToDateTestCases")
    void invoiceOpenToDate_withSchedule_matchSql(String caseName, MInvoice invoice,
                                                   int scheduleId, Timestamp dateAcct) {
        BigDecimal javaResult = InvoiceFunctions.invoiceOpenToDate(
            invoice.getC_Invoice_ID(), scheduleId, dateAcct);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceOpenToDate(
            invoice.getC_Invoice_ID(), scheduleId, dateAcct);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s schedule %d: java=%s, sql=%s",
                caseName, scheduleId, javaResult, sqlResult));
    }

    // ========== invoicePaid Tests ==========

    Stream<Arguments> invoicePaidTestCases() {
        return Stream.concat(
            paidInvoices.stream().map(i -> Arguments.of("paid_" + i.getC_Invoice_ID(), i)),
            openInvoices.stream().map(i -> Arguments.of("open_" + i.getC_Invoice_ID(), i))
        );
    }

    @ParameterizedTest(name = "invoicePaid({0})")
    @MethodSource("invoicePaidTestCases")
    void invoicePaid_allInvoices_matchSql(String caseName, MInvoice invoice) {
        int invoiceId = invoice.getC_Invoice_ID();
        int currencyId = invoice.getC_Currency_ID();
        BigDecimal multiplierAP = invoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();

        BigDecimal javaResult = InvoiceFunctions.invoicePaid(invoiceId, currencyId, multiplierAP, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaid(invoiceId, currencyId, multiplierAP);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    // ========== invoicePaidToDate Tests ==========

    Stream<Arguments> invoicePaidToDateTestCases() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Timestamp historicalDate = Timestamp.valueOf("2020-01-01 00:00:00");

        return Stream.concat(
            paidInvoices.stream().flatMap(i -> Stream.of(
                Arguments.of("paid_current_" + i.getC_Invoice_ID(), i, currentDate),
                Arguments.of("paid_historical_" + i.getC_Invoice_ID(), i, historicalDate)
            )),
            openInvoices.stream().flatMap(i -> Stream.of(
                Arguments.of("open_current_" + i.getC_Invoice_ID(), i, currentDate),
                Arguments.of("open_historical_" + i.getC_Invoice_ID(), i, historicalDate)
            ))
        );
    }

    @ParameterizedTest(name = "invoicePaidToDate({0})")
    @MethodSource("invoicePaidToDateTestCases")
    void invoicePaidToDate_allInvoices_matchSql(String caseName, MInvoice invoice, Timestamp dateAcct) {
        int invoiceId = invoice.getC_Invoice_ID();
        int currencyId = invoice.getC_Currency_ID();
        BigDecimal multiplierAP = invoice.isSOTrx() ? BigDecimal.ONE : BigDecimal.ONE.negate();

        BigDecimal javaResult = InvoiceFunctions.invoicePaidToDate(
            invoiceId, currencyId, multiplierAP, dateAcct, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoicePaidToDate(
            invoiceId, currencyId, multiplierAP, dateAcct);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    // ========== invoiceDiscount Tests ==========

    Stream<Arguments> invoiceDiscountTestCases() {
        Timestamp payDate = new Timestamp(System.currentTimeMillis());
        return Stream.concat(
            paidInvoices.stream().map(i -> Arguments.of("paid_" + i.getC_Invoice_ID(), i, payDate)),
            openInvoices.stream().map(i -> Arguments.of("open_" + i.getC_Invoice_ID(), i, payDate))
        );
    }

    @ParameterizedTest(name = "invoiceDiscount({0})")
    @MethodSource("invoiceDiscountTestCases")
    void invoiceDiscount_allInvoices_matchSql(String caseName, MInvoice invoice, Timestamp payDate) {
        BigDecimal javaResult = InvoiceFunctions.invoiceDiscount(
            invoice.getC_Invoice_ID(), payDate, null);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceDiscount(
            invoice.getC_Invoice_ID(), payDate, null);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    Stream<Arguments> scheduledInvoiceDiscountTestCases() {
        Timestamp payDate = new Timestamp(System.currentTimeMillis());
        return scheduledInvoices.stream().flatMap(inv -> {
            MInvoicePaySchedule[] schedules = MInvoicePaySchedule.getInvoicePaySchedule(
                Env.getCtx(), inv.getC_Invoice_ID(), 0, null);
            return java.util.Arrays.stream(schedules)
                .map(s -> Arguments.of("sched_" + inv.getC_Invoice_ID(),
                    inv, s.getC_InvoicePaySchedule_ID(), payDate));
        });
    }

    @ParameterizedTest(name = "invoiceDiscount with schedule({0})")
    @MethodSource("scheduledInvoiceDiscountTestCases")
    void invoiceDiscount_withSchedule_matchSql(String caseName, MInvoice invoice,
                                                int scheduleId, Timestamp payDate) {
        BigDecimal javaResult = InvoiceFunctions.invoiceDiscount(
            invoice.getC_Invoice_ID(), payDate, scheduleId);
        BigDecimal sqlResult = SqlFunctionCaller.callInvoiceDiscount(
            invoice.getC_Invoice_ID(), payDate, scheduleId);

        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s schedule %d: java=%s, sql=%s",
                caseName, scheduleId, javaResult, sqlResult));
    }

    // ========== paymentAllocated Tests ==========

    Stream<Arguments> paymentAllocatedTestCases() {
        return Stream.concat(
            allocatedPayments.stream()
                .map(p -> Arguments.of("allocated_" + p.getC_Payment_ID(), p)),
            Stream.concat(
                unallocatedPayments.stream()
                    .map(p -> Arguments.of("unallocated_" + p.getC_Payment_ID(), p)),
                chargePayments.stream()
                    .map(p -> Arguments.of("charge_" + p.getC_Payment_ID(), p))
            )
        );
    }

    @ParameterizedTest(name = "paymentAllocated({0})")
    @MethodSource("paymentAllocatedTestCases")
    void paymentAllocated_allPayments_matchSql(String caseName, MPayment payment) {
        int paymentId = payment.getC_Payment_ID();
        int currencyId = payment.getC_Currency_ID();

        BigDecimal javaResult = payment.getAllocatedAmt();
        BigDecimal sqlResult = SqlFunctionCaller.callPaymentAllocated(paymentId, currencyId);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }

    // ========== paymentAvailable Tests ==========

    Stream<Arguments> paymentAvailableTestCases() {
        return Stream.concat(
            allocatedPayments.stream()
                .map(p -> Arguments.of("allocated_" + p.getC_Payment_ID(), p)),
            unallocatedPayments.stream()
                .map(p -> Arguments.of("unallocated_" + p.getC_Payment_ID(), p))
        );
    }

    @ParameterizedTest(name = "paymentAvailable({0})")
    @MethodSource("paymentAvailableTestCases")
    void paymentAvailable_allPayments_matchSql(String caseName, MPayment payment) {
        int paymentId = payment.getC_Payment_ID();

        BigDecimal javaResult = payment.getAvailableAmt();
        BigDecimal sqlResult = SqlFunctionCaller.callPaymentAvailable(paymentId);

        assertNotNull(javaResult, "Java returned null for " + caseName);
        assertNotNull(sqlResult, "SQL returned null for " + caseName);
        assertEquals(0, javaResult.compareTo(sqlResult),
            String.format("%s: java=%s, sql=%s", caseName, javaResult, sqlResult));
    }
}
