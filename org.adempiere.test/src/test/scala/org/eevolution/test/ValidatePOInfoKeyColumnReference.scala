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
  * ****************************************************************************/

package org.eevolution.test

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import org.compiere.model.{POInfo, POInfoColumn}
import org.compiere.util.{DB, DisplayType, Env, Trx}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.GivenWhenThen

/**
  * Regression spec for issue #4462 / PR #4463.
  *
  * POInfo.loadInfo now resolves the referenced table's key-column reference inside its own
  * metadata query (KeyColumnReference_ID) and passes it to POInfoColumn, instead of letting
  * POInfoColumn run two nested queries per Table-reference column while the outer result set
  * still holds a pooled connection.
  *
  * The spec seeds a throwaway dictionary inside its own transaction and rolls it back, so
  * nothing is committed. Scratch names carry a per-run stamp so the static POInfo cache can
  * never serve a stale entry to this or any other spec.
  *
  * The nested fallback is pinned structurally rather than by instrumenting DB: DB.getSQLValueEx
  * prepares and executes its statement directly instead of routing through DB.runResultSetFunction,
  * so a nesting-depth decorator (the pattern used for issue #4460) records depth one on both the
  * base branch and the fix, and would pass without proving anything.
  */
class ValidatePOInfoKeyColumnReference extends AnyFeatureSpec with AdempiereTestCase with GivenWhenThen {

  /** Identifiers and exact dictionary names of one seeded fixture (all rolled back). */
  private case class Fixture(
    stringTableName: String,
    noRefTableName: String,
    tableRefColumnName: String,
    noRefColumnName: String,
    stringRefId: Int,
    noStringRefId: Int
  )

  private def allocateId(trxName: String, table: String, column: String): Int =
    DB.getSQLValue(trxName, s"SELECT COALESCE(MAX($column), 0) + 1 FROM $table")

  /**
    * Seeds:
    *  - table `stringTableName` with column `tableRefColumnName` (DisplayType Table, 18)
    *    whose AD_Ref_Table.AD_Key names a String key column (DisplayType String, 10);
    *  - table `noRefTableName` with column `noRefColumnName` (DisplayType Table, 18) whose
    *    reference deliberately has no AD_Ref_Table row, so the join yields NULL (0 in JDBC).
    */
  private def seedFixture(trxName: String): Fixture = {
    val stamp = System.currentTimeMillis() % 1000000
    val stringTableName = s"PR4463Str$stamp"
    val noRefTableName = s"PR4463NoR$stamp"
    val keyColumnName = s"PR4463Key$stamp"
    val tableRefColumnName = s"PR4463Ref$stamp"
    val noRefColumnName = s"PR4463Nor$stamp"

    val stringTableId = allocateId(trxName, "AD_Table", "AD_Table_ID")
    val noRefTableId = stringTableId + 1
    val stringRefId = allocateId(trxName, "AD_Reference", "AD_Reference_ID")
    val noStringRefId = stringRefId + 1
    val keyColumnId = allocateId(trxName, "AD_Column", "AD_Column_ID")
    val tableRefColumnId = keyColumnId + 1
    val noRefColumnId = keyColumnId + 2
    val keyElementId = allocateId(trxName, "AD_Element", "AD_Element_ID")
    val refElementId = keyElementId + 1
    val norElementId = keyElementId + 2

    def insertElement(elementId: Int, columnName: String): Unit =
      DB.executeUpdate(
        """INSERT INTO AD_Element (AD_Element_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,
          |Createdby, Updated, Updatedby, ColumnName, EntityType, Name, PrintName, Description)
          |VALUES (?, 0, 0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, ?, 'U', ?, ?,
          |'Scratch element rolled back by this spec')""".stripMargin,
        Array[AnyRef](Integer.valueOf(elementId), columnName, columnName, columnName),
        false, trxName)

    def insertTable(tableId: Int, tableName: String): Unit =
      DB.executeUpdate(
        """INSERT INTO AD_Table (AD_Table_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,
          |Createdby, Updated, Updatedby, Name, TableName, IsView, AccessLevel, EntityType,
          |IsSecurityEnabled, IsDeleteable, IsHighVolume, IsChangeLog, ReplicationType)
          |VALUES (?, 0, 0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, ?, ?, 'N', '4', 'U', 'N', 'N', 'N',
          |'N', 'L')""".stripMargin,
        Array[AnyRef](Integer.valueOf(tableId), tableName, tableName),
        false, trxName)

    def insertColumn(columnId: Int, tableId: Int, elementId: Int, columnName: String,
                     referenceId: Int, referenceValueId: Integer, isKey: String): Unit =
      DB.executeUpdate(
        """INSERT INTO AD_Column (AD_Column_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,
          |Createdby, Updated, Updatedby, Name, Version, EntityType, ColumnName, AD_Table_ID,
          |AD_Reference_ID, AD_Reference_Value_ID, AD_Element_ID, IsKey, IsParent, IsMandatory,
          |IsUpdateable, IsIdentifier, IsTranslated, IsEncrypted, IsSelectionColumn,
          |IsAlwaysUpdateable, IsAutocomplete, IsRange, FieldLength)
          |VALUES (?, 0, 0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, ?, 1, 'U', ?, ?, ?, ?, ?, ?, 'N', 'N',
          |'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 60)""".stripMargin,
        Array[AnyRef](Integer.valueOf(columnId), columnName, columnName, Integer.valueOf(tableId),
          Integer.valueOf(referenceId), referenceValueId, Integer.valueOf(elementId), isKey),
        false, trxName)

    insertElement(keyElementId, keyColumnName)
    insertElement(refElementId, tableRefColumnName)
    insertElement(norElementId, noRefColumnName)

    insertTable(stringTableId, stringTableName)
    insertTable(noRefTableId, noRefTableName)

    def insertReference(referenceId: Int, name: String): Unit =
      DB.executeUpdate(
        """INSERT INTO AD_Reference (AD_Reference_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,
          |Createdby, Updated, Updatedby, Name, ValidationType, EntityType)
          |VALUES (?, 0, 0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, ?, 'T', 'U')""".stripMargin,
        Array[AnyRef](Integer.valueOf(referenceId), name),
        false, trxName)

    insertReference(stringRefId, s"PR4463 string key reference $stamp")
    insertReference(noStringRefId, s"PR4463 reference without AD_Ref_Table row $stamp")

    // Key column of the first scratch table: DisplayType String is what must be resolved.
    insertColumn(keyColumnId, stringTableId, keyElementId, keyColumnName,
      DisplayType.String, null, "Y")
    // Table column whose AD_Ref_Table.AD_Key points at the String key column.
    insertColumn(tableRefColumnId, stringTableId, refElementId, tableRefColumnName,
      DisplayType.Table, Integer.valueOf(stringRefId), "N")
    // Table column whose reference has no AD_Ref_Table row: the join yields NULL.
    insertColumn(noRefColumnId, noRefTableId, norElementId, noRefColumnName,
      DisplayType.Table, Integer.valueOf(noStringRefId), "Y")

    DB.executeUpdate(
      """INSERT INTO AD_Ref_Table (AD_Reference_ID, AD_Client_ID, AD_Org_ID, IsActive, Created,
        |Createdby, Updated, Updatedby, AD_Table_ID, AD_Key, AD_Display, IsValueDisplayed,
        |EntityType, IsAlert, IsDisplayIdentifier)
        |VALUES (?, 0, 0, 'Y', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP, 0, ?, ?, ?, 'N', 'U', 'N', 'Y')""".stripMargin,
      Array[AnyRef](Integer.valueOf(stringRefId), Integer.valueOf(stringTableId),
        Integer.valueOf(keyColumnId), Integer.valueOf(keyColumnId)),
      false, trxName)
    // Deliberately no AD_Ref_Table row for noStringRefId.

    Fixture(stringTableName, noRefTableName, tableRefColumnName, noRefColumnName,
      stringRefId, noStringRefId)
  }

  private def withSeededFixture(test: (Fixture, String) => Unit): Unit = {
    val trxName = Trx.createTrxName("PR4463_")
    try {
      val fixture = seedFixture(trxName)
      test(fixture, trxName)
    } finally {
      val trx = Trx.get(trxName, false)
      if (trx != null) {
        trx.rollback
        trx.close
      }
    }
  }

  Feature("POInfo resolves the referenced key column inside its own metadata query") {

    Scenario("a Table column whose referenced key column is String loads as String") {
      Given("a seeded Table column whose AD_Ref_Table.AD_Key names a String column")
      withSeededFixture { (fixture, trxName) =>
        When("POInfo loads the scratch table metadata inside the seeding transaction")
        val info = POInfo.getPOInfo(Env.getCtx, fixture.stringTableName, trxName)
        Then("the column display type is resolved to String through the join")
        val index = info.getColumnIndex(fixture.tableRefColumnName)
        assert(index >= 0, s"column ${fixture.tableRefColumnName} not loaded")
        assert(info.getColumnDisplayType(index) == DisplayType.String,
          "joined KeyColumnReference_ID must convert DisplayType.Table to String")
        assert(info.getColumnClass(index) == classOf[String])
      }
    }

    Scenario("a Table column whose reference has no AD_Ref_Table row stays Table") {
      Given("a seeded Table column whose reference has no AD_Ref_Table row")
      withSeededFixture { (fixture, trxName) =>
        When("POInfo loads the scratch table metadata inside the seeding transaction")
        val info = POInfo.getPOInfo(Env.getCtx, fixture.noRefTableName, trxName)
        Then("the NULL from the left join keeps the column as DisplayType.Table")
        val index = info.getColumnIndex(fixture.noRefColumnName)
        assert(index >= 0, s"column ${fixture.noRefColumnName} not loaded")
        assert(info.getColumnDisplayType(index) == DisplayType.Table,
          "a NULL key reference must not change the display type")
        assert(info.getColumnClass(index) == classOf[Integer])
      }
    }

    Scenario("the legacy constructor keeps resolving through its nested lookup fallback") {
      Given("a seeded Table reference whose key column is String")
      withSeededFixture { (fixture, trxName) =>
        When("POInfoColumn is built with the legacy constructor inside the transaction")
        val legacy = new POInfoColumn(
          1, fixture.tableRefColumnName, null, DisplayType.Table,
          false, true, null, "label", "description",
          false, false,
          fixture.stringRefId, null,
          60, null, null,
          false, false, true, true,
          trxName)
        Then("the nested lookup fallback still converts it to String")
        assert(legacy.DisplayType == DisplayType.String,
          "the 21-argument constructor must keep the pre-PR lookup behavior")
        assert(legacy.ColumnClass == classOf[String])
      }
    }

    Scenario("the joined resolution stays in the metadata query and the fallback stays guarded") {
      //	Every pin below is scoped to the body of the method that owns the behavior, never to the
      //	whole file: a matching statement elsewhere in POInfo.java must not be able to satisfy a
      //	check about what loadInfo does. Method bodies are cut on their own trailing comment
      //	markers, which predate this change, so the scope holds on the base branch too and the
      //	scenario still fails there for the right reason.
      def bodyBetween(source: String, startMarker: String, endMarker: String, last: Boolean = false): String = {
        val from = if (last) source.lastIndexOf(startMarker) else source.indexOf(startMarker)
        assert(from >= 0, s"start marker ${'"'}$startMarker${'"'} was not found")
        val to = source.indexOf(endMarker, from)
        assert(to > from, s"end marker ${'"'}$endMarker${'"'} was not found after the start marker")
        source.substring(from, to)
      }

      val poInfoSource = Files.readString(repoRoot.resolve("base/src/org/compiere/model/POInfo.java"),
        StandardCharsets.UTF_8)
      val loadInfo = bodyBetween(poInfoSource, "private void loadInfo (", "//  loadInfo")

      Then("POInfo.loadInfo joins AD_Ref_Table and AD_Column once and passes the reference")
      assert(loadInfo.contains("LEFT OUTER JOIN AD_Ref_Table rt ON (rt.AD_Reference_ID=c.AD_Reference_Value_ID)"),
        "the metadata query must resolve the reference row with a join")
      assert(loadInfo.contains("LEFT OUTER JOIN AD_Column kc ON (kc.AD_Column_ID=rt.AD_Key)"),
        "the metadata query must resolve the key column with a join")
      assert(loadInfo.contains("kc.AD_Reference_ID AS KeyColumnReference_ID"),
        "the metadata query must select the key column reference")
      //	Bind the identifier by role, not by spelling: capture whatever local variable reads the
      //	joined column and require that same variable to be the one handed to POInfoColumn. This
      //	pins the wiring on the PR head (KeyColumnReference_ID) and survives any later rename.
      val joinedRead = """int\s+(\w+)\s*=\s*row\.getInt\(\s*"KeyColumnReference_ID"\s*\)"""
        .r.findFirstMatchIn(loadInfo)
      assert(joinedRead.isDefined,
        "loadInfo must read the joined key column reference out of its own result set")
      val joinedValueVariable = joinedRead.get.group(1)
      //	The reference must reach the POInfoColumn constructor built from this row, so match the
      //	argument position inside loadInfo rather than any bare "trxName," in the file.
      assert(("""new POInfoColumn\s*\([\s\S]*?trxName,\s*""" + joinedValueVariable + """\s*\)""")
        .r.findFirstIn(loadInfo).isDefined,
        s"loadInfo must hand the resolved reference $joinedValueVariable to the POInfoColumn it builds")

      Then("POInfoColumn only runs the nested lookups when the reference is unknown")
      val poInfoColumnSource = Files.readString(
        repoRoot.resolve("base/src/org/compiere/model/POInfoColumn.java"), StandardCharsets.UTF_8)
      //	The 22-argument constructor is the last one declared, and it is the one that owns the
      //	fallback; scoping to its body keeps the guard checks about that constructor alone.
      val constructor = bodyBetween(poInfoColumnSource, "public POInfoColumn (", "}   //  Column", last = true)
      val guard = """if\s*\(\s*(\w+)\s*==\s*KEY_REFERENCE_UNKNOWN\s*\)"""
        .r.findFirstMatchIn(constructor)
      assert(guard.isDefined,
        "the nested AD_Ref_Table/AD_Column lookups must stay guarded by KEY_REFERENCE_UNKNOWN")
      val guardedVariable = guard.get.group(1)
      val guardIndex = guard.get.start(0)
      val nestedKey = constructor.indexOf("Select AD_Key FROM AD_Ref_Table WHERE AD_Reference_ID=?")
      val nestedRef = constructor.indexOf("Select AD_Reference_ID FROM AD_Column WHERE AD_Column_ID=?")
      assert(nestedKey > guardIndex && nestedRef > guardIndex,
        "the fallback lookups must live inside the KEY_REFERENCE_UNKNOWN guard, not run eagerly")
      //	The value the guard protects must be the same one the display type is decided from,
      //	otherwise the guard could be bypassed by a second, unguarded resolution.
      assert(("""DisplayType\.String\s*==\s*""" + guardedVariable).r.findFirstIn(constructor).isDefined,
        s"the String conversion must be decided from the guarded $guardedVariable, not from another value")
      //	The sentinel value is pinned against the whole file, not the constructor body: the
      //	declaration is a class field, so the field scope is the right one here. The value matters
      //	because a left outer join yields NULL and JDBC reports that as 0, so a sentinel of 0 would
      //	read a genuinely unresolved reference as already resolved and silently skip the fallback.
      assert(poInfoColumnSource.contains("KEY_REFERENCE_UNKNOWN = -1"),
        "the legacy constructor must keep signalling an unresolved reference with a sentinel that"
          + " cannot collide with the 0 a left join NULL arrives as")
    }
  }

  // The forked test JVM may start from any working directory, so locate the repository root
  // instead of trusting the CWD.
  private def repoRoot: java.nio.file.Path = {
    var dir = Paths.get("").toAbsolutePath()
    while (dir != null) {
      if (Files.exists(dir.resolve("base/src/org/compiere/model/POInfo.java")))
        return dir
      dir = dir.getParent
    }
    fail("repository root containing base/src/org/compiere/model/POInfo.java was not found")
  }
}
