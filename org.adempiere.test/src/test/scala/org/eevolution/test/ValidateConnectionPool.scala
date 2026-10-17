/** Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
  * This program is free software: you can redistribute it and/or modify
  * it under the terms of the GNU Affero General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * This program is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU Affero General Public License for more details.
  * You should have received a copy of the GNU Affero General Public License
  * along with this program.  If not, see <http://www.gnu.org/licenses/>.
  * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
  * Created by victor.perez@e-evolution.com , www.e-evolution.com
  */

package org.eevolution.test

import java.sql.Connection

import com.zaxxer.hikari.HikariDataSource
import org.compiere.db.{CConnection, DB_Oracle, DB_PostgreSQL}
import org.compiere.model.MQuery
import org.compiere.print.{DataEngine, MPrintFormat}
import org.compiere.util.{DB, Env, Language, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.util.Try

/** Tests of the connection layer: what a checkout from the Hikari pool costs and which state it hands out.
  *
  * Round trips are observed through pg_stat_statements (PostgreSQL): the `SET SESSION CHARACTERISTICS` that
  * `setTransactionIsolation` sends and the `SHOW TRANSACTION ISOLATION LEVEL` that `getTransactionIsolation` sends.
  * The statistics are read through a driver connection that does not come from the pool, so reading them does not
  * add the round trips that are being counted. The scenarios that count are cancelled without pg_stat_statements.
  *
  * The state scenarios rely on Hikari resetting autoCommit, isolation and uncommitted work when a connection returns to
  * the pool: a connection that was dirty must come back clean.
  *
  * The last feature is an opt-in measurement (ADEMPIERE_BENCHMARK=true) of what the connection layer costs a report whose
  * definition is read from the dictionary on every run (definition cache off); it asserts nothing about time.
  *
  * The pool property scenarios build a second pool with `new DB_PostgreSQL()` (the pool of the test JVM is not touched)
  * and close it again.
  */
class ValidateConnectionPool extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  private val PoolPrefix = "org.adempiere.db.pool."
  private val PoolProperties = Vector("maximumPoolSize", "minimumIdle", "idleTimeout", "keepaliveTime",
    "connectionTimeout", "leakDetectionThreshold", "maxLifetime")

  private def check(condition: Boolean, message: => String = "assertion failed"): Unit =
    if (!condition) fail(message)

  private def isolationName(level: Int): String = CConnection.getTransactionIsolationInfo(level)

  // ---- pg_stat_statements --------------------------------------------------------------------------------------

  /** a connection that does not come from the pool, so the statistics reads leave no checkout behind */
  private def withDriverConnection[T](body: Connection => T): T = {
    val connection = DB.getDatabase.getDriverConnection(CConnection.get())
    try body(connection)
    finally connection.close()
  }

  /** calls of the statements that start with `prefix`; None without PostgreSQL or pg_stat_statements */
  private def calls(prefix: String): Option[Long] =
    if (!DB.isPostgreSQL) None
    else Try(withDriverConnection { connection =>
      val statement = connection.prepareStatement(
        "SELECT COALESCE(SUM(calls), 0) FROM pg_stat_statements " +
          "WHERE dbid = (SELECT oid FROM pg_database WHERE datname = current_database()) AND query ILIKE ?")
      try {
        statement.setString(1, prefix + "%")
        val rs = statement.executeQuery()
        try { rs.next(); rs.getLong(1) } finally rs.close()
      } finally statement.close()
    }).toOption

  private val SetIsolation = "SET SESSION CHARACTERISTICS"
  private val ShowIsolation = "SHOW TRANSACTION ISOLATION LEVEL"

  private case class RoundTrips(set: Long, show: Long)

  private def roundTrips(): RoundTrips = RoundTrips(calls(SetIsolation).get, calls(ShowIsolation).get)

  /** pg_stat_statements counts the whole database: another session can only add calls, never remove them. The work is
    * measured up to five times and the quietest run is kept, so a regression (calls on every run) is still seen. */
  private def measure(work: => Unit): RoundTrips = {
    val runs = Iterator.continually {
      val before = roundTrips()
      work
      val after = roundTrips()
      RoundTrips(after.set - before.set, after.show - before.show)
    }
    val seen = scala.collection.mutable.ArrayBuffer.empty[RoundTrips]
    runs.take(5).takeWhile { run => seen += run; run.set != 0 || run.show != 0 }.foreach(_ => ())
    seen.minBy(r => r.set + r.show)
  }

  // ---- state of a checkout -----------------------------------------------------------------------------------------

  private case class State(pid: Int, autoCommit: Boolean, isolation: Int)

  private def stateOf(connection: Connection): State = {
    val statement = connection.createStatement()
    val pid =
      try {
        val rs = statement.executeQuery("SELECT pg_backend_pid()")
        try { rs.next(); rs.getInt(1) } finally rs.close()
      } finally statement.close()
    State(pid, connection.getAutoCommit, connection.getTransactionIsolation)
  }

  private def scalar(connection: Connection, sql: String): String = {
    val statement = connection.createStatement()
    try {
      val rs = statement.executeQuery(sql)
      try { rs.next(); rs.getString(1) } finally rs.close()
    } finally statement.close()
  }

  /** state of the next default (read only) checkouts; closes them right away */
  private def readOnlyCheckouts(count: Int): Vector[State] =
    (1 to count).toVector.map { _ =>
      val connection = DB.getConnectionRO
      try stateOf(connection) finally connection.close()
    }

  private def readWriteCheckout(): State = {
    val connection = DB.getConnectionRW
    try stateOf(connection) finally connection.close()
  }

  private def poolOf(database: org.compiere.db.AdempiereDatabase): HikariDataSource =
    database.getDataSource(CConnection.get()).asInstanceOf[HikariDataSource]

  Feature("Checkout from the pool outside a transaction") {

    Scenario("A statement outside a transaction costs no isolation round trip") {
      assume(calls(SetIsolation).isDefined, "pg_stat_statements is not available: the round trips cannot be observed")
      Given("a warm pool")
      DB.getSQLValue(null, "SELECT COUNT(*) FROM AD_System")
      When("50 statements run outside a transaction")
      val sent = measure((1 to 50).foreach(_ => DB.getSQLValue(null, "SELECT COUNT(*) FROM AD_System")))
      info(s"50 statements: +${sent.set} SET SESSION CHARACTERISTICS, +${sent.show} SHOW TRANSACTION ISOLATION LEVEL")
      Then("no SET SESSION CHARACTERISTICS and no SHOW TRANSACTION ISOLATION LEVEL were sent")
      check(sent.set == 0, s"SET SESSION CHARACTERISTICS: ${sent.set} calls")
      check(sent.show == 0, s"SHOW TRANSACTION ISOLATION LEVEL: ${sent.show} calls")
    }

    Scenario("A read write checkout and a read only checkout send no isolation round trip") {
      assume(calls(SetIsolation).isDefined, "pg_stat_statements is not available: the round trips cannot be observed")
      def checkout(connection: Connection): Unit = try check(connection.getAutoCommit) finally connection.close()
      checkout(DB.getConnectionRO)
      val sent = measure((1 to 20).foreach { _ => checkout(DB.getConnectionRO); checkout(DB.getConnectionRW) })
      info(s"40 checkouts: +${sent.set} SET SESSION CHARACTERISTICS, +${sent.show} SHOW TRANSACTION ISOLATION LEVEL")
      check(sent.set == 0 && sent.show == 0)
    }

    Scenario("Another isolation level is still set when it is requested") {
      val connection = DB.createConnection(true, Connection.TRANSACTION_SERIALIZABLE)
      try check(connection.getTransactionIsolation == Connection.TRANSACTION_SERIALIZABLE, "the requested isolation was not given")
      finally connection.close()
    }
  }

  Feature("State of a connection after it returns to the pool") {

    Scenario("A transaction that commits leaves a connection with autoCommit and READ COMMITTED") {
      val trx = Trx.get(Trx.createTrxName("ValidateConnectionPool_"), true)
      val inTrx = stateOf(trx.getConnection)
      check(!inTrx.autoCommit, "a Trx connection must not autocommit")
      check(inTrx.isolation == Connection.TRANSACTION_READ_COMMITTED)
      check(trx.commit(true))
      trx.close()
      val next = readOnlyCheckouts(5) :+ readWriteCheckout()
      info(s"transaction connection pid ${inTrx.pid}; next checkouts: ${next.map(s => s"${s.pid}/${s.autoCommit}/${isolationName(s.isolation)}").mkString(", ")}")
      check(next.exists(_.pid == inTrx.pid), "the connection of the transaction was not observed again")
      Then("every next checkout, the one of the transaction included, has autoCommit and READ COMMITTED")
      check(next.forall(_.autoCommit), "autoCommit=false leaked to the next checkout")
      check(next.forall(_.isolation == Connection.TRANSACTION_READ_COMMITTED), "the isolation leaked to the next checkout")
    }

    Scenario("A transaction that rolls back leaves a connection with autoCommit and READ COMMITTED, and no data") {
      val original = DB.getSQLValueString(null, "SELECT Description FROM AD_System")
      val marker = "ValidateConnectionPool rolled back"
      val trx = Trx.get(Trx.createTrxName("ValidateConnectionPool_"), true)
      val inTrx = stateOf(trx.getConnection)
      val updated = DB.executeUpdateEx("UPDATE AD_System SET Description = ?", Array[Object](marker), trx.getTrxName)
      check(updated == 1)
      check(DB.getSQLValueString(trx.getTrxName, "SELECT Description FROM AD_System") == marker, "the change is not visible in its own transaction")
      check(trx.rollback(true))
      trx.close()
      val next = readOnlyCheckouts(5) :+ readWriteCheckout()
      check(next.exists(_.pid == inTrx.pid), "the connection of the transaction was not observed again")
      check(next.forall(_.autoCommit) && next.forall(_.isolation == Connection.TRANSACTION_READ_COMMITTED))
      Then("the rolled back data is not visible")
      check(DB.getSQLValueString(null, "SELECT Description FROM AD_System") == original, "rolled back data is visible")
    }

    Scenario("A connection closed with uncommitted work and autoCommit=false comes back clean") {
      val original = DB.getSQLValueString(null, "SELECT Description FROM AD_System")
      val dirty = DB.createConnection(false, Connection.TRANSACTION_READ_COMMITTED)
      val inUse = stateOf(dirty)
      val statement = dirty.prepareStatement("UPDATE AD_System SET Description = ?")
      statement.setString(1, "ValidateConnectionPool never committed")
      check(statement.executeUpdate() == 1)
      statement.close()
      dirty.close() // no commit, no rollback: the pool has to discard the work
      val next = readOnlyCheckouts(5)
      check(next.exists(_.pid == inUse.pid), "the dirty connection was not observed again")
      check(next.forall(_.autoCommit) && next.forall(_.isolation == Connection.TRANSACTION_READ_COMMITTED))
      check(DB.getSQLValueString(null, "SELECT Description FROM AD_System") == original, "uncommitted data is visible")
    }

    Scenario("A SERIALIZABLE checkout does not leak its isolation to the next default checkout") {
      val serializable = DB.createConnection(false, Connection.TRANSACTION_SERIALIZABLE)
      val inUse = stateOf(serializable)
      check(inUse.isolation == Connection.TRANSACTION_SERIALIZABLE, "the requested isolation was not given")
      check(!inUse.autoCommit)
      serializable.close()
      val next = readOnlyCheckouts(5) :+ readWriteCheckout()
      info(s"serializable pid ${inUse.pid}; next: ${next.map(s => s"${s.pid}/${isolationName(s.isolation)}").mkString(", ")}")
      check(next.exists(_.pid == inUse.pid), "the SERIALIZABLE connection was not observed again")
      check(next.forall(_.isolation == Connection.TRANSACTION_READ_COMMITTED), "SERIALIZABLE leaked to the next checkout")
      check(next.forall(_.autoCommit))
    }

    Scenario("A connection requested with autoCommit=false and the default isolation is given as requested") {
      val connection = DB.getConnectionID
      try {
        val state = stateOf(connection)
        check(!state.autoCommit && state.isolation == Connection.TRANSACTION_READ_COMMITTED)
      } finally connection.close()
    }
  }

  Feature("Visible in the database") {

    Scenario("pg_stat_activity names the pool") {
      assume(DB.isPostgreSQL, "ApplicationName is a PostgreSQL property")
      val connection = DB.getConnectionRO
      try {
        val name = scalar(connection, "SELECT application_name FROM pg_stat_activity WHERE pid = pg_backend_pid()")
        info(s"application_name = $name")
        check(name == "ADempiere AdempiereDS", s"application_name = '$name'")
      } finally connection.close()
    }
  }

  Feature("Pool settings") {

    Scenario("The pool of the JVM keeps the settings it always had, plus the connection defaults") {
      assume(DB.isPostgreSQL, "the pool settings are asserted on the PostgreSQL pool")
      val pool = poolOf(DB.getDatabase)
      info(s"max ${pool.getMaximumPoolSize}, minIdle ${pool.getMinimumIdle}, idle ${pool.getIdleTimeout}, keepalive ${pool.getKeepaliveTime}, " +
        s"timeout ${pool.getConnectionTimeout}, leak ${pool.getLeakDetectionThreshold}, lifetime ${pool.getMaxLifetime}")
      check(pool.getPoolName == "AdempiereDS")
      check(pool.getMaximumPoolSize == 150)
      check(pool.getMinimumIdle == 15)
      check(pool.getIdleTimeout == 60000L)
      check(pool.getKeepaliveTime == 30000L)
      check(pool.getConnectionTimeout == 30000L, "Hikari default")
      check(pool.getLeakDetectionThreshold == 0L, "leak detection is off by default")
      check(pool.getMaxLifetime == 1800000L, "Hikari default")
      check(pool.isAutoCommit)
      check(pool.getTransactionIsolation == "TRANSACTION_READ_COMMITTED", s"transactionIsolation = ${pool.getTransactionIsolation}")
      check(pool.getConnectionTestQuery == null, "JDBC4 isValid must validate the connections")
      val properties = pool.getDataSourceProperties
      check(!properties.containsKey("cachePrepStmts") && !properties.containsKey("prepStmtCacheSize") && !properties.containsKey("prepStmtCacheSqlLimit"),
        "MySQL only properties on a PostgreSQL pool")
      check(properties.getProperty("ApplicationName") == "ADempiere AdempiereDS")
    }

    Scenario("System properties override the settings of a new pool") {
      assume(DB.isPostgreSQL, "the pool settings are asserted on the PostgreSQL pool")
      val overrides = Map("maximumPoolSize" -> "7", "minimumIdle" -> "2", "idleTimeout" -> "20000", "keepaliveTime" -> "45000",
        "connectionTimeout" -> "5000", "leakDetectionThreshold" -> "7000", "maxLifetime" -> "900000")
      check(overrides.keySet == PoolProperties.toSet)
      overrides.foreach { case (name, value) => System.setProperty(PoolPrefix + name, value) }
      try {
        val pool = poolOf(new DB_PostgreSQL())
        try {
          info(s"max ${pool.getMaximumPoolSize}, minIdle ${pool.getMinimumIdle}, idle ${pool.getIdleTimeout}, keepalive ${pool.getKeepaliveTime}, " +
            s"timeout ${pool.getConnectionTimeout}, leak ${pool.getLeakDetectionThreshold}, lifetime ${pool.getMaxLifetime}")
          check(pool.getMaximumPoolSize == 7)
          check(pool.getMinimumIdle == 2)
          check(pool.getIdleTimeout == 20000L)
          check(pool.getKeepaliveTime == 45000L)
          check(pool.getConnectionTimeout == 5000L)
          check(pool.getLeakDetectionThreshold == 7000L)
          check(pool.getMaxLifetime == 900000L)
          Then("the overridden pool serves connections with the same defaults")
          val connection = pool.getConnection
          try {
            check(connection.getAutoCommit && connection.getTransactionIsolation == Connection.TRANSACTION_READ_COMMITTED)
            check(scalar(connection, "SELECT application_name FROM pg_stat_activity WHERE pid = pg_backend_pid()") == "ADempiere AdempiereDS")
          } finally connection.close()
        } finally pool.close()
      } finally overrides.keys.foreach(name => System.clearProperty(PoolPrefix + name))
    }

    Scenario("A value that is not a number is ignored and the default stays") {
      assume(DB.isPostgreSQL, "the pool settings are asserted on the PostgreSQL pool")
      System.setProperty(PoolPrefix + "maximumPoolSize", "many")
      System.setProperty(PoolPrefix + "minimumIdle", "1")
      try {
        val pool = poolOf(new DB_PostgreSQL())
        try check(pool.getMaximumPoolSize == 150)
        finally pool.close()
      } finally { System.clearProperty(PoolPrefix + "maximumPoolSize"); System.clearProperty(PoolPrefix + "minimumIdle") }
    }
  }
  Feature("The short running pool AdempiereSRDS") {

    Scenario("Oracle takes its short running connection from the short running pool, not from the long running one") {
      val asked = scala.collection.mutable.ArrayBuffer.empty[String]
      val oracle = new DB_Oracle {
        override def getDataSource(connection: CConnection): javax.sql.DataSource = { asked += "AdempiereDS"; null }
        override def getDataSourceShortRunning(connection: CConnection): javax.sql.DataSource = { asked += "AdempiereSRDS"; null }
      }
      When("Oracle is asked for a short running connection and no pool can be built")
      val outcome = Try(oracle.getFromConnectionPoolShortRunning(CConnection.get(), true, Connection.TRANSACTION_READ_COMMITTED))
      Then("only the short running pool was asked for (the long running one is never built for it)")
      info(s"pools asked for: ${asked.mkString(", ")}; outcome: $outcome")
      check(asked.toList == List("AdempiereSRDS"), s"pools asked for: ${asked.mkString(", ")}")
    }
  }

  Feature("Cost of the connection layer for a report (opt-in benchmark)") {

    Scenario("A cold getPrintData with the definition cache off, in statements and milliseconds") {
      assume(sys.env.get("ADEMPIERE_BENCHMARK").exists(_.trim.equalsIgnoreCase("true")), "opt-in: set ADEMPIERE_BENCHMARK=true")
      assume(calls(SetIsolation).isDefined, "pg_stat_statements is not available: the statements cannot be counted")
      def totalCalls(): Long = withDriverConnection { connection =>
        scalar(connection, "SELECT COALESCE(SUM(calls), 0) FROM pg_stat_statements WHERE dbid = " +
          "(SELECT oid FROM pg_database WHERE datname = current_database()) AND query NOT ILIKE '%pg_stat_statements%'").toLong
      }
      def median(values: Seq[Double]): Double = values.sorted.apply(values.size / 2)
      val formats = Vector(1000003 -> "C_Order", 1000004 -> "RV_OrderDetail", 1000008 -> "C_OrderLine", 1000007 -> "Fact_Acct")
      val runs = 10
      var total = 0.0
      formats.foreach { case (id, name) =>
        val format = MPrintFormat.get(Env.getCtx, id, true)
        def run(definitionOnly: Boolean): Double = {
          val query = new MQuery()
          if (definitionOnly) query.addRestriction("1=2")
          val engine = new DataEngine(Language.getLanguage("en_US"), null)
          engine.setLookupJoin(true)
          engine.setDefinitionCache(false) // the dictionary is read on every run
          val start = System.nanoTime
          engine.getPrintData(Env.getCtx, format, query, false, 0)
          (System.nanoTime - start) / 1e6
        }
        (1 to 3).foreach(_ => run(definitionOnly = false)) // warm the pool, the JIT and the plan caches
        val set0 = roundTrips()
        val statements0 = totalCalls()
        val times = (1 to runs).map(_ => run(definitionOnly = false))
        val statements = totalCalls() - statements0
        val set1 = roundTrips()
        val definition = (1 to runs).map(_ => run(definitionOnly = true))
        info(f"$name: ${statements / runs.toDouble}%.1f statements per run (SET SESSION CHARACTERISTICS ${(set1.set - set0.set) / runs.toDouble}%.1f, " +
          f"SHOW TRANSACTION ISOLATION LEVEL ${(set1.show - set0.show) / runs.toDouble}%.1f), getPrintData median ${median(times)}%.1f ms, definition only median ${median(definition)}%.1f ms")
        total += median(times)
      }
      info(f"sum of the four medians: $total%.1f ms")
    }
  }
}
