# Dead Code Candidates Report

**Generated:** 2026-01-01
**Total Candidates:** 28 functions (36% of all functions)
**Recommendation:** Do not migrate - deprecate or remove

---

## Summary

| Category | Count | Recommendation |
|----------|-------|----------------|
| DDL Utilities | 3 | DO NOT MIGRATE - Admin only |
| HR/Payroll | 6 | DEPRECATE - Entire category unused |
| BOM ASI Variants | 4 | DEPRECATE - No callers |
| Utility Functions | 6 | DEPRECATE or keep internal |
| Other Business | 9 | DEPRECATE - No callers |

---

## Category 1: DDL Utilities (DO NOT MIGRATE)

These are PostgreSQL administrative functions for schema modifications. They should remain as SQL and never be migrated to Java.

| Function | File | LOC | Purpose |
|----------|------|-----|---------|
| altercolumn | altercolumn.sql | 99 | Alters column definitions with dependency handling |
| deps_save_and_drop_dependencies | viewsDependencies.sql | 607 | Saves and drops view dependencies before DDL |
| deps_restore_dependencies | viewsDependencies.sql | 670 | Restores view dependencies after DDL |

**Reason:** These are database schema management utilities, not business logic. They manipulate PostgreSQL system catalogs and execute dynamic DDL.

**Action:** Keep in PostgreSQL, exclude from migration scope.

---

## Category 2: HR/Payroll Functions (DEPRECATE)

The entire HR/Payroll category has **zero callers** in Java code or views. This suggests the functionality was either:
- Planned but never implemented
- Replaced by alternative implementation
- Part of an unused module

| Function | File | LOC | Parameters |
|----------|------|-----|------------|
| dailySalary | dailySalary.sql | 37 | p_C_BPartner_ID |
| dailySalaryToDate | dailySalaryToDate.sql | 66 | p_C_BPartner_ID, DateTo |
| dailySalaryToDateByHRProcess | DailySalaryToDateByHrProcess.sql | 55 | p_C_BPartner_ID, p_HR_Process_ID, DateTo |
| monthlySalary | monthlySalary.sql | 37 | p_C_BPartner_ID |
| monthlySalaryToDate | monthlySalaryToDate.sql | 66 | p_C_BPartner_ID, DateTo |
| ProcessReportSource | ProcessReportSource.sql | 79 | HR_Process_ID, C_BPartner_ID, HR_ProcessReportLine_ID, _From, _To |

**Total LOC:** 340 lines

**Dependencies:**
- dailySalary -> dailySalaryToDate, getDate
- monthlySalary -> monthlySalaryToDate, getDate
- Others are standalone

**Action:** Verify with stakeholders if HR module is in use. If not, mark for deprecation.

---

## Category 3: BOM ASI Variants (DEPRECATE)

These are Attribute Set Instance (ASI) variants of the BOM quantity functions. The non-ASI versions are actively used, but these ASI-specific overloads have no callers.

| Function | File | LOC | Additional Parameter |
|----------|------|-----|---------------------|
| bomQtyAvailableASI | BOM_Qty_AvailableASI.sql | 28 | AttributeSetInstance_ID |
| bomQtyOnHandASI | BOM_Qty_OnHandASI.sql | 139 | AttributeSetInstance_ID |
| bomQtyOrderedASI | BOM_Qty_OrderedASI.sql | 145 | AttributeSetInstance_ID |
| bomQtyReservedASI | BOM_Qty_ReservedASI.sql | 145 | AttributeSetInstance_ID |

**Total LOC:** 457 lines

**Pattern:** These are identical to their non-ASI counterparts except they filter by AttributeSetInstance_ID. The lack of usage suggests ASI-level BOM quantity tracking was never implemented in the UI/reports.

**Action:**
1. Verify no custom reports use these functions
2. If unused, mark for deprecation
3. If needed, they share 90% code with non-ASI versions - could be unified

---

## Category 4: Unused Utility Functions

These utility functions exist but have no external callers. Some are called internally by other functions.

| Function | File | LOC | Called By | Notes |
|----------|------|-----|-----------|-------|
| subtractdays | addDays.sql | 41 | None | Wrapper for addDays with negative |
| addweeks | addweeks_postgresql.sql | 47 | None | Oracle compatibility |
| addyears | addyears_postgresql.sql | 47 | None | Oracle compatibility |
| instr | INSTR.sql | 20 | None | Oracle compatibility for INSTR() |
| nextIDByYear | NextIDByYear.sql | 25 | None | Year-based sequence (unused feature) |
| getUUID | getUUIDWithPGCrypto.sql | 36 | None | UUID generation via pgcrypto |

**Total LOC:** 216 lines

**Analysis:**
- `subtractdays`, `addweeks`, `addyears` - Oracle compatibility wrappers, PostgreSQL has native equivalents
- `instr` - PostgreSQL has native `POSITION()` function
- `nextIDByYear` - Feature appears unused (no year-based sequences)
- `getUUID` - PostgreSQL 13+ has `gen_random_uuid()` built-in

**Action:** Keep only if there's a chance they're called from custom SQL or reports. Otherwise deprecate.

---

## Category 5: Other Unused Business Functions

| Function | File | LOC | Purpose | Notes |
|----------|------|-----|---------|-------|
| add_months | Add_Months.sql | 14 | Date arithmetic | Internal: called by paymentTermDueDate |
| bpartnerRemitLocation | C_BPartner_RemitLocation.sql | 25 | Get remit-to location | Never called |
| currencyBaseType | C_Currency_Base_Type.sql | 52 | Convert to base currency with type | Wrapper for currencyConvert, unused |
| financialRateToDate | financialRateToDate.sql | 43 | Get financial rate | Never called |
| nextBusinessDay | nextBusinessDay.sql | 59 | Skip weekends/holidays | Internal: called by paymentTermDiscount |
| prodQtyOrdered | prodQtyOrdered.sql | 66 | Product qty ordered | Replaced by bomQtyOrdered? |
| prodQtyReserved | Prod_Qty_Reserved.sql | 96 | Product qty reserved | Replaced by bomQtyReserved? |

**Total LOC:** 355 lines

**Analysis:**
- `add_months`, `nextBusinessDay` - Internal functions, keep as Java helpers
- `bpartnerRemitLocation` - Feature never used
- `currencyBaseType` - Redundant wrapper
- `prodQtyOrdered`, `prodQtyReserved` - Appear to be superseded by BOM versions

---

## Verification Checklist

Before deprecating, verify these functions are not called from:

- [ ] Custom SQL reports (AD_Report definitions)
- [ ] JasperReports templates
- [ ] External systems via direct SQL
- [ ] Stored procedures or triggers (not in standard codebase)
- [ ] Database-level constraints or computed columns

### SQL to Find Usage in Reports

```sql
-- Check if functions are used in report definitions
SELECT r.Name, r.ReportType, rd.SelectClause
FROM AD_Report r
JOIN AD_ReportColumn rd ON r.AD_Report_ID = rd.AD_Report_ID
WHERE rd.SelectClause ILIKE '%dailySalary%'
   OR rd.SelectClause ILIKE '%bomQty%ASI%'
   OR rd.SelectClause ILIKE '%prodQty%';
```

---

## Migration Savings

By not migrating dead code:

| Metric | Value |
|--------|-------|
| Functions avoided | 28 |
| Lines of SQL avoided | ~1,400 |
| Estimated days saved | 5-8 days |
| Test cases avoided | ~50-100 |

---

## Recommended Actions

### Immediate (Before Migration)

1. **DDL Utilities (3 functions)**
   - Status: EXCLUDED
   - Action: Document as out-of-scope

2. **HR/Payroll (6 functions)**
   - Status: VERIFY
   - Action: Confirm with stakeholders, then deprecate

3. **BOM ASI Variants (4 functions)**
   - Status: VERIFY
   - Action: Search for usage in reports, then deprecate

### Deferred (Post-Migration)

4. **Utility Functions (6 functions)**
   - Status: LOW PRIORITY
   - Action: Migrate only if usage discovered

5. **Other Business (9 functions)**
   - Keep internal helpers (add_months, nextBusinessDay)
   - Deprecate truly unused (bpartnerRemitLocation, etc.)

---

## Deprecation Process

1. **Mark as deprecated in SQL**
   ```sql
   -- DEPRECATED: Function not used in Java or views
   -- Scheduled for removal in version X.Y
   CREATE OR REPLACE FUNCTION dailySalary(...) ...
   ```

2. **Add logging to catch hidden usage**
   ```sql
   CREATE OR REPLACE FUNCTION dailySalary(...)
   RETURNS NUMERIC AS $$
   BEGIN
       RAISE NOTICE 'DEPRECATED: dailySalary called - please report this usage';
       -- Original logic
   END;
   $$ LANGUAGE plpgsql;
   ```

3. **Monitor for 1-2 release cycles**

4. **Remove if no usage detected**

---

## Appendix: Complete Dead Code List

| # | Function | File | LOC | Category |
|---|----------|------|-----|----------|
| 1 | altercolumn | altercolumn.sql | 99 | ddl-utility |
| 2 | deps_save_and_drop_dependencies | viewsDependencies.sql | 607 | ddl-utility |
| 3 | deps_restore_dependencies | viewsDependencies.sql | 670 | ddl-utility |
| 4 | dailySalary | dailySalary.sql | 37 | hr-payroll |
| 5 | dailySalaryToDate | dailySalaryToDate.sql | 66 | hr-payroll |
| 6 | dailySalaryToDateByHRProcess | DailySalaryToDateByHrProcess.sql | 55 | hr-payroll |
| 7 | monthlySalary | monthlySalary.sql | 37 | hr-payroll |
| 8 | monthlySalaryToDate | monthlySalaryToDate.sql | 66 | hr-payroll |
| 9 | ProcessReportSource | ProcessReportSource.sql | 79 | hr-payroll |
| 10 | bomQtyAvailableASI | BOM_Qty_AvailableASI.sql | 28 | bom-quantity |
| 11 | bomQtyOnHandASI | BOM_Qty_OnHandASI.sql | 139 | bom-quantity |
| 12 | bomQtyOrderedASI | BOM_Qty_OrderedASI.sql | 145 | bom-quantity |
| 13 | bomQtyReservedASI | BOM_Qty_ReservedASI.sql | 145 | bom-quantity |
| 14 | subtractdays | addDays.sql | 41 | utility-date |
| 15 | addweeks | addweeks_postgresql.sql | 47 | utility-date |
| 16 | addyears | addyears_postgresql.sql | 47 | utility-date |
| 17 | instr | INSTR.sql | 20 | utility-string |
| 18 | nextIDByYear | NextIDByYear.sql | 25 | id-generation |
| 19 | getUUID | getUUIDWithPGCrypto.sql | 36 | utility |
| 20 | add_months | Add_Months.sql | 14 | utility-date |
| 21 | bpartnerRemitLocation | C_BPartner_RemitLocation.sql | 25 | business-partner |
| 22 | currencyBaseType | C_Currency_Base_Type.sql | 52 | currency |
| 23 | financialRateToDate | financialRateToDate.sql | 43 | financial |
| 24 | nextBusinessDay | nextBusinessDay.sql | 59 | utility-date |
| 25 | prodQtyOrdered | prodQtyOrdered.sql | 66 | product |
| 26 | prodQtyReserved | Prod_Qty_Reserved.sql | 96 | product |

**Note:** Functions 20 (add_months) and 24 (nextBusinessDay) are called internally by other functions and will need to be migrated as part of their respective transaction units.
