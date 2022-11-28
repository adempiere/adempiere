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

import org.adempiere.core.domains.models.*
import org.compiere.model.*
import org.eevolution.dsl
import org.eevolution.dsl.*

/** Discount Schema Builder allows create a Discount Schema using a DSL
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 02/03/16.
  */
object DiscountSchemaBuilder {

  def apply()(implicit context: Context, transaction: Transaction) = {
    new Builder[
      Mandatory,
      Mandatory,
      Optional,
      Mandatory,
      Optional,
      Optional,
      Optional,
      Optional,
      Optional
    ]()
  }

  case class Builder[
      WithOrganizationTracking <: Optional,
      WithNameTracking <: Optional,
      WithDescriptionTracking <: Optional,
      WithDiscountTypeTracking <: Optional,
      WithIsPartnerFlatDiscountTracking <: Optional,
      WithFlatDiscountTracking <: Optional,
      WithIsQuantityBasedTracking <: Optional,
      WithAccumulationLevelTracking <: Optional,
      WithDiscountSchemaBreakTracking <: Optional
  ](
      organization: Option[Organization] = None,
      name: Option[String] = None,
      description: Option[String] = None,
      discountType: Option[String] = None,
      isPartnerFlatDiscount: Option[Boolean] = Some(false),
      flatDiscount: Option[BigDecimal] = None,
      isQuantityBased: Option[Boolean] = Some(false),
      accumulationLevel: Option[String] = None,
      discountSchemaBreaks: Map[DiscountSchemaBreak, Option[Integer]] =
        Map.empty
  )(implicit context: Context, transaction: Transaction) {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withOrganization[Organization <: WithOrganizationTracking: IsMandatory](
        o: dsl.Organization
    ): Builder[
      Once,
      WithNameTracking,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        Once,
        WithNameTracking,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](organization = Some(o))

    def withName[Name <: WithNameTracking: IsMandatory](n: String): Builder[
      WithOrganizationTracking,
      Once,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        Once,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](name = Some(n))

    def withDescription[Description <: WithDescriptionTracking: IsMandatory](
        d: String
    ): Builder[
      WithOrganizationTracking,
      WithNameTracking,
      Once,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        Once,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](description = Some(d))

    def withDiscountType[DiscountType <: WithDiscountTypeTracking: IsMandatory](
        dt: String
    ): Builder[
      WithOrganizationTracking,
      WithNameTracking,
      WithDescriptionTracking,
      Once,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        WithDescriptionTracking,
        Once,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](discountType = Some(dt))

    def asPartnerFlatDiscount[
        IsPartnerFlatDiscount <: WithIsPartnerFlatDiscountTracking: IsMandatory
    ]: Builder[
      WithOrganizationTracking,
      WithNameTracking,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      Once,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        Once,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](isPartnerFlatDiscount = Some(true))

    def withFlatDiscount[FlatDiscount <: WithFlatDiscountTracking: IsMandatory](
        d: BigDecimal
    ): Builder[
      WithOrganizationTracking,
      WithNameTracking,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      Once,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        Once,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](flatDiscount = Some(d))

    def asQuantityBased[
        IsQuantityBased <: WithIsQuantityBasedTracking: IsMandatory
    ](): Builder[
      WithOrganizationTracking,
      WithNameTracking,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      Once,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        Once,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](isQuantityBased = Some(true))

    def withAccumulationLevel[
        AccumulationLevel <: WithAccumulationLevelTracking: IsMandatory
    ](al: String): Builder[
      WithOrganizationTracking,
      WithNameTracking,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      WithDiscountSchemaBreakTracking
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        WithDiscountSchemaBreakTracking
      ](accumulationLevel = Some(al))

    def AddDiscountSchemaBreak[
        DiscountSchemaBreak <: WithDiscountSchemaBreakTracking: IsOnce
    ](
        discountSchemaBreak: dsl.DiscountSchemaBreak,
        sequenceNo: Integer
    ): Builder[
      WithOrganizationTracking,
      WithNameTracking,
      WithDescriptionTracking,
      WithDiscountTypeTracking,
      WithIsPartnerFlatDiscountTracking,
      WithFlatDiscountTracking,
      WithIsQuantityBasedTracking,
      WithAccumulationLevelTracking,
      Once
    ] =
      copy[
        WithOrganizationTracking,
        WithNameTracking,
        WithDescriptionTracking,
        WithDiscountTypeTracking,
        WithIsPartnerFlatDiscountTracking,
        WithFlatDiscountTracking,
        WithIsQuantityBasedTracking,
        WithAccumulationLevelTracking,
        Once
      ](discountSchemaBreaks =
        discountSchemaBreaks + (discountSchemaBreak -> Some(sequenceNo))
      )

    def build[
        Organization <: WithOrganizationTracking: IsOnce,
        Name <: WithNameTracking: IsOnce,
        Description <: WithDescriptionTracking: IsOnce,
        DiscountTyp <: WithDiscountTypeTracking: IsOnce,
        IsPartnerFlatDiscount <: WithIsPartnerFlatDiscountTracking: IsOnce,
        FlatDiscount <: WithFlatDiscountTracking: IsOnce,
        IsQuantityBased <: WithIsQuantityBasedTracking: IsOnce,
        AccumulationLevel <: WithAccumulationLevelTracking: IsOnce
    ](): DiscountSchema = {
      val discountSchema =
        new DiscountSchema(context, 0, transaction.getTrxName)
      discountSchema.setAD_Org_ID(organization.get.getAD_Org_ID)
      discountSchema.setName(name.get)
      discountSchema.setDescription(description.get)
      discountSchema.setDiscountType(discountType.get)
      if (discountType.get == X_M_DiscountSchema.DISCOUNTTYPE_FlatPercent) {
        discountSchema.setIsBPartnerFlatDiscount(isPartnerFlatDiscount.get)
        discountSchema.setFlatDiscount(flatDiscount.get.bigDecimal)
      }
      if (discountType.get == X_M_DiscountSchema.DISCOUNTTYPE_Breaks) {
        discountSchema.setIsQuantityBased(isQuantityBased.get)
        discountSchema.setCumulativeLevel(accumulationLevel.get)
      }
      discountSchema.saveEx()
      discountSchema
    }
  }
}
