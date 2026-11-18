package org.compiere.util;

/**
 * Counts database queries for performance testing.
 * Thread-local to support concurrent test execution.
 *
 * Usage:
 *   QueryCounter.reset();
 *   // ... execute code that runs queries ...
 *   int count = QueryCounter.get();
 */
public class QueryCounter {

    private static final ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

    /** Reset counter to zero */
    public static void reset() {
        counter.set(0);
    }

    /** Get current count */
    public static int get() {
        return counter.get();
    }

    /** Increment counter (called by DB.prepareStatement) */
    public static void increment() {
        counter.set(counter.get() + 1);
    }
}
