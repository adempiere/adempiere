// base/test/src/org/compiere/migration/CircuitBreakerTest.java
package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTest")
public class CircuitBreakerTest {

    @BeforeEach
    void reset() {
        CircuitBreaker.resetAll(); // Clean all circuits between tests
    }

    @Test
    void testCircuitStartsClosed() {
        assertFalse(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testCircuitOpensAfterThreshold() {
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("testFunc");
        }
        assertTrue(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testCircuitStaysClosedBelowThreshold() {
        for (int i = 0; i < 4; i++) {
            CircuitBreaker.recordFailure("testFunc");
        }
        assertFalse(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testSuccessResetsFailureCount() {
        for (int i = 0; i < 4; i++) {
            CircuitBreaker.recordFailure("testFunc");
        }
        CircuitBreaker.recordSuccess("testFunc");
        CircuitBreaker.recordFailure("testFunc");
        assertFalse(CircuitBreaker.isOpen("testFunc"));
    }

    @Test
    void testCircuitsArePerFunction() {
        for (int i = 0; i < 5; i++) {
            CircuitBreaker.recordFailure("func1");
        }
        assertTrue(CircuitBreaker.isOpen("func1"));
        assertFalse(CircuitBreaker.isOpen("func2"));
    }
}
