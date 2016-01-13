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

package org.eevolution

import org.compiere.model._
import org.compiere.util.Trx

/**
  * Define DSL types for testing and other services
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 07/01/16.
  */
package object service {

  type Organization =  MOrg
  type Partner = MBPartner
  type Product = MProduct
  type PriceList = MPriceList
  type Order = MOrder
  type OrderLine = MOrderLine
  type Shipment = MInOut
  type ShipmentLine = MInOutLine
  type Warehouse = MWarehouse
  type Payment = MPayment
  type Context = java.util.Properties
  type Transaction = Trx
  type Quantity = BigDecimal

}
