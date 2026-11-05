package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Guards the inline window-access SQL of MRole.updateAccessRecords() so the
  * first-tab subquery predicate keeps a space before the closing quote and
  * stays separated from the following AND clause ("...AD_Window_ID) AND tt.
  * AccessLevel" instead of "...AD_Window_ID)AND tt.AccessLevel"). The
  * no-space form is valid PostgreSQL; the spacing is a defensive readability
  * improvement that this guard pins.
  */
class ValidateMRoleAccessRecordsSqlSpacing extends AnyFeatureSpec {

  private val source = "base/src/org/compiere/model/MRole.java"

  // The forked test JVM may start from any working directory (repo root or
  // subproject), so resolve the source relative to the repository root instead
  // of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve(source)))
        return dir
      dir = dir.getParent
    }
    fail(s"repository root containing $source was not found")
  }

  Feature("MRole access records SQL separates the clauses") {
    Scenario(s"$source keeps a space between the first-tab subquery and the following AND") {
      val text = Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

      // The subquery predicate must end with a space before the closing
      // quote, so the generated SQL reads "...AD_Window_ID) AND tt.
      // AccessLevel IN (...)" and never "...AD_Window_ID)AND tt.AccessLevel".
      val subqueryThenAnd = Pattern.compile(
        "(?s)WHERE xt\\.AD_Window_ID=w\\.AD_Window_ID\\)(\\s*)\"[^\"]{0,120}?\\+\\s*\"AND tt\\.AccessLevel IN"
      )
      val matcher = subqueryThenAnd.matcher(text)
      assert(matcher.find(),
        s"$source: expected the first-tab subquery predicate followed by the AND tt.AccessLevel clause")
      assert(matcher.group(1).endsWith(" "),
        s"$source: the subquery predicate must keep a trailing space before the following AND clause")
    }
  }
}
