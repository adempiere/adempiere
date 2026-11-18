---
date: 2026-01-07T05:53:17+00:00
researcher: Claude
git_commit: 133d68a886494ea95da696ee7313a35c6591e1d8
branch: wave3
repository: adempiere
topic: "Wave 3 Cutover Bug Fix - C_Invoice_v Aggregation Issue"
tags: [wave3, bug-fix, invoiceOpen, invoiceOpenToDate, cutover, rollback]
status: in_progress
last_updated: 2026-01-07
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: Wave3 Cutover Bug Fix - C_Invoice_v Aggregation Issue

## Task(s)

| Task | Status |
|------|--------|
| Run 5 sequential test runs for Gate 3 validation | **Completed** |
| Update quality gate documentation | **Completed** |
| Execute cutover to JAVA_ONLY mode | **Completed then Rolled Back** |
| Fix C_Invoice_v aggregation bug in InvoiceFunctions.java | **In Progress** |

**Context:** After successfully completing 5 shadow execution test runs (all passing), user approved cutover. Cutover to JAVA_ONLY mode was executed. Post-cutover verification tests revealed failures that were NOT present in pre-cutover runs. Investigation revealed a bug in the Java implementation that wasn't being caught because of non-deterministic test ordering.

**CRITICAL:** System is currently **ROLLED BACK to SHADOW mode** - cutover has NOT been completed.

## Critical References

1. **Quality Gates Document:** `docs/plans/wave3-quality-gates.md` - Current status and validation results
2. **InvoiceFunctions.java:** `base/src/org/compiere/util/InvoiceFunctions.java:220-225, 398-404` - Bug location (partially fixed)
3. **SQL Function Reference:** `db/ddlutils/postgresql/functions/C_Invoice_OpenToDate.sql:58-62` - Correct behavior

## Recent changes

**Partial fix applied (incomplete):**
- `base/src/org/compiere/util/InvoiceFunctions.java:220-225` - Changed to use `MAX/SUM` aggregation for `calculateInvoiceOpenJava()`
- `base/src/org/compiere/util/InvoiceFunctions.java:398-404` - Changed to use `MAX/SUM` aggregation for `calculateInvoiceOpenToDateJava()` BUT incorrectly removed the DateAcct filter

## Learnings

### Root Cause: C_Invoice_v View Returns Multiple Rows for Payment Schedules

The `C_Invoice_v` view returns **multiple rows** for invoices that have payment schedules (`IsPayScheduleValid='Y'`). For example, invoice 109 has 2 payment schedule lines, so the view returns:

```
c_invoice_id | grandtotal
109          | 114.43
109          | 114.42
```

**SQL function behavior (correct):**
- Uses `SUM(GrandTotal)` to get total: 228.85
- Uses `MAX(C_Currency_ID)`, `MAX(MultiplierAP)`, `MAX(Multiplier)` for other fields

**Java implementation (bug):**
- Only read first row: 114.43 (exactly half!)
- This caused `invoiceOpen(109, null)` to return 114.43 instead of 228.85

### Why Tests Passed Before

The test data queries (`Wave3ShadowIntegrationTest.java:67-69`) don't have ORDER BY, so database returns invoices in non-deterministic order. Invoice 109 (the one with payment schedules) wasn't consistently included in the test set during the 5 successful runs. After running the migration script, database state changed slightly causing different query execution plans.

### invoiceOpenToDate Has Additional Complexity

The SQL function `invoiceOpenToDate` at line 62 has:
```sql
AND DateAcct <= p_DateAcct
```

This means if historical date (2020-01-01) is before invoice's DateAcct, no rows match and function returns 0. The Java implementation needs this filter but must still use aggregation.

## Artifacts

- `docs/plans/wave3-quality-gates.md` - Updated with Gate 3 results and cutover attempt
- `base/src/org/compiere/util/InvoiceFunctions.java` - Partially fixed (needs more work)
- `db/ddlutils/postgresql/migrations/003_wave3_function_config.sql` - Was run during session

## Action Items & Next Steps

### Immediate (Fix the Bug)

1. **Fix `calculateInvoiceOpenToDateJava()`** at `base/src/org/compiere/util/InvoiceFunctions.java:398-408`:
   - Add back the `DateAcct <= ?` filter to the aggregation query
   - Add back `pstmt.setTimestamp(2, cutoffDate)` parameter binding
   - Handle case where aggregation returns NULL values (no matching rows = return 0)

   The query should be:
   ```java
   String headerSql = "SELECT MAX(C_Currency_ID) AS C_Currency_ID, SUM(GrandTotal) AS GrandTotal, "
       + "MAX(MultiplierAP) AS MultiplierAP, MAX(Multiplier) AS Multiplier "
       + "FROM C_Invoice_v "
       + "WHERE C_Invoice_ID=? AND DateAcct <= ?";
   ```

   And handle NULL result (no rows matched date filter) by returning 0 instead of null.

2. **Run tests** to verify fix: `ant wave3-tests`

3. **Re-run 5 sequential test runs** to confirm stability

4. **Re-attempt cutover** after all tests pass

### Database State

Current function_config state (SHADOW mode - safe):
```sql
SELECT function_name, mode FROM migration.function_config
WHERE function_name IN ('invoiceOpen','invoiceOpenToDate','invoiceDiscount','invoicePaid','invoicePaidToDate','paymentAllocated','paymentAvailable');
```

All 7 functions are in SHADOW mode.

## Other Notes

### Test Failure Counts During Session

| State | ShadowIntegration Failures |
|-------|---------------------------|
| Before cutover (5 runs) | 0 |
| After cutover (JAVA_ONLY) | 11 |
| After rollback (SHADOW) | 11 |
| After partial fix (aggregation) | 8 |

### Key Database Queries for Debugging

Check if invoice has multiple rows in view:
```sql
SELECT C_Invoice_ID, C_Currency_ID, GrandTotal FROM C_Invoice_v WHERE C_Invoice_ID = 109;
```

Check payment schedules:
```sql
SELECT C_InvoicePaySchedule_ID, DueAmt FROM C_InvoicePaySchedule WHERE C_Invoice_ID = 109;
```

Check invoices with payment schedules that cause view to have multiple rows:
```sql
SELECT C_Invoice_ID, DocumentNo, IsPayScheduleValid,
  (SELECT COUNT(*) FROM C_Invoice_v v WHERE v.C_Invoice_ID = i.C_Invoice_ID) as view_rows
FROM C_Invoice i
WHERE DocStatus IN ('CO','CL') AND view_rows > 1;
```

### Files Modified (Uncommitted)

```
M docs/plans/wave3-quality-gates.md
M base/src/org/compiere/util/InvoiceFunctions.java  (partial fix - needs completion)
```
