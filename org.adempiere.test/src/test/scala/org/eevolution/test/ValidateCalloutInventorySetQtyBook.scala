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

import java.lang.reflect.{Field, InvocationTargetException, Method}
import java.math.BigDecimal

import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import org.compiere.db.{CConnection, DB_PostgreSQL}
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
  *   - repeated callouts alternating both paths, to pin observable behaviour when
  *     the same statement is taken and released over and over,
  *   - the same callouts against a pool of one connection, so that a statement
  *     returned without being closed fails the next call instead of hiding behind
  *     a large pool or behind the garbage collector.
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

  /**
    * Connections currently borrowed from the client connection pool, reported for
    * diagnosis only.
    *
    * The pool is the long running Hikari datasource held by the database driver
    * (DB_PostgreSQL.datasourceLongRunning, reached through DB.getDatabase()); the
    * private field is read reflectively so no datasource is created as a side
    * effect. None means there is no Hikari pool (a JNDI datasource can be
    * non-Hikari).
    *
    * This is never asserted: the statement proxy is finalized by the garbage
    * collector, and finalizing it returns the borrowed connection to the pool, so
    * a callout that forgets to close only holds the connection until the next
    * collection. A measured count cannot prove that here, and a passing assertion
    * built on it would be false confidence for the reviewer.
    */
  private def borrowedPoolConnections: Option[Int] =
    Try {
      val driver = DB.getDatabase
      val field = driver.getClass.getDeclaredField("datasourceLongRunning")
      field.setAccessible(true)
      field.get(driver)
    } match {
      case Success(pool: HikariDataSource) => Some(pool.getHikariPoolMXBean.getActiveConnections)
      case _ => None
    }

  /**
    * The long running pool of the login database driver, so the scenario that
    * swaps it can put it back.
    *
    * The private field of DB_PostgreSQL is reached reflectively because reading it
    * is the only way to know whether a pool already exists without creating one as
    * a side effect.
    */
  private def longRunningPoolField: Option[Field] =
    Try {
      val field = DB.getDatabase.getClass.getDeclaredField("datasourceLongRunning")
      field.setAccessible(true)
      field
    }.toOption

  /**
    * The login connection, reached reflectively for its url and credentials so the
    * scenario can build its own pool without changing the login configuration.
    */
  private def loginConnection: Option[CConnection] =
    Try {
      val field = classOf[DB].getDeclaredField("s_cc")
      field.setAccessible(true)
      field.get(null).asInstanceOf[CConnection]
    }.toOption

  /**
    * A pool of one connection, borrowing with a short timeout.
    *
    * A single slot is what turns the leak of issue #4452 into a failure: the
    * pre-fix return paths hand back a statement that was never closed, and the
    * pooled connection stays borrowed until that statement is garbage collected,
    * so the next callout cannot borrow a connection. Closing on every path, as the
    * fix does, returns the slot immediately and the loop runs.
    */
  private def singleConnectionPool(connection: CConnection): HikariDataSource = {
    val config = new HikariConfig()
    config.setDriverClassName(DB_PostgreSQL.DRIVER)
    config.setJdbcUrl(connection.getConnectionURL)
    config.setUsername(connection.getDbUid)
    config.setPassword(connection.getDbPwd)
    config.setMaximumPoolSize(1)
    config.setMinimumIdle(0)
    config.setConnectionTimeout(2000)
    config.setPoolName("PR4453SingleConnection")
    new HikariDataSource(config)
  }

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

    Scenario("repeated callouts keep returning the right quantities") {
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

      Then("every call returns the right quantity")
      assert(ok, "every callout must return the summed book quantity of 30")
      assert(lastQuantity.compareTo(BigDecimal.ZERO) == 0,
        "the no-rows path must keep returning zero")
      And("the suite transaction is still active after the loop")
      val trx = Trx.get(trxName, false)
      assert(trx.isActive)
      //	Why there is no assertion on borrowed pool connections: the statement
      //	proxy is finalized by the garbage collector and finalizing it returns the
      //	borrowed connection to the pool, so the leaked connections of the pre-fix
      //	code are released before any sample can be taken. Measured over these 60
      //	callouts, the borrowed count stays flat on the fixed and the leaking code
      //	alike, so such an assertion would pass either way and prove nothing.
      //	What this spec pins instead is observable behaviour on every return path
      //	of the issue; closing the resources is what the try-with-resources headers
      //	guarantee structurally, without waiting for a collection.
      borrowedPoolConnections.foreach { borrowed =>
        info(s"borrowed pool connections after 60 callouts: $borrowed (diagnostic only, not asserted)")
      }
    }

    Scenario("a pool of one connection fails when a callout keeps its connection") {
      Given("a locator with stored quantity for the product Oak, and one without")
      val locator = committedLocator("PR4453 OneConn")
      val oak = getProductByValue("Oak")
      committedStorage(locator.getM_Locator_ID, oak.getM_Product_ID, 0, new BigDecimal("10"))
      //	The second locator drives the no-row return path, the other leak named in
      //	the issue: pre-fix it returned new BigDecimal(0) without closing either.
      val emptyLocatorId = committedLocator("PR4453 OneConn Empty").getM_Locator_ID
      val field = longRunningPoolField
      val connection = loginConnection
      assume(field.isDefined && connection.isDefined,
        "the login driver exposes no long running pool and connection to swap")

      //	One slot, borrowed with a short timeout: a statement returned without being
      //	closed keeps that slot until the garbage collector finalizes the proxy, so
      //	the next callout cannot borrow a connection. This is the discriminator:
      //	the fix must pass, the leak must fail. It depends on neither a pool count
      //	nor on a collection: the statement is still reachable when the next borrow
      //	happens, so no collector can rescue the leaking code.
      val temporaryPool = singleConnectionPool(connection.get)
      val originalPool = field.get.get(DB.getDatabase)
      field.get.set(DB.getDatabase, temporaryPool)
      try {
        When("the callout runs repeatedly over both leaking paths")
        var failure: Throwable = null
        var calls = 0
        try {
          for (_ <- 1 to 10) {
            //	the non-null quantity path, then the no-row path
            val found = setQtyBook(0, oak.getM_Product_ID, locator.getM_Locator_ID)
            assert(found.compareTo(new BigDecimal("10")) == 0,
              s"expected the stored quantity 10 but got $found")
            val missing = setQtyBook(0, oak.getM_Product_ID, emptyLocatorId)
            assert(missing.compareTo(BigDecimal.ZERO) == 0,
              s"the no-row path must return zero but got $missing")
            calls += 2
          }
        } catch {
          case e: Throwable => failure = e
        }
        val borrowed = temporaryPool.getHikariPoolMXBean.getActiveConnections
        val served = temporaryPool.getHikariPoolMXBean.getTotalConnections

        Then("every call returns and no borrow ever times out")
        info(s"single slot: served=$served borrowed=$borrowed completed=$calls of 20 callouts")
        //	Without this the scenario would be vacuous: a pool that never created a
        //	connection proves nothing about who answered the callouts.
        assert(served > 0, "the single-slot pool must have served the callouts")
        if (failure != null)
          fail(s"a callout could not borrow the only pooled connection after $calls calls, "
            + s"because a previous one was returned without closing its statement: $failure")
        assert(calls == 20, s"every callout must have returned, completed $calls of 20")
        assert(borrowed == 0, s"no connection may stay borrowed, got $borrowed")
      } finally {
        //	Hand the login pool back before anything else in the suite runs, and
        //	close the temporary one so its single backend does not linger.
        field.get.set(DB.getDatabase, originalPool)
        Try(temporaryPool.close())
      }
    }
  }
}
