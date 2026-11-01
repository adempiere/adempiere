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

import org.compiere.util.{CLogMgt, LoggingSnapshot}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen

import java.util.logging.{Level, Logger}

/**
  * Regression for the review finding on PR adempiere#4470: a suite that
  * silences logging must give back what it found, not a guessed level.
  *
  * Adempiere.startup applies the configured Ini.P_TRACELEVEL, so the level
  * on entry is not fixed at SEVERE; restoring SEVERE silently changes the
  * logging of every later suite in the same JVM.
  *
  * No database is needed: the scenarios drive the logging configuration
  * directly, and every scenario restores the state it mutated.
  */
class ValidateLoggingRestore extends AnyFeatureSpec
  with BeforeAndAfterAll
  with GivenWhenThen {

  override def beforeAll(): Unit = {
    //\tHandlers exist after initialize; no database is touched by it.
    CLogMgt.initialize(true)
    assert(LoggingSnapshot.handlers.nonEmpty,
      "CLogMgt must expose at least one handler after initialize")
  }

  Feature("a silenced suite restores the logging state it found") {

    Scenario("handlers keep their own original level even when they differ from each other") {
      Given("handlers with different levels")
      val handlers = LoggingSnapshot.handlers
      handlers.head.setLevel(Level.INFO)
      if (handlers.length > 1)
        handlers(1).setLevel(Level.WARNING)
      val expected = handlers.map(_.getLevel)
      When("the suite silences logging and then restores its snapshot")
      val snapshot = LoggingSnapshot.capture()
      LoggingSnapshot.silence()
      //	CLogErrorBuffer maps OFF to SEVERE by design, so the silence is proven
      //	on any handler that does take it.
      assert(handlers.exists(_.getLevel == Level.OFF),
        "silence must have turned at least one handler off, or the restore proves nothing")
      snapshot.restore()
      Then("every handler comes back to the level it had before the silence")
      handlers.zip(expected).foreach { case (handler, level) =>
        assert(handler.getLevel == level,
          "a hardcoded SEVERE restore would leave this handler on the wrong level")
      }
    }

    Scenario("a logger without an explicit level comes back inheriting, not pinned") {
      Given("a logger that inherits its level from its parent")
      val inherited = Logger.getLogger("org.eevolution.test.restore.inherited")
      inherited.setLevel(null)
      val pinned = Logger.getLogger("org.eevolution.test.restore.pinned")
      pinned.setLevel(Level.INFO)
      When("the suite silences logging and then restores its snapshot")
      val snapshot = LoggingSnapshot.capture()
      LoggingSnapshot.silence()
      assert(inherited.getLevel == Level.OFF && pinned.getLevel == Level.OFF,
        "silence must have turned both loggers off")
      snapshot.restore()
      Then("the inherited logger is not pinned to a concrete level")
      assert(inherited.getLevel == null,
        "restoring a fixed SEVERE would pin a logger that was inheriting")
      And("the pinned logger keeps the level it was pinned to")
      assert(pinned.getLevel == Level.INFO,
        "the explicit level of this logger must survive the silence")
    }

    Scenario("the aggregate CLogMgt level is restored, not left silenced") {
      Given("the aggregate level the suite found on entry")
      val found = CLogMgt.getLevel
      assert(found != null, "CLogMgt must report an aggregate level")
      When("the suite silences logging and then restores its snapshot")
      val snapshot = LoggingSnapshot.capture()
      LoggingSnapshot.silence()
      assert(CLogMgt.getLevel == Level.OFF,
        "silence must have moved the aggregate level to OFF")
      snapshot.restore()
      Then("the aggregate level is the one found before the silence")
      assert(CLogMgt.getLevel == found,
        "a hardcoded SEVERE restore would report a different aggregate level")
    }
  }
}
