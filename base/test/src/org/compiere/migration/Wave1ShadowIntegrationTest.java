package org.compiere.migration;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.stream.Stream;

import org.adempiere.core.domains.models.I_C_Currency;
import org.adempiere.test.CommonGWSetup;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.CurrencyFunctions;
import org.compiere.util.Env;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Integration tests comparing Java implementations to SQL functions.
 * Validates Java matches SQL exactly for Wave 1 currency functions.
 *
 * <p><b>Test Data Requirements:</b>
 * <ul>
 *   <li>USD and EUR currencies must exist</li>
 *   <li>Client ID 11 (GardenWorld) with configured accounting schema</li>
 * </ul>
 *
 * <p>Uses dynamic currency lookup to avoid hardcoded ID dependencies.
 */
@Tag("IntegrationTest")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Wave1ShadowIntegrationTest extends CommonGWSetup {

    private Integer usdCurrencyId;
    private Integer eurCurrencyId;

    @BeforeAll
    void loadTestData() {
        MCurrency usd = getCurrencyByIsoCode("USD");
        assumeTrue(usd != null && usd.get_ID() > 0, "USD currency must exist");
        usdCurrencyId = usd.get_ID();

        MCurrency eur = getCurrencyByIsoCode("EUR");
        assumeTrue(eur != null && eur.get_ID() > 0, "EUR currency must exist");
        eurCurrencyId = eur.get_ID();
    }

    /**
     * Get currency by ISO code with fallback for API compatibility.
     * MCurrency.get(ctx, isoCode) may not exist in all ADempiere versions.
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

    static Stream<Arguments> currencyRoundTestCases() {
        return Stream.of(
            Arguments.of("123.456789", "N", "standard precision"),
            Arguments.of("123.456789", "Y", "costing precision"),
            Arguments.of("0.00", "N", "zero amount"),
            Arguments.of("999999.999999", "N", "large amount"),
            Arguments.of("-50.555", "N", "negative amount")
        );
    }

    @ParameterizedTest(name = "currencyRound({0}, USD, {1}) - {2}")
    @MethodSource("currencyRoundTestCases")
    void currencyRound_matchesSql(String amountStr, String costing, String description) {
        BigDecimal amount = new BigDecimal(amountStr);

        BigDecimal javaResult = CurrencyFunctions.currencyRound(amount, usdCurrencyId, costing);
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRound(amount, usdCurrencyId, costing);

        assertEquals(0, sqlResult.compareTo(javaResult),
            String.format("currencyRound(%s, USD, %s): java=%s, sql=%s",
                amountStr, costing, javaResult, sqlResult));
    }

    @Test
    void currencyRound_nullAmount_matchesSql() {
        BigDecimal javaResult = CurrencyFunctions.currencyRound(null, usdCurrencyId, "N");
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRound(null, usdCurrencyId, "N");

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void currencyRound_nullCurrency_matchesSql() {
        BigDecimal amount = new BigDecimal("123.456");
        BigDecimal javaResult = CurrencyFunctions.currencyRound(amount, null, "N");
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRound(amount, null, "N");

        // Both should return the original amount
        assertEquals(0, amount.compareTo(javaResult));
        assertEquals(0, amount.compareTo(sqlResult));
    }

    @Test
    void currencyRate_sameCurrency_matchesSql() {
        BigDecimal javaResult = CurrencyFunctions.currencyRate(
            usdCurrencyId, usdCurrencyId, null, null, 11, 0);
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(
            usdCurrencyId, usdCurrencyId, null, null, 11, 0);

        assertEquals(0, javaResult.compareTo(sqlResult),
            "currencyRate(USD, USD): java=" + javaResult + ", sql=" + sqlResult);
    }

    @Test
    void currencyRate_nullCurrency_matchesSql() {
        BigDecimal javaResult = CurrencyFunctions.currencyRate(null, usdCurrencyId, null, null, 11, 0);
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(null, usdCurrencyId, null, null, 11, 0);

        assertEquals(sqlResult, javaResult);
    }

    @Test
    void currencyRate_crossCurrency_matchesSqlWithTolerance() {
        // Use a historical date that's likely to have rates configured
        Timestamp convDate = Timestamp.valueOf("2024-01-01 00:00:00");

        BigDecimal javaResult = CurrencyFunctions.currencyRate(
            usdCurrencyId, eurCurrencyId, convDate, null, 11, 0);
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(
            usdCurrencyId, eurCurrencyId, convDate, null, 11, 0);

        // Both may be null if rate not configured - that's a match
        if (javaResult == null && sqlResult == null) {
            return; // Both null = match
        }

        assertNotNull(javaResult, "Java returned null but SQL returned " + sqlResult);
        assertNotNull(sqlResult, "SQL returned null but Java returned " + javaResult);

        // Compare with 6 decimal place tolerance for division rounding differences
        // See: docs/plans/2026-01-03-wave1-currency-implementation.md, Decision 2
        assertEquals(0,
            javaResult.setScale(6, RoundingMode.HALF_UP)
                      .compareTo(sqlResult.setScale(6, RoundingMode.HALF_UP)),
            String.format("currencyRate(USD, EUR, %s): java=%s, sql=%s",
                convDate, javaResult, sqlResult));
    }

    @Test
    void currencyRate_emuToEmu_matchesSqlAfterBugFix() {
        // This tests the exact path that had the SQL bug at line 110
        // DEM -> FRF (both EMU members) - validates Decision 1 fix
        MCurrency dem = getCurrencyByIsoCode("DEM");
        MCurrency frf = getCurrencyByIsoCode("FRF");
        assumeTrue(dem != null && dem.isEMUMember(), "DEM (EMU member) required for EMU path test");
        assumeTrue(frf != null && frf.isEMUMember(), "FRF (EMU member) required for EMU path test");

        // Use post-EMU date (Euro adoption was 1999-2002)
        Timestamp convDate = Timestamp.valueOf("2002-01-01 00:00:00");

        BigDecimal javaResult = CurrencyFunctions.currencyRate(
            dem.get_ID(), frf.get_ID(), convDate, null, 11, 0);
        BigDecimal sqlResult = SqlFunctionCaller.callCurrencyRate(
            dem.get_ID(), frf.get_ID(), convDate, null, 11, 0);

        // After SQL bug fix, both should return FRF_rate / DEM_rate
        // Before fix, SQL would incorrectly compute because it checked DEM twice
        assertNotNull(javaResult, "EMU-to-EMU should have fixed rate after entry date");
        assertNotNull(sqlResult, "SQL EMU-to-EMU should have fixed rate after bug fix");

        assertEquals(0,
            javaResult.setScale(6, RoundingMode.HALF_UP)
                      .compareTo(sqlResult.setScale(6, RoundingMode.HALF_UP)),
            String.format("currencyRate(DEM, FRF) EMU-to-EMU: java=%s, sql=%s",
                javaResult, sqlResult));
    }
}
