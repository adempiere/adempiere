# Wave4 Function Call Sites Analysis

## Summary

After comprehensive search of the ADempiere codebase, the Wave4 functions are used as follows:

| Function | Java Call Sites | SQL-only Usages | Notes |
|----------|-----------------|-----------------|-------|
| `acct_balance()` | None | No views found | Function defined in PostgreSQL, no Java callers found |
| `productattribute()` | **SQL-embedded only** | OutBoundOrder.java | Used in SQL strings passed to DB |
| `documentno()` | None | No views found | Function defined in PostgreSQL/Oracle, no Java callers found |
| `linenetamtrealinvoiceline()` | **SQL-embedded only** | MCommissionRun.java | Used in SQL strings passed to DB |
| `linenetamtrealorderline()` | **SQL-embedded only** | MCommissionRun.java | Used in SQL strings passed to DB |
| `maxpaydate()` | **SQL-embedded only** | MCommissionRun.java | Used in SQL strings passed to DB |
| `get_Sysconfig()` | None | N/A | Already implemented in Java via MSysConfig |

## Detailed Call Site Analysis

### 1. linenetamtrealinvoiceline() - SQL-EMBEDDED, NO JAVA WIRING NEEDED

**File:** `/home/yv01p/adempiere/base/src/org/compiere/model/MCommissionRun.java`

**Lines:** 518, 614, 632, 696, 707, 846, 850, 873

**Pattern:** These are SQL function calls embedded inside complex SQL queries that are executed by the database. Example:

```java
// Line 518 - Function used inside SQL query string
sql.append("SELECT ((SUM(linenetamtrealinvoiceline(l.c_Invoiceline_ID)) / MAX(fl.ForecastAmt)) * 100) "
        + "FROM C_Invoice h "
        + "INNER JOIN C_InvoiceLine l ON (h.C_Invoice_ID = l.C_Invoice_ID) "
        // ... more complex SQL ...
```

**Why no Java wiring:** The function is called BY THE DATABASE ENGINE during query execution, not by Java code. Converting these would require:
1. Breaking apart the SQL queries
2. Fetching all invoice line IDs first
3. Calling Java function for each ID
4. Aggregating results in Java

This would be a major architectural change with significant performance implications and is OUT OF SCOPE for the routing task.

### 2. linenetamtrealorderline() - SQL-EMBEDDED, NO JAVA WIRING NEEDED

**File:** `/home/yv01p/adempiere/base/src/org/compiere/model/MCommissionRun.java`

**Lines:** 549, 672, 683, 950, 954, 977

**Pattern:** Same as linenetamtrealinvoiceline - functions used in SQL strings that are executed by the database.

### 3. maxpaydate() - SQL-EMBEDDED, NO JAVA WIRING NEEDED

**File:** `/home/yv01p/adempiere/base/src/org/compiere/model/MCommissionRun.java`

**Lines:** 606, 649, 659

**Pattern:** Used in SQL WHERE clauses for payment date filtering:

```java
// Line 606 - Function used in SQL WHERE clause
sqlAppend = " AND (p.DateTrx <= ? OR p.DateTrx <= ?) AND InvoiceopenToDate(h.C_Invoice_ID, null, ?) = 0 AND maxPayDate(h.c_Invoice_ID) BETWEEN ? AND ? ";
```

### 4. acct_balance() - NO JAVA CALLERS FOUND

No Java code currently calls this function. It is only defined in:
- `db/ddlutils/postgresql/functions/acctBalance.sql` (PostgreSQL implementation)
- `base/src/org/compiere/migration/SqlFunctionCaller.java` (wrapper for router)
- `base/src/org/compiere/migration/Wave4Functions.java` (Java implementation)

### 5. productattribute() - SQL-EMBEDDED, NO JAVA WIRING NEEDED

**File:** `/home/yv01p/adempiere/org.eevolution.warehouse/src/main/java/base/org/spin/wms/form/OutBoundOrder.java`

**Lines:** 387, 464

**Pattern:** Function used in SQL strings for product attribute display:

```java
// Line 387 - Function used inside SQL query string
"(pro.Name || COALESCE(' - ' || productattribute(lord.M_AttributeSetInstance_ID), '')) Product, "
```

**Why no Java wiring:** Same pattern as other SQL-embedded calls - the function is called BY THE DATABASE ENGINE during query execution.

### 6. documentno() - NO JAVA CALLERS FOUND

No Java code currently calls this function. Used in LiberoMRP views:
- `db/ddlutils/oracle/functions/documentNo.sql` (Oracle implementation)
- View column references like `mrp.documentno` are column accesses, NOT function calls

### 7. get_Sysconfig() - ALREADY JAVA-IMPLEMENTED

This function is already implemented natively in Java via `MSysConfig.getValue()`. No routing needed.

## Conclusion

**No Java wiring changes are required for Task 5.4.**

All discovered usages fall into one of these categories:

1. **SQL-embedded calls** (MCommissionRun.java, OutBoundOrder.java): Functions are called inside SQL strings that are executed by PostgreSQL. These calls cannot be routed to Java without major refactoring that would:
   - Break apart complex SQL queries
   - Require multiple round-trips to the database
   - Potentially degrade performance significantly
   - These will continue using PostgreSQL functions until a full JAVA_ONLY cutover with view updates

2. **No Java callers** (acct_balance, documentno): These functions have no Java code calling them directly. They are available via `Wave4FunctionRouter` if/when needed.

3. **Already Java-native** (get_Sysconfig): Already implemented in Java, documented as such.

## Router Availability

The `Wave4FunctionRouter` class is available for any future Java callers:

```java
import org.compiere.migration.Wave4FunctionRouter;

// Available methods:
Wave4FunctionRouter.acctBalance(accountId, amtDr, amtCr);
Wave4FunctionRouter.productAttribute(attributeSetInstanceId);
Wave4FunctionRouter.documentNo(mrpId);
Wave4FunctionRouter.linenetamtrealinvoiceline(invoiceLineId);
Wave4FunctionRouter.linenetamtrealorderline(orderLineId);
Wave4FunctionRouter.maxpaydate(invoiceId);
Wave4FunctionRouter.getSysconfig(name, clientId, orgId);
```

## Search Commands Used

```bash
# Search for Java call sites
grep -rn "productattribute\|documentno\|linenetamtrealinvoiceline\|linenetamtrealorderline\|maxpaydate\|acct_balance" \
    --include="*.java" base/src org.adempiere libero zkwebui

# Search for SQL view usages
grep -rn "productattribute\|documentno\|linenetamtrealinvoiceline\|linenetamtrealorderline\|maxpaydate\|acct_balance" \
    --include="*.sql" db/ddlutils/
```
