# PostgreSQL Function Migration Design

**Date:** 2026-01-01
**Status:** Design Complete (Updated 2026-01-02 per Critical Review v4)
**Reference Framework:** `~/sprocfw/step1.md` (principles), `~/sprocfw/step3.md` (per-function template)

---

## 1. Goals and Scope

**Goal:** Migrate PostgreSQL PL/pgSQL functions to Java while keeping PostgreSQL as the primary database. This improves testability, debuggability, and consolidates business logic in one language.

**In Scope:**
- 62 PostgreSQL functions in `db/ddlutils/postgresql/functions/`
- Views that depend on migrated functions (subset of 184 views)
- Existing Java implementations that duplicate SQL functions (re-validate via shadow mode)

**Out of Scope:**
- Oracle/MySQL support (PostgreSQL-primary)
- Simple views with no function dependencies (remain in PostgreSQL)
- Prioritization order (handled separately in step 2)

**Success Criteria:**
- All migrated functions pass validation (99.9% match rate for 7 days)
- No SQL functions remain after migration completes
- Views calling migrated functions are converted to Java query methods
- Zero production regressions after cutover

**Non-Goals:**
- Database abstraction layer (not trying to support multiple databases)
- ORM adoption (continue using ADempiere's existing `Query` class and direct JDBC)
- Refactoring beyond what's necessary for migration

---

## 2. Migration Architecture

### 2.1 Hybrid Validation Strategy

**Overview:** Different validation strategies for different risk categories.

| Category | Functions | Strategy | Rationale |
|----------|-----------|----------|-----------|
| **Stateful** | `nextID`, `nextIDFunc` | Dual-Write Logging | Shadow mode incompatible (dual execution consumes sequences) |
| **High-Frequency Critical** | `currencyConvert`, `currencyRate` | Dual-Write Logging | Thousands of calls/transaction; even 1% sampling adds latency |
| **Standard** | Waves 2, 3, 4 | Shadow Mode (sampling) | Adequate coverage with acceptable latency |
| **Complex Low-Frequency** | BOM functions (Wave 5) | Shadow Mode (100%) | Real-time comparison catches edge cases |

**Dual-Write Logging Flow:**
```
Request arrives
    → Execute Java implementation
    → Emit structured log: {inputs, intermediates, output, timestamp}
    → Return Java result immediately

Background replay job:
    → Read logged executions
    → Replay inputs against SQL in isolated environment
    → Compare outputs, report mismatches
```

**Benefits:**
- Zero production latency impact (logging is async)
- 100% validation coverage without sampling
- Works uniformly for stateful functions
- Richer diagnostics (intermediate calculations logged)

### 2.1.1 Dual-Write Logging Infrastructure

**Log Format:**
```java
public class FunctionExecutionLog {
    String functionName;
    String correlationId;
    Instant timestamp;
    Map<String, Object> inputs;
    Map<String, Object> intermediates;  // e.g., "rate": 1.25, "fromCurrency": "EUR"
    Object output;
    long executionTimeMs;
}
```

**Storage:** `migration.function_execution_log` table (JSON column for flexibility)

**Replay Environment:**
- Isolated PostgreSQL instance with production schema
- Refreshed via logical replication (< 1 minute lag) or nightly snapshots
- For stateful functions (`nextID`): replay with sequence reset to verify pattern matching

**Replay Executor:** Background job that:
1. Reads unprocessed logs in batches (1000 at a time)
2. Executes SQL function with logged inputs
3. Compares output, records mismatch if any
4. Marks log entry as processed

**Detection Latency:** 5-15 minutes (acceptable tradeoff for zero production impact)

**Infrastructure Components:**

| Component | Effort |
|-----------|--------|
| Log format + async emitter | 0.5 days |
| Replay executor | 1-2 days |
| Isolated database setup | 0.5 days |
| Comparator + dashboard | 1-2 days |
| **Total** | **~4 days** |

### 2.1.2 Shadow Mode Circuit Breaker

**Problem:** During peak load (month-end, batch processing), shadow mode adds latency without providing validation data if the queue is overwhelmed.

**Solution:** Automatic circuit breaker that disables shadow comparisons under stress.

**Trigger Conditions:**
- Queue depth > 80% (8,000 entries) for > 30 seconds, OR
- p95 latency degradation > 10% compared to baseline

**Circuit Breaker Behavior:**
```
CLOSED (normal):
    → Shadow mode active, comparisons running

OPEN (tripped):
    → Shadow comparisons disabled
    → Java executes alone (JAVA_ONLY behavior)
    → Log: "Circuit breaker tripped for {function}"
    → Retry after 5 minutes

HALF-OPEN (recovery):
    → Allow 10% of calls through shadow mode
    → If queue stable for 2 minutes → CLOSED
    → If queue spikes again → OPEN
```

**Metrics:**
- `migration.shadow.circuit_breaker.state` - current state per function
- `migration.shadow.circuit_breaker.trips` - count of trips
- Alert: circuit breaker tripped > 3 times in 1 hour indicates sample rate too high

**Configuration:** Stored in `migration.function_config`:
```sql
ALTER TABLE migration.function_config ADD COLUMN
    circuit_breaker_enabled BOOLEAN DEFAULT true;
```

### 2.1.3 Stateful Function Validation

**Affected Functions:** `nextID`, `nextIDFunc` (TU-009)

**Why Dual-Write, Not Staged Rollout:**
- Staged rollout (10%/50% random) causes selection bias within transactions
- A single Order with 10 Lines could get mixed Java/SQL-generated IDs
- Debugging inconsistent gaps within business documents is extremely difficult

**Validation Approach:**

| Phase | Activity | Success Criteria |
|-------|----------|------------------|
| 1. Unit Testing | Test sequence logic in isolation | All edge cases pass |
| 2. Integration Testing | Concurrent access (100 threads) | No duplicate IDs |
| 3. Dual-Write Logging | Log all Java executions in production | 100% coverage |
| 4. Replay Validation | Replay against isolated DB with reset sequences | Pattern matching confirms equivalent behavior |
| 5. Cutover | Switch to JAVA_ONLY | Monitor for 24h |

**Replay for Stateful Functions:**
```
Production: Java generates IDs 1001, 1002, 1003...
Replay DB:  Reset sequence to 1001
            Execute SQL with same inputs
            Verify: SQL generates 1001, 1002, 1003...
```

**Rollback Trigger:** Any duplicate ID detection triggers immediate revert to SQL_ONLY.

**Note:** `documentNo` is in TU-012 (Miscellaneous Standalone), not TU-009. It uses shadow mode, not dual-write, as it's lower frequency.

### 2.1.4 Sequence State Capture for Replay

**Problem:** Replay database sequences drift from production due to replication lag or snapshot age. Resetting to arbitrary positions doesn't validate real behavior.

**Solution:** Capture sequence position at execution time; use for pattern validation only.

**Enhanced Log Format for Stateful Functions:**
```java
public class StatefulExecutionLog extends FunctionExecutionLog {
    Map<String, Long> sequencePositions;  // e.g., {"ad_sequence.c_order": 50000}
}
```

**Replay Strategy for nextID/nextIDFunc:**
1. Log captures: sequence name, position before call, generated ID, position after
2. Replay validates: increment pattern correct (position_after = position_before + 1)
3. Replay does NOT validate: absolute ID values match production

**Validation Confidence Matrix:**

| Validation Method | Confidence | What It Validates |
|-------------------|------------|-------------------|
| Unit tests | HIGH | Logic correctness, edge cases |
| Integration tests (100 threads) | HIGH | Concurrency, no duplicates |
| Dual-write replay | MEDIUM | Increment patterns, no gaps |
| Production monitoring | HIGH | No duplicate ID errors post-cutover |

**Acceptance:** Stateful functions cannot achieve the same replay confidence as stateless functions. The combination of thorough integration tests + pattern validation + 24h post-cutover monitoring provides sufficient safety.

**Rollback Trigger:** Any duplicate ID or gap detected in production triggers immediate revert to SQL_ONLY.

### 2.1.5 Replay Executor Reliability

**Health Monitoring:**

| Metric | Alert Threshold | Action |
|--------|-----------------|--------|
| Last successful replay | > 30 minutes ago | Page on-call |
| Unprocessed backlog | > 10,000 entries | Warning |
| Unprocessed backlog | > 50,000 entries | Critical |
| Replay error rate | > 5% | Warning |

**Heartbeat Table:**
```sql
CREATE TABLE migration.replay_executor_health (
    executor_id VARCHAR(50) PRIMARY KEY,
    last_heartbeat TIMESTAMP NOT NULL,
    entries_processed_last_hour INT,
    current_backlog INT
);
```

**Redundancy via Partitioning:**
- Multiple executor instances (2-3 recommended)
- Each instance owns partition of functions: `hash(function_name) % instance_count`
- Leader election not required; partitions are static
- If instance dies, its partition backlog grows until restart (acceptable for 5-15 min detection SLA)

**Backlog Retention Policy:**

| Age | Action |
|-----|--------|
| 0-7 days | Active replay queue |
| 7-30 days | Archive to `migration.replay_archive` (compressed) |
| > 30 days | Delete archived entries |

**Failure Runbook:**
```markdown
## Replay Executor Failure

1. Check executor logs: journalctl -u adempiere-replay-executor
2. If crashed, restart: systemctl restart adempiere-replay-executor
3. If backlog > 50K, scale horizontally or reduce dual-write functions temporarily
4. If persistent failures, set affected functions to SHADOW mode (accepts latency tradeoff)
5. Post-incident: analyze root cause, add to monitoring
```

### 2.1.6 Trigger-Based Validation (Escape Hatch)

**Use Case:** Wave 4-5 low-frequency functions where shadow infrastructure proves too complex or risky to deploy.

**When to Use:**
- Function called < 100 times/day
- Shadow mode causing unexpected production issues
- Team capacity insufficient for full shadow implementation

**Approach:**
```sql
-- Add logging trigger to existing SQL function
CREATE OR REPLACE FUNCTION migration.log_function_call()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO migration.trigger_log (function_name, inputs, output, called_at)
    VALUES (TG_ARGV[0], row_to_json(NEW), TG_ARGV[1], NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
```

**Validation Flow:**
```
Production: SQL function executes normally
  → Trigger logs inputs/outputs

Offline job (hourly):
  → Read trigger_log entries
  → Execute Java function with same inputs
  → Compare outputs
  → Report mismatches

Cutover (when stable):
  → Set feature flag to JAVA_ONLY
  → Drop trigger
```

**Trade-offs:**

| Aspect | Shadow/Dual-Write | Trigger-Based |
|--------|-------------------|---------------|
| Production code change | Required | None until cutover |
| Latency overhead | Varies | 1-5ms per call |
| Intermediate values | Captured | Not available |
| Implementation effort | Higher | Lower |

**Recommendation:** Default to shadow/dual-write. Use trigger-based only for Wave 4-5 functions if operational issues emerge with primary strategy.

### 2.2 Function Dependency Graph

**Problem:** Functions have interdependencies (e.g., `invoiceOpen` calls `currencyConvert`). Migrating in wrong order causes shadow mode comparisons to be meaningless.

**Resolution:** Complete dependency analysis exists in `docs/discovery/`:

| Artifact | Purpose |
|----------|---------|
| `dependency-graph.dot` | Visual dependency graph (Graphviz format) |
| `migration-waves.md` | 6 waves with topological ordering |
| `transaction-units.json` | 12 units grouping functions with dependent views |
| `circular-dependencies.json` | Identified cycles (none blocking) |

**Migration Order:** Leaf functions first (Wave 0: Foundation), then progressively up the dependency chain. See `migration-waves.md` for complete ordering.

**Critical Path:** Wave 0 → Wave 1 (Currency) → Wave 3 (Financial Core)

### 2.3 Known Duplicate Resolution: invoiceOpen

**Problem:** `MInvoice.getOpenAmt()` (Java) diverges from `C_Invoice_Open` (SQL). Java has empty TODO blocks for payment schedule logic that SQL implements (~30 lines).

**Decision:** Enhance Java to match SQL behavior.

**Rationale:**
- Payment schedule logic exists for business reasons - customers depend on it
- Shadow mode would fail immediately for invoices with payment schedules
- Fixing Java eliminates a known bug rather than enshrining divergence

**Implementation Requirements:**
1. Port `C_InvoicePaySchedule` iteration logic from SQL to Java
2. Add cache invalidation for `openAmount` field when allocations change
3. Create integration tests with multi-schedule invoices

**Complete Cache Invalidation Scope:**

All state transitions affecting invoice open amount must invalidate the cache:

| Trigger | Location | Method |
|---------|----------|--------|
| Allocation created/modified | `MAllocationLine.afterSave()` | `invoice.invalidateOpenAmtCache()` |
| Allocation deleted | `MAllocationLine.afterDelete()` | `invoice.invalidateOpenAmtCache()` |
| Invoice voided | `MInvoice.voidIt()` | `this.invalidateOpenAmtCache()` |
| Invoice reversed | `MInvoice.reverseCorrectIt()` | Both invoices invalidated |
| Payment voided | `MPayment.voidIt()` | Invalidate all linked invoices |
| Payment reversed | `MPayment.reverseCorrectIt()` | Invalidate all linked invoices |
| Credit memo allocated | `MAllocationLine.afterSave()` | Already covered |

### 2.3.1 Cache Strategy: Request-Scoped

**Decision:** Use request-scoped cache instead of field-level cache.

**Rationale:**
- Field-level cache requires manual invalidation hooks in 7+ locations
- Missing a single invalidation hook causes subtle, hard-to-debug inconsistencies
- Request-scoped cache auto-invalidates at transaction boundary
- Eliminates entire class of cache coherency bugs

**Implementation:**
```java
public class RequestCache {
    private static final ThreadLocal<Map<String, Object>> cache =
        ThreadLocal.withInitial(HashMap::new);

    public static <T> T computeIfAbsent(String key, Supplier<T> compute) {
        return (T) cache.get().computeIfAbsent(key, k -> compute.get());
    }

    public static void clear() {
        cache.get().clear();
    }
}

// Usage in MInvoice:
public BigDecimal getOpenAmt() {
    return RequestCache.computeIfAbsent(
        "invoice.openAmt." + getC_Invoice_ID(),
        this::calculateOpenAmtJava
    );
}
```

**Transaction Boundary Hook:**
```java
// In Trx.close() or equivalent transaction completion point:
RequestCache.clear();
```

**Migration Path:**
1. Implement RequestCache utility class
2. Hook clear() into transaction completion
3. Migrate getOpenAmt() to use RequestCache
4. Remove field-level cache and all invalidation hooks
5. Other cached calculations follow same pattern

**Note:** The comprehensive invalidation trigger table above documents what field-level cache would require; request-scoped cache eliminates this complexity.

**Acceptance Criteria:** Shadow mode achieves 99.9% match rate for `invoiceOpen` including invoices with payment schedules.

### 2.4 Code Location Pattern

Migrated functions follow ADempiere's existing model class pattern:

| Function Type | Java Location | Example |
|---------------|---------------|---------|
| Entity-specific calculations | Instance method on M* class | `MInvoice.getOpenAmt()` |
| Utility functions | Static method on relevant M* class | `MConversionRate.convert()` |
| Complex multi-entity logic | Dedicated calculation class | `MProductPricing` |

### 2.5 Shadow Execution Flow

```
Caller invokes Java method
    |
    v
Check feature flag (migration.function_config)
    |
    v
+-------------------------------------+
| SHADOW mode                         |
|  - Check circuit breaker state      |
|  - Execute Java logic               |
|  - Apply sampling (if not skipped)  |
|  - Execute SQL function via JDBC    |
|  - Compare results                  |
|  - Log mismatches (async)           |
|  - Return Java result               |
+-------------------------------------+
    |
    v
After 99.9% match for 7 days -> JAVA_ONLY mode
    |
    v
Delete SQL function from database
```

### 2.6 Feature Flag Values (per function)

| Flag Value | Behavior |
|------------|----------|
| `SQL_ONLY` | Legacy path, Java not ready |
| `SHADOW` | Both execute, compare, return Java (with circuit breaker) |
| `DUAL_WRITE` | Java executes, logs for replay validation |
| `JAVA_ONLY` | SQL function can be deleted |

**Flag Storage:** `migration.function_config` table (separate schema)

### 2.7 BOM Function Migration Requirements

**Affected Functions:** `bomQtyOnHand`, `bomQtyOrdered`, `bomQtyReserved` (Wave 5b, 135-145 LOC each)

**Recursion Safeguards (Required, Not Optional):**

| Safeguard | Specification |
|-----------|---------------|
| **Maximum Depth** | 100 levels (configurable via `AD_SysConfig` key `BOM_MAX_DEPTH`) |
| **Circular Detection** | `HashSet<Integer>` of visited `M_Product_ID` per traversal |
| **Memory Limit** | 10,000 cached component calculations per request (LRU eviction) |
| **Exceeded Behavior** | Log warning, return result for traversed portion, mark as partial |

**Implementation Pattern:**
```java
public BigDecimal calculateBomQty(int productId, Set<Integer> visited, int depth) {
    if (depth > getMaxDepth()) {
        log.warn("BOM depth limit exceeded for M_Product_ID={}", productId);
        return partialResult;
    }
    if (!visited.add(productId)) {
        log.warn("Circular BOM detected for M_Product_ID={}", productId);
        return BigDecimal.ZERO;  // Break cycle
    }
    // ... recursion logic
}
```

**Required Integration Tests:**
- Deep BOM: 50+ levels
- Circular reference: A → B → C → A
- Wide BOM: 100+ components at single level
- Combined: deep + wide + near-circular

### 2.7.1 BOM Cache Memory Management

**Problem:** Per-request caches (10,000 entries each) don't protect against concurrent batch processing memory pressure.

**Solution:** JVM-wide shared cache with size-based eviction.

**Implementation:**
```java
// Shared across all requests
private static final Cache<BomCacheKey, BigDecimal> bomCache = Caffeine.newBuilder()
    .maximumSize(100_000)              // JVM-wide limit
    .expireAfterWrite(5, TimeUnit.MINUTES)  // Prevent stale data
    .recordStats()                      // For monitoring
    .build();

public record BomCacheKey(int productId, int warehouseId, String qtyType) {}
```

**Memory Budget:**
- 100,000 entries × ~150 bytes/entry = ~15MB maximum
- Acceptable overhead for JVM with typical 2-4GB heap

**Per-Request Tracking (for circular detection):**
```java
public BigDecimal calculateBomQty(int productId, Set<Integer> visited, int depth) {
    // Circular detection still per-request (thread-local)
    if (!visited.add(productId)) {
        log.warn("Circular BOM detected for M_Product_ID={}", productId);
        return BigDecimal.ZERO;
    }

    // Cache lookup is JVM-wide
    BomCacheKey key = new BomCacheKey(productId, warehouseId, "ON_HAND");
    return bomCache.get(key, k -> computeBomQtyInternal(productId, visited, depth));
}
```

**Monitoring:**
- `bom.cache.hit_rate` - target > 80% during batch processing
- `bom.cache.evictions` - alert if > 10,000/minute (cache too small)
- `bom.cache.size` - current entry count

---

## 3. View Migration Strategy

### Hybrid Approach

| View Type | Action | Example |
|-----------|--------|---------|
| Simple (no function calls) | Keep in PostgreSQL | Basic joins, projections |
| Calls migrated functions | Migrate to Java | `C_Invoice_V` calls `currencyConvert` |
| Reporting/aggregation | Keep in PostgreSQL | Materialized views, dashboards |

### 3.1 View Migration Performance Requirements

**Problem:** Functions have tiered latency budgets (5%/30%/100%); view migration needs equivalent gates.

**Performance Gates for Views:**

| View Type | Max Latency Increase | Max Row Amplification |
|-----------|---------------------|----------------------|
| **Transactional** (used in forms) | 30% | 1.0x (no extra rows) |
| **Reporting** (dashboards, lists) | 100% | 1.5x |
| **Batch** (overnight jobs) | 200% | 2.0x |

**Row Amplification:** Java query method must not fetch significantly more rows than SQL view. Measures filter pushdown correctness.

**Validation Process (per view):**

```
1. Baseline capture:
   EXPLAIN ANALYZE SELECT * FROM {view} WHERE {typical_filter};
   Record: execution time, rows returned, rows scanned

2. After migration:
   Benchmark Java query method with identical filter
   Record: execution time, rows fetched from DB

3. Compare:
   - Latency within tier budget?
   - Row count within amplification limit?
   - If NO: optimize Java or keep view in PostgreSQL
```

**Success Criterion:** Java query method must not read more rows than SQL view would for equivalent filters.

**Escape Hatch:** Views that cannot meet performance gates remain in PostgreSQL (hybrid approach already supports this).

### 3.2 Transaction Units

Functions and their dependent views migrate together as a single unit. This prevents broken intermediate states.

```
Transaction Unit Example: Invoice Open Amount
+-- C_Invoice_Open (function) -> MInvoice.getOpenAmt()
+-- C_Invoice_V (view) -> InvoiceQuery.findInvoicesWithOpenAmt()
+-- Any other views calling C_Invoice_Open
```

### 3.3 View to Java Conversion Pattern

SQL views become query methods using ADempiere's existing `Query` class:

```java
// Before: SELECT * FROM C_Invoice_V WHERE ...
// After:
public class InvoiceQuery {
    public static List<InvoiceVO> findOpenInvoices(int bPartnerId) {
        return new Query(ctx, MInvoice.Table_Name, "C_BPartner_ID=?", trxName)
            .setParameters(bPartnerId)
            .list()
            .stream()
            .map(inv -> new InvoiceVO(inv, inv.getOpenAmt()))
            .collect(Collectors.toList());
    }
}
```

**Discovery Step:** Before migrating each function, identify all dependent views using PostgreSQL catalog queries.

---

## 4. Handling Existing Duplicates

**Problem:** Some functions already have Java equivalents that may have diverged (e.g., `MInvoice.getOpenAmt()` vs `C_Invoice_Open`).

### Known Duplicates to Investigate

| SQL Function | Java Method | Risk |
|--------------|-------------|------|
| `C_Invoice_Open` | `MInvoice.getOpenAmt()` | HIGH - Java has TODO for payment schedules |
| `C_Currency_Convert` | `MConversionRate.convert()` | MEDIUM - verify edge cases |

### Process for Duplicates

```
1. Identify which path is currently used
   - Search codebase for Java method calls
   - Search views/functions for SQL function calls

2. Run validation (shadow mode or dual-write depending on category)
   - SQL = source of truth
   - Log all mismatches with full context

3. Analyze mismatches
   - If Java is incomplete -> enhance Java to match SQL
   - If Java has bugs -> fix Java
   - If SQL has bugs -> document, fix Java, note deviation

4. Achieve 99.9% match -> cutover to JAVA_ONLY
```

### Mismatch Logging

Stored in `migration.function_log`:
- Timestamp
- Function name
- Input parameters
- SQL result
- Java result
- Difference description
- Correlation ID (for debugging)

---

## 5. Testing and Quality Gates

### 5.1 Rollback Validation Requirement

**Problem:** The design relies on git rollback but this procedure has not been validated. An untested rollback is not a safety net.

**Required: Rollback Drill Before Wave 1**

Execute end-to-end rollback in staging environment:

| Step | Action | Verification |
|------|--------|--------------|
| 1 | Deploy Java implementation, set JAVA_ONLY | Function works via Java |
| 2 | Simulate failure: delete SQL function from DB | Confirm it's gone |
| 3 | Git checkout previous commit | Code reverts |
| 4 | Run deployment pipeline | SQL DDL re-applied |
| 5 | Reset feature flag to SQL_ONLY | Function works via SQL |
| 6 | Execute function with test data | Correct result returned |

**Runbook Documentation (Required):**

```markdown
## Rollback Procedure for Function: {function_name}

1. Set feature flag to SQL_ONLY:
   UPDATE migration.function_config SET mode = 'SQL_ONLY' WHERE function_name = '{name}';

2. If SQL function was deleted, restore from git:
   git show {commit_sha}:db/ddlutils/postgresql/functions/{name}.sql | psql

3. Verify function exists:
   SELECT proname FROM pg_proc WHERE proname = '{name}';

4. Test with known inputs:
   SELECT {function_name}({test_params});
```

**Gate 2 Addition:** Rollback drill completion is now a prerequisite before any function enters SHADOW or DUAL_WRITE mode.

### 5.2 Additional Configuration Requirements

**Configurable Match Rate Tolerances:**

Add per-function tolerance configuration to `migration.function_config`:

```sql
ALTER TABLE migration.function_config ADD COLUMN
    tolerance_config JSONB DEFAULT '{
        "timestamp_seconds": 1,
        "decimal_precision": 4,
        "null_empty_equivalent": true
    }';
```

**Sample Rate Hot-Reload:**

- `sample_rate` changes take effect within 60 seconds (cached with TTL)
- No restart required to tune shadow mode intensity
- Add `updated_at` trigger to track configuration changes

**Performance Baseline Capture:**

| Parameter | Specification |
|-----------|---------------|
| Load conditions | Capture during typical business hours (not peak, not idle) |
| Duration | 1 hour of sampling |
| Minimum samples | 1,000 calls (or 100% if lower frequency) |
| Metrics captured | p50, p95, p99, max |

### 5.3 Cross-Wave Rollback Dependencies

| If Rolling Back... | Check Dependencies |
|--------------------|-------------------|
| Wave 1 (Currency) | Wave 2, 3 depend on currency functions - must also rollback |
| Wave 2 (Invoicing) | Wave 3 (Financial) may depend - assess before rollback |
| Wave 3+ | Generally independent - can rollback in isolation |

**Rule:** Before rolling back any wave, check `docs/discovery/dependency-graph.dot` for downstream dependencies.

### 5.4 Discovery Artifact Lifecycle

| Phase | Artifact Status |
|-------|-----------------|
| During migration | Active, updated as issues discovered |
| Post-migration (all waves complete) | Archived to `docs/archive/migration-2026/` |
| Retention | Permanent (historical reference) |

### 5.5 Testing Layers

| Layer | Purpose | When |
|-------|---------|------|
| Unit tests | Test Java logic in isolation | Before validation mode |
| Integration tests | Test with real database | Before validation mode |
| Performance tests | Verify latency acceptable | Before validation mode |
| Shadow/Dual-write | Production validation | 7+ days before cutover |

### 5.6 Performance Requirements

Performance budgets are tiered by function criticality:

| Tier | Max Latency Increase (p95) | Functions |
|------|---------------------------|-----------|
| **Critical** | 5% | `nextID`, `currencyConvert`, `currencyRate`, `documentNo` |
| **Standard** | 30% | Most functions (default tier) |
| **Reporting** | 100% | Rarely-used reporting functions, BOM calculations |

**Tier Assignment:** Stored in `migration.function_config.performance_tier` column.

**Measurement:**
- Baseline captured before validation mode begins
- Measured during shadow mode (both paths timed independently)
- Alert thresholds vary by tier

### 5.7 Quality Gates (per function)

```
Gate 1: Code Complete
+-- Java implementation matches SQL logic
+-- Unit tests pass
+-- Integration tests pass

Gate 2: Validation Ready
+-- Feature flag set to SHADOW or DUAL_WRITE
+-- Mismatch logging configured
+-- Monitoring dashboard ready
+-- Performance baseline captured, Java within tier budget
+-- Rollback drill completed

Gate 3: Cutover Approved
+-- 99.9% match rate achieved
+-- 7 consecutive days stable
+-- No critical mismatches unresolved
+-- Dependent views migrated

Gate 4: Cleanup Complete
+-- Feature flag set to JAVA_ONLY
+-- SQL function deleted
+-- Monitoring confirms no errors
```

### 5.8 Match Rate Calculation

```
Match Rate = (Total Calls - Mismatches) / Total Calls x 100

Exclusions from mismatch count (configurable per-function):
- Timing differences (timestamps within configured tolerance)
- Floating point precision (within configured tolerance)
- Null vs empty string (if configured as equivalent)
```

**Rollback Trigger:** If error rate spikes post-cutover, redeploy previous version via git (includes SQL functions).

---

## 6. Logging and Monitoring

### 6.1 Correlation ID Strategy

**Origin:** Generated at request entry point (web request or API call).

**Format:** `{timestamp_ms}-{random_4_chars}` e.g., `1735689600000-a3f9`

**Propagation:**
```
HTTP Request arrives
  → Filter generates correlationId
  → Stored in ThreadLocal (RequestContext)
  → Passed to all shadow/dual-write log entries
  → Included in mismatch alerts
  → Cleared at request completion
```

**Implementation:**
```java
public class RequestContext {
    private static final ThreadLocal<String> correlationId = new ThreadLocal<>();

    public static void init() {
        correlationId.set(System.currentTimeMillis() + "-" +
            Integer.toHexString(ThreadLocalRandom.current().nextInt(0xFFFF)));
    }

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void clear() {
        correlationId.remove();
    }
}
```

**Integration Points:**

| Component | Action |
|-----------|--------|
| Web filter | `RequestContext.init()` on entry, `clear()` on exit |
| ShadowExecutor | `RequestContext.getCorrelationId()` for log entries |
| DualWriteLogger | Same |
| Mismatch alerts | Include correlationId for debugging |

**ADempiere Compatibility:** If ADempiere has existing request tracing, use that instead. Check for `Env.getContext()` or similar request-scoped storage.

### 6.2 Log Retention Policy

**Retention Tiers:**

| Table | Retention | Cleanup Method |
|-------|-----------|----------------|
| `function_log` (matches) | 7 days | Daily batch delete |
| `function_log` (mismatches) | 90 days | Manual review required before delete |
| `function_execution_log` (processed) | 30 days | Daily batch delete |
| `function_execution_log` (unprocessed) | Indefinite | Alert if age > 7 days |
| `replay_executor_health` | 7 days | Daily batch delete |

**Cleanup Job:**
```sql
-- Run daily at 03:00 UTC (low-traffic window)
DELETE FROM migration.function_log
WHERE is_match = true
  AND created_at < NOW() - INTERVAL '7 days';

DELETE FROM migration.function_execution_log
WHERE processed = true
  AND created_at < NOW() - INTERVAL '30 days';

-- Archive mismatches before deletion
INSERT INTO migration.mismatch_archive
SELECT * FROM migration.function_log
WHERE is_match = false
  AND created_at < NOW() - INTERVAL '90 days';

DELETE FROM migration.function_log
WHERE is_match = false
  AND created_at < NOW() - INTERVAL '90 days';
```

**Archive Table:**
```sql
CREATE TABLE migration.mismatch_archive (
    LIKE migration.function_log INCLUDING ALL
) WITH (autovacuum_enabled = false);  -- Cold storage, no maintenance
```

**Storage Estimate:**
- 10,000 shadow comparisons/day × 500 bytes × 7 days = ~35 MB for matches
- Mismatches should be rare; 90-day retention is negligible

### 6.3 Separate Schema

All migration-related tables live in a dedicated PostgreSQL schema, not touching the main ADempiere schema.

```sql
CREATE SCHEMA migration;

CREATE TABLE migration.function_log (
    id SERIAL PRIMARY KEY,
    function_name VARCHAR(100) NOT NULL,
    correlation_id VARCHAR(50),
    input_params JSONB,
    sql_result TEXT,
    java_result TEXT,
    sql_time_ms INT,
    java_time_ms INT,
    is_match BOOLEAN,
    mismatch_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE migration.function_config (
    function_name VARCHAR(100) PRIMARY KEY,
    mode VARCHAR(20) NOT NULL, -- SQL_ONLY, SHADOW, DUAL_WRITE, JAVA_ONLY
    performance_tier VARCHAR(20) DEFAULT 'STANDARD', -- CRITICAL, STANDARD, REPORTING
    sql_baseline_p95_ms INT,
    sample_rate DECIMAL(5,4) DEFAULT 1.0, -- 1.0 = 100%, 0.01 = 1%
    circuit_breaker_enabled BOOLEAN DEFAULT true,
    tolerance_config JSONB DEFAULT '{"timestamp_seconds": 1, "decimal_precision": 4, "null_empty_equivalent": true}',
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE migration.function_execution_log (
    id SERIAL PRIMARY KEY,
    function_name VARCHAR(100) NOT NULL,
    correlation_id VARCHAR(50),
    inputs JSONB,
    intermediates JSONB,
    output JSONB,
    execution_time_ms INT,
    processed BOOLEAN DEFAULT false,
    replay_result JSONB,
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_function_log_name_created
ON migration.function_log(function_name, created_at);

CREATE INDEX idx_execution_log_unprocessed
ON migration.function_execution_log(function_name, created_at) WHERE NOT processed;
```

### Async Logging

To avoid skewing performance measurements:

```
Call Java method
  -> Start timer
  -> Execute logic
  -> Stop timer
  -> Add entry to bounded queue (fast, non-blocking)
  -> Return result

Background thread (every 1 second):
  -> Drain queue
  -> Batch INSERT to migration.function_log
```

**Bounded Queue with Backpressure:**

```java
// Queue configuration
private static final int QUEUE_CAPACITY = 10_000;
private final BlockingQueue<LogEntry> queue =
    new LinkedBlockingQueue<>(QUEUE_CAPACITY);

// Non-blocking add with overflow handling
public void logAsync(LogEntry entry) {
    if (!queue.offer(entry)) {
        // Queue full - apply backpressure strategy
        droppedCount.incrementAndGet();
        if (droppedCount.get() % 1000 == 0) {
            log.warn("Migration log queue full, {} entries dropped", droppedCount.get());
        }
    }
}
```

**Overflow Strategy:** Drop-oldest with monitoring. Queue depth exposed as metric for alerting.

**Operational Metrics:**
- `migration.queue.depth` - current queue size
- `migration.queue.dropped` - entries dropped due to overflow
- Alert threshold: queue depth > 8,000 for > 60 seconds

### Monitoring Dashboard Queries

```sql
-- Match rate by function (last 24 hours)
SELECT function_name,
       COUNT(*) as total_calls,
       SUM(CASE WHEN is_match THEN 1 ELSE 0 END) * 100.0 / COUNT(*) as match_rate
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;

-- Performance comparison
SELECT function_name,
       PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY sql_time_ms) as sql_p95,
       PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY java_time_ms) as java_p95
FROM migration.function_log
GROUP BY function_name;

-- Dual-write replay status
SELECT function_name,
       COUNT(*) FILTER (WHERE processed) as replayed,
       COUNT(*) FILTER (WHERE NOT processed) as pending,
       COUNT(*) FILTER (WHERE processed AND (replay_result->>'match')::boolean = false) as mismatches
FROM migration.function_execution_log
WHERE created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;
```

### Alerts

- Match rate drops below 99.9% -> page on-call
- Java p95 exceeds tier budget -> warning
- Mismatch on critical function (e.g., `C_Invoice_Open`) -> immediate alert
- Circuit breaker tripped > 3 times in 1 hour -> warning (sample rate too high)
- Replay backlog > 10,000 entries -> warning

### Cleanup After Migration

When all functions are migrated and stable:
```sql
DROP SCHEMA migration CASCADE;
```

---

## 7. Shadow Mode Infrastructure

### 7.1 Core Classes

```
org.compiere.migration/
+-- ShadowExecutor.java      -- Orchestrates shadow execution
+-- DualWriteLogger.java     -- Async logging for dual-write mode
+-- MigrationConfig.java     -- Reads function_config table
+-- MigrationLogger.java     -- Async logging to function_log
+-- ResultComparator.java    -- Compares SQL vs Java results
+-- SqlFunctionCaller.java   -- Calls legacy SQL functions via JDBC
+-- CircuitBreaker.java      -- Protects shadow mode under load
+-- ReplayExecutor.java      -- Background replay for dual-write
+-- RequestContext.java      -- Correlation ID and request cache
+-- InputSnapshot.java       -- Captures input state for shadow comparison
```

### 7.1.1 Input Snapshot Strategy

**Problem:** Shadow SQL executes in a separate connection and cannot see uncommitted modifications from the Java execution within the same transaction.

**Solution:** Capture all input data at shadow entry point before Java execution.

**Implementation:**
1. Before Java executes, capture snapshot of all inputs:
   - Primitive parameters (already captured)
   - Referenced entity state (getC_Invoice_ID() → read invoice row)

2. Pass snapshot explicitly to both Java and SQL paths

3. For entity-state-dependent functions (invoiceOpen, currencyConvert), capture:
   - Primary key of referenced entities
   - Relevant column values that could change mid-transaction

**Snapshot Structure:**
```java
public class InputSnapshot {
    Map<String, Object> parameters;       // Function parameters
    Map<String, Object> entityState;      // Key entity field values
    Instant capturedAt;
}
```

**Functions Requiring Entity Snapshot:**
- invoiceOpen: allocation amounts, invoice totals
- currencyConvert: exchange rates
- productQty: inventory quantities

**Trade-off:** Some functions may have large entity state. For these, limit snapshot to fields actually used by the function (determined during function analysis).

**Excluded from Shadow Validation:** Functions that depend on writes made earlier in the same Java method call (circular dependency). These use dual-write instead.

### ShadowExecutor Pattern

```java
public class ShadowExecutor<T> {

    public T execute(String functionName,
                     Supplier<T> javaPath,
                     Supplier<T> sqlPath,
                     BiPredicate<T, T> comparator) {

        MigrationConfig config = MigrationConfig.get(functionName);

        if ("SQL_ONLY".equals(config.mode)) {
            return sqlPath.get();
        }

        // Time Java execution
        long javaStart = System.nanoTime();
        T javaResult = javaPath.get();
        long javaTime = (System.nanoTime() - javaStart) / 1_000_000;

        if ("JAVA_ONLY".equals(config.mode)) {
            return javaResult;
        }

        if ("DUAL_WRITE".equals(config.mode)) {
            // Log for offline replay, no SQL execution
            DualWriteLogger.logAsync(functionName, config.correlationId,
                                      inputs, intermediates, javaResult, javaTime);
            return javaResult;
        }

        // SHADOW mode - check circuit breaker first
        if (!CircuitBreaker.allowShadow(functionName)) {
            return javaResult; // Circuit open, skip shadow
        }

        // Apply sampling for high-frequency functions
        if (!shouldSample(config.sampleRate)) {
            return javaResult; // Skip shadow comparison this call
        }

        // Run SQL with transaction isolation
        long sqlStart = System.nanoTime();
        T sqlResult = executeSqlWithIsolation(sqlPath);
        long sqlTime = (System.nanoTime() - sqlStart) / 1_000_000;

        // Compare and log async
        boolean match = comparator.test(javaResult, sqlResult);
        MigrationLogger.logAsync(functionName, javaResult, sqlResult,
                                  javaTime, sqlTime, match);

        // Update circuit breaker metrics
        CircuitBreaker.recordExecution(functionName, javaTime);

        return javaResult;  // Always return Java in shadow mode
    }

    private boolean shouldSample(double sampleRate) {
        return ThreadLocalRandom.current().nextDouble() < sampleRate;
    }
}
```

### Sampling-Based Shadow Execution

**Problem:** Running both Java and SQL for every call doubles latency. For high-frequency functions like `currencyConvert` (called 1000s of times per transaction), this is unacceptable.

**Solution:** Sample-based shadow execution for standard functions; dual-write for high-frequency critical functions.

| Function Frequency | Strategy | Rationale |
|--------------------|----------|-----------|
| Critical/High (>1000 calls/day) | Dual-Write Logging | Zero production impact, 100% coverage |
| Standard (100-1000 calls/day) | Shadow at 10% | Balance of coverage and performance |
| Low (<100 calls/day) | Shadow at 100% | Full coverage, negligible overhead |

**Configuration:** `migration.function_config.sample_rate` column (0.01 = 1%, 1.0 = 100%)

**Statistical Confidence:** At 10% sampling with 1,000 daily calls, we get 100 shadow comparisons/day - sufficient to detect a 1% mismatch rate with 95% confidence within 3 days.

### Transaction Isolation for Shadow Comparisons

**Problem:** Shadow mode executes Java then SQL sequentially. If another transaction modifies data between executions, the comparison is invalid, causing spurious mismatches.

**Solution:** Execute SQL shadow call with `REPEATABLE READ` isolation.

```java
private <T> T executeSqlWithIsolation(Supplier<T> sqlPath) {
    Connection conn = null;
    int originalIsolation = -1;
    try {
        conn = DB.getConnection();
        originalIsolation = conn.getTransactionIsolation();
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        return sqlPath.get();
    } finally {
        if (conn != null && originalIsolation != -1) {
            conn.setTransactionIsolation(originalIsolation);
        }
    }
}
```

**Alternative:** For functions where isolation is impractical, capture input data snapshot before Java execution and pass explicitly to SQL call.

**Expected False Positive Rate:** <0.1% from race conditions with `REPEATABLE READ`. Spurious mismatches logged with `mismatch_reason = 'POSSIBLE_RACE'` when timestamps differ.

### 7.2 Shadow Connection Pool

**Problem:** Shadow execution calls `DB.getConnection()` per comparison. For high-frequency functions with sampling, this saturates the main connection pool.

**Solution:** Dedicated connection pool for shadow executions with strict limits.

**Configuration:**
```java
// Shadow-specific pool (HikariCP or similar)
HikariConfig shadowConfig = new HikariConfig();
shadowConfig.setMaximumPoolSize(5);           // Hard limit
shadowConfig.setConnectionTimeout(100);        // Fail fast (ms)
shadowConfig.setPoolName("shadow-validation");
```

**Behavior When Pool Exhausted:**
- Shadow execution skipped (not queued)
- Increment `migration.shadow.pool_exhausted` counter
- Java result returned immediately
- Log: "Shadow pool exhausted, skipping comparison for {function}"

**Global Rate Limiting:**
In addition to per-function sampling, enforce global shadow throughput:

| Metric | Limit | Action When Exceeded |
|--------|-------|---------------------|
| Concurrent shadow executions | 5 | Skip new shadow calls |
| Shadow calls per second | 100 | Probabilistic skip |
| Shadow queue depth | 50 | Circuit breaker opens |

**Updated ShadowExecutor:**
```java
private static final Semaphore shadowPermits = new Semaphore(5);

private <T> T executeSqlWithIsolation(Supplier<T> sqlPath) {
    if (!shadowPermits.tryAcquire()) {
        poolExhaustedCounter.increment();
        return null;  // Signal to skip comparison
    }
    try {
        // Use dedicated shadow pool
        Connection conn = shadowDataSource.getConnection();
        // ... existing logic
    } finally {
        shadowPermits.release();
    }
}
```

**Monitoring:** Alert if `pool_exhausted` exceeds 1000/hour (indicates sample rate too high).

### 7.3 Serialization Error Handling

**Problem:** PostgreSQL REPEATABLE READ throws `40001` (serialization_failure) on write conflicts. Shadow execution should be read-only, but defensive handling needed.

**Implementation:**
```java
private <T> T executeSqlWithIsolation(Supplier<T> sqlPath) {
    if (!shadowPermits.tryAcquire()) {
        poolExhaustedCounter.increment();
        return null;
    }
    try {
        Connection conn = shadowDataSource.getConnection();
        conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        return sqlPath.get();
    } catch (SQLException e) {
        if ("40001".equals(e.getSQLState())) {
            // Serialization conflict - skip this comparison
            serializationSkipCounter.increment();
            log.debug("Serialization conflict in shadow for {}, skipping",
                      currentFunctionName.get());
            return null;  // Signal to skip comparison
        }
        throw new RuntimeException("Shadow SQL execution failed", e);
    } finally {
        shadowPermits.release();
    }
}
```

**Metrics:**
- `migration.shadow.serialization_skips` - count of skipped comparisons
- Alert threshold: > 100/hour indicates shadow may be causing write conflicts

**Investigation Trigger:** If serialization skips are high, audit the SQL function for unintended writes (should be SELECT-only).

### 7.4 Usage in Model Class

```java
public BigDecimal getOpenAmt() {
    return ShadowExecutor.execute(
        "C_Invoice_Open",
        () -> calculateOpenAmtJava(),           // Java implementation
        () -> SqlFunctionCaller.invokeOpen(id), // SQL function
        (j, s) -> j.compareTo(s) == 0           // Comparator
    );
}
```

---

## 8. Migration Workflow Per Function

**Per-function migrations follow the step3.md template** (`~/sprocfw/step3.md`), which provides a comprehensive 16-section checklist with decision tree.

### Summary View (maps to step3.md sections)

```
PHASE 1: Discovery & Planning
+-- Complete Decision Tree (Section 0)
|   +-- Generates customized section checklist
+-- Document Procedure Information (Section 1)
+-- Transaction Unit Gate (Section 2)
+-- Pattern Selection (Section 3)
+-- Latency Assessment if >=3 internal calls (Section 4)

PHASE 2: Implementation
+-- Implementation (Section 5)
|   +-- Repository -> Domain -> Service -> Mapper
+-- Session State if package variables (Section 6)
+-- Function Migration specifics (Section 8)
+-- Transaction Mapping (Section 9)
+-- Testing Strategy (Section 15)

PHASE 3: Validation (Shadow or Dual-Write)
+-- Shadow/Dual-Write setup (Section 10)
+-- Feature Flags configuration (Section 12)
+-- Quality Gates monitoring (Section 13)
+-- Rollback Architecture ready (Section 14)

PHASE 4: Cutover
+-- Cutover & Post-Migration (Section 16)
    +-- Set use-java: true
    +-- Monitor 24h with validation still on
    +-- Disable validation after 7 days stable
    +-- Delete SQL function after 30 days

PHASE 5: Cleanup
+-- Remove shadow/dual-write code
+-- Remove feature flags
+-- Archive migration document
```

### Sections Likely Skipped for ADempiere Functions

- Section 6 (Session State) - unless function uses package variables
- Section 7 (Trigger Migration) - we're doing functions, not triggers
- Section 11 (Cross-Database) - PostgreSQL-only

### Template Location

`~/sprocfw/step3.md`

---

## 9. Risks and Mitigations

| Risk | Likelihood | Impact | Mitigation |
|------|------------|--------|------------|
| Java/SQL divergence in existing duplicates | HIGH | HIGH | Validation with SQL as source of truth; fix Java to match |
| Performance regression | MEDIUM | HIGH | Baseline capture, tiered budgets, optimize before validation |
| View dependencies break during migration | MEDIUM | MEDIUM | Migrate function + dependent views as transaction unit |
| Shadow mode logging impacts production | LOW | MEDIUM | Async logging with batching; circuit breaker; separate schema |
| Match rate never reaches 99.9% | LOW | HIGH | Investigate mismatches early; configurable tolerances |
| Rollback needed after SQL deletion | LOW | HIGH | Rollback drill validates procedure before cutover |
| Transaction semantics differ Java vs SQL | MEDIUM | HIGH | Follow step3.md Section 9 transaction mapping |
| N+1 patterns in Java implementation | MEDIUM | MEDIUM | Latency assessment (Section 4) catches before validation |
| Dual-write replay backlog grows | LOW | MEDIUM | Monitor replay lag; scale replay executor if needed |
| Circuit breaker thrashing | LOW | LOW | Adjust sample rate; tune thresholds |

### 9.1 Key Trade-off Decisions

**Trade-off 1: Validation Confidence vs. Production Risk**

| Strategy | Confidence | Production Impact |
|----------|------------|-------------------|
| Shadow (100%) | Highest | +50-100% latency |
| Shadow (sampled) | High | +5-10% latency |
| Dual-write | High | Zero latency (delayed detection) |

**Decision Matrix - When to Switch Strategy:**

| Trigger | Action |
|---------|--------|
| Shadow latency exceeds tier budget | Reduce sample rate to 1% |
| Sample rate at 1% still exceeds budget | Switch to dual-write |
| Circuit breaker trips > 3x/hour | Switch to dual-write |
| Dual-write replay backlog growing | Scale replay executor, not strategy change |

**Escalation:** If function cannot validate under any strategy, escalate to architecture review before proceeding.

---

**Trade-off 2: Rollback Granularity vs. Dependency Coupling**

**Problem:** Rolling back Wave 1 requires rolling back dependent Waves 2-3, creating all-or-nothing scenarios.

**Mitigation:** Retain SQL functions in git after JAVA_ONLY cutover.

| Phase | SQL Function Status |
|-------|---------------------|
| JAVA_ONLY cutover | SQL deleted from database, retained in git |
| Dependent wave stable (7 days) | SQL remains in git |
| All dependent waves complete | SQL moved to `archive/` directory |
| 90 days post-migration | SQL deleted from git |

**Rollback with Retained SQL:**
```bash
# Restore single function without full wave rollback
git show HEAD:db/ddlutils/postgresql/functions/C_Currency_Convert.sql | psql
UPDATE migration.function_config SET mode = 'SQL_ONLY' WHERE function_name = '...';
```

**Benefit:** Granular rollback possible even after JAVA_ONLY, as long as SQL source is retained in git.

---

## 10. Decision Summary

| Decision | Choice |
|----------|--------|
| Target state | PostgreSQL-primary, functions in Java |
| **Validation strategy** | **Hybrid: dual-write for high-risk, shadow for standard** |
| Stateful functions (TU-009) | Dual-write logging with replay validation (no staged rollout) |
| High-frequency critical (Wave 1) | Dual-write logging (zero production latency impact) |
| Standard functions (Waves 2-4) | Shadow mode with sampling (1-100%) |
| BOM functions (Wave 5) | Shadow mode at 100% with recursion safeguards |
| Shadow mode protection | Circuit breaker (80% queue depth → disable) |
| Views | Hybrid: keep simple, migrate those with function deps |
| View performance gates | Tiered: Transactional (30%), Reporting (100%), Batch (200%) |
| MInvoice.getOpenAmt() divergence | Enhance Java to match SQL (complete cache invalidation) |
| Rollback | Git rollback; **drill required before Wave 1** |
| Function + view migration | Together as transaction unit |
| Migration order | Leaf-first per dependency graph (see `docs/discovery/`) |
| Code location | Model classes (M*) following existing patterns |
| Existing duplicates | Re-evaluate via shadow/dual-write, SQL is source of truth |
| Feature flags | Database-backed (migration schema) |
| Performance budgets | Tiered: Critical (5%), Standard (30%), Reporting (100%) |
| Match rate tolerances | Configurable per-function (JSON in function_config) |
| Sample rate | Hot-reloadable (60s TTL cache) |
| BOM safeguards | Depth 100, circular detection, 10K cache limit |
| Logging | Async with bounded queue (10K), circuit breaker protection |
| Per-function template | ~/sprocfw/step3.md |
| Shadow transaction handling | Input snapshot capture (not shared connection) |
| Stateful function replay | Pattern validation + sequence state capture |
| Shadow connection pool | Dedicated pool (max 5) with global rate limiting |
| Replay executor redundancy | Partitioned by function, health monitoring |
| Cache strategy | Request-scoped with transaction boundary invalidation |
| BOM cache scope | JVM-wide Caffeine cache (100K entries max) |
| Correlation ID | ThreadLocal, generated at request entry |
| Migration schema fallback | Default to SQL_ONLY if unavailable |
| SQL retention post-cutover | Kept in git until dependent waves complete |
| Low-frequency escape hatch | Trigger-based logging for Wave 4-5 if needed |

---

## 11. Design Clarifications

### Q1: Disaster Recovery for Migration Schema

**Fallback Behavior:** If `migration.function_config` is unavailable, default to `SQL_ONLY`.

**Implementation:**
```java
public static MigrationConfig get(String functionName) {
    try {
        return loadFromDatabase(functionName);
    } catch (Exception e) {
        log.error("Migration config unavailable, defaulting to SQL_ONLY", e);
        return MigrationConfig.sqlOnly(functionName);
    }
}
```

**Rationale:** SQL functions are the known-working baseline. Failing to Java during infrastructure issues compounds problems.

### Q2: Total Migration Duration

**Estimate:** 4-6 months across all 6 waves.

| Wave | Duration | Cumulative |
|------|----------|------------|
| 0: Foundation | 2 weeks | 2 weeks |
| 1: Currency | 3 weeks | 5 weeks |
| 2: Invoicing | 4 weeks | 9 weeks |
| 3: Financial | 4 weeks | 13 weeks |
| 4: Misc | 3 weeks | 16 weeks |
| 5: BOM | 4 weeks | 20 weeks |

**Infrastructure Maintenance:** Shadow/dual-write infrastructure maintained for full duration plus 30-day stabilization period.

### Q3: Replay Database Resource Allocation

**Decision:** Use existing non-production PostgreSQL instance (staging/QA).

- No new infrastructure required
- Logical replication from production already exists for QA refresh
- Replay jobs run during off-peak hours to avoid QA contention

### Q4: 99.9% Match Rate Escape Hatch

**Decision Process:**
1. Investigate all mismatches (usually reveals undocumented SQL edge cases)
2. If SQL behavior is correct → fix Java
3. If SQL behavior is buggy → document deviation, fix Java to correct behavior
4. If match rate plateaus at 99.5%+ → extend validation 2 more weeks
5. If fundamental incompatibility → escalate to architecture review

**Never:** Accept known divergence without documentation and sign-off.

### Q5: Distributed Transaction Awareness

**ADempiere Status:** No XA/two-phase commit in standard ADempiere.

- All transactions are single-database
- Shadow/dual-write modes don't participate in distributed transactions
- If custom XA extensions exist, they require separate analysis

### Q6: Performance Test Data Representativeness

**Requirement:** Baselines captured against production-scale data.

| Method | Data Source |
|--------|-------------|
| Development | Anonymized production snapshot (monthly refresh) |
| Staging | Full production clone (weekly refresh) |
| Baseline capture | Staging environment with production data volumes |

**Validation:** Before Wave 1, verify staging data volumes within 10% of production for key tables (C_Invoice, C_Order, C_Payment).

---

## Appendix A: File Locations Reference

| Resource | Path |
|----------|------|
| PostgreSQL functions | `db/ddlutils/postgresql/functions/` |
| PostgreSQL views | `db/ddlutils/postgresql/views/` |
| Model classes | `base/src/org/compiere/model/` |
| Database adapters | `base/src/org/compiere/db/` |
| Migration framework (principles) | `~/sprocfw/step1.md` |
| Migration framework (per-function template) | `~/sprocfw/step3.md` |
| Discovery artifacts | `docs/discovery/` |

## Appendix B: Sample Functions by Complexity

| Function | Complexity | Validation Strategy |
|----------|------------|---------------------|
| `addDays.sql`, `trunc.sql` | Simple | Shadow (100%) |
| `C_Currency_Convert.sql` | Medium | Dual-Write (high-frequency) |
| `C_Invoice_Open.sql` | Complex | Shadow (10%) |
| `nextID.sql` | Critical | Dual-Write (stateful) |
| `bomQtyOnHand.sql` | Complex | Shadow (100%) with recursion safeguards |

## Appendix C: Review History

| Version | Date | Changes |
|---------|------|---------|
| v1 | 2026-01-01 | Initial design |
| v2 | 2026-01-01 | Added discovery package, stateful handling, divergence resolution |
| v3 | 2026-01-02 | Adopted hybrid validation (dual-write + shadow), added circuit breaker, BOM safeguards, complete cache invalidation, rollback drill, view performance gates, configurable tolerances |
| v4 | 2026-01-02 | Added per Critical Review v4: input snapshot strategy for shadow transaction handling, sequence state capture for stateful replay, dedicated shadow connection pool with global rate limiting, replay executor health monitoring and partitioning, request-scoped cache decision, JVM-wide BOM cache with Caffeine, correlation ID propagation, serialization error handling, log retention policy, design clarifications (Q1-Q6), key trade-off documentation, trigger-based escape hatch for low-frequency functions |
