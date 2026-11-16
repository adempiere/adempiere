/** ****************************************************************************
  * Product: ADempiere ERP & CRM Smart Business Solution                       *
  * Copyright (C) 2006-2026 ADempiere Foundation, All Rights Reserved.         *
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
  * or via info@adempiere.net or http://www.adempiere.net/license.html         *
  * ****************************************************************************/

package org.eevolution.test

import java.util.logging.Level

import org.adempiere.Adempiere
import org.adempiere.util.ProcessUtil
import org.compiere.process.{ProcessCall, ProcessInfo}
import org.compiere.util.{CLogMgt, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.BeforeAndAfterEach
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression tests for the transaction ownership of
  * [[org.adempiere.util.ProcessUtil#startJavaProcess(java.util.Properties,org.compiere.process.ProcessInfo,org.compiere.util.Trx)]]
  * (adempiere/adempiere issue #4281).
  *
  * `ProcessInfo.isManagedTransaction()` is the flag that says who owns the
  * transaction a process runs in. When it is `true`, `ProcessUtil` owns it: it
  * commits on success and, in a `finally`, rolls back on failure and closes it.
  * When it is `false`, the caller owns it and decides what becomes of it.
  *
  * The three argument overload used to delegate with the flag hard-coded to
  * `true`, so a caller that had declared the transaction unmanaged lost it
  * anyway: `Trx.close()` commits and also removes the name from the `Trx`
  * cache, which leaves the caller holding a transaction that is no longer
  * registered, and any later commit, rollback, savepoint or close from that
  * caller acts on nothing. The overload must read the flag from the Process
  * Info instead.
  *
  * The rule is pinned as a differential pair, started through the public
  * interface only ([[org.compiere.process.ProcessInfo]] plus
  * [[org.adempiere.util.ProcessUtil]], never the four argument overload), and
  * each scenario asserts observables a caller can actually see:
  *
  *  - the return value and `ProcessInfo.isError`, proving the process really
  *    ran and succeeded under both flags, so the pair cannot pass because the
  *    process was skipped;
  *  - [[org.compiere.util.Trx#get(java.lang.String,%20boolean)]] with
  *    `createNew = false`, which is the public way to ask whether a named
  *    transaction is still registered. A closed transaction is removed from
  *    the cache, so `null` here means "someone closed it";
  *  - whether the caller can still roll the transaction back itself, which is
  *    the whole point of declaring it unmanaged.
  *
  * Each scenario works on its own freshly opened transaction and never on the
  * suite transaction: a managed scenario closes whatever it is handed, so
  * sharing the suite transaction would tear the suite down under the scenarios
  * that follow. The stub process writes nothing, and every transaction opened
  * here is rolled back and closed again in `afterEach`, so no fixture and no
  * registered transaction survives a scenario.
  *
  * @author ADempiere Foundation
  */
class ValidateProcessUtilManagedTransaction extends AnyFeatureSpec
  with AdempiereTestCase
  with GivenWhenThen
  with BeforeAndAfterEach {

  override def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.OFF)
    CLogMgt.setLoggerLevel(Level.OFF, null)

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
  }    //	startup

  /** Names of the transactions this suite opened and has not closed yet. */
  private var opened: List[String] = Nil

  /**
    * Open a transaction of the scenario's own, on a real pooled connection so
    * that it is active and so a rollback really rolls something back.
    *
    * @return the registered transaction, already tracked for cleanup
    */
  private def openOwnTransaction(): Trx = {
    val name = Trx.createTrxName("PUTrx")
    val trx = Trx.get(name, true)
    assert(trx != null, "test fixture could not register transaction " + name)
    trx.getConnection()
    assert(trx.isActive, "test fixture transaction " + name + " is not active")
    opened = name :: opened
    trx
  }

  /** Close a tracked transaction if it is still registered, ignoring doubles. */
  private def closeIfRegistered(name: String): Unit = {
    val trx = Trx.get(name, false)
    if (trx != null) {
      trx.rollback()
      trx.close()
    }
  }

  override def afterEach(): Unit = {
    //	Never leak a registered transaction into the next scenario, not even
    //	when the scenario failed halfway through.
    opened.foreach(closeIfRegistered)
    opened = Nil
    super.afterEach()
  }    //	afterEach

  /**
    * A Process Info that runs [[ProcessUtilManagedTransactionStub]] instead of
    * a real process. Supplying the class name is what keeps this spec free of
    * any `AD_Process` fixture: `ProcessUtil` only reads the process definition
    * from the database when the Process Info carries no class name at all.
    *
    * @param managedTransaction the ownership flag under test
    * @return a Process Info ready to start
    */
  private def processInfoForStub(managedTransaction: Boolean): ProcessInfo = {
    val pi = new ProcessInfo("ProcessUtil managed transaction", 0)
    pi.setClassName(classOf[ProcessUtilManagedTransactionStub].getName)
    pi.setManagedTransaction(managedTransaction)
    pi
  }

  Feature("the caller keeps the transaction it declared unmanaged") {

    Scenario("an unmanaged process leaves the caller's transaction registered, active and rollable") {
      Given("a Process Info that declares the transaction is managed by the caller")
      val pi = processInfoForStub(managedTransaction = false)
      assert(!pi.isManagedTransaction,
        "the fixture must start with IsManagedTransaction = false to pin #4281")

      And("a transaction of this scenario's own, active on its own connection")
      val trx = openOwnTransaction()
      val name = trx.getTrxName()
      val before = ProcessUtilManagedTransactionStub.invocations

      try {
        When("the process is started through the three argument overload")
        val started = ProcessUtil.startJavaProcess(Context, pi, trx)

        Then("the process ran and reported no error")
        assert(started,
          "#4281: the unmanaged process must still start successfully")
        assert(!pi.isError,
          "#4281: the unmanaged process ended with an error: " + pi.getSummary)
        assert(ProcessUtilManagedTransactionStub.invocations == before + 1,
          "#4281: the process class was instantiated "
            + (ProcessUtilManagedTransactionStub.invocations - before)
            + " times, expected exactly one real run")

        And("the caller's transaction is still registered, and is the same one")
        val live = Trx.get(name, false)
        assert(live != null,
          "#4281: ProcessUtil closed a transaction the caller declared"
            + " unmanaged; " + name + " is no longer registered, so every"
            + " later commit, rollback or savepoint by the caller is a no-op")
        assert(live eq trx,
          "#4281: " + name + " resolved to another transaction instance")

        And("it is still active")
        assert(live.isActive,
          "#4281: the unmanaged transaction " + name
            + " is no longer active after the process returned")

        And("the caller can still roll it back, which is what ownership means")
        assert(trx.rollback(),
          "#4281: the caller could not roll back its own unmanaged transaction "
            + name + "; it had already been committed or closed by ProcessUtil")

        And("and it can close it itself")
        trx.close()
        assert(Trx.get(name, false) == null,
          "#4281: " + name + " stayed registered after the caller closed it")
      } finally {
        closeIfRegistered(name)
      }
    }    //	Scenario

    Scenario("a managed process leaves nothing for the caller to close") {
      Given("a Process Info that hands the transaction to ProcessUtil")
      val pi = processInfoForStub(managedTransaction = true)
      assert(pi.isManagedTransaction,
        "the fixture must start with IsManagedTransaction = true to pin the other half of the pair")

      And("a transaction of this scenario's own, active on its own connection")
      val trx = openOwnTransaction()
      val name = trx.getTrxName()
      val before = ProcessUtilManagedTransactionStub.invocations

      When("the process is started through the three argument overload")
      val started = ProcessUtil.startJavaProcess(Context, pi, trx)

      Then("the process ran and reported no error")
      assert(started,
        "#4281: the managed process must still start successfully")
      assert(!pi.isError,
        "#4281: the managed process ended with an error: " + pi.getSummary)
      assert(ProcessUtilManagedTransactionStub.invocations == before + 1,
        "#4281: the process class was instantiated "
          + (ProcessUtilManagedTransactionStub.invocations - before)
          + " times, expected exactly one real run")

      And("ProcessUtil closed the transaction, so it is gone from the cache")
      assert(Trx.get(name, false) == null,
        "#4281: the managed transaction " + name
          + " is still registered; ProcessUtil owns it and must close it")
    }    //	Scenario
  }    //	Feature

  Feature("a Process Info that never sets the flag keeps the old behavior") {

    Scenario("IsManagedTransaction defaults to true for both constructors") {
      Given("a Process Info built without the flag")
      val defaulted = new ProcessInfo("ProcessUtil default", 0)

      Then("the transaction is managed, as it was before the fix")
      assert(defaulted.isManagedTransaction,
        "#4281: a Process Info that never sets the flag must keep managing the"
          + " transaction, or every existing caller changes behavior")

      And("the constructor that takes the flag still honours it")
      val unmanaged = new ProcessInfo("ProcessUtil unmanaged", 0, false)
      assert(!unmanaged.isManagedTransaction,
        "the Process Info constructor ignored the managedTransaction argument")

      And("setting it back is possible, so the flag is a plain property")
      unmanaged.setManagedTransaction(true)
      assert(unmanaged.isManagedTransaction,
        "setManagedTransaction(true) did not restore ownership")
    }    //	Scenario
  }    //	Feature
}    //	ValidateProcessUtilManagedTransaction

/**
  * A process class that only records that it ran.
  *
  * It is a top level class with a public no argument constructor because
  * `ProcessUtil` builds it with
  * `processClass.getDeclaredConstructor().newInstance()`: a class nested in the
  * spec would carry the spec instance as an implicit outer argument and would
  * have no no argument constructor to find. The counter lives in the companion
  * so the spec can read it without inspecting any `ProcessUtil` internals.
  *
  * It touches no table and never commits or rolls back the transaction it is
  * handed: who closes that transaction is exactly what the spec measures, so
  * the process itself must leave it alone.
  */
class ProcessUtilManagedTransactionStub extends ProcessCall {

  override def startProcess(ctx: java.util.Properties, pi: ProcessInfo, trx: Trx): Boolean = {
    ProcessUtilManagedTransactionStub.record()
    true
  }    //	startProcess
}    //	ProcessUtilManagedTransactionStub

/** Invocation counter for [[ProcessUtilManagedTransactionStub]]. */
object ProcessUtilManagedTransactionStub {

  @volatile private var count: Int = 0

  /** How many times the stub process has run in this class loader. */
  def invocations: Int = count

  /** Called by the stub process on every run. */
  private[test] def record(): Unit = synchronized {
    count += 1
  }
}    //	ProcessUtilManagedTransactionStub
