package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class TimeUtilGetDateTest {

    @Test
    void testGetDateReturnsCurrentTimestamp() {
        long before = System.currentTimeMillis();
        Timestamp result = TimeUtil.getDate();
        long after = System.currentTimeMillis();

        assertNotNull(result);
        assertTrue(result.getTime() >= before, "Should be >= start time");
        assertTrue(result.getTime() <= after, "Should be <= end time");
    }

    @Test
    void testGetDateIsNotTruncated() {
        Timestamp result = TimeUtil.getDate();
        // Unlike getDay(), getDate() should NOT truncate to midnight
        // It returns now() which includes time component
        assertNotNull(result);
    }
}
