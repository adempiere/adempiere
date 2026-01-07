package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

public class Wave4FunctionsTest {

    @Test
    void nextID_methodExists() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "nextID", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }

    @Test
    void nextIDFunc_methodExists() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "nextIDFunc", Integer.class, String.class, String.class);
            assertNotNull(method);
        });
    }

    // ========== acctBalance Tests ==========

    // Test constants - use @EnabledIfEnvironmentVariable for DB-dependent tests
    // These IDs must exist in the test database with the specified AccountType values
    private static final int ASSET_ACCOUNT_ID = 12345;      // AccountType='A'
    private static final int LIABILITY_ACCOUNT_ID = 12346;  // AccountType='L'

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
    void acctBalance_assetDebit_positive() {
        // Asset account with natural sign = Debit balance
        BigDecimal result = Wave4Functions.acctBalance(
            ASSET_ACCOUNT_ID, new BigDecimal("100.00"), new BigDecimal("30.00"));
        assertEquals(0, new BigDecimal("70.00").compareTo(result), "Balance should be 70.00");
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
    void acctBalance_liabilityCredit_positive() {
        // Liability account with natural sign = Credit balance
        BigDecimal result = Wave4Functions.acctBalance(
            LIABILITY_ACCOUNT_ID, new BigDecimal("30.00"), new BigDecimal("100.00"));
        assertEquals(0, new BigDecimal("70.00").compareTo(result), "Balance should be 70.00");
    }

    @Test
    void acctBalance_nullAccount_defaultCalculation() {
        // No DB lookup needed - null account uses default calculation
        BigDecimal result = Wave4Functions.acctBalance(
            null, new BigDecimal("100.00"), new BigDecimal("30.00"));
        assertEquals(0, new BigDecimal("70.00").compareTo(result), "Balance should be 70.00");
    }

    @Test
    void acctBalance_zeroAccount_defaultCalculation() {
        BigDecimal result = Wave4Functions.acctBalance(
            0, new BigDecimal("100.00"), new BigDecimal("30.00"));
        assertEquals(0, new BigDecimal("70.00").compareTo(result));
    }

    @Test
    void acctBalance_negativeAccount_defaultCalculation() {
        BigDecimal result = Wave4Functions.acctBalance(
            -1, new BigDecimal("100.00"), new BigDecimal("30.00"));
        assertEquals(0, new BigDecimal("70.00").compareTo(result));
    }

    @Test
    void acctBalance_nullAmounts_treatedAsZero() {
        BigDecimal result = Wave4Functions.acctBalance(null, null, null);
        assertEquals(0, BigDecimal.ZERO.compareTo(result));
    }

    // ========== getSysconfig Tests ==========

    @Test
    void getSysconfig_returnsDefault_whenNotFound() {
        String result = Wave4Functions.getSysconfig(
            "NONEXISTENT_CONFIG", "default_value", 0, 0);
        assertEquals("default_value", result);
    }

    @Test
    void getSysconfig_methodSignature() {
        assertDoesNotThrow(() -> {
            var method = Wave4Functions.class.getMethod(
                "getSysconfig", String.class, String.class, Integer.class, Integer.class);
            assertEquals(String.class, method.getReturnType());
        });
    }
}
