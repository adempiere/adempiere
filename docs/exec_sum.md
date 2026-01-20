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

---

## Wave Status

### Wave 0: Foundation - COMPLETE

**Status:** All 5 parts completed. Branch `wave0` ready for merge to `develop`.

| Part | Description | Status |
|------|-------------|--------|
| Part 1 | Core Infrastructure (schema, config, logging, serialization, comparators) | Complete |
| Part 2 | Execution Infrastructure (CircuitBreaker, SqlFunctionCaller, ShadowExecutor) | Complete |
| Part 3 | DateTime Functions (getDate, daysBetween, addDays, subtractDays, trunc) | Complete |
| Part 4 | Utility Functions (round, charAt, firstOf) | Complete |
| Part 5 | Testing & Deployment (integration tests, shadow mode, runbook) | Complete |

**Functions Migrated (8):**

| Function | Java Location |
|----------|---------------|
| `getDate` | `TimeUtil.getDate()` |
| `daysBetween` | `TimeUtil.daysBetweenSql()` |
| `addDays` | `TimeUtil.addDaysSql()` |
| `subtractDays` | `TimeUtil.subtractDaysSql()` |
| `trunc` | `TimeUtil.truncSql()` |
| `round` | `SqlCompat.round()` |
| `firstOf` | `TimeUtil.firstOf()` |
| `charAt` | `SqlCompat.charAt()` |

**Test Results:**
- Unit Tests: All passing
- Integration Tests: 9/9 passed
- Performance Tests: 25/25 passed (all <= 130% of SQL latency)

### Wave 1: Currency - COMPLETE

**Status:** All 20 tasks completed. Shadow mode infrastructure ready for activation.

| Task Group | Description | Status |
|------------|-------------|--------|
| Task 0 | EMU-to-EMU SQL bug fix | Complete |
| Tasks 1-5 | currencyRound implementation & tests | Complete |
| Tasks 6-9 | currencyRate implementation & tests | Complete |
| Tasks 10-11 | currencyConvert implementation & tests | Complete |
| Tasks 12-14 | currencyBase implementation & tests | Complete |
| Tasks 15-16 | Performance tests & baseline | Complete |
| Tasks 17-19 | BigDecimalComparator & CurrencyFunctionRouter | Complete |

**Functions Migrated (4):**

| Function | Java Location |
|----------|---------------|
| `currencyRound` | `CurrencyFunctions.currencyRound()` |
| `currencyRate` | `CurrencyFunctions.currencyRate()` |
| `currencyConvert` | `CurrencyFunctions.currencyConvert()` |
| `currencyBase` | `CurrencyFunctions.currencyBase()` |

**Key Achievements:**
- EMU-to-EMU rate bug fixed in SQL and Java
- Division-by-zero protection in EMU calculations
- BigDecimalComparator with 6 decimal place tolerance
- CurrencyFunctionRouter for shadow mode integration
- All unit, integration, and performance tests passing

### Wave 2: Payment Terms - COMPLETE

**Status:** All functions implemented, validated, and cut over to JAVA_ONLY mode.

| Task Group | Description | Status |
|------------|-------------|--------|
| Group 1 | addMonths & nextBusinessDay | Complete |
| Group 2 | paymentTermDueDate & paymentTermDueDays | Complete |
| Group 3 | paymentTermDiscount | Complete |
| Group 4 | Shadow validation & cutover | Complete |

**Functions Migrated (5):**

| Function | Java Location |
|----------|---------------|
| `addMonths` | `PaymentTermFunctions.addMonths()` |
| `nextBusinessDay` | `PaymentTermFunctions.nextBusinessDay()` |
| `paymentTermDueDate` | `PaymentTermFunctions.paymentTermDueDate()` |
| `paymentTermDueDays` | `PaymentTermFunctions.paymentTermDueDays()` |
| `paymentTermDiscount` | `PaymentTermFunctions.paymentTermDiscount()` |

**Key Achievements:**
- 100% match rate in shadow validation (Java vs SQL)
- Critical bug fixes: nextBusinessDay loop, calculateFixedDueDate cutoff
- N+1 query elimination via 30-day holiday pre-fetch
- Rollback drill: 16/16 tests passed
- Wave 3 unblocked (paymentTermDiscount enables invoiceDiscount)

### Wave 3: Financial Core - COMPLETE

**Status:** All 7 functions migrated to JAVA_ONLY mode. Post-cutover monitoring in progress.

| Task Group | Description | Status |
|------------|-------------|--------|
| Group 1 | SqlFunctionCaller extensions, QueryCounter integration | Complete |
| Group 2 | Payment functions (paymentAllocated, paymentAvailable) | Complete |
| Group 3 | Invoice paid functions (invoicePaid, invoicePaidToDate) | Complete |
| Group 4 | Invoice open functions (invoiceOpen, invoiceOpenToDate) | Complete |
| Group 5 | Invoice discount function (invoiceDiscount) | Complete |
| Group 6 | Shadow integration tests (75), performance tests (70) | Complete |
| Group 7 | Quality gates & JAVA_ONLY cutover | Complete |

**Functions Migrated (7):**

| Function | Java Location |
|----------|---------------|
| `invoiceOpen` | `InvoiceFunctions.invoiceOpen()` |
| `invoiceOpenToDate` | `InvoiceFunctions.invoiceOpenToDate()` |
| `invoiceDiscount` | `InvoiceFunctions.invoiceDiscount()` |
| `invoicePaid` | `InvoiceFunctions.invoicePaid()` |
| `invoicePaidToDate` | `InvoiceFunctions.invoicePaidToDate()` |
| `paymentAllocated` | `PaymentFunctions.paymentAllocated()` |
| `paymentAvailable` | `PaymentFunctions.paymentAvailable()` |

**Key Achievements:**
- 100% match rate across 670 test executions
- Sub-millisecond overhead (0.27-0.37ms per call)
- Payment schedule logic ported (previously empty TODO blocks)
- C_Invoice_v multi-row aggregation fix applied
- SQL functions retained for database views (RV_OPENITEM, RV_BPARTNEROPEN, RV_PAYMENT)

### Wave 4: Standalone Functions - COMPLETE

**Status:** All 9 functions migrated to JAVA_ONLY mode. Gate 5 monitoring in progress (until 2026-01-16).

| Task Group | Description | Status |
|------------|-------------|--------|
| Group 0 | Prerequisites (StringComparator, view analysis) | Complete |
| Group 1 | Infrastructure (config SQL, SqlFunctionCaller) | Complete |
| Group 2 | Sequence functions (nextID, nextIDFunc) | Complete |
| Group 3 | Simple lookup (acctBalance, getSysconfig, maxpaydate) | Complete |
| Group 4 | Line amount (linenetamtrealinvoiceline, linenetamtrealorderline) | Complete |
| Group 5 | Complex functions (productAttribute, documentNo) + Router | Complete |
| Group 6 | Testing (101 tests across 5 test classes) | Complete |

**Functions Migrated (9):**

| Function | Java Location |
|----------|---------------|
| `nextID` | `Wave4Functions.nextID()` |
| `nextIDFunc` | `Wave4Functions.nextIDFunc()` |
| `acctBalance` | `Wave4Functions.acctBalance()` |
| `getSysconfig` | `MSysConfig` (pre-existing) |
| `maxpaydate` | `Wave4Functions.maxpaydate()` |
| `linenetamtrealinvoiceline` | `Wave4Functions.linenetamtrealinvoiceline()` |
| `linenetamtrealorderline` | `Wave4Functions.linenetamtrealorderline()` |
| `productAttribute` | `Wave4Functions.productAttribute()` |
| `documentNo` | `Wave4Functions.documentNo()` |

**Key Achievements:**
- 100% match rate in shadow validation (46/46 calls)
- Atomic nextID using UPDATE...RETURNING (eliminates race conditions)
- 5 sequential successful test runs for cutover
- 101 total tests (95 passed, 5 skipped for missing test data)

### Wave 5: BOM Functions - COMPLETE

**Status:** All 7 functions migrated to JAVA_ONLY mode. Gate 5 monitoring in progress (Day 1 of 7).

| Task Group | Description | Status |
|------------|-------------|--------|
| Group 1 | Infrastructure (config SQL, SqlFunctionCaller) | Complete |
| Group 2 | BOM tree loading (batch CTE, circular detection) | Complete |
| Group 3 | Price functions (bomPriceLimit, bomPriceList, bomPriceStd) | Complete |
| Group 4 | Quantity functions (bomQtyOnHand, bomQtyReserved, bomQtyOrdered, bomQtyAvailable) | Complete |
| Group 5 | Router & shadow validation | Complete |
| Group 6 | Testing (92 tests across 6 test classes) | Complete |

**Functions Migrated (7):**

| Function | Java Location |
|----------|---------------|
| `bomPriceLimit` | `Wave5Functions.bomPriceLimit()` |
| `bomPriceList` | `Wave5Functions.bomPriceList()` |
| `bomPriceStd` | `Wave5Functions.bomPriceStd()` |
| `bomQtyOnHand` | `Wave5Functions.bomQtyOnHand()` |
| `bomQtyReserved` | `Wave5Functions.bomQtyReserved()` |
| `bomQtyOrdered` | `Wave5Functions.bomQtyOrdered()` |
| `bomQtyAvailable` | `Wave5Functions.bomQtyAvailable()` |

**Key Achievements:**
- Zero N+1 queries via batch CTE approach
- 100% validation match rate across all test scenarios
- Robust circular BOM detection (ancestor-path tracking)
- Deep BOM support validated (15-25 level chains)
- 52ms rollback execution time verified
- 92 tests (83 passed, 9 skipped for missing test data)
