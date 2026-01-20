package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.sql.Timestamp;

@Tag("UnitTest")
class TimestampComparatorTest {

    @Test
    void sameDay_withSameDayDifferentTime_returnsTrue() {
        Timestamp t1 = Timestamp.valueOf("2026-01-15 10:00:00");
        Timestamp t2 = Timestamp.valueOf("2026-01-15 23:59:59");
        assertTrue(TimestampComparator.SAME_DAY.test(t1, t2));
    }

    @Test
    void sameDay_withDifferentDays_returnsFalse() {
        Timestamp t1 = Timestamp.valueOf("2026-01-15 10:00:00");
        Timestamp t2 = Timestamp.valueOf("2026-01-16 10:00:00");
        assertFalse(TimestampComparator.SAME_DAY.test(t1, t2));
    }

    @Test
    void sameDay_withBothNull_returnsTrue() {
        assertTrue(TimestampComparator.SAME_DAY.test(null, null));
    }

    @Test
    void sameDay_withOneNull_returnsFalse() {
        Timestamp t1 = Timestamp.valueOf("2026-01-15 10:00:00");
        assertFalse(TimestampComparator.SAME_DAY.test(t1, null));
        assertFalse(TimestampComparator.SAME_DAY.test(null, t1));
    }

    @Test
    void withinSecond_withExactlyOneSecondDiff_returnsTrue() {
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1001000); // exactly 1000ms
        assertTrue(TimestampComparator.WITHIN_SECOND.test(t1, t2));
    }

    @Test
    void withinSecond_withJustOverOneSecond_returnsFalse() {
        Timestamp t1 = new Timestamp(1000000);
        Timestamp t2 = new Timestamp(1001001); // 1001ms
        assertFalse(TimestampComparator.WITHIN_SECOND.test(t1, t2));
    }

    @Test
    void withinSecond_withBothNull_returnsTrue() {
        assertTrue(TimestampComparator.WITHIN_SECOND.test(null, null));
    }

    @Test
    void withinSecond_withOneNull_returnsFalse() {
        Timestamp t1 = new Timestamp(1000000);
        assertFalse(TimestampComparator.WITHIN_SECOND.test(t1, null));
        assertFalse(TimestampComparator.WITHIN_SECOND.test(null, t1));
    }
}
