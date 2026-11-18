# Wave 2 Payment Term Functions - Performance Baseline

**Captured:** [DATE]
**Environment:** PostgreSQL, OpenJDK 11, Linux

## Baseline Metrics

| Function | Test Iterations | Warmup | Max Ratio | Result |
|----------|-----------------|--------|-----------|--------|
| nextBusinessDay | 2000 | 500 | 1.30 | PENDING |
| paymentTermDueDate | 2000 | 500 | 1.30 | PENDING |
| paymentTermDueDays | 2000 | 500 | 1.30 | PENDING |
| paymentTermDiscount | 2000 | 500 | 1.30 | PENDING |

## Test Configuration

- Warmup iterations: 500
- Test iterations: 2000
- Measurement rounds: 5
- Max acceptable ratio: 1.30 (Java/SQL)
- Statistical method: Median of 5 rounds

## Notes

- nextBusinessDay pre-fetches holidays for 30-day range (avoids N+1)
- paymentTermDueDate uses MPaymentTerm caching
- paymentTermDueDays shares calculateDueDate helper with paymentTermDueDate
- paymentTermDiscount may call nextBusinessDay internally
- Performance affected by payment term configuration complexity

## Design Decisions

See Section "Design Decisions" in implementation plan for:
- Transaction handling: Optional trxName parameter
- Holiday source: MNonBusinessDay model
- Week standard: ISO (Sat/Sun = weekend)
- Rounding: Fixed 2 decimals

## SQL Baseline Timing (to be captured)

Run against test database with representative data:

```sql
-- nextBusinessDay timing
EXPLAIN ANALYZE SELECT nextBusinessDay(NOW()::timestamptz, 11);

-- paymentTermDueDate timing
EXPLAIN ANALYZE SELECT paymentTermDueDate(106, NOW()::timestamptz);

-- paymentTermDueDays timing
EXPLAIN ANALYZE SELECT paymentTermDueDays(106, NOW()::timestamptz, NOW()::timestamptz);

-- paymentTermDiscount timing
EXPLAIN ANALYZE SELECT paymentTermDiscount(1000.00, 100, 106, NOW()::timestamptz, NOW()::timestamptz);
```
