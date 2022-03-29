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
  * Price List Product Builder allows create a Price List Product using a DSL
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/02/16.
  */
object PriceListProductBuilder {

  def apply()(implicit context: Context , transaction: Transaction): Builder[Mandatory, Mandatory, Optional, Optional, Optional] = {
      new Builder[Mandatory, Mandatory, Optional, Optional, Optional]()
  }

  case class Builder[
  WithPriceListVersionTracking <: Optional,
  WithProductTracking <: Optional ,
  WithPriceListAmountTracking <: Optional ,
  WithPriceStdAmountTracking <: Optional ,
  WithPriceLimitAmountTracking <: Optional
  ](
     priceListVersion: Option[dsl.PriceListVersion] = None,
     product: Option[dsl.Product] = None,
     priceList: Option[BigDecimal] = None,
     priceStd : Option[BigDecimal] = None,
     priceLimit : Option[BigDecimal] = None)
   (implicit context: Context, transaction: Transaction)
  {
    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withPriceListVersion[PLV <: WithPriceListVersionTracking : IsMandatory] (plv: dsl.PriceListVersion): Builder[Once, WithProductTracking, WithPriceListAmountTracking, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
    copy[
      Once,
      WithProductTracking,
      WithPriceListAmountTracking,
      WithPriceStdAmountTracking,
      WithPriceLimitAmountTracking
      ](priceListVersion = Some(plv))

    def withProduct[Product <: WithProductTracking : IsMandatory] (p: dsl.Product): Builder[WithPriceListVersionTracking, Once, WithPriceListAmountTracking, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        Once,
        WithPriceListAmountTracking,
        WithPriceStdAmountTracking,
        WithPriceLimitAmountTracking
        ](product = Some(p))


    def withPriceListAmount[PriceListAmount <: WithPriceListAmountTracking : IsMandatory] (list: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, Once, WithPriceStdAmountTracking, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        Once,
        WithPriceStdAmountTracking,
        WithPriceLimitAmountTracking
        ](priceList = Some(list))

    def withPriceStdAmount[PriceListStdAmount <: WithPriceStdAmountTracking : IsMandatory] (std: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, WithPriceListAmountTracking, Once, WithPriceLimitAmountTracking] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        WithPriceListAmountTracking,
        Once,
        WithPriceLimitAmountTracking
        ](priceStd = Some(std))

    def withPriceLimitAmount[PriceLimitAmount <: WithPriceLimitAmountTracking: IsMandatory] (limit: BigDecimal): Builder[WithPriceListVersionTracking, WithProductTracking, WithPriceListAmountTracking, WithPriceStdAmountTracking, Once] =
      copy[
        WithPriceListVersionTracking,
        WithProductTracking,
        WithPriceListAmountTracking,
        WithPriceStdAmountTracking,
        Once
        ](priceLimit = Some(limit))


    def build[
    PLV <: WithPriceListVersionTracking : IsOnce,
    Product <: WithProductTracking : IsOnce,
    PriceListAmount <: WithPriceListAmountTracking: IsOnce,
    PriceStdAmount <: WithPriceStdAmountTracking: IsOnce,
    PriceLimitAmount <: WithPriceLimitAmountTracking: IsOnce
   ]() : ProductPrice = {
      val productPrice = new ProductPrice(context, priceListVersion.get.getM_PriceList_Version_ID , product.get.getM_Product_ID, transaction.getTrxName)
      productPrice.setAD_Org_ID(priceListVersion.get.getAD_Org_ID)
      productPrice.setPrices(priceList.get.bigDecimal, priceStd.get.bigDecimal , priceLimit.get.bigDecimal)
      productPrice.saveEx()
      productPrice
    }
  }
}

