# org.adempiere.test

Scala (ScalaTest) regression tests that run against a real ADempiere database, plus the Scala DSL
(`src/main/scala`) used to build documents in the tests.

How the build is wired:

| Build | Used by | Notes |
|---|---|---|
| `build.gradle` (this module) | Gradle / CI (`.github/workflows/build_with_gradle.yml`) | Compiles only the Java in `src`. It never runs the Scala tests. |
| **`build.sbt` in the repository root** | Developers, to run the Scala tests | Supported way to run the tests. Described below. |
| `org.adempiere.test/build.sbt` | Nobody (legacy standalone Scala 2.13 build) | Kept for IntelliJ users; only its hardcoded paths were replaced by environment variables. |

No CI job calls sbt, so the root `build.sbt` is a developer tool.

## What the root build does

* `adempiereCore` (sbt sub-project) compiles the Java of the repository with sbt's incremental compiler (zinc):
  `tools`, `base`, `client`, `extend`, `migration`, `JasperReports` and the `org.*` modules (the same source
  directories the Gradle modules use, without the web and server modules). The tests therefore always run
  against the Java sources of your working tree, no Ant `bin/` or `Adempiere.jar` is needed.
  The classes are needed at run time too: the model validators registered in `AD_ModelValidator`
  (project, CRM, asset, freight, ...) are loaded when the test logs in.
* The root project compiles `org.adempiere.test/src/main/scala` and `src/test/scala` (Scala 3.2.1) and the
  Java tests in `src/test/java`.
* Libraries: the jars bundled under `lib`, `tools/lib` and `zkwebui/WEB-INF/lib`
  (`tools/lib/legacy`, `junit`, `junit-old` and `testing` are skipped), SuperCSV from `tools/lib/testing`, and
  from Maven the same JUnit/Mockito versions as `build.gradle`: `junit-jupiter-api` 5.7.0,
  `mockito-junit-jupiter` 3.6.0, `junit-platform-console-standalone` 1.7.0.

## Prerequisites

1. **JDK 17.** Run sbt with JDK 17 (`JAVA_HOME`). sbt 1.6.2 (`project/build.properties`) does not even start on
   JDK 21 or newer. The build stops with
   `ADempiere needs JDK 17 but found ...` when compile or test runs on another JDK. To run the build with
   another sbt JDK use `ADEMPIERE_JAVA_HOME` (below). The forked test JVM gets the `--add-exports` options
   the ADempiere security classes need.
2. **sbt 1.6.x** (the `sbt` launcher picks the version from `project/build.properties`).
3. **A PostgreSQL (or Oracle) database with the GardenWorld demo client**, user `SuperUser`/`System`, role
   `GardenWorld Admin`, organization `HQ` (see `AdempiereStartup.scala`). The tests read and write this database;
   most of them roll their transaction back, but do not use a production database.
4. **The connection to that database.** By default the build generates
   `target/adempiere-test/Adempiere.properties` for PostgreSQL on `localhost:5432`, database `adempiere`, user
   `adempiere` (change them with the `ADEMPIERE_DB_*` variables below). To use your own `Adempiere.properties`
   instead, set `ADEMPIERE_PROPERTY_FILE`; its `Connection=CConnection[...]` entry must say `type=PostgreSQL` when
   the database is PostgreSQL: a file with `type=Oracle` connects with the wrong driver. The tests never read
   `~/Adempiere.properties`, which may point to another database.
5. **Every class registered in `AD_ModelValidator` must be on the classpath**, otherwise the login of
   `AdempiereStartup` is refused (`Login.validateLogin: Refused ... ClassNotFoundException`, the test JVM
   exits with no test run). A database that was used with another ADempiere distribution can reference classes that
   this repository does not contain (for example `org.eevolution.model.GRPAccountValidator` or
   `org.eevolution.context.service.domain.validators.ContractModelValidator`). Options:
   * put the classes of that distribution on the test classpath with `ADEMPIERE_TEST_CLASSPATH`
     (a directory of classes or a jar; a directory with empty stub classes that implement
     `org.compiere.model.ModelValidator` is enough for the tests);
   * or use a database without those rows;
   * or set `AD_System.IsFailOnMissingModelValidator='N'` in that database (the engine only refuses the login
     when this flag is `Y`, see `ModelValidationEngine.loginComplete`). This changes the database, so it is your
     decision and the build never does it.

## Environment variables

Each one can also be passed as a JVM property to sbt (`sbt -Dadempiere.propertyFile=...`).
The property wins over the variable.

| Variable | Property | Default | Meaning |
|---|---|---|---|
| `ADEMPIERE_PROPERTY_FILE` | `adempiere.propertyFile` | `<repo>/install/build/Adempiere/Adempiere.properties` when it exists, otherwise the generated `<repo>/target/adempiere-test/Adempiere.properties` | `Adempiere.properties` handed to the tests as `-DPropertyFile`. The test run stops with a message when a configured file does not exist. |
| `ADEMPIERE_DB_HOST` | `adempiere.dbHost` | `localhost` | PostgreSQL host of the generated property file. |
| `ADEMPIERE_DB_PORT` | `adempiere.dbPort` | `5432` | PostgreSQL port of the generated property file. |
| `ADEMPIERE_DB_NAME` | `adempiere.dbName` | `adempiere` | Database of the generated property file. |
| `ADEMPIERE_DB_USER` | `adempiere.dbUser` | `adempiere` | Database user of the generated property file. |
| `ADEMPIERE_DB_PASSWORD` | `adempiere.dbPassword` | empty | Database password of the generated property file (empty works with `trust` authentication). |
| `ADEMPIERE_JAVA_HOME` | `adempiere.javaHome` | JDK that runs sbt | JDK 17 used to compile the Java and to fork the tests. |
| `ADEMPIERE_TEST_CLASSPATH` | `adempiere.testClasspath` | empty | Extra classpath entries (separator `:` on macOS/Linux) for the test JVM, for example the model validator classes of point 5. |
| `ADEMPIERE_HOME` | `adempiere.home` | `<repo>/target/adempiere-home` | Directory where the test JVM writes the ADempiere logs. |
| `ADEMPIERE_SOURCE_DIR` | `adempiere.sourceDir` | the sbt build root | Source tree to compile; only change it if you build another checkout. |
| `ADEMPIERE_APPS_TYPE` | - | `standalone` | Set by the build for the test JVM; ADempiere refuses to open the connection pool without it. |

## Commands

Run them from the repository root.

```bash
export JAVA_HOME=/path/to/jdk-17
# database: localhost:5432, DB adempiere, user adempiere by default; or your own property file
# export ADEMPIERE_DB_PASSWORD=...
# export ADEMPIERE_PROPERTY_FILE=/path/to/Adempiere.properties

# compile everything (Java modules, Scala DSL, Scala and Java tests)
sbt Test/compile

# run one test class
sbt "testOnly org.eevolution.test.ValidateReportLookupJoinRegression"

# run one scenario of a class (ScalaTest -z selects by scenario name substring)
sbt "testOnly org.eevolution.test.ValidateReportLookupJoinRegression -- -z \"ORDER BY\""

# run all Scala tests
sbt test
```

The first build compiles about 4,500 Java sources (about 30 s); later runs reuse the incremental analysis
(a run with no source change starts testing in a few seconds). `sbt clean` forces a full rebuild.

## Tests and status

Every Scala test class is compiled and run by `sbt test`. Status on a local GardenWorld database
(PostgreSQL, JDK 17, validator stubs on `ADEMPIERE_TEST_CLASSPATH`):

| Test class | Scenarios | Status | Notes |
|---|---|---|---|
| `ValidateFindBindParameters` | 9 | pass | Find, `initialQuery` and the zoom to a detail tab bind their MQuery restriction (`FindCount`, `DB.getSQLValueQuery`): the count equals the literal count for a String LIKE, a date range, a number, a text on an ID column (R3) and a raw `whereExtended` with `@context@` in Order, Business Partner and Product; the link value of the zoom equals the literal one. `DB.inlineQueryParameters` renders each type, skips a `?` inside a string literal and executes to the same rows as the bound statement; `_TabInfo_SQL` (and `_TabInfo_FindSQL`) keep the literal text with no `?`. The Swing and ZK dialogs are verified by compilation only. |
| `ValidateReportLookupJoinRegression` | 22 | pass | Report lookups as LEFT JOINs, nested TableDir identifiers chained up to depth 3 (`<Word>_<n>_<m>_<k>`): same rows, NULLs, GROUP BY and ORDER BY as the legacy subselects. |
| `CreateSalesOrder` | 1 | pass | |
| `ValidatePriceListAndDiscountFunctionality` | 10 | pass | |
| `ValidateWmRuleLocatorArgumentOrder` | 1 | pass | Creates its own WM area type and section type: GardenWorld has none. |
| `ValidateReportWithoutCloseTrxConnection` | 2 | pass | Needs `ServerReportCtl`/`ReportExportHandler` to accept the `AD_PInstance.ReportType` list values (`P`, `C`, `H`, `X`, `XX`); the second scenario checks that an unknown type produces no file and no `NullPointerException`. |
| `ValidateReportDefinitionCache` | 14 | pass | Report definition cache of `DataEngine` (see below). Two scenarios are cancelled without `pg_stat_statements`. |
| `ValidateReportLookupJoinPerformance` | 10 | cancelled | Opt-in benchmark (see below). Without `ADEMPIERE_BENCHMARK=true` every scenario is cancelled, so `sbt test` stays fast. |
| `ValidateConnectionPool` | 13 | pass | Connection layer (see below): no per-checkout round trips, state after the pool, `ApplicationName`, pool settings. Three scenarios count statements with `pg_stat_statements` (cancelled without it; run it while nobody else uses the database); the last one is an opt-in measurement (`ADEMPIERE_BENCHMARK=true`) and is cancelled otherwise. |
| `ValidateZoomBindParameters` | 9 | pass | The zoom family binds the MQuery values: `DB.isSOTrx(table, MQuery.BoundWhereClause)` answers as the literal overload for a sales and a purchase order and order line, with an Integer key and with a String key (R3); `Query.setQueryParameters` finds the same record as the literal `Query` (the zoom across of Swing and ZK), and a String on a numeric column needs it, because `Query.setParameters` fails on PostgreSQL with `numeric = character varying`; `Query.setParameters` keeps its behavior; `MRelationType` gives the same count and first key as the literal statements for the orders, invoices and partners of GardenWorld (its restriction is raw, so it has no parameters); `GenericZoomProvider` stays raw (no parameters, the text of the literal where clause). `AEnv`, `MLookup.getZoom`, `AZoomAcross` and `WZoomAcross` are verified by compilation only. Read-only: nothing is written to the database. |
| `ValidateLookupBindParameters` | 3 | pass | The zoom query of a lookup is one raw restriction (`MLookupFactory`, from the where clause of the table reference): for up to 30 table references with a where clause it has no parameters and the literal text, so `VLookup`, `WSearchEditor`, `ADLookup` and the Info windows have nothing to bind and keep the literal text. `DB.getSQLValueStringQuery`, used by `lookupValue` of the servlet and mobile `WWindow`, returns the row of the literal statement for a raw restriction and for a structured one (Integer and String key, R3), with the values of the restriction in front of the key. The `WWindow` pair is verified by compilation only. Read-only. |
| `ValidateReportBindParameters` | 12 | pass | `DataEngine` runs the restrictions of a report `MQuery` as bind parameters: the same rows, columns and order as the literal statement (EQUAL on an ID, LIKE on a name, BETWEEN on numbers, an OR group, the date range of a process instance, and a String value on a numeric column; lookup joins on and off), the shared binder `DB.setQueryParameters` (a String is sent untyped on PostgreSQL, the other types as `DB.setParameter`), `?` placeholders and `PrintData` values in equal number, the parameters in the FINE log, and the plan reuse switch `REPORT_PLAN_CACHE` observed in `pg_prepared_statements` (PostgreSQL only). The process instance is committed for a moment and deleted right after. |
| `ValidateMQueryBindParameters` | 23 | pass | `MQuery.getBoundWhereClause`: `?` placeholders and ordered values for every operator and value type, AND/OR nesting, raw restrictions and the fallback to the literal text when a raw restriction has a `?`; the same rows as the literal `getWhereClause` (pinned, unchanged) on GardenWorld; `MQuery.get` from a process instance (half-open day ranges, BigDecimal numbers). The process instance is committed for a moment, because `MQuery.get` reads it without a transaction, and deleted right after. |
| `ValidateWindowBindParameters` | 12 | pass | `GridTab.query` runs the MQuery restrictions of a window tab as bind parameters through `GridTable` (`?` in the select and the count, the values in `getSelectWhereParameters`): Sales Order, Business Partner and Product (EQUAL, LIKE, BETWEEN on a date and a number, IS NULL), a child tab with its link column, a column outside the tab (IN subquery), and a text value against a numeric or date column (R3: the binder sends a String untyped on PostgreSQL, as the literal was); the same rows as the raw literal restriction; a changed value re-queries; the `GridTable` binder for Timestamp, Boolean and null, and the reset of the parameters. A child tab binds the value of its link column, ahead of the values of the restriction: Sales Order > Order Line and Business Partner > Location keep the same SQL text and change only the parameter when the parent record changes, return the rows of the literal link value, and `getWhereExtended()` keeps the literal link value. A sweep of every window and tab reachable without a screen compares an EQUAL on the key bound and literal, and, for every child tab, the rows of the first parent record bound against literal (a dictionary WHERE with `@context@` compares the SQL only, because the first query changes the window context). Read-only: nothing is written to the database. |

`AdempiereStartup` and `AdempiereTestCase` are test support, not tests.

## Report definition cache

`DataEngine` caches what the dictionary says about a report (print format items, columns, group and function specs,
the lookup join plan, the SQL fragments that do not depend on the user, the role or the query, and the per-lookup
metadata): a warm `getPrintData` reads nothing from `AD_PrintFormat`, `AD_PrintFormatItem`, `AD_Column`, `AD_Table`,
`AD_Ref_Table` or `AD_ReportView`. `SysConfig` `REPORT_DEFINITION_CACHE` (default `Y`, per client) and
`DataEngine.setDefinitionCache(Boolean)` switch it. It is dropped when a row of `AD_PrintFormat`,
`AD_PrintFormatItem`, `AD_Column`, `AD_Table`, `AD_Ref_Table`, `AD_Reference`, `AD_ReportView` or
`AD_ReportView_Col` is created, changed or deleted, and every 60 minutes (`ReportDefinitionCache.setExpireMillis`).

The change is reported by `PO`: after a successful save (new records included) and a successful delete it calls
`CacheMgt.fireChange(tableName, recordId, trxName, newRecord, deleted)`, which tells every `CacheChangeListener`
registered with `CacheMgt.addChangeListener` (a copy-on-write list, so a save never meets a listener that is being
registered). `ReportDefinitionCache` registers one listener that ignores every table but those eight. A listener that
throws is logged and never fails a save. The existing `CacheMgt.reset` calls of `PO` are unchanged, so no other cache
behaves differently. Other JVMs have no shared reset: expiry bounds the staleness.

`ValidateReportDefinitionCache` compares the engine with the cache switched off (the oracle) against a cold build and
a cache hit, then changes the dictionary and checks that the next run reflects it:

* the SQL and the `PrintData` of every table based print format are identical with the cache on and off, in en_US and
  es_MX, with subselects and with joins;
* a warm run executes no dictionary statement. This is observed with `pg_stat_statements` (a delta of the statements of
  the database), never through the storage of the cache; without the extension the two scenarios that count statements
  are cancelled. The extension must be in `shared_preload_libraries` and created in the database
  (`CREATE EXTENSION pg_stat_statements`);
* update (IsPrinted, SeqNo, SortNo), insert and delete of a print format item, a change of `AD_PrintFormatItem_Trl`,
  of an `AD_Column` identifier, of an `AD_Ref_Table` and an insert and delete of an `AD_ReportView_Col` are visible in
  the next run;
* a change inside a transaction that is rolled back leaves no stale definition, and a transaction that commits is
  visible after the commit (`org.compiere.util.Trx` has no commit or rollback listener; a transaction with an
  uncommitted dictionary change is "dirty": no run reads from or writes to the cache until it ends);
* 8 threads x 25 runs on one print format and on different print formats with a cold cache return the single-thread result;
* 4 threads save `AD_PrintFormatItem` changes (each in its own transaction, rolled back) while 4 threads run cold
  `getPrintData` for four formats and four client contexts (two of them never ran a report): no exception on either
  side, and the reports and the dictionary are correct afterwards;
* saving, updating and deleting a `C_Order` (rolled back) is seen by a change listener but leaves the cache warm: the next
  `getPrintData` runs no dictionary statement (`pg_stat_statements`);
* a change listener that throws does not fail the save, the update or the delete, and the listeners after it are still called.

The scenarios that change the dictionary create a print format of `C_Order` (and, for the last scenario, an
`AD_ReportView`) with committed saves, so the model events reach the cache like in production, and delete them in a
`finally` block; two dictionary rows (`C_PaymentTerm.Description` and the `SalesRep_ID` reference table) are changed and
restored. Only consumed sequence numbers stay behind. Do not run them on a database that other people are using.

## Performance benchmark of the report lookups

`ValidateReportLookupJoinPerformance` compares the two report lookup strategies of `DataEngine`: correlated
subselects (`setLookupJoin(false)`, legacy) and `LEFT OUTER JOIN` (`setLookupJoin(true)`, join). It is opt-in: without
`ADEMPIERE_BENCHMARK=true` all its scenarios are cancelled (they show as `canceled`, not as failures).

```bash
export JAVA_HOME=/path/to/jdk-17
ADEMPIERE_BENCHMARK=true sbt "testOnly org.eevolution.test.ValidateReportLookupJoinPerformance"
```

| Variable | Default | Meaning |
|---|---|---|
| `ADEMPIERE_BENCHMARK` | unset | `true` enables the benchmark. |
| `ADEMPIERE_BENCHMARK_SCALE` | `200` | Factor that multiplies the rows of `Fact_Acct` and `C_OrderLine` in the scaled scenario. `1` copies nothing. |
| `ADEMPIERE_BENCHMARK_REPORT` | `org.adempiere.test/target/report-lookup-join-benchmark.md` | Where the markdown report is written. It is also printed. |
| `ADEMPIERE_BENCHMARK_SQL_DIR` | unset | When set, the SQL of both paths (as PostgreSQL receives it) is saved as `<format>-legacy.sql` / `<format>-join.sql`, so a DBA can run `EXPLAIN` by hand. |

What it measures, for each of four GardenWorld print formats (chosen because they have the most top-level
TableDir / Search lookups: `Fact_Acct` 1000007, `C_OrderLine` 1000008, `RV_OrderDetail` 1000004, `C_Order` 1000003):

* `EXPLAIN (ANALYZE, BUFFERS)` of the exact `PrintData` SQL through the JDBC connection of the test transaction:
  planning and execution time, number of `SubPlan` nodes, total loops of those SubPlans and shared buffers.
  PostgreSQL only; on another database the columns show `n/a`.
* The JDBC full fetch of that SQL (execute plus reading every cell): 3 warmup runs, then 15 runs interleaved
  legacy/join/legacy/join; median and p95.
* The end-to-end `new DataEngine(lang, trx).getPrintData(...)` time, median of 5.

The natural GardenWorld volume is tiny (`Fact_Acct` 144 rows), so a second, scaled scenario copies the rows of
`Fact_Acct` and `C_OrderLine` `ADEMPIERE_BENCHMARK_SCALE - 1` more times with `INSERT ... SELECT` and repeats the
measurements on that data:

* It happens inside a transaction that is always rolled back; there is no DDL and no `ANALYZE` (it would update
  `pg_class` outside the transaction). The benchmark refuses to run when the connection autocommits, and stops when
  a second connection sees the new rows. The report states the row counts before the run and after the rollback.
* New keys start 1,000,000 above the highest existing key; every other column, foreign keys included, is copied as it is.
* Rolled back rows leave dead tuples that autovacuum removes; the visible rows are exactly the same as before.
* The scaled scenario needs PostgreSQL (`generate_series`); on another database it is cancelled.

How to read the report: the summary table lists, per format and volume, the speedup `legacy / join` of the fetch
median, the fetch p95, the `EXPLAIN` execution time and the end-to-end time (greater than `1.00x`: the join path is
faster). The table of each format adds rows, `SubPlan` nodes and their loops (the work that the join removes), planning
time (the join SQL has more joins and is planned slower) and the absolute milliseconds.
Judge the speedup by the fetch median and the execution time of the scaled volume; at the natural volume the
differences are a few milliseconds and the p95 is noise. If the fetch median is far above the `EXPLAIN` execution time,
something else was using the machine or the database: run it again.

The test fails only on a clear regression (join fetch median above the legacy median times 1.20 and more than 20 ms
worse) or when the two paths return a different number of rows. Improvements are reported, never asserted, because
timings are not deterministic.

The lookup comparison runs with the definition cache off (`setDefinitionCache(false)`), so its figures keep meaning
"definition read from the dictionary on every run" and stay comparable with the figures before the cache existed.

### Benchmark of the report definition cache

The same opt-in class has a second feature (`Definition cache: ...` scenarios, same `ADEMPIERE_BENCHMARK=true`). For the
four formats above it measures, with the cache **off** (dictionary read on every run), **cold** (the cache and the
`MTable`/`MColumn` caches emptied before every run: build and store) and **warm** (hits):

* the definition time: `getPrintData` of a query that returns no row (median of 20 runs), so what remains is reading
  and building the definition;
* the end-to-end `getPrintData` time with the real query (median of 5 runs);
* the dictionary statements of one run, from `pg_stat_statements` (`n/a` without it);
* one pass over every active table based print format (definition only), and the heap held by the cache after
  172 formats x 2 languages x 2 paths (`System.gc()` before and after, indicative).

The report is appended to the markdown file of the lookup comparison. The scenarios fail only when a warm run executes a
dictionary statement, when the rows differ, or when a cache hit is clearly slower than reading the dictionary.

## Connection pool

The pools of `DB_PostgreSQL`, `DB_Oracle`, `DB_MySQL` and `DB_MariaDB` (HikariCP 5.0.1) are configured by `AdempiereDatabase.configurePool`
and `ValidateConnectionPool` checks them.

* **Connection state.** The pool gives every connection `autoCommit=true` and `TRANSACTION_READ_COMMITTED`, and Hikari restores both
  (and rolls back uncommitted work) when a connection returns to the pool. `getFromConnectionPool` therefore calls `setAutoCommit` /
  `setTransactionIsolation` only when the request differs from the pool default (`AdempiereDatabase.applyConnectionState`), and
  `CConnection.getConnection` no longer reads `getTransactionIsolation()`. In PostgreSQL that were two round trips
  (`SET SESSION CHARACTERISTICS` and `SHOW TRANSACTION ISOLATION LEVEL`) on every statement outside a transaction. A datasource from JNDI that
  does not declare `transactionIsolation` is not known, so its isolation is still always set (as before); the template of
  `install/Adempiere/build.xml` declares it.
* **Validation.** PostgreSQL and Oracle pools have no `connectionTestQuery`: Hikari validates with the JDBC4 `Connection.isValid`.
  MySQL and MariaDB keep the query.
* **`ApplicationName`.** PostgreSQL connections show `ADempiere AdempiereDS` (`AdempiereSRDS` for the short running pool) in
  `pg_stat_activity.application_name`; Oracle shows it as `v$session.program`.
* **No MySQL properties.** `cachePrepStmts`, `prepStmtCacheSize` and `prepStmtCacheSqlLimit` only exist in the MySQL driver; pgjdbc and
  ojdbc ignore them, so only the MySQL and MariaDB pools keep them. pgjdbc prepares a statement on the server after 5 executions
  (`prepareThreshold`) and keeps its own 256 statement cache; ojdbc has the implicit statement cache (below).

### Pool settings (JVM system properties)

The standalone and client pool `AdempiereDS` read these properties, so the pool can be tuned without a rebuild (for example
`JAVA_TOOL_OPTIONS="-Dorg.adempiere.db.pool.maximumPoolSize=50"`). Times are in milliseconds. A property that is not set, is not a number or is
refused by Hikari (it is logged) leaves the default. The short running pool uses the prefix `org.adempiere.db.poolShortRunning.`.
A datasource from JNDI (`install/Adempiere/build.xml`, Jetty or Tomcat) is configured by its `Resource` entry, not by these properties.

| Property (`org.adempiere.db.pool.` + name) | Default |
|---|---|
| `maximumPoolSize` | 150 |
| `minimumIdle` | 15 |
| `idleTimeout` | 0 (client), 60000 (standalone) |
| `keepaliveTime` | 0 (client), 30000 (standalone) |
| `connectionTimeout` | 30000 (Hikari default) |
| `leakDetectionThreshold` | 0 (off); a value below 2000 is refused by Hikari. Set it to find connections that are not closed |
| `maxLifetime` | 1800000 (Hikari default) |

Oracle only (not run here, only compiled):

| Property | Default | Meaning |
|---|---|---|
| `org.adempiere.db.oracle.defaultRowPrefetch` | 100 | Rows per round trip. The driver default of 10 is low for a report that reads thousands of rows; 100 divides the round trips by 10 and keeps about 100 rows per open statement in memory. `0` leaves the driver default. |
| `org.adempiere.db.oracle.implicitStatementCacheSize` | 0 (off) | Size of the ojdbc implicit statement cache (`oracle.jdbc.implicitStatementCacheSize`, an ojdbc6 11.2 property). Off until it is measured on Oracle, because every cached statement keeps an open cursor (keep it below `open_cursors`). |

`ValidateConnectionPool` builds a second pool with `new DB_PostgreSQL()` to prove an override and closes it; the pool of the test JVM is not touched.
Its opt-in scenario (`ADEMPIERE_BENCHMARK=true`) prints, for four print formats with the definition cache off, the statements, the
connection round trips and the time of a cold `getPrintData`.

## Data left in the database

The tests share the transaction `trxName` of `AdempiereTestCase`, rolled back in `afterAll`, so the records they
create (warehouses, productions, orders, discount schemas, prices, ...) never reach the database. What does stay
after every run: `AD_PInstance` rows (and their log) that `ProcessBuilder` creates outside that transaction (one per
`Rpt C_Order` execution) and the document number sequences that were consumed. Nothing else accumulates.

The regression test compares `ORDER BY` keys by what the statement sorts: the display of a lookup column, not its
ID. Two IDs with the same display (for example the cost elements 100 and 1000000, both named `Material`) are a tie,
and neither strategy defines the order inside a tie.

## Writing tests that create data

`PO.save()` returns `false` when the insert fails and, on PostgreSQL, the failed statement aborts the whole
transaction, so every later `save()` fails too. A test that ignores the result only fails far away, for example
with a `null` rule or a reference that resolves to ID 0. Use `saveEx()` for fixtures so the first error is the one
you see, and create the master data your database may lack (the demo GardenWorld has no WM area or section types).

## Notes

* sbt-dotenv prints an `InaccessibleObjectException` stack trace when it loads `.env` on JDK 17. It is harmless.
* Tests that depend on the data of your database (for example the print formats, the warehouse rules or the
  process definitions) can fail on a database that is not the standard GardenWorld.

## Report bind parameters and plan reuse (`ValidateReportBindParameters`)

`DataEngine.getPrintDataInfo` takes the where clause of the report from `MQuery.getBoundWhereClause(true)`: the SQL holds
`?` and `PrintData.getSQLParameters()` holds the values in placeholder order; `loadPrintData` binds them with
`DB.setQueryParameters` on both read paths (pool `AdempiereSRDS` and `DB.prepareStatement`). The `T_Report*` and
`AD_PInstance*` branches keep the literal text, because they cut the text by `AD_PInstance_ID`.

* **Plan reuse.** `SysConfig` `REPORT_PLAN_CACHE` (default `Y`) and `DataEngine.setPlanCache(Boolean)` (public override,
  `null` = SysConfig). With bind parameters and `Y`, the statement keeps the pgjdbc `prepareThreshold` (5): from the
  5th execution on the same connection the server prepares it and PostgreSQL chooses between a generic and a specific
  plan (`plan_cache_mode=auto`). With `N`, or without parameters, `prepareThreshold=0` as before. Set it to `N` if a
  generic plan turns out worse in production. Oracle: the setting is PostgreSQL-only, the Oracle driver keeps its own
  statement cache.
* **One binder.** Every statement built from `MQuery.getBoundWhereClause` binds its values with
  `DB.setQueryParameters` (`DB.setQueryParameter` for one value). On PostgreSQL a String goes untyped
  (`Types.OTHER`), like the quoted literal it replaces, so a text such as `'118'` still compares with a numeric
  column; `DB.setParameter` would send it as varchar and fail with `numeric = character varying`. Any other value
  behaves as in `DB.setParameter`, which is unchanged for the rest of ADempiere. `GridTable` uses the same helper.

## Report read tuning (`ValidateReportReadTuning`)

`DataEngine.loadPrintData` reads the data of a report with two PostgreSQL-oriented settings:

* **One-off statement.** A report SQL without bind parameters carries the literals of its filters and runs once, so its
  statement gets `prepareThreshold=0` (`PGStatement.setPrepareThreshold(0)` through `unwrap`, on that statement only). pgjdbc would
  otherwise server-side prepare it at the 5th execution on a pooled connection and reuse a plan built for a small volume
  on a large one. Dictionary statements keep the driver default. A report statement *with* bind parameters keeps the
  driver default too while `REPORT_PLAN_CACHE` is `Y` (see the next section).
* **Fetch size.** `SysConfig` `REPORT_FETCH_SIZE` (default `1000`, `0` = read the whole result at once, as before) and
  `DataEngine.setFetchSize(Integer)` (public override, `null` = SysConfig). pgjdbc only streams with `autoCommit=false`:
  inside a `Trx` the fetch size is set on the statement; without a `Trx` (and on PostgreSQL) the engine reads inside a
  short transaction of its own (READ COMMITTED, like every `Trx`) that is always rolled back and closed in `finally`.
  Other databases only get `setFetchSize`. Data, order and row count do not change.

`ValidateReportReadTuning` (6 scenarios) checks:

* after 7 runs of the same report through one transaction, `pg_prepared_statements` of that connection holds no
  statement with the `/* AD_PrintFormat_ID=<id> */` tag (a control statement of an ordinary query is prepared, so the
  detector works); PostgreSQL only;
* the fetch size comes from `REPORT_FETCH_SIZE` or the override (negative values mean 0);
* SQL, columns, cells and order are identical for fetch size 0, 1000 and 7, with and without a transaction, on four
  formats;
* a read without a transaction leaves no registered `Trx` behind, also when the report SQL fails;
* `Fact_Acct` multiplied by 20 inside a rolled back transaction (2,880 rows) returns every row for fetch size 0, 1000
  and 100.

The opt-in benchmark `ValidateReportLookupJoinPerformance` has a third feature, `Report read`, that compares fetch size
0 with 1000 (interleaved, median of 5) at the natural volume (no transaction) and at the scaled volume (inside the
rolled back transaction): peak heap above the heap in use before the read (sampled while `getPrintData` runs, garbage
included, so indicative and noisy), bytes allocated by the thread and end-to-end time. The table is appended to the same
markdown report. The heap figure moves with garbage collection timing: repeat the run before drawing conclusions.
