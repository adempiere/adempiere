# Invoice SQL Functions to Java Migration Design

## Overview

Migrate Invoice-related SQL stored functions from the database layer into Java, enabling unit testing, single-language debugging, and proper version control integration.

### Drivers

- **Testability:** Stored procedures are hard to unit test; want better test coverage
- **Developer experience:** Java developers struggle with PL/SQL or PL/pgSQL; want all logic in one language
- **Debugging/observability:** Hard to trace issues through database logic; want better logging and stack traces
- **Version control:** Database logic lives outside normal code review and deployment pipelines

---

## Technical Context

### Target Database: PostgreSQL Only

This migration targets **PostgreSQL deployments only**. Key findings:

| Aspect | Details |
|--------|---------|
| **Source implementation** | Native PL/pgSQL functions in `db/ddlutils/postgresql/functions/` |
| **PL/Java status** | Not used - PostgreSQL import script ignores SQLJ (`# ignored for postgresql`) |
| **SQLJ code** | Oracle-only; `sqlj/src/org/compiere/sqlj/` is irrelevant to PostgreSQL |
| **Existing framework** | None - prior migrations (MSequence, T_InventoryValue_Create) were ad-hoc |

### Migration Approach

No pre-defined migration framework exists in ADempiere. This design proposes a new architecture (service/repository pattern with dependency injection) that differs from existing ad-hoc migrations. This is intentional to achieve the testability goals.

### Constraints & Limitations

#### 1. SQL Functions Cannot Be Removed

Database views depend on these functions and cannot call Java directly:

| View | Functions Used |
|------|----------------|
| `RV_OPENITEM.sql` | `invoiceOpen()`, `invoicePaid()` in SELECT and WHERE |
| `RV_OPENITEMTODATE.sql` | `invoiceOpenToDate()` |
| `RV_BPARTNEROPEN.sql` | `invoiceOpen()` |

**Implication:** SQL functions must remain as a compatibility layer for views.

#### 2. Some Callers Cannot Be Migrated

Certain Java code embeds function calls in complex SQL that cannot be trivially converted:

```java
// MBPartner.java:724 - nested inside currencyBase() inside a subquery
"SELECT SUM(currencyBase(invoiceOpen(i.C_Invoice_ID,...),...))"

// OpenItemToDate.java:155 - in WHERE clause
"WHERE invoiceOpenToDate(i.C_Invoice_ID, 0, ?) <> 0"
```

**Implication:** These remain as-is or require significant query restructuring.

#### 3. Dependency Chain

`invoiceOpen()` calls `currencyConvert()` internally (line 76 of C_Invoice_Open.sql).

**Options:**
1. Migrate Currency functions first (recommended)
2. Have Java Invoice code call SQL `currencyConvert()` (hybrid approach)
3. Accept temporary code duplication

#### 4. Hybrid End State

The migration will result in a hybrid state:

| Caller Type | After Migration |
|-------------|-----------------|
| Simple Java callers (`SELECT invoiceOpen(...)`) | Migrated to Java service |
| Database views | Keep using SQL functions |
| Complex embedded SQL queries | Case-by-case decision |

---

### Scope (Pilot)

This is an incremental migration starting with Invoice functions as the pilot module.

**Functions to migrate:**
1. `invoiceOpen` - Calculate open amount on an invoice
2. `invoiceOpenToDate` - Open amount as of a specific date
3. `invoicePaid` - Amount paid on an invoice
4. `invoicePaidToDate` - Amount paid as of a specific date
5. `invoiceDiscount` - Calculate applicable discount

---

## Architecture

### Java Class Structure

**Package:** `base/src/org/compiere/model/invoice/`

**Core service:**
```java
public class InvoiceCalculationService {

    // Main methods (mirror SQL functions)
    public BigDecimal getOpenAmount(int invoiceId, int invoicePayScheduleId)
    public BigDecimal getOpenAmountToDate(int invoiceId, int invoicePayScheduleId, Timestamp asOfDate)
    public BigDecimal getPaidAmount(int invoiceId)
    public BigDecimal getPaidAmountToDate(int invoiceId, Timestamp asOfDate)
    public BigDecimal getDiscount(int invoiceId, Timestamp payDate)

    // Dependencies (injected for testability)
    private final InvoiceRepository invoiceRepository;
    private final AllocationRepository allocationRepository;
    private final CurrencyService currencyService;
}
```

**Design decisions:**
- Repository pattern for database queries (enables mocking in tests)
- Dependency injection via constructor (no static methods)
- BigDecimal throughout (matches ADempiere's numeric handling)
- No direct DB access in service (all queries through repositories)

### Repository Layer

**InvoiceRepository:**
```java
public class InvoiceRepository {
    public InvoiceData getInvoiceData(int invoiceId)
    public List<PaymentSchedule> getPaymentSchedules(int invoiceId)
}
```

**AllocationRepository:**
```java
public class AllocationRepository {
    public List<AllocationLine> getAllocations(int invoiceId)
    public List<AllocationLine> getAllocationsToDate(int invoiceId, Timestamp asOfDate)
}
```

**CurrencyService:**
```java
public class CurrencyService {
    public BigDecimal convert(BigDecimal amount, int fromCurrency, int toCurrency,
                              Timestamp date, int clientId, int orgId)
    public int getPrecision(int currencyId)
}
```

### Data Transfer Objects

- `InvoiceData` - currencyId, grandTotal, multiplierAP, multiplierCM, precision
- `PaymentSchedule` - id, dueDate, dueAmount, isValid
- `AllocationLine` - amount, discountAmt, writeOffAmt, currencyId, dateTrx, clientId, orgId

---

## Testing Strategy

### Unit Tests

```java
@Tag("UnitTest")
@Tag("InvoiceCalculation")
class InvoiceCalculationServiceTest extends CommonUnitTestSetup {

    @Mock InvoiceRepository invoiceRepository;
    @Mock AllocationRepository allocationRepository;
    @Mock CurrencyService currencyService;

    @InjectMocks InvoiceCalculationService service;

    // Test scenarios:
    @Test void openAmount_noAllocations_returnsGrandTotal()
    @Test void openAmount_fullyAllocated_returnsZero()
    @Test void openAmount_partiallyAllocated_returnsRemaining()
    @Test void openAmount_withPaymentSchedule_allocatesInOrder()
    @Test void openAmount_multiCurrency_convertsCorrectly()
    @Test void openAmount_roundingEdgeCases_handledCorrectly()
    @Test void openAmount_creditMemo_appliesMultiplier()
}
```

### Integration Tests

```java
@Tag("IntegrationTest")
class InvoiceCalculationIntegrationTest {
    // Compare Java result vs SQL function result for real data
    @Test void openAmount_matchesSqlFunction()
    @Test void paidAmount_matchesSqlFunction()
}
```

### Test Data Approach

- Unit tests use mocked repositories with controlled data
- Integration tests run against Garden World sample database
- Parameterized tests for edge cases (zero amounts, negative, rounding boundaries)

---

## Migration Plan

### Caller Migration Pattern

**Before:**
```java
String sql = "SELECT invoiceOpen(C_Invoice_ID, ?) FROM C_Invoice WHERE...";
BigDecimal open = DB.getSQLValueBD(trxName, sql, payScheduleId);
```

**After:**
```java
InvoiceCalculationService calcService = new InvoiceCalculationService(...);
BigDecimal open = calcService.getOpenAmount(invoiceId, payScheduleId);
```

### Rollout Phases

1. **Phase 1:** Deploy Java service alongside SQL functions (no caller changes)
2. **Phase 2:** Add integration tests comparing Java vs SQL results
3. **Phase 3:** Migrate simple callers one-by-one, starting with lowest-risk (UI panels)
4. **Phase 4:** Migrate high-impact callers (Callouts, processes)
5. **Phase 5:** Evaluate complex callers (MBPartner, OpenItemToDate) - migrate or defer
6. **Phase 6:** Mark SQL functions as "compatibility only" (add comments, do NOT remove)

**Note:** SQL functions cannot be removed due to view dependencies (see Constraints above). The goal is to make Java the primary implementation for new code while maintaining SQL for backward compatibility.

### Files to Modify

Callers to update:
- `CalloutPayment.java`
- `CalloutPaymentAllocate.java`
- `CalloutPaySelection.java`
- `CalloutCashJournal.java`
- `Aging.java`
- `DunningRunCreate.java`
- `InvoiceWriteOff.java`
- `InfoInvoicePanel.java`
- Additional UI panels and processes

---

## File Structure

### New Files

```
base/src/org/compiere/model/invoice/
├── InvoiceCalculationService.java
├── InvoiceRepository.java
├── AllocationRepository.java
├── CurrencyService.java
├── dto/
│   ├── InvoiceData.java
│   ├── PaymentSchedule.java
│   └── AllocationLine.java

base/test/src/org/compiere/model/invoice/
├── InvoiceCalculationServiceTest.java
├── InvoiceCalculationIntegrationTest.java
└── InvoiceTestDataBuilder.java
```

### SQL Files (Compatibility Layer - Do Not Remove)

These files must remain for database view compatibility:

- `db/ddlutils/postgresql/functions/C_Invoice_Open.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_Paid.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_OpenToDate.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_PaidToDate.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_Discount.sql`

**Action:** Add header comments marking these as "compatibility layer - Java implementation preferred for new code"

---

## Future Expansion

### Recommended Migration Order

Due to dependency chains, the recommended order is:

1. **Currency functions first** (required by Invoice)
   - `currencyConvert`, `currencyRate`, `currencyRound`, `currencyBase`
   - Invoice functions call `currencyConvert()` internally

2. **Invoice functions** (this pilot)
   - `invoiceOpen`, `invoicePaid`, `invoiceDiscount`, etc.

3. **Payment functions** (related to Invoice)
   - `paymentAllocated`, `paymentAvailable`, `paymentTermDueDate`

4. **BOM/Inventory functions**
   - `bomQtyOnHand`, `bomQtyAvailable`, `bomQtyOrdered`, etc.

5. **Remaining utility functions**
   - Date utilities, string functions, etc.

### Alternative: Hybrid Approach for Invoice Pilot

If migrating Currency first adds too much scope, the Invoice Java service can temporarily call the SQL `currencyConvert()` function:

```java
// Hybrid approach - call SQL function from Java
BigDecimal converted = DB.getSQLValueBD(trxName,
    "SELECT currencyConvert(?, ?, ?, ?, ?, ?, ?)",
    amount, fromCurrency, toCurrency, date, convType, clientId, orgId);
```

This defers the Currency migration while still achieving the Invoice pilot goals.
