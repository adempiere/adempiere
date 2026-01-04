package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Tests for QueryCounter utility class.
 */
@Tag("UnitTest")
public class QueryCounterTest {

    @Test
    void queryCounter_incrementsOnPrepareStatement() {
        QueryCounter.reset();
        assertEquals(0, QueryCounter.get(), "Counter should start at 0");

        try {
            DB.prepareStatement("SELECT 1", null);
        } catch (Exception e) {
            // Expected - we may not have a connection, but the counter should still increment
        }

        assertEquals(1, QueryCounter.get(), "Counter should increment after prepareStatement");
    }

    @Test
    void queryCounter_resetsCorrectly() {
        QueryCounter.reset();
        assertEquals(0, QueryCounter.get());

        try {
            DB.prepareStatement("SELECT 1", null);
            DB.prepareStatement("SELECT 2", null);
        } catch (Exception e) {
            // Expected - we may not have a connection
        }

        assertEquals(2, QueryCounter.get(), "Counter should be 2 after two statements");

        QueryCounter.reset();
        assertEquals(0, QueryCounter.get(), "Counter should be 0 after reset");
    }
}
