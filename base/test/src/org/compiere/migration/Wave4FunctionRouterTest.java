package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Wave4FunctionRouterTest {

    @Test
    void classExists() {
        assertDoesNotThrow(() -> Class.forName("org.compiere.migration.Wave4FunctionRouter"));
    }

    @Test
    void hasAcctBalanceMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "acctBalance", Integer.class, BigDecimal.class, BigDecimal.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasGetSysconfigMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "getSysconfig", String.class, String.class, Integer.class, Integer.class);
            assertEquals(String.class, method.getReturnType());
        });
    }

    @Test
    void hasProductAttributeMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "productAttribute", Integer.class);
            assertEquals(String.class, method.getReturnType());
        });
    }

    @Test
    void hasDocumentNoMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "documentNo", Integer.class);
            assertEquals(String.class, method.getReturnType());
        });
    }

    @Test
    void hasLinenetamtrealinvoicelineMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "linenetamtrealinvoiceline", Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasLinenetamtrealorderlineMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "linenetamtrealorderline", Integer.class);
            assertEquals(BigDecimal.class, method.getReturnType());
        });
    }

    @Test
    void hasMaxpaydateMethod() {
        assertDoesNotThrow(() -> {
            var method = Wave4FunctionRouter.class.getMethod(
                "maxpaydate", Integer.class);
            assertEquals(Timestamp.class, method.getReturnType());
        });
    }
}
