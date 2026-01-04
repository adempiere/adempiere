// base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoicePaySchedule;
import org.compiere.model.MPayment;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SqlFunctionCallerTest extends CommonGWSetup {

    private MCurrency usd;
    private int testInvoiceId;
    private int testInvoiceScheduleId;
    private int testCurrencyId;
    private int testPaymentId;
    private int testPaymentCurrencyId;

    @BeforeAll
    void loadTestData() {
        usd = MCurrency.get(Env.getCtx(), "USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required");
    }

    @BeforeAll
    void findTestData() {
        // Find a completed invoice dynamically
        testInvoiceId = new Query(Env.getCtx(), "C_Invoice", "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true).firstId();
        assumeTrue(testInvoiceId > 0, "Need completed invoice for test");

        // Get currency from invoice
        MInvoice inv = new MInvoice(Env.getCtx(), testInvoiceId, null);
        testCurrencyId = inv.getC_Currency_ID();

        // Try to find invoice with payment schedule
        int invWithSched = new Query(Env.getCtx(), "C_Invoice",
            "DocStatus IN ('CO','CL') AND IsPayScheduleValid='Y'", null)
            .setOnlyActiveRecords(true).firstId();
        if (invWithSched > 0) {
            MInvoicePaySchedule[] scheds = MInvoicePaySchedule.getInvoicePaySchedule(
                Env.getCtx(), invWithSched, 0, null);
            if (scheds.length > 0) {
                testInvoiceScheduleId = scheds[0].getC_InvoicePaySchedule_ID();
            }
        }

        // Find a completed payment dynamically
        testPaymentId = new Query(Env.getCtx(), "C_Payment", "DocStatus IN ('CO','CL')", null)
            .setOnlyActiveRecords(true).firstId();
        assumeTrue(testPaymentId > 0, "Need completed payment for test");

        // Get currency from payment
        MPayment pmt = new MPayment(Env.getCtx(), testPaymentId, null);
        testPaymentCurrencyId = pmt.getC_Currency_ID();
    }

    @Test
    void testCallGetDate() {
        Timestamp result = SqlFunctionCaller.callGetDate();
        assertNotNull(result);
        // Should be within last second
        long diff = System.currentTimeMillis() - result.getTime();
        assertTrue(Math.abs(diff) < 2000, "getDate() should return current time");
    }

    @Test
    void testCallDaysBetween() {
        Timestamp date1 = Timestamp.valueOf("2026-01-10 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        Integer result = SqlFunctionCaller.callDaysBetween(date1, date2);
        assertEquals(9, result);
    }

    @Test
    void testCallDaysBetweenReverse() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-10 12:00:00");

        Integer result = SqlFunctionCaller.callDaysBetween(date1, date2);
        assertEquals(-9, result);
    }

    @Test
    void testCallDaysBetweenNullInput() {
        assertNull(SqlFunctionCaller.callDaysBetween(null, Timestamp.valueOf("2026-01-01 00:00:00")));
        assertNull(SqlFunctionCaller.callDaysBetween(Timestamp.valueOf("2026-01-01 00:00:00"), null));
    }

    @Test
    void testCallAddDays() {
        Timestamp base = Timestamp.valueOf("2026-01-01 00:00:00");
        Date result = SqlFunctionCaller.callAddDays(base, new BigDecimal("5"));
        assertEquals(Date.valueOf("2026-01-06"), result);
    }

    @Test
    void testCallAddDaysNullInput() {
        assertNull(SqlFunctionCaller.callAddDays(null, new BigDecimal("5")));
    }

    @Test
    void testCallSubtractDays() {
        Timestamp base = Timestamp.valueOf("2026-01-10 00:00:00");
        Date result = SqlFunctionCaller.callSubtractDays(base, new BigDecimal("5"));
        assertEquals(Date.valueOf("2026-01-05"), result);
    }

    @Test
    void testCallSubtractDaysNullInput() {
        assertNull(SqlFunctionCaller.callSubtractDays(null, new BigDecimal("5")));
    }

    @Test
    void testCallTruncTimestamp() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:45");
        Timestamp result = SqlFunctionCaller.callTrunc(ts);
        assertEquals(Timestamp.valueOf("2026-01-15 00:00:00"), result);
    }

    @Test
    void testCallTruncWithFormat() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:45");
        Date result = SqlFunctionCaller.callTrunc(ts, "MM");
        assertEquals(Date.valueOf("2026-01-01"), result);
    }

    @Test
    void testCallTruncNullInput() {
        assertNull(SqlFunctionCaller.callTrunc(null));
        assertNull(SqlFunctionCaller.callTrunc(null, "MM"));
    }

    @Test
    void testCallRound() {
        BigDecimal value = new BigDecimal("123.456");
        BigDecimal result = SqlFunctionCaller.callRound(value, 2);
        assertEquals(new BigDecimal("123.46"), result);
    }

    @Test
    void testCallRoundNullInput() {
        assertNull(SqlFunctionCaller.callRound(null, 2));
    }

    @Test
    void testCallFirstOf() {
        Timestamp ts = Timestamp.valueOf("2026-01-15 14:30:45");
        Date result = SqlFunctionCaller.callFirstOf(ts, "MM");
        assertEquals(Date.valueOf("2026-01-01"), result);
    }

    @Test
    void testCallFirstOfYear() {
        Timestamp ts = Timestamp.valueOf("2026-03-15 14:30:45");
        Date result = SqlFunctionCaller.callFirstOf(ts, "YY");
        assertEquals(Date.valueOf("2026-01-01"), result);
    }

    @Test
    void testCallFirstOfNullInput() {
        assertNull(SqlFunctionCaller.callFirstOf(null, "MM"));
    }

    @Test
    void testCallCharAt() {
        String result = SqlFunctionCaller.callCharAt("Hello", 1);
        assertEquals("H", result);
    }

    @Test
    void testCallCharAtMiddle() {
        String result = SqlFunctionCaller.callCharAt("Hello", 3);
        assertEquals("l", result);
    }

    @Test
    void testCallCharAtNullInput() {
        assertNull(SqlFunctionCaller.callCharAt(null, 1));
    }

    @Test
    void callCurrencyRound_validInput_returnsResult() {
        BigDecimal amount = new BigDecimal("123.456789");
        BigDecimal result = SqlFunctionCaller.callCurrencyRound(amount, usd.get_ID(), "N");

        assertNotNull(result);
        BigDecimal expected = amount.setScale(usd.getStdPrecision(), RoundingMode.HALF_UP);
        assertEquals(0, expected.compareTo(result));
    }

    @Test
    void callCurrencyRound_nullAmount_returnsNull() {
        BigDecimal result = SqlFunctionCaller.callCurrencyRound(null, 100, "N");
        assertNull(result);
    }

    @Test
    void callCurrencyRate_sameCurrency_returnsOne() {
        MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required");

        BigDecimal result = SqlFunctionCaller.callCurrencyRate(
            usd.get_ID(), usd.get_ID(), null, null, 11, 0);

        assertNotNull(result);
        assertEquals(0, BigDecimal.ONE.compareTo(result));
    }

    @Test
    void callCurrencyRate_usdToEur_noException() {
        MCurrency usd = MCurrency.get(Env.getCtx(), "USD");
        MCurrency eur = MCurrency.get(Env.getCtx(), "EUR");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency required");
        assumeTrue(eur != null && eur.get_ID() > 0, "EUR currency required");

        // This test validates the SQL function is callable
        // Rate may be null if not configured in test DB - that's OK
        assertDoesNotThrow(() -> {
            SqlFunctionCaller.callCurrencyRate(
                usd.get_ID(), eur.get_ID(),
                Timestamp.valueOf("2024-01-01 00:00:00"),
                null, 11, 0);
        });
    }

    @Test
    void callNextBusinessDay_withNullDate_returnsNull() {
        assertNull(SqlFunctionCaller.callNextBusinessDay(null, 0));
    }

    @Test
    void callPaymentTermDueDate_withNullInputs_returnsNull() {
        assertNull(SqlFunctionCaller.callPaymentTermDueDate(null, null));
    }

    @Test
    void callPaymentTermDueDays_withZeroPaymentTermId_returnsZero() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        assertEquals(0, SqlFunctionCaller.callPaymentTermDueDays(0, docDate, null));
    }

    @Test
    void callPaymentTermDiscount_withNullAmount_returnsZero() {
        Timestamp docDate = Timestamp.valueOf("2026-01-15 00:00:00");
        assertEquals(BigDecimal.ZERO, SqlFunctionCaller.callPaymentTermDiscount(
            null, 100, 106, docDate, docDate));
    }

    @Test
    void callInvoiceOpen_returnsNumeric() {
        assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceOpen(testInvoiceId, null));
    }

    @Test
    void callInvoiceOpen_withSchedule_returnsNumeric() {
        assumeTrue(testInvoiceScheduleId > 0, "Need invoice with schedule");
        assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceOpen(testInvoiceId, testInvoiceScheduleId));
    }

    @Test
    void callInvoicePaid_returnsNumeric() {
        BigDecimal result = SqlFunctionCaller.callInvoicePaid(testInvoiceId, testCurrencyId, BigDecimal.ONE);
        assertNotNull(result);
    }

    @Test
    void callInvoiceDiscount_returnsNumeric() {
        Timestamp payDate = new Timestamp(System.currentTimeMillis());
        assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceDiscount(testInvoiceId, payDate, null));
    }

    @Test
    void callInvoiceOpenToDate_returnsNumeric() {
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());
        assertDoesNotThrow(() -> SqlFunctionCaller.callInvoiceOpenToDate(testInvoiceId, null, dateAcct));
    }

    @Test
    void callInvoicePaidToDate_returnsNumeric() {
        Timestamp dateAcct = new Timestamp(System.currentTimeMillis());
        BigDecimal result = SqlFunctionCaller.callInvoicePaidToDate(
            testInvoiceId, testCurrencyId, BigDecimal.ONE, dateAcct);
        assertNotNull(result);
    }

    @Test
    void callPaymentAllocated_returnsNumeric() {
        BigDecimal result = SqlFunctionCaller.callPaymentAllocated(testPaymentId, testPaymentCurrencyId);
        assertNotNull(result);
    }

    @Test
    void callPaymentAvailable_returnsNumeric() {
        BigDecimal result = SqlFunctionCaller.callPaymentAvailable(testPaymentId);
        assertNotNull(result);
    }
}
