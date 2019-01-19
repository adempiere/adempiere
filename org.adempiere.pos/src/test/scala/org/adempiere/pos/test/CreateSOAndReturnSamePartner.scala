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

package org.adempiere.pos.test

import org.compiere.model._
import org.compiere.process.DocAction
import org.eevolution.dsl.builder.PaymentBuilder
import org.eevolution.dsl.{Order, Payment}
import org.eevolution.service.dsl.ProcessBuilder
import org.eevolution.services.{ProductService, PaymentService, SystemConfigService}
import org.eevolution.test._
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.collection.JavaConversions._

/**
  * Test to validate process of  Sales in POS
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 05/01/16.
  */
class CreateSOAndReturnSamePartner extends FeatureSpec
with AdempiereTestCase
with GivenWhenThen
with ProductService
with PaymentService
with SystemConfigService{
  feature("Create a sales ticket using final consumer") {
    info("The customer Joe Block buy one Oak Trees and two Azalea Bush")
    info("The customer not ask for an invoice so that the delivery is made using final consumer")
    info("The customer paid using your credit card 50 % of sales ticket and other 50% in cash")
    info("The customer next day cancel all the sales ticket")
    var order : Order = null
    var newOrder : Order = null
    val OakPrice  = { 61.75 }
    val QtyAzalea =  { 2 }
    val AzaleaPrice = { 23.75}
    val TotalSales = { (OakPrice * 1)+(AzaleaPrice * 2) }

    //Functions for this scenario
    scenario("Creating the sales order") {
      val HQ = { Organization }
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val Azalea = { getProductByValue("Azalea Bush") }
      val SalePriceList = { MPriceList.getDefault(Context, true) }
      import X_C_DocType._
      val AsWarehouseOrder = { DOCSUBTYPESO_WarehouseOrder }
      val HQWarehouse = { Warehouse }
      val QtyOak = { BigDecimal(1) }
      val Oak = { getProductByValue("Oak") }

      import org.eevolution.dsl.builder._
      order = OrderBuilder(Context , trxName)
        .AddLine(Oak , QtyOak)
        .AddLine (Azalea , QtyAzalea) withOrganization HQ withPartner JoeBlock withWarehouse HQWarehouse withPriceList SalePriceList withBaseDocumentType DOCBASETYPE_SalesOrder withSubType AsWarehouseOrder build()
      Given(s"Joe Block buy one Oak Trees for $OakPrice USD per unit and two Azalea Bush for $AzaleaPrice USD per unit")
      When("Sales order is created ")
      Then("the organization " + Organization.getName + " is used")
      assert( order.getAD_Org_ID == HQ.getAD_Org_ID)
      assert( order.getDocumentNo.length > 0)
      And("the document no was generate with " + order.getDocumentNo)
      And("the order is created with partner " + JoeBlock.getName)
      assert ( order.getC_BPartner == JoeBlock)
      And("the order have a warehouse " + HQWarehouse.getName)
      assert ( order.getM_Warehouse == HQWarehouse)
      assert(order.isSOTrx)
      And("the order is of type Sales Order")
      val documentType = order.getC_DocType
      assert(documentType.getDocBaseType == DOCBASETYPE_SalesOrder)
      And("sub type of the Sales Order is Warehouse Order")
      assert(documentType.getDocSubTypeSO == AsWarehouseOrder)
      And("the order has two lines ")
      assert ( order.getLines().length == 2)
      for (orderLine <- order.getLines)
        info ("    with the product " + orderLine.getM_Product.getName + " with " + orderLine.getQtyOrdered +  " of quantity and total Line " +  orderLine.getLineNetAmt)

      And(s"the total Sales Order is of $TotalSales")
      assert(TotalSales.toDouble == order.getGrandTotal.doubleValue())
    }
    scenario("Completing the Sales Order and generate shipments")
    {
      Given("that the Sales Order is completed")
      order.processIt(DocAction.ACTION_Complete)
      order.saveEx
      Then("the shipment was generated")
      val shipments = order.getShipments
      assert(shipments.length > 0)
      for (shipment <- shipments) {
        info("    with " + shipment.getDocumentInfo)
        for (shipmentLine <- shipment.getLines()) {
          info("        with the product " + shipmentLine.getM_Product.getName + " with " + shipmentLine.getMovementQty + " of quantity")
        }
      }
    }
    scenario("Creating and processing the Credit Card payment")
    {
      Given("that shipments was generated ")
      val CreditPayAmount : BigDecimal = {(TotalSales / 2)}
      Then(s"the payment with Credit Card is create for $CreditPayAmount")
      val HQ = { Organization }
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val GWBankAccount = { MBankAccount.get(Context , 100)}
      val USD = {MCurrency.get(Context , 100)}
      import X_C_DocType._
      val AsReceipt = { DOCBASETYPE_ARReceipt }
      val CreditCard = { X_C_Payment.TENDERTYPE_CreditCard }
      val payment = PaymentBuilder(Context,trxName).asPrePayment() withOrganization HQ withPartner JoeBlock withBankAccount GWBankAccount withDateTrx Today withDateAccount Today withBaseDocumentType AsReceipt withCurrency USD withTenderType CreditCard withPayAmount CreditPayAmount withOrder order build()
      And("the organization " + Organization.getName + " is used")
      assert( payment.getAD_Org_ID == HQ.getAD_Org_ID)
      And("the document no was generate with " + payment.getDocumentNo)
      assert(payment.getDocumentNo.length > 0)
      And("the payment have a partner "+ JoeBlock.getName)
      assert ( payment.getC_BPartner == JoeBlock)
      And("the payment is register as a receipt")
      assert(payment.isReceipt)
      val documentType = payment.getC_DocType
      assert(documentType.getDocBaseType == DOCBASETYPE_ARReceipt)
      And("the payment has the tender type Credit Card")
      assert(CreditCard == payment.getTenderType)
      And(s"the payment amount is of $CreditPayAmount")
      assert(CreditPayAmount.bigDecimal == payment.getPayAmt())
      And(s"the cash payment is linked with sales order " +  order.getDocumentInfo)
      assert(order.get_ID==payment.getC_Order_ID)
      And("the credit card payment is completed")
      payment.processIt(DocAction.ACTION_Complete)
      payment.saveEx()
      assert(payment.getDocStatus.equals(DocAction.STATUS_Completed))
      And("payment was process as prepayment")
      assert(payment.isPrepayment)
    }
    scenario("Creating and processing the cash payment without Cash book")
    {
      val HQ = { Organization }
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val GWBankAccount = { MBankAccount.get(Context , 100)}
      val USD = {MCurrency.get(Context , 100)}
      import X_C_DocType._
      val AsReceipt = { DOCBASETYPE_ARReceipt }
      val CashPayment = { X_C_Payment.TENDERTYPE_Cash }
      val CashPayAmount : BigDecimal = {(TotalSales / 2)}
      Given("that the Credit Card Payment is generated ")
      Then(s"a cash payment for $CashPayAmount is created")
      And("is register without Cash Book")
      val SysConfig = {getSystemConfig(Context , "CASH_AS_PAYMENT" ,  trxName)}
      assert(SysConfig.get_ID() > 0)
      SysConfig.setValue("Y")
      SysConfig.saveEx()
      assert(SysConfig.getValue().equals("Y"))
      val payment = PaymentBuilder(Context,trxName).asPrePayment() withOrganization HQ withPartner JoeBlock withBankAccount GWBankAccount withDateTrx Today withDateAccount Today withBaseDocumentType AsReceipt withCurrency USD withTenderType CashPayment withPayAmount CashPayAmount withOrder order build()
      And("the organization " + Organization.getName + " is used")
      assert( payment.getAD_Org_ID == HQ.getAD_Org_ID)
      assert(payment.getDocumentNo.length > 0)
      And("the document no was generate with " + payment.getDocumentNo)
      And("the payment have a partner "+ JoeBlock.getName)
      assert ( payment.getC_BPartner == JoeBlock)
      And("the payment is register as a receipt")
      assert(payment.isReceipt)
      val documentType = payment.getC_DocType
      assert(documentType.getDocBaseType == DOCBASETYPE_ARReceipt)
      And("the payment has the tender type Cash ")
      assert( CashPayment == payment.getTenderType)
      And(s"the payment amount is of $CashPayAmount")
      assert(CashPayAmount.bigDecimal == payment.getPayAmt())
      And(s"cash payment is linked with the sales order " +  order.getDocumentInfo)
      assert(order.get_ID==payment.getC_Order_ID)
      And("the cash Payment is completed")
      payment.processIt(DocAction.ACTION_Complete)
      payment.saveEx()
      assert(payment.getDocStatus.equals(DocAction.STATUS_Completed))
      And("the payment was process as a prepayment")
      assert(payment.isPrepayment)
    }
    scenario("The Payments was created and linked to the Sales Order")
    {
      Given(s"the total sale is of $TotalSales ")
      Then(s"sum of all payments amount ")
      val payments = getOrderPayments(order)
      assert(2 == payments.size)
      var totalPaid :BigDecimal = 0.0
      for (pay : Payment <- payments) {
        info("    with the payment " + pay.getDocumentInfo)
        totalPaid += pay.getPayAmt
      }
      And(s"$totalPaid should be equal that sales total $TotalSales")
      assert(totalPaid.toDouble  == TotalSales.toDouble)
    }
  }
}
