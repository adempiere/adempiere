# Critical Architectural Review v4

**Document Reviewed:** `docs/plans/2026-01-01-postgresql-function-migration-design.md`
**Reviewer:** Senior Principal Architect
**Date:** 2026-01-02

---

## 1. Overall Assessment

**Strengths:**
- Well-structured hybrid validation strategy that appropriately differentiates between stateful, high-frequency, and standard functions
- Comprehensive dependency analysis with topological ordering prevents broken intermediate states
- Circuit breaker pattern protects production during shadow mode overload
- Rollback drill requirement before Wave 1 is operationally sound
- Transaction units ensure functions and dependent views migrate atomically

**Major Concerns:**
- Replay database consistency model is underspecified for stateful functions
- Transaction boundary semantics between Java execution and shadow SQL execution are architecturally ambiguous
- Connection pool exhaustion risk from shadow mode's isolation strategy
- Missing failure mode handling for the dual-write replay infrastructure

---

## 2. Critical Issues

### 2.1 Replay Database State Drift (HIGH)

**Problem:** The design specifies the replay environment is "Refreshed via logical replication (< 1 minute lag) or nightly snapshots." For stateful functions like `nextID`, this creates a fundamental problem:

- Production Java generates ID 50,000
- Replay DB sequences are at 49,500 (replication lag or snapshot age)
- Replaying logged inputs against SQL with "reset sequences" doesn't validate **actual** production behavior
- The design claims "replay with sequence reset to verify pattern matching" but sequence position is the entire point of ID generation

**Impact:** False confidence in stateful function validation. A 1-minute replication lag during batch processing could mean hundreds of ID generations are never properly validated.

**Suggestion:** Either:
1. **Record sequence state at execution time** in `FunctionExecutionLog` and reset replay DB sequences to match before each replay batch, OR
2. **Use synchronous validation in non-production replica** that receives production traffic via read replica + write forwarding pattern, OR
3. **Accept that stateful functions cannot be fully validated via replay** and instead rely exclusively on integration test suites with concurrent access patterns

### 2.2 Transaction Boundary Ambiguity in Shadow Mode (HIGH)

**Problem:** The shadow executor calls `executeSqlWithIsolation()` with REPEATABLE READ, but the design doesn't address what happens when:

1. The Java path modifies data within the caller's transaction
2. Shadow SQL executes in a *separate* connection (implied by `DB.getConnection()`)
3. Shadow SQL cannot see uncommitted modifications from the Java execution

**Example Scenario:**
```
Caller starts transaction
  → Java path updates invoice allocation
  → Java calculates getOpenAmt() = $500 (sees uncommitted allocation)
  → Shadow SQL runs in separate connection
  → SQL calculates C_Invoice_Open = $1000 (doesn't see allocation)
  → MISMATCH LOGGED (spurious)
```

**Impact:** The design estimates "<0.1% false positives from race conditions" but this ignores *intra-transaction* modifications, which could be common for invoice/allocation workflows.

**Suggestion:** Either:
1. **Pass the caller's connection to shadow SQL** (requires API change to share transaction), OR
2. **Capture input data snapshot before Java execution** and pass explicitly to SQL (design mentions this as "alternative" but doesn't commit), OR
3. **Document this limitation** and exclude functions called after modifications within the same transaction from shadow validation

### 2.3 Connection Pool Exhaustion Risk (HIGH)

**Problem:** `executeSqlWithIsolation()` calls `DB.getConnection()` for each shadow execution. For functions like `currencyConvert` that run thousands of times per transaction, even with 1% sampling this means:

- 10,000 calls/transaction × 1% sampling = 100 shadow executions
- Each shadow gets a new connection with modified isolation level
- If transactions overlap, connection pool saturates rapidly

**Impact:** Production database connection exhaustion during peak load, causing transaction failures unrelated to the migration.

**Suggestion:** Either:
1. **Reuse the caller's connection for shadow** (eliminates this risk but has transaction isolation implications), OR
2. **Use a dedicated connection pool for shadow executions** with strict size limits (e.g., max 5 connections), OR
3. **Rate-limit shadow executions globally** (not just per-function sampling, but total concurrent shadow calls across all functions)

### 2.4 Replay Executor Single Point of Failure (MEDIUM-HIGH)

**Problem:** The dual-write strategy depends on a background replay executor. The design specifies:
- "Background replay job" that reads unprocessed logs
- "Detection latency: 5-15 minutes"

Missing from the design:
- What happens if the replay executor crashes or hangs?
- How is replay executor health monitored?
- Is there replay executor redundancy (leader election, multiple instances)?
- What is the backlog retention policy if replay can't keep up for days?

**Impact:** If replay executor fails silently, dual-write validation provides no protection. You wouldn't know until a production incident reveals Java/SQL divergence.

**Suggestion:**
1. Add heartbeat monitoring for replay executor (alert if no replays processed in 30 minutes)
2. Define backlog retention: after N days unprocessed, archive to cold storage with alert
3. Consider multiple replay executor instances with partition-based work distribution
4. Add explicit runbook for "replay executor failure" scenario

### 2.5 Cache Strategy Unresolved (MEDIUM)

**Problem:** Section 2.3 lists "Alternative: Request-Scoped Cache" but doesn't make a decision. The field-level cache approach (`private BigDecimal cachedOpenAmt`) is listed with comprehensive invalidation triggers, but:

- No decision is made between field-level vs request-scoped
- The invalidation trigger list is exhaustive but the audit requirement ("grep codebase for all state-changing methods") is manual and error-prone
- Request-scoped cache would eliminate this entire class of bugs but isn't selected

**Impact:** Architectural ambiguity. Developers implementing Wave 3 won't know which pattern to use. Field-level cache with manual invalidation is a long-term maintenance burden.

**Suggestion:** Make an explicit decision. Recommendation: **request-scoped cache** with automatic invalidation at transaction boundary. The complexity of maintaining invalidation hooks across all entity state changes is higher than implementing a request-context cache.

### 2.6 BOM Memory Pressure at Scale (MEDIUM)

**Problem:** BOM safeguards specify "10,000 cached component calculations per request (LRU eviction)." For concurrent requests:

- 50 concurrent BOM calculations × 10,000 entries × ~100 bytes/entry = 50MB per JVM
- No mention of per-JVM global limit
- LRU eviction is per-request, so no cross-request memory protection

**Impact:** Memory pressure during batch processing that calculates BOM for many products simultaneously.

**Suggestion:**
1. Add JVM-wide BOM calculation cache limit (e.g., 100,000 total entries with global LRU)
2. Use WeakHashMap or similar to allow GC pressure to evict
3. Consider Caffeine/Guava cache with size-based eviction

---

## 3. Alternative Architectural Challenge

### Database Trigger-Based Logging (vs. Dual-Write + Shadow)

**Approach:** Instead of instrumenting Java code for dual-write and shadow execution:

1. Add PostgreSQL triggers to existing SQL functions that log inputs/outputs to `migration.function_log`
2. Implement Java functions that run offline against logged inputs
3. Compare results in batch (similar to replay executor)
4. When match rate stable, deploy Java with feature flag directly to JAVA_ONLY

**Flow:**
```
Production: SQL function executes as normal
  → Trigger logs: {function, inputs, outputs, timestamp}

Offline validation job:
  → Read log entries
  → Execute Java function with same inputs
  → Compare outputs
  → Report mismatches

Cutover:
  → Set feature flag to JAVA_ONLY
  → Remove trigger
```

**Primary Pro:** Zero Java code change during validation phase. Production code is unchanged until cutover. No connection pool risks, no transaction boundary issues, no circuit breaker complexity.

**Primary Con:** Trigger overhead on every SQL function call (estimated 1-5ms per call). Cannot capture intermediate calculations from SQL for debugging. Harder to validate Java path under realistic call patterns (e.g., caching behavior, connection reuse).

**Verdict:** The current hybrid approach is superior for high-frequency functions where trigger overhead is unacceptable. However, trigger-based logging could be simpler for Wave 4-5 low-frequency functions. Consider as escape hatch if shadow infrastructure proves too complex.

---

## 4. Minor Issues & Improvements

### 4.1 Missing Two Critical Trade-offs

**Trade-off 1: Validation Confidence vs. Production Risk**
- Shadow mode provides high confidence but adds production latency and connection pressure
- Dual-write provides zero latency impact but delayed detection (5-15 minutes)
- The design assigns strategies per-function but doesn't document the decision matrix for "when to switch from shadow to dual-write if problems emerge"

**Trade-off 2: Rollback Granularity vs. Dependency Coupling**
- Design says "rollback Wave 1 requires rollback Wave 2, 3"
- This creates all-or-nothing rollback scenarios
- Alternative: Maintain SQL functions in git even after JAVA_ONLY cutover; only delete after all dependent waves are stable

### 4.2 Sample Rate Statistical Validity

The design claims "10% sampling with 1,000 daily calls = 100 comparisons, sufficient to detect 1% mismatch with 95% confidence within 3 days."

This math is approximately correct but should be made explicit:
- 300 samples to detect 1% deviation at 95% confidence (binomial calculation)
- 3 days at 100 samples/day = 300 samples
- Consider adding adaptive sampling: increase sample rate if match rate drops below 99.5%

### 4.3 Correlation ID Propagation

The design mentions `correlationId` in log schemas but doesn't specify:
- Where correlation ID originates (request? transaction? generated per-call?)
- How it propagates from Java to SQL to logs
- Whether it connects to ADempiere's existing tracing/logging infrastructure

### 4.4 Index Strategy for Log Tables

The schema includes:
```sql
CREATE INDEX idx_execution_log_unprocessed
ON migration.function_execution_log(function_name, created_at) WHERE NOT processed;
```

This partial index is good, but consider:
- Adding `function_name` partition if log volume is high (separate tables per function)
- Adding retention policy (auto-delete processed logs after 30 days)

### 4.5 REPEATABLE READ Serialization Errors

REPEATABLE READ in PostgreSQL can throw serialization errors on write conflicts. The shadow executor doesn't handle `40001` (serialization_failure). If shadow path triggers a write (shouldn't, but defensive):

```java
} catch (SQLException e) {
    if ("40001".equals(e.getSQLState())) {
        log.debug("Serialization conflict in shadow, skipping comparison");
        return javaResult;
    }
    throw e;
}
```

---

## 5. Questions for Clarification

1. **Disaster Recovery for Migration Schema:** If `migration.function_config` becomes corrupted or unavailable, what is the fallback behavior? Do all functions default to SQL_ONLY or JAVA_ONLY?

2. **Total Migration Duration:** Across all 6 waves, what is the expected calendar duration? This affects how long the shadow/dual-write infrastructure must be maintained.

3. **Replay Database Resource Allocation:** Is there budget for a dedicated PostgreSQL instance for replay? The design mentions "isolated PostgreSQL instance" but doesn't specify whether this is new infrastructure.

4. **99.9% Match Rate Escape Hatch:** If a function cannot achieve 99.9% match rate (e.g., SQL has undocumented edge cases), what is the decision process? Extend validation? Accept deviation? Modify SQL?

5. **Distributed Transaction Awareness:** Does ADempiere use any distributed transactions (XA) or two-phase commit? If so, how do shadow/dual-write modes participate?

6. **Performance Test Data Representativeness:** For tiered performance budgets, are baselines captured with production-representative data volumes? A function that's 5% slower on test data might be 50% slower on real data.

---

## 6. Final Recommendation

**Major Revisions Needed**

The design is comprehensive and well-reasoned, but contains several architectural gaps that could lead to false confidence in validation or production incidents:

### Required Changes Before Wave 1:

| # | Issue | Required Change |
|---|-------|-----------------|
| 1 | Replay database state drift | Document sequence state capture strategy for stateful functions |
| 2 | Transaction boundary ambiguity | Make explicit decision: shared connection vs. snapshot capture |
| 3 | Connection pool risk | Add connection pool protection (dedicated pool or global rate limit) |
| 4 | Replay executor reliability | Add health monitoring and failure runbook |
| 5 | Cache strategy | Decide field-level vs. request-scoped; document in design |

### Recommended Changes (Can Address During Waves 1-2):

| # | Issue | Change |
|---|-------|--------|
| 6 | BOM memory limits | Add JVM-wide cache limit |
| 7 | Correlation ID propagation | Document origin and propagation |
| 8 | Serialization error handling | Add defensive catch in shadow executor |
| 9 | Log retention policy | Define auto-cleanup for processed logs |

Once these issues are addressed, the design provides a solid foundation for migration. The hybrid validation strategy is architecturally sound, and the phased wave approach with transaction units is the correct way to manage this complexity.
