// base/src/org/compiere/migration/CircuitBreaker.java
package org.compiere.migration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.compiere.util.CLogger;

/**
 * Per-function circuit breaker to prevent cascading failures during SQL outages.
 * Opens after FAILURE_THRESHOLD consecutive failures, resets after RESET_TIMEOUT_MS.
 * Logs state transitions for operational visibility.
 *
 * <p><b>Design Note:</b> This is a simplified two-state breaker (CLOSED/OPEN) without
 * a HALF_OPEN state. After timeout, the circuit closes and allows all requests through.
 * This is acceptable for Wave 0's low-volume functions (&lt;100 calls/day). For high-volume
 * functions, consider implementing the full pattern with HALF_OPEN state that allows
 * a single test request before fully closing.</p>
 *
 * <p><b>Memory Note:</b> The circuits map is bounded by the number of migrated functions
 * (currently 8 for Wave 0). In a future with dynamic function names, consider adding
 * periodic cleanup of stale closed circuits.</p>
 *
 * <p><b>Scope:</b> Circuit state is per-JVM. In clustered deployments, each instance
 * maintains independent state. This prevents a single failing node from tripping
 * circuits cluster-wide.</p>
 */
public class CircuitBreaker {
    private static final CLogger log = CLogger.getCLogger(CircuitBreaker.class);
    private static final int FAILURE_THRESHOLD = 5;
    private static final long RESET_TIMEOUT_MS = Long.getLong(
        "migration.circuit.reset.timeout.ms", 60_000); // 1 minute default

    private static final ConcurrentHashMap<String, CircuitState> circuits = new ConcurrentHashMap<>();

    /**
     * Check if circuit is open for the given function.
     * Uses compare-and-swap for thread-safe timeout reset.
     */
    public static boolean isOpen(String functionName) {
        CircuitState state = circuits.get(functionName);
        if (state == null) return false;

        long openedTime = state.openedAt.get();
        if (openedTime == 0) return false; // Not open

        if (System.currentTimeMillis() - openedTime > RESET_TIMEOUT_MS) {
            // Atomic reset - only one thread succeeds in logging
            if (state.openedAt.compareAndSet(openedTime, 0)) {
                state.failures.set(0);
                log.info("Circuit CLOSED for function: " + functionName + " (timeout elapsed)");
            }
            return false;
        }
        return true;
    }

    public static void recordSuccess(String functionName) {
        CircuitState state = circuits.get(functionName);
        if (state != null) {
            state.failures.set(0);
        }
    }

    public static void recordFailure(String functionName) {
        CircuitState state = circuits.computeIfAbsent(functionName, k -> new CircuitState());
        int failures = state.failures.incrementAndGet();
        if (failures >= FAILURE_THRESHOLD) {
            // compareAndSet ensures we only log once when opening
            if (state.openedAt.compareAndSet(0, System.currentTimeMillis())) {
                log.warning("Circuit OPENED for function: " + functionName +
                           " after " + failures + " consecutive failures");
            }
        }
    }

    /** Reset circuit state for a function (used in testing) */
    public static void reset(String functionName) {
        circuits.remove(functionName);
    }

    /** Reset all circuit state (used in testing) */
    public static void resetAll() {
        circuits.clear();
    }

    private static class CircuitState {
        final AtomicInteger failures = new AtomicInteger(0);
        final AtomicLong openedAt = new AtomicLong(0);
    }
}
