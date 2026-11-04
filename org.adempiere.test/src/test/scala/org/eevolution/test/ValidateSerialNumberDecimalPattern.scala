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
import java.util.{Locale, Properties}

import org.adempiere.core.domains.models.{X_M_SerNoCtl, X_AD_Sequence}
import org.compiere.model.{MSerNoCtl, MSequence}
import org.compiere.util.{DB, DecimalPatternUtil, Env}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/** Prefix/suffix used by every serial control built by this spec. */
private object SerialFixture {
  val Prefix = "SN4447"
  val Suffix = "-X"
}

/**
  * Serial control used by this spec.
  *
  * Declared at file scope on purpose: nesting it inside the spec clashes with
  * the outer pointer Scala 3 derives for the Java superclass.
  */
private class PatternControl(ctx: Properties, trxName: String, next: Int)
  extends MSerNoCtl(ctx, 0, trxName) {
  setName("PR4447 Serial " + System.nanoTime())
  setPrefix(SerialFixture.Prefix)
  setSuffix(SerialFixture.Suffix)
  setStartNo(next)
  setCurrentNext(next)
  setIncrementNo(1)

  def withPattern(pattern: String): PatternControl = {
    setDecimalPattern(pattern)
    this
  }
}

/**
  * Regression spec for issue #4446 / pull request #4447: optional
  * `DecimalPattern` on Serial Number Control (`M_SerNoCtl`).
  *
  * The scenarios below are the acceptance contract for the feature, not only
  * the happy path:
  *
  *  - S1..S5 pin the behaviour the pull request claims and must stay green
  *    (the issue's own example, the legacy output, uniqueness across the
  *    pattern width, and the dictionary round-trip).
  *  - S6, S7 pin that an unusable pattern is refused when the record is
  *    saved, instead of failing later inside `createSerNo`.
  *  - S8 pins that the number symbols come from the locale of the user's
  *    context language, never from the JVM default locale.
  *  - S9 pins the generated accessors on `X_M_SerNoCtl`.
  *  - S10, S11 pin the shape rule itself: what is rejected because it would
  *    lose or alter the counter value, and what must stay accepted.
  *  - S12 pins the same save-time rule on `AD_Sequence.DecimalPattern`, the
  *    other consumer of the very same dictionary element.
  *
  * Verified on this machine (Azul JDK 17, CLDR locales):
  *   `0000` renders 23 as `0023` under `en_US`/`es_MX`/`de_DE`, as `٠٠٢٣`
  *   under `ar_EG` and as `۰۰۲۳` under `fa_IR`;
  *   `0E0` formats 10 and 11 both as `1E1`, `0.0E0` 100 and 101 both as
  *   `1.0E2`, `0.00E0` 1000 and 1001 both as `1.00E3` - the collision point
  *   moves with the mantissa width, which is why collapsing patterns are
  *   rejected by pattern shape and not by probing consecutive numbers;
  *   `SN.0000` renders 23 as `SN23.0000` (the dot is the fraction separator,
  *   not a literal); `%0` renders 23 as `%2300`; `0,#` throws
  *   `IllegalArgumentException: Malformed pattern "0,#"`.
  *
  * Run it against a migrated instance:
  *   cd org.adempiere.test
  *   sbt "testOnly org.eevolution.test.ValidateSerialNumberDecimalPattern"
  */
class ValidateSerialNumberDecimalPattern extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen {

  /** ASCII digits, no grouping: the shape the issue asks for. */
  private val FixedWidth = "0000"
  private val Prefix = SerialFixture.Prefix
  private val Suffix = SerialFixture.Suffix

  /** Context language of the running session, restored after every scenario. */
  private var originalLanguage: String = _

  override def beforeAll(): Unit = {
    super.beforeAll()
    originalLanguage = Env.getAD_Language(Env.getCtx)
  }

  override def afterAll(): Unit = {
    Env.setContext(Env.getCtx, Env.LANGUAGE, originalLanguage)
    // Leave the instance as the suite found it: every fixture row this spec
    // writes carries the PR4447 prefix, including the sequence it seeds with an
    // invalid legacy pattern in S12.
    DB.executeUpdate("DELETE FROM AD_Sequence WHERE Name LIKE 'PR4447%'", true, trxName)
    DB.executeUpdate("DELETE FROM M_SerNoCtl WHERE Name LIKE 'PR4447%'", true, trxName)
    super.afterAll()
  }

  /**
    * Run a block with the context language forced to `language`, whatever the
    * JVM default locale happens to be.
    */
  private def withContextLanguage[A](language: String)(body: => A): A = {
    Env.setContext(Env.getCtx, Env.LANGUAGE, language)
    try body
    finally Env.setContext(Env.getCtx, Env.LANGUAGE, originalLanguage)
  }

  /** Run a block with the JVM default FORMAT locale forced, then restore it. */
  private def withJvmFormatLocale[A](locale: Locale)(body: => A): A = {
    val original = Locale.getDefault(Locale.Category.FORMAT)
    Locale.setDefault(Locale.Category.FORMAT, locale)
    try body
    finally Locale.setDefault(Locale.Category.FORMAT, original)
  }

  /**
    * Render `value` padded to `width` using the digits of `locale`, derived
    * from `DecimalFormatSymbols.getZeroDigit` instead of hard-coded literals,
    * so the expectation does not depend on one CLDR version.
    */
  private def localizedDigits(value: Int, width: Int, locale: Locale): String = {
    val zero = new DecimalFormatSymbols(locale).getZeroDigit
    val padded = value.toString.reverse.padTo(width, '0').reverse
    padded.map(c => (zero + (c - 0x30)).toChar).mkString
  }

  /**
    * Create an unsaved serial number control.
    *
    * @param pattern value stored in `DecimalPattern`; `None` leaves it null
    */
  private def control(pattern: Option[String], next: Int): MSerNoCtl = {
    val ctl = new PatternControl(Env.getCtx, trxName, next)
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
      val reloaded = new MSerNoCtl(Env.getCtx, ctl.getM_SerNoCtl_ID, trxName)

      Then("the physical column exists and holds the pattern")
      assert(reloaded.getDecimalPattern == FixedWidth,
        s"DecimalPattern must round-trip, got '${reloaded.getDecimalPattern}'")
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

    Scenario("S6. An invalid pattern is rejected when the control is saved") {
      Given("a serial control whose pattern cannot be parsed by DecimalFormat")
      // `0,#` is rejected by the JDK with "Malformed pattern "0,#"".
      val ctl = control(Some("0,#"), 23)

      When("the control is saved")
      val saved = ctl.save()

      Then("the save must fail instead of storing a pattern that breaks generation")
      assert(!saved,
        "saving an unparseable DecimalPattern must be refused with a user visible error")

      And("the row is never written")
      // Reading the in-memory buffer would pass even if the row had been
      // written, so prove it against the database, scoped to this control's own
      // name (other scenarios in this suite do save valid PR4447 controls).
      val name = ctl.getName
      val rows = DB.getSQLValue(null,
        "SELECT COUNT(*) FROM M_SerNoCtl WHERE Name = ?", name)
      assert(rows == 0, s"a rejected pattern must not write a row for '$name', found $rows")

      And("a control with a valid pattern still generates")
      val safe = saveControl(control(Some(FixedWidth), 23))
      assert(safe.createSerNo().startsWith(Prefix))
    }

    Scenario("S7. A pattern that collapses distinct numbers is rejected at save time") {
      Given("patterns that round different integers to the same text")
      // Each pair is (pattern, first number that collides with its successor),
      // verified on this JDK: 0E0 -> 10/11 both "1E1"; 0.0E0 -> 100/101 both
      // "1.0E2"; 0.00E0 -> 1000/1001 both "1.00E3"; 0.#E0 -> 100/101 both
      // "1E2". The collision point moves with the mantissa width, which is why
      // no fixed probe of consecutive numbers can catch them all and the
      // implementation has to reject the shape.
      val collapsing = Seq("0E0" -> 10, "0.0E0" -> 100, "0.00E0" -> 1000, "0.#E0" -> 100)

      collapsing.foreach { case (pattern, base) =>
        And(s"the pattern '$pattern' really collapses at $base/${base + 1}")
        val df = new DecimalFormat(pattern, new DecimalFormatSymbols(Locale.US))
        assert(df.format(base) == df.format(base + 1),
          s"$pattern is expected to collapse $base and ${base + 1}")

        When(s"a control is saved with the pattern '$pattern'")
        val ctl = control(Some(pattern), base)
        val saved = ctl.save()

        Then("the save is refused, because CurrentNext would repeat serials")
        assert(!saved,
          s"pattern '$pattern' maps distinct numbers to the same serial and must be rejected at save time")
      }
    }

    Scenario("S8. The number symbols follow the user's context language, not the JVM locale") {
      Given("a serial control with pattern 0000")
      val ctl = saveControl(control(Some(FixedWidth), 23))

      When("the JVM default FORMAT locale uses Arabic-Indic digits")
      withJvmFormatLocale(new Locale("ar", "EG")) {
        val probe = new DecimalFormat(FixedWidth).format(23)
        if (probe.forall(_.isDigit) && probe.head < 0x80)
          cancel(s"the JDK formats $probe with ASCII digits under ar-EG; the locale cannot be exercised here")

        And("the user's context language is en_US")
        Then("the serial is rendered with ASCII digits, ignoring the JVM default")
        val serial = withContextLanguage("en_US")(ctl.createSerNo())
        assert(serial == s"$Prefix" + "0023" + Suffix,
          s"the JVM default locale must not leak into a serial number, got $serial")
      }

      When("the user's context language is ar_EG")
      withContextLanguage("ar_EG") {
        Then("the serial is rendered with the digits of that language")
        val locale = new Locale("ar", "EG")
        val next = ctl.getCurrentNext
        val serial = ctl.createSerNo()
        val expectedDigits = localizedDigits(next, 4, locale)
        assert(serial == Prefix + expectedDigits + Suffix,
          s"serial must follow the context language ar_EG, got $serial")
        assert(expectedDigits.forall(_ >= 0x80),
          s"probe broken: ar_EG digits are expected to be non-ASCII, got $expectedDigits")
      }

      When("the JVM default FORMAT locale is en_US but the user works in ar_EG")
      withJvmFormatLocale(Locale.US) {
        withContextLanguage("ar_EG") {
          Then("the serial still follows the user, not the server")
          val locale = new Locale("ar", "EG")
          val next = ctl.getCurrentNext
          val serial = ctl.createSerNo()
          val expectedDigits = localizedDigits(next, 4, locale)
          assert(serial == Prefix + expectedDigits + Suffix,
            s"context language must win over the JVM default, got $serial")
          assert(expectedDigits.forall(_ >= 0x80),
            s"probe broken: ar_EG digits are expected to be non-ASCII, got $expectedDigits")
        }
      }
    }

    Scenario("S9. The pattern is reached through the generated accessor") {
      Given("the centrally maintained entity X_M_SerNoCtl")
      val getter = classOf[X_M_SerNoCtl].getMethods.find(_.getName == "getDecimalPattern")
      val setter = classOf[X_M_SerNoCtl].getMethods.find(_.getName == "setDecimalPattern")
      val constant = classOf[X_M_SerNoCtl].getFields.find(_.getName == "COLUMNNAME_DecimalPattern")

      Then("the entity exposes the column like every other column does")
      assert(getter.isDefined, "X_M_SerNoCtl must expose getDecimalPattern()")
      assert(setter.isDefined, "X_M_SerNoCtl must expose setDecimalPattern(String)")
      assert(constant.isDefined, "X_M_SerNoCtl must expose COLUMNNAME_DecimalPattern")

      And("the constant names the column exactly")
      assert(constant.map(_.get(null)).contains("DecimalPattern"))

      And("the accessor returns the stored pattern")
      val ctl = saveControl(control(Some(FixedWidth), 23))
      assert(ctl.getDecimalPattern == FixedWidth)
    }

    Scenario("S10. The shape rule rejects value-losing patterns and keeps the rest") {
      Given("patterns that would alter or lose the counter value")
      // Each one is rejected by shape, not by probing: see the collision
      // points in the header.
      val rejected = Seq("0E0", "0.0E0", "0.00E0", "0.#E0", "#.#E#",
        "SN.0000", "0.00", "%0", "0%", "0,#", "SN", "SN-E0")

      Then("validate names the offending construct for each")
      // validate answers with an AD_Message key; the localized text is
      // resolved by getValidationError, asserted in S11.
      rejected.foreach { pattern =>
        val key = DecimalPatternUtil.validate(Env.getCtx, pattern)
        assert(key != null, s"pattern '$pattern' must be rejected")
        assert(key.nonEmpty, s"rejection of '$pattern' must name the construct")
      }

      And("patterns that keep the counter value stay accepted")
      // Grouping, a negative subpattern, quoted literals (including a quoted
      // dot and a quoted E) and the currency sign do not change the value.
      val accepted = Seq("0000", "#0", "#,##0", "0;(#0)", "'E'0", "SN'.'0000",
        "'0.0'0", "SN-0000", "0", FixedWidth)
      accepted.foreach { pattern =>
        assert(DecimalPatternUtil.validate(Env.getCtx, pattern) == null,
          s"pattern '$pattern' is usable and must not be rejected")
      }

      And("an empty or null pattern is usable, meaning no formatting")
      assert(DecimalPatternUtil.validate(Env.getCtx, null) == null)
      assert(DecimalPatternUtil.validate(Env.getCtx, "") == null)
    }

    Scenario("S11. A rejected pattern is refused in the user's language") {
      Given("the context language is es_MX")
      withContextLanguage("es_MX") {
        When("a collapsing pattern is saved")
        val ctl = control(Some("0E0"), 100)
        val saved = ctl.save()

        Then("the save is refused")
        assert(!saved, "a collapsing pattern must be refused in any context language")

        And("the whole reason reads in Spanish, not just its label")
        // A translated label glued to a hardcoded English reason is the defect
        // this asserts against: inspect the resolved text, not the context key.
        val reason = DecimalPatternUtil.getValidationError(Env.getCtx, "0E0")
        assert(reason != null, "a collapsing pattern must produce a reason")
        assert(reason.contains("notación científica"),
          s"reason must be resolved in es_MX, got: $reason")
        assert(!reason.toLowerCase.contains("scientific notation"),
          s"reason leaks English into a Spanish context, got: $reason")

        And("every rejection reason resolves translated, none falls back to its key")
        Seq("0E0", "SN.0000", "%0", "SN", "0,#").foreach { pattern =>
          val text = DecimalPatternUtil.getValidationError(Env.getCtx, pattern)
          assert(text != null && text.nonEmpty, s"$pattern must carry a reason")
          assert(!text.startsWith("DecimalPattern"),
            s"reason for '$pattern' fell back to the AD_Message key: $text")
        }
      }
    }

    Scenario("S12. AD_Sequence.DecimalPattern obeys the same save-time rule") {
      Given("AD_Sequence carries the same dictionary element")
      assert(classOf[X_AD_Sequence].getMethods.exists(_.getName == "getDecimalPattern"),
        "X_AD_Sequence must expose getDecimalPattern()")

      When("a document sequence is saved with a collapsing pattern")
      val seq = new MSequence(Env.getCtx, Env.getAD_Client_ID(Env.getCtx),
        "PR4447_Collapsing", trxName)
      seq.setPrefix("DOC")
      seq.setDecimalPattern("0E0")
      val savedCollapsing = seq.save()

      Then("the save is refused, because the same element feeds document numbers")
      assert(!savedCollapsing,
        "AD_Sequence.DecimalPattern must obey the same rule as M_SerNoCtl.DecimalPattern")

      When("the same sequence is saved with a usable pattern")
      val ok = new MSequence(Env.getCtx, Env.getAD_Client_ID(Env.getCtx),
        "PR4447_Valid", trxName)
      ok.setPrefix("DOC")
      ok.setDecimalPattern(FixedWidth)

      Then("it saves")
      assert(ok.save(), s"pattern $FixedWidth must stay accepted on AD_Sequence")

      And("an existing sequence keeps a legacy pattern when something else changes")
      // Simulate a row written before the guard existed: put an invalid pattern
      // straight into the table, reload it, and change only Prefix. The guard is
      // keyed on is_ValueChanged, so this save must go through; otherwise every
      // existing sequence with a legacy pattern would become uneditable.
      val legacy = new MSequence(Env.getCtx, Env.getAD_Client_ID(Env.getCtx),
        "PR4447_Legacy", trxName)
      legacy.setPrefix("DOC")
      assert(legacy.save(), "a sequence with no pattern must save")
      val legacyId = legacy.getAD_Sequence_ID
      assert(DB.executeUpdate(
        "UPDATE AD_Sequence SET DecimalPattern = '0E0' WHERE AD_Sequence_ID = ?",
        legacyId, true, trxName) == 1, "seeding the legacy pattern must succeed")

      val reloaded = new MSequence(Env.getCtx, legacyId, trxName)
      assert(reloaded.getDecimalPattern == "0E0",
        s"the legacy pattern must be read back, got ${reloaded.getDecimalPattern}")
      reloaded.setPrefix("DOC2")
      assert(reloaded.save(),
        "changing an unrelated column must not re-validate an untouched legacy pattern")

      And("touching that legacy pattern again is refused")
      reloaded.setDecimalPattern("0.0E0")
      assert(!reloaded.save(),
        "editing a legacy pattern must re-run the guard and refuse a collapsing one")
    }

    Scenario("S13. A rendered serial carries no invisible bidi control character") {
      Given("a pattern with a literal separator, formatted under a right-to-left language")
      // Verified on this JDK: under ar_EG DecimalFormat renders the pattern
      // SN-0000 for 23 as "SN\u061C-\u0660\u0660\u0662\u0663" (8 characters),
      // inserting U+061C ARABIC LETTER MARK before the separator. A serial is
      // matched exactly by lookups, barcodes and integrations, so an invisible
      // character there breaks equality while the value looks identical on
      // screen. DecimalPatternUtil.stripBidiControls removes it.
      val bidi = (c: Char) => c == 0x061C || c == 0x200E || c == 0x200F
        || (c >= 0x202A && c <= 0x202E) || (c >= 0x2066 && c <= 0x2069)
      val locale = new Locale("ar", "EG")
      val raw = new DecimalFormat("SN-0000", new DecimalFormatSymbols(locale)).format(23)
      if (!raw.exists(bidi))
        cancel(s"this JDK does not insert a bidi mark under ar_EG (got $raw); nothing to strip")

      When("the serial is produced through the model")
      val ctl = saveControl(control(Some("SN-0000"), 23))
      val next = ctl.getCurrentNext
      val serial = withContextLanguage("ar_EG")(ctl.createSerNo())

      Then("no bidi control character survives in the serial")
      val offending = serial.filter(bidi)
      assert(offending.isEmpty,
        s"serial carries invisible bidi controls ${offending.map(c => f"U+${c.toInt}%04X").mkString(", ")}: $serial")

      And("the localized digits and the separator are all still there")
      val expected = Prefix + "SN-" + localizedDigits(next, 4, locale) + Suffix
      assert(serial == expected, s"expected $expected but got $serial")

      And("the same control under a left-to-right language renders plain ASCII")
      val asciiNext = ctl.getCurrentNext
      val ascii = withContextLanguage("en_US")(ctl.createSerNo())
      assert(ascii == Prefix + "SN-" + "%04d".format(asciiNext) + Suffix,
        s"ASCII rendering must stay plain, got $ascii")
    }
  }
}
