package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
public class NextIDConcurrencyTest {

    private static final int THREAD_COUNT = 100;
    private static final int TEST_SEQUENCE_ID = 999999; // Use a test sequence

    @BeforeEach
    void setUp() {
        // Create test sequence if not exists
        // DB.executeUpdate("INSERT INTO AD_Sequence ...", null);
    }

    @Test
    void nextID_noDuplicatesUnderConcurrency() throws InterruptedException {
        Set<Integer> generatedIds = Collections.synchronizedSet(new HashSet<>());
        AtomicInteger duplicateCount = new AtomicInteger(0);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(THREAD_COUNT);

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                try {
                    startLatch.await(); // Wait for all threads to be ready
                    int id = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);
                    if (id > 0) {
                        if (!generatedIds.add(id)) {
                            duplicateCount.incrementAndGet();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        // Start all threads simultaneously
        startLatch.countDown();

        // Wait for completion
        boolean completed = doneLatch.await(60, TimeUnit.SECONDS);
        executor.shutdown();

        assertTrue(completed, "All threads should complete within 60 seconds");
        assertEquals(0, duplicateCount.get(), "No duplicate IDs should be generated");
        assertEquals(THREAD_COUNT, generatedIds.size(), "Should generate " + THREAD_COUNT + " unique IDs");
    }

    @Test
    void nextID_incrementsCorrectly() {
        int first = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);
        int second = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);

        assertTrue(first > 0, "First ID should be positive");
        assertTrue(second > first, "Second ID should be greater than first");
    }

    @Test
    void nextID_invalidSequence_returnsNegative() {
        int result = Wave4Functions.nextID(-1, "N", null);
        assertEquals(-1, result, "Invalid sequence should return -1");
    }

    @Test
    void nextID_systemVsRegular() {
        int regular = Wave4Functions.nextID(TEST_SEQUENCE_ID, "N", null);
        int system = Wave4Functions.nextID(TEST_SEQUENCE_ID, "Y", null);

        assertTrue(regular > 0, "Regular ID should be positive");
        assertTrue(system > 0, "System ID should be positive");
        // System and regular sequences are independent
    }
}
