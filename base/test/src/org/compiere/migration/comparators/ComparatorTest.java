// base/test/src/org/compiere/migration/comparators/ComparatorTest.java
package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class ComparatorTest {

    @Test
    void testTimestampWithinTolerance() {
        TimestampComparator comp = TimestampComparator.withDefaultTolerance();
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1000500); // 500ms difference

        assertTrue(comp.test(t1, t2));
    }

    @Test
    void testTimestampOutsideTolerance() {
        TimestampComparator comp = TimestampComparator.withDefaultTolerance();
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1002000); // 2000ms difference

        assertFalse(comp.test(t1, t2));
    }

    @Test
    void testTimestampExactMatch() {
        TimestampComparator comp = TimestampComparator.exact();
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1000000);

        assertTrue(comp.test(t1, t2));
    }

    @Test
    void testTimestampNullHandling() {
        TimestampComparator comp = TimestampComparator.withDefaultTolerance();

        assertTrue(comp.test(null, null));
        assertFalse(comp.test(null, new Timestamp(1000)));
        assertFalse(comp.test(new Timestamp(1000), null));
    }

    @Test
    void testDateComparator() {
        DateComparator comp = DateComparator.INSTANCE;

        Date d1 = Date.valueOf("2026-01-15");
        Date d2 = Date.valueOf("2026-01-15");
        Date d3 = Date.valueOf("2026-01-16");

        assertTrue(comp.test(d1, d2));
        assertFalse(comp.test(d1, d3));
    }

    @Test
    void testDateNullHandling() {
        DateComparator comp = DateComparator.INSTANCE;

        assertTrue(comp.test(null, null));
        assertFalse(comp.test(null, Date.valueOf("2026-01-15")));
        assertFalse(comp.test(Date.valueOf("2026-01-15"), null));
    }
}
