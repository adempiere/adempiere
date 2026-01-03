# Critical Architectural Review: PostgreSQL Function Migration Design

**Reviewed Document:** `docs/plans/2026-01-01-postgresql-function-migration-design.md`
**Review Date:** 2026-01-01
**Review Version:** 1

---

## 1. Overall Assessment

**Strengths:**
- Well-structured phased approach with clear quality gates (99.9% match rate, 7-day stability)
- Transaction unit concept correctly groups functions with dependent views
- Shadow execution pattern provides safe production validation before cutover
- Async logging design minimizes performance measurement skew
- Leverages existing ADempiere patterns (Query class, M* model classes) for consistency
- Clear rollback strategy via git versioning

**Major Concerns:**
- **Critical flaw:** Shadow mode design cannot handle functions with side effects (e.g., `nextID.sql` modifies sequences)
- Synchronous dual-path execution doubles latency during shadow phase for high-traffic functions
- No dependency resolution order for functions that call other functions
- Known Java duplicate (`MInvoice.getOpenAmt()`) lacks payment schedule handling present in SQL - confirmed HIGH risk
- Missing concurrency/isolation handling when shadow mode compares results that may see different database states

---

## 2. Critical Issues

### 2.1 Side-Effect Functions Cannot Use Shadow Mode (BLOCKING)

**Description:** `nextID.sql` performs `UPDATE AD_Sequence SET CurrentNext = CurrentNext + IncrementNo`. Running both Java AND SQL in shadow mode will:
- Consume two sequence values per call (one discarded)
- Cause sequence gaps and potential ID exhaustion
- Make comparison meaningless (different IDs are expected)

**Impact:** This affects the most critical, highest-frequency function in the system. Shadow mode as designed is fundamentally incompatible with state-mutating functions.

**Recommendation:**
1. Create a separate migration category: "stateful functions" that require different validation strategy
2. For `nextID`, use integration testing with production-like load, NOT shadow mode
3. Consider a "dry-run" shadow variant that executes Java, logs what SQL *would* return without executing, using a test replica

### 2.2 Shadow Mode Doubles Latency During Validation (HIGH)

**Description:** In SHADOW mode, every call executes both Java AND SQL synchronously. For high-frequency functions like `currencyConvert` (called by `invoiceOpen` and many others), this effectively doubles response time for 7+ days.

**Impact:** Production performance degradation during entire shadow period. For functions called 1000s of times per transaction, this compounds significantly.

**Recommendation:**
1. Implement sampling-based shadow execution (e.g., 1% of calls run both paths)
2. Use asynchronous SQL verification that doesn't block the response path
3. Define "high-frequency threshold" above which sampling is mandatory

### 2.3 Missing Function Dependency Graph (HIGH)

**Description:** The 62 functions have interdependencies (e.g., `invoiceOpen` calls `currencyConvert`). The design mentions "transaction units" for function+view grouping but doesn't address function-to-function dependencies.

**Impact:** If `currencyConvert` is migrated while `invoiceOpen` still calls SQL, shadow mode comparisons become meaningless. Migration order is undefined.

**Recommendation:**
1. Build explicit dependency graph before migration begins: `SELECT proname, prosrc FROM pg_proc WHERE prosrc ILIKE '%functionname%'`
2. Migrate leaf functions first (no outbound dependencies)
3. Document topological sort order in step2 prioritization

### 2.4 Confirmed Java/SQL Divergence in MInvoice.getOpenAmt() (HIGH)

**Description:** Code review confirms the design document's HIGH risk assessment. The existing Java implementation at `MInvoice.java:1219-1245`:
- Has empty TODO blocks for "Payment Discount" and "Payment Schedule"
- Uses cached `openAmount` field (stale data risk)
- Does NOT handle `C_InvoicePaySchedule` logic at all

The SQL `invoiceOpen` function has 30+ lines of payment schedule iteration logic.

**Impact:** These implementations will produce different results for any invoice with payment schedules. Shadow mode will immediately show mismatches.

**Recommendation:**
1. Prioritize this function for early migration to understand mismatch resolution workflow
2. Document decision: will Java be enhanced to match SQL, or will differences be accepted?
3. Add explicit cache invalidation strategy for `openAmount` field

### 2.5 No Transaction Isolation Between Shadow Paths (MEDIUM-HIGH)

**Description:** Shadow mode executes Java then SQL sequentially. If another transaction modifies data between these executions, the comparison is invalid. The design doesn't address read consistency.

**Impact:** Spurious mismatches under concurrent load, potentially masking real bugs or causing false alerts.

**Recommendation:**
1. Execute shadow comparisons within same transaction with `REPEATABLE READ` isolation
2. Or: capture relevant input data snapshot before Java execution, pass to SQL call
3. Document expected false-positive rate from race conditions

### 2.6 Performance Gate (30%) Is Undifferentiated (MEDIUM)

**Description:** "Max 30% latency increase at p95" applies uniformly to all functions, but some are vastly more critical than others.

**Impact:** 30% increase on `nextID` (called millions of times) is catastrophic; 30% on a rarely-used reporting function is acceptable.

**Recommendation:**
1. Define tiered latency budgets: critical (5%), standard (30%), reporting (100%)
2. Classify each function before migration
3. `nextID`, `currencyConvert`, `documentNo` should be in critical tier

### 2.7 In-Memory Logging Queue Has No Backpressure (MEDIUM)

**Description:** Async logging uses an unbounded in-memory queue drained every 1 second. Under sustained high load, if the background thread can't keep pace, queue grows unboundedly.

**Impact:** Potential OOM in production during high-load periods.

**Recommendation:**
1. Use bounded queue (e.g., `LinkedBlockingQueue` with capacity)
2. Implement drop-oldest or sampling when queue is full
3. Monitor queue depth as operational metric

### 2.8 View-to-Java Loses Query Planner Optimization (MEDIUM)

**Description:** Converting SQL views to Java `.stream().map().filter()` moves filtering from database to application layer.

**Impact:** Views that aggregate over large datasets will perform significantly worse when reimplemented as Java stream operations.

**Recommendation:**
1. Keep aggregation/reporting views in PostgreSQL (already mentioned in design, but enforce this)
2. For migrated views, ensure WHERE clauses are pushed to Query builder, not post-filtered in Java
3. Add query-level performance tests comparing SQL view vs Java implementation

---

## 3. Alternative Architectural Challenge

### Strangler Fig with Service Layer Abstraction

**Description:** Instead of shadow mode at the function level, introduce an explicit service layer that abstracts all calculation logic. Migrate one service at a time with proper A/B testing at the service boundary.

```
Current:  View -> SQL Function -> Tables
Proposed: Java Caller -> CalculationService (interface)
                              |
              +---------------+---------------+
              |                               |
        SQLImplementation              JavaImplementation
        (delegates to SQL fn)          (pure Java)
```

Route calls via feature flags at the service level, not function level. The service interface enforces identical contracts.

**Primary Pro:** Cleaner separation of concerns; services can be tested independently; no dual-execution performance penalty; works correctly for stateful functions (routing, not dual-execution).

**Primary Con:** Requires more upfront refactoring to introduce service layer; existing code calling SQL functions via JDBC or M* classes needs modification to use service interfaces; higher initial investment.

---

## 4. Minor Issues & Improvements

### 4.1 Match Rate Exclusions Need Formal Specification
The design lists timestamp tolerance (1 second), floating point precision, null vs empty string. These should be configurable per-function via `migration.function_config` table, not hardcoded.

### 4.2 Cleanup Timeline Is Aggressive
"Delete SQL function after 30 days" - consider retaining SQL functions longer (90 days) in git history is sufficient but some organizations prefer database-level retention for emergency rollback.

### 4.3 Consider Canary Deployments for Cutover
The cutover from SHADOW to JAVA_ONLY is binary. Consider percentage-based rollout (10% -> 50% -> 100%) with automatic rollback triggers.

### 4.4 ShadowExecutor Exception Handling Not Specified
What happens if Java throws but SQL succeeds, or vice versa? Should be logged as mismatch with exception details, not silent failure.

### 4.5 No Explicit Index Strategy for migration.function_log
At high volume, the single index on `(function_name, created_at)` may be insufficient. Consider partitioning by week/month for easier cleanup.

---

## 5. Questions for Clarification

1. **Stateful function strategy:** How will `nextID`, `nextIDByYear`, `documentNo` (all with side effects) be validated without shadow mode?

2. **Dependency resolution:** Is there a dependency analysis output for the 62 functions? Which are leaf nodes?

3. **Existing callers inventory:** How many Java code paths currently call SQL functions directly via `DB.executeFunction()`? These need migration to use the new Java methods.

4. **Multi-tenant considerations:** Does shadow mode need to respect AD_Client_ID isolation, or is comparison cross-tenant acceptable?

5. **Concurrent modification tolerance:** What is the acceptable false-positive mismatch rate from race conditions, and how will it be distinguished from real bugs?

6. **Payment schedule TODO in MInvoice:** Is there existing documentation on why this was left incomplete? Are there known bugs in production from this divergence?

---

## 6. Final Recommendation

**Major revisions needed.**

The design is well-structured for read-only calculation functions but has fundamental gaps for the most critical category: stateful functions like `nextID`. Before proceeding:

### Required Changes (Blocking)

1. **Add "Stateful Functions" migration category** with alternative validation strategy (not shadow mode)
2. **Build and document function dependency graph** to establish migration order
3. **Address MInvoice.getOpenAmt() divergence** - decide if Java will be fixed to match SQL or if the difference is acceptable

### Recommended Changes (High Priority)

4. Implement sampling-based shadow execution for high-frequency functions
5. Add bounded queue with backpressure to async logging
6. Define tiered performance budgets by function criticality
7. Specify transaction isolation strategy for shadow comparisons

Once these are addressed, the design provides a solid framework for safe, observable migration of PostgreSQL functions to Java.
