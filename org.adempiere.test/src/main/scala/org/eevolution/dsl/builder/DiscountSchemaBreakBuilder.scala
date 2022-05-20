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

import org.eevolution.dsl
import org.eevolution.dsl.*

/**
  * Discount Schema Break Builder allows create a Discount Schema Break using a DSL
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 02/03/16.
  */
object DiscountSchemaBreakBuilder {

  def apply()(implicit context: Context , transaction: Transaction): Builder[Mandatory, Mandatory, Optional, Optional, Mandatory, Optional, Optional] = {
      new Builder[Mandatory, Mandatory, Optional , Optional , Mandatory, Optional, Optional]()
  }

  case class Builder[
  WithDiscountSchemaTracking <: Optional,
  WithSequenceNo <: Optional,
  WithProductTracking <: Optional ,
  WithProductCategoryTracking <: Optional,
  WithBreakValueTracking <: Optional ,
  WithIsPartnerFlatDiscountTracking <: Optional ,
  WithBreakDiscountTracking <: Optional
  ](
     discountSchema: Option[dsl.DiscountSchema] = None,
     sequenceNo : Option[Integer] = None,
     product: Option[dsl.Product] = None,
     productCategory : Option[dsl.ProductCategory] = None,
     breakValue : Option[BigDecimal] = None,
     isPartnerFlatDiscount: Option[Boolean] = Some(false),
     breakDiscount : Option[BigDecimal] = None
  )(implicit context: Context, transaction: Transaction)
  {
    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withDiscountSchema[DiscountSchema <: WithDiscountSchemaTracking : IsMandatory](ds: dsl.DiscountSchema): Builder[Once, WithSequenceNo, WithProductTracking, WithProductCategoryTracking, WithBreakValueTracking, WithIsPartnerFlatDiscountTracking, WithBreakDiscountTracking] =
    copy[
      Once,
      WithSequenceNo,
      WithProductTracking,
      WithProductCategoryTracking,
      WithBreakValueTracking,
      WithIsPartnerFlatDiscountTracking,
      WithBreakDiscountTracking
      ](discountSchema = Some(ds))

    def withSequenceNo[SequenceNo <: WithSequenceNo : IsMandatory](sn: Integer): Builder[Once, WithSequenceNo, WithProductTracking, WithProductCategoryTracking, WithBreakValueTracking, WithIsPartnerFlatDiscountTracking, WithBreakDiscountTracking] =
      copy[
        Once,
        WithSequenceNo,
        WithProductTracking,
        WithProductCategoryTracking,
        WithBreakValueTracking,
        WithIsPartnerFlatDiscountTracking,
        WithBreakDiscountTracking
        ](sequenceNo = Some(sn))

    def withProduct[Product <: WithProductTracking : IsMandatory] (p: dsl.Product): Builder[WithDiscountSchemaTracking, WithSequenceNo, Once, WithProductCategoryTracking, WithBreakValueTracking, WithIsPartnerFlatDiscountTracking, WithBreakDiscountTracking] =
      copy[
        WithDiscountSchemaTracking,
        WithSequenceNo,
        Once,
        WithProductCategoryTracking,
        WithBreakValueTracking,
        WithIsPartnerFlatDiscountTracking,
        WithBreakDiscountTracking
        ](product = Some(p))

    def withProductCategory[ProductCategory<: WithProductCategoryTracking : IsMandatory] (pc: dsl.ProductCategory): Builder[WithDiscountSchemaTracking, WithSequenceNo, WithProductTracking, Once, WithBreakValueTracking, WithIsPartnerFlatDiscountTracking, WithBreakDiscountTracking] =
      copy[
        WithDiscountSchemaTracking,
        WithSequenceNo,
        WithProductTracking,
        Once,
        WithBreakValueTracking,
        WithIsPartnerFlatDiscountTracking,
        WithBreakDiscountTracking
        ](productCategory = Some(pc))


    def withBreakValue[BreakValue <: WithBreakValueTracking : IsMandatory] (bv: BigDecimal): Builder[WithDiscountSchemaTracking, WithSequenceNo, WithProductTracking, WithProductCategoryTracking, Once, WithIsPartnerFlatDiscountTracking, WithBreakDiscountTracking] =
      copy[
        WithDiscountSchemaTracking,
        WithSequenceNo,
        WithProductTracking,
        WithProductCategoryTracking,
        Once,
        WithIsPartnerFlatDiscountTracking,
        WithBreakDiscountTracking
        ](breakValue = Some(bv))

    def asPartnerFlatDiscount[IsPartnerFlatDiscount <: WithIsPartnerFlatDiscountTracking : IsMandatory](): Builder[WithDiscountSchemaTracking, WithSequenceNo, WithProductTracking, WithProductCategoryTracking, WithBreakValueTracking, Once, WithBreakDiscountTracking] =
      copy[
        WithDiscountSchemaTracking,
        WithSequenceNo,
        WithProductTracking,
        WithProductCategoryTracking,
        WithBreakValueTracking,
        Once,
        WithBreakDiscountTracking
        ](isPartnerFlatDiscount = Some(true))

    def withBreakDiscount[BreakDiscount <: WithBreakDiscountTracking : IsMandatory](bd: BigDecimal): Builder[WithDiscountSchemaTracking, WithSequenceNo, WithProductTracking, WithProductCategoryTracking, WithBreakValueTracking, WithIsPartnerFlatDiscountTracking, Once] =
      copy[
        WithDiscountSchemaTracking,
        WithSequenceNo,
        WithProductTracking,
        WithProductCategoryTracking,
        WithBreakValueTracking,
        WithIsPartnerFlatDiscountTracking,
        Once
        ](breakDiscount = Some(bd))

    def build[
    PLV <: WithDiscountSchemaTracking : IsOnce,
    Product <: WithProductTracking : IsOnce,
    ProductCategory <: WithProductCategoryTracking : IsOnce,
    BreakValue <: WithBreakValueTracking : IsOnce,
    IsPartnerFlatDiscount <: WithIsPartnerFlatDiscountTracking: IsOnce,
    BreakDiscount <: WithBreakDiscountTracking: IsOnce
   ]() : DiscountSchemaBreak = {
      val discountSchemaBreak = new DiscountSchemaBreak(context, 0 , transaction.getTrxName)
      discountSchemaBreak.setAD_Org_ID(discountSchema.get.getAD_Org_ID)
      discountSchemaBreak.setM_DiscountSchema_ID(discountSchema.get.get_ID())
      discountSchemaBreak.setSeqNo(sequenceNo.get)
      if(productCategory.isDefined)
        discountSchemaBreak.setM_Product_Category_ID(productCategory.get.get_ID())
      if (product.isDefined)
        discountSchemaBreak.setM_Product_ID(product.get.get_ID)
      discountSchemaBreak.setBreakValue(breakValue.get.bigDecimal)
      discountSchemaBreak.setIsBPartnerFlatDiscount(isPartnerFlatDiscount.get)
      discountSchemaBreak.setBreakDiscount(breakDiscount.get.bigDecimal)
      discountSchemaBreak.saveEx()
      discountSchemaBreak
    }
  }
}

