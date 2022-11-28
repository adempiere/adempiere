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

package org.eevolution

import java.sql.Timestamp

import org.compiere.model.*
import org.adempiere.core.domains.models.*
import org.compiere.util.Trx
import org.eevolution.model.*
import org.eevolution.distribution.model.*
import org.eevolution.manufacturing.model.*

/*
 * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 06/01/16.
 */
package object dsl {
  type Organization = MOrg
  type Partner = MBPartner
  type Product = MProduct
  type ProductCategory = MProductCategory
  type ProductPrice = MProductPrice
  type ProductPriceBreak = X_M_ProductPriceVendorBreak
  type ProductPricing = MProductPricing
  type UOM = MUOM
  type PriceList = MPriceList
  type PriceListVersion = MPriceListVersion
  type DiscountSchema = MDiscountSchema
  type DiscountSchemaBreak = MDiscountSchemaBreak
  type Currency = MCurrency
  type Order = MOrder
  type OrderLine = MOrderLine
  type DistributionOrder = MDDOrder
  type DistributionOrderLine = MDDOrderLine
  type MfgOrder = MPPOrder
  type Shipment = MInOut
  type ShipmentLine = MInOutLine
  type Invoice = MInvoice
  type InvoiceLine = MInvoiceLine
  type InventoryMovement = MMovement
  type InventoryMovementLine = MMovementLine
  type Warehouse = MWarehouse
  type Payment = MPayment
  type BankAccount = MBankAccount
  type Context = java.util.Properties
  type DocumentType = MDocType
  type Tax = MTax
  type TaxCategory = MTaxCategory
  type Transaction = Trx
  type Quantity = BigDecimal
  type Amount = BigDecimal
  type YesNo = Boolean
  type Date = Timestamp
  type Project = MProject
  type ProjectPhase = MProjectPhase
  type ProjectTask = MProjectTask
  type ProjectGroup = MProjectGroup
  type ProjectCategory = MProjectCategory
  type ProjectClass = MProjectClass
  type ProjectType = MProjectType
  type SystemConfig = MSysConfig

  sealed trait Optional
  sealed trait Mandatory extends Optional
  sealed trait Once extends Optional
}
