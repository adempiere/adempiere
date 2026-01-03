# Wave 2: Payment Terms Migration Design

**Date:** 2026-01-03
**Status:** Design Summary
**Parent Design:** [2026-01-01-postgresql-function-migration-design.md](./2026-01-01-postgresql-function-migration-design.md)
**Wave Reference:** [migration-waves.md](../discovery/migration-waves.md)

---

## 1. Wave Overview

**Goal:** Migrate payment term calculations - unlocks Invoice Discount functionality in Wave 3

**Effort:** 2-3 days

**Dependencies:** Wave 0 (getDate) must be complete

**Validation Strategy:** Shadow mode with sampling (standard functions)

---

## 2. Functions to Migrate

| Priority | Function | File | LOC | Complexity | Usage | Sample Rate |
|----------|----------|------|-----|------------|-------|-------------|
| 1 | add_months | Add_Months.sql | 14 | Low | Internal | 100% |
| 2 | nextBusinessDay | nextBusinessDay.sql | 59 | Medium | Internal | 100% |
| 3 | paymentTermDiscount | C_PaymentTerm_Discount.sql | 68 | Medium | Java | 10% |
| 4 | paymentTermDueDate | C_PaymentTerm_DueDate.sql | 50 | Medium | Java + Views | 10% |
| 5 | paymentTermDueDays | C_PaymentTerm_DueDays.sql | 123 | Medium | Java + Views | 10% |

**Total:** 5 functions (314 LOC)

---

## 3. Dependency Chain

```
add_months ◄── paymentTermDueDate
                      │
                      ├──► paymentTermDueDays
                      │
nextBusinessDay ◄── paymentTermDiscount

getDate (Wave 0) ◄── paymentTermDueDays
```

**Migration Order:**
1. `add_months` (leaf - no dependencies)
2. `nextBusinessDay` (leaf - no dependencies)
3. `paymentTermDueDate` (depends on add_months)
4. `paymentTermDiscount` (depends on nextBusinessDay)
5. `paymentTermDueDays` (depends on getDate from Wave 0)

---

## 4. Java Implementation Location

| Function | Java Location | Pattern |
|----------|---------------|---------|
| add_months | `org.compiere.util.TimeUtil.addMonths()` | Static utility |
| nextBusinessDay | `MNonBusinessDay.nextBusinessDay()` | Model class |
| paymentTermDiscount | `MPaymentTerm.getDiscount()` | Instance method |
| paymentTermDueDate | `MPaymentTerm.getDueDate()` | Instance method |
| paymentTermDueDays | `MPaymentTerm.getDueDays()` | Instance method |

---

## 5. Key Implementation Notes

### 5.1 add_months
- Simple date arithmetic
- Handle month-end edge cases (e.g., Jan 31 + 1 month = Feb 28/29)
- Use Java 8+ `LocalDate.plusMonths()`

### 5.2 nextBusinessDay
- Requires lookup of `C_NonBusinessDay` table
- Considers `AD_Org_ID` for org-specific holidays
- Must handle recursive skipping (consecutive holidays)

### 5.3 paymentTermDiscount
- Calculates early payment discount amount
- Depends on nextBusinessDay for discount date calculation
- Uses payment term schedule from `C_PaymentTermSchedule`

### 5.4 paymentTermDueDate
- Calculates invoice due date based on payment terms
- Handles payment term schedules for split payments
- Month-end handling for "Net 30" style terms

### 5.5 paymentTermDueDays
- Most complex function (123 LOC)
- Calculates number of days until payment due
- Considers multiple schedules
- Uses getDate from Wave 0

---

## 6. Validation Strategy

Per parent design Section 2.1:

| Function | Strategy | Rationale |
|----------|----------|-----------|
| add_months | Shadow 100% | Low frequency, simple |
| nextBusinessDay | Shadow 100% | Low frequency, internal |
| paymentTermDiscount | Shadow 10% | Medium frequency |
| paymentTermDueDate | Shadow 10% | Medium frequency |
| paymentTermDueDays | Shadow 10% | Medium frequency |

**Match Rate Target:** 99.9% for 7 consecutive days

---

## 7. Performance Requirements

Per parent design Section 5.6:

| Function | Tier | Max Latency Increase (p95) |
|----------|------|---------------------------|
| add_months | Standard | 30% |
| nextBusinessDay | Standard | 30% |
| paymentTermDiscount | Standard | 30% |
| paymentTermDueDate | Standard | 30% |
| paymentTermDueDays | Standard | 30% |

---

## 8. Risk Areas

| Risk | Likelihood | Mitigation |
|------|------------|------------|
| Month-end date handling differs | Medium | Comprehensive unit tests for edge cases |
| Holiday calendar not loaded | Low | Verify test data includes holidays |
| Payment schedule iteration order | Medium | Shadow mode will catch differences |

---

## 9. Quality Gates

### Gate 1: Code Complete
- [ ] All 5 functions implemented in Java
- [ ] Unit tests cover edge cases (month-end, holidays, schedules)
- [ ] Integration tests pass

### Gate 2: Shadow Ready
- [ ] Feature flags set to SHADOW for all 5 functions
- [ ] Performance baseline captured
- [ ] Java within 30% of SQL p95

### Gate 3: Cutover Approved
- [ ] 99.9% match rate for 7 days
- [ ] No critical mismatches unresolved
- [ ] Wave 3 readiness confirmed

### Gate 4: Cleanup Complete
- [ ] Feature flags set to JAVA_ONLY
- [ ] SQL functions retained in git
- [ ] Monitoring confirms no errors

---

## 10. Downstream Impact

**Wave 3 Dependency:** `paymentTermDiscount` is required by:
- `C_Invoice_Discount.sql` (invoiceDiscount)

Wave 3 (Financial Core) cannot begin validation until Wave 2 achieves Gate 3.

---

## 11. Configuration

Add to `migration.function_config`:

```sql
INSERT INTO migration.function_config
(function_name, mode, performance_tier, sample_rate) VALUES
('add_months', 'SHADOW', 'STANDARD', 1.0),
('nextBusinessDay', 'SHADOW', 'STANDARD', 1.0),
('paymentTermDiscount', 'SHADOW', 'STANDARD', 0.10),
('paymentTermDueDate', 'SHADOW', 'STANDARD', 0.10),
('paymentTermDueDays', 'SHADOW', 'STANDARD', 0.10);
```

---

## 12. Test Data Requirements

Ensure test environment includes:
- [ ] Multiple payment terms with various schedules
- [ ] Non-business days (weekends + holidays)
- [ ] Invoices spanning month boundaries
- [ ] Multi-schedule payment terms
- [ ] Edge case dates (Feb 29, Dec 31, etc.)

---

## 13. Rollback Procedure

Per parent design Section 5.1:

```bash
# Rollback single function
UPDATE migration.function_config
SET mode = 'SQL_ONLY'
WHERE function_name = '{function_name}';

# If SQL function was deleted, restore from git
git show HEAD:db/ddlutils/postgresql/functions/{function_name}.sql | psql
```

**Cross-Wave Dependency:** Rolling back Wave 2 requires checking Wave 3 status. If Wave 3 is in SHADOW or later, it must also be rolled back.

---

## Appendix: File Locations

| Resource | Path |
|----------|------|
| Add_Months.sql | `db/ddlutils/postgresql/functions/Add_Months.sql` |
| nextBusinessDay.sql | `db/ddlutils/postgresql/functions/nextBusinessDay.sql` |
| C_PaymentTerm_Discount.sql | `db/ddlutils/postgresql/functions/C_PaymentTerm_Discount.sql` |
| C_PaymentTerm_DueDate.sql | `db/ddlutils/postgresql/functions/C_PaymentTerm_DueDate.sql` |
| C_PaymentTerm_DueDays.sql | `db/ddlutils/postgresql/functions/C_PaymentTerm_DueDays.sql` |
| MPaymentTerm.java | `base/src/org/compiere/model/MPaymentTerm.java` |
| MNonBusinessDay.java | `base/src/org/compiere/model/MNonBusinessDay.java` |
| TimeUtil.java | `base/src/org/compiere/util/TimeUtil.java` |
