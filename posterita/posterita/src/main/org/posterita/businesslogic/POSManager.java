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
 * Created on 14-Mar-2006 by alok
 */


package org.posterita.businesslogic;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.compiere.model.I_C_CashLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MCashLine;
import org.compiere.model.MCharge;
import org.compiere.model.MCurrency;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrg;
import org.compiere.model.MPayment;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.MRole;
import org.compiere.model.MTax;
import org.compiere.model.MUser;
import org.compiere.model.MUserRoles;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.CheckoutBean;
import org.posterita.beans.CreditCheckBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.POSBean;
import org.posterita.beans.POSInfoBean;
import org.posterita.beans.ProductBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.businesslogic.administration.PriceListManager;
import org.posterita.businesslogic.administration.ProductManager;
import org.posterita.businesslogic.administration.TaxManager;
import org.posterita.businesslogic.core.ChargeManager;
import org.posterita.core.JulianDate;
import org.posterita.core.TimestampConvertor;
import org.posterita.core.TrxPrefix;
import org.posterita.core.utils.FormatBigDecimal;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.DiscountLimitException;
import org.posterita.exceptions.InvalidOrderIDException;
import org.posterita.exceptions.InvalidTenderedAmountException;
import org.posterita.exceptions.LimitPriceViolatedException;
import org.posterita.exceptions.MandatoryException;
import org.posterita.exceptions.NoOrderLineFoundException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.order.UDIOrderType;
import org.posterita.order.UDIOrderTypes;
import org.posterita.util.PoManager;


public class POSManager 
{
    public static final String DELETE_GOODS_RECEIVE_ORDERLINE="deleteGoodsReceiveOrderLine";
    public static final String DELETE_GOODS_RETURN_ORDERLINE="deleteGoodsReturnOrderLine";
    public static final String DELETE_POS_ORDERLINE="deletePOSOrderLine";
    public static final String DELETE_PARTIAL_POS_ORDERLINE="deletePartialPOSOrderLine";
    public static final String DELETE_CUSTOMER_RETURN_ORDERLINE="deleteCustomerReturnOrderLine";
    
       
    public static final int PAYMENT_RULE_REF_ID = 195;
    
    public static MOrder createPOSOrder(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems,String trxName) throws DiscountLimitException,BPartnerOverCreditLimitException,OperationException
    {
        UDIOrderType orderType= UDIOrderTypes.POS_ORDER;
        return createOrders(ctx,bean,cartBeanItems,orderType,trxName);
    }
    
    public static MOrder createCreditOrder(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems,String trxName) throws DiscountLimitException,BPartnerOverCreditLimitException,OperationException
    {
        UDIOrderType orderType= UDIOrderTypes.CREDIT_ORDER;
        return createOrders(ctx,bean,cartBeanItems,orderType,trxName);
    }
    
    public static MOrder createCreditOrderWithOutShipment(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems,String trxName) throws DiscountLimitException,BPartnerOverCreditLimitException,OperationException
    {
        UDIOrderType orderType= UDIOrderTypes.CREDIT_ORDER_NO_SHIPMENT;
        return createOrders(ctx,bean,cartBeanItems,orderType,trxName);
    }
    
    private static MOrder createOrders(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems,UDIOrderType orderType,String trxName) throws DiscountLimitException,BPartnerOverCreditLimitException,OperationException
    {
        if(bean == null)
        {
            throw new OperationException("Order Line bean cannot be null");
        }
        
        BigDecimal userDiscount = bean.getUserDiscount();
        String discounts[] = bean.getDiscountPercent(); 
        
        
        if(( discounts != null ) && ( discounts.length != 0 ))
        {
            if( userDiscount == null )
            {
                throw new OperationException("You are not allowed to give discounts!");
            }
            
            double discountEntered = 0.0;
            double maxDiscountAllowed = userDiscount.doubleValue();
            
            for(String discount : discounts)
            {
                if( discount == null || discount == "" )
                {
                    discount = "0.0";
                }
                
                discountEntered = Double.parseDouble(discount);
                
                if( discountEntered > maxDiscountAllowed )
                {
                    throw new DiscountLimitException("Discount Limit exceeded!");
                }
            }
        }
        
        if(bean.getBpartnerId() == null)
        {
            throw new OperationException("Business Partner cannot be null");
        }
        
        int cashBookId = POSTerminalManager.getCashBookId(ctx);
        int terminalId = POSTerminalManager.getTerminalId(ctx);
        int priceListId = POSTerminalManager.getSOPriceListId(ctx);
        int warehouseId = POSTerminalManager.getWarehouseId(ctx);
        
        if(firstOrderOfTheDay(ctx))
        {
            CashManager.closePreviousDraftedCashjournals(ctx, cashBookId, trxName);
        }
        
  
        String paymentRule=null;
        
        if (bean.getTrxType().equalsIgnoreCase(Constants.PAYMENT_RULE_CASH))
            paymentRule=MOrder.PAYMENTRULE_Cash;
        else if(bean.getTrxType().equalsIgnoreCase(Constants.PAYMENT_RULE_CARD))
            paymentRule=MOrder.PAYMENTRULE_CreditCard; 
        else if(bean.getTrxType().equalsIgnoreCase(Constants.PAYMENT_RULE_CHEQUE))
            paymentRule=MOrder.PAYMENTRULE_Check;
        else if(bean.getTrxType().equalsIgnoreCase(Constants.PAYMENT_RULE_MIXED))
        {
            paymentRule=UdiConstants.PAYMENTRULE_MIXED;
            validateMixedPaymentAmount(bean);
        }
        else if(bean.getTrxType().equalsIgnoreCase(MOrder.PAYMENTRULE_OnCredit))
            paymentRule=MOrder.PAYMENTRULE_OnCredit;
        
        else
            throw new OperationException("Invalid Payment Rule");
        
        //reusing existing order
        MOrder order = null;
        
        Integer orderId = bean.getOrderId();
        if( orderId == null )
        {
            //creating new order
            order = OrderManager.createOrder(ctx, bean.getBpartnerId().intValue(), true, 
                    priceListId, orderType.getOrderType(), warehouseId, paymentRule, trxName);
        }
        else
        {
            //updating existing order
            MBPartner partner = new MBPartner(ctx, bean.getBpartnerId().intValue(), trxName);
            order = new MOrder(ctx, orderId.intValue(), trxName);
            
            //deleting existing orderlines
            OrderManager.deleteOrderlines(ctx, order, trxName);
            
            
            order.setBPartner(partner);
            order.setPaymentRule(paymentRule); 
            order.setDateOrdered(new Timestamp(System.currentTimeMillis()));            
        }
        
        //order.setC_POS_ID(terminalId);TODO - Trifon; order.setU_POSTerminal_ID(terminalId);
        if(bean.getPaymentTermId()!=null)
        {
            order.setC_PaymentTerm_ID(bean.getPaymentTermId());
        }
        PoManager.save(order);
        
        Iterator iter = cartBeanItems.iterator();
        ItemBean itemBean;
        BigDecimal qtyTotal= BigDecimal.ZERO;
        int i=0;
        while(iter.hasNext())
        {
            double actualPrice=0.0;
            double discount=0.0;
            itemBean =(ItemBean)iter.next();
            
            if(bean.getDiscountPercent()!=null)
            {
                String discountEntered = bean.getDiscountPercent()[i];
                if( discountEntered == null ||  discountEntered.equals(""))
                    discount=0;
                else 
                {
                    discount = Double.parseDouble(bean.getDiscountPercent()[i]);
                    actualPrice=Double.parseDouble(bean.getActualPrice()[i]);
                }
            }
            
            if(discount > 0.0)
            {
                MUser user = new MUser(ctx,Env.getAD_User_ID(ctx),null);
                
                MUserRoles[] userRole = MUserRoles.getOfUser(ctx,user.get_ID());  
                
                if (userRole.length == 0)
                    throw new OperationException("Role not found for user " + user.getName());
                
                MRole role = new MRole(ctx,userRole[0].getAD_Role_ID(),null);
                
                MPriceList priceList = MPriceList.get(ctx, order.getM_PriceList_ID(), order.get_TrxName());
                int priceListVersionId = PriceListManager.getPriceListVersionID(ctx, order.getM_PriceList_ID(), order.get_TrxName());
                BigDecimal limitPrice=ProductManager.getLimitPrice(ctx, priceListVersionId, itemBean.getProductId(), priceList.isSOPriceList(), order.get_TrxName());
              
                MProduct product = new MProduct(ctx,itemBean.getProductId().intValue(),null);
                MTax tax = TaxManager.getTaxFromCategory(ctx, product.getC_TaxCategory_ID(), order.get_TrxName());
               BigDecimal limitPriceWithTax = TaxManager.getPriceWithTax(ctx,limitPrice,tax.getRate());
                
                if(role.isOverwritePriceLimit()==false && actualPrice/itemBean.getQty().doubleValue() < limitPriceWithTax.doubleValue())
                {
                       throw new LimitPriceViolatedException("Discount Exceeds Limit price, limit price is= "+limitPriceWithTax.doubleValue());
                }
                else
                {
                    itemBean.setDiscountPercent(new BigDecimal(discount));
                    itemBean.setActualPrice(new BigDecimal(actualPrice));
                }
            }
            qtyTotal=qtyTotal.add(itemBean.getQty());
            itemBean.setQtyTotal(qtyTotal);
            OrderManager.createOrderLine(ctx,order,itemBean.getProductId().intValue(),itemBean.getQty(),itemBean.getDiscountPercent(),itemBean.getActualPrice()) ;
            i++;
        }
        
        if(order.isSOTrx()==true)
        {
            CreditCheckBean crBean = OrderManager.checkBPartnerCreditLimit(ctx,order.getC_BPartner_ID(),order.get_ID(),order.get_TrxName());
            if(crBean.getValid()==false)
                throw new BPartnerOverCreditLimitException(crBean.getMsg());
        }
       
        return order;
    }

    private static void validateMixedPaymentAmount(OrderLineBean bean) throws InvalidTenderedAmountException
    {
        if(bean.getTrxType().equalsIgnoreCase(Constants.PAYMENT_RULE_MIXED))
        {
            double cashAmt=0.0;
            double chequeAmt=0.0;
            double cardAmt =0.0;
            
            if(bean.getPaymentByCash()!=null)
                cashAmt = bean.getPaymentByCash();
            if(bean.getPaymentByChq()!=null)
                chequeAmt=bean.getPaymentByChq();
            if(bean.getPaymentByCard()!=null)
                cardAmt = bean.getPaymentByCard();
            
            BigDecimal total = FormatBigDecimal.currency(cashAmt+chequeAmt+cardAmt);
          
            double lineDiscount=0.0;
            double sumActualAmt=0.0;
            int count=0;
            if(bean.getDiscountPercent()!=null && bean.getDiscountPercent().length>0)
            {
                for(int i=0;i<bean.getDiscountPercent().length;i++)
                {
                    String disPer = bean.getDiscountPercent()[i]== "" ? "0": bean.getDiscountPercent()[i] ;
                    
                    if(lineDiscount ==0.0 || lineDiscount==Double.parseDouble(disPer))
                    {
                        lineDiscount=Double.parseDouble(disPer);
                        count=count+1;
                    }
                    else
                    {
                        lineDiscount=0.0;
                        break;
                    }
                }
            } 
            if(lineDiscount>0.0)
            {
                sumActualAmt= bean.getTotalActualPrice()-(bean.getTotalActualPrice()*(lineDiscount/100));
            }
           
            else
            {
                sumActualAmt=bean.getTotalActualPrice();
            }
             
            
            if(total.doubleValue() !=sumActualAmt)
                throw new InvalidTenderedAmountException("The Tendered Amount should be equal to the total amount");  
        }
    }
    
    
    public static MInvoice createDocuments(Properties ctx, MOrder order) throws OperationException
    {
        MInvoice customerInvoice = createCustomerInvoice(ctx, order);
        PoManager.processIt(customerInvoice, DocumentEngine.ACTION_Complete);
        if (!customerInvoice.getPaymentRule().equalsIgnoreCase(MInvoice.PAYMENTRULE_Cash) && !customerInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_OnCredit))
        {
            MPayment paymentReceived = PaymentManager.createARReceipt(ctx, customerInvoice, customerInvoice.get_TrxName());
            PaymentManager.completePayment(ctx, paymentReceived);
        }

        MInOut shipment = MinOutManager.createMInOut(ctx, customerInvoice, order.getM_Warehouse_ID());
      
         if (customerInvoice.isCreditMemo())
         {
            MInOutConfirm confirm=MinOutManager.createConfirmation(ctx,shipment);
            MinOutManager.completeConfirmation(ctx, confirm);
         }
        MinOutManager.completeShipment(ctx, shipment);
        order.setIsDelivered(true);
        PoManager.save(order);

        if (firstCashInvoice(ctx, customerInvoice))
        {
            CashManager.updateBeginningBalance(ctx, order.getC_POS_ID(), order.get_TrxName());
        }

        return customerInvoice;

    }
    
    public static MOrder completePOSOrder(Properties ctx,int orderId,OrderLineBean bean,String trxName) throws InvalidTenderedAmountException,BPartnerOverCreditLimitException,NoOrderLineFoundException,OperationException
    {
        MOrder order = new MOrder(ctx,orderId,trxName);
        
        if(order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_Cash))
        {
            if(bean.getAmountGiven()==null || bean.getAmountGiven().doubleValue()< order.getGrandTotal().doubleValue())
            {
                throw new InvalidTenderedAmountException("The Amount given is Invalid");
            }
        }
        
        if(order.getPaymentRule().equalsIgnoreCase(UdiConstants.PAYMENTRULE_MIXED) && (bean.getPaymentByCash() == null || bean.getPaymentByCash() <= 0))
        {
            if(bean.getAmountGiven()==null || bean.getAmountGiven().doubleValue() < bean.getPaymentByCash())
            {
                throw new InvalidTenderedAmountException("The Amount given is Invalid");
            }
        }
        
        order.setAmountTendered(bean.getAmountGiven());
        order.setAmountRefunded(bean.getAmountRefunded());
        PoManager.save(order);
        
        if(order.getLines().length==0)
        {
            throw new NoOrderLineFoundException("POS Order does not have any orderlines!");
        }      
        
        PoManager.processIt(order, DocumentEngine.ACTION_Complete);
        
        Double cashAmt = bean.getPaymentByCash();
        if(cashAmt == null)
            cashAmt = 0.0;
        
        Double chequeAmt=bean.getPaymentByChq();
        if(chequeAmt == null)
            chequeAmt = 0.0;
        
        Double cardAmt = bean.getPaymentByCard();
        if(cardAmt == null)
            cardAmt = 0.0;
        
        BigDecimal writeOffAmount = Env.ZERO;
        
        if (order.getAmountTendered() != null && order.getAmountRefunded() != null)
        {
            writeOffAmount = (order.getAmountTendered().subtract(order.getAmountRefunded())).subtract(order.getGrandTotal());
        }
        
        CheckoutBean checkoutBean = new CheckoutBean();
        checkoutBean.setAmountRefunded(order.getAmountRefunded());
        checkoutBean.setAmountTendered(order.getAmountTendered());
        checkoutBean.setBpartnerId(order.getC_BPartner_ID());
        checkoutBean.setCardAmt(new BigDecimal(cardAmt));
        checkoutBean.setCardNo(bean.getCreditCardNumber());
        checkoutBean.setCardType(bean.getCreditCardType());
        checkoutBean.setCashAmt(new BigDecimal(cashAmt));
        checkoutBean.setChequeAmt(new BigDecimal(chequeAmt));
        checkoutBean.setChequeNo(bean.getChequeNo());
        checkoutBean.setWriteOffAmt(writeOffAmount);
        checkoutBean.setDiscountAmt(Env.ZERO);
        
        createAndCompleteDocuments(ctx, order, checkoutBean, trxName);
        
        return order; 
    }    
    
    public static MOrder completePOSOrder(Properties ctx,int orderId, String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx, orderId, trxName);
        
        return completePOSOrder(ctx, order);
    }
    
    public static MOrder completePOSOrder(Properties ctx,MOrder order) throws OperationException
    {
        MOrder completedOrder = OrderManager.completeOrder(ctx,order);
        //if(completedOrder.getOrderType().equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
        OrderManager.printOrder(ctx,completedOrder);
        
        createDocuments(ctx,completedOrder);
        return completedOrder;
    }
    
    public static MOrder completePOSOrderPrintInvoice(Properties ctx,MOrder order) throws OperationException
    {
        MOrder completedOrder = OrderManager.completeOrder(ctx,order);
        MInvoice invoice = createDocuments(ctx,completedOrder);
        InvoiceManager.printInvoice(ctx,invoice);
        
        return completedOrder;
    }    
    
    public static void createAndCompleteDocuments(Properties ctx, MOrder order, CheckoutBean bean, String trxName) throws OperationException
    {
    	MInvoice invoice = createARInvoice(ctx,order.get_ID(), trxName);
        createInvoiceLines(ctx, invoice, order, trxName);            
        PoManager.processIt(invoice, DocumentEngine.ACTION_Complete); 
        
    	if(order.getPaymentRule().equals(MOrder.PAYMENTRULE_OnCredit))
        {
        	//No payment for credit order
        	return;
        }           
                
        BigDecimal cashAmt = (bean.getCashAmt() == null ? Env.ZERO : bean.getCashAmt());
        BigDecimal cardAmt = (bean.getCardAmt() == null ? Env.ZERO : bean.getCardAmt());
        BigDecimal chequeAmt = (bean.getChequeAmt() == null ? Env.ZERO : bean.getChequeAmt());
        BigDecimal writeOffAmount = (bean.getWriteOffAmt() == null ? Env.ZERO : bean.getWriteOffAmt());
        BigDecimal discountAmt = (bean.getDiscountAmt() == null ? Env.ZERO : bean.getDiscountAmt());
        
        boolean paymentCreated = false;
        boolean discountApplied = false;
        
        /*
        // Cash invoices creates automatically Cash Line entry upon completion of the invoice
        // @see MInvoice.completeIt
        // Reload cashLine to set writeOff Amt in case of cash payment
        // because for each new cashline created, writeOff is set to zero by default
        if (invoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_Cash))
        {
            paymentCreated = true;
                                  
            MCashLine cashLine = new MCashLine(ctx, invoice.getC_CashLine_ID(), trxName);
            
            if(cashLine != null)
            {   
                cashLine.setWriteOffAmt(writeOffAmount);
                cashLine.setDiscountAmt(discountAmt);
                
                if(!(discountAmt.compareTo(Env.ZERO)==0))
                {
                    cashLine.setAmount(invoice.getGrandTotal().subtract(discountAmt).subtract(writeOffAmount));
                }
                else
                {   
                    cashLine.setAmount(invoice.getGrandTotal().subtract(writeOffAmount));
                }
                
                PoManager.save(cashLine);
            }
            else
            {
                throw new OperationException("Could not load CashLine");
            }
        }
        // For mixed payment with Cash being part
        else if (cashAmt.compareTo(Env.ZERO) > 0 && invoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_Mixed)) 
        {
            int cashBookId = POSTerminalManager.getCashBookId(ctx);
            MCash cash = MCash.get(ctx, cashBookId, invoice.getDateInvoiced(), trxName);
            
            if (cash == null)
            {
                throw new OperationException("Could not load Cash Journal to do cash entry");
            }   
            
            //No WriteOff in case of Mixed Payment
            MCashLine cashLine = CashManager.createCashLine(ctx, cash.get_ID(), 
                    invoice.get_ID(), cashAmt, Env.ZERO, discountAmt, trxName);
            invoice.setC_CashLine_ID(cashLine.get_ID());
            PoManager.save(cashLine);
            
            paymentCreated = true;
            discountApplied = true;
        }
        */
        
        //Modifications for cash payment
        if (invoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_Cash))
        {
            paymentCreated = true;
            BigDecimal amt = null;
            
            if(!(discountAmt.compareTo(Env.ZERO)==0))
            {
            	amt = invoice.getGrandTotal().subtract(discountAmt).subtract(writeOffAmount);
            }
            else
            {   
                amt = invoice.getGrandTotal().subtract(writeOffAmount);
            }
            
            MPayment paymentReceivedCash = PaymentManager.createARReceipt(ctx, invoice, 
                    MPayment.TENDERTYPE_Cash, amt, invoice.get_TrxName());
            
            paymentReceivedCash.setWriteOffAmt(writeOffAmount);
        	paymentReceivedCash.setDiscountAmt(discountAmt);
            
        	PoManager.save(paymentReceivedCash);
        	PaymentManager.completePayment(ctx,paymentReceivedCash);
        }
        // For mixed payment with Cash being part
        else if (cashAmt.compareTo(Env.ZERO) > 0 && invoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_Mixed)) 
        {            
            //No WriteOff in case of Mixed Payment
        	MPayment paymentReceivedCash = PaymentManager.createARReceipt(ctx, invoice, 
                    MPayment.TENDERTYPE_Cash, cashAmt, invoice.get_TrxName());
            
            PoManager.save(paymentReceivedCash);
            PaymentManager.completePayment(ctx,paymentReceivedCash);
            
            paymentCreated = true;
            discountApplied = true;
        }
        //End of modifications
        
        
        
        // Enforce that check payment should be created only if Payment rule is check or mixed
        if(chequeAmt.compareTo(Env.ZERO) > 0 
                && (MInvoice.PAYMENTRULE_Check.equals(invoice.getPaymentRule()) || MInvoice.PAYMENTRULE_Mixed.equals(invoice.getPaymentRule())))
        {
            MPayment paymentReceivedCheque = PaymentManager.createARReceipt(ctx, invoice, 
                    MPayment.TENDERTYPE_Check, chequeAmt, invoice.get_TrxName());
            
            if (bean.getChequeNo() != null && bean.getChequeNo().trim().length() > 0)
            {
                paymentReceivedCheque.setCheckNo(bean.getChequeNo());
            }
            
            if (!discountApplied && discountAmt.compareTo(Env.ZERO) > 0)
            {
                paymentReceivedCheque.setDiscountAmt(discountAmt);
            }
            
            PoManager.save(paymentReceivedCheque);
            PaymentManager.completePayment(ctx,paymentReceivedCheque);
            
            paymentCreated = true;
            discountApplied = true;
        }
        
        // Enforce that card payment should be created only if Payment rule is credit card or mixed
        if (cardAmt.compareTo(Env.ZERO) > 0
                && (MInvoice.PAYMENTRULE_CreditCard.equals(invoice.getPaymentRule()) || MInvoice.PAYMENTRULE_Mixed.equals(invoice.getPaymentRule())))
        {
            MPayment paymentReceivedCard = PaymentManager.createARReceipt(ctx, invoice, 
                    MPayment.TENDERTYPE_CreditCard, cardAmt, invoice.get_TrxName());
            
            if (bean.getCardType() != null && bean.getCardType().trim().length() > 0)
            {
                paymentReceivedCard.setCreditCardType(bean.getCardType());
            }
            
            if (bean.getCardNo() != null && bean.getCardNo().trim().length() > 0)
            {
                paymentReceivedCard.setCreditCardNumber(bean.getCardNo());
            }
            
            if (!discountApplied && discountAmt.compareTo(Env.ZERO) > 0)
            {
                paymentReceivedCard.setDiscountAmt(discountAmt);
            }
            
            PoManager.save(paymentReceivedCard);
            PaymentManager.completePayment(ctx,paymentReceivedCard);
            
            paymentCreated = true;
            discountApplied = true;
        }
        
        if (!paymentCreated)
        {
            throw new OperationException("No payments have been created!!!");
        }
        
        if (firstCashInvoice(ctx, invoice))
            CashManager.updateBeginningBalance(ctx, order.getC_POS_ID(),order.get_TrxName());
     
        //@TODO not allowed to use MOrderLine getallids here, must use a manager.
        int [] orderLines=MOrderLine.getAllIDs(MOrderLine.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+" and C_ORDER_ID="+order.get_ID(),trxName);
        MInOut shipment= MinOutManager.createShipment(ctx, order.get_ID(), orderLines, trxName);
        MinOutManager.completeShipment(ctx,shipment);
    }
    
    
    public static ArrayList<WebOrderLineBean> populateOrderLines(Properties ctx,MOrder order) throws  OperationException
    {
        return populateOrderLines(ctx,order,false);
    }
    
    public static ArrayList<WebOrderLineBean> populateOrderLines(Properties ctx, MOrder order,boolean abbr) throws  OperationException
    {
        MOrderLine [] lines=order.getLines();
        MOrderLine line;
        MProduct product;
        // AttributeValuesPair attributeValuesPair;
        WebOrderLineBean orderLineBean;
        BigDecimal qty = Env.ZERO;
        BigDecimal totalGrossProfit = Env.ZERO;
        BigDecimal totalGrossProfitPercentage = Env.ZERO;
        
        int priceListId = order.getM_PriceList_ID();
        
        MPriceList priceList = MPriceList.get(ctx, priceListId, null);
        ArrayList<WebOrderLineBean> orderLines = new ArrayList<WebOrderLineBean>();
        for (int i = 0; i < lines.length; i++)
        {
            line = lines[i];
            
            product = new MProduct(ctx, line.getM_Product_ID(), null);
            
            orderLineBean = new WebOrderLineBean();
            orderLineBean.setProductName(product.getName());
            orderLineBean.setLineNetAmt(line.getLineNetAmt());
            BigDecimal lineTaxAmount = getLineTaxAmt(line.getCtx(), line.getLineNetAmt(), line.getC_Tax_ID(), priceList.isTaxIncluded(), line.getQtyOrdered());
            orderLineBean.setTaxAmt(lineTaxAmount);
            BigDecimal lineTotalAmount = Env.ZERO;
            if (priceList.isTaxIncluded())
            {
            	lineTotalAmount = line.getLineNetAmt();
            }
            else
            {
            	lineTotalAmount = line.getLineNetAmt().add(orderLineBean.getTaxAmt());
            }
            
            orderLineBean.setLineTotalAmt(lineTotalAmount);
            orderLineBean.setUnitPrice(line.getPriceList().setScale(2));
            orderLineBean.setPriceActual(line.getPriceActual());
            orderLineBean.setProductId(Integer.valueOf(line.getM_Product_ID()));
            orderLineBean.setUom(product.getUOMSymbol());
            MTax tax = MTax.get(ctx, line.getC_Tax_ID());
            orderLineBean.setTaxRate(tax.getRate());
            BigDecimal multiplier = (tax.getRate().add(Env.ONEHUNDRED)).divide(Env.ONEHUNDRED);
            
            Integer purchasePriceListId = PriceListManager.getDefaultPriceListId(ctx, false);
            Integer ppriceListVersionId = PriceListManager.getPriceListVersionID(ctx, purchasePriceListId, null);
            MPriceList ppriceList = new MPriceList(ctx, purchasePriceListId, null);
            MProductPrice pprice = MProductPrice.get(ctx, ppriceListVersionId, line.getM_Product_ID(), null);
                        
            BigDecimal priceLimit = Env.ZERO;
           
            try
            {
                if(ppriceList.isTaxIncluded())
                {
                    priceLimit = pprice.getPriceLimit();
                }
                else
                {
                    priceLimit = pprice.getPriceLimit().multiply(multiplier);
                }
            }
            catch(NullPointerException e)
            {
                priceLimit = Env.ZERO;
            }
            
            orderLineBean.setPurchasePriceList(priceLimit.setScale(2, RoundingMode.HALF_DOWN));
            BigDecimal grossProfit = lineTotalAmount.subtract((priceLimit).multiply(line.getQtyOrdered()));
            BigDecimal grossProfitPercentage = Env.ZERO;
            
            try
            {
                grossProfitPercentage = (grossProfit.divide(lineTotalAmount, 4, BigDecimal.ROUND_UP)).multiply(Env.ONEHUNDRED);
            }
            catch (ArithmeticException e) 
            {
                grossProfitPercentage = Env.ZERO;
            }
            
            totalGrossProfit = totalGrossProfit.add(grossProfit);
            
            orderLineBean.setGrossProfit(grossProfit.setScale(2, RoundingMode.HALF_DOWN));
            orderLineBean.setTotalGrossProfit(totalGrossProfit.setScale(2, RoundingMode.HALF_DOWN));
            orderLineBean.setGrossProfitPercentage(grossProfitPercentage.setScale(2, RoundingMode.HALF_DOWN));            
            
            if (line.getM_Product_ID() == 0)
            {
                if(line.getC_Charge_ID() != 0)
                {
                    MCharge charge = ChargeManager.loadCharge(ctx, line.getC_Charge_ID(), null);
                    orderLineBean.setProductName(charge.getName());
                }
                else
                    throw new OperationException("Unknown Order line type with id: " + line.get_ID()); 
            }
            else
            {
                String description = product.getDescription();
                if(description == null)
                {
                    description = product.getName();
                }
                orderLineBean.setDescription(description);
            }
            
            orderLineBean.setOrderLineId(Integer.valueOf(line.get_ID()));
            orderLineBean.setIsinvoiced(Boolean.valueOf(false));
            orderLineBean.setIsQtyReserved(Boolean.valueOf(true));
            qty=qty.add(line.getQtyOrdered());
            orderLineBean.setQtyTotal(qty) ;
            
            orderLineBean.setQtyOrdered(line.getQtyOrdered());
            orderLineBean.setDiscountPercentage(line.getDiscount());
            BigDecimal discountAmt = Env.ZERO;
            BigDecimal subTotal = Env.ZERO;
            BigDecimal grandTotal = Env.ZERO;
            
            if (order.getPaymentRule().equals(MOrder.PAYMENTRULE_Cash) || order.getPaymentRule().equals(MOrder.PAYMENTRULE_Mixed))           
            {
                MInvoice invoice = MInvoice.get(ctx, order.getC_Invoice_ID());
                MCashLine cashLine = new MCashLine(ctx, invoice.getC_CashLine_ID(), null);
                if (cashLine.getC_CashLine_ID() != 0)
                {
                    discountAmt = cashLine.getDiscountAmt()==null? Env.ZERO: cashLine.getDiscountAmt();
                    discountAmt = discountAmt.setScale(lineTotalAmount.scale(), RoundingMode.HALF_DOWN);
                    BigDecimal writeOffAmt = cashLine.getWriteOffAmt() == null? Env.ZERO: cashLine.getWriteOffAmt();
                    writeOffAmt = writeOffAmt.setScale(lineTotalAmount.scale(), RoundingMode.HALF_DOWN);
                   
                    orderLineBean.setWriteOffAmt(writeOffAmt);
                    subTotal = order.getGrandTotal();
                    grandTotal = cashLine.getAmount();
                }
            }
            
            if (!order.getPaymentRule().equals(MOrder.PAYMENTRULE_Cash))
            {
                int[] payIds = MPayment.getAllIDs(MPayment.Table_Name, "C_Invoice_ID = " + order.getC_Invoice_ID(), null);
                for (int payId : payIds)
                {
                    MPayment payment = new MPayment(ctx, payId, null);
                    
                    if (payment.get_ID() != 0)
                    {
                        if (payment.getDiscountAmt() != null)
                        {
                            discountAmt = discountAmt.add(payment.getDiscountAmt());
                        }
                        subTotal = subTotal.add(payment.getPayAmt());
                        grandTotal = grandTotal.add(payment.getPayAmt());
                    }
                }
            }
            
            if(subTotal.compareTo(Env.ZERO)!=0)
            {
                try
                {
                    totalGrossProfitPercentage = (totalGrossProfit.divide(subTotal, 4, BigDecimal.ROUND_UP)).multiply(Env.ONEHUNDRED);
                }
                catch (ArithmeticException e) 
                {
                    totalGrossProfitPercentage = Env.ZERO;
                }
                orderLineBean.setTotalGrossProfitPercentage(totalGrossProfitPercentage.setScale(2));
            }
            
            orderLineBean.setDiscountAmt(discountAmt);
            orderLineBean.setSubTotal(subTotal);
            orderLineBean.setGrandTotal(grandTotal);
            orderLines.add(orderLineBean);
        }
        
        return orderLines;
    }   
    
    private static I_C_CashLine getCashLine(Properties ctx, MOrder order) throws OperationException 
    {
        int invoiceId = order.getC_Invoice_ID();
        MInvoice invoice = new MInvoice(ctx, invoiceId, null);
        I_C_CashLine cashLine = null;
        try 
        {
            cashLine = invoice.getC_CashLine();
        }
        catch (Exception e) 
        {
            throw new OperationException("No cash line generated for invoice: " + invoiceId);
        }
        return cashLine;
    }

    public static WebDocumentBean calculateOrderTotals(ArrayList webOrderLineList)
    {
        Iterator iter = webOrderLineList.iterator();
        
        WebOrderLineBean bean;
        WebDocumentBean webDocumentBean=new WebDocumentBean();
        BigDecimal totalLines = Env.ZERO;
        BigDecimal totalTax = Env.ZERO;
        BigDecimal totalQty= Env.ZERO;
        BigDecimal discountAmt = Env.ZERO;
        BigDecimal writeOffAmt = Env.ZERO;
        BigDecimal subTotal = Env.ZERO;
        BigDecimal grandTotal = Env.ZERO;
       
        while(iter.hasNext())
        {
            bean = (WebOrderLineBean) iter.next();
            totalLines = totalLines.add(bean.getLineNetAmt());
            totalTax = totalTax.add(bean.getTaxAmt());
            totalQty=totalQty.add(bean.getQtyTotal());
            if (bean.getDiscountAmt()!=null && bean.getDiscountAmt().compareTo(Env.ZERO)!=0)
            {
                discountAmt = bean.getDiscountAmt();
            }
            if (bean.getWriteOffAmt()!=null && bean.getWriteOffAmt().compareTo(Env.ZERO)!=0)
            {
                writeOffAmt = bean.getWriteOffAmt();
            }
        }
        
        subTotal = totalLines.add(totalTax);
        grandTotal = subTotal.subtract(discountAmt).subtract(writeOffAmt);
       
        webDocumentBean.setTotalLines(totalLines);
        webDocumentBean.setTotalTax(totalTax);
        webDocumentBean.setSubTotal(subTotal);
        webDocumentBean.setGrandTotal(grandTotal);
        webDocumentBean.setTotalQty(totalQty);
        return webDocumentBean;
    }
    
    
    public static String deleteOrderLines(Properties ctx,Integer[]orderlineIds,String trxName) throws OperationException 
    {
        if((orderlineIds == null)||(orderlineIds.length == 0))
        {
            throw new NoOrderLineSelectedException("Cannot delete orderlines. No orderlines supplied!");
        }
        
        MOrderLine orderLine = new MOrderLine(ctx,orderlineIds[0].intValue(),trxName);
        MOrder order = new MOrder(ctx,orderLine.getC_Order_ID(),trxName);
        
        String orderType = order.getOrderType();
        
        for(int i=0;i<orderlineIds.length;i++)
        {
            String sql="DELETE FROM c_orderline WHERE c_orderline_id="+orderlineIds[i].intValue();
            
            PreparedStatement pstmt = null;
            try 
            {
                pstmt = DB.prepareStatement(sql,trxName);
                pstmt.executeUpdate();
            }
            catch (SQLException e) 
            {
                throw new OperationException(e);
            }
            finally
            {
                try
                {
                    if(pstmt != null)
                        pstmt.close();
                }
                catch(Exception ex) {}
            }
            
        }
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
            return DELETE_POS_ORDERLINE;
        
        else if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()))
            return DELETE_GOODS_RECEIVE_ORDERLINE;
        
        else if(orderType.equalsIgnoreCase(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
            return DELETE_CUSTOMER_RETURN_ORDERLINE;
        
        else return DELETE_GOODS_RETURN_ORDERLINE;
    }
    
    
    public static void openCashDrawer(Properties ctx) throws OperationException
    {
        PrintService psServies[] = PrintServiceLookup.lookupPrintServices(null, null);
        
        for (int i =0; i < psServies.length; i++)
        {
            
            if (psServies[i].getName().equalsIgnoreCase(POSTerminalManager.getPOSPrinter(ctx)))
            {
                
                DocPrintJob job =   psServies[i].createPrintJob();
                byte[] printData= {27,112,48,55,1};
                SimpleDoc doc = new SimpleDoc(printData,DocFlavor.BYTE_ARRAY.AUTOSENSE,null);
                try 
                {
                    job.print(doc, null);
                } 
                catch (PrintException e) 
                {
                    e.printStackTrace();
                }
                
            }
        } 
    }

    public static ArrayList getAllPOS(Properties ctx) throws OperationException
    {
        String sql="select pos.C_POS_ID," +
        "pos.NAME," +
        "pos.DESCRIPTION," +
        "pos.SALESREP_ID," +
        "pos.M_PRICELIST_ID," +
        "pos.C_CASHBOOK_ID," +
        "pos.M_WAREHOUSE_ID," +
        "cash.name," +
        "us.name,"+
        "warehouse.name"+
        " from C_POS  pos,C_CASHBOOK cash,AD_USER us,M_WAREHOUSE warehouse "+
        " where pos.c_cashbook_id=  cash.C_CASHBOOK_ID"+
        " and pos.SALESREP_ID=us.AD_USER_ID"+
        " and pos.M_WAREHOUSE_ID=warehouse.M_WAREHOUSE_ID"+
        " and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and AD_Org_ID="+Env.getAD_Org_ID(ctx);
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        POSBean bean=null;
        ArrayList <POSBean>list = new ArrayList<POSBean>();
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                bean = new POSBean();
                bean.setPosId(Integer.valueOf(rs.getInt(1)));
                bean.setPosName(rs.getString(2));
                bean.setPosDesc(rs.getString(3));
                bean.setSalesRepId(Integer.valueOf(rs.getInt(4)));
                bean.setPriceListId(Integer.valueOf(rs.getInt(5)));
                bean.setCashBookId(Integer.valueOf(rs.getInt(6)));
                bean.setWarehouseId(Integer.valueOf(rs.getInt(7)));
                bean.setCashBookName(rs.getString(8));
                bean.setSaleRepName(rs.getString(9));
                bean.setWarehouseName(rs.getString(10));
                
                list.add(bean);
                
            }
            rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch(Exception ex){}
            
            pstmt = null;
        }        
        return list;
    }
 
    public static ArrayList<POSInfoBean> getPOSInfo(Properties ctx, Timestamp fromDate, Timestamp todate, String trxName) throws OperationException
    {
        ArrayList <POSInfoBean> list  =  new ArrayList<POSInfoBean>();
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT U_POSTerminal_ID, AD_Org_ID FROM U_POSTerminal ");
        sqlStmt.append("WHERE AD_Client_ID=? AND AD_Org_ID IN (");
        sqlStmt.append(Env.getContext(ctx, UdiConstants.ROLE_EDITABLE_ORGS_CTX_PARAM)).append(") ");
        sqlStmt.append("AND IsActive='Y'");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        POSInfoBean bean = null;
        
        try 
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setInt(1, Env.getAD_Client_ID(ctx));
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                bean = new POSInfoBean();
                
                MOrg org = MOrg.get(ctx, rs.getInt(2));
                CurrentTillAmountBean tillBean = getCurrentTillForInfo(ctx, rs.getInt(1), fromDate, todate);
                MCurrency currency = POSTerminalManager.getCurrencyOfTerminalCashBook(ctx,rs.getInt(1));
                bean.setPosName(tillBean.getPosName() + " " + currency.getCurSymbol());
                bean.setOrgName(org.getName());
                bean.setCardTotal(tillBean.getCardTotal());
                bean.setChequeTotal(tillBean.getChequeTotal());
                bean.setCashTotal(tillBean.getCashTotal());
                bean.setOrderGrandTotal(tillBean.getTillGrandTotal());
                
                list.add(bean);
            }
        } 
        catch (SQLException e) 
        {
            throw new OperationException(e);
        }
        finally
        {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        } 
        return list;
    }
    
    public static ArrayList<Object[]> getPOSInfoReportData(Properties ctx, Timestamp fromDate, Timestamp todate, String trxName) throws OperationException
    {
        ArrayList<POSInfoBean> list = getPOSInfo(ctx, fromDate, todate, trxName);
        ArrayList<Object[]> reportData = new ArrayList<Object[]>();
        
        Object[] headers = new Object[]{"POS Name","Organisation","Cash Total","Card Total","Cheque Total","Amount"};
        reportData.add(headers);
        
        NumberFormat formatter = new DecimalFormat("###,###,##0.00");  
        
        Object[] data = null;
        String grandTotal = null;
        
        for (POSInfoBean bean : list) 
        {
            data = new Object[6];
            
            data[0] = bean.getPosName();
            data[1] = bean.getOrgName();
            data[2] = formatter.format(bean.getCashTotal().doubleValue());
            data[3] = formatter.format(bean.getCardTotal().doubleValue());
            data[4] = formatter.format(bean.getChequeTotal().doubleValue());
            
            //format the currency
            grandTotal = formatter.format(bean.getOrderGrandTotal().doubleValue());
            
            data[5] = grandTotal;               
            
            reportData.add(data);
        }
        
        return reportData;
    }

    protected static boolean firstCashInvoice(Properties ctx, MInvoice invoice) throws OperationException
    {
        boolean firstCashInvoice = false;
        if (invoice != null && invoice.getC_CashLine_ID() > 0)
        {
            StringBuffer whereClause = new StringBuffer();
            whereClause.append("C_Cash_ID=(SELECT C_Cash_ID FROM C_CashLine WHERE C_CashLine_ID=")
                       .append(invoice.getC_CashLine_ID())
                       .append(")");
            whereClause.append(" AND CashType='").append(MCashLine.CASHTYPE_Invoice).append("'");
            
            int ids[] = MCashLine.getAllIDs(MCashLine.Table_Name, whereClause.toString(), invoice.get_TrxName());
            
            firstCashInvoice = (ids.length == 1);
        }
        return firstCashInvoice;
    }
    
    
    public static BigDecimal getSumOfChequeAndCard(Properties ctx,Timestamp toDate,String paymentRule) throws OperationException
    {
        int terminalId = POSTerminalManager.getTerminalId(ctx);
        BigDecimal grandTotal =null;
        
        String sql="select sum(pay.payAmt) " +
        " from C_order ord,c_payment pay right outer join C_invoice inv  on inv.c_Invoice_id=pay.c_Invoice_id" +
        " where pay.created>="+ DB.TO_DATE(toDate, false) +
        " and inv.c_order_id=ord.c_order_id"+
        " and ord.C_POS_ID="+terminalId+
        " and ord.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and ord.AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
        " and inv.isSotrx='Y'"+
        " and ord.orderType in ('"+UDIOrderTypes.POS_ORDER.getOrderType()+"',"+
        "'"+UDIOrderTypes.CREDIT_ORDER.getOrderType()+"')"+
        " and pay.tenderType='"+paymentRule+"'";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                if(rs.getString(1)==null)
                    grandTotal=new BigDecimal(0);
                else
                    grandTotal=new BigDecimal(rs.getString(1));
                
               grandTotal=grandTotal.add(paymentAmtWithOutInvoice(ctx,toDate,null,paymentRule,terminalId)); 
            }
            
            rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();   
            }
            catch(Exception e) {}
            
            pstmt = null;
            
        }
        
        return grandTotal;
        
    }
    
    /**
     * @ashley TODO Should look further into this workaround solution
     * @param ctx
     * @param fromDate
     * @param toDate
     * @param paymentRule
     * @param terminalId
     * @return
     * @throws OperationException
     */
    private static BigDecimal paymentAmtWithOutInvoice(Properties ctx, Timestamp fromDate, Timestamp toDate, 
            String paymentRule,int terminalId) throws OperationException
    {
        BigDecimal grandTotal =null;
        String sql="select sum(pay.payAmt) " +
                "from C_PAYMENT pay " ;
        if(toDate==null)
        {
            sql=sql+ "where pay.created>= " + DB.TO_DATE(fromDate, false);
        }
        else
        {
            sql=sql+  " where pay.created between " + DB.TO_DATE(fromDate, false) + " AND " + DB.TO_DATE(toDate, false);
        }
    
    
        sql=sql+" and not exists(select * from c_invoice inv where pay.C_invoice_id=inv.c_invoice_id)" +
        " and pay.tenderType='"+paymentRule+"'";
        
        sql=sql+" and pay.description='"+terminalId+"'"; //pos id was saved into descriptuon while creating the payment for multiple invoice
        
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                if(rs.getString(1)==null)
                    grandTotal=new BigDecimal(0);
                else
                    grandTotal=new BigDecimal(rs.getString(1));
            }
            
            rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();   
            }
            catch(Exception e) {}
            
            pstmt = null;
            
        }
        
        return grandTotal;
    }
    
    
    public static BigDecimal getSumOfChequeAndCardPaymentsForInfo(Properties ctx, Timestamp fromDate,Timestamp toDate,String paymentRule,int posId) throws OperationException
    {
        BigDecimal grandTotal =null;
        
        String sql="select sum(pay.payAmt) " +
        " from C_INVOICE inv,C_order ord,c_payment pay " +
        " where inv.c_order_id=ord.c_order_id"+
        " and inv.c_Invoice_id=pay.c_Invoice_id"+
        " and ord.C_POS_ID="+posId+
        " and ord.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and ord.AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
        " and inv.isSotrx='Y'"+
        " and ord.orderType in ('"+UDIOrderTypes.POS_ORDER.getOrderType()+"',"+
        "'"+UDIOrderTypes.CREDIT_ORDER.getOrderType()+"')"+
        " and pay.tenderType='"+paymentRule+"'"+
        " and pay.created between " + DB.TO_DATE(fromDate, false) +
        " and " + DB.TO_DATE(toDate, false) ;
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                if(rs.getString(1)==null)
                    grandTotal=new BigDecimal(0);
                else
                    grandTotal=new BigDecimal(rs.getString(1));
                
                grandTotal=grandTotal.add(paymentAmtWithOutInvoice(ctx,fromDate,toDate,paymentRule,posId));
            }
            
            rs.close();
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();   
            }
            catch(Exception e) {}
            
            pstmt = null;
            
        }
        
        return grandTotal;
        
    }
    
    /**
     * It returns the date-time of last completed cash journal
     * @param ctx
     * @param cashBookId
     * @return
     * @throws OperationException
     */
    public static Timestamp getTimeOfLastCompletedJournal(Properties ctx, int cashBookId, String trxName) throws OperationException
    {
        Timestamp lastDateTime = TimestampConvertor.getCurrentDateTimestamp();
        
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT MAX(cl.Created) FROM C_Cash c ");
        sqlStmt.append("INNER JOIN C_CashLine cl ON cl.C_Cash_ID = c.C_Cash_ID "); 
        sqlStmt.append("WHERE c.C_CashBook_ID=? ");
        sqlStmt.append("AND c.DocStatus IN ('CO', 'CL')");
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try
        {
            pstmt = DB.prepareStatement(sqlStmt.toString(), trxName);
            pstmt.setInt(1, cashBookId);
           
            rs = pstmt.executeQuery();
            
            if (rs.next() && rs.getTimestamp(1) != null)
            {
                lastDateTime = rs.getTimestamp(1);
            }
        }
        catch (Exception exception)
        {
            throw new OperationException("Could not get last completed journal date and time", exception);
        }
        
        return lastDateTime;
    }
    
    private static CurrentTillAmountBean getCurrentTillForInfo(Properties ctx, int terminalId, Timestamp fromDate, Timestamp toDate) throws OperationException
    {
        BigDecimal cardSum = BigDecimal.valueOf(0.0);
        BigDecimal chequeSum = BigDecimal.valueOf(0.0);
        BigDecimal cashSum=new BigDecimal(0);
        BigDecimal grandTotal;
        BigDecimal beginingBalance=null;
        BigDecimal statementDifference=null;
        int cashBookId = POSTerminalManager.getCashBookId(ctx, terminalId);
        cardSum = getSumOfChequeAndCardPaymentsForInfo(ctx,fromDate,toDate,MPayment.TENDERTYPE_CreditCard,terminalId);
        chequeSum = getSumOfChequeAndCardPaymentsForInfo(ctx,fromDate,toDate,MPayment.TENDERTYPE_Check,terminalId);
        ArrayList list = CashManager.getCashBookDetailsForTill(ctx, cashBookId,fromDate,toDate);
        Iterator iter = list.iterator();
        
        CashBookDetailBean cashDetBean;
        while(iter.hasNext())
        {
            cashDetBean = (CashBookDetailBean)iter.next();
            beginingBalance=cashDetBean.getBeginingBalance();
            statementDifference=cashDetBean.getStatementDifference();
            cashSum=cashDetBean.getStatementDifference();
            
        }
        
        grandTotal = new BigDecimal(cardSum.doubleValue()+chequeSum.doubleValue()+cashSum.doubleValue());
        
        CurrentTillAmountBean bean = new CurrentTillAmountBean();
        bean.setBeginingBalance(beginingBalance);
        bean.setStatementDifference(statementDifference);
        bean.setCardTotal(cardSum);
        bean.setCashTotal(cashSum);
        bean.setChequeTotal(chequeSum);
        bean.setTillGrandTotal(grandTotal);
        bean.setPosName(POSTerminalManager.getTerminalName(ctx, terminalId));
        
        return bean;
    }
    
    public static CurrentTillAmountBean getCurrentTillAmount(Properties ctx) throws OperationException
    {
        BigDecimal cardSum ;
        BigDecimal chequeSum;
        BigDecimal cashSum=new BigDecimal(0);
        BigDecimal grandTotal;
        BigDecimal beginingBalance=null;
        BigDecimal statementDifference=null;
        
        int cashBookId = POSTerminalManager.getCashBookId(ctx);
        Timestamp toDate = getTimeOfLastCompletedJournal(ctx, cashBookId, null);
        cardSum = getSumOfChequeAndCard(ctx,toDate,MPayment.TENDERTYPE_CreditCard);
        chequeSum = getSumOfChequeAndCard(ctx,toDate,MPayment.TENDERTYPE_Check);
        CashBookDetailBean cashDetBean = CashManager.getCashBookDetails(ctx, null);
        
        // Recently completed journal and not new cash transaction performed
        // Thus the Till beginning balance should be the ending balance
        // as the new cash journal will have
        if (DocumentEngine.STATUS_Completed.equals(cashDetBean.getDocStatus()))
        {
            beginingBalance = cashDetBean.getEndingBalance();
            statementDifference = Env.ZERO;
            cashSum = beginingBalance;
        }
        else
        {
            beginingBalance=cashDetBean.getBeginingBalance();
            statementDifference=cashDetBean.getStatementDifference();
            cashSum=cashDetBean.getStatementDifference().add(cashDetBean.getBeginingBalance());
        }
        
        grandTotal = new BigDecimal(cardSum.doubleValue()+chequeSum.doubleValue()+cashSum.doubleValue());
        
        CurrentTillAmountBean bean = new CurrentTillAmountBean();
        bean.setBeginingBalance(beginingBalance);
        bean.setStatementDifference(statementDifference);
        bean.setCardTotal(cardSum);
        bean.setCashTotal(cashSum);
        bean.setChequeTotal(chequeSum);
        bean.setTillGrandTotal(grandTotal);
        bean.setPosName(POSTerminalManager.getTerminalName(ctx));
        
        return bean;
        
    }
    
    public static MInvoice createCustomerInvoice(Properties ctx, MOrder salesOrder) throws OperationException
    {
        MInvoice invoice = null;
        
        boolean isSOTrx = salesOrder.isSOTrx();
        
        if (!isSOTrx)
            throw new OperationException("Customer Invoice should be created from sales Order");
        
        MDocType [] docType;
        boolean isSotrx=true;
        
        if (salesOrder.getOrderType().equals(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
            docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_ARCreditMemo);
        
        else if (salesOrder.getOrderType().equals(UDIOrderTypes.CREDIT_MEMO.getOrderType()))
            docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_ARCreditMemo);
        
        else if (salesOrder.getOrderType().equals(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()))
        {
            docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_APCreditMemo);
            isSotrx=false;
        }
        else    
            docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_ARInvoice); 
        invoice = new MInvoice(salesOrder,docType[0].get_ID(),new Timestamp(System.currentTimeMillis()));
        invoice.setIsSOTrx(isSotrx);
        invoice.setC_Order_ID(salesOrder.get_ID());
        invoice.setPaymentRule(salesOrder.getPaymentRule());
        invoice.setC_DocTypeTarget_ID(docType[0].get_ID());
        invoice.setC_DocType_ID(docType[0].get_ID());
        invoice.setC_BPartner_ID(salesOrder.getC_BPartner_ID());
        invoice.setC_Currency_ID(salesOrder.getC_Currency_ID());
        invoice.setIsTaxIncluded(false);
        invoice.setIsDiscountPrinted(true);
        invoice.setTotalLines(salesOrder.getTotalLines());
        invoice.setGrandTotal(salesOrder.getGrandTotal());
        invoice.setC_PaymentTerm_ID(salesOrder.getC_PaymentTerm_ID());

        PoManager.save(invoice);
        
        
        MOrderLine [] orderLines=salesOrder.getLines();
        MInvoiceLine invoiceLine;
        MTax tax;
        
        for( int i=0;i < orderLines.length;i++)
        {
            
            invoiceLine = new MInvoiceLine(invoice);
            invoiceLine.setOrderLine(orderLines[i]);
            invoiceLine.setC_OrderLine_ID(orderLines[i].get_ID());
            invoiceLine.setQtyEntered(orderLines[i].getQtyEntered());
            invoiceLine.setM_AttributeSetInstance_ID(orderLines[i].getM_AttributeSetInstance_ID());
            
            
            tax = new MTax(ctx,orderLines[i].getC_Tax_ID(),null);
            invoiceLine.setQty(orderLines[i].getQtyEntered());
            invoiceLine.setQtyEntered(orderLines[i].getQtyEntered());
            invoiceLine.setQtyInvoiced(orderLines[i].getQtyEntered());
            invoiceLine.setTaxAmt(tax.calculateTax(orderLines[i].getLineNetAmt(),false,3));
            invoiceLine.setLineNetAmt(orderLines[i].getLineNetAmt());
            invoiceLine.setLineTotalAmt(new BigDecimal(orderLines[i].getLineNetAmt().intValue()+tax.calculateTax(orderLines[i].getLineNetAmt(),false,3).intValue()));
            
            
            PoManager.save(invoiceLine);
        }
        
        return invoice;
    }
    
    
    /**
     * Creates an Invoice for a sales order
     * @param ctx Context
     * @param salesOrderId Sales Order
     * @param trxType Payment Rule
     * @param trxName Transaction
     * @return Invoice
     * @throws OperationException if could not save invoice
     */
    protected static MInvoice createARInvoice(Properties ctx, int salesOrderId, String trxName) throws OperationException
    {
        MOrder salesOrder = new MOrder(ctx,salesOrderId,trxName);
        MInvoice invoice = null;
        
        boolean isSOTrx = salesOrder.isSOTrx();
        if (!isSOTrx)
        {
            throw new OperationException("AR Invoice should be created from sales Order");
        }
        
        if (!DocumentEngine.STATUS_Completed.equals(salesOrder.getDocStatus()))
        {
            throw new OperationException("Order not in completed status, Status: " + salesOrder.getDocStatus());
        }
        
        invoice = new MInvoice(salesOrder, 0, salesOrder.getDateOrdered());
        PoManager.save(invoice);
        
        return invoice;
    }
    
    /**
     * Create Invoice lines from OrderLines
     * @param ctx
     * @param invoice
     * @param salesOrder
     * @param trxName
     * @throws OperationException if Line cannot be created
     */
    protected static void createInvoiceLines(Properties ctx, MInvoice invoice, MOrder order, String trxName) throws OperationException
    {
        MOrderLine [] orderLines = order.getLines();
        MInvoiceLine invoiceLine;

        for( int i=0;i < orderLines.length;i++)
        {
            invoiceLine = new MInvoiceLine(invoice);
            invoiceLine.setOrderLine(orderLines[i]);
            invoiceLine.setQty(orderLines[i].getQtyEntered());
            PoManager.save(invoiceLine);
        }
    }
        
    public static boolean getCashDrawerStatusFromCookie(HttpServletRequest request)
    {
        String status = getDataFromCookie(request, "preference.cashdrawer");
        return "true".equalsIgnoreCase(status);
    }
    
    public static String getPrintingTypeFromCookie(HttpServletRequest request)
    {
        return getDataFromCookie(request, "preference.printerType");
    }
    
    public static String getDataFromCookie(HttpServletRequest request, String key)
    {
        Cookie[] cookies = request.getCookies();
        String value = null;
        
        if(cookies != null)
        {
            for(int i=0;i<cookies.length;i++)
            {
                if(cookies[i].getName().equalsIgnoreCase(key))
                {
                    value = cookies[i].getValue();              
                }
                
            }
        }
        return value;
    }
    
    protected static void addInvoiceLine(Properties ctx,MInvoice invoice,BigDecimal paymentAmt) throws OperationException
    {
        paymentAmt = FormatBigDecimal.currency(paymentAmt);
        
        MInvoiceLine invoiceLine = new MInvoiceLine(invoice);
        invoiceLine.setQtyEntered(new BigDecimal(1));
        invoiceLine.setQty(new BigDecimal(1));
        invoiceLine.setQtyEntered(new BigDecimal(1));
        invoiceLine.setQtyInvoiced(new BigDecimal(1));
        invoiceLine.setPrice(paymentAmt);
        invoiceLine.setPriceList(paymentAmt);
        invoiceLine.setPriceActual(paymentAmt);
        invoiceLine.setLineNetAmt(paymentAmt);
        
        PoManager.save(invoiceLine);
        
    }
    
    public static BigDecimal getLineTaxAmt(Properties ctx, BigDecimal lineNetAmt, int taxId, BigDecimal qty)
    {
        return getLineTaxAmt(ctx, lineNetAmt, taxId, false, qty);
    }
    
    public static BigDecimal getLineTaxAmt(Properties ctx, BigDecimal lineNetAmt, int taxId,
    		Boolean isTaxIncluded, BigDecimal qty)
    {
        BigDecimal baseAmt = Env.ZERO;
        
        baseAmt = baseAmt.add(lineNetAmt);
        
        MTax tax = new MTax(ctx, taxId, null);
        
        return (tax.calculateTax(baseAmt, isTaxIncluded, 2));
    }
    
    public static void getAmountForMixedPayment(Properties ctx,MOrder order,WebDocumentBean webBean)
    {
        int [] invoiceIds= MInvoice.getAllIDs(MInvoice.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and C_ORDER_ID="+order.get_ID(),order.get_TrxName());
        
        MInvoice invoice= null;
        for(int i=0;i<invoiceIds.length;i++)
        {
            invoice= new MInvoice(ctx,invoiceIds[i],null);
            if(invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_Cash))
                webBean.setPaymentByCash(invoice.getGrandTotal().doubleValue());
            if(invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_CreditCard))
                webBean.setPaymentByCard(invoice.getGrandTotal().doubleValue());
            if(invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_DirectDebit))
                webBean.setPaymentByCard(invoice.getGrandTotal().doubleValue());
            if(invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_Check))
                webBean.setPaymentbyCheque(invoice.getGrandTotal().doubleValue());
        }
    } 
    
    
    private static boolean firstOrderOfTheDay(Properties ctx) throws OperationException
    {
        String sql="select * from c_order" +
        " where AD_CLIENT_ID=?" +
        " and dateordered=?"+
        " and orderType='"+UDIOrderTypes.POS_ORDER.getOrderType()+"'";
        
        PreparedStatement pstmt=null;
        ResultSet rs = null;
        boolean value = true;
        
        try
        {
            pstmt = DB.prepareStatement (sql, null);
            pstmt.setInt (1, Env.getAD_Client_ID(ctx));
            pstmt.setTimestamp (2, TimeUtil.getDay(JulianDate.getTodayDateOnly()));
            
            rs = pstmt.executeQuery ();
            
            if(rs.next())
            {
                value = false;
            }
            
            rs.close();
        }
        catch(SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                pstmt.close();
            }
            catch(Exception e)
            {}
            
            pstmt = null;
        }
        
        return value;
    }
    
    public static ShoppingCartBean getShoppingCartForOrder(Properties ctx, int orderId, String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx, orderId, trxName);
        
        if(order==null)
            throw new InvalidOrderIDException("Order is not present");
        
        ShoppingCartBean cart = new ShoppingCartBean(); 
        
        int priceListId = order.getM_PriceList_ID();
        MPriceList priceList = MPriceList.get(ctx, priceListId, null);
        String currency = PriceListManager.getCurrency(ctx, priceListId);
        
        cart.setCurrency(currency);
        
        ArrayList<ItemBean> cartItems = new ArrayList<ItemBean>();
        
        MOrderLine[] orderlines =  order.getLines();
        MOrderLine orderline = null;
        ItemBean item = null;
        BigDecimal orderTotal = Env.ZERO;
        BigDecimal orderTaxTotal = Env.ZERO;      
        BigDecimal orderQtyTotal = Env.ZERO;
        
        
        for (int i = 0; i < orderlines.length; i++) 
        {
            int c_tax_id = orderlines[i].getC_Tax_ID();
            MTax tax = new MTax(ctx, c_tax_id, null);
            
            orderline = orderlines[i];
            item = new ItemBean();
            item.setTaxRate(tax.getRate());
            
            MProduct product = orderline.getProduct();
            
            String description = product.getName();
            int productId = product.get_ID();
            BigDecimal qty = orderline.getQtyOrdered();
            String uom = product.getUOMSymbol();
            
            //Prices
            BigDecimal actualPrice = orderline.getPriceActual();
            BigDecimal lineNetAmt = actualPrice.multiply(qty);            
                                 
            BigDecimal lineNetTaxAmt = tax.calculateTax(lineNetAmt, priceList.isTaxIncluded(), 2);
            BigDecimal lineUnitTaxAmt = tax.calculateTax(actualPrice, priceList.isTaxIncluded(), 2);
            
            if (!priceList.isTaxIncluded())
            {
            	lineNetAmt = lineNetAmt.add(lineNetTaxAmt);
                item.setUnitPrice(actualPrice);
                item.setInclPrice(actualPrice.add(lineUnitTaxAmt));
            }
            else
            {
            	item.setInclPrice(actualPrice);
            	item.setUnitPrice(actualPrice.subtract(lineUnitTaxAmt));
            }
            
            item.setIsTaxIncluded(priceList.isTaxIncluded());
            item.setListPrice(orderline.getPriceList());
            item.setPriceLimit(orderline.getPriceLimit());
            item.setProductId(Integer.valueOf(productId));
            item.setDescription(description);
            item.setQty(qty);           
            item.setUom(uom);           
            item.setProductName(product.getName());
           
            item.setTaxAmt(lineNetTaxAmt);
            item.setPrice(lineNetAmt);
            item.setDiscountPercent(orderline.getDiscount());
            
            //calculating totals
            orderQtyTotal = orderQtyTotal.add(qty);
            orderTaxTotal = orderTaxTotal.add(lineNetTaxAmt);
            orderTotal = orderTotal.add(lineNetAmt); 
            
            cartItems.add(item);
        }   
        
        cart.setTotalPrice(orderTotal);        
        cart.setItems(cartItems);
        return cart;
    }
    
    
    public static ShoppingCartBean getShoppingCartForOrderInEdit(Properties ctx, int orderId, String trxName) throws OperationException
    {
        MOrder order = new MOrder(ctx, orderId, trxName);
        
        if(order==null)
            throw new InvalidOrderIDException("Order is not present");
        
        ShoppingCartBean cart = new ShoppingCartBean(); 
        
        int priceListId = Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM);       
        String currency = PriceListManager.getCurrency(ctx, priceListId);
        
        /*        if (currency.equals("EUR"))
         cart.setCurrency(Constants.EURO_SIGN);
         else */
        cart.setCurrency(currency);
        
        ArrayList<ItemBean> cartItems = new ArrayList<ItemBean>();
        
        MOrderLine[] orderlines =  order.getLines();
        MOrderLine orderline = null;
        ItemBean item = null;
        double total=0;
        double taxTotal=0;
        double priceTotal=0;
        double qtyTotal=0;
        
        for (int i = 0; i < orderlines.length; i++) 
        {
            orderline = orderlines[i];
            item = new ItemBean();
            
            MProduct product = orderline.getProduct();
            
            String description = product.getName();
            int productId = product.get_ID();
            BigDecimal qty = orderline.getQtyOrdered();
            
            item.setProductId(Integer.valueOf(productId));
            item.setDescription(description);
            item.setQty(qty);
            
            int [] taxIds = MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+"  and C_TAXCATEGORY_ID="+product.getC_TaxCategory_ID() + " and isActive='Y'",null);
            if(taxIds.length==0)
                throw new OperationException("no tax Category for the product");
            MTax tax = new MTax(ctx,taxIds[0],null);
            BigDecimal priceTax= tax.calculateTax(orderline.getLineNetAmt(),false,2);
           
            
            
            item.setUnitPrice(orderline.getPriceEntered());
            item.setStandardPrice(orderline.getLineNetAmt());
            item.setTaxAmt(priceTax);
            item.setPrice(orderline.getLineNetAmt().add(priceTax));
            item.setActualPrice(orderline.getLineNetAmt().add(priceTax)); //initially price and the actual price are same
            
            priceTotal=priceTotal+orderline.getLineNetAmt().doubleValue();
            taxTotal=taxTotal+priceTax.doubleValue();
            total=total+orderline.getLineNetAmt().add(priceTax).doubleValue();
            qtyTotal=qtyTotal+qty.doubleValue();
            
            item.setPriceTotal(new BigDecimal(priceTotal));
            item.setTaxTotal(new BigDecimal(taxTotal));
            item.setgrandTotal(new BigDecimal(total));
            item.setQtyTotal(new BigDecimal(qtyTotal));
            item.setDiscountPercent(orderline.getDiscount());
            
            cartItems.add(item);
        }
        
        //setting prices
       // StockManager.setPOSItemPrices(ctx,cartItems,true);
        
        BigDecimal totalPrice = new BigDecimal(0.0d);
        for(ItemBean bean:cartItems)
        {
            if(bean.getPriceTotal()!=null)
            {
                totalPrice = totalPrice.add(bean.getPriceTotal());
            }
        }
        
        cart.setTotalPrice(totalPrice);
        cart.setItems(cartItems);
        
        return cart;
    }
    
    
    
    public static OrderBean getPOSOrderDetail(Properties ctx,String documentNo) throws InvalidOrderIDException,OperationException
    {
//      checking whether order exixts        
        int ids[] = MOrder.getAllIDs(MOrder.Table_Name,"ISACTIVE='Y' AND DOCUMENTNO = '" + documentNo + "' and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx),null);
        
        if((ids == null)||(ids.length == 0))
        {
            throw new InvalidOrderIDException("Could not found order!");
        }
        
        if(ids.length>1)
        throw new OperationException("two orders with same document number");
        
        MOrder order= OrderManager.loadOrder(ctx, ids[0], null); 
       OrderBean bean=new OrderBean();
       bean.setOrderId(order.get_ID());
       bean.setBpartnerId(order.getC_BPartner_ID());
       bean.setDocStatus(order.getDocStatus());
       bean.setOrderType(order.getOrderType());
       bean.setPaymentTermId(order.getC_PaymentTerm_ID());
       
       return bean;
            
    }
    
    public static MOrder createAndCompletePOSOrder(Properties ctx,OrderLineBean bean,ArrayList cartBeanItems) throws BPartnerOverCreditLimitException,OperationException, DiscountLimitException
    {
        
        MOrder order = null;
        
        Trx trx = Trx.get(TrxPrefix.getPrefix(),true);
        
        try
        {
            trx.start();
            order = createPOSOrder(ctx, bean, cartBeanItems,null/*trx.getTrxName()*/);
            trx.commit();
        }
        catch (OperationException e)
        {
            trx.rollback();
            throw (e);
        }
        finally
        {
            trx.close();
        }
        
        
        OrderLineBean orderLineBean = new OrderLineBean();
        
        if(order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_Cash))
        {
            MOrder newOrder = new MOrder(ctx,order.get_ID(),null);//has to load again as it is not getting the grand total
            orderLineBean.setAmountGiven(newOrder.getGrandTotal());
            orderLineBean.setAmountRefunded(new BigDecimal(0));
        }
        
        order = completePOSOrder(ctx, order.get_ID(), orderLineBean, null);
        
        return order;
    }
    
    public static BigDecimal getPayment(Properties ctx, int orderId, String tenderType, String trxName) throws OperationException
    {
        MOrder order = OrderManager.loadOrder(ctx,orderId,trxName);
        
        if(! UDIOrderTypes.POS_ORDER.getOrderType().equalsIgnoreCase(order.getOrderType()))
        {
            throw new OperationException("Only POS Orders are supported!");
        }       
        
        if(! MOrder.DOCSTATUS_Completed.equalsIgnoreCase(order.getDocStatus()))
        {
            throw new OperationException("Document is not yet completed!");
        }
        
        boolean isCheck = MPayment.TENDERTYPE_Check.equalsIgnoreCase(tenderType);
        boolean isCard = MPayment.TENDERTYPE_CreditCard.equalsIgnoreCase(tenderType);
        boolean isCash = "S".equalsIgnoreCase(tenderType);
                
        if(isCash)
        {
            return order.getAmountTendered();
        }
        
        if(isCheck || isCard)
        {
            String whereClause = " C_ORDER_ID = " + orderId +
                    " AND TENDERTYPE = '" + tenderType + "'";
            
            int[] ids = MPayment.getAllIDs(MPayment.Table_Name,whereClause,trxName);
            
            if(ids == null || ids.length == 0)
            {
                return new BigDecimal(0.0d);
            }
            
            MPayment payment = new MPayment(ctx,ids[0],trxName);
            
            return payment.getPayAmt();
        }
        
        throw new OperationException("Invalid tender type :" + tenderType);
        
    }
    
    public static String getForward(Properties ctx,String orderType)
    {
    
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_ORDER.getOrderType()))
        {
            return "/CreatePOSOrder.do";
        }
        if(orderType.equalsIgnoreCase(UDIOrderTypes.CUSTOMER_RETURN_ORDER.getOrderType()))
        {
            return "/CustomerReturnOrder.do";
        }
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.CREDIT_ORDER.getOrderType()))
        {
            return "/InitCreateCreditOrderAction.do?action=initCreateCreditOrder";
        }
        
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType()))
        {
            return "/GetAllPOSVendor.do?action=getAllVendors&isSales=true";
        }
        if(orderType.equalsIgnoreCase(UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType()))
        {
            return "/GetAllPOSVendor.do?action=getAllVendors&isSales=false";
        }
        
        else return "/CreatePartialPOSOrder.do";
        
    }
    
    public static ArrayList getItemBean(ArrayList<ItemBean> itemBean)
    {
        ArrayList<ItemBean> list = new ArrayList<ItemBean>();
        for(ItemBean bean : itemBean)
        {
            if(bean.getUnitPrice().compareTo(bean.getStandardPrice())==0 && bean.getDiscountPercent().doubleValue() > 0)
            {
                double disPer = bean.getDiscountPercent().doubleValue();
                double stanPrice = bean.getStandardPrice().doubleValue();
                double newStanPrice = stanPrice -( (stanPrice*disPer)/100);
                bean.setStandardPrice(new BigDecimal(newStanPrice));
                
            }
            
            list.add(bean);
        }
        
        return list;
    }
    
    //----------------------------------------------------------------------------------------------
    
    public static int checkoutOrder(Properties ctx, CheckoutBean checkoutBean, ArrayList<ItemBean> items, String trxName) throws Exception
    {
        boolean isSoTrx = true;
        String orderType = checkoutBean.getOrderType();
        
        int m_pricelist_id = POSTerminalManager.getSOPriceListId(ctx);
        int m_warehouse_id = POSTerminalManager.getWarehouseId(ctx);
        int cashBookId = POSTerminalManager.getCashBookId(ctx);
        
        //close previous cash journals
        if(firstOrderOfTheDay(ctx))
        {
            CashManager.closePreviousDraftedCashjournals(ctx, cashBookId, trxName);
        }
        
        Integer c_bpartner_id = checkoutBean.getBpartnerId();
        
        //takes bp pricelist
        if(c_bpartner_id != null)
        {
            MBPartner bpartner = MBPartner.get(ctx, c_bpartner_id);         
            if(bpartner != null && bpartner.getM_PriceList_ID() > 0)
            {
                m_pricelist_id = bpartner.getM_PriceList_ID();              
            }             
        }
        else
        {
            //set standard customer
            c_bpartner_id = POSTerminalManager.getCashBPartnerId(ctx);
        }
        
        String tenderType = checkoutBean.getTenderType();
        String paymentRule = checkoutBean.getPaymentRuleFromTenderType();
        
        //create order
        MOrder order = OrderManager.createOrder(ctx, c_bpartner_id, isSoTrx, m_pricelist_id, orderType, 
                m_warehouse_id, paymentRule, trxName);    
        
        
        BigDecimal[] discounts = checkoutBean.getDiscount();
        BigDecimal grandTotal =  Env.ZERO;
        
        for(int i=0; i<items.size(); i++)
        {
            ItemBean itemBean = items.get(i);           
            
            int m_product_id = itemBean.getProductId();
            BigDecimal qty = itemBean.getQty();
            BigDecimal actualPrice = itemBean.getActualPrice();
            BigDecimal discount = itemBean.getDiscountPercent();
            BigDecimal price = itemBean.getPrice();
            grandTotal = grandTotal.add(price);
            
            OrderManager.createOrderLine(ctx, order, m_product_id, qty, discount, price);           
        }
        
        // Reload order due to grand total update when adding order lines
        // @see MOrderLine.updateHeaderTax()
        order = new MOrder(ctx, order.get_ID(), trxName);
        order.setGrandTotal(grandTotal);
        //set cash tendered and refunded
        BigDecimal cashAmt = (checkoutBean.getCashAmt() == null ? Env.ZERO : checkoutBean.getCashAmt());
        BigDecimal cardAmt = (checkoutBean.getCardAmt() == null ? Env.ZERO : checkoutBean.getCardAmt());
        BigDecimal chequeAmt = (checkoutBean.getChequeAmt() == null ? Env.ZERO : checkoutBean.getChequeAmt());
        
        BigDecimal amountTendered = checkoutBean.getAmountTendered();
        BigDecimal amountRefunded = checkoutBean.getAmountRefunded();
        BigDecimal writeOffAmt = Env.ZERO;
        BigDecimal amountRefundedAfterWriteOff = Env.ZERO;
        BigDecimal paymentAmt = Env.ZERO; //Actual payment amount after writeOff
        
        //--------------------------------------------------------------------------
        // calculate discount amount
        BigDecimal discountAmt = Env.ZERO;
        BigDecimal orderTotal = order.getGrandTotal();
        
        BigDecimal totalAmount = orderTotal;
        if (checkoutBean.getDiscountOnTotal())
        {
            BigDecimal orderDiscountedTotal = checkoutBean.getGrandTotal();
            discountAmt = orderTotal.subtract(orderDiscountedTotal);
            totalAmount = orderDiscountedTotal;
        }
        else if (checkoutBean.getDiscountOnTotalPercent())
        {
        	discountAmt = orderTotal.multiply(checkoutBean.getTotalDiscount().divide(Env.ONEHUNDRED, 2, RoundingMode.HALF_DOWN));
        	totalAmount = orderTotal.subtract(discountAmt);
        }
        //--------------------------------------------------------------------------
        
        if (Constants.PAYMENT_RULE_CASH.equalsIgnoreCase(tenderType))
        {
            writeOffAmt = PaymentManager.computeWriteOff(ctx, order.getC_Currency_ID(), totalAmount, trxName);
            paymentAmt = totalAmount.subtract(writeOffAmt);
            amountRefundedAfterWriteOff = amountTendered.subtract(paymentAmt);
            
            if(amountTendered != null)
            {
                if (amountTendered.add(writeOffAmt).compareTo(totalAmount) == -1)
                {
                   throw new OperationException("Amount tendered is less than total amount.");
                }
            }
            
            order.setAmountTendered(checkoutBean.getAmountTendered());
            order.setAmountRefunded(amountRefundedAfterWriteOff);
            
            PoManager.save(order);
        }
        if (Constants.PAYMENT_RULE_CARD.equalsIgnoreCase(tenderType))
        {
             cardAmt = totalAmount;
        }
        if (Constants.PAYMENT_RULE_CHEQUE.equalsIgnoreCase(tenderType))
        {
             chequeAmt = totalAmount;
        }
        if (Constants.PAYMENT_RULE_MIXED.equalsIgnoreCase(tenderType))
        {
            BigDecimal totalPaid = Env.ZERO;
            
            if(cashAmt != null)
            {
                amountTendered = cashAmt;
                totalPaid = cashAmt;
            }
            if(chequeAmt != null)
            {
                totalPaid = totalPaid.add(chequeAmt);
            }
            if(cardAmt != null)
            {
                totalPaid = totalPaid.add(cardAmt);
            }
                        
            if(totalPaid.doubleValue() < order.getGrandTotal().doubleValue())
            {
                throw new OperationException("Amount tendered is less than total amount.");
            }
            
            amountRefunded = totalPaid.subtract(order.getGrandTotal());         
            order.setAmountTendered(amountTendered);
            order.setAmountRefunded(amountRefunded);            
                    
            PoManager.save(order);
        }
        PoManager.processIt(order, DocumentEngine.ACTION_Complete); 
        
        if(order.isSOTrx()==true)
        {
            CreditCheckBean crBean = OrderManager.checkBPartnerCreditLimit(ctx,order.getC_BPartner_ID(),order.get_ID(),order.get_TrxName());
            if(crBean.getValid()==false)
                throw new BPartnerOverCreditLimitException(crBean.getMsg());
        }
        
        checkoutBean.setCashAmt(cashAmt);
        checkoutBean.setCardAmt(cardAmt);
        checkoutBean.setChequeAmt(chequeAmt);
        checkoutBean.setWriteOffAmt(writeOffAmt);
        checkoutBean.setDiscountAmt(discountAmt);
        
        createAndCompleteDocuments(ctx, order, checkoutBean, trxName);
        
        return order.get_ID();
    }
       
    public static void setDiscount(Properties ctx, CheckoutBean checkoutBean, HttpServletRequest request, String trxName) throws OperationException, DiscountLimitException
    {       
        BigDecimal[] prices = checkoutBean.getDiscountedPrice();
        Boolean[] isDiscOnInclUnitPrices = checkoutBean.getIsDiscOnInclUnitPrice();
        BigDecimal[] discountedInclUnitPrices = checkoutBean.getDiscInclUnitPrice();
        BigDecimal[] discountPerc = checkoutBean.getDiscount();
        Boolean[] isDiscountsOnPerc = checkoutBean.getIsDiscOnPerc();
        Boolean[] isDiscountsOnTotal = checkoutBean.getIsDiscOnTotal();
        Integer priceListId = checkoutBean.getPriceListId();
        MPriceList priceList = MPriceList.get(ctx, priceListId, trxName);
        int priceListVersionId = 0;
        
        if (priceListId != null)
        {
            MPriceListVersion plv = PriceListManager.getPriceListVersion(ctx, priceListId, null, trxName);
            priceListVersionId = plv.get_ID();
        }
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
                                
        ArrayList<ItemBean> newItems = new ArrayList<ItemBean>();
        
        if (items == null || items.isEmpty()) 
        {
            return;
        }       
        
        BigDecimal grandTotal = Env.ZERO;
        BigDecimal grandTotalWithoutDiscount = Env.ZERO;
        
        int roleId = Env.getAD_Role_ID(ctx);
        MRole role = MRole.get(ctx, roleId);
        BigDecimal userDiscount = role.getUserDiscount();
        BigDecimal discountLimit  = userDiscount;
        
        BigDecimal newOrderTotal = checkoutBean.getDiscountedTotal();
        BigDecimal newOrderDiscount = checkoutBean.getTotalDiscount();
        
        if (newOrderDiscount.compareTo(discountLimit) == 1) // fraud!
        {
        	throw new DiscountLimitException("user discount limit exceeded on total, " +
        			"discount percentage of " + newOrderDiscount +" was tried to be given");
        }
        
        Iterator<ItemBean> itemBeanIter = items.iterator();
        BigDecimal priceLimitTotal = Env.ZERO;
        
        while (itemBeanIter.hasNext())
        {
        	ItemBean item = itemBeanIter.next();
        	BigDecimal priceLimit = item.getPriceLimit();
        	if (priceLimit == null)
        	{
        		throw new OperationException("Product "+ item.getProductName()+" has no price limit");
        	}
        	priceLimitTotal = priceLimitTotal.add(priceLimit);
        }
        
        if (priceLimitTotal.compareTo(newOrderTotal) == 1) //fraud!
        {
        	throw new DiscountLimitException("user discount limit exceeded on total, " +
        			"total of " + newOrderTotal + "was tried to be given");
        }
        
        for(int i=0; i< prices.length; i++)
        {
            BigDecimal price = prices[i];
            Boolean isDiscOnInclUnitPrice = false;
            BigDecimal discountedInclUnitPrice = Env.ZERO;
            Boolean isDiscOnPerc = false;
            Boolean isDiscOnTotal = false;
            
            if (isDiscOnInclUnitPrices != null)
            {
                isDiscOnInclUnitPrice = isDiscOnInclUnitPrices[i];
            }
            if (discountedInclUnitPrices!= null)
            {
                discountedInclUnitPrice = discountedInclUnitPrices[i];
            }
            if(isDiscountsOnPerc != null)
            {
                isDiscOnPerc = isDiscountsOnPerc[i];
            }
            if(isDiscountsOnTotal != null)
            {
                isDiscOnTotal = isDiscountsOnTotal[i];
            }
            ItemBean bean = items.get(i);
            
            BigDecimal unitPrice = bean.getUnitPrice();
            BigDecimal inclUnitPrice = bean.getInclPrice();
            BigDecimal listPrice = bean.getListPrice();
            BigDecimal qty = bean.getQty();
            BigDecimal total = Env.ZERO;
            BigDecimal tax = bean.getTaxAmt();
            BigDecimal taxRate = bean.getTaxRate() == null? Env.ZERO: bean.getTaxRate();
            BigDecimal taxMulFactor = (taxRate.divide(Env.ONEHUNDRED, 2, RoundingMode.HALF_UP)).add(Env.ONE);
        
            BigDecimal discount = Env.ZERO;
            BigDecimal actualPrice = Env.ZERO;
            
            if (priceList.isTaxIncluded())
        	{
        		actualPrice = inclUnitPrice;
        	}
            else
            {
            	actualPrice = unitPrice;
            }
            
            total = actualPrice.multiply(qty);
            
            // verify fraud
            
            if (!isDiscOnInclUnitPrice && !actualPrice.equals(discountedInclUnitPrice))
        	{
            	if (discountedInclUnitPrice.compareTo(bean.getPriceLimit()) == -1)
        		{
            		 throw new OperationException("incl unit price less than limit price, a value of " +
        				discountedInclUnitPrice + "was tried to be given");
        		}
            	if (discountedInclUnitPrice.compareTo(bean.getListPrice()) == 1)
            	{
            		 throw new OperationException("incl unit price less greater list price, a value of " +
             				discountedInclUnitPrice + "was tried to be given");
            	}
        	}
            if (!isDiscOnPerc && !discountPerc[i].equals(bean.getDiscountPercent()))
            {
            	if (discountPerc[i].compareTo(Env.ZERO) == -1)
            	{
            		throw new OperationException("user discount less than zero on product " +
            			"" + bean.getProductName() +", discount percentage given is "+ discountPerc[i]);
            	}
            	if (discountPerc[i].compareTo(discountLimit) == 1)
            	{
            		throw new OperationException("user discount exceeds discount limit on product " +
                			"" + bean.getProductName() +", discount percentage given is "+ discountPerc[i]);
            	}
            }
            if (!isDiscOnTotal && !total.equals(price))
            {
            	if (price.compareTo(bean.getPriceLimit().multiply(bean.getQty())) == -1)
            	{	
            		throw new OperationException("line total less than price limit total, a value of " +
                			price+" was tried to be given");
            	}
            		
        		if (price.compareTo(bean.getListPrice().multiply(bean.getQty())) == 1)
            	{
            		throw new OperationException("line total greater than price list total, a value of " +
            			price+" was tried to be given");
            	}
            }
           
            if (isDiscOnInclUnitPrice)
            {
        		discount = (listPrice.subtract(discountedInclUnitPrice)).divide(listPrice, 12, RoundingMode.HALF_UP).multiply(Env.ONEHUNDRED);
        		actualPrice = discountedInclUnitPrice;
            }
            else if (isDiscOnPerc)
            {
                discount = discountPerc[i];
                actualPrice = price.divide(qty, 2, RoundingMode.HALF_UP);              	
            }
            else if (isDiscOnTotal)
            {
                discount = ((total.subtract(price)).divide(total, 4, RoundingMode.HALF_UP)).multiply(Env.ONEHUNDRED);
                actualPrice = price.divide(qty, 2, RoundingMode.HALF_UP);
                bean.setIsDiscountOnTotal(false); // reset
            }
            
            if (discount.compareTo(discountLimit) == 1)
            {
            	throw new DiscountLimitException("user discount limit exceeded on product " + bean.getProductName() +", discount percentage given is "+ discount);
            }
            
            // fraud verified
            
            bean.setActualPrice(actualPrice);
            
            if (priceList.isTaxIncluded())
            {
            	BigDecimal exclActualPrice = actualPrice.divide(taxMulFactor, 2, RoundingMode.HALF_UP);
            	tax = actualPrice.subtract(exclActualPrice);
            	unitPrice = actualPrice.divide(taxMulFactor, 2, RoundingMode.HALF_UP);
            	bean.setInclPrice(actualPrice);
            }
            else
            {
            	tax = actualPrice.divide(taxMulFactor, 4).setScale(2, RoundingMode.HALF_UP);
            	bean.setInclPrice(actualPrice);
            }
            
            bean.setTaxAmt(tax.multiply(qty));
            bean.setUnitPrice(unitPrice);
            bean.setDiscountPercent(discount);
            bean.setPrice(price);
            bean.setIsDiscountOnInclUnitPrice(isDiscOnInclUnitPrice);
            bean.setIsDiscountOnPercentage(isDiscOnPerc);
            bean.setIsDiscountOnTotal(isDiscOnTotal);
            
            if (isDiscOnInclUnitPrice!=null && isDiscOnInclUnitPrice)
            {
                bean.setDiscountedInclUnitPrice(discountedInclUnitPrice);
                bean.setDiscountedLinePrice(discountedInclUnitPrice);
            }
            // todo - remove that grand total hack
            grandTotal = grandTotal.add(price);
            bean.setgrandTotal(grandTotal);
            grandTotalWithoutDiscount = grandTotalWithoutDiscount.add(total);
            
            newItems.add(bean);
        }
        
        //Check for discount on total
        request.getSession().setAttribute(Constants.DISCOUNT_ON_ORDER_TOTAL, checkoutBean.getDiscountOnTotal());        
        request.getSession().setAttribute(Constants.DISCOUNT_ON_ORDER_TOTAL_PERCENT, checkoutBean.getDiscountOnTotalPercent());
        request.getSession().setAttribute(Constants.SHOPPING_CART_DISCOUNT_PERCENTAGE, newOrderDiscount);
        request.getSession().setAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL, newOrderTotal);
        request.getSession().setAttribute(Constants.SHOPPING_CART_TOTAL, grandTotal);       
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART_ITEMS, newItems);
        request.getSession().setAttribute(Constants.CHECKOUT_BEAN, checkoutBean);
      
    }
    
    
    public static void setQuickDiscount(Properties ctx, CheckoutBean checkoutBean, HttpServletRequest request, String trxName) throws OperationException, DiscountLimitException
    {       
        BigDecimal[] prices = checkoutBean.getDiscountedPrice();
        Boolean[] isDiscOnInclUnitPrices = checkoutBean.getIsDiscOnInclUnitPrice();
        BigDecimal[] discountPerc = checkoutBean.getDiscount();
        Boolean[] isDiscountsOnPerc = checkoutBean.getIsDiscOnPerc();
        Boolean[] isDiscountsOnTotal = checkoutBean.getIsDiscOnTotal();
        Integer priceListId = checkoutBean.getPriceListId();
        Integer productId = checkoutBean.getM_productId();
        BigDecimal totalLinesIncDisc = checkoutBean.getGrandTotal(); 
        MPriceList priceList = MPriceList.get(ctx, priceListId, trxName);
        int priceListVersionId = 0;
        
        String currency = (String) request.getSession().getAttribute(Constants.CURRENCY_SYMBOLE);
        
        int roleId = checkoutBean.getRoleId();
        MRole role = MRole.get(ctx, roleId);
        BigDecimal userDiscount = role.getUserDiscount();
        BigDecimal discountLimit  = userDiscount;
        boolean isDiscountAllowedOnTotal = role.isDiscountAllowedOnTotal();
        boolean isDiscountUptoLimitPrice = role.isDiscountUptoLimitPrice();
        boolean isOverideLimitPrice = role.isOverwritePriceLimit();
        
        if (priceListId != null)
        {
            MPriceListVersion plv = PriceListManager.getPriceListVersion(ctx, priceListId, null, trxName);
            priceListVersionId = plv.get_ID();
        }
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        
        if (items == null || items.isEmpty()) 
        {
            return;
        } 
        
        Iterator<ItemBean> iter = items.iterator();
        ItemBean bean;
        
        if(isDiscountAllowedOnTotal && isDiscountsOnTotal[0])
        {
            BigDecimal totalListPrice = Env.ZERO;
            BigDecimal totalLimitPrice = Env.ZERO;
            BigDecimal taxPerc = Env.ZERO;
            BigDecimal grandTotal = Env.ZERO;
            
            for (ItemBean itemBean : items)
            {
                MPriceList sprice = new MPriceList(ctx, itemBean.getPriceListId(), null);
                ProductBean details = POSProductManager.viewPOSProduct(ctx, itemBean.getProductId(), priceListId, null);
                taxPerc = (details.getTaxRate().add(Env.ONEHUNDRED)).divide(Env.ONEHUNDRED, 4, BigDecimal.ROUND_HALF_UP);
                
               
               if(sprice.isTaxIncluded())
               {
                   totalLimitPrice = totalLimitPrice.add(details.getPriceLimit());
                   totalListPrice = totalListPrice.add(details.getPriceList());
               }
               else
               {
                   totalLimitPrice = totalLimitPrice.add(details.getPriceLimit().multiply(taxPerc));
                   totalListPrice = totalListPrice.add(details.getPriceList().multiply(taxPerc));
               }
               
               grandTotal = itemBean.getGrandTotal();
               
            }
            
            if(isDiscountUptoLimitPrice && !isOverideLimitPrice)
            {
                if(totalLinesIncDisc.compareTo(totalListPrice) == 1)
                {
                    throw new OperationException("DISCOUNTED TOTAL EXCEEDS TOTAL LINES!!! A discount to price " + currency + totalLinesIncDisc +" was tried to be given");
                }
                
                if(totalLinesIncDisc.compareTo(totalLimitPrice) == 1)
                {
                    throw new OperationException("TOTAL DISCOUNT EXCEEDED!!! A discount to price " + currency + totalLinesIncDisc +" was tried to be given");
                }
            }
            
            if(!isDiscountUptoLimitPrice && isOverideLimitPrice)
            {
                if(userDiscount.compareTo(Env.ZERO) == 1)
                {
                   BigDecimal discount = totalListPrice.subtract(totalLinesIncDisc) ;
                   BigDecimal discPerc = discount.divide(totalListPrice, 2, BigDecimal.ROUND_HALF_UP).multiply(Env.ONEHUNDRED);
                   
                   if(discPerc.compareTo(userDiscount) == 1)
                   {
                       throw new OperationException("DISCOUNT LIMIT EXCEEDED!!!");
                   }
                   
                   discount = totalLinesIncDisc.subtract(grandTotal);
                   
                   if(discount.compareTo(Env.ZERO) == 1)
                   {
                       throw new OperationException("DISCOUNT CANNOT BE NAGATIVE!!!");
                   }
                }
                
                if(userDiscount.compareTo(Env.ZERO) == 0)
                {
                    throw new OperationException("CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET");
                }
            }
            
            if(isDiscountUptoLimitPrice && !isOverideLimitPrice)
            {
                if(userDiscount.compareTo(Env.ZERO) == 1)
                {
                    if(totalLinesIncDisc.compareTo(totalLimitPrice) == -1)
                    {
                        throw new OperationException("CANNOT OVERRIDE LIMIT PRICE!!!");
                    }
                    
                    if(totalLinesIncDisc.compareTo(totalListPrice) == 1)
                    {
                        throw new OperationException("DISCOUNTED TOTAL EXCEEDS TOTAL LINES!!!");
                    }
                }
                
                if(userDiscount.compareTo(Env.ZERO) == 0)
                {
                    throw new OperationException("CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET");
                }
            }
            
            if(isDiscountUptoLimitPrice && isOverideLimitPrice)
            {
                throw new OperationException("DISCOUNT ERROR!! CANNOT PROCESS YOUR REQUEST!!!");
            }
            
            BigDecimal discountAmt = totalListPrice.subtract(totalLinesIncDisc);
            checkoutBean.setDiscountOnTotal(isDiscountsOnTotal[0]);
            checkoutBean.setDiscountAmt(discountAmt);
            checkoutBean.setDiscountedTotal(totalLinesIncDisc);
            checkoutBean.setGrandTotal(totalLinesIncDisc);
            
            request.getSession().setAttribute(Constants.SHOPPING_CART_DISCOUNTED_TOTAL, totalLinesIncDisc);
        }
        else
        {
            
            while (iter.hasNext())
            {                
                bean = (ItemBean) iter.next();            
                if(bean.getProductId().equals(productId))
                {
                    BigDecimal price = prices[0];
                    BigDecimal listPrice = bean.getListPrice();
                    BigDecimal limitPrice = bean.getPriceLimit();
                    BigDecimal qty = bean.getQty();
                    BigDecimal total = Env.ZERO;
                    BigDecimal grandTotal = Env.ZERO;
                    BigDecimal grandTotalWithoutDiscount = Env.ZERO;
                    BigDecimal tax = bean.getTaxAmt();
                    
                    MProduct product = new MProduct(ctx,productId, trxName);
                    MTax mtax = TaxManager.getTaxFromCategory(ctx, product.getC_TaxCategory_ID(), trxName);
                    
                    BigDecimal taxRate = mtax.getRate();
                    BigDecimal taxMulFactor = (taxRate.divide(Env.ONEHUNDRED, 2, BigDecimal.ROUND_HALF_UP)).add(Env.ONE);
                    BigDecimal newTaxAmt = mtax.calculateTax(price.multiply(qty), true, 12).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    
                    if(!priceList.isTaxIncluded())
                    {
                        listPrice = listPrice.multiply(taxMulFactor);
                    }
                    
                    if(isDiscountUptoLimitPrice)
                    {
                        if(price.compareTo(limitPrice) == -1)
                        {
                            throw new OperationException("You cannot exceed the limit price!! A discount to price " + currency + prices[0] +" was tried to be given");
                        }
                        
                        if(price.compareTo(listPrice) == 1)
                        {
                            throw new OperationException("You cannot exceed the list price!! A discount to price " + currency + prices[0] +" was tried to be given");
                        }
                    }
                    
                    if(isDiscountUptoLimitPrice && !isOverideLimitPrice)
                    {
                        if(price.compareTo(limitPrice) == -1)
                        {
                            throw new OperationException("You cannot exceed the limit price!! A discount to price " + currency + prices[0] +" was tried to be given");
                        }
                        
                        if(price.compareTo(listPrice) == 1)
                        {
                            throw new OperationException("You cannot exceed the list price!! A discount to price " + currency + prices[0] +" was tried to be given");
                        }
                    }
                    
                    if(!isDiscountUptoLimitPrice && isOverideLimitPrice)
                    {
                        if(userDiscount.compareTo(Env.ZERO) == 1)
                        {
                           if(discountPerc[0].compareTo(userDiscount) == 1)
                            {
                               throw new OperationException("DISCOUNT LIMIT EXCEEDED!!!");
                            }
                        }
                        
                        if(userDiscount.compareTo(Env.ZERO) == 0)
                        {
                            throw new OperationException("CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET");
                        }   
                    }
                    
                    if(!isDiscountUptoLimitPrice && !isOverideLimitPrice)
                    {
                        if(userDiscount.compareTo(Env.ZERO) == 1)
                        {
                            if(discountPerc[0].compareTo(userDiscount) == 1)
                            {
                                throw new OperationException("DISCOUNT LIMIT EXCEEDED!!!");
                            }
                            
                            if(discountPerc[0].compareTo(Env.ZERO) == -1)
                            {
                                throw new OperationException("DISCOUNT CANNOT BE NEGATIVE!!!");
                            }
                            
                            if(price.compareTo(limitPrice) == -1)
                            {
                                throw new OperationException("CANNOT OVER RIDE LIMIT PRICE!!!");
                            }
                        }
                        
                        if(userDiscount.compareTo(Env.ZERO) == 0)
                        {
                            throw new OperationException("CANNOT APPLY DISCOUNT!!!<br>NO DISCOUNT PERCENTAGE SET");
                        }  
                         
                    }
                    
                    if(isDiscountUptoLimitPrice && isOverideLimitPrice)
                    {
                        throw new OperationException("DISCOUNT ERROR!! CANNOT PROCESS YOUR REQUEST!!!");
                    }
                    
                    bean.setInclPrice(price.setScale(2));
                    
                    BigDecimal incPriceExcVat = ((price.multiply(qty)).subtract(newTaxAmt)).divide(qty, 2, BigDecimal.ROUND_HALF_UP);
                    bean.setDiscountedLinePrice(incPriceExcVat.setScale(2));
                    bean.setDiscountedInclUnitPrice(incPriceExcVat);
                    bean.setUnitPrice(incPriceExcVat.setScale(2));
                    
                    bean.setTaxAmt(newTaxAmt.setScale(2));
                    bean.setIsDiscountOnInclUnitPrice(isDiscOnInclUnitPrices[0]);
                    bean.setIsDiscountOnPercentage(isDiscountsOnPerc[0]);
                    bean.setIsDiscountOnTotal(isDiscountsOnTotal[0]);
                    
                    bean.setPrice(price.multiply(qty));
                    grandTotal = grandTotal.add(price);
                    bean.setgrandTotal(grandTotal);
                    grandTotalWithoutDiscount = grandTotalWithoutDiscount.add(total);
    
                }
            }
        }
        //Check for discount on total
        request.getSession().setAttribute(Constants.DISCOUNT_ON_ORDER_TOTAL, checkoutBean.getDiscountOnTotal());        
        request.getSession().setAttribute(Constants.DISCOUNT_ON_ORDER_TOTAL_PERCENT, checkoutBean.getDiscountOnTotalPercent());      
        request.getSession().setAttribute(Constants.SHOPPING_ORDER_CART_ITEMS, items);
        request.getSession().setAttribute(Constants.CHECKOUT_BEAN, checkoutBean);
      
    }
    
    /*private static void validateUserInput(ItemBean itemBean, CheckoutBean checkoutBean) 
    {
    	BigDecimal discountLimit = checkoutBean.getDiscountLimit();
        BigDecimal[] prices = checkoutBean.getDiscountedPrice();
        Boolean[] isDiscOnInclUnitPrices = checkoutBean.getIsDiscOnInclUnitPrice();
        BigDecimal[] discountedInclUnitPrices = checkoutBean.getDiscInclUnitPrice();
        BigDecimal[] discountPerc = checkoutBean.getDiscount();
        Boolean[] isDiscountsOnPerc = checkoutBean.getIsDiscOnPerc();
        Boolean[] isDiscountsOnTotal = checkoutBean.getIsDiscOnTotal();
        
        if (!isDiscOnInclUnitPrice && actualPrice.compareTo(discountedInclUnitPrice) == 1)
    	{
    		throw new OperationException("incl unit price not valid, a value of " +
    				discountedInclUnitPrice + "was tried to be given");
    	}
        if (!isDiscOnPerc && discountPerc[i].compareTo(bean.getDiscountPercent()) == 1)
        {
        	throw new OperationException("user discount limit exceeded on product " +
        			"" + bean.getProductName() +", discount percentage given is "+ discountPerc[i]);
        }
        if (!isDiscOnTotal && !total.equals(price))
        {
        	if ((price.compareTo(bean.getPriceLimit().multiply(bean.getQty())) == -1) || total.compareTo(price)==-1)
        	throw new OperationException("line total not valid, a value of " +
        			price+" was tried to be given");
        }
		
	}*/

	public static int createGoodsReceiveNote(Properties ctx, CheckoutBean checkoutBean, HttpServletRequest request, String trxName) throws OperationException
    {
        MOrder order = null;

        MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
        int priceListId = 0;        
        Integer bpartnerId = checkoutBean.getBpartnerId();        
        
        if (bpartnerId == null)
        {
            throw new MandatoryException("Vendor is mandatory");
        }
        priceListId = checkoutBean.getPriceListId();
        
        if(checkoutBean.getPriceListId() == null)
        {
            priceListId  = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL);
        }
            
        order = OrderManager.createOrder(ctx,bpartnerId,false,priceListId,UDIOrderTypes.POS_GOODS_RECEIVE_NOTE.getOrderType(),
                        warehouse.get_ID(),checkoutBean.getPaymentRuleFromTenderType(),trxName);            
        
              
        PoManager.save(order); 
          
      
        //create orderlines
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        
        for(int i=0; i<items.size(); i++)
        {
            ItemBean itemBean = items.get(i);           
            
            int m_product_id = itemBean.getProductId();
            BigDecimal qty = itemBean.getQty();
            BigDecimal actualPrice = itemBean.getActualPrice();
            BigDecimal discount = itemBean.getDiscountPercent();
            BigDecimal price = itemBean.getPrice();
            
            OrderManager.createOrderLine(ctx, order, m_product_id, qty, discount, price);           
        }
        
        //complete goods receive note
        order = POSGoodsManager.createAndCompletePODocuments(ctx,order.get_ID(),trxName);
        
        return order.get_ID();
    }
      
    
    
    public static BigDecimal getFromInvoice(Properties ctx, int invoiceId, String value, String trxName) throws OperationException 
    {

        BigDecimal orderDiscount = Env.ZERO;
        BigDecimal orderWriteOff = Env.ZERO;
        BigDecimal orderPayment = Env.ZERO;

        StringBuffer whereClause = new StringBuffer("C_Invoice_ID = ").append(invoiceId)
        .append(" AND AD_Client_ID = ").append(Env.getAD_Client_ID(ctx));
        
        
        int[] cashLineIds = MCashLine.getAllIDs(MCashLine.Table_Name, whereClause.toString(), trxName);

        if (cashLineIds!=null && cashLineIds.length!=0)
        {
            for (int cashLineId : cashLineIds)
            {   
                MCashLine cashLine = new MCashLine(ctx, cashLineId, trxName);

                BigDecimal discountAmount = cashLine.getDiscountAmt()==null?Env.ZERO:cashLine.getDiscountAmt();
                BigDecimal writeOffAmount = cashLine.getWriteOffAmt()==null?Env.ZERO:cashLine.getWriteOffAmt();
                BigDecimal paymentAmount = cashLine.getAmount()==null?Env.ZERO:cashLine.getAmount();

    			orderDiscount = orderDiscount.add(discountAmount);
    			orderWriteOff = orderWriteOff.add(writeOffAmount);
    			if (Constants.ORDER_CASH_AMOUNT.equals(value) || Constants.ORDER_PAYMENT.equals(value))
    	    	{
    				orderPayment = orderPayment.add(paymentAmount);
    	    	}
    		}
    	}
    	if (Constants.ORDER_CARD_AMOUNT.equals(value) || Constants.ORDER_CHECK_AMOUNT.equals(value))
    	{
    		whereClause.append(" AND tenderType = '").append(value).append("'");
    	}

        int[] payIds = MPayment.getAllIDs(MPayment.Table_Name, whereClause.toString(), trxName);

        if (payIds!=null && payIds.length != 0)
        {
            for (int payId : payIds)
            {
                MPayment payment = new MPayment(ctx, payId, trxName);
                BigDecimal discountAmount = payment.getDiscountAmt()==null?Env.ZERO:payment.getDiscountAmt();
                BigDecimal writeOffAmount = payment.getWriteOffAmt()==null?Env.ZERO:payment.getWriteOffAmt();
                BigDecimal paymentAmount = payment.getPayAmt()==null?Env.ZERO:payment.getPayAmt();

                orderDiscount = orderDiscount.add(discountAmount);
                orderWriteOff = orderWriteOff.add(writeOffAmount);
                orderPayment = orderPayment.add(paymentAmount);             
            }
        }

        if (Constants.ORDER_DICOUNT.equals(value))
        {
            return orderDiscount;
        }
        else if (Constants.ORDER_WRITE_OFF.equals(value))
        {
            return orderWriteOff;
        }
        else if (Constants.ORDER_PAYMENT.equals(value) || Constants.ORDER_CARD_AMOUNT.equals(value) 
                || Constants.ORDER_CHECK_AMOUNT.equals(value))
        {
            return orderPayment;
        }
        else
        {
            throw new OperationException("unknown value");
        }
    }
    
    /**
     * Creates a customer return order
     * @throws OperationException 
     * @throws BPartnerOverCreditLimitException 
     */
    public static int createCustomerReturnOrder(Properties ctx, CheckoutBean bean, HttpServletRequest request, String trxName) throws BPartnerOverCreditLimitException, OperationException
    {        
        int priceListId = bean.getPriceListId();  
        int bpartnerId = bean.getBpartnerId();
        int paymentTermId = -1;
        
        MBPartner partner = MBPartner.get(ctx, bpartnerId);
        if(partner != null)
        {
        	paymentTermId = partner.getC_PaymentTerm_ID();
        }
        
        String orderType = bean.getOrderType();
        String paymentRule = bean.getPaymentRuleFromTenderType();
        String tenderType = bean.getTenderType();
        String reason = "";
        String documentNo = null;
        
        OrderLineBean orderBean = new OrderLineBean();
        orderBean.setOrderType(orderType);
        orderBean.setPriceListId(priceListId);
        orderBean.setBpartnerId(bpartnerId);
        orderBean.setPaymentTermId(paymentTermId);
        orderBean.setDescription(reason);   
        orderBean.setPaymentRule(paymentRule);
        
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        
        MOrder order = POSGoodsManager.createCustomerReturn(ctx,orderBean,items,bpartnerId,reason,trxName);        
        POSGoodsManager.completeCustomerReturnOrder(ctx,order.get_ID(),trxName);
        
        return order.get_ID();        
    }
    
    public static int createGoodsReturnNote(Properties ctx, CheckoutBean checkoutBean, HttpServletRequest request, String trxName) throws OperationException
    {
    	MOrder order = null;

        MWarehouse warehouse = POSTerminalManager.getWarehouse(ctx);
        int priceListId = 0;        
        Integer bpartnerId = checkoutBean.getBpartnerId();        
        
        if (bpartnerId == null)
        {
            throw new MandatoryException("Vendor is mandatory");
        }
        priceListId = checkoutBean.getPriceListId();
        
        if(checkoutBean.getPriceListId() == null)
        {
            priceListId  = Env.getContextAsInt(ctx,UdiConstants.POS_PURCHASE_PL);
        }
            
        order = OrderManager.createOrder(ctx,bpartnerId,false,priceListId,UDIOrderTypes.POS_GOODS_RETURN_NOTE.getOrderType(),
                        warehouse.get_ID(),checkoutBean.getPaymentRuleFromTenderType(),trxName);            
        
              
        PoManager.save(order); 
          
      
        //create orderlines
        ArrayList<ItemBean> items = (ArrayList<ItemBean>) request.getSession().getAttribute(Constants.SHOPPING_ORDER_CART_ITEMS);
        
        for(int i=0; i<items.size(); i++)
        {
            ItemBean itemBean = items.get(i);           
            
            int m_product_id = itemBean.getProductId();
            BigDecimal qty = itemBean.getQty();
            BigDecimal actualPrice = itemBean.getActualPrice();
            BigDecimal discount = itemBean.getDiscountPercent();
            BigDecimal price = itemBean.getPrice();
            
            OrderManager.createOrderLine(ctx, order, m_product_id, qty, discount, price);           
        }
        
        //complete goods return note
        order = POSGoodsManager.createAndCompletePODocuments(ctx,order.get_ID(),trxName);
        
        return order.get_ID();
    }
}