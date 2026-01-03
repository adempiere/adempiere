# Critical Architectural Review: PostgreSQL Function Migration Design

**Reviewed Document:** `docs/plans/2026-01-01-postgresql-function-migration-design.md`
**Review Date:** 2026-01-02
**Review Version:** 3
**Reviewer Focus:** Post-revision validation and identification of residual architectural gaps

---

## 1. Overall Assessment

**Strengths:**
- **Major prior issues resolved:** The design now addresses the three blocking issues from v1/v2 reviews:
  - Stateful function migration strategy with staged rollout (Section 2.1)
  - Function dependency graph via discovery package (Section 2.2)
  - MInvoice.getOpenAmt() divergence with explicit "enhance Java" decision (Section 2.3)
- Sampling-based shadow execution eliminates the latency-doubling concern (1-100% by frequency)
- Transaction isolation with `REPEATABLE READ` addresses spurious mismatch concerns
- Bounded queue with 10K capacity and explicit backpressure prevents OOM
- Tiered performance budgets (5%/30%/100%) differentiate critical vs. reporting functions
- Comprehensive discovery package with 12 transaction units, 6 migration waves, and dead code identification
- Clear per-function workflow via step3.md template reference

**Major Concerns:**
- **First-to-fail component not identified:** Under high load during shadow mode, the logging queue (10K capacity) is the likely bottleneck, but there's no circuit breaker to disable shadow mode before it impacts production
- **Staged rollout for stateful functions has selection bias risk:** Random 10%/50% routing could cause inconsistent behavior within a single business transaction
- **BOM recursion safeguards still underspecified:** Despite being acknowledged in migration-waves.md, depth limits, circular detection, and memory bounds are "success criteria" not "implementation requirements"
- **Cache invalidation scope incomplete:** Only `MAllocationLine.afterSave()` is specified; payment reversals, invoice voiding, and credit memo allocations are missing
- **Rollback testing not validated:** "Git contains SQL functions" assumes untested rollback procedure works

**Key Architectural Recommendation:**
- Adopt a **hybrid validation approach** (see Section 3): use dual-write logging for stateful functions (TU-009) and high-frequency critical functions (Wave 1), while keeping shadow mode for remaining waves. This resolves the first two concerns above for the highest-risk functions with ~4 days additional upfront investment.

---

## 2. Critical Issues

### 2.1 No Circuit Breaker for Shadow Mode Under Load (HIGH - NEW)

**Description:** Shadow mode with 10K bounded queue will drop entries when overwhelmed, but there's no mechanism to disable shadow mode automatically when the system is under stress. Continued shadow execution during traffic spikes consumes CPU/memory for comparisons that won't even be logged.

**Impact:** During peak load (month-end closing, batch processing), shadow mode adds latency without providing validation data. The queue silently drops entries, giving false confidence in match rates.

**Why It Matters:** The design optimizes for steady-state, not for production's worst moments. Shadow mode should degrade gracefully, not compete with production workload.

**Recommendation:**
1. Implement shadow mode circuit breaker: if queue depth > 80% for > 30 seconds, automatically disable shadow comparisons (Java-only execution) until load subsides
2. Add metric: `migration.shadow.circuit_breaker.trips` to track how often this happens
3. Define SLO: shadow mode should not degrade p95 latency by more than 10% during normal operation; if exceeded, circuit breaker triggers
4. Treat tripped circuit breaker as indication that sample rate is too high for that function

### 2.2 Staged Rollout Selection Bias in Stateful Functions (HIGH - NEW)

**Description:** Section 2.1 describes `STAGED_10` as "10% Java, 90% SQL (random selection)". For `nextID`, this means:
- Call 1: Java generates ID 1001
- Call 2: SQL generates ID 1002
- Call 3: Java generates ID 1003
- Within a single business transaction (e.g., creating an Order with 10 Lines), some records get Java-generated IDs, others get SQL-generated IDs.

**Impact:** If there's any subtle difference in the ID generation logic (rounding, sequence caching, transaction isolation), it will manifest as inconsistent gaps within a single business document's child records. Debugging this will be extremely difficult.

**Why It Matters:** ID generation is transactional. Mixing implementations within a transaction violates transactional consistency expectations.

**Recommendation:**
1. **Route by transaction, not by call:** Use transaction ID hash to determine Java/SQL for entire transaction
   ```java
   boolean useJava = (trxName.hashCode() % 100) < percentage;
   ```
2. Or: **Route by client/org:** Roll out Java to specific AD_Client_ID first, validate, then expand
3. Or: **Route by time window:** 10% means Java runs for 6 minutes per hour (easier to reason about)
4. Document the selection mechanism explicitly in the design

### 2.3 BOM Recursion Safeguards Are Success Criteria, Not Requirements (MEDIUM-HIGH - INHERITED)

**Description:** `migration-waves.md` Wave 5b lists as success criteria:
- "No stack overflow with deep BOMs"
- "Handle circular BOM detection (safeguard)"

But these are verification outcomes, not implementation requirements. The design document doesn't specify:
- Maximum depth limit (what number? 50? 100? 1000?)
- Circular detection algorithm (visited set? path tracking?)
- Memory bounds for memoization cache
- Behavior when limits are exceeded (exception? return partial result? log and continue?)

**Impact:** Without explicit requirements, implementation will be inconsistent. Different developers may implement different safeguards (or none).

**Why It Matters:** BOM functions are the most complex in the migration (135-145 LOC each). Underspecification here leads to production incidents.

**Recommendation:**
1. Add to Section 2 or create new Section on BOM Migration:
   - Maximum depth: 100 levels (configurable via AD_SysConfig)
   - Circular detection: HashSet of visited Product_IDs per traversal
   - Memory limit: 10,000 cached component calculations per request (LRU eviction)
   - Exceeded behavior: Log warning, return result for traversed portion, mark result as "partial"
2. Add integration test with pathological BOM data: 50+ deep, circular reference, wide (100+ components at one level)

### 2.4 Cache Invalidation Scope for openAmount Is Incomplete (MEDIUM - NEW)

**Description:** Section 2.3 shows cache invalidation only in `MAllocationLine.afterSave()`:
```java
// In MAllocationLine.afterSave()
MInvoice invoice = getC_Invoice();
if (invoice != null) {
    invoice.invalidateOpenAmtCache();
}
```

But `openAmount` should also be invalidated when:
- Invoice is voided (`MInvoice.voidIt()`)
- Invoice is reversed (`MInvoice.reverseCorrectIt()`)
- Credit memo is allocated against invoice
- Payment is reversed or voided
- Allocation line is deleted (not just saved)

**Impact:** Stale cached `openAmount` values returned after business operations that affect the balance.

**Why It Matters:** This is exactly the kind of divergence that caused the original Java/SQL mismatch. Incomplete cache invalidation will recreate the problem.

**Recommendation:**
1. Enumerate all state transitions that affect invoice open amount:
   - Allocation created/modified/deleted
   - Payment voided/reversed
   - Invoice voided/reversed
   - Credit memo allocated
2. Add `invalidateOpenAmtCache()` calls to all relevant `afterSave()` and `afterDelete()` methods
3. Consider: replace field-level cache with request-scoped cache (invalidates automatically at transaction boundary)

### 2.5 Rollback Procedure Not Validated (MEDIUM - INHERITED)

**Description:** The design states "Rollback: No SQL retention; git rollback if needed". But:
- Git rollback of Java code is straightforward
- Restoring deleted SQL functions requires re-running DDL from git history
- This assumes the DDL path works correctly after codebase changes
- No dry-run validation of this procedure is documented

**Impact:** If rollback is needed in production, the team may discover the procedure doesn't work (migration scripts interfere, function signatures changed, etc.).

**Why It Matters:** Rollback is the safety net for the entire migration. An untested safety net is not a safety net.

**Recommendation:**
1. Before Wave 1 cutover, execute rollback procedure in staging:
   - Simulate: delete SQL function from database
   - Execute: git checkout previous version, run deployment pipeline
   - Verify: SQL function restored and callable
2. Document exact rollback steps in runbook:
   - Which git commit contains SQL functions
   - Command sequence to restore specific functions
   - How to reset feature flags to SQL_ONLY
3. Add "rollback drill" as Gate 2 requirement (before any function enters SHADOW mode)

### 2.6 View Migration Performance Not Gated (MEDIUM - NEW)

**Description:** Section 3 describes converting views to Java query methods using ADempiere's Query class. But there's no performance gate for view migration. The function migration has tiered latency budgets (5%/30%/100%); view migration has none.

**Impact:** A view that performs well in PostgreSQL (query planner optimization, indexed aggregation) could perform terribly as Java stream operations.

**Why It Matters:** Views may be used in reports or dashboards with large datasets. Performance regression here directly impacts user experience.

**Recommendation:**
1. Apply same tiered performance budgets to view migration
2. Before migrating each view:
   - Capture baseline: `EXPLAIN ANALYZE` on PostgreSQL view
   - After migration: benchmark Java query method with same data volume
   - Compare row counts to ensure filter pushdown is correct
3. Add explicit success criterion: Java query method must not read more rows than SQL view would

---

## 3. Alternative Architectural Challenge

### Observability-First Migration via Dual-Write Logging

**Description:** Instead of comparing Java vs SQL results at runtime (shadow mode), instrument both implementations to emit structured logs of their intermediate calculations, then compare logs offline.

#### 3.1 How It Works

**Current Design (Shadow Mode):**
```
Request arrives
    → Execute Java implementation
    → Execute SQL function (same transaction)
    → Compare results synchronously
    → Log mismatch if any
    → Return Java result

Total latency = Java time + SQL time + comparison overhead
```

**Proposed Alternative (Dual-Write Logging):**
```
Request arrives
    → Execute Java implementation
    → Emit structured log: {inputs, intermediates, output, timestamp}
    → Return Java result immediately

Total latency = Java time only

Background replay job (runs continuously or scheduled):
    → Read logged Java executions
    → Replay same inputs against SQL function in isolated environment
    → Compare outputs
    → Report mismatches to dashboard
```

#### 3.2 Detailed Pros

| Advantage | Impact | Explanation |
|-----------|--------|-------------|
| **Zero production performance impact** | HIGH | No SQL execution in request path. Java runs alone. Shadow mode's 1-100% sampling becomes unnecessary - you can validate 100% of calls without latency penalty. |
| **Works uniformly for stateful functions** | HIGH | For `nextID`: log what Java generated, replay in isolated DB with reset sequences to verify SQL would generate same pattern. No dual-execution, no sequence consumption. |
| **Richer diagnostic data** | MEDIUM | Can log intermediate calculations (e.g., "fetched rate 1.25 for EUR→USD on 2026-01-01"), not just final result. Makes root cause analysis faster. |
| **Historical re-validation** | MEDIUM | After fixing a bug, re-run replay against historical logs to confirm the fix would have produced correct results. Shadow mode only validates going forward. |
| **Decoupled validation timeline** | MEDIUM | Replay can run during off-peak hours, using spare capacity. Shadow mode must run during actual traffic. |
| **Graceful degradation** | LOW | If replay infrastructure is down, production is unaffected. Shadow mode failures could impact production. |

#### 3.3 Detailed Cons

| Disadvantage | Impact | Explanation |
|--------------|--------|-------------|
| **Requires replay infrastructure** | HIGH | Must build: log storage, replay executor, isolated database for SQL execution, result comparator, mismatch dashboard. Significant upfront investment. |
| **Delayed feedback** | HIGH | Mismatches discovered minutes/hours after the fact, not immediately. If a bug is introduced, it runs in production before detection. Shadow mode catches issues in real-time. |
| **Data freshness for replay** | MEDIUM | SQL replay needs database state matching what Java saw. Options: (1) snapshot database frequently, (2) log all relevant input data with each call, (3) accept that some replays will be invalid due to state drift. |
| **Storage costs** | MEDIUM | Logging every call with full inputs/outputs generates significant data. For high-frequency functions like `currencyConvert`, could be GBs/day. Requires retention policy and archival. |
| **Security/privacy complexity** | MEDIUM | Production data flows to replay environment. Must ensure same access controls, data masking if needed, audit trails. |
| **Replay environment maintenance** | LOW | Isolated database needs to stay in sync with production schema. Migrations must be applied to both. |

#### 3.4 Quantitative Comparison

| Metric | Shadow Mode (Current) | Dual-Write Logging |
|--------|----------------------|-------------------|
| Production latency impact | +5-100% depending on sample rate | 0% (logging is async) |
| Detection latency | Real-time | Minutes to hours |
| Infrastructure complexity | Low (in-process) | High (separate replay system) |
| Stateful function support | Requires special handling (staged rollout) | Works uniformly |
| Data storage required | ~100 bytes/mismatch | ~1KB/call (full inputs/outputs) |
| Validation coverage | Sample-based (1-100%) | Can be 100% |
| Rollback speed | Immediate (feature flag) | Immediate (feature flag) |

#### 3.5 When Each Approach Is Better

**Dual-Write Logging is better when:**
1. **Performance-critical systems** where any latency increase is unacceptable
2. **High-frequency functions** (millions of calls/day) where sampling reduces confidence
3. **Stateful functions** where dual-execution is fundamentally problematic
4. **Teams with strong observability culture** who already have log aggregation, replay tooling
5. **Long migration timelines** where historical re-validation is valuable

**Shadow Mode is better when:**
1. **Fast feedback is critical** - bugs must be caught before they accumulate
2. **Limited infrastructure budget** - shadow mode is simpler to implement
3. **Short migration timeline** - 4-6 weeks where upfront infrastructure investment doesn't pay off
4. **Low-frequency functions** - sampling at 100% has minimal performance impact
5. **Simple functions** - no intermediate state worth logging

#### 3.6 Recommendation: Hybrid Approach for This Migration

| Function Category | Recommended Strategy | Rationale |
|-------------------|---------------------|-----------|
| **Stateful functions** (TU-009: `nextID`, `nextIDFunc`) | Dual-Write Logging | Shadow mode is fundamentally incompatible. Staged rollout has selection bias issues (Issue 2.2). Logging + replay in isolated environment is the only safe validation path. |
| **High-frequency critical functions** (Wave 1: `currencyConvert`, `currencyRate`) | Dual-Write Logging | Called thousands of times per transaction. Even 1% sampling adds measurable latency at scale. 100% logging with async replay gives full coverage without production impact. |
| **Standard functions** (Wave 2, 3, 4) | Shadow Mode with sampling | Current design is adequate. 10% sampling provides sufficient coverage with acceptable latency. Infrastructure already designed. |
| **BOM functions** (Wave 5) | Shadow Mode at 100% | Low frequency (reporting), complex logic where real-time comparison catches edge cases immediately. |

#### 3.7 Implementation Path for Hybrid Approach

**Phase 1 (before Wave 1):** Build minimal replay infrastructure
- Structured logging format for function calls
- Isolated PostgreSQL instance with production schema
- Simple replay script that reads logs, executes SQL, compares
- Dashboard for mismatch review

**Phase 2 (Wave 1):** Use dual-write logging for currency functions
- Validate infrastructure works at scale
- Tune logging volume, retention

**Phase 3 (Wave 4):** Use dual-write logging for `nextID`
- Special handling: replay with sequence reset
- Verify ID generation patterns match

**Phase 4 (Waves 2, 3, 5):** Continue with shadow mode
- Already designed, lower risk for these categories

#### 3.8 Effort Estimate

Building minimal replay infrastructure: **3-5 days** additional upfront investment

| Component | Effort |
|-----------|--------|
| Log format definition | 0.5 days |
| Replay executor | 1-2 days |
| Isolated database setup | 0.5 days |
| Comparator + dashboard | 1-2 days |

**Offset by eliminating:**
- Staged rollout complexity for stateful functions
- Circuit breaker implementation for shadow mode (for functions using dual-write)
- Higher confidence in high-frequency function validation

**Net assessment:** Invest in dual-write logging for TU-009 (stateful) and Wave 1 (currency). Keep shadow mode for remaining waves. Total additional effort ~4 days, with better validation coverage for the highest-risk functions.

---

## 4. Minor Issues & Improvements

### 4.1 Match Rate Exclusions Remain Hardcoded
Section 5 lists specific tolerance rules (timestamps within 1 second, floating point precision, null vs empty string). These should be stored in `migration.function_config` as configurable per-function, not hardcoded. Some functions may need tighter or looser tolerances.

### 4.2 Sample Rate Configuration Timing Unspecified
`migration.function_config.sample_rate` determines shadow execution frequency. But when is this configured? Before shadow mode starts? Can it be adjusted during shadow mode? Hot-reload capability is important for tuning.

### 4.3 Cross-Wave Rollback Dependencies Not Addressed
If Wave 3 (Financial Core) fails and must rollback, but Wave 4 (Standalone) has already completed, is Wave 4 affected? The design doesn't address cross-wave dependency during rollback scenarios.

### 4.4 Performance Baseline Capture Timing Unspecified
Section 5 mentions "Baseline captured before shadow mode begins" but doesn't specify:
- Under what load conditions? (Peak vs off-peak matters)
- For how long? (1 minute vs 1 hour of sampling)
- How many samples? (Statistical significance)

### 4.5 `documentNo` Missing from Stateful Function List
Section 2.1 mentions "TU-009 (`nextID`, `nextIDFunc`) and `documentNo`" but the Feature Flag table only shows flags for `SQL_ONLY`, `STAGED_10`, `STAGED_50`, `JAVA_ONLY`. Is `documentNo` officially in the stateful category or not? It's listed in TU-012 (Miscellaneous Standalone), not TU-009.

### 4.6 Discovery Artifact Lifecycle Undefined
The `docs/discovery/` folder contains analysis artifacts (8 files). Post-migration:
- Are these archived, deleted, or retained?
- Do they need updating as migration progresses?
- Who owns maintenance of these documents?

---

## 5. Questions for Clarification

1. **Hybrid approach decision:** Is the team willing to invest 3-5 days upfront in dual-write logging infrastructure to get better validation for stateful and high-frequency functions? Or is the preference to stay with pure shadow mode and address Issues 2.1/2.2 through circuit breakers and transaction-based routing?

2. **Circuit breaker ownership:** Who monitors queue depth during shadow mode? Is there a dedicated dashboard or does it rely on existing APM?

3. **Staged rollout selection mechanism:** Is the 10%/50% random per-call, per-transaction, or per-tenant? What ensures consistency within a business transaction?

4. **BOM depth limits:** What is the maximum expected BOM depth in production data? Has anyone measured this? The safeguard limit should be based on actual data characteristics.

5. **Cache invalidation completeness:** Has anyone audited all code paths that modify allocation/payment data to ensure cache invalidation is called? Is there a risk of missing a path?

6. **Rollback drill schedule:** Will the rollback procedure be validated before Wave 1 cutover? Who is responsible for this validation?

7. **View performance acceptance criteria:** Is there a specific latency budget for migrated views, or is it "best effort"?

8. **Shadow mode during maintenance windows:** Should shadow mode be disabled during known batch processing windows (month-end, overnight jobs) to avoid competing for resources?

9. **Replay environment data:** If adopting dual-write logging, how will the replay database be kept in sync with production? Nightly snapshots? Logical replication? What's the acceptable data staleness?

---

## 6. Final Recommendation

**Approve with changes.**

The design has matured significantly and now addresses the three previously blocking issues. The remaining gaps are refinements rather than fundamental architectural flaws. Additionally, adopting the **hybrid validation approach** (Section 3.6) would resolve Issues 2.1 and 2.2 for the highest-risk functions while keeping the existing shadow mode infrastructure for standard functions.

### Required Before Wave 1 Cutover (Blocking)

1. **Adopt hybrid validation approach** (Section 3.6)
   - Use dual-write logging for TU-009 (stateful: `nextID`, `nextIDFunc`) and Wave 1 (currency: `currencyConvert`, `currencyRate`)
   - This eliminates Issue 2.1 (circuit breaker) and Issue 2.2 (selection bias) for these high-risk functions
   - Keep shadow mode for Waves 2, 3, 4, 5
   - Effort: ~4 days upfront for replay infrastructure

2. **If hybrid approach is rejected:** Implement circuit breaker for shadow mode (Issue 2.1)
   - Define threshold (80% queue depth for 30s), behavior (disable shadow), monitoring
   - Required only if staying with pure shadow mode for all functions

3. **If hybrid approach is rejected:** Specify staged rollout selection mechanism (Issue 2.2)
   - Route by transaction hash, not per-call random
   - Required only if using staged rollout for stateful functions

4. **Validate rollback procedure** (Issue 2.5)
   - Execute end-to-end rollback drill in staging
   - Document runbook before any function enters SHADOW or dual-write logging

### Required Before Wave 5 (BOM Functions)

5. **Specify BOM recursion safeguards as requirements** (Issue 2.3)
   - Depth limits, circular detection, memory bounds
   - Exceeded behavior (exception vs partial result)

### Recommended (High Priority)

6. Complete cache invalidation audit for openAmount (Issue 2.4)
7. Add performance gates for view migration (Issue 2.6)
8. Make match rate tolerances configurable per-function (Issue 4.1)
9. Define cross-wave rollback dependencies (Issue 4.3)

---

## Summary of Status Changes from Prior Reviews

| Prior Issue | Status in v3 | Notes |
|-------------|--------------|-------|
| Stateful functions incompatible with shadow mode | **RESOLVED** | Section 2.1 defines staged rollout strategy |
| Missing function dependency graph | **RESOLVED** | Discovery package complete |
| MInvoice.getOpenAmt() divergence | **RESOLVED** | Decision: enhance Java, requirements documented |
| Shadow mode doubles latency | **RESOLVED** | Sampling-based execution (1-100%) |
| No transaction isolation for shadow | **RESOLVED** | REPEATABLE READ specified |
| Performance gate undifferentiated | **RESOLVED** | Tiered budgets (5%/30%/100%) |
| Unbounded logging queue | **RESOLVED** | Bounded queue with 10K capacity |
| BOM recursion safeguards | **PARTIAL** | Acknowledged but not specified as requirements |
| Rollback procedure validation | **OPEN** | No drill scheduled |

---

*Review conducted following critical-architectural-review skill protocol.*
