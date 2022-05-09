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
  * Price List Builder allows create a Price List using a DSL
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/02/16.
  */
object PriceListBuilder {

  def apply()(implicit context: Context , transaction: Transaction): Builder[Mandatory, Mandatory, Optional, Mandatory, Mandatory, Optional, Optional, Optional, Optional, Optional] = {
      new Builder[Mandatory, Mandatory, Optional, Mandatory, Mandatory , Optional , Optional , Optional , Optional , Optional]()
  }

  case class Builder[
  WithOrganizationTracking <: Optional,
  WithNameTracking <: Optional ,
  WithDescriptionTracking <: Optional ,
  WithCurrency <: Optional ,
  WithPricePrecision <: Optional ,
  WithIsDefault <: Optional,
  WithIsSalesPriceList <: Optional,
  WithIsTaxIncluded  <: Optional,
  WithIsForcePriceLimit <: Optional,
  WithIsNetPrice <: Optional
  ](
     organization: Option[Organization] = None,
     name: Option[String] = None,
     description: Option[String] = None,
     currency: Option[Currency] = None,
     pricePrecision : Option[BigDecimal] = None,
     isDefault : Option[Boolean] = Some(false),
     isSalesPriceList : Option[Boolean] = Some(false),
     isTaxIncluded : Option[Boolean] = Some(false),
     isForcePriceLimit : Option[Boolean] = Some(false),
     isNetPrice : Option[Boolean] = Some(false)
      )(implicit context: Context, transaction: Transaction)
  {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withOrganization[Organization <: WithOrganizationTracking : IsMandatory](o : dsl.Organization): Builder[Once, WithNameTracking, WithDescriptionTracking, WithCurrency, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        Once,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](organization = Some(o))

    def withName[Name <: WithNameTracking : IsMandatory](n: String): Builder[WithOrganizationTracking, Once, WithDescriptionTracking, WithCurrency, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        Once ,
        WithDescriptionTracking,
        WithCurrency ,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](name = Some(n))

    def withDescription[Description <: WithDescriptionTracking : IsMandatory](d: String): Builder[WithOrganizationTracking, WithNameTracking, Once, WithCurrency, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        Once,
        WithCurrency ,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](description = Some(d))

    def withCurrency[Currency <: WithCurrency : IsMandatory](c: dsl.Currency): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, Once, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        Once ,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](currency = Some(c))


    def withPricePrecision[PricePrecision <: WithPricePrecision: IsMandatory](p:BigDecimal): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, WithCurrency, Once, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency ,
        Once,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](pricePrecision = Some(p))

    def asDefault[IsDefault <: WithIsDefault: IsMandatory](): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, WithCurrency, WithPricePrecision, Once, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency ,
        WithPricePrecision,
        Once,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](isDefault = Some(true))

    def asSalesPriceList[IsSalesPriceList <: WithIsSalesPriceList: IsMandatory](): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, WithCurrency, WithPricePrecision, WithIsDefault, Once, WithIsTaxIncluded, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency ,
        WithPricePrecision,
        WithIsDefault,
        Once ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        WithIsNetPrice](isSalesPriceList = Some(true))

    def asTaxIncluded[IsTaxIncluded <: WithIsTaxIncluded: IsMandatory](): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, WithCurrency, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, Once, WithIsForcePriceLimit, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency ,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        Once ,
        WithIsForcePriceLimit,
        WithIsNetPrice](isTaxIncluded = Some(true))

    def asForcePriceLimit[IsForcePriceLimit <: WithIsForcePriceLimit: IsMandatory](): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, WithCurrency, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, Once, WithIsNetPrice] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency ,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        Once,
        WithIsNetPrice](isForcePriceLimit = Some(true))

    def asNetPrice[IsNetPrice <: WithIsNetPrice: IsMandatory](): Builder[WithOrganizationTracking, WithNameTracking, WithDescriptionTracking, WithCurrency, WithPricePrecision, WithIsDefault, WithIsSalesPriceList, WithIsTaxIncluded, WithIsForcePriceLimit, Once] =
      copy[
        WithOrganizationTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithCurrency ,
        WithPricePrecision,
        WithIsDefault,
        WithIsSalesPriceList ,
        WithIsTaxIncluded ,
        WithIsForcePriceLimit,
        Once](isNetPrice = Some(true))


    def build[
    Organization <: WithOrganizationTracking : IsOnce,
    Name <: WithNameTracking : IsOnce,
    Description <: WithDescriptionTracking : IsOnce,
    Currency <: WithCurrency : IsOnce,
    PricePrecision <: WithPricePrecision : IsOnce,
    IsDefault <: WithIsDefault : IsOnce,
    IsSalesPriceList <: WithIsSalesPriceList : IsOnce,
    IsTaxIncluded  <: WithIsTaxIncluded : IsOnce ,
    IsForcePriceLimit <: WithIsForcePriceLimit : IsOnce,
    IsNetPrice <: WithIsNetPrice : IsOnce
    ](): PriceList = {
      val priceList = new PriceList(context, 0, transaction.getTrxName)
      priceList.setAD_Org_ID(organization.get.getAD_Org_ID)
      priceList.setName(name.get)
      priceList.setDescription(description.get)
      priceList.setC_Currency_ID(currency.get.getC_Currency_ID)
      priceList.setIsDefault(isDefault.get)
      priceList.setIsSOPriceList(isSalesPriceList.get)
      priceList.setIsTaxIncluded(isTaxIncluded.get)
      priceList.setEnforcePriceLimit(isForcePriceLimit.get)
      priceList.saveEx()
      priceList
    }
  }
}

