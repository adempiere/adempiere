// base/test/src/org/compiere/util/TimeUtilTruncSqlTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible trunc() with format codes.
 */
@Tag("UnitTest")
public class TimeUtilTruncSqlTest {

    @Test
    void testTruncToDay() {
        Timestamp datetime = Timestamp.valueOf("2026-03-15 14:30:45");
        Date result = TimeUtil.truncSql(datetime);

        // Should truncate to date
        assertEquals(Date.valueOf("2026-03-15"), result);
    }

    @Test
    void testTruncToQuarter() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");
        Date result = TimeUtil.truncSql(datetime, "Q");

        // Q2 starts April 1
        assertEquals(Date.valueOf("2026-04-01"), result);
    }

    @Test
    void testTruncToYear() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.truncSql(datetime, "Y"));
        assertEquals(Date.valueOf("2026-01-01"), TimeUtil.truncSql(datetime, "YEAR"));
    }

    @Test
    void testTruncToMonth() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.truncSql(datetime, "MM"));
        assertEquals(Date.valueOf("2026-05-01"), TimeUtil.truncSql(datetime, "MONTH"));
    }

    @Test
    void testTruncToDayFormat() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.truncSql(datetime, "DD"));
        assertEquals(Date.valueOf("2026-05-15"), TimeUtil.truncSql(datetime, "DY"));
    }

    @Test
    void testTruncUnknownFormatThrows() {
        Timestamp datetime = Timestamp.valueOf("2026-05-15 14:30:45");

        assertThrows(IllegalArgumentException.class,
            () -> TimeUtil.truncSql(datetime, "UNKNOWN"));
    }

    @Test
    void testTruncNullReturnsNull() {
        assertNull(TimeUtil.truncSql(null));
        assertNull(TimeUtil.truncSql(null, "Q"));
    }
}
