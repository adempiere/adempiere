# Wave 4: Standalone Functions - Design Summary

**Date:** 2026-01-07
**Status:** Design Complete
**Parent Design:** `2026-01-01-postgresql-function-migration-design.md`
**Reference Template:** `~/sprocfw/step3.md`

---

## 1. Wave 4 Scope

**Goal:** Migrate 9 independent functions with minimal dependencies
**Effort:** 4-5 days
**Dependencies:** Minimal (Wave 0 foundation utilities only)

### Functions

| Priority | Function | File | LOC | Complexity | Usage |
|----------|----------|------|-----|------------|-------|
| 1 | nextID | nextID.sql | 56 | Medium | Java (CallableStatement) |
| 2 | nextIDFunc | nextIDFunc.sql | 14 | Low | Java |
| 3 | acctBalance | Acct_Balance.sql | 42 | Low | Java |
| 4 | productAttribute | ProductAttribute.sql | 93 | Medium | Java + Views |
| 5 | documentNo | documentNo.sql | 51 | Medium | Views |
| 6 | get_Sysconfig | get_Sysconfig.sql | 47 | Low | Java |
| 7 | linenetamtrealinvoiceline | linenetamtrealinvoiceline.sql | 18 | Low | Java |
| 8 | linenetamtrealorderline | linenetamtrealorderline.sql | 18 | Low | Java |
| 9 | maxpaydate | maxpaydate.sql | 20 | Low | Java |

### Risk Classification

| Risk Level | Functions | Reason |
|------------|-----------|--------|
| **High** | nextID, nextIDFunc | Stateful (sequence generation), concurrent access |
| **Medium** | documentNo, productAttribute | View dependencies, moderate complexity |
| **Low** | acctBalance, get_Sysconfig, linenetamt*, maxpaydate | Simple logic, isolated usage |

---

## 2. Validation Strategy

Wave 4 uses a **hybrid validation strategy** based on function characteristics.

### 2.1 Stateful Functions (Dual-Write Logging)

**Applies to:** `nextID`, `nextIDFunc`

**Why Dual-Write, Not Shadow:**
- Dual execution would consume sequence IDs twice
- Staged rollout (random Java/SQL split) causes inconsistent IDs within same transaction
- Shadow mode incompatible with sequence state

**Flow:**
```
Request arrives
    → Execute Java implementation
    → Emit structured log: {inputs, sequencePositions, output, timestamp}
    → Return Java result immediately

Background replay job:
    → Read logged executions
    → Validate increment pattern (not absolute values)
    → Report mismatches
```

**Validation Approach:**

| Phase | Activity | Success Criteria |
|-------|----------|------------------|
| 1 | Unit tests | All edge cases pass |
| 2 | Integration tests (100 threads) | No duplicate IDs |
| 3 | Dual-Write Logging in production | 100% coverage |
| 4 | Replay validation | Pattern matching confirms equivalent behavior |
| 5 | Cutover to JAVA_ONLY | Monitor 24h |

**Rollback Trigger:** Any duplicate ID detected → immediate revert to SQL_ONLY.

### 2.2 Standard Functions (Shadow Mode)

**Applies to:** `acctBalance`, `get_Sysconfig`, `linenetamtrealinvoiceline`, `linenetamtrealorderline`, `maxpaydate`

**Sample Rate:** 100% (low-frequency functions)

**Flow:**
```
Check feature flag
    → Execute Java logic
    → Execute SQL function via JDBC (REPEATABLE READ isolation)
    → Compare results
    → Log mismatches (async)
    → Return Java result
```

### 2.3 View-Dependent Functions (Shadow Mode)

**Applies to:** `documentNo`, `productAttribute`

**Sample Rate:** 10-100% depending on call volume

**Additional Requirement:** Migrate dependent views as part of the same transaction unit.

### 2.4 Escape Hatch: Trigger-Based Validation

For low-frequency functions where shadow infrastructure proves too complex:

```sql
-- Add logging trigger to existing SQL function
CREATE OR REPLACE FUNCTION migration.log_function_call()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO migration.trigger_log (function_name, inputs, output, called_at)
    VALUES (TG_ARGV[0], row_to_json(NEW), TG_ARGV[1], NOW());
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
```

Use for: Any Wave 4 function if shadow mode causes operational issues.

---

## 3. Feature Flag Configuration

All flags stored in `migration.function_config` table.

### Initial Flag Values

| Function | Initial Mode | Performance Tier | Sample Rate |
|----------|--------------|------------------|-------------|
| nextID | DUAL_WRITE | CRITICAL | N/A |
| nextIDFunc | DUAL_WRITE | CRITICAL | N/A |
| documentNo | SHADOW | CRITICAL | 0.10 |
| productAttribute | SHADOW | STANDARD | 1.0 |
| acctBalance | SHADOW | STANDARD | 1.0 |
| get_Sysconfig | SHADOW | STANDARD | 1.0 |
| linenetamtrealinvoiceline | SHADOW | STANDARD | 1.0 |
| linenetamtrealorderline | SHADOW | STANDARD | 1.0 |
| maxpaydate | SHADOW | STANDARD | 1.0 |

### Flag Values

| Flag Value | Behavior |
|------------|----------|
| `SQL_ONLY` | Legacy path, Java not ready |
| `SHADOW` | Both execute, compare, return Java |
| `DUAL_WRITE` | Java executes, logs for replay validation |
| `JAVA_ONLY` | SQL function can be deleted |

---

## 4. Performance Requirements

### Tiered Latency Budgets

| Tier | Max Latency Increase (p95) | Wave 4 Functions |
|------|---------------------------|------------------|
| **CRITICAL** | 5% | nextID, nextIDFunc, documentNo |
| **STANDARD** | 30% | All others |

### Baseline Capture Requirements

| Parameter | Specification |
|-----------|---------------|
| Load conditions | Typical business hours |
| Duration | 1 hour of sampling |
| Minimum samples | 1,000 calls (or 100% if lower frequency) |
| Metrics captured | p50, p95, p99, max |

---

## 5. Code Location Pattern

| Function | Java Location | Class |
|----------|---------------|-------|
| nextID | Instance method | `MSequence` |
| nextIDFunc | Static wrapper | `MSequence` |
| acctBalance | Static method | `MAccount` or `MAcctSchema` |
| productAttribute | Instance method | `MProduct` or `MAttributeSetInstance` |
| documentNo | Instance method | `MSequence` or `PO` base class |
| get_Sysconfig | Static method | `MSysConfig` |
| linenetamtrealinvoiceline | Instance method | `MInvoiceLine` |
| linenetamtrealorderline | Instance method | `MOrderLine` |
| maxpaydate | Static method | `MPayment` or query utility |

---

## 6. Quality Gates

### Gate 1: Code Complete
- [ ] Java implementation matches SQL logic
- [ ] Unit tests pass (including edge cases)
- [ ] Integration tests pass

### Gate 2: Validation Ready
- [ ] Feature flag set to SHADOW or DUAL_WRITE
- [ ] Mismatch logging configured
- [ ] Performance baseline captured
- [ ] Java within tier budget (CRITICAL: 5%, STANDARD: 30%)
- [ ] Rollback drill completed (if not done in earlier wave)

### Gate 3: Cutover Approved
- [ ] 99.9% match rate for 7 days
- [ ] No critical mismatches unresolved
- [ ] Dependent views migrated (documentNo, productAttribute)

### Gate 4: Cleanup Complete
- [ ] Feature flag set to JAVA_ONLY
- [ ] SQL function retained in git (deleted after 30 days stable)
- [ ] Monitoring confirms no errors

---

## 7. Match Rate Calculation

```
Match Rate = (Total Calls - Mismatches) / Total Calls x 100

Exclusions from mismatch count:
- Timing differences (within 1 second tolerance)
- Floating point precision (within 4 decimal places)
- Null vs empty string (if configured as equivalent)
```

**Configurable per-function in `migration.function_config.tolerance_config`:**
```json
{
    "timestamp_seconds": 1,
    "decimal_precision": 4,
    "null_empty_equivalent": true
}
```

---

## 8. nextID/nextIDFunc Special Handling

### Concurrency Requirements

The `nextID` function is called during record creation. Java implementation must:

1. **Use pessimistic locking** on sequence row
2. **Handle concurrent access** from multiple threads/processes
3. **Maintain atomicity** - no gaps, no duplicates

### Test Requirements

| Test Type | Specification |
|-----------|---------------|
| Unit | Sequence logic in isolation |
| Integration | 100 concurrent threads |
| Stress | 10,000 calls in 60 seconds |

### Validation Pattern

Since absolute IDs cannot match (SQL consumed different values), validate:
- Increment pattern: each call returns previous + increment
- No duplicate IDs generated
- No gaps (unless explicitly allowed by sequence config)

---

## 9. View Dependencies

**Analysis Date:** 2026-01-07
**Database:** PostgreSQL 15.15 on adempiere schema

### Summary

Query identified **13 distinct views** (with translations) that depend on Wave 4 functions:
- **12 views** use `productAttribute(m_attributesetinstance_id)`
- **1 view** uses `documentNo(pp_mrp_id)`
- **0 views** use other Wave 4 functions (acct_balance, linenetamtreal*, maxpaydate, get_sysconfig)

### productAttribute Dependencies

**Function:** `productAttribute(m_attributesetinstance_id)` - Returns product attribute description

**Affected Views (12):**

| View Name | Application Dictionary | Used In | Migration Decision |
|-----------|------------------------|---------|-------------------|
| c_invoice_linetax_v | Yes | UI Tabs | PostgreSQL-only |
| c_invoice_linetax_vt | Yes (translation) | UI Tabs | PostgreSQL-only |
| c_order_linetax_v | Yes | Sales Order Window | PostgreSQL-only |
| c_order_linetax_vt | Yes (translation) | Sales Order Window | PostgreSQL-only |
| c_rfqresponseline_v | Yes | RfQ Processing | PostgreSQL-only |
| c_rfqresponseline_vt | Yes (translation) | RfQ Processing | PostgreSQL-only |
| m_inout_line_v | Yes | Shipment/Receipt | PostgreSQL-only |
| m_inout_line_vt | Yes (translation) | Shipment/Receipt | PostgreSQL-only |
| rv_c_invoiceline | Yes | Invoice Inquiry Tab | PostgreSQL-only |
| rv_dd_orderdetail | Yes | Distribution Order | PostgreSQL-only |
| rv_inoutdetails | Yes | Shipment Details | PostgreSQL-only |
| rv_orderdetail | Yes | Order Detail Tab/Report | PostgreSQL-only |

**Usage Pattern:**
All views concatenate `productAttribute()` output to product name for display:
```sql
COALESCE(c.name, (p.name || COALESCE(productattribute(il.m_attributesetinstance_id), ''))::varchar, il.description)
```

**Impact Assessment:**
- **UI Impact:** Views power read-only tabs and reports in the application
- **Java Usage:** ADempiere framework queries these views via ResultSet, no direct Java calls to function
- **Behavior:** After Wave 4, views will continue calling PostgreSQL version of `productAttribute()`
- **Migration Required:** NO - These are display-only views, can remain PostgreSQL

**Reasoning:**
1. Views serve reporting/display purposes (not transactional)
2. Product attribute formatting is presentation logic, appropriate for database views
3. No performance or correctness issues with views calling PostgreSQL function
4. Migrating 12 views would expand Wave 4 scope unnecessarily

### documentNo Dependencies

**Function:** `documentNo(pp_mrp_id)` - Generates document number for MRP records

**Affected Views (1):**

| View Name | Application Dictionary | Used In | Migration Decision |
|-----------|------------------------|---------|-------------------|
| rv_pp_mrp | Yes | View MRP Records Window, MRP Detail Tab | PostgreSQL-only |

**Usage Pattern:**
```sql
SELECT ..., documentno(mrp.pp_mrp_id) AS documentno, ...
FROM pp_mrp mrp
```

**Impact Assessment:**
- **UI Impact:** View powers "View MRP Records" window (manufacturing module)
- **Java Usage:** `WMRPDetailed.java` and `MRPDetailed.java` reference view via table name
- **Behavior:** After Wave 4, view will continue calling PostgreSQL version of `documentNo()`
- **Migration Required:** NO - Read-only view for MRP record display

**Reasoning:**
1. View is manufacturing-specific reporting interface
2. Document number generation in view context is display-only
3. No transactional usage - manufacturing processes call function directly from Java
4. Single view migration would be out of scope for Wave 4

### Other Wave 4 Functions

**Functions with NO view dependencies:**
- `acctBalance` - No views found
- `linenetamtrealinvoiceline` - No views found
- `linenetamtrealorderline` - No views found
- `maxpaydate` - No views found
- `get_Sysconfig` - No views found
- `nextID` - No views (sequence generation, not query-accessible)
- `nextIDFunc` - No views (sequence generation, not query-accessible)

### Migration Strategy

**For Wave 4 Implementation:**

1. **Proceed with function migration** - No blocking view dependencies identified
2. **Leave views as-is** - All dependent views can remain PostgreSQL-only
3. **No view migration required** - Views will continue calling PostgreSQL functions via database engine
4. **Document in cutover plan** - Note that views retain PostgreSQL function calls (expected behavior)

**Future Consideration:**

If ADempiere eventually migrates to non-PostgreSQL database:
- Views using `productAttribute()` would need conversion to Java queries
- View using `documentNo()` would need conversion or alternative implementation
- Estimated effort: 2-3 days to migrate all 13 views to Java query methods

But for Wave 4 PostgreSQL-to-Java migration: **No view changes needed**.

### Gate Check Result

**PASS** - No blocking conditions detected:

- [ ] ~~A view is called from Java code AND cannot be migrated before Wave 4~~
  - Views are called via ADempiere framework (ResultSet), not direct function calls
  - Views can remain PostgreSQL-only during and after Wave 4

- [ ] ~~A view is critical for production operations AND depends on function behavior~~
  - Views are display/reporting only, not transactional
  - Function behavior in views is for formatting/display, not business logic

- [ ] ~~View migration would require changes beyond Wave 4 scope~~
  - No view migration required
  - Views will continue using PostgreSQL versions of functions

**Conclusion:** Wave 4 can proceed to Task Group 1 without view-related blockers.

---

## 10. Rollback Procedure

### Per-Function Rollback

```bash
# 1. Set feature flag to SQL_ONLY
UPDATE migration.function_config
SET mode = 'SQL_ONLY'
WHERE function_name = '{function_name}';

# 2. If SQL function was deleted, restore from git
git show HEAD:db/ddlutils/postgresql/functions/{function_name}.sql | psql

# 3. Verify function exists
SELECT proname FROM pg_proc WHERE proname = '{function_name}';

# 4. Test with known inputs
SELECT {function_name}({test_params});
```

### Cross-Wave Dependencies

Wave 4 has **no downstream dependencies** - can be rolled back independently.

---

## 11. Monitoring & Alerts

### Key Metrics

| Metric | Alert Threshold |
|--------|-----------------|
| Match rate | < 99.9% → page on-call |
| Java p95 (CRITICAL tier) | > 5% baseline → warning |
| Java p95 (STANDARD tier) | > 30% baseline → warning |
| nextID duplicate detection | Any → immediate alert |
| Replay backlog (dual-write) | > 10,000 entries → warning |

### Dashboard Queries

```sql
-- Match rate by function (last 24 hours)
SELECT function_name,
       COUNT(*) as total_calls,
       SUM(CASE WHEN is_match THEN 1 ELSE 0 END) * 100.0 / COUNT(*) as match_rate
FROM migration.function_log
WHERE created_at > NOW() - INTERVAL '24 hours'
  AND function_name IN ('nextID', 'nextIDFunc', 'acctBalance',
                        'productAttribute', 'documentNo', 'get_Sysconfig',
                        'linenetamtrealinvoiceline', 'linenetamtrealorderline',
                        'maxpaydate')
GROUP BY function_name;

-- Dual-write replay status (nextID functions)
SELECT function_name,
       COUNT(*) FILTER (WHERE processed) as replayed,
       COUNT(*) FILTER (WHERE NOT processed) as pending,
       COUNT(*) FILTER (WHERE processed AND (replay_result->>'match')::boolean = false) as mismatches
FROM migration.function_execution_log
WHERE function_name IN ('nextID', 'nextIDFunc')
  AND created_at > NOW() - INTERVAL '24 hours'
GROUP BY function_name;
```

---

## 12. Success Criteria

Wave 4 is complete when:

1. All 9 functions implemented in Java
2. All functions pass Gate 4 (JAVA_ONLY, stable for 30 days)
3. No production regressions
4. SQL functions retained in git for emergency rollback
5. Dependent views (productAttribute, documentNo) migrated or documented as PostgreSQL-only

---

## 13. Risks & Mitigations

| Risk | Likelihood | Impact | Mitigation |
|------|------------|--------|------------|
| nextID duplicate ID under concurrency | LOW | HIGH | 100-thread integration tests before validation mode |
| documentNo view dependencies missed | MEDIUM | MEDIUM | Full dependency scan before implementation |
| Performance regression on nextID | MEDIUM | HIGH | CRITICAL tier (5% budget), baseline capture |
| Shadow mode for low-freq functions adds complexity | LOW | LOW | Trigger-based escape hatch available |

---

## Appendix: File Locations

| Resource | Path |
|----------|------|
| nextID.sql | `db/ddlutils/postgresql/functions/nextID.sql` |
| nextIDFunc.sql | `db/ddlutils/postgresql/functions/nextIDFunc.sql` |
| Acct_Balance.sql | `db/ddlutils/postgresql/functions/Acct_Balance.sql` |
| ProductAttribute.sql | `db/ddlutils/postgresql/functions/ProductAttribute.sql` |
| documentNo.sql | `db/ddlutils/postgresql/functions/documentNo.sql` |
| get_Sysconfig.sql | `db/ddlutils/postgresql/functions/get_Sysconfig.sql` |
| linenetamtrealinvoiceline.sql | `db/ddlutils/postgresql/functions/linenetamtrealinvoiceline.sql` |
| linenetamtrealorderline.sql | `db/ddlutils/postgresql/functions/linenetamtrealorderline.sql` |
| maxpaydate.sql | `db/ddlutils/postgresql/functions/maxpaydate.sql` |
| MSequence | `base/src/org/compiere/model/MSequence.java` |
| MSysConfig | `base/src/org/compiere/model/MSysConfig.java` |
| Per-function template | `~/sprocfw/step3.md` |
