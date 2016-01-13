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


package org.eevolution.dsl.builder

import org.eevolution.service._
/**
  * Order Builder allows create a sales order using a DSL
  * ie val order = OrderBuilder(Context , trxName)
        .AddLine(Oak , QtyOak) AddLine (Azalea , QtyAzalea) AsSalesOrder() With HQ With JoeBlock With HQWarehouse With SalePriceList With WarehouseOrder build()
  * So an Type-safe Builder Pattern si implemented
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 06/01/16.
  */
object OrderBuilder {

  def apply(context: Context , trxName : String) = {
      new OrderBuilder[Zero, Zero, Zero, Zero, Zero, Zero , Once ](context: Context , trxName : String)
    }

  case class LineItem(product: Product , quantity: Option[Quantity] = None)

  case class OrderBuilder[
  WithOrganizationTracking <: Count,
  WithPartnerTracking <: Count,
  WithWarehouseTracking <: Count ,
  WithPriceListTracking <: Count ,
  WithDocumentTypeTracking <: Count ,
  IsSalesOrder <: Count ,
  WithLinesTracking <: Count
    ](
       context: Context ,
       trxName : String ,
       organization: Option[Organization] = None ,
       partner: Option[Partner] = None ,
       warehouse: Option[Warehouse] = None ,
       priceList: Option[PriceList] = None ,
       documentType: Option[String] = None ,
       isSalesOrder: Option[Boolean] = None,
       orderLines : Map[Product, Option[Quantity]] = Map.empty)
  {

    type IsOnce[T] = =:=[T, Once]
    type IsZero[T] = =:=[T, Zero]

    def With[O <: WithOrganizationTracking : IsZero](o : Organization) =
      copy[Once, WithPartnerTracking, WithWarehouseTracking , WithPriceListTracking, WithDocumentTypeTracking, IsSalesOrder , WithLinesTracking](organization = Some(o))

    def With[P <: WithPartnerTracking : IsZero](p: Partner) =
      copy[WithOrganizationTracking, Once, WithWarehouseTracking , WithPriceListTracking, WithDocumentTypeTracking, IsSalesOrder , WithLinesTracking](partner = Some(p))

    def With[W <: WithWarehouseTracking : IsZero](w : Warehouse) =
      copy[WithOrganizationTracking, WithPartnerTracking , Once , WithPriceListTracking, WithDocumentTypeTracking, IsSalesOrder, WithLinesTracking] (warehouse = Some(w))

    def With[PL <: WithPriceListTracking : IsZero](pl: PriceList) =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, Once , WithDocumentTypeTracking, IsSalesOrder, WithLinesTracking](priceList = Some(pl))

    def With[DT <: WithDocumentTypeTracking : IsZero](dt: String) =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithPriceListTracking, Once, IsSalesOrder, WithLinesTracking](documentType = Some(dt))

    def AsSalesOrder[SO <: IsSalesOrder : IsZero]() =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithPriceListTracking, WithDocumentTypeTracking, Once , WithLinesTracking](isSalesOrder = Some(true))

    def AddLine(product: Product , quantity: Quantity) =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithPriceListTracking, WithDocumentTypeTracking, IsSalesOrder , Once] (orderLines = orderLines + (product -> Some(quantity)))


    def build[
    O <: WithOrganizationTracking : IsOnce,
    P <: WithPartnerTracking : IsOnce,
    W <: WithWarehouseTracking : IsOnce ,
    PL <: WithPriceListTracking : IsOnce,
    DT <: WithDocumentTypeTracking : IsOnce,
    SO <: IsSalesOrder : IsOnce ,
    L <: WithLinesTracking : IsOnce
    ](): Order = {
      val order = new Order(context, 0, trxName)
      order.setAD_Org_ID(organization.get getAD_Org_ID())
      order.setBPartner(partner.get)
      order.setM_PriceList_ID(priceList.get getM_PriceList_ID)
      order.setM_Warehouse_ID(warehouse.get getM_Warehouse_ID)
      order.setC_DocTypeTarget_ID(documentType.get)
      order.setIsSOTrx(isSalesOrder.get)
      order.setC_DocTypeTarget_ID()
      order.saveEx()

      for ((product , quantity) <- orderLines if product != null)
      {
        val orderLine = new OrderLine(order)
        orderLine.setProduct(product)
        orderLine.setC_UOM_ID(product.getC_UOM_ID)
        orderLine.setQty(quantity.get.bigDecimal)
        orderLine.saveEx()
      }
      order.load(trxName)
      order
    }
  }
}

