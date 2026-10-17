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

Also: `-Dadempiere.includeBrokenTests=true` compiles the Scala tests that are skipped by default (below).

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

## Tests skipped by default

These Scala tests were written against classes that do not exist on this branch (another ADempiere branch:
`org.compiere.util.PO`, `X_M_DiscountSchema` in `org.compiere.model`, `PO.query`, `ProcessBuilder` changes, ...).
They do not compile, so `build.sbt` excludes them (`brokenScalaTests`):
`CreateSalesOrder`, `ValidateMProductEnvAttributeSetInstance`, `ValidatePriceListAndDiscountFunctionality`,
`ValidateProductionLineReversalReference`. Fix them and remove them from that list.

## Notes

* sbt-dotenv prints an `InaccessibleObjectException` stack trace when it loads `.env` on JDK 17. It is harmless.
* Tests that depend on the data of your database (for example the print formats, the warehouse rules or the
  process definitions) can fail on a database that is not the standard GardenWorld.
