package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible addDays/subtractDays.
 * SQL: cast(date_trunc('day',datetime) + cast(days || ' day' as interval) as date)
 */
@Tag("UnitTest")
public class TimeUtilAddDaysSqlTest {

    @Test
    void testAddDaysPositive() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-20"), result);
    }

    @Test
    void testAddDaysNegative() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("-5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-10"), result);
    }

    @Test
    void testAddDaysZero() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = BigDecimal.ZERO;

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-15"), result);
    }

    @Test
    void testAddDaysNullDatetime() {
        assertNull(TimeUtil.addDaysSql(null, new BigDecimal("5")));
    }

    @Test
    void testAddDaysNullDays() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        assertNull(TimeUtil.addDaysSql(datetime, null));
    }

    @Test
    void testSubtractDays() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.subtractDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-10"), result);
    }

    @Test
    void testAddDaysCrossMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-01-30 00:00:00");
        BigDecimal days = new BigDecimal("5");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-02-04"), result);
    }

    @Test
    void testAddDaysFractionalThrows() {
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("1.5");

        assertThrows(IllegalArgumentException.class,
            () -> TimeUtil.addDaysSql(datetime, days));
    }

    @Test
    void testAddDaysWholeNumberWithScaleWorks() {
        // 5.00 should work (trailing zeros)
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:00");
        BigDecimal days = new BigDecimal("5.00");

        Date result = TimeUtil.addDaysSql(datetime, days);
        assertEquals(Date.valueOf("2026-01-20"), result);
    }

    @Test
    void testAddDaysLeapYear() {
        // 2024 is a leap year
        Timestamp datetime = Timestamp.valueOf("2024-02-28 12:00:00");
        Date result = TimeUtil.addDaysSql(datetime, new BigDecimal("1"));
        assertEquals(Date.valueOf("2024-02-29"), result);
    }

    @Test
    void testAddDaysLargeOffset() {
        // Verify no overflow with large day values (~100 years)
        Timestamp datetime = Timestamp.valueOf("2026-01-01 00:00:00");
        Date result = TimeUtil.addDaysSql(datetime, new BigDecimal("36500"));
        assertNotNull(result);
    }
}
