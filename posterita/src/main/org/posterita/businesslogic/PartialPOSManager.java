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
 * Created on 14-Mar-2006
 */


package org.posterita.businesslogic;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.compiere.model.MInOut;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.process.DocAction;
import org.compiere.process.DocumentEngine;
import org.compiere.util.DB;
import org.compiere.util.Env;

import org.posterita.Constants;
import org.posterita.beans.ItemBean;
import org.posterita.beans.OrderLineBean;
import org.posterita.core.DocStatusMap;
import org.posterita.exceptions.InvalidInvokeOrderStatusException;
import org.posterita.exceptions.InvalidOrderIDException;
import org.posterita.exceptions.InvalidOrderTypeException;
import org.posterita.exceptions.InvalidTenderedAmountException;
import org.posterita.exceptions.NoOrderLineFoundException;
import org.posterita.exceptions.NoOrderLineSelectedException;
import org.posterita.exceptions.OperationException;
import org.posterita.lib.UdiConstants;
import org.posterita.model.UDIMInvoice;
import org.posterita.model.UDIMOrder;
import org.posterita.model.UDIMOrderLine;
import org.posterita.order.UDIOrderTypes;


public class PartialPOSManager extends POSManager
{	        
    public static void validateMixedPaymentAmount(OrderLineBean bean) throws InvalidTenderedAmountException
    {
        if(bean.getTrxType().equalsIgnoreCase("Mixed"))
        {
        	double cashAmt = 0.0;
            double chequeAmt = 0.0;
            double cardAmt = 0.0;
            
            cashAmt = (bean.getPaymentByCash()!=null) ? bean.getPaymentByCash() : cashAmt;
            chequeAmt = (bean.getPaymentByChq()!=null) ? bean.getPaymentByChq() : chequeAmt;
            cardAmt = (bean.getPaymentByCard()!=null) ? bean.getPaymentByCard() : cardAmt;
            
            double sumActualAmt=bean.getTotalActualPrice();
            
            
            if(
            		(cashAmt+chequeAmt+cardAmt !=sumActualAmt)||
            		(cashAmt<0.0)||
            		(chequeAmt<0.0)||
            		(cardAmt<0.0)
            	)
            {
            	throw new InvalidTenderedAmountException("The Tendered Amount should be equal to the total amount");  
            }                            
        }             
    }
    
    //FIXME
    public static String deleteOrderLines(Properties ctx,Integer[]orderlineIds,String trxName) throws OperationException 
    {
    	if((orderlineIds == null)||(orderlineIds.length == 0))
    	{
    		throw new NoOrderLineSelectedException("Cannot delete orderlines. No orderlines supplied!");
    	}
        
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
	            		pstmt.close();
            	}
            	catch(Exception ex) {}
            	
            	pstmt = null;
            }
            
            
        }
        
        return DELETE_PARTIAL_POS_ORDERLINE;        
        
    }  
	
	
	public static String getPaymentRule(Properties ctx,String trxType)
	{		
		if(Constants.PAYMENT_RULE_CASH.equalsIgnoreCase(trxType)) 
			return MOrder.PAYMENTRULE_Cash;
		
		if(Constants.PAYMENT_RULE_CARD.equalsIgnoreCase(trxType)) 
			return MOrder.PAYMENTRULE_CreditCard;
		
		if(Constants.PAYMENT_RULE_CHEQUE.equalsIgnoreCase(trxType)) 
			return MOrder.PAYMENTRULE_Check;
		
		if(Constants.PAYMENT_RULE_MIXED.equalsIgnoreCase(trxType))
			return UdiConstants.PAYMENTRULE_MIXED;
        		
	    return null;
	}
	//TODO should take a trx.
	public static MOrder setOrderPaymentDetails(Properties ctx,int  orderId ,OrderLineBean bean,ArrayList<ItemBean> cartBeanItems, String trxName) throws OperationException
    {    
		
		MOrder order = new MOrder(ctx,orderId,trxName);
        validateMixedPaymentAmount(bean);

        String paymentRule = getPaymentRule(ctx,bean.getTrxType());        
               
        order.setPaymentRule(paymentRule);
        
        UDIMOrder uOrder = new UDIMOrder(order);
        uOrder.save(); //<---- blocks
        
        int i=0;
        for(ItemBean itemBean : cartBeanItems)
        {
        	double actualPrice = 0.0;
            double discount = 0.0;
            
            if(bean.getDiscountPercent()!=null)
            {
                String discountEntered = bean.getDiscountPercent()[i];
                
                if( discountEntered == null ||  discountEntered.equals(""))
                {
                    discount = 0;
                }
                else 
                {
                    discount = Double.parseDouble(bean.getDiscountPercent()[i]);
                    
                    //double d = 100.0 - discount;
                    double priceWithDiscount = Double.parseDouble(bean.getActualPrice()[i]);
                    
                    // newPrice = (actualPrice + VAT) - discount
                    
                    actualPrice = (100.0 / 115.0) * priceWithDiscount;
                }
            }                
            
            itemBean.setDiscountPercent(new BigDecimal(discount));
            itemBean.setActualPrice(new BigDecimal(actualPrice));            
            
            updateOrderLine(ctx,order,itemBean.getProductId().intValue(),itemBean.getDiscountPercent(),itemBean.getActualPrice()) ;
                        
            i++;
        }
      
        
        return order;
    }
	
	private static void updateOrderLine(Properties ctx,MOrder order,int productID,BigDecimal discount,BigDecimal actualPrice) throws OperationException
	{
		//load orderLine
		int orderId = order.get_ID();
		
		int id[] = MOrderLine.getAllIDs(MOrderLine.Table_Name,"C_ORDER_ID = "+orderId+" AND M_PRODUCT_ID = "+productID,order.get_TrxName());
		if((id==null)||(id.length==0))
		{
			throw new OperationException("OrderLine not found.");
		}
		
		String trxName = order.get_TrxName();
		
		MOrderLine orderLine = new MOrderLine(ctx,id[0],trxName);
        
		//calculating new price
        BigDecimal newActualPrice = OrderManager.calculateActualPrice(ctx,orderLine.getPriceActual(),discount);
        orderLine.setPrice (newActualPrice);
        
        UDIMOrderLine udiOrderLine = new UDIMOrderLine(orderLine);
        udiOrderLine.save();
        
	}
	
	public static MOrder completePOSOrder(Properties ctx,MOrder order,OrderLineBean bean) throws OperationException
    {
		String trxName = order.get_TrxName();
		
        if(order.getPaymentRule().equalsIgnoreCase(UdiConstants.PAYMENTRULE_MIXED))
        {
            UDIMOrder udiOrder = new UDIMOrder(order);
            udiOrder.processIt(DocumentEngine.ACTION_Complete);
            OrderManager.printOrder(ctx,order,bean);
            Double cashAmt = bean.getPaymentByCash();
            
            Double chequeAmt=bean.getPaymentByChq();
            
            Double cardAmt = bean.getPaymentByCard();
            
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
                
                MPayment paymentReceivedCash= PaymentManager.createARReceipt(ctx,cashInvoice);
                PaymentManager.completePayment(ctx,paymentReceivedCash);
                
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
            
            
            
            int [] orderLines = OrderManager.getAllOrderlineIDs(ctx, order.get_ID(), trxName);
            
            MInOut shipment= MinOutManager.createShipment(ctx,order.get_ID(),orderLines,trxName);
            MinOutManager.completeShipment(ctx,shipment);
            
            if(cashInvoice!=null && firstCashInvoice(ctx,cashInvoice))
                CashManager.updateBeginningBalance(ctx,order.getC_POS_ID(),order.get_TrxName());
            
            return udiOrder.getOrder(); 
            
        }
        else 
            return completePOSOrder(ctx,order);
    }
	
	public static MOrder invokePartialOrder(Properties ctx,int orderId,String trxName) throws InvalidOrderIDException,NoOrderLineFoundException,InvalidInvokeOrderStatusException
	{
		//checking whether order exixts        
        int ids[] = MOrder.getAllIDs(MOrder.Table_Name,"ISACTIVE='Y' AND C_ORDER_ID = " + orderId,null);
        
        if((ids == null)||(ids.length == 0))
        {
        	throw new InvalidOrderIDException("Could not found order!");
        }
        
        //loading order         
        MOrder order = new MOrder(ctx,ids[0],trxName);
        
        //checking order status
        String status = order.getDocStatus();
        if((status.equals(DocAction.STATUS_Drafted))||(status.equals(DocAction.STATUS_InProgress)))
        {
        	//looking for orderlines
        	if(OrderManager.isOrderEmpty(ctx,orderId,trxName))
        	{
        		throw new NoOrderLineFoundException("Order does contain any orderlines!");
        	}        	      	
        }
        else
        {
        	//display err msg
        	String s = DocStatusMap.getDocStatusMap().get(status);
        	throw new InvalidInvokeOrderStatusException("Cannot invoke with status: " + s,s);
        }
        
        return order;
	}
	
	public static MOrder invokePartialOrder (Properties ctx, String documentNo, String trxName) throws InvalidOrderIDException,NoOrderLineFoundException,InvalidInvokeOrderStatusException, InvalidOrderTypeException
	{
        int orderId=getOrderIdFromDocumentNo(ctx,documentNo);
        //loading order  
        MOrder order = new MOrder(ctx,orderId,trxName);
        
        //check order type
        //only drafted pos orders can be invoked
        String orderType = order.getOrderType();
        if(! orderType.equalsIgnoreCase( UDIOrderTypes.POS_ORDER.getOrderType()  ) )
        {
        	throw new InvalidOrderTypeException("Can only POS orders ");
        }
        
        //checking order status
        String status = order.getDocStatus();
        if((status.equals(DocAction.STATUS_Drafted))||(status.equals(DocAction.STATUS_InProgress)))
        {
        	//looking for orderlines
        	if(OrderManager.isOrderEmpty(ctx,orderId,trxName))
        	{
        		throw new NoOrderLineFoundException("Order does contain any orderlines!");
        	}        	      	
        }
        else
        {
        	//display err msg
        	String s = DocStatusMap.getDocStatusMap().get(status);
        	throw new InvalidInvokeOrderStatusException("Cannot invoke with status: " + s,s);
        }
        
        return order;
	}
    
    public static int getOrderIdFromDocumentNo(Properties ctx,String documentNo) throws InvalidOrderIDException
    {
//      checking whether order exixts        
        int ids[] = MOrder.getAllIDs(MOrder.Table_Name,"ISACTIVE='Y' AND AD_CLIENT_ID="+Env.getAD_Client_ID(ctx)+" and DOCUMENTNO = '" + documentNo + "'",null);
        
        if((ids == null)||(ids.length == 0))
        {
            throw new InvalidOrderIDException("Could not found order!");
        }
        
       return ids[0];
            
    }
    
}
