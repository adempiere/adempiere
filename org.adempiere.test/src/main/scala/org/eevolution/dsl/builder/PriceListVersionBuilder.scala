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
  * Price List Version Builder allows create a Price List Version using a DSL
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 29/02/16.
  */
object PriceListVersionBuilder {

  def apply()(implicit context: Context , transaction: Transaction) = {
      new Builder[Mandatory, Mandatory, Optional, Mandatory, Mandatory , Optional]()
  }

  case class Builder[
  WithPriceListTracking <: Optional,
  WithNameTracking <: Optional ,
  WithDescriptionTracking <: Optional ,
  WithValidFromTracking <: Optional ,
  WithPriceListSchemaTracking <: Optional ,
  WithBasePriceListVersionTracking <: Optional
  ](priceList: Option[dsl.PriceList] = None,
    name: Option[String] = None,
    description: Option[String] = None,
    priceListSchema : Option[dsl.DiscountSchema] = None,
    basePriceListVersion : Option[dsl.PriceListVersion] = None,
    validFrom: Option[dsl.Date] = None)
   (implicit context: Context, transaction: Transaction)
  {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withPriceList[PriceList <: WithPriceListTracking : IsMandatory](pl : dsl.PriceList) =
      copy[
        Once,
        WithNameTracking ,
        WithDescriptionTracking,
        WithValidFromTracking,
        WithPriceListSchemaTracking,
        WithBasePriceListVersionTracking](priceList = Some(pl))

    def withName[Name <: WithNameTracking : IsMandatory](n: String) =
      copy[
        WithPriceListTracking,
        Once,
        WithDescriptionTracking,
        WithValidFromTracking,
        WithPriceListSchemaTracking,
        WithBasePriceListVersionTracking](name = Some(n))

    def withDescription[Description <: WithDescriptionTracking : IsMandatory](d: String) =
      copy[
        WithPriceListTracking,
        WithNameTracking,
        Once,
        WithValidFromTracking,
        WithPriceListSchemaTracking,
        WithBasePriceListVersionTracking](description = Some(d))

    def withValidFrom[ValidFrom <: WithValidFromTracking : IsMandatory](vf: dsl.Date) =
      copy[
        WithPriceListTracking,
        WithNameTracking,
        WithDescriptionTracking,
        Once,
        WithPriceListSchemaTracking,
        WithBasePriceListVersionTracking](validFrom = Some(vf))

    def withPriceListSchema[PriceListSchema <: WithPriceListSchemaTracking : IsMandatory](ds: dsl.DiscountSchema) =
      copy[
        WithPriceListTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithValidFromTracking,
        Once,
        WithBasePriceListVersionTracking](priceListSchema = Some(ds))

    def withBasePriceListVersion[BasePriceListVersion <: WithBasePriceListVersionTracking : IsMandatory](bplv: dsl.PriceListVersion) =
      copy[
        WithPriceListTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithValidFromTracking,
        WithPriceListSchemaTracking,
        Once](basePriceListVersion = Some(bplv))

    def build[
    PriceList <: WithPriceListTracking : IsOnce,
    Name <: WithNameTracking : IsOnce,
    Description <: WithDescriptionTracking : IsOnce,
    ValidFrom  <: WithValidFromTracking : IsOnce ,
    DiscountSchema <: WithPriceListSchemaTracking : IsOnce,
    BasePriceListVersion <: WithBasePriceListVersionTracking : IsOnce
   ]() : PriceListVersion = {
      val priceListVersion = new PriceListVersion(context, 0, transaction.getTrxName)
      priceListVersion.setAD_Org_ID(priceList.get.getAD_Org_ID)
      priceListVersion.setM_PriceList_ID(priceList.get.get_ID)
      priceListVersion.setName(name.get)
      priceListVersion.setDescription(description.get)
      if (priceListSchema.isDefined)
      priceListVersion.setM_DiscountSchema_ID(priceListSchema.get.getM_DiscountSchema_ID)
      if (basePriceListVersion.isDefined)
      priceListVersion.setM_Pricelist_Version_Base_ID(basePriceListVersion.get.getM_PriceList_Version_ID)

      priceListVersion.setValidFrom(validFrom.get)
      priceListVersion.saveEx()
      priceListVersion
    }
  }
}

