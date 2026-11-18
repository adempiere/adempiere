package org.compiere.migration.comparators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
class IntegerComparatorTest {

    @Test
    void exact_withEqualValues_returnsTrue() {
        assertTrue(IntegerComparator.EXACT.test(42, 42));
    }

    @Test
    void exact_withDifferentValues_returnsFalse() {
        assertFalse(IntegerComparator.EXACT.test(42, 43));
    }

    @Test
    void exact_withBothNull_returnsTrue() {
        assertTrue(IntegerComparator.EXACT.test(null, null));
    }

    @Test
    void exact_withOneNull_returnsFalse() {
        assertFalse(IntegerComparator.EXACT.test(null, 42));
        assertFalse(IntegerComparator.EXACT.test(42, null));
    }
}
