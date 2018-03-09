package org.eevolution.test

import org.compiere.model.{MOrg, MWarehouse}
import org.compiere.util.{Env, Trx}
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Define a trait to create an ADempiere test
  * eEvolution author Victor Perez <victor.perez@e-evolution.com> ,Created by e-Evolution on 07/01/16.
  */
trait AdempiereTestCase extends AdempiereStartup with Suite with BeforeAndAfterAll {

  var trxName = Trx.createTrxName(getClass.getName + "_")

  override def beforeAll() {
    startup
  }

  override def afterAll() {
    // Rollback the transaction, if any
    val trx: Trx = Trx.get(trxName, false)
    if (trx != null && trx.isActive) {
      trx.rollback
      trx.close
    }
  }
  import org.eevolution.dsl._
  def Organization : Organization = MOrg.get(Env.getCtx , Env.getAD_Org_ID(Env.getCtx))
  def Warehouse : Warehouse = MWarehouse.get(Env.getCtx, Env.getContextAsInt(Env.getCtx(), "#M_Warehouse_ID"))
  implicit def Context : Context = Env.getCtx
  implicit def Transaction : Transaction = Trx.get(trxName, false)
  def Today : Date = Env.getContextAsDate(Context, "#Date")
}
