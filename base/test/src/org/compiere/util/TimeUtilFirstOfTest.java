// base/test/src/org/compiere/util/TimeUtilFirstOfTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible firstOf() with Oracle-compatible format codes.
 */
@Tag("UnitTest")
public class TimeUtilFirstOfTest {

    @Test
    void testFirstOfYear() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YYYY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YEAR"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YYY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "YY"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "Y"));
    }

    @Test
    void testFirstOfQuarter() {
        // Q1: Jan-Mar
        assertEquals(Date.valueOf("2026-01-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-02-15 00:00:00"), "Q"));
        // Q2: Apr-Jun
        assertEquals(Date.valueOf("2026-04-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-05-15 00:00:00"), "Q"));
        // Q3: Jul-Sep
        assertEquals(Date.valueOf("2026-07-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-08-15 00:00:00"), "Q"));
        // Q4: Oct-Dec
        assertEquals(Date.valueOf("2026-10-01"),
            TimeUtil.firstOf(Timestamp.valueOf("2026-11-15 00:00:00"), "Q"));
    }

    @Test
    void testFirstOfMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MONTH"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MON"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "MM"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "RM"));
    }

    @Test
    void testFirstOfWeek() {
        // 2026-01-15 is a Thursday. ISO week starts Monday = 2026-01-12
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:45");

        // IW uses ISO week (Monday start)
        assertEquals(Date.valueOf("2026-01-12"), TimeUtil.firstOf(datetime, "IW"));
        // W also uses week
        assertEquals(Date.valueOf("2026-01-12"), TimeUtil.firstOf(datetime, "W"));
    }

    @Test
    void testFirstOfDay() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "DDD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "DD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "J"));
    }

    @Test
    void testFirstOfDayOfWeek() {
        // DAY/DY/D: week start with -1 offset for Oracle compatibility
        // 2026-01-15 is Thursday, ISO Monday = 2026-01-12, -1 = 2026-01-11 (Sun)
        Timestamp datetime = Timestamp.valueOf("2026-01-15 14:30:45");

        Date result = TimeUtil.firstOf(datetime, "DAY");
        assertEquals(Date.valueOf("2026-01-11"), result);
    }

    @Test
    void testFirstOfHour() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        // Hour truncation returns date at that hour (but we return Date, so just the date)
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH12"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "HH24"));
    }

    @Test
    void testFirstOfMinute() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, "MI"));
    }

    @Test
    void testFirstOfNull() {
        assertNull(TimeUtil.firstOf(null, "YYYY"));
    }

    @Test
    void testFirstOfNullFormat() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        // Null format should return the date as-is
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.firstOf(datetime, null));
    }

    @Test
    void testFirstOfCaseInsensitive() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        // Format codes should be case-insensitive
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.firstOf(datetime, "yyyy"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.firstOf(datetime, "month"));
        assertEquals(Date.valueOf("2026-04-01"), TimeUtil.firstOf(datetime, "q"));
    }
}
