// base/test/src/org/compiere/migration/SqlFunctionCallerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("IntegrationTest")
public class SqlFunctionCallerTest extends CommonGWSetup {

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
}
