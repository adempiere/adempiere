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
4. **An `Adempiere.properties` file** that points to that database. The `Connection=CConnection[...]` entry must
   say `type=PostgreSQL` when the database is PostgreSQL: a file with `type=Oracle` connects with the wrong driver.
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
| `ADEMPIERE_PROPERTY_FILE` | `adempiere.propertyFile` | `<repo>/install/build/Adempiere/Adempiere.properties` | `Adempiere.properties` handed to the tests as `-DPropertyFile`. The test run stops with a message when the file does not exist. |
| `ADEMPIERE_JAVA_HOME` | `adempiere.javaHome` | JDK that runs sbt | JDK 17 used to compile the Java and to fork the tests. |
| `ADEMPIERE_TEST_CLASSPATH` | `adempiere.testClasspath` | empty | Extra classpath entries (separator `:` on macOS/Linux) for the test JVM, for example the model validator classes of point 5. |
| `ADEMPIERE_HOME` | `adempiere.home` | `<repo>/target/adempiere-home` | Directory where the test JVM writes the ADempiere logs. |
| `ADEMPIERE_SOURCE_DIR` | `adempiere.sourceDir` | the sbt build root | Source tree to compile; only change it if you build another checkout. |
| `ADEMPIERE_APPS_TYPE` | - | `standalone` | Set by the build for the test JVM; ADempiere refuses to open the connection pool without it. |

## Commands

Run them from the repository root.

```bash
export JAVA_HOME=/path/to/jdk-17
export ADEMPIERE_PROPERTY_FILE=$HOME/Adempiere.properties        # your database

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
| `ValidateReportLookupJoinRegression` | 22 | pass | Report lookups as LEFT JOINs, nested TableDir identifiers chained up to depth 3 (`<Word>_<n>_<m>_<k>`): same rows, NULLs, GROUP BY and ORDER BY as the legacy subselects. |
| `CreateSalesOrder` | 1 | pass | |
| `ValidatePriceListAndDiscountFunctionality` | 10 | pass | |
| `ValidateWmRuleLocatorArgumentOrder` | 1 | pass | Creates its own WM area type and section type: GardenWorld has none. |
| `ValidateReportWithoutCloseTrxConnection` | 2 | pass | Needs `ServerReportCtl`/`ReportExportHandler` to accept the `AD_PInstance.ReportType` list values (`P`, `C`, `H`, `X`, `XX`); the second scenario checks that an unknown type produces no file and no `NullPointerException`. |
| `ValidateReportLookupJoinPerformance` | 5 | cancelled | Opt-in benchmark (see below). Without `ADEMPIERE_BENCHMARK=true` every scenario is cancelled, so `sbt test` stays fast. |

`AdempiereStartup` and `AdempiereTestCase` are test support, not tests.

## Performance benchmark of the report lookups

`ValidateReportLookupJoinPerformance` compares the two report lookup strategies of `DataEngine`: correlated
subselects (`setLookupJoin(false)`, legacy) and `LEFT OUTER JOIN` (`setLookupJoin(true)`, join). It is opt-in: without
`ADEMPIERE_BENCHMARK=true` all its scenarios are cancelled (they show as `canceled`, not as failures).

```bash
export JAVA_HOME=/path/to/jdk-17
export ADEMPIERE_PROPERTY_FILE=$HOME/Adempiere.properties
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
