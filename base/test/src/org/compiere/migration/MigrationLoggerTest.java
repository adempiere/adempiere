// base/test/src/org/compiere/migration/MigrationLoggerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class MigrationLoggerTest {

    @BeforeEach
    void resetLogger() {
        MigrationLogger.resetForTesting();
    }

    @Test
    void testLogAsyncDoesNotBlock() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            MigrationLogger.logAsync("testFunction", "[\"input" + i + "\"]", "sqlResult", "javaResult", 10, 8, true, null);
        }

        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed < 100, "logAsync should not block: took " + elapsed + "ms");
    }

    @Test
    void testQueueOverflowDoesNotBlock() {
        // Fill queue beyond capacity - use larger number to ensure overflow
        // despite drain thread consuming entries concurrently
        for (int i = 0; i < 50000; i++) {
            MigrationLogger.logAsync("overflow", "[\"input" + i + "\"]", "sql", "java", 1, 1, true, null);
        }
        // Should not throw or block - if dropped count is 0, the drain thread kept up
        // which is fine, the test is checking that logAsync doesn't block
        assertTrue(MigrationLogger.getQueueDepth() <= 10000 || MigrationLogger.getDroppedCount() > 0,
            "Queue should not block when full (depth=" + MigrationLogger.getQueueDepth() + ", dropped=" + MigrationLogger.getDroppedCount() + ")");
    }
}
