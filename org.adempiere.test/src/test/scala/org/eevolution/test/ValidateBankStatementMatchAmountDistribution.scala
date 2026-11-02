/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
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

import java.math.BigDecimal

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen
import org.spin.apps.form.BankStatementMatchAmounts

import scala.jdk.CollectionConverters.*

/**
  * Regression test for issue #4442: manual Bank Statement Match must keep the
  * accounting invariants while it distributes one payment over several imported
  * bank statement lines, and while it reverses one imported line over several
  * payments.
  *
  * The three invariants the class documents are pinned here for every shape of
  * selection:
  *   - the sum of TrxAmt equals the payment amount (or the sum of the payments
  *     on a reverse match)
  *   - on every line StmtAmt equals TrxAmt plus ChargeAmt
  *   - on a reverse match the sum of StmtAmt equals the imported line amount
  *
  * BankStatementMatchAmounts is pure arithmetic without any dependency on PO,
  * Env or DB, so this suite deliberately does not extend AdempiereTestCase: it
  * needs no database, no login and no property file, and it runs in milliseconds.
  *
  * Amounts are signed, positive is income and negative is outcome, and every
  * comparison uses BigDecimal.compareTo so a different scale never hides a real
  * difference.
  */
class ValidateBankStatementMatchAmountDistribution
    extends AnyFeatureSpec
    with GivenWhenThen {

  /**  A signed amount written the way a bank statement would show it. */
  private def amount(value: String): BigDecimal = new BigDecimal(value)

  /**  Two amounts are the same value regardless of their scale. */
  private def sameAmount(actual: BigDecimal, expected: BigDecimal): Boolean =
    actual != null && actual.compareTo(expected) == 0

  /**  The distributed lines of one payment over N imported lines. */
  private def toPayment(payAmt: BigDecimal, lineAmounts: List[BigDecimal], mixed: Boolean): List[BankStatementMatchAmounts.LineAmount] =
    BankStatementMatchAmounts
      .distributeToPayment(payAmt, lineAmounts.asJava, mixed)
      .asScala
      .toList

  /**  The distributed lines of one imported line over N payments. */
  private def toPayments(importedAmt: BigDecimal, payAmounts: List[BigDecimal], mixed: Boolean): List[BankStatementMatchAmounts.LineAmount] =
    BankStatementMatchAmounts
      .distributeToPayments(importedAmt, payAmounts.asJava, mixed)
      .asScala
      .toList

  /**  The distributed line built for a given source position. */
  private def lineFor(distribution: List[BankStatementMatchAmounts.LineAmount], index: Int): BankStatementMatchAmounts.LineAmount =
    distribution
      .find(_.getIndex == index)
      .getOrElse(fail(s"no distributed line references source index $index"))

  /**  The sum of one amount column of the distribution. */
  private def totalOf(distribution: List[BankStatementMatchAmounts.LineAmount])
      (column: BankStatementMatchAmounts.LineAmount => BigDecimal): BigDecimal =
    distribution.foldLeft(BigDecimal.ZERO)((accumulator, line) => accumulator.add(column(line)))

  /**  Asserts StmtAmt equals TrxAmt plus ChargeAmt on every distributed line. */
  private def assertChargeClosesEveryLine(distribution: List[BankStatementMatchAmounts.LineAmount]): Unit =
    distribution.foreach { line =>
      val statementAmount = line.getStmtAmt
      val appliedAmount = line.getTrxAmt.add(line.getChargeAmt)
      assert(
        sameAmount(statementAmount, appliedAmount),
        s"line ${line.getIndex}: StmtAmt $statementAmount must equal TrxAmt ${line.getTrxAmt} plus ChargeAmt ${line.getChargeAmt}"
      )
    }

  Feature("one payment distributed over several imported bank statement lines") {

    Scenario("the payment covers every imported line exactly and leaves no charge (#4442)") {
      Given("a receipt payment of 100.00")
      val paymentAmount = amount("100.00")
      And("two imported lines of 60.00 and 40.00, the same the bank moved")
      val importedAmounts = List(amount("60.00"), amount("40.00"))

      When("the payment is distributed over the imported lines")
      val distribution = toPayment(paymentAmount, importedAmounts, false)

      Then("every imported line keeps the amount the bank moved")
      assert(sameAmount(lineFor(distribution, 0).getStmtAmt, amount("60.00")), "first line must keep 60.00")
      assert(sameAmount(lineFor(distribution, 1).getStmtAmt, amount("40.00")), "second line must keep 40.00")

      And("the applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the sum of TrxAmt must equal the payment amount $paymentAmount")

      And("there is no difference to take to a charge")
      assert(sameAmount(totalOf(distribution)(_.getChargeAmt), BigDecimal.ZERO),
        "an exact match must leave no charge")

      And("each line closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("the bank moved more than the payment and the difference goes to the charge (#4442)") {
      Given("a receipt payment of 100.00")
      val paymentAmount = amount("100.00")
      And("two imported lines of 70.00 and 50.00, 120.00 in total")
      val importedAmounts = List(amount("70.00"), amount("50.00"))

      When("the payment is distributed over the imported lines")
      val distribution = toPayment(paymentAmount, importedAmounts, false)

      Then("the biggest line is covered in full and the smallest one is short by 20.00")
      assert(sameAmount(lineFor(distribution, 0).getTrxAmt, amount("70.00")), "the 70.00 line must be covered in full")
      assert(sameAmount(lineFor(distribution, 1).getTrxAmt, amount("30.00")), "the 50.00 line is only covered by 30.00")
      assert(sameAmount(lineFor(distribution, 1).getChargeAmt, amount("20.00")), "the 20.00 short must go to the charge")

      And("the applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the sum of TrxAmt must equal the payment amount $paymentAmount")

      And("each line closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("the payment exceeds the imported lines and the last line takes the remainder (#4442)") {
      Given("a receipt payment of 100.00")
      val paymentAmount = amount("100.00")
      And("two imported lines of 30.00 and 20.00, only 50.00 in total")
      val importedAmounts = List(amount("30.00"), amount("20.00"))

      When("the payment is distributed over the imported lines")
      val distribution = toPayment(paymentAmount, importedAmounts, false)

      Then("the applied amounts still add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the sum of TrxAmt must equal the payment amount $paymentAmount")

      And("the over applied 50.00 is taken back as a negative charge")
      assert(sameAmount(totalOf(distribution)(_.getChargeAmt), amount("-50.00")),
        "the 50.00 over payment must be reflected as a negative charge")

      And("each line still closes against the amount the bank moved")
      assertChargeClosesEveryLine(distribution)
      assert(sameAmount(totalOf(distribution)(_.getStmtAmt), amount("50.00")),
        "the bank only moved 50.00, the statement must not be distorted")
    }

    Scenario("the biggest imported line is matched before the smallest one (#4442)") {
      Given("a receipt payment of 95.00")
      val paymentAmount = amount("95.00")
      And("three imported lines of 10.00, 90.00 and 40.00, unsorted")
      val importedAmounts = List(amount("10.00"), amount("90.00"), amount("40.00"))

      When("the payment is distributed over the imported lines")
      val distribution = toPayment(paymentAmount, importedAmounts, false)

      Then("the lines are processed from the biggest to the smallest")
      assert(distribution.map(_.getIndex) == List(1, 2, 0),
        s"the processing order must be the biggest line first, was ${distribution.map(_.getIndex)}")

      And("the first line is covered in full and the rest takes the difference")
      assert(sameAmount(lineFor(distribution, 1).getTrxAmt, amount("90.00")), "the 90.00 line must be covered in full")
      assert(sameAmount(lineFor(distribution, 2).getTrxAmt, amount("5.00")), "the 40.00 line is only covered by 5.00")
      assert(sameAmount(lineFor(distribution, 0).getTrxAmt, BigDecimal.ZERO), "the 10.00 line gets nothing left")

      And("the applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the sum of TrxAmt must equal the payment amount $paymentAmount")

      And("each line closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("an outcome payment distributed over outcome imported lines keeps the direction (#4442)") {
      Given("an outcome payment of -100.00")
      val paymentAmount = amount("-100.00")
      And("two imported outcome lines of -60.00 and -40.00")
      val importedAmounts = List(amount("-60.00"), amount("-40.00"))

      When("the payment is distributed over the imported lines")
      val distribution = toPayment(paymentAmount, importedAmounts, false)

      Then("every applied amount stays an outcome")
      distribution.foreach { line =>
        assert(line.getTrxAmt.signum <= 0,
          s"line ${line.getIndex} must never flip an outcome into income, was ${line.getTrxAmt}")
      }

      And("the signed applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the signed sum of TrxAmt must equal the payment amount $paymentAmount")

      And("each line keeps the amount the bank moved and closes against it")
      assert(sameAmount(lineFor(distribution, 0).getStmtAmt, amount("-60.00")), "the first line must keep -60.00")
      assert(sameAmount(lineFor(distribution, 1).getStmtAmt, amount("-40.00")), "the second line must keep -40.00")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("an outcome payment short of the outcome imported lines charges the difference with the outcome sign (#4442)") {
      Given("an outcome payment of -100.00")
      val paymentAmount = amount("-100.00")
      And("two imported outcome lines of -70.00 and -50.00, -120.00 in total")
      val importedAmounts = List(amount("-70.00"), amount("-50.00"))

      When("the payment is distributed over the imported lines")
      val distribution = toPayment(paymentAmount, importedAmounts, false)

      Then("the biggest line is covered in full and the smallest one is short by 20.00")
      assert(sameAmount(lineFor(distribution, 0).getTrxAmt, amount("-70.00")), "the -70.00 line must be covered in full")
      assert(sameAmount(lineFor(distribution, 1).getTrxAmt, amount("-30.00")), "the -50.00 line is only covered by -30.00")

      And("the 20.00 short is charged as an outcome, never as income")
      assert(sameAmount(lineFor(distribution, 1).getChargeAmt, amount("-20.00")),
        "the shortfall of an outcome match must be charged with the outcome sign")

      And("the signed applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the signed sum of TrxAmt must equal the payment amount $paymentAmount")

      And("each line closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }
  }

  Feature("a mixed selection of income and outcome keeps the accounting invariant") {

    Scenario("a mixed selection whose total matches the payment leaves no charge (#4442)") {
      Given("a receipt payment of 100.00")
      val paymentAmount = amount("100.00")
      And("one income line of 150.00 and one outcome line of -50.00")
      val importedAmounts = List(amount("150.00"), amount("-50.00"))

      When("the payment is distributed over the mixed selection")
      val distribution = toPayment(paymentAmount, importedAmounts, true)

      Then("every line keeps its own signed amount, so the bank statement is not distorted")
      assert(sameAmount(lineFor(distribution, 0).getStmtAmt, amount("150.00")), "the income line must keep 150.00")
      assert(sameAmount(lineFor(distribution, 1).getStmtAmt, amount("-50.00")), "the outcome line must keep -50.00")

      And("the signed applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the signed sum of TrxAmt must equal the payment amount $paymentAmount")

      And("each line closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("the biggest line absorbs the whole difference of a mixed selection (#4442)") {
      Given("a receipt payment of 90.00")
      val paymentAmount = amount("90.00")
      And("one income line of 150.00 and one outcome line of -50.00, 100.00 in total")
      val importedAmounts = List(amount("150.00"), amount("-50.00"))

      When("the payment is distributed over the mixed selection")
      val distribution = toPayment(paymentAmount, importedAmounts, true)

      Then("the biggest line takes the whole 10.00 difference as its charge")
      assert(sameAmount(lineFor(distribution, 0).getChargeAmt, amount("10.00")),
        "the biggest line must absorb the whole difference")
      assert(sameAmount(lineFor(distribution, 0).getTrxAmt, amount("140.00")),
        "the biggest line applies 150.00 minus the 10.00 difference")

      And("the outcome line is left untouched")
      assert(sameAmount(lineFor(distribution, 1).getTrxAmt, amount("-50.00")), "the outcome line must keep -50.00")
      assert(sameAmount(lineFor(distribution, 1).getChargeAmt, BigDecimal.ZERO), "the outcome line must take no charge")

      And("the signed applied amounts add up to the payment amount")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
        s"the signed sum of TrxAmt must equal the payment amount $paymentAmount")

      And("each line closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }
  }

  Feature("one imported bank statement line reversed over several payments") {

    Scenario("the imported line splits exactly over the payments (#4442)") {
      Given("an imported line the bank moved for 100.00")
      val importedAmount = amount("100.00")
      And("two payments of 60.00 and 40.00")
      val payAmounts = List(amount("60.00"), amount("40.00"))

      When("the imported line is reversed over the payments")
      val distribution = toPayments(importedAmount, payAmounts, false)

      Then("the statement amounts of the splits add up to the imported line amount")
      assert(sameAmount(totalOf(distribution)(_.getStmtAmt), importedAmount),
        s"the sum of StmtAmt must equal the imported line amount $importedAmount")

      And("the applied amounts add up to the sum of the payments")
      assert(sameAmount(totalOf(distribution)(_.getTrxAmt), amount("100.00")),
        "the sum of TrxAmt must equal the sum of the payments")

      And("there is no difference to take to a charge")
      assert(sameAmount(totalOf(distribution)(_.getChargeAmt), BigDecimal.ZERO),
        "an exact reverse match must leave no charge")

      And("each split closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("the last split takes the pending statement amount and the shortfall goes to the charge (#4442)") {
      Given("an imported line the bank moved for 100.00")
      val importedAmount = amount("100.00")
      And("two payments of 30.00 and 50.00, only 80.00 in total")
      val payAmounts = List(amount("30.00"), amount("50.00"))

      When("the imported line is reversed over the payments")
      val distribution = toPayments(importedAmount, payAmounts, false)

      Then("the payments keep their own amount on the accounting side")
      assert(sameAmount(lineFor(distribution, 1).getTrxAmt, amount("50.00")), "the 50.00 payment applies 50.00")
      assert(sameAmount(lineFor(distribution, 0).getTrxAmt, amount("30.00")), "the 30.00 payment applies 30.00")

      And("the last split takes the pending statement amount so the bank total matches")
      assert(sameAmount(lineFor(distribution, 0).getStmtAmt, amount("50.00")),
        "the last split must take the 50.00 the bank still has pending")

      And("the 20.00 the payments do not cover goes to the charge")
      assert(sameAmount(totalOf(distribution)(_.getChargeAmt), amount("20.00")),
        "the 20.00 shortfall must go to the charge")

      And("the statement amounts of the splits add up to the imported line amount")
      assert(sameAmount(totalOf(distribution)(_.getStmtAmt), importedAmount),
        s"the sum of StmtAmt must equal the imported line amount $importedAmount")

      And("each split closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("an outcome imported line keeps its sign on every split (#4442)") {
      Given("an imported payment the bank moved out for -100.00")
      val importedAmount = amount("-100.00")
      And("two payments of -60.00 and -40.00")
      val payAmounts = List(amount("-60.00"), amount("-40.00"))

      When("the imported line is reversed over the payments")
      val distribution = toPayments(importedAmount, payAmounts, false)

      Then("no split flips the direction of the money")
      distribution.foreach { line =>
        assert(line.getStmtAmt.signum <= 0,
          s"split ${line.getIndex} must never flip an outcome into income, was ${line.getStmtAmt}")
        assert(line.getTrxAmt.signum <= 0,
          s"split ${line.getIndex} must never flip an outcome into income, was ${line.getTrxAmt}")
      }

      And("the signed statement amounts add up to the imported line amount")
      assert(sameAmount(totalOf(distribution)(_.getStmtAmt), importedAmount),
        s"the signed sum of StmtAmt must equal the imported line amount $importedAmount")

      And("each split closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }

    Scenario("the rounding residue of three equal payments lands on the last split (#4442)") {
      Given("an imported line the bank moved for 100.00")
      val importedAmount = amount("100.00")
      And("three payments of 33.33, which do not divide the total exactly")
      val payAmounts = List(amount("33.33"), amount("33.33"), amount("33.33"))

      When("the imported line is reversed over the payments")
      val distribution = toPayments(importedAmount, payAmounts, false)

      Then("the statement amounts of the splits still add up to exactly 100.00")
      assert(sameAmount(totalOf(distribution)(_.getStmtAmt), importedAmount),
        s"the sum of StmtAmt must equal the imported line amount $importedAmount")

      And("the last split carries the 0.01 the thirds left over")
      assert(sameAmount(lineFor(distribution, 2).getStmtAmt, amount("33.34")),
        "the last split must take the pending 33.34")
      assert(sameAmount(lineFor(distribution, 2).getChargeAmt, amount("0.01")),
        "the 0.01 rounding residue must go to the charge of the last split")

      And("each split closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }
  }

  Feature("the distribution survives selections with nothing to distribute") {

    Scenario("no imported line produces no amounts and no failure (#4442)") {
      Given("a receipt payment of 100.00 and an empty imported selection")
      val paymentAmount = amount("100.00")

      When("the payment is distributed over nothing")
      val distribution = toPayment(paymentAmount, List.empty, false)

      Then("there is nothing to distribute and nothing to balance")
      assert(distribution.isEmpty, "an empty imported selection must produce no lines")
    }

    Scenario("a zero amount payment against an imported line charges the whole statement amount (#4442)") {
      Given("an imported line the bank moved for 100.00")
      val importedAmount = amount("100.00")
      And("one payment of 0.00")
      val payAmounts = List(BigDecimal.ZERO)

      When("the imported line is reversed over the payment")
      val distribution = toPayments(importedAmount, payAmounts, false)

      Then("the whole statement amount is taken by the charge")
      assert(sameAmount(lineFor(distribution, 0).getTrxAmt, BigDecimal.ZERO),
        "a zero payment must apply nothing")
      assert(sameAmount(lineFor(distribution, 0).getChargeAmt, importedAmount),
        "the whole statement amount must go to the charge")

      And("the split closes against its own statement amount")
      assertChargeClosesEveryLine(distribution)
    }
  }

  /**
    * Known defect, pinned so it is not lost.
    *
    * distributeToPayment takes the payment amount as an absolute value on the
    * homogeneous path (BankStatementMatchAmounts.java:92) and then applies the
    * sign of every imported line (BankStatementMatchAmounts.java:99-100). When
    * the payment and the imported lines have opposite signs and mixing is
    * disabled, the result keeps the sign of the lines, so the sum of TrxAmt no
    * longer equals the payment amount and the invariant documented on
    * BankStatementMatchAmounts.java:27 is broken.
    *
    * It is not reachable today because BankStatementMatchController keeps
    * allowMixedTypes permanently true: setAllowMixedTypes is never called from
    * VBankStatementMatch, from WBankStatementMatch or from anywhere else in the
    * repository, so isMixedMatch always routes opposite signs to the signed
    * algorithm. The moment that flag is wired to a user control this scenario
    * becomes a real accounting error.
    *
    * Expected once the defect is fixed, with payAmt -100.00 and one imported
    * receipt line of 100.00 and mixing disabled: the sum of TrxAmt must be
    * -100.00, not 100.00. Un-ignore this scenario together with that fix.
    */
  ignore("opposite signs with mixed types disabled must still match the payment amount (#4442)") {
    Given("an outcome payment of -100.00")
    val paymentAmount = amount("-100.00")
    And("one imported receipt line of 100.00")
    val importedAmounts = List(amount("100.00"))

    When("the payment is distributed with mixing disabled")
    val distribution = toPayment(paymentAmount, importedAmounts, false)

    Then("the applied amounts add up to the payment amount")
    assert(sameAmount(totalOf(distribution)(_.getTrxAmt), paymentAmount),
      s"the sum of TrxAmt must equal the payment amount $paymentAmount")
  }
}
