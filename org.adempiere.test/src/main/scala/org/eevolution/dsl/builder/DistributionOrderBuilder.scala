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

import org.compiere.model.{MDocType, X_C_DocType}
import org.compiere.process.DocAction
import org.eevolution.dsl
import org.eevolution.dsl.*

/**
  * Distribution Order Builder allows create a sales order using a DSL
  * So an Type-safe Builder Pattern is implemented
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 06/01/16.
  */
object DistributionOrderBuilder {

  def apply(context: Context , trxName : String) = {
      new Builder[Mandatory, Mandatory, Mandatory, Mandatory , Once , Once ](context: Context , trxName : String)
    }

  case class LineItem(product: Product , quantity: Option[Quantity] = None)

  case class Builder[
  WithOrganizationTracking <: Optional,
  WithPartnerTracking <: Optional,
  WithWarehouseTracking <: Optional ,
  WithBaseDocumentTypeTracking <: Optional ,
  WithDropShipTracking <: Optional ,
  WithLinesTracking <: Optional
    ](
       context: Context,
       trxName : String,
       organization: Option[Organization] = None,
       partner: Option[Partner] = None,
       warehouse: Option[Warehouse] = None,
       baseDocumentType: Option[String] = None,
       dropShip: Option[Boolean] = None,
       orderLines : Map[Product, Option[Quantity]] = Map.empty)
  {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withOrganization[Organization <: WithOrganizationTracking : IsMandatory](o : dsl.Organization): Builder[Once, WithPartnerTracking, WithWarehouseTracking, WithBaseDocumentTypeTracking, WithDropShipTracking, WithLinesTracking] =
      copy[Once, WithPartnerTracking, WithWarehouseTracking , WithBaseDocumentTypeTracking , WithDropShipTracking, WithLinesTracking](organization = Some(o))

    def withPartner[Partner <: WithPartnerTracking : IsMandatory](p: dsl.Partner): Builder[WithOrganizationTracking, Once, WithWarehouseTracking, WithBaseDocumentTypeTracking, WithDropShipTracking, WithLinesTracking] =
      copy[WithOrganizationTracking, Once, WithWarehouseTracking , WithBaseDocumentTypeTracking , WithDropShipTracking, WithLinesTracking](partner = Some(p))

    def withWarehouse[Warehouse <: WithWarehouseTracking : IsMandatory](w : dsl.Warehouse): Builder[WithOrganizationTracking, WithPartnerTracking, Once, WithBaseDocumentTypeTracking, WithDropShipTracking, WithLinesTracking] =
      copy[WithOrganizationTracking, WithPartnerTracking , Once , WithBaseDocumentTypeTracking, WithDropShipTracking, WithLinesTracking] (warehouse = Some(w))

    def withBaseDocumentType[BaseDocumentType <: WithBaseDocumentTypeTracking : IsMandatory](bdt: String): Builder[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, Once, WithDropShipTracking, WithLinesTracking] =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, Once, WithDropShipTracking , WithLinesTracking](baseDocumentType = Some(bdt))

    def withDropShip[DropShipDocument <: WithDropShipTracking : IsMandatory](ds: Boolean): Builder[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithBaseDocumentTypeTracking, Once, WithLinesTracking] =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithBaseDocumentTypeTracking, Once , WithLinesTracking](dropShip = Some(ds))

    def AddLine[DistributionOrderLine <: WithLinesTracking : IsOnce ](product: Product , quantity: Quantity): Builder[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithBaseDocumentTypeTracking, WithDropShipTracking, Once] =
      copy[WithOrganizationTracking, WithPartnerTracking, WithWarehouseTracking, WithBaseDocumentTypeTracking,WithDropShipTracking, Once] (orderLines = orderLines + (product -> Some(quantity)))


    def build[
    Organization <: WithOrganizationTracking : IsOnce,
    Partner <: WithPartnerTracking : IsOnce,
    Warehouse <: WithWarehouseTracking : IsOnce ,
    BaseDocumentType <: WithBaseDocumentTypeTracking : IsOnce,
    SubDocumentType <:   WithDropShipTracking : IsOnce,
    DistributionOrderLine <: WithLinesTracking : IsOnce
    ](): DistributionOrder = {


      val order = new DistributionOrder(context, 0, trxName)
      order.setAD_Org_ID(organization.get.getAD_Org_ID())
      order.setBPartner(partner.get)
      order.setM_Warehouse_ID(warehouse.get.getM_Warehouse_ID)
      if (X_C_DocType.DOCBASETYPE_DistributionOrder== baseDocumentType.get)
      {
        val documentTypeId = MDocType.getDocType(baseDocumentType.get,organization.get.getAD_Org_ID)
        order.setC_DocType_ID(documentTypeId)
        order.setIsSOTrx(true);
      }

      order.setIsDropShip(dropShip.getOrElse(false))
      order.setDocAction(DocAction.STATUS_Drafted)
      order.setDocAction(DocAction.ACTION_Complete)
      order.saveEx()

      for ((product , quantity) <- orderLines if product != null)
      {
        val orderLine = new dsl.DistributionOrderLine(order)
        orderLine.setProduct(product)
        orderLine.setC_UOM_ID(product.getC_UOM_ID)
        orderLine.setQty(quantity.get.bigDecimal)
        orderLine.setConfirmedQty(quantity.get.bigDecimal)
        orderLine.saveEx()
      }
      order.load(trxName)
      order
    }
  }
}

