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

import org.adempiere.core.domains.models.I_C_DocType
import org.adempiere.core.domains.models.I_M_ReplenishPlan
import org.adempiere.production.process.{CalculateReplenishPlan, CalculateReplenishPlanAbstract}
import org.compiere.model.{MLocator, MMovement, MMovementLine, Query}
import org.compiere.production.model.MReplenishPlan
import org.compiere.util.{DB, Env, Trx}
import org.eevolution.dsl.*
import org.eevolution.services.dsl.ProcessBuilder
import org.eevolution.services.{PriceListService, ProductService}
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec

/** Integration test for the unconfirmed-production cleanup of `CalculateReplenishPlan`.
  *
  * The cleanup deletes the inventory movements whose production batch no longer exists. Those
  * DELETEs must stay inside the client running the process: an orphan of another client is not
  * this plan's data and must survive the run.
  *
  * The process is launched through `ProcessBuilder`, the way a user launches it: an
  * `AD_PInstance` is created, its parameters are read back from the database, and
  * `AD_Client_ID` is resolved from that instance. The test therefore exercises the same client
  * resolution the fix depends on, instead of handing the client to the process by hand.
  *
  * Everything the process produces lives in the transaction opened here and is rolled back by
  * `AdempiereTestCase`, because `withoutTransactionClose()` keeps `ProcessUtil` from committing
  * a transaction it does not own. Two tables are the deliberate exception: `AD_PInstance` and
  * its parameters are saved outside any transaction (`MPInstance` documents "no transaction
  * support", and `DB` commits locally when `trxName` is null), so they are removed on their own
  * committed transaction, the way the module's other specs clean up after `ProcessBuilder`.
  *
  * No DDL is needed to create an orphan movement: `m_movement.m_productionbatch_id` references
  * `m_productionbatch` as `DEFERRABLE INITIALLY DEFERRED`, so a dangling reference is accepted
  * while the transaction stays open and is only checked at commit. This suite rolls back, so the
  * constraint is never validated and never altered.
  */
class DeleteUnconfirmedProductionClientScope
    extends AnyFeatureSpec
    with AdempiereTestCase
    with GivenWhenThen
    with ProductService
    with PriceListService {

  /** One row this scenario watches, under the label it carries in the census. */
  private case class Row(label: String, table: String, idColumn: String, id: Int)

  /** A tenant described entirely as data: the ids its rows carry, and nothing else.
    *
    * The unrelated tenant owns every row it references, down to its document type, its product
    * and its locators, so no row of one tenant ever points into another tenant.
    */
  private case class Tenant(
      clientId: Int,
      orgId: Int,
      locationId: Int,
      warehouseId: Int,
      locatorId: Int,
      locatorToId: Int,
      glCategoryId: Int,
      docTypeId: Int,
      taxCategoryId: Int,
      productCategoryId: Int,
      productId: Int,
      movementId: Int,
      movementLineId: Int
  )

  /** The four document types `CalculateReplenishPlan` requires on its plan. */
  private case class PlanDocTypes(
      planned: DocumentType,
      confirmed: DocumentType,
      purchase: DocumentType,
      requisition: DocumentType
  )

  /** A moment `days` away from the login date, as a SQL timestamp. */
  private def daysFromNow(days: Long): Timestamp =
    new Timestamp(Env.getContextAsDate(Context, "#Date").getTime + days * 24L * 60 * 60 * 1000)    //	daysFromNow

  /** The next `count` free ids of one table, read inside `trx`.
    *
    * The fixture derives its ids instead of hardcoding them, so it cannot collide with rows a
    * developer already has in their GardenWorld.
    */
  private def nextIds(trx: Trx, table: String, idColumn: String, count: Int): List[Int] = {
    val highest = DB.getSQLValue(trx.getTrxName, s"SELECT COALESCE(MAX($idColumn), 0) FROM $table")
    (1 to count).map(offset => highest + offset).toList
  }    //	nextIds

  /** Borrows a prepared statement bound to `trx`, runs `body` over it, and closes it. */
  private def withStatement[A](trx: Trx, sql: String)(body: PreparedStatement => A): A = {
    val statement = trx.getConnection.prepareStatement(sql)
    try body(statement)
    finally statement.close()
  }    //	withStatement

  /** Runs one seed statement and insists it touched exactly one row. */
  private def insert(trx: Trx, sql: String): Unit =
    withStatement(trx, sql) { statement =>
      assert(statement.executeUpdate() == 1, s"the seed row was not inserted: ${sql.take(72)}")
    }    //	insert

  /** Inserts every statement in order, returning how many rows were seeded. */
  private def insertAll(trx: Trx, statements: List[String]): Int =
    statements.foldLeft(0) { (seeded, sql) =>
      insert(trx, sql)
      seeded + 1
    }    //	insertAll

  /** Whether one row exists, read inside `trx`. */
  private def rowExists(trx: Trx, row: Row): Boolean =
    withStatement(trx, s"SELECT 1 FROM ${row.table} WHERE ${row.idColumn} = ?") { statement =>
      statement.setInt(1, row.id)
      val resultSet = statement.executeQuery()
      try resultSet.next()
      finally DB.close(resultSet)
    }    //	rowExists

  /** Existence of every watched row, as one immutable snapshot keyed by label. */
  private def census(trx: Trx, rows: List[Row]): Map[String, Boolean] =
    rows.map(row => row.label -> rowExists(trx, row)).toMap    //	census

  /** The labels whose rows went from present to absent between two censuses. */
  private def removed(before: Map[String, Boolean], after: Map[String, Boolean]): Set[String] =
    before.collect {
      case (label, exists) if exists && !after.getOrElse(label, exists) => label
    }.toSet    //	removed

  /** The watched labels whose rows still exist in `census`. */
  private def stillPresent(census: Map[String, Boolean], watched: Set[String]): Set[String] =
    census.collect {
      case (label, exists) if exists && watched(label) => label
    }.toSet    //	stillPresent

  /** One document type of the logged-in client, by name and base type. */
  private def docType(name: String, baseType: String): Option[DocumentType] = {
    val found: DocumentType = new Query(
      Context,
      I_C_DocType.Table_Name,
      s"${I_C_DocType.COLUMNNAME_Name} = ? AND ${I_C_DocType.COLUMNNAME_DocBaseType} = ?",
      trxName
    )
      .setClient_ID()
      .setParameters(name, baseType)
      .first()
    Option(found).filter(_.getC_DocType_ID > 0)
  }    //	docType

  /** The plan's document types, resolved from the client instead of hardcoded. */
  private def planDocTypes: Option[PlanDocTypes] =
    for {
      planned <- docType("Planned Order", "MPO")
      confirmed <- docType("Manufacturing Order", "MOP")
      purchase <- docType("Purchase Order", "POO")
      requisition <- docType("Purchase Requisition", "POR")
    } yield PlanDocTypes(planned, confirmed, purchase, requisition)    //	planDocTypes

  /** One active locator of the logged-in client, `offset` positions into the table. */
  private def locatorAt(offset: Int): Option[Locator] = {
    val id = DB.getSQLValue(
      trxName,
      "SELECT M_Locator_ID FROM M_Locator WHERE AD_Client_ID=? AND IsActive='Y' "
        + "ORDER BY M_Locator_ID LIMIT 1 OFFSET ?",
      Integer.valueOf(Env.getAD_Client_ID(Context)),
      Integer.valueOf(offset)
    )
    Option(if (id > 0) MLocator.get(Context, id) else null)
  }    //	locatorAt

  /** Two distinct locators, because the movement model rejects a line whose locator equals its
    * locator to.
    */
  private def locatorPair: Option[(Locator, Locator)] =
    locatorAt(0).flatMap(from => locatorAt(1).map(to => (from, to)))    //	locatorPair

  /** The INSERTs of one whole tenant, as data.
    *
    * Every row belongs to the tenant itself. The cleanup never reads the product, the document
    * type or the category of a movement, so this tenant needs no replenishment plan at all: it
    * only has to own an orphan the run must not reach.
    */
  private def tenantRows(tenant: Tenant, missingBatchId: Int): List[String] = {
    /** Builds one INSERT from column/value pairs, keeping the first value given for a column.
      *
      * AD_Client and AD_Org name their primary key after the common tenant columns, so the
      * column list is deduplicated instead of trusting the caller not to repeat them.
      */
    def row(table: String, id: Int, extra: List[(String, String)]): String = {
      val base = List(
        (table + "_ID", id.toString),
        ("AD_Client_ID", tenant.clientId.toString),
        ("AD_Org_ID", tenant.orgId.toString),
        ("IsActive", "'Y'"),
        ("Created", "now()"),
        ("CreatedBy", "0"),
        ("Updated", "now()"),
        ("UpdatedBy", "0")
      ) ++ extra
      val columns = base.map(_._1).distinct
      val values = columns.map(name => base.collectFirst { case (col, value) if col == name => value }.getOrElse("NULL"))
      s"INSERT INTO $table (${columns.mkString(", ")}) VALUES (${values.mkString(", ")})"
    }    //	row

    List(
      row("AD_Client", tenant.clientId, List("Value" -> "'IT-TENANT'", "Name" -> "'IT Unrelated Tenant'")),
      row("AD_Org", tenant.orgId, List("Value" -> "'IT-TENANT-ORG'", "Name" -> "'IT Unrelated Tenant Org'", "IsSummary" -> "'N'")),
      row("C_Location", tenant.locationId, List("C_Country_ID" -> "100")),
      row("M_Warehouse", tenant.warehouseId, List("Value" -> "'IT-WH'", "Name" -> "'IT Unrelated Warehouse'", "C_Location_ID" -> tenant.locationId.toString, "Separator" -> "'-'")),
      row("M_Locator", tenant.locatorId, List("Value" -> "'IT-LOC'", "M_Warehouse_ID" -> tenant.warehouseId.toString, "PriorityNo" -> "0", "IsDefault" -> "'Y'")),
      row("M_Locator", tenant.locatorToId, List("Value" -> "'IT-LOC-TO'", "M_Warehouse_ID" -> tenant.warehouseId.toString, "PriorityNo" -> "10", "IsDefault" -> "'N'")),
      row("GL_Category", tenant.glCategoryId, List("Name" -> "'IT Unrelated GL Category'", "Categorytype" -> "'O'")),
      row("C_TaxCategory", tenant.taxCategoryId, List("Name" -> "'IT Unrelated Tax Category'")),
      row("M_Product_Category", tenant.productCategoryId, List("Value" -> "'IT-CAT'", "Name" -> "'IT Unrelated Product Category'", "PlannedMargin" -> "0")),
      row("C_DocType", tenant.docTypeId, List("Name" -> "'IT Material Movement'", "PrintName" -> "'IT Material Movement'", "GL_Category_ID" -> tenant.glCategoryId.toString, "DocumentCopies" -> "1", "DocBaseType" -> "'MMM'", "IsSOTrx" -> "'N'")),
      row("M_Product", tenant.productId, List("Value" -> "'IT-ORPHAN'", "Name" -> "'IT Unrelated Orphan Product'", "C_UOM_ID" -> "100", "M_Product_Category_ID" -> tenant.productCategoryId.toString, "C_TaxCategory_ID" -> tenant.taxCategoryId.toString, "ProductType" -> "'I'", "IsStocked" -> "'Y'")),
      row("M_Movement", tenant.movementId, List("DocumentNo" -> "'IT-ORPHAN-OTHER'", "MovementDate" -> "now()", "Posted" -> "'N'", "Processed" -> "'N'", "DocAction" -> "'CO'", "DocStatus" -> "'IP'", "IsInTransit" -> "'N'", "IsApproved" -> "'N'", "C_DocType_ID" -> tenant.docTypeId.toString, "M_ProductionBatch_ID" -> missingBatchId.toString)),
      row("M_MovementLine", tenant.movementLineId, List("M_Movement_ID" -> tenant.movementId.toString, "M_Locator_ID" -> tenant.locatorId.toString, "M_LocatorTo_ID" -> tenant.locatorToId.toString, "M_Product_ID" -> tenant.productId.toString, "MovementQty" -> "1", "Processed" -> "'N'", "Line" -> "10"))
    )
  }    //	tenantRows

  /** The ids of a whole tenant, derived from the tables so no instance id is hardcoded. */
  private def newTenant(trx: Trx): Tenant = {
    def free(table: String, idColumn: String): Int = nextIds(trx, table, idColumn, 1).head
    val locators = nextIds(trx, "M_Locator", "M_Locator_ID", 2)

    Tenant(
      free("AD_Client", "AD_Client_ID"),
      free("AD_Org", "AD_Org_ID"),
      free("C_Location", "C_Location_ID"),
      free("M_Warehouse", "M_Warehouse_ID"),
      locators.head,
      locators(1),
      free("GL_Category", "GL_Category_ID"),
      free("C_TaxCategory", "C_TaxCategory_ID"),
      free("M_Product_Category", "M_Product_Category_ID"),
      free("C_DocType", "C_DocType_ID"),
      free("M_Product", "M_Product_ID"),
      free("M_Movement", "M_Movement_ID"),
      free("M_MovementLine", "M_MovementLine_ID")
    )
  }    //	newTenant

  /** Seeds a whole unrelated tenant and reports the rows the census must watch. */
  private def seedTenant(trx: Trx, missingBatchId: Int): (Tenant, List[Row]) = {
    val tenant = newTenant(trx)
    val rows = tenantRows(tenant, missingBatchId)
    val seeded = insertAll(trx, rows)
    assert(seeded == rows.size, s"the unrelated tenant must be seeded in ${rows.size} rows, got $seeded")

    (
      tenant,
      List(
        Row("other movement", "M_Movement", "M_Movement_ID", tenant.movementId),
        Row("other movement line", "M_MovementLine", "M_MovementLine_ID", tenant.movementLineId)
      )
    )
  }    //	seedTenant

  /** The plan's own orphan movement, built through the model so its mandatory columns are set
    * the way the inventory movement screen sets them.
    */
  private def seedOwnMovement(trx: Trx, product: Product, locator: Locator, locatorTo: Locator, missingBatchId: Int): List[Row] = {
    val movement = new MMovement(Context, 0, trx.getTrxName)
    movement.setDocumentNo("IT-ORPHAN-OWN")
    movement.setMovementDate(Env.getContextAsDate(Context, "#Date"))
    movement.setProcessed(false)
    movement.setDocAction("CO")
    movement.setDocStatus("IP")
    movement.setC_DocType_ID(DB.getSQLValue(trx.getTrxName, "SELECT C_DocType_ID FROM C_DocType WHERE AD_Client_ID=? AND DocBaseType='MMM' AND IsActive='Y' ORDER BY C_DocType_ID", Integer.valueOf(Env.getAD_Client_ID(Context))))
    //	A batch id absent from M_ProductionBatch is what makes this movement an orphan.
    movement.setM_ProductionBatch_ID(missingBatchId)
    assert(movement.save(trx.getTrxName), "the plan's orphan movement must be saved")

    val line = new MMovementLine(Context, 0, trx.getTrxName)
    line.setM_Movement_ID(movement.getM_Movement_ID)
    line.setM_Locator_ID(locator.getM_Locator_ID)
    line.setM_LocatorTo_ID(locatorTo.getM_Locator_ID)
    line.setM_Product_ID(product.getM_Product_ID)
    line.setMovementQty(java.math.BigDecimal.ONE)
    line.setLine(10)
    assert(line.save(trx.getTrxName), "the line of the plan's orphan movement must be saved")

    List(
      Row("own movement", "M_Movement", "M_Movement_ID", movement.getM_Movement_ID),
      Row("own movement line", "M_MovementLine", "M_MovementLine_ID", line.getM_MovementLine_ID)
    )
  }    //	seedOwnMovement

  /** Remove the process instances this scenario launched from the shared database.
    *
    * `MPInstance` documents "no transaction support" and `DB` commits locally when `trxName` is
    * null, so the instance is already committed while the builder is still being configured:
    * `withRecordId` saves it and `withParameter` saves each parameter. Those rows therefore
    * escape the transaction rolled back by `AdempiereTestCase` and have to be deleted on their
    * own committed transaction.
    *
    * The key is derived from data rather than read back from the builder, because the builder
    * exposes no accessor for the instance it committed and its `getProcessInfo` is still null
    * when the run dies before that info exists — keying on it would leak the rows. The plan id
    * is brand new and lives only in the rolled-back transaction, so no other instance can carry
    * it, and this reaches nothing but the instances launched here.
    *
    * One residue is knowingly accepted: an instance saved before its record id is stamped carries
    * a null record id, and sweeping nulls could delete an instance another session is mid-build.
    */
  private def cleanupProcessInstances(processId: Int, planId: Int): Unit =
    try
      Trx.run { name =>
        val key = Array[AnyRef](Integer.valueOf(processId), Integer.valueOf(planId))
        List(
          "DELETE FROM AD_PInstance_Para WHERE AD_PInstance_ID IN "
            + "(SELECT AD_PInstance_ID FROM AD_PInstance WHERE AD_Process_ID = ? AND Record_ID = ?)",
          "DELETE FROM AD_PInstance WHERE AD_Process_ID = ? AND Record_ID = ?"
        ).foreach(sql => DB.executeUpdateEx(sql, key, name))
      }
    catch {
      //	A failed sweep is reported, never rethrown: it must not mask the defect the scenario
      //	just found, and the census assertions still surface the rows it left behind.
      case failure: Exception => info(s"the process instance sweep failed, rows may remain: ${failure.getMessage}")
    }    //	cleanupProcessInstances

  Feature("The unconfirmed-production cleanup of CalculateReplenishPlan stays inside its client") {
    info("Issue #4207 / PR #4484: the movement cleanup DELETEs must be scoped by AD_Client_ID")
    info("The process runs through ProcessBuilder, so AD_Client_ID is resolved from its AD_PInstance")

    Scenario("Cleaning the orphan movements of the plan's client leaves another tenant's rows untouched") {
      Given("one transaction that owns every row this scenario creates")
      val trx = Trx.get(trxName, true)
      //	The connection is what starts a Trx: it stays inactive until it owns one, and
      //	AdempiereTestCase only rolls back at afterAll when the transaction is active.
      assert(trx.getConnection != null, "the suite transaction must own a connection")
      assert(trx.isActive, "the suite transaction must be active, or nothing would roll back")

      And("the master data of the logged-in client, resolved through the module services")
      val oak: Product = getProductByValue("Oak")
      val standard: PriceList = getPriceListByName("Standard")
      val home: Warehouse = Warehouse
      assert(oak.getM_Product_ID > 0, "GardenWorld must provide the Oak product")
      assert(standard.getM_PriceList_ID > 0, "GardenWorld must provide the Standard price list")
      assert(home.getM_Warehouse_ID > 0, "the login context must provide a warehouse")

      And("the document types the plan requires, resolved by base type")
      val docTypes = planDocTypes.getOrElse(
        fail("GardenWorld must provide the plan's Planned Order, Manufacturing Order, Purchase Order and Purchase Requisition document types")
      )

      And("two distinct locators, because the movement model rejects equal locator and locator to")
      val (locator, locatorTo) = locatorPair.getOrElse(
        fail("GardenWorld must provide two distinct active locators")
      )

      And("a replenishment plan of that client that deletes unconfirmed production")
      val plan = new MReplenishPlan(Context, 0, trxName)
      plan.setName("IT Replenish Plan #4484")
      plan.setC_DocType_PlannedOrder(docTypes.planned.getC_DocType_ID)
      plan.setC_DocType_ConfirmedOrder(docTypes.confirmed.getC_DocType_ID)
      plan.setC_DocType_PO(docTypes.purchase.getC_DocType_ID)
      plan.setC_DocType_Requisition(docTypes.requisition.getC_DocType_ID)
      plan.setM_PriceList_ID(standard.getM_PriceList_ID)
      plan.setIsDeleteUnconfirmedProduction(true)
      plan.setIsDeletePlannedPO(false)
      //	A far-future window keeps the demand and open-order phases from picking up documents.
      plan.setDateStart(daysFromNow(365))
      plan.setDateFinish(daysFromNow(372))
      assert(plan.save(trxName), "the replenishment plan must be saved")

      And("an orphan movement of that client and an identical orphan of a whole unrelated tenant")
      val missingBatchId = nextIds(trx, "M_ProductionBatch", "M_ProductionBatch_ID", 1).head
      val ownRows = seedOwnMovement(trx, oak, locator, locatorTo, missingBatchId)
      val (otherTenant, otherRows) = seedTenant(trx, missingBatchId)
      val watched = ownRows ++ otherRows
      val otherLabels = otherRows.map(_.label).toSet

      And("both tenants hold one orphan movement and one line before the run")
      val before = census(trx, watched)
      assert(before.values.forall(identity), s"every watched row must exist before the run: $before")
      info(s"the unrelated tenant is client ${otherTenant.clientId}, the plan's client is ${Env.getAD_Client_ID(Context)}")

      When("CalculateReplenishPlan runs for that plan through the process engine")
      val planId = plan.getM_ReplenishPlan_ID
      //	The whole builder chain is inside the try, not just execute: configuring the builder
      //	already commits AD_PInstance and its parameters, so a throw anywhere in it must still
      //	be swept.
      val run =
        try
          ProcessBuilder
            .create(Context)
            .process(classOf[CalculateReplenishPlan])
            .withTitle(CalculateReplenishPlanAbstract.getProcessName)
            .withRecordId(I_M_ReplenishPlan.Table_ID, Integer.valueOf(planId))
            .withParameter(CalculateReplenishPlanAbstract.M_PRODUCT_ID, Integer.valueOf(oak.getM_Product_ID))
            .withParameter(CalculateReplenishPlanAbstract.M_WAREHOUSE_ID, Integer.valueOf(home.getM_Warehouse_ID))
            .withParameter("Date", Env.getContextAsDate(Context, "#Date"))
            .withoutTransactionClose()
            .execute(trxName)
        finally cleanupProcessInstances(CalculateReplenishPlanAbstract.getProcessId, planId)

      Then("the process executed without errors")
      assert(!run.isError, s"the replenish plan process must succeed: ${run.getSummary}")
      info("Process summary: " + run.getSummary)

      And("the run cleaned exactly the plan's own orphan movement and its line")
      val after = census(trx, watched)
      assert(
        removed(before, after) == ownRows.map(_.label).toSet,
        s"only the plan's own rows may be deleted, but ${removed(before, after)} were"
      )

      And("no row of the unrelated tenant was touched")
      assert(
        stillPresent(after, otherLabels) == otherLabels,
        s"client ${otherTenant.clientId} must keep every one of its own rows"
      )

      And("the run persisted the pegging report of the plan it was asked to calculate")
      assert(
        DB.getSQLValue(
          trxName,
          "SELECT COUNT(*) FROM M_ReplenishPlanLine WHERE M_ReplenishPlan_ID = ?",
          Integer.valueOf(plan.getM_ReplenishPlan_ID)
        ) > 0,
        "the plan must have persisted its pegging report lines"
      )
    }    //	Scenario
  }    //	Feature
}    //	DeleteUnconfirmedProductionClientScope
