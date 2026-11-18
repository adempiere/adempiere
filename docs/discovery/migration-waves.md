# PostgreSQL Function Migration Waves

**Generated:** 2026-01-01
**Total Functions to Migrate:** 50 (excluding 28 dead code candidates)
**Estimated Total Effort:** 25-35 days

---

## Migration Wave Overview

```
Wave 0 ─────► Wave 1 ─────► Wave 2 ─────► Wave 3 ─────► Wave 4 ─────► Wave 5
Foundation   Currency     Payment      Financial    Standalone   BOM/Complex
 (7 fn)       (4 fn)      Terms (5)    Core (7)      (9 fn)       (7 fn)
 1-2 days     4-5 days    2-3 days     5-8 days      4-5 days     9-12 days
```

---

## Wave 0: Foundation (No Dependencies)

**Goal:** Establish base utility functions that other functions depend on
**Effort:** 1-2 days
**Functions:** 7

| Priority | Function | File | LOC | Complexity | Usage |
|----------|----------|------|-----|------------|-------|
| 1 | getDate | getDate.sql | 7 | Trivial | Views |
| 2 | daysBetween | daysBetween.sql | 29 | Low | Java + Views |
| 3 | addDays | addDays.sql | 34 | Low | Java + Views |
| 4 | trunc | trunc.sql | 28 | Low | Java + Views |
| 5 | round | round.sql | 10 | Trivial | Views |
| 6 | firstOf | firstOf.sql | 77 | Low | Java + Views |
| 7 | charAt | charAt.sql | 35 | Low | Views |

**Success Criteria:**
- [ ] All 7 functions implemented in Java
- [ ] Unit tests pass
- [ ] Integration tests with database pass
- [ ] Shadow mode deployed for 24 hours with 100% match rate

**Views Affected:** Multiple date-based views

**Migration Notes:**
- These are simple functions with no dependencies
- Can be migrated in parallel
- Good candidates for establishing the migration pattern

---

## Wave 1: Currency (Core Chain)

**Goal:** Migrate currency conversion chain - unlocks Invoice/Payment functions
**Effort:** 4-5 days
**Dependencies:** Wave 0 (getDate)
**Functions:** 4

| Priority | Function | File | LOC | Complexity | Usage |
|----------|----------|------|-----|------------|-------|
| 1 | currencyRound | C_Currency_Round.sql | 63 | Low | Java |
| 2 | currencyRate | C_Currency_Rate.sql | 179 | High (CURSOR) | Java |
| 3 | currencyConvert | C_Currency_Convert.sql | 63 | Medium | Java + Views |
| 4 | currencyBase | C_Currency_Base.sql | 34 | Low | Java |

**Dependency Chain:**
```
currencyRound ◄── currencyConvert ◄── currencyBase
                       ▲
currencyRate ──────────┘
     ▲
  getDate (Wave 0)
```

**Success Criteria:**
- [ ] currencyRate handles CURSOR fallback logic correctly
- [ ] currencyConvert produces identical results to SQL
- [ ] Shadow mode 99.9% match rate for 7 days
- [ ] Performance within 30% of SQL baseline

**Risk Areas:**
- `currencyRate` uses CURSOR for fallback - complex logic
- `currencyConvert` has 45+ call sites - high impact

**Views Affected:**
- RV_CASH_DETAIL.sql
- RV_PROJECTCYCLE.sql

---

## Wave 2: Payment Terms

**Goal:** Migrate payment term calculations - unlocks Invoice Discount
**Effort:** 2-3 days
**Dependencies:** Wave 0 (getDate)
**Functions:** 5

| Priority | Function | File | LOC | Complexity | Usage |
|----------|----------|------|-----|------------|-------|
| 1 | add_months | Add_Months.sql | 14 | Low | Internal |
| 2 | nextBusinessDay | nextBusinessDay.sql | 59 | Medium | Internal |
| 3 | paymentTermDiscount | C_PaymentTerm_Discount.sql | 68 | Medium | Java |
| 4 | paymentTermDueDate | C_PaymentTerm_DueDate.sql | 50 | Medium | Java + Views |
| 5 | paymentTermDueDays | C_PaymentTerm_DueDays.sql | 123 | Medium | Java + Views |

**Dependency Chain:**
```
add_months ◄── paymentTermDueDate

nextBusinessDay ◄── paymentTermDiscount

getDate (Wave 0) ◄── paymentTermDueDays
```

**Success Criteria:**
- [ ] Discount calculations match SQL exactly
- [ ] Due date calculations handle month-end edge cases
- [ ] Business day skipping works with holiday calendars

---

## Wave 3: Financial Core

**Goal:** Migrate core invoice and payment functions
**Effort:** 5-8 days
**Dependencies:** Wave 1 (currencyConvert), Wave 2 (paymentTermDiscount)
**Functions:** 7

| Priority | Function | File | LOC | Complexity | Known Duplicate |
|----------|----------|------|-----|------------|-----------------|
| 1 | invoiceOpen | C_Invoice_Open.sql | 121 | High | MInvoice.getOpenAmt() |
| 2 | invoiceOpenToDate | C_Invoice_OpenToDate.sql | 126 | High | - |
| 3 | invoiceDiscount | C_Invoice_Discount.sql | 83 | Medium | - |
| 4 | invoicePaid | C_Invoice_Paid.sql | 65 | Medium | - |
| 5 | invoicePaidToDate | C_Invoice_PaidToDate.sql | 74 | Medium | - |
| 6 | paymentAllocated | C_Payment_Allocated.sql | 64 | Medium | - |
| 7 | paymentAvailable | C_Payment_Available.sql | 66 | Medium | - |

**Dependency Chain:**
```
currencyConvert (Wave 1) ◄── invoiceOpen ──► invoiceOpenToDate
                              ▲
                         invoicePaid ──► invoicePaidToDate
                              ▲
                    paymentAllocated
                              ▲
                    paymentAvailable

paymentTermDiscount (Wave 2) ◄── invoiceDiscount
```

**Success Criteria:**
- [ ] `invoiceOpen` shadow validation against `MInvoice.getOpenAmt()`
- [ ] All allocation calculations match
- [ ] Payment schedule handling correct
- [ ] 99.9% match rate for 7 days

**Risk Areas:**
- `invoiceOpen` has known Java duplicate with TODO about payment schedules
- High call volume - performance critical

**Views Affected:**
- RV_OPENITEM.sql
- RV_BPARTNEROPEN.sql
- RV_PAYMENT.sql

---

## Wave 4: Standalone Functions

**Goal:** Migrate independent functions with limited dependencies
**Effort:** 4-5 days
**Dependencies:** Minimal
**Functions:** 9

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

**Special Considerations:**
- `nextID` is the only function using CallableStatement - critical for record creation
- These can be migrated in parallel with Wave 3

**Success Criteria:**
- [ ] nextID maintains sequence integrity under concurrent access
- [ ] All functions produce identical results

---

## Wave 5: BOM Functions (Complex)

**Goal:** Migrate recursive BOM functions - most complex wave
**Effort:** 9-12 days
**Dependencies:** None (self-contained)
**Functions:** 7

### Wave 5a: BOM Pricing (4-5 days)

| Priority | Function | File | LOC | Recursion |
|----------|----------|------|-----|-----------|
| 1 | bomPriceLimit | BOM_PriceLimit.sql | 51 | Self |
| 2 | bomPriceList | BOM_PriceList.sql | 61 | Self |
| 3 | bomPriceStd | BOM_PriceStd.sql | 60 | Self |

### Wave 5b: BOM Quantity (5-7 days)

| Priority | Function | File | LOC | Recursion |
|----------|----------|------|-----|-----------|
| 1 | bomQtyOnHand | BOM_Qty_OnHand.sql | 135 | Self |
| 2 | bomQtyReserved | BOM_Qty_Reserved.sql | 141 | Self |
| 3 | bomQtyOrdered | BOM_Qty_Ordered.sql | 143 | Self |
| 4 | bomQtyAvailable | BOM_Qty_Available.sql | 26 | Calls OnHand, Reserved |

**Dependency Chain:**
```
bomQtyOnHand ──────┐
                   ├──► bomQtyAvailable
bomQtyReserved ────┘

bomQtyOrdered (independent)

bomPriceLimit, bomPriceList, bomPriceStd (all independent)
```

**Implementation Strategy:**

1. **Convert Recursion to Iteration**
   ```java
   public BigDecimal bomQtyOnHand(int productId, int warehouseId, int locatorId) {
       Deque<BOMComponent> stack = new ArrayDeque<>();
       stack.push(new BOMComponent(productId, 1.0));

       BigDecimal total = BigDecimal.ZERO;
       while (!stack.isEmpty()) {
           BOMComponent comp = stack.pop();
           if (isComponent(comp)) {
               total = total.add(getQtyOnHand(comp));
           } else {
               for (BOMComponent child : getChildren(comp)) {
                   stack.push(child);
               }
           }
       }
       return total;
   }
   ```

2. **Add Memoization**
   - Cache component calculations
   - Reduce database round trips

3. **Batch Loading**
   - Load entire BOM tree in single query
   - Process in memory

**Success Criteria:**
- [ ] Recursive traversal produces identical results
- [ ] No stack overflow with deep BOMs
- [ ] Performance within 30% of SQL
- [ ] Handle circular BOM detection (safeguard)

**Views Affected:**
- RV_WAREHOUSEPRICE.sql (all 7 BOM functions)

---

## Wave Summary

| Wave | Name | Functions | Effort | Dependencies | Risk |
|------|------|-----------|--------|--------------|------|
| 0 | Foundation | 7 | 1-2 days | None | Low |
| 1 | Currency | 4 | 4-5 days | Wave 0 | Medium |
| 2 | Payment Terms | 5 | 2-3 days | Wave 0 | Low |
| 3 | Financial Core | 7 | 5-8 days | Wave 1, 2 | High |
| 4 | Standalone | 9 | 4-5 days | Minimal | Low |
| 5 | BOM (Complex) | 7 | 9-12 days | None | High |

**Total:** 39 functions (+ 11 internal helpers) = 50 functions migrated

---

## Critical Path

The critical path through the migration is:

```
Wave 0 (Foundation) ──► Wave 1 (Currency) ──► Wave 3 (Financial Core)
        │                                              │
        └──► Wave 2 (Payment Terms) ─────────────────►─┘
```

**Blocking Dependencies:**
- Wave 3 cannot start until Wave 1 completes (currencyConvert)
- Wave 3 cannot start until Wave 2 completes (paymentTermDiscount)
- Wave 4 and Wave 5 have no blockers - can run in parallel with Wave 3

---

## Parallel Execution Opportunities

```
Timeline:
─────────────────────────────────────────────────────────────────────►

Week 1-2:  [Wave 0 + Wave 2]        [Wave 4 starts]
Week 2-3:  [Wave 1]                 [Wave 4 continues]
Week 3-5:  [Wave 3]                 [Wave 5a starts]
Week 5-7:                           [Wave 5b]
```

With parallel execution:
- **Sequential:** 25-35 days
- **Parallel:** 18-25 days

---

## Quality Gates per Wave

Each wave must pass before proceeding:

### Gate 1: Code Complete
- [ ] Java implementation matches SQL logic
- [ ] Unit tests cover edge cases
- [ ] Integration tests pass

### Gate 2: Shadow Ready
- [ ] Feature flag set to SHADOW
- [ ] Performance baseline captured
- [ ] Java within 30% of SQL p95

### Gate 3: Cutover Approved
- [ ] 99.9% match rate for 7 days
- [ ] No critical mismatches
- [ ] Dependent views migrated (if applicable)

### Gate 4: Cleanup Complete
- [ ] Feature flag set to JAVA_ONLY
- [ ] SQL function retained (deleted after 30 days stable)
- [ ] Monitoring confirms no errors

---

## Appendix: Functions Not in Any Wave (Dead Code)

These 28 functions are excluded from migration waves:

- DDL Utilities: altercolumn, deps_save_and_drop_dependencies, deps_restore_dependencies
- HR/Payroll: dailySalary, dailySalaryToDate, dailySalaryToDateByHRProcess, monthlySalary, monthlySalaryToDate, ProcessReportSource
- BOM ASI: bomQtyAvailableASI, bomQtyOnHandASI, bomQtyOrderedASI, bomQtyReservedASI
- Unused Utilities: subtractdays, addweeks, addyears, instr, nextIDByYear, getUUID
- Other: bpartnerRemitLocation, currencyBaseType, financialRateToDate, prodQtyOrdered, prodQtyReserved

See `dead-code-candidates.md` for deprecation recommendations.
