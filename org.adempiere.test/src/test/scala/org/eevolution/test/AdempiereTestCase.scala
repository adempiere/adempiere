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

import org.compiere.model.{MOrg, MWarehouse}
import org.compiere.util.{Env, Trx}
import org.scalatest.*


/**
  * Define a trait to create an ADempiere test
  * eEvolution author Victor Perez <victor.perez@e-evolution.com> ,Created by e-Evolution on 07/01/16.
  */
trait AdempiereTestCase extends AdempiereStartup with Suite with BeforeAndAfterAll {

  var trxName: String = Trx.createTrxName(getClass.getName + "_")

  override def beforeAll(): Unit = {
    startup()
  }

  override def afterAll(): Unit = {
    // Rollback the transaction, if any
    val trx: Trx = Trx.get(trxName, false)
    if (trx != null && trx.isActive) {
      trx.rollback
      trx.close
    }
  }
  import org.eevolution.dsl.*
  def Organization : Organization = MOrg.get(Env.getCtx , Env.getAD_Org_ID(Env.getCtx))
  def Warehouse : Warehouse = MWarehouse.get(Env.getCtx, Env.getContextAsInt(Env.getCtx, "#M_Warehouse_ID"))
  implicit def Context : Context = Env.getCtx
  implicit def Transaction : Transaction = Trx.get(trxName, false)
  def Today : Date = Env.getContextAsDate(Context, "#Date")
}
