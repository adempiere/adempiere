# Critical Architectural Review: PostgreSQL Function Migration Design

**Reviewed Document:** `thoughts/shared/handoffs/general/2026-01-01_23-21-26_postgresql-function-migration-critical-review.md` (handoff referencing `docs/plans/2026-01-01-postgresql-function-migration-design.md`)
**Review Date:** 2026-01-01
**Review Version:** 2
**Reviewer Focus:** Strategic architectural assessment with attention to prior review findings

---

## 1. Overall Assessment

**Strengths:**
- Comprehensive discovery package with function inventory, dependency graph, complexity scoring, and migration waves
- Transaction unit concept correctly groups functions with dependent views (12 units, 50 functions)
- Shadow execution pattern provides production validation before cutover
- Clear quality gates (99.9% match rate, 7-day stability) with defined rollback via git
- Pragmatic hybrid view strategy (keep simple views, migrate function-dependent ones)
- Dead code identification (28 functions, 36%) reduces migration scope significantly
- Prior review (v1) correctly identified critical blocking issues

**Major Concerns:**
- **Blocking issue remains unresolved:** Stateful functions (`nextID`, `nextIDFunc`, `documentNo`) in TU-009 still lack explicit migration strategy differentiation - shadow mode is fundamentally incompatible with these
- **MInvoice divergence unactioned:** The confirmed Java/SQL divergence for `getOpenAmt()` has no decision recorded - this blocks Wave 3 (Financial Core)
- **Enhanced Shadow Mode refinements not incorporated:** Prior review recommended sampling (1-5%), bounded queues, and tiered performance budgets - these are mentioned in handoff "Recommended Enhancements" but not integrated into the design
- **Missing integration test strategy:** For stateful functions excluded from shadow mode, there's no alternative validation approach defined
- **Operational complexity underestimated:** Running shadow mode on 50 functions across 6 waves for 7+ days each creates significant monitoring burden and coordination overhead

---

## 2. Critical Issues

### 2.1 TU-009 Stateful Functions Lack Migration Path (BLOCKING - Inherited)

**Description:** The handoff acknowledges this issue ("Stateful Functions - cannot use shadow mode") but `transaction-units.json` TU-009 still has no explicit `stateful: true` marker or alternative validation strategy. The design document's shadow mode pattern would:
- Consume two sequence values per `nextID` call (one discarded)
- Create sequence gaps causing potential ID space exhaustion
- Make comparison meaningless (different IDs are expected)

**Impact:** `nextID` is the most critical infrastructure function - called for every record creation. Incorrect migration approach could cause production data integrity issues.

**Why It Matters:** This isn't a documentation gap - it's a missing architectural decision. The team cannot proceed with TU-009 until an alternative validation strategy exists.

**Recommendation:**
1. Add explicit `"stateful": true` flag to TU-009 in `transaction-units.json`
2. Define alternative validation strategy:
   - Option A: Integration tests with production-like concurrent load on staging replica
   - Option B: "Observe mode" - Java runs, logs what it would do, SQL actually executes; compare logs post-hoc
   - Option C: Staged rollout (10% -> 50% -> 100%) with monitoring for sequence anomalies
3. Document acceptance criteria: What defines success for stateful function migration without shadow mode?

### 2.2 MInvoice.getOpenAmt() Divergence Blocks Wave 3 (HIGH - Inherited)

**Description:** Confirmed: Java at `MInvoice.java:1219-1245` has empty TODO blocks, while SQL `invoiceOpen` has 30+ lines of payment schedule logic. No decision recorded on resolution path.

**Impact:** Wave 3 (Financial Core) cannot achieve 99.9% match rate until this divergence is resolved. This is the critical path for the migration.

**Why It Matters:** This exemplifies the "shadow mode as discovery tool" anti-pattern - using production validation to find bugs rather than fixing known issues upfront.

**Recommendation:**
1. **Decide now:** Will Java be enhanced to match SQL, or will SQL be simplified to match Java?
2. If enhancing Java:
   - Create dedicated backlog item for `MInvoice.getOpenAmt()` payment schedule implementation
   - Estimate 2-3 days additional effort before shadow mode can start
3. Document the decision in TU-006 with rationale
4. Add integration test specifically for invoices with payment schedules

### 2.3 No Alternative Validation Strategy for Stateful Functions (HIGH - NEW)

**Description:** The design assumes all functions can use shadow mode. For the 3+ stateful functions that cannot, there's no defined validation approach.

**Impact:** TU-009 (`nextID`, `nextIDFunc`) and potentially `documentNo` have no migration quality gates.

**Why It Matters:** Either these functions are migrated without proper validation (risky), or they block forever waiting for a strategy (stalled).

**Recommendation:**
1. Create "Stateful Function Migration Protocol" section in design document:
   - No shadow mode
   - Unit tests with sequence reset/isolation
   - Integration tests with concurrent access patterns
   - Staged rollout with monitoring for sequence gaps/duplicates
   - Define rollback trigger: duplicate ID detection = immediate revert
2. Apply to TU-009 explicitly

### 2.4 Monitoring Burden at Scale Not Addressed (MEDIUM-HIGH - NEW)

**Description:** Running shadow mode on 50 functions across 6 waves for 7+ days each means:
- 6-12 weeks of active monitoring (with parallel waves)
- 50 functions in `migration.function_log` producing high volume
- Dashboard queries will slow as table grows
- Alert fatigue risk from low-significance mismatches

**Impact:** Team may reduce monitoring diligence over time, missing real issues in later waves.

**Why It Matters:** The design assumes consistent attention throughout a multi-month migration. Human factors matter.

**Recommendation:**
1. Implement log partitioning by week (mentioned in v1 review minor issues, not actioned)
2. Define "attention tiers":
   - Critical (TU-006 invoice, TU-009 nextID): active monitoring, immediate alerts
   - Standard (TU-001 utilities): batch review, daily summary
   - Low (TU-012 miscellaneous): weekly review acceptable
3. Automate gate transitions: if 99.9% for 7 days with zero critical mismatches, auto-promote to JAVA_ONLY
4. Add logging sample rate per-function for high-volume operations

### 2.5 Prior Review Recommendations Not Integrated (MEDIUM)

**Description:** The handoff lists v1 review's recommendations under "Recommended Enhancements" but these haven't been incorporated into the actual design document:
- Sampling-based shadow execution (1-5% for high-frequency functions)
- Bounded queue with backpressure
- Tiered performance budgets (critical: 5%, standard: 30%, reporting: 100%)
- Transaction isolation for shadow comparisons

**Impact:** The design document remains incomplete; implementers may not see these recommendations.

**Why It Matters:** Reviews are only valuable if findings are integrated. The handoff is informal documentation, not the design of record.

**Recommendation:**
1. Update `2026-01-01-postgresql-function-migration-design.md` Section 7 (Shadow Mode Infrastructure) with:
   - Sampling configuration per-function
   - Bounded queue with drop-oldest semantics
   - `REPEATABLE READ` transaction isolation for shadow comparisons
2. Update Section 5 (Testing and Quality Gates) with tiered performance budgets
3. Mark v1 review recommendations as "Integrated" vs "Deferred" vs "Rejected" with rationale

### 2.6 BOM Recursion Strategy Lacks Depth Limits (MEDIUM)

**Description:** Wave 5 BOM functions are self-recursive. The migration plan mentions "Convert Recursion to Iteration" with stack-based traversal, but doesn't address:
- Maximum BOM depth handling (stack overflow protection)
- Circular BOM detection (infinite loop prevention)
- Memory bounds for cached component calculations

**Impact:** Malformed BOM data could cause Java implementation to hang or OOM.

**Why It Matters:** SQL recursion has implicit limits (stack depth, statement timeout). Java iteration needs explicit safeguards.

**Recommendation:**
1. Add explicit depth limit (e.g., 100 levels) with warning log if exceeded
2. Implement visited-node tracking for circular reference detection
3. Define memory budget for memoization cache; use LRU eviction if exceeded
4. Add integration test with pathological BOM (deep, circular, wide)

---

## 3. Alternative Architectural Challenge

### Phased Dual-Stack with Explicit Cutover Windows

**Description:** Instead of per-function shadow mode running continuously for 7+ days, implement a "burst validation" approach with scheduled cutover windows.

```
Current Design:
  Function A: [----SHADOW (7+ days)----][JAVA_ONLY]
  Function B:    [----SHADOW (7+ days)----][JAVA_ONLY]
  (continuous monitoring burden)

Alternative:
  Week 1: Foundation functions in SHADOW (2 days burst)
          -> Cutover window (1 day intensive validation)
          -> JAVA_ONLY
  Week 2: Currency functions in SHADOW (2 days burst)
          -> Cutover window (1 day intensive validation)
          -> JAVA_ONLY
  (concentrated attention, clear boundaries)
```

**Key Differences:**
1. **Time-boxed validation:** 2-3 days of intensive shadow mode per wave, not 7+ days per function
2. **Scheduled cutover windows:** Planned periods where team focuses on one wave transition
3. **Lower match threshold during burst:** 99.0% (not 99.9%) acceptable for initial burst; full week at JAVA_ONLY with monitoring confirms stability
4. **Rollback at wave level:** If wave fails, revert entire wave (functions stay cohesive)

**Primary Pro:** Reduces total migration elapsed time from 6-12 weeks to 4-6 weeks; concentrates monitoring effort into defined windows; clearer success/failure states per wave.

**Primary Con:** Higher risk per cutover (less soak time); requires more upfront testing confidence; team must be available for intensive cutover windows (not always feasible).

---

## 4. Minor Issues & Improvements

### 4.1 Match Rate Exclusions Still Hardcoded
The v1 review noted that timestamp tolerance (1 second), floating point precision, and null vs empty string should be configurable per-function in `migration.function_config`. This hasn't been addressed.

### 4.2 No Post-Migration Cleanup Plan for Discovery Artifacts
The `docs/discovery/` folder contains 8 files of analysis. After migration completes:
- Should these be archived or deleted?
- `transaction-units.json` references obsolete SQL files
- No documentation lifecycle defined

### 4.3 Logging Schema Cleanup Window Undefined
Design says "DROP SCHEMA migration CASCADE" after all functions migrated. But:
- What's the retention period after last function migrates?
- Should historical data be archived for audit?
- Consider: 30 days post-migration retention before drop

### 4.4 Test Data Requirements Not Specified
For shadow mode to be meaningful, production-representative data is required. The design doesn't specify:
- Will shadow run in production or staging?
- If staging: how fresh must data mirror be?
- For invoice functions: are there test invoices with payment schedules, multi-currency, allocations?

### 4.5 Rollback Granularity Mismatch
Design says "redeploy previous version via git" for rollback. But:
- Git rollback is all-or-nothing (entire codebase)
- Feature flags allow per-function rollback
- Consider: explicit "revert to SQL_ONLY" runbook per function

---

## 5. Questions for Clarification

1. **Stateful function decision owner:** Who decides the alternative validation strategy for `nextID`? Is there a deadline for this decision before it blocks Wave 4?

2. **MInvoice divergence resolution timeline:** Will Java be enhanced before Wave 3 shadow mode starts, or will divergence be "expected mismatches" initially?

3. **Shadow mode environment:** Will shadow validation run in production (real traffic) or staging with production data copy? The design implies production but doesn't state explicitly.

4. **Team capacity during migration:** The 18-25 day parallel execution estimate assumes dedicated resources. Is there competing work that could stretch this timeline?

5. **Existing callers of SQL functions:** How many Java code paths currently call SQL functions directly via `DB.executeFunction()`? These callers need refactoring to use new Java methods - is this tracked?

6. **Multi-tenant shadow mode:** Does comparison need to respect `AD_Client_ID` isolation, or is cross-tenant comparison acceptable for validation purposes?

7. **Performance baseline timing:** When are SQL baselines captured? Under what load conditions? Peak vs off-peak can significantly affect p95 measurements.

---

## 6. Final Recommendation

**Major revisions needed.**

The design has evolved significantly with the addition of the discovery package, which addresses several gaps from the initial v1 review (dependency graph, migration waves, transaction units). However, two blocking issues remain unresolved:

### Required Before Migration Can Proceed (Blocking)

1. **Define stateful function migration protocol** (Issue 2.1, 2.3)
   - TU-009 cannot proceed without alternative validation strategy
   - Add explicit `stateful: true` marker and validation approach

2. **Decide on MInvoice.getOpenAmt() resolution** (Issue 2.2)
   - Wave 3 is blocked until Java/SQL divergence is addressed
   - Document decision: enhance Java OR accept divergence with exclusion rules

3. **Integrate v1 review recommendations into design document** (Issue 2.5)
   - The handoff acknowledges issues but the design of record hasn't been updated
   - Sampling, bounded queues, tiered budgets should be in main design

### Recommended Before Shadow Mode Begins (High Priority)

4. Add BOM depth limits and circular reference detection (Issue 2.6)
5. Define monitoring tier strategy to prevent alert fatigue (Issue 2.4)
6. Clarify shadow mode environment (production vs staging with data copy)
7. Specify log partitioning and retention policy

Once these issues are addressed, the design provides a robust framework for safe, observable migration. The discovery package demonstrates thorough preparation; the remaining gaps are decision-making and design document completion rather than analysis deficiencies.

---

## Summary of Status Changes from v1 Review

| v1 Issue | Status | Notes |
|----------|--------|-------|
| Side-effect functions incompatible with shadow mode | **OPEN - BLOCKING** | Acknowledged but not resolved |
| Missing function dependency graph | **RESOLVED** | Discovery package includes full graph |
| MInvoice.getOpenAmt() divergence | **OPEN - BLOCKING** | No decision on resolution path |
| Shadow mode doubles latency | **PARTIAL** | Sampling recommended but not integrated |
| No transaction isolation for shadow | **OPEN** | Not addressed |
| Performance gate undifferentiated | **PARTIAL** | Tiered budgets recommended but not integrated |
| Unbounded logging queue | **PARTIAL** | Bounded queue recommended but not integrated |

---

*Review conducted following critical-architectural-review skill protocol.*
