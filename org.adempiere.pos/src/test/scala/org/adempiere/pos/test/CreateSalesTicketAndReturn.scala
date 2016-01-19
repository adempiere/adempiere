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
import org.eevolution.dsl._
import org.eevolution.dsl.builder.PaymentBuilder
import org.eevolution.service.{ProductService}
import org.eevolution.services.{SysConfigService, PaymentService}
import org.scalatest.{ GivenWhenThen, FeatureSpec}
import scala.collection.JavaConversions._
import org.eevolution.test._

/**
  * Test to validate process of  Sales in POS
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 05/01/16.
  */
class CreateSalesTicketAndReturn extends FeatureSpec
with AdempiereTestCase
with GivenWhenThen
with ProductService
with PaymentService
with SysConfigService{
  feature("Create a sales ticket") {
    info("The customer Joe Block buy one Oak Trees and two Azalea Bush")
    info("The customer not ask for an invoice so that the delivery is made using final consumer")
    info("The customer paid using your credit card 50 % of sales ticket and other 50% in cash")
    info("The customer next day cancel all the sales ticket")
    var order : Order = null
    val OakPrice  = { 61.75 }
    val QtyAzalea =  { 2 }
    val AzaleaPrice = { 23.75}
    val TotalSales = { (OakPrice * 1)+(AzaleaPrice * 2) }


    //Functions for this scenario
    scenario("Create sales order") {
      val HQ = { Organization }
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val Azalea = { getProduct("Azalea Bush",trxName) }
      val SalePriceList = { MPriceList.getDefault(Context, true) }
      import X_C_DocType._
      val AsWarehouseOrder = { DOCSUBTYPESO_WarehouseOrder }
      val HQWarehouse = { Warehouse }
      val QtyOak = { BigDecimal(1) }
      val Oak = { getProduct("Oak", trxName) }

      import org.eevolution.dsl.builder._
      order = OrderBuilder(Context , trxName)
        .AddLine(Oak , QtyOak)
        .AddLine (Azalea , QtyAzalea) withOrganization HQ withPartner JoeBlock withWarehouse HQWarehouse withPriceList SalePriceList withBaseDocumentType DOCBASETYPE_SalesOrder withSubType AsWarehouseOrder build()
      Given(s"Joe Block buy one Oak Trees with $OakPrice USD by unit and two Azalea Bush with $AzaleaPrice USD by unit")
      When("Sales order is created ")
      Then("the order have a organization")
      assert( order.getAD_Org_ID == HQ.getAD_Org_ID)
      info(Organization.getName)
      And("the Document No have the value ")
      assert( order.getDocumentNo.length > 0)
      info(order.getDocumentNo)
      And("the order have a partner ")
      assert ( order.getC_BPartner == JoeBlock)
      info(JoeBlock.getName)
      And("the order have a warehouse ")
      assert ( order.getM_Warehouse == HQWarehouse)
      info(HQWarehouse.getName)
      And("Is an Sales Order")
      assert(order.isSOTrx)
      And("The  Order is Sales Order")
      val documentType = order.getC_DocType
      assert(documentType.getDocBaseType == DOCBASETYPE_SalesOrder)
      And("Sub Type Sales Order Is Warehouse Order")
      assert(documentType.getDocSubTypeSO == AsWarehouseOrder)
      And("the order have two lines ")
      assert ( order.getLines().length == 2)
      info("--------------------------------------------------------")
      for (orderLine <- order.getLines)
        info ("Product : " + orderLine.getM_Product.getName + " Qty : " + orderLine.getQtyOrdered +  " Total Line : " +  orderLine.getLineNetAmt)

      info("--------------------------------------------------------")
      And(s"the total Sales Order is that $TotalSales")
      assert(TotalSales.toDouble == order.getGrandTotal.doubleValue())
    }
    scenario("Complete the Sales Order")
    {
      Given(" The Sales Orderd is completed")
      order.processIt(DocAction.ACTION_Complete)
      order.saveEx
      Then("The Shipment is created")
      val shipments = order.getShipments
      assert(shipments.length > 0)
      info("--------------------------------------------------------")
      for (shipment <- shipments ;
           shipmentLine <- shipment.getLines())
        info ("Product : " + shipmentLine.getM_Product.getName + " Qty : " + shipmentLine.getMovementQty)
    }
    scenario("Create and process Credit Card payment")
    {
      Given("The Shipments is generated ")
      Then("Payment with Credit Card is created")
      val HQ = { Organization }
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val GWBankAccount = { MBankAccount.get(Context , 100)}
      val USD = {MCurrency.get(Context , 100)}
      import X_C_DocType._
      val AsReceipt = { DOCBASETYPE_ARReceipt }
      val CreditCard = { X_C_Payment.TENDERTYPE_CreditCard }
      val CreditPayAmount : BigDecimal = {(TotalSales / 2)}
      val payment = PaymentBuilder(Context,trxName).asPrePayment() withOrganization HQ withPartner JoeBlock withBankAccount GWBankAccount withDateTrx Today withDateAccount Today withBaseDocumentType AsReceipt withCurrency USD withTenderType CreditCard withPayAmount CreditPayAmount withOrder order build()
      Then("the order have a organization")
      assert( payment.getAD_Org_ID == HQ.getAD_Org_ID)
      info(Organization.getName)
      And("the Document No have the value ")
      assert(payment.getDocumentNo.length > 0)
      info(payment.getDocumentNo)
      And("the payment have a partner ")
      assert ( payment.getC_BPartner == JoeBlock)
      info(JoeBlock.getName)
      And("Is a Receipt")
      assert(payment.isReceipt)
      And("The payment is AR Receipt")
      val documentType = payment.getC_DocType
      assert(documentType.getDocBaseType == DOCBASETYPE_ARReceipt)
      And("The payment is Tender Type Creadit Card")
      assert(CreditCard == payment.getTenderType)
      And(s"Payment Amount $CreditPayAmount")
      assert(CreditPayAmount.bigDecimal == payment.getPayAmt())
      And(s"Cash payment is link with sales order : " +  order.getDocumentInfo)
      assert(order.get_ID==payment.getC_Order_ID)
      And("Credit Card Payment is completed")
      payment.processIt(DocAction.ACTION_Complete)
      payment.saveEx()
      assert(payment.getDocStatus.equals(DocAction.STATUS_Completed))
      And("Payment was process as prepayment")
      assert(payment.isPrepayment)
    }

    scenario("Create and process Cash payment without Cash book")
    {
      val HQ = { Organization }
      val JoeBlock = { MBPartner.get(Context , "JoeBlock") }
      val GWBankAccount = { MBankAccount.get(Context , 100)}
      val USD = {MCurrency.get(Context , 100)}
      import X_C_DocType._
      val AsReceipt = { DOCBASETYPE_ARReceipt }
      val CashPayment = { X_C_Payment.TENDERTYPE_Cash }
      val CashPayAmount : BigDecimal = {(TotalSales / 2)}
      Given("The Credit Card Payment is generated ")
      Then(s"a cash payment for $CashPayAmount is created")
      And(" this is register without Cash Book")
      val SysConfig = {getSysConfig(Context , "CASH_AS_PAYMENT" ,  trxName)}
      assert(SysConfig.get_ID() > 0)
      SysConfig.setValue("Y")
      SysConfig.saveEx()
      assert(SysConfig.getValue().equals("Y"))
      val payment = PaymentBuilder(Context,trxName).asPrePayment() withOrganization HQ withPartner JoeBlock withBankAccount GWBankAccount withDateTrx Today withDateAccount Today withBaseDocumentType AsReceipt withCurrency USD withTenderType CashPayment withPayAmount CashPayAmount withOrder order build()
      And("the order have a organization")
      assert( payment.getAD_Org_ID == HQ.getAD_Org_ID)
      info(Organization.getName)
      And("the Document No have the value ")
      assert(payment.getDocumentNo.length > 0)
      info(payment.getDocumentNo)
      And("the payment have a partner ")
      assert ( payment.getC_BPartner == JoeBlock)
      info(JoeBlock.getName)
      And("Is a Receipt")
      assert(payment.isReceipt)
      And("The payment is AR Receipt")
      val documentType = payment.getC_DocType
      assert(documentType.getDocBaseType == DOCBASETYPE_ARReceipt)
      And("The payment is Tender Type Creadit Card")
      assert( CashPayment == payment.getTenderType)
      And(s"Payment Amount $CashPayAmount")
      assert(CashPayAmount.bigDecimal == payment.getPayAmt())
      And(s"Cash payment is link with sales order :" +  order.getDocumentInfo)
      assert(order.get_ID==payment.getC_Order_ID)
      And("Cash Payment is completed")
      payment.processIt(DocAction.ACTION_Complete)
      payment.saveEx()
      assert(payment.getDocStatus.equals(DocAction.STATUS_Completed))
      And("Payment was process as prepayment")
      assert(payment.isPrepayment)

    }
    scenario("The Payments was created and link with Sales Order")
    {
      Given(s" The Total Sale $TotalSales ")
      Then(s" The Total Sales $TotalSales is equal that the sum payment amount ")
      val payments = getOrderPayments(order)
      assert(2 == payments.size)
      info("--------------------------------------------------------")
      var totalPaid :BigDecimal = 0.0
      for (pay : Payment <- payments) {
        info("Payment :" + pay.getDocumentInfo)
        totalPaid += pay.getPayAmt
      }
      And(s"The payment sum $totalPaid is equalt that sales total $TotalSales")
    }
    scenario("Create sales return") {
      Given(s"Joe Block want return the sales ticket : " + order.getDocumentNo)
      Then("A return process is execute")
      import org.eevolution.service.dsl._
      info("--------------------------------------------------------")
      val processInfo = ProcessBuilder.
        create(Context) process("C_POS ReverseTheSalesTransaction") withTitle("Reverse The Sales Transaction")  withParameter("C_Order_ID", order.get_ID) withParameter("Bill_BPartner_ID",order.getBill_BPartner_ID) execute(trxName)

      val payments = getOrderPayments(order)
      assert(4 == payments.size)
      info("--------------------------------------------------------")
      var totalPaid :BigDecimal = 0.0
      for (pay : Payment <- payments) {
        info("Payment :" + pay.getDocumentInfo + " Pay Amount :" +pay.getPayAmt())
        if (pay.isReceipt)
          totalPaid += pay.getPayAmt
        else
          totalPaid -= pay.getPayAmt
      }
      And(s"The payment sum $totalPaid is equal Zero")
      assert(totalPaid == 0)
      order.processIt(DocAction.ACTION_Close)
      order.saveEx()
      And("Sales Order is closed")
      assert(DocAction.STATUS_Closed == order.getDocStatus)
    }
  }
}
