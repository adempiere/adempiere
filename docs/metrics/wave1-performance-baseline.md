# Wave 1 Currency Functions - Performance Baseline

**Captured:** 2026-01-03
**Environment:** PostgreSQL, OpenJDK 11, Linux 6.14.0-1021-gcp

## Baseline Metrics

| Function | Test Iterations | Warmup | Max Ratio | Result |
|----------|-----------------|--------|-----------|--------|
| currencyRound | 2000 | 500 | 1.30 | PASS |
| currencyRate | 2000 | 500 | 1.30 | PASS |
| currencyConvert | 2000 | 500 | 1.30 | PASS |

## Test Configuration

- Warmup iterations: 500
- Test iterations: 2000
- Measurement rounds: 5
- Max acceptable ratio: 1.30 (Java/SQL)
- Statistical method: Median of 5 rounds

## Notes

- currencyRound involves C_Currency table lookup (cached by MCurrency)
- currencyRate involves C_Conversion_Rate table lookup with complex EMU logic
- currencyConvert combines rate lookup and rounding
- Performance advantage over SQL is smaller than Wave 0 due to DB lookups
- All tests use same-currency paths for consistent measurement

## Decision Reference

See `docs/plans/2026-01-03-wave1-currency-implementation.md`:
- Decision 1: EMU-to-EMU SQL bug handling
- Decision 2: BigDecimal comparison tolerance (6 decimal places)
- Decision 3: Dynamic test data strategy
