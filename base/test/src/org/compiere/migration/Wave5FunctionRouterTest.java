package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

class Wave5FunctionRouterTest {

    @Test
    void classExists() {
        assertDoesNotThrow(() -> Class.forName("org.compiere.migration.Wave5FunctionRouter"));
    }

    @Test
    void hasBomPriceLimitMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomPriceLimit", Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomPriceListMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomPriceList", Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomPriceStdMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomPriceStd", Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyOnHandMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomQtyOnHand", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyReservedMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomQtyReserved", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyOrderedMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomQtyOrdered", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasBomQtyAvailableMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave5FunctionRouter.class.getMethod(
                "bomQtyAvailable", Integer.class, Integer.class, Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }
}
