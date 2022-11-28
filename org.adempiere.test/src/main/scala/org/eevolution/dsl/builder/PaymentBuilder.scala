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

import org.compiere.model.*
import org.adempiere.core.domains.models.*
import org.compiere.process.DocAction
import org.eevolution.dsl
import org.eevolution.dsl._

/** Payment Builder allows create a payment using a DSL
  * So an Type-safe Builder Pattern si implemented
  * eEvolution author Victor Perez <victor.perez@e-evolution.com>, Created by e-Evolution on 15/01/16.
  */
object PaymentBuilder {

  def apply(context: Context, trxName: String): Builder[
    Mandatory,
    Mandatory,
    Mandatory,
    Mandatory,
    Optional,
    Mandatory,
    Mandatory,
    Mandatory,
    Mandatory,
    Optional,
    Optional,
    Optional,
    Optional,
    Optional
  ] = {
    new Builder[
      Mandatory,
      Mandatory,
      Mandatory,
      Mandatory,
      Optional,
      Mandatory,
      Mandatory,
      Mandatory,
      Mandatory,
      Optional,
      Optional,
      Optional,
      Optional,
      Optional
    ](context: Context, trxName: String)
  }

  case class Builder[
      WithOrganizationTracking <: Optional,
      WithPartnerTracking <: Optional,
      WithBankAccountTracking <: Optional,
      WithDateTrxTracking <: Optional,
      WithDateAccountTracking <: Optional, // Once
      WithBaseDocumentTypeTracking <: Optional,
      WithCurrencyTracking <: Optional,
      WithTenderType <: Optional,
      WithPayAmt <: Optional,
      WithWriteOffAmount <: Optional, // Once
      WithDiscountAmount <: Optional, // Once
      WithOrderTracking <: Optional, // Once
      WithInvoiceTracking <: Optional, // Once
      WithPrePayment <: Optional // Once
  ](
      context: Context,
      trxName: String,
      organization: Option[Organization] = None,
      partner: Option[Partner] = None,
      bankAccount: Option[BankAccount] = None,
      dateTrx: Option[Date] = None,
      dateAccount: Option[Date] = None,
      order: Option[Order] = None,
      invoice: Option[Invoice] = None,
      baseDocumentType: Option[String] = None,
      currency: Option[Currency] = None,
      tenderType: Option[String] = None,
      payAmount: Option[Amount] = None,
      writeOffAmount: Option[Amount] = Some(0),
      discountAmount: Option[Amount] = Some(0),
      isPrePayment: Option[YesNo] = Some(false)
  ) {

    type IsOnce[T] = =:=[T, Once]
    type IsMandatory[T] = =:=[T, Mandatory]

    def withOrganization[Organization <: WithOrganizationTracking: IsMandatory](
        o: dsl.Organization
    ): Builder[
      Once,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        Once,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](organization = Some(o))

    def withPartner[Partner <: WithPartnerTracking: IsMandatory](
        p: dsl.Partner
    ): Builder[
      WithOrganizationTracking,
      Once,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        Once,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](partner = Some(p))

    def withBankAccount[BankAccount <: WithBankAccountTracking: IsMandatory](
        ba: dsl.BankAccount
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      Once,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        Once,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](bankAccount = Some(ba))

    def withDateTrx[DateTrx <: WithDateTrxTracking: IsMandatory](
        dt: Date
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      Once,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        Once,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](dateTrx = Some(dt))

    def withDateAccount[DateAccount <: WithDateAccountTracking: IsMandatory](
        da: Date
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      Once,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        Once,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](dateAccount = Some(da))
    def withBaseDocumentType[
        BaseDocument <: WithBaseDocumentTypeTracking: IsMandatory
    ](bdt: String): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      Once,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        Once,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](baseDocumentType = Some(bdt))

    def withCurrency[Currency <: WithCurrencyTracking: IsMandatory](
        c: dsl.Currency
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      Once,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        Once,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](currency = Some(c))

    def withTenderType[TenderType <: WithTenderType: IsMandatory](
        tt: String
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      Once,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        Once,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](tenderType = Some(tt))

    def withPayAmount[PayAmt <: WithPayAmt: IsMandatory](pa: Amount): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      Once,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        Once,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](payAmount = Some(pa))

    def withWriteOffAmount[WriteOffAmount <: WithWriteOffAmount: IsMandatory](
        wa: Amount
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      Once,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        Once,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](writeOffAmount = Some(wa))

    def withDiscountAmount[DiscountAmount <: WithDiscountAmount: IsMandatory](
        da: Amount
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      Once,
      WithOrderTracking,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        Once,
        WithOrderTracking,
        WithInvoiceTracking,
        WithPrePayment
      ](discountAmount = Some(da))

    def withOrder[Order <: WithOrderTracking: IsMandatory](
        o: dsl.Order
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      Once,
      WithInvoiceTracking,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        Once,
        WithInvoiceTracking,
        WithPrePayment
      ](order = Some(o))

    def withInvoice[Invoice <: WithInvoiceTracking: IsMandatory](
        i: dsl.Invoice
    ): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      Once,
      WithPrePayment
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        Once,
        WithPrePayment
      ](invoice = Some(i))

    def asPrePayment[IsPrePayment <: WithPrePayment: IsMandatory](): Builder[
      WithOrganizationTracking,
      WithPartnerTracking,
      WithBankAccountTracking,
      WithDateTrxTracking,
      WithDateAccountTracking,
      WithBaseDocumentTypeTracking,
      WithCurrencyTracking,
      WithTenderType,
      WithPayAmt,
      WithWriteOffAmount,
      WithDiscountAmount,
      WithOrderTracking,
      WithInvoiceTracking,
      Once
    ] =
      copy[
        WithOrganizationTracking,
        WithPartnerTracking,
        WithBankAccountTracking,
        WithDateTrxTracking,
        WithDateAccountTracking,
        WithBaseDocumentTypeTracking,
        WithCurrencyTracking,
        WithTenderType,
        WithPayAmt,
        WithWriteOffAmount,
        WithDiscountAmount,
        WithOrderTracking,
        WithInvoiceTracking,
        Once
      ](isPrePayment = Some(true))

    def build[
        Organization <: WithOrganizationTracking: IsOnce,
        Partner <: WithPartnerTracking: IsOnce,
        BankAccount <: WithBankAccountTracking: IsOnce,
        DateTrx <: WithDateTrxTracking: IsOnce,
        DateAccount <: WithDateAccountTracking: IsOnce,
        BaseDocument <: WithBaseDocumentTypeTracking: IsOnce,
        Currency <: WithCurrencyTracking: IsOnce,
        TenderType <: WithTenderType: IsOnce,
        PayAmt <: WithPayAmt: IsOnce,
        WriteOffAmount <: WithWriteOffAmount: IsOnce,
        DiscountAmount <: WithDiscountAmount: IsOnce,
        Order <: WithOrderTracking: IsOnce,
        Invoice <: WithInvoiceTracking: IsOnce
    ](): Payment = {

      var payment = new Payment(context, 0, trxName)
      payment.setAD_Org_ID(organization.get.get_ID)
      payment.setC_BankAccount_ID(bankAccount.get.get_ID)
      payment.setDateTrx(dateTrx.get)
      payment.setDateAcct(dateAccount.get)
      payment.setC_BPartner_ID(partner.get.get_ID)
      if (X_C_DocType.DOCBASETYPE_ARReceipt == baseDocumentType.get) {
        val documentTypeId = MDocType.getDocType(baseDocumentType.get)
        payment.setC_DocType_ID(documentTypeId)
        payment.setIsReceipt(true)
      } else if (X_C_DocType.DOCBASETYPE_APPayment == baseDocumentType.get) {
        val documentTypeId = MDocType.getDocType(baseDocumentType.get)
        payment.setC_DocType_ID(documentTypeId)
        payment.setIsReceipt(false)
      }
      payment.setC_Currency_ID(currency.get.get_ID)
      payment.setPayAmt(payAmount.get.bigDecimal)
      payment.setWriteOffAmt(writeOffAmount.get.bigDecimal)
      payment.setDiscountAmt(discountAmount.get.bigDecimal)

      if (order != None) payment.setC_Order_ID(order.get.get_ID)
      if (invoice != None) payment.setC_Invoice_ID(invoice.get.get_ID)

      payment.setIsPrepayment(isPrePayment.get)
      payment.setTenderType(tenderType.get)
      payment.setDocStatus(DocAction.STATUS_Drafted)
      payment.setDocAction(DocAction.ACTION_Complete)
      payment.saveEx

      // payment.setDocumentNo(imp.getDocumentNo)
      // payment.setPONum(imp.getPONum)
      // payment.setRoutingNo()
      // payment.setAccountNo()
      // payment.setCheckNo()
      // payment.setMicr()
      // payment.setTrxType(imp.getTrxType)
      // payment.setC_ConversionType_ID(imp.getC_ConversionType_ID());
      // payment.setC_Charge_ID(imp.getC_Charge_ID)
      // payment.setChargeAmt(imp.getChargeAmt)
      // payment.setTaxAmt(imp.getTaxAmt)
      // payment.setCreditCardNumber(imp.getCreditCardNumber)
      // payment.setCreditCardExpMM(imp.getCreditCardExpMM)
      // payment.setCreditCardExpYY(imp.getCreditCardExpYY)
      // payment.setCreditCardVV(imp.getCreditCardVV)
      // payment.setSwipe(imp.getSwipe)
      // payment.setDescription(imp.getDescription());
      // payment.setA_City(imp.getA_City)
      // payment.setA_Country(imp.getA_Country)
      // payment.setA_EMail(imp.getA_EMail)
      // payment.setA_Ident_DL(imp.getA_Ident_DL)
      // payment.setA_Ident_SSN(imp.getA_Ident_SSN)
      // payment.setA_Name(imp.getA_Name)
      // payment.setA_State(imp.getA_State)
      // payment.setA_Street(imp.getA_Street)
      // payment.setA_Zip(imp.getA_Zip)
      // payment.setR_AuthCode(imp.getR_AuthCode)
      // payment.setR_Info(imp.getR_Info)
      // payment.setR_PnRef(imp.getR_PnRef)
      // payment.setR_RespMsg(imp.getR_RespMsg)
      // payment.setR_Result(imp.getR_Result)
      // payment.setOrig_TrxID(imp.getOrig_TrxID)
      // payment.setVoiceAuthCode(imp.getVoiceAuthCode)
      //
      payment.load(trxName)
      payment
    }
  }
}
