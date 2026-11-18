---
date: 2026-01-01T20:05:43+00:00
researcher: Claude
git_commit: 9e06877a1f4c026afffcb990be605d04187cd174
branch: develop
repository: adempiere
topic: "SQL Functions to Java Migration Design"
tags: [design, migration, database, java, postgresql, oracle]
status: complete
last_updated: 2026-01-01
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: SQL Functions to Java Migration Design

## Task(s)

| Task | Status |
|------|--------|
| Brainstorm migration of stored procedures/functions from DB to Java | Completed |
| Create comprehensive inventory of DB functions across Oracle/PostgreSQL/MySQL | Completed |
| Design architecture for Invoice function migration (pilot) | Completed |
| Write design document | Completed |
| Commit design document to repository | Completed |
| Set up implementation environment | Not started |

## Critical References

1. **Design Document**: `docs/plans/2026-01-01-invoice-sql-to-java-migration-design.md` - Full architecture, testing strategy, and rollout plan
2. **PostgreSQL Invoice Function (source of truth)**: `db/ddlutils/postgresql/functions/C_Invoice_Open.sql` - Reference implementation to translate
3. **SQLJ Java Classes**: `sqlj/src/org/compiere/sqlj/Invoice.java` - Existing Java implementation callable from SQL (may be reusable)

## Recent changes

- `docs/plans/2026-01-01-invoice-sql-to-java-migration-design.md:1-215` - Created design document for SQL to Java migration

## Learnings

### Database Architecture
- ADempiere supports 4 databases: Oracle, PostgreSQL, MySQL, MariaDB
- **Single database per deployment** - user chooses at install time via `ADEMPIERE_DB_TYPE`
- PostgreSQL is the default and likely most common deployment

### Function Inventory
| Database | Functions | Procedures |
|----------|-----------|------------|
| PostgreSQL | 62 | 0 |
| Oracle | 50 | 14 |
| MySQL | 50 | 0 |

### Key Discovery: Three Implementations Exist
For business functions like `invoiceOpen`, there are THREE implementations:
1. **PL/pgSQL** (`db/ddlutils/postgresql/functions/`) - Native PostgreSQL
2. **PL/SQL** (`db/ddlutils/oracle/functions/`) - Native Oracle
3. **SQLJ Java** (`sqlj/src/org/compiere/sqlj/`) - Java callable from SQL via PL/Java

### Oracle Procedures Are Maintenance-Only
The 14 Oracle procedures (`AD_Sequence_*`, `DBA_*`, `AD_Synchronize`) are for database maintenance, not business logic. They already have Java equivalents for PostgreSQL deployments:
- `MSequence.java` handles sequence generation in Java for PostgreSQL
- `SynchronizeTerminology.java` replaces `AD_Synchronize`
- `T_InventoryValue_Create.java` replaces the Oracle procedure

### SQLJ/PL/Java Consideration
If the target deployment uses PL/Java (check: `SELECT * FROM pg_language WHERE lanname = 'java'`), the Java implementations already exist in `sqlj/`. The migration could refactor these rather than rewrite from scratch.

## Artifacts

- `docs/plans/2026-01-01-invoice-sql-to-java-migration-design.md` - Complete design document

## Action Items & Next Steps

1. **Verify target environment**: Check if PL/Java is used in target deployments
   - If yes: Consider refactoring existing `sqlj/src/org/compiere/sqlj/Invoice.java`
   - If no: Proceed with new implementation based on PL/pgSQL functions

2. **Set up implementation environment**:
   - Create git worktree for isolated development
   - Set up PostgreSQL with Garden World test data
   - Create implementation plan using `superpowers:writing-plans`

3. **Create Java service layer** (per design doc):
   - `base/src/org/compiere/model/invoice/InvoiceCalculationService.java`
   - `base/src/org/compiere/model/invoice/InvoiceRepository.java`
   - `base/src/org/compiere/model/invoice/AllocationRepository.java`
   - DTO classes in `dto/` subdirectory

4. **Write tests**:
   - Unit tests with mocked repositories extending `CommonUnitTestSetup`
   - Integration tests comparing Java results vs SQL function results

5. **Migrate callers** (start with low-risk):
   - `CalloutPayment.java`
   - `CalloutPaymentAllocate.java`
   - UI panels before core processes

## Other Notes

### Function Categories for Future Migration
| Priority | Category | Count | Notes |
|----------|----------|-------|-------|
| 1 (Pilot) | Invoice | 5 | `invoiceOpen`, `invoicePaid`, `invoiceDiscount`, etc. |
| 2 | Payment | 5 | Related to Invoice, similar patterns |
| 3 | Currency | 5 | Dependency for Invoice/Payment |
| 4 | BOM/Inventory | 13 | Complex, recursive |
| 5 | HR/Payroll | 6 | Module-specific |

### Key File Locations
- Database functions: `db/ddlutils/{postgresql,oracle,mysql}/functions/`
- Oracle procedures: `db/ddlutils/oracle/procedures/`
- SQLJ Java classes: `sqlj/src/org/compiere/sqlj/`
- Test infrastructure: `org.adempiere.test/src/test/java/org/adempiere/test/`
- Existing invoice tests: `base/test/src/org/compiere/model/Test_MInvoice_TestAllocation.java`

### Driver for Migration
User's stated goals: testability, developer experience, debugging/observability, version control. Not database portability or performance.
