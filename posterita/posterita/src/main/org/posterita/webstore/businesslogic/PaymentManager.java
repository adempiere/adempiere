/**
 *  Product: Posterita Web-Based POS and Adempiere Plugin
 *  Copyright (C) 2007  Posterita Ltd
 *  This file is part of POSterita
 *  
 *  POSterita is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *  
 **/
package org.posterita.webstore.businesslogic;

import java.util.Properties;

import org.compiere.model.MBankAccount;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentValidate;
import org.compiere.process.DocumentEngine;
import org.compiere.util.Env;
import org.posterita.Constants;
import org.posterita.beans.CreditCardBean;
import org.posterita.exceptions.CreditCardExpiryInvalidException;
import org.posterita.exceptions.CreditCardNumberException;
import org.posterita.exceptions.CreditCardTypeNotSupportedException;
import org.posterita.exceptions.CreditCardVVInvalidException;
import org.posterita.exceptions.OperationException;
import org.posterita.order.UDIOrderTypes;
import org.posterita.util.PoManager;

public class PaymentManager
{

	public static MPayment createARReceipt(Properties ctx, int salesOrderId, String deliveryViaRule, String trxName) throws OperationException
	{
		return createPayment(ctx, salesOrderId, deliveryViaRule, MDocType.DOCBASETYPE_ARReceipt, null, null, null, null, null, trxName);
	}
	
	
	public static MPayment createARReceipt(Properties ctx, int salesOrderId, String deliveryViaRule, String creditCardType, String creditCardNumber, String cvv2, Integer expMonth, Integer expYear, String trxName) throws OperationException
	{
		return createPayment(ctx, salesOrderId, deliveryViaRule, MDocType.DOCBASETYPE_ARReceipt, creditCardType, creditCardNumber, cvv2, expMonth, expYear, trxName);
	}


	public static MPayment createPayment(Properties ctx, int orderId, String deliveryViaRule, String docBaseType, String creditCardType, String creditCardNumber, String cvv2, Integer expMonth, Integer expYear, String trxName) throws OperationException
	{
		MPayment payment;
		MOrder order = new MOrder(ctx, orderId, trxName);
		
		try
		{
			int orgID = Env.getAD_Org_ID(ctx);
			//get org's default bank Account
			int bankAccIds[] = MBankAccount.getAllIDs(MBankAccount.Table_Name, " ad_org_id=" + orgID + " and ad_client_id=" + Env.getAD_Client_ID(ctx) + " and isDefault='Y'", trxName);
			
			if (bankAccIds.length == 0)
				throw new OperationException("No default bank account found for org");
			
			MBankAccount orgBankAc = new MBankAccount(ctx, bankAccIds[0], trxName);
			
			payment = new MPayment(ctx,0, trxName);
			payment.setC_BankAccount_ID(bankAccIds[0]);
			payment.setC_BPartner_ID(order.getC_BPartner_ID());
			payment.setC_Currency_ID(order.getC_Currency_ID());
			payment.setC_Order_ID(orderId);
			
			if (deliveryViaRule != null && deliveryViaRule.equals(MInOut.DELIVERYVIARULE_Shipper))
			{
	         	payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
	         	
				boolean creditcardDetailsValid = payment.setCreditCard(MPayment.TRXTYPE_Sales, creditCardType, creditCardNumber, cvv2,expMonth.intValue(), expYear.intValue());
			
				if (!creditcardDetailsValid)
					throw new OperationException("Credit Card Details Missing!!!");
			}
			else
	         	payment.setTenderType(MPayment.TENDERTYPE_Check);
			
			MDocType[] docTypes = MDocType.getOfDocBaseType(ctx, docBaseType);
			
			if (docTypes.length == 0)
			    throw new OperationException("not document type found for payment");

			payment.setC_DocType_ID(docTypes[0].get_ID());

			payment.setAccountNo(orgBankAc.getAccountNo());
			
			payment.setPayAmt(order.getGrandTotal());
			
			
			if (docBaseType.equals(MDocType.DOCBASETYPE_APPayment))
			{
			    payment.setIsReceipt(false);
			    payment.setDescription("(AP Payment) Payment Made");
			}
			    
			else
			{
			    payment.setIsReceipt(true);
			    payment.setDescription("(AR Receipt) Payment Received");
			}
			    
			PoManager.save(payment);
          
		}
		catch(OperationException e)
		{
			throw e;
		}
		
		return payment;
	}


	public static MPayment completePayment(Properties ctx, int paymentId, String trxName) throws OperationException
	{
		MPayment payment;
		
		try
		{
			payment = new MPayment(ctx, paymentId, trxName);
             
			
			if (payment.getDocStatus().equals(DocumentEngine.STATUS_Completed))
				throw new OperationException("Cannot complete a payment which is already completed");
			
			if (payment.getDocStatus().equals(DocumentEngine.STATUS_Voided))
				throw new OperationException("Cannot complete a payment which has been voided");
			
			PoManager.processIt(payment, DocumentEngine.ACTION_Complete);
		}
		catch(OperationException e)
		{
			throw e;
		}
		
		return payment;
	}

	public static MPayment loadPayment(Properties ctx, MOrder order, String trxName) throws OperationException
	{
		if (!order.getOrderType().equals(UDIOrderTypes.WEBSTORE_ORDER.getOrderType()))
			throw new OperationException("This method only loads payment for a webstore order!!");
		
		int[] paymentIds = MPayment.getAllIDs(MPayment.Table_Name, "c_order_id=" + order.get_ID(), null);
		
		if (paymentIds.length > 1)
			throw new OperationException("Error loading payment. Webstore does not support multiple payment for one order.Order Id is " + order.get_ID());
		
		if (paymentIds.length < 0)
			throw new OperationException("Error loading payment. No payment found for this order: " + order.get_ID());
		
		//Assuming that there will be only one payment for this order
		MPayment payment = new MPayment(ctx, paymentIds[0], trxName);
		
		return payment;
	}

	
	public static String isPaid(Properties ctx, int orderId, String trxName)
	{
		int[] paymentIds = MPayment.getAllIDs(MPayment.Table_Name, "c_order_id=" + orderId, trxName);
		
		if (paymentIds.length == 0)
			return Constants.NO_CHAR;
		
		MPayment payment = new MPayment(ctx, paymentIds[0], trxName);
		
		if (payment.getDocStatus().equals(MPayment.DOCSTATUS_Completed))
			return Constants.YES_CHAR;
		else 
			return Constants.NO_CHAR;
	}
	
	
	public static void validateCreditCardDetails(CreditCardBean cardBean) throws CreditCardTypeNotSupportedException, CreditCardNumberException, CreditCardExpiryInvalidException, CreditCardVVInvalidException
	{
		if(cardBean == null)
			throw new IllegalArgumentException("CardBean cannot be null");
		
		String creditCardNum = cardBean.getCreditCardNumber();
		String creditCardType = cardBean.getCreditCardType();
		
		if(!(creditCardType != null 
				&& (creditCardType.equals(MPayment.CREDITCARDTYPE_Amex) 
						|| creditCardType.equals(MPayment.CREDITCARDTYPE_Discover)
						|| creditCardType.equals(MPayment.CREDITCARDTYPE_MasterCard)
						|| creditCardType.equals(MPayment.CREDITCARDTYPE_Visa))))
		{
			throw new CreditCardTypeNotSupportedException("Credit Card type: " + creditCardType + " is not supported");
		}
		
		String numValResult = MPaymentValidate.validateCreditCardNumber(creditCardNum, creditCardType);
		if(numValResult != "")
			throw new CreditCardNumberException("Credit Card number is not valid, Reason: " + numValResult);
		
		Integer expMonth = cardBean.getCreditCardExpMonth();
		Integer expYear = cardBean.getCreditCardExpYear();
		
		if(expMonth == null)
			throw new CreditCardExpiryInvalidException("Expiry month cannot be null");
		if(expYear == null)
			throw new CreditCardExpiryInvalidException("Expiry year cannot be null");
		
		String expiryValResult = MPaymentValidate.validateCreditCardExp(expMonth.intValue(), expYear.intValue());
		
		if(expiryValResult != "")
			throw new CreditCardExpiryInvalidException("Credit Card Expiry date invalid");
		
		String cvv = cardBean.getCvv();
		if(cvv == null)
			throw new CreditCardVVInvalidException("Credit Card VV cannot be null");
		
		String cvvValResult = MPaymentValidate.validateCreditCardVV(cvv, creditCardType);
		if(cvvValResult != "")
			throw new CreditCardVVInvalidException("Credit Card VV is not valid");
	}


	public static String loadCreditCardTypeName(String creditCardType) throws OperationException
	{
		if(creditCardType == null)
			throw new OperationException("Credit Card Type not present!!");
				
		String creditCardTypeName = "";
				
		if (creditCardType.equals(MPayment.CREDITCARDTYPE_Amex))
			creditCardTypeName =  Constants.CREDITCARDTYPE_AMEX;
		else
		if (creditCardType.equals(MPayment.CREDITCARDTYPE_Discover))
			creditCardTypeName = Constants.CREDITCARDTYPE_DISCOVER;
		else
		if (creditCardType.equals(MPayment.CREDITCARDTYPE_MasterCard))
			creditCardTypeName = Constants.CREDITCARDTYPE_MASTERCARD;
		else
		if (creditCardType.equals(MPayment.CREDITCARDTYPE_Visa))
			creditCardTypeName = Constants.CREDITCARDTYPE_VISA;
		
		return creditCardTypeName;

	}
	
}
