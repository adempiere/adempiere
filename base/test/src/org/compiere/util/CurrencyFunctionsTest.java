package org.compiere.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import org.adempiere.core.domains.models.I_C_Currency;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * Unit tests for CurrencyFunctions.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>At least one currency with ISO_Code='USD' must exist</li>
 *   <li>At least one currency with ISO_Code='EUR' must exist</li>
 *   <li>Client ID 11 (GardenWorld) should exist with configured accounting schema</li>
 * </ul>
 *
 * <p>Tests use dynamic lookup by ISO code to avoid hardcoded ID dependencies.
 * Tests will be skipped (not failed) if required data is missing.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CurrencyFunctionsTest extends CommonGWSetup {

    private Integer usdCurrencyId;
    private Integer eurCurrencyId;
    private int usdStdPrecision;
    private int usdCostingPrecision;

    @BeforeAll
    void loadTestData() {
        // Dynamic lookup - tests will skip if data missing
        MCurrency usd = getCurrencyByIsoCode("USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency must exist for tests");
        usdCurrencyId = usd.get_ID();
        usdStdPrecision = usd.getStdPrecision();
        usdCostingPrecision = usd.getCostingPrecision();

        MCurrency eur = getCurrencyByIsoCode("EUR");
        assumeTrue(eur != null && eur.get_ID() > 0, "EUR currency must exist for tests");
        eurCurrencyId = eur.get_ID();
    }

    /**
     * Get currency by ISO code with fallback for API compatibility.
     */
    private MCurrency getCurrencyByIsoCode(String isoCode) {
        // Try MCurrency.get(ctx, isoCode) first - exists in newer versions
        try {
            MCurrency currency = MCurrency.get(Env.getCtx(), isoCode);
            if (currency != null && currency.get_ID() > 0) {
                return currency;
            }
        } catch (NoSuchMethodError e) {
            // Method doesn't exist, fall through to Query approach
        }

        // Fallback: Use Query API (works in all versions)
        int currencyId = new Query(Env.getCtx(), I_C_Currency.Table_Name, "ISO_Code=?", null)
            .setParameters(isoCode)
            .setOnlyActiveRecords(true)
            .firstId();

        if (currencyId > 0) {
            return MCurrency.get(Env.getCtx(), currencyId);
        }
        return null;
    }

    @Test
    void currencyRound_nullAmount_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyRound(null, usdCurrencyId, "N");
        assertNull(result);
    }

    @Test
    void currencyRound_nullCurrencyId_returnsAmount() {
        BigDecimal amount = new BigDecimal("123.456");
        BigDecimal result = CurrencyFunctions.currencyRound(amount, null, "N");
        assertEquals(amount, result);
    }

    @Test
    void currencyRound_validCurrency_roundsToStdPrecision() {
        BigDecimal amount = new BigDecimal("123.456789");
        BigDecimal result = CurrencyFunctions.currencyRound(amount, usdCurrencyId, "N");

        // USD typically has StdPrecision=2
        BigDecimal expected = amount.setScale(usdStdPrecision, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    void currencyRound_costingPrecision_roundsToCostPrecision() {
        BigDecimal amount = new BigDecimal("123.456789");
        BigDecimal result = CurrencyFunctions.currencyRound(amount, usdCurrencyId, "Y");

        // USD typically has CostingPrecision=4
        BigDecimal expected = amount.setScale(usdCostingPrecision, RoundingMode.HALF_UP);
        assertEquals(expected, result);
    }

    @Test
    void currencyRound_unknownCurrency_returnsAmount() {
        BigDecimal amount = new BigDecimal("123.456789");
        BigDecimal result = CurrencyFunctions.currencyRound(amount, 999999, "N");

        assertEquals(amount, result);
    }

    @Test
    void currencyRate_sameCurrency_returnsOne() {
        BigDecimal result = CurrencyFunctions.currencyRate(
            usdCurrencyId, usdCurrencyId,  // Same currency
            null, null, null, null);

        assertEquals(0, BigDecimal.ONE.compareTo(result));
    }

    @Test
    void currencyRate_nullFromCurrency_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyRate(
            null, usdCurrencyId, null, null, null, null);

        assertNull(result);
    }

    @Test
    void currencyRate_nullToCurrency_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyRate(
            usdCurrencyId, null, null, null, null, null);

        assertNull(result);
    }

    @Test
    void currencyRate_euroToEmuMember_handlesGracefully() {
        // EUR to any EMU member - tests the code path exists without crashing
        // The actual rate depends on EMU configuration in test DB
        // This test verifies no exceptions are thrown
        assertDoesNotThrow(() -> {
            CurrencyFunctions.currencyRate(
                eurCurrencyId, usdCurrencyId,
                Timestamp.valueOf("2002-01-01 00:00:00"),
                null, 11, 0);
        });
    }

    @Test
    void currencyRate_unknownCurrency_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyRate(
            999999, 999998,
            null, null, 11, 0);

        assertNull(result);
    }

    @Test
    void currencyConvert_sameCurrency_returnsRoundedAmount() {
        BigDecimal amount = new BigDecimal("100.00");
        BigDecimal result = CurrencyFunctions.currencyConvert(
            amount, usdCurrencyId, usdCurrencyId, null, null, 11, 0);

        // Same currency returns amount rounded to target currency precision
        assertEquals(0, amount.compareTo(result));
    }

    @Test
    void currencyConvert_zeroAmount_returnsZero() {
        BigDecimal result = CurrencyFunctions.currencyConvert(
            BigDecimal.ZERO, usdCurrencyId, eurCurrencyId, null, null, 11, 0);

        assertEquals(0, BigDecimal.ZERO.compareTo(result));
    }

    @Test
    void currencyConvert_nullAmount_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyConvert(
            null, usdCurrencyId, eurCurrencyId, null, null, 11, 0);

        assertNull(result);
    }

    @Test
    void currencyConvert_nullCurrency_returnsNull() {
        BigDecimal result = CurrencyFunctions.currencyConvert(
            new BigDecimal("100"), null, eurCurrencyId, null, null, 11, 0);

        assertNull(result);
    }
}
