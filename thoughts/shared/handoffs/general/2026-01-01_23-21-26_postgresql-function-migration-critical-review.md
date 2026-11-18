---
date: 2026-01-01T23:21:26+00:00
researcher: Claude
git_commit: 84f4d611f0671c63c57eb5458a0b9bb7591e4e99
branch: develop
repository: adempiere
topic: "PostgreSQL Function Migration Design Review"
tags: [architecture-review, postgresql, migration, design-review]
status: complete
last_updated: 2026-01-01
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: PostgreSQL Function Migration Critical Architecture Review

## Task(s)

| Task | Status |
|------|--------|
| Critical architecture review of `docs/plans/2026-01-01-postgresql-function-migration-design.md` | **Completed** |
| Assess alternative architectures (Strangler Fig vs Enhanced Shadow Mode) | **Completed** |
| Review discovery package for gap coverage | **Completed** |

## Critical References

1. **Design Document**: `docs/plans/2026-01-01-postgresql-function-migration-design.md` - The migration design being reviewed
2. **Critical Review Output**: `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md` - My review findings
3. **Discovery Package**: `docs/discovery/` - Comprehensive analysis of all 78 PostgreSQL functions

## Recent Changes

- Created `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md` - Full critical architecture review

## Learnings

### Key Architectural Findings

1. **Shadow mode incompatible with stateful functions**: `nextID.sql` (line 28-35) performs `UPDATE AD_Sequence SET CurrentNext = CurrentNext + IncrementNo`. Running both Java AND SQL in shadow mode will consume two sequence values per call - this is a blocking issue.

2. **Confirmed Java/SQL divergence**: `MInvoice.getOpenAmt()` at `base/src/org/compiere/model/MInvoice.java:1219-1245` has empty TODO blocks for payment schedules, while SQL `invoiceOpen` has 30+ lines of payment schedule logic. HIGH risk.

3. **Strangler Fig is over-engineering**: After reassessment, Enhanced Shadow Mode (sampling + async + special handling for stateful functions) is the better approach because:
   - ADempiere already has M* model classes that functions will migrate into
   - No need for new service layer abstraction
   - Targeted fixes to current design vs. architectural overhaul

4. **Discovery package is comprehensive**: The `docs/discovery/` folder covers all gaps from my review:
   - `function-usage-context.md` - View-to-function dependencies
   - `transaction-units.json` - Migration clusters with existing Java duplicate tracking
   - `migration-waves.md` - 6-wave topological sort with critical path
   - `circular-dependencies.json` - Recursive function handling strategies
   - `dead-code-candidates.md` - 28 functions (36%) identified as unused

### One Remaining Gap

Stateful functions (`nextID`, `nextIDFunc`, `documentNo`) need explicit "STATEFUL - cannot use shadow mode" markers in `transaction-units.json` TU-009.

## Artifacts

1. `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md` - Full critical review with:
   - 7 critical/high issues identified
   - Alternative architecture comparison
   - Minor issues and questions for clarification
   - Final recommendation: Major revisions needed

## Action Items & Next Steps

### Required Before Migration (Blocking)

1. **Add "Stateful Functions" migration category** to design document
   - `nextID`, `nextIDFunc` cannot use shadow mode
   - Alternative: integration testing with production-like load, staged rollout

2. **Address MInvoice.getOpenAmt() divergence**
   - Decide: enhance Java to match SQL, or accept difference?
   - The Java implementation is missing payment schedule logic

3. **Update transaction-units.json TU-009** with explicit stateful warning

### Recommended Enhancements

4. Implement sampling-based shadow execution for high-frequency functions (1-5% sample rate)
5. Add bounded queue with backpressure to async logging (prevent OOM)
6. Define tiered performance budgets: critical (5%), standard (30%), reporting (100%)

## Other Notes

### Key File Locations

| Resource | Path |
|----------|------|
| PostgreSQL functions | `db/ddlutils/postgresql/functions/` (62 files, 78 functions) |
| Discovery package | `docs/discovery/` (8 files) |
| Design document | `docs/plans/2026-01-01-postgresql-function-migration-design.md` |
| Critical review | `docs/plans/2026-01-01-postgresql-function-migration-design-critical-review-1.md` |
| MInvoice class | `base/src/org/compiere/model/MInvoice.java` |

### Discovery Package Summary

| File | Purpose |
|------|---------|
| `discovery-inventory.json` | All 78 functions with params, dependencies, complexity flags |
| `dependency-graph.dot` | Visual function dependency graph |
| `circular-dependencies.json` | Clusters and recursion handling strategies |
| `complexity-report.md` | Scoring formula, tier breakdown, priority matrix |
| `function-usage-context.md` | Which Java files and views call each function |
| `transaction-units.json` | 12 migration units with dependency ordering |
| `dead-code-candidates.md` | 28 unused functions with deprecation process |
| `migration-waves.md` | 6-wave migration schedule with critical path |

### Migration Statistics

- Total functions: 78
- To migrate: 50 (in 12 transaction units)
- Dead code: 28 (36%)
- Estimated effort: 25-35 days (sequential), 18-25 days (parallel)
