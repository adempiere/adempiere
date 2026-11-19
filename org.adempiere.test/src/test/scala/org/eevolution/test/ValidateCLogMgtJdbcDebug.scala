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

import java.sql.DriverManager
import java.util.logging.Level
import org.compiere.util.CLogMgt
import org.scalatest.BeforeAndAfterAll
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/**
  * Regression guard for CLogMgt.setLevel and the JDBC log writer.
  *
  * setLevel decided the JDBC debug state from s_currentLevel, the level of the
  * previous call, and only assigned the new level afterwards. The JDBC writer
  * therefore always lagged one call behind the level the class reported:
  *   - setLevel(ALL) from any other level left DriverManager without a writer,
  *     so a developer asking for full tracing got none and had to call twice;
  *   - coming down from ALL to INFO left the writer attached, so every JDBC call
  *     kept serialising its traffic to System.err under a level that says it
  *     should not, with the cost and the disclosure that implies.
  *
  * isLevelAll reads s_currentLevel, so it reported true while the writer was
  * still null: the API and the real state disagreed.
  *
  * Every scenario starts from a level chosen by the scenario rather than from
  * the ambient one, so the ALL transition is exercised on every run instead of
  * only when the suite happens to begin at ALL.
  *
  * No database is needed: DriverManager's log writer is process state and
  * CLogMgt.initialize(true) touches no database.
  */
class ValidateCLogMgtJdbcDebug extends AnyFeatureSpec
  with BeforeAndAfterAll
  with GivenWhenThen {

  /** The JDBC writer is process-wide; hand back what the suite found. */
  private var foundWriter: java.io.PrintWriter = _
  private var foundLevel: Level = _

  override def beforeAll(): Unit = {
    CLogMgt.initialize(true)
    foundWriter = DriverManager.getLogWriter
    foundLevel = CLogMgt.getLevel
    assert(foundLevel != null, "CLogMgt must report an aggregate level on entry")
  }

  override def afterAll(): Unit = {
    //\tLeave the writer and the level exactly as found, whatever the scenarios did.
    DriverManager.setLogWriter(foundWriter)
    if (foundLevel != null)
      CLogMgt.setLevel(foundLevel)
  }

  private def jdbcOn: Boolean = DriverManager.getLogWriter != null

  Feature("setLevel drives the JDBC log writer from the level requested") {

    Scenario("asking for ALL turns JDBC tracing on in that same call") {
      Given("a level below ALL")
      CLogMgt.setLevel(Level.INFO)
      assert(CLogMgt.getLevel == Level.INFO, "the suite must start below ALL")
      assert(!jdbcOn, "INFO must not leave a JDBC writer attached")
      When("the caller asks for ALL once")
      CLogMgt.setLevel(Level.ALL)
      Then("JDBC tracing is on without a second call")
      assert(jdbcOn,
        "setLevel(ALL) left DriverManager without a log writer: the decision was" +
          " taken from the previous level, so the caller got no JDBC tracing")
      And("the reported level agrees with the real JDBC state")
      assert(CLogMgt.isLevelAll, "isLevelAll must agree with the level just set")
      assert(CLogMgt.getLevel == Level.ALL, "getLevel must report ALL")
    }

    Scenario("coming down from ALL detaches the JDBC log writer") {
      Given("ALL, with JDBC tracing on")
      CLogMgt.setLevel(Level.ALL)
      assert(jdbcOn, "ALL must attach the JDBC writer before this scenario means anything")
      When("the caller drops to INFO")
      CLogMgt.setLevel(Level.INFO)
      Then("JDBC tracing stops")
      assert(!jdbcOn,
        "setLevel(INFO) left the JDBC writer attached: every JDBC call keeps writing" +
          " its traffic to System.err under a level that says it should not")
      And("the reported level agrees with the real JDBC state")
      assert(!CLogMgt.isLevelAll, "isLevelAll must be false once the level is INFO")
    }

    Scenario("a repeated call keeps the JDBC state steady") {
      Given("INFO")
      CLogMgt.setLevel(Level.INFO)
      When("the caller sets INFO again")
      CLogMgt.setLevel(Level.INFO)
      Then("nothing turned on")
      assert(!jdbcOn,
        "a no-op call must not flip the JDBC writer; the setJDBCDebug call sits" +
          " outside the level-changed guard")
      When("the caller sets ALL twice")
      CLogMgt.setLevel(Level.ALL)
      val afterFirst = jdbcOn
      CLogMgt.setLevel(Level.ALL)
      Then("both calls agree")
      assert(afterFirst && jdbcOn,
        "ALL must be on from the first call and stay on, not need a repeat")
      CLogMgt.setLevel(Level.INFO)
    }
  }
}
