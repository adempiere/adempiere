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
  * For the text or an alternative of this public license, you may reach us    *
  * Copyright (C) 2003-2026 e-Evolution,SC. All Rights Reserved.              *
  * Contributor(s): Victor Perez www.e-evolution.com                           *
  * ****************************************************************************/

package org.eevolution.test

import org.compiere.util.{CLogMgt, LoggingSnapshot}

import java.util.logging.{Level, Logger}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.BeforeAndAfterEach
import org.scalatest.exceptions.TestFailedException
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression guard for the logging discipline a silenced suite owes the JVM.
  *
  * A suite that silences logging must give back what it found, not a guessed
  * level, it must hand it back even when one of its own assertions fails, and
  * it must leave no trace for the suites that run after it.
  *
  * Adempiere.startup applies the configured Ini.P_TRACELEVEL, so the level on
  * entry is not fixed at SEVERE; restoring SEVERE silently changes the logging
  * of every later suite in the same JVM.
  *
  * Each scenario therefore keeps two snapshots:
  *   - preSilence, captured after the Given has built its case, so the restore
  *     is exactly what the Then asserts on (per-handler fidelity, including a
  *     logger left inheriting);
  *   - ambient, captured in beforeAll before any scenario touched a level,
  *     restored by afterEach, so a scenario that dies half way through still
  *     hands back the state it found and never makes its own levels what the
  *     next scenario inherits. The last scenario asserts that trace is really
  *     gone, so this is a guard and not a hope.
  *
  * No database is needed: the scenarios drive the logging configuration directly.
  */
class ValidateLoggingRestore extends AnyFeatureSpec
  with BeforeAndAfterAll
  with BeforeAndAfterEach
  with GivenWhenThen {

  private val inheritedName = "org.eevolution.test.restore.inherited"
  private val pinnedName = "org.eevolution.test.restore.pinned"

  //\tCaptured before any scenario changes a level, so every scenario can hand
  //\tback the state the suite found rather than the state it built.
  private var ambient: LoggingSnapshot.Snapshot = _
  private var ambientHandlers: Array[Level] = Array.empty
  private var ambientLevel: Level = _

  override def beforeAll(): Unit = {
    //\tHandlers exist after initialize; no database is touched by it.
    CLogMgt.initialize(true)
    ambient = LoggingSnapshot.capture()
    ambientHandlers = LoggingSnapshot.handlers.map(_.getLevel)
    ambientLevel = CLogMgt.getLevel
    assert(LoggingSnapshot.handlers.nonEmpty,
      "CLogMgt must expose at least one handler after initialize")
    assert(ambientLevel != null, "CLogMgt must report an aggregate level on entry")
  }

  /**
    * Hand back the ambient state the suite borrowed. Runs after every scenario,
    * so a scenario that fails half way through still leaves clean state behind.
    * Doing it here rather than at the end of each scenario matters: a scenario
    * that dies before its own cleanup would otherwise leave the next scenario
    * silenced, capturing and restoring an OFF state and proving nothing.
    */
  override def afterEach(): Unit = {
    if (ambient != null) {
      ambient.restore()
      //	Loggers created by a scenario did not exist when ambient was captured,
      //	so ambient cannot undo them; unpin them as well.
      Logger.getLogger(inheritedName).setLevel(null)
      Logger.getLogger(pinnedName).setLevel(null)
    }
  }

  override def afterAll(): Unit = afterEach()

  Feature("a silenced suite restores the logging state it found") {

    Scenario("handlers keep their own original level even when they differ from each other") {
      Given("handlers set to different levels")
      val handlers = LoggingSnapshot.handlers
      handlers.head.setLevel(Level.INFO)
      if (handlers.length > 1)
        handlers(1).setLevel(Level.WARNING)
      val expected = handlers.map(_.getLevel)
      val preSilence = LoggingSnapshot.capture()
      When("the suite silences logging and then restores its snapshot")
      try {
        LoggingSnapshot.silence()
        //	CLogErrorBuffer maps OFF to SEVERE by design, so the silence is proven
        //	on any handler that does take it.
        assert(handlers.exists(_.getLevel == Level.OFF),
          "silence must have turned at least one handler off, or the restore proves nothing")
      } finally {
        //	Restore on the failure path too: a throwing assertion above would
        //	otherwise leave the runner silenced for every later suite.
        preSilence.restore()
      }
      Then("every handler comes back to the level it had before the silence")
      handlers.zip(expected).foreach { case (handler, level) =>
        assert(handler.getLevel == level,
          "a hardcoded SEVERE restore would leave this handler on the wrong level")
      }
    }

    Scenario("a logger without an explicit level comes back inheriting, not pinned") {
      Given("a logger that inherits its level and one that is pinned")
      val inherited = Logger.getLogger(inheritedName)
      inherited.setLevel(null)
      val pinned = Logger.getLogger(pinnedName)
      pinned.setLevel(Level.INFO)
      val preSilence = LoggingSnapshot.capture()
      When("the suite silences logging and then restores its snapshot")
      try {
        LoggingSnapshot.silence()
        assert(inherited.getLevel == Level.OFF && pinned.getLevel == Level.OFF,
          "silence must have turned both loggers off")
      } finally {
        preSilence.restore()
      }
      Then("the inherited logger is not pinned to a concrete level")
      assert(inherited.getLevel == null,
        "restoring a fixed SEVERE would pin a logger that was inheriting")
      And("the pinned logger keeps the level it was pinned to")
      assert(pinned.getLevel == Level.INFO,
        "the explicit level of this logger must survive the silence")
    }

    Scenario("a failure inside the silenced section still restores logging") {
      Given("a silenced section whose assertion is bound to fail")
      val handlers = LoggingSnapshot.handlers
      val expected = handlers.map(_.getLevel)
      val preSilence = LoggingSnapshot.capture()
      When("the section silences logging and then fails")
      val failure = intercept[TestFailedException] {
        try {
          LoggingSnapshot.silence()
          assert(1 == 2, "forced failure while logging is silenced")
        } finally {
          preSilence.restore()
        }
      }
      Then("the failure is the forced assertion, not something else")
      assert(failure.getMessage.contains("forced failure while logging is silenced"),
        "unexpected failure: " + failure.getMessage)
      And("logging came back although the body never reached its end")
      handlers.zip(expected).foreach { case (handler, level) =>
        assert(handler.getLevel == level,
          "without the finally this handler would stay OFF for later suites")
      }
    }

    Scenario("the aggregate CLogMgt level is restored, not left silenced") {
      Given("the aggregate level the suite found on entry")
      val found = CLogMgt.getLevel
      val preSilence = LoggingSnapshot.capture()
      When("the suite silences logging and then restores its snapshot")
      try {
        LoggingSnapshot.silence()
        assert(CLogMgt.getLevel == Level.OFF,
          "silence must have moved the aggregate level to OFF")
      } finally {
        preSilence.restore()
      }
      Then("the aggregate level is the one found before the silence")
      assert(CLogMgt.getLevel == found,
        "a hardcoded SEVERE restore would report a different aggregate level")
    }

    Scenario("no trace is left for the suites that run next") {
      Given("the levels captured in beforeAll, before this suite touched any")
      assert(ambientHandlers.nonEmpty, "beforeAll must have captured handler levels")
      When("the scenarios above have all silenced and restored logging")
      Then("every handler is back at the level the suite found, not one it set")
      LoggingSnapshot.handlers.zip(ambientHandlers).foreach { case (handler, level) =>
        assert(handler.getLevel == level,
          "a scenario leaked level " + handler.getLevel + " where it found " + level)
      }
      And("the aggregate level is the one the suite found")
      assert(CLogMgt.getLevel == ambientLevel,
        "the suite leaked its aggregate level " + CLogMgt.getLevel +
          " where it found " + ambientLevel)
      And("the loggers the scenarios created are not left pinned")
      assert(Logger.getLogger(inheritedName).getLevel == null,
        "the inherited test logger must not survive pinned")
      assert(Logger.getLogger(pinnedName).getLevel == null,
        "the pinned test logger must not survive pinned")
    }
  }
}
