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

import org.compiere.model._
import org.eevolution.service._
import org.scalatest.{GivenWhenThen, FeatureSpec}

/**
  * Test to validate process of  Sales in POS
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 05/01/16.
  */
class CreateSalesOrder extends FeatureSpec  with AdempiereTestCase with GivenWhenThen with ProductService{
  feature("Create a sales ticket") {
    info("The customer Joe Block buy two Oak Trees and one Azalea Bush")
    info("The customer not ask for an invoice so that the delivery is made using final consumer")
    info("The customer paid using your credit card 50 % of sales ticket and other 50% in cash")

    scenario("Create sales order") {

      import org.eevolution.dsl.builder._

      //Functions for this scenario
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val SalePriceList = { MPriceList.getDefault(Context, true) }
      import X_C_DocType._
      val WarehouseOrder = { DOCSUBTYPESO_WarehouseOrder }
      val HQ = { Organization }
      val HQWarehouse = { Warehouse }
      val QtyOak = { BigDecimal(1) }
      val Oak = { getProduct("Oak", trxName) }
      val OakPrice  = { 61.75 }
      val QtyAzalea =  { 2 }
      val Azalea = { getProduct("Azalea Bush",trxName) }
      val AzaleaPrice = { 23.75}
      val TotalSales = { (OakPrice * 1)+(AzaleaPrice * 2) }

      given(s"Joe Block buy one Oak Trees with $OakPrice USD by unit and two Azalea Bush with $AzaleaPrice USD by unit")
      when("when sales order is created ")
      val order = OrderBuilder(Context , trxName)
        .AddLine(Oak , QtyOak) AddLine (Azalea , QtyAzalea) AsSalesOrder() With HQ With JoeBlock With HQWarehouse With SalePriceList With WarehouseOrder build()
      then("the order have a organization")
      assert( order.getAD_Org_ID == HQ.getAD_Org_ID)
      info(Organization.getName)
      and("the Document No have the value ")
      assert( order.getDocumentNo.length > 0)
      info(order.getDocumentNo)
      and("the order have a partner ")
      assert ( order.getC_BPartner == JoeBlock)
      info(JoeBlock.getName)
      and("the order have a warehouse ")
      assert ( order.getM_Warehouse == HQWarehouse)
      info(HQWarehouse.getName)
      and("the order have two lines ")
      assert ( order.getLines().length == 2)
      info("--------------------------------------------------------")
      for (orderLine <- order.getLines)
        info ("Product : " + orderLine.getM_Product.getName + " Qty : " + orderLine.getQtyOrdered +  " Total Line : " +  orderLine.getLineNetAmt)

      info("--------------------------------------------------------")
      and(s"the total Sales Order is that $TotalSales")
      assert(TotalSales.toDouble == order.getGrandTotal.doubleValue())
    }
  }
}
