---
date: 2026-01-01T23:37:59+00:00
researcher: claude
git_commit: 84f4d611f0671c63c57eb5458a0b9bb7591e4e99
branch: develop
repository: adempiere
topic: "PostgreSQL Function Migration Design Document Update"
tags: [design, migration, postgresql, java, critical-review]
status: in_progress
last_updated: 2026-01-01
last_updated_by: claude
type: implementation_strategy
---

# Handoff: PostgreSQL Function Migration Design Update

## Task(s)

**Status: Work In Progress**

Updating `docs/plans/2026-01-01-postgresql-function-migration-design.md` based on critical review feedback from `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md`.

The critical review identified 7 items to address:

### Blocking Issues (Required) - 3 items
1. **Stateful Functions Category** - DRAFTED (Section 2.1)
   - Add separate migration strategy for `nextID`, `nextIDByYear`, `documentNo`
   - These cannot use shadow mode (would consume 2 sequence values per call)
   - Use replica-based validation and integration testing instead

2. **Function Dependency Graph** - DRAFTED (Section 2.2)
   - References existing discovery work in `docs/discovery/`
   - Migration waves already documented in `migration-waves.md`

3. **MInvoice.getOpenAmt() Divergence** - DRAFTED (Section 2.3)
   - Decision: Java will be enhanced to match SQL behavior
   - Includes cache invalidation strategy

### High Priority Recommendations - 4 items (NOT YET STARTED)
4. **Sampling-based shadow execution** for high-frequency functions
5. **Bounded queue with backpressure** for async logging
6. **Tiered performance budgets** by function criticality
7. **Transaction isolation strategy** for shadow comparisons

## Critical References

1. `docs/plans/2026-01-01-postgresql-function-migration-design.md` - The design document being updated
2. `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md` - The critical review with all issues
3. `docs/discovery/migration-waves.md` - Completed discovery work with dependency analysis

## Recent changes

No file changes committed yet. Three sections have been drafted in conversation but not yet written to the design document:
- Section 2.1: Stateful Function Migration Strategy
- Section 2.2: Function Dependency Graph (references existing discovery)
- Section 2.3: Known Duplicate Resolution: invoiceOpen

## Learnings

1. **Stateful functions cannot use shadow mode** - Functions like `nextID` that modify database state would consume resources twice (e.g., two sequence values per call), making shadow validation meaningless.

2. **Discovery work is already complete** - The function dependency graph and migration waves are documented in `docs/discovery/`:
   - `dependency-graph.dot`
   - `migration-waves.md` (6 waves, 50 functions)
   - `transaction-units.json`
   - `circular-dependencies.json`

3. **MInvoice.getOpenAmt() confirmed diverged** - Java implementation has empty TODO blocks for payment schedule logic that exists in SQL. Decision is to enhance Java to match SQL.

4. **The sprocfw framework** at `~/sprocfw/step2.md` provides the discovery methodology used.

## Artifacts

- `docs/plans/2026-01-01-postgresql-function-migration-design.md` - Original design (needs update)
- `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md` - Critical review being addressed
- `docs/discovery/migration-waves.md` - Discovery output with wave assignments
- `docs/discovery/dependency-graph.dot` - Function dependency graph
- `docs/discovery/transaction-units.json` - Transaction unit groupings

## Action Items & Next Steps

### Immediate (Blocking Issues - Finish Drafting)
1. Write the three drafted sections (2.1, 2.2, 2.3) to the design document

### Then (High Priority Items)
2. Add Section: Sampling-Based Shadow Execution
   - Define "high-frequency threshold" (e.g., >1000 calls/day)
   - Implement 1% sampling for functions above threshold
   - Document async SQL verification approach

3. Add Section: Bounded Logging Queue
   - Replace unbounded queue with `LinkedBlockingQueue` with capacity
   - Add drop-oldest/sampling when queue full
   - Add queue depth monitoring metric

4. Add Section: Tiered Performance Budgets
   - Critical tier (5% max latency increase): `nextID`, `currencyConvert`, `documentNo`
   - Standard tier (30%): Most functions
   - Reporting tier (100%): Rarely-used reporting functions

5. Add Section: Transaction Isolation for Shadow Comparisons
   - Use `REPEATABLE READ` isolation for shadow comparisons
   - Or capture input data snapshot before Java execution

### Final
6. Update Decision Summary table (Section 10)
7. Commit updated design document

## Other Notes

### Key Files in Codebase
- PostgreSQL functions: `db/ddlutils/postgresql/functions/`
- PostgreSQL views: `db/ddlutils/postgresql/views/`
- Model classes: `base/src/org/compiere/model/`
- MInvoice: `base/src/org/compiere/model/MInvoice.java` (line ~1219-1245 for getOpenAmt)

### Critical Review Summary
The review praised the phased approach, shadow execution pattern, and async logging design. Main concerns were:
- Shadow mode incompatible with stateful functions (BLOCKING)
- No dependency ordering for function-to-function calls (addressed by existing discovery)
- Java/SQL divergence in MInvoice.getOpenAmt() (needs resolution decision)
- Performance concerns for high-frequency functions during shadow phase

### Alternative Architecture Considered
The review proposed a "Strangler Fig with Service Layer Abstraction" as alternative - routing via feature flags at service level rather than function level. This was noted but not adopted; current design proceeds with function-level shadow mode with the stateful function exception.
