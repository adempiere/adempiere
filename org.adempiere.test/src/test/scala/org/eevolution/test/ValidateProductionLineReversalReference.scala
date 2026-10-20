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
import java.sql.Timestamp

import org.adempiere.core.domains.models.I_M_ProductionLine
import org.compiere.model.{MProduct, MProduction, MProductionLine}
import org.compiere.util.{DB, Env}
import org.eevolution.services.ProductService
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen

/**
  * Regression test for issue #4437: a production line must resolve its
  * ReversalLine_ID against M_ProductionLine (not M_InOutLine), and
  * getReversalDocumentLine() must return the referenced original line.
  *
  * The lines are built through the model, so every mandatory column of
  * M_Production and M_ProductionLine is set the way the production process
  * sets it. All rows live in the transaction opened by AdempiereTestCase and
  * are rolled back when the suite finishes.
  */
class ValidateProductionLineReversalReference
    extends AnyFeatureSpec
    with AdempiereTestCase
    with ProductService
    with GivenWhenThen {

  /**  Movement date of the production header, inside the running period. */
  private def movementDate: Timestamp = new Timestamp(System.currentTimeMillis())

  /**  An active locator of the tested organization, or 0 when there is none. */
  private def locatorId: Int = {
    val conn = DB.getConnectionRW()
    val statement = conn.prepareStatement(
      "SELECT M_Locator_ID FROM M_Locator "
        + "WHERE AD_Client_ID=? AND AD_Org_ID IN (0,?) AND IsActive='Y' "
        + "ORDER BY M_Locator_ID")
    try {
      statement.setInt(1, Env.getAD_Client_ID(Context))
      statement.setInt(2, Env.getAD_Org_ID(Context))
      val rs = statement.executeQuery()
      try {
        if (rs.next()) rs.getInt(1) else 0
      } finally {
        DB.close(rs)
      }
    } finally {
      statement.close()
    }
  }

  /**  Minimal production header: the line is mandatory to it. */
  private def createProduction(): MProduction = {
    val production = new MProduction(Context, 0, trxName)
    production.setMovementDate(movementDate)
    production.setIsCreated(false)
    production.setIsWIP(false)
    production.setPosted(false)
    production.setProcessed(false)
    assert(production.save(trxName), "the production header must be saved")
    production
  }

  /**  Production line of that header, with every mandatory column set. */
  private def createProductionLine(
      production: MProduction,
      product: MProduct,
      lineNo: Int,
      locator: Int): MProductionLine = {
    val line = new MProductionLine(Context, 0, trxName)
    line.setM_Production_ID(production.getM_Production_ID)
    line.setM_Product_ID(product.getM_Product_ID)
    line.setM_Locator_ID(locator)
    line.setLine(lineNo)
    line.setMovementQty(new BigDecimal(1))
    assert(line.save(trxName), "the production line must be saved")
    line
  }

  Feature("Production reversal line reference resolution") {
    info("Issue #4437: ReversalLine_ID of a production line must resolve against M_ProductionLine")

    Scenario("A production line resolves its reversal reference in M_ProductionLine") {
      Given("a product that can be used in production")
      val product = getProductByValue("Oak")

      And("an active locator and a production header")
      val locator = locatorId
      assert(locator > 0, "the test database must contain an active locator")
      val production = createProduction()

      When("an original line and a reversal line pointing to it are saved")
      val original = createProductionLine(production, product, 10, locator)
      val reversal = createProductionLine(production, product, 20, locator)
      reversal.setReversalLine_ID(original.getM_ProductionLine_ID)
      assert(reversal.save(trxName), "the reversal reference must be saved")

      And("the reversal line is reloaded without the previous cached state")
      val loadedReversal =
        new MProductionLine(Context, reversal.getM_ProductionLine_ID, trxName)

      Then("getReversalLine() resolves the reference in M_ProductionLine")
      val resolvedLine = loadedReversal.getReversalLine()
      assert(resolvedLine != null,
        "the reversal reference must resolve to a production line")
      assert(resolvedLine.isInstanceOf[MProductionLine],
        "the reference must resolve to the M_ProductionLine model, not another table")
      assert(resolvedLine.getM_ProductionLine_ID == original.getM_ProductionLine_ID,
        "the resolved line must be the referenced original line")

      And("getReversalDocumentLine() returns the original line, not itself")
      val docLine = loadedReversal.getReversalDocumentLine()
      assert(docLine != null,
        "the reversal document line must resolve to a document line")
      assert(docLine.get_ID == original.getM_ProductionLine_ID,
        "the reversal document line must be the referenced original line")
      assert(docLine.get_ID != loadedReversal.getM_ProductionLine_ID,
        "the reversal document line must not be the reversal line itself")
    }
  }
}
