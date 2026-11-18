# PostgreSQL Function Complexity Report

**Generated:** 2026-01-01
**Source:** discovery-inventory.json analysis
**Total Functions:** 78

---

## Complexity Scoring Formula

```
complexity_score = (LOC * 0.1)
                 + (table_count * 2)
                 + (dependency_count * 3)
                 + (has_loops ? 5 : 0)
                 + (has_exception_handling ? 3 : 0)
                 + (has_dynamic_sql ? 10 : 0)
                 + (has_cursors ? 8 : 0)
                 + (is_recursive ? 15 : 0)
```

---

## Complexity Tiers

| Tier | Score Range | Count | Description |
|------|-------------|-------|-------------|
| **Critical** | 30+ | 9 | Requires careful migration planning, extensive testing |
| **High** | 20-29 | 8 | Significant complexity, needs thorough review |
| **Medium** | 10-19 | 19 | Moderate complexity, standard migration |
| **Low** | 0-9 | 42 | Simple functions, straightforward migration |

---

## Critical Complexity Functions (Score 30+)

| Rank | Function | Score | LOC | Tables | Deps | Loops | Exceptions | Cursors | Recursive | Category |
|------|----------|-------|-----|--------|------|-------|------------|---------|-----------|----------|
| 1 | deps_save_and_drop_dependencies | 80.7 | 607 | 2 | 0 | Yes | No | No | No | ddl-utility |
| 2 | deps_restore_dependencies | 82.0 | 670 | 1 | 0 | Yes | No | No | No | ddl-utility |
| 3 | bomQtyOnHand | 41.5 | 135 | 5 | 1 | Yes | Yes | No | Yes | bom-quantity |
| 4 | bomQtyReserved | 39.1 | 141 | 5 | 1 | Yes | Yes | No | Yes | bom-quantity |
| 5 | bomQtyOrdered | 39.3 | 143 | 5 | 1 | Yes | Yes | No | Yes | bom-quantity |
| 6 | bomQtyOnHandASI | 40.9 | 139 | 5 | 1 | Yes | Yes | No | Yes | bom-quantity |
| 7 | bomQtyOrderedASI | 39.5 | 145 | 5 | 1 | Yes | Yes | No | Yes | bom-quantity |
| 8 | bomQtyReservedASI | 39.5 | 145 | 5 | 1 | Yes | Yes | No | Yes | bom-quantity |
| 9 | currencyRate | 35.9 | 179 | 3 | 1 | Yes | Yes | Yes | No | currency |

### Critical Function Analysis

**deps_save_and_drop_dependencies / deps_restore_dependencies**
- **Recommendation:** DO NOT MIGRATE - DDL utility functions for schema modifications
- These are PostgreSQL administrative functions, not business logic

**BOM Quantity Functions (bomQtyOnHand, bomQtyReserved, bomQtyOrdered)**
- **Risk:** Self-recursive traversal of BOM component tree
- **Challenge:** Convert recursion to iterative Java with stack-based traversal
- **Testing:** Need comprehensive test data with multi-level BOMs
- **Performance:** May have N+1 query patterns - consider batch loading

**currencyRate**
- **Risk:** Uses CURSOR for rate lookup fallback logic
- **Challenge:** Complex date/org fallback logic when exact rate not found
- **Testing:** Edge cases around conversion type and date boundaries

---

## High Complexity Functions (Score 20-29)

| Rank | Function | Score | LOC | Tables | Deps | Loops | Exceptions | Cursors | Recursive | Category |
|------|----------|-------|-----|--------|------|-------|------------|---------|-----------|----------|
| 10 | paymentTermDueDays | 22.3 | 123 | 1 | 1 | Yes | No | No | No | payment-term |
| 11 | invoiceOpen | 23.1 | 121 | 3 | 2 | Yes | Yes | No | No | invoice |
| 12 | invoiceOpenToDate | 23.6 | 126 | 3 | 1 | Yes | Yes | No | No | invoice |
| 13 | bomPriceLimit | 28.1 | 51 | 3 | 1 | Yes | No | No | Yes | bom-pricing |
| 14 | bomPriceList | 29.1 | 61 | 3 | 1 | Yes | No | No | Yes | bom-pricing |
| 15 | bomPriceStd | 29.0 | 60 | 3 | 1 | Yes | No | No | Yes | bom-pricing |
| 16 | altercolumn | 28.9 | 99 | 2 | 2 | Yes | No | No | No | ddl-utility |
| 17 | productAttribute | 19.3 | 93 | 2 | 0 | Yes | No | No | No | product |

### High Complexity Analysis

**BOM Pricing Functions (bomPriceLimit, bomPriceList, bomPriceStd)**
- Similar pattern to BOM Quantity - self-recursive
- Slightly simpler (no exception handling)
- Share common pattern - can reuse migration approach

**Invoice Functions (invoiceOpen, invoiceOpenToDate)**
- Complex business logic with currency conversion
- Multiple table joins and allocation calculations
- Known existing Java duplicate: `MInvoice.getOpenAmt()` - verify in shadow mode

---

## Medium Complexity Functions (Score 10-19)

| Function | Score | LOC | Category | Notes |
|----------|-------|-----|----------|-------|
| invoiceDiscount | 17.3 | 83 | invoice | Uses paymentTermDiscount |
| paymentTermDiscount | 15.8 | 68 | payment-term | Uses nextBusinessDay |
| paymentAllocated | 16.4 | 64 | payment | Currency conversion |
| paymentAvailable | 16.6 | 66 | payment | Currency conversion |
| invoicePaid | 14.5 | 65 | invoice | Currency conversion |
| invoicePaidToDate | 15.4 | 74 | invoice | Currency conversion |
| prodQtyReserved | 14.6 | 96 | product | Exception handling |
| prodQtyOrdered | 11.6 | 66 | product | Exception handling |
| nextBusinessDay | 12.9 | 59 | utility-date | Loop for day calculation |
| paymentTermDueDate | 12.0 | 50 | payment-term | Uses add_months |
| currencyConvert | 12.3 | 63 | currency | Core conversion, uses rate+round |
| dailySalaryToDate | 11.6 | 66 | hr-payroll | UNUSED |
| monthlySalaryToDate | 11.6 | 66 | hr-payroll | UNUSED |
| nextID | 11.6 | 56 | id-generation | Exception handling, critical |
| documentNo | 11.1 | 51 | mrp | View-only usage |
| get_Sysconfig | 10.7 | 47 | system | Exception handling |
| ProcessReportSource | 12.9 | 79 | hr-payroll | UNUSED |
| bpartnerRemitLocation | 10.5 | 25 | business-partner | UNUSED |
| currencyBaseType | 10.2 | 52 | currency | UNUSED |

---

## Low Complexity Functions (Score 0-9)

These functions are straightforward to migrate:

| Function | Score | LOC | Category | Usage |
|----------|-------|-----|----------|-------|
| getDate | 0.7 | 7 | utility-date | Views |
| round | 1.0 | 10 | utility-math | Views |
| add_months | 1.4 | 14 | utility-date | Internal |
| nextIDFunc | 1.4 | 14 | id-generation | Java |
| instr | 2.0 | 20 | utility-string | None |
| nextIDByYear | 4.5 | 25 | id-generation | None |
| trunc | 2.8 | 28 | utility-date | Java+Views |
| daysBetween | 2.9 | 29 | utility-date | Java+Views |
| addDays | 3.4 | 34 | utility-date | Java+Views |
| charAt | 3.5 | 35 | utility-string | Views |
| currencyBase | 6.4 | 34 | currency | Java |
| getUUID | 6.6 | 36 | utility | None |
| dailySalary | 6.7 | 37 | hr-payroll | None |
| monthlySalary | 6.7 | 37 | hr-payroll | None |
| linenetamtrealinvoiceline | 8.8 | 18 | commission | Java |
| linenetamtrealorderline | 8.8 | 18 | commission | Java |
| maxpaydate | 7.0 | 20 | commission | Java |
| subtractdays | 4.1 | 41 | utility-date | None |
| acctBalance | 9.2 | 42 | accounting | Java |
| financialRateToDate | 8.3 | 43 | financial | None |
| firstOf | 7.7 | 77 | utility-date | Java+Views |
| addweeks | 4.7 | 47 | utility-date | None |
| addyears | 4.7 | 47 | utility-date | None |
| bomQtyAvailable | 2.6 | 26 | bom-quantity | Java+Views |
| bomQtyAvailableASI | 2.8 | 28 | bom-quantity | None |
| currencyRound | 8.3 | 63 | currency | Java |
| dailySalaryToDateByHRProcess | 8.5 | 55 | hr-payroll | None |

---

## Migration Priority Matrix

| Priority | Criteria | Functions |
|----------|----------|-----------|
| **P0 - Skip** | DDL utilities, unused PostgreSQL extensions | altercolumn, deps_*, getUUID |
| **P1 - Critical Path** | High usage, foundation for others | currencyConvert, currencyRate, currencyRound, getDate |
| **P2 - Business Critical** | Core financial operations | invoiceOpen, invoicePaid, paymentAllocated, paymentAvailable |
| **P3 - Important** | Used in views and Java | BOM functions (used ones), payment term functions |
| **P4 - Low Priority** | Limited usage | Utility functions, commission functions |
| **P5 - Dead Code** | No callers | HR/Payroll (all 6), ASI variants (4), various (18 total) |

---

## Recommendations

### 1. Start with Low-Complexity Foundation
Migrate these first to build infrastructure:
- `getDate` - trivial, widely used
- `currencyRound` - simple, needed by currencyConvert
- `daysBetween`, `addDays`, `trunc` - utility, no dependencies

### 2. Tackle Currency Chain Early
Order: `currencyRound` -> `currencyRate` -> `currencyConvert` -> `currencyBase`

This unlocks Invoice and Payment functions.

### 3. Defer BOM Functions
Most complex, requires:
- Recursion-to-iteration conversion strategy
- Comprehensive BOM test data
- Performance benchmarking

### 4. Skip Dead Code (28 functions)
Consider deprecation instead of migration for unused functions.

---

## Appendix: Full Scoring Data

| Function | LOC | Tables | Deps | Loops | Except | DynSQL | Cursor | Recur | Score |
|----------|-----|--------|------|-------|--------|--------|--------|-------|-------|
| acctBalance | 42 | 1 | 0 | No | Yes | No | No | No | 9.2 |
| add_months | 14 | 0 | 0 | No | No | No | No | No | 1.4 |
| addDays | 34 | 0 | 0 | No | No | No | No | No | 3.4 |
| addweeks | 47 | 0 | 0 | No | No | No | No | No | 4.7 |
| addyears | 47 | 0 | 0 | No | No | No | No | No | 4.7 |
| altercolumn | 99 | 2 | 2 | Yes | No | Yes | No | No | 28.9 |
| bomPriceLimit | 51 | 3 | 1 | Yes | No | No | No | Yes | 28.1 |
| bomPriceList | 61 | 3 | 1 | Yes | No | No | No | Yes | 29.1 |
| bomPriceStd | 60 | 3 | 1 | Yes | No | No | No | Yes | 29.0 |
| bomQtyAvailable | 26 | 0 | 2 | No | No | No | No | No | 8.6 |
| bomQtyAvailableASI | 28 | 0 | 2 | No | No | No | No | No | 8.8 |
| bomQtyOnHand | 135 | 5 | 1 | Yes | Yes | No | No | Yes | 41.5 |
| bomQtyOnHandASI | 139 | 5 | 1 | Yes | Yes | No | No | Yes | 40.9 |
| bomQtyOrdered | 143 | 5 | 1 | Yes | Yes | No | No | Yes | 39.3 |
| bomQtyOrderedASI | 145 | 5 | 1 | Yes | Yes | No | No | Yes | 39.5 |
| bomQtyReserved | 141 | 5 | 1 | Yes | Yes | No | No | Yes | 39.1 |
| bomQtyReservedASI | 145 | 5 | 1 | Yes | Yes | No | No | Yes | 39.5 |
| bpartnerRemitLocation | 25 | 1 | 0 | Yes | No | No | No | No | 9.5 |
| charAt | 35 | 0 | 0 | No | No | No | No | No | 3.5 |
| currencyBase | 34 | 0 | 1 | No | No | No | No | No | 6.4 |
| currencyBaseType | 52 | 2 | 1 | No | No | No | No | No | 12.2 |
| currencyConvert | 63 | 0 | 2 | No | No | No | No | No | 12.3 |
| currencyRate | 179 | 3 | 1 | Yes | Yes | No | Yes | No | 35.9 |
| currencyRound | 63 | 1 | 0 | No | No | No | No | No | 8.3 |
| dailySalary | 37 | 0 | 2 | No | No | No | No | No | 9.7 |
| dailySalaryToDate | 66 | 2 | 1 | No | No | No | No | No | 13.6 |
| dailySalaryToDateByHRProcess | 55 | 4 | 0 | No | No | No | No | No | 13.5 |
| daysBetween | 29 | 0 | 0 | No | No | No | No | No | 2.9 |
| deps_restore_dependencies | 670 | 1 | 0 | Yes | No | Yes | No | No | 82.0 |
| deps_save_and_drop_dependencies | 607 | 2 | 0 | Yes | No | Yes | No | No | 79.7 |
| documentNo | 51 | 6 | 0 | No | No | No | No | No | 17.1 |
| financialRateToDate | 43 | 1 | 1 | No | No | No | No | No | 9.3 |
| firstOf | 77 | 0 | 0 | No | No | No | No | No | 7.7 |
| getDate | 7 | 0 | 0 | No | No | No | No | No | 0.7 |
| get_Sysconfig | 47 | 1 | 0 | No | Yes | No | No | No | 9.7 |
| getUUID | 36 | 0 | 1 | No | No | No | No | No | 6.6 |
| instr | 20 | 0 | 0 | No | No | No | No | No | 2.0 |
| invoiceDiscount | 83 | 2 | 2 | No | Yes | No | No | No | 17.3 |
| invoiceOpen | 121 | 3 | 2 | Yes | Yes | No | No | No | 26.1 |
| invoiceOpenToDate | 126 | 3 | 1 | Yes | Yes | No | No | No | 23.6 |
| invoicePaid | 65 | 2 | 1 | Yes | No | No | No | No | 14.5 |
| invoicePaidToDate | 74 | 2 | 1 | Yes | No | No | No | No | 15.4 |
| linenetamtrealinvoiceline | 18 | 4 | 0 | No | No | No | No | No | 9.8 |
| linenetamtrealorderline | 18 | 4 | 0 | No | No | No | No | No | 9.8 |
| maxpaydate | 20 | 3 | 0 | No | No | No | No | No | 8.0 |
| monthlySalary | 37 | 0 | 2 | No | No | No | No | No | 9.7 |
| monthlySalaryToDate | 66 | 2 | 1 | No | No | No | No | No | 13.6 |
| nextBusinessDay | 59 | 1 | 0 | Yes | No | No | No | No | 12.9 |
| nextID | 56 | 1 | 0 | No | Yes | No | No | No | 10.6 |
| nextIDByYear | 25 | 1 | 0 | No | No | No | No | No | 4.5 |
| nextIDFunc | 14 | 0 | 1 | No | No | No | No | No | 4.4 |
| paymentAllocated | 64 | 3 | 1 | Yes | No | No | No | No | 17.4 |
| paymentAvailable | 66 | 3 | 1 | Yes | No | No | No | No | 17.6 |
| paymentTermDiscount | 68 | 1 | 1 | Yes | No | No | No | No | 15.8 |
| paymentTermDueDate | 50 | 1 | 1 | Yes | No | No | No | No | 15.0 |
| paymentTermDueDays | 123 | 1 | 1 | Yes | No | No | No | No | 22.3 |
| ProcessReportSource | 79 | 3 | 0 | Yes | No | No | No | No | 18.9 |
| prodQtyOrdered | 66 | 3 | 0 | No | Yes | No | No | No | 12.6 |
| prodQtyReserved | 96 | 4 | 0 | No | Yes | No | No | No | 20.6 |
| productAttribute | 93 | 2 | 0 | Yes | No | No | No | No | 18.3 |
| round | 10 | 0 | 0 | No | No | No | No | No | 1.0 |
| subtractdays | 41 | 0 | 1 | No | No | No | No | No | 7.1 |
| trunc | 28 | 0 | 0 | No | No | No | No | No | 2.8 |
