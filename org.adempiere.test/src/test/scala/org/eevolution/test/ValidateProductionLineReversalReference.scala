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

import org.adempiere.core.domains.models.I_M_ProductionLine
import org.compiere.model.MProduct
import org.compiere.model.MProductionLine
import org.eevolution.services.ProductService
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen

/**
  * Regression test for issue #4437: a reversed production line must resolve
  * its ReversalLine_ID against M_ProductionLine (not M_InOutLine), and
  * getReversalDocumentLine() must return the referenced original line.
  */
class ValidateProductionLineReversalReference extends AnyFeatureSpec
  with AdempiereTestCase
  with ProductService
  with GivenWhenThen {

  def createProductionLine(product: MProduct, lineNo: Int): MProductionLine = {
    val line = new MProductionLine(Context, 0, trxName)
    line.setM_Product_ID(product.getM_Product_ID)
    line.setLine(new java.math.BigDecimal(lineNo))
    line.setMovementQty(new java.math.BigDecimal(1))
    line.save()
    line
  }

  Feature("Production reversal line reference resolution") {
    info("Issue #4437: ReversalLine_ID of a production line must resolve against M_ProductionLine")

    Scenario("A reversed production line resolves its original line") {
      Given("a product that can be used in production")
      val product = getProductByValue("Oak")

      When("an original and a reversed production line are created")
      val original = createProductionLine(product, 10)
      val reversal = createProductionLine(product, 20)
      reversal.setReversalLine_ID(original.getM_ProductionLine_ID)
      reversal.save()

      // Reload the reversal line to avoid caching of the previous state
      val loadedReversal = new MProductionLine(Context, reversal.getM_ProductionLine_ID, trxName)

      Then("getReversalLine() resolves the reference in M_ProductionLine")
      val resolvedLine = loadedReversal.getReversalLine()
      assert(resolvedLine != null)
      assert(resolvedLine.getTable_ID == I_M_ProductionLine.Table_ID)
      assert(resolvedLine.getM_ProductionLine_ID == original.getM_ProductionLine_ID)

      And("getReversalDocumentLine() returns the original line, not itself")
      val docLine = loadedReversal.getReversalDocumentLine()
      assert(docLine != null)
      assert(docLine.getM_ProductionLine_ID == original.getM_ProductionLine_ID)
      assert(docLine.getM_ProductionLine_ID != loadedReversal.getM_ProductionLine_ID)
    }
  }
}
