---
date: 2026-01-07T05:14:08+00:00
researcher: Claude
git_commit: 7b00e6e958da02678ff857cdc9f26cb4e4d98d73
branch: wave3
repository: adempiere
topic: "Wave0/Wave1 Performance Test Variable Threshold Update"
tags: [performance, wave0, wave1, testing, threshold]
status: in_progress
last_updated: 2026-01-07
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Update Wave0 and Wave1 Performance Tests with Variable Threshold

## Task(s)

| Task | Status |
|------|--------|
| Implement variable threshold in Wave3 performance tests | **Completed** (prior session) |
| Create performance threshold design document | **Completed** |
| Commit Wave3 changes | **Completed** - commit 7b00e6e95 |
| Update Wave0PerformanceTest with variable threshold | **Completed** (uncommitted) |
| Update Wave1PerformanceTest with variable threshold | **In Progress** - not started yet |
| Run all performance tests to verify | Planned |
| Commit Wave0/Wave1 changes | Planned |

## Critical References

1. **Performance threshold design doc:** `docs/plans/2026-01-07-wave3-performance-threshold-design.md` - Explains the variable threshold approach and rationale
2. **Wave3 implementation pattern:** `base/test/src/org/compiere/migration/Wave3InvoiceOpenPerformanceTest.java` - Reference for how tests should be structured

## Recent changes

Wave0PerformanceTest.java has been fully updated (uncommitted):
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java:33-64` - New constants and helper methods
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java:66-132` - Updated testDaysBetweenPerformance
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java:134-200` - Updated testAddDaysPerformance
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java:202-268` - Updated testRoundPerformance
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java:270-336` - Updated testTruncPerformance
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java:338-405` - Updated testFirstOfPerformance

## Learnings

### Variable Threshold Logic

Replace fixed `MAX_LATENCY_RATIO = 1.30` with:

```java
private static final double RELAXED_RATIO = 3.0;
private static final double STRICT_RATIO = 1.5;
private static final double MAX_OVERHEAD_MS = 1.0;
```

Helper methods:
```java
private boolean meetsThreshold(double overheadMs, double ratio) {
    if (overheadMs < MAX_OVERHEAD_MS && ratio <= RELAXED_RATIO) {
        return true;
    }
    return ratio <= STRICT_RATIO;
}

private String getThresholdRule(double overheadMs, double ratio) {
    if (overheadMs < MAX_OVERHEAD_MS) {
        return String.format("overhead %.3fms < %.1fms, ratio %.2f <= %.1f",
            overheadMs, MAX_OVERHEAD_MS, ratio, RELAXED_RATIO);
    }
    return String.format("ratio %.2f <= %.1f", ratio, STRICT_RATIO);
}
```

### Test Classes to Update

| File | Functions | Should Update? |
|------|-----------|----------------|
| Wave0PerformanceTest | daysBetween, addDays, round, trunc, firstOf | **Yes** - DONE |
| Wave1PerformanceTest | currencyRound, currencyRate, currencyConvert | **Yes** - TODO |
| PaymentTermFunctionsPerformanceTest | various | **No** - uses absolute thresholds, not Java vs SQL comparison |

### Output Format

Each test outputs a formatted table:
```
┌─────────────────────────────────────────────────────────────────────────────┐
│ PERFORMANCE: functionName                                                   │
├─────────────────────────────────────────────────────────────────────────────┤
│ Metric               │          SQL │         Java │         Diff │   Status │
├─────────────────────────────────────────────────────────────────────────────┤
│ Total (N calls)      │   XXX.XX ms  │   XXX.XX ms  │  +XXX.XX ms  │     PASS │
│ Per call             │     X.XXX ms │     X.XXX ms │    +X.XXX ms │          │
│ Ratio                │              │              │         XX%  │          │
│ Rule: overhead X.XXXms < 1.0ms, ratio X.XX <= 3.0                           │
└─────────────────────────────────────────────────────────────────────────────┘
```

## Artifacts

- `docs/plans/2026-01-07-wave3-performance-threshold-design.md` - Design document (committed)
- `base/test/src/org/compiere/migration/Wave0PerformanceTest.java` - Updated (uncommitted)
- `base/test/src/org/compiere/migration/Wave1PerformanceTest.java` - Needs update

## Action Items & Next Steps

1. **Update Wave1PerformanceTest.java:**
   - Replace `MAX_LATENCY_RATIO = 1.30` with the 3 new constants
   - Add `javaTimeAccumulator` and `sqlTimeAccumulator` ThreadLocals
   - Add `meetsThreshold()` and `getThresholdRule()` helper methods
   - Update class javadoc
   - Update all 3 test methods (currencyRound, currencyRate, currencyConvert) with:
     - Store times in accumulators
     - Calculate overhead
     - Use `meetsThreshold()` for assertion
     - Add formatted output table with Rule line

2. **Run all performance tests:**
   ```bash
   gradle :base:test:test --tests "Wave0PerformanceTest" --tests "Wave1PerformanceTest" --rerun-tasks
   ```

3. **Commit changes:**
   ```bash
   git add base/test/src/org/compiere/migration/Wave0PerformanceTest.java base/test/src/org/compiere/migration/Wave1PerformanceTest.java
   git commit -m "perf(wave0,wave1): apply variable threshold to legacy performance tests"
   ```

4. **Update design doc** to mention Wave0 and Wave1 coverage

## Other Notes

### Wave1PerformanceTest Differences

Wave1PerformanceTest uses slightly different patterns than Wave0:
- Uses `@TestInstance(TestInstance.Lifecycle.PER_CLASS)` and `@Execution(ExecutionMode.SAME_THREAD)`
- Uses instance field `double[] ratioAccumulator` instead of ThreadLocal
- Has `@BeforeAll` to load USD currency and `@BeforeEach` to init accumulator
- Uses 2000 iterations (vs 5000 in Wave0)

The update should preserve these patterns while adding the variable threshold logic.
