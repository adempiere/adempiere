# Function Usage Context Report

**Generated:** 2026-01-01
**Source:** Java codebase and PostgreSQL view analysis
**Total Functions:** 78 (33 called from Java, 23 called from views, 28 unused)

---

## Summary Statistics

| Usage Type | Count | Percentage |
|------------|-------|------------|
| Called from Java only | 10 | 13% |
| Called from Views only | 2 | 3% |
| Called from both Java and Views | 21 | 27% |
| Not called (dead code) | 28 | 36% |
| Internal only (called by other functions) | 17 | 22% |

---

## High-Impact Functions (Most Callers)

### 1. currencyConvert
**Call Sites:** 45+ locations
**Category:** Currency

| Caller Type | Location | Purpose |
|-------------|----------|---------|
| Java | `client/src/org/compiere/apps/form/Allocation.java:338-486` | Payment/Invoice allocation conversion |
| Java | `client/src/org/compiere/apps/form/PaySelect.java:211-253` | Pay selection amount conversion |
| Java | `base/src/org/compiere/model/MCost.java:845-971` | Cost calculation with currency |
| Java | `base/src/org/compiere/model/MPayment.java:724` | Payment allocation queries |
| Java | `base/src/org/compiere/model/MInvoice.java:1095` | Invoice allocation sum |
| Java | `base/src/org/compiere/model/MCashLine.java:400` | Cash line conversion |
| Java | `base/src/org/compiere/model/CalloutPaySelection.java:270` | Pay selection callout |
| Java | `base/src/org/compiere/process/PaySelectionCreateFrom.java:130-133` | Pay selection creation |
| Java | `base/src/org/compiere/process/CombinedAgingRevalue.java:118-125` | Aging revaluation |
| Java | `base/src/org/compiere/process/InvoiceNGL.java:134-137` | Invoice NGL processing |
| Java | `base/src/org/eevolution/process/ValuationEffectiveDate.java:126-143` | Valuation processing |
| View | `RV_CASH_DETAIL.sql:12` | Cash detail report |
| View | `RV_PROJECTCYCLE.sql:23-27` | Project cycle report (5 calls) |

**Migration Impact:** CRITICAL - Core financial function, nested in many queries

---

### 2. invoiceOpen
**Call Sites:** 25+ locations
**Category:** Invoice

| Caller Type | Location | Purpose |
|-------------|----------|---------|
| Java | `client/src/org/compiere/apps/form/Allocation.java:473-485` | Invoice allocation form |
| Java | `client/src/org/compiere/apps/form/PaySelect.java:252-265` | Pay selection filtering |
| Java | `client/src/org/compiere/apps/search/InfoInvoice.java:180-220` | Invoice info panel |
| Java | `zkwebui/WEB-INF/src/org/adempiere/webui/panel/InfoInvoicePanel.java:164-198` | ZK invoice panel |
| Java | `base/src/org/compiere/model/CalloutCashJournal.java:72` | Cash journal callout |
| Java | `base/src/org/compiere/model/CalloutPayment.java:83-329` | Payment callout (multiple) |
| Java | `base/src/org/compiere/model/CalloutPaySelection.java:269-270` | Pay selection callout |
| Java | `base/src/org/compiere/model/CalloutPaymentAllocate.java:83` | Payment allocate callout |
| Java | `base/src/org/compiere/model/MBPartner.java:724-727` | Business partner credit (via currencyBase) |
| Java | `base/src/org/compiere/process/PaySelectionCreateFrom.java:130-148` | Pay selection creation |
| Java | `base/src/org/compiere/process/DunningRunCreate.java:156` | Dunning run |
| Java | `base/src/org/compiere/process/InvoiceWriteOff.java:79` | Invoice write-off |
| Java | `base/src/org/compiere/process/InvoiceNGL.java:134` | Invoice NGL |
| View | `RV_OPENITEM.sql:21-53` | Open items report (4 calls) |
| View | `RV_BPARTNEROPEN.sql:10` | Business partner open items |

**Migration Impact:** HIGH - Known Java duplicate `MInvoice.getOpenAmt()` exists

---

### 3. BOM Pricing Functions (bomPriceStd, bomPriceList, bomPriceLimit)
**Call Sites:** 20+ locations each
**Category:** BOM Pricing

| Caller Type | Location | Purpose |
|-------------|----------|---------|
| Java | `client/src/org/compiere/apps/search/InfoProduct.java:1497-1500` | Product info panel |
| Java | `zkwebui/WEB-INF/src/org/adempiere/webui/panel/InfoProductPanel.java:1488-1491` | ZK product panel |
| Java | `org.adempiere.webservice/WEB-INF/src/com/_3e/ADInterface/InfoProduct.java:292-303` | Web service product info |
| Java | `base/src/org/compiere/model/MProductPricing.java:166-323` | Product pricing (9 calls) |
| Java | `base/src/org/compiere/model/CalloutTimeExpense.java:70-113` | Time expense callout |
| Java | `org.adempiere.pos/src/main/java/base/org/adempiere/pos/services/CPOS.java:2297-2299` | POS pricing |
| View | `RV_WAREHOUSEPRICE.sql:15-18` | Warehouse price report (4 calls) |

**Migration Impact:** HIGH - Recursive, affects product pricing across UI

---

### 4. BOM Quantity Functions (bomQtyAvailable, bomQtyOnHand, bomQtyReserved, bomQtyOrdered)
**Call Sites:** 15+ locations each
**Category:** BOM Quantity

| Caller Type | Location | Purpose |
|-------------|----------|---------|
| Java | `client/src/org/compiere/apps/search/InfoProduct.java:1505-1508` | Product info panel |
| Java | `zkwebui/WEB-INF/src/org/adempiere/webui/panel/InfoProductPanel.java:1496-1499` | ZK product panel |
| Java | `org.adempiere.webservice/WEB-INF/src/com/_3e/ADInterface/InfoProduct.java:291-296` | Web service product info |
| Java | `base/src/org/compiere/model/CalloutInventory.java:207` | Inventory callout |
| Java | `org.eevolution.manufacturing/.../MRPDetailed.java:125` | MRP detailed form |
| Java | `org.eevolution.manufacturing/.../OrderReceiptIssue.java:164-343` | Order receipt/issue |
| Java | `org.eevolution.manufacturing/.../MPPMRP.java:1030` | MRP calculation |
| View | `RV_WAREHOUSEPRICE.sql:20-23` | Warehouse price report (4 calls) |

**Migration Impact:** HIGH - Recursive, affects inventory across manufacturing

---

### 5. paymentAvailable / paymentAllocated
**Call Sites:** 10+ locations
**Category:** Payment

| Caller Type | Location | Purpose |
|-------------|----------|---------|
| Java | `client/src/org/compiere/apps/form/Allocation.java:339` | Allocation form |
| Java | `base/src/org/compiere/process/DunningRunCreate.java:380` | Dunning run |
| Java | `base/src/org/compiere/process/CombinedAgingRevalue.java:121-149` | Aging revaluation |
| View | `RV_PAYMENT.sql:33-34` | Payment report |
| View | `RV_BPARTNEROPEN.sql:22` | Business partner open items |

**Migration Impact:** MEDIUM - Depends on currencyConvert

---

## Functions by Java Caller File

### Most Function Calls per File

| File | Functions Called | Total Calls |
|------|------------------|-------------|
| `Allocation.java` | currencyConvert, invoiceOpen, paymentAvailable, invoiceDiscount | 12+ |
| `PaySelect.java` | currencyConvert, invoiceOpen, paymentTermDiscount | 5 |
| `InfoProduct.java` | bomPriceStd, bomPriceList, bomPriceLimit, bomQtyAvailable, bomQtyOnHand, bomQtyReserved, bomQtyOrdered | 8 |
| `MProductPricing.java` | bomPriceStd, bomPriceList, bomPriceLimit | 9 |
| `PaySelectionCreateFrom.java` | currencyConvert, invoiceOpen, paymentTermDiscount | 5 |
| `MCost.java` | currencyConvert | 6 |
| `CalloutPayment.java` | invoiceOpen | 4 |

---

## Functions by View Usage

### Views Using Most Functions

| View | Functions Used | Count |
|------|----------------|-------|
| `RV_WAREHOUSEPRICE.sql` | bomPriceList, bomPriceStd, bomPriceLimit, bomQtyAvailable, bomQtyOnHand, bomQtyReserved, bomQtyOrdered | 7 |
| `RV_OPENITEM.sql` | invoiceOpen, invoicePaid | 6 |
| `RV_PROJECTCYCLE.sql` | currencyConvert | 5 |
| `RV_BPARTNEROPEN.sql` | invoiceOpen, paymentAvailable, daysBetween | 3 |
| `RV_PAYMENT.sql` | paymentAllocated, paymentAvailable | 2 |
| `RV_CASH_DETAIL.sql` | currencyConvert | 1 |

---

## Call Pattern Analysis

### Pattern 1: Nested Function Calls
Most common pattern - functions called inside other functions:

```sql
-- Example from Allocation.java
currencyConvert(invoiceOpen(C_Invoice_ID, C_InvoicePaySchedule_ID), ...)

-- Example from PaySelect.java
currencyConvert(invoiceOpen(...) - paymentTermDiscount(...), ...)
```

**Impact:** Must maintain same signature or refactor all callers

### Pattern 2: Inline SQL in Java Strings
45+ calls embed SQL directly in Java:

```java
// Example from Allocation.java:338
"currencyConvert(p.PayAmt,p.C_Currency_ID,?,?,p.C_ConversionType_ID,p.AD_Client_ID,p.AD_Org_ID) AS ConvertedAmt"
```

**Impact:** Must maintain backward compatibility during migration

### Pattern 3: Only nextID Uses CallableStatement
All other functions are called via inline SQL, not JDBC CallableStatement:

```java
// Only example of CallableStatement usage
CallableStatement cs = conn.prepareCall("{call nextID(?, ?, ?)}");
```

**Impact:** Migration can use same SQL interface pattern

---

## Functions Called from Multiple Contexts

| Function | Java Files | Views | Internal Calls | Total Contexts |
|----------|------------|-------|----------------|----------------|
| currencyConvert | 15+ | 3 | 3 (currencyBase, etc.) | 21+ |
| invoiceOpen | 12+ | 3 | 0 | 15+ |
| bomPriceStd | 6 | 1 | 0 | 7 |
| bomQtyOnHand | 5 | 1 | 1 (bomQtyAvailable) | 7 |
| paymentAvailable | 3 | 2 | 0 | 5 |

---

## Unused Functions (28 Total)

Functions with no Java or view callers:

### HR/Payroll (6 functions - entire category unused)
- dailySalary
- dailySalaryToDate
- dailySalaryToDateByHRProcess
- monthlySalary
- monthlySalaryToDate
- ProcessReportSource

### BOM ASI Variants (4 functions)
- bomQtyAvailableASI
- bomQtyOnHandASI
- bomQtyOrderedASI
- bomQtyReservedASI

### Date/String Utilities (6 functions)
- add_months (internal only)
- addweeks
- addyears
- instr
- subtractdays
- nextBusinessDay (internal: paymentTermDiscount)

### Other Unused (12 functions)
- altercolumn (DDL utility)
- bpartnerRemitLocation
- currencyBaseType
- deps_save_and_drop_dependencies (DDL utility)
- deps_restore_dependencies (DDL utility)
- financialRateToDate
- getUUID
- nextIDByYear
- prodQtyOrdered
- prodQtyReserved

---

## Migration Refactoring Opportunities

### 1. Consolidate BOM Functions
All BOM quantity functions share same pattern - could share implementation:
- `bomQtyOnHand`, `bomQtyReserved`, `bomQtyOrdered` - differ only in which quantity field

### 2. Consolidate Invoice Functions
`invoiceOpen` and `invoiceOpenToDate` - second adds date parameter

### 3. Replace View Usages with Java Queries
Views using functions can be replaced with Java query methods:
- `RV_OPENITEM` -> `OpenItemQuery.findOpenItems()`
- `RV_WAREHOUSEPRICE` -> `WarehousePriceQuery.findPrices()`

---

## Next Steps

1. **Start with utility functions** (getDate, daysBetween, addDays, trunc)
2. **Migrate currency chain** (currencyRound -> currencyRate -> currencyConvert)
3. **Migrate invoice functions** (invoiceOpen, invoicePaid)
4. **Migrate payment functions** (paymentAvailable, paymentAllocated)
5. **Migrate BOM functions last** (most complex, recursive)
6. **Deprecate dead code** (28 unused functions)
