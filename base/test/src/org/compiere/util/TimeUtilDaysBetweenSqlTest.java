package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible daysBetween calculation.
 * SQL: CAST(p_date1 AS DATE) - CAST(p_date2 as DATE)
 */
@Tag("UnitTest")
public class TimeUtilDaysBetweenSqlTest {

    @Test
    void testSameDayDifferentTimes() {
        Timestamp date1 = Timestamp.valueOf("2026-01-15 23:59:59");
        Timestamp date2 = Timestamp.valueOf("2026-01-15 00:00:00");

        // SQL: CAST('2026-01-15' AS DATE) - CAST('2026-01-15' AS DATE) = 0
        assertEquals(0, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDate1AfterDate2() {
        Timestamp date1 = Timestamp.valueOf("2026-01-10 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-01 08:00:00");

        // SQL: 2026-01-10 - 2026-01-01 = 9
        assertEquals(9, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testDate1BeforeDate2() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 08:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-01-10 12:00:00");

        // SQL: 2026-01-01 - 2026-01-10 = -9
        assertEquals(-9, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testNullDate1ReturnsNull() {
        Timestamp date2 = Timestamp.valueOf("2026-01-01 00:00:00");
        assertNull(TimeUtil.daysBetweenSql(null, date2));
    }

    @Test
    void testNullDate2ReturnsNull() {
        Timestamp date1 = Timestamp.valueOf("2026-01-01 00:00:00");
        assertNull(TimeUtil.daysBetweenSql(date1, null));
    }

    @Test
    void testBothNullReturnsNull() {
        assertNull(TimeUtil.daysBetweenSql(null, null));
    }

    @Test
    void testCrossYearBoundary() {
        Timestamp date1 = Timestamp.valueOf("2027-01-01 00:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-12-31 00:00:00");

        assertEquals(1, TimeUtil.daysBetweenSql(date1, date2));
    }

    @Test
    void testCalendarDaysNotHourBased() {
        // Verifies we count calendar days, not 24-hour periods
        Timestamp date1 = Timestamp.valueOf("2026-03-09 12:00:00");
        Timestamp date2 = Timestamp.valueOf("2026-03-07 12:00:00");

        // Should be 2 calendar days
        assertEquals(2, TimeUtil.daysBetweenSql(date1, date2));
    }
}
