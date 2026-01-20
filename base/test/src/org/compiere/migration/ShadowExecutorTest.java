// base/test/src/org/compiere/migration/ShadowExecutorTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class ShadowExecutorTest {

    @BeforeEach
    void reset() {
        CircuitBreaker.resetAll();
    }

    @Test
    void testSqlOnlyModeOnlyCallsSql() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "testFunc",
            new Object[]{},
            MigrationMode.SQL_ONLY,
            1.0,
            false,
            () -> { javaCalls.incrementAndGet(); return "java"; },
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("sql", result);
        assertEquals(0, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void testJavaOnlyModeOnlyCallsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "testFunc",
            new Object[]{},
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> { javaCalls.incrementAndGet(); return "java"; },
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("java", result);
        assertEquals(1, javaCalls.get());
        assertEquals(0, sqlCalls.get());
    }

    @Test
    void testShadowModeCallsBothAndReturnsJava() {
        AtomicInteger javaCalls = new AtomicInteger(0);
        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "testFunc",
            new Object[]{},
            MigrationMode.SHADOW,
            1.0, // 100% sample rate
            false,
            () -> { javaCalls.incrementAndGet(); return "java"; },
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("java", result);
        assertEquals(1, javaCalls.get());
        assertEquals(1, sqlCalls.get());
    }

    @Test
    void testShadowModeSkipsSqlWhenCircuitOpen() {
        // Open the circuit
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("circuitTest");
        }

        AtomicInteger sqlCalls = new AtomicInteger(0);

        String result = ShadowExecutor.execute(
            "circuitTest",
            new Object[]{},
            MigrationMode.SHADOW,
            1.0,
            true, // circuit breaker enabled
            () -> "java",
            () -> { sqlCalls.incrementAndGet(); return "sql"; },
            String::equals
        );

        assertEquals("java", result);
        assertEquals(0, sqlCalls.get());

        // Cleanup
        CircuitBreaker.reset("circuitTest");
    }

    @Test
    void testSqlFunctionExceptionTripsCircuitBreaker() {
        CircuitBreaker.reset("exceptionTest");

        // Should not throw, just log and return Java result
        String result = ShadowExecutor.execute(
            "exceptionTest",
            new Object[]{},
            MigrationMode.SHADOW,
            1.0,
            true,
            () -> "java",
            () -> { throw new SqlFunctionException("test", new RuntimeException("db error")); },
            String::equals
        );

        assertEquals("java", result);
        // After one failure, circuit should still be closed (threshold is 5)
        assertFalse(CircuitBreaker.isOpen("exceptionTest"));

        // Cleanup
        CircuitBreaker.reset("exceptionTest");
    }

    @Test
    void testNullParamsHandledGracefully() {
        // Verify null params array doesn't cause NPE
        String result = ShadowExecutor.execute(
            "nullParamsTest",
            null, // null params
            MigrationMode.JAVA_ONLY,
            1.0,
            false,
            () -> "java",
            () -> "sql",
            String::equals
        );

        assertEquals("java", result);
    }
}
