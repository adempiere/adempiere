# Invoice SQL Functions to Java Migration Design

## Overview

Migrate Invoice-related SQL stored functions from the database layer into Java, enabling unit testing, single-language debugging, and proper version control integration.

### Drivers

- **Testability:** Stored procedures are hard to unit test; want better test coverage
- **Developer experience:** Java developers struggle with PL/SQL or PL/pgSQL; want all logic in one language
- **Debugging/observability:** Hard to trace issues through database logic; want better logging and stack traces
- **Version control:** Database logic lives outside normal code review and deployment pipelines

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
3. **Phase 3:** Migrate callers one-by-one, starting with lowest-risk (UI panels)
4. **Phase 4:** Migrate high-impact callers (Callouts, processes)
5. **Phase 5:** Deprecate SQL functions (add comments, keep for 1-2 releases)
6. **Phase 6:** Remove SQL functions

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

### SQL Files to Deprecate (Later)

- `db/ddlutils/postgresql/functions/C_Invoice_Open.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_Paid.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_OpenToDate.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_PaidToDate.sql`
- `db/ddlutils/postgresql/functions/C_Invoice_Discount.sql`
- Oracle and MySQL equivalents

---

## Future Expansion

After validating the Invoice pilot:
1. Currency functions (`currencyConvert`, `currencyRate`, `currencyRound`)
2. Payment functions (`paymentAllocated`, `paymentAvailable`, `paymentTermDueDate`)
3. BOM/Inventory functions (`bomQtyOnHand`, `bomQtyAvailable`, etc.)
4. Remaining utility functions
