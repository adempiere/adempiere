# Critical Architectural Review - PostgreSQL Function Migration Design (v6)

**Date:** 2026-01-02
**Reviewer:** Senior Principal Software Architect
**Document Under Review:** `docs/plans/2026-01-01-postgresql-function-migration-design.md` (v5)
**Review Focus:** Strategic architectural risks after 5 iterations of refinement

---

## 1. Overall Assessment

**Strengths:**
- Comprehensive hybrid validation strategy with well-reasoned category-based approaches (dual-write vs shadow)
- Mature operational readiness: circuit breakers, health monitoring, explicit failure modes documented
- Transaction unit concept prevents broken intermediate states
- Escape hatches defined at multiple levels (trigger-based, downgrade path)
- Rollback drills required before Wave 1 - operationally sound

**Major Concerns:**
- **Accumulated complexity risk**: The design has grown to ~2,400 lines with 15+ infrastructure components. The infrastructure may now be more complex than the 62 functions it validates.
- **First-mover scaling bottleneck**: Bounded queue (10K limit) will saturate before other components under multi-function shadow load.
- **Scenario coverage gap**: Time-based gates (7 days @ 99.9%) don't ensure coverage of monthly/quarterly business events.
- **ThreadLocal lifecycle hazards**: InheritableThreadLocal propagation with async execution has subtle memory leak vectors not addressed by cleanup patterns.

---

## 2. Critical Issues

### 2.1 Infrastructure Complexity Exceeds Migration Complexity

**Description:** The migration infrastructure (~15 components, ~4 weeks to build per Section 2.1.1) rivals the complexity of the 62 functions being migrated. This inverts the risk profile: infrastructure failures become more likely than function migration bugs.

**Why It Matters:**
- More surface area for bugs in validation than in migration itself
- Operational burden during 4-6 month migration window
- Post-migration, all infrastructure becomes throwaway

**Suggestion:** Consider simplifying to two validation modes only:
1. **Dual-write** (for all functions, not just stateful/high-frequency) - unified logging, single replay mechanism
2. **Direct cutover** after replay validation passes

This eliminates: ShadowExecutor, CircuitBreaker, SnapshotManager, ResultComparator, sampling logic, shadow connection pool. Replay executor handles everything.

**Trade-off Analysis:**
| Current Approach | Simplified Approach |
|-----------------|---------------------|
| ~4 days infrastructure + ongoing maintenance | ~2 days infrastructure |
| Real-time mismatch detection (shadow) | 5-15 min delayed detection (dual-write) |
| Latency-sensitive for some functions | Uniform zero latency impact |
| 15+ components | 4-5 components |

**Recommendation:** Evaluate if real-time detection (shadow) justifies 3x infrastructure complexity. For a one-time migration, delayed detection may be acceptable.

---

### 2.2 Bounded Queue as First Failure Point Under Scale

**Description:** The 10K bounded queue for async logging (Section 6.3) is shared across all shadow-mode functions. When Wave 2-3 functions enter shadow mode concurrently (potentially 20+ functions), queue overflow becomes likely.

**Why It Matters:**
- Queue overflow = dropped log entries = reduced validation confidence
- Alert only fires at 8K depth - by then, data loss is imminent
- No per-function isolation means one high-frequency function can starve others

**Suggestion:**
Option A: Per-function bounded queues with independent drains:
```java
Map<String, BlockingQueue<LogEntry>> functionQueues = new ConcurrentHashMap<>();
// Each function gets 2K entries, total budget same but isolated
```

Option B: Priority queuing with function categorization:
```java
PriorityBlockingQueue<LogEntry> queue = new PriorityBlockingQueue<>(
    10_000,
    Comparator.comparing(e -> e.getPriority())  // Critical > Standard > Reporting
);
```

**First Component to Fail:** Under sustained load with 5+ functions in shadow mode, the bounded queue will overflow within 2-3 minutes, causing validation data loss before circuit breaker trips.

---

### 2.3 Time-Based Gates Miss Scenario Coverage

**Description:** Quality gates require "99.9% match rate for 7 consecutive days" (Section 5.7). This is necessary but insufficient - it doesn't ensure coverage of periodic business events.

**Why It Matters:**
- Month-end closing runs once per month - 7-day window may miss it
- Quarterly reporting, year-end processing have distinct code paths
- Mismatches in edge scenarios discovered post-cutover cause operational incidents

**Missing Trade-off:** Coverage vs Duration - the design chose duration without analyzing coverage.

**Suggestion:** Add scenario-based gates alongside time-based:
```markdown
Gate 3: Cutover Approved
+-- 99.9% match rate achieved
+-- 7 consecutive days stable
+-- Scenario coverage checklist:
    +-- Month-end close executed during validation
    +-- Currency revaluation batch completed
    +-- Payment allocation batch completed
    +-- [ ] Manual sign-off on edge case coverage
```

For Wave 2-3 functions touching invoicing/payments, validation window should span at least one month-end.

---

### 2.4 ThreadLocal Memory Leak Vectors

**Description:** Section 6.1 uses `InheritableThreadLocal` with `ContextAwareExecutor` for async propagation. While cleanup is documented, the pattern has known leak vectors in long-running thread pools.

**Why It Matters:**
- Thread pools reuse threads across requests - stale context can accumulate
- `InheritableThreadLocal` copies value to child threads, but parent updates don't propagate
- Memory leaks in production cause OOM over days/weeks

**Gap:** No explicit memory leak detection or audit mechanism.

**Suggestion:** Add defensive measures:
```java
// 1. Context age guard
public static ContextData capture() {
    ContextData data = context.get();
    if (data != null && data.createdAt.isBefore(Instant.now().minus(1, HOURS))) {
        log.warn("Stale context detected (age > 1h), clearing");
        context.remove();
        staleContextCounter.increment();
        return null;
    }
    return data;
}

// 2. Periodic audit (every 10 minutes)
ScheduledExecutorService.scheduleAtFixedRate(() -> {
    long activeContexts = countActiveContexts();  // Via reflection or weak refs
    if (activeContexts > 1000) {
        log.error("Potential context leak: {} active contexts", activeContexts);
    }
}, 10, 10, MINUTES);
```

---

### 2.5 Cache Scope Default Risk

**Description:** `RequestCache` defaults to `CacheScope.REQUEST` (Section 2.3.2). Batch jobs must explicitly call `setScope(CacheScope.BATCH)`. If a developer forgets, batch processing uses stale cached values.

**Why It Matters:**
- Silent correctness bugs in batch processing
- Allocations may not see updated invoice amounts within same batch
- Debugging "sometimes wrong" calculations is extremely difficult

**Suggestion:** Invert the default or add detection:

Option A: Default to `TRANSACTION` scope (safer for batch, acceptable for web):
```java
ThreadLocal.withInitial(() -> CacheScope.TRANSACTION)
```

Option B: Entry point detection:
```java
public static void autoDetectScope() {
    if (isHttpRequest()) {
        scope.set(CacheScope.REQUEST);
    } else if (isScheduledJob() || isBatchImport()) {
        scope.set(CacheScope.BATCH);
    } else {
        scope.set(CacheScope.TRANSACTION);  // Safe default
    }
}
```

---

### 2.6 Replay Database HA Not Addressed

**Description:** Section 11 Q3 discusses replay database isolation but not high availability. If replay DB is unavailable, dual-write validation stops.

**Why It Matters:**
- Dual-write is the strategy for highest-risk functions (nextID, currencyConvert)
- If replay DB down for 24h, validation data accumulates but cannot be processed
- Extended outage could exceed retention period, losing validation data

**Suggestion:** Document HA strategy or explicit acceptance of risk:

Option A: Replay against production (read-only):
```markdown
Fallback: If replay DB unavailable for > 4 hours, replay executor switches to
production database with READ COMMITTED isolation. Stateful functions (nextID)
skip replay during fallback (pattern validation only).
```

Option B: Explicit risk acceptance:
```markdown
Replay DB HA: Not implemented. Acceptable downtime: 24 hours.
If exceeded, affected functions extend validation period by downtime duration.
```

---

## 3. Alternative Architectural Challenge

### Database-Side Shadow Functions

**Approach:** Instead of Java-side shadow execution, wrap each SQL function with a logging trigger function in PostgreSQL:

```sql
CREATE OR REPLACE FUNCTION shadow_wrapper_invoiceOpen(p_id INT)
RETURNS NUMERIC AS $$
DECLARE
    sql_result NUMERIC;
    java_result NUMERIC;
BEGIN
    -- Execute original SQL
    sql_result := C_Invoice_Open(p_id);

    -- Call Java via pljava or external table
    java_result := java_invoke('MInvoice.getOpenAmt', p_id);

    -- Log comparison async (via pg_notify or direct insert)
    PERFORM pg_notify('migration_log', json_build_object(
        'function', 'invoiceOpen',
        'input', p_id,
        'sql_result', sql_result,
        'java_result', java_result,
        'match', sql_result = java_result
    )::text);

    RETURN sql_result;  -- SQL is source of truth during validation
END;
$$ LANGUAGE plpgsql;
```

**Primary Advantage:**
- Zero Java infrastructure changes during validation
- All comparison logic lives in database - no connection pool, circuit breaker, snapshot manager
- Production code unchanged until cutover

**Primary Disadvantage:**
- Requires pljava or similar extension for Java invocation from PL/pgSQL
- Adds round-trip latency (DB → Java → DB) for every call
- Less flexible mismatch diagnostics (no intermediate value capture)

**Verdict:** If ADempiere already has pljava or can install it, this is significantly simpler. Otherwise, current approach is appropriate.

---

## 4. Minor Issues & Improvements

### 4.1 Caffeine Dependency for 3 Functions

Adding Caffeine (Section 2.7.1) for BOM cache benefits only 3 functions (Wave 5b). The alternative `ConcurrentHashMap` with manual eviction is provided but marked as inferior.

**Suggestion:** Start with `ConcurrentHashMap` for Wave 5. If cache hit rate < 70% or eviction proves problematic, upgrade to Caffeine. Avoids adding dependency until proven necessary.

### 4.2 Schema Evolution Not Addressed

`migration.function_config` has been modified across v3-v5 (added columns). No migration scripts or versioning strategy documented.

**Suggestion:** Add:
```sql
-- Migration script pattern
-- V2026_01_02_01__add_snapshot_config.sql
ALTER TABLE migration.function_config
ADD COLUMN IF NOT EXISTS snapshot_config JSONB DEFAULT '{"max_size_kb": 50}';
```

### 4.3 Sample Rate Statistical Formula Misplaced

Section 7.3 includes the statistical confidence formula for sample rates. This is valuable but belongs in a methodology appendix, not inline with connection pool configuration.

**Suggestion:** Move to new Appendix D: "Statistical Confidence for Sample-Based Validation"

### 4.4 Monitoring Dashboard Query Performance

Section 6.3 dashboard queries include `PERCENTILE_CONT` aggregations over `function_log`. Under high log volume (50K entries/day), these queries will be slow.

**Suggestion:** Add partial indexes or materialized views:
```sql
CREATE INDEX idx_function_log_recent ON migration.function_log(function_name, created_at)
WHERE created_at > NOW() - INTERVAL '7 days';

-- Or materialized view refreshed hourly
CREATE MATERIALIZED VIEW migration.function_stats_hourly AS
SELECT function_name, DATE_TRUNC('hour', created_at) as hour,
       COUNT(*) as total, SUM(CASE WHEN is_match THEN 1 ELSE 0 END) as matches
FROM migration.function_log
GROUP BY 1, 2;
```

---

## 5. Questions for Clarification

1. **pljava Availability:** Does ADempiere deploy pljava or any Java-from-SQL bridge? If yes, the database-side shadow approach (Section 3) becomes viable.

2. **Month-End Timing:** Are Wave 2-3 functions (invoicing, payments) planned to enter validation before or after a month-end close? The current plan doesn't align validation windows with business cycles.

3. **Existing ThreadLocal Usage:** Does ADempiere's `Env` or `ServerContext` already use ThreadLocal extensively? If so, what's the pattern for cleanup? The new `RequestContext` should follow the same pattern.

4. **Post-Migration Cleanup Ownership:** Who removes the `migration` schema after completion? Is there a scheduled task or manual runbook?

5. **Circuit Breaker Tuning Authority:** Who can modify circuit breaker thresholds in production? Is this a DBA function, developer function, or requires code change?

---

## 6. Final Recommendation

**Major Revisions Needed**

The design is operationally mature with comprehensive failure handling. However, the accumulated complexity (15+ components, ~2,400 lines) has crossed a threshold where the infrastructure itself poses more risk than the migration.

**Required Changes Before Approval:**

1. **Evaluate complexity reduction** (Issue 2.1): Run a spike to test dual-write-only approach. If 5-15 min detection delay is acceptable, eliminate shadow-mode infrastructure entirely.

2. **Address queue saturation** (Issue 2.2): Implement per-function isolation or priority queuing before Wave 2.

3. **Add scenario coverage gates** (Issue 2.3): Extend validation window for Wave 2-3 to span month-end close.

4. **Document ThreadLocal audit** (Issue 2.4): Add stale context detection before production deployment.

5. **Invert cache scope default** (Issue 2.5): Change default to `TRANSACTION` or add auto-detection.

**After These Changes:** Design will be ready for Wave 1 execution.

---

## Appendix: Component Count Analysis

| Category | Components | Lines of Code (Est.) |
|----------|------------|---------------------|
| Core Validation | ShadowExecutor, DualWriteLogger, ResultComparator | ~400 |
| Infrastructure Protection | CircuitBreaker, SnapshotManager, BoundedQueue | ~300 |
| Connection Management | Shadow Pool, SqlFunctionCaller | ~150 |
| Logging & Monitoring | MigrationLogger, MigrationConfig, Health Metrics | ~350 |
| Caching | RequestCache, BOM Cache | ~200 |
| Context Propagation | RequestContext, ContextAwareExecutor | ~150 |
| Replay Infrastructure | ReplayExecutor, StatefulExecutionLog | ~400 |
| **Total** | **15 components** | **~1,950 LOC** |

For 62 functions averaging ~100 LOC each in SQL (~6,200 LOC total), the validation infrastructure represents ~30% of the migrated code volume.
