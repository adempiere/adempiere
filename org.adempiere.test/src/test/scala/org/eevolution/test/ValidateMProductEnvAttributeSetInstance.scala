/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software; you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope  *
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

import org.compiere.model.{MAttributeSetInstance, MProduct}
import org.compiere.util.{Env, PO, WindowNo}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression test for issue #4434 (fixed by PR #4431):
  * MProduct.getEnvAttributeSetInstance() threw a NullPointerException when the
  * window context carried a stale M_AttributeSetInstance_ID that does not
  * resolve to an instance with a valid attribute set (the old code compared
  * `masi.getMAttributeSet().get_ID()` and dereferenced a null attribute set).
  * The method is invoked from CalloutOrder and CalloutInvoice when a sales
  * order or invoice line window is opened.
  */
class ValidateMProductEnvAttributeSetInstance extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen {

  private val StaleAttributeSetInstance_ID = 999999999

  private def productWithAttributeSet(): MProduct = {
    val product = new MProduct(Context, 0, trxName)
    product.setWhereClause("M_AttributeSet_ID > 0 AND (M_AttributeSetInstance_ID = 0 OR M_AttributeSetInstance_ID IS NULL)")
    product.query(PO.QUERYTYPE_TOP)
    assert(product.first, "the test database must contain at least one product with an attribute set and no product-level instance")
    product
  }

  Feature("MProduct.getEnvAttributeSetInstance with a stale context attribute set instance") {
    info("Issue #4434: a stale M_AttributeSetInstance_ID in the window context must be discarded, not dereferenced")

    Scenario("a stale context attribute set instance does not throw and is cleared") {
      Given("a product that has an attribute set but no product-level instance")
      val product = productWithAttributeSet()
      assert(product.getM_AttributeSet_ID > 0)

      When("the window context carries a M_AttributeSetInstance_ID that does not exist")
      Env.setContext(Context, WindowNo.getDefault, Env.TAB_INFO, "M_AttributeSetInstance_ID", StaleAttributeSetInstance_ID)

      Then("getEnvAttributeSetInstance does not throw and returns null (instance cleared)")
      val attributeSetInstance = product.getEnvAttributeSetInstance(Context, WindowNo.getDefault)
      assert(attributeSetInstance == null, "a stale context instance must be discarded and the method must return null")
    }

    Scenario("an existing context attribute set instance of the same set is kept") {
      Given("a product with an attribute set that has an existing instance in the database")
      val product = productWithAttributeSet()
      val existing = new MAttributeSetInstance(Context, 0, trxName)
      existing.setWhereClause("M_Product_ID = " + product.getM_Product_ID)
      existing.query(PO.QUERYTYPE_TOP)
      if (!existing.first) {
        info("skipped: no existing attribute set instance for the test product in this database")
      } else {
        When("the window context carries that instance")
        Env.setContext(Context, WindowNo.getDefault, Env.TAB_INFO, "M_AttributeSetInstance_ID", existing.getM_AttributeSetInstance_ID)

        Then("getEnvAttributeSetInstance returns the same instance")
        val attributeSetInstance = product.getEnvAttributeSetInstance(Context, WindowNo.getDefault)
        assert(attributeSetInstance == existing.getM_AttributeSetInstance_ID)
      }
    }
  }
}
