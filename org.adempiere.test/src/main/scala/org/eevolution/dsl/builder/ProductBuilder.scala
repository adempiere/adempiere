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
  * Product Builder allows create a Product using a DSL
  * So an Type-safe Builder Pattern si implemented
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/02/16.
  */
object ProductBuilder {

  def apply()(implicit context: Context , transaction: Transaction): Builder[Mandatory, Mandatory, Mandatory, Optional, Mandatory, Mandatory, Mandatory, Optional, Mandatory, Optional] = {
      new Builder[Mandatory, Mandatory, Mandatory, Optional, Mandatory , Mandatory , Mandatory , Optional , Mandatory , Optional]()
  }

  case class Builder[
  WithOrganizationTracking <: Optional,
  WithValueTracking <: Optional,
  WithNameTracking <: Optional ,
  WithDescriptionTracking <: Optional ,
  WithProductCategoryTracking <: Optional ,
  WithTaxCategoryTracking <: Optional ,
  WithUOMTracking <: Optional ,
  WithProductTypeTracking <: Optional,
  WithIsSoldTracking <: Optional,
  WithIsPurchasedTracking <: Optional
  ](
    organization: Option[Organization] = None,
    value: Option[String] = None,
    name: Option[String] = None,
    description: Option[String] = None,
    productCategory: Option[dsl.ProductCategory] = None,
    taxCategory: Option[dsl.TaxCategory] = None,
    uom: Option[dsl.UOM] = None,
    productType : Option[String] = Some("I"),
    isSold : Option[Boolean] = Some(false),
    isPurchased : Option[Boolean] = Some(false))(implicit context: Context, transaction: Transaction)
  {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withOrganization[Organization <: WithOrganizationTracking : IsMandatory](o : dsl.Organization): Builder[Once, WithValueTracking, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        Once,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](organization = Some(o))

    def withValue[Value <: WithValueTracking : IsMandatory](v: String): Builder[WithOrganizationTracking, Once, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        Once,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](value = Some(v))

    def withName[Name <: WithNameTracking : IsMandatory](n : String): Builder[WithOrganizationTracking, WithValueTracking, Once, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        Once ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking] (name = Some(n))

    def withDescription[Description <: WithDescriptionTracking : IsMandatory](d: String): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, Once, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        Once,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](description = Some(d))

    def withProductCategory[ProductCategory <: WithProductCategoryTracking : IsMandatory](pc: dsl.ProductCategory): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, WithDescriptionTracking, Once, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        Once ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](productCategory = Some(pc))

    def withTaxCategory[TaxCategory <: WithTaxCategoryTracking : IsMandatory](tc: dsl.TaxCategory): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, Once, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        Once,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](taxCategory = Some(tc))

    def withUOM[UOM <: WithUOMTracking : IsMandatory](u: dsl.UOM): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, Once, WithProductTypeTracking, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        Once,
        WithProductTypeTracking ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](uom = Some(u))

    def withProductType[ProductType <: WithProductTypeTracking : IsMandatory](pt : String): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, Once, WithIsSoldTracking, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        Once ,
        WithIsSoldTracking ,
        WithIsPurchasedTracking](productType = Some(pt))

    def asSold[IsSold <: WithIsSoldTracking : IsMandatory](): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, Once, WithIsPurchasedTracking] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        Once ,
        WithIsPurchasedTracking](isSold = Some(true))

    def asPurchased[IsPurchased <: WithIsPurchasedTracking : IsMandatory](): Builder[WithOrganizationTracking, WithValueTracking, WithNameTracking, WithDescriptionTracking, WithProductCategoryTracking, WithTaxCategoryTracking, WithUOMTracking, WithProductTypeTracking, WithIsSoldTracking, Once] =
      copy[
        WithOrganizationTracking,
        WithValueTracking,
        WithNameTracking ,
        WithDescriptionTracking,
        WithProductCategoryTracking ,
        WithTaxCategoryTracking,
        WithUOMTracking,
        WithProductTypeTracking ,
        WithIsSoldTracking,
        Once](isPurchased = Some(true))

    def build[
    Organization <: WithOrganizationTracking : IsOnce,
    Value <: WithValueTracking : IsOnce,
    Name <: WithNameTracking : IsOnce,
    Description <: WithDescriptionTracking : IsOnce,
    ProductCategory <: WithProductCategoryTracking : IsOnce,
    TaxCategory <: WithTaxCategoryTracking : IsOnce,
    UOM <: WithUOMTracking : IsOnce,
    ProductType <: WithProductTypeTracking : IsOnce,
    IsSold <: WithIsSoldTracking : IsOnce ,
    IsPurchased <: WithIsPurchasedTracking : IsOnce
    ](): Product = {
      val product = new Product(context, 0, transaction.getTrxName)
      product.setValue(value.get)
      product.setName(name.get)
      product.setDescription(description.get)
      product.setM_Product_Category_ID(productCategory.get.getM_Product_Category_ID)
      product.setC_TaxCategory_ID(taxCategory.get.getC_TaxCategory_ID)
      product.setC_UOM_ID(uom.get.getC_UOM_ID)
      product.setProductType(productType.get)
      product.setIsSold(isSold.get)
      product.setIsPurchased(isPurchased.get)
      product.saveEx()
      product
    }
  }
}

