---
date: 2026-01-02T16:20:01+00:00
researcher: Claude
git_commit: b53532bf94a5f3da758d1cdf74dadc45647fd838
branch: wave0
repository: adempiere
topic: "Wave 0 Implementation Plan Update from Critical Review"
tags: [implementation, migration, postgresql-functions, wave0]
status: complete
last_updated: 2026-01-02
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Wave 0 Implementation Plan Update

## Task(s)

**Completed:** Updated the Wave 0 implementation plan (`docs/plans/2026-01-02-wave0-implementation-plan.md`) to address all issues identified in the critical review (`docs/plans/2026-01-02-wave0-implementation-plan-critical-review-1.md`).

The update incorporated fixes for 15 issues across P0/P1/P2/Minor priorities through an interactive brainstorming session where each fix was presented and approved individually.

## Critical References

1. `docs/plans/2026-01-02-wave0-implementation-plan.md` - The updated implementation plan (primary artifact)
2. `docs/plans/2026-01-02-wave0-implementation-plan-critical-review-1.md` - The critical review that identified the issues
3. `docs/plans/2026-01-01-postgresql-function-migration-design.md` - Parent design document

## Recent changes

- `docs/plans/2026-01-02-wave0-implementation-plan.md` - Complete rewrite incorporating all critical review feedback

## Learnings

1. **DST handling**: `ChronoUnit.DAYS.between()` correctly handles DST transitions (counts calendar days, not 24-hour periods), unlike millisecond arithmetic
2. **Test infrastructure**: ADempiere uses `CommonGWSetup` for integration tests (not `AdempiereTestCase` as originally stated in the plan)
3. **Timezone safety**: Timestamps should be compared using epoch millis with tolerance, dates using string representation to avoid timezone-at-midnight issues
4. **Fractional days**: SQL `addDays` returns DATE, so fractional days are lossy anyway - better to fail fast with IllegalArgumentException
5. **Week calculations**: `GregorianCalendar` week behavior varies by locale; `java.time.DayOfWeek` is locale-independent

## Artifacts

- `docs/plans/2026-01-02-wave0-implementation-plan.md` - Updated implementation plan with 17 tasks, 3074 lines

Key sections added/modified:
- Lines 31-59: Scope Exclusions section (interval overloads, fractional days)
- Lines 71-77: mkdir step for migrations directory
- Lines 481-628: ParamSerializer (Task 2) - new infrastructure component
- Lines 632-810: Comparators (Task 3) - TimestampComparator, DateComparator for timezone-safe comparisons
- Lines 813-981: CircuitBreaker (Task 4) - resilience infrastructure
- Lines 1694-1725: daysBetweenSql using ChronoUnit.DAYS.between (DST fix)
- Lines 1871-1911: addDaysSql with fractional day validation
- Lines 2424-2487: firstOf using java.time API (locale-independent week calculations)
- Lines 2676-2812: Performance tests (Task 14)
- Lines 2957-2987: Rollback procedure in monitoring runbook

## Action Items & Next Steps

1. **Execute the plan**: Use `superpowers:executing-plans` skill to implement the updated plan task-by-task
2. **Start with Task 0**: Create migration schema and configuration table
3. **Follow TDD pattern**: Each task has failing test -> implementation -> passing test -> commit structure
4. **Run integration tests**: After implementing all functions, run `./gradlew :base:test -PintegrationTest` to validate Java matches SQL
5. **Run performance tests**: Use `./gradlew :base:test -PperformanceTest` to validate latency requirements

## Other Notes

### Test Commands
- Unit tests: `./gradlew :base:test --tests "*.MigrationLoggerTest" -i`
- Integration tests: `./gradlew :base:test --tests "*.SqlFunctionCallerTest" -PintegrationTest -i`
- Performance tests: `./gradlew :base:test --tests "*.Wave0PerformanceTest" -PperformanceTest -i`

### Key New Files to Create
- Infrastructure: `base/src/org/compiere/migration/` package with MigrationMode, MigrationConfig, MigrationLogger, ParamSerializer, CircuitBreaker, SqlFunctionCaller, ShadowExecutor
- Comparators: `base/src/org/compiere/migration/comparators/` with TimestampComparator, DateComparator
- Utility: `base/src/org/compiere/util/SqlCompat.java`
- DDL: `db/ddlutils/postgresql/migrations/001_create_migration_schema.sql`

### Test Base Classes
- Unit tests: No base class needed, use `@Tag("UnitTest")`
- Integration tests: Extend `CommonGWSetup`, use `@Tag("IntegrationTest")`
- Performance tests: Extend `CommonGWSetup`, use `@Tag("PerformanceTest")`
