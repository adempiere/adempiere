package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

/**
 * Verifies all Wave 5 BOM callBom* methods exist with correct signatures.
 * Uses reflection to avoid requiring database connection.
 */
public class SqlFunctionCallerWave5Test {

    @Test
    void hasBomPriceLimitMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomPriceLimit", Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomPriceListMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomPriceList", Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomPriceStdMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomPriceStd", Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyOnHandMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomQtyOnHand", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyReservedMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomQtyReserved", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyOrderedMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomQtyOrdered", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyAvailableMethod() {
        assertDoesNotThrow(() -> {
            var method = SqlFunctionCaller.class.getMethod(
                "callBomQtyAvailable", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }
}
