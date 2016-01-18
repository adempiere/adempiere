package org.eevolution

import java.sql.Timestamp

import org.compiere.model._
import org.compiere.util.Trx

/**
  * Created by e-Evolution on 14/01/16.
  */
package object dsl {
  type Organization =  MOrg
  type Partner = MBPartner
  type Product = MProduct
  type PriceList = MPriceList
  type Currency = MCurrency
  type Order = MOrder
  type OrderLine = MOrderLine
  type Shipment = MInOut
  type ShipmentLine = MInOutLine
  type Invoice = MInvoice
  type InvoiceLine = MInvoiceLine
  type Warehouse = MWarehouse
  type Payment = MPayment
  type BankAccount = MBankAccount
  type Context = java.util.Properties
  type DocumentType = MDocType
  type Transaction = Trx
  type Quantity = BigDecimal
  type Amount = BigDecimal
  type YesNo = Boolean
  type Date = Timestamp
  type SysConfig = MSysConfig

  sealed trait Optional
  sealed trait Mandatory extends Optional
  sealed trait Once extends Optional

}
