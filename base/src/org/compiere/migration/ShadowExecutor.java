// base/src/org/compiere/migration/ShadowExecutor.java
package org.compiere.migration;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.compiere.util.CLogger;

/**
 * Orchestrates shadow execution for function migration.
 * Executes both Java and SQL implementations, compares results, logs mismatches.
 * Handles exceptions with proper circuit breaker integration.
 */
public class ShadowExecutor {
    private static final CLogger log = CLogger.getCLogger(ShadowExecutor.class);

    /**
     * Execute with automatic config lookup.
     */
    public static <T> T execute(String functionName,
                                 @Nullable Object[] params,
                                 Supplier<T> javaPath,
                                 Supplier<T> sqlPath,
                                 BiPredicate<T, T> comparator) {
        MigrationConfig config = MigrationConfig.get(functionName);
        return execute(functionName, params, config.getMode(), config.getSampleRate(),
                       config.isCircuitBreakerEnabled(), javaPath, sqlPath, comparator);
    }

    /**
     * Execute with explicit mode and sample rate (for testing).
     */
    public static <T> T execute(String functionName,
                                 @Nullable Object[] params,
                                 MigrationMode mode,
                                 double sampleRate,
                                 boolean circuitBreakerEnabled,
                                 Supplier<T> javaPath,
                                 Supplier<T> sqlPath,
                                 BiPredicate<T, T> comparator) {

        String serializedParams = ParamSerializer.toJson(params);

        if (mode == MigrationMode.SQL_ONLY) {
            return sqlPath.get();
        }

        // Execute Java
        long javaStart = System.nanoTime();
        T javaResult = javaPath.get();
        long javaTimeMs = (System.nanoTime() - javaStart) / 1_000_000;

        if (mode == MigrationMode.JAVA_ONLY) {
            return javaResult;
        }

        // SHADOW mode: check circuit breaker first
        if (circuitBreakerEnabled && CircuitBreaker.isOpen(functionName)) {
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      0, javaTimeMs, false, "CIRCUIT_OPEN");
            return javaResult;
        }

        // SHADOW mode: apply sampling
        if (!shouldSample(sampleRate)) {
            return javaResult;
        }

        // Execute SQL with proper exception handling
        long sqlStart = System.nanoTime();
        T sqlResult = null;
        long sqlTimeMs = 0;

        try {
            sqlResult = sqlPath.get();
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            if (circuitBreakerEnabled) {
                CircuitBreaker.recordSuccess(functionName);
            }
        } catch (SqlFunctionException e) {
            // Database/SQL issue - record failure for circuit breaker
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            if (circuitBreakerEnabled) {
                CircuitBreaker.recordFailure(functionName);
            }
            log.log(Level.WARNING, "Shadow SQL execution failed for " + functionName, e);
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      sqlTimeMs, javaTimeMs, false, "SQL_EXCEPTION: " + e.getMessage());
            return javaResult;
        } catch (RuntimeException e) {
            // Programming error - log but don't trip circuit breaker
            sqlTimeMs = (System.nanoTime() - sqlStart) / 1_000_000;
            log.log(Level.SEVERE, "Unexpected error in shadow execution for " + functionName, e);
            MigrationLogger.logAsync(functionName, serializedParams, "", String.valueOf(javaResult),
                                      sqlTimeMs, javaTimeMs, false, "UNEXPECTED_ERROR: " + e.getClass().getName());
            return javaResult;
        }

        // Compare
        boolean match = false;
        String mismatchReason = null;
        try {
            match = comparator.test(javaResult, sqlResult);
            if (!match) {
                mismatchReason = "VALUE_MISMATCH";
            }
        } catch (Exception e) {
            mismatchReason = "COMPARATOR_EXCEPTION: " + e.getMessage();
        }

        // Log async
        MigrationLogger.logAsync(functionName,
                                  serializedParams,
                                  String.valueOf(sqlResult),
                                  String.valueOf(javaResult),
                                  sqlTimeMs,
                                  javaTimeMs,
                                  match,
                                  mismatchReason);

        return javaResult;
    }

    private static boolean shouldSample(double sampleRate) {
        if (sampleRate >= 1.0) {
            return true;
        }
        if (sampleRate <= 0.0) {
            return false;
        }
        return ThreadLocalRandom.current().nextDouble() < sampleRate;
    }
}
