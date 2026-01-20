// base/test/src/org/compiere/util/SqlCompatTest.java
package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for SQL-compatible utility functions.
 */
@Tag("UnitTest")
public class SqlCompatTest {

    // round() tests
    @Test
    void testRoundPositiveScale() {
        BigDecimal value = new BigDecimal("123.456789");

        assertEquals(new BigDecimal("123.46"), SqlCompat.round(value, 2));
    }

    @Test
    void testRoundZeroScale() {
        BigDecimal value = new BigDecimal("123.456789");

        assertEquals(new BigDecimal("123"), SqlCompat.round(value, 0));
    }

    @Test
    void testRoundNegativeScale() {
        BigDecimal value = new BigDecimal("1234.56");

        // Rounds to nearest hundred
        assertEquals(new BigDecimal("1200"), SqlCompat.round(value, -2));
    }

    @Test
    void testRoundNullValue() {
        assertNull(SqlCompat.round(null, 2));
    }

    @Test
    void testRoundHalfUp() {
        // Verify HALF_UP rounding mode (standard SQL behavior)
        assertEquals(new BigDecimal("1.5"), SqlCompat.round(new BigDecimal("1.45"), 1));
        assertEquals(new BigDecimal("1.4"), SqlCompat.round(new BigDecimal("1.44"), 1));
        assertEquals(new BigDecimal("2"), SqlCompat.round(new BigDecimal("1.5"), 0));
    }

    // charAt() tests
    @Test
    void testCharAtFirstPosition() {
        assertEquals("H", SqlCompat.charAt("Hello", 1));
    }

    @Test
    void testCharAtMiddlePosition() {
        assertEquals("l", SqlCompat.charAt("Hello", 3));
    }

    @Test
    void testCharAtLastPosition() {
        assertEquals("o", SqlCompat.charAt("Hello", 5));
    }

    @Test
    void testCharAtOutOfBounds() {
        // SQL SUBSTR returns empty string for out of bounds
        assertEquals("", SqlCompat.charAt("Hello", 10));
    }

    @Test
    void testCharAtZeroPosition() {
        // SQL positions are 1-based, 0 is invalid
        assertEquals("", SqlCompat.charAt("Hello", 0));
    }

    @Test
    void testCharAtNegativePosition() {
        // SQL positions are 1-based, negative is invalid
        assertEquals("", SqlCompat.charAt("Hello", -1));
    }

    @Test
    void testCharAtNullString() {
        assertNull(SqlCompat.charAt(null, 1));
    }

    @Test
    void testCharAtEmptyString() {
        assertEquals("", SqlCompat.charAt("", 1));
    }

    @Test
    void testCharAtVeryLargePosition() {
        assertEquals("", SqlCompat.charAt("Hello", Integer.MAX_VALUE));
    }
}
