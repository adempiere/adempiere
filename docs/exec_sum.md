# Executive Summary: PostgreSQL Function Migration Design

## Purpose
Migrate 62 PostgreSQL PL/pgSQL functions to Java while keeping PostgreSQL as the primary database. This improves testability, debuggability, and consolidates business logic in one language.

## Scope
- **In scope:** 62 PostgreSQL functions, dependent views, existing Java duplicates
- **Out of scope:** Oracle/MySQL support, simple views without function dependencies

## Validation Strategy (Hybrid Approach)

| Category | Strategy | Rationale |
|----------|----------|-----------|
| Stateful (`nextID`, `nextIDFunc`) | Dual-Write Logging | Shadow mode incompatible—dual execution consumes sequences |
| High-Frequency (`currencyConvert`, `currencyRate`) | Dual-Write Logging | Zero production latency impact |
| Standard (Waves 2-4) | Shadow Mode (sampled) | Adequate coverage with acceptable latency |
| BOM Functions (Wave 5) | Shadow Mode (100%) | Real-time comparison catches edge cases |

## Quality Gates (Per Function)
1. **Gate 1:** Java implementation complete, unit/integration tests pass
2. **Gate 2:** Shadow/dual-write enabled, monitoring ready, rollback drill completed
3. **Gate 3:** 99.9% match rate for 7 consecutive days
4. **Gate 4:** JAVA_ONLY mode, SQL function deleted

## Key Architectural Decisions
- **Request-scoped caching** to avoid complex cache invalidation hooks
- **Circuit breaker** for shadow mode protection under load (80% queue depth triggers)
- **Dedicated shadow connection pool** (max 5 connections) with global rate limiting
- **Performance tiers:** Critical (5% max latency increase), Standard (30%), Reporting (100%)
- **BOM safeguards:** 100-level max depth, circular detection, 100K entry JVM-wide cache

## Migration Timeline
6 waves over approximately 20 weeks:
- Wave 0: Foundation (2 weeks)
- Wave 1: Currency (3 weeks)
- Wave 2: Invoicing (4 weeks)
- Wave 3: Financial (4 weeks)
- Wave 4: Miscellaneous (3 weeks)
- Wave 5: BOM (4 weeks)

## Success Criteria
- All migrated functions achieve 99.9% match rate for 7 days
- Zero SQL functions remain after migration completes
- Zero production regressions after cutover

## Key Risks & Mitigations
| Risk | Mitigation |
|------|------------|
| Java/SQL divergence | Validate with SQL as source of truth; fix Java to match |
| Performance regression | Baseline capture, tiered budgets, optimize before validation |
| View dependencies break | Migrate function + dependent views as transaction unit |
| Rollback needed | Rollback drill required before Wave 1; SQL retained in git |

## Infrastructure
- Separate `migration` schema for all logging/configuration tables
- Async logging with bounded queue (10K entries)
- Replay executor for dual-write validation (5-15 minute detection latency)
- Correlation ID propagation for debugging
