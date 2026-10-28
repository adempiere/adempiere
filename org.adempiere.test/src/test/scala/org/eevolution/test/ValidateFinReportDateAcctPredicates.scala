package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression guard for #4213: financial reports ran slow when a date range
  * was used because the generated SQL wrapped DateAcct in TRUNC(...)
  * (TRUNC(DateAcct, 'DD')). A function around the column prevents PostgreSQL
  * from using the Fact_Acct.DateAcct indexes, forcing sequential scans.
  *
  * The fix rewrites the predicates to compare DateAcct directly with the
  * range bounds (e.g. "DateAcct BETWEEN ... AND ..."), which keeps the
  * predicates index-friendly. This guard pins that no report SQL builder
  * re-introduces a TRUNC() wrapper around DateAcct.
  */
class ValidateFinReportDateAcctPredicates extends AnyFeatureSpec {

  private val sources =
    List(
      "base/src/org/compiere/report/FinReport.java",
      "base/src/org/compiere/report/FinReportPeriod.java",
      "base/src/org/compiere/report/FinStatement.java",
      "base/src/org/compiere/report/TrialBalance.java"
    )

  private val anchor = "base/src/org/compiere/report/FinReport.java"

  // The forked test JVM may start from any working directory (repo root or
  // subproject), so resolve the sources relative to the repository root
  // instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(anchor)))
        return dir
      dir = dir.getParent
    }
    fail(s"repository root containing $anchor was not found")
  }

  private def read(source: String): String =
    Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

  // TRUNC() applied to DateAcct, whether in one literal (TRUNC(DateAcct),
  // TRUNC(fb.DateAcct, 'DD')) or across string concatenation
  // (TRUNC(" + alias + ".DateAcct, 'DD')). The window covers the
  // concatenation glue between TRUNC( and the column.
  private val truncDateAcct =
    Pattern.compile("TRUNC\\s*\\([\"' \\w.+,\\-]{0,60}?DateAcct", Pattern.CASE_INSENSITIVE)

  Feature("Financial report SQL keeps DateAcct predicates index-friendly") {

    for (source <- sources) {
      Scenario(s"$source does not wrap DateAcct in TRUNC()") {
        val text = read(source)
        assert(!truncDateAcct.matcher(text).find(),
          s"$source must not build SQL predicates that wrap DateAcct in TRUNC(); " +
          s"that defeats the Fact_Acct.DateAcct indexes (#4213)")
      }
    }

    Scenario("FinReport filters DateAcct directly in the period predicate") {
      val text = read(sources(0))
      assert(text.contains("\" WHERE DateAcct \""),
        s"${sources(0)}: the period predicate must start with a direct DateAcct comparison")
      assert(text.contains("OR fb.DateAcct \" + yearWhere"),
        s"${sources(0)}: the total/year predicate must compare fb.DateAcct directly")
    }

    Scenario("FinReportPeriod.getNaturalWhere compares the alias DateAcct directly") {
      val text = read(sources(1))
      assert(text.contains("\" OR \" + alias + \".DateAcct \""),
        s"${sources(1)}: getNaturalWhere must compare <alias>.DateAcct directly, " +
        s"without TRUNC( + alias + \".DateAcct, 'DD')")
    }

    Scenario("FinStatement balances and detail lines use direct DateAcct ranges") {
      val text = read(sources(2))
      assert(text.contains("DateAcct < "),
        s"${sources(2)}: the balance line must filter DateAcct directly")
      assert(text.contains("DateAcct BETWEEN"),
        s"${sources(2)}: the detail lines must use a direct DateAcct BETWEEN range")
    }

    Scenario("TrialBalance binds the DateAcct range as parameters") {
      val text = read(sources(3))
      assert(text.contains("AND DateAcct >= ?"),
        s"${sources(3)}: the trial balance lower bound must be a bound parameter")
      assert(text.contains("AND DateAcct <= ?"),
        s"${sources(3)}: the trial balance upper bound must be a bound parameter")
      assert(!truncDateAcct.matcher(text).find(),
        s"${sources(3)}: the trial balance must not wrap DateAcct in TRUNC()")
      // The bound dates must be normalised to the day. The SQL this replaced rendered
      // DB.TO_DATE(p_DateAcct_From, true), a day-only literal, so binding the raw
      // parameter keeps its time component and silently drops facts dated earlier on
      // the start day.
      assert(text.contains("parameters.add(TimeUtil.getDay(p_DateAcct_From))"),
        s"${sources(3)}: the bound lower date must be normalised to the day, " +
        "otherwise a non-midnight start timestamp excludes valid same-day facts")
      assert(text.contains("parameters.add(TimeUtil.getDay(p_DateAcct_To))"),
        s"${sources(3)}: the bound upper date must be normalised to the day")
      assert(text.contains("import org.compiere.util.TimeUtil;"),
        s"${sources(3)}: TimeUtil must be imported for the day normalisation")
    }
  }
}
