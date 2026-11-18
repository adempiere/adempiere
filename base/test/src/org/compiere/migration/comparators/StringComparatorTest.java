package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
class StringComparatorTest {

    @Test
    void nullsafe_bothNull_returnsTrue() {
        assertTrue(StringComparator.NULLSAFE.test(null, null));
    }

    @Test
    void nullsafe_oneNull_returnsFalse() {
        assertFalse(StringComparator.NULLSAFE.test("a", null));
        assertFalse(StringComparator.NULLSAFE.test(null, "a"));
    }

    @Test
    void nullsafe_equal_returnsTrue() {
        assertTrue(StringComparator.NULLSAFE.test("hello", "hello"));
    }

    @Test
    void nullsafe_notEqual_returnsFalse() {
        assertFalse(StringComparator.NULLSAFE.test("hello", "world"));
    }

    @Test
    void trimNullsafe_withWhitespace_returnsTrue() {
        assertTrue(StringComparator.TRIM_NULLSAFE.test("hello ", " hello"));
        assertTrue(StringComparator.TRIM_NULLSAFE.test("  hello  ", "hello"));
    }

    @Test
    void trimNullsafe_differentAfterTrim_returnsFalse() {
        assertFalse(StringComparator.TRIM_NULLSAFE.test("hello", "world"));
    }
}
