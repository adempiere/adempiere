# Wave 3 Performance Baseline

**Date:** 2026-01-07
**Environment:** Headless Linux (tests require GUI environment to run)
**Test Iterations:** 500 per function
**Measurement Rounds:** 5 (median reported)
**Status:** Baseline template - requires execution in proper test environment

## Results

| Function | Java/SQL Ratio | Threshold | Status |
|----------|----------------|-----------|--------|
| paymentAllocated | TBD | <= 1.30 | Pending |
| paymentAvailable | TBD | <= 1.30 | Pending |
| invoicePaid | TBD | <= 1.30 | Pending |
| invoicePaidToDate | TBD | <= 1.30 | Pending |
| invoiceOpen | TBD | <= 1.30 | Pending |
| invoiceOpen (with schedule) | TBD | <= 1.30 | Pending |
| invoiceOpenToDate | TBD | <= 1.30 | Pending |
| invoiceDiscount | TBD | <= 1.30 | Pending |
| invoiceDiscount (with schedule) | TBD | <= 1.30 | Pending |

## Test Execution Notes

### Attempt 1: 2026-01-07

**Command Executed:**
```bash
gradle :base:test:test --tests "Wave3*PerformanceTest"
```

**Failure Reason:**
Tests require a graphical environment (X11 DISPLAY) for initialization. The ADempiere test framework uses `CommonGWSetup` which calls `Adempiere.startup()`, which in turn invokes GUI-related components (`JOptionPane.showMessageDialog`).

**Error Summary:**
```
java.awt.HeadlessException:
No X11 DISPLAY variable was set,
or no headful library support was found,
but this program performed an operation which requires it.
    at org.compiere.util.Login.isJavaOK(Login.java:145)
    at org.compiere.Adempiere.startup(Adempiere.java:336)
```

**Test Files:**
- `/home/yv01p/adempiere/base/test/src/org/compiere/migration/Wave3PaymentPerformanceTest.java`
- `/home/yv01p/adempiere/base/test/src/org/compiere/migration/Wave3InvoicePaidPerformanceTest.java`
- `/home/yv01p/adempiere/base/test/src/org/compiere/migration/Wave3InvoiceOpenPerformanceTest.java`
- `/home/yv01p/adempiere/base/test/src/org/compiere/migration/Wave3InvoiceDiscountPerformanceTest.java`

## Performance Test Configuration

All performance tests share the following configuration:

- **Warmup Iterations:** 100 iterations to allow JIT compilation
- **Test Iterations:** 500 iterations per measurement round
- **Measurement Rounds:** 5 rounds with median reported
- **Threshold:** Java/SQL ratio <= 1.30 (130% of SQL performance)
- **Tag:** `@Tag("PerformanceTest")`

### Functions Under Test

1. **paymentAllocated** - Calculates allocated amount for a payment
2. **paymentAvailable** - Calculates available amount for a payment
3. **invoicePaid** - Calculates paid amount for an invoice
4. **invoicePaidToDate** - Calculates paid amount up to a specific date
5. **invoiceOpen** - Calculates open/remaining amount for an invoice
6. **invoiceOpen (with schedule)** - Variant with payment schedule
7. **invoiceOpenToDate** - Calculates open amount up to a specific date
8. **invoiceDiscount** - Calculates available discount for early payment
9. **invoiceDiscount (with schedule)** - Variant with payment schedule

## Next Steps

To capture actual performance baseline data:

1. **Option 1: Run in headful environment**
   - Set up X11 forwarding or run on a machine with GUI
   - Execute: `gradle :base:test:test --tests "Wave3*PerformanceTest"`

2. **Option 2: Configure headless mode**
   - Modify test framework to support headless execution
   - Add `-Djava.awt.headless=true` to test JVM args
   - Disable GUI-dependent initialization in test setup

3. **Option 3: Use CI/CD with headless support**
   - Configure GitHub Actions or similar CI with virtual display (xvfb)
   - Automate baseline capture on merge to main branches

## Performance Expectations

Based on the design notes in the test files, the Java implementations are expected to be **100-1000x faster** than SQL in practice due to:

- No network round-trip to database
- No JDBC marshalling overhead
- No PostgreSQL function call overhead

The 1.30 threshold (130% of SQL) is intentionally conservative to allow for measurement noise while still catching severe performance regressions (e.g., O(n²) algorithms, excessive object allocation).

## Notes

- The performance tests use `ThreadLocal<double[]>` to accumulate ratios across repeated test executions
- Median is used instead of mean to be robust against outliers
- Each test validates that the Java implementation is not significantly slower than the SQL equivalent
- For rigorous production benchmarking, consider migrating to JMH (Java Microbenchmark Harness)
