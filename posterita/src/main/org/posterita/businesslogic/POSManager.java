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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.compiere.model.MBPartner;
import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.model.MCharge;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutConfirm;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPOS;
import org.compiere.model.MPayment;
import org.compiere.model.MProduct;
import org.compiere.model.MTax;
import org.compiere.model.MTaxCategory;
import org.compiere.model.MUser;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.compiere.util.Trx;
import org.posterita.Constants;
import org.posterita.beans.CashBean;
import org.posterita.beans.CashBookDetailBean;
import org.posterita.beans.CreditCheckBean;
import org.posterita.beans.CurrentTillAmountBean;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.beans.POSBean;
import org.posterita.beans.POSDescriptionBean;
import org.posterita.beans.POSInfoBean;
import org.posterita.beans.ShoppingCartBean;
import org.posterita.beans.WebDocumentBean;
import org.posterita.beans.WebOrderLineBean;
import org.posterita.core.JulianDate;
import org.posterita.core.TrxPrefix;
import org.posterita.core.utils.FormatBigDecimal;
import org.posterita.exceptions.BPartnerOverCreditLimitException;
import org.posterita.exceptions.DiscountLimitException;
import org.posterita.exceptions.InvalidOrderIDException;
import org.posterita.exceptions.InvalidTenderedAmountException;
import org.posterita.exceptions.LimitPriceVioletException;
import org.posterita.exceptions.NoOrderLineFoundException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.formatter.PrintFormatter;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMInvoice;
import org.posterita.model.UDIMInvoiceLine;
import org.posterita.model.UDIMOrder;
import org.posterita.model.UDIMTax;
import org.posterita.order.UDIOrderType;
import org.posterita.order.UDIOrderTypes;


public class POSManager 
{
    //FIXME use Compiere Process ID in model.
    // public static final int PROCESS_ID=110;
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
        
        int posId=Env.getContextAsInt(ctx, UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,null);
        
        if(firstOrderOfTheDay(ctx))
        {
            CashManager.closePreviousDraftedCashjournals(ctx,pos.getC_CashBook_ID(),trxName);
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
            order = OrderManager.createOrder(ctx,bean.getBpartnerId().intValue(),true,pos.getM_PriceList_ID(),orderType.getOrderType(),pos.getM_Warehouse_ID(),paymentRule,trxName);
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
        
        order.setC_POS_ID(posId);
        if(bean.getPaymentTermId()!=null)
        {
            order.setC_PaymentTerm_ID(bean.getPaymentTermId());
        }
        UDIMOrder udiOrder = new UDIMOrder(order);
        udiOrder.save();
        
        Iterator iter = cartBeanItems.iterator();
        ItemBean itemBean;
        
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
                 
                BigDecimal limitPrice=ProductManager.getPrice(ctx,POSTerminalManager.getSalesPriceListVersionId(ctx),itemBean.getProductId().intValue(),false,false,true,trxName);
                MProduct product = new MProduct(ctx,itemBean.getProductId().intValue(),null);
                MTax tax = TaxManager.getTaxFromCategory(ctx, product.getC_TaxCategory_ID(), order.get_TrxName());
               BigDecimal limitPriceWithTax = TaxManager.getPriceWithTax(ctx,limitPrice,tax.getRate());
                
                if(user.isFullBPAccess()==false && actualPrice/itemBean.getQty()<limitPriceWithTax.doubleValue())
                {
                       throw new LimitPriceVioletException("Discount Exceeds Limit price, limit price is= "+limitPriceWithTax.doubleValue());
                }
                else
                {
                    itemBean.setDiscountPercent(new BigDecimal(discount));
                    itemBean.setActualPrice(new BigDecimal(actualPrice));
                }
            }
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
		UDIMInvoice udiposInvoice = new UDIMInvoice(customerInvoice);
		udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
		if (!customerInvoice.getPaymentRule().equalsIgnoreCase(MInvoice.PAYMENTRULE_Cash) && !customerInvoice.getPaymentRule().equals(MInvoice.PAYMENTRULE_OnCredit))
		{
			MPayment paymentReceived = PaymentManager.createARReceipt(ctx, customerInvoice);
			PaymentManager.completePayment(ctx, paymentReceived);
		}

		MInOut shipment = MinOutManager.createMInOut(ctx, udiposInvoice.getInvoice(), order.getM_Warehouse_ID());
      
         if (customerInvoice.isCreditMemo())
         {
            MInOutConfirm confirm=MinOutManager.createConfirmation(ctx,shipment);
            MinOutManager.completeConfirmation(ctx, confirm);
         }
		MinOutManager.completeShipment(ctx, shipment);
        order.setIsDelivered(true);
        UDIMOrder udiOrder = new UDIMOrder(order);
        udiOrder.save();

		if (firstCashInvoice(ctx, udiposInvoice.getInvoice()))
			CashManager.updateBeginningBalance(ctx,order.getC_POS_ID(),order.get_TrxName());

		return udiposInvoice.getInvoice();

    }
    
    
    public static MOrder completePOSOrder(Properties ctx,int orderId,OrderLineBean bean,String trxName) throws InvalidTenderedAmountException,BPartnerOverCreditLimitException,NoOrderLineFoundException,OperationException
    {
        MOrder order = new MOrder(ctx,orderId,trxName);
        
        if(order.getPaymentRule().equalsIgnoreCase(MOrder.PAYMENTRULE_Cash))
        {
            if(bean.getAmountGiven()==null || bean.getAmountGiven().doubleValue()< order.getGrandTotal().doubleValue())
                throw new InvalidTenderedAmountException("The Amount given is Invalid");
            
        }
        
        if(order.getPaymentRule().equalsIgnoreCase(UdiConstants.PAYMENTRULE_MIXED) && bean.getPaymentByCash()!=null & !bean.getPaymentByCash().equals(0.0))
        {
            if(bean.getAmountGiven()==null || bean.getAmountGiven().doubleValue()< bean.getPaymentByCash())
                throw new InvalidTenderedAmountException("The Amount given is Invalid");
            
        }
        
        order.setAmountTendered(bean.getAmountGiven());
        order.setAmountRefunded(bean.getAmountRefunded());
        
        UDIMOrder udiMOrder = new UDIMOrder(order);
        udiMOrder.save();
        
        if(order.getLines().length==0)
        {
            throw new NoOrderLineFoundException("POS Order does not have any orderlines!");
        }      
        
        
        if(order.getPaymentRule().equalsIgnoreCase(UdiConstants.PAYMENTRULE_MIXED))
        {
            UDIMOrder udiOrder = new UDIMOrder(order);
            udiOrder.processIt(DocumentEngine.ACTION_Complete);
            OrderManager.printOrder(ctx,order,bean);
            Double cashAmt = bean.getPaymentByCash();
            if(cashAmt==null)
                cashAmt=0.0;
            
            Double chequeAmt=bean.getPaymentByChq();
            if(chequeAmt==null)
                chequeAmt=0.0;
            
            Double cardAmt = bean.getPaymentByCard();
            if(cardAmt==null)
                cardAmt=0.0;
            
            MInvoice cashInvoice=null;
            
            boolean isTaxIncluded=true; //should be true only for the first occurance
            
            
            if(cashAmt!=null && !cashAmt.equals(0.0))
            {  
                cashInvoice=createInvoiceForMixedPayment(ctx,order.get_ID(),MOrder.PAYMENTRULE_Cash,trxName);
                createInvoiceLines(ctx,cashInvoice,order,isTaxIncluded,trxName);
                
                BigDecimal amtToBeDeducted = new BigDecimal(chequeAmt).add(new BigDecimal(cardAmt));
                addInvoiceLine(ctx,cashInvoice,amtToBeDeducted.negate());
                
                UDIMInvoice  udiposInvoice= new UDIMInvoice(cashInvoice);
                udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
                
                if (firstCashInvoice(ctx, udiposInvoice.getInvoice()))
                    CashManager.updateBeginningBalance(ctx, order.getC_POS_ID(),order.get_TrxName());
                
               // MPayment paymentReceivedCash= PaymentManager.createARReceipt(ctx,cashInvoice);
               // PaymentManager.completePayment(ctx,paymentReceivedCash);
                
                isTaxIncluded=false;
            }
            if(chequeAmt!=null && !chequeAmt.equals(0.0))
            {
                MInvoice chequeInvoice=createInvoiceForMixedPayment(ctx,order.get_ID(),MOrder.PAYMENTRULE_Check,trxName);
                
                if(isTaxIncluded)
                {
                    createInvoiceLines(ctx,chequeInvoice,order,isTaxIncluded,trxName);
                    BigDecimal amtToBeDeducted = new BigDecimal(cashAmt).add(new BigDecimal(cardAmt));
                    addInvoiceLine(ctx,chequeInvoice,amtToBeDeducted.negate());
                }
                else
                {
                    addInvoiceLine(ctx,chequeInvoice,new BigDecimal(chequeAmt)); 
                }
                UDIMInvoice udiposInvoice= new UDIMInvoice(chequeInvoice);
                udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
                
                MPayment paymentReceivedCheque= PaymentManager.createARReceipt(ctx,chequeInvoice);
                PaymentManager.completePayment(ctx,paymentReceivedCheque);
                
                isTaxIncluded=false;
            }
            
            if(cardAmt!=null  && ! cardAmt.equals(0.0))
            {
                
                MInvoice cardInvoice=createInvoiceForMixedPayment(ctx,order.get_ID(),MOrder.PAYMENTRULE_CreditCard,trxName);
                if(isTaxIncluded)
                {
                    createInvoiceLines(ctx,cardInvoice,order,isTaxIncluded,trxName);
                    BigDecimal amtToBeDeducted = new BigDecimal(cashAmt).add(new BigDecimal(chequeAmt));
                    addInvoiceLine(ctx,cardInvoice,amtToBeDeducted.negate());
                }
                else
                {
                    addInvoiceLine(ctx,cardInvoice,new BigDecimal(cardAmt)); 
                }
                
                
                UDIMInvoice udiposInvoice= new UDIMInvoice(cardInvoice);
                udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
                
                MPayment paymentReceivedCard= PaymentManager.createARReceipt(ctx,cardInvoice);
                PaymentManager.completePayment(ctx,paymentReceivedCard);
                
            }
            
            
            //@TODO not allowed to use MOrderLine getallids here, must use a manager.
            int [] orderLines=MOrderLine.getAllIDs(MOrderLine.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+" and C_ORDER_ID="+order.get_ID(),trxName);
            MInOut shipment= MinOutManager.createShipment(ctx,orderId,orderLines,trxName);
            MinOutManager.completeShipment(ctx,shipment);
            if(cashInvoice!=null && firstCashInvoice(ctx,cashInvoice))
                CashManager.updateBeginningBalance(ctx,order.getC_POS_ID(),order.get_TrxName());
            
            return udiOrder.getOrder(); 
            
        }
        else 
            return completePOSOrder(ctx,order);
    }
    

    public static MOrder completePOSOrder2(Properties ctx,int orderId,OrderLineBean bean,String trxName) throws NoOrderLineFoundException,BPartnerOverCreditLimitException,OperationException
    {
        MOrder order = new MOrder(ctx,orderId,trxName);
        
        order.setAmountTendered(bean.getAmountGiven());
        order.setAmountRefunded(bean.getAmountRefunded());
        
        UDIMOrder udiMOrder = new UDIMOrder(order);
        udiMOrder.save();
        
        if(order.getLines().length==0)
        {
            throw new NoOrderLineFoundException("POS Order does not have any orderlines!");
        }      
        
        
        if(order.getPaymentRule().equalsIgnoreCase(UdiConstants.PAYMENTRULE_MIXED))
        {
            UDIMOrder udiOrder = new UDIMOrder(order);
            udiOrder.processIt(DocumentEngine.ACTION_Complete);
            OrderManager.printOrder(ctx,order,bean);
            Double cashAmt = bean.getPaymentByCash();
            if(cashAmt==null)
                cashAmt=0.0;
            
            Double chequeAmt=bean.getPaymentByChq();
            if(chequeAmt==null)
                chequeAmt=0.0;
            
            Double cardAmt = bean.getPaymentByCard();
            if(cardAmt==null)
                cardAmt=0.0;
            
            MInvoice cashInvoice=null;
            
            boolean isTaxIncluded=true; //should be true only for the first occurance
            
            
            if(cashAmt!=null && !cashAmt.equals(0.0))
            {  
                cashInvoice=createInvoiceForMixedPayment(ctx,order.get_ID(),MOrder.PAYMENTRULE_Cash,trxName);
                createInvoiceLines(ctx,cashInvoice,order,isTaxIncluded,trxName);
                
                BigDecimal amtToBeDeducted = new BigDecimal(chequeAmt).add(new BigDecimal(cardAmt));
                addInvoiceLine(ctx,cashInvoice,amtToBeDeducted.negate());
                
                UDIMInvoice  udiposInvoice= new UDIMInvoice(cashInvoice);
                udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
                
              //  MPayment paymentReceivedCash= PaymentManager.createARReceipt(ctx,cashInvoice);
              //  PaymentManager.completePayment(ctx,paymentReceivedCash);
                
                isTaxIncluded=false;
            }
            if(chequeAmt!=null && !chequeAmt.equals(0.0))
            {
                MInvoice chequeInvoice=createInvoiceForMixedPayment(ctx,order.get_ID(),MOrder.PAYMENTRULE_Check,trxName);
                
                if(isTaxIncluded)
                {
                    createInvoiceLines(ctx,chequeInvoice,order,isTaxIncluded,trxName);
                    BigDecimal amtToBeDeducted = new BigDecimal(cashAmt).add(new BigDecimal(cardAmt));
                    addInvoiceLine(ctx,chequeInvoice,amtToBeDeducted.negate());
                }
                else
                {
                    addInvoiceLine(ctx,chequeInvoice,BigDecimal.valueOf(chequeAmt)); 
                }
                UDIMInvoice udiposInvoice= new UDIMInvoice(chequeInvoice);
                udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
                
                MPayment paymentReceivedCheque= PaymentManager.createARReceipt(ctx,chequeInvoice);
                PaymentManager.completePayment(ctx,paymentReceivedCheque);
                
                isTaxIncluded=false;
            }
            
            if(cardAmt!=null  & ! cardAmt.equals(0.0))
            {
                
                MInvoice cardInvoice=createInvoiceForMixedPayment(ctx,order.get_ID(),MOrder.PAYMENTRULE_CreditCard,trxName);
                if(isTaxIncluded)
                {
                    createInvoiceLines(ctx,cardInvoice,order,isTaxIncluded,trxName);
                    BigDecimal amtToBeDeducted = new BigDecimal(cashAmt).add(new BigDecimal(chequeAmt));
                    addInvoiceLine(ctx,cardInvoice,amtToBeDeducted.negate());
                }
                else
                {
                    addInvoiceLine(ctx,cardInvoice,new BigDecimal(cardAmt)); 
                }
                
                
                UDIMInvoice udiposInvoice= new UDIMInvoice(cardInvoice);
                udiposInvoice.processIt(DocumentEngine.ACTION_Complete);
                
                MPayment paymentReceivedCard= PaymentManager.createARReceipt(ctx,cardInvoice);
                PaymentManager.completePayment(ctx,paymentReceivedCard);
                
            }
            
            
            //@TODO not allowed to use MOrderLine getallids here, must use a manager.
            int [] orderLines=MOrderLine.getAllIDs(MOrderLine.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+" and C_ORDER_ID="+order.get_ID(),trxName);
            MInOut shipment= MinOutManager.createShipment(ctx,orderId,orderLines,trxName);
            MinOutManager.completeShipment(ctx,shipment);
            if(cashInvoice!=null && firstCashInvoice(ctx,cashInvoice))
                CashManager.updateBeginningBalance(ctx,order.getC_POS_ID(),order.get_TrxName());
            
            return udiOrder.getOrder(); 
            
        }
        else 
        return completePOSOrderPrintInvoice(ctx,order);
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
        int qty=0;
        
        
        ArrayList<WebOrderLineBean> orderLines = new ArrayList<WebOrderLineBean>();
        for (int i = 0; i < lines.length; i++)
        {
            line = lines[i];
            
            product = new MProduct(ctx, line.getM_Product_ID(), null);
            
            orderLineBean = new WebOrderLineBean();
            BigDecimal roundedLineNetAmount = round(line.getLineNetAmt(), 2);
            orderLineBean.setLineNetAmt(roundedLineNetAmount);
            BigDecimal lineTaxAmount = getLineTaxAmt(line.getCtx(), line.getLineNetAmt(), line.getC_Tax_ID(),line.getQtyOrdered());
            BigDecimal roundedTaxAmount = round(lineTaxAmount, 2);
            orderLineBean.setTaxAmt(roundedTaxAmount);
            BigDecimal lineTotalAmount = line.getLineNetAmt().add(orderLineBean.getTaxAmt());
            BigDecimal roundedLineTotalAmount = round(lineTotalAmount, 2);
            
            orderLineBean.setLineTotalAmt(roundedLineTotalAmount);
            orderLineBean.setUnitPrice(round(line.getPriceList(),2));
            
            orderLineBean.setProductId(Integer.valueOf(line.getM_Product_ID()));
            
            
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
                if(abbr)
                    orderLineBean.setProductName(new PrintFormatter().format(product).toString());
                else     
                    orderLineBean.setProductName(product.getName().replaceAll("~"," "));
                orderLineBean.setDescription(product.getDescription());
            }
            
            orderLineBean.setOrderLineId(Integer.valueOf(line.get_ID()));
            orderLineBean.setIsinvoiced(Boolean.valueOf(false));
            orderLineBean.setIsQtyReserved(Boolean.valueOf(true));
            qty=qty+line.getQtyOrdered().intValue();
            orderLineBean.setQtyTotal(BigDecimal.valueOf(qty)) ;
            
            orderLineBean.setQtyOrdered(line.getQtyOrdered());
            orderLineBean.setDiscountPercentage(line.getDiscount());
            
            orderLineBean.setGrandTotal(order.getGrandTotal());
            
            
            
            
            orderLines.add(orderLineBean);
            
        }
        
        return orderLines;
    }
    
    
    public static BigDecimal round(BigDecimal number, int decimalPlaces)
    {
        BigDecimal roundedNumber = number.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
        
        return roundedNumber;
    }
    
    
    
    public static WebDocumentBean calculateOrderTotals(ArrayList webOrderLineList)
    {
        Iterator iter = webOrderLineList.iterator();
        
        WebOrderLineBean bean;
        WebDocumentBean webDocumentBean=new WebDocumentBean();
        BigDecimal totalLines = new BigDecimal(0);
        BigDecimal totalTax = new BigDecimal(0);
        int totalQty=0;
        BigDecimal grandTotal;
        int amout;
        amout=2;
        
        
        BigDecimal roundedTotalLines;
        BigDecimal roundedTotalTax;
        BigDecimal roundedGrandTotal;
        // BigDecimal newGrandTotal=new BigDecimal(0);
        // BigDecimal newTotalLines;
        while(iter.hasNext())
        {
            bean = (WebOrderLineBean) iter.next();
            totalLines = totalLines.add(bean.getLineNetAmt());
            totalTax = totalTax.add(bean.getTaxAmt());
            totalQty=totalQty+bean.getQtyTotal().intValue();
            
            
            
        }
        
        grandTotal = totalLines.add(totalTax);
        roundedTotalLines = round(totalLines, amout);
        roundedTotalTax = round(totalTax, amout);
        roundedGrandTotal = round(grandTotal, 2);
        webDocumentBean.setTotalLines(roundedTotalLines);
        webDocumentBean.setTotalTax(roundedTotalTax);
        webDocumentBean.setGrandTotal(roundedGrandTotal);
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
            String sql="delete from c_orderline where c_orderline_id="+orderlineIds[i].intValue();
            
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
 
    public static ArrayList<POSInfoBean> getPOSInfo(Properties ctx,String fromDate,String todate) throws OperationException
    {
    	ArrayList <POSInfoBean> list  =  new ArrayList<POSInfoBean>();
        String sql = "select C_POS_ID " +
                "from c_pos where ad_client_id="+Env.getAD_Client_ID(ctx)+
                " and AD_ORG_ID="+Env.getAD_Org_ID(ctx)
                +" and isActive='Y'";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        POSInfoBean bean=null;
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                bean=new POSInfoBean();
                CurrentTillAmountBean tillBean=getCurrentTillForInfo(ctx,rs.getInt(1),fromDate,todate);
                bean.setPosName(tillBean.getPosName()+" "+POSTerminalManager.getCurrencyOfCashBook(ctx,rs.getInt(1)).getCurSymbol());
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
            try
            {
                pstmt.close();                
            }
            catch(Exception ex){}
            
          
        } 
        return list;
    }
    
    public static ArrayList<Object[]> getPOSInfoReportData(Properties ctx,String fromDate,String todate) throws OperationException
    {
        ArrayList<POSInfoBean> list = getPOSInfo(ctx,fromDate,todate);
        ArrayList<Object[]> reportData = new ArrayList<Object[]>();

       
        
        Object[] headers = new Object[]{"POS Name","Cash Total","Card Total","Cheque Total","Amount"};
        reportData.add(headers);
        
        //NumberFormat formatter = new DecimalFormat("###,###,##0.00"); 
        DecimalFormat f = DisplayType.getNumberFormat(DisplayType.CostPrice);
        
        Object[] data = null;
        String grandTotal = null;
        
        for (POSInfoBean bean : list) 
        {
            data = new Object[5];
            
            data[0] = bean.getPosName();
            data[1] = formatter.format(bean.getCashTotal().doubleValue());
            data[2] = formatter.format(bean.getCardTotal().doubleValue());
            data[3] = formatter.format(bean.getChequeTotal().doubleValue());
            
            //format the currency
            grandTotal = formatter.format(bean.getOrderGrandTotal().doubleValue());
            
            data[4] = grandTotal;    			
            
            reportData.add(data);
        }
        
        return reportData;
    }
    
    
    
    protected static boolean firstCashInvoice(Properties ctx,MInvoice invoice) throws OperationException
    {
        boolean firstCashInvoice=false;
        CashBean bean=null;
        if(invoice.getPaymentRule().equals(MOrder.PAYMENTRULE_Cash))
        {
            MOrder order=new MOrder(ctx,invoice.getC_Order_ID(),invoice.get_TrxName());
            MPOS pos = new MPOS(ctx,order.getC_POS_ID(),invoice.get_TrxName());
            bean=CashManager.getData(ctx,pos.getC_CashBook_ID(),true,invoice.get_TrxName());
            if(bean==null)
                return false;
            MCash cash = new MCash(ctx,bean.getCashJournalId().intValue(),invoice.get_TrxName());
            String whereClause=" AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
            " and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
            " and C_CASH_ID="+cash.get_ID() ;
            int [] cashLineIds=MCashLine.getAllIDs(MCashLine.Table_Name,whereClause,invoice.get_TrxName()); 
            if(cashLineIds.length>1)
                firstCashInvoice=false;
            else
                firstCashInvoice=true;
        }
        
        return firstCashInvoice;
    }
    
    
    public static BigDecimal getSumOfChequeAndCard(Properties ctx,Timestamp toDate,String paymentRule) throws OperationException
    {
        int posId=Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        BigDecimal grandTotal =null;
        
        long date = toDate.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String sqlDate = sdf.format(new Date(date));      
        
        String sql="select sum(pay.payAmt) " +
        " from C_order ord,c_payment pay right outer join C_invoice inv  on inv.c_Invoice_id=pay.c_Invoice_id" +
        " where pay.created>=TO_DATE('" +sqlDate+"','YYYY-MM-DD HH24:MI:SS')"+
        " and inv.c_order_id=ord.c_order_id"+
        " and ord.POSID="+posId+
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
                
               grandTotal=grandTotal.add(paymentAmtWithOutInvoice(ctx,sqlDate,null,paymentRule,posId)); 
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
    
    private static BigDecimal paymentAmtWithOutInvoice(Properties ctx,String fromDate,String toDate,String paymentRule,int posId) throws OperationException
    {
        BigDecimal grandTotal =null;
        String sql="select sum(pay.payAmt) " +
                "from C_PAYMENT pay " ;
                if(toDate==null)
                {
                    sql=sql+ "where pay.created>=TO_DATE('" +fromDate+"','YYYY-MM-DD HH24:MI:SS')";
                }
                else
                {
                    sql=sql+  " where pay.created between to_date('"+ fromDate+"','DD-MM-YYYY HH24:MI:SS') " +
                    " and to_date('" + toDate+"','DD-MM-YYYY HH24:MI:SS') " ; 
                }
            
            
                sql=sql+" and not exists(select * from c_invoice inv where pay.C_invoice_id=inv.c_invoice_id)" +
                " and pay.tenderType='"+paymentRule+"'";
                
                sql=sql+" and pay.description='"+posId+"'"; //pos id was saved into descriptuon while creating the payment for multiple invoice
        
        
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
    
    
    public static BigDecimal getSumOfChequeAndCardPaymentsForInfo(Properties ctx,String fromDate,String toDate,String paymentRule,int posId) throws OperationException
    {
       
        BigDecimal grandTotal =null;
        
        
        String sql="select sum(pay.payAmt) " +
        " from C_INVOICE inv,C_order ord,c_payment pay " +
        " where inv.c_order_id=ord.c_order_id"+
        " and inv.c_Invoice_id=pay.c_Invoice_id"+
        " and ord.POSID="+posId+
        " and ord.AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and ord.AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
        " and inv.isSotrx='Y'"+
        " and ord.orderType in ('"+UDIOrderTypes.POS_ORDER.getOrderType()+"',"+
        "'"+UDIOrderTypes.CREDIT_ORDER.getOrderType()+"')"+
        " and pay.tenderType='"+paymentRule+"'"+
        " and pay.created between to_date('"+ fromDate+"','DD-MM-YYYY HH24:MI:SS') " +
        " and to_date('" + toDate+"','DD-MM-YYYY HH24:MI:SS') " ;
        
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
    
    public static Timestamp getTimeOfLastCompletedJournal(Properties ctx,MPOS pos) throws OperationException
    {
        
        /**It returns the date-time of last completed cash journal, and if there is no completed journal it returns
         todays date starting at midnight*/
        
        Timestamp created=null;
        CashBean bean=null;
        bean=CashManager.getData(ctx,pos.getC_CashBook_ID(),false,null);
        if (bean==null)
        { 
            Timestamp time = new Timestamp(System.currentTimeMillis());
            Date adate= new Date(time.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String newDate=sdf.format(adate);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            Timestamp timestamp = null;
            try
            {
                date = sdf2.parse(newDate);
                timestamp= new Timestamp(date.getTime());
            } 
            catch (ParseException e) 
            {
                throw new OperationException(e);
            }
            return  timestamp;
        }
        MCash cash = new MCash(ctx,bean.getCashJournalId().intValue(),null);
        
        String sql="select max(created) from C_CASHLINE"+
        " where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and AD_ORG_ID="+Env.getAD_Org_ID(ctx)+
        " and C_CASH_ID="+cash.get_ID();
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
            {
                created=rs.getTimestamp(1);
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
        
        return created;
    }
    
    public static ArrayList getAllPOSIDs(Properties ctx)
    {
        int [] POSIds = MPOS.getAllIDs(MPOS.Table_Name,"AD_ORG_ID="+Env.getAD_Org_ID(ctx)+" and AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and ISACTIVE='Y'",null);
        
        POSDescriptionBean bean;
        MPOS pos;
        MWarehouse warehouse ;
        
        ArrayList <POSDescriptionBean> list = new ArrayList<POSDescriptionBean>();
        
        for(int i=0;i<POSIds.length;i++)
        {
            bean = new POSDescriptionBean();
            pos = new MPOS(ctx,POSIds[i],null);
            
            bean.setPosId(Integer.valueOf(POSIds[i]));
            bean.setPosName(pos.getName());
            bean.setWareHouseId(Integer.valueOf(pos.getM_Warehouse_ID()));
            
            warehouse = new MWarehouse(ctx,pos.getM_Warehouse_ID(),null);
            bean.setWarehouseName(warehouse.getName());
            
            list.add(bean);
        }
        
        return list;
    }
    
    private static CurrentTillAmountBean getCurrentTillForInfo(Properties ctx,int posId,String fromDate,String toDate) throws OperationException
    {
        BigDecimal cardSum = BigDecimal.valueOf(0.0);
        BigDecimal chequeSum = BigDecimal.valueOf(0.0);
        BigDecimal cashSum=new BigDecimal(0);
        BigDecimal grandTotal;
        BigDecimal beginingBalance=null;
        BigDecimal statementDifference=null;
        MPOS pos = new MPOS(ctx,posId,null);
        cardSum = getSumOfChequeAndCardPaymentsForInfo(ctx,fromDate,toDate,MPayment.TENDERTYPE_CreditCard,posId);
        chequeSum = getSumOfChequeAndCardPaymentsForInfo(ctx,fromDate,toDate,MPayment.TENDERTYPE_Check,posId);
        ArrayList list = CashManager.getCashBookDetailsForTill(ctx,pos.getC_CashBook_ID(),fromDate,toDate);
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
        bean.setPosName(pos.getName());
        
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
        int posId = Env.getContextAsInt(ctx,UdiConstants.POS_ID);
        MPOS pos = new MPOS(ctx,posId,null);
        Timestamp toDate=getTimeOfLastCompletedJournal(ctx,pos);
        cardSum = getSumOfChequeAndCard(ctx,toDate,MPayment.TENDERTYPE_CreditCard);
        chequeSum = getSumOfChequeAndCard(ctx,toDate,MPayment.TENDERTYPE_Check);
        ArrayList list = CashManager.getCashBookDetails(ctx);
        Iterator iter = list.iterator();
        
        CashBookDetailBean cashDetBean;
        while(iter.hasNext())
        {
            cashDetBean = (CashBookDetailBean)iter.next();
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
        bean.setPosName(pos.getName());
        
        return bean;
        
    }
    
    public static int getPurchasePLVersion(Properties ctx) throws OperationException
    {
        String sql = "select M_PRICELIST_VERSION_ID from M_PRICELIST_VERSION where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and M_PRICELIST_ID="+Env.getContextAsInt(ctx, UdiConstants.PRICELIST_CTX_PARAM)+" and isactive='Y'";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        int priceListVersionId=0;
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                priceListVersionId=rs.getInt(1);
            }
        } 
        catch (SQLException e)
        {
            throw new OperationException(e);
        }
        finally
        {
            try
            {
                if (pstmt!=null)
                    pstmt.close();	 
            }
            catch(Exception e) {}
        }
        
        return priceListVersionId;
        
    }
    
    
    public static int getPurchasePList(Properties ctx,int priceListVersionId) throws OperationException
    {
        String sql = "select M_PRICELIST_ID from M_PRICELIST_VERSION where AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+
        " and M_PRICELIST_VERSION_ID='"+priceListVersionId+"' and isactive='Y'";
        
        PreparedStatement pstmt = DB.prepareStatement(sql,null);
        
        int priceListId=0;
        
        try 
        {
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                priceListId=rs.getInt(1);
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
        
        return priceListId;
        
    }
    
    
    public static boolean getQtyAndItem(OrderLineBean bean) throws Exception
    {
        String qtyAndItem = bean.getQtyAndItem();
        String delimiter="+";
        boolean saved=true;
        
        try
        {      
            StringTokenizer token = new StringTokenizer(qtyAndItem,delimiter);
            
            if(token.countTokens()==1)
            {
                bean.setBarCode(bean.getQtyAndItem()); 
                return saved;
            }
            
            
            if(token.hasMoreTokens())
            {
                Integer qty=Integer.valueOf(token.nextToken());
                String barcode=token.nextToken();
                bean.setQuantity(qty);
                bean.setBarCode(barcode);
                
            }
        }
        catch(Exception e)
        {
            throw new Exception("Check the product again",e);
        }
        return saved;
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

        UDIMInvoice udiInvoice = new UDIMInvoice(invoice);
        udiInvoice.save();
        
        
        MOrderLine [] orderLines=salesOrder.getLines();
        MInvoiceLine invoiceLine;
        MTax tax;
        UDIMInvoiceLine udiInvoiceLine;
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
            
            
            udiInvoiceLine = new UDIMInvoiceLine(invoiceLine);
            udiInvoiceLine.save();
        }
        
        
        return invoice;
    }
    
    
    
    
    protected static MInvoice createInvoiceForMixedPayment(Properties ctx,int  salesOrderId,String trxType,String trxName) throws OperationException
    {
        MOrder salesOrder = new MOrder(ctx,salesOrderId,trxName);
        MInvoice invoice = null;
        
        boolean isSOTrx = salesOrder.isSOTrx();
        
        if (!isSOTrx)
            throw new OperationException("Customer Invoice should be created from sales Order");
        
        MDocType [] docType;
        docType = MDocType.getOfDocBaseType(ctx,MDocType.DOCBASETYPE_ARInvoice); 
        invoice = new MInvoice(ctx,0,trxName);
        invoice.setIsSOTrx(true);
        invoice.setC_Order_ID(salesOrder.get_ID());
        invoice.setPaymentRule(trxType);
        invoice.setC_DocTypeTarget_ID(MDocType.DOCBASETYPE_ARInvoice);
        invoice.setC_DocType_ID(docType[0].get_ID());
        invoice.setC_BPartner_ID(salesOrder.getC_BPartner_ID());
        invoice.setC_Currency_ID(salesOrder.getC_Currency_ID());
        invoice.setIsTaxIncluded(false);
        invoice.setIsDiscountPrinted(true);
        UDIMInvoice udiInvoice = new UDIMInvoice(invoice);
        udiInvoice.save();
        
        return invoice;
    }
    
    
    protected static void createInvoiceLines(Properties ctx,MInvoice invoice,MOrder salesOrder,boolean isTaxincluded,String trxName) throws OperationException
    {
        
        
        MOrderLine [] orderLines=salesOrder.getLines();
        MInvoiceLine invoiceLine;
        MTax tax;
        UDIMInvoiceLine udiInvoiceLine;
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
            
            if(isTaxincluded)  //tax is calculated for the first invoice only
            {
                invoiceLine.setC_Tax_ID(orderLines[i].getC_Tax_ID());
                invoiceLine.setTaxAmt(tax.calculateTax(orderLines[i].getLineNetAmt(),false,3));  
            }
            else
            {
                BigDecimal taxAmt = tax.calculateTax(orderLines[i].getLineNetAmt(),false,3);
                invoiceLine.setPrice(orderLines[i].getPriceActual().add(taxAmt));
                invoiceLine.setPriceList(orderLines[i].getPriceActual().add(taxAmt));
                invoiceLine.setPriceActual(orderLines[i].getPriceActual().add(taxAmt));
                invoiceLine.setLineNetAmt(orderLines[i].getPriceActual().add(taxAmt));
                invoiceLine.setC_Tax_ID(getTAXforzero(ctx,trxName));
                invoiceLine.setTaxAmt(new BigDecimal(0));  
            }
            udiInvoiceLine = new UDIMInvoiceLine(invoiceLine);
            udiInvoiceLine.save();
        }
        
        
    }
        
    
    public static String getPOSIDFromCookie(HttpServletRequest request) 
    {
        Cookie[] cookies = request.getCookies();
        String posId = null;
        
        if(cookies != null)
            for(int i=0;i<cookies.length;i++)
            {
                if(cookies[i].getName().equalsIgnoreCase(Constants.POSID))
                {
                    posId = cookies[i].getValue();				
                }
                
            }
        return posId;
        
    }
    
    public static void setPOSIDInCookie(HttpServletResponse response,String posId)
    {
        Cookie cookie =  new Cookie(Constants.POSID,posId);
        
        cookie.setMaxAge(365*24*60*60);		
        response.addCookie(cookie);
    }
    
    
    public static boolean isPOSTerminalPresent(Properties ctx, int posId)
    {
        String whereClause = "AD_Client_ID=" + Env.getAD_Client_ID(ctx) + " and C_Pos_ID=" + posId;
        int posIds[] = MPOS.getAllIDs(MPOS.Table_Name, whereClause, null);
        if(posIds.length == 0)
            return false;
        return true;
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
        UDIMInvoiceLine udiInvoiceLine= new UDIMInvoiceLine(invoiceLine);
        udiInvoiceLine.save();
        
    }
    
    
    private static int getTAXforzero(Properties ctx,String trxName) throws OperationException
    {
        
        int taxIds []= MTax.getAllIDs(MTax.Table_Name,"AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and rate=0",null);
        
        if(taxIds.length!=0)
        {
            MTax tax = new MTax(ctx,taxIds[0],null);
            return tax.get_ID();
        }
        
        
        MTaxCategory taxCategory =null;
        
        taxCategory =new MTaxCategory(ctx,0,trxName);
        
        taxCategory.setName("VAT 0%");
        taxCategory.save();
        
        MTax tx = new MTax(ctx,0,trxName);
        tx.setName("VAT 0%");
        tx.setValidFrom(new Timestamp(System.currentTimeMillis()));
        tx.setC_TaxCategory_ID(taxCategory.get_ID());
        
        UDIMTax udiTax = new UDIMTax(tx);
        udiTax.save();
        
        return udiTax.getMTax().get_ID();
        
    }
    
    public static BigDecimal getLineTaxAmt(Properties ctx, BigDecimal lineNetAmt, int taxId, BigDecimal qty)
    {
        BigDecimal baseAmt = Env.ZERO;
        
        baseAmt = baseAmt.add(lineNetAmt);
        
        MTax tax = new MTax(ctx, taxId, null);
        
        return (tax.calculateTax(baseAmt, false, 2));
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
            item.setQty(Integer.valueOf(qty.intValue()));
            
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
            item.setQty(Integer.valueOf(qty.intValue()));
            
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
    
    
}
