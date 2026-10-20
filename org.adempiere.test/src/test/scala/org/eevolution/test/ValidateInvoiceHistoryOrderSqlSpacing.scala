package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}
import java.util.regex.Pattern

import org.scalatest.featurespec.AnyFeatureSpec

/** Guards the inline Invoice History SQL against appending ORDER BY to an ID. */
class ValidateInvoiceHistoryOrderSqlSpacing extends AnyFeatureSpec {

  private val sources = Seq(
    "client/src/org/compiere/apps/search/InvoiceHistory.java" -> 4,
    "zkwebui/WEB-INF/src/org/adempiere/webui/panel/InvoiceHistory.java" -> 2
  )

  // The forked test JVM may start from any working directory (repo root or subproject),
  // so resolve the sources relative to the repository root instead of the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve("client/src/org/compiere/apps/search/InvoiceHistory.java")))
        return dir
      dir = dir.getParent
    }
    fail("repository root containing the Invoice History sources was not found")
  }

  Feature("Invoice History SQL separates numeric WHERE values from ORDER BY") {
    sources.foreach { case (path, expectedQueries) =>
      Scenario(s"$path keeps a leading space on every ORDER BY following an inline ID") {
        val source = Files.readString(repoRoot.resolve(path), StandardCharsets.UTF_8)
        val idThenOrder = Pattern.compile(
          "(?s)\\+\\s*m_(?:C_BPartner|M_Product)_ID\\s*\\+\\s*\"([^\"]*ORDER BY[^\"]*)\""
        )
        val matches = idThenOrder.matcher(source)
        var count = 0
        while (matches.find()) {
          count += 1
          assert(matches.group(1).startsWith(" "),
            s"$path: ORDER BY must be separated from the numeric ID")
        }
        assert(count == expectedQueries,
          s"$path: expected $expectedQueries inline-ID ORDER BY queries, found $count")
      }
    }
  }
}
