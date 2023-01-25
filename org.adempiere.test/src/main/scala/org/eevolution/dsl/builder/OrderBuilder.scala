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
  * ***************************************************************************
  */

package org.eevolution.dsl.builder

import org.compiere.model.*
import org.adempiere.core.domains.models.*
import org.compiere.process.DocAction
import org.eevolution.dsl
import org.eevolution.dsl.*

/** Order Builder allows create a sales order using a DSL
  * ie val order = OrderBuilder(Context , trxName)
  *        .AddLine(Oak , QtyOak) AddLine (Azalea , QtyAzalea) AsSalesOrder() With HQ With JoeBlock With HQWarehouse With SalePriceList With WarehouseOrder build()
  * So an Type-safe Builder Pattern si implemented
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 06/01/16.
  */
object OrderBuilder {

  def apply(context: Context, trxName: String): Builder[
    Mandatory,
    Mandatory,
    Mandatory,
    Mandatory,
    Mandatory,
    Mandatory,
    Once
  ] = {
    new Builder[
      Mandatory,
      Mandatory,
      Mandatory,
      Mandatory,
      Mandatory,
      Mandatory,
      Once
    ](context: Context, trxName: String)
  }

  case class LineItem(product: Product, quantity: Option[Quantity] = None)

  case class Builder[
      WithOrganizationTracking <: Optional,
      WithPartnerTracking <: Optional,
      WithWarehouseTracking <: Optional,
      WithPriceListTracking <: Optional,
      WithBaseDocumentTypeTracking <: Optional,
      WithSubTypeDocumentTracking <: Optional,
      WithLinesTracking <: Optional
  ](
      context: Context,
      trxName: String,
      organization: Option[Organization] = None,
      partner: Option[Partner] = None,
      warehouse: Option[Warehouse] = None,
      priceList: Option[PriceList] = None,
      baseDocumentType: Option[String] = None,
      subTypeDocument: Option[String] = None,
      orderLines: Map[Product, Option[Quantity]] = Map.empty
  ) {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withOrganization[Organization <: WithOrganizationTracking: IsMandatory](
        o: dsl.Organization
    ): Builder[
      Once,
      WithPartnerTracking,
      WithWarehouseTracking,
      WithPriceListTracking,
      WithBaseDocumentTypeTracking,
      WithSubTypeDocumentTracking,
      WithLinesTracking
    ] =
      copy[
        Once,
        WithPartnerTracking,
        WithWarehouseTracking,
        WithPriceListTracking,
        WithBaseDocumentTypeTracking,
        WithSubTypeDocumentTracking,
        WithLinesTracking
      ](organization = Some(o))

    def withPartner[Partner <: WithPartnerTracking: IsMandatory](
        p: dsl.Partner
    ): Builder[
      WithOrganizationTracking,
      Once,
      WithWarehouseTracking,
      WithPriceListTracking,
      WithBaseDocumentTypeTracking,
      WithSubTypeDocumentTracking,
      WithLinesTracking
    ] =
      copy[
        WithOrganizationTracking,
        Once,
        WithWarehouseTracking,
        WithPriceListTracking,
        WithBaseDocumentTypeTracking,
        WithSubTypeDocumentTracking,
        WithLinesTracking
      ](partner = Some(p))

    def withWarehouse[Warehouse <: WithWarehouseTracking: IsMandatory](
        w: dsl.Warehouse
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      Once,
      WithPriceListTracking,
      WithBaseDocumentTypeTracking,
      WithSubTypeDocumentTracking,
      WithLinesTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        Once,
        WithPriceListTracking,
        WithBaseDocumentTypeTracking,
        WithSubTypeDocumentTracking,
        WithLinesTracking
      ](warehouse = Some(w))

    def withPriceList[PriceList <: WithPriceListTracking: IsMandatory](
        pl: dsl.PriceList
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithWarehouseTracking,
      Once,
      WithBaseDocumentTypeTracking,
      WithSubTypeDocumentTracking,
      WithLinesTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithWarehouseTracking,
        Once,
        WithBaseDocumentTypeTracking,
        WithSubTypeDocumentTracking,
        WithLinesTracking
      ](priceList = Some(pl))

    def withBaseDocumentType[
        BaseDocumentType <: WithBaseDocumentTypeTracking: IsMandatory
    ](bdt: String): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithWarehouseTracking,
      WithPriceListTracking,
      Once,
      WithSubTypeDocumentTracking,
      WithLinesTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithWarehouseTracking,
        WithPriceListTracking,
        Once,
        WithSubTypeDocumentTracking,
        WithLinesTracking
      ](baseDocumentType = Some(bdt))

    def withSubType[
        SubTypeDocument <: WithSubTypeDocumentTracking: IsMandatory
    ](sdt: String): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithWarehouseTracking,
      WithPriceListTracking,
      WithBaseDocumentTypeTracking,
      Once,
      WithLinesTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithWarehouseTracking,
        WithPriceListTracking,
        WithBaseDocumentTypeTracking,
        Once,
        WithLinesTracking
      ](subTypeDocument = Some(sdt))

    def AddLine[OrderLine <: WithLinesTracking: IsOnce](
        product: Product,
        quantity: Quantity
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithWarehouseTracking,
      WithPriceListTracking,
      WithBaseDocumentTypeTracking,
      WithSubTypeDocumentTracking,
      Once
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithWarehouseTracking,
        WithPriceListTracking,
        WithBaseDocumentTypeTracking,
        WithSubTypeDocumentTracking,
        Once
      ](orderLines = orderLines + (product -> Some(quantity)))

    def build[
        Organization <: WithOrganizationTracking: IsOnce,
        Partner <: WithPartnerTracking: IsOnce,
        Warehouse <: WithWarehouseTracking: IsOnce,
        PriceList <: WithPriceListTracking: IsOnce,
        BaseDocumentType <: WithBaseDocumentTypeTracking: IsOnce,
        SubDocumentType <: WithSubTypeDocumentTracking: IsOnce,
        OrderLine <: WithLinesTracking: IsOnce
    ](): Order = {

      val order = new Order(context, 0, trxName)
      order.setAD_Org_ID(organization.get.getAD_Org_ID())
      order.setBPartner(partner.get)
      order.setM_PriceList_ID(priceList.get.getM_PriceList_ID)
      order.setM_Warehouse_ID(warehouse.get.getM_Warehouse_ID)
      if (X_C_DocType.DOCBASETYPE_SalesOrder == baseDocumentType.get) {
        val documentTypeId = MDocType.getDocTypeBaseOnSubType(
          organization.get.getAD_Org_ID,
          baseDocumentType.get,
          subTypeDocument.getOrElse(X_C_DocType.DOCSUBTYPESO_StandardOrder)
        )
        order.setC_DocType_ID(documentTypeId)
        order.setC_DocTypeTarget_ID(documentTypeId)
        order.setIsSOTrx(true);
      } else if (
        X_C_DocType.DOCBASETYPE_PurchaseOrder == baseDocumentType.get
      ) {
        val documentTypeId = MDocType.getDocType(baseDocumentType.get)
        order.setC_DocType_ID(documentTypeId)
        order.setC_DocTypeTarget_ID(documentTypeId)
        order.setIsSOTrx(false);
      }

      order.setDocAction(DocAction.STATUS_Drafted)
      order.setDocAction(DocAction.ACTION_Complete)
      order.saveEx()

      for ((product, quantity) <- orderLines if product != null) {
        val orderLine = new dsl.OrderLine(order)
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
