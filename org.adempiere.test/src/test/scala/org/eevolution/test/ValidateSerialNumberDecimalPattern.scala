/** ****************************************************************************
  * Product: ADempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program; if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * ****************************************************************************/

package org.eevolution.test

import java.text.{DecimalFormat, DecimalFormatSymbols}
import java.util.Locale

import org.adempiere.core.domains.models.X_M_SerNoCtl
import org.compiere.model.MSerNoCtl
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/** Prefix/suffix used by every serial control built by this spec. */
private object SerialFixture {
  val Prefix = "SN4447"
  val Suffix = "-X"
}

/**
  * Serial control able to store a pattern before `X_M_SerNoCtl` is
  * regenerated: `PO.set_Value` is `protected final`, so the value can only be
  * written from inside the model hierarchy. Scenario S8 drops this helper once
  * the generated `setDecimalPattern` exists.
  *
  * Declared at file scope on purpose: nesting it inside the spec clashes with
  * the outer pointer Scala 3 derives for the Java superclass.
  */
private class PatternControl(ctx: java.util.Properties, trxName: String, next: Int)
  extends MSerNoCtl(ctx, 0, trxName) {
  setName("PR4447 Serial " + System.nanoTime())
  setPrefix(SerialFixture.Prefix)
  setSuffix(SerialFixture.Suffix)
  setStartNo(next)
  setCurrentNext(next)
  setIncrementNo(1)

  def withPattern(pattern: String): PatternControl = {
    set_Value("DecimalPattern", pattern)
    this
  }
}

/**
  * Regression spec for issue #4446 / pull request #4447: optional
  * `DecimalPattern` on Serial Number Control (`M_SerNoCtl`).
  *
  * The scenarios below are the acceptance contract for the feature, not only
  * the happy path. They are written so the whole suite can be run before and
  * after the implementation work:
  *
  *  - S1..S5 are the behaviour the pull request already claims and must stay
  *    green (they pin the issue's own example, the legacy output, uniqueness
  *    across the pattern width, and the dictionary round-trip, so a future
  *    refactor cannot silently change them).
  *  - S6, S7, S8, S9 are known RED at the time of writing: the pattern is
  *    consumed by `new DecimalFormat(pattern)` with no validation, no fixed
  *    decimal-symbol set and no generated accessor. They are the specification
  *    the implementation has to satisfy; each one names the fix it expects.
  *
  * Verified on this machine (JDK 11 Azul for the run, JDK 25 CLDR probes):
  *   `new DecimalFormat("0000")` under an `ar-EG` FORMAT locale yields
  *   `\u0660\u0660\u0662\u0663` (Arabic-Indic digits), `fa-IR` yields
  *   `\u06F0\u06F0\u06F2\u06F3` and `bn-BD` yields `\u09E6\u09E6\u09E8\u09E2`;
  *   `0.0E0` formats 100 and 101 both as `1.0E2`; `0.#E0` both as `1E2`;
  *   `0E0` formats 10 and 11 both as `1E1`; `0.0.0` throws
  *   `IllegalArgumentException: Multiple decimal separators in pattern`.
  *
  * First run against the local GardenWorld instance (migration not applied
  * yet): S2, S3 and S5 green; S1 and S4 red on `set_Value: Column not found
  * - DecimalPattern`; S6, S7, S8 and S9 red as designed. After the migration
  *  is applied S1 and S4 must turn green with no code change; S6..S9 stay red
  *  until `createSerNo` validates the pattern, pins ASCII `DecimalFormatSymbols`
  *  and `X_M_SerNoCtl` is regenerated.
  *
  * Run it against a migrated instance:
  *   cd org.adempiere.test
  *   sbt "testOnly org.eevolution.test.ValidateSerialNumberDecimalPattern"
  *
  * NOTE: the pattern is read through `get_ValueAsString("DecimalPattern")`
  * here on purpose, so this spec keeps compiling before `X_M_SerNoCtl` is
  * regenerated. Scenario S9 is the marker telling the author to switch both
  * the model and this spec to the generated accessor.
  */
class ValidateSerialNumberDecimalPattern extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen {

  /** ASCII digits, no grouping: the only shape a serial number may take. */
  private val FixedWidth = "0000"
  private val Prefix = SerialFixture.Prefix
  private val Suffix = SerialFixture.Suffix

  /**
    * Create an unsaved serial number control.
    *
    * @param pattern value stored in `DecimalPattern`; `None` leaves it null
    */
  private def control(pattern: Option[String], next: Int): MSerNoCtl = {
    val ctl = new PatternControl(Context, trxName, next)
    pattern.foreach(ctl.withPattern)
    ctl
  }

  /** Persist a control and assert the save itself succeeded. */
  private def saveControl(ctl: MSerNoCtl): MSerNoCtl = {
    assert(ctl.save(), "the serial number control must be savable with a valid pattern")
    ctl
  }

  Feature("Serial number decimal pattern") {
    info("Issue #4446: M_SerNoCtl.DecimalPattern formats the numeric part of a serial number")

    Scenario("S1. A fixed width pattern pads the next number, as the issue requests") {
      Given("a serial control with prefix, suffix, next number 23 and pattern 0000")
      val ctl = saveControl(control(Some(FixedWidth), 23))

      When("createSerNo is called")
      val serial = ctl.createSerNo()

      Then("the numeric part is padded to the pattern width")
      assert(serial == s"$Prefix" + "0023" + Suffix, s"expected ${Prefix}0023$Suffix but got $serial")

      And("the next number still advances by IncrementNo")
      assert(ctl.getCurrentNext == 24, s"CurrentNext must advance, got ${ctl.getCurrentNext}")
    }

    Scenario("S2. An empty pattern keeps the legacy output byte for byte") {
      Given("a serial control with no pattern at all")
      val ctl = saveControl(control(None, 23))

      When("createSerNo is called")
      val serial = ctl.createSerNo()

      Then("the number is appended unformatted, exactly as before the feature")
      assert(serial == s"$Prefix" + "23" + Suffix, s"expected ${Prefix}23$Suffix but got $serial")
    }

    Scenario("S3. A blank pattern behaves like no pattern") {
      Given("a serial control whose pattern is the empty string")
      val ctl = saveControl(control(Some(""), 23))

      When("createSerNo is called")
      val serial = ctl.createSerNo()

      Then("no DecimalFormat is applied")
      assert(serial == s"$Prefix" + "23" + Suffix, s"expected ${Prefix}23$Suffix but got $serial")
    }

    Scenario("S4. The pattern is stored and read back from the database") {
      Given("a serial control saved with pattern 0000")
      val ctl = saveControl(control(Some(FixedWidth), 23))

      When("the record is reloaded from the database")
      val reloaded = new MSerNoCtl(Context, ctl.getM_SerNoCtl_ID, trxName)

      Then("the physical column exists and holds the pattern")
      assert(reloaded.get_ValueAsString("DecimalPattern") == FixedWidth,
        s"DecimalPattern must round-trip, got '${reloaded.get_ValueAsString("DecimalPattern")}'")
    }

    Scenario("S5. Consecutive serials are always distinct across the pattern width") {
      Given("a serial control with pattern 0000 starting at 9998")
      val ctl = saveControl(control(Some(FixedWidth), 9998))

      When("three serials are generated")
      val serials = (1 to 3).map(_ => ctl.createSerNo())

      Then("every serial is unique")
      assert(serials.distinct.size == 3, s"serials must be distinct, got $serials")
      assert(serials == Seq(s"$Prefix" + "9998" + Suffix,
        s"$Prefix" + "9999" + Suffix, s"$Prefix" + "10000" + Suffix),
        s"unexpected serial sequence $serials")
    }

    Scenario("S6. RED: an invalid pattern must be rejected when the control is saved") {
      Given("a serial control whose pattern cannot be parsed by DecimalFormat")
      // `0.0.0` is rejected by the JDK with
      // "Multiple decimal separators in pattern \"0.0.0\"".
      val ctl = control(Some("0.0.0"), 23)

      When("the control is saved")
      val saved = ctl.save()

      Then("the save must fail instead of storing a pattern that breaks generation")
      // Expected RED: today the record is stored and the failure only shows
      // up later, inside createSerNo(), in the middle of the document that
      // first needs a serial number.
      assert(!saved,
        "saving an unparseable DecimalPattern must be refused with a user visible error")

      And("no serial number control may ever blow up while generating a serial")
      // Guard kept separate so the failure message points at the contract
      // that matters: generation must never throw on stored configuration.
      val safe = saveControl(control(Some(FixedWidth), 23))
      assert(safe.createSerNo().startsWith(Prefix))
    }

    Scenario("S7. RED: a pattern that collapses distinct numbers must be rejected") {
      Given("a serial control whose pattern rounds different integers to one string")
      // Verified on this JDK: 0.0E0 formats 100 and 101 both as "1.0E2",
      // 0.#E0 both as "1E2", 0E0 formats 10 and 11 both as "1E1".
      Seq("0.0E0", "0.#E0", "0E0").foreach { collapsing =>
        And(s"the pattern '$collapsing' collapses consecutive numbers")
        val df = new DecimalFormat(collapsing, new DecimalFormatSymbols(Locale.US))
        assert(df.format(100) == df.format(101),
          s"$collapsing is expected to collapse 100 and 101")

        When(s"a control is saved with the pattern '$collapsing'")
        val ctl = control(Some(collapsing), 100)
        val saved = ctl.save()

        Then("the save must be refused, because CurrentNext would repeat serials")
        // Expected RED: today it saves, and createSerNo() hands out the same
        // serial for 100 and 101 while CurrentNext keeps advancing.
        assert(!saved,
          s"pattern '$collapsing' maps distinct numbers to the same serial and must be rejected at save time")
      }
    }

    Scenario("S8. RED: digits stay ASCII whatever the server FORMAT locale is") {
      Given("a serial control with pattern 0000 saved under the test locale")
      val ctl = saveControl(control(Some(FixedWidth), 23))

      When("the JVM default FORMAT locale uses non-ASCII digits")
      val original = Locale.getDefault(Locale.Category.FORMAT)
      try {
        Locale.setDefault(Locale.Category.FORMAT, Locale.forLanguageTag("ar-EG"))
        val probe = new DecimalFormat(FixedWidth).format(23)
        if (probe.forall(_.isDigit) && probe.head < 0x80)
          cancel(s"the JDK formats $probe with ASCII digits under ar-EG; the locale cannot be exercised here")

        Then("the generated serial still carries ASCII digits")
        val serial = ctl.createSerNo()
        // Expected RED: `new DecimalFormat(pattern)` inherits the default
        // FORMAT locale, so this yields "SN4447٠٠٢٣-X" on a CLDR JDK.
        assert(serial == s"$Prefix" + "0023" + Suffix,
          s"serial identifiers must be locale independent, got $serial")
      } finally {
        Locale.setDefault(Locale.Category.FORMAT, original)
      }
    }

    Scenario("S9. RED: the pattern is reached through the generated accessor") {
      Given("the centrally maintained entity X_M_SerNoCtl")
      // Reflection keeps this spec compiling before the entity is regenerated.
      val getter = classOf[X_M_SerNoCtl].getMethods.find(_.getName == "getDecimalPattern")
      val setter = classOf[X_M_SerNoCtl].getMethods.find(_.getName == "setDecimalPattern")
      val constant = classOf[X_M_SerNoCtl].getFields.find(_.getName == "COLUMNNAME_DecimalPattern")

      Then("the entity exposes the column like every other column does")
      // Expected RED: the pull request adds the dictionary column but never
      // regenerates X_M_SerNoCtl, so MSerNoCtl reads it with a magic string.
      assert(getter.isDefined, "X_M_SerNoCtl must expose getDecimalPattern()")
      assert(setter.isDefined, "X_M_SerNoCtl must expose setDecimalPattern(String)")
      assert(constant.isDefined, "X_M_SerNoCtl must expose COLUMNNAME_DecimalPattern")

      And("the accessor returns the stored pattern")
      val ctl = saveControl(control(Some(FixedWidth), 23))
      assert(getter.map(_.invoke(ctl)).contains(FixedWidth))
    }
  }
}
