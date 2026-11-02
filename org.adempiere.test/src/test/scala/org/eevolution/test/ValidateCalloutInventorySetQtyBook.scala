/** ****************************************************************************
  * Product: Adempiere ERP & CRM Smart Business Solution                       *
  * This program is free software, you can redistribute it and/or modify it    *
  * under the terms version 2 of the GNU General Public License as published   *
  * by the Free Software Foundation. This program is distributed in the hope   *
  * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
  * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
  * See the GNU General Public License for more details.                       *
  * You should have received a copy of the GNU General Public License along    *
  * with this program, if not, write to the Free Software Foundation, Inc.,    *
  * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2026 ADempiere Foundation, All Rights Reserved.              *
  * ****************************************************************************/

package org.eevolution.test

import java.lang.reflect.{InvocationTargetException, Method}
import java.math.BigDecimal

import org.compiere.model.{CalloutInventory, MStorage}
import org.compiere.util.{DB, Trx}
import org.eevolution.dsl.*
import org.eevolution.services.ProductService
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.collection.mutable.ArrayBuffer
import scala.util.{Failure, Success, Try}

/**
  * Regression test for issue #4452 (fixed by PR #4453):
  * CalloutInventory.setQtyBook() returned before closing its ResultSet and
  * PreparedStatement when the query found a non-null quantity or no matching
  * storage row, and a SQLException also bypassed both explicit close calls.
  * Repeated inventory line callouts could leave database connections in use.
  *
  * setQtyBook is the method extracted from the CalloutInventory.product
  * callout (the QtyBook of an inventory line). It is invoked directly through
  * reflection so the spec does not need a full grid window, and every path
  * named in the issue is exercised:
  *   - no storage rows for the locator (gwu 1719401: booked qty cleared to 0),
  *   - SUM path (no attribute set instance) with stored rows,
  *   - detail path (attribute set instance) binding the third parameter,
  *   - repeated callouts alternating both paths, to show the pool stays healthy.
  *
  * The fixtures are committed (saveEx) on purpose: setQtyBook opens its own
  * statement with a null transaction name (DB.prepareStatement(sql, null)), so
  * rows written inside the suite transaction would not be visible to it. The
  * committed fixtures are deleted in afterAll, and every locator is created
  * with a PR4453-prefixed value so nothing collides with the demo data.
  */
class ValidateCalloutInventorySetQtyBook extends AnyFeatureSpec
  with AdempiereTestCase
  with ProductService
  with GivenWhenThen {

  /**  Locators created by this suite, deleted in afterAll. */
  private val createdLocatorIds = ArrayBuffer.empty[Int]

  override def afterAll(): Unit = {
    //	The callout reads outside the suite transaction, so its fixtures were
    //	committed: remove them explicitly instead of relying on the rollback.
    if (createdLocatorIds.nonEmpty) {
      val ids = createdLocatorIds.mkString(",")
      DB.executeUpdate(s"DELETE FROM M_Storage WHERE M_Locator_ID IN ($ids)", null)
      DB.executeUpdate(s"DELETE FROM M_Transaction WHERE M_Locator_ID IN ($ids)", null)
      DB.executeUpdate(s"DELETE FROM M_Locator WHERE M_Locator_ID IN ($ids)", null)
      createdLocatorIds.clear()
    }
    super.afterAll()
  }

  /**  Loads the private extracted method invoked by the product callout. */
  private def setQtyBookMethod: Method = {
    val method = classOf[CalloutInventory]
      .getDeclaredMethod("setQtyBook", classOf[Int], classOf[Int], classOf[Int])
    method.setAccessible(true)
    method
  }

  /**  Invokes setQtyBook and surfaces the real cause of a failed call. */
  private def setQtyBook(attributeSetInstanceId: Int, productId: Int,
      locatorId: Int): BigDecimal = {
    val callout = new CalloutInventory
    Try(setQtyBookMethod.invoke(callout,
      Int.box(attributeSetInstanceId), Int.box(productId), Int.box(locatorId))) match {
      case Success(quantity) => quantity.asInstanceOf[BigDecimal]
      case Failure(e: InvocationTargetException) => throw e.getCause
      case Failure(e) => throw e
    }
  }

  /**  A committed locator in the login warehouse; tracked for afterAll cleanup. */
  private def committedLocator(value: String): Locator = {
    val locator = new Locator(Context, 0, null)
    locator.setValue(value)
    locator.setM_Warehouse_ID(Warehouse.getM_Warehouse_ID)
    //	saveEx throws on failure, so a returned Unit already proves the insert.
    locator.saveEx()
    createdLocatorIds += locator.getM_Locator_ID
    locator
  }

  /**  Commits a storage row of the product in the locator. */
  private def committedStorage(locatorId: Int, productId: Int,
      attributeSetInstanceId: Int, qtyOnHand: BigDecimal): Unit = {
    val storage = MStorage.getCreate(Context, locatorId, productId,
      attributeSetInstanceId, null)
    storage.setQtyOnHand(qtyOnHand)
    storage.saveEx()
  }

  /**  An existing attribute set instance, to drive the detail query path. */
  private def existingAttributeSetInstanceId: Int =
    DB.getSQLValue(null,
      "SELECT M_AttributeSetInstance_ID FROM M_AttributeSetInstance "
        + "WHERE M_AttributeSetInstance_ID > 0 ORDER BY M_AttributeSetInstance_ID")

  Feature("CalloutInventory.setQtyBook closes its database resources") {
    info("Issue #4452: every return path of setQtyBook must close the statement and the result set")

    Scenario("a locator that never stored the product returns zero and leaks nothing") {
      Given("a fresh locator with no storage rows for the product Oak")
      val locator = committedLocator("PR4453 Empty")
      val oak = getProductByValue("Oak")
      assert(oak != null, "the test database must contain the demo product Oak")
      val stored = DB.getSQLValue(null,
        "SELECT COUNT(*) FROM M_Storage WHERE M_Locator_ID=? AND M_Product_ID=?",
        locator.getM_Locator_ID, oak.getM_Product_ID)
      assert(stored == 0, "the test locator must start without storage rows")

      When("the book quantity callout runs for that product and locator")
      val bookQuantity = setQtyBook(0, oak.getM_Product_ID, locator.getM_Locator_ID)

      Then("the booked quantity is cleared to zero (gwu 1719401)")
      assert(bookQuantity.compareTo(BigDecimal.ZERO) == 0,
        s"expected zero but got $bookQuantity")
    }

    Scenario("stored quantities are summed and returned on the closed-resource path") {
      Given("two storage rows of the product Oak in one locator, without a single ASI filter")
      val locator = committedLocator("PR4453 Sum")
      val oak = getProductByValue("Oak")
      //	M_Storage is unique per locator/product/ASI, so the second row uses an
      //	attribute set instance: the callout without ASI runs SUM over both rows.
      val attributeSetInstanceId = existingAttributeSetInstanceId
      assume(attributeSetInstanceId > 0,
        "the test database must contain at least one attribute set instance")
      committedStorage(locator.getM_Locator_ID, oak.getM_Product_ID, 0, new BigDecimal("10"))
      committedStorage(locator.getM_Locator_ID, oak.getM_Product_ID,
        attributeSetInstanceId, new BigDecimal("20"))

      When("the book quantity callout runs without an attribute set instance")
      val bookQuantity = setQtyBook(0, oak.getM_Product_ID, locator.getM_Locator_ID)

      Then("the summed book quantity is returned")
      assert(bookQuantity.compareTo(new BigDecimal("30")) == 0,
        s"expected 30 but got $bookQuantity")
    }

    Scenario("the detail query with an attribute set instance returns the stored quantity") {
      Given("an attribute set instance and a storage row of the product Oak for it")
      val attributeSetInstanceId = existingAttributeSetInstanceId
      assume(attributeSetInstanceId > 0,
        "the test database must contain at least one attribute set instance")
      val locator = committedLocator("PR4453 Detail")
      val oak = getProductByValue("Oak")
      committedStorage(locator.getM_Locator_ID, oak.getM_Product_ID,
        attributeSetInstanceId, new BigDecimal("7.5"))

      When("the book quantity callout runs with that attribute set instance")
      val bookQuantity = setQtyBook(attributeSetInstanceId, oak.getM_Product_ID,
        locator.getM_Locator_ID)

      Then("the exact stored quantity of the instance is returned")
      assert(bookQuantity.compareTo(new BigDecimal("7.5")) == 0,
        s"expected 7.5 but got $bookQuantity")
    }

    Scenario("repeated callouts keep the connection pool healthy") {
      Given("a locator with stored quantity for the product Oak")
      val locator = committedLocator("PR4453 Loop")
      val oak = getProductByValue("Oak")
      val attributeSetInstanceId = existingAttributeSetInstanceId
      assume(attributeSetInstanceId > 0,
        "the test database must contain at least one attribute set instance")
      committedStorage(locator.getM_Locator_ID, oak.getM_Product_ID, 0, new BigDecimal("10"))
      committedStorage(locator.getM_Locator_ID, oak.getM_Product_ID,
        attributeSetInstanceId, new BigDecimal("20"))
      //	A locator that stores nothing, to drive the no-rows return path.
      val emptyLocatorId = committedLocator("PR4453 Loop Empty").getM_Locator_ID

      When("the callout runs repeatedly mixing the found and the no-rows paths")
      var ok = true
      var lastQuantity: BigDecimal = null
      for (_ <- 1 to 30) {
        val found = setQtyBook(0, oak.getM_Product_ID, locator.getM_Locator_ID)
        val missing = setQtyBook(0, oak.getM_Product_ID, emptyLocatorId)
        ok = ok && found.compareTo(new BigDecimal("30")) == 0
        lastQuantity = missing
      }

      Then("every call returns the right quantity without exhausting connections")
      assert(ok, "every callout must return the summed book quantity of 30")
      assert(lastQuantity.compareTo(BigDecimal.ZERO) == 0,
        "the no-rows path must keep returning zero")
      And("the suite transaction is still active after the loop")
      val trx = Trx.get(trxName, false)
      assert(trx.isActive)
    }
  }
}
