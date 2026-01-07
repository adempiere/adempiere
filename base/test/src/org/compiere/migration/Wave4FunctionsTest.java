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
        assertEquals(new BigDecimal("70.00"), result);
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "RUN_DB_TESTS", matches = "true")
    void acctBalance_liabilityCredit_positive() {
        // Liability account with natural sign = Credit balance
        BigDecimal result = Wave4Functions.acctBalance(
            LIABILITY_ACCOUNT_ID, new BigDecimal("30.00"), new BigDecimal("100.00"));
        assertEquals(new BigDecimal("70.00"), result);
    }

    @Test
    void acctBalance_nullAccount_defaultCalculation() {
        // No DB lookup needed - null account uses default calculation
        BigDecimal result = Wave4Functions.acctBalance(
            null, new BigDecimal("100.00"), new BigDecimal("30.00"));
        assertEquals(new BigDecimal("70.00"), result); // AmtDr - AmtCr
    }
}
