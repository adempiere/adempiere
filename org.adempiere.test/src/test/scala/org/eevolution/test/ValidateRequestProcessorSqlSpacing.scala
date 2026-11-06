package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Guards the inline ECR filter SQL of RequestProcessor.processECR() so the
  * subquery predicates keep a trailing space before the closing quote and the
  * next fragment starts a new clause ("...R_RequestType_ID " + "AND rt.
  * IsAutoChangeRequest='Y') " + "AND EXISTS ("), instead of producing fused
  * tokens such as "='Y')AND EXISTS (" that PostgreSQL 15+ rejects with a
  * syntax error.
  *
  * See https://github.com/adempiere/adempiere/issues/4211
  */
class ValidateRequestProcessorSqlSpacing extends AnyFeatureSpec {

  private val source = "serverRoot/src/main/server/org/compiere/server/RequestProcessor.java"

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

  Feature("RequestProcessor ECR SQL (#4211) keeps the subquery boundaries separated") {
    Scenario(s"$source keeps a space after the R_RequestType predicate before its AND clause") {
      val text = Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

      // The subquery predicate must end with a space before the closing
      // quote, so the generated SQL reads "...= R_Request.R_RequestType_ID
      // AND rt.IsAutoChangeRequest='Y')" and never "..._IDAND rt.
      // IsAutoChangeRequest".
      val requestTypePredicate = Pattern.compile(
        "(?s)WHERE rt\\.R_RequestType_ID = R_Request\\.R_RequestType_ID(\\s*)\"[^\"]{0,120}?\\+\\s*\"AND rt\\.IsAutoChangeRequest='Y'\\)"
      )
      val matcher = requestTypePredicate.matcher(text)
      assert(matcher.find(),
        s"$source: expected the R_RequestType predicate followed by its AND rt.IsAutoChangeRequest clause")
      assert(matcher.group(1).endsWith(" "),
        s"$source: the R_RequestType predicate must keep a trailing space before the following AND clause")
    }

    Scenario(s"$source keeps a space after the IsAutoChangeRequest predicate before AND EXISTS") {
      val text = Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

      // The subquery predicate must end with a space after the closing
      // parenthesis, so the generated SQL reads "...IsAutoChangeRequest='Y')
      // AND EXISTS (SELECT 1 FROM R_Group ...)" and never "='Y')AND EXISTS (".
      val showPredicate = Pattern.compile(
        "(?s)AND rt\\.IsAutoChangeRequest='Y'\\)(\\s*)\"[^\"]{0,120}?\\+\\s*\"AND EXISTS \\("
      )
      val matcher = showPredicate.matcher(text)
      assert(matcher.find(),
        s"$source: expected the IsAutoChangeRequest predicate followed by its AND EXISTS clause")
      assert(matcher.group(1).endsWith(" "),
        s"$source: the IsAutoChangeRequest predicate must keep a trailing space before the following AND EXISTS clause")
    }

    Scenario(s"$source keeps a space after the R_Group predicate before its AND clause") {
      val text = Files.readString(repoRoot.resolve(source), StandardCharsets.UTF_8)

      // The R_Group predicate must end with a space before the closing quote,
      // so the generated SQL reads "...= R_Request.R_Group_ID AND (g.M_BOM_ID
      // IS NOT NULL ...)" and never "..._IDAND (g.M_BOM_ID".
      val groupPredicate = Pattern.compile(
        "(?s)WHERE g\\.R_Group_ID = R_Request\\.R_Group_ID(\\s*)\"[^\"]{0,120}?\\+\\s*\"AND \\(g\\.M_BOM_ID IS NOT NULL"
      )
      val matcher = groupPredicate.matcher(text)
      assert(matcher.find(),
        s"$source: expected the R_Group predicate followed by its AND (g.M_BOM_ID clause")
      assert(matcher.group(1).endsWith(" "),
        s"$source: the R_Group predicate must keep a trailing space before the following AND clause")
    }
  }
}
