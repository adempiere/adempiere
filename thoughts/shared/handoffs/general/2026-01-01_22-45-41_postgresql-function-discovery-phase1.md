---
date: 2026-01-01T22:45:41+00:00
researcher: Claude
git_commit: 84f4d611f0671c63c57eb5458a0b9bb7591e4e99
branch: develop
repository: adempiere
topic: "PostgreSQL Function Discovery - Phase 1 Execution"
tags: [discovery, postgresql, functions, migration, inventory]
status: in_progress
last_updated: 2026-01-01
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: PostgreSQL Function Discovery - Phase 1 Deliverables

## Task(s)

**Executing Phase 1 Discovery** against ADempiere's PostgreSQL functions to produce migration inventory deliverables.

| Task | Status |
|------|--------|
| Parse all 62 SQL function files for metadata | **Completed** |
| Search Java codebase for function callers | **Completed** |
| Search SQL views for function usage | **Completed** |
| Write discovery-inventory.json | **Completed** |
| Build dependency graph (dependency-graph.dot) | **Completed** |
| Detect circular dependencies (circular-dependencies.json) | In Progress |
| Score complexity and write complexity-report.md | Planned |
| Write function-usage-context.md | Planned |
| Identify transaction units (transaction-units.json) | Planned |
| Identify dead code candidates (dead-code-candidates.md) | Planned |
| Plan migration waves (migration-waves.md) | Planned |

**Context**: Using direct execution approach (not subagent-driven development, which is for code implementation tasks). Three parallel research agents successfully gathered:
- Complete function metadata (78 functions across 62 files)
- Java caller analysis (48+ call sites, 33 functions called, 28 unused)
- View usage analysis (41 views using 23 functions)

## Critical References

1. `~/sprocfw/step2.md` - The Phase 1 Discovery & Assessment Plan (v2.2) framework document
2. `/home/yv01p/adempiere/docs/plans/2026-01-01-postgresql-function-migration-design.md` - Migration design doc (shadow execution, quality gates)

## Recent changes

- Created `docs/discovery/discovery-inventory.json` - Complete inventory of 78 functions with metadata, parameters, dependencies, complexity indicators, and caller information
- Created `docs/discovery/dependency-graph.dot` - Graphviz visualization of function dependencies with color-coded usage status

## Learnings

### Function Statistics
- **78 functions** across 62 SQL files (some files have multiple overloads)
- **33 functions** called from Java code
- **23 functions** called from SQL views
- **28 functions** appear unused (dead code candidates)
- **12 recursive functions** (BOM quantity, BOM pricing families call themselves)

### Key Dependency Chains
```
invoiceOpen -> getdate, currencyConvert
currencyConvert -> currencyRate, currencyRound
currencyRate -> getdate (uses CURSOR)
bomQtyAvailable -> bomQtyOnHand, bomQtyReserved (both recursive)
```

### Function Categories Identified
- **Invoice** (5): invoiceOpen, invoicePaid, invoiceDiscount, invoiceOpenToDate, invoicePaidToDate
- **Currency** (5): currencyConvert, currencyRate, currencyRound, currencyBase, currencyBaseType
- **BOM Quantity** (8): bomQtyAvailable, bomQtyOnHand, bomQtyReserved, bomQtyOrdered + ASI variants
- **BOM Pricing** (3): bomPriceLimit, bomPriceList, bomPriceStd
- **Payment** (2): paymentAllocated, paymentAvailable
- **Payment Term** (3): paymentTermDiscount, paymentTermDueDate, paymentTermDueDays
- **ID Generation** (3): nextID, nextIDFunc, nextIDByYear
- **HR/Payroll** (6): dailySalary, monthlySalary, etc. - ALL UNUSED
- **DDL Utility** (3): altercolumn, deps_save_and_drop_dependencies - DO NOT MIGRATE

### Call Patterns
- 45+ calls are direct SQL embedded in Java strings
- Only `nextID` uses CallableStatement pattern
- Functions commonly nested: `currencyConvert(invoiceOpen(...))`
- Most callers in: MBPartner.java, Aging.java, PaySelectionCreateFrom.java, Allocation.java

## Artifacts

Created deliverables:
- `/home/yv01p/adempiere/docs/discovery/discovery-inventory.json` - Complete function inventory
- `/home/yv01p/adempiere/docs/discovery/dependency-graph.dot` - Visual dependency graph

Remaining deliverables to create:
- `docs/discovery/circular-dependencies.json`
- `docs/discovery/complexity-report.md`
- `docs/discovery/function-usage-context.md`
- `docs/discovery/transaction-units.json`
- `docs/discovery/dead-code-candidates.md`
- `docs/discovery/migration-waves.md`

## Action Items & Next Steps

1. **Write circular-dependencies.json** - Document recursive functions:
   - BOM quantity family (bomQtyOnHand, bomQtyReserved, bomQtyOrdered, + ASI variants)
   - BOM pricing family (bomPriceLimit, bomPriceList, bomPriceStd)

2. **Write complexity-report.md** - Apply weighted scoring formula from step2.md:
   ```
   complexity_score = (LOC * 0.1) + (table_count * 2) + (dependency_depth * 3) + ...
   ```

3. **Write function-usage-context.md** - Document where each function is called from (Java files, views, line numbers)

4. **Write transaction-units.json** - Group functions that must migrate together:
   - Invoice unit: invoiceOpen + currencyConvert + currencyRate + currencyRound
   - BOM unit: bomQtyAvailable + bomQtyOnHand + bomQtyReserved

5. **Write dead-code-candidates.md** - List 28 functions with no Java/view callers:
   - HR/Payroll functions (6)
   - ASI variants of BOM functions (4)
   - Various utility functions

6. **Write migration-waves.md** - Order by dependencies and complexity:
   - Wave 0: Utility functions (getDate, round, trunc, addDays)
   - Wave 1: Currency functions (currencyConvert chain)
   - Wave 2: Invoice functions (depend on currency)
   - Wave 3: BOM functions (complex, recursive)

## Other Notes

### Function Files Location
`/home/yv01p/adempiere/db/ddlutils/postgresql/functions/`

### Views Location
`/home/yv01p/adempiere/db/ddlutils/postgresql/views/` (171 view files, 7823 lines)

### Most Critical Views Using Functions
- `RV_OPENITEM.sql` - Uses 7 functions (invoiceOpen, invoicePaid, currencyConvert, daysBetween, etc.)
- `RV_WAREHOUSEPRICE.sql` - Uses all 7 BOM functions
- `RV_BPARTNEROPEN.sql` - Uses invoiceOpen, paymentAvailable, daysBetween

### Functions to NOT Migrate
- DDL utilities: altercolumn, deps_save_and_drop_dependencies, deps_restore_dependencies
- PostgreSQL extensions: getUUID (uses pgcrypto or uuid-ossp)

### High Priority Migration Targets (Business Critical)
1. Invoice functions - Core financial operations
2. Currency functions - Used everywhere (100+ calls)
3. Payment functions - Used in allocation and payment processing
4. BOM quantity functions - Used in 8+ manufacturing views
