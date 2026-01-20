---
date: 2026-01-01T22:29:11+00:00
researcher: Claude
git_commit: 84f4d611f0671c63c57eb5458a0b9bb7591e4e99
branch: develop
repository: adempiere
topic: "PostgreSQL Function Migration Discovery - Phase 1 Execution"
tags: [discovery, postgresql, functions, migration, inventory]
status: in_progress
last_updated: 2026-01-01
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: PostgreSQL Function Discovery & Inventory

## Task(s)

**Executing Phase 1 Discovery** against ADempiere's PostgreSQL functions to produce migration inventory deliverables.

| Task | Status |
|------|--------|
| Get list of all PostgreSQL function files | **Completed** - Found 62 SQL files |
| Parse function files for metadata | **In Progress** - Read 5 of 62 files |
| Search Java codebase for function callers | Planned |
| Search SQL views for function callers | Planned |
| Build dependency graph and detect circular dependencies | Planned |
| Score complexity for each function | Planned |
| Identify transaction units and dead code candidates | Planned |
| Plan migration waves | Planned |
| Write all deliverables to docs/discovery/ | Planned |

**Context**: Using brainstorming skill to execute the discovery framework from `~/sprocfw/step2.md`. User confirmed:
- No database access available - working from source files only
- Full set of 8 deliverables requested
- Output to `docs/discovery/` directory
- Claude performs the analysis directly (not generating scripts)

## Critical References

1. `~/sprocfw/step2.md` - The Phase 1 Discovery & Assessment Plan (v2.2) being executed - comprehensive 4000+ line framework document
2. `/home/yv01p/adempiere/docs/plans/2026-01-01-postgresql-function-migration-design.md` - Existing design doc for the migration approach (shadow execution, quality gates)
3. `/home/yv01p/adempiere/docs/plans/2026-01-01-invoice-sql-to-java-migration-design.md` - Pilot migration design for Invoice functions

## Recent changes

- Created directory: `docs/discovery/` (empty, awaiting deliverables)

## Learnings

### Function File Structure
Functions are located in `db/ddlutils/postgresql/functions/` with these patterns:
- Some files define multiple functions (e.g., `addDays.sql` has 4 functions: addDays, subtractdays, and interval overloads)
- Function naming varies: `invoiceopen` vs `currencyConvert` vs `bomQtyAvailable`
- Dependencies between functions are visible in source (e.g., `invoiceopen` calls `currencyConvert`)

### Complexity Indicators Found
From initial file reads:
- `C_Invoice_Open.sql`: 121 LOC, calls `currencyConvert`, has loops, exception handling - HIGH complexity
- `C_Currency_Convert.sql`: 63 LOC, calls `currencyRate` and `currencyRound` - MEDIUM complexity
- `nextID.sql`: 56 LOC, UPDATE statements, exception handling - MEDIUM complexity, CRITICAL function
- `BOM_Qty_Available.sql`: 26 LOC, calls `bomQtyOnHand` and `bomQtyReserved` - LOW complexity
- `addDays.sql`: 72 LOC, multiple function overloads, utility function - LOW complexity

### Function Dependencies Observed
```
invoiceopen -> currencyConvert
currencyConvert -> currencyRate, currencyRound
bomQtyAvailable -> bomQtyOnHand, bomQtyReserved
```

## Artifacts

Created:
- `/home/yv01p/adempiere/docs/discovery/` - Output directory (empty)

To be produced (8 deliverables):
1. `docs/discovery/discovery-inventory.json`
2. `docs/discovery/complexity-report.md`
3. `docs/discovery/dependency-graph.dot`
4. `docs/discovery/function-usage-context.md`
5. `docs/discovery/migration-waves.md`
6. `docs/discovery/transaction-units.json`
7. `docs/discovery/dead-code-candidates.md`
8. `docs/discovery/circular-dependencies.json`

## Action Items & Next Steps

1. **Continue reading function files** - Read remaining 57 SQL files from `db/ddlutils/postgresql/functions/` to extract:
   - Function name(s) per file
   - Parameters and return types
   - Lines of code
   - Dependencies (function calls within source)
   - Complexity indicators (loops, exception handling, dynamic SQL)

2. **Search Java codebase for callers** - Use grep to find:
   ```
   - Direct SQL calls: "invoiceopen", "currencyconvert", etc.
   - CallableStatement usage referencing these functions
   ```

3. **Search SQL views for function usage** - Check `db/ddlutils/postgresql/views/` for functions embedded in view definitions

4. **Build dependency graph** - Create DOT file showing function-to-function dependencies

5. **Score complexity** - Apply weighted formula from step2.md:
   ```
   complexity_score = (LOC * 0.1) + (table_count * 2) + (dependency_depth * 3) + ...
   ```

6. **Identify transaction units** - Group functions that share state or must migrate together

7. **Detect dead code** - Functions with no Java or SQL view callers

8. **Plan migration waves** - Order by dependencies and complexity

## Other Notes

### Complete List of 62 Function Files
```
db/ddlutils/postgresql/functions/
├── Acct_Balance.sql
├── Add_Months.sql
├── BOM_PriceLimit.sql
├── BOM_PriceList.sql
├── BOM_PriceStd.sql
├── BOM_Qty_Available.sql
├── BOM_Qty_AvailableASI.sql
├── BOM_Qty_OnHand.sql
├── BOM_Qty_OnHandASI.sql
├── BOM_Qty_Ordered.sql
├── BOM_Qty_OrderedASI.sql
├── BOM_Qty_Reserved.sql
├── BOM_Qty_ReservedASI.sql
├── C_BPartner_RemitLocation.sql
├── C_Currency_Base.sql
├── C_Currency_Base_Type.sql
├── C_Currency_Convert.sql
├── C_Currency_Rate.sql
├── C_Currency_Round.sql
├── C_Invoice_Discount.sql
├── C_Invoice_Open.sql
├── C_Invoice_OpenToDate.sql
├── C_Invoice_Paid.sql
├── C_Invoice_PaidToDate.sql
├── C_Payment_Allocated.sql
├── C_Payment_Available.sql
├── C_PaymentTerm_Discount.sql
├── C_PaymentTerm_DueDate.sql
├── C_PaymentTerm_DueDays.sql
├── DailySalaryToDateByHrProcess.sql
├── INSTR.sql
├── NextIDByYear.sql
├── ProcessReportSource.sql
├── Prod_Qty_Reserved.sql
├── ProductAttribute.sql
├── addDays.sql
├── addweeks_postgresql.sql
├── addyears_postgresql.sql
├── altercolumn.sql
├── charAt.sql
├── dailySalary.sql
├── dailySalaryToDate.sql
├── daysBetween.sql
├── documentNo.sql
├── financialRateToDate.sql
├── firstOf.sql
├── getDate.sql
├── getUUIDWithPGCrypto.sql
├── getUUIDWithUUID-OSSP.sql
├── get_Sysconfig.sql
├── linenetamtrealinvoiceline.sql
├── linenetamtrealorderline.sql
├── maxpaydate.sql
├── monthlySalary.sql
├── monthlySalaryToDate.sql
├── nextBusinessDay.sql
├── nextID.sql
├── nextIDFunc.sql
├── prodQtyOrdered.sql
├── round.sql
├── trunc.sql
├── viewsDependencies.sql
```

### Existing Design Decisions (from existing docs)
- PostgreSQL-only (no Oracle)
- Shadow execution mode for validation
- 99.9% match rate for 7 days before cutover
- Functions migrate to M* model classes following existing patterns
- Feature flags stored in `migration.function_config` table
- Transaction Units: functions and dependent views migrate together

### Key Functions by Category (from existing design docs)
| Category | Functions | Priority |
|----------|-----------|----------|
| Invoice | invoiceOpen, invoicePaid, invoiceDiscount, invoiceOpenToDate, invoicePaidToDate | High (pilot) |
| Currency | currencyConvert, currencyRate, currencyRound, currencyBase | High (dependency) |
| BOM | bomQtyAvailable, bomQtyOnHand, bomQtyReserved, etc. | Medium |
| ID Generation | nextID, nextIDFunc, NextIDByYear | Critical |
| Utility | addDays, trunc, round, daysBetween | Low |
