# EMU-to-EMU Regression Test Requirement

## Context
The SQL function `currencyRate` at line 110 had a bug where it checked the source currency's EMU membership twice instead of checking both source and target currencies.

**Bug:** `IF (cf_IsEMUMember = 'Y' AND cf_IsEMUMember ='Y'`
**Fixed:** `IF (cf_IsEMUMember = 'Y' AND ct_IsEMUMember = 'Y'`

## Regression Test Needed
Add the following test to `Wave1ShadowIntegrationTest` when that file is created (Task 9):

```java
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
```

## Test Data Requirements
- DEM (Deutsche Mark) currency with `IsEMUMember = 'Y'`
- FRF (French Franc) currency with `IsEMUMember = 'Y'`
- Both currencies should have EMU entry dates and EMU rates configured

## References
- SQL function: `/home/yv01p/adempiere/db/ddlutils/postgresql/functions/C_Currency_Rate.sql:110`
- Migration script: `/home/yv01p/adempiere/migration/sql/fix-emu-rate-bug.sql`
- Implementation plan: `/home/yv01p/adempiere/docs/plans/2026-01-03-wave1-currency-implementation.md` (Decision 1)
