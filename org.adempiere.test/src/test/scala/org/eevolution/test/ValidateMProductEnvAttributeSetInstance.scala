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

import java.sql.ResultSet

import org.compiere.model.{MAttributeSetInstance, MProduct}
import org.compiere.util.{DB, Env}
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
  *
  * Context values are written with Env.setContext, which stores them as
  * strings, and read back by Env.getContextAsInt, exactly as the callouts do.
  */
class ValidateMProductEnvAttributeSetInstance
    extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /**  An instance identifier that cannot exist in any supported installation. */
  private val StaleAttributeSetInstance_ID = 999999999

  /**  Window number of the document window under test. */
  private val StandardWindowNo = 0

  private def execQuery(sql: String, params: Array[AnyRef]): ResultSet = {
    val conn = DB.getConnectionRW()
    val ps = conn.prepareStatement(sql)
    try {
      var i = 1
      params.foreach { p =>
        ps.setObject(i, p)
        i += 1
      }
      ps.executeQuery()
    } catch {
      case e: Throwable =>
        ps.close()
        throw e
    }
  }    //	execQuery

  /**
    * Loads a product that has an attribute set but no product-level attribute
    * set instance, so getEnvAttributeSetInstance falls through to the context.
    */
  private def productWithAttributeSet(): MProduct = {
    var productId = 0
    val rs = execQuery(
      "SELECT M_Product_ID FROM M_Product "
        + "WHERE IsActive='Y' AND M_AttributeSet_ID > 0 "
        + "AND (M_AttributeSetInstance_ID = 0 OR M_AttributeSetInstance_ID IS NULL) "
        + "ORDER BY M_Product_ID",
      Array.empty[AnyRef])
    try {
      if (rs.next())
        productId = rs.getInt(1)
    } finally {
      DB.close(rs)
    }
    assert(productId > 0,
      "the test database must contain at least one active product with an attribute set and no product-level instance")
    MProduct.get(Context, productId)
  }

  /**  An existing attribute set instance of the same attribute set, or 0 when there is none. */
  private def existingInstanceId(attributeSetId: Int): Int = {
    var instanceId = 0
    val rs = execQuery(
      "SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance "
        + "WHERE M_AttributeSet_ID = ? ORDER BY M_AttributeSetInstance_ID",
      Array[AnyRef](int2Integer(attributeSetId)))
    try {
      if (rs.next())
        instanceId = rs.getInt(1)
    } finally {
      DB.close(rs)
    }
    instanceId
  }

  Feature("MProduct.getEnvAttributeSetInstance with a stale context attribute set instance") {
    info("Issue #4434: a stale M_AttributeSetInstance_ID in the window context must be discarded, not dereferenced")

    Scenario("a stale context attribute set instance does not throw and is cleared") {
      Given("a product that has an attribute set but no product-level instance")
      val product = productWithAttributeSet()
      assert(product.getM_AttributeSet_ID > 0)

      When("the window context carries a M_AttributeSetInstance_ID that does not exist")
      Env.setContext(Context, StandardWindowNo, Env.TAB_INFO,
        "M_AttributeSetInstance_ID", StaleAttributeSetInstance_ID.toString)

      Then("getEnvAttributeSetInstance does not throw and returns null (instance cleared)")
      val attributeSetInstance = product.getEnvAttributeSetInstance(Context, StandardWindowNo)
      assert(attributeSetInstance == null,
        "a stale context instance must be discarded and the method must return null")
    }

    Scenario("an existing context attribute set instance of the same set is kept") {
      Given("a product with an attribute set that has an existing instance in the database")
      val product = productWithAttributeSet()
      val existing = existingInstanceId(product.getM_AttributeSet_ID)
      if (existing == 0) {
        info("skipped: no existing attribute set instance for the test product in this database")
      } else {
        When("the window context carries that instance")
        Env.setContext(Context, StandardWindowNo, Env.TAB_INFO,
          "M_AttributeSetInstance_ID", existing.toString)

        Then("getEnvAttributeSetInstance returns the same instance")
        val attributeSetInstance = product.getEnvAttributeSetInstance(Context, StandardWindowNo)
        assert(attributeSetInstance != null
          && attributeSetInstance.intValue == existing,
          "an instance of the same attribute set must be kept")
      }
    }
  }
}
