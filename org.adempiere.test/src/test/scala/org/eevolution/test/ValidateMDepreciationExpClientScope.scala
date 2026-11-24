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
  * ****************************************************************************/

package org.eevolution.test

import java.sql.{PreparedStatement, Timestamp}
import java.util.Calendar
import java.util.logging.Level

import org.adempiere.Adempiere
import org.compiere.asset.exceptions.AssetException
import org.compiere.asset.model.MDepreciationExp
import org.compiere.util.{CLogMgt, Env, Ini, Login, Trx}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

import scala.jdk.CollectionConverters.ListHasAsScala

/**
  * Regression test for adempiere/adempiere#4299: "AssetException: There are
  * unprocessed records to date" raised while processing depreciation, even
  * though every unprocessed A_Depreciation_Exp entry of the asset belongs to a
  * different AD_Client_ID.
  *
  * Root cause: the A_Depreciation_Exp lookups in MDepreciationExp were built
  * without limiting the Query to the logged in client, so an unprocessed entry
  * of another client made the current client believe its own asset still had
  * unprocessed records. The fix scopes checkExistsNotProcessedEntries,
  * getNotProcessedEntries and getEntries with setClient_ID(), and writes
  * setProcessed with an AD_Client_ID predicate.
  *
  * The scenarios insert A_Depreciation_Exp fixture rows inside the rolled back
  * test transaction: nothing is committed. One entry belongs to a different
  * AD_Client_ID (the cross-client row that triggered the bug) and one belongs
  * to the logged in client (a control row that must still be found).
  */
class ValidateMDepreciationExpClientScope
    extends AnyFeatureSpec
    with AdempiereTestCase
    with GivenWhenThen {

  /**
    * Log in as the standard GardenWorld demo user so the context AD_Client_ID
    * is the GardenWorld client; the cross-client fixture then lives in a
    * different AD_Client_ID.
    */
  override def startup(): Unit = {
    Adempiere.startup(false)
    CLogMgt.setLevel(Level.ALL)
    CLogMgt.setLoggerLevel(Level.ALL, null)

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
  }    //  startup

  /** The logged in client (GardenWorld), read lazily so it is evaluated after
    * startup has logged in, not when the spec object is constructed.
    */
  private lazy val currentClientId: Int = Env.getAD_Client_ID(Context)
  /** A client that is guaranteed to differ from the logged in one. */
  private lazy val otherClientId: Int = if (currentClientId == 0) 1 else 0

  /** The next free ids of one table, read inside `trx`, so the fixture cannot
    * collide with rows a developer already has in their GardenWorld.
    */
  private def nextIds(tableName: String, idColumn: String, count: Int): List[Int] = {
    val highest = org.compiere.util.DB.getSQLValue(
      trxName, s"SELECT COALESCE(MAX($idColumn), 0) FROM $tableName")
    (1 to count).map(offset => highest + offset).toList
  }    //  nextIds

  /** Borrows a prepared statement bound to `trx`, runs `body` over it, and closes it. */
  private def withStatement[A](sql: String)(body: PreparedStatement => A): A = {
    val trx = Trx.get(trxName, true)
    val statement = trx.getConnection.prepareStatement(sql)
    try body(statement)
    finally statement.close()
  }    //  withStatement

  /** Runs one seed statement and insists it touched exactly one row. */
  private def insert(sql: String): Unit =
    withStatement(sql) { statement =>
      assert(statement.executeUpdate() == 1, s"the fixture row was not inserted: ${sql.take(72)}")
    }    //  insert

  /** A fixed accounting month `year`/`month`, as a SQL timestamp literal. */
  private def monthYearLiteral(year: Int, month: Int): String = {
    val m = ("%02d").format(month)
    s"$year-$m-01 00:00:00"
  }

  /** The probe month, right after the entry month, as a real Timestamp. */
  private def monthYear(year: Int, month: Int): Timestamp = {
    val c = Calendar.getInstance()
    c.clear()
    c.set(year, month - 1, 1, 0, 0, 0)
    new Timestamp(c.getTimeInMillis)
  }
  private val entryDateAcctLiteral = monthYearLiteral(2020, 1)   // TRUNC(entry,'MONTH') = 2020-01-01
  private val probeDateAcct = monthYear(2021, 1)                 // probe month 2021-01, after the entry

  /**
    * Insert one A_Depreciation_Exp fixture row by inlining every value, the way
    * the module's other client scope specs seed data. Boolean columns are
    * CHAR(1), so 'Y'/'N'; the numeric amount columns with a (0) default are
    * left to the default.
    */
  private def insertDepreciationExp(
      id: Int, clientId: Int, assetId: Int,
      dateAcctLiteral: String, processed: Boolean): Unit = {
    val sql =
      s"INSERT INTO A_Depreciation_Exp (" +
      s"A_Depreciation_Exp_ID, AD_Client_ID, AD_Org_ID, A_Asset_ID, " +
      s"A_Entry_Type, A_Period, DateAcct, PostingType, Processed, " +
      s"IsActive, IsDepreciated, Description, Created, CreatedBy, Updated, UpdatedBy" +
      s") VALUES (" +
      s"$id, $clientId, 0, $assetId, " +
      s"'DEP', 1, '$dateAcctLiteral', 'A', ${if (processed) "'Y'" else "'N'"}, " +
      s"'Y', 'N', 'regression fixture 4299', now(), 0, now(), 0)"
    insert(sql)
  }    //  insertDepreciationExp

  Feature("MDepreciationExp client scoping (adempiere/adempiere#4299)") {
    Scenario("checkExistsNotProcessedEntries ignores unprocessed entries of another client") {
      Given("one active transaction that owns every fixture row this scenario creates")
      val trx = Trx.get(trxName, true)
      assert(trx.isActive, "the suite transaction must be active, or the fixtures would not roll back")
      val expIds = nextIds("A_Depreciation_Exp", "A_Depreciation_Exp_ID", 1)
      val assetIds = nextIds("A_Asset", "A_Asset_ID", 1)

      And(s"an unprocessed A_Depreciation_Exp entry of another client (AD_Client_ID=$otherClientId)")
      insertDepreciationExp(expIds.head, otherClientId, assetIds.head,
        entryDateAcctLiteral, processed = false)

      When("checkExistsNotProcessedEntries is called for the logged in client")
      // Before the fix the unscoped Query matched the cross-client row and raised
      // AssetException; after the fix it is scoped to the current client, which has
      // no unprocessed entry, so nothing is raised.
      var raised: Option[AssetException] = None
      try {
        MDepreciationExp.checkExistsNotProcessedEntries(
          Context, assetIds.head, probeDateAcct, "A", trxName)
      } catch {
        case failure: AssetException => raised = Some(failure)
      }

      Then("no AssetException is raised for the logged in client")
      assert(raised.isEmpty,
        s"the logged in client must not raise for an asset that only has cross-client unprocessed entries, got: ${raised.map(_.toString)}")
    }

    Scenario("getNotProcessedEntries returns only entries of the logged in client") {
      Given("one active transaction that owns every fixture row this scenario creates")
      val trx = Trx.get(trxName, true)
      assert(trx.isActive, "the suite transaction must be active, or the fixtures would not roll back")
      val expIds = nextIds("A_Depreciation_Exp", "A_Depreciation_Exp_ID", 2)
      val assetIds = nextIds("A_Asset", "A_Asset_ID", 2)
      val crossAsset = assetIds.head
      val ownAsset = assetIds(1)

      And(s"an unprocessed entry of another client (AD_Client_ID=$otherClientId) for asset ${crossAsset}")
      insertDepreciationExp(expIds.head, otherClientId, crossAsset,
        entryDateAcctLiteral, processed = false)

      And(s"an unprocessed entry of the logged in client for asset ${ownAsset}")
      insertDepreciationExp(expIds(1), currentClientId, ownAsset,
        entryDateAcctLiteral, processed = false)

      When("getNotProcessedEntries is called for the cross-client asset")
      val crossEntries = MDepreciationExp
        .getNotProcessedEntries(Context, crossAsset, "A", trxName)
        .asScala.toList

      Then("the cross-client asset reports no unprocessed entries")
      assert(crossEntries.isEmpty,
        s"the logged in client must not see the cross-client unprocessed entry of asset $crossAsset, got ${crossEntries.size}")

      When("getNotProcessedEntries is called for the logged in client's asset")
      val ownEntries = MDepreciationExp
        .getNotProcessedEntries(Context, ownAsset, "A", trxName)
        .asScala.toList

      Then("the logged in client's asset reports exactly its own entry")
      assert(ownEntries.size == 1, s"the logged in client's asset must report one unprocessed entry, got ${ownEntries.size}")
      assert(ownEntries.head.getA_Asset_ID == ownAsset,
        s"the entry must belong to asset $ownAsset, got ${ownEntries.head.getA_Asset_ID}")
      assert(ownEntries.head.getAD_Client_ID == currentClientId,
        s"the entry must belong to the logged in client $currentClientId, got ${ownEntries.head.getAD_Client_ID}")
    }

    Scenario("getEntries is scoped to the logged in client") {
      Given("one active transaction that owns every fixture row this scenario creates")
      val trx = Trx.get(trxName, true)
      assert(trx.isActive, "the suite transaction must be active, or the fixtures would not roll back")
      val expIds = nextIds("A_Depreciation_Exp", "A_Depreciation_Exp_ID", 1)
      val assetIds = nextIds("A_Asset", "A_Asset_ID", 1)
      val crossAsset = assetIds.head

      And(s"a processed entry of another client (AD_Client_ID=$otherClientId) for asset ${crossAsset}")
      insertDepreciationExp(expIds.head, otherClientId, crossAsset,
        entryDateAcctLiteral, processed = true)

      When("getEntries is called for the cross-client asset")
      val entries = MDepreciationExp
        .getEntries(Context, crossAsset, "A", trxName)
        .asScala.toList

      Then("the cross-client asset reports no entries for the logged in client")
      assert(entries.isEmpty,
        s"the logged in client must not see the cross-client entry of asset $crossAsset, got ${entries.size}")
    }
  }    //  Feature
}    //  ValidateMDepreciationExpClientScope
