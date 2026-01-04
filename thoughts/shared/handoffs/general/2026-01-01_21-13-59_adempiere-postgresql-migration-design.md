---
date: 2026-01-01T21:14:00+00:00
researcher: Claude
git_commit: 8ba41f41e757dc7e8728e599619232f2ac98dbea
branch: develop
repository: adempiere
topic: "ADempiere PostgreSQL Functions/Views Migration Strategy Design"
tags: [migration, postgresql, database, functions, views, brainstorming]
status: in_progress
last_updated: 2026-01-01
last_updated_by: Claude
type: implementation_strategy
---

# Handoff: ADempiere PostgreSQL Functions/Views Migration Design (Brainstorming Phase)

## Task(s)

**Status: In Progress - Brainstorming Phase (Early Stage)**

The user requested a design for migrating ADempiere's SQL functions and views using `~/sprocfw/step1.md` as a framework. The user explicitly stated to ignore `docs/plans/2026-01-01-invoice-sql-to-java-migration-design.md`.

The brainstorming skill was invoked and we completed:
1. **Framework review** - Read and analyzed the comprehensive stored procedure migration framework from `~/sprocfw/step1.md`
2. **Codebase exploration** - Explored ADempiere's database layer architecture
3. **First question posed** - Asked the user about the target migration state, but session ended before receiving answer

## Critical References

1. **Migration Framework**: `/home/yv01p/sprocfw/step1.md` - The 4500+ line comprehensive framework for migrating stored procedures to Java. Covers patterns A-K, shadow execution, rollback architecture, quality gates, and more.
2. **PostgreSQL Functions**: `/home/yv01p/adempiere/db/ddlutils/postgresql/functions/` - 62 PL/pgSQL functions to potentially migrate
3. **PostgreSQL Views**: `/home/yv01p/adempiere/db/ddlutils/postgresql/views/` - 184 views to potentially migrate

## Recent changes

No code changes were made - this was a discovery/brainstorming session.

## Learnings

### ADempiere Database Architecture Discovery

1. **Multi-database support**: ADempiere supports Oracle, PostgreSQL, and MySQL/MariaDB with separate SQL definitions for each:
   - PostgreSQL: `db/ddlutils/postgresql/`
   - Oracle: `db/ddlutils/oracle/`
   - MySQL: `db/ddlutils/mysql/`

2. **Database object counts**:
   - PostgreSQL functions: 62 files in `db/ddlutils/postgresql/functions/`
   - PostgreSQL views: 184 files in `db/ddlutils/postgresql/views/`
   - Oracle functions: 48 files in `db/ddlutils/oracle/functions/`

3. **Function complexity examples** (from `db/ddlutils/postgresql/functions/C_Invoice_Open.sql`):
   - Functions use PL/pgSQL with DECLARE blocks
   - Complex business logic: currency conversion, payment schedule handling
   - Dependencies on views (e.g., `C_Invoice_v`) and other functions (e.g., `currencyConvert`)
   - Exception handling with RAISE NOTICE

4. **View structure** (from `db/ddlutils/postgresql/views/C_INVOICE_V.sql`):
   - Views depend on custom functions (e.g., `charAt`, `paymentTermDueDate`)
   - Use UNION for handling different payment schedule scenarios
   - Complex calculated columns with CASE statements

5. **Java data access layer**:
   - Uses model classes extending generated base classes (e.g., `MInvoice extends X_C_Invoice`)
   - Direct JDBC via `org.compiere.util.DB` utility class
   - HikariCP connection pooling (`DB_PostgreSQL.java`)
   - Query builder pattern via `Query` class
   - Database-specific converters (e.g., `Convert_PostgreSQL`)

6. **Key Java files**:
   - `/home/yv01p/adempiere/base/src/org/compiere/db/DB_PostgreSQL.java` - PostgreSQL database adapter
   - `/home/yv01p/adempiere/base/src/org/compiere/model/MInvoice.java` - Invoice model class example
   - `/home/yv01p/adempiere/base/src/org/compiere/db/AdempiereDatabase.java` - Database interface

### Framework Key Concepts (from step1.md)

The migration framework provides:
- **Patterns A-K**: Different migration patterns for different procedure types (CRUD, cursor loops, temp tables, business logic, dynamic SQL, etc.)
- **Shadow execution**: Run legacy and Java in parallel to validate correctness
- **Quality gates**: 99.9% match rate for 7 days before cutover
- **Rollback architecture**: Feature flags for instant rollback
- **Transaction units**: Groups of procedures that must migrate together
- **Complexity scoring**: Formula to prioritize migration order

## Artifacts

None produced yet - session ended during brainstorming phase before first design section could be written.

## Action Items & Next Steps

1. **Answer the pending question**: The next agent should present this question to the user:
   > **What is the target state for this migration?**
   > 1. **PostgreSQL-only** - Remove Oracle/MySQL support, standardize entirely on PostgreSQL (views and functions remain in PostgreSQL)
   > 2. **PostgreSQL-primary with functions migrated to Java** - Keep PostgreSQL as the database, but move PL/pgSQL function logic into Java code
   > 3. **Database-agnostic Java** - Migrate all database-specific functions/views to Java so the application works identically across any database
   > 4. **Something else** - Please describe your vision

2. **Continue brainstorming questions**: After target state is clarified, explore:
   - Which functional areas to prioritize (invoicing, inventory, accounting, etc.)
   - Whether to maintain backward compatibility during transition
   - Testing and validation requirements
   - Timeline and resource constraints

3. **Propose approaches**: Present 2-3 different migration strategies with trade-offs

4. **Present design incrementally**: Per the brainstorming skill, present design in 200-300 word sections, validating each before proceeding

5. **Write design document**: Final output should be written to `docs/plans/YYYY-MM-DD-<topic>-design.md`

## Other Notes

### File Locations Reference

- **PostgreSQL functions directory**: `/home/yv01p/adempiere/db/ddlutils/postgresql/functions/`
- **PostgreSQL views directory**: `/home/yv01p/adempiere/db/ddlutils/postgresql/views/`
- **Oracle functions directory**: `/home/yv01p/adempiere/db/ddlutils/oracle/functions/`
- **Oracle views directory**: `/home/yv01p/adempiere/db/ddlutils/oracle/views/`
- **Database adapters**: `/home/yv01p/adempiere/base/src/org/compiere/db/`
- **Model classes**: `/home/yv01p/adempiere/base/src/org/compiere/model/`
- **Migration framework reference**: `/home/yv01p/sprocfw/step1.md`

### Sample Functions to Reference

When designing migration patterns, these functions provide good examples of different complexity levels:
- `C_Invoice_Open.sql` - Complex: currency conversion, loops, exception handling
- `C_Currency_Convert.sql` - Medium: utility function likely called by many others
- `addDays.sql`, `trunc.sql` - Simple: Oracle compatibility functions
- `nextID.sql` - Critical: sequence/ID generation

### Brainstorming Skill Requirements

The next agent should continue following the brainstorming skill process:
1. Ask questions one at a time (prefer multiple choice when possible)
2. Explore 2-3 approaches with trade-offs before settling on one
3. Present design in 200-300 word sections
4. Validate each section before proceeding
5. Apply YAGNI ruthlessly - remove unnecessary features from designs
