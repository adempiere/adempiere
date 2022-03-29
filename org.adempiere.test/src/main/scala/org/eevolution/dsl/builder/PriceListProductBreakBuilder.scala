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
  * Price List Product Break Builder allows create a Price List Product Break using a DSL
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 02/03/16.
  */
object PriceListProductBreakBuilder {

  def apply()(implicit context: Context , transaction: Transaction): Builder[Mandatory, Mandatory, Optional, Mandatory, Optional, Optional, Optional] = {
      new Builder[Mandatory, Mandatory, Optional , Mandatory , Optional, Optional, Optional]()
  }

  case class Builder[
  WithPriceListVersionTracking <: Optional,
  WithProductTracking <: Optional ,
  WithPartnerTracking <: Optional ,
  WithBreakValueTracking <: Optional ,
  WithPriceListAmountTracking <: Optional ,
  WithPriceStdAmountTracking <: Optional ,
  WithPriceLimitAmountTracking <: Optional
  ](
     priceListVersion: Option[dsl.PriceListVersion] = None,
     product: Option[dsl.Product] = None,
     partner : Option[dsl.Partner] = None,
     breakValue : Option[BigDecimal] = None,
     priceList: Option[BigDecimal] = None,
     priceStd : Option[BigDecimal] = None,
     priceLimit : Option[BigDecimal] = None)
   (implicit context: Context, transaction: Transaction)
  {
    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withPriceListVersion[PLV <: WithPriceListVersionTracking : IsMandatory] (plv: dsl.PriceListVersion): Builder[Once, WithProductTracking, WithPartnerTracking, WithBreakValueTracking, WithPriceListAmountTracking, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
    copy[
      Once,
      WithProductTracking,
      WithPartnerTracking,
      WithBreakValueTracking,
      WithPriceListAmountTracking,
      WithPriceStdAmountTracking,
      WithPriceLimitAmountTracking
      ](priceListVersion = Some(plv))

    def withProduct[Product <: WithProductTracking : IsMandatory] (p: dsl.Product): Builder[WithPriceListVersionTracking, Once, WithPartnerTracking, WithBreakValueTracking, WithPriceListAmountTracking, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        Once,
        WithPartnerTracking,
        WithBreakValueTracking,
        WithPriceListAmountTracking,
        WithPriceStdAmountTracking,
        WithPriceLimitAmountTracking
        ](product = Some(p))

    def withPartner[Partner<: WithPartnerTracking : IsMandatory] (bp: dsl.Partner): Builder[WithPriceListVersionTracking, WithProductTracking, Once, WithBreakValueTracking, WithPriceListAmountTracking, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        Once,
        WithBreakValueTracking,
        WithPriceListAmountTracking,
        WithPriceStdAmountTracking,
        WithPriceLimitAmountTracking
        ](partner = Some(bp))


    def withBreakValue[BreakValue <: WithBreakValueTracking : IsMandatory] (bv: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, WithPartnerTracking, Once, WithPriceListAmountTracking, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        WithPartnerTracking,
        Once,
        WithPriceListAmountTracking,
        WithPriceStdAmountTracking,
        WithPriceLimitAmountTracking
        ](breakValue = Some(bv))

    def withPriceListAmount[PriceListAmount <: WithPriceListAmountTracking : IsMandatory] (list: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, WithPartnerTracking, WithBreakValueTracking, Once, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        WithPartnerTracking,
        WithBreakValueTracking,
        Once,
        WithPriceStdAmountTracking,
        WithPriceLimitAmountTracking
        ](priceList = Some(list))

    def withPriceStdAmount[PriceListStdAmount <: WithPriceStdAmountTracking : IsMandatory] (std: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, WithPartnerTracking, WithBreakValueTracking, WithPriceListAmountTracking, Once, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        WithPartnerTracking,
        WithBreakValueTracking,
        WithPriceListAmountTracking,
        Once,
        WithPriceLimitAmountTracking
        ](priceStd = Some(std))

    def withPriceLimitAmount[PriceLimitAmount <: WithPriceLimitAmountTracking: IsMandatory] (limit: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, WithPartnerTracking, WithBreakValueTracking, WithPriceListAmountTracking, WithPriceStdAmountTracking, Once] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        WithPartnerTracking,
        WithBreakValueTracking,
        WithPriceListAmountTracking,
        WithPriceStdAmountTracking,
        Once
        ](priceLimit = Some(limit))


    def build[
    PLV <: WithPriceListVersionTracking : IsOnce,
    Product <: WithProductTracking : IsOnce,
    Partner <: WithPartnerTracking : IsOnce,
    BreakValue <: WithBreakValueTracking : IsOnce,
    PriceListAmount <: WithPriceListAmountTracking: IsOnce,
    PriceStdAmount <: WithPriceStdAmountTracking: IsOnce,
    PriceLimitAmount <: WithPriceLimitAmountTracking: IsOnce
   ]() : ProductPriceBreak = {
      val productPriceBreak = new ProductPriceBreak(context, 0 , transaction.getTrxName)
      productPriceBreak.setAD_Org_ID(priceListVersion.get.getAD_Org_ID)
      productPriceBreak.setM_PriceList_Version_ID(priceListVersion.get.get_ID)
      productPriceBreak.setM_Product_ID(product.get.get_ID)
      if (partner.isDefined)
        productPriceBreak.setC_BPartner_ID(partner.get.get_ID)
      productPriceBreak.setBreakValue(breakValue.get.bigDecimal)
      productPriceBreak.setPriceList(priceList.get.bigDecimal)
      productPriceBreak.setPriceStd(priceStd.get.bigDecimal)
      productPriceBreak.setPriceLimit(priceLimit.get.bigDecimal)
      productPriceBreak.saveEx()
      productPriceBreak
    }
  }
}

