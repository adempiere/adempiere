# Critical Architectural Review v5

**Document Reviewed:** `2026-01-01-postgresql-function-migration-design.md` (v4)
**Reviewer:** Principal Software Architect
**Date:** 2026-01-02

---

## 1. Overall Assessment

**Strengths:**
- Mature, well-iterated design addressing feedback from 4 prior reviews
- Sophisticated hybrid validation strategy (dual-write for high-risk, shadow for standard) balances confidence vs. production impact
- Strong dependency analysis with transaction units preventing broken intermediate states
- Comprehensive monitoring, circuit breakers, and fallback mechanisms
- Clear quality gates with specific thresholds (99.9% match, 7-day stability)

**Major Concerns:**
- **Operational complexity is now the primary risk** — the design introduces 10+ new infrastructure components (replay executors, shadow pools, circuit breakers, correlation tracking, bounded queues, etc.) that must all work correctly during a 4-6 month migration window
- **Infrastructure failure modes are under-specified** — what happens when replay executor, shadow pool, AND circuit breaker have issues simultaneously?
- **Long-running batch job handling is ambiguous** — request-scoped cache assumes HTTP request boundaries, but ADempiere has significant batch processing

---

## 2. Critical Issues

### 2.1 Infrastructure Complexity as Primary Risk Vector

**Description:** The design has evolved to include approximately 15 infrastructure components that must function correctly in concert:
- ShadowExecutor, DualWriteLogger, MigrationConfig
- CircuitBreaker, ReplayExecutor (2-3 instances, partitioned)
- RequestContext, RequestCache, InputSnapshot
- Shadow connection pool (dedicated, 5 max)
- Bounded queue (10K), async logging
- Replay database with logical replication
- Health monitoring tables, cleanup jobs

**Why It Matters:** Each component adds failure modes. The combination creates emergent complexity that's difficult to test. A subtle bug in any component could cause:
- False confidence (mismatches not detected)
- Production incidents (shadow execution affecting main path)
- Migration stalls (cannot proceed due to infrastructure issues)

**Suggestion:**
1. **Add infrastructure dependency diagram** showing how these components interact and what happens when each fails
2. **Define "migration infrastructure health" composite metric** that gates progression (if infrastructure unhealthy, pause migration advancement)
3. **Create simplified fallback mode** — if infrastructure is degraded, all functions fall back to SQL_ONLY until repaired, with alerting

---

### 2.2 Batch Processing Request Boundary Ambiguity

**Description:** Section 2.3.1 introduces request-scoped cache with "Transaction Boundary Hook" calling `RequestCache.clear()` in `Trx.close()`. However, ADempiere batch processing often involves:
- Single transaction spanning thousands of records
- Long-running transactions (30+ minutes)
- Nested transactions (`Trx.start()` within existing `Trx`)

**Why It Matters:**
- Within a single batch transaction, cached values could become stale if allocations/payments are created that affect `getOpenAmt()` of previously processed invoices
- The invoice processing order becomes semantically significant (A before B may produce different results than B before A)

**First Anticipated Component to Fail:** The request-scoped cache in long-running batch jobs processing interrelated invoices.

**Suggestion:**
1. **Define explicit batch processing mode** — either disable caching for batch contexts, or implement explicit invalidation APIs callable from batch code
2. **Add cache scope configuration per execution context**: `BATCH` (no caching), `REQUEST` (current design), `TRANSACTION` (clear on commit/rollback)
3. **Integration test requirement:** Multi-invoice batch with cross-invoice allocations verifying correct open amounts

---

### 2.3 Input Snapshot Memory Pressure Unbudgeted

**Description:** Section 7.1.1 introduces `InputSnapshot` for shadow validation, capturing entity state before Java execution. However:
- No size limit defined per snapshot
- No aggregate memory budget for concurrent snapshots
- Functions like `invoiceOpen` may reference large entity graphs

**Why It Matters:** Under high concurrent load, snapshot creation could cause memory pressure, triggering GC pauses that affect production latency — exactly the scenario shadow mode should avoid impacting.

**Suggestion:**
1. **Add snapshot size limit** (e.g., 50KB serialized per snapshot)
2. **Add concurrent snapshot budget** (e.g., 100 snapshots max in-flight)
3. **If limits exceeded:** Skip shadow comparison for that call (similar to circuit breaker behavior)
4. **Monitor:** `migration.shadow.snapshot_size_bytes`, `migration.shadow.snapshot_skipped_size`

---

### 2.4 Replay Database Resource Contention

**Description:** Q3 states replay uses "existing non-production PostgreSQL instance (staging/QA)" with replay jobs running "during off-peak hours."

**Why It Matters:**
- Dual-write replay is continuous, not just off-peak (Section 2.1 shows 5-15 minute detection latency SLA)
- QA environment is actively used for testing — replay may interfere with QA results
- If QA is refreshed weekly, replay may lose in-progress validations

**Suggestion:**
1. **Dedicate replay database** — either separate instance or isolated schema with its own connection pool quota
2. **Define replay job resource limits** (CPU, connections) to bound impact on other QA activities
3. **Handle QA refresh gracefully** — pause replay before refresh, resume after, mark affected period in monitoring

---

### 2.5 Correlation ID ThreadLocal Lifecycle

**Description:** Section 6.1 uses `ThreadLocal` for correlation ID, with `clear()` in request filter exit.

**Why It Matters:** In ADempiere contexts:
- Async processing (scheduled jobs, message handlers) may not go through web filter
- Thread pools reuse threads — stale correlation IDs could pollute logs
- Nested async calls lose correlation context

**Suggestion:**
1. **Use `InheritableThreadLocal`** for correlation propagation to child threads
2. **Add correlation ID to existing `Env.getContext()` pattern** if available, for consistency
3. **Explicit correlation setter for non-web entry points**: `RequestContext.initBatch(jobId)`, `RequestContext.initScheduled(taskId)`
4. **Use `try-finally` pattern** in all entry points to guarantee cleanup

---

## 3. Alternative Architectural Challenge

### Change Data Capture (CDC) with Debezium Instead of Dual-Write Logging

**Description:** Instead of custom dual-write logging infrastructure, use PostgreSQL logical replication with Debezium to capture function invocation patterns:

```
Production Database
    → Logical replication slot
    → Debezium connector
    → Kafka topic (function_calls)
    → Consumer: replay against Java
    → Compare outputs
```

**Pros:**
- Eliminates custom DualWriteLogger, ReplayExecutor, bounded queue, health monitoring
- Zero application code changes for logging (database handles it)
- Battle-tested infrastructure (Debezium widely used)
- Captures ALL SQL function calls, not just instrumented ones
- No production latency impact

**Cons:**
- Requires Kafka infrastructure (new operational dependency)
- CDC captures result sets, not intermediate calculations
- More complex initial setup
- May capture more data than needed (all function calls, not just migration targets)

**Verdict:** The current custom approach is reasonable given ADempiere's likely infrastructure constraints. CDC would be preferable for organizations already running Kafka. Document this as a future option if dual-write operational burden becomes significant.

---

## 4. Minor Issues & Improvements

### 4.1 Missing Chaos/Failure Testing Plan
The design mentions rollback drills but lacks systematic infrastructure failure testing:
- What if replay executor crashes mid-batch?
- What if shadow pool exhausts during peak load?
- What if circuit breaker flaps repeatedly?

**Suggestion:** Add "Infrastructure Failure Drill" section mirroring rollback drill format.

### 4.2 Shadow Connection Pool Size Justification
The pool is hard-coded at 5 connections. No sizing rationale provided.

**Suggestion:** Document sizing formula based on expected shadow throughput and SQL function execution time.

### 4.3 Caffeine Cache Version Compatibility
Section 2.7.1 uses Caffeine for BOM cache. ADempiere's existing dependencies should be verified.

**Suggestion:** Confirm Caffeine is already in dependency tree or document addition.

### 4.4 Log Retention Archive Compression
Section 6.2 archives mismatches but doesn't specify compression.

**Suggestion:** Use `WITH (autovacuum_enabled = false)` is good; add explicit compression strategy for archive table (pg_dump with compression, or partitioned with old partitions detached).

### 4.5 Sample Rate Statistical Confidence
Section 7.1.1 claims "100 shadow comparisons/day - sufficient to detect a 1% mismatch rate with 95% confidence within 3 days."

**Verification:** For 1% defect rate with n=100/day over 3 days (n=300): P(detecting ≥1 defect) = 1 - (0.99)^300 = 95.1%. Math checks out, but document the formula for future reference.

---

## 5. Questions for Clarification

1. **Batch processing cache semantics:** Is there an existing pattern in ADempiere for cache invalidation within long-running batch jobs? The design should align with existing conventions.

2. **Thread pool usage:** Does ADempiere use thread pools for processing (e.g., async document completion)? If so, the ThreadLocal correlation approach needs adaptation.

3. **Infrastructure monitoring integration:** Will the new metrics (`migration.shadow.*`, `bom.cache.*`) integrate with existing ADempiere monitoring, or is new tooling required?

4. **Replay database sizing:** What's the expected peak log volume, and is the staging/QA database sized to handle the additional load without impacting QA testing?

5. **Downgrade path:** If v4 design proves too complex operationally, is there a defined path to simplify (e.g., revert to 100% shadow mode with higher latency acceptance)?

---

## 6. Final Recommendation

### ⚠️ APPROVE WITH CHANGES

The design is comprehensive and has been significantly strengthened through 4 iterations. The hybrid validation strategy, circuit breakers, and quality gates are well-designed.

**Required Changes Before Implementation:**

| Priority | Change |
|----------|--------|
| **HIGH** | Define batch processing cache semantics (Issue 2.2) — this will affect correctness |
| **HIGH** | Add snapshot memory budget (Issue 2.3) — this will affect production stability |
| **MEDIUM** | Create infrastructure dependency diagram (Issue 2.1) |
| **MEDIUM** | Document replay database isolation strategy (Issue 2.4) |
| **LOW** | Improve correlation ID lifecycle for non-web contexts (Issue 2.5) |

**Validation Gate:** Before Wave 1 begins, conduct a "migration infrastructure health check" drill simulating:
1. Replay executor failure during active validation
2. Shadow pool exhaustion during peak load
3. Circuit breaker thrashing scenario

The design team has done excellent work iterating on this plan. The remaining issues are addressable without fundamental architectural changes.

---

*Review complete. Ready for author response addressing HIGH priority items.*
