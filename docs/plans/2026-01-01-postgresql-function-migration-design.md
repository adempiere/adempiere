# PostgreSQL Function Migration Design

**Date:** 2026-01-01
**Status:** Design Complete
**Reference Framework:** `~/sprocfw/step1.md` (principles), `~/sprocfw/step3.md` (per-function template)

---

## 1. Goals and Scope

**Goal:** Migrate PostgreSQL PL/pgSQL functions to Java while keeping PostgreSQL as the primary database. This improves testability, debuggability, and consolidates business logic in one language.

**In Scope:**
- 62 PostgreSQL functions in `db/ddlutils/postgresql/functions/`
- Views that depend on migrated functions (subset of 184 views)
- Existing Java implementations that duplicate SQL functions (re-validate via shadow mode)

**Out of Scope:**
- Oracle/MySQL support (PostgreSQL-primary)
- Simple views with no function dependencies (remain in PostgreSQL)
- Prioritization order (handled separately in step 2)

**Success Criteria:**
- All migrated functions pass shadow mode validation (99.9% match rate for 7 days)
- No SQL functions remain after migration completes
- Views calling migrated functions are converted to Java query methods
- Zero production regressions after cutover

**Non-Goals:**
- Database abstraction layer (not trying to support multiple databases)
- ORM adoption (continue using ADempiere's existing `Query` class and direct JDBC)
- Refactoring beyond what's necessary for migration

---

## 2. Migration Architecture

### Code Location Pattern

Migrated functions follow ADempiere's existing model class pattern:

| Function Type | Java Location | Example |
|---------------|---------------|---------|
| Entity-specific calculations | Instance method on M* class | `MInvoice.getOpenAmt()` |
| Utility functions | Static method on relevant M* class | `MConversionRate.convert()` |
| Complex multi-entity logic | Dedicated calculation class | `MProductPricing` |

### Shadow Execution Flow

```
Caller invokes Java method
    |
    v
Check feature flag (migration.function_config)
    |
    v
+-------------------------------------+
| SHADOW mode                         |
|  - Execute Java logic               |
|  - Execute SQL function via JDBC    |
|  - Compare results                  |
|  - Log mismatches (async)           |
|  - Return Java result               |
+-------------------------------------+
    |
    v
After 99.9% match for 7 days -> JAVA_ONLY mode
    |
    v
Delete SQL function from database
```

### Feature Flag Values (per function)

| Flag Value | Behavior |
|------------|----------|
| `SQL_ONLY` | Legacy path, Java not ready |
| `SHADOW` | Both execute, compare, return Java |
| `JAVA_ONLY` | SQL function can be deleted |

**Flag Storage:** `migration.function_config` table (separate schema)

---

## 3. View Migration Strategy

### Hybrid Approach

| View Type | Action | Example |
|-----------|--------|---------|
| Simple (no function calls) | Keep in PostgreSQL | Basic joins, projections |
| Calls migrated functions | Migrate to Java | `C_Invoice_V` calls `currencyConvert` |
| Reporting/aggregation | Keep in PostgreSQL | Materialized views, dashboards |

### Transaction Units

Functions and their dependent views migrate together as a single unit. This prevents broken intermediate states.

```
Transaction Unit Example: Invoice Open Amount
+-- C_Invoice_Open (function) -> MInvoice.getOpenAmt()
+-- C_Invoice_V (view) -> InvoiceQuery.findInvoicesWithOpenAmt()
+-- Any other views calling C_Invoice_Open
```

### View to Java Conversion Pattern

SQL views become query methods using ADempiere's existing `Query` class:

```java
// Before: SELECT * FROM C_Invoice_V WHERE ...
// After:
public class InvoiceQuery {
    public static List<InvoiceVO> findOpenInvoices(int bPartnerId) {
        return new Query(ctx, MInvoice.Table_Name, "C_BPartner_ID=?", trxName)
            .setParameters(bPartnerId)
            .list()
            .stream()
            .map(inv -> new InvoiceVO(inv, inv.getOpenAmt()))
            .collect(Collectors.toList());
    }
}
```

**Discovery Step:** Before migrating each function, identify all dependent views using PostgreSQL catalog queries.

---

## 4. Handling Existing Duplicates

**Problem:** Some functions already have Java equivalents that may have diverged (e.g., `MInvoice.getOpenAmt()` vs `C_Invoice_Open`).

### Known Duplicates to Investigate

| SQL Function | Java Method | Risk |
|--------------|-------------|------|
| `C_Invoice_Open` | `MInvoice.getOpenAmt()` | HIGH - Java has TODO for payment schedules |
| `C_Currency_Convert` | `MConversionRate.convert()` | MEDIUM - verify edge cases |

### Process for Duplicates

```
1. Identify which path is currently used
   - Search codebase for Java method calls
   - Search views/functions for SQL function calls

2. Run shadow mode (SQL = source of truth)
   - Java result compared against SQL
   - Log all mismatches with full context

3. Analyze mismatches
   - If Java is incomplete -> enhance Java to match SQL
   - If Java has bugs -> fix Java
   - If SQL has bugs -> document, fix Java, note deviation

4. Achieve 99.9% match -> cutover to JAVA_ONLY
```

### Mismatch Logging

Stored in `migration.function_log`:
- Timestamp
- Function name
- Input parameters
- SQL result
- Java result
- Difference description
- Correlation ID (for debugging)

---

## 5. Testing and Quality Gates

### Testing Layers

| Layer | Purpose | When |
|-------|---------|------|
| Unit tests | Test Java logic in isolation | Before shadow mode |
| Integration tests | Test with real database | Before shadow mode |
| Performance tests | Verify latency acceptable | Before shadow mode |
| Shadow mode | Production validation | 7+ days before cutover |

### Performance Requirements

- **Max 30% latency increase** at p95 compared to SQL baseline
- Baseline captured before shadow mode begins
- Measured during shadow mode (both paths timed independently)

### Performance Test Process (Built into Shadow Mode)

```
1. Before shadow mode begins:
   - Run SQL function 1000x with representative inputs
   - Record p50, p95, p99 to migration.function_config

2. During shadow mode:
   - Both paths timed automatically
   - Store metrics in migration.function_log

3. Dashboard/query to compare:
   - SQL baseline vs Java shadow timings
   - Alert if Java exceeds 130% of SQL p95
```

### Quality Gates (per function)

```
Gate 1: Code Complete
+-- Java implementation matches SQL logic
+-- Unit tests pass
+-- Integration tests pass

Gate 2: Shadow Ready
+-- Feature flag set to SHADOW
+-- Mismatch logging configured
+-- Monitoring dashboard ready
+-- Performance baseline captured, Java within 30%

Gate 3: Cutover Approved
+-- 99.9% match rate achieved
+-- 7 consecutive days stable
+-- No critical mismatches unresolved
+-- Dependent views migrated

Gate 4: Cleanup Complete
+-- Feature flag set to JAVA_ONLY
+-- SQL function deleted
+-- Monitoring confirms no errors
```

### Match Rate Calculation

```
Match Rate = (Total Calls - Mismatches) / Total Calls x 100

Exclusions from mismatch count:
- Timing differences (timestamps within 1 second)
- Floating point precision (within configured tolerance)
- Null vs empty string (if semantically equivalent)
```

**Rollback Trigger:** If error rate spikes post-cutover, redeploy previous version via git (includes SQL functions).

---

## 6. Logging and Monitoring

### Separate Schema

All migration-related tables live in a dedicated PostgreSQL schema, not touching the main ADempiere schema.

```sql
CREATE SCHEMA migration;

CREATE TABLE migration.function_log (
    id SERIAL PRIMARY KEY,
    function_name VARCHAR(100) NOT NULL,
    correlation_id VARCHAR(50),
    input_params JSONB,
    sql_result TEXT,
    java_result TEXT,
    sql_time_ms INT,
    java_time_ms INT,
    is_match BOOLEAN,
    mismatch_reason VARCHAR(255),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE migration.function_config (
    function_name VARCHAR(100) PRIMARY KEY,
    mode VARCHAR(20) NOT NULL, -- SQL_ONLY, SHADOW, JAVA_ONLY
    sql_baseline_p95_ms INT,
    updated_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX idx_function_log_name_created
ON migration.function_log(function_name, created_at);
```

### Async Logging

To avoid skewing performance measurements:

```
Call Java method
  -> Start timer
  -> Execute logic
  -> Stop timer
  -> Add entry to in-memory queue (fast, non-blocking)
  -> Return result

Background thread (every 1 second):
  -> Drain queue
  -> Batch INSERT to migration.function_log
```

### Monitoring Dashboard Queries

```sql
-- Match rate by function (last 24 hours)
SELECT function_name,
       COUNT(*) as total_calls,
       SUM(CASE WHEN is_match THEN 1 ELSE 0 END) * 100.0 / COUNT(*) as match_rate
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;

-- Performance comparison
SELECT function_name,
       PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY sql_time_ms) as sql_p95,
       PERCENTILE_CONT(0.95) WITHIN GROUP (ORDER BY java_time_ms) as java_p95
FROM migration.function_log
GROUP BY function_name;
```

### Alerts

- Match rate drops below 99.9% -> page on-call
- Java p95 exceeds SQL p95 x 1.3 -> warning
- Mismatch on critical function (e.g., `C_Invoice_Open`) -> immediate alert

### Cleanup After Migration

When all functions are migrated and stable:
```sql
DROP SCHEMA migration CASCADE;
```

---

## 7. Shadow Mode Infrastructure

### Core Classes

```
org.compiere.migration/
+-- ShadowExecutor.java      -- Orchestrates shadow execution
+-- MigrationConfig.java     -- Reads function_config table
+-- MigrationLogger.java     -- Async logging to function_log
+-- ResultComparator.java    -- Compares SQL vs Java results
+-- SqlFunctionCaller.java   -- Calls legacy SQL functions via JDBC
```

### ShadowExecutor Pattern

```java
public class ShadowExecutor<T> {

    public T execute(String functionName,
                     Supplier<T> javaPath,
                     Supplier<T> sqlPath,
                     BiPredicate<T, T> comparator) {

        String mode = MigrationConfig.getMode(functionName);

        if ("SQL_ONLY".equals(mode)) {
            return sqlPath.get();
        }

        // Time Java execution
        long javaStart = System.nanoTime();
        T javaResult = javaPath.get();
        long javaTime = (System.nanoTime() - javaStart) / 1_000_000;

        if ("JAVA_ONLY".equals(mode)) {
            return javaResult;
        }

        // SHADOW mode - also run SQL
        long sqlStart = System.nanoTime();
        T sqlResult = sqlPath.get();
        long sqlTime = (System.nanoTime() - sqlStart) / 1_000_000;

        // Compare and log async
        boolean match = comparator.test(javaResult, sqlResult);
        MigrationLogger.logAsync(functionName, javaResult, sqlResult,
                                  javaTime, sqlTime, match);

        return javaResult;  // Always return Java in shadow mode
    }
}
```

### Usage in Model Class

```java
public BigDecimal getOpenAmt() {
    return ShadowExecutor.execute(
        "C_Invoice_Open",
        () -> calculateOpenAmtJava(),           // Java implementation
        () -> SqlFunctionCaller.invokeOpen(id), // SQL function
        (j, s) -> j.compareTo(s) == 0           // Comparator
    );
}
```

---

## 8. Migration Workflow Per Function

**Per-function migrations follow the step3.md template** (`~/sprocfw/step3.md`), which provides a comprehensive 16-section checklist with decision tree.

### Summary View (maps to step3.md sections)

```
PHASE 1: Discovery & Planning
+-- Complete Decision Tree (Section 0)
|   +-- Generates customized section checklist
+-- Document Procedure Information (Section 1)
+-- Transaction Unit Gate (Section 2)
+-- Pattern Selection (Section 3)
+-- Latency Assessment if >=3 internal calls (Section 4)

PHASE 2: Implementation
+-- Implementation (Section 5)
|   +-- Repository -> Domain -> Service -> Mapper
+-- Session State if package variables (Section 6)
+-- Function Migration specifics (Section 8)
+-- Transaction Mapping (Section 9)
+-- Testing Strategy (Section 15)

PHASE 3: Shadow Validation
+-- Shadow Execution setup (Section 10)
+-- Feature Flags configuration (Section 12)
+-- Quality Gates monitoring (Section 13)
+-- Rollback Architecture ready (Section 14)

PHASE 4: Cutover
+-- Cutover & Post-Migration (Section 16)
    +-- Set use-java: true
    +-- Monitor 24h with shadow still on
    +-- Disable shadow after 7 days stable
    +-- Delete SQL function after 30 days

PHASE 5: Cleanup
+-- Remove shadow execution code
+-- Remove feature flags
+-- Archive migration document
```

### Sections Likely Skipped for ADempiere Functions

- Section 6 (Session State) - unless function uses package variables
- Section 7 (Trigger Migration) - we're doing functions, not triggers
- Section 11 (Cross-Database) - PostgreSQL-only

### Template Location

`~/sprocfw/step3.md`

---

## 9. Risks and Mitigations

| Risk | Likelihood | Impact | Mitigation |
|------|------------|--------|------------|
| Java/SQL divergence in existing duplicates | HIGH | HIGH | Shadow mode with SQL as source of truth; fix Java to match |
| Performance regression | MEDIUM | HIGH | Baseline capture, 130% p95 gate, optimize before shadow |
| View dependencies break during migration | MEDIUM | MEDIUM | Migrate function + dependent views as transaction unit |
| Shadow mode logging impacts production | LOW | MEDIUM | Async logging with batching; separate schema |
| Match rate never reaches 99.9% | LOW | HIGH | Investigate mismatches early; adjust tolerance for non-critical fields |
| Rollback needed after SQL deletion | LOW | HIGH | Git contains SQL functions; redeploy previous version |
| Transaction semantics differ Java vs SQL | MEDIUM | HIGH | Follow step3.md Section 9 transaction mapping |
| N+1 patterns in Java implementation | MEDIUM | MEDIUM | Latency assessment (Section 4) catches before shadow |

---

## 10. Decision Summary

| Decision | Choice |
|----------|--------|
| Target state | PostgreSQL-primary, functions in Java |
| Views | Hybrid: keep simple, migrate those with function deps |
| Shadow mode | Full (99.9% match, 7 days) |
| Rollback | No SQL retention; git rollback if needed |
| Function + view migration | Together as transaction unit |
| Code location | Model classes (M*) following existing patterns |
| Existing duplicates | Re-evaluate via shadow, SQL is source of truth |
| Feature flags | Database-backed (migration schema) |
| Performance testing | Built into shadow mode with baseline capture |
| Logging | Async, separate `migration` schema |
| Per-function template | ~/sprocfw/step3.md |

---

## Appendix A: File Locations Reference

| Resource | Path |
|----------|------|
| PostgreSQL functions | `db/ddlutils/postgresql/functions/` |
| PostgreSQL views | `db/ddlutils/postgresql/views/` |
| Model classes | `base/src/org/compiere/model/` |
| Database adapters | `base/src/org/compiere/db/` |
| Migration framework (principles) | `~/sprocfw/step1.md` |
| Migration framework (per-function template) | `~/sprocfw/step3.md` |

## Appendix B: Sample Functions by Complexity

| Function | Complexity | Notes |
|----------|------------|-------|
| `addDays.sql`, `trunc.sql` | Simple | Oracle compatibility wrappers |
| `C_Currency_Convert.sql` | Medium | Utility, called by many others |
| `C_Invoice_Open.sql` | Complex | Currency conversion, loops, exception handling |
| `nextID.sql` | Critical | Sequence/ID generation, heavily used |
