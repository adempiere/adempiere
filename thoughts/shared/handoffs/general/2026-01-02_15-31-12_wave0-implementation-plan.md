---
date: 2026-01-02T15:31:12+00:00
researcher: Claude
git_commit: b53532bf94a5f3da758d1cdf74dadc45647fd838
branch: wave0
repository: adempiere
topic: "Wave 0 PostgreSQL Function Migration Implementation Plan"
tags: [implementation, strategy, postgresql, migration, wave0, functions]
status: in_progress
last_updated: 2026-01-02
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Wave 0 PostgreSQL Function Migration Implementation Plan

## Task(s)
**Status: In Progress**

Creating an implementation plan for Wave 0 of the PostgreSQL function migration, following the framework in `~/sprocfw/step3.md`. This is Phase 1 (Planning) - the implementation plan document has not yet been written.

The user requested:
- Write plan for Wave 0 only (as per `docs/discovery/migration-waves.md`)
- Use the framework in `~/sprocfw/step3.md` as the base template
- Save to `docs/plans/2026-01-01-postgresql-function-migration-design.md` (this file exists as a design doc; the implementation plan should likely be a separate file)

## Critical References
1. `~/sprocfw/step3.md` - The per-function migration template (16 sections) that defines the implementation plan structure
2. `docs/discovery/migration-waves.md` - Defines Wave 0 with 7 functions and their prioritization
3. `docs/plans/2026-01-01-postgresql-function-migration-design.md` - The comprehensive design document (v5) with hybrid validation strategy

## Recent changes
No code changes made yet - was in research/planning phase when interrupted.

## Learnings

### Wave 0 Functions (7 total)
| Priority | Function | SQL File | LOC | Complexity | Usage |
|----------|----------|----------|-----|------------|-------|
| 1 | getDate | getDate.sql | 7 | Trivial | Views |
| 2 | daysBetween | daysBetween.sql | 29 | Low | Java + Views |
| 3 | addDays | addDays.sql | 34 | Low | Java + Views |
| 4 | trunc | trunc.sql | 28 | Low | Java + Views |
| 5 | round | round.sql | 10 | Trivial | Views |
| 6 | firstOf | firstOf.sql | 77 | Low | Java + Views |
| 7 | charAt | charAt.sql | 35 | Low | Views |

### Existing Java Implementations Found
**Critical Discovery**: `base/src/org/compiere/util/TimeUtil.java` already contains Java implementations that correspond to most Wave 0 SQL functions:
- `TimeUtil.getDay()` - similar to `getDate.sql` (returns current timestamp truncated to day)
- `TimeUtil.getDaysBetween()` - corresponds to `daysBetween.sql`
- `TimeUtil.addDays()` - corresponds to `addDays.sql`
- `TimeUtil.trunc()` - corresponds to `trunc.sql`

**Implication**: These are known duplicates requiring shadow mode validation to ensure Java matches SQL behavior before cutover.

### SQL Function Details
1. **getDate.sql**: Returns `now()` - trivial, 7 lines
2. **daysBetween.sql**: Casts both timestamps to DATE and subtracts - returns INTEGER
3. **addDays.sql**: Contains 4 function overloads:
   - `addDays(TIMESTAMP, Numeric) -> DATE`
   - `subtractDays(TIMESTAMP, Numeric) -> DATE` (calls addDays with negative)
   - `addDays(interval, numeric) -> INTEGER`
   - `subtractDays(interval, numeric) -> INTEGER`
4. **trunc.sql**: Contains 3 function overloads:
   - `trunc(TIMESTAMP) -> TIMESTAMP` (truncate to date)
   - `trunc(TIMESTAMP, format) -> DATE` (Q, Y, YEAR, MM, MONTH, DD, DY)
   - `trunc(INTERVAL) -> INTEGER` (extract days)
5. **round.sql**: Simple wrapper - `ROUND($1, cast($2 as integer))`
6. **firstOf.sql**: Complex date part mapping (Oracle-compatible format codes to PostgreSQL date_trunc)
7. **charAt.sql**: Simple wrapper - `SUBSTR($1, $2, 1)`

### Validation Strategy for Wave 0
Per the design doc (Section 2.1), these are "Standard" functions using **Shadow Mode at 100%** because:
- Low frequency (< 100 calls/day)
- Simple, stateless functions
- No dependencies on other functions

## Artifacts
Files read/analyzed (not created):
- `db/ddlutils/postgresql/functions/getDate.sql`
- `db/ddlutils/postgresql/functions/daysBetween.sql`
- `db/ddlutils/postgresql/functions/addDays.sql`
- `db/ddlutils/postgresql/functions/trunc.sql`
- `db/ddlutils/postgresql/functions/round.sql`
- `db/ddlutils/postgresql/functions/firstOf.sql`
- `db/ddlutils/postgresql/functions/charAt.sql`
- `base/src/org/compiere/util/TimeUtil.java` (existing Java date utilities)
- `docs/discovery/migration-waves.md` (Wave definitions)
- `docs/plans/2026-01-01-postgresql-function-migration-design.md` (v5 design doc)
- `~/sprocfw/step3.md` (Implementation plan template - 16 sections)

## Action Items & Next Steps

1. **Write the Wave 0 Implementation Plan** following `~/sprocfw/step3.md` template:
   - The plan should cover all 7 functions as a single transaction unit (TU-001: Foundation Utilities)
   - Many sections can be simplified/skipped for these simple utility functions:
     - Section 6 (Session State) - Not applicable
     - Section 7 (Trigger Migration) - Not applicable
     - Section 11 (Cross-Database) - Not applicable
   - Key sections to complete: 0, 1, 2, 3, 5, 8, 9, 10, 12, 13, 14, 15, 16

2. **For each function, document**:
   - Exact SQL logic
   - Corresponding Java implementation (existing in TimeUtil.java or new)
   - Test cases (edge cases for date truncation, null handling, format codes)
   - Shadow mode configuration

3. **Create bite-sized tasks** per the writing-plans skill:
   - Each step should be 2-5 minutes
   - TDD approach: write failing test, run it, implement, run test, commit
   - Include exact file paths, code snippets, and commands

4. **Save plan to**: `docs/plans/YYYY-MM-DD-wave0-implementation-plan.md`

5. **After plan is complete**: Offer execution choice (Subagent-Driven vs Parallel Session)

## Other Notes

### ADempiere Patterns to Follow
- Utility functions go in static methods on relevant classes (TimeUtil.java)
- Model classes are in `base/src/org/compiere/model/`
- Database adapters in `base/src/org/compiere/db/`
- Use ADempiere's existing `Query` class for database access

### Key Design Decisions from v5 Design Doc
- Shadow Mode with 100% sampling for Wave 0 (low frequency)
- Request-scoped caching with configurable scope
- Circuit breaker for shadow mode protection
- Async logging with bounded queue (10K limit)
- Feature flags stored in `migration.function_config` table
- Match rate target: 99.9% for 7 days before cutover

### Files to Create During Implementation
- Migration infrastructure classes in `org.compiere.migration/`:
  - ShadowExecutor.java
  - MigrationConfig.java
  - MigrationLogger.java
  - ResultComparator.java
  - SqlFunctionCaller.java
- Tests in appropriate test directories
- Migration schema DDL (`migration.function_config`, `migration.function_log`)
