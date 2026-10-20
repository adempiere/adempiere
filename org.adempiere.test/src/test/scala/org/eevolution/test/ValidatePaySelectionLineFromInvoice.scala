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
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2016 e-Evolution,SC. All Rights Reserved.               *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

package org.eevolution.test

import java.math.BigDecimal
import java.util.logging.Level

import org.adempiere.Adempiere
import org.adempiere.core.domains.models.X_C_PaySelectionLine
import org.compiere.model.MPaySelectionLine
import org.compiere.util.{CLogMgt, Env, Ini, Login}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for adempiere/adempiere#4313, #4314 and #4317:
  * pay selection lines created from invoices/orders take the payment
  * rule, description and business partner bank account from the source
  * document, and creating the lines must never fail with a
  * NullPointerException in MPaySelectionLine.setPaymentRule.
  *
  * Before the fix, setPaymentRule dereferenced the current (null) value
  * of a new line, so the MPaySelectionLine(paySelection, line, rule)
  * constructor raised a NullPointerException. The isSOTrx setInvoice
  * overload read the still-null invoice field before its local invoice
  * was created, with the same failure.
  *
  * The scenarios run on in-memory models inside the rolled-back test
  * transaction: no pay selection line, invoice or bank account rows
  * are committed.
  */
class ValidatePaySelectionLineFromInvoice
    extends AnyFeatureSpec
    with AdempiereTestCase
    with GivenWhenThen {

  private def newLine: MPaySelectionLine =
    new MPaySelectionLine(Context, 0, trxName)

  /**
    * Start Adempiere with the standard GardenWorld demo credentials.
    * The shared AdempiereStartup trait still logs in as SuperUser, which
    * does not exist in the standard 3.9 install of client GardenWorld.
    */
  override def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.ALL)
    CLogMgt.setLoggerLevel(Level.ALL, null)

    Ini.setProperty(Ini.P_UID, "GardenAdmin")
    Ini.setProperty(Ini.P_PWD, "GardenAdmin")
    Ini.setProperty(Ini.P_ROLE, "GardenWorld Admin")
    Ini.setProperty(Ini.P_CLIENT, "GardenWorld")
    Ini.setProperty(Ini.P_ORG, "Store Central")
    Ini.setProperty(Ini.P_WAREHOUSE, "Store Central")
    Ini.setProperty(Ini.P_LANGUAGE, "English")

    val login = new Login(Env.getCtx)
    if (!login.batchLogin(null))
      throw new AssertionError("batchLogin failed for GardenAdmin/GardenWorld")
    CLogMgt.setLoggerLevel(Level.SEVERE, null)
    CLogMgt.setLevel(Level.SEVERE)
  }    //  startup

  private def runQuietly(body: => Unit): Throwable =
    try { body; null } catch { case t: Throwable => t }

  Feature("Pay selection lines created from invoices") {
    info("setPaymentRule must be null-safe on a new line (NPE regression)")

    Scenario("setting a supported payment rule on a new line does not throw") {
      Given("a new pay selection line with the standard default payment rule")
      val line = newLine
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_Check,
        "a new pay selection line must start with the standard check rule")
      When("the credit card payment rule is set")
      val error = runQuietly(
        line.setPaymentRule(X_C_PaySelectionLine.PAYMENTRULE_CreditCard)
      )
      Then("no exception is raised")
      assert(error == null, "setPaymentRule must not raise on a new line")
      Then("the line holds the credit card rule")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_CreditCard,
        "the incoming credit card rule must be kept")
    }

    Scenario("setting a null payment rule on a new line falls back to direct deposit") {
      Given("a new pay selection line with the standard default payment rule")
      val line = newLine
      When("the payment rule is set to null")
      val error = runQuietly(line.setPaymentRule(null))
      Then("no exception is raised")
      assert(error == null, "setPaymentRule(null) must not raise on a new line")
      Then("the standard direct deposit rule is set")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_DirectDeposit,
        "a null rule must fall back to the standard direct deposit rule")
    }

    Scenario("an incoming payment rule is kept while the current rule is supported") {
      Given("a new pay selection line with the standard default payment rule")
      val line = newLine
      When("the cash payment rule is set from an invoice")
      val error =
        runQuietly(line.setPaymentRule(X_C_PaySelectionLine.PAYMENTRULE_Cash))
      Then("no exception is raised")
      assert(error == null, "setPaymentRule must not raise on a new line")
      Then("the incoming cash rule is kept, preserving the current-value semantics")
      assert(line.getPaymentRule == X_C_PaySelectionLine.PAYMENTRULE_Cash,
        "an incoming rule must be kept while the current rule is a supported one")
    }

    info("the isSOTrx setInvoice overload must read an initialized invoice")

    Scenario("linking a new invoice through the isSOTrx overload does not throw") {
      Given("a new pay selection line whose invoice field is null")
      val line = newLine
      When("setInvoice with the isSOTrx flag is called with a new invoice")
      val error = runQuietly(
        line.setInvoice(
          0,
          0,
          false,
          new BigDecimal("100.00"),
          new BigDecimal("50.00"),
          BigDecimal.ZERO
        )
      )
      Then("no exception is raised")
      assert(error == null,
        "the isSOTrx setInvoice overload must not raise on a new line")
      Then("the difference amount is computed from the open and pay amounts")
      assert(line.getDifferenceAmt.compareTo(new BigDecimal("50.00")) == 0,
        "openAmt - payAmt - discountAmt must be 50.00")
    }
  }
}
