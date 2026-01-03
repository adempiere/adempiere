package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.adempiere.test.CommonGWSetup;
import org.compiere.migration.comparators.DateComparator;
import org.compiere.migration.comparators.TimestampComparator;
import org.compiere.util.SqlCompat;
import org.compiere.util.TimeUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Integration tests comparing Java implementations to SQL functions.
 * These tests validate that Java matches SQL exactly.
 */
@Tag("IntegrationTest")
public class Wave0ShadowIntegrationTest extends CommonGWSetup {

    @Test
    void testGetDateMatchesSql() {
        // Execute both as close together as possible to minimize drift
        long beforeJava = System.currentTimeMillis();
        Timestamp javaResult = TimeUtil.getDate();
        Timestamp sqlResult = SqlFunctionCaller.callGetDate();
        long afterSql = System.currentTimeMillis();

        // Use actual elapsed time as tolerance, plus 500ms buffer
        long maxDrift = (afterSql - beforeJava) + 500;
        long actualDrift = Math.abs(javaResult.getTime() - sqlResult.getTime());

        assertTrue(actualDrift <= maxDrift,
            String.format("getDate() drift %dms exceeded max %dms: java=%s, sql=%s",
                actualDrift, maxDrift, javaResult, sqlResult));
    }

    @Test
    void testDaysBetweenMatchesSql() {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 14:30:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        Integer javaResult = TimeUtil.daysBetweenSql(date1, date2);
        Integer sqlResult = SqlFunctionCaller.callDaysBetween(date1, date2);

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void testDaysBetweenNegativeMatchesSql() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-15 14:30:00");

        Integer javaResult = TimeUtil.daysBetweenSql(date1, date2);
        Integer sqlResult = SqlFunctionCaller.callDaysBetween(date1, date2);

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void testAddDaysMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        Date javaResult = TimeUtil.addDaysSql(datetime, days);
        Date sqlResult = SqlFunctionCaller.callAddDays(datetime, days);

        assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult));
    }

    @Test
    void testSubtractDaysMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("10");

        Date javaResult = TimeUtil.subtractDaysSql(datetime, days);
        Date sqlResult = SqlFunctionCaller.callSubtractDays(datetime, days);

        assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult));
    }

    @Test
    void testTruncMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        // Test various format codes
        for (String format : new String[]{"Q", "Y", "YEAR", "MM", "MONTH", "DD"}) {
            Date javaResult = TimeUtil.truncSql(datetime, format);
            Date sqlResult = SqlFunctionCaller.callTrunc(datetime, format);

            assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult),
                "trunc(" + datetime + ", " + format + ") mismatch: java=" + javaResult + ", sql=" + sqlResult);
        }
    }

    @Test
    void testRoundMatchesSql() {
        BigDecimal value = new BigDecimal("123.456789");

        for (int scale = -2; scale <= 4; scale++) {
            BigDecimal javaResult = SqlCompat.round(value, scale);
            BigDecimal sqlResult = SqlFunctionCaller.callRound(value, scale);

            assertEquals(0, sqlResult.compareTo(javaResult),
                "round(" + value + ", " + scale + "): java=" + javaResult + ", sql=" + sqlResult);
        }
    }

    @Test
    void testFirstOfMatchesSql() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        for (String format : new String[]{"YYYY", "Q", "MM", "DD"}) {
            Date javaResult = TimeUtil.firstOf(datetime, format);
            Date sqlResult = SqlFunctionCaller.callFirstOf(datetime, format);

            assertTrue(DateComparator.INSTANCE.test(javaResult, sqlResult),
                "firstOf(" + datetime + ", " + format + ") mismatch: java=" + javaResult + ", sql=" + sqlResult);
        }
    }

    @Test
    void testCharAtMatchesSql() {
        String str = "Hello World";

        for (int pos = 1; pos <= str.length() + 2; pos++) {
            String javaResult = SqlCompat.charAt(str, pos);
            String sqlResult = SqlFunctionCaller.callCharAt(str, pos);

            assertEquals(sqlResult, javaResult, "charAt(" + str + ", " + pos + ") mismatch");
        }
    }
}
